package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpperenggankkplpsn entity provides the base persistence
 * definition of the Tblphpperenggankkplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpperenggankkplpsn implements
		java.io.Serializable {

	// Fields

	private Long idPerenggankkplpsn;
	private Tblphpkertaskerjapelepasan tblphpkertaskerjapelepasan;
	private String perenggan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpperenggankkplpsn() {
	}

	/** minimal constructor */
	public AbstractTblphpperenggankkplpsn(Long idPerenggankkplpsn) {
		this.idPerenggankkplpsn = idPerenggankkplpsn;
	}

	/** full constructor */
	public AbstractTblphpperenggankkplpsn(Long idPerenggankkplpsn,
			Tblphpkertaskerjapelepasan tblphpkertaskerjapelepasan,
			String perenggan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPerenggankkplpsn = idPerenggankkplpsn;
		this.tblphpkertaskerjapelepasan = tblphpkertaskerjapelepasan;
		this.perenggan = perenggan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerenggankkplpsn() {
		return this.idPerenggankkplpsn;
	}

	public void setIdPerenggankkplpsn(Long idPerenggankkplpsn) {
		this.idPerenggankkplpsn = idPerenggankkplpsn;
	}

	public Tblphpkertaskerjapelepasan getTblphpkertaskerjapelepasan() {
		return this.tblphpkertaskerjapelepasan;
	}

	public void setTblphpkertaskerjapelepasan(
			Tblphpkertaskerjapelepasan tblphpkertaskerjapelepasan) {
		this.tblphpkertaskerjapelepasan = tblphpkertaskerjapelepasan;
	}

	public String getPerenggan() {
		return this.perenggan;
	}

	public void setPerenggan(String perenggan) {
		this.perenggan = perenggan;
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