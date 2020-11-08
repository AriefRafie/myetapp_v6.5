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

import ekptg.helpers.Paging2;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.PPTHeader;

public class SkrinPopupCarianHakmilik_BorangE_F extends AjaxBasedModule {
	static Logger myLogger = Logger
			.getLogger(SkrinPopupCarianHakmilik_BorangE_F.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH = "app/ppt/PopupHakmilik/";
	private String vm = PATH + "SkrinPopupCarianBorangE.jsp";

	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {
		session = request.getSession();
		action = getParam("action");
		String command = getParam("command");
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
		String id_borange = getParam("id_borange");
		context.put("id_borange", id_borange);

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
								getParam("NO_PB"), db, id_pegawai));
				context.put("refreshHakmilik", "yes");
			} else if ("janaSubjaket".equals(command)) {
				janaSubjaket(session, id_permohonan, db,
						getListSeqSubjaket(id_permohonan, db));
				context.put("refreshHakmilik", "yes");
			} else if ("hapusBorangE".equals(command)) {
				FrmUPTSek8BorangFData.deleteListCB(id_borange, db);
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
					db, id_pegawai);
			context.put("saiz_listTanah",
					getTanah_count(id_permohonan, "", "", db));

		} finally {

			if (db != null)
				db.close();
		}

		return vm;
	}

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
			String id_pegawai) throws Exception {
		
		List<Hashtable> list = null;
		list = getHakmilik(id_permohonan, flag_skrin, no_lot, nama_pb, no_pb,
				db, id_pegawai);
		context.put("SenaraiFail", list);
		//setupPage(session, action, list);
		
		List<Hashtable> senarai = null;
		senarai = getRingkasanSiasatan(id_permohonan, db);
		context.put("SenaraiRingkasan", senarai);
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
			String no_lot, String nama_pb, String no_pb, Db db, String idpegawai)
			throws Exception {
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
			sql = " SELECT DISTINCT B.ID_BORANGE, B.TARIKH_BORANGE, B.TARIKH_SIASATAN, A.ID_HAKMILIK, ";
			sql += " (SELECT COUNT(*) FROM TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";
			sql += " WHERE B1.ID_BORANGE = A1.ID_BORANGE AND B1.ID_BORANGE = B.ID_BORANGE)AS TOTALHM,  ";
			sql += " TO_DATE(TO_CHAR(A.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')AS TARIKH_MASUK ";
			sql += " FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C ";
			sql += " WHERE A.ID_BORANGE = B.ID_BORANGE ";
			sql += " AND A.ID_HAKMILIK = C.ID_HAKMILIK ";
			sql += " AND C.ID_PERMOHONAN = '" + id_permohonan + "' ";
			*/
			
			//razman rombak query
			
			sql = " SELECT DISTINCT B.ID_BORANGE, B.TARIKH_BORANGE, B.TARIKH_SIASATAN, "; 
			sql += " COUNT(A.ID_BORANGEHAKMILIK) AS   TOTALHM, TO_DATE(TO_CHAR(B.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')AS TARIKH_MASUK   ";
			sql += " FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C  ";
			sql += " WHERE A.ID_BORANGE = B.ID_BORANGE  AND A.ID_HAKMILIK = C.ID_HAKMILIK  AND C.ID_PERMOHONAN = '" + id_permohonan + "' "; 
			

			if (no_lot != "" && no_lot != null) {
				if (!noLOT.equals("")) {
					sql += " AND (" + "UPPER(C.no_lot) LIKE '%"
							+ noLOT.toUpperCase() + "%' "
							+ " OR  UPPER(C.no_pt) LIKE '%"
							+ noLOT.toUpperCase() + "%' " + "  " + " ) ";
				}
			}

			if (nama_pb != "" && nama_pb != null) {
				if (!nama_pb.equals("")) {
					sql += " AND C.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "
							+ " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "
							+ " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
							+ " AND M1.ID_HAKMILIK = C.ID_HAKMILIK  "
							+ "  "
							+ " AND upper(PB1.NAMA_PB) LIKE ('%"
							+ nama_pb.toUpperCase() + "%')) ";
				}
			}

			if (no_pb != "" && no_pb != null) {
				if (!no_pb.equals("")) {
					sql += " AND C.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "
							+ " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "
							+ " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
							+ " AND M1.ID_HAKMILIK = C.ID_HAKMILIK  "
							+ "  "
							+ " AND upper(PB1.NO_PB) LIKE ('%"
							+ no_pb.toUpperCase() + "%')) ";
				}
			}
			sql += " GROUP BY  B.ID_BORANGE, B.TARIKH_BORANGE, B.TARIKH_SIASATAN,B.TARIKH_MASUK  ";
			sql += " ORDER BY  B.TARIKH_BORANGE  ";
			myLogger.info("LIST HAKMILIK :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("bil", bil);
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""	: rs.getString("ID_BORANGE"));
				/*
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
						*/
				h.put("TARIKH_BORANGE",	rs.getDate("TARIKH_BORANGE") == null ? "" : Format.format(rs.getDate("TARIKH_BORANGE")));
				h.put("TARIKH_SIASATAN", rs.getDate("TARIKH_SIASATAN") == null ? "" : Format.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TOTALHM", rs.getString("TOTALHM") == null ? "" : rs.getString("TOTALHM"));
				h.put("TARIKH_MASUK", rs.getDate("TARIKH_MASUK") == null ? "" : Format.format(rs.getDate("TARIKH_MASUK")));
				
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
	
	
	// PPT-07 >>>>>>>>>>>>>>>>
	
	Vector list_ringkasan_siasatan  = null;

	public Vector getRingkasanSiasatan(String id_permohonan,  Db db)
			throws Exception {

		list_ringkasan_siasatan = new Vector();
		list_ringkasan_siasatan.clear();

		String sql = "";
		
		try {

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT M.ID_HAKMILIK, M.SEKSYEN,  JH.KOD_JENIS_HAKMILIK, ";
    		sql += " M.NO_HAKMILIK, MK.NAMA_MUKIM, ";  
    		sql += " CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND ";  
    		sql += " M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || ";  
    		sql += " M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41) ";  
    		sql += " ELSE '' END AS NO_LOTPT, M.NO_SUBJAKET, NVL(TO_NUMBER(M.NO_SUBJAKET),0) AS NO_SUBJAKET_NUM"; 
    		  		
    		sql += " ,(SELECT A1.ID_BORANGE FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE ";  
    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ) AS ID_BORANGE "; 
    		
    		sql += " ,(SELECT TO_CHAR(B1.TARIKH_SIASATAN,'DD/MM/YYYY') AS TARIKH_SIASATAN FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE ";  
    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ) AS TARIKH_SIASATAN "; 
    		
//    		PPT-06
    		sql += " ,(SELECT DISTINCT A1.JENIS_WAKTU AS JW FROM	TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";
    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";
    		sql += "AND A1.ID_BORANGE = B1.ID_BORANGE ";
    		sql += "AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";
    		sql += ")AS JENIS_WAKTU ";
    		
//    		PPT-06
    		sql += ",(SELECT DISTINCT A1.MASA_SIASATAN AS MS FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";
    		sql += "WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";
    		sql += "AND A1.ID_BORANGE = B1.ID_BORANGE ";
    		sql += "AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";
    		sql += ")AS MASA_SIASATAN ";
    		
    		sql += ",(SELECT A1.CATATAN FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";
    		sql += "WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";
    		sql += "AND A1.ID_BORANGE = B1.ID_BORANGE ";
    		sql += "AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";
    		sql += ")AS CATATAN ";		
    		
    		
    		sql += " ,(SELECT DISTINCT TO_CHAR(B1.TARIKH_BORANGE,'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE ";  
    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";  
    		sql += "  )AS TARIKH_BORANGE "; 
    		
    		sql += " FROM TBLPPTPERMOHONAN P, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH ";  
    		sql += " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN(+) ";   
    		sql += " AND M.ID_NEGERI = N.ID_NEGERI ";  
    		sql += " AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";
    		sql += " AND M.ID_LOT = LT.ID_LOT(+) "; 
    		sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";   
    			
    		sql += " AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y' "; 
    		sql += " AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
    		sql += " AND P.ID_PERMOHONAN = '"+id_permohonan+"' ";
    		
    		sql += " ORDER BY NO_SUBJAKET_NUM ASC";
    		
			
			myLogger.info("LIST HAKMILIK (Ringkasan):" + sql.toUpperCase());
			
    		ResultSet rs = stmt.executeQuery(sql);
   	     
    		Hashtable h;
    		int bil = 1;
    		
    		while (rs.next()) {	    	  
    			h = new Hashtable();
    			h.put("bil", bil);
    			h.put("id_borange", rs.getString("ID_BORANGE")== null?"":rs.getString("ID_BORANGE"));
    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
    			h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
    			h.put("no_lotpt", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
    			h.put("nama_mukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
    			h.put("tarikh_siasatan", rs.getString("TARIKH_SIASATAN")== null?"":rs.getString("TARIKH_SIASATAN"));
    			h.put("masa_siasatan", rs.getString("MASA_SIASATAN")== null?"":rs.getString("MASA_SIASATAN"));
    			h.put("jenis_waktu", rs.getString("JENIS_WAKTU")== null?"":rs.getString("JENIS_WAKTU"));
    			h.put("catatan", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
    			h.put("seksyen", rs.getString("SEKSYEN")== null?"":rs.getString("SEKSYEN"));
    			h.put("no_subjaket", rs.getString("NO_SUBJAKET")== null?"":rs.getString("NO_SUBJAKET"));
    			h.put("TARIKH_BORANGE", rs.getString("TARIKH_BORANGE")== null?"":rs.getString("TARIKH_BORANGE"));
    			list_ringkasan_siasatan.addElement(h);
    			bil++;
    	}	
    		
		return list_ringkasan_siasatan;
			
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	



}
