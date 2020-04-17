package etapp.entity.htp.rekod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.htp.HakmilikUrusan;

@Entity
@Table(name = "TBLHTPPEMBANGUNAN")
public class MaklumatPembangunan {
	@Id @Column(name="ID_PEMBANGUNAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIKPEGANGAN")
	private HakmilikUrusan hakmilikUrusan;
	
//	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
//	private Hakmilik hakmilik;
	
	@Column(name="JENIS_BINAAN")
	private String jenisBinaan;	
	@Column(name="NO_RUJJKR")
	private String noRujukanJKR;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BINAAN")
	private Date tarikhBina;	
	@Column(name="HARGA_BINAAN")
	private double hargaBinaan;
	@Column(name="CATATAN")
	private String catatan;	
	@Column(name="UNIT_LUAS") 
	private int unitLuas ;
	@Column(name="LUAS")
	private String luas;
	@Column(name="LUAS_HEKTAR")
	private double luasHektar;	
	@Column(name="LUAS_JALAN")
	private double luasJalan;
	@Column(name="LUAS_PADANG")
	private double luasPadang;
	@Column(name="LUAS_BGN")
	private double luasBangunan;
	@Column(name="LUAS_PARKING")
	private double luasParking;
	@Column(name="LUAS_LAIN")
	private double luasLain;
	@Column(name="LUAS_ASAL")
	private double luasAsal;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	public String getJenisBinaan() {
		return jenisBinaan;
	}
	public void setJenisBinaan(String jenisBinaan) {
		this.jenisBinaan = jenisBinaan;
	}
	public String getNoRujukanJKR() {
		return noRujukanJKR;
	}
	public void setNoRujukanJKR(String noRujukanJKR) {
		this.noRujukanJKR = noRujukanJKR;
	}
	public Date getTarikhBina() {
		return tarikhBina;
	}
	public void setTarikhBina(Date tarikhBina) {
		this.tarikhBina = tarikhBina;
	}
	public double getHargaBinaan() {
		return hargaBinaan;
	}
	public void setHargaBinaan(double hargaBinaan) {
		this.hargaBinaan = hargaBinaan;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public int getUnitLuas() {
		return unitLuas;
	}
	public void setUnitLuas(int unitLuas) {
		this.unitLuas = unitLuas;
	}
	public String getLuas() {
		return luas;
	}
	public void setLuas(String luas) {
		this.luas = luas;
	}
	public double getLuasHektar() {
		return luasHektar;
	}
	public void setLuasHektar(double luasHektar) {
		this.luasHektar = luasHektar;
	}
	public double getLuasJalan() {
		return luasJalan;
	}
	public void setLuasJalan(double luasJalan) {
		this.luasJalan = luasJalan;
	}
	public double getLuasPadang() {
		return luasPadang;
	}
	public void setLuasPadang(double luasPadang) {
		this.luasPadang = luasPadang;
	}
	public double getLuasBangunan() {
		return luasBangunan;
	}
	public void setLuasBangunan(double luasBangunan) {
		this.luasBangunan = luasBangunan;
	}
	public double getLuasParking() {
		return luasParking;
	}
	public void setLuasParking(double luasParking) {
		this.luasParking = luasParking;
	}
	public double getLuasLain() {
		return luasLain;
	}
	public void setLuasLain(double luasLain) {
		this.luasLain = luasLain;
	}
	public double getLuasAsal() {
		return luasAsal;
	}
	public void setLuasAsal(double luasAsal) {
		this.luasAsal = luasAsal;
	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public Long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	
}
