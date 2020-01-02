package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTAWARD")
public class PPTAwardMobile {
	
	@Id @Column(name="ID_AWARD")
	private long id;
	@Column(name="JENIS_AWARD")
	private String jenisAward;
	
	@ManyToOne
	@JoinColumn(name="ID_HAKMILIKPB")
	private PPTHakmilikPBMobile hakmilikPB;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJenisAward() {
		return jenisAward;
	}
	public void setJenisAward(String jenisAward) {
		this.jenisAward = jenisAward;
	}
	public PPTHakmilikPBMobile getHakmilikPB() {
		return hakmilikPB;
	}
	public void setHakmilikPB(PPTHakmilikPBMobile hakmilikPB) {
		this.hakmilikPB = hakmilikPB;
	}
}