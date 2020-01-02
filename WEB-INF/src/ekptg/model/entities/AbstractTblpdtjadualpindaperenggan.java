package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualpindaperenggan entity provides the base persistence
 * definition of the Tblpdtjadualpindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualpindaperenggan implements
		java.io.Serializable {

	// Fields

	private Long idJadualpindaperenggan;
	private Tblpdtjadualpindaseksyen tblpdtjadualpindaseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanjadualparas = new HashSet(0);
	private Set tblpdtjadualpindasubperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualpindaperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualpindaperenggan(Long idJadualpindaperenggan) {
		this.idJadualpindaperenggan = idJadualpindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtjadualpindaperenggan(Long idJadualpindaperenggan,
			Tblpdtjadualpindaseksyen tblpdtjadualpindaseksyen,
			String notaBirai, String kandungan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanjadualparas, Set tblpdtjadualpindasubperenggans) {
		this.idJadualpindaperenggan = idJadualpindaperenggan;
		this.tblpdtjadualpindaseksyen = tblpdtjadualpindaseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanjadualparas = tblpdtpindaanjadualparas;
		this.tblpdtjadualpindasubperenggans = tblpdtjadualpindasubperenggans;
	}

	// Property accessors

	public Long getIdJadualpindaperenggan() {
		return this.idJadualpindaperenggan;
	}

	public void setIdJadualpindaperenggan(Long idJadualpindaperenggan) {
		this.idJadualpindaperenggan = idJadualpindaperenggan;
	}

	public Tblpdtjadualpindaseksyen getTblpdtjadualpindaseksyen() {
		return this.tblpdtjadualpindaseksyen;
	}

	public void setTblpdtjadualpindaseksyen(
			Tblpdtjadualpindaseksyen tblpdtjadualpindaseksyen) {
		this.tblpdtjadualpindaseksyen = tblpdtjadualpindaseksyen;
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

	public Set getTblpdtpindaanjadualparas() {
		return this.tblpdtpindaanjadualparas;
	}

	public void setTblpdtpindaanjadualparas(Set tblpdtpindaanjadualparas) {
		this.tblpdtpindaanjadualparas = tblpdtpindaanjadualparas;
	}

	public Set getTblpdtjadualpindasubperenggans() {
		return this.tblpdtjadualpindasubperenggans;
	}

	public void setTblpdtjadualpindasubperenggans(
			Set tblpdtjadualpindasubperenggans) {
		this.tblpdtjadualpindasubperenggans = tblpdtjadualpindasubperenggans;
	}

}