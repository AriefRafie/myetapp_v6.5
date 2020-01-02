package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptbangunan entity provides the base persistence definition of the
 * Tblpptbangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptbangunan implements java.io.Serializable {

	// Fields

	private Long idBangunan;
	private String noBangunan;
	private Long jenisBangunan;
	private String saizBangunan;
	private Double nilaiBangunan;
	private String noUnitBangunan;
	private String pemilik;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String noKpLama;
	private Long idJenispb;
	private Long idJenisnopb;
	private String noPemilik;
	private String noTel;
	private String ulasan;
	private String perihalKepentinganLain2;
	private Long idPihakberkepentingan;
	private Double kosPindah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptbangunan() {
	}

	/** full constructor */
	public AbstractTblpptbangunan(String noBangunan, Long jenisBangunan,
			String saizBangunan, Double nilaiBangunan, String noUnitBangunan,
			String pemilik, String alamat1, String alamat2, String alamat3,
			String poskod, Long idNegeri, String noKpLama, Long idJenispb,
			Long idJenisnopb, String noPemilik, String noTel, String ulasan,
			String perihalKepentinganLain2, Long idPihakberkepentingan,
			Double kosPindah, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.noBangunan = noBangunan;
		this.jenisBangunan = jenisBangunan;
		this.saizBangunan = saizBangunan;
		this.nilaiBangunan = nilaiBangunan;
		this.noUnitBangunan = noUnitBangunan;
		this.pemilik = pemilik;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.noKpLama = noKpLama;
		this.idJenispb = idJenispb;
		this.idJenisnopb = idJenisnopb;
		this.noPemilik = noPemilik;
		this.noTel = noTel;
		this.ulasan = ulasan;
		this.perihalKepentinganLain2 = perihalKepentinganLain2;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.kosPindah = kosPindah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBangunan() {
		return this.idBangunan;
	}

	public void setIdBangunan(Long idBangunan) {
		this.idBangunan = idBangunan;
	}

	public String getNoBangunan() {
		return this.noBangunan;
	}

	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}

	public Long getJenisBangunan() {
		return this.jenisBangunan;
	}

	public void setJenisBangunan(Long jenisBangunan) {
		this.jenisBangunan = jenisBangunan;
	}

	public String getSaizBangunan() {
		return this.saizBangunan;
	}

	public void setSaizBangunan(String saizBangunan) {
		this.saizBangunan = saizBangunan;
	}

	public Double getNilaiBangunan() {
		return this.nilaiBangunan;
	}

	public void setNilaiBangunan(Double nilaiBangunan) {
		this.nilaiBangunan = nilaiBangunan;
	}

	public String getNoUnitBangunan() {
		return this.noUnitBangunan;
	}

	public void setNoUnitBangunan(String noUnitBangunan) {
		this.noUnitBangunan = noUnitBangunan;
	}

	public String getPemilik() {
		return this.pemilik;
	}

	public void setPemilik(String pemilik) {
		this.pemilik = pemilik;
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

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
	}

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public String getNoPemilik() {
		return this.noPemilik;
	}

	public void setNoPemilik(String noPemilik) {
		this.noPemilik = noPemilik;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getPerihalKepentinganLain2() {
		return this.perihalKepentinganLain2;
	}

	public void setPerihalKepentinganLain2(String perihalKepentinganLain2) {
		this.perihalKepentinganLain2 = perihalKepentinganLain2;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Double getKosPindah() {
		return this.kosPindah;
	}

	public void setKosPindah(Double kosPindah) {
		this.kosPindah = kosPindah;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}