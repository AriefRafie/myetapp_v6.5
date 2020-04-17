package ekptg.model.entities;

import java.util.Date;
import java.util.Set;


/**
 * Tblpptpermohonan entity. @author MyEclipse Persistence Tools
 */
public class Tblpptpermohonan extends AbstractTblpptpermohonan implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tblpptpermohonan() {
    }

	/** minimal constructor */
    public Tblpptpermohonan(Long idFail) {
        super(idFail);        
    }
    
    /** full constructor */
    public Tblpptpermohonan(Long idFail, String noPermohonan, String noPerserahan, Date tarikhPermohonan, Long idAgensi, String flagPeruntukan, String flagSegera, String tujuan, String noRujukanSurat, Date tarikhSurat, Date tarikhKehendaki, String alamat1, String alamat2, String alamat3, String poskod, Long idNegeri, Long idMukim, Long idStatus, Long idDaerah, Date tarikhBoranga, Date tarikhBorangb, Date tarikhBorangc, Date tarikhBorangd, String perihalKawasan, Date tarikhTerima, Long idSemak, Date tarikhSemak, Long idSahkan, Date tarikhSahkan, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, String noRujukanPtg, String noRujukanPtd, String noRujukanUpt, Set tblpptpembatalans, Set tblpptpenarikanbaliks) {
        super(idFail, noPermohonan, noPerserahan, tarikhPermohonan, idAgensi, flagPeruntukan, flagSegera, tujuan, noRujukanSurat, tarikhSurat, tarikhKehendaki, alamat1, alamat2, alamat3, poskod, idNegeri, idMukim, idStatus, idDaerah, tarikhBoranga, tarikhBorangb, tarikhBorangc, tarikhBorangd, perihalKawasan, tarikhTerima, idSemak, tarikhSemak, idSahkan, tarikhSahkan, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, noRujukanPtg, noRujukanPtd, noRujukanUpt, tblpptpembatalans, tblpptpenarikanbaliks);        
    }
   
}
