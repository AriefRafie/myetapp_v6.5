package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdrujlokasifail entity provides the base persistence definition
 * of the Tblpfdrujlokasifail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdrujlokasifail implements
		java.io.Serializable {

	// Fields

	private Long idLokasifail;
	private String lokasiFail;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdrujlokasifail() {
	}

	/** full constructor */
	public AbstractTblpfdrujlokasifail(String lokasiFail, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.lokasiFail = lokasiFail;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdLokasifail() {
		return this.idLokasifail;
	}

	public void setIdLokasifail(Long idLokasifail) {
		this.idLokasifail = idLokasifail;
	}

	public String getLokasiFail() {
		return this.lokasiFail;
	}

	public void setLokasiFail(String lokasiFail) {
		this.lokasiFail = lokasiFail;
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