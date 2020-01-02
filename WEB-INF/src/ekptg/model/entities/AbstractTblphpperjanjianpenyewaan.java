package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpperjanjianpenyewaan entity provides the base persistence
 * definition of the Tblphpperjanjianpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpperjanjianpenyewaan implements
		java.io.Serializable {

	// Fields

	private Long idPerjanjianpenyewaan;
	private Tblphppermohonanpenyewaan tblphppermohonanpenyewaan;
	private Long idFailhasil;
	private String noSiriPerjanjian;
	private Long bilPerjanjian;
	private Date tarikhDaftarPerjanjian;
	private Long idJenistnh;
	private Long idHakmilik;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idJenishm;
	private String noHm;
	private Long idLot;
	private String noLot;
	private Date tarikhWarta;
	private String noWarta;
	private Long idMenteri;
	private Long idAgensi;
	private String keterangan;
	private Long idTempoh;
	private String tempoh;
	private Date tarikhMulaPerjanjian;
	private Date tarikhTamatPerjanjian;
	private Double kadarSewa;
	private Long idUnitluas;
	private Double luas;
	private String statusFail;
	private Double deposit;
	private Double kadarBayaran;
	private Date tarikhBayarBerikut;
	private Long idJenissewa;
	private String statusPerjanjian;
	private Date tarikhMohonSamb;
	private String flagGuna;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppemegangpenyewaans = new HashSet(0);
	private Set tblphptanahdisewas = new HashSet(0);
	private Set tblphpbayaransewas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpperjanjianpenyewaan() {
	}

	/** minimal constructor */
	public AbstractTblphpperjanjianpenyewaan(Long idPerjanjianpenyewaan,
			String noSiriPerjanjian, Long bilPerjanjian) {
		this.idPerjanjianpenyewaan = idPerjanjianpenyewaan;
		this.noSiriPerjanjian = noSiriPerjanjian;
		this.bilPerjanjian = bilPerjanjian;
	}

	/** full constructor */
	public AbstractTblphpperjanjianpenyewaan(Long idPerjanjianpenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan,
			Long idFailhasil, String noSiriPerjanjian, Long bilPerjanjian,
			Date tarikhDaftarPerjanjian, Long idJenistnh, Long idHakmilik,
			Long idNegeri, Long idDaerah, Long idMukim, Long idJenishm,
			String noHm, Long idLot, String noLot, Date tarikhWarta,
			String noWarta, Long idMenteri, Long idAgensi, String keterangan,
			Long idTempoh, String tempoh, Date tarikhMulaPerjanjian,
			Date tarikhTamatPerjanjian, Double kadarSewa, Long idUnitluas,
			Double luas, String statusFail, Double deposit,
			Double kadarBayaran, Date tarikhBayarBerikut, Long idJenissewa,
			String statusPerjanjian, Date tarikhMohonSamb, String flagGuna,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppemegangpenyewaans,
			Set tblphptanahdisewas, Set tblphpbayaransewas) {
		this.idPerjanjianpenyewaan = idPerjanjianpenyewaan;
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
		this.idFailhasil = idFailhasil;
		this.noSiriPerjanjian = noSiriPerjanjian;
		this.bilPerjanjian = bilPerjanjian;
		this.tarikhDaftarPerjanjian = tarikhDaftarPerjanjian;
		this.idJenistnh = idJenistnh;
		this.idHakmilik = idHakmilik;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idJenishm = idJenishm;
		this.noHm = noHm;
		this.idLot = idLot;
		this.noLot = noLot;
		this.tarikhWarta = tarikhWarta;
		this.noWarta = noWarta;
		this.idMenteri = idMenteri;
		this.idAgensi = idAgensi;
		this.keterangan = keterangan;
		this.idTempoh = idTempoh;
		this.tempoh = tempoh;
		this.tarikhMulaPerjanjian = tarikhMulaPerjanjian;
		this.tarikhTamatPerjanjian = tarikhTamatPerjanjian;
		this.kadarSewa = kadarSewa;
		this.idUnitluas = idUnitluas;
		this.luas = luas;
		this.statusFail = statusFail;
		this.deposit = deposit;
		this.kadarBayaran = kadarBayaran;
		this.tarikhBayarBerikut = tarikhBayarBerikut;
		this.idJenissewa = idJenissewa;
		this.statusPerjanjian = statusPerjanjian;
		this.tarikhMohonSamb = tarikhMohonSamb;
		this.flagGuna = flagGuna;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppemegangpenyewaans = tblphppemegangpenyewaans;
		this.tblphptanahdisewas = tblphptanahdisewas;
		this.tblphpbayaransewas = tblphpbayaransewas;
	}

	// Property accessors

	public Long getIdPerjanjianpenyewaan() {
		return this.idPerjanjianpenyewaan;
	}

	public void setIdPerjanjianpenyewaan(Long idPerjanjianpenyewaan) {
		this.idPerjanjianpenyewaan = idPerjanjianpenyewaan;
	}

	public Tblphppermohonanpenyewaan getTblphppermohonanpenyewaan() {
		return this.tblphppermohonanpenyewaan;
	}

	public void setTblphppermohonanpenyewaan(
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan) {
		this.tblphppermohonanpenyewaan = tblphppermohonanpenyewaan;
	}

	public Long getIdFailhasil() {
		return this.idFailhasil;
	}

	public void setIdFailhasil(Long idFailhasil) {
		this.idFailhasil = idFailhasil;
	}

	public String getNoSiriPerjanjian() {
		return this.noSiriPerjanjian;
	}

	public void setNoSiriPerjanjian(String noSiriPerjanjian) {
		this.noSiriPerjanjian = noSiriPerjanjian;
	}

	public Long getBilPerjanjian() {
		return this.bilPerjanjian;
	}

	public void setBilPerjanjian(Long bilPerjanjian) {
		this.bilPerjanjian = bilPerjanjian;
	}

	public Date getTarikhDaftarPerjanjian() {
		return this.tarikhDaftarPerjanjian;
	}

	public void setTarikhDaftarPerjanjian(Date tarikhDaftarPerjanjian) {
		this.tarikhDaftarPerjanjian = tarikhDaftarPerjanjian;
	}

	public Long getIdJenistnh() {
		return this.idJenistnh;
	}

	public void setIdJenistnh(Long idJenistnh) {
		this.idJenistnh = idJenistnh;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdJenishm() {
		return this.idJenishm;
	}

	public void setIdJenishm(Long idJenishm) {
		this.idJenishm = idJenishm;
	}

	public String getNoHm() {
		return this.noHm;
	}

	public void setNoHm(String noHm) {
		this.noHm = noHm;
	}

	public Long getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public String getNoLot() {
		return this.noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public String getNoWarta() {
		return this.noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Long getIdMenteri() {
		return this.idMenteri;
	}

	public void setIdMenteri(Long idMenteri) {
		this.idMenteri = idMenteri;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Long getIdTempoh() {
		return this.idTempoh;
	}

	public void setIdTempoh(Long idTempoh) {
		this.idTempoh = idTempoh;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}

	public Date getTarikhMulaPerjanjian() {
		return this.tarikhMulaPerjanjian;
	}

	public void setTarikhMulaPerjanjian(Date tarikhMulaPerjanjian) {
		this.tarikhMulaPerjanjian = tarikhMulaPerjanjian;
	}

	public Date getTarikhTamatPerjanjian() {
		return this.tarikhTamatPerjanjian;
	}

	public void setTarikhTamatPerjanjian(Date tarikhTamatPerjanjian) {
		this.tarikhTamatPerjanjian = tarikhTamatPerjanjian;
	}

	public Double getKadarSewa() {
		return this.kadarSewa;
	}

	public void setKadarSewa(Double kadarSewa) {
		this.kadarSewa = kadarSewa;
	}

	public Long getIdUnitluas() {
		return this.idUnitluas;
	}

	public void setIdUnitluas(Long idUnitluas) {
		this.idUnitluas = idUnitluas;
	}

	public Double getLuas() {
		return this.luas;
	}

	public void setLuas(Double luas) {
		this.luas = luas;
	}

	public String getStatusFail() {
		return this.statusFail;
	}

	public void setStatusFail(String statusFail) {
		this.statusFail = statusFail;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getKadarBayaran() {
		return this.kadarBayaran;
	}

	public void setKadarBayaran(Double kadarBayaran) {
		this.kadarBayaran = kadarBayaran;
	}

	public Date getTarikhBayarBerikut() {
		return this.tarikhBayarBerikut;
	}

	public void setTarikhBayarBerikut(Date tarikhBayarBerikut) {
		this.tarikhBayarBerikut = tarikhBayarBerikut;
	}

	public Long getIdJenissewa() {
		return this.idJenissewa;
	}

	public void setIdJenissewa(Long idJenissewa) {
		this.idJenissewa = idJenissewa;
	}

	public String getStatusPerjanjian() {
		return this.statusPerjanjian;
	}

	public void setStatusPerjanjian(String statusPerjanjian) {
		this.statusPerjanjian = statusPerjanjian;
	}

	public Date getTarikhMohonSamb() {
		return this.tarikhMohonSamb;
	}

	public void setTarikhMohonSamb(Date tarikhMohonSamb) {
		this.tarikhMohonSamb = tarikhMohonSamb;
	}

	public String getFlagGuna() {
		return this.flagGuna;
	}

	public void setFlagGuna(String flagGuna) {
		this.flagGuna = flagGuna;
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

	public Set getTblphppemegangpenyewaans() {
		return this.tblphppemegangpenyewaans;
	}

	public void setTblphppemegangpenyewaans(Set tblphppemegangpenyewaans) {
		this.tblphppemegangpenyewaans = tblphppemegangpenyewaans;
	}

	public Set getTblphptanahdisewas() {
		return this.tblphptanahdisewas;
	}

	public void setTblphptanahdisewas(Set tblphptanahdisewas) {
		this.tblphptanahdisewas = tblphptanahdisewas;
	}

	public Set getTblphpbayaransewas() {
		return this.tblphpbayaransewas;
	}

	public void setTblphpbayaransewas(Set tblphpbayaransewas) {
		this.tblphpbayaransewas = tblphpbayaransewas;
	}

}