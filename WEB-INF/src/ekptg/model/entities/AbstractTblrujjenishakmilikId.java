package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenishakmilikId entity provides the base persistence definition
 * of the TblrujjenishakmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenishakmilikId implements
		java.io.Serializable {

	// Fields

	private Long idJenishakmilik;
	private String kodJnshm;
	private String keterangan;
	private String statusHm;
	private String ownerHm;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenishakmilikId() {
	}

	/** minimal constructor */
	public AbstractTblrujjenishakmilikId(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	/** full constructor */
	public AbstractTblrujjenishakmilikId(Long idJenishakmilik, String kodJnshm,
			String keterangan, String statusHm, String ownerHm, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idJenishakmilik = idJenishakmilik;
		this.kodJnshm = kodJnshm;
		this.keterangan = keterangan;
		this.statusHm = statusHm;
		this.ownerHm = ownerHm;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public String getKodJnshm() {
		return this.kodJnshm;
	}

	public void setKodJnshm(String kodJnshm) {
		this.kodJnshm = kodJnshm;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatusHm() {
		return this.statusHm;
	}

	public void setStatusHm(String statusHm) {
		this.statusHm = statusHm;
	}

	public String getOwnerHm() {
		return this.ownerHm;
	}

	public void setOwnerHm(String ownerHm) {
		this.ownerHm = ownerHm;
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
		if (!(other instanceof AbstractTblrujjenishakmilikId))
			return false;
		AbstractTblrujjenishakmilikId castOther = (AbstractTblrujjenishakmilikId) other;

		return ((this.getIdJenishakmilik() == castOther.getIdJenishakmilik()) || (this
				.getIdJenishakmilik() != null
				&& castOther.getIdJenishakmilik() != null && this
				.getIdJenishakmilik().equals(castOther.getIdJenishakmilik())))
				&& ((this.getKodJnshm() == castOther.getKodJnshm()) || (this
						.getKodJnshm() != null
						&& castOther.getKodJnshm() != null && this
						.getKodJnshm().equals(castOther.getKodJnshm())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())))
				&& ((this.getStatusHm() == castOther.getStatusHm()) || (this
						.getStatusHm() != null
						&& castOther.getStatusHm() != null && this
						.getStatusHm().equals(castOther.getStatusHm())))
				&& ((this.getOwnerHm() == castOther.getOwnerHm()) || (this
						.getOwnerHm() != null
						&& castOther.getOwnerHm() != null && this.getOwnerHm()
						.equals(castOther.getOwnerHm())))
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

		result = 37
				* result
				+ (getIdJenishakmilik() == null ? 0 : this.getIdJenishakmilik()
						.hashCode());
		result = 37 * result
				+ (getKodJnshm() == null ? 0 : this.getKodJnshm().hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
						.hashCode());
		result = 37 * result
				+ (getStatusHm() == null ? 0 : this.getStatusHm().hashCode());
		result = 37 * result
				+ (getOwnerHm() == null ? 0 : this.getOwnerHm().hashCode());
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