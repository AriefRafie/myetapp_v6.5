package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppembelipasir entity provides the base persistence definition of
 * the Tblphppembelipasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppembelipasir implements
		java.io.Serializable {

	// Fields

	private Long idPembelipasir;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private String nama;
	private Long idJenispengenalan;
	private String noKp;
	private String noKpLama;
	private Long idWarnakp;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idBandar;
	private Long idNegeri;
	private String noTelRumah;
	private String noTelPejabat;
	private String extTel;
	private String noFax;
	private String noTelBimbit;
	private String emel;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppembelipasir() {
	}

	/** minimal constructor */
	public AbstractTblphppembelipasir(Long idPembelipasir) {
		this.idPembelipasir = idPembelipasir;
	}

	/** full constructor */
	public AbstractTblphppembelipasir(Long idPembelipasir,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, String nama,
			Long idJenispengenalan, String noKp, String noKpLama,
			Long idWarnakp, String alamat1, String alamat2, String alamat3,
			String poskod, Long idBandar, Long idNegeri, String noTelRumah,
			String noTelPejabat, String extTel, String noFax,
			String noTelBimbit, String emel, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPembelipasir = idPembelipasir;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.nama = nama;
		this.idJenispengenalan = idJenispengenalan;
		this.noKp = noKp;
		this.noKpLama = noKpLama;
		this.idWarnakp = idWarnakp;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idBandar = idBandar;
		this.idNegeri = idNegeri;
		this.noTelRumah = noTelRumah;
		this.noTelPejabat = noTelPejabat;
		this.extTel = extTel;
		this.noFax = noFax;
		this.noTelBimbit = noTelBimbit;
		this.emel = emel;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPembelipasir() {
		return this.idPembelipasir;
	}

	public void setIdPembelipasir(Long idPembelipasir) {
		this.idPembelipasir = idPembelipasir;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getIdJenispengenalan() {
		return this.idJenispengenalan;
	}

	public void setIdJenispengenalan(Long idJenispengenalan) {
		this.idJenispengenalan = idJenispengenalan;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public Long getIdWarnakp() {
		return this.idWarnakp;
	}

	public void setIdWarnakp(Long idWarnakp) {
		this.idWarnakp = idWarnakp;
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

	public Long getIdBandar() {
		return this.idBandar;
	}

	public void setIdBandar(Long idBandar) {
		this.idBandar = idBandar;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoTelRumah() {
		return this.noTelRumah;
	}

	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}

	public String getNoTelPejabat() {
		return this.noTelPejabat;
	}

	public void setNoTelPejabat(String noTelPejabat) {
		this.noTelPejabat = noTelPejabat;
	}

	public String getExtTel() {
		return this.extTel;
	}

	public void setExtTel(String extTel) {
		this.extTel = extTel;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public String getNoTelBimbit() {
		return this.noTelBimbit;
	}

	public void setNoTelBimbit(String noTelBimbit) {
		this.noTelBimbit = noTelBimbit;
	}

	public String getEmel() {
		return this.emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
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