package ekptg.engine;

import java.io.Serializable;

public class OnlineUser implements Serializable {
	private String id;
	private String noKp;
	private String email;
	private String name;
	private String kategori;
	
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
	
}
