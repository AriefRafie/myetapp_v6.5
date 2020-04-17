package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktasubperenggan entity provides the base persistence
 * definition of the Tblpdtaktasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktasubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idAktasubperenggan;
	private Tblpdtaktaperenggan tblpdtaktaperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanaktasubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktasubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktasubperenggan(Long idAktasubperenggan,
			Tblpdtaktaperenggan tblpdtaktaperenggan) {
		this.idAktasubperenggan = idAktasubperenggan;
		this.tblpdtaktaperenggan = tblpdtaktaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtaktasubperenggan(Long idAktasubperenggan,
			Tblpdtaktaperenggan tblpdtaktaperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktasubparas) {
		this.idAktasubperenggan = idAktasubperenggan;
		this.tblpdtaktaperenggan = tblpdtaktaperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanaktasubparas = tblpdtpindaanaktasubparas;
	}

	// Property accessors

	public Long getIdAktasubperenggan() {
		return this.idAktasubperenggan;
	}

	public void setIdAktasubperenggan(Long idAktasubperenggan) {
		this.idAktasubperenggan = idAktasubperenggan;
	}

	public Tblpdtaktaperenggan getTblpdtaktaperenggan() {
		return this.tblpdtaktaperenggan;
	}

	public void setTblpdtaktaperenggan(Tblpdtaktaperenggan tblpdtaktaperenggan) {
		this.tblpdtaktaperenggan = tblpdtaktaperenggan;
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

	public Set getTblpdtpindaanaktasubparas() {
		return this.tblpdtpindaanaktasubparas;
	}

	public void setTblpdtpindaanaktasubparas(Set tblpdtpindaanaktasubparas) {
		this.tblpdtpindaanaktasubparas = tblpdtpindaanaktasubparas;
	}

}