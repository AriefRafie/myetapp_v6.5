package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppembangunan entity provides the base persistence definition of
 * the Tblhtppembangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppembangunan implements java.io.Serializable {

	// Fields

	private Long idPembangunan;
	private Long idHakmilikpegangan;
	private String jenisBinaan;
	private String noRujjkr;
	private Date tarikhBinaan;
	private Double hargaBinaan;
	private String catatan;
	private String unitLuas;
	private String luas;
	private Double luasHektar;
	private Double luasJalan;
	private Double luasPadang;
	private Double luasBgn;
	private Double luasParking;
	private Double luasLain;
	private Double luasAsal;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtppembangunan() {
	}

	/** minimal constructor */
	public AbstractTblhtppembangunan(Long idPembangunan,
			Long idHakmilikpegangan, Long idMasuk) {
		this.idPembangunan = idPembangunan;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtppembangunan(Long idPembangunan,
			Long idHakmilikpegangan, String jenisBinaan, String noRujjkr,
			Date tarikhBinaan, Double hargaBinaan, String catatan,
			String unitLuas, String luas, Double luasHektar, Double luasJalan,
			Double luasPadang, Double luasBgn, Double luasParking,
			Double luasLain, Double luasAsal, Long idMasuk, Date tarikhMasuk) {
		this.idPembangunan = idPembangunan;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.jenisBinaan = jenisBinaan;
		this.noRujjkr = noRujjkr;
		this.tarikhBinaan = tarikhBinaan;
		this.hargaBinaan = hargaBinaan;
		this.catatan = catatan;
		this.unitLuas = unitLuas;
		this.luas = luas;
		this.luasHektar = luasHektar;
		this.luasJalan = luasJalan;
		this.luasPadang = luasPadang;
		this.luasBgn = luasBgn;
		this.luasParking = luasParking;
		this.luasLain = luasLain;
		this.luasAsal = luasAsal;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public Long getIdPembangunan() {
		return this.idPembangunan;
	}

	public void setIdPembangunan(Long idPembangunan) {
		this.idPembangunan = idPembangunan;
	}

	public Long getIdHakmilikpegangan() {
		return this.idHakmilikpegangan;
	}

	public void setIdHakmilikpegangan(Long idHakmilikpegangan) {
		this.idHakmilikpegangan = idHakmilikpegangan;
	}

	public String getJenisBinaan() {
		return this.jenisBinaan;
	}

	public void setJenisBinaan(String jenisBinaan) {
		this.jenisBinaan = jenisBinaan;
	}

	public String getNoRujjkr() {
		return this.noRujjkr;
	}

	public void setNoRujjkr(String noRujjkr) {
		this.noRujjkr = noRujjkr;
	}

	public Date getTarikhBinaan() {
		return this.tarikhBinaan;
	}

	public void setTarikhBinaan(Date tarikhBinaan) {
		this.tarikhBinaan = tarikhBinaan;
	}

	public Double getHargaBinaan() {
		return this.hargaBinaan;
	}

	public void setHargaBinaan(Double hargaBinaan) {
		this.hargaBinaan = hargaBinaan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getUnitLuas() {
		return this.unitLuas;
	}

	public void setUnitLuas(String unitLuas) {
		this.unitLuas = unitLuas;
	}

	public String getLuas() {
		return this.luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public Double getLuasHektar() {
		return this.luasHektar;
	}

	public void setLuasHektar(Double luasHektar) {
		this.luasHektar = luasHektar;
	}

	public Double getLuasJalan() {
		return this.luasJalan;
	}

	public void setLuasJalan(Double luasJalan) {
		this.luasJalan = luasJalan;
	}

	public Double getLuasPadang() {
		return this.luasPadang;
	}

	public void setLuasPadang(Double luasPadang) {
		this.luasPadang = luasPadang;
	}

	public Double getLuasBgn() {
		return this.luasBgn;
	}

	public void setLuasBgn(Double luasBgn) {
		this.luasBgn = luasBgn;
	}

	public Double getLuasParking() {
		return this.luasParking;
	}

	public void setLuasParking(Double luasParking) {
		this.luasParking = luasParking;
	}

	public Double getLuasLain() {
		return this.luasLain;
	}

	public void setLuasLain(Double luasLain) {
		this.luasLain = luasLain;
	}

	public Double getLuasAsal() {
		return this.luasAsal;
	}

	public void setLuasAsal(Double luasAsal) {
		this.luasAsal = luasAsal;
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

}