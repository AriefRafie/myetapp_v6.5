package ekptg.model.htp.entity;

import java.io.Serializable;
import java.util.Date;

public class HakmilikPajakan extends HakMilik implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long idHakmilikUrusan;
	private long idLuas;
	private double luas;
	private double luasBersamaan;
	private String luasString;
	private long idLuasBersamaan;
	private long idLuasPajakan;
	private double luasPajakan;
	private double luasBersamaanPajakan;
	private String luasStringPajakan;
	private long idLuasBersamaanPajakan;
	private long idMasuk;
	private Date tarikhMasuk;
	private long idKemaskini;
	private Date tarikhKemaskini;
	private String sekatan;
	private String syarat;
	private long idKategori;
	private long idSubKategori;
	private String kategori;
	private String subKategori;
	
	public long getIdHakmilikUrusan() {
		return idHakmilikUrusan;
	}
	public void setIdHakmilikUrusan(long idHakmilikUrusan) {
		this.idHakmilikUrusan = idHakmilikUrusan;
	}
	
	public long getIdLuas() {
		return idLuas;
	}
	public void setIdLuas(long idLuas) {
		this.idLuas = idLuas;
	}
	public double getLuas() {
		return luas;
	}
	public void setLuas(double luas) {
		this.luas = luas;
	}
	
	public long getIdLuasBersamaan() {
		return idLuasBersamaan;
	}
	public void setIdLuasBersamaan(long idLuasBersamaan) {
		this.idLuasBersamaan = idLuasBersamaan;
	}

	public long getIdLuasPajakan() {
		return idLuasPajakan;
	}
	public void setIdLuasPajakan(long idLuasPajakan) {
		this.idLuasPajakan = idLuasPajakan;
	}
	public double getLuasPajakan() {
		return luasPajakan;
	}
	public void setLuasPajakan(double luasPajakan) {
		this.luasPajakan = luasPajakan;
	}
	
	public long getIdLuasBersamaanPajakan() {
		return idLuasBersamaanPajakan;
	}
	public void setIdLuasBersamaanPajakan(long idLuasBersamaanPajakan) {
		this.idLuasBersamaanPajakan = idLuasBersamaanPajakan;
	}	
	
	public double getLuasBersamaanPajakan() {
		return luasBersamaanPajakan;
	}
	public void setLuasBersamaanPajakan(double luasBersamaanPajakan) {
		this.luasBersamaanPajakan = luasBersamaanPajakan;
	}
	
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	
	public long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}	
	public double getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
	}
	public String getLuasString() {
		return luasString;
	}
	public void setLuasString(String luasString) {
		this.luasString = luasString;
	}

	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getLuasStringPajakan() {
		return luasStringPajakan;
	}
	public void setLuasStringPajakan(String luasStringPajakan) {
		this.luasStringPajakan = luasStringPajakan;
	}

	public String getSyarat() {
		return syarat;
	}
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}

	public String getSekatan() {
		return sekatan;
	}
	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}
	
	public long getIdKategori() {
		return idKategori;
	}
	public void setIdKategori(long idKategori) {
		this.idKategori = idKategori;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	
	public long getIdSubKategori() {
		return idSubKategori;
	}
	public void setIdSubKategori(long idSubKategori) {
		this.idSubKategori = idSubKategori;
	}	
	
	public String getSubKategori() {
		return subKategori;
	}
	public void setSubKategori(String subKategori) {
		this.subKategori = subKategori;
	}
	
}
