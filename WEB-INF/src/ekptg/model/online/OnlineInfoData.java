package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class OnlineInfoData {
	
	private static Vector list = new Vector();
	
	public static String getStatus(int status) throws Exception {
		 String statuSemasa = "";
		 try {
				if(status == 1) {
					statuSemasa =mesejStatus("Menunggu Semakan");
				}else if(status == 2) {
					statuSemasa =mesejStatus("Menunggu Pengesahan");    				
				}else if(status == 4){
					statuSemasa =mesejStatus("Permohonan Dikembalikan");
				}else if(status == 0){	//PPT, flag_status_online=1 (ditukar kepada 0
					statuSemasa =mesejStatus("Permohonan Ditolak");
				}
		      
		 }catch (Exception e) {
//		      if (db != null) db.close();
		 }  
		 return statuSemasa;
				 
	}
	
	public static String mesejStatus(String keterangan){	 
		return "<label style=\"background-color:blue\" align=\"center\" valign=\"top\" > "+
					" <b><font color=\"WHITE\" size=\"0.6em\"><span class=\"blink\">"+keterangan+"</span></font></b>"+
					"</label> ";
	}
	 

}
