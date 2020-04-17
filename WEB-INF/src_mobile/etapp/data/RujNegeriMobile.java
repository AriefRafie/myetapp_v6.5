package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJNEGERI")
public class RujNegeriMobile {
	
	@Id	@Column(name="ID_NEGERI")
	private long id;
	@Column(name="NAMA_NEGERI")
	private String namaNegeri;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaNegeri() {
		return namaNegeri;
	}
	public void setNamaNegeri(String namaNegeri) {
		this.namaNegeri = namaNegeri;
	}
}