package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblsemakanhantar entity provides the base persistence definition of
 * the Tblsemakanhantar entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblsemakanhantar implements java.io.Serializable {

	// Fields

	private Long idSemakanhantar;
	private Long idSemakansenarai;
	private Long idPermohonan;
	private String pemohon;
	private String pentadbir;
	private String status;
	private String catatan;
	private Date tarikhPelbagai;
	private Long idDokumen;
	private String flagAda;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblsemakanhantar() {
	}

	/** minimal constructor */
	public AbstractTblsemakanhantar(Long idSemakansenarai, Long idPermohonan) {
		this.idSemakansenarai = idSemakansenarai;
		this.idPermohonan = idPermohonan;
	}

	/** full constructor */
	public AbstractTblsemakanhantar(Long idSemakansenarai, Long idPermohonan,
			String pemohon, String pentadbir, String status, String catatan,
			Date tarikhPelbagai, Long idDokumen, String flagAda, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idSemakansenarai = idSemakansenarai;
		this.idPermohonan = idPermohonan;
		this.pemohon = pemohon;
		this.pentadbir = pentadbir;
		this.status = status;
		this.catatan = catatan;
		this.tarikhPelbagai = tarikhPelbagai;
		this.idDokumen = idDokumen;
		this.flagAda = flagAda;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdSemakanhantar() {
		return this.idSemakanhantar;
	}

	public void setIdSemakanhantar(Long idSemakanhantar) {
		this.idSemakanhantar = idSemakanhantar;
	}

	public Long getIdSemakansenarai() {
		return this.idSemakansenarai;
	}

	public void setIdSemakansenarai(Long idSemakansenarai) {
		this.idSemakansenarai = idSemakansenarai;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public String getPemohon() {
		return this.pemohon;
	}

	public void setPemohon(String pemohon) {
		this.pemohon = pemohon;
	}

	public String getPentadbir() {
		return this.pentadbir;
	}

	public void setPentadbir(String pentadbir) {
		this.pentadbir = pentadbir;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Date getTarikhPelbagai() {
		return this.tarikhPelbagai;
	}

	public void setTarikhPelbagai(Date tarikhPelbagai) {
		this.tarikhPelbagai = tarikhPelbagai;
	}

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public String getFlagAda() {
		return this.flagAda;
	}

	public void setFlagAda(String flagAda) {
		this.flagAda = flagAda;
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