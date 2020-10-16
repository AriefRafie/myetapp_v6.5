/**
 * AUTHOR : RAZMAN BIN MD ZAINAL
 * MODIFIED : YATI -13102020
 */
package ekptg.intergration.eTanah.pengambilan;
//import integrasi.ws.etanah.ppt.ETanahCarianManager;
//import integrasi.ws.etanah.ppt.ETanahPPTManager;
import integrasi.IntegrasiManager;
import integrasi.ws.etanah.ppt.ETanahCarianManager;
import integrasi.ws.etanah.ppt.ETanahPPTManager;
//import integrasi.ws.etanah.melaka_ns.ppt.CustomDataSource;
//import integrasi.rest.etanah.wpkl.ppt.EtanahWPKLPPTManager;
//import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMK;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMKE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.BorangCdanMMKResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMk;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMkE;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanBorangAMMkResponse;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8E;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.DaftarPermohonanSek8Response;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.LampiranForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatHakmilikForm;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatPermohonanSek4Form;
import integrasi.ws.etanah.ppt.MyEtappPengambilanServiceStub.MaklumatPermohonanSek8Form;


import java.net.URL;

//import integrasi.rest.etanah.wpkl.ppk.EtanahWPKLPPKManager;
//import integrasi.ws.etanah.ppt.ETanahCarianManager;

//import ekptg.model.integrasi.CapaianHakmilikeTanahHTP;
//import ekptg.model.integrasi.CapaianHakmilikeTanahPPK;
//import ekptg.model.integrasi.FrmPopupCapaianHakmilikeTanahData;
//import ekptg.model.integrasi.IIntegrasieTanahCarian;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import etanah.ws.MyEtappPengambilan;
import etanah.ws.MyEtappPengambilanServiceLocator;

public class PopupPengambilanTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(PopupPengambilanTanah.class);

	PopupPengambilanTanahData logic = new PopupPengambilanTanahData();
	private static MyEtappPengambilanServiceStub stub = null;
	private static IntegrasiManager im = null;
	private static String userName = ""; 
	private static String password = ""; 
	private static String caseCode = "";
	private static String url = "";
	private static String source = "";
	private static String eventName = "";
	private static URL objURL = null;
	private static String msg = "";
	private static String msgDaftar = "";
	//private MyEtappPengambilanServiceStub stub = null;
	public static String flagMsg = null;
	public static String outputMsg = null;
	//private static MyEtappPengambilanServiceStub stub = null;
	
	/*public PopupPengambilanTanah(String kod) throws Exception{
		im = new IntegrasiManager(kod);	
		eventName = im.geTujuan();
		password = im.getKataLaluan();
		source = im.getSumber();
		url = im.getURL();
		userName = im.getIdPengguna();
		
	}
*/
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		//PopupPengambilanTanah cm = new PopupPengambilanTanah("E-TANAH");
		url = "http://kjsb.zapto.org/etanahwsa/MyEtappPengambilanService?WSDL";
		userName = "myetapp";
		password = "etanah123";
		source = "";
		eventName = "";
		//open new version of token in popup
		String prev_token = session.getAttribute("form_token_Pop") == null ? "" : (String) session.getAttribute("form_token_Pop");
		myLogger.info("---------GET prev_token :" + prev_token);		
		String form_token = getParam("form_token_Pop") == null ? "empty" : getParam("form_token_Pop");
		myLogger.info("---------GET form_token :" + form_token);
		if (prev_token.equals(form_token)) {
			session.setAttribute("doPostPop", "true");
		} else if ("empty".equals(form_token)) {
			session.setAttribute("doPostPop", "false");
		} else {
			session.setAttribute("doPostPop", "false");
		}		
		String form_token_set = Long.toString(System.currentTimeMillis());
        session.setAttribute("form_token_Pop", form_token_set);		
		String doPostPop = session.getAttribute("doPostPop").toString();		
		myLogger.info("---------GET doPostPop :" + doPostPop);
		myLogger.info("---------GET SESSION REFRESH :" + session.getAttribute("recordInsertedSuccessfully") );
		//close new version of token in popup
		
		String vm = "";
		
		/*
		Integer new_turutan = 1;		
		if(!getParam("new_turutan").equals("") && !getParam("new_turutan").equals(null))
		{
			new_turutan = Integer.parseInt(getParam("new_turutan"));
		}
		Integer current_turutan = 1;		
		if(!getParam("current_turutan").equals("") && !getParam("current_turutan").equals(null))
		{
			current_turutan = Integer.parseInt(getParam("current_turutan"));
		}
		*/
		
		
		String id_fail = getParam("id_fail");
		String id_permohonan = getParam("id_permohonan");
		String no_fail = getParam("no_fail");
		String jenis_skrin = getParam("jenis_skrin");
		String hitButton = getParam("hitButton");
		String action = getParam("action");
		String id_dokumen = getParam("id_dokumen");
		String tajuk = getParam("tajuk");
		String kategori_lampiran = getParam("kategori_lampiran");
		String id_hakmilik = getParam("id_hakmilik");
		String id_penarikan = getParam("id_penarikan");
		String command = getParam("command");
		myLogger.info("command :::::::" + command);
		myLogger.info("action :::::::" + action);
		this.context.put("id_fail", id_fail);
		this.context.put("id_permohonan", id_permohonan);
		this.context.put("no_fail", no_fail);
		this.context.put("jenis_skrin", jenis_skrin);
		this.context.put("hitButton", hitButton);
		this.context.put("tajuk", tajuk);
		this.context.put("id_hakmilik", id_hakmilik);
		this.context.put("id_penarikan", id_penarikan);
		context.put("statusSend", "");
		context.put("statusMesej", "");
		context.put("errorMesej", "");
		context.put("statusSend_doc", "");
		context.put("statusMesej_doc", "");
		context.put("errorMesej_doc", "");
		context.put("count_hakmilik", "");
		
		
		context.put("hash_maklumatEndorsan_size", "0");
		context.put("hash_maklumatKeputusanMmk_size", "0");
		context.put("hash_maklumatHMS_size", "0");
		context.put("listSenaraiDokumen_fromEtanah_size", "0");
		
		vm = "app/integrasi/etanah/pengambilanTanah/PopupPengambilanTanah.jsp";
		myLogger.info("vm : "+vm);
		myLogger.info("hitButton :::::::" + hitButton);
		
		
		
		//default
		if (jenis_skrin.equals("BorangA")) {
			myLogger.info("masukk");
			//PopupPengambilanTanah cm = new PopupPengambilanTanah("E-TANAH");
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				List<Hashtable> listSenaraiLotBorangC = new ArrayList();
				listSenaraiLotBorangC = logic
						.listSenaraiLotBorangC(id_fail, db);
				setupPage(session, action, listSenaraiLotBorangC);
				//listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						//jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				//listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						//id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);/*
				hashMaklumatWarta_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db,id_penarikan);*/
				//hashMaklumatEndorsan_fromEtanah(id_fail, id_permohonan,	id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				//hashMaklumatKeputusanMmk_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				//maklumatMMK("",id_fail, jenis_skrin, db, "");
				//count_logDerafMMK(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				//listSenaraiKategoriLampiran("",jenis_skrin,db);
				//derafMaklumatPengambilanLog_COUNT(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				
			} finally {
				if (db != null)
					db.close();
			}
			
			//ETanahPPTManager.
			//hantarBorangD(idPermohonanIntegrasi, userID, dbMain);
			if ("hantarData".equals(hitButton)) {
				myLogger.info("masuk hantar ");
				if(doPostPop.equals("true"))
				{
					myLogger.info("masuk aaa la ");
					context.put("statusSend", "yes");
					context.put("statusMesej",
							"<font color='blue'><b><blink>Berjaya!</blink></b></font>");
					
					try {
						db = new Db();
						myLogger.info("masuk sini tak ye");
						MaklumatPermohonanSek4Form reqformSek4 = new MaklumatPermohonanSek4Form();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						Calendar cal = Calendar.getInstance();
						String dateNow = dateFormat.format(cal.getTime());
						myLogger.info("dateNow :" + dateNow);
						
						reqformSek4.setTarikh_permohonan("16/10/2020");
						myLogger.info("setTarikh_permohonan :"+dateNow);
						reqformSek4.setNama_kementerian("KEMENTERIAN AIR, TANAH DAN SUMBER ASLI");
						reqformSek4.setTujuan_dalam_english("");
						reqformSek4.setTujuan("Pembersihan dan Pengindahan Sg. Melaka, Melaka Parcel 2.");
						reqformSek4.setNo_fail_jkptg("JKPTG(S).MLK/03/881/18/2020/3");
						reqformSek4.setKod_negeri_pengambilan("04");
						reqformSek4.setNama_negeri_pengambilan("MELAKA");
						reqformSek4.setKod_daerah_pengambilan("03");
						reqformSek4.setNama_daerah_pengambilan("ALOR GAJAH");
						reqformSek4.setJenis_pengambilan("SEKSYEN4");
						reqformSek4.setJenis_projek_pengambilan("");
						reqformSek4.setNo_rujukan_surat_kjp("KATS/BPN 608-8/5 Jld.5 (33)");
						reqformSek4.setTarikh_surat_kjp("09/08/2012");
						reqformSek4.setId_kementerian_myetapp("18");
						reqformSek4.setNama_agensi("BAHAGIAN PEMBANGUNAN");
						reqformSek4.setId_agensi_myetapp("445");
						reqformSek4.setKodAgensi("25");
						reqformSek4.setKodKementerian("18");
						reqformSek4.setAlamat1("Aras 15, Wisma Sumber Asli");
						reqformSek4.setAlamat2("No.25 Persiaran Perdana, Presint 4");
						reqformSek4.setAlamat3("");
						reqformSek4.setAlamat4("");
						reqformSek4.setPoskod("62574");
						reqformSek4.setKodNegeri("16");			
						
						MaklumatHakmilikForm reqformHakmilik = new MaklumatHakmilikForm();
						
						reqformHakmilik.setId_hakmilik("040108PN00019187");
						reqformHakmilik.setKod_luas_ambil("M");
						reqformHakmilik.setKod_luas_asal("M");
						reqformHakmilik.setKod_unit_hakmilik("PN");
						reqformHakmilik.setLuas_ambil("143.0000");
						reqformHakmilik.setLuas_asal("143.0000");
						reqformHakmilik.setNo_fail_jkptg("JKPTG(S).MLK/03/881/18/2020/3"); 
						reqformHakmilik.setNo_hakmilik("00019187");
						reqformHakmilik.setNo_lot("0003346");
						reqformHakmilik.setNo_warta("");
						reqformHakmilik.setStatus_borangk("");
						reqformHakmilik.setTarikh_borangk("");
						reqformHakmilik.setTarikh_warta("");
						
						MaklumatHakmilikForm[] hakmiliks = new MaklumatHakmilikForm[];
						LampiranForm lampiranMMK = null;
						LampiranForm[] lampiran = null;
						//hantarData(jenis_skrin, id_fail, id_hakmilik, session,id_permohonan, id_penarikan,db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
						String test = daftarPermohonanBorangAMMk(reqformSek4
								,hakmiliks
								,lampiranMMK
								,lampiran
								);
						myLogger.info("masuk sini tak --- "+test);
						myLogger.info("masuk sini tak");
					} 
					catch (Exception e) {
						context.put("statusSend", "no");
					context.put("statusMesej",
							"<font color='red'><b><blink>Tidak Berjaya!</blink></b></font>");
					context.put("errorMesej",
							"<font color='red'><b>" + e.toString() + "</b></font>");
				}
					finally {
						if (db != null)
							db.close();
					}
					
				}
			}
		
		}
		
		   return vm;
	}
	
	private Integer return_new_turutan (String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db) throws Exception {
		
		Integer turutan = 1;		
		
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		String flag_keputusan_mmk = "";
		Integer turutan_frmetanah = 0;
		
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatKeputusanMmk = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""	: hash_maklumatprojek.get("NO_FAIL").toString());		
		//hash_maklumatKeputusanMmk = getMaklumatKeputusanMmk_fromEtanah(jenis_skrin, no_fail_jkptg, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db));	
		
		myLogger.info("************ hash_maklumatKeputusanMmk :"+hash_maklumatKeputusanMmk);
		if(hash_maklumatKeputusanMmk != null && hash_maklumatKeputusanMmk.size()>0)
		{
			flag_keputusan_mmk = (hash_maklumatKeputusanMmk.get("FLAG_KEPUTUSAN_MMK").toString() == null ? ""	: hash_maklumatKeputusanMmk.get("FLAG_KEPUTUSAN_MMK").toString());		
			if(flag_keputusan_mmk.equals("T") && (jenis_skrin.equals("BorangA") || jenis_skrin.equals("BorangC")))
			{
				turutan_frmetanah = (Integer) hash_maklumatKeputusanMmk.get("TURUTAN");	
				myLogger.info("************ turutan_frmetanah :"+turutan_frmetanah);
				turutan += turutan_frmetanah;
			}
			else if(flag_keputusan_mmk.equals("Y") && (jenis_skrin.equals("BorangA") || jenis_skrin.equals("BorangC")))
			{
				turutan_frmetanah = (Integer) hash_maklumatKeputusanMmk.get("TURUTAN");	
				turutan = turutan_frmetanah;
			}
		}
		myLogger.info("************ hash_maklumatKeputusanMmk turutan :"+turutan);
		
		return turutan;
	}

	private Integer return_current_turutan (String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db) throws Exception {
		
		Integer current_turutan = 1;		
		
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		String flag_keputusan_mmk = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_current_turutan_maklumatKeputusanMmk = null;
		
		
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""	: hash_maklumatprojek.get("NO_FAIL").toString());		
		//hash_current_turutan_maklumatKeputusanMmk = current_turutan_getMaklumatKeputusanMmk_fromEtanah(jenis_skrin, no_fail_jkptg, db);	
		
		myLogger.info("************ hash_current_turutan_maklumatKeputusanMmk :"+hash_current_turutan_maklumatKeputusanMmk);
		if(hash_current_turutan_maklumatKeputusanMmk != null && hash_current_turutan_maklumatKeputusanMmk.size()>0)
		{
			if(jenis_skrin.equals("BorangA") || jenis_skrin.equals("BorangC"))
			{
				current_turutan = (Integer) hash_current_turutan_maklumatKeputusanMmk.get("CURRENT_TURUTAN");
			}
		}
		myLogger.info("************ hash_current_turutan_maklumatKeputusanMmk turutan :"+current_turutan);
		
		return current_turutan;
	}

	
	private void count_logDerafMMKPB(String id_penarikan, String jenis_skrin, Db db)
			throws Exception {
		Integer countlog_mmkPB = 0;
		countlog_mmkPB = logic.derafMmkPBLog_COUNT(id_penarikan, jenis_skrin, db);
		context.put("countlog_mmkPB", countlog_mmkPB);
	}
	
	private void listSenaraiKategoriLampiran(String id_lampiran,String jenis_skrin, Db db)
			throws Exception {
		List<Hashtable>  listSenaraiKategoriLampiran = new ArrayList();
		listSenaraiKategoriLampiran = logic.listSenaraiKategoriLampiran(id_lampiran,jenis_skrin,db);
		context.put("listSenaraiKategoriLampiran", listSenaraiKategoriLampiran);
	}
	
	
	private void count_logMaklumatWarta(String id_fail, String jenis_skrin, Db db)
			throws Exception {
		Integer countlog_warta = 0;
		countlog_warta = logic.derafMaklumatWartaLog_COUNT(id_fail, jenis_skrin, db);
		context.put("countlog_warta", countlog_warta);
	}
	
	private void derafMaklumatPengambilanLog_COUNT(String id_fail, String jenis_skrin, Db db,Integer turutan)
			throws Exception {
		Integer countlog_maklumatpengambilan = 0;
		countlog_maklumatpengambilan = logic.derafMaklumatPengambilanLog_COUNT(id_fail, jenis_skrin, db,turutan);
		context.put("countlog_maklumatpengambilan", countlog_maklumatpengambilan);
	}
	
	private void count_logDerafMMK(String id_fail, String jenis_skrin, Db db,Integer turutan)
			throws Exception {
		Integer countlog_mmk = 0;
		countlog_mmk = logic.derafMmkLog_COUNT(id_fail, jenis_skrin, db,turutan);
		context.put("countlog_mmk", countlog_mmk);
	}

	private void count_dokumen(String id_permohonan, String id_hakmilik,
			String jenis_skrin, Db db, String id_penarikan) throws Exception {
		Integer count_dokumen = 0;
		count_dokumen = logic.dokumen_COUNT(id_permohonan, id_hakmilik,
				jenis_skrin, db, id_penarikan);
		context.put("count_dokumen", count_dokumen);
	}

	private void count_hakmilik(String id_penarikan, String id_fail,
			String id_hakmilik, String jenis_skrin, Db db) throws Exception {
		Integer count_hakmilik = 0;
		count_hakmilik = logic.hakmilik_COUNT(id_penarikan, id_fail,
				id_hakmilik, jenis_skrin, db);
		context.put("count_hakmilik", count_hakmilik);
	}

	private void countLog_hakmilik(String id_fail, String id_hakmilik,
			String jenis_skrin, Db db, String id_penarikan,Integer turutan) throws Exception {
		Integer countlog_hakmilik = 0;
		countlog_hakmilik = logic.hakmilikLog_COUNT(id_fail, id_hakmilik,
				jenis_skrin, db, id_penarikan, turutan);
		context.put("countlog_hakmilik", countlog_hakmilik);
	}

	private void countLog_dokumen(String id_fail, String id_hakmilik,
			String jenis_skrin, Db db, String id_penarikan) throws Exception {
		Integer countLog_dokumen = 0;
		countLog_dokumen = logic.dokumenLog_COUNT(id_fail, id_hakmilik,
				jenis_skrin, db, id_penarikan);
		context.put("countLog_dokumen", countLog_dokumen);
	}

	private void maklumatProjek(String id_fail, Db db) throws Exception {
		Hashtable hash_maklumatprojek = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		context.put("hash_maklumatprojek", hash_maklumatprojek);
	}
	
	

	private void maklumatWarta(String id_fail, Db db) throws Exception {
		Hashtable hash_maklumatWarta = null;
		hash_maklumatWarta = logic.getMaklumatWarta(id_fail, db);
		
		myLogger.info("hash_maklumatWarta WARTA SIZE"+hash_maklumatWarta);
		
		context.put("hash_maklumatWarta", hash_maklumatWarta);
	}
	String nama_nama_mukim = "";
	public String getNamaMukim(String idPermohonan) throws Exception{
        
        
        Db db = null;
        String sql = "";
        String nama2Mukim = "";
        String listLOT = "";
        String listLOTHM = "";
        double totalSize = 0;
        
        try {
                     db = new Db();
                     Statement stmt = db.getStatement();
                     
                     sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rx = stmt.executeQuery(sql);                        
                     int totalMukim = 0;

                     while (rx.next()){                              
                             totalMukim = rx.getInt("totalMukim");                                                                                              
                     }
        
                     sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rz = stmt.executeQuery(sql);   
                     
                     String namaMukim = "";
                     int bilMukim = 0;
                     
                     while (rz.next()){                              
                                   
                            if(totalMukim!=0){
                                   if(bilMukim==0){
                                          
                                          namaMukim += namaMukim = rz.getString("nama_mukim");   
                                   
                                   }else{
                                          
                                          if(totalMukim - bilMukim == 1){
                                                 namaMukim += " dan "+rz.getString("nama_mukim");
                                          }else{
                                                 namaMukim += ", "+rz.getString("nama_mukim");   
                                          }      
                                   }
                                   bilMukim++;   
                            
                            }else{
                                   namaMukim = "";
                            }
                            
                            if(totalMukim == bilMukim){
                                   nama2Mukim = namaMukim;
                            }else{
                                   nama2Mukim = "";
                            }
                     }
                     nama_nama_mukim = nama2Mukim;
                     
                     myLogger.info("nama_nama_mukim ::::::::::::: "+nama_nama_mukim);
                     
                     return nama_nama_mukim;
              
        }finally {
               if(db != null) db.close();
        }
        }//close setDataListKertas
	
	public String daftarPermohonanBorangAMMk( MaklumatPermohonanSek4Form form
			,MaklumatHakmilikForm[] hakmiliks
			,LampiranForm lampiranMMK
			,LampiranForm[] lampiran
			) throws Exception{
			//MaklumatPermohonanSek8Form permohonan = null;
			stub = getStub();
			myLogger.info("masuk sini tak12345");
			DaftarPermohonanBorangAMMk requestBorangA = new DaftarPermohonanBorangAMMk();
			//request.setIdPermohonan(idPermohonan);
			//request.setDrafMMk(lampiranMMK);
			//request.setAttachment(lampiran);
			requestBorangA.setMaklumatPermohonan(form);
			requestBorangA.setMaklumatHakmilik(hakmiliks);
			requestBorangA.setDrafMMk(lampiranMMK);
			requestBorangA.setAttachment(lampiran);
				
			DaftarPermohonanBorangAMMkE temp = new DaftarPermohonanBorangAMMkE();
			//temp.setBorangCdanMMK(request);
			temp.setDaftarPermohonanBorangAMMk(requestBorangA);
				
			DaftarPermohonanBorangAMMkResponse response = stub.daftarPermohonanBorangAMMk(temp).getDaftarPermohonanBorangAMMkResponse();
					//stub.borangCdanMMK(temp).getBorangCdanMMKResponse();
			return response.get_return();
				
		}
	
	public void MaklumatPermohonanSek4Form(String tarikh_permohonan, String nama_kementerian, String tujuan_dalam_english, 
			String tujuan,String no_fail_jkptg, String kod_negeri_pengambilan, String nama_negeri_pengambilan, 
			String kod_daerah_pengambilan, String nama_daerah_pengambilan,String jenis_pengambilan, String jenis_projek_pengambilan, 
			String no_rujukan_surat_kjp, String tarikh_surat_kjp, String id_kementerian_myetapp, String nama_agensi,
			String id_agensi_myetapp, String kodAgensi, String kodKementerian, String alamat1, String alamat2, String alamat3,
			String alamat4, String poskod, String kodNegeri) throws Exception{
			
			stub = getStub();
			
			//DaftarPermohonanBorangAMMk requestBorangA = new DaftarPermohonanBorangAMMk();
			MaklumatPermohonanSek4Form reqformSek4 = new MaklumatPermohonanSek4Form();
			
			//request.setIdPermohonan(idPermohonan);
			//request.setDrafMMk(lampiranMMK);
			//request.setAttachment(lampiran);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String dateNow = dateFormat.format(cal.getTime());
			myLogger.info("dateNow :" + dateNow);
			
			reqformSek4.setTarikh_permohonan(dateNow);
			myLogger.info("setTarikh_permohonan :"+dateNow);
			reqformSek4.setNama_kementerian("KEMENTERIAN AIR, TANAH DAN SUMBER ASLI");
			reqformSek4.setTujuan_dalam_english("");
			reqformSek4.setTujuan("Pembersihan dan Pengindahan Sg. Melaka, Melaka Parcel 2.");
			reqformSek4.setNo_fail_jkptg("JKPTG(S).MLK/03/881/18/2020/3");
			reqformSek4.setKod_negeri_pengambilan("04");
			reqformSek4.setNama_negeri_pengambilan("MELAKA");
			reqformSek4.setKod_daerah_pengambilan("03");
			reqformSek4.setNama_daerah_pengambilan("ALOR GAJAH");
			reqformSek4.setJenis_pengambilan("SEKSYEN4");
			reqformSek4.setJenis_projek_pengambilan("");
			reqformSek4.setNo_rujukan_surat_kjp("KATS/BPN 608-8/5 Jld.5 (33)");
			reqformSek4.setTarikh_surat_kjp("09/08/2012");
			reqformSek4.setId_kementerian_myetapp("18");
			reqformSek4.setNama_agensi("BAHAGIAN PEMBANGUNAN");
			reqformSek4.setId_agensi_myetapp("445");
			reqformSek4.setKodAgensi("25");
			reqformSek4.setKodKementerian("18");
			reqformSek4.setAlamat1("Aras 15, Wisma Sumber Asli");
			reqformSek4.setAlamat2("No.25 Persiaran Perdana, Presint 4");
			reqformSek4.setAlamat3("");
			reqformSek4.setAlamat4("");
			reqformSek4.setPoskod("62574");
			reqformSek4.setKodNegeri("16");
				
	}
	
	public void maklumatHakmilikForm(String idHakmilik, String kod_luas_ambil, String kod_luas_asal, 
			String kod_unit_hakmilik,String kodunit_hakmilik, String luas_ambil, String luas_asal, 
			String no_fail_jkptg, String no_hakmilik,String no_lot, String no_warta, String status_borangK, 
			String tarikh_borangK, String tarikh_warta) throws Exception{
			
			stub = getStub();
			
			//DaftarPermohonanBorangAMMk requestBorangA = new DaftarPermohonanBorangAMMk();
			MaklumatHakmilikForm reqformHakmilik = new MaklumatHakmilikForm();
			//request.setIdPermohonan(idPermohonan);
			//request.setDrafMMk(lampiranMMK);
			//request.setAttachment(lampiran);
			reqformHakmilik.setId_hakmilik("040108PN00019187");
			reqformHakmilik.setKod_luas_ambil("M");
			reqformHakmilik.setKod_luas_asal("M");
			reqformHakmilik.setKod_unit_hakmilik("PN");
			reqformHakmilik.setLuas_ambil("143.0000");
			reqformHakmilik.setLuas_asal("143.0000");
			reqformHakmilik.setNo_fail_jkptg("JKPTG(S).MLK/03/881/18/2020/3"); 
			reqformHakmilik.setNo_hakmilik("00019187");
			reqformHakmilik.setNo_lot("0003346");
			reqformHakmilik.setNo_warta("");
			reqformHakmilik.setStatus_borangk("");
			reqformHakmilik.setTarikh_borangk("");
			reqformHakmilik.setTarikh_warta("");
				
	}
	
	private static MyEtappPengambilanServiceStub getStub() throws Exception {
		MyEtappPengambilanServiceStub stub = new MyEtappPengambilanServiceStub(url);	
				
		org.apache.axis2.client.Options options = new org.apache.axis2.client.Options();
		List<org.apache.axis2.context.NamedValue> namedValuePairs = new ArrayList<org.apache.axis2.context.NamedValue>();
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("SOAPAction", "myetapp"));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("username", userName));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("password", password));
		namedValuePairs.add(new org.apache.axis2.context.NamedValue("Content-Type", "text/html"));
		options.setProperty(org.apache.axis2.transport.http.HTTPConstants.HTTP_HEADERS, namedValuePairs);
		stub._getServiceClient().setOptions(options);
		
		stub._getServiceClient().setTargetEPR(new EndpointReference(url));
		return stub;
		
	}
	public static String getEventName() {
		return eventName;
	}

	public static void setEventName(String eventName) {
		PopupPengambilanTanah.eventName = eventName;
	}
	
	public static String getFlagMsg() {
		return flagMsg;
	}

	public static void setFlagMsg(String flagMsg) {
		PopupPengambilanTanah.flagMsg = flagMsg;
	}

	public static String getOutputMsg() {
		return outputMsg;
	}

	public static void setOutputMsg(String outputMsg) {
		PopupPengambilanTanah.outputMsg = outputMsg;
	}
	
	
	}

