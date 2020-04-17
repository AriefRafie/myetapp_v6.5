package ekptg.model.entities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

/**
 * AbstractTblpfdrujlampiran entity provides the base persistence definition of
 * the Tblpfdrujlampiran entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdrujlampiran implements java.io.Serializable {

	// Fields

	private Long idLampiran;
	private Long idDokumen;
	private Blob content;
	private String namaFail;
	private String jenisMime;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdrujlampiran() {
	}

	/** full constructor */
	public AbstractTblpfdrujlampiran(Long idDokumen, Blob content,
			String namaFail, String jenisMime, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idDokumen = idDokumen;
		this.content = content;
		this.namaFail = namaFail;
		this.jenisMime = jenisMime;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdLampiran() {
		return this.idLampiran;
	}

	public void setIdLampiran(Long idLampiran) {
		this.idLampiran = idLampiran;
	}

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}
    
        public Blob getContent() {
                return this.content;
        }
        
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
        
	public String getNamaFail() {
		return this.namaFail;
	}

	public void setNamaFail(String namaFail) {
		this.namaFail = namaFail;
	}

	public String getJenisMime() {
		return this.jenisMime;
	}

	public void setJenisMime(String jenisMime) {
		this.jenisMime = jenisMime;
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

}