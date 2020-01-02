package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpbayaranroyaltipasir entity provides the base persistence
 * definition of the Tblphpbayaranroyaltipasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbayaranroyaltipasir implements
		java.io.Serializable {

	// Fields

	private Long idBayaranroyaltipasir;
	private Tblphplaporanpasir tblphplaporanpasir;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Double jumlahRoyalti;
	private Set tblphpbayaranperludibayars = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpbayaranroyaltipasir() {
	}

	/** minimal constructor */
	public AbstractTblphpbayaranroyaltipasir(Long idBayaranroyaltipasir) {
		this.idBayaranroyaltipasir = idBayaranroyaltipasir;
	}

	/** full constructor */
	public AbstractTblphpbayaranroyaltipasir(Long idBayaranroyaltipasir,
			Tblphplaporanpasir tblphplaporanpasir, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Double jumlahRoyalti, Set tblphpbayaranperludibayars) {
		this.idBayaranroyaltipasir = idBayaranroyaltipasir;
		this.tblphplaporanpasir = tblphplaporanpasir;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.jumlahRoyalti = jumlahRoyalti;
		this.tblphpbayaranperludibayars = tblphpbayaranperludibayars;
	}

	// Property accessors

	public Long getIdBayaranroyaltipasir() {
		return this.idBayaranroyaltipasir;
	}

	public void setIdBayaranroyaltipasir(Long idBayaranroyaltipasir) {
		this.idBayaranroyaltipasir = idBayaranroyaltipasir;
	}

	public Tblphplaporanpasir getTblphplaporanpasir() {
		return this.tblphplaporanpasir;
	}

	public void setTblphplaporanpasir(Tblphplaporanpasir tblphplaporanpasir) {
		this.tblphplaporanpasir = tblphplaporanpasir;
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

	public Double getJumlahRoyalti() {
		return this.jumlahRoyalti;
	}

	public void setJumlahRoyalti(Double jumlahRoyalti) {
		this.jumlahRoyalti = jumlahRoyalti;
	}

	public Set getTblphpbayaranperludibayars() {
		return this.tblphpbayaranperludibayars;
	}

	public void setTblphpbayaranperludibayars(Set tblphpbayaranperludibayars) {
		this.tblphpbayaranperludibayars = tblphpbayaranperludibayars;
	}

}