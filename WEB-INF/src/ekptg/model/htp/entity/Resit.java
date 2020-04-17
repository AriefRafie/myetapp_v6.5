package ekptg.model.htp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Resit implements Serializable {
	private long id;
	private String noRujukan;
	private double jumlah = 0.00;
	private Date tarikh;
	private String tarikhStr = "";
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
		if(tarikhStr.equals(""))
			return "";
		else
			return tarikhStr.equals("01/01/1900")?"":tarikhStr;		
	
	}
	
	public void setTarikh(Date tarikh) {
		this.tarikh= tarikh;
	}
	
	public void setTarikhStr(String tarikhStr) {
		this.tarikhStr = tarikhStr;
		this.tarikh = new Date(tarikhStr);
		
	}

}
