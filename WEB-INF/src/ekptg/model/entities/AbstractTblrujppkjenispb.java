package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppkjenispb entity provides the base persistence definition of
 * the Tblrujppkjenispb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppkjenispb implements java.io.Serializable {

	// Fields

	private Long idJenispb;
	private String kod;
	private String keterangan;
	private String jenisDaftarPb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkobs = new HashSet(0);
	private Set tblppkborangphtas = new HashSet(0);
	private Set tblppkborangaobs = new HashSet(0);
	private Set tblppkborangpobs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppkjenispb() {
	}

	/** minimal constructor */
	public AbstractTblrujppkjenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
	}

	/** full constructor */
	public AbstractTblrujppkjenispb(Long idJenispb, String kod,
			String keterangan, String jenisDaftarPb, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkobs, Set tblppkborangphtas, Set tblppkborangaobs,
			Set tblppkborangpobs) {
		this.idJenispb = idJenispb;
		this.kod = kod;
		this.keterangan = keterangan;
		this.jenisDaftarPb = jenisDaftarPb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkobs = tblppkobs;
		this.tblppkborangphtas = tblppkborangphtas;
		this.tblppkborangaobs = tblppkborangaobs;
		this.tblppkborangpobs = tblppkborangpobs;
	}

	// Property accessors

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
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

	public String getJenisDaftarPb() {
		return this.jenisDaftarPb;
	}

	public void setJenisDaftarPb(String jenisDaftarPb) {
		this.jenisDaftarPb = jenisDaftarPb;
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

	public Set getTblppkobs() {
		return this.tblppkobs;
	}

	public void setTblppkobs(Set tblppkobs) {
		this.tblppkobs = tblppkobs;
	}

	public Set getTblppkborangphtas() {
		return this.tblppkborangphtas;
	}

	public void setTblppkborangphtas(Set tblppkborangphtas) {
		this.tblppkborangphtas = tblppkborangphtas;
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

}