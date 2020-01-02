package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_ONLINE")
public class UsersDataMobile {

	@Id @Column(name="USER_ID")
	private Long id;
	
//	@OneToOne 
//	@JoinColumn(name="USER_ID")
//	private Users userData;
	
	@Column(name="EMEL")
	private String uEmel;
	@Column(name="NO_HP")
	private String uNoHP;
	@Column(name="NO_KP_BARU")
	private String uKPBaru;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUEmel() {
		return uEmel;
	}
	public void setUEmel(String emel) {
		uEmel = emel;
	}
	public String getUNoHP() {
		return uNoHP;
	}
	public void setUNoHP(String noHP) {
		uNoHP = noHP;
	}
	public String getUKPBaru() {
		return uKPBaru;
	}
	public void setUKPBaru(String baru) {
		uKPBaru = baru;
	}
}