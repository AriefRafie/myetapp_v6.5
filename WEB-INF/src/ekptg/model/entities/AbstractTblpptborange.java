package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborange entity provides the base persistence definition of the
 * Tblpptborange entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborange implements java.io.Serializable {

	// Fields

	private Long idBorange;
	private Date tarikhBorange;
	private Date tarikhSiasatan;
	private String masaSiasatan;
	private String tempatSiasatan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Date tarikhAkhirTampal;
	private String flagAktif;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborange() {
	}

	/** full constructor */
	public AbstractTblpptborange(Date tarikhBorange, Date tarikhSiasatan,
			String masaSiasatan, String tempatSiasatan, String alamat1,
			String alamat2, String alamat3, String poskod, Long idNegeri,
			Date tarikhCetak, Date tarikhCetakSemula, Date tarikhAkhirTampal,
			String flagAktif, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.tarikhBorange = tarikhBorange;
		this.tarikhSiasatan = tarikhSiasatan;
		this.masaSiasatan = masaSiasatan;
		this.tempatSiasatan = tempatSiasatan;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.tarikhAkhirTampal = tarikhAkhirTampal;
		this.flagAktif = flagAktif;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorange() {
		return this.idBorange;
	}

	public void setIdBorange(Long idBorange) {
		this.idBorange = idBorange;
	}

	public Date getTarikhBorange() {
		return this.tarikhBorange;
	}

	public void setTarikhBorange(Date tarikhBorange) {
		this.tarikhBorange = tarikhBorange;
	}

	public Date getTarikhSiasatan() {
		return this.tarikhSiasatan;
	}

	public void setTarikhSiasatan(Date tarikhSiasatan) {
		this.tarikhSiasatan = tarikhSiasatan;
	}

	public String getMasaSiasatan() {
		return this.masaSiasatan;
	}

	public void setMasaSiasatan(String masaSiasatan) {
		this.masaSiasatan = masaSiasatan;
	}

	public String getTempatSiasatan() {
		return this.tempatSiasatan;
	}

	public void setTempatSiasatan(String tempatSiasatan) {
		this.tempatSiasatan = tempatSiasatan;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Date getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Date tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
	}

	public Date getTarikhAkhirTampal() {
		return this.tarikhAkhirTampal;
	}

	public void setTarikhAkhirTampal(Date tarikhAkhirTampal) {
		this.tarikhAkhirTampal = tarikhAkhirTampal;
	}

	public String getFlagAktif() {
		return this.flagAktif;
	}

	public void setFlagAktif(String flagAktif) {
		this.flagAktif = flagAktif;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}