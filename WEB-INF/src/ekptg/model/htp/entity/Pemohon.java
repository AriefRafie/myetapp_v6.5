package ekptg.model.htp.entity;

import java.io.Serializable;

public class Pemohon implements Serializable {
	private long idPemohon;
	private Permohonan permohonan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String idDaerah;
	private String idNegeri;
	private String tel;
	private String fax;
	private String noPA;
	private String noPemohon;
	private String nama;
	private String flagPemilik;
	private String emel;
	
	public long getIdPemohon() {
		return idPemohon;
	}
	public void setIdPemohon(long idPemohon) {
		this.idPemohon = idPemohon;
	}
	public void setIdPemohon(String idPemohon) {
		if(idPemohon.equals(""))
			idPemohon ="0";
		this.idPemohon =Long.parseLong(idPemohon);
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getAlamat1() {
		return alamat1;
	}
	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}
	public String getAlamat2() {
		return alamat2;
	}
	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	public String getAlamat3() {
		return alamat3;
	}
	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	public String getPoskod() {
		return poskod;
	}
	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}
	public String getIdDaerah() {
		if(idDaerah == null ||idDaerah.equals(""))
			idDaerah="0";
		return idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}
	public String getIdNegeri() {
		if(idNegeri == null ||idNegeri.equals(""))
			idNegeri="0";
		return idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getNoPA() {
		return noPA;
	}
	public void setNoPA(String noPA) {
		this.noPA = noPA;
	}
	public String getNoPemohon() {
		return noPemohon;
	}
	public void setNoPemohon(String noPemohon) {
		this.noPemohon = noPemohon;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getFlagPemilik() {
		return flagPemilik;
	}
	public void setFlagPemilik(String flagPemilik) {
		this.flagPemilik = flagPemilik;
	}
	
	public String getEmel() {
		return emel;
	}
	public void setEmel(String emel) {
		this.emel = emel;
	}	
	
}
