package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppeguam entity provides the base persistence definition of the
 * Tblhtppeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppeguam implements java.io.Serializable {

	// Fields

	private Long idPeguam;
	private Long idPermohonan;
	private String noRujukan;
	private String nama;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idDaerah;
	private Long idNegeri;
	private String noTel;
	private String noFax;
	private String namaPeguam;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppeguam() {
	}

	/** minimal constructor */
	public AbstractTblhtppeguam(Long idPeguam, Long idPermohonan,
			Long idDaerah, Long idNegeri) {
		this.idPeguam = idPeguam;
		this.idPermohonan = idPermohonan;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtppeguam(Long idPeguam, Long idPermohonan,
			String noRujukan, String nama, String alamat1, String alamat2,
			String alamat3, String poskod, Long idDaerah, Long idNegeri,
			String noTel, String noFax, String namaPeguam, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idPeguam = idPeguam;
		this.idPermohonan = idPermohonan;
		this.noRujukan = noRujukan;
		this.nama = nama;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.noTel = noTel;
		this.noFax = noFax;
		this.namaPeguam = namaPeguam;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPeguam() {
		return this.idPeguam;
	}

	public void setIdPeguam(Long idPeguam) {
		this.idPeguam = idPeguam;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getNoRujukan() {
		return this.noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public String getNamaPeguam() {
		return this.namaPeguam;
	}

	public void setNamaPeguam(String namaPeguam) {
		this.namaPeguam = namaPeguam;
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