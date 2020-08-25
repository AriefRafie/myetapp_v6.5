package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblsemakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblsemakan extends AbstractTblsemakan implements java.io.Serializable {
	// Constructors
	/**
	 * 
	 */
	private static final long serialVersionUID = -4073049528942021778L;

	/** default constructor */
	public Tblsemakan() {
	}

	/** minimal constructor */
	public Tblsemakan(Long idSemakan, String kodSemak) {
		super(idSemakan, kodSemak);
	}

	/** full constructor */
	public Tblsemakan(Long idSemakan, Long idParent, String kodSemak,
			String perihal, String lainLain, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblsemakansenarais) {
		super(idSemakan, idParent, kodSemak, perihal, lainLain, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblsemakansenarais);
	}


}
