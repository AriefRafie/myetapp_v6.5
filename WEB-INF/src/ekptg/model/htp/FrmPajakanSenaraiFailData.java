/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;

/**
 * 
 *
 */
public class FrmPajakanSenaraiFailData {
	
	private static Logger log = Logger.getLogger(FrmPajakanSenaraiFailData.class);	
	private Vector senaraiFail = null;	
	private Vector senaraiFailOnline = null;	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private IPembelian iPembelian = null;
	
	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String tarikhTerima,String tajukFail, String namaPemohon) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
			//	+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
			//	+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
			//			"AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18)";

			// By Rosli, 05/05/2010
			sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " +
						" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			System.out.println("==="+sql);
			//log.info("sql carian fail : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void carianFail(String noFail, String tarikhTerima,String tajukFail, String namaPemohon,String idKementerian) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
			//	+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
			//	+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
			//			"AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18)";

			// By Rosli, 05/05/2010
			sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " +
						" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			System.out.println("==="+sql);
			//log.info("sql carian fail : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}	
	
	/*public void carianFailOnline2(String noFail, String tarikhTerima,String tajukFail, String namaPemohon,String idKementerian, String idUser) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		//String userId = (String)session.getAttribute("_ekptg_user_id");

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
			//	+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
			//	+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
			//			"AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18)";

			// By Rosli, 05/05/2010
			/*sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " +
						" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' ";*/
			
		/*	sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN," +
					" RN.NAMA_NEGERI " +
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN, USERS " +
					" WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+)" +
					" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' AND USERS.USER_ID = B.ID_MASUK AND A.ID_MASUK = '" +idUser+ "'";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}	
			sql = sql + "and A.NO_FAIL not in (' ','TIADA') ORDER BY B.ID_PERMOHONAN DESC";
			System.out.println("==="+sql);
			//log.info("sql carian fail : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}*/	
	
	public void carianFailOnline(String noFail, String tarikhTerima,String tajukFail, String namaPemohon,String idKementerian) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFailOnline = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
			//	+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
			//	+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
			//			"AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18)";

			// By Rosli, 05/05/2010
			sql = "SELECT B.NO_PERMOHONAN,A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI,A.ID_KEMENTERIAN "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D,TBLRUJNEGERI RN "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) " +
						" AND B.ID_STATUS='138' AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%"
							+ tajukFail.trim().toUpperCase() + "%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%"
							+ noFail.trim().toUpperCase() + "%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			System.out.println("==="+sql);
			//log.info("sql carian fail : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
		    	h.put("noP", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFailOnline.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public String getKodMampu(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return String.valueOf(rs.getInt("KOD_MAMPU"));
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '" + idKementerian + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return String.valueOf(rs.getInt("KOD_KEMENTERIAN"));
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String simpan(String idNegeri, String idKementerian, String idAgensi, String idSuburusan, String idStatusTanah,
			String idJenisFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, String tarikhAgihan, String tajuk, String tarikhSuratPemohon, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String TKJP = "to_date('" + tarikhSuratKJP + "','dd/MM/yyyy')";
			String TA = "to_date('" + tarikhAgihan + "','dd/MM/yyyy')";
			String TSP = "to_date('" + tarikhSuratPemohon + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "3");
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", idJenisFail);
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", tajuk);
			
			String kodUrusan = "882";
			
			r.add("NO_FAIL", generateNoFail(kodUrusan, getKodKementerian(idKementerian), idKementerian, getKodMampu(idNegeri), idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			log.info("sql:TBLPFDFAIL="+sql);
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID(db, "TBLPERMOHONAN_SEQ");
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("1",idSuburusan,"=");

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS",setIdStatus);
			if (!"".equals(tarikhSuratKJP)){
				r.add("TARIKH_SURAT", r.unquote(TKJP));
			}			
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("TUJUAN", tajuk);

			//Penambahbaikan. Syaz. 02122014. Untuk function pengesahan (2 layer)
			/**
			 * N = BARU SAVE
			 * H = HANTAR
			 * S = SAH (TELAH DISAHKAN)
			 * 
			 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
			 */
			r.add("FLAG_MOHON_FAIL", "N");			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPERMOHONAN");
			//log.info("sql:TBLPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			long idHTPPermohonan = DB.getNextID(db, "TBLHTPPERMOHONAN_SEQ");
			r.add("ID_HTPPERMOHONAN", idHTPPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_JENISTANAH", idStatusTanah);
			r.add("NO_RUJUKAN_KJP", noFailKJP);
			r.add("NO_RUJUKAN_LAIN", noFailLain);
			if (!"".equals(tarikhAgihan)){
				r.add("TARIKH_AGIHAN", r.unquote(TA));
			}
			if (!"".equals(tarikhSuratPemohon)){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(TSP));
			}
			r.add("ID_PEGAWAI", userId);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			//log.info("sql:TBLHTPPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			if (idSuburusan != null && idSuburusan.trim().length() != 0){
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSuburusan,"=");

				//TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				long idSuburusanstatusfail = DB.getNextID(db, "TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus); //PENERIMAAN PERMOHONAN				
				r.add("AKTIF", "1");	
				r.add("ID_FAIL", idFail);				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				//log.info("sql:TBLRUJSUBURUSANSTATUSFAIL="+sql);
				stmt.executeUpdate(sql);
				
			}			
			conn.commit();
			session.setAttribute("idFail", idFail);
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		return idFailString;
	}
	
	public String simpanOnline(String idNegeri, String idKementerian, String idAgensi, String idSuburusan, String idStatusTanah,
			String idJenisFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, String tarikhAgihan, String tajuk, String tarikhSuratPemohon, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String kodNegeriMampu = "";
		String kodKementerianMampu = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String TKJP = "to_date('" + tarikhSuratKJP + "','dd/MM/yyyy')";
			String TA = "to_date('" + tarikhAgihan + "','dd/MM/yyyy')";
			String TSP = "to_date('" + tarikhSuratPemohon + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "3");
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", idJenisFail);
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", tajuk);
			
			String kodUrusan = "882";
			
			//r.add("NO_FAIL", " ");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			log.info("sql:TBLPFDFAIL="+sql);
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID(db, "TBLPERMOHONAN_SEQ");
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("-1",idSuburusan,"=");
			kodKementerianMampu = getKementerianByMampu(Integer.parseInt(idKementerian));
			kodNegeriMampu = getNegeriByMampu(Integer.parseInt(idNegeri));

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NO_PERMOHONAN",FrmUtilData.generateNoOnline(3,kodKementerianMampu, String.valueOf(idKementerian), kodNegeriMampu, idNegeri));
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS",setIdStatus);
			if (!"".equals(tarikhSuratKJP)){
				r.add("TARIKH_SURAT", r.unquote(TKJP));
			}			
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("TUJUAN", tajuk);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			log.info("sql:TBLPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			long idHTPPermohonan = DB.getNextID(db, "TBLHTPPERMOHONAN_SEQ");
			r.add("ID_HTPPERMOHONAN", idHTPPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_JENISTANAH", idStatusTanah);
			r.add("NO_RUJUKAN_KJP", noFailKJP);
			r.add("NO_RUJUKAN_LAIN", noFailLain);
			if (!"".equals(tarikhAgihan)){
				r.add("TARIKH_AGIHAN", r.unquote(TA));
			}
			if (!"".equals(tarikhSuratPemohon)){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(TSP));
			}
			r.add("ID_PEGAWAI", userId);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			log.info("sql:TBLHTPPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("id_Permohonan", idPermohonan);
			r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("-1",""+idSuburusan,"="));
			r.add("aktif","1");
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_KEMASKINI", userId);
			r.add("tarikh_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_FAIL", idFail);
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			log.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
			stmt.executeUpdate(sql);	
			conn.commit();
			
			session.setAttribute("idFail", idFail);
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		return idFailString;
	}
	
	
	public String generateNoFail(String kodUrusan, String kodKementerian, String idKementerian, String kodMampu, String idNegeri) throws Exception{
		String noFail = "";
		noFail = "JKPTG/101/" + kodUrusan + "/" + kodKementerian + "/" +  kodMampu + "-" + File.getSeqNo(3, 3, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));
		return noFail;
		
	}
	
	public Vector getSenaraiFail() {
		return senaraiFail;
	}


	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
	
	//By Penswastaan
	
	public String simpan(String idPermohonanLama,String idNegeri, String idKementerian, String idAgensi, String idSuburusan, String idStatusTanah,
			String idJenisFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, String tarikhAgihan, String tajuk, String tarikhSuratPemohon, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String TKJP = "to_date('" + tarikhSuratKJP + "','dd/MM/yyyy')";
			String TA = "to_date('" + tarikhAgihan + "','dd/MM/yyyy')";
			String TSP = "to_date('" + tarikhSuratPemohon + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "3");
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", idJenisFail);
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", tajuk);
			
			String kodUrusan = "882";
			
			r.add("NO_FAIL", generateNoFail(kodUrusan, getKodKementerian(idKementerian), idKementerian, getKodMampu(idNegeri), idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			log.info("sql:TBLPFDFAIL="+sql);
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID(db, "TBLPERMOHONAN_SEQ");
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("1",idSuburusan,"=");

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS",setIdStatus);
			if (!"".equals(tarikhSuratKJP)){
				r.add("TARIKH_SURAT", r.unquote(TKJP));
			}			
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("TUJUAN", tajuk);

			//Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
			/**
			 * N = BARU SAVE
			 * H = HANTAR
			 * S = SAH (TELAH DISAHKAN)
			 * 
			 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
			 */
			r.add("FLAG_MOHON_FAIL", "N");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			log.info("sql:TBLPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			long idHTPPermohonan = DB.getNextID(db, "TBLHTPPERMOHONAN_SEQ");
			r.add("ID_HTPPERMOHONAN", idHTPPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_JENISTANAH", idStatusTanah);
			r.add("NO_RUJUKAN_KJP", noFailKJP);
			r.add("NO_RUJUKAN_LAIN", noFailLain);
			if (!"".equals(tarikhAgihan)){
				r.add("TARIKH_AGIHAN", r.unquote(TA));
			}
			if (!"".equals(tarikhSuratPemohon)){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(TSP));
			}
			r.add("ID_PEGAWAI", userId);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			log.info("sql:TBLHTPPERMOHONAN="+sql);
			stmt.executeUpdate(sql);
			
			if (idSuburusan != null && idSuburusan.trim().length() != 0){
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSuburusan,"=");

				//TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				long idSuburusanstatusfail = DB.getNextID(db, "TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
				/*if ("7".equals(idSuburusan)){
					r.add("ID_SUBURUSANSTATUS", "15"); //PENERIMAAN PERMOHONAN
				} else if ("17".equals(idSuburusan)){
					r.add("ID_SUBURUSANSTATUS", "186"); //PENERIMAAN PERMOHONAN
				} else if ("18".equals(idSuburusan)){
					r.add("ID_SUBURUSANSTATUS", "186"); //PENERIMAAN PERMOHONAN
				}	*/
				r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus); //PENERIMAAN PERMOHONAN				
				r.add("AKTIF", "1");	
				r.add("ID_FAIL", idFail);
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				log.info("sql:TBLRUJSUBURUSANSTATUSFAIL="+sql);
				stmt.executeUpdate(sql);
				
				//HTPHakmilikUrusan
				HakmilikUrusan hUrusan = getIPembelian().findByHakmilikUrusanId(idPermohonanLama);				
				sql = simpanHakmilikSQL(hUrusan);
				log.info("sql:TBLHTPHAKMILIKURUSAN="+sql);
				stmt.executeUpdate(sql);
				
			}
			
			conn.commit();
			session.setAttribute("idFail", idFail);
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		return idFailString;
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	public String simpanHakmilikSQL(HakmilikUrusan urusan) {
		//Db db = null;
		//Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		try{
			//db = new Db();
			//conn = db.getConnection();
			//conn.setAutoCommit(false);
			//Statement stmt = db.getStatement();
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			urusan.setIdhakmilikurusan(String.valueOf(idHakmilikurusan));
			
			String TT = "to_date('" + urusan.getTarikhMula() + "','dd/MM/yyyy')";
			String TL = "to_date('" + urusan.getTarikhLuput() + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.add("id_Hakmilikurusan", idHakmilikurusan);
			r.add("id_Permohonan", urusan.getPermohonan().getIdPermohonan());	
			r.add("pegangan_Hakmilik",urusan.getPeganganHakmilik());
			r.add("id_Negeri", urusan.getIdNegeri());
			r.add("id_Daerah", urusan.getIdDaerah());
			r.add("id_Mukim", urusan.getIdMukim());
			r.add("id_JenisHakmilik", urusan.getIdHakmilik());
			r.add("no_Hakmilik", urusan.getNohakmilik());
			r.add("no_Lot", urusan.getNolot());
			r.add("id_Lot", urusan.getIdLot());
			r.add("no_Bangunan", urusan.getNoBangunan());
		    r.add("no_Tingkat", urusan.getNoTingkat());
		    r.add("no_Petak", urusan.getNoPetak());
			r.add("Tarikh_Mula", r.unquote(TT));
			r.add("Tarikh_Luput", r.unquote(TL));
			r.add("Luas", urusan.getLuas());
			r.add("id_Luas", urusan.getIdLuas());
			r.add("no_Pelan", urusan.getNoPlan());
			r.add("id_Jenisrizab", urusan.getIdJenisRizab());
			r.add("id_Kategori", urusan.getIdKategoriTanah());
			r.add("id_Subkategori", urusan.getIdSubKetegoriTanah());		  
			  
		    sql = r.getSQLInsert("Tblhtphakmilikurusan");
		    //
		    //stmt.executeUpdate(sql);
		    //conn.commit();
		
		}catch(Exception e){
			e.printStackTrace();
		
		}finally{
			 //if (db != null){
		    //	  db.close();
		      //}
		}
		return sql;
	}
	
	public String getKementerianByMampu(int idkementerian) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_kementerian,kod_kementerian" +
	    		" from tblrujkementerian where id_kementerian="+idkementerian;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      //Tblrujkementerian f = null;
		    if (rs.next()) {
	    	 // f = new Tblrujkementerian();
	    	  //f.setKodKementerian(rs.getString("kod_kementerian"));
		    	output = rs.getString("kod_kementerian");
	      }
	      //return f.getKodKementerian();
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}
	
	public String getNegeriByMampu(int idnegeri) throws Exception {
	    Db db = null;
	    String output = "";
	    String sql = "Select id_negeri,kod_negeri,nama_negeri,kod_mampu" +
	    		" from tblrujnegeri where id_negeri="+idnegeri;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      //Tblrujnegeri f = null;
		    //System.out.println("FrmPajakanKecil::getNegeriByMampu 1");
		    if (rs.next()) {
	    	  //f = new Tblrujnegeri();
	    	  //f.setKodMampu(rs.getString("kod_mampu"));
		    	output = Utils.isNull(rs.getString("kod_mampu"));
	      }
		   //System.out.println("FrmPajakanKecil::getNegeriByMampu 2"+f.getKodMampu());
	      //return f.getKodMampu();
	    } finally {
	      if (db != null) db.close();
	    }
	    return output;
	}
	public Vector getSenaraiFailOnline() {
		
			return senaraiFailOnline;
		
	}
}
