package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtphakmilikperihal entity provides the base persistence definition
 * of the Tblhtphakmilikperihal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtphakmilikperihal implements
		java.io.Serializable {

	// Fields

	private Long idPerihal;
	private Long idHakmilikpegangan;
	private String luasPetak;
	private String kegunaanTapak;
	private Double luasBangunan;
	private Double luasParking;
	private Double luasLain;
	private Double luasBelumdibangunkan;
	private String kegunaanTanah;
	private Double luasJalan;
	private Double luasPadang;
	private String status;
	private Long idMasuk;
	private Date tarikhMasuk;

	// Constructors

	/** default constructor */
	public AbstractTblhtphakmilikperihal() {
	}

	/** minimal constructor */
	public AbstractTblhtphakmilikperihal(Long idPerihal,
			Long idHakmilikpegangan, Long idMasuk) {
		this.idPerihal = idPerihal;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtphakmilikperihal(Long idPerihal,
			Long idHakmilikpegangan, String luasPetak, String kegunaanTapak,
			Double luasBangunan, Double luasParking, Double luasLain,
			Double luasBelumdibangunkan, String kegunaanTanah,
			Double luasJalan, Double luasPadang, String status, Long idMasuk,
			Date tarikhMasuk) {
		this.idPerihal = idPerihal;
		this.idHakmilikpegangan = idHakmilikpegangan;
		this.luasPetak = luasPetak;
		this.kegunaanTapak = kegunaanTapak;
		this.luasBangunan = luasBangunan;
		this.luasParking = luasParking;
		this.luasLain = luasLain;
		this.luasBelumdibangunkan = luasBelumdibangunkan;
		this.kegunaanTanah = kegunaanTanah;
		this.luasJalan = luasJalan;
		this.luasPadang = luasPadang;
		this.status = status;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
	}

	// Property accessors

	public Long getIdPerihal() {
		return this.idPerihal;
	}

	public void setIdPerihal(Long idPerihal) {
		this.idPerihal = idPerihal;
	}

	public Long getIdHakmilikpegangan() {
		return this.idHakmilikpegangan;
	}

	public void setIdHakmilikpegangan(Long idHakmilikpegangan) {
		this.idHakmilikpegangan = idHakmilikpegangan;
	}

	public String getLuasPetak() {
		return this.luasPetak;
	}

	public void setLuasPetak(String luasPetak) {
		this.luasPetak = luasPetak;
	}

	public String getKegunaanTapak() {
		return this.kegunaanTapak;
	}

	public void setKegunaanTapak(String kegunaanTapak) {
		this.kegunaanTapak = kegunaanTapak;
	}

	public Double getLuasBangunan() {
		return this.luasBangunan;
	}

	public void setLuasBangunan(Double luasBangunan) {
		this.luasBangunan = luasBangunan;
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

	public Double getLuasBelumdibangunkan() {
		return this.luasBelumdibangunkan;
	}

	public void setLuasBelumdibangunkan(Double luasBelumdibangunkan) {
		this.luasBelumdibangunkan = luasBelumdibangunkan;
	}

	public String getKegunaanTanah() {
		return this.kegunaanTanah;
	}

	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
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

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

}