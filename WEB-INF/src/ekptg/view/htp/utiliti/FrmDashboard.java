
package ekptg.view.htp.utiliti;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.HTPeringatanBean;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.IHTPeringatan;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.CukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.ICukaiPenyata;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.rekod.HTPStatusRekodBean;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.utils.status.IStatus;
import ekptg.model.utils.status.StatusBean;
import ekptg.view.admin.Pengumuman;

public class FrmDashboard extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3455784952255888226L;
	private final String PATHBASE="app/";
	private final String PATH=PATHBASE+"htp/utiliti/";
	private final String PATHVER=PATHBASE+"htp/6.0/utiliti/";
	private final String IDURUSANCUKAI = "11";
	private final String IDURUSANPAJAKANKECIL = "309";
	private final String IDSUBURUSANREKOD = "61";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.utiliti.FrmDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logic = new Pengumuman();	
	private Hashtable<String,String> mBayaran = null;
	private ICukai iCukai = null;
	private ICukaiPenyata iCukaiPenyata = null;
	private IHtp iErr = null;
	private IHTPPermohonan iHTPPermohonan = null;
	private IHTPStatus iStatus = null;
	private IStatus iStatusOnline = null;
	private IOnline iOnline = null;
	private Vector<HtpPermohonan> vecSenaraiOnline = null;
	private Db db = null;
	private Connection conn = null;
	private String sql = "";
	//private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List listFail = null;
	List listHakmilik = null;
	List listKemaskiniCukai = null;
	List listCukai = null;
	Vector pendaftaranPPT = null; 

	private IHTPeringatan iHTPP = null;  
	SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfBulan = new SimpleDateFormat("MM");

	@Override
	public String doTemplate2() throws Exception {	
			
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String portal_role = (String)session.getAttribute("myrole");
		context.put("portal_role_",portal_role);
		String command = getParam("command");
		myLog.info("command="+command);
		
		//Carian 
		if (command.equals("doCarianFail")) {
			return doCarianFail(session);
		}else if (command.equals("doCarianHakmilik")) {
			return doCarianHakmilik(session);
		}else if (command.equals("doCloseCarianFail")) {
			return doCloseCarianFail(session);
		}else if (command.equals("doCloseCarianHakmilik")) {
			return doCloseCarianHakmilik(session); 
		}else if (command.equals("getPPT")) {
    		pendaftaranPPT = FrmPermohonanUPTData.getListPemohonSeksyen8("");
			return senaraiFailPPT(session); 
		}else if (command.equals("doGetListFail")) {
			return doGetListFail(session);
		} else if (command.equals("doCloseListFail")) {
			return doCloseListFail(session);
		}else if (command.equals("doGetListHakmilik")) {
			return doGetListHakmilik(session);
		}else if (command.equals("doCloseListHakmilik")) {
			return doCloseListHakmilik(session);
		}else if (command.equals("doGetListCukai")) {
			return doGetListCukai(session);
		}else if (command.equals("doCloseListCukai")) {
			return doCloseListCukai(session);
		}else if (command.equals("doReadCukai")) {
			
			String id_cukaitemp = getParam("id_cukaitemp");
			
			if(id_cukaitemp!=""){
				updateRead(session,id_cukaitemp);
			}
			
			this.context.put("div_getListCukai_open", "Y");
			listCukai = DBListKemaskiniCukai(session);
			this.context.put("listCukai", listCukai);
			return PATH+"/div_listCukai.jsp";
		
		}
		
				
		Hashtable get_stat = null;
		Integer check_notifikasi_aduan = 0;
		Integer check_notifikasi_online8 = 0;
		Integer check_notifikasi_online17 = 0;
		Integer check_notifikasi_pindah = 0;
		Integer check_notifikasi_inbox = 0;
		Vector list_memo_aktif = null;
		
		role = (String)session.getAttribute("myrole");
		userId = (String)session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
			
		get_stat = (Hashtable<String,String>) statFail();
		String negeriServer = (String)get_stat.get("NAMA_NEGERI_SERVER");
		String jumlahKeseluruhan = (String)get_stat.get("JUMLAH_KESELURUHAN");
		String fail01 = (String)get_stat.get("JUMLAH_PEMBERIMILIKAN");
		String fail02 = (String)get_stat.get("JUMLAH_PEMBELIAN");
		String fail03 = (String)get_stat.get("JUMLAH_PAJAKAN");
		String fail04 = (String)get_stat.get("JUMLAH_PENSWASTAAN");
		String fail05 = (String)get_stat.get("JUMLAH_PERLETAKHAKAN");
		String fail10 = (String)get_stat.get("JUMLAH_PERIZAPAN");
		String fail108 = (String)get_stat.get("JUMLAH_GADAIAN");
		String fail309 = (String)get_stat.get("JUMLAH_PKECIL");
		String failHapus = (String)get_stat.get("JUMLAH_FAIL_HAPUS");
		String jumlahHakmilik = (String)get_stat.get("JUMLAH_HAKMILIK");
		String jumlahRizab = (String)get_stat.get("JUMLAH_RIZAB");
		
		check_notifikasi_aduan = getListNotifikasi_main_list(role,user_negeri_login,"","",userId,"NO");
		context.put("check_notifikasi_aduan",check_notifikasi_aduan);	
		int bilPermohonanOnline = 0; 
//    	if(isTab(portal_role,"<i>Online</i>")){
//    		context.remove("senaraionline");
//    		
//    		vecSenaraiOnline = getIOnline().findFailOnlineAktif(getParam("txtTajukFail"), getParam("txtNoFail"), "", "");
//    		if(vecSenaraiOnline != null)
//    			bilPermohonanOnline = vecSenaraiOnline.size();
//    			    		
//    	}
//     	context.put("senaraionline", vecSenaraiOnline);  
   	
		
		//ONLINE - Submodul Permohonan
    	Vector<HtpPermohonan> onlinePermohonan = null; 
    	Vector<Hashtable<String, String>> onlinePermohonanPra = null; 
    	Vector<Hashtable<String, String>> onlinePermohonanPraRizab = null; 

    	if(isTab(portal_role,"Permohonan")){
    		context.remove("onlinePermohonan");
    		onlinePermohonan = getIOnline().findFailOnlineUrusan("","","","","1,10");
    		if(onlinePermohonan != null)
    			bilPermohonanOnline += onlinePermohonan.size();
    		
    		onlinePermohonanPra =  getStatusOnlinePra("1");
    		if(onlinePermohonanPra != null)
    			bilPermohonanOnline += onlinePermohonanPra.size();

    		onlinePermohonanPraRizab =  getStatusOnlinePra("10");
    		if(onlinePermohonanPraRizab != null)
    			bilPermohonanOnline += onlinePermohonanPraRizab.size();

    		pendaftaranPPT = FrmPermohonanUPTData.getListPemohonSeksyen8("");
//    		pendaftaranPPT.isEmpty()
    		
    	}
    	context.put("senaraiPermohonanPPT", pendaftaranPPT);  

    	if(onlinePermohonanPra !=null & onlinePermohonan != null)
    		context.put("onlinePermohonanPra", onlinePermohonanPra.size() + onlinePermohonanPraRizab.size());  

    	context.put("onlinePermohonan", onlinePermohonan);  
    	
    	Vector<HtpPermohonan> onlinePembelian = null; 
		if(isTab(portal_role,"Pembelian")){
    		context.remove("onlinePembelian");
    		onlinePembelian = getIOnline().findFailOnlineUrusan("","","","","2");
    		if(onlinePembelian != null)
    			bilPermohonanOnline += onlinePembelian.size();    			
    		
    	}
    	context.put("onlinePembelian", onlinePembelian);
    	
    	Vector<HtpPermohonan> onlineGadaian = null; 
		if(isTab(portal_role,"Gadaian")){
    		context.remove("onlineGadaian");
    		onlineGadaian = getIOnline().findFailOnlineUrusan("","","","","108");
    		if(onlineGadaian != null)
    			bilPermohonanOnline += onlineGadaian.size();    			
    		
    	}
    	context.put("onlineGadaian", onlineGadaian);

    	Vector<HtpPermohonan> onlineJRP = null; 
		if(isTab(portal_role,"JRP")){
    		context.remove("onlineJRP");
    		onlineJRP = getIOnline().findFailOnlineUrusan("","","","","14");
    		if(onlineJRP != null)
    			bilPermohonanOnline += onlineJRP.size();    			
    		
    	}
    	context.put("onlineJRP", onlineJRP);
    	
    	context.put("bilPermohonanOnline", bilPermohonanOnline); 
    	
//		check_notifikasi_online8 =  getListNotifikasi_online8(userId,"8");
//		context.put("check_notifikasi_online8",check_notifikasi_online8);		
//		check_notifikasi_online17 =  getListNotifikasi_online8(userId,"17");
//		context.put("check_notifikasi_online17",check_notifikasi_online17);
//		check_notifikasi_pindah =  getListNotifikasi_pindah(userId);
//		context.put("check_notifikasi_pindah",check_notifikasi_pindah);	
		check_notifikasi_inbox =  getListNotifikasi_inbox(userId);
		context.put("check_notifikasi_inbox",check_notifikasi_inbox);
		
		context.put("negeriSever", negeriServer);
		context.put("jumlahKeseluruhan", jumlahKeseluruhan);
		context.put("failPemberimilikan", fail01);
		context.put("failPembelian", fail02);
		context.put("failPajakan", fail03);
		context.put("failPenswastaan", fail04);
		context.put("failPerletakhakan", fail05);
		context.put("failPerizapan", fail10);
		context.put("failGadaian", fail108);
		context.put("failPKecil", fail309);
		context.put("failHapus", failHapus);
		context.put("jumlahHakmilik", jumlahHakmilik);
		context.put("jumlahRizab", jumlahRizab);
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);		
		//
		String langkahCukaiHantar = "5";
		String langkahPerakuan = "4";
		String langkahRekod = "24";

		if(portal_role.contains("HQPengguna")){
  			langkahCukaiHantar = "5";
 			langkahRekod = "24";
   			langkahPerakuan = "1";

		}else if(portal_role.contains("HQPegawai")){
			langkahCukaiHantar = "6";
   			langkahPerakuan = "4";
   			langkahRekod = "25";

		}else if(portal_role.contains("HQPengarah")){
			langkahCukaiHantar = "7";
   			langkahPerakuan = "5";
   			langkahRekod = "26";

		}

		int bilTugasan = 0; 
		//submodul Permohonan
		//PENAMBAHBAIKAN. SYAZ. 17112014. NOTIFIKASI BORANG 15A UTK UNIT REKOD DAN UNIT CUKAI
//		Integer bil_borang5ABaru = getBilBorang5ABaru(userId);
//		this.context.put("bil_borang5ABaru",bil_borang5ABaru);
//		
		Integer bil_borang15ABaru = getBilBorang15ABaru(userId);
		this.context.put("bil_borang15ABaru",bil_borang15ABaru);

		//submodul Akuan Pembelian
		myLog.info("langkahPerakuan="+langkahPerakuan);
		int bilTugasanPerakuan = 0; 
		Vector<Hashtable<String,String>> vecPerakuan = null;
		vecPerakuan = getHTPermohonan().getPermohonanAktifLangkah("2", langkahPerakuan); 

		if(vecPerakuan != null)
			bilTugasanPerakuan = vecPerakuan.size();

		myLog.info("bilTugasanPerakuan="+bilTugasanPerakuan);

		//submodul Pengurusan Cukai
		int bilTugasanCukaiPenyata = 0; 
    	if(isTab(portal_role,"Cukai")){
    	 	String socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");

    		context.remove("cukaiSenaraiPenyata");
    		Vector vecCukaiSenarai = getICukai().getSenaraiNegeriXPenyata(socTahun);
    		String idNegeris = "";
      		for (int i = 0; i < vecCukaiSenarai.size(); i++) {
      			Hashtable hTemp = (Hashtable)vecCukaiSenarai.elementAt(i);
      			if (i==0) {
      				idNegeris = String.valueOf(hTemp.get("idNegeri"));
				} else {
					idNegeris += ","+String.valueOf(hTemp.get("idNegeri"));
				}
      		}
      	    Vector vecSenaraiFailXPenyata = getICukaiPenyata().getSenaraiFailXPenyata(IDURUSANCUKAI, "", "", idNegeris); 
	    	if( vecSenaraiFailXPenyata != null)
	    		bilTugasanCukaiPenyata = vecSenaraiFailXPenyata.size();
	    		
	    	context.put("vecSenaraiFailXPenyata", vecSenaraiFailXPenyata);  

    	}
    	//this.context.put("cukaiSenaraiPenyata", vecCukaiSenarai);
		int bilTugasanCukaiHantar = 0; 
		Vector vecCukaiHantar = null;
 		vecCukaiHantar = getHTPermohonan().getPermohonanAktifLangkah(IDURUSANCUKAI, langkahCukaiHantar); 
		myLog.info("bilTugasan="+vecCukaiHantar);
  		if(vecCukaiHantar != null)
  			bilTugasanCukaiHantar = vecCukaiHantar.size();
		
  		context.put("bilTugasanCukaiHantar",bilTugasanCukaiHantar);
		context.put("bilTugasanCukaiHantar",String.valueOf(bilTugasanCukaiHantar));    	
    	
		//PENAMBAHBAIKAN. SYAZ. 25112014. NOTIFIKASI KEMASKINI CUKAI
		listKemaskiniCukai = DBListKemaskiniCukai(session);
		this.context.put("listKemaskiniCukai",listKemaskiniCukai);
		//System.out.println("listKemaskiniCukai : "+listKemaskiniCukai);
		
		
  	    int bilPajakanKecilInt = 0;
		String bilPajakanKecil = "0";
		Vector vecPajakanKecil = getHTPermohonan().getPermohonanAktifLangkah(IDURUSANPAJAKANKECIL, "82");
  		if(vecPajakanKecil != null)
  			bilPajakanKecil = String.valueOf(vecPajakanKecil.size());
  		
		context.put("bilPajakanKecil",bilPajakanKecil);
		
		//Rekod - Pembangunan 
		Vector vecMulaPembangunan = null;
		String langkahPembangunan = "20"; 

		vecMulaPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahPembangunan);
		context.put("bilPrePembangunan",vecMulaPembangunan);

		
		int bilTugasanRekod = 0; 
		Vector vecMaklumatPembangunan = null;
  		if(portal_role.contains("HQPengguna")){
  			vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod);
 
		}else if(portal_role.contains("HQPegawai")){
  			vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod);
 		
		}else if(portal_role.contains("HQPengarah")){
  			vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod);
 
		}
		myLog.info("bilTugasan="+vecMaklumatPembangunan);
  		if(vecMaklumatPembangunan != null)
  			bilTugasanRekod = vecMaklumatPembangunan.size();
		
  		context.put("bilTugasanRekodInt",bilTugasanRekod);
		context.put("bilTugasanRekod",String.valueOf(bilTugasanRekod));
		
		
		bilTugasan = bilTugasanCukaiHantar+bilTugasanCukaiPenyata+bilPajakanKecilInt+bilTugasanRekod+bilTugasanPerakuan;
		myLog.info("bilTugasan="+bilTugasan);
		context.put("bilTugasanInt",bilTugasan);
		context.put("bilTugasan",String.valueOf(bilTugasan));
		/*
		if(command.equals("changeTab")){
			myLogger.info("INDEX :"+getParam("tab_index"));
			context.put("tab_index",getParam("tab_index"));				
		}
		else		
		{
			context.put("tab_index",0);			
		}
		*/
				
		
		//System.out.println("portal_role "+portal_role);
		if(session.getAttribute("rbFile") == null){
			ResourceBundle rb = ResourceBundle.getBundle("file");
			session.setAttribute("rbFile", rb);
		
		}
		//Vector peringatanBayaran = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfTahun.format(new Date()));
		myLog.info("getBayaran()= "+getBayaran());

		this.context.put("perBayaranPaj",getBayaran());

		String vm = PATH+"dashboard.jsp";
		return vm;
		
	}
	
	private String doCarianFail(HttpSession session) throws Exception {
		myLog.info("doCarianFail : "+getParam("search"));
		this.context.put("div_carianFail_open", "Y");
		listFail = ListFail(session, "fail", getParam("search"));
		this.context.put("listFail", listFail);

		return PATH+"/div_carianFail.jsp";
	}
	
	private String doCarianHakmilik(HttpSession session) throws Exception {
		this.context.put("div_carianHakmilik_open", "Y");
		listHakmilik = ListHakmilik(session, "hakmilik", getParam("search"));
		this.context.put("listHakmilik", listHakmilik);

		return PATH+"/div_carianHakmilik.jsp";
	}
	
	private String doCarianPermohonanPra(HttpSession session) throws Exception {
		//myLog.info("doCarianFail : "+getParam("search"));
		//this.context.put("div_carianFail_open", "Y");
		listFail = ListFail(session, "fail", getParam("search"));
		this.context.put("listFail", listFail);

		return PATH+"/div_carianFail.jsp";
	}
	
	private String doCloseCarianFail(HttpSession session) throws Exception {
		this.context.put("div_carianFail_open", "N");
		return PATH+"/div_carianFail.jsp";
	}
	
	private String doCloseCarianHakmilik(HttpSession session) throws Exception {
		this.context.put("div_carianHakmilik_open", "N");
		return PATH+"/div_carianHakmilik.jsp";
	}
	
	private String senaraiFailPPT(HttpSession session) throws Exception {
		if (!getParam("div_getListFail_open").equals("Y")) {
			this.context.put("div_getListFail_open", "Y");
			Vector senaraiFail = pendaftaranPPT;
//			listFail = ListFail(session, "fail", getParam("search"));
			this.context.put("listFail", senaraiFail);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFail_open", "N");
		}
		return PATHVER+"div_senaraiFailPPT.jsp";
	}
	
	private String doGetListFail(HttpSession session) throws Exception {
		if (!getParam("div_getListFail_open").equals("Y")) {
			this.context.put("div_getListFail_open", "Y");
			listFail = ListFail(session, "fail", getParam("search"));
			this.context.put("listFail", listFail);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFail_open", "N");
		}
		return PATH+"/div_listFail.jsp";
	}
	
	private String doCloseListFail(HttpSession session) throws Exception {
		this.context.put("div_getListFail_open", "N");
		return PATH+"/div_listFail.jsp";
	}
	
	private String doGetListHakmilik(HttpSession session) throws Exception {
		if (!getParam("div_getListHakmilik_open").equals("Y")) {
			this.context.put("div_getListHakmilik_open", "Y");
			listHakmilik = ListHakmilik(session, "hakmilik", getParam("search"));
			this.context.put("listHakmilik", listHakmilik);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListHakmilik_open", "N");
		}
		return PATH+"/div_listHakmilik.jsp";
	}
	
	private String doCloseListHakmilik(HttpSession session) throws Exception {
		this.context.put("div_getListHakmilik_open", "N");
		return PATH+"/div_listHakmilik.jsp";
	}
	
	/**Cukai open */
	private String doGetListCukai(HttpSession session) throws Exception {
		if (!getParam("div_getListCukai_open").equals("Y")) {
			this.context.put("div_getListCukai_open", "Y");
			listCukai = DBListKemaskiniCukai(session);
			this.context.put("listCukai", listCukai);
		} else {
			this.context.put("div_getListCukai_open", "N");
		}
		return PATH+"/div_listCukai.jsp";
	}
	
	/**Cukai close */
	private String doCloseListCukai(HttpSession session) throws Exception {
		this.context.put("div_getListCukai_open", "N");
		return PATH+"/div_listCukai.jsp";
	}
	
	private List ListFail(HttpSession session, String type, String search)
		throws Exception {
		listFail = DBcarianFail(session, type, search);
		this.context.put("listFail", listFail);
		return listFail;
	}
	
	private List ListHakmilik(HttpSession session, String type, String search)
			throws Exception {
		listHakmilik = DBcarianFail(session, type, search);
		this.context.put("listHakmilik", listHakmilik);
		return listHakmilik;
	}
	
	public Hashtable<String,String> statFail() throws Exception {	
		//Hashtable<String,String> get_negeri = (Hashtable<String,String>) kod_negeri();
		//String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " " +
				" SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N " +
				" WHERE S.KOD_NEGERI = N.KOD_NEGERI " +
				" ) AS NAMA_NEGERI_SERVER "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN IN (1,10,5,4,3,2,108,309) "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') "+
				" ) FAIL_HTP "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 1 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PEMBERIMILIKAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 2 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PEMBELIAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 3 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PAJAKAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 4 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PENSWASTAAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 5 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PERLETAKHAKAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 10 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PERIZAPAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 108 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_GADAIAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 309 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999') " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PKECIL "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN IN (1,10,5,4,3,2,108,309) "+
				" 		AND (FI.ID_STATUS = '999') "+
				" ) FAIL_HAPUS "+
				" ,(SELECT COUNT(*) "+
				" FROM TBLHTPHAKMILIK D "+
				" WHERE NVL(D.NO_HAKMILIK,' ')<>' ' "+
				" AND D.STATUS_SAH IN ( "+
				" 		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH "+
				" 		WHERE AKTIF=1 "+
				" ) "+
				" ) AS JUMLAH_HAKMILIK "+
				" ,(SELECT COUNT(*) "+
				" FROM TBLHTPHAKMILIK D "+
				" WHERE NVL(D.NO_WARTA,' ')<>' ' "+
				" AND D.STATUS_SAH IN ( "+
				" 		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH "+
				" 		WHERE AKTIF=1 "+
				" ) "+
				" ) AS JUMLAH_RIZAB "+
				" FROM DUAL ";			
			//myLog.info(" STATISTIK :"+sql.toUpperCase());
				
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			while (rs.next()) {
				h.put("NAMA_NEGERI_SERVER", Utils.isNull(rs.getString("NAMA_NEGERI_SERVER")));
				h.put("JUMLAH_KESELURUHAN", Utils.isNull(rs.getString("FAIL_HTP")));
				h.put("JUMLAH_PEMBERIMILIKAN",  Utils.isNull(rs.getString("FAIL_PEMBERIMILIKAN")));
				h.put("JUMLAH_PEMBELIAN",  Utils.isNull(rs.getString("FAIL_PEMBELIAN")));
				h.put("JUMLAH_PAJAKAN",  Utils.isNull(rs.getString("FAIL_PAJAKAN")));
				h.put("JUMLAH_PENSWASTAAN",  Utils.isNull(rs.getString("FAIL_PENSWASTAAN")));
				h.put("JUMLAH_PERLETAKHAKAN",  Utils.isNull(rs.getString("FAIL_PERLETAKHAKAN")));
				h.put("JUMLAH_PERIZAPAN",  Utils.isNull(rs.getString("FAIL_PERIZAPAN")));
				h.put("JUMLAH_PKECIL",  Utils.isNull(rs.getString("FAIL_PKECIL")));
				h.put("JUMLAH_GADAIAN",  Utils.isNull(rs.getString("FAIL_GADAIAN")));
				h.put("JUMLAH_FAIL_HAPUS", rs.getString("FAIL_HAPUS"));
				h.put("JUMLAH_HAKMILIK", rs.getString("JUMLAH_HAKMILIK"));
				h.put("JUMLAH_RIZAB", rs.getString("JUMLAH_RIZAB"));
				//h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				//h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public Hashtable<String,String> kod_negeri() throws Exception {		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
			sql = "SELECT KOD_NEGERI FROM TBLLOOKUPSTATE S ";		
			//myLog.info(" KOD_NEGERI :"+sql.toUpperCase());				
			ResultSet rs = stmt.executeQuery(sql);		
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			while (rs.next()) {
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI"));
				}
				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Integer count_aduan = null;
	public Integer getListNotifikasi_main_list(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) as notification FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "+
			" TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "+
			" WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "+
			" AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "+
			" AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "+
			" AND A.USER_ID = U.USER_ID "+
			" AND X.ID_ESADUAN = A.ID_ESADUAN "+
			" AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "+
			" AND U.USER_ID = UI.USER_ID "+
			" AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "+
			" AND UI.ID_NEGERI = N.ID_NEGERI(+) "+
			" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "+
			" AND UI.ID_DAERAH = D.ID_DAERAH(+)" +
			" AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";
			
			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if(!id_esaduan.equals(""))
			{
			sql += " AND X.ID_ESADUAN = '"+id_esaduan+ "' ";
			}
			if(!id_negeri_user.equals("") && !role.equals("adminsuper_es"))
			{
			sql += " AND A.ID_NEGERI_PENGADU = '"+id_negeri_user+ "' ";
			}
			if(!user_terima.equals(""))
			{
			sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '"+user_terima+"' ";
			}
			if(!flag_notifikasi.equals(""))
			{
			sql += " AND X.FLAG_NOTIFIKASI = '"+flag_notifikasi+"'";
			}			
			if(!notread.equals(""))
			{
			sql += " AND X.FLAG_READ = '"+notread+"'";
			}
			
			//
			myLog.info("LIST NOTIFICATION DASHBOARD LIST:sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			
				count_aduan = rs.getInt("notification");
				
				}
			return count_aduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Integer count_8 = null;
	public Integer getListNotifikasi_online8(String userid,String seksyen) throws Exception {
		Db db = null;		
		String sql = "";
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
			+" WHERE" 
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"')"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND A.ID_STATUS <> '999'"
	    	+" AND A.SEKSYEN = '"+seksyen+"'"  
			+" AND STA.AKTIF = 1" 
	        +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
			+" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
			+"";
			
			//myLogger.info("LIST NOTIFICATION ONLINE 8 DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			
				count_8 = rs.getInt("notification");
				
				}
			return count_8;
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	Integer count_pindah = null;
	public Integer getListNotifikasi_pindah(String ekptg_user_id) throws Exception {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT COUNT(distinct A.id_permohonan) as notification from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "+
		      "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "+
		      "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "+
		      "TBLRUJNEGERI R, TBLRUJDAERAH S "+
		      "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "AND A.ID_FAIL  =  C.ID_FAIL "+
		      "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "AND D.ID_SIMATI = E.ID_SIMATI "+
		      "AND A.ID_PEMOHON = F.ID_PEMOHON "+
		      "AND B.ID_NEGERI = G.ID_NEGERI "+
		      "AND B.ID_DAERAH = H.ID_DAERAH "+
		      //"and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      //"AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "AND M.AKTIF = 1 "+
		      "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "AND N.ID_STATUS = O.ID_STATUS "+
		      "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "AND P.USER_ID  =  Q.USER_ID "+
		      "AND A.ID_NEGERIMHN = R.ID_NEGERI "+
		      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
		      "AND Q.USER_ID = '"+ekptg_user_id+"' "+
		      "AND G.ID_NEGERI = P.ID_NEGERI "+
		      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
		      "AND A.ID_STATUS = 56 ";
   
		      //myLogger.info("LIST NOTIFICATION PINDAH DASHBOARD LIST"+sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
				
					count_pindah = rs.getInt("notification");
					
					}
				return count_pindah;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
	Integer count_inbox = null;
	public Integer getListNotifikasi_inbox(String userId) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();				
				sql = ""+
				" SELECT COUNT(*) as notification"+
				" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
				" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "+
				" AND B.USER_ID = '"+userId+"' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";		
				sql += "  ";				
				//myLog.info("SQL COUNT LIST MAIN INBOX XX :" + sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {					
					count_inbox = rs.getInt("notification");					
					}
				return count_inbox;
			} finally {
				if (db != null)
					db.close();
			}
	
	}
	
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
		
	}
	
	private IHTPPermohonan getHTPermohonan(){
		if(iHTPPermohonan == null){
			iHTPPermohonan = new HTPPermohonanBean();
		}
		return iHTPPermohonan;
		
	}	
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	private ICukaiPenyata getICukaiPenyata(){
		if(iCukaiPenyata==null){
			iCukaiPenyata = new CukaiPenyataBean();
		}
		return iCukaiPenyata;
		
	}
	
	private boolean isTab(String role, String tab) throws Exception {
		boolean returnValue = false;
		Utils utils = new Utils();
		if(!utils.getTabID(tab,role).equals(""))
			returnValue = true;
		//myLog.info(utils.getTabID(tab,role));
		return returnValue;
	}	
	
	
	//Carian
	@SuppressWarnings("unchecked")
	public List<Map> DBcarianFail(HttpSession session, String type, String search)throws Exception {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		//String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		ResultSet rs = null;
		Statement stmt = null;

		List senaraiFail = null;

		Integer count = 0;

		try {

			db = new Db();
			//sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = "SELECT DISTINCT FAIL.ID_FAIL, FAIL.NO_FAIL, FAIL.TAJUK_FAIL, FAIL.ID_URUSAN, S.KETERANGAN AS STATUS_FAIL, "+
				  " FAIL.ID_STATUS, PRMHN.ID_PERMOHONAN, HTPPMHN.ID_HTPPERMOHONAN ";
			
			if (type.equals("hakmilik")){
				sql += " , HM.NO_WARTA, HM.STATUS_SAH, HM.ID_HAKMILIK, HM.NO_HAKMILIK, HM.NO_LOT, LT.KETERANGAN AS KOD_LOT, JHM.KOD_JENIS_HAKMILIK, HM.NO_FAIL AS NO_FAIL_HAKMILIK ";
			}
			
			sql += " FROM TBLPFDFAIL FAIL, TBLPERMOHONAN PRMHN, TBLHTPPERMOHONAN HTPPMHN, TBLRUJSUBURUSANSTATUSFAIL SF, TBLRUJSUBURUSANSTATUS US, TBLRUJSTATUS S, TBLRUJKEMENTERIAN KEM ";
					
			if (type.equals("hakmilik")){
				sql += " ,TBLHTPHAKMILIK HM, TBLRUJLOT LT, TBLRUJJENISHAKMILIK JHM ";
			}

			sql += " WHERE FAIL.ID_SEKSYEN = 3 "+ 
					" AND FAIL.ID_FAIL = PRMHN.ID_FAIL "+
					" AND PRMHN.ID_PERMOHONAN = HTPPMHN.ID_PERMOHONAN "+
					" AND PRMHN.ID_PERMOHONAN = SF.ID_PERMOHONAN "+
					" AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS "+
					" AND SF.ID_FAIL = FAIL.ID_FAIL "+
					" AND US.ID_STATUS = S.ID_STATUS "+
					" AND FAIL.ID_KEMENTERIAN = KEM.ID_KEMENTERIAN(+) "+
					" AND SF.AKTIF = '1' "+
					" AND (FAIL.ID_STATUS <> 999 OR FAIL.ID_STATUS IS NULL) "+
					" AND (FAIL.ID_URUSAN IN (4,309) AND FAIL.ID_MASUK = '"+userId+"' "+
						" OR FAIL.ID_URUSAN IN (1,2,3,5,10,108)) ";
			
			if (type.equals("hakmilik")){
				sql += " AND HM.ID_PERMOHONAN = PRMHN.ID_PERMOHONAN AND HM.ID_LOT = LT.ID_LOT(+) AND JHM.ID_JENISHAKMILIK(+) = HM.ID_JENISHAKMILIK";
			}
			
			if (type.equals("fail")) {
				if (search != null) {
					if (!search.trim().equals("")) {
						sql += " AND (" + " UPPER(FAIL.NO_FAIL) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(HTPPMHN.NO_RUJUKAN_KJP) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(HTPPMHN.NO_RUJUKAN_PTG) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(HTPPMHN.NO_RUJUKAN_PTD) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(HTPPMHN.NO_RUJUKAN_UPT) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR  UPPER(PRMHN.TUJUAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR  UPPER(KEM.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR  UPPER(FAIL.TAJUK_FAIL)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim()
								+ "'|| '%')"
								+ ")  ";
					}
				}
			}
			
			
			if (type.equals("hakmilik")) {
				if (search != null) {
					if (!search.trim().equals("")) {
						sql += " AND (";
						sql += " trim(UPPER(HM.NO_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR trim(UPPER(HM.NO_LOT)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						/*sql += " OR trim(UPPER(JHM.KOD_JENIS_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";*/
						sql += ")  ";
					}
				}
			} 
			sql += " ORDER BY FAIL.NO_FAIL ";
			
			myLog.info("carian dashboard : "+sql);
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiFail = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);
				h.put("no_fail", rs.getString("no_fail") == null ? "" : rs.getString("no_fail"));
				h.put("id_fail", rs.getString("id_fail") == null ? "" : rs.getString("id_fail"));
				h.put("tajuk_fail", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL"));
				h.put("id_urusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("status_fail", rs.getString("STATUS_FAIL") == null ? "" : rs.getString("STATUS_FAIL"));
				h.put("id_status", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("id_htppermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				
				if (type.equals("hakmilik")) {
					h.put("NO_WARTA",
							rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
					h.put("STATUS_SAH",
							rs.getString("STATUS_SAH") == null ? "" : rs.getString("STATUS_SAH"));
					h.put("ID_HAKMILIK",
							rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
					h.put("NO_HAKMILIK",
						rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
					h.put("NO_LOT",
						rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
					h.put("KOD_LOT",
						rs.getString("KOD_LOT") == null ? "" : rs.getString("KOD_LOT"));					
					h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));
					h.put("NO_FAIL_HAKMILIK",
						rs.getString("NO_FAIL_HAKMILIK") == null ? "" : rs.getString("NO_FAIL_HAKMILIK")); 
				}
	
				senaraiFail.add(h);
				bil++;
				count++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return senaraiFail;

	}
	
	Integer bil_permohonan = 0;
	public Integer getBilBorang5ABaru(String userId) throws Exception {
		
		Db db = null;
		String sql = "";
		
		try{
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT COUNT(P.ID_PERMOHONAN) AS BIL_PERMOHONAN "+
						" FROM TBLPERMOHONAN P, TBLHTPKEPUTUSANMOHON HK, TBLPFDFAIL F, TBLRUJSUBURUSANSTATUSFAIL SF, TBLRUJSUBURUSANSTATUS US "+ 
						" WHERE P.ID_FAIL = F.ID_FAIL "+
						" AND P.ID_PERMOHONAN = HK.ID_PERMOHONAN "+
						" AND P.ID_PERMOHONAN = SF.ID_PERMOHONAN "+
						" AND SF.ID_SUBURUSANSTATUS = US.ID_SUBURUSANSTATUS "+
						" AND SF.ID_FAIL = F.ID_FAIL "+
						" AND P.ID_STATUS = '86' "+
						" AND (F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) "+
						" AND F.ID_URUSAN IN (1,10) "+
						" AND SF.AKTIF = '1' "+
						" AND HK.FLAG_NOTIFIKASI = 'Y' "+
						" AND F.ID_MASUK = '"+userId+"'";
			
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					bil_permohonan = rs.getInt("BIL_PERMOHONAN");
				}
				
				return bil_permohonan;
		
		}finally{
			if (db != null)
				db.close();
		}
	}
	
	Integer bil_permohonan15a = 0;
	public Integer getBilBorang15ABaru(String userId) throws Exception {
		
		Db db = null;
		String sql = "";
		
		try{
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT COUNT(b.ID_PERMOHONAN) AS BIL_PERMOHONAN "+
						" FROM tblpfdfail a, "+
						" tblpermohonan b, "+
						" tblhtppermohonan pp, "+
						" TBLHTPBORANGPAJAKAN brg "+
						" WHERE b.id_fail = a.id_fail "+
						" AND b.id_permohonan = pp.id_permohonan "+
						" AND a.id_urusan = '3' "+
						" AND (a.id_status <> 999 OR a.id_status IS NULL) "+
						" AND b.id_permohonan = brg.id_permohonan "+
						" AND brg.flag_notifikasi = 'Y' "+
						" ORDER BY b.id_permohonan DESC ";
			
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					bil_permohonan15a = rs.getInt("BIL_PERMOHONAN");
				}
				
				return bil_permohonan15a;
		
		}finally{
			if (db != null)
				db.close();
		}
	}
	
	//check kemaskini cukai
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List DBListKemaskiniCukai(HttpSession session)throws Exception {

		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiKemaskini = null;
		String sql = "";
		
		try {

			db = new Db();
			stmt = db.getStatement();

			sql = " SELECT C.ID_CUKAITEMP, D.ID_NEGERI, D.ID_DAERAH, D.ID_MUKIM, D.ID_KEMENTERIAN, C.NO_HAKMILIK, C.ID_JENISHAKMILIK, "+
					" F.NO_FAIL, F.ID_FAIL, P.ID_PERMOHONAN, C.ID_CUKAITEMP, D.KEGUNAAN_TANAH, JHM.KOD_JENIS_HAKMILIK "+
					" FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPCUKAITEMP C, TBLHTPHAKMILIK D, TBLRUJJENISHAKMILIK JHM "+
					" WHERE F.ID_FAIL = P.ID_FAIL "+
					" AND P.ID_PERMOHONAN = C.ID_PERMOHONAN "+ 
					" AND P.ID_PERMOHONAN = D.ID_PERMOHONAN "+
					" AND JHM.ID_JENISHAKMILIK(+) = C.ID_JENISHAKMILIK "+
					" AND C.FLAG_KEMASKINI_CUKAI = 'NEW' ";
			
			stmt.setFetchSize(20);
//			System.out.println("kemaskini cukai : "+sql);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiKemaskini = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);
				h.put("no_fail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("id_fail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("id_cukaitemp", rs.getString("ID_CUKAITEMP") == null ? "" : rs.getString("ID_CUKAITEMP"));
				h.put("kegunaan_tanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH"));
				
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("id_daerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("id_mukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("id_kementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("no_hakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("id_jenishakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				//h.put("id_hakmilikcukai", rs.getString("ID_HAKMILIKCUKAI") == null ? "" : rs.getString("ID_HAKMILIKCUKAI"));
				h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));
				
				senaraiKemaskini.add(h);
				bil++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return senaraiKemaskini;
	}
	
	public void updateRead(HttpSession session,String id_cukaitemp) throws Exception {
		Statement stmt = null;		
		try{
	      
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	stmt = db.getStatement();	 
	    		
	    	sql = " UPDATE TBLHTPCUKAITEMP SET FLAG_KEMASKINI_CUKAI = 'READ' WHERE ID_CUKAITEMP = "+id_cukaitemp;
	    	System.out.println("update read : "+sql);
	    	stmt.executeUpdate(sql);
	    		
	    	conn.commit();
	    		
	    }catch (SQLException se) { 
		    try {
		    	conn.rollback();
		    } catch (SQLException se2) {
		    	throw new Exception("Rollback error:"+se2.getMessage());
		    }
		    throw new Exception("Ralat : UPDATE FLAG READ ");
		}finally {
	      if (db != null) db.close();
	      if (stmt != null)stmt.close();
	    }//close finally
		  
	}
	
	private Hashtable<String,String> getBayaran() throws Exception {
		mBayaran = new Hashtable<String,String>();
		
		Vector peringatanBayaran = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfBulan.format(new Date()));
		Vector peringatanLewat = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfTahun.format(new Date()));
		mBayaran.put("bilPerBayaran", String.valueOf(peringatanBayaran.size()));
		mBayaran.put("bilPerBayaranLewat", String.valueOf(peringatanLewat.size()));
		return mBayaran;
		
	}
	
	public Vector<Hashtable<String, String>> getStatusOnlinePra(String idUrusan) throws Exception {
		Vector<Hashtable<String, String>> returnVal = null;
		try {
			returnVal = getStatusOnline().getInfoStatusPermohonan(idUrusan,"","-4");
		}catch(Exception e){
			throw new Exception(getErr().getErrorHTML("inquery:"+idUrusan+"::"+e.getMessage()));
		}
		return returnVal ;
		
	}
	
	private IHtp getErr(){
		if(iErr== null)
			iErr = new HtpBean();
		return iErr;
	}

	private IHTPeringatan getIHTPP(){
		if(iHTPP== null)
			iHTPP = new HTPeringatanBean();
		return iHTPP;
	}
	
	private IHTPStatus getStatusRekod(){
		if(iStatus==null){
			iStatus = new HTPStatusRekodBean();
		}
		return iStatus;
				
	}
	
	private IStatus getStatusOnline(){
		if (iStatusOnline==null){
			iStatusOnline=new StatusBean();
		}
		return iStatusOnline;
	}
	
	
}

