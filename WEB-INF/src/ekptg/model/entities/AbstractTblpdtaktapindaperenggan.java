package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindaperenggan entity provides the base persistence
 * definition of the Tblpdtaktapindaperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindaperenggan implements
		java.io.Serializable {

	// Fields

	private Long idAktapindaperenggan;
	private Tblpdtaktapindaseksyen tblpdtaktapindaseksyen;
	private String notaBirai;
	private String kandungan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktapindasubperenggans = new HashSet(0);
	private Set tblpdtpindaanaktaparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindaperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindaperenggan(Long idAktapindaperenggan) {
		this.idAktapindaperenggan = idAktapindaperenggan;
	}

	/** full constructor */
	public AbstractTblpdtaktapindaperenggan(Long idAktapindaperenggan,
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen, String notaBirai,
			String kandungan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktapindasubperenggans,
			Set tblpdtpindaanaktaparas) {
		this.idAktapindaperenggan = idAktapindaperenggan;
		this.tblpdtaktapindaseksyen = tblpdtaktapindaseksyen;
		this.notaBirai = notaBirai;
		this.kandungan = kandungan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktapindasubperenggans = tblpdtaktapindasubperenggans;
		this.tblpdtpindaanaktaparas = tblpdtpindaanaktaparas;
	}

	// Property accessors

	public Long getIdAktapindaperenggan() {
		return this.idAktapindaperenggan;
	}

	public void setIdAktapindaperenggan(Long idAktapindaperenggan) {
		this.idAktapindaperenggan = idAktapindaperenggan;
	}

	public Tblpdtaktapindaseksyen getTblpdtaktapindaseksyen() {
		return this.tblpdtaktapindaseksyen;
	}

	public void setTblpdtaktapindaseksyen(
			Tblpdtaktapindaseksyen tblpdtaktapindaseksyen) {
		this.tblpdtaktapindaseksyen = tblpdtaktapindaseksyen;
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

	public Set getTblpdtaktapindasubperenggans() {
		return this.tblpdtaktapindasubperenggans;
	}

	public void setTblpdtaktapindasubperenggans(Set tblpdtaktapindasubperenggans) {
		this.tblpdtaktapindasubperenggans = tblpdtaktapindasubperenggans;
	}

	public Set getTblpdtpindaanaktaparas() {
		return this.tblpdtpindaanaktaparas;
	}

	public void setTblpdtpindaanaktaparas(Set tblpdtpindaanaktaparas) {
		this.tblpdtpindaanaktaparas = tblpdtpindaanaktaparas;
	}

}