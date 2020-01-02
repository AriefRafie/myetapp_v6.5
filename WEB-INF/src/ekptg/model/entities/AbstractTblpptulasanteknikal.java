package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptulasanteknikal entity provides the base persistence definition
 * of the Tblpptulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptulasanteknikal implements
		java.io.Serializable {

	// Fields

	private Long idUlasanteknikal;
	private Long idJabatanteknikal;
	private String noRujukanSurat;
	private Date tarikhSurat;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private Long bilSurat;
	private Long tempoh;
	private Date tarikhMula;
	private Date tarikhAkhir;
	private String keputusan;
	private String ulasan;
	private String flagTerima;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptulasanteknikal() {
	}

	/** full constructor */
	public AbstractTblpptulasanteknikal(Long idJabatanteknikal,
			String noRujukanSurat, Date tarikhSurat, Date tarikhHantar,
			Date tarikhTerima, Long bilSurat, Long tempoh, Date tarikhMula,
			Date tarikhAkhir, String keputusan, String ulasan,
			String flagTerima, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idJabatanteknikal = idJabatanteknikal;
		this.noRujukanSurat = noRujukanSurat;
		this.tarikhSurat = tarikhSurat;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.bilSurat = bilSurat;
		this.tempoh = tempoh;
		this.tarikhMula = tarikhMula;
		this.tarikhAkhir = tarikhAkhir;
		this.keputusan = keputusan;
		this.ulasan = ulasan;
		this.flagTerima = flagTerima;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
	}

	public Long getIdJabatanteknikal() {
		return this.idJabatanteknikal;
	}

	public void setIdJabatanteknikal(Long idJabatanteknikal) {
		this.idJabatanteknikal = idJabatanteknikal;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
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

	public Long getBilSurat() {
		return this.bilSurat;
	}

	public void setBilSurat(Long bilSurat) {
		this.bilSurat = bilSurat;
	}

	public Long getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(Long tempoh) {
		this.tempoh = tempoh;
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

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getFlagTerima() {
		return this.flagTerima;
	}

	public void setFlagTerima(String flagTerima) {
		this.flagTerima = flagTerima;
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