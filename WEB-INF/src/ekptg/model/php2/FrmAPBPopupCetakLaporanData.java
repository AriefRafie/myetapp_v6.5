package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmAPBPopupCetakLaporanData {

	private Vector beanMaklumatPegawai = null;

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

	// GET MAKLUMAT PEGAWAI FROM USERS -AIN 17052017-
	public void setMaklumatPegawai(String idPegawai) throws Exception {
		Db db = null;

		String sql = "SELECT A.USER_NAME AS NAMA_PEGAWAI, C.KETERANGAN AS JAWATAN, B.NO_TEL_PEJABAT, "
				+ " B.NO_FAKS, B.EMEL"
				+ " FROM USERS A, USERS_INTERNAL B, TBLRUJJAWATAN C"
				+ " WHERE A.USER_ID = B.USER_ID"
				+ " AND B.ID_JAWATAN = C.ID_JAWATAN(+)"
				+ " AND A.USER_ID ='"
				+ idPegawai + "'";

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

	public Vector getBeanMaklumatPegawai() {
		return beanMaklumatPegawai;
	}

	public void setBeanMaklumatPegawai(Vector beanMaklumatPegawai) {
		this.beanMaklumatPegawai = beanMaklumatPegawai;
	}
}
