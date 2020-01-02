package ekptg.model.htp.cukai.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ekptg.model.entities.Daerah;
import ekptg.model.entities.Negeri;
import ekptg.model.htp.entity.Baucer;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.Resit;

public class BayaranCukai implements Serializable {
	private long id;
	private long idPeringkat;
	private String noRujukan;
	private String statusBayaran = "";
	private double jumlah = 0.00;
	private Date tarikh;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	Bayaran bayaran = null;
	Baucer baucer = null;
	Resit resit = null;
	Negeri negeri;
	Daerah daerah;

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
	
	public Bayaran getBayaran() {
		return bayaran;
	}
	public void setBayaran(Bayaran bayaran) {
		this.bayaran = bayaran;
	}
	
	public Baucer getBaucer() {
		return baucer;
	}
	public void setBaucer(Baucer baucer) {
		this.baucer = baucer;
	}

	public long getIdPeringkat() {
		return idPeringkat;
	}
	public void setIdPeringkat(long idPeringkat) {
		this.idPeringkat = idPeringkat;
	}		
	
	public Resit getResit() {
		return resit;
	}
	public void setResit(Resit resit) {
		this.resit = resit;
	}

	public Negeri getNegeri() {
		if (this.negeri == null)negeri = null;
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	
	public Daerah getDaerah() {
		if (this.daerah == null)daerah = null;
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}

	public String getStatusBayaran() {
		return statusBayaran;
	}
	public void setStatusBayaran(String statusBayaran) {
		this.statusBayaran = statusBayaran;
	}
	
}
