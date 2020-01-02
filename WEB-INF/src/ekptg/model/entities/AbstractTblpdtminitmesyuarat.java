package ekptg.model.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtminitmesyuarat entity provides the base persistence definition
 * of the Tblpdtminitmesyuarat entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtminitmesyuarat implements
		java.io.Serializable {

	// Fields

	private Long idMinitmesyuarat;
	private Long idAgendamesyuarat;
	private String tajukMinit;
	private String namaPegawai;
	private String catatan;
	private String idMasuk;
	private Date tarikhMasuk;
	private String idKemaskini;
	private Date tarikhKemaskini;
        private Long idDb;
        private Long idMesyuarat;
	private Set tblpdtminitmesyuaratparas = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtminitmesyuarat() {
	}

	/** full constructor */
	public AbstractTblpdtminitmesyuarat(Long idAgendamesyuarat,
			String tajukMinit, String namaPegawai, String catatan,
			String idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarikhKemaskini, Long idDb, Long idMesyuarat,Set tblpdtminitmesyuaratparas) {
		this.idAgendamesyuarat = idAgendamesyuarat;
		this.tajukMinit = tajukMinit;
		this.namaPegawai = namaPegawai;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.idDb = idDb;
                this.idMesyuarat = idMesyuarat;
		this.tblpdtminitmesyuaratparas = tblpdtminitmesyuaratparas;
	}

	// Property accessors

	public Long getIdMinitmesyuarat() {
		return this.idMinitmesyuarat;
	}

	public void setIdMinitmesyuarat(Long idMinitmesyuarat) {
		this.idMinitmesyuarat = idMinitmesyuarat;
	}

	public Long getIdAgendamesyuarat() {
		return this.idAgendamesyuarat;
	}

	public void setIdAgendamesyuarat(Long idAgendamesyuarat) {
		this.idAgendamesyuarat = idAgendamesyuarat;
	}

	public String getTajukMinit() {
		return this.tajukMinit;
	}

	public void setTajukMinit(String tajukMinit) {
		this.tajukMinit = tajukMinit;
	}

	public String getNamaPegawai() {
		return this.namaPegawai;
	}

	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblpdtminitmesyuaratparas() {
		return this.tblpdtminitmesyuaratparas;
	}

	public void setTblpdtminitmesyuaratparas(Set tblpdtminitmesyuaratparas) {
		this.tblpdtminitmesyuaratparas = tblpdtminitmesyuaratparas;
	}

}