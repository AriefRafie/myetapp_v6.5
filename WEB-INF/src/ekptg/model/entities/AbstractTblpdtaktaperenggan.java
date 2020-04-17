package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktaperenggan entity provides the base persistence definition
 * of the Tblpdtaktaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktaperenggan implements
		java.io.Serializable {

	// Fields

	private Long idAktaperenggan;
	private Tblpdtaktaseksyen tblpdtaktaseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktasubperenggans = new HashSet(0);
	private Set tblpdtpindaanaktaparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktaperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktaperenggan(Long idAktaperenggan) {
		this.idAktaperenggan = idAktaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtaktaperenggan(Long idAktaperenggan,
			Tblpdtaktaseksyen tblpdtaktaseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktasubperenggans,
			Set tblpdtpindaanaktaparas) {
		this.idAktaperenggan = idAktaperenggan;
		this.tblpdtaktaseksyen = tblpdtaktaseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktasubperenggans = tblpdtaktasubperenggans;
		this.tblpdtpindaanaktaparas = tblpdtpindaanaktaparas;
	}

	// Property accessors

	public Long getIdAktaperenggan() {
		return this.idAktaperenggan;
	}

	public void setIdAktaperenggan(Long idAktaperenggan) {
		this.idAktaperenggan = idAktaperenggan;
	}

	public Tblpdtaktaseksyen getTblpdtaktaseksyen() {
		return this.tblpdtaktaseksyen;
	}

	public void setTblpdtaktaseksyen(Tblpdtaktaseksyen tblpdtaktaseksyen) {
		this.tblpdtaktaseksyen = tblpdtaktaseksyen;
	}

	public String getNotaBirai() {
		return this.notaBirai;
	}

	public void setNotaBirai(String notaBirai) {
		this.notaBirai = notaBirai;
	}

	public String getKandungan() {
		return this.kandungan;
	}

	public void setKandungan(String kandungan) {
		this.kandungan = kandungan;
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

	public Set getTblpdtaktasubperenggans() {
		return this.tblpdtaktasubperenggans;
	}

	public void setTblpdtaktasubperenggans(Set tblpdtaktasubperenggans) {
		this.tblpdtaktasubperenggans = tblpdtaktasubperenggans;
	}

	public Set getTblpdtpindaanaktaparas() {
		return this.tblpdtpindaanaktaparas;
	}

	public void setTblpdtpindaanaktaparas(Set tblpdtpindaanaktaparas) {
		this.tblpdtpindaanaktaparas = tblpdtpindaanaktaparas;
	}

}