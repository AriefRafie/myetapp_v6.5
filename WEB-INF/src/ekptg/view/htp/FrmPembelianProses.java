package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmGadaianSemakan1Data;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPembelianData;

public class FrmPembelianProses extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result = new String();
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmPembelianProses.class);
	
	//by Rosli 2009/12/17 
	private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");

	public String doTemplate2()throws Exception {
		
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
    	String readability = "";
    	String style1 = "";
		String style2 = "";
		
		this.context.put("Util", new lebah.util.Util());

		String selectedTab = new String();
    	selectedTab = getParam("tabId").toString();
    	if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    	}

   		this.context.put("selectedTab",selectedTab);
   		//System.out.println("CukaiProcess:::selectedTab::"+selectedTab);

	    Vector senaraiFail = null;
	    //String submitAjax = getParam("command");
	    //String submit = getParam("command1");
	    String submit = getParam("command");
	    this.context.put("command1", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
		Vector list = new Vector();
		mylog.info("command::"+getParam("command"));
		//mylog.info(this.className+":command1::"+getParam("command1"));
		mylog.info("pageMode::"+getParam("pagemode"));
		//mylog.info(this.className+":fail::"+getParam("fail"));
    	// by Rosli 2009/12/17
		String mode = getParam("mode");
   		mylog.info("doTemplate2 : Mode :: "+mode);

		if("cukaiperingkatbayar".equals(submit)){
		}else if("tambahBaucer".equals(submit)){
		}else if("cukaiBayaran".equals(submit)){
		}else if("tambahBayaran".equals(submit)){
		}else if("FailBaru".equals(submit)){
			template_name = "app/htp/frmPembelianSemakanN.jsp";
			String idPermohonan = "0";
			this.context.put("IdPermohonan", getParam("idPermohonan"));			
			this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPembelianSemakan"));
			this.context.put("semakclass", new FrmSemakan());

			if("view".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = getParam("idPermohonan");
			    setFailBaru(session,idPermohonan);
			    style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
			}else if("kemaskini".equals(mode)){
			    idPermohonan = getParam("idPermohonan");
			    setFailBaru(session,idPermohonan);
			    style1 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
			}else if("simpan".equals(mode)){
				readability = "readonly";
			    disability = "disabled";
			    idPermohonan = SimpanFailBaru(session);
				
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	    //System.out.println("FrmPajakanKecilSenaraiPermohonan:id::"+id);
	        	FrmSemakan frmSemak = new FrmSemakan();
	        	frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
	        	if(cbsemaks!=null){
	        		for (int i = 0; i < cbsemaks.length; i++) { 
	        			frmSemak = new FrmSemakan();
	        			//System.out.println("PembelianProcess:FailBaru::simpan::cbsemaks:::"+cbsemaks[i]);
	        			frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));           
	        		}
	        	}
				
				setFailBaru(session,idPermohonan);
				style2 = "none";
				ViewFailBaru(session,disability,readability,style1,style2);
				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
			}else if("pilihkementerian".equals(mode)){
				//readability = "readonly";
			    //disability = "disabled";
			    //idPermohonan = Integer.parseInt(getParam("idPermohonan").toString());
			    //setFailBaru(session,idPermohonan);
			    style2 = "none";
			    displaySelected(session,disability,readability,style1,style2);
			} else{
				style1 = " none";
				DataFailBaru(session, disability, readability, style1, style2);
			}
			//System.out.println("PembelianProcess::FailBaru");
			
		}else{ 
			template_name = "app/htp/frmPembelianSenaraiFail.jsp";	        
			String key_cari = getParam("NamaFail");
			String keyNo_cari = getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			ListFail(session, key_cari, keyNo_cari, idNegeri);
	    	list = FrmSenaraiFailPembelianData.getList();
	    	this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",idNegeri,disability));
		    this.context.put("Senaraifail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
			//System.out.println("PembelianProcess::SenaraiFail");
		   
		}
		return template_name;
	}
	
	//*** Senarai Fail Pembelian Controller
	@SuppressWarnings("unchecked")
	public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception {
		Vector v = FrmSenaraiFailPembelianData.getSenaraiFail(key_cari, keyNo_cari, idNegeri);
	}
	
	//By Rosli Created fail baru
	public void setFailBaru(HttpSession session, String idFail) throws Exception {
		FrmGadaianSemakan1Data.setSemak(idFail);
	}
	
	private void ViewFailBaru(HttpSession session, String disability, String readability, 
			String style1, String style2) throws Exception {
		Vector<?> list = new Vector();
		list.clear();
		try{
			list = FrmGadaianSemakan1Data.getSemak();
		    this.context.put("Semak", list);
		    Hashtable<?, ?> h = (Hashtable<?, ?>) list.get(0);	
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idNegeri").toString()), " disabled"));
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(h.get("idKementerian").toString()), "disabled","onChange=\"javascript:doChangeKementerian()\""));
//		    this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(h.get("idAgensi").toString()), disability));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan",Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			System.out.println("GadaianProcess::ListFailBaru::Exception = "+ex);
		}
	}

	private String SimpanFailBaru(HttpSession session) throws Exception{
		String idFail;
		if(getParam("idFail") == ""){
			//fail baru
			Hashtable h = new Hashtable();			
			h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
			h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
//			h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idFail = FrmGadaianSemakan1Data.simpan(h);
			result = "baru";
			return idFail;
			
		}else{
			//kemaskini fail
			Hashtable h = new Hashtable();
	//		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			h.put("idFail", Integer.parseInt(getParam("idFail")));
//			h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
//			h.put("idSuburusan", Integer.parseInt(getParam("socSuburusan")));
//			h.put("socAgensi", Integer.parseInt(getParam("socAgensi")));			
			h.put("Tajuk", getParam("txtTajuk"));
//			h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
//			h.put("NoFailLain", getParam("txtNoFailLain"));
//			h.put("TarikhBukaFail", getParam("txdTarikhBukaFail"));
			System.out.println("GadaianProcess::SimpanSemak:: h = "+h);
			idFail = FrmGadaianSemakan1Data.update(h);
			result = "kemaskini";
			return idFail;
		}
	}
	
	//*** Fail Baru Controller
	private void DataFailBaru(HttpSession session, String disability, String readability, 
			String style1, String style2) throws Exception{
		try{
			
			mylog.info("DataFailBaru::1");
			this.context.put("Semak", "");
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
		     //this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian"));
		   this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",0L, null," onChange=\" javascript:doChangeKementerian()\""));
	    	  //socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");

		     this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",""));
		   	//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(getParam("socKementerian").equals("")?"0L":getParam("socKementerian")),"",""));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			mylog.info("DataFailBaru::Exception = "+ex);
		}
	}
	
	//*** Fail Baru Controller
	private void displaySelected(HttpSession session, String disability, String readability, 
			String style1, String style2) throws Exception{
		try{
			
			mylog.info("displaySelected::1"+getParam("socNegeri"));
			mylog.info("displaySelected::1"+getParam("socKementerian"));
		    this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(getParam("socNegeri")),""));
		    
		    this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(getParam("socKementerian")), null," onChange=\" javascript:doChangeKementerian()\""));
	    	  //socKementerian = HTML.SelectKementerian("sockementerian", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeKementerian()\" ");

		    // this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",""));
		   	//this.context.put("selectAgensi",HTML.SelectAgensi("socAgensi",Long.parseLong(getParam("socKementerian").equals("")?"0L":getParam("socKementerian")),"",""));
		   	this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi",getParam("socKementerian"),Long.parseLong("1"),""));
		    this.context.put("selectSuburusan",HTML.SelectSuburusan("socSuburusan"));
		    this.context.put("datenow", formatter.format(now));
		    this.context.put("modeSoc", disability);
		    this.context.put("mode", readability);
		    this.context.put("Style1", style1);
		    this.context.put("Style2", style2);
		}catch(Exception ex){
			mylog.info("displaySelected::Exception = "+ex);
		}
	}

		
}
