/**
 * 
 */
package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPenswastaan2HeaderData;
import ekptg.model.htp.FrmPenswastaan2PindahMilikTanahData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.PerjanjianPembelian;
import ekptg.model.htp.entity.PerjanjianPindahMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPerjanjianPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PerjanjianBean;
import ekptg.model.htp.penswastaan.IPenswastaan;
import ekptg.model.htp.penswastaan.PenswastaanBean;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPenswastaan2PindahMilikTanahView extends AjaxBasedModule {

 	private IHTPFail iHTPFail = null;  
 	private IHtp iHTP = null; 
	private static final String PATH="app/htp/penswastaan/";
	private static final String PATHPEM="app/htp/pembelian/perjanjian/";

	private static final long serialVersionUID = 1L;
	private String selectedTab = "0";
	private static Logger myLog = Logger.getLogger(FrmPenswastaan2PindahMilikTanahView.class);
	FrmPenswastaan2HeaderData logicHeader = new FrmPenswastaan2HeaderData();
	FrmPenswastaan2PindahMilikTanahData logic = new FrmPenswastaan2PindahMilikTanahData();
	String disabled = "disabled='disabled' class='disabled'";
	private PerjanjianPindahMilik pindahMilik = null;
	private IPerjanjianPembelian iPerjanjianPembelian = null;
	private IPembelian iPembelian = null;
	private IPenswastaan iPenswastaan = null;
	private PerjanjianPembelian perjanjian = null;
	private HtpPermohonan htpPermohonan = null;
	private Permohonan permohonan = null;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPenswastaan = getParam("actionPenswastaan"); // our main submit
		if (actionPenswastaan.isEmpty()){
			actionPenswastaan = "papar";
		}
		String submit = getParam("command"); // for doAjax only
		String mode = getParam("mode");
		if (mode.isEmpty()){
			mode = "view";
		}
		String hitButton = getParam("hitButton");
		String idHakmilikurusan = getParam("idHakmilikurusan");

		// GET ID PARAM
		String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPihakKepentingan = getParam("idPihakKepentingan");
		

		// VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatTanah= null;
		Vector senaraiPemilikTanah = null;
		Vector senaraiTanahPindahMilik = null;
		
		 //vm = "app/htp/frmPenswastaan2PindahMilikTanah.jsp";
		 vm = PATH+"frmPenswastaanPindahMilikTanah.jsp";
		 
		//HITBUTTON
		if (postDB){
			if ("saveUpdatePemilik".equals(hitButton)){
				saveUpdatePemilik(idPihakKepentingan, session);

	        }
			if ("savePemilik".equals(hitButton)){
				savePemilik(idHakmilikurusan, session);
	        }
			if ("hapusPemilik".equals(hitButton)){
				logic.hapusPemilik(idPihakKepentingan);

	        }
    		selectedTab="1";

	    }
		
		//HEADER
        beanHeader = new Vector();
        //logicHeader.setMaklumatPermohonan(idFailSession);
        //beanHeader = logicHeader.getBeanMaklumatPermohonan();
        beanHeader = getIHTPFail().getMaklumatPermohonans(idFailSession);
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			//Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			Hashtable hashHeader = (Hashtable) beanHeader.get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			
			/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
			}
			
			//senarai pemilik tanah
			senaraiPemilikTanah = new Vector();
//			logic.setMaklumatSenaraiPemilikTanah(idHakmilikurusan);
			//logic.setMaklumatSenaraiPemilikTanah();
			logic.setMaklumatSenaraiPemilikPermohonan(idPermohonan);
			senaraiPemilikTanah = logic.getSenaraiMaklumatPemilik();
			this.context.put("SenaraiPemilik", senaraiPemilikTanah);
						
			//senarai tanah pindah milik
			senaraiTanahPindahMilik = new Vector();
			logic.setMaklumatSenaraiPindahMilikTanah(idPermohonan);
			senaraiTanahPindahMilik = logic.getSenaraiMaklumatTanahPindahMilik();
			this.context.put("SenaraiTPMilik", senaraiTanahPindahMilik);
			//this.context.put("pindahMilik", senaraiTanahPindahMilik);
		
			if ("papar".equals(actionPenswastaan)){	
        		PindahMilikView(mode, idPihakKepentingan, idHakmilikurusan); 			
    		}
		}
		
		if(submit.equals("maklumatview")){
			myLog.info("==maklumatview==");
			selectedTab="1";
			
		}else if(submit.equals("pindahMilik")){
			Vector pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(idPermohonan);
			Vector<HakmilikUrusan> hakmiliks = getIPenswastaan().getHakmilikList(idPermohonan);
			myLog.info("==pindahMilik==");
			String pindahMode ="";
			mode =" readonly class=\"disabled\"";
			if(pindahMilik.isEmpty()){
				pindahMode = "new";
				mode ="";
				generatePerjanjianPindahMilik(hakmiliks,idPermohonan);
				pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(idPermohonan);
			}
			context.put("mode", mode);
			context.put("pindahMode", pindahMode);
			context.put("pindahMilik", pindahMilik);
			selectedTab="0";
				
		}else if(submit.equals("viewPindahMilik")){
			myLog.info("==viewPindahMilik==");
			getPermohonanInfo();
			pindahMilik = getIPerjanjianPembelian().getPindahMilik(getParam("idPindahMilik"));
			context.put("pindahMilik", pindahMilik);
			context.put("mode", "");
			context.put("pindahMode", "update");
			//selectedTab="3";
			vm = PATHPEM+"updatePindahMilik.jsp";
			
		}else if(submit.equals("updatePindahMilik")){
			myLog.info("==updatePindahMilik==");
			getPermohonanInfo();
			getPindahMilikValues();
			getIPerjanjianPembelian().updatePindahMilik(pindahMilik);
			Vector pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(getParam("idPermohonan"));
			context.put("pindahMilik", pindahMilik);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("pindahMode", "");
			//vm = PATH+"perjanjian.jsp";
		
		}else if(submit.equals("senaraisemakpmilik")){
			myLog.info("==senaraisemakpmilik==");
			getPermohonanInfo();
			//selectedTab="2";
			vm = PATHPEM+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " disabled");
			context.put("semakMode", " ");
			context.put("tajukSemakan", getParam("tajuk"));
			
		}else if(submit.equals("senaraisemakpmilikkemaskini")){
			myLog.info("==senaraisemakpmilikkemaskini==");
			getPermohonanInfo();
			//selectedTab="2";
			vm = PATHPEM+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " ");
			context.put("semakMode", "update");
		}
		else if(submit.equals("senaraisemakpmiliksimpan")){
			myLog.info("==senaraisemakpmiliksimpan==");
			getPermohonanInfo();
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			//selectedTab="2";
			vm = PATHPEM+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " disabled");
			context.put("semakMode", "");
		
		}else if (submit.equals("doChangeNegeri")){
			selectedTab="1";

		}else{
//			ekptg.helpers.Utils hUtils = new ekptg.helpers.Utils();
//			String tab = hUtils.getTabID("Pajakan","adminhtp");
//			log.info("==senaraisemakview=="+tab+"---"+session.getAttribute("_portal_role"));
//			log.info("==senaraisemakview=="+tab+"---jsp "+session.getAttribute("portal_role"));		
			Vector pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(idPermohonan);
			Vector<HakmilikUrusan> hakmiliks = getIPenswastaan().getHakmilikList(idPermohonan);
			myLog.info("== ==");
			String pindahMode ="";
			mode =" readonly class=\"disabled\"";
			if(pindahMilik.isEmpty()){
				pindahMode = "new";
				mode ="";
				generatePerjanjianPindahMilik(hakmiliks,idPermohonan);
				pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(idPermohonan);
			}
			context.put("mode", mode);
			context.put("pindahMode", pindahMode);
			context.put("pindahMilik", pindahMilik);
			selectedTab="0";

		}		
		
		//SET DEFAULT PARAM
        this.context.put("actionPenswastaan", actionPenswastaan);
		this.context.put("mode", mode);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idHakmilikurusan", idHakmilikurusan);
        this.context.put("idPihakKepentingan", idPihakKepentingan);
    
		context.put("selectedTab", selectedTab);
		return vm;
	
	}
	
	public void PindahMilikView(String mode, String idPihakKepentingan, String idHakmilikurusan)throws Exception{
		try{
			//GET DROPDOWN PARAM
	        String idNegeri = getParam("socNegeri");
			if (idNegeri == null || idNegeri.trim().length() == 0){
				idNegeri = "99999";
			}
			String idDaerah = getParam("socDaerah");
			if (idDaerah == null || idDaerah.trim().length() == 0){
				idDaerah = "99999";
			}
					
			Vector beanPemilik = null;
			Hashtable hashPemilik = null;
			Vector noHakmilikv = null;		
			
    		if (mode.equalsIgnoreCase("newPemilik")){    			
    			beanPemilik = new Vector();
    			hashPemilik = new Hashtable();
    			logic.setNoWartaNohakmilik(idHakmilikurusan);
    			
    			if ("doChangeNegeri".equals(getParam("command"))){
					hashPemilik.put("noPerserahan", getParam("txtNoPerserahan") == null ? "" : getParam("txtNoPerserahan") );
					hashPemilik.put("nama", getParam("txtNama") == null ? "" : getParam("txtNama")  );
					hashPemilik.put("tarikhDaftar", getParam("txdTarikhDaftar") == null ? "" : getParam("txdTarikhDaftar"));
					hashPemilik.put("alamat1",getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1") );
					hashPemilik.put("alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2") );
					hashPemilik.put("alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3") );
					hashPemilik.put("poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod") );
					hashPemilik.put("tel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon") );
					hashPemilik.put("fax", getParam("txtFax") == null ? "" : getParam("txtFax") );
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"", "onChange=\"doChangeNegeri()\""));
	        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", ""));					
					
    			} else {
    				hashPemilik.put("noPerserahan", "");
					hashPemilik.put("nama", "");
					hashPemilik.put("tarikhDaftar", "");
					hashPemilik.put("alamat1","");
					hashPemilik.put("alamat2", "");
					hashPemilik.put("alamat3", "");
					hashPemilik.put("poskod", "");
					hashPemilik.put("tel", "");
					hashPemilik.put("fax", "");
					
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong("0"),"", "onChange=\"doChangeNegeri()\""));
	        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong("0"), "", ""));
    			}				
				
				beanPemilik.addElement(hashPemilik);
				
				noHakmilikv = logic.getNoWartaNohakmilik();
				Hashtable hash = new Hashtable();
				hash = (Hashtable)noHakmilikv.get(0);
				String snoHakmilik = hash.get("noHakmilik").toString();
				String snoWarta = hash.get("noWarta").toString();
				
				
        		if(!snoHakmilik.equalsIgnoreCase("") && !snoHakmilik.equalsIgnoreCase(null) ){
        			this.context.put("selectNohakmilik", snoHakmilik);
        		}
        		else{
        			this.context.put("selectNohakmilik", snoWarta);
        		}
    			
        		this.context.put("BeanPemilik", beanPemilik);
//        		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"", "onChange=\"doChangeNegeri()\""));
//        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", ""));
        		
        		this.context.put("readOnly", "");
        		this.context.put("classDis", "");
        		
    		}    		
    		//mode = view
    		else if (mode.equalsIgnoreCase("viewPemilik")){
    			
    			beanPemilik = new Vector();
    			hashPemilik = new Hashtable();
    			logic.setMaklumatPemilikTanah(idPihakKepentingan);
    			beanPemilik = logic.getMaklumatPemilik();
    			hashPemilik = (Hashtable)beanPemilik.get(0);    			
    			this.context.put("BeanPemilik", beanPemilik);   			
    			String snoHakmilik = hashPemilik.get("noHakmilik").toString();
				String snoWarta = hashPemilik.get("noWarta").toString();
				
        		if(!snoHakmilik.equalsIgnoreCase("") && !snoHakmilik.equalsIgnoreCase(null) ){
        			this.context.put("selectNohakmilik", snoHakmilik);
        		
        		}else{
        			this.context.put("selectNohakmilik", snoWarta);
        		}
    			
    			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(hashPemilik.get("idNegeri").toString()),disabled, ""));
        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(hashPemilik.get("idNegeri").toString(), "socDaerah", Long.parseLong(hashPemilik.get("idDaerah").toString()), disabled, ""));   			
        		this.context.put("readOnly", "readOnly");
        		this.context.put("classDis", "disabled");
        		
    		}    		
    		//mode = update
    		else if(mode.equalsIgnoreCase("updatePemilik")){
    			
    			beanPemilik = new Vector();
    			hashPemilik = new Hashtable();

				hashPemilik.put("noPerserahan", getParam("txtNoPerserahan") == null ? "" : getParam("txtNoPerserahan") );
				hashPemilik.put("nama", getParam("txtNama") == null ? "" : getParam("txtNama")  );
				hashPemilik.put("tarikhDaftar", getParam("txdTarikhDaftar") == null ? "" : getParam("txdTarikhDaftar"));
				hashPemilik.put("alamat1",getParam("txtAlamat1") == null ? "" : getParam("txtAlamat1") );
				hashPemilik.put("alamat2", getParam("txtAlamat2") == null ? "" : getParam("txtAlamat2") );
				hashPemilik.put("alamat3", getParam("txtAlamat3") == null ? "" : getParam("txtAlamat3") );
				hashPemilik.put("poskod", getParam("txtPoskod") == null ? "" : getParam("txtPoskod") );
				hashPemilik.put("tel", getParam("txtNoTelefon") == null ? "" : getParam("txtNoTelefon") );
				hashPemilik.put("fax", getParam("txtFax") == null ? "" : getParam("txtFax") );
				beanPemilik.addElement(hashPemilik);

    			this.context.put("BeanPemilik", beanPemilik);
        		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri),"", "onChange=\"doChangeNegeri()\""));
        		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", ""));    			
    			this.context.put("readOnly", "");
        		this.context.put("classDis", "");
    		}    		
			
		}catch(Exception e){
			myLog.error("Error : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void savePemilik(String idHakmilikurusan, HttpSession session)throws Exception{
		try{
			
			Hashtable hashPemilik = new Hashtable();
			hashPemilik.put("noPerserahan", getParam("txtNoPerserahan"));
			hashPemilik.put("nama", getParam("txtNama"));
			hashPemilik.put("tarikhDaftar", getParam("txdTarikhDaftar"));
			hashPemilik.put("alamat1",getParam("txtAlamat1"));
			hashPemilik.put("alamat2", getParam("txtAlamat2"));
			hashPemilik.put("alamat3", getParam("txtAlamat3"));
			hashPemilik.put("poskod", getParam("txtPoskod"));
			hashPemilik.put("tel", getParam("txtNoTelefon"));
			hashPemilik.put("fax", getParam("txtFax"));
			hashPemilik.put("negeri", getParam("socNegeri"));
			hashPemilik.put("daerah", getParam("socDaerah"));
			
			myLog.info("fir savePemilik ");
			
			logic.savePemilik(idHakmilikurusan, hashPemilik, session);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveUpdatePemilik(String idPihakKepentingan, HttpSession session)throws Exception{
		try{
			
			Hashtable hashPemilik = new Hashtable();
			hashPemilik.put("noPerserahan", getParam("txtNoPerserahan"));
			hashPemilik.put("nama", getParam("txtNama"));
			hashPemilik.put("tarikhDaftar", getParam("txdTarikhDaftar"));
			hashPemilik.put("alamat1",getParam("txtAlamat1"));
			hashPemilik.put("alamat2", getParam("txtAlamat2"));
			hashPemilik.put("alamat3", getParam("txtAlamat3"));
			hashPemilik.put("poskod", getParam("txtPoskod"));
			hashPemilik.put("tel", getParam("txtNoTelefon"));
			hashPemilik.put("fax", getParam("txtFax"));
			hashPemilik.put("negeri", getParam("socNegeri"));
			hashPemilik.put("daerah", getParam("socDaerah"));
			
			myLog.info("fir saveupdatePemilik ");
			
			logic.updatePemilik(idPihakKepentingan, hashPemilik, session);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 

	private IPenswastaan getIPenswastaan(){
		if (iPenswastaan==null){
			iPenswastaan=new PenswastaanBean();
		}
		return iPenswastaan;
	} 
	private IPerjanjianPembelian getIPerjanjianPembelian(){
		if(iPerjanjianPembelian == null)
			iPerjanjianPembelian = new PerjanjianBean();
		return iPerjanjianPembelian;
	}
	
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	
	private void generatePerjanjianPindahMilik(Vector<HakmilikUrusan> hakmiliks, String idPermohonan) {
		if(!hakmiliks.isEmpty()){
			for(HakmilikUrusan urusan : hakmiliks){
				pindahMilik = new PerjanjianPindahMilik();
				pindahMilik.setIdPermohonan(idPermohonan);
				pindahMilik.setHakmilikUrusan(urusan);
				getIPerjanjianPembelian().simpanPindahMilik(pindahMilik);
			}
		}
		
	}
	
	private void getPindahMilikValues(){
		String idPerjanjianPindahMilik = getParam("idPindahMilik");
		String tarikhTerima = getParam("tarikhTerimaKJP");
		String tarikhHantar = getParam("tarikhHantarKJP");
		String tarikhTandatangan = getParam("tarikhTandatanganPTP");
		String idPermohonan = getParam("idPermohonan");
		String idDokumenPerjanjian = getParam("idDokumenPerjanjian");
		System.out.println("getPindahMilikValues==" +idPermohonan);
		pindahMilik = new PerjanjianPindahMilik();
		permohonan = new Permohonan();
		
		permohonan.setIdPermohonan(idPermohonan);
		pindahMilik.setIdPindahMilik(idPerjanjianPindahMilik);
		pindahMilik.setIdPermohonan(idPermohonan);
		pindahMilik.setTarikhHantar(tarikhHantar);
		pindahMilik.setTarikhTerima(tarikhTerima);
		pindahMilik.setTarikhTandatangan(tarikhTandatangan);
		pindahMilik.setIdDokumenPerjanjian(idDokumenPerjanjian);
		context.put("pindahMilik", pindahMilik);
		
	}
	
	private void getSemakanPindahMilik()throws Exception{
		context.put("semakclass", new FrmSemakan());
		Vector semakList = FrmSemakan.getSenaraiSemakan("frmBeliPindahMilik");
		context.put("semakDraf", semakList);
	}


	private void simpanSenaraiSemakFail(String idPermohonan)throws Exception{
		String[] cbsemaks = this.request.getParameterValues("cbsemaks");
		FrmSemakan frmSemak = new FrmSemakan();
		frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				frmSemak = new FrmSemakan();
				frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
			}
		}	
		
	}
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
}
