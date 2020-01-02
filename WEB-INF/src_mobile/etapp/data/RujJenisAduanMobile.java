package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJJENISADUAN")
public class RujJenisAduanMobile {

	@Id @Column(name="ID_JENISADUAN")
	private Long id;
	@Column(name="KOD_JENIS_ADUAN")
	private String kodJenisAduan;
	@Column(name="JENIS_ADUAN")
	private String jenisAduan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKodJenisAduan() {
		return kodJenisAduan;
	}
	public void setKodJenisAduan(String kodJenisAduan) {
		this.kodJenisAduan = kodJenisAduan;
	}
	public String getJenisAduan() {
		return jenisAduan;
	}
	public void setJenisAduan(String jenisAduan) {
		this.jenisAduan = jenisAduan;
	}
}
