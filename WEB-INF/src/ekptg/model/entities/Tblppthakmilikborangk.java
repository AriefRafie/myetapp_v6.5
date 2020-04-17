package ekptg.model.entities;

import java.util.Date;


/**
 * Tblppthakmilikborangk entity. @author MyEclipse Persistence Tools
 */
public class Tblppthakmilikborangk extends AbstractTblppthakmilikborangk implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblppthakmilikborangk() {
    }

    
    /** full constructor */
    public Tblppthakmilikborangk(Long idHakmilik, Long idBorangk, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        super(idHakmilik, idBorangk, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);        
    }
   
}
