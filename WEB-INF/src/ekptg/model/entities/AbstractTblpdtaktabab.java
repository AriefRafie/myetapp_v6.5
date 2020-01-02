package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktabab entity provides the base persistence definition of the
 * Tblpdtaktabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktabab implements java.io.Serializable {

	// Fields

	private Long idAktabab;
	private Tblpdtaktabahagian tblpdtaktabahagian;
	private String tajukBab;
	private String noBab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanaktababs = new HashSet(0);
	private Set tblpdtaktaseksyens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktabab() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktabab(Long idAktabab) {
		this.idAktabab = idAktabab;
	}

	/** full constructor */
	public AbstractTblpdtaktabab(Long idAktabab,
			Tblpdtaktabahagian tblpdtaktabahagian, String tajukBab,String noBab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanaktababs,
			Set tblpdtaktaseksyens) {
		this.idAktabab = idAktabab;
		this.tblpdtaktabahagian = tblpdtaktabahagian;
		this.tajukBab = tajukBab;
		this.noBab = noBab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanaktababs = tblpdtpindaanaktababs;
		this.tblpdtaktaseksyens = tblpdtaktaseksyens;
	}

	// Property accessors

	public Long getIdAktabab() {
		return this.idAktabab;
	}

	public void setIdAktabab(Long idAktabab) {
		this.idAktabab = idAktabab;
	}

	public Tblpdtaktabahagian getTblpdtaktabahagian() {
		return this.tblpdtaktabahagian;
	}

	public void setTblpdtaktabahagian(Tblpdtaktabahagian tblpdtaktabahagian) {
		this.tblpdtaktabahagian = tblpdtaktabahagian;
	}

	public String getTajukBab() {
		return this.tajukBab;
	}

	public void setTajukBab(String tajukBab) {
		this.tajukBab = tajukBab;
	}
	public String getNoBab() {
		return this.noBab;
	}

	public void setNoBab(String noBab) {
		this.noBab = noBab;
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

	public Set getTblpdtpindaanaktababs() {
		return this.tblpdtpindaanaktababs;
	}

	public void setTblpdtpindaanaktababs(Set tblpdtpindaanaktababs) {
		this.tblpdtpindaanaktababs = tblpdtpindaanaktababs;
	}

	public Set getTblpdtaktaseksyens() {
		return this.tblpdtaktaseksyens;
	}

	public void setTblpdtaktaseksyens(Set tblpdtaktaseksyens) {
		this.tblpdtaktaseksyens = tblpdtaktaseksyens;
	}

}