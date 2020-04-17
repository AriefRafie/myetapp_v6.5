package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblppttanahumum entity provides the base persistence definition of the Tblppttanahumum entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppttanahumum  implements java.io.Serializable {


    // Fields    

     private Long idTanahumum;
     private Long idPermohonan;
     private Date tarikhLawatan;
     private Date tarikhMulaChart;
     private Date tarikhAkhirChart;
     private String perihalSyit;
     private String jenisTanah;
     private String perihalKawasanSimpan;
     private String perihalKawasanMajlis;
     private String perihalKawasanLain2;
     private String lokasiTanah;
     private String keadaanRupabumi;
     private String kemudahanAwam;
     private String perihalBangunan;
     private String ulasanSyor;
     private String perihalTmSendiri;
     private String perihalTnhKerajaan;
     private Long bilHakmilik;
     private Long idUnitluas;
     private String luasTerlibat;
     private Double hargaAnggar;
     private String unitAnggar;
     private Double hargaAnggarBangunan;
     private String perihalTmtrSekutuan;
     private String perihalTrNegeri;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblppttanahumum() {
    }

    
    /** full constructor */
    public AbstractTblppttanahumum(Long idPermohonan, Date tarikhLawatan, Date tarikhMulaChart, Date tarikhAkhirChart, String perihalSyit, String jenisTanah, String perihalKawasanSimpan, String perihalKawasanMajlis, String perihalKawasanLain2, String lokasiTanah, String keadaanRupabumi, String kemudahanAwam, String perihalBangunan, String ulasanSyor, String perihalTmSendiri, String perihalTnhKerajaan, Long bilHakmilik, Long idUnitluas, String luasTerlibat, Double hargaAnggar, String unitAnggar, Double hargaAnggarBangunan, String perihalTmtrSekutuan, String perihalTrNegeri, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.idPermohonan = idPermohonan;
        this.tarikhLawatan = tarikhLawatan;
        this.tarikhMulaChart = tarikhMulaChart;
        this.tarikhAkhirChart = tarikhAkhirChart;
        this.perihalSyit = perihalSyit;
        this.jenisTanah = jenisTanah;
        this.perihalKawasanSimpan = perihalKawasanSimpan;
        this.perihalKawasanMajlis = perihalKawasanMajlis;
        this.perihalKawasanLain2 = perihalKawasanLain2;
        this.lokasiTanah = lokasiTanah;
        this.keadaanRupabumi = keadaanRupabumi;
        this.kemudahanAwam = kemudahanAwam;
        this.perihalBangunan = perihalBangunan;
        this.ulasanSyor = ulasanSyor;
        this.perihalTmSendiri = perihalTmSendiri;
        this.perihalTnhKerajaan = perihalTnhKerajaan;
        this.bilHakmilik = bilHakmilik;
        this.idUnitluas = idUnitluas;
        this.luasTerlibat = luasTerlibat;
        this.hargaAnggar = hargaAnggar;
        this.unitAnggar = unitAnggar;
        this.hargaAnggarBangunan = hargaAnggarBangunan;
        this.perihalTmtrSekutuan = perihalTmtrSekutuan;
        this.perihalTrNegeri = perihalTrNegeri;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdTanahumum() {
        return this.idTanahumum;
    }
    
    public void setIdTanahumum(Long idTanahumum) {
        this.idTanahumum = idTanahumum;
    }

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Date getTarikhLawatan() {
        return this.tarikhLawatan;
    }
    
    public void setTarikhLawatan(Date tarikhLawatan) {
        this.tarikhLawatan = tarikhLawatan;
    }

    public Date getTarikhMulaChart() {
        return this.tarikhMulaChart;
    }
    
    public void setTarikhMulaChart(Date tarikhMulaChart) {
        this.tarikhMulaChart = tarikhMulaChart;
    }

    public Date getTarikhAkhirChart() {
        return this.tarikhAkhirChart;
    }
    
    public void setTarikhAkhirChart(Date tarikhAkhirChart) {
        this.tarikhAkhirChart = tarikhAkhirChart;
    }

    public String getPerihalSyit() {
        return this.perihalSyit;
    }
    
    public void setPerihalSyit(String perihalSyit) {
        this.perihalSyit = perihalSyit;
    }

    public String getJenisTanah() {
        return this.jenisTanah;
    }
    
    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getPerihalKawasanSimpan() {
        return this.perihalKawasanSimpan;
    }
    
    public void setPerihalKawasanSimpan(String perihalKawasanSimpan) {
        this.perihalKawasanSimpan = perihalKawasanSimpan;
    }

    public String getPerihalKawasanMajlis() {
        return this.perihalKawasanMajlis;
    }
    
    public void setPerihalKawasanMajlis(String perihalKawasanMajlis) {
        this.perihalKawasanMajlis = perihalKawasanMajlis;
    }

    public String getPerihalKawasanLain2() {
        return this.perihalKawasanLain2;
    }
    
    public void setPerihalKawasanLain2(String perihalKawasanLain2) {
        this.perihalKawasanLain2 = perihalKawasanLain2;
    }

    public String getLokasiTanah() {
        return this.lokasiTanah;
    }
    
    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public String getKeadaanRupabumi() {
        return this.keadaanRupabumi;
    }
    
    public void setKeadaanRupabumi(String keadaanRupabumi) {
        this.keadaanRupabumi = keadaanRupabumi;
    }

    public String getKemudahanAwam() {
        return this.kemudahanAwam;
    }
    
    public void setKemudahanAwam(String kemudahanAwam) {
        this.kemudahanAwam = kemudahanAwam;
    }

    public String getPerihalBangunan() {
        return this.perihalBangunan;
    }
    
    public void setPerihalBangunan(String perihalBangunan) {
        this.perihalBangunan = perihalBangunan;
    }

    public String getUlasanSyor() {
        return this.ulasanSyor;
    }
    
    public void setUlasanSyor(String ulasanSyor) {
        this.ulasanSyor = ulasanSyor;
    }

    public String getPerihalTmSendiri() {
        return this.perihalTmSendiri;
    }
    
    public void setPerihalTmSendiri(String perihalTmSendiri) {
        this.perihalTmSendiri = perihalTmSendiri;
    }

    public String getPerihalTnhKerajaan() {
        return this.perihalTnhKerajaan;
    }
    
    public void setPerihalTnhKerajaan(String perihalTnhKerajaan) {
        this.perihalTnhKerajaan = perihalTnhKerajaan;
    }

    public Long getBilHakmilik() {
        return this.bilHakmilik;
    }
    
    public void setBilHakmilik(Long bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public Long getIdUnitluas() {
        return this.idUnitluas;
    }
    
    public void setIdUnitluas(Long idUnitluas) {
        this.idUnitluas = idUnitluas;
    }

    public String getLuasTerlibat() {
        return this.luasTerlibat;
    }
    
    public void setLuasTerlibat(String luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public Double getHargaAnggar() {
        return this.hargaAnggar;
    }
    
    public void setHargaAnggar(Double hargaAnggar) {
        this.hargaAnggar = hargaAnggar;
    }

    public String getUnitAnggar() {
        return this.unitAnggar;
    }
    
    public void setUnitAnggar(String unitAnggar) {
        this.unitAnggar = unitAnggar;
    }

    public Double getHargaAnggarBangunan() {
        return this.hargaAnggarBangunan;
    }
    
    public void setHargaAnggarBangunan(Double hargaAnggarBangunan) {
        this.hargaAnggarBangunan = hargaAnggarBangunan;
    }

    public String getPerihalTmtrSekutuan() {
        return this.perihalTmtrSekutuan;
    }
    
    public void setPerihalTmtrSekutuan(String perihalTmtrSekutuan) {
        this.perihalTmtrSekutuan = perihalTmtrSekutuan;
    }

    public String getPerihalTrNegeri() {
        return this.perihalTrNegeri;
    }
    
    public void setPerihalTrNegeri(String perihalTrNegeri) {
        this.perihalTrNegeri = perihalTrNegeri;
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