package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualpindaseksyen entity provides the base persistence
 * definition of the Tblpdtjadualpindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualpindaseksyen implements
		java.io.Serializable {

	// Fields

	private Long idJadualpindaseksyen;
	private Tblpdtjadualpinda tblpdtjadualpinda;
	private Long noSeksyen;
	private String namaSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtjadualpindaperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualpindaseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualpindaseksyen(Long idJadualpindaseksyen) {
		this.idJadualpindaseksyen = idJadualpindaseksyen;
	}

	/** full constructor */
	public AbstractTblpdtjadualpindaseksyen(Long idJadualpindaseksyen,
			Tblpdtjadualpinda tblpdtjadualpinda, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualpindaperenggans) {
		this.idJadualpindaseksyen = idJadualpindaseksyen;
		this.tblpdtjadualpinda = tblpdtjadualpinda;
		this.noSeksyen = noSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtjadualpindaperenggans = tblpdtjadualpindaperenggans;
	}

	// Property accessors

	public Long getIdJadualpindaseksyen() {
		return this.idJadualpindaseksyen;
	}

	public void setIdJadualpindaseksyen(Long idJadualpindaseksyen) {
		this.idJadualpindaseksyen = idJadualpindaseksyen;
	}

	public Tblpdtjadualpinda getTblpdtjadualpinda() {
		return this.tblpdtjadualpinda;
	}

	public void setTblpdtjadualpinda(Tblpdtjadualpinda tblpdtjadualpinda) {
		this.tblpdtjadualpinda = tblpdtjadualpinda;
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

	public Set getTblpdtjadualpindaperenggans() {
		return this.tblpdtjadualpindaperenggans;
	}

	public void setTblpdtjadualpindaperenggans(Set tblpdtjadualpindaperenggans) {
		this.tblpdtjadualpindaperenggans = tblpdtjadualpindaperenggans;
	}

}