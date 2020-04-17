package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujagensi entity provides the base persistence definition of the
 * Tblrujagensi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujagensi implements java.io.Serializable {

	// Fields

	private Long idAgensi;
	private String kodAgensi;
	private String namaAgensi;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String jawatan;
	private Long idKementerian;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujagensi() {
	}

	/** minimal constructor */
	public AbstractTblrujagensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	/** full constructor */
	public AbstractTblrujagensi(Long idAgensi, String kodAgensi,
			String namaAgensi, String alamat1, String alamat2, String alamat3,
			String poskod, Long idNegeri, String jawatan, Long idKementerian,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idAgensi = idAgensi;
		this.kodAgensi = kodAgensi;
		this.namaAgensi = namaAgensi;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.jawatan = jawatan;
		this.idKementerian = idKementerian;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public String getKodAgensi() {
		return this.kodAgensi;
	}

	public void setKodAgensi(String kodAgensi) {
		this.kodAgensi = kodAgensi;
	}

	public String getNamaAgensi() {
		return this.namaAgensi;
	}

	public void setNamaAgensi(String namaAgensi) {
		this.namaAgensi = namaAgensi;
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

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
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