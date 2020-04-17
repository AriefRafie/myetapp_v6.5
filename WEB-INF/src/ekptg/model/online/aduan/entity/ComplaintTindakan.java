package ekptg.model.online.aduan.entity;

import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujseksyen;

public class ComplaintTindakan {
	private long id;
	private Tblrujseksyen seksyen;
	private Tblrujnegeri negeri;
	private ComplaintCategory category;
	private String kod;
	private String name;
	private String groupEmail;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Tblrujseksyen getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(Tblrujseksyen seksyen) {
		this.seksyen = seksyen;
	}
	public Tblrujnegeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Tblrujnegeri negeri) {
		this.negeri = negeri;
	}
	public ComplaintCategory getCategory() {
		return category;
	}
	public void setCategory(ComplaintCategory category) {
		this.category = category;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public String getKod() {
		return kod;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}
	public String getGroupEmail() {
		return groupEmail;
	}
}
