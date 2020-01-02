package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptbuktipenyampaian entity provides the base persistence
 * definition of the Tblpptbuktipenyampaian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptbuktipenyampaian implements
		java.io.Serializable {

	// Fields

	private Long idBuktipenyampaian;
	private String noDokumen;
	private Long idJenisdokumen;
	private String flagSerah;
	private String catatan;
	private Long jenisSerahan;
	private String namaPenerima;
	private String noKpPenerima;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private Long idPermohonan;
	private Long idBantahan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptbuktipenyampaian() {
	}

	/** full constructor */
	public AbstractTblpptbuktipenyampaian(String noDokumen,
			Long idJenisdokumen, String flagSerah, String catatan,
			Long jenisSerahan, String namaPenerima, String noKpPenerima,
			Date tarikhHantar, Date tarikhTerima, Long idPermohonan,
			Long idBantahan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.noDokumen = noDokumen;
		this.idJenisdokumen = idJenisdokumen;
		this.flagSerah = flagSerah;
		this.catatan = catatan;
		this.jenisSerahan = jenisSerahan;
		this.namaPenerima = namaPenerima;
		this.noKpPenerima = noKpPenerima;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.idPermohonan = idPermohonan;
		this.idBantahan = idBantahan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBuktipenyampaian() {
		return this.idBuktipenyampaian;
	}

	public void setIdBuktipenyampaian(Long idBuktipenyampaian) {
		this.idBuktipenyampaian = idBuktipenyampaian;
	}

	public String getNoDokumen() {
		return this.noDokumen;
	}

	public void setNoDokumen(String noDokumen) {
		this.noDokumen = noDokumen;
	}

	public Long getIdJenisdokumen() {
		return this.idJenisdokumen;
	}

	public void setIdJenisdokumen(Long idJenisdokumen) {
		this.idJenisdokumen = idJenisdokumen;
	}

	public String getFlagSerah() {
		return this.flagSerah;
	}

	public void setFlagSerah(String flagSerah) {
		this.flagSerah = flagSerah;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getJenisSerahan() {
		return this.jenisSerahan;
	}

	public void setJenisSerahan(Long jenisSerahan) {
		this.jenisSerahan = jenisSerahan;
	}

	public String getNamaPenerima() {
		return this.namaPenerima;
	}

	public void setNamaPenerima(String namaPenerima) {
		this.namaPenerima = namaPenerima;
	}

	public String getNoKpPenerima() {
		return this.noKpPenerima;
	}

	public void setNoKpPenerima(String noKpPenerima) {
		this.noKpPenerima = noKpPenerima;
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

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdBantahan() {
		return this.idBantahan;
	}

	public void setIdBantahan(Long idBantahan) {
		this.idBantahan = idBantahan;
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