package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppenyelarasanbayaran entity provides the base persistence
 * definition of the Tblphppenyelarasanbayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppenyelarasanbayaran implements
		java.io.Serializable {

	// Fields

	private Long idPenyelarasanbayaran;
	private Tblphpbayaranperludibayar tblphpbayaranperludibayar;
	private Tblphpbayaran tblphpbayaran;
	private Double bakiBawakehadapan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppenyelarasanbayaran() {
	}

	/** minimal constructor */
	public AbstractTblphppenyelarasanbayaran(Long idPenyelarasanbayaran) {
		this.idPenyelarasanbayaran = idPenyelarasanbayaran;
	}

	/** full constructor */
	public AbstractTblphppenyelarasanbayaran(Long idPenyelarasanbayaran,
			Tblphpbayaranperludibayar tblphpbayaranperludibayar,
			Tblphpbayaran tblphpbayaran, Double bakiBawakehadapan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPenyelarasanbayaran = idPenyelarasanbayaran;
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
		this.tblphpbayaran = tblphpbayaran;
		this.bakiBawakehadapan = bakiBawakehadapan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPenyelarasanbayaran() {
		return this.idPenyelarasanbayaran;
	}

	public void setIdPenyelarasanbayaran(Long idPenyelarasanbayaran) {
		this.idPenyelarasanbayaran = idPenyelarasanbayaran;
	}

	public Tblphpbayaranperludibayar getTblphpbayaranperludibayar() {
		return this.tblphpbayaranperludibayar;
	}

	public void setTblphpbayaranperludibayar(
			Tblphpbayaranperludibayar tblphpbayaranperludibayar) {
		this.tblphpbayaranperludibayar = tblphpbayaranperludibayar;
	}

	public Tblphpbayaran getTblphpbayaran() {
		return this.tblphpbayaran;
	}

	public void setTblphpbayaran(Tblphpbayaran tblphpbayaran) {
		this.tblphpbayaran = tblphpbayaran;
	}

	public Double getBakiBawakehadapan() {
		return this.bakiBawakehadapan;
	}

	public void setBakiBawakehadapan(Double bakiBawakehadapan) {
		this.bakiBawakehadapan = bakiBawakehadapan;
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