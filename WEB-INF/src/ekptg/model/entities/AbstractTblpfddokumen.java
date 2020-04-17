package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfddokumen entity provides the base persistence definition of the
 * Tblpfddokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfddokumen implements java.io.Serializable {

	// Fields

	private Long idDokumen;
	private Long idFail;
	private Long idSubjaket;
	private String namaPengirim;
	private String noRujukanDokumen;
	private String bilMinitDokumen;
	private Date tarikhDokumenMasuk;
	private Date tarikhDokumenKeluar;
	private Date tarikhDaftar;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idJenisdokumen;
	private String namaPenerima;
	private String namaPegawai;
	private Long idDb;
	private String flagDokumen;

	// Constructors

	/** default constructor */
	public AbstractTblpfddokumen() {
	}

	/** minimal constructor */
	public AbstractTblpfddokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	/** full constructor */
	public AbstractTblpfddokumen(Long idDokumen, Long idFail, Long idSubjaket,
			String namaPengirim, String noRujukanDokumen,
			String bilMinitDokumen, Date tarikhDokumenMasuk,
			Date tarikhDokumenKeluar, Date tarikhDaftar, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idJenisdokumen, String namaPenerima,
			String namaPegawai, Long idDb, String flagDokumen) {
		this.idDokumen = idDokumen;
		this.idFail = idFail;
		this.idSubjaket = idSubjaket;
		this.namaPengirim = namaPengirim;
		this.noRujukanDokumen = noRujukanDokumen;
		this.bilMinitDokumen = bilMinitDokumen;
		this.tarikhDokumenMasuk = tarikhDokumenMasuk;
		this.tarikhDokumenKeluar = tarikhDokumenKeluar;
		this.tarikhDaftar = tarikhDaftar;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idJenisdokumen = idJenisdokumen;
		this.namaPenerima = namaPenerima;
		this.namaPegawai = namaPegawai;
		this.idDb = idDb;
		this.flagDokumen = flagDokumen;
	}

	// Property accessors

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Long getIdSubjaket() {
		return this.idSubjaket;
	}

	public void setIdSubjaket(Long idSubjaket) {
		this.idSubjaket = idSubjaket;
	}

	public String getNamaPengirim() {
		return this.namaPengirim;
	}

	public void setNamaPengirim(String namaPengirim) {
		this.namaPengirim = namaPengirim;
	}

	public String getNoRujukanDokumen() {
		return this.noRujukanDokumen;
	}

	public void setNoRujukanDokumen(String noRujukanDokumen) {
		this.noRujukanDokumen = noRujukanDokumen;
	}

	public String getBilMinitDokumen() {
		return this.bilMinitDokumen;
	}

	public void setBilMinitDokumen(String bilMinitDokumen) {
		this.bilMinitDokumen = bilMinitDokumen;
	}

	public Date getTarikhDokumenMasuk() {
		return this.tarikhDokumenMasuk;
	}

	public void setTarikhDokumenMasuk(Date tarikhDokumenMasuk) {
		this.tarikhDokumenMasuk = tarikhDokumenMasuk;
	}

	public Date getTarikhDokumenKeluar() {
		return this.tarikhDokumenKeluar;
	}

	public void setTarikhDokumenKeluar(Date tarikhDokumenKeluar) {
		this.tarikhDokumenKeluar = tarikhDokumenKeluar;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public Long getIdJenisdokumen() {
		return this.idJenisdokumen;
	}

	public void setIdJenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	public String getNamaPenerima() {
		return this.namaPenerima;
	}

	public void setNamaPenerima(String namaPenerima) {
		this.namaPenerima = namaPenerima;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

	public String getFlagDokumen() {
		return this.flagDokumen;
	}

	public void setFlagDokumen(String flagDokumen) {
		this.flagDokumen = flagDokumen;
	}

}