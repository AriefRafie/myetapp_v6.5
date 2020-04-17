package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpdtdokumen entity provides the base persistence definition of the
 * Tblpdtdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtdokumen implements java.io.Serializable {

	// Fields

	private Long idDokumen;
	private String noDokumen;
	private String tajukDokumen;
	private String kategoriDokumen;
	private Date tarikhDokumen;
	private Date tarikhDaftar;
	private Long idFail;
	private String seksyenUrusetia;
	private String kandunganDokumen;
	private String catatan;
	private String dirDokumen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpdtdokumen() {
	}

	/** minimal constructor */
	public AbstractTblpdtdokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	/** full constructor */
	public AbstractTblpdtdokumen(Long idDokumen, String noDokumen,
			String tajukDokumen, String kategoriDokumen, Date tarikhDokumen,
			Date tarikhDaftar, Long idFail, String seksyenUrusetia,
			String kandunganDokumen, String catatan, String dirDokumen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idDokumen = idDokumen;
		this.noDokumen = noDokumen;
		this.tajukDokumen = tajukDokumen;
		this.kategoriDokumen = kategoriDokumen;
		this.tarikhDokumen = tarikhDokumen;
		this.tarikhDaftar = tarikhDaftar;
		this.idFail = idFail;
		this.seksyenUrusetia = seksyenUrusetia;
		this.kandunganDokumen = kandunganDokumen;
		this.catatan = catatan;
		this.dirDokumen = dirDokumen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public String getNoDokumen() {
		return this.noDokumen;
	}

	public void setNoDokumen(String noDokumen) {
		this.noDokumen = noDokumen;
	}

	public String getTajukDokumen() {
		return this.tajukDokumen;
	}

	public void setTajukDokumen(String tajukDokumen) {
		this.tajukDokumen = tajukDokumen;
	}

	public String getKategoriDokumen() {
		return this.kategoriDokumen;
	}

	public void setKategoriDokumen(String kategoriDokumen) {
		this.kategoriDokumen = kategoriDokumen;
	}

	public Date getTarikhDokumen() {
		return this.tarikhDokumen;
	}

	public void setTarikhDokumen(Date tarikhDokumen) {
		this.tarikhDokumen = tarikhDokumen;
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

	public String getKandunganDokumen() {
		return this.kandunganDokumen;
	}

	public void setKandunganDokumen(String kandunganDokumen) {
		this.kandunganDokumen = kandunganDokumen;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getDirDokumen() {
		return this.dirDokumen;
	}

	public void setDirDokumen(String dirDokumen) {
		this.dirDokumen = dirDokumen;
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

}