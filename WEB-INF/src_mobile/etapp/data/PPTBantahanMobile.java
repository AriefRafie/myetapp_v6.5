package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTBANTAHAN")
public class PPTBantahanMobile {

	@Id @Column(name="ID_BANTAHAN")
	private long id;
	@Column(name="NO_BANTAHAN")
	private String noBantahan;

	@ManyToOne
	@JoinColumn(name="ID_HAKMILIK")
	private PPTHakmilikMobile hakmilik;
	@ManyToOne
	@JoinColumn(name="STATUS_BANTAHAN")
	private RujStatusMobile status;

	@Column(name="FLAG_ONLINE")
	private long flagOnline;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTHakmilikMobile getHakmilik() {
		return hakmilik;
	}
	public void setHakmilik(PPTHakmilikMobile hakmilik) {
		this.hakmilik = hakmilik;
	}
	public String getNoBantahan() {
		return noBantahan;
	}
	public void setNoBantahan(String noBantahan) {
		this.noBantahan = noBantahan;
	}
	public RujStatusMobile getStatus() {
		return status;
	}
	public void setStatus(RujStatusMobile status) {
		this.status = status;
	}
	public long getFlagOnline() {
		return flagOnline;
	}
	public void setFlagOnline(long flagOnline) {
		this.flagOnline = flagOnline;
	}
}