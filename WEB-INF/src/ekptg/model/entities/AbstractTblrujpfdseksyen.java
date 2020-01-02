package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujpfdseksyen entity provides the base persistence definition of
 * the Tblrujpfdseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpfdseksyen implements java.io.Serializable {

	// Fields

	private Long idSeksyen;
	private String kodSeksyen;
	private String namaSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpfdfails = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujpfdseksyen() {
	}

	/** minimal constructor */
	public AbstractTblrujpfdseksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	/** full constructor */
	public AbstractTblrujpfdseksyen(Long idSeksyen, String kodSeksyen,
			String namaSeksyen, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblpfdfails) {
		this.idSeksyen = idSeksyen;
		this.kodSeksyen = kodSeksyen;
		this.namaSeksyen = namaSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpfdfails = tblpfdfails;
	}

	// Property accessors

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public String getKodSeksyen() {
		return this.kodSeksyen;
	}

	public void setKodSeksyen(String kodSeksyen) {
		this.kodSeksyen = kodSeksyen;
	}

	public String getNamaSeksyen() {
		return this.namaSeksyen;
	}

	public void setNamaSeksyen(String namaSeksyen) {
		this.namaSeksyen = namaSeksyen;
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

	public Set getTblpfdfails() {
		return this.tblpfdfails;
	}

	public void setTblpfdfails(Set tblpfdfails) {
		this.tblpfdfails = tblpfdfails;
	}

}