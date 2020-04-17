package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktapara entity provides the base persistence definition
 * of the Tblpdtpindaanaktapara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktapara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktapara;
	private Tblpdtaktaperenggan tblpdtaktaperenggan;
	private Tblpdtaktapindaperenggan tblpdtaktapindaperenggan;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktapara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktapara(Long idPindaanaktapara) {
		this.idPindaanaktapara = idPindaanaktapara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktapara(Long idPindaanaktapara,
			Tblpdtaktaperenggan tblpdtaktaperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan,
			Set tblpdtpindaanaktas) {
		this.idPindaanaktapara = idPindaanaktapara;
		this.tblpdtaktaperenggan = tblpdtaktaperenggan;
		this.tblpdtaktapindaperenggan = tblpdtaktapindaperenggan;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktapara() {
		return this.idPindaanaktapara;
	}

	public void setIdPindaanaktapara(Long idPindaanaktapara) {
		this.idPindaanaktapara = idPindaanaktapara;
	}

	public Tblpdtaktaperenggan getTblpdtaktaperenggan() {
		return this.tblpdtaktaperenggan;
	}

	public void setTblpdtaktaperenggan(Tblpdtaktaperenggan tblpdtaktaperenggan) {
		this.tblpdtaktaperenggan = tblpdtaktaperenggan;
	}

	public Tblpdtaktapindaperenggan getTblpdtaktapindaperenggan() {
		return this.tblpdtaktapindaperenggan;
	}

	public void setTblpdtaktapindaperenggan(
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan) {
		this.tblpdtaktapindaperenggan = tblpdtaktapindaperenggan;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}