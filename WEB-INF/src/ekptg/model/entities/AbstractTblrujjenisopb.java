package ekptg.model.entities;

/**
 * AbstractTblrujjenisopb entity provides the base persistence definition of the
 * Tblrujjenisopb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisopb implements java.io.Serializable {

	// Fields

	private Long idRujjenisopb;
	private String kodNopb;
	private String keterangan;

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisopb() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisopb(Long idRujjenisopb) {
		this.idRujjenisopb = idRujjenisopb;
	}

	/** full constructor */
	public AbstractTblrujjenisopb(Long idRujjenisopb, String kodNopb,
			String keterangan) {
		this.idRujjenisopb = idRujjenisopb;
		this.kodNopb = kodNopb;
		this.keterangan = keterangan;
	}

	// Property accessors

	public Long getIdRujjenisopb() {
		return this.idRujjenisopb;
	}

	public void setIdRujjenisopb(Long idRujjenisopb) {
		this.idRujjenisopb = idRujjenisopb;
	}

	public String getKodNopb() {
		return this.kodNopb;
	}

	public void setKodNopb(String kodNopb) {
		this.kodNopb = kodNopb;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}