package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphplaporanpasir entity provides the base persistence definition of
 * the Tblphplaporanpasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphplaporanpasir implements
		java.io.Serializable {

	// Fields

	private Long idLaporanpasir;
	private Tblphpjadualkedualesenapb tblphpjadualkedualesenapb;
	private Long bulanPengambilan;
	private Long tahunPengambilan;
	private Double jumlahKuantiti;
	private Long idUnitisipadu;
	private Double jumlahRoyalti;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphpborangk2k3s = new HashSet(0);
	private Set tblphpbayaranroyaltipasirs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphplaporanpasir() {
	}

	/** minimal constructor */
	public AbstractTblphplaporanpasir(Long idLaporanpasir) {
		this.idLaporanpasir = idLaporanpasir;
	}

	/** full constructor */
	public AbstractTblphplaporanpasir(Long idLaporanpasir,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb,
			Long bulanPengambilan, Long tahunPengambilan,
			Double jumlahKuantiti, Long idUnitisipadu, Double jumlahRoyalti,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpborangk2k3s,
			Set tblphpbayaranroyaltipasirs) {
		this.idLaporanpasir = idLaporanpasir;
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
		this.bulanPengambilan = bulanPengambilan;
		this.tahunPengambilan = tahunPengambilan;
		this.jumlahKuantiti = jumlahKuantiti;
		this.idUnitisipadu = idUnitisipadu;
		this.jumlahRoyalti = jumlahRoyalti;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphpborangk2k3s = tblphpborangk2k3s;
		this.tblphpbayaranroyaltipasirs = tblphpbayaranroyaltipasirs;
	}

	// Property accessors

	public Long getIdLaporanpasir() {
		return this.idLaporanpasir;
	}

	public void setIdLaporanpasir(Long idLaporanpasir) {
		this.idLaporanpasir = idLaporanpasir;
	}

	public Tblphpjadualkedualesenapb getTblphpjadualkedualesenapb() {
		return this.tblphpjadualkedualesenapb;
	}

	public void setTblphpjadualkedualesenapb(
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb) {
		this.tblphpjadualkedualesenapb = tblphpjadualkedualesenapb;
	}

	public Long getBulanPengambilan() {
		return this.bulanPengambilan;
	}

	public void setBulanPengambilan(Long bulanPengambilan) {
		this.bulanPengambilan = bulanPengambilan;
	}

	public Long getTahunPengambilan() {
		return this.tahunPengambilan;
	}

	public void setTahunPengambilan(Long tahunPengambilan) {
		this.tahunPengambilan = tahunPengambilan;
	}

	public Double getJumlahKuantiti() {
		return this.jumlahKuantiti;
	}

	public void setJumlahKuantiti(Double jumlahKuantiti) {
		this.jumlahKuantiti = jumlahKuantiti;
	}

	public Long getIdUnitisipadu() {
		return this.idUnitisipadu;
	}

	public void setIdUnitisipadu(Long idUnitisipadu) {
		this.idUnitisipadu = idUnitisipadu;
	}

	public Double getJumlahRoyalti() {
		return this.jumlahRoyalti;
	}

	public void setJumlahRoyalti(Double jumlahRoyalti) {
		this.jumlahRoyalti = jumlahRoyalti;
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

	public Set getTblphpborangk2k3s() {
		return this.tblphpborangk2k3s;
	}

	public void setTblphpborangk2k3s(Set tblphpborangk2k3s) {
		this.tblphpborangk2k3s = tblphpborangk2k3s;
	}

	public Set getTblphpbayaranroyaltipasirs() {
		return this.tblphpbayaranroyaltipasirs;
	}

	public void setTblphpbayaranroyaltipasirs(Set tblphpbayaranroyaltipasirs) {
		this.tblphpbayaranroyaltipasirs = tblphpbayaranroyaltipasirs;
	}

}