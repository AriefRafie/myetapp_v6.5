package ekptg.model.htp.entity;

import java.io.Serializable;

public class UploadPerjanjian implements Serializable{
	
	private String NamaFail;
	private long idDokumenPerjanjian;
	private long idDokumenPerjanjianAttach;
	private Permohonan permohonan;
	private String tarikhTerima;
	private String tarikhHantar;
	private String tarikhTandatangan;
	private String mIMEType;
	
	public long getIdDokumenPerjanjianAttach() {
		return idDokumenPerjanjianAttach;
	}
	public void setIdDokumenPerjanjianAttach(long idDokumenPerjanjianAttach) {
		this.idDokumenPerjanjianAttach = idDokumenPerjanjianAttach;
	}
	
	public String getMIMEType() {
		return mIMEType;
	}
	public void setMIMEType(String type) {
		mIMEType = type;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	private byte[] content;
	
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
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getNamaFail() {
		return NamaFail;
	}
	public void setNamaFail(String namaFail) {
		NamaFail = namaFail;
	}
	public long getIdDokumenPerjanjian() {
		return idDokumenPerjanjian;
	}
	public void setIdDokumenPerjanjian(long idDokumenPerjanjian) {
		this.idDokumenPerjanjian = idDokumenPerjanjian;
	}
	public void setIdDokumenPerjanjian(String idDokumenPerjanjian) {
		if(idDokumenPerjanjian.equals(""))
			idDokumenPerjanjian = "0";
		this.idDokumenPerjanjian = Long.parseLong(idDokumenPerjanjian);
	}

}
