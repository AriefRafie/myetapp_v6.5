package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppengarahpmhnlesenapb entity provides the base persistence
 * definition of the Tblphppengarahpmhnlesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppengarahpmhnlesenapb implements
		java.io.Serializable {

	// Fields

	private Long idTblpengarahpmhnlesenapb;
	private Tblphppemohonlesenapb tblphppemohonlesenapb;
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
	public AbstractTblphppengarahpmhnlesenapb() {
	}

	/** minimal constructor */
	public AbstractTblphppengarahpmhnlesenapb(Long idTblpengarahpmhnlesenapb) {
		this.idTblpengarahpmhnlesenapb = idTblpengarahpmhnlesenapb;
	}

	/** full constructor */
	public AbstractTblphppengarahpmhnlesenapb(Long idTblpengarahpmhnlesenapb,
			Tblphppemohonlesenapb tblphppemohonlesenapb, String nama,
			Long idJenispengenalan, String noKp, Long idWarganegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idTblpengarahpmhnlesenapb = idTblpengarahpmhnlesenapb;
		this.tblphppemohonlesenapb = tblphppemohonlesenapb;
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

	public Long getIdTblpengarahpmhnlesenapb() {
		return this.idTblpengarahpmhnlesenapb;
	}

	public void setIdTblpengarahpmhnlesenapb(Long idTblpengarahpmhnlesenapb) {
		this.idTblpengarahpmhnlesenapb = idTblpengarahpmhnlesenapb;
	}

	public Tblphppemohonlesenapb getTblphppemohonlesenapb() {
		return this.tblphppemohonlesenapb;
	}

	public void setTblphppemohonlesenapb(
			Tblphppemohonlesenapb tblphppemohonlesenapb) {
		this.tblphppemohonlesenapb = tblphppemohonlesenapb;
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