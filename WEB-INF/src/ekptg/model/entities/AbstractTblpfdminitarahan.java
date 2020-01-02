package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdminitarahan entity provides the base persistence definition of
 * the Tblpfdminitarahan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdminitarahan implements java.io.Serializable {

	// Fields

	private Long idMinitarahan;
	private String minitArahan;
        private Long idPegawaiYgmengarah;
        private Long idPegawaiYgmenerima;
	private Date tarikhMinitArahan;
	private Long idDokumen;
        private Long idDb;
        private String statusTindakan;

	// Constructors

	/** default constructor */
	public AbstractTblpfdminitarahan() {
	}

	/** minimal constructor */
	public AbstractTblpfdminitarahan(Long idMinitarahan) {
		this.idMinitarahan = idMinitarahan;
	}

	/** full constructor */
	public AbstractTblpfdminitarahan(Long idMinitarahan, String minitArahan,
			Long idPegawaiYgmengarah, Long idPegawaiYgmenerima, Date tarikhMinitArahan,
                        Long idDokumen, Long idDb, String statusTindakan) {
		this.idMinitarahan = idMinitarahan;
		this.minitArahan = minitArahan;
                this.idPegawaiYgmengarah = idPegawaiYgmengarah;
                this.idPegawaiYgmenerima = idPegawaiYgmenerima;
		this.tarikhMinitArahan = tarikhMinitArahan;
		this.idDokumen = idDokumen;
                this.idDb = idDb;
                this.statusTindakan = statusTindakan;
	}

	// Property accessors

	public Long getIdMinitarahan() {
		return this.idMinitarahan;
	}

	public void setIdMinitarahan(Long idMinitarahan) {
		this.idMinitarahan = idMinitarahan;
	}

	public String getMinitArahan() {
		return this.minitArahan;
	}

	public void setMinitArahan(String minitArahan) {
		this.minitArahan = minitArahan;
	}
        
         public Long getIdPegawaiYgmengarah() {
                return this.idPegawaiYgmengarah;
        }

        public void setIdPegawaiYgmengarah(Long idPegawaiYgmengarah) {
                this.idPegawaiYgmengarah = idPegawaiYgmengarah;
        }

        public Long getIdPegawaiYgmenerima() {
                return this.idPegawaiYgmenerima;
        }

        public void setIdPegawaiYgmenerima(Long idPegawaiYgmenerima) {
                this.idPegawaiYgmenerima = idPegawaiYgmenerima;
        }

	public Date getTarikhMinitArahan() {
		return this.tarikhMinitArahan;
	}

	public void setTarikhMinitArahan(Date tarikhMinitArahan) {
		this.tarikhMinitArahan = tarikhMinitArahan;
	}

	public Long getIdDokumen() {
		return this.idDokumen;
	}

	public void setIdDokumen(Long idDokumen) {
		this.idDokumen = idDokumen;
	}
        public Long getIdDb() {
                return this.idDb;
        }
    
        public void setIdDb(Long idDb) {
                this.idDb = idDb;
        }
        public String getStatusTindakan() {
                return this.statusTindakan;
        }
    
        public void setStatusTindakan(String statusTindakan) {
                this.statusTindakan = statusTindakan;
        }
        


}