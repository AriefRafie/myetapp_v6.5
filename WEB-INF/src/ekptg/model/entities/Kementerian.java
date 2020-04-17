package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujkementerian entity provides the base persistence definition of
 * the Tblrujkementerian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Kementerian implements java.io.Serializable {

	// Fields

	private Long idKementerian;
	private String kodKementerian;
	private String namaKementerian;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String jawatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public Kementerian() {
	}

	/** minimal constructor */
	public Kementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	/** full constructor */
	public Kementerian(Long idKementerian, String kodKementerian,
			String namaKementerian, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, String jawatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idKementerian = idKementerian;
		this.kodKementerian = kodKementerian;
		this.namaKementerian = namaKementerian;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.jawatan = jawatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public String getKodKementerian() {
		return this.kodKementerian;
	}

	public void setKodKementerian(String kodKementerian) {
		this.kodKementerian = kodKementerian;
	}

	public String getNamaKementerian() {
		return this.namaKementerian;
	}

	public void setNamaKementerian(String namaKementerian) {
		this.namaKementerian = namaKementerian;
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

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
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