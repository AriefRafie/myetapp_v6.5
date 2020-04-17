package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenpindabahagian entity provides the base persistence
 * definition of the Tblpdtenakmenpindabahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenpindabahagian implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenpindabahagian;
	private Tblpdtenakmenpinda tblpdtenakmenpinda;
	private Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal;
	private String namaBahagian;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtenakmenpindababs = new HashSet(0);
	private Set tblpdtpindaanenakmenbahagians = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenpindabahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenpindabahagian(Long idEnakmenpindabahagian) {
		this.idEnakmenpindabahagian = idEnakmenpindabahagian;
	}

	/** full constructor */
	public AbstractTblpdtenakmenpindabahagian(Long idEnakmenpindabahagian,
			Tblpdtenakmenpinda tblpdtenakmenpinda,
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal,
			String namaBahagian, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpdtenakmenpindababs,
			Set tblpdtpindaanenakmenbahagians) {
		this.idEnakmenpindabahagian = idEnakmenpindabahagian;
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
		this.tblpdtenakmenpindapenggal = tblpdtenakmenpindapenggal;
		this.namaBahagian = namaBahagian;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtenakmenpindababs = tblpdtenakmenpindababs;
		this.tblpdtpindaanenakmenbahagians = tblpdtpindaanenakmenbahagians;
	}

	// Property accessors

	public Long getIdEnakmenpindabahagian() {
		return this.idEnakmenpindabahagian;
	}

	public void setIdEnakmenpindabahagian(Long idEnakmenpindabahagian) {
		this.idEnakmenpindabahagian = idEnakmenpindabahagian;
	}

	public Tblpdtenakmenpinda getTblpdtenakmenpinda() {
		return this.tblpdtenakmenpinda;
	}

	public void setTblpdtenakmenpinda(Tblpdtenakmenpinda tblpdtenakmenpinda) {
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
	}

	public Tblpdtenakmenpindapenggal getTblpdtenakmenpindapenggal() {
		return this.tblpdtenakmenpindapenggal;
	}

	public void setTblpdtenakmenpindapenggal(
			Tblpdtenakmenpindapenggal tblpdtenakmenpindapenggal) {
		this.tblpdtenakmenpindapenggal = tblpdtenakmenpindapenggal;
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

	public Set getTblpdtenakmenpindababs() {
		return this.tblpdtenakmenpindababs;
	}

	public void setTblpdtenakmenpindababs(Set tblpdtenakmenpindababs) {
		this.tblpdtenakmenpindababs = tblpdtenakmenpindababs;
	}

	public Set getTblpdtpindaanenakmenbahagians() {
		return this.tblpdtpindaanenakmenbahagians;
	}

	public void setTblpdtpindaanenakmenbahagians(
			Set tblpdtpindaanenakmenbahagians) {
		this.tblpdtpindaanenakmenbahagians = tblpdtpindaanenakmenbahagians;
	}

}