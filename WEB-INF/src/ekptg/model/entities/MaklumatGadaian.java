package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpmaklumatgadaian entity provides the base persistence definition
 * of the Tblhtpmaklumatgadaian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MaklumatGadaian implements
		java.io.Serializable {

	// Fields

	private Long idHtpmaklumatgadaian;
	private Long idPermohonan;
	private String noRujukanKjp;
	private String noRujukanLain;
	private Date tarikhAgihan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Date tarikhLepasgadai;
	private Long idFail;

	// Constructors

	/** default constructor */
	public MaklumatGadaian() {
	}

	/** minimal constructor */
	public MaklumatGadaian(Long idHtpmaklumatgadaian,
			Long idPermohonan, Long idFail) {
		this.idHtpmaklumatgadaian = idHtpmaklumatgadaian;
		this.idPermohonan = idPermohonan;
		this.idFail = idFail;
	}

	/** full constructor */
	public MaklumatGadaian(Long idHtpmaklumatgadaian,
			Long idPermohonan, String noRujukanKjp, String noRujukanLain,
			Date tarikhAgihan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Date tarikhLepasgadai,
			Long idFail) {
		this.idHtpmaklumatgadaian = idHtpmaklumatgadaian;
		this.idPermohonan = idPermohonan;
		this.noRujukanKjp = noRujukanKjp;
		this.noRujukanLain = noRujukanLain;
		this.tarikhAgihan = tarikhAgihan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tarikhLepasgadai = tarikhLepasgadai;
		this.idFail = idFail;
	}

	// Property accessors

	public Long getIdHtpmaklumatgadaian() {
		return this.idHtpmaklumatgadaian;
	}

	public void setIdHtpmaklumatgadaian(Long idHtpmaklumatgadaian) {
		this.idHtpmaklumatgadaian = idHtpmaklumatgadaian;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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

	public Date getTarikhLepasgadai() {
		return this.tarikhLepasgadai;
	}

	public void setTarikhLepasgadai(Date tarikhLepasgadai) {
		this.tarikhLepasgadai = tarikhLepasgadai;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

}