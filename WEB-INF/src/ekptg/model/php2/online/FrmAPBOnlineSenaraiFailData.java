package ekptg.model.php2.online;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.openjpa.lib.log.Log;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
//import ekptg.intergration.EkptgEmailSender;
import ekptg.model.php2.FrmAPBSenaraiFailData;

public class FrmAPBOnlineSenaraiFailData {
	
	static Logger myLog = Logger.getLogger(FrmAPBOnlineSenaraiFailData.class);
	private Vector senaraiFail = null;
	private Vector beanMaklumatPengarah = null;
	private Vector listPengarah = null;
	private Vector listPembeliPasir = null;
	private Vector beanMaklumatPembeliPasir = null;
	private Vector listProjek = null;
	private Vector listKoordinat = null;
	private Vector listPakar = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatProjek = null;
	private Vector beanMaklumatPakar = null;
	private Vector beanMaklumatKoordinat = null;
	private Vector beanMaklumatHeader = null;
	private Vector beanMaklumatKawasanMohon = null;
	private Vector beanMaklumatLampiran = null;
	private Vector listLampiran = null;
	private Vector<Hashtable<String,String>> beanMaklumatPejabat = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String daftarBaru(String idKaitanTujuan, String idJenistujuan, String idJenisLesen, String tujuanPengambilan, String tempoh,
			String pengalaman, String idNegeri, String lokasi,String luas, String idLuas, String idJenisPengenalanIndividu, 
			String idKategoriIndividu, String idKategoriPemohon, String idJantina, String idBangsa, String idBandar, 
			String idNegeriSykt, String idBandarSykt, String idJenisPermohonan, String idPermohonanLama, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String idUrusan = "9";
		String namaUser = "";
		String emelUser = "";	
		myLog.info("idJenisLesen : "+idJenisLesen);
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);

			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "9");
			r.add("ID_SUBURUSAN", "57");
			r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "4");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("ID_LOKASIFAIL", "2"); //UNIT PHP DI TINGKAT 2
			r.add("FLAG_JENIS_FAIL", "4"); //DATA BARU ETAPP
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			//TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", "2");
			sql = "SELECT A.USER_NAME,B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_NEGERI,B.NO_FAX, B.NO_HP," 
				+ " B.NO_KP_BARU, B.NO_TEL, B.EMEL, B.ID_BANDAR"
				+ " FROM USERS A, USERS_ONLINE B"
				+ " WHERE A.USER_ID = B.USER_ID AND A.USER_ID = '" + userId + "'";
			ResultSet rsUserOnline = stmt.executeQuery(sql);
			
			if (rsUserOnline.next()){
				if (rsUserOnline.getString("USER_NAME") != null){
					namaUser = rsUserOnline.getString("USER_NAME");
				}
				r.add("NAMA", namaUser);
				r.add("ALAMAT1_TETAP",rsUserOnline.getString("ALAMAT1") == null ? "" : rsUserOnline.getString("ALAMAT1"));
				r.add("ALAMAT2_TETAP", rsUserOnline.getString("ALAMAT2") == null ? "" : rsUserOnline.getString("ALAMAT2"));
				r.add("ALAMAT3_TETAP", rsUserOnline.getString("ALAMAT3") == null ? "" : rsUserOnline.getString("ALAMAT3"));
				r.add("POSKOD_TETAP", rsUserOnline.getString("POSKOD") == null ? "" : rsUserOnline.getString("POSKOD"));
				r.add("ID_NEGERITETAP", rsUserOnline.getString("ID_NEGERI") == null ? "99999" : rsUserOnline.getString("ID_NEGERI"));
				r.add("NO_FAX", rsUserOnline.getString("NO_FAX") == null ? "" : rsUserOnline.getString("NO_FAX"));
				r.add("NO_PENGENALAN", rsUserOnline.getString("NO_KP_BARU") == null ? "" : rsUserOnline.getString("NO_KP_BARU"));
				r.add("NO_TEL", rsUserOnline.getString("NO_TEL") == null ? "" : rsUserOnline.getString("NO_TEL"));
				
				if (rsUserOnline.getString("EMEL") != null){
					emelUser = rsUserOnline.getString("EMEL");
				}
				r.add("EMEL", emelUser);
				r.add("ID_BANDARTETAP",rsUserOnline.getString("ID_BANDAR") == null ? "99999" : rsUserOnline.getString("ID_BANDAR"));
			}		
			
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
			r.add("ID_STATUS", "");
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			
			Calendar currentDate = new GregorianCalendar();			
			String noPermohonan = "JKPTG/BPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/" + currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);
			r.add("NO_PERMOHONAN", noPermohonan);
			
			r.add("FLAG_AKTIF", 1);	
			r.add("NO_SUBJAKET", 0);
			r.add("NO_JILID", 0);
			r.add("NO_RAYUAN", 0);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			
			//TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			long id = DB.getNextID("TBLPHPPMOHONNJDUALPERTAMA_SEQ");
			r.add("ID_PHPPMOHONNJDUALPERTAMA", id);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_RAYUAN", 0);
			r.add("ID_JENISTUJUAN",idJenistujuan);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", "1");
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_JENISPERMOHONAN",idJenisPermohonan);
			r.add("ID_JENIS_LESEN",idJenisLesen);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
			myLog.info("sql -- "+sql);
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
	    session.setAttribute("idFail", idFailString);
		return idFailString;
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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getKodUrusanByIdUrusan(String idUrusan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = '" + idUrusan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("KOD_URUSAN");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPermohonanDaftar(String idFail) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.NO_PERMOHONAN");
			r.add("B.ID_KAITANTUJUAN");
			r.add("B.ID_JENISTUJUAN");
			r.add("B.ID_JENIS_LESEN");
			r.add("B.ID_JENISPERMOHONAN");
			r.add("B.TUJUAN_PENGAMBILAN");
			r.add("B.TEMPOH_DIPOHON");
			r.add("B.ID_TEMPOH");
			r.add("B.PENGALAMAN");
			r.add("B.FLAG_LUAR_PERAIRANNEGERI");
			r.add("B.LUAS_DIPOHON");
			r.add("B.ID_UNITLUAS");
			r.add("B.LOKASI_PERMOHONAN");
			r.add("B.ID_NEGERI_PERAIRAN");
			r.add("C.ID_PERMOHONAN",r.unquote("B.ID_PERMOHONAN"));
			r.add("A.ID_FAIL",r.unquote("C.ID_FAIL"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPHPPMOHONNJDUALPERTAMA B, TBLPERMOHONAN C");
			myLog.info("ID PERMOHONAN :" +sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("noFail",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idKaitanTujuan",rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs.getString("ID_KAITANTUJUAN"));
				h.put("idJenistujuan",rs.getString("ID_JENISTUJUAN") == null ? "99999" : rs.getString("ID_JENISTUJUAN"));
				h.put("idJenisPermohonan",rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs.getString("ID_JENISPERMOHONAN"));
				h.put("idJenisLesen",rs.getString("ID_JENIS_LESEN") == null ? "" : rs.getString("ID_JENIS_LESEN"));
				h.put("tujuanPengambilan", rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("tempoh",rs.getString("TEMPOH_DIPOHON") == null ? "" : rs.getString("TEMPOH_DIPOHON"));
				h.put("idTempoh",rs.getString("ID_TEMPOH") == null ? "99999" : rs.getString("ID_TEMPOH"));
				h.put("pengalaman",rs.getString("PENGALAMAN") == null ? "" : rs.getString("PENGALAMAN"));
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "99999" : rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("luas",rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON"));
				h.put("idLuas",rs.getString("ID_UNITLUAS") == null ? "99999" : rs.getString("ID_UNITLUAS"));
				h.put("idNegeri",rs.getString("ID_NEGERI_PERAIRAN") == null ? "99999" : rs.getString("ID_NEGERI_PERAIRAN"));
				h.put("lokasi",rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs.getString("LOKASI_PERMOHONAN"));				
				beanMaklumatPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
public void carianFail(String noFail,String noPermohonan, String tarikhPermohonan, String userId) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, F.TUJUAN_PENGAMBILAN, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN, B.NO_PERMOHONAN, C.ID_PEMOHON, "
				+ " CASE WHEN F.ID_JENISPERMOHONAN = '2' THEN 'PEMBAHARUAN LESEN' ELSE 'PERMOHONAN BARU' END AS JENISPERMOHONAN, F.ID_PERMOHONANLAMA "	
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E, TBLPHPPMOHONNJDUALPERTAMA F"
				+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.FLAG_JENIS_FAIL = '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+)"
				+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
				+ " AND B.ID_PERMOHONAN = E.ID_PERMOHONAN(+) AND A.ID_MASUK = '" + userId + "'";
			
			//noPermohonan
			if (noPermohonan != null) {
				if (!noPermohonan.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ noPermohonan.trim().toUpperCase() + "'|| '%'";
				}
			}
				
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			//tarikhPermohonan
			if (tarikhPermohonan != null) {
				if (!tarikhPermohonan.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhPermohonan)).toUpperCase() +"'";
				}
			}	
			
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			myLog.info("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idPermohonanLama", rs.getString("ID_PERMOHONANLAMA") == null ? "" : rs.getString("ID_PERMOHONANLAMA"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("jenispermohonan", rs.getString("JENISPERMOHONAN") == null ? "" : rs.getString("JENISPERMOHONAN").toUpperCase());
				h.put("tarikhPermohonan", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon",rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("tujuanPengambilan", rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
						"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
					h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
					h.put("status", " PENDAFTARAN");
				} else {
					h.put("status", " SEDANG DIPROSES");
				}
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

public void setMaklumatHeader(String idFail) throws Exception {
	Db db = null;
	String sql = "";

	try {
		beanMaklumatHeader = new Vector();
		db = new Db();
		Statement stmt = db.getStatement();
		int bil = 1;
		Hashtable h;

		sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, B.TARIKH_TERIMA, B.NO_PERMOHONAN, B.TUJUAN,B.FLAG_AKTIF, C.ID_PEMOHON, C.ID_KATEGORIPEMOHON, C.NAMA,B.NO_RAYUAN,"
			+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, F.FLAG_SAMBUNGAN, F.TUJUAN_PENGAMBILAN"
			+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F"
			+ " WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' " 
			+ " AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND "
			+ " C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS(+) AND A.ID_FAIL = '" + idFail + "'";
		myLog.info("sql setMaklumatHeader = "+sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) {
			h = new Hashtable();
			h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
			h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
			h.put("noRayuan", rs.getString("NO_RAYUAN") == null ? "0" : rs.getString("NO_RAYUAN"));
			h.put("urusan", "AKTA PELANTAR BENUA");
			h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
			h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
			h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON").toUpperCase());
			h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
			h.put("tujuanPengambilan", rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
			h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
			if ("1610197".equals(rs.getString("ID_STATUS")) || "1610212".equals(rs.getString("ID_STATUS"))|| 
					"1610208".equals(rs.getString("ID_STATUS")) || "1610207".equals(rs.getString("ID_STATUS"))){
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
			}else if ("".equals(rs.getString("ID_STATUS")) || rs.getString("ID_STATUS") == null){
				h.put("status", " PENDAFTARAN");
			} else {
				h.put("status", " SEDANG DIPROSES");
			}
			h.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF").toUpperCase());
			h.put("idKategoriPemohon", rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs.getString("ID_KATEGORIPEMOHON").toUpperCase());
			h.put("flagSambung", rs.getString("FLAG_SAMBUNGAN") == null ? "" : rs.getString("FLAG_SAMBUNGAN"));
			
			beanMaklumatHeader.addElement(h);
			bil++;
		}
		
		if (bil == 1){
			h = new Hashtable();
			h.put("idFail", "");
			h.put("noFail", "");
			h.put("urusan", "");
			h.put("noPermohonan","");
			h.put("idPermohonan", "");
			h.put("tarikhTerima", "");
			h.put("idStatus", "");
			h.put("status", "");
			h.put("flagAktif", "");
			h.put("idKategoriPemohon", "");
			h.put("flagSambung","");
			beanMaklumatHeader.addElement(h);
		}

	} finally {
		if (db != null)
			db.close();
		}
	}

public void setSenaraiProjek(String idPermohonan) throws Exception {
	Db db = null;
	String sql = "";

	try {
		listProjek = new Vector();
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.add("ID_PROJEKLESENAPB");
		r.add("NAMA_PROJEK");

		r.add("ID_PERMOHONAN", idPermohonan);

		sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB",
				"ID_PROJEKLESENAPB ASC");
		myLog.info("sql = "+sql);
		ResultSet rs = stmt.executeQuery(sql);

		Hashtable h;
		Integer bil = 1;

		while (rs.next()) {
			h = new Hashtable();

			h.put("bil", bil);
			h.put("idProjek", rs.getString("ID_PROJEKLESENAPB"));
			h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? ""
					: rs.getString("NAMA_PROJEK").toUpperCase());
			listProjek.addElement(h);
			bil++;
		}
	} finally {
		if (db != null)
			db.close();
	}
}

	public void setSenaraiKoordinat(String idPermohonan) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			listKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_KOORDINATPERMOHONAN");
			r.add("LABEL_TITIK");
			r.add("DARJAH_U");
			r.add("MINIT_U");
			r.add("SAAT_U");
			r.add("DARJAH_T");
			r.add("MINIT_T");
			r.add("SAAT_T");

			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPKOORDINATPERMOHONAN", "ID_KOORDINATPERMOHONAN ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idKoordinat", rs.getString("ID_KOORDINATPERMOHONAN"));
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? "" : rs.getString("LABEL_TITIK"));
				h.put("darjahU", rs.getString("DARJAH_U") == null ? "" : rs.getString("DARJAH_U"));
				h.put("minitU", rs.getString("MINIT_U") == null ? "" : rs.getString("MINIT_U"));
				h.put("saatU", rs.getString("SAAT_U") == null ? "" : rs.getString("SAAT_U"));
				h.put("darjahT", rs.getString("DARJAH_T") == null ? "" : rs.getString("DARJAH_T"));
				h.put("minitT", rs.getString("MINIT_T") == null ? "" : rs.getString("MINIT_T"));
				h.put("saatT", rs.getString("SAAT_T") == null ? "" : rs.getString("SAAT_T"));
				
				listKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiPakar(String idPermohonan) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			listPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MAKLUMATPAKAR");
			r.add("NAMA");
			r.add("KELAYAKAN");
			r.add("ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR", "ID_MAKLUMATPAKAR ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPakar", rs.getString("ID_MAKLUMATPAKAR"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("kelayakan", rs.getString("KELAYAKAN") == null ? "" : rs.getString("KELAYAKAN"));
				
				listPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPengarah(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT A.ID_PENGARAH, A.NAMA, ID_JENISPENGENALAN, A.NO_PENGENALAN, "
					+ " A.ID_WARGANEGARA, A.ID_BANGSA,B.KETERANGAN, A.PEGANGAN_SAHAM AS SAHAM, "
					+ " (SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon
					+ "') AS TOTAL, "
					+ " ROUND(A.PEGANGAN_SAHAM/(SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"
					+ idPemohon
					+ "')*100) AS PERCENTAGE "
					+ " FROM TBLPHPPENGARAH A, TBLRUJWARGANEGARA B "
					+ " WHERE A.ID_PEMOHON = '"
					+ idPemohon
					+ "' AND A.ID_WARGANEGARA = B.ID_WARGANEGARA ";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("idPengarah", rs.getString("ID_PENGARAH"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("idWarganegara",
						rs.getString("ID_WARGANEGARA") == null ? "99999" : rs
								.getString("ID_WARGANEGARA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("saham",
						rs.getString("PERCENTAGE") == null ? "" : rs
								.getString("PERCENTAGE"));
				h.put("warganegara", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());

				listPengarah.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPembeliPasir(String idPermohonan) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			listPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMBELIPASIR, NAMA FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil",bil);
				h.put("idPembeliPasir",rs.getString("ID_PEMBELIPASIR"));				
				h.put("nama",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				
				listPembeliPasir.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPembeliPasir(String idPembeliPasir) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatPembeliPasir = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PEMBELIPASIR");
			r.add("NAMA");
			r.add("ALAMAT1");
			r.add("ALAMAT2");
			r.add("ALAMAT3");
			r.add("POSKOD");			
			r.add("ID_NEGERI");	
			r.add("ID_BANDAR");
			r.add("NO_TEL_PEJABAT");
			r.add("NO_FAX");	
			r.add("FLAG_JENIS_PERJANJIAN");

			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLSelect("TBLPHPPEMBELIPASIR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPembeliPasir",rs.getString("ID_PEMBELIPASIR"));				
				h.put("nama",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("alamat2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("alamat3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("poskod",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("idNegeri",rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));		
				h.put("idBandar",rs.getString("ID_BANDAR") == null ? "99999" : rs.getString("ID_BANDAR"));			
				h.put("noTel", rs.getString("NO_TEL_PEJABAT") == null ? "" : rs.getString("NO_TEL_PEJABAT"));
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));				
				h.put("idJenisPerjanjian",rs.getString("FLAG_JENIS_PERJANJIAN") == null ? "99999" : rs.getString("FLAG_JENIS_PERJANJIAN"));			
				
				beanMaklumatPembeliPasir.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPengarah(String idPengarah) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_PENGARAH");
			r.add("NAMA");
			r.add("ID_JENISPENGENALAN");
			r.add("NO_PENGENALAN");
			r.add("ID_WARGANEGARA");
			r.add("ID_BANGSA");			
			r.add("PEGANGAN_SAHAM");

			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLSelect("TBLPHPPENGARAH");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPengarah",rs.getString("ID_PENGARAH"));				
				h.put("nama",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("idJenisPengenalan", rs.getString("ID_JENISPENGENALAN") == null ? "99999" : rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("idWarganegara",rs.getString("ID_WARGANEGARA") == null ? "99999" : rs.getString("ID_WARGANEGARA"));
				h.put("idBangsa",rs.getString("ID_BANGSA") == null ? "99999" : rs.getString("ID_BANGSA"));				
				h.put("saham", rs.getString("PEGANGAN_SAHAM") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("PEGANGAN_SAHAM"))));
				
				beanMaklumatPengarah.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	//yati tambah baru
	public String getNoFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_SUBURUSAN = '57'" 
					+ "AND ID_URUSAN = '9' AND ID_FAIL  = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql no. fail lama: "+sql);
			if (rs.next()) {
				return rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//yati tambah baru
		public String getNoFailPermohonan(String idFail)
				throws Exception {
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT NO_PERMOHONAN FROM TBLPERMOHONAN WHERE " 
						+ "ID_FAIL  = '"
						+ idFail + "'";

				ResultSet rs = stmt.executeQuery(sql);
				myLog.info("sql no. permohonan lama: "+sql);
				if (rs.next()) {
					return rs.getString("NO_PERMOHONAN") == null ? "" : rs
							.getString("NO_PERMOHONAN").toString();

				} else {
					return "";
				}

			} finally {
				if (db != null)
					db.close();
			}
		}
	
	public String getIdFailByNoFail(String noFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_SUBURUSAN = '57'" 
					+ "AND ID_URUSAN = '9' AND UPPER(NO_FAIL) = '"
					+ noFail.trim().toUpperCase() + "'";

			ResultSet rs = stmt.executeQuery(sql);
			//myLog.info("sql id. fail lama: "+sql);
			if (rs.next()) {
				return rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//yati tambah
	public String getIdPermohonanByNoFail(String idFail)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE " 
						+ "ID_FAIL  = '"
						+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			//myLog.info("sql id. fail lama: "+sql);
			if (rs.next()) {
				return rs.getString("ID_PERMOHONAN") == null ? "" : rs
						.getString("ID_PERMOHONAN").toString();

			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatProjek(String idProjek) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatProjek = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA_PROJEK");

			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK").toUpperCase());

				beanMaklumatProjek.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void removeProjek(String idProjek, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLDelete("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idProjek + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public void setMaklumatPakar(String idPakar) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatPakar = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NAMA");
			r.add("KELAYAKAN");

			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLSelect("TBLPHPMAKLUMATPAKAR");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("kelayakan", rs.getString("KELAYAKAN") == null ? "" : rs.getString("KELAYAKAN").toUpperCase());
				
				beanMaklumatPakar.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKoordinat(String idKoordinat) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatKoordinat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("LABEL_TITIK");
			r.add("DARJAH_U");
			r.add("MINIT_U");
			r.add("SAAT_U");
			r.add("DARJAH_T");
			r.add("MINIT_T");
			r.add("SAAT_T");

			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLSelect("TBLPHPKOORDINATPERMOHONAN");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("labelTitik", rs.getString("LABEL_TITIK") == null ? "" : rs.getString("LABEL_TITIK"));
				h.put("darjahU", rs.getString("DARJAH_U") == null ? "" : rs.getString("DARJAH_U"));
				h.put("minitU", rs.getString("MINIT_U") == null ? "" : rs.getString("MINIT_U"));
				h.put("saatU", rs.getString("SAAT_U") == null ? "" : rs.getString("SAAT_U"));
				h.put("darjahT", rs.getString("DARJAH_T") == null ? "" : rs.getString("DARJAH_T"));
				h.put("minitT", rs.getString("MINIT_T") == null ? "" : rs.getString("MINIT_T"));
				h.put("saatT", rs.getString("SAAT_T") == null ? "" : rs.getString("SAAT_T"));
				
				beanMaklumatKoordinat.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String savePengarah(String idPemohon, String idWarganegara, String nama, String idJenisPengenalan, String idBangsa, String noPengenalan, String saham, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPengarahString = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPENGARAH
			long idPengarah = DB.getNextID("TBLPHPPENGARAH_SEQ");
			idPengarahString = String.valueOf(idPengarah);
			r.add("ID_PENGARAH", idPengarah);
			r.add("ID_PEMOHON", idPemohon);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("ID_BANGSA", idBangsa);
			r.add("PEGANGAN_SAHAM", saham);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENGARAH");
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
		return idPengarahString;
	}
	
	public void updatePengarah(String idPengarah, String idWarganegara, String nama, String idJenisPengenalan, String idBangsa, String noPengenalan, String saham, HttpSession session) throws Exception {
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
			
			//TBLPHPPENGARAH
			r.update("ID_PENGARAH", idPengarah);
			r.add("NAMA", nama);
			r.add("ID_JENISPENGENALAN", idJenisPengenalan);
			r.add("NO_PENGENALAN", noPengenalan);
			r.add("ID_WARGANEGARA", idWarganegara);
			r.add("ID_BANGSA", idBangsa);
			r.add("PEGANGAN_SAHAM", Utils.RemoveSymbol((String)saham));
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENGARAH");
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
	
	public void removePengarah(String idPengarah) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPENGARAH
			r.add("ID_PENGARAH", idPengarah);

			sql = r.getSQLDelete("TBLPHPPENGARAH");
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
	
	public boolean checkPercentagePengarah(String idPemohon, String saham) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0){
			total = total + Double.valueOf(saham);
		}
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '" + idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}

			if (total > 100){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPercentagePengarahForUpdate(String idPemohon, String idPengarah, String saham) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;
		if (saham != null && saham.trim().length() != 0){
			total = total + Double.valueOf(saham);
		}
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT PEGANGAN_SAHAM FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '" + idPemohon + "' AND ID_PENGARAH != '" + idPengarah + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				total = total + rs.getDouble("PEGANGAN_SAHAM");
			}
			
			if (total > 100){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String savePembeliPasir(String idPermohonan, String nama, String alamat1, String alamat2, String alamat3, 
			String poskod, String idNegeri, String idBandar, String noTel, String noFax, String idJenisPerjanjian, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPembeliPasirString = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPEMBELIPASIR
			long idPembeliPasir = DB.getNextID("TBLPHPPEMBELIPASIR_SEQ");
			idPembeliPasirString = String.valueOf(idPembeliPasir);
			r.add("ID_PEMBELIPASIR", idPembeliPasir);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMBELIPASIR");
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
		return idPembeliPasirString;
	}
	
	public void updatePembeliPasir(String idPembeliPasir, String nama, String alamat1, String alamat2, String alamat3, 
			String poskod, String idNegeri, String idBandar, String noTel, String noFax, String idJenisPerjanjian, HttpSession session) throws Exception {
		
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
			
			//TBLPHPPEMBELIPASIR
			r.update("ID_PEMBELIPASIR", idPembeliPasir);

			r.add("NAMA", nama);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_BANDAR", idBandar);
			r.add("NO_TEL_PEJABAT", noTel);
			r.add("NO_FAX", noFax);
			r.add("FLAG_JENIS_PERJANJIAN", idJenisPerjanjian);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMBELIPASIR");
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
	
	public void removePembeliPasir(String idPembeliPasir) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPEMBELIPASIR
			r.add("ID_PEMBELIPASIR", idPembeliPasir);

			sql = r.getSQLDelete("TBLPHPPEMBELIPASIR");
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

	public String saveProjek(String idPermohonan, String nama,HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idProjekString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPROJEKLESENAPB
			long idProjek = DB.getNextID("TBLPHPPROJEKLESENAPB_SEQ");
			idProjekString = String.valueOf(idProjek);
			r.add("ID_PROJEKLESENAPB", idProjek);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA_PROJEK", nama);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPROJEKLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
		return idProjekString;
	}
	
	public void updateProjek(String idProjek, String nama, HttpSession session) throws Exception {
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
			
			//TBLPHPPROJEKLESENAPB
			r.update("ID_PROJEKLESENAPB", idProjek);
			r.add("NAMA_PROJEK", nama);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPROJEKLESENAPB");
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
	
	public void removeProjek(String idProjek) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPPROJEKLESENAPB
			r.add("ID_PROJEKLESENAPB", idProjek);

			sql = r.getSQLDelete("TBLPHPPROJEKLESENAPB");
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
	
	public String savePakar(String idPermohonan, String nama, String kelayakan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPakarString = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPMAKLUMATPAKAR
			long idPakar = DB.getNextID("TBLPHPMAKLUMATPAKAR_SEQ");
			idPakarString = String.valueOf(idPakar);
			r.add("ID_MAKLUMATPAKAR", idPakar);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPMAKLUMATPAKAR");
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
		return idPakarString;
	}
	
	public void updatePakar(String idPakar, String nama, String kelayakan, HttpSession session) throws Exception {
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
			
			//TBLPHPMAKLUMATPAKAR
			r.update("ID_MAKLUMATPAKAR", idPakar);
			r.add("NAMA", nama);
			r.add("KELAYAKAN", kelayakan);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPMAKLUMATPAKAR");
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
	
	public void removePakar(String idPakar) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPMAKLUMATPAKAR
			r.add("ID_MAKLUMATPAKAR", idPakar);

			sql = r.getSQLDelete("TBLPHPMAKLUMATPAKAR");
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
	
	public String saveKoordinat(String idPermohonan, String labelTitik, String darjahU, String minitU, String saatU, 
			String darjahT, String minitT, String saatT, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idKoordinatString = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPKOORDINATPERMOHONAN
			long idKoordinat = DB.getNextID("TBLPHPKOORDINATPERMOHONAN_SEQ");
			idKoordinatString = String.valueOf(idKoordinat);
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPKOORDINATPERMOHONAN");
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
		return idKoordinatString;
	}
	
	public void updateKoordinat(String idKoordinat, String labelTitik, String darjahU, String minitU, String saatU, 
			String darjahT, String minitT, String saatT, HttpSession session) throws Exception {
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
			
			//TBLPHPKOORDINATPERMOHONAN
			r.update("ID_KOORDINATPERMOHONAN", idKoordinat);
			r.add("LABEL_TITIK", labelTitik);
			r.add("DARJAH_U", darjahU);
			r.add("MINIT_U", minitU);
			r.add("SAAT_U", saatU);
			r.add("DARJAH_T", darjahT);
			r.add("MINIT_T", minitT);
			r.add("SAAT_T", saatT);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKOORDINATPERMOHONAN");
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
	
	public void removeKoordinat(String idKoordinat) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPHPKOORDINATPERMOHONAN
			r.add("ID_KOORDINATPERMOHONAN", idKoordinat);

			sql = r.getSQLDelete("TBLPHPKOORDINATPERMOHONAN");
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

	public void setMaklumatPermohonan(String idPermohonan) throws Exception {
		Db db = null;		
		String sql = "";

		try {
			beanMaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.ID_KAITANTUJUAN");
			r.add("B.ID_JENISTUJUAN");
			r.add("B.ID_JENIS_LESEN");
			r.add("B.ID_JENISPERMOHONAN");
			r.add("B.TUJUAN_PENGAMBILAN");
			r.add("B.TEMPOH_DIPOHON");
			r.add("B.ID_TEMPOH");
			r.add("B.PENGALAMAN");
			r.add("B.FLAG_LUAR_PERAIRANNEGERI");
			r.add("B.LUAS_DIPOHON");
			r.add("B.ID_UNITLUAS");
			r.add("B.LOKASI_PERMOHONAN");
			r.add("B.ID_NEGERI_PERAIRAN");
			r.add("C.TUJUAN");
			r.add("C.TARIKH_SURAT");
			r.add("C.TARIKH_TERIMA");
			r.add("D.ID_PEMOHON");
			r.add("D.MODAL_DIBENARKAN");
			r.add("D.MODAL_JELAS");
			r.add("C.ID_PERMOHONAN",r.unquote("B.ID_PERMOHONAN"));
			r.add("A.ID_FAIL", r.unquote("C.ID_FAIL"));
			r.add("C.ID_PEMOHON", r.unquote("D.ID_PEMOHON"));
			r.add("B.ID_PERMOHONAN", idPermohonan);

			sql = r.getSQLSelect("TBLPFDFAIL A,TBLPHPPMOHONNJDUALPERTAMA B, TBLPERMOHONAN C,TBLPHPPEMOHON D ");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idKaitanTujuan",rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs.getString("ID_KAITANTUJUAN"));
				h.put("idJenistujuan",rs.getString("ID_JENISTUJUAN") == null ? "99999" : rs.getString("ID_JENISTUJUAN"));
				h.put("idJenisPermohonan",rs.getString("ID_JENISPERMOHONAN") == null ? "" : rs.getString("ID_JENISPERMOHONAN"));
				h.put("idJenisLesen",rs.getString("ID_JENIS_LESEN") == null ? "" : rs.getString("ID_JENIS_LESEN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("perkara", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("tujuanPengambilan", rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs.getString("TUJUAN_PENGAMBILAN"));
				h.put("tempoh",rs.getString("TEMPOH_DIPOHON") == null ? "" : rs.getString("TEMPOH_DIPOHON"));
				h.put("idTempoh",rs.getString("ID_TEMPOH") == null ? "99999" : rs.getString("ID_TEMPOH"));
				h.put("pengalaman",rs.getString("PENGALAMAN") == null ? "" : rs.getString("PENGALAMAN"));
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "99999" : rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
				h.put("luas",rs.getString("LUAS_DIPOHON") == null ? "" : rs.getString("LUAS_DIPOHON"));
				h.put("idLuas",rs.getString("ID_UNITLUAS") == null ? "99999" : rs.getString("ID_UNITLUAS"));
				h.put("idNegeri",rs.getString("ID_NEGERI_PERAIRAN") == null ? "99999" : rs.getString("ID_NEGERI_PERAIRAN"));
				h.put("idPemohon",rs.getString("ID_PEMOHON"));
				h.put("lokasi",rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs.getString("LOKASI_PERMOHONAN"));				
				h.put("modalBenar", rs.getString("MODAL_DIBENARKAN") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("MODAL_DIBENARKAN"))));
				h.put("modalJelas", rs.getString("MODAL_JELAS") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("MODAL_JELAS"))));
				beanMaklumatPermohonan.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setBeanMaklumatKawasanMohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKawasanMohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, C.ID_NEGERI_PERAIRAN, C.FLAG_LUAR_PERAIRANNEGERI, C.ID_JENIS_LESEN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPMOHONNJDUALPERTAMA C, TBLRUJNEGERI D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_NEGERI_PERAIRAN = D.ID_NEGERI"
					+ " AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idFlagLuar", rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? "" : rs
						.getString("FLAG_LUAR_PERAIRANNEGERI").toUpperCase());
				h.put("idNegeriPerairan", rs.getString("ID_NEGERI_PERAIRAN") == null ? "" : rs
						.getString("ID_NEGERI_PERAIRAN").toUpperCase());
				h.put("idJenisLesen", rs.getString("ID_JENIS_LESEN") == null ? "" : rs
						.getString("ID_JENIS_LESEN").toUpperCase());
				beanMaklumatKawasanMohon.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePermohonan(String idFail,String idPermohonan,String idPemohon,String idKaitanTujuan, String tujuanPengambilan,
			String tempoh, String pengalaman,String idNegeri, String lokasi,
			String luas, String idLuas,String modalBenar, String modalJelas,String idJenistujuan,String idJenisPermohonan, String idJenisLesen, HttpSession session) throws Exception {


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
						
			//TBLPHPPEMOHON
			r.update("ID_PEMOHON", idPemohon);	
			r.add("MODAL_DIBENARKAN", Utils.RemoveSymbol(modalBenar));
			r.add("MODAL_JELAS", Utils.RemoveSymbol(modalJelas));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);
						
			//TBLPHPPMOHONNJDUALPERTAMA
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_JENISTUJUAN",idJenistujuan);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("ID_JENISPERMOHONAN",idJenisPermohonan);
			r.add("ID_JENIS_LESEN",idJenisLesen);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", "1");
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPMOHONNJDUALPERTAMA");
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
	
	public void updatePermohonanEmel(String idFail,String idPermohonan,HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String namaUser = "";
		String emelUser = "";
		String idhakmilikPermohonan = "";
		String noPermohonan = "";
		String idSuburusan = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			sql = "SELECT B.ID_HAKMILIKPERMOHONAN, A.NO_PERMOHONAN, C.ID_SUBURUSAN " 
				+ " FROM TBLPERMOHONAN A,TBLPHPHAKMILIKPERMOHONAN B, TBLPFDFAIL C WHERE "
				+ " C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			
			ResultSet rsPermohonan = stmt.executeQuery(sql);
			if (rsPermohonan.next()){
				idhakmilikPermohonan = rsPermohonan.getString("ID_HAKMILIKPERMOHONAN");
				noPermohonan = rsPermohonan.getString("NO_PERMOHONAN");
				idSuburusan = rsPermohonan.getString("ID_SUBURUSAN");
			}	
			
			//TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610197");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610197")); //MAKLUMAT PERMOHONAN
			r.add("AKTIF", "1");	
			r.add("ID_FAIL", idFail);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			conn.commit();
			

//			if (!"".equals(namaUser) && !"".equals(emelUser)){
//				EkptgEmailSender email = EkptgEmailSender.getInstance();
//				email.FROM = "etapp_webmaster@kptg.gov.my";
//				email.RECIEPIENT = emelUser;				
//				email.SUBJECT = "PERMOHONAN LESEN AKTA PELANTAR BENUA #" + noPermohonan;
//				email.MESSAGE = namaUser.toUpperCase() + "."
//								+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
//								+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan."
//								+ "<br><br>Terima Kasih.";
//				email.sendEmail();
//			}
			
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
	
	public boolean checkMaklumatAPBLengkap(String idPermohonan) throws Exception{
		Db db = null;
		String sql = "";
		boolean bool = true;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT FLAG_LUAR_PERAIRANNEGERI FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				
				if("1".equals(rs.getString("FLAG_LUAR_PERAIRANNEGERI")) || "2".equals(rs.getString("FLAG_LUAR_PERAIRANNEGERI"))){
					
					sql = "SELECT ID_PERMOHONAN FROM TBLPHPPMOHONNJDUALPERTAMA WHERE "
						+ "(ID_NEGERI_PERAIRAN IS NULL OR LOKASI_PERMOHONAN IS NULL OR LUAS_DIPOHON IS NULL OR ID_UNITLUAS IS NULL)"
						+ " AND ID_PERMOHONAN= '" +idPermohonan+ "'";
					
					ResultSet rs2 = stmt.executeQuery(sql);
					if (rs2.next()){
						bool = false;
					} else {
						bool = true;
					}
						
				} else{
					bool = true;
				}
				
			} else {
				bool = true;
			}
		
		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
	
	public void hapusPermohonan(String idFail) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLPHPPMOHONNJDUALPERTAMA
			sql = "DELETE FROM TBLPHPPMOHONNJDUALPERTAMA WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN  WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPPENGARAH 
			sql = "DELETE FROM TBLPHPPENGARAH  WHERE ID_PEMOHON IN "
				+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			stmt.executeUpdate(sql);
			
			//TBLPHPPEMBELIPASIR
			sql = "DELETE FROM TBLPHPPEMBELIPASIR WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPPROJEKLESENAPB
			sql = "DELETE FROM TBLPHPPROJEKLESENAPB WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPMAKLUMATPAKAR
			sql = "DELETE FROM TBLPHPMAKLUMATPAKAR WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPKOORDINATPERMOHONAN
			sql = "DELETE FROM TBLPHPKOORDINATPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPBYRNSYRTKLLSNLESENAPB
			sql = "DELETE FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE ID_PERMOHONAN IN"
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPHPPEMOHON 
			sql = "DELETE FROM TBLPHPPEMOHON WHERE ID_PEMOHON IN "
				+ "(SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + ")))";
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			sql = "DELETE FROM TBLPERMOHONAN WHERE ID_PERMOHONAN IN "
				+ "(SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL IN (" + idFail + "))";
			stmt.executeUpdate(sql);
			
			//TBLPFDFAIL
			sql = "DELETE FROM TBLPFDFAIL WHERE ID_FAIL IN (" + idFail + ")";
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

	public void setMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PEMOHON");
			r.add("C.FLAG_INDIVIDU");
			r.add("C.ID_KATEGORIPEMOHON");
			r.add("C.NAMA");
			r.add("C.ID_JENISPENGENALAN");
			r.add("C.NO_PENGENALAN");
			r.add("C.PEKERJAAN");
			r.add("C.ID_JANTINA");
			r.add("C.ID_BANGSA");
			r.add("C.ALAMAT1_TETAP");
			r.add("C.ALAMAT2_TETAP");
			r.add("C.ALAMAT3_TETAP");
			r.add("C.POSKOD_TETAP");
			r.add("C.ID_NEGERITETAP");
			r.add("C.ID_BANDARTETAP");
			r.add("C.NO_TEL_RUMAH");
			r.add("C.NO_TEL_PEJABAT");
			r.add("C.NO_TEL_BIMBIT");
			r.add("C.NO_FAX");
			r.add("C.EMEL");

			r.add("A.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("C.ID_PEMOHON"));
			r.add("A.ID_FAIL", idFail);

			sql = r.getSQLSelect("TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C");
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("getBeanMaklumatPemohon >> " + sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriIndividu",
						rs.getString("FLAG_INDIVIDU") == null ? "" : rs
								.getString("FLAG_INDIVIDU"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("idJantina", rs.getString("ID_JANTINA") == null ? "99999"
						: rs.getString("ID_JANTINA"));
				h.put("idBangsa", rs.getString("ID_BANGSA") == null ? "99999"
						: rs.getString("ID_BANGSA"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandar",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTel",
						rs.getString("NO_TEL_RUMAH") == null ? "" : rs
								.getString("NO_TEL_RUMAH"));
				h.put("noTelBim", rs.getString("NO_TEL_BIMBIT") == null ? ""
						: rs.getString("NO_TEL_BIMBIT"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("namaSykt",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPengenalanSykt",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaanSykt", rs.getString("PEKERJAAN") == null ? ""
						: rs.getString("PEKERJAAN"));
				h.put("alamat1Sykt", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2Sykt", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3Sykt", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskodSykt", rs.getString("POSKOD_TETAP") == null ? ""
						: rs.getString("POSKOD_TETAP"));
				h.put("idNegeriSykt",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandarSykt",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("noTelSykt", rs.getString("NO_TEL_PEJABAT") == null ? ""
						: rs.getString("NO_TEL_PEJABAT"));
				h.put("noFaxSykt",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("emelSykt",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));

				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//BARU TAMBAH 21062020
	//SENARAI SEMAK
	public Vector getSenaraiSemak(String idPermohonan, String kategori) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();
			
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_RUJSENARAISEMAK, A.KETERANGAN,"
					+ " CASE WHEN A.ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG, "
					+ " CASE WHEN B.KETERANGAN = 'INDIVIDU' THEN '1' ELSE '2' END AS ID_KATEGORIPEMOHON "
					+ " FROM TBLPHPRUJSENARAISEMAK A, TBLRUJKATEGORIPEMOHON B "
					+ " WHERE A.ID_KATEGORIPEMOHON = B.ID_KATEGORIPEMOHON AND A.FLAG_AKTIF = 'Y' AND B.KETERANGAN = '" + kategori + "' ";
				
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return senaraiSemak;
	}
	
	public static void semakanTambah(String idsemakan, String idpermohonan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	      String idPermohonan = idpermohonan;
	      String idSemakan = idsemakan;
	      int idKementerian = 1;
	      int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_semakanhantar", idSemakanhantar);
	      r.add("id_permohonan", idPermohonan);
	      r.add("id_semakansenarai", idSemakan);
	      sql = r.getSQLInsert("tblsemakanhantar");
	      myLog.info("semakanTambah : "+sql);
	      stmt.executeUpdate(sql);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	//UPDATE SENARAI SEMAK
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {
			
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";		
			
		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();
				
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);
				
			for (int i = 0; i < semaks.length; i++) {
				r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();
				
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//BARU TAMBAH 21062020
	//LAMPIRAN
	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran,
		String txtCatatan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaLampiran);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			myLog.info("sql : " +sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
				
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public void hapusLampiran(String idDokumen, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			myLog.info("sql : " +sql);
			stmt.executeUpdate(sql);

			conn.commit();
				
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idDokumen + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, JENIS_MIME FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			myLog.info("sql : " +sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaLampiran", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("jenisMime",
						rs.getString("JENIS_MIME") == null ? "" : StringUtils.substringBefore(rs.getString("JENIS_MIME"), "/"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public void setSenaraiLampiran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND FLAG_DOKUMEN = 'L'"
					+ " AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL AND ID_PENAWARANKJP IS NULL" 
					+ " ORDER BY ID_DOKUMEN DESC";

			myLog.info("sql : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listLampiran.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	public void updateSenaraiSemak(String idPermohonanSewa, String txtTujuan, String socTempohSewa, String idLuasKegunaan, 
		String idLuas, String txtLuasMohon1, String txtLuasMohon2,String txtLuasMohon3, String txtLuasBersamaan, 
		String txtBakiLuas, HttpSession session) throws Exception {
			
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
		    conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			//TBLPHPPERMOHONANSEWA
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("TUJUAN", txtTujuan);
			r.add("FLAG_GUNA",idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);			
			r.add("ID_LUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_LUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);
			r.add("FLAG_TEMPOHSEWA", socTempohSewa);
				
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			myLog.info("sql : "+sql);
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
	
	public Vector<Hashtable<String,String>> setMaklumatPejabatJKPTG() throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.NAMA_PEJABAT, B.NO_TEL, B.NO_FAX, B.EMEL, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, C.NAMA_NEGERI, B.KOD_JKPTG, D.NAMA_DAERAH, E.KETERANGAN AS NAMA_BANDAR, "
				+ "B.ID_PEJABATJKPTG FROM TBLPERMOHONAN A, TBLRUJPEJABATJKPTG B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJBANDAR E WHERE A.ID_JKPTG = B.ID_PEJABATJKPTG "
				+ "AND B.ID_NEGERI = C.ID_NEGERI(+) AND B.KOD_JKPTG = '16' AND B.ID_DAERAH = D.ID_DAERAH(+) "
				+ " AND B.ID_BANDAR = E.ID_BANDAR(+)";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("sql 2 = "+sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				h.put("emel", rs.getString("EMEL") == null ? "" : rs
						.getString("EMEL"));
				beanMaklumatPejabat.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatPejabat;
	}
	
//	public Vector getBeanMaklumatPejabat() {
//		return beanMaklumatPejabat;
//	}

//	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
//		this.beanMaklumatPejabat = beanMaklumatPejabat;
//	}
		
	public Vector getSenaraiFail() {
		return senaraiFail;
	}
	
	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatHeader() {
		return beanMaklumatHeader;
	}
	
	public Vector getBeanMaklumatPengarah() {
		return beanMaklumatPengarah;
	}

	public void setBeanMaklumatPengarah(Vector beanMaklumatPengarah) {
		this.beanMaklumatPengarah = beanMaklumatPengarah;
	}

	public Vector getListPengarah() {
		return listPengarah;
	}

	public void setListPengarah(Vector listPengarah) {
		this.listPengarah = listPengarah;
	}

	public Vector getListPembeliPasir() {
		return listPembeliPasir;
	}

	public void setListPembeliPasir(Vector listPembeliPasir) {
		this.listPembeliPasir = listPembeliPasir;
	}

	public Vector getBeanMaklumatPembeliPasir() {
		return beanMaklumatPembeliPasir;
	}

	public void setBeanMaklumatPembeliPasir(Vector beanMaklumatPembeliPasir) {
		this.beanMaklumatPembeliPasir = beanMaklumatPembeliPasir;
	}

	public Vector getListProjek() {
		return listProjek;
	}

	public void setListProjek(Vector listProjek) {
		this.listProjek = listProjek;
	}

	public Vector getListKoordinat() {
		return listKoordinat;
	}

	public void setListKoordinat(Vector listKoordinat) {
		this.listKoordinat = listKoordinat;
	}

	public Vector getListPakar() {
		return listPakar;
	}

	public void setListPakar(Vector listPakar) {
		this.listPakar = listPakar;
	}

	public Vector getBeanMaklumatProjek() {
		return beanMaklumatProjek;
	}
	
	public void setBeanMaklumatProjek(Vector beanMaklumatProjek) {
		this.beanMaklumatProjek = beanMaklumatProjek;
	}

	public Vector getBeanMaklumatPakar() {
		return beanMaklumatPakar;
	}

	public void setBeanMaklumatPakar(Vector beanMaklumatPakar) {
		this.beanMaklumatPakar = beanMaklumatPakar;
	}

	public Vector getBeanMaklumatKoordinat() {
		return beanMaklumatKoordinat;
	}

	public void setBeanMaklumatKoordinat(Vector beanMaklumatKoordinat) {
		this.beanMaklumatKoordinat = beanMaklumatKoordinat;
	}
	public Vector getBeanMaklumatKawasanMohon() {
		return beanMaklumatKawasanMohon;
	}
	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}
	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}
	public Vector getListLampiran() {
		return listLampiran;
	}
	public void setListLampiran(Vector listLampiran) {
		this.listLampiran = listLampiran;
	}
	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}

}
