package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmensubpara entity provides the base persistence
 * definition of the Tblpdtpindaanenakmensubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmensubpara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmensubpara;
	private Tblpdtenakmenpindasubperenggan tblpdtenakmenpindasubperenggan;
	private Tblpdtenakmensubperenggan tblpdtenakmensubperenggan;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmensubpara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmensubpara(Long idPindaanenakmensubpara) {
		this.idPindaanenakmensubpara = idPindaanenakmensubpara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmensubpara(Long idPindaanenakmensubpara,
			Tblpdtenakmenpindasubperenggan tblpdtenakmenpindasubperenggan,
			Tblpdtenakmensubperenggan tblpdtenakmensubperenggan,
			Set tblpdtpindaanenakmens) {
		this.idPindaanenakmensubpara = idPindaanenakmensubpara;
		this.tblpdtenakmenpindasubperenggan = tblpdtenakmenpindasubperenggan;
		this.tblpdtenakmensubperenggan = tblpdtenakmensubperenggan;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmensubpara() {
		return this.idPindaanenakmensubpara;
	}

	public void setIdPindaanenakmensubpara(Long idPindaanenakmensubpara) {
		this.idPindaanenakmensubpara = idPindaanenakmensubpara;
	}

	public Tblpdtenakmenpindasubperenggan getTblpdtenakmenpindasubperenggan() {
		return this.tblpdtenakmenpindasubperenggan;
	}

	public void setTblpdtenakmenpindasubperenggan(
			Tblpdtenakmenpindasubperenggan tblpdtenakmenpindasubperenggan) {
		this.tblpdtenakmenpindasubperenggan = tblpdtenakmenpindasubperenggan;
	}

	public Tblpdtenakmensubperenggan getTblpdtenakmensubperenggan() {
		return this.tblpdtenakmensubperenggan;
	}

	public void setTblpdtenakmensubperenggan(
			Tblpdtenakmensubperenggan tblpdtenakmensubperenggan) {
		this.tblpdtenakmensubperenggan = tblpdtenakmensubperenggan;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}