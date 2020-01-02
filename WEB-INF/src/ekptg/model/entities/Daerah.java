package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujdaerah entity provides the base persistence definition of the
 * Tblrujdaerah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Daerah implements java.io.Serializable {

	// Fields

	private Long idDaerah;
	private String kodDaerah;
	private String namaDaerah;
	private Long idNegeri;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public Daerah() {
	}

	/** minimal constructor */
	public Daerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	/** full constructor */
	public Daerah(Long idDaerah, String kodDaerah,
			String namaDaerah, Long idNegeri, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDaerah = idDaerah;
		this.kodDaerah = kodDaerah;
		this.namaDaerah = namaDaerah;
		this.idNegeri = idNegeri;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public String getKodDaerah() {
		return this.kodDaerah;
	}

	public void setKodDaerah(String kodDaerah) {
		this.kodDaerah = kodDaerah;
	}

	public String getNamaDaerah() {
		return this.namaDaerah;
	}

	public void setNamaDaerah(String namaDaerah) {
		this.namaDaerah = namaDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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

}