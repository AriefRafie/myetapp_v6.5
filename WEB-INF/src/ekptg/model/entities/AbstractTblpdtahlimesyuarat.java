package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpdtahlimesyuarat entity provides the base persistence definition
 * of the Tblpdtahlimesyuarat entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtahlimesyuarat implements
		java.io.Serializable {

	// Fields

	private Long idAhlimesyuarat;
	private Long idPegawai;
	private Long idMesyuarat;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String emel;
	private String noTel;
	private String noFaks;
	private Long idDb;
        private String namaAhlimesyuarat;
        private String jawatan;
        private String flagPengerusi;
        private Long idSeksyen;
	// Constructors

	/** default constructor */
	public AbstractTblpdtahlimesyuarat() {
	}

	/** full constructor */
	public AbstractTblpdtahlimesyuarat(Long idPegawai,
			Long idMesyuarat, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			String emel, String noTel, String noFaks, Long idDb,
                        String namaAhlimesyuarat, String jawatan, String flagPengerusi, Long idSeksyen) {
		this.idPegawai = idPegawai;
		this.idMesyuarat = idMesyuarat;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.emel = emel;
		this.noTel = noTel;
		this.noFaks = noFaks;
		this.idDb = idDb;
                this.namaAhlimesyuarat = namaAhlimesyuarat;
                this.jawatan = jawatan;
                this.flagPengerusi = flagPengerusi;
                this.idSeksyen = idSeksyen;
                
	}

	// Property accessors

	public Long getIdAhlimesyuarat() {
		return this.idAhlimesyuarat;
	}

	public void setIdAhlimesyuarat(Long idAhlimesyuarat) {
		this.idAhlimesyuarat = idAhlimesyuarat;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
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

	public String getEmel() {
		return this.emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public String getNoTel() {
		return this.noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public String getNoFaks() {
		return this.noFaks;
	}

	public void setNoFaks(String noFaks) {
		this.noFaks = noFaks;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}
        public String getNamaAhlimesyuarat() {
                return this.namaAhlimesyuarat;
        }
    
        public void setNamaAhlimesyuarat(String namaAhlimesyuarat) {
                this.namaAhlimesyuarat = namaAhlimesyuarat;
        }
        public String getJawatan() {
                return this.jawatan;
        }
    
        public void setJawatan(String jawatan) {
                this.jawatan = jawatan;
        }
        public String getFlagPengerusi() {
                return this.flagPengerusi;
        }
    
        public void setFlagPengerusi(String flagPengerusi) {
                this.flagPengerusi = flagPengerusi;
        }
        public Long getIdSeksyen() {
                return this.idSeksyen;
        }
    
        public void setIdSeksyen(Long idSeksyen) {
                this.idSeksyen = idSeksyen;
        }


}