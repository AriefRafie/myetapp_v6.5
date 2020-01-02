package ekptg.model.online.aduan.entity;

import java.io.Serializable;

import ekptg.model.online.aduan.AduanLampiran;

public class OnlineLampiran extends AduanLampiran implements Serializable{
	private String referenceTable;
	private String idRekod;
	public String getReferenceTable() {
		return referenceTable;
	}
	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}
	public String getIdRekod() {
		return idRekod;
	}
	public void setIdRekod(String idRekod) {
		this.idRekod = idRekod;
	}
	
	
}
