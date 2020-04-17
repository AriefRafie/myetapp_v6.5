package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtptukargantiId entity provides the base persistence definition of
 * the TblhtptukargantiId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtptukargantiId implements
		java.io.Serializable {

	// Fields

	private Long idTukarganti;
	private Long idPenswastaan;
	private Long idHtphakmilik;
	private Long idHakmilik;
	private String ulasan;
	private String statusPtp;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtptukargantiId() {
	}

	/** minimal constructor */
	public AbstractTblhtptukargantiId(Long idTukarganti, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		this.idTukarganti = idTukarganti;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
	}

	/** full constructor */
	public AbstractTblhtptukargantiId(Long idTukarganti, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, String ulasan,
			String statusPtp, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idTukarganti = idTukarganti;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
		this.ulasan = ulasan;
		this.statusPtp = statusPtp;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdTukarganti() {
		return this.idTukarganti;
	}

	public void setIdTukarganti(Long idTukarganti) {
		this.idTukarganti = idTukarganti;
	}

	public Long getIdPenswastaan() {
		return this.idPenswastaan;
	}

	public void setIdPenswastaan(Long idPenswastaan) {
		this.idPenswastaan = idPenswastaan;
	}

	public Long getIdHtphakmilik() {
		return this.idHtphakmilik;
	}

	public void setIdHtphakmilik(Long idHtphakmilik) {
		this.idHtphakmilik = idHtphakmilik;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
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
		if (!(other instanceof AbstractTblhtptukargantiId))
			return false;
		AbstractTblhtptukargantiId castOther = (AbstractTblhtptukargantiId) other;

		return ((this.getIdTukarganti() == castOther.getIdTukarganti()) || (this
				.getIdTukarganti() != null
				&& castOther.getIdTukarganti() != null && this
				.getIdTukarganti().equals(castOther.getIdTukarganti())))
				&& ((this.getIdPenswastaan() == castOther.getIdPenswastaan()) || (this
						.getIdPenswastaan() != null
						&& castOther.getIdPenswastaan() != null && this
						.getIdPenswastaan()
						.equals(castOther.getIdPenswastaan())))
				&& ((this.getIdHtphakmilik() == castOther.getIdHtphakmilik()) || (this
						.getIdHtphakmilik() != null
						&& castOther.getIdHtphakmilik() != null && this
						.getIdHtphakmilik()
						.equals(castOther.getIdHtphakmilik())))
				&& ((this.getIdHakmilik() == castOther.getIdHakmilik()) || (this
						.getIdHakmilik() != null
						&& castOther.getIdHakmilik() != null && this
						.getIdHakmilik().equals(castOther.getIdHakmilik())))
				&& ((this.getUlasan() == castOther.getUlasan()) || (this
						.getUlasan() != null
						&& castOther.getUlasan() != null && this.getUlasan()
						.equals(castOther.getUlasan())))
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
				+ (getIdTukarganti() == null ? 0 : this.getIdTukarganti()
						.hashCode());
		result = 37
				* result
				+ (getIdPenswastaan() == null ? 0 : this.getIdPenswastaan()
						.hashCode());
		result = 37
				* result
				+ (getIdHtphakmilik() == null ? 0 : this.getIdHtphakmilik()
						.hashCode());
		result = 37
				* result
				+ (getIdHakmilik() == null ? 0 : this.getIdHakmilik()
						.hashCode());
		result = 37 * result
				+ (getUlasan() == null ? 0 : this.getUlasan().hashCode());
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