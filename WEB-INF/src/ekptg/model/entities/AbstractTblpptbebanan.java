package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptbebanan entity provides the base persistence definition of the
 * Tblpptbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptbebanan implements java.io.Serializable {

	// Fields

	private Long idBebanan;
	private String jilid;
	private String folio;
	private Long idKodbebanan;
	private Long idJenisnopb;
	private String noPb;
	private String noKpLama;
	private String nama;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private Long idHakmilik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptbebanan() {
	}

	/** full constructor */
	public AbstractTblpptbebanan(String jilid, String folio, Long idKodbebanan,
			Long idJenisnopb, String noPb, String noKpLama, String nama,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, Long idHakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.jilid = jilid;
		this.folio = folio;
		this.idKodbebanan = idKodbebanan;
		this.idJenisnopb = idJenisnopb;
		this.noPb = noPb;
		this.noKpLama = noKpLama;
		this.nama = nama;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.idHakmilik = idHakmilik;
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

	public Long getIdKodbebanan() {
		return this.idKodbebanan;
	}

	public void setIdKodbebanan(Long idKodbebanan) {
		this.idKodbebanan = idKodbebanan;
	}

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public String getNoPb() {
		return this.noPb;
	}

	public void setNoPb(String noPb) {
		this.noPb = noPb;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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