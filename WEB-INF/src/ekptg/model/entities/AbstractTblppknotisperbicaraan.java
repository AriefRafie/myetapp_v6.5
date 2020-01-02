package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppknotisperbicaraan entity provides the base persistence
 * definition of the Tblppknotisperbicaraan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppknotisperbicaraan implements
		java.io.Serializable {

	// Fields

	private Long idNotisperbicaraan;
	private Tblppknotisobmst tblppknotisobmst;
	private Tblppkperbicaraan tblppkperbicaraan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppknotisperbicaraan() {
	}

	/** minimal constructor */
	public AbstractTblppknotisperbicaraan(Long idNotisperbicaraan,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkperbicaraan tblppkperbicaraan) {
		this.idNotisperbicaraan = idNotisperbicaraan;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	/** full constructor */
	public AbstractTblppknotisperbicaraan(Long idNotisperbicaraan,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkperbicaraan tblppkperbicaraan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idNotisperbicaraan = idNotisperbicaraan;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNotisperbicaraan() {
		return this.idNotisperbicaraan;
	}

	public void setIdNotisperbicaraan(Long idNotisperbicaraan) {
		this.idNotisperbicaraan = idNotisperbicaraan;
	}

	public Tblppknotisobmst getTblppknotisobmst() {
		return this.tblppknotisobmst;
	}

	public void setTblppknotisobmst(Tblppknotisobmst tblppknotisobmst) {
		this.tblppknotisobmst = tblppknotisobmst;
	}

	public Tblppkperbicaraan getTblppkperbicaraan() {
		return this.tblppkperbicaraan;
	}

	public void setTblppkperbicaraan(Tblppkperbicaraan tblppkperbicaraan) {
		this.tblppkperbicaraan = tblppkperbicaraan;
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