package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpbayaranperludibayar entity provides the base persistence
 * definition of the Tblphpbayaranperludibayar entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpbayaranperludibayar implements
		java.io.Serializable {

	// Fields

	private Long idBayaranperludibayar;
	private Tblphpbayaransewa tblphpbayaransewa;
	private Tblphpbayaranroyaltipasir tblphpbayaranroyaltipasir;
	private Double amaunPerludibayar;
	private Double amaunSemasa;
	private Double amaunTunggakan;
	private Long bulanTunggakDari;
	private Long tahunTunggakDari;
	private Long bulanTunggakHingga;
	private Long tahunTunggakHingga;
	private Double bakiAwal;
	private Double bakiAkhir;
	private Long idHasil;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppenyelarasanbayarans = new HashSet(0);
	private Set tblphpbayarans = new HashSet(0);
	private Set tblphpbils = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpbayaranperludibayar() {
	}

	/** minimal constructor */
	public AbstractTblphpbayaranperludibayar(Long idBayaranperludibayar) {
		this.idBayaranperludibayar = idBayaranperludibayar;
	}

	/** full constructor */
	public AbstractTblphpbayaranperludibayar(Long idBayaranperludibayar,
			Tblphpbayaransewa tblphpbayaransewa,
			Tblphpbayaranroyaltipasir tblphpbayaranroyaltipasir,
			Double amaunPerludibayar, Double amaunSemasa,
			Double amaunTunggakan, Long bulanTunggakDari,
			Long tahunTunggakDari, Long bulanTunggakHingga,
			Long tahunTunggakHingga, Double bakiAwal, Double bakiAkhir,
			Long idHasil, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppenyelarasanbayarans,
			Set tblphpbayarans, Set tblphpbils) {
		this.idBayaranperludibayar = idBayaranperludibayar;
		this.tblphpbayaransewa = tblphpbayaransewa;
		this.tblphpbayaranroyaltipasir = tblphpbayaranroyaltipasir;
		this.amaunPerludibayar = amaunPerludibayar;
		this.amaunSemasa = amaunSemasa;
		this.amaunTunggakan = amaunTunggakan;
		this.bulanTunggakDari = bulanTunggakDari;
		this.tahunTunggakDari = tahunTunggakDari;
		this.bulanTunggakHingga = bulanTunggakHingga;
		this.tahunTunggakHingga = tahunTunggakHingga;
		this.bakiAwal = bakiAwal;
		this.bakiAkhir = bakiAkhir;
		this.idHasil = idHasil;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppenyelarasanbayarans = tblphppenyelarasanbayarans;
		this.tblphpbayarans = tblphpbayarans;
		this.tblphpbils = tblphpbils;
	}

	// Property accessors

	public Long getIdBayaranperludibayar() {
		return this.idBayaranperludibayar;
	}

	public void setIdBayaranperludibayar(Long idBayaranperludibayar) {
		this.idBayaranperludibayar = idBayaranperludibayar;
	}

	public Tblphpbayaransewa getTblphpbayaransewa() {
		return this.tblphpbayaransewa;
	}

	public void setTblphpbayaransewa(Tblphpbayaransewa tblphpbayaransewa) {
		this.tblphpbayaransewa = tblphpbayaransewa;
	}

	public Tblphpbayaranroyaltipasir getTblphpbayaranroyaltipasir() {
		return this.tblphpbayaranroyaltipasir;
	}

	public void setTblphpbayaranroyaltipasir(
			Tblphpbayaranroyaltipasir tblphpbayaranroyaltipasir) {
		this.tblphpbayaranroyaltipasir = tblphpbayaranroyaltipasir;
	}

	public Double getAmaunPerludibayar() {
		return this.amaunPerludibayar;
	}

	public void setAmaunPerludibayar(Double amaunPerludibayar) {
		this.amaunPerludibayar = amaunPerludibayar;
	}

	public Double getAmaunSemasa() {
		return this.amaunSemasa;
	}

	public void setAmaunSemasa(Double amaunSemasa) {
		this.amaunSemasa = amaunSemasa;
	}

	public Double getAmaunTunggakan() {
		return this.amaunTunggakan;
	}

	public void setAmaunTunggakan(Double amaunTunggakan) {
		this.amaunTunggakan = amaunTunggakan;
	}

	public Long getBulanTunggakDari() {
		return this.bulanTunggakDari;
	}

	public void setBulanTunggakDari(Long bulanTunggakDari) {
		this.bulanTunggakDari = bulanTunggakDari;
	}

	public Long getTahunTunggakDari() {
		return this.tahunTunggakDari;
	}

	public void setTahunTunggakDari(Long tahunTunggakDari) {
		this.tahunTunggakDari = tahunTunggakDari;
	}

	public Long getBulanTunggakHingga() {
		return this.bulanTunggakHingga;
	}

	public void setBulanTunggakHingga(Long bulanTunggakHingga) {
		this.bulanTunggakHingga = bulanTunggakHingga;
	}

	public Long getTahunTunggakHingga() {
		return this.tahunTunggakHingga;
	}

	public void setTahunTunggakHingga(Long tahunTunggakHingga) {
		this.tahunTunggakHingga = tahunTunggakHingga;
	}

	public Double getBakiAwal() {
		return this.bakiAwal;
	}

	public void setBakiAwal(Double bakiAwal) {
		this.bakiAwal = bakiAwal;
	}

	public Double getBakiAkhir() {
		return this.bakiAkhir;
	}

	public void setBakiAkhir(Double bakiAkhir) {
		this.bakiAkhir = bakiAkhir;
	}

	public Long getIdHasil() {
		return this.idHasil;
	}

	public void setIdHasil(Long idHasil) {
		this.idHasil = idHasil;
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

	public Set getTblphppenyelarasanbayarans() {
		return this.tblphppenyelarasanbayarans;
	}

	public void setTblphppenyelarasanbayarans(Set tblphppenyelarasanbayarans) {
		this.tblphppenyelarasanbayarans = tblphppenyelarasanbayarans;
	}

	public Set getTblphpbayarans() {
		return this.tblphpbayarans;
	}

	public void setTblphpbayarans(Set tblphpbayarans) {
		this.tblphpbayarans = tblphpbayarans;
	}

	public Set getTblphpbils() {
		return this.tblphpbils;
	}

	public void setTblphpbils(Set tblphpbils) {
		this.tblphpbils = tblphpbils;
	}

}