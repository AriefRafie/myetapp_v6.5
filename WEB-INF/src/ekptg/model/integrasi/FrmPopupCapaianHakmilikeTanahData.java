
package ekptg.model.integrasi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.view.integrasi.etanah.FrmPopupCapaianHakmilikeTanah;

/**
 * @author mohd faizal
 */
public class FrmPopupCapaianHakmilikeTanahData {
	
	static Logger myLog = Logger.getLogger(FrmPopupCapaianHakmilikeTanahData.class);
	private Vector<Hashtable<String,String>> senaraiHakmilik = null;
	private Vector beanMaklumatHakmilik = null;
	private String idHTA = null;
	private String flagMsg = null;
	private String outputMsg = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector<Hashtable<String,String>> senaraiCarian(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			senaraiHakmilik = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT FLAG_AKTIF "
//					+ "* "
					+ " ,ID_MAKLUMATANAH "
					+ " ,ID_HAKMILIK "
					+ " ,NO_RESIT "
					+ " ,TARIKH_TERIMA "
					+ " FROM TBLINTMAKLUMATANAH WHERE ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY TARIKH_TERIMA, FLAG_AKTIF DESC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idMT", rs.getString("ID_MAKLUMATANAH") == null ? "" : rs.getString("ID_MAKLUMATANAH"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("noResit", rs.getString("NO_RESIT") == null ? "" : rs.getString("NO_RESIT"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));		
				h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				senaraiHakmilik.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return senaraiHakmilik;
		
	}
	
	public Hashtable<String,String> setMaklumatHakmilik(String idMT) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable<String,String> h = null;

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HTA.ID_MAKLUMATANAH, HTA.ID_PERMOHONAN, HTA.NO_RESIT, HTA.ID_HAKMILIK, HTA.ID_JENISHM, JENISHM.KOD_JENIS_HAKMILIK, HTA.NO_HAKMILIK, HTA.NO_PT, HTA.ID_LUAS, HTA.LUAS, LUAS.KETERANGAN AS JENIS_LUAS, HTA.ID_MUKIM, MUKIM.NAMA_MUKIM, HTA.ID_DAERAH, DAERAH.NAMA_DAERAH,"
					+ " HTA.ID_NEGERI, NEGERI.NAMA_NEGERI, HTA.BA_SIMATI, HTA.BB_SIMATI,"
					+ " HTA.PAJAKAN, HTA.NO_PERSERAHAN_PAJAKAN, HTA.ID_KATEGORI, HTA.STATUS_PEMILIKAN, HTA.GADAIAN, HTA.NO_PERSERAHAN_GADAIAN, HTA.KAVEAT, HTA.NO_PERSERAHAN_KAVEAT, HTA.SYARAT_NYATA, HTA.SEKATAN, HTA.CATATAN,"
					+ " RUJKATEGORI.KETERANGAN AS KATEGORI_TANAH, UPPER(JENISPB.KETERANGAN) AS PEMILIKAN"
					
					+ " FROM TBLINTMAKLUMATANAH HTA, TBLRUJJENISHAKMILIK JENISHM, TBLRUJLUAS LUAS, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM, TBLRUJKATEGORI RUJKATEGORI"
					+ ", TBLRUJJENISPB JENISPB"
					
					+ " WHERE HTA.ID_JENISHM = JENISHM.ID_JENISHAKMILIK(+) "
					+ " AND HTA.ID_LUAS = LUAS.ID_LUAS(+) "
					+ " AND HTA.ID_NEGERI = NEGERI.ID_NEGERI(+) "
					+ " AND  HTA.ID_DAERAH = DAERAH.ID_DAERAH(+) "
					+ " AND HTA.ID_NEGERI = DAERAH.ID_NEGERI(+)"
					+ " AND HTA.ID_MUKIM = MUKIM.ID_MUKIM(+) "
					+ " AND HTA.ID_DAERAH = MUKIM.ID_DAERAH(+) "
					+ " AND HTA.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) "
					+ " AND UPPER(HTA.STATUS_PEMILIKAN) = UPPER(JENISPB.KOD_JENIS_PB(+))"
					+ " AND HTA.ID_MAKLUMATANAH = '" + idMT + "'";
			myLog.info("setMaklumatHakmilik:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

//			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idMT", rs.getString("ID_MAKLUMATANAH") == null ? "" : rs.getString("ID_MAKLUMATANAH"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noResit", rs.getString("NO_RESIT") == null ? "" : rs.getString("NO_RESIT"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));			
				h.put("idJenisHakmilik", rs.getString("ID_JENISHM") == null ? "" : rs.getString("ID_JENISHM"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("hakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase()) + " " + (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK")));				
				h.put("lot", rs.getString("NO_PT") == null ? "" : rs.getString("NO_PT"));				
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				h.put("luas", (rs.getString("LUAS") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));								
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());	
				h.put("ba", rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI"));
				h.put("bb", rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI"));
				h.put("pajakan", rs.getString("PAJAKAN") == null ? "" : rs.getString("PAJAKAN"));
				h.put("noPerserahan", rs.getString("NO_PERSERAHAN_PAJAKAN") == null ? "" : rs.getString("NO_PERSERAHAN_PAJAKAN"));
				h.put("kategoriTanah", rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH"));
				h.put("statusPemilikan", rs.getString("PEMILIKAN") == null ? "" : rs.getString("PEMILIKAN"));
				h.put("gadaian", rs.getString("GADAIAN") == null ? "" : rs.getString("GADAIAN"));
				h.put("noPerserahanGadaian", rs.getString("NO_PERSERAHAN_GADAIAN") == null ? "" : rs.getString("NO_PERSERAHAN_GADAIAN"));
				h.put("kaveat", rs.getString("KAVEAT") == null ? "" : rs.getString("KAVEAT"));
				h.put("noPerserahanKaveat", rs.getString("NO_PERSERAHAN_KAVEAT") == null ? "" : rs.getString("NO_PERSERAHAN_KAVEAT"));				
				h.put("syarat", rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("SYARAT_NYATA").toUpperCase());	
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());	
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());	
			
				beanMaklumatHakmilik.addElement(h);
//				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		return h;
		
	}
	
	public void daftarHakmilik(String idPPKHTA, String noResit, String idHakmilik, String idPermohonan, String userId) 
		throws Exception {	
		Db db = null;
		Connection conn = null;
		//String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			
			sql = "SELECT * FROM TBLINTMAKLUMATANAH WHERE "
				+ "NO_RESIT = '" + noResit + "' "
				+ "AND ID_HAKMILIK = '" + idHakmilik + "' "
				+ "AND ID_PERMOHONAN = '" + idPermohonan + "'";
			myLog.info("daftarHakmilik:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				String noHakmilik = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				String noPT = rs.getString("NO_PT") == null ? "" : rs.getString("NO_PT");
				String idKategori = rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI");
				String idJenisHM = rs.getString("ID_JENISHM") == null ? "" : rs.getString("ID_JENISHM");
				//String idJenisPB = rs.getString("ID_JENISPB") == null ? "" : rs.getString("ID_JENISPB");
				String idNegeri = rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI");
				String idDaerah = rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH");
				String idMukim = rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM");
				String idLuas = rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS");
				String luas = rs.getString("LUAS") == null ? "" : rs.getString("LUAS");
				String jenisTNH = rs.getString("JENIS_TANAH") == null ? "" : rs.getString("JENIS_TANAH");
				//String noPerserahan = rs.getString("NO_PERSERAHAN_PAJAKAN") == null ? "" : rs.getString("NO_PERSERAHAN_PAJAKAN");
				String catatan = rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN");
				//String baSimati = rs.getString("BA_SIMATI") == null ? "" : rs.getString("BA_SIMATI");
				//String bbSimati = rs.getString("BB_SIMATI") == null ? "" : rs.getString("BB_SIMATI");
				//String statusPemilikan = rs.getString("STATUS_PEMILIKAN") == null ? "" : getJenisPB(rs.getString("STATUS_PEMILIKAN"));
				String syaratNyata = rs.getString("SYARAT_NYATA") == null ? "" : rs.getString("SYARAT_NYATA");
				String sekatan = rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN");
				
				
				if (hakmilikRegistered(noResit, idHakmilik, idPermohonan)){
					//UPDATE
					r.update("ID_HAKMILIK", this.getIdHTA());
					r.add("NO_HAKMILIK", noHakmilik);
					r.add("NO_PT", noPT);
					r.add("ID_KATEGORITANAH", idKategori);
					r.add("ID_JENISHAKMILIK", idJenisHM);
//					r.add("ID_JENISPB", idJenisPB);
					r.add("ID_NEGERI", idNegeri);
					r.add("ID_DAERAH", idDaerah);
					r.add("ID_MUKIM", idMukim);	
					r.add("ID_UNITLUASLOT", idLuas);
					r.add("LUAS_LOT", luas);
//					r.add("ID_JENISTANAH", jenisTNH);
//					r.add("NO_PERSERAHAN", noPerserahan);	
					r.add("CATATAN", catatan);					
//					r.add("BA_SIMATI", baSimati);
//					r.add("BB_SIMATI", bbSimati);
//					r.add("JENIS_HTA", "Y");
//					r.add("STATUS_PEMILIKAN", statusPemilikan);
					r.add("SYARAT_NYATA", syaratNyata);
					r.add("SEKATAN_KEPENTINGAN", sekatan);
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPPTHAKMILIK");
					stmt.executeUpdate(sql);
						
					conn.commit();
					
					this.flagMsg = "Y";
					
				} else {
					//INSERT
					long idHTA = DB.getNextID("TBLPPTHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHTA);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("NO_HAKMILIK", noHakmilik);
					r.add("NO_PT", noPT);
					r.add("ID_KATEGORITANAH", idKategori);
					r.add("ID_JENISHAKMILIK", idJenisHM);
					r.add("ID_NEGERI", idNegeri);
					r.add("ID_DAERAH", idDaerah);
					r.add("ID_MUKIM", idMukim);	
					r.add("ID_UNITLUASLOT", idLuas);
					r.add("LUAS_LOT", luas);
//					r.add("ID_JENISTANAH", jenisTNH);
//					r.add("NO_PERSERAHAN", noPerserahan);	
					r.add("CATATAN", catatan);					
//					r.add("STATUS_PEMILIKAN", statusPemilikan);
					r.add("SYARAT_NYATA", syaratNyata);
					r.add("SEKATAN_KEPENTINGAN", sekatan);
//					r.add("NO_RESIT_CARIAN", noResit);
					r.add("PGNHM", idHakmilik);
					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPTHAKMILIK");
					myLog.info("sql="+sql);
					stmt.executeUpdate(sql);
					
//		    		r.add("id_daerahpenggawa", socDaerahPenggawa);
//		    		r.add("no_warta_rizab", txtNoWartaRizab); 	
//		    		r.add("tarikh_warta_rizab", r.unquote(TW));
//		    		r.add("flag_jenis_rizab", sorJenisRizab); 	
//		    		r.add("nama_lain_rizab", txtLain);
//		    		r.add("id_lot", id_lot);
//		    		r.add("no_lot", txtnolot);
//		    		r.add("seksyen",txtseksyen);	    		
//		    		r.add("tarikh_daftar",r.unquote(TD));
//		    		r.add("tarikh_luput",r.unquote(TL));
//		    		r.add("tempoh_luput", baki);
//		    		r.add("lokasi",lokasi);	    		
//		    		r.add("syarat_khas", syaratKhas);
//		    		r.add("sekatan_hak",sekatanHak);
//		    		r.add("no_syit",noSyit);

					//UPDATE
//					r = new SQLRenderer();
//					r.update("ID_HAKMILIK", idPPKHTA);
//					r.add("FLAG_AKTIF", "T");
//
//					sql = r.getSQLUpdate("TBLINTMAKLUMATANAH");
//					stmt.executeUpdate(sql);
					
					sql = "SELECT * FROM TBLINTMAKLUMATANAHOB WHERE "
							+ "ID_HAKMILIK = '" + idHakmilik + "' "
							//+ "AND ID_PERMOHONAN = '" + idPermohonan + "'"
						+ "";
						myLog.info("TBLINTMAKLUMATANAHOB:sql="+sql);
					rs = stmt.executeQuery(sql);
					if (rs.next()){
						
					}
					
					conn.commit();
					this.flagMsg = "Y";
				}
				
			}		
			
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

	private String getJenisPB(String jenisPB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLRUJJENISPB WHERE UPPER(KOD_JENIS_PB) = '" + jenisPB.toUpperCase() + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_JENISPB");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	private String getIdSimati(String idPermohonanSimati) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_SIMATI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	private boolean hakmilikRegistered(String noResit, String idHakmilik, String idPermohonanSimati) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPTHAKMILIK "+ "WHERE PGNHM = '" + idHakmilik + "'";
			myLog.info("hakmilikRegistered:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				this.idHTA = rs.getString("ID_HAKMILIK");
				return true;
				
			} else {
				return false;
			}
			
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

	public String getIdHTA() {
		return idHTA;
	}

	public void setIdHTA(String idHTA) {
		this.idHTA = idHTA;
	}

	public String getFlagMsg() {
		return flagMsg;
	}

	public void setFlagMsg(String flagMsg) {
		this.flagMsg = flagMsg;
	}

	public String getOutputMsg() {
		return outputMsg;
	}

	public void setOutputMsg(String outputMsg) {
		this.outputMsg = outputMsg;
	}

}
