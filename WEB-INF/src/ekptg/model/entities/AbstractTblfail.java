package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblfail entity provides the base persistence definition of the
 * Tblfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblfail implements java.io.Serializable {

	// Fields

	private Long idFail;
	private String kodFail;
	private String tajukFail;
	private Date tarikhBuka;
	private String catatan;
	private String kodPengguna;
	private String kodStatusFail;
	private String kodKategoriKeselamatan;
	private String kodKategoriFail;

	// Constructors

	/** default constructor */
	public AbstractTblfail() {
	}

	/** full constructor */
	public AbstractTblfail(String kodFail, String tajukFail, Date tarikhBuka,
			String catatan, String kodPengguna, String kodStatusFail,
			String kodKategoriKeselamatan, String kodKategoriFail) {
		this.kodFail = kodFail;
		this.tajukFail = tajukFail;
		this.tarikhBuka = tarikhBuka;
		this.catatan = catatan;
		this.kodPengguna = kodPengguna;
		this.kodStatusFail = kodStatusFail;
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
		this.kodKategoriFail = kodKategoriFail;
	}

	// Property accessors

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getKodFail() {
		return this.kodFail;
	}

	public void setKodFail(String kodFail) {
		this.kodFail = kodFail;
	}

	public String getTajukFail() {
		return this.tajukFail;
	}

	public void setTajukFail(String tajukFail) {
		this.tajukFail = tajukFail;
	}

	public Date getTarikhBuka() {
		return this.tarikhBuka;
	}

	public void setTarikhBuka(Date tarikhBuka) {
		this.tarikhBuka = tarikhBuka;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getKodStatusFail() {
		return this.kodStatusFail;
	}

	public void setKodStatusFail(String kodStatusFail) {
		this.kodStatusFail = kodStatusFail;
	}

	public String getKodKategoriKeselamatan() {
		return this.kodKategoriKeselamatan;
	}

	public void setKodKategoriKeselamatan(String kodKategoriKeselamatan) {
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
	}

	public String getKodKategoriFail() {
		return this.kodKategoriFail;
	}

	public void setKodKategoriFail(String kodKategoriFail) {
		this.kodKategoriFail = kodKategoriFail;
	}

}