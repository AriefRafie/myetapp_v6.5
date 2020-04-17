package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpulasanteknikal entity provides the base persistence definition
 * of the Tblphpulasanteknikal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpulasanteknikal implements
		java.io.Serializable {

	// Fields

	private Long idUlasanteknikal;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppenyediaulasanjpphs = new HashSet(0);
	private Set tblphpulasankjpkjts = new HashSet(0);
	private Set tblphpulasanjupems = new HashSet(0);
	private Set tblphppenyediaulasankjpkjts = new HashSet(0);
	private Set tblphpulasanjpphs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpulasanteknikal() {
	}

	/** minimal constructor */
	public AbstractTblphpulasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
	}

	/** full constructor */
	public AbstractTblphpulasanteknikal(Long idUlasanteknikal, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblphppenyediaulasanjpphs, Set tblphpulasankjpkjts,
			Set tblphpulasanjupems, Set tblphppenyediaulasankjpkjts,
			Set tblphpulasanjpphs) {
		this.idUlasanteknikal = idUlasanteknikal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppenyediaulasanjpphs = tblphppenyediaulasanjpphs;
		this.tblphpulasankjpkjts = tblphpulasankjpkjts;
		this.tblphpulasanjupems = tblphpulasanjupems;
		this.tblphppenyediaulasankjpkjts = tblphppenyediaulasankjpkjts;
		this.tblphpulasanjpphs = tblphpulasanjpphs;
	}

	// Property accessors

	public Long getIdUlasanteknikal() {
		return this.idUlasanteknikal;
	}

	public void setIdUlasanteknikal(Long idUlasanteknikal) {
		this.idUlasanteknikal = idUlasanteknikal;
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

	public Set getTblphppenyediaulasanjpphs() {
		return this.tblphppenyediaulasanjpphs;
	}

	public void setTblphppenyediaulasanjpphs(Set tblphppenyediaulasanjpphs) {
		this.tblphppenyediaulasanjpphs = tblphppenyediaulasanjpphs;
	}

	public Set getTblphpulasankjpkjts() {
		return this.tblphpulasankjpkjts;
	}

	public void setTblphpulasankjpkjts(Set tblphpulasankjpkjts) {
		this.tblphpulasankjpkjts = tblphpulasankjpkjts;
	}

	public Set getTblphpulasanjupems() {
		return this.tblphpulasanjupems;
	}

	public void setTblphpulasanjupems(Set tblphpulasanjupems) {
		this.tblphpulasanjupems = tblphpulasanjupems;
	}

	public Set getTblphppenyediaulasankjpkjts() {
		return this.tblphppenyediaulasankjpkjts;
	}

	public void setTblphppenyediaulasankjpkjts(Set tblphppenyediaulasankjpkjts) {
		this.tblphppenyediaulasankjpkjts = tblphppenyediaulasankjpkjts;
	}

	public Set getTblphpulasanjpphs() {
		return this.tblphpulasanjpphs;
	}

	public void setTblphpulasanjpphs(Set tblphpulasanjpphs) {
		this.tblphpulasanjpphs = tblphpulasanjpphs;
	}

}