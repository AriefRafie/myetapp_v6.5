/*
 * entity PihakBerkepentingan to cater pajakan kecil
 * 
 * */


package ekptg.model.htp;

import java.io.Serializable;

import ekptg.model.entities.Daerah;
import ekptg.model.entities.Negeri;

public class PihakBerkepentingan implements Serializable {
	private String idpihakberkepentingan;
	private String nama;
	private String noRujukan;
	private String idHakmilikurusanPB;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String idDaerah;
	private String idNegeri;
	private String tel;
	private String fax;
	private String ddownHakmilik;
	private String jenisPB;
	private String idHakmilikUrusan;
	private String noHakmilik;
	private Negeri negeri;
	private Daerah daerah;
	private HakmilikUrusan hakmilikUrusan;
	
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public String getIdHakmilikUrusan() {
		return idHakmilikUrusan;
	}
	public void setIdHakmilikUrusan(String idHakmilikUrusan) {
		this.idHakmilikUrusan = idHakmilikUrusan;
	}
	public String getJenisPB() {
		return jenisPB;
	}

	public void setJenisPB(String jenisPB) {
		if(jenisPB.equals(""))
			jenisPB="7";
		this.jenisPB = jenisPB;
	}
	public String getDdownHakmilik() {
		
		return ddownHakmilik;
	}


	public void setDdownHakmilik(String ddownHakmilik) {
		this.ddownHakmilik = ddownHakmilik;
	}
	public String getIdHakmilikurusanPB() {
		return idHakmilikurusanPB;
	}
	public void setIdHakmilikurusanPB(String idHakmilikurusanPB) {
		this.idHakmilikurusanPB = idHakmilikurusanPB;
	}
	
	public String getIdpihakberkepentingan() {
		return idpihakberkepentingan;
	}
	public void setIdpihakberkepentingan(String idpihakberkepentingan) {
		this.idpihakberkepentingan = idpihakberkepentingan;
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
	public String getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}
	public String getIdNegeri() {
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
	
	public HakmilikUrusan getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
		this.hakmilikUrusan = hakmilikUrusan;
	}
	
}
