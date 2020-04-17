package lebah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")
public class Role {

	@Id
	@Column(name="NAME")
	public String name;
	@Column(name="DESCRIPTION")
	public String description;
	@Column(name="THEME")
	public String theme;
	
	public Role() {
		
	}
	
	public String getId() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
}
