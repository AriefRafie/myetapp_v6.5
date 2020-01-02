package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphppegawailaporantanah entity provides the base persistence
 * definition of the Tblphppegawailaporantanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphppegawailaporantanah implements
		java.io.Serializable {

	// Fields

	private Long idPegawailaporantanah;
	private Tblphplawatantapak tblphplawatantapak;
	private String namaPegawai;
	private String jawatan;
	private Long idNegeripegawai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphppegawailaporantanah() {
	}

	/** minimal constructor */
	public AbstractTblphppegawailaporantanah(Long idPegawailaporantanah) {
		this.idPegawailaporantanah = idPegawailaporantanah;
	}

	/** full constructor */
	public AbstractTblphppegawailaporantanah(Long idPegawailaporantanah,
			Tblphplawatantapak tblphplawatantapak, String namaPegawai,
			String jawatan, Long idNegeripegawai, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idPegawailaporantanah = idPegawailaporantanah;
		this.tblphplawatantapak = tblphplawatantapak;
		this.namaPegawai = namaPegawai;
		this.jawatan = jawatan;
		this.idNegeripegawai = idNegeripegawai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPegawailaporantanah() {
		return this.idPegawailaporantanah;
	}

	public void setIdPegawailaporantanah(Long idPegawailaporantanah) {
		this.idPegawailaporantanah = idPegawailaporantanah;
	}

	public Tblphplawatantapak getTblphplawatantapak() {
		return this.tblphplawatantapak;
	}

	public void setTblphplawatantapak(Tblphplawatantapak tblphplawatantapak) {
		this.tblphplawatantapak = tblphplawatantapak;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getJawatan() {
		return this.jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public Long getIdNegeripegawai() {
		return this.idNegeripegawai;
	}

	public void setIdNegeripegawai(Long idNegeripegawai) {
		this.idNegeripegawai = idNegeripegawai;
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