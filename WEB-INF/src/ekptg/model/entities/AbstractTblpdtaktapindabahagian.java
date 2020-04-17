package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtaktapindabahagian entity provides the base persistence
 * definition of the Tblpdtaktapindabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtaktapindabahagian implements
		java.io.Serializable {

	// Fields

	private Long idAktapindabahagian;
	private Tblpdtaktapindapenggal tblpdtaktapindapenggal;
	private Tblpdtaktapinda tblpdtaktapinda;
	private String namaBahagian;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtaktapindababs = new HashSet(0);
	private Set tblpdtpindaanaktabahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtaktapindabahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtaktapindabahagian(Long idAktapindabahagian) {
		this.idAktapindabahagian = idAktapindabahagian;
	}

	/** full constructor */
	public AbstractTblpdtaktapindabahagian(Long idAktapindabahagian,
			Tblpdtaktapindapenggal tblpdtaktapindapenggal,
			Tblpdtaktapinda tblpdtaktapinda, String namaBahagian, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtaktapindababs, Set tblpdtpindaanaktabahagians) {
		this.idAktapindabahagian = idAktapindabahagian;
		this.tblpdtaktapindapenggal = tblpdtaktapindapenggal;
		this.tblpdtaktapinda = tblpdtaktapinda;
		this.namaBahagian = namaBahagian;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtaktapindababs = tblpdtaktapindababs;
		this.tblpdtpindaanaktabahagians = tblpdtpindaanaktabahagians;
	}

	// Property accessors

	public Long getIdAktapindabahagian() {
		return this.idAktapindabahagian;
	}

	public void setIdAktapindabahagian(Long idAktapindabahagian) {
		this.idAktapindabahagian = idAktapindabahagian;
	}

	public Tblpdtaktapindapenggal getTblpdtaktapindapenggal() {
		return this.tblpdtaktapindapenggal;
	}

	public void setTblpdtaktapindapenggal(
			Tblpdtaktapindapenggal tblpdtaktapindapenggal) {
		this.tblpdtaktapindapenggal = tblpdtaktapindapenggal;
	}

	public Tblpdtaktapinda getTblpdtaktapinda() {
		return this.tblpdtaktapinda;
	}

	public void setTblpdtaktapinda(Tblpdtaktapinda tblpdtaktapinda) {
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

	public String getNamaBahagian() {
		return this.namaBahagian;
	}

	public void setNamaBahagian(String namaBahagian) {
		this.namaBahagian = namaBahagian;
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

	public Set getTblpdtaktapindababs() {
		return this.tblpdtaktapindababs;
	}

	public void setTblpdtaktapindababs(Set tblpdtaktapindababs) {
		this.tblpdtaktapindababs = tblpdtaktapindababs;
	}

	public Set getTblpdtpindaanaktabahagians() {
		return this.tblpdtpindaanaktabahagians;
	}

	public void setTblpdtpindaanaktabahagians(Set tblpdtpindaanaktabahagians) {
		this.tblpdtpindaanaktabahagians = tblpdtpindaanaktabahagians;
	}

}