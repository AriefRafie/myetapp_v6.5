package ekptg.engine;

import java.io.Serializable;

public class OnlineUser implements Serializable {
	private String id;
	private String noKp;
	private String email;
	private String name;
	private String kategori;
	private String jawatan;
	private String seksyen;
	private String pejabat;
	private String negeri;
	private String daerah;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoKp() {
		return noKp;
	}
	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getJawatan() {
		return jawatan;
	}
	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}
	public String getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}
	public String getPejabat() {
		return pejabat;
	}
	public void setPejabat(String pejabat) {
		this.pejabat = pejabat;
	}
	public String getNegeri() {
		return negeri;
	}
	public void setNegeri(String negeri) {
		this.negeri = negeri;
	}
	public String getDaerah() {
		return daerah;
	}
	public void setDaerah(String daerah) {
		this.daerah = daerah;
	}

}
