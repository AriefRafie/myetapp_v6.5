package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmPYWPopupCetakLaporanData {

	private Vector beanMaklumatPegawai = null;
	private Vector beanDuitRM = null;
	private Vector beanMesyuarat = null;
	private Vector beanMaklumatUser = null;

	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL");
			}
			return "";

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoFailNegeriByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT NO_FAIL_NEGERI FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NO_FAIL_NEGERI");
			}
			return "";

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//GET MAKLUMAT PEGAWAI FROM USERS -AIN 17052017-
	public void setMaklumatPegawai(String idPegawai) throws Exception {
		Db db = null;

		String sql = "SELECT A.USER_NAME AS NAMA_PEGAWAI, C.KETERANGAN AS JAWATAN, B.NO_TEL_PEJABAT, " +
				" B.NO_FAKS, B.EMEL" +
				" FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C" +
				" WHERE A.USER_ID = B.USER_ID" +
				" AND B.ID_JAWATAN = C.ID_JAWATAN(+)" +
				" AND A.USER_ID ='" + idPegawai + "'";
		
		try {
			beanMaklumatPegawai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("nama", rs.getString("NAMA_PEGAWAI") == null ? "" : rs
						.getString("NAMA_PEGAWAI").toUpperCase());
				h.put("jawatan", rs.getString("JAWATAN") == null ? "" : rs
						.getString("JAWATAN").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
						.getString("NO_TEL_PEJABAT").toUpperCase());
				h.put("noFaks", rs.getString("NO_FAKS") == null ? "" : rs
						.getString("NO_FAKS").toUpperCase());
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPegawai.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	// GET USER FROM USERS - ASNA
	public void setMaklumatUser(String idPegawai) throws Exception {
		Db db = null;

		String sql = "SELECT D.USER_NAME, A.EMEL, B.NAMA_PEJABAT,B.NO_TEL, NO_FAX, C.KETERANGAN FROM USERS_INTERNAL A, TBLRUJPEJABATJKPTG B, TBLRUJJAWATAN C, USERS D "
				+ " WHERE A.ID_PEJABATJKPTG = B.ID_PEJABATJKPTG AND A.ID_JAWATAN = C.ID_JAWATAN AND A.USER_ID = D.USER_ID"
				+ " AND A.USER_ID ='" + idPegawai + "'";

		try {
			beanMaklumatUser = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("nama", rs.getString("USER_NAME") == null ? "" : rs
						.getString("USER_NAME").toUpperCase());
				h.put("jawatan", rs.getString("KETERANGAN") == null ? "" : rs
						.getString("KETERANGAN").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFaks", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("pejabat",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT"));
				beanMaklumatUser.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setDuitRM(String idPerjanjian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanDuitRM = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPPERJANJIAN.KADAR_SEWA, TBLPHPPERJANJIAN.CAGARAN, TBLPHPPERJANJIAN.ROYALTI "
					+ "FROM "
					+ "TBLPHPPERJANJIAN "
					+ "WHERE TBLPHPPERJANJIAN.FLAG_PERJANJIAN = 'U' "
					+ "AND TBLPHPPERJANJIAN.ID_PERJANJIAN = '"
					+ idPerjanjian
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("kadarSewa", rs.getString("KADAR_SEWA") == null ? "0.00"
						: rs.getString("KADAR_SEWA"));
				h.put("royalti",
						rs.getString("ROYALTI") == null ? "0.00" : rs
								.getString("ROYALTI"));
				h.put("cagaran",
						rs.getString("CAGARAN") == null ? "0.00" : rs
								.getString("CAGARAN"));
				beanDuitRM.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void getMesyuarat(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT MAX(TO_CHAR(A.TARIKH_MESYUARAT,'DD/MM/YYYY')) AS TARIKH_MESYUARAT, MAX(A.BIL_MESYUARAT) AS BIL_MESYUARAT"
					+ " FROM TBLPHPMESYUARAT A, TBLPHPMESYUARATPERMOHONAN B, TBLPERMOHONAN C"
					+ " WHERE A.ID_MESYUARAT = B.ID_MESYUARAT AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("tarikhMesyuarat",
						rs.getString("TARIKH_MESYUARAT") == null ? " " : rs
								.getString("TARIKH_MESYUARAT"));
				h.put("bilMesyuarat",
						rs.getString("BIL_MESYUARAT") == null ? "" : rs
								.getString("BIL_MESYUARAT"));
				beanMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPegawai() {
		return beanMaklumatPegawai;
	}

	public void setBeanMaklumatPegawai(Vector beanMaklumatPegawai) {
		this.beanMaklumatPegawai = beanMaklumatPegawai;
	}

	public Vector getBeanDuitRM() {
		return beanDuitRM;
	}

	public Vector getBeanMesyuarat() {
		return beanMesyuarat;
	}

	public Vector getBeanMaklumatUser() {
		return beanMaklumatUser;
	}

	public void setBeanMaklumatUser(Vector beanMaklumatUser) {
		this.beanMaklumatUser = beanMaklumatUser;
	}
}
