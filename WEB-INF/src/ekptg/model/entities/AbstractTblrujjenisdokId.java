package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujjenisdokId entity provides the base persistence definition of
 * the TblrujjenisdokId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisdokId implements java.io.Serializable {

	// Fields

	private Long idJenisdok;
	private String kodJenisDokumen;
	private String keterangan;
	private Long idForm;
	private Long idReport;
	private Long idSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisdokId() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisdokId(Long idJenisdok) {
		this.idJenisdok = idJenisdok;
	}

	/** full constructor */
	public AbstractTblrujjenisdokId(Long idJenisdok, String kodJenisDokumen,
			String keterangan, Long idForm, Long idReport, Long idSeksyen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idJenisdok = idJenisdok;
		this.kodJenisDokumen = kodJenisDokumen;
		this.keterangan = keterangan;
		this.idForm = idForm;
		this.idReport = idReport;
		this.idSeksyen = idSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenisdok() {
		return this.idJenisdok;
	}

	public void setIdJenisdok(Long idJenisdok) {
		this.idJenisdok = idJenisdok;
	}

	public String getKodJenisDokumen() {
		return this.kodJenisDokumen;
	}

	public void setKodJenisDokumen(String kodJenisDokumen) {
		this.kodJenisDokumen = kodJenisDokumen;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getIdForm() {
		return this.idForm;
	}

	public void setIdForm(Long idForm) {
		this.idForm = idForm;
	}

	public Long getIdReport() {
		return this.idReport;
	}

	public void setIdReport(Long idReport) {
		this.idReport = idReport;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
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
		if (!(other instanceof AbstractTblrujjenisdokId))
			return false;
		AbstractTblrujjenisdokId castOther = (AbstractTblrujjenisdokId) other;

		return ((this.getIdJenisdok() == castOther.getIdJenisdok()) || (this
				.getIdJenisdok() != null
				&& castOther.getIdJenisdok() != null && this.getIdJenisdok()
				.equals(castOther.getIdJenisdok())))
				&& ((this.getKodJenisDokumen() == castOther
						.getKodJenisDokumen()) || (this.getKodJenisDokumen() != null
						&& castOther.getKodJenisDokumen() != null && this
						.getKodJenisDokumen().equals(
								castOther.getKodJenisDokumen())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())))
				&& ((this.getIdForm() == castOther.getIdForm()) || (this
						.getIdForm() != null
						&& castOther.getIdForm() != null && this.getIdForm()
						.equals(castOther.getIdForm())))
				&& ((this.getIdReport() == castOther.getIdReport()) || (this
						.getIdReport() != null
						&& castOther.getIdReport() != null && this
						.getIdReport().equals(castOther.getIdReport())))
				&& ((this.getIdSeksyen() == castOther.getIdSeksyen()) || (this
						.getIdSeksyen() != null
						&& castOther.getIdSeksyen() != null && this
						.getIdSeksyen().equals(castOther.getIdSeksyen())))
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
				+ (getIdJenisdok() == null ? 0 : this.getIdJenisdok()
						.hashCode());
		result = 37
				* result
				+ (getKodJenisDokumen() == null ? 0 : this.getKodJenisDokumen()
						.hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
						.hashCode());
		result = 37 * result
				+ (getIdForm() == null ? 0 : this.getIdForm().hashCode());
		result = 37 * result
				+ (getIdReport() == null ? 0 : this.getIdReport().hashCode());
		result = 37 * result
				+ (getIdSeksyen() == null ? 0 : this.getIdSeksyen().hashCode());
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