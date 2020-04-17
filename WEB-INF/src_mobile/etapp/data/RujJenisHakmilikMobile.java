package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJJENISHAKMILIK")
public class RujJenisHakmilikMobile {
	
	@Id @Column(name="ID_JENISHAKMILIK")
	private long id;
	@Column(name="KOD_JENIS_HAKMILIK")
	private String kodHakmilik;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodHakmilik() {
		return kodHakmilik;
	}
	public void setKodHakmilik(String kodHakmilik) {
		this.kodHakmilik = kodHakmilik;
	}
}
