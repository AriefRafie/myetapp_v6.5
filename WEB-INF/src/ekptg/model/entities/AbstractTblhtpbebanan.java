package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpbebanan entity provides the base persistence definition of the
 * Tblhtpbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbebanan implements java.io.Serializable {

	// Fields

	private Long idBebanan;
	private Long idPihakberkepentingan;
	private String noPihakBerkepentingan;
	private String namaPihakBerkepentingan;
	private String noPerserahan;
	private String jilid;
	private String folio;
	private String jawatan;
	private String statusDaftar;
	private Date tarikhDaftar;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idDaerah;
	private Long idNegeri;
	private String noTel;
	private String noFax;
	private Long idRujbebanan;
	private Long idRujpihakberkepentingan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblhtpbebanan() {
	}

	/** minimal constructor */
	public AbstractTblhtpbebanan(Long idPihakberkepentingan,
			String noPerserahan, String jilid, String folio, Long idDaerah,
			Long idNegeri, Long idRujbebanan, Long idRujpihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.noPerserahan = noPerserahan;
		this.jilid = jilid;
		this.folio = folio;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.idRujbebanan = idRujbebanan;
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
	}

	/** full constructor */
	public AbstractTblhtpbebanan(Long idPihakberkepentingan,
			String noPihakBerkepentingan, String namaPihakBerkepentingan,
			String noPerserahan, String jilid, String folio, String jawatan,
			String statusDaftar, Date tarikhDaftar, String alamat1,
			String alamat2, String alamat3, String poskod, Long idDaerah,
			Long idNegeri, String noTel, String noFax, Long idRujbebanan,
			Long idRujpihakberkepentingan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.noPihakBerkepentingan = noPihakBerkepentingan;
		this.namaPihakBerkepentingan = namaPihakBerkepentingan;
		this.noPerserahan = noPerserahan;
		this.jilid = jilid;
		this.folio = folio;
		this.jawatan = jawatan;
		this.statusDaftar = statusDaftar;
		this.tarikhDaftar = tarikhDaftar;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.noTel = noTel;
		this.noFax = noFax;
		this.idRujbebanan = idRujbebanan;
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBebanan() {
		return this.idBebanan;
	}

	public void setIdBebanan(Long idBebanan) {
		this.idBebanan = idBebanan;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public String getNoPihakBerkepentingan() {
		return this.noPihakBerkepentingan;
	}

	public void setNoPihakBerkepentingan(String noPihakBerkepentingan) {
		this.noPihakBerkepentingan = noPihakBerkepentingan;
	}

	public String getNamaPihakBerkepentingan() {
		return this.namaPihakBerkepentingan;
	}

	public void setNamaPihakBerkepentingan(String namaPihakBerkepentingan) {
		this.namaPihakBerkepentingan = namaPihakBerkepentingan;
	}

	public String getNoPerserahan() {
		return this.noPerserahan;
	}

	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}

	public String getJilid() {
		return this.jilid;
	}

	public void setJilid(String jilid) {
		this.jilid = jilid;
	}

	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getStatusDaftar() {
		return this.statusDaftar;
	}

	public void setStatusDaftar(String statusDaftar) {
		this.statusDaftar = statusDaftar;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
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

	public Long getIdRujbebanan() {
		return this.idRujbebanan;
	}

	public void setIdRujbebanan(Long idRujbebanan) {
		this.idRujbebanan = idRujbebanan;
	}

	public Long getIdRujpihakberkepentingan() {
		return this.idRujpihakberkepentingan;
	}

	public void setIdRujpihakberkepentingan(Long idRujpihakberkepentingan) {
		this.idRujpihakberkepentingan = idRujpihakberkepentingan;
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