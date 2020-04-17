package ekptg.view.online.aduan.setup;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujseksyen;
import ekptg.model.online.aduan.entity.ComplaintCategory;
import ekptg.model.online.aduan.entity.ComplaintTindakan;
import ekptg.model.online.aduan.setup.ComplaintCategoryBean;
import ekptg.model.online.aduan.setup.IComplaintCategoryBean;

public class ComplaintCategoryModule extends AjaxModule {
	private final String PATH="app/online/aduan/setup/";
	private String vm = PATH +"index.jsp";
	String userId = null;
	private IComplaintCategoryBean b;
	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		getHandler();
		if(command.equals("viewCategory")){
			String idCategory = getParam("category");
			context.put("category",b.getCategory(idCategory));
			vm = PATH +"view.jsp";
		}
		else if(command.equals("saveCategory")){
			String idCategory = getParam("category");
			String idNegeri = getParam("negeri");
			String idSeksyen = getParam("seksyen");
			String description = getParam("description");
			String email = getParam("email");
			ComplaintTindakan tindakan = new ComplaintTindakan();
			ComplaintCategory category = new ComplaintCategory();
			category.setId(Long.parseLong(idCategory));
			Tblrujnegeri negeri = new Tblrujnegeri();
			negeri.setIdNegeri(Long.parseLong(idNegeri));
			Tblrujseksyen seksyen = new Tblrujseksyen();
			seksyen.setIdSeksyen(Long.parseLong(idSeksyen));
			
			tindakan.setCategory(category);
			tindakan.setNegeri(negeri);
			tindakan.setSeksyen(seksyen);
			tindakan.setName(description);
			tindakan.setGroupEmail(email);
			
			b.saveTindakan(tindakan);
			
			context.put("category",b.getCategory(idCategory));
			vm = PATH +"view.jsp";
		}
		else{
			 vm = PATH +"index.jsp";
		}
		getCategories();
		return vm;
	}
	private void getCategories()throws Exception{
		context.put("sections",DB.getSeksyen());
		context.put("states",DB.getNegeri());
		context.put("categories",b.getComplaintCategory());
	}
	private IComplaintCategoryBean getHandler(){
		if(b == null)
			b = new ComplaintCategoryBean();
		return b;
	}
}
