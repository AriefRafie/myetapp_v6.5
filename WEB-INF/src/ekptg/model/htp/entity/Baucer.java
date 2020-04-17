package ekptg.model.htp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Baucer implements Serializable {
	private Date tarikh;
	private double jumlah = 0.00;
	private int bil=0;
	private long id;
	private String noRujukan;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public void setJumlah(double jumlah) {
		this.jumlah = jumlah;
	}
	
	public Date getTarikh() {
		return tarikh;
	}
	public String getTarikhFormat() {
		if(sdf.format(tarikh).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikh);
	}	
	public void setTarikh(Date tarikh) {
		this.tarikh= tarikh;
	}	
	
	public double getBil() {
		return bil;
	}
	public void setBil(int bil) {
		this.bil = bil;
	}

}
