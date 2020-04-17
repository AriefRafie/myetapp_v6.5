package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktasubpara entity provides the base persistence
 * definition of the Tblpdtpindaanaktasubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktasubpara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktasubpara;
	private Tblpdtaktapindasubperenggan tblpdtaktapindasubperenggan;
	private Tblpdtaktasubperenggan tblpdtaktasubperenggan;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktasubpara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktasubpara(Long idPindaanaktasubpara) {
		this.idPindaanaktasubpara = idPindaanaktasubpara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktasubpara(Long idPindaanaktasubpara,
			Tblpdtaktapindasubperenggan tblpdtaktapindasubperenggan,
			Tblpdtaktasubperenggan tblpdtaktasubperenggan,
			Set tblpdtpindaanaktas) {
		this.idPindaanaktasubpara = idPindaanaktasubpara;
		this.tblpdtaktapindasubperenggan = tblpdtaktapindasubperenggan;
		this.tblpdtaktasubperenggan = tblpdtaktasubperenggan;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktasubpara() {
		return this.idPindaanaktasubpara;
	}

	public void setIdPindaanaktasubpara(Long idPindaanaktasubpara) {
		this.idPindaanaktasubpara = idPindaanaktasubpara;
	}

	public Tblpdtaktapindasubperenggan getTblpdtaktapindasubperenggan() {
		return this.tblpdtaktapindasubperenggan;
	}

	public void setTblpdtaktapindasubperenggan(
			Tblpdtaktapindasubperenggan tblpdtaktapindasubperenggan) {
		this.tblpdtaktapindasubperenggan = tblpdtaktapindasubperenggan;
	}

	public Tblpdtaktasubperenggan getTblpdtaktasubperenggan() {
		return this.tblpdtaktasubperenggan;
	}

	public void setTblpdtaktasubperenggan(
			Tblpdtaktasubperenggan tblpdtaktasubperenggan) {
		this.tblpdtaktasubperenggan = tblpdtaktasubperenggan;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}