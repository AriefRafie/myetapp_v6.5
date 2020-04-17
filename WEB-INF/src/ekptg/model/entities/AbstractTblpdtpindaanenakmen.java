package ekptg.model.entities;

/**
 * AbstractTblpdtpindaanenakmen entity provides the base persistence definition
 * of the Tblpdtpindaanenakmen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanenakmen implements
		java.io.Serializable {

	// Fields

	private Long idPindaanenakmen;
	private Tblpdtpindaanenakmenpenggal tblpdtpindaanenakmenpenggal;
	private Tblpdtenakmenpinda tblpdtenakmenpinda;
	private Tblpdtenakmen tblpdtenakmen;
	private Tblpdtpindaanenakmensubpara tblpdtpindaanenakmensubpara;
	private Tblpdtpindaanenakmenbab tblpdtpindaanenakmenbab;
	private Tblpdtpindaanjadualpara tblpdtpindaanjadualpara;
	private Tblpdtpindaanenakmenbahagian tblpdtpindaanenakmenbahagian;
	private Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara;
	private Tblpdtpindaanenakmenseksyen tblpdtpindaanenakmenseksyen;
	private Tblpdtpindaanenakmenpara tblpdtpindaanenakmenpara;

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanenakmen() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanenakmen(Long idPindaanenakmen,
			Tblpdtenakmenpinda tblpdtenakmenpinda, Tblpdtenakmen tblpdtenakmen) {
		this.idPindaanenakmen = idPindaanenakmen;
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
		this.tblpdtenakmen = tblpdtenakmen;
	}

	/** full constructor */
	public AbstractTblpdtpindaanenakmen(Long idPindaanenakmen,
			Tblpdtpindaanenakmenpenggal tblpdtpindaanenakmenpenggal,
			Tblpdtenakmenpinda tblpdtenakmenpinda, Tblpdtenakmen tblpdtenakmen,
			Tblpdtpindaanenakmensubpara tblpdtpindaanenakmensubpara,
			Tblpdtpindaanenakmenbab tblpdtpindaanenakmenbab,
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara,
			Tblpdtpindaanenakmenbahagian tblpdtpindaanenakmenbahagian,
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara,
			Tblpdtpindaanenakmenseksyen tblpdtpindaanenakmenseksyen,
			Tblpdtpindaanenakmenpara tblpdtpindaanenakmenpara) {
		this.idPindaanenakmen = idPindaanenakmen;
		this.tblpdtpindaanenakmenpenggal = tblpdtpindaanenakmenpenggal;
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
		this.tblpdtenakmen = tblpdtenakmen;
		this.tblpdtpindaanenakmensubpara = tblpdtpindaanenakmensubpara;
		this.tblpdtpindaanenakmenbab = tblpdtpindaanenakmenbab;
		this.tblpdtpindaanjadualpara = tblpdtpindaanjadualpara;
		this.tblpdtpindaanenakmenbahagian = tblpdtpindaanenakmenbahagian;
		this.tblpdtpindaanjadualsubpara = tblpdtpindaanjadualsubpara;
		this.tblpdtpindaanenakmenseksyen = tblpdtpindaanenakmenseksyen;
		this.tblpdtpindaanenakmenpara = tblpdtpindaanenakmenpara;
	}

	// Property accessors

	public Long getIdPindaanenakmen() {
		return this.idPindaanenakmen;
	}

	public void setIdPindaanenakmen(Long idPindaanenakmen) {
		this.idPindaanenakmen = idPindaanenakmen;
	}

	public Tblpdtpindaanenakmenpenggal getTblpdtpindaanenakmenpenggal() {
		return this.tblpdtpindaanenakmenpenggal;
	}

	public void setTblpdtpindaanenakmenpenggal(
			Tblpdtpindaanenakmenpenggal tblpdtpindaanenakmenpenggal) {
		this.tblpdtpindaanenakmenpenggal = tblpdtpindaanenakmenpenggal;
	}

	public Tblpdtenakmenpinda getTblpdtenakmenpinda() {
		return this.tblpdtenakmenpinda;
	}

	public void setTblpdtenakmenpinda(Tblpdtenakmenpinda tblpdtenakmenpinda) {
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
	}

	public Tblpdtenakmen getTblpdtenakmen() {
		return this.tblpdtenakmen;
	}

	public void setTblpdtenakmen(Tblpdtenakmen tblpdtenakmen) {
		this.tblpdtenakmen = tblpdtenakmen;
	}

	public Tblpdtpindaanenakmensubpara getTblpdtpindaanenakmensubpara() {
		return this.tblpdtpindaanenakmensubpara;
	}

	public void setTblpdtpindaanenakmensubpara(
			Tblpdtpindaanenakmensubpara tblpdtpindaanenakmensubpara) {
		this.tblpdtpindaanenakmensubpara = tblpdtpindaanenakmensubpara;
	}

	public Tblpdtpindaanenakmenbab getTblpdtpindaanenakmenbab() {
		return this.tblpdtpindaanenakmenbab;
	}

	public void setTblpdtpindaanenakmenbab(
			Tblpdtpindaanenakmenbab tblpdtpindaanenakmenbab) {
		this.tblpdtpindaanenakmenbab = tblpdtpindaanenakmenbab;
	}

	public Tblpdtpindaanjadualpara getTblpdtpindaanjadualpara() {
		return this.tblpdtpindaanjadualpara;
	}

	public void setTblpdtpindaanjadualpara(
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara) {
		this.tblpdtpindaanjadualpara = tblpdtpindaanjadualpara;
	}

	public Tblpdtpindaanenakmenbahagian getTblpdtpindaanenakmenbahagian() {
		return this.tblpdtpindaanenakmenbahagian;
	}

	public void setTblpdtpindaanenakmenbahagian(
			Tblpdtpindaanenakmenbahagian tblpdtpindaanenakmenbahagian) {
		this.tblpdtpindaanenakmenbahagian = tblpdtpindaanenakmenbahagian;
	}

	public Tblpdtpindaanjadualsubpara getTblpdtpindaanjadualsubpara() {
		return this.tblpdtpindaanjadualsubpara;
	}

	public void setTblpdtpindaanjadualsubpara(
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara) {
		this.tblpdtpindaanjadualsubpara = tblpdtpindaanjadualsubpara;
	}

	public Tblpdtpindaanenakmenseksyen getTblpdtpindaanenakmenseksyen() {
		return this.tblpdtpindaanenakmenseksyen;
	}

	public void setTblpdtpindaanenakmenseksyen(
			Tblpdtpindaanenakmenseksyen tblpdtpindaanenakmenseksyen) {
		this.tblpdtpindaanenakmenseksyen = tblpdtpindaanenakmenseksyen;
	}

	public Tblpdtpindaanenakmenpara getTblpdtpindaanenakmenpara() {
		return this.tblpdtpindaanenakmenpara;
	}

	public void setTblpdtpindaanenakmenpara(
			Tblpdtpindaanenakmenpara tblpdtpindaanenakmenpara) {
		this.tblpdtpindaanenakmenpara = tblpdtpindaanenakmenpara;
	}

}