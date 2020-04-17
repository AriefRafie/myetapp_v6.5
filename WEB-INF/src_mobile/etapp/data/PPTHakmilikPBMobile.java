package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPPTHAKMILIKPB")
public class PPTHakmilikPBMobile {
	
	@Id @Column(name="ID_HAKMILIKPB")
	private long id;
	@Column(name="NO_AKAUN")
	private String noAkaun;
	@Column(name="NO_HANDPHONE")
	private String noHandphone;
	
	@ManyToOne
	@JoinColumn(name="ID_HAKMILIK")
	private PPTHakmilikMobile hakmilik;
	@ManyToOne
	@JoinColumn(name="ID_PIHAKBERKEPENTINGAN")
	private PPTPihakBerkepentinganMobile pihakPenting;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNoAkaun() {
		return noAkaun;
	}
	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}
	public String getNoHandphone() {
		return noHandphone;
	}
	public void setNoHandphone(String noHandphone) {
		this.noHandphone = noHandphone;
	}
	public PPTHakmilikMobile getHakmilik() {
		return hakmilik;
	}
	public void setHakmilik(PPTHakmilikMobile hakmilik) {
		this.hakmilik = hakmilik;
	}
	public PPTPihakBerkepentinganMobile getPihakPenting() {
		return pihakPenting;
	}
	public void setPihakPenting(PPTPihakBerkepentinganMobile pihakPenting) {
		this.pihakPenting = pihakPenting;
	}
}