package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpdokumen entity provides the base persistence definition of the
 * Tblhtpdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpdokumen implements java.io.Serializable {

	// Fields

	private Long idDokumen;
	private Long idPermohonan;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private String jenisDokumen;
	private String pihak;
	private String ulasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpdokumen() {
	}

	/** minimal constructor */
	public AbstractTblhtpdokumen(Long idDokumen, Long idPermohonan) {
		this.idDokumen = idDokumen;
		this.idPermohonan = idPermohonan;
	}

	/** full constructor */
	public AbstractTblhtpdokumen(Long idDokumen, Long idPermohonan,
			Date tarikhHantar, Date tarikhTerima, String jenisDokumen,
			String pihak, String ulasan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDokumen = idDokumen;
		this.idPermohonan = idPermohonan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.jenisDokumen = jenisDokumen;
		this.pihak = pihak;
		this.ulasan = ulasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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

	public String getJenisDokumen() {
		return this.jenisDokumen;
	}

	public void setJenisDokumen(String jenisDokumen) {
		this.jenisDokumen = jenisDokumen;
	}

	public String getPihak() {
		return this.pihak;
	}

	public void setPihak(String pihak) {
		this.pihak = pihak;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
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