package etapp.template;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lebah.portal.velocity.VTemplate;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

public abstract class VMobile<T> extends VTemplate {
	static Logger myLogger = Logger.getLogger(VMobile.class);

	private Class<T> objectClass;
	protected DbPersistence db = new DbPersistence();
	protected String tableHeaderPage;
	protected String selectCountStmt;
	protected String dataRowPage;
	protected String selectStmt;
	protected String sequence;
	protected String orderBy;
	protected String where;
	protected int rowSize = 10;
	protected int rowStart = 0;

	public void setPersistentClass(final Class<T> objectClass) {
		this.objectClass = objectClass;
	}

	public void setTableHeaderPage(String headerPage) {
		this.tableHeaderPage = headerPage;
	}

	public void setDataRowPage(String dataPage) {
		this.dataRowPage = dataPage;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
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

	public abstract Map<String, Object> searchCriteria() throws Exception;

	public void listRecord(int pageNum) {
		int rowStart = pageNum > 1 ? (pageNum - 1) * rowSize : 0;
		context.put("row_start", rowStart);
		context.put("page_num", pageNum);

		where = request.getSession().getAttribute("_where_stmt") != null ? (String) request
				.getSession().getAttribute("_where_stmt") : null;

		if (selectStmt == null) {
			selectStmt = "SELECT x FROM " + objectClass.getSimpleName() + " x";

			// System.out.println("APA YANG ADA DALAM WHERE NIE : " + where);

			if (where != null)
				selectStmt += " WHERE " + where;
			if (orderBy != null)
				selectStmt += " ORDER BY x." + orderBy + " " + sequence;

			List<T> dataSize = db.list(selectStmt);
			// long dataSize = (Long) db.get(selectStmt);
			long totalPage = dataSize.size() % rowSize <= 0 ? dataSize.size()
					/ rowSize : (dataSize.size() / rowSize) + 1;

			context.put("total_page", (int) totalPage);
			context.put("data_size", dataSize.size());

			List<T> records = db.list(selectStmt, rowStart, rowSize);
			context.put("records", records);
		} else {
			List<T> records = db.list(selectCountStmt, rowStart, rowSize);
			context.put("records", records);
		}
		// myLogger.info("---------------> Statement SQL : " + selectStmt);
	}

	public void listRecord(List<String> idCollection) {
		selectStmt = "SELECT x FROM " + objectClass.getSimpleName() + " x";
		String where = "";

		Object IC[] = idCollection.toArray();
		for (int i = 0; i < idCollection.size(); i++) {
			if (!"".equals(where))
				where += " AND ";
			where += IC[i];
		}

		// System.out.println(selectStmt);

		if (!"".equals(where))
			selectStmt += " WHERE " + where;
		if (orderBy != null)
			selectStmt += " ORDER BY x." + orderBy + " " + sequence;

		// System.out.println(selectStmt);

		request.getSession().setAttribute("_where_stmt", where);

		List<T> records = db.list(selectStmt, rowStart, rowSize);
		context.put("records", records);
	}

	public void listFindRecord() throws Exception {
		int pageNum = 1;
		context.put("current_page_num", pageNum);

		String where = "";
		selectCountStmt = null;
		Map<String, Object> searchMap = searchCriteria();

		// System.out.println("Where statement : " + where);

		Set<String> keys = searchMap.keySet();
		for (String key : keys) {
			if (!"".equals(where))
				where += " AND ";
			if (searchMap.get(key) instanceof String)
				where += "x." + key + " LIKE '" + searchMap.get(key) + "'";
			if (searchMap.get(key) instanceof Integer
					|| searchMap.get(key) instanceof Long
					|| searchMap.get(key) instanceof Double
					|| searchMap.get(key) instanceof Float)
				where += "x." + key + " = " + searchMap.get(key);
		}

		// System.out.println("Where statement 2 : " + where);

		request.getSession().setAttribute("_where_stmt", where);

		if (selectCountStmt == null)
			selectCountStmt = "SELECT count(x) FROM "
					+ objectClass.getSimpleName() + " x ";
		if (!"".equals(where))
			selectCountStmt += " WHERE " + where;

		// System.out.println("Selection count : " + selectCountStmt);

		try {
			long dataSize = (Long) db.get(selectCountStmt);
			long totalPage = dataSize % rowSize <= 0 ? dataSize / rowSize
					: (dataSize / rowSize) + 1;

			context.put("total_page", (int) totalPage);
			context.put("data_size", dataSize);

			listRecord(1);
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}

	public void getPage(int pageSelect) throws Exception {
		int pageNum = 1;
		try {
			pageNum = pageSelect;
		} catch (Exception e) {
		}
		context.put("current_page_num", pageNum);
		listRecord(pageNum);
	}
}