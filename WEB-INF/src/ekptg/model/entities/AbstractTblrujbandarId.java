package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujbandarId entity provides the base persistence definition of the
 * TblrujdaerahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbandarId implements java.io.Serializable {

	// Fields

	private Long idBandar;
	private String kodBandar;
	private String keterangan;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujbandarId() {
	}

	/** minimal constructor */
	public AbstractTblrujbandarId(Long idBandar) {
		this.idBandar = idBandar;
	}

	/** full constructor */
	public AbstractTblrujbandarId(Long idBandar, String kodBandar,
			String keterangan, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idBandar = idBandar;
		this.kodBandar = kodBandar;
		this.keterangan = keterangan;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBandar() {
		return this.idBandar;
	}

	public void setIdBandar(Long idBandar) {
		this.idBandar = idBandar;
	}

	public String getKodBandar() {
		return this.kodBandar;
	}

	public void setKodBandar(String kodBandar) {
		this.kodBandar = kodBandar;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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
		if (!(other instanceof AbstractTblrujbandarId))
			return false;
		AbstractTblrujbandarId castOther = (AbstractTblrujbandarId) other;

		return ((this.getIdBandar() == castOther.getIdBandar()) || (this
				.getIdBandar() != null
				&& castOther.getIdBandar() != null && this.getIdBandar()
				.equals(castOther.getIdBandar())))
				&& ((this.getKodBandar() == castOther.getKodBandar()) || (this
						.getKodBandar() != null
						&& castOther.getKodBandar() != null && this
						.getKodBandar().equals(castOther.getKodBandar())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
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
				+ (getIdBandar() == null ? 0 : this.getIdBandar().hashCode());
		result = 37 * result
				+ (getKodBandar() == null ? 0 : this.getKodBandar().hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
						.hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
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