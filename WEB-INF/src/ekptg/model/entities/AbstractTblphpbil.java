package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpbil entity provides the base persistence definition of the
 * Tblphpbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbil implements java.io.Serializable {

	// Fields

	private Long idBil;
	private Tblphpbayaranperludibayar tblphpbayaranperludibayar;
	private Tblphppemegang tblphppemegang;
	private String noAkaun;
	private Double amaunSemasa;
	private Double amaunPerludibayar;
	private Double bakiAkhir;
	private Long bulanBil;
	private Long tahunBil;
	private String flagTangguh;
	private String flagBayar;
	private String flagBatal;
	private Long idBatal;
	private Date tarikhBatal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Double amaunTertunggak;

	// Constructors

	/** default constructor */
	public AbstractTblphpbil() {
	}

	/** minimal constructor */
	public AbstractTblphpbil(Long idBil) {
		this.idBil = idBil;
	}

	/** full constructor */
	public AbstractTblphpbil(Long idBil,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Tblphppemegang tblphppemegang, String noAkaun, Double amaunSemasa,
			Double amaunPerludibayar, Double bakiAkhir, Long bulanBil,
			Long tahunBil, String flagTangguh, String flagBayar,
			String flagBatal, Long idBatal, Date tarikhBatal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double amaunTertunggak) {
		this.idBil = idBil;
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
		this.tblphppemegang = tblphppemegang;
		this.noAkaun = noAkaun;
		this.amaunSemasa = amaunSemasa;
		this.amaunPerludibayar = amaunPerludibayar;
		this.bakiAkhir = bakiAkhir;
		this.bulanBil = bulanBil;
		this.tahunBil = tahunBil;
		this.flagTangguh = flagTangguh;
		this.flagBayar = flagBayar;
		this.flagBatal = flagBatal;
		this.idBatal = idBatal;
		this.tarikhBatal = tarikhBatal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.amaunTertunggak = amaunTertunggak;
	}

	// Property accessors

	public Long getIdBil() {
		return this.idBil;
	}

	public void setIdBil(Long idBil) {
		this.idBil = idBil;
	}

	public Tblphpbayaranperludibayar getTblphpbayaranperludibayar() {
		return this.tblphpbayaranperludibayar;
	}

	public void setTblphpbayaranperludibayar(
			Tblphpbayaranperludibayar tblphpbayaranperludibayar) {
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
	}

	public Tblphppemegang getTblphppemegang() {
		return this.tblphppemegang;
	}

	public void setTblphppemegang(Tblphppemegang tblphppemegang) {
		this.tblphppemegang = tblphppemegang;
	}

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public Double getAmaunSemasa() {
		return this.amaunSemasa;
	}

	public void setAmaunSemasa(Double amaunSemasa) {
		this.amaunSemasa = amaunSemasa;
	}

	public Double getAmaunPerludibayar() {
		return this.amaunPerludibayar;
	}

	public void setAmaunPerludibayar(Double amaunPerludibayar) {
		this.amaunPerludibayar = amaunPerludibayar;
	}

	public Double getBakiAkhir() {
		return this.bakiAkhir;
	}

	public void setBakiAkhir(Double bakiAkhir) {
		this.bakiAkhir = bakiAkhir;
	}

	public Long getBulanBil() {
		return this.bulanBil;
	}

	public void setBulanBil(Long bulanBil) {
		this.bulanBil = bulanBil;
	}

	public Long getTahunBil() {
		return this.tahunBil;
	}

	public void setTahunBil(Long tahunBil) {
		this.tahunBil = tahunBil;
	}

	public String getFlagTangguh() {
		return this.flagTangguh;
	}

	public void setFlagTangguh(String flagTangguh) {
		this.flagTangguh = flagTangguh;
	}

	public String getFlagBayar() {
		return this.flagBayar;
	}

	public void setFlagBayar(String flagBayar) {
		this.flagBayar = flagBayar;
	}

	public String getFlagBatal() {
		return this.flagBatal;
	}

	public void setFlagBatal(String flagBatal) {
		this.flagBatal = flagBatal;
	}

	public Long getIdBatal() {
		return this.idBatal;
	}

	public void setIdBatal(Long idBatal) {
		this.idBatal = idBatal;
	}

	public Date getTarikhBatal() {
		return this.tarikhBatal;
	}

	public void setTarikhBatal(Date tarikhBatal) {
		this.tarikhBatal = tarikhBatal;
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

	public Double getAmaunTertunggak() {
		return this.amaunTertunggak;
	}

	public void setAmaunTertunggak(Double amaunTertunggak) {
		this.amaunTertunggak = amaunTertunggak;
	}

}