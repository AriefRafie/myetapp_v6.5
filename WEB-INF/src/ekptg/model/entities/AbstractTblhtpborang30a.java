package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpborang30a entity provides the base persistence definition of
 * the Tblhtpborang30a entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpborang30a implements java.io.Serializable {

	// Fields

	private Long idBorang30a;
	private Tblhtppermohonan tblhtppermohonan;
	private Date tarikhSurat;
	private Date tarikhTerima;
	private Date tarikhHantar;
	private Date tarikhTandatanganPtp;
	private Date tarikhDaftar;
	private String noPeserahan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpborang30a() {
	}

	/** minimal constructor */
	public AbstractTblhtpborang30a(Long idBorang30a,
			Tblhtppermohonan tblhtppermohonan) {
		this.idBorang30a = idBorang30a;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtpborang30a(Long idBorang30a,
			Tblhtppermohonan tblhtppermohonan, Date tarikhSurat,
			Date tarikhTerima, Date tarikhHantar, Date tarikhTandatanganPtp,
			Date tarikhDaftar, String noPeserahan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idBorang30a = idBorang30a;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tarikhSurat = tarikhSurat;
		this.tarikhTerima = tarikhTerima;
		this.tarikhHantar = tarikhHantar;
		this.tarikhTandatanganPtp = tarikhTandatanganPtp;
		this.tarikhDaftar = tarikhDaftar;
		this.noPeserahan = noPeserahan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBorang30a() {
		return this.idBorang30a;
	}

	public void setIdBorang30a(Long idBorang30a) {
		this.idBorang30a = idBorang30a;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public Date getTarikhTandatanganPtp() {
		return this.tarikhTandatanganPtp;
	}

	public void setTarikhTandatanganPtp(Date tarikhTandatanganPtp) {
		this.tarikhTandatanganPtp = tarikhTandatanganPtp;
	}

	public Date getTarikhDaftar() {
		return this.tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getNoPeserahan() {
		return this.noPeserahan;
	}

	public void setNoPeserahan(String noPeserahan) {
		this.noPeserahan = noPeserahan;
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