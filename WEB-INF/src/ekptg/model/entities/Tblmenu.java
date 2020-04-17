package ekptg.model.entities;

/**
 * Tblmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblmenu extends AbstractTblmenu implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblmenu() {
	}

	/** full constructor */
	public Tblmenu(Long idMenuAtas, String namaMenu, String alamatMenu,
			String alamatIkon, Long aturan, Long status, String kodMenu) {
		super(idMenuAtas, namaMenu, alamatMenu, alamatIkon, aturan, status,
				kodMenu);
	}

}
