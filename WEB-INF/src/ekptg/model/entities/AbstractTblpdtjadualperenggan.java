package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualperenggan entity provides the base persistence definition
 * of the Tblpdtjadualperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualperenggan implements
		java.io.Serializable {

	// Fields

	private Long idJadualperenggan;
	private Tblpdtjadualseksyen tblpdtjadualseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtjadualsubperenggans = new HashSet(0);
	private Set tblpdtpindaanjadualparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualperenggan(Long idJadualperenggan) {
		this.idJadualperenggan = idJadualperenggan;
	}

	/** full constructor */
	public AbstractTblpdtjadualperenggan(Long idJadualperenggan,
			Tblpdtjadualseksyen tblpdtjadualseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtjadualsubperenggans,
			Set tblpdtpindaanjadualparas) {
		this.idJadualperenggan = idJadualperenggan;
		this.tblpdtjadualseksyen = tblpdtjadualseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtjadualsubperenggans = tblpdtjadualsubperenggans;
		this.tblpdtpindaanjadualparas = tblpdtpindaanjadualparas;
	}

	// Property accessors

	public Long getIdJadualperenggan() {
		return this.idJadualperenggan;
	}

	public void setIdJadualperenggan(Long idJadualperenggan) {
		this.idJadualperenggan = idJadualperenggan;
	}

	public Tblpdtjadualseksyen getTblpdtjadualseksyen() {
		return this.tblpdtjadualseksyen;
	}

	public void setTblpdtjadualseksyen(Tblpdtjadualseksyen tblpdtjadualseksyen) {
		this.tblpdtjadualseksyen = tblpdtjadualseksyen;
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

	public Set getTblpdtjadualsubperenggans() {
		return this.tblpdtjadualsubperenggans;
	}

	public void setTblpdtjadualsubperenggans(Set tblpdtjadualsubperenggans) {
		this.tblpdtjadualsubperenggans = tblpdtjadualsubperenggans;
	}

	public Set getTblpdtpindaanjadualparas() {
		return this.tblpdtpindaanjadualparas;
	}

	public void setTblpdtpindaanjadualparas(Set tblpdtpindaanjadualparas) {
		this.tblpdtpindaanjadualparas = tblpdtpindaanjadualparas;
	}

}