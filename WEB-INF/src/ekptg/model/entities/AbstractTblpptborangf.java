package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangf entity provides the base persistence definition of the
 * Tblpptborangf entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangf implements java.io.Serializable {

	// Fields

	private Long idBorangf;
	private Long idPermohonan;
	private Long idPihakberkepentingan;
	private Long idJenispb;
	private String nama;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private Date tarikhBorangf;
	private Date tarikhHantar;
	private String tempoh;
	private Long unitTempoh;
	private String ulasan;
	private Date tarikhTerima;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangf() {
	}

	/** full constructor */
	public AbstractTblpptborangf(Long idPermohonan, Long idPihakberkepentingan,
			Long idJenispb, String nama, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, Date tarikhBorangf,
			Date tarikhHantar, String tempoh, Long unitTempoh, String ulasan,
			Date tarikhTerima, Date tarikhCetak, Date tarikhCetakSemula,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idPermohonan = idPermohonan;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idJenispb = idJenispb;
		this.nama = nama;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.tarikhBorangf = tarikhBorangf;
		this.tarikhHantar = tarikhHantar;
		this.tempoh = tempoh;
		this.unitTempoh = unitTempoh;
		this.ulasan = ulasan;
		this.tarikhTerima = tarikhTerima;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangf() {
		return this.idBorangf;
	}

	public void setIdBorangf(Long idBorangf) {
		this.idBorangf = idBorangf;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
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

	public Date getTarikhBorangf() {
		return this.tarikhBorangf;
	}

	public void setTarikhBorangf(Date tarikhBorangf) {
		this.tarikhBorangf = tarikhBorangf;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Date getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Date tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
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