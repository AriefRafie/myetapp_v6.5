package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindasubperenggan entity provides the base persistence
 * definition of the Tblpdtenakmenpindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindasubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindasubperenggan;
	private Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmensubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindasubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindasubperenggan(
			Long idEnakmenpindasubperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan) {
		this.idEnakmenpindasubperenggan = idEnakmenpindasubperenggan;
		this.tblpdtenakmenpindaperenggan = tblpdtenakmenpindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindasubperenggan(
			Long idEnakmenpindasubperenggan,
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmensubparas) {
		this.idEnakmenpindasubperenggan = idEnakmenpindasubperenggan;
		this.tblpdtenakmenpindaperenggan = tblpdtenakmenpindaperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmensubparas = tblpdtpindaanenakmensubparas;
	}

	// Property accessors

	public Long getIdEnakmenpindasubperenggan() {
		return this.idEnakmenpindasubperenggan;
	}

	public void setIdEnakmenpindasubperenggan(Long idEnakmenpindasubperenggan) {
		this.idEnakmenpindasubperenggan = idEnakmenpindasubperenggan;
	}

	public Tblpdtenakmenpindaperenggan getTblpdtenakmenpindaperenggan() {
		return this.tblpdtenakmenpindaperenggan;
	}

	public void setTblpdtenakmenpindaperenggan(
			Tblpdtenakmenpindaperenggan tblpdtenakmenpindaperenggan) {
		this.tblpdtenakmenpindaperenggan = tblpdtenakmenpindaperenggan;
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