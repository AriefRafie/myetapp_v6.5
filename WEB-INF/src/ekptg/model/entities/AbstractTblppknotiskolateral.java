package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppknotiskolateral entity provides the base persistence definition
 * of the Tblppknotiskolateral entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppknotiskolateral implements
		java.io.Serializable {

	// Fields

	private Long idNotiskolateral;
	private Tblppknotisobmst tblppknotisobmst;
	private Tblppkkolateralmst tblppkkolateralmst;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppknotiskolateral() {
	}

	/** minimal constructor */
	public AbstractTblppknotiskolateral(Long idNotiskolateral,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkkolateralmst tblppkkolateralmst) {
		this.idNotiskolateral = idNotiskolateral;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkkolateralmst = tblppkkolateralmst;
	}

	/** full constructor */
	public AbstractTblppknotiskolateral(Long idNotiskolateral,
			Tblppknotisobmst tblppknotisobmst,
			Tblppkkolateralmst tblppkkolateralmst, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idNotiskolateral = idNotiskolateral;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkkolateralmst = tblppkkolateralmst;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNotiskolateral() {
		return this.idNotiskolateral;
	}

	public void setIdNotiskolateral(Long idNotiskolateral) {
		this.idNotiskolateral = idNotiskolateral;
	}

	public Tblppknotisobmst getTblppknotisobmst() {
		return this.tblppknotisobmst;
	}

	public void setTblppknotisobmst(Tblppknotisobmst tblppknotisobmst) {
		this.tblppknotisobmst = tblppknotisobmst;
	}

	public Tblppkkolateralmst getTblppkkolateralmst() {
		return this.tblppkkolateralmst;
	}

	public void setTblppkkolateralmst(Tblppkkolateralmst tblppkkolateralmst) {
		this.tblppkkolateralmst = tblppkkolateralmst;
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