package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtpindaanjadualpara entity provides the base persistence
 * definition of the Tblpdtpindaanjadualpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanjadualpara implements
		java.io.Serializable {

	// Fields

	private Long idPindaanjadualpara;
	private Tblpdtjadualperenggan tblpdtjadualperenggan;
	private Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan;
	private Set tblpdtpindaanaktas = new HashSet(0);
	private Set tblpdtpindaanenakmens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanjadualpara() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanjadualpara(Long idPindaanjadualpara) {
		this.idPindaanjadualpara = idPindaanjadualpara;
	}

	/** full constructor */
	public AbstractTblpdtpindaanjadualpara(Long idPindaanjadualpara,
			Tblpdtjadualperenggan tblpdtjadualperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan,
			Set tblpdtpindaanaktas, Set tblpdtpindaanenakmens) {
		this.idPindaanjadualpara = idPindaanjadualpara;
		this.tblpdtjadualperenggan = tblpdtjadualperenggan;
		this.tblpdtjadualpindaperenggan = tblpdtjadualpindaperenggan;
		this.tblpdtpindaanaktas = tblpdtpindaanaktas;
		this.tblpdtpindaanenakmens = tblpdtpindaanenakmens;
	}

	// Property accessors

	public Long getIdPindaanjadualpara() {
		return this.idPindaanjadualpara;
	}

	public void setIdPindaanjadualpara(Long idPindaanjadualpara) {
		this.idPindaanjadualpara = idPindaanjadualpara;
	}

	public Tblpdtjadualperenggan getTblpdtjadualperenggan() {
		return this.tblpdtjadualperenggan;
	}

	public void setTblpdtjadualperenggan(
			Tblpdtjadualperenggan tblpdtjadualperenggan) {
		this.tblpdtjadualperenggan = tblpdtjadualperenggan;
	}

	public Tblpdtjadualpindaperenggan getTblpdtjadualpindaperenggan() {
		return this.tblpdtjadualpindaperenggan;
	}

	public void setTblpdtjadualpindaperenggan(
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan) {
		this.tblpdtjadualpindaperenggan = tblpdtjadualpindaperenggan;
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