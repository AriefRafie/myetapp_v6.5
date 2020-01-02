package ekptg.view.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmMMKSek8Data;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8JabatanTeknikalData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.Seksyen4MMK;
import ekptg.model.ppt.SementaraMMK;
import ekptg.view.ppt.email.EmailTester;

public class FrmSementaraMMK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSementaraMMK.class);
	
	// MODEL SEKSYEN 8
	FrmSek8LaporanAwalTanahData modelLaporan = new FrmSek8LaporanAwalTanahData();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8JabatanTeknikalData modelJabatan = new FrmSek8JabatanTeknikalData();
	FrmMMKSek8Data model = new FrmMMKSek8Data();
	PPTHeader header = new PPTHeader();
	FrmPembatalanInternalData modelPembatalan = new FrmPembatalanInternalData();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	
	// MODEL SEMENTARA
	SementaraMMK modelSementara = new SementaraMMK();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
    	//get user login detail
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	
    	//get nama pengarah dan id pengarah
    	nameAndId(userIdNeg);
    	String id_pengarah = nameAndId(userIdNeg);

		//command for paging
    	String action = getParam("action");
    	myLogger.info("action :: "+action);
    	
    	String vm = "";

    	Vector listPageDepan = new Vector();
    	Vector dataMMK = new Vector();
    	Vector senarai_submmk = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	
    	listMaklumatTanah.clear();
    	senarai_submmk.clear();
    	dataMMK.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSementaraMMKSenarai.jsp";
    	String mainscreen = "app/ppt/frmSementaraMMK.jsp";
    	
    	//default list
    	listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	//tabbed
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();	
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
    	
		//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "8");
    	}
    	
		//header yati comment
    	/*String nama_kementerian = "";
    	String tarikh_permohonan = "";
    	String tujuan = "";
		String id_status = "";
		String id_fail = "";
		String negeriMMK = "";
		String flagSegera = "";
		String no_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");
			flagSegera = dh.get("flag_segera").toString();
			no_fail = (String)dh.get("no_fail");	
			tujuan = (String)dh.get("tujuan");
			tarikh_permohonan = (String)dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		*/
    	
    	//header
    	String tujuanInit = "";
    	String nama_kementerian = "";
    	String tarikh_permohonan = "";
    	String tujuan = "";
		String id_status = "";
		String id_fail = "";
		String negeriMMK = "";
		String flagSegera = "";
		String no_fail = "";
		String projek_daerah = "";
		String no_rujukan_ptg = "";
		String projek_negeri = "";
		String no_rujukan_surat = "";
		String tarikh_surat = "";
		String id_projekNegeri = "";
		String agensiKem = "";
		String alamatKem1 = "";
		String alamatKem2 = "";
		String alamatKem3 = "";
		String poskodKem = "";
		String negeriKem = "";
		int totalHakmilik = 0;
    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");
			flagSegera = dh.get("flag_segera").toString();
			no_fail = (String)dh.get("no_fail");
			projek_daerah = (String)dh.get("projek_daerah");
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");
			tujuan = (String)dh.get("tujuan");
			tarikh_permohonan = (String)dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			projek_negeri = (String)dh.get("projek_negeri");
			totalHakmilik = (Integer)dh.get("totalHakmilik");
			no_rujukan_surat = (String)dh.get("no_rujukan_surat");
			tarikh_surat = (String)dh.get("tarikh_surat");
			tujuanInit = (String)dh.get("tujuanInit");
			id_projekNegeri = (String)dh.get("id_projekNegeri");
			agensiKem = (String)dh.get("agensiKem");
			alamatKem1 = (String)dh.get("alamat1");
			alamatKem2 = (String)dh.get("alamat2");
			alamatKem3 = (String)dh.get("alamat3");
			poskodKem = (String)dh.get("poskod");
			negeriKem = (String)dh.get("negeriKem");
			
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		
		context.put("agensiKem",agensiKem);
		context.put("nama_kementerian",nama_kementerian);
		context.put("alamatKem1",alamatKem1);
		context.put("alamatKem2",alamatKem2);
		context.put("alamatKem3",alamatKem3);
		context.put("poskodKem",poskodKem);
		context.put("negeriKem",negeriKem);
		context.put("projek_negeri",projek_negeri);
		
		context.put("tarikh_surat",tarikh_surat);
		context.put("no_rujukan_surat",no_rujukan_surat);
		context.put("MMKtotalHakmilik",totalHakmilik);
		context.put("tujuanPermohonan",tujuanInit);
		context.put("flagSegera",flagSegera);
		context.put("projek_daerah",projek_daerah);
		context.put("no_rujukan_ptg",no_rujukan_ptg);
		context.put("page_portal_role", null);
		context.put("flagSegera",flagSegera);
		context.put("id_fail",id_fail);
		System.out.println("NAMA PROJEK :"+tujuan);
		
		//get tarikh hantar
		model.setDataMMK(idpermohonan);
    	dataMMK = model.getDataMMK();
    	String tarikh_hantar = "";
    	if(dataMMK.size()!=0){
    		Hashtable dm = (Hashtable)dataMMK.get(0);
    		tarikh_hantar = (String)dm.get("tarikh_hantar");
    	}
    	
    	//get total hakmilik
    	String nama2Mukim = "";
    	String nama2MukimInit = "";
		modelUPT.setListMaklumatTanah(idpermohonan,"","");
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		if(listMaklumatTanah.size()!=0){
 			Hashtable lmt = (Hashtable)listMaklumatTanah.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 			nama2MukimInit = (String)lmt.get("nama2MukimInit");	
 		}
 		
 		context.put("nama2Mukim",nama2Mukim);
 		context.put("nama2MukimInit",nama2MukimInit);
 		
//    	//user login id
//    	String id_user = (String) session.getAttribute("_ekptg_user_id");
//    	
//       	//get nama pengarah dan id negeri user
//    	nameAndId(id_user);
//    	String userIdNeg = nameAndId(id_user);
    	
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("showSemak","");
		context.put("showKeputusan","");
		context.put("modeSemak","");
		context.put("isEditSemak","");
		context.put("modeKeputusan","");
		context.put("isEditKeputusan","");
		
		//get jumlah keluasan ambil
		getTotalLuasAmbil(idpermohonan);
				
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	
    	if("viewMMK".equals(submit)){
    		
    		//new form
    		if(id_status.equals("22") || id_status.equals("147")){
    			System.out.println("masuk sini");
    			//sub mmk
        		senarai_submmk = modelPembatalan.senarai_submmk(getParam("id_mmk"));
    			context.put("senarai_submmk", senarai_submmk);
    			
    			//form validation
    			context.put("mode","new");
  
    			String submit2 = getParam("command2");
    	    	myLogger.info("submit[2] : " + submit2);
    	    	
    	    	if("simpanPenyediaan".equals(submit2)){
    	    		
    	    		if (doPost.equals("true")) { 	    	
            			//simpan data penyediaan
    	    			simpanPenyediaan(session);
    	    			updateStatusPenyediaan(session);
            		}
    	    		
    	    		//form validation
    	    		context.put("mode","view");
    	    		context.put("isEdit","no");
    	    		
    	    		//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_status = dataHeaderStatus(session,idpermohonan);
    	    		
    	    		//data mmk
    	    		dataMMK(session,idpermohonan);
    	    		
    	    		
    	    	}//close simpanPenyediaan
    		
    	    
    	    //view form (kecuali status maklumat jabatan teknikal)
    		}else{
    			
    			//form validation
    			context.put("mode","view");
    			context.put("isEdit","no");
    			
    			//data mmk
	    		dataMMK(session,idpermohonan);
	    		
	    		String submit2 = getParam("command2");
    	    	myLogger.info("submit[2] : " + submit2);
	    		
    	    	if("kemaskiniPenyediaan".equals(submit2)){
    	    		
    	    		//form validation
        			context.put("mode","view");
        			context.put("isEdit","yes");
    	    		
        			//data mmk
    	    		dataMMK(session,idpermohonan);
    	    		
        			String submit3 = getParam("command3");
        	    	myLogger.info("submit[3] : " + submit3);
    	    		
        	    	if("updatePenyediaan".equals(submit3)){
        	    		
        	    		System.out.println("masukkk here");
        	    		updatePenyediaan(session);
        	    		
        	    		//form validation
            			context.put("mode","view");
            			context.put("isEdit","no");
        	    		
            			//data mmk
        	    		dataMMK(session,idpermohonan);
        	    		
        	    	}//close updatePenyediaan
        			
    	    	}//close kemaskiniPenyediaan
	    		
    	    	
    	    	//** TAB 2 **//
	    		//new form for tab 2
	    		if(id_status.equals("132")){
	    			
	    			//form validation
	    			context.put("modeSemak","new");
	    			
	    			if("hantarPengesahan".equals(submit2)){
	    				
	    				if (doPost.equals("true")) {
	    					hantarPengesahan(session);
	    					sendEmail(id_pengarah,no_fail,tujuan,tarikh_permohonan,nama_kementerian);  
	    				}	
	    				
	    				//data mmk
	    	    		dataMMK(session,idpermohonan);
	    	    		
	    			}//close hantarPengesahan
	    			
	    			else if("updateSemakan".equals(submit2)){
	    	    		
	    	    		//form validation
		    			context.put("modeSemak","view");
		    			context.put("isEditSemak","no");
		    			
		    			if (doPost.equals("true")) {
		    				//update semakan
			    			updateSemakan(session);	  
			    			updateStatusSemakan(session);
	            		}
	    	
		    			//status temporary
	    	    		//id_status = "133";
		    			
		    			//data header (get id_status)
	    	    		dataHeaderStatus(session,idpermohonan);
	    	    		id_status = dataHeaderStatus(session,idpermohonan);
	  
	    	    		//data mmk
        	    		dataMMK(session,idpermohonan);
        	    		
        	    		//get tarikh hantar
        	    		model.setDataMMK(idpermohonan);
        	        	dataMMK = model.getDataMMK();
        	        	if(dataMMK.size()!=0){
        	        		Hashtable dm = (Hashtable)dataMMK.get(0);
        	        		tarikh_hantar = (String)dm.get("tarikh_hantar");
        	        	}
        	    		
	    	    	}//close simpanSemakan
	    		
	    	    //view form for tab 2	
	    		}else{
	    			
	    			//form validation tab 2
	    			context.put("modeSemak","view");
	    			context.put("isEditSemak","no");
	    			
	    			//data mmk
    	    		dataMMK(session,idpermohonan);
    	    		
    	    		
    	    		//** TAB 3 **//
    	    		//view form for tab 3
    	    		if(id_status.equals("134") || id_status.equals("26") || id_status.equals("31")
    					|| id_status.equals("35") || id_status.equals("54") || id_status.equals("52")
    					|| id_status.equals("58") || id_status.equals("43") || id_status.equals("38")
    					|| id_status.equals("48") || id_status.equals("62") || id_status.equals("68")
    					|| id_status.equals("72") || id_status.equals("59") || id_status.equals("76")
    					|| id_status.equals("82")){
    	    			
    	    			//form validation tab 3
    	    			context.put("modeKeputusan","view");
    	    			context.put("isEditKeputusan","no");
    	    			
    	    			//data mmk
        	    		dataMMK(session,idpermohonan);
        	    		
    	    			//data mmkkeputusan
        	    		dataMMKKeputusan(session,idpermohonan);
    	    			
        	    		if("kemaskiniKeputusan".equals(submit2)){
        	    			
        	    			//form validation tab 3
        	    			context.put("modeKeputusan","view");
        	    			context.put("isEditKeputusan","yes");
        	    			
        	    			//data mmk
            	    		dataMMK(session,idpermohonan);
            	    		
                			String submit3 = getParam("command3");
                	    	myLogger.info("submit[3] : " + submit3);
            	    		
                	    	if("updateKeputusan".equals(submit3)){
                	    		
                	    		updateKeputusan(session);
                	    		
                	    		//form validation tab 3
            	    			context.put("modeKeputusan","view");
            	    			context.put("isEditKeputusan","no");
            	    			
                	    		//data mmkkeputusan
                	    		dataMMKKeputusan(session,idpermohonan);
                	    		
                	    		//data mmk
                	    		dataMMK(session,idpermohonan);
                	    		
                	    	}//close updateKeputusan
                	    	
        	    		}//close kemaskiniKeputusan
        	    		
    	    		//new form for tab 3
    	    		}else{
    	    			
    	    			//form validation tab 3
    	    			context.put("modeKeputusan","new");
    	    			
    	    			if("simpanKeputusan".equals(submit2)){
    	    				
    	    				//form validation
    		    			context.put("modeKeputusan","view");
    		    			context.put("isEditKeputusan","no");
    		    			
    		    			if (doPost.equals("true")) {
    		    				//simpan keputusan
    		    				simpanKeputusan(session);
    		    				updateStatusKeputusan(session);
    	            		}
    	    	
    		    			//status temporary
    	    	    		//id_status = "134";
    	    	    		
    		    			//data header (get id_status)
    	    	    		dataHeaderStatus(session,idpermohonan);
    	    	    		id_status = dataHeaderStatus(session,idpermohonan);
    	    	    		
    	    	    		//data mmk
            	    		dataMMK(session,idpermohonan);
            	    		
            	    		//data mmkkeputusan
            	    		dataMMKKeputusan(session,idpermohonan);
    	    				
    	    			}//close simpanKeputusan
    	    			
    	    		}//close if tab 3 view/new
    	    		
    	    		
    	    		if("kemaskiniSemakan".equals(submit2)){
    	    			
    	    			//form validation
    	    			context.put("modeSemak","view");
    	    			context.put("isEditSemak","yes");
    	    			
    	    			String submit3 = getParam("command3");
            	    	myLogger.info("submit[3] : " + submit3);
            	    	
    	    			if("updateSemakan2".equals(submit3)){
    	    				
    	    				//form validation
    		    			context.put("modeSemak","view");
    		    			context.put("isEditSemak","no");
    		    			
    		    			//update semakan
    			    		updateSemakan(session);
    			    		
    	    	    		//data mmk
            	    		dataMMK(session,idpermohonan);
    	    				
            	    		//get tarikh hantar
            	    		model.setDataMMK(idpermohonan);
            	        	dataMMK = model.getDataMMK();
            	        	if(dataMMK.size()!=0){
            	        		Hashtable dm = (Hashtable)dataMMK.get(0);
            	        		tarikh_hantar = (String)dm.get("tarikh_hantar");
            	        	}
            	        	
    	    			}//close updateSemakan
        	    		
    	    		}//close kemaskiniSemakan
    	    		
	    		}//close form for tab 2
	    		
    		}//close form validation
    		
    		
    		//screen
    		vm = mainscreen;   		
    
    	}//close viewMMK
    	
    	else 
    	if("hantar".equals(submit)){
    			
    		if (doPost.equals("true")) {
				//simpan keputusan
				hantar(session);
    		}
    		
    		//status temporary
    		//id_status = "26";
    		
    		//form validation tab 1
			context.put("mode","view");
			context.put("isEdit","no");
    		
    		//form validation tab 2
			context.put("modeSemak","view");
			context.put("isEditSemak","no");
			
    		//form validation tab 3
			context.put("modeKeputusan","view");
			context.put("isEditKeputusan","no");
    		
			//data header (get id_status)
    		dataHeaderStatus(session,idpermohonan);
    		id_status = dataHeaderStatus(session,idpermohonan);
    		
    		//data mmk
    		dataMMK(session,idpermohonan);
    		
    		//data mmkkeputusan
    		dataMMKKeputusan(session,idpermohonan);
    		
    		//screen
    		vm = mainscreen;
    		
    	}//close hantar
    	
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = modelSementara.getListCarian(userIdNeg);
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = modelSementara.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
   
    		//tab validation
			if(id_status.equals("132")){
				context.put("showSemak","yes");
			}
			else if(id_status.equals("133")){
				
				context.put("showSemak","yes");
				
				if(tarikh_hantar!=""){
				context.put("showKeputusan","yes");
				}
				
			}
			
			else if(id_status.equals("26") || id_status.equals("134") || id_status.equals("31")
					|| id_status.equals("35") || id_status.equals("54") || id_status.equals("52")
					|| id_status.equals("58") || id_status.equals("43") || id_status.equals("38")
					|| id_status.equals("48") || id_status.equals("62") || id_status.equals("68")
					|| id_status.equals("72") || id_status.equals("59") || id_status.equals("76")
					|| id_status.equals("82")){
				
				context.put("showSemak","yes");
				context.put("showKeputusan","yes");
			}
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id negeri untuk mmk
	    	context.put("negeriMMK", negeriMMK);
	    	
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_fail", id_fail);
	    	context.put("no_fail", no_fail);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		myLogger.info("VM ::::::::::: "+vm);
    		return vm;
    		
	    }//close public template
	
	
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access"})
	private void sendEmail(String id_pengarah,String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception{
		
    	Vector checkEmailPengarah = new Vector();
    	checkEmailPengarah.clear();
  
		//check email (pengarah)
		checkEmailPengarah = myInfo.checkEmail(id_pengarah);
		String emelPengarah = "";
		if(checkEmailPengarah.size()!=0){
			Hashtable ceP = (Hashtable)checkEmailPengarah.get(0);
			emelPengarah = (String)ceP.get("EMEL");
		}

		EmailTester et = new EmailTester();
		
		//if(emelPengarah!=""){
			et.setEmail("sementara",emelPengarah,"hantarUntukSemakan",nofail,nama_projek,tarikh_permohonan,nama_kementerian);
		//}
		
		//jenis email
		// - hantar pengesahan (pt - pengarah)
		// - hantar untuk diagihankan
		// - hantar untuk semakan mmk (pt - pengarah)
		
	}//close sendEmail
	
	private void ListCarian(HttpSession session, String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		SementaraMMK.setListCarian(nofail,tarikh,status,userIdNeg);
      
	}//close listcarian
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeaderStatus(HttpSession session,String idpermohonan) throws Exception{
    	
		//header
		String id_status = "";
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
		}
		
		return id_status;
		
	}//close dataHeaderStatus
	
	@SuppressWarnings("unchecked")
	private void simpanPenyediaan(HttpSession session) throws Exception{

		System.out.println("xxx");
		long id_mmk = DB.getNextID("TBLPPTMMK_SEQ");  
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", getParam("id_permohonan"));

//		h.put("txtLatarBelakang", getParam("txtLatarBelakang"));		
//		h.put("txtNilaianTanah", getParam("txtNilaianTanah"));
//		h.put("txtSyor", getParam("txtSyor"));					
//		h.put("txtPeruntukan", getParam("txtPeruntukan"));
//		h.put("txtNamaTuanTanah", getParam("txtNamaTuanTanah"));		
//		h.put("txtAsasPertimbangan", getParam("txtAsasPertimbangan"));		
//		h.put("txtTujuan", getParam("txtTujuan"));
//		h.put("txtPerihalTanah", getParam("txtPerihalTanah"));
//		h.put("txtPerihalPohon", getParam("txtPerihalPohon"));		
//		h.put("txtAnggaranKos", getParam("txtAnggaranKos"));
//		h.put("txtUlasanJT", getParam("txtUlasanJT"));
//		h.put("txtUlasan", getParam("txtUlasan"));
//		h.put("txtPerakuan", getParam("txtPerakuan"));
//		h.put("txtJawatankuasa", getParam("txtJawatankuasa"));
//		h.put("txtKeputusan", getParam("txtKeputusan"));	
//		h.put("txtPengecualian", getParam("txtPengecualian"));
//		h.put("txtHalLain", getParam("txtHalLain"));
//		h.put("txtImplikasi", getParam("txtImplikasi"));
//		h.put("txtKesimpulan", getParam("txtKesimpulan"));
//		h.put("txtPenutup", getParam("txtPenutup"));
//		h.put("txtPandangan", getParam("txtPandangan"));
//		h.put("txtPerakuanSetiausaha", getParam("txtPerakuanSetiausaha"));
//		h.put("txtButirAsas", getParam("txtButirAsas"));
//		h.put("txtKeadaanTanah", getParam("txtKeadaanTanah"));
//		h.put("txtButirTanah", getParam("txtButirTanah"));
//		h.put("txtKemudahanAsas", getParam("txtKemudahanAsas"));
		/*
		h.put("txtJenisPenggunaan", getParam("txtJenisPenggunaan"));
		h.put("txtSidang", getParam("txtSidang"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtKedudukan", getParam("txtKedudukan"));
		h.put("txtKeadaan", getParam("txtKeadaan"));
		*/
		 h.put("txtTajuk", getParam("txtTajuk"));
			h.put("txtSidang", getParam("txtSidang"));
			h.put("txtTempatSidang", getParam("txtTempatSidang"));
			h.put("txtTarikhSidang", getParam("txtTarikhSidang"));
			h.put("txtMasaSidang", getParam("txtMasaSidang"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			
			
			
		h.put("txtJenisPenggunaan", getParam("txtJenisPenggunaan"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtKedudukan", getParam("txtKedudukan"));
		h.put("txtKeadaan", getParam("txtKeadaan"));
		h.put("id_user", id_user);
		
		FrmMMKSek8Data.simpanPenyediaan(h,id_mmk);
		
		
		// TUJUAN
    	String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
		if (txtUlasanTUJUAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",id_mmk,"TUJUAN",id_user);
			}
		}
		
		//LATAR BELAKANG
		String[] txtUlasanLATARBELAKANG_MAIN = this.request.getParameterValues("txtUlasanLATARBELAKANG_MAIN");
		if (txtUlasanLATARBELAKANG_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLATARBELAKANG_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLATARBELAKANG_MAIN[t],"MAIN",id_mmk,"LATARBELAKANG",id_user);
			}
		}
    	
		//LAPORAN TANAH
		String[] txtUlasanLAPORANTANAH_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAH_MAIN");
		if (txtUlasanLAPORANTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAH_MAIN[t],"MAIN",id_mmk,"LAPORANTANAH",id_user);
			}
		}
		
		//PERIHAL TANAH
		String[] txtUlasanPERIHALTANAH_MAIN = this.request.getParameterValues("txtUlasanPERIHALTANAH_MAIN");
		if (txtUlasanPERIHALTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALTANAH_MAIN[t],"MAIN",id_mmk,"PERIHALTANAH",id_user);
			}
		}
		
		//ULASAN PENGARAH
		String[] txtUlasanULASANPENGARAH_MAIN = this.request.getParameterValues("txtUlasanULASANPENGARAH_MAIN");
		if (txtUlasanULASANPENGARAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANPENGARAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANPENGARAH_MAIN[t],"MAIN",id_mmk,"ULASANPENGARAH",id_user);
			}
		}
		
		//SYOR
		String[] txtUlasanSYOR_MAIN = this.request.getParameterValues("txtUlasanSYOR_MAIN");
		if (txtUlasanSYOR_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanSYOR_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanSYOR_MAIN[t],"MAIN",id_mmk,"SYOR",id_user);
			}
		}
		
		//KESIMPULAN
		String[] txtUlasanKESIMPULAN_MAIN = this.request.getParameterValues("txtUlasanKESIMPULAN_MAIN");
		if (txtUlasanKESIMPULAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKESIMPULAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKESIMPULAN_MAIN[t],"MAIN",id_mmk,"KESIMPULAN",id_user);
			}
		}
		
		//NILAIAN TANAH
		String[] txtUlasanNILAITANAH_MAIN = this.request.getParameterValues("txtUlasanNILAITANAH_MAIN");
		if (txtUlasanNILAITANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanNILAITANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanNILAITANAH_MAIN[t],"MAIN",id_mmk,"NILAITANAH",id_user);
			}
		}
		
		//WANG PERUNTUKAN
		String[] txtUlasanWANGPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanWANGPERUNTUKAN_MAIN");
		if (txtUlasanWANGPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanWANGPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanWANGPERUNTUKAN_MAIN[t],"MAIN",id_mmk,"WANGPERUNTUKAN",id_user);
			}
		}
		
		//PERIHALPERMOHONAN
		String[] txtUlasanPERIHALPERMOHONAN_MAIN = this.request.getParameterValues("txtUlasanPERIHALPERMOHONAN_MAIN");
		if (txtUlasanPERIHALPERMOHONAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPERMOHONAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPERMOHONAN_MAIN[t],"MAIN",id_mmk,"PERIHALPERMOHONAN",id_user);
			}
		}
		
		//PERIHALPEMOHON
		String[] txtUlasanPERIHALPEMOHON_MAIN = this.request.getParameterValues("txtUlasanPERIHALPEMOHON_MAIN");
		if (txtUlasanPERIHALPEMOHON_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPEMOHON_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPEMOHON_MAIN[t],"MAIN",id_mmk,"PERIHALPEMOHON",id_user);
			}
		}
		
		//ANGGARANPAMPASAN
		String[] txtUlasanANGGARANPAMPASAN_MAIN = this.request.getParameterValues("txtUlasanANGGARANPAMPASAN_MAIN");
		if (txtUlasanANGGARANPAMPASAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANPAMPASAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANPAMPASAN_MAIN[t],"MAIN",id_mmk,"ANGGARANPAMPASAN",id_user);
			}
		}
		
		//ULASANTEKNIKAL
		String[] txtUlasanULASANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanULASANTEKNIKAL_MAIN");
		if (txtUlasanULASANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANTEKNIKAL_MAIN[t],"MAIN",id_mmk,"ULASANTEKNIKAL",id_user);
			}
		}
		
		
		//PANDANGANYB
		String[] txtUlasanPANDANGANYB_MAIN = this.request.getParameterValues("txtUlasanPANDANGANYB_MAIN");
		if (txtUlasanPANDANGANYB_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPANDANGANYB_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPANDANGANYB_MAIN[t],"MAIN",id_mmk,"PANDANGANYB",id_user);
			}
		}
		
		//PANDANGANPT
		String[] txtUlasanPANDANGANPT_MAIN = this.request.getParameterValues("txtUlasanPANDANGANPT_MAIN");
		if (txtUlasanPANDANGANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPANDANGANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPANDANGANPT_MAIN[t],"MAIN",id_mmk,"PANDANGANPT",id_user);
			}
		}
		
		//PERAKUANPT
		String[] txtUlasanPERAKUANPT_MAIN = this.request.getParameterValues("txtUlasanPERAKUANPT_MAIN");
		if (txtUlasanPERAKUANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERAKUANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERAKUANPT_MAIN[t],"MAIN",id_mmk,"PERAKUANPT",id_user);
			}
		}
		
		// BUTIRASAS
    	String[] txtUlasanBUTIRASAS_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS_MAIN");
		if (txtUlasanBUTIRASAS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS_MAIN[t],"MAIN",id_mmk,"BUTIRASAS",id_user);
			}
		}
		
		// KEADAANTANAH
    	String[] txtUlasanKEADAANTANAH_MAIN = this.request.getParameterValues("txtUlasanKEADAANTANAH_MAIN");
		if (txtUlasanKEADAANTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEADAANTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEADAANTANAH_MAIN[t],"MAIN",id_mmk,"KEADAANTANAH",id_user);
			}
		}
		
		// KEMUDAHAN ASAS
    	String[] txtUlasanKEMUDAHANASAS_MAIN = this.request.getParameterValues("txtUlasanKEMUDAHANASAS_MAIN");
		if (txtUlasanKEMUDAHANASAS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEMUDAHANASAS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEMUDAHANASAS_MAIN[t],"MAIN",id_mmk,"KEMUDAHANASAS",id_user);
			}
		}
		
		//ULASANPT
		String[] txtUlasanULASANPT_MAIN = this.request.getParameterValues("txtUlasanULASANPT_MAIN");
		if (txtUlasanULASANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANPT_MAIN[t],"MAIN",id_mmk,"ULASANPT",id_user);
			}
		}
		
		//KEPUTUSAN
		String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
		if (txtUlasanKEPUTUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",id_mmk,"KEPUTUSAN",id_user);
			}
		}
		
		//ASASPERTIMBANGAN
		String[] txtUlasanASASPERTIMBANGAN_MAIN = this.request.getParameterValues("txtUlasanASASPERTIMBANGAN_MAIN");
		if (txtUlasanASASPERTIMBANGAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanASASPERTIMBANGAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanASASPERTIMBANGAN_MAIN[t],"MAIN",id_mmk,"ASASPERTIMBANGAN",id_user);
			}
		}
		
		//IMPLIKASI
		String[] txtUlasanIMPLIKASI_MAIN = this.request.getParameterValues("txtUlasanIMPLIKASI_MAIN");
		if (txtUlasanIMPLIKASI_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanIMPLIKASI_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanIMPLIKASI_MAIN[t],"MAIN",id_mmk,"IMPLIKASI",id_user);
			}
		}
		
		//LAPORANTEKNIKAL
		String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
		if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",id_mmk,"LAPORANTEKNIKAL",id_user);
			}
		}
		
		//ANGGARANKOS
		String[] txtUlasanANGGARANKOS_MAIN = this.request.getParameterValues("txtUlasanANGGARANKOS_MAIN");
		if (txtUlasanANGGARANKOS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANKOS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANKOS_MAIN[t],"MAIN",id_mmk,"ANGGARANKOS",id_user);
			}
		}
		
		//JAWATANKUASANEGERI
		String[] txtUlasanJAWATANKUASANEGERI_MAIN = this.request.getParameterValues("txtUlasanJAWATANKUASANEGERI_MAIN");
		if (txtUlasanJAWATANKUASANEGERI_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanJAWATANKUASANEGERI_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanJAWATANKUASANEGERI_MAIN[t],"MAIN",id_mmk,"JAWATANKUASANEGERI",id_user);
			}
		}
		
		//TAKSIRANHARGATANAH
		String[] txtUlasanTAKSIRANHARGATANAH_MAIN = this.request.getParameterValues("txtUlasanTAKSIRANHARGATANAH_MAIN");
		if (txtUlasanTAKSIRANHARGATANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTAKSIRANHARGATANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTAKSIRANHARGATANAH_MAIN[t],"MAIN",id_mmk,"TAKSIRANHARGATANAH",id_user);
			}
		}
		
		//HALLAIN
		String[] txtUlasanHALLAIN_MAIN = this.request.getParameterValues("txtUlasanHALLAIN_MAIN");
		if (txtUlasanHALLAIN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanHALLAIN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanHALLAIN_MAIN[t],"MAIN",id_mmk,"HALLAIN",id_user);
			}
		}
		
		//PERIHALAP
		String[] txtUlasanPERIHALAP_MAIN = this.request.getParameterValues("txtUlasanPERIHALAP_MAIN");
		if (txtUlasanPERIHALAP_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALAP_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALAP_MAIN[t],"MAIN",id_mmk,"PERIHALAP",id_user);
			}
		}
		
		//PERUNTUKAN
		String[] txtUlasanPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanPERUNTUKAN_MAIN");
		if (txtUlasanPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERUNTUKAN_MAIN[t],"MAIN",id_mmk,"PERUNTUKAN",id_user);
			}
		}
		
		
	}//close simpanPenyediaan
	
	@SuppressWarnings("unchecked")
	private void updatePenyediaan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		String id_mmk = getParam("id_mmk");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		
		h.put("id_mmk", id_mmk);
		
//		h.put("txtLatarBelakang", getParam("txtLatarBelakang"));		
//		h.put("txtNilaianTanah", getParam("txtNilaianTanah"));
//		h.put("txtSyor", getParam("txtSyor"));					
//		h.put("txtPeruntukan", getParam("txtPeruntukan"));
//		h.put("txtNamaTuanTanah", getParam("txtNamaTuanTanah"));		
//		h.put("txtAsasPertimbangan", getParam("txtAsasPertimbangan"));		
//		h.put("txtTujuan", getParam("txtTujuan"));
//		h.put("txtPerihalTanah", getParam("txtPerihalTanah"));
//		h.put("txtPerihalPohon", getParam("txtPerihalPohon"));		
//		h.put("txtAnggaranKos", getParam("txtAnggaranKos"));
//		h.put("txtUlasanJT", getParam("txtUlasanJT"));
//		h.put("txtUlasan", getParam("txtUlasan"));
//		h.put("txtPerakuan", getParam("txtPerakuan"));
//		h.put("txtJawatankuasa", getParam("txtJawatankuasa"));
//		h.put("txtKeputusan", getParam("txtKeputusan"));		
//		h.put("txtPengecualian", getParam("txtPengecualian"));
//		h.put("txtHalLain", getParam("txtHalLain"));
//		h.put("txtImplikasi", getParam("txtImplikasi"));
//		h.put("txtKesimpulan", getParam("txtKesimpulan"));
//		h.put("txtPenutup", getParam("txtPenutup"));
//		h.put("txtPandangan", getParam("txtPandangan"));
//		h.put("txtPerakuanSetiausaha", getParam("txtPerakuanSetiausaha"));
//		h.put("txtButirAsas", getParam("txtButirAsas"));
//		h.put("txtKeadaanTanah", getParam("txtKeadaanTanah"));
//		h.put("txtButirTanah", getParam("txtButirTanah"));
//		h.put("txtKemudahanAsas", getParam("txtKemudahanAsas"));
		/* yati comment
		h.put("txtJenisPenggunaan", getParam("txtJenisPenggunaan"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtKedudukan", getParam("txtKedudukan"));
		h.put("txtKeadaan", getParam("txtKeadaan"));
		*/
		
		h.put("txtTajuk", getParam("txtTajuk"));
		h.put("txtLatarBelakang", getParam("txtLatarBelakang"));
		h.put("txtTujuan", getParam("txtTujuan"));
		h.put("txtPerihalTanah", getParam("txtPerihalTanah"));
		h.put("txtNilaianTanah", getParam("txtNilaianTanah"));
		h.put("txtPengesahanPeruntukan", getParam("txtPengesahanPeruntukan"));
		h.put("txtSyor", getParam("txtSyor"));	
		h.put("txtSidang", getParam("txtSidang"));
		h.put("txtTempatSidang", getParam("txtTempatSidang"));
		h.put("txtTarikhSidang", getParam("txtTarikhSidang"));
		h.put("txtMasaSidang", getParam("txtMasaSidang"));
		h.put("jeniswaktu", getParam("jeniswaktu"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//FrmMMKSek8Data.updatePenyediaan(h);
		FrmMMKSek8Data.updatePenyediaanSementara(h);
		
		modelPembatalan.delete_subMMK(id_mmk);
		
		// TUJUAN
    	String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
		if (txtUlasanTUJUAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"TUJUAN",id_user);
			}
		}
		
		//LATAR BELAKANG
		String[] txtUlasanLATARBELAKANG_MAIN = this.request.getParameterValues("txtUlasanLATARBELAKANG_MAIN");
		if (txtUlasanLATARBELAKANG_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLATARBELAKANG_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLATARBELAKANG_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LATARBELAKANG",id_user);
			}
		}
    	
		//LAPORAN TANAH
		String[] txtUlasanLAPORANTANAH_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAH_MAIN");
		if (txtUlasanLAPORANTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTANAH",id_user);
			}
		}
		
		//PERIHAL TANAH
		String[] txtUlasanPERIHALTANAH_MAIN = this.request.getParameterValues("txtUlasanPERIHALTANAH_MAIN");
		if (txtUlasanPERIHALTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALTANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALTANAH",id_user);
			}
		}
		
		//ULASAN PENGARAH
		String[] txtUlasanULASANPENGARAH_MAIN = this.request.getParameterValues("txtUlasanULASANPENGARAH_MAIN");
		if (txtUlasanULASANPENGARAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANPENGARAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANPENGARAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ULASANPENGARAH",id_user);
			}
		}
		
		//SYOR
		String[] txtUlasanSYOR_MAIN = this.request.getParameterValues("txtUlasanSYOR_MAIN");
		if (txtUlasanSYOR_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanSYOR_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanSYOR_MAIN[t],"MAIN",Long.parseLong(id_mmk),"SYOR",id_user);
			}
		}
		
		//KESIMPULAN
		String[] txtUlasanKESIMPULAN_MAIN = this.request.getParameterValues("txtUlasanKESIMPULAN_MAIN");
		if (txtUlasanKESIMPULAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKESIMPULAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKESIMPULAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KESIMPULAN",id_user);
			}
		}
		
		//NILAIAN TANAH
		String[] txtUlasanNILAITANAH_MAIN = this.request.getParameterValues("txtUlasanNILAITANAH_MAIN");
		if (txtUlasanNILAITANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanNILAITANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanNILAITANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"NILAITANAH",id_user);
			}
		}
		
		//WANG PERUNTUKAN
		String[] txtUlasanWANGPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanWANGPERUNTUKAN_MAIN");
		if (txtUlasanWANGPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanWANGPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanWANGPERUNTUKAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"WANGPERUNTUKAN",id_user);
			}
		}
		
		//PERIHALPERMOHONAN
		String[] txtUlasanPERIHALPERMOHONAN_MAIN = this.request.getParameterValues("txtUlasanPERIHALPERMOHONAN_MAIN");
		if (txtUlasanPERIHALPERMOHONAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPERMOHONAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPERMOHONAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALPERMOHONAN",id_user);
			}
		}
		
		//PERIHALPEMOHON
		String[] txtUlasanPERIHALPEMOHON_MAIN = this.request.getParameterValues("txtUlasanPERIHALPEMOHON_MAIN");
		if (txtUlasanPERIHALPEMOHON_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPEMOHON_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPEMOHON_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALPEMOHON",id_user);
			}
		}
		
		//ANGGARANPAMPASAN
		String[] txtUlasanANGGARANPAMPASAN_MAIN = this.request.getParameterValues("txtUlasanANGGARANPAMPASAN_MAIN");
		if (txtUlasanANGGARANPAMPASAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANPAMPASAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANPAMPASAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ANGGARANPAMPASAN",id_user);
			}
		}
		
		//ULASANTEKNIKAL
		String[] txtUlasanULASANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanULASANTEKNIKAL_MAIN");
		if (txtUlasanULASANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANTEKNIKAL_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ULASANTEKNIKAL",id_user);
			}
		}
		
		
		//PANDANGANYB
		String[] txtUlasanPANDANGANYB_MAIN = this.request.getParameterValues("txtUlasanPANDANGANYB_MAIN");
		if (txtUlasanPANDANGANYB_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPANDANGANYB_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPANDANGANYB_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PANDANGANYB",id_user);
			}
		}
		
		//PANDANGANPT
		String[] txtUlasanPANDANGANPT_MAIN = this.request.getParameterValues("txtUlasanPANDANGANPT_MAIN");
		if (txtUlasanPANDANGANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPANDANGANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPANDANGANPT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PANDANGANPT",id_user);
			}
		}
		
		//PERAKUANPT
		String[] txtUlasanPERAKUANPT_MAIN = this.request.getParameterValues("txtUlasanPERAKUANPT_MAIN");
		if (txtUlasanPERAKUANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERAKUANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERAKUANPT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERAKUANPT",id_user);
			}
		}
		
		// BUTIRASAS
    	String[] txtUlasanBUTIRASAS_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS_MAIN");
		if (txtUlasanBUTIRASAS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS",id_user);
			}
		}
		
		// KEADAANTANAH
    	String[] txtUlasanKEADAANTANAH_MAIN = this.request.getParameterValues("txtUlasanKEADAANTANAH_MAIN");
		if (txtUlasanKEADAANTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEADAANTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEADAANTANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KEADAANTANAH",id_user);
			}
		}
		
		// KEMUDAHAN ASAS
    	String[] txtUlasanKEMUDAHANASAS_MAIN = this.request.getParameterValues("txtUlasanKEMUDAHANASAS_MAIN");
		if (txtUlasanKEMUDAHANASAS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEMUDAHANASAS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEMUDAHANASAS_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KEMUDAHANASAS",id_user);
			}
		}
		
		//ULASANPT
		String[] txtUlasanULASANPT_MAIN = this.request.getParameterValues("txtUlasanULASANPT_MAIN");
		if (txtUlasanULASANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanULASANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanULASANPT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ULASANPT",id_user);
			}
		}
		
		//KEPUTUSAN
		String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
		if (txtUlasanKEPUTUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KEPUTUSAN",id_user);
			}
		}
		
		//ASASPERTIMBANGAN
		String[] txtUlasanASASPERTIMBANGAN_MAIN = this.request.getParameterValues("txtUlasanASASPERTIMBANGAN_MAIN");
		if (txtUlasanASASPERTIMBANGAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanASASPERTIMBANGAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanASASPERTIMBANGAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ASASPERTIMBANGAN",id_user);
			}
		}
		
		//IMPLIKASI
		String[] txtUlasanIMPLIKASI_MAIN = this.request.getParameterValues("txtUlasanIMPLIKASI_MAIN");
		if (txtUlasanIMPLIKASI_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanIMPLIKASI_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanIMPLIKASI_MAIN[t],"MAIN",Long.parseLong(id_mmk),"IMPLIKASI",id_user);
			}
		}
		
		//LAPORANTEKNIKAL
		String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
		if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTEKNIKAL",id_user);
			}
		}
		
		//ANGGARANKOS
		String[] txtUlasanANGGARANKOS_MAIN = this.request.getParameterValues("txtUlasanANGGARANKOS_MAIN");
		if (txtUlasanANGGARANKOS_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANKOS_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANKOS_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ANGGARANKOS",id_user);
			}
		}
		
		//JAWATANKUASANEGERI
		String[] txtUlasanJAWATANKUASANEGERI_MAIN = this.request.getParameterValues("txtUlasanJAWATANKUASANEGERI_MAIN");
		if (txtUlasanJAWATANKUASANEGERI_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanJAWATANKUASANEGERI_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanJAWATANKUASANEGERI_MAIN[t],"MAIN",Long.parseLong(id_mmk),"JAWATANKUASANEGERI",id_user);
			}
		}
		
		//TAKSIRANHARGATANAH
		String[] txtUlasanTAKSIRANHARGATANAH_MAIN = this.request.getParameterValues("txtUlasanTAKSIRANHARGATANAH_MAIN");
		if (txtUlasanTAKSIRANHARGATANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTAKSIRANHARGATANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTAKSIRANHARGATANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"TAKSIRANHARGATANAH",id_user);
			}
		}
		
		//HALLAIN
		String[] txtUlasanHALLAIN_MAIN = this.request.getParameterValues("txtUlasanHALLAIN_MAIN");
		if (txtUlasanHALLAIN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanHALLAIN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanHALLAIN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"HALLAIN",id_user);
			}
		}
		
		//PERIHALAP
		String[] txtUlasanPERIHALAP_MAIN = this.request.getParameterValues("txtUlasanPERIHALAP_MAIN");
		if (txtUlasanPERIHALAP_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALAP_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALAP_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALAP",id_user);
			}
		}
		
		//PERUNTUKAN
		String[] txtUlasanPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanPERUNTUKAN_MAIN");
		if (txtUlasanPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERUNTUKAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERUNTUKAN",id_user);
			}
		}
		
	}//close updatePenyediaan
	
	private void hantar(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status maklumat mmk
		String idstatus = "26";
    	
		FrmMMKSek8Data.updateStatus(idpermohonan,idUser,idstatus);
      
	}//close hantar

	@SuppressWarnings("unchecked")
	private void hantarPengesahan(HttpSession session) throws Exception{
	    	
		Hashtable h = new Hashtable();
		    
		h.put("id_mmk", getParam("id_mmk"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		Seksyen4MMK.hantarPengesahan(h);  
		    
	}//close hantarPengesahan
	
	private void updateStatusPenyediaan(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status penyediaan mmk
		String idstatus = "132";
    	
		FrmMMKSek8Data.updateStatus(idpermohonan,idUser,idstatus);
      
	}//close updateStatusPenyediaan
	
	private void updateStatusSemakan(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status semakan mmk
		String idstatus = "133";
    	
		FrmMMKSek8Data.updateStatus(idpermohonan,idUser,idstatus);
		
	}//close updateStatusSemakan

	private void updateStatusKeputusan(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status keputusan mmk
		String idstatus = "134";
    	
		FrmMMKSek8Data.updateStatus(idpermohonan,idUser,idstatus);
      
	}//close updateStatusPenyediaan

	@SuppressWarnings("unchecked")
	private void simpanKeputusan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_mmk", getParam("id_mmk"));
		
		h.put("txtNoRujukan", getParam("txtNoRujukan"));
		h.put("txdTarikhMMK", getParam("txdTarikhMMK"));
		//h.put("txdTarikhHantar", getParam("txdTarikhHantar"));
		//h.put("txdTarikhKeputusan", getParam("txdTarikhKeputusan"));
		h.put("txdTarikhTerimaKeputusan", getParam("txdTarikhTerimaKeputusan"));
		h.put("sorKeputusan", getParam("sorKeputusan"));
		h.put("txtUlasanKeputusan", getParam("txtUlasanKeputusan"));
		h.put("txtNoPerserahan", getParam("txtNoPerserahan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmMMKSek8Data.simpanKeputusan(h);
  
	}//close simpanKeputusan
	
	@SuppressWarnings("unchecked")
	private void updateKeputusan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		h.put("id_mmkkeputusan", getParam("id_mmkkeputusan"));
		h.put("id_mmk", getParam("id_mmk"));
		
		h.put("txtNoRujukan", getParam("txtNoRujukan"));
		h.put("txdTarikhMMK", getParam("txdTarikhMMK"));
		//h.put("txdTarikhHantar", getParam("txdTarikhHantar"));
		//h.put("txdTarikhKeputusan", getParam("txdTarikhKeputusan"));
		h.put("txdTarikhTerimaKeputusan", getParam("txdTarikhTerimaKeputusan"));
		h.put("sorKeputusan", getParam("sorKeputusan"));
		h.put("txtUlasanKeputusan", getParam("txtUlasanKeputusan"));
		h.put("txtNoPerserahan", getParam("txtNoPerserahan"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmMMKSek8Data.updateKeputusan(h);
  
	}//close updateKeputusan
	
	@SuppressWarnings("unchecked")
	private void updateSemakan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();
		
		String tarikh_hantar = getParam("txdTarikhHantar");
		h.put("id_mmk", getParam("id_mmk"));
		
		h.put("txdTarikhSemakan", getParam("txdTarikhSemakan"));
		h.put("txdTarikhHantar", tarikh_hantar);
		h.put("socKeputusanSemak", getParam("socKeputusanSemak"));
		h.put("txtUlasanSemak", getParam("txtUlasanSemak"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("role", session.getAttribute("_portal_role"));
		 
		FrmMMKSek8Data.updateSemakan(h);
		
	}//close updateSemakan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMMK(HttpSession session,String idpermohonan) throws Exception{
    	
		model.setDataMMK(idpermohonan);
    	Vector dataMMK = model.getDataMMK();
    	String id_mmk = "";
    	String flag_semakan_pengarah = "";
    	if(dataMMK.size()!=0){
    		Hashtable dm = (Hashtable)dataMMK.get(0);
    		id_mmk = (String)dm.get("id_mmk");
    		flag_semakan_pengarah = (String)dm.get("flag_semakan_pengarah");
    	}
    	context.put("id_mmk",id_mmk);
    	context.put("dataMMK",dataMMK);
    	context.put("flag_semakan_pengarah",flag_semakan_pengarah);
      
    	//sub mmk
		Vector senarai_submmk = modelPembatalan.senarai_submmk(id_mmk);
		context.put("senarai_submmk", senarai_submmk);
    	
	}//close dataMMK
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMMKKeputusan(HttpSession session,String idpermohonan) throws Exception{
    	
		model.setDataMMK(idpermohonan);
    	Vector dataMMK = model.getDataMMK();
    	String id_mmk = "";
    	if(dataMMK.size()!=0){
    		Hashtable dm = (Hashtable)dataMMK.get(0);
    		id_mmk = (String)dm.get("id_mmk");
    	}

    	model.setDataMMKKeputusan(id_mmk);
    	Vector dataMMKKeputusan = model.getDataMMKKeputusan();
    	String id_mmkkeputusan = "";
    	if(dataMMKKeputusan.size()!=0){
    		Hashtable dmk = (Hashtable)dataMMKKeputusan.get(0);
    		id_mmkkeputusan = (String)dmk.get("id_mmkkeputusan");
    	}
    	context.put("id_mmkkeputusan",id_mmkkeputusan);
    	context.put("dataMMKKeputusan",dataMMKKeputusan);
    	
	}//close dataMMKKeputusan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndId(String userIdNeg) throws Exception{
		
		Vector dataNamaPengarah = new Vector();		
		dataNamaPengarah.clear();
		
	    //GET NAMA PENGARAH DAN ID PENGARAH
	    String nama_pengarah = "";
	    String id_pengarah = "";
	    modelUPT.setNamaPengarah(userIdNeg);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = (String)np.get("nama_pengarah");
	    	id_pengarah = (String)np.get("user_id");
	    }
	    
	    context.put("nama_pengarah",nama_pengarah);

	    return id_pengarah;
	    
	}//close userData	
	
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session,String action,Vector list) {
		
			try {
			
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
			this.context.put("listPermohonan",paging.getPage(page));
		    this.context.put("page", new Integer(page));
		    this.context.put("itemsPerPage", new Integer(itemsPerPage));
		    this.context.put("totalPages", new Integer(paging.getTotalPages()));
		    this.context.put("startNumber", new Integer(paging.getTopNumber()));
		    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
		    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
		        
			} catch (Exception e) {
				e.printStackTrace();
				this.context.put("error",e.getMessage());
			}	
		}	
	
	//PENAMBAHAN YATI
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalLuasAmbil(String idpermohonan) throws Exception{
		
		Vector getLuasAmbil = new Vector();
		getLuasAmbil.clear();
		
		String totalLuasAmbil = "";
		
		modelLaporan.setTotalLuasAmbil(idpermohonan,"");
		getLuasAmbil = modelLaporan.getTotalLuasAmbil();
		if(getLuasAmbil.size()!=0){
			Hashtable la = (Hashtable)getLuasAmbil.get(0);
			totalLuasAmbil = (String)la.get("totalLuasAmbil");
		}
		
		context.put("lblLuasKeseluruhan",totalLuasAmbil);
		
		
		Vector getJumlahLot = new Vector();
		getJumlahLot.clear();
		String totaLLot = "";
		modelLaporan.setTotalLot(idpermohonan,"");
		getJumlahLot = modelLaporan.getTotalLot();
		if(getJumlahLot.size()!=0){
			Hashtable ll = (Hashtable)getJumlahLot.get(0);
			totaLLot = (String)ll.get("totaLLot");
		}
				
		context.put("totaLLot",totaLLot);
		
	}//close getTotalLuasAmbil
		
	
}//close class
