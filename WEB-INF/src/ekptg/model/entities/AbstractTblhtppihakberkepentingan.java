package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppihakberkepentingan entity provides the base persistence
 * definition of the Tblhtppihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppihakberkepentingan implements
		java.io.Serializable {

	// Fields

	private Long idPihakberkepentingan;
	private Long idHakmilikurusan;
	private Long idJenisnopb;
	private Long idJenispb;
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
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblhtppihakberkepentingan() {
	}

	/** minimal constructor */
	public AbstractTblhtppihakberkepentingan(Long idPihakberkepentingan,
			Long idHakmilikurusan, Long idJenisnopb, Long idJenispb,
			Long idDaerah, Long idNegeri) {
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idHakmilikurusan = idHakmilikurusan;
		this.idJenisnopb = idJenisnopb;
		this.idJenispb = idJenispb;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtppihakberkepentingan(Long idPihakberkepentingan,
			Long idHakmilikurusan, Long idJenisnopb, Long idJenispb,
			String noRujukan, String nama, String alamat1, String alamat2,
			String alamat3, String poskod, Long idDaerah, Long idNegeri,
			String noTel, String noFax, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idHakmilikurusan = idHakmilikurusan;
		this.idJenisnopb = idJenisnopb;
		this.idJenispb = idJenispb;
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
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Long getIdHakmilikurusan() {
		return this.idHakmilikurusan;
	}

	public void setIdHakmilikurusan(Long idHakmilikurusan) {
		this.idHakmilikurusan = idHakmilikurusan;
	}

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
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