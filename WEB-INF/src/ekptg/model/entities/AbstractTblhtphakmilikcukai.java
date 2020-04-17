package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtphakmilikcukai entity provides the base persistence definition
 * of the Tblhtphakmilikcukai entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikcukai implements
		java.io.Serializable {

	// Fields

	private Long idHakmilikcukai;
	private Long idHakmilikpegangan;
	private Double luas;
	private Double cukai;
	private String luasPetak;
	private String kodBayarancukai;
	private Double cukaiTaliair;
	private Double cukaiParit;
	private Double denda;
	private Double pengurangan;
	private Double pengecualian;
	private String statusProsescukai;
	private Double bayaranLain;
	private String status;
	private Long idMasuk;
	private Long tarikhMasuk;
	private Set tblhtpcukaiterperincis = new HashSet(0);
	private Set tblhtppajakankadars = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikcukai() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikcukai(Long idHakmilikcukai,
			Long idHakmilikpegangan, Long idMasuk) {
		this.idHakmilikcukai = idHakmilikcukai;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtphakmilikcukai(Long idHakmilikcukai,
			Long idHakmilikpegangan, Double luas, Double cukai,
			String luasPetak, String kodBayarancukai, Double cukaiTaliair,
			Double cukaiParit, Double denda, Double pengurangan,
			Double pengecualian, String statusProsescukai, Double bayaranLain,
			String status, Long idMasuk, Long tarikhMasuk,
			Set tblhtpcukaiterperincis, Set tblhtppajakankadars) {
		this.idHakmilikcukai = idHakmilikcukai;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.luas = luas;
		this.cukai = cukai;
		this.luasPetak = luasPetak;
		this.kodBayarancukai = kodBayarancukai;
		this.cukaiTaliair = cukaiTaliair;
		this.cukaiParit = cukaiParit;
		this.denda = denda;
		this.pengurangan = pengurangan;
		this.pengecualian = pengecualian;
		this.statusProsescukai = statusProsescukai;
		this.bayaranLain = bayaranLain;
		this.status = status;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.tblhtpcukaiterperincis = tblhtpcukaiterperincis;
		this.tblhtppajakankadars = tblhtppajakankadars;
	}

	// Property accessors

	public Long getIdHakmilikcukai() {
		return this.idHakmilikcukai;
	}

	public void setIdHakmilikcukai(Long idHakmilikcukai) {
		this.idHakmilikcukai = idHakmilikcukai;
	}

	public Long getIdHakmilikpegangan() {
		return this.idHakmilikpegangan;
	}

	public void setIdHakmilikpegangan(Long idHakmilikpegangan) {
		this.idHakmilikpegangan = idHakmilikpegangan;
	}

	public Double getLuas() {
		return this.luas;
	}

	public void setLuas(Double luas) {
		this.luas = luas;
	}

	public Double getCukai() {
		return this.cukai;
	}

	public void setCukai(Double cukai) {
		this.cukai = cukai;
	}

	public String getLuasPetak() {
		return this.luasPetak;
	}

	public void setLuasPetak(String luasPetak) {
		this.luasPetak = luasPetak;
	}

	public String getKodBayarancukai() {
		return this.kodBayarancukai;
	}

	public void setKodBayarancukai(String kodBayarancukai) {
		this.kodBayarancukai = kodBayarancukai;
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

	public Double getDenda() {
		return this.denda;
	}

	public void setDenda(Double denda) {
		this.denda = denda;
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

	public String getStatusProsescukai() {
		return this.statusProsescukai;
	}

	public void setStatusProsescukai(String statusProsescukai) {
		this.statusProsescukai = statusProsescukai;
	}

	public Double getBayaranLain() {
		return this.bayaranLain;
	}

	public void setBayaranLain(Double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Long getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Long tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Set getTblhtpcukaiterperincis() {
		return this.tblhtpcukaiterperincis;
	}

	public void setTblhtpcukaiterperincis(Set tblhtpcukaiterperincis) {
		this.tblhtpcukaiterperincis = tblhtpcukaiterperincis;
	}

	public Set getTblhtppajakankadars() {
		return this.tblhtppajakankadars;
	}

	public void setTblhtppajakankadars(Set tblhtppajakankadars) {
		this.tblhtppajakankadars = tblhtppajakankadars;
	}

}