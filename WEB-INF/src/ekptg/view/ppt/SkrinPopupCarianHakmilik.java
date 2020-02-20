package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;
import ekptg.model.ppt.PPTHeader;

public class SkrinPopupCarianHakmilik extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupCarianHakmilik.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH = "app/ppt/PopupHakmilik/";
	private String vm = PATH + "SkrinPopupCarianHakmilik.jsp";

	HttpSession session = null;
	String action = null;

	public String doTemplate2() throws Exception {
		session = request.getSession();
		action = getParam("action");
		String command = getParam("command");
		myLogger.info(" command popup : " + command);
		this.context.put("command", command);
		String id_permohonan = getParam("id_permohonan");
		this.context.put("id_permohonan", id_permohonan);
		String id_daerah = getParam("id_daerah");
		this.context.put("id_daerah", id_daerah);
		String id_negeri = getParam("id_negeri");
		this.context.put("id_negeri", id_negeri);
		String flag_skrin = getParam("flag_skrin");
		this.context.put("flag_skrin", flag_skrin);
		this.context.put("NO_LOT", "");
		this.context.put("NAMA_PB", "");
		this.context.put("NO_PB", "");
		String flag_subjaket = "";
		this.context.put("flag_subjaket", "");
		PPTHeader header = new PPTHeader();
		context.put("showSJ", "no");
		myLogger.info("command :" + command);
		context.put("refreshHakmilik", "");
		String id_pegawai = getParam("id_pegawai");
		this.context.put("tutup_skrin_popup", "");

		String id_borange = getParam("id_borange");
		this.context.put("id_borange", id_borange);
		context.put("isEdit", "no");

		if (flag_skrin.equals("skrin_list_hakmilik_pb_sek8")) {
			// context.put("showSJ", "yes");
		}
		

		Db db = null;
		try {
			db = new Db();

			if ("janaSubjaketManual".equals(command)) {
				context.put("showSJ", "yes");
				context.put("refreshHakmilik", "yes");

			}
			if ("simpanSj".equals(command)) {
				context.put("showSJ", "no");
				simpanSj(
						session,
						id_permohonan,
						db,
						getHakmilik(id_permohonan, flag_skrin,
								getParam("NO_LOT"), getParam("NAMA_PB"),
								getParam("NO_PB"), db, id_pegawai, id_borange));
				context.put("refreshHakmilik", "yes");
			} else if ("janaSubjaket".equals(command)) {
				janaSubjaket(session, id_permohonan, db,
						getListSeqSubjaket(id_permohonan, db));
				context.put("refreshHakmilik", "yes");
			}

			else if ("kemaskiniBorangL".equals(command)) {
				context.put("isEdit", "yes");
			} else if ("simpanBorangL".equals(command)) {
				context.put("isEdit", "no");
				simpanBorangL(session, id_permohonan, db);
			}

			header.setDataHeader(id_permohonan);
			Vector dataHeader = header.getDataHeader();
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				flag_subjaket = dh.get("flag_subjaket").toString();
			}
			this.context.put("flag_subjaket", flag_subjaket);

			this.context.put("NO_LOT", getParam("NO_LOT"));
			this.context.put("NAMA_PB", getParam("NAMA_PB"));
			this.context.put("NO_PB", getParam("NO_PB"));
			displayHakmilik(id_permohonan, flag_skrin, action,
					getParam("NO_LOT"), getParam("NAMA_PB"), getParam("NO_PB"),
					db, id_pegawai, id_borange);
			context.put("saiz_listTanah",
					getTanah_count(id_permohonan, "", "", db));

		} finally {

			if (db != null)
				db.close();
		}

		return vm;
	}

	public static void simpanBorangL(String idUser, String id_hakmilik,
			long id_borangl, String dateBorangL, String statusBL,
			String tempoh, Db db) throws Exception {

		// Db db = null;
		String sql = "";

		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_borangl", id_borangl);
			r.add("id_hakmilik", id_hakmilik);
			r.add("tempoh", tempoh);
			r.add("tarikh_borangl",
					r.unquote("to_date('" + dateBorangL + "','dd/MM/yyyy')"));
			r.add("jenis_pilih", statusBL);
			r.add("id_masuk", idUser);
			r.add("tarikh_masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptborangl");
			stmt.executeUpdate(sql);

		}// close try
		finally {
			// if (db != null) db.close();
		}// close finally

	}// close simpanBorangL

	public static void updateBorangL(String idUser, String id_hakmilik,
			String id_borangl, String dateBorangL, String statusBL,
			String tempoh, Db db) throws Exception {

		// Db db = null;
		String sql = "";

		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_borangl", id_borangl);
			r.update("id_hakmilik", id_hakmilik);
			r.add("tarikh_borangl",
					r.unquote("to_date('" + dateBorangL + "','dd/MM/yyyy')"));
			r.add("jenis_pilih", statusBL);
			r.add("tempoh", tempoh);
			r.add("id_kemaskini", idUser);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptborangl");
			stmt.executeUpdate(sql);

		}// close try
		finally {
			// if (db != null) db.close();
		}// close finally

	}// close updateBorangL

	private void simpanBorangL(HttpSession session, String idpermohonan, Db db)
			throws Exception {

		Hashtable h = new Hashtable();
		String idUser = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_permohonan", idpermohonan);

		h.put("txdTarikhBorangL", getParam("txdTarikhBorangL"));
		h.put("txtTempoh", getParam("txtTempoh"));

		h.put("id_user", idUser);

		String[] cb_id_hakmilik = request.getParameterValues("id_hakmilik");

		if (cb_id_hakmilik != null) {
			for (int i = 0; i < cb_id_hakmilik.length; i++) {
				int bil = i + 1;
				String dateBorangL = getParam("txdTarikhBorangL" + bil);
				String socStatusBL = getParam("sorStatusBorangL" + bil);
				String id_borangl = getParam("id_borangl" + bil);
				String tempoh = getParam("txtTempoh" + bil);

				if (id_borangl.equals("")) {
					long id_borangl_seq = DB.getNextID("TBLPPTBORANGL_SEQ");
					simpanBorangL(idUser, cb_id_hakmilik[i], id_borangl_seq,
							dateBorangL, socStatusBL, tempoh, db);
				} else {
					updateBorangL(idUser, cb_id_hakmilik[i], id_borangl,
							dateBorangL, socStatusBL, tempoh, db);
				}

			}
		}

	}// close simpanBorangL

	Vector listSeqSubjaket = null;

	public Vector getListSeqSubjaket(String idPermohonan, Db db)
			throws Exception {

		listSeqSubjaket = new Vector();

		// Db db = null;
		listSeqSubjaket.clear();
		String sql = "";

		try {
			// db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT M.ID_HAKMILIK, M.NO_SUBJAKET, MK.NAMA_MUKIM, ";

			sql += " CASE ";
			sql += " WHEN M.no_lot IS NOT NULL AND M.no_pt IS NULL THEN M.no_lot ";
			sql += " WHEN M.no_lot IS NULL AND M.no_pt IS NULL THEN LT.keterangan || M.no_pt ";
			sql += " WHEN M.no_lot IS NULL AND M.no_pt IS NOT NULL THEN  LT.keterangan || M.no_pt ";
			sql += " WHEN M.no_lot IS NOT NULL AND M.no_pt IS NOT NULL THEN LT.keterangan || M.no_pt || CHR(32) || CHR(40) || M.no_lot || CHR(41) ";
			sql += " ELSE '' ";
			sql += " END AS NO_LOTSUB ";

			sql += " FROM TBLPPTHAKMILIK M, TBLRUJLOT LT, TBLRUJMUKIM MK ";
			sql += " WHERE M.ID_PERMOHONAN = '" + idPermohonan + "'";
			sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";
			sql += " AND M.ID_LOT = LT.ID_LOT(+)";
			sql += " AND NVL(M.flag_pembatalan_keseluruhan,0) <> 'Y' ";
			sql += " AND NVL(M.flag_penarikan_keseluruhan,0) <> 'Y'";

			// sql +=
			// " ORDER BY LPAD(M.NO_SUBJAKET,10) asc, LPAD(M.no_lot,10) asc, LPAD(M.no_pt,10) asc, LPAD(NO_LOTSUB,10) asc, MK.NAMA_MUKIM asc";
			sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTSUB,20) asc, LPAD(m.no_subjaket,20) asc";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				listSeqSubjaket.addElement(h);
				bil++;
			}
			return listSeqSubjaket;
		} finally {
			// if(db != null) db.close();
		}
	}// close setDataListKertas

	private void janaSubjaket(HttpSession session, String idpermohonan, Db db,
			Vector listSeqSubjaket) throws Exception {

		// String[] listIdHM = request.getParameterValues("ListIdHM");
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		/*
		 * if((listIdHM!=null)){ int bil = 0; for (int i = 0; i <
		 * listIdHM.length; i++) { bil++;
		 * janaSubjaket(listIdHM[i],bil,idpermohonan,id_user,db); } }
		 */
		int bil = 0;
		for (int i = 0; i < listSeqSubjaket.size(); i++) {
			Hashtable getSub = (Hashtable) listSeqSubjaket.get(i);
			String id_hakmilik = getSub.get("id_hakmilik").toString() == null ? ""
					: getSub.get("id_hakmilik").toString();
			bil++;
			janaSubjaket(id_hakmilik, bil, idpermohonan, id_user, db);
		}

	}// close janaSubjaket

	public static void janaSubjaket(String idHakmilik, int bil,
			String idpermohonan, String id_user, Db db) throws Exception {

		// Db db = null;
		String sql = "";

		try {

			// db = new Db();
			Statement stmt = db.getStatement();

			SQLRenderer r = new SQLRenderer();
			r.update("id_hakmilik", idHakmilik);
			r.add("no_subjaket", bil);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_user);
			sql = r.getSQLUpdate("Tblppthakmilik");
			myLogger.info("janaSubjaket 1 :" + sql);
			stmt.executeUpdate(sql);

			r.clear();

			// update flag di tblpptpermohonan
			r.update("id_permohonan", idpermohonan);
			r.add("flag_subjaket", "1");
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_user);
			sql = r.getSQLUpdate("Tblpptpermohonan");
			myLogger.info("janaSubjaket 2 :" + sql);
			stmt.executeUpdate(sql);

		}// close try
		finally {
			// if (db != null) db.close();
		}// close finally

	}// close janaSubjaket

	public int getTanah_count(String idPermohonan, String lot,
			String idpegawai, Db db) throws Exception {

		// Db db = null;
		String sql = "";

		try {
			// db = new Db();
			Statement stmt = db.getStatement();

			sql += "SELECT count(*) as total_hakmilik ";
			sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, ";
			sql += " Tblrujdaerah d, Users u";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
			sql += " AND m.id_negeri = n.id_negeri ";
			sql += " AND p.id_fail = f.id_fail ";
			sql += " AND m.id_daerah = d.id_daerah(+)";
			sql += " AND ls.id_luas(+) = m.id_unitluaslot ";
			sql += " AND m.id_pegawai = u.user_id(+)";
			sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
			sql += " AND m.id_lot = lt.id_lot(+) ";
			sql += " AND m.id_mukim = mk.id_mukim(+) ";
			sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
			sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
			sql += " AND p.id_Permohonan = '" + idPermohonan + "'";

			myLogger.info(" get total tanah : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int total_hakmilik = 0;
			while (rs.next()) {
				total_hakmilik = rs.getString("total_hakmilik") == null ? 0
						: rs.getInt("total_hakmilik");

			}
			return total_hakmilik;
		} finally {
			// if(db != null) db.close();
		}
	}

	private void simpanSj(HttpSession session, String idpermohonan, Db db,
			Vector list_hakmilik) throws Exception {
		/*
		 * String[] listIdHM = request.getParameterValues("ListIdHM"); String[]
		 * txtSj = request.getParameterValues("txtSj"); String id_user =
		 * (String)session.getAttribute("_ekptg_user_id");
		 * 
		 * if((listIdHM!=null && txtSj!=null)){
		 * 
		 * for (int i = 0; i < listIdHM.length; i++){
		 * simpanSj(listIdHM[i],txtSj[i],idpermohonan,id_user,db);
		 * 
		 * } }
		 */
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		for (int i = 0; i < list_hakmilik.size(); i++) {
			Hashtable getidH = (Hashtable) list_hakmilik.get(i);
			String id_hakmilik = getidH.get("id_hakmilik").toString() == null ? ""
					: getidH.get("id_hakmilik").toString();

			String id_hakmilik_sj_val = getParam(id_hakmilik + "txtSj");
			myLogger.info("id_hakmilik from SJ :" + id_hakmilik);
			myLogger.info("value from SJ :" + id_hakmilik_sj_val);
			if (!id_hakmilik_sj_val.equals(null) && id_hakmilik_sj_val != null) {
				simpanSj(id_hakmilik, id_hakmilik_sj_val, idpermohonan,
						id_user, db);
			}
		}

	}

	public static void simpanSj(String idHakmilik, String sj,
			String idpermohonan, String id_user, Db db) throws Exception {

		// Db db = null;
		String sql = "";

		try {

			// db = new Db();
			Statement stmt = db.getStatement();

			SQLRenderer r = new SQLRenderer();
			r.update("id_hakmilik", idHakmilik);
			r.add("no_subjaket", sj);
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_user);
			sql = r.getSQLUpdate("Tblppthakmilik");
			stmt.executeUpdate(sql);

			r.clear();

			// update flag di tblpptpermohonan
			r.update("id_permohonan", idpermohonan);
			r.add("flag_subjaket", "1");
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_kemaskini", id_user);
			sql = r.getSQLUpdate("Tblpptpermohonan");
			stmt.executeUpdate(sql);

		}// close try
		finally {
			// if (db != null) db.close();
		}// close finally

	}// close simpanSj

	/*
	 * private void displayFail(String NO_FAIL,String NAMA_PROJEK,String
	 * ID_NEGERI,String ID_DAERAH,Db db) throws Exception{ List<Hashtable> list
	 * = null; list = getFail(NO_FAIL,NAMA_PROJEK,ID_NEGERI,ID_DAERAH,db);
	 * context.put("SenaraiFail", list); setupPage(session,action,list); }
	 */

	private void displayHakmilik(String id_permohonan, String flag_skrin,
			String action, String no_lot, String nama_pb, String no_pb, Db db,
			String id_pegawai, String id_borange) throws Exception {
		List<Hashtable> list = null;
		list = getHakmilik(id_permohonan, flag_skrin, no_lot, nama_pb, no_pb, db, id_pegawai, id_borange);
		context.put("SenaraiFail", list);
		setupPage(session, action, list);
	}

	/*
	 * Hashtable maklumatPermohonan = null; public Hashtable
	 * maklumatPermohonan(String id_permohonan,Db db) throws Exception {
	 * maklumatPermohonan = new Hashtable(); maklumatPermohonan.clear(); // Db
	 * db = null; String sql = ""; try { // db = new Db(); Statement stmt =
	 * db.getStatement(); SQLRenderer r = new SQLRenderer(); sql =
	 * " SELECT P.ID_PERMOHONAN,F.NO_FAIL,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,F.ID_FAIL,UPPER(P.TUJUAN) AS PROJEK,D.NAMA_DAERAH AS NAMA_DAERAH,D.ID_DAERAH, N.NAMA_NEGERI AS NAMA_NEGERI, N.ID_NEGERI,F.ID_SUBURUSAN, "
	 * +
	 * " TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY')  AS TARIKH_MOHON, (SELECT COUNT(*) FROM TBLPPTHAKMILIK H WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN) AS TOTAL_HAKMILIK "
	 * +
	 * " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJDAERAH D,TBLRUJNEGERI N "+
	 * " WHERE P.ID_FAIL = F.ID_FAIL " +
	 * " AND P.ID_DAERAH = D.ID_DAERAH  AND F.ID_NEGERI = N.ID_NEGERI AND P.ID_PERMOHONAN = '"
	 * +id_permohonan+"' " ;
	 * 
	 * myLogger.info("SELECT maklumatPermohonan :"+sql); ResultSet rs =
	 * stmt.executeQuery(sql);
	 * 
	 * Hashtable h; h = new Hashtable(); while (rs.next()) {
	 * 
	 * if (rs.getString("ID_PERMOHONAN") == null) { h.put("ID_PERMOHONAN", "");
	 * } else { h.put("ID_PERMOHONAN",
	 * rs.getString("ID_PERMOHONAN").toUpperCase()); } if
	 * (rs.getString("NO_FAIL") == null) { h.put("NO_FAIL", ""); } else {
	 * h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_PTD") == null) { h.put("NO_RUJUKAN_PTD", ""); }
	 * else { h.put("NO_RUJUKAN_PTD",
	 * rs.getString("NO_RUJUKAN_PTD").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_UPT") == null) { h.put("NO_RUJUKAN_UPT", ""); }
	 * else { h.put("NO_RUJUKAN_UPT",
	 * rs.getString("NO_RUJUKAN_UPT").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_PTG") == null) { h.put("NO_RUJUKAN_PTG", ""); }
	 * else { h.put("NO_RUJUKAN_PTG",
	 * rs.getString("NO_RUJUKAN_PTG").toUpperCase()); } if
	 * (rs.getString("PROJEK") == null) { h.put("PROJEK", ""); } else {
	 * h.put("PROJEK", rs.getString("PROJEK").toUpperCase()); } if
	 * (rs.getString("NAMA_DAERAH") == null) { h.put("NAMA_DAERAH", ""); } else
	 * { h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH").toUpperCase()); } if
	 * (rs.getString("NAMA_NEGERI") == null) { h.put("NAMA_NEGERI", ""); } else
	 * { h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase()); } if
	 * (rs.getString("TARIKH_MOHON") == null) { h.put("TARIKH_MOHON", ""); }
	 * else { h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON").toUpperCase());
	 * } if (rs.getString("TOTAL_HAKMILIK") == null) { h.put("TOTAL_HAKMILIK",
	 * ""); } else { h.put("TOTAL_HAKMILIK",
	 * rs.getString("TOTAL_HAKMILIK").toUpperCase()); }
	 * 
	 * } return h; } finally { // if (db != null) // db.close(); } }
	 */

	/*
	 * Vector list_fail = null; public Vector getFail(String NO_FAIL,String
	 * NAMA_PROJEK,String ID_NEGERI,String ID_DAERAH,Db db) throws Exception {
	 * 
	 * 
	 * myLogger.info("NO_FAIL :"+NO_FAIL);
	 * myLogger.info("NAMA_PROJEK :"+NAMA_PROJEK);
	 * myLogger.info("ID_NEGERI :"+ID_NEGERI);
	 * myLogger.info("ID_DAERAH :"+ID_DAERAH);
	 * 
	 * 
	 * list_fail = new Vector(); list_fail.clear(); //Db db = null; String sql =
	 * ""; try { //db = new Db(); Statement stmt = db.getStatement();
	 * SQLRenderer r = new SQLRenderer();
	 * 
	 * 
	 * 
	 * sql =
	 * " SELECT P.ID_PERMOHONAN,F.NO_FAIL,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,F.ID_FAIL,UPPER(P.TUJUAN) AS PROJEK,D.NAMA_DAERAH AS NAMA_DAERAH,D.ID_DAERAH, N.NAMA_NEGERI AS NAMA_NEGERI, N.ID_NEGERI,F.ID_SUBURUSAN, "
	 * +
	 * " TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY')  AS TARIKH_MOHON, (SELECT COUNT(*) FROM TBLPPTHAKMILIK H WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN) AS TOTAL_HAKMILIK "
	 * +
	 * " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJDAERAH D,TBLRUJNEGERI N "+
	 * " WHERE P.ID_FAIL = F.ID_FAIL " +
	 * " AND P.ID_DAERAH = D.ID_DAERAH  AND F.ID_NEGERI = N.ID_NEGERI " +
	 * " AND F.ID_SUBURUSAN = '51'" + " ";
	 * 
	 * 
	 * if (ID_NEGERI != null) { if (!ID_NEGERI.equals("")) {
	 * 
	 * sql += " AND F.ID_NEGERI = '" + ID_NEGERI + "' "; } }
	 * 
	 * if (ID_DAERAH != null) { if (!ID_DAERAH.equals("")) {
	 * 
	 * sql += " AND P.ID_DAERAH = '" + ID_DAERAH + "' "; } }
	 * 
	 * 
	 * if (NO_FAIL != null) { if (!NO_FAIL.trim().equals("")) {
	 * 
	 * sql += " AND (UPPER(F.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase().trim() +
	 * "%'  OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + NO_FAIL.toUpperCase().trim() +
	 * "%' OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + NO_FAIL.toUpperCase().trim() +
	 * "%' OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + NO_FAIL.toUpperCase().trim() +
	 * "%') "; } }
	 * 
	 * if (NAMA_PROJEK != null) { if (!NAMA_PROJEK.trim().equals("")) {
	 * 
	 * sql += " AND UPPER(P.TUJUAN) LIKE '%" + NAMA_PROJEK.toUpperCase().trim()
	 * + "%'";
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * 
	 * sql +=
	 * "  AND  ROWNUM < 50 ORDER BY TO_DATE(TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY'),'DD/MM/YYYY') DESC "
	 * ;
	 * 
	 * 
	 * 
	 * 
	 * myLogger.info("LIST FAIL :"+sql.toUpperCase());
	 * 
	 * 
	 * 
	 * stmt.setFetchSize(10); ResultSet rs = stmt.executeQuery(sql); int bil =
	 * 0; while (rs.next()) { bil = bil + 1; Hashtable h = new Hashtable();
	 * h.put("BIL", bil); if (rs.getString("ID_PERMOHONAN") == null) {
	 * h.put("ID_PERMOHONAN", ""); } else { h.put("ID_PERMOHONAN",
	 * rs.getString("ID_PERMOHONAN").toUpperCase()); } if
	 * (rs.getString("NO_FAIL") == null) { h.put("NO_FAIL", ""); } else {
	 * h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_PTD") == null) { h.put("NO_RUJUKAN_PTD", ""); }
	 * else { h.put("NO_RUJUKAN_PTD",
	 * rs.getString("NO_RUJUKAN_PTD").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_UPT") == null) { h.put("NO_RUJUKAN_UPT", ""); }
	 * else { h.put("NO_RUJUKAN_UPT",
	 * rs.getString("NO_RUJUKAN_UPT").toUpperCase()); } if
	 * (rs.getString("NO_RUJUKAN_PTG") == null) { h.put("NO_RUJUKAN_PTG", ""); }
	 * else { h.put("NO_RUJUKAN_PTG",
	 * rs.getString("NO_RUJUKAN_PTG").toUpperCase()); } if
	 * (rs.getString("PROJEK") == null) { h.put("PROJEK", ""); } else {
	 * h.put("PROJEK", rs.getString("PROJEK").toUpperCase()); } if
	 * (rs.getString("NAMA_DAERAH") == null) { h.put("NAMA_DAERAH", ""); } else
	 * { h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH").toUpperCase()); } if
	 * (rs.getString("NAMA_NEGERI") == null) { h.put("NAMA_NEGERI", ""); } else
	 * { h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase()); } if
	 * (rs.getString("TARIKH_MOHON") == null) { h.put("TARIKH_MOHON", ""); }
	 * else { h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON").toUpperCase());
	 * } if (rs.getString("TOTAL_HAKMILIK") == null) { h.put("TOTAL_HAKMILIK",
	 * ""); } else { h.put("TOTAL_HAKMILIK",
	 * rs.getString("TOTAL_HAKMILIK").toUpperCase()); } list_fail.addElement(h);
	 * } return list_fail; } finally { //if (db != null) //db.close(); } }
	 */

	Vector list_hakmilik = null;

	public Vector getHakmilik(String id_permohonan, String flag_skrin,
			String no_lot, String nama_pb, String no_pb, Db db,
			String idpegawai, String id_borange) throws Exception {
		// temp

		String noLOT = no_lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		list_hakmilik = new Vector();
		list_hakmilik.clear();
		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			 * sql =
			 * " SELECT (J.KOD_JENIS_HAKMILIK || ' ' || H.NO_HAKMILIK) AS NO_HAKMILIK, H.NO_LOT,UPPER(M.NAMA_MUKIM) AS NAMA_MUKIM, H.ID_PERMOHONAN "
			 * + " FROM TBLPPTHAKMILIK H, TBLRUJJENISHAKMILIK J,TBLRUJMUKIM M "+
			 * " WHERE H.ID_JENISHAKMILIK = J.ID_JENISHAKMILIK(+) " +
			 * //" AND H.ID_PERMOHONAN = '"+id_permohonan+"' "+
			 * " AND H.ID_MUKIM = M.ID_MUKIM ORDER BY NAMA_MUKIM,NO_LOT ";
			 */
			/*
			 * if(flag_skrin.equals("daftar_sementara")||
			 * flag_skrin.equals("senarai_pampasan_sementara") ||
			 * flag_skrin.equals("senarai_siasatan_sementara") ||
			 * flag_skrin.equals("daftar_sek8") ||
			 * flag_skrin.equals("skrin_hakmilik_sek4") ||
			 * flag_skrin.equals("daftar_sek4")||
			 * flag_skrin.equals("skrin_hakmilik_sek8")){
			 */sql += " SELECT * FROM (";
			// }

			sql += " "
					+
					// "SELECT *    FROM ( "+
					" SELECT  ROW_NUMBER () OVER (ORDER BY MK.NAMA_MUKIM ASC, LPAD (M.NO_LOT, 20) ASC, LPAD (M.NO_PT, 20) ASC, LPAD (M.NO_SUBJAKET, 20) ASC) AS RN, G.TARIKH_BORANGH, K.TARIKH_BORANGK, "
					+ " M.FLAG_SEGERA_SEBAHAGIAN, m.flag_pembatalan_keseluruhan,m.flag_penarikan_keseluruhan,P.NO_RUJUKAN_PTG, P.ID_STATUS, F.NO_FAIL, M.CATATAN, P.ID_PERMOHONAN, LS.KETERANGAN AS UNIT1, "
					+ "LT.KETERANGAN AS UNIT2, M.ID_HAKMILIK, M.ID_NEGERI," 
					+ " M.TARIKH_MASUK AS TARIKH_MASUK,  ";

			if (!idpegawai.equals("") && idpegawai != null) {
				sql += "U.USER_NAME AS NAMA_PEGAWAI, ";
			} else {
				sql += "'' AS NAMA_PEGAWAI, ";
			}

			if (!id_borange.equals("") && id_borange != null) {
				sql += "B.ID_BORANGE AS ID_BORANGE, ";
			} else {
				sql += "'' AS ID_BORANGE, ";
			}

			if (!id_permohonan.equals("") && id_permohonan != null
					&& flag_skrin.equals("bantahan_mahkamah")) {
				sql += " BAN.FLAG_ONLINE,BAN.STATUS_BANTAHAN_AP,SB.KETERANGAN, ";
			} else {
				sql += " '' as FLAG_ONLINE,'' as STATUS_BANTAHAN_AP,'' as KETERANGAN, ";
			}

			if (flag_skrin.equals("senarai_siasatan")) // ||
														// flag_skrin.equals("senarai_siasatan_sementara"))
			{
				sql += " SS.ID_SIASATAN, ";
				
			} else {
				sql += " ''  AS ID_SIASATAN,";
			}

			if (flag_skrin.equals("hakmilik_borangk")
					|| flag_skrin.equals("hakmilik_borangL")) {
				sql += " LL.id_borangl, LL.tarikh_borangl, LL.jenis_pilih,  LL.tempoh, ";
			} else {
				sql += " '' as id_borangl, '' as tarikh_borangl, '' as jenis_pilih,  '' as tempoh, ";
			}

			sql += " N.NAMA_NEGERI,  "
					+
					// "(SELECT COUNT(A.ID_HAKMILIKPB) FROM TBLPPTHAKMILIKPB A, TBLPPTPIHAKBERKEPENTINGAN B  WHERE A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN AND A.ID_HAKMILIK(+) = M.ID_HAKMILIK  "+
					// " AND A.ID_JENISPB NOT IN (40,41,42))AS TOTALPB,  " +
					" NVL(TEMP_COUNTPB.TOTALPB,0) AS TOTALPB, "
					+ "M.ID_JENISHAKMILIK, M.ID_DAERAH, M.ID_MUKIM, MK.NAMA_MUKIM, M.ID_UNITLUASLOT, M.LUAS_AMBIL,   "
					+ " M.NO_HAKMILIK, M.NO_LOT, M.LUAS_LOT, M.NO_PT, M.TARIKH_DAFTAR, M.TARIKH_LUPUT, M.TEMPOH_LUPUT, JH.KOD_JENIS_HAKMILIK,  M.LOKASI,M.SYARAT_NYATA,M.SYARAT_KHAS, "
					+ " M.SEKATAN_HAK,M.SEKATAN_KEPENTINGAN,M.NO_SYIT, JH.KETERANGAN AS JENIS_HAKMILIK, M.ID_KATEGORITANAH,  M.FLAG_AMBIL_SEGERA,D.NAMA_DAERAH,M.FLAG_BORANGL, M.STATUS_BORANGL,  "
					+ " M.TARIKH_TERIMA_HM, JH.STATUS_HAKMILIK,  M.FLAG_JENIS_RIZAB, M.NAMA_LAIN_RIZAB, M.NO_WARTA_RIZAB, M.TARIKH_WARTA_RIZAB, M.NO_SUBJAKET,  "
					+ " M.ID_PEGAWAI, M.ID_UNITLUASAMBIL_CONVERT, M.ID_UNITLUASLOT_CONVERT,  CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND  "
					+ " M.NO_PT IS NULL THEN LT.KETERANGAN || M.NO_PT  WHEN M.NO_LOT IS NULL AND M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   "
					+ " WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41)  ELSE 'TIADA'  END AS NO_LOTPT, M.SEKSYEN   "
					+ " FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLRUJLOT LT, ";

			sql += " (SELECT PHM.ID_HAKMILIK, COUNT(HPB.ID_PIHAKBERKEPENTINGAN) AS TOTALPB "
					+ " FROM TBLPPTHAKMILIK PHM, "
					+ " TBLPPTHAKMILIKPB HPB, TBLPPTPIHAKBERKEPENTINGAN PPB "
					+ " WHERE PHM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HPB.ID_JENISPB NOT IN (40, 41, 42) "
					+ " AND PHM.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " GROUP BY PHM.ID_HAKMILIK) TEMP_COUNTPB, ";

			if (!id_permohonan.equals("") && id_permohonan != null
					&& flag_skrin.equals("bantahan_mahkamah")) {
				sql += " TBLPPTBANTAHAN BAN, TBLRUJSTATUS SB, ";
			}

			if (!idpegawai.equals("") && idpegawai != null) {
				sql += " USERS U,TBLPPTAGIHANHM AGHM, ";
			}

			if (!id_borange.equals("") && id_borange != null) {
				sql += " TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B,";
			}

			if (flag_skrin.equals("bukti_notis_borangK")
					|| flag_skrin.equals("senarai_pu")) {
				sql += " Tblpptborangk BK, Tblppthakmilikborangk HBK, ";
			}

			// sql +=
			// " AND (select count(*)from Tblpptborangk a, Tblppthakmilikborangk b where a.id_borangk = b.id_borangk and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";

			if (flag_skrin.equals("senarai_siasatan")) {
				//open komen temp by razman
				//sql += " TBLPPTBORANGE BE,TBLPPTBORANGEHAKMILIK BEH, ";
				//close komen temp by razman
			}

			if (flag_skrin.equals("hakmilik_pampasan")
					|| flag_skrin.equals("senarai_pampasan_sementara")) {
				// sql += " Tblpptsiasatan SS, ";
			}

			if (flag_skrin.equals("hakmilik_pampasan")
					|| flag_skrin.equals("senarai_pampasan_sementara")
					|| flag_skrin.equals("senarai_siasatan")) {
				sql += " (SELECT MSS.ID_SIASATAN,MSS.ID_HAKMILIK FROM TBLPPTSIASATAN MSS, "
						+ " (SELECT MAX(SS1.BIL_SIASATAN) AS BIL, SS1.ID_HAKMILIK FROM TBLPPTSIASATAN SS1   "
						+ " GROUP BY SS1.ID_HAKMILIK) CS  "
						+ " WHERE MSS.ID_HAKMILIK = CS.ID_HAKMILIK AND MSS.BIL_SIASATAN = CS.BIL) SS, ";
			}

			if (flag_skrin.equals("hakmilik_borangk")
					|| flag_skrin.equals("hakmilik_borangL")) {
				sql += " Tblpptborangl LL, ";
			}

			sql += "TBLRUJLUAS LS, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH,  TBLRUJDAERAH D, TBLPPTBORANGG G, TBLPPTBORANGK K  "
					+ " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN  "
					+ " AND M.ID_NEGERI = N.ID_NEGERI  "
					+ " AND M.ID_PERMOHONAN = K.ID_PERMOHONAN  "
					+ " AND P.ID_FAIL = F.ID_FAIL  "
					+ " AND M.ID_DAERAH = D.ID_DAERAH "
					+ " AND TEMP_COUNTPB.ID_HAKMILIK(+) = M.ID_HAKMILIK "
					+ " AND LS.ID_LUAS(+) = M.ID_UNITLUASLOT   "
					+ " AND M.ID_BORANGG = G.ID_BORANGG(+)"
					// " AND M.ID_PEGAWAI = U.USER_ID(+) " +
					+ " AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND M.ID_LOT = LT.ID_LOT(+)  "
					+ " AND M.ID_MUKIM = MK.ID_MUKIM(+)  AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y'   "
					+ " AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
			sql += " AND P.ID_PERMOHONAN = '" + id_permohonan + "' ";

			/*
			 * if(flag_skrin.equals("daftar_sementara") ||
			 * flag_skrin.equals("senarai_siasatan_sementara") ||
			 * flag_skrin.equals("daftar_sek8") ||
			 * flag_skrin.equals("skrin_hakmilik_sek4") ||
			 * flag_skrin.equals("daftar_sek4") ||
			 * flag_skrin.equals("skrin_hakmilik_sek8")){
			 */
			

			if (nama_pb != "" && nama_pb != null) {
				if (!nama_pb.equals("")) {
					sql += " AND M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "
							+ " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "
							+ " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
							+ " AND M1.ID_HAKMILIK = M.ID_HAKMILIK  "
							+ "  "
							+ " AND upper(PB1.NAMA_PB) LIKE ('%"
							+ nama_pb.toUpperCase() + "%')) ";
				}
			}
			

			if (no_pb != "" && no_pb != null) {
				if (!no_pb.equals("")) {
					sql += " AND M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "
							+ " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "
							+ " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
							+ " AND M1.ID_HAKMILIK = M.ID_HAKMILIK  "
							+ "  "
							+ " AND upper(PB1.NO_PB) LIKE ('%"
							+ no_pb.toUpperCase() + "%')) ";
				}
			}

			if (no_pb != "" && no_pb != null) {
				if (!no_pb.equals("")) {
					sql += " AND M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "
							+ " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "
							+ " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
							+ " AND M1.ID_HAKMILIK = M.ID_HAKMILIK  "
							+ "  "
							+ " AND upper(PB1.NO_PB) LIKE ('%"
							+ no_pb.toUpperCase() + "%')) ";
				}
			}
			
			if (!flag_skrin.equals("senarai_pampasan_sementara")
					&& (!flag_skrin.equals("kemasukan_borangF"))
					&& (!flag_skrin.equals("senarai_siasatan"))
					&& (!flag_skrin.equals("hakmilik_pampasan"))
					&& (!flag_skrin.equals("hakmilik_borangk"))
					&& (!flag_skrin.equals("hakmilik_borangL"))
					&& (!flag_skrin.equals("bukti_notis_borangK"))
					&& (!flag_skrin.equals("senarai_pu"))
					&& (!flag_skrin.equals("laporan_awal_tanah"))
					&& (!flag_skrin.equals("bantahan_mahkamah"))
					&& (!flag_skrin.equals("papar_lot_borangE"))) {
				sql += ")";
			}

			if (flag_skrin.equals("senarai_siasatan")) {
				//open komen temp by razman
				//sql += " AND BEH.ID_HAKMILIK = M.ID_HAKMILIK AND BEH.ID_BORANGE = BE.ID_BORANGE ";
				//close komen temp by razman
				sql += " AND SS.ID_HAKMILIK(+) = M.ID_HAKMILIK";
				
				sql += ")";
			}

			if (!id_permohonan.equals("") && id_permohonan != null
					&& flag_skrin.equals("bantahan_mahkamah")) {
				sql += " AND M.ID_HAKMILIK = BAN.ID_HAKMILIK(+) AND BAN.ID_HAKMILIKPB IS NULL  ";
				sql += " AND BAN.STATUS_BANTAHAN_AP = SB.ID_STATUS(+) ";
			}

			if (flag_skrin.equals("hakmilik_pampasan")
					|| flag_skrin.equals("senarai_pampasan_sementara")) {
				sql += " AND SS.id_hakmilik = M.id_hakmilik " /*
															 * +
															 * " AND SS.id_siasatan IN "
															 * + //
															 * " (SELECT MAX(SS.ID_SIASATAN) FROM TBLPPTSIASATAN SS WHERE M.ID_HAKMILIK = SS.ID_HAKMILIK) "
															 * ;
															 * " (SELECT ID_SIASATAN FROM TBLPPTSIASATAN MSS, "
															 * +
															 * " (SELECT MAX(SS.BIL_SIASATAN) AS BIL, SS.ID_HAKMILIK FROM TBLPPTSIASATAN SS  "
															 * +
															 * " GROUP BY SS.ID_HAKMILIK) CS "
															 * +
															 * " WHERE MSS.ID_HAKMILIK = CS.ID_HAKMILIK AND MSS.BIL_SIASATAN = CS.BIL)"
															 */
						+ " ";

				sql += " AND (select count(aw.id_award) from tblpptaward aw where aw.id_siasatan = SS.id_siasatan) > 0 ";
			}

			if (flag_skrin.equals("senarai_pampasan_sementara")) {
				sql += ")";
			}

			if (!id_borange.equals("") && id_borange != null) {
				sql += " AND  A.ID_BORANGE = B.ID_BORANGE ";
				sql += "AND A.ID_HAKMILIK = M.ID_HAKMILIK ";
				sql += "AND B.ID_BORANGE = '" + id_borange + "' ";

			}

			if (flag_skrin.equals("kemasukan_borangF")) {
				sql += ")";
			}

			if (flag_skrin.equals("bukti_notis_borangK")
					|| flag_skrin.equals("senarai_pu")) {
				sql += " AND BK.id_borangk = HBK.id_borangk and HBK.id_hakmilik = M.id_hakmilik ";
			}

			// sql +=
			// " AND (select count(*)from Tblpptborangk a, Tblppthakmilikborangk b where a.id_borangk = b.id_borangk and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";

			if (flag_skrin.equals("hakmilik_borangk") 
					|| flag_skrin.equals("hakmilik_borangL")) {
				sql += " AND M.id_hakmilik = LL.id_hakmilik(+) ";
				sql += " AND (P.flag_segera = '3' AND M.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
				sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
				sql += " and bx.cara_bayar in ('1','2') AND hx.flag_segera_sebahagian = 'N' ) ";
				sql += " OR P.flag_segera = '3' AND M.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx ";
				sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";
				sql += " and (select count(*) from Tblpptborangi bix ";
				sql += " where bix.id_permohonan = p.id_permohonan) > 0 ";
				sql += " OR p.flag_segera = '2' AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b ";
				sql += " where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > 0 ";
				sql += " OR p.flag_segera = '1' " +
						//" AND (select count(*) from Tblpptborangi bix where bix.id_permohonan = p.id_permohonan) > 0" +
						" ) ";

			}

			/*
			 * if(flag_skrin.equals("daftar_sementara") ||
			 * flag_skrin.equals("senarai_pampasan_sementara") ||
			 * flag_skrin.equals("senarai_siasatan_sementara") ||
			 * flag_skrin.equals("daftar_sek8") ||
			 * flag_skrin.equals("skrin_hakmilik_sek4") ||
			 * flag_skrin.equals("daftar_sek4")){ sql
			 * +="    WHERE UPPER(no_lotpt) LIKE '%" +
			 * noLOT.toUpperCase().trim() + "%'   "; }
			 */

			if (flag_skrin.equals("hakmilik_borangk")
					|| flag_skrin.equals("hakmilik_borangL")
					|| flag_skrin.equals("hakmilik_pampasan")
					|| flag_skrin.equals("bukti_notis_borangK")
					|| flag_skrin.equals("senarai_pu")) {
				sql += ")";
			}


			if (!idpegawai.equals("") && idpegawai != null) {
				sql += " AND AGHM.ID_HAKMILIK = M.ID_HAKMILIK  ";
				sql += " AND AGHM.USER_ID = U.USER_ID  ";
				sql += " AND AGHM.BARIS = '2'  ";
			}

			if (!idpegawai.equals("") && idpegawai != null) {
				sql += " AND U.USER_ID  = '" + idpegawai + "'";
			}

			/*
			 * if ((no_lot != "" && no_lot != null) &&
			 * !flag_skrin.equals("daftar_sementara") &&
			 * !flag_skrin.equals("senarai_siasatan_sementara") &&
			 * !flag_skrin.equals("senarai_pampasan_sementara") &&
			 * !flag_skrin.equals("daftar_sek8") &&
			 * !flag_skrin.equals("skrin_hakmilik_sek4") &&
			 * !flag_skrin.equals("daftar_sek4") ) { if (!noLOT.equals("")) {
			 * sql += " AND (" + "UPPER(m.no_lot) LIKE '%" +
			 * noLOT.toUpperCase().trim() + "%' " +
			 * " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase().trim() +
			 * "%' " + " OR UPPER(lt.keterangan) LIKE '%" +
			 * noLOT.toUpperCase().trim() + "%' "+ " ) "; } }
			 */

			/*
			 * 
			 * if (id_borange != "" && id_borange != null) { if
			 * (!id_borange.equals("")) { sql += " AND M.ID_HAKMILIK IN ( "; sql
			 * += "SELECT M.ID_HAKMILIK  "; sql +=
			 * "FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK M "
			 * ; sql += "WHERE A.ID_BORANGE = B.ID_BORANGE "; sql +=
			 * "AND A.ID_HAKMILIK = M.ID_HAKMILIK "; sql +=
			 * "AND B.ID_BORANGE = '"+id_borange+"') "; } }
			 */

			/*
			 * if(flag_skrin.equals("daftar_sementara") ||
			 * flag_skrin.equals("senarai_pampasan_sementara") ||
			 * flag_skrin.equals("senarai_siasatan_sementara") ||
			 * flag_skrin.equals("daftar_sek8") ||
			 * flag_skrin.equals("skrin_hakmilik_sek4") ||
			 * flag_skrin.equals("daftar_sek4")) {
			 */
			if (flag_skrin.equals("laporan_awal_tanah") ||  (flag_skrin.equals("bantahan_mahkamah"))
				|| (flag_skrin.equals("papar_lot_borangE"))) {
				sql += ")";
			}
			
			sql += "    WHERE UPPER(no_lotpt) LIKE '%"
					+ noLOT.toUpperCase().trim() + "%'   ";
			
			sql += " ORDER BY NAMA_MUKIM ASC, TARIKH_MASUK ASC, LPAD(NO_LOT,20) ASC, LPAD(NO_PT,20) ASC, LPAD(NO_LOTPT,20) ASC, LPAD(NO_SUBJAKET,20) ASC"
					+
					// ")  WHERE RN >= 0  AND  RN <= 50 " +
					"";
			// }
			/*
			 * else{ sql +=
			 * " ORDER BY MK.NAMA_MUKIM ASC, LPAD(M.NO_LOT,20) ASC, LPAD(M.NO_PT,20) ASC, LPAD(NO_LOTPT,20) ASC, LPAD(M.NO_SUBJAKET,20) ASC"
			 * + //")  WHERE RN >= 0  AND  RN <= 50 " + ""; }
			 */

			myLogger.info("LIST HAKMILIK :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);

				h.put("id_borangl", rs.getString("id_borangl") == null ? ""
						: rs.getString("id_borangl"));
				h.put("jenis_pilih", rs.getString("jenis_pilih") == null ? ""
						: rs.getString("jenis_pilih"));
				h.put("tarikh_borangl",
						rs.getDate("tarikh_borangl") == null ? "" : Format
								.format(rs.getDate("tarikh_borangl")));

				if (rs.getString("jenis_pilih") != null
						&& rs.getString("jenis_pilih") != "") {
					if (rs.getString("jenis_pilih").equals("1")) {
						h.put("status_borang_l", "Hakmilik Belum Diterima");
					} else if (rs.getString("jenis_pilih").equals("2")) {
						h.put("status_borang_l", "Hakmilik Telah Diterima");
					}
				} else {
					h.put("status_borang_l", "");
				}

				/*
				 * if (rs.getString("NO_HAKMILIK") == null) {
				 * h.put("NO_HAKMILIK", ""); } else { h.put("NO_HAKMILIK",
				 * rs.getString("NO_HAKMILIK").toUpperCase()); } if
				 * (rs.getString("NO_LOT") == null) { h.put("NO_LOT", ""); }
				 * else { h.put("NO_LOT", rs.getString("NO_LOT").toUpperCase());
				 * } if (rs.getString("NAMA_MUKIM") == null) {
				 * h.put("NAMA_MUKIM", ""); } else { h.put("NAMA_MUKIM",
				 * rs.getString("NAMA_MUKIM").toUpperCase()); }
				 */
				
				
				
				h.put("id_siasatan", rs.getString("ID_SIASATAN") == null ? ""
					: rs.getString("ID_SIASATAN"));
				
				
				h.put("rn",
						rs.getString("rn") == null ? "" : rs.getString("rn"));
				h.put("id_borange", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("flag_segera_sebahagian",
						rs.getString("flag_segera_sebahagian") == null ? ""
								: rs.getString("flag_segera_sebahagian"));
				h.put("seksyen",
						rs.getString("seksyen") == null ? "" : rs
								.getString("seksyen"));
				h.put("listLOT", listLOT.toUpperCase());
				h.put("listLOTHM", listLOTHM.toUpperCase());
				h.put("nama2Mukim", nama2Mukim.toUpperCase());
				h.put("nama2MukimInit", nama2Mukim);
				h.put("bil", bil);
				h.put("totalPB",
						rs.getString("totalPB") == null ? "" : rs
								.getString("totalPB"));
				h.put("kod_jenis_hakmilik",
						rs.getString("kod_jenis_hakmilik") == null ? "" : rs
								.getString("kod_jenis_hakmilik"));
				h.put("no_rujukan_ptg",
						rs.getString("no_rujukan_ptg") == null ? "" : rs
								.getString("no_rujukan_ptg"));
				h.put("id_status",
						rs.getString("id_status") == null ? "" : rs
								.getString("id_status"));
				h.put("no_lotpt",
						rs.getString("NO_LOTPT") == null ? "" : rs
								.getString("NO_LOTPT"));
				h.put("no_fail",
						rs.getString("no_fail") == null ? "" : rs
								.getString("no_fail"));
				h.put("no_subjaket", rs.getString("no_subjaket") == null ? ""
						: rs.getString("no_subjaket"));
				h.put("id_pegawai", rs.getString("id_pegawai") == null ? ""
						: rs.getString("id_pegawai"));
				h.put("id_permohonan",
						rs.getString("id_Permohonan") == null ? "" : rs
								.getString("id_Permohonan"));
				h.put("nama_pegawai", rs.getString("nama_pegawai") == null ? ""
						: rs.getString("nama_pegawai"));
				h.put("kod_lot",
						rs.getString("unit2") == null ? "" : rs
								.getString("unit2"));
				h.put("unitluaslot",
						rs.getString("unit1") == null ? "" : rs
								.getString("unit1"));
				h.put("id_hakmilik", rs.getString("id_hakmilik") == null ? ""
						: rs.getString("id_hakmilik"));
				h.put("id_negeri",
						rs.getString("id_negeri") == null ? "" : rs
								.getString("id_negeri"));
				h.put("nama_negeri", rs.getString("nama_negeri") == null ? ""
						: rs.getString("nama_negeri"));
				h.put("id_jenishakmilik",
						rs.getString("id_jenishakmilik") == null ? "" : rs
								.getString("id_jenishakmilik"));
				h.put("id_daerah",
						rs.getString("id_daerah") == null ? "" : rs
								.getString("id_daerah"));
				h.put("id_mukim",
						rs.getString("id_mukim") == null ? "" : rs
								.getString("id_mukim"));
				h.put("nama_mukim", rs.getString("nama_mukim") == null ? ""
						: rs.getString("nama_mukim"));
				h.put("id_unitluaslot",
						rs.getString("id_unitluaslot") == null ? "" : rs
								.getString("id_unitluaslot"));
				h.put("flag_ambil_segera",
						rs.getString("flag_ambil_segera") == null ? "" : rs
								.getString("flag_ambil_segera"));
				h.put("nama_daerah", rs.getString("nama_daerah") == null ? ""
						: rs.getString("nama_daerah"));
				h.put("status_borangl",
						rs.getString("status_borangl") == null ? "" : rs
								.getString("status_borangl"));
				h.put("tarikh_terima_hm",
						rs.getDate("tarikh_terima_hm") == null ? "" : Format
								.format(rs.getDate("tarikh_terima_hm")));
				h.put("status_hakmilik",
						rs.getString("status_hakmilik") == null ? "" : rs
								.getString("status_hakmilik"));

				h.put("tarikh_borangh",
						rs.getString("TARIKH_BORANGH") == null ? "" : rs
								.getString("TARIKH_BORANGH"));
				h.put("tarikh_borangk",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK"));
				h.put("flag_jenis_rizab",
						rs.getString("flag_jenis_rizab") == null ? "" : rs
								.getString("flag_jenis_rizab"));
				h.put("nama_lain_rizab",
						rs.getString("nama_lain_rizab") == null ? "" : rs
								.getString("nama_lain_rizab"));
				h.put("tarikh_warta_rizab",
						rs.getDate("tarikh_warta_rizab") == null ? "" : Format
								.format(rs.getDate("tarikh_warta_rizab")));
				h.put("no_warta_rizab",
						rs.getString("no_warta_rizab") == null ? "" : rs
								.getString("no_warta_rizab"));

				if (rs.getString("luas_ambil") != null
						&& rs.getString("luas_ambil") != "") {

					double luasAmbil = rs.getDouble("luas_ambil");
					String LA = Utils.formatLuasHM(luasAmbil);
					h.put("luas_ambil", LA);

				} else {
					h.put("luas_ambil", "0");
				}

				// validation for PU
				if (rs.getString("luas_ambil") != null
						&& rs.getString("luas_lot") != null) {

					double luasAmbil = 0;
					if (rs.getString("id_unitluasambil_convert") != null
							&& rs.getString("id_unitluasambil_convert").equals(
									"2")) {
						luasAmbil = rs.getDouble("luas_ambil"); // meter persegi
					} else {
						luasAmbil = rs.getDouble("luas_ambil") * 10000; // hektar
					}

					double luasLot = 0;
					if (rs.getString("id_unitluaslot_convert") != null
							&& rs.getString("id_unitluaslot_convert").equals(
									"2")) {
						luasLot = rs.getDouble("luas_lot"); // meter persegi
					} else {
						luasLot = rs.getDouble("luas_lot") * 10000; // hektar
					}

					double baki = luasLot - luasAmbil;

					if (baki > 0) {
						h.put("flagPU", "yes");
					} else {
						h.put("flagPU", "no");
					}

				} else {
					h.put("flagPU", "no");
				}

				if (rs.getString("id_unitluasambil_convert") != null) {

					if (rs.getString("id_unitluasambil_convert").equals("1")) {
						h.put("unitByKategori", "Hektar");
					} else {
						h.put("unitByKategori", "Meter Persegi");
					}
				} else {
					h.put("unitByKategori", "");
				}

				if (rs.getString("id_unitluasambil_convert") != null) {

					if (rs.getString("id_unitluasambil_convert").equals("1")) {

						if (rs.getString("luas_ambil") != null) {
							double luasAmbil = rs.getDouble("luas_ambil");
							String LAH = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah", LAH);
						} else {
							h.put("nilaiTanah", "0");
						}

					} else {
						if (rs.getString("luas_ambil") != null) {
							double luasAmbil = rs.getDouble("luas_ambil") / 10000;
							String LAM = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah", LAM);
						} else {
							h.put("nilaiTanah", "0");
						}
					}

				} else {
					h.put("nilaiTanah", "0");
				}

				// get total luas ambil in hectar
				if (rs.getString("luas_ambil") != null) {
					if (rs.getString("id_unitluasambil_convert") != null
							&& rs.getString("id_unitluasambil_convert").equals(
									"1")) {
						totalSize += rs.getDouble("luas_ambil");
					} else {
						totalSize += (rs.getDouble("luas_ambil") / 10000);
					}
				}
				// ADDED BY ELLY
				// myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
				h.put("luas_keseluruhan", totalSize);

				h.put("id_kategoritanah",
						rs.getString("id_kategoritanah") == null ? "" : rs
								.getString("id_kategoritanah"));
				h.put("no_hakmilik", rs.getString("no_hakmilik") == null ? ""
						: rs.getString("no_hakmilik"));
				h.put("no_lot",
						rs.getString("no_lot") == null ? "" : rs
								.getString("no_lot"));
				h.put("luas_lot",
						rs.getString("luas_lot") == null ? "" : rs
								.getDouble("luas_lot"));
				h.put("no_pt",
						rs.getString("no_pt") == null ? "" : rs
								.getString("no_pt"));
				h.put("catatan",
						rs.getString("catatan") == null ? "" : rs
								.getString("catatan"));

				h.put("tarikh_daftar", rs.getDate("tarikh_daftar") == null ? ""
						: Format.format(rs.getDate("tarikh_daftar")));
				h.put("tarikh_luput", rs.getDate("tarikh_luput") == null ? ""
						: Format.format(rs.getDate("tarikh_luput")));
				h.put("tempoh_luput", rs.getString("tempoh_luput") == null ? ""
						: rs.getString("tempoh_luput"));

				h.put("lokasi",
						rs.getString("lokasi") == null ? "" : rs
								.getString("lokasi"));
				h.put("syarat_nyata", rs.getString("syarat_nyata") == null ? ""
						: rs.getString("syarat_nyata"));
				h.put("syarat_khas", rs.getString("syarat_khas") == null ? ""
						: rs.getString("syarat_khas"));

				h.put("sekatan_hak", rs.getString("sekatan_hak") == null ? ""
						: rs.getString("sekatan_hak"));
				h.put("sekatan_kepentingan",
						rs.getString("sekatan_kepentingan") == null ? "" : rs
								.getString("sekatan_kepentingan"));
				h.put("no_syit",
						rs.getString("no_syit") == null ? "" : rs
								.getString("no_syit"));
				h.put("jenis_hakmilik",
						rs.getString("jenis_hakmilik") == null ? "" : rs
								.getString("jenis_hakmilik"));
				h.put("flag_borangl", rs.getString("flag_borangl") == null ? ""
						: rs.getString("flag_borangl"));

				h.put("status_bantahan_ap",
						rs.getString("STATUS_BANTAHAN_AP") == null ? "" : rs
								.getString("STATUS_BANTAHAN_AP"));
				h.put("keteranganStatusBantahan",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("flag_online", rs.getString("FLAG_ONLINE") == null ? ""
						: rs.getString("FLAG_ONLINE"));

				list_hakmilik.addElement(h);
			}
			return list_hakmilik;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	protected void setupPage(HttpSession session, String action, List lists) {
		if (lists == null) {
			context.put("totalRecords", Integer.valueOf(0));
			context.put("SenaraiFail", "");
			context.put("page", Integer.valueOf(0));
			context.put("itemsPerPage", Integer.valueOf(0));
			context.put("totalPages", Integer.valueOf(0));
			context.put("startNumber", Integer.valueOf(0));
			context.put("isFirstPage", Boolean.valueOf(true));
			context.put("isLastPage", Boolean.valueOf(true));
		} else {
			context.put("totalRecords", Integer.valueOf(lists.size()));
			int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
			int itemsPerPage;
			if (context.get("itemsPerPage") == null
					|| "".equals(context.get("itemsPerPage"))
					|| "0".equals(context.get("itemsPerPage")))
				itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage")
						: 10;
			else {
				myLogger.info("PAGE 2 :" + context.get("itemsPerPage"));
				// itemsPerPage =
				// ((Integer)context.get("itemsPerPage")).intValue();
				itemsPerPage = Integer.parseInt(context.get("itemsPerPage")
						.toString());
				myLogger.info("PAGE 3 :" + context.get("itemsPerPage"));
			}
			if ("getNext".equals(action))
				page++;
			else if ("getPrevious".equals(action))
				page--;
			else if ("getPage".equals(action))
				page = getParamAsInteger("value");
			else if ("doChangeItemPerPage".equals(action))
				itemsPerPage = getParamAsInteger("itemsPerPage");
			if (itemsPerPage == 0)
				itemsPerPage = 10;
			Paging2 paging = new Paging2(session, lists, itemsPerPage);
			if (page > paging.getTotalPages())
				page = 1;
			context.put("SenaraiFail", paging.getPage(page));
			context.put("page", new Integer(page));
			context.put("itemsPerPage", new Integer(itemsPerPage));
			context.put("totalPages", new Integer(paging.getTotalPages()));
			context.put("startNumber", new Integer(paging.getTopNumber()));
			context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			context.put("isLastPage", new Boolean(paging.isLastPage()));
		}
	}

	public void transferData(HttpSession session, String id_mohon_selected,
			String id_permohonan, Db db) throws Exception {
		// Db db = null;
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " INSERT INTO TBLPPTHAKMILIK "
					+ " (ID_NEGERI, ID_DAERAH,  "
					+ " ID_MUKIM, ID_JENISHAKMILIK, NO_HAKMILIK,  "
					+ " ID_SUBJAKET, TARIKH_TERIMA, TARIKH_DAFTAR,  "
					+ " FLAG_REZAB, FLAG_GSA, TEMPOH_LUPUT,  "
					+ " TARIKH_LUPUT, NO_PT, ID_UNITLUASPT,  "
					+ " LUAS_PT, NO_LOT, ID_UNITLUASLOT,  "
					+ " LUAS_LOT, ID_UNITLUASAMBIL, LUAS_AMBIL,  "
					+ " ID_UNITLUASBARU, LUAS_BARU, NO_PELAN,  "
					+ " NO_SYIT, LOKASI, ID_KATEGORITANAH,  "
					+ " SYARAT_NYATA, SYARAT_KHAS, SEKATAN_KEPENTINGAN, "
					+ " SEKATAN_HAK, JENIS_MILIK, ULASAN_PENTADBIR,  "
					+ " JUMLAH_AWARD, UNIT_AWARD, TARIKH_HANTAR_DHD,  "
					+ " TARIKH_TERIMA_DHD, FLAG_AMBIL_SEGERA, FLAG_PEMBATALAN, "
					+ " FLAG_PENARIKAN_BALIK, FLAG_LAPORAN_TANAH, FLAG_HANTAR_DHD, "
					+ " FLAG_TERIMA_DHD, FLAG_SIASATAN, FLAG_BORANGL,  "
					+ " FLAG_PERMINTAAN_UKUR, ID_SIASATAN, ID_BORANGK,  "
					+ " ID_BORANGG, ID_BORANGE, ID_BORANGI,  "
					+ " ID_BORANGL, ID_PENARIKANBALIK, ID_PEMBATALAN, "
					+ " FLAG_UBAH, NO_BANGUNAN, NO_TINGKAT,  "
					+ " NO_PETAK, TARIKH_BORANGK, TARIKH_BORANGK_SEGERA, "
					+ " ID_PERMOHONAN, TARIKH_TERIMA_HM, NO_KELULUSAN,  "
					+ " NO_DAFTAR, ID_LOT,  "
					+ " ID_DB, SEKSYEN, CATATAN, "
					+ " FLAG_ENDOSAN, LUAS_LOT_TARIK, ID_DAERAHPENGGAWA,  "
					+ " TARIKH_SURAT_PTG, TARIKH_HANTAR_JUPEM, TARIKH_BORANG_PU,  "
					+ " STATUS_BORANGL, ID_PEGAWAI, ID_BANTAHAN,  "
					+ " FLAG_BANTAHAN, NO_SUBJAKET, FLAG_JENIS_RIZAB,  "
					+ " NAMA_LAIN_RIZAB, NO_WARTA_RIZAB, TARIKH_WARTA_RIZAB,  "
					+ " NO_PERMINTAANUKUR, FLAG_ENDOSAN_BORANGK, FLAG_PENARIKAN_KESELURUHAN,  "
					+ " FLAG_PEMBATALAN_KESELURUHAN, ID_UNITLUASLOT_CONVERT, ID_UNITLUASAMBIL_CONVERT,  "
					+ " NAMA_LUAS_ASAL, NAMA_LUAS_AMBIL, FLAG_BAYAR_BANTAHAN,  "
					+ " FLAG_SEBAHAGIAN, PGNHM, FLAG_HANTAR_HTP,  "
					+ " TARIKH_HANTAR_HTP, FLAG_SEGERA_SEBAHAGIAN, CATATAN_STOP_SIASATAN,  "
					+ " FLAG_STOP_SIASATAN, TARIKH_STOP_SIASATAN,ID_MASUK,  "
					+ " TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)    "
					+ " SELECT  T.ID_NEGERI, T.ID_DAERAH,  "
					+ " T.ID_MUKIM, T.ID_JENISHAKMILIK, T.NO_HAKMILIK,  "
					+ " T.ID_SUBJAKET, T.TARIKH_TERIMA, T.TARIKH_DAFTAR,  "
					+ " T.FLAG_REZAB, T.FLAG_GSA, T.TEMPOH_LUPUT,  "
					+ " T.TARIKH_LUPUT, T.NO_PT, T.ID_UNITLUASPT,  "
					+ " T.LUAS_PT, T.NO_LOT, T.ID_UNITLUASLOT,  "
					+ " T.LUAS_LOT, T.ID_UNITLUASAMBIL, T.LUAS_AMBIL,  "
					+ " T.ID_UNITLUASBARU, T.LUAS_BARU, T.NO_PELAN,  "
					+ " T.NO_SYIT, T.LOKASI, T.ID_KATEGORITANAH,  "
					+ " T.SYARAT_NYATA, T.SYARAT_KHAS, T.SEKATAN_KEPENTINGAN,  "
					+ " T.SEKATAN_HAK, T.JENIS_MILIK, T.ULASAN_PENTADBIR,  "
					+ " T.JUMLAH_AWARD, T.UNIT_AWARD, T.TARIKH_HANTAR_DHD,  "
					+ " T.TARIKH_TERIMA_DHD, T.FLAG_AMBIL_SEGERA, T.FLAG_PEMBATALAN,  "
					+ " T.FLAG_PENARIKAN_BALIK, T.FLAG_LAPORAN_TANAH, T.FLAG_HANTAR_DHD,  "
					+ " T.FLAG_TERIMA_DHD, T.FLAG_SIASATAN, T.FLAG_BORANGL,  "
					+ " T.FLAG_PERMINTAAN_UKUR, T.ID_SIASATAN, T.ID_BORANGK,  "
					+ " T.ID_BORANGG, T.ID_BORANGE, T.ID_BORANGI,  "
					+ " T.ID_BORANGL, '', '',  "
					+ " T.FLAG_UBAH, T.NO_BANGUNAN, T.NO_TINGKAT,  "
					+ " T.NO_PETAK, T.TARIKH_BORANGK, T.TARIKH_BORANGK_SEGERA,  "
					+ " "
					+ id_permohonan
					+ ", T.TARIKH_TERIMA_HM, T.NO_KELULUSAN,  "
					+ " T.NO_DAFTAR, T.ID_LOT,  "
					+ " T.ID_DB, T.SEKSYEN, T.CATATAN, "
					+ " T.FLAG_ENDOSAN, T.LUAS_LOT_TARIK, T.ID_DAERAHPENGGAWA, "
					+ " T.TARIKH_SURAT_PTG, T.TARIKH_HANTAR_JUPEM, T.TARIKH_BORANG_PU, "
					+ " T.STATUS_BORANGL, T.ID_PEGAWAI, T.ID_BANTAHAN,  "
					+ " T.FLAG_BANTAHAN, T.NO_SUBJAKET, T.FLAG_JENIS_RIZAB, "
					+ " T.NAMA_LAIN_RIZAB, T.NO_WARTA_RIZAB, T.TARIKH_WARTA_RIZAB, "
					+ " T.NO_PERMINTAANUKUR, T.FLAG_ENDOSAN_BORANGK, T.FLAG_PENARIKAN_KESELURUHAN, "
					+ " T.FLAG_PEMBATALAN_KESELURUHAN, T.ID_UNITLUASLOT_CONVERT, T.ID_UNITLUASAMBIL_CONVERT, "
					+ " T.NAMA_LUAS_ASAL, T.NAMA_LUAS_AMBIL, T.FLAG_BAYAR_BANTAHAN,  "
					+ " T.FLAG_SEBAHAGIAN, T.PGNHM, T.FLAG_HANTAR_HTP,  "
					+ " T.TARIKH_HANTAR_HTP, T.FLAG_SEGERA_SEBAHAGIAN, T.CATATAN_STOP_SIASATAN, "
					+ " T.FLAG_STOP_SIASATAN, T.TARIKH_STOP_SIASATAN, "
					+ " "
					+ user_id
					+ ",SYSDATE AS TARIKH_MASUK,  "
					+ user_id
					+ ", SYSDATE AS TARIKH_KEMASKINI "
					+ " FROM TBLPPTHAKMILIK T WHERE ID_PERMOHONAN = '"
					+ id_mohon_selected + "' ";

			myLogger.info("transfer :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)
			// db.close();
		}
	}

}
