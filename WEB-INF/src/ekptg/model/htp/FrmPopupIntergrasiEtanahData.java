/**
 * 
 */
package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

/**
 * @author mohd faizal
 *
 */
public class FrmPopupIntergrasiEtanahData {
	
	private Vector beanMaklumatHakmilik = null;

	public void setMaklumatHakmilik(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HM.PEGANGAN_HAKMILIK, HM.KEGUNAAN_TANAH, KEM.KOD_KEMENTERIAN, KEM.NAMA_KEMENTERIAN, AGENSI.KOD_AGENSI, AGENSI.NAMA_AGENSI"
					+ " FROM TBLHTPHAKMILIK HM, TBLRUJKEMENTERIAN KEM, TBLRUJAGENSI AGENSI"
					+ " WHERE HM.ID_KEMENTERIAN = KEM.ID_KEMENTERIAN(+) AND HM.ID_AGENSI = AGENSI.ID_AGENSI(+) AND HM.ID_HAKMILIK = '" + idHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());			
				h.put("kodKementerian", rs.getString("KOD_KEMENTERIAN") == null ? "" : rs.getString("KOD_KEMENTERIAN"));
				h.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				h.put("kodAgensi", rs.getString("KOD_AGENSI") == null ? "" : rs.getString("KOD_AGENSI"));
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI"));
			
				beanMaklumatHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodNegeri(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NEGERI.KOD_NEGERI"
					+ " FROM TBLHTPHAKMILIK HM, TBLRUJNEGERI NEGERI"
					+ " WHERE HM.ID_NEGERI = NEGERI.ID_NEGERI(+) AND HM.ID_HAKMILIK = '" + idHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("KOD_NEGERI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

}
