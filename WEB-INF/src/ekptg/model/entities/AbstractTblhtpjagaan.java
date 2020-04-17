package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpjagaan entity provides the base persistence definition of the
 * Tblhtpjagaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpjagaan implements java.io.Serializable {

	// Fields

	private Long idJagaan;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpjagaan() {
	}

	/** minimal constructor */
	public AbstractTblhtpjagaan(Long idJagaan, Long idNegeri, Long idDaerah) {
		this.idJagaan = idJagaan;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public AbstractTblhtpjagaan(Long idJagaan, Long idNegeri, Long idDaerah,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idJagaan = idJagaan;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJagaan() {
		return this.idJagaan;
	}

	public void setIdJagaan(Long idJagaan) {
		this.idJagaan = idJagaan;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
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