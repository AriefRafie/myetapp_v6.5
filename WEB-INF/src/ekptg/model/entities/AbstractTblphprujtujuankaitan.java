package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphprujtujuankaitan entity provides the base persistence definition of the
 * Tblphprujtujuankaitan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphprujtujuankaitan implements java.io.Serializable {

	// Fields

	private Long idTujuankaitan;
	private String kodTujuankaitan;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphprujtujuankaitan() {
	}

	/** full constructor */
	public AbstractTblphprujtujuankaitan(String kodTujuankaitan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.kodTujuankaitan = kodTujuankaitan;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdTujuankaitan() {
		return this.idTujuankaitan;
	}

	public void setIdTujuankaitan(Long idTujuankaitan) {
		this.idTujuankaitan = idTujuankaitan;
	}

	public String getKodTujuankaitan() {
		return this.kodTujuankaitan;
	}

	public void setKodTujuankaitan(String kodTujuankaitan) {
		this.kodTujuankaitan = kodTujuankaitan;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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