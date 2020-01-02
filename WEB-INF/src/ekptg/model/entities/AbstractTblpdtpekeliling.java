package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpdtpekeliling entity provides the base persistence definition of
 * the Tblpdtpekeliling entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpekeliling implements java.io.Serializable {

	// Fields

	private Long idPekeliling;
	private String kategoriPekeliling;
	private String bilPekeliling;
	private String tajukPekeliling;
	private Date tarikhPekeliling;
	private Date tarikhKuatkuasa;
	private Date tarikhDaftar;
	private Long idFail;
	private String seksyenUrusetia;
	private String status;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDokumenPekeliling;

	// Constructors

	/** default constructor */
	public AbstractTblpdtpekeliling() {
	}

	/** minimal constructor */
	public AbstractTblpdtpekeliling(Long idPekeliling) {
		this.idPekeliling = idPekeliling;
	}

	/** full constructor */
	public AbstractTblpdtpekeliling(Long idPekeliling,
			String kategoriPekeliling, String bilPekeliling,
			String tajukPekeliling, Date tarikhPekeliling,
			Date tarikhKuatkuasa, Date tarikhDaftar, Long idFail,
			String seksyenUrusetia, String status, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDokumenPekeliling) {
		this.idPekeliling = idPekeliling;
		this.kategoriPekeliling = kategoriPekeliling;
		this.bilPekeliling = bilPekeliling;
		this.tajukPekeliling = tajukPekeliling;
		this.tarikhPekeliling = tarikhPekeliling;
		this.tarikhKuatkuasa = tarikhKuatkuasa;
		this.tarikhDaftar = tarikhDaftar;
		this.idFail = idFail;
		this.seksyenUrusetia = seksyenUrusetia;
		this.status = status;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDokumenPekeliling = idDokumenPekeliling;
	}

	// Property accessors

	public Long getIdPekeliling() {
		return this.idPekeliling;
	}

	public void setIdPekeliling(Long idPekeliling) {
		this.idPekeliling = idPekeliling;
	}

	public String getKategoriPekeliling() {
		return this.kategoriPekeliling;
	}

	public void setKategoriPekeliling(String kategoriPekeliling) {
		this.kategoriPekeliling = kategoriPekeliling;
	}

	public String getBilPekeliling() {
		return this.bilPekeliling;
	}

	public void setBilPekeliling(String bilPekeliling) {
		this.bilPekeliling = bilPekeliling;
	}

	public String getTajukPekeliling() {
		return this.tajukPekeliling;
	}

	public void setTajukPekeliling(String tajukPekeliling) {
		this.tajukPekeliling = tajukPekeliling;
	}

	public Date getTarikhPekeliling() {
		return this.tarikhPekeliling;
	}

	public void setTarikhPekeliling(Date tarikhPekeliling) {
		this.tarikhPekeliling = tarikhPekeliling;
	}

	public Date getTarikhKuatkuasa() {
		return this.tarikhKuatkuasa;
	}

	public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
		this.tarikhKuatkuasa = tarikhKuatkuasa;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getSeksyenUrusetia() {
		return this.seksyenUrusetia;
	}

	public void setSeksyenUrusetia(String seksyenUrusetia) {
		this.seksyenUrusetia = seksyenUrusetia;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public Long getIdDokumenPekeliling() {
		return this.idDokumenPekeliling;
	}

	public void setIdDokumenPekeliling(Long idDokumenPekeliling) {
		this.idDokumenPekeliling = idDokumenPekeliling;
	}

}