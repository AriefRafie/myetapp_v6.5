package lebah.portal.db;

public class Role {
	String name = "";
	String description = "";
	String kod = "";
	String layer = "";
	String details = "";

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}
	
	public String getLayer() {
		return this.layer;
	}

	public void setLayer(String layer) {
		this.layer = layer;
	}
	
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}