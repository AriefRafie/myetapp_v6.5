package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktaseksyen entity provides the base persistence definition of
 * the Tblpdtaktaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktaseksyen implements java.io.Serializable {

	// Fields

	private Long idAktaseksyen;
	private Tblpdtaktabab tblpdtaktabab;
	private String seksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktaperenggans = new HashSet(0);
	private Set tblpdtpindaanaktaseksyens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktaseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktaseksyen(Long idAktaseksyen) {
		this.idAktaseksyen = idAktaseksyen;
	}

	/** full constructor */
	public AbstractTblpdtaktaseksyen(Long idAktaseksyen,
			Tblpdtaktabab tblpdtaktabab, Long noSeksyen, String seksyen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktaperenggans,
			Set tblpdtpindaanaktaseksyens) {
		this.idAktaseksyen = idAktaseksyen;
		this.tblpdtaktabab = tblpdtaktabab;
		this.seksyen = seksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktaperenggans = tblpdtaktaperenggans;
		this.tblpdtpindaanaktaseksyens = tblpdtpindaanaktaseksyens;
	}

	// Property accessors

	public Long getIdAktaseksyen() {
		return this.idAktaseksyen;
	}

	public void setIdAktaseksyen(Long idAktaseksyen) {
		this.idAktaseksyen = idAktaseksyen;
	}

	public Tblpdtaktabab getTblpdtaktabab() {
		return this.tblpdtaktabab;
	}

	public void setTblpdtaktabab(Tblpdtaktabab tblpdtaktabab) {
		this.tblpdtaktabab = tblpdtaktabab;
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

	public Set getTblpdtaktaperenggans() {
		return this.tblpdtaktaperenggans;
	}

	public void setTblpdtaktaperenggans(Set tblpdtaktaperenggans) {
		this.tblpdtaktaperenggans = tblpdtaktaperenggans;
	}

	public Set getTblpdtpindaanaktaseksyens() {
		return this.tblpdtpindaanaktaseksyens;
	}

	public void setTblpdtpindaanaktaseksyens(Set tblpdtpindaanaktaseksyens) {
		this.tblpdtpindaanaktaseksyens = tblpdtpindaanaktaseksyens;
	}

}