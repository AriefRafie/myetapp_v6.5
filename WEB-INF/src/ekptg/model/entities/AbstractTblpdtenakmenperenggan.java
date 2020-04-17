package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenperenggan entity provides the base persistence
 * definition of the Tblpdtenakmenperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenperenggan implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenperenggan;
	private Tblpdtenakmenseksyen tblpdtenakmenseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenparas = new HashSet(0);
	private Set tblpdtenakmensubperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenperenggan(Long idEnakmenperenggan) {
		this.idEnakmenperenggan = idEnakmenperenggan;
	}

	/** full constructor */
	public AbstractTblpdtenakmenperenggan(Long idEnakmenperenggan,
			Tblpdtenakmenseksyen tblpdtenakmenseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenparas,
			Set tblpdtenakmensubperenggans) {
		this.idEnakmenperenggan = idEnakmenperenggan;
		this.tblpdtenakmenseksyen = tblpdtenakmenseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenparas = tblpdtpindaanenakmenparas;
		this.tblpdtenakmensubperenggans = tblpdtenakmensubperenggans;
	}

	// Property accessors

	public Long getIdEnakmenperenggan() {
		return this.idEnakmenperenggan;
	}

	public void setIdEnakmenperenggan(Long idEnakmenperenggan) {
		this.idEnakmenperenggan = idEnakmenperenggan;
	}

	public Tblpdtenakmenseksyen getTblpdtenakmenseksyen() {
		return this.tblpdtenakmenseksyen;
	}

	public void setTblpdtenakmenseksyen(
			Tblpdtenakmenseksyen tblpdtenakmenseksyen) {
		this.tblpdtenakmenseksyen = tblpdtenakmenseksyen;
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

	public Set getTblpdtenakmensubperenggans() {
		return this.tblpdtenakmensubperenggans;
	}

	public void setTblpdtenakmensubperenggans(Set tblpdtenakmensubperenggans) {
		this.tblpdtenakmensubperenggans = tblpdtenakmensubperenggans;
	}

}