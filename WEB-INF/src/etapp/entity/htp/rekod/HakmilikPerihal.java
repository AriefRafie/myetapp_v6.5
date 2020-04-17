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

import etapp.entity.rujukan.Luas;

@Entity
@Table(name = "TBLHTPHAKMILIKPERIHAL")
public class HakmilikPerihal {
	@Id @Column(name="ID_HAKMILIKPERIHAL")
	private long id;
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private Hakmilik hakmilik;
	@ManyToOne @JoinColumn(name="ID_LUAS")
	private Luas luasLama;		
	@ManyToOne @JoinColumn(name="ID_LUAS_BERSAMAAN")
	private Luas luasBaru;		

	@Column(name="KEGUNAAN_TAPAK") 
	private String kegunaanTapak ;
	@Column(name="KEGUNAAN_TANAH") 
	private String kegunaanTanah ;
	@Column(name="LUAS_PETAK")
	private double luasPetak;
	@Column(name="LUAS_BANGUNAN")
	private double luasBangunan;
	@Column(name="LUAS_PARKING")
	private double luasParking;
	@Column(name="LUASLAIN")
	private double luasLain;
	@Column(name="LUAS_BELUMDIBANGUNKAN")
	private double luasBelumDibangun;
	@Column(name="LUAS_JALAN")
	private double luasJalan;
	@Column(name="LUAS_PADANG")
	private double luasPadang;
	@Column(name="STATUS")
	private String status;	
	@Column(name="JENIS_BINAAN")
	private String jenisBinaan;	
	@Column(name="NO_RUJUKAN_JKR")
	private String noRujukanJKR;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BINAAN")
	private Date tarikhBina;	
	@Column(name="HARGA_BINAAN")
	private double hargaBinaan;
	@Column(name="CATATAN")
	private String catatan;	
	@Column(name="LUAS_HEKTAR")
	private String luasHektar;	
	@Column(name="UNIT_LUAS") 
	private int unitLuas ;
	@Column(name="LUAS")
	private String luas;
	@Column(name="LUAS_BERSAMAAN")
	private double luasBersamaan;
		
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
	public Hakmilik getHakmilik() {
		return hakmilik;
	}
	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}
	public Luas getLuasLama() {
		return luasLama;
	}
	public void setLuasLama(Luas luasLama) {
		this.luasLama = luasLama;
	}
	public Luas getLuasBaru() {
		return luasBaru;
	}
	public void setLuasBaru(Luas luasBaru) {
		this.luasBaru = luasBaru;
	}
	public String getKegunaanTapak() {
		return kegunaanTapak;
	}
	public void setKegunaanTapak(String kegunaanTapak) {
		this.kegunaanTapak = kegunaanTapak;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public double getLuasPetak() {
		return luasPetak;
	}
	public void setLuasPetak(double luasPetak) {
		this.luasPetak = luasPetak;
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
	public double getLuasBelumDibangun() {
		return luasBelumDibangun;
	}
	public void setLuasBelumDibangun(double luasBelumDibangun) {
		this.luasBelumDibangun = luasBelumDibangun;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getLuasHektar() {
		return luasHektar;
	}
	public void setLuasHektar(String luasHektar) {
		this.luasHektar = luasHektar;
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
	public double getLuasBersamaan() {
		return luasBersamaan;
	}
	public void setLuasBersamaan(double luasBersamaan) {
		this.luasBersamaan = luasBersamaan;
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
