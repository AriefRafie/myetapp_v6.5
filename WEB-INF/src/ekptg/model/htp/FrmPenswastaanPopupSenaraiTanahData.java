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
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * 
 *
 */
public class FrmPenswastaanPopupSenaraiTanahData {
	
	private Vector senaraiTanah = null;	
	private Vector beanMaklumatTanah = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianTanah(String idJenisTanah, String peganganHakmilik, String jenisHakmilik, String noHakmilik, String jenisLot,
			String lot, String noWarta, String tarikhWarta, String idNegeri, String idDaerah, String idMukim) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH,"
				+ " C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI" +
						",NVL((SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = A.ID_JENISHAKMILIK),'') NAMA_JENISHAKMILIK"
				+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+)";
				
			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND A.STATUS_RIZAB IS NOT NULL";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND A.STATUS_RIZAB IS NULL";
					}
				}
			}
			
			//peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("") && !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND A.ID_JENISHAKMILIK = '" + jenisHakmilik.trim().toUpperCase() + "'";
				}
			}
			
			//noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("") && !jenisLot.trim().equals("99999")) {
					sql = sql + " AND A.ID_LOT = '" + jenisLot.trim().toUpperCase() + "'";
				}
			}
			
			//lot
			if (lot != null) {
				if (!lot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'"
							+ lot.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//tarikhWarta
			if (tarikhWarta != null) {
				if (!tarikhWarta.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_WARTA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhWarta)).toUpperCase() +"'";
				}
			}
			
			//idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("") && !idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri.trim().toUpperCase() + "'";
				}
			}
			
			//idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("") && !idDaerah.trim().equals("99999")) {
					sql = sql + " AND A.ID_DAERAH = '" + idDaerah.trim().toUpperCase() + "'";
				}
			}
			
			//idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("") && !idMukim.trim().equals("99999")) {
					sql = sql + " AND A.ID_MUKIM = '" + idMukim.trim().toUpperCase() + "'";
				}
			}
						
			sql = sql + " ORDER BY A.ID_HAKMILIK ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("namaJenisHakmilik", Utils.isNull(rs.getString("NAMA_JENISHAKMILIK")));
				senaraiTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}		
	}
	
	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK, B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.LUAS, A.NO_WARTA,"
				+ " A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS"
				+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, TBLRUJLUAS K"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+)"
				+ " AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS AND A.ID_HAKMILIK = '" + idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("jenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("JENIS_HAKMILIK") == null? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " - " + rs.getString("JENIS_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("KOD_LOT") == null || rs.getString("JENIS_LOT") == null? "" : rs.getString("KOD_LOT").toUpperCase() + " - " + rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));				
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				beanMaklumatTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveHakmilikUrusan(String idHakmilik, String idPermohonan, String tindakanLanjut, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlSelect = "";
		String sql = "";
		String TR = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "  select NO_HAKMILIK,ID_LUAS,PEGANGAN_HAKMILIK,NO_WARTA ,ID_LUAS,PEGANGAN_HAKMILIK,to_char(tarikh_warta,'dd/MM/YYYY')as TARIKH_WARTA,"+
				  "	NO_LOT ,LUAS,NO_SYIT,NO_PELAN,SYARAT,SEKATAN,CUKAI,STATUS_SWASTA,ID_SUBKATEGORI,CUKAI_TERKINI,STATUS_RIZAB,NO_BANGUNAN,NO_TINGKAT,"+
				  "	NO_PETAK,LOKASI,ID_KATEGORI,ID_NEGERI,ID_DAERAH,ID_MUKIM,ID_LOT,ID_JENISHAKMILIK,ID_JENISHAKMILIK FROM TBLHTPHAKMILIK WHERE ID_HAKMILIK = '" + idHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				String t = rs.getString("TARIKH_WARTA");
				if(t!=null){
					TR = "to_date('" + t + "','dd/MM/yyyy')";
				}
				else{
					TR= "";
				}
				
				//TBLHTPHAKMILIKURUSAN
				long idHakmilikUrusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
				r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
				r.add("ID_PERMOHONAN", idPermohonan);	
				r.add("ID_LUAS", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				r.add("PEGANGAN_HAKMILIK", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				r.add("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				r.add("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				if(!TR.equals(""))
					r.add("TARIKH_WARTA",r.unquote(TR));
				r.add("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				r.add("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));				
				r.add("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT"));
				r.add("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				r.add("SYARAT", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
				r.add("SEKATAN", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
				r.add("CUKAI", rs.getString("CUKAI") == null ? "" : rs.getString("CUKAI"));
				r.add("STATUS_SWASTA", rs.getString("STATUS_SWASTA") == null ? "" : rs.getString("STATUS_SWASTA"));				
				r.add("ID_SUBKATEGORI", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				r.add("CUKAI_TERKINI", rs.getString("CUKAI_TERKINI") == null ? "" : rs.getString("CUKAI_TERKINI"));
				r.add("STATUS_RIZAB", rs.getString("STATUS_RIZAB") == null ? "" : rs.getString("STATUS_RIZAB"));
				r.add("NO_BANGUNAN", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN"));
				r.add("NO_TINGKAT", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT"));				
				r.add("NO_PETAK", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK"));
				r.add("LOKASI", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI"));
				r.add("ID_KATEGORI", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				r.add("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));				
				r.add("FLAG_PTP", "Y");
				r.add("TINDAKAN_LANJUT", tindakanLanjut);
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
				stmt.executeUpdate(sql);
			}
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	ex.printStackTrace();
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiTanah() {
		return senaraiTanah;
	}

	public void setSenaraiTanah(Vector senaraiTanah) {
		this.senaraiTanah = senaraiTanah;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}


}
