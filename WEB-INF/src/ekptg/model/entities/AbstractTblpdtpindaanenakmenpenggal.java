package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmenpenggal entity provides the base persistence
 * definition of the Tblpdtpindaanenakmenpenggal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmenpenggal implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmenpenggal;
	private Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal;
	private Tblpdtenakmenpenggal tblpdtenakmenpenggal;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmenpenggal() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmenpenggal(Long idPindaanenakmenpenggal) {
		this.idPindaanenakmenpenggal = idPindaanenakmenpenggal;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmenpenggal(Long idPindaanenakmenpenggal,
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal,
			Tblpdtenakmenpenggal tblpdtenakmenpenggal, Set tblpdtpindaanenakmens) {
		this.idPindaanenakmenpenggal = idPindaanenakmenpenggal;
		this.tblpdtenakmenpindapenggal = tblpdtenakmenpindapenggal;
		this.tblpdtenakmenpenggal = tblpdtenakmenpenggal;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmenpenggal() {
		return this.idPindaanenakmenpenggal;
	}

	public void setIdPindaanenakmenpenggal(Long idPindaanenakmenpenggal) {
		this.idPindaanenakmenpenggal = idPindaanenakmenpenggal;
	}

	public Tblpdtenakmenpindapenggal getTblpdtenakmenpindapenggal() {
		return this.tblpdtenakmenpindapenggal;
	}

	public void setTblpdtenakmenpindapenggal(
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal) {
		this.tblpdtenakmenpindapenggal = tblpdtenakmenpindapenggal;
	}

	public Tblpdtenakmenpenggal getTblpdtenakmenpenggal() {
		return this.tblpdtenakmenpenggal;
	}

	public void setTblpdtenakmenpenggal(
			Tblpdtenakmenpenggal tblpdtenakmenpenggal) {
		this.tblpdtenakmenpenggal = tblpdtenakmenpenggal;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}