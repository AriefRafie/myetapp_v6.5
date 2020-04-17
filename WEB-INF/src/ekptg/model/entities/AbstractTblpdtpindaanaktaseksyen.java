package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanaktaseksyen entity provides the base persistence
 * definition of the Tblpdtpindaanaktaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanaktaseksyen implements
		java.io.Serializable {

	// Fields

	private Long idPindaanaktaseksyen;
	private Tblpdtaktapindaseksyen tblpdtaktapindaseksyen;
	private Tblpdtaktaseksyen tblpdtaktaseksyen;
	private Set tblpdtpindaanaktas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanaktaseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanaktaseksyen(Long idPindaanaktaseksyen) {
		this.idPindaanaktaseksyen = idPindaanaktaseksyen;
	}

	/** full constructor */
	public AbstractTblpdtpindaanaktaseksyen(Long idPindaanaktaseksyen,
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen,
			Tblpdtaktaseksyen tblpdtaktaseksyen, Set tblpdtpindaanaktas) {
		this.idPindaanaktaseksyen = idPindaanaktaseksyen;
		this.tblpdtaktapindaseksyen = tblpdtaktapindaseksyen;
		this.tblpdtaktaseksyen = tblpdtaktaseksyen;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

	// Property accessors

	public Long getIdPindaanaktaseksyen() {
		return this.idPindaanaktaseksyen;
	}

	public void setIdPindaanaktaseksyen(Long idPindaanaktaseksyen) {
		this.idPindaanaktaseksyen = idPindaanaktaseksyen;
	}

	public Tblpdtaktapindaseksyen getTblpdtaktapindaseksyen() {
		return this.tblpdtaktapindaseksyen;
	}

	public void setTblpdtaktapindaseksyen(
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen) {
		this.tblpdtaktapindaseksyen = tblpdtaktapindaseksyen;
	}

	public Tblpdtaktaseksyen getTblpdtaktaseksyen() {
		return this.tblpdtaktaseksyen;
	}

	public void setTblpdtaktaseksyen(Tblpdtaktaseksyen tblpdtaktaseksyen) {
		this.tblpdtaktaseksyen = tblpdtaktaseksyen;
	}

	public Set getTblpdtpindaanaktas() {
		return this.tblpdtpindaanaktas;
	}

	public void setTblpdtpindaanaktas(Set tblpdtpindaanaktas) {
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
	}

}