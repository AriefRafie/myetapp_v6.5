package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptjabatanteknikal entity provides the base persistence definition
 * of the Tblpptjabatanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptjabatanteknikal implements
		java.io.Serializable {

	// Fields

	private Long idJabatanteknikal;
	private String kodJabatanTeknikal;
	private String namaJabatan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
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
	public AbstractTblpptjabatanteknikal() {
	}

	/** full constructor */
	public AbstractTblpptjabatanteknikal(String kodJabatanTeknikal,String namaJabatan, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			String noTel, String noFax, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.kodJabatanTeknikal = kodJabatanTeknikal;
		this.namaJabatan = namaJabatan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
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

	public Long getIdJabatanteknikal() {
		return this.idJabatanteknikal;
	}

	public void setIdJabatanteknikal(Long idJabatanteknikal) {
		this.idJabatanteknikal = idJabatanteknikal;
	}
	
	public String getKodJabatanTeknikal() {
		return this.kodJabatanTeknikal;
	}

	public void setKodJabatanTeknikal(String kodJabatanTeknikal) {
		this.kodJabatanTeknikal = kodJabatanTeknikal;
	}

	public String getNamaJabatan() {
		return this.namaJabatan;
	}

	public void setNamaJabatan(String namaJabatan) {
		this.namaJabatan = namaJabatan;
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
