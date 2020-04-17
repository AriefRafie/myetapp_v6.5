package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpkertaskerjapelepasan entity provides the base persistence
 * definition of the Tblphpkertaskerjapelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpkertaskerjapelepasan implements
		java.io.Serializable {

	// Fields

	private Long idKertaskerjapelepasan;
	private Tblphppermohonanpelepasan tblphppermohonanpelepasan;
	private Long idMesyuarat;
	private Long idKertaskerja;
	private Long noVersi;
	private String tujuan;
	private String perihalKemajuantanah;
	private String pihakPohonSerahbalik;
	private String laporanPenilaiTanah;
	private String ulasanKementerianTani;
	private String perakuanPtpAtasmemo;
	private String perakuanPtpAtaskertas;
	private String syor;
	private String ulasanJabatan;
	private String ulasanMenteriKewangan;
	private String perakuanKsu;
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
	private Set tblphpperenggankkplpsns = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpkertaskerjapelepasan() {
	}

	/** minimal constructor */
	public AbstractTblphpkertaskerjapelepasan(Long idKertaskerjapelepasan) {
		this.idKertaskerjapelepasan = idKertaskerjapelepasan;
	}

	/** full constructor */
	public AbstractTblphpkertaskerjapelepasan(Long idKertaskerjapelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idMesyuarat, Long idKertaskerja, Long noVersi, String tujuan,
			String perihalKemajuantanah, String pihakPohonSerahbalik,
			String laporanPenilaiTanah, String ulasanKementerianTani,
			String perakuanPtpAtasmemo, String perakuanPtpAtaskertas,
			String syor, String ulasanJabatan, String ulasanMenteriKewangan,
			String perakuanKsu, String noKertas, String bilMesyuarat,
			Date tarikhMesyuarat, Date tarikhKeputusan, String keputusan,
			String ulasanKeputusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDokumen,
			Set tblphpperenggankkplpsns) {
		this.idKertaskerjapelepasan = idKertaskerjapelepasan;
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
		this.idMesyuarat = idMesyuarat;
		this.idKertaskerja = idKertaskerja;
		this.noVersi = noVersi;
		this.tujuan = tujuan;
		this.perihalKemajuantanah = perihalKemajuantanah;
		this.pihakPohonSerahbalik = pihakPohonSerahbalik;
		this.laporanPenilaiTanah = laporanPenilaiTanah;
		this.ulasanKementerianTani = ulasanKementerianTani;
		this.perakuanPtpAtasmemo = perakuanPtpAtasmemo;
		this.perakuanPtpAtaskertas = perakuanPtpAtaskertas;
		this.syor = syor;
		this.ulasanJabatan = ulasanJabatan;
		this.ulasanMenteriKewangan = ulasanMenteriKewangan;
		this.perakuanKsu = perakuanKsu;
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
		this.tblphpperenggankkplpsns = tblphpperenggankkplpsns;
	}

	// Property accessors

	public Long getIdKertaskerjapelepasan() {
		return this.idKertaskerjapelepasan;
	}

	public void setIdKertaskerjapelepasan(Long idKertaskerjapelepasan) {
		this.idKertaskerjapelepasan = idKertaskerjapelepasan;
	}

	public Tblphppermohonanpelepasan getTblphppermohonanpelepasan() {
		return this.tblphppermohonanpelepasan;
	}

	public void setTblphppermohonanpelepasan(
			Tblphppermohonanpelepasan tblphppermohonanpelepasan) {
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
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

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getPerihalKemajuantanah() {
		return this.perihalKemajuantanah;
	}

	public void setPerihalKemajuantanah(String perihalKemajuantanah) {
		this.perihalKemajuantanah = perihalKemajuantanah;
	}

	public String getPihakPohonSerahbalik() {
		return this.pihakPohonSerahbalik;
	}

	public void setPihakPohonSerahbalik(String pihakPohonSerahbalik) {
		this.pihakPohonSerahbalik = pihakPohonSerahbalik;
	}

	public String getLaporanPenilaiTanah() {
		return this.laporanPenilaiTanah;
	}

	public void setLaporanPenilaiTanah(String laporanPenilaiTanah) {
		this.laporanPenilaiTanah = laporanPenilaiTanah;
	}

	public String getUlasanKementerianTani() {
		return this.ulasanKementerianTani;
	}

	public void setUlasanKementerianTani(String ulasanKementerianTani) {
		this.ulasanKementerianTani = ulasanKementerianTani;
	}

	public String getPerakuanPtpAtasmemo() {
		return this.perakuanPtpAtasmemo;
	}

	public void setPerakuanPtpAtasmemo(String perakuanPtpAtasmemo) {
		this.perakuanPtpAtasmemo = perakuanPtpAtasmemo;
	}

	public String getPerakuanPtpAtaskertas() {
		return this.perakuanPtpAtaskertas;
	}

	public void setPerakuanPtpAtaskertas(String perakuanPtpAtaskertas) {
		this.perakuanPtpAtaskertas = perakuanPtpAtaskertas;
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

	public String getUlasanMenteriKewangan() {
		return this.ulasanMenteriKewangan;
	}

	public void setUlasanMenteriKewangan(String ulasanMenteriKewangan) {
		this.ulasanMenteriKewangan = ulasanMenteriKewangan;
	}

	public String getPerakuanKsu() {
		return this.perakuanKsu;
	}

	public void setPerakuanKsu(String perakuanKsu) {
		this.perakuanKsu = perakuanKsu;
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

	public Set getTblphpperenggankkplpsns() {
		return this.tblphpperenggankkplpsns;
	}

	public void setTblphpperenggankkplpsns(Set tblphpperenggankkplpsns) {
		this.tblphpperenggankkplpsns = tblphpperenggankkplpsns;
	}

}