package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpkertaskerjapenyewaan entity provides the base persistence
 * definition of the Tblphpkertaskerjapenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpkertaskerjapenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idKertaskerjapenyewaan;
	private Tblphppermohonanpenyewaan tblphppermohonanpenyewaan;
	private Long idMesyuarat;
	private Long idKertaskerja;
	private Long noVersi;
	private String tajukKertas;
	private String tujuan;
	private String laporanTanah;
	private String laporanPenilaiTanah;
	private String ulasanKjp;
	private String ulasanUrusetia;
	private String syorUrusetia;
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
	private Set tblphpperenggankkpenyewaans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpkertaskerjapenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphpkertaskerjapenyewaan(Long idKertaskerjapenyewaan) {
		this.idKertaskerjapenyewaan = idKertaskerjapenyewaan;
	}

	/** full constructor */
	public AbstractTblphpkertaskerjapenyewaan(Long idKertaskerjapenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan,
			Long idMesyuarat, Long idKertaskerja, Long noVersi,
			String tajukKertas, String tujuan, String laporanTanah,
			String laporanPenilaiTanah, String ulasanKjp,
			String ulasanUrusetia, String syorUrusetia, String noKertas,
			String bilMesyuarat, Date tarikhMesyuarat, Date tarikhKeputusan,
			String keputusan, String ulasanKeputusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idDokumen, Set tblphpperenggankkpenyewaans) {
		this.idKertaskerjapenyewaan = idKertaskerjapenyewaan;
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
		this.idMesyuarat = idMesyuarat;
		this.idKertaskerja = idKertaskerja;
		this.noVersi = noVersi;
		this.tajukKertas = tajukKertas;
		this.tujuan = tujuan;
		this.laporanTanah = laporanTanah;
		this.laporanPenilaiTanah = laporanPenilaiTanah;
		this.ulasanKjp = ulasanKjp;
		this.ulasanUrusetia = ulasanUrusetia;
		this.syorUrusetia = syorUrusetia;
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
		this.tblphpperenggankkpenyewaans = tblphpperenggankkpenyewaans;
	}

	// Property accessors

	public Long getIdKertaskerjapenyewaan() {
		return this.idKertaskerjapenyewaan;
	}

	public void setIdKertaskerjapenyewaan(Long idKertaskerjapenyewaan) {
		this.idKertaskerjapenyewaan = idKertaskerjapenyewaan;
	}

	public Tblphppermohonanpenyewaan getTblphppermohonanpenyewaan() {
		return this.tblphppermohonanpenyewaan;
	}

	public void setTblphppermohonanpenyewaan(
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan) {
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
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

	public String getLaporanTanah() {
		return this.laporanTanah;
	}

	public void setLaporanTanah(String laporanTanah) {
		this.laporanTanah = laporanTanah;
	}

	public String getLaporanPenilaiTanah() {
		return this.laporanPenilaiTanah;
	}

	public void setLaporanPenilaiTanah(String laporanPenilaiTanah) {
		this.laporanPenilaiTanah = laporanPenilaiTanah;
	}

	public String getUlasanKjp() {
		return this.ulasanKjp;
	}

	public void setUlasanKjp(String ulasanKjp) {
		this.ulasanKjp = ulasanKjp;
	}

	public String getUlasanUrusetia() {
		return this.ulasanUrusetia;
	}

	public void setUlasanUrusetia(String ulasanUrusetia) {
		this.ulasanUrusetia = ulasanUrusetia;
	}

	public String getSyorUrusetia() {
		return this.syorUrusetia;
	}

	public void setSyorUrusetia(String syorUrusetia) {
		this.syorUrusetia = syorUrusetia;
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

	public Set getTblphpperenggankkpenyewaans() {
		return this.tblphpperenggankkpenyewaans;
	}

	public void setTblphpperenggankkpenyewaans(Set tblphpperenggankkpenyewaans) {
		this.tblphpperenggankkpenyewaans = tblphpperenggankkpenyewaans;
	}

}