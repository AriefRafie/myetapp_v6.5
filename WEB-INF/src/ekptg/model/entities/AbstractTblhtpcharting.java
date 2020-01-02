package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpcharting entity provides the base persistence definition of the
 * Tblhtpcharting entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpcharting implements java.io.Serializable {

	// Fields

	private Long idCharting;
	private Tblhtppermohonan tblhtppermohonan;
	private String noRujukanSeksyen;
	private String flagPelan;
	private String flagCharting;
	private String ulasan;
	private Double jumlahBayaranProses;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpcharting() {
	}

	/** minimal constructor */
	public AbstractTblhtpcharting(Long idCharting,
			Tblhtppermohonan tblhtppermohonan, String noRujukanSeksyen) {
		this.idCharting = idCharting;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noRujukanSeksyen = noRujukanSeksyen;
	}

	/** full constructor */
	public AbstractTblhtpcharting(Long idCharting,
			Tblhtppermohonan tblhtppermohonan, String noRujukanSeksyen,
			String flagPelan, String flagCharting, String ulasan,
			Double jumlahBayaranProses, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idCharting = idCharting;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noRujukanSeksyen = noRujukanSeksyen;
		this.flagPelan = flagPelan;
		this.flagCharting = flagCharting;
		this.ulasan = ulasan;
		this.jumlahBayaranProses = jumlahBayaranProses;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdCharting() {
		return this.idCharting;
	}

	public void setIdCharting(Long idCharting) {
		this.idCharting = idCharting;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getNoRujukanSeksyen() {
		return this.noRujukanSeksyen;
	}

	public void setNoRujukanSeksyen(String noRujukanSeksyen) {
		this.noRujukanSeksyen = noRujukanSeksyen;
	}

	public String getFlagPelan() {
		return this.flagPelan;
	}

	public void setFlagPelan(String flagPelan) {
		this.flagPelan = flagPelan;
	}

	public String getFlagCharting() {
		return this.flagCharting;
	}

	public void setFlagCharting(String flagCharting) {
		this.flagCharting = flagCharting;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Double getJumlahBayaranProses() {
		return this.jumlahBayaranProses;
	}

	public void setJumlahBayaranProses(Double jumlahBayaranProses) {
		this.jumlahBayaranProses = jumlahBayaranProses;
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