package lebah.portal.element;

import lebah.util.Logger;

public class Role {
	private String name;
	private String description;
	private String theme;
	private String kod;
	private String layer;
	private Logger log;
	private String className = "mecca.object.Role";
	private boolean logger = false;

	public Role() {
		this.name = "";
		this.description = "";
		this.theme = "";
		this.kod = "";
		this.layer = "";
		if (!(this.logger))
			return;
		this.log = new Logger(this.className);
	}

	public void setName(String name) {
		if (name == null) {
			this.name = "";
		} else
			this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = "";
		} else
			this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setTheme(String theme) {
		if (theme == null) {
			this.theme = "";
		} else
			this.theme = theme;
	}

	public String getTheme() {
		return this.theme;
	}
	
	public void setKod(String kod) {
		if (kod == null) {
			this.kod = "";
		} else
			this.kod = kod;
	}

	public String getKod() {
		return this.kod;
	}
	
	public void setLayer(String layer) {
		if (layer == null) {
			this.layer = "";
		} else
			this.layer = layer;
	}

	public String getLayer() {
		return this.layer;
	}
}