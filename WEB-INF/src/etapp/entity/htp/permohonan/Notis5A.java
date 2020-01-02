package etapp.entity.htp.permohonan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;

@Entity
@Table(name = "TBLHTPNOTIS5A")
public class Notis5A {
	@Id @Column(name="ID_NOTIS5A")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_NOTIS5A")
	private Date tarikhNotis5A;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Column(name="KADAR_CUKAI") 
	private double cukai = 0.00;
	@Column(name="BAYAR_NOTIS") 
	private double bayarNotis = 0.00;
	@Column(name="BAYAR_DAFTAR_HAKMILIK") 
	private double bayarDaftar = 0.00;
	@Column(name="BAYAR_UKUR") 
	private double bayarUkur = 0.00;
	@Column(name="BAYAR_PREMIUM") 
	private double bayarPremium = 0.00;
	@Column(name="BAYAR_PERENGGAN") 
	private double bayarPerenggan = 0.00;
	@Column(name="BAYAR_SEMPADAN") 
	private double bayarSempadan= 0.00;
	@Column(name="BAYARAN_LAIN") 
	private double bayarLain = 0.00;
	@Column(name="JUMLAH_BAYAR") 
	private double jumlahBayar = 0.00;
	@Column(name="NO_RUJUKAN_MOP")
	private String noRujukan;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_LUPUT1")
	private Date tarikhLuput1;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_LUPUT2")
	private Date tarikhLuput2;	
	@Column(name="RAYUAN_PREMIUM") 
	private double rayuanPremium = 0.00;		
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR_GERAN")
	private Date tarikhHantarGeran;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA_GERAN")
	private Date tarikhTerimaGeran;
	@Column(name="BAYAR_SEDIAHAKMILIK") 
	private double bayarSediaHakmilik = 0.00;		
	@Column(name="BAYAR_WARTA") 
	private double bayarWarta = 0.00;		
	@Column(name="RAYUAN_LAIN") 
	private double rayuanLain= 0.00;		
	@Column(name="PERIHAL_RAYUAN") 
	private String keteranganRayuan;		
	@Column(name="CUKAI_TERTUNGGAK") 
	private double cukaiTertuggak = 0.00;
	@Column(name="BAYAR_TERTUNGGAK") 
	private double bayarTertuggak = 0.00;
	@Column(name="BAYAR_MOHON_LA") 
	private double bayarMohonLA = 0.00;
	@Column(name="DRAF_TITLE") 
	private String tajukDeraf;		
	@Column(name="BAYARAN_LAIN1") 
	private double bayaranLain1= 0.00;		
	@Column(name="BAYARAN_LAIN1_KETERANGAN") 
	private String keteranganBayaranLain1;		
	@Column(name="BAYARAN_LAIN2") 
	private double bayaranLain2= 0.00;		
	@Column(name="BAYARAN_LAIN2_KETERANGAN") 
	private String keteranganBayaranLain2;		
	@Column(name="BAYARAN_LAIN3") 
	private double bayaranLain3= 0.00;		
	@Column(name="BAYARAN_LAIN3_KETERANGAN") 
	private String keteranganBayaranLain3;		
	@Column(name="BAYARAN_PERENGGAN") 
	private double bayaranPerenggan = 0.00;
	@Column(name="BAYARAN_FAIL") 
	private double bayaranFail= 0.00;		
	@Column(name="BAYARAN_NOTISF") 
	private double bayaranNotisF= 0.00;	
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_LUPUT3")
	private Date tarikhLuput;	
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Date getTarikhNotis5A() {
		return tarikhNotis5A;
	}
	public void setTarikhNotis5A(Date tarikhNotis5A) {
		this.tarikhNotis5A = tarikhNotis5A;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}
	public double getBayarNotis() {
		return bayarNotis;
	}
	public void setBayarNotis(double bayarNotis) {
		this.bayarNotis = bayarNotis;
	}
	public double getBayarDaftar() {
		return bayarDaftar;
	}
	public void setBayarDaftar(double bayarDaftar) {
		this.bayarDaftar = bayarDaftar;
	}
	public double getBayarUkur() {
		return bayarUkur;
	}
	public void setBayarUkur(double bayarUkur) {
		this.bayarUkur = bayarUkur;
	}
	public double getBayarPremium() {
		return bayarPremium;
	}
	public void setBayarPremium(double bayarPremium) {
		this.bayarPremium = bayarPremium;
	}
	public double getBayarPerenggan() {
		return bayarPerenggan;
	}
	public void setBayarPerenggan(double bayarPerenggan) {
		this.bayarPerenggan = bayarPerenggan;
	}
	public double getBayarSempadan() {
		return bayarSempadan;
	}
	public void setBayarSempadan(double bayarSempadan) {
		this.bayarSempadan = bayarSempadan;
	}
	public double getBayarLain() {
		return bayarLain;
	}
	public void setBayarLain(double bayarLain) {
		this.bayarLain = bayarLain;
	}
	public double getJumlahBayar() {
		return jumlahBayar;
	}
	public void setJumlahBayar(double jumlahBayar) {
		this.jumlahBayar = jumlahBayar;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public Date getTarikhLuput1() {
		return tarikhLuput1;
	}
	public void setTarikhLuput1(Date tarikhLuput1) {
		this.tarikhLuput1 = tarikhLuput1;
	}
	public Date getTarikhLuput2() {
		return tarikhLuput2;
	}
	public void setTarikhLuput2(Date tarikhLuput2) {
		this.tarikhLuput2 = tarikhLuput2;
	}
	public double getRayuanPremium() {
		return rayuanPremium;
	}
	public void setRayuanPremium(double rayuanPremium) {
		this.rayuanPremium = rayuanPremium;
	}
	public Date getTarikhHantarGeran() {
		return tarikhHantarGeran;
	}
	public void setTarikhHantarGeran(Date tarikhHantarGeran) {
		this.tarikhHantarGeran = tarikhHantarGeran;
	}
	public Date getTarikhTerimaGeran() {
		return tarikhTerimaGeran;
	}
	public void setTarikhTerimaGeran(Date tarikhTerimaGeran) {
		this.tarikhTerimaGeran = tarikhTerimaGeran;
	}
	public double getBayarSediaHakmilik() {
		return bayarSediaHakmilik;
	}
	public void setBayarSediaHakmilik(double bayarSediaHakmilik) {
		this.bayarSediaHakmilik = bayarSediaHakmilik;
	}
	public double getBayarWarta() {
		return bayarWarta;
	}
	public void setBayarWarta(double bayarWarta) {
		this.bayarWarta = bayarWarta;
	}
	public double getRayuanLain() {
		return rayuanLain;
	}
	public void setRayuanLain(double rayuanLain) {
		this.rayuanLain = rayuanLain;
	}
	public String getKeteranganRayuan() {
		return keteranganRayuan;
	}
	public void setKeteranganRayuan(String keteranganRayuan) {
		this.keteranganRayuan = keteranganRayuan;
	}
	public double getCukaiTertuggak() {
		return cukaiTertuggak;
	}
	public void setCukaiTertuggak(double cukaiTertuggak) {
		this.cukaiTertuggak = cukaiTertuggak;
	}
	public double getBayarTertuggak() {
		return bayarTertuggak;
	}
	public void setBayarTertuggak(double bayarTertuggak) {
		this.bayarTertuggak = bayarTertuggak;
	}
	public double getBayarMohonLA() {
		return bayarMohonLA;
	}
	public void setBayarMohonLA(double bayarMohonLA) {
		this.bayarMohonLA = bayarMohonLA;
	}
	public String getTajukDeraf() {
		return tajukDeraf;
	}
	public void setTajukDeraf(String tajukDeraf) {
		this.tajukDeraf = tajukDeraf;
	}
	public double getBayaranLain1() {
		return bayaranLain1;
	}
	public void setBayaranLain1(double bayaranLain1) {
		this.bayaranLain1 = bayaranLain1;
	}
	public String getKeteranganBayaranLain1() {
		return keteranganBayaranLain1;
	}
	public void setKeteranganBayaranLain1(String keteranganBayaranLain1) {
		this.keteranganBayaranLain1 = keteranganBayaranLain1;
	}
	public double getBayaranLain2() {
		return bayaranLain2;
	}
	public void setBayaranLain2(double bayaranLain2) {
		this.bayaranLain2 = bayaranLain2;
	}
	public String getKeteranganBayaranLain2() {
		return keteranganBayaranLain2;
	}
	public void setKeteranganBayaranLain2(String keteranganBayaranLain2) {
		this.keteranganBayaranLain2 = keteranganBayaranLain2;
	}
	public double getBayaranLain3() {
		return bayaranLain3;
	}
	public void setBayaranLain3(double bayaranLain3) {
		this.bayaranLain3 = bayaranLain3;
	}
	public String getKeteranganBayaranLain3() {
		return keteranganBayaranLain3;
	}
	public void setKeteranganBayaranLain3(String keteranganBayaranLain3) {
		this.keteranganBayaranLain3 = keteranganBayaranLain3;
	}
	public double getBayaranPerenggan() {
		return bayaranPerenggan;
	}
	public void setBayaranPerenggan(double bayaranPerenggan) {
		this.bayaranPerenggan = bayaranPerenggan;
	}
	public double getBayaranFail() {
		return bayaranFail;
	}
	public void setBayaranFail(double bayaranFail) {
		this.bayaranFail = bayaranFail;
	}
	public double getBayaranNotisF() {
		return bayaranNotisF;
	}
	public void setBayaranNotisF(double bayaranNotisF) {
		this.bayaranNotisF = bayaranNotisF;
	}
	public Date getTarikhLuput() {
		return tarikhLuput;
	}
	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
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
