package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkkolateraldtl entity provides the base persistence definition of
 * the Tblppkkolateraldtl entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkkolateraldtl implements
		java.io.Serializable {

	// Fields

	private Long idKolateraldtl;
	private Tblppkkolateralmst tblppkkolateralmst;
	private Tblppkob tblppkob;
	private String jenisOb;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkkolateraldtl() {
	}

	/** minimal constructor */
	public AbstractTblppkkolateraldtl(Long idKolateraldtl,
			Tblppkkolateralmst tblppkkolateralmst, Tblppkob tblppkob) {
		this.idKolateraldtl = idKolateraldtl;
		this.tblppkkolateralmst = tblppkkolateralmst;
		this.tblppkob = tblppkob;
	}

	/** full constructor */
	public AbstractTblppkkolateraldtl(Long idKolateraldtl,
			Tblppkkolateralmst tblppkkolateralmst, Tblppkob tblppkob,
			String jenisOb, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idKolateraldtl = idKolateraldtl;
		this.tblppkkolateralmst = tblppkkolateralmst;
		this.tblppkob = tblppkob;
		this.jenisOb = jenisOb;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdKolateraldtl() {
		return this.idKolateraldtl;
	}

	public void setIdKolateraldtl(Long idKolateraldtl) {
		this.idKolateraldtl = idKolateraldtl;
	}

	public Tblppkkolateralmst getTblppkkolateralmst() {
		return this.tblppkkolateralmst;
	}

	public void setTblppkkolateralmst(Tblppkkolateralmst tblppkkolateralmst) {
		this.tblppkkolateralmst = tblppkkolateralmst;
	}

	public Tblppkob getTblppkob() {
		return this.tblppkob;
	}

	public void setTblppkob(Tblppkob tblppkob) {
		this.tblppkob = tblppkob;
	}

	public String getJenisOb() {
		return this.jenisOb;
	}

	public void setJenisOb(String jenisOb) {
		this.jenisOb = jenisOb;
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