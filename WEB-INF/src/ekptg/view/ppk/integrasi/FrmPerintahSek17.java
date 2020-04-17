package ekptg.view.ppk.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
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
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPerintahSek17Data;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.perintah.FrmPerintah17Bean;
import ekptg.model.ppk.perintah.IMaklumatPerintah;

public class FrmPerintahSek17 extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private IMaklumatPerintah iPerintah = null;  

	FrmPerintahSek17Data logic = new FrmPerintahSek17Data();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPrmhnnSek8InternalData logicPendaftaran2 = new FrmPrmhnnSek8InternalData();
	FrmPrmhnnSek8SenaraiSemakInternalData logicPendaftaran = new FrmPrmhnnSek8SenaraiSemakInternalData();
	static Logger myLogger = Logger.getLogger(ekptg.view.ppk.integrasi.FrmPerintahSek17.class);
	
	Utils utils = new Utils();
	String role = null;
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		role = (String)session.getAttribute("myrole");
	    myLogger.info("CURRENT ROLE :"+role);
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    } 	    
	    //GET DEFAULT PARAM
		String vm = ""; 
        String actionPerintah = getParam("actionPerintah");
        String mode = getParam("mode");
        String hitButt = getParam("hitButt");
        //String submit = getParam("command");
        String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
        
        /*myLogger.info("::: actionPerintah :"+actionPerintah);
        myLogger.info("::: mode :"+mode);
        myLogger.info("::: flagPopup :"+flagPopup);
        myLogger.info("::: modePopup :"+modePopup);        
        */
		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper) ) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower) ) {
			selectedTabLower = "0";
		}
		String selectedTabPT = getParam("selectedTabPT").toString();
		if (selectedTabPT == null || "".equals(selectedTabPT) ) {
			selectedTabPT = "0";
		}
		String selectedTabPA = getParam("selectedTabPA").toString();
		if (selectedTabPA == null || "".equals(selectedTabPA) ) {
			selectedTabPA = "0";
		}
		String anchor = getParam("anchor");

		String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
        
        //GET VALUE PARAM
		String idPermohonanSimati = getParam("idPermohonanSimati");
        String idPermohonan = getParam("idPermohonan");
        String idFail = getParam("idPermohonan");
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
		
		String hartaTertinggal = getParam("hartaTertinggal");
		String batalPT = getParam("batalPT");
		String lantikPT = getParam("lantikPT");
		String batalPA = getParam("batalPA");
		String lantikPA = getParam("lantikPA");
		
		String flagSelesaiHTA = getParam("flagSelesaiHTA");
		String flagSelesaiHA = getParam("flagSelesaiHA");
		
		String flagSelesaiPembahagian = "Y";
		this.context.put("systemMsg", "");
        
        //VECTOR
        Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatPermohonan = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanHeaderDetail = new Vector<Hashtable<String, String>>();
        
        //list.clear();
        beanMaklumatPermohonan.clear();
        beanHeaderDetail.clear();
        
        Vector<Hashtable<String, String>> listHTA = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatHTA = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHTAPopup = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHTATH = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatHTATH = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHA = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatHA = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHAPopup = new Vector<Hashtable<String, String>>();
        
        listHTA.clear();
        beanMaklumatHTA.clear();
        listHTAPopup.clear();
        listHTATH.clear();
        beanMaklumatHTATH.clear();
        listHA.clear();
        beanMaklumatHA.clear();
        listHAPopup.clear();
        
        Vector<Hashtable<String, String>> listHTAPT = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHAPT = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHTAPKT = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHAPKT = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHTAPL = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHAPL = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHTAPF = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> listHAPF = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatHTAPL = new Vector<Hashtable<String, String>>();
        Vector<Hashtable<String, String>> beanMaklumatHAPL = new Vector<Hashtable<String, String>>();
        
        
        
        listHTAPT.clear();
        listHAPT.clear();
        listHTAPKT.clear();
        listHAPKT.clear();
        listHTAPL.clear();
        listHAPL.clear();
        listHTAPF.clear();
        listHAPF.clear();
        
        Vector<Hashtable<String,String>> listHTAPTDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listHAPTDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listHTAPKTDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listPembahagianHTAPKTDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listHAPKTDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listHTAPFDTL = new Vector<Hashtable<String,String>>();
        Vector<Hashtable<String,String>> listHAPFDTL = new Vector<Hashtable<String,String>>();
        
        listHTAPTDTL.clear();
        listHAPTDTL.clear();
        listHTAPKTDTL.clear();
        listHAPKTDTL.clear();
        listHTAPFDTL.clear();
        listHAPFDTL.clear();
        
        //Vector senaraiPT = new Vector();
		//senaraiPT.clear();
		
		Vector<Hashtable<String,String>> listHTAPKTTerdahulu = new Vector<Hashtable<String,String>>();
		Vector<Hashtable<String,String>> listHAPKTTerdahulu = new Vector<Hashtable<String,String>>();

		listHTAPKTTerdahulu.clear();
		listHAPKTTerdahulu.clear();
		
		Vector<Hashtable<String,String>> listHTAPKTTerdahuluBB = new Vector<Hashtable<String,String>>();
		Vector<Hashtable<String,String>> listHAPKTTerdahuluBB = new Vector<Hashtable<String,String>>();

		listHTAPKTTerdahuluBB.clear();
		listHAPKTTerdahuluBB.clear();
		
		//Vector<Hashtable<String,String>> listHTAPKTTerdahuluBL = new Vector<Hashtable<String,String>>();
		//Vector<Hashtable<String,String>> listHAPKTTerdahuluBL = new Vector<Hashtable<String,String>>();

		listHTAPKTTerdahuluBB.clear();
		listHAPKTTerdahuluBB.clear();
		
		Vector<Hashtable<String,String>> listHTAPATerdahulu = new Vector<Hashtable<String,String>>();
		Vector<Hashtable<String,String>> listHAPATerdahulu = new Vector<Hashtable<String,String>>();

		listHTAPATerdahulu.clear();
		listHAPATerdahulu.clear();
		
		
		Vector<Hashtable<String,String>> listHTAduluPerintah = new Vector<Hashtable<String,String>>();
		listHTAduluPerintah.clear();
		Vector<Hashtable<String,String>> listOBHTAdulu = new Vector<Hashtable<String,String>>();
		listOBHTAdulu.clear();
		Vector<Hashtable<String,String>> listHadulu = new Vector<Hashtable<String,String>>();
		listHadulu.clear();
		Vector<Hashtable<String,String>> listOBHAdulu = new Vector<Hashtable<String,String>>();
		listOBHAdulu.clear();
		Vector<Hashtable<String,String>> check_pilihan_ha = new Vector<Hashtable<String,String>>();
		check_pilihan_ha.clear();
		Vector<Hashtable<String,String>> check_pilihan_ha_ob = new Vector<Hashtable<String,String>>();
		check_pilihan_ha_ob.clear();
		Vector<Hashtable<String,String>> check_pilihan = new Vector<Hashtable<String,String>>();
		check_pilihan.clear();
		Vector<Hashtable<String,String>> check_pilihan_hta_ob = new Vector<Hashtable<String,String>>();
		check_pilihan_hta_ob.clear();	
		
		
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
		
		String catatanPerintah = getParam("txtCatatanPerintah");
	    
		this.context.put("onload", "");
		
	
		
	    //SCREEN NAVIGATION
		if (actionPerintah.equals("papar")){
			
			vm = "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
			
			//<-------------------- SELESAI PERMOHONAN --------------------------->
        	
        	if (hitButt.equals("selesaiPermohonan")){
        		
        		//CHECK ALL HTA YANG BELUM DIDAFTARKAN
				if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
				}				
				//CHECK ALL HA YANG BELUM DIDAFTARKAN
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
				}
				
				
				
				//buka alert
				//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HAPT telah dibahagikan sepenuhnya
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkPembahagianHAPTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHTAPKT").equals("1")){
					if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHAPKT").equals("1")){
					if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
								
				//check all HTA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHTAPL").equals("1")){
					if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHAPL").equals("1")){
					if (logic.checkPembahagianHAPLLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//tutup alert
				
				
				//all validation pass - proceed to hantar permohonan
				if (postDB){
					logic.selesaiPermohonan(idPermohonan, session);
				}
			}
        	
        	//<-------------------- SELESAI --------------------------->

			if (hitButt.equals("selesai")){	
				//CHECK ALL HTA YANG BELUM DIDAFTARKAN
				if (logic.checkHTAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih (Ada Hakmilik) Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
				}				
				//CHECK ALL HA YANG BELUM DIDAFTARKAN
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Simati Yang Belum Didaftarkan Perintahnya.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
				}
				//check all HTAPT telah dibahagikan sepenuhnya bagi perintah terus
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkPembahagianHTAPTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HAPT telah dibahagikan sepenuhnya
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkPembahagianHAPTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Yang Belum Dibahagikan Sepenuhnya Bagi Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HTA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHTAPKT").equals("1")){
					if (logic.checkPembahagianHTAPKTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Tidak Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HA telah dibahagikan sepenuhnya bagi perintah kuasa tadbir
				if (getParam("flagAdaHAPKT").equals("1")){
					if (logic.checkPembahagianHAPKTLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Harta Alih Perintah Kuasa Pentadbir Yang Belum Dilantik Pentadbir.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HTA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHTAPL").equals("1")){
					if (logic.checkPembahagianHTAPLLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Tidak Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all HA telah dikemaskini bagi perintah lelong
				if (getParam("flagAdaHAPL").equals("1")){
					if (logic.checkPembahagianHAPLLengkap(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Maklumat Harta Alih Yang Belum Dikemaskini Bagi Perintah Lelong.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pa
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pa
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah terus is registered dia punya pt
				if (getParam("flagAdaHTAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Terus.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah terus is registered dia punya pt
				if (getParam("flagAdaHAPT").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHAPT(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Terus.')\"");
					return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pa
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPAHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Belum Didaftarkan Pemegang Amanah Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under hta perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHTAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHTAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Tak Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}
				//check all waris yang belum dewasa/kurang waras under ha perintah Faraid is registered dia punya pt
				if (getParam("flagAdaHAPF").equals("1")){
					if (logic.checkWarisYangPerluAdaPTHAPF(idPerintah)){
						this.context.put("onload", " \"javascript:alert('Masih Terdapat Waris Yang Hilang Belum Didaftarkan Pentadbir Bagi Harta Alih Untuk Perintah Faraid.')\"");
						return "app/ppk/frmPerintahMaklumatPerintahSek17.jsp";
					}
				}

				//all validation pass - proceed to selesai permohonan
				if (postDB){
					if (idStatus.equals("25")){
						logic.selesai(idPermohonan, idPerintah, session,idPermohonanSimati);
					}
				}
			}
			
 			//<--------------------------- HARTA TAK ALIH (ADA HAKMILIK) --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA TAK ALIH (ADA HAKMILIK)
			if (hitButt.equals("simpanBaruHTA")){
				if (postDB) {
					String idspentadbir[] = request.getParameterValues("idspentadbir");
					if (idspentadbir != null) {
						for (int i = 0; i < idspentadbir.length; i++) {
							logic.saveHTA(getParam("socJenisPerintahHTA"), getParam("txtCatatanHTA"), idspentadbir[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
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
					Hashtable<String, String> hashHTA = new Hashtable<String, String>();
					hashHTA.put("catatan", getParam("txtCatatanHTA") == null ? "" : getParam("txtCatatanHTA"));
					beanMaklumatHTA.addElement(hashHTA);
					this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
					
					this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
					
					// SENARAI HTA
					logic.setDataSenaraiHTAPopup(idPermohonanSimati, idPerintah);
					listHTAPopup = logic.getSenaraiHTAPopup();
					this.context.put("SenaraiHTAPopup", listHTAPopup);
					
					Hashtable<?, ?> h = listHTAPopup.get(0);
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
					Hashtable<String, String> hashHTA = (Hashtable<String, String>) logic.getBeanMaklumatHTA().get(0);
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
        	
			//OPEN POPUP HARTA TAK ALIH (TIADA HAKMILIK)
        	if (flagPopup.equals("openHTATH")){
        		this.context.put("idHTA", idHTA);
        		
        		//MODE = NEW
				if (modePopup.equals("new")){
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					
					//DETAIL HTA
					Hashtable<String, String> hashHTA = new Hashtable<String, String>();
					hashHTA.put("catatan", getParam("txtCatatanHTA") == null ? "" : getParam("txtCatatanHTA"));
					beanMaklumatHTA.addElement(hashHTA);
					this.context.put("BeanMaklumatHTA", beanMaklumatHTA);
					
					this.context.put("selectJenisPerintahHTA",HTML.SelectJenisPerintahSek8("socJenisPerintahHTA", Long.parseLong(idJenisPerintahHTA), "", ""));
					
					// SENARAI HTA
					logic.setDataSenaraiHTATHPopup(idPermohonanSimati, idPerintah);
					listHTAPopup = logic.getSenaraiHTAPopup();
					this.context.put("SenaraiHTAPopup", listHTAPopup);
					
					Hashtable<String, String> h = listHTAPopup.get(0);
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
					logic.setDataMaklumatHTATH(idHTA,idPermohonanSimati);
					beanMaklumatHTA = logic.getBeanMaklumatHTA();
					Hashtable<String,String> hashHTA = (Hashtable<String,String>) logic.getBeanMaklumatHTA().get(0);
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
        	
        	// <--------------------------- HARTA ALIH --------------------------->
        	
        	//HITBUTTON FOR POPUP HARTA ALIH
			if (hitButt.equals("simpanBaruHA")){
				if (postDB) {
					String idspentadbir[] = request.getParameterValues("idspentadbir");
					if (idspentadbir != null) {
						for (int i = 0; i < idspentadbir.length; i++) {
							logic.saveHA(getParam("socJenisPerintahHA"), getParam("txtCatatanHA"), idspentadbir[i], idPerintah, idPermohonan, idSimati, idPermohonanSimati, session);
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
					Hashtable<String, String> hashHA = new Hashtable<String, String>();
					hashHA.put("catatan", getParam("txtCatatanHA") == null ? "" : getParam("txtCatatanHA"));
					beanMaklumatHA.addElement(hashHA);
					this.context.put("BeanMaklumatHA", beanMaklumatHA);
					
					this.context.put("selectJenisPerintahHA",HTML.SelectJenisPerintahSek8("socJenisPerintahHA", Long.parseLong(idJenisPerintahHA), "", ""));
					
					// SENARAI HA
					logic.setDataSenaraiHAPopup(idPermohonanSimati, idPerintah);
					listHAPopup = logic.getSenaraiHAPopup();
					this.context.put("SenaraiHAPopup", listHAPopup);
					
					Hashtable<String,String> h = listHAPopup.get(0);
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
					Hashtable<String,String> hashHA = logic.getBeanMaklumatHA().get(0);
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
					String idspentadbir[] = request.getParameterValues("idspentadbir");
					String status[] = request.getParameterValues("status");
					String BA[] = request.getParameterValues("txtBA");
					String BB[] = request.getParameterValues("txtBB");
					if (idspentadbir != null) {
						for (int i = 0; i < idspentadbir.length; i++) {
							if (BA[i] != null){
								if (Utils.parseLong(BA[i]) > 0){
									logic.updateHTAPT(idHTA, idspentadbir[i], status[i], BA[i], BB[i], idPerintah, session,idPermohonanSimati);
								} else {
									logic.removeHTAOBDTL(idHTA, idspentadbir[i]);
								}
							}
						}
					}
					
					//SAVE FOR WARIS YANG HILANG
					if (getParam("chkWarisHilang").equals("1")){
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
    			String flagBatal = "";
    			if (logic.getBeanHeaderDetail().size() != 0){
					Hashtable<String,String> hash = logic.getBeanHeaderDetail().get(0);					
					flagBatal = (String) hash.get("flagBatal");
    			}
    			
    			//logic.setDataSenaraiHTAPTDTL(idHTA, idSimati, idPermohonanSimati);
    			logic.setDataSenaraiHTAPTDTL(idHTA, idSimati, idPermohonanSimati, idPermohonan, flagBatal);
				listHTAPTDTL = logic.getSenaraiHTAPTDTL();
				this.context.put("SenaraiHTAPTDTL", listHTAPTDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHTAPTDTL.size(); i++){
					Hashtable<String, String> hash = (Hashtable<String, String>) listHTAPTDTL.get(i);
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
					Hashtable<String,String> hash = (Hashtable<String,String>) logic.getSenaraiHTAPTDTLHilang().get(0);
					
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
					String idspentadbir[] = request.getParameterValues("idspentadbir");
					String status[] = request.getParameterValues("status");
					String BA[] = request.getParameterValues("txtBA");
					String BB[] = request.getParameterValues("txtBB");
					if (idspentadbir != null) {
						for (int i = 0; i < idspentadbir.length; i++) {
							if (BA[i] != null){
								if (Utils.parseLong(BA[i]) > 0){
									logic.updateHAPT(idHA, idspentadbir[i], status[i], BA[i], BB[i], idPerintah, session,idPermohonanSimati);
								} else {
									logic.removeHAOBDTL(idHA, idspentadbir[i]);
								}
							}
						}
					}
					
					//save for waris yang hilang
					if (getParam("chkWarisHilang").equals("1")){
						logic.updateHAPTHilang(idHA, idPerintah, getParam("txtBAHilang"), getParam("txtBBHilang"), session,idPermohonanSimati);
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
    			
    			String flagBatal = "";
    			if (logic.getBeanHeaderDetail().size() != 0){
					Hashtable<String,String> hash = logic.getBeanHeaderDetail().get(0);					
					flagBatal = (String) hash.get("flagBatal");
    			}
    			
    			//logic.setDataSenaraiHAPTDTL(idHA, idSimati, idPermohonanSimati);
    			logic.setDataSenaraiHAPTDTL(idHA, idSimati, idPermohonanSimati,idPermohonan,flagBatal);
				listHAPTDTL = logic.getSenaraiHAPTDTL();
				this.context.put("SenaraiHAPTDTL", listHAPTDTL);
				
				Fraction f = new Fraction(0,1);
				for (int i = 0; i < listHAPTDTL.size(); i++){
					Hashtable<String, String> hash = (Hashtable<String, String>) listHAPTDTL.get(i);
					long pembawah = 1;
					
					if ("".equals(hash.get("BB"))) {
						hash.put("BB","0");
					}
					if ("".equals(hash.get("BA"))) {
						hash.put("BA","0");
					}
					
					if (!hash.get("BB").equals("0")){
						myLogger.info("--------------HASH TABLE BB :"+hash.get("BB").toString());
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					f = f.plus(f2);
				}
				
				logic.setDataSenaraiHAPTDTLHilang(idHA);
				if (logic.getSenaraiHAPTDTLHilang().size() != 0){
					Hashtable<String,String> hash = (Hashtable<String,String>) logic.getSenaraiHAPTDTLHilang().get(0);
					
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
    			
    			Hashtable<String,String> h = (Hashtable<String,String>) listHTAPKTDTL.get(0);
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
					Hashtable<String, String> hash = (Hashtable<String, String>) listPembahagianHTAPKTDTL.get(i);
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
        			String idspentadbir[] = request.getParameterValues("idspentadbir");
        			
        			if (idspentadbir != null) {
        				for (int i = 0; i < idspentadbir.length; i++) {
        					if (i == 0){
        						idPA1 = idspentadbir[i];
        					} else if (i == 1){
        						idPA2 = idspentadbir[i];
        					} else if (i == 2){
        						idPA3 = idspentadbir[i];
        					} else if (i == 3){
        						idPA4 = idspentadbir[i];
        					}
        				}
        			}
        			
        			logic.updateCatatanHA(getParam("txtCatatan"), idHA, session);
        			logic.updatePentadbir(idPA1, idPA2, idPA3, idPA4, idPerintah, session);
        		}
			}
        	
        	//OPEN POPUP HARTA  ALIH PERINTAH KUASA TADBIR
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
    			
    			Hashtable<String,String> h = (Hashtable<String,String>) listHAPKTDTL.get(0);
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
					Hashtable<String,String> hashHTAPL = logic.getBeanMaklumatHTAPL().get(0);
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
					Hashtable<String, String> hashHAPL = logic.getBeanMaklumatHAPL().get(0);
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
					Hashtable<String,String> hash = (Hashtable<String,String>) listHTAPFDTL.get(i);
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
					Hashtable<String,String> hash = (Hashtable<String,String>) listHAPFDTL.get(i);
					long pembawah = 1;
					long pengatas = 1;
					System.out.println("** BA:"+hash.get("BA"));
					System.out.println("** BB:"+hash.get("BB"));
					
					if (!"".equals(hash.get("BB")) && !hash.get("BB").equals("0")){
						pembawah = Long.parseLong(hash.get("BB").toString());
					}
					if (!"".equals(hash.get("BA")) && !hash.get("BA").equals("0")){
						pengatas = Long.parseLong(hash.get("BA").toString());
					}
					
					//Fraction f2 = new Fraction(Long.parseLong(hash.get("BA").toString()),pembawah);
					Fraction f2 = new Fraction(pengatas,pembawah);
					f = f.plus(f2);
				}
				this.context.put("jumlahBahagian", f.numerator() + " / " + f.denominator());
        	}
        	
        	//TODO COMMENT BY PEJE FOR TEMP
//        	// <--------------------------- BATAL AND BAHAGI PT--------------------------->
//        	
//        	if (hitButt.equals("batalDanBahagiHTAPKT")){
//        		if (postDB) {
//        			String idspentadbir[] = request.getParameterValues("idspentadbir");
//        			
//        			if (idspentadbir != null) {
//        				for (int i = 0; i < idspentadbir.length; i++) {
//        					logic.batalDanBahagiHTAPKT(idspentadbir[i], session);
//        				}
//        			}
//        		}
//        	}
//        	if (hitButt.equals("batalDanBahagiHAPKT")){
//        		if (postDB) {
//        			String idspentadbir[] = request.getParameterValues("idspentadbir");
//        			
//        			if (idspentadbir != null) {
//        				for (int i = 0; i < idspentadbir.length; i++) {
//        					logic.batalDanBahagiHAPKT(idspentadbir[i], session);
//        				}
//        			}
//        		}
//        	}
//        	if (hitButt.equals("batalDanLantikHTAPKT")){
//        		if (postDB) {
//        			String idspentadbir[] = request.getParameterValues("idspentadbir");
//        			
//        			if (idspentadbir != null) {
//        				for (int i = 0; i < idspentadbir.length; i++) {
//        					logic.batalDanLantikHTAPKT(idspentadbir[i], idPerintah, session);
//        				}
//        			}
//        		}
//        	}
//        	if (hitButt.equals("batalDanLantikHAPKT")){
//        		if (postDB) {
//        			String idspentadbir[] = request.getParameterValues("idspentadbir");
//        			
//        			if (idspentadbir != null) {
//        				for (int i = 0; i < idspentadbir.length; i++) {
//        					logic.batalDanLantikHAPKT(idspentadbir[i], idPerintah, session);
//        				}
//        			}
//        		}
//        	}
			
			//HEADER
			logic.setMaklumatPermohonan(idPermohonan,session);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("MaklumatPermohonan", beanMaklumatPermohonan);
			//hashtable maklumat header
			headerppk_baru(session,idPermohonan,"Y","","T");
			
			if (beanMaklumatPermohonan.size() != 0){
				//SET MODE AND ID_STATUS
				Hashtable<String, String> hashHeader = logic.getBeanMaklumatPermohonan().get(0);
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
			
			//setCatatanPerintah
			if (hitButt.equals("kemaskiniCatatan")){
				logic.updateCatatanPerintah(idPerintah, catatanPerintah, session);
			}
			//getCatatanPerintah
			this.context.put("catatanPerintah", catatanPerintah == null || catatanPerintah.equals("") ? logic.getCatatanPerintah(idPerintah) : catatanPerintah);
			
			//GET ID PERBICARAAN
			if (idPerbicaraan.isEmpty()){
				idPerbicaraan =  logic.getIdPerbicaraan(idPerintah);
			}
			this.context.put("idPerbicaraan", idPerbicaraan);

			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable<String,String> hashHeader = logic.getBeanMaklumatPermohonan().get(0);
				
				//SET VALUE FOR FLAG TUJUAN PERMOHONAN SEK17
				hartaTertinggal = hashHeader.get("hartaTertinggal").toString();
				batalPT = hashHeader.get("batalPT").toString();
				lantikPT = hashHeader.get("lantikPT").toString();
				batalPA = hashHeader.get("batalPA").toString();
				lantikPA = hashHeader.get("lantikPA").toString();
				
				//SET MODE AND ID_STATUS
//				if (flagFromSenaraiFailSek8.equals("yes")){
//					if (hashHeader.get("id_Status").equals("41") || hashHeader.get("id_Status").equals("25")){
//						idStatus = hashHeader.get("id_Status").toString();
//						mode = "update";
//					} else {
//						idStatus = hashHeader.get("id_Status").toString();
//						mode = "view";
//					}
//				} else {
//					if (hashHeader.get("id_Status").equals("41")){
//						idStatus = hashHeader.get("id_Status").toString();
//						mode = "update";
//					} else {
//						idStatus = hashHeader.get("id_Status").toString();
//						mode = "view";
//					}
//				}
				
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
			
			
			//FUNGSI BARU BY RAZMAN
			
			if(hitButt.equals("simpanHTA"))
			{
			myLogger.info("Simpan HTA");
			
			clearSuburusanStatus(idPermohonan,idFail,session);
			clearBatalLantik("HTA",session,idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);			
			simpanpilihanHTA(session,idPermohonanSimati,idPerintah);			
			simpanpilihanHTA_Perintah(session,idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);
			}
			
			if(hitButt.equals("simpanHA"))
			{
			myLogger.info("Simpan HA");
			
			clearSuburusanStatus(idPermohonan,idFail,session);
			clearBatalLantik("HA",session,idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);
			simpanpilihanHA(session,idPermohonanSimati,idPerintah);
			simpanpilihanHTA_Perintah(session,idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);
			}
			
			listHTAduluPerintah = logic.setDataHTAduluPerintah(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);
			this.context.put("listHTAduluPerintah", listHTAduluPerintah);
			
			listHadulu = logic.setDataHaDulu(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA);
			this.context.put("listHadulu", listHadulu);
			
			listOBHTAdulu = logic.setDataOBHTAdulu(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA,"");
			this.context.put("listOBHTAdulu", listOBHTAdulu);
			
			listOBHAdulu = logic.setDataOBHAdulu(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPA,"");
			this.context.put("listOBHAdulu", listOBHAdulu);
			
			check_pilihan_ha = logicPendaftaran.check_pilihan_ha(idPermohonanSimati);
			this.context.put("check_pilihan_ha",check_pilihan_ha);
			
			check_pilihan_ha_ob = logicPendaftaran.check_pilihan_ha_ob(idPermohonanSimati);
			this.context.put("check_pilihan_ha_ob",check_pilihan_ha_ob);
			
			
			check_pilihan = logicPendaftaran.check_pilihan(idPermohonanSimati);
			this.context.put("check_pilihan",check_pilihan);
			
			check_pilihan_hta_ob = logicPendaftaran.check_pilihan_hta_ob(idPermohonanSimati);
			this.context.put("check_pilihan_hta_ob",check_pilihan_hta_ob);
			
			//CHECK IF SIMATI ADA HTA, HTATH AND HA		
			if (logic.checkExistHTA(idPermohonanSimati,idPerintah)){
				flagAdaHTA = "1";
			}
			if (logic.checkExistHTATH(idPermohonanSimati, idPerintah)){
				flagAdaHTATH = "1";
			}
			if (logic.checkExistHA(idPermohonanSimati, idPerintah)){
				flagAdaHA = "1";
			}
			
			//SET VALUE FOR FLAG ADA LAGI TAK DATA PERINTAH KUASA TADBIR TERDAHULU YANG BELUM DIBATALKAN
			if (logic.checkExistPreviousRecordForHTAPKT(idPerintah)){
				this.context.put("flagAdaHTAPKTTerdahulu", "Y");
			} else {
				this.context.put("flagAdaHTAPKTTerdahulu", "T");
			}
			
			//SET VALUE FOR FLAG ADA LAGI TAK DATA PERINTAH KUASA TADBIR TERDAHULU YANG BELUM DIBATALKAN
			if (logic.checkExistPreviousRecordForHAPKT(idPerintah)){
				this.context.put("flagAdaHAPKTTerdahulu", "Y");
			} else {
				this.context.put("flagAdaHAPKTTerdahulu", "T");
			}
			
			//SET VALUE FOR FLAG ADA LAGI TAK DATA PEMEGANG AMANAH TERDAHULU YANG BELUM DIBATALKAN
			if (logic.checkExistPreviousRecordForHTAPA(idPerintah)){
				this.context.put("flagAdaHTAPATerdahulu", "Y");
			} else {
				this.context.put("flagAdaHTAPATerdahulu", "T");
			}
			
			//SET VALUE FOR FLAG ADA LAGI TAK DATA PEMEGANG AMANAH TERDAHULU YANG BELUM DIBATALKAN
			if (logic.checkExistPreviousRecordForHAPA(idPerintah)){
				this.context.put("flagAdaHAPATerdahulu", "Y");
			} else {
				this.context.put("flagAdaHAPATerdahulu", "T");
			}
			
			//AUTOMATIC REGISTER HTATH INTO PERINTAH KUASA TADBIR IF EXIST
			if (flagAdaHTATH.equals("1")){
				logic.registerHTATHIntoPKT(idPerintah, idPermohonanSimati, session);
			}
			
			//GET LIST HARTA TAK ALIH (ADA HAKMILIK)
			if (flagAdaHTA.equals("1")){
				logic.setDataSenaraiHTA(idPerintah,idPermohonanSimati);
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
			if (flagAdaHA.equals("1")){
				logic.setDataSenaraiHA(idPerintah,idPermohonanSimati);
				listHA = logic.getSenaraiHA();
				this.context.put("SenaraiHA", listHA);
				
				//CHECK ALL HA YANG BELUM DIDAFTARKAN
				if (logic.checkHAYangBelumDibahagikan(idPermohonanSimati, idPerintah)){
					flagSelesaiHA = "";
				} else {
					flagSelesaiHA = "Y";
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
			
			//TODO COMMENT BY PEJE FOR TEMP
//			if (batalPT.equals("Y") || lantikPT.equals("Y")){
//				logic.setDataSenaraiHTAPKTTerDahulu(idPerintah,idPermohonanSimati);
//				listHTAPKTTerdahulu = logic.getSenaraiHTAPKTTerDahulu();
//				this.context.put("SenaraiHTAPKTTerDahulu", listHTAPKTTerdahulu);
//				
//				logic.setDataSenaraiHTAPKTTerDahuluBB(idPerintah,idPermohonanSimati);
//				listHTAPKTTerdahuluBB = logic.getSenaraiHTAPKTTerDahuluBB();
//				this.context.put("SenaraiHTAPKTTerDahuluBB", listHTAPKTTerdahuluBB);
//				
//				logic.setDataSenaraiHTAPKTTerDahuluBL(idPerintah,idPermohonanSimati);
//				listHTAPKTTerdahuluBL = logic.getSenaraiHTAPKTTerDahuluBL();
//				this.context.put("SenaraiHTAPKTTerDahuluBL", listHTAPKTTerdahuluBL);
//				
//				logic.setDataSenaraiHAPKTTerDahulu(idPerintah,idPermohonanSimati);
//				listHAPKTTerdahulu = logic.getSenaraiHAPKTTerDahulu();
//				this.context.put("SenaraiHAPKTTerDahulu", listHAPKTTerdahulu);
//				
//				logic.setDataSenaraiHAPKTTerDahuluBB(idPerintah,idPermohonanSimati);
//				listHAPKTTerdahuluBB = logic.getSenaraiHAPKTTerDahuluBB();
//				this.context.put("SenaraiHAPKTTerDahuluBB", listHAPKTTerdahuluBB);
//				
//				logic.setDataSenaraiHAPKTTerDahuluBL(idPerintah,idPermohonanSimati);
//				listHAPKTTerdahuluBL = logic.getSenaraiHAPKTTerDahuluBL();
//				this.context.put("SenaraiHAPKTTerDahuluBL", listHAPKTTerdahuluBL);
//			}
//			
//			if (batalPA.equals("Y") || lantikPA.equals("Y")){
//				logic.setDataSenaraiHTAPATerDahulu(idPerintah,idPermohonanSimati);
//				listHTAPATerdahulu = logic.getSenaraiHTAPATerDahulu();
//				this.context.put("SenaraiHTAPATerDahulu", listHTAPATerdahulu);
//				
//				logic.setDataSenaraiHAPATerDahulu(idPerintah,idPermohonanSimati);
//				listHAPATerdahulu = logic.getSenaraiHAPATerDahulu();
//				this.context.put("SenaraiHAPATerDahulu", listHAPATerdahulu);
//			}
			
		} else {			
			//GO TO LIST PERINTAH			
			vm = "app/ppk/perintah/frmSenaraiFailPerintahSek17.jsp";	
			//context.remove("SenaraiFail");
			context.remove("totalRecords");	
			list = new Vector<Hashtable<String, String>>();
			if(role.equals("userSemakanPerintah") || role.contains("(INTEGRASI)")){
				//logic.carianFail_semakanPerintahHQ(role,getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
				//list = logic.getSenaraiFail_semakanPerintahHQ();
				if(!getParam("txtNoFail").equals("") || !getParam("txtPemohon").equals("") || !getParam("txtSimati").equals("") || !getParam("socJenisKp").equals("") || !getParam("txtIcSimati").equals("")){
				list = getPerintah().carianFail_semakanPerintahHQ(role,getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
				}
			}else{
				//logic.carianFail(getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
				//list = logic.getSenaraiFail();				
				list = getPerintah().carianFail(getParam("txtNoFail"),getParam("txtPemohon"), getParam("txtSimati"), getParam("socJenisKp"), getParam("txtIcSimati"), session);
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
        this.context.put("selectedTabPT", selectedTabPT);
        this.context.put("selectedTabPA", selectedTabPA);
        
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
		
		if (checkSelesaiPembahagian(idPermohonanSimati, idPerintah)){
			flagSelesaiPembahagian = "T";
		}
		this.context.put("flagSelesaiPembahagian", flagSelesaiPembahagian);
        
        //SET ID PARAM
        this.context.put("idPermohonanSimati", idPermohonanSimati);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPerintah", idPerintah);
        this.context.put("idSimati", idSimati);
        this.context.put("idStatus", idStatus);
        
        if (!anchor.equals("")){
        	this.context.put("onload", " \"javascript:goToAnchor('" + anchor + "')\"");
        }
	    
		return vm;
	}
	
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
		Hashtable<String, String> hash = new Hashtable<String, String>();
		hash.put("jenisLelong", getParam("sorJenisLelong"));
		hash.put("tarikhJualan", getParam("txdTarikhJualan"));
		hash.put("hargaRizab", Utils.RemoveSymbol(getParam("txtHargaRizab").toString()));
		hash.put("amaun", Utils.RemoveSymbol(getParam("txtAmaun").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHTAPL(idHTA,hash,session);
	}
	
	private void clearSuburusanStatus(String idpermohonan,String idfail, HttpSession session) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " DELETE FROM TBLRUJSUBURUSANSTATUSFAIL X WHERE X.ID_SUBURUSANSTATUSFAIL IN "+
			 " (SELECT SSF.ID_SUBURUSANSTATUSFAIL FROM TBLRUJSUBURUSANSTATUSFAIL SSF "+
			 " ,TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S WHERE "+
			 " SSF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS AND SS.ID_STATUS = S.ID_STATUS AND "+
			 " SSF.ID_PERMOHONAN = '"+idpermohonan+"' AND S.ID_STATUS IN ('41','21','25'))";
			myLogger.info(" DELETE SUBURUSANSTATUSFAIL :"+sql);
			stmt.executeUpdate(sql);	
			
			r.clear();
			r.update("ID_PERMOHONAN", idpermohonan);			
			r.add("ID_STATUS","41");
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			myLogger.info(" UPDATE STATUS PERMOHONAN :"+sql);
			stmt.executeUpdate(sql);
			
			setSuburusanStatusFail(idpermohonan,session);
			
		} finally {
			if (db != null)
				db.close();
		}
		}
	
	private void setSuburusanStatusFail(String idpermohonan,HttpSession session) throws Exception {
		Db db = null;
		String sql1 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql1 = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + idpermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql1);
			rs.next();
		    myLogger.info("setSuburusanStatusFail ID FAIL :"+rs.getString("ID_FAIL"));
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session,idpermohonan,(String)session.getAttribute("_ekptg_user_id"),"41","410",rs.getString("ID_FAIL")+"");
		
		} 
		finally {
			if (db != null)
				db.close();
		}
	}	
		
		
		
	
	
	
	private void updateHAPL(String idHAMST, HttpSession session) throws Exception {
		Hashtable<String, String> hash = new Hashtable<String, String>();
		hash.put("jenisLelong", getParam("sorJenisLelongHAPL"));
		hash.put("tarikhJualan", getParam("txdTarikhJualanHAPL"));
		hash.put("hargaRizab", Utils.RemoveSymbol(getParam("txtHargaRizabHAPL").toString()));
		hash.put("amaun", Utils.RemoveSymbol(getParam("txtAmaunHAPL").toString()));
		hash.put("catatan", getParam("txtCatatanHTAPL"));
		logic.updateHAPL(idHAMST,hash,session);
	}

//	public void setupPage(HttpSession session,String action,Vector list) {
//		
//		try {
//		
//			this.context.put("totalRecords",list.size());
//			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
//			
//			int itemsPerPage;
//			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
//				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
//			} else {
//				itemsPerPage = (Integer)this.context.get("itemsPerPage");
//			}
//		    
//		    if ("getNext".equals(action)) {
//		    	page++;
//		    } else if ("getPrevious".equals(action)) {
//		    	page--;
//		    } else if ("getPage".equals(action)) {
//		    	page = getParamAsInteger("value");
//		    } else if ("doChangeItemPerPage".equals(action)) {
//		       itemsPerPage = getParamAsInteger("itemsPerPage");
//		    }
//		    	
//		    Paging paging = new Paging(session,list,itemsPerPage);
//			
//			if (page > paging.getTotalPages()) page = 1; //reset page number
//				this.context.put("SenaraiFail",paging.getPage(page));
//			    this.context.put("page", new Integer(page));
//			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
//			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
//			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
//			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
//			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//			this.context.put("error",e.getMessage());
//		}	
//	}
	
	private void clearBatalLantik(String jenis_harta,HttpSession session,String idPerintah,String idPermohonanSimati,String batalPT,String lantikPT,String batalPA,String lantikPA) throws Exception
	{
		if(jenis_harta.equals("HTA"))
		{
			Vector<Hashtable<String,String>> listHTAduluPerintah_Pilihan = logic.setDataHTAduluPerintah_Pilihan(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPT);
			
			for(int i=0; i < listHTAduluPerintah_Pilihan.size(); i++)
			{
					Hashtable<String,String> h = (Hashtable<String,String>) listHTAduluPerintah_Pilihan.get(i);
									
					myLogger.info("---id_harta :"+h.get("idhta").toString());
					myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
					myLogger.info("---jenis_harta_check :"+h.get("JENIS_HTA").toString());
					myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHTAOBMST").toString());
					myLogger.info("   ");
					
					
					if(!h.get("ID_JENISPERINTAH").toString().equals("2"))
					{				
					logic.updateFlagBatalHTA_clear(h.get("ID_JENISPERINTAH").toString(),idPerintah,h.get("idhta").toString(),h.get("ID_PERINTAHHTAOBMST").toString(),"HTA",h.get("JENIS_HTA").toString(), session);	
					}
					
					
			 }
			
			
			
		}
		
		if(jenis_harta.equals("HA"))
		{
			Vector<Hashtable<String,String>> listDataHadulu_Pilihan = logic.setDataHaDulu_Pilihan(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPT);
			//clear rekod HA balik
			for(int i=0; i < listDataHadulu_Pilihan.size(); i++)
			{
					Hashtable<String,String> h = (Hashtable<String,String>) listDataHadulu_Pilihan.get(i);
									
					myLogger.info("---id_harta :"+h.get("id_Ha").toString());
					myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
					myLogger.info("---jenis_harta_check :");
					myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHAOBMST").toString());
					myLogger.info("   ");
					
					
					if(!h.get("ID_JENISPERINTAH").toString().equals("2"))
					{
						logic.updateFlagBatalHA_clear(h.get("ID_JENISPERINTAH").toString(),idPerintah,h.get("id_Ha").toString(),h.get("ID_PERINTAHHAOBMST").toString(),"HA","", session);										
						
					}
					
					
			 }
			
		
		}
		
		
	
	}
	
	private void simpanpilihanHTA_Perintah(HttpSession session,String idPerintah,String idPermohonanSimati,String batalPT,String lantikPT,String batalPA,String lantikPA) throws Exception
	{
		
		Vector<Hashtable<String,String>> listHTAduluPerintah_Pilihan = logic.setDataHTAduluPerintah_Pilihan(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPT);
		
		//clear rekod HTA dulu
		for(int i=0; i < listHTAduluPerintah_Pilihan.size(); i++)
		{
				Hashtable<String,String> h = (Hashtable<String,String>) listHTAduluPerintah_Pilihan.get(i);
								
				myLogger.info("---id_harta :"+h.get("idhta").toString());
				myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
				myLogger.info("---jenis_harta_check :"+h.get("JENIS_HTA").toString());
				myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHTAOBMST").toString());
				myLogger.info("   ");
				
				
				if(h.get("ID_JENISPERINTAH").toString().equals("2"))
				{
					logic.updateFlagBatalHTA_clear(h.get("ID_JENISPERINTAH").toString(),idPerintah,h.get("idhta").toString(),h.get("ID_PERINTAHHTAOBMST").toString(),"HTA",h.get("JENIS_HTA").toString(), session);	
					
				}
				
				
				
		 }
		
		
		//insert rekod HTA balik
		for(int i=0; i < listHTAduluPerintah_Pilihan.size(); i++)
		{
				Hashtable<String,String> h = (Hashtable<String,String>) listHTAduluPerintah_Pilihan.get(i);
								
				myLogger.info("---id_harta :"+h.get("idhta").toString());
				myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
				myLogger.info("---jenis_harta_check :"+h.get("JENIS_HTA").toString());
				myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHTAOBMST").toString());
				myLogger.info("   ");
				
				
				if(h.get("ID_JENISPERINTAH").toString().equals("2"))
				{
					myLogger.info("--------- NO:"+i+" id_hta" +h.get("idhta").toString());
					logic.updateFlagBatalHTA_insert(idPerintah,h.get("idhta").toString(),h.get("ID_PERINTAHHTAOBMST").toString(),"HTA",h.get("JENIS_HTA").toString(), session);	
										
				}
				
		 }
		
		
		
		Vector<Hashtable<String,String>> listDataHadulu_Pilihan = logic.setDataHaDulu_Pilihan(idPerintah,idPermohonanSimati,batalPT,lantikPT,batalPA,lantikPT);
		//clear rekod HA balik
		for(int i=0; i < listDataHadulu_Pilihan.size(); i++)
		{
				Hashtable<String,String> h = (Hashtable<String,String>) listDataHadulu_Pilihan.get(i);
								
				myLogger.info("---id_harta :"+h.get("id_Ha").toString());
				myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
				myLogger.info("---jenis_harta_check :");
				myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHAOBMST").toString());
				myLogger.info("   ");
				
				
				if(h.get("ID_JENISPERINTAH").toString().equals("2"))
				{
					logic.updateFlagBatalHA_clear(h.get("ID_JENISPERINTAH").toString(),idPerintah,h.get("id_Ha").toString(),h.get("ID_PERINTAHHAOBMST").toString(),"HA","", session);										
					
				}
				
				
		 }
		
		
		//insert rekod HA balik
		for(int i=0; i < listDataHadulu_Pilihan.size(); i++)
		{
				Hashtable<String,String> h = (Hashtable<String,String>) listDataHadulu_Pilihan.get(i);
								
				myLogger.info("---id_harta :"+h.get("id_Ha").toString());
				myLogger.info("---jenis_perintah_check :"+h.get("ID_JENISPERINTAH").toString());
				myLogger.info("---jenis_harta_check :");
				myLogger.info("---harta_obmst_check :"+h.get("ID_PERINTAHHAOBMST").toString());
				myLogger.info("   ");
				
				
				if(h.get("ID_JENISPERINTAH").toString().equals("2"))
				{
					logic.updateFlagBatalHA_insert(idPerintah,h.get("id_Ha").toString(),h.get("ID_PERINTAHHAOBMST").toString(),"HA","", session);										
				}
				
		 }
		
		
		
	
	}
	
	
	
	private void simpanpilihanHTA(HttpSession session,String idPermohonanSimati,String idPerintah) throws Exception
	{
		
		String per_mati = idPermohonanSimati;
		String bolehsimpan = "yes";
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		logicPendaftaran.clear_pilihanHTAMainDelete(per_mati,"");
		//logicPendaftaran.clear_pilihanHAMainDelete(per_mati,"");
		myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = this.request.getParameterValues("ids");
		myLogger.info("ids :"+ids);
		String[] idsx = this.request.getParameterValues("idsx");
		myLogger.info("idsx :"+idsx);
		myLogger.info("ids temp :"+getParam("ids"));
		myLogger.info("per_mati :"+per_mati);
		myLogger.info("bolehsimpan :"+bolehsimpan);
		
		//check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";	
		String[] ids_check = this.request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"+ids_check[ii];	
				String jenis_perintah_check = getParam(temp_jenis_perintah_check);
				if(jenis_perintah_check.equals("2"))
				{
				check_pkt++;
				}
			}
		}
		//tutup
		//String temp = null;	
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
					if (bolehsimpan.equals("yes")) {
									
						long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
						
						String flag_daftar = "flag_daftar"+ids[i];
						
						myLogger.info("*************************GET RADIO VALUE :"+getParam(flag_daftar));
						logicPendaftaran.pilihanAdd(per_mati,ids[i],user_id,id_Pilihanhta,getParam(flag_daftar));								
						String temp_id_ob = "main_ob"+ids[i];								
						String[] ids_ob = this.request.getParameterValues(temp_id_ob);		
						//buka
						String temp_jenis_perintah = "jenis_perintah_harta"+ids[i];	
						String jenis_perintah = getParam(temp_jenis_perintah);					
						if(jenis_perintah.equals("2"))
						{
							check_pkt_true++;						
						}
						
						//tutup
						
						if (ids_ob != null) {
							for (int y = 0; y < ids_ob.length; y++) {
								
								
								
								String temp_id_ob_sub = "check_ob"+ids[i]+ids_ob[y];
								String status_tb_id = "status_tb"+ids[i]+ids_ob[y];
								String status_tadbir = getParam(status_tb_id);
								
								String[] ids_ob_sub = this.request.getParameterValues(temp_id_ob_sub);							
								String ids_ob_sub1 = "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = "";
								if (ids_ob_sub != null) {
									
									for (int x = 0; x < ids_ob_sub.length; x++) {
										
										if(x==0)
										{
											ids_ob_sub1 = ids_ob_sub[x];
										}
										if(x==1)
										{
											ids_ob_sub2 = ids_ob_sub[x];
										}
										if(x==2)
										{
											ids_ob_sub3 = ids_ob_sub[x];
										}
										if(x==3)
										{
											ids_ob_sub4 = ids_ob_sub[x];
										}
										
									}
								}
								
								
								String flag_daftar_ob = "flag_daftar_ob"+ids[i]+ids_ob[y];
														
								myLogger.info("*************************GET RADIO NAMA RADIO :"+flag_daftar_ob);
								myLogger.info("*************************GET RADIO VALUE OB :"+getParam(flag_daftar_ob));
								String id_obdtl = "";
								id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								
								logicPendaftaran.pilihanAddOBHTA(jenis_perintah,per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,ids_ob_sub3,ids_ob_sub4,(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta+"",getParam(flag_daftar_ob),getParam(id_obdtl));	
									
								String bahagian_waris_atas = "";
								String bahagian_waris_bawah = "";
								
								long id_perintahhtaobmst = 0;		  
								if(getParam(flag_daftar_ob).equals("2") && !jenis_perintah.equals("2"))
								{								
								id_perintahhtaobmst = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");							
								bahagian_waris_atas = "bahagian_waris_atas"+ids[i]+ids_ob[y];
								bahagian_waris_bawah = "bahagian_waris_bawah"+ids[i]+ids_ob[y];
								//String id_waris_batal = "id_waris_batal"+ids[i]+ids_ob[y];	
								//id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								logicPendaftaran.pilihanBatalDanLantik("HTA",id_perintahhtaobmst+"","1",idPerintah,per_mati,ids[i],ids_ob[y],"B","BL",getParam(bahagian_waris_atas),getParam(bahagian_waris_bawah), (String)session.getAttribute("_ekptg_user_id"),status_tadbir,getParam(id_obdtl));
								}
								
								if(getParam(flag_daftar_ob).equals("1") && !jenis_perintah.equals("2"))
								{								
								id_perintahhtaobmst = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");							
								bahagian_waris_atas = "bahagian_waris_atas"+ids[i]+ids_ob[y];
								bahagian_waris_bawah = "bahagian_waris_bawah"+ids[i]+ids_ob[y];
								//String id_waris_batal = "id_waris_batal"+ids[i]+ids_ob[y];
								id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								logicPendaftaran.pilihanBatalDanLantik("HTA",id_perintahhtaobmst+"","1",idPerintah,per_mati,ids[i],ids_ob[y],"B","BB",getParam(bahagian_waris_atas),getParam(bahagian_waris_bawah), (String)session.getAttribute("_ekptg_user_id"),status_tadbir,getParam(id_obdtl));
								
								
								}
								
								
							}
						}							
					}				
					
			}
		}
		//buka
		myLogger.info("check_pkt :"+check_pkt);
		myLogger.info("check_pkt_true :"+check_pkt_true);
		
		if(check_pkt>0 && check_pkt_true == 0)
		{
			flag_perlu_batal_kt = "unbatalkan";
		}
		else if(check_pkt>0 && check_pkt_true>0)
		{
			flag_perlu_batal_kt = "batalkan";					
		}
		myLogger.info("flag_perlu_batal_kt:"+flag_perlu_batal_kt);


		if(!flag_perlu_batal_kt.equals(""))
		{
			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");
			Vector<Hashtable<String,String>> listHartaTakAlihdulu = new Vector<Hashtable<String,String>>();
			listHartaTakAlihdulu = logicPendaftaran2.setDataSemuaHartaTakAlihdulu(per_mati, bkp, lp, bpa, lpa);
			for(int i=0; i < listHartaTakAlihdulu.size(); i++)
			{
				Hashtable<String,String> h = (Hashtable<String,String>) listHartaTakAlihdulu.get(i);
				myLogger.info("GET ID HTA :"+h.get("idhta").toString());
				logicPendaftaran.clear_pilihanHTAMainDelete_byId(h.get("idhta").toString(),per_mati,"");
				if(flag_perlu_batal_kt.equals("batalkan"))
				{
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logicPendaftaran.pilihanAdd(per_mati,h.get("idhta").toString(),user_id,id_Pilihanhta,"");	
					Vector<Hashtable<String,String>> listHartaTakAlihduluOB = new Vector<Hashtable<String,String>>();
					//logicPendaftaran2.setDataOBHTAdulu(per_mati,bkp,lp,bpa,lpa,""); 
					//listHartaTakAlihduluOB = logicPendaftaran2.getDataOBHTAdulu();
					listHartaTakAlihduluOB = logic.setDataOBHTAdulu(idPerintah,idPermohonanSimati,bkp, lp, bpa, lpa,"");
					for(int ix=0; ix < listHartaTakAlihduluOB.size(); ix++)
					{
						Hashtable<String,String> hx = (Hashtable<String,String>) listHartaTakAlihduluOB.get(ix);
						myLogger.info("GET ID HTA OB :"+hx.get("ID_HTA").toString());				
						if(h.get("idhta").toString().equals(hx.get("ID_HTA").toString()))
						{
						myLogger.info("GET ID OB OB :"+hx.get("ID_OB").toString());
						logicPendaftaran.pilihanAddOBHTA("",per_mati,hx.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta+"","",hx.get("ID_PERINTAHHTAOBDTL").toString());	
						}					
					}
				}
			}
			
			Vector<Hashtable<String,String>> listHartaAlihdulu = new Vector<Hashtable<String,String>>();
			listHartaAlihdulu = logicPendaftaran2.setDataSemuaHartaAlihdulu( per_mati, bkp, lp, bpa, lpa);
			for(int i=0; i < listHartaAlihdulu.size(); i++)
			{
				Hashtable<String,String> h = (Hashtable<String,String>) listHartaAlihdulu.get(i);
				myLogger.info("GET ID HA :"+h.get("id_Ha").toString());
				logicPendaftaran.clear_pilihanHAMainDelete_byId(h.get("id_Ha").toString(),per_mati,"");
				
				if(flag_perlu_batal_kt.equals("batalkan"))
				{
					myLogger.info("********************************* HA flag_perlu_batal_kt ");	
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logicPendaftaran.pilihanAdd_HA(per_mati,h.get("id_Ha").toString(),user_id,id_Pilihanha,"");	
					
					Vector<Hashtable<String,String>> listHartaAlihduluOB = new Vector<Hashtable<String,String>>();
					//logicPendaftaran2.setDataOBHAdulu(per_mati,bkp,lp,bpa,lpa,""); 
					//listHartaAlihduluOB = logicPendaftaran2.getDataOBHAdulu();
					listHartaAlihduluOB = logic.setDataOBHAdulu(idPerintah,idPermohonanSimati,bkp, lp, bpa, lpa,"");
					
					for(int iy=0; iy < listHartaAlihduluOB.size(); iy++)
					{
						Hashtable<String,String> hy = (Hashtable<String,String>) listHartaAlihduluOB.get(iy);
						myLogger.info("GET ID HA OB :"+hy.get("ID_HA").toString());				
						if(h.get("id_Ha").toString().equals(hy.get("ID_HA").toString()))
						{
						myLogger.info("********************************* HA GET ID OB :"+hy.get("ID_OB").toString());
						logicPendaftaran.pilihanAddOBHA("",per_mati,hy.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanha+"","",hy.get("ID_PERINTAHHAOBDTL").toString());	
						}					
					}
				}
			}
			
			
			
			
		}	
		
		
	}

/*
	private void simpanpilihanHTAX(HttpSession session,String idPermohonanSimati) throws Exception
	{
		String per_mati = idPermohonanSimati;
		String bolehsimpan = "yes";
			String user_id = (String) session
					.getAttribute("_ekptg_user_id");
			logicPendaftaran.clear_pilihanHTAMainDelete(per_mati,"T");
			myLogger.info("NAK SIMPAN PILIHAN OB");
			String[] ids = this.request.getParameterValues("ids");
			myLogger.info("ids :"+ids);
			
			//check ada x perintah kuasa tadbir
			int check_pkt = 0;
			int check_pkt_true = 0;
			String flag_perlu_batal_kt = "";	
			String[] ids_check = this.request.getParameterValues("ids_check");
			if (ids_check != null) {
				for (int ii = 0; ii < ids_check.length; ii++) {
					String temp_jenis_perintah_check = "jenis_perintah_harta"+ids_check[ii];	
					String jenis_perintah_check = getParam(temp_jenis_perintah_check);
					if(jenis_perintah_check.equals("2"))
					{
					check_pkt++;
					}
				}
			}
			//tutup
			
			
			String temp = null;				
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
						if (bolehsimpan.equals("yes")) {
							//logicPendaftaran.clear_pilihanHTADelete(per_mati,ids[i],(String)session.getAttribute("_ekptg_user_id"));				
							long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
							String flag_daftar = "flag_daftar"+ids[i];
							logicPendaftaran.pilihanAdd(per_mati,ids[i],user_id,id_Pilihanhta,getParam(flag_daftar));								
							String temp_id_ob = "main_ob"+ids[i];
							String[] ids_ob = this.request.getParameterValues(temp_id_ob);
							myLogger.info("temp_id_ob :"+temp_id_ob);
							myLogger.info("main_ob :"+ids_ob);
							
							//buka
							String temp_jenis_perintah = "jenis_perintah_harta"+ids[i];	
							String jenis_perintah = getParam(temp_jenis_perintah);					
							if(jenis_perintah.equals("2"))
							{
								check_pkt_true++;						
							}
							//tutup
							
							
							if (ids_ob != null) {
								for (int y = 0; y < ids_ob.length; y++) {	
									String temp_id_ob_sub = "check_ob"+ids[i]+ids_ob[y];
									String[] ids_ob_sub = this.request.getParameterValues(temp_id_ob_sub);							
									String ids_ob_sub1 = "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = "";
									if (ids_ob_sub != null) {
										
										for (int x = 0; x < ids_ob_sub.length; x++) {
											
											if(x==0)
											{
												ids_ob_sub1 = ids_ob_sub[x];
											}
											if(x==1)
											{
												ids_ob_sub2 = ids_ob_sub[x];
											}
											if(x==2)
											{
												ids_ob_sub3 = ids_ob_sub[x];
											}
											if(x==3)
											{
												ids_ob_sub4 = ids_ob_sub[x];
											}
											
										}
									}										
									logicPendaftaran.pilihanAddOBHTAX(per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,ids_ob_sub3,ids_ob_sub4,(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta+"");	
								}
							}							
						}
				}
			}
			
			
			//buka
			myLogger.info("check_pkt :"+check_pkt);
			myLogger.info("check_pkt_true :"+check_pkt_true);
			
			if(check_pkt>0 && check_pkt_true == 0)
			{
				flag_perlu_batal_kt = "unbatalkan";
			}
			else if(check_pkt>0 && check_pkt_true>0)
			{
				flag_perlu_batal_kt = "batalkan";					
			}
			myLogger.info("flag_perlu_batal_kt:"+flag_perlu_batal_kt);

			if(!flag_perlu_batal_kt.equals(""))
			{
				String bkp = getParam("bkp");
				String lp = getParam("lp");
				String bpa = getParam("bpa");
				String lpa = getParam("lpa");
				Vector listHartaTakAlihdulu = new Vector();
				listHartaTakAlihdulu = logicPendaftaran2.setDataSemuaHartaTakAlihdulu( per_mati, bkp, lp, bpa, lpa);
				for(int i=0; i < listHartaTakAlihdulu.size(); i++)
				{
					Hashtable h = (Hashtable) listHartaTakAlihdulu.get(i);
					myLogger.info("GET ID HTA :"+h.get("idhta").toString());
					logicPendaftaran.clear_pilihanHTAMainDelete_byId(h.get("idhta").toString(),per_mati,"");
					
					if(flag_perlu_batal_kt.equals("batalkan"))
					{
						long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
						logicPendaftaran.pilihanAdd(per_mati,h.get("idhta").toString(),user_id,id_Pilihanhta,"");	
						
						Vector listHartaTakAlihduluOB = new Vector();
						logicPendaftaran2.setDataOBHTAdulu(per_mati,bkp,lp,bpa,lpa,""); 
						listHartaTakAlihduluOB = logicPendaftaran2.getDataOBHTAdulu();
						for(int ix=0; ix < listHartaTakAlihduluOB.size(); ix++)
						{
							Hashtable hx = (Hashtable) listHartaTakAlihduluOB.get(ix);
							myLogger.info("GET ID HTA OB :"+hx.get("ID_HTA").toString());				
							if(h.get("idhta").toString().equals(hx.get("ID_HTA").toString()))
							{
							myLogger.info("GET ID OB OB :"+hx.get("ID_OB").toString());
							logicPendaftaran.pilihanAddOBHTA("",per_mati,hx.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta+"","",hx.get("ID_PERINTAHHTAOBDTL").toString());	
							}					
						}
					}
				}
				
				Vector listHartaAlihdulu = new Vector();
				listHartaAlihdulu = logicPendaftaran2.setDataSemuaHartaAlihdulu( per_mati, bkp, lp, bpa, lpa);
				for(int i=0; i < listHartaAlihdulu.size(); i++)
				{
					Hashtable h = (Hashtable) listHartaAlihdulu.get(i);
					myLogger.info("GET ID HA :"+h.get("id_Ha").toString());
					logicPendaftaran.clear_pilihanHAMainDelete_byId(h.get("id_Ha").toString(),per_mati,"");
					
					if(flag_perlu_batal_kt.equals("batalkan"))
					{
						long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
						logicPendaftaran.pilihanAdd_HA(per_mati,h.get("id_Ha").toString(),user_id,id_Pilihanha,"");	
						
						Vector listHartaAlihduluOB = new Vector();
						logicPendaftaran2.setDataOBHAdulu(per_mati,bkp,lp,bpa,lpa,""); 
						listHartaAlihduluOB = logicPendaftaran2.getDataOBHAdulu();
						for(int iy=0; iy < listHartaAlihduluOB.size(); iy++)
						{
							Hashtable hy = (Hashtable) listHartaAlihduluOB.get(iy);
							myLogger.info("GET ID HA OB :"+hy.get("ID_HA").toString());				
							if(h.get("id_Ha").toString().equals(hy.get("ID_HA").toString()))
							{
							myLogger.info("GET ID OB OB :"+hy.get("ID_OB").toString());
							logicPendaftaran.pilihanAddOBHA("",per_mati,hy.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanha+"","",hy.get("ID_PERINTAHHAOBDTL").toString());	
							}					
						}
					}
				}
				
				
				
				
			}	
			
	}*/

	private void simpanpilihanHA(HttpSession session,String idPermohonanSimati,String idPerintah) throws Exception
	{
		String per_mati = idPermohonanSimati;
		String bolehsimpan = "yes";
		String user_id = (String) session
				.getAttribute("_ekptg_user_id");
		logicPendaftaran.clear_pilihanHAMainDelete(per_mati,"");
		//logicPendaftaran.clear_pilihanHTAMainDelete(per_mati,"Y");
		myLogger.info("NAK SIMPAN PILIHAN OB");
		String[] ids = this.request.getParameterValues("ids");
		myLogger.info("ids :"+ids);
		
		
		//check ada x perintah kuasa tadbir
		int check_pkt = 0;
		int check_pkt_true = 0;
		String flag_perlu_batal_kt = "";	
		String[] ids_check = this.request.getParameterValues("ids_check");
		if (ids_check != null) {
			for (int ii = 0; ii < ids_check.length; ii++) {
				String temp_jenis_perintah_check = "jenis_perintah_harta"+ids_check[ii];	
				String jenis_perintah_check = getParam(temp_jenis_perintah_check);
				if(jenis_perintah_check.equals("2"))
				{
				check_pkt++;
				}
			}
		}
		//tutup
		//String temp = null;				
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
					if (bolehsimpan.equals("yes")) {
						long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
						String flag_daftar = "flag_daftar"+ids[i];
						logicPendaftaran.pilihanAdd_HA(per_mati,ids[i],user_id,id_Pilihanha,getParam(flag_daftar));								
						String temp_id_ob = "main_ob"+ids[i];
						String[] ids_ob = this.request.getParameterValues(temp_id_ob);
						myLogger.info("temp_id_ob :"+temp_id_ob);
						myLogger.info("ids_ob :"+ids_ob);
						
						//buka
						String temp_jenis_perintah = "jenis_perintah_harta"+ids[i];	
						String jenis_perintah = getParam(temp_jenis_perintah);					
						if(jenis_perintah.equals("2"))
						{
							check_pkt_true++;						
						}
						//tutup
						
						
						if (ids_ob != null) {
							for (int y = 0; y < ids_ob.length; y++) {
								String temp_id_ob_sub = "check_ob"+ids[i]+ids_ob[y];
								String status_tb_id = "status_tb"+ids[i]+ids_ob[y];
								String status_tadbir = getParam(status_tb_id);
								
								String[] ids_ob_sub = this.request.getParameterValues(temp_id_ob_sub);							
								String ids_ob_sub1 = "",ids_ob_sub2 = "",ids_ob_sub3 = "",ids_ob_sub4 = "";
								if (ids_ob_sub != null) {
									
									for (int x = 0; x < ids_ob_sub.length; x++) {
										
										if(x==0)
										{
											ids_ob_sub1 = ids_ob_sub[x];
										}
										if(x==1)
										{
											ids_ob_sub2 = ids_ob_sub[x];
										}
										if(x==2)
										{
											ids_ob_sub3 = ids_ob_sub[x];
										}
										if(x==3)
										{
											ids_ob_sub4 = ids_ob_sub[x];
										}
										
									}
								}
								if(!ids_ob_sub1.equals("") || !ids_ob_sub2.equals("") || !ids_ob_sub3.equals("") || !ids_ob_sub4.equals(""))
								{
								}
								String id_obdtl = "";
								id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								String flag_daftar_ob = "flag_daftar_ob"+ids[i]+ids_ob[y];
								logicPendaftaran.pilihanAddOBHA("",per_mati,ids_ob[y],ids_ob_sub1,ids_ob_sub2,ids_ob_sub3,ids_ob_sub4,(String)session.getAttribute("_ekptg_user_id"),id_Pilihanha+"",getParam(flag_daftar_ob),getParam(id_obdtl));	
								
								String bahagian_waris_atas = "";
								String bahagian_waris_bawah = "";
								
								long id_perintahhaobmst = 0;		  
								if(getParam(flag_daftar_ob).equals("2") && !jenis_perintah.equals("2"))
								{
								myLogger.info("------------------Buka Batal dan Lantik");
								myLogger.info("----------------------------------------OBMST");
								id_perintahhaobmst = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");								
								myLogger.info("id_perintah :"+idPerintah);
								myLogger.info("id_permohonansimati :"+per_mati);
								myLogger.info("id_perintahhaobmst :"+id_perintahhaobmst);
								myLogger.info("id_ha :"+ids[i]);
								myLogger.info("flag_harta :"+"B");
								myLogger.info("flag_batal :"+"BL");
								bahagian_waris_atas = "bahagian_waris_atas"+ids[i]+ids_ob[y];
								bahagian_waris_bawah = "bahagian_waris_bawah"+ids[i]+ids_ob[y];
								String id_waris_batal = "id_waris_batal"+ids[i]+ids_ob[y];
								id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								myLogger.info("bahagian_waris_atas :"+getParam(bahagian_waris_atas));
								myLogger.info("bahagian_waris_bawah :"+getParam(bahagian_waris_bawah));
								myLogger.info("id_waris_batal :"+getParam(id_waris_batal));	
								myLogger.info("----------------------------------------");
								myLogger.info("----------------------------------------OBDTL");								
								myLogger.info("----------------------------------------");
								myLogger.info("------------------Tutup Batal dan Lantik");
								logicPendaftaran.pilihanBatalDanLantik("HA",id_perintahhaobmst+"","1",idPerintah,per_mati,ids[i],ids_ob[y],"B","BL",getParam(bahagian_waris_atas),getParam(bahagian_waris_bawah), (String)session.getAttribute("_ekptg_user_id"),status_tadbir,getParam(id_obdtl));
								}
								if(getParam(flag_daftar_ob).equals("1") && !jenis_perintah.equals("2"))
								{								
								id_perintahhaobmst = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");							
								bahagian_waris_atas = "bahagian_waris_atas"+ids[i]+ids_ob[y];
								bahagian_waris_bawah = "bahagian_waris_bawah"+ids[i]+ids_ob[y];
								//String id_waris_batal = "id_waris_batal"+ids[i]+ids_ob[y];	
								id_obdtl = "id_obdtl"+ids[i]+ids_ob[y];
								logicPendaftaran.pilihanBatalDanLantik("HA",id_perintahhaobmst+"","1",idPerintah,per_mati,ids[i],ids_ob[y],"B","BB",getParam(bahagian_waris_atas),getParam(bahagian_waris_bawah), (String)session.getAttribute("_ekptg_user_id"),status_tadbir,getParam(id_obdtl));
								}
							}
						}							
					}
			}
		}
		
		//buka
		myLogger.info("check_pkt :"+check_pkt);
		myLogger.info("check_pkt_true :"+check_pkt_true);
		
		if(check_pkt>0 && check_pkt_true == 0)
		{
			flag_perlu_batal_kt = "unbatalkan";
		}
		else if(check_pkt>0 && check_pkt_true>0)
		{
			flag_perlu_batal_kt = "batalkan";					
		}
		myLogger.info("flag_perlu_batal_kt:"+flag_perlu_batal_kt);

		if(!flag_perlu_batal_kt.equals(""))
		{
			String bkp = getParam("bkp");
			String lp = getParam("lp");
			String bpa = getParam("bpa");
			String lpa = getParam("lpa");
			Vector<Hashtable<String,String>> listHartaTakAlihdulu = new Vector<Hashtable<String,String>>();
			listHartaTakAlihdulu = logicPendaftaran2.setDataSemuaHartaTakAlihdulu( per_mati, bkp, lp, bpa, lpa);
			for(int i=0; i < listHartaTakAlihdulu.size(); i++)
			{
				Hashtable<String,String> h = (Hashtable<String,String>) listHartaTakAlihdulu.get(i);
				myLogger.info("GET ID HTA :"+h.get("idhta").toString());
				logicPendaftaran.clear_pilihanHTAMainDelete_byId(h.get("idhta").toString(),per_mati,"");
				
				if(flag_perlu_batal_kt.equals("batalkan"))
				{
					long id_Pilihanhta = DB.getNextID("TBLPPKPILIHANHTA_SEQ");
					logicPendaftaran.pilihanAdd(per_mati,h.get("idhta").toString(),user_id,id_Pilihanhta,"");	
					
					Vector<Hashtable<String,String>> listHartaTakAlihduluOB = new Vector<Hashtable<String,String>>();
					//logicPendaftaran2.setDataOBHTAdulu(per_mati,bkp,lp,bpa,lpa,""); 
					//listHartaTakAlihduluOB = logicPendaftaran2.getDataOBHTAdulu();
					listHartaTakAlihduluOB = logic.setDataOBHTAdulu(idPerintah,idPermohonanSimati,bkp, lp, bpa, lpa,"");
									
					for(int ix=0; ix < listHartaTakAlihduluOB.size(); ix++)
					{
						Hashtable<String,String> hx = (Hashtable<String,String>) listHartaTakAlihduluOB.get(ix);
						myLogger.info("GET ID HTA OB :"+hx.get("ID_HTA").toString());				
						if(h.get("idhta").toString().equals(hx.get("ID_HTA").toString()))
						{
						myLogger.info("GET ID OB OB :"+hx.get("ID_OB").toString());
						logicPendaftaran.pilihanAddOBHTA("",per_mati,hx.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanhta+"","",hx.get("ID_PERINTAHHTAOBDTL").toString());	
						}					
					}
				}
			}
			
			Vector<Hashtable<String,String>> listHartaAlihdulu = new Vector<Hashtable<String,String>>();
			listHartaAlihdulu = logicPendaftaran2.setDataSemuaHartaAlihdulu( per_mati, bkp, lp, bpa, lpa);
			for(int i=0; i < listHartaAlihdulu.size(); i++)
			{
				Hashtable<String,String> h = (Hashtable<String,String>) listHartaAlihdulu.get(i);
				myLogger.info("GET ID HA :"+h.get("id_Ha").toString());
				logicPendaftaran.clear_pilihanHAMainDelete_byId(h.get("id_Ha").toString(),per_mati,"");
				
				if(flag_perlu_batal_kt.equals("batalkan"))
				{
					long id_Pilihanha = DB.getNextID("TBLPPKPILIHANHA_SEQ");
					logicPendaftaran.pilihanAdd_HA(per_mati,h.get("id_Ha").toString(),user_id,id_Pilihanha,"");	
					
					Vector<Hashtable<String,String>> listHartaAlihduluOB = new Vector<Hashtable<String,String>>();
					//logicPendaftaran2.setDataOBHAdulu(per_mati,bkp,lp,bpa,lpa,""); 
					//listHartaAlihduluOB = logicPendaftaran2.getDataOBHAdulu();
					listHartaAlihduluOB = logic.setDataOBHAdulu(idPerintah,idPermohonanSimati,bkp, lp, bpa, lpa,"");
					
					for(int iy=0; iy < listHartaAlihduluOB.size(); iy++)
					{
						Hashtable<String,String> hy = (Hashtable<String,String>) listHartaAlihduluOB.get(iy);
						myLogger.info("GET ID HA OB :"+hy.get("ID_HA").toString());				
						if(h.get("id_Ha").toString().equals(hy.get("ID_HA").toString()))
						{
						myLogger.info("GET ID OB OB :"+hy.get("ID_OB").toString());
						logicPendaftaran.pilihanAddOBHA("",per_mati,hy.get("ID_OB").toString(),"","","","",(String)session.getAttribute("_ekptg_user_id"),id_Pilihanha+"","",hy.get("ID_PERINTAHHAOBDTL").toString());	
						}					
					}
				}
			}			
			
		}	
		//tutup    
    }
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector<Hashtable<String,String>> list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	
	private IMaklumatPerintah getPerintah(){
		if(iPerintah == null)
			iPerintah = new FrmPerintah17Bean();
		return iPerintah;
	}
	
	
}