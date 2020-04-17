package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindabab entity provides the base persistence definition
 * of the Tblpdtenakmenpindabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindabab implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindabab;
	private Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian;
	private String namaBab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtenakmenpindaseksyens = new HashSet(0);
	private Set tblpdtpindaanenakmenbabs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindabab() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindabab(Long idEnakmenpindabab) {
		this.idEnakmenpindabab = idEnakmenpindabab;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindabab(Long idEnakmenpindabab,
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian,
			String namaBab, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtenakmenpindaseksyens,
			Set tblpdtpindaanenakmenbabs) {
		this.idEnakmenpindabab = idEnakmenpindabab;
		this.tblpdtenakmenpindabahagian = tblpdtenakmenpindabahagian;
		this.namaBab = namaBab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtenakmenpindaseksyens = tblpdtenakmenpindaseksyens;
		this.tblpdtpindaanenakmenbabs = tblpdtpindaanenakmenbabs;
	}

	// Property accessors

	public Long getIdEnakmenpindabab() {
		return this.idEnakmenpindabab;
	}

	public void setIdEnakmenpindabab(Long idEnakmenpindabab) {
		this.idEnakmenpindabab = idEnakmenpindabab;
	}

	public Tblpdtenakmenpindabahagian getTblpdtenakmenpindabahagian() {
		return this.tblpdtenakmenpindabahagian;
	}

	public void setTblpdtenakmenpindabahagian(
			Tblpdtenakmenpindabahagian tblpdtenakmenpindabahagian) {
		this.tblpdtenakmenpindabahagian = tblpdtenakmenpindabahagian;
	}

	public String getNamaBab() {
		return this.namaBab;
	}

	public void setNamaBab(String namaBab) {
		this.namaBab = namaBab;
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

	public Set getTblpdtenakmenpindaseksyens() {
		return this.tblpdtenakmenpindaseksyens;
	}

	public void setTblpdtenakmenpindaseksyens(Set tblpdtenakmenpindaseksyens) {
		this.tblpdtenakmenpindaseksyens = tblpdtenakmenpindaseksyens;
	}

	public Set getTblpdtpindaanenakmenbabs() {
		return this.tblpdtpindaanenakmenbabs;
	}

	public void setTblpdtpindaanenakmenbabs(Set tblpdtpindaanenakmenbabs) {
		this.tblpdtpindaanenakmenbabs = tblpdtpindaanenakmenbabs;
	}

}