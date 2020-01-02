package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmLaporanPrestasiPegawai extends AjaxBasedModule {
	Vector<Hashtable<String,String>> negeriLogin = null;
	Vector<Hashtable<String,String>> unitLogin = null;
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.FrmLaporanPrestasiPegawai.class);

	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String idNegeri = getParam("socNegeri").equals("")
							?String.valueOf(session.getAttribute("_ekptg_user_negeri")):getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "-1";
		}
		//myLog.info("idNegeri="+idNegeri);
		String idUnit = getParam("socUnit").equals("")
							?String.valueOf(session.getAttribute("_ekptg_user_unit")):getParam("socUnit");
		if (idUnit == null || idUnit.trim().length() == 0){
			idUnit = "0";
		}
		//myLog.info("idUnit="+idUnit);
		String tahun = getParam("socTahun");
		if (tahun == null || tahun.trim().length() == 0){
			tahun = "0";
		}
		//String submit = getParam("command");
		String reportRole = String.valueOf(session.getAttribute("_portal_role"));
		//myLog.info("reportRole="+reportRole);
		String selectNegeri = "";
		String selectUnit = "";
		//negeri = getIdNegeriByLogin(usid);
		//Hashtable hN = (Hashtable)negeri.get(0);
		//context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340"));
		//context.put("selectNegeri",HTML.SelectNegeriWithOnChange("socNegeri"));
		if(reportRole.equals("adminppk") || reportRole.equals("meps") || reportRole.equals("(MEPS)PPK")){
			String socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",Long.parseLong(idNegeri),""," onChange=\"doChangeNegeri()\"", null);
			selectNegeri = getHTMLNegeri(false,socNegeri);			
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
					"socUnit",Utils.parseLong(idUnit),"","style=width:400 onChange=\"doChangeUnit();\"",idNegeri);
			selectUnit = getHTMLUnit(false,"",socUnit);
			
		}else if(reportRole.equals("usernegeri_ppk")){
			selectNegeri = getHTMLNegeri(true,idNegeri);
			String socUnit = PPKUtilHTML.SelectUnitPPKLaporan(
							"socUnit",Utils.parseLong(idUnit),"","style=width:400 onChange=\"doChangeUnit();\"",idNegeri);
			selectUnit = getHTMLUnit(false,"",socUnit);

		}else if(reportRole.equals("user_ppk")){
			selectNegeri = getHTMLNegeri(true,idNegeri);
			String socUnit = PPKUtilData.getNamaUnitPPK(idUnit);
			selectUnit = getHTMLUnit(true,idUnit,socUnit);
		}
		this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));

//		if ("doChangeNegeri".equals(submit)){			
//			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
//			this.context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit", Long.parseLong(idNegeri), "", "", idNegeri));
//			this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=\"width=80px\""));
//
//		}
		context.put("selectNegeri",selectNegeri);
	    context.put("selectUnit",selectUnit);
	    context.put("reportRole",reportRole);

		return "app/ppk/frmLaporanPrestasiPegawai.jsp";
		
	}
	
	public Vector<Hashtable<String,String>> getIdNegeriByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 
		 try{
			 negeriLogin = new Vector<Hashtable<String,String>>();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_NEGERI FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable<String,String> h = null;
		     while (rs.next()) {		    	  
		    	  h = new Hashtable<String,String>();
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	  negeriLogin.addElement(h);
		    	  
		     }		     
		     return negeriLogin;
			 
		 }finally {
			if(db != null) db.close();	
		}
		 
	 }
	
	public Vector<Hashtable<String,String>> getIdUnitByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 
		 try{
			 unitLogin = new Vector<Hashtable<String,String>>();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable<String,String> h = null;
		     while (rs.next()) {	    	  
		    	  h = new Hashtable<String,String>();
		    	  h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG"));
		    	  unitLogin.addElement(h);
		    	  
		     }		     
		     return unitLogin;
			 
		 }finally {
			if(db != null) db.close();
		}
		 
	 }
	private String getHTMLNegeri(boolean dsable,String negeri){
        String htmlNegeri = "<tr> ";
        htmlNegeri += "<td width=\"30%\" align=\"right\"><span class=\"style1\">Negeri</span></td>";
        htmlNegeri += "<td width=\"70%\">: "+negeri+"</td>";
        htmlNegeri += "</tr>";
        if(dsable)
            htmlNegeri = "<input type=\"hidden\" name=\"socNegeri\" value=\""+negeri+"\">";

        	
        return htmlNegeri;
      
	}
	private String getHTMLUnit(boolean dsable,String idUnit,String unit){
		 String htmlUnit = "<tr>";
		 htmlUnit += "<td width=\"30%\" align=\"right\"><span class=\"style1\">Unit</span></td>";
		 htmlUnit += "<td width=\"70%\">: "+unit+"</td>";
		 htmlUnit += "</tr>";
		 if(dsable)
			 htmlUnit += "<input type=\"hidden\" name=\"socUnit\" value=\""+idUnit+"\">";

		 return htmlUnit;
		 
	}
	
	
}
