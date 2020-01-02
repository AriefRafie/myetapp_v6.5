package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtppemaju entity provides the base persistence definition of the
 * Tblhtppemaju entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppemaju implements java.io.Serializable {

	// Fields

	private Long idPemaju;
	private Tblhtppermohonan tblhtppermohonan;
	private Long idRujjenisopb;
	private String noOpb;
	private String noRujukanPemaju;
	private String namaPemaju;
	private String alamatPemaju1;
	private String alamatPemaju2;
	private String alamatPemaju3;
	private String poskodPemaju;
	private Long idDaerah;
	private Long idNegeri;
	private String noTel;
	private String noFax;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtppengarahs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtppemaju() {
	}

	/** minimal constructor */
	public AbstractTblhtppemaju(Long idPemaju,
			Tblhtppermohonan tblhtppermohonan, Long idRujjenisopb,
			Long idDaerah, Long idNegeri) {
		this.idPemaju = idPemaju;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idRujjenisopb = idRujjenisopb;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
	}

	/** full constructor */
	public AbstractTblhtppemaju(Long idPemaju,
			Tblhtppermohonan tblhtppermohonan, Long idRujjenisopb,
			String noOpb, String noRujukanPemaju, String namaPemaju,
			String alamatPemaju1, String alamatPemaju2, String alamatPemaju3,
			String poskodPemaju, Long idDaerah, Long idNegeri, String noTel,
			String noFax, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblhtppengarahs) {
		this.idPemaju = idPemaju;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idRujjenisopb = idRujjenisopb;
		this.noOpb = noOpb;
		this.noRujukanPemaju = noRujukanPemaju;
		this.namaPemaju = namaPemaju;
		this.alamatPemaju1 = alamatPemaju1;
		this.alamatPemaju2 = alamatPemaju2;
		this.alamatPemaju3 = alamatPemaju3;
		this.poskodPemaju = poskodPemaju;
		this.idDaerah = idDaerah;
		this.idNegeri = idNegeri;
		this.noTel = noTel;
		this.noFax = noFax;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtppengarahs = tblhtppengarahs;
	}

	// Property accessors

	public Long getIdPemaju() {
		return this.idPemaju;
	}

	public void setIdPemaju(Long idPemaju) {
		this.idPemaju = idPemaju;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
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

	public String getNoRujukanPemaju() {
		return this.noRujukanPemaju;
	}

	public void setNoRujukanPemaju(String noRujukanPemaju) {
		this.noRujukanPemaju = noRujukanPemaju;
	}

	public String getNamaPemaju() {
		return this.namaPemaju;
	}

	public void setNamaPemaju(String namaPemaju) {
		this.namaPemaju = namaPemaju;
	}

	public String getAlamatPemaju1() {
		return this.alamatPemaju1;
	}

	public void setAlamatPemaju1(String alamatPemaju1) {
		this.alamatPemaju1 = alamatPemaju1;
	}

	public String getAlamatPemaju2() {
		return this.alamatPemaju2;
	}

	public void setAlamatPemaju2(String alamatPemaju2) {
		this.alamatPemaju2 = alamatPemaju2;
	}

	public String getAlamatPemaju3() {
		return this.alamatPemaju3;
	}

	public void setAlamatPemaju3(String alamatPemaju3) {
		this.alamatPemaju3 = alamatPemaju3;
	}

	public String getPoskodPemaju() {
		return this.poskodPemaju;
	}

	public void setPoskodPemaju(String poskodPemaju) {
		this.poskodPemaju = poskodPemaju;
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

	public Set getTblhtppengarahs() {
		return this.tblhtppengarahs;
	}

	public void setTblhtppengarahs(Set tblhtppengarahs) {
		this.tblhtppengarahs = tblhtppengarahs;
	}

}