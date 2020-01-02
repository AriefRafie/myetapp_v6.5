package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperjanjianpembelian entity provides the base persistence
 * definition of the Tblhtpperjanjianpembelian entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjianpembelian implements
		java.io.Serializable {

	// Fields

	private Long idPerjanjianpembelian;
	private Long idPerjanjian;
	private Double nilaiTanah;
	private Double hargaBeli;
	private String bilHakmilikbeli;
	private String bilPetakBeli;
	private String bilUnitBeli;
	private Date tarikhBorang14a;
	private Double hargaTambahan;
	private Date tarikhSerahBangunan;
	private Double nilaiBangunan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjianpembelian() {
	}

	/** minimal constructor */
	public AbstractTblhtpperjanjianpembelian(Long idPerjanjianpembelian,
			Long idPerjanjian) {
		this.idPerjanjianpembelian = idPerjanjianpembelian;
		this.idPerjanjian = idPerjanjian;
	}

	/** full constructor */
	public AbstractTblhtpperjanjianpembelian(Long idPerjanjianpembelian,
			Long idPerjanjian, Double nilaiTanah, Double hargaBeli,
			String bilHakmilikbeli, String bilPetakBeli, String bilUnitBeli,
			Date tarikhBorang14a, Double hargaTambahan,
			Date tarikhSerahBangunan, Double nilaiBangunan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.idPerjanjianpembelian = idPerjanjianpembelian;
		this.idPerjanjian = idPerjanjian;
		this.nilaiTanah = nilaiTanah;
		this.hargaBeli = hargaBeli;
		this.bilHakmilikbeli = bilHakmilikbeli;
		this.bilPetakBeli = bilPetakBeli;
		this.bilUnitBeli = bilUnitBeli;
		this.tarikhBorang14a = tarikhBorang14a;
		this.hargaTambahan = hargaTambahan;
		this.tarikhSerahBangunan = tarikhSerahBangunan;
		this.nilaiBangunan = nilaiBangunan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdPerjanjianpembelian() {
		return this.idPerjanjianpembelian;
	}

	public void setIdPerjanjianpembelian(Long idPerjanjianpembelian) {
		this.idPerjanjianpembelian = idPerjanjianpembelian;
	}

	public Long getIdPerjanjian() {
		return this.idPerjanjian;
	}

	public void setIdPerjanjian(Long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}

	public Double getNilaiTanah() {
		return this.nilaiTanah;
	}

	public void setNilaiTanah(Double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}

	public Double getHargaBeli() {
		return this.hargaBeli;
	}

	public void setHargaBeli(Double hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getBilHakmilikbeli() {
		return this.bilHakmilikbeli;
	}

	public void setBilHakmilikbeli(String bilHakmilikbeli) {
		this.bilHakmilikbeli = bilHakmilikbeli;
	}

	public String getBilPetakBeli() {
		return this.bilPetakBeli;
	}

	public void setBilPetakBeli(String bilPetakBeli) {
		this.bilPetakBeli = bilPetakBeli;
	}

	public String getBilUnitBeli() {
		return this.bilUnitBeli;
	}

	public void setBilUnitBeli(String bilUnitBeli) {
		this.bilUnitBeli = bilUnitBeli;
	}

	public Date getTarikhBorang14a() {
		return this.tarikhBorang14a;
	}

	public void setTarikhBorang14a(Date tarikhBorang14a) {
		this.tarikhBorang14a = tarikhBorang14a;
	}

	public Double getHargaTambahan() {
		return this.hargaTambahan;
	}

	public void setHargaTambahan(Double hargaTambahan) {
		this.hargaTambahan = hargaTambahan;
	}

	public Date getTarikhSerahBangunan() {
		return this.tarikhSerahBangunan;
	}

	public void setTarikhSerahBangunan(Date tarikhSerahBangunan) {
		this.tarikhSerahBangunan = tarikhSerahBangunan;
	}

	public Double getNilaiBangunan() {
		return this.nilaiBangunan;
	}

	public void setNilaiBangunan(Double nilaiBangunan) {
		this.nilaiBangunan = nilaiBangunan;
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