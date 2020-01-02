package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujenishakmilik entity provides the base persistence definition of
 * the Tblrujenishakmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujenishakmilik implements
		java.io.Serializable {

	// Fields

	private Long idJenishakmilik;
	private String kodJenisHm;
	private String keterangan;
	private String statusHm;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        
	// Constructors

	/** default constructor */
	public AbstractTblrujenishakmilik() {
	}

	/** minimal constructor */
	public AbstractTblrujenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	/** full constructor */
	public AbstractTblrujenishakmilik(Long idJenishakmilik, String kodJenisHm,
			String keterangan, String statusHm, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idJenishakmilik = idJenishakmilik;
		this.kodJenisHm = kodJenisHm;
		this.keterangan = keterangan;
		this.statusHm = statusHm;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdJenishakmilik() {
		return this.idJenishakmilik;
	}

	public void setIdJenishakmilik(Long idJenishakmilik) {
		this.idJenishakmilik = idJenishakmilik;
	}

	public String getKodJenisHm() {
		return this.kodJenisHm;
	}

	public void setKodJenisHm(String kodJenisHm) {
		this.kodJenisHm = kodJenisHm;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatusHm() {
		return this.statusHm;
	}

	public void setStatusHm(String statusHm) {
		this.statusHm = statusHm;
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