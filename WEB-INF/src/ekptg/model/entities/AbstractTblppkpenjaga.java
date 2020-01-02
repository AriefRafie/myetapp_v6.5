package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkpenjaga entity provides the base persistence definition of the
 * Tblppkpenjaga entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkpenjaga implements java.io.Serializable {

	// Fields

	private Long idPenjaga;
	private Long idObminor;
	private String noKpBaru;
	private String noKpLama;
	private String jenisKp;
	private String noKpLain;
	private String namaPenjaga;
	private String jantina;
	private String jenisAgama;
	private String jenisWarga;
	private Long umur;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String bandar;
	private Long idNegeri;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkpenjaga() {
	}

	/** minimal constructor */
	public AbstractTblppkpenjaga(Long idPenjaga, Long idObminor) {
		this.idPenjaga = idPenjaga;
		this.idObminor = idObminor;
	}

	/** full constructor */
	public AbstractTblppkpenjaga(Long idPenjaga, Long idObminor,
			String noKpBaru, String noKpLama, String jenisKp, String noKpLain,
			String namaPenjaga, String jantina, String jenisAgama,
			String jenisWarga, Long umur, String alamat1, String alamat2,
			String alamat3, String poskod, String bandar, Long idNegeri,
			String catatan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenjaga = idPenjaga;
		this.idObminor = idObminor;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.jenisKp = jenisKp;
		this.noKpLain = noKpLain;
		this.namaPenjaga = namaPenjaga;
		this.jantina = jantina;
		this.jenisAgama = jenisAgama;
		this.jenisWarga = jenisWarga;
		this.umur = umur;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.bandar = bandar;
		this.idNegeri = idNegeri;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenjaga() {
		return this.idPenjaga;
	}

	public void setIdPenjaga(Long idPenjaga) {
		this.idPenjaga = idPenjaga;
	}

	public Long getIdObminor() {
		return this.idObminor;
	}

	public void setIdObminor(Long idObminor) {
		this.idObminor = idObminor;
	}

	public String getNoKpBaru() {
		return this.noKpBaru;
	}

	public void setNoKpBaru(String noKpBaru) {
		this.noKpBaru = noKpBaru;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public String getJenisKp() {
		return this.jenisKp;
	}

	public void setJenisKp(String jenisKp) {
		this.jenisKp = jenisKp;
	}

	public String getNoKpLain() {
		return this.noKpLain;
	}

	public void setNoKpLain(String noKpLain) {
		this.noKpLain = noKpLain;
	}

	public String getNamaPenjaga() {
		return this.namaPenjaga;
	}

	public void setNamaPenjaga(String namaPenjaga) {
		this.namaPenjaga = namaPenjaga;
	}

	public String getJantina() {
		return this.jantina;
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
	}

	public String getJenisAgama() {
		return this.jenisAgama;
	}

	public void setJenisAgama(String jenisAgama) {
		this.jenisAgama = jenisAgama;
	}

	public String getJenisWarga() {
		return this.jenisWarga;
	}

	public void setJenisWarga(String jenisWarga) {
		this.jenisWarga = jenisWarga;
	}

	public Long getUmur() {
		return this.umur;
	}

	public void setUmur(Long umur) {
		this.umur = umur;
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

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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

}