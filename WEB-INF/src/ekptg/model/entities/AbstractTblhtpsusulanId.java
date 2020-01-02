package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpsusulanId entity provides the base persistence definition of
 * the TblhtpsusulanId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpsusulanId implements java.io.Serializable {

	// Fields

	private Long idSusulan;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikh;
	private String aktiviti;
	private String keterangan;
	private String namaPegawai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpsusulanId() {
	}

	/** minimal constructor */
	public AbstractTblhtpsusulanId(Long idSusulan,
			Tblhtppermohonan tblhtppermohonan) {
		this.idSusulan = idSusulan;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpsusulanId(Long idSusulan,
			Tblhtppermohonan tblhtppermohonan, Date tarikh, String aktiviti,
			String keterangan, String namaPegawai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSusulan = idSusulan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikh = tarikh;
		this.aktiviti = aktiviti;
		this.keterangan = keterangan;
		this.namaPegawai = namaPegawai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSusulan() {
		return this.idSusulan;
	}

	public void setIdSusulan(Long idSusulan) {
		this.idSusulan = idSusulan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikh() {
		return this.tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}

	public String getAktiviti() {
		return this.aktiviti;
	}

	public void setAktiviti(String aktiviti) {
		this.aktiviti = aktiviti;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
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
		if (!(other instanceof AbstractTblhtpsusulanId))
			return false;
		AbstractTblhtpsusulanId castOther = (AbstractTblhtpsusulanId) other;

		return ((this.getIdSusulan() == castOther.getIdSusulan()) || (this
				.getIdSusulan() != null
				&& castOther.getIdSusulan() != null && this.getIdSusulan()
				.equals(castOther.getIdSusulan())))
				&& ((this.getTblhtppermohonan() == castOther
						.getTblhtppermohonan()) || (this.getTblhtppermohonan() != null
						&& castOther.getTblhtppermohonan() != null && this
						.getTblhtppermohonan().equals(
								castOther.getTblhtppermohonan())))
				&& ((this.getTarikh() == castOther.getTarikh()) || (this
						.getTarikh() != null
						&& castOther.getTarikh() != null && this.getTarikh()
						.equals(castOther.getTarikh())))
				&& ((this.getAktiviti() == castOther.getAktiviti()) || (this
						.getAktiviti() != null
						&& castOther.getAktiviti() != null && this
						.getAktiviti().equals(castOther.getAktiviti())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())))
				&& ((this.getNamaPegawai() == castOther.getNamaPegawai()) || (this
						.getNamaPegawai() != null
						&& castOther.getNamaPegawai() != null && this
						.getNamaPegawai().equals(castOther.getNamaPegawai())))
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
				+ (getIdSusulan() == null ? 0 : this.getIdSusulan().hashCode());
		result = 37
				* result
				+ (getTblhtppermohonan() == null ? 0 : this
						.getTblhtppermohonan().hashCode());
		result = 37 * result
				+ (getTarikh() == null ? 0 : this.getTarikh().hashCode());
		result = 37 * result
				+ (getAktiviti() == null ? 0 : this.getAktiviti().hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
						.hashCode());
		result = 37
				* result
				+ (getNamaPegawai() == null ? 0 : this.getNamaPegawai()
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