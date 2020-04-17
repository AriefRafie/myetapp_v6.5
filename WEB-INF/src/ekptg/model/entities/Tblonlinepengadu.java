package ekptg.model.entities;

import java.util.Set;

/**
 * Tblonlinepengadu entity. @author MyEclipse Persistence Tools
 */
public class Tblonlinepengadu extends AbstractTblonlinepengadu implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblonlinepengadu() {
	}

	/** minimal constructor */
	public Tblonlinepengadu(Long idPengadu) {
		super(idPengadu);
	}

	/** full constructor */
	public Tblonlinepengadu(Long idPengadu, Long idPengguna,
			String namaPengadu, String noTelRumah, String noTelBimbit,
			String emel, Long idDb, Set tblonlinepengaduans) {
		super(idPengadu, idPengguna, namaPengadu, noTelRumah, noTelBimbit,
				emel, idDb, tblonlinepengaduans);
	}

}
