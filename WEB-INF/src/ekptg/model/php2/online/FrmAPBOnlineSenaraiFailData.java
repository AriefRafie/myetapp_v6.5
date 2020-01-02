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

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
import ekptg.intergration.XEkptgEmailSender;

public class FrmAPBOnlineSenaraiFailData {
	
	private Vector senaraiFail = null;
	private Vector beanMaklumatPengarah = null;
	private Vector listPengarah = null;
	private Vector listPembeliPasir = null;
	private Vector beanMaklumatPembeliPasir = null;
	private Vector listProjek = null;
	private Vector listKoordinat = null;
	private Vector listPakar = null;
	private Vector beanMaklumatPermohonan = null;
	private Vector beanMaklumatProjek = null;
	private Vector beanMaklumatPakar = null;
	private Vector beanMaklumatKoordinat = null;
	private Vector beanMaklumatHeader = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String daftarBaru(String idKaitanTujuan, String tujuanPengambilan, String tempoh,
			String pengalaman, String idNegeri, String lokasi,String luas, String idLuas,
			HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";
		String idUrusan = "9";
		String namaUser = "";
		String emelUser = "";	
		
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
				+ " B.NO_KP_BARU, B.NO_TEL, B.EMEL "
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
				r.add("ID_BANDARTETAP","99999");
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
			String noPermohonan = "JKPTG/SPHP/04/" + getKodUrusanByIdUrusan(idUrusan) + "/" + currentDate.get(Calendar.YEAR) + "/" + File.getSeqNo(db, 4, Integer.parseInt(idUrusan), 0, 0, 0, false, false, currentDate.get(Calendar.YEAR), 0);
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
			r.add("ID_JENISTUJUAN",3);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
			r.add("TUJUAN_PENGAMBILAN", tujuanPengambilan);
			r.add("TEMPOH_DIPOHON", tempoh);
			r.add("ID_TEMPOH", 2);
			r.add("PENGALAMAN", pengalaman);
			r.add("FLAG_LUAR_PERAIRANNEGERI", "1");
			r.add("LUAS_DIPOHON", luas);
			r.add("ID_UNITLUAS", idLuas);
			r.add("LOKASI_PERMOHONAN", lokasi);
			r.add("ID_NEGERI_PERAIRAN", idNegeri);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPMOHONNJDUALPERTAMA");
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
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("idKaitanTujuan",rs.getString("ID_KAITANTUJUAN") == null ? "99999" : rs.getString("ID_KAITANTUJUAN"));
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
	
public void carianFail(String userId,String noPermohonan, String tarikhPermohonan) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS,B.NO_RAYUAN, B.NO_PERMOHONAN, C.ID_PEMOHON "
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPBYRNSYRTKLLSNLESENAPB E"
				+ " WHERE A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.FLAG_JENIS_FAIL = '4' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS(+) AND B.ID_PEMOHON = C.ID_PEMOHON " 
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
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noPermohonan", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhPermohonan", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon",rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
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
			+ " C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, C.NO_TEL, C.NO_FAX, B.ID_STATUS, E.KETERANGAN, F.FLAG_SAMBUNGAN"
			+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F"
			+ " WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND "
			+ "C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS(+) AND A.ID_FAIL = '" + idFail + "'";
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
	
			sql = r.getSQLSelect("TBLPHPPROJEKLESENAPB", "ID_PROJEKLESENAPB ASC");
			ResultSet rs = stmt.executeQuery(sql);
	
			Hashtable h;
			Integer bil = 1;
	
			while (rs.next()) {
				h = new Hashtable();
	
				h.put("bil", bil);
				h.put("idProjek", rs.getString("ID_PROJEKLESENAPB"));
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? "" : rs.getString("NAMA_PROJEK").toUpperCase());
				
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
		          +" A.ID_WARGANEGARA, A.ID_BANGSA,B.KETERANGAN, A.PEGANGAN_SAHAM AS SAHAM, "
		          +" (SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"+ idPemohon +"') AS TOTAL, "
		          +" ROUND(A.PEGANGAN_SAHAM/(SELECT SUM(PEGANGAN_SAHAM) FROM TBLPHPPENGARAH WHERE ID_PEMOHON = '"+ idPemohon +"')*100) AS PERCENTAGE "
		          +" FROM TBLPHPPENGARAH A, TBLRUJWARGANEGARA B "
		          +" WHERE A.ID_PEMOHON = '"+ idPemohon +"' AND A.ID_WARGANEGARA = B.ID_WARGANEGARA ";
		          
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil",bil);
				h.put("idPengarah",rs.getString("ID_PENGARAH"));				
				h.put("nama",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("idJenisPengenalan", rs.getString("ID_JENISPENGENALAN") == null ? "99999" : rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("idWarganegara",rs.getString("ID_WARGANEGARA") == null ? "99999" : rs.getString("ID_WARGANEGARA"));
				h.put("idBangsa",rs.getString("ID_BANGSA") == null ? "99999" : rs.getString("ID_BANGSA"));				
				h.put("saham", rs.getString("PERCENTAGE") == null ? "" : rs.getString("PERCENTAGE"));
				h.put("warganegara", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				
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
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? "" : rs.getString("NAMA_PROJEK").toUpperCase());
				
				beanMaklumatProjek.addElement(h);
				bil++;
			}
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

	public String saveProjek(String idPermohonan, String nama, HttpSession session) throws Exception {
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
			
			//TBLPHPPROJEKLESENAPB
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
	
	public void updatePermohonan(String idFail,String idPermohonan,String idPemohon,String idKaitanTujuan, String tujuanPengambilan,
			String tempoh, String pengalaman,String idNegeri, String lokasi,
			String luas, String idLuas,String modalBenar, String modalJelas,HttpSession session) throws Exception {


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
			r.add("ID_JENISTUJUAN",3);
			r.add("ID_KAITANTUJUAN", idKaitanTujuan);
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
			

			if (!"".equals(namaUser) && !"".equals(emelUser)){
				XEkptgEmailSender email = XEkptgEmailSender.getInstance();
				email.FROM = "etapp_webmaster@kptg.gov.my";
				email.RECIEPIENT = emelUser;				
				email.SUBJECT = "PERMOHONAN LESEN AKTA PELANTAR BENUA #" + noPermohonan;
				email.MESSAGE = namaUser.toUpperCase() + "."
								+ "<br><br>Permohonan anda telah diterima.Sila gunakan nombor permohonan diatas sebagai rujukan."
								+ "Anda akan dimaklumkan setelah permohonan ini telah didaftarkan."
								+ "<br><br>Terima Kasih.";
				email.sendEmail();
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
	

}
