package ekptg.model.entities;

import java.sql.Blob;
import java.util.Date;


/**
 * Tblpptdokumen entity. @author MyEclipse Persistence Tools
 */
public class Tblpptdokumen extends AbstractTblpptdokumen implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblpptdokumen() {
    }

    
    /** full constructor */
    public Tblpptdokumen(String tajuk, String keterangan, String namaFail, Blob content, Date tarikhMasuk, Long idMasuk, Long idKemaskini, Date tarikhKemaskini, String jenisMime, String noRujukan, Long idPermohonan, Long idPembatalan, Long idPenarikanbalik, Long idBantahan, Long idDb) {
        super(tajuk, keterangan, namaFail, content, tarikhMasuk, idMasuk, idKemaskini, tarikhKemaskini, jenisMime, noRujukan, idPermohonan, idPembatalan, idPenarikanbalik, idBantahan, idDb);        
    }
   
}
