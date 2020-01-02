package lebah.admin;

import java.util.HashMap;
import java.util.Map;

import lebah.entity.Role;
import lebah.template.RecordTemplateModule2;

public class RoleManagerModule extends RecordTemplateModule2<Role> {

	@Override
	public String getPath() {
		return "app/admin/role";
	}

	@Override
	public void begin() {
		this.setOrderBy("name");
		this.setOrderType("ASC");
		context.put("css", db.list("select c from PageCss c order by c.name"));
	}

	@Override
	public boolean delete(Role arg0) throws Exception {
		return false;
	}

	@Override
	public Class<Role> getPersistenceClass() {
		return Role.class;
	}

	@Override
	public void save(Role role) throws Exception {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String theme = request.getParameter("theme");
		
		role.setName(name);
		role.setDescription(description);
		role.setTheme(theme);
		
	}

	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		String name = request.getParameter("find_name");
		String theme = request.getParameter("find_theme");
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", name);
		m.put("theme", theme);
		return m;
	}

	@Override
	public void getRelatedData(Role r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterSave(Role r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeSave() {
		// TODO Auto-generated method stub
		
	}
	
}
