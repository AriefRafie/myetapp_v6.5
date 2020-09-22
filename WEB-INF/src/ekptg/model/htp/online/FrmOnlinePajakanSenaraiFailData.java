/**
 * 
 */
package ekptg.model.htp.online;

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

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmGadaianHakmilikData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.view.esaduan.EkptgEmailSender;

/**
 * 
 *
 */
public class FrmOnlinePajakanSenaraiFailData {
	
	private static Logger log = Logger.getLogger(FrmOnlinePajakanSenaraiFailData.class);	
	private Vector senaraiFail = null;	
	private Vector senaraiFailOnline = null;	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private IPembelian iPembelian = null;
	private Vector beanMaklumatPemohon1 = null;
	
	@SuppressWarnings("unchecked")
	/*public void carianFail(String noFail, String tarikhTerima,String tajukFail, String namaPemohon) throws Exception {
		
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
	}	*/
	
	public void carianFailOnline2(String noFail, String tarikhTerima,String tajukFail, String namaPemohon, String userId) throws Exception {
		
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
			sql = "SELECT A.TAJUK_FAIL, A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.NO_PERMOHONAN, B.TARIKH_TERIMA, " +
					" C.KETERANGAN,RN.NAMA_NEGERI " +
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C, TBLHTPPEMOHON D, TBLRUJNEGERI RN " +
					" WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
					" AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) AND A.ID_NEGERI = RN.ID_NEGERI(+) " +
					" AND A.ID_URUSAN = '3' AND A.ID_MASUK = '"+userId+"'";
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
			
			/*if(idKementerian != null){
				if(!idKementerian.trim().equals("")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}*/
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}	
			//sql = sql + "and B.NO_PERMOHONAN not in (' ','TIADA') ORDER BY B.ID_PERMOHONAN DESC";
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
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
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
		String namaUser = "";
		
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
			
			//r.add("NO_FAIL", generateNoFail(kodUrusan, getKodKementerian(idKementerian), idKementerian, getKodMampu(idNegeri), idNegeri));
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
			//r.add("ID_PEMOHON", idPemohon);
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
			
			//TBLHTPPEMOHON
			sql = " SELECT A.USER_NAME, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_NEGERI,B.NO_FAX,"
					+ " B.NO_KP_BARU, B.NO_TEL, B.EMEL "
					+ " FROM USERS A, USERS_ONLINE B"
					+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			
			ResultSet rsUser = stmt.executeQuery(sql);
			r = new SQLRenderer();
	        long idPemohon = DB.getNextID(db,"TBLHTPPEMOHON_SEQ");
	        r.add("ID_PEMOHON", idPemohon);
	        r.add("ID_PERMOHONAN", idPermohonan);
	        if (rsUser.next()){
	        	if (rsUser.getString("USER_NAME") != null){
	        		namaUser = rsUser.getString("USER_NAME");
	        	}
	            r.add("NAMA_PEMOHON", namaUser);
	            r.add("NO_PEMOHON", rsUser.getString("NO_KP_BARU") == null ? "" : rsUser.getString("NO_KP_BARU"));
	            r.add("ALAMAT_PEMOHON1", rsUser.getString("ALAMAT1") == null ? "" : rsUser.getString("ALAMAT1"));
	            r.add("ALAMAT_PEMOHON2", rsUser.getString("ALAMAT2") == null ? "" : rsUser.getString("ALAMAT2"));
	            r.add("ALAMAT_PEMOHON3", rsUser.getString("ALAMAT3") == null ? "" : rsUser.getString("ALAMAT3"));
	            r.add("POSKOD", rsUser.getString("POSKOD") == null ? "" : rsUser.getString("POSKOD"));
	            r.add("ID_NEGERI", rsUser.getString("ID_NEGERI") == null ? "99999" : rsUser.getString("ID_NEGERI"));
	            r.add("ID_BANDAR","99999");
	            r.add("NO_TEL", rsUser.getString("NO_TEL") == null ? "" : rsUser.getString("NO_TEL"));
	                r.add("NO_FAX", rsUser.getString("NO_FAX") == null ? "" : rsUser.getString("NO_FAX"));
	            }
	            r.add("ID_MASUK", userId);
	            r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

	            sql = r.getSQLInsert("TBLHTPPEMOHON");
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
	
	public String simpanOnline(String idNegeri, String idKementerian, String idAgensi, String idSuburusan, 
			String idStatusTanah, String idJenisFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, 
			String tarikhAgihan, String tajuk, String tarikhSuratPemohon, String idHakmilik, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String kodNegeriMampu = "";
		String kodKementerianMampu = "";
		String idNegeriHakmilik = "";
		String idLuas = "";
		String luas = "";
		
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
			r.add("ID_TARAFKESELAMATAN", 2);	//2 - TERHAD
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", tajuk);
//			String kodUrusan = "882";
			
			//r.add("NO_FAIL", " ");
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			log.info("TBLPFDFAIL:sql="+sql);
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID(db, "TBLPERMOHONAN_SEQ");
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("-2",idSuburusan,"=");
			kodKementerianMampu = getKementerianByMampu(Integer.parseInt(idKementerian));
			kodNegeriMampu = getNegeriByMampu(Integer.parseInt(idNegeri));

			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NO_PERMOHONAN", FrmUtilData.generateNoOnline(3,kodKementerianMampu, String.valueOf(idKementerian), kodNegeriMampu, idNegeri));
			
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
			
			//TBLHTPPEMOHON
			FrmOnlinePajakanHeaderData logicHeader = new FrmOnlinePajakanHeaderData();
			Vector<Hashtable<String,String>> vec = logicHeader.setMaklumatPemohon(userId);
	    	Hashtable<String,String> hash = vec.get(0);
	    	
//	    	sql = " SELECT U.USER_ID, U.USER_NAME, UPPER(UO.KATEGORI) AS KATEGORI, UO.NO_KP_BARU, UO.NO_FAX, UO.NO_HP, UO.EMEL"+
//					", UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3,NVL(UO.ID_NEGERI,0) ID_NEGERI,NVL(UO.ID_BANDAR,0) ID_BANDAR  " +
//					", UO.POSKOD, RB.KETERANGAN AS NAMA_BANDAR, RN.NAMA_NEGERI  " +
//					  " FROM USERS U,USERS_ONLINE UO, TBLRUJNEGERI RN, TBLRUJBANDAR RB " +
//					  " WHERE U.USER_ID = UO.USER_ID "+
//					  " AND UO.ID_BANDAR = RB.ID_BANDAR(+) "+
//					  " AND UO.ID_NEGERI = RN.ID_NEGERI(+) " +
//					  " AND U.USER_ID = '" + userId + "'";
	    	
			String idPemohon = String.valueOf(DB.getNextID(db, "TBLHTPPEMOHON_SEQ"));
			r = new SQLRenderer();
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_PERMOHONAN", idPermohonan);	
			r.add("NO_PEMOHON", hash.get("noPengenalan"));
			r.add("NAMA_PEMOHON", hash.get("namaPemohon"));
			r.add("ALAMAT_PEMOHON1", hash.get("alamat1"));
			r.add("ALAMAT_PEMOHON2", hash.get("alamat2"));
			r.add("ALAMAT_PEMOHON3", hash.get("alamat3"));
			r.add("POSKOD", hash.get("poskod"));
			r.add("ID_DAERAH", FrmGadaianHakmilikData.getDaerahMengikutBandar(String.valueOf(hash.get("idBandar"))));
			r.add("ID_NEGERI", hash.get("idNegeri"));
			if(!hash.get("noTel").equals(""))
				r.add("NO_TEL", hash.get("noTel"));
			
			if(!hash.get("noFax").equals(""))
				r.add("NO_FAX", hash.get("noFax"));
			
			if(!hash.get("noFax").equals(""))
				r.add("ID_BANDAR", hash.get("idBandar"));
			
			r.add("EMEL", hash.get("emel"));				
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPEMOHON");
			log.info("TBLHTPPEMOHON:sql = "+sql);
			stmt.executeUpdate(sql);
        
			r.clear();
			r.add("id_Permohonan", idPermohonan);
			r.add("Id_Suburusanstatus", FrmUtilData.getIdSuburusanstatusByLangkah("-2",""+idSuburusan,"="));
			r.add("aktif","1");
			r.add("id_Masuk", userId);
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_KEMASKINI", userId);
			r.add("tarikh_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_FAIL", idFail);
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			log.info("StatusChange_Action::TBLRUJSUBURUSANSTATUSFAIL = "+sql);
			stmt.executeUpdate(sql);
			
			//TBLHTPHAKMILIKURUSAN
			sql = "SELECT ID_DAERAH, ID_NEGERI, ID_MUKIM, PEGANGAN_HAKMILIK, NO_HAKMILIK, NO_LOT, " +
					" ID_LOT, ID_JENISHAKMILIK, ID_KATEGORI, LUAS, ID_LUAS" +
					" FROM TBLHTPHAKMILIK WHERE ID_HAKMILIK = '"+idHakmilik+"'";
			log.info("TBLHTPHAKMILIKURUSAN = "+sql);
			r = new SQLRenderer();
			long idHakmilikUrusan = DB.getNextID(db, "TBLHTPHAKMILIKURUSAN_SEQ");
			ResultSet rs = stmt.executeQuery(sql);
			
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
			r.add("ID_PERMOHONAN", idPermohonan);
			if (rs.next()){
				r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				r.add("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				r.add("PEGANGAN_HAKMILIK", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				r.add("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				r.add("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				r.add("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				r.add("ID_LUAS", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				r.add("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
			}
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
			log.info("StatusChange_Action::TBLHTPHAKMILIKURUSAN = "+sql);
			stmt.executeUpdate(sql);
			
			
			conn.commit();
			
			//session.setAttribute("idFail", idFail);
			
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
	    session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}
	
	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon1 = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_MASUK, C.NAMA_PEMOHON, C.NO_PEMOHON, C.ALAMAT_PEMOHON1, C.ALAMAT_PEMOHON2, C.ALAMAT_PEMOHON3, " + 
					"C.POSKOD, C.ID_BANDAR, C.ID_NEGERI, C.NO_TEL, C.NO_FAX, " + 
					"C.EMEL, UPPER(D.KATEGORI) AS KATEGORI " + 
					"FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPEMOHON C, USERS_ONLINE D, TBLRUJBANDAR E " + 
					"WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_BANDAR = E.ID_BANDAR(+) AND D.USER_ID = C.ID_MASUK AND D.USER_ID = '"+ idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idKategori",
						rs.getString("KATEGORI") == null ? "99999"
								: rs.getString("KATEGORI"));
				h.put("namaPemohon",
						rs.getString("NAMA_PEMOHON") == null ? "" : rs
								.getString("NAMA_PEMOHON"));
				
				h.put("noPemohon",
						rs.getString("NO_PEMOHON") == null ? "" : rs
								.getString("NO_PEMOHON"));
				
				h.put("alamat1", rs.getString("ALAMAT_PEMOHON1") == null ? ""
						: rs.getString("ALAMAT_PEMOHON1"));
				
				h.put("alamat2", rs.getString("ALAMAT_PEMOHON2") == null ? ""
						: rs.getString("ALAMAT_PEMOHON2"));
				
				h.put("alamat3", rs.getString("ALAMAT_PEMOHON3") == null ? ""
						: rs.getString("ALAMAT_PEMOHON3"));
				
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "99999" : rs
								.getString("ID_NEGERI"));
				
				h.put("idBandar",
						rs.getString("ID_BANDAR") == null ? "99999" : rs
								.getString("ID_BANDAR"));
				
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));

				beanMaklumatPemohon1.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon1;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon1 = beanMaklumatPemohon;
	}
//	public String generateNoFail(String kodUrusan, String kodKementerian, String idKementerian, String kodMampu, String idNegeri) throws Exception{
//		String noFail = "";
//		noFail = "JKPTG/101/" + kodUrusan + "/" + kodKementerian + "/" +  kodMampu + "-" + File.getSeqNo(3, 3, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));
//		return noFail;
//		
//	}
	
	public static String generateNoOnline(int idUrusan, String kodKementerian, String idKementerian, String kodNegeri, String idNegeri) throws Exception{
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);
		String noFail = "";
		String X = String.format("%04d",File.getSeqNo(3, idUrusan,Integer.parseInt(idKementerian), Integer.parseInt(idNegeri),getYear));

		noFail += "JKPTG/SHTP/"+ kodKementerian + "/"+ kodNegeri + "/"+X+"/"+getYear;				
		log.info("generateNoOnline:"+noFail);
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
		String namaUser = "";
		
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
			
//			r.add("NO_FAIL", generateNoFail(kodUrusan, getKodKementerian(idKementerian), idKementerian, getKodMampu(idNegeri), idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			log.info("sql:TBLPFDFAIL="+sql);
			stmt.executeUpdate(sql);
			
			
			//TBLHTPPEMOHON v6.5
			ResultSet rsUser = stmt.executeQuery(sql);
			
			r = new SQLRenderer();
			long idPemohon = DB.getNextID(db,"TBLHTPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			if (rsUser.next()){
				if (rsUser.getString("USER_NAME") != null){
					namaUser = rsUser.getString("USER_NAME");
				}
				r.add("NO_PENGENALAN", rsUser.getString("NO_KP_BARU") == null ? "" : rsUser.getString("NO_"));
				r.add("ALAMATPEMOHON1", rsUser.getString("ALAMAT1") == null ? "" : rsUser.getString("ALAMAT1"));
				r.add("ALAMATPEMOHON2", rsUser.getString("ALAMAT2") == null ? "" : rsUser.getString("ALAMAT2"));
				r.add("ALAMATPEMOHON3", rsUser.getString("ALAMAT3") == null ? "" : rsUser.getString("ALAMAT3"));
				r.add("POSKOD_TETAP", rsUser.getString("POSKOD") == null ? "" : rsUser.getString("POSKOD"));
				r.add("ID_NEGERITETAP", rsUser.getString("ID_NEGERI") == null ? "99999" : rsUser.getString("ID_NEGERI"));
				r.add("ID_BANDARTETAP","99999");
				r.add("NO_TEL", rsUser.getString("NO_TEL") == null ? "" : rsUser.getString("NO_TEL"));
				r.add("NO_FAX", rsUser.getString("NO_FAX") == null ? "" : rsUser.getString("NO_FAX"));
			}
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPEMOHON");
			log.info("sql:TBLHTPPEMOHON="+sql);
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
	
	public void UpdatePermohonan(String idFail,String subUrusan, String idPemohon, Hashtable<String,String> hash, HttpSession session) throws Exception {
		Connection conn = null;
		String sql = "";
		Db db = new Db();
		
		try {
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			String userId = session.getAttribute("_ekptg_user_id").toString();
			SQLRenderer r = new SQLRenderer();	

			/*Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("3",subUrusan,"=");
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("3",subUrusan,"=");
			*/
			
			//TBLHTPPEMOHON
			r.update("ID_PEMOHON", idPemohon);	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPPEMOHON");
			stmt.executeUpdate(sql);
			
			//TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", hash.get("tajuk"));				
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			log.info("TBLPFDFAIL_sql="+sql);
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.add("TUJUAN", hash.get("tajuk"));	
			//r.add("ID_STATUS",setIdStatus);		
			String tarikhSuratKJP = "to_date('" + hash.get("tarikhSuratKJP").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhSuratKJP").toString())){
				r.add("TARIKH_SURAT", r.unquote(tarikhSuratKJP));
			}			
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			log.info("TBLPERMOHONAN_sql="+sql);
			stmt.executeUpdate(sql);
						
			//get id permohonan
			sql = "SELECT A.ID_PERMOHONAN ";
			sql += "FROM TBLPERMOHONAN A ";
			sql += "WHERE A.ID_FAIL ='"+idFail+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			String idPermohonan = "";			
			while(rs.next()){
				idPermohonan = rs.getString("ID_PERMOHONAN");
			}
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN_LAIN", hash.get("noFailLain"));
			r.add("NO_RUJUKAN_KJP", hash.get("noFailKJP"));
			
			String tarikhAgihan = "to_date('" + hash.get("tarikhAgihan").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhAgihan").toString())){
				r.add("TARIKH_AGIHAN", r.unquote(tarikhAgihan));
			}
			
			String tarikhSuratPemohon = "to_date('" + hash.get("tarikhSuratPemohon").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhSuratPemohon").toString())){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(tarikhSuratPemohon));
			}
			
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			log.info("TBLHTPPERMOHONAN_sql="+sql);
			stmt.executeUpdate(sql);
			conn.commit();
			
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
		
	}
	
	//HANTAR PENGESAHAN
	public void updatePengesahan(String idFail,String idPermohonan,HttpSession session) throws Exception {
		log.info(idFail);
				
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "";
		String idhakmilikUrusan = "";
		String noPermohonan = "";
		String idSuburusan = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
									
		sql = "SELECT B.ID_HAKMILIKURUSAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN, D.NAMA_PEMOHON, D.EMEL " 
				+ " FROM TBLPERMOHONAN A,TBLHTPHAKMILIKURUSAN B, TBLPFDFAIL C,TBLHTPPEMOHON D "
				+ " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN "
				+ " AND A.ID_PEMOHON = D.ID_PEMOHON AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
						
						
		ResultSet rsPermohonan = stmt.executeQuery(sql);
		log.info("sql update pengesahan="+sql);
		if (rsPermohonan.next()){
			idhakmilikUrusan = rsPermohonan.getString("ID_HAKMILIKURUSAN");
			noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
			idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
			namaUser = rsPermohonan.getString("NAMA");
			emelUser = rsPermohonan.getString("EMEL");
		}	
						
		//TBLPERMOHONAN
		Long setIdStatus = 0L;
		setIdStatus = FrmUtilData.getIdStatusByLangkah("-1","7","=");
		r.update("ID_PERMOHONAN", idPermohonan);			
		r.add("ID_JKPTG", "1");
		r.add("ID_FAIL", idFail);
		r.add("ID_STATUS", "1610197");
						
		r.add("ID_KEMASKINI", userId);
		r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
	
		sql = r.getSQLUpdate("TBLPERMOHONAN");
		log.info("TBLPERMOHONAN_sql update pengesahan="+sql);
		stmt.executeUpdate(sql);
		
		conn.commit();
		
		if (!"".equals(namaUser) && !"".equals(emelUser)){
			EkptgEmailSender email = EkptgEmailSender.getInstance();
			email.FROM = "etapp_webmaster@kptg.gov.my";
			email.RECIEPIENT = emelUser;				
			email.SUBJECT = "PERMOHONAN HARTA TANAH PERSEKUTUAN - PAJAKAN #" + noPermohonan;
			email.MESSAGE = namaUser.toUpperCase() + "."
							+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
							+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan."
							+ "<br><br>Terima Kasih.";
			email.sendEmail();
		}
						
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
	}
			
	public String getIdSuburusanstatus(String idSuburusan, String idStatus) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
					
			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '" + idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";
					
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_SUBURUSANSTATUS");
			} else {
					return "";
			}
					
			}  catch (Exception re) {
				log.error("Error: ", re);
				throw re;
			}	finally {
			if (db != null)
				db.close();
			}
	}
		
	//DELETEPERMOHONAN
	public void hapusPermohonan(String idFail) throws Exception {
			Db db = null;
			Connection conn = null;
			String sql = "";

			try {
				db = new Db();
				conn = db.getConnection();
		    	conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();	
				
				//TBLHTPPERMOHONAN
				sql = "DELETE FROM TBLHTPPERMOHONAN WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
				stmt.executeUpdate(sql);
				
				//TBLHTPHAKMILIKURUSAN 
				sql = "DELETE FROM TBLHTPHAKMILIKURUSAN WHERE ID_PERMOHONAN IN"
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
				stmt.executeUpdate(sql);
				
				//TBLHTPPEMOHON 
				sql = "DELETE FROM TBLHTPPEMOHON WHERE ID_PEMOHON IN "
					+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
				stmt.executeUpdate(sql);
				
				//TBLPERMOHONAN
				sql = "DELETE FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
				stmt.executeUpdate(sql);
				
				//TBLRUJSUBURUSANSTATUSFAIL
				sql = "DELETE FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_PERMOHONAN IN "
					+ "(SELECT ID_PERMOHONAN FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_FAIL IN (" + idFail + "))";
				stmt.executeUpdate(sql);
				
				//TBLPFDFAIL
				sql = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL IN (" + idFail + ")";
				stmt.executeUpdate(sql);
				
				conn.commit();
				
			} catch (SQLException ex) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException e) {
		    		throw new Exception("Rollback error : " + e.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
		    	
		    } finally {
				if (db != null)
					db.close();
			}	
		}
		
	//LAMPIRAN
	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran,
			String txtCatatan, HttpSession session) throws Exception {
			Db db = null;
			Connection conn = null;
			String userId = (String) session.getAttribute("_ekptg_user_id");
			String sql = "";

			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				//TBLHTPDOKUMEN
				r.update("ID_DOKUMEN", idDokumen);
				r.add("NAMA_DOKUMEN", txtNamaLampiran);
				r.add("CATATAN", txtCatatan);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPDOKUMEN");
				log.info("sql : " +sql);
				stmt.executeUpdate(sql);
				
				conn.commit();
					
				/*
				 * AuditTrail.logActivity("1610198", "4", null, session, "UPD", "FAIL [" +
				 * idDokumen + "] DIKEMASKINI");
				 */

			} catch (SQLException ex) {
				try {
					conn.rollback();
				} catch (SQLException e) {
					throw new Exception("Rollback error : " + e.getMessage());
				}
				throw new Exception("Ralat : Masalah penyimpanan data "
						+ ex.getMessage());

			} finally {
				if (db != null)
					db.close();
			}
		}
		
	public void hapusLampiran(String idDokumen, HttpSession session) throws Exception {
			Db db = null;
			Connection conn = null;
			String sql = "";

			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				Statement stmt = db.getStatement();

				// TBLHTPDOKUMEN
				SQLRenderer r = new SQLRenderer();
				r.add("ID_DOKUMEN", idDokumen);

				sql = r.getSQLDelete("TBLHTPDOKUMEN");
				log.info("sql : " +sql);
				stmt.executeUpdate(sql);

				conn.commit();
					
				/*
				 * AuditTrail.logActivity("1610198", "4", null, session, "DEL", "FAIL [" +
				 * idDokumen + "] DIHAPUSKAN");
				 */

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
}
