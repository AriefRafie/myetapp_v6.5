package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkbke entity provides the base persistence definition of the
 * Tblppkbke entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkbke implements java.io.Serializable {

	// Fields

	private Long idBke;
	private Tblrujppkjenisperintah tblrujppkjenisperintah;
	private Tblppkpermohonan tblppkpermohonan;
	private Tblrujppkunit tblrujppkunit;
	private Long idNegeri;
	private Long idDaerah;
	private Date tarikhMohon;
	private Long idNegeriunitpsk;
	private String alasan1;
	private String alasan2;
	private String alasan3;
	private String alasan4;
	private String alasan5;
	private String alasan6;
	private String alasan7;
	private String alasanLain;
	private String keputusanPegawai;
	private String keputusanKptgPtg;
	private String catatanPegawai;
	private Date tarikhLulus;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkbke() {
	}

	/** minimal constructor */
	public AbstractTblppkbke(Long idBke, Tblppkpermohonan tblppkpermohonan) {
		this.idBke = idBke;
		this.tblppkpermohonan = tblppkpermohonan;
	}

	/** full constructor */
	public AbstractTblppkbke(Long idBke,
			Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkpermohonan tblppkpermohonan, Tblrujppkunit tblrujppkunit,
			Long idNegeri, Long idDaerah, Date tarikhMohon,
			Long idNegeriunitpsk, String alasan1, String alasan2,
			String alasan3, String alasan4, String alasan5, String alasan6,
			String alasan7, String alasanLain, String keputusanPegawai,
			String keputusanKptgPtg, String catatanPegawai, Date tarikhLulus,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idBke = idBke;
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
		this.tblppkpermohonan = tblppkpermohonan;
		this.tblrujppkunit = tblrujppkunit;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.tarikhMohon = tarikhMohon;
		this.idNegeriunitpsk = idNegeriunitpsk;
		this.alasan1 = alasan1;
		this.alasan2 = alasan2;
		this.alasan3 = alasan3;
		this.alasan4 = alasan4;
		this.alasan5 = alasan5;
		this.alasan6 = alasan6;
		this.alasan7 = alasan7;
		this.alasanLain = alasanLain;
		this.keputusanPegawai = keputusanPegawai;
		this.keputusanKptgPtg = keputusanKptgPtg;
		this.catatanPegawai = catatanPegawai;
		this.tarikhLulus = tarikhLulus;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBke() {
		return this.idBke;
	}

	public void setIdBke(Long idBke) {
		this.idBke = idBke;
	}

	public Tblrujppkjenisperintah getTblrujppkjenisperintah() {
		return this.tblrujppkjenisperintah;
	}

	public void setTblrujppkjenisperintah(
			Tblrujppkjenisperintah tblrujppkjenisperintah) {
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
	}

	public Tblppkpermohonan getTblppkpermohonan() {
		return this.tblppkpermohonan;
	}

	public void setTblppkpermohonan(Tblppkpermohonan tblppkpermohonan) {
		this.tblppkpermohonan = tblppkpermohonan;
	}

	public Tblrujppkunit getTblrujppkunit() {
		return this.tblrujppkunit;
	}

	public void setTblrujppkunit(Tblrujppkunit tblrujppkunit) {
		this.tblrujppkunit = tblrujppkunit;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public Long getIdNegeriunitpsk() {
		return this.idNegeriunitpsk;
	}

	public void setIdNegeriunitpsk(Long idNegeriunitpsk) {
		this.idNegeriunitpsk = idNegeriunitpsk;
	}

	public String getAlasan1() {
		return this.alasan1;
	}

	public void setAlasan1(String alasan1) {
		this.alasan1 = alasan1;
	}

	public String getAlasan2() {
		return this.alasan2;
	}

	public void setAlasan2(String alasan2) {
		this.alasan2 = alasan2;
	}

	public String getAlasan3() {
		return this.alasan3;
	}

	public void setAlasan3(String alasan3) {
		this.alasan3 = alasan3;
	}

	public String getAlasan4() {
		return this.alasan4;
	}

	public void setAlasan4(String alasan4) {
		this.alasan4 = alasan4;
	}

	public String getAlasan5() {
		return this.alasan5;
	}

	public void setAlasan5(String alasan5) {
		this.alasan5 = alasan5;
	}

	public String getAlasan6() {
		return this.alasan6;
	}

	public void setAlasan6(String alasan6) {
		this.alasan6 = alasan6;
	}

	public String getAlasan7() {
		return this.alasan7;
	}

	public void setAlasan7(String alasan7) {
		this.alasan7 = alasan7;
	}

	public String getAlasanLain() {
		return this.alasanLain;
	}

	public void setAlasanLain(String alasanLain) {
		this.alasanLain = alasanLain;
	}

	public String getKeputusanPegawai() {
		return this.keputusanPegawai;
	}

	public void setKeputusanPegawai(String keputusanPegawai) {
		this.keputusanPegawai = keputusanPegawai;
	}

	public String getKeputusanKptgPtg() {
		return this.keputusanKptgPtg;
	}

	public void setKeputusanKptgPtg(String keputusanKptgPtg) {
		this.keputusanKptgPtg = keputusanKptgPtg;
	}

	public String getCatatanPegawai() {
		return this.catatanPegawai;
	}

	public void setCatatanPegawai(String catatanPegawai) {
		this.catatanPegawai = catatanPegawai;
	}

	public Date getTarikhLulus() {
		return this.tarikhLulus;
	}

	public void setTarikhLulus(Date tarikhLulus) {
		this.tarikhLulus = tarikhLulus;
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