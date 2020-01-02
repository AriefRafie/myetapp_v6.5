package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpulasanjupem entity provides the base persistence definition of
 * the Tblphpulasanjupem entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanjupem implements java.io.Serializable {

	// Fields

	private Long idUlasanjupem;
	private Tblphpulasanteknikal tblphpulasanteknikal;
	private Long idJabatanteknikal;
	private String noRujukan;
	private Long idDokumen;
	private String ulasan;
	private Date tarikhHantar;
	private Long jangkamasa;
	private Date tarikhJangkaTerima;
	private Date tarikhSurat;
	private Date tarikhTerima;
	private String flagStatus;
	private Long bilHantar;
	private Long noVersi;
	private Long idUlasanringkasan;
	private String flagTerkini;
	private String flagBertindih;
	private Long idKedudukan;
	private String flagApb;
	private String ulasanPindaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppenyediaulasanjupems = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanjupem() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanjupem(Long idUlasanjupem) {
		this.idUlasanjupem = idUlasanjupem;
	}

	/** full constructor */
	public AbstractTblphpulasanjupem(Long idUlasanjupem,
			Tblphpulasanteknikal tblphpulasanteknikal, Long idJabatanteknikal,
			String noRujukan, Long idDokumen, String ulasan, Date tarikhHantar,
			Long jangkamasa, Date tarikhJangkaTerima, Date tarikhSurat,
			Date tarikhTerima, String flagStatus, Long bilHantar, Long noVersi,
			Long idUlasanringkasan, String flagTerkini, String flagBertindih,
			Long idKedudukan, String flagApb, String ulasanPindaan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppenyediaulasanjupems) {
		this.idUlasanjupem = idUlasanjupem;
		this.tblphpulasanteknikal = tblphpulasanteknikal;
		this.idJabatanteknikal = idJabatanteknikal;
		this.noRujukan = noRujukan;
		this.idDokumen = idDokumen;
		this.ulasan = ulasan;
		this.tarikhHantar = tarikhHantar;
		this.jangkamasa = jangkamasa;
		this.tarikhJangkaTerima = tarikhJangkaTerima;
		this.tarikhSurat = tarikhSurat;
		this.tarikhTerima = tarikhTerima;
		this.flagStatus = flagStatus;
		this.bilHantar = bilHantar;
		this.noVersi = noVersi;
		this.idUlasanringkasan = idUlasanringkasan;
		this.flagTerkini = flagTerkini;
		this.flagBertindih = flagBertindih;
		this.idKedudukan = idKedudukan;
		this.flagApb = flagApb;
		this.ulasanPindaan = ulasanPindaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppenyediaulasanjupems = tblphppenyediaulasanjupems;
	}

	// Property accessors

	public Long getIdUlasanjupem() {
		return this.idUlasanjupem;
	}

	public void setIdUlasanjupem(Long idUlasanjupem) {
		this.idUlasanjupem = idUlasanjupem;
	}

	public Tblphpulasanteknikal getTblphpulasanteknikal() {
		return this.tblphpulasanteknikal;
	}

	public void setTblphpulasanteknikal(
			Tblphpulasanteknikal tblphpulasanteknikal) {
		this.tblphpulasanteknikal = tblphpulasanteknikal;
	}

	public Long getIdJabatanteknikal() {
		return this.idJabatanteknikal;
	}

	public void setIdJabatanteknikal(Long idJabatanteknikal) {
		this.idJabatanteknikal = idJabatanteknikal;
	}

	public String getNoRujukan() {
		return this.noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Long getJangkamasa() {
		return this.jangkamasa;
	}

	public void setJangkamasa(Long jangkamasa) {
		this.jangkamasa = jangkamasa;
	}

	public Date getTarikhJangkaTerima() {
		return this.tarikhJangkaTerima;
	}

	public void setTarikhJangkaTerima(Date tarikhJangkaTerima) {
		this.tarikhJangkaTerima = tarikhJangkaTerima;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getFlagStatus() {
		return this.flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}

	public Long getBilHantar() {
		return this.bilHantar;
	}

	public void setBilHantar(Long bilHantar) {
		this.bilHantar = bilHantar;
	}

	public Long getNoVersi() {
		return this.noVersi;
	}

	public void setNoVersi(Long noVersi) {
		this.noVersi = noVersi;
	}

	public Long getIdUlasanringkasan() {
		return this.idUlasanringkasan;
	}

	public void setIdUlasanringkasan(Long idUlasanringkasan) {
		this.idUlasanringkasan = idUlasanringkasan;
	}

	public String getFlagTerkini() {
		return this.flagTerkini;
	}

	public void setFlagTerkini(String flagTerkini) {
		this.flagTerkini = flagTerkini;
	}

	public String getFlagBertindih() {
		return this.flagBertindih;
	}

	public void setFlagBertindih(String flagBertindih) {
		this.flagBertindih = flagBertindih;
	}

	public Long getIdKedudukan() {
		return this.idKedudukan;
	}

	public void setIdKedudukan(Long idKedudukan) {
		this.idKedudukan = idKedudukan;
	}

	public String getFlagApb() {
		return this.flagApb;
	}

	public void setFlagApb(String flagApb) {
		this.flagApb = flagApb;
	}

	public String getUlasanPindaan() {
		return this.ulasanPindaan;
	}

	public void setUlasanPindaan(String ulasanPindaan) {
		this.ulasanPindaan = ulasanPindaan;
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

	public Set getTblphppenyediaulasanjupems() {
		return this.tblphppenyediaulasanjupems;
	}

	public void setTblphppenyediaulasanjupems(Set tblphppenyediaulasanjupems) {
		this.tblphppenyediaulasanjupems = tblphppenyediaulasanjupems;
	}

}