package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblurusan entity provides the base persistence definition of the
 * Tblurusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblurusan implements java.io.Serializable {

	// Fields

	private Long idUrusan;
	private String kodProses;
	private String kodFail;
	private Date tarikhMula;
	private Date tarikhSelesai;
	private String kodPengguna;
	private String kodStatusUrusan;
	private String catatan;

	// Constructors

	/** default constructor */
	public AbstractTblurusan() {
	}

	/** full constructor */
	public AbstractTblurusan(String kodProses, String kodFail, Date tarikhMula,
			Date tarikhSelesai, String kodPengguna, String kodStatusUrusan,
			String catatan) {
		this.kodProses = kodProses;
		this.kodFail = kodFail;
		this.tarikhMula = tarikhMula;
		this.tarikhSelesai = tarikhSelesai;
		this.kodPengguna = kodPengguna;
		this.kodStatusUrusan = kodStatusUrusan;
		this.catatan = catatan;
	}

	// Property accessors

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

	public String getKodFail() {
		return this.kodFail;
	}

	public void setKodFail(String kodFail) {
		this.kodFail = kodFail;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhSelesai() {
		return this.tarikhSelesai;
	}

	public void setTarikhSelesai(Date tarikhSelesai) {
		this.tarikhSelesai = tarikhSelesai;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getKodStatusUrusan() {
		return this.kodStatusUrusan;
	}

	public void setKodStatusUrusan(String kodStatusUrusan) {
		this.kodStatusUrusan = kodStatusUrusan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

}