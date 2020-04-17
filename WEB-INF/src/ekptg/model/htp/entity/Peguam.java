package ekptg.model.htp.entity;

import java.io.Serializable;

import ekptg.model.entities.Daerah;
import ekptg.model.entities.Negeri;

public class Peguam implements Serializable {
	private long idpeguam;
	private Permohonan permohonan;
	private String nama;
	private String noRujukan;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private long idNegeri;
	private long idDaerah;
	private String noTel;
	private String noFax;
	private Negeri negeri;
	private Daerah daerah;
	
	public long getIdpeguam() {
		return idpeguam;
	}
	public void setIdpeguam(long idpeguam) {
		this.idpeguam = idpeguam;
	}
	public void setIdpeguam(String idpeguam) {
		if(idpeguam.equals("")){
			idpeguam = "0";
		}
		this.idpeguam = Long.parseLong(idpeguam);
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
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
	public long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		if(idNegeri == null && idNegeri.equals(""))
			idNegeri ="0";
		this.idNegeri = Long.parseLong(idNegeri);
	}
	public long getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(long idDaerah) {
		this.idDaerah = idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		if(idDaerah == null && !idDaerah.equals(""))
			idDaerah ="0";
		this.idDaerah = Long.parseLong(idDaerah);
	}
	public String getNoTel() {
		return noTel;
	}
	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}
	public String getNoFax() {
		return noFax;
	}
	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}
	
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	
}
