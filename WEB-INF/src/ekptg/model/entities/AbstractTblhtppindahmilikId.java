package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppindahmilikId entity provides the base persistence definition
 * of the TblhtppindahmilikId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppindahmilikId implements
		java.io.Serializable {

	// Fields

	private Long idPindahmilik;
	private Long idPenswastaan;
	private Long idHtphakmilik;
	private Long idHakmilik;
	private Date tarikhTandatangan;
	private Date tarikhHantar;
	private Date tarikhDaftar;
	private Date tarikhBatalRekod;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppindahmilikId() {
	}

	/** minimal constructor */
	public AbstractTblhtppindahmilikId(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik) {
		this.idPindahmilik = idPindahmilik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
	}

	/** full constructor */
	public AbstractTblhtppindahmilikId(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilik, Date tarikhTandatangan,
			Date tarikhHantar, Date tarikhDaftar, Date tarikhBatalRekod,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPindahmilik = idPindahmilik;
		this.idPenswastaan = idPenswastaan;
		this.idHtphakmilik = idHtphakmilik;
		this.idHakmilik = idHakmilik;
		this.tarikhTandatangan = tarikhTandatangan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhBatalRekod = tarikhBatalRekod;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPindahmilik() {
		return this.idPindahmilik;
	}

	public void setIdPindahmilik(Long idPindahmilik) {
		this.idPindahmilik = idPindahmilik;
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

	public Date getTarikhTandatangan() {
		return this.tarikhTandatangan;
	}

	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhBatalRekod() {
		return this.tarikhBatalRekod;
	}

	public void setTarikhBatalRekod(Date tarikhBatalRekod) {
		this.tarikhBatalRekod = tarikhBatalRekod;
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
		if (!(other instanceof AbstractTblhtppindahmilikId))
			return false;
		AbstractTblhtppindahmilikId castOther = (AbstractTblhtppindahmilikId) other;

		return ((this.getIdPindahmilik() == castOther.getIdPindahmilik()) || (this
				.getIdPindahmilik() != null
				&& castOther.getIdPindahmilik() != null && this
				.getIdPindahmilik().equals(castOther.getIdPindahmilik())))
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
				&& ((this.getTarikhTandatangan() == castOther
						.getTarikhTandatangan()) || (this
						.getTarikhTandatangan() != null
						&& castOther.getTarikhTandatangan() != null && this
						.getTarikhTandatangan().equals(
								castOther.getTarikhTandatangan())))
				&& ((this.getTarikhHantar() == castOther.getTarikhHantar()) || (this
						.getTarikhHantar() != null
						&& castOther.getTarikhHantar() != null && this
						.getTarikhHantar().equals(castOther.getTarikhHantar())))
				&& ((this.getTarikhDaftar() == castOther.getTarikhDaftar()) || (this
						.getTarikhDaftar() != null
						&& castOther.getTarikhDaftar() != null && this
						.getTarikhDaftar().equals(castOther.getTarikhDaftar())))
				&& ((this.getTarikhBatalRekod() == castOther
						.getTarikhBatalRekod()) || (this.getTarikhBatalRekod() != null
						&& castOther.getTarikhBatalRekod() != null && this
						.getTarikhBatalRekod().equals(
								castOther.getTarikhBatalRekod())))
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
				+ (getIdPindahmilik() == null ? 0 : this.getIdPindahmilik()
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
		result = 37
				* result
				+ (getTarikhTandatangan() == null ? 0 : this
						.getTarikhTandatangan().hashCode());
		result = 37
				* result
				+ (getTarikhHantar() == null ? 0 : this.getTarikhHantar()
						.hashCode());
		result = 37
				* result
				+ (getTarikhDaftar() == null ? 0 : this.getTarikhDaftar()
						.hashCode());
		result = 37
				* result
				+ (getTarikhBatalRekod() == null ? 0 : this
						.getTarikhBatalRekod().hashCode());
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