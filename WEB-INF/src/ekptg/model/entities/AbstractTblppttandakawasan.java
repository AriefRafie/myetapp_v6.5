package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppttandakawasan entity provides the base persistence definition of
 * the Tblppttandakawasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppttandakawasan implements
		java.io.Serializable {

	// Fields

	private Long idTandakawasan;
	private Long caraLaksana;
	private Date tarikhMula;
	private Date tarikhAkhir;
	private String namaPegawai;
	private String flagTanda;
	private String alamatJuruukur;
	private String ulasan;
	private String noRujagensi;
	private Date tarikhLawat;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblppttandakawasan() {
	}

	/** full constructor */
	public AbstractTblppttandakawasan(Long caraLaksana, Date tarikhMula,
			Date tarikhAkhir, String namaPegawai, String flagTanda,
			String alamatJuruukur, String ulasan, String noRujagensi,
			Date tarikhLawat, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.caraLaksana = caraLaksana;
		this.tarikhMula = tarikhMula;
		this.tarikhAkhir = tarikhAkhir;
		this.namaPegawai = namaPegawai;
		this.flagTanda = flagTanda;
		this.alamatJuruukur = alamatJuruukur;
		this.ulasan = ulasan;
		this.noRujagensi = noRujagensi;
		this.tarikhLawat = tarikhLawat;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdTandakawasan() {
		return this.idTandakawasan;
	}

	public void setIdTandakawasan(Long idTandakawasan) {
		this.idTandakawasan = idTandakawasan;
	}

	public Long getCaraLaksana() {
		return this.caraLaksana;
	}

	public void setCaraLaksana(Long caraLaksana) {
		this.caraLaksana = caraLaksana;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhAkhir() {
		return this.tarikhAkhir;
	}

	public void setTarikhAkhir(Date tarikhAkhir) {
		this.tarikhAkhir = tarikhAkhir;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getFlagTanda() {
		return this.flagTanda;
	}

	public void setFlagTanda(String flagTanda) {
		this.flagTanda = flagTanda;
	}

	public String getAlamatJuruukur() {
		return this.alamatJuruukur;
	}

	public void setAlamatJuruukur(String alamatJuruukur) {
		this.alamatJuruukur = alamatJuruukur;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getNoRujagensi() {
		return this.noRujagensi;
	}

	public void setNoRujagensi(String noRujagensi) {
		this.noRujagensi = noRujagensi;
	}

	public Date getTarikhLawat() {
		return this.tarikhLawat;
	}

	public void setTarikhLawat(Date tarikhLawat) {
		this.tarikhLawat = tarikhLawat;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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