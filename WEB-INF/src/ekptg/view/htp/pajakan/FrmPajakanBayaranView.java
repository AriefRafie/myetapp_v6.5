package ekptg.view.htp.pajakan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPajakanBayaranPajakanCukaiTanahData;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;

public class FrmPajakanBayaranView extends AjaxBasedModule{

	  private static final long serialVersionUID = 1L;
	  private static final String PATH="app/htp/pajakan/bayaran/";
	  private static Logger log = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanBayaranView.class);
	    
	  FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	  FrmPajakanBayaranPajakanCukaiTanahData logic = new FrmPajakanBayaranPajakanCukaiTanahData();
	  private HtpPermohonan htpPermohonan = null;
	  private IHtp iHTP = null;  
	  private String idFail = "";
	  
	@Override
	public String doTemplate2() throws Exception {	
		HttpSession session = this.request.getSession();
        Boolean postDB = false;
        String doPost = session.getAttribute("doPost").toString();
        if (doPost.equals("true")) {
            postDB = true;
        }
        //GET DEFAULT PARAM
        String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        String vm = "";
        String actionPajakan = getParam("actionPajakan"); //our main submit
        if (actionPajakan.isEmpty()){
        	actionPajakan = "BayaranPajakan";
        }
        String submit = getParam("command"); //for doAjax only
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
        //GET ID PARAM
        String idFailSession = "";
        String idPermohonan = getParam("idPermohonan");
        if (session.getAttribute("idFail") != null){
        	idFailSession = String.valueOf(session.getAttribute("idFail"));
    		htpPermohonan = getIHTP().findPermohonan(idFailSession,idPermohonan,"");
    		if(htpPermohonan == null)
				throw new Exception(getIHTP().getErrorHTML("[HTP PAJAKAN] SILA KEMASKINI FAIL TERLEBIH DAHULU"));
			
    		idPermohonan = String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan());
       
        }
        idFail = getParam("idFail").equals("")?idFailSession:getParam("idFail");
 		session.setAttribute("idFail", idFail);
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idBayaran = getParam("idBayaran");

        //VECTOR
        Vector list = null;
        Vector beanHeader = null;
        
        //vm  = PATH+"frmPajakanBayaranPajakanCukaiTanah.jsp";
        vm  = PATH+"index.jsp";
        
        //HITBUTTON
        if (postDB) {
        	if ("saveBayaran".equals(hitButton)){    			
    			saveBayaran(idPermohonan, session);
    		}
    		if ("hapusBayaran".equals(hitButton)){    			
    			logic.hapusBayaran(idBayaran);
    		}
    		if ("saveUpdateBayaran".equals(hitButton)){    			
    			saveUpdateBayaran(idBayaran, session);
    		}
        	
    	}
        
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
			
			/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String strval = "display";
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				strval = "hide";
				throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
			}
			this.context.put("valFlagMohonFail", strval);
			
			 if(actionPajakan.equalsIgnoreCase("BayaranPajakan")){       	
		        
//		        logic.setListMaklumatBayaran(idPermohonan);
				 logic.setListMaklumatBayaran(idFail);
		        BayaranView(mode, idBayaran);
		        	
		     }
		}
                
        //SET DEFAULT PARAM
        this.context.put("actionPajakan", actionPajakan);
        this.context.put("mode", mode);

        //SET DEFAULT ID PARAM
        this.context.put("idFail", idFail);
        this.context.put("idStatus", idStatus);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("subUrusan", subUrusan);
        this.context.put("idBayaran", idBayaran);
		this.context.put("page", "5");
		getPermohonanInfo();
		if(getParam("idPermohonan").equals("")){
			getPermohonanInfo(idPermohonan);
		}
        //log.info(vm+":idPermohonan="+idPermohonan);
        return vm;
        
	}
	
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}

	private void getPermohonanInfo(String idPermohonan )throws Exception{
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIHTP().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	
	 public void BayaranView(String mode, String idBayaran) throws Exception{
	    	
		 String tujuan = getParam("socTujuan");
			if (tujuan == null || tujuan.trim().length() == 0){
				tujuan = "99999";
			}
			String caraBayar = getParam("socCaraBayar");
			if (caraBayar == null || caraBayar.trim().length() == 0){
				caraBayar = "99999";
			}
		 
		 try{
	    		
	    		Vector beanBayaran = null;

	    		Vector v = logic.getListMaklumatBayaran();
	    		Hashtable hashBayar = null;
	    		this.context.put("BayaranList", v);

	    		if (mode.equalsIgnoreCase("newBayaran")){
	    			
	    			beanBayaran = new Vector();
	    			Hashtable hashBayaran = new Hashtable();
	    			hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
	    			hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
	    			hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
	    			hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
	    			hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
	    			hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
	    			hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
	    			hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));

	    			beanBayaran.addElement(hashBayaran);
					this.context.put("BeanBayaranList", beanBayaran);
					
					this.context.put("selectTujuan", logic.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
//					this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(caraBayar), "", ""));
					this.context.put("selectCaraBayar", logic.selectCaraBayaranPajakan("socCaraBayar", Long.parseLong(caraBayar), "", ""));
					
					this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    			
	    		} else if (mode.equalsIgnoreCase("viewBayaran")){
	    			
	    			beanBayaran = new Vector();
	    			hashBayar = new Hashtable();
	    			logic.setMaklumatBayaran(idBayaran);
	    			beanBayaran = logic.getBeanMaklumatBayaran();
	    			hashBayar = (Hashtable)beanBayaran.get(0);
	    			String disabled = "class='disabled' disabled='disabled'";
	    			
	    			this.context.put("BeanBayaranList", beanBayaran);
	    			
	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		
	        		this.context.put("selectTujuan", logic.SelectTujuanBayar("socTujuan", Long.parseLong((String)hashBayar.get("tujuan")), disabled , ""));
					this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong((String)hashBayar.get("caraBayar")), disabled, ""));
	        		
	    		}
	    		//mode = update
	    		else if(mode.equalsIgnoreCase("updateBayaran")){
	    			
	    			this.context.put("selectTujuan", logic.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
					this.context.put("selectCaraBayar", HTML.selectCaraBayar("socCaraBayar", Long.parseLong(caraBayar), "", ""));
	    			
	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    		}    		
	    	} catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	 
	 private void saveBayaran(String idPermohonan, HttpSession session) throws Exception {
	    	Hashtable hashBayaran = new Hashtable();
	    	hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
			hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
			hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
			hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
			hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
			hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
			hashBayaran.put("tujuan", getParam("socTujuan"));
			hashBayaran.put("caraBayar", getParam("socCaraBayar"));
			hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));
			
			
			log.info("hash bayaran : " + hashBayaran);
			
			logic.saveBayaran(idPermohonan, hashBayaran, session);
		}
	 
	 private void saveUpdateBayaran(String idBayaran, HttpSession session) throws Exception {
		 	Hashtable hashBayaran = new Hashtable();
	    	hashBayaran.put("namaBank", getParam("txtNamaBank") == null ? "" : getParam("txtNamaBank"));
			hashBayaran.put("NoBayaran", getParam("txtNoBayaran") == null ? "" : getParam("txtNoBayaran"));
			hashBayaran.put("tarikhTerima", getParam("txdTarikhTerima") == null ? "" : getParam("txdTarikhTerima"));
			hashBayaran.put("jumlahBayaran", getParam("txtJumlahBayaran") == null ? "" : getParam("txtJumlahBayaran"));
			hashBayaran.put("NoResit", getParam("txtNoResit") == null ? "" : getParam("txtNoResit"));
			hashBayaran.put("tarikhResit", getParam("txdtarikhResit") == null ? "" : getParam("txdtarikhResit"));
			hashBayaran.put("tarikhHantarResit", getParam("txdtarikhHantarResit") == null ? "" : getParam("txdtarikhHantarResit"));
			hashBayaran.put("tujuan", getParam("socTujuan") == null ? "" : getParam("socTujuan"));
			hashBayaran.put("caraBayar", getParam("socCaraBayar") == null ? "" : getParam("socCaraBayar"));
			hashBayaran.put("tarikhCek", getParam("txdTarikhCek") == null ? "" : getParam("txdTarikhCek"));
			
			
			logic.saveUpdateBayaran(idBayaran, hashBayaran, session);
		} 
	  
		private IHtp getIHTP(){
			if(iHTP== null)
				iHTP = new HtpBean();
			return iHTP;
		}	 
	 


}
