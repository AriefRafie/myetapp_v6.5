package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphppemeganglesenperjanjian entity provides the base persistence
 * definition of the Tblphppemeganglesenperjanjian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppemeganglesenperjanjian implements
		java.io.Serializable {

	// Fields

	private Long idPemeganglesenperjanjian;
	private Tblphppemegangpenyewaan tblphppemegangpenyewaan;
	private Tblphppemeganglesenapb tblphppemeganglesenapb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppemegangs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphppemeganglesenperjanjian() {
	}

	/** minimal constructor */
	public AbstractTblphppemeganglesenperjanjian(Long idPemeganglesenperjanjian) {
		this.idPemeganglesenperjanjian = idPemeganglesenperjanjian;
	}

	/** full constructor */
	public AbstractTblphppemeganglesenperjanjian(
			Long idPemeganglesenperjanjian,
			Tblphppemegangpenyewaan tblphppemegangpenyewaan,
			Tblphppemeganglesenapb tblphppemeganglesenapb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppemegangs) {
		this.idPemeganglesenperjanjian = idPemeganglesenperjanjian;
		this.tblphppemegangpenyewaan = tblphppemegangpenyewaan;
		this.tblphppemeganglesenapb = tblphppemeganglesenapb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppemegangs = tblphppemegangs;
	}

	// Property accessors

	public Long getIdPemeganglesenperjanjian() {
		return this.idPemeganglesenperjanjian;
	}

	public void setIdPemeganglesenperjanjian(Long idPemeganglesenperjanjian) {
		this.idPemeganglesenperjanjian = idPemeganglesenperjanjian;
	}

	public Tblphppemegangpenyewaan getTblphppemegangpenyewaan() {
		return this.tblphppemegangpenyewaan;
	}

	public void setTblphppemegangpenyewaan(
			Tblphppemegangpenyewaan tblphppemegangpenyewaan) {
		this.tblphppemegangpenyewaan = tblphppemegangpenyewaan;
	}

	public Tblphppemeganglesenapb getTblphppemeganglesenapb() {
		return this.tblphppemeganglesenapb;
	}

	public void setTblphppemeganglesenapb(
			Tblphppemeganglesenapb tblphppemeganglesenapb) {
		this.tblphppemeganglesenapb = tblphppemeganglesenapb;
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

	public Set getTblphppemegangs() {
		return this.tblphppemegangs;
	}

	public void setTblphppemegangs(Set tblphppemegangs) {
		this.tblphppemegangs = tblphppemegangs;
	}

}