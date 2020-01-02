/**
 * 
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

/**
 * 
 *
 */
public class FrmPenswastaan2TukarGantiData {
	
	private Vector senaraiHakmilik = null;	
	private Vector senaraiHakmilikTukarGanti = null;	
	
	public void setListHakmilik(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH,"
				+ " C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI, A.FLAG_PTP, A.TINDAKAN_LANJUT, F.KOD_JENIS_HAKMILIK"
				+ " FROM TBLHTPHAKMILIKURUSAN A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+)"
				+ " AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND A.TINDAKAN_LANJUT = '6' AND A.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY A.ID_HAKMILIKURUSAN ASC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				if ("1".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "SERAHBALIK TANAH");
				} else if ("2".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PINDAHMILIK SEMUA");
				} else if ("3".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PINDAHMILIK SEBAHAGIAN");
				} else if ("4".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PAJAK SEMUA");
				} else if ("5".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PAJAK SEBAHAGIAN");
				} else if ("6".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "TUKARGANTI");
				} else if ("7".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PERMOHONAN TANAH");
				} else if ("8".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PERMOHONAN RIZAB");
				} else if ("9".equals(rs.getString("TINDAKAN_LANJUT"))){
					h.put("tindakanLanjut", "PENAWARAN TANAH");
				} else {
					h.put("tindakanLanjut", "");
				}
				senaraiHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	public void setListHakmilikTukarGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiHakmilikTukarGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT G.ID_TUKARGANTI, A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH,"
				+ " C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI, F.KOD_JENIS_HAKMILIK"
				+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLHTPTUKARGANTI G"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+)"
				+ " AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND G.ID_HAKMILIK = A.ID_HAKMILIK AND G.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idTukarGanti", rs.getString("ID_TUKARGANTI") == null ? "" : rs.getString("ID_TUKARGANTI").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				senaraiHakmilikTukarGanti.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	public void hapusHakmilikTukarGanti(String idTukarGanti) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPTUKARGANTI
			r.add("ID_TUKARGANTI", idTukarGanti);

			sql = r.getSQLDelete("TBLHTPTUKARGANTI");
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

	public Vector getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public void setSenaraiHakmilik(Vector senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public Vector getSenaraiHakmilikTukarGanti() {
		return senaraiHakmilikTukarGanti;
	}

	public void setSenaraiHakmilikTukarGanti(Vector senaraiHakmilikTukarGanti) {
		this.senaraiHakmilikTukarGanti = senaraiHakmilikTukarGanti;
	}

}
