package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpermohonan entity provides the base persistence definition of the
 * Tblpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpermohonan implements java.io.Serializable {

	// Fields

	private Long idPermohonan;
	private Long idFail;
	private Long idJkptg;
	private String noPermohonan;
	private String noPerserahan;
	private Date tarikhSurat;
	private Date tarikhTerima;
	private String tujuan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpermohonan() {
	}

	/** minimal constructor */
	public AbstractTblpermohonan(Long idPermohonan, Long idFail, Long idJkptg) {
		this.idPermohonan = idPermohonan;
		this.idFail = idFail;
		this.idJkptg = idJkptg;
	}

	/** full constructor */
	public AbstractTblpermohonan(Long idPermohonan, Long idFail, Long idJkptg,
			String noPermohonan, String noPerserahan, Date tarikhSurat,
			Date tarikhTerima, String tujuan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPermohonan = idPermohonan;
		this.idFail = idFail;
		this.idJkptg = idJkptg;
		this.noPermohonan = noPermohonan;
		this.noPerserahan = noPerserahan;
		this.tarikhSurat = tarikhSurat;
		this.tarikhTerima = tarikhTerima;
		this.tujuan = tujuan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Long getIdJkptg() {
		return this.idJkptg;
	}

	public void setIdJkptg(Long idJkptg) {
		this.idJkptg = idJkptg;
	}

	public String getNoPermohonan() {
		return this.noPermohonan;
	}

	public void setNoPermohonan(String noPermohonan) {
		this.noPermohonan = noPermohonan;
	}

	public String getNoPerserahan() {
		return this.noPerserahan;
	}

	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
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