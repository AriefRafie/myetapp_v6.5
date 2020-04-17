/**
 * 
 */
package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

/**
 * 
 *
 */
public class FrmPenswastaan2HeaderData {
	
private Vector beanMaklumatPermohonan = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			int bil = 1;
			Hashtable h;

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, D.NAMA_NEGERI, E.NAMA_KEMENTERIAN, F.NAMA_AGENSI " 
				+ ", A.ID_SUBURUSAN, G.NAMA_SUBURUSAN, H.KETERANGAN, I.TARAF_KESELAMATAN, A.NO_FAIL,"
				+ " C.NO_RUJUKAN_KJP, B.TARIKH_SURAT, C.NO_RUJUKAN_LAIN, C.TARIKH_AGIHAN" 
				+ ", A.TAJUK_FAIL,RU.NAMA_URUSAN,A.ID_NEGERI "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLRUJNEGERI D, TBLRUJKEMENTERIAN E, "
				+ " TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H, TBLPFDRUJTARAFKESELAMATAN I, TBLRUJURUSAN RU"
				+ " WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL "
				+ "AND A.ID_NEGERI = D.ID_NEGERI AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN " 
				+ "AND C.ID_AGENSI = F.ID_AGENSI "
				+ "AND A.ID_SUBURUSAN = G.ID_SUBURUSAN AND C.ID_JENISTANAH = H.ID_JENISTANAH "
				+ "AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN "
				+ "AND RU.ID_URUSAN=G.ID_URUSAN "
				+ "AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				//h.put("urusan", "PAJAKAN");
				h.put("urusan", rs.getString("NAMA_URUSAN") == null ? "" : rs.getString("NAMA_URUSAN").toUpperCase());
				h.put("subUrusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "0" : rs.getString("ID_NEGERI"));
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}	

}
