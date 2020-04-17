package ekptg.model.htp.entity;

import java.io.Serializable;

public class Rundingan implements Serializable {
	private String idMaklumatMysrt;
	private double hargaBersamaan;
	private double nilaiTanah;
	private double nilaiBangunan;
	private double hargaSetuju;
	private int tempohSerahan;
	private double hargaTawaran;
	private Permohonan permohonan;
	private String unitBersamaan;
	private String keputusan;
	private String ulasan;
	public String getIdMaklumatMysrt() {
		return idMaklumatMysrt;
	}
	public void setIdMaklumatMysrt(long idMaklumatMysrt) {
		this.idMaklumatMysrt = String.valueOf(idMaklumatMysrt);
	}
	public void setIdMaklumatMysrt(String idMaklumatMysrt) {
		this.idMaklumatMysrt = idMaklumatMysrt;
	}
	public double getHargaBersamaan() {
		return hargaBersamaan;
	}
	public void setHargaBersamaan(double hargaBersamaan) {
		this.hargaBersamaan = hargaBersamaan;
	}
	public void setHargaBersamaan(String hargaBersamaan) {
		if(hargaBersamaan.equals(""))
			hargaBersamaan ="0";
		this.hargaBersamaan = Double.parseDouble(hargaBersamaan);
	}
	public String getKeputusan() {
		return keputusan;
	}
	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}
	
	public double getNilaiTanah() {
		return nilaiTanah;
	}
	public void setNilaiTanah(double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}
	public void setNilaiTanah(String nilaiTanah) {
		if(nilaiTanah.equals(""))
			nilaiTanah ="0";
		this.nilaiTanah = Double.parseDouble(nilaiTanah);
	}
	public double getNilaiBangunan() {
		return nilaiBangunan;
	}
	public void setNilaiBangunan(double nilaiBangunan) {
		this.nilaiBangunan = nilaiBangunan;
	}
	public void setNilaiBangunan(String nilaiBangunan) {
		if(nilaiBangunan.equals(""))
			nilaiBangunan = "0";
		this.nilaiBangunan = Double.parseDouble(nilaiBangunan);
	}
	public double getHargaSetuju() {
		return hargaSetuju;
	}
	public void setHargaSetuju(double hargaSetuju) {
		this.hargaSetuju = hargaSetuju;
	}
	public void setHargaSetuju(String hargaSetuju) {
		if(hargaSetuju.equals(""))
			hargaSetuju ="0";
		this.hargaSetuju = Double.parseDouble(hargaSetuju);
	}
	public int getTempohSerahan() {
		return tempohSerahan;
	}
	public void setTempohSerahan(int tempohSerahan) {
		this.tempohSerahan = tempohSerahan;
	}
	public void setTempohSerahan(String tempohSerahan) {
		if(tempohSerahan.equals(""))
			tempohSerahan = "0";
		this.tempohSerahan = Integer.parseInt(tempohSerahan);
	}
	public double getHargaTawaran() {
		return hargaTawaran;
	}
	public void setHargaTawaran(double hargaTawaran) {
		this.hargaTawaran = hargaTawaran;
	}
	public void setHargaTawaran(String hargaTawaran) {
		if(hargaTawaran.equals(""))
			hargaTawaran ="0";
		this.hargaTawaran = Double.parseDouble(hargaTawaran);
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getUnitBersamaan() {
		return unitBersamaan;
	}
	public void setUnitBersamaan(String unitBersamaan) {
		this.unitBersamaan = unitBersamaan;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	
		

}
