package ekptg.model.entities;


/**
 * Tblonlinepengaduan entity. @author MyEclipse Persistence Tools
 */
public class Tblonlinepengaduan extends AbstractTblonlinepengaduan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblonlinepengaduan() {
	}

	/** minimal constructor */
	public Tblonlinepengaduan(Long idPengaduan) {
		super(idPengaduan);
	}

	/** full constructor */
	public Tblonlinepengaduan(Long idPengaduan,
			Long idAduan, Long idPengadu,
			Long idDb) {
		super(idPengaduan, idAduan, idPengadu, idDb);
	}

}
