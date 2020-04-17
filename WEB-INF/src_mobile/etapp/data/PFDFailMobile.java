package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPFDFAIL")
public class PFDFailMobile {

	@Id @Column(name="ID_FAIL")
	private long id;
	@Column(name="NO_FAIL")
	private String noFail;
	
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
	private RujStatusMobile status;
	@ManyToOne
	@JoinColumn(name="ID_SUBURUSAN")
	private RujSubUrusanMobile subUrusan;
	@ManyToOne
	@JoinColumn(name="ID_KEMENTERIAN")
	private RujKementerianMobile kementerian;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public RujStatusMobile getStatus() {
		return status;
	}
	public void setStatus(RujStatusMobile status) {
		this.status = status;
	}
	public RujSubUrusanMobile getSubUrusan() {
		return subUrusan;
	}
	public void setSubUrusan(RujSubUrusanMobile subUrusan) {
		this.subUrusan = subUrusan;
	}
	public RujKementerianMobile getKementerian() {
		return kementerian;
	}
	public void setKementerian(RujKementerianMobile kementerian) {
		this.kementerian = kementerian;
	}
}