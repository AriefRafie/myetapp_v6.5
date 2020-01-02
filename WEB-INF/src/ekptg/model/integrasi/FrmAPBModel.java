package ekptg.model.integrasi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;

import ekptg.helpers.Utils;

public class FrmAPBModel {
	
	Date date = new Date();
	
	private int STATUS_DIHANTAR = 2;
	private int STATUS_ULANGAN_PERTAMA = 3;
	private int STATUS_ULANGAN_KEDUA = 4;
	private int STATUS_TELAH_DITERIMA = 5;
	
	private long idDB = 0;
	public long idUlasanTeknikal = 0;
	
	public String noFail = "";
	public String urusan = "";
	public Date tarikhTerima = null;
	public Date tarikhSurat = null;
	public String perkara = "";
	public String namaPemohon = "";
	public String alamat1Pemohon = "";
	public String alamat2Pemohon = "";
	public String alamat3Pemohon = "";
	public String poskodPemohon = "";
	public String negeriPemohon = "";
	public String noTelefonPemohon = "";
	public String noFaksPemohon = "";
	public String luarPerairanNegeri = "";
	public long idNegeri = 0;
	public String lokasiDipohon = "";
	public String luasDipohon = "";
	public long idAgensi = 0;
	
	public String agensiUlasanTeknikal = "";
	public String agensiNamaPemohonBertindih1 = "";
	public String agensiNamaPemohonBertindih2 = "";
	public String agensiNamaPemohonBertindih3 = "";
	public String agensiKoordinat = "";
	public Date agensiTarikhMohonBertindih = null;
	public String agensiCatatan = "";
	public String agensiNamaPengulas = "";
	public String agensiNoTelefonPengulas = "";
	
	public long getIdUlasanTeknikal() {
		return idUlasanTeknikal;
	}

	public void setIdUlasanTeknikal(long idUlasanTeknikal) {
		this.idUlasanTeknikal = idUlasanTeknikal;
	}

	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getUrusan() {
		return urusan;
	}

	public void setUrusan(String urusan) {
		this.urusan = urusan;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhSurat() {
		return tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public String getPerkara() {
		return perkara;
	}

	public void setPerkara(String perkara) {
		this.perkara = perkara;
	}

	public String getNamaPemohon() {
		return namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public String getAlamat1Pemohon() {
		return alamat1Pemohon;
	}

	public void setAlamat1Pemohon(String alamat1Pemohon) {
		this.alamat1Pemohon = alamat1Pemohon;
	}

	public String getAlamat2Pemohon() {
		return alamat2Pemohon;
	}

	public void setAlamat2Pemohon(String alamat2Pemohon) {
		this.alamat2Pemohon = alamat2Pemohon;
	}

	public String getAlamat3Pemohon() {
		return alamat3Pemohon;
	}

	public void setAlamat3Pemohon(String alamat3Pemohon) {
		this.alamat3Pemohon = alamat3Pemohon;
	}

	public String getPoskodPemohon() {
		return poskodPemohon;
	}

	public void setPoskodPemohon(String poskodPemohon) {
		this.poskodPemohon = poskodPemohon;
	}

	public String getNegeriPemohon() {
		return negeriPemohon;
	}

	public void setNegeriPemohon(String negeriPemohon) {
		this.negeriPemohon = negeriPemohon;
	}

	public String getNoTelefonPemohon() {
		return noTelefonPemohon;
	}

	public void setNoTelefonPemohon(String noTelefonPemohon) {
		this.noTelefonPemohon = noTelefonPemohon;
	}

	public String getNoFaksPemohon() {
		return noFaksPemohon;
	}

	public void setNoFaksPemohon(String noFaksPemohon) {
		this.noFaksPemohon = noFaksPemohon;
	}

	public String getLuarPerairanNegeri() {
		return luarPerairanNegeri;
	}

	public void setLuarPerairanNegeri(String luarPerairanNegeri) {
		this.luarPerairanNegeri = luarPerairanNegeri;
	}

	public long getIdNegeri() {
		return idNegeri;
	}

	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getLokasiDipohon() {
		return lokasiDipohon;
	}

	public void setLokasiDipohon(String lokasiDipohon) {
		this.lokasiDipohon = lokasiDipohon;
	}

	public String getLuasDipohon() {
		return luasDipohon;
	}

	public void setLuasDipohon(String luasDipohon) {
		this.luasDipohon = luasDipohon;
	}

	public long getIdAgensi() {
		return idAgensi;
	}

	public void setIdAgensi(long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public String getAgensiUlasanTeknikal() {
		return agensiUlasanTeknikal;
	}

	public void setAgensiUlasanTeknikal(String agensiUlasanTeknikal) {
		this.agensiUlasanTeknikal = agensiUlasanTeknikal;
	}

	public String getAgensiNamaPemohonBertindih1() {
		return agensiNamaPemohonBertindih1;
	}

	public void setAgensiNamaPemohonBertindih1(String agensiNamaPemohonBertindih1) {
		this.agensiNamaPemohonBertindih1 = agensiNamaPemohonBertindih1;
	}

	public String getAgensiNamaPemohonBertindih2() {
		return agensiNamaPemohonBertindih2;
	}

	public void setAgensiNamaPemohonBertindih2(String agensiNamaPemohonBertindih2) {
		this.agensiNamaPemohonBertindih2 = agensiNamaPemohonBertindih2;
	}

	public String getAgensiNamaPemohonBertindih3() {
		return agensiNamaPemohonBertindih3;
	}

	public void setAgensiNamaPemohonBertindih3(String agensiNamaPemohonBertindih3) {
		this.agensiNamaPemohonBertindih3 = agensiNamaPemohonBertindih3;
	}

	public String getAgensiKoordinat() {
		return agensiKoordinat;
	}

	public void setAgensiKoordinat(String agensiKoordinat) {
		this.agensiKoordinat = agensiKoordinat;
	}

	public Date getAgensiTarikhMohonBertindih() {
		return agensiTarikhMohonBertindih;
	}

	public void setAgensiTarikhMohonBertindih(Date agensiTarikhMohonBertindih) {
		this.agensiTarikhMohonBertindih = agensiTarikhMohonBertindih;
	}

	public String getAgensiCatatan() {
		return agensiCatatan;
	}

	public void setAgensiCatatan(String agensiCatatan) {
		this.agensiCatatan = agensiCatatan;
	}

	public String getAgensiNamaPengulas() {
		return agensiNamaPengulas;
	}

	public void setAgensiNamaPengulas(String agensiNamaPengulas) {
		this.agensiNamaPengulas = agensiNamaPengulas;
	}

	public String getAgensiNoTelefonPengulas() {
		return agensiNoTelefonPengulas;
	}

	public void setAgensiNoTelefonPengulas(String agensiNoTelefonPengulas) {
		this.agensiNoTelefonPengulas = agensiNoTelefonPengulas;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector searchMaklumat() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(noFail)) {
				SQL_SEARCH += "AND UPPER(A.NO_FAIL) LIKE '%" + noFail.toUpperCase() + "%' ";
			}
			if (tarikhSurat != null) {
				SQL_SEARCH += "AND B.TARIKH_SURAT = '" + sdf.format(tarikhSurat.toString()) + "' ";
			}
			if (!"".equalsIgnoreCase(namaPemohon)) {
				SQL_SEARCH += "AND UPPER(C.NAMA) = '" + namaPemohon + "' ";
			}
			if (idNegeri > 0) {
				SQL_SEARCH += "AND F.ID_NEGERI_PERAIRAN = " + idNegeri + " ";
			}
			if (idAgensi > 0) {
				SQL_SEARCH += "AND G.ID_AGENSI = " + idAgensi + " ";
			}
			sql = "SELECT B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.NAMA_NEGERI, I.NAMA_AGENSI, G.FLAG_STATUS " +
				"FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, TBLPHPULASANTEKNIKAL G, TBLRUJAGENSI I " + 
				"WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND F.ID_NEGERI_PERAIRAN = D.ID_NEGERI " +
				"AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_STATUS = E.ID_STATUS AND B.ID_PERMOHONAN = G.ID_PERMOHONAN AND G.ID_AGENSI = I.ID_AGENSI AND G.FLAG_STATUS IN (2, 3, 4) " +
				SQL_SEARCH + "ORDER BY G.TARIKH_KEMASKINI DESC";
			int i = 1;
			String ID_PERMOHONAN = "", NO_FAIL = "", TARIKH_SURAT = "", NAMA_PEMOHON = "", NEGERI = "", AGENSI = "";
			String STATUS = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				TARIKH_SURAT = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
				NAMA_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
				NEGERI = rs.getString(5) == null ? "" : rs.getString(5);
				AGENSI = rs.getString(6) == null ? "" : rs.getString(6);
				STATUS = rs.getString(7) == null ? "" : rs.getString(7);
				
				h = new Hashtable();
				h.put("NO", i);
				h.put("ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("NO_FAIL", NO_FAIL);
				h.put("TARIKH_SURAT", TARIKH_SURAT);
				h.put("NAMA_PEMOHON", NAMA_PEMOHON);
				h.put("NEGERI", NEGERI);
				h.put("AGENSI", AGENSI);
				h.put("STATUS", STATUS);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public long getIdPermohonanFromIdUlasanTeknikal(long ID_ULASANTEKNIKAL) throws Exception {
		long returnValue = 0;
		Db db = new Db();
		
		try {
			if (ID_ULASANTEKNIKAL > 0) {
				Statement stmt = db.getStatement();			
				String sql = "SELECT ID_PERMOHONAN FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = " + ID_ULASANTEKNIKAL;
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getLong(1);
				}
				rs.close();
			}
		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector viewMaklumat() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (idUlasanTeknikal > 0) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				
				String ID_ULASANTEKNIKAL = "", NO_FAIL = "", URUSAN = "", TARIKH_TERIMA = "", TARIKH_SURAT = "", PERKARA = "", NAMA_PEMOHON = "", ALAMAT1_PEMOHON = "";
				String ALAMAT2_PEMOHON = "", ALAMAT3_PEMOHON = "", POSKOD = "", NEGERI_PEMOHON = "", NO_TELEFON = "", NO_FAKS = "";
				String LUAR_PERAIRAN_NEGERI = "", NEGERI = "", LOKASI = "", LUAS_DIPOHON = "", MAKLUMAT_TAMBAHAN = "";
				String AGENSI_ULASAN = "", AGENSI_NAMA_PEMOHON_TINDIH1 = "", AGENSI_NAMA_PEMOHON_TINDIH2 = "", AGENSI_NAMA_PEMOHON_TINDIH3 = "", AGENSI_TARIKH_MOHON_TINDIH = "", AGENSI_IMEJ_KAWASAN = "";
				String AGENSI_CATATAN = "", AGENSI_NAMA_PENGULAS = "", AGENSI_NO_TEL_PENGULAS = "", STATUS_PROSES = "", UNIT_LUAS_DIPOHON = "", AGENSI_KOORDINAT = "";
				
				sql = "SELECT G.ID_ULASANTEKNIKAL, A.NO_FAIL, 'AKTA PELANTAR BENUA', B.TARIKH_TERIMA, B.TARIKH_SURAT, B.TUJUAN, C.NAMA, C.ALAMAT1_TETAP, C.ALAMAT2_TETAP, " +
					"C.ALAMAT3_TETAP, C.POSKOD_TETAP, D.NAMA_NEGERI, C.NO_TEL, C.NO_FAX, F.FLAG_LUAR_PERAIRANNEGERI, I.NAMA_NEGERI, F.LOKASI_PERMOHONAN, F.LUAS_DIPOHON, " +
					"G.AGENSI_ULASAN, G.AGENSI_NAMA_PEMOHON_TINDIH1, G.AGENSI_TARIKH_MOHON_TINDIH, G.AGENSI_CATATAN, G.AGENSI_NAMA_PENGULAS, G.AGENSI_NO_TEL_PENGULAS, " +
					"G.FLAG_STATUS, H.KETERANGAN, G.MAKLUMAT_TAMBAHAN, G.AGENSI_NAMA_PEMOHON_TINDIH2, G.AGENSI_NAMA_PEMOHON_TINDIH3, G.AGENSI_KOORDINAT " +
					"FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, TBLPHPULASANTEKNIKAL G, TBLRUJNEGERI I, TBLRUJLUAS H " +
					"WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL AND F.ID_NEGERI_PERAIRAN = I.ID_NEGERI " +
					"AND B.ID_PEMOHON = C.ID_PEMOHON AND C.ID_NEGERITETAP = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS AND B.ID_PERMOHONAN = G.ID_PERMOHONAN AND F.ID_UNITLUAS = H.ID_LUAS " +
					"AND G.ID_ULASANTEKNIKAL = " + idUlasanTeknikal;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_ULASANTEKNIKAL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					URUSAN = rs.getString(3) == null ? "" : rs.getString(3);
					TARIKH_TERIMA = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					TARIKH_SURAT = rs.getDate(5) == null ? "" : sdf.format(rs.getDate(5));
					PERKARA = rs.getString(6) == null ? "" : rs.getString(6);
					NAMA_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					ALAMAT1_PEMOHON = rs.getString(8) == null ? "" : rs.getString(8);
					ALAMAT2_PEMOHON = rs.getString(9) == null ? "" : rs.getString(9);
					ALAMAT3_PEMOHON = rs.getString(10) == null ? "" : rs.getString(10);
					POSKOD = rs.getString(11) == null ? "" : rs.getString(11);
					NEGERI_PEMOHON = rs.getString(12) == null ? "" : rs.getString(12);
					NO_TELEFON = rs.getString(13) == null ? "" : rs.getString(13);
					NO_FAKS = rs.getString(14) == null ? "" : rs.getString(14);
					LUAR_PERAIRAN_NEGERI = rs.getString(15) == null ? "" : rs.getString(15);
					NEGERI = rs.getString(16) == null ? "" : rs.getString(16);
					LOKASI = rs.getString(17) == null ? "" : rs.getString(17);
					LUAS_DIPOHON = rs.getString(18) == null ? "" : rs.getString(18);
					AGENSI_ULASAN = rs.getString(19) == null ? "" : rs.getString(19);
					AGENSI_NAMA_PEMOHON_TINDIH1 = rs.getString(20) == null ? "" : rs.getString(20);
					AGENSI_TARIKH_MOHON_TINDIH = rs.getDate(21) == null ? "" : sdf.format(rs.getDate(21));
					AGENSI_CATATAN = rs.getString(22) == null ? "" : rs.getString(22);
					AGENSI_NAMA_PENGULAS = rs.getString(23) == null ? "" : rs.getString(23);
					AGENSI_NO_TEL_PENGULAS = rs.getString(24) == null ? "" : rs.getString(24);
					STATUS_PROSES = rs.getString(25) == null ? "" : rs.getString(25);
					UNIT_LUAS_DIPOHON = rs.getString(26) == null ? "" : rs.getString(26);
					MAKLUMAT_TAMBAHAN = rs.getString(27) == null ? "" : rs.getString(27);
					AGENSI_NAMA_PEMOHON_TINDIH2 = rs.getString(28) == null ? "" : rs.getString(28);
					AGENSI_NAMA_PEMOHON_TINDIH3 = rs.getString(29) == null ? "" : rs.getString(29);
					AGENSI_KOORDINAT = rs.getString(30) == null ? "" : rs.getString(30);
				}
				
				h = new Hashtable();
				h.put("ID_ULASANTEKNIKAL", ID_ULASANTEKNIKAL);
				h.put("MP_NOFAIL", NO_FAIL);
				h.put("MP_URUSAN", URUSAN);
				h.put("MP_TARIKHTERIMA", TARIKH_TERIMA);
				h.put("MP_TARIKHSURAT", TARIKH_SURAT);
				h.put("MP_PERKARA", PERKARA);
				h.put("MP_NAMAPEMOHON", NAMA_PEMOHON);
				h.put("MP_ALAMAT1PEMOHON", ALAMAT1_PEMOHON);
				h.put("MP_ALAMAT2PEMOHON", ALAMAT2_PEMOHON);
				h.put("MP_ALAMAT3PEMOHON", ALAMAT3_PEMOHON);
				h.put("MP_POSKOD", POSKOD);
				h.put("MP_NEGERI", NEGERI_PEMOHON);
				h.put("MP_NOTELEFON", NO_TELEFON);
				h.put("MP_NOFAKS", NO_FAKS);
				h.put("KAW_LUARPERAIRANNEGERI", LUAR_PERAIRAN_NEGERI);
				h.put("KAW_NEGERI", NEGERI);
				h.put("KAW_LOKASI", LOKASI);
				h.put("KAW_LUASDIPOHON", LUAS_DIPOHON + " " + UNIT_LUAS_DIPOHON);
				h.put("LAINLAIN_MAKLUMATTAMBAHAN", MAKLUMAT_TAMBAHAN);
				h.put("MB_ULASAN", AGENSI_ULASAN);
				h.put("MB_NAMAPEMOHONTINDIH1", AGENSI_NAMA_PEMOHON_TINDIH1);
				h.put("MB_NAMAPEMOHONTINDIH2", AGENSI_NAMA_PEMOHON_TINDIH2);
				h.put("MB_NAMAPEMOHONTINDIH3", AGENSI_NAMA_PEMOHON_TINDIH3);
				h.put("MB_KOORDINAT", AGENSI_KOORDINAT);
				h.put("MB_TARIKHMOHONTINDIH", AGENSI_TARIKH_MOHON_TINDIH);
				h.put("AGENSI_IMEJ_KAWASAN", AGENSI_IMEJ_KAWASAN);
				h.put("MB_CATATAN", AGENSI_CATATAN);
				h.put("MB_NAMAPENGULAS", AGENSI_NAMA_PENGULAS);
				h.put("MB_NOTELPENGULAS", AGENSI_NO_TEL_PENGULAS);
				h.put("STATUS_PROSES", STATUS_PROSES);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean saveMaklumat() throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (idUlasanTeknikal > 0) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				// check in TBLINTAPB
				r.clear();
				r.add("ID_ULASANTEKNIKAL");
				r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
				sql = r.getSQLSelect("TBLPHPULASANTEKNIKAL");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
				
				r.clear();
				if (haveData) {
					r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
				}
				r.add("AGENSI_ULASAN", agensiUlasanTeknikal);
				r.add("AGENSI_NAMA_PEMOHON_TINDIH1", agensiNamaPemohonBertindih1);
				r.add("AGENSI_NAMA_PEMOHON_TINDIH2", agensiNamaPemohonBertindih2);
				r.add("AGENSI_NAMA_PEMOHON_TINDIH3", agensiNamaPemohonBertindih3);
				r.add("AGENSI_KOORDINAT", agensiKoordinat);
				if (agensiTarikhMohonBertindih != null) {
					r.add("AGENSI_TARIKH_MOHON_TINDIH", r.unquote("to_date('" + sdf.format(agensiTarikhMohonBertindih) + "','dd/MM/yyyy')"));
				}					
				r.add("AGENSI_CATATAN", agensiCatatan);
				r.add("AGENSI_NAMA_PENGULAS", agensiNamaPengulas);
				r.add("AGENSI_NO_TEL_PENGULAS", agensiNoTelefonPengulas);

				if (haveData) {
					sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
					stmt.execute(sql);
				}


				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean sendMaklumat() throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (idUlasanTeknikal > 0) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				// check in TBLINTAPB
				r.clear();
				r.add("ID_ULASANTEKNIKAL");
				r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
				sql = r.getSQLSelect("TBLPHPULASANTEKNIKAL");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
				
				r.clear();
				if (haveData) {
					int STATUS = 0;
					r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
					STATUS = STATUS_TELAH_DITERIMA;
					r.add("FLAG_STATUS", STATUS);
					sql = r.getSQLUpdate("TBLPHPULASANTEKNIKAL");
					stmt.execute(sql);
					
					returnValue = true;
				}
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean uploadFileAPB(FileItem objItem, String ID_USER, String ID_ULASANTEKNIKAL) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("UPDATE TBLPHPULASANTEKNIKAL SET AGENSI_IMEJ_KAWASAN = ?, " +
        			"AGENSI_IMEJ_MIME = ?, AGENSI_IMEJ_NAME = ? WHERE ID_ULASANTEKNIKAL = ?");
        	ps.setBinaryStream(1, objItem.getInputStream(), (int)objItem.getSize());
        	ps.setString(2, objItem.getContentType());
        	ps.setString(3, objItem.getName());
        	ps.setLong(4, Utils.parseLong(ID_ULASANTEKNIKAL));
        	ps.executeUpdate();
            con.commit();
            
            returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteImage() throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			sql = "UPDATE TBLPHPULASANTEKNIKAL SET AGENSI_IMEJ_KAWASAN = NULL, AGENSI_IMEJ_NAME = NULL WHERE ID_ULASANTEKNIKAL = " + idUlasanTeknikal;
			stmt.executeQuery(sql);
			returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean hasImage(String ID_ULASANTEKNIKAL) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_ULASANTEKNIKAL.trim())) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				sql = "SELECT ID_ULASANTEKNIKAL FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = " + ID_ULASANTEKNIKAL + " AND AGENSI_IMEJ_KAWASAN IS NOT NULL";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = true;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String imageName(String ID_ULASANTEKNIKAL) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_ULASANTEKNIKAL.trim())) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				sql = "SELECT AGENSI_IMEJ_NAME FROM TBLPHPULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL = " + ID_ULASANTEKNIKAL + " AND AGENSI_IMEJ_KAWASAN IS NOT NULL";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteAPBFile(String ID_APBDOKUMEN) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();

		try {
			String sql = "";
			Statement stmt = db.getStatement();
			
			sql = "DELETE FROM TBLINTAPBDOKUMEN WHERE ID_APBDOKUMEN = " + ID_APBDOKUMEN;
			stmt.execute(sql);
			returnValue = true;
		} finally {
			if (db != null)
				db.close();			
		}
		
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getListUploadedFile(String ID_ULASANTEKNIKAL) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			SQLRenderer r = new SQLRenderer();
			int i = 1;
			String ID_APBDOKUMEN = "", CONTENT_NAME = "", CONTENT_MIME = "", CONTENT_SHA1 = "";
			
			r.add("ID_APBDOKUMEN");
			r.add("CONTENT_NAME");
			r.add("CONTENT_MIME");
			r.add("CONTENT_SHA1");
			r.add("ID_ULASANTEKNIKAL", ID_ULASANTEKNIKAL);
			sql = r.getSQLSelect("TBLINTAPBDOKUMEN", "TARIKH_KEMASKINI DESC");
			r.clear();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_APBDOKUMEN = rs.getString(1) == null ? "" : rs.getString(1);
				CONTENT_NAME = rs.getString(2) == null ? "" : rs.getString(2);
				CONTENT_MIME = rs.getString(3) == null ? "" : rs.getString(3);
				CONTENT_SHA1 = rs.getString(4) == null ? "" : rs.getString(4);
					
				h = new Hashtable();
				h.put("No", i);
				h.put("ID_APBDOKUMEN", ID_APBDOKUMEN);
				h.put("CONTENT_NAME", CONTENT_NAME);
				h.put("CONTENT_MIME", CONTENT_MIME);
				h.put("CONTENT_SHA1", CONTENT_SHA1);
				
				v.add(h);
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getListKoordinat(long idPermohonan) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (idPermohonan > 0) {
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				int i = 1;
				String ID_KOORDINATPERMOHONAN = "", LABEL_TITIK = "", DARJAH_U = "", DARJAH_T = "", MINIT_U = "", MINIT_T = "", SAAT_U = "", SAAT_T = "";
				
				sql = "SELECT ID_KOORDINATPERMOHONAN, LABEL_TITIK, DARJAH_U, DARJAH_T, MINIT_U, MINIT_T, SAAT_U, SAAT_T " +
					"FROM TBLPHPKOORDINATPERMOHONAN " +
					"WHERE ID_ULASANTEKNIKAL IS NULL AND ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY ID_KOORDINATPERMOHONAN";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_KOORDINATPERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					LABEL_TITIK = rs.getString(2) == null ? "" : rs.getString(2);
					DARJAH_U = rs.getString(3) == null ? "" : rs.getString(3);
					DARJAH_T = rs.getString(4) == null ? "" : rs.getString(4);
					MINIT_U = rs.getString(4) == null ? "" : rs.getString(4);
					MINIT_T = rs.getString(4) == null ? "" : rs.getString(4);
					SAAT_U = rs.getString(4) == null ? "" : rs.getString(4);
					SAAT_T = rs.getString(4) == null ? "" : rs.getString(4);
						
					h = new Hashtable();
					h.put("NO", i);
					h.put("ID_KOORDINATPERMOHONAN", ID_KOORDINATPERMOHONAN);
					h.put("LABEL_TITIK", LABEL_TITIK);
					h.put("DARJAH_U", DARJAH_U);
					h.put("DARJAH_T", DARJAH_T);
					h.put("MINIT_U", MINIT_U);
					h.put("MINIT_T", MINIT_T);
					h.put("SAAT_U", SAAT_U);
					h.put("SAAT_T", SAAT_T);
					
					v.add(h);
					i++;
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}	
	
	public String getNamaAgensi(long ID_AGENSI) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			// TODO
			String sql = "SELECT NAMA_AGENSI FROM TBLRUJAGENSI WHERE ID_AGENSI = " + ID_AGENSI;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
			rs.close();			
		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public Long getIdAgensiFromIdUser(long ID_USER) throws Exception {
		Long returnValue = (long) 0;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			/// TODO
			String sql = "SELECT ID_AGENSI FROM USERS_INTEGRASI WHERE USER_ID = " + ID_USER;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getLong(1);
			}
			rs.close();			
		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector viewMyInfo(long ID_AGENSI) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (ID_AGENSI > 0) {
				String sql = "";
                sql = "SELECT G.ID_ULASANTEKNIKAL, A.NO_FAIL, D.NAMA_NEGERI, G.TARIKH_HANTAR, C.NAMA, G.FLAG_STATUS "
                	+ "FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJNEGERI D, TBLRUJSTATUS E, TBLPHPPMOHONNJDUALPERTAMA F, TBLPHPULASANTEKNIKAL G "
                	+ "WHERE B.ID_PERMOHONAN = F.ID_PERMOHONAN AND A.ID_URUSAN = '9' AND A.ID_SUBURUSAN = '57' AND A.ID_FAIL = B.ID_FAIL "
                	+ "AND B.ID_PEMOHON = C.ID_PEMOHON AND F.ID_NEGERI_PERAIRAN = D.ID_NEGERI AND B.ID_STATUS = E.ID_STATUS AND B.ID_PERMOHONAN = G.ID_PERMOHONAN "
                	+ "AND G.FLAG_STATUS IN (2, 3, 4) AND G.ID_AGENSI = " + ID_AGENSI + " "
                	+ "ORDER BY A.NO_FAIL, G.TARIKH_HANTAR DESC, D.NAMA_NEGERI, C.NAMA";
				int i = 1;
				String ID_ULASANTEKNIKAL = "", NO_FAIL = "", NEGERI_PERAIRAN = "", TARIKH_SURAT = "", NAMA_PEMOHON = "", STATUS = "";
				Hashtable h = null;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					ID_ULASANTEKNIKAL = rs.getString(1) == null ? "" : rs.getString(1);
					NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
					NEGERI_PERAIRAN = rs.getString(3) == null ? "" : rs.getString(3);
					TARIKH_SURAT = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
					NAMA_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
					STATUS = rs.getString(6) == null ? "" : rs.getString(6);
					
					if (STATUS.equalsIgnoreCase(Long.toString(STATUS_DIHANTAR))) {
						STATUS = "DIHANTAR";
					} else if (STATUS.equalsIgnoreCase(Long.toString(STATUS_ULANGAN_PERTAMA))) {
						STATUS = "ULANGAN PERTAMA";
					} else if (STATUS.equalsIgnoreCase(Long.toString(STATUS_ULANGAN_KEDUA))) {
						STATUS = "ULANGAN KEDUA";
					} else if (STATUS.equalsIgnoreCase(Long.toString(STATUS_TELAH_DITERIMA))) {
						STATUS = "TELAH DITERIMA";
					} else {
						STATUS = "";
					}
					
					h = new Hashtable();
					h.put("NO", i);
					h.put("ID_ULASANTEKNIKAL", ID_ULASANTEKNIKAL);
					h.put("NO_FAIL", NO_FAIL);
					h.put("NEGERI_PERAIRAN", NEGERI_PERAIRAN);
					h.put("TARIKH_SURAT", TARIKH_SURAT);
					h.put("NAMA_PEMOHON", NAMA_PEMOHON);
					h.put("STATUS", STATUS);
					v.add(h);
					i++;
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}	
}