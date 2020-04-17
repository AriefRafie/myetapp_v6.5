package ekptg.model.entities;

import java.util.Date;


/**
 * Tblpptsenaraisemak entity. @author MyEclipse Persistence Tools
 */
public class Tblpptsenaraisemak extends AbstractTblpptsenaraisemak implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblpptsenaraisemak() {
    }

    
    /** full constructor */
    public Tblpptsenaraisemak(Long idPermohonan, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb, String semak1, String semak2, String semak3, String semak4, String semak5, String semak6, String semak7) {
        super(idPermohonan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb, semak1, semak2, semak3, semak4, semak5, semak6, semak7);        
    }
   
}
