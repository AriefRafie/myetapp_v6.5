package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpmoa entity provides the base persistence definition of the
 * Tblhtpmoa entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpmoa implements java.io.Serializable {

	// Fields

	private Long idMoa;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhHantar;
	private Date tarikhTerima;
	private String noRujukanMoa;
	private Date tarikhTandatangan;
	private Date tarikhDaftar;
	private Date tarikhBayaran;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpmoa() {
	}

	/** minimal constructor */
	public AbstractTblhtpmoa(Long idMoa, Tblhtppermohonan tblhtppermohonan) {
		this.idMoa = idMoa;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpmoa(Long idMoa, Tblhtppermohonan tblhtppermohonan,
			Date tarikhHantar, Date tarikhTerima, String noRujukanMoa,
			Date tarikhTandatangan, Date tarikhDaftar, Date tarikhBayaran,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idMoa = idMoa;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTerima = tarikhTerima;
		this.noRujukanMoa = noRujukanMoa;
		this.tarikhTandatangan = tarikhTandatangan;
		this.tarikhDaftar = tarikhDaftar;
		this.tarikhBayaran = tarikhBayaran;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdMoa() {
		return this.idMoa;
	}

	public void setIdMoa(Long idMoa) {
		this.idMoa = idMoa;
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

	public String getNoRujukanMoa() {
		return this.noRujukanMoa;
	}

	public void setNoRujukanMoa(String noRujukanMoa) {
		this.noRujukanMoa = noRujukanMoa;
	}

	public Date getTarikhTandatangan() {
		return this.tarikhTandatangan;
	}

	public void setTarikhTandatangan(Date tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhBayaran() {
		return this.tarikhBayaran;
	}

	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
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