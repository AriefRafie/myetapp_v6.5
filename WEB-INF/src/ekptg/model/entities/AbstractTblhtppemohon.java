package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppemohon entity provides the base persistence definition of the
 * Tblhtppemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppemohon implements java.io.Serializable {

	// Fields

	private Long idPemohon;
	private Tblhtppermohonan tblhtppermohonan;
	private String noPemohon;
	private String namaPemohon;
	private String alamatPemohon1;
	private String alamatPemohon2;
	private String alamatPemohon3;
	private String poskod;
	private Long idDaerah;
	private Long idNegeri;
	private String noTel;
	private String noFax;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppemohon() {
	}

	/** minimal constructor */
	public AbstractTblhtppemohon(Long idPemohon,
			Tblhtppermohonan tblhtppermohonan, Long idDaerah, Long idNegeri) {
		this.idPemohon = idPemohon;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtppemohon(Long idPemohon,
			Tblhtppermohonan tblhtppermohonan, String noPemohon,
			String namaPemohon, String alamatPemohon1, String alamatPemohon2,
			String alamatPemohon3, String poskod, Long idDaerah, Long idNegeri,
			String noTel, String noFax, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idPemohon = idPemohon;
		this.tblhtppermohonan = tblhtppermohonan;
		this.noPemohon = noPemohon;
		this.namaPemohon = namaPemohon;
		this.alamatPemohon1 = alamatPemohon1;
		this.alamatPemohon2 = alamatPemohon2;
		this.alamatPemohon3 = alamatPemohon3;
		this.poskod = poskod;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.noTel = noTel;
		this.noFax = noFax;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPemohon() {
		return this.idPemohon;
	}

	public void setIdPemohon(Long idPemohon) {
		this.idPemohon = idPemohon;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public String getNoPemohon() {
		return this.noPemohon;
	}

	public void setNoPemohon(String noPemohon) {
		this.noPemohon = noPemohon;
	}

	public String getNamaPemohon() {
		return this.namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public String getAlamatPemohon1() {
		return this.alamatPemohon1;
	}

	public void setAlamatPemohon1(String alamatPemohon1) {
		this.alamatPemohon1 = alamatPemohon1;
	}

	public String getAlamatPemohon2() {
		return this.alamatPemohon2;
	}

	public void setAlamatPemohon2(String alamatPemohon2) {
		this.alamatPemohon2 = alamatPemohon2;
	}

	public String getAlamatPemohon3() {
		return this.alamatPemohon3;
	}

	public void setAlamatPemohon3(String alamatPemohon3) {
		this.alamatPemohon3 = alamatPemohon3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
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