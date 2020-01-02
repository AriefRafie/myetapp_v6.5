package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblSeksyen entity provides the base persistence definition of the
 * Tblrujmukim entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Seksyen implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2265904538945619648L;
	// Fields
	private Long idSek;
	private String kodSek;
	private String namaSek;
	private Long idMukim;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors
	/** default constructor */
	public Seksyen() {
	}
	/** minimal constructor */
	public Seksyen(Long idSek, Long idMukim) {
		this.idSek = idSek;
		this.idMukim = idMukim;
	}
	/** full constructor */
	public Seksyen(Long idSek, String kodSek, String namaSek,
		Long idMukim, Long idMasuk, Date tarikhMasuk,
		Long idKemaskini, Date tarikhKemaskini) {
		this.idSek = idSek;
		this.kodSek = kodSek;
		this.namaSek = namaSek;
		this.idMukim = idMukim;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		
	}
	// Property accessors
	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public String getKodSeksyen() {
		return this.kodSek;
	}

	public void setKodSeksyen(String kodSek) {
		this.kodSek = kodSek;
	}

	public String getNamaSeksyen() {
		return this.namaSek;
	}

	public void setNamaSeksyen(String namaSek) {
		this.namaSek = namaSek;
	}

	public Long getIdSeksyen() {
		return this.idSek;
	}

	public void setIdSeksyen(Long idSek) {
		this.idSek = idSek;
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