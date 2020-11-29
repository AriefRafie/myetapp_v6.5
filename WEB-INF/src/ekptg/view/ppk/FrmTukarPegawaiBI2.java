package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.CacheManager;
import ekptg.engine.CachedObject;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;
import ekptg.model.RazTemplete;
import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;
import ekptg.model.ppk.FrmTukarPegawaiBI2Data;

public class FrmTukarPegawaiBI2 extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(FrmTukarPegawaiBI2.class);
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmTukarPegawaiBI2Data modelTukarPegawai = new FrmTukarPegawaiBI2Data();
	BicaraInteraktifData modelBI = new BicaraInteraktifData();
	FrmPrmhnnSek8BicaraData modelBicaraData = new FrmPrmhnnSek8BicaraData();
	FrmPrmhnnSek8KptsanBicaraData modelKeputusanPerbicaraan = new FrmPrmhnnSek8KptsanBicaraData();
	
	@SuppressWarnings("unused")
	public String doTemplate2() throws Exception{
		HttpSession session = this.request.getSession();
		String vm = "";
		String doPost = (String)session.getAttribute("doPost");
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		Map getDetailUsers = modelTukarPegawai.getDetailUsers(session, "", USER_ID_SYSTEM, "", null);
		
		List listPermohonanTukarPegawai = null;
		Map h_listBicaraMohonMultiple = null;
		
		vm = "app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp";
		if (submit.equals("hantar")) {
			
	
		}
		if (submit.equals("batal")) {
			vm = "app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp";
		}
		if (submit.equals("tutup")) {
			vm = "app/ppk/dashboard.jsp";
		}
		//if (getDetailUsers = ) {
			
		//}
		vm = "app/ppk/TukarPegawai2/historyTukarPegawai2.jsp";
		vm = "app/ppk/TukarPegawai2/listTukarPegawai2.jsp";
		
		return vm;
	}
}

/*
 * List fail tukar pegawai 2:
 * 1. ekptg.view.ppk.FrmTukarPegawaiBI2.java
 * 2. app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp
 * 3. app/ppk/TukarPegawai2/historyTukarPegawai2.jsp
 * 4. app/ppk/TukarPegawai2/listTukarPegawai2.jsp
 * 5. ekptg.model.ppk.FrmTukarPegawaiBI2Data.java
 * */
