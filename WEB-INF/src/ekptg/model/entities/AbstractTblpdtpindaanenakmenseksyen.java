package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmenseksyen entity provides the base persistence
 * definition of the Tblpdtpindaanenakmenseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmenseksyen implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmenseksyen;
	private Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen;
	private Tblpdtenakmenseksyen tblpdtenakmenseksyen;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmenseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmenseksyen(Long idPindaanenakmenseksyen) {
		this.idPindaanenakmenseksyen = idPindaanenakmenseksyen;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmenseksyen(Long idPindaanenakmenseksyen,
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen,
			Tblpdtenakmenseksyen tblpdtenakmenseksyen, Set tblpdtpindaanenakmens) {
		this.idPindaanenakmenseksyen = idPindaanenakmenseksyen;
		this.tblpdtenakmenpindaseksyen = tblpdtenakmenpindaseksyen;
		this.tblpdtenakmenseksyen = tblpdtenakmenseksyen;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmenseksyen() {
		return this.idPindaanenakmenseksyen;
	}

	public void setIdPindaanenakmenseksyen(Long idPindaanenakmenseksyen) {
		this.idPindaanenakmenseksyen = idPindaanenakmenseksyen;
	}

	public Tblpdtenakmenpindaseksyen getTblpdtenakmenpindaseksyen() {
		return this.tblpdtenakmenpindaseksyen;
	}

	public void setTblpdtenakmenpindaseksyen(
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen) {
		this.tblpdtenakmenpindaseksyen = tblpdtenakmenpindaseksyen;
	}

	public Tblpdtenakmenseksyen getTblpdtenakmenseksyen() {
		return this.tblpdtenakmenseksyen;
	}

	public void setTblpdtenakmenseksyen(
			Tblpdtenakmenseksyen tblpdtenakmenseksyen) {
		this.tblpdtenakmenseksyen = tblpdtenakmenseksyen;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}