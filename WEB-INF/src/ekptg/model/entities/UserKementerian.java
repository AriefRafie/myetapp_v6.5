package ekptg.model.entities;

import java.io.Serializable;

import lebah.portal.element.User;

public class UserKementerian extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7063891775860411010L;
	private long idUser;
	private Agensi agensi;
	
	public long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	
	public Agensi getAgensi() {
		return agensi;
	}
	

}
