package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.intergration.XEkptgEmailSender;

public class FrmJPPHModelNilaianSewaan {

	private String noFail;
	private long idDB;
	private String idPermohonan;
	private String idHM;
	private String idSimpan;
	private String tarikhSimpan;
	private String idKemaskini;
	private String tarikhKemaskini;
	private String idKemaskiniJPPH;
	private String tarikhKemaskiniJPPH;
	private String statusProses;
	private String unitJKPTG;
	private String namaPegawaiJKPTG;
	private String noTelPegawaiJKPTG;
	private String cawanganJKPTG;
	private String idNegeri;
	private String idDaerah;
	private String idMukim;
	private String seksyen;
	private String jenisHakmilik;
	private String noHakmilik;
	private String noPTLot;
	private String jenisPTLot;
	private String jenisPegangan;
	private String tarikhLuputPajakan;
	private String tempohPajakan;
	private String adaPelanTapak;
	private String kategoriTanah;
	private String syaratNyata;
	private String sekatanKepentingan;
	private String luasAsal;
	private String jenisLuasAsal;
	private String luasDisewa;
	private String jenisLuasDisewa;
	private String statusPemilikan;
	private String tujuanSewaan;
	private String alamatHarta1;
	private String alamatHarta2;
	private String alamatHarta3;
	private String alamatHarta4;
	private String noLotBerkaitan;
	private String catatan;
	private String emailAddr1;
	private String emailAddr2;
	private String emailAddr3;
	private String emailSend;
	private String jpphTarikhNilaian;
	private String jpphKadarSewaBulanan;
	private String jpphKadarSewaBulanan3Tahun;
	private String jpphKadarSewaTahunan;
	private String jpphNamaPegawai;
	private String jpphCawangan;
	private String jpphCatatan;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public long getIdDB() {
		return idDB;
	}

	public void setIdDB(long idDB) {
		this.idDB = idDB;
	}

	public String getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getIdHM() {
		return idHM;
	}

	public void setIdHM(String idHM) {
		this.idHM = idHM;
	}

	public String getIdSimpan() {
		return idSimpan;
	}

	public void setIdSimpan(String idSimpan) {
		this.idSimpan = idSimpan;
	}

	public String getTarikhSimpan() {
		return tarikhSimpan;
	}

	public void setTarikhSimpan(String tarikhSimpan) {
		this.tarikhSimpan = tarikhSimpan;
	}

	public String getIdKemaskini() {
		return idKemaskini;
	}

	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}

	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public String getIdKemaskiniJPPH() {
		return idKemaskiniJPPH;
	}

	public void setIdKemaskiniJPPH(String idKemaskiniJPPH) {
		this.idKemaskiniJPPH = idKemaskiniJPPH;
	}

	public String getTarikhKemaskiniJPPH() {
		return tarikhKemaskiniJPPH;
	}

	public void setTarikhKemaskiniJPPH(String tarikhKemaskiniJPPH) {
		this.tarikhKemaskiniJPPH = tarikhKemaskiniJPPH;
	}

	public String getStatusProses() {
		return statusProses;
	}

	public void setStatusProses(String statusProses) {
		this.statusProses = statusProses;
	}

	public String getUnitJKPTG() {
		return unitJKPTG;
	}

	public void setUnitJKPTG(String unitJKPTG) {
		this.unitJKPTG = unitJKPTG;
	}

	public String getNamaPegawaiJKPTG() {
		return namaPegawaiJKPTG;
	}

	public void setNamaPegawaiJKPTG(String namaPegawaiJKPTG) {
		this.namaPegawaiJKPTG = namaPegawaiJKPTG;
	}

	public String getNoTelPegawaiJKPTG() {
		return noTelPegawaiJKPTG;
	}

	public void setNoTelPegawaiJKPTG(String noTelPegawaiJKPTG) {
		this.noTelPegawaiJKPTG = noTelPegawaiJKPTG;
	}

	public String getCawanganJKPTG() {
		return cawanganJKPTG;
	}

	public void setCawanganJKPTG(String cawanganJKPTG) {
		this.cawanganJKPTG = cawanganJKPTG;
	}

	public String getIdNegeri() {
		return idNegeri;
	}

	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getIdDaerah() {
		return idDaerah;
	}

	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getIdMukim() {
		return idMukim;
	}

	public void setIdMukim(String idMukim) {
		this.idMukim = idMukim;
	}

	public String getSeksyen() {
		return seksyen;
	}

	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}

	public String getJenisHakmilik() {
		return jenisHakmilik;
	}

	public void setJenisHakmilik(String jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
	}

	public String getNoHakmilik() {
		return noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getNoPTLot() {
		return noPTLot;
	}

	public void setNoPTLot(String noPTLot) {
		this.noPTLot = noPTLot;
	}

	public String getJenisPTLot() {
		return jenisPTLot;
	}

	public void setJenisPTLot(String jenisPTLot) {
		this.jenisPTLot = jenisPTLot;
	}

	public String getJenisPegangan() {
		return jenisPegangan;
	}

	public void setJenisPegangan(String jenisPegangan) {
		this.jenisPegangan = jenisPegangan;
	}

	public String getTarikhLuputPajakan() {
		return tarikhLuputPajakan;
	}

	public void setTarikhLuputPajakan(String tarikhLuputPajakan) {
		this.tarikhLuputPajakan = tarikhLuputPajakan;
	}

	public String getTempohPajakan() {
		return tempohPajakan;
	}

	public void setTempohPajakan(String tempohPajakan) {
		this.tempohPajakan = tempohPajakan;
	}

	public String getAdaPelanTapak() {
		return adaPelanTapak;
	}

	public void setAdaPelanTapak(String adaPelanTapak) {
		this.adaPelanTapak = adaPelanTapak;
	}

	public String getKategoriTanah() {
		return kategoriTanah;
	}

	public void setKategoriTanah(String kategoriTanah) {
		this.kategoriTanah = kategoriTanah;
	}

	public String getSyaratNyata() {
		return syaratNyata;
	}

	public void setSyaratNyata(String syaratNyata) {
		this.syaratNyata = syaratNyata;
	}

	public String getSekatanKepentingan() {
		return sekatanKepentingan;
	}

	public void setSekatanKepentingan(String sekatanKepentingan) {
		this.sekatanKepentingan = sekatanKepentingan;
	}

	public String getLuasAsal() {
		return luasAsal;
	}

	public void setLuasAsal(String luasAsal) {
		this.luasAsal = luasAsal;
	}

	public String getJenisLuasAsal() {
		return jenisLuasAsal;
	}

	public void setJenisLuasAsal(String jenisLuasAsal) {
		this.jenisLuasAsal = jenisLuasAsal;
	}

	public String getLuasDisewa() {
		return luasDisewa;
	}

	public void setLuasDisewa(String luasDisewa) {
		this.luasDisewa = luasDisewa;
	}

	public String getJenisLuasDisewa() {
		return jenisLuasDisewa;
	}

	public void setJenisLuasDisewa(String jenisLuasDisewa) {
		this.jenisLuasDisewa = jenisLuasDisewa;
	}

	public String getStatusPemilikan() {
		return statusPemilikan;
	}

	public void setStatusPemilikan(String statusPemilikan) {
		this.statusPemilikan = statusPemilikan;
	}

	public String getTujuanSewaan() {
		return tujuanSewaan;
	}

	public void setTujuanSewaan(String tujuanSewaan) {
		this.tujuanSewaan = tujuanSewaan;
	}

	public String getAlamatHarta1() {
		return alamatHarta1;
	}

	public void setAlamatHarta1(String alamatHarta1) {
		this.alamatHarta1 = alamatHarta1;
	}

	public String getAlamatHarta2() {
		return alamatHarta2;
	}

	public void setAlamatHarta2(String alamatHarta2) {
		this.alamatHarta2 = alamatHarta2;
	}

	public String getAlamatHarta3() {
		return alamatHarta3;
	}

	public void setAlamatHarta3(String alamatHarta3) {
		this.alamatHarta3 = alamatHarta3;
	}

	public String getAlamatHarta4() {
		return alamatHarta4;
	}

	public void setAlamatHarta4(String alamatHarta4) {
		this.alamatHarta4 = alamatHarta4;
	}

	public String getNoLotBerkaitan() {
		return noLotBerkaitan;
	}

	public void setNoLotBerkaitan(String noLotBerkaitan) {
		this.noLotBerkaitan = noLotBerkaitan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getEmailAddr1() {
		return emailAddr1;
	}

	public void setEmailAddr1(String emailAddr1) {
		this.emailAddr1 = emailAddr1;
	}

	public String getEmailAddr2() {
		return emailAddr2;
	}

	public void setEmailAddr2(String emailAddr2) {
		this.emailAddr2 = emailAddr2;
	}

	public String getEmailAddr3() {
		return emailAddr3;
	}

	public void setEmailAddr3(String emailAddr3) {
		this.emailAddr3 = emailAddr3;
	}

	public String getEmailSend() {
		return emailSend;
	}

	public void setEmailSend(String emailSend) {
		this.emailSend = emailSend;
	}

	public String getJpphTarikhNilaian() {
		return jpphTarikhNilaian;
	}

	public void setJpphTarikhNilaian(String jpphTarikhNilaian) {
		this.jpphTarikhNilaian = jpphTarikhNilaian;
	}

	public String getJpphKadarSewaBulanan() {
		return jpphKadarSewaBulanan;
	}

	public void setJpphKadarSewaBulanan(String jpphKadarSewaBulanan) {
		this.jpphKadarSewaBulanan = jpphKadarSewaBulanan;
	}

	public String getJpphKadarSewaBulanan3Tahun() {
		return jpphKadarSewaBulanan3Tahun;
	}

	public void setJpphKadarSewaBulanan3Tahun(String jpphKadarSewaBulanan3Tahun) {
		this.jpphKadarSewaBulanan3Tahun = jpphKadarSewaBulanan3Tahun;
	}

	public String getJpphKadarSewaTahunan() {
		return jpphKadarSewaTahunan;
	}

	public void setJpphKadarSewaTahunan(String jpphKadarSewaTahunan) {
		this.jpphKadarSewaTahunan = jpphKadarSewaTahunan;
	}

	public String getJpphNamaPegawai() {
		return jpphNamaPegawai;
	}

	public void setJpphNamaPegawai(String jpphNamaPegawai) {
		this.jpphNamaPegawai = jpphNamaPegawai;
	}

	public String getJpphCawangan() {
		return jpphCawangan;
	}

	public void setJpphCawangan(String jpphCawangan) {
		this.jpphCawangan = jpphCawangan;
	}

	public String getJpphCatatan() {
		return jpphCatatan;
	}

	public void setJpphCatatan(String jpphCatatan) {
		this.jpphCatatan = jpphCatatan;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector searchMaklumat() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(noFail)) {
				SQL_SEARCH += "AND UPPER(FA.NO_FAIL) LIKE '%" + noFail.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(idNegeri)) {
				SQL_SEARCH += "AND HTPHM.ID_NEGERI = '" + idNegeri + "' ";
			}
			if (!"".equalsIgnoreCase(idDaerah)) {
				SQL_SEARCH += "AND UPPER(HTPHM.ID_DAERAH) = '" + idDaerah + "' ";
			}
			if (!"".equalsIgnoreCase(idMukim)) {
				SQL_SEARCH += "AND UPPER(HTPHM.ID_MUKIM) = '" + idMukim + "' ";
			}
			if (!"".equalsIgnoreCase(noHakmilik)) {
				SQL_SEARCH += "AND UPPER(HTPHM.NO_HAKMILIK) LIKE '%" + noHakmilik.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(noPTLot)) {
				SQL_SEARCH += "AND UPPER(PEMOHON.NO_LOT) LIKE '%" + noPTLot.toUpperCase() + "%' ";
			}
			sql = "SELECT M.ID_PERMOHONAN, HTPHM.ID_HAKMILIK, FA.NO_FAIL, NG.NAMA_NEGERI, DA.NAMA_DAERAH, MK.NAMA_MUKIM, HTPHM.NO_HAKMILIK, HTPHM.NO_LOT " +
				"FROM TBLPERMOHONAN M, TBLPHPHAKMILIKPERMOHONAN PHPHMPMHN, TBLHTPHAKMILIKAGENSI HTPHMAGENSI, TBLHTPHAKMILIK HTPHM, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA, TBLRUJMUKIM MK " +
				"WHERE M.ID_PERMOHONAN = PHPHMPMHN.ID_PERMOHONAN AND PHPHMPMHN.ID_HAKMILIKAGENSI = HTPHMAGENSI.ID_HAKMILIKAGENSI AND HTPHMAGENSI.ID_HAKMILIK = HTPHM.ID_HAKMILIK " +
				"AND M.ID_FAIL = FA.ID_FAIL AND HTPHM.ID_NEGERI = NG.ID_NEGERI(+) AND HTPHM.ID_DAERAH = DA.ID_DAERAH(+) AND HTPHM.ID_MUKIM = MK.ID_MUKIM(+) " +
				SQL_SEARCH + "ORDER BY NG.ID_NEGERI, DA.ID_DAERAH, MK.ID_MUKIM, FA.NO_FAIL, HTPHM.NO_HAKMILIK, HTPHM.NO_LOT";
			int i = 1;
			String ID_PERMOHONAN = "", ID_HM = "", NO_FAIL = "", NEGERI = "", DAERAH = "", MUKIM = "", NO_HAKMILIK = "", NO_PTLOT = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
				ID_HM = rs.getString(2) == null ? "" : rs.getString(2);
				NO_FAIL = rs.getString(3) == null ? "" : rs.getString(3);
				NEGERI = rs.getString(4) == null ? "" : rs.getString(4);
				DAERAH = rs.getString(5) == null ? "" : rs.getString(5);
				MUKIM = rs.getString(6) == null ? "" : rs.getString(6);
				NO_HAKMILIK = rs.getString(7) == null ? "" : rs.getString(7);
				NO_PTLOT = rs.getString(8) == null ? "" : rs.getString(8);
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPermohonan", ID_PERMOHONAN);
				h.put("IDHM", ID_HM);
				h.put("NoFail", NO_FAIL);
				h.put("Negeri", NEGERI);
				h.put("Daerah", DAERAH);
				h.put("Mukim", MUKIM);
				h.put("NoHakmilik", NO_HAKMILIK);
				h.put("NoPTLot", NO_PTLOT);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean hasMaklumat(String ID_PERMOHONAN, String ID_HM) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();			
			String sql = "SELECT ID_JPPHSEWAAN FROM TBLINTJPPHSEWAAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HM = " + ID_HM;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = true;
			}
			rs.close();
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
			if (!"".equalsIgnoreCase(idPermohonan)) {
				String sql = "";
				Boolean haveINTData = false;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				
				String ID_HM = "", MP_NOFAIL = "", MP_NEGERI = "", MP_DAERAH = "", MP_UNITJKPTG = "", MP_NAMAPEGAWAIJKPTG = "", MP_NOTELJKPTG = "", MP_CAWANGANJKPTG = "";
				String MH_NEGERI = "", MH_DAERAH = "", MH_MUKIM = "", MH_SEKSYEN = "", MH_JENISHAKMILIK = "", MH_NOHAKMILIK = "", MH_NOPTLOT = "";
				String MH_JENISPTLOT = "", MH_JENISPEGANGAN = "", MH_TARIKHLUPUTPAJAKAN = "", MH_TEMPOHPAJAKAN = "", MH_ADAPELANTAPAK = "";
				String MH_KATEGORITANAH = "", MH_SYARATNYATA = "", MH_SEKATANKEPENTINGAN = "", MH_LUASASAL = "", MH_JENISLUASASAL = "";
				String MH_LUASDISEWA = "", MH_JENISLUASDISEWA = "", MH_STATUSPEMILIKAN = "", MH_TUJUANSEWAAN = "", MH_ALAMAT1HARTA = "";
				String MH_ALAMAT2HARTA = "", MH_ALAMAT3HARTA = "", MH_ALAMAT4HARTA = "", MH_NOLOTBERKAITAN = "", MH_CATATAN = "";
				String JPPH_TARIKHNILAIAN = "", JPPH_KADARBULAN = "", JPPH_KADARBULAN3TAHUN = "", JPPH_KADARTAHUNAN = "", JPPH_NAMAPEGAWAI = "";
				String JPPH_CAWANGAN = "", JPPH_CATATAN = "", EMAIL_ADDR1 = "", EMAIL_SEND = "", EMAIL_ADDR2 = "", EMAIL_ADDR3 = "";

				sql = "SELECT JPPH.ID_HM, FA.NO_FAIL, NG.NAMA_NEGERI, DA.NAMA_DAERAH, JPPH.UNIT_JKPTG, JPPH.NAMA_PEGAWAI_JKPTG, JPPH.NO_TEL_PEGAWAI_JKPTG, JPPH.CAWANGAN_JKPTG, JPPH.ID_NEGERI, JPPH.ID_DAERAH, JPPH.ID_MUKIM, JPPH.SEKSYEN, JPPH.JENIS_HAKMILIK, JPPH.NO_HAKMILIK, JPPH.NO_PTLOT, " +
					"JPPH.JENIS_PTLOT, JPPH.JENIS_PEGANGAN, JPPH.TARIKH_LUPUT_PAJAKAN, JPPH.TEMPOH_PAJAKAN, JPPH.ADA_PELAN_TAPAK, JPPH.KATEGORI_TANAH, JPPH.SYARAT_NYATA, JPPH.SEKATAN_KEPENTINGAN, JPPH.LUAS_ASAL, JPPH.JENIS_LUAS_ASAL, " +
					"JPPH.LUAS_DISEWA, JPPH.JENIS_LUAS_DISEWA, JPPH.STATUS_PEMILIKAN, JPPH.TUJUAN_SEWAAN, JPPH.ALAMAT1_HARTA, JPPH.ALAMAT2_HARTA, JPPH.ALAMAT3_HARTA, JPPH.ALAMAT4_HARTA, JPPH.NO_LOT_BERKAITAN, JPPH.CATATAN, " +
					"JPPH.JPPH_TARIKH_NILAIAN, JPPH.JPPH_KADAR_BULAN, JPPH.JPPH_KADAR_BULAN_3_TAHUN, JPPH.JPPH_KADAR_TAHUNAN, JPPH.JPPH_NAMA_PEGAWAI, JPPH.JPPH_CAWANGAN, JPPH.JPPH_CATATAN, JPPH.EMAIL_ADDR_PEGAWAI_JPPH1, JPPH.EMAIL_SEND_JPPH, " +
					"JPPH.EMAIL_ADDR_PEGAWAI_JPPH2, JPPH.EMAIL_ADDR_PEGAWAI_JPPH3 " +
					"FROM TBLINTJPPHSEWAAN JPPH, TBLPERMOHONAN M, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
					"WHERE JPPH.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_FAIL = FA.ID_FAIL AND FA.ID_NEGERI = NG.ID_NEGERI(+) AND FA.ID_DAERAH = DA.ID_DAERAH(+) " +
					"AND JPPH.ID_PERMOHONAN = " + idPermohonan;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ID_HM = rs.getString(1) == null ? "" : rs.getString(1);
					MP_NOFAIL = rs.getString(2) == null ? "" : rs.getString(2);
					MP_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
					MP_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
					MP_UNITJKPTG = rs.getString(5) == null ? "" : rs.getString(5);
					MP_NAMAPEGAWAIJKPTG = rs.getString(6) == null ? "" : rs.getString(6);
					MP_NOTELJKPTG = rs.getString(7) == null ? "" : rs.getString(7);
					MP_CAWANGANJKPTG = rs.getString(8) == null ? "" : rs.getString(8);
					MH_NEGERI = rs.getString(9) == null ? "" : rs.getString(9);
					MH_DAERAH = rs.getString(10) == null ? "" : rs.getString(10);
					MH_MUKIM = rs.getString(11) == null ? "" : rs.getString(11);
					MH_SEKSYEN = rs.getString(12) == null ? "" : rs.getString(12);
					MH_JENISHAKMILIK = rs.getString(13) == null ? "" : rs.getString(13);
					MH_NOHAKMILIK = rs.getString(14) == null ? "" : rs.getString(14);
					MH_NOPTLOT = rs.getString(15) == null ? "" : rs.getString(15);
					MH_JENISPTLOT = rs.getString(16) == null ? "" : rs.getString(16);
					MH_JENISPEGANGAN = rs.getString(17) == null ? "" : rs.getString(17);
					MH_TARIKHLUPUTPAJAKAN = rs.getDate(18) == null ? "" : sdf.format(rs.getDate(18));
					MH_TEMPOHPAJAKAN = rs.getString(19) == null ? "" : rs.getString(19);
					MH_ADAPELANTAPAK = rs.getString(20) == null ? "" : rs.getString(20);
					MH_KATEGORITANAH = rs.getString(21) == null ? "" : rs.getString(21);
					MH_SYARATNYATA = rs.getString(22) == null ? "" : rs.getString(22);
					MH_SEKATANKEPENTINGAN = rs.getString(23) == null ? "" : rs.getString(23);
					MH_LUASASAL = rs.getString(24) == null ? "" : rs.getString(24);
					MH_JENISLUASASAL = rs.getString(25) == null ? "" : rs.getString(25);
					MH_LUASDISEWA = rs.getString(26) == null ? "" : rs.getString(26);
					MH_JENISLUASDISEWA = rs.getString(27) == null ? "" : rs.getString(27);
					MH_STATUSPEMILIKAN = rs.getString(28) == null ? "" : rs.getString(28);
					MH_TUJUANSEWAAN = rs.getString(29) == null ? "" : rs.getString(29);
					MH_ALAMAT1HARTA = rs.getString(30) == null ? "" : rs.getString(30);
					MH_ALAMAT2HARTA = rs.getString(31) == null ? "" : rs.getString(31);
					MH_ALAMAT3HARTA = rs.getString(32) == null ? "" : rs.getString(32);
					MH_ALAMAT4HARTA = rs.getString(33) == null ? "" : rs.getString(33);
					MH_NOLOTBERKAITAN = rs.getString(34) == null ? "" : rs.getString(34);
					MH_CATATAN = rs.getString(35) == null ? "" : rs.getString(35);
					JPPH_TARIKHNILAIAN = rs.getDate(36) == null ? "" : sdf.format(rs.getDate(36));
					JPPH_KADARBULAN = rs.getString(37) == null ? "" : rs.getString(37);
					JPPH_KADARBULAN3TAHUN = rs.getString(38) == null ? "" : rs.getString(38);
					JPPH_KADARTAHUNAN = rs.getString(39) == null ? "" : rs.getString(39);
					JPPH_NAMAPEGAWAI = rs.getString(40) == null ? "" : rs.getString(40);
					JPPH_CAWANGAN = rs.getString(41) == null ? "" : rs.getString(41);
					JPPH_CATATAN = rs.getString(42) == null ? "" : rs.getString(42);
					EMAIL_ADDR1 = rs.getString(43) == null ? "" : rs.getString(43);
					EMAIL_SEND = rs.getString(44) == null ? "" : rs.getString(44);
					EMAIL_ADDR2 = rs.getString(45) == null ? "" : rs.getString(45);
					EMAIL_ADDR3 = rs.getString(46) == null ? "" : rs.getString(46);
				}
				
				if (!haveINTData) {
					sql = "SELECT HTPHM.ID_HAKMILIK, FA.NO_FAIL, NG.NAMA_NEGERI, DA.NAMA_DAERAH, '', '', '', '', HTPHM.ID_NEGERI, HTPHM.ID_DAERAH, HTPHM.ID_MUKIM, '', '', HTPHM.NO_HAKMILIK, HTPHM.NO_LOT, " +
						"'', '', HTPHM.TARIKH_LUPUT, HTPHM.TEMPOH, '', '', '', '', HTPHM.LUAS, '', '', '', '', '', '', '', '', '', '', '', " +
						"'', '', '', '', '', '', '', '', '', '', '' " +
						"FROM TBLPERMOHONAN M, TBLPHPHAKMILIKPERMOHONAN PHPHMPMHN, TBLHTPHAKMILIKAGENSI HTPHMAGENSI, TBLHTPHAKMILIK HTPHM, TBLPFDFAIL FA, TBLRUJNEGERI NG, TBLRUJDAERAH DA " +
						"WHERE M.ID_PERMOHONAN = PHPHMPMHN.ID_PERMOHONAN AND PHPHMPMHN.ID_HAKMILIKAGENSI = HTPHMAGENSI.ID_HAKMILIKAGENSI AND HTPHMAGENSI.ID_HAKMILIK = HTPHM.ID_HAKMILIK "  +
						"AND M.ID_FAIL = FA.ID_FAIL AND FA.ID_NEGERI = NG.ID_NEGERI(+) AND FA.ID_DAERAH = DA.ID_DAERAH(+) " +
						"AND M.ID_PERMOHONAN = " + idPermohonan;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_HM = rs.getString(1) == null ? "" : rs.getString(1);
						MP_NOFAIL = rs.getString(2) == null ? "" : rs.getString(2);
						MP_NEGERI = rs.getString(3) == null ? "" : rs.getString(3);
						MP_DAERAH = rs.getString(4) == null ? "" : rs.getString(4);
						MP_UNITJKPTG = rs.getString(5) == null ? "" : rs.getString(5);
						MP_NAMAPEGAWAIJKPTG = rs.getString(6) == null ? "" : rs.getString(6);
						MP_NOTELJKPTG = rs.getString(7) == null ? "" : rs.getString(7);
						MP_CAWANGANJKPTG = rs.getString(8) == null ? "" : rs.getString(8);
						MH_NEGERI = rs.getString(9) == null ? "" : rs.getString(9);
						MH_DAERAH = rs.getString(10) == null ? "" : rs.getString(10);
						MH_MUKIM = rs.getString(11) == null ? "" : rs.getString(11);
						MH_SEKSYEN = rs.getString(12) == null ? "" : rs.getString(12);
						MH_JENISHAKMILIK = rs.getString(13) == null ? "" : rs.getString(13);
						MH_NOHAKMILIK = rs.getString(14) == null ? "" : rs.getString(14);
						MH_NOPTLOT = rs.getString(15) == null ? "" : rs.getString(15);
						MH_JENISPTLOT = rs.getString(16) == null ? "" : rs.getString(16);
						MH_JENISPEGANGAN = rs.getString(17) == null ? "" : rs.getString(17);
						MH_TARIKHLUPUTPAJAKAN = rs.getString(18) == null ? "" : rs.getString(18);
						MH_TEMPOHPAJAKAN = rs.getString(19) == null ? "" : rs.getString(19);
						MH_ADAPELANTAPAK = rs.getString(20) == null ? "" : rs.getString(20);
						MH_KATEGORITANAH = rs.getString(21) == null ? "" : rs.getString(21);
						MH_SYARATNYATA = rs.getString(22) == null ? "" : rs.getString(22);
						MH_SEKATANKEPENTINGAN = rs.getString(23) == null ? "" : rs.getString(23);
						MH_LUASASAL = rs.getString(24) == null ? "" : rs.getString(24);
						MH_JENISLUASASAL = rs.getString(25) == null ? "" : rs.getString(25);
						MH_LUASDISEWA = rs.getString(26) == null ? "" : rs.getString(26);
						MH_JENISLUASDISEWA = rs.getString(27) == null ? "" : rs.getString(27);
						MH_STATUSPEMILIKAN = rs.getString(28) == null ? "" : rs.getString(28);
						MH_TUJUANSEWAAN = rs.getString(29) == null ? "" : rs.getString(29);
						MH_ALAMAT1HARTA = rs.getString(30) == null ? "" : rs.getString(30);
						MH_ALAMAT2HARTA = rs.getString(31) == null ? "" : rs.getString(31);
						MH_ALAMAT3HARTA = rs.getString(32) == null ? "" : rs.getString(32);
						MH_ALAMAT4HARTA = rs.getString(33) == null ? "" : rs.getString(33);
						MH_NOLOTBERKAITAN = rs.getString(34) == null ? "" : rs.getString(34);
						MH_CATATAN = rs.getString(35) == null ? "" : rs.getString(35);
						JPPH_TARIKHNILAIAN = rs.getDate(36) == null ? "" : sdf.format(rs.getDate(36));
						JPPH_KADARBULAN = rs.getString(37) == null ? "" : rs.getString(37);
						JPPH_KADARBULAN3TAHUN = rs.getString(38) == null ? "" : rs.getString(38);
						JPPH_KADARTAHUNAN = rs.getString(39) == null ? "" : rs.getString(39);
						JPPH_NAMAPEGAWAI = rs.getString(40) == null ? "" : rs.getString(40);
						JPPH_CAWANGAN = rs.getString(41) == null ? "" : rs.getString(41);
						JPPH_CATATAN = rs.getString(42) == null ? "" : rs.getString(42);
						EMAIL_ADDR1 = rs.getString(43) == null ? "" : rs.getString(43);
						EMAIL_SEND = rs.getString(44) == null ? "" : rs.getString(44);
						EMAIL_ADDR2 = rs.getString(45) == null ? "" : rs.getString(45);
						EMAIL_ADDR3 = rs.getString(46) == null ? "" : rs.getString(46);
					}
				}
				h = new Hashtable();
				h.put("ID_HM", ID_HM);
				h.put("MP_NOFAIL", MP_NOFAIL);
				h.put("MP_NEGERI", MP_NEGERI);
				h.put("MP_DAERAH", MP_DAERAH);
				h.put("MP_UNITJKPTG", MP_UNITJKPTG);
				h.put("MP_NAMAPEGAWAIJKPTG", MP_NAMAPEGAWAIJKPTG);
				h.put("MP_NOTELJKPTG", MP_NOTELJKPTG);
				h.put("MP_CAWANGANJKPTG", MP_CAWANGANJKPTG);
				h.put("MH_NEGERI", MH_NEGERI);
				h.put("MH_DAERAH", MH_DAERAH);
				h.put("MH_MUKIM", MH_MUKIM);
				h.put("MH_SEKSYEN", MH_SEKSYEN);
				h.put("MH_JENISHAKMILIK", MH_JENISHAKMILIK);
				h.put("MH_NOHAKMILIK", MH_NOHAKMILIK);
				h.put("MH_NOPTLOT", MH_NOPTLOT);
				h.put("MH_JENISPTLOT", MH_JENISPTLOT);
				h.put("MH_JENISPEGANGAN", MH_JENISPEGANGAN);
				h.put("MH_TARIKHLUPUTPAJAKAN", MH_TARIKHLUPUTPAJAKAN);
				h.put("MH_TEMPOHPAJAKAN", MH_TEMPOHPAJAKAN);
				h.put("MH_ADAPELANTAPAK", MH_ADAPELANTAPAK);
				h.put("MH_KATEGORITANAH", MH_KATEGORITANAH);
				h.put("MH_SYARATNYATA", MH_SYARATNYATA);
				h.put("MH_SEKATANKEPENTINGAN", MH_SEKATANKEPENTINGAN);
				h.put("MH_LUASASAL", MH_LUASASAL);
				h.put("MH_JENISLUASASAL", MH_JENISLUASASAL);
				h.put("MH_LUASDISEWA", MH_LUASDISEWA);
				h.put("MH_JENISLUASDISEWA", MH_JENISLUASDISEWA);
				h.put("MH_STATUSPEMILIKAN", MH_STATUSPEMILIKAN);
				h.put("MH_TUJUANSEWAAN", MH_TUJUANSEWAAN);
				h.put("MH_ALAMAT1HARTA", MH_ALAMAT1HARTA);
				h.put("MH_ALAMAT2HARTA", MH_ALAMAT2HARTA);
				h.put("MH_ALAMAT3HARTA", MH_ALAMAT3HARTA);
				h.put("MH_ALAMAT4HARTA", MH_ALAMAT4HARTA);
				h.put("MH_NOLOTBERKAITAN", MH_NOLOTBERKAITAN);
				h.put("MH_CATATAN", MH_CATATAN);
				h.put("JPPH_TARIKHNILAIAN", JPPH_TARIKHNILAIAN);
				h.put("JPPH_KADARBULAN", JPPH_KADARBULAN);
				h.put("JPPH_KADARBULAN3TAHUN", JPPH_KADARBULAN3TAHUN);
				h.put("JPPH_KADARTAHUNAN", JPPH_KADARTAHUNAN);
				h.put("JPPH_NAMAPEGAWAI", JPPH_NAMAPEGAWAI);
				h.put("JPPH_CAWANGAN", JPPH_CAWANGAN);
				h.put("JPPH_CATATAN", JPPH_CATATAN);
				h.put("EMAIL_ADDR1", EMAIL_ADDR1);
				h.put("EMAIL_ADDR2", EMAIL_ADDR2);
				h.put("EMAIL_ADDR3", EMAIL_ADDR3);
				h.put("EMAIL_SEND", EMAIL_SEND);
				v.add(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	public Boolean saveMaklumat(String ID_USER, Boolean isJPPHUser) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(idPermohonan)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				// check in TBLINTJPPHSEWAAN
				r.clear();
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", idPermohonan);
				sql = r.getSQLSelect("TBLINTJPPHSEWAAN");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
				
				r.clear();
				if (haveData) {
					r.update("ID_PERMOHONAN", idPermohonan);
				} else {
					if (!isJPPHUser) {
						r.add("ID_SIMPAN", ID_USER);
						r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
						r.add("ID_PERMOHONAN", idPermohonan);
					}
				}
				if (isJPPHUser) {
					r.add("STATUS_PROSES", "DALAM PROSES JPPH");
					r.add("ID_KEMASKINI_JPPH", ID_USER);
					r.add("TARIKH_KEMASKINI_JPPH", r.unquote("SYSDATE"));
					r.add("JPPH_TARIKH_NILAIAN", jpphTarikhNilaian);
					r.add("JPPH_KADAR_BULAN", jpphKadarSewaBulanan);
					r.add("JPPH_KADAR_BULAN_3_TAHUN", jpphKadarSewaBulanan3Tahun);
					r.add("JPPH_KADAR_TAHUNAN", jpphKadarSewaTahunan);
					r.add("JPPH_NAMA_PEGAWAI", jpphNamaPegawai);
					r.add("JPPH_CAWANGAN", jpphCawangan);
					r.add("JPPH_CATATAN", jpphCatatan);
				} else {
					r.add("ID_HM", idHM);
					r.add("STATUS_PROSES", "BARU");
					r.add("ID_KEMASKINI", ID_USER);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));					
					r.add("UNIT_JKPTG", unitJKPTG);
					r.add("NAMA_PEGAWAI_JKPTG", namaPegawaiJKPTG);
					r.add("NO_TEL_PEGAWAI_JKPTG", noTelPegawaiJKPTG);
					r.add("CAWANGAN_JKPTG", cawanganJKPTG);
					r.add("ID_NEGERI", idNegeri);
					r.add("ID_DAERAH", idDaerah);
					r.add("ID_MUKIM", idMukim);
					r.add("SEKSYEN", seksyen);
					r.add("JENIS_HAKMILIK", jenisHakmilik);
					r.add("NO_HAKMILIK", noHakmilik);
					r.add("NO_PTLOT", noPTLot);
					r.add("JENIS_PTLOT", jenisPTLot);
					r.add("JENIS_PEGANGAN", jenisPegangan);
					r.add("TARIKH_LUPUT_PAJAKAN", tarikhLuputPajakan);
					r.add("TEMPOH_PAJAKAN", tempohPajakan);
					r.add("ADA_PELAN_TAPAK", adaPelanTapak);
					r.add("KATEGORI_TANAH", kategoriTanah);
					r.add("SYARAT_NYATA", syaratNyata);
					r.add("SEKATAN_KEPENTINGAN", sekatanKepentingan);
					r.add("LUAS_ASAL", luasAsal);
					r.add("JENIS_LUAS_ASAL", jenisLuasAsal);
					r.add("LUAS_DISEWA", luasDisewa);
					r.add("JENIS_LUAS_DISEWA", jenisLuasDisewa);
					r.add("STATUS_PEMILIKAN", statusPemilikan);
					r.add("TUJUAN_SEWAAN", tujuanSewaan);
					r.add("ALAMAT1_HARTA", alamatHarta1);
					r.add("ALAMAT2_HARTA", alamatHarta2);
					r.add("ALAMAT3_HARTA", alamatHarta3);
					r.add("ALAMAT4_HARTA", alamatHarta4);
					r.add("NO_LOT_BERKAITAN", noLotBerkaitan);
					r.add("CATATAN", catatan);
					r.add("EMAIL_ADDR_PEGAWAI_JPPH1", emailAddr1);
					r.add("EMAIL_ADDR_PEGAWAI_JPPH2", emailAddr2);
					r.add("EMAIL_ADDR_PEGAWAI_JPPH3", emailAddr3);
					r.add("EMAIL_SEND_JPPH", emailSend);
				}

				if (haveData) {
					sql = r.getSQLUpdate("TBLINTJPPHSEWAAN");
				} else {
					idDB = DB.getNextID("TBLINTJPPHSEWAAN_SEQ");
					r.add("ID_JPPHSEWAAN", idDB);
					sql = r.getSQLInsert("TBLINTJPPHSEWAAN");
				}
				stmt.execute(sql);

				returnValue = true;
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean sendMaklumat(String ID_USER, Boolean isJPPHUser) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(idPermohonan)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				// check in TBLINTJPPHSEWAAN
				r.clear();
				r.add("ID_PERMOHONAN");
				r.add("ID_PERMOHONAN", idPermohonan);
				sql = r.getSQLSelect("TBLINTJPPHSEWAAN");
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				rs.close();
				
				r.clear();
				if (haveData) {
					String STATUS = "";
					r.update("ID_PERMOHONAN", idPermohonan);
					if (isJPPHUser) {
						STATUS = "SELESAI";
					} else {
						STATUS = "BARU";
					}
					r.add("STATUS_PROSES", STATUS);
					sql = r.getSQLUpdate("TBLINTJPPHSEWAAN");
					stmt.execute(sql);
					
					// ****************************
					// sending e-mail codes
					// note: if is JPPH user, send to last JKPTG user who sent the file to JPPH
					//       if is JKPTG user, send to JPPH user according to email address specified (if checkbox to send email is ticked)
					

					String NO_FAIL = "";
					sql = "SELECT FA.NO_FAIL " +
						"FROM TBLINTJPPHSEWAAN JPPH, TBLPERMOHONAN M, TBLPFDFAIL FA " +
						"WHERE JPPH.ID_PERMOHONAN = M.ID_PERMOHONAN AND M.ID_FAIL = FA.ID_FAIL " +
						"AND JPPH.ID_PERMOHONAN = " + idPermohonan;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						NO_FAIL = rs.getString(1) == null ? "" : rs.getString(1);
					}					
					
					String EMAIL_ADDR[];
					if (isJPPHUser) {
						EMAIL_ADDR = new String[1];
						sql = "SELECT U.EMEL " +
							"FROM TBLINTJPPHSEWAAN JPPH, USERS_INTERNAL U " +
							"WHERE JPPH.ID_KEMASKINI_JPPH = U.USER_ID " +
							"AND JPPH.ID_PERMOHONAN = " + idPermohonan;
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							EMAIL_ADDR[0] = rs.getString(1) == null ? "" : rs.getString(1);
						}
					} else {
						EMAIL_ADDR = new String[3];
						String EMAIL_SEND = "";
						EMAIL_SEND = emailSend;
						if ("1".equalsIgnoreCase(EMAIL_SEND.trim())) {
							EMAIL_ADDR[0] = emailAddr1;
							if (!"".equalsIgnoreCase(emailAddr2.trim())) {
								EMAIL_ADDR[1] = emailAddr2; 
							}
							if (!"".equalsIgnoreCase(emailAddr3.trim())) {
								EMAIL_ADDR[2] = emailAddr3; 
							}
						}
					}
					
					//**************************
					// temporary hardcode
					//EMAIL_ADDR = "";
					//**************************
	
					if (EMAIL_ADDR.length > 0) {
						String EMAIL_FROM = "", EMAIL_HAVE_CC = "", EMAIL_CC_1 = "", EMAIL_CC_2 = "", EMAIL_SUBJECT = "", EMAIL_BODY = "";
						sql = "SELECT ALAMAT_DARI, TAJUK, KANDUNGAN, ADA_CC, ALAMAT_CC_1, ALAMAT_CC_2 FROM TBLINTEMAILMSG WHERE UPPER(KOD_AGENSI) = 'JPPH' AND STATUS = 1";
						rs = stmt.executeQuery(sql);
						if (rs.next()) {
							EMAIL_FROM = rs.getString(1) == null ? "" : rs.getString(1);
							EMAIL_SUBJECT = rs.getString(2) == null ? "" : rs.getString(2);
							EMAIL_BODY = rs.getString(3) == null ? "" : rs.getString(3);
							EMAIL_HAVE_CC = rs.getString(4) == null ? "" : rs.getString(4);
							EMAIL_CC_1 = rs.getString(5) == null ? "" : rs.getString(5);
							EMAIL_CC_2 = rs.getString(6) == null ? "" : rs.getString(6);
						}
	
						if (!"".equalsIgnoreCase(EMAIL_FROM.trim()) 
								&& !"".equalsIgnoreCase(EMAIL_SUBJECT.trim()) && !"".equalsIgnoreCase(EMAIL_BODY.trim())) {
							EMAIL_SUBJECT = EMAIL_SUBJECT.replace("[$NOFAIL]", NO_FAIL);
							if (isJPPHUser) {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JPPH");
							} else {
								EMAIL_BODY = EMAIL_BODY.replace("[$DARIPIHAK]", "JKPTG");
							}
							Date date = new Date();
							EMAIL_BODY = EMAIL_BODY.replace("[$JENISHARTA]", "Nilaian Sewaan Harta Tanah Persekutuan");
							EMAIL_BODY = EMAIL_BODY.replace("[$NOFAIL]", NO_FAIL);
							EMAIL_BODY = EMAIL_BODY.replace("[$STATUS_PROSES]", STATUS);
							EMAIL_BODY = EMAIL_BODY.replace("[$TARIKH_KEMASKINI]", sdf.format(date));
							XEkptgEmailSender email = XEkptgEmailSender.getInstance();
							email.FROM = EMAIL_FROM;
							if (isJPPHUser) {
								email.MULTIPLE_RECIEPIENT = new String[1];
								email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR[0];
							} else {
								email.MULTIPLE_RECIEPIENT = new String[3];
								email.MULTIPLE_RECIEPIENT[0] = EMAIL_ADDR[0];
								email.MULTIPLE_RECIEPIENT[1] = EMAIL_ADDR[1];
								email.MULTIPLE_RECIEPIENT[2] = EMAIL_ADDR[2];
							}
							if ("1".equalsIgnoreCase(EMAIL_HAVE_CC)) {
								if (!"".equalsIgnoreCase(EMAIL_CC_1) && !"".equalsIgnoreCase(EMAIL_CC_2)) {
									email.TO_CC = new String[2];
									email.TO_CC[0] = EMAIL_CC_1;
									email.TO_CC[1] = EMAIL_CC_2;
								} else {
									email.TO_CC = new String[1];
									if (!"".equalsIgnoreCase(EMAIL_CC_1)) {
										email.TO_CC[0] = EMAIL_CC_1;
									} else {
										email.TO_CC[0] = EMAIL_CC_2;
									}
								}
							}
							//email.RECIEPIENT = EMAIL_ADDR;
							email.SUBJECT = EMAIL_SUBJECT;
							email.MESSAGE = EMAIL_BODY;
							email.sendEmail();				
						}
					}
					// ****************************
					
					returnValue = true;
				}
			}			
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public Boolean returnMaklumat(String ID_USER) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();			
			String sql = "SELECT ID_JPPHSEWAAN FROM TBLINTJPPHSEWAAN WHERE ID_PERMOHONAN = " + idPermohonan + " AND ID_HM = " + idHM;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idDB = rs.getLong(1);
			}
			rs.close();
			
			if (idDB > 0) {
				SQLRenderer r = new SQLRenderer();
				r.add("ID_KEMASKINI_JPPH", ID_USER);
				r.add("TARIKH_KEMASKINI_JPPH", r.unquote("SYSDATE"));
				r.add("STATUS_PROSES", "DIKEMBALIKAN");
				r.add("JPPH_CATATAN", jpphCatatan);
				r.set("ID_JPPHSEWAAN", idDB);
				sql = r.getSQLUpdate("TBLINTJPPHSEWAAN");
				stmt.executeQuery(sql);
				returnValue = true;
			}
		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public Boolean cancelMaklumat() throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(idPermohonan) && !"".equalsIgnoreCase(idHM)) {
				String sql = "";
				Statement stmt = db.getStatement();
				
				sql = "DELETE FROM TBLINTJPPHSEWAAN WHERE ID_PERMOHONAN = " + idPermohonan + " AND ID_HM = " + idHM;
				stmt.execute(sql);
			}
		} finally {
			if (db != null)
				db.close();			
		}
		return returnValue;
	}
	
	public Boolean isCompleted(String ID_PERMOHONAN, String ID_HM) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String STATUS_PROSES = "";
			Statement stmt = db.getStatement();			
			String sql = "SELECT STATUS_PROSES FROM TBLINTJPPHSEWAAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN + " AND ID_HM = " + ID_HM;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				STATUS_PROSES = rs.getString(1) == null ? "" : rs.getString(1);
			}
			rs.close();
			
			if ("SELESAI".equalsIgnoreCase(STATUS_PROSES.trim().toUpperCase())) {
				returnValue = true;
			}
		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector listUlasanOnline(String ID_PERMOHONAN) throws Exception {
		Vector v = new Vector();
		Hashtable h = null;
		Db db = new Db();
		
		try {
			String tarikhKemaskini = "", status = "";
			String sql = "";
			int i = 1;
			Statement stmt = db.getStatement();
			sql = "SELECT TO_CHAR(TARIKH_KEMASKINI, 'DD/MM/YYYY'), STATUS_PROSES FROM TBLINTJPPHSEWAAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tarikhKemaskini = rs.getString(1) == null ? "" : rs.getString(1);
				status = rs.getString(2) == null ? "" : rs.getString(2);
				h = new Hashtable();
				h.put("bil", i);
				h.put("tarikhKemaskini", tarikhKemaskini);
				h.put("status", status);
				v.add(h);
				i++;
			}
			rs.close();

		} catch (Exception ex) {
			
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	public String SelectJenisPTLot(String SOC_NAME) throws Exception {
		return SelectJenisPTLot(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, "", "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED) throws Exception {
		return SelectJenisPTLot(SOC_NAME, SELECTED_PTLOT, DISABLED, "");
	}
	public String SelectJenisPTLot(String SOC_NAME, long SELECTED_PTLOT, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PTLOT == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - LOT</option>\r\n";
				returnValue += "  <option value=\"2\">02 - PT</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - LOT</option>\r\n";
				if (SELECTED_PTLOT == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - PT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - PT</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectJenisNoBangunan(String SOC_NAME) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, "", "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED) throws Exception {
		return SelectJenisNoBangunan(SOC_NAME, SELECTED_NOBANGUNAN, DISABLED, "");
	}
	public String SelectJenisNoBangunan(String SOC_NAME, long SELECTED_NOBANGUNAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_NOBANGUNAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - BANGUNAN</option>\r\n";
				returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
				returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - BANGUNAN</option>\r\n";
				if (SELECTED_NOBANGUNAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - TINGKAT</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - TINGKAT</option>\r\n";
					if (SELECTED_NOBANGUNAN == 3) {
						returnValue += "  <option value=\"3\" selected=\"selected\">03 - STRATA</option>\r\n";
					} else {
						returnValue += "  <option value=\"3\">03 - STRATA</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectJenisPegangan(String SOC_NAME) throws Exception {
		return SelectJenisPegangan(SOC_NAME, 0, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, "", "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED) throws Exception {
		return SelectJenisPegangan(SOC_NAME, SELECTED_PEGANGAN, DISABLED, "");
	}
	public String SelectJenisPegangan(String SOC_NAME, long SELECTED_PEGANGAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		
		try {
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			if (SELECTED_PEGANGAN == 1) {
				returnValue += "  <option value=\"1\" selected=\"selected\">01 - KEKAL</option>\r\n";
				returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
			} else {
				returnValue += "  <option value=\"1\">01 - KEKAL</option>\r\n";
				if (SELECTED_PEGANGAN == 2) {
					returnValue += "  <option value=\"2\" selected=\"selected\">02 - PAJAKAN</option>\r\n";
				} else {
					returnValue += "  <option value=\"2\">02 - PAJAKAN</option>\r\n";
				}
			}
			returnValue += "</select>\r\n";
		} finally {

		}
		return returnValue;
	}	

	public String SelectKategoriTanah(String SOC_NAME) throws Exception {
		return SelectKategoriTanah(SOC_NAME, 0, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, "", "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED) throws Exception {
		return SelectKategoriTanah(SOC_NAME, SELECTED_KATEGORI, DISABLED, "");
	}
	public String SelectKategoriTanah(String SOC_NAME, long SELECTED_KATEGORI, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + ">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_KATEGORI, KOD_KATEGORI, KETERANGAN FROM TBLRUJKATEGORI " +
				SQL_SEARCH + "ORDER BY KOD_KATEGORI, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_KATEGORI == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	
	
	public String SelectJenisLuasTanah(String SOC_NAME) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, 0, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, "", "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED) throws Exception {
		return SelectJenisLuasTanah(SOC_NAME, SELECTED_LUASTANAH, DISABLED, "");
	}
	public String SelectJenisLuasTanah(String SOC_NAME, long SELECTED_LUASTANAH, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_LUAS, KOD_LUAS, KETERANGAN FROM TBLRUJLUAS " +
				SQL_SEARCH + "ORDER BY KOD_LUAS, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_LUASTANAH == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}	

	public String SelectSyaratNyata(String SOC_NAME) throws Exception {
		return SelectSyaratNyata(SOC_NAME, 0, "", "");
	}
	public String SelectSyaratNyata(String SOC_NAME, long SELECTED_SYARATNYATA) throws Exception {
		return SelectSyaratNyata(SOC_NAME, SELECTED_SYARATNYATA, "", "");
	}
	public String SelectSyaratNyata(String SOC_NAME, long SELECTED_SYARATNYATA, String DISABLED) throws Exception {
		return SelectSyaratNyata(SOC_NAME, SELECTED_SYARATNYATA, DISABLED, "");
	}
	public String SelectSyaratNyata(String SOC_NAME, long SELECTED_SYARATNYATA, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_SYARATNYATA, KOD_JPPH, KETERANGAN FROM TBLINTRUJSYARATNYATA " +
				SQL_SEARCH + "ORDER BY KOD_JPPH, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_SYARATNYATA == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	public String SelectSekatanKepentingan(String SOC_NAME) throws Exception {
		return SelectSekatanKepentingan(SOC_NAME, 0, "", "");
	}
	public String SelectSekatanKepentingan(String SOC_NAME, long SELECTED_SEKATANKEPENTINGAN) throws Exception {
		return SelectSekatanKepentingan(SOC_NAME, SELECTED_SEKATANKEPENTINGAN, "", "");
	}
	public String SelectSekatanKepentingan(String SOC_NAME, long SELECTED_SEKATANKEPENTINGAN, String DISABLED) throws Exception {
		return SelectSekatanKepentingan(SOC_NAME, SELECTED_SEKATANKEPENTINGAN, DISABLED, "");
	}
	public String SelectSekatanKepentingan(String SOC_NAME, long SELECTED_SEKATANKEPENTINGAN, String DISABLED, String ONCHANGE) throws Exception {
		String returnValue = "";
		Db db = new Db();
		String sql = "";
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String SQL_SEARCH = "";
			
			returnValue += "<select id=\"" + SOC_NAME + "\" name=\"" + SOC_NAME + "\" " + DISABLED + ONCHANGE + " style=\"width:auto\">\r\n";
			returnValue += "  <option value=\"\">SILA PILIH</option>\r\n";
			long ID_DATA = 0;
			String KOD = "", KETERANGAN = "";
			sql = "SELECT ID_SEKATANKPTGN, KOD_JPPH, KETERANGAN FROM TBLINTRUJSEKATANKPTGN " +
				SQL_SEARCH + "ORDER BY KOD_JPPH, KETERANGAN";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_DATA = rs.getInt(1);
				KOD = rs.getString(2) == null ? "" : rs.getString(2);
				KETERANGAN = rs.getString(3) == null ? "" : rs.getString(3);
				if (ID_DATA > 0) {
					if (SELECTED_SEKATANKEPENTINGAN == ID_DATA) {
						returnValue += "  <option value=\"" + ID_DATA + "\" selected=\"selected\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					} else {
						returnValue += "  <option value=\"" + ID_DATA + "\">" + KOD + " - " + KETERANGAN + "</option>\r\n";
					}
				}
			}
			returnValue += "</select>\r\n";
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
}