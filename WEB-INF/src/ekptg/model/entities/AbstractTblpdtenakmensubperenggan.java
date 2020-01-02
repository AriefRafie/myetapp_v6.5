package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmensubperenggan entity provides the base persistence
 * definition of the Tblpdtenakmensubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmensubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idEnakmensubperenggan;
	private Tblpdtenakmenperenggan tblpdtenakmenperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmensubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmensubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmensubperenggan(Long idEnakmensubperenggan,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan) {
		this.idEnakmensubperenggan = idEnakmensubperenggan;
		this.tblpdtenakmenperenggan = tblpdtenakmenperenggan;
	}

	/** full constructor */
	public AbstractTblpdtenakmensubperenggan(Long idEnakmensubperenggan,
			Tblpdtenakmenperenggan tblpdtenakmenperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmensubparas) {
		this.idEnakmensubperenggan = idEnakmensubperenggan;
		this.tblpdtenakmenperenggan = tblpdtenakmenperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmensubparas = tblpdtpindaanenakmensubparas;
	}

	// Property accessors

	public Long getIdEnakmensubperenggan() {
		return this.idEnakmensubperenggan;
	}

	public void setIdEnakmensubperenggan(Long idEnakmensubperenggan) {
		this.idEnakmensubperenggan = idEnakmensubperenggan;
	}

	public Tblpdtenakmenperenggan getTblpdtenakmenperenggan() {
		return this.tblpdtenakmenperenggan;
	}

	public void setTblpdtenakmenperenggan(
			Tblpdtenakmenperenggan tblpdtenakmenperenggan) {
		this.tblpdtenakmenperenggan = tblpdtenakmenperenggan;
	}

	public String getNotaBirai() {
		return this.notaBirai;
	}

	public void setNotaBirai(String notaBirai) {
		this.notaBirai = notaBirai;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblpdtpindaanenakmensubparas() {
		return this.tblpdtpindaanenakmensubparas;
	}

	public void setTblpdtpindaanenakmensubparas(Set tblpdtpindaanenakmensubparas) {
		this.tblpdtpindaanenakmensubparas = tblpdtpindaanenakmensubparas;
	}

}