package ekptg.model.entities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;


/**
 * AbstractTblpptdokumen entity provides the base persistence definition of the Tblpptdokumen entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptdokumen  implements java.io.Serializable {


    // Fields    

     private Long idDokumen;
     private String tajuk;
     private String keterangan;
     private String namaFail;
     private Blob content;
     private Date tarikhMasuk;
     private Long idMasuk;
     private Long idKemaskini;
     private Date tarikhKemaskini;
     private String jenisMime;
     private String noRujukan;
     private Long idPermohonan;
     private Long idPembatalan;
     private Long idPenarikanbalik;
     private Long idBantahan;
     private Long idDb;


    // Constructors

    /** default constructor */
    public AbstractTblpptdokumen() {
    }

    
    /** full constructor */
    public AbstractTblpptdokumen(String tajuk, String keterangan, String namaFail, Blob content, Date tarikhMasuk, Long idMasuk, Long idKemaskini, Date tarikhKemaskini, String jenisMime, String noRujukan, Long idPermohonan, Long idPembatalan, Long idPenarikanbalik, Long idBantahan, Long idDb) {
        this.tajuk = tajuk;
        this.keterangan = keterangan;
        this.namaFail = namaFail;
        this.content = content;
        this.tarikhMasuk = tarikhMasuk;
        this.idMasuk = idMasuk;
        this.idKemaskini = idKemaskini;
        this.tarikhKemaskini = tarikhKemaskini;
        this.jenisMime = jenisMime;
        this.noRujukan = noRujukan;
        this.idPermohonan = idPermohonan;
        this.idPembatalan = idPembatalan;
        this.idPenarikanbalik = idPenarikanbalik;
        this.idBantahan = idBantahan;
        this.idDb = idDb;
    }

   
    // Property accessors

    public Long getIdDokumen() {
        return this.idDokumen;
    }
    
    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getTajuk() {
        return this.tajuk;
    }
    
    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getKeterangan() {
        return this.keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaFail() {
        return this.namaFail;
    }
    
    public void setNamaFail(String namaFail) {
        this.namaFail = namaFail;
    }


    public Blob getContent() {
        return this.content;
    }

//    public void setContent(Blob content) {
//        this.content = content;
//    }
//    
//    public InputStream getDistroStream() throws SQLException
//    {
//        if (getContent() == null)
//        return null;
//        return getContent().getBinaryStream();
//    }
//    
//    public void setContent( InputStream sourceStream )throws IOException
//    {
//        setContent( Hibernate.createBlob( sourceStream ) );
//    }
    
    public void setContent(Blob content) {
        this.content = content;
    }
    
    public InputStream getDistroStream() throws SQLException
    {
        if (getContent() == null)
        return null;
        return getContent().getBinaryStream();
    }
    
    public void setContent( InputStream sourceStream )throws IOException
    {
        setContent( Hibernate.createBlob( sourceStream ) );
    }

//    public Blob getContent() {
//        return this.content;
//    }
//    
//    public void setContent(Blob content) {
//        this.content = content;
//    }

    public Date getTarikhMasuk() {
        return this.tarikhMasuk;
    }
    
    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public Long getIdMasuk() {
        return this.idMasuk;
    }
    
    public void setIdMasuk(Long idMasuk) {
        this.idMasuk = idMasuk;
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

    public String getJenisMime() {
        return this.jenisMime;
    }
    
    public void setJenisMime(String jenisMime) {
        this.jenisMime = jenisMime;
    }

    public String getNoRujukan() {
        return this.noRujukan;
    }
    
    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Long getIdPermohonan() {
        return this.idPermohonan;
    }
    
    public void setIdPermohonan(Long idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPembatalan() {
        return this.idPembatalan;
    }
    
    public void setIdPembatalan(Long idPembatalan) {
        this.idPembatalan = idPembatalan;
    }

    public Long getIdPenarikanbalik() {
        return this.idPenarikanbalik;
    }
    
    public void setIdPenarikanbalik(Long idPenarikanbalik) {
        this.idPenarikanbalik = idPenarikanbalik;
    }

    public Long getIdBantahan() {
        return this.idBantahan;
    }
    
    public void setIdBantahan(Long idBantahan) {
        this.idBantahan = idBantahan;
    }

    public Long getIdDb() {
        return this.idDb;
    }
    
    public void setIdDb(Long idDb) {
        this.idDb = idDb;
    }
   








}