package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblppthakmilikborangk entity provides the base persistence definition of the Tblppthakmilikborangk entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppthakmilikborangk  implements java.io.Serializable {


    // Fields    

     private Long idHakmilikborangk;
     private Long idHakmilik;
     private Long idBorangk;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblppthakmilikborangk() {
    }

    
    /** full constructor */
    public AbstractTblppthakmilikborangk(Long idHakmilik, Long idBorangk, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.idHakmilik = idHakmilik;
        this.idBorangk = idBorangk;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdHakmilikborangk() {
        return this.idHakmilikborangk;
    }
    
    public void setIdHakmilikborangk(Long idHakmilikborangk) {
        this.idHakmilikborangk = idHakmilikborangk;
    }

    public Long getIdHakmilik() {
        return this.idHakmilik;
    }
    
    public void setIdHakmilik(Long idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Long getIdBorangk() {
        return this.idBorangk;
    }
    
    public void setIdBorangk(Long idBorangk) {
        this.idBorangk = idBorangk;
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