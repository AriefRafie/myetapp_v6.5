package lebah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PAGE_CSS")
public class PageCss {
	
	@Id
	@Column(name="CSS_NAME")
	private String name;
	@Column(name="CSS_TITLE")
	private String title;
	
	public PageCss() {
		
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
