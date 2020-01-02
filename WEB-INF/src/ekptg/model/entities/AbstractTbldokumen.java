package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTbldokumen entity provides the base persistence definition of the
 * Tbldokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTbldokumen implements java.io.Serializable {

	// Fields

	private Long idDokumen;
	private String kodFail;
	private String kodDokumen;
	private String tajukDokumen;
	private String catatan;
	private Date tarikhMasuk;
	private String kodPengguna;
	private String dokumen;
	private String tandatangan;
	private String kodKategoriKeselamatan;
	private String kodKategoriDokumen;

	// Constructors

	/** default constructor */
	public AbstractTbldokumen() {
	}

	/** full constructor */
	public AbstractTbldokumen(String kodFail, String kodDokumen,
			String tajukDokumen, String catatan, Date tarikhMasuk,
			String kodPengguna, String dokumen, String tandatangan,
			String kodKategoriKeselamatan, String kodKategoriDokumen) {
		this.kodFail = kodFail;
		this.kodDokumen = kodDokumen;
		this.tajukDokumen = tajukDokumen;
		this.catatan = catatan;
		this.tarikhMasuk = tarikhMasuk;
		this.kodPengguna = kodPengguna;
		this.dokumen = dokumen;
		this.tandatangan = tandatangan;
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
		this.kodKategoriDokumen = kodKategoriDokumen;
	}

	// Property accessors

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public String getKodFail() {
		return this.kodFail;
	}

	public void setKodFail(String kodFail) {
		this.kodFail = kodFail;
	}

	public String getKodDokumen() {
		return this.kodDokumen;
	}

	public void setKodDokumen(String kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public String getTajukDokumen() {
		return this.tajukDokumen;
	}

	public void setTajukDokumen(String tajukDokumen) {
		this.tajukDokumen = tajukDokumen;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getDokumen() {
		return this.dokumen;
	}

	public void setDokumen(String dokumen) {
		this.dokumen = dokumen;
	}

	public String getTandatangan() {
		return this.tandatangan;
	}

	public void setTandatangan(String tandatangan) {
		this.tandatangan = tandatangan;
	}

	public String getKodKategoriKeselamatan() {
		return this.kodKategoriKeselamatan;
	}

	public void setKodKategoriKeselamatan(String kodKategoriKeselamatan) {
		this.kodKategoriKeselamatan = kodKategoriKeselamatan;
	}

	public String getKodKategoriDokumen() {
		return this.kodKategoriDokumen;
	}

	public void setKodKategoriDokumen(String kodKategoriDokumen) {
		this.kodKategoriDokumen = kodKategoriDokumen;
	}

}