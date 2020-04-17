package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpmaklumatpakarapb entity provides the base persistence
 * definition of the Tblphpmaklumatpakarapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpmaklumatpakarapb implements
		java.io.Serializable {

	// Fields

	private Long idMaklumatpakarapb;
	private Tblphppemohonlesenapb tblphppemohonlesenapb;
	private String nama;
	private String kelayakan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpmaklumatpakarapb() {
	}

	/** minimal constructor */
	public AbstractTblphpmaklumatpakarapb(Long idMaklumatpakarapb) {
		this.idMaklumatpakarapb = idMaklumatpakarapb;
	}

	/** full constructor */
	public AbstractTblphpmaklumatpakarapb(Long idMaklumatpakarapb,
			Tblphppemohonlesenapb tblphppemohonlesenapb, String nama,
			String kelayakan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idMaklumatpakarapb = idMaklumatpakarapb;
		this.tblphppemohonlesenapb = tblphppemohonlesenapb;
		this.nama = nama;
		this.kelayakan = kelayakan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdMaklumatpakarapb() {
		return this.idMaklumatpakarapb;
	}

	public void setIdMaklumatpakarapb(Long idMaklumatpakarapb) {
		this.idMaklumatpakarapb = idMaklumatpakarapb;
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

	public String getKelayakan() {
		return this.kelayakan;
	}

	public void setKelayakan(String kelayakan) {
		this.kelayakan = kelayakan;
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