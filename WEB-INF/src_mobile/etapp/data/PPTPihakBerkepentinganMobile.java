package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTPIHAKBERKEPENTINGAN")
public class PPTPihakBerkepentinganMobile {
	
	@Id @Column(name="ID_PIHAKBERKEPENTINGAN")
	private long id;
	@Column(name="NO_PB")
	private String noPB;
	@Column(name="NAMA_PB")
	private String namaPB;
	@Column(name="NAMA_KP")
	private String namaKP;
	@Column(name="NO_HP")
	private String noHP;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoPB() {
		return noPB;
	}
	public void setNoPB(String noPB) {
		this.noPB = noPB;
	}
	public String getNamaPB() {
		return namaPB;
	}
	public void setNamaPB(String namaPB) {
		this.namaPB = namaPB;
	}
	public String getNamaKP() {
		return namaKP;
	}
	public void setNamaKP(String namaKP) {
		this.namaKP = namaKP;
	}
	public String getNoHP() {
		return noHP;
	}
	public void setNoHP(String noHP) {
		this.noHP = noHP;
	}
}