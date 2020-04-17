package ekptg.model.entities;

/**
 * AbstractTblphpseqpfdfail entity provides the base persistence definition of
 * the Tblphpseqpfdfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpseqpfdfail implements java.io.Serializable {

	// Fields

	private TblphpseqpfdfailId id;
	private Long tahun;
	private Long noTurutan;

	// Constructors

	/** default constructor */
	public AbstractTblphpseqpfdfail() {
	}

	/** minimal constructor */
	public AbstractTblphpseqpfdfail(TblphpseqpfdfailId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractTblphpseqpfdfail(TblphpseqpfdfailId id, Long tahun,
			Long noTurutan) {
		this.id = id;
		this.tahun = tahun;
		this.noTurutan = noTurutan;
	}

	// Property accessors

	public TblphpseqpfdfailId getId() {
		return this.id;
	}

	public void setId(TblphpseqpfdfailId id) {
		this.id = id;
	}

	public Long getTahun() {
		return this.tahun;
	}

	public void setTahun(Long tahun) {
		this.tahun = tahun;
	}

	public Long getNoTurutan() {
		return this.noTurutan;
	}

	public void setNoTurutan(Long noTurutan) {
		this.noTurutan = noTurutan;
	}

}