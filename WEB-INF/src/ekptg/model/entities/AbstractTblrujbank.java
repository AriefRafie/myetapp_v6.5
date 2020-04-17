package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujbank entity provides the base persistence definition of the
 * Tblrujbank entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbank implements java.io.Serializable {

	// Fields

	private Long idBank;
	private String kodBank;
	private String namaBank;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujbank() {
	}

	/** full constructor */
	public AbstractTblrujbank(String kodBank, String namaBank, String alamat1,
			String alamat2, String alamat3, String poskod, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.kodBank = kodBank;
		this.namaBank = namaBank;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public String getKodBank() {
		return this.kodBank;
	}

	public void setKodBank(String kodBank) {
		this.kodBank = kodBank;
	}

	public String getNamaBank() {
		return this.namaBank;
	}

	public void setNamaBank(String namaBank) {
		this.namaBank = namaBank;
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