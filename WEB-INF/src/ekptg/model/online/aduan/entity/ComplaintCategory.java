package ekptg.model.online.aduan.entity;

import java.io.Serializable;
import java.util.Vector;

public class ComplaintCategory implements Serializable {
	private long id;
	private String kod;
	private String name;
	private Vector<ComplaintTindakan> actions = new Vector<ComplaintTindakan>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setActions(Vector<ComplaintTindakan> actions) {
		this.actions = actions;
	}
	public Vector<ComplaintTindakan> getActions() {
		return actions;
	}
	
	
}
