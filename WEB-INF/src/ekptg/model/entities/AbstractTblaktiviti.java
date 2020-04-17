package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblaktiviti entity provides the base persistence definition of the
 * Tblaktiviti entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblaktiviti implements java.io.Serializable {

	// Fields

	private Long idAktiviti;
	private String kodLangkah;
	private String kodPengguna;
	private Date tarikhMula;
	private Date tarikhSelesai;
	private String kodStatusAktiviti;
	private String catatan;
	private String kodProses;

	// Constructors

	/** default constructor */
	public AbstractTblaktiviti() {
	}

	/** full constructor */
	public AbstractTblaktiviti(String kodLangkah, String kodPengguna,
			Date tarikhMula, Date tarikhSelesai, String kodStatusAktiviti,
			String catatan, String kodProses) {
		this.kodLangkah = kodLangkah;
		this.kodPengguna = kodPengguna;
		this.tarikhMula = tarikhMula;
		this.tarikhSelesai = tarikhSelesai;
		this.kodStatusAktiviti = kodStatusAktiviti;
		this.catatan = catatan;
		this.kodProses = kodProses;
	}

	// Property accessors

	public Long getIdAktiviti() {
		return this.idAktiviti;
	}

	public void setIdAktiviti(Long idAktiviti) {
		this.idAktiviti = idAktiviti;
	}

	public String getKodLangkah() {
		return this.kodLangkah;
	}

	public void setKodLangkah(String kodLangkah) {
		this.kodLangkah = kodLangkah;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
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

	public String getKodStatusAktiviti() {
		return this.kodStatusAktiviti;
	}

	public void setKodStatusAktiviti(String kodStatusAktiviti) {
		this.kodStatusAktiviti = kodStatusAktiviti;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getKodProses() {
		return this.kodProses;
	}

	public void setKodProses(String kodProses) {
		this.kodProses = kodProses;
	}

}