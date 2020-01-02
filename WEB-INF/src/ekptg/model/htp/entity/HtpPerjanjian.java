package ekptg.model.htp.entity;

import java.io.Serializable;

public class HtpPerjanjian implements Serializable {
	private long idPerjanjian;
	private Permohonan permohonan;
	private String noRujukanPerjanjian;
	private String tarikhPerjanjian;
	private String tarikhTandatangan;
	private String idMasuk;
	private String tarikhMasuk;
	private String tarikhKemaskini;
	public long getIdPerjanjian() {
		return idPerjanjian;
	}
	public void setIdPerjanjian(long idPerjanjian) {
		this.idPerjanjian = idPerjanjian;
	}
	public void setIdPerjanjian(String idPerjanjian) {
		if(idPerjanjian.equals(""))
			idPerjanjian ="0";
		this.idPerjanjian = Long.parseLong(idPerjanjian);
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getNoRujukanPerjanjian() {
		return noRujukanPerjanjian;
	}
	public void setNoRujukanPerjanjian(String noRujukanPerjanjian) {
		this.noRujukanPerjanjian = noRujukanPerjanjian;
	}
	public String getTarikhPerjanjian() {
		return tarikhPerjanjian;
	}
	public void setTarikhPerjanjian(String tarikhPerjanjian) {
		this.tarikhPerjanjian = tarikhPerjanjian;
	}
	public String getTarikhTandatangan() {
		return tarikhTandatangan;
	}
	public void setTarikhTandatangan(String tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
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
