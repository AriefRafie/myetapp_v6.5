package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenseksyen entity provides the base persistence definition
 * of the Tblpdtenakmenseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenseksyen implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenseksyen;
	private Tblpdtenakmenbab tblpdtenakmenbab;
	private String seksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenseksyens = new HashSet(0);
	private Set tblpdtenakmenperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenseksyen(Long idEnakmenseksyen) {
		this.idEnakmenseksyen = idEnakmenseksyen;
	}

	/** full constructor */
	public AbstractTblpdtenakmenseksyen(Long idEnakmenseksyen,
			Tblpdtenakmenbab tblpdtenakmenbab,
			String seksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanenakmenseksyens, Set tblpdtenakmenperenggans) {
		this.idEnakmenseksyen = idEnakmenseksyen;
		this.tblpdtenakmenbab = tblpdtenakmenbab;
		this.seksyen = seksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenseksyens = tblpdtpindaanenakmenseksyens;
		this.tblpdtenakmenperenggans = tblpdtenakmenperenggans;
	}

	// Property accessors

	public Long getIdEnakmenseksyen() {
		return this.idEnakmenseksyen;
	}

	public void setIdEnakmenseksyen(Long idEnakmenseksyen) {
		this.idEnakmenseksyen = idEnakmenseksyen;
	}

	public Tblpdtenakmenbab getTblpdtenakmenbab() {
		return this.tblpdtenakmenbab;
	}

	public void setTblpdtenakmenbab(Tblpdtenakmenbab tblpdtenakmenbab) {
		this.tblpdtenakmenbab = tblpdtenakmenbab;
	}

	public String getSeksyen() {
		return this.seksyen;
	}

	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
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

	public Set getTblpdtpindaanenakmenseksyens() {
		return this.tblpdtpindaanenakmenseksyens;
	}

	public void setTblpdtpindaanenakmenseksyens(Set tblpdtpindaanenakmenseksyens) {
		this.tblpdtpindaanenakmenseksyens = tblpdtpindaanenakmenseksyens;
	}

	public Set getTblpdtenakmenperenggans() {
		return this.tblpdtenakmenperenggans;
	}

	public void setTblpdtenakmenperenggans(Set tblpdtenakmenperenggans) {
		this.tblpdtenakmenperenggans = tblpdtenakmenperenggans;
	}

}