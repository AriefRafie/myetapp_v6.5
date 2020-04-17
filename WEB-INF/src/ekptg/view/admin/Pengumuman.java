
package ekptg.view.admin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class Pengumuman extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918341154128865392L;
	static Logger myLogger = Logger.getLogger(Pengumuman.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	@Override
	public String doTemplate2() throws Exception {	
		
		HttpSession session = this.request.getSession();
		
		role = (String)session.getAttribute("myrole");
		userId = (String)session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");	
		
		String command = getParam("command");
		String mode = getParam("mode");
		
		Vector list_memo_aktif = null;
		Vector list_memo_xaktif = null;
				
		String bolehsimpan = "";
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		
		String vm = "app/utils/frmDaftarPengumuman.jsp";
		
		
		if(command.equals("simpanMemo"))
		{
			if(bolehsimpan.equals("yes"))
			{
			addMemo(session);
			}
		}
		else if(command.equals("hapusMemo"))
		{
			String id_memo = getParam("id_memo");
			deleteMemo(id_memo);
			defaultMemo();
		}		
		else if(command.equals("paparMemo"))
		{
			String id_memo = getParam("id_memo");
			Hashtable get_memo = null;
			Vector list_memo = null;			
			list_memo = getMemo(id_memo,"","","", "", "", "", "");
			
			    if(list_memo.size()!=0)
			    {
				get_memo = (Hashtable) list_memo.get(0);
				
				
				context.put("id_memo",(String)get_memo.get("id_memo"));
				context.put("mesej",(String)get_memo.get("mesej"));
				context.put("submesej",(String)get_memo.get("submesej"));
				context.put("flag_mesej",(String)get_memo.get("flag_mesej"));
				context.put("tarikh_mula_papar_full",(String)get_memo.get("tarikh_mula_papar_full"));
				context.put("tarikh_mula_papar",(String)get_memo.get("tarikh_mula_papar"));
				context.put("tarikh_mula_papar_hour",(String)get_memo.get("tarikh_mula_papar_hour"));
				context.put("tarikh_mula_papar_minute",(String)get_memo.get("tarikh_mula_papar_minute"));
				context.put("tarikh_mula_papar_ampm",(String)get_memo.get("tarikh_mula_papar_ampm"));
				context.put("tarikh_akhir_papar_full",(String)get_memo.get("tarikh_akhir_papar_full"));
				context.put("tarikh_akhir_papar",(String)get_memo.get("tarikh_akhir_papar"));
				context.put("tarikh_akhir_papar_hour",(String)get_memo.get("tarikh_akhir_papar_hour"));
				context.put("tarikh_akhir_papar_minute",(String)get_memo.get("tarikh_akhir_papar_minute"));
				context.put("tarikh_akhir_papar_ampm",(String)get_memo.get("tarikh_akhir_papar_ampm"));
				context.put("status_mesej",(String)get_memo.get("status_mesej"));
				
				context.put("flag_role_internal",(String)get_memo.get("flag_role_internal"));
				context.put("flag_role_internalHQ",(String)get_memo.get("flag_role_internalHQ"));
				context.put("flag_role_internalNeg",(String)get_memo.get("flag_role_internalNeg"));
				context.put("flag_role_internalKJP",(String)get_memo.get("flag_role_internalKJP"));
				context.put("flag_role_internalInteg",(String)get_memo.get("flag_role_internalInteg"));
				context.put("flag_role_internalOnline",(String)get_memo.get("flag_role_internalOnline"));

				context.put("turutan_mesej",(String)get_memo.get("turutan_mesej"));		
			
			    }	
		}
		else
		{
			defaultMemo();			
		}
		//updatePapar();
		list_memo_aktif = getMemo("","Aktif","","", "", "", "", "");	
		list_memo_xaktif = getMemo("","Tidak Aktif","","", "", "", "", "");		
		context.put("list_memo_aktif",list_memo_aktif);	
		context.put("list_memo_xaktif",list_memo_xaktif);	
			
		return vm;
		
		
	}
	
	private void defaultMemo()throws Exception{
		
		Calendar currentDate = Calendar.getInstance();
		 //SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter_hour = new SimpleDateFormat("hh");
		SimpleDateFormat formatter_minute = new SimpleDateFormat("mm");
		SimpleDateFormat formatter_ampm = new SimpleDateFormat("a");
		
		
		
		String dateNow = formatter_date.format(currentDate.getTime());
		String hourNow = formatter_hour.format(currentDate.getTime());
		String minuteNow = formatter_minute.format(currentDate.getTime());
		String ampmNow = formatter_ampm.format(currentDate.getTime());
		
		myLogger.info("DATE:"+dateNow);
		myLogger.info("HOUR:"+hourNow);
		myLogger.info("MINUTE:"+minuteNow);
		myLogger.info("AMPM:"+ampmNow);
			  
		
		context.put("id_memo","");
		context.put("mesej","");
		context.put("submesej","");
		context.put("flag_mesej","");
		context.put("tarikh_mula_papar",dateNow);
		context.put("tarikh_mula_papar_hour",hourNow);
		context.put("tarikh_mula_papar_minute",minuteNow);
		context.put("tarikh_mula_papar_ampm",ampmNow);
		context.put("tarikh_akhir_papar","");
		context.put("tarikh_akhir_papar_hour","12");
		context.put("tarikh_akhir_papar_minute","00");
		context.put("tarikh_akhir_papar_ampm","AM");
		context.put("status_mesej","Tidak Aktif");

		context.put("flag_role_internal","");
		context.put("flag_role_internalHQ","");
		context.put("flag_role_internalNeg","");
		context.put("flag_role_internalKJP","");
		context.put("flag_role_internalInteg","");
		context.put("flag_role_internalOnline","");

		context.put("turutan_mesej","");		
	}
	
	public void updatePapar() throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " UPDATE TBLMEMO SET STATUS_MESEJ = 'Tidak Aktif' WHERE (TO_CHAR(TARIKH_MULA_PAPAR,'dd/mm/yyyy hh:mi:ss am') > TO_CHAR(SYSDATE,'dd/mm/yyyy hh:mi:ss am')) " 
				+" OR (TO_CHAR(TARIKH_AKHIR_PAPAR,'dd/mm/yyyy hh:mi:ss am') < TO_CHAR(SYSDATE,'dd/mm/yyyy hh:mi:ss am'))";
			myLogger.info("UPDATE STATUS :"+sql);
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void addMemo(HttpSession session) throws Exception {
	 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql="";
		String sql1="";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			CallableStatement stmtx = null;
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			
			long id_memo = 0;
			String id_memo_update = getParam("id_memo");
			String mesej = getParam("mesej");
			String status_mesej = getParam("status_mesej");
			String turutan_mesej = getParam("turutan_mesej");
			String submesej = getParam("submesej");
			String flag_mesej = getParam("flag_mesej");
			
			String tarikh_mula_papar = "''";
			String tarikh_mula_papar_convert = "''";			
			if(!getParam("tarikh_mula_papar").equals("") && !getParam("tarikh_mula_papar_hour").equals("") && !getParam("tarikh_mula_papar_minute").equals("") && !getParam("tarikh_mula_papar_ampm").equals(""))
			{
			tarikh_mula_papar	= getParam("tarikh_mula_papar") + " " + getParam("tarikh_mula_papar_hour") + ":" + getParam("tarikh_mula_papar_minute") + ":00 " + getParam("tarikh_mula_papar_ampm");  					
			tarikh_mula_papar_convert = "to_date('" + tarikh_mula_papar + "','dd/mm/yyyy hh:mi:ss am')";
			}
			
			String tarikh_akhir_papar = "''";
			String tarikh_akhir_papar_convert = "''";			
			if(!getParam("tarikh_akhir_papar").equals("") && !getParam("tarikh_akhir_papar_hour").equals("") && !getParam("tarikh_akhir_papar_minute").equals("") && !getParam("tarikh_akhir_papar_ampm").equals(""))
			{
			tarikh_akhir_papar	= getParam("tarikh_akhir_papar") + " " + getParam("tarikh_akhir_papar_hour") + ":" + getParam("tarikh_akhir_papar_minute") + ":00 " + getParam("tarikh_akhir_papar_ampm");  					
			tarikh_akhir_papar_convert = "to_date('" + tarikh_akhir_papar + "','dd/mm/yyyy hh:mi:ss am')";
			}
			
			String flag_role_internal = getParam("cbinternalInt") != "" ? 
					(String) getParam("cbinternalInt") : "0";

			String flag_role_internalHQ = getParam("cbinternalHQ") != "" ? 
						(String) getParam("cbinternalHQ") : "0";
							
			String flag_role_internalNeg = getParam("cbinternalNeg") != "" ? 
					(String) getParam("cbinternalNeg") : "0";
					
			String flag_role_internalKJP = getParam("cbinternalKJP") != "" ? 
					(String) getParam("cbinternalKJP") : "0";
					
			String flag_role_internalInteg = getParam("cbinternalInteg") != "" ? 
					(String) getParam("cbinternalInteg") : "0";
					
			String flag_role_internalOnline = getParam("cbinternalOnline") != "" ? 
					(String) getParam("cbinternalOnline") : "0";
					
						
			myLogger.info("tarikh_mula_papar_convert :"+tarikh_mula_papar_convert);
			
			
			
			
			

			if(id_memo_update.equals(""))
			{
				id_memo = DB.getNextID(db,"TBLMEMO_SEQ");
            PreparedStatement ps = conn.prepareStatement("insert into TBLMEMO " +
                    "(ID_MEMO,MESEJ,STATUS_MESEJ,TURUTAN_MESEJ,ID_MASUK,ID_KEMASKINI,SUBMESEJ,FLAG_MESEJ," +
            		" FLAG_ROLE_INTERNAL, FLAG_ROLE_HQ, FLAG_ROLE_NEG, FLAG_ROLE_KJP, FLAG_ROLE_INTEG, FLAG_ROLE_ONLINE, " +
                    " TARIKH_MASUK,TARIKH_KEMASKINI) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,sysdate)");
            //FLAG_ROLE_ONLINE,
			        ps.setLong(1, id_memo);
			        ps.setString(2, mesej);
			        ps.setString(3, status_mesej);
			        ps.setString(4, turutan_mesej);
			        ps.setString(5, user_id_login);
			        ps.setString(6, user_id_login);
			        ps.setString(7, submesej);
			        ps.setString(8, flag_mesej);
			        
			        ps.setString(9, flag_role_internal);
			        ps.setString(10, flag_role_internalHQ);
			        ps.setString(11, flag_role_internalNeg);
			        ps.setString(12, flag_role_internalKJP);
			        ps.setString(13, flag_role_internalInteg);
			        ps.setString(14, flag_role_internalOnline);
			        
			        ps.executeUpdate();
			}
			else
			{
				 PreparedStatement ps = conn.prepareStatement(" UPDATE TBLMEMO SET MESEJ = ?, STATUS_MESEJ = ?, TURUTAN_MESEJ = ?, ID_KEMASKINI = ?,SUBMESEJ = ?, FLAG_MESEJ= ?," +
				 												" FLAG_ROLE_INTERNAL =?, FLAG_ROLE_HQ =?, FLAG_ROLE_NEG =?, FLAG_ROLE_KJP =?, FLAG_ROLE_INTEG =?, FLAG_ROLE_ONLINE =?, " +
				 												" TARIKH_KEMASKINI = sysdate WHERE ID_MEMO = ?");
					       //FLAG_ROLE_ONLINE= ?,
					        ps.setString(1, mesej);
					        ps.setString(2, status_mesej);
					        ps.setString(3, turutan_mesej);
					        ps.setString(4, user_id_login);					        
					        ps.setString(5, submesej);
					        ps.setString(6, flag_mesej);  
					        
					        ps.setString(7, flag_role_internal);
					        ps.setString(8, flag_role_internalHQ);
					        ps.setString(9, flag_role_internalNeg);
					        ps.setString(10, flag_role_internalKJP);
					        ps.setString(11, flag_role_internalInteg);
					        ps.setString(12, flag_role_internalOnline);
					        
					        ps.setString(13, id_memo_update);
					        ps.executeUpdate();	
			}
			
			
			/*
			if(id_memo_update.equals(""))
			{
			id_memo = DB.getNextID(db,"TBLMEMO_SEQ");
			r.add("ID_MEMO",id_memo);						
			}
			else
			{
			r.update("ID_MEMO",id_memo_update);				
			}
			
			r.add("MESEJ",mesej);
			r.add("TARIKH_MULA_PAPAR",r.unquote(tarikh_mula_papar_convert));
			r.add("TARIKH_AKHIR_PAPAR",r.unquote(tarikh_akhir_papar_convert));
			r.add("STATUS_MESEJ",status_mesej);
			r.add("TURUTAN_MESEJ",turutan_mesej);
			
			
			if(id_memo_update.equals(""))
			{
			r.add("ID_MASUK",user_id_login);
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));				
			sql = r.getSQLInsert("TBLMEMO");
			}
			else
			{				
			r.add("ID_KEMASKINI",user_id_login);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));				
			sql = r.getSQLUpdate("TBLMEMO");
			}	
			myLogger.info("INSERT UPDATE MEMO :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			*/
			
			
			
			
			/*
			String sql_x = "";
			String sql_d = "";			
			if(id_memo_update.equals(""))
			{
			sql_d = "DELETE FROM XTBLMEMO WHERE IDX = "	+ id_memo;		
			myLogger.info("DELETE XMEMO INSERT :"+sql_d.toUpperCase());
			stmt.executeUpdate(sql_d);			    
			sql_x += " Call P_XTBLMEMO("+id_memo+",'"+mesej+"',0)";	
			//sql_x += " Call P_XTBLMEMO("+id_memo+",rpad('*',32000,'*'),0)";	
			myLogger.info("SIMPAN CLOB :"+sql_x.toUpperCase());			
			}
			else
			{
			sql_d = "DELETE FROM XTBLMEMO WHERE IDX = "	+ id_memo_update;		
			myLogger.info("DELETE XMEMO UPDATE :"+sql_d.toUpperCase());
			stmt.executeUpdate(sql_d);			
			//stmtx = conn.prepareCall("Call {P_XTBLMEMO("+id_memo_update+",'"+mesej+"',0)}");
			sql_x += " Call P_XTBLMEMO("+id_memo_update+",'"+mesej+"',0)";	
			myLogger.info("UPDATE CLOB :"+sql_x.toUpperCase());
			}			
			stmt.execute(sql_x);
			*/
			
			
			conn.commit();
			
			
			Vector<Hashtable<String,String>> list_memo = null;
			Hashtable<String,String> get_memo = null;
			
			if(id_memo_update.equals(""))
			{
			list_memo = getMemo(id_memo+"","","","", "", "", "", "");								
			}
			else
			{
			list_memo = getMemo(id_memo_update+"","","","", "", "", "", "");			
			}			
			    if(list_memo.size()!=0)
			    {
				get_memo = (Hashtable<String,String>) list_memo.get(0);
				
				context.put("id_memo",(String)get_memo.get("id_memo"));
				context.put("mesej",(String)get_memo.get("mesej"));
				context.put("submesej",(String)get_memo.get("submesej"));
				context.put("flag_mesej",(String)get_memo.get("flag_mesej"));
				context.put("tarikh_mula_papar_full",(String)get_memo.get("tarikh_mula_papar_full"));
				context.put("tarikh_mula_papar",(String)get_memo.get("tarikh_mula_papar"));
				context.put("tarikh_mula_papar_hour",(String)get_memo.get("tarikh_mula_papar_hour"));
				context.put("tarikh_mula_papar_minute",(String)get_memo.get("tarikh_mula_papar_minute"));
				context.put("tarikh_mula_papar_ampm",(String)get_memo.get("tarikh_mula_papar_ampm"));
				context.put("tarikh_akhir_papar_full",(String)get_memo.get("tarikh_akhir_papar_full"));
				context.put("tarikh_akhir_papar",(String)get_memo.get("tarikh_akhir_papar"));
				context.put("tarikh_akhir_papar_hour",(String)get_memo.get("tarikh_akhir_papar_hour"));
				context.put("tarikh_akhir_papar_minute",(String)get_memo.get("tarikh_akhir_papar_minute"));
				context.put("tarikh_akhir_papar_ampm",(String)get_memo.get("tarikh_akhir_papar_ampm"));
				context.put("status_mesej",(String)get_memo.get("status_mesej"));

				context.put("flag_role_internal",(String)get_memo.get("flag_role_internal"));
				context.put("flag_role_internalHQ",(String)get_memo.get("flag_role_internalHQ"));
				context.put("flag_role_internalNeg",(String)get_memo.get("flag_role_internalNeg"));
				context.put("flag_role_internalKJP",(String)get_memo.get("flag_role_internalKJP"));
				context.put("flag_role_internalInteg",(String)get_memo.get("flag_role_internalInteg"));
				context.put("flag_role_internalOnline",(String)get_memo.get("flag_role_internalOnline"));

				
				context.put("turutan_mesej",(String)get_memo.get("turutan_mesej"));		
			
		/*		}      
		}
		finally {
			if(db != null)db.close();
			}
	}*/

		    
			    
			    
			    }
    
		} 
		catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
		} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
		}
		
}	
	
	Vector<Hashtable<String,String>> list_memo = null;
	
	public Vector<Hashtable<String,String>> getMemo(String id_memo,String status_mesej, String internalType, String onlineType) throws Exception {
		return getMemo(id_memo,status_mesej, "1", "1", "1", "1","1", "1");
	}
	
    public Vector<Hashtable<String,String>> getMemo(String id_memo,String status_mesej, String internalType, String internalHQType, String internalNegType, String internalKJPType, String internalIntegType, String onlineType) throws Exception {
          //updatePapar();
          list_memo = new Vector<Hashtable<String,String>>();
          list_memo.clear();
          Db db = null;
          String sql = "";
          try {
                db = new Db();
                Statement stmt = db.getStatement();
                
          sql = " SELECT A.ID_MEMO," +
          " A.MESEJ," +
          // "X.TXT AS MESEJ," +
          "A.STATUS_MESEJ," +
          "A.SUBMESEJ," +
          "A.FLAG_MESEJ," +
          "A.FLAG_ROLE_INTERNAL, " +
          "A.FLAG_ROLE_HQ, " +
          "A.FLAG_ROLE_NEG, " +
          "A.FLAG_ROLE_KJP, " +
          "A.FLAG_ROLE_INTEG, " +
          "A.FLAG_ROLE_ONLINE," + 
          " A.TURUTAN_MESEJ," +
          
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'dd/mm/yyyy') AS TARIKH_MULA_PAPAR_DATE," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'hh') AS TARIKH_MULA_PAPAR_HOUR," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'mi') AS TARIKH_MULA_PAPAR_MINUTE," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'am') AS TARIKH_MULA_PAPAR_AMPM," +             
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'dd/mm/yyyy hh:mi am') AS TARIKH_MULA_PAPAR," + 
          
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'dd/mm/yyyy') AS TARIKH_AKHIR_PAPAR_DATE," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'hh') AS TARIKH_AKHIR_PAPAR_HOUR," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'mi') AS TARIKH_AKHIR_PAPAR_MINUTE," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'am') AS TARIKH_AKHIR_PAPAR_AMPM," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'dd/mm/yyyy hh:mi am') AS TARIKH_AKHIR_PAPAR " +      
          
          //" FROM TBLMEMO A,XTBLMEMO X WHERE A.ID_MEMO = X.IDX AND A.ID_MEMO IS NOT NULL ";
          " FROM TBLMEMO A WHERE  A.ID_MEMO IS NOT NULL ";
                
          if(!id_memo.equals("") && !id_memo.equals(null))
          {
                sql += " AND A.ID_MEMO = '"+id_memo+"'";  
          }
         
          
          if(!internalType.equals("") && !internalType.equals(null) && !internalType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_INTERNAL = '"+internalType+"'";
          }
          
          if(!internalHQType.equals("") && !internalHQType.equals(null) && !internalHQType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_HQ = '"+internalHQType+"'";
          }
          
          if(!internalNegType.equals("") && !internalNegType.equals(null) && !internalNegType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_NEG = '"+internalNegType+"'";
          }
          
          if(!internalKJPType.equals("") && !internalKJPType.equals(null) && !internalKJPType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_KJP = '"+internalKJPType+"'";
          }
          
          if(!internalIntegType.equals("") && !internalIntegType.equals(null) && !internalIntegType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_INTEG = '"+internalIntegType+"'";
          }
          
          if(!status_mesej.equals("") && !status_mesej.equals(null))
          {
                sql += " AND A.STATUS_MESEJ= '"+status_mesej+"'";     
          }
                sql +=  " ORDER BY A.TURUTAN_MESEJ ASC";              
                myLogger.info("LIST MEMO :"+sql);
                int bil = 0;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                      bil = bil + 1;
                      Hashtable<String,String> h = new Hashtable<String,String>();
                      h.put("BIL", String.valueOf(bil));
                      h.put("id_memo", rs.getString("ID_MEMO"));                  
                      
                      if (rs.getString("MESEJ") == null) {
                            h.put("mesej", "");
                      } else {
                            h.put("mesej", rs.getString("MESEJ"));
                      }
                      
                      if (rs.getString("SUBMESEJ") == null) {
                            h.put("submesej", "");
                      } else {
                            h.put("submesej", rs.getString("submesej"));
                      }
                      
                      if (rs.getString("FLAG_MESEJ") == null) {
                            h.put("flag_mesej", "");
                      } else {
                            h.put("flag_mesej", rs.getString("flag_mesej"));
                      }
                      
                      if (rs.getString("TURUTAN_MESEJ") == null) {
                            h.put("turutan_mesej", "");
                      } else {
                            h.put("turutan_mesej", rs.getString("TURUTAN_MESEJ"));
                      }
                      
                      if (rs.getString("STATUS_MESEJ") == null) {
                            h.put("status_mesej", "");
                      } else {
                            h.put("status_mesej", rs.getString("STATUS_MESEJ"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_INTERNAL") == null) {
                          h.put("flag_role_internal", "");
                      } else {
                          h.put("flag_role_internal", rs.getString("FLAG_ROLE_INTERNAL"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_HQ") == null) {
                          h.put("flag_role_internalHQ", "");
                      } else {
                          h.put("flag_role_internalHQ", rs.getString("FLAG_ROLE_HQ"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_NEG") == null) {
                          h.put("flag_role_internalNeg", "");
                      } else {
                          h.put("flag_role_internalNeg", rs.getString("FLAG_ROLE_NEG"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_KJP") == null) {
                          h.put("flag_role_internalKJP", "");
                      } else {
                          h.put("flag_role_internalKJP", rs.getString("FLAG_ROLE_KJP"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_INTEG") == null) {
                          h.put("flag_role_internalInteg", "");
                      } else {
                          h.put("flag_role_internalInteg", rs.getString("FLAG_ROLE_INTEG"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_ONLINE") == null) {
                          h.put("flag_role_internalOnline", "");
                      } else {
                          h.put("flag_role_internalOnline", rs.getString("FLAG_ROLE_ONLINE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR") == null) {
                            h.put("tarikh_mula_papar_full", "");
                      } else {
                            h.put("tarikh_mula_papar_full", rs.getString("TARIKH_MULA_PAPAR"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_DATE") == null) {
                            h.put("tarikh_mula_papar", "");
                      } else {
                            h.put("tarikh_mula_papar", rs.getString("TARIKH_MULA_PAPAR_DATE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_HOUR") == null) {
                            h.put("tarikh_mula_papar_hour", "");
                      } else {
                            h.put("tarikh_mula_papar_hour", rs.getString("TARIKH_MULA_PAPAR_HOUR"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_MINUTE") == null) {
                            h.put("tarikh_mula_papar_minute", "");
                      } else {
                            h.put("tarikh_mula_papar_minute", rs.getString("TARIKH_MULA_PAPAR_MINUTE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_AMPM") == null) {
                            h.put("tarikh_mula_papar_ampm", "");
                      } else {
                            h.put("tarikh_mula_papar_ampm", rs.getString("TARIKH_MULA_PAPAR_AMPM").toUpperCase());
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR") == null) {
                            h.put("tarikh_akhir_papar_full", "");
                      } else {
                            h.put("tarikh_akhir_papar_full", rs.getString("TARIKH_AKHIR_PAPAR"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_DATE") == null) {
                            h.put("tarikh_akhir_papar", "");
                      } else {
                            h.put("tarikh_akhir_papar", rs.getString("TARIKH_AKHIR_PAPAR_DATE"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_HOUR") == null) {
                            h.put("tarikh_akhir_papar_hour", "");
                      } else {
                            h.put("tarikh_akhir_papar_hour", rs.getString("TARIKH_AKHIR_PAPAR_HOUR"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_MINUTE") == null) {
                            h.put("tarikh_akhir_papar_minute", "");
                      } else {
                            h.put("tarikh_akhir_papar_minute", rs.getString("TARIKH_AKHIR_PAPAR_MINUTE"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_AMPM") == null) {
                            h.put("tarikh_akhir_papar_ampm", "");
                      } else {
                            h.put("tarikh_akhir_papar_ampm", rs.getString("TARIKH_AKHIR_PAPAR_AMPM").toUpperCase());
                      }
                      list_memo.addElement(h);
                }
                return list_memo;
          } finally {
                if (db != null)
                      db.close();
          }
    }

	
	
	
	public void deleteMemo(String id_memo) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLMEMO WHERE ID_MEMO = "
					+ id_memo;
			
			myLogger.info("DELETE MEMO :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	

}






