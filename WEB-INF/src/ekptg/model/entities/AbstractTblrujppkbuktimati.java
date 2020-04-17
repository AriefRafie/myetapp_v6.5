package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppkbuktimati entity provides the base persistence definition of
 * the Tblrujppkbuktimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppkbuktimati implements
		java.io.Serializable {

	// Fields

	private Long idBuktimati;
	private String kod;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkpermohonans = new HashSet(0);
	private Set tblppkborangpsimatis = new HashSet(0);
	private Set tblppkborangasimatis = new HashSet(0);
	private Set tblppksimatis = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppkbuktimati() {
	}

	/** minimal constructor */
	public AbstractTblrujppkbuktimati(Long idBuktimati) {
		this.idBuktimati = idBuktimati;
	}

	/** full constructor */
	public AbstractTblrujppkbuktimati(Long idBuktimati, String kod,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkpermohonans,
			Set tblppkborangpsimatis, Set tblppkborangasimatis,
			Set tblppksimatis) {
		this.idBuktimati = idBuktimati;
		this.kod = kod;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkpermohonans = tblppkpermohonans;
		this.tblppkborangpsimatis = tblppkborangpsimatis;
		this.tblppkborangasimatis = tblppkborangasimatis;
		this.tblppksimatis = tblppksimatis;
	}

	// Property accessors

	public Long getIdBuktimati() {
		return this.idBuktimati;
	}

	public void setIdBuktimati(Long idBuktimati) {
		this.idBuktimati = idBuktimati;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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

	public Set getTblppkpermohonans() {
		return this.tblppkpermohonans;
	}

	public void setTblppkpermohonans(Set tblppkpermohonans) {
		this.tblppkpermohonans = tblppkpermohonans;
	}

	public Set getTblppkborangpsimatis() {
		return this.tblppkborangpsimatis;
	}

	public void setTblppkborangpsimatis(Set tblppkborangpsimatis) {
		this.tblppkborangpsimatis = tblppkborangpsimatis;
	}

	public Set getTblppkborangasimatis() {
		return this.tblppkborangasimatis;
	}

	public void setTblppkborangasimatis(Set tblppkborangasimatis) {
		this.tblppkborangasimatis = tblppkborangasimatis;
	}

	public Set getTblppksimatis() {
		return this.tblppksimatis;
	}

	public void setTblppksimatis(Set tblppksimatis) {
		this.tblppksimatis = tblppksimatis;
	}

}