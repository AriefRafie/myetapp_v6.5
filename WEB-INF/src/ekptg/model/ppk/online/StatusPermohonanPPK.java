package ekptg.model.ppk.online;

import java.io.Serializable;

public class StatusPermohonanPPK implements Serializable{
	private String status;
	private String tarikhMohon;
	private String noFail;
	private String seksyen;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTarikhMohon() {
		return tarikhMohon;
	}
	public void setTarikhMohon(String tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public String getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}
	
}
