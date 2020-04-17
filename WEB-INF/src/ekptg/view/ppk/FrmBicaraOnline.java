package ekptg.view.ppk;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8Rayuan;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
import ekptg.model.ppk.FrmSenaraiFailKptsPerbcrnData;
/*
 * @author
 * Salnizam
 */

public class FrmBicaraOnline extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmBicaraOnline.class);

	private static final long serialVersionUID = 1L;

	//model name
	FrmRynSek8Rayuan model = new FrmRynSek8Rayuan();
	FrmPrmhnnSek8KptsanBicaraData logic4 = new FrmPrmhnnSek8KptsanBicaraData();
	FrmPrmhnnSek8Notis model2 = new FrmPrmhnnSek8Notis();
	FrmRynSek8SemakPenerimaan model3 = new FrmRynSek8SemakPenerimaan();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmPrmhnnSek8InternalData logic_internal = new FrmPrmhnnSek8InternalData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{

		HttpSession session = request.getSession();
		//logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
    	String vm = "";

    	String action = getParam("action");
    	myLogger.info("[action] :: " +action);
    	
    	headerppk_baru_default();

    	//screen
    	String screensenarai = "app/ppk/frmSenaraiBicaraOnline.jsp";
    	String screen = "app/ppk/frmRynPermohonanRayuanSemakPenerimaan.jsp";
    	String mainscreen = "app/ppk/frmPerbicaraan.jsp";

    	String doPost = (String) session.getAttribute("doPost");
    	String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);
    	//vector
    	Vector listWaris = null;
    	Vector listSimati = null;
    	Vector listKehadiran = null;
    	Vector listPemohon = null;
    	Vector list = new Vector();
    	Vector listCarian = new Vector();
    	Vector dataPemohon = new Vector();
    	Vector dataSemakK2 = new Vector();
    	Vector bayaranK2 = new Vector();
    	Vector maklumatRayuan = new Vector();
    	Vector maklumatSerahan = new Vector();
    	Vector listOB = new Vector();
    	Vector onchangeListOB = new Vector();
    	Vector listFirma = new Vector();
    	Vector dataLatestPeguam = new Vector();
    	Vector otherPeguam = new Vector();
    	Vector listOBatas18 = new Vector();
    	Vector listFirmaTR = new Vector();
    	Vector detailFirma = new Vector();
    	Vector listFirmaTerdahulu = new Vector();
    	Vector bayaranKR = new Vector();
    	Vector keputusanPermohonan = new Vector();
    	Vector dataNotis = new Vector();
    	Vector IdPermohonanSimati = new Vector();
    	Vector perintah = new Vector();
    	Vector flagRayuan = new Vector();
    	Vector tarikh_selesai = new Vector();

    	//02122009
    	Vector penghantarNotis = new Vector();
    	Vector penghantarNotisByJkptg = new Vector();
    	Vector getSelectedPenghantarNotis = new Vector();
    	Vector getSelectedPenghantarNotisByJkptg = new Vector();
    	Vector getPenghantarNotis = new Vector();
    	Vector dataMstRayuan = new Vector();
    	dataMstRayuan.clear();
    	penghantarNotisByJkptg.clear();
    	penghantarNotis.clear();
    	getSelectedPenghantarNotisByJkptg.clear();
    	getSelectedPenghantarNotis.clear();
    	getPenghantarNotis.clear();

    	//vector list clear
    	tarikh_selesai.clear();
    	flagRayuan.clear();
    	perintah.clear();
    	dataNotis.clear();
    	keputusanPermohonan.clear();
    	listFirmaTerdahulu.clear();
    	detailFirma.clear();
    	listFirmaTR.clear();
    	list.clear();
    	listCarian.clear();
    	dataPemohon.clear();
    	dataSemakK2.clear();
    	bayaranK2.clear();
    	maklumatRayuan.clear();
    	maklumatSerahan.clear();
    	listOB.clear();
    	onchangeListOB.clear();
    	listFirma.clear();
    	dataLatestPeguam.clear();
    	otherPeguam.clear();
    	listOBatas18.clear();
    	bayaranKR.clear();

    	context.put("Util", new lebah.util.Util());
    	
    	String usid="";
		usid=(String)session.getAttribute("_ekptg_user_id");

    	String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
    	String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
    	String flagForView = getParam("flagForView");
    	
    	String idprmhn = "";
    	idprmhn = getParam("id_permohonan");
    	myLogger.info("[idprmhn] :: " +idprmhn);
    	
    	String selectedTabUpper = getParam("selectedTabUpper").toString();	
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower) ) {
			selectedTabLower = "0";
		}
		String anchor = getParam("anchor");
    	//get id keputusan permohonan
		keputusanPermohonan = model2.getKeputusanPermohonan(idprmhn);
		String idkp = "";
		if(keputusanPermohonan.size()!=0){
			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
			idkp = kp.get("id_keputusanpermohonan").toString();
		}

		String idperbicaraan = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			idperbicaraan = idn.get("id_perbicaraan").toString();
		}

		//get info pemohon
    	model2.setListSemak(idprmhn,usid);
		dataPemohon = model2.getListSemak();

		String id_permohonansimatiINT = "";

		if(dataPemohon.size()!=0){
			Hashtable ls = (Hashtable) dataPemohon.get(0);
			id_permohonansimatiINT = ls.get("id_permohonansimati").toString();
		}


    	//command 1
		

    	context.put("flagRadio", "");

    	if	("papar_bicara".equals(submit)){
    		myLogger.info("Read papar bicara");
    		

	    	//--data notis
			logic4.setListSemakWithData(idkp);
    		dataNotis = logic4.getListSemakWithData();
    		long idUnitPsk = 0;
    		String tarikh_bicara = "";
    		String id_perbicaraan = "";
    		String tempat_bicara = "";
    		String alamat_bicara1 = "";
    		String alamat_bicara2 = "";
    		String alamat_bicara3 = "";
    		String poskod = "";
    		String id_negeribicara = "";
    		String peg_pengendali = "";
    		String nofail = "";
    		nofail= getParam("nofail");
    		String idpermohonan = getParam("id_permohonan");
    		String id_simati = getParam("id_simati");
    		String id_Permohonansimati = "";
    		String questioner = getParam("questioner");
    		logic_internal.setIdPermohonanSimati(id_simati);
    		IdPermohonanSimati = logic_internal.getIdPermohonanSimati();
    		this.context.put("IdPermohonanSimati",IdPermohonanSimati);
    		
    		if(dataNotis.size()!=0){
    			Hashtable idn = (Hashtable) dataNotis.get(0);
        		//idUnitPsk = Long.parseLong(idn.get("id_unitpsk").toString());
        		tarikh_bicara = idn.get("tarikh_bicara").toString();
        		id_perbicaraan = (String) idn.get("id_perbicaraan");
        		tempat_bicara = (String) idn.get("tempat_bicara");
        		alamat_bicara1 = (String) idn.get("alamat_bicara1");
        		alamat_bicara2 = (String) idn.get("alamat_bicara2");
        		alamat_bicara3 = (String) idn.get("alamat_bicara3");
        		poskod = (String) idn.get("poskod");
        		id_negeribicara = idn.get("id_negeribicara").toString();
        		peg_pengendali = (String) idn.get("peg_pengendali");
        		
		    	 
    		}
    		this.context.put("tarikh_bicara",tarikh_bicara);
    		this.context.put("id_perbicaraan",id_perbicaraan);
    		this.context.put("tempat_bicara",tempat_bicara);
    		this.context.put("alamat_bicara1",alamat_bicara1);
    		this.context.put("alamat_bicara2",alamat_bicara2);
    		this.context.put("alamat_bicara3",alamat_bicara3);
    		this.context.put("poskod",poskod);
    		this.context.put("id_negeribicara",id_negeribicara);
    		this.context.put("peg_pengendali",peg_pengendali);
    		this.context.put("nofail",nofail);
    		this.context.put("id_permohonan",idpermohonan);
    		this.context.put("id_simati", id_simati);
    		this.context.put("id_Permohonansimati", id_Permohonansimati);
    		this.context.put("questioner", questioner);
    		myLogger.info("questioner = "+questioner);
    		//SET DEFAULT PARAM

    		context.put("selectedTab",0);
            if (!anchor.equals("")){
            	this.context.put("onload", " \"javascript:goToAnchor('" + anchor + "')\"");
            }
    		vm = "app/ppk/frmPerbicaraan.jsp";
    	}
    		else if ("cari".equals(submit)){
    		
    		myLogger.info("Read here cari");
    		carianNotis(usid);
			list = logic4.getListCarian();

    		//data & size default list
			
    		context.put("listNotis", list);
    		context.put("list_size", list.size());

    		//maintain searching value
    		this.context.put("txtNoFail", getParam("txtNoFail"));
    		this.context.put("txtPemohon", getParam("txtPemohon"));
    		this.context.put("txtSimati", getParam("txtSimati"));
    		this.context.put("txtIcSimati", getParam("txtIcSimati"));
    		this.context.put("jenisIc", getParam("jenisIc"));
    		this.context.put("statusFail", getParam("statusFail"));
    		
    		vm = screensenarai;

    	}//close cari
    	
    		else if("maklumatSimati".equals(submit)){
    			//READ SENARAI KEHADIRAN
    			String id_perbicaraan = getParam("id_perbicaraan");
    			String status_perkahwinan = "";
    			status_perkahwinan = getParam("perkahwinan");
    			String anak = getParam("anak");
    			String berhak = getParam("Berhak");
    			String cerai = "";
    			cerai = getParam("cerai");
    			String cerai_mati = "";
    			cerai_mati = getParam("cerai_mati");
    			String hidupMati = "";
    			hidupMati = getParam("hidupMati");
    			String DDA = getParam("DDA");
    			String kosongkan  = getParam("kosongkandia");
    			String bilpasangan  = getParam("bilpasangan");
    			String bapa="";
    			bapa=getParam("bapa");
    			String bilAnak = getParam("bilAnak");
    			String BerhakAnak = getParam("BerhakAnak");
    			String jantinaAnak = getParam("jantinaAnak");
    			String BerhakAnak2 = getParam("BerhakAnak2");
    			String jantinaAnak2 = getParam("jantinaAnak2");
    			String kehadiran2 = getParam("kehadiran2");
    			String hidupMati2 = getParam("hidupMati2");
    			String berhakIbu = getParam("BerhakIbu");
    			String berhakBapa = getParam("BerhakBapa");
    			
    			String DDA2 = getParam("DDA2");
    			String masabapasimatimeninggal = "";
    			masabapasimatimeninggal = getParam("BapaSimatiselection");
    			String kehadiran = getParam("kehadiran");
        		logic_internal.setDataKehadiranBicara_online(id_perbicaraan);
    			listKehadiran = logic_internal.setDataKehadiranBicara_online();
    			this.context.put("listKehadiran", listKehadiran);
    			if ("kosongkandia".equals(kosongkan)){
        			
        			this.context.put("status_perkahwinan", "");
        			this.context.put("cerai", "");
        			this.context.put("cerai_mati", "");
        			this.context.put("bilpasangan", "");
        			this.context.put("anak", "");
        			this.context.put("berhak", "");
        			this.context.put("hidupMati", "");
        			this.context.put("kehadiran", "");
        			this.context.put("DDA", "");
        			this.context.put("BerhakAnak", "");
        			this.context.put("jantinaAnak", "");
        			this.context.put("kehadiran2", "");
        			this.context.put("DDA2", "");
        			this.context.put("BerhakAnak2", "");
        			this.context.put("jantinaAnak2", "");
        			this.context.put("hidupMati2", "");
        			this.context.put("bilAnak", "");
        			this.context.put("berhakIbu", "");
        			this.context.put("berhakBapa", "");
        			
        			
    			}
    			else
    			{
    				this.context.put("status_perkahwinan", status_perkahwinan);
    				this.context.put("cerai", cerai);
    				this.context.put("cerai_mati", cerai_mati);
    				this.context.put("bilpasangan", bilpasangan);
    				this.context.put("anak", anak);
    				this.context.put("berhak", berhak);
    				this.context.put("hidupMati", hidupMati);
    				this.context.put("hidupMati2", hidupMati2);
    				this.context.put("kehadiran", kehadiran);
    				this.context.put("DDA", DDA);
    				this.context.put("BerhakAnak", BerhakAnak);
    				this.context.put("jantinaAnak", jantinaAnak);
    				this.context.put("kehadiran2", kehadiran2);
    				this.context.put("DDA2", DDA2);
    				this.context.put("BerhakAnak2", BerhakAnak2);
    				this.context.put("jantinaAnak2", jantinaAnak2);
    				this.context.put("bilAnak", bilAnak);
    				this.context.put("berhakIbu", berhakIbu);
        			this.context.put("berhakBapa", berhakBapa);
    				
    				
    			}
    			
    			myLogger.info("maklumatSimati");
    			String idpermohonan = getParam("id_permohonan");
    			myLogger.info("idpermohonan = "+idpermohonan);
    			logic_internal.setDataSimati(idpermohonan);
    			listSimati = logic_internal.getDataSimati();
    			this.context.put("listSimati", listSimati);
    			
    			//maklumat pemohon
    			logic_internal.setDataPemohon(idpermohonan);
    			listPemohon = logic_internal.getDataPemohon();
    			Db db = null;
    			try {
    				db = new Db();
    				Vector listsaudara = logic_A.getListsaudaraDb(db);
    				this.context.put("listsaudara", listsaudara);
    				}
    			catch (SQLException se2) {
    				throw new Exception("Error:" + se2.getMessage());
    			}
    			finally {		
    			if (db != null)
    			db.close();
    			}
    			this.context.put("listPemohon", listPemohon);
    			
    			//maklumat waris

    			String id_Permohonansimati = getParam("id_Permohonansimati");
    			logic_internal.setDataWaris(id_Permohonansimati);
    			listWaris = logic_internal.getDataWaris();
    			this.context.put("listWaris", listWaris);

    			String questioner = getParam("namaPenanya");
    			if (questioner == null || "".equals(questioner))
    				{
    				this.context.put("questioner", "0");
    				}
    			else
    				{
    				this.context.put("questioner", questioner);
    				}
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="2";
                }
                if (bapa!="")
                {
                	context.put("bapa",bapa);
                	context.put("masabapasimatimeninggal",masabapasimatimeninggal);
                }
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmPerbicaraan.jsp";
    		}
    	

    		
    	
    		else if("maklumatKehadiran".equals(submit)){
    			//READ SENARAI KEHADIRAN
    			String id_perbicaraan = getParam("id_perbicaraan");
        		logic_internal.setDataKehadiranBicara_online(id_perbicaraan);
    			listKehadiran = logic_internal.setDataKehadiranBicara_online();
    			this.context.put("listKehadiran", listKehadiran);
    			
    			myLogger.info("maklumatKehadiran");
    			String idpermohonan = getParam("id_permohonan");
    			myLogger.info("idpermohonan = "+idpermohonan);
    			logic_internal.setDataSimati(idpermohonan);
    			listSimati = logic_internal.getDataSimati();
    			this.context.put("listSimati", listSimati);
    			
    			
    			
    			
    			//maklumat pemohon
    			logic_internal.setDataPemohon(idpermohonan);
    			listPemohon = logic_internal.getDataPemohon();
    			Db db = null;
    			try {
    				db = new Db();
    				Vector listsaudara = logic_A.getListsaudaraDb(db);
    				this.context.put("listsaudara", listsaudara);
    				}
    			catch (SQLException se2) {
    				throw new Exception("Error:" + se2.getMessage());
    			}
    			finally {		
    			if (db != null)
    			db.close();
    			}
    			this.context.put("listPemohon", listPemohon);
    			
    			//maklumat waris

    			String id_Permohonansimati = getParam("id_Permohonansimati");
    			logic_internal.setDataWaris(id_Permohonansimati);
    			listWaris = logic_internal.getDataWaris();
    			this.context.put("listWaris", listWaris);

    			
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="2";
                }
               
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmPerbicaraan.jsp";
    		}
    	
    	
    		else if("ambilKeterangan".equals(submit)){
    			myLogger.info("ambilKeterangan");
    			
    			this.context.put("ambilKeterangan", 1);
    			//this.context.put("View", listMaklumatSimati);
    			
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="3";
                }
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmPerbicaraan.jsp";
                return vm;
    		}
    		else if("maklumatPemohon".equals(submit)){
    			myLogger.info("maklumatPemohon");
    			String idpermohonan = getParam("id_permohonan");
    			myLogger.info("idpermohonan = "+idpermohonan);
    			logic_internal.setDataPemohon(idpermohonan);
    			listPemohon = logic_internal.getDataPemohon();
    			Db db = null;
    			try {
    				db = new Db();
    				Vector listsaudara = logic_A.getListsaudaraDb(db);
    				this.context.put("listsaudara", listsaudara);
    				}
    			catch (SQLException se2) {
    				throw new Exception("Error:" + se2.getMessage());
    			}
    			finally {		
    			if (db != null)
    			db.close();
    			}
    			this.context.put("listPemohon", listPemohon);
    			//this.context.put("View", listMaklumatSimati);
    			
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="3";
                }
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmPerbicaraan.jsp";
    		}
    	
    		else if("maklumatWaris".equals(submit)){
    			myLogger.info("maklumatWaris");
    			String idpermohonan = getParam("id_permohonan");
    			String id_Permohonansimati = getParam("id_Permohonansimati");
    			myLogger.info("idpermohonan = "+idpermohonan);
    			logic_internal.setDataWaris(id_Permohonansimati);
    			listWaris = logic_internal.getDataWaris();
    			this.context.put("listWaris", listWaris);

    			this.context.put("listPemohon", listPemohon);
    			//this.context.put("View", listMaklumatSimati);
    			
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="3";
                }
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmPerbicaraan.jsp";
    		}
    	
    		else if("tambahKehadiran".equals(submit)){
    			myLogger.info("tambahKehadiran");
    			String idpermohonan = getParam("id_permohonan");
    			String id_Permohonansimati = getParam("id_Permohonansimati");
    			myLogger.info("idpermohonan = "+idpermohonan);
    			logic_internal.setDataWaris(id_Permohonansimati);
    			listWaris = logic_internal.getDataWaris();
    			this.context.put("listWaris", listWaris);

    			this.context.put("listPemohon", listPemohon);
    			//this.context.put("View", listMaklumatSimati);
    			
        		String selectedTab = "";

        		selectedTab = getParam("tabId").toString();

                if (selectedTab == null || "".equals(selectedTab))
                {
                	selectedTab="3";
                }
                context.put("selectedTab",selectedTab);
                
                vm = "app/ppk/frmTambahKehadiran.jsp";
    		}

    	
		else if("maklumatTambahKehadiran".equals(submit)){
			myLogger.info("maklumattambahKehadiran");
			String id_perbicaraan = getParam("id_perbicaraan");
			String nama = getParam("nama");
			String hubungan = getParam("hubungan");
			String kehadiran = getParam("kehadiran");
    		logic_internal.setDataKehadiranBicara_online2(id_perbicaraan, nama, hubungan, kehadiran);
			listKehadiran = logic_internal.setDataKehadiranBicara_online();
			this.context.put("listKehadiran", listKehadiran);
			
    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="3";
            }
            context.put("selectedTab",selectedTab);
            
            vm = "app/ppk/frmTambahKehadiran.jsp";
		}

    	else
    	{
    		//get list data
    		list.clear();
	        FrmSenaraiFailKptsPerbcrnData.setList(usid);
			list = FrmSenaraiFailKptsPerbcrnData.getList();
			
    		this.context.put("listNotis", list.size());
    		context.put("list", list.size());
    		myLogger.info("Read here BICARA ONLINE");

    		vm = screensenarai;

    	}//close else

        //this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
       // this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
        //this.context.put("id_perbicaraan", idperbicaraan);
       // this.context.put("flagForView", flagForView);

       setupPage(session,action,list);
       myLogger.info("vm = "+vm);
       return vm;

	}//close template


/*
 *
 *	::Method::
 *
 */

	//carian
	private void carianNotis(String usid) throws Exception{

		String noFail = getParam("txtNoFail");
		myLogger.info("Read here carianNotis");
		String namaPemohon = getParam("txtPemohon");
		String namaSimati = getParam("txtSimati");
		String icSimati = getParam("txtIcSimati");
		String JenisIc = getParam("jenisIc");
		String statusFail = getParam("statusFail");

		logic4.setCarianFail(noFail,namaPemohon,namaSimati,icSimati,JenisIc,usid,statusFail);
	}

	


	


	

	//simpan Data Rayuan [update]
	private void simpanDataRayuan(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//get id
	    	String id_rayuan = getParam("id_rayuan");

	    	//get data perayu
	    	//String tarikh_rayuan = getParam("txdTarikhRayuan");
	    	String perkara_rayu = getParam("txtPerkaraRayu");
	    	//String alasan_rayuan = getParam("txtAlasanRayuan");

	    	//send id to model
	    	//h.put("tarikh_rayuan",tarikh_rayuan);
	    	h.put("perkara_rayu",perkara_rayu);
	    	//h.put("alasan_rayuan",alasan_rayuan);
	    	h.put("id_rayuan",id_rayuan);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

	    	model.simpanDataRayuan(h);

	  }//simpan Data Rayuan [update]

	


	//update serahan
	private void updateSerahan(HttpSession session,String idperbicaraan) throws Exception{

			Hashtable h = new Hashtable();

			//get id
	    	String id_rayuan = getParam("id_rayuan");
	    	String id_serahanrayuan = getParam("id_serahanrayuan");
	    	String id_permohonan = getParam("id_permohonan");

	    	//get data perayu
	    	String tarikh_serahan = getParam("txdTarikhSerahan");
	    	String nama_penerima = getParam("txtNamaPenerima");
	    	String alamatSerah1 = getParam("alamatSerah1");
	    	String alamatSerah2 = getParam("alamatSerah2");
	    	String alamatSerah3 = getParam("alamatSerah3");
	    	String txtPoskodSerah = getParam("txtPoskodSerah");
	    	String txtBandarSerah = getParam("txtBandarSerah");
	    	String idnegeri = getParam("socNegeri");

	    	String bayaranR = getParam("txtBayaranRekod");
	    	String id_bayaran = getParam("id_bayaran");


	    	//11122009
	    	String tarikhS = getParam("tarikhS");
	    	h.put("tarikhS", tarikhS);

	    	//send id to model
	    	h.put("id_serahanrayuan", id_serahanrayuan);
	    	h.put("tarikh_serahan", tarikh_serahan);
	    	h.put("nama_penerima",nama_penerima );
	    	h.put("alamatSerah1", alamatSerah1);
	    	h.put("alamatSerah2", alamatSerah2);
	    	h.put("alamatSerah3", alamatSerah3);
	    	h.put("txtPoskodSerah", txtPoskodSerah);
	    	h.put("txtBandarSerah", txtBandarSerah);
	    	h.put("idnegeri",idnegeri);
	    	h.put("id_rayuan",id_rayuan);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
	    	h.put("bayaranR",bayaranR);
	    	h.put("id_bayaran",id_bayaran);
	    	h.put("id_permohonan", id_permohonan);

	    	//-- 02122009
	    	model.setDataMstRayuan(idperbicaraan);
    		Vector dataMstRayuan = model.getDataMstRayuan();
    		String id_notisobmst = "";
    		if(dataMstRayuan.size()!=0){
				Hashtable mst = (Hashtable) dataMstRayuan.get(0);
				id_notisobmst = mst.get("id_notisobmst").toString();
			}


	    	String jenis_status = getParam("sorStatus");
	    	String jenis_serah = getParam("sorJenisSerah");
	    	String catatan = getParam("txtCatatan");
	    	String id_penghantar = getParam("txtNamaPenghantar");
	    	String no_surat_daftar = getParam("txtNoDaftarPos");

	    	String nama = "";
			Vector getPenghantarNotis = model2.getDetailPenghantarNotis(id_penghantar);
			if(getPenghantarNotis.size()!=0){
				Hashtable x = (Hashtable) getPenghantarNotis.get(0);
				nama = x.get("nama").toString();
			}

			h.put("id_notisobmst",id_notisobmst);
			h.put("idperbicaraan",idperbicaraan);
			h.put("nama_penghantar",nama);
			h.put("id_penghantar",id_penghantar);
			h.put("jenis_serah",jenis_serah);
			h.put("jenis_status",jenis_status);
			h.put("daftar_pos",no_surat_daftar);
			h.put("catatan",catatan);

			if(id_notisobmst!=""){
				model.updateMstRayuan(h);
			}else{
				model.addMstRayuan(h);
			}
	    	//-- 02122009

	    	model.updateSerahan(h);

	  }//update serahan


	//simpan Tambahan Peguam
	private void simpanTambahanPeguam(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			String socNamaFirma = "";

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_pemohon = getParam("id_pemohon");
	    	String id_perayu = getParam("id_perayu");

	    	int sorPeguam = getParamAsInteger("sorPeguam");

	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	socNamaFirma = getParam("socNamaFirma");

	    	// 1 = update
	    	// 2 = insert


	    	if(sorPeguam==1){
	    		String nama_firma = "";
	    		if(socNamaFirma!=""){
	    		Vector detailFirma = model.getDetailFirma(socNamaFirma);
        		Hashtable onc = (Hashtable) detailFirma.get(0);
	    		nama_firma = onc.get("nama_firma").toString();
	    		}
	    		if(socNamaFirma!=""){
	    			h.put("Xdefault",1);
		    		h.put("id_peguam", socNamaFirma);
	    		}else{
	    			h.put("Xdefault",2);
		    		h.put("id_peguam", "");
		    		h.put("txtNamaFirma",txtNamaFirma);
	    		}


	    	}else if(sorPeguam==2){
	    		h.put("txtNamaFirma",txtNamaFirma);
	    		h.put("Xdefault",2);
	    		h.put("id_peguam", "");
	    	}else{
	    		h.put("txtNamaFirma",txtNamaFirma);
	    		h.put("Xdefault",2);
	    		h.put("id_peguam", "");
	    	}

	    	//get data peguam
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	String txtBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");

	    	//send perayu to model
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_pemohon",id_pemohon);
	    	h.put("id_perayu",id_perayu);
	    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

	    	//send peguam to model
	    	h.put("txtNamaFirma",txtNamaFirma);
	    	h.put("txtNoRujukan",txtNoRujukan);
	    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
	    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
	    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
	    	h.put("txtPoskodPeguam",txtPoskodPeguam);
	    	h.put("txtBandarPeguam",txtBandarPeguam);
	    	h.put("txtNoTelefon",txtNoTelefon);
	    	h.put("txtNoFaks",txtNoFaks);
	    	h.put("txtEmel",txtEmel);
	    	h.put("socNegeriPeguam",socNegeriPeguam);


	    	model.simpanTambahanPeguam(h);

	  }//close simpan Maklumat PP

	//update Tambah Peguam
	private void updateTambahPeguam(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_pemohon = getParam("id_pemohon");
	    	String id_perayu = getParam("id_perayu");
	    	String id_peguam = getParam("id_peguamX");

	    	//validation name
	    	String onchangex = getParam("onchangex");


	    	String a,b,c = "";
	    	String kplama = "";
	    	String kplain = "";

	    	//get data perayu
	    	a = getParam("EtxtNoKPBaru1");
	    	b = getParam("EtxtNoKPBaru2");
	    	c = getParam("EtxtNoKPBaru3");
	    	String txtNoKPBaru = a+b+c;
	    	kplama = getParam("EtxtNoKPLama");
	    	kplain = getParam("EtxtNoKPLain");


	    	String txtNamaPerayu = getParam("EsocPerayu");
	    	String txtAlamatPerayu1 = getParam("EtxtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("EtxtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("EtxtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("EtxtPoskodPerayu");
	    	String txtBandarPerayu = getParam("EtxtBandarPerayu");
	    	String socNegeriPerayu = getParam("socNegeriPerayu");

	    	//get data peguam
	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	String txtBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");

	    	String namaPERAYU = "";


	    	if(onchangex.equals("yes")){

	    		Vector onchangeListOB = model.getOnchangeListOB(txtNamaPerayu);
	    		Hashtable xyz = (Hashtable) onchangeListOB.get(0);
		    	namaPERAYU = xyz.get("nama_ob").toString();

		    	h.put("txtNamaPerayu",namaPERAYU);

	    	}else{
	    		h.put("txtNamaPerayu",txtNamaPerayu);
	    	}


	    	//send id to model
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_pemohon",id_pemohon);
	    	h.put("id_perayu",id_perayu);
	    	h.put("id_peguam",id_peguam);

	    	//send perayu to model
	    	h.put("txtNoKPBaru",txtNoKPBaru);
	    	h.put("txtNoKPLama",kplama);
	    	h.put("txtNoKPLain",kplain);
	    	h.put("txtAlamatPerayu1",txtAlamatPerayu1);
	    	h.put("txtAlamatPerayu2",txtAlamatPerayu2);
	    	h.put("txtAlamatPerayu3",txtAlamatPerayu3);
	    	h.put("txtPoskodPerayu",txtPoskodPerayu);
	    	h.put("txtBandarPerayu",txtBandarPerayu);
	    	h.put("socNegeriPerayu",socNegeriPerayu);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

	    	//send peguam to model
	    	h.put("txtNamaFirma",txtNamaFirma);
	    	h.put("txtNoRujukan",txtNoRujukan);
	    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
	    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
	    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
	    	h.put("txtPoskodPeguam",txtPoskodPeguam);
	    	h.put("txtBandarPeguam",txtBandarPeguam);
	    	h.put("txtNoTelefon",txtNoTelefon);
	    	h.put("txtNoFaks",txtNoFaks);
	    	h.put("txtEmel",txtEmel);
	    	h.put("socNegeriPeguam",socNegeriPeguam);

	    	if(onchangex.equals("yes")){
	    		h.put("id_ob", txtNamaPerayu);
	    		model.inserttableOB(h);
	    	}else{
	    		model.updatetableOB(h);
	    	}

	    	model.updatePerayu(h);
	    	model.updatePeguam(h);

	  }//close update Maklumat PP

	private void simpanRekodRayuan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		//get id
    	String id_rayuan = getParam("id_rayuan");

    	//get data
    	String asas_keputusan = getParam("txtAsasKeputusan");
    	String nota_bicara = getParam("txtNotaBicara");
    	String qBorangA = getParam("qBorangA");
    	String qBorangSA = getParam("qBorangSA");
    	String qBorangDDA = getParam("qBorangDDA");
    	String qBorangP = getParam("qBorangP");
    	String q1BorangP = getParam("q1BorangP");
    	String q1BorangA = getParam("q1BorangA");
    	String q1BorangSA = getParam("q1BorangSA");
    	String q1BorangDDA = getParam("q1BorangDDA");
    	
    	String feeLain1 = getParam("textfeeLainHidden");
    	String feeLaina1 = getParam("feeLaina1");
    	String qfeeLain1 = getParam("qfeeLain1");
    	String jumlahfeeLain1 = getParam("jumlahfeeLain1");
    	
    	String feeLain2 = getParam("textfeeLainHidden2");
    	String feeLaina2 = getParam("feeLaina2a");
    	String qfeeLain2 = getParam("qfeeLain2a");
    	String jumlahfeeLain2 = getParam("jumlahfeeLain2");
    	
    	String feeLain3 = getParam("textfeeLainHidden3");
    	String feeLaina3 = getParam("feeLaina3a");
    	String qfeeLain3 = getParam("qfeeLain3a");
    	String jumlahfeeLain3 = getParam("jumlahfeeLain3");
    	
    	String feeLain4 = getParam("textfeeLainHidden4");
    	String feeLaina4 = getParam("feeLaina4a");
    	String qfeeLain4 = getParam("qfeeLain4a");
    	String jumlahfeeLain4 = getParam("jumlahfeeLain4");
    	
    	String feeLain5 = getParam("textfeeLainHidden5");
    	String feeLaina5 = getParam("feeLaina5a");
    	String qfeeLain5 = getParam("qfeeLain5a");
    	String jumlahfeeLain5 = getParam("jumlahfeeLain5");
    	
    	String feeLain6 = getParam("textfeeLainHidden6");
    	String feeLaina6 = getParam("feeLaina6a");
    	String qfeeLain6 = getParam("qfeeLain6a");
    	String jumlahfeeLain6 = getParam("jumlahfeeLain6");
    	
    	String feeLain7 = getParam("textfeeLainHidden7");
    	String feeLaina7 = getParam("feeLaina7a");
    	String qfeeLain7 = getParam("qfeeLain7a");
    	String jumlahfeeLain7 = getParam("jumlahfeeLain7");
    	
    	String feeLain8 = getParam("textfeeLainHidden8");
    	String feeLaina8 = getParam("feeLaina8a");
    	String qfeeLain8 = getParam("qfeeLain8a");
    	String jumlahfeeLain8 = getParam("jumlahfeeLain8");
    	
    	String textLain1 = getParam("textLainHidden");
    	String textLain2 = getParam("textLainHidden2");
    	String textLain3 = getParam("textLainHidden3");
    	String textLain4 = getParam("textLainHidden4");
    	String textLain5 = getParam("textLainHidden5");
    	String textLain6 = getParam("textLainHidden6");
    	String textLain7 = getParam("textLainHidden7");
    	String textLain8 = getParam("textLainHidden8");
    	String jumlahAllfee = getParam("jumlahh");
    	
    	//send id to model
    	h.put("F2LAMPIRAN1",feeLain1);
    	h.put("FLAMPIRAN1",feeLaina1);
    	h.put("Lampiran1", textLain1);
    	h.put("qfeeLain1", qfeeLain1);
    	h.put("qBorangA",qBorangA);
    	h.put("qBorangP", qBorangP);
    	h.put("qBorangDDA", qBorangDDA);
    	h.put("qBorangSA", qBorangSA);
    	
    	h.put("q1BorangA",q1BorangA);
    	h.put("q1BorangP", q1BorangP);
    	h.put("q1BorangDDA", q1BorangDDA);
    	h.put("q1BorangSA", q1BorangSA);
    	h.put("jumlahAllfee" ,jumlahAllfee);
    	
    	h.put("FLAMPIRAN2",feeLaina2);
    	h.put("F2LAMPIRAN2",feeLain2); 	
    	h.put("qfeeLain2", qfeeLain2);
    	h.put("Lampiran2", textLain2);
    	
    	h.put("FLAMPIRAN3",feeLaina3);
    	h.put("F2LAMPIRAN3",feeLain3); 	
    	h.put("qfeeLain3", qfeeLain3);
    	h.put("Lampiran3", textLain3);
    	
    	h.put("FLAMPIRAN4",feeLaina4);
    	h.put("F2LAMPIRAN4",feeLain4); 	
    	h.put("qfeeLain4", qfeeLain4);
    	h.put("Lampiran4", textLain4);
    	
    	h.put("FLAMPIRAN5",feeLaina5);
    	h.put("F2LAMPIRAN5",feeLain5); 	
    	h.put("qfeeLain5", qfeeLain5);
    	h.put("Lampiran5", textLain5);
    	
    	h.put("FLAMPIRAN6",feeLaina6);
    	h.put("F2LAMPIRAN6",feeLain6); 	
    	h.put("qfeeLain6", qfeeLain6);
    	h.put("Lampiran6", textLain6);
    	
    	h.put("FLAMPIRAN7",feeLaina7);
    	h.put("F2LAMPIRAN7",feeLain7); 	
    	h.put("qfeeLain7", qfeeLain7);
    	h.put("Lampiran7", textLain7);
    	
    	h.put("FLAMPIRAN8",feeLaina8);
    	h.put("F2LAMPIRAN8",feeLain8); 	
    	h.put("qfeeLain8", qfeeLain8);
    	h.put("Lampiran8", textLain8);
    	
    	h.put("asas_keputusan",asas_keputusan);
    	h.put("nota_bicara",nota_bicara);
    	h.put("id_rayuan",id_rayuan);
    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

    	//delect checkbox
    	model.cbObDelete(id_rayuan);

    	//checkbox
    	String[] cbsemaks = request.getParameterValues("cbsemaks");

    	if(cbsemaks!=null){
    		for (int i = 0; i < cbsemaks.length; i++) {
    			model.addSelectedOB(h,cbsemaks[i]);
    		}
    	}

    	model.simpanRekodRayuan(h);


  }//close simpanRekodRayuan

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
		this.context.put("listNotis",paging.getPage(page));
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
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
	}

}//close class
