package ekptg.model.htp.entity;

import java.io.Serializable;

import ekptg.intergration.entity.BorangK;
import ekptg.model.htp.HakmilikUrusan;

public class HtpBorangK extends BorangK implements Serializable{
	private String idInfoBorangK;
	private HakmilikUrusan hakmilik;

	public String getIdInfoBorangK() {
		return idInfoBorangK;
	}

	public void setIdInfoBorangK(String idInfoBorangK) {
		this.idInfoBorangK = idInfoBorangK;
	}

	public HakmilikUrusan getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(HakmilikUrusan hakmilik) {
		this.hakmilik = hakmilik;
	}
	
	
}
