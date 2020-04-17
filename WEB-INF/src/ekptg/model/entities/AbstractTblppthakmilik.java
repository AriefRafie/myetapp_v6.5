package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblppthakmilik entity provides the base persistence definition of the Tblppthakmilik entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppthakmilik  implements java.io.Serializable {


    // Fields    

     private Long idHakmilik;
     private Long idNegeri;
     private Long idDaerah;
     private Long idMukim;
     private Long idJenishakmilik;
     private String noHakmilik;
     private Long idSubjaket;
     private Date tarikhTerima;
     private Date tarikhDaftar;
     private String flagRezab;
     private String flagGsa;
     private String tempohLuput;
     private Long tarikhLuput;
     private String noPt;
     private Long idUnitluaspt;
     private String luasPt;
     private String noLot;
     private Long idUnitluaslot;
     private String luasLot;
     private Long idUnitluasambil;
     private Long luasAmbil;
     private Long idUnitluasbaru;
     private Long luasBaru;
     private String noPelan;
     private String noSyit;
     private String lokasi;
     private Long idKategoritanah;
     private String syaratNyata;
     private String syaratKhas;
     private String sekatanKepentingan;
     private String sekatanHak;
     private String jenisMilik;
     private String ulasanPentadbir;
     private Double jumlahAward;
     private String unitAward;
     private Date tarikhHantarDhd;
     private Date tarikhTerimaDhd;
     private String flagAmbilSegera;
     private String flagPembatalan;
     private String flagPenarikanBalik;
     private String flagLaporanTanah;
     private String flagHantarDhd;
     private String flagTerimaDhd;
     private String flagSiasatan;
     private String flagBorangl;
     private String flagPermintaanUkur;
     private Long idSiasatan;
     private Long idBorangk;
     private Long idBorangg;
     private Long idBorange;
     private Long idBorangi;
     private String noPermintaanukur;
     private Long idBorangl;
     private Long idPenarikanbalik;
     private Long idPembatalan;
     private String flagUbah;
     private String noBangunan;
     private String noTingkat;
     private String noPetak;
     private Date tarikhBorangk;
     private Date tarikhBorangkSegera;
     private Long idPermohonan;
     private Date tarikhTerimaHm;
     private String noKelulusan;
     private String noDaftar;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblppthakmilik() {
    }

    
    /** full constructor */
    public AbstractTblppthakmilik(Long idNegeri, Long idDaerah, Long idMukim, Long idJenishakmilik, String noHakmilik, Long idSubjaket, Date tarikhTerima, Date tarikhDaftar, String flagRezab, String flagGsa, String tempohLuput, Long tarikhLuput, String noPt, Long idUnitluaspt, String luasPt, String noLot, Long idUnitluaslot, String luasLot, Long idUnitluasambil, Long luasAmbil, Long idUnitluasbaru, Long luasBaru, String noPelan, String noSyit, String lokasi, Long idKategoritanah, String syaratNyata, String syaratKhas, String sekatanKepentingan, String sekatanHak, String jenisMilik, String ulasanPentadbir, Double jumlahAward, String unitAward, Date tarikhHantarDhd, Date tarikhTerimaDhd, String flagAmbilSegera, String flagPembatalan, String flagPenarikanBalik, String flagLaporanTanah, String flagHantarDhd, String flagTerimaDhd, String flagSiasatan, String flagBorangl, String flagPermintaanUkur, Long idSiasatan, Long idBorangk, Long idBorangg, Long idBorange, Long idBorangi, String noPermintaanukur, Long idBorangl, Long idPenarikanbalik, Long idPembatalan, String flagUbah, String noBangunan, String noTingkat, String noPetak, Date tarikhBorangk, Date tarikhBorangkSegera, Long idPermohonan, Date tarikhTerimaHm, String noKelulusan, String noDaftar, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.idNegeri = idNegeri;
        this.idDaerah = idDaerah;
        this.idMukim = idMukim;
        this.idJenishakmilik = idJenishakmilik;
        this.noHakmilik = noHakmilik;
        this.idSubjaket = idSubjaket;
        this.tarikhTerima = tarikhTerima;
        this.tarikhDaftar = tarikhDaftar;
        this.flagRezab = flagRezab;
        this.flagGsa = flagGsa;
        this.tempohLuput = tempohLuput;
        this.tarikhLuput = tarikhLuput;
        this.noPt = noPt;
        this.idUnitluaspt = idUnitluaspt;
        this.luasPt = luasPt;
        this.noLot = noLot;
        this.idUnitluaslot = idUnitluaslot;
        this.luasLot = luasLot;
        this.idUnitluasambil = idUnitluasambil;
        this.luasAmbil = luasAmbil;
        this.idUnitluasbaru = idUnitluasbaru;
        this.luasBaru = luasBaru;
        this.noPelan = noPelan;
        this.noSyit = noSyit;
        this.lokasi = lokasi;
        this.idKategoritanah = idKategoritanah;
        this.syaratNyata = syaratNyata;
        this.syaratKhas = syaratKhas;
        this.sekatanKepentingan = sekatanKepentingan;
        this.sekatanHak = sekatanHak;
        this.jenisMilik = jenisMilik;
        this.ulasanPentadbir = ulasanPentadbir;
        this.jumlahAward = jumlahAward;
        this.unitAward = unitAward;
        this.tarikhHantarDhd = tarikhHantarDhd;
        this.tarikhTerimaDhd = tarikhTerimaDhd;
        this.flagAmbilSegera = flagAmbilSegera;
        this.flagPembatalan = flagPembatalan;
        this.flagPenarikanBalik = flagPenarikanBalik;
        this.flagLaporanTanah = flagLaporanTanah;
        this.flagHantarDhd = flagHantarDhd;
        this.flagTerimaDhd = flagTerimaDhd;
        this.flagSiasatan = flagSiasatan;
        this.flagBorangl = flagBorangl;
        this.flagPermintaanUkur = flagPermintaanUkur;
        this.idSiasatan = idSiasatan;
        this.idBorangk = idBorangk;
        this.idBorangg = idBorangg;
        this.idBorange = idBorange;
        this.idBorangi = idBorangi;
        this.noPermintaanukur = noPermintaanukur;
        this.idBorangl = idBorangl;
        this.idPenarikanbalik = idPenarikanbalik;
        this.idPembatalan = idPembatalan;
        this.flagUbah = flagUbah;
        this.noBangunan = noBangunan;
        this.noTingkat = noTingkat;
        this.noPetak = noPetak;
        this.tarikhBorangk = tarikhBorangk;
        this.tarikhBorangkSegera = tarikhBorangkSegera;
        this.idPermohonan = idPermohonan;
        this.tarikhTerimaHm = tarikhTerimaHm;
        this.noKelulusan = noKelulusan;
        this.noDaftar = noDaftar;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

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

    public Long getIdJenishakmilik() {
        return this.idJenishakmilik;
    }
    
    public void setIdJenishakmilik(Long idJenishakmilik) {
        this.idJenishakmilik = idJenishakmilik;
    }

    public String getNoHakmilik() {
        return this.noHakmilik;
    }
    
    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public Long getIdSubjaket() {
        return this.idSubjaket;
    }
    
    public void setIdSubjaket(Long idSubjaket) {
        this.idSubjaket = idSubjaket;
    }

    public Date getTarikhTerima() {
        return this.tarikhTerima;
    }
    
    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public Date getTarikhDaftar() {
        return this.tarikhDaftar;
    }
    
    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getFlagRezab() {
        return this.flagRezab;
    }
    
    public void setFlagRezab(String flagRezab) {
        this.flagRezab = flagRezab;
    }

    public String getFlagGsa() {
        return this.flagGsa;
    }
    
    public void setFlagGsa(String flagGsa) {
        this.flagGsa = flagGsa;
    }

    public String getTempohLuput() {
        return this.tempohLuput;
    }
    
    public void setTempohLuput(String tempohLuput) {
        this.tempohLuput = tempohLuput;
    }

    public Long getTarikhLuput() {
        return this.tarikhLuput;
    }
    
    public void setTarikhLuput(Long tarikhLuput) {
        this.tarikhLuput = tarikhLuput;
    }

    public String getNoPt() {
        return this.noPt;
    }
    
    public void setNoPt(String noPt) {
        this.noPt = noPt;
    }

    public Long getIdUnitluaspt() {
        return this.idUnitluaspt;
    }
    
    public void setIdUnitluaspt(Long idUnitluaspt) {
        this.idUnitluaspt = idUnitluaspt;
    }

    public String getLuasPt() {
        return this.luasPt;
    }
    
    public void setLuasPt(String luasPt) {
        this.luasPt = luasPt;
    }

    public String getNoLot() {
        return this.noLot;
    }
    
    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public Long getIdUnitluaslot() {
        return this.idUnitluaslot;
    }
    
    public void setIdUnitluaslot(Long idUnitluaslot) {
        this.idUnitluaslot = idUnitluaslot;
    }

    public String getLuasLot() {
        return this.luasLot;
    }
    
    public void setLuasLot(String luasLot) {
        this.luasLot = luasLot;
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

    public Long getIdUnitluasbaru() {
        return this.idUnitluasbaru;
    }
    
    public void setIdUnitluasbaru(Long idUnitluasbaru) {
        this.idUnitluasbaru = idUnitluasbaru;
    }

    public Long getLuasBaru() {
        return this.luasBaru;
    }
    
    public void setLuasBaru(Long luasBaru) {
        this.luasBaru = luasBaru;
    }

    public String getNoPelan() {
        return this.noPelan;
    }
    
    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

    public String getNoSyit() {
        return this.noSyit;
    }
    
    public void setNoSyit(String noSyit) {
        this.noSyit = noSyit;
    }

    public String getLokasi() {
        return this.lokasi;
    }
    
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Long getIdKategoritanah() {
        return this.idKategoritanah;
    }
    
    public void setIdKategoritanah(Long idKategoritanah) {
        this.idKategoritanah = idKategoritanah;
    }

    public String getSyaratNyata() {
        return this.syaratNyata;
    }
    
    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSyaratKhas() {
        return this.syaratKhas;
    }
    
    public void setSyaratKhas(String syaratKhas) {
        this.syaratKhas = syaratKhas;
    }

    public String getSekatanKepentingan() {
        return this.sekatanKepentingan;
    }
    
    public void setSekatanKepentingan(String sekatanKepentingan) {
        this.sekatanKepentingan = sekatanKepentingan;
    }

    public String getSekatanHak() {
        return this.sekatanHak;
    }
    
    public void setSekatanHak(String sekatanHak) {
        this.sekatanHak = sekatanHak;
    }

    public String getJenisMilik() {
        return this.jenisMilik;
    }
    
    public void setJenisMilik(String jenisMilik) {
        this.jenisMilik = jenisMilik;
    }

    public String getUlasanPentadbir() {
        return this.ulasanPentadbir;
    }
    
    public void setUlasanPentadbir(String ulasanPentadbir) {
        this.ulasanPentadbir = ulasanPentadbir;
    }

    public Double getJumlahAward() {
        return this.jumlahAward;
    }
    
    public void setJumlahAward(Double jumlahAward) {
        this.jumlahAward = jumlahAward;
    }

    public String getUnitAward() {
        return this.unitAward;
    }
    
    public void setUnitAward(String unitAward) {
        this.unitAward = unitAward;
    }

    public Date getTarikhHantarDhd() {
        return this.tarikhHantarDhd;
    }
    
    public void setTarikhHantarDhd(Date tarikhHantarDhd) {
        this.tarikhHantarDhd = tarikhHantarDhd;
    }

    public Date getTarikhTerimaDhd() {
        return this.tarikhTerimaDhd;
    }
    
    public void setTarikhTerimaDhd(Date tarikhTerimaDhd) {
        this.tarikhTerimaDhd = tarikhTerimaDhd;
    }

    public String getFlagAmbilSegera() {
        return this.flagAmbilSegera;
    }
    
    public void setFlagAmbilSegera(String flagAmbilSegera) {
        this.flagAmbilSegera = flagAmbilSegera;
    }

    public String getFlagPembatalan() {
        return this.flagPembatalan;
    }
    
    public void setFlagPembatalan(String flagPembatalan) {
        this.flagPembatalan = flagPembatalan;
    }

    public String getFlagPenarikanBalik() {
        return this.flagPenarikanBalik;
    }
    
    public void setFlagPenarikanBalik(String flagPenarikanBalik) {
        this.flagPenarikanBalik = flagPenarikanBalik;
    }

    public String getFlagLaporanTanah() {
        return this.flagLaporanTanah;
    }
    
    public void setFlagLaporanTanah(String flagLaporanTanah) {
        this.flagLaporanTanah = flagLaporanTanah;
    }

    public String getFlagHantarDhd() {
        return this.flagHantarDhd;
    }
    
    public void setFlagHantarDhd(String flagHantarDhd) {
        this.flagHantarDhd = flagHantarDhd;
    }

    public String getFlagTerimaDhd() {
        return this.flagTerimaDhd;
    }
    
    public void setFlagTerimaDhd(String flagTerimaDhd) {
        this.flagTerimaDhd = flagTerimaDhd;
    }

    public String getFlagSiasatan() {
        return this.flagSiasatan;
    }
    
    public void setFlagSiasatan(String flagSiasatan) {
        this.flagSiasatan = flagSiasatan;
    }

    public String getFlagBorangl() {
        return this.flagBorangl;
    }
    
    public void setFlagBorangl(String flagBorangl) {
        this.flagBorangl = flagBorangl;
    }

    public String getFlagPermintaanUkur() {
        return this.flagPermintaanUkur;
    }
    
    public void setFlagPermintaanUkur(String flagPermintaanUkur) {
        this.flagPermintaanUkur = flagPermintaanUkur;
    }

    public Long getIdSiasatan() {
        return this.idSiasatan;
    }
    
    public void setIdSiasatan(Long idSiasatan) {
        this.idSiasatan = idSiasatan;
    }

    public Long getIdBorangk() {
        return this.idBorangk;
    }
    
    public void setIdBorangk(Long idBorangk) {
        this.idBorangk = idBorangk;
    }

    public Long getIdBorangg() {
        return this.idBorangg;
    }
    
    public void setIdBorangg(Long idBorangg) {
        this.idBorangg = idBorangg;
    }

    public Long getIdBorange() {
        return this.idBorange;
    }
    
    public void setIdBorange(Long idBorange) {
        this.idBorange = idBorange;
    }

    public Long getIdBorangi() {
        return this.idBorangi;
    }
    
    public void setIdBorangi(Long idBorangi) {
        this.idBorangi = idBorangi;
    }

    public String getNoPermintaanukur() {
        return this.noPermintaanukur;
    }
    
    public void setNoPermintaanukur(String noPermintaanukur) {
        this.noPermintaanukur = noPermintaanukur;
    }

    public Long getIdBorangl() {
        return this.idBorangl;
    }
    
    public void setIdBorangl(Long idBorangl) {
        this.idBorangl = idBorangl;
    }

    public Long getIdPenarikanbalik() {
        return this.idPenarikanbalik;
    }
    
    public void setIdPenarikanbalik(Long idPenarikanbalik) {
        this.idPenarikanbalik = idPenarikanbalik;
    }

    public Long getIdPembatalan() {
        return this.idPembatalan;
    }
    
    public void setIdPembatalan(Long idPembatalan) {
        this.idPembatalan = idPembatalan;
    }

    public String getFlagUbah() {
        return this.flagUbah;
    }
    
    public void setFlagUbah(String flagUbah) {
        this.flagUbah = flagUbah;
    }

    public String getNoBangunan() {
        return this.noBangunan;
    }
    
    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoTingkat() {
        return this.noTingkat;
    }
    
    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getNoPetak() {
        return this.noPetak;
    }
    
    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public Date getTarikhBorangk() {
        return this.tarikhBorangk;
    }
    
    public void setTarikhBorangk(Date tarikhBorangk) {
        this.tarikhBorangk = tarikhBorangk;
    }

    public Date getTarikhBorangkSegera() {
        return this.tarikhBorangkSegera;
    }
    
    public void setTarikhBorangkSegera(Date tarikhBorangkSegera) {
        this.tarikhBorangkSegera = tarikhBorangkSegera;
    }

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Date getTarikhTerimaHm() {
        return this.tarikhTerimaHm;
    }
    
    public void setTarikhTerimaHm(Date tarikhTerimaHm) {
        this.tarikhTerimaHm = tarikhTerimaHm;
    }

    public String getNoKelulusan() {
        return this.noKelulusan;
    }
    
    public void setNoKelulusan(String noKelulusan) {
        this.noKelulusan = noKelulusan;
    }

    public String getNoDaftar() {
        return this.noDaftar;
    }
    
    public void setNoDaftar(String noDaftar) {
        this.noDaftar = noDaftar;
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