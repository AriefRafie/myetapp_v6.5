package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualsubperenggan entity provides the base persistence
 * definition of the Tblpdtjadualsubperenggan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualsubperenggan implements
		java.io.Serializable {

	// Fields

	private Long idJadualsubperenggan;
	private Tblpdtjadualperenggan tblpdtjadualperenggan;
	private String notaBirai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanjadualsubparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualsubperenggan() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualsubperenggan(Long idJadualsubperenggan,
			Tblpdtjadualperenggan tblpdtjadualperenggan) {
		this.idJadualsubperenggan = idJadualsubperenggan;
		this.tblpdtjadualperenggan = tblpdtjadualperenggan;
	}

	/** full constructor */
	public AbstractTblpdtjadualsubperenggan(Long idJadualsubperenggan,
			Tblpdtjadualperenggan tblpdtjadualperenggan, String notaBirai,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanjadualsubparas) {
		this.idJadualsubperenggan = idJadualsubperenggan;
		this.tblpdtjadualperenggan = tblpdtjadualperenggan;
		this.notaBirai = notaBirai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanjadualsubparas = tblpdtpindaanjadualsubparas;
	}

	// Property accessors

	public Long getIdJadualsubperenggan() {
		return this.idJadualsubperenggan;
	}

	public void setIdJadualsubperenggan(Long idJadualsubperenggan) {
		this.idJadualsubperenggan = idJadualsubperenggan;
	}

	public Tblpdtjadualperenggan getTblpdtjadualperenggan() {
		return this.tblpdtjadualperenggan;
	}

	public void setTblpdtjadualperenggan(
			Tblpdtjadualperenggan tblpdtjadualperenggan) {
		this.tblpdtjadualperenggan = tblpdtjadualperenggan;
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