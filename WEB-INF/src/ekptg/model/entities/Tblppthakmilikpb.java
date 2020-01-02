package ekptg.model.entities;

import java.util.Date;


/**
 * Tblppthakmilikpb entity. @author MyEclipse Persistence Tools
 */
public class Tblppthakmilikpb extends AbstractTblppthakmilikpb implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblppthakmilikpb() {
    }

    
    /** full constructor */
    public Tblppthakmilikpb(Long idPihakberkepentingan, Long idHakmilik, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        super(idPihakberkepentingan, idHakmilik, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);        
    }
   
}
