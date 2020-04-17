package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkobfaraid entity provides the base persistence definition of the
 * Tblppkobfaraid entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkobfaraid implements java.io.Serializable {

	// Fields

	private Long idObfaraid;
	private Tblppkhbgnfaraid tblppkhbgnfaraid;
	private Tblppkob tblppkob;
	private String statusHidup;
	private Long baFaraid;
	private Long bbFaraid;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkobfaraid() {
	}

	/** minimal constructor */
	public AbstractTblppkobfaraid(Long idObfaraid,
			Tblppkhbgnfaraid tblppkhbgnfaraid, Tblppkob tblppkob) {
		this.idObfaraid = idObfaraid;
		this.tblppkhbgnfaraid = tblppkhbgnfaraid;
		this.tblppkob = tblppkob;
	}

	/** full constructor */
	public AbstractTblppkobfaraid(Long idObfaraid,
			Tblppkhbgnfaraid tblppkhbgnfaraid, Tblppkob tblppkob,
			String statusHidup, Long baFaraid, Long bbFaraid, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idObfaraid = idObfaraid;
		this.tblppkhbgnfaraid = tblppkhbgnfaraid;
		this.tblppkob = tblppkob;
		this.statusHidup = statusHidup;
		this.baFaraid = baFaraid;
		this.bbFaraid = bbFaraid;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdObfaraid() {
		return this.idObfaraid;
	}

	public void setIdObfaraid(Long idObfaraid) {
		this.idObfaraid = idObfaraid;
	}

	public Tblppkhbgnfaraid getTblppkhbgnfaraid() {
		return this.tblppkhbgnfaraid;
	}

	public void setTblppkhbgnfaraid(Tblppkhbgnfaraid tblppkhbgnfaraid) {
		this.tblppkhbgnfaraid = tblppkhbgnfaraid;
	}

	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
	}

	public String getStatusHidup() {
		return this.statusHidup;
	}

	public void setStatusHidup(String statusHidup) {
		this.statusHidup = statusHidup;
	}

	public Long getBaFaraid() {
		return this.baFaraid;
	}

	public void setBaFaraid(Long baFaraid) {
		this.baFaraid = baFaraid;
	}

	public Long getBbFaraid() {
		return this.bbFaraid;
	}

	public void setBbFaraid(Long bbFaraid) {
		this.bbFaraid = bbFaraid;
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