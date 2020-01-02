package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphppemegangpenyewaan entity provides the base persistence
 * definition of the Tblphppemegangpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppemegangpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idPemegangpenyewaan;
	private Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppemeganglesenperjanjians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphppemegangpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphppemegangpenyewaan(Long idPemegangpenyewaan) {
		this.idPemegangpenyewaan = idPemegangpenyewaan;
	}

	/** full constructor */
	public AbstractTblphppemegangpenyewaan(Long idPemegangpenyewaan,
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppemeganglesenperjanjians) {
		this.idPemegangpenyewaan = idPemegangpenyewaan;
		this.tblphpperjanjianpenyewaan = tblphpperjanjianpenyewaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppemeganglesenperjanjians = tblphppemeganglesenperjanjians;
	}

	// Property accessors

	public Long getIdPemegangpenyewaan() {
		return this.idPemegangpenyewaan;
	}

	public void setIdPemegangpenyewaan(Long idPemegangpenyewaan) {
		this.idPemegangpenyewaan = idPemegangpenyewaan;
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

	public Set getTblphppemeganglesenperjanjians() {
		return this.tblphppemeganglesenperjanjians;
	}

	public void setTblphppemeganglesenperjanjians(
			Set tblphppemeganglesenperjanjians) {
		this.tblphppemeganglesenperjanjians = tblphppemeganglesenperjanjians;
	}

}