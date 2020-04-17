package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujpdtdokumenpekeliling entity provides the base persistence
 * definition of the Tblrujpdtdokumenpekeliling entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpdtdokumenpekeliling implements
		java.io.Serializable {

	// Fields

	private Long idDokumenPekeliling;
	private String jenisDokumenPekeliling;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujpdtdokumenpekeliling() {
	}

	/** minimal constructor */
	public AbstractTblrujpdtdokumenpekeliling(Long idDokumenPekeliling) {
		this.idDokumenPekeliling = idDokumenPekeliling;
	}

	/** full constructor */
	public AbstractTblrujpdtdokumenpekeliling(Long idDokumenPekeliling,
			String jenisDokumenPekeliling, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idDokumenPekeliling = idDokumenPekeliling;
		this.jenisDokumenPekeliling = jenisDokumenPekeliling;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdDokumenPekeliling() {
		return this.idDokumenPekeliling;
	}

	public void setIdDokumenPekeliling(Long idDokumenPekeliling) {
		this.idDokumenPekeliling = idDokumenPekeliling;
	}

	public String getJenisDokumenPekeliling() {
		return this.jenisDokumenPekeliling;
	}

	public void setJenisDokumenPekeliling(String jenisDokumenPekeliling) {
		this.jenisDokumenPekeliling = jenisDokumenPekeliling;
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