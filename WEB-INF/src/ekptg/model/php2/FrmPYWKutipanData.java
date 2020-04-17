/**
 * 
 */
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

/**
 *  modified by hilda
 *
 */
public class FrmPYWKutipanData {
	
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatBorangK = null;
	private Vector beanMaklumatHakmilik = null;
	private Vector senaraiFail = null;	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_SURAT, B.TARIKH_TERIMA, B.NO_RUJ_SURAT, A.TAJUK_FAIL, B.TUJUAN, B.ID_PEMOHON,"
				+ " A.ID_URUSAN, A.ID_SUBURUSAN, C.FLAG_PROSESFAIL, C.CATATAN, C.TUJUAN AS TUJUAN_BANGUNAN FROM TBLPFDFAIL A, TBLPERMOHONAN B,"
				+ " TBLPHPPERMOHONANSEWA C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"
				+ " AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("noRujukanSurat", rs.getString("NO_RUJ_SURAT") == null ? "" : rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("perkara", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
				h.put("tujuan", rs.getString("TUJUAN_BANGUNAN") == null ? "" : rs.getString("TUJUAN_BANGUNAN").toUpperCase());
				h.put("idUrusan", rs.getString("ID_URUSAN") == null ? "" : rs.getString("ID_URUSAN"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("flagProsesFail", rs.getString("FLAG_PROSESFAIL") == null ? "" : rs.getString("FLAG_PROSESFAIL"));
				beanMaklumatPermohonan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT C.ID_PEMOHON, C.NAMA, C.NAMA_PEGAWAI, C.NO_PENGENALAN, C.PEKERJAAN, C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP,"
				+ " C.POSKOD_TETAP, C.ID_BANDARTETAP, C.ID_NEGERITETAP, C.NO_TEL, C.NO_FAX," 
				+ " C.EMEL, C.ID_KATEGORIPEMOHON "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
								
				h = new Hashtable();
				h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs.getString("ID_KATEGORIPEMOHON"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
				h.put("noPendaftaran", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("pekerjaan", rs.getString("PEKERJAAN") == null ? "" : rs.getString("PEKERJAAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? "" : rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? "" : rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? "" : rs.getString("ALAMAT3_TETAP"));
				h.put("poskod", rs.getString("POSKOD_TETAP") == null ? "" : rs.getString("POSKOD_TETAP"));
				h.put("idNegeri", rs.getString("ID_NEGERITETAP") == null ? "" : rs.getString("ID_NEGERITETAP"));
				h.put("idBandar", rs.getString("ID_BANDARTETAP") == null ? "" : rs.getString("ID_BANDARTETAP"));
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("noFaks", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));

				beanMaklumatPemohon.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getidHakmilikAgensi(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_HAKMILIKAGENSI FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatTanah(String idHakmilikAgensi, String idHakmilikSementara) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
				+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
				+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
				+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
				+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
				+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
				+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
				
				+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
				+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI,"
				+ " TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
				+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
				+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			
			sql = sql + " UNION";
			
			sql = sql + " SELECT NULL AS ID_HAKMILIKAGENSI, NULL AS ID_HAKMILIK, HMS.ID_HAKMILIKSEMENTARA, HMS.PEGANGAN_HAKMILIK," +
					" HMS.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HMS.NO_HAKMILIK, HMS.ID_LOT," +
					" RUJLOT.KETERANGAN AS JENIS_LOT, HMS.NO_LOT, HMS.ID_LUAS AS ID_LUAS_BERSAMAAN, HMS.LUAS AS LUAS_BERSAMAAN, " +
					" RUJLUAS.KETERANGAN AS JENIS_LUAS, HMS.NO_WARTA, HMS.TARIKH_WARTA, HMS.ID_MUKIM, RUJMUKIM.NAMA_MUKIM," +
					" HMS.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HMS.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HMS.ID_KATEGORI AS ID_KATEGORI," +
					" RUJKATEGORI.KETERANGAN AS KATEGORI, HMS.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HMS.KEGUNAAN_TANAH," +
					" HMS.SYARAT, HMS.SEKATAN, HMS.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN" +
					" FROM TBLPHPHAKMILIKSEMENTARA HMS, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS," +
					" TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI," +
					" TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN" +
					" WHERE HMS.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HMS.ID_LOT = RUJLOT.ID_LOT(+)" +
					" AND HMS.ID_LUAS = RUJLUAS.ID_LUAS(+) AND HMS.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HMS.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)" +
					" AND HMS.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HMS.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HMS.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)" +
					" AND HMS.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)" +
					" AND HMS.ID_HAKMILIKSEMENTARA = '" + idHakmilikSementara + "'";
			
			System.out.println("cek pilih tanah = " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikSementara", rs.getString("ID_HAKMILIKSEMENTARA") == null ? "" : rs.getString("ID_HAKMILIKSEMENTARA"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());				
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("hakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase()) + " " + (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK")));				
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot", (rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase()) + " " + (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));				
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));						
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));				
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());				
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());		
				if (rs.getString("NO_HAKMILIK") != null && rs.getString("NO_WARTA") == null){
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null && rs.getString("NO_WARTA") != null){
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				
				beanMaklumatTanah.addElement(h);
			
			} else {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");				
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");				
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");				
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");						
				h.put("noWarta", "");
				h.put("tarikhWarta", "");				
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");				
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");				
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikUrusanByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT HMU.ID_HAKMILIKURUSAN"
				+ " FROM TBLHTPHAKMILIKURUSAN HMU, TBLHTPPERMOHONAN MOHON"
				+ " WHERE HMU.ID_PERMOHONAN = MOHON.ID_PERMOHONAN AND MOHON.ID_JENISTANAH = 3"
				+ " AND UPPER(HMU.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
							
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKURUSAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikAgensiByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikSementaraByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_HAKMILIKSEMENTARA FROM TBLPHPHAKMILIKSEMENTARA WHERE "
				+ " UPPER(PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKSEMENTARA");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String daftarBaru(String noFail, String idJenisTanah, String tarikhTerima, 
			String tarikhSurat, String noRujukanSurat, String perkara, String idKategoriPemohon, String nama, String namaPegawai, 
			String noPendaftaran, String pekerjaan, String alamat1, String alamat2, String alamat3, String poskod, String idBandar, String idNegeri, 
			String emel, String noTel, String noFaks, String idHakmilikAgensi, 
			String idPPTBorangK, String idHakmilikUrusan, String idPHPBorangK, 
			String txtTujuan, String idKementerianTanah, String idNegeriTanah, String idLuasTanah, String luasTanah,
			String idUrusan, String idSuburusan, String socFlagProsesFail, String catatan, String idHakmilikSementara,
			HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String idNegeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String userRole = (String) session.getAttribute("myrole");
		String sql = "";
		String idFailString = "";
		String idStatus = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if ("L".equals(socFlagProsesFail)){
				idStatus = "1610214"; //PERJANJIAN
			} else {
				idStatus = "1610198"; //MAKLUMAT PERMOHONAN					
			}
			
			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", idUrusan);
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", perkara);			
			r.add("NO_FAIL", noFail);
			r.add("NO_FAIL_ROOT", noFail);
			r.add("ID_LOKASIFAIL", "2"); //UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "3"); //DATA KUTIPAN ETAPP
			r.add("ID_NEGERI", idNegeriTanah);
			r.add("ID_KEMENTERIAN", idKementerianTanah);
			r.add("ID_NEGERI_BUKAFAIL", getIDDBNegeri());
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);
			r.add("NAMA", nama);
			r.add("NAMA_PEGAWAI", namaPegawai);
			r.add("NO_PENGENALAN", noPendaftaran);
			r.add("PEKERJAAN", pekerjaan);
			r.add("ALAMAT1_TETAP", alamat1);
			r.add("ALAMAT2_TETAP", alamat2);
			r.add("ALAMAT3_TETAP", alamat3);
			r.add("POSKOD_TETAP", poskod);
			r.add("ID_BANDARTETAP", idBandar);
			r.add("ID_NEGERITETAP", idNegeri);
			r.add("NO_TEL", noTel);
			r.add("NO_FAX", noFaks);
			r.add("EMEL", emel);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", idStatus);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("NO_RUJ_SURAT", noRujukanSurat);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_PERJANJIAN", "U");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_PPTBORANGK", idPPTBorangK);
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
			r.add("ID_PHPBORANGK", idPHPBorangK);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			if ("3".equals(idJenisTanah)){
				r.add("FLAG_BORANGK", "Y");
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLPHPHAKMILIK
			String peganganHakmilik = "";
			if ("3".equals(idJenisTanah)){
				setMaklumatBorangK(idPPTBorangK, idHakmilikUrusan, idPHPBorangK);
				if (getBeanMaklumatBorangK().size() != 0){
					Hashtable hashTanah = (Hashtable) getBeanMaklumatBorangK().get(0);
					
					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					if (hashTanah.get("peganganHakmilik").toString().trim().length() > 0){
						r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));
					} else {						
						peganganHakmilik = getKodNegeri((String)hashTanah.get("idNegeri")) + 
						getKodDaerah((String)hashTanah.get("idDaerah")) + getKodMukim((String)hashTanah.get("idMukim")) + 
						getKodJenisHakmilik((String)hashTanah.get("idJenisHakmilik")) + getNoHakmilik((String)hashTanah.get("noHakmilik"));
						r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
					}					
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
					r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
					r.add("ID_LOT", hashTanah.get("idLot"));
					r.add("NO_LOT", hashTanah.get("noLot"));
					r.add("ID_LUAS", hashTanah.get("idLuas"));
					r.add("LUAS", hashTanah.get("luasBersamaan"));			
					r.add("SYARAT", hashTanah.get("syarat"));
					r.add("SEKATAN", hashTanah.get("sekatan"));
					r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
					r.add("ID_KATEGORI", hashTanah.get("idKategori"));
					r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
					r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
					r.add("ID_AGENSI", hashTanah.get("idAgensi"));

					r.add("TARIKH_BORANGK", r.unquote("to_date('" + hashTanah.get("tarikhBorangK") + "','dd/MM/yyyy')"));
					r.add("CATATAN", hashTanah.get("catatan"));
					r.add("NO_PERSERAHAN", hashTanah.get("noPerserahan"));
					r.add("TARIKH_CATATAN", r.unquote("to_date('" + hashTanah.get("tarikhCatatan") + "','dd/MM/yyyy')"));
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + hashTanah.get("tarikhTerima") + "','dd/MM/yyyy')"));

					sql = r.getSQLInsert("TBLPHPHAKMILIK");
					stmt.executeUpdate(sql);
					
				}
			} else {
				setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
				if (getBeanMaklumatTanah().size() != 0){
					Hashtable hashTanah = (Hashtable) getBeanMaklumatTanah().get(0);
					
					r = new SQLRenderer();
					long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
					r.add("ID_HAKMILIK", idHakmilik);
					r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
					r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));					
					r.add("ID_NEGERI", hashTanah.get("idNegeri"));
					r.add("ID_DAERAH", hashTanah.get("idDaerah"));
					r.add("ID_MUKIM", hashTanah.get("idMukim"));
					r.add("NO_WARTA", hashTanah.get("noWarta"));
					r.add("TARIKH_WARTA", r.unquote("to_date('" + hashTanah.get("tarikhWarta") + "','dd/MM/yyyy')"));
					r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
					r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
					r.add("ID_LOT", hashTanah.get("idLot"));
					r.add("NO_LOT", hashTanah.get("noLot"));
					r.add("ID_LUAS", hashTanah.get("idLuas"));
					r.add("LUAS", hashTanah.get("luasBersamaan"));			
					r.add("SYARAT", hashTanah.get("syarat"));
					r.add("SEKATAN", hashTanah.get("sekatan"));
					r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
					r.add("ID_KATEGORI", hashTanah.get("idKategori"));
					r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
					r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
					r.add("ID_AGENSI", hashTanah.get("idAgensi"));

					sql = r.getSQLInsert("TBLPHPHAKMILIK");
					stmt.executeUpdate(sql);
					
				}
			}
			
			//TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			long idPHPPermohonanSewa = DB.getNextID("TBLPHPPERMOHONANSEWA_SEQ");
			r.add("ID_PHPPERMOHONANSEWA", idPHPPermohonanSewa);
			r.add("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_LUASASAL", idLuasTanah);
			r.add("LUAS_ASAL", luasTanah);
			
			if ("14".equals(idNegeriTanah)){
				r.add("FLAG_PERMOHONANDARI", "0");
			} else {
				r.add("FLAG_PERMOHONANDARI", "1");
			}
			if ("12".equals(idUrusan)){
				r.add("TUJUAN", "Mengeluarkan hasil " + getNamaSuburusan(idSuburusan));
			}else if("7".equals(idUrusan)){
				r.add("TUJUAN", txtTujuan);
			}else if("13".equals(idUrusan)){
				r.add("TUJUAN", "Mengeluarkan " + getNamaSuburusan(idSuburusan));
			}
			r.add("FLAG_PROSESFAIL", socFlagProsesFail);
			if ("L".equals(socFlagProsesFail)){
				r.add("KEPUTUSAN", "L");
			}
			r.add("CATATAN", catatan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);			
			
			//TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus(idSuburusan, idStatus)); //MAKLUMAT PERMOHONAN || PERJANJIAN
			r.add("AKTIF", "1");	
			r.add("ID_FAIL", idFail);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "P"); //TANAH DIPOHON
			r.add("ID_HAKMILIK", idhakmilikPermohonan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);
			
			//TBLPHPKERTASKERJAPENYEWAAN
			r = new SQLRenderer();
			long idKertasKerja = DB.getNextID("TBLPHPKERTASKERJAPENYEWAAN_SEQ");
			r.add("ID_KERTASKERJA", idKertasKerja);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_KERTAS", "1");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKERTASKERJAPENYEWAAN");
			stmt.executeUpdate(sql);
			
			if ("L".equals(socFlagProsesFail)){
				insertPerjanjian(idPermohonan, db, userId);
			}
			
			// INSERT ON TUGASAN
			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_PEGAWAI", userId);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("ROLE", userRole);
			r.add("FLAG_BUKA", "Y");

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
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
	    session.setAttribute("ID_FAIL", idFailString);
		return idFailString;
	}
	
	private void insertPerjanjian(long idPermohonan, Db db, String userId) throws Exception {

		String sql = "";		
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPERJANJIAN
			r = new SQLRenderer();
			long idPerjanjian = DB.getNextID("TBLPHPPERJANJIAN_SEQ");
			r.add("ID_PERJANJIAN", idPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_PERJANJIAN", "U");
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERJANJIAN");
			stmt.executeUpdate(sql);
			
			//TBLPHPMAKLUMBALAS
			r = new SQLRenderer();
			long idMaklumbalas = DB.getNextID("TBLPHPMAKLUMBALAS_SEQ");
			r.add("ID_MAKLUMBALAS", idMaklumbalas);
			r.add("ID_PERJANJIAN", idPerjanjian);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMAKLUMBALAS");
			stmt.executeUpdate(sql);
			
		} catch (SQLException ex) { 
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    } 
	}
	
	public String getIdSuburusanstatus(String idSuburusan, String idStatus) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '" + idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNamaSuburusan(String idSuburusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT LOWER(NAMA_SUBURUSAN) AS NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = '" + idSuburusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("NAMA_SUBURUSAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkingExistNoFail(String noFail) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SEKSYEN = '4' AND ID_URUSAN IN (7,12,13) AND FLAG_JENIS_FAIL != '9' AND UPPER(NO_FAIL) = UPPER('" + noFail + "')";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getFlagBorangK(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.FLAG_BORANGK FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("FLAG_BORANGK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPHPBorangK(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_BORANGK FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_BORANGK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatBorangK(String idPPTBorangK, String idHakmilikUrusan, String idPHPBorangK) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatBorangK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			//SENARAI BORANG K PPT
			sql = "SELECT TO_CHAR(PPTBK.ID_BORANGK) AS ID_PPTBORANGK, '' AS ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, '' AS PEGANGAN_HAKMILIK, PPTHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PPTHM.NO_HAKMILIK,"
				+ " CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1 WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN PPTHM.ID_LOT ELSE 1 END AS ID_LOT,"
				+ " (SELECT RUJLOT.KETERANGAN FROM TBLRUJLOT RUJLOT WHERE RUJLOT.ID_LOT = (CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN 1"
				+ " WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN  PPTHM.ID_LOT ELSE 1 END)) AS JENIS_LOT,"
				+ " CASE WHEN PPTHM.NO_LOT IS NOT NULL AND PPTHM.NO_PT IS NULL THEN PPTHM.NO_LOT WHEN PPTHM.NO_LOT IS NULL AND PPTHM.NO_PT IS NOT NULL THEN  PPTHM.NO_PT ELSE PPTHM.NO_LOT END AS NO_LOT,"
				+ " PPTHM.ID_UNITLUASAMBIL AS ID_LUAS_BERSAMAAN, PPTHM.LUAS_AMBIL AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
				+ " PPTHM.NO_WARTA_RIZAB AS NO_WARTA, PPTHM.TARIKH_WARTA_RIZAB AS TARIKH_WARTA,"
				+ " PPTHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PPTHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PPTHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
				+ " PPTHM.ID_KATEGORITANAH AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, 109 AS ID_SUBKATEGORI, 'TIADA MAKLUMAT' AS SUBKATEGORI, '' AS KEGUNAAN_TANAH,"
				+ " PPTHM.SYARAT_NYATA AS SYARAT, PPTHM.SEKATAN_KEPENTINGAN AS SEKATAN, PPTMOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
				+ " PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, PPTENBK.NO_PERSERAHAN, PPTENBK.TARIKH_CATATAN, PPTENBK.TARIKH_TERIMA"
				
				+ " FROM TBLPPTBORANGK PPTBK, TBLPPTHAKMILIKBORANGK PPTHMBK, TBLPPTHAKMILIK PPTHM, TBLPPTENDOSANBORANGK PPTENBK, TBLPPTPERMOHONAN PPTMOHON,"
				+ " TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI,"
				+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE PPTBK.ID_BORANGK = PPTHMBK.ID_BORANGK AND PPTHMBK.ID_HAKMILIK = PPTHM.ID_HAKMILIK AND PPTBK.ID_BORANGK = PPTENBK.ID_BORANGK"
				+ " AND PPTHM.ID_PERMOHONAN = PPTMOHON.ID_PERMOHONAN AND PPTHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PPTHM.ID_UNITLUASAMBIL = RUJLUAS.ID_LUAS(+)"
				+ " AND PPTHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PPTHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PPTHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
				+ " AND PPTHM.ID_KATEGORITANAH = RUJKATEGORI.ID_KATEGORI(+) AND PPTMOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND PPTHM.FLAG_ENDOSAN_BORANGK IS NOT NULL AND PPTBK.ID_BORANGK NOT IN (SELECT ID_BORANGK FROM TBLHTPINFOBORANGK)"				
				+ " AND PPTBK.ID_BORANGK = '" + idPPTBorangK + "'";
				
			sql = sql + " UNION";
				
			//SENARAI BORANG K HTP
			sql = sql + " SELECT '' AS ID_PPTBORANGK, TO_CHAR(URUSAN.ID_HAKMILIKURUSAN) AS ID_HAKMILIKURUSAN, '' AS ID_PHPBORANGK, URUSAN.PEGANGAN_HAKMILIK, URUSAN.ID_JENISHAKMILIK, JENISHAKMILIK.KOD_JENIS_HAKMILIK, URUSAN.NO_HAKMILIK, URUSAN.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, URUSAN.NO_LOT,"
				+ " URUSAN.ID_LUAS_BERSAMAAN, URUSAN.LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS, URUSAN.NO_WARTA, URUSAN.TARIKH_WARTA, TO_NUMBER(URUSAN.ID_MUKIM) AS ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
				+ " URUSAN.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, URUSAN.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, URUSAN.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, URUSAN.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, '' AS KEGUNAAN_TANAH,"
				+ " URUSAN.SYARAT, URUSAN.SEKATAN, MOHON.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
				+ " PPTBK.TARIKH_BORANGK, PPTBK.CATATAN, ENDOSAN.NO_PERSERAHAN, ENDOSAN.TARIKH_CATATAN, ENDOSAN.TARIKH_TERIMA"
					
				+ " FROM TBLHTPHAKMILIKURUSAN URUSAN, TBLHTPPERMOHONAN MOHON, TBLHTPINFOBORANGK INFO, TBLPPTBORANGK PPTBK, TBLPPTENDOSANBORANGK ENDOSAN,"
				+ " TBLRUJJENISHAKMILIK JENISHAKMILIK, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI,"
				+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
					
				+ " WHERE URUSAN.ID_PERMOHONAN = MOHON.ID_PERMOHONAN(+) AND URUSAN.ID_PERMOHONAN = INFO.ID_PERMOHONAN(+) AND INFO.ID_INFOBORANGK = PPTBK.ID_BORANGK(+)"
				+ " AND PPTBK.ID_BORANGK = ENDOSAN.ID_BORANGK(+) AND URUSAN.ID_JENISHAKMILIK = JENISHAKMILIK.ID_JENISHAKMILIK(+) AND URUSAN.ID_LOT = RUJLOT.ID_LOT"
				+ " AND URUSAN.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND URUSAN.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND URUSAN.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
				+ " AND URUSAN.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND URUSAN.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND URUSAN.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
				+ " AND MOHON.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				//+ " AND MOHON.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK)" 
				+ " AND MOHON.ID_JENISTANAH = 3"
				+ " AND URUSAN.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "'";
				
			sql = sql + " UNION";
				
			//SENARAI BORANG K PHP
			sql = sql + " SELECT '' AS ID_PPTBORANGK, '' AS ID_HAKMILIKURUSAN, TO_CHAR(PHPBK.ID_BORANGK) AS ID_PHPBORANGK, PHPHMBK.PEGANGAN_HAKMILIK, PHPHMBK.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHMBK.NO_HAKMILIK,"
				+ " PHPHMBK.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHMBK.NO_LOT, PHPHMBK.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHMBK.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
				+ " PHPHMBK.NO_WARTA, PHPHMBK.TARIKH_WARTA, PHPHMBK.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHMBK.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHMBK.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
				+ " PHPHMBK.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHMBK.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHMBK.KEGUNAAN_TANAH, PHPHMBK.SYARAT, PHPHMBK.SEKATAN,"
				+ " PHPHMBK.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
				+ " PHPBK.TARIKH_BORANGK, PHPBK.CATATAN, PHPBK.NO_PERSERAHAN, PHPBK.TARIKH_CATATAN, PHPBK.TARIKH_TERIMA"
					
				+ " FROM TBLPHPBORANGK PHPBK, TBLPHPHAKMILIKBORANGK PHPHMBK, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
				+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE PHPBK.ID_BORANGK = PHPHMBK.ID_BORANGK AND PHPHMBK.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHMBK.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND PHPHMBK.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHMBK.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHMBK.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHMBK.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
				+ " AND PHPHMBK.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHMBK.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHMBK.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND PHPBK.ID_HAKMILIKURUSAN IS NULL"
				+ " AND PHPBK.ID_BORANGK = '" + idPHPBorangK + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idPPTBorangK", rs.getString("ID_PPTBORANGK") == null ? "" : rs.getString("ID_PPTBORANGK").toUpperCase());
				h.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("idPHPBorangK", rs.getString("ID_PHPBORANGK") == null ? "" : rs.getString("ID_PHPBORANGK").toUpperCase());
				
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());				
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("hakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase()) + " " + (rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK")));				
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot", (rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase()) + " " + (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));				
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));						
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));				
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());				
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());				
				h.put("tarikhBorangK", rs.getDate("TARIKH_BORANGK") == null ? "" : sdf.format(rs.getDate("TARIKH_BORANGK")));	
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
				h.put("noPerserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan", rs.getDate("TARIKH_CATATAN") == null ? "" : sdf.format(rs.getDate("TARIKH_CATATAN")));	
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));	
				beanMaklumatBorangK.addElement(h);

			} else {
				
				h = new Hashtable();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");
				
				h.put("peganganHakmilik", "");				
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");				
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");				
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");						
				h.put("noWarta", "");
				h.put("tarikhWarta", "");				
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");				
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");				
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");				
				h.put("tarikhBorangK", "");	
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");	
				h.put("tarikhTerima", "");	
				beanMaklumatBorangK.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPHPBorangKByPeganganHakmilik(String peganganHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT BK.ID_BORANGK AS ID_PHPBORANGK"
				+ " FROM TBLPHPBORANGK BK, TBLPHPHAKMILIKBORANGK HMBK, TBLPHPHAKMILIK HM"
				+ " WHERE BK.ID_BORANGK = HMBK.ID_BORANGK AND HMBK.ID_HAKMILIK = HM.ID_HAKMILIK"
				+ " AND UPPER(HM.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_PHPBORANGK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHakmilikPermohonanByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN FROM TBLPERMOHONAN MOHON, TBLPHPHAKMILIKPERMOHONAN PHPHMP"
				+ " WHERE MOHON.ID_PERMOHONAN = PHPHMP.ID_PERMOHONAN AND MOHON.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIKPERMOHONAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatHakmilik(String idHakmilikPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatHakmilik = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
				+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
				+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
				+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
				+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
				+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
				+ " PHPHMP.FLAG_BORANGK"
				
				+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
				+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
				+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND PHPHMP.ID_HAKMILIKPERMOHONAN = '" + idHakmilikPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikPermohonan", rs.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());				
				h.put("idJenisHakmilik", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("hakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase()) + " " + (rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK")));				
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot", (rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase()) + " " + (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));				
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : rs.getString("LUAS_BERSAMAAN"));
				h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));						
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));				
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());				
				h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());				
				h.put("tarikhBorangK", rs.getDate("TARIKH_BORANGK") == null ? "" : sdf.format(rs.getDate("TARIKH_BORANGK")));	
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN").toUpperCase());
				h.put("noPerserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan", rs.getDate("TARIKH_CATATAN") == null ? "" : sdf.format(rs.getDate("TARIKH_CATATAN")));	
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? "" : rs.getString("FLAG_BORANGK").toUpperCase());
				beanMaklumatHakmilik.addElement(h);

			} else {
				
				h = new Hashtable();
				h.put("idPPTBorangK", "");
				h.put("idHakmilikUrusan", "");
				h.put("idPHPBorangK", "");
				
				h.put("peganganHakmilik", "");				
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");				
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");				
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");						
				h.put("noWarta", "");
				h.put("tarikhWarta", "");				
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");				
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");				
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");				
				h.put("tarikhBorangK", "");	
				h.put("catatan", "");
				h.put("noPerserahan", "");
				h.put("tarikhCatatan", "");	
				h.put("tarikhTerima", "");
				h.put("flagBorangK", "");	
				beanMaklumatHakmilik.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_MAMPU");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodDaerah(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '" + idDaerah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_DAERAH");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodMukim(String idMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM = '" + idMukim + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_MUKIM");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_JENIS_HAKMILIK");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoHakmilik(String noHakmilik){
		
		while (noHakmilik.length() < 8){
			noHakmilik = "0" + noHakmilik;
		}
		return noHakmilik;		
	}
	
	public String getIDDBNegeri() throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLRUJNEGERI.ID_NEGERI FROM TBLLOOKUPSTATE, TBLRUJNEGERI WHERE TBLLOOKUPSTATE.KOD_NEGERI = TBLRUJNEGERI.KOD_NEGERI";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_NEGERI");
			} else {
				return "";
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

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanMaklumatBorangK() {
		return beanMaklumatBorangK;
	}

	public void setBeanMaklumatBorangK(Vector beanMaklumatBorangK) {
		this.beanMaklumatBorangK = beanMaklumatBorangK;
	}

	public Vector getBeanMaklumatHakmilik() {
		return beanMaklumatHakmilik;
	}

	public void setBeanMaklumatHakmilik(Vector beanMaklumatHakmilik) {
		this.beanMaklumatHakmilik = beanMaklumatHakmilik;
	}
}