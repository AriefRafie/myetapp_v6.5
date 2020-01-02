package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppthakmilikberikutpu entity provides the base persistence
 * definition of the Tblppthakmilikberikutpu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppthakmilikberikutpu implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikberikutpu;
	private Long idSubjaket;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idJenishakmilik;
	private String noHakmilik;
	private Date tarikhTerima;
	private Date tarikhDaftar;
	private String noPu;
	private String noPt;
	private String noLot;
	private Long idUnitluaslot;
	private String luasLot;
	private Long idUnitluaspt;
	private String luasPt;
	private Long idUnitluasbaru;
	private Long luasBaru;
	private String flagBatal;
	private String ulasan;
	private String flagPu;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private Long idHakmilik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idBorangk;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblppthakmilikberikutpu() {
	}

	/** full constructor */
	public AbstractTblppthakmilikberikutpu(Long idSubjaket, Long idNegeri,
			Long idDaerah, Long idMukim, Long idJenishakmilik,
			String noHakmilik, Date tarikhTerima, Date tarikhDaftar,
			String noPu, String noPt, String noLot, Long idUnitluaslot,
			String luasLot, Long idUnitluaspt, String luasPt,
			Long idUnitluasbaru, Long luasBaru, String flagBatal,
			String ulasan, String flagPu, String noBangunan, String noTingkat,
			String noPetak, Long idHakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idBorangk, Long idDb) {
		this.idSubjaket = idSubjaket;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idJenishakmilik = idJenishakmilik;
		this.noHakmilik = noHakmilik;
		this.tarikhTerima = tarikhTerima;
		this.tarikhDaftar = tarikhDaftar;
		this.noPu = noPu;
		this.noPt = noPt;
		this.noLot = noLot;
		this.idUnitluaslot = idUnitluaslot;
		this.luasLot = luasLot;
		this.idUnitluaspt = idUnitluaspt;
		this.luasPt = luasPt;
		this.idUnitluasbaru = idUnitluasbaru;
		this.luasBaru = luasBaru;
		this.flagBatal = flagBatal;
		this.ulasan = ulasan;
		this.flagPu = flagPu;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noPetak = noPetak;
		this.idHakmilik = idHakmilik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idBorangk = idBorangk;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdHakmilikberikutpu() {
		return this.idHakmilikberikutpu;
	}

	public void setIdHakmilikberikutpu(Long idHakmilikberikutpu) {
		this.idHakmilikberikutpu = idHakmilikberikutpu;
	}

	public Long getIdSubjaket() {
		return this.idSubjaket;
	}

	public void setIdSubjaket(Long idSubjaket) {
		this.idSubjaket = idSubjaket;
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

	public String getNoPu() {
		return this.noPu;
	}

	public void setNoPu(String noPu) {
		this.noPu = noPu;
	}

	public String getNoPt() {
		return this.noPt;
	}

	public void setNoPt(String noPt) {
		this.noPt = noPt;
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

	public String getFlagBatal() {
		return this.flagBatal;
	}

	public void setFlagBatal(String flagBatal) {
		this.flagBatal = flagBatal;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getFlagPu() {
		return this.flagPu;
	}

	public void setFlagPu(String flagPu) {
		this.flagPu = flagPu;
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

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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

	public Long getIdBorangk() {
		return this.idBorangk;
	}

	public void setIdBorangk(Long idBorangk) {
		this.idBorangk = idBorangk;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}