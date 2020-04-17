package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;


public class HTPEmelSemakBean extends EkptgCache implements IHTPEmel {
	
 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.HTPEmelSemakBean.class);
	private Vector checkEmail = null;

	@Override
	public String checkEmail(String userId) throws Exception {
		String returnValue = "";
		//checkEmail = new Vector();
		//checkEmail.clear();	
		Db db = null;
		String sql = "";		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
	
			myLog.info("checkEmail:sql="+sql.toUpperCase());		
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				//h = new Hashtable();
				//h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
				//checkEmail.addElement(h);
				returnValue = Utils.isNull(rs.getString("EMEL"));
			}
			//return checkEmail;
		}catch(Exception e){ 	
			getIHTP().getErrorHTML(e.toString());
		
		} finally {
			if (db != null)	db.close();
			
		}
		//return checkEmail;
		return returnValue;
	
	}

	@Override
	public String setEmailSign(String noFail){		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");		  
		bff.append("Permohonan Tuan/Puan telah diterima dan nombor fail adalah seperti berikut "+noFail+".");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan serta pengesahan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
		
	}
	
	@Override
	public String setEmailSign(String noFail,String tajuk, String dari){		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");		  
	    bff.append("Minta Tuan/Puan  menyemak/mengesahkan permohonan " +
	    		"<b>"+tajuk+"</b> daripada "+dari+" - <b>"+noFail+"</b>.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> ke <a href=\"http://www.etapp.gov.my\" >www.etapp.gov.my</a> untuk semakan selanjutnya.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
		
	}
	
		
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
	
	
}
