package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpperenggankkpenyewaan entity provides the base persistence
 * definition of the Tblphpperenggankkpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpperenggankkpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idPerenggankkpenyewaan;
	private Tblphpkertaskerjapenyewaan tblphpkertaskerjapenyewaan;
	private String perenggan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpperenggankkpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphpperenggankkpenyewaan(Long idPerenggankkpenyewaan) {
		this.idPerenggankkpenyewaan = idPerenggankkpenyewaan;
	}

	/** full constructor */
	public AbstractTblphpperenggankkpenyewaan(Long idPerenggankkpenyewaan,
			Tblphpkertaskerjapenyewaan tblphpkertaskerjapenyewaan,
			String perenggan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPerenggankkpenyewaan = idPerenggankkpenyewaan;
		this.tblphpkertaskerjapenyewaan = tblphpkertaskerjapenyewaan;
		this.perenggan = perenggan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerenggankkpenyewaan() {
		return this.idPerenggankkpenyewaan;
	}

	public void setIdPerenggankkpenyewaan(Long idPerenggankkpenyewaan) {
		this.idPerenggankkpenyewaan = idPerenggankkpenyewaan;
	}

	public Tblphpkertaskerjapenyewaan getTblphpkertaskerjapenyewaan() {
		return this.tblphpkertaskerjapenyewaan;
	}

	public void setTblphpkertaskerjapenyewaan(
			Tblphpkertaskerjapenyewaan tblphpkertaskerjapenyewaan) {
		this.tblphpkertaskerjapenyewaan = tblphpkertaskerjapenyewaan;
	}

	public String getPerenggan() {
		return this.perenggan;
	}

	public void setPerenggan(String perenggan) {
		this.perenggan = perenggan;
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