package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpulasankptg entity provides the base persistence definition of
 * the Tblhtpulasankptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpulasankptg implements java.io.Serializable {

	// Fields

	private Long idUlasankptg;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhHantar;
	private String status;
	private String ulasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpulasankptg() {
	}

	/** minimal constructor */
	public AbstractTblhtpulasankptg(Long idUlasankptg,
			Tblhtppermohonan tblhtppermohonan) {
		this.idUlasankptg = idUlasankptg;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpulasankptg(Long idUlasankptg,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantar,
			String status, String ulasan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idUlasankptg = idUlasankptg;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhHantar = tarikhHantar;
		this.status = status;
		this.ulasan = ulasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUlasankptg() {
		return this.idUlasankptg;
	}

	public void setIdUlasankptg(Long idUlasankptg) {
		this.idUlasankptg = idUlasankptg;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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