package ekptg.model.entities;

/**
 * Tblphpseqpfdfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpseqpfdfail extends AbstractTblphpseqpfdfail implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpseqpfdfail() {
	}

	/** minimal constructor */
	public Tblphpseqpfdfail(TblphpseqpfdfailId id) {
		super(id);
	}

	/** full constructor */
	public Tblphpseqpfdfail(TblphpseqpfdfailId id, Long tahun, Long noTurutan) {
		super(id, tahun, noTurutan);
	}

}
