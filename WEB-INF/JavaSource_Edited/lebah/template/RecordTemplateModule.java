package lebah.template;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lebah.portal.action.LebahModule;

public abstract class RecordTemplateModule<T> extends LebahModule {
	
	private Class<T> objectClass;
	private Class idType;
	protected DbPersistence db = new DbPersistence();
	protected String templateDir = "RecordTemplate";
	protected String userId = "";
	protected int rowSize = 17;
	
	protected boolean isNew = false;
	
	protected String where;
	protected Hashtable params = new Hashtable();
	protected String selectStmt;
	protected String selectCountStmt;
	protected String tableHeaderPage;
	protected String dataRowPage;
	protected String dataEntryPage;
	protected String searchEntryPage;
	protected String orderBy;
	protected String sequence;
	
	protected List<String> filterList = new ArrayList<String>();
	
	public abstract Class<T> getPersistenceClass();
	public abstract int getRowSize();
	public abstract String getTableHeaderPage();
	public abstract String getDataRowPage();
	public abstract String getDataEntryPage();
	public abstract String getSearchEntryPage();
	public abstract String getPath();

	public abstract void begin();
	public abstract void save(T r) throws Exception;
	public abstract boolean delete(T r) throws Exception;
	public abstract Map<String, Object> searchCriteria() throws Exception;
	
	public abstract void beforeSave();
	public abstract void afterSave(T r);

	
	public abstract void getRelatedData(T r);
	public Class getIdType(){ return Object.class; }
	
	public void clearFilter() {
		filterList.clear();
	}
	
	public void addFilter(String s) {
		this.filterList.add(s);
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public void setOrderType(String sequence) {
		this.sequence = sequence;
	}
	
	public void setSelectStatement(String selectStmt) {
		this.selectStmt = selectStmt;
	}
	
	public void setSelectCountStatement(String selectCountStmt) {
		this.selectCountStmt = selectStmt;
	}
	
	public String get(String paramName) {
		return request.getParameter(paramName) != null ? request.getParameter(paramName) : "";
	}
	
	public Date getDate(String paramName) {
		String dateTxt = get(paramName);
		if ( dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd-MM-yyyy").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	
	public void preProcess() {
		
		context.remove("error_flag");
		context.remove("success_flag");
		
		filterList.clear();
		begin();
		
		objectClass = getPersistenceClass();
		idType = getIdType();
		rowSize = getRowSize();
		tableHeaderPage = getTableHeaderPage();
		dataRowPage = getDataRowPage();
		dataEntryPage = getDataEntryPage();
		searchEntryPage = getSearchEntryPage();
		
	
		context.put("templateDir", templateDir);
		context.put("viewPath", getPath());
		context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
		userId = (String) request.getSession().getAttribute("_portal_login");
		context.put("row_data_page", dataRowPage);
		context.put("row_title_page", tableHeaderPage);
		context.put("data_entry_page", dataEntryPage);
		context.put("search_entry_page", searchEntryPage);
	}	
	
	public String doCommand() throws Exception {
		context.put("update_counter", false);
		if ( "list".equals(command)) return list();
		else if ( "get_page".equals(command)) return getPage();
		else if ( "get_next".equals(command)) return getNextPage();
		else if ( "get_previous".equals(command)) return getPreviousPage();
		else if ( "get_first_page".equals(command)) return getFirstPage();
		else if ( "get_last_page".equals(command)) return getLastPage();
		else if ( "edit".equals(command)) return edit();
		else if ( "delete".equals(command)) return delete();
		else if ( "save_data".equals(command)) return saveData();
		else if ( "save_add_new_record".equals(command)) return saveDataAndAddNew();
		else if ( "add_new_record".equals(command)) return addNewRecord();
		else if ( "list_find".equals(command)) return listFind();
		else if ( "list_page".equals(command)) return listPage();
		return "";
	}


	public String listPage() throws Exception{
		
		where = request.getSession().getAttribute("_where_stmt") != null ? 
				(String) request.getSession().getAttribute("_where_stmt") : null;
		params = request.getSession().getAttribute("_where_params") != null ? 
				(Hashtable) request.getSession().getAttribute("_where_params") : null;		
		
		doSelectCount(where, params);
		
		String pageNum = request.getParameter("page_num");
		context.put("current_page_num", pageNum);
		int num = pageNum != null && !"".equals(pageNum) ? Integer.parseInt(pageNum) : 1;
		list(num);
		//return templateDir + "/list.vm";
		return templateDir + "/entry.vm";
	}
	
	@Override
	public String start() {
		try {
			listAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return templateDir + "/start.vm";
	}
	
	private void list(int pageNum) throws Exception {
		int rowStart = pageNum > 1 ? (pageNum - 1) * rowSize : 0;
		context.put("row_start", rowStart);
		context.put("page_num", pageNum);
		
		where = request.getSession().getAttribute("_where_stmt") != null ? 
				(String) request.getSession().getAttribute("_where_stmt") : null;
		params = request.getSession().getAttribute("_where_params") != null ? 
				(Hashtable) request.getSession().getAttribute("_where_params") : null;
				
				
				
		//if ( selectStmt == null ) {
			selectStmt = "select x from " + objectClass.getSimpleName() + " x ";
			if ( filterList.size() > 0 ) {
				int i = 0;
				for ( String s : filterList ) {
					if ( i == 0 ) {
						selectStmt += " where x." + s;
					} else {
						selectStmt += " and x." + s;
					}
					i++;
				}
				if ( where != null && !"".equals(where) ) selectStmt += " and " + where + " ";
			} else {
				if ( where != null && !"".equals(where) ) selectStmt += " where " + where + " ";
			}
			if ( orderBy != null && !"".equals(orderBy) ) selectStmt += " order by x." + orderBy + " " + sequence;
		//}
		
		System.out.println(selectStmt);
		System.out.println("params=" + params);
		
		if ( where == null ) params = null;
		
		List<T> records = null;
		if ( params != null ) {
			records = db.list(selectStmt, rowStart, rowSize, params);
		}
		else {
			records = db.list(selectStmt, rowStart, rowSize);
		}
		//else List<T> records = db.list(selectStmt, rowStart, rowSize);

		context.put("records", records);


	}
	
	public String list() throws Exception {
		listAll();
		return templateDir + "/list.vm";
	}
	private void listAll() throws Exception {
		where = null;
		params = null;
		request.getSession().removeAttribute("_where_stmt");
		
		this.doSelectCount(where, params);
		
		int currentPageNum = 1;
		try {
			currentPageNum = Integer.parseInt(request.getParameter("current_page_num"));
		} catch ( Exception e ) {}
		context.put("current_page_num", currentPageNum);
		list(currentPageNum);
	}

	public String getPage() throws Exception {
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("page_num")); 
		} catch ( Exception e ) {}
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
	}
	
	public String getNextPage() throws Exception {
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("page_num")); 
		} catch ( Exception e ) {}
		pageNum++;
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
	}
	
	public String getPreviousPage() throws Exception {
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("page_num")); 
		} catch ( Exception e ) {}
		pageNum--;
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
	}

	public String getFirstPage() throws Exception {
		int pageNum = 1;
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
		
	}

	public String getLastPage() throws Exception {
		int pageNum =  Integer.parseInt(request.getParameter("last_page"));
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
		
	}
	
	public String edit() throws Exception {
		
		Object recordId = getRecordId();
		T r = db.find(objectClass, recordId);
		context.put("r", r);
		getRelatedData(r);
		String pageNum = request.getParameter("page_num");
		context.put("current_page_num", pageNum);
		return templateDir + "/entry_fields.vm";
	}
	
	private Object getRecordId() {
		if ( getIdType() == Long.class ) {
			return !"".equals(get("record_id")) ? Long.parseLong(get("record_id")) : 0;
		} else if ( getIdType() == Integer.class ) {
			return !"".equals(get("record_id")) ? Integer.parseInt(get("record_id")) : 0;
		}
		return request.getParameter("record_id");
	}

	
	public String delete() throws Exception {
		Object recordId = getRecordId();
		T r = db.find(objectClass, recordId);
		if ( delete(r) ) {
			db.begin();
			db.remove(r);
			db.commit();
			
			
			this.doSelectCount(where, params);
			
			context.put("update_counter", true);
			System.out.println("data deleted success!");
			context.put("success_flag", "record_delete_success");
		}
		else {
			int dataSize = Integer.parseInt(get("data_size"));
			int totalPage = Integer.parseInt(get("total_page"));
			context.put("total_page", totalPage);
			context.put("data_size", dataSize);
			context.put("update_counter", true);	
			System.out.println("data can't be deleted... unsucessfull");
			context.put("error_flag", "record_delete_failed");
		}
		
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("page_num")); 
		} catch ( Exception e ) {}
		context.put("current_page_num", pageNum);
		list(pageNum);
		return templateDir + "/div_items.vm";
	}
	
	
	public String addNewRecord() throws Exception {
		String currentPageNum = get("current_page_num");
		context.put("current_page_num", currentPageNum);
		context.remove("r");
		return templateDir + "/entry_fields.vm";
	}
	
	public String saveDataAndAddNew() throws Exception {
		save();
		context.remove("r");
		return templateDir + "/entry_fields.vm";
	}

	
	public String saveData() throws Exception {
		T r = save();
		context.put("r", r);
		return templateDir + "/entry_fields.vm";
	}
	
	private T save() throws InstantiationException, IllegalAccessException, Exception {
		
		beforeSave();
		
		String currentPageNum = get("current_page_num");
		context.put("current_page_num", currentPageNum);
		
		Object recordId = getRecordId();
		T r = null;
		if ( recordId instanceof String ) {
			r = !"".equals(recordId) ? db.find(objectClass, recordId) : null;
		} else if ( recordId instanceof Long ) {
			r = (Long) recordId != 0 ? db.find(objectClass, recordId) : null;
		} else if ( recordId instanceof Integer ) {
			r = (Integer) recordId != 0 ? db.find(objectClass, recordId) : null;
		}
		boolean addnew = r == null;
		if ( addnew ) {
			r = objectClass.newInstance();
			isNew = true;
		} else {
			isNew = false;
		}
		db.begin();
		save(r);
		if ( addnew ) {
			db.persist(r);
		}
		db.commit();
		
		afterSave(r);
		
		//find again -- to make sure data is saved by JPA
		if ( !addnew )
			r = db.find(objectClass, recordId);
		return r;
	}
	

	private String listFind() throws Exception {
		String currentPageNum = get("current_page_num");
		context.put("current_page_num", currentPageNum);
		
		System.out.println("DOING SEARCH CRITERIA....");
		String where = "";
		Map<String, Object> searchMap = searchCriteria();
		Hashtable h = new Hashtable();
		Set<String> keys = searchMap.keySet();
		for ( String key : keys ) {
			
			if ( searchMap.get(key) instanceof String ) {
				if ( !"".equals(searchMap.get(key))) {
					if ( !"".equals(where)) where += " and ";
					where += " x." + key + " like '%" + searchMap.get(key) + "%' ";
				}
			}
			if ( searchMap.get(key) instanceof Integer ||  searchMap.get(key) instanceof Long 
					|| searchMap.get(key) instanceof Double || searchMap.get(key) instanceof Float ) {
				Object value = searchMap.get(key);
				if ( value instanceof Long ) {
					if ( ((Long) value) != -99999 ) {
						if ( !"".equals(where)) where += " and ";
						//where += " x." + key + " = " + value + " ";
						where += " x." + key + " = :" + key.replace('.', '_');
						h.put(key.replace('.', '_'), (Long) value);
					}
				} else if ( value instanceof Integer ) {
					if ( ((Integer) value) != -99999 ) {
						if ( !"".equals(where)) where += " and ";
						//where += " x." + key + " = " + value + " ";
						where += " x." + key + " = :" + key.replace('.', '_');
						h.put(key.replace('.', '_'), (Integer) value);
					}
				} else {
					if ( !"".equals(where)) where += " and ";
					where += " x." + key + " = " + value + " ";
				}
			}
		}
		
		request.getSession().setAttribute("_where_stmt", where);
		request.getSession().setAttribute("_where_params", h);
		
		doSelectCount(where, h);
	
		list(1);
		
		return templateDir + "/list.vm";

	}
	
	private void doSelectCount(String where, Hashtable h) {
		System.out.println("WHERE = " + where);
		//if ( selectCountStmt == null ) {
			selectCountStmt = "select count(x) from " + objectClass.getSimpleName() + " x ";
			if ( filterList.size() > 0 ) {
				int i = 0;
				for ( String s : filterList ) {
					if ( i == 0 ) {
						selectCountStmt += " where x." + s;
					} else {
						selectCountStmt += " and x." + s;
					}
					i++;
				}
				if ( where != null && !"".equals(where) ) selectCountStmt += " and " + where + " ";
			} else {
				if ( where != null && !"".equals(where) ) selectCountStmt += " where " + where + " ";
			}
		
		//System.out.println(selectCountStmt);
		//}
		//long dataSize = (Long) db.get(selectCountStmt);
		
		System.out.println(h);
			
		List<Long> listCount = h != null ? db.list(selectCountStmt, h) : db.list(selectCountStmt);
		long dataSize = listCount.get(0);
		long totalPage = dataSize % rowSize <= 0 ? dataSize / rowSize : (dataSize / rowSize) + 1;  
		context.put("total_page", (int) totalPage);
		context.put("data_size", dataSize);
	}	
	
	public static void main(String[] args) {
		
		String txt = "status.id.x";
		System.out.println(txt.replace('.', '_'));
		
	}
	

}
