package lebah.portal.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.Log;
import lebah.db.SQLRenderer;
import lebah.db.UniqueID;
import lebah.portal.element.Module2;
import lebah.portal.element.Tab;
import ekptg.helpers.Utils;

public class UserTabDb {
	public static Vector retrieve(String usrlogin) throws DbException {
		Db db = null;
		String sql = "";
		Vector vector;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			Vector v = new Vector();
			SQLRenderer r = new SQLRenderer();
			r.add("tab_id");
			r.add("tab_title");
			r.add("display_type");
			r.add("locked");
			r.add("user_login", usrlogin);
			sql = r.getSQLSelect("tab_template", "sequence");
			Tab tab;
			for (ResultSet rs = stmt.executeQuery(sql); rs.next(); v
					.addElement(tab)) {
				tab = new Tab();
				tab.setId(rs.getString("tab_id"));
				tab.setTitle(rs.getString("tab_title"));
				tab.setDisplaytype(rs.getString("display_type"));
				int locked = rs.getInt("locked");
				tab.setLocked(locked == 1);
			}

			for (Iterator iterator = v.iterator(); iterator.hasNext();) {
				Tab tab1 = (Tab) iterator.next();
				if (!("pulldown_menu".equals(tab1.getDisplayType())))
					continue;
				sql = r.reset().add("t.tab_id", tab1.getId())
						.add("t.user_login", usrlogin).add("t.module_id")
						.add("t.module_custom_title").add("m.module_title")
						.add("m.module_class")
						.relate("t.module_id", "m.module_id")
						.getSQLSelect("user_module_template t, module m")
						.concat(" order by t.sequence");
				Module2 module;
				for (ResultSet rs = stmt.executeQuery(sql); rs.next(); tab1
						.addModule(module)) {
					String moduleId = rs.getString("module_id");
					String moduleTitle = rs.getString("module_title");
					String moduleClass = rs.getString("module_class");
					String s = rs.getString("module_custom_title");
					module = new Module2(moduleId, moduleTitle, moduleClass, s);
				}

			}

			vector = v;
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage() + ": " + sql);
		} finally {
			if (db != null)
				db.close();
		}
		return vector;
	}

	public static void changeSequence(String usrlogin, String tab, String pos)
			throws DbException {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			int sequence = 0;
			sql = "SELECT sequence FROM tab_template WHERE tab_id = '" + tab
					+ "' AND user_login = '" + usrlogin + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				sequence = rs.getInt("sequence");
			String tab2 = "";
			if ("down".equals(pos)) {
				sql = "SELECT tab_id FROM tab_template WHERE user_login = '"
						+ usrlogin + "' AND sequence = "
						+ Integer.toString(++sequence);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					tab2 = rs.getString("tab_id");
				if (!("".equals(tab2))) {
					sql = "UPDATE tab_template SET sequence = " + sequence
							+ " WHERE tab_id = '" + tab
							+ "' AND user_login = '" + usrlogin + "'";
					stmt.executeUpdate(sql);
					sql = "UPDATE tab_template SET sequence = "
							+ Integer.toString(--sequence)
							+ " WHERE tab_id = '" + tab2
							+ "' AND user_login = '" + usrlogin + "'";
					stmt.executeUpdate(sql);
				}
			} else if ("up".equals(pos)) {
				sql = "SELECT tab_id FROM tab_template WHERE user_login = '"
						+ usrlogin + "' AND sequence = "
						+ Integer.toString(--sequence);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					tab2 = rs.getString("tab_id");
				if (!("".equals(tab2))) {
					sql = "UPDATE tab_template SET sequence = " + sequence
							+ " WHERE tab_id = '" + tab
							+ "' AND user_login = '" + usrlogin + "'";
					stmt.executeUpdate(sql);
					sql = "UPDATE tab_template SET sequence = "
							+ Integer.toString(++sequence)
							+ " WHERE tab_id = '" + tab2
							+ "' AND user_login = '" + usrlogin + "'";
					stmt.executeUpdate(sql);
				}
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void fixTabSequence(String usrlogin) throws DbException {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			boolean fix = false;
			sql = "SELECT sequence FROM tab_template WHERE user_login = '"
					+ usrlogin + "' AND sequence = 0";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				fix = true;
			if (fix) {
				Vector v = new Vector();
				sql = "SELECT tab_id FROM tab_template WHERE user_login = '"
						+ usrlogin + "'";
				String tab_id;
				for (rs = stmt.executeQuery(sql); rs.next(); v
						.addElement(tab_id)) {
					tab_id = rs.getString("tab_id");
				}
				for (int i = 0; i < v.size(); ++i) {
					String tab_id1 = (String) v.elementAt(i);
					sql = "UPDATE tab_template SET sequence = "
							+ Integer.toString(i + 1) + " WHERE tab_id = '"
							+ tab_id1 + "' AND user_login = '" + usrlogin + "'";
					Log.print(sql);
					stmt.executeUpdate(sql);
				}
			}

			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Tab getTab(String usrlogin, String tabid) throws DbException {
		Tab tab = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("tab_title");
			r.add("display_type");
			r.add("user_login", usrlogin);
			r.add("tab_id", tabid);
			sql = r.getSQLSelect("tab_template");
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String tab_title = rs.getString("tab_title");
				String displaytype = rs.getString("display_type");
				tab = new Tab(tabid, tab_title, displaytype);
			}
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + " : " + sql);
		}
		if (db != null)
			db.close();
		return tab;
	}

	public static boolean addNewTab(String usrlogin, String tabid,
			String tabtitle) throws DbException {
		return addNewTab(usrlogin, tabid, tabtitle, "");
	}

	public static boolean addNewTab(String usrlogin, String tabid,
			String tabtitle, String displaytype) throws DbException {
		if ("".equals(displaytype))
			displaytype = "left_navigation";
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			tabid = UniqueID.getUID();
			int max_seq = 0;
			sql = "SELECT MAX(sequence) AS seq FROM tab_template WHERE user_login = '"
					+ usrlogin + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				max_seq = rs.getInt("seq");
			SQLRenderer r = new SQLRenderer();
			r.add("tab_id", tabid);
			r.add("tab_title", tabtitle);
			r.add("user_login", usrlogin);
			r.add("display_type", displaytype);
			r.add("sequence", ++max_seq);
			r.add("locked", 1);
			sql = r.getSQLInsert("tab_template");
			stmt.executeUpdate(sql);
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + ": " + sql);
		}
		if (db != null)
			db.close();
		return true;
	}

	public static void deleteTab(String usrlogin, String tabid)
			throws DbException {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			int sequence = 0;
			sql = "SELECT sequence FROM tab_template WHERE tab_id = '" + tabid
					+ "' AND user_login = '" + usrlogin + "' ";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				sequence = rs.getInt("sequence");
			sql = "DELETE FROM user_module_template WHERE tab_id = '" + tabid
					+ "' AND user_login = '" + usrlogin + "' ";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM tab_template WHERE tab_id = '" + tabid
					+ "' AND user_login = '" + usrlogin + "' ";
			stmt.executeUpdate(sql);
			sql = "UPDATE tab_template SET sequence = sequence - 1 WHERE user_login = '"
					+ usrlogin + "' AND sequence > " + sequence;
			stmt.executeUpdate(sql);
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + ": " + sql);
		}
		if (db != null)
			db.close();
	}

	public static void changeTitle(String usrlogin, String tab, String title)
			throws DbException {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "UPDATE tab_template SET tab_title='"
					+ Utils.escapeSQL(title) + "' WHERE tab_id='" + tab
					+ "' and user_login = '" + usrlogin + "' ";

			stmt.executeUpdate(sql);
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + ": " + sql);
		}
		if (db != null)
			db.close();
	}

	public static void changeTitleAndDisplayType(String usrlogin, String tab,
			String title, String displaytype) throws DbException {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "UPDATE tab_template SET tab_title = '" + title
					+ "', display_type = '" + displaytype + "' "
					+ "WHERE tab_id = '" + tab + "' AND user_login = '"
					+ usrlogin + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + ": " + sql);
		}

		if (db != null)
			db.close();
	}

	public static void changeDisplayType(String usrlogin, String tab,
			String displaytype) throws DbException {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "UPDATE tab_template SET display_type = '" + displaytype
					+ "' " + "WHERE tab_id = '" + tab + "' AND user_login = '"
					+ usrlogin + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException sqlex) {
			throw new DbException(sqlex.getMessage() + ": " + sql);
		}
		if (db != null)
			db.close();
	}

	public static Vector getRoles() {
		//String sql = "select name, description from role order by name";
		String sql = "SELECT R1.NAME AS name, REGEXP_REPLACE (REGEXP_REPLACE (R1.THEME, 'eTapp_', ''), '.css', '') AS kod, " +
					" UPPER (R1.DESCRIPTION) AS description, "+//R1.ROLE_DETAILS as details, 
					" 2 AS layer FROM   ROLE R1 UNION " + 
					" SELECT DISTINCT 'none' AS name,  REGEXP_REPLACE (REGEXP_REPLACE (R3.THEME, 'eTapp_', ''), '.css', '') AS kod, " +
					" '' AS description, "+ //R3.ROLE_DETAILS as details, 
					" 1 AS layer FROM   ROLE R3 WHERE   R3.NAME IS NOT NULL "+ 
					" ORDER BY   kod, layer ";
		
		System.out.println("sql -- "+sql);
		Db database = null;
		Vector list = new Vector();
		try {
			Role obj = null;
			database = new Db();
			Statement stmt = database.getStatement();
			for (ResultSet rs = stmt.executeQuery(sql); rs.next(); list
					.addElement(obj)) {
				obj = new Role();

				obj.setName(rs.getString("name"));
				obj.setDescription(rs.getString("description"));
				obj.setKod(rs.getString("kod"));
				obj.setLayer(rs.getString("layer"));
				//obj.setDetails(rs.getString("details"));
			}

		} catch (DbException dbex) {
			System.out.println("PrepareTemplateTab.getRoles(): DbException : "
					+ dbex.getMessage());
		} catch (SQLException ex) {
			System.out.println("PrepareTemplateTab.getRoles(): SQLException : "
					+ ex.getMessage());
		} finally {
			if (database != null)
				database.close();
		}
		return list;
	}

	public static void saveTabsOrder(String[] tabIds, String[] lockIds,
			String role) throws Exception {
		if (tabIds == null)
			return;
		Db db = null;
		String sql = "";
		db = new Db();
		int seq = 0;
		String[] as = tabIds;
		int i = 0;
		for (int k = as.length; i < k; ++i) {
			String tabId = as[i];
			sql = "update tab_template set sequence = " + (seq++)
					+ ", locked = 0 where tab_id = '" + tabId
					+ "' and user_login = '" + role + "'";
			db.getStatement().executeUpdate(sql);
		}

		if (lockIds != null) {
			String[] as1 = lockIds;
			int j = 0;
			for (int l = as1.length; j < l; ++j) {
				String tabId = as1[j];
				sql = "update tab_template set locked = 1 where tab_id = '"
						+ tabId + "' and user_login = '" + role + "'";
				db.getStatement().executeUpdate(sql);
			}
		}

		if (db == null)
			return;
		db.close();
	}

	public static void saveTabsOrderPersonal(String[] tabIds, String user)
			throws Exception {
		if (tabIds == null)
			return;
		Db db = null;
		String sql = "";
		db = new Db();
		int seq = 0;
		String[] as = tabIds;
		int i = 0;
		for (int j = as.length; i < j; ++i) {
			String tabId = as[i];
			sql = "update tabs set sequence = " + (seq++) + " where tab_id = '"
					+ tabId + "' and user_login = '" + user + "'";
			db.getStatement().executeUpdate(sql);
		}
		if (db == null)
			return;
		db.close();
	}
}