package ekptg.model.entities;

/**
 * AbstractTblpdtpindaanakta entity provides the base persistence definition of
 * the Tblpdtpindaanakta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtpindaanakta implements java.io.Serializable {

	// Fields

	private Long idPindaanakta;
	private Tblpdtpindaanaktabab tblpdtpindaanaktabab;
	private Tblpdtpindaanaktasubpara tblpdtpindaanaktasubpara;
	private Tblpdtpindaanaktabahagian tblpdtpindaanaktabahagian;
	private Tblpdtpindaanjadualpara tblpdtpindaanjadualpara;
	private Tblpdtpindaanaktapara tblpdtpindaanaktapara;
	private Tblpdtpindaanaktaseksyen tblpdtpindaanaktaseksyen;
	private Tblpdtakta tblpdtakta;
	private Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara;
	private Tblpdtpindaanaktapenggal tblpdtpindaanaktapenggal;
	private Tblpdtaktapinda tblpdtaktapinda;

	// Constructors

	/** default constructor */
	public AbstractTblpdtpindaanakta() {
	}

	/** minimal constructor */
	public AbstractTblpdtpindaanakta(Long idPindaanakta, Tblpdtakta tblpdtakta,
			Tblpdtaktapinda tblpdtaktapinda) {
		this.idPindaanakta = idPindaanakta;
		this.tblpdtakta = tblpdtakta;
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

	/** full constructor */
	public AbstractTblpdtpindaanakta(Long idPindaanakta,
			Tblpdtpindaanaktabab tblpdtpindaanaktabab,
			Tblpdtpindaanaktasubpara tblpdtpindaanaktasubpara,
			Tblpdtpindaanaktabahagian tblpdtpindaanaktabahagian,
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara,
			Tblpdtpindaanaktapara tblpdtpindaanaktapara,
			Tblpdtpindaanaktaseksyen tblpdtpindaanaktaseksyen,
			Tblpdtakta tblpdtakta,
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara,
			Tblpdtpindaanaktapenggal tblpdtpindaanaktapenggal,
			Tblpdtaktapinda tblpdtaktapinda) {
		this.idPindaanakta = idPindaanakta;
		this.tblpdtpindaanaktabab = tblpdtpindaanaktabab;
		this.tblpdtpindaanaktasubpara = tblpdtpindaanaktasubpara;
		this.tblpdtpindaanaktabahagian = tblpdtpindaanaktabahagian;
		this.tblpdtpindaanjadualpara = tblpdtpindaanjadualpara;
		this.tblpdtpindaanaktapara = tblpdtpindaanaktapara;
		this.tblpdtpindaanaktaseksyen = tblpdtpindaanaktaseksyen;
		this.tblpdtakta = tblpdtakta;
		this.tblpdtpindaanjadualsubpara = tblpdtpindaanjadualsubpara;
		this.tblpdtpindaanaktapenggal = tblpdtpindaanaktapenggal;
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

	// Property accessors

	public Long getIdPindaanakta() {
		return this.idPindaanakta;
	}

	public void setIdPindaanakta(Long idPindaanakta) {
		this.idPindaanakta = idPindaanakta;
	}

	public Tblpdtpindaanaktabab getTblpdtpindaanaktabab() {
		return this.tblpdtpindaanaktabab;
	}

	public void setTblpdtpindaanaktabab(
			Tblpdtpindaanaktabab tblpdtpindaanaktabab) {
		this.tblpdtpindaanaktabab = tblpdtpindaanaktabab;
	}

	public Tblpdtpindaanaktasubpara getTblpdtpindaanaktasubpara() {
		return this.tblpdtpindaanaktasubpara;
	}

	public void setTblpdtpindaanaktasubpara(
			Tblpdtpindaanaktasubpara tblpdtpindaanaktasubpara) {
		this.tblpdtpindaanaktasubpara = tblpdtpindaanaktasubpara;
	}

	public Tblpdtpindaanaktabahagian getTblpdtpindaanaktabahagian() {
		return this.tblpdtpindaanaktabahagian;
	}

	public void setTblpdtpindaanaktabahagian(
			Tblpdtpindaanaktabahagian tblpdtpindaanaktabahagian) {
		this.tblpdtpindaanaktabahagian = tblpdtpindaanaktabahagian;
	}

	public Tblpdtpindaanjadualpara getTblpdtpindaanjadualpara() {
		return this.tblpdtpindaanjadualpara;
	}

	public void setTblpdtpindaanjadualpara(
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara) {
		this.tblpdtpindaanjadualpara = tblpdtpindaanjadualpara;
	}

	public Tblpdtpindaanaktapara getTblpdtpindaanaktapara() {
		return this.tblpdtpindaanaktapara;
	}

	public void setTblpdtpindaanaktapara(
			Tblpdtpindaanaktapara tblpdtpindaanaktapara) {
		this.tblpdtpindaanaktapara = tblpdtpindaanaktapara;
	}

	public Tblpdtpindaanaktaseksyen getTblpdtpindaanaktaseksyen() {
		return this.tblpdtpindaanaktaseksyen;
	}

	public void setTblpdtpindaanaktaseksyen(
			Tblpdtpindaanaktaseksyen tblpdtpindaanaktaseksyen) {
		this.tblpdtpindaanaktaseksyen = tblpdtpindaanaktaseksyen;
	}

	public Tblpdtakta getTblpdtakta() {
		return this.tblpdtakta;
	}

	public void setTblpdtakta(Tblpdtakta tblpdtakta) {
		this.tblpdtakta = tblpdtakta;
	}

	public Tblpdtpindaanjadualsubpara getTblpdtpindaanjadualsubpara() {
		return this.tblpdtpindaanjadualsubpara;
	}

	public void setTblpdtpindaanjadualsubpara(
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara) {
		this.tblpdtpindaanjadualsubpara = tblpdtpindaanjadualsubpara;
	}

	public Tblpdtpindaanaktapenggal getTblpdtpindaanaktapenggal() {
		return this.tblpdtpindaanaktapenggal;
	}

	public void setTblpdtpindaanaktapenggal(
			Tblpdtpindaanaktapenggal tblpdtpindaanaktapenggal) {
		this.tblpdtpindaanaktapenggal = tblpdtpindaanaktapenggal;
	}

	public Tblpdtaktapinda getTblpdtaktapinda() {
		return this.tblpdtaktapinda;
	}

	public void setTblpdtaktapinda(Tblpdtaktapinda tblpdtaktapinda) {
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

}