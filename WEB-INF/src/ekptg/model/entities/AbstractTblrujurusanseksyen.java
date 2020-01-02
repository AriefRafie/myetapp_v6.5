package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujurusanseksyen entity provides the base persistence definition
 * of the Tblrujurusanseksyen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujurusanseksyen implements
		java.io.Serializable {

	// Fields

	private Long idUrusanseksyen;
	private Long idUrusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujurusanseksyen() {
	}

	/** minimal constructor */
	public AbstractTblrujurusanseksyen(Long idUrusanseksyen, Long idUrusan) {
		this.idUrusanseksyen = idUrusanseksyen;
		this.idUrusan = idUrusan;
	}

	/** full constructor */
	public AbstractTblrujurusanseksyen(Long idUrusanseksyen, Long idUrusan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idUrusanseksyen = idUrusanseksyen;
		this.idUrusan = idUrusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdUrusanseksyen() {
		return this.idUrusanseksyen;
	}

	public void setIdUrusanseksyen(Long idUrusanseksyen) {
		this.idUrusanseksyen = idUrusanseksyen;
	}

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
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