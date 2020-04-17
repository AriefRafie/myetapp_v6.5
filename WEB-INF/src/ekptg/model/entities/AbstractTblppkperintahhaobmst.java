package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkperintahhaobmst entity provides the base persistence definition
 * of the Tblppkperintahhaobmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperintahhaobmst implements
		java.io.Serializable {

	// Fields

	private Long idPerintahhaobmst;
	private Tblppkperintah tblppkperintah;
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
	private Set tblppkperintahhaobdtls = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkperintahhaobmst() {
	}

	/** minimal constructor */
	public AbstractTblppkperintahhaobmst(Long idPerintahhaobmst,
			Tblppkperintah tblppkperintah) {
		this.idPerintahhaobmst = idPerintahhaobmst;
		this.tblppkperintah = tblppkperintah;
	}

	/** full constructor */
	public AbstractTblppkperintahhaobmst(Long idPerintahhaobmst,
			Tblppkperintah tblppkperintah, String catatan, Double nilaiBersih,
			Double cukaiHarta, String namaPembayarCukai, Date tarikhJualan,
			Double amaun, String jenisLelong, Double hargaRizab, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkperintahhaobdtls) {
		this.idPerintahhaobmst = idPerintahhaobmst;
		this.tblppkperintah = tblppkperintah;
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
		this.tblppkperintahhaobdtls = tblppkperintahhaobdtls;
	}

	// Property accessors

	public Long getIdPerintahhaobmst() {
		return this.idPerintahhaobmst;
	}

	public void setIdPerintahhaobmst(Long idPerintahhaobmst) {
		this.idPerintahhaobmst = idPerintahhaobmst;
	}

	public Tblppkperintah getTblppkperintah() {
		return this.tblppkperintah;
	}

	public void setTblppkperintah(Tblppkperintah tblppkperintah) {
		this.tblppkperintah = tblppkperintah;
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

	public Set getTblppkperintahhaobdtls() {
		return this.tblppkperintahhaobdtls;
	}

	public void setTblppkperintahhaobdtls(Set tblppkperintahhaobdtls) {
		this.tblppkperintahhaobdtls = tblppkperintahhaobdtls;
	}

}