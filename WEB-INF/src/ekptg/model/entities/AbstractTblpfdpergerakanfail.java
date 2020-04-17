package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdpergerakanfail entity provides the base persistence definition
 * of the Tblpfdpergerakanfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdpergerakanfail implements
		java.io.Serializable {

	// Fields

	private Long idPergerakanFail;
	private Long idFail;
	private Date tarikhFailMasuk;
	private Date tarikhFailKeluar;
	private Long idPegawaipenerima;
	private Long idPegawaipenghantar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Date tempohSdpDari;
        private Date tempohSdpHingga;
	private String catatan;
	private Long idStatus;
        private Long idDb;
	

	// Constructors

	/** default constructor */
	public AbstractTblpfdpergerakanfail() {
	}

	/** minimal constructor */
	public AbstractTblpfdpergerakanfail(Long idPergerakanFail,
			Long idFail) {
		this.idPergerakanFail = idPergerakanFail;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpfdpergerakanfail(Long idPergerakanFail,
			Long idFail, Date tarikhFailMasuk, Date tarikhFailKeluar,
			Long idPegawaipenerima, Long idPegawaipenghantar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Date tempohSdpDari,Date tempohSdpHingga, String catatan,
			Long idStatus, Long idDb) {
		this.idPergerakanFail = idPergerakanFail;
		this.idFail = idFail;
		this.tarikhFailMasuk = tarikhFailMasuk;
		this.tarikhFailKeluar = tarikhFailKeluar;
		this.idPegawaipenerima = idPegawaipenerima;
		this.idPegawaipenghantar = idPegawaipenghantar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tempohSdpDari = tempohSdpDari;
                this.tempohSdpHingga = tempohSdpHingga;
		this.catatan = catatan;
		this.idStatus = idStatus;
                this.idDb = idDb;
		
	}

	// Property accessors

	public Long getIdPergerakanFail() {
		return this.idPergerakanFail;
	}

	public void setIdPergerakanFail(Long idPergerakanFail) {
		this.idPergerakanFail = idPergerakanFail;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Date getTarikhFailMasuk() {
		return this.tarikhFailMasuk;
	}

	public void setTarikhFailMasuk(Date tarikhFailMasuk) {
		this.tarikhFailMasuk = tarikhFailMasuk;
	}

	public Date getTarikhFailKeluar() {
		return this.tarikhFailKeluar;
	}

	public void setTarikhFailKeluar(Date tarikhFailKeluar) {
		this.tarikhFailKeluar = tarikhFailKeluar;
	}

	public Long getIdPegawaipenerima() {
		return this.idPegawaipenerima;
	}

	public void setIdPegawaipenerima(Long idPegawaipenerima) {
		this.idPegawaipenerima = idPegawaipenerima;
	}

	public Long getIdPegawaipenghantar() {
		return this.idPegawaipenghantar;
	}

	public void setIdPegawaipenghantar(Long idPegawaipenghantar) {
		this.idPegawaipenghantar = idPegawaipenghantar;
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

	public Date getTempohSdpDari() {
		return this.tempohSdpDari;
	}

	public void setTempohSdpDari(Date tempohSdpDari) {
		this.tempohSdpDari = tempohSdpDari;
	}
        
        public Date getTempohSdpHingga() {
                return this.tempohSdpHingga;
        }

        public void setTempohSdpHingga(Date tempohSdpHingga) {
                this.tempohSdpHingga = tempohSdpHingga;
        }

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
        public Long getIdDb() {
                return this.idDb;
        }
    
        public void setIdDb(Long idDb) {
                this.idDb = idDb;
        }

	
}