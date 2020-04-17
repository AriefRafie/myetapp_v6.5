package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_INTERNAL")
public class UsersInternalMobile {

	@Id @Column(name="USER_ID")
	private Long id;
	
//	@OneToOne
//	@JoinColumn(name="USER_ID")
//	private Users userDetail;
	
	@ManyToOne
	@JoinColumn(name="ID_SEKSYEN")
	private RujSeksyenMobile seksyen;
	@ManyToOne
	@JoinColumn(name="ID_JAWATAN")
	private RujJawatanMobile jawatan;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RujSeksyenMobile getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(RujSeksyenMobile seksyen) {
		this.seksyen = seksyen;
	}
	public RujJawatanMobile getJawatan() {
		return jawatan;
	}
	public void setJawatan(RujJawatanMobile jawatan) {
		this.jawatan = jawatan;
	}
}