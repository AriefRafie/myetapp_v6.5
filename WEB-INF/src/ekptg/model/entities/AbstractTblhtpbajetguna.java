package ekptg.model.entities;

/**
 * AbstractTblhtpbajetguna entity provides the base persistence definition of
 * the Tblhtpbajetguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpbajetguna implements java.io.Serializable {

	// Fields

	private Long idBajetguna;
	private Tblhtpbajet tblhtpbajet;
	private Tblhtpbayarancukai tblhtpbayarancukai;
	private Long guna;
	private Long baki;

	// Constructors

	/** default constructor */
	public AbstractTblhtpbajetguna() {
	}

	/** minimal constructor */
	public AbstractTblhtpbajetguna(Long idBajetguna, Tblhtpbajet tblhtpbajet,
			Tblhtpbayarancukai tblhtpbayarancukai) {
		this.idBajetguna = idBajetguna;
		this.tblhtpbajet = tblhtpbajet;
		this.tblhtpbayarancukai = tblhtpbayarancukai;
	}

	/** full constructor */
	public AbstractTblhtpbajetguna(Long idBajetguna, Tblhtpbajet tblhtpbajet,
			Tblhtpbayarancukai tblhtpbayarancukai, Long guna, Long baki) {
		this.idBajetguna = idBajetguna;
		this.tblhtpbajet = tblhtpbajet;
		this.tblhtpbayarancukai = tblhtpbayarancukai;
		this.guna = guna;
		this.baki = baki;
	}

	// Property accessors

	public Long getIdBajetguna() {
		return this.idBajetguna;
	}

	public void setIdBajetguna(Long idBajetguna) {
		this.idBajetguna = idBajetguna;
	}

	public Tblhtpbajet getTblhtpbajet() {
		return this.tblhtpbajet;
	}

	public void setTblhtpbajet(Tblhtpbajet tblhtpbajet) {
		this.tblhtpbajet = tblhtpbajet;
	}

	public Tblhtpbayarancukai getTblhtpbayarancukai() {
		return this.tblhtpbayarancukai;
	}

	public void setTblhtpbayarancukai(Tblhtpbayarancukai tblhtpbayarancukai) {
		this.tblhtpbayarancukai = tblhtpbayarancukai;
	}

	public Long getGuna() {
		return this.guna;
	}

	public void setGuna(Long guna) {
		this.guna = guna;
	}

	public Long getBaki() {
		return this.baki;
	}

	public void setBaki(Long baki) {
		this.baki = baki;
	}

}