package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujpejabatjkptg entity provides the base persistence definition of
 * the Tblrujpejabatjkptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpejabatjkptg implements
		java.io.Serializable {

	// Fields

	private Long idPejabatjkptg;
	private Long idSeksyen;
	private Long idDb;
	private String kodJkptg;
	private String jenisPejabat;
	private String namaPejabat;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private Long idDaerah;
	private String prefix;
	private String noTel;
	private String noFax;
	private String jawatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujpejabatjkptg() {
	}

	/** minimal constructor */
	public AbstractTblrujpejabatjkptg(Long idSeksyen, Long idNegeri,
			Long idDaerah) {
		this.idSeksyen = idSeksyen;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public AbstractTblrujpejabatjkptg(Long idSeksyen, Long idDb,
			String kodJkptg, String jenisPejabat, String namaPejabat,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, Long idDaerah, String prefix, String noTel,
			String noFax, String jawatan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idSeksyen = idSeksyen;
		this.idDb = idDb;
		this.kodJkptg = kodJkptg;
		this.jenisPejabat = jenisPejabat;
		this.namaPejabat = namaPejabat;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.prefix = prefix;
		this.noTel = noTel;
		this.noFax = noFax;
		this.jawatan = jawatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPejabatjkptg() {
		return this.idPejabatjkptg;
	}

	public void setIdPejabatjkptg(Long idPejabatjkptg) {
		this.idPejabatjkptg = idPejabatjkptg;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

	public String getKodJkptg() {
		return this.kodJkptg;
	}

	public void setKodJkptg(String kodJkptg) {
		this.kodJkptg = kodJkptg;
	}

	public String getJenisPejabat() {
		return this.jenisPejabat;
	}

	public void setJenisPejabat(String jenisPejabat) {
		this.jenisPejabat = jenisPejabat;
	}

	public String getNamaPejabat() {
		return this.namaPejabat;
	}

	public void setNamaPejabat(String namaPejabat) {
		this.namaPejabat = namaPejabat;
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

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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