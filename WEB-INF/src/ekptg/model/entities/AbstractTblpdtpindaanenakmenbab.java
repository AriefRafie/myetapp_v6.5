package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmenbab entity provides the base persistence
 * definition of the Tblpdtpindaanenakmenbab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmenbab implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmenbab;
	private Tblpdtenakmenpindabab tblpdtenakmenpindabab;
	private Tblpdtenakmenbab tblpdtenakmenbab;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmenbab() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmenbab(Long idPindaanenakmenbab) {
		this.idPindaanenakmenbab = idPindaanenakmenbab;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmenbab(Long idPindaanenakmenbab,
			Tblpdtenakmenpindabab tblpdtenakmenpindabab,
			Tblpdtenakmenbab tblpdtenakmenbab, Set tblpdtpindaanenakmens) {
		this.idPindaanenakmenbab = idPindaanenakmenbab;
		this.tblpdtenakmenpindabab = tblpdtenakmenpindabab;
		this.tblpdtenakmenbab = tblpdtenakmenbab;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmenbab() {
		return this.idPindaanenakmenbab;
	}

	public void setIdPindaanenakmenbab(Long idPindaanenakmenbab) {
		this.idPindaanenakmenbab = idPindaanenakmenbab;
	}

	public Tblpdtenakmenpindabab getTblpdtenakmenpindabab() {
		return this.tblpdtenakmenpindabab;
	}

	public void setTblpdtenakmenpindabab(
			Tblpdtenakmenpindabab tblpdtenakmenpindabab) {
		this.tblpdtenakmenpindabab = tblpdtenakmenpindabab;
	}

	public Tblpdtenakmenbab getTblpdtenakmenbab() {
		return this.tblpdtenakmenbab;
	}

	public void setTblpdtenakmenbab(Tblpdtenakmenbab tblpdtenakmenbab) {
		this.tblpdtenakmenbab = tblpdtenakmenbab;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}