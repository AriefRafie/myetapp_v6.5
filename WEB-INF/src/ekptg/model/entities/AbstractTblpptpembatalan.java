package ekptg.model.entities;

import java.util.Date;


/**
 * AbstractTblpptpembatalan entity provides the base persistence definition of the Tblpptpembatalan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptpembatalan  implements java.io.Serializable {


    // Fields    

     private Long idPembatalan;
     private Tblpptpermohonan tblpptpermohonan;
     private Date tarikhPembatalan;
     private String noPembatalan;
     private Long jenisPembatalan;
     private String sebabPembatalan;
     private Long idStatus;
     private Date tarikhSurat;
     private Date tarikhTerimaSurat;
     private Date tarikhKuatkuasa;
     private String noRujukanSurat;
     private Long idMasuk;
     private Date tarikhMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblpptpembatalan() {
    }

	/** minimal constructor */
    public AbstractTblpptpembatalan(Tblpptpermohonan tblpptpermohonan) {
        this.tblpptpermohonan = tblpptpermohonan;
    }
    
    /** full constructor */
    public AbstractTblpptpembatalan(Tblpptpermohonan tblpptpermohonan, Date tarikhPembatalan, String noPembatalan, Long jenisPembatalan, String sebabPembatalan, Long idStatus, Date tarikhSurat, Date tarikhTerimaSurat, Date tarikhKuatkuasa, String noRujukanSurat, Long idMasuk, Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
        this.tblpptpermohonan = tblpptpermohonan;
        this.tarikhPembatalan = tarikhPembatalan;
        this.noPembatalan = noPembatalan;
        this.jenisPembatalan = jenisPembatalan;
        this.sebabPembatalan = sebabPembatalan;
        this.idStatus = idStatus;
        this.tarikhSurat = tarikhSurat;
        this.tarikhTerimaSurat = tarikhTerimaSurat;
        this.tarikhKuatkuasa = tarikhKuatkuasa;
        this.noRujukanSurat = noRujukanSurat;
        this.idMasuk = idMasuk;
        this.tarikhMasuk = tarikhMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdPembatalan() {
        return this.idPembatalan;
    }
    
    public void setIdPembatalan(Long idPembatalan) {
        this.idPembatalan = idPembatalan;
    }

    public Tblpptpermohonan getTblpptpermohonan() {
        return this.tblpptpermohonan;
    }
    
    public void setTblpptpermohonan(Tblpptpermohonan tblpptpermohonan) {
        this.tblpptpermohonan = tblpptpermohonan;
    }

    public Date getTarikhPembatalan() {
        return this.tarikhPembatalan;
    }
    
    public void setTarikhPembatalan(Date tarikhPembatalan) {
        this.tarikhPembatalan = tarikhPembatalan;
    }

    public String getNoPembatalan() {
        return this.noPembatalan;
    }
    
    public void setNoPembatalan(String noPembatalan) {
        this.noPembatalan = noPembatalan;
    }

    public Long getJenisPembatalan() {
        return this.jenisPembatalan;
    }
    
    public void setJenisPembatalan(Long jenisPembatalan) {
        this.jenisPembatalan = jenisPembatalan;
    }

    public String getSebabPembatalan() {
        return this.sebabPembatalan;
    }
    
    public void setSebabPembatalan(String sebabPembatalan) {
        this.sebabPembatalan = sebabPembatalan;
    }

    public Long getIdStatus() {
        return this.idStatus;
    }
    
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public Date getTarikhSurat() {
        return this.tarikhSurat;
    }
    
    public void setTarikhSurat(Date tarikhSurat) {
        this.tarikhSurat = tarikhSurat;
    }

    public Date getTarikhTerimaSurat() {
        return this.tarikhTerimaSurat;
    }
    
    public void setTarikhTerimaSurat(Date tarikhTerimaSurat) {
        this.tarikhTerimaSurat = tarikhTerimaSurat;
    }

    public Date getTarikhKuatkuasa() {
        return this.tarikhKuatkuasa;
    }
    
    public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
        this.tarikhKuatkuasa = tarikhKuatkuasa;
    }

    public String getNoRujukanSurat() {
        return this.noRujukanSurat;
    }
    
    public void setNoRujukanSurat(String noRujukanSurat) {
        this.noRujukanSurat = noRujukanSurat;
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