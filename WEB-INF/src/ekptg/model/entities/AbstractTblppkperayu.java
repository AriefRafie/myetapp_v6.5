package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkperayu entity provides the base persistence definition of the
 * Tblppkperayu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperayu implements java.io.Serializable {

	// Fields

	private Long idPerayu;
	private Tblppkrayuan tblppkrayuan;
	private String namaPerayu;
	private String noKpBaru;
	private String noKpLama;
	private String noKpLain;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private Long idTarafkptg;
	private Long idNegeri;
	private Long idDaerah;
	private String jenisKp;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkperayu() {
	}

	/** minimal constructor */
	public AbstractTblppkperayu(Long idPerayu, Tblppkrayuan tblppkrayuan) {
		this.idPerayu = idPerayu;
		this.tblppkrayuan = tblppkrayuan;
	}

	/** full constructor */
	public AbstractTblppkperayu(Long idPerayu, Tblppkrayuan tblppkrayuan,
			String namaPerayu, String noKpBaru, String noKpLama,
			String noKpLain, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, Long idTarafkptg, Long idNegeri,
			Long idDaerah, String jenisKp, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPerayu = idPerayu;
		this.tblppkrayuan = tblppkrayuan;
		this.namaPerayu = namaPerayu;
		this.noKpBaru = noKpBaru;
		this.noKpLama = noKpLama;
		this.noKpLain = noKpLain;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.idTarafkptg = idTarafkptg;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.jenisKp = jenisKp;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerayu() {
		return this.idPerayu;
	}

	public void setIdPerayu(Long idPerayu) {
		this.idPerayu = idPerayu;
	}

	public Tblppkrayuan getTblppkrayuan() {
		return this.tblppkrayuan;
	}

	public void setTblppkrayuan(Tblppkrayuan tblppkrayuan) {
		this.tblppkrayuan = tblppkrayuan;
	}

	public String getNamaPerayu() {
		return this.namaPerayu;
	}

	public void setNamaPerayu(String namaPerayu) {
		this.namaPerayu = namaPerayu;
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

	public String getNoKpLain() {
		return this.noKpLain;
	}

	public void setNoKpLain(String noKpLain) {
		this.noKpLain = noKpLain;
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

	public String getBandar() {
		return this.bandar;
	}

	public void setBandar(String bandar) {
		this.bandar = bandar;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdTarafkptg() {
		return this.idTarafkptg;
	}

	public void setIdTarafkptg(Long idTarafkptg) {
		this.idTarafkptg = idTarafkptg;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getJenisKp() {
		return this.jenisKp;
	}

	public void setJenisKp(String jenisKp) {
		this.jenisKp = jenisKp;
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