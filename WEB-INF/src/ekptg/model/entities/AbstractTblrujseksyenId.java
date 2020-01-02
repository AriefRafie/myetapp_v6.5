package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujseksyenId entity provides the base persistence definition of
 * the TblrujseksyenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujseksyenId implements java.io.Serializable {

	// Fields

	private Long idSeksyen;
	private String kodSeksyen;
	private String namaSeksyen;
	private String versiSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujseksyenId() {
	}

	/** minimal constructor */
	public AbstractTblrujseksyenId(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	/** full constructor */
	public AbstractTblrujseksyenId(Long idSeksyen, String kodSeksyen,
			String namaSeksyen, String versiSeksyen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSeksyen = idSeksyen;
		this.kodSeksyen = kodSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.versiSeksyen = versiSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public String getKodSeksyen() {
		return this.kodSeksyen;
	}

	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}

	public String getNamaSeksyen() {
		return this.namaSeksyen;
	}

	public void setNamaSeksyen(String namaSeksyen) {
		this.namaSeksyen = namaSeksyen;
	}

	public String getVersiSeksyen() {
		return this.versiSeksyen;
	}

	public void setVersiSeksyen(String versiSeksyen) {
		this.versiSeksyen = versiSeksyen;
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
		if (!(other instanceof AbstractTblrujseksyenId))
			return false;
		AbstractTblrujseksyenId castOther = (AbstractTblrujseksyenId) other;

		return ((this.getIdSeksyen() == castOther.getIdSeksyen()) || (this
				.getIdSeksyen() != null
				&& castOther.getIdSeksyen() != null && this.getIdSeksyen()
				.equals(castOther.getIdSeksyen())))
				&& ((this.getKodSeksyen() == castOther.getKodSeksyen()) || (this
						.getKodSeksyen() != null
						&& castOther.getKodSeksyen() != null && this
						.getKodSeksyen().equals(castOther.getKodSeksyen())))
				&& ((this.getNamaSeksyen() == castOther.getNamaSeksyen()) || (this
						.getNamaSeksyen() != null
						&& castOther.getNamaSeksyen() != null && this
						.getNamaSeksyen().equals(castOther.getNamaSeksyen())))
				&& ((this.getVersiSeksyen() == castOther.getVersiSeksyen()) || (this
						.getVersiSeksyen() != null
						&& castOther.getVersiSeksyen() != null && this
						.getVersiSeksyen().equals(castOther.getVersiSeksyen())))
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
				+ (getIdSeksyen() == null ? 0 : this.getIdSeksyen().hashCode());
		result = 37
				* result
				+ (getKodSeksyen() == null ? 0 : this.getKodSeksyen()
						.hashCode());
		result = 37
				* result
				+ (getNamaSeksyen() == null ? 0 : this.getNamaSeksyen()
						.hashCode());
		result = 37
				* result
				+ (getVersiSeksyen() == null ? 0 : this.getVersiSeksyen()
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