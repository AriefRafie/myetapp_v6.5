package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppengarahpmhnpenyewaan entity provides the base persistence
 * definition of the Tblphppengarahpmhnpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppengarahpmhnpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idTblpengarahpmhnpenyewaan;
	private Tblphppemohonpenyewaan tblphppemohonpenyewaan;
	private String nama;
	private Long idJenispengenalan;
	private String noKp;
	private Long idWarganegara;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppengarahpmhnpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphppengarahpmhnpenyewaan(Long idTblpengarahpmhnpenyewaan) {
		this.idTblpengarahpmhnpenyewaan = idTblpengarahpmhnpenyewaan;
	}

	/** full constructor */
	public AbstractTblphppengarahpmhnpenyewaan(Long idTblpengarahpmhnpenyewaan,
			Tblphppemohonpenyewaan tblphppemohonpenyewaan, String nama,
			Long idJenispengenalan, String noKp, Long idWarganegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idTblpengarahpmhnpenyewaan = idTblpengarahpmhnpenyewaan;
		this.tblphppemohonpenyewaan = tblphppemohonpenyewaan;
		this.nama = nama;
		this.idJenispengenalan = idJenispengenalan;
		this.noKp = noKp;
		this.idWarganegara = idWarganegara;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdTblpengarahpmhnpenyewaan() {
		return this.idTblpengarahpmhnpenyewaan;
	}

	public void setIdTblpengarahpmhnpenyewaan(Long idTblpengarahpmhnpenyewaan) {
		this.idTblpengarahpmhnpenyewaan = idTblpengarahpmhnpenyewaan;
	}

	public Tblphppemohonpenyewaan getTblphppemohonpenyewaan() {
		return this.tblphppemohonpenyewaan;
	}

	public void setTblphppemohonpenyewaan(
			Tblphppemohonpenyewaan tblphppemohonpenyewaan) {
		this.tblphppemohonpenyewaan = tblphppemohonpenyewaan;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getIdJenispengenalan() {
		return this.idJenispengenalan;
	}

	public void setIdJenispengenalan(Long idJenispengenalan) {
		this.idJenispengenalan = idJenispengenalan;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public Long getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
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