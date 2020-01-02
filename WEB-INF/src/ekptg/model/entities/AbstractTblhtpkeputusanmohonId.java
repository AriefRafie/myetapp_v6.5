package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpkeputusanmohonId entity provides the base persistence
 * definition of the TblhtpkeputusanmohonId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpkeputusanmohonId implements
		java.io.Serializable {

	// Fields

	private Long idKeputusan;
	private Tblhtppermohonan tblhtppermohonan;
	private String noRujukanKeputusan;
	private Date tarikhKeputusan;
	private String status;
	private String ulasan;
	private String keputusanKjp;
	private String ulasanKjp;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpkeputusanmohonId() {
	}

	/** minimal constructor */
	public AbstractTblhtpkeputusanmohonId(Long idKeputusan,
			Tblhtppermohonan tblhtppermohonan) {
		this.idKeputusan = idKeputusan;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpkeputusanmohonId(Long idKeputusan,
			Tblhtppermohonan tblhtppermohonan, String noRujukanKeputusan,
			Date tarikhKeputusan, String status, String ulasan,
			String keputusanKjp, String ulasanKjp, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idKeputusan = idKeputusan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noRujukanKeputusan = noRujukanKeputusan;
		this.tarikhKeputusan = tarikhKeputusan;
		this.status = status;
		this.ulasan = ulasan;
		this.keputusanKjp = keputusanKjp;
		this.ulasanKjp = ulasanKjp;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKeputusan() {
		return this.idKeputusan;
	}

	public void setIdKeputusan(Long idKeputusan) {
		this.idKeputusan = idKeputusan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getNoRujukanKeputusan() {
		return this.noRujukanKeputusan;
	}

	public void setNoRujukanKeputusan(String noRujukanKeputusan) {
		this.noRujukanKeputusan = noRujukanKeputusan;
	}

	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getKeputusanKjp() {
		return this.keputusanKjp;
	}

	public void setKeputusanKjp(String keputusanKjp) {
		this.keputusanKjp = keputusanKjp;
	}

	public String getUlasanKjp() {
		return this.ulasanKjp;
	}

	public void setUlasanKjp(String ulasanKjp) {
		this.ulasanKjp = ulasanKjp;
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
		if (!(other instanceof AbstractTblhtpkeputusanmohonId))
			return false;
		AbstractTblhtpkeputusanmohonId castOther = (AbstractTblhtpkeputusanmohonId) other;

		return ((this.getIdKeputusan() == castOther.getIdKeputusan()) || (this
				.getIdKeputusan() != null
				&& castOther.getIdKeputusan() != null && this.getIdKeputusan()
				.equals(castOther.getIdKeputusan())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getNoRujukanKeputusan() == castOther
						.getNoRujukanKeputusan()) || (this
						.getNoRujukanKeputusan() != null
						&& castOther.getNoRujukanKeputusan() != null && this
						.getNoRujukanKeputusan().equals(
								castOther.getNoRujukanKeputusan())))
				&& ((this.getTarikhKeputusan() == castOther
						.getTarikhKeputusan()) || (this.getTarikhKeputusan() != null
						&& castOther.getTarikhKeputusan() != null && this
						.getTarikhKeputusan().equals(
								castOther.getTarikhKeputusan())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getUlasan() == castOther.getUlasan()) || (this
						.getUlasan() != null
						&& castOther.getUlasan() != null && this.getUlasan()
						.equals(castOther.getUlasan())))
				&& ((this.getKeputusanKjp() == castOther.getKeputusanKjp()) || (this
						.getKeputusanKjp() != null
						&& castOther.getKeputusanKjp() != null && this
						.getKeputusanKjp().equals(castOther.getKeputusanKjp())))
				&& ((this.getUlasanKjp() == castOther.getUlasanKjp()) || (this
						.getUlasanKjp() != null
						&& castOther.getUlasanKjp() != null && this
						.getUlasanKjp().equals(castOther.getUlasanKjp())))
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
				+ (getIdKeputusan() == null ? 0 : this.getIdKeputusan()
						.hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37
				* result
				+ (getNoRujukanKeputusan() == null ? 0 : this
						.getNoRujukanKeputusan().hashCode());
		result = 37
				* result
				+ (getTarikhKeputusan() == null ? 0 : this.getTarikhKeputusan()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getUlasan() == null ? 0 : this.getUlasan().hashCode());
		result = 37
				* result
				+ (getKeputusanKjp() == null ? 0 : this.getKeputusanKjp()
						.hashCode());
		result = 37 * result
				+ (getUlasanKjp() == null ? 0 : this.getUlasanKjp().hashCode());
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