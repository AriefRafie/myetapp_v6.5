/**
 * 
 */
package ekptg.view.integrasi.sid;

import integrasi.rest.sid.SIDManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;

public class FrmCarianFailHTP extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String submit = getParam("command");

		Vector list = new Vector();
		
		String noFail = getParam("txtNoFail");
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0) {
			idDaerah = "99999";
		}

		try {			
			if ("carian".equals(submit) || "doChanges".equals(submit)) {				
				if ("".equals(noFail))
					noFail = "-";
				
				list = SIDManager.getArkibDokumenHTP(noFail, getkodNegeriByIdNegeri(idNegeri), getKodDaerahByIdNegeri(idDaerah));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.context.put("SenaraiFail", list);
		this.context.put("noFail", getParam("txtNoFail"));
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "",""));
		System.out.println(SIDManager.getFlagMsg());
		System.out.println(SIDManager.getOutputMsg());

		setupPage(session, action, list);

		return "app/integrasi/sid/frmCarianFailHTP.jsp";
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiFail", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	public String getkodNegeriByIdNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_NEGERI");
			} else {
				return "-";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodDaerahByIdNegeri(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLRUJNEGERI.KOD_NEGERI || TBLRUJDAERAH.KOD_DAERAH AS KOD_DAERAH FROM TBLRUJNEGERI, TBLRUJDAERAH WHERE TBLRUJNEGERI.ID_NEGERI = TBLRUJDAERAH.ID_NEGERI"
					+ " AND TBLRUJDAERAH.ID_NEGERI = '" + idDaerah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_DAERAH");
			} else {
				return "-";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
}
