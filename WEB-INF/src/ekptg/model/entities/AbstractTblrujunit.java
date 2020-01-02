package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujseksyen entity provides the base persistence definition of the
 * Tblrujseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujunit implements java.io.Serializable {

	// Fields

	private Long idUnit;
	private String kodUnit;
	private String namaUnit;
	private String versiUnit;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujunit() {
	}

	/** minimal constructor */
	public AbstractTblrujunit(Long idUnit) {
		this.idUnit = idUnit;
	}

	/** full constructor */
	public AbstractTblrujunit(Long idUnit, String kodUnit,
			String namaUnit, String versiUnit, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idUnit = idUnit;
		this.kodUnit = kodUnit;
		this.namaUnit = namaUnit;
		this.versiUnit = versiUnit;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUnit() {
		return this.idUnit;
	}

	public void setIdUnit(Long idUnit) {
		this.idUnit = idUnit;
	}

	public String getKodUnit() {
		return this.kodUnit;
	}

	public void setKodUnit(String kodUnit) {
		this.kodUnit = kodUnit;
	}

	public String getNamaUnit() {
		return this.namaUnit;
	}

	public void setNamaUnit(String namaUnit) {
		this.namaUnit = namaUnit;
	}

	public String getVersiUnit() {
		return this.versiUnit;
	}

	public void setVersiUnit(String versiUnit) {
		this.versiUnit = versiUnit;
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

}