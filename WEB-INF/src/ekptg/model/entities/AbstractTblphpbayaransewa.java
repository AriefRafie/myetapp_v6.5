package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpbayaransewa entity provides the base persistence definition of
 * the Tblphpbayaransewa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbayaransewa implements java.io.Serializable {

	// Fields

	private Long idBayaransewa;
	private Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Double kadarSewa;
	private Set tblphpbayaranperludibayars = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpbayaransewa() {
	}

	/** minimal constructor */
	public AbstractTblphpbayaransewa(Long idBayaransewa) {
		this.idBayaransewa = idBayaransewa;
	}

	/** full constructor */
	public AbstractTblphpbayaransewa(Long idBayaransewa,
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double kadarSewa, Set tblphpbayaranperludibayars) {
		this.idBayaransewa = idBayaransewa;
		this.tblphpperjanjianpenyewaan = tblphpperjanjianpenyewaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.kadarSewa = kadarSewa;
		this.tblphpbayaranperludibayars = tblphpbayaranperludibayars;
	}

	// Property accessors

	public Long getIdBayaransewa() {
		return this.idBayaransewa;
	}

	public void setIdBayaransewa(Long idBayaransewa) {
		this.idBayaransewa = idBayaransewa;
	}

	public Tblphpperjanjianpenyewaan getTblphpperjanjianpenyewaan() {
		return this.tblphpperjanjianpenyewaan;
	}

	public void setTblphpperjanjianpenyewaan(
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan) {
		this.tblphpperjanjianpenyewaan = tblphpperjanjianpenyewaan;
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

	public Double getKadarSewa() {
		return this.kadarSewa;
	}

	public void setKadarSewa(Double kadarSewa) {
		this.kadarSewa = kadarSewa;
	}

	public Set getTblphpbayaranperludibayars() {
		return this.tblphpbayaranperludibayars;
	}

	public void setTblphpbayaranperludibayars(Set tblphpbayaranperludibayars) {
		this.tblphpbayaranperludibayars = tblphpbayaranperludibayars;
	}

}