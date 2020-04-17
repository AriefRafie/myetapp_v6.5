package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanjadualsubpara entity provides the base persistence
 * definition of the Tblpdtpindaanjadualsubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanjadualsubpara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanjadualsubpara;
	private Tblpdtjadualsubperenggan tblpdtjadualsubperenggan;
	private Tblpdtjadualpindasubperenggan tblpdtjadualpindasubperenggan;
	private Set tblpdtpindaanaktas = new HashSet(0);
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanjadualsubpara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanjadualsubpara(Long idPindaanjadualsubpara) {
		this.idPindaanjadualsubpara = idPindaanjadualsubpara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanjadualsubpara(Long idPindaanjadualsubpara,
			Tblpdtjadualsubperenggan tblpdtjadualsubperenggan,
			Tblpdtjadualpindasubperenggan tblpdtjadualpindasubperenggan,
			Set tblpdtpindaanaktas, Set tblpdtpindaanenakmens) {
		this.idPindaanjadualsubpara = idPindaanjadualsubpara;
		this.tblpdtjadualsubperenggan = tblpdtjadualsubperenggan;
		this.tblpdtjadualpindasubperenggan = tblpdtjadualpindasubperenggan;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanjadualsubpara() {
		return this.idPindaanjadualsubpara;
	}

	public void setIdPindaanjadualsubpara(Long idPindaanjadualsubpara) {
		this.idPindaanjadualsubpara = idPindaanjadualsubpara;
	}

	public Tblpdtjadualsubperenggan getTblpdtjadualsubperenggan() {
		return this.tblpdtjadualsubperenggan;
	}

	public void setTblpdtjadualsubperenggan(
			Tblpdtjadualsubperenggan tblpdtjadualsubperenggan) {
		this.tblpdtjadualsubperenggan = tblpdtjadualsubperenggan;
	}

	public Tblpdtjadualpindasubperenggan getTblpdtjadualpindasubperenggan() {
		return this.tblpdtjadualpindasubperenggan;
	}

	public void setTblpdtjadualpindasubperenggan(
			Tblpdtjadualpindasubperenggan tblpdtjadualpindasubperenggan) {
		this.tblpdtjadualpindasubperenggan = tblpdtjadualpindasubperenggan;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}