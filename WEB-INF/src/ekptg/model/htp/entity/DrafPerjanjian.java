package ekptg.model.htp.entity;

import java.io.Serializable;

public class DrafPerjanjian implements Serializable {
	private long idDrafPerjanjian;
	private String idMasuk;
	private Permohonan permohonan;
	private String tarikhTerima;
	private String tarikhHantar;
	private String ulasan;
	public long getIdDrafPerjanjian() {
		return idDrafPerjanjian;
	}
	public void setIdDrafPerjanjian(long idDrafPerjanjian) {
		this.idDrafPerjanjian = idDrafPerjanjian;
	}
	public void setIdDrafPerjanjian(String idDrafPerjanjian) {
		if(idDrafPerjanjian.equals(""))
			idDrafPerjanjian ="0";
		this.idDrafPerjanjian = Long.parseLong(idDrafPerjanjian);
	}
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(String tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public String getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(String tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	
	public String getTarikhHantarFormat(){
		if(getTarikhHantar() != null && !getTarikhHantar().equals("")){
			return  "to_date('" + getTarikhHantar() + "','dd/MM/yyyy')";
		}else
			return null;
	}
	
	
}
