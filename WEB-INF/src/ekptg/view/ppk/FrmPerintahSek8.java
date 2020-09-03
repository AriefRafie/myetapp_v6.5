package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPerintahSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.view.admin.Push;
//import etanah.ws.ppk.EtanahPPKManager;

/**
 * 
 *
 */
public class FrmPerintahSek8 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPerintahSek8Data logic = new FrmPerintahSek8Data();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	static Logger myLogger = Logger.getLogger(FrmPerintahSek8.class);
	String userId = "";
	
	Utils utils = new Utils();
	//String nofail = getParam("noFail3");
	String role = null;
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	FrmPrmhnnSek8InternalData logic_internal = new FrmPrmhnnSek8InternalData();
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		myLogger.info("userId======"+userId);
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    } 
	    
	    role = (String)session.getAttribute("myrole");
	    myLogger.info("CURRENT ROLE :"+role);
	   
	    
	    

	    //GET DEFAULT PARAM
		String vm = ""; 
		int simpanStatus = 2;
		String noFail2 = getParam("noFail3");
		String noFail1 = getParam("noFail");
		String kemaskini = getParam("kemaskini");
		System.out.println("noFail1 = " + noFail1);
		System.out.println("noFail2A = " + noFail2);
		System.out.println("kemaskini dari senarai = " + kemaskini);
        String actionPerintah = getParam("actionPerintah");
        String mode = getParam("mode");
        String hitButt = getParam("hitButt");
        
        String submit = getParam("command"); 
        String submit2 = getParam("command2");
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String selectedTabUpper = getParam("selectedTabUpper").toString();	
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower) ) {
			selectedTabLower = "0";
		}
		String anchor = getParam("anchor");

		String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        
        //GET VALUE PARAM 
		String idPermohonanSimati = getParam("idPermohonanSimati");
        String idPermohonan = getParam("idPermohonan");
        String idPerintah = getParam("idPerintah");
        String idPerbicaraan = getParam("idPerbicaraan");
        String idSimati = getParam("idSimati");
        String idStatus = getParam("idStatus");       
        String idHTA = getParam("idHTA");
        String idHA = getParam("idHA");   
        String idJenisHA = getParam("idJenisHA");
        
        //GET FLAG PARAM
		String flagAdaHTA = "0";
		String flagAdaHTATH = "0";
		String flagAdaHA = "0";
		
		String flagAdaHTAPT = "0";
		String flagAdaHAPT = "0";
		String flagAdaHTAPKT = "0";
		String flagAdaHAPKT = "0";
		String flagAdaHTAPL = "0";
		String flagAdaHAPL = "0";
		String flagAdaHTAPF = "0";
		String flagAdaHAPF = "0";
		
		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		
		String flagSelesaiHTA = getParam("flagSelesaiHTA");
		String flagSelesaiHA = "";//getParam("flagSelesaiHA");
		String flagSelesaiHAARB = getParam("flagSelesaiHAARB");
		String adaKIV = "";
		
		String flagSelesaiPembahagian = "Y";
		this.context.put("systemMsg", "");
		this.context.put("SimpanStatus", simpanStatus);
		
        //VECTOR
        Vector list = new Vector();
        Vector beanMaklumatPermohonan = new Vector();
        Vector beanHeaderDetail = new Vector();
        
        list.clear();
        beanMaklumatPermohonan.clear();
        beanHeaderDetail.clear();
        
        Vector listHTA = new Vector();
        Vector beanMaklumatHTA = new Vector();
        Vector listHTAPopup = new Vector();
        Vector listHTATH = new Vector();
        Vector beanMaklumatHTATH = new Vector();
        Vector listHA = new Vector();
        Vector listHAARB = new Vector();
        Vector listIdPerintahhaobmst = new Vector();
        Vector listOBARB = new Vector();        
        Vector beanMaklumatHA = new Vector();
        Vector listHAPopup = new Vector();
        
		Vector penghantarNotisByJkptg = new Vector();
		Vector penghantarNotis = new Vector();
		Vector getPenghantarNotis = new Vector();
		Vector getSelectedPenghantarNotis = new Vector();
		Vector getSelectedPenghantarNotisByJkptg = new Vector();
        
		Vector keputusanPermohonan = new Vector();//arief add
		Vector dataPerintah = new Vector();//arief add
		Vector validPegPengendali = new Vector();//arief add
		validPegPengendali.clear();//arief add
		
		// clear data vector
		getSelectedPenghantarNotisByJkptg.clear();
		getSelectedPenghantarNotis.clear();
		getPenghantarNotis.clear();
		penghantarNotis.clear();
		penghantarNotisByJkptg.clear();
		
		listHTA.clear();
		listHAARB.clear();
        beanMaklumatHTA.clear();
        listHTAPopup.clear();
        listHTATH.clear();
        beanMaklumatHTATH.clear();
        listHA.clear();
        beanMaklumatHA.clear();
        listHAPopup.clear();
        
        Vector listHTAPT = new Vector();
        Vector listHAPT = new Vector();
        Vector listHTAPKT = new Vector();
        Vector listHAPKT = new Vector();
        Vector listHTAPL = new Vector();
        Vector listHAPL = new Vector();
        Vector listHTAPF = new Vector();
        Vector listHAPF = new Vector();
        Vector beanMaklumatHTAPL = new Vector();
        Vector beanMaklumatHAPL = new Vector();      
        
        listHTAPT.clear();
        listHAPT.clear();
        listHTAPKT.clear();
        listHAPKT.clear();
        listHTAPL.clear();
        listHAPL.clear();
        listHTAPF.clear();
        listHAPF.clear();        
        
        Vector listHTAPTDTL = new Vector();
        Vector listHAPTDTL = new Vector();
        Vector listHTAPKTDTL = new Vector();
        Vector listHAPKTDTL = new Vector();
        Vector listHTAPFDTL = new Vector();
        Vector listHAPFDTL = new Vector();
        Vector listPembahagianHTAPKTDTL = new Vector(); 
        Vector listOBatas18 = new Vector();
        Vector namaOBnKP = new Vector();
        Vector listpeguam = new Vector(); 
        //Vector list = new Vector();
        
        listHTAPTDTL.clear();
        listHAPTDTL.clear();
        listHTAPKTDTL.clear();
        listHAPKTDTL.clear();
        listHTAPFDTL.clear();
        listHAPFDTL.clear();
        listPembahagianHTAPKTDTL.clear();
        listOBatas18.clear();
		
        id = getParam("idPermohonan");
        String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");
		context.put("usid", "");    
		//System.out.println("-------id-------" + id);
		//System.out.println("-------usid-------" + usid);
				// get info pemohon
     			//modelNotis.setListSemak(id, usid);
     			//list = modelNotis.getListSemak();
     	        
     	     // data pemohon
		
		String id_permohonansimati = "";
		if(!id.equals(""))
		{
     				myLogger.info("::::Baca di sini data pemohon::::");
     	     		FrmPrmhnnSek8Notis.setListSemak(id, usid);
     	     		list = FrmPrmhnnSek8Notis.getListSemak();
     	     		// hashtable maklumat header
     	     		//headerppk_baru(session, id, "Y", "", "T");aishah tutup 5/6/2017
     	     		
     	     		if (list.size() != 0) {
     	     			Hashtable ls = (Hashtable) list.get(0);
     	     			id_permohonansimati = ls.get("id_permohonansimati").toString();
     	     		}
     	        
     	     		// get list ob atas 18
	     			FrmPrmhnnSek8Notis.setListOBatas18Semua(id_permohonansimati, idSimati, "8");
	     			listOBatas18 = FrmPrmhnnSek8Notis.getListOBatas18Semua();
	     			context.put("listOBatas18", listOBatas18);
	     			
	     			logic_internal.setDataPeguamDalamPerintah(id);
	    			listpeguam = logic_internal.getDataPeguam();
	    			this.context.put("listPentingPeguam", listpeguam);
		}
	     			context.put("selectedDropdown", "");    
	     			//context.put("namaPenerima", "");
     
        //Salnizam edit starts
        /*String idPejabatJKPTG = "";
        
        if (list.size() != 0) {
        Hashtable ls = (Hashtable) list.get(0);      
        idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
        System.out.println("-------idPejabatJKPTG-------" + idPejabatJKPTG);
        }
		modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
		penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
		// and
		modelNotis.setPenghantarNotis();
		penghantarNotis = modelNotis.getPenghantarNotis();

		if (penghantarNotisByJkptg.size() != 0) {
			context.put("penghantarNotis", penghantarNotisByJkptg);
		} else {
			context.put("penghantarNotis", penghantarNotis);
		}
		*/
     	     			
     	   			
		String id_penghantarnotis = getParam("txtNamaPenghantarNotis");
		//System.out.println("id_penghantarnotis = " + id_penghantarnotis);
		//System.out.println("hitButt2 = " + hitButt2);
		// Masukkan nama waris. Start.
		
		
		myLogger.info("hitButt======"+hitButt);
		myLogger.info("actionPerintah======"+actionPerintah);
		if (hitButt.equals("onchangemyList")) {
			
			String penyerah = getParam("txtNamaPenghantarNotis");	
			String TARIKH_SERAHAN = getParam("txtTarikh");
			String txtNoKpPenerima = getParam("txtNoKpPenerima");
			String radioJenis = getParam("radioJenis");	
			String txtCatatan = getParam("txtCatatan");
			String selectionBox = "";
			selectionBox = getParam("myList");
			System.out.println("-------selectionBox-------" + selectionBox);
			// check samaada penerima Lain-lain atau tidak
			String penerima = getParam("myList");
			//if (penerima.substring(0,5) == "peguam")
			//{
			//	penerima = penerima.substring(6)
			//}
			System.out.println("-------TARIKH_SERAHAN-------" + TARIKH_SERAHAN);
			System.out.println("-------penerima-------" + penerima);
			System.out.println("-------peguam-------" + penerima.substring(0,6));
			if (penerima.equals("Lain-lain")) 
			{
				System.out.println("-------Lain-lain -------");
				context.put("SimpanStatus", "1");
				context.put("NAMA_PENERIMA", "");
				context.put("NAMA_PENYERAH", penyerah);
				context.put("CATATAN", txtCatatan);
				context.put("TARIKH_SERAHAN", TARIKH_SERAHAN);
				context.put("JENIS_PENGHANTARAN", radioJenis);
				context.put("kp1", "");
				context.put("kp2", "");
				context.put("kp3", "");
				context.put("NO_KP_PENERIMA", "");
				context.put("jeniskp", "");
				context.put("selectionBox",selectionBox);
			}
			else if (penerima.substring(0,6).equals("peguam")) 
				{
					System.out.println("-------Lain-lain -------");
					context.put("SimpanStatus", "1");
					context.put("NAMA_PENERIMA", penerima.substring(6));
					context.put("NAMA_PENYERAH", penyerah);
					context.put("CATATAN", txtCatatan);
					context.put("TARIKH_SERAHAN", TARIKH_SERAHAN);
					context.put("JENIS_PENGHANTARAN", radioJenis);
					context.put("kp1", "");
					context.put("kp2", "");
					context.put("kp3", "");
					context.put("NO_KP_PENERIMA", "");
					context.put("jeniskp", "");
					context.put("selectionBox",selectionBox);
				
			}
			else
			{
			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
	
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			// get list ob
			modelNotis.setListOBatas18Semua(id_permohonansimati, idSimati,"8");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("listOBatas18", listOBatas18);

			String dropdown = getParam("myList");

			//String id_penghantarnotis = getParam("txtNamaPenghantarNotis");
			//String tarikh_serahan = getParam("txdTarikhSerahan");
			String nama_penerima = getParam("txtNamaPenerima");
			String catatan = getParam("txtCatatan");
			String kp1 = getParam("txtNoKpPenerima1");
			String kp2 = getParam("txtNoKpPenerima2");
			String kp3 = getParam("txtNoKpPenerima3");
			String myList = getParam("myList");
			String no_kp_penerima ="";
			String namaPen = "";
			String xkp1 = "";
			String xkp2 = "";
			String xkp3 = "";
			String kplama = "";
			String kplain = "";

			namaOBnKP = modelNotis.getNameNKPByDropdown(dropdown,
					getParam("id_permohonansimati_atheader"));
			if (namaOBnKP.size() != 0) {
				Hashtable obkp = (Hashtable) namaOBnKP.get(0);
				namaPen = obkp.get("nama_ob").toString();
				xkp1 = obkp.get("no_kp_baru1").toString();
				xkp2 = obkp.get("no_kp_baru2").toString();
				System.out.println("-------no_kp_penerima2-------" + xkp1 + xkp2);
				xkp3 = obkp.get("no_kp_baru3").toString();
				kplama = obkp.get("no_kp_lama").toString();
				kplain = obkp.get("no_kp_lain").toString();
			}

			if (namaPen != "") {
				context.put("NAMA_PENERIMA", namaPen);
			} else {
				context.put("NAMA_PENERIMA", nama_penerima);
			}

			if (xkp1 != "") {
				context.put("jeniskp", "baru");
				no_kp_penerima = xkp1+xkp2+xkp3;
				context.put("no_kp_penerima",no_kp_penerima);
				System.out.println("-------no_kp_penerima3-------" + no_kp_penerima);
				context.put("kp1", xkp1);
				context.put("kp2", xkp2);
				context.put("kp3", xkp3);
				
			} else if (kplama != "") {
				context.put("jeniskp", "lama");
				context.put("kplama", kplama);
			} else if (kplain != "") {
				context.put("jeniskp", "lain");
				context.put("kplain", kplain);
			} else {
				context.put("jeniskp", "");
				context.put("kp1", "");
				context.put("kp2", "");
				context.put("kp3", "");
				context.put("kplama", "");
				context.put("kplain", "");
			}
			System.out.println("-------penyerah2-------" + penyerah);
			System.out.println("-------TARIKH_SERAHAN-------" + TARIKH_SERAHAN);
			context.put("NAMA_PENYERAH", penyerah);
			context.put("TARIKH_SERAHAN", TARIKH_SERAHAN);
			context.put("CATATAN", txtCatatan);
			context.put("SimpanStatus", "1");
			context.put("JENIS_PENGHANTARAN", radioJenis);
			context.put("penerima", penerima);
			context.put("selectionBox",selectionBox);
			}
			context.put("userRole", role); 
			vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
			return vm;
 
		}// close onchangemyList

		
		
		
		// Masukkan nama waris. End.
		
		
		
		/* User tak nak nama penghantar notis ditarik daripada database
		if (id_penghantarnotis != "") {
			String nama = "";
			String kod = "";
			// get data keputusan permohonan
			getPenghantarNotis = modelNotis
					.getDetailPenghantarNotis(id_penghantarnotis);
			if (getPenghantarNotis.size() != 0) {
				Hashtable x = (Hashtable) getPenghantarNotis.get(0);
				nama = x.get("nama").toString();
				kod = x.get("kod_penghantar_notis").toString();
			}

			context.put("idPenghantar", id_penghantarnotis);
			context.put("namaPenghantar", nama);
			context.put("kodPenghantar", kod);

			// selected penghantar ob
			getSelectedPenghantarNotisByJkptg = modelNotis
					.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,
							id_penghantarnotis);
			// and
			getSelectedPenghantarNotis = modelNotis
					.getSelectedPenghantarNotis(id_penghantarnotis);
			// check penghantar notis ade o xde dlm negeri
			modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
			penghantarNotisByJkptg = modelNotis
					.getPenghantarNotisByJkptg();

			if (idPejabatJKPTG != "") {
				if (penghantarNotisByJkptg.size() != 0) {
					context.put("penghantarNotisONCHANGE",
							getSelectedPenghantarNotisByJkptg);
				} else {
					context.put("penghantarNotisONCHANGE",
							getSelectedPenghantarNotis);
				}
			} else {
				context.put("penghantarNotisONCHANGE",
						getSelectedPenghantarNotis);
			}

			context.put("onchangeMyList", "yes");

		} else {
			context.put("onchangeMyList", "no");
		}
        */
        if (hitButt.equals("Batal")) {
        	System.out.println("-------Read Batal KemaskiniLaporan-------");
        	simpanStatus = 2;
        	this.context.put("SimpanStatus", simpanStatus);
        	
        	vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
        	
        }
        
        if (hitButt.equals("KemaskiniLaporan")) {
        	System.out.println("-------Read KemaskiniLaporan-------");
        	simpanStatus = 1;
        	int kemaskini1 = 1;
        	this.context.put("SimpanStatus", simpanStatus);
        	if (kemaskini == "0")
        	{
        		kemaskini1 = 0;
        		System.out.println("kemaskini1 = 0");
        		}
        	else
        		{
        		kemaskini1 = 1;
        		System.out.println("kemaskini1 = 1");
        		}
        	this.context.put("kemaskini", kemaskini1);
        	
        	vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
        	
        }
		if (hitButt.equals("SimpanSerahan")) {
			System.out.println("-------Read SimpanSerahan-------");
			String txtNamaPenerima = getParam("txtNamaPenerima");
			String txtTarikh = getParam("txtTarikh");
			//String txtNoKpPenerima = getParam("txtNoKpPenerima");
			String jeniskp = "";

			jeniskp = getParam("jeniskp");
			String a, b, c = "";
			String kplama = "";
			String kplain = "";
			String kpbaru = "";
			//String tarikh = getParam("date1");
			a = getParam("txtNoKPBaru1");
			b = getParam("txtNoKPBaru2");
			c = getParam("txtNoKPBaru3");
			kplama = getParam("txtNoKPLama");
			kplain = getParam("txtNoKPLain");
			System.out.println("a = "+a);
			if (jeniskp.equals("baru")) {
				kpbaru = a + b + c;
			} else if (jeniskp.equals("lama")) {
				kplama = kplama;
			} else if (jeniskp.equals("lain")) {
				kplain = kplain;
			} else {
				kpbaru = a + b + c;
			}
			
			String radioJenis = getParam("radioJenis");
			String txtCatatan = getParam("txtCatatan");
			String noFail = getParam("noFail");
			String txtNamaPenghantarNotis = getParam("txtNamaPenghantarNotis");
			//String idPerintah = getParam("idPerintah");
			simpanStatus = 2;

			saveData(noFail,txtCatatan,txtTarikh,txtNamaPenerima,kpbaru,kplama,kplain,radioJenis,txtNamaPenghantarNotis,idPerintah);
			vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
			
			
		}	


		//Salnizam edit ends
        
        
        
        
        //GET DROPDOWN PARAM
		String jenisKp = getParam("socJenisKp");
		if (jenisKp == null || jenisKp.trim().length() == 0){
			jenisKp = "0";
		}
		
		String idJenisPerintahHTA = getParam("socJenisPerintahHTA");
		if (idJenisPerintahHTA == null || idJenisPerintahHTA.trim().length() == 0){
			idJenisPerintahHTA = "0";
		}
		String idJenisPerintahHA = getParam("socJenisPerintahHA");
		if (idJenisPerintahHA == null || idJenisPerintahHA.trim().length() == 0){
			idJenisPerintahHA = "0";
		}
	    
		this.context.put("onload", "");
		
	    //SCREEN NAVIGATION
		if (actionPerintah.equals("papar")){			
			vm = "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";		
			if (noFail1 != "")
				{
				noFail2 = noFail1;
				}
			paparLaporanSerahan(noFail2);
			//<-------------------- SELESAI PERMOHONAN --------------------------->
			if (logic.checkPerintahKIV(idPerintah)){
				adaKIV = "1";
				this.context.put("adaKIV", adaKIV);
				myLogger.info("checkHAYangBelumDibahagikanARB");
			}
        	if (hitButt.equals("selesaiPermohonan")){
        		myLogger.info("selesaiPermohonan");
        		//CHECK ALL HTA YANG BELUM DIDAFTARKAN
				if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					myLogger.info("logic.checkHTAYangBelumDibahagikan");
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
				}				
				//CHECK ALL HA YANG BELUM DIDAFTARKAN
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					myLogger.info("logic.checkHAYangBelumDibahagikan");
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
				}
				//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
				if (getParam("flagAdaHTAPT").equals("1")){
					myLogger.info("flagAdaHTAPT");
					if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
						myLogger.info("logic.checkPembahagianHTAPTLengkap");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all HAPT telah dibahagikan sepenuhnya
				if (getParam("flagAdaHAPT").equals("1")){
					myLogger.info("flagAdaHAPT");
					if (logic.checkPembahagianHAPTLengkap(idPerintah)){
						myLogger.info("logic.checkPembahagianHAPTLengkap");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHTAPKT").equals("1")){
					myLogger.info("flagAdaHTAPKT");
					if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
						myLogger.info("logic.checkPembahagianHTAPKTLengkap");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHAPKT").equals("1")){
					myLogger.info("flagAdaHAPKT***");
					if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
						myLogger.info("logic.checkPembahagianHAPKTLengkap");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all HTA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHTAPL").equals("1")){
					myLogger.info("flagAdaHTAPL***");
					if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
						myLogger.info("logic.checkPembahagianHTAPLLengkap***");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all HA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHAPL").equals("1")){
					myLogger.info("flagAdaHAPL***");
					if (logic.checkPembahagianHAPLLengkap(idPerintah)){
						myLogger.info("checkPembahagianHAPLLengkap###***");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
				if (getParam("flagAdaHAPT").equals("1")){
					myLogger.info("flagAdaHAPT***");
					if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
						myLogger.info("logic.checkWarisYangPerluAdaPAHAPT***");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
				if (getParam("flagAdaHAPT").equals("1")){
					myLogger.info("flagAdaHAPT***");
					if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
						myLogger.info("logic.checkWarisYangPerluAdaPTHAPT***");
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
					}
				}
				
				//all validation pass - proceed to hantar permohonan
				if (postDB){
					logic.selesaiPermohonan(idPermohonan, session);
				}
			}
        	
        	//<-------------------- SELESAI --------------------------->

			if (hitButt.equals("selesai")){		
				
				
				//all validation pass - proceed to selesai permohonan
				
				if (postDB){
					if (idStatus.equals("25")){
						

						//CHECK ALL HTA YANG BELUM DIDAFTARKAN
						if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}				
						//CHECK ALL HA YANG BELUM DIDAFTARKAN
						if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
							this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
							return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
						}
						//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
						if (getParam("flagAdaHTAPT").equals("1")){
							if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all HAPT telah dibahagikan sepenuhnya
						if (getParam("flagAdaHAPT").equals("1")){
							if (logic.checkPembahagianHAPTLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
						if (getParam("flagAdaHTAPKT").equals("1")){
							if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
						if (getParam("flagAdaHAPKT").equals("1")){
							if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all HTA telah dikemaskini bagi perintah lelong
						if (getParam("flagAdaHTAPL").equals("1")){
							if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all HA telah dikemaskini bagi perintah lelong
						if (getParam("flagAdaHAPL").equals("1")){
							if (logic.checkPembahagianHAPLLengkap(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
						if (getParam("flagAdaHTAPT").equals("1")){
							if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
						if (getParam("flagAdaHAPT").equals("1")){
							if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
						if (getParam("flagAdaHTAPT").equals("1")){
							if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
						if (getParam("flagAdaHAPT").equals("1")){
							if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
						if (getParam("flagAdaHTAPF").equals("1")){
							if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
						if (getParam("flagAdaHAPF").equals("1")){
							if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
						if (getParam("flagAdaHTAPF").equals("1")){
							if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
						if (getParam("flagAdaHAPF").equals("1")){
							if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
								this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
								return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
							}
						}
						
						if (getParam("printOption").equals("potrait")){
							this.context.put("onload", " \"javascript:generateNotisKeluarGeranPot('" + getParam("idFail") + "')\"");
						} else {
							this.context.put("onload", " \"javascript:generateNotisKeluarGeranLand('" + getParam("idFail") + "')\"");
						}
						
						logic.selesai(idPermohonan, idPerintah, session,idPermohonanSimati);
						
						//mobile notification 06092017
						Push.genMsgPush(getParam("idFail"), "perintah");
						
					} else {
						
                        if (getParam("printOption").equals("potrait")) {
                            this.context.put("onload"," \"javascript:generateNotisKeluarGeranPot('" + getParam("idFail") + "')\"");
                        } else if (getParam("printOption").equals("landskap")) {
                        	
                            this.context.put("onload"," \"javascript:generateNotisKeluarGeranLand('" + getParam("idFail") + "')\"");
                        } else {
                            this.context.put("onload"," \"javascript:generateNotisKeluarGeran('" + getParam("idFail") + "')\"");
                        }
                        
					}
				}
				
				context.put("usid", usid);    //aishah
			}
			
			if (hitButt.equals("janaPerintah")){
				if (!logic.checkGeneratedPerintah(logic.getNoFailByIdFail(getParam("idFail")))){
					logic.janaPerintah(getParam("idFail"), idPermohonan, idPermohonanSimati, idPerbicaraan, idPerintah);
				}
				this.context.put("onload", " \"javascript:popupIntergrasiPerintahEtanah('" + getParam("idFail") + "','" + getParam("idPerintah") + "')\"");
			}
			
 			//<--------------------------- HARTA TAK ALIH (ADA HAKMILIK) --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH (ADA HAKMILIK)
			if (hitButt.equals("simpanBaruHTA")){
				if (postDB) {
					String ids[] = request.getParameterValues("idspentadbir");
					if (ids != null) {
						for (int i = 0; i < ids.length; i++) {
							logic.saveHTA(getParam("socJenisPerintahHTA"), getParam("txtCatatanHTA"), ids[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
						}
					}
					idJenisPerintahHTA = "0";
				}
			}
			if (hitButt.equals("hapusHTA")){
				if (postDB) {
					logic.hapusHTA(idHTA);
				}
			}
			if (hitButt.equals("simpanKemaskiniHTA")){
				if (getParam("jenisPerintah").equals(getParam("socJenisPerintahHTA"))){
					if (postDB) {
						logic.updateCatatanHTA(getParam("txtCatatanHTA"), idHTA, session);
					}
				} else {
					if (postDB) {
						logic.updateHTA(getParam("socJenisPerintahHTA"), getParam("txtCatatanHTA"), idHTA, idPermohonan, idSimati, idPermohonanSimati, idPerintah, session);
					}
				}
			}
			
			//OPEN POPUP HARTA TAK ALIH (ADA HAKMILIK)
        	if (flagPopup.equals("openHTA")){
        		this.context.put("idHTA", idHTA);
        		
        		//MODE = NEW
				if (modePopup.equals("new")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					
					//DETAIL HTA
					Hashtable hashHTA = new Hashtable();
					hashHTA.put("catatan", getParam("txtCatatanHTA") == null ? "" : getParam("txtCatatanHTA"));
					beanMaklumatHTA.addElement(hashHTA);
					this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
					
					this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
					
					// SENARAI HTA
					logic.setDataSenaraiHTAPopup(idPermohonanSimati, idPerintah);
					listHTAPopup = logic.getSenaraiHTAPopup();
					this.context.put("SenaraiHTAPopup", listHTAPopup);
					
					Hashtable h = (Hashtable) listHTAPopup.get(0);
					if (h.get("bil").toString().equals("")){
						this.context.put("listSizeHTAPopup", 0);
					} else {
						this.context.put("listSizeHTAPopup", listHTAPopup.size());
					}
				}
				
				//MODE = UPDATE
				if (modePopup.equals("update")){
					if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
					}
					
					
					//DETAIL HTA
					logic.setDataMaklumatHTA(idHTA,idPermohonanSimati);
					beanMaklumatHTA = logic.getBeanMaklumatHTA();
					Hashtable hashHTA = (Hashtable) logic.getBeanMaklumatHTA().get(0);
					this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
					
					if ("0".equals(idJenisPerintahHTA)){
						idJenisPerintahHTA = hashHTA.get("idJenisPerintah").toString();
					}
					if (mode.equals("update")){
						this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
					} else {
						this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(hashHTA.get("idJenisPerintah").toString()), "disabled", " class=\"disabled\""));
					}
					
					this.context.put("jenisPerintah", hashHTA.get("idJenisPerintah"));
				}
        	}
        	
        	//<--------------------------- HARTA TAK ALIH (TIADA HAKMILIK) --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH (TIADA HAKMILIK)
			if (hitButt.equals("simpanKemaskiniHTATH")){
				if (postDB) {
					logic.updateCatatanHTA(getParam("txtCatatanHTATH"), idHTA, session);
				}
			}
			//HITBUTTON FOR HAPUS HARTA TAK ALIH (TIADA HAKMILIK)
			if (hitButt.equals("hapusHTATH")){
				if (postDB) {
					logic.hapusHTATH(idHTA);
				}
			}
        	//OPEN POPUP HARTA TAK ALIH (TIADA HAKMILIK)
        	if (flagPopup.equals("openHTATH")){
        		this.context.put("idHTA", idHTA);
				
				//MODE = UPDATE
				if (modePopup.equals("update")){
					if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
					}
					
					//DETAIL HTATH
					logic.setDataMaklumatHTATH(idHTA,idPermohonanSimati);					
					beanMaklumatHTATH = logic.getBeanMaklumatHTATH();
					Hashtable hashHTATH = (Hashtable) logic.getBeanMaklumatHTATH().get(0);
					this.context.put("BeanMaklumatHTATH", beanMaklumatHTATH);
				}
        	}
        	
        	// <--------------------------- HARTA ALIH --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA ALIH
			if (hitButt.equals("simpanBaruHA")){
				if (postDB) {
					String ids[] = request.getParameterValues("idspentadbir");
					if (ids != null) {
						for (int i = 0; i < ids.length; i++) {
							logic.saveHA(getParam("socJenisPerintahHA"), getParam("txtCatatanHA"), ids[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
						}
					}
					idJenisPerintahHA = "0";
				}
			}
			if (hitButt.equals("hapusHA")){
				if (postDB) {
					logic.hapusHA(idHA);
				}
			}
			if (hitButt.equals("simpanKemaskiniHA")){
				if (getParam("jenisPerintah").equals(getParam("socJenisPerintahHA"))){
					if (postDB) {
						logic.updateCatatanHA(getParam("txtCatatanHA"), idHA, session);
					}
				} else {
					if (postDB) {
						logic.updateHA(getParam("socJenisPerintahHA"), getParam("txtCatatanHA"), idHA, idPermohonan, idSimati, idPermohonanSimati, idJenisHA, idPerintah, session);
					}
				}
			}
        	
        	//OPEN POPUP HARTA ALIH
        	if (flagPopup.equals("openHA")){
        		this.context.put("idHA", idHA);
        		
        		//MODE = NEW
				if (modePopup.equals("new")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					
					//DETAIL HA
					Hashtable hashHA = new Hashtable();
					hashHA.put("catatan", getParam("txtCatatanHA") == null ? "" : getParam("txtCatatanHA"));
					beanMaklumatHA.addElement(hashHA);
					this.context.put("BeanMaklumatHA", beanMaklumatHA);
					
					this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(idJenisPerintahHA), "", ""));
					
					// SENARAI HA
					logic.setDataSenaraiHAPopup(idPermohonanSimati, idPerintah);
					listHAPopup = logic.getSenaraiHAPopup();
					this.context.put("SenaraiHAPopup", listHAPopup);
					
					Hashtable h = (Hashtable) listHAPopup.get(0);
					if (h.get("bil").toString().equals("")){
						this.context.put("listSizeHAPopup", 0);
					} else {
						this.context.put("listSizeHAPopup", listHAPopup.size());
					}
				}
				
				//MODE = UPDATE
				if (modePopup.equals("update")){
					if (mode.equals("update")){
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
					} else {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
					}
					
					//DETAIL HA
					logic.setDataMaklumatHA(idHA,idPermohonanSimati);
					beanMaklumatHA = logic.getBeanMaklumatHA();
					Hashtable hashHA = (Hashtable) logic.getBeanMaklumatHA().get(0);
					this.context.put("BeanMaklumatHA", beanMaklumatHA);
					
					if ("0".equals(idJenisPerintahHA)){
						idJenisPerintahHA = hashHA.get("idJenisPerintah").toString();
					}
					idJenisHA = hashHA.get("idJenisHA").toString();
					
					if (mode.equals("update")){
						this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(idJenisPerintahHA), "", ""));
					} else {
						this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(hashHA.get("idJenisPerintah").toString()), "disabled", " class=\"disabled\""));
					}
					
					this.context.put("jenisPerintah", hashHA.get("idJenisPerintah"));
				}
        	}
        	
        	// <--------------------------- HTAPT --------------------------->
        	
        	//HITBUTTON FOR POPUP HTAPT
			if (hitButt.equals("simpanKemaskiniHTAPT")){
				if (postDB) {
					logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
					String ids[] = request.getParameterValues("idspentadbir");
					String status[] = request.getParameterValues("status");
					String BA[] = request.getParameterValues("txtBA");
					String BB[] = request.getParameterValues("txtBB");
					if (ids != null) {
						for (int i = 0; i < ids.length; i++) {
							if (BA[i] != null){
								if (Utils.parseLong(BA[i]) > 0){
									logic.updateHTAPT(idHTA, ids[i], status[i], BA[i], BB[i], idPerintah, session,idPermohonanSimati);
								} else {
									logic.removeHTAOBDTL(idHTA, ids[i]);
								}
							}
						}
					}
					
					//SAVE FOR WARIS YANG HILANG
					if (getParam("chkWarisHilang").equals("1") && getParamAsInteger("txtBAHilang") > 0){
						logic.updateHTAPTHilang(idHTA, idPerintah, getParam("txtBAHilang"), getParam("txtBBHilang"), session,idPermohonanSimati);
					} else {
						logic.removeHTAPTHilang(idHTA);
					}
					
					logic.updatePecahanWarisHTA(idHTA);
				}
			}
        	
        	//OPEN POPUP HARTA TAK ALIH PERINTAH TERUS
        	if (flagPopup.equals("openHTAPT")){
        		this.context.put("idHTA", idHTA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			logic.setDataSenaraiHTAPTDTL(idHTA, idSimati, idPermohonanSimati);
				listHTAPTDTL = logic.getSenaraiHTAPTDTL();
				this.context.put("SenaraiHTAPTDTL", listHTAPTDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHTAPTDTL.size(); i++){
					Hashtable hash = (Hashtable) listHTAPTDTL.get(i);
					long pembawah = 1;
					//Azam add checking
					if ("".equals(hash.get("BB"))) {
						hash.put("BB","0");
					}
					if ("".equals(hash.get("BA"))) {
						hash.put("BA","0");
					}
					//end here
					if (!hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
				}

				logic.setDataSenaraiHTAPTDTLHilang(idHTA);
				if (logic.getSenaraiHTAPTDTLHilang().size() != 0){
					Hashtable hash = (Hashtable) logic.getSenaraiHTAPTDTLHilang().get(0);
					
					this.context.put("idHilang", hash.get("idPerintahHTAOBDTL"));
					this.context.put("BAHilang", hash.get("BA"));
					this.context.put("BBHilang", hash.get("BB"));
					this.context.put("checked", "checked");
					this.context.put("readonlyHilang", "");
					this.context.put("disabledHilang", "");
					
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),Long.parseLong(hash.get("BB").toString()));
					f = f.plus(f2);
					
				} else {
					
					this.context.put("idHilang", "");
					this.context.put("BAHilang", "0");
					this.context.put("BBHilang", "0");
					this.context.put("checked", "");
					this.context.put("readonlyHilang", "readonly");	
					this.context.put("disabledHilang", "disabled");
				}

				this.context.put("txtJumlahBA", f.numerator());
				this.context.put("txtJumlahBB", f.denominator());
        	}
        	
        	// <--------------------------- HAPT --------------------------->
        	
        	//HITBUTTON FOR POPUP HAPT
			if (hitButt.equals("simpanKemaskiniHAPT")){
				if (postDB) {
					logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
					String ids[] = request.getParameterValues("idspentadbir");
					String status[] = request.getParameterValues("status");
					String BA[] = request.getParameterValues("txtBA");
					String BB[] = request.getParameterValues("txtBB");
					if (ids != null) {
						for (int i = 0; i < ids.length; i++) {
							if (BA[i] != null){
								if (Utils.parseLong(BA[i]) > 0){
									logic.updateHAPT(idHA, ids[i], status[i], BA[i], BB[i], idPerintah, session);
								} else {
									logic.removeHAOBDTL(idHA, ids[i]);
								}
							}
						}
					}
					
					//save for waris yang hilang
					if (getParam("chkWarisHilang").equals("1") && getParamAsInteger("txtBAHilang") > 0){
						logic.updateHAPTHilang(idHA, idPerintah, getParam("txtBAHilang"), getParam("txtBBHilang"), session);
					} else {
						logic.removeHAPTHilang(idHA);
					}
					
					logic.updatePecahanWarisHA(idHA);
				}
			}
        	
        	//OPEN POPUP HARTA ALIH PERINTAH TERUS
        	if (flagPopup.equals("openHAPT")){
        		this.context.put("idHA", idHA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			logic.setDataSenaraiHAPTDTL(idHA, idSimati, idPermohonanSimati);
				listHAPTDTL = logic.getSenaraiHAPTDTL();
				this.context.put("SenaraiHAPTDTL", listHAPTDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHAPTDTL.size(); i++){
					Hashtable hash = (Hashtable) listHAPTDTL.get(i);
					long pembawah = 1;
					if (!hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
				}
				
				logic.setDataSenaraiHAPTDTLHilang(idHA);
				if (logic.getSenaraiHAPTDTLHilang().size() != 0){
					Hashtable hash = (Hashtable) logic.getSenaraiHAPTDTLHilang().get(0);
					
					this.context.put("idHilang", hash.get("idPerintahHTAOBDTL"));
					this.context.put("BAHilang", hash.get("BA"));
					this.context.put("BBHilang", hash.get("BB"));
					this.context.put("checked", "checked");
					this.context.put("readonlyHilang", "");
					this.context.put("disabledHilang", "");
					
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),Long.parseLong(hash.get("BB").toString()));
					f = f.plus(f2);
					
				} else {
					
					this.context.put("idHilang", "");
					this.context.put("BAHilang", "0");
					this.context.put("BBHilang", "0");
					this.context.put("checked", "");
					this.context.put("readonlyHilang", "readonly");	
					this.context.put("disabledHilang", "disabled");
				}

				this.context.put("txtJumlahBA", f.numerator());
				this.context.put("txtJumlahBB", f.denominator());
        	}
        	
        	
        	// <--------------------------- HTAPKT --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH KUASA TADBIR
        	if (hitButt.equals("simpanKemaskiniHTAPKT")){
        		if (postDB) {
        			String idPA1 = "";
        			String idPA2 = "";
        			String idPA3 = "";
        			String idPA4 = "";
        			String ids[] = request.getParameterValues("idsOB");
        			
        			if (ids != null) {
        				for (int i = 0; i < ids.length; i++) {
        					if (i == 0){
        						idPA1 = ids[i];
        					} else if (i == 1){
        						idPA2 = ids[i];
        					} else if (i == 2){
        						idPA3 = ids[i];
        					} else if (i == 3){
        						idPA4 = ids[i];
        					}
        				}
        			}
        			
        			logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
        			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
        			
        			//UPDATE PECAHAN HARTA
        			String idspentadbir[] = request.getParameterValues("idspentadbir");
        			String status[] = request.getParameterValues("status");
					String BA[] = request.getParameterValues("txtBA");
					String BB[] = request.getParameterValues("txtBB");
					
					if (idspentadbir != null) {
						for (int i = 0; i < idspentadbir.length; i++) {
							if (BA[i] != null){
								if (Utils.parseLong(BA[i]) > 0){
									logic.updatePembahagianHTAPKT(idHTA, idspentadbir[i], status[i], BA[i], BB[i], idPerintah, session, idPermohonanSimati);
								} else {
									logic.removePembahagianHTAPKT(idHTA, idspentadbir[i]);
								}
							}
						}
					}
					logic.updatePecahanWarisHTAPKT(idHTA);
        		}
			}
        	
        	//OPEN POPUP HARTA TAK ALIH PERINTAH KUASA TADBIR
        	if (flagPopup.equals("openHTAPKT")){
        		this.context.put("idHTA", idHTA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			// SENARAI HTAPKT
    			logic.setDataSenaraiHTAPKTDTL(idHTA, idPermohonanSimati, idSimati);
    			listHTAPKTDTL = logic.getSenaraiHTAPKTDTL();
    			this.context.put("ListHTAPKTDTL", listHTAPKTDTL);
    			
    			Hashtable h = (Hashtable) listHTAPKTDTL.get(0);
    			if (h.get("bil").toString().equals("")){
    				this.context.put("listSize", 0);
    			} else {
    				this.context.put("listSize", listHTAPKTDTL.size());
    			}
    			
    			// SENARAI PEMBAHAGIAN HTAPKT
    			logic.setDataSenaraiPembahagianHTAPKTDTL(idHTA, idSimati, idPermohonanSimati);
				listPembahagianHTAPKTDTL = logic.getSenaraiPembahagianHTAPKTDTL();
				this.context.put("SenaraiPembahagianHTAPKTDTL", listPembahagianHTAPKTDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listPembahagianHTAPKTDTL.size(); i++){
					Hashtable hash = (Hashtable) listPembahagianHTAPKTDTL.get(i);
					long pembawah = 1;
					//Azam add checking
					if ("".equals(hash.get("BB"))) {
						hash.put("BB","0");
					}
					if ("".equals(hash.get("BA"))) {
						hash.put("BA","0");
					}
					//end here
					if (!hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
					
					this.context.put("txtJumlahBA", f.numerator());
					this.context.put("txtJumlahBB", f.denominator());
				}
    			
        	}
        	
        	// <--------------------------- HAPKT --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH KUASA TADBIR
        	if (hitButt.equals("simpanKemaskiniHAPKT")){
        		if (postDB) {
        			String idPA1 = "";
        			String idPA2 = "";
        			String idPA3 = "";
        			String idPA4 = "";
        			String ids[] = request.getParameterValues("idspentadbir");
        			
        			if (ids != null) {
        				for (int i = 0; i < ids.length; i++) {
        					if (i == 0){
        						idPA1 = ids[i];
        					} else if (i == 1){
        						idPA2 = ids[i];
        					} else if (i == 2){
        						idPA3 = ids[i];
        					} else if (i == 3){
        						idPA4 = ids[i];
        					}
        				}
        			}
        			
        			logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
        			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
        		}
			}
        	
        	//OPEN POPUP HARTA ALIH PERINTAH KUASA TADBIR
        	if (flagPopup.equals("openHAPKT")){
        		this.context.put("idHA", idHA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");
				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
					this.context.put("disabled", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			// SENARAI HAPKT
    			logic.setDataSenaraiHAPKTDTL(idHA, idPermohonanSimati, idSimati);
    			listHAPKTDTL = logic.getSenaraiHAPKTDTL();
    			this.context.put("ListHAPKTDTL", listHAPKTDTL);
    			
    			Hashtable h = (Hashtable) listHAPKTDTL.get(0);
    			if (h.get("bil").toString().equals("")){
    				this.context.put("listSize", 0);
    			} else {
    				this.context.put("listSize", listHAPKTDTL.size());
    			}
        	}
        	
        	// <--------------------------- HTAPL --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH LELONG
        	if (hitButt.equals("simpanKemaskiniHTAPL")){
        		if (postDB) {
        			updateHTAPL(idHTA, session);
        		}
			}
        	
        	//OPEN POPUP HARTA TAK ALIH PERINTAH LELONG
        	if (flagPopup.equals("openHTAPL")){
        		this.context.put("idHTA", idHTA);
				
				//MODE = UPDATE
				if (modePopup.equals("update")){
					if (mode.equals("update")){
						this.context.put("style", "style1");
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("style", "");
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}					
					
					//DETAIL
					logic.setDataMaklumatHTAPL(idHTA,idPermohonanSimati);
					beanMaklumatHTAPL = logic.getBeanMaklumatHTAPL();
					Hashtable hashHTAPL = (Hashtable) logic.getBeanMaklumatHTAPL().get(0);
					this.context.put("BeanMaklumatHTAPL", beanMaklumatHTAPL);
										
					if (hashHTAPL.get("jenisLelong").equals("A")){
						this.context.put("checkedA", "checked");
						this.context.put("checkedT", "");
					} else if (hashHTAPL.get("jenisLelong").equals("T")){
						this.context.put("checkedA", "");
						this.context.put("checkedT", "checked");
					} else {
						this.context.put("checkedA", "");
						this.context.put("checkedT", "");
					}
				}
        	}
        	
        	// <--------------------------- HAPL --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH LELONG
        	if (hitButt.equals("simpanKemaskiniHAPL")){
        		if (postDB) {
        			updateHAPL(idHA,session);
        		}
			}
        	
        	//OPEN POPUP FOR HARTA ALIH PERINTAH LELONG
        	if (flagPopup.equals("openHAPL")){
        		this.context.put("idHA", idHA);
				
				//MODE = UPDATE
				if (modePopup.equals("update")){
					if (mode.equals("update")){
						this.context.put("style", "style1");
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					} else {
						this.context.put("style", "");
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					}
					
					//detail
					logic.setDataMaklumatHAPL(idHA,idPermohonanSimati);
					beanMaklumatHAPL = logic.getBeanMaklumatHAPL();
					Hashtable hashHAPL = (Hashtable) logic.getBeanMaklumatHAPL().get(0);
					this.context.put("BeanMaklumatHAPL", beanMaklumatHAPL);
					
					if (hashHAPL.get("jenisLelong").equals("A")){
						this.context.put("checkedA", "checked");
						this.context.put("checkedT", "");
					} else if (hashHAPL.get("jenisLelong").equals("T")){
						this.context.put("checkedA", "");
						this.context.put("checkedT", "checked");
					} else {
						this.context.put("checkedA", "");
						this.context.put("checkedT", "");
					}
				}
        	}
        	
        	// <--------------------------- HTAPF --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH PERINTAH FARAID
        	if (hitButt.equals("simpanKemaskiniHTAPF")){
        		if (postDB) {
        			logic.updateCatatanHTA(getParam("txtCatatan"), idHTA, session);
        		}
			}
        	
        	//OPEN POPUP HARTA TAK ALIH PERINTAH FARAID
        	if (flagPopup.equals("openHTAPF")){
        		this.context.put("idHTA", idHTA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");

				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHTA(idHTA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			logic.setDataSenaraiHTAPFDTL(idHTA,idPermohonanSimati);
				listHTAPFDTL = logic.getSenaraiHTAPFDTL();
				this.context.put("SenaraiHTAPFDTL", listHTAPFDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHTAPFDTL.size(); i++){
					Hashtable hash = (Hashtable) listHTAPFDTL.get(i);
					long pembawah = 1;
					if (!hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
				}
				this.context.put("jumlahBahagian", f.numerator() + " / " + f.denominator());
        	}
        	
        	// <--------------------------- HAPF --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA ALIH PERINTAH FARAID
        	if (hitButt.equals("simpanKemaskiniHAPF")){
        		if (postDB) {
        			logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
        		}
			}
        	
        	//OPEN POPUP HARTA ALIH PERINTAH FARAID
        	if (flagPopup.equals("openHAPF")){
        		this.context.put("idHA", idHA);
        		
        		if (mode.equals("update")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");

				} else {
					this.context.put("readonly", "readonly");
					this.context.put("inputTextClass", "disabled");
				}
        		
        		//HEADER DETAIL
        		logic.setMaklumatHeaderDetailHA(idHA,idPermohonanSimati);
    			beanHeaderDetail = logic.getBeanHeaderDetail();
    			this.context.put("BeanHeaderDetail", beanHeaderDetail);
    			
    			logic.setDataSenaraiHAPFDTL(idHA,idPermohonanSimati);
				listHAPFDTL = logic.getSenaraiHAPFDTL();
				this.context.put("SenaraiHAPFDTL", listHAPFDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHAPFDTL.size(); i++){
					Hashtable hash = (Hashtable) listHAPFDTL.get(i);
					long pembawah = 1;
					if (!hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
				}
				this.context.put("jumlahBahagian", f.numerator() + " / " + f.denominator());
        	}
			
			//HEADER
			logic.setMaklumatPermohonan(idPermohonan,session);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("MaklumatPermohonan", beanMaklumatPermohonan);
			//hashtable maklumat header
			headerppk_baru(session,idPermohonan,"Y","","T");
			
			if (beanMaklumatPermohonan.size() != 0){
				//SET MODE AND ID_STATUS
				Hashtable hashHeader = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				if (hashHeader.get("id_Status").equals("41") || hashHeader.get("id_Status").equals("25")){
					idStatus = hashHeader.get("id_Status").toString();
					mode = "update";
				} else {
					String roleId = "";  
					roleId = (String)session.getAttribute("_portal_role");
					if (roleId.equals("adminppk")){
						idStatus = hashHeader.get("id_Status").toString();
						mode = "update";
					} else {
						idStatus = hashHeader.get("id_Status").toString();
						//mode = "view";
						//man tukar flag
						mode = "update";
					}
			   		
				}
			}
			
			//GET ID PERINTAH
			if (idPerintah.isEmpty()){
				idPerintah =  logic.getIdPerintah(idPermohonan);
			}
			this.context.put("idPerintah", idPerintah);
			
			//GET ID PERBICARAAN
			if (idPerbicaraan.isEmpty()){
				idPerbicaraan =  logic.getIdPerbicaraan(idPerintah);
			}
			this.context.put("idPerbicaraan", idPerbicaraan);
			
			
			//CHECK IF SIMATI ADA HTA, HTATH AND HA		
			if (logic.checkExistHTA(idPermohonanSimati)){
				flagAdaHTA = "1";
			}
			if (logic.checkExistHTATH(idPermohonanSimati)){
				flagAdaHTATH = "1";
			}
			if (logic.checkExistHA(idPermohonanSimati)){
				flagAdaHA = "1";
			}
			
			//DISABLEKAN SEKEJAP 17/6/2020
			/*//AUTOMATIC REGISTER HTATH INTO PERINTAH KUASA TADBIR IF EXIST
			if (flagAdaHTATH.equals("1")){
				logic.registerHTATHIntoPKT(idPerintah, idPermohonanSimati, session);
			}*/
			
			//GET LIST HARTA TAK ALIH (ADA HAKMILIK)
			if (flagAdaHTA.equals("1")){
				logic.setDataSenaraiHTA(idPerintah,idPermohonanSimati);
				//###temp
				//logic.setDataMaklumatHTATH(idHTA,idPermohonanSimati);
				
				listHTA = logic.getSenaraiHTA();
				this.context.put("SenaraiHTA", listHTA);
				
				//CHECK ALL HTA YANG BELUM DIDAFTARKAN
				if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					flagSelesaiHTA = "";
				} else {
					flagSelesaiHTA = "Y";
				}
			} 
			
			//GET LIST HARTA TAK ALIH (TIADA HAKMILIK)
			if (flagAdaHTATH.equals("1")){
				logic.setDataSenaraiHTATH(idPerintah,idPermohonanSimati);
				listHTATH = logic.getSenaraiHTATH();
				this.context.put("SenaraiHTATH", listHTATH);
			}
			
			//GET LIST HARTA ALIH
			myLogger.info("flagAdaHA == "+flagAdaHA);
			myLogger.info("flagSelesaiHA == "+flagSelesaiHA);
			if (flagAdaHA.equals("1")){
				logic.setDataSenaraiHA(idPerintah,idPermohonanSimati);
				listHA = logic.getSenaraiHA();
				this.context.put("SenaraiHA", listHA);
				
				//CHECK ALL HA YANG BELUM DIDAFTARKAN
				/*if (logic.checkHAYangDiselesaikanARBBelumDibahagikan(idPermohonanSimati, idPerintah)){
					flagSelesaiHA = "";
					myLogger.info("flagSelesaiHA");
				} else {
					flagSelesaiHA = "Y";
					myLogger.info("flagSelesaiHA--Y");
				}*/
				flagSelesaiHA = "Y";
				myLogger.info("&&&&******");
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					myLogger.info("logic.checkHAYangBelumDibahagikan");
					flagSelesaiHA = "";
					myLogger.info("flagSelesaiHA"+flagSelesaiHA);
					//this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
					//return "app/ppk/frmPerintahMaklumatPerintahSek8.jsp";
				}
				
				//CHECK ADA KIV 
				if (logic.checkPerintahKIV(idPerintah)){
					adaKIV = "1";
					this.context.put("adaKIV", adaKIV);
					myLogger.info("checkHAYangBelumDibahagikanARB");
				}
				//CHECK HA YANG DISELESAIKAN OLEH ARB TETAPI BELUM DIDAFTARKAN 
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					flagSelesaiHAARB = "";
					myLogger.info("checkHAYangBelumDibahagikanARB");
					logic.setDataSenaraiHAARB(idPerintah,idPermohonanSimati);
					listHAARB = logic.getSenaraiHAARB();
					String id_HAARB = "";
					String id_OBARB = "";
					String id_perintahhaobmst = "";
					myLogger.info("listHAARB.size="+listHAARB.size());
					if (listHAARB.size() != 0) {
     	     			Hashtable lsHAARB = (Hashtable) listHAARB.get(0);
     	     			id_HAARB = lsHAARB.get("ID_HA").toString();
					
					
					myLogger.info("Sini1");
					//disablekan dulu sbb semua HA dia register perintah secara auto
					//logic.saveHA("1", "", id_HAARB, idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
					myLogger.info("Sini2");
					}
					//cari perintahhaobmst
					logic.findIDPerintahHAOBMST(id_HAARB);
					listIdPerintahhaobmst = logic.getSenaraiPerintahHAARB();
					if (listIdPerintahhaobmst.size() != 0) {
     	     			Hashtable lslistIdPerintahhaobmst = (Hashtable) listIdPerintahhaobmst.get(0);
     	     			id_perintahhaobmst = lslistIdPerintahhaobmst.get("ID_PERINTAHHAOBMST").toString();
					}
					
					//cari idOB Amanah Raya Berhad
					logic.findIdObARB(idPermohonanSimati);
					listOBARB = logic.getSenaraiOBARB();
					//SELECT ID_OB FROM TBLPPKOB WHERE ID_SIMATI in (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '99191149646') AND NAMA_OB LIKE '%AMANAH RAYA%'
					if (listOBARB.size() != 0) {
						Hashtable lsOBARB = (Hashtable) listOBARB.get(0);
						id_OBARB = lsOBARB.get("ID_OB").toString();
						//logic.updateHAPT(idHA, ids[i], status[i], BA[i], BB[i], idPerintah, session);
						logic.updateHAPT(id_perintahhaobmst, id_OBARB, id_HAARB, "1", "1", idPerintah, session);
					}
				
					
						idJenisPerintahHA = "0";
					
				}
				else {
					flagSelesaiHAARB = "Y";
					myLogger.info("flagSelesaiHAARB");
				}
			}
			
			//CHECK IF ADA PEMBAHAGIAN HTA AND HA FOR PT,PKT,PL AND PF		
			if (flagAdaHTA.equals("1") || flagAdaHTATH.equals("1")){
				//HTA PERINTAH TERUS
				if (logic.checkExistHTAPT(idPerintah)){
					flagAdaHTAPT = "1";
				}
				//HTA PERINTAH KUASA TADBIR
				if (logic.checkExistHTAPKT(idPerintah)){
					flagAdaHTAPKT = "1";
				}
				//HTA PERINTAH LELONG
				if (logic.checkExistHTAPL(idPerintah)){
					flagAdaHTAPL = "1";
				}
				//HTA PERINTAH FARAID
				if (logic.checkExistHTAPF(idPerintah)){
					flagAdaHTAPF = "1";
				}
			}
			if (flagAdaHA.equals("1")){
				//HA PERINTAH TERUS
				if (logic.checkExistHAPT(idPerintah)){
					flagAdaHAPT = "1";
				}
				//HA PERINTAH KUASA TADBIR
				if (logic.checkExistHAPKT(idPerintah)){
					flagAdaHAPKT = "1";
				}
				//HA PERINTAH LELONG
				if (logic.checkExistHAPL(idPerintah)){
					flagAdaHAPL = "1";
				}
				//HA PERINTAH FARAID
				if (logic.checkExistHAPF(idPerintah)){
					flagAdaHAPF = "1";
				}
			}
			
			if (flagAdaHTAPT.equals("1")){
				logic.setDataSenaraiHTAPT(idPerintah,idPermohonanSimati);
				listHTAPT = logic.getSenaraiHTAPT();
				this.context.put("SenaraiHTAPT", listHTAPT);
			}			
			if (flagAdaHAPT.equals("1")){
				logic.setDataSenaraiHAPT(idPerintah,idPermohonanSimati);
				listHAPT = logic.getSenaraiHAPT();
				this.context.put("SenaraiHAPT", listHAPT);
			}
			if (flagAdaHTAPKT.equals("1")){
				logic.setDataSenaraiHTAPKT(idPerintah,idPermohonanSimati);
				listHTAPKT = logic.getSenaraiHTAPKT();
				this.context.put("SenaraiHTAPKT", listHTAPKT);
			}
			if (flagAdaHAPKT.equals("1")){
				logic.setDataSenaraiHAPKT(idPerintah,idPermohonanSimati);
				listHAPKT = logic.getSenaraiHAPKT();
				this.context.put("SenaraiHAPKT", listHAPKT);
			}
			if (flagAdaHTAPL.equals("1")){
				logic.setDataSenaraiHTAPL(idPerintah,idPermohonanSimati);
				listHTAPL = logic.getSenaraiHTAPL();
				this.context.put("SenaraiHTAPL", listHTAPL);
			}
			if (flagAdaHAPL.equals("1")){
				logic.setDataSenaraiHAPL(idPerintah,idPermohonanSimati);
				listHAPL = logic.getSenaraiHAPL();
				this.context.put("SenaraiHAPL", listHAPL);
			}
			if (flagAdaHTAPF.equals("1")){
				logic.setDataSenaraiHTAPF(idPerintah,idPermohonanSimati);
				listHTAPF = logic.getSenaraiHTAPF();
				this.context.put("SenaraiHTAPF", listHTAPF);
			}
			if (flagAdaHAPF.equals("1")){
				logic.setDataSenaraiHAPF(idPerintah,idPermohonanSimati);
				listHAPF = logic.getSenaraiHAPF();
				this.context.put("SenaraiHAPF", listHAPF);
			}
			
			
			//aishah 03022017
			//semakan fail yang telah dibuat pembetulan perintah
			/*
			String FLAG_KEBENARAN_EDIT = "";
			FLAG_KEBENARAN_EDIT = logic.getFLAG_KEBENARAN_EDIT(getParam("idFail"));
			String CHECK_EDIT = "";
			CHECK_EDIT = logic.getCHECK_EDIT(getParam("idFail"),userId);
			//myLogger.info(":::FLAG_KEBENARAN_EDIT::: "+ FLAG_KEBENARAN_EDIT + " :::CHECK_EDIT::: "+CHECK_EDIT	);
			
			if(FLAG_KEBENARAN_EDIT.equals("1")) {
                if(CHECK_EDIT.equals("1")){//1 = YES(dah KLIK PADA NOTIFIKASI UTILITY, Kebenaran Kemaskini Fail)
                	//myLogger.info(":::::::: updateFlagKemaskiniPerintah ::::::::: "	);
                	logic.updateFlagKemaskiniPerintah(getParam("idFail"));
                }
			}
			*/
			
			
		} else {
			
			//GO TO LIST PERINTAH
			
			vm = "app/ppk/frmSenaraiFailPerintahSek8.jsp";
			
			if(role.equals("userSemakanPerintah"))
			{
				
				logic.carianFail_semakanPerintahHQ(role,getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"),getParam("socJenisKp"), getParam("txtIcSimati"), session);
				list = logic.getSenaraiFail_semakanPerintahHQ();
			}
			else
			{
				
			logic.carianFail(getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
			list = logic.getSenaraiFail();
			//nofail=getParam("txtNoFail");
			}
			this.context.put("SenaraiFail", list);
			
			this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "class=\"autoselect\""));
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txtPemohon", getParam("txtPemohon"));
			this.context.put("txtSimati", getParam("txtSimati"));
			this.context.put("jenisIc", getParam("socJenisKp"));
			this.context.put("txtIcSimati", getParam("txtIcSimati"));
			setupPage(session,action,list);
		}

		//SET DEFAULT PARAM
		this.context.put("actionPerintah", actionPerintah);
        this.context.put("mode", mode);
        this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
        this.context.put("selectedTabUpper", selectedTabUpper);
        this.context.put("selectedTabLower", selectedTabLower);
        
        //SET FLAG PARAM
        this.context.put("flagAdaHTA", flagAdaHTA);
        this.context.put("flagAdaHTATH", flagAdaHTATH);
        this.context.put("flagAdaHA", flagAdaHA);
        
        this.context.put("flagAdaHTAPT", flagAdaHTAPT);
        this.context.put("flagAdaHAPT", flagAdaHAPT);
        this.context.put("flagAdaHTAPKT", flagAdaHTAPKT);
        this.context.put("flagAdaHAPKT", flagAdaHAPKT);
        this.context.put("flagAdaHTAPL", flagAdaHTAPL);
        this.context.put("flagAdaHAPL", flagAdaHAPL);
        this.context.put("flagAdaHTAPF", flagAdaHTAPF);
        this.context.put("flagAdaHAPF", flagAdaHAPF);
        
        this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
		this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
		
		this.context.put("flagSelesaiHTA", flagSelesaiHTA);
		this.context.put("flagSelesaiHA", flagSelesaiHA);
		
		if(!idPermohonanSimati.equals(""))
		{
			if (checkSelesaiPembahagian(idPermohonanSimati, idPerintah)){
				flagSelesaiPembahagian = "T";
			}
		}
		this.context.put("flagSelesaiPembahagian", flagSelesaiPembahagian);
		
		
		//arief try add open
		logic.setListSemak(idPermohonan, userId);
		list = logic.getListSemak();
		// get data keputusan permohonan
		keputusanPermohonan = logic.getKeputusanPermohonan(idPermohonan);
		String idkp = "";
		String idperbicaraan = "";
		String idpsk = "";
		String idNeg = "";
		String currentBil = "";
		String idpejabat = "";
		String idjenispejabat = "";
		String tempatBicara = "";
		
		if (keputusanPermohonan.size() != 0) {
			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
			idkp = kp.get("id_keputusanpermohonan").toString();
		}
				
		// --data notis
		String idpbrn = "";
		String nama_peg_pengendali = "";

		logic.setListSemakWithData(idkp);
		dataPerintah = logic.getListSemakWithData();

		if (dataPerintah.size() != 0) {
			Hashtable idn = (Hashtable) dataPerintah.get(0);
			idpbrn = idn.get("id_perbicaraan").toString();
			idpsk = idn.get("id_unitpsk").toString();
			idNeg = idn.get("id_negeribicara").toString();
			currentBil = idn.get("bil_bicara").toString();
			idpejabat = idn.get("id_pejabat").toString();
			tempatBicara = idn.get("tempat_bicara").toString();
			idjenispejabat = idn.get("id_jenispejabat").toString();
			nama_peg_pengendali = idn.get("peg_pengendali").toString();
						
		
			if(nama_peg_pengendali.equals("")){
				nama_peg_pengendali = "";
			}
		}
		context.put("dataPerintah_size", dataPerintah.size());
		context.put("nama_peg_pengendali", nama_peg_pengendali);
		myLogger.info("nama_peg_pengendali::"+nama_peg_pengendali);
		
		String username = (String) session.getAttribute("_portal_username");
		logic.setValidPegawaiPengendali(usid,idpbrn,nama_peg_pengendali,username );
		validPegPengendali = logic.getValidPegawaiPengendali();

		System.out.println("validPegPengendali.size()==="+validPegPengendali.size());
		if (validPegPengendali.size() != 0) {
			context.put("enabledPegawai", "yes");
		} else {
			context.put("enabledPegawai", "no");
		}
		
		String signedData = getParam("signedData");
		String open_pupop = getParam("open_pupop");
		if(open_pupop.equals("Y"))
		{
			//NO_FAIL="+NO_FAIL+"&id_perbicaraan="+id_perbicaraan+"&idfail="+idfail+
			context.put("open_pupop",open_pupop);
			context.put("NO_FAIL", getParam("NO_FAIL"));
			context.put("id_perbicaraan", getParam("id_perbicaraan"));
			context.put("idfail", getParam("idfail"));	
			context.put("signedData", getParam("signedData"));				
		}
		else
		{
			context.put("open_pupop","");
		}
		

		//GET SIGNEDDATA
		String dataDahSign = logic.getSignedData(getParam("id_perbicaraan"));
		context.put("dataDahSign", dataDahSign);
		
		//arief try add close
        
        //SET ID PARAM
		
        this.context.put("idPermohonanSimati", idPermohonanSimati);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPerintah", idPerintah);
        this.context.put("idSimati", idSimati);
        this.context.put("idStatus", idStatus);
        
        if (!anchor.equals("")){
        	this.context.put("onload", " \"javascript:goToAnchor('" + anchor + "')\"");
        }
        context.put("userRole", role); 
        System.out.println("vm perintah ==="+vm);
		return vm;
	}





//Salnizam starts
		private void paparLaporanSerahan(String noFail) throws Exception{
		Db db = null;
		Db db2 = null;
		String sqlPaparTblLaporanHantarPerintah = "";
		String sqlPaparNamaPenghantarNotis = "";
		System.out.println("paparLaporanSerahan");	
		try {
	    	db = new Db();
	    	Connection con = db.getConnection();
	    	Statement stmt = db.getStatement();
	    	String noFail1 = noFail;
	    	sqlPaparTblLaporanHantarPerintah = "SELECT NO_FAIL,JENIS_PENGHANTARAN, TARIKH_SERAHAN, NAMA_PENYERAH, NAMA_PENERIMA, NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, CATATAN, ID_PERINTAH, ID_PENGHANTARAN FROM TBLPPKHANTARPERINTAH WHERE NO_FAIL='" + noFail1 + "' AND ID_PENGHANTARAN=(SELECT MAX(ID_PENGHANTARAN) FROM TBLPPKHANTARPERINTAH WHERE NO_FAIL = '" + noFail1 + "')";
	    
	    	System.out.println("sqlPaparTblLaporanHantarPerintah = " + sqlPaparTblLaporanHantarPerintah);
	    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			ResultSet rs = stmt.executeQuery(sqlPaparTblLaporanHantarPerintah);
			System.out.println("check dia baca x kat sini");
			if (rs != null || "".equals(rs))
			{
				System.out.println("read here1-----");
				this.context.put("TARIKH_SERAHAN", "");
				this.context.put("NAMA_PENERIMA", "");
				this.context.put("NO_KP_BARU","");
				this.context.put("NO_KP_LAMA","");
				this.context.put("NO_KP_LAIN","");
				this.context.put("kp1","");
				this.context.put("kp2","");
				this.context.put("kp3","");
				this.context.put("kplama","");
				this.context.put("kplain","");
				this.context.put("CATATAN", "");
				this.context.put("JENIS_PENGHANTARAN", "");
				this.context.put("NAMA_PENYERAH", "");
			while (rs.next())
			{	
				System.out.println("read here-----");
				this.context.put("JENIS_PENGHANTARAN", rs.getString("JENIS_PENGHANTARAN"));
				Date TARIKH_SERAHAN1 = rs.getDate("TARIKH_SERAHAN");
				String nama_penyerah = rs.getString("NAMA_PENYERAH");
				//String TARIKH_SERAHAN = dateFormat.format(TARIKH_SERAHAN1);
				String TARIKH_SERAHAN =  rs.getDate("TARIKH_SERAHAN") == null ? "" : dateFormat.format(rs.getDate("TARIKH_SERAHAN"));//aishahlatip 06022017
				
				/*
				if (id_penghantar_notis != null)
				{
					try {
				    	db2 = new Db();
				    	Connection con2 = db2.getConnection();
				    	Statement stmt2 = db2.getStatement();
				    	sqlPaparNamaPenghantarNotis = "SELECT PE.NAMA, PE.ID_PENGHANTARNOTIS, PE.KOD_PENGHANTAR_NOTIS FROM TBLPPKRUJPENGHANTARNOTIS PE WHERE PE.ID_PENGHANTARNOTIS='" + id_penghantar_notis + "'";
				    			//"SELECT NO_FAIL,JENIS_PENGHANTARAN, TARIKH_SERAHAN, ID_PENGHANTAR_NOTIS, NAMA_PENERIMA, NO_KP_PENERIMA, CATATAN, ID_PERINTAH, ID_PENGHANTARAN FROM TBLPPKHANTARPERINTAH WHERE NO_FAIL='" + noFail1 + "' AND ID_PENGHANTARAN=(SELECT MAX(ID_PENGHANTARAN) FROM TBLPPKHANTARPERINTAH WHERE NO_FAIL = '" + noFail1 + "')";
				    	//sqlPaparTblLaporanHantarPerintah = "SELECT * FROM TBLPPKHANTARPERINTAH WHERE (ID_PENGHANTARAN =(SELECT MAX(ID_PENGHANTARAN) FROM TBLPPKHANTARPERINTAH) AND NO_FAIL = '" + noFail1 + "')";	
				    	System.out.println("sqlPaparTblLaporanHantarPerintah = " + sqlPaparTblLaporanHantarPerintah);
				    
						ResultSet rs2 = stmt2.executeQuery(sqlPaparNamaPenghantarNotis);
						while (rs2.next())
						{
							this.context.put("NAMA", rs2.getString("NAMA"));
							this.context.put("KOD_PENGHANTAR_NOTIS", rs2.getString("KOD_PENGHANTAR_NOTIS"));
						}
					}
					finally 
					{
			    	
				      if (db2 != null) db2.close();
				     
					}
				}
				*/
				this.context.put("TARIKH_SERAHAN", TARIKH_SERAHAN);
				this.context.put("NAMA_PENERIMA", rs.getString("NAMA_PENERIMA") == null ? ""
						:rs.getString("NAMA_PENERIMA"));
				this.context.put("kp1", rs.getString("NO_KP_BARU") == null ? ""
						:rs.getString("NO_KP_BARU").substring(0, 6));
				this.context.put("kp2", rs.getString("NO_KP_BARU") == null ? ""
						:rs.getString("NO_KP_BARU").substring(6, 8));
				this.context.put("kp3", rs.getString("NO_KP_BARU") == null ? ""
						:rs.getString("NO_KP_BARU").substring(8, 12));
				this.context.put("kplama", rs.getString("NO_KP_LAMA") == null ? ""
						:rs.getString("NO_KP_LAMA"));
				this.context.put("kplain", rs.getString("NO_KP_LAIN") == null ? ""
						:rs.getString("NO_KP_LAIN"));
				this.context.put("CATATAN", rs.getString("CATATAN") == null ? ""
						:rs.getString("CATATAN"));	
				this.context.put("NAMA_PENYERAH", rs.getString("NAMA_PENYERAH") == null ? ""
						:rs.getString("NAMA_PENYERAH"));
				//this.context.put("NAMA", rs.getString("NAMA"));
			}
			}
			else
			{
				System.out.println("read here2-----");
				this.context.put("JENIS_PENGHANTARAN", "Tiada");
				this.context.put("TARIKH_SERAHAN", "0/0/0000");
				this.context.put("NAMA_PENERIMA", "Tiada");
				this.context.put("NO_KP_BARU", "");
				this.context.put("NO_KP_LAMA", "");
				this.context.put("NO_KP_LAIN", "");
				this.context.put("CATATAN", "Tiada");
			}
		}
		
		finally 
			{
	    	
		      if (db != null) db.close();
		     
			}
		
		
		}
	
		private void saveData(String noFail,String txtCatatan, String txtTarikh, String txtNamaPenerima,String txtNoKpBaru,String txtNoKpLama,String txtNoKpLain,String radioJenis, String txtNamaPenghantarNotis, String idPerintah) throws Exception {
		Db db = null;
		String sqlInsertTblLaporanHantarPerintah = "";
		System.out.println("Dalam SaveData");
		    try {
		    	db = new Db();
		    	Connection con = db.getConnection();
		    	con.setAutoCommit(false);
		    	Statement stmt = db.getStatement();
		    	//SQLRenderer r_get = new SQLRenderer();
		    	long id_penghantaran =  DB.getNextID(db,"TblPPKHantarPerintah_SEQ");
		    	String txtNamaPenerima1 = txtNamaPenerima;
		    	String txtTarikh1 = "to_date('" + txtTarikh + "','dd/MM/yyyy')";
		    	String txtNoKpBaru1 = txtNoKpBaru;
		    	String txtNoKpLama1 = txtNoKpLama;
		    	String txtNoKpLain1 = txtNoKpLain;
		    	String radioJenis1 = radioJenis; 
		    	String txtCatatan1 = txtCatatan;
		    	String noFail1 = noFail;
		    	//String jenisKP1 = jenisKP;
		    	String idperintah = idPerintah;
		    	
		    	SQLRenderer r = new SQLRenderer();
		    	
		    	r.add("ID_PENGHANTARAN", id_penghantaran);
		    	r.add("NO_FAIL", noFail1);
		    	r.add("CATATAN", txtCatatan1);
		    	r.add("TARIKH_SERAHAN", r.unquote(txtTarikh1));
		    	//r.add("TARIKH_SERAHAN", r.unquote("SYSDATE"));
		    	r.add("JENIS_PENGHANTARAN", radioJenis1);
		    	r.add("NAMA_PENERIMA", txtNamaPenerima1);
		    	r.add("NO_KP_BARU", txtNoKpBaru1);
		    	r.add("NO_KP_LAMA", txtNoKpLama1);
		    	r.add("NO_KP_LAIN", txtNoKpLain1);
		    	r.add("NAMA_PENYERAH", txtNamaPenghantarNotis);
		    	r.add("ID_PERINTAH", idperintah);
		    	//r.add("TARIKH_SERAHAN",tarikh);
		    	sqlInsertTblLaporanHantarPerintah = r.getSQLInsert("TBLPPKHANTARPERINTAH");
		    	System.out.println("sqlInsertTblLaporanHantarPerintah = " + sqlInsertTblLaporanHantarPerintah);
				stmt.executeUpdate(sqlInsertTblLaporanHantarPerintah);
				
				
		    	/*
		    	sqlInsertTblLaporanHantarPerintah = "INSERT INTO TBLPPKHANTARPERINTAH (ID_PENGHANTARAN, NO_FAIL, " +
		    			"CATATAN, TARIKH_SERAHAN, JENIS_PENGHANTARAN, NAMA_PENERIMA, NO_KP_BARU, NO_KP_LAMA, " +
		    			"NO_KP_LAIN, NAMA_PENYERAH, ID_PERINTAH) " +
		    			"VALUES ('"+id_penghantaran+"','"+noFail1+"','"+txtCatatan1+"','"+txtTarikh1+"'," +
		    					"'"+radioJenis1+"','"+txtNamaPenerima1+"','"+txtNoKpBaru1+"','"+txtNoKpLama1+"'," +
		    							"'"+txtNoKpLain1+"','"+txtNamaPenghantarNotis+"','"+idperintah+"')";*/
		    	
		    	//stmt.executeUpdate(sqlInsertTblLaporanHantarPerintah);
		    	con.commit();
		    	return;
		    }
		    finally {
			      if (db != null) db.close();
		    }
			}

//Salnizam ends



	private boolean checkSelesaiPembahagian(String idPermohonanSimati, String idPerintah) throws Exception {

		//CHECK ALL HTA YANG BELUM DIDAFTARKAN
		if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
			this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.");
			return true;
		}				
		//CHECK ALL HA YANG BELUM DIDAFTARKAN
		if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
			this.context.put("systemMsg", "Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.");
			return true;
		}
		//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.");
				return true;
			}
		}
		//check all HAPT telah dibahagikan sepenuhnya
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkPembahagianHAPTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.");
				return true;
			}
		}
		//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
		if (getParam("flagAdaHTAPKT").equals("1")){
			if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.");
				return true;
			}
		}
		//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
		if (getParam("flagAdaHAPKT").equals("1")){
			if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.");
				return true;
			}
		}
		//check all HTA telah dikemaskini bagi perintah lelong
		if (getParam("flagAdaHTAPL").equals("1")){
			if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.");
				return true;
			}
		}
		//check all HA telah dikemaskini bagi perintah lelong
		if (getParam("flagAdaHAPL").equals("1")){
			if (logic.checkPembahagianHAPLLengkap(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
		if (getParam("flagAdaHTAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
		if (getParam("flagAdaHAPT").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
		if (getParam("flagAdaHTAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
		if (getParam("flagAdaHAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
		if (getParam("flagAdaHTAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
		if (getParam("flagAdaHAPF").equals("1")){
			if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
				this.context.put("systemMsg", "Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.");
				return true;
			}
		}
		
		return false;
	}

	private void updateHTAPL(String idHTA, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("jenisLelong", getParam("sorJenisLelong"));
		hash.put("tarikhJualan", getParam("txdTarikhJualan"));
		hash.put("hargaRizab", utils.RemoveSymbol(getParam("txtHargaRizab").toString()));
		hash.put("amaun", utils.RemoveSymbol(getParam("txtAmaun").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHTAPL(idHTA,hash,session);
	}
	
	private void updateHAPL(String idHAMST, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("jenisLelong", getParam("sorJenisLelongHAPL"));
		hash.put("tarikhJualan", getParam("txdTarikhJualanHAPL"));
		hash.put("hargaRizab", utils.RemoveSymbol(getParam("txtHargaRizabHAPL").toString()));
		hash.put("amaun", utils.RemoveSymbol(getParam("txtAmaunHAPL").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHAPL(idHAMST,hash,session);
	}

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
				this.context.put("SenaraiFail",paging.getPage(page));
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
}
/**List fail-fail Perintah dan T/T Digital :
Perintah
1.	ekptg.view.ppk.FrmPerintahSek8
2.	ekptg.model.ppk.FrmPerintahSek8Data
3.	ekptg.model.ppk.FrmPrmhnnSek8Notis
4.	ekptg.model.ppk.FrmHeaderPpk
5.	ekptg.model.ppk.FrmPrmhnnSek8InternalData
6.	app/ppk/frmPerintahMaklumatPerintahSek8.jsp
7.	app/ppk/frmSenaraiFailPerintahSek8.jsp
8.	app/ppk/tindakanPegawaiPerintahSek8.jsp
9.	FrmIntegrasiDGCertPerintah.java
10.	app/ppk/DGCertPerintah.jsp
11.	FrmPerintahSek8Data.java
12.	app/ppk/TandatanganSuccessPerintah.jsp
*/