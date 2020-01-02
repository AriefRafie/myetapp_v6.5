package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppkjenisha entity provides the base persistence definition of
 * the Tblrujppkjenisha entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppkjenisha implements java.io.Serializable {

	// Fields

	private Long idJenisha;
	private String kod;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangahas = new HashSet(0);
	private Set tblppkborangphas = new HashSet(0);
	private Set tblppkhas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppkjenisha() {
	}

	/** minimal constructor */
	public AbstractTblrujppkjenisha(Long idJenisha) {
		this.idJenisha = idJenisha;
	}

	/** full constructor */
	public AbstractTblrujppkjenisha(Long idJenisha, String kod,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangahas,
			Set tblppkborangphas, Set tblppkhas) {
		this.idJenisha = idJenisha;
		this.kod = kod;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangahas = tblppkborangahas;
		this.tblppkborangphas = tblppkborangphas;
		this.tblppkhas = tblppkhas;
	}

	// Property accessors

	public Long getIdJenisha() {
		return this.idJenisha;
	}

	public void setIdJenisha(Long idJenisha) {
		this.idJenisha = idJenisha;
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

	public Set getTblppkborangahas() {
		return this.tblppkborangahas;
	}

	public void setTblppkborangahas(Set tblppkborangahas) {
		this.tblppkborangahas = tblppkborangahas;
	}

	public Set getTblppkborangphas() {
		return this.tblppkborangphas;
	}

	public void setTblppkborangphas(Set tblppkborangphas) {
		this.tblppkborangphas = tblppkborangphas;
	}

	public Set getTblppkhas() {
		return this.tblppkhas;
	}

	public void setTblppkhas(Set tblppkhas) {
		this.tblppkhas = tblppkhas;
	}

}