package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualpindasubperenggan entity provides the base persistence
 * definition of the Tblpdtjadualpindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualpindasubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idJadualpindasubperenggan;
	private Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanjadualsubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualpindasubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualpindasubperenggan(
			Long idJadualpindasubperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan) {
		this.idJadualpindasubperenggan = idJadualpindasubperenggan;
		this.tblpdtjadualpindaperenggan = tblpdtjadualpindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtjadualpindasubperenggan(
			Long idJadualpindasubperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanjadualsubparas) {
		this.idJadualpindasubperenggan = idJadualpindasubperenggan;
		this.tblpdtjadualpindaperenggan = tblpdtjadualpindaperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanjadualsubparas = tblpdtpindaanjadualsubparas;
	}

	// Property accessors

	public Long getIdJadualpindasubperenggan() {
		return this.idJadualpindasubperenggan;
	}

	public void setIdJadualpindasubperenggan(Long idJadualpindasubperenggan) {
		this.idJadualpindasubperenggan = idJadualpindasubperenggan;
	}

	public Tblpdtjadualpindaperenggan getTblpdtjadualpindaperenggan() {
		return this.tblpdtjadualpindaperenggan;
	}

	public void setTblpdtjadualpindaperenggan(
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan) {
		this.tblpdtjadualpindaperenggan = tblpdtjadualpindaperenggan;
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

	public Set getTblpdtpindaanjadualsubparas() {
		return this.tblpdtpindaanjadualsubparas;
	}

	public void setTblpdtpindaanjadualsubparas(Set tblpdtpindaanjadualsubparas) {
		this.tblpdtpindaanjadualsubparas = tblpdtpindaanjadualsubparas;
	}

}