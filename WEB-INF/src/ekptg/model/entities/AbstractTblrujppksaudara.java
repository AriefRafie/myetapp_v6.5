package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppksaudara entity provides the base persistence definition of
 * the Tblrujppksaudara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppksaudara implements java.io.Serializable {

	// Fields

	private Long idSaudara;
	private String kod;
	private String keterangan;
	private String jantina;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangpobs = new HashSet(0);
	private Set tblppkborangppemohons = new HashSet(0);
	private Set tblppkpemohons = new HashSet(0);
	private Set tblppkhbgnfaraids = new HashSet(0);
	private Set tblppkborangapemohons = new HashSet(0);
	private Set tblppkborangaobs = new HashSet(0);
	private Set tblppkobs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppksaudara() {
	}

	/** minimal constructor */
	public AbstractTblrujppksaudara(Long idSaudara) {
		this.idSaudara = idSaudara;
	}

	/** full constructor */
	public AbstractTblrujppksaudara(Long idSaudara, String kod,
			String keterangan, String jantina, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangpobs,
			Set tblppkborangppemohons, Set tblppkpemohons,
			Set tblppkhbgnfaraids, Set tblppkborangapemohons,
			Set tblppkborangaobs, Set tblppkobs) {
		this.idSaudara = idSaudara;
		this.kod = kod;
		this.keterangan = keterangan;
		this.jantina = jantina;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangpobs = tblppkborangpobs;
		this.tblppkborangppemohons = tblppkborangppemohons;
		this.tblppkpemohons = tblppkpemohons;
		this.tblppkhbgnfaraids = tblppkhbgnfaraids;
		this.tblppkborangapemohons = tblppkborangapemohons;
		this.tblppkborangaobs = tblppkborangaobs;
		this.tblppkobs = tblppkobs;
	}

	// Property accessors

	public Long getIdSaudara() {
		return this.idSaudara;
	}

	public void setIdSaudara(Long idSaudara) {
		this.idSaudara = idSaudara;
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

	public String getJantina() {
		return this.jantina;
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
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

	public Set getTblppkborangpobs() {
		return this.tblppkborangpobs;
	}

	public void setTblppkborangpobs(Set tblppkborangpobs) {
		this.tblppkborangpobs = tblppkborangpobs;
	}

	public Set getTblppkborangppemohons() {
		return this.tblppkborangppemohons;
	}

	public void setTblppkborangppemohons(Set tblppkborangppemohons) {
		this.tblppkborangppemohons = tblppkborangppemohons;
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

	public Set getTblppkborangapemohons() {
		return this.tblppkborangapemohons;
	}

	public void setTblppkborangapemohons(Set tblppkborangapemohons) {
		this.tblppkborangapemohons = tblppkborangapemohons;
	}

	public Set getTblppkborangaobs() {
		return this.tblppkborangaobs;
	}

	public void setTblppkborangaobs(Set tblppkborangaobs) {
		this.tblppkborangaobs = tblppkborangaobs;
	}

	public Set getTblppkobs() {
		return this.tblppkobs;
	}

	public void setTblppkobs(Set tblppkobs) {
		this.tblppkobs = tblppkobs;
	}

}