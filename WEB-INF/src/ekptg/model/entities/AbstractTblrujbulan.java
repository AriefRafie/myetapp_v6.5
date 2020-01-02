package ekptg.model.entities;

/**
 * AbstractTblrujbulan entity provides the base persistence definition of the
 * Tblbulan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujbulan implements java.io.Serializable {

	// Fields

	private Long idBulan;
	private String kodBulan;
	private String namaBulan;

	// Constructors

	/** default constructor */
	public AbstractTblrujbulan() {
	}
	
	/** minimal constructor */
	public AbstractTblrujbulan(Long idBulan) {
		this.idBulan = idBulan;
	}

	/** full constructor */
	public AbstractTblrujbulan(Long idBulan,String kodBulan,String namaBulan) {
		this.idBulan = idBulan;
		this.kodBulan = kodBulan;
		this.namaBulan = namaBulan;
	}

	// Property accessors

	public Long getIdBulan() {
		return this.idBulan;
	}

	public void setIdBulan(Long idBulan) {
		this.idBulan = idBulan;
	}

	public String getKodBulan() {
		return this.kodBulan;
	}

	public void setKodBulan(String kodBulan) {
		this.kodBulan = kodBulan;
	}

	public String getNamaBulan() {
		return this.namaBulan;
	}

	public void setNamaBulan(String namaBulan) {
		this.namaBulan = namaBulan;
	}
	

}