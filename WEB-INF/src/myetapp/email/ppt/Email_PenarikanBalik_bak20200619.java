package myetapp.email.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.intergration.XEkptgEmailSender;

public class Email_PenarikanBalik_bak20200619 {
	static Logger myLogger = Logger.getLogger(Email_PenarikanBalik_bak20200619.class);
	
	public static void main(String args []){		
	}
	
	public static void setEmail_MMK_Penarikan(String emailTO, String emailType, 
			String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian){		
		
        String subject_emel = "testing";
		
		if(emailType.equals("mmk_penarikanbalik")){
		//subject (untuk testing)
		subject_emel = "Pengujian emel notification untuk modul Pengambilan Tanah " +
				"(Mohon membuat semakan Kertas MMK untuk suburusan Penarikan Balik) ";
		}
		else if(emailType.equals("online_hantar_penarikan_balik")){
			
		}
		else if(emailType.equals("online_terima_penarikan_balik")){
			
		}
        else if(emailType.equals("online_hantar_pembatalan")){
			
		}
        else if(emailType.equals("online_terima_pembatalan")){
			
		}
		
		myLogger.info("BERJAYA SEND");
		
		//default sender email
		String email_from = "etapp_webmaster@ekptg.gov.my";
			
		XEkptgEmailSender email = XEkptgEmailSender.getInstance();
		email.FROM = email_from;
		email.SUBJECT = subject_emel;
		email.MESSAGE = setMessageTable_Penarikan(emailType,nama_projek)
		+""+setParaGraph_Penarikan(emailType,nofail,tarikh_permohonan,nama_kementerian)+"";	
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = "razman@hla-group.com";

		//untuk testing.
		email.TO_CC = new String[2];
		email.TO_CC[0]  = "razman@hla-group.com";
		email.TO_CC[1]  = "m.syazreen@hla-group.com";
		//email.TO_CC[2]  = "adminsupport@hla-group.com";
		//email.TO_CC[3]  = "nik_rafizal@hla-group.com";
		
		
		
		
		myLogger.info("BERJAYA SEND ALL");
		
		email.sendEmail();

		
	}
	
	//get detail user
	private static Vector namaPengarah = null;
	
	public static Vector getNamaPengarah() {
		return namaPengarah;
	}
	
	@SuppressWarnings("unchecked")
	public static void setNamaPengarah(String userIdNeg) throws Exception {
		
		namaPengarah = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  "SELECT DISTINCT A.USER_ID, A.USER_NAME ";
				sql += " FROM USERS A, USERS_INTERNAL B ";
				sql += " WHERE A.USER_ID = B.USER_ID ";
				sql += " AND NVL(B.ID_JAWATAN,0) = '4' ";
				sql += " AND B.ID_NEGERI = '"+userIdNeg+"'";
				
				
				myLogger.info("RET ID PENGARAH"+sql.toUpperCase());
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
					h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
					namaPengarah.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}//close setNamaPengarah
	

	
	public static String setMessageTable_Penarikan(String emailType,String nama_projek){
		StringBuffer bff = new StringBuffer();
		bff.append("<table border='0'>");
		bff.append("<tr>");
		bff.append("<td>NAMA PROJEK : <b>"+nama_projek.toUpperCase()+"</b></td>");
		bff.append("<td></td>");
		bff.append("</tr>");
		bff.append("</table>");		
		return bff.toString();
	}
	

	public static String setParaGraph_Penarikan(String emailType,String nofail,String tarikh_permohonan,String nama_kementerian){
		
		StringBuffer bff = new StringBuffer();
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");
		
		
		
		    if(emailType.equals("mmk_penarikanbalik")){
			//subject (untuk testing)		    	
		    bff.append("Minta Tuan/Puan  menyemak dan mengesahkan Kertas Kerja MMK bagi permohonan Penarikan Balik Tanah daripada "+nama_kementerian+" - "+nofail+" ");
			
			}
			else if(emailType.equals("online_hantar_penarikan_balik")){
				
			}
			else if(emailType.equals("online_terima_penarikan_balik")){
				
			}
	        else if(emailType.equals("online_hantar_pembatalan")){
				
			}
	        else if(emailType.equals("online_terima_pembatalan")){
				
			}
		
		
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan serta pengesahan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian,Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
	}
	
	@SuppressWarnings("unchecked")
	Vector checkEmail = null;
	
	@SuppressWarnings("unchecked")
	public Vector checkEmail(String userId) throws Exception {
		
		checkEmail = new Vector();
		checkEmail.clear();
		
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
	
			myLogger.info("EMEL PENGARAH :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
				checkEmail.addElement(h);
			}
			return checkEmail;
		} finally {
			if (db != null)
				db.close();
		}
	}
	

}
