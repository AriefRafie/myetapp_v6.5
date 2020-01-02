package ekptg.model.entities;

import java.util.Date;


/**
 * Tblpptpenarikanbalik entity. @author MyEclipse Persistence Tools
 */
public class Tblpptpenarikanbalik extends AbstractTblpptpenarikanbalik implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblpptpenarikanbalik() {
    }

	/** minimal constructor */
    public Tblpptpenarikanbalik(Tblpptpermohonan tblpptpermohonan) {
        super(tblpptpermohonan);        
    }
    
    /** full constructor */
    public Tblpptpenarikanbalik(Tblpptpermohonan tblpptpermohonan, String noPenarikanbalik, Date tarikhPenarikanBalik, Long jenisPenarikanbalik, String sebabPenarikanbalik, Long idStatus, Date tarikhSurat, Date tarikhTerima, Date tarikhKuatkuasa, String noRujukanSurat, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        super(tblpptpermohonan, noPenarikanbalik, tarikhPenarikanBalik, jenisPenarikanbalik, sebabPenarikanbalik, idStatus, tarikhSurat, tarikhTerima, tarikhKuatkuasa, noRujukanSurat, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);        
    }
   
}
