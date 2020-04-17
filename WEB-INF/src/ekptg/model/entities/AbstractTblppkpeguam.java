package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkpeguam entity provides the base persistence definition of the
 * Tblppkpeguam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkpeguam implements java.io.Serializable {

	// Fields

	private Long idPeguam;
        private Long idPemohon;
	//private Tblppkpemohon tblppkpemohon;
	private String namaFirma;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String bandar;
	private String poskod;
	private Long idNegeri;
	private String noRujukanFirma;
	private String noTel;
	private String noFax;
	private String noSyarikat;
	private String namaPeguam;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private String emel;

	// Constructors

	/** default constructor */
	public AbstractTblppkpeguam() {
	}

	/** minimal constructor */
	public AbstractTblppkpeguam(Long idPeguam) {
		this.idPeguam = idPeguam;
		//this.tblppkpemohon = tblppkpemohon;
	}

	/** full constructor */
	public AbstractTblppkpeguam(Long idPeguam, Long idPemohon,
                        //Tblppkpemohon tblppkpemohon,
			String namaFirma, String alamat1, String alamat2, String alamat3,
			String bandar, String poskod, Long idNegeri, String noRujukanFirma,
			String noTel, String noFax, String noSyarikat, String namaPeguam,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini,String emel) {
		this.idPeguam = idPeguam;
                this.idPemohon = idPemohon;
		//this.tblppkpemohon = tblppkpemohon;
		this.namaFirma = namaFirma;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.bandar = bandar;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.noRujukanFirma = noRujukanFirma;
		this.noTel = noTel;
		this.noFax = noFax;
		this.noSyarikat = noSyarikat;
		this.namaPeguam = namaPeguam;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.emel = emel;
	}

	// Property accessors

	public Long getIdPeguam() {
		return this.idPeguam;
	}

	public void setIdPeguam(Long idPeguam) {
		this.idPeguam = idPeguam;
	}

/*	public Tblppkpemohon getTblppkpemohon() {
		return this.tblppkpemohon;
	}

	public void setTblppkpemohon(Tblppkpemohon tblppkpemohon) {
		this.tblppkpemohon = tblppkpemohon;
	}
*/
	public String getNamaFirma() {
		return this.namaFirma;
	}

	public void setNamaFirma(String namaFirma) {
		this.namaFirma = namaFirma;
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

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoRujukanFirma() {
		return this.noRujukanFirma;
	}

	public void setNoRujukanFirma(String noRujukanFirma) {
		this.noRujukanFirma = noRujukanFirma;
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

	public String getNoSyarikat() {
		return this.noSyarikat;
	}

	public void setNoSyarikat(String noSyarikat) {
		this.noSyarikat = noSyarikat;
	}

	public String getNamaPeguam() {
		return this.namaPeguam;
	}

	public void setNamaPeguam(String namaPeguam) {
		this.namaPeguam = namaPeguam;
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

    public void setIdPemohon(Long idPemohon) {
        this.idPemohon = idPemohon;
    }

    public Long getIdPemohon() {
        return idPemohon;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getEmel() {
        return emel;
    }
}
