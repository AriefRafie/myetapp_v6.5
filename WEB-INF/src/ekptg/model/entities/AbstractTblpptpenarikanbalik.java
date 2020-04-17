package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblpptpenarikanbalik entity provides the base persistence definition of the Tblpptpenarikanbalik entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptpenarikanbalik  implements java.io.Serializable {


    // Fields    

     private Long idPenarikanbalik;
     private Tblpptpermohonan tblpptpermohonan;
     private String noPenarikanbalik;
     private Date tarikhPenarikanBalik;
     private Long jenisPenarikanbalik;
     private String sebabPenarikanbalik;
     private Long idStatus;
     private Date tarikhSurat;
     private Date tarikhTerima;
     private Date tarikhKuatkuasa;
     private String noRujukanSurat;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblpptpenarikanbalik() {
    }

	/** minimal constructor */
    public AbstractTblpptpenarikanbalik(Tblpptpermohonan tblpptpermohonan) {
        this.tblpptpermohonan = tblpptpermohonan;
    }
    
    /** full constructor */
    public AbstractTblpptpenarikanbalik(Tblpptpermohonan tblpptpermohonan, String noPenarikanbalik, Date tarikhPenarikanBalik, Long jenisPenarikanbalik, String sebabPenarikanbalik, Long idStatus, Date tarikhSurat, Date tarikhTerima, Date tarikhKuatkuasa, String noRujukanSurat, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.tblpptpermohonan = tblpptpermohonan;
        this.noPenarikanbalik = noPenarikanbalik;
        this.tarikhPenarikanBalik = tarikhPenarikanBalik;
        this.jenisPenarikanbalik = jenisPenarikanbalik;
        this.sebabPenarikanbalik = sebabPenarikanbalik;
        this.idStatus = idStatus;
        this.tarikhSurat = tarikhSurat;
        this.tarikhTerima = tarikhTerima;
        this.tarikhKuatkuasa = tarikhKuatkuasa;
        this.noRujukanSurat = noRujukanSurat;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdPenarikanbalik() {
        return this.idPenarikanbalik;
    }
    
    public void setIdPenarikanbalik(Long idPenarikanbalik) {
        this.idPenarikanbalik = idPenarikanbalik;
    }

    public Tblpptpermohonan getTblpptpermohonan() {
        return this.tblpptpermohonan;
    }
    
    public void setTblpptpermohonan(Tblpptpermohonan tblpptpermohonan) {
        this.tblpptpermohonan = tblpptpermohonan;
    }

    public String getNoPenarikanbalik() {
        return this.noPenarikanbalik;
    }
    
    public void setNoPenarikanbalik(String noPenarikanbalik) {
        this.noPenarikanbalik = noPenarikanbalik;
    }

    public Date getTarikhPenarikanBalik() {
        return this.tarikhPenarikanBalik;
    }
    
    public void setTarikhPenarikanBalik(Date tarikhPenarikanBalik) {
        this.tarikhPenarikanBalik = tarikhPenarikanBalik;
    }

    public Long getJenisPenarikanbalik() {
        return this.jenisPenarikanbalik;
    }
    
    public void setJenisPenarikanbalik(Long jenisPenarikanbalik) {
        this.jenisPenarikanbalik = jenisPenarikanbalik;
    }

    public String getSebabPenarikanbalik() {
        return this.sebabPenarikanbalik;
    }
    
    public void setSebabPenarikanbalik(String sebabPenarikanbalik) {
        this.sebabPenarikanbalik = sebabPenarikanbalik;
    }

    public Long getIdStatus() {
        return this.idStatus;
    }
    
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Date getTarikhSurat() {
        return this.tarikhSurat;
    }
    
    public void setTarikhSurat(Date tarikhSurat) {
        this.tarikhSurat = tarikhSurat;
    }

    public Date getTarikhTerima() {
        return this.tarikhTerima;
    }
    
    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public Date getTarikhKuatkuasa() {
        return this.tarikhKuatkuasa;
    }
    
    public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
        this.tarikhKuatkuasa = tarikhKuatkuasa;
    }

    public String getNoRujukanSurat() {
        return this.noRujukanSurat;
    }
    
    public void setNoRujukanSurat(String noRujukanSurat) {
        this.noRujukanSurat = noRujukanSurat;
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