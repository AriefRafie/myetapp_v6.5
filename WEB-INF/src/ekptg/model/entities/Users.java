package ekptg.model.entities;

import java.util.Date;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
public class Users extends AbstractUsers implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Long userId) {
		super(userId);
	}

	/** full constructor */
	public Users(Long userId, String userLogin, String userPassword,
			String userName, String userRole, Date dateRegistered,
			String userType, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(userId, userLogin, userPassword, userName, userRole,
				dateRegistered, userType, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
