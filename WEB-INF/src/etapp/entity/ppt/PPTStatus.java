package etapp.entity.ppt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lebah.template.UID;

@Entity
@Table(name="TBLPPTSTATUS")
public class PPTStatus {
	
	@Id
	@Column(name="ID_STATUS")
	private long id;
	@Column(name="KOD")
	private String kod;
	@Column(name="NAMA")
	private String nama;
	@Column(name="KATEGORI")
	private String kategori;
	
	public PPTStatus() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String name) {
		this.nama = name;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	
	

}
