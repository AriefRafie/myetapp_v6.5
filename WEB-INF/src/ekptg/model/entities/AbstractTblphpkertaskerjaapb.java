package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpkertaskerjaapb entity provides the base persistence definition
 * of the Tblphpkertaskerjaapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpkertaskerjaapb implements
		java.io.Serializable {

	// Fields

	private Long idKertaskerjaapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private Long idMesyuarat;
	private Long idKertaskerja;
	private Long noVersi;
	private String nota;
	private String tajukKertas;
	private String tujuan;
	private String pengalaman;
	private String syor;
	private String ulasanJabatan;
	private String ulasanJawatankuasaKhas;
	private String perakuanPtpAtaskertas;
	private String perakuanPtpAtasmemo;
	private Date tarikhHantarKrtsrngksn;
	private String flagPerakuanKsu;
	private String perakuanKsu;
	private Date tarikhPerakuanKsu;
	private String noKertas;
	private String bilMesyuarat;
	private Date tarikhMesyuarat;
	private Date tarikhKeputusan;
	private String keputusan;
	private String ulasanKeputusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDokumen;

	// Constructors

	/** default constructor */
	public AbstractTblphpkertaskerjaapb() {
	}

	/** minimal constructor */
	public AbstractTblphpkertaskerjaapb(Long idKertaskerjaapb) {
		this.idKertaskerjaapb = idKertaskerjaapb;
	}

	/** full constructor */
	public AbstractTblphpkertaskerjaapb(Long idKertaskerjaapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			Long idMesyuarat, Long idKertaskerja, Long noVersi, String nota,
			String tajukKertas, String tujuan, String pengalaman, String syor,
			String ulasanJabatan, String ulasanJawatankuasaKhas,
			String perakuanPtpAtaskertas, String perakuanPtpAtasmemo,
			Date tarikhHantarKrtsrngksn, String flagPerakuanKsu,
			String perakuanKsu, Date tarikhPerakuanKsu, String noKertas,
			String bilMesyuarat, Date tarikhMesyuarat, Date tarikhKeputusan,
			String keputusan, String ulasanKeputusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idDokumen) {
		this.idKertaskerjaapb = idKertaskerjaapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.idMesyuarat = idMesyuarat;
		this.idKertaskerja = idKertaskerja;
		this.noVersi = noVersi;
		this.nota = nota;
		this.tajukKertas = tajukKertas;
		this.tujuan = tujuan;
		this.pengalaman = pengalaman;
		this.syor = syor;
		this.ulasanJabatan = ulasanJabatan;
		this.ulasanJawatankuasaKhas = ulasanJawatankuasaKhas;
		this.perakuanPtpAtaskertas = perakuanPtpAtaskertas;
		this.perakuanPtpAtasmemo = perakuanPtpAtasmemo;
		this.tarikhHantarKrtsrngksn = tarikhHantarKrtsrngksn;
		this.flagPerakuanKsu = flagPerakuanKsu;
		this.perakuanKsu = perakuanKsu;
		this.tarikhPerakuanKsu = tarikhPerakuanKsu;
		this.noKertas = noKertas;
		this.bilMesyuarat = bilMesyuarat;
		this.tarikhMesyuarat = tarikhMesyuarat;
		this.tarikhKeputusan = tarikhKeputusan;
		this.keputusan = keputusan;
		this.ulasanKeputusan = ulasanKeputusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDokumen = idDokumen;
	}

	// Property accessors

	public Long getIdKertaskerjaapb() {
		return this.idKertaskerjaapb;
	}

	public void setIdKertaskerjaapb(Long idKertaskerjaapb) {
		this.idKertaskerjaapb = idKertaskerjaapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

	public Long getIdKertaskerja() {
		return this.idKertaskerja;
	}

	public void setIdKertaskerja(Long idKertaskerja) {
		this.idKertaskerja = idKertaskerja;
	}

	public Long getNoVersi() {
		return this.noVersi;
	}

	public void setNoVersi(Long noVersi) {
		this.noVersi = noVersi;
	}

	public String getNota() {
		return this.nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getTajukKertas() {
		return this.tajukKertas;
	}

	public void setTajukKertas(String tajukKertas) {
		this.tajukKertas = tajukKertas;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getPengalaman() {
		return this.pengalaman;
	}

	public void setPengalaman(String pengalaman) {
		this.pengalaman = pengalaman;
	}

	public String getSyor() {
		return this.syor;
	}

	public void setSyor(String syor) {
		this.syor = syor;
	}

	public String getUlasanJabatan() {
		return this.ulasanJabatan;
	}

	public void setUlasanJabatan(String ulasanJabatan) {
		this.ulasanJabatan = ulasanJabatan;
	}

	public String getUlasanJawatankuasaKhas() {
		return this.ulasanJawatankuasaKhas;
	}

	public void setUlasanJawatankuasaKhas(String ulasanJawatankuasaKhas) {
		this.ulasanJawatankuasaKhas = ulasanJawatankuasaKhas;
	}

	public String getPerakuanPtpAtaskertas() {
		return this.perakuanPtpAtaskertas;
	}

	public void setPerakuanPtpAtaskertas(String perakuanPtpAtaskertas) {
		this.perakuanPtpAtaskertas = perakuanPtpAtaskertas;
	}

	public String getPerakuanPtpAtasmemo() {
		return this.perakuanPtpAtasmemo;
	}

	public void setPerakuanPtpAtasmemo(String perakuanPtpAtasmemo) {
		this.perakuanPtpAtasmemo = perakuanPtpAtasmemo;
	}

	public Date getTarikhHantarKrtsrngksn() {
		return this.tarikhHantarKrtsrngksn;
	}

	public void setTarikhHantarKrtsrngksn(Date tarikhHantarKrtsrngksn) {
		this.tarikhHantarKrtsrngksn = tarikhHantarKrtsrngksn;
	}

	public String getFlagPerakuanKsu() {
		return this.flagPerakuanKsu;
	}

	public void setFlagPerakuanKsu(String flagPerakuanKsu) {
		this.flagPerakuanKsu = flagPerakuanKsu;
	}

	public String getPerakuanKsu() {
		return this.perakuanKsu;
	}

	public void setPerakuanKsu(String perakuanKsu) {
		this.perakuanKsu = perakuanKsu;
	}

	public Date getTarikhPerakuanKsu() {
		return this.tarikhPerakuanKsu;
	}

	public void setTarikhPerakuanKsu(Date tarikhPerakuanKsu) {
		this.tarikhPerakuanKsu = tarikhPerakuanKsu;
	}

	public String getNoKertas() {
		return this.noKertas;
	}

	public void setNoKertas(String noKertas) {
		this.noKertas = noKertas;
	}

	public String getBilMesyuarat() {
		return this.bilMesyuarat;
	}

	public void setBilMesyuarat(String bilMesyuarat) {
		this.bilMesyuarat = bilMesyuarat;
	}

	public Date getTarikhMesyuarat() {
		return this.tarikhMesyuarat;
	}

	public void setTarikhMesyuarat(Date tarikhMesyuarat) {
		this.tarikhMesyuarat = tarikhMesyuarat;
	}

	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getUlasanKeputusan() {
		return this.ulasanKeputusan;
	}

	public void setUlasanKeputusan(String ulasanKeputusan) {
		this.ulasanKeputusan = ulasanKeputusan;
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

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}

}