package ekptg.model.htp.entity;

import java.io.Serializable;

public class PerjanjianPembelian implements Serializable {
	private long idPerjanjianPembelian;
	private HtpPerjanjian htpPerjanjian;
	private String nilaiTanah;
	private String hargaBeli;
	private String bilHakmilikBeli;
	private String tarikhBorang14A;
	private String hargaTambahan;
	private String nilaiBangunan;
	private int bilUnitBeli;
	private String idMasuk;
	private String idKemaskini;
	private String tarikhMasuk;
	private String tarikhKemaskini;
	public long getIdPerjanjianPembelian() {
		return idPerjanjianPembelian;
	}
	public void setIdPerjanjianPembelian(long idPerjanjianPembelian) {
		this.idPerjanjianPembelian = idPerjanjianPembelian;
	}
	public void setIdPerjanjianPembelian(String idPerjanjianPembelian) {
		if(idPerjanjianPembelian.equals(""))
			idPerjanjianPembelian = "0";
		this.idPerjanjianPembelian = Long.parseLong(idPerjanjianPembelian);
	}
	public HtpPerjanjian getHtpPerjanjian() {
		return htpPerjanjian;
	}
	public void setHtpPerjanjian(HtpPerjanjian htpPerjanjian) {
		this.htpPerjanjian = htpPerjanjian;
	}
	public String getNilaiTanah() {
		return nilaiTanah;
	}
//	public void setNilaiTanah(double nilaiTanah) {
//		this.nilaiTanah = nilaiTanah;
//	}
	public void setNilaiTanah(String nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}
	public String getHargaBeli() {
		return hargaBeli;
	}
	public void setHargaBeli(String hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getBilHakmilikBeli() {
		return bilHakmilikBeli;
	}
	public void setBilHakmilikBeli(String bilHakmilikBeli) {
		this.bilHakmilikBeli = bilHakmilikBeli;
	}
	public String getTarikhBorang14A() {
		return tarikhBorang14A;
	}
	public void setTarikhBorang14A(String tarikhBorang14A) {
		this.tarikhBorang14A = tarikhBorang14A;
	}
	public String getNilaiBangunan() {
		return nilaiBangunan;
	}
	public String getHargaTambahan() {
		return hargaTambahan;
	}
	public void setHargaTambahan(String hargaTambahan) {
		this.hargaTambahan = hargaTambahan;
	}
	public void setNilaiBangunan(String nilaiBangunan) {
		this.nilaiBangunan = nilaiBangunan;
	}

	public int getBilUnitBeli() {
		return bilUnitBeli;
	}
	public void setBilUnitBeli(int bilUnitBeli) {
		this.bilUnitBeli = bilUnitBeli;
	}
	public void setBilUnitBeli(String bilUnitBeli) {
		if(bilUnitBeli != null && !bilUnitBeli.equals(""))
			this.bilUnitBeli =  Integer.parseInt(bilUnitBeli);
		else
			this.bilUnitBeli = 0;
			
	}
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	
	
}
