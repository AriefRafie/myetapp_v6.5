package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkborangjdtl entity provides the base persistence definition of
 * the Tblppkborangjdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkborangjdtl implements java.io.Serializable {

	// Fields

	private Long idBorangjdtl;
	private Tblppkborangj tblppkborangj;
	private Tblppkob tblppkob;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkborangjdtl() {
	}

	/** minimal constructor */
	public AbstractTblppkborangjdtl(Long idBorangjdtl,
			Tblppkborangj tblppkborangj) {
		this.idBorangjdtl = idBorangjdtl;
		this.tblppkborangj = tblppkborangj;
	}

	/** full constructor */
	public AbstractTblppkborangjdtl(Long idBorangjdtl,
			Tblppkborangj tblppkborangj, Tblppkob tblppkob, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idBorangjdtl = idBorangjdtl;
		this.tblppkborangj = tblppkborangj;
		this.tblppkob = tblppkob;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorangjdtl() {
		return this.idBorangjdtl;
	}

	public void setIdBorangjdtl(Long idBorangjdtl) {
		this.idBorangjdtl = idBorangjdtl;
	}

	public Tblppkborangj getTblppkborangj() {
		return this.tblppkborangj;
	}

	public void setTblppkborangj(Tblppkborangj tblppkborangj) {
		this.tblppkborangj = tblppkborangj;
	}

	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
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