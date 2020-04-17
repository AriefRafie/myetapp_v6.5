package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperjanjianswasta entity provides the base persistence
 * definition of the Tblhtpperjanjianswasta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjianswasta implements
		java.io.Serializable {

	// Fields

	private Long idPerjanjianswasta;
	private Tblhtppermohonan tblhtppermohonan;
	private Long idPerjanjian;
	private Date tarikhKeputusan;
	private Double nilaiTanah;
	private Double nilaianProjek;
	private String caraLaksanafee;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjianswasta() {
	}

	/** minimal constructor */
	public AbstractTblhtpperjanjianswasta(Long idPerjanjianswasta,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian) {
		this.idPerjanjianswasta = idPerjanjianswasta;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idPerjanjian = idPerjanjian;
	}

	/** full constructor */
	public AbstractTblhtpperjanjianswasta(Long idPerjanjianswasta,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian,
			Date tarikhKeputusan, Double nilaiTanah, Double nilaianProjek,
			String caraLaksanafee, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPerjanjianswasta = idPerjanjianswasta;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idPerjanjian = idPerjanjian;
		this.tarikhKeputusan = tarikhKeputusan;
		this.nilaiTanah = nilaiTanah;
		this.nilaianProjek = nilaianProjek;
		this.caraLaksanafee = caraLaksanafee;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerjanjianswasta() {
		return this.idPerjanjianswasta;
	}

	public void setIdPerjanjianswasta(Long idPerjanjianswasta) {
		this.idPerjanjianswasta = idPerjanjianswasta;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Long getIdPerjanjian() {
		return this.idPerjanjian;
	}

	public void setIdPerjanjian(Long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}

	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public Double getNilaiTanah() {
		return this.nilaiTanah;
	}

	public void setNilaiTanah(Double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}

	public Double getNilaianProjek() {
		return this.nilaianProjek;
	}

	public void setNilaianProjek(Double nilaianProjek) {
		this.nilaianProjek = nilaianProjek;
	}

	public String getCaraLaksanafee() {
		return this.caraLaksanafee;
	}

	public void setCaraLaksanafee(String caraLaksanafee) {
		this.caraLaksanafee = caraLaksanafee;
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