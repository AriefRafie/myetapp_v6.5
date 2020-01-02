package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpcukaiterperinci entity provides the base persistence definition
 * of the Tblhtpcukaiterperinci entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpcukaiterperinci implements
		java.io.Serializable {

	// Fields

	private Long idCukaiterperinci;
	private Tblhtphakmilikcukai tblhtphakmilikcukai;
	private Tblhtpcukaiutama tblhtpcukaiutama;
	private Double tunggakan;
	private String tahun;
	private Double cukaiKenaBayar;
	private Double cukaiPerluBayar;
	private Double cukaiDibayar;
	private Double denda;
	private Double bayaranLain;
	private Double cukaiTaliair;
	private Double cukaiParit;
	private Double pengurangan;
	private Double pengecualian;
	private String noResit;
	private Date tarikhResit;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpcukaiterperinci() {
	}

	/** minimal constructor */
	public AbstractTblhtpcukaiterperinci(Long idCukaiterperinci,
			Tblhtphakmilikcukai tblhtphakmilikcukai,
			Tblhtpcukaiutama tblhtpcukaiutama) {
		this.idCukaiterperinci = idCukaiterperinci;
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
		this.tblhtpcukaiutama = tblhtpcukaiutama;
	}

	/** full constructor */
	public AbstractTblhtpcukaiterperinci(Long idCukaiterperinci,
			Tblhtphakmilikcukai tblhtphakmilikcukai,
			Tblhtpcukaiutama tblhtpcukaiutama, Double tunggakan, String tahun,
			Double cukaiKenaBayar, Double cukaiPerluBayar, Double cukaiDibayar,
			Double denda, Double bayaranLain, Double cukaiTaliair,
			Double cukaiParit, Double pengurangan, Double pengecualian,
			String noResit, Date tarikhResit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idCukaiterperinci = idCukaiterperinci;
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
		this.tblhtpcukaiutama = tblhtpcukaiutama;
		this.tunggakan = tunggakan;
		this.tahun = tahun;
		this.cukaiKenaBayar = cukaiKenaBayar;
		this.cukaiPerluBayar = cukaiPerluBayar;
		this.cukaiDibayar = cukaiDibayar;
		this.denda = denda;
		this.bayaranLain = bayaranLain;
		this.cukaiTaliair = cukaiTaliair;
		this.cukaiParit = cukaiParit;
		this.pengurangan = pengurangan;
		this.pengecualian = pengecualian;
		this.noResit = noResit;
		this.tarikhResit = tarikhResit;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdCukaiterperinci() {
		return this.idCukaiterperinci;
	}

	public void setIdCukaiterperinci(Long idCukaiterperinci) {
		this.idCukaiterperinci = idCukaiterperinci;
	}

	public Tblhtphakmilikcukai getTblhtphakmilikcukai() {
		return this.tblhtphakmilikcukai;
	}

	public void setTblhtphakmilikcukai(Tblhtphakmilikcukai tblhtphakmilikcukai) {
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
	}

	public Tblhtpcukaiutama getTblhtpcukaiutama() {
		return this.tblhtpcukaiutama;
	}

	public void setTblhtpcukaiutama(Tblhtpcukaiutama tblhtpcukaiutama) {
		this.tblhtpcukaiutama = tblhtpcukaiutama;
	}

	public Double getTunggakan() {
		return this.tunggakan;
	}

	public void setTunggakan(Double tunggakan) {
		this.tunggakan = tunggakan;
	}

	public String getTahun() {
		return this.tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public Double getCukaiKenaBayar() {
		return this.cukaiKenaBayar;
	}

	public void setCukaiKenaBayar(Double cukaiKenaBayar) {
		this.cukaiKenaBayar = cukaiKenaBayar;
	}

	public Double getCukaiPerluBayar() {
		return this.cukaiPerluBayar;
	}

	public void setCukaiPerluBayar(Double cukaiPerluBayar) {
		this.cukaiPerluBayar = cukaiPerluBayar;
	}

	public Double getCukaiDibayar() {
		return this.cukaiDibayar;
	}

	public void setCukaiDibayar(Double cukaiDibayar) {
		this.cukaiDibayar = cukaiDibayar;
	}

	public Double getDenda() {
		return this.denda;
	}

	public void setDenda(Double denda) {
		this.denda = denda;
	}

	public Double getBayaranLain() {
		return this.bayaranLain;
	}

	public void setBayaranLain(Double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}

	public Double getCukaiTaliair() {
		return this.cukaiTaliair;
	}

	public void setCukaiTaliair(Double cukaiTaliair) {
		this.cukaiTaliair = cukaiTaliair;
	}

	public Double getCukaiParit() {
		return this.cukaiParit;
	}

	public void setCukaiParit(Double cukaiParit) {
		this.cukaiParit = cukaiParit;
	}

	public Double getPengurangan() {
		return this.pengurangan;
	}

	public void setPengurangan(Double pengurangan) {
		this.pengurangan = pengurangan;
	}

	public Double getPengecualian() {
		return this.pengecualian;
	}

	public void setPengecualian(Double pengecualian) {
		this.pengecualian = pengecualian;
	}

	public String getNoResit() {
		return this.noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhResit() {
		return this.tarikhResit;
	}

	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
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