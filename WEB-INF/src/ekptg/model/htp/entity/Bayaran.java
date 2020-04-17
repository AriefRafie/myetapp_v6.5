package ekptg.model.htp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.util.Util;

public class Bayaran implements Serializable {
	private long idBayaran;
	private long idBank;
	private String noRujukan;
	private String tarikhBayaranStr;
	private String bank;
	private double jumlah = 0.00;
	private Date tarikhBayaran;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	Resit resit = null;
	private long idCaraBayar;
	private Date tarikhTerimaBayaran;
	private String tarikhTerimaBayaranStr;
	private String caraBayar;

	public long getIdBayaran() {
		return idBayaran;
	}
	public void setIdBayaran(long idBayaran) {
		this.idBayaran = idBayaran;
	}
	
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	
	public double getJumlah() {
		return jumlah;
	}
	public String getJumlahFormat() {
		return Util.formatDecimal(jumlah);
	}
	public void setJumlah(double jumlah) {
		this.jumlah = jumlah;
	}
	
	public String getTarikhBayaranFormat() {
		if(sdf.format(tarikhBayaran).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhBayaran);
	}
	public Date getTarikhBayaran() {
		return tarikhBayaran;
	}
	public void setTarikhBayaran(Date tarikhBayaran) {
		this.tarikhBayaran = tarikhBayaran;
	}
	
	public String getTarikhBayaranStr() {
		return tarikhBayaranStr;
	}
	public void setTarikhBayaranStr(String tarikhBayaran) {
		this.tarikhBayaranStr = tarikhBayaran;
	}
	
	public long getIdBank() {
		return idBank;
	}
	public void setIdBank(long idBank) {
		this.idBank = idBank;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public Resit getResit() {
		return resit;
	}
	public void setResit(Resit resit) {
		this.resit = resit;
	}

	public long getIdCaraBayaran() {
		return idCaraBayar;
	}
	public void setIdCaraBayaran(long idCaraBayar) {
		this.idCaraBayar = idCaraBayar;
	}	
	
	public String getTarikhTerimaBayaranFormat() {
		if(tarikhTerimaBayaranStr.equals(""))
			return "";
		else
			return tarikhTerimaBayaranStr.equals("01/01/1900")?"":tarikhTerimaBayaranStr;		

	}
	public Date getTarikhTerimaBayaran() {
		return tarikhTerimaBayaran;
	}
	public void setTarikhTerimaBayaran(Date tarikhTerimaBayaran) {
		this.tarikhTerimaBayaran = tarikhTerimaBayaran;
	}
	
	public String getTarikhTerimaBayaranStr() {
		return tarikhTerimaBayaranStr;
	}
	public void setTarikhTerimaBayaranStr(String tarikhTerimaBayaran) {
		this.tarikhTerimaBayaranStr = tarikhTerimaBayaran;
	}
	
	public String getCaraBayar() {
		return caraBayar;
	}
	public void setCaraBayar(String caraBayar) {
		this.caraBayar = caraBayar;
	}
	

}
