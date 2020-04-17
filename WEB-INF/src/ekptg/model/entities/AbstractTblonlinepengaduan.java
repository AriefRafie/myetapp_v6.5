package ekptg.model.entities;


/**
 * AbstractTblonlinepengaduan entity provides the base persistence definition of
 * the Tblonlinepengaduan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblonlinepengaduan implements
		java.io.Serializable {

	// Fields

	private Long idPengaduan;
	private Long idAduan;
	private Long idPengadu;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblonlinepengaduan() {
	}

	/** minimal constructor */
	public AbstractTblonlinepengaduan(Long idPengaduan) {
		this.idPengaduan = idPengaduan;
	}

	/** full constructor */
	public AbstractTblonlinepengaduan(Long idPengaduan,
			Long idAduan, Long idPengadu,
			Long idDb) {
		this.idPengaduan = idPengaduan;
		this.idAduan = idAduan;
		this.idPengadu = idPengadu;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdPengaduan() {
		return this.idPengaduan;
	}

	public void setIdPengaduan(Long idPengaduan) {
		this.idPengaduan = idPengaduan;
	}

	public Long getIdAduan() {
		return this.idAduan;
	}

	public void setIdAduan(Long idAduan) {
		this.idAduan = idAduan;
	}

	public Long getIdPengadu() {
		return this.idPengadu;
	}

	public void setIdPengadu(Long idPengadu) {
		this.idPengadu = idPengadu;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}