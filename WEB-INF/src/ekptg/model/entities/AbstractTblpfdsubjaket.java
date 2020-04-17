package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdsubjaket entity provides the base persistence definition of the
 * Tblpfdsubjaket entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdsubjaket implements java.io.Serializable {

	// Fields

	private Long idSubjaket;
	private Long idFail;
	private Long idPegawai;
	private Date tarikhSubjaketFail;
	private Date tarikhMasukFail;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private String noFailSubjaket;
        private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpfdsubjaket() {
	}

	/** minimal constructor */
	public AbstractTblpfdsubjaket(Long idSubjaket, Long idFail) {
		this.idSubjaket = idSubjaket;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblpfdsubjaket(Long idSubjaket, Long idFail,
			Long idPegawai, Date tarikhSubjaketFail, Date tarikhMasukFail,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String noFailSubjaket, Long idDb) {
		this.idSubjaket = idSubjaket;
		this.idFail = idFail;
		this.idPegawai = idPegawai;
		this.tarikhSubjaketFail = tarikhSubjaketFail;
		this.tarikhMasukFail = tarikhMasukFail;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.noFailSubjaket = noFailSubjaket;
                this.idDb = idDb;
	}   

	// Property accessors

	public Long getIdSubjaket() {
		return this.idSubjaket;
	}

	public void setIdSubjaket(Long idSubjaket) {
		this.idSubjaket = idSubjaket;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Long getIdPegawai() {
		return this.idPegawai;
	}

	public void setIdPegawai(Long idPegawai) {
		this.idPegawai = idPegawai;
	}

	public Date getTarikhSubjaketFail() {
		return this.tarikhSubjaketFail;
	}

	public void setTarikhSubjaketFail(Date tarikhSubjaketFail) {
		this.tarikhSubjaketFail = tarikhSubjaketFail;
	}

	public Date getTarikhMasukFail() {
		return this.tarikhMasukFail;
	}

	public void setTarikhMasukFail(Date tarikhMasukFail) {
		this.tarikhMasukFail = tarikhMasukFail;
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
        public String getNoFailSubjaket() {
                return this.noFailSubjaket;
        }

        public void setNoFailSubjaket(String noFailSubjaket) {
                this.noFailSubjaket = noFailSubjaket;
        }
        public Long getIdDb() {
                return this.idDb;
        }
    
        public void setIdDb(Long idDb) {
                this.idDb = idDb;
        }


}