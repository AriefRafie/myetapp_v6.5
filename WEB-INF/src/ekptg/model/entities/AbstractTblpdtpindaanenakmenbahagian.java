package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanenakmenbahagian entity provides the base persistence
 * definition of the Tblpdtpindaanenakmenbahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmenbahagian implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmenbahagian;
	private Tblpdtenakmenbahagian tblpdtenakmenbahagian;
	private Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian;
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmenbahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmenbahagian(Long idPindaanenakmenbahagian) {
		this.idPindaanenakmenbahagian = idPindaanenakmenbahagian;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmenbahagian(Long idPindaanenakmenbahagian,
			Tblpdtenakmenbahagian tblpdtenakmenbahagian,
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian,
			Set tblpdtpindaanenakmens) {
		this.idPindaanenakmenbahagian = idPindaanenakmenbahagian;
		this.tblpdtenakmenbahagian = tblpdtenakmenbahagian;
		this.tblpdtenakmenpindabahagian = tblpdtenakmenpindabahagian;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanenakmenbahagian() {
		return this.idPindaanenakmenbahagian;
	}

	public void setIdPindaanenakmenbahagian(Long idPindaanenakmenbahagian) {
		this.idPindaanenakmenbahagian = idPindaanenakmenbahagian;
	}

	public Tblpdtenakmenbahagian getTblpdtenakmenbahagian() {
		return this.tblpdtenakmenbahagian;
	}

	public void setTblpdtenakmenbahagian(
			Tblpdtenakmenbahagian tblpdtenakmenbahagian) {
		this.tblpdtenakmenbahagian = tblpdtenakmenbahagian;
	}

	public Tblpdtenakmenpindabahagian getTblpdtenakmenpindabahagian() {
		return this.tblpdtenakmenpindabahagian;
	}

	public void setTblpdtenakmenpindabahagian(
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian) {
		this.tblpdtenakmenpindabahagian = tblpdtenakmenpindabahagian;
	}

	public Set getTblpdtpindaanenakmens() {
		return this.tblpdtpindaanenakmens;
	}

	public void setTblpdtpindaanenakmens(Set tblpdtpindaanenakmens) {
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

}