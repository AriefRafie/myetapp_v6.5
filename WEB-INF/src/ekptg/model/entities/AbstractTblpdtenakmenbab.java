package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenbab entity provides the base persistence definition of
 * the Tblpdtenakmenbab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenbab implements java.io.Serializable {

	// Fields

	private Long idEnakmenbab;
	private Tblpdtenakmenbahagian tblpdtenakmenbahagian;
	private String tajukBab;
	private String noBab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtenakmenseksyens = new HashSet(0);
	private Set tblpdtpindaanenakmenbabs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenbab() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenbab(Long idEnakmenbab) {
		this.idEnakmenbab = idEnakmenbab;
	}

	/** full constructor */
	public AbstractTblpdtenakmenbab(Long idEnakmenbab,
			Tblpdtenakmenbahagian tblpdtenakmenbahagian, String tajukBab,String noBab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtenakmenseksyens,
			Set tblpdtpindaanenakmenbabs) {
		this.idEnakmenbab = idEnakmenbab;
		this.tblpdtenakmenbahagian = tblpdtenakmenbahagian;
		this.tajukBab = tajukBab;
		this.noBab = noBab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtenakmenseksyens = tblpdtenakmenseksyens;
		this.tblpdtpindaanenakmenbabs = tblpdtpindaanenakmenbabs;
	}

	// Property accessors

	public Long getIdEnakmenbab() {
		return this.idEnakmenbab;
	}

	public void setIdEnakmenbab(Long idEnakmenbab) {
		this.idEnakmenbab = idEnakmenbab;
	}

	public Tblpdtenakmenbahagian getTblpdtenakmenbahagian() {
		return this.tblpdtenakmenbahagian;
	}

	public void setTblpdtenakmenbahagian(
			Tblpdtenakmenbahagian tblpdtenakmenbahagian) {
		this.tblpdtenakmenbahagian = tblpdtenakmenbahagian;
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

	public Set getTblpdtenakmenseksyens() {
		return this.tblpdtenakmenseksyens;
	}

	public void setTblpdtenakmenseksyens(Set tblpdtenakmenseksyens) {
		this.tblpdtenakmenseksyens = tblpdtenakmenseksyens;
	}

	public Set getTblpdtpindaanenakmenbabs() {
		return this.tblpdtpindaanenakmenbabs;
	}

	public void setTblpdtpindaanenakmenbabs(Set tblpdtpindaanenakmenbabs) {
		this.tblpdtpindaanenakmenbabs = tblpdtpindaanenakmenbabs;
	}

}