package ekptg.model.online.aduan.entity;

import java.io.Serializable;

import ekptg.model.online.aduan.Complaint;
import ekptg.model.online.aduan.ResponseStatus;

public class ComplaintResponse implements Serializable {
	private long id;
	private String jawapan;
	private Complaint complaint;
	private String idMasuk;
	private String idKemaskini;
	private String tarikhMasuk;
	private String tarikhKemaskini;
	private ResponseStatus responseStatus;
	private String arahan;
	private String status;
	private ComplaintTindakan tindakan;
	public String getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(String idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(String idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJawapan() {
		return jawapan;
	}
	public void setJawapan(String jawapan) {
		this.jawapan = jawapan;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public void setArahan(String arahan) {
		this.arahan = arahan;
	}
	public String getArahan() {
		return arahan;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setTindakan(ComplaintTindakan tindakan) {
		this.tindakan = tindakan;
	}
	public ComplaintTindakan getTindakan() {
		return tindakan;
	}
	
	
}
