package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppkunit entity provides the base persistence definition of the
 * Tblrujppkunit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppkunit implements java.io.Serializable {

	// Fields

	private Long idUnitpsk;
	private Long idNegeri;
	private Long idJkptg;
	private String namaPejabat;
	private String keteranganUnitPsk;
	private String namaPegawai;
	private String jawatan;
	private String statusPeg;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String alamat4;
	private String poskod;
	private String noTel;
	private String noTelSambungan;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkbkes = new HashSet(0);
	private Set tblppkperintahs = new HashSet(0);
	private Set tblppkpermohonans = new HashSet(0);
	private Set tblppkrayuans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppkunit() {
	}

	/** minimal constructor */
	public AbstractTblrujppkunit(Long idUnitpsk, Long idNegeri) {
		this.idUnitpsk = idUnitpsk;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblrujppkunit(Long idUnitpsk, Long idNegeri, Long idJkptg,
			String namaPejabat, String keteranganUnitPsk, String namaPegawai,
			String jawatan, String statusPeg, String alamat1, String alamat2,
			String alamat3, String alamat4, String poskod, String noTel,
			String noTelSambungan, String catatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkbkes, Set tblppkperintahs, Set tblppkpermohonans,
			Set tblppkrayuans) {
		this.idUnitpsk = idUnitpsk;
		this.idNegeri = idNegeri;
		this.idJkptg = idJkptg;
		this.namaPejabat = namaPejabat;
		this.keteranganUnitPsk = keteranganUnitPsk;
		this.namaPegawai = namaPegawai;
		this.jawatan = jawatan;
		this.statusPeg = statusPeg;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.alamat4 = alamat4;
		this.poskod = poskod;
		this.noTel = noTel;
		this.noTelSambungan = noTelSambungan;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkbkes = tblppkbkes;
		this.tblppkperintahs = tblppkperintahs;
		this.tblppkpermohonans = tblppkpermohonans;
		this.tblppkrayuans = tblppkrayuans;
	}

	// Property accessors

	public Long getIdUnitpsk() {
		return this.idUnitpsk;
	}

	public void setIdUnitpsk(Long idUnitpsk) {
		this.idUnitpsk = idUnitpsk;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdJkptg() {
		return this.idJkptg;
	}

	public void setIdJkptg(Long idJkptg) {
		this.idJkptg = idJkptg;
	}

	public String getNamaPejabat() {
		return this.namaPejabat;
	}

	public void setNamaPejabat(String namaPejabat) {
		this.namaPejabat = namaPejabat;
	}

	public String getKeteranganUnitPsk() {
		return this.keteranganUnitPsk;
	}

	public void setKeteranganUnitPsk(String keteranganUnitPsk) {
		this.keteranganUnitPsk = keteranganUnitPsk;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getStatusPeg() {
		return this.statusPeg;
	}

	public void setStatusPeg(String statusPeg) {
		this.statusPeg = statusPeg;
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

	public String getAlamat4() {
		return this.alamat4;
	}

	public void setAlamat4(String alamat4) {
		this.alamat4 = alamat4;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getNoTelSambungan() {
		return this.noTelSambungan;
	}

	public void setNoTelSambungan(String noTelSambungan) {
		this.noTelSambungan = noTelSambungan;
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

	public Set getTblppkbkes() {
		return this.tblppkbkes;
	}

	public void setTblppkbkes(Set tblppkbkes) {
		this.tblppkbkes = tblppkbkes;
	}

	public Set getTblppkperintahs() {
		return this.tblppkperintahs;
	}

	public void setTblppkperintahs(Set tblppkperintahs) {
		this.tblppkperintahs = tblppkperintahs;
	}

	public Set getTblppkpermohonans() {
		return this.tblppkpermohonans;
	}

	public void setTblppkpermohonans(Set tblppkpermohonans) {
		this.tblppkpermohonans = tblppkpermohonans;
	}

	public Set getTblppkrayuans() {
		return this.tblppkrayuans;
	}

	public void setTblppkrayuans(Set tblppkrayuans) {
		this.tblppkrayuans = tblppkrayuans;
	}

}