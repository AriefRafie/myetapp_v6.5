package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_KEMENTERIAN")
public class UsersKementerianMobile {

	@Id @Column(name="ID_USERSKEMENTERIAN")
	private Long id;

	@ManyToOne
	@JoinColumn(name="ID_AGENSI")
	private RujAgensiMobile agensi;
	@ManyToOne
	@JoinColumn(name="ID_KEMENTERIAN")
	private RujKementerianMobile kementerian;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UsersMobile users;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RujAgensiMobile getAgensi() {
		return agensi;
	}
	public void setAgensi(RujAgensiMobile agensi) {
		this.agensi = agensi;
	}
	public RujKementerianMobile getKementerian() {
		return kementerian;
	}
	public void setKementerian(RujKementerianMobile kementerian) {
		this.kementerian = kementerian;
	}
	public UsersMobile getUsers() {
		return users;
	}
	public void setUsers(UsersMobile users) {
		this.users = users;
	}
}
