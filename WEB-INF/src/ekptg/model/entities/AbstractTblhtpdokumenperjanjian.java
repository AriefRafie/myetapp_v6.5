package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpdokumenperjanjian entity provides the base persistence
 * definition of the Tblhtpdokumenperjanjian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpdokumenperjanjian implements
		java.io.Serializable {

	// Fields

	private Long idDokumenperjanjian;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private String flag;
	private String pihak;
	private String ulasan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private String idKemaskini;
	private Date tarkhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpdokumenperjanjian() {
	}

	/** minimal constructor */
	public AbstractTblhtpdokumenperjanjian(Long idDokumenperjanjian,
			Tblhtppermohonan tblhtppermohonan, Long idMasuk) {
		this.idDokumenperjanjian = idDokumenperjanjian;
		this.tblhtppermohonan = tblhtppermohonan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtpdokumenperjanjian(Long idDokumenperjanjian,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantar,
			Date tarikhTerima, String flag, String pihak, String ulasan,
			Long idMasuk, Date tarikhMasuk, String idKemaskini,
			Date tarkhKemaskini) {
		this.idDokumenperjanjian = idDokumenperjanjian;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.flag = flag;
		this.pihak = pihak;
		this.ulasan = ulasan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarkhKemaskini = tarkhKemaskini;
	}

	// Property accessors

	public Long getIdDokumenperjanjian() {
		return this.idDokumenperjanjian;
	}

	public void setIdDokumenperjanjian(Long idDokumenperjanjian) {
		this.idDokumenperjanjian = idDokumenperjanjian;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPihak() {
		return this.pihak;
	}

	public void setPihak(String pihak) {
		this.pihak = pihak;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
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

	public String getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarkhKemaskini() {
		return this.tarkhKemaskini;
	}

	public void setTarkhKemaskini(Date tarkhKemaskini) {
		this.tarkhKemaskini = tarkhKemaskini;
	}

}