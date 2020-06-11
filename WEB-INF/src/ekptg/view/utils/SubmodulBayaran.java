package ekptg.view.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmPajakanBayaranPajakanCukaiTanahData;
import ekptg.model.htp.FrmTukaranStatusData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.IHTPStatus;


public class SubmodulBayaran extends VTemplate{
	private static final long serialVersionUID = -3597940389060050945L;
	static Logger myLog = Logger.getLogger(ekptg.view.utils.SubmodulBayaran.class);
	FrmTukaranStatusData model = new FrmTukaranStatusData();
	String userId="";  
	private IHTPStatus iStatus = null;
	FrmPajakanBayaranPajakanCukaiTanahData bayarData = new FrmPajakanBayaranPajakanCukaiTanahData();

	
	public Template doTemplate() throws Exception{
		HttpSession session = request.getSession();
		String doPost = (String) session.getAttribute("doPost"); 	
    	String id_permohonan = "";
		String id_fail = "";
		String id_status = "";
		String id_suburusanstatusfail = "";
		String keterangan_status = "";
		String noFail = "";
    	String vm = "";
    	Vector<Hashtable<String,String>> vStatus = null; 	
    	Hashtable<String,String> hash = null;
   		userId=(String)session.getAttribute("_ekptg_user_id");	
   		context.put("carianrekod","");  		
    	String submit = getParam("command");
    	//myLog.info("[submit] :: " +submit); 
		 String tujuan = getParam("socTujuan").equals("")?"0":getParam("socTujuan");
		 String caraBayar = getParam("socCaraBayar").equals("")?"0":getParam("socCaraBayar");
		 String mode = getParam("mode");

    	
    	if ("Cari".equals(submit)){
			String id_suburusanstatus = "";
    		noFail = getParam("txtNoFail");    		
        	//myLog.info("[Cari] :: " +getParam("txtNoFail"));
    		hash = (Hashtable<String,String>)FrmTukaranStatusData.getListV2(noFail,userId);
    		
    		if(hash.isEmpty() == false){
    			//Hashtable x = (Hashtable) list.get(0);
    			Hashtable<String,String> x = hash;
    			id_permohonan = x.get("id_permohonan").toString();
    			id_fail = x.get("id_fail").toString();
    			id_status = x.get("id_status").toString();	
    			id_suburusanstatus = x.get("id_suburusanstatus").toString();	
    			id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
    			keterangan_status = x.get("tajuk").toString();
    	   		context.remove("listSemak");
        		context.remove("listSemak_size");
     
    	    	vStatus = (Vector<Hashtable<String,String>>)FrmUtilData.getSenaraiStatusMengikutSubUrusan(id_status);
        		context.put("listSemak",vStatus);
        		//context.put("listSemak_size",vStatus.size());
        		context.put("listSemak_size",false);			
    			context.put("carianrekod","");
    			context.put("url",x.get("url"));
   			
    		}else{
    			if(noFail!=""){
    				context.put("carianrekod","tiada");
    			}else{
    				context.put("listSemak",vStatus);
    				context.put("listSemak_size",true);
    				context.put("carianrekod","");
 
    			}
    		}
    		mode = "newBayaran";
    		
    		context.put("id_permohonan", id_permohonan);
			context.put("id_fail", id_fail);
			context.put("id_status", id_status);
			context.put("keterangan_status", keterangan_status);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
			context.put("id_suburusanstatus", id_suburusanstatus);
    		context.put("nofail", noFail.trim());   		
    		
    		vm = "app/utils/bayaran/index.jsp";
    		
    	}else if ("tukarstatus".equals(submit)){
    		noFail = getParam("txtNoFail");	
    		id_permohonan = getParam("id_permohonan");
    		simpanKemaskiniBayaran(id_permohonan, "-1");

//    		if (doPost.equals("true")) {
//    			tukarstatus(session); 
//    			setStatusPermohonan();
//    			
//    		}
	   		hash = (Hashtable<String,String>)FrmTukaranStatusData.getListV2(noFail,userId);
    		
    		if(hash.isEmpty() == false){
    			Hashtable<String,String> x = hash;
    			id_permohonan = x.get("id_permohonan").toString();
    			id_fail = x.get("id_fail").toString();
    			id_status = x.get("id_status").toString();	
    			id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
    			keterangan_status = x.get("keterangan").toString();
    	   		context.remove("listSemak");
        		context.remove("listSemak_size");
     
    	    	vStatus = (Vector<Hashtable<String,String>>)FrmUtilData.getSenaraiStatusMengikutSubUrusan(id_status);
        		context.put("listSemak",vStatus);
        		context.put("listSemak_size",false);			
    			context.put("carianrekod","");
    			context.put("url",x.get("url"));
    			
    		}
    		mode = "viewBayaran";

    		
     		context.put("nofail", noFail.trim());
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
    		context.put("id_keputusanpermohonan", "");
    		context.put("id_perbicaraan", "");
    		context.put("id_permohonan", "");    		
    		vm = "app/utils/bayaran/index.jsp";
    
    	}else{
    		mode = "newBayaran";

    		context.remove("listSemak");
    		context.remove("listSemak_size");
    		//context.remove("nofail");
    		context.put("nofail", "");
    		context.remove("id_permohonan");
			context.remove("id_fail");
			context.remove("id_status");
			context.remove("id_suburusanstatusfail");
			//context.remove("keterangan_status");
    		context.put("keterangan_status", "");
			context.remove("seksyen");
			context.remove("id_perintah");
    		context.remove("id_keputusanpermohonan");
    		context.remove("id_perbicaraan");
    		context.remove("id_permohonan");
    		context.remove("listSemak");  		
   		   	vm = "app/utils/bayaran/index.jsp";
   		   	
    	}//close else
    	FrmPajakanBayaranPajakanCukaiTanahData bayarData = new FrmPajakanBayaranPajakanCukaiTanahData();
		this.context.put("selectTujuan", FrmPajakanBayaranPajakanCukaiTanahData.SelectTujuanBayar("socTujuan", Long.parseLong(tujuan), "" , ""));
		this.context.put("selectCaraBayar", bayarData.selectCaraBayaranPajakan("socCaraBayar", Long.parseLong(caraBayar), "", ""));

    	this.context.put("mode", mode);

    	String strdate = "";
    	strdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
    	this.context.put("txdTarikh",strdate);
    	Template template = engine.getTemplate(vm);
        return template;
    	
	}//close dotemplate
	
	//tukarstatus permohonan
	private void setStatusPermohonan() throws Exception{
		String idPermohonan = getParam("id_permohonan");
		String idStatus = getParam("id_status");
		String langkah = getParam("level");
		//FrmUtilData data = new FrmUtilData();
		//data.kemaskiniStatusPermohonan(id_permohonan,id_status);
		getStatus().kemaskiniStatusPermohonan(idPermohonan, idStatus, langkah, userId);
					
	}
	
	//tukarstatus Baru
	//private void tukarstatusV1(HttpSession session) throws Exception{
	private void tukarstatus(HttpSession session) throws Exception{
		String id_permohonan = getParam("id_permohonan");
		String id_Suburusanstatusfail = getParam("id_suburusanstatusfail");
		String id_fail = getParam("id_fail");
		String idSuburusanstatus = getParam("id_keputusanpermohonan");
		//String id_status = getParam("id_status");
		String txtUlasan = getParam("txtUlasan");
		String txdTarikh = getParam("txdTarikh");
    	//mylog.info("[id_permohonan] :: " +getParam("id_permohonan"));
    	//mylog.info("[id_fail] :: " +getParam("id_fail"));
    	//mylog.info("[id_keputusanpermohonan] :: " +getParam("id_keputusanpermohonan"));
		Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
		s.setIdPermohonan(Long.parseLong(id_permohonan));
		s.setIdFail(Long.parseLong(id_fail));
		s.setAktif("0");
		FrmUtilData.kemaskiniStatusPermohonanAktif(s);
		
		//Insert new rekod
		// id_permohonan as up
		s.setIdSuburusanstatus(Long.parseLong(idSuburusanstatus));
		s.setAktif("1");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = df.parse(txdTarikh);           
		s.setTarikhMasuk(today);
		s.setUrl(txtUlasan);
		s.setIdMasuk(Long.parseLong(userId));
		if(FrmUtilData.getIdSuburusanStatusFail(id_fail,id_permohonan,idSuburusanstatus)){
			s.setIdSuburusanstatusfail(Long.parseLong(id_Suburusanstatusfail));
			s.setIdKemaskini(Long.parseLong(userId));
			FrmUtilData.kemaskiniSubUrusanStatusFail(s);
			
		}else
			FrmUtilData.simpanStatusPermohonan(s);
		
	}
	
	//tukarstatus
//	private void tukarstatus(HttpSession session) throws Exception{		   
//		Hashtable h = new Hashtable();	
//		String id_perintah = getParam("id_perintah");
//		String id_permohonan = getParam("id_permohonan");
//		String id_perbicaraan = getParam("id_perbicaraan");
//		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");
//		String id_fail = getParam("id_fail");
//		String id_suburusanstatusfail = getParam("id_suburusanstatusfail");	
//		String level = getParam("level");	
//		int seksyen = 0;
//		seksyen = getParamAsInteger("seksyen");
//				
//		h.put("id_perintah", id_perintah);
//		h.put("id_permohonan", id_permohonan);
//		h.put("id_keputusanpermohonan", id_keputusanpermohonan);
//		h.put("id_perbicaraan", id_perbicaraan);
//		h.put("id_fail", id_fail);
//		h.put("id_suburusanstatusfail", id_suburusanstatusfail);		
//		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
//		
//	  // LEVEL   
//	  /*  1 = PERMOHONAN DITERUSKAN 
//	      2 = NOTIS PERBICARAAN 
//	      3 = SELESAI PERBICARAAN 
//	      4 = PERMOHONAN SELESAI 	*/
//		
//		String lvl = "";
//		String status = "";
//		String suburusanstatus = "";
//		if(level.equals("1")){
//			lvl = "1";
//			status = "53";
//			if(seksyen==8){
//				suburusanstatus = "406";
//			}else{
//				suburusanstatus = "435";
//			}
//			
//		}else if(level.equals("2")){
//			lvl = "2";
//			status = "18";
//			if(seksyen==8){
//				suburusanstatus = "354";
//			}else{
//				suburusanstatus = "341";
//			}
//			
//		}else if(level.equals("3")){
//			lvl = "3";
//			status = "41";
//			if(seksyen==8){
//				suburusanstatus = "373";
//			}else{
//				suburusanstatus = "410";
//			}
//			
//		}else if(level.equals("4")){
//			lvl = "4";
//			status = "21";
//			if(seksyen==8){
//				suburusanstatus = "358";
//			}else{
//				suburusanstatus = "355";
//			}
//			
//		}else{
//			lvl = "4";
//			status = "21";
//			if(seksyen==8){
//				suburusanstatus = "358";
//			}else{
//				suburusanstatus = "355";
//			}
//		}
//
//		h.put("id_suburusanstatus", suburusanstatus);
//		h.put("id_statusKE", status);
//		h.put("level", lvl);
//		
//		//model.tukarstatus(h);
//		model.tukarKE(h);
//	    model.updateANDinsertStatusFail(h);
//	    
//	  }//close addnotis tambah
	
    private void simpanKemaskiniBayaran(String idPermohonan,String idBayaran) throws Exception {
	 	Hashtable <String,String> hashBayaran = new Hashtable <String,String>();
    	hashBayaran.put("idBayaran", idBayaran);
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
		hashBayaran.put("userId", userId);	
		bayarData.simpanKemaskiniBayaran(idPermohonan, hashBayaran);
		
	}
	 
	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
				
	}
	
}//close class
