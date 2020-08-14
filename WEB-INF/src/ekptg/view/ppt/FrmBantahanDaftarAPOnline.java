package ekptg.view.ppt;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanAgensiDaftar;
import ekptg.model.ppt.BantahanAgensiDaftarOnline;
import ekptg.model.ppt.BantahanAgensiOnlineOperations;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.BantahanDaftarOperations;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmPermohonanUPTOnlineData;
import ekptg.model.ppt.PPTHeader;
import ekptg.view.ppt.email.EmailOnline;

public class FrmBantahanDaftarAPOnline extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmBantahanDaftarAPOnline.class);
	
	// MODEL INTERNAL
	BantahanDaftar modelPB = new BantahanDaftar();
	BantahanAgensiDaftar model = new BantahanAgensiDaftar();		
	BantahanDaftarOperations modelOperations = new BantahanDaftarOperations();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	// MODEL ONLINE
	BantahanAgensiDaftarOnline modelOnline = new BantahanAgensiDaftarOnline();
	BantahanAgensiOnlineOperations modelOnlineOperations = new BantahanAgensiOnlineOperations();
	FrmPermohonanUPTOnlineData modelUPTOnline = new FrmPermohonanUPTOnlineData();
	
	
	//DECLARATION
	String checkedsbcBantahan1 = "";
	String checkedsbcBantahan2 = "";
	String checkedsbcBantahan3 = "";
	String checkedsbcBantahan4 = "";
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public String doTemplate2() throws Exception
    {
		HttpSession session = this.request.getSession(); 				
    	//get user login detail
		String usid = "";  		
   		usid = (String)session.getAttribute("_ekptg_user_id");  		
    	String vm = "";  
    	String noLOT = "";
    	String idpegawai = "";
    	String flag_semakan_online = "";
    	
    	Vector listHeader = null;
    	Vector listC = null;
    	Vector listE = null;
    	Vector listH = null;
    	Vector listJ = null;   	
    	Vector maklumatBantahan =null;  
    	
    	Vector dataNamaPengarah = new Vector();
    	Vector listMaklumatTanah = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector listPageDepan = new Vector();
    	Vector listMaklumatBantahanAgensi = new Vector();
    	
    	String portal_role = (String) session.getAttribute("_portal_role");
   
    	dataNamaPengarah.clear();
    	listMaklumatTanah.clear();
    	listPageDepan.clear();
    	listMaklumatBantahanAgensi.clear();

    	//DEFAULT LIST
		listPageDepan = modelOnline.getListPemohon(usid,portal_role);	
		
    	String doPost = (String)session.getAttribute("doPost");
    	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
    	String submit = getParam("command");
    	myLogger.info("SUBMIT :: "+submit);
    	this.context.put("Util",new lebah.util.Util());	// UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)
   		
    	String location = getParam("location");
    	context.put("location",location);   
    	
    	String point = getParam("point");
    	context.put("point",point);  
    	
    	String id_fail = getParam("id_fail");	
   		context.put("id_fail", id_fail);
   		
   		//get id jawatan
    	String id_jawatan_user = getJawatan(usid);
    	context.put("id_jawatan_user",id_jawatan_user);
    	
   		String id_permohonan = getParam("id_permohonan");
   		context.put("id_permohonan", id_permohonan);
   		
   		String id_hakmilik = getParam("id_hakmilik");
   		context.put("id_hakmilik", id_hakmilik);
   		
//   		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
//   		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
   		
   		String status_bantahan = getParam("status_bantahan");
   		context.put("status_bantahan", status_bantahan);
   		
   		String id_bantahan = getParam("id_bantahan");
   		context.put("id_bantahan", "id_bantahan");
   		
   		myLogger.info("id_permohonan :: "+id_permohonan);
//   		myLogger.info("id_hakmilik :: "+id_hakmilik);
//   		myLogger.info("id_pihakberkepentingan :: "+id_pihakberkepentingan);
//   		myLogger.info("id_hakmilikpb :: "+id_hakmilikpb);
//   		myLogger.info("status_bantahan :: "+status_bantahan);
//   		myLogger.info("id_bantahan :: "+id_bantahan);
   		
		listHeader = modelPB.getHeader(id_fail,id_permohonan); 
		context.put("Header", listHeader);
		String idNegeriMhn = "";
		if (listHeader.size()!=0){
			Hashtable h = (Hashtable) listHeader.get(0);
			idNegeriMhn = h.get("id_negeri").toString();
			context.put("idNegeriMhn", idNegeriMhn);
		}
	
		Hashtable statusFail = modelPB.getStatusFail(id_permohonan);	
		this.context.put("statusFail", statusFail);
		
		String selectedtab = getParam("selectedtab");
		if ("".equals(selectedtab)){
			selectedtab = "0";
		}
		this.context.put("selectedtab", selectedtab);	
		
		//CLEARKAN FORM
		String key = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
		key = (String) allparam.nextElement();
		value = request.getParameter(key);
		this.context.put(key, value);
		}
		//END
		
		// HEADER
		String negeriMMK = "";
		String namaProjek = "";
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");
			namaProjek = dh.get("tujuan").toString();
		}
 		
		// get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		modelUPT.setDataSuburusanHakmilik(id_hakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if(dataSuburusanHakmilik.size()!=0){
			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
		} 		
		
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(id_permohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}		
		
		//default data
		context.put("showPopupHantar", "no");
		
    	//screen jsp
		String skrinListPermohonanAP = "app/ppt/frmBantahanDaftarAPOnline.jsp";
		String listHMscreen = "app/ppt/frmBantahanListHakmilikAPOnline.jsp";
    	String skrinDaftarOnline = "app/ppt/frmBantahanDaftarAPOnlinePortal.jsp";
		String screenSemakan = "app/ppt/frmBantahanSenaraiSemakanOnline.jsp";
    	
    	if("screenBorangN".equals(submit)){ 
    		
    		// GET DATA BANTAHAN AGENSI
    		modelOnline.setMaklumatAgensi_DgnBantahan(id_hakmilik);
     		listMaklumatBantahanAgensi = modelOnline.getMaklumatAgensi_DgnBantahan();
     		context.put("saiz_listTanah", listMaklumatBantahanAgensi.size());	
     		
     		//GETHAKMILIK
    		getValueHakmilik(id_hakmilik,usid);
    		
     		//NEW FORM
     		if(listMaklumatBantahanAgensi.size()==0){
     			
        		//GET CURRENT DATE
        		context.put("txdMohon",lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));
        		
        		//CLEAR
        		context.put("clearForm", "yes");
        		if(portal_role.equals("online_kjpagensi")){
        			context.put("mode", "disabled");
        		}else{
        			context.put("mode", "");
        		}
        			
        		context.put("button", "add");
        		context.put("flag", "");
        		
        		
     		//VIEW FORM	
     		}else{
     			
     			Hashtable a = (Hashtable) listMaklumatBantahanAgensi.get(0);
     			id_bantahan = (String)a.get("id_bantahan");
     			
     			context.put("id_bantahan", id_bantahan);
     			
     			//GET DATA BANTAHAN
         		getDataBantahan(id_permohonan,id_bantahan);
         		
         		maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
        		if (maklumatBantahan.size()!=0 ){				
        			Hashtable tx = (Hashtable) maklumatBantahan.get(0);
        			flag_semakan_online = (String)tx.get("flag_semakan_online");
        		}
        		
        		//CLEAR
        		context.put("clearForm", "");
        		context.put("mode", "disabled");
        		context.put("button", "view");
        		context.put("flag", "semak");
     		}
     		
    		vm = skrinDaftarOnline; 
    		
    	}
    	else if("doChangeNegeri".equals(submit)){  		
    		
    		String id_negeri = getParam("socNegeri");
    		context.put("id_negeri", id_negeri);
    	 		
    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),"style=width:300px onChange=\"doChangeNegeri();\""));
    		
    		if(id_negeri!=""){
    			context.put("selectBandar",HTML.SelectBandarByNegeri(id_negeri,"socBandar",Utils.parseLong(id_negeri),"style=width:300px"));
    		}else{
    			context.put("selectBandar",HTML.SelectBandar("socBandar",null,"style=width:300px"));
    		}    		
    		
    		vm = skrinDaftarOnline;
    		

    	}
    	else if("seterusnya".equals(submit)){
    	
    		vm = screenSemakan;
    	}
    	else if("sahkan_permohonan".equals(submit)){
    		
    		context.put("showPopupHantar", "yes");
    		
			// UPDATE FLAG ONLINE
			updateFlagOnline(id_bantahan,usid,id_hakmilik);
			
			// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
			modelOnline.setMaklumatAgensi_DgnBantahan(id_hakmilik);
			listMaklumatTanah = modelOnline.getMaklumatAgensi_DgnBantahan();
			context.put("saiz_listTanah", listMaklumatTanah.size());	
     		if(listMaklumatTanah.size()!=0){
     			Hashtable a = (Hashtable) listMaklumatTanah.get(0);
         		id_bantahan = (String)a.get("id_bantahan");
     		}
     	
     		context.put("id_bantahan", id_bantahan);
    		
			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
			context.put("getMaklumatBantahan",maklumatBantahan );
			String flag_online = "";
			String id_status_bantahan = "";
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");								
				flag_online = (String)b.get("flag_online");
				id_status_bantahan = (String)b.get("status_bantahan_ap");
				
				if (flag_penerima_pampasan.equals("Y")){
					setValueBantahanTerhadap("checked","","","");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")){
					setValueBantahanTerhadap("","checked","","");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")){
					setValueBantahanTerhadap("","","checked","");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")){
					setValueBantahanTerhadap("","","","checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}
			
			context.put("id_status_bantahan",id_status_bantahan);
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnline.getHakmilikPortal(id_hakmilik);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
    		String nama_kementerian = (String) getHakmilik.get("nama_kementerian");
    		String nama_agensi = (String) getHakmilik.get("nama_agensi");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2= (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		String id_kementerian = (String) getHakmilik.get("id_kementerian");
    		String id_agensi = (String) getHakmilik.get("id_agensi");

    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_kementerian);
    		context.put("txtNamaAgensi", nama_agensi);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
    		context.put("id_negeri", id_negeri);
    		context.put("id_kementerian", id_kementerian);
    		context.put("id_agensi", id_agensi);    		

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
    		
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");
    		
    		if(flag_online.equals("1")){
    			context.put("flagOnline", "1");
    		}else{
    			context.put("flagOnline", "");
    		}
			
    		vm = skrinDaftarOnline;
    		
    	}else if("add_bantahanOnline".equals(submit)){	// FASA 1
    		    		
			if (doPost.equals("true")){
				
				// INSERT TBLPPTBANTAHAN & INSERT TBLPPTSUBURUSANSTATUSBANTAHAN & UPDATE TBLPPTHAKMILIKPB				
        		
				String result = "";
            	//simpan data
                result = daftarBantahan(usid);
            	            	
            	context.put("id_bantahan", result);
            	
            	id_bantahan = result;
				
				// UPDATE TBLPPTPERMOHONAN
				updateStatusDalamProses(id_permohonan,usid);
				
				// UPDATE TBLPPTSUBURUSANHAKMILIK
				updateSuburusanHakmilik(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);
				
				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);
				
				// UPDATE TBLPPTRUJSUBURUSANSTATUSBANTAHAN
				updateSuburusanStatusBantahan(session,usid,id_bantahan,id_permohonan,id_hakmilik,id_fail,id_suburusanstatusfailppt);
			}
			
			//GET NO SIASATAN
			listH = model.getNoSiasatanAP(id_permohonan,id_hakmilik);
			context.put("getNoSiasatan", listH);
			String id_siasatan = "";
			if(listH.size()!=0){
				Hashtable b = (Hashtable) listH.get(0);
				id_siasatan = (String)b.get("id_siasatan");	
				context.put("id_siasatan", id_siasatan);
			}			
			//END
    		
    		//GET MAX NO WARTA
    		listJ = model.getNoWarta(id_permohonan);
    		context.put("getNoSiasatan", listJ);
    		String id_warta = "";
    		if(listJ.size()!=0){
    			Hashtable e = (Hashtable) listJ.get(0);
    			id_warta = e.get("id_warta").toString();	
    		}		
    		//END

    		
			maklumatBantahan = model.getMaklumatBantahanAP(id_permohonan,id_hakmilik,id_siasatan, id_warta);
			context.put("getMaklumatBantahan",maklumatBantahan );
			String flag_online = "";
			String jenis_pembantah = "";
			String flag_penerima_pampasan = "";
			String flag_bahagian_pampasan = "";
			String flag_ukur_luas = "";
			String flag_pampasan = "";
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				jenis_pembantah = (String)b.get("jenis_pembantah");
				flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				flag_ukur_luas = (String)b.get("flag_ukur_luas");
				flag_pampasan = (String)b.get("flag_pampasan");	
				flag_online = (String)b.get("flag_online");
				flag_semakan_online = (String)b.get("FLAG_SEMAKAN_ONLINE");
			}
			
			
				myLogger.info("flag Online :: "+flag_online);
				
				if (jenis_pembantah.equals("1")){
					setValueJenisPembantah("1","");
				}else{
					setValueJenisPembantah("","2");
				}
				
				if (flag_penerima_pampasan.equals("Y")){
					setValueBantahanTerhadap("checked","","","");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")){
					setValueBantahanTerhadap("","checked","","");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")){
					setValueBantahanTerhadap("","","checked","");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")){
					setValueBantahanTerhadap("","","","checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnline.getHakmilikPortal(id_hakmilik);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
//    		String nama_pb = (String) getHakmilik.get("nama_pb");
    		String nama_kementerian = (String) getHakmilik.get("nama_kementerian");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2= (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
//    		String id_bandar = (String) getHakmilik.get("id_bandar");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		
    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_kementerian);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
//    		context.put("id_bandar", id_bandar);
    		context.put("id_negeri", id_negeri);

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
//    		context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"style=width:300px disabled"));
    		
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");
    		
    		if (flag_online.equals("1") ){
    			context.put("flagOnline", "1");
    		}else{
    			context.put("flagOnline", "");
    		}
    		
    		vm = skrinDaftarOnline;
    		
    	}else if("kemaskiniBantahan".equals(submit)){
    		
    		// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
    		modelOnline.setMaklumatAgensi_DgnBantahan(id_hakmilik);
     		listMaklumatTanah = modelOnline.getMaklumatAgensi_DgnBantahan();
     		context.put("saiz_listTanah", listMaklumatTanah.size());	
     		Hashtable a = (Hashtable) listMaklumatTanah.get(0);
     		id_bantahan = (String)a.get("id_bantahan");
     		context.put("id_bantahan", id_bantahan);
    		
			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
			context.put("getMaklumatBantahan",maklumatBantahan );
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");								
				String flag_online = (String)b.get("flag_online");
				
				if (flag_penerima_pampasan.equals("Y")){
					setValueBantahanTerhadap("checked","","","");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")){
					setValueBantahanTerhadap("","checked","","");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")){
					setValueBantahanTerhadap("","","checked","");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")){
					setValueBantahanTerhadap("","","","checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnline.getHakmilikPortal(id_hakmilik);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
    		String nama_kementerian = (String) getHakmilik.get("nama_kementerian");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2 = (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		
    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_kementerian);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
    		context.put("id_negeri", id_negeri);

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
    		
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "");
    		context.put("button", "edit");
    		context.put("flag", "semak");
    		context.put("flagOnline", "");
    		
    		vm = skrinDaftarOnline; 
    	
    		
    	}
    	
    	//semakan 3 layer
    	else if("updateFlagSemakan".equals(submit)){
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
    		
    		
    		// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
    		modelOnline.setMaklumatAgensi_DgnBantahan(id_hakmilik);
     		listMaklumatTanah = modelOnline.getMaklumatAgensi_DgnBantahan();
     		context.put("saiz_listTanah", listMaklumatTanah.size());	
     		Hashtable a = (Hashtable) listMaklumatTanah.get(0);
     		id_bantahan = (String)a.get("id_bantahan");
     		context.put("id_bantahan", id_bantahan);
    		
     		if("hantarSemakan".equals(submit2)){
    			
				
    			//update flag
    			updateFlag(session,"1",id_bantahan);
    			
    			if (doPost.equals("true")) {   			
    				//sendEmail(namaProjek,"","","",usid,"hantarSemakanBantahan");    		
        		}
    			
    		}else if("sahSemakan".equals(submit2)){
    			
    			//update flag
    			updateFlag(session,"2",id_bantahan);
    			
    			if (doPost.equals("true")) {   			
    				sendEmail(namaProjek,"","","",usid,"hantarLulusBantahan");    		
        		}
    			
    		}else if("lulusPermohonan".equals(submit2)){
    			
    			//update flag
    			updateFlag(session,"3",id_bantahan);
    			
    		}
     		
     		String flag_online = "";
			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
			context.put("getMaklumatBantahan",maklumatBantahan );
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String)b.get("flag_ukur_luas");
				String flag_pampasan = (String)b.get("flag_pampasan");								
				flag_semakan_online = (String)b.get("flag_semakan_online");
				flag_online = (String)b.get("flag_online");
				
				if (flag_penerima_pampasan.equals("Y")){
					setValueBantahanTerhadap("checked","","","");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")){
					setValueBantahanTerhadap("","checked","","");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")){
					setValueBantahanTerhadap("","","checked","");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")){
					setValueBantahanTerhadap("","","","checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}
			
    		//GETHAKMILIK
    		Hashtable getHakmilik =  modelOnline.getHakmilikPortal(id_hakmilik);
    		String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
    		String no_lotpt = (String) getHakmilik.get("no_lotpt");
    		String nama_kementerian = (String) getHakmilik.get("nama_kementerian");
    		String alamat1 = (String) getHakmilik.get("alamat1");
    		String alamat2 = (String) getHakmilik.get("alamat2");
    		String alamat3 = (String) getHakmilik.get("alamat3");
    		String poskod = (String) getHakmilik.get("poskod");
    		String id_negeri = (String) getHakmilik.get("id_negeri");
    		
    		context.put("txtNoHakmilik", no_hakmilik);
    		context.put("txtNoLot", no_lotpt);
    		context.put("txtNamaPembantah", nama_kementerian);
    		context.put("txtAlamat1", alamat1);
    		context.put("txtAlamat2", alamat2);
    		context.put("txtAlamat3", alamat3);
    		context.put("txtPoskod", poskod);
    		context.put("id_negeri", id_negeri);

    		//DROP DOWN
    		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
    		
    		if (flag_online.equals("1") ){
    			context.put("flagOnline", "1");
    		}else{
    			context.put("flagOnline", "");
    		}
    		
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");
    		
    		vm = skrinDaftarOnline; 
    	
    		
    	}else if("simpanEditBantahan".equals(submit)){
    		
    		maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
    		String id_status_bantahan = "";
    		if (maklumatBantahan.size()!=0 ){	
				Hashtable idsb = (Hashtable) maklumatBantahan.get(0);
				id_status_bantahan = (String)idsb.get("status_bantahan_ap");	
			}
    		
    		context.put("id_status_bantahan", id_status_bantahan);
    		
    		//---UPDATE
			simpanEditBantahan(id_bantahan,usid,id_status_bantahan);
    		
       		// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
    		modelOnline.setMaklumatAgensi_DgnBantahan(id_hakmilik);
     		listMaklumatTanah = modelOnline.getMaklumatAgensi_DgnBantahan();
     		context.put("saiz_listTanah", listMaklumatTanah.size());
     		if(listMaklumatTanah.size()!=0){
     			Hashtable a = (Hashtable) listMaklumatTanah.get(0);
         		id_bantahan = (String)a.get("id_bantahan");
     		}
     		
     		context.put("id_bantahan", id_bantahan);

    		//GETHAKMILIK
    		getValueHakmilik(id_hakmilik,usid);
    		
    		//GET DATA BANTAHAN
     		getDataBantahan(id_permohonan,id_bantahan);

			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
			if (maklumatBantahan.size()!=0 ){				
				Hashtable b = (Hashtable) maklumatBantahan.get(0);							
				flag_semakan_online = (String)b.get("flag_semakan_online");
			}
			
    		//CLEAR
    		context.put("clearForm", "");
    		context.put("mode", "disabled");
    		context.put("button", "view");
    		context.put("flag", "semak");
    		
    		vm = skrinDaftarOnline; 
    		
    	}else if("papar_ListHakmilik".equals(submit)){
    		
        	// GET TOTAL HAKMILIK
    		modelOnline.setListMaklumatTanahByStatus(id_permohonan,noLOT,"");
     		listMaklumatTanah = modelOnline.getListMaklumatTanah();
     		context.put("listMaklumatTanah", listMaklumatTanah);
     		context.put("saiz_listTanah", listMaklumatTanah.size());	
    		
    		id_bantahan = getParam("id_bantahan");
    		context.put("id_bantahan", id_bantahan);
    		
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());

    		//list maklumat tanah
    		//listHakmilik(id_permohonan,usid,noLOT);
    		
    		vm = listHMscreen;
    		
    	}else if("search_data".equals(submit)){
    		
			carianBantahan(usid,portal_role);
			listPageDepan = modelOnline.getListCarian();

    		//data & size default list
    		context.put("PermohonanList", listPageDepan);
    		context.put("list_size", listPageDepan.size());  

     		vm = skrinListPermohonanAP;
    		
    	}else { 
    		
    		//GET LIST DATA
			listPageDepan = modelOnline.getListPemohon(usid,portal_role);	
    		
			resetValueCarian();
						
    		//screen
    		vm = skrinListPermohonanAP;
	 		
    	}   
    	
    	context.put("PermohonanList", listPageDepan);
		context.put("list_size", listPageDepan.size()); 
		context.put("flag_semakan_online",flag_semakan_online);
		System.out.println("flag_semakan_online : "+flag_semakan_online);
    	setupPageBantahan(session,action,listPageDepan);
    	myLogger.info("vm : "+vm);
    	return vm;
    	
    }

	


	// METHOD
	
	@SuppressWarnings({ "static-access" })
	private void sendEmail(String nama_projek,String tarikh_permohonan,String userIdKementerian, String id_jawatan_user, String usid, String purpose) throws Exception{

		EmailOnline et = new EmailOnline();
		et.setEmail("","",purpose,"",nama_projek,tarikh_permohonan,"", userIdKementerian, id_jawatan_user, usid);
		
	}//close sendEmail
	
	private void resetValueCarian() throws Exception{
		
		//dropdown
		context.put("selectStatusSPT",HTML.SelectStatusSPT("socStatus",null,"style=width:auto"));
		context.put("selectJenisHMCarian",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"id=selectJenisHMCarian style=width:auto"));
		context.put("selectNegeriCarian",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:auto"));	
		
		context.put("txtNoFailCarian", "");
		context.put("txdTarikhPermohonan", "");
		context.put("sorUrusanCarian", "");
		context.put("txtBilPermohonanCarian", "");			
		context.put("txtNamaPBCarian", "");
		context.put("txtNoPBCarian", "");
		context.put("txtNoHakmilikCarian", "");			
		context.put("txtNoLotCarian", "");
		context.put("txtTujuanCarian", "");
		
	}//close resetValueCarian

	@SuppressWarnings("unchecked")
	private void updateFlag(HttpSession session,String flag,String id_bantahan) throws Exception{
		
	    	Hashtable h = new Hashtable();
	       
	    	h.put("id_bantahan", id_bantahan);
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	h.put("flag_semakan_online", flag);
	    	BantahanDaftarOperations.updateFlag(h);
	    	
	}//close updateFlag
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String getJawatan(String id_user) throws Exception{
		
		Vector dataUser = new Vector();

		dataUser.clear();

		modelUPT.setDataUser(id_user);
		dataUser = modelUPT.getDataUser();
	    String ID_JAWATAN = "";
	    if(dataUser.size()!=0){
	    	Hashtable t = (Hashtable)dataUser.get(0);
	    	ID_JAWATAN = t.get("ID_JAWATAN").toString();
	    }
	   
	    return ID_JAWATAN;
	    
	}//close getJawatan
	
	@SuppressWarnings("unchecked")
	public void getDataBantahan(String id_permohonan,String id_bantahan) throws Exception{
		
		Vector maklumatBantahan = new Vector();
		maklumatBantahan.clear();
		
		maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,id_bantahan);
		context.put("getMaklumatBantahan",maklumatBantahan );
		String flag_online = "";
		String id_status_bantahan = "";
		if (maklumatBantahan.size()!=0 ){				
			Hashtable b = (Hashtable) maklumatBantahan.get(0);
			String flag_penerima_pampasan = (String)b.get("flag_penerima_pampasan");
			String flag_bahagian_pampasan = (String)b.get("flag_bahagian_pampasan");
			String flag_ukur_luas = (String)b.get("flag_ukur_luas");
			String flag_pampasan = (String)b.get("flag_pampasan");								
			flag_online = (String)b.get("flag_online");
			id_status_bantahan = (String)b.get("status_bantahan_ap");
			
			if (flag_penerima_pampasan.equals("Y")){
				setValueBantahanTerhadap("checked","","","");
				context.put("TEMPchecked1", checkedsbcBantahan1);
			}
			if (flag_bahagian_pampasan.equals("Y")){
				setValueBantahanTerhadap("","checked","","");
				context.put("TEMPchecked2", checkedsbcBantahan2);
			}
			if (flag_ukur_luas.equals("Y")){
				setValueBantahanTerhadap("","","checked","");
				context.put("TEMPchecked3", checkedsbcBantahan3);
			}
			if (flag_pampasan.equals("Y")){
				setValueBantahanTerhadap("","","","checked");
				context.put("TEMPchecked4", checkedsbcBantahan4);
			}
		}
		
		context.put("id_status_bantahan",id_status_bantahan);
		
		if (flag_online.equals("1") ){
			context.put("flagOnline", "1");
		}else{
			context.put("flagOnline", "");
		}
		
	}//close getDataBantahan
	
	@SuppressWarnings("unchecked")
	public void getValueHakmilik(String id_hakmilik,String usid) throws Exception{
		
		//GETHAKMILIK
		String no_hakmilik = "";
		String no_lotpt = ""; 		
		String alamat1 = "";
		String alamat2= "";
		String alamat3 = "";
		String poskod = "";
		String id_negeri = "";
		String id_kementerian = "";;
		String nama_kementerian = "";
		String id_agensi = "";
		String nama_agensi = "";
		String jumlah_award = "";
		
		Hashtable getHakmilik =  modelOnline.getHakmilikPortal(id_hakmilik);
		if(getHakmilik.size()!=0){
			 no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			 no_lotpt = (String) getHakmilik.get("no_lotpt");    		
			 alamat1 = (String) getHakmilik.get("alamat1");
			 alamat2= (String) getHakmilik.get("alamat2");
			 alamat3 = (String) getHakmilik.get("alamat3");
			 poskod = (String) getHakmilik.get("poskod");
			 id_negeri = (String) getHakmilik.get("id_negeri");
			 id_kementerian = (String) getHakmilik.get("id_kementerian");
			 nama_kementerian = (String) getHakmilik.get("nama_kementerian");
			 id_agensi = (String) getHakmilik.get("id_agensi");
			 nama_agensi = (String) getHakmilik.get("nama_agensi");
			 jumlah_award = (String) getHakmilik.get("jumlah_award");
		}
		
		context.put("id_kementerian", id_kementerian);
		context.put("txtNamaPembantah", nama_kementerian);
		context.put("id_agensi", id_agensi);
		context.put("txtNamaAgensi", nama_agensi);
		context.put("txtNoHakmilik", no_hakmilik);
		context.put("txtNoLot", no_lotpt);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		context.put("txtPoskod", poskod);
		context.put("id_negeri", id_negeri);
		context.put("txtAmaunTuntutan", jumlah_award);

		//DROP DOWN
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
	
	}//close getValueHakmilik
	
	private void updateSuburusanStatusBantahan (HttpSession session,String usid, String id_bantahan,
			String id_permohonan, String id_hakmilik, String id_fail,String id_suburusanstatusfailppt) throws Exception   {
		
		Hashtable h = new Hashtable();	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		
		//update suburusanstatusfailppt
		modelOnlineOperations.updateSuburusanStatusBantahan(h,id_suburusanstatusfailppt,"16102106");
		
	}//close updateSuburusanStatusBantahan


	public void setValueBantahanTerhadap(String checkedsbcBantahan1, String checkedsbcBantahan2,String checkedsbcBantahan3, String checkedsbcBantahan4) {
		this.checkedsbcBantahan1 = checkedsbcBantahan1;
		this.checkedsbcBantahan2 = checkedsbcBantahan2;
		this.checkedsbcBantahan3 = checkedsbcBantahan3;
		this.checkedsbcBantahan4 = checkedsbcBantahan4;
	}

	public void setValueJenisPembantah(String PB,String AP){		
		context.put("jenis_pembantah", PB);
		context.put("jenis_pembantah", AP);
	}	
	
	private void simpanEditBantahan(String id_bantahan, String usid, String id_status_bantahan) throws Exception {	
		
		String txdMohon = getParam("txdMohon");
		String txdBrgN = getParam("txdBrgN");
		String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");
		
		// BANTAHAN TERHADAP
	    String ukuran_luas = getParam("ukuran_luas");
	    String amaun_pampasan = getParam("amaun_pampasan");
	    String terima_pampasan = getParam("terima_pampasan");
	    String umpuk_pampasan = getParam("umpuk_pampasan");
	    
	    String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
		String txtAlasanBantahan = getParam("txtAlasanBantahan");
		
		modelOnline.simpanEditBantahan(usid,id_bantahan,txdMohon,txdBrgN,txtKptgnAtasTnh,
				ukuran_luas,amaun_pampasan,terima_pampasan,umpuk_pampasan,txtAmaunTuntutan,txtAlasanBantahan,id_status_bantahan);		
	}	

	private void updateSuburusanStatusFailPPT(HttpSession session,
			String id_permohonan, String id_fail,
			String id_suburusanstatusfailppt) throws Exception {
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelOnlineOperations.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"16102106");
		
	}//close updateSuburusanStatusFailPPT

	private void updateSuburusanHakmilik(HttpSession session,
			String id_permohonan, String id_fail, String id_hakmilik,String id_suburusanstatushakmilik) throws Exception {
	    
		Hashtable h = new Hashtable();		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelOnlineOperations.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"16102106");
	
	}//close addSuburusanHakmilik

	private void updateStatusDalamProses(String id_permohonan, String usid) throws Exception {
		modelOnlineOperations.updateStatusDalamProses(id_permohonan,usid);
		
	}

	private void updateFlagOnline(String id_bantahan, String usid, String id_hakmilik) throws Exception {
		modelOnline.updateFlagOnline(id_bantahan,usid,id_hakmilik);
		
	}


	private String daftarBantahan(String usid) throws Exception {
		
	    String txdMohon = getParam("txdMohon"); 
	    String txdBrgN = getParam("txdBrgN");
	    String txtNoLot = getParam("txtNoLot");
	    String txtNoHakmilik = getParam("txtNoHakmilik");
	    
	    String txtNama = getParam("txtNamaPembantah");
	    String txtAlamat1 = getParam("txtAlamat1");
	    String txtAlamat2 = getParam("txtAlamat2");
	    String txtAlamat3 = getParam("txtAlamat3");
	    String txtPoskod = getParam("txtPoskod");
	    String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");
	    String txtAlasanBantahan = getParam("txtAlasanBantahan");
	
	    String id_kementerian = getParam("id_kementerian");
	    String id_agensi = getParam("id_agensi");
	    String id_hakmilik = getParam("id_hakmilik");
	    String jenis_pembantah = getParam("jenis_pembantah");
	    
	    //ALASAN BANTAHAN
	    String ukuran_luas = getParam("ukuran_luas");
	    String amaun_pampasan = getParam("amaun_pampasan");
	    String terima_pampasan = getParam("terima_pampasan");
	    String umpuk_pampasan = getParam("umpuk_pampasan");

	    String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
	    String id_permohonan = getParam("id_permohonan");
	    
	   return modelOnline.daftarBantahanPortal(txdMohon,txdBrgN,txtNoLot,
	    		txtNoHakmilik,txtNama,txtAlamat1,txtAlamat2,txtAlamat3,txtPoskod,
	    		txtKptgnAtasTnh,txtAlasanBantahan,usid,jenis_pembantah,ukuran_luas,amaun_pampasan,id_kementerian,id_agensi,
	    		terima_pampasan,umpuk_pampasan,id_hakmilik,txtAmaunTuntutan,id_permohonan);	
	}




	private void listHakmilik(String id_permohonan,String usid,String noLOT) throws Exception{
		
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();

		modelOnline.setListMaklumatTanah(id_permohonan,usid,noLOT);
 		listMaklumatTanah = modelOnline.getListMaklumatTanah();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());

	}//close listHakmilik	
	
	private void carianBantahan(String usid,String role) throws Exception {
		
		//dropdown
		context.put("selectStatusSPT",HTML.SelectStatusSPT("socStatus",Utils.parseLong(getParam("socStatus")),"style=width:auto"));
		context.put("selectJenisHMCarian",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=selectJenisHMCarian style=width:auto"));
		context.put("selectNegeriCarian",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(getParam("socNegeri")),null,"style=width:auto"));	
		
		context.put("txtNoFailCarian", getParam("txtNoFailCarian").trim());
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan").trim());
		context.put("sorUrusanCarian", getParam("sorUrusanCarian"));
		context.put("txtBilPermohonanCarian", getParam("txtBilPermohonanCarian").trim());			
		context.put("txtNamaPBCarian", getParam("txtNamaPBCarian").trim());
		context.put("txtNoPBCarian", getParam("txtNoPBCarian").trim());
		context.put("txtNoHakmilikCarian", getParam("txtNoHakmilikCarian").trim());			
		context.put("txtNoLotCarian", getParam("txtNoLotCarian").trim());
		context.put("txtTujuanCarian", getParam("txtTujuanCarian").trim());
		
		modelOnline.setCarianFail(getParam("socStatus"),getParam("socJenisHakmilik"),getParam("socNegeri"),getParam("txtNoFailCarian").trim(),
				getParam("txdTarikhPermohonan").trim(),getParam("sorUrusanCarian"),getParam("txtBilPermohonanCarian").trim(),
				getParam("txtNamaPBCarian").trim(),getParam("txtNoPBCarian").trim(),getParam("txtNoHakmilikCarian").trim(),getParam("txtNoLotCarian").trim(),
				getParam("txtTujuanCarian").trim(),usid,role);	
		
	}	
	@SuppressWarnings("unchecked")
	public void setupPageBantahan(HttpSession session,String action,Vector list) {
		
			try {
			
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
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
	/*private String userData(String id_user,String portal_role) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		listUserid = modelUPTOnline.getListPemohon(id_user,portal_role);
//	    listUserid = modelUPTOnline.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;
	}
		*/



	
	
}
