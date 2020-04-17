package ekptg.model.htp.cukai.entity;

import java.io.Serializable;

import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujnegeri;


public class CukaiUtama implements Serializable {
	public static final String TABLENAME="TBLHTPCUKAIUTAMA";
	private long idCukaiUtama;
	private long idPeringkatBayaran;
	private int jumlahHakmilik;
	private double jumlahCukai;
	private double jumlahBayar;
	private String tahun;
	private long idDaerah;
	private long idNegeri;
	private long idMasuk;
	private String tarikhMasuk;
	private String idKemaskini;
	private String tarikhKemaskini;		
	private Tblrujnegeri rujNegeri;
	private Tblrujdaerah rujDaerah;
	
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public int getBilanganHakmilik() {
		return jumlahHakmilik;
	}
	public void setBilanganHakmilik(int jumlahHakmilik) {
		this.jumlahHakmilik = jumlahHakmilik;
	}
	public double getJumlahBayar() {
		return jumlahBayar;
	}
	public void setJumlahBayar(double jumlahBayar) {
		this.jumlahBayar = jumlahBayar;
	}

	public double getJumlahCukai() {
		return jumlahCukai;
	}
	public void setJumlahCukai(double jumlahCukai) {
		this.jumlahCukai = jumlahCukai;
	}
	public long getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(long idDaerah) {
		this.idDaerah = idDaerah;
	}
	public long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public Tblrujnegeri getRujNegeri() {
		return rujNegeri;
	}
	public void setRujNegeri(Tblrujnegeri rujNegeri) {
		this.rujNegeri = rujNegeri;
	}
	public Tblrujdaerah getRujDaerah() {
		return rujDaerah;
	}
	public void setRujDaerah(Tblrujdaerah rujDaerah) {
		this.rujDaerah = rujDaerah;
	}

}
