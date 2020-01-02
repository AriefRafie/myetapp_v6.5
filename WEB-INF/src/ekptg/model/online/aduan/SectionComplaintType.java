package ekptg.model.online.aduan;

import java.io.Serializable;

public class SectionComplaintType implements Serializable {
	private long id;
	private ComplaintType type;
	private Section section;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ComplaintType getType() {
		return type;
	}
	public void setType(ComplaintType type) {
		this.type = type;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	
}
