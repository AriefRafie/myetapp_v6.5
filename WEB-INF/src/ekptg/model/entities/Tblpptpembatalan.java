package ekptg.model.entities;

import java.util.Date;


/**
 * Tblpptpembatalan entity. @author MyEclipse Persistence Tools
 */
public class Tblpptpembatalan extends AbstractTblpptpembatalan implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblpptpembatalan() {
    }

	/** minimal constructor */
    public Tblpptpembatalan(Tblpptpermohonan tblpptpermohonan) {
        super(tblpptpermohonan);        
    }
    
    /** full constructor */
    public Tblpptpembatalan(Tblpptpermohonan tblpptpermohonan, Date tarikhPembatalan, String noPembatalan, Long jenisPembatalan, String sebabPembatalan, Long idStatus, Date tarikhSurat, Date tarikhTerimaSurat, Date tarikhKuatkuasa, String noRujukanSurat, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        super(tblpptpermohonan, tarikhPembatalan, noPembatalan, jenisPembatalan, sebabPembatalan, idStatus, tarikhSurat, tarikhTerimaSurat, tarikhKuatkuasa, noRujukanSurat, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);        
    }
   
}
