package ekptg.model.entities;


/**
 * AbstractTblppkrujpenghantarnotis entity provides the base persistence definition of
 * the Tblppkrujpenghantarnotis entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkrujpenghantarnotis implements
		java.io.Serializable {

	// Fields

	private Long idPenghantarNotis;
	private Long idPejabatJkptg;
	private String kodPenghantarNotis;
	private String nama;
	

	// Constructors

	/** default constructor */
	public AbstractTblppkrujpenghantarnotis() {
	}

	/** minimal constructor */
	public AbstractTblppkrujpenghantarnotis(Long idPejabatJkptg) {
		this.idPejabatJkptg = idPejabatJkptg;
	}

	/** full constructor */
	public AbstractTblppkrujpenghantarnotis(Long idPejabatJkptg, String nama,
			String kodPenghantarNotis) {
		
		this.kodPenghantarNotis = kodPenghantarNotis;
		this.nama = nama;	
		this.idPejabatJkptg = idPejabatJkptg;
	}

	
	//getter and setter
	
	public Long getIdPenghantarNotis() {
		return idPenghantarNotis;
	}

	public void setIdPenghantarNotis(Long idPenghantarNotis) {
		this.idPenghantarNotis = idPenghantarNotis;
	}

	public Long getIdPejabatJkptg() {
		return idPejabatJkptg;
	}

	public void setIdPejabatJkptg(Long idPejabatJkptg) {
		this.idPejabatJkptg = idPejabatJkptg;
	}

	public String getKodPenghantarNotis() {
		return kodPenghantarNotis;
	}

	public void setKodPenghantarNotis(String kodPenghantarNotis) {
		this.kodPenghantarNotis = kodPenghantarNotis;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	// Property accessors

	
	
}