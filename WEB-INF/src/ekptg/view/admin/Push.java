package ekptg.view.admin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class Push {
	static Logger myLogger = Logger.getLogger(Push.class);

	public static void main(String[] args) throws Exception {
		
		sendPush(
				"eufjj-2sqIw:APA91bGVULYOZbJ2ZmhmDJ5WRU2Tph09lYC7jek7dbuMqt8JqjMgPMCvaksACb_NBDdkOgiktd-jlU9AsqYDDUoPLDiatcRWkV1gvL2glUHj8ww5FS14i_8U4yEaNEpo1NdEkwrRaL5_",
				"hye kamal", "");
				
		//genMsgPush("", "daftar");
	}

	public static void genMsgPush(String id_fail, String type) throws Exception {
		
		if(type.equals("daftar"))
		{
			List listPushPendaftaran = listPushPendaftaran(id_fail);
			for(int i = 0; i < listPushPendaftaran.size();i++)
			   {			   
				   Map m = (Map) listPushPendaftaran.get(i);
				   String msg = "";
				   String NO_FAIL = (String) m.get("NO_FAIL");
				   String TARIKH_DAFTAR = (String) m.get("TARIKH_DAFTAR");
				   String STATUS = (String) m.get("STATUS");
				   String MOBILEAPP_TOKEN = (String) m.get("MOBILEAPP_TOKEN");
				   //msg += "PERMOHONAN PUSAKA KECIL "+NO_FAIL+" DIDAFTARKAN PADA "+TARIKH_DAFTAR+". STATUS SEMASA FAIL ADALAH "+STATUS+". ";
				   msg += "PERMOHONAN PUSAKA KECIL "+NO_FAIL+" DIDAFTARKAN PADA "+TARIKH_DAFTAR+". ";
				   if(!MOBILEAPP_TOKEN.equals(""))
				   {
					   sendPush(MOBILEAPP_TOKEN,msg,"");
				   }			     
			   }
		}
		else if(type.equals("bicara"))
		{
			List listPushPerbicaraan = listPushPerbicaraan(id_fail);
			for(int i = 0; i < listPushPerbicaraan.size();i++)
			   {			   
				   Map m = (Map) listPushPerbicaraan.get(i);
				   String msg = "";
				   String NO_FAIL = (String) m.get("NO_FAIL");
				   String TARIKH_BICARA = (String) m.get("TARIKH_BICARA");
				   String MASA_BICARA = (String) m.get("MASA_BICARA");
				   String TARIKH_NOTIS = (String) m.get("TARIKH_NOTIS");
				   String TEMPAT_BICARA = (String) m.get("TEMPAT_BICARA");
				   String MOBILEAPP_TOKEN = (String) m.get("MOBILEAPP_TOKEN");
				   //msg += "PERBICARAAN PUSAKA KECIL UNTUK FAIL "+NO_FAIL+" AKAN DIADAKAN PADA WAKTU "+MASA_BICARA+" BERTARIKH "+TARIKH_BICARA+"' DI "+TEMPAT_BICARA+". TARIKH NOTIS ADALAH PADA "+TARIKH_NOTIS+". ";
				   msg += "PERBICARAAN PUSAKA KECIL UNTUK FAIL "+NO_FAIL+" AKAN DIADAKAN PADA WAKTU "+MASA_BICARA+" BERTARIKH "+TARIKH_BICARA+"' DI "+TEMPAT_BICARA+". NOTIS AKAN DIHANTAR. ";
					 
				   if(!MOBILEAPP_TOKEN.equals(""))
				   {
					   sendPush(MOBILEAPP_TOKEN,msg,"");
				   }			     
			   }
		}
		else if(type.equals("perintah"))
		{
			List listPushPerintah = listPushPerintah(id_fail);
			for(int i = 0; i < listPushPerintah.size();i++)
			   {			   
				   Map m = (Map) listPushPerintah.get(i);
				   String msg = "";
				   String NO_FAIL = (String) m.get("NO_FAIL");
				   String TARIKH_SELESAI = (String) m.get("TARIKH_SELESAI");
				   String STATUS = (String) m.get("STATUS");
				   String MOBILEAPP_TOKEN = (String) m.get("MOBILEAPP_TOKEN");
				   //msg += "PERMOHONAN PUSAKA KECIL '"+NO_FAIL+"' TELAH SELESAI PADA '"+TARIKH_SELESAI+"'. STATUS SEMASA FAIL ADALAH '"+STATUS+"'. ";
				   
				   msg += "PERMOHONAN PUSAKA KECIL '"+NO_FAIL+"' TELAH SELESAI PADA '"+TARIKH_SELESAI+"'. ";
				   
				   if(!MOBILEAPP_TOKEN.equals(""))
				   {
					   sendPush(MOBILEAPP_TOKEN,msg,"");
				   }			     
			   }
		}
		
	}	
	

	public static void sendPush(String userToken, String mesej, String page)
			throws Exception {
		{
			String url = "https://fcm.googleapis.com/fcm/send";
			String API_ACCESS_KEY = "AAAAl0mKKeI:APA91bHc_P2BVgK-rAa75suZPngQB_Uv0tKkilF6gytzzi6ZUDg1iSGNNAuhL-qdonS96BWjpsWtIQ4ScReyKaTGFmP5Oc5SDWNyAyL_CAWjM6oSB-MrYyPw_zP4GRLNjzNHjk4fACXP";
			String registrationIds = userToken;
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "key=" + API_ACCESS_KEY);
			con.setRequestProperty("Content-Type", "application/json");
			String urlParameters = "{\"data\":{\"message\":\"" + mesej
					+ "\",\"page\":\"" + page + "\"},\"registration_ids\":[\""
					+ registrationIds + "\"]}";
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("result : " + response.toString());
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List listPushPendaftaran(String id_fail)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			
		    sql = " SELECT DISTINCT U.MOBILEAPP_TOKEN, UO.USER_ID,OBP.NAMA_OB, F.NO_FAIL, OBP.NO_KP_BARU, " +
		    		" TO_CHAR(F.TARIKH_DAFTAR_FAIL, 'DD/MM/YYYY') AS TARIKH_DAFTAR, " +
		    		" UPPER(S.KETERANGAN) AS STATUS "+
		    		" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM,  "+
		    		" TBLPPKPERMOHONANSIMATI PSM, TBLPPKOBPERMOHONAN OBP, TBLRUJSTATUS S, USERS_ONLINE UO, USERS U "+
		    		" WHERE F.ID_FAIL = P.ID_FAIL AND UO.USER_ID = U.USER_ID "+
		    		" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN  "+
		    		" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
		    		" AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI "+
		    		" AND P.ID_STATUS = S.ID_STATUS "+
		    		" AND OBP.NO_KP_BARU = UO.NO_KP_BARU "+
		    		" AND P.ID_STATUS = 8 AND F.ID_FAIL = '"+id_fail+"' "+
		    		//" AND ROWNUM < 100 " +
		    		"";				
			
			myLogger.info(" listPushPendaftaran :"+ sql);			
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("MOBILEAPP_TOKEN",rs.getString("MOBILEAPP_TOKEN") == null ? "" : rs.getString("MOBILEAPP_TOKEN"));
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
				h.put("NAMA_OB",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				list.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return list;

	}
	
	
	@SuppressWarnings("unchecked")
	public static List listPushPerbicaraan(String id_fail)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			
		    sql = " SELECT DISTINCT U.MOBILEAPP_TOKEN,UO.USER_ID,OBP.NAMA_OB, F.NO_FAIL, OBP.NO_KP_BARU, TO_CHAR(PB.TARIKH_BICARA, 'DD/MM/YYYY') AS TARIKH_BICARA, "+
		    		" (PB.MASA_BICARA || (CASE WHEN JENIS_MASA_BICARA = 1 THEN ' PAGI'  WHEN JENIS_MASA_BICARA = 2 THEN ' PETANG' ELSE '' END)) AS MASA_BICARA ,  "+
		    		" TO_CHAR(PB.TARIKH_NOTIS, 'DD/MM/YYYY') AS TARIKH_NOTIS, UPPER(PB.TEMPAT_BICARA) AS TEMPAT_BICARA "+
		    		" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM,  "+
		    		" TBLPPKPERMOHONANSIMATI PSM, TBLPPKOBPERMOHONAN OBP, USERS_ONLINE UO, USERS U,  "+
		    		" TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERBICARAAN PB "+
		    		" WHERE F.ID_FAIL = P.ID_FAIL  "+
		    		" AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN "+
		    		" AND KP.ID_KEPUTUSANPERMOHONAN = PB.ID_KEPUTUSANPERMOHONAN "+
		    		" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN  "+
		    		" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
		    		" AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI "+
		    		" AND OBP.NO_KP_BARU = UO.NO_KP_BARU AND UO.USER_ID = U.USER_ID  "+
		    		" AND P.ID_STATUS = 18 AND F.ID_FAIL = '"+id_fail+"' "+
		    		//" AND ROWNUM < 100 " +
		    		"";				
			
			myLogger.info(" listPushPendaftaran :"+ sql);			
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("MOBILEAPP_TOKEN",rs.getString("MOBILEAPP_TOKEN") == null ? "" : rs.getString("MOBILEAPP_TOKEN"));
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
				h.put("NAMA_OB",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("TARIKH_BICARA",rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
				h.put("MASA_BICARA",rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA"));
				h.put("TARIKH_NOTIS",rs.getString("TARIKH_NOTIS") == null ? "" : rs.getString("TARIKH_NOTIS"));
				h.put("TEMPAT_BICARA",rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA"));
				list.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return list;

	}
	
	
	@SuppressWarnings("unchecked")
	public static List listPushPerintah(String id_fail)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			
		    sql = " SELECT U.MOBILEAPP_TOKEN,UO.USER_ID,OBP.NAMA_OB, F.NO_FAIL, OBP.NO_KP_BARU, " +
		    		" TO_CHAR(STF.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_SELESAI, UPPER(S.KETERANGAN) AS STATUS "+
		    		" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKSIMATI SM,  "+
		    		" TBLPPKPERMOHONANSIMATI PSM, TBLPPKOBPERMOHONAN OBP, TBLRUJSTATUS S, USERS_ONLINE UO, USERS U, "+
		    		" TBLRUJSUBURUSANSTATUSFAIL STF "+
		    		" WHERE F.ID_FAIL = P.ID_FAIL  "+
		    		" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN  "+
		    		" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
		    		" AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI "+
		    		" AND P.ID_STATUS = S.ID_STATUS "+
		    		" AND OBP.NO_KP_BARU = UO.NO_KP_BARU AND UO.USER_ID = U.USER_ID "+
		    		" AND P.ID_PERMOHONAN = STF.ID_PERMOHONAN "+
		    		" AND STF.ID_SUBURUSANSTATUS IN (355, 358) "+
		    		" AND P.ID_STATUS = 21 AND F.ID_FAIL = '"+id_fail+"' "+
		    		" AND STF.AKTIF = '1' "+
		    		//" AND ROWNUM < 100 " +
		    		" ";				
			
			myLogger.info(" listPushPendaftaran :"+ sql);			
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("MOBILEAPP_TOKEN",rs.getString("MOBILEAPP_TOKEN") == null ? "" : rs.getString("MOBILEAPP_TOKEN"));
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
				h.put("NAMA_OB",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
				h.put("TARIKH_SELESAI",rs.getString("TARIKH_SELESAI") == null ? "" : rs.getString("TARIKH_SELESAI"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				list.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return list;

	}

}