package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJDAERAH")
public class RujDaerahMobile {

	@Id @Column(name="ID_DAERAH")
	private long id;
	@Column(name="NAMA_DAERAH")
	private String namaDaerah;
	
	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaDaerah() {
		return namaDaerah;
	}
	public void setNamaDaerah(String namaDaerah) {
		this.namaDaerah = namaDaerah;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
}
