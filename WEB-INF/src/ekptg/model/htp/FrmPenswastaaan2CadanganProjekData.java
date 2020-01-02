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
import ekptg.model.entities.Tblrujsubkategori;

/**
 * 
 *
 */
public class FrmPenswastaaan2CadanganProjekData {
	
	private Vector senaraiHakmilik = null;	
	private Vector beanMaklumatHakmilik = null;	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Logger mylog = Logger.getLogger(ekptg.model.htp.FrmPenswastaaan2CadanganProjekData.class);

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
				+ " AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND A.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY A.ID_HAKMILIKURUSAN ASC";

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
	
	public void setMaklumatHakmilik(String idHakmilikUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH,"
				+ " C.NAMA_NEGERI, NVL(A.ID_JENISHAKMILIK,0) ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI, A.FLAG_PTP, A.TINDAKAN_LANJUT,"
				+ " A.ID_KATEGORI, NVL(A.ID_SUBKATEGORI,109) ID_SUBKATEGORI, F.KOD_JENIS_HAKMILIK, A.SYARAT, A.SEKATAN, NVL(A.ID_LUAS,0) ID_LUAS, A.LUAS"
				+ " FROM TBLHTPHAKMILIKURUSAN A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+)"
				+ " AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK (+) AND A.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "' ORDER BY A.ID_HAKMILIKURUSAN ASC";
			//mylog.info("setMaklumatHakmilik:"+sql);
			//ID_SUBKATEGORI=109, ID_KATEGORI=1(TIADA MAKLUMAT)
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "0" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "0" : rs.getString("ID_DAERAH").toUpperCase());
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "0" : rs.getString("ID_MUKIM").toUpperCase());
				h.put("idLot", rs.getString("ID_LOT") == null ? "0" : rs.getString("ID_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT").toUpperCase());
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS").toUpperCase());
				h.put("luas", rs.getString("LUAS") == null ? "" : rs.getString("LUAS").toUpperCase());
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA").toUpperCase());
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI").toUpperCase());
				h.put("idSubkategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("flagPTP", rs.getString("FLAG_PTP") == null ? "" : rs.getString("FLAG_PTP").toUpperCase());
				h.put("idTindakanLanjut", rs.getString("TINDAKAN_LANJUT") == null ? "" : rs.getString("TINDAKAN_LANJUT").toUpperCase());

				beanMaklumatHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	public void hapusHakmilik(String idHakmilikUrusan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPHAKMILIKURUSAN
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);

			sql = r.getSQLDelete("TBLHTPHAKMILIKURUSAN");
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
	
	public void saveHakmilik(String idPermohonan, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPHAKMILIKURUSAN
			long idHakmilikurusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
			r.add("ID_HAKMILIKURUSAN", idHakmilikurusan);
			r.add("PEGANGAN_HAKMILIK", idHakmilikurusan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_NEGERI", hash.get("idNegeri"));	
			r.add("ID_DAERAH", hash.get("idDaerah"));
			r.add("ID_MUKIM", hash.get("idMukim"));			
			r.add("ID_LOT", hash.get("idLot"));	
			r.add("NO_LOT", hash.get("noLot"));
			r.add("ID_JENISHAKMILIK", hash.get("idJenisHakmilik"));
			r.add("NO_HAKMILIK", hash.get("noHakmilik"));
			r.add("ID_LUAS", hash.get("idLuas"));
			r.add("LUAS", hash.get("luas"));	
			r.add("NO_WARTA", hash.get("noWarta"));
			if (!"".equals(hash.get("tarikhWarta"))){
				r.add("TARIKH_WARTA", r.unquote("to_date('" + hash.get("tarikhWarta") + "','dd/MM/yyyy')"));
			}
			r.add("ID_KATEGORI", hash.get("idKategori"));	
			r.add("ID_SUBKATEGORI", hash.get("idSubkategori"));
			r.add("SYARAT", hash.get("syarat"));
			r.add("SEKATAN", hash.get("sekatan"));
			r.add("TINDAKAN_LANJUT", hash.get("tindakanLanjut"));
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
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
	
	public void saveUpdateHakmilik(String idHakmilikUrusan, Hashtable hash, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPHAKMILIKURUSAN
			r.update("ID_HAKMILIKURUSAN", idHakmilikUrusan);
			r.add("ID_NEGERI", hash.get("idNegeri"));	
			r.add("ID_DAERAH", hash.get("idDaerah"));
			r.add("ID_MUKIM", hash.get("idMukim"));			
			r.add("ID_LOT", hash.get("idLot"));	
			r.add("NO_LOT", hash.get("noLot"));
			r.add("ID_JENISHAKMILIK", hash.get("idJenisHakmilik"));
			r.add("NO_HAKMILIK", hash.get("noHakmilik"));
			r.add("ID_LUAS", hash.get("idLuas"));
			r.add("LUAS", hash.get("luas"));	
			r.add("NO_WARTA", hash.get("noWarta"));
			if (!"".equals(hash.get("tarikhWarta"))){
				r.add("TARIKH_WARTA", r.unquote("to_date('" + hash.get("tarikhWarta") + "','dd/MM/yyyy')"));
			}
			r.add("ID_KATEGORI", hash.get("idKategori"));	
			r.add("ID_SUBKATEGORI", hash.get("idSubkategori"));
			r.add("SYARAT", hash.get("syarat"));
			r.add("SEKATAN", hash.get("sekatan"));
			r.add("TINDAKAN_LANJUT", hash.get("tindakanLanjut"));
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
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
	
	public static String SelectSubKategori(String idKategori, String selectName, Long selectedValue,
			String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>Sila Pilih </option>\n");
			Vector v = getSubKategoriByIdKategori(idKategori);
			Tblrujsubkategori f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsubkategori) v.get(i);
				if (f.getIdSubkategori().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSubkategori() + ">"
						+ f.getKodSubkategori() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
	
	public static Vector getSubKategoriByIdKategori(String idKategori) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Subkategori");
			r.add("kod_subkategori");
			r.add("Keterangan");
			r.add("id_kategori", idKategori);
			sql = r.getSQLSelect("Tblrujsubkategori", "kod_subkategori");
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			Tblrujsubkategori k = null;
			while (rs.next()) {
				k = new Tblrujsubkategori();
				k.setIdSubkategori(rs.getLong("id_Subkategori"));
				k.setKodSubkategori(rs.getString("kod_subkategori"));
				k.setKeterangan(rs.getString("Keterangan"));
				v.addElement(k);
			}
			return v;
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

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}

}
