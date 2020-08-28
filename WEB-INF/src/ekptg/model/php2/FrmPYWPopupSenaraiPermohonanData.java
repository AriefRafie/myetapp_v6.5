package ekptg.model.php2;

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
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPYWPopupSenaraiPermohonanData {

	private Vector senaraiFailMesyuarat = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setSenaraiFailMesyuarat(String idFail) throws Exception {
		
		Db db = null;
		String sql = "";

		try {
			senaraiFailMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C "
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.NO_FAIL IS NOT NULL"
				+ " AND A.ID_FAIL IS NOT NULL AND A.ID_SEKSYEN = '4' AND B.FLAG_AKTIF = 'Y' "
				+ " AND ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND B.ID_STATUS = '1610201' ";
			
			sql = sql + " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiFailMesyuarat() {
		return senaraiFailMesyuarat;
	}

}
