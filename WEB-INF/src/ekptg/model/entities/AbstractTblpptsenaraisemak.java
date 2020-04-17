package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblpptsenaraisemak entity provides the base persistence definition of the Tblpptsenaraisemak entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptsenaraisemak  implements java.io.Serializable {


    // Fields    

     private Long idSenaraisemak;
     private Long idPermohonan;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;
     private String semak1;
     private String semak2;
     private String semak3;
     private String semak4;
     private String semak5;
     private String semak6;
     private String semak7;


    // Constructors

    /** default constructor */
    public AbstractTblpptsenaraisemak() {
    }

    
    /** full constructor */
    public AbstractTblpptsenaraisemak(Long idPermohonan, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb, String semak1, String semak2, String semak3, String semak4, String semak5, String semak6, String semak7) {
        this.idPermohonan = idPermohonan;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
        this.semak1 = semak1;
        this.semak2 = semak2;
        this.semak3 = semak3;
        this.semak4 = semak4;
        this.semak5 = semak5;
        this.semak6 = semak6;
        this.semak7 = semak7;
    }

   
    // Property accessors

    public Long getIdSenaraisemak() {
        return this.idSenaraisemak;
    }
    
    public void setIdSenaraisemak(Long idSenaraisemak) {
        this.idSenaraisemak = idSenaraisemak;
    }

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getSemak1() {
        return this.semak1;
    }
    
    public void setSemak1(String semak1) {
        this.semak1 = semak1;
    }

    public String getSemak2() {
        return this.semak2;
    }
    
    public void setSemak2(String semak2) {
        this.semak2 = semak2;
    }

    public String getSemak3() {
        return this.semak3;
    }
    
    public void setSemak3(String semak3) {
        this.semak3 = semak3;
    }

    public String getSemak4() {
        return this.semak4;
    }
    
    public void setSemak4(String semak4) {
        this.semak4 = semak4;
    }

    public String getSemak5() {
        return this.semak5;
    }
    
    public void setSemak5(String semak5) {
        this.semak5 = semak5;
    }

    public String getSemak6() {
        return this.semak6;
    }
    
    public void setSemak6(String semak6) {
        this.semak6 = semak6;
    }

    public String getSemak7() {
        return this.semak7;
    }
    
    public void setSemak7(String semak7) {
        this.semak7 = semak7;
    }
   








}