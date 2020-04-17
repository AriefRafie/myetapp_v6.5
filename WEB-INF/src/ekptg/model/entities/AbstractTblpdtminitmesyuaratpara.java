package ekptg.model.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtminitmesyuaratpara entity provides the base persistence
 * definition of the Tblpdtminitmesyuaratpara entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpdtminitmesyuaratpara implements
		java.io.Serializable {

	// Fields

	private Long idMinitmesyuaratpara;
	private Long idMinitmesyuarat;
	private String para;
	private String idMasuk;
	private Date tarikhMasuk;
	private String idKemaskini;
	private Date tarikhKemaskini;
        private Long idDb;
        private Long idMesyuarat;
        private String pihakBertindak;
        private String maklumbalas;
	private Set tblpdtminitmesyuarattindakans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtminitmesyuaratpara() {
	}

	/** full constructor */
	public AbstractTblpdtminitmesyuaratpara(
			Long idMinitmesyuarat, String para,
			String idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarikhKemaskini, Long idDb, Long idMesyuarat, String pihakBertindak,
                        String maklumbalas,Set tblpdtminitmesyuarattindakans) {
		this.idMinitmesyuarat = idMinitmesyuarat;
		this.para = para;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.idDb = idDb;
                this.idMesyuarat = idMesyuarat;
                this.pihakBertindak = pihakBertindak;
                this.maklumbalas = maklumbalas;
		this.tblpdtminitmesyuarattindakans = tblpdtminitmesyuarattindakans;
	}

	// Property accessors

	public Long getIdMinitmesyuaratpara() {
		return this.idMinitmesyuaratpara;
	}

	public void setIdMinitmesyuaratpara(Long idMinitmesyuaratpara) {
		this.idMinitmesyuaratpara = idMinitmesyuaratpara;
	}

	public Long getIdMinitmesyuarat() {
		return this.idMinitmesyuarat;
	}

	public void setIdMinitmesyuarat(
			Long idMinitmesyuarat) {
		this.idMinitmesyuarat = idMinitmesyuarat;
	}

	public String getPara() {
		return this.para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public String getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(String idKemaskini) {
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
        public Long getIdMesyuarat() {
                return this.idMesyuarat;
        }
        public void setIdMesyuarat(Long idMesyuarat) {
                this.idMesyuarat = idMesyuarat;
        }
        public String getPihakBertindak() {
                return this.pihakBertindak;
        }
    
        public void setPihakBertindak(String pihakBertindak) {
                this.pihakBertindak = pihakBertindak;
        }

       
        public String getMaklumbalas() {
                return this.maklumbalas;
        }
        
        public void setMaklumbalas(String maklumbalas) {
                this.maklumbalas = maklumbalas;
        }
	public Set getTblpdtminitmesyuarattindakans() {
		return this.tblpdtminitmesyuarattindakans;
	}

	public void setTblpdtminitmesyuarattindakans(
			Set tblpdtminitmesyuarattindakans) {
		this.tblpdtminitmesyuarattindakans = tblpdtminitmesyuarattindakans;
	}

}