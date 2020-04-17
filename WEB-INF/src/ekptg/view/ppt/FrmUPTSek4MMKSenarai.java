package ekptg.view.ppt;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.Seksyen4MMK;
import ekptg.view.ppt.email.EmailTester;




public class FrmUPTSek4MMKSenarai extends AjaxBasedModule {
	
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek4MMKSenarai.class);
	
	Seksyen4MMK logic = new Seksyen4MMK();
	PPTHeader header = new PPTHeader();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmPembatalanInternalData modelPembatalan = new FrmPembatalanInternalData();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	
	
	
		
	
	
	FrmSek8LaporanAwalTanahData modelLaporanTanah = new FrmSek8LaporanAwalTanahData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
    public String doTemplate2() throws Exception{
		
    	HttpSession session = this.request.getSession();

    	//command for paging
    	String action = getParam("action");
    	
    	String vm = "";
    	String idpegawai = "";
    	
    	//screen
    	String pageDepan = "app/ppt/frmUPTSek4MMKSenarai.jsp";
    	String screenListMMK = "app/ppt/frmUPTSek4Senarai.jsp";
    	String screenMMK = "app/ppt/frmUPTSek4MMK.jsp";
  
    	String diluluskan = "";
    	String ditolak = "";
    	String ditangguhkan = "";
    	context.put("tarikhBorangA", "");
    	
    	Vector list = new Vector();
    	Vector listDataSek4MMK = new Vector();
    	Vector listdepan = new Vector();
    	Vector listAgensi = new Vector();
    	Vector dataHeader = new Vector();
    	Vector listViewKertas = new Vector();
    	Vector listViewKertasKeputusan = new Vector();
    	Vector getMaxTarikhLuput = new Vector();
    	Vector getidmmk = new Vector();
    	Vector negeriProjek = new Vector();
    	Vector senarai_submmk = new Vector();
    	Vector dataNamaPengarah = new Vector();
    	Vector listUserid = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	listUserid.clear();
    	dataNamaPengarah.clear();
    	senarai_submmk.clear();
    	negeriProjek.clear();
    	dataHeader.clear();
    	listAgensi.clear();
    	listDataSek4MMK.clear();
    	list.clear();
    	listdepan.clear();
    	listViewKertas.clear();
    	listViewKertasKeputusan.clear();
    	getMaxTarikhLuput.clear();
    	getidmmk.clear();
    	
    	
    	
    	//user login id
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	//get nama pengarah dan id pengarah
    	//nameAndId(userIdNeg);
    	
    	
    	//header
    	String nama_kementerian = "";
    	String tujuan_projek = "";
    	String tarikh_permohonan = "";
		String id_status = "";
		String id_fail = "";
		String no_fail = "";
		String no_rujukan_ptg = "";
    	String idpermohonan = getParam("id_permohonan");
    	
    	if(!idpermohonan.equals(""))
    	{
    	header.setDataHeader(idpermohonan);
		dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = dh.get("id_status").toString();
			id_fail = dh.get("id_fail").toString();
			no_fail = (String)dh.get("no_fail");	
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");
			tujuan_projek = (String)dh.get("tujuan");
			tarikh_permohonan = (String)dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		getTotalLuasAmbil(idpermohonan);
    	}
    	
		context.put("no_rujukan_ptg",no_rujukan_ptg);
		
		
		//get jumlah keluasan ambil
		
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		if(!idpermohonan.equals(""))
    	{
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
    	}
		
		Vector malaysianDateByDate = new Vector();
		String sysdateMalaytarikhMohon = "";
		if(!idpermohonan.equals(""))
    	{
			if(!tarikh_permohonan.equals(""))
			{
				modelUPT.setMalayDateByDate(tarikh_permohonan);
			    malaysianDateByDate = modelUPT.getMalayDateByDate();
			    if(malaysianDateByDate.size()!=0){
			    	Hashtable md = (Hashtable)malaysianDateByDate.get(0);
			    	sysdateMalaytarikhMohon = (String)md.get("sysdateMalayByDate");
			    }
			}
    	}
		
		context.put("sysdateMalaytarikhMohon",sysdateMalaytarikhMohon);
		
		
		//GET NAMA PENGARAH
	    String nama_pengarah = "";
	    String id_pengarah = "";
	    if(!idpermohonan.equals(""))
    	{
		    modelUPT.setNamaPengarah(userIdNeg);
		    dataNamaPengarah = modelUPT.getNamaPengarah();
		    if(dataNamaPengarah.size()!=0){
		    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
		    	nama_pengarah = np.get("nama_pengarah").toString();
		    	id_pengarah = np.get("user_id").toString();
		    }
    	}
	    
	    context.put("nama_pengarah",nama_pengarah);
	    
		//anchor
    	String ScreenLocation = getParam("ScreenLocation");
    	String CursorPoint = getParam("CursorPoint");    	
    	context.put("ScreenLocation", ScreenLocation);
    	context.put("CursorPoint", CursorPoint);
    	
    	//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "4");
    	}
    	
    	//list senarai permohonan
    	if(idpermohonan.equals(""))
    	{
    	listdepan = logic.getListPemohon(userIdNeg);
    	}
    	
    	//prevent duplicate when refresh page
    	String doPost = (String) session.getAttribute("doPost");
    	
    	//default
    	context.put("view", "");
    	
    	//tab
    	String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
        if (selectedTab == null || "".equals(selectedTab)) {
        	selectedTab="0";
        }
        
        String id_mmk = getParam("id_mmk");       
        listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
        String flag_semak = "";
  		if(listViewKertas.size()!=0){
  			Hashtable lvk = (Hashtable) listViewKertas.get(0);
  			flag_semak = lvk.get("flag_semak").toString();
  		}
        context.put("flag_semak",flag_semak);
  		
        //data & list maklumat tanah
        String noLOT = "";
 		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		Vector listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		String nama2Mukim = "";
 		if(listMaklumatTanah.size()!=0){
 			Hashtable lmt = (Hashtable)listMaklumatTanah.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 		}
 		context.put("nama2Mukim", nama2Mukim);
 		
 		//get negeri for mmk
 		//data 2
 		modelUPT.setListPohon2(idpermohonan);
 		negeriProjek = modelUPT.getListPohon2();
 		String negeriMMK = "";
		if(negeriProjek.size()!=0){
			Hashtable i = (Hashtable) negeriProjek.get(0);
			negeriMMK = i.get("idProjekNegeri").toString();
		}
		
    	String submit = getParam("command");
    	myLogger.info("submit : "+submit);
    	
    	if("kemasukanMMKBaru".equals(submit))
		{
    		
    		//default tujuan projek
    		String tujuan = "";
    		if(dataHeader.size()!=0){
    			Hashtable mmk = (Hashtable) dataHeader.get(0);
    			tujuan = mmk.get("tujuan").toString(); 
    		}

    		context.put("tujuanProjek",tujuan);
    		
    		//sub mmk
    		senarai_submmk = modelPembatalan.senarai_submmk(getParam("id_mmk"));
			context.put("senarai_submmk", senarai_submmk);
    		
    		//reset value mmk
    		resetValueMMK(session);
    		
        	context.put("view", "no");
        	context.put("edit_penyediaan", "yes");
        	context.put("viewKertas", "");
    		   
        	vm = screenMMK;
    	    
		}//close command baru
    	
        else 
        if ("deleteKertasMMK".equals(submit)) {
  	      
        	//delete kertas mmk
        	delete(session);
  	      
        	//get tarikh luput
        	getTarikhLuput(session,idpermohonan);
        	String tarikhL = getTarikhLuput(session,idpermohonan);       	       	
    		context.put("tarikh_luput",tarikhL);
    		
    		//list mmk
    		listMMK(session,idpermohonan);
    		
  	      	logic.setDataSek4MMK(idpermohonan);
  	      	listDataSek4MMK = logic.getSek4MMK();      
  	      	String tarikhBorangA = "";
  	      	if(listDataSek4MMK.size()!=0){
  	      		Hashtable q = (Hashtable) listDataSek4MMK.get(0);
  	      		tarikhBorangA = q.get("tarikh_boranga").toString();
  	      	}
  		
  	      	//validation button hantar
  	      	context.put("tarikhBorangA",tarikhBorangA); 	      	
  		 
  	      	//screen
  	      	vm = screenListMMK;
  	      	
  	    }//close deleteKertasMMK
    	
    	else 
    	if("update_item_keputusan".equals(submit)){
    	
    		update_item_keputusan(session,id_status,id_fail,id_suburusanstatusfailppt);
    		
    		//form validation
      		context.put("view", "yes");
      		context.put("edit_penyediaan", "no");
      		context.put("edit_semakan", "no");
      		context.put("edit_keputusan", "no");
      		
      		header.setDataHeader(idpermohonan);
    		dataHeader = header.getDataHeader();
    		context.put("dataHeader", dataHeader);
    		if(dataHeader.size()!=0){
    			Hashtable dh = (Hashtable) dataHeader.get(0);
    			id_status = dh.get("id_status").toString();
    		}
      		
    		String id_mmk_keputusan = getParam("id_mmk_keputusan");
  			
    		context.put("id_mmk", id_mmk);
      		context.put("id_mmk_keputusan", id_mmk_keputusan);
      		   
      		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
      		String ruj_mmk = "";
      		if(listViewKertas.size()!=0){
      			Hashtable lvk = (Hashtable) listViewKertas.get(0);
      			ruj_mmk = lvk.get("no_rujmmk").toString();
      		}

      		listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
        	String statusKeputusan = "";
      		if(listViewKertasKeputusan.size()!=0){
      			Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
      			statusKeputusan = lvkk.get("status_keputusan").toString();
      		}
      		   		
      		if (statusKeputusan.equals("1")){
  				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
      		context.put("diluluskan",diluluskan);
    		context.put("ditolak",ditolak);
    		context.put("ditangguh",ditangguhkan);
      		
    		
        	context.put("ruj_mmk", ruj_mmk);
		
        	context.put("viewKertasKeputusan", listViewKertasKeputusan);
      		context.put("viewKertas", listViewKertas);

      		//screen
      		vm = screenMMK;
    		
      		
    	}//close update_item_keputusan
    	
    	else 
    	if ("update_penyediaan".equals(submit)){
    		
    		update_penyediaan(session);
    		
    		//form validation
        	context.put("view", "yes");
        	context.put("edit_penyediaan", "no");
        	context.put("edit_semakan", "no");
        	context.put("edit_keputusan", "no");
        	
        	//data sub mmk
    		senarai_submmk = modelPembatalan.senarai_submmk(id_mmk);
			context.put("senarai_submmk", senarai_submmk);
        	
    		logic.setDataSek4MMK(idpermohonan);
  	      	listDataSek4MMK = logic.getSek4MMK();
  	      	String tarikhBorangA = "";
  	      	if(listDataSek4MMK.size()!=0){
  	      		Hashtable q = (Hashtable) listDataSek4MMK.get(0);
  	      		tarikhBorangA = q.get("tarikh_boranga").toString();
  	      	}
  		
  	      	//validation button hantar
  	      	context.put("tarikhBorangA",tarikhBorangA);

        	listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
        		
        	context.put("viewKertas", listViewKertas);
        	
        	//screen
        	vm = screenMMK;
        	
    	}//close update_penyediaan
    	
    	
    	else 
        if ("hantarPengesahan".equals(submit)){
        		
        	if (doPost.equals("true")) {
        		hantarPengesahan(session);
        		sendEmail(id_pengarah,no_fail,tujuan_projek,tarikh_permohonan,nama_kementerian);  
        	}
        	
        	//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
  		    if(listViewKertas.size()!=0){
  		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
  		   		flag_semak = lvk.get("flag_semak").toString();
  		   	}
  		    context.put("flag_semak", flag_semak);
  		    context.put("viewKertas", listViewKertas);
    		
    		int stats = 0;
    		if(listViewKertas.size()!=0){
    			Hashtable lvk = (Hashtable) listViewKertas.get(0);
    			String _stats = lvk.get("status_semakan").toString();
 		   		if(_stats!=""){
 		   			stats = Integer.parseInt(_stats);
 		   		}
    		}
    		
    		if(stats==1)
    		{
    			context.put("select", "");
    			context.put("selectA", "selected");
    			context.put("selectB", "");
    		}
    		else if(stats==2)
    		{
    			context.put("select", "");
    			context.put("selectA", "");
    			context.put("selectB", "selected");
    		}else{
    			context.put("select", "selected");
    			context.put("selectA", "");
    			context.put("selectB", "");
    		}
	
    		//screen
    		vm = screenMMK;
        	
        }//close hantarPengesahan
    	
    	else 
    	if ("update_item_semakan".equals(submit)){
    		
    		update_item_semakan(session);
    		
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
  		    if(listViewKertas.size()!=0){
  		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
  		   		flag_semak = lvk.get("flag_semak").toString();
  		   	}
  		    context.put("flag_semak", flag_semak);
  		    context.put("viewKertas", listViewKertas);
    		
    		int stats = 0;
    		if(listViewKertas.size()!=0){
    			Hashtable lvk = (Hashtable) listViewKertas.get(0);
    			String _stats = lvk.get("status_semakan").toString();
 		   		if(_stats!=""){
 		   			stats = Integer.parseInt(_stats);
 		   		}
    		}
    		
    		if(stats==1)
    		{
    			context.put("select", "");
    			context.put("selectA", "selected");
    			context.put("selectB", "");
    		}
    		else if(stats==2)
    		{
    			context.put("select", "");
    			context.put("selectA", "");
    			context.put("selectB", "selected");
    		}else{
    			context.put("select", "selected");
    			context.put("selectA", "");
    			context.put("selectB", "");
    		}
	
    		//screen
    		vm = screenMMK;
    		
    	}//close update_item_semakan
    	
    	else 
    	if ("simpanPenyediaan".equals(submit)){
		      
    		
 			//simpan penyediaan
    		if (doPost.equals("true")) {
				add(session);
				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
			}
    		
    		//get latest id mmk
    		getidmmk = logic.getIdMMK(idpermohonan);
    		String idmmk = "";
    		if(getidmmk.size()!=0){
    			Hashtable im = (Hashtable)getidmmk.get(0);
    			idmmk = (String)im.get("id_mmk");
    		}
    		
    		//data sub mmk
    		senarai_submmk = modelPembatalan.senarai_submmk(idmmk);
			context.put("senarai_submmk", senarai_submmk);
			
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    	    context.put("edit_semakan", "no");
    	    context.put("edit_keputusan", "no");
    	    context.put("ruj_mmk", "");   		
    		
    	    header.setDataHeader(idpermohonan);
    		dataHeader = header.getDataHeader();
    		context.put("dataHeader", dataHeader);
    		if(dataHeader.size()!=0){
    			Hashtable dh = (Hashtable) dataHeader.get(0);
    			id_status = dh.get("id_status").toString();
    		}
    		
    		logic.setDataSek4MMK(idpermohonan);
  	      	listDataSek4MMK = logic.getSek4MMK();
  	      	String tarikhBorangA = "";
  	      	if(listDataSek4MMK.size()!=0){
  	      		Hashtable q = (Hashtable) listDataSek4MMK.get(0);
  	      		tarikhBorangA = q.get("tarikh_boranga").toString();
  	      	}
  		
  	      	//validation button hantar
  	      	context.put("tarikhBorangA",tarikhBorangA);

			//list mmk
    		listMMK(session,idpermohonan);
      				  
 		    listViewKertas = Seksyen4MMK.getViewKertas(idmmk);
 		    if(listViewKertas.size()!=0){
 		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
 		   		flag_semak = lvk.get("flag_semak").toString();
 		   	}
 		    context.put("flag_semak", flag_semak);
 		   
    	    listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(idmmk);
    	    String statusKeputusan = "";
 		   	if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}		
    	
 		    if (statusKeputusan.equals("1")){
 				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
     		context.put("diluluskan",diluluskan);
     		context.put("ditolak",ditolak);
     		context.put("ditangguh",ditangguhkan);
    	    		
    	    context.put("viewKertasKeputusan", listViewKertasKeputusan);		
    	    context.put("viewKertas", listViewKertas);
    	    context.put("size", listViewKertas.size());
    	    
    	    context.put("id_mmk", idmmk);
    	       	    
    		//screen
    		vm = screenMMK;
		      
		}//close simpanPenyediaan	
    	
    	else 
    	if("batal_kemaskini".equals(submit)){
    		 
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);   		
    		context.put("viewKertas", listViewKertas);
    		
    		//screen   
    		vm = screenMMK;
    		
		}//close batal kemaskini
    	
    	else 
    	if("viewSenaraiMMK".equals(submit)){
 
           	logic.setDataSek4MMK(idpermohonan);
    		listDataSek4MMK = logic.getSek4MMK();
    		String tarikhBorangA = "";
    		if(listDataSek4MMK.size()!=0){
    			Hashtable q = (Hashtable) listDataSek4MMK.get(0);
    			tarikhBorangA = q.get("tarikh_boranga").toString();
    		}
    		context.put("senaraiMMK", listDataSek4MMK);
    		
    		//validation button hantar
    		context.put("tarikhBorangA", tarikhBorangA);
    		
    		//list mmk
    		listMMK(session,idpermohonan);
    		
    		//get tarikh luput
        	getTarikhLuput(session,idpermohonan);
        	String tarikhL = getTarikhLuput(session,idpermohonan);       	       	
    		context.put("tarikh_luput",tarikhL);
    		  
    		//screen
    	    vm = screenListMMK;
    		
	    }//close command semak
    	
 /*   	else 
    	if("hantar".equals(submit)){
    		
    		if (doPost.equals("true")) {
    			hantar(session);
    		}
  
			listdepan = logic.getListPemohon();
    		
    		context.put("PermohonanList", listdepan);
    		context.put("list_size", listdepan.size());
    		context.put("carianRujukan", "");
			context.put("carianPermohonan", "");
	
			vm = pageDepan;
    	}
 */   	
    	else 
    	if("kemaskini_penyediaan".equals(submit)){
    		
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "yes");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		  
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);    		
    		context.put("viewKertas", listViewKertas);
 
    		//screen
    		vm = screenMMK;
    	
    	}//close kemaskiniPenyediaan
    	
    	else 
    	if("kemaskini_semakan".equals(submit)){
    		
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "yes");
    		context.put("edit_keputusan", "no");
    		
    		//get current date
    		context.put("tarikhSemakan",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		context.put("viewKertas", listViewKertas);
    		
    		int stats = 0;
    		if(listViewKertas.size()!=0){
    			Hashtable lvk = (Hashtable) listViewKertas.get(0);
    			String _stats = lvk.get("status_semakan").toString();
 		   		if(_stats!=""){
 		   			stats = Integer.parseInt(_stats);
 		   		}
    		}
    		
    		if(stats==1)
    		{
    			context.put("select", "");
    			context.put("selectA", "selected");
    			context.put("selectB", "");
    		}
    		else if(stats==2)
    		{
    			context.put("select", "");
    			context.put("selectA", "");
    			context.put("selectB", "selected");
    		}else{
    			context.put("select", "selected");
    			context.put("selectA", "");
    			context.put("selectB", "");
    		}
    		
    	    //screen
    		vm = screenMMK;
    	
    	}//close kemaskiniSemakan
    	
    	else 
    	if("kemaskini_keputusan".equals(submit)){
    		
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "yes");
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
    		
    		String statusKeputusan = "";
    		if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}	
    		
    		if (statusKeputusan.equals("1")){
 				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
     		context.put("diluluskan",diluluskan);
     		context.put("ditolak",ditolak);
     		context.put("ditangguh",ditangguhkan);

			//data
    		context.put("viewKertasKeputusan", listViewKertasKeputusan);
    		context.put("viewKertas", listViewKertas);
    		

    	    //screen    
    		vm = screenMMK;
    		
    	}//close kemaskini keputusan
    	
    	else 
    	if("viewKertasMMK".equals(submit)){
    		 
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		
    		//sub mmk
    		senarai_submmk = modelPembatalan.senarai_submmk(getParam("id_mmk"));
			context.put("senarai_submmk", senarai_submmk);
			
    	 	if(listViewKertas.size()==0){
    	 		
    	 		//default tujuan projek
        		String tujuan = "";
        		if(dataHeader.size()!=0){
        			Hashtable mmk = (Hashtable) dataHeader.get(0);
        			tujuan = mmk.get("tujuan").toString(); 
        		}

        		context.put("tujuanProjek",tujuan);
        		
        		//reset value mmk
        		resetValueMMK(session);
        		
            	context.put("view", "no");
            	
    	 	}else{
    	 		//form validation
        		context.put("view", "yes");
        		context.put("edit_penyediaan", "no");
        		context.put("edit_semakan", "no");
        		context.put("edit_keputusan", "no");
    	 	}   		
    		
    		logic.setDataSek4MMK(idpermohonan);
  	      	listDataSek4MMK = logic.getSek4MMK();
  	      	String tarikhBorangA = "";
  	      	if(listDataSek4MMK.size()!=0){
  	      		Hashtable q = (Hashtable) listDataSek4MMK.get(0);
  	      		tarikhBorangA = q.get("tarikh_boranga").toString();
  	      	}
  		
  	      	//validation button hantar
  	      	context.put("tarikhBorangA",tarikhBorangA);
  	
    		
    		int stats = 0;
    		String ruj_mmk = "";
 		   	if(listViewKertas.size()!=0){
 		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
 		   		String _stats = lvk.get("status_semakan").toString();
 		   		if(_stats!=""){
 		   			stats = Integer.parseInt(_stats);
 		   		}
 		   		id_mmk = lvk.get("id_mmk").toString();
 		   		ruj_mmk = lvk.get("no_rujmmk").toString();
 		   	}
    		
 		   	listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
 		   	String statusKeputusan = "";
 		   	if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}

    		context.put("ruj_mmk", ruj_mmk);
    		context.put("id_mmk", id_mmk);
    		
    		if(stats==1)
    		{
    			context.put("select", "");
    			context.put("selectA", "selected");
    			context.put("selectB", "");
    		}
    		else if(stats==2)
    		{
    			context.put("select", "");
    			context.put("selectA", "");
    			context.put("selectB", "selected");
    		}else{
    			context.put("select", "selected");
    			context.put("selectA", "");
    			context.put("selectB", "");
    		}
    		
    		
    		if (statusKeputusan.equals("1")){
 				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
     		context.put("diluluskan",diluluskan);
     		context.put("ditolak",ditolak);
     		context.put("ditangguh",ditangguhkan);
    		
    		context.put("viewKertasKeputusan", listViewKertasKeputusan);
    		context.put("viewKertas", listViewKertas);    	
    		context.put("size_keputusan", listViewKertasKeputusan.size());

    		//screen
            vm = screenMMK;
	        
		}//close command viewKertasMMK
    	
    	else 
    	if("viewKertas_semakan".equals(submit)){
   		 
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		if(listViewKertas.size()!=0){
 		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
 		   		id_mmk = lvk.get("id_mmk").toString();		   
 		   	}
    		context.put("id_mmk",id_mmk);
    		
    		
    		listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
    		String statusKeputusan = "";
    		if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}

    		if (statusKeputusan.equals("1")){
 				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
     		context.put("diluluskan",diluluskan);
     		context.put("ditolak",ditolak);
     		context.put("ditangguh",ditangguhkan);
	
    		context.put("viewKertasKeputusan", listViewKertasKeputusan);
    		context.put("viewKertas", listViewKertas);
    		context.put("size_keputusan", listViewKertasKeputusan.size());

    		//screen
            vm = screenMMK;
	        
		}//close command viewKertas(semakan)
    	
    	else 
    	if("viewKertas_keputusan".equals(submit)){
      		 
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    		 
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		
    		listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
 		   	String statusKeputusan = "";
 		   	if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}
    		
 		   if (statusKeputusan.equals("1")){
				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
    		context.put("diluluskan",diluluskan);
    		context.put("ditolak",ditolak);
    		context.put("ditangguh",ditangguhkan);

    		context.put("viewKertasKeputusan", listViewKertasKeputusan);
    		context.put("viewKertas", listViewKertas);   		
    		context.put("size_keputusan", listViewKertasKeputusan.size());

    		//screen
            vm = screenMMK;
	        
		}//close command viewKertas(keputusan)
    	
    	else 
    	if("cari".equals(submit))
    	{
    		ListCarian(session,userIdNeg);			
    		listdepan = Seksyen4MMK.getList();
			
			context.put("carianPermohonan", getParam("no_fail").trim());
			context.put("carianRujukan", getParam("no_rujukan").trim());
			context.put("carianStatus", getParam("carianStatus"));
		    context.put("PermohonanList", listdepan);
		    context.put("list_size", listdepan.size());
		    
		    //screen
		    vm = pageDepan;
		    
    	}//close cari
    	
    	else
    	if("SimpanTarikhBorangA".equals(submit))
    	{
    		
    		if (doPost.equals("true")) {
    			SimpanTarikh(session);
    		}
    		
    		//form validation
    		context.put("view", "yes");
    		context.put("edit_penyediaan", "no");
    		context.put("edit_semakan", "no");
    		context.put("edit_keputusan", "no");
    	
    		logic.setDataSek4MMK(idpermohonan);
  	      	listDataSek4MMK = logic.getSek4MMK();
  	      	String tarikhBorangA = "";
  	      	if(listDataSek4MMK.size()!=0){
  	      		Hashtable q = (Hashtable) listDataSek4MMK.get(0);
  	      		tarikhBorangA = q.get("tarikh_boranga").toString();
  	      	}
  		
  	      	//validation button hantar
  	      	context.put("tarikhBorangA",tarikhBorangA);
  	 
    		listViewKertas = Seksyen4MMK.getViewKertas(id_mmk);
    		int stats = 0;
    		String ruj_mmk = "";
 		   	if(listViewKertas.size()!=0){
 		   		Hashtable lvk = (Hashtable) listViewKertas.get(0);
 		   		String _stats = lvk.get("status_semakan").toString();
 		   		if(_stats!=""){
 		   			stats = Integer.parseInt(_stats);
 		   		}
 		   		ruj_mmk = lvk.get("no_rujmmk").toString();
 		   	}
    		
 		   	listViewKertasKeputusan = Seksyen4MMK.getViewKertasKeputusan(id_mmk);
 		   	String statusKeputusan = "";
 		   	if(listViewKertasKeputusan.size()!=0){
		   		Hashtable lvkk = (Hashtable) listViewKertasKeputusan.get(0);
		   		statusKeputusan = lvkk.get("status_keputusan").toString();
		   	}
 		   	
    		context.put("ruj_mmk", ruj_mmk);
    		
    		if(stats==1)
    		{
    			context.put("select", "");
    			context.put("selectA", "selected");
    			context.put("selectB", "");
    		}
    		else if(stats==2)
    		{
    			context.put("select", "");
    			context.put("selectA", "");
    			context.put("selectB", "selected");
    		}else{
    			context.put("select", "selected");
    			context.put("selectA", "");
    			context.put("selectB", "");
    		}
    		
    		if (statusKeputusan.equals("1")){
 				diluluskan = "checked";
				ditolak = "";	
				ditangguhkan = "";
			}else if (statusKeputusan.equals("2")){
				diluluskan = "";
				ditolak = "checked";
				ditangguhkan = "";
			}else if (statusKeputusan.equals("3")){
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "checked";
			}else{
				diluluskan = "";
				ditolak = "";
				ditangguhkan = "";
			}
     		context.put("diluluskan",diluluskan);
     		context.put("ditolak",ditolak);
     		context.put("ditangguh",ditangguhkan);
    		
    		context.put("viewKertasKeputusan", listViewKertasKeputusan);
    		context.put("viewKertas", listViewKertas);   		
    		context.put("size_keputusan", listViewKertasKeputusan.size());

    		//screen
    		vm = screenMMK;;
    		
    	}//simpan tarikh borang a
    	
    	else{
    		
    		listdepan = logic.getListPemohon(userIdNeg);
    		
    		context.put("carianPermohonan", "");
    		context.put("carianRujukan", "");
		    context.put("PermohonanList", listdepan);
		    context.put("list_size", listdepan.size());
		    
		    context.put("selectStatus",HTML.SelectStatusFail("socStatus"));
		    
		    vm = pageDepan;
		    
		}//close else
    	
    	context.put("no_fail", no_fail);
    	context.put("negeriMMK", negeriMMK);
    	context.put("id_fail", id_fail);
    	context.put("id_mmk", id_mmk);
    	context.put("id_permohonan", idpermohonan);
    	context.put("id_status", id_status);
       	context.put("id_suburusanstatusfailppt", id_suburusanstatusfailppt);
    	
    	
    	setupPage(session,action,listdepan);
    	context.put("selectedTab",selectedTab);
        return vm;
        
    }//close public
   
	
//METHOD
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalLuasAmbil(String idpermohonan) throws Exception{
		
		Vector getLuasAmbil = new Vector();
		getLuasAmbil.clear();
		
		String totalLuasAmbil = "";
		
		modelLaporanTanah.setTotalLuasAmbil(idpermohonan,"");
		getLuasAmbil = modelLaporanTanah.getTotalLuasAmbil();
		if(getLuasAmbil.size()!=0){
			Hashtable la = (Hashtable)getLuasAmbil.get(0);
			totalLuasAmbil = (String)la.get("totalLuasAmbil");
		}
		
		context.put("lblLuasKeseluruhan",totalLuasAmbil);
		
		
		Vector getJumlahLot = new Vector();
		getJumlahLot.clear();
		String totaLLot = "";
		modelLaporanTanah.setTotalLot(idpermohonan,"");
		getJumlahLot = modelLaporanTanah.getTotalLot();
		if(getJumlahLot.size()!=0){
			Hashtable ll = (Hashtable)getJumlahLot.get(0);
			totaLLot = (String)ll.get("totaLLot");
		}
		
		
		context.put("totaLLot",totaLLot);
		
		
		

		
	}//close getTotalLuasAmbil
	
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
			et.setEmail("seksyen4",emelPengarah,"hantarUntukSemakan",nofail,nama_projek,tarikh_permohonan,nama_kementerian);
		//}
		
		//jenis email
		// - hantar pengesahan (pt - pengarah)
		// - hantar untuk diagihankan
		// - hantar untuk semakan mmk (pt - pengarah)
		
	}//close sendEmail
	
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
	    
	}//close nameAndId
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1424");
		
	}//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings("unchecked")
	private void SimpanTarikh(HttpSession session) throws Exception{
	    	
		 Hashtable h = new Hashtable();
		 
		 String id_permohonan = getParam("id_permohonan");
		 h.put("id_permohonan", id_permohonan);
		 h.put("id_user", session.getAttribute("_ekptg_user_id"));
		 Seksyen4MMK.simpanTarikh(h);
		
	}//close SimpanTarikh
	 
	@SuppressWarnings("unchecked")
	private void listMMK(HttpSession session,String idpermohonan) throws Exception{
	    	
		logic.setDataListKertas(idpermohonan);
	    Vector listDataListKertas = logic.getListKertas();		
	    context.put("senaraiKertas", listDataListKertas);
	    context.put("saiz_list", listDataListKertas.size());
		
	}//close listMMK
	
    private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
    	String carianPermohonan = getParam("no_fail");
    	String carianRujukan = getParam("no_rujukan");
    	String carianStatus = getParam("carianStatus");
    	
    	Seksyen4MMK.setList(carianPermohonan,carianRujukan,carianStatus,userIdNeg);
	
    }//close listcarian
    
    @SuppressWarnings({ "unchecked", "static-access" })
	private String getTarikhLuput(HttpSession session,String idpermohonan) throws Exception{
	    	
    	//get latest id mmk
		Vector getidmmk = logic.getIdMMK(idpermohonan);
		String idmmk = "";
		if(getidmmk.size()!=0){
			Hashtable im = (Hashtable)getidmmk.get(0);
			idmmk = (String)im.get("id_mmk");
		}
		
		//get tarikh luput
		logic.setTarikhLuput(idmmk);
		Vector getMaxTarikhLuput = logic.getTarikhLuput();
		String tarikh_luput = "";
		if(getMaxTarikhLuput.size()!=0){
			Hashtable gmt = (Hashtable) getMaxTarikhLuput.get(0);
			tarikh_luput = (String)gmt.get("tarikh_luput");
		}
		
		return tarikh_luput;
		
	}//close getTarikhLuput
    
    private void resetValueMMK(HttpSession session) throws Exception{
    	
    	context.put("id_mmk", "");
    	context.put("noRujukan", "");
    	context.put("tujuan", "");
    	context.put("implikasi", "");
    	context.put("latarbelakang", "");
    	context.put("kesimpulan", "");
    	context.put("asas_pertimbangan", "");
    	context.put("syor", "");
    	context.put("tarikh_mmk", "");
    	context.put("tarikh_semak", "");
    	context.put("ulasan", "");
    	context.put("flag_semak", "");
    	 
    }//close resetValueMMK

    @SuppressWarnings("unchecked")
	private void add(HttpSession session) throws Exception{
	    
    	long idNewMMK_ = DB.getNextID("TBLPPTMMK_SEQ");  
		String id_mmk = Long.toString(idNewMMK_);
			
    	Hashtable h = new Hashtable();
	
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	
    	// TUJUAN
    	String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
		if (txtUlasanTUJUAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"TUJUAN",id_user);
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
		
		// BUTIRASAS1
    	String[] txtUlasanBUTIRASAS1_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS1_MAIN");
		if (txtUlasanBUTIRASAS1_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS1_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS1_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS1",id_user);
			}
		}
		
		// BUTIRASAS2
    	String[] txtUlasanBUTIRASAS2_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS2_MAIN");
		if (txtUlasanBUTIRASAS2_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS2_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS2_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS2",id_user);
			}
		}
		
		
		// BUTIRASAS3
    	String[] txtUlasanBUTIRASAS3_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS3_MAIN");
		if (txtUlasanBUTIRASAS3_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS3_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS3_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS3",id_user);
			}
		}
		
		// BUTIRASAS4
    	String[] txtUlasanBUTIRASAS4_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS4_MAIN");
		if (txtUlasanBUTIRASAS4_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS4_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS4_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS4",id_user);
			}
		}
		
		
		// BUTIRASAS5
    	String[] txtUlasanBUTIRASAS5_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS5_MAIN");
		if (txtUlasanBUTIRASAS5_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS5_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS5_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS5",id_user);
			}
		}
		
		
		// BUTIRASAS6
    	String[] txtUlasanBUTIRASAS6_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS6_MAIN");
		if (txtUlasanBUTIRASAS6_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS6_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS6_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS6",id_user);
			}
		}
		
		// BUTIRASAS7
    	String[] txtUlasanBUTIRASAS7_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS7_MAIN");
		if (txtUlasanBUTIRASAS7_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS7_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS7_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS7",id_user);
			}
		}
		
		
		// BUTIRASAS8
    	String[] txtUlasanBUTIRASAS8_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS8_MAIN");
		if (txtUlasanBUTIRASAS8_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS8_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS8_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS8",id_user);
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
		
		// BUTIRTANAH
    	String[] txtUlasanBUTIRTANAH_MAIN = this.request.getParameterValues("txtUlasanBUTIRTANAH_MAIN");
		if (txtUlasanBUTIRTANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRTANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRTANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRTANAH",id_user);
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
		
		//WANGPERUNTUKAN
		String[] txtUlasanWANGPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanWANGPERUNTUKAN_MAIN");
		if (txtUlasanWANGPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanWANGPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanWANGPERUNTUKAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"WANGPERUNTUKAN",id_user);
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
		
		//PERAKUANPT
		String[] txtUlasanPERAKUANPT_MAIN = this.request.getParameterValues("txtUlasanPERAKUANPT_MAIN");
		if (txtUlasanPERAKUANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERAKUANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERAKUANPT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERAKUANPT",id_user);
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
		
		
		//LAPORANTANAH1
		String[] txtUlasanLAPORANTANAH1_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAH1_MAIN");
		if (txtUlasanLAPORANTANAH1_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAH1_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAH1_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTANAH1",id_user);
			}
		}
		

		//LAPORANTANAHSUB
		String[] txtUlasanLAPORANTANAHSUB_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAHSUB_MAIN");
		if (txtUlasanLAPORANTANAHSUB_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAHSUB_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAHSUB_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTANAHSUB",id_user);
			}
		}		
		
		//LAPORAN TEKNIKAL
		String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
		if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTEKNIKAL",id_user);
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
		
		//ANGGARANPAMPASAN
		String[] txtUlasanANGGARANPAMPASAN_MAIN = this.request.getParameterValues("txtUlasanANGGARANPAMPASAN_MAIN");
		if (txtUlasanANGGARANPAMPASAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANPAMPASAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANPAMPASAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ANGGARANPAMPASAN",id_user);
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
		
		//PERIHALPERMOHONAN
		String[] txtUlasanPERIHALPERMOHONAN_MAIN = this.request.getParameterValues("txtUlasanPERIHALPERMOHONAN_MAIN");
		if (txtUlasanPERIHALPERMOHONAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPERMOHONAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPERMOHONAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALPERMOHONAN",id_user);
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
		
		//KEPUTUSAN
		String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
		if (txtUlasanKEPUTUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KEPUTUSAN",id_user);
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
		
		//NILAIAN TANAH
		String[] txtUlasanNILAITANAH_MAIN = this.request.getParameterValues("txtUlasanNILAITANAH_MAIN");
		if (txtUlasanNILAITANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanNILAITANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanNILAITANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"NILAITANAH",id_user);
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
		
		//PENDAPAT
		String[] txtUlasanPENDAPAT_MAIN = this.request.getParameterValues("txtUlasanPENDAPAT_MAIN");
		if (txtUlasanPENDAPAT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPENDAPAT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPENDAPAT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PENDAPAT",id_user);
			}
		}
		
		//KELULUSAN
		String[] txtUlasanKELULUSAN_MAIN = this.request.getParameterValues("txtUlasanKELULUSAN_MAIN");
		if (txtUlasanKELULUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKELULUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKELULUSAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KELULUSAN",id_user);
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
		
		
		
		//PERIHALAP
		String[] txtUlasanPERIHALAP_MAIN = this.request.getParameterValues("txtUlasanPERIHALAP_MAIN");
		if (txtUlasanPERIHALAP_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALAP_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALAP_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALAP",id_user);
			}
		}
		
	    h.put("id_permohonan", getParam("id_permohonan"));
//	    h.put("txttujuan", getParam("txttujuan"));
//	    h.put("txtlatarbelakang", getParam("txtlatarbelakang"));
//	    h.put("txtlaporan", getParam("txtlaporan"));
//	    h.put("txtsyor", getParam("txtsyor"));	    
//	    h.put("txtasaspertimbangan", getParam("txtasaspertimbangan"));
//	    h.put("txtimplikasi", getParam("txtimplikasi"));
//	    h.put("txtkesimpulan", getParam("txtkesimpulan"));	    
//	    h.put("txtnilaian", getParam("txtnilaian"));
//	    h.put("txtulasan", getParam("txtulasan"));
//	    h.put("txtkeputusan", getParam("txtkeputusan"));
//	    h.put("txtkedudukan", getParam("txtkedudukan"));
//	    h.put("txtperuntukan", getParam("txtperuntukan"));
//	    h.put("txtperakuan", getParam("txtperakuan"));
//	    h.put("txtpandangan", getParam("txtpandangan"));
//	    h.put("txtperihalpohon", getParam("txtperihalpohon"));
//	    h.put("txtanggaran", getParam("txtanggaran"));
//	    h.put("txtulasanjt", getParam("txtulasanjt"));
//	    h.put("txtjawatankuasa", getParam("txtjawatankuasa"));
//	    h.put("txthallain", getParam("txthallain"));
	    
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
		
	    h.put("idNewMMK", id_mmk);
	    
	    h.put("id_user", id_user);
	    
	    Seksyen4MMK.add(h);
	   
	  }//close add
    
    private void delete(HttpSession session) throws Exception
	  {
    	
    	String id_mmk = getParam("id_mmk");    	
    	Seksyen4MMK.delete(id_mmk);
    	
    	modelPembatalan.delete_subMMK(id_mmk);
    	
	  }
    
    @SuppressWarnings({ "unchecked", "static-access" })
	private void update_item_keputusan(HttpSession session,String idstatus,String id_fail,String id_suburusanstatusfailppt) throws Exception
    {
    	Hashtable h = new Hashtable();
    	
    	h.put("id_permohonan", getParam("id_permohonan"));	
    	h.put("id_fail", id_fail);	
    	
    	//h.put("tarikhLuput", getParam("tarikhLuput"));
    	h.put("id_mmk",getParam("id_mmk"));
    	h.put("txtRujMMK", getParam("txtRujMMK"));
    	h.put("txdTarikhMMK", getParam("txdTarikhMasuk_edit"));
    	
	    h.put("id_mmk_keputusan",getParam("id_mmk_keputusan"));
	    //h.put("tarikh_hantar", getParam("txdTarikhHantar"));
	    //h.put("tarikh_keputusan", getParam("txdTarikhKeputusan"));
	    h.put("tarikh_terima", getParam("txdTarikhTerima2"));
	    h.put("ulasan", getParam("txtUlasan2"));
	    h.put("keputusan", getParam("sorKeputusan"));
	  
	    h.put("id_user", session.getAttribute("_ekptg_user_id"));

	    //h.put("tarikhWarta",getParam("txdTarikhKeputusan"));	    

	    if(idstatus.equals("26")){
	    	 Seksyen4MMK.hantar(h);
	    	 modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1425");
	    }

	    Seksyen4MMK.add_keputusan(h);
    }
    
    @SuppressWarnings("unchecked")
	private void update_penyediaan(HttpSession session) throws Exception
	  {
	    
    	Hashtable h = new Hashtable();
	    
    	String id_mmk = getParam("id_mmk");
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	
	    h.put("id_mmk", id_mmk);
	    
//	    h.put("txttujuan", getParam("txttujuan"));
//	    h.put("txtlatarbelakang", getParam("txtlatarbelakang"));
//	    h.put("txtlaporan", getParam("txtlaporan"));
//	    h.put("txtsyor", getParam("txtsyor"));	    
//	    h.put("txtasaspertimbangan", getParam("txtasaspertimbangan"));
//	    h.put("txtimplikasi", getParam("txtimplikasi"));
//	    h.put("txtkesimpulan", getParam("txtkesimpulan"));
//	    h.put("txtnilaian", getParam("txtnilaian"));
//	    h.put("txtulasan", getParam("txtulasan"));
//	    h.put("txtkeputusan", getParam("txtkeputusan"));
//	    h.put("txtkedudukan", getParam("txtkedudukan"));
//	    h.put("txtperuntukan", getParam("txtperuntukan"));
//	    h.put("txtperakuan", getParam("txtperakuan"));
//	    h.put("txtperihalpohon", getParam("txtperihalpohon"));
//	    h.put("txtanggaran", getParam("txtanggaran"));
//	    h.put("txtulasanjt", getParam("txtulasanjt"));
//	    h.put("txtjawatankuasa", getParam("txtjawatankuasa"));
//	    h.put("txtpandangan", getParam("txtpandangan"));
//	    h.put("txthallain", getParam("txthallain"));
	    
	    h.put("txtJenisPenggunaan", getParam("txtJenisPenggunaan"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtKedudukan", getParam("txtKedudukan"));
		h.put("txtKeadaan", getParam("txtKeadaan"));
		
		 h.put("txtTajuk", getParam("txtTajuk"));
			h.put("txtSidang", getParam("txtSidang"));
			h.put("txtTempatSidang", getParam("txtTempatSidang"));
			h.put("txtTarikhSidang", getParam("txtTarikhSidang"));
			h.put("txtMasaSidang", getParam("txtMasaSidang"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
		
	    h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    
	    Seksyen4MMK.update_penyediaan(h);
	    
	    
	    modelPembatalan.delete_subMMK(id_mmk);
	    
	    // TUJUAN
    	String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
		if (txtUlasanTUJUAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"TUJUAN",id_user);
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
		
		
		// BUTIRASAS1
    	String[] txtUlasanBUTIRASAS1_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS1_MAIN");
		if (txtUlasanBUTIRASAS1_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS1_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS1_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS1",id_user);
			}
		}
		
		// BUTIRASAS2
    	String[] txtUlasanBUTIRASAS2_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS2_MAIN");
		if (txtUlasanBUTIRASAS2_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS2_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS2_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS2",id_user);
			}
		}
		
		// BUTIRASAS3
    	String[] txtUlasanBUTIRASAS3_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS3_MAIN");
		if (txtUlasanBUTIRASAS3_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS3_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS3_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS3",id_user);
			}
		}
		
		
		// BUTIRASAS4
    	String[] txtUlasanBUTIRASAS4_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS4_MAIN");
		if (txtUlasanBUTIRASAS4_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS4_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS4_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS4",id_user);
			}
		}
		
		
		// BUTIRASAS5
    	String[] txtUlasanBUTIRASAS5_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS5_MAIN");
		if (txtUlasanBUTIRASAS5_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS5_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS5_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS5",id_user);
			}
		}
		
		// BUTIRASAS6
    	String[] txtUlasanBUTIRASAS6_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS6_MAIN");
		if (txtUlasanBUTIRASAS6_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS6_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS6_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS6",id_user);
			}
		}
		
		
		// BUTIRASAS7
    	String[] txtUlasanBUTIRASAS7_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS7_MAIN");
		if (txtUlasanBUTIRASAS7_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS7_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS7_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS7",id_user);
			}
		}
		
		
		// BUTIRASAS8
    	String[] txtUlasanBUTIRASAS8_MAIN = this.request.getParameterValues("txtUlasanBUTIRASAS8_MAIN");
		if (txtUlasanBUTIRASAS8_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanBUTIRASAS8_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanBUTIRASAS8_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRASAS8",id_user);
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
		
		//WANGPERUNTUKAN
		String[] txtUlasanWANGPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanWANGPERUNTUKAN_MAIN");
		if (txtUlasanWANGPERUNTUKAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanWANGPERUNTUKAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanWANGPERUNTUKAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"WANGPERUNTUKAN",id_user);
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
		
		//PERAKUANPT
		String[] txtUlasanPERAKUANPT_MAIN = this.request.getParameterValues("txtUlasanPERAKUANPT_MAIN");
		if (txtUlasanPERAKUANPT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERAKUANPT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERAKUANPT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERAKUANPT",id_user);
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
		
		//BUTIRTANAH
				String[] txtUlasanBUTIRTANAH_MAIN = this.request.getParameterValues("txtUlasanBUTIRTANAH_MAIN");
				if (txtUlasanBUTIRTANAH_MAIN != null ) 
				{
					for (int t = 0; t < txtUlasanBUTIRTANAH_MAIN.length; t++){    			    						
							modelPembatalan.simpan_subMMK(txtUlasanBUTIRTANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"BUTIRTANAH",id_user);
					}
				}
		
		//LAPORANTANAH1
		String[] txtUlasanLAPORANTANAH1_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAH1_MAIN");
		if (txtUlasanLAPORANTANAH1_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAH1_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAH1_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTANAH1",id_user);
			}
		}
		
		
		//LAPORANTANAHSUB
		String[] txtUlasanLAPORANTANAHSUB_MAIN = this.request.getParameterValues("txtUlasanLAPORANTANAHSUB_MAIN");
		if (txtUlasanLAPORANTANAHSUB_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTANAHSUB_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTANAHSUB_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTANAHSUB",id_user);
			}
		}
		
		//LAPORAN TEKNIKAL
		String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
		if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",Long.parseLong(id_mmk),"LAPORANTEKNIKAL",id_user);
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
		
		//ANGGARANPAMPASAN
		String[] txtUlasanANGGARANPAMPASAN_MAIN = this.request.getParameterValues("txtUlasanANGGARANPAMPASAN_MAIN");
		if (txtUlasanANGGARANPAMPASAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanANGGARANPAMPASAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanANGGARANPAMPASAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"ANGGARANPAMPASAN",id_user);
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
		
		//PERIHALPERMOHONAN
		String[] txtUlasanPERIHALPERMOHONAN_MAIN = this.request.getParameterValues("txtUlasanPERIHALPERMOHONAN_MAIN");
		if (txtUlasanPERIHALPERMOHONAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALPERMOHONAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALPERMOHONAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALPERMOHONAN",id_user);
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
		
		//KEPUTUSAN
		String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
		if (txtUlasanKEPUTUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KEPUTUSAN",id_user);
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
		
		//NILAIAN TANAH
		String[] txtUlasanNILAITANAH_MAIN = this.request.getParameterValues("txtUlasanNILAITANAH_MAIN");
		if (txtUlasanNILAITANAH_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanNILAITANAH_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanNILAITANAH_MAIN[t],"MAIN",Long.parseLong(id_mmk),"NILAITANAH",id_user);
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
		
		//PENDAPAT
		String[] txtUlasanPENDAPAT_MAIN = this.request.getParameterValues("txtUlasanPENDAPAT_MAIN");
		if (txtUlasanPENDAPAT_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPENDAPAT_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPENDAPAT_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PENDAPAT",id_user);
			}
		}
		
		//KELULUSAN
		String[] txtUlasanKELULUSAN_MAIN = this.request.getParameterValues("txtUlasanKELULUSAN_MAIN");
		if (txtUlasanKELULUSAN_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanKELULUSAN_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanKELULUSAN_MAIN[t],"MAIN",Long.parseLong(id_mmk),"KELULUSAN",id_user);
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
		
		//PERIHALAP
		String[] txtUlasanPERIHALAP_MAIN = this.request.getParameterValues("txtUlasanPERIHALAP_MAIN");
		if (txtUlasanPERIHALAP_MAIN != null ) 
		{
			for (int t = 0; t < txtUlasanPERIHALAP_MAIN.length; t++){    			    						
					modelPembatalan.simpan_subMMK(txtUlasanPERIHALAP_MAIN[t],"MAIN",Long.parseLong(id_mmk),"PERIHALAP",id_user);
			}
		}
		
	}//CLOSE UPDATE
    
    
    @SuppressWarnings("unchecked")
	private void hantarPengesahan(HttpSession session) throws Exception{
    	
	    Hashtable h = new Hashtable();
	    
	    h.put("id_mmk", getParam("id_mmk"));
	    h.put("id_user", session.getAttribute("_ekptg_user_id"));

	    Seksyen4MMK.hantarPengesahan(h);  
	    
	}//close hantarPengesahan
    
    @SuppressWarnings("unchecked")
	private void update_item_semakan(HttpSession session) throws Exception
	  {
	    Hashtable h = new Hashtable();
	    
	    h.put("id_mmk", getParam("id_mmk"));
	    h.put("tarikh_semak", getParam("txdTarikhSemak"));
	    h.put("ulasan", getParam("txtUlasan"));
	    h.put("id_pegawai", session.getAttribute("_ekptg_user_id"));
	    h.put("status_semakan", getParam("socKeputusanSemak"));
	    h.put("tarikh_hantar", getParam("txdTarikhHantar"));
	    
	    h.put("role", session.getAttribute("_portal_role"));
	    
	    Seksyen4MMK.update_item_semakan(h);
	  }
    
/*    @SuppressWarnings("unchecked")
	private void hantar(HttpSession session) throws Exception
	  {
	    Hashtable h = new Hashtable();
	    
	    String idpermohonan = getParam("id_permohonan");
	    
	    logic.setDataListKertas(idpermohonan);
		Vector listDataListKertas = logic.getListKertas();
		String tarikhWarta = "";
	    if(listDataListKertas.size()!=0){
	    	Hashtable ldlk = (Hashtable)listDataListKertas.get(0);
	    	tarikhWarta = ldlk.get("tarikh_keputusan").toString();
	    }
		
	    h.put("tarikhWarta",tarikhWarta);	    
	    h.put("id_permohonan",getParam("id_permohonan"));	   
	    h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    
	    Seksyen4MMK.hantar(h);
	  }
*/
    
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
			this.context.put("PermohonanList",paging.getPage(page));
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
    
}//close class