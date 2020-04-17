package ekptg.model.htp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HakmilikUrusanTanah implements Serializable {

	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	
	private String hakmilikTukarGanti;
	private String statusSwasta;
	private Date tarikhSwasta;
	private String statusPajakan;
	private Date tarikhPajakan;
	private String statusSewa;	
	private Date tarikhSewa;
	private String statusPelepasan;
	private Date tarikhPelepasan;
	
	public String getHakmilikTukarGanti() {
		return hakmilikTukarGanti;
	}
	public void setHakmilikTukarGanti(String hakmilikTukarGanti) {
		this.hakmilikTukarGanti = hakmilikTukarGanti;
	}
	
	public String getStatusSwasta() {
		if (this.statusSwasta == null)statusSwasta = "";
		return statusSwasta;
	}
	public void setStatusSwasta(String statusSwasta) {
		this.statusSwasta = statusSwasta;
	}

	public Date getTarikhSwasta() {
		return tarikhSwasta;
	}
	public String getTarikhSwastaFormat() {
		if(sdf.format(tarikhSwasta).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhSwasta);
	}
	public void setTarikhSwasta(Date tarikhSwasta) {
		this.tarikhSwasta = tarikhSwasta;
	}
	
	public String getStatusPajakan() {
		if (this.statusPajakan == null)statusPajakan = "";
		return statusPajakan;
	}
	public void setStatusPajakan(String statusPajakan) {
		this.statusPajakan = statusPajakan;
	}

	public Date getTarikhPajakan() {
		return tarikhPajakan;
	}
	public String getTarikhPajakanFormat() {
		if(sdf.format(tarikhPajakan).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhPajakan);
	}
	public void setTarikhPajakan(Date tarikhPajakan) {
		this.tarikhPajakan = tarikhPajakan;
	}
	
	public String getStatusSewa() {
		if (this.statusSewa == null)statusSewa = "";
		return statusSewa;
	}
	public void setStatusSewa(String statusSewa) {
		this.statusSewa = statusSewa;
	}

	public Date getTarikhSewa() {
		return tarikhSewa;
	}
	public String getTarikhSewaFormat() {
		if(sdf.format(tarikhSewa).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhSewa);
	}
	public void setTarikhSewa(Date tarikhSewa) {
		this.tarikhSewa = tarikhSewa;
	}
	
	public String getStatusPelepasan() {
		if (this.statusPelepasan == null)statusPelepasan = "";
		return statusPelepasan;
	}
	public void setStatusPelepasan(String statusPelepasan) {
		this.statusPelepasan = statusPelepasan;
	}

	public Date getTarikhPelepasan() {
		return tarikhPelepasan;
	}
	public String getTarikhPelepasanFormat() {
		if(sdf.format(tarikhPelepasan).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhPelepasan);
	}
	public void setTarikhPelepasan(Date tarikhPelepasan) {
		this.tarikhPelepasan = tarikhPelepasan;
	}


	
}
