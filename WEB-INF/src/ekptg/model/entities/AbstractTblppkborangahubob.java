package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkborangahubob entity provides the base persistence definition of
 * the Tblppkborangahubob entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangahubob implements
		java.io.Serializable {

	// Fields

	private Long idBorangahubob;
	private Tblppkborangaob tblppkborangaob;
	private Long idParentborangaob;
	private Long idSaudara;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkborangahubob() {
	}

	/** minimal constructor */
	public AbstractTblppkborangahubob(Long idBorangahubob,
			Tblppkborangaob tblppkborangaob, Long idParentborangaob,
			Long idSaudara) {
		this.idBorangahubob = idBorangahubob;
		this.tblppkborangaob = tblppkborangaob;
		this.idParentborangaob = idParentborangaob;
		this.idSaudara = idSaudara;
	}

	/** full constructor */
	public AbstractTblppkborangahubob(Long idBorangahubob,
			Tblppkborangaob tblppkborangaob, Long idParentborangaob,
			Long idSaudara, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idBorangahubob = idBorangahubob;
		this.tblppkborangaob = tblppkborangaob;
		this.idParentborangaob = idParentborangaob;
		this.idSaudara = idSaudara;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorangahubob() {
		return this.idBorangahubob;
	}

	public void setIdBorangahubob(Long idBorangahubob) {
		this.idBorangahubob = idBorangahubob;
	}

	public Tblppkborangaob getTblppkborangaob() {
		return this.tblppkborangaob;
	}

	public void setTblppkborangaob(Tblppkborangaob tblppkborangaob) {
		this.tblppkborangaob = tblppkborangaob;
	}

	public Long getIdParentborangaob() {
		return this.idParentborangaob;
	}

	public void setIdParentborangaob(Long idParentborangaob) {
		this.idParentborangaob = idParentborangaob;
	}

	public Long getIdSaudara() {
		return this.idSaudara;
	}

	public void setIdSaudara(Long idSaudara) {
		this.idSaudara = idSaudara;
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