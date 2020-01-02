/* ************************************************************************
LEBAH PORTAL FRAMEWORK, http://lebah.sf.net
Copyright (C) 2007  Shamsul Bahrin

This program is free software; you can redistribute it and/or




This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

* ************************************************************************ */
package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UsersMobile {
	
	@Id @Column(name="USER_ID")
	private Long id;
	@Column(name="USER_LOGIN")
	private String uLogin;
	@Column(name="USER_NAME")
	private String uName;
	@Column(name="USER_ROLE")
	private String uRole;
	@Column(name="USER_TYPE")
	private String uType;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getULogin() {
		return uLogin;
	}
	public void setULogin(String login) {
		uLogin = login;
	}
	public String getUName() {
		return uName;
	}
	public void setUName(String name) {
		uName = name;
	}
	public String getURole() {
		return uRole;
	}
	public void setURole(String role) {
		uRole = role;
	}
	public String getUType() {
		return uType;
	}
	public void setUType(String type) {
		uType = type;
	}
}