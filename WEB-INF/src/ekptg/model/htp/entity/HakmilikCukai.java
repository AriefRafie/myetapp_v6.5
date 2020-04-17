package ekptg.model.htp.entity;

import ekptg.model.htp.cukai.entity.BayaranCukai;

public class HakmilikCukai {
	private long idHakmilikCukai = 0;
	private double cukai = 0.00;
	private double cukaiTerkini = 0.00;
	private double cukailain = 0.00;
	private double denda = 0.00;
	private double bayaranLain = 0.00;
	private double cukaiTaliAir = 0.00;
	private double cukaiParit = 0.00;
	private double pengurangan = 0.00;
	private double tunggakan = 0.00;
	private double pengecualian = 0.00;
	private double lebihan = 0.00;
	private double jumlah = 0.00;
	private String kodBayaranCukai = "";
	private String statusBayaran = "";
	private BayaranCukai bCukai = null;
	
	public long getIdHakmilikCukai() {
		return idHakmilikCukai;
	}
	public void setIdHakmilikCukai(long idHakmilikCukai) {
		this.idHakmilikCukai = idHakmilikCukai;
	}
	public double getCukaiTerkini() {
		return cukaiTerkini;
	}
	public void setCukaiTerkini(double cukaiTerkini) {
		this.cukaiTerkini = cukaiTerkini;
	}
	public double getCukai() {
		return cukai;
	}
	public void setCukai(double cukai) {
		this.cukai = cukai;
	}
	public String getKodBayaranCukai() {
		return kodBayaranCukai;
	}
	public void setKodBayaranCukai(String kodBayaranCukai) {
		this.kodBayaranCukai = kodBayaranCukai;
	}
	
	public double getDenda() {
		return denda;
	}
	public void setDenda(double denda) {
		this.denda = denda;
	}
	public double getBayaranLain() {
		return bayaranLain;
	}
	public void setBayaranLain(double bayaranLain) {
		this.bayaranLain = bayaranLain;
	}
	public double getCukaiTaliAir() {
		return cukaiTaliAir;
	}
	public void setCukaiTaliAir(double cukaiTaliAir) {
		this.cukaiTaliAir = cukaiTaliAir;
	}
	public double getCukaiParit() {
		return cukaiParit;
	}
	public void setCukaiParit(double cukaiParit) {
		this.cukaiParit = cukaiParit;
	}
	public double getPengurangan() {
		return pengurangan;
	}
	public void setPengurangan(double pengurangan) {
		this.pengurangan = pengurangan;
	}
	public double getPengecualian() {
		return pengecualian;
	}
	public void setPengecualian(double pengecualian) {
		this.pengecualian = pengecualian;
	}
	public double getCukailain() {
		return cukailain;
	}
	public void setCukailain(double cukailain) {
		this.cukailain = cukailain;
	}
	public double getLebihan() {
		return lebihan;
	}
	public void setLebihan(double lebihan) {
		this.lebihan = lebihan;
	}	
	public double getJumlah() {
		return jumlah;
	}
	public void setJumlah(double jumlah) {
		this.jumlah = jumlah;
	}
	public double getTunggakan() {
		return tunggakan;
	}
	public void setTunggakan(double tunggakan) {
		this.tunggakan = tunggakan;
	}
	
	public String getStatusBayaran() {
		return statusBayaran;
	}
	public void setStatusBayaran(String statusBayaran) {
		this.statusBayaran = statusBayaran;
	}
	
	public BayaranCukai getBayaranCukai() {
		return bCukai;
	}
	public void setBayaranCukai(BayaranCukai bCukai) {
		this.bCukai = bCukai;
	}
	
	
}
