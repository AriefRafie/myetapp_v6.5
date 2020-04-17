package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujenistanahId entity provides the base persistence definition of
 * the TblrujenistanahId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujenistanahId implements java.io.Serializable {

	// Fields

	private Long idJenistanah;
	private String kodJenisTanah;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujenistanahId() {
	}

	/** minimal constructor */
	public AbstractTblrujenistanahId(Long idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	/** full constructor */
	public AbstractTblrujenistanahId(Long idJenistanah, String kodJenisTanah,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idJenistanah = idJenistanah;
		this.kodJenisTanah = kodJenisTanah;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenistanah() {
		return this.idJenistanah;
	}

	public void setIdJenistanah(Long idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	public String getKodJenisTanah() {
		return this.kodJenisTanah;
	}

	public void setKodJenisTanah(String kodJenisTanah) {
		this.kodJenisTanah = kodJenisTanah;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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
		if (!(other instanceof AbstractTblrujenistanahId))
			return false;
		AbstractTblrujenistanahId castOther = (AbstractTblrujenistanahId) other;

		return ((this.getIdJenistanah() == castOther.getIdJenistanah()) || (this
				.getIdJenistanah() != null
				&& castOther.getIdJenistanah() != null && this
				.getIdJenistanah().equals(castOther.getIdJenistanah())))
				&& ((this.getKodJenisTanah() == castOther.getKodJenisTanah()) || (this
						.getKodJenisTanah() != null
						&& castOther.getKodJenisTanah() != null && this
						.getKodJenisTanah()
						.equals(castOther.getKodJenisTanah())))
				&& ((this.getKeterangan() == castOther.getKeterangan()) || (this
						.getKeterangan() != null
						&& castOther.getKeterangan() != null && this
						.getKeterangan().equals(castOther.getKeterangan())))
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
				+ (getIdJenistanah() == null ? 0 : this.getIdJenistanah()
						.hashCode());
		result = 37
				* result
				+ (getKodJenisTanah() == null ? 0 : this.getKodJenisTanah()
						.hashCode());
		result = 37
				* result
				+ (getKeterangan() == null ? 0 : this.getKeterangan()
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