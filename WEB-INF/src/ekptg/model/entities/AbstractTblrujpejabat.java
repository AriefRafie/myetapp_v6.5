package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujpejabat entity provides the base persistence definition of the
 * Tblrujpejabat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpejabat implements java.io.Serializable {

	// Fields

	private Long idPejabat;
	private Long idDaerah;
	private Long idSeksyen;
	private Long idNegeri;
	private String jenisPejabat;
	private String kodPejabat;
	private String namaPejabat;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String noTel;
	private String noFax;
	private String jawatan;
	private String noAkaun;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujpejabat() {
	}

	/** minimal constructor */
	public AbstractTblrujpejabat(Long idDaerah,
			Long idSeksyen, Long idNegeri) {
		this.idDaerah = idDaerah;
		this.idSeksyen = idSeksyen;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblrujpejabat(Long idDaerah,
			Long idSeksyen, Long idNegeri,
			String jenisPejabat, String kodPejabat, String namaPejabat, String alamat1,
			String alamat2, String alamat3, String poskod, String noTel,
			String noFax, String jawatan, String noAkaun, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idDaerah = idDaerah;
		this.idSeksyen = idSeksyen;
		this.idNegeri = idNegeri;
		this.jenisPejabat = jenisPejabat;
		this.kodPejabat = kodPejabat;
		this.namaPejabat = namaPejabat;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.noTel = noTel;
		this.noFax = noFax;
		this.jawatan = jawatan;
		this.noAkaun = noAkaun;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPejabat() {
		return this.idPejabat;
	}

	public void setIdPejabat(Long idPejabat) {
		this.idPejabat = idPejabat;
	}
	
	public String getKodPejabat() {
		return this.kodPejabat;
	}

	public void setKodPejabat(String kodPejabat) {
		this.kodPejabat = kodPejabat;
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

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
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

    public void setIdDaerah(Long idDaerah) {
        this.idDaerah = idDaerah;
    }

    public Long getIdDaerah() {
        return idDaerah;
    }

    public void setIdSeksyen(Long idSeksyen) {
        this.idSeksyen = idSeksyen;
    }

    public Long getIdSeksyen() {
        return idSeksyen;
    }

    public void setIdNegeri(Long idNegeri) {
        this.idNegeri = idNegeri;
    }

    public Long getIdNegeri() {
        return idNegeri;
    }
}
