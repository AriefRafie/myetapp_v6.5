package etapp.entity.htp.pembelian;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.htp.Perjanjian;

@Entity
@Table(name = "TBLHTPPERJANJIANPEMBELIAN")
public class PerjanjianPembelian {
	@Id @Column(name="ID_PERJANJIANPEMBELIAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERJANJIAN")
	private Perjanjian perjanjian;

	@Column(name="NILAI_TANAH") 
	private double nilaiTanah = 0.00;
	@Column(name="HARGA_BELI") 
	private double hargaBeli = 0.00;
	@Column(name="BIL_HAKMILIKBELI")
	private String bilHakmilik;
	@Column(name="BIL_PETAK_KBELI")
	private String bilPetak;
	@Column(name="BIL_UNIT_BELI")
	private String bilUnit;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_BORANG14A")
	private Date tarikhBorang14A;
	@Column(name="HARGA_TAMBAHAN") 
	private double hargaTambahan = 0.00;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_SERAH_BANGUNAN")
	private Date tarikhSerahBangunan;	
	@Column(name="NILAI_BANGUNAN") 
	private double nilaiBangunan = 0.00;
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
	public Perjanjian getPerjanjian() {
		return perjanjian;
	}
	public void setPerjanjian(Perjanjian perjanjian) {
		this.perjanjian = perjanjian;
	}
	public double getNilaiTanah() {
		return nilaiTanah;
	}
	public void setNilaiTanah(double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}
	public double getHargaBeli() {
		return hargaBeli;
	}
	public void setHargaBeli(double hargaBeli) {
		this.hargaBeli = hargaBeli;
	}
	public String getBilHakmilik() {
		return bilHakmilik;
	}
	public void setBilHakmilik(String bilHakmilik) {
		this.bilHakmilik = bilHakmilik;
	}
	public String getBilPetak() {
		return bilPetak;
	}
	public void setBilPetak(String bilPetak) {
		this.bilPetak = bilPetak;
	}
	public String getBilUnit() {
		return bilUnit;
	}
	public void setBilUnit(String bilUnit) {
		this.bilUnit = bilUnit;
	}
	public Date getTarikhBorang14A() {
		return tarikhBorang14A;
	}
	public void setTarikhBorang14A(Date tarikhBorang14A) {
		this.tarikhBorang14A = tarikhBorang14A;
	}
	public double getHargaTambahan() {
		return hargaTambahan;
	}
	public void setHargaTambahan(double hargaTambahan) {
		this.hargaTambahan = hargaTambahan;
	}
	public Date getTarikhSerahBangunan() {
		return tarikhSerahBangunan;
	}
	public void setTarikhSerahBangunan(Date tarikhSerahBangunan) {
		this.tarikhSerahBangunan = tarikhSerahBangunan;
	}
	public double getNilaiBangunan() {
		return nilaiBangunan;
	}
	public void setNilaiBangunan(double nilaiBangunan) {
		this.nilaiBangunan = nilaiBangunan;
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
