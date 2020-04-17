package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpengarah entity provides the base persistence definition of the
 * Tblpengarah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpengarah implements java.io.Serializable {

	// Fields

	private Long idPengarah;
	private Long idPemaju;
	private Long idRujjenisopb;
	private String noOpb;
	private String nama;
	private Long idWarganegara;
	private Long idUrusan;
	private Long idSeksyen;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpengarah() {
	}

	/** minimal constructor */
	public AbstractTblpengarah(Long idPengarah, Long idPemaju,
			Long idRujjenisopb, Long idWarganegara, Long idUrusan,
			Long idSeksyen) {
		this.idPengarah = idPengarah;
		this.idPemaju = idPemaju;
		this.idRujjenisopb = idRujjenisopb;
		this.idWarganegara = idWarganegara;
		this.idUrusan = idUrusan;
		this.idSeksyen = idSeksyen;
	}

	/** full constructor */
	public AbstractTblpengarah(Long idPengarah, Long idPemaju,
			Long idRujjenisopb, String noOpb, String nama, Long idWarganegara,
			Long idUrusan, Long idSeksyen, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPengarah = idPengarah;
		this.idPemaju = idPemaju;
		this.idRujjenisopb = idRujjenisopb;
		this.noOpb = noOpb;
		this.nama = nama;
		this.idWarganegara = idWarganegara;
		this.idUrusan = idUrusan;
		this.idSeksyen = idSeksyen;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPengarah() {
		return this.idPengarah;
	}

	public void setIdPengarah(Long idPengarah) {
		this.idPengarah = idPengarah;
	}

	public Long getIdPemaju() {
		return this.idPemaju;
	}

	public void setIdPemaju(Long idPemaju) {
		this.idPemaju = idPemaju;
	}

	public Long getIdRujjenisopb() {
		return this.idRujjenisopb;
	}

	public void setIdRujjenisopb(Long idRujjenisopb) {
		this.idRujjenisopb = idRujjenisopb;
	}

	public String getNoOpb() {
		return this.noOpb;
	}

	public void setNoOpb(String noOpb) {
		this.noOpb = noOpb;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
	}

	public Long getIdUrusan() {
		return this.idUrusan;
	}

	public void setIdUrusan(Long idUrusan) {
		this.idUrusan = idUrusan;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
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