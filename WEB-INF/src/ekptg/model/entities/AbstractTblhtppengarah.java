package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppengarah entity provides the base persistence definition of the
 * Tblhtppengarah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppengarah implements java.io.Serializable {

	// Fields

	private Long idPengarah;
	private Tblhtppemaju tblhtppemaju;
	private Long idRujjenisopb;
	private String noOpb;
	private String nama;
	private String idWarganegara;
	private Long idUrusan;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtppengarah() {
	}

	/** minimal constructor */
	public AbstractTblhtppengarah(Long idPengarah, Tblhtppemaju tblhtppemaju,
			Long idRujjenisopb, String idWarganegara, Long idUrusan,
			Long idMasuk) {
		this.idPengarah = idPengarah;
		this.tblhtppemaju = tblhtppemaju;
		this.idRujjenisopb = idRujjenisopb;
		this.idWarganegara = idWarganegara;
		this.idUrusan = idUrusan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtppengarah(Long idPengarah, Tblhtppemaju tblhtppemaju,
			Long idRujjenisopb, String noOpb, String nama,
			String idWarganegara, Long idUrusan, Long idMasuk, Date tarikhMasuk) {
		this.idPengarah = idPengarah;
		this.tblhtppemaju = tblhtppemaju;
		this.idRujjenisopb = idRujjenisopb;
		this.noOpb = noOpb;
		this.nama = nama;
		this.idWarganegara = idWarganegara;
		this.idUrusan = idUrusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public Long getIdPengarah() {
		return this.idPengarah;
	}

	public void setIdPengarah(Long idPengarah) {
		this.idPengarah = idPengarah;
	}

	public Tblhtppemaju getTblhtppemaju() {
		return this.tblhtppemaju;
	}

	public void setTblhtppemaju(Tblhtppemaju tblhtppemaju) {
		this.tblhtppemaju = tblhtppemaju;
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

	public String getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(String idWarganegara) {
		this.idWarganegara = idWarganegara;
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

}