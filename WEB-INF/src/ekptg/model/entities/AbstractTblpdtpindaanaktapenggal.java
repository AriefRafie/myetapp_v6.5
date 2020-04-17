package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktapenggal entity provides the base persistence
 * definition of the Tblpdtpindaanaktapenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktapenggal implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktapenggal;
	private Tblpdtaktapenggal tblpdtaktapenggal;
	private Tblpdtaktapindapenggal tblpdtaktapindapenggal;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktapenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktapenggal(Long idPindaanaktapenggal) {
		this.idPindaanaktapenggal = idPindaanaktapenggal;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktapenggal(Long idPindaanaktapenggal,
			Tblpdtaktapenggal tblpdtaktapenggal,
			Tblpdtaktapindapenggal tblpdtaktapindapenggal,
			Set tblpdtpindaanaktas) {
		this.idPindaanaktapenggal = idPindaanaktapenggal;
		this.tblpdtaktapenggal = tblpdtaktapenggal;
		this.tblpdtaktapindapenggal = tblpdtaktapindapenggal;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktapenggal() {
		return this.idPindaanaktapenggal;
	}

	public void setIdPindaanaktapenggal(Long idPindaanaktapenggal) {
		this.idPindaanaktapenggal = idPindaanaktapenggal;
	}

	public Tblpdtaktapenggal getTblpdtaktapenggal() {
		return this.tblpdtaktapenggal;
	}

	public void setTblpdtaktapenggal(Tblpdtaktapenggal tblpdtaktapenggal) {
		this.tblpdtaktapenggal = tblpdtaktapenggal;
	}

	public Tblpdtaktapindapenggal getTblpdtaktapindapenggal() {
		return this.tblpdtaktapindapenggal;
	}

	public void setTblpdtaktapindapenggal(
			Tblpdtaktapindapenggal tblpdtaktapindapenggal) {
		this.tblpdtaktapindapenggal = tblpdtaktapindapenggal;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}