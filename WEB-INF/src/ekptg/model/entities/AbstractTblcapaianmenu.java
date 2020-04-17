package ekptg.model.entities;

/**
 * AbstractTblcapaianmenu entity provides the base persistence definition of the
 * Tblcapaianmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblcapaianmenu implements java.io.Serializable {

	// Fields

	private Long idCapaianMenu;
	private String kodKumpulan;
	private String kodMenu;

	// Constructors

	/** default constructor */
	public AbstractTblcapaianmenu() {
	}

	/** full constructor */
	public AbstractTblcapaianmenu(String kodKumpulan, String kodMenu) {
		this.kodKumpulan = kodKumpulan;
		this.kodMenu = kodMenu;
	}

	// Property accessors

	public Long getIdCapaianMenu() {
		return this.idCapaianMenu;
	}

	public void setIdCapaianMenu(Long idCapaianMenu) {
		this.idCapaianMenu = idCapaianMenu;
	}

	public String getKodKumpulan() {
		return this.kodKumpulan;
	}

	public void setKodKumpulan(String kodKumpulan) {
		this.kodKumpulan = kodKumpulan;
	}

	public String getKodMenu() {
		return this.kodMenu;
	}

	public void setKodMenu(String kodMenu) {
		this.kodMenu = kodMenu;
	}

}