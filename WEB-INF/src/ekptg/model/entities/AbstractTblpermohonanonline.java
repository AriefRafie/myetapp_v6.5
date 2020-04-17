package ekptg.model.entities;

/**
 * AbstractTblpermohonanonline entity provides the base persistence definition
 * of the Tblpermohonanonline entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpermohonanonline implements
		java.io.Serializable {

	// Fields

	private Long idPermohonanonline;
	private String noPermohonanOnline;
	private Long idPermohonan;

	// Constructors

	/** default constructor */
	public AbstractTblpermohonanonline() {
	}

	/** minimal constructor */
	public AbstractTblpermohonanonline(Long idPermohonanonline) {
		this.idPermohonanonline = idPermohonanonline;
	}

	/** full constructor */
	public AbstractTblpermohonanonline(Long idPermohonanonline,
			String noPermohonanOnline, Long idPermohonan) {
		this.idPermohonanonline = idPermohonanonline;
		this.noPermohonanOnline = noPermohonanOnline;
		this.idPermohonan = idPermohonan;
	}

	// Property accessors

	public Long getIdPermohonanonline() {
		return this.idPermohonanonline;
	}

	public void setIdPermohonanonline(Long idPermohonanonline) {
		this.idPermohonanonline = idPermohonanonline;
	}

	public String getNoPermohonanOnline() {
		return this.noPermohonanOnline;
	}

	public void setNoPermohonanOnline(String noPermohonanOnline) {
		this.noPermohonanOnline = noPermohonanOnline;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

}