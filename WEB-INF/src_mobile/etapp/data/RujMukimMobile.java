package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJMUKIM")
public class RujMukimMobile {

	@Id @Column(name="ID_MUKIM")
	private long id;
	@Column(name="NAMA_MUKIM")
	private String namaMukim;
	
	@ManyToOne
	@JoinColumn(name="ID_DAERAH")
	private RujDaerahMobile daerah;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaMukim() {
		return namaMukim;
	}
	public void setNamaMukim(String namaMukim) {
		this.namaMukim = namaMukim;
	}
	public RujDaerahMobile getDaerah() {
		return daerah;
	}
	public void setDaerah(RujDaerahMobile daerah) {
		this.daerah = daerah;
	}
}
