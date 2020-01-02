package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujdaerahpenggawa entity provides the base persistence definition of the
 * Tblrujdaerahpenggawa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public abstract class AbstractTblrujdaerahpenggawa implements java.io.Serializable {

	// Fields

	private Long idDaerahPenggawa;
	private String kodDaerahPenggawa;
	private String namaDaerahPenggawa;
	private Long idDaerah;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujdaerahpenggawa() {
	}

	/** minimal constructor */
	public AbstractTblrujdaerahpenggawa(Long idDaerahPenggawa) {
		this.idDaerahPenggawa = idDaerahPenggawa;
	}

	/** full constructor */
	public AbstractTblrujdaerahpenggawa(Long idDaerahPenggawa, String kodDaerahPenggawa,
			String namaDaerahPenggawa, Long idDaerah, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDaerahPenggawa = idDaerah;
		this.kodDaerahPenggawa = kodDaerahPenggawa;
		this.namaDaerahPenggawa = namaDaerahPenggawa;
		this.idDaerah = idDaerah;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDaerahPenggawa() {
		return this.idDaerahPenggawa;
	}

	public void setIdDaerahPenggawa(Long idDaerahPenggawa) {
		this.idDaerahPenggawa = idDaerahPenggawa;
	}

	public String getKodDaerahPenggawa() {
		return this.kodDaerahPenggawa;
	}

	public void setKodDaerahPenggawa(String kodDaerahPenggawa) {
		this.kodDaerahPenggawa = kodDaerahPenggawa;
	}

	public String getNamaDaerahPenggawa() {
		return this.namaDaerahPenggawa;
	}

	public void setNamaDaerahPenggawa(String namaDaerahPenggawa) {
		this.namaDaerahPenggawa = namaDaerahPenggawa;
	}

	public Long getIdNegeri() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
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