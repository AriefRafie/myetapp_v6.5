package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindaseksyen entity provides the base persistence
 * definition of the Tblpdtaktapindaseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindaseksyen implements
		java.io.Serializable {

	// Fields

	private Long idAktapindaseksyen;
	private Tblpdtaktapindabab tblpdtaktapindabab;
	private Long noSeksyen;
	private String namaSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanaktaseksyens = new HashSet(0);
	private Set tblpdtaktapindaperenggans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindaseksyen() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindaseksyen(Long idAktapindaseksyen) {
		this.idAktapindaseksyen = idAktapindaseksyen;
	}

	/** full constructor */
	public AbstractTblpdtaktapindaseksyen(Long idAktapindaseksyen,
			Tblpdtaktapindabab tblpdtaktapindabab, Long noSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtpindaanaktaseksyens, Set tblpdtaktapindaperenggans) {
		this.idAktapindaseksyen = idAktapindaseksyen;
		this.tblpdtaktapindabab = tblpdtaktapindabab;
		this.noSeksyen = noSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanaktaseksyens = tblpdtpindaanaktaseksyens;
		this.tblpdtaktapindaperenggans = tblpdtaktapindaperenggans;
	}

	// Property accessors

	public Long getIdAktapindaseksyen() {
		return this.idAktapindaseksyen;
	}

	public void setIdAktapindaseksyen(Long idAktapindaseksyen) {
		this.idAktapindaseksyen = idAktapindaseksyen;
	}

	public Tblpdtaktapindabab getTblpdtaktapindabab() {
		return this.tblpdtaktapindabab;
	}

	public void setTblpdtaktapindabab(Tblpdtaktapindabab tblpdtaktapindabab) {
		this.tblpdtaktapindabab = tblpdtaktapindabab;
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

	public Set getTblpdtpindaanaktaseksyens() {
		return this.tblpdtpindaanaktaseksyens;
	}

	public void setTblpdtpindaanaktaseksyens(Set tblpdtpindaanaktaseksyens) {
		this.tblpdtpindaanaktaseksyens = tblpdtpindaanaktaseksyens;
	}

	public Set getTblpdtaktapindaperenggans() {
		return this.tblpdtaktapindaperenggans;
	}

	public void setTblpdtaktapindaperenggans(Set tblpdtaktapindaperenggans) {
		this.tblpdtaktapindaperenggans = tblpdtaktapindaperenggans;
	}

}