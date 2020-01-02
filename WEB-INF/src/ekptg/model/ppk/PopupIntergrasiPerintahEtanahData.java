/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

/**
 * @author mohd faizal
 *
 */
public class PopupIntergrasiPerintahEtanahData {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Vector beanMaklumatPerintah = null;
	private Vector listHakmilik = null;

	public void getMaklumatPerintah(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPerintah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM INT_PPKPERINTAH PERINTAH, TBLPFDFAIL FAIL WHERE PERINTAH.NO_FAIL = FAIL.NO_FAIL AND FAIL.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPPKPerintah", rs.getString("ID_PPKPERINTAH") == null ? "" : rs.getString("ID_PPKPERINTAH"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("noKPSimati", rs.getString("NO_KPSIMATI") == null ? "" : rs.getString("NO_KPSIMATI"));
				h.put("tarikhMati", rs.getString("TARIKH_MATI") == null ? "" : sdf.format(sdf.parse(rs.getString("TARIKH_MATI"))));				
				h.put("noSijilMati", rs.getString("NO_SIJILMATI") == null ? "" : rs.getString("NO_SIJILMATI"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("noKPPemohon", rs.getString("NO_KPPEMOHON") == null ? "" : rs.getString("NO_KPPEMOHON"));								
				h.put("pegawaiBicara", rs.getString("PEGAWAI_BICARA") == null ? "" : rs.getString("PEGAWAI_BICARA"));
				h.put("tempatBicara", rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA"));
				h.put("tarikhPerintah", rs.getString("TARIKH_PERINTAH") == null ? "" : sdf.format(sdf.parse(rs.getString("TARIKH_PERINTAH"))));			

				beanMaklumatPerintah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void getSenaraiHakmilik(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMPERINTAH.* FROM INT_PPKPERINTAH PERINTAH, TBLPFDFAIL FAIL, INT_PPKHAKMILIKPERINTAH HMPERINTAH WHERE PERINTAH.NO_FAIL = FAIL.NO_FAIL AND PERINTAH.ID_PPKPERINTAH = HMPERINTAH.ID_PPKPERINTAH AND FAIL.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPPKHakmilikPerintah", rs.getString("ID_PPKHAKMILIKPERINTAH") == null ? "" : rs.getString("ID_PPKHAKMILIKPERINTAH"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("ba", rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
				h.put("bb", rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
				if ("1".equals(rs.getString("ID_JENIS_PERINTAH"))){
					h.put("jenisPembahagian", "PERINTAH TERUS");
				} else if ("2".equals(rs.getString("ID_JENIS_PERINTAH"))){
					h.put("jenisPembahagian", "PERINTAH KUASA TADBIR");
				} else if ("3".equals(rs.getString("ID_JENIS_PERINTAH"))){
					h.put("jenisPembahagian", "PERINTAH LELONG");
				} else if ("4".equals(rs.getString("ID_JENIS_PERINTAH"))){
					h.put("jenisPembahagian", "PERINTAH FARAID");
				} else {
					h.put("jenisPembahagian", "");
				}
				h.put("tarikhHantar", rs.getString("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));			
				h.put("flagHantar", rs.getString("FLAG_HANTAR") == null ? "" : rs.getString("FLAG_HANTAR"));
				
				listHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("NO_FAIL");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getFlagHantar(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT H.FLAG_HANTAR FROM INT_PPKPERINTAH P, TBLPFDFAIL F, INT_PPKHAKMILIKPERINTAH H WHERE P.NO_FAIL = F.NO_FAIL"
					+ " AND P.ID_PPKPERINTAH = H.ID_PPKPERINTAH AND H.FLAG_HANTAR = 'T' AND F.ID_FAIL = '" + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("FLAG_HANTAR");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPerintah() {
		return beanMaklumatPerintah;
	}

	public void setBeanMaklumatPerintah(Vector beanMaklumatPerintah) {
		this.beanMaklumatPerintah = beanMaklumatPerintah;
	}

	public Vector getListHakmilik() {
		return listHakmilik;
	}

	public void setListHakmilik(Vector listHakmilik) {
		this.listHakmilik = listHakmilik;
	}
}
