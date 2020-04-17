package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperjanjiantambahan entity provides the base persistence
 * definition of the Tblhtpperjanjiantambahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjiantambahan implements
		java.io.Serializable {

	// Fields

	private Long idPerjanjiantambahan;
	private Tblhtppermohonan tblhtppermohonan;
	private Long idPerjanjian;
	private Date tarikhTandatangan;
	private Date tarikhTerima;
	private Date tarikhPerjanjian;
	private String sebab;
	private String tempoh;
	private String ulasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjiantambahan() {
	}

	/** minimal constructor */
	public AbstractTblhtpperjanjiantambahan(Long idPerjanjiantambahan,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian) {
		this.idPerjanjiantambahan = idPerjanjiantambahan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idPerjanjian = idPerjanjian;
	}

	/** full constructor */
	public AbstractTblhtpperjanjiantambahan(Long idPerjanjiantambahan,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian,
			Date tarikhTandatangan, Date tarikhTerima, Date tarikhPerjanjian,
			String sebab, String tempoh, String ulasan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idPerjanjiantambahan = idPerjanjiantambahan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idPerjanjian = idPerjanjian;
		this.tarikhTandatangan = tarikhTandatangan;
		this.tarikhTerima = tarikhTerima;
		this.tarikhPerjanjian = tarikhPerjanjian;
		this.sebab = sebab;
		this.tempoh = tempoh;
		this.ulasan = ulasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerjanjiantambahan() {
		return this.idPerjanjiantambahan;
	}

	public void setIdPerjanjiantambahan(Long idPerjanjiantambahan) {
		this.idPerjanjiantambahan = idPerjanjiantambahan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Long getIdPerjanjian() {
		return this.idPerjanjian;
	}

	public void setIdPerjanjian(Long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}

	public Date getTarikhTandatangan() {
		return this.tarikhTandatangan;
	}

	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhPerjanjian() {
		return this.tarikhPerjanjian;
	}

	public void setTarikhPerjanjian(Date tarikhPerjanjian) {
		this.tarikhPerjanjian = tarikhPerjanjian;
	}

	public String getSebab() {
		return this.sebab;
	}

	public void setSebab(String sebab) {
		this.sebab = sebab;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
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