package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpulasanteknikalpenyewaan entity provides the base persistence
 * definition of the Tblphpulasanteknikalpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanteknikalpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idUlasanteknikalpenyewaan;
	private Tblphppermohonanpenyewaan tblphppermohonanpenyewaan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idUlasanteknikal;

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanteknikalpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanteknikalpenyewaan(Long idUlasanteknikalpenyewaan) {
		this.idUlasanteknikalpenyewaan = idUlasanteknikalpenyewaan;
	}

	/** full constructor */
	public AbstractTblphpulasanteknikalpenyewaan(
			Long idUlasanteknikalpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUlasanteknikal) {
		this.idUlasanteknikalpenyewaan = idUlasanteknikalpenyewaan;
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idUlasanteknikal = idUlasanteknikal;
	}

	// Property accessors

	public Long getIdUlasanteknikalpenyewaan() {
		return this.idUlasanteknikalpenyewaan;
	}

	public void setIdUlasanteknikalpenyewaan(Long idUlasanteknikalpenyewaan) {
		this.idUlasanteknikalpenyewaan = idUlasanteknikalpenyewaan;
	}

	public Tblphppermohonanpenyewaan getTblphppermohonanpenyewaan() {
		return this.tblphppermohonanpenyewaan;
	}

	public void setTblphppermohonanpenyewaan(
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan) {
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
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

	public Long getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
	}

}