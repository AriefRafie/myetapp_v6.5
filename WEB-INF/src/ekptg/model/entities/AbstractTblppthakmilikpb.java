package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblppthakmilikpb entity provides the base persistence definition of the Tblppthakmilikpb entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppthakmilikpb  implements java.io.Serializable {


    // Fields    

     private Long idHakmilikpb;
     private Long idPihakberkepentingan;
     private Long idHakmilik;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblppthakmilikpb() {
    }

    
    /** full constructor */
    public AbstractTblppthakmilikpb(Long idPihakberkepentingan, Long idHakmilik, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.idPihakberkepentingan = idPihakberkepentingan;
        this.idHakmilik = idHakmilik;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdHakmilikpb() {
        return this.idHakmilikpb;
    }
    
    public void setIdHakmilikpb(Long idHakmilikpb) {
        this.idHakmilikpb = idHakmilikpb;
    }

    public Long getIdPihakberkepentingan() {
        return this.idPihakberkepentingan;
    }
    
    public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
        this.idPihakberkepentingan = idPihakberkepentingan;
    }

    public Long getIdHakmilik() {
        return this.idHakmilik;
    }
    
    public void setIdHakmilik(Long idHakmilik) {
        this.idHakmilik = idHakmilik;
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