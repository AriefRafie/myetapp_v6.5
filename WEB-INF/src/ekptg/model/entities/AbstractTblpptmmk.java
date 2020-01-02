package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblpptmmk entity provides the base persistence definition of the Tblpptmmk entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptmmk  implements java.io.Serializable {


    // Fields    

     private Long idMmk;
     private Long idPenarikanbalik;
     private Long jenisMmk;
     private String ulasan;
     private String noRujmmk	;
     private String flagSemak	;
     private Long idSemak	;
     private Date tarikhSemak;
     private String flagBorangi;
     private Long idPermohonan;
     private String tujuan;
     private String latarbelakang;
     private String asasPertimbangan	;
     private String kesimpulan	;
     private String syor	;
     private String kedudukanLaporanTnh;
     private String pengesahanPeruntukan;
     private String pandangan;
     private String implikasi;
     private String ulasanJabteknikal;
     private String perihalTanah;
     private String perihalPohon;
     private String anggaranKos;
     private String perakuanPentadbirTnh;
     private String nilaiAtasTanah;
     private String pengecualianUpahUkur;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblpptmmk() {
    }

    
    /** full constructor */
    public AbstractTblpptmmk(Long idPenarikanbalik, Long jenisMmk, String ulasan, String noRujmmk	, String flagSemak	, Long idSemak	, Date tarikhSemak, String flagBorangi, Long idPermohonan, String tujuan, String latarbelakang, String asasPertimbangan	, String kesimpulan	, String syor	, String kedudukanLaporanTnh, String pengesahanPeruntukan, String pandangan, String implikasi, String ulasanJabteknikal, String perihalTanah, String perihalPohon, String anggaranKos, String perakuanPentadbirTnh, String nilaiAtasTanah, String pengecualianUpahUkur, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.idPenarikanbalik = idPenarikanbalik;
        this.jenisMmk = jenisMmk;
        this.ulasan = ulasan;
        this.noRujmmk	 = noRujmmk	;
        this.flagSemak	 = flagSemak	;
        this.idSemak	 = idSemak	;
        this.tarikhSemak = tarikhSemak;
        this.flagBorangi = flagBorangi;
        this.idPermohonan = idPermohonan;
        this.tujuan = tujuan;
        this.latarbelakang = latarbelakang;
        this.asasPertimbangan	 = asasPertimbangan	;
        this.kesimpulan	 = kesimpulan	;
        this.syor	 = syor	;
        this.kedudukanLaporanTnh = kedudukanLaporanTnh;
        this.pengesahanPeruntukan = pengesahanPeruntukan;
        this.pandangan = pandangan;
        this.implikasi = implikasi;
        this.ulasanJabteknikal = ulasanJabteknikal;
        this.perihalTanah = perihalTanah;
        this.perihalPohon = perihalPohon;
        this.anggaranKos = anggaranKos;
        this.perakuanPentadbirTnh = perakuanPentadbirTnh;
        this.nilaiAtasTanah = nilaiAtasTanah;
        this.pengecualianUpahUkur = pengecualianUpahUkur;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdMmk() {
        return this.idMmk;
    }
    
    public void setIdMmk(Long idMmk) {
        this.idMmk = idMmk;
    }

    public Long getIdPenarikanbalik() {
        return this.idPenarikanbalik;
    }
    
    public void setIdPenarikanbalik(Long idPenarikanbalik) {
        this.idPenarikanbalik = idPenarikanbalik;
    }

    public Long getJenisMmk() {
        return this.jenisMmk;
    }
    
    public void setJenisMmk(Long jenisMmk) {
        this.jenisMmk = jenisMmk;
    }

    public String getUlasan() {
        return this.ulasan;
    }
    
    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getNoRujmmk	() {
        return this.noRujmmk	;
    }
    
    public void setNoRujmmk	(String noRujmmk	) {
        this.noRujmmk	 = noRujmmk	;
    }

    public String getFlagSemak	() {
        return this.flagSemak	;
    }
    
    public void setFlagSemak	(String flagSemak	) {
        this.flagSemak	 = flagSemak	;
    }

    public Long getIdSemak	() {
        return this.idSemak	;
    }
    
    public void setIdSemak	(Long idSemak	) {
        this.idSemak	 = idSemak	;
    }

    public Date getTarikhSemak() {
        return this.tarikhSemak;
    }
    
    public void setTarikhSemak(Date tarikhSemak) {
        this.tarikhSemak = tarikhSemak;
    }

    public String getFlagBorangi() {
        return this.flagBorangi;
    }
    
    public void setFlagBorangi(String flagBorangi) {
        this.flagBorangi = flagBorangi;
    }

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTujuan() {
        return this.tujuan;
    }
    
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getLatarbelakang() {
        return this.latarbelakang;
    }
    
    public void setLatarbelakang(String latarbelakang) {
        this.latarbelakang = latarbelakang;
    }

    public String getAsasPertimbangan	() {
        return this.asasPertimbangan	;
    }
    
    public void setAsasPertimbangan	(String asasPertimbangan	) {
        this.asasPertimbangan	 = asasPertimbangan	;
    }

    public String getKesimpulan	() {
        return this.kesimpulan	;
    }
    
    public void setKesimpulan	(String kesimpulan	) {
        this.kesimpulan	 = kesimpulan	;
    }

    public String getSyor	() {
        return this.syor	;
    }
    
    public void setSyor	(String syor	) {
        this.syor	 = syor	;
    }

    public String getKedudukanLaporanTnh() {
        return this.kedudukanLaporanTnh;
    }
    
    public void setKedudukanLaporanTnh(String kedudukanLaporanTnh) {
        this.kedudukanLaporanTnh = kedudukanLaporanTnh;
    }

    public String getPengesahanPeruntukan() {
        return this.pengesahanPeruntukan;
    }
    
    public void setPengesahanPeruntukan(String pengesahanPeruntukan) {
        this.pengesahanPeruntukan = pengesahanPeruntukan;
    }

    public String getPandangan() {
        return this.pandangan;
    }
    
    public void setPandangan(String pandangan) {
        this.pandangan = pandangan;
    }

    public String getImplikasi() {
        return this.implikasi;
    }
    
    public void setImplikasi(String implikasi) {
        this.implikasi = implikasi;
    }

    public String getUlasanJabteknikal() {
        return this.ulasanJabteknikal;
    }
    
    public void setUlasanJabteknikal(String ulasanJabteknikal) {
        this.ulasanJabteknikal = ulasanJabteknikal;
    }

    public String getPerihalTanah() {
        return this.perihalTanah;
    }
    
    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getPerihalPohon() {
        return this.perihalPohon;
    }
    
    public void setPerihalPohon(String perihalPohon) {
        this.perihalPohon = perihalPohon;
    }

    public String getAnggaranKos() {
        return this.anggaranKos;
    }
    
    public void setAnggaranKos(String anggaranKos) {
        this.anggaranKos = anggaranKos;
    }

    public String getPerakuanPentadbirTnh() {
        return this.perakuanPentadbirTnh;
    }
    
    public void setPerakuanPentadbirTnh(String perakuanPentadbirTnh) {
        this.perakuanPentadbirTnh = perakuanPentadbirTnh;
    }

    public String getNilaiAtasTanah() {
        return this.nilaiAtasTanah;
    }
    
    public void setNilaiAtasTanah(String nilaiAtasTanah) {
        this.nilaiAtasTanah = nilaiAtasTanah;
    }

    public String getPengecualianUpahUkur() {
        return this.pengecualianUpahUkur;
    }
    
    public void setPengecualianUpahUkur(String pengecualianUpahUkur) {
        this.pengecualianUpahUkur = pengecualianUpahUkur;
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