package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpulasanjpph entity provides the base persistence definition of
 * the Tblphpulasanjpph entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanjpph implements java.io.Serializable {

	// Fields

	private Long idUlasanjpph;
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
	private Double kadarSewaKesebulan;
	private Double kadarSewaBertempoh;
	private Double kadarNilaianJpph;
	private Double jumlahNilaianJpph;
	private Long tempohSahNilaian;
	private String ulasanPindaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppenyediaulasanjpphs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanjpph() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanjpph(Long idUlasanjpph) {
		this.idUlasanjpph = idUlasanjpph;
	}

	/** full constructor */
	public AbstractTblphpulasanjpph(Long idUlasanjpph,
			Tblphpulasanteknikal tblphpulasanteknikal, Long idJabatanteknikal,
			String noRujukan, Long idDokumen, String ulasan, Date tarikhHantar,
			Long jangkamasa, Date tarikhJangkaTerima, Date tarikhSurat,
			Date tarikhTerima, String flagStatus, Long bilHantar, Long noVersi,
			Long idUlasanringkasan, String flagTerkini,
			Double kadarSewaKesebulan, Double kadarSewaBertempoh,
			Double kadarNilaianJpph, Double jumlahNilaianJpph,
			Long tempohSahNilaian, String ulasanPindaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyediaulasanjpphs) {
		this.idUlasanjpph = idUlasanjpph;
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
		this.kadarSewaKesebulan = kadarSewaKesebulan;
		this.kadarSewaBertempoh = kadarSewaBertempoh;
		this.kadarNilaianJpph = kadarNilaianJpph;
		this.jumlahNilaianJpph = jumlahNilaianJpph;
		this.tempohSahNilaian = tempohSahNilaian;
		this.ulasanPindaan = ulasanPindaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppenyediaulasanjpphs = tblphppenyediaulasanjpphs;
	}

	// Property accessors

	public Long getIdUlasanjpph() {
		return this.idUlasanjpph;
	}

	public void setIdUlasanjpph(Long idUlasanjpph) {
		this.idUlasanjpph = idUlasanjpph;
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

	public Double getKadarSewaKesebulan() {
		return this.kadarSewaKesebulan;
	}

	public void setKadarSewaKesebulan(Double kadarSewaKesebulan) {
		this.kadarSewaKesebulan = kadarSewaKesebulan;
	}

	public Double getKadarSewaBertempoh() {
		return this.kadarSewaBertempoh;
	}

	public void setKadarSewaBertempoh(Double kadarSewaBertempoh) {
		this.kadarSewaBertempoh = kadarSewaBertempoh;
	}

	public Double getKadarNilaianJpph() {
		return this.kadarNilaianJpph;
	}

	public void setKadarNilaianJpph(Double kadarNilaianJpph) {
		this.kadarNilaianJpph = kadarNilaianJpph;
	}

	public Double getJumlahNilaianJpph() {
		return this.jumlahNilaianJpph;
	}

	public void setJumlahNilaianJpph(Double jumlahNilaianJpph) {
		this.jumlahNilaianJpph = jumlahNilaianJpph;
	}

	public Long getTempohSahNilaian() {
		return this.tempohSahNilaian;
	}

	public void setTempohSahNilaian(Long tempohSahNilaian) {
		this.tempohSahNilaian = tempohSahNilaian;
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

	public Set getTblphppenyediaulasanjpphs() {
		return this.tblphppenyediaulasanjpphs;
	}

	public void setTblphppenyediaulasanjpphs(Set tblphppenyediaulasanjpphs) {
		this.tblphppenyediaulasanjpphs = tblphppenyediaulasanjpphs;
	}

}