package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblonlineaduan entity provides the base persistence definition of the
 * Tblonlineaduan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblonlineaduan implements java.io.Serializable {

	// Fields

	private Long idAduan;
	private Long idPegawai;
	private Long idJenisaduan;
	private Long idSeksyen;
	private Long noAduanOnline;
	private String aduan;
	private String dirBuktiAduan;
	private Date tarikhAduan;
	private Date tarikhPengagihan;
	private Date tarikhPengagihanKpdpegawai;
	private String flagStatusAduan;
	private String tindakanSusulan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;
	private Set tblonlinepengaduans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblonlineaduan() {
	}

	/** minimal constructor */
	public AbstractTblonlineaduan(Long idAduan) {
		this.idAduan = idAduan;
	}

	/** full constructor */
	public AbstractTblonlineaduan(Long idAduan,
			Long idPegawai, Long idJenisaduan,
			Long idSeksyen, Long noAduanOnline, String aduan,
			String dirBuktiAduan, Date tarikhAduan, Date tarikhPengagihan,
			Date tarikhPengagihanKpdpegawai, String flagStatusAduan,
			String tindakanSusulan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb,
			Set tblonlinepengaduans) {
		this.idAduan = idAduan;
		this.idPegawai = idPegawai;
		this.idJenisaduan = idJenisaduan;
		this.idSeksyen = idSeksyen;
		this.noAduanOnline = noAduanOnline;
		this.aduan = aduan;
		this.dirBuktiAduan = dirBuktiAduan;
		this.tarikhAduan = tarikhAduan;
		this.tarikhPengagihan = tarikhPengagihan;
		this.tarikhPengagihanKpdpegawai = tarikhPengagihanKpdpegawai;
		this.flagStatusAduan = flagStatusAduan;
		this.tindakanSusulan = tindakanSusulan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
		this.tblonlinepengaduans = tblonlinepengaduans;
	}

	// Property accessors

	public Long getIdAduan() {
		return this.idAduan;
	}

	public void setIdAduan(Long idAduan) {
		this.idAduan = idAduan;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public Long getIdJenisaduan() {
		return this.idJenisaduan;
	}

	public void setIdJenisaduan(Long idJenisaduan) {
		this.idJenisaduan = idJenisaduan;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}

	public Long getNoAduanOnline() {
		return this.noAduanOnline;
	}

	public void setNoAduanOnline(Long noAduanOnline) {
		this.noAduanOnline = noAduanOnline;
	}

	public String getAduan() {
		return this.aduan;
	}

	public void setAduan(String aduan) {
		this.aduan = aduan;
	}

	public String getDirBuktiAduan() {
		return this.dirBuktiAduan;
	}

	public void setDirBuktiAduan(String dirBuktiAduan) {
		this.dirBuktiAduan = dirBuktiAduan;
	}

	public Date getTarikhAduan() {
		return this.tarikhAduan;
	}

	public void setTarikhAduan(Date tarikhAduan) {
		this.tarikhAduan = tarikhAduan;
	}

	public Date getTarikhPengagihan() {
		return this.tarikhPengagihan;
	}

	public void setTarikhPengagihan(Date tarikhPengagihan) {
		this.tarikhPengagihan = tarikhPengagihan;
	}

	public Date getTarikhPengagihanKpdpegawai() {
		return this.tarikhPengagihanKpdpegawai;
	}

	public void setTarikhPengagihanKpdpegawai(Date tarikhPengagihanKpdpegawai) {
		this.tarikhPengagihanKpdpegawai = tarikhPengagihanKpdpegawai;
	}

	public String getFlagStatusAduan() {
		return this.flagStatusAduan;
	}

	public void setFlagStatusAduan(String flagStatusAduan) {
		this.flagStatusAduan = flagStatusAduan;
	}

	public String getTindakanSusulan() {
		return this.tindakanSusulan;
	}

	public void setTindakanSusulan(String tindakanSusulan) {
		this.tindakanSusulan = tindakanSusulan;
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

	public Set getTblonlinepengaduans() {
		return this.tblonlinepengaduans;
	}

	public void setTblonlinepengaduans(Set tblonlinepengaduans) {
		this.tblonlinepengaduans = tblonlinepengaduans;
	}

}