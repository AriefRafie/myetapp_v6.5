package etapp.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLPPTBORANGH")
public class PPTBorangHMobile {
	
	@Id @Column(name="ID_BORANGH")
	private long id;
	@Column(name="JENIS_BORANGH")
	private long jenisBorangH;
	@Column(name="KEPUTUSAN")
	private long keputusan;
	@Column(name="TARIKH_BORANGH")
	@Temporal(TemporalType.DATE)
	private Date tarikhBorangH;
	
	@ManyToOne
	@JoinColumn(name="ID_HAKMILIKPB")
	private PPTHakmilikPBMobile hakmilikPB;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getJenisBorangH() {
		return jenisBorangH;
	}
	public void setJenisBorangH(long jenisBorangH) {
		this.jenisBorangH = jenisBorangH;
	}
	public long getKeputusan() {
		return keputusan;
	}
	public void setKeputusan(long keputusan) {
		this.keputusan = keputusan;
	}
	public Date getTarikhBorangH() {
		return tarikhBorangH;
	}
	public void setTarikhBorangH(Date tarikhBorangH) {
		this.tarikhBorangH = tarikhBorangH;
	}
	public PPTHakmilikPBMobile getHakmilikPB() {
		return hakmilikPB;
	}
	public void setHakmilikPB(PPTHakmilikPBMobile hakmilikPB) {
		this.hakmilikPB = hakmilikPB;
	}
}