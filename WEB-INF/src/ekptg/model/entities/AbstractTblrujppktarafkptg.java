package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppktarafkptg entity provides the base persistence definition of
 * the Tblrujppktarafkptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppktarafkptg implements
		java.io.Serializable {

	// Fields

	private Long idTarafkptg;
	private String kod;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangaobs = new HashSet(0);
	private Set tblppkborangpobs = new HashSet(0);
	private Set tblppkborangapemohons = new HashSet(0);
	private Set tblppkborangppemohons = new HashSet(0);
	private Set tblppkobs = new HashSet(0);
	private Set tblppkpermohonans = new HashSet(0);
	private Set tblppkpemohons = new HashSet(0);
	private Set tblppkhbgnfaraids = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppktarafkptg() {
	}

	/** minimal constructor */
	public AbstractTblrujppktarafkptg(Long idTarafkptg) {
		this.idTarafkptg = idTarafkptg;
	}

	/** full constructor */
	public AbstractTblrujppktarafkptg(Long idTarafkptg, String kod,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangaobs,
			Set tblppkborangpobs, Set tblppkborangapemohons,
			Set tblppkborangppemohons, Set tblppkobs, Set tblppkpermohonans,
			Set tblppkpemohons, Set tblppkhbgnfaraids) {
		this.idTarafkptg = idTarafkptg;
		this.kod = kod;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangaobs = tblppkborangaobs;
		this.tblppkborangpobs = tblppkborangpobs;
		this.tblppkborangapemohons = tblppkborangapemohons;
		this.tblppkborangppemohons = tblppkborangppemohons;
		this.tblppkobs = tblppkobs;
		this.tblppkpermohonans = tblppkpermohonans;
		this.tblppkpemohons = tblppkpemohons;
		this.tblppkhbgnfaraids = tblppkhbgnfaraids;
	}

	// Property accessors

	public Long getIdTarafkptg() {
		return this.idTarafkptg;
	}

	public void setIdTarafkptg(Long idTarafkptg) {
		this.idTarafkptg = idTarafkptg;
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

	public Set getTblppkborangaobs() {
		return this.tblppkborangaobs;
	}

	public void setTblppkborangaobs(Set tblppkborangaobs) {
		this.tblppkborangaobs = tblppkborangaobs;
	}

	public Set getTblppkborangpobs() {
		return this.tblppkborangpobs;
	}

	public void setTblppkborangpobs(Set tblppkborangpobs) {
		this.tblppkborangpobs = tblppkborangpobs;
	}

	public Set getTblppkborangapemohons() {
		return this.tblppkborangapemohons;
	}

	public void setTblppkborangapemohons(Set tblppkborangapemohons) {
		this.tblppkborangapemohons = tblppkborangapemohons;
	}

	public Set getTblppkborangppemohons() {
		return this.tblppkborangppemohons;
	}

	public void setTblppkborangppemohons(Set tblppkborangppemohons) {
		this.tblppkborangppemohons = tblppkborangppemohons;
	}

	public Set getTblppkobs() {
		return this.tblppkobs;
	}

	public void setTblppkobs(Set tblppkobs) {
		this.tblppkobs = tblppkobs;
	}

	public Set getTblppkpermohonans() {
		return this.tblppkpermohonans;
	}

	public void setTblppkpermohonans(Set tblppkpermohonans) {
		this.tblppkpermohonans = tblppkpermohonans;
	}

	public Set getTblppkpemohons() {
		return this.tblppkpemohons;
	}

	public void setTblppkpemohons(Set tblppkpemohons) {
		this.tblppkpemohons = tblppkpemohons;
	}

	public Set getTblppkhbgnfaraids() {
		return this.tblppkhbgnfaraids;
	}

	public void setTblppkhbgnfaraids(Set tblppkhbgnfaraids) {
		this.tblppkhbgnfaraids = tblppkhbgnfaraids;
	}

}