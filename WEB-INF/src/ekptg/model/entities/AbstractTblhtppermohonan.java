package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppermohonan entity provides the base persistence definition of
 * the Tblhtppermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppermohonan implements java.io.Serializable {

	// Fields

	private Long idHtppermohonan;
	private Long idPermohonan;
	private Long idAgensi;
	private String idJenistanah;
	private Long idPegawai;
	private String noRujukanKjp;
	private String noRujukanLain;
	private Date tarikhAgihan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppermohonan() {
	}

	/** minimal constructor */
	public AbstractTblhtppermohonan(Long idHtppermohonan, Long idPegawai) {
		this.idHtppermohonan = idHtppermohonan;
		this.idPegawai = idPegawai;
	}

	/** full constructor */
	public AbstractTblhtppermohonan(Long idHtppermohonan, Long idPermohonan,
			Long idAgensi, String idJenistanah, Long idPegawai,
			String noRujukanKjp, String noRujukanLain, Date tarikhAgihan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idHtppermohonan = idHtppermohonan;
		this.idPermohonan = idPermohonan;
		this.idAgensi = idAgensi;
		this.idJenistanah = idJenistanah;
		this.idPegawai = idPegawai;
		this.noRujukanKjp = noRujukanKjp;
		this.noRujukanLain = noRujukanLain;
		this.tarikhAgihan = tarikhAgihan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHtppermohonan() {
		return this.idHtppermohonan;
	}

	public void setIdHtppermohonan(Long idHtppermohonan) {
		this.idHtppermohonan = idHtppermohonan;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public String getIdJenistanah() {
		return this.idJenistanah;
	}

	public void setIdJenistanah(String idJenistanah) {
		this.idJenistanah = idJenistanah;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public String getNoRujukanKjp() {
		return this.noRujukanKjp;
	}

	public void setNoRujukanKjp(String noRujukanKjp) {
		this.noRujukanKjp = noRujukanKjp;
	}

	public String getNoRujukanLain() {
		return this.noRujukanLain;
	}

	public void setNoRujukanLain(String noRujukanLain) {
		this.noRujukanLain = noRujukanLain;
	}

	public Date getTarikhAgihan() {
		return this.tarikhAgihan;
	}

	public void setTarikhAgihan(Date tarikhAgihan) {
		this.tarikhAgihan = tarikhAgihan;
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