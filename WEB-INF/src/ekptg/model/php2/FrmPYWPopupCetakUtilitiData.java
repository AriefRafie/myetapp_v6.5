package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmPYWPopupCetakUtilitiData {

	private Vector beanMaklumatPegawai = null;
	private Vector beanNegeri = null;
	private Vector beanBandar = null;

	public void setMaklumatPegawai(String idPegawai) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatPegawai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PEGAWAI");
			r.add("JAWATAN");
			r.add("NO_TEL_PEJABAT");
			r.add("NO_FAKS");
			r.add("EMEL");

			r.add("ID_PEGAWAI", idPegawai);

			sql = r.getSQLSelect("TBLRUJPEGAWAI");
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

	public void setMaklumatNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJNEGERI.NAMA_NEGERI " + "FROM "
					+ "TBLRUJNEGERI " + "WHERE TBLRUJNEGERI.ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("negeri",
						rs.getString("NAMA_NEGERI") == null ? " " : rs
								.getString("NAMA_NEGERI"));
				beanNegeri.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatBandar(String idBandar) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanBandar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJBANDAR.KETERANGAN " + "FROM " + "TBLRUJBANDAR "
					+ "WHERE TBLRUJBANDAR.ID_BANDAR = '" + idBandar + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();

				h.put("bandar",
						rs.getString("KETERANGAN") == null ? " " : rs
								.getString("KETERANGAN"));
				beanBandar.addElement(h);
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

	public Vector getBeanMaklumatNegeri() {
		return beanNegeri;
	}

	public Vector getBeanMaklumatBandar() {
		return beanBandar;
	}
}
