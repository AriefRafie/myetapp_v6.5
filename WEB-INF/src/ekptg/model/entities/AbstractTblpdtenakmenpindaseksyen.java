package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindaseksyen entity provides the base persistence
 * definition of the Tblpdtenakmenpindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindaseksyen implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindaseksyen;
	private Tblpdtenakmenpindabab tblpdtenakmenpindabab;
	private Long noSeksyen;
	private String namaSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtenakmenpindaperenggans = new HashSet(0);
	private Set tblpdtpindaanenakmenseksyens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindaseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindaseksyen(Long idEnakmenpindaseksyen) {
		this.idEnakmenpindaseksyen = idEnakmenpindaseksyen;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindaseksyen(Long idEnakmenpindaseksyen,
			Tblpdtenakmenpindabab tblpdtenakmenpindabab, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtenakmenpindaperenggans, Set tblpdtpindaanenakmenseksyens) {
		this.idEnakmenpindaseksyen = idEnakmenpindaseksyen;
		this.tblpdtenakmenpindabab = tblpdtenakmenpindabab;
		this.noSeksyen = noSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtenakmenpindaperenggans = tblpdtenakmenpindaperenggans;
		this.tblpdtpindaanenakmenseksyens = tblpdtpindaanenakmenseksyens;
	}

	// Property accessors

	public Long getIdEnakmenpindaseksyen() {
		return this.idEnakmenpindaseksyen;
	}

	public void setIdEnakmenpindaseksyen(Long idEnakmenpindaseksyen) {
		this.idEnakmenpindaseksyen = idEnakmenpindaseksyen;
	}

	public Tblpdtenakmenpindabab getTblpdtenakmenpindabab() {
		return this.tblpdtenakmenpindabab;
	}

	public void setTblpdtenakmenpindabab(
			Tblpdtenakmenpindabab tblpdtenakmenpindabab) {
		this.tblpdtenakmenpindabab = tblpdtenakmenpindabab;
	}

	public Long getNoSeksyen() {
		return this.noSeksyen;
	}

	public void setNoSeksyen(Long noSeksyen) {
		this.noSeksyen = noSeksyen;
	}

	public String getNamaSeksyen() {
		return this.namaSeksyen;
	}

	public void setNamaSeksyen(String namaSeksyen) {
		this.namaSeksyen = namaSeksyen;
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

	public Set getTblpdtenakmenpindaperenggans() {
		return this.tblpdtenakmenpindaperenggans;
	}

	public void setTblpdtenakmenpindaperenggans(Set tblpdtenakmenpindaperenggans) {
		this.tblpdtenakmenpindaperenggans = tblpdtenakmenpindaperenggans;
	}

	public Set getTblpdtpindaanenakmenseksyens() {
		return this.tblpdtpindaanenakmenseksyens;
	}

	public void setTblpdtpindaanenakmenseksyens(Set tblpdtpindaanenakmenseksyens) {
		this.tblpdtpindaanenakmenseksyens = tblpdtpindaanenakmenseksyens;
	}

}