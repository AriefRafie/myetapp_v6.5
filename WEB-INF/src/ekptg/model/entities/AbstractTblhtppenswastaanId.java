package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppenswastaanId entity provides the base persistence definition
 * of the TblhtppenswastaanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppenswastaanId implements
		java.io.Serializable {

	// Fields

	private Long idPenswastaan;
	private Tblhtppermohonan tblhtppermohonan;
	private String tindakanLanjut;
	private String statusPtp;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppenswastaanId() {
	}

	/** minimal constructor */
	public AbstractTblhtppenswastaanId(Long idPenswastaan,
			Tblhtppermohonan tblhtppermohonan) {
		this.idPenswastaan = idPenswastaan;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtppenswastaanId(Long idPenswastaan,
			Tblhtppermohonan tblhtppermohonan, String tindakanLanjut,
			String statusPtp, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenswastaan = idPenswastaan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tindakanLanjut = tindakanLanjut;
		this.statusPtp = statusPtp;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenswastaan() {
		return this.idPenswastaan;
	}

	public void setIdPenswastaan(Long idPenswastaan) {
		this.idPenswastaan = idPenswastaan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getTindakanLanjut() {
		return this.tindakanLanjut;
	}

	public void setTindakanLanjut(String tindakanLanjut) {
		this.tindakanLanjut = tindakanLanjut;
	}

	public String getStatusPtp() {
		return this.statusPtp;
	}

	public void setStatusPtp(String statusPtp) {
		this.statusPtp = statusPtp;
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
		if (!(other instanceof AbstractTblhtppenswastaanId))
			return false;
		AbstractTblhtppenswastaanId castOther = (AbstractTblhtppenswastaanId) other;

		return ((this.getIdPenswastaan() == castOther.getIdPenswastaan()) || (this
				.getIdPenswastaan() != null
				&& castOther.getIdPenswastaan() != null && this
				.getIdPenswastaan().equals(castOther.getIdPenswastaan())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getTindakanLanjut() == castOther.getTindakanLanjut()) || (this
						.getTindakanLanjut() != null
						&& castOther.getTindakanLanjut() != null && this
						.getTindakanLanjut().equals(
								castOther.getTindakanLanjut())))
				&& ((this.getStatusPtp() == castOther.getStatusPtp()) || (this
						.getStatusPtp() != null
						&& castOther.getStatusPtp() != null && this
						.getStatusPtp().equals(castOther.getStatusPtp())))
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
				+ (getIdPenswastaan() == null ? 0 : this.getIdPenswastaan()
						.hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37
				* result
				+ (getTindakanLanjut() == null ? 0 : this.getTindakanLanjut()
						.hashCode());
		result = 37 * result
				+ (getStatusPtp() == null ? 0 : this.getStatusPtp().hashCode());
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