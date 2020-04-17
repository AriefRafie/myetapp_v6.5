package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpversikoordinat entity provides the base persistence definition
 * of the Tblphpversikoordinat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpversikoordinat implements
		java.io.Serializable {

	// Fields

	private Long idVersikoordinat;
	private Tblphpjadualkedualesenapb tblphpjadualkedualesenapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private Long noVersi;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphpkoordinatpermohonans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpversikoordinat() {
	}

	/** minimal constructor */
	public AbstractTblphpversikoordinat(Long idVersikoordinat) {
		this.idVersikoordinat = idVersikoordinat;
	}

	/** full constructor */
	public AbstractTblphpversikoordinat(Long idVersikoordinat,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long noVersi,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpkoordinatpermohonans) {
		this.idVersikoordinat = idVersikoordinat;
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.noVersi = noVersi;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphpkoordinatpermohonans = tblphpkoordinatpermohonans;
	}

	// Property accessors

	public Long getIdVersikoordinat() {
		return this.idVersikoordinat;
	}

	public void setIdVersikoordinat(Long idVersikoordinat) {
		this.idVersikoordinat = idVersikoordinat;
	}

	public Tblphpjadualkedualesenapb getTblphpjadualkedualesenapb() {
		return this.tblphpjadualkedualesenapb;
	}

	public void setTblphpjadualkedualesenapb(
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb) {
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public Long getNoVersi() {
		return this.noVersi;
	}

	public void setNoVersi(Long noVersi) {
		this.noVersi = noVersi;
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

	public Set getTblphpkoordinatpermohonans() {
		return this.tblphpkoordinatpermohonans;
	}

	public void setTblphpkoordinatpermohonans(Set tblphpkoordinatpermohonans) {
		this.tblphpkoordinatpermohonans = tblphpkoordinatpermohonans;
	}

}