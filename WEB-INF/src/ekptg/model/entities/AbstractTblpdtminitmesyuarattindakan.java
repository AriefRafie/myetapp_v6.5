package ekptg.model.entities;


import java.util.Date;

/**
 * AbstractTblpdtminitmesyuarattindakan entity provides the base persistence
 * definition of the Tblpdtminitmesyuarattindakan entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpdtminitmesyuarattindakan implements
		java.io.Serializable {

	// Fields

	private Long idTindakan;
	private Long idMinitmesyuaratpara;
	private String pihakBertindak;
	private String idMasuk;
	private Date tarikhMasuk;
	private String idKemaskini;
	private Date tarikhKemaskini;
        private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpdtminitmesyuarattindakan() {
	}

	/** full constructor */
	public AbstractTblpdtminitmesyuarattindakan(
			Long idMinitmesyuaratpara,
			String pihakBertindak, String idMasuk, Date tarikhMasuk,
			String idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idMinitmesyuaratpara = idMinitmesyuaratpara;
		this.pihakBertindak = pihakBertindak;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.idDb = idDb;
	}

	// Property accessors

	public Long getIdTindakan() {
		return this.idTindakan;
	}

	public void setIdTindakan(Long idTindakan) {
		this.idTindakan = idTindakan;
	}

	public Long getIdMinitmesyuaratpara() {
		return this.idMinitmesyuaratpara;
	}

	public void setIdMinitmesyuaratpara(
			Long idMinitmesyuaratpara) {
		this.idMinitmesyuaratpara = idMinitmesyuaratpara;
	}

	public String getPihakBertindak() {
		return this.pihakBertindak;
	}

	public void setPihakBertindak(String pihakBertindak) {
		this.pihakBertindak = pihakBertindak;
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
}