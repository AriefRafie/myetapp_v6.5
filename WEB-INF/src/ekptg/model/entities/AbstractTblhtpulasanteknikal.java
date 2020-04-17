package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpulasanteknikal entity provides the base persistence definition
 * of the Tblhtpulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpulasanteknikal implements
		java.io.Serializable {

	// Fields

	private String idUlasanteknikal;
	private Tblhtppermohonan tblhtppermohonan;
	private String noRujkjt;
	private Long idNegeri;
	private Long idDaerah;
	private Long idAgensi;
	private Long idKementerian;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private Date tarikhSuratKeputusan;
	private String ulasan;
	private String statusKeputusan;
	private String idPejabat;
	private String namaPegawai;
	private String idJnsdokumen;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtpulasanteknikal() {
	}

	/** minimal constructor */
	public AbstractTblhtpulasanteknikal(String idUlasanteknikal,
			Tblhtppermohonan tblhtppermohonan, Long idNegeri, Long idDaerah,
			Long idAgensi, Long idKementerian, Long idMasuk) {
		this.idUlasanteknikal = idUlasanteknikal;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idAgensi = idAgensi;
		this.idKementerian = idKementerian;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtpulasanteknikal(String idUlasanteknikal,
			Tblhtppermohonan tblhtppermohonan, String noRujkjt, Long idNegeri,
			Long idDaerah, Long idAgensi, Long idKementerian,
			Date tarikhHantar, Date tarikhTerima, Date tarikhSuratKeputusan,
			String ulasan, String statusKeputusan, String idPejabat,
			String namaPegawai, String idJnsdokumen, Long idMasuk,
			Date tarikhMasuk) {
		this.idUlasanteknikal = idUlasanteknikal;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noRujkjt = noRujkjt;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idAgensi = idAgensi;
		this.idKementerian = idKementerian;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.tarikhSuratKeputusan = tarikhSuratKeputusan;
		this.ulasan = ulasan;
		this.statusKeputusan = statusKeputusan;
		this.idPejabat = idPejabat;
		this.namaPegawai = namaPegawai;
		this.idJnsdokumen = idJnsdokumen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public String getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(String idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getNoRujkjt() {
		return this.noRujkjt;
	}

	public void setNoRujkjt(String noRujkjt) {
		this.noRujkjt = noRujkjt;
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

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public Long getIdKementerian() {
		return this.idKementerian;
	}

	public void setIdKementerian(Long idKementerian) {
		this.idKementerian = idKementerian;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhSuratKeputusan() {
		return this.tarikhSuratKeputusan;
	}

	public void setTarikhSuratKeputusan(Date tarikhSuratKeputusan) {
		this.tarikhSuratKeputusan = tarikhSuratKeputusan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getStatusKeputusan() {
		return this.statusKeputusan;
	}

	public void setStatusKeputusan(String statusKeputusan) {
		this.statusKeputusan = statusKeputusan;
	}

	public String getIdPejabat() {
		return this.idPejabat;
	}

	public void setIdPejabat(String idPejabat) {
		this.idPejabat = idPejabat;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getIdJnsdokumen() {
		return this.idJnsdokumen;
	}

	public void setIdJnsdokumen(String idJnsdokumen) {
		this.idJnsdokumen = idJnsdokumen;
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

}