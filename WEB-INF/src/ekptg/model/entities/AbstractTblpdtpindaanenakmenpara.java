package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmenpara entity provides the base persistence
 * definition of the Tblpdtpindaanenakmenpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmenpara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmenpara;
	private Tblpdtenakmenperenggan tblpdtenakmenperenggan;
	private Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmenpara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmenpara(Long idPindaanenakmenpara) {
		this.idPindaanenakmenpara = idPindaanenakmenpara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmenpara(Long idPindaanenakmenpara,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan,
			Set tblpdtpindaanenakmens) {
		this.idPindaanenakmenpara = idPindaanenakmenpara;
		this.tblpdtenakmenperenggan = tblpdtenakmenperenggan;
		this.tblpdtenakmenpindaperenggan = tblpdtenakmenpindaperenggan;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmenpara() {
		return this.idPindaanenakmenpara;
	}

	public void setIdPindaanenakmenpara(Long idPindaanenakmenpara) {
		this.idPindaanenakmenpara = idPindaanenakmenpara;
	}

	public Tblpdtenakmenperenggan getTblpdtenakmenperenggan() {
		return this.tblpdtenakmenperenggan;
	}

	public void setTblpdtenakmenperenggan(
			Tblpdtenakmenperenggan tblpdtenakmenperenggan) {
		this.tblpdtenakmenperenggan = tblpdtenakmenperenggan;
	}

	public Tblpdtenakmenpindaperenggan getTblpdtenakmenpindaperenggan() {
		return this.tblpdtenakmenpindaperenggan;
	}

	public void setTblpdtenakmenpindaperenggan(
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan) {
		this.tblpdtenakmenpindaperenggan = tblpdtenakmenpindaperenggan;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}