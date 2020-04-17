package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtmesyuarat entity provides the base persistence definition of
 * the Tblpdtmesyuarat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtmesyuarat implements java.io.Serializable {

	// Fields

	private Long idMesyuarat;
	private String bilMesyuarat;
	private String tajukMesyuarat;
	private String kategoriMesyuarat;
	private Date tarikhMesyuarat;
	private String masaMesyuaratDari;
	private Long idLokasi;
	private Long idFail;
	private Long idSeksyen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private Long idPegawai;
        private String catatan;
        private Long idDb;
        private String waktuMesyuaratDari;
        private String masaMesyuaratHingga;
        private String waktuMesyuaratHingga;
        private Long idStatus;
	private Set tblpdtminitmesyuarats = new HashSet(0);
	private Set tblpdtahlimesyuarats = new HashSet(0);
	private Set tblpdtversis = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtmesyuarat() {
	}

	/** minimal constructor */
	public AbstractTblpdtmesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

	/** full constructor */
	public AbstractTblpdtmesyuarat(Long idMesyuarat, String bilMesyuarat,
			String tajukMesyuarat, String kategoriMesyuarat,
			Date tarikhMesyuarat, String masaMesyuaratDari, Long idLokasi,
			Long idFail, Long idSeksyen, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,Long idPegawai,
			String catatan,Long idDb,String waktuMesyuaratDari,
                        String masaMesyuaratHingga, String waktuMesyuaratHingga, Long idStatus,
                        Set tblpdtminitmesyuarats, Set tblpdtahlimesyuarats,Set tblpdtversis) {
		this.idMesyuarat = idMesyuarat;
		this.bilMesyuarat = bilMesyuarat;
		this.tajukMesyuarat = tajukMesyuarat;
		this.kategoriMesyuarat = kategoriMesyuarat;
		this.tarikhMesyuarat = tarikhMesyuarat;
		this.masaMesyuaratDari = masaMesyuaratDari;
		this.idLokasi = idLokasi;
		this.idFail = idFail;
		this.idSeksyen = idSeksyen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.idPegawai = idPegawai;
                this.catatan = catatan;
                this.idDb = idDb;
                this.waktuMesyuaratDari = waktuMesyuaratDari;
                this.masaMesyuaratHingga = masaMesyuaratHingga;
                this.waktuMesyuaratHingga = waktuMesyuaratHingga;
                this.idStatus = idStatus;
		this.tblpdtminitmesyuarats = tblpdtminitmesyuarats;
		this.tblpdtahlimesyuarats = tblpdtahlimesyuarats;
		this.tblpdtversis = tblpdtversis;
	}

	// Property accessors

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

	public String getBilMesyuarat() {
		return this.bilMesyuarat;
	}

	public void setBilMesyuarat(String bilMesyuarat) {
		this.bilMesyuarat = bilMesyuarat;
	}

	public String getTajukMesyuarat() {
		return this.tajukMesyuarat;
	}

	public void setTajukMesyuarat(String tajukMesyuarat) {
		this.tajukMesyuarat = tajukMesyuarat;
	}

	public String getKategoriMesyuarat() {
		return this.kategoriMesyuarat;
	}

	public void setKategoriMesyuarat(String kategoriMesyuarat) {
		this.kategoriMesyuarat = kategoriMesyuarat;
	}

	public Date getTarikhMesyuarat() {
		return this.tarikhMesyuarat;
	}

	public void setTarikhMesyuarat(Date tarikhMesyuarat) {
		this.tarikhMesyuarat = tarikhMesyuarat;
	}

	public String getMasaMesyuaratDari() {
		return this.masaMesyuaratDari;
	}

	public void setMasaMesyuaratDari(String masaMesyuaratDari) {
		this.masaMesyuaratDari = masaMesyuaratDari;
	}

	public Long getIdLokasi() {
		return this.idLokasi;
	}

	public void setIdLokasi(Long idLokasi) {
		this.idLokasi = idLokasi;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Long getIdSeksyen() {
		return this.idSeksyen;
	}

	public void setIdSeksyen(Long idSeksyen) {
		this.idSeksyen = idSeksyen;
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
        
        public Long getIdPegawai() {
                return this.idPegawai;
        }
    
        public void setIdPegawai(Long idPegawai) {
                this.idPegawai = idPegawai;
        }
        public String getCatatan() {
                return this.catatan;
        }
    
        public void setCatatan(String catatan) {
                this.catatan =catatan;
        }
        public Long getIdDb() {
                return this.idDb;
        }
    
        public void setIdDb(Long idDb) {
                this.idDb = idDb;
        }
        public String getWaktuMesyuaratDari() {
                return this.waktuMesyuaratDari;
        }
    
        public void setWaktuMesyuaratDari(String waktuMesyuaratDari) {
                this.waktuMesyuaratDari = waktuMesyuaratDari;
        }
        public String getMasaMesyuaratHingga() {
                return this.masaMesyuaratHingga;
        }
    
        public void setMasaMesyuaratHingga(String masaMesyuaratHingga) {
                this.masaMesyuaratHingga = masaMesyuaratHingga;
        }
        public String getWaktuMesyuaratHingga() {
                return this.waktuMesyuaratHingga;
        }
    
        public void setWaktuMesyuaratHingga(String waktuMesyuaratHingga) {
                this.waktuMesyuaratHingga = waktuMesyuaratHingga;
        }
        public Long getIdStatus() {
                return this.idStatus;
        }
    
        public void setIdStatus(Long idStatus) {
                this.idStatus = idStatus;
        }
      
	public Set getTblpdtminitmesyuarats() {
		return this.tblpdtminitmesyuarats;
	}

	public void setTblpdtminitmesyuarats(Set tblpdtminitmesyuarats) {
		this.tblpdtminitmesyuarats = tblpdtminitmesyuarats;
	}

	public Set getTblpdtahlimesyuarats() {
		return this.tblpdtahlimesyuarats;
	}

	public void setTblpdtahlimesyuarats(Set tblpdtahlimesyuarats) {
		this.tblpdtahlimesyuarats = tblpdtahlimesyuarats;
	}

	public Set getTblpdtversis() {
		return this.tblpdtversis;
	}

	public void setTblpdtversis(Set tblpdtversis) {
		this.tblpdtversis = tblpdtversis;
	}

}