package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktabahagian entity provides the base persistence
 * definition of the Tblpdtpindaanaktabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktabahagian implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktabahagian;
	private Tblpdtaktabahagian tblpdtaktabahagian;
	private Tblpdtaktapindabahagian tblpdtaktapindabahagian;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktabahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktabahagian(Long idPindaanaktabahagian) {
		this.idPindaanaktabahagian = idPindaanaktabahagian;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktabahagian(Long idPindaanaktabahagian,
			Tblpdtaktabahagian tblpdtaktabahagian,
			Tblpdtaktapindabahagian tblpdtaktapindabahagian,
			Set tblpdtpindaanaktas) {
		this.idPindaanaktabahagian = idPindaanaktabahagian;
		this.tblpdtaktabahagian = tblpdtaktabahagian;
		this.tblpdtaktapindabahagian = tblpdtaktapindabahagian;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktabahagian() {
		return this.idPindaanaktabahagian;
	}

	public void setIdPindaanaktabahagian(Long idPindaanaktabahagian) {
		this.idPindaanaktabahagian = idPindaanaktabahagian;
	}

	public Tblpdtaktabahagian getTblpdtaktabahagian() {
		return this.tblpdtaktabahagian;
	}

	public void setTblpdtaktabahagian(Tblpdtaktabahagian tblpdtaktabahagian) {
		this.tblpdtaktabahagian = tblpdtaktabahagian;
	}

	public Tblpdtaktapindabahagian getTblpdtaktapindabahagian() {
		return this.tblpdtaktapindabahagian;
	}

	public void setTblpdtaktapindabahagian(
			Tblpdtaktapindabahagian tblpdtaktapindabahagian) {
		this.tblpdtaktapindabahagian = tblpdtaktapindabahagian;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}