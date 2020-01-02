package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindabab entity provides the base persistence definition of
 * the Tblpdtaktapindabab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindabab implements
		java.io.Serializable {

	// Fields

	private Long idAktapindabab;
	private Tblpdtaktapindabahagian tblpdtaktapindabahagian;
	private String namaBab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktapindaseksyens = new HashSet(0);
	private Set tblpdtpindaanaktababs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindabab() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindabab(Long idAktapindabab) {
		this.idAktapindabab = idAktapindabab;
	}

	/** full constructor */
	public AbstractTblpdtaktapindabab(Long idAktapindabab,
			Tblpdtaktapindabahagian tblpdtaktapindabahagian, String namaBab,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtaktapindaseksyens,
			Set tblpdtpindaanaktababs) {
		this.idAktapindabab = idAktapindabab;
		this.tblpdtaktapindabahagian = tblpdtaktapindabahagian;
		this.namaBab = namaBab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktapindaseksyens = tblpdtaktapindaseksyens;
		this.tblpdtpindaanaktababs = tblpdtpindaanaktababs;
	}

	// Property accessors

	public Long getIdAktapindabab() {
		return this.idAktapindabab;
	}

	public void setIdAktapindabab(Long idAktapindabab) {
		this.idAktapindabab = idAktapindabab;
	}

	public Tblpdtaktapindabahagian getTblpdtaktapindabahagian() {
		return this.tblpdtaktapindabahagian;
	}

	public void setTblpdtaktapindabahagian(
			Tblpdtaktapindabahagian tblpdtaktapindabahagian) {
		this.tblpdtaktapindabahagian = tblpdtaktapindabahagian;
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

	public Set getTblpdtaktapindaseksyens() {
		return this.tblpdtaktapindaseksyens;
	}

	public void setTblpdtaktapindaseksyens(Set tblpdtaktapindaseksyens) {
		this.tblpdtaktapindaseksyens = tblpdtaktapindaseksyens;
	}

	public Set getTblpdtpindaanaktababs() {
		return this.tblpdtpindaanaktababs;
	}

	public void setTblpdtpindaanaktababs(Set tblpdtpindaanaktababs) {
		this.tblpdtpindaanaktababs = tblpdtpindaanaktababs;
	}

}