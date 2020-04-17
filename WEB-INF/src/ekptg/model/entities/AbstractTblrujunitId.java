package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujseksyenId entity provides the base persistence definition of
 * the TblrujseksyenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujunitId implements java.io.Serializable {

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
	public AbstractTblrujunitId() {
	}

	/** minimal constructor */
	public AbstractTblrujunitId(Long idUnit) {
		this.idUnit = idUnit;
	}

	/** full constructor */
	public AbstractTblrujunitId(Long idUnit, String kodUnit,
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblrujunitId))
			return false;
		AbstractTblrujunitId castOther = (AbstractTblrujunitId) other;

		return ((this.getIdUnit() == castOther.getIdUnit()) || (this
				.getIdUnit() != null
				&& castOther.getIdUnit() != null && this.getIdUnit()
				.equals(castOther.getIdUnit())))
				&& ((this.getKodUnit() == castOther.getKodUnit()) || (this
						.getKodUnit() != null
						&& castOther.getKodUnit() != null && this
						.getKodUnit().equals(castOther.getKodUnit())))
				&& ((this.getNamaUnit() == castOther.getNamaUnit()) || (this
						.getNamaUnit() != null
						&& castOther.getNamaUnit() != null && this
						.getNamaUnit().equals(castOther.getNamaUnit())))
				&& ((this.getVersiUnit() == castOther.getVersiUnit()) || (this
						.getVersiUnit() != null
						&& castOther.getVersiUnit() != null && this
						.getVersiUnit().equals(castOther.getVersiUnit())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdUnit() == null ? 0 : this.getIdUnit().hashCode());
		result = 37
				* result
				+ (getKodUnit() == null ? 0 : this.getKodUnit()
						.hashCode());
		result = 37
				* result
				+ (getNamaUnit() == null ? 0 : this.getNamaUnit()
						.hashCode());
		result = 37
				* result
				+ (getVersiUnit() == null ? 0 : this.getVersiUnit()
						.hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}
