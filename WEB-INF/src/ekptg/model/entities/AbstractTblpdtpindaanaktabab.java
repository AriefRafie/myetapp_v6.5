package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktabab entity provides the base persistence definition
 * of the Tblpdtpindaanaktabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktabab implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktabab;
	private Tblpdtaktabab tblpdtaktabab;
	private Tblpdtaktapindabab tblpdtaktapindabab;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktabab() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktabab(Long idPindaanaktabab) {
		this.idPindaanaktabab = idPindaanaktabab;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktabab(Long idPindaanaktabab,
			Tblpdtaktabab tblpdtaktabab, Tblpdtaktapindabab tblpdtaktapindabab,
			Set tblpdtpindaanaktas) {
		this.idPindaanaktabab = idPindaanaktabab;
		this.tblpdtaktabab = tblpdtaktabab;
		this.tblpdtaktapindabab = tblpdtaktapindabab;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktabab() {
		return this.idPindaanaktabab;
	}

	public void setIdPindaanaktabab(Long idPindaanaktabab) {
		this.idPindaanaktabab = idPindaanaktabab;
	}

	public Tblpdtaktabab getTblpdtaktabab() {
		return this.tblpdtaktabab;
	}

	public void setTblpdtaktabab(Tblpdtaktabab tblpdtaktabab) {
		this.tblpdtaktabab = tblpdtaktabab;
	}

	public Tblpdtaktapindabab getTblpdtaktapindabab() {
		return this.tblpdtaktapindabab;
	}

	public void setTblpdtaktapindabab(Tblpdtaktapindabab tblpdtaktapindabab) {
		this.tblpdtaktapindabab = tblpdtaktapindabab;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}