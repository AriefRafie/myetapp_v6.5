package ekptg.model.htp.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.IEmel;


public class HTPEmelKelulusanBean implements IEmel {
	
	//HTPEmelSemakanBean
 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.HTPEmelSemakanBean.class);
	private Vector checkEmail = null;
	private String tajukDefault = "MyeTaPP : Semakan/ Pengesahan Permohonan ";

	
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
		bff.append("Sila <i>login</i> masuk ke www.myetapp.gov.my untuk semakan serta pengesahan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		//bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh sistem MyeTaPP dan tidak perlu dibalas.");
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
	    bff.append("Berdasarkan maklumat yang diberikan, Jabatan ini tiada halangan untuk pembelian tapak ini diteruskan dengan syarat-syarat tanah tersebut adalah bebas " +
	    		"daripada sebarang bebanan dan semua arahan seperti di dalam Surat Pekeliling Perbendaharaan (SPP) 11/2007 dipatuhi sepenuhnya. " +
	    		"");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nama Perakuan:"+tajuk+"" +
		"<br/>Tarikh Permohonan:"+noFail+"" +
		"<br/>Nama Kementerian:"+dari);
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> ke <a href=\"http://www.myetapp.gov.my\" >www.myetapp.gov.my</a> untuk semakan selanjutnya.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, terima kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		//bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
		
	}
//	email.MESSAGE = "<html><table><tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
//	"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
//	"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
//	"<tr><td>Telah berjaya disahkan</td></tr>" +
//	"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
//	"</table></html>" ;
	@Override
	public String setKandungan(String tajuk, String dari){	
		String kandungan = getSignHeader();
		
		StringBuffer sb = new StringBuffer();
	    //sb.append("Minta Tuan/Puan enyemak/mengesahkan permohonan " +
	    sb.append("Mohon Tuan/Puan Menyemak/Meluluskan Permohonan " +
	    		"<b>"+tajuk+"</b> daripada "+dari+".");

		kandungan += sb.toString();
		kandungan += getSignFooter();
		
		return kandungan;

	}	
	
	@Override
	public String setKandungan(String tajuk, String kementerian,String noRujukan){	
		String kandungan = getSignHeader();
		
		StringBuffer sb = new StringBuffer();
	    sb.append("Mohon Tuan/Puan Menerima/Mengesahkan Permohonan " +
	    		"<b>"+tajuk+"</b> daripada <b>"+kementerian+"</b> dan nombor rujukan adalah seperti berikut <b>"+noRujukan+"</b>.");
 
		kandungan += sb.toString();
		kandungan += getSignFooter();
		
		return kandungan;

	}
	
	public String getSignFooter() {
		return EmailConfig.getFooter();
		
	}
	
	public String getSignHeader() {
		return EmailConfig.getHeader();
		
	}
	
	@Override
	public String seTajuk(String namaSubmodul) {
		if(!namaSubmodul.equals(""))
			tajukDefault += "("+namaSubmodul+")";
			
		return tajukDefault;
		
	}

	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
	
}
