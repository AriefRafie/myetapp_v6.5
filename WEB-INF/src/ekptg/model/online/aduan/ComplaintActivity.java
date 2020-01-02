package ekptg.model.online.aduan;

import java.io.Serializable;

public class ComplaintActivity implements Serializable {
	private long id;
	private String tarikhMasuk;
	private String idMasuk;
	private String userName;
	private String aktiviti;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAktiviti() {
		return aktiviti;
	}
	public void setAktiviti(String aktiviti) {
		this.aktiviti = aktiviti;
	}
	
}
