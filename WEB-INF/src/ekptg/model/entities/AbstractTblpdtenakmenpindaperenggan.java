package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindaperenggan entity provides the base persistence
 * definition of the Tblpdtenakmenpindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindaperenggan implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindaperenggan;
	private Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenparas = new HashSet(0);
	private Set tblpdtenakmenpindasubperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindaperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindaperenggan(Long idEnakmenpindaperenggan) {
		this.idEnakmenpindaperenggan = idEnakmenpindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindaperenggan(Long idEnakmenpindaperenggan,
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen,
			String notaBirai, String kandungan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenparas, Set tblpdtenakmenpindasubperenggans) {
		this.idEnakmenpindaperenggan = idEnakmenpindaperenggan;
		this.tblpdtenakmenpindaseksyen = tblpdtenakmenpindaseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenparas = tblpdtpindaanenakmenparas;
		this.tblpdtenakmenpindasubperenggans = tblpdtenakmenpindasubperenggans;
	}

	// Property accessors

	public Long getIdEnakmenpindaperenggan() {
		return this.idEnakmenpindaperenggan;
	}

	public void setIdEnakmenpindaperenggan(Long idEnakmenpindaperenggan) {
		this.idEnakmenpindaperenggan = idEnakmenpindaperenggan;
	}

	public Tblpdtenakmenpindaseksyen getTblpdtenakmenpindaseksyen() {
		return this.tblpdtenakmenpindaseksyen;
	}

	public void setTblpdtenakmenpindaseksyen(
			Tblpdtenakmenpindaseksyen tblpdtenakmenpindaseksyen) {
		this.tblpdtenakmenpindaseksyen = tblpdtenakmenpindaseksyen;
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

	public Set getTblpdtpindaanenakmenparas() {
		return this.tblpdtpindaanenakmenparas;
	}

	public void setTblpdtpindaanenakmenparas(Set tblpdtpindaanenakmenparas) {
		this.tblpdtpindaanenakmenparas = tblpdtpindaanenakmenparas;
	}

	public Set getTblpdtenakmenpindasubperenggans() {
		return this.tblpdtenakmenpindasubperenggans;
	}

	public void setTblpdtenakmenpindasubperenggans(
			Set tblpdtenakmenpindasubperenggans) {
		this.tblpdtenakmenpindasubperenggans = tblpdtenakmenpindasubperenggans;
	}

}