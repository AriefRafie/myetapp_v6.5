package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptkehadiran entity provides the base persistence definition of
 * the Tblpptkehadiran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptkehadiran implements java.io.Serializable {

	// Fields

	private Long idKehadiran;
	private Long idSiasatan;
	private Long idPihakberkepentingan;
	private String nama;
	private String namaKp;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String noAkaun;
	private Long idBank;
	private String noKp;
	private String flagHadir;
	private Long idJenispb;
	private Long idJenisnopb;
	private String noKpLama;
	private String perihalJenisLainpb;
	private Long jenisLainpb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptkehadiran() {
	}

	/** full constructor */
	public AbstractTblpptkehadiran(Long idSiasatan, Long idPihakberkepentingan,
			String nama, String namaKp, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, String noAkaun,
			Long idBank, String noKp, String flagHadir, Long idJenispb,
			Long idJenisnopb, String noKpLama, String perihalJenisLainpb,
			Long jenisLainpb, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idSiasatan = idSiasatan;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.nama = nama;
		this.namaKp = namaKp;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.noAkaun = noAkaun;
		this.idBank = idBank;
		this.noKp = noKp;
		this.flagHadir = flagHadir;
		this.idJenispb = idJenispb;
		this.idJenisnopb = idJenisnopb;
		this.noKpLama = noKpLama;
		this.perihalJenisLainpb = perihalJenisLainpb;
		this.jenisLainpb = jenisLainpb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdKehadiran() {
		return this.idKehadiran;
	}

	public void setIdKehadiran(Long idKehadiran) {
		this.idKehadiran = idKehadiran;
	}

	public Long getIdSiasatan() {
		return this.idSiasatan;
	}

	public void setIdSiasatan(Long idSiasatan) {
		this.idSiasatan = idSiasatan;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNamaKp() {
		return this.namaKp;
	}

	public void setNamaKp(String namaKp) {
		this.namaKp = namaKp;
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

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public String getFlagHadir() {
		return this.flagHadir;
	}

	public void setFlagHadir(String flagHadir) {
		this.flagHadir = flagHadir;
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

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public String getPerihalJenisLainpb() {
		return this.perihalJenisLainpb;
	}

	public void setPerihalJenisLainpb(String perihalJenisLainpb) {
		this.perihalJenisLainpb = perihalJenisLainpb;
	}

	public Long getJenisLainpb() {
		return this.jenisLainpb;
	}

	public void setJenisLainpb(Long jenisLainpb) {
		this.jenisLainpb = jenisLainpb;
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