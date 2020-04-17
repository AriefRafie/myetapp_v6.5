package ekptg.model.integrasi.entities;

import java.io.Serializable;

import ekptg.model.htp.entity.HakMilik;

public class MaklumaTanahGIS extends HakMilik implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4983421048467520339L;
	private String latitude;
	private String longitude;
	private int statusTanahGIS;
	private String keteranganStatusGIS;
	private String upi;
	private String idGIS;

	public String getIdGIS() {
		if (this.idGIS == null)idGIS = "0";
		return idGIS;
	}
	public void setIdGIS(String idGIS) {
		this.idGIS = idGIS;
	}
	
	public String getLatitude() {
		if (this.latitude == null)latitude = "";
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		if (this.longitude == null)longitude = "";
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Integer getStatusGIS() {
		return statusTanahGIS;
	}
	public void setStatusGIS(int statusTanahGIS) {
		this.statusTanahGIS = statusTanahGIS;
	}	
	
	public String getStatusTanahGIS() {
		return keteranganStatusGIS;
	}
	public void setStatusTanahGIS(String keteranganStatusGIS) {
		this.keteranganStatusGIS = keteranganStatusGIS;
	}	
	
	public String getUPI() {
		return upi;
	}
	public void setUPI(String upi) {
		this.upi = upi;
	}	
	
	
	
}
