package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJKEMENTERIAN")
public class RujKementerianMobile {

	@Id @Column(name="ID_KEMENTERIAN")
	private long id;
	@Column(name="NAMA_KEMENTERIAN")
	private String namaKementerian;
	
	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNamaKementerian() {
		return namaKementerian;
	}
	public void setNamaKementerian(String namaKementerian) {
		this.namaKementerian = namaKementerian;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
}