/**
 * AUTHOR : RAZMAN BIN MD ZAINAL
 */
package ekptg.view.integrasi.etanah.ppt;

import integrasi.ws.etanah.melaka_ns.ppt.CustomDataSource;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMKTajuk_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMKTajuk_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMK_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDerafMMK_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDokumen_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertDokumen_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertHakmilikList_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertHakmilikList_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatPengambilan_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatPengambilan_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatWarta_byObject;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.InsertMaklumatWarta_byObjectE;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintpptderafmmk;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintpptderafmmktajuk;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintpptdokumen;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintppthakmilik;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintpptmaklumatpengambilan;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.Tblintpptwarta;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.UploadDokumen;
import integrasi.ws.etanah.melaka_ns.ppt.EtappPengambilanServiceStub.UploadDokumenE;

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

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.intergration.eTanah.pengambilan.PopupPengambilanTanahData;

public class PopupPengambilanTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(ekptg.view.integrasi.etanah.ppt.PopupPengambilanTanah.class);

	PopupPengambilanTanahData logic = new PopupPengambilanTanahData();
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		//open new version of token in popup
		String prev_token = session.getAttribute("form_token_Pop") == null ? "" : (String) session.getAttribute("form_token_Pop");
		String form_token = getParam("form_token_Pop") == null ? "empty" : getParam("form_token_Pop");
		myLogger.info("---------GET prev_token :" + prev_token+",form_token :" + form_token);
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
		myLogger.info("hitButton =" + hitButton);	
		
		if ("simpanDokumen".equals(hitButton)) {
			
			if(doPostPop.equals("true")){
				context.put("statusSend_doc", "yes");
				context.put("statusMesej_doc","<font color='blue'><b><blink>Muat Naik Berjaya!</blink></b></font>");
				Db db = null;
				Connection conn = null;
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);					
						uploadFiles(id_permohonan, id_fail, id_hakmilik, jenis_skrin,
								tajuk, session, db, conn, id_penarikan,kategori_lampiran,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
						context.put("flag_submit", "yes");
						conn.commit();					
				
				} catch (Exception e) {
					try {
						conn.rollback();
					} catch (SQLException se2) {
						throw new Exception("Rollback error:" + se2.getMessage());
					}
					context.put("statusSend_doc", "no");
					context.put("statusMesej_doc",
							"<font color='red'><b><blink>Muat Naik Tidak Berjaya!</blink></b></font>");
					context.put("errorMesej_doc",
							"<font color='red'><b>" + e.toString() + "</b></font>");
				} finally {
					if (db != null)
						db.close();
				}
			}	
			
		} else if ("hapusDokumen".equals(hitButton)) {
			if(doPostPop.equals("true"))
			{
				hapusDokumen(id_dokumen, jenis_skrin);
				context.put("flag_submit", "yes");
			}
		} else if ("hapusDokumenEtanah".equals(hitButton)) {
			if(doPostPop.equals("true")){
				hapusDokumenEtanah(id_dokumen, jenis_skrin);
				context.put("flag_submit", "yes");
			}
			
		} else if ("hantarData".equals(hitButton)) {
			if(doPostPop.equals("true")){
				context.put("statusSend", "yes");
				context.put("statusMesej","<font color='blue'><b><blink>Berjaya!</blink></b></font>");
				Db db = null;
				try {
					db = new Db();
					hantarData(jenis_skrin, id_fail, id_hakmilik, session,id_permohonan, id_penarikan,db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				
				} catch (Exception e) {
					context.put("statusSend", "no");
					context.put("statusMesej","<font color='red'><b><blink>Tidak Berjaya!</blink></b></font>");
					context.put("errorMesej","<font color='red'><b>" + e.toString() + "</b></font>");
			
				}finally {
					if (db != null)
						db.close();
				}
				
			}
			
		}
		if (jenis_skrin.equals("BorangC")) {
			Db db = null;
			try {
				db = new Db();				
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				List<Hashtable> listSenaraiLotBorangC = new ArrayList();
				listSenaraiLotBorangC = logic
						.listSenaraiLotBorangC(id_fail, db);
				setupPage(session, action, listSenaraiLotBorangC);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
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
				//hashMaklumatEndorsan_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				maklumatMMK("",id_fail, jenis_skrin, db, "");
				count_logDerafMMK(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiKategoriLampiran("",jenis_skrin,db);
				hashMaklumatKeputusanMmk_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				derafMaklumatPengambilanLog_COUNT(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
			} finally {
				if (db != null)
					db.close();
			}
		} 
		
		else if (jenis_skrin.equals("WartaS8")) {
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				List<Hashtable> listSenaraiLotBorangC = new ArrayList();
				listSenaraiLotBorangC = logic
						.listSenaraiLotBorangC(id_fail, db);
				setupPage(session, action, listSenaraiLotBorangC);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				listSenaraiKategoriLampiran("",jenis_skrin,db);
				count_logMaklumatWarta(id_fail,jenis_skrin,db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				hashMaklumatEndorsan_fromEtanah(id_fail, id_permohonan,	id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		else if (jenis_skrin.equals("WartaS4")) {
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				listSenaraiKategoriLampiran("",jenis_skrin,db);
				count_logMaklumatWarta(id_fail,jenis_skrin,db);
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);
			} finally {
				if (db != null)
					db.close();
			}
		}
		else if (jenis_skrin.equals("BorangI")) {
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				List<Hashtable> listSenaraiLotBorangI = new ArrayList();
				listSenaraiLotBorangI = logic
						.listSenaraiLotBorangI(id_fail, db);
				setupPage(session, action, listSenaraiLotBorangI);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				listSenaraiKategoriLampiran("",jenis_skrin,db);
			} finally {
				if (db != null)
					db.close();
			}
		} else if (jenis_skrin.equals("BorangA")) {
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				List<Hashtable> listSenaraiLotBorangC = new ArrayList();
				listSenaraiLotBorangC = logic
						.listSenaraiLotBorangC(id_fail, db);
				setupPage(session, action, listSenaraiLotBorangC);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
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
				hashMaklumatKeputusanMmk_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				maklumatMMK("",id_fail, jenis_skrin, db, "");
				count_logDerafMMK(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiKategoriLampiran("",jenis_skrin,db);
				derafMaklumatPengambilanLog_COUNT(id_fail, jenis_skrin, db,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				
			} finally {
				if (db != null)
					db.close();
			}
		}/*
		else if (jenis_skrin.equals("TarikBalik")) {
			Db db = null;
			try {
				db = new Db();
				maklumatTarikBalik(id_penarikan, db);
				List<Hashtable> listSenaraiLotTarikBalik = new ArrayList();
				listSenaraiLotTarikBalik = logic.listSenaraiLotTarikBalik(
						id_penarikan, db);
				setupPage(session, action, listSenaraiLotTarikBalik);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				maklumatMMK(id_penarikan,id_fail, jenis_skrin, db, "");
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_logDerafMMKPB(id_penarikan, jenis_skrin, db);
				hashMaklumatWarta_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db,id_penarikan);
				listSenaraiKategoriLampiran("",jenis_skrin,db);
			} finally {
				if (db != null)
					db.close();
			}
		}*/
		else if (jenis_skrin.equals("BorangK")) {
			Db db = null;
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				maklumatBorangK(id_fail, id_hakmilik, db);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				hashMaklumatEndorsan_fromEtanah(id_fail, id_permohonan,	id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiKategoriLampiran("",jenis_skrin,db);
			} finally {
				if (db != null)
					db.close();
			}
		} else if (jenis_skrin.equals("PU")) {
			Db db = null;			
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				maklumatBorangK(id_fail, id_hakmilik, db);
				maklumatPU(id_fail, id_hakmilik, db);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);				
				hashMaklumatHMSambungan_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiKategoriLampiran("",jenis_skrin,db);
			} finally {
				if (db != null)
					db.close();
			}
		}
		else if (jenis_skrin.equals("SijilUkur")) {
			Db db = null;			
			try {
				db = new Db();
				context.put("turutan",return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				maklumatBorangK(id_fail, id_hakmilik, db);
				maklumatPU(id_fail, id_hakmilik, db);
				listSenaraiDokumenUpload(id_permohonan, id_hakmilik,
						jenis_skrin, id_penarikan, db);
				maklumatProjek(id_fail, db);
				maklumatWarta(id_fail, db);
				listSenaraiDokumenUpload_fromEtanah(id_fail, id_permohonan,
						id_hakmilik, jenis_skrin, db);
				count_hakmilik(id_penarikan, id_fail, id_hakmilik, jenis_skrin,
						db);
				countLog_hakmilik(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan,return_new_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				countLog_dokumen(id_fail, id_hakmilik, jenis_skrin, db,
						id_penarikan);
				count_dokumen(id_permohonan, id_hakmilik, jenis_skrin, db,
						id_penarikan);				
				//hashMaklumatHMSambungan_fromEtanah(id_fail, id_permohonan,id_hakmilik, jenis_skrin, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik, jenis_skrin,db));
				listSenaraiKategoriLampiran("",jenis_skrin,db);
			} finally {
				if (db != null)
					db.close();
			}
		}
		return vm;
	}

	public void hantarData(String jenis_skrin_etapp, String id_fail_etapp,
		String id_hakmilik_etapp, HttpSession session,
		String id_permohonan, String id_penarikan,Db db,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);
		//Db db = null;
		try {
			//db = new Db();
			String NO_WARTA = "";
			String TARIKH_WARTA = "";
			String NO_FAIL_DERAF = "";
			String NO_RUJUKAN_TARIKBALIK = "";
			String ID_NEGERI = "";

			Hashtable hash_maklumatWarta = null;
			Hashtable hash_maklumatProjek = null;
			Hashtable hash_maklumatTarikBalik = null;		
			
			hash_maklumatProjek = logic.getMaklumatProjek(id_fail_etapp, db);
			NO_FAIL_DERAF = (hash_maklumatProjek.get("NO_FAIL").toString());
			ID_NEGERI = (hash_maklumatProjek.get("ID_NEGERI").toString());
			
			//open maklumat pengambilan tanah				
			 String tarikh_permohonan_mp = "";
			 String nama_kementerian_mp = "";
			 String tujuan_dalam_english_mp = "";
			 String tujuan_mp = "";
			 String no_fail_jkptg_mp = "";
			 String kod_negeri_pengambilan_mp = "";
			 String nama_negeri_pengambilan_mp = "";
			 String kod_daerah_pengambilan_mp = "";
			 String nama_daerah_pengambilan_mp = "";
			 String jenis_pengambilan_mp = "";
			 String jenis_projek_pengambilan_mp = "";
			 String no_rujukan_surat_kjp_mp = "";
			 String tarikh_surat_kjp_mp = "";
			 String no_rujukan_ptg_mp = "";    
			 String no_rujukan_ptd_mp = "";
			 String no_rujukan_upt_mp = "";
			 String id_kementerian_myetapp_mp = "";
			 String nama_agensi_mp = "";
			 String id_agensi_myetapp_mp = "";
			 String jenis_pengambilan_segera_mp = "";
			 String flag_permohonan_segera_mp = ""; /* 1-Pengambilan Segera Keseluruhan 3-pengambilan segera sebahagian */
			 Integer turutan_mp = turutan;
			 String flag_proses_mp = jenis_skrin_etapp;
			 String kod_agensi = "";
			 String kod_kementerian = "";
			 
			 String alamat1_kjp = "";
			 String alamat2_kjp = "";
			 String alamat3_kjp = "";
			 String poskod_kjp = "";
			 String kod_negeri_kjp = "";
			 
			if(hash_maklumatProjek!=null && hash_maklumatProjek.size()>0){	
				
			tarikh_permohonan_mp = (hash_maklumatProjek.get("TARIKH_PERMOHONAN").toString());
			nama_kementerian_mp = (hash_maklumatProjek.get("NAMA_KEMENTERIAN").toString());
			tujuan_dalam_english_mp = (hash_maklumatProjek.get("TUJUAN_DALAM_ENGLISH").toString());
			tujuan_mp = (hash_maklumatProjek.get("TUJUAN").toString());
			no_fail_jkptg_mp = (hash_maklumatProjek.get("NO_FAIL_JKPTG").toString());
			kod_negeri_pengambilan_mp = (hash_maklumatProjek.get("KOD_NEGERI_PENGAMBILAN").toString());
			nama_negeri_pengambilan_mp = (hash_maklumatProjek.get("NAMA_NEGERI_PENGAMBILAN").toString());
			kod_daerah_pengambilan_mp = (hash_maklumatProjek.get("KOD_DAERAH_PENGAMBILAN").toString());
			nama_daerah_pengambilan_mp = (hash_maklumatProjek.get("NAMA_DAERAH_PENGAMBILAN").toString());
			jenis_pengambilan_mp = (hash_maklumatProjek.get("JENIS_PENGAMBILAN").toString());
			jenis_projek_pengambilan_mp = (hash_maklumatProjek.get("JENIS_PROJEK_PENGAMBILAN").toString());
			no_rujukan_surat_kjp_mp = (hash_maklumatProjek.get("NO_RUJUKAN_SURAT_KJP").toString());
			tarikh_surat_kjp_mp = (hash_maklumatProjek.get("TARIKH_SURAT_KJP").toString());
			no_rujukan_ptg_mp = (hash_maklumatProjek.get("NO_RUJUKAN_PTG").toString());    
			no_rujukan_ptd_mp = (hash_maklumatProjek.get("NO_RUJUKAN_PTD").toString());
		    no_rujukan_upt_mp = (hash_maklumatProjek.get("NO_RUJUKAN_UPT").toString());
			id_kementerian_myetapp_mp = (hash_maklumatProjek.get("ID_KEMENTERIAN_MYETAPP").toString());
			nama_agensi_mp = (hash_maklumatProjek.get("NAMA_AGENSI").toString());
			id_agensi_myetapp_mp = (hash_maklumatProjek.get("ID_AGENSI_MYETAPP").toString());
			jenis_pengambilan_segera_mp = (hash_maklumatProjek.get("JENIS_PENGMABILAN_SEGERA").toString());
			flag_permohonan_segera_mp = (hash_maklumatProjek.get("FLAG_PERMOHONAN_SEGERA").toString());			
			kod_agensi = (hash_maklumatProjek.get("KOD_KEMENTERIAN").toString());
			kod_kementerian = (hash_maklumatProjek.get("KOD_AGENSI").toString());
			
			alamat1_kjp = (hash_maklumatProjek.get("ALAMAT1_KJP").toString());
			alamat2_kjp = (hash_maklumatProjek.get("ALAMAT2_KJP").toString());
			alamat3_kjp = (hash_maklumatProjek.get("ALAMAT3_KJP").toString());
			poskod_kjp = (hash_maklumatProjek.get("POSKOD_KJP").toString());
			kod_negeri_kjp = (hash_maklumatProjek.get("KOD_NEGERI_KJP").toString());
			
			Integer checkMaklumatPPTdihantar = logic.derafMaklumatPengambilanLog_COUNT(id_fail_etapp, jenis_skrin_etapp, db,return_new_turutan (id_fail_etapp,id_permohonan, id_hakmilik_etapp, jenis_skrin_etapp,db));
			if (checkMaklumatPPTdihantar == 0) {
				Tblintpptmaklumatpengambilan tblintpptmaklumatpengambilan[] = new Tblintpptmaklumatpengambilan[1];
				tblintpptmaklumatpengambilan[0] =  addMaklumatPengambilan_return_obj( tarikh_permohonan_mp, nama_kementerian_mp,  tujuan_dalam_english_mp,  tujuan_mp,  no_fail_jkptg_mp,  kod_negeri_pengambilan_mp, 
						 nama_negeri_pengambilan_mp,  kod_daerah_pengambilan_mp,  nama_daerah_pengambilan_mp,  jenis_pengambilan_mp,  jenis_projek_pengambilan_mp,  no_rujukan_surat_kjp_mp, 
						 tarikh_surat_kjp_mp,  no_rujukan_ptg_mp,  no_rujukan_ptd_mp,  id_kementerian_myetapp_mp,  nama_agensi_mp,  id_agensi_myetapp_mp,  jenis_pengambilan_segera_mp, flag_permohonan_segera_mp, 
						turutan_mp, flag_proses_mp, session,kod_agensi,kod_kementerian,alamat1_kjp,alamat2_kjp,alamat3_kjp,poskod_kjp,kod_negeri_kjp);
				
				addDerafMaklumatPengambilan_List(tblintpptmaklumatpengambilan,id_fail_etapp, session, db);
				saveLogMaklumatPengambilan(id_fail_etapp, jenis_skrin_etapp,
						session, db,turutan);
			}				
			
					
			}		
			
			//close maklumat pengambilan tanah
			
			
			/*
			//open hantar data deraf MMK
			if (jenis_skrin_etapp.equals("TarikBalik"))
			{
				hash_maklumatTarikBalik = logic.getMaklumatTarikBalik(id_penarikan,db);
				hash_maklumatProjek = logic.getMaklumatProjek(id_fail_etapp, db);
				NO_FAIL_DERAF = (hash_maklumatProjek.get("NO_FAIL").toString());
				ID_NEGERI = (hash_maklumatProjek.get("ID_NEGERI").toString());
				NO_RUJUKAN_TARIKBALIK = (hash_maklumatTarikBalik.get("NO_PENARIKANBALIK").toString());
				
					Integer checkMMKPBdihantar = logic.derafMmkPBLog_COUNT(id_penarikan, jenis_skrin_etapp, db);
					if (checkMMKPBdihantar == 0) {
						
						Hashtable hash_maklumatMMK = null;
						hash_maklumatMMK = logic.getMaklumatMMK(id_penarikan,id_fail_etapp,jenis_skrin_etapp,db);
						if(hash_maklumatMMK.size()>0)
						{
						String KETERANGAN_SIDANG = hash_maklumatMMK.get("KETERANGAN_SIDANG").toString();
						String WAKTU_SIDANG = hash_maklumatMMK.get("WAKTU_SIDANG").toString();
						String JENIS_WAKTU_SIDANG = hash_maklumatMMK.get("JENIS_WAKTU_SIDANG").toString();
						String WAKTU_SIDANG_KETERANGAN = hash_maklumatMMK.get("WAKTU_SIDANG_KETERANGAN").toString();
						String TARIKH_SIDANG = hash_maklumatMMK.get("TARIKH_SIDANG").toString();
						String TEMPAT_SIDANG = hash_maklumatMMK.get("TEMPAT_SIDANG").toString();
						String TAJUK = hash_maklumatMMK.get("TAJUK").toString();
						
						addDerafMMKTAJUK(id_fail_etapp,jenis_skrin_etapp,NO_RUJUKAN_TARIKBALIK,NO_FAIL_DERAF, dateNow,
								KETERANGAN_SIDANG, WAKTU_SIDANG, JENIS_WAKTU_SIDANG, WAKTU_SIDANG_KETERANGAN, TARIKH_SIDANG, TEMPAT_SIDANG, TAJUK, 
								session, db);
						
						}
						
						if (ID_NEGERI.equals("5")) {
							saveFormatMMKN9_PB(id_fail_etapp,id_penarikan,
									jenis_skrin_etapp,NO_RUJUKAN_TARIKBALIK,NO_FAIL_DERAF, dateNow,
									db, "", session);
						}
						else if (ID_NEGERI.equals("4")) {
							saveFormatMMKMelaka_PB(id_fail_etapp,id_penarikan,
									jenis_skrin_etapp,NO_RUJUKAN_TARIKBALIK,NO_FAIL_DERAF, dateNow,
									db, "", session);
						}						
						saveLogDerafMMKPB(id_penarikan, jenis_skrin_etapp,session, db);
					}					
				
							
			}	
			else 
			*/
			
			
			if (jenis_skrin_etapp.equals("WartaS4") || jenis_skrin_etapp.equals("WartaS8")) {
				hash_maklumatProjek = logic.getMaklumatProjek(id_fail_etapp, db);
				NO_FAIL_DERAF = (hash_maklumatProjek.get("NO_FAIL").toString());
				hash_maklumatWarta = logic.getMaklumatWarta(id_fail_etapp, db);
				NO_WARTA = (hash_maklumatWarta.get("NO_WARTA").toString());
				TARIKH_WARTA = (hash_maklumatWarta.get("TARIKH_WARTA").toString());
			
				Integer checkWartadihantar = logic.derafMaklumatWartaLog_COUNT(
						id_fail_etapp, jenis_skrin_etapp, db);
				if (checkWartadihantar == 0) {
					Tblintpptwarta tblintpptwarta_arr[] = new Tblintpptwarta[1];
					tblintpptwarta_arr[0] = addDerafMaklumatWarta_return_obj(id_fail_etapp, jenis_skrin_etapp,NO_FAIL_DERAF, dateNow,
							NO_WARTA, TARIKH_WARTA,session, db,turutan);
					addDerafMaklumatWarta_List(tblintpptwarta_arr,id_fail_etapp, session, db);
					saveLogMaklumatWarta(id_fail_etapp, jenis_skrin_etapp,
							session, db,turutan);
				}				
				
				
			}else if (jenis_skrin_etapp.equals("BorangC") || jenis_skrin_etapp.equals("BorangA")) {
				hash_maklumatProjek = logic.getMaklumatProjek(id_fail_etapp, db);
				NO_FAIL_DERAF = (hash_maklumatProjek.get("NO_FAIL").toString());
				ID_NEGERI = (hash_maklumatProjek.get("ID_NEGERI").toString());
				
				if (ID_NEGERI.equals("5")) {
					Integer checkMMKdihantar = logic.derafMmkLog_COUNT(
							id_fail_etapp, jenis_skrin_etapp, db,turutan);
					if (checkMMKdihantar == 0) {
						Hashtable hash_maklumatMMK = null;
						hash_maklumatMMK = logic.getMaklumatMMK(id_penarikan,id_fail_etapp,jenis_skrin_etapp,db);
						if(hash_maklumatMMK.size()>0)
						{
						String KETERANGAN_SIDANG = hash_maklumatMMK.get("KETERANGAN_SIDANG").toString();
						String WAKTU_SIDANG = hash_maklumatMMK.get("WAKTU_SIDANG").toString();
						String JENIS_WAKTU_SIDANG = hash_maklumatMMK.get("JENIS_WAKTU_SIDANG").toString();
						String WAKTU_SIDANG_KETERANGAN = hash_maklumatMMK.get("WAKTU_SIDANG_KETERANGAN").toString();
						String TARIKH_SIDANG = hash_maklumatMMK.get("TARIKH_SIDANG").toString();
						String TEMPAT_SIDANG = hash_maklumatMMK.get("TEMPAT_SIDANG").toString();
						String JUM_LUAS_AMBIL = hash_maklumatMMK.get("JUM_LUAS_AMBIL").toString();
						String NAMA_DAERAH = hash_maklumatMMK.get("NAMA_DAERAH").toString();
						String TUJUAN = hash_maklumatMMK.get("TUJUAN").toString();
						String NAMA_ROJEK = hash_maklumatMMK.get("NAMA_ROJEK").toString();
						//String TAJUK = hash_maklumatMMK.get("TAJUK").toString();		
						String NAMA_MUKIM = getNamaMukim(id_permohonan).toUpperCase();
						String TAJUK = "PERMOHONAN PENGAMBILAN TANAH YANG BERKEMUNGKINAN AKAN DIAMBIL DI ATAS KESELURUHAN LOT-LOT BERKENAAN DI " +NAMA_MUKIM+
								" DI DAERAH "+NAMA_DAERAH+" SELUAS "+JUM_LUAS_AMBIL+" HEKTAR/METER PERSEGI UNTUK "+NAMA_ROJEK;
						
						
						
						Tblintpptderafmmktajuk tblintpptderafmmktajuk_arr[] = new Tblintpptderafmmktajuk[1];
						tblintpptderafmmktajuk_arr[0] = addDerafMMKTAJUK_return_obj(id_fail_etapp,jenis_skrin_etapp,"",NO_FAIL_DERAF, dateNow,
								KETERANGAN_SIDANG, WAKTU_SIDANG, JENIS_WAKTU_SIDANG, WAKTU_SIDANG_KETERANGAN, TARIKH_SIDANG, TEMPAT_SIDANG, TAJUK.toUpperCase(), 
								session, db,turutan);
						addDerafMMKTAJUK_List(tblintpptderafmmktajuk_arr,id_fail_etapp, session, db);
						//saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,session, db,turutan);
						}

						if (jenis_skrin_etapp.equals("BorangC")) {						
							saveFormatMMKN9_S8(id_fail_etapp,
									jenis_skrin_etapp, NO_FAIL_DERAF, dateNow,
									db, "", session,turutan);
							saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,
									session, db,turutan);
						} else if (jenis_skrin_etapp.equals("BorangA")) {
							
							saveFormatMMKN9_S4(id_fail_etapp,
									jenis_skrin_etapp, NO_FAIL_DERAF, dateNow,
									db, "", session,turutan);
							saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,
									session, db,turutan);
						}
						
					}
				} else if (ID_NEGERI.equals("4")) {
					
					Integer checkMMKdihantar = logic.derafMmkLog_COUNT(
							id_fail_etapp, jenis_skrin_etapp, db,turutan);
					if (checkMMKdihantar == 0) {
						Hashtable hash_maklumatMMK = null;
						hash_maklumatMMK = logic.getMaklumatMMK(id_penarikan,id_fail_etapp,jenis_skrin_etapp,db);
						if(hash_maklumatMMK.size()>0)
						{
						String KETERANGAN_SIDANG = hash_maklumatMMK.get("KETERANGAN_SIDANG").toString();
						String WAKTU_SIDANG = hash_maklumatMMK.get("WAKTU_SIDANG").toString();
						String JENIS_WAKTU_SIDANG = hash_maklumatMMK.get("JENIS_WAKTU_SIDANG").toString();
						String WAKTU_SIDANG_KETERANGAN = hash_maklumatMMK.get("WAKTU_SIDANG_KETERANGAN").toString();
						String TARIKH_SIDANG = hash_maklumatMMK.get("TARIKH_SIDANG").toString();
						String TEMPAT_SIDANG = hash_maklumatMMK.get("TEMPAT_SIDANG").toString();
						String TAJUK = hash_maklumatMMK.get("TAJUK").toString();		
						
						Tblintpptderafmmktajuk tblintpptderafmmktajuk_arr[] = new Tblintpptderafmmktajuk[1];
						tblintpptderafmmktajuk_arr[0] = addDerafMMKTAJUK_return_obj(id_fail_etapp,jenis_skrin_etapp,"",NO_FAIL_DERAF, dateNow,
								KETERANGAN_SIDANG, WAKTU_SIDANG, JENIS_WAKTU_SIDANG, WAKTU_SIDANG_KETERANGAN, TARIKH_SIDANG, TEMPAT_SIDANG, TAJUK, 
								session, db,turutan);
						addDerafMMKTAJUK_List(tblintpptderafmmktajuk_arr,id_fail_etapp, session, db);
						//saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,session, db,turutan);
						}
						
						if (jenis_skrin_etapp.equals("BorangC")) {
							
							saveFormatMMKMelaka_S8(id_fail_etapp,
									jenis_skrin_etapp, NO_FAIL_DERAF, dateNow,
									db, "", session,turutan);
							saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,
									session, db,turutan);
						} else if (jenis_skrin_etapp.equals("BorangA")) {
							
							saveFormatMMKMelaka_S4(id_fail_etapp,
									jenis_skrin_etapp, NO_FAIL_DERAF, dateNow,
									db, "", session,turutan);
							saveLogDerafMMK(id_fail_etapp, jenis_skrin_etapp,
									session, db,turutan);
						}
						
					}
				}
			}
			
			
			
			//close hantar data deraf MMK
			

			//open hantar data hakmilik
			String id_hakmilik_temp = "";
			String flag_proses = "";
			String no_warta = "";
			String tarikh_warta = "";
			String luas_asal = "";
			String kod_luas_asal = "";
			String luas_ambil = "";
			String kod_luas_ambil = "";
			String no_fail_jkptg = "";
			String no_lot = "";
			String kod_unit_hakmilik = "";
			String no_hakmilik = "";
			String tarikh_borangk = "";
			String tarikh_borangi = "";
			String status_borangk = "";
			String no_rujukan_tarikhbalik = "";
			String sebab_tarikbalik = "";
			String no_warta_tarikbalik = "";
			String tarikh_warta_tarikbalik = "";
			String no_lot_baru = "";
			String no_syit = "";
			String no_pa = "";
			String no_pu = "";
			String luas_pa = "";
			String kod_luas_pa = "";
			String tarikh_terima_data = "";
			String kod_negeri = "";
			String kod_daerah = "";
			String kod_mukim = "";

			String tajuk_dokumen = "";
			String nama_dokumen = "";
			String jenis_fail = "";
			FileItem content = null;
			String id_hakmilik_temp_doc = "";
			String kod_unit_hakmilik_doc = "";
			String no_hakmilik_doc = "";
			String kod_negeri_doc = "";
			String kod_daerah_doc = "";
			String kod_mukim_doc = "";
			
			if (jenis_skrin_etapp.equals("BorangC")
					|| jenis_skrin_etapp.equals("BorangA") || jenis_skrin_etapp.equals("WartaS8")) {
				List listSenaraiLotBorangC_PULL = logic
						.listSenaraiLotBorangC_PULL(id_fail_etapp, db);
				
				Tblintppthakmilik tblintppthakmilik_arr[] = new Tblintppthakmilik[listSenaraiLotBorangC_PULL.size()];				
				for (int i = 0; i < listSenaraiLotBorangC_PULL.size(); i++) {
					Hashtable hashSenaraiLotBorangC = (Hashtable) listSenaraiLotBorangC_PULL
							.get(i);
					String NO_HAKMILIK = (hashSenaraiLotBorangC.get(
							"NO_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangC.get("NO_HAKMILIK")
									.toString());
					String ID_HAKMILIK = (hashSenaraiLotBorangC.get(
							"ID_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangC.get("ID_HAKMILIK")
									.toString());
					String KOD_JENIS_HAKMILIK = (hashSenaraiLotBorangC.get(
							"KOD_JENIS_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangC.get("KOD_JENIS_HAKMILIK")
									.toString());
					String UNIT_LUAS_ASAL = (hashSenaraiLotBorangC.get(
							"UNIT_LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangC.get("UNIT_LUAS_ASAL")
									.toString());
					String UNIT_LUAS_AMBIL = (hashSenaraiLotBorangC.get(
							"UNIT_LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangC.get("UNIT_LUAS_AMBIL")
									.toString());
					String LUAS_ASAL = (hashSenaraiLotBorangC.get("LUAS_ASAL")
							.toString() == null ? "" : hashSenaraiLotBorangC
							.get("LUAS_ASAL").toString());
					String LUAS_AMBIL = (hashSenaraiLotBorangC
							.get("LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangC.get("LUAS_AMBIL")
									.toString());
					String NO_FAIL_JKPTG = (hashSenaraiLotBorangC.get(
							"NO_FAIL_JKPTG").toString() == null ? ""
							: hashSenaraiLotBorangC.get("NO_FAIL_JKPTG")
									.toString());
					String NO_LOT = (hashSenaraiLotBorangC.get("NO_LOT")
							.toString() == null ? "" : hashSenaraiLotBorangC
							.get("NO_LOT").toString());
					String KOD_NEGERI = (hashSenaraiLotBorangC
							.get("KOD_NEGERI").toString() == null ? ""
							: hashSenaraiLotBorangC.get("KOD_NEGERI")
									.toString());
					String KOD_DAERAH = (hashSenaraiLotBorangC
							.get("KOD_DAERAH").toString() == null ? ""
							: hashSenaraiLotBorangC.get("KOD_DAERAH")
									.toString());
					String KOD_MUKIM = (hashSenaraiLotBorangC.get("KOD_MUKIM")
							.toString() == null ? "" : hashSenaraiLotBorangC
							.get("KOD_MUKIM").toString());
					String ID_HAKMILIK_ETAPP = (hashSenaraiLotBorangC.get(
							"ID_HAKMILIK_ETAPP").toString() == null ? ""
							: hashSenaraiLotBorangC.get("ID_HAKMILIK_ETAPP")
									.toString());
					myLogger.info("**************ID_HAKMILIK*************"
							+ ID_HAKMILIK);
					id_hakmilik_temp = ID_HAKMILIK;
					flag_proses = jenis_skrin_etapp;
					no_warta = NO_WARTA;
					tarikh_warta = TARIKH_WARTA;
					luas_asal = LUAS_ASAL;
					kod_luas_asal = UNIT_LUAS_ASAL;
					luas_ambil = LUAS_AMBIL;
					kod_luas_ambil = UNIT_LUAS_AMBIL;
					no_fail_jkptg = NO_FAIL_JKPTG;
					no_lot = NO_LOT;
					kod_unit_hakmilik = KOD_JENIS_HAKMILIK;
					no_hakmilik = NO_HAKMILIK;
					tarikh_terima_data = dateNow;
					kod_negeri = KOD_NEGERI;
					kod_daerah = KOD_DAERAH;
					kod_mukim = KOD_MUKIM;
					id_hakmilik_etapp = ID_HAKMILIK_ETAPP;
					Integer checkHakmilikHantar = logic
							.hakmilikLog_TelahDihantar(id_fail_etapp,
									id_hakmilik_etapp, jenis_skrin_etapp, db,
									id_penarikan,turutan);
					if (checkHakmilikHantar == 0) {							
						tblintppthakmilik_arr[i]= addHakmilik_return_obj(id_hakmilik_temp, flag_proses, no_warta,
								tarikh_warta, luas_asal, kod_luas_asal,
								luas_ambil, kod_luas_ambil, no_fail_jkptg,
								no_lot, kod_unit_hakmilik, no_hakmilik,
								tarikh_borangk, status_borangk,
								no_rujukan_tarikhbalik, sebab_tarikbalik,
								no_warta_tarikbalik, tarikh_warta_tarikbalik,
								no_lot_baru, no_syit, no_pa, no_pu, luas_pa,
								kod_luas_pa, tarikh_terima_data, kod_negeri,
								kod_daerah, kod_mukim, id_fail_etapp,
								id_hakmilik_etapp, jenis_skrin_etapp, session,
								db, id_penarikan, tarikh_borangi,turutan);					
					}
				}
				
				addHakmilik_List(tblintppthakmilik_arr,id_fail_etapp, session, db,turutan);

			} else if (jenis_skrin_etapp.equals("BorangI")) {
				List listSenaraiLotBorangI_PULL = logic
						.listSenaraiLotBorangI_PULL(id_fail_etapp, db);
				Tblintppthakmilik tblintppthakmilik_arr[] = new Tblintppthakmilik[listSenaraiLotBorangI_PULL.size()];
				for (int i = 0; i < listSenaraiLotBorangI_PULL.size(); i++) {
					Hashtable hashSenaraiLotBorangI = (Hashtable) listSenaraiLotBorangI_PULL
							.get(i);
					String NO_HAKMILIK = (hashSenaraiLotBorangI.get(
							"NO_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangI.get("NO_HAKMILIK")
									.toString());
					String ID_HAKMILIK = (hashSenaraiLotBorangI.get(
							"ID_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangI.get("ID_HAKMILIK")
									.toString());
					String KOD_JENIS_HAKMILIK = (hashSenaraiLotBorangI.get(
							"KOD_JENIS_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangI.get("KOD_JENIS_HAKMILIK")
									.toString());
					String UNIT_LUAS_ASAL = (hashSenaraiLotBorangI.get(
							"UNIT_LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangI.get("UNIT_LUAS_ASAL")
									.toString());
					String UNIT_LUAS_AMBIL = (hashSenaraiLotBorangI.get(
							"UNIT_LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangI.get("UNIT_LUAS_AMBIL")
									.toString());
					String LUAS_ASAL = (hashSenaraiLotBorangI.get("LUAS_ASAL")
							.toString() == null ? "" : hashSenaraiLotBorangI
							.get("LUAS_ASAL").toString());
					String LUAS_AMBIL = (hashSenaraiLotBorangI
							.get("LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangI.get("LUAS_AMBIL")
									.toString());
					String NO_FAIL_JKPTG = (hashSenaraiLotBorangI.get(
							"NO_FAIL_JKPTG").toString() == null ? ""
							: hashSenaraiLotBorangI.get("NO_FAIL_JKPTG")
									.toString());
					String NO_LOT = (hashSenaraiLotBorangI.get("NO_LOT")
							.toString() == null ? "" : hashSenaraiLotBorangI
							.get("NO_LOT").toString());
					String KOD_NEGERI = (hashSenaraiLotBorangI
							.get("KOD_NEGERI").toString() == null ? ""
							: hashSenaraiLotBorangI.get("KOD_NEGERI")
									.toString());
					String KOD_DAERAH = (hashSenaraiLotBorangI
							.get("KOD_DAERAH").toString() == null ? ""
							: hashSenaraiLotBorangI.get("KOD_DAERAH")
									.toString());
					String KOD_MUKIM = (hashSenaraiLotBorangI.get("KOD_MUKIM")
							.toString() == null ? "" : hashSenaraiLotBorangI
							.get("KOD_MUKIM").toString());
					String ID_HAKMILIK_ETAPP = (hashSenaraiLotBorangI.get(
							"ID_HAKMILIK_ETAPP").toString() == null ? ""
							: hashSenaraiLotBorangI.get("ID_HAKMILIK_ETAPP")
									.toString());
					String TARIKH_BORANGI = (hashSenaraiLotBorangI.get(
							"TARIKH_BORANGI").toString() == null ? ""
							: hashSenaraiLotBorangI.get("TARIKH_BORANGI")
									.toString());

					myLogger.info("**************ID_HAKMILIK*************"
							+ ID_HAKMILIK);
					id_hakmilik_temp = ID_HAKMILIK;
					flag_proses = jenis_skrin_etapp;
					no_warta = NO_WARTA;
					tarikh_warta = TARIKH_WARTA;
					luas_asal = LUAS_ASAL;
					kod_luas_asal = UNIT_LUAS_ASAL;
					luas_ambil = LUAS_AMBIL;
					kod_luas_ambil = UNIT_LUAS_AMBIL;
					no_fail_jkptg = NO_FAIL_JKPTG;
					no_lot = NO_LOT;
					kod_unit_hakmilik = KOD_JENIS_HAKMILIK;
					no_hakmilik = NO_HAKMILIK;
					tarikh_terima_data = dateNow;
					kod_negeri = KOD_NEGERI;
					kod_daerah = KOD_DAERAH;
					kod_mukim = KOD_MUKIM;
					id_hakmilik_etapp = ID_HAKMILIK_ETAPP;
					tarikh_borangi = TARIKH_BORANGI;
					Integer checkHakmilikHantar = logic
							.hakmilikLog_TelahDihantar(id_fail_etapp,
									id_hakmilik_etapp, jenis_skrin_etapp, db,
									id_penarikan,turutan);
					if (checkHakmilikHantar == 0) {
						tblintppthakmilik_arr[i]= addHakmilik_return_obj(id_hakmilik_temp, flag_proses, no_warta,
								tarikh_warta, luas_asal, kod_luas_asal,
								luas_ambil, kod_luas_ambil, no_fail_jkptg,
								no_lot, kod_unit_hakmilik, no_hakmilik,
								tarikh_borangk, status_borangk,
								no_rujukan_tarikhbalik, sebab_tarikbalik,
								no_warta_tarikbalik, tarikh_warta_tarikbalik,
								no_lot_baru, no_syit, no_pa, no_pu, luas_pa,
								kod_luas_pa, tarikh_terima_data, kod_negeri,
								kod_daerah, kod_mukim, id_fail_etapp,
								id_hakmilik_etapp, jenis_skrin_etapp, session,
								db, id_penarikan, tarikh_borangi,turutan);		

					}
				}
				addHakmilik_List(tblintppthakmilik_arr,id_fail_etapp, session, db,turutan);

			} /*else if (jenis_skrin_etapp.equals("TarikBalik")) {
				List listSenaraiLotTarikBalik_PULL = logic
						.listSenaraiLotTarikBalik_PULL(id_fail_etapp, db,
								id_penarikan);
				Tblintppthakmilik tblintppthakmilik_arr[] = new Tblintppthakmilik[listSenaraiLotTarikBalik_PULL.size()];
				for (int i = 0; i < listSenaraiLotTarikBalik_PULL.size(); i++) {
					Hashtable hashSenaraiLotTarikBalik_PULL = (Hashtable) listSenaraiLotTarikBalik_PULL
							.get(i);
					String NO_HAKMILIK = (hashSenaraiLotTarikBalik_PULL.get(
							"NO_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("NO_HAKMILIK")
									.toString());
					String ID_HAKMILIK = (hashSenaraiLotTarikBalik_PULL.get(
							"ID_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("ID_HAKMILIK")
									.toString());
					String KOD_JENIS_HAKMILIK = (hashSenaraiLotTarikBalik_PULL
							.get("KOD_JENIS_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"KOD_JENIS_HAKMILIK").toString());
					String UNIT_LUAS_ASAL = (hashSenaraiLotTarikBalik_PULL.get(
							"UNIT_LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"UNIT_LUAS_ASAL").toString());
					String UNIT_LUAS_AMBIL = (hashSenaraiLotTarikBalik_PULL
							.get("UNIT_LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"UNIT_LUAS_AMBIL").toString());
					String LUAS_ASAL = (hashSenaraiLotTarikBalik_PULL.get(
							"LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("LUAS_ASAL")
									.toString());
					String LUAS_AMBIL = (hashSenaraiLotTarikBalik_PULL.get(
							"LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("LUAS_AMBIL")
									.toString());
					String NO_FAIL_JKPTG = (hashSenaraiLotTarikBalik_PULL.get(
							"NO_FAIL_JKPTG").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL
									.get("NO_FAIL_JKPTG").toString());
					String NO_LOT = (hashSenaraiLotTarikBalik_PULL
							.get("NO_LOT").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("NO_LOT")
									.toString());
					String KOD_NEGERI = (hashSenaraiLotTarikBalik_PULL.get(
							"KOD_NEGERI").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("KOD_NEGERI")
									.toString());
					String KOD_DAERAH = (hashSenaraiLotTarikBalik_PULL.get(
							"KOD_DAERAH").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("KOD_DAERAH")
									.toString());
					String KOD_MUKIM = (hashSenaraiLotTarikBalik_PULL.get(
							"KOD_MUKIM").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get("KOD_MUKIM")
									.toString());
					String ID_HAKMILIK_ETAPP = (hashSenaraiLotTarikBalik_PULL
							.get("ID_HAKMILIK_ETAPP").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"ID_HAKMILIK_ETAPP").toString());
					String NO_PENARIKANBALIK = (hashSenaraiLotTarikBalik_PULL
							.get("NO_PENARIKANBALIK").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"NO_PENARIKANBALIK").toString());
					String SEBAB_PENARIKANBALIK = (hashSenaraiLotTarikBalik_PULL
							.get("SEBAB_PENARIKANBALIK").toString() == null ? ""
							: hashSenaraiLotTarikBalik_PULL.get(
									"SEBAB_PENARIKANBALIK").toString());

					myLogger.info("**************ID_HAKMILIK*************"
							+ ID_HAKMILIK);

					no_rujukan_tarikhbalik = NO_PENARIKANBALIK;
					id_hakmilik_temp = ID_HAKMILIK;
					flag_proses = jenis_skrin_etapp;
					// no_warta = NO_WARTA;
					// tarikh_warta = TARIKH_WARTA;
					luas_asal = LUAS_ASAL;
					kod_luas_asal = UNIT_LUAS_ASAL;
					luas_ambil = LUAS_AMBIL;
					kod_luas_ambil = UNIT_LUAS_AMBIL;
					no_fail_jkptg = NO_FAIL_JKPTG;
					no_lot = NO_LOT;
					kod_unit_hakmilik = KOD_JENIS_HAKMILIK;
					no_hakmilik = NO_HAKMILIK;
					tarikh_terima_data = dateNow;
					kod_negeri = KOD_NEGERI;
					kod_daerah = KOD_DAERAH;
					kod_mukim = KOD_MUKIM;
					id_hakmilik_etapp = ID_HAKMILIK_ETAPP;
					sebab_tarikbalik = SEBAB_PENARIKANBALIK;

					Integer checkHakmilikHantar = logic
							.hakmilikLog_TelahDihantar(id_fail_etapp,
									id_hakmilik_etapp, jenis_skrin_etapp, db,
									id_penarikan);

					if (checkHakmilikHantar == 0) {
						tblintppthakmilik_arr[i]= addHakmilik_return_obj(id_hakmilik_temp, flag_proses, no_warta,
								tarikh_warta, luas_asal, kod_luas_asal,
								luas_ambil, kod_luas_ambil, no_fail_jkptg,
								no_lot, kod_unit_hakmilik, no_hakmilik,
								tarikh_borangk, status_borangk,
								no_rujukan_tarikhbalik, sebab_tarikbalik,
								no_warta_tarikbalik, tarikh_warta_tarikbalik,
								no_lot_baru, no_syit, no_pa, no_pu, luas_pa,
								kod_luas_pa, tarikh_terima_data, kod_negeri,
								kod_daerah, kod_mukim, id_fail_etapp,
								id_hakmilik_etapp, jenis_skrin_etapp, session,
								db, id_penarikan, tarikh_borangi,turutan);		
					}
				}
				addHakmilik_List(tblintppthakmilik_arr,id_fail_etapp, session, db);

			} */else if (jenis_skrin_etapp.equals("BorangK") || jenis_skrin_etapp.equals("SijilUkur")) {
				List listSenaraiLotBorangK_PULL = logic
						.listSenaraiLotBorangK_PULL(id_fail_etapp,
								id_hakmilik_etapp, db);
				Tblintppthakmilik tblintppthakmilik_arr[] = new Tblintppthakmilik[listSenaraiLotBorangK_PULL.size()];
				for (int i = 0; i < listSenaraiLotBorangK_PULL.size(); i++) {
					Hashtable hashSenaraiLotBorangK_PULL = (Hashtable) listSenaraiLotBorangK_PULL
							.get(i);
					String NO_HAKMILIK = (hashSenaraiLotBorangK_PULL.get(
							"NO_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_HAKMILIK")
									.toString());
					String ID_HAKMILIK = (hashSenaraiLotBorangK_PULL.get(
							"ID_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("ID_HAKMILIK")
									.toString());
					String KOD_JENIS_HAKMILIK = (hashSenaraiLotBorangK_PULL
							.get("KOD_JENIS_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get(
									"KOD_JENIS_HAKMILIK").toString());
					String UNIT_LUAS_ASAL = (hashSenaraiLotBorangK_PULL.get(
							"UNIT_LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("UNIT_LUAS_ASAL")
									.toString());
					String UNIT_LUAS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"UNIT_LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("UNIT_LUAS_AMBIL")
									.toString());
					String LUAS_ASAL = (hashSenaraiLotBorangK_PULL.get(
							"LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("LUAS_ASAL")
									.toString());
					String LUAS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("LUAS_AMBIL")
									.toString());
					String NO_FAIL_JKPTG = (hashSenaraiLotBorangK_PULL.get(
							"NO_FAIL_JKPTG").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_FAIL_JKPTG")
									.toString());
					String NO_LOT = (hashSenaraiLotBorangK_PULL.get("NO_LOT")
							.toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_LOT")
									.toString());
					String KOD_NEGERI = (hashSenaraiLotBorangK_PULL.get(
							"KOD_NEGERI").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_NEGERI")
									.toString());
					String KOD_DAERAH = (hashSenaraiLotBorangK_PULL.get(
							"KOD_DAERAH").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_DAERAH")
									.toString());
					String KOD_MUKIM = (hashSenaraiLotBorangK_PULL.get(
							"KOD_MUKIM").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_MUKIM")
									.toString());
					String ID_HAKMILIK_ETAPP = (hashSenaraiLotBorangK_PULL.get(
							"ID_HAKMILIK_ETAPP").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get(
									"ID_HAKMILIK_ETAPP").toString());
					String TARIKH_BORANGK = (hashSenaraiLotBorangK_PULL.get(
							"TARIKH_BORANGK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("TARIKH_BORANGK")
									.toString());
					String STATUS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"STATUS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("STATUS_AMBIL")
									.toString());

					id_hakmilik_temp = ID_HAKMILIK;
					flag_proses = jenis_skrin_etapp;
					no_warta = NO_WARTA;
					tarikh_warta = TARIKH_WARTA;
					luas_asal = LUAS_ASAL;
					kod_luas_asal = UNIT_LUAS_ASAL;
					luas_ambil = LUAS_AMBIL;
					kod_luas_ambil = UNIT_LUAS_AMBIL;
					no_fail_jkptg = NO_FAIL_JKPTG;
					no_lot = NO_LOT;
					kod_unit_hakmilik = KOD_JENIS_HAKMILIK;
					no_hakmilik = NO_HAKMILIK;
					tarikh_terima_data = dateNow;
					kod_negeri = KOD_NEGERI;
					kod_daerah = KOD_DAERAH;
					kod_mukim = KOD_MUKIM;
					id_hakmilik_etapp = ID_HAKMILIK_ETAPP;
					tarikh_borangk = TARIKH_BORANGK;
					status_borangk = STATUS_AMBIL;

					Integer checkHakmilikHantar = logic
							.hakmilikLog_TelahDihantar(id_fail_etapp,
									id_hakmilik_etapp, jenis_skrin_etapp, db,
									id_penarikan,turutan);

					if (checkHakmilikHantar == 0) {
						tblintppthakmilik_arr[i]= addHakmilik_return_obj(id_hakmilik_temp, flag_proses, no_warta,
								tarikh_warta, luas_asal, kod_luas_asal,
								luas_ambil, kod_luas_ambil, no_fail_jkptg,
								no_lot, kod_unit_hakmilik, no_hakmilik,
								tarikh_borangk, status_borangk,
								no_rujukan_tarikhbalik, sebab_tarikbalik,
								no_warta_tarikbalik, tarikh_warta_tarikbalik,
								no_lot_baru, no_syit, no_pa, no_pu, luas_pa,
								kod_luas_pa, tarikh_terima_data, kod_negeri,
								kod_daerah, kod_mukim, id_fail_etapp,
								id_hakmilik_etapp, jenis_skrin_etapp, session,
								db, id_penarikan, tarikh_borangi,turutan);		
					}

				}
				addHakmilik_List(tblintppthakmilik_arr,id_fail_etapp, session, db,turutan);

			} else if (jenis_skrin_etapp.equals("PU")) {
				List listSenaraiLotBorangK_PULL = logic
						.listSenaraiLotBorangK_PULL(id_fail_etapp,
								id_hakmilik_etapp, db);
				Tblintppthakmilik tblintppthakmilik_arr[] = new Tblintppthakmilik[listSenaraiLotBorangK_PULL.size()];
				for (int i = 0; i < listSenaraiLotBorangK_PULL.size(); i++) {
					Hashtable hashSenaraiLotBorangK_PULL = (Hashtable) listSenaraiLotBorangK_PULL
							.get(i);
					String NO_HAKMILIK = (hashSenaraiLotBorangK_PULL.get(
							"NO_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_HAKMILIK")
									.toString());
					String ID_HAKMILIK = (hashSenaraiLotBorangK_PULL.get(
							"ID_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("ID_HAKMILIK")
									.toString());
					String KOD_JENIS_HAKMILIK = (hashSenaraiLotBorangK_PULL
							.get("KOD_JENIS_HAKMILIK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get(
									"KOD_JENIS_HAKMILIK").toString());
					String UNIT_LUAS_ASAL = (hashSenaraiLotBorangK_PULL.get(
							"UNIT_LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("UNIT_LUAS_ASAL")
									.toString());
					String UNIT_LUAS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"UNIT_LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("UNIT_LUAS_AMBIL")
									.toString());
					String LUAS_ASAL = (hashSenaraiLotBorangK_PULL.get(
							"LUAS_ASAL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("LUAS_ASAL")
									.toString());
					String LUAS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"LUAS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("LUAS_AMBIL")
									.toString());
					String NO_FAIL_JKPTG = (hashSenaraiLotBorangK_PULL.get(
							"NO_FAIL_JKPTG").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_FAIL_JKPTG")
									.toString());
					String NO_LOT = (hashSenaraiLotBorangK_PULL.get("NO_LOT")
							.toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("NO_LOT")
									.toString());
					String KOD_NEGERI = (hashSenaraiLotBorangK_PULL.get(
							"KOD_NEGERI").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_NEGERI")
									.toString());
					String KOD_DAERAH = (hashSenaraiLotBorangK_PULL.get(
							"KOD_DAERAH").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_DAERAH")
									.toString());
					String KOD_MUKIM = (hashSenaraiLotBorangK_PULL.get(
							"KOD_MUKIM").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("KOD_MUKIM")
									.toString());
					String ID_HAKMILIK_ETAPP = (hashSenaraiLotBorangK_PULL.get(
							"ID_HAKMILIK_ETAPP").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get(
									"ID_HAKMILIK_ETAPP").toString());
					String TARIKH_BORANGK = (hashSenaraiLotBorangK_PULL.get(
							"TARIKH_BORANGK").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("TARIKH_BORANGK")
									.toString());
					String STATUS_AMBIL = (hashSenaraiLotBorangK_PULL.get(
							"STATUS_AMBIL").toString() == null ? ""
							: hashSenaraiLotBorangK_PULL.get("STATUS_AMBIL")
									.toString());

					id_hakmilik_temp = ID_HAKMILIK;
					flag_proses = jenis_skrin_etapp;
					no_warta = NO_WARTA;
					tarikh_warta = TARIKH_WARTA;
					luas_asal = LUAS_ASAL;
					kod_luas_asal = UNIT_LUAS_ASAL;
					luas_ambil = LUAS_AMBIL;
					kod_luas_ambil = UNIT_LUAS_AMBIL;
					no_fail_jkptg = NO_FAIL_JKPTG;
					no_lot = NO_LOT;
					kod_unit_hakmilik = KOD_JENIS_HAKMILIK;
					no_hakmilik = NO_HAKMILIK;
					tarikh_terima_data = dateNow;
					kod_negeri = KOD_NEGERI;
					kod_daerah = KOD_DAERAH;
					kod_mukim = KOD_MUKIM;
					id_hakmilik_etapp = ID_HAKMILIK_ETAPP;
					tarikh_borangk = TARIKH_BORANGK;
					status_borangk = STATUS_AMBIL;

					Hashtable hash_maklumatPU = null;
					hash_maklumatPU = logic.getMaklumatPU(id_fail_etapp,
							id_hakmilik_etapp, db);
					if (hash_maklumatPU.size() > 0) {
						no_lot_baru = (hash_maklumatPU.get("NO_LOT_BARU")
								.toString());
						no_syit = (hash_maklumatPU.get("NO_SYIT").toString());
						no_pa = (hash_maklumatPU.get("NO_PA").toString());
						no_pu = (hash_maklumatPU.get("NO_PU").toString());
						luas_pa = (hash_maklumatPU.get("LUAS_PA").toString());
						kod_luas_pa = (hash_maklumatPU.get("KOD_UNIT_LUAS_PA")
								.toString());
					}

					Integer checkHakmilikHantar = logic
							.hakmilikLog_TelahDihantar(id_fail_etapp,
									id_hakmilik_etapp, jenis_skrin_etapp, db,
									id_penarikan,turutan);

					if (checkHakmilikHantar == 0) {
						tblintppthakmilik_arr[i]= addHakmilik_return_obj(id_hakmilik_temp, flag_proses, no_warta,
								tarikh_warta, luas_asal, kod_luas_asal,
								luas_ambil, kod_luas_ambil, no_fail_jkptg,
								no_lot, kod_unit_hakmilik, no_hakmilik,
								tarikh_borangk, status_borangk,
								no_rujukan_tarikhbalik, sebab_tarikbalik,
								no_warta_tarikbalik, tarikh_warta_tarikbalik,
								no_lot_baru, no_syit, no_pa, no_pu, luas_pa,
								kod_luas_pa, tarikh_terima_data, kod_negeri,
								kod_daerah, kod_mukim, id_fail_etapp,
								id_hakmilik_etapp, jenis_skrin_etapp, session,
								db, id_penarikan, tarikh_borangi,turutan);		
					}

				}
				addHakmilik_List(tblintppthakmilik_arr,id_fail_etapp, session, db,turutan);				
			}
			
		} finally {
		//	if (db != null)
		//		db.close();
		}

	}

	private void saveFormatMMKMelaka_S8(String id_fail_etapp,
			String flag_proses, String no_fail_jkptg,
			String tarikh_terima_data, Db db, String hitButton,
			HttpSession session,Integer turutan) throws Exception {
		myLogger.info("************** addDerafMMK MASUK 1");
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "", db,flag_proses);
		
		Tblintpptderafmmk tblintpptderafmmk_arr[] = new Tblintpptderafmmk[listMMK.size()];
		myLogger.info("************** total saiz :"+listMMK.size());
		
		int count_add_item = 0;
		
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "TUJUAN", db,flag_proses);
		no_item = 1;
		myLogger.info("************** addDerafMMK MASUK 4");
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				myLogger.info("************** addDerafMMK MASUK 2 ");
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				
				count_add_item ++;
			}
		}
		no_item = 2;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"PERIHALPERMOHONAN", db,flag_proses);
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PERIHALTANAH",
				db,flag_proses);
		no_item = 3;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"PERIHALPEMOHON", db,flag_proses);
		no_item = 4;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ANGGARANPAMPASAN", db,flag_proses);
		no_item = 5;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ULASANTEKNIKAL", db,flag_proses);
		no_item = 6;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PANDANGANYB",
				db,flag_proses);
		no_item = 7;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PANDANGANPT",
				db,flag_proses);
		no_item = 8;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail_etapp, "PERAKUANPT", db,flag_proses);
		no_item = 9;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		
		myLogger.info("count_add_item 10 :"+count_add_item);
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ULASANPENGARAH", db,flag_proses);
		no_item = 10;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		
		myLogger.info("count_add_item :"+count_add_item);
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTG", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTD", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_UPT", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		addDerafMMK_List(tblintpptderafmmk_arr,id_fail_etapp, session, db);
	}

	private void saveFormatMMKMelaka_S4(String id_fail_etapp,
			String flag_proses, String no_fail_jkptg,
			String tarikh_terima_data, Db db, String hitButton,
			HttpSession session,Integer turutan) throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "", db,flag_proses);
		Tblintpptderafmmk tblintpptderafmmk_arr[] = new Tblintpptderafmmk[listMMK.size()];
		int count_add_item = 0;
		
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "TUJUAN", db,flag_proses);
		no_item = 1;
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		no_item = 2;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"PERIHALPERMOHONAN", db,flag_proses);
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PERIHALTANAH",
				db,flag_proses);
		no_item = 3;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"PERIHALPEMOHON", db,flag_proses);
		no_item = 4;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ANGGARANPAMPASAN", db,flag_proses);
		no_item = 5;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "LAPORANTANAH",
				db,flag_proses);
		no_item = 6;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"LAPORANTEKNIKAL", db,flag_proses);
		no_item = 7;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PANDANGANYB",
				db,flag_proses);
		no_item = 8;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail_etapp, "PERAKUANPT", db,flag_proses);
		no_item = 9;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		
		
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTG", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTD", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_UPT", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		addDerafMMK_List(tblintpptderafmmk_arr,id_fail_etapp, session, db);

	}
	
	private void saveFormatMMKN9_S8(String id_fail_etapp, String flag_proses,
			String no_fail_jkptg, String tarikh_terima_data, Db db,
			String hitButton, HttpSession session,Integer turutan) throws Exception {

		
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "", db,flag_proses);
		Tblintpptderafmmk tblintpptderafmmk_arr[] = new Tblintpptderafmmk[listMMK.size()];
		
		
		
		int count_add_item = 0;
		
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "TUJUAN", db,flag_proses);
		no_item = 1;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "LATARBELAKANG",
				db,flag_proses);
		no_item = 2;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PERIHALTANAH",
				db,flag_proses);
		no_item = 3;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail_etapp, "NILAITANAH", db,flag_proses);
		no_item = 4;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "SYOR", db,flag_proses);
		no_item = 5;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ULASANPENGARAH", db,flag_proses);
		no_item = 6;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "KEPUTUSAN", db,flag_proses);
		no_item = 7;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTG", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTD", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_UPT", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		
		addDerafMMK_List(tblintpptderafmmk_arr,id_fail_etapp, session, db);
	}

	private void saveFormatMMKN9_S4(String id_fail_etapp, String flag_proses,
			String no_fail_jkptg, String tarikh_terima_data, Db db,
			String hitButton, HttpSession session,Integer turutan) throws Exception {

		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "", db,flag_proses);
		Tblintpptderafmmk tblintpptderafmmk_arr[] = new Tblintpptderafmmk[listMMK.size()];
		int count_add_item = 0;
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "TUJUAN", db,flag_proses);
		no_item = 1;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "LATARBELAKANG",
				db,flag_proses);
		no_item = 2;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db, no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "PERIHALTANAH",
				db,flag_proses);
		no_item = 3;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail_etapp, "NILAITANAH", db,flag_proses);
		no_item = 4;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "SYOR", db,flag_proses);
		no_item = 5;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp,
				"ULASANPENGARAH", db,flag_proses);
		no_item = 6;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "KEPUTUSAN", db,flag_proses);
		no_item = 7;
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				double no_item_deraf = (((x + 1) * 0.1) + no_item);
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses, "",no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, no_item_deraf,
						FLAG_JENIS_MMK, session, db,no_item,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTG", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_PTD", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail_etapp, "NO_RUJUKAN_UPT", db,flag_proses);
		if (listMMK.size() > 0) {
			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				tblintpptderafmmk_arr[count_add_item] = addDerafMMK_return_obj(id_fail_etapp, flag_proses,"", no_fail_jkptg,
						tarikh_terima_data, KETERANGAN_SUBMMK, 0,
						FLAG_JENIS_MMK, session, db,0,turutan);
				count_add_item ++;
			}
		}
		
		addDerafMMK_List(tblintpptderafmmk_arr,id_fail_etapp, session, db);

	}
	
	public void addDerafMMK_List(Tblintpptderafmmk tblintpptderafmmk[],String id_fail_etapp, HttpSession session, Db db) throws Exception {		
		//point to server etanah
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertDerafMMK_byObject c2 = new InsertDerafMMK_byObject();
		InsertDerafMMK_byObjectE c2E = new InsertDerafMMK_byObjectE(); 		

		try {
			System.out.println("*******************************************try");
			c2.setTblintpptderafmmk(tblintpptderafmmk);			
			c2E.setInsertDerafMMK_byObject(c2);		
			stub.insertDerafMMK_byObject(c2E);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		
		
		
		//point to local server
		/*
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertDerafMMK_byObject c2 = new InsertDerafMMK_byObject();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptderafmmk(tblintpptderafmmk);	
			stub.insertDerafMMK_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
	}
	
	public void addDerafMMKTAJUK_List(Tblintpptderafmmktajuk tblintpptderafmmktajuk[],String id_fail_etapp, HttpSession session, Db db) throws Exception {		
		//point to server etanah
		
		System.out.println("tblintpptderafmmktajuk :: "+tblintpptderafmmktajuk);
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertDerafMMKTajuk_byObject c2 = new InsertDerafMMKTajuk_byObject();
		InsertDerafMMKTajuk_byObjectE c2E = new InsertDerafMMKTajuk_byObjectE();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptderafmmktajuk(tblintpptderafmmktajuk);
			c2E.setInsertDerafMMKTajuk_byObject(c2);
			stub.insertDerafMMKTajuk_byObject(c2E);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out.println("*******************************************finally");
		}
		
		
		
		
		//point to local server
		/*
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertDerafMMKTajuk_byObject c2 = new InsertDerafMMKTajuk_byObject();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptderafmmktajuk(tblintpptderafmmktajuk);
			stub.insertDerafMMKTajuk_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
		
	}
	
	
	public void addDerafMaklumatWarta_List(Tblintpptwarta tblintpptwarta[],String id_fail_etapp, HttpSession session, Db db) throws Exception {		
		//point to server etanah
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}	
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertMaklumatWarta_byObject c2 = new InsertMaklumatWarta_byObject();
		InsertMaklumatWarta_byObjectE c2E = new InsertMaklumatWarta_byObjectE();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptwarta(tblintpptwarta);
			c2E.setInsertMaklumatWarta_byObject(c2);
			stub.insertMaklumatWarta_byObject(c2E);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		
		
		
		//point to local server 
		/*
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertMaklumatWarta_byObject c2 = new InsertMaklumatWarta_byObject();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptwarta(tblintpptwarta);
			stub.insertMaklumatWarta_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
		
	}

	
	public void addDerafMaklumatPengambilan_List(Tblintpptmaklumatpengambilan tblintpptmaklumatpengambilan[],String id_fail_etapp, HttpSession session, Db db) throws Exception {		
		//point to server etanah
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertMaklumatPengambilan_byObject c2 = new InsertMaklumatPengambilan_byObject();
		InsertMaklumatPengambilan_byObjectE c2E = new InsertMaklumatPengambilan_byObjectE();
		try {
			System.out.println("*******************************************try");			
			c2.setTblintpptmaklumatpengambilan(tblintpptmaklumatpengambilan);
			c2E.setInsertMaklumatPengambilan_byObject(c2);
			stub.insertMaklumatPengambilan_byObject(c2E);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		
		//point to local server
		/*
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertMaklumatPengambilan_byObject c2 = new InsertMaklumatPengambilan_byObject();
		try {
			System.out.println("*******************************************try");			
			c2.setTblintpptmaklumatpengambilan(tblintpptmaklumatpengambilan);
			stub.insertMaklumatPengambilan_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
		
	}
	
	
	public void addDokumen_List(Tblintpptdokumen tblintpptdokumen[],String id_fail_etapp, HttpSession session, Db db) throws Exception {		
		//point to etanah sever
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertDokumen_byObject c2 = new InsertDokumen_byObject();
		InsertDokumen_byObjectE c2E = new InsertDokumen_byObjectE();
		try {
			System.out.println("*******************************************try");
			//c2.setTblintpptdokumen(tblintpptdokumen);
			//c2E.setInsertDokumen_byObject(c2);
			//stub.insertDokumen_byObject(c2E);
			for(int i =0;i<tblintpptdokumen.length;i++){
				Tblintpptdokumen tbl = tblintpptdokumen[i];
				DataHandler data =tbl.getContent_upload();
				byte [] b = IOUtils.toByteArray(data.getDataSource().getInputStream());
				String content = Base64.encodeToString(b);
				UploadDokumenE uplodDokumenE = new UploadDokumenE();
				UploadDokumen params = new UploadDokumen();
				params.setContent64(content);
				params.setJenis_format_fail(tbl.getJenis_format_fail());
				params.setJenisFail(tbl.getJenis_fail());
				
				String kat_doc_etanah = "";
				
				if(tbl.getKategori().equals("9") || tbl.getKategori().equals("10"))
				{
					kat_doc_etanah = "PLK";
				}
				else if(tbl.getKategori().equals("12"))
				{
					if(tbl.getKod_negeri().equals("05"))
					{
						kat_doc_etanah = "MMK";
					}
					else if(tbl.getKod_negeri().equals("04"))
					{
						kat_doc_etanah = "MMKN";
					}
					//n9 : mmk
					//melaka : mmkn
					
				}
				else if(tbl.getKategori().equals("2") || tbl.getKategori().equals("3") || tbl.getKategori().equals("4") || tbl.getKategori().equals("5") || tbl.getKategori().equals("6")
						|| tbl.getKategori().equals("7"))
				{
					kat_doc_etanah = "MMKN";
				}
								
				params.setKodDokumen(kat_doc_etanah);
				params.setNama_dokumen(tbl.getNama_dokumen());
				params.setNoFail(tbl.getNo_fail_jkptg());
				params.setTajuk_dokumen(tbl.getTajuk_dokumen());
				uplodDokumenE.setUploadDokumen(params);
				System.out.println("########### upload 1");
				stub.uploadDokumen(uplodDokumenE);
				System.out.println("########### upload 2");
			}

		} catch (RuntimeException e) {
			System.out.println("*******************************************catch"
							+ e);
		} finally {
			System.out.println("*******************************************finally");
		}
		
		
		
		
		//point to local server
	/*	
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertDokumen_byObject c2 = new InsertDokumen_byObject();
		try {
			System.out.println("*******************************************try");
			c2.setTblintpptdokumen(tblintpptdokumen);
			stub.insertDokumen_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
		
	}
	
	
	public void addHakmilik_List(Tblintppthakmilik tblintppthakmilik[],String id_fail_etapp, HttpSession session, Db db,Integer turutan) throws Exception {		
		//point to server etanah
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());			
		String url = "";		
		
		
		if(id_negeri.equals("4"))
		{
			url = "http://etanah.melaka.gov.my/etanahwsa/EtappPengambilanService";
		}
		else if(id_negeri.equals("5"))
		{
			url = "http://218.208.26.234/etanahwsa/EtappPengambilanService";
		}
		//url = "http://192.168.200.129:20928/etanahwsa/EtappPengambilanService";
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub(url);
		InsertHakmilikList_byObject c2 = new InsertHakmilikList_byObject();
		InsertHakmilikList_byObjectE c2E = new InsertHakmilikList_byObjectE();
		try {
			System.out.println("*******************************************try");
			c2.setTblintppthakmilik(tblintppthakmilik);
			c2E.setInsertHakmilikList_byObject(c2);
			stub.insertHakmilikList_byObject(c2E);
			
			for (int i = 0; i < tblintppthakmilik.length; i++) {
			
			saveLog(id_fail_etapp, tblintppthakmilik[i].getId_hakmilik_myetapp(), tblintppthakmilik[i].getFlag_proses(),
					session, db, "",turutan);
			
			}
			
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
			
		
		//point to local server
		/*
		IntegrationHakmilikPengambilanStub stub = new IntegrationHakmilikPengambilanStub();
		InsertHakmilikList_byObject c2 = new InsertHakmilikList_byObject();
		try {
			System.out.println("*******************************************try");
			c2.setTblintppthakmilik(tblintppthakmilik);
			stub.insertHakmilikList_byObject(c2);
			
			for (int i = 0; i < tblintppthakmilik.length; i++) {
			
			saveLog(id_fail_etapp, tblintppthakmilik[i].getId_hakmilik_myetapp(), tblintppthakmilik[i].getFlag_proses(),
					session, db, "",turutan);
			
			}
			
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
		*/
	}
	
	
	public Tblintpptderafmmk addDerafMMK_return_obj(String id_fail_etapp, String jenis_skrin_etapp,
			String no_rujukan_tarikbalik,String no_fail_jkptg, String tarikh_terima_data,
			String keterangan_mmk, double item_mmk, String flag_jenis_mmk,
			HttpSession session, Db db,double main_item_mmk,Integer turutan) throws Exception {
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());		
		myLogger.info("************** addDerafMMK MASUK 3");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);		
			Tblintpptderafmmk td = new Tblintpptderafmmk();
			td.setFlag_proses(jenis_skrin_etapp);
			td.setKeterangan_item_mmk(keterangan_mmk);
			td.setNo_fail_jkptg(no_fail_jkptg.trim());
			td.setNo_item_mmk(item_mmk);
			td.setTarikh_terima_data(dateNow);
			td.setFlag_jenis_mmk(flag_jenis_mmk);
			td.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			td.setNo_main_item_mmk(main_item_mmk);
			td.setTurutan(turutan);
		return td;
	}

	/*
	public void addDerafMMK(String id_fail_etapp, String jenis_skrin_etapp,
			String no_rujukan_tarikbalik,String no_fail_jkptg, String tarikh_terima_data,
			String keterangan_mmk, double item_mmk, String flag_jenis_mmk,
			HttpSession session, Db db) throws Exception {
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		
		
		myLogger.info("************** addDerafMMK MASUK 3");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub();
		InsertDerafMMK_byObject c2 = new InsertDerafMMK_byObject();
		try {
			Tblintpptderafmmk td = new Tblintpptderafmmk();
			td.setFlag_proses(jenis_skrin_etapp);
			td.setKeterangan_item_mmk(keterangan_mmk);
			td.setNo_fail_jkptg(no_fail_jkptg.trim());
			td.setNo_item_mmk(item_mmk);
			td.setTarikh_terima_data(dateNow);
			td.setFlag_jenis_mmk(flag_jenis_mmk);
			td.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			c2.setTd(td);

			stub.insertDerafMMK_byObject(c2);

		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
	}
	*/	
	
	public Tblintpptderafmmktajuk addDerafMMKTAJUK_return_obj(String id_fail_etapp, String jenis_skrin_etapp,
			String no_rujukan_tarikbalik,String no_fail_jkptg, String tarikh_terima_data,
			String keterangan_sidang, String waktu_sidang, String jenis_waktu_sidang, String waktu_sidang_keterangan, String tarikh_sidang, String tempat_sidang, String tajuk, 
			HttpSession session, Db db,Integer turutan) throws Exception {
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		
		System.out.println("jenis_skrin_etapp :"+jenis_skrin_etapp);
		System.out.println("no_fail_jkptg : "+no_fail_jkptg);
		System.out.println("no_rujukan_tarikbalik :"+no_rujukan_tarikbalik);
		System.out.println("tarikh_terima_data :"+tarikh_terima_data);
		System.out.println("keterangan_sidang :"+keterangan_sidang);
		System.out.println("waktu_sidang :"+waktu_sidang);
		System.out.println("jenis_waktu_sidang :"+jenis_waktu_sidang);
		System.out.println("waktu_sidang_keterangan :"+waktu_sidang_keterangan);
		System.out.println("tarikh_sidang :"+tarikh_sidang);
		System.out.println("tempat_sidang :"+tempat_sidang);
		System.out.println("tajuk :"+tajuk);
		System.out.println("turutan :"+turutan);
		
			Tblintpptderafmmktajuk tt = new Tblintpptderafmmktajuk();			
			tt.setFlag_proses(jenis_skrin_etapp);
			tt.setNo_fail_jkptg(no_fail_jkptg);
			tt.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			tt.setTarikh_terima_data(tarikh_terima_data);
			tt.setKeterangan_sidang(keterangan_sidang);
			tt.setWaktu_sidang(waktu_sidang);
			tt.setJenis_waktu_sidang(jenis_waktu_sidang);
			tt.setWaktu_sidang_keterangan(waktu_sidang_keterangan);
			tt.setTarikh_sidang(tarikh_sidang);
			tt.setTempat_sidang(tempat_sidang);
			tt.setTajuk(tajuk);	
			tt.setTurutan(turutan);
			return tt;
	}
	
	
	
	
	
	public Tblintpptmaklumatpengambilan addMaklumatPengambilan_return_obj(String tarikh_permohonan,String nama_kementerian, String tujuan_dalam_english, String tujuan, String no_fail_jkptg, String kod_negeri_pengambilan, 
			String nama_negeri_pengambilan, String kod_daerah_pengambilan, String nama_daerah_pengambilan, String jenis_pengambilan, String jenis_projek_pengambilan, String no_rujukan_surat_kjp, 
			String tarikh_surat_kjp, String no_rujukan_ptg, String no_rujukan_ptd, String id_kementerian_myetapp, String nama_agensi, String id_agensi_myetapp, String jenis_pengambilan_segera, String flag_permohonan_segera, 
			Integer turutan, String flag_proses, HttpSession session,String kod_agensi,String kod_kementerian,String alamat1_kjp,String alamat2_kjp,
			String alamat3_kjp,String poskod_kjp,String kod_negeri_kjp) throws Exception {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String dateNow = dateFormat.format(cal.getTime());
			Tblintpptmaklumatpengambilan mp = new Tblintpptmaklumatpengambilan();			
			mp.setTarikh_permohonan(tarikh_permohonan);
			mp.setNama_kementerian(nama_kementerian);
			mp.setTujuan_dalam_english(tujuan_dalam_english);
			mp.setTujuan(tujuan);
			mp.setNo_fail_jkptg(no_fail_jkptg);
			mp.setKod_negeri_pengambilan(kod_negeri_pengambilan);
			mp.setNama_negeri_pengambilan(nama_negeri_pengambilan);
			mp.setKod_daerah_pengambilan(kod_daerah_pengambilan);
			mp.setNama_daerah_pengambilan(nama_daerah_pengambilan);
			mp.setJenis_pengambilan(jenis_pengambilan);
			mp.setJenis_projek_pengambilan(jenis_projek_pengambilan);
			mp.setNo_rujukan_surat_kjp(no_rujukan_surat_kjp);
			mp.setTarikh_surat_kjp(tarikh_surat_kjp);
			mp.setNo_rujukan_ptg(no_rujukan_ptg);
			mp.setNo_rujukan_ptd(no_rujukan_ptd);
			mp.setId_kementerian_myetapp(id_kementerian_myetapp);
			mp.setNama_agensi(nama_agensi);
			mp.setId_agensi_myetapp(id_agensi_myetapp);
			mp.setJenis_pengambilan_segera(jenis_pengambilan_segera);
			mp.setFlag_permohonan_segera(flag_permohonan_segera);
			mp.setTurutan(turutan);
			mp.setFlag_proses(flag_proses);
			mp.setKodAgensi(kod_agensi);
			mp.setKodKementerian(kod_kementerian);
			
			
			
			//server etanah
			
			mp.setAlamat1(alamat1_kjp);
			mp.setAlamat2(alamat2_kjp);
			mp.setAlamat3(alamat3_kjp);
			mp.setPoskod(poskod_kjp);
			mp.setKodNegeri(kod_negeri_kjp);
			
			
			//local
			/*
			mp.setAlamat1_kjp(alamat1_kjp);
			mp.setAlamat2_kjp(alamat2_kjp);
			mp.setAlamat3_kjp(alamat3_kjp);
			mp.setPoskod_kjp(poskod_kjp);
			mp.setKod_negeri_kjp(kod_negeri_kjp);
			*/
			
			return mp;	
	}
	
	/*	public void addDerafMMKTAJUK(String id_fail_etapp, String jenis_skrin_etapp,
			String no_rujukan_tarikbalik,String no_fail_jkptg, String tarikh_terima_data,
			String keterangan_sidang, String waktu_sidang, String jenis_waktu_sidang, String waktu_sidang_keterangan, String tarikh_sidang, String tempat_sidang, String tajuk, 
			HttpSession session, Db db) throws Exception {
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub();
		InsertDerafMMKTajuk_byObject c2 = new InsertDerafMMKTajuk_byObject();
		try {
			Tblintpptderafmmktajuk tt = new Tblintpptderafmmktajuk();
			
			tt.setFlag_proses(jenis_skrin_etapp);
			tt.setNo_fail_jkptg(no_fail_jkptg);
			tt.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			tt.setTarikh_terima_data(tarikh_terima_data);
			tt.setKeterangan_sidang(keterangan_sidang);
			tt.setWaktu_sidang(waktu_sidang);
			tt.setJenis_waktu_sidang(jenis_waktu_sidang);
			tt.setWaktu_sidang_keterangan(waktu_sidang_keterangan);
			tt.setTarikh_sidang(tarikh_sidang);
			tt.setTempat_sidang(tempat_sidang);
			tt.setTajuk(tajuk);		
			c2.setTt(tt);
			stub.insertDerafMMKTajuk_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
	}
	*/
	
	
	
	

	
	public Tblintpptwarta addDerafMaklumatWarta_return_obj(String id_fail_etapp, String jenis_skrin_etapp,String no_fail_jkptg, String tarikh_terima_data,
			 String no_warta, String tarikh_warta,HttpSession session, Db db,Integer turutan) throws Exception {		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		
			Tblintpptwarta tw = new Tblintpptwarta();			
			tw.setFlag_proses(jenis_skrin_etapp);
			tw.setNo_fail_jkptg(no_fail_jkptg);
			tw.setNo_warta(no_warta);
			tw.setTarikh_terima_data(tarikh_terima_data);
			tw.setTarikh_warta(tarikh_warta);
			tw.setTurutan(turutan);
		
		return tw;
	}
	
	/*
	public void addDerafMaklumatWarta(String id_fail_etapp, String jenis_skrin_etapp,String no_fail_jkptg, String tarikh_terima_data,
			 String no_warta, String tarikh_warta,HttpSession session, Db db) throws Exception {
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub();
		InsertMaklumatWarta_byObject c2 = new InsertMaklumatWarta_byObject();
		try {
			Tblintpptwarta tw = new Tblintpptwarta();			
			tw.setFlag_proses(jenis_skrin_etapp);
			tw.setNo_fail_jkptg(no_fail_jkptg);
			tw.setNo_warta(no_warta);
			tw.setTarikh_terima_data(tarikh_terima_data);
			tw.setTarikh_warta(tarikh_warta);			
			c2.setTw(tw);
			stub.insertMaklumatWarta_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");
		}
	}
	*/

	
	public Tblintpptdokumen addDokumen_return_obj(String id_hakmilik, String flag_proses,
			String no_fail_jkptg, String kod_unit_hakmilik, String no_hakmilik,
			String kod_daerah, String kod_mukim, String kod_negeri,
			String tajuk_dokumen, String nama_dokumen, String jenis_fail,
			String tarikh_terima_data, DataHandler file_upload, int sizefail,
			String jenis_format_fail, String no_rujukan_tarikbalik,String kategori_lampiran,String id_fail_etapp,Db db,Integer turutan)
			throws Exception {
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);
			Tblintpptdokumen td = new Tblintpptdokumen();
			td.setId_hakmilik(id_hakmilik.trim());
			td.setFlag_proses(flag_proses);
			td.setNo_fail_jkptg(no_fail_jkptg.trim());
			td.setKod_unit_hakmilik(kod_unit_hakmilik.trim());
			td.setNo_hakmilik(no_hakmilik.trim());
			td.setTarikh_terima_data(dateNow);
			td.setKod_daerah(kod_daerah.trim());
			td.setKod_mukim(kod_mukim.trim());
			td.setKod_negeri(kod_negeri.trim());
			td.setTajuk_dokumen(tajuk_dokumen);
			td.setNama_dokumen(nama_dokumen);
			td.setJenis_fail(jenis_fail);
			td.setContent_upload(file_upload);
			td.setSize(sizefail);
			td.setJenis_fail(jenis_fail);
			td.setJenis_format_fail(jenis_format_fail);
			td.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			td.setKategori(kategori_lampiran);
			td.setTurutan(turutan);
			
			return td;
	}
	
	/*
	public void addDokumen(String id_hakmilik, String flag_proses,
			String no_fail_jkptg, String kod_unit_hakmilik, String no_hakmilik,
			String kod_daerah, String kod_mukim, String kod_negeri,
			String tajuk_dokumen, String nama_dokumen, String jenis_fail,
			String tarikh_terima_data, DataHandler file_upload, int sizefail,
			String jenis_format_fail, String no_rujukan_tarikbalik,String kategori_lampiran,String id_fail_etapp,Db db)
			throws Exception {
		
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub();
		InsertDokumen_byObject c2 = new InsertDokumen_byObject();
		try {
			Tblintpptdokumen td = new Tblintpptdokumen();
			td.setId_hakmilik(id_hakmilik.trim());
			td.setFlag_proses(flag_proses);
			td.setNo_fail_jkptg(no_fail_jkptg.trim());
			td.setKod_unit_hakmilik(kod_unit_hakmilik.trim());
			td.setNo_hakmilik(no_hakmilik.trim());
			td.setTarikh_terima_data(dateNow);
			td.setKod_daerah(kod_daerah.trim());
			td.setKod_mukim(kod_mukim.trim());
			td.setKod_negeri(kod_negeri.trim());
			td.setTajuk_dokumen(tajuk_dokumen);
			td.setNama_dokumen(nama_dokumen);
			td.setJenis_fail(jenis_fail);
			td.setContent_upload(file_upload);
			td.setSize(sizefail);
			td.setJenis_fail(jenis_fail);
			td.setJenis_format_fail(jenis_format_fail);
			td.setNo_rujukan_tarikbalik(no_rujukan_tarikbalik);
			td.setKategori(kategori_lampiran);
			c2.setTd(td);
			myLogger.info("C2:::::::::::::::::::::: :" + c2);
			stub.insertDokumen_byObject(c2);
		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch :::::::::: "
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");

		}
	}
	*/
	
	
	
	public Tblintppthakmilik addHakmilik_return_obj(String id_hakmilik, String flag_proses,
			String no_warta, String tarikh_warta, String luas_asal,
			String kod_luas_asal, String luas_ambil, String kod_luas_ambil,
			String no_fail_jkptg, String no_lot, String kod_unit_hakmilik,
			String no_hakmilik, String tarikh_borangk, String status_borangk,
			String no_rujukan_tarikhbalik, String sebab_tarikbalik,
			String no_warta_tarikbalik, String tarikh_warta_tarikbalik,
			String no_lot_baru, String no_syit, String no_pa, String no_pu,
			String luas_pa, String kod_luas_pa, String tarikh_terima_data,
			String kod_negeri, String kod_daerah, String kod_mukim,
			String id_fail_etapp, String id_hakmilik_etapp,
			String jenis_skrin_etapp, HttpSession session, Db db,
			String id_penarikan, String tarikh_borangi,Integer turutan) throws Exception {		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);

		myLogger.info("get_tururan :::"+turutan);
			Tblintppthakmilik th = new Tblintppthakmilik();

			th.setId_hakmilik(id_hakmilik.trim());
			th.setFlag_proses(flag_proses);
			th.setNo_warta(no_warta);
			th.setTarikh_warta(tarikh_warta);
			th.setLuas_asal(luas_asal.trim());
			th.setKod_luas_asal(kod_luas_asal.trim());
			th.setLuas_ambil(luas_ambil.trim());
			th.setKod_luas_ambil(kod_luas_ambil.trim());
			th.setNo_fail_jkptg(no_fail_jkptg.trim());
			th.setNo_lot(no_lot.trim());
			th.setKod_unit_hakmilik(kod_unit_hakmilik.trim());
			th.setNo_hakmilik(no_hakmilik.trim());
			th.setTarikh_borangk(tarikh_borangk);
			th.setStatus_borangk(status_borangk);
			th.setNo_rujukan_tarikhbalik(no_rujukan_tarikhbalik.trim());
			th.setSebab_tarikbalik(sebab_tarikbalik);
			th.setNo_warta_tarikbalik(sebab_tarikbalik);
			th.setTarikh_warta_tarikbalik(tarikh_warta_tarikbalik);
			th.setNo_lot_baru(no_lot_baru);
			th.setNo_syit(no_syit);
			th.setNo_pa(no_pa);
			th.setNo_pu(no_pu);
			th.setLuas_pa(luas_pa);
			th.setKod_luas_pa(kod_luas_pa);
			th.setTarikh_terima_data(dateNow);
			th.setKod_negeri(kod_negeri.trim());
			th.setKod_daerah(kod_daerah.trim());
			th.setKod_mukim(kod_mukim.trim());
			th.setTarikh_borangi(tarikh_borangi);
			th.setId_hakmilik_myetapp(id_hakmilik_etapp);
			th.setId_fail_myetapp(id_fail_etapp);
			th.setTurutan(turutan);

			return th;
	}

/*
	public void addHakmilik(String id_hakmilik, String flag_proses,
			String no_warta, String tarikh_warta, String luas_asal,
			String kod_luas_asal, String luas_ambil, String kod_luas_ambil,
			String no_fail_jkptg, String no_lot, String kod_unit_hakmilik,
			String no_hakmilik, String tarikh_borangk, String status_borangk,
			String no_rujukan_tarikhbalik, String sebab_tarikbalik,
			String no_warta_tarikbalik, String tarikh_warta_tarikbalik,
			String no_lot_baru, String no_syit, String no_pa, String no_pu,
			String luas_pa, String kod_luas_pa, String tarikh_terima_data,
			String kod_negeri, String kod_daerah, String kod_mukim,
			String id_fail_etapp, String id_hakmilik_etapp,
			String jenis_skrin_etapp, HttpSession session, Db db,
			String id_penarikan, String tarikh_borangi) throws Exception {
		
		Hashtable hash_maklumatprojek = logic.getMaklumatProjek(id_fail_etapp, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? "": hash_maklumatprojek.get("ID_NEGERI").toString());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNow :" + dateNow);
		EtappPengambilanServiceStub stub = new EtappPengambilanServiceStub();
		InsertHakmilik_byObject c2 = new InsertHakmilik_byObject();
		try {
			System.out
					.println("*******************************************try");

			myLogger.info("kod_unit_hakmilik :" + kod_unit_hakmilik);
			myLogger.info("no_hakmilik :" + no_hakmilik);

			Tblintppthakmilik th = new Tblintppthakmilik();

			th.setId_hakmilik(id_hakmilik.trim());
			th.setFlag_proses(flag_proses);
			th.setNo_warta(no_warta);
			th.setTarikh_warta(tarikh_warta);
			th.setLuas_asal(luas_asal.trim());
			th.setKod_luas_asal(kod_luas_asal.trim());
			th.setLuas_ambil(luas_ambil.trim());
			th.setKod_luas_ambil(kod_luas_ambil.trim());
			th.setNo_fail_jkptg(no_fail_jkptg.trim());
			th.setNo_lot(no_lot.trim());
			th.setKod_unit_hakmilik(kod_unit_hakmilik.trim());
			th.setNo_hakmilik(no_hakmilik.trim());
			th.setTarikh_borangk(tarikh_borangk);
			th.setStatus_borangk(status_borangk);
			th.setNo_rujukan_tarikhbalik(no_rujukan_tarikhbalik.trim());
			th.setSebab_tarikbalik(sebab_tarikbalik);
			th.setNo_warta_tarikbalik(sebab_tarikbalik);
			th.setTarikh_warta_tarikbalik(tarikh_warta_tarikbalik);
			th.setNo_lot_baru(no_lot_baru);
			th.setNo_syit(no_syit);
			th.setNo_pa(no_pa);
			th.setNo_pu(no_pu);
			th.setLuas_pa(luas_pa);
			th.setKod_luas_pa(kod_luas_pa);
			th.setTarikh_terima_data(dateNow);
			th.setKod_negeri(kod_negeri.trim());
			th.setKod_daerah(kod_daerah.trim());
			th.setKod_mukim(kod_mukim.trim());
			th.setTarikh_borangi(tarikh_borangi);

			c2.setTh(th);
			stub.insertHakmilik_byObject(c2);
			saveLog(id_fail_etapp, id_hakmilik_etapp, jenis_skrin_etapp,
					session, db, id_penarikan);

		} catch (RuntimeException e) {
			System.out
					.println("*******************************************catch"
							+ e);
		} finally {
			System.out
					.println("*******************************************finally");

		}
	}
	*/
	public void insertPerson() throws Exception {
		// PersonDBServiceStub stub = null;
		/*
		 * try {
		 * System.out.println("*******************************************try");
		 * PersonDBServiceStub stub = new PersonDBServiceStub(); InsertPerson c2
		 * = new InsertPerson(); c2.setId(147); c2.setName("Razman TEST");
		 * c2.setAddress("alamat TEST"); c2.setAge(13); stub.insertPerson(c2);
		 * 
		 * } catch(RuntimeException e) {
		 * System.out.println("*******************************************catch"
		 * +e); } finally {
		 * System.out.println("*******************************************finally"
		 * ); }
		 */
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

	private void maklumatMMK(String id_penarikanbalik,String id_fail, String jenis_skrin, Db db,
			String hitButton) throws Exception {
		String maklumat_mmk = "";
		Hashtable hash_maklumatprojek = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? ""
				: hash_maklumatprojek.get("ID_NEGERI").toString());
		String nama_negeri = (hash_maklumatprojek.get("NAMA_NEGERI").toString() == null ? ""
				: hash_maklumatprojek.get("NAMA_NEGERI").toString());
		
		
		
		
		if (jenis_skrin.equals("BorangC") || jenis_skrin.equals("BorangA"))
		{
				List<Hashtable> listMMK = new ArrayList();
				listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "", db,jenis_skrin);
				if (listMMK.size() > 0) {
		
					maklumat_mmk += " <table border=\"0\" width=\"100%\"  class=\"nav\"> "
							+ " <tr  > "
							+ " <td valign=\"top\" >"
							+ " <strong>Maklumat Deraf MMK ("
							+ nama_negeri
							+ ")</strong>" + " </td>" + " </tr>" + " </table>";
					
					
					maklumat_mmk += "  <div style=\"width:100%;height:150;overflow:auto\"> ";
					
					Hashtable hash_maklumatMMK = null;
					hash_maklumatMMK = logic.getMaklumatMMK(id_penarikanbalik,id_fail,jenis_skrin,db);
					if(hash_maklumatMMK.size()>0)
					{
					String KETERANGAN_SIDANG = hash_maklumatMMK.get("KETERANGAN_SIDANG").toString();
					String WAKTU_SIDANG = hash_maklumatMMK.get("WAKTU_SIDANG").toString();
					String JENIS_WAKTU_SIDANG = hash_maklumatMMK.get("JENIS_WAKTU_SIDANG").toString();
					String WAKTU_SIDANG_KETERANGAN = hash_maklumatMMK.get("WAKTU_SIDANG_KETERANGAN").toString();
					String TARIKH_SIDANG = hash_maklumatMMK.get("TARIKH_SIDANG").toString();
					String TEMPAT_SIDANG = hash_maklumatMMK.get("TEMPAT_SIDANG").toString();
					String TAJUK = hash_maklumatMMK.get("TAJUK").toString();	
					
							if(!KETERANGAN_SIDANG.equals("") || !WAKTU_SIDANG.equals("") || !TARIKH_SIDANG.equals("") || !TEMPAT_SIDANG.equals("") || !TAJUK.equals(""))
							{
							maklumat_mmk += "  "
									+ " <table width=\"80%\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\" class=\"dashboard_sub\"> "
									+ " <tr> "
									+ " <td width=\"1%\"></td> "
									+ " <td width=\"20%\"></td> "
									+ " <td width=\"1%\"></td> "
									+ " <td width=\"68%\"></td> "
									+ " </tr> ";
							if(!KETERANGAN_SIDANG.equals(""))
							{
									maklumat_mmk += "  "
									+ " <tr> "
									+ " <td ></td> "
									+ " <td valign = \"top\" >Persidangan</td> "
									+ " <td valign = \"top\">:</td> "
									+ " <td align = \"justify\">"+KETERANGAN_SIDANG+"</td> "
									+ " </tr> ";
							}
							if(!WAKTU_SIDANG.equals(""))
							{
									maklumat_mmk += "  "
									+ " <tr> "
									+ " <td ></td> "
									+ " <td valign = \"top\">Masa</td> "
									+ " <td valign = \"top\">:</td> "
									+ " <td >"+WAKTU_SIDANG+" "+WAKTU_SIDANG_KETERANGAN+"</td> "
									+ " </tr> ";
							}
							if(!TARIKH_SIDANG.equals(""))
							{
									maklumat_mmk += "  "
									+ " <tr> "
									+ " <td ></td> "
									+ " <td valign = \"top\">Tarikh</td> "
									+ " <td valign = \"top\">:</td> "
									+ " <td >"+TARIKH_SIDANG+"</td> "
									+ " </tr> ";
							}
							if(!TEMPAT_SIDANG.equals(""))
							{
									maklumat_mmk += "  "
									+ " <tr> "
									+ " <td ></td> "
									+ " <td valign = \"top\">Tempat</td> "
									+ " <td valign = \"top\">:</td> "
									+ " <td >"+TEMPAT_SIDANG+"</td> "
									+ " </tr> ";
							}
							if(!TAJUK.equals(""))
							{
							maklumat_mmk += "  "
									+ " <tr> "
									+ " <td ></td> "
									+ " <td valign = \"top\">Tajuk</td> "
									+ " <td valign = \"top\">:</td> "
									+ " <td align = \"justify\">"+TAJUK+"</td> "
									+ " </tr> ";
							}
							maklumat_mmk += "</table>";
							}
					}
					
					
		
					maklumat_mmk += " <table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\" class=\"dashboard_sub\"> "
							+ " <tr> "
							+ " <td width=\"1%\"></td> "
							+ " <td width=\"3%\"></td> "
							+ " <td width=\"1%\"></td> "
							+ " <td width=\"73%\"></td> "
							+ " <td width=\"23%\"></td> "
							+ " </tr> ";
					if (id_negeri.equals("5") && jenis_skrin.equals("BorangC")) // negeri
																				// 9
																				// sek8
					{
						maklumat_mmk += formatMMKN9_S8(id_fail, db, hitButton,jenis_skrin);
					} else if (id_negeri.equals("4") && jenis_skrin.equals("BorangC")) // melaka
																						// sek8
					{
						maklumat_mmk += formatMMKMelaka_S8(id_fail, db, hitButton,jenis_skrin);
					} else if (id_negeri.equals("5") && jenis_skrin.equals("BorangA")) // negeri
																						// 9
																						// sek4
					{
						maklumat_mmk += formatMMKN9_S4(id_fail, db, hitButton,jenis_skrin);
					} else if (id_negeri.equals("4") && jenis_skrin.equals("BorangA")) // melaka
																						// sek4
					{
						maklumat_mmk += formatMMKMelaka_S4(id_fail, db, hitButton,jenis_skrin);
					}
					maklumat_mmk += "</table></div><br>";
				}
				
		}
		/*
		if (jenis_skrin.equals("TarikBalik"))
		{
			List<Hashtable> listMMK_TarikBalik = new ArrayList();
			listMMK_TarikBalik = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikanbalik, "", db);
			if (listMMK_TarikBalik.size() > 0) {
				maklumat_mmk += " <table border=\"0\" width=\"100%\"  class=\"nav\"> "
						+ " <tr  > "
						+ " <td valign=\"top\" >";
				if (jenis_skrin.equals("TarikBalik"))			
				{
						maklumat_mmk += " <strong>Maklumat Deraf MMK Penarikan Balik ("
						+ nama_negeri
						+ ")</strong>"; 
				}
				else
				{
						maklumat_mmk += " <strong>Maklumat Deraf MMK ("
							+ nama_negeri
							+ ")</strong>"; 
				}
		
						maklumat_mmk += " </td>" + " </tr>" + " </table>";
						
				maklumat_mmk += "  <div style=\"width:100%;height:150;overflow:auto\"> ";
				
				Hashtable hash_maklumatMMK = null;
				hash_maklumatMMK = logic.getMaklumatMMK(id_penarikanbalik,id_fail,jenis_skrin,db);
				if(hash_maklumatMMK.size()>0)
				{
				String KETERANGAN_SIDANG = hash_maklumatMMK.get("KETERANGAN_SIDANG").toString();
				String WAKTU_SIDANG = hash_maklumatMMK.get("WAKTU_SIDANG").toString();
				String JENIS_WAKTU_SIDANG = hash_maklumatMMK.get("JENIS_WAKTU_SIDANG").toString();
				String WAKTU_SIDANG_KETERANGAN = hash_maklumatMMK.get("WAKTU_SIDANG_KETERANGAN").toString();
				String TARIKH_SIDANG = hash_maklumatMMK.get("TARIKH_SIDANG").toString();
				String TEMPAT_SIDANG = hash_maklumatMMK.get("TEMPAT_SIDANG").toString();
				String TAJUK = hash_maklumatMMK.get("TAJUK").toString();	
				
				
				maklumat_mmk += "  "
						+ " <table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\" class=\"dashboard_sub\"> "
						+ " <tr> "
						+ " <td width=\"1%\"></td> "
						+ " <td width=\"20%\"></td> "
						+ " <td width=\"1%\"></td> "
						+ " <td width=\"68%\"></td> "
						+ " </tr> ";
				if(!KETERANGAN_SIDANG.equals(""))
				{
						maklumat_mmk += "  "
						+ " <tr> "
						+ " <td ></td> "
						+ " <td >Persidangan</td> "
						+ " <td >:</td> "
						+ " <td >"+KETERANGAN_SIDANG+"</td> "
						+ " </tr> ";
				}
				if(!WAKTU_SIDANG.equals(""))
				{
						maklumat_mmk += "  "
						+ " <tr> "
						+ " <td ></td> "
						+ " <td >Masa</td> "
						+ " <td >:</td> "
						+ " <td >"+WAKTU_SIDANG+" "+WAKTU_SIDANG_KETERANGAN+"</td> "
						+ " </tr> ";
				}
				if(!TARIKH_SIDANG.equals(""))
				{
						maklumat_mmk += "  "
						+ " <tr> "
						+ " <td ></td> "
						+ " <td >Tarikh</td> "
						+ " <td >:</td> "
						+ " <td >"+TARIKH_SIDANG+"</td> "
						+ " </tr> ";
				}
				if(!TEMPAT_SIDANG.equals(""))
				{
						maklumat_mmk += "  "
						+ " <tr> "
						+ " <td ></td> "
						+ " <td >Tempat</td> "
						+ " <td >:</td> "
						+ " <td >"+TEMPAT_SIDANG+"</td> "
						+ " </tr> ";
				}
				if(!TAJUK.equals(""))
				{
				maklumat_mmk += "  "
						+ " <tr> "
						+ " <td ></td> "
						+ " <td >Tajuk</td> "
						+ " <td >:</td> "
						+ " <td >"+TAJUK+"</td> "
						+ " </tr> ";
				}
				maklumat_mmk += "</table>";
				}
	
				
				maklumat_mmk		+= " <table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\" class=\"dashboard_sub\"> "
						+ " <tr> "
						+ " <td width=\"1%\"></td> "
						+ " <td width=\"3%\"></td> "
						+ " <td width=\"1%\"></td> "
						+ " <td width=\"73%\"></td> "
						+ " <td width=\"23%\"></td> "
						+ " </tr> ";
				if (id_negeri.equals("5") && jenis_skrin.equals("TarikBalik"))			
				{
				maklumat_mmk += formatMMKN9_TarikBalik(id_penarikanbalik, db, hitButton);
				} 
				else if (id_negeri.equals("4") && jenis_skrin.equals("TarikBalik"))						
				{
				maklumat_mmk += formatMMKMelaka_TarikBalik(id_penarikanbalik, db, hitButton);
				}	
				maklumat_mmk += "</table></div><br>";
			}			
		}*/

		context.put("maklumat_mmk", maklumat_mmk);
	}

	private String formatMMKN9_S8(String id_fail, Db db, String hitButton,String flag_proses)
			throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "", db,flag_proses);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "TUJUAN", db,flag_proses);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "LATARBELAKANG", db,flag_proses);
		no_item = 2;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LATAR BELAKANG</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALTANAH", db,flag_proses);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "NILAITANAH", db,flag_proses);
		no_item = 4;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>NILAI TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "SYOR", db,flag_proses);
		no_item = 5;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SYOR PENTADBIR TANAH</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\" >" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "ULASANPENGARAH", db,flag_proses);
		no_item = 6;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN PENGARAH NEGERI</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "KEPUTUSAN", db,flag_proses);
		no_item = 7;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>KEPUTUSAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}

		return maklumat_mmk;
	}
	
	/*
	private String formatMMKMelaka_TarikBalik(String id_penarikan, Db db, String hitButton)
			throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "", db);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "TUJUAN", db);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "LATARBELAKANG", db);
		no_item = 2;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LATAR BELAKANG</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "ULASAN_PENTADBIR", db);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN PENTADBIR TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "PERAKUAN_PENTADBIR", db);
		no_item = 4	;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERAKUAN PENTADBIR TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "PERAKUAN_PTG", db);
		no_item = 5	;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERAKUAN PENGARAH TANAH DA GALIAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		return maklumat_mmk;
	}
	
	private String formatMMKN9_TarikBalik(String id_penarikan, Db db, String hitButton)
			throws Exception {
		
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "", db);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "TUJUAN", db);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();
				
				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "LATARBELAKANG", db);
		no_item = 2;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LATAR BELAKANG</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "SEBABPENARIKAN", db);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SEBAB-SEBAB PENARIKAN BALIK</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "SYOR", db);
		no_item = 4	;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SYOR PENTADBIR TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "ULASAN_PENGARAH", db);
		no_item = 5	;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN PENGARAH NEGERI</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMKTarikBalik_PULL(id_penarikan, "KEPUTUSAN", db);
		no_item = 6	;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>KEPUTUSAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		return maklumat_mmk;
	}
*/
	private String formatMMKN9_S4(String id_fail, Db db, String hitButton,String flag_proses)
			throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "", db,flag_proses);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "TUJUAN", db,flag_proses);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "LATARBELAKANG", db,flag_proses);
		no_item = 2;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LATAR BELAKANG</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALTANAH", db,flag_proses);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "NILAITANAH", db,flag_proses);
		no_item = 4;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>NILAI TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "SYOR", db,flag_proses);
		no_item = 5;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SYOR PENTADBIR TANAH</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\" >" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "ULASANPENGARAH", db,flag_proses);
		no_item = 6;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN PENGARAH NEGERI</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "KEPUTUSAN", db,flag_proses);
		no_item = 7;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>KEPUTUSAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}

		return maklumat_mmk;
	}

	private String formatMMKMelaka_S8(String id_fail, Db db, String hitButton,String flag_proses)
			throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "", db,flag_proses);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "TUJUAN", db,flag_proses);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}

		no_item = 2;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALPERMOHONAN",
				db,flag_proses);
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL PERMOHONAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALTANAH", db,flag_proses);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALPEMOHON", db,flag_proses);
		no_item = 4;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL PEMOHON</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail, "ANGGARANPAMPASAN", db,flag_proses);
		no_item = 5;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ANGGARAN PAMPASAN NILAIAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\" >" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}

		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "ULASANTEKNIKAL", db,flag_proses);
		no_item = 6;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN JABATAN TEKNIKAL</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PANDANGANYB", db,flag_proses);
		no_item = 7;
		maklumat_mmk += ""
				+ " <tr> "
				+ " <td ><b>"
				+ no_item
				+ "</b></td> "
				+ " <td colspan=\"4\" ><b>PANDANGAN Y.B ADUAN KAWASAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PANDANGANPT", db,flag_proses);
		no_item = 8;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PANDANGAN PENTADBIR TANAH</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERAKUANPT", db,flag_proses);
		no_item = 9;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PANDANGAN PENTADBIR TANAH</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "ULASANPENGARAH", db,flag_proses);
		no_item = 10;
		maklumat_mmk += ""
				+ " <tr> "
				+ " <td ><b>"
				+ no_item
				+ "</b></td> "
				+ " <td colspan=\"4\" ><b>ULASAN PENGARAH TANAH DAN GALIAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}

		return maklumat_mmk;
	}

	private String formatMMKMelaka_S4(String id_fail, Db db, String hitButton,String flag_proses)
			throws Exception {
		String maklumat_mmk = "";
		List<Hashtable> listMMK = new ArrayList();
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "", db,flag_proses);
		int no_item = 0;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "TUJUAN", db,flag_proses);
		no_item = 1;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>TUJUAN</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}

		no_item = 2;
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALPERMOHONAN",
				db,flag_proses);
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL PERMOHONAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALTANAH", db,flag_proses);
		no_item = 3;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERIHALPEMOHON", db,flag_proses);
		no_item = 4;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>PERIHAL PEMOHON</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic
				.listSenaraiItemMMK_PULL(id_fail, "ANGGARANPAMPASAN", db,flag_proses);
		no_item = 5;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>ANGGARAN PAMPASAN NILAIAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\" >" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}

		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "LAPORANTANAH", db,flag_proses);
		no_item = 6;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LAPORAN TANAH</b></td> " + " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> " + " <td align=\"justify\">"
						+ KETERANGAN_SUBMMK + "</td> " + " <td ></td> "
						+ " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "LAPORANTEKNIKAL", db,flag_proses);
		no_item = 7;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>LAPORAN TEKNIKAL</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PANDANGANYB", db,flag_proses);
		no_item = 8;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SYOR Y.B KAWASAN</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}
		listMMK = logic.listSenaraiItemMMK_PULL(id_fail, "PERAKUANPT", db,flag_proses);
		no_item = 9;
		maklumat_mmk += "" + " <tr> " + " <td ><b>" + no_item + "</b></td> "
				+ " <td colspan=\"4\" ><b>SYOR PENTADBIR TANAH</b></td> "
				+ " </tr> ";
		if (listMMK.size() > 0) {

			for (int x = 0; x < listMMK.size(); x++) {
				Hashtable getMMK = (Hashtable) listMMK.get(x);
				String FLAG_JENIS_MMK = getMMK.get("FLAG_JENIS_MMK").toString() == null ? ""
						: getMMK.get("FLAG_JENIS_MMK").toString();
				String KETERANGAN_SUBMMK = getMMK.get("KETERANGAN_SUBMMK")
						.toString() == null ? "" : getMMK.get(
						"KETERANGAN_SUBMMK").toString();

				maklumat_mmk += "" + " <tr> " + " <td ></td> "
						+ " <td valign=\"top\">" + (((x + 1) * 0.1) + no_item)
						+ "</td> " + " <td ></td> "
						+ " <td align=\"justify\" >" + KETERANGAN_SUBMMK
						+ "</td> " + " <td ></td> " + " </tr> ";
			}
		}

		return maklumat_mmk;
	}

	private void maklumatPU(String id_fail, String id_hakmilik, Db db)
			throws Exception {
		Hashtable hash_maklumatPU = null;
		hash_maklumatPU = logic.getMaklumatPU(id_fail, id_hakmilik, db);
		context.put("hash_maklumatPU", hash_maklumatPU);
	}

	private void maklumatBorangK(String id_fail, String id_hakmilik, Db db)
			throws Exception {
		Hashtable hash_maklumatBorangK = null;
		hash_maklumatBorangK = logic.getMaklumatBorangK(id_fail, id_hakmilik,
				db);
		context.put("hash_maklumatBorangK", hash_maklumatBorangK);
	}

	private void maklumatTarikBalik(String id_penarikan, Db db)
			throws Exception {
		Hashtable hash_maklumatTarikBalik = null;
		hash_maklumatTarikBalik = logic.getMaklumatTarikBalik(id_penarikan, db);
		context.put("hash_maklumatTarikBalik", hash_maklumatTarikBalik);
	}

	private void listSenaraiLotBorangC(String id_fail, Db db) throws Exception {

		List<Hashtable> listSenaraiLotBorangC = new ArrayList();
		listSenaraiLotBorangC = logic.listSenaraiLotBorangC(id_fail, db);
		context.put("listSenaraiLotBorangC", listSenaraiLotBorangC);

	}

	private void listSenaraiDokumenUpload(String id_permohonan,
			String id_hakmilik, String jenis_skrin, String id_penarikan, Db db)
			throws Exception {
		List<Hashtable> listSenaraiDokumen = new ArrayList();
		listSenaraiDokumen = listSenaraiDokumen(id_permohonan, id_hakmilik,
				jenis_skrin, id_penarikan, db);
		context.put("listSenaraiDokumen", listSenaraiDokumen);
		context.put("listSenaraiDokumen_size", listSenaraiDokumen.size());
	}

	private void listSenaraiDokumenUpload_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db) throws Exception {
		List<Hashtable> listSenaraiDokumen_fromEtanah = new ArrayList();
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());

		if (jenis_skrin.equals("hantarPelanChartingS8")
				|| jenis_skrin.equals("hantarPelanChartingS4")
				|| jenis_skrin.equals("BorangC")
				|| jenis_skrin.equals("BorangA")
				|| jenis_skrin.equals("MMK_S8") || jenis_skrin.equals("MMK_S4")
				|| jenis_skrin.equals("TarikBalik")
				|| jenis_skrin.equals("BorangI")
				|| jenis_skrin.equals("WartaS4")
				|| jenis_skrin.equals("WartaS8")) {
			listSenaraiDokumen_fromEtanah = listSenaraiDokumen_fromEtanah("",
					"", jenis_skrin, no_fail_jkptg, "", "", "", "", "", db);
		}
		else if(jenis_skrin.equals("BorangK") || jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur"))
		{
		hash_maklumathakmilik = logic.getMaklumatHakmilik(id_fail,
				id_hakmilik_etapp, db);
		id_hakmilik_etanah = (hash_maklumathakmilik.get(
				"ID_HAKMILIK_ETANAH").toString() == null ? ""
				: hash_maklumathakmilik.get("ID_HAKMILIK_ETANAH")
						.toString());
		listSenaraiDokumen_fromEtanah = listSenaraiDokumen_fromEtanah("",
				id_hakmilik_etanah, jenis_skrin, no_fail_jkptg, "", "", "", "", "", db);		
		}

		context.put("listSenaraiDokumen_fromEtanah",
				listSenaraiDokumen_fromEtanah);
		context.put("listSenaraiDokumen_fromEtanah_size",
				listSenaraiDokumen_fromEtanah.size());
	}

	private void listSyor_fromEtanah(String id_fail, String id_permohonan,
			String id_hakmilik_etapp, String jenis_skrin, Db db)
			throws Exception {
		List<Hashtable> listSyor_fromEtanah = new ArrayList();
		String no_fail_jkptg = "";
		Hashtable hash_maklumatprojek = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());

		if (jenis_skrin.equals("MMK_S8") || jenis_skrin.equals("MMK_S4")) {
			listSyor_fromEtanah = listSyor_fromEtanah("", "", jenis_skrin,
					no_fail_jkptg, "", "", "", "", "", db);
		}

		context.put("listSyor_fromEtanah", listSyor_fromEtanah);
		context.put("listSyor_fromEtanah_size", listSyor_fromEtanah.size());
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
		hash_maklumatKeputusanMmk = getMaklumatKeputusanMmk_fromEtanah(jenis_skrin, no_fail_jkptg, db,return_current_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db));	
		
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
		hash_current_turutan_maklumatKeputusanMmk = current_turutan_getMaklumatKeputusanMmk_fromEtanah(jenis_skrin, no_fail_jkptg, db);	
		
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

	
	
	private void hashMaklumatEndorsan_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,Integer turutan) throws Exception {
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatEndorsan = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());
		if (jenis_skrin.equals("hantarPelanChartingS8")
				|| jenis_skrin.equals("WartaS8")
				|| jenis_skrin.equals("BorangC")
				|| jenis_skrin.equals("BorangA")) {
			hash_maklumatEndorsan = getMaklumatEndorsan_fromEtanah(jenis_skrin,
					no_fail_jkptg, db,turutan);

		} else if (jenis_skrin.equals("BorangK")) {
			hash_maklumathakmilik = logic.getMaklumatHakmilik(id_fail,
					id_hakmilik_etapp, db);
			id_hakmilik_etanah = (hash_maklumathakmilik.get(
					"ID_HAKMILIK_ETANAH").toString() == null ? ""
					: hash_maklumathakmilik.get("ID_HAKMILIK_ETANAH")
							.toString());
			hash_maklumatEndorsan = getMaklumatEndorsanBK_fromEtanah(
					jenis_skrin, no_fail_jkptg, id_hakmilik_etanah, db,turutan);

		}
		context.put("hash_maklumatEndorsan", hash_maklumatEndorsan);
		context.put("hash_maklumatEndorsan_size", hash_maklumatEndorsan.size());
	}
	
	private void hashMaklumatKeputusanMmk_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,Integer turutan) throws Exception {
		String no_fail_jkptg = "";
		Integer count_hakmilik = 0;
		count_hakmilik = logic.hakmilik_COUNT("", id_fail,
				id_hakmilik_etapp, jenis_skrin, db);		
		Integer countlog_hakmilik = 0;
		countlog_hakmilik = logic.hakmilikLog_COUNT(id_fail, id_hakmilik_etapp,
				jenis_skrin, db, id_hakmilik_etapp, return_new_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db));
		
		Hashtable hash_maklumatprojek = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());		
		
		
		myLogger.info("@@@@@@@@@@@@@ count_hakmilik :"+count_hakmilik);
		myLogger.info("@@@@@@@@@@@@@ countlog_hakmilik :"+countlog_hakmilik);
		
		String display_maklumbalas = "N";
		if(count_hakmilik == countlog_hakmilik && count_hakmilik > 0)
		{
			Integer turutan_frm_etanah = 1;
			Hashtable hash_maklumatKeputusanMmk = null;
			hash_maklumatKeputusanMmk = getMaklumatKeputusanMmk_fromEtanah(jenis_skrin,
					no_fail_jkptg, db,turutan);
			
			if(hash_maklumatKeputusanMmk.size() > 0)
			{
			turutan_frm_etanah = Integer.parseInt((hash_maklumatKeputusanMmk.get("TURUTAN").toString()));	
			
			
			
					if(turutan_frm_etanah == (return_new_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db)))
					{
						display_maklumbalas = "Y";
					}
					else
					{
						display_maklumbalas = "N";
					}
			}
			
		}
        else
        {
           if(count_hakmilik > 0)
           {
        	   display_maklumbalas = "Y";
           }
        }
		
		
		myLogger.info("@@@@@@@@@@@@@ CURRENT TURUTAN :"+(return_current_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db)));
		myLogger.info("@@@@@@@@@@@@@ NEW TURUTAN :"+(return_new_turutan (id_fail,id_permohonan, id_hakmilik_etapp, jenis_skrin,db)));
		
		
		myLogger.info("::::::::: display_maklumbalas ::::::"+display_maklumbalas);
		
		if(display_maklumbalas.equals("Y"))
		{
			
			String id_hakmilik_etanah = "";
			
			Hashtable hash_maklumathakmilik = null;
			Hashtable hash_maklumatKeputusanMmk = null;		
			
			hash_maklumatKeputusanMmk = getMaklumatKeputusanMmk_fromEtanah(jenis_skrin,
						no_fail_jkptg, db,turutan);		
			context.put("hash_maklumatKeputusanMmk", hash_maklumatKeputusanMmk);
			context.put("hash_maklumatKeputusanMmk_size", hash_maklumatKeputusanMmk.size());
		}
	}
	
	private void hashMaklumatHMSambungan_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,Integer turutan) throws Exception {
		String no_fail_jkptg = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatHMS = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());		
			hash_maklumathakmilik = logic.getMaklumatHakmilik(id_fail,
					id_hakmilik_etapp, db);
			id_hakmilik_etanah = (hash_maklumathakmilik.get(
					"ID_HAKMILIK_ETANAH").toString() == null ? ""
					: hash_maklumathakmilik.get("ID_HAKMILIK_ETANAH")
							.toString());
			hash_maklumatHMS = getMaklumatHMSambungan_fromEtanah(
					jenis_skrin, no_fail_jkptg, id_hakmilik_etanah, db,turutan);
		context.put("hash_maklumatHMS", hash_maklumatHMS);
		context.put("hash_maklumatHMS_size", hash_maklumatHMS.size());
	}

	/*
	private void hashMaklumatWarta_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,String id_penarikan) throws Exception {
		String no_fail_jkptg = "";
		String no_rujukan_tarikbalik = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatWarta = null;
		Hashtable hash_maklumatTarikBalik = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? ""
				: hash_maklumatprojek.get("NO_FAIL").toString());
		if (jenis_skrin.equals("BorangC") || jenis_skrin.equals("BorangA")) {
			hash_maklumatWarta = getMaklumatWarta_fromEtanah(jenis_skrin,
					no_fail_jkptg,no_rujukan_tarikbalik, db);

		}
		else if (jenis_skrin.equals("TarikBalik")) {
			
			hash_maklumatTarikBalik = logic.getMaklumatTarikBalik(id_penarikan,db);
			no_rujukan_tarikbalik = (hash_maklumatTarikBalik.get("NO_PENARIKANBALIK").toString());
			
			hash_maklumatWarta = getMaklumatWarta_fromEtanah(jenis_skrin,
					no_fail_jkptg,no_rujukan_tarikbalik, db);

		}
		context.put("hash_maklumatWarta", hash_maklumatWarta);
		context.put("hash_maklumatWarta_size", hash_maklumatWarta.size());
	}
	
	
	private void hashPersetujuanBorangC_fromEtanah(String id_fail,
			String id_permohonan, String id_hakmilik_etapp, String jenis_skrin,
			Db db,String id_penarikan) throws Exception {
		String no_fail_jkptg = "";
		String no_rujukan_tarikbalik = "";
		String id_hakmilik_etanah = "";
		Hashtable hash_maklumatprojek = null;
		Hashtable hash_maklumathakmilik = null;
		Hashtable hash_maklumatPersetujuanBorangC = null;
		Hashtable hash_maklumatTarikBalik = null;
		hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
		no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? "": hash_maklumatprojek.get("NO_FAIL").toString());
		hash_maklumatPersetujuanBorangC = getMaklumatPersetujuanBorangC_fromEtanah(jenis_skrin,no_fail_jkptg,no_rujukan_tarikbalik, db);
		context.put("hash_maklumatPersetujuanBorangC", hash_maklumatPersetujuanBorangC);
		context.put("hash_maklumatPersetujuanBorangC_size", hash_maklumatPersetujuanBorangC.size());
	}
	
	*/

	// UPLOAD FILE
	private void uploadFiles(String id_permohonan, String id_fail,
		String id_hakmilik, String jenis_skrin, String tajuk,
		HttpSession session, Db db, Connection conn, String id_penarikan,String kategori_lampiran,Integer turutan)
		throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {

					double ds = item.getSize() / 1024 / 1024;
					DecimalFormat df = new DecimalFormat("#.00");

					// 2097152 = 2MB

					//off checking saiz dokumen
					/*
					if (item.getSize() > 2097152) {
						context.put("statusSend_doc", "no");
						context.put("statusMesej_doc",
								"<font color='red'><b><blink>Muat Naik Tidak Berjaya!</blink></b></font>");
						context.put(
								"errorMesej_doc",
								"<font color='red'><b>Saiz Dokumen adalah "
										+ df.format(ds)
										+ " MB. Sila Pastikan Saiz Dokumen Tidak Melebihi 2MB!</b></font>");
					} else {
						saveData(item, id_fail, id_permohonan, id_hakmilik,
								jenis_skrin, tajuk, session, db, conn,
								id_penarikan,kategori_lampiran);
					}*/

					saveData(item, id_fail, id_permohonan, id_hakmilik,
							jenis_skrin, tajuk, session, db, conn,
							id_penarikan,kategori_lampiran,turutan);
				}
			}
		}
	}

	public static String getExtension(FileItem f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	private void saveData(FileItem item, String id_fail, String id_permohonan,
		String id_hakmilik, String jenis_skrin, String tajuk,
		HttpSession session, Db db, Connection conn, String id_penarikan,String kategori_lampiran,Integer turutan)
		throws Exception {

		// Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		try {
			// db = new Db();
			myLogger.info("GET EXTENSION :" + getExtension(item));

			// TBLPPTDOKUMENINT
			long idDokumen = DB.getNextID("TBLPPTDOKUMENINT_SEQ");
			// conn = db.getConnection();
			// con.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO TBLPPTDOKUMENINT "
							+ "(ID_DOKUMENINT,TAJUK,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_PERMOHONAN,JENIS_DOKUMENINT_INTEGRASI,ID_HAKMILIK,JENIS_FORMAT_FAIL,ID_PENARIKANBALIK,KATEGORI,TURUTAN) "
							+ "VALUES(?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, tajuk);
			ps.setString(3, userId);
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize());
			ps.setString(5, item.getContentType());
			ps.setString(6, item.getName());
			ps.setString(7, id_permohonan);
			ps.setString(8, jenis_skrin);
			ps.setString(9, id_hakmilik);
			ps.setString(10, getExtension(item));
			ps.setString(11, id_penarikan);
			ps.setString(12, kategori_lampiran);
			ps.setInt(13, turutan);
			ps.executeUpdate();
			// conn.commit();

			// addDokumen("","hantarPelanChartingS8","JKPTG(S).SEL/01/881/01/2012/21","","","","","","","","","",item);

			DataHandler handler = new DataHandler(new CustomDataSource(item));

			myLogger.info("TEST TYPE ::::::::::::::::"+ handler.getContentType());
			myLogger.info("TEST NAME :::::::::::::::::" + handler.getName());
			myLogger.info("TEST INPUT STREAM :::::::::::::::::"+ handler.getInputStream());

			Hashtable hash_maklumatprojek = null;
			Hashtable hash_maklumathakmilik = null;
			hash_maklumatprojek = logic.getMaklumatProjek(id_fail, db);
			String no_fail_jkptg = (hash_maklumatprojek.get("NO_FAIL").toString() == null ? "" : hash_maklumatprojek.get("NO_FAIL").toString());

			String id_hakmilik_etanah = "";
			String kod_negeri = "";
			String kod_daerah = "";
			String kod_mukim = "";
			String kod_unit_hakmilik = "";
			String no_hakmilik = "";
			String default_tajuk_dokumen = "";
			
			if(!logic.getMaklumatKategori(kategori_lampiran,db).equals(""));{
				tajuk = logic.getMaklumatKategori(kategori_lampiran,db);
			}
		
			if (jenis_skrin.equals("hantarPelanChartingS8")
				|| jenis_skrin.equals("hantarPelanChartingS4")
				|| jenis_skrin.equals("BorangC")
				|| jenis_skrin.equals("BorangA")
				|| jenis_skrin.equals("BorangI")
				|| jenis_skrin.equals("MMK_S8")
				|| jenis_skrin.equals("MMK_S4")
				|| jenis_skrin.equals("WartaS4")
				|| jenis_skrin.equals("WartaS8")) {
							
				Tblintpptdokumen tblintpptdokumen_arr[] = new Tblintpptdokumen[1];
				tblintpptdokumen_arr[0] = addDokumen_return_obj("", jenis_skrin, no_fail_jkptg, "", "", "", "", "",
						tajuk, handler.getName(), handler.getContentType(), "",
						handler, (int) item.getSize(), getExtension(item), "",kategori_lampiran,id_fail,db,turutan);
				addDokumen_List(tblintpptdokumen_arr,id_fail, session, db);
				
				saveLogDokumen(id_fail, idDokumen, id_hakmilik, jenis_skrin,session, db, conn, id_penarikan,turutan);
			
			} else if (jenis_skrin.equals("BorangK")
					|| jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur")) {

				hash_maklumathakmilik = logic.getMaklumatHakmilik(id_fail,
						id_hakmilik, db);
				id_hakmilik_etanah = (hash_maklumathakmilik.get(
						"ID_HAKMILIK_ETANAH").toString() == null ? ""
						: hash_maklumathakmilik.get("ID_HAKMILIK_ETANAH")
								.toString());

				kod_negeri = (hash_maklumathakmilik.get("KOD_NEGERI")
						.toString() == null ? "" : hash_maklumathakmilik.get(
						"KOD_NEGERI").toString());
				kod_daerah = (hash_maklumathakmilik.get("KOD_DAERAH")
						.toString() == null ? "" : hash_maklumathakmilik.get(
						"KOD_DAERAH").toString());
				kod_mukim = (hash_maklumathakmilik.get("KOD_MUKIM").toString() == null ? ""
						: hash_maklumathakmilik.get("KOD_MUKIM").toString());
				kod_unit_hakmilik = (hash_maklumathakmilik.get(
						"KOD_JENIS_HAKMILIK").toString() == null ? ""
						: hash_maklumathakmilik.get("KOD_JENIS_HAKMILIK")
								.toString());
				no_hakmilik = (hash_maklumathakmilik.get("NO_HAKMILIK")
						.toString() == null ? "" : hash_maklumathakmilik.get(
						"NO_HAKMILIK").toString());								
				
				Tblintpptdokumen tblintpptdokumen_arr[] = new Tblintpptdokumen[1];
				tblintpptdokumen_arr[0] = addDokumen_return_obj(id_hakmilik_etanah, jenis_skrin, no_fail_jkptg,
						kod_unit_hakmilik, no_hakmilik, kod_daerah, kod_mukim,
						kod_negeri, tajuk, handler.getName(),
						handler.getContentType(), "", handler,
						(int) item.getSize(), getExtension(item), "",kategori_lampiran,id_fail,db,turutan);
				addDokumen_List(tblintpptdokumen_arr,id_fail, session, db);
							
				saveLogDokumen(id_fail, idDokumen, id_hakmilik, jenis_skrin,session, db, conn, id_penarikan,turutan);
			
			} 
			
		} finally {
			// if (db != null)
			// db.close();
		}
		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}

	
	private void saveLogMaklumatWarta(String id_fail, String jenis_skrin,
			HttpSession session, Db db,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO TBLLOGINTMAKLUMATWARTA "
						+ "(ID_FAIL,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,TURUTAN) "
						+ "VALUES(?,?,?,?,?)");

		ps.setString(1, id_fail);
		ps.setString(2, jenis_skrin);
		ps.setString(3, userId);
		ps.setString(4, dateNow);
		ps.setInt(5, turutan);
		ps.executeUpdate();
		con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}
	
	
	private void saveLogMaklumatPengambilan(String id_fail, String jenis_skrin,
			HttpSession session, Db db,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO TBLLOGINTMAKLUMATPPT "
						+ "(ID_FAIL,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,TURUTAN) "
						+ "VALUES(?,?,?,?,?)");

		ps.setString(1, id_fail);
		ps.setString(2, jenis_skrin);
		ps.setString(3, userId);
		ps.setString(4, dateNow);
		ps.setInt(5, turutan);
		ps.executeUpdate();
		con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}
	
	
	private void saveLogDerafMMK(String id_fail, String jenis_skrin,
			HttpSession session, Db db,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO TBLLOGINTDERAFMMK "
						+ "(ID_FAIL,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,TURUTAN) "
						+ "VALUES(?,?,?,?,?)");

		ps.setString(1, id_fail);
		ps.setString(2, jenis_skrin);
		ps.setString(3, userId);
		ps.setString(4, dateNow);
		ps.setInt(5, turutan);
		ps.executeUpdate();
		con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}
	
	
	private void saveLogDerafMMKPB(String id_penarikan, String jenis_skrin,
			HttpSession session, Db db,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO TBLLOGINTDERAFMMKPB "
						+ "(ID_PENARIKANBALIK,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,TURUTAN) "
						+ "VALUES(?,?,?,?,?)");

		ps.setString(1, id_penarikan);
		ps.setString(2, jenis_skrin);
		ps.setString(3, userId);
		ps.setString(4, dateNow);
		ps.setInt(5, turutan);
		ps.executeUpdate();
		con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}

	private void saveLog(String id_fail, String id_hakmilik,
			String jenis_skrin, HttpSession session, Db db,
			String id_penarikanbalik,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO TBLLOGINTHAKMILIK "
						+ "(ID_FAIL,ID_HAKMILIK,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,ID_PENARIKANBALIK,TURUTAN) "
						+ "VALUES(?,?,?,?,?,?,?)");

		ps.setString(1, id_fail);
		ps.setString(2, id_hakmilik);
		ps.setString(3, jenis_skrin);
		ps.setString(4, userId);
		ps.setString(5, dateNow);
		ps.setString(6, id_penarikanbalik);
		ps.setInt(7, turutan);
		ps.executeUpdate();
		con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}

	private void saveLogDokumen(String id_fail, long id_dokumen,
		String id_hakmilik, String jenis_skrin, HttpSession session, Db db,
		Connection conn, String id_penarikanbalik,Integer turutan) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String dateNow = dateFormat.format(cal.getTime());
		myLogger.info("dateNowLog :" + dateNow);

		String userId = (String) session.getAttribute("_ekptg_user_id");
		// Connection con = db.getConnection();
		// conn.setAutoCommit(false);
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO TBLLOGINTDOKUMEN "
						+ "(ID_FAIL,ID_DOKUMEN,ID_HAKMILIK,JENIS_SKRIN,ID_MASUK,TARIKH_LOG,ID_PENARIKANBALIK,TURUTAN) "
						+ "VALUES(?,?,?,?,?,?,?,?)");

		ps.setString(1, id_fail);
		ps.setLong(2, id_dokumen);
		ps.setString(3, id_hakmilik);
		ps.setString(4, jenis_skrin);
		ps.setString(5, userId);
		ps.setString(6, dateNow);
		ps.setString(7, id_penarikanbalik);
		ps.setInt(8, turutan);
		ps.executeUpdate();
		// con.commit();

		// this.context.put("completed", true);
		// this.context.put("close_window", "yes");
	}

	public void hapusDokumen(String id_dokumen, String jenis_skrin)
		throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPPTDOKUMENINT
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMENINT", id_dokumen);
			sql = r.getSQLDelete("TBLPPTDOKUMENINT");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusDokumenEtanah(String id_dokumen, String jenis_skrin)
			throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPPTDOKUMENFRMETANAH
			SQLRenderer r = new SQLRenderer();
			r.add("ROWID", id_dokumen);
			sql = r.getSQLDelete("TBLPPTDOKUMENFRMETANAH");

			myLogger.info(" DELETE DOKUMEN TBLPPTDOKUMENFRMETANAH :" + sql);

			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Hashtable getMaklumatEndorsan_fromEtanah(String jenis_skrin,
			String no_fail_jkptg, Db db, Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.NO_JILID, T.TARIKH_ENDORSAN, T.TARIKH_TERIMA_DATA FROM TBLPPTENDORSANFRMETANAH T "
					+ " WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("WartaS8")) {
				sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
				sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' AND T.TURUTAN = '"+turutan+"' ";
			}
			myLogger.info(" getMaklumatEndorsan_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NO_FAIL_JKPTG") == null) {
					h.put("NO_FAIL_JKPTG", "");
				} else {
					h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG")
							.toUpperCase());
				}
				if (rs.getString("FLAG_PROSES") == null) {
					h.put("FLAG_PROSES", "");
				} else {
					h.put("FLAG_PROSES", rs.getString("FLAG_PROSES")
							.toUpperCase());
				}
				if (rs.getString("NO_JILID") == null) {
					h.put("NO_JILID", "");
				} else {
					h.put("NO_JILID", rs.getString("NO_JILID").toUpperCase());
				}
				if (rs.getString("TARIKH_ENDORSAN") == null) {
					h.put("TARIKH_ENDORSAN", "");
				} else {
					h.put("TARIKH_ENDORSAN", rs.getString("TARIKH_ENDORSAN")
							.toUpperCase());
				}
				if (rs.getString("TARIKH_TERIMA_DATA") == null) {
					h.put("TARIKH_TERIMA_DATA", "");
				} else {
					h.put("TARIKH_TERIMA_DATA",
							rs.getString("TARIKH_TERIMA_DATA").toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	

	public static Hashtable getMaklumatWarta_fromEtanah(String jenis_skrin,
			String no_fail_jkptg,String no_rujukan_tarikbalik, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.NO_WARTA,  T.TARIKH_WARTA, T.TARIKH_TERIMA_DATA, T.NO_PERSERAHAN "
					+ " FROM TBLPPTWARTAFRMETANAH T WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' ";
			
			if(jenis_skrin.equals("BorangC") || jenis_skrin.equals("BorangA"))
			{
				sql += " AND T.NO_RUJUKAN_TARIKBALIK = '' ";
			}
			else if(jenis_skrin.equals("TarikBalik"))
			{ 
				sql += " AND T.NO_RUJUKAN_TARIKBALIK = '" + no_rujukan_tarikbalik + "' ";
			}

			myLogger.info(" getMaklumatWarta_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NO_FAIL_JKPTG") == null) {
					h.put("NO_FAIL_JKPTG", "");
				} else {
					h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG")
							.toUpperCase());
				}
				if (rs.getString("FLAG_PROSES") == null) {
					h.put("FLAG_PROSES", "");
				} else {
					h.put("FLAG_PROSES", rs.getString("FLAG_PROSES")
							.toUpperCase());
				}
				if (rs.getString("NO_WARTA") == null) {
					h.put("NO_WARTA", "");
				} else {
					h.put("NO_WARTA", rs.getString("NO_WARTA").toUpperCase());
				}
				if (rs.getString("TARIKH_WARTA") == null) {
					h.put("TARIKH_WARTA", "");
				} else {
					h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA")
							.toUpperCase());
				}
				if (rs.getString("NO_PERSERAHAN") == null) {
					h.put("NO_PERSERAHAN", "");
				} else {
					h.put("NO_PERSERAHAN", rs.getString("NO_PERSERAHAN")
							.toUpperCase());
				}
				if (rs.getString("TARIKH_TERIMA_DATA") == null) {
					h.put("TARIKH_TERIMA_DATA", "");
				} else {
					h.put("TARIKH_TERIMA_DATA",
							rs.getString("TARIKH_TERIMA_DATA").toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	public static Hashtable getMaklumatKeputusanMmk_fromEtanah(String jenis_skrin,
			String no_fail_jkptg, Db db,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.TARIKH_TERIMA_DATA, T.FLAG_KEPUTUSAN_MMK," +
					" (CASE WHEN T.FLAG_KEPUTUSAN_MMK = 'Y' THEN 'Lulus' " +
					" WHEN T.FLAG_KEPUTUSAN_MMK = 'T' THEN 'Tidak Lulus' " +
					" ELSE '' END) AS KEPUTUSAN_MMK," +
					" T.KETERANGAN_KEPUTUSAN_MMK,TURUTAN "+
					" FROM TBLPPTKEPUTUSANMMKFRMETANAH T WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' AND T.TURUTAN = '"+turutan+"' ";

			myLogger.info(" getMaklumatKeputusanMmk_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NO_FAIL_JKPTG") == null) {
					h.put("NO_FAIL_JKPTG", "");
				} else {
					h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG")
							.toUpperCase());
				}
				if (rs.getString("FLAG_PROSES") == null) {
					h.put("FLAG_PROSES", "");
				} else {
					h.put("FLAG_PROSES", rs.getString("FLAG_PROSES")
							.toUpperCase());
				}
				if (rs.getString("KEPUTUSAN_MMK") == null) {
					h.put("KEPUTUSAN_MMK", "");
				} else {
					h.put("KEPUTUSAN_MMK", rs.getString("KEPUTUSAN_MMK").toUpperCase());
				}
				if (rs.getString("FLAG_KEPUTUSAN_MMK") == null) {
					h.put("FLAG_KEPUTUSAN_MMK", "");
				} else {
					h.put("FLAG_KEPUTUSAN_MMK", rs.getString("FLAG_KEPUTUSAN_MMK").toUpperCase());
				}
				if (rs.getString("KETERANGAN_KEPUTUSAN_MMK") == null) {
					h.put("KETERANGAN_KEPUTUSAN_MMK", "");
				} else {
					h.put("KETERANGAN_KEPUTUSAN_MMK", rs.getString("KETERANGAN_KEPUTUSAN_MMK"));
				}
				if (rs.getString("TARIKH_TERIMA_DATA") == null) {
					h.put("TARIKH_TERIMA_DATA", "");
				} else {
					h.put("TARIKH_TERIMA_DATA",
							rs.getString("TARIKH_TERIMA_DATA").toUpperCase());
				}
				if (rs.getString("TURUTAN") == null) {
					h.put("TURUTAN", 1);
				} else {
					h.put("TURUTAN",
							rs.getInt("TURUTAN"));
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	public static Hashtable current_turutan_getMaklumatKeputusanMmk_fromEtanah(String jenis_skrin,
			String no_fail_jkptg, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT MAX(TURUTAN) AS CURRENT_TURUTAN "+
					" FROM TBLPPTKEPUTUSANMMKFRMETANAH T WHERE T.NO_FAIL_JKPTG IS NOT NULL  ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "'  ";

			myLogger.info(" current_turutan_getMaklumatKeputusanMmk_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				
				if (rs.getString("CURRENT_TURUTAN") == null) {
					h.put("CURRENT_TURUTAN", 1);
				} else {
					h.put("CURRENT_TURUTAN",
							rs.getInt("CURRENT_TURUTAN"));
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	
	public static Hashtable getMaklumatHMSambungan_fromEtanah(
			String jenis_skrin, String no_fail_jkptg,
			String id_hakmilik_etanah, Db db,Integer turutan) throws Exception {
			String sql = "";		
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT T.ID_HAKMILIK, T.FLAG_PROSES, T.NO_FAIL_JKPTG, T.NO_NOT_BARU, T.NO_SYIT, T.NO_PA, T.NO_PU, T.LUAS_PA, T.KOD_LUAS_PA, T.TARIKH_TERIMA_DATA, T.KOD_NEGERI, T.KOD_DAERAH,"+ 
					" T.KOD_MUKIM, T.KOD_UNIT_HAKMILIK, T.NO_HAKMILIK,T.FLAG_HAKMILIK_DIDAFTARKAN," +
					" (CASE WHEN T.FLAG_HAKMILIK_DIDAFTARKAN = 'Y' THEN 'Hakmilik Telah Didaftarkan'" +
					"  WHEN T.FLAG_HAKMILIK_DIDAFTARKAN = 'T' THEN 'Hakmilik Tidak Berjaya Didaftarkan' " +
					" ELSE '' END) AS STATUS_PENDAFTARAN_HAKMILIK "+
					" FROM TBLPPTHMSAMBUNGANFRMETANAH T "
					+ " WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			sql += " AND T.ID_HAKMILIK = '" + id_hakmilik_etanah + "' ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' AND T.TURUTAN = '"+turutan+"' ";
			myLogger.info(" getMaklumatHAKMILIKSAMBUNGAN_fromEtanah ::::::::::::" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("ID_HAKMILIK") == null) {h.put("ID_HAKMILIK", "");
				} else {h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK"));}
				if (rs.getString("NO_FAIL_JKPTG") == null) {h.put("NO_FAIL_JKPTG", "");
				} else {h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG"));}
				if (rs.getString("NO_NOT_BARU") == null) {h.put("NO_NOT_BARU", "");
				} else {h.put("NO_NOT_BARU", rs.getString("NO_NOT_BARU"));}
				if (rs.getString("NO_SYIT") == null) {h.put("NO_SYIT", "");
				} else {h.put("NO_SYIT", rs.getString("NO_SYIT"));}
				if (rs.getString("NO_PA") == null) {h.put("NO_PA", "");
				} else {h.put("NO_PA", rs.getString("NO_PA"));}
				if (rs.getString("NO_PU") == null) {h.put("NO_PU", "");
				} else {h.put("NO_PU", rs.getString("NO_PU"));}
				if (rs.getString("LUAS_PA") == null) {h.put("LUAS_PA", "");
				} else {h.put("LUAS_PA", rs.getString("LUAS_PA"));}
				if (rs.getString("KOD_LUAS_PA") == null) {h.put("KOD_LUAS_PA", "");
				} else {h.put("KOD_LUAS_PA", rs.getString("KOD_LUAS_PA"));}
				if (rs.getString("FLAG_HAKMILIK_DIDAFTARKAN") == null) {h.put("FLAG_HAKMILIK_DIDAFTARKAN", "");
				} else {h.put("FLAG_HAKMILIK_DIDAFTARKAN", rs.getString("FLAG_HAKMILIK_DIDAFTARKAN"));}
				if (rs.getString("STATUS_PENDAFTARAN_HAKMILIK") == null) {h.put("STATUS_PENDAFTARAN_HAKMILIK", "");
				} else {h.put("STATUS_PENDAFTARAN_HAKMILIK", rs.getString("STATUS_PENDAFTARAN_HAKMILIK"));}
				
			}
			return h;
		
	}

	public static Hashtable getMaklumatEndorsanBK_fromEtanah(
			String jenis_skrin, String no_fail_jkptg,
			String id_hakmilik_etanah, Db db,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "  SELECT T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.NO_JILID, T.ID_HAKMILIK, T.TARIKH_ENDORSAN, T.TARIKH_TERIMA_DATA FROM TBLPPTENDORSANFRMETANAH T "
					+ " WHERE T.NO_FAIL_JKPTG IS NOT NULL AND ROWNUM < 2 ";
			sql += " AND T.ID_HAKMILIK = '" + id_hakmilik_etanah + "' ";
			sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' AND T.TURUTAN = '"+turutan+"' ";

			myLogger.info(" getMaklumatEndorsan_fromEtanah ::::::::::::" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NO_FAIL_JKPTG") == null) {
					h.put("NO_FAIL_JKPTG", "");
				} else {
					h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG")
							.toUpperCase());
				}
				if (rs.getString("FLAG_PROSES") == null) {
					h.put("FLAG_PROSES", "");
				} else {
					h.put("FLAG_PROSES", rs.getString("FLAG_PROSES")
							.toUpperCase());
				}
				if (rs.getString("NO_JILID") == null) {
					h.put("NO_JILID", "");
				} else {
					h.put("NO_JILID", rs.getString("NO_JILID").toUpperCase());
				}
				if (rs.getString("TARIKH_ENDORSAN") == null) {
					h.put("TARIKH_ENDORSAN", "");
				} else {
					h.put("TARIKH_ENDORSAN", rs.getString("TARIKH_ENDORSAN")
							.toUpperCase());
				}
				if (rs.getString("TARIKH_TERIMA_DATA") == null) {
					h.put("TARIKH_TERIMA_DATA", "");
				} else {
					h.put("TARIKH_TERIMA_DATA",
							rs.getString("TARIKH_TERIMA_DATA").toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Hashtable> listSenaraiDokumen_fromEtanah(
			String id_permohonan, String id_hakmilik_etanah,
			String jenis_skrin, String no_fail_jkptg, String kod_unit_hakmilik,
			String no_hakmilik, String kod_negeri, String kod_daerah,
			String kod_mukim, Db db) throws Exception {

		List listSenaraiDokumen_fromEtanah = null;
		// Db db = null;
		String sql = "";
		try {

			// db = new Db();
			Statement stmt = db.getStatement();

			sql = "  SELECT T.ROWID AS ROW_ID,T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.TAJUK_DOKUMEN, T.NAMA_DOKUMEN, T.CONTENT_UPLOAD, "
					+ " T.JENIS_FAIL,  T.TARIKH_TERIMA_DATA, T.KOD_UNIT_HAKMILIK, T.NO_HAKMILIK, T.KOD_NEGERI, T.KOD_DAERAH, T.KOD_MUKIM, "
					+ " T.ID_HAKMILIK AS ID_HAKMILIK_ETANAH FROM TBLPPTDOKUMENFRMETANAH T WHERE T.NO_FAIL_JKPTG IS NOT NULL ";

			if (jenis_skrin.equals("BorangC") || jenis_skrin.equals("BorangI")  || jenis_skrin.equals("BorangA") || jenis_skrin.equals("WartaS4") || jenis_skrin.equals("WartaS8")
					|| jenis_skrin.equals("TarikBalik")) {
				sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
				sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' ";
			} else if (jenis_skrin.equals("BorangK")					
					|| jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur")) {
				sql += " AND T.FLAG_PROSES = '" + jenis_skrin + "' ";
				sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' ";
				sql += " AND T.ID_HAKMILIK = '" + id_hakmilik_etanah
						+ "'  ";
			}

			/*
			 * sql += " AND T.KOD_UNIT_HAKMILIK = '"+kod_unit_hakmilik+"' " +
			 * "AND T.NO_HAKMILIK = '"+no_hakmilik+"' " +
			 * " AND T.KOD_NEGERI = '"
			 * +kod_negeri+"' AND T.KOD_DAERAH = '"+kod_daerah
			 * +"' AND  T.KOD_MUKIM = '"+kod_mukim+"' ";
			 */

			myLogger.info(" SENARAI DOKUMENINT INTEGRATION FROM ETANAH :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			listSenaraiDokumen_fromEtanah = Collections
					.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ROW_ID",
						rs.getString("ROW_ID") == null ? "" : rs
								.getString("ROW_ID"));
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG"));
				h.put("FLAG_PROSES", rs.getString("FLAG_PROSES") == null ? ""
						: rs.getString("FLAG_PROSES"));
				h.put("TAJUK_DOKUMEN",
						rs.getString("TAJUK_DOKUMEN") == null ? "" : rs
								.getString("TAJUK_DOKUMEN"));
				h.put("NAMA_DOKUMEN", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				// h.put("CONTENT_UPLOAD", rs.getString("CONTENT_UPLOAD")==
				// null?"":rs.getString("CONTENT_UPLOAD"));
				h.put("JENIS_FAIL", rs.getString("JENIS_FAIL") == null ? ""
						: rs.getString("JENIS_FAIL"));
				h.put("TARIKH_TERIMA_DATA",
						rs.getString("TARIKH_TERIMA_DATA") == null ? "" : rs
								.getString("TARIKH_TERIMA_DATA"));
				h.put("KOD_UNIT_HAKMILIK",
						rs.getString("KOD_UNIT_HAKMILIK") == null ? "" : rs
								.getString("KOD_UNIT_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI"));
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH"));
				h.put("KOD_MUKIM",
						rs.getString("KOD_MUKIM") == null ? "" : rs
								.getString("KOD_MUKIM"));
				h.put("ID_HAKMILIK_ETANAH",
						rs.getString("ID_HAKMILIK_ETANAH") == null ? "" : rs
								.getString("ID_HAKMILIK_ETANAH"));
				listSenaraiDokumen_fromEtanah.add(h);
				bil++;
			}

		} finally {
			// if (db != null) db.close();
		}
		return listSenaraiDokumen_fromEtanah;

	}// close setListDokumen

	@SuppressWarnings("unchecked")
	public static List<Hashtable> listSyor_fromEtanah(String id_permohonan,
			String id_hakmilik_etanah, String jenis_skrin,
			String no_fail_jkptg, String kod_unit_hakmilik, String no_hakmilik,
			String kod_negeri, String kod_daerah, String kod_mukim, Db db)
			throws Exception {

		List listSyor_fromEtanah = null;
		// Db db = null;
		String sql = "";
		try {

			// db = new Db();
			Statement stmt = db.getStatement();

			sql = "  SELECT ROWID AS ROW_ID,T.NO_FAIL_JKPTG, T.FLAG_PROSES, T.TURUTAN,  T.SYOR_PENTADBIR_TANAH, T.TARIKH_TERIMA_DATA FROM TBLPPTSYOTPTFRMETANAH T ";
			sql += " WHERE T.FLAG_PROSES = '" + jenis_skrin + "' ";
			sql += " AND T.NO_FAIL_JKPTG = '" + no_fail_jkptg + "' "
					+ " ORDER BY TURUTAN ASC ";

			myLogger.info(" listSyor_fromEtanah:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			listSyor_fromEtanah = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ROW_ID",
						rs.getString("ROW_ID") == null ? "" : rs
								.getString("ROW_ID"));
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG"));
				h.put("FLAG_PROSES", rs.getString("FLAG_PROSES") == null ? ""
						: rs.getString("FLAG_PROSES"));
				h.put("TURUTAN",
						rs.getString("TURUTAN") == null ? "" : rs
								.getString("TURUTAN"));
				h.put("SYOR_PENTADBIR_TANAH",
						rs.getString("SYOR_PENTADBIR_TANAH") == null ? "" : rs
								.getString("SYOR_PENTADBIR_TANAH"));
				h.put("TARIKH_TERIMA_DATA",
						rs.getString("TARIKH_TERIMA_DATA") == null ? "" : rs
								.getString("TARIKH_TERIMA_DATA"));
				listSyor_fromEtanah.add(h);
				bil++;
			}

		} finally {
			// if (db != null) db.close();
		}
		return listSyor_fromEtanah;

	}// close listSyor_fromEtanah

	@SuppressWarnings("unchecked")
	public static List<Hashtable> listSenaraiDokumen(String id_permohonan,
			String id_hakmilik, String jenis_skrin, String id_penarikan, Db db)
			throws Exception {

		List listSenaraiDokumen = null;
		// Db db = null;
		String sql = "";
		try { // db = new Db();
			Statement stmt = db.getStatement();

			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("MMK_S8")
					|| jenis_skrin.equals("MMK_S4")
					|| jenis_skrin.equals("BorangK")
					|| jenis_skrin.equals("BorangI")
					|| jenis_skrin.equals("PU")
					|| jenis_skrin.equals("WartaS4")
					|| jenis_skrin.equals("WartaS8")
					|| jenis_skrin.equals("SijilUkur")) {

				sql = " SELECT DISTINCT K.KETERANGAN_LAMPIRANETANAH, F.NO_FAIL AS NO_FAIL_JKPTG,D.ID_DOKUMENINT,D.NAMA_FAIL,D.JENIS_MIME,D.TAJUK,D.KETERANGAN,"
						+ " D.JENIS_DOKUMENINT_INTEGRASI,D.ID_HAKMILIK,D.KATEGORI  ";
				sql += " FROM TBLPPTDOKUMENINT D,TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTKATEGORILAMPIRANETANAH K ";
				sql += " WHERE D.ID_PERMOHONAN = '"
						+ id_permohonan
						+ "' AND D.ID_PERMOHONAN = P.ID_PERMOHONAN AND D.KATEGORI = K.ID_LAMPIRANETANAH(+)   AND P.ID_FAIL = F.ID_FAIL   "
						+ " AND D.JENIS_DOKUMENINT_INTEGRASI  = '"
						+ jenis_skrin + "' AND D.ID_PENARIKANBALIK IS NULL ";
				if (!id_hakmilik.equals("") && !id_hakmilik.equals(null)) {
					sql += " AND ID_HAKMILIK = '" + id_hakmilik + "' ";
				}

			} else if (jenis_skrin.equals("TarikBalik")) {
				sql = " SELECT DISTINCT K.KETERANGAN_LAMPIRANETANAH, F.NO_FAIL AS NO_FAIL_JKPTG,D.ID_DOKUMENINT,D.NAMA_FAIL,D.JENIS_MIME,D.TAJUK,D.KETERANGAN,"
						+ " D.JENIS_DOKUMENINT_INTEGRASI,D.ID_HAKMILIK,D.KATEGORI  ";
				sql += " FROM TBLPPTDOKUMENINT D,TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLPPTKATEGORILAMPIRANETANAH K ";
				sql += " WHERE D.ID_PERMOHONAN = '"
						+ id_permohonan
						+ "' AND D.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL   "
						+ " AND D.KATEGORI = K.ID_LAMPIRANETANAH(+)  AND D.JENIS_DOKUMENINT_INTEGRASI  = '"
						+ jenis_skrin + "' AND D.ID_PENARIKANBALIK = '"
						+ id_penarikan + "' ";
				if (!id_hakmilik.equals("") && !id_hakmilik.equals(null)) {
					sql += " AND ID_HAKMILIK = '" + id_hakmilik + "' ";
				}
			}

			myLogger.info(" SENARAI DOKUMENINT INTEGRATION :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			listSenaraiDokumen = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMENINT") == null ? ""
						: rs.getString("ID_DOKUMENINT"));
				h.put("NAMA_FAIL",
						rs.getString("NAMA_FAIL") == null ? "" : rs
								.getString("NAMA_FAIL"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("TAJUK",
						rs.getString("TAJUK") == null ? "" : rs
								.getString("TAJUK"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("JENIS_DOKUMEN_INTEGRASI", rs
						.getString("JENIS_DOKUMENINT_INTEGRASI") == null ? ""
						: rs.getString("JENIS_DOKUMENINT_INTEGRASI"));
				// Blob b = rs.getBlob("CONTENT");
				// h.put("CONTENT",rs.getBlob("CONTENT")== null?
				// null:rs.getBlob("CONTENT"));

				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG"));
				
				h.put("KETERANGAN_LAMPIRANETANAH",
						rs.getString("KETERANGAN_LAMPIRANETANAH") == null ? "" : rs
								.getString("KETERANGAN_LAMPIRANETANAH"));
				
				h.put("KATEGORI",
						rs.getString("KATEGORI") == null ? "" : rs
								.getString("KATEGORI"));

				listSenaraiDokumen.add(h);
				bil++;
			}

		} finally {
			// if (db != null) db.close();
		}
		return listSenaraiDokumen;

	}// close setListDokumen
	
	
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
	   
	 
}
