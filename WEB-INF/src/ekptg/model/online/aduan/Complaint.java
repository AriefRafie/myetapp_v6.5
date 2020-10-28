package ekptg.model.online.aduan;

import java.io.Serializable;
import java.util.Vector;

import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujseksyen;
import ekptg.model.online.aduan.entity.OnlineLampiran;

public class Complaint implements Serializable, Comparable<Complaint> {
	private long id;
	private String loginName;
	private String namaPengadu;
	private String emailPengadu;
	private String phonePengadu;
	private String catatan;
	private ComplaintType type;
	private String tarikhAduan;
	private String masaAduan;
	private String status;
	private String remarkPenyelesaian;
	
	private String idResponSeksyen;
	private String idPegawai;
	private String idResponPegawai;
	private String idNegeri;
	private Tblrujnegeri negeri;
	private String ulasanPenerimaan;
	private String noKP;
	private String tarikhKemaskini;
	private String ulasanBalas;
	private String ulasanPegawai;
	private Vector<AduanLampiran> lampiran = new Vector<AduanLampiran>();
	private String catatanSelesai;
	private String flagOnline;
	private Tblrujseksyen seksyen;
	private ComplaintSource source;
	private String statusPenyelesaian;
	private Vector<ComplaintActivity> logs = new Vector<ComplaintActivity>();
	private String idPengadu;
	private Vector<OnlineLampiran> jawapanLampiran = new Vector<OnlineLampiran>();
	private String flagNotifiedPengadu;
	private String noAduan;

	private String tempStatus;
	public String getCatatanSelesai() {
		if(catatanSelesai == null)
			catatanSelesai ="";
		return catatanSelesai;
	}
	public void setCatatanSelesai(String catatanSelesai) {
		this.catatanSelesai = catatanSelesai;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNamaPengadu() {
		return namaPengadu;
	}
	public void setNamaPengadu(String namaPengadu) {
		this.namaPengadu = namaPengadu;
	}
	public String getEmailPengadu() {
		if(emailPengadu == null)
			emailPengadu="";
		return emailPengadu;
	}
	public void setEmailPengadu(String emailPengadu) {
		this.emailPengadu = emailPengadu;
	}
	public String getPhonePengadu() {
		if(phonePengadu == null)
			phonePengadu="";
		return phonePengadu;
	}
	public void setPhonePengadu(String phonePengadu) {
		this.phonePengadu = phonePengadu;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public ComplaintType getType() {
		return type;
	}
	public void setType(ComplaintType type) {
		this.type = type;
	}
	public String getTarikhAduan() {
		return tarikhAduan;
	}
	public void setTarikhAduan(String tarikhAduan) {
		this.tarikhAduan = tarikhAduan;
	}
	public String getMasaAduan() {
		return masaAduan;
	}
	public void setMasaAduan(String masaAduan) {
		this.masaAduan = masaAduan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarkPenyelesaian() {
		return remarkPenyelesaian;
	}
	public void setRemarkPenyelesaian(String remarkPenyelesaian) {
		this.remarkPenyelesaian = remarkPenyelesaian;
	}
	public String getIdResponSeksyen() {
		return idResponSeksyen;
	}
	public void setIdResponSeksyen(String idResponSeksyen) {
		this.idResponSeksyen = idResponSeksyen;
	}
	public String getIdPegawai() {
		return idPegawai;
	}
	public void setIdPegawai(String idPegawai) {
		this.idPegawai = idPegawai;
	}
	public String getUlasanPenerimaan() {
		if(ulasanPenerimaan==null)
			ulasanPenerimaan ="";
		return ulasanPenerimaan;
	}
	public void setUlasanPenerimaan(String ulasanPenerimaan) {
		this.ulasanPenerimaan = ulasanPenerimaan;
	}
	public String getNoKP() {
		return noKP;
	}
	public void setNoKP(String noKP) {
		this.noKP = noKP;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getUlasanBalas() {
		return ulasanBalas;
	}
	public void setUlasanBalas(String ulasanBalas) {
		this.ulasanBalas = ulasanBalas;
	}
	public String getUlasanPegawai() {
		if(ulasanPegawai == null)
			ulasanPegawai="";
		return ulasanPegawai;
	}
	public void setUlasanPegawai(String ulasanPegawai) {
		this.ulasanPegawai = ulasanPegawai;
	}
	public Vector<AduanLampiran> getLampiran() {
		return lampiran;
	}
	public void setLampiran(Vector<AduanLampiran> lampiran) {
		this.lampiran = lampiran;
	}
	public String getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}
	public String getFlagOnline() {
		if(flagOnline == null)
			flagOnline="";
		return flagOnline;
	}
	public void setFlagOnline(String flagOnline) {
		this.flagOnline = flagOnline;
	}
	public Tblrujnegeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Tblrujnegeri negeri) {
		this.negeri = negeri;
	}
	public Tblrujseksyen getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(Tblrujseksyen seksyen) {
		this.seksyen = seksyen;
	}
	public void setSource(ComplaintSource source) {
		this.source = source;
	}
	public ComplaintSource getSource() {
		return source;
	}
	public String getStatusPenyelesaian() {
		if(statusPenyelesaian == null)
			statusPenyelesaian ="";
		return statusPenyelesaian;
	}
	public void setStatusPenyelesaian(String statusPenyelesaian) {
		this.statusPenyelesaian = statusPenyelesaian;
	}
	public void setLogs(Vector<ComplaintActivity> logs) {
		this.logs = logs;
	}
	public Vector<ComplaintActivity> getLogs() {
		return logs;
	}
	public void setIdResponPegawai(String idResponPegawai) {
		this.idResponPegawai = idResponPegawai;
	}
	public String getIdResponPegawai() {
		return idResponPegawai;
	}
	public String getIdPengadu() {
		return idPengadu;
	}
	public void setIdPengadu(String idPengadu) {
		this.idPengadu = idPengadu;
	}
	
	public Vector<OnlineLampiran> getJawapanLampiran() {
		return jawapanLampiran;
	}
	public void setJawapanLampiran(Vector<OnlineLampiran> jawapanLampiran) {
		this.jawapanLampiran = jawapanLampiran;
	}
	
	public String getFlagNotifiedPengadu() {
		return flagNotifiedPengadu;
	}
	public void setFlagNotifiedPengadu(String flagNotifiedPengadu) {
		this.flagNotifiedPengadu = flagNotifiedPengadu;
	}
	
	public String getTempStatus() {
		return tempStatus;
	}
	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}
	@Override
	public int compareTo(Complaint o) {
		
		return 0;
	}
	
	public String getNoAduan() {
		return noAduan;
	}
	public void setNoAduan(String noAduan) {
		this.tempStatus = noAduan;
	}


}
