package ekptg.model.entities;

/**
 * AbstractTblmenu entity provides the base persistence definition of the
 * Tblmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblmenu implements java.io.Serializable {

	// Fields

	private Long idMenu;
	private Long idMenuAtas;
	private String namaMenu;
	private String alamatMenu;
	private String alamatIkon;
	private Long aturan;
	private Long status;
	private String kodMenu;

	// Constructors

	/** default constructor */
	public AbstractTblmenu() {
	}

	/** full constructor */
	public AbstractTblmenu(Long idMenuAtas, String namaMenu, String alamatMenu,
			String alamatIkon, Long aturan, Long status, String kodMenu) {
		this.idMenuAtas = idMenuAtas;
		this.namaMenu = namaMenu;
		this.alamatMenu = alamatMenu;
		this.alamatIkon = alamatIkon;
		this.aturan = aturan;
		this.status = status;
		this.kodMenu = kodMenu;
	}

	// Property accessors

	public Long getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Long getIdMenuAtas() {
		return this.idMenuAtas;
	}

	public void setIdMenuAtas(Long idMenuAtas) {
		this.idMenuAtas = idMenuAtas;
	}

	public String getNamaMenu() {
		return this.namaMenu;
	}

	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}

	public String getAlamatMenu() {
		return this.alamatMenu;
	}

	public void setAlamatMenu(String alamatMenu) {
		this.alamatMenu = alamatMenu;
	}

	public String getAlamatIkon() {
		return this.alamatIkon;
	}

	public void setAlamatIkon(String alamatIkon) {
		this.alamatIkon = alamatIkon;
	}

	public Long getAturan() {
		return this.aturan;
	}

	public void setAturan(Long aturan) {
		this.aturan = aturan;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getKodMenu() {
		return this.kodMenu;
	}

	public void setKodMenu(String kodMenu) {
		this.kodMenu = kodMenu;
	}

}