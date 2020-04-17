package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppthakmilikasal entity provides the base persistence definition of
 * the Tblppthakmilikasal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppthakmilikasal implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikasal;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idJenishakmilik;
	private String noHakmilik;
	private Long idSubjaket;
	private Date tarikhTerima;
	private Date tarikhDaftar;
	private String flagRezab;
	private String flagGsa;
	private String tempohLuput;
	private Date tarikhLuput;
	private String noPt;
	private Long idUnitluaspt;
	private String luasPt;
	private String noLot;
	private Long idUnitluaslot;
	private String luasLot;
	private Long idUnitluasambil;
	private Long luasAmbil;
	private Long idUnitluasbaru;
	private Long luasBaru;
	private String noPelan;
	private String noSyit;
	private String lokasi;
	private Long idKategoritanah;
	private String syaratNyata;
	private String syaratKhas;
	private String sekatanKepentingan;
	private String sekatanHak;
	private String jenisMilik;
	private String ulasanPentadbir;
	private String flagUbah;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private Long idPermohonan;
	private Long idHakmilik;
	private Long idPembatalan;
	private String noKelulusan;
	private String noDaftar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblppthakmilikasal() {
	}

	/** full constructor */
	public AbstractTblppthakmilikasal(Long idNegeri, Long idDaerah,
			Long idMukim, Long idJenishakmilik, String noHakmilik,
			Long idSubjaket, Date tarikhTerima, Date tarikhDaftar,
			String flagRezab, String flagGsa, String tempohLuput,
			Date tarikhLuput, String noPt, Long idUnitluaspt, String luasPt,
			String noLot, Long idUnitluaslot, String luasLot,
			Long idUnitluasambil, Long luasAmbil, Long idUnitluasbaru,
			Long luasBaru, String noPelan, String noSyit, String lokasi,
			Long idKategoritanah, String syaratNyata, String syaratKhas,
			String sekatanKepentingan, String sekatanHak, String jenisMilik,
			String ulasanPentadbir, String flagUbah, String noBangunan,
			String noTingkat, String noPetak, Long idPermohonan,
			Long idHakmilik, Long idPembatalan, String noKelulusan,
			String noDaftar, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idJenishakmilik = idJenishakmilik;
		this.noHakmilik = noHakmilik;
		this.idSubjaket = idSubjaket;
		this.tarikhTerima = tarikhTerima;
		this.tarikhDaftar = tarikhDaftar;
		this.flagRezab = flagRezab;
		this.flagGsa = flagGsa;
		this.tempohLuput = tempohLuput;
		this.tarikhLuput = tarikhLuput;
		this.noPt = noPt;
		this.idUnitluaspt = idUnitluaspt;
		this.luasPt = luasPt;
		this.noLot = noLot;
		this.idUnitluaslot = idUnitluaslot;
		this.luasLot = luasLot;
		this.idUnitluasambil = idUnitluasambil;
		this.luasAmbil = luasAmbil;
		this.idUnitluasbaru = idUnitluasbaru;
		this.luasBaru = luasBaru;
		this.noPelan = noPelan;
		this.noSyit = noSyit;
		this.lokasi = lokasi;
		this.idKategoritanah = idKategoritanah;
		this.syaratNyata = syaratNyata;
		this.syaratKhas = syaratKhas;
		this.sekatanKepentingan = sekatanKepentingan;
		this.sekatanHak = sekatanHak;
		this.jenisMilik = jenisMilik;
		this.ulasanPentadbir = ulasanPentadbir;
		this.flagUbah = flagUbah;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noPetak = noPetak;
		this.idPermohonan = idPermohonan;
		this.idHakmilik = idHakmilik;
		this.idPembatalan = idPembatalan;
		this.noKelulusan = noKelulusan;
		this.noDaftar = noDaftar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdHakmilikasal() {
		return this.idHakmilikasal;
	}

	public void setIdHakmilikasal(Long idHakmilikasal) {
		this.idHakmilikasal = idHakmilikasal;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public String getNoHakmilik() {
		return this.noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public Long getIdSubjaket() {
		return this.idSubjaket;
	}

	public void setIdSubjaket(Long idSubjaket) {
		this.idSubjaket = idSubjaket;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getFlagRezab() {
		return this.flagRezab;
	}

	public void setFlagRezab(String flagRezab) {
		this.flagRezab = flagRezab;
	}

	public String getFlagGsa() {
		return this.flagGsa;
	}

	public void setFlagGsa(String flagGsa) {
		this.flagGsa = flagGsa;
	}

	public String getTempohLuput() {
		return this.tempohLuput;
	}

	public void setTempohLuput(String tempohLuput) {
		this.tempohLuput = tempohLuput;
	}

	public Date getTarikhLuput() {
		return this.tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public String getNoPt() {
		return this.noPt;
	}

	public void setNoPt(String noPt) {
		this.noPt = noPt;
	}

	public Long getIdUnitluaspt() {
		return this.idUnitluaspt;
	}

	public void setIdUnitluaspt(Long idUnitluaspt) {
		this.idUnitluaspt = idUnitluaspt;
	}

	public String getLuasPt() {
		return this.luasPt;
	}

	public void setLuasPt(String luasPt) {
		this.luasPt = luasPt;
	}

	public String getNoLot() {
		return this.noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public Long getIdUnitluaslot() {
		return this.idUnitluaslot;
	}

	public void setIdUnitluaslot(Long idUnitluaslot) {
		this.idUnitluaslot = idUnitluaslot;
	}

	public String getLuasLot() {
		return this.luasLot;
	}

	public void setLuasLot(String luasLot) {
		this.luasLot = luasLot;
	}

	public Long getIdUnitluasambil() {
		return this.idUnitluasambil;
	}

	public void setIdUnitluasambil(Long idUnitluasambil) {
		this.idUnitluasambil = idUnitluasambil;
	}

	public Long getLuasAmbil() {
		return this.luasAmbil;
	}

	public void setLuasAmbil(Long luasAmbil) {
		this.luasAmbil = luasAmbil;
	}

	public Long getIdUnitluasbaru() {
		return this.idUnitluasbaru;
	}

	public void setIdUnitluasbaru(Long idUnitluasbaru) {
		this.idUnitluasbaru = idUnitluasbaru;
	}

	public Long getLuasBaru() {
		return this.luasBaru;
	}

	public void setLuasBaru(Long luasBaru) {
		this.luasBaru = luasBaru;
	}

	public String getNoPelan() {
		return this.noPelan;
	}

	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}

	public String getNoSyit() {
		return this.noSyit;
	}

	public void setNoSyit(String noSyit) {
		this.noSyit = noSyit;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public Long getIdKategoritanah() {
		return this.idKategoritanah;
	}

	public void setIdKategoritanah(Long idKategoritanah) {
		this.idKategoritanah = idKategoritanah;
	}

	public String getSyaratNyata() {
		return this.syaratNyata;
	}

	public void setSyaratNyata(String syaratNyata) {
		this.syaratNyata = syaratNyata;
	}

	public String getSyaratKhas() {
		return this.syaratKhas;
	}

	public void setSyaratKhas(String syaratKhas) {
		this.syaratKhas = syaratKhas;
	}

	public String getSekatanKepentingan() {
		return this.sekatanKepentingan;
	}

	public void setSekatanKepentingan(String sekatanKepentingan) {
		this.sekatanKepentingan = sekatanKepentingan;
	}

	public String getSekatanHak() {
		return this.sekatanHak;
	}

	public void setSekatanHak(String sekatanHak) {
		this.sekatanHak = sekatanHak;
	}

	public String getJenisMilik() {
		return this.jenisMilik;
	}

	public void setJenisMilik(String jenisMilik) {
		this.jenisMilik = jenisMilik;
	}

	public String getUlasanPentadbir() {
		return this.ulasanPentadbir;
	}

	public void setUlasanPentadbir(String ulasanPentadbir) {
		this.ulasanPentadbir = ulasanPentadbir;
	}

	public String getFlagUbah() {
		return this.flagUbah;
	}

	public void setFlagUbah(String flagUbah) {
		this.flagUbah = flagUbah;
	}

	public String getNoBangunan() {
		return this.noBangunan;
	}

	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}

	public String getNoTingkat() {
		return this.noTingkat;
	}

	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}

	public String getNoPetak() {
		return this.noPetak;
	}

	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Long getIdPembatalan() {
		return this.idPembatalan;
	}

	public void setIdPembatalan(Long idPembatalan) {
		this.idPembatalan = idPembatalan;
	}

	public String getNoKelulusan() {
		return this.noKelulusan;
	}

	public void setNoKelulusan(String noKelulusan) {
		this.noKelulusan = noKelulusan;
	}

	public String getNoDaftar() {
		return this.noDaftar;
	}

	public void setNoDaftar(String noDaftar) {
		this.noDaftar = noDaftar;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}