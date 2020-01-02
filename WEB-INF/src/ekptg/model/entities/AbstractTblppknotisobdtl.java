package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppknotisobdtl entity provides the base persistence definition of
 * the Tblppknotisobdtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppknotisobdtl implements java.io.Serializable {

	// Fields

	private Long idNotisobdtl;
	private Tblppknotisobmst tblppknotisobmst;
	private Tblppkob tblppkob;
	private String namaPenerima;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppknotisobdtl() {
	}

	/** minimal constructor */
	public AbstractTblppknotisobdtl(Long idNotisobdtl,
			Tblppknotisobmst tblppknotisobmst, Tblppkob tblppkob) {
		this.idNotisobdtl = idNotisobdtl;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkob = tblppkob;
	}

	/** full constructor */
	public AbstractTblppknotisobdtl(Long idNotisobdtl,
			Tblppknotisobmst tblppknotisobmst, Tblppkob tblppkob,
			String namaPenerima, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idNotisobdtl = idNotisobdtl;
		this.tblppknotisobmst = tblppknotisobmst;
		this.tblppkob = tblppkob;
		this.namaPenerima = namaPenerima;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNotisobdtl() {
		return this.idNotisobdtl;
	}

	public void setIdNotisobdtl(Long idNotisobdtl) {
		this.idNotisobdtl = idNotisobdtl;
	}

	public Tblppknotisobmst getTblppknotisobmst() {
		return this.tblppknotisobmst;
	}

	public void setTblppknotisobmst(Tblppknotisobmst tblppknotisobmst) {
		this.tblppknotisobmst = tblppknotisobmst;
	}

	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
	}

	public String getNamaPenerima() {
		return this.namaPenerima;
	}

	public void setNamaPenerima(String namaPenerima) {
		this.namaPenerima = namaPenerima;
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