package ekptg.model.htp.entity;

import java.io.Serializable;

import ekptg.model.htp.HakmilikUrusan;

public class PerjanjianPindahMilik implements Serializable {
	private String idPindahMilik;
	private String tarikhTerima;
	private String tarikhHantar;
	private String tarikhTandatangan;
	private String idPermohonan;
	private String idDokumenPerjanjian;
	public String getIdDokumenPerjanjian() {
		return idDokumenPerjanjian;
	}
	public void setIdDokumenPerjanjian(String idDokumenPerjanjian) {
		this.idDokumenPerjanjian = idDokumenPerjanjian;
	}
	private HakmilikUrusan hakmilikUrusan;
	private UploadPerjanjian perjanjianAttch;
	
	public UploadPerjanjian getPerjanjianAttch() {
		return perjanjianAttch;
	}
	public void setPerjanjianAttch(UploadPerjanjian perjanjianAttch) {
		this.perjanjianAttch = perjanjianAttch;
	}
	public String getIdPindahMilik() {
		return idPindahMilik;
	}
	public void setIdPindahMilik(String idPindahMilik) {
		this.idPindahMilik = idPindahMilik;
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
	public String getTarikhTandatangan() {
		return tarikhTandatangan;
	}
	public void setTarikhTandatangan(String tarikhTandatangan) {
		this.tarikhTandatangan = tarikhTandatangan;
	}
	public String getIdPermohonan() {
		return idPermohonan;
	}
	public void setIdPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	
}
