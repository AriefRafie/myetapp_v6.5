package ekptg.model.entities;

import java.io.Serializable;

public class InternalUser implements Serializable {
	private String userId;
	private String idSeksyen;
	private String idNegeri;
	private String idDaerah;
	private String idPejabat;
	private String idJawatan;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIdSeksyen() {
		return idSeksyen;
	}
	public void setIdSeksyen(String idSeksyen) {
		this.idSeksyen = idSeksyen;
	}
	public String getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}
	public String getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}
	public String getIdPejabat() {
		return idPejabat;
	}
	public void setIdPejabat(String idPejabat) {
		this.idPejabat = idPejabat;
	}
	public String getIdJawatan() {
		return idJawatan;
	}
	public void setIdJawatan(String idJawatan) {
		this.idJawatan = idJawatan;
	}
	
}
