package ekptg.model.entities;


import java.util.Date;

/**
 * AbstractTblpdtrujperkarapekeliling entity provides the base persistence
 * definition of the Tblpdtrujperkarapekeliling entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractTblpdtrujperkarapekeliling implements
		java.io.Serializable {

	// Fields

	private Long idPerkarapekeliling;
	private String kodPerkaraPekeliling;
	private String perkaraPekeliling;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpdtrujperkarapekeliling() {
	}

	/** full constructor */
	public AbstractTblpdtrujperkarapekeliling(String kodPerkaraPekeliling,
			String perkaraPekeliling, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.kodPerkaraPekeliling = kodPerkaraPekeliling;
		this.perkaraPekeliling = perkaraPekeliling;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerkarapekeliling() {
		return this.idPerkarapekeliling;
	}

	public void setIdPerkarapekeliling(Long idPerkarapekeliling) {
		this.idPerkarapekeliling = idPerkarapekeliling;
	}

	public String getKodPerkaraPekeliling() {
		return this.kodPerkaraPekeliling;
	}

	public void setKodPerkaraPekeliling(String kodPerkaraPekeliling) {
		this.kodPerkaraPekeliling = kodPerkaraPekeliling;
	}

	public String getPerkaraPekeliling() {
		return this.perkaraPekeliling;
	}

	public void setPerkaraPekeliling(String perkaraPekeliling) {
		this.perkaraPekeliling = perkaraPekeliling;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

}