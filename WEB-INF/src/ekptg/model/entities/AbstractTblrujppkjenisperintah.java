package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujppkjenisperintah entity provides the base persistence
 * definition of the Tblrujppkjenisperintah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujppkjenisperintah implements
		java.io.Serializable {

	// Fields

	private Long idJenisperintah;
	private String kod;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkrayuans = new HashSet(0);
	private Set tblppkbkes = new HashSet(0);
	private Set tblppkperintahs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujppkjenisperintah() {
	}

	/** minimal constructor */
	public AbstractTblrujppkjenisperintah(Long idJenisperintah) {
		this.idJenisperintah = idJenisperintah;
	}

	/** full constructor */
	public AbstractTblrujppkjenisperintah(Long idJenisperintah, String kod,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkrayuans,
			Set tblppkbkes, Set tblppkperintahs) {
		this.idJenisperintah = idJenisperintah;
		this.kod = kod;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkrayuans = tblppkrayuans;
		this.tblppkbkes = tblppkbkes;
		this.tblppkperintahs = tblppkperintahs;
	}

	// Property accessors

	public Long getIdJenisperintah() {
		return this.idJenisperintah;
	}

	public void setIdJenisperintah(Long idJenisperintah) {
		this.idJenisperintah = idJenisperintah;
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

	public Set getTblppkrayuans() {
		return this.tblppkrayuans;
	}

	public void setTblppkrayuans(Set tblppkrayuans) {
		this.tblppkrayuans = tblppkrayuans;
	}

	public Set getTblppkbkes() {
		return this.tblppkbkes;
	}

	public void setTblppkbkes(Set tblppkbkes) {
		this.tblppkbkes = tblppkbkes;
	}

	public Set getTblppkperintahs() {
		return this.tblppkperintahs;
	}

	public void setTblppkperintahs(Set tblppkperintahs) {
		this.tblppkperintahs = tblppkperintahs;
	}

}