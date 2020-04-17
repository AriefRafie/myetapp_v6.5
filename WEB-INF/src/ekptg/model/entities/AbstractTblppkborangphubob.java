package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkborangphubob entity provides the base persistence definition of
 * the Tblppkborangphubob entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangphubob implements
		java.io.Serializable {

	// Fields

	private Long idBorangphubob;
	private Tblppkborangpob tblppkborangpob;
	private Long idParentborangpob;
	private Long idSaudara;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkborangphubob() {
	}

	/** minimal constructor */
	public AbstractTblppkborangphubob(Long idBorangphubob,
			Tblppkborangpob tblppkborangpob, Long idParentborangpob,
			Long idSaudara) {
		this.idBorangphubob = idBorangphubob;
		this.tblppkborangpob = tblppkborangpob;
		this.idParentborangpob = idParentborangpob;
		this.idSaudara = idSaudara;
	}

	/** full constructor */
	public AbstractTblppkborangphubob(Long idBorangphubob,
			Tblppkborangpob tblppkborangpob, Long idParentborangpob,
			Long idSaudara, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idBorangphubob = idBorangphubob;
		this.tblppkborangpob = tblppkborangpob;
		this.idParentborangpob = idParentborangpob;
		this.idSaudara = idSaudara;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorangphubob() {
		return this.idBorangphubob;
	}

	public void setIdBorangphubob(Long idBorangphubob) {
		this.idBorangphubob = idBorangphubob;
	}

	public Tblppkborangpob getTblppkborangpob() {
		return this.tblppkborangpob;
	}

	public void setTblppkborangpob(Tblppkborangpob tblppkborangpob) {
		this.tblppkborangpob = tblppkborangpob;
	}

	public Long getIdParentborangpob() {
		return this.idParentborangpob;
	}

	public void setIdParentborangpob(Long idParentborangpob) {
		this.idParentborangpob = idParentborangpob;
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