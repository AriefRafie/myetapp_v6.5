package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkperintahhtaobmst entity provides the base persistence
 * definition of the Tblppkperintahhtaobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperintahhtaobmst implements
		java.io.Serializable {

	// Fields

	private Long idPerintahhtaobmst;
	private Tblppkperintah tblppkperintah;
	private Tblppkhta tblppkhta;
	private String catatan;
	private Double nilaiBersih;
	private Double cukaiHarta;
	private String namaPembayarCukai;
	private Date tarikhJualan;
	private Double amaun;
	private String jenisLelong;
	private Double hargaRizab;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkperintahhtaobdtls = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkperintahhtaobmst() {
	}

	/** minimal constructor */
	public AbstractTblppkperintahhtaobmst(Long idPerintahhtaobmst,
			Tblppkperintah tblppkperintah, Tblppkhta tblppkhta) {
		this.idPerintahhtaobmst = idPerintahhtaobmst;
		this.tblppkperintah = tblppkperintah;
		this.tblppkhta = tblppkhta;
	}

	/** full constructor */
	public AbstractTblppkperintahhtaobmst(Long idPerintahhtaobmst,
			Tblppkperintah tblppkperintah, Tblppkhta tblppkhta, String catatan,
			Double nilaiBersih, Double cukaiHarta, String namaPembayarCukai,
			Date tarikhJualan, Double amaun, String jenisLelong,
			Double hargaRizab, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkperintahhtaobdtls) {
		this.idPerintahhtaobmst = idPerintahhtaobmst;
		this.tblppkperintah = tblppkperintah;
		this.tblppkhta = tblppkhta;
		this.catatan = catatan;
		this.nilaiBersih = nilaiBersih;
		this.cukaiHarta = cukaiHarta;
		this.namaPembayarCukai = namaPembayarCukai;
		this.tarikhJualan = tarikhJualan;
		this.amaun = amaun;
		this.jenisLelong = jenisLelong;
		this.hargaRizab = hargaRizab;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkperintahhtaobdtls = tblppkperintahhtaobdtls;
	}

	// Property accessors

	public Long getIdPerintahhtaobmst() {
		return this.idPerintahhtaobmst;
	}

	public void setIdPerintahhtaobmst(Long idPerintahhtaobmst) {
		this.idPerintahhtaobmst = idPerintahhtaobmst;
	}

	public Tblppkperintah getTblppkperintah() {
		return this.tblppkperintah;
	}

	public void setTblppkperintah(Tblppkperintah tblppkperintah) {
		this.tblppkperintah = tblppkperintah;
	}

	public Tblppkhta getTblppkhta() {
		return this.tblppkhta;
	}

	public void setTblppkhta(Tblppkhta tblppkhta) {
		this.tblppkhta = tblppkhta;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Double getNilaiBersih() {
		return this.nilaiBersih;
	}

	public void setNilaiBersih(Double nilaiBersih) {
		this.nilaiBersih = nilaiBersih;
	}

	public Double getCukaiHarta() {
		return this.cukaiHarta;
	}

	public void setCukaiHarta(Double cukaiHarta) {
		this.cukaiHarta = cukaiHarta;
	}

	public String getNamaPembayarCukai() {
		return this.namaPembayarCukai;
	}

	public void setNamaPembayarCukai(String namaPembayarCukai) {
		this.namaPembayarCukai = namaPembayarCukai;
	}

	public Date getTarikhJualan() {
		return this.tarikhJualan;
	}

	public void setTarikhJualan(Date tarikhJualan) {
		this.tarikhJualan = tarikhJualan;
	}

	public Double getAmaun() {
		return this.amaun;
	}

	public void setAmaun(Double amaun) {
		this.amaun = amaun;
	}

	public String getJenisLelong() {
		return this.jenisLelong;
	}

	public void setJenisLelong(String jenisLelong) {
		this.jenisLelong = jenisLelong;
	}

	public Double getHargaRizab() {
		return this.hargaRizab;
	}

	public void setHargaRizab(Double hargaRizab) {
		this.hargaRizab = hargaRizab;
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

	public Set getTblppkperintahhtaobdtls() {
		return this.tblppkperintahhtaobdtls;
	}

	public void setTblppkperintahhtaobdtls(Set tblppkperintahhtaobdtls) {
		this.tblppkperintahhtaobdtls = tblppkperintahhtaobdtls;
	}

}