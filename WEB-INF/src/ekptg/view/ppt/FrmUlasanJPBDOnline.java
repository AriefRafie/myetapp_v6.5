package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUlasanJPBDOnlineData;
import ekptg.model.ppt.PPTHeader;
/*
 * @author 
 * Shiqa
 */

public class FrmUlasanJPBDOnline extends AjaxBasedModule {

	String MP_NOPERMOHONAN = "";
	String MP_NOFAIL = "";
	String MP_NEGERI = "";
	String MP_DAERAH = "";
	String MP_TAJUKPERMOHONAN = "";
	
	String JPBD_NORUJUKANJPBD = "";
	String JPBD_NOWARTA = "";
	String JPBD_DALAMKAWASANPBPT = "";
	String JPBD_DALAMKAWASANPBPT0 = "";
	String JPBD_DALAMKAWASANPBPT1 = "";
	String JPBD_ADAPELANSTRUKTUR = "";
	String JPBD_ADAPELANTEMPATAN = "";
	String JPBD_TARIKHLULUSPELANSTRUKTUR = "";
	String JPBD_TARIKHLULUSPELANTEMPATAN = "";
	String JPBD_KEGUNAANTANAH = "";
	String JPBD_POTENSIPEMBANGUNAN = "";
	String JPBD_NAMAPBT = "";
	String JPBD_STATUSKELULUSAN = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH0 = "";
	String JPBD_PERMOHONANMEMAJUKANTANAH1 = "";
	String JPBD_TUJUANPERMOHONAN = "";
	String JPBD_TARIKHLULUSTOLAK = "";
	String JPBD_TARIKHLUPUTKELULUSAN = "";
	String JPBD_CATATANLAIN = "";
	String JPBD_NAMAPEGAWAIJPBD = "";
	String JPBD_JAWATANPEGAWAIJPBD = "";
	String JPBD_TANDATANGANBAGIPIHAK = "";
	String JPBD_NAMAPEGAWAIASAL = "";
	String JPBD_JAWATANPEGAWAIASAL = "";
	String JPBD_TARIKHPERMOHONAN = "";
	String JPBD_JABATAN = "";
	
	Boolean haveINTData = false;
	
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUlasanJPBDOnline.class);
	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUlasanJPBDOnlineData model = new FrmUlasanJPBDOnlineData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{
		
		String idKementerian = "";
		Vector listDetailMOF = null;
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		
		listDetailMOF = modelUPT.getIdNegeriKJPByUserId(userId);

		if (!listDetailMOF.isEmpty() && listDetailMOF.size() > 0) {
			Hashtable hashMaklumatMOF = (Hashtable) listDetailMOF.get(0);
			idKementerian = hashMaklumatMOF.get("idKementerian").toString();
		}

		this.context.put("idKementerian", idKementerian);
		myLogger.info("idKementerian====="+idKementerian);

		//command for pagings
    	String action = getParam("action");
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);
	
    	String vm = "";
    	String noLOT = "";
    	String idpegawai = "";
    	
    	Vector listPageDepan = new Vector();
    	Vector dataMaklumatJPBD = new Vector();
    	Vector dataMaklumatJPPH = new Vector();
    	Vector getIdSuburusanstatusfail = new Vector();
    	Vector dataSuburusanHakmilik = new Vector();
    	Vector listHakmilik = new Vector();
    	Vector dataMaklumatTanah = new Vector();
    	Vector vData = new Vector();
    	
    	dataMaklumatTanah.clear();
		listHakmilik.clear();
    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
    	dataMaklumatJPPH.clear();
    	dataMaklumatJPBD.clear();
    	listPageDepan.clear();
   	
    	//screen jsp
    	String listdepan = "app/ppt/frmSenaraiUlasanJPBDOnline.jsp";
    	String mainscreen = "app/ppt/frmUlasanJPBDOnline.jsp";
    	String listHMscreen = "app/ppt/frmListHMUlasanJPBDOnline.jsp";
    	String maklumatUlasan = "app/ppt/frmMaklumatUlasanJPBDOnline.jsp";
    	
    	//default list
    	//listPageDepan = model.getListPermohonan(userIdNeg);
    	
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
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "8");
    	}
    	*/
    	context.put("paging", "8");
		//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String tarikh_permohonan = "";
		String tujuan = "";
		String nama_kementerian = "";
		String no_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	Vector dataHeader = null;
    	String nama2Mukim = "";
    	if(!idpermohonan.equals(""))
    	{
	    	header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if(dataHeader.size()!=0){
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String)dh.get("id_status");
				id_fail = (String)dh.get("id_fail");
				flagSegera = dh.get("flag_segera").toString();
				tujuan = (String) dh.get("tujuan");
				tarikh_permohonan = (String) dh.get("tarikh_permohonan");
				nama_kementerian = (String)dh.get("nama_kementerian");
				no_fail = (String) dh.get("no_fail");
				
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
			
			/*
			modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
	 		listHakmilik = modelUPT.getListMaklumatTanah();
	 		if(listHakmilik.size()!=0){
	 			Hashtable lmt = (Hashtable)listHakmilik.get(0);
	 			nama2Mukim = (String)lmt.get("nama2Mukim");
	 		}
	 		*/
	 		
	 		
    	}
    	context.put("nama2Mukim", nama2Mukim);
		context.put("flagSegera",flagSegera);

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
		
		
		String idHakmilik = getParam("id_hakmilik");
		String id_suburusanstatushakmilik = "";
		if(!idHakmilik.equals(""))
		{
			//data hakmilik
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);			
			//get size suburusanhakmilik			
			modelUPT.setDataSuburusanHakmilik(idHakmilik);
			dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
			if(dataSuburusanHakmilik.size()!=0){
				Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
				id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
			}
		}	
		
		String id_hakmilik = getParam("id_hakmilik");
		if(!id_hakmilik.equals("") && !idpermohonan.equals(""))
		{ 		
 		//data maklumat JPBD
    	model.setDataMaklumatJPBD(idpermohonan,id_hakmilik);
		dataMaklumatJPBD = model.getDataMaklumatJPBD();
		}
		
		//default
		context.put("mode","");
		context.put("isEdit","");
		context.put("modeJPPH","");
		context.put("isEditJPPH","");
		
    	String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	Hashtable h = new Hashtable();
    	
    	if("viewListHM".equals(submit)){
    	    
    		noLOT = getParam("carianNoLot");
    		context.put("carianNoLot", noLOT.trim());
    		
    		//list maklumat tanah
    		listHakmilik(idpermohonan,noLOT,idpegawai);
     		
        	//screen
    		vm = listHMscreen;
        	
    	}//close viewListHM
    	else if ("viewBorangLampiranA1".equalsIgnoreCase(submit)) {
        	myLogger.info("baca viewBorangLampiranA1========="+idpermohonan);
    		// initial VM value
        	if (!"".equalsIgnoreCase(idpermohonan)) {
    			vData = model.viewMaklumatPermohonan(idpermohonan);
    			if (!vData.isEmpty()) {
    	        	vm = maklumatUlasan;
    				h = (Hashtable) vData.get(0);
    				populatePage(h);
    			}
    		}
        	vm = maklumatUlasan;
    		
        }
    	else if("viewMaklumat".equals(submit)){
    		
    		//penambahan yati
    		String id_permohonan = getParam("id_permohonan");
    		context.put("id_permohonan", id_permohonan);
    		
    	
    		String namaMukim ="";
    		String nolot ="";
    		String noHakmilik = "";
    		String kodHakmilik = "";
    		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
     		listHakmilik = modelUPT.getListMaklumatTanah();
     		if(listHakmilik.size()!=0){
     			Hashtable mm = (Hashtable)listHakmilik.get(0);
     			namaMukim = (String)mm.get("nama2Mukim");
     			nolot = (String)mm.get("no_lotpt");
     			noHakmilik = (String)mm.get("no_hakmilik");
     			kodHakmilik = (String)mm.get("kod_jenis_hakmilik");
     		}
     		
     		context.put("namaMukim", namaMukim);
     		context.put("nolot", nolot);
     		context.put("noHakmilik", noHakmilik);
     		context.put("kodHakmilik", kodHakmilik);
     	
    		emelListJPBD(id_hakmilik);
			String emelJPBD  = emelListJPBD(id_hakmilik); 
			//System.out.println("EMEL CHECK :: "+emelJPBD);
			
			if(getParam("FlagHantarEmelJPBD").equals("Y"))
    		{
    			hantarEmelJPBD("content","Makluman Maklumat JPBD" ,no_fail, tujuan, tarikh_permohonan,emelJPBD,nama_kementerian, namaMukim, kodHakmilik, noHakmilik, nolot );												
    		}
    
			emelListJPPH(id_hakmilik);
			String emelJPPH  = emelListJPPH(id_hakmilik); 
			//System.out.println("EMEL CHECK :: "+emelJPPH);
			
			if(getParam("FlagHantarEmelJPPH").equals("Y"))
    		{
    			hantarEmelJPPH("content","Makluman Maklumat JPPH" ,no_fail, tujuan, tarikh_permohonan,emelJPPH,nama_kementerian, namaMukim, kodHakmilik, noHakmilik, nolot );											
    		}
    
    		//data maklumat JPBD
        	model.setDataMaklumatJPBD(idpermohonan,id_hakmilik);
    		dataMaklumatJPBD = model.getDataMaklumatJPBD();
    		String id_jpbd = "";
    		if(dataMaklumatJPBD.size()!=0){
    			Hashtable dm = (Hashtable)dataMaklumatJPBD.get(0);
    			id_jpbd = (String)dm.get("id_ulasanteknikal");
    		}
    		context.put("dataMaklumatJPBD",dataMaklumatJPBD);
    		context.put("id_jpbd",id_jpbd);
    		
    		//data maklumat JPPH
        	model.setDataMaklumatJPPH(idpermohonan,id_hakmilik);
    		dataMaklumatJPPH = model.getDataMaklumatJPPH();
    		String id_jpph = "";
    		if(dataMaklumatJPPH.size()!=0){
    			Hashtable jpph = (Hashtable)dataMaklumatJPPH.get(0);
    			id_jpph = (String)jpph.get("id_ulasanteknikal");
    		}
    		context.put("dataMaklumatJPPH",dataMaklumatJPPH);
    		context.put("id_jpph",id_jpph);
    		
    		
    		String submit2 = getParam("command2");
        	myLogger.info("submit[2] : " + submit2);
        	
    		//NEW TAB 1
    		if(dataMaklumatJPBD.size()==0){
    			
    			//form validation
        		context.put("mode","new");
    			
        		if("simpanMaklumatJPBD".equals(submit2)){
            		
        			
            		if (doPost.equals("true")) {
            			
            			//simpan data
            			simpanMaklumatJPBD(session,idpermohonan);
            			
            			//updateSuburusanHakmilik(session,idpermohonan,id_fail,idHakmilik,id_suburusanstatushakmilik);
            			
            			//if(id_status.equals("147")){
            			if(modelUPT.cekStatusFailDahWujud(idpermohonan,"43","52")==false)
                		{
            				updateStatus(session);
            				updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
            			}
            			
            		}
            		
            		//form validation
                	context.put("mode","view");
                	context.put("isEdit","no");
                	
                	//data header (get id_status)
    	    		dataHeaderStatus(session,idpermohonan);
    	    		id_status = dataHeaderStatus(session,idpermohonan);
                	
            		//data maklumat JPBD / JPPH
    	    		dataMaklumatJPBD(session,idpermohonan,id_hakmilik);
                	
    	    		//data maklumat JPBD
    	        	model.setDataMaklumatJPBD(idpermohonan,id_hakmilik);
    	    		dataMaklumatJPBD = model.getDataMaklumatJPBD();
    	    		
            	}//close simpanMaklumat
        		
    		}else{
    			
    			//form validation
            	context.put("mode","view");
            	context.put("isEdit","no");
     
        		//data maklumat JPBD / JPPH
            	dataMaklumatJPBD(session,idpermohonan,id_hakmilik);
        		
            	if("kemaskiniJPBD".equals(submit2)){
            		
            		//form validation
                	context.put("isEdit","yes");
            		
                	String submit3 = getParam("command3");
                	myLogger.info("submit[3] : " + submit3);
                	
                	if("updateJPBD".equals(submit3)){
                		
                		updateMaklumatJPBD(session);
                		
                		//form validation
                    	context.put("mode","view");
                    	context.put("isEdit","no");
                    	
                		//data header (get id_status)
        	    		dataHeaderStatus(session,idpermohonan);
        	    		id_status = dataHeaderStatus(session,idpermohonan);
        	    		
                		//data maklumat JPBD / JPPH
                    	dataMaklumatJPBD(session,idpermohonan,id_hakmilik);
                		
                	}//close updateJPBD
                	
            	}//close kemaskiniJPBD
            	
    		}//close view tab1
    		

        	//screen
        	vm = mainscreen;
        	
    	}//close 
    
		else 
		if("cari".equals(submit)){
    		
    		//carian
    		ListCarian(session,userIdNeg);			
    		listPageDepan = model.getListCarian();
	
			//screen
    		vm = listdepan;
		    
    	}//close cari
    	
    	else{	
    		
    		listPageDepan = model.getListPermohonan(userIdNeg);
    		
    		context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");
			
    		//screen
    		vm = listdepan;
    		
		}//close else
   	
    		//validation tab
    		if(dataMaklumatJPBD.size()!=0){
    			context.put("showTab","yes");
    		}else{
    			context.put("showTab","no");
    		}
    	
    		//list permohonan
	    	context.put("listPermohonan", listPageDepan);
	    	context.put("list_size", listPageDepan.size());
	    
	    	//id
	    	context.put("id_permohonan", idpermohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_hakmilik", idHakmilik);
	    	
    		setupPage(session,action,listPageDepan);
    		this.context.put("selectedTab",selectedTab);
    		return vm;
    		
	    }//close public template
	
	
//--METHOD--//	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String userData(String id_user) throws Exception{
		
		Vector listUserid = new Vector();
		listUserid.clear();
		
		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    
	    return userIdNeg;
	    
	}//close userData
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,"1474");
	
	}//close addSuburusanHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1474");
		
	}//close updateSuburusanStatusFailPPT
	
	
	@SuppressWarnings("unchecked")
	private void listHakmilik(String idpermohonan,String noLOT,String idpegawai) throws Exception{
    	
		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();
		
		modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		
	}//close listHakmilik
	
	private void ListCarian(HttpSession session,String userIdNeg) throws Exception{
    	
		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");
    	
		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);
			
		FrmUlasanJPBDOnlineData.setListCarian(nofail,tarikh,status,userIdNeg);
      
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
	private void simpanMaklumatJPBD(HttpSession session,String idpermohonan) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		//h.put("id_hakmilik", "");
		h.put("id_permohonan", idpermohonan);
		h.put("id_hakmilik", getParam("id_hakmilik"));
		
		//h.put("txtKodPejabatJPBD", getParam("txtKodPejabatJPBD"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJT"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("txtUlasanJPBD", getParam("txtUlasanJPBD"));
		
		//PENAMBAHAN YATI
		h.put("namaJPBD", getParam("namaJPBD"));
		h.put("emelJPBD", getParam("emelJPBD"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUlasanJPBDOnlineData.simpanMaklumatJPBD(h);
		
	}//close simpanMaklumat

	@SuppressWarnings("unchecked")
	private void simpanMaklumatJPPH(HttpSession session,String idpermohonan) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", idpermohonan);
		h.put("id_hakmilik", getParam("id_hakmilik"));
		
		//h.put("txtKodPejabatJPPH", getParam("txtKodPejabatJPPH"));
		h.put("txtBilSurat", getParam("txtBilSuratH"));
		h.put("txdTarikhSurat", getParam("txdTarikhSuratH"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerimaH"));
		h.put("txtNoRujSurat", getParam("txtNoRujSuratH"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJTH"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJTH"));
		h.put("txtUlasanJPPH", getParam("txtUlasanJPPH"));
		//penambahan yati
		h.put("namaJPPH", getParam("namaJPPH"));
		h.put("emelJPPH", getParam("emelJPPH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUlasanJPBDOnlineData.simpanMaklumatJPPH(h);
		
	}//close simpanMaklumat
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatJPBD(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_jpbd"));
		
		//h.put("txtKodPejabatJPBD", getParam("txtKodPejabatJPBD"));
		h.put("txtBilSurat", getParam("txtBilSurat"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJT"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJT"));
		h.put("txtUlasanJPBD", getParam("txtUlasanJPBD"));
		
		//PENAMBAHAN YATI
		h.put("namaJPPH", getParam("namaJPPH"));
		h.put("emelJPPH", getParam("emelJPPH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUlasanJPBDOnlineData.updateMaklumatJPBD(h);
		
	}//close updateMaklumatJPBD
	
	@SuppressWarnings("unchecked")
	private void updateMaklumatJPPH(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_jpph"));
		
		//h.put("txtKodPejabatJPPH", getParam("txtKodPejabatJPPH"));
		h.put("txtBilSurat", getParam("txtBilSuratH"));
		h.put("txdTarikhSurat", getParam("txdTarikhSuratH"));
		h.put("txdTarikhTerima", getParam("txdTarikhTerimaH"));
		h.put("txtNoRujSurat", getParam("txtNoRujSuratH"));
		h.put("txdTarikhTerimaJT", getParam("txdTarikhTerimaJTH"));
		h.put("txdTarikhSuratJT", getParam("txdTarikhSuratJTH"));
		h.put("txtUlasanJPPH", getParam("txtUlasanJPPH"));
		
		//penambahan yati
		h.put("namaJPPH", getParam("namaJPPH"));
		h.put("emelJPPH", getParam("emelJPPH"));
		
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUlasanJPBDOnlineData.updateMaklumatJPPH(h);
		
	}//close updateMaklumatJPPH
	
	@SuppressWarnings("unchecked")
	private void hantarMaklumatJPPH(HttpSession session) throws Exception{
    	
		Hashtable h = new Hashtable();
		
		h.put("id_ulasanteknikal", getParam("id_jpph"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		FrmUlasanJPBDOnlineData.hantarMaklumatJPPH(h);
		
	}//close updateMaklumatJPPH
	
	private void updateStatus(HttpSession session) throws Exception{
    	
		String idpermohonan = getParam("id_permohonan");
    	String idUser = (String) session.getAttribute("_ekptg_user_id");
    	
    	//status JPBD/JPPH
		String idstatus = "43";
    	
		FrmUlasanJPBDOnlineData.updateStatus(idpermohonan,idUser,idstatus);
      
	}//close hantar

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatJPBD(HttpSession session,String idpermohonan,String id_hakmilik) throws Exception{
    	
		//data maklumat jpbd/jpph
    	model.setDataMaklumatJPBD(idpermohonan,id_hakmilik);
		Vector dataMaklumatJPBD = model.getDataMaklumatJPBD();
		String id_jpbd = "";
		if(dataMaklumatJPBD.size()!=0){
			Hashtable dm = (Hashtable)dataMaklumatJPBD.get(0);
			id_jpbd = (String)dm.get("id_ulasanteknikal");
		}
		context.put("dataMaklumatJPBD",dataMaklumatJPBD);
		context.put("id_jpbd",id_jpbd);
		
	}//close dataMaklumat
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataMaklumatJPPH(HttpSession session,String idpermohonan,String id_hakmilik) throws Exception{
    	
		//data maklumat JPPH
    	model.setDataMaklumatJPPH(idpermohonan,id_hakmilik);
		Vector dataMaklumatJPPH = model.getDataMaklumatJPPH();
		String id_jpph = "";
		String flagTugasan = "";
		if(dataMaklumatJPPH.size()!=0){
			Hashtable jpph = (Hashtable)dataMaklumatJPPH.get(0);
			id_jpph = (String)jpph.get("id_ulasanteknikal");
			flagTugasan = (String)jpph.get("flag_tugasan");
		}
		context.put("dataMaklumatJPPH",dataMaklumatJPPH);
		context.put("id_jpph",id_jpph);
		context.put("flagTugasan",flagTugasan);
		
	}//close dataMaklumatJPPH
	

	//penambahan yati-jpbd
	@SuppressWarnings("static-access")
	private String emelListJPBD(String id_hakmilik) throws Exception {

	Vector emelJPBD = new Vector();
	emelJPBD.clear();

	String emel = "";
	emelJPBD = model.setListEmelJPBD(id_hakmilik);
	if (emelJPBD.size() != 0) {
		Hashtable npp = (Hashtable) emelJPBD.get(0);
		emel = (String) npp.get("emel");
		}
		context.put("emel", emel);

	return emel;

	}// close emel JPBD
	
	//penambahan yati --jpph	
	@SuppressWarnings("static-access")
	private String emelListJPPH(String id_hakmilik) throws Exception {

	Vector emelJPPH = new Vector();
	emelJPPH.clear();
	
	String emel = "";
	emelJPPH = model.setListEmelJPPH(id_hakmilik);
	if (emelJPPH.size() != 0) {
		Hashtable npp = (Hashtable) emelJPPH.get(0);	
		emel = (String) npp.get("emel");
		}
		context.put("emel", emel);

	return emel;

	}// close emel JPPH
	
	
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
	
	public void hantarEmelJPBD(String content,String subjek,String nofail,
			 String nama_projek, String tarikh_permohonan, String emelJPBD, String nama_kementerian, String noMukim, String kodHakmilik, String noHakmilik, String nolot) throws Exception {

			Vector checkEmailJPBD = new Vector();
			checkEmailJPBD.clear();
			System.out.println("id checkEmailKJP :::"+emelJPBD);
		
			EmailProperty pro = EmailProperty.getInstance();
			//String EMAIL_HOST = pro.getHost_GM();
			EmailSender email = EmailSender.getInstance();
		
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>Makluman maklumat JPBD</td></tr>" +
				"<tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
				"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
				"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
				"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
				"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelJPBD;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "testingetapp@gmail.com";
		email.sendEmail();
		
	 } //close hantar emel jpbd

	public void hantarEmelJPPH(String content,String subjek,String nofail,
			 String nama_projek, String tarikh_permohonan, String emelJPPH, String nama_kementerian, String namaMukim, String kodHakmilik, String noHakmilik, String nolot) throws Exception {

			Vector checkEmailJPPH = new Vector();
			checkEmailJPPH.clear();
			System.out.println("id checkEmailKJP :::"+emelJPPH);
		
			EmailProperty pro = EmailProperty.getInstance();
			//String EMAIL_HOST = pro.getHost_GM();
			EmailSender email = EmailSender.getInstance();
		
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table><tr><td>Makluman maklumat JPPH</td></tr>" +
				"<tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
				"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
				"<tr><td>Nama Mukim</td><td>:</td><td>"+namaMukim+"</td></tr>" +
				"<tr><td>No Hakmilik</td><td>:</td><td>"+kodHakmilik+noHakmilik+"</td></tr>" +
				"<tr><td>No Lot</td><td>:</td><td>"+nolot+"</td></tr>" +
				"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
				"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
				"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelJPPH;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "testingetapp@gmail.com";
		email.sendEmail();
		
	 } //close hantar emel jpph
	@SuppressWarnings("unchecked")
	private void populatePage(Hashtable h) throws Exception {
		if (!h.isEmpty()) {
			MP_NOPERMOHONAN = (String) h.get("MP_NOPERMOHONAN");
			MP_NOFAIL = (String) h.get("MP_NOFAIL");
			MP_NEGERI = (String) h.get("MP_NEGERI");
			MP_DAERAH = (String) h.get("MP_DAERAH");
			MP_TAJUKPERMOHONAN = (String) h.get("MP_TAJUKPERMOHONAN");
			JPBD_NORUJUKANJPBD = (String) h.get("JPBD_NORUJUKANJPBD");
			JPBD_NOWARTA = (String) h.get("JPBD_NOWARTA");
			JPBD_DALAMKAWASANPBPT = (String) h.get("JPBD_DALAMKAWASANPBPT");
			JPBD_ADAPELANSTRUKTUR = (String) h.get("JPBD_ADAPELANSTRUKTUR");
			JPBD_ADAPELANTEMPATAN = (String) h.get("JPBD_ADAPELANTEMPATAN");
			JPBD_TARIKHLULUSPELANSTRUKTUR = (String) h.get("JPBD_TARIKHLULUSPELANSTRUKTUR");
			JPBD_TARIKHLULUSPELANTEMPATAN = (String) h.get("JPBD_TARIKHLULUSPELANTEMPATAN");
			JPBD_KEGUNAANTANAH = (String) h.get("JPBD_KEGUNAANTANAH");
			JPBD_POTENSIPEMBANGUNAN = (String) h.get("JPBD_POTENSIPEMBANGUNAN");
			JPBD_NAMAPBT = (String) h.get("JPBD_NAMAPBT");
			JPBD_STATUSKELULUSAN = (String) h.get("JPBD_STATUSKELULUSAN");
			JPBD_PERMOHONANMEMAJUKANTANAH = (String) h.get("JPBD_PERMOHONANMEMAJUKANTANAH");
			JPBD_TUJUANPERMOHONAN = (String) h.get("JPBD_TUJUANPERMOHONAN");
			JPBD_TARIKHLULUSTOLAK = (String) h.get("JPBD_TARIKHLULUSTOLAK");
			JPBD_TARIKHLUPUTKELULUSAN = (String) h.get("JPBD_TARIKHLUPUTKELULUSAN");
			JPBD_CATATANLAIN = (String) h.get("JPBD_CATATANLAIN");
			JPBD_NAMAPEGAWAIJPBD = (String) h.get("JPBD_NAMAPEGAWAIJPBD");
			JPBD_JAWATANPEGAWAIJPBD = (String) h.get("JPBD_JAWATANPEGAWAIJPBD");
			JPBD_TANDATANGANBAGIPIHAK = (String) h.get("JPBD_TANDATANGANBAGIPIHAK");
			JPBD_NAMAPEGAWAIASAL = (String) h.get("JPBD_NAMAPEGAWAIASAL");
			JPBD_JAWATANPEGAWAIASAL = (String) h.get("JPBD_JAWATANPEGAWAIASAL");
			JPBD_TARIKHPERMOHONAN = (String) h.get("JPBD_TARIKHPERMOHONAN");
			JPBD_JABATAN = (String) h.get("JPBD_JABATAN");
			if ("true".equalsIgnoreCase((String) h.get("haveINTData"))) {
				haveINTData = true;
			}
		}
	}
	
}//close class
