package ekptg.model.online.aduan.setup;

import java.util.Vector;

import ekptg.model.online.aduan.entity.ComplaintCategory;
import ekptg.model.online.aduan.entity.ComplaintTindakan;

public interface IComplaintCategoryBean {
	public Vector<ComplaintCategory> getComplaintCategory();
	public ComplaintCategory getCategory(String idCategory);
	public void saveTindakan(ComplaintTindakan tindakan);
}
