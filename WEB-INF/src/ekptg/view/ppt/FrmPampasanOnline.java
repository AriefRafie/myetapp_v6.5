package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmSek8PampasanData;

public class FrmPampasanOnline extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahanSenaraiOnline.class);

	// MODEL
	FrmSek8PampasanData newmodel = new FrmSek8PampasanData();

	@Override
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		// get user login detail
		String action = getParam("action");
		String submit = getParam("command");
		myLogger.info("submit : " + submit);
		String vm = "";
		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");
		String findNoFail = getParam("findNoFail");
		String findNamaProjek = getParam("findNamaProjek");
		String findNoCekEFT = getParam("findNoCekEFT");
		String findKaedahBayar = getParam("findKaedahBayar");
		
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);

		// screen jsp
		String skrinPampasan = "app/ppt/frmPampasanOnline.jsp";
		
		Vector listSuratAgensiOnline = null;
        
			if ("cari".equals(submit)) {
				
				Vector cara_bayar = new Vector();
	
				listSuratAgensiOnline = newmodel.setlistSuratAgensiOnline(findNoFail, findNamaProjek, findNoCekEFT, findKaedahBayar, getUserIC(session, usid));
				this.context.put("listSuratAgensiOnline", listSuratAgensiOnline);
				
				cara_bayar = newmodel.dropdown_caraBayar();
				this.context.put("cara_bayar", cara_bayar);
				
				this.context.put("findNoFail", getParam("findNoFail"));	
				this.context.put("findNamaProjek", getParam("findNamaProjek"));
				this.context.put("findNoCekEFT", getParam("findNoCekEFT"));
				this.context.put("findKaedahBayar", getParam("findKaedahBayar"));
	
				// screen
				vm = skrinPampasan;
	
			}// close cari
			
			else {// GO TO PAMPASAN
						
				Vector listFail = newmodel.setlistSuratAgensiOnline(findNoFail, findNamaProjek, findNoCekEFT, findKaedahBayar, getUserIC(session, usid));
				this.context.put("listSuratAgensiOnline", listFail);
				setupPage(session, action, listFail);
				
				context.remove("findNoFail");
				context.remove("findNamaProjek");
				context.remove("findNoCekEFT");
				context.remove("findKaedahBayar");
			}
			
		return skrinPampasan;
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
