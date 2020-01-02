package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractTblpptpermohonan entity provides the base persistence definition of the Tblpptpermohonan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptpermohonan  implements java.io.Serializable {


    // Fields    

     private Long idPermohonan;
     private Long idFail;
     private String noPermohonan;
     private String noPerserahan;
     private Date tarikhPermohonan;
     private Long idAgensi;
     private String flagPeruntukan;
     private String flagSegera;
     private String tujuan;
     private String noRujukanSurat;
     private Date tarikhSurat;
     private Date tarikhKehendaki;
     private String alamat1;
     private String alamat2;
     private String alamat3;
     private String poskod;
     private Long idNegeri;
     private Long idMukim;
     private Long idStatus;
     private Long idDaerah;
     private Date tarikhBoranga;
     private Date tarikhBorangb;
     private Date tarikhBorangc;
     private Date tarikhBorangd;
     private String perihalKawasan;
     private Date tarikhTerima;
     private Long idSemak;
     private Date tarikhSemak;
     private Long idSahkan;
     private Date tarikhSahkan;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private String noRujukanPtg;
     private String noRujukanPtd;
     private String noRujukanUpt;
     private Set tblpptpembatalans = new HashSet(0);
     private Set tblpptpenarikanbaliks = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractTblpptpermohonan() {
    }

	/** minimal constructor */
    public AbstractTblpptpermohonan(Long idFail) {
        this.idFail = idFail;
    }
    
    /** full constructor */
    public AbstractTblpptpermohonan(Long idFail, String noPermohonan, String noPerserahan, Date tarikhPermohonan, Long idAgensi, String flagPeruntukan, String flagSegera, String tujuan, String noRujukanSurat, Date tarikhSurat, Date tarikhKehendaki, String alamat1, String alamat2, String alamat3, String poskod, Long idNegeri, Long idMukim, Long idStatus, Long idDaerah, Date tarikhBoranga, Date tarikhBorangb, Date tarikhBorangc, Date tarikhBorangd, String perihalKawasan, Date tarikhTerima, Long idSemak, Date tarikhSemak, Long idSahkan, Date tarikhSahkan, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, String noRujukanPtg, String noRujukanPtd, String noRujukanUpt, Set tblpptpembatalans, Set tblpptpenarikanbaliks) {
        this.idFail = idFail;
        this.noPermohonan = noPermohonan;
        this.noPerserahan = noPerserahan;
        this.tarikhPermohonan = tarikhPermohonan;
        this.idAgensi = idAgensi;
        this.flagPeruntukan = flagPeruntukan;
        this.flagSegera = flagSegera;
        this.tujuan = tujuan;
        this.noRujukanSurat = noRujukanSurat;
        this.tarikhSurat = tarikhSurat;
        this.tarikhKehendaki = tarikhKehendaki;
        this.alamat1 = alamat1;
        this.alamat2 = alamat2;
        this.alamat3 = alamat3;
        this.poskod = poskod;
        this.idNegeri = idNegeri;
        this.idMukim = idMukim;
        this.idStatus = idStatus;
        this.idDaerah = idDaerah;
        this.tarikhBoranga = tarikhBoranga;
        this.tarikhBorangb = tarikhBorangb;
        this.tarikhBorangc = tarikhBorangc;
        this.tarikhBorangd = tarikhBorangd;
        this.perihalKawasan = perihalKawasan;
        this.tarikhTerima = tarikhTerima;
        this.idSemak = idSemak;
        this.tarikhSemak = tarikhSemak;
        this.idSahkan = idSahkan;
        this.tarikhSahkan = tarikhSahkan;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.noRujukanPtg = noRujukanPtg;
        this.noRujukanPtd = noRujukanPtd;
        this.noRujukanUpt = noRujukanUpt;
        this.tblpptpembatalans = tblpptpembatalans;
        this.tblpptpenarikanbaliks = tblpptpenarikanbaliks;
    }

   
    // Property accessors

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdFail() {
        return this.idFail;
    }
    
    public void setIdFail(Long idFail) {
        this.idFail = idFail;
    }

    public String getNoPermohonan() {
        return this.noPermohonan;
    }
    
    public void setNoPermohonan(String noPermohonan) {
        this.noPermohonan = noPermohonan;
    }

    public String getNoPerserahan() {
        return this.noPerserahan;
    }
    
    public void setNoPerserahan(String noPerserahan) {
        this.noPerserahan = noPerserahan;
    }

    public Date getTarikhPermohonan() {
        return this.tarikhPermohonan;
    }
    
    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public Long getIdAgensi() {
        return this.idAgensi;
    }
    
    public void setIdAgensi(Long idAgensi) {
        this.idAgensi = idAgensi;
    }

    public String getFlagPeruntukan() {
        return this.flagPeruntukan;
    }
    
    public void setFlagPeruntukan(String flagPeruntukan) {
        this.flagPeruntukan = flagPeruntukan;
    }

    public String getFlagSegera() {
        return this.flagSegera;
    }
    
    public void setFlagSegera(String flagSegera) {
        this.flagSegera = flagSegera;
    }

    public String getTujuan() {
        return this.tujuan;
    }
    
    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getNoRujukanSurat() {
        return this.noRujukanSurat;
    }
    
    public void setNoRujukanSurat(String noRujukanSurat) {
        this.noRujukanSurat = noRujukanSurat;
    }

    public Date getTarikhSurat() {
        return this.tarikhSurat;
    }
    
    public void setTarikhSurat(Date tarikhSurat) {
        this.tarikhSurat = tarikhSurat;
    }

    public Date getTarikhKehendaki() {
        return this.tarikhKehendaki;
    }
    
    public void setTarikhKehendaki(Date tarikhKehendaki) {
        this.tarikhKehendaki = tarikhKehendaki;
    }

    public String getAlamat1() {
        return this.alamat1;
    }
    
    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return this.alamat2;
    }
    
    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return this.alamat3;
    }
    
    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getPoskod() {
        return this.poskod;
    }
    
    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public Long getIdNegeri() {
        return this.idNegeri;
    }
    
    public void setIdNegeri(Long idNegeri) {
        this.idNegeri = idNegeri;
    }

    public Long getIdMukim() {
        return this.idMukim;
    }
    
    public void setIdMukim(Long idMukim) {
        this.idMukim = idMukim;
    }

    public Long getIdStatus() {
        return this.idStatus;
    }
    
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Long getIdDaerah() {
        return this.idDaerah;
    }
    
    public void setIdDaerah(Long idDaerah) {
        this.idDaerah = idDaerah;
    }

    public Date getTarikhBoranga() {
        return this.tarikhBoranga;
    }
    
    public void setTarikhBoranga(Date tarikhBoranga) {
        this.tarikhBoranga = tarikhBoranga;
    }

    public Date getTarikhBorangb() {
        return this.tarikhBorangb;
    }
    
    public void setTarikhBorangb(Date tarikhBorangb) {
        this.tarikhBorangb = tarikhBorangb;
    }

    public Date getTarikhBorangc() {
        return this.tarikhBorangc;
    }
    
    public void setTarikhBorangc(Date tarikhBorangc) {
        this.tarikhBorangc = tarikhBorangc;
    }

    public Date getTarikhBorangd() {
        return this.tarikhBorangd;
    }
    
    public void setTarikhBorangd(Date tarikhBorangd) {
        this.tarikhBorangd = tarikhBorangd;
    }

    public String getPerihalKawasan() {
        return this.perihalKawasan;
    }
    
    public void setPerihalKawasan(String perihalKawasan) {
        this.perihalKawasan = perihalKawasan;
    }

    public Date getTarikhTerima() {
        return this.tarikhTerima;
    }
    
    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public Long getIdSemak() {
        return this.idSemak;
    }
    
    public void setIdSemak(Long idSemak) {
        this.idSemak = idSemak;
    }

    public Date getTarikhSemak() {
        return this.tarikhSemak;
    }
    
    public void setTarikhSemak(Date tarikhSemak) {
        this.tarikhSemak = tarikhSemak;
    }

    public Long getIdSahkan() {
        return this.idSahkan;
    }
    
    public void setIdSahkan(Long idSahkan) {
        this.idSahkan = idSahkan;
    }

    public Date getTarikhSahkan() {
        return this.tarikhSahkan;
    }
    
    public void setTarikhSahkan(Date tarikhSahkan) {
        this.tarikhSahkan = tarikhSahkan;
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

    public String getNoRujukanPtg() {
        return this.noRujukanPtg;
    }
    
    public void setNoRujukanPtg(String noRujukanPtg) {
        this.noRujukanPtg = noRujukanPtg;
    }

    public String getNoRujukanPtd() {
        return this.noRujukanPtd;
    }
    
    public void setNoRujukanPtd(String noRujukanPtd) {
        this.noRujukanPtd = noRujukanPtd;
    }

    public String getNoRujukanUpt() {
        return this.noRujukanUpt;
    }
    
    public void setNoRujukanUpt(String noRujukanUpt) {
        this.noRujukanUpt = noRujukanUpt;
    }

    public Set getTblpptpembatalans() {
        return this.tblpptpembatalans;
    }
    
    public void setTblpptpembatalans(Set tblpptpembatalans) {
        this.tblpptpembatalans = tblpptpembatalans;
    }

    public Set getTblpptpenarikanbaliks() {
        return this.tblpptpenarikanbaliks;
    }
    
    public void setTblpptpenarikanbaliks(Set tblpptpenarikanbaliks) {
        this.tblpptpenarikanbaliks = tblpptpenarikanbaliks;
    }
   








}