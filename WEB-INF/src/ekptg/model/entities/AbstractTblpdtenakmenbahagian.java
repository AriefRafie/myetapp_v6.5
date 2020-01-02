package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtenakmenbahagian entity provides the base persistence definition
 * of the Tblpdtenakmenbahagian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtenakmenbahagian implements
		java.io.Serializable {

	// Fields

	private Long idEnakmenbahagian;
	private Tblpdtenakmen tblpdtenakmen;
	private Tblpdtenakmenpenggal tblpdtenakmenpenggal;
	private String tajukBahagian;
	private Long idMasuk;
	private String noBahagian;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtpindaanenakmenbahagians = new HashSet(0);
	private Set tblpdtenakmenbabs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtenakmenbahagian() {
	}

	/** minimal constructor */
	public AbstractTblpdtenakmenbahagian(Long idEnakmenbahagian) {
		this.idEnakmenbahagian = idEnakmenbahagian;
	}

	/** full constructor */
	public AbstractTblpdtenakmenbahagian(Long idEnakmenbahagian,
			Tblpdtenakmen tblpdtenakmen,
			Tblpdtenakmenpenggal tblpdtenakmenpenggal, String tajukBahagian,String noBahagian,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblpdtpindaanenakmenbahagians,
			Set tblpdtenakmenbabs) {
		this.idEnakmenbahagian = idEnakmenbahagian;
		this.tblpdtenakmen = tblpdtenakmen;
		this.tblpdtenakmenpenggal = tblpdtenakmenpenggal;
		this.tajukBahagian = tajukBahagian;
		this.idMasuk = idMasuk;
		this.noBahagian = noBahagian;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtpindaanenakmenbahagians = tblpdtpindaanenakmenbahagians;
		this.tblpdtenakmenbabs = tblpdtenakmenbabs;
	}

	// Property accessors

	public Long getIdEnakmenbahagian() {
		return this.idEnakmenbahagian;
	}

	public void setIdEnakmenbahagian(Long idEnakmenbahagian) {
		this.idEnakmenbahagian = idEnakmenbahagian;
	}

	public Tblpdtenakmen getTblpdtenakmen() {
		return this.tblpdtenakmen;
	}

	public void setTblpdtenakmen(Tblpdtenakmen tblpdtenakmen) {
		this.tblpdtenakmen = tblpdtenakmen;
	}

	public Tblpdtenakmenpenggal getTblpdtenakmenpenggal() {
		return this.tblpdtenakmenpenggal;
	}

	public void setTblpdtenakmenpenggal(
			Tblpdtenakmenpenggal tblpdtenakmenpenggal) {
		this.tblpdtenakmenpenggal = tblpdtenakmenpenggal;
	}

	public String getTajukBahagian() {
		return this.tajukBahagian;
	}

	public void setTajukBahagian(String tajukBahagian) {
		this.tajukBahagian = tajukBahagian;
	}
	public String getNoBahagian() {
		return this.noBahagian;
	}

	public void setNoBahagian(String noBahagian) {
		this.noBahagian = noBahagian;
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

	public Set getTblpdtpindaanenakmenbahagians() {
		return this.tblpdtpindaanenakmenbahagians;
	}

	public void setTblpdtpindaanenakmenbahagians(
			Set tblpdtpindaanenakmenbahagians) {
		this.tblpdtpindaanenakmenbahagians = tblpdtpindaanenakmenbahagians;
	}

	public Set getTblpdtenakmenbabs() {
		return this.tblpdtenakmenbabs;
	}

	public void setTblpdtenakmenbabs(Set tblpdtenakmenbabs) {
		this.tblpdtenakmenbabs = tblpdtenakmenbabs;
	}

}