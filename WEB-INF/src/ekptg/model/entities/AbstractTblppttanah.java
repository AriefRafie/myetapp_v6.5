package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppttanah entity provides the base persistence definition of the
 * Tblppttanah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppttanah implements java.io.Serializable {

	// Fields

	private Long idTanah;
	private Long idHakmilik;
	private Long idUnitluasambil;
	private Long luasAmbil;
	private Long idUnitluasterkini;
	private Long luasTerkini;
	private String jarakBandar;
	private String keadaanTanah;
	private String sempadanUtara;
	private String sempadanSelatan;
	private String sempadanBarat;
	private String sempadanTimur;
	private String kemudahanAwam;
	private String ulasanPegawai;
	private String tanaman;
	private Date tarikhMulaLawat;
	private Date tarikhAkhirLawat;
	private String lokasiTanah;
	private Double hargaPasaran;
	private Double hargaSeunitJpph;
	private Double amaunPenjejasanJpph;
	private Double amaunPecahpisahJpph;
	private Double naikNilaiJpph;
	private Double amaunSaguhati;
	private Double amaunBayarPenyewa;
	private Double amaunLain2;
	private Long idPelapor;
	private String unitHarga;
	private Double hargaSeunitSo;
	private Double bayarFee;
	private Double bayarTanah;
	private Double bayarPecahpisah;
	private Double bayarPenjejasan;
	private String unitHargaSo;
	private Double hargaPasaranSo;
	private Double bayarNaikNilaiso;
	private Double hargaSeunitAkhir;
	private String unitHargaAkhir;
	private String namaPbt;
	private String halangan;
	private String flagPbt;
	private String flagRezabMelayu;
	private String flagBukit;
	private String flagLandai;
	private String flagRendah;
	private String flagRata;
	private String flagPaya;
	private String flagLembah;
	private String flagLurah;
	private String flagDiusaha;
	private String flagLapang;
	private String flagTerbiar;
	private String flagHutan;
	private String flagBelukar;
	private String flagSemak;
	private String rupabumi;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblppttanah() {
	}

	/** full constructor */
	public AbstractTblppttanah(Long idHakmilik, Long idUnitluasambil,
			Long luasAmbil, Long idUnitluasterkini, Long luasTerkini,
			String jarakBandar, String keadaanTanah, String sempadanUtara,
			String sempadanSelatan, String sempadanBarat, String sempadanTimur,
			String kemudahanAwam, String ulasanPegawai, String tanaman,
			Date tarikhMulaLawat, Date tarikhAkhirLawat, String lokasiTanah,
			Double hargaPasaran, Double hargaSeunitJpph,
			Double amaunPenjejasanJpph, Double amaunPecahpisahJpph,
			Double naikNilaiJpph, Double amaunSaguhati,
			Double amaunBayarPenyewa, Double amaunLain2, Long idPelapor,
			String unitHarga, Double hargaSeunitSo, Double bayarFee,
			Double bayarTanah, Double bayarPecahpisah, Double bayarPenjejasan,
			String unitHargaSo, Double hargaPasaranSo, Double bayarNaikNilaiso,
			Double hargaSeunitAkhir, String unitHargaAkhir, String namaPbt,
			String halangan, String flagPbt, String flagRezabMelayu,
			String flagBukit, String flagLandai, String flagRendah,
			String flagRata, String flagPaya, String flagLembah,
			String flagLurah, String flagDiusaha, String flagLapang,
			String flagTerbiar, String flagHutan, String flagBelukar,
			String flagSemak, String rupabumi, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idHakmilik = idHakmilik;
		this.idUnitluasambil = idUnitluasambil;
		this.luasAmbil = luasAmbil;
		this.idUnitluasterkini = idUnitluasterkini;
		this.luasTerkini = luasTerkini;
		this.jarakBandar = jarakBandar;
		this.keadaanTanah = keadaanTanah;
		this.sempadanUtara = sempadanUtara;
		this.sempadanSelatan = sempadanSelatan;
		this.sempadanBarat = sempadanBarat;
		this.sempadanTimur = sempadanTimur;
		this.kemudahanAwam = kemudahanAwam;
		this.ulasanPegawai = ulasanPegawai;
		this.tanaman = tanaman;
		this.tarikhMulaLawat = tarikhMulaLawat;
		this.tarikhAkhirLawat = tarikhAkhirLawat;
		this.lokasiTanah = lokasiTanah;
		this.hargaPasaran = hargaPasaran;
		this.hargaSeunitJpph = hargaSeunitJpph;
		this.amaunPenjejasanJpph = amaunPenjejasanJpph;
		this.amaunPecahpisahJpph = amaunPecahpisahJpph;
		this.naikNilaiJpph = naikNilaiJpph;
		this.amaunSaguhati = amaunSaguhati;
		this.amaunBayarPenyewa = amaunBayarPenyewa;
		this.amaunLain2 = amaunLain2;
		this.idPelapor = idPelapor;
		this.unitHarga = unitHarga;
		this.hargaSeunitSo = hargaSeunitSo;
		this.bayarFee = bayarFee;
		this.bayarTanah = bayarTanah;
		this.bayarPecahpisah = bayarPecahpisah;
		this.bayarPenjejasan = bayarPenjejasan;
		this.unitHargaSo = unitHargaSo;
		this.hargaPasaranSo = hargaPasaranSo;
		this.bayarNaikNilaiso = bayarNaikNilaiso;
		this.hargaSeunitAkhir = hargaSeunitAkhir;
		this.unitHargaAkhir = unitHargaAkhir;
		this.namaPbt = namaPbt;
		this.halangan = halangan;
		this.flagPbt = flagPbt;
		this.flagRezabMelayu = flagRezabMelayu;
		this.flagBukit = flagBukit;
		this.flagLandai = flagLandai;
		this.flagRendah = flagRendah;
		this.flagRata = flagRata;
		this.flagPaya = flagPaya;
		this.flagLembah = flagLembah;
		this.flagLurah = flagLurah;
		this.flagDiusaha = flagDiusaha;
		this.flagLapang = flagLapang;
		this.flagTerbiar = flagTerbiar;
		this.flagHutan = flagHutan;
		this.flagBelukar = flagBelukar;
		this.flagSemak = flagSemak;
		this.rupabumi = rupabumi;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdTanah() {
		return this.idTanah;
	}

	public void setIdTanah(Long idTanah) {
		this.idTanah = idTanah;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Long getIdUnitluasambil() {
		return this.idUnitluasambil;
	}

	public void setIdUnitluasambil(Long idUnitluasambil) {
		this.idUnitluasambil = idUnitluasambil;
	}

	public Long getLuasAmbil() {
		return this.luasAmbil;
	}

	public void setLuasAmbil(Long luasAmbil) {
		this.luasAmbil = luasAmbil;
	}

	public Long getIdUnitluasterkini() {
		return this.idUnitluasterkini;
	}

	public void setIdUnitluasterkini(Long idUnitluasterkini) {
		this.idUnitluasterkini = idUnitluasterkini;
	}

	public Long getLuasTerkini() {
		return this.luasTerkini;
	}

	public void setLuasTerkini(Long luasTerkini) {
		this.luasTerkini = luasTerkini;
	}

	public String getJarakBandar() {
		return this.jarakBandar;
	}

	public void setJarakBandar(String jarakBandar) {
		this.jarakBandar = jarakBandar;
	}

	public String getKeadaanTanah() {
		return this.keadaanTanah;
	}

	public void setKeadaanTanah(String keadaanTanah) {
		this.keadaanTanah = keadaanTanah;
	}

	public String getSempadanUtara() {
		return this.sempadanUtara;
	}

	public void setSempadanUtara(String sempadanUtara) {
		this.sempadanUtara = sempadanUtara;
	}

	public String getSempadanSelatan() {
		return this.sempadanSelatan;
	}

	public void setSempadanSelatan(String sempadanSelatan) {
		this.sempadanSelatan = sempadanSelatan;
	}

	public String getSempadanBarat() {
		return this.sempadanBarat;
	}

	public void setSempadanBarat(String sempadanBarat) {
		this.sempadanBarat = sempadanBarat;
	}

	public String getSempadanTimur() {
		return this.sempadanTimur;
	}

	public void setSempadanTimur(String sempadanTimur) {
		this.sempadanTimur = sempadanTimur;
	}

	public String getKemudahanAwam() {
		return this.kemudahanAwam;
	}

	public void setKemudahanAwam(String kemudahanAwam) {
		this.kemudahanAwam = kemudahanAwam;
	}

	public String getUlasanPegawai() {
		return this.ulasanPegawai;
	}

	public void setUlasanPegawai(String ulasanPegawai) {
		this.ulasanPegawai = ulasanPegawai;
	}

	public String getTanaman() {
		return this.tanaman;
	}

	public void setTanaman(String tanaman) {
		this.tanaman = tanaman;
	}

	public Date getTarikhMulaLawat() {
		return this.tarikhMulaLawat;
	}

	public void setTarikhMulaLawat(Date tarikhMulaLawat) {
		this.tarikhMulaLawat = tarikhMulaLawat;
	}

	public Date getTarikhAkhirLawat() {
		return this.tarikhAkhirLawat;
	}

	public void setTarikhAkhirLawat(Date tarikhAkhirLawat) {
		this.tarikhAkhirLawat = tarikhAkhirLawat;
	}

	public String getLokasiTanah() {
		return this.lokasiTanah;
	}

	public void setLokasiTanah(String lokasiTanah) {
		this.lokasiTanah = lokasiTanah;
	}

	public Double getHargaPasaran() {
		return this.hargaPasaran;
	}

	public void setHargaPasaran(Double hargaPasaran) {
		this.hargaPasaran = hargaPasaran;
	}

	public Double getHargaSeunitJpph() {
		return this.hargaSeunitJpph;
	}

	public void setHargaSeunitJpph(Double hargaSeunitJpph) {
		this.hargaSeunitJpph = hargaSeunitJpph;
	}

	public Double getAmaunPenjejasanJpph() {
		return this.amaunPenjejasanJpph;
	}

	public void setAmaunPenjejasanJpph(Double amaunPenjejasanJpph) {
		this.amaunPenjejasanJpph = amaunPenjejasanJpph;
	}

	public Double getAmaunPecahpisahJpph() {
		return this.amaunPecahpisahJpph;
	}

	public void setAmaunPecahpisahJpph(Double amaunPecahpisahJpph) {
		this.amaunPecahpisahJpph = amaunPecahpisahJpph;
	}

	public Double getNaikNilaiJpph() {
		return this.naikNilaiJpph;
	}

	public void setNaikNilaiJpph(Double naikNilaiJpph) {
		this.naikNilaiJpph = naikNilaiJpph;
	}

	public Double getAmaunSaguhati() {
		return this.amaunSaguhati;
	}

	public void setAmaunSaguhati(Double amaunSaguhati) {
		this.amaunSaguhati = amaunSaguhati;
	}

	public Double getAmaunBayarPenyewa() {
		return this.amaunBayarPenyewa;
	}

	public void setAmaunBayarPenyewa(Double amaunBayarPenyewa) {
		this.amaunBayarPenyewa = amaunBayarPenyewa;
	}

	public Double getAmaunLain2() {
		return this.amaunLain2;
	}

	public void setAmaunLain2(Double amaunLain2) {
		this.amaunLain2 = amaunLain2;
	}

	public Long getIdPelapor() {
		return this.idPelapor;
	}

	public void setIdPelapor(Long idPelapor) {
		this.idPelapor = idPelapor;
	}

	public String getUnitHarga() {
		return this.unitHarga;
	}

	public void setUnitHarga(String unitHarga) {
		this.unitHarga = unitHarga;
	}

	public Double getHargaSeunitSo() {
		return this.hargaSeunitSo;
	}

	public void setHargaSeunitSo(Double hargaSeunitSo) {
		this.hargaSeunitSo = hargaSeunitSo;
	}

	public Double getBayarFee() {
		return this.bayarFee;
	}

	public void setBayarFee(Double bayarFee) {
		this.bayarFee = bayarFee;
	}

	public Double getBayarTanah() {
		return this.bayarTanah;
	}

	public void setBayarTanah(Double bayarTanah) {
		this.bayarTanah = bayarTanah;
	}

	public Double getBayarPecahpisah() {
		return this.bayarPecahpisah;
	}

	public void setBayarPecahpisah(Double bayarPecahpisah) {
		this.bayarPecahpisah = bayarPecahpisah;
	}

	public Double getBayarPenjejasan() {
		return this.bayarPenjejasan;
	}

	public void setBayarPenjejasan(Double bayarPenjejasan) {
		this.bayarPenjejasan = bayarPenjejasan;
	}

	public String getUnitHargaSo() {
		return this.unitHargaSo;
	}

	public void setUnitHargaSo(String unitHargaSo) {
		this.unitHargaSo = unitHargaSo;
	}

	public Double getHargaPasaranSo() {
		return this.hargaPasaranSo;
	}

	public void setHargaPasaranSo(Double hargaPasaranSo) {
		this.hargaPasaranSo = hargaPasaranSo;
	}

	public Double getBayarNaikNilaiso() {
		return this.bayarNaikNilaiso;
	}

	public void setBayarNaikNilaiso(Double bayarNaikNilaiso) {
		this.bayarNaikNilaiso = bayarNaikNilaiso;
	}

	public Double getHargaSeunitAkhir() {
		return this.hargaSeunitAkhir;
	}

	public void setHargaSeunitAkhir(Double hargaSeunitAkhir) {
		this.hargaSeunitAkhir = hargaSeunitAkhir;
	}

	public String getUnitHargaAkhir() {
		return this.unitHargaAkhir;
	}

	public void setUnitHargaAkhir(String unitHargaAkhir) {
		this.unitHargaAkhir = unitHargaAkhir;
	}

	public String getNamaPbt() {
		return this.namaPbt;
	}

	public void setNamaPbt(String namaPbt) {
		this.namaPbt = namaPbt;
	}

	public String getHalangan() {
		return this.halangan;
	}

	public void setHalangan(String halangan) {
		this.halangan = halangan;
	}

	public String getFlagPbt() {
		return this.flagPbt;
	}

	public void setFlagPbt(String flagPbt) {
		this.flagPbt = flagPbt;
	}

	public String getFlagRezabMelayu() {
		return this.flagRezabMelayu;
	}

	public void setFlagRezabMelayu(String flagRezabMelayu) {
		this.flagRezabMelayu = flagRezabMelayu;
	}

	public String getFlagBukit() {
		return this.flagBukit;
	}

	public void setFlagBukit(String flagBukit) {
		this.flagBukit = flagBukit;
	}

	public String getFlagLandai() {
		return this.flagLandai;
	}

	public void setFlagLandai(String flagLandai) {
		this.flagLandai = flagLandai;
	}

	public String getFlagRendah() {
		return this.flagRendah;
	}

	public void setFlagRendah(String flagRendah) {
		this.flagRendah = flagRendah;
	}

	public String getFlagRata() {
		return this.flagRata;
	}

	public void setFlagRata(String flagRata) {
		this.flagRata = flagRata;
	}

	public String getFlagPaya() {
		return this.flagPaya;
	}

	public void setFlagPaya(String flagPaya) {
		this.flagPaya = flagPaya;
	}

	public String getFlagLembah() {
		return this.flagLembah;
	}

	public void setFlagLembah(String flagLembah) {
		this.flagLembah = flagLembah;
	}

	public String getFlagLurah() {
		return this.flagLurah;
	}

	public void setFlagLurah(String flagLurah) {
		this.flagLurah = flagLurah;
	}

	public String getFlagDiusaha() {
		return this.flagDiusaha;
	}

	public void setFlagDiusaha(String flagDiusaha) {
		this.flagDiusaha = flagDiusaha;
	}

	public String getFlagLapang() {
		return this.flagLapang;
	}

	public void setFlagLapang(String flagLapang) {
		this.flagLapang = flagLapang;
	}

	public String getFlagTerbiar() {
		return this.flagTerbiar;
	}

	public void setFlagTerbiar(String flagTerbiar) {
		this.flagTerbiar = flagTerbiar;
	}

	public String getFlagHutan() {
		return this.flagHutan;
	}

	public void setFlagHutan(String flagHutan) {
		this.flagHutan = flagHutan;
	}

	public String getFlagBelukar() {
		return this.flagBelukar;
	}

	public void setFlagBelukar(String flagBelukar) {
		this.flagBelukar = flagBelukar;
	}

	public String getFlagSemak() {
		return this.flagSemak;
	}

	public void setFlagSemak(String flagSemak) {
		this.flagSemak = flagSemak;
	}

	public String getRupabumi() {
		return this.rupabumi;
	}

	public void setRupabumi(String rupabumi) {
		this.rupabumi = rupabumi;
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