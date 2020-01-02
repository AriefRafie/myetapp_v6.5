/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ekptg.view;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.portal.element.Tab;

import org.apache.log4j.Logger;

public class FrmManualPenggunaPPT extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7403463147357674035L;
	private final String PATH="app/";
    private static Logger myLog = Logger.getLogger(ekptg.view.FrmManualPenggunaPPT.class);
    private Vector<Tab> vecTabs = null;  
    
    @Override
    public String doTemplate2() throws Exception {
      	//
    	HttpSession session = this.request.getSession();
		//String userId = String.valueOf(session.getAttribute("_ekptg_user_id"));
		String role = String.valueOf(session.getAttribute("myrole"));
		//myLog.info("userId="+userId);

    	//Vector tabs =  getTabs(role);
		getTabs(role);
		ResourceBundle rb = ResourceBundle.getBundle("file");
		String path = rb.getString("generalpath")+"manualpengguna/";

		this.context.put("manual",setContext(vecTabs,path));
		this.context.put("modul_","ekptg.view.ppt.dashboard.Dashboard");
		String vm = PATH+"frmManual.jsp";
        return vm;
        
	}
	private String setContext(Vector<Tab> tabs,String manualPath){
    	StringBuffer sb = new StringBuffer("");
    	Tab tab = null;
    	//myLog.info("tabs.size()="+tabs.size());
		try {

			for (int i = 0; i < tabs.size(); i++) {
				tab = (Tab)tabs.get(i);
				String namaFail = "MyeTaPP_UMPPT_"+tab.getTitle().replace("/", "").replace(" ", "")+"_v6.pdf";
				//myLog.info(namaFail);
				sb.append("<tr>");
				sb.append("	<td>&nbsp;</td>");
				sb.append("	<td>");
				//<td> <a href="javascript:openManualX('PanduanModulHTP')" class=style1><b>Manual Pengguna Modul Harta Tanah Persekutuan (HTP)</b></a>	
				sb.append("		<a href=\"../download?file="+manualPath+namaFail+"\" class=\"style1\">");
				sb.append("			<b>Manual Pengguna "+getNama(tab.getTitle())+"</b>");
				sb.append("		</a>");				
				sb.append("	</td>");				
				sb.append("</tr>");

			//if(!modul.equals("Utiliti") && !modul.equals("<i>Dashboard</i>") && !modul.equals("Panduan"))
			//	newTabs.addElement(modul);

			}
			//myLog.info("tabs="+tabs);
   
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}

	public Vector<Tab> getTabs(String role) throws Exception {
		Db db = null;
		String sql = "";
		vecTabs = new Vector<Tab>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT * FROM TAB_TEMPLATE " +
				" WHERE USER_LOGIN= '" + role + "'" +
				" ORDER BY SEQUENCE ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Tab tab = new Tab();
				if(
						//!rs.getString("tab_title").equals("Utiliti") && 
						!rs.getString("tab_title").equals("Dashboard")  
						&& !rs.getString("tab_title").equals("'My Info'")
						//&& !rs.getString("tab_title").equals("<i>Leading KPI</i>")
						//&& !rs.getString("tab_title").equals("<i>Lagging KPI</i>")
						&& !rs.getString("tab_title").equals("Panduan")	){
					tab.setId(rs.getString("tab_id"));
					tab.setTitle(rs.getString("tab_title"));
					//tab.setSequence(rs.getInt("sequence"));
					//tab.setDisplaytype(rs.getString("display_type"));
					vecTabs.addElement(tab);
					
				}
	
			}
		} finally {
			if (db != null)
				db.close();
		}
		return vecTabs;
	}
	private String getNama(String s){
		String returnVal;
		switch (s) {
		     case "P/Kecil":
		    	 returnVal = "Pajakan Kecil";
		    	 break;
		     default:
		    	 returnVal = s;
		}
		return returnVal;
	     
	}
	
	
}
