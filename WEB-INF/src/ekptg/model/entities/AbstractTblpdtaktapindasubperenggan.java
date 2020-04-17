package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindasubperenggan entity provides the base persistence
 * definition of the Tblpdtaktapindasubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindasubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idAktapindasubperenggan;
	private Tblpdtaktapindaperenggan tblpdtaktapindaperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanaktasubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindasubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindasubperenggan(Long idAktapindasubperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan) {
		this.idAktapindasubperenggan = idAktapindasubperenggan;
		this.tblpdtaktapindaperenggan = tblpdtaktapindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtaktapindasubperenggan(Long idAktapindasubperenggan,
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan,
			String notaBirai, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktasubparas) {
		this.idAktapindasubperenggan = idAktapindasubperenggan;
		this.tblpdtaktapindaperenggan = tblpdtaktapindaperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanaktasubparas = tblpdtpindaanaktasubparas;
	}

	// Property accessors

	public Long getIdAktapindasubperenggan() {
		return this.idAktapindasubperenggan;
	}

	public void setIdAktapindasubperenggan(Long idAktapindasubperenggan) {
		this.idAktapindasubperenggan = idAktapindasubperenggan;
	}

	public Tblpdtaktapindaperenggan getTblpdtaktapindaperenggan() {
		return this.tblpdtaktapindaperenggan;
	}

	public void setTblpdtaktapindaperenggan(
			Tblpdtaktapindaperenggan tblpdtaktapindaperenggan) {
		this.tblpdtaktapindaperenggan = tblpdtaktapindaperenggan;
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