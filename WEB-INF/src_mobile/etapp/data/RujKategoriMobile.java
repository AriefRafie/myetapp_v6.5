package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJKATEGORI")
public class RujKategoriMobile {

	@Id @Column(name="ID_KATEGORI")
	private long id;
	@Column(name="KETERANGAN")
	private String keterangan;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}	
}
