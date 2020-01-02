package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmSek8PampasanData;

public class FrmStatusBicaraOnline extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahanSenaraiOnline.class);

	// MODEL
	FrmSek8PampasanData newmodel = new FrmSek8PampasanData();

	@Override
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");

		String vm = "";
		Vector listBicaraOnline = null;

		String doPost = (String) session.getAttribute("doPost");
		String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
		String submit = getParam("command");
		myLogger.info("SUBMIT :: " + submit);
		
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);
		
		String skrinStatusBicara = "app/ppt/frmStatusBicaraOnline.jsp";
        
			if ("cari".equals(submit)) {
				
				listBicaraOnline = newmodel.setStatusBicaraOnline(getParam("findNoFail"), getParam("findNamaProjek"), getParam("findNoSiasatan"), getUserIC(session, usid));
				this.context.put("listBicaraOnline", listBicaraOnline);
				
				this.context.put("findNoFail", getParam("findNoFail"));	
				this.context.put("findNamaProjek", getParam("findNamaProjek"));
				this.context.put("findNoSiasatan", getParam("findNoSiasatan"));

			// screen
			vm = skrinStatusBicara;

			}// close cari
			else {// GO TO STATUS BICARA
			
				String findNoFail = getParam("findNoFail");
				String findNamaProjek = getParam("findNamaProjek");
				String findNoSiasatan = getParam("findNoSiasatan");
						
				Vector listFail = newmodel.setStatusBicaraOnline(findNoFail, findNamaProjek, findNoSiasatan, getUserIC(session, usid));
				this.context.put("listBicaraOnline", listFail);
				setupPage(session, action, listFail);
				
				context.remove("findNoFail");
				context.remove("findNamaProjek");
				context.remove("findNoSiasatan");
			}
		return skrinStatusBicara;
	}
	
	// METHOD

		public String getUserIC(HttpSession session, String USER_ID) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String getUserIC="";
				sql = " SELECT UO.NO_KP_BARU FROM USERS U, USERS_ONLINE UO "+
						" WHERE U.USER_ID = UO.USER_ID "+
						" AND U.USER_ID = '"+USER_ID+"'";				
					myLogger.info(" OT : getUserIC :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					
					
					while (rs.next()) {				

						getUserIC = (rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						
					}
				
				return getUserIC;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
	}
