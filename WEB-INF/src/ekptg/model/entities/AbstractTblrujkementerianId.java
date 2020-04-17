package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujkementerianId entity provides the base persistence definition
 * of the TblrujkementerianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujkementerianId implements
		java.io.Serializable {

	// Fields

	private Long idKementerian;
	private String kodKementerian;
	private String namaKementerian;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String jawatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujkementerianId() {
	}

	/** minimal constructor */
	public AbstractTblrujkementerianId(Long idKementerian, Long idNegeri) {
		this.idKementerian = idKementerian;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblrujkementerianId(Long idKementerian,
			String kodKementerian, String namaKementerian, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			String jawatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idKementerian = idKementerian;
		this.kodKementerian = kodKementerian;
		this.namaKementerian = namaKementerian;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.jawatan = jawatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public String getKodKementerian() {
		return this.kodKementerian;
	}

	public void setKodKementerian(String kodKementerian) {
		this.kodKementerian = kodKementerian;
	}

	public String getNamaKementerian() {
		return this.namaKementerian;
	}

	public void setNamaKementerian(String namaKementerian) {
		this.namaKementerian = namaKementerian;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
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
		if (!(other instanceof AbstractTblrujkementerianId))
			return false;
		AbstractTblrujkementerianId castOther = (AbstractTblrujkementerianId) other;

		return ((this.getIdKementerian() == castOther.getIdKementerian()) || (this
				.getIdKementerian() != null
				&& castOther.getIdKementerian() != null && this
				.getIdKementerian().equals(castOther.getIdKementerian())))
				&& ((this.getKodKementerian() == castOther.getKodKementerian()) || (this
						.getKodKementerian() != null
						&& castOther.getKodKementerian() != null && this
						.getKodKementerian().equals(
								castOther.getKodKementerian())))
				&& ((this.getNamaKementerian() == castOther
						.getNamaKementerian()) || (this.getNamaKementerian() != null
						&& castOther.getNamaKementerian() != null && this
						.getNamaKementerian().equals(
								castOther.getNamaKementerian())))
				&& ((this.getAlamat1() == castOther.getAlamat1()) || (this
						.getAlamat1() != null
						&& castOther.getAlamat1() != null && this.getAlamat1()
						.equals(castOther.getAlamat1())))
				&& ((this.getAlamat2() == castOther.getAlamat2()) || (this
						.getAlamat2() != null
						&& castOther.getAlamat2() != null && this.getAlamat2()
						.equals(castOther.getAlamat2())))
				&& ((this.getAlamat3() == castOther.getAlamat3()) || (this
						.getAlamat3() != null
						&& castOther.getAlamat3() != null && this.getAlamat3()
						.equals(castOther.getAlamat3())))
				&& ((this.getPoskod() == castOther.getPoskod()) || (this
						.getPoskod() != null
						&& castOther.getPoskod() != null && this.getPoskod()
						.equals(castOther.getPoskod())))
				&& ((this.getIdNegeri() == castOther.getIdNegeri()) || (this
						.getIdNegeri() != null
						&& castOther.getIdNegeri() != null && this
						.getIdNegeri().equals(castOther.getIdNegeri())))
				&& ((this.getJawatan() == castOther.getJawatan()) || (this
						.getJawatan() != null
						&& castOther.getJawatan() != null && this.getJawatan()
						.equals(castOther.getJawatan())))
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
				+ (getIdKementerian() == null ? 0 : this.getIdKementerian()
						.hashCode());
		result = 37
				* result
				+ (getKodKementerian() == null ? 0 : this.getKodKementerian()
						.hashCode());
		result = 37
				* result
				+ (getNamaKementerian() == null ? 0 : this.getNamaKementerian()
						.hashCode());
		result = 37 * result
				+ (getAlamat1() == null ? 0 : this.getAlamat1().hashCode());
		result = 37 * result
				+ (getAlamat2() == null ? 0 : this.getAlamat2().hashCode());
		result = 37 * result
				+ (getAlamat3() == null ? 0 : this.getAlamat3().hashCode());
		result = 37 * result
				+ (getPoskod() == null ? 0 : this.getPoskod().hashCode());
		result = 37 * result
				+ (getIdNegeri() == null ? 0 : this.getIdNegeri().hashCode());
		result = 37 * result
				+ (getJawatan() == null ? 0 : this.getJawatan().hashCode());
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