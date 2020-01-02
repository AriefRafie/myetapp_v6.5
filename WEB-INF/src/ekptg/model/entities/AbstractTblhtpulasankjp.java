package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpulasankjp entity provides the base persistence definition of
 * the Tblhtpulasankjp entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpulasankjp implements java.io.Serializable {

	// Fields

	private Long idUlasankjp;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private Date tarikhSuratKeputusan;
	private String ulasan;
	private String statusKeputusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpulasankjp() {
	}

	/** minimal constructor */
	public AbstractTblhtpulasankjp(Long idUlasankjp,
			Tblhtppermohonan tblhtppermohonan) {
		this.idUlasankjp = idUlasankjp;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpulasankjp(Long idUlasankjp,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantar,
			Date tarikhTerima, Date tarikhSuratKeputusan, String ulasan,
			String statusKeputusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idUlasankjp = idUlasankjp;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.tarikhSuratKeputusan = tarikhSuratKeputusan;
		this.ulasan = ulasan;
		this.statusKeputusan = statusKeputusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUlasankjp() {
		return this.idUlasankjp;
	}

	public void setIdUlasankjp(Long idUlasankjp) {
		this.idUlasankjp = idUlasankjp;
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