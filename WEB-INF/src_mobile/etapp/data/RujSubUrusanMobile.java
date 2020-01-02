package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJSUBURUSAN")
public class RujSubUrusanMobile {

	@Id @Column(name="ID_SUBURUSAN")
	private long id;
	@Column(name="KOD_SUBURUSAN")
	private String kodSuburusan;
	@Column(name="NAMA_SUBURUSAN")
	private String namaSuburusan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKodSuburusan() {
		return kodSuburusan;
	}
	public void setKodSuburusan(String kodSuburusan) {
		this.kodSuburusan = kodSuburusan;
	}
	public String getNamaSuburusan() {
		return namaSuburusan;
	}
	public void setNamaSuburusan(String namaSuburusan) {
		this.namaSuburusan = namaSuburusan;
	}
}