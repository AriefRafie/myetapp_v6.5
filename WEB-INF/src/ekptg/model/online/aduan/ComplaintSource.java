package ekptg.model.online.aduan;

import java.io.Serializable;

public class ComplaintSource implements Serializable {
	private long id;
	private String code;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setId(String id) {
		if(id == null && id.equals("")){
			id = "0";
		}
		this.id = Long.parseLong(id);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
