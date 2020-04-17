package ekptg.model.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class OtData {
	
	static Logger myLogger = Logger.getLogger(SQLRenderer.class);
	
	public static Hashtable checkTransaksi(String NAMA_TABLE, Db db) throws Exception {
		
		//myLogger.info(" DB checkTransaksi :::::::::: "+db);
		Db db1 = null;
		String sql1 = "";
		ResultSet rs1 = null;
		Statement stmt1 = null;
		
		try
		//(Connection con = db1.getConnection()) 
		{
			if(db==null)
			{			
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt1 = db1.getStatement();
			
			//Connection con = db1.getConnection();
			//sql1 = " SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, T.FIELD_CHECK FROM TBLTRANSAKSI T WHERE T.NAMA_TABLE = ? ";
			//myLogger.info(" checkTransaksi :" + sql1.toUpperCase());
			//PreparedStatement ps = con.prepareStatement("SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, T.FIELD_CHECK FROM TBLTRANSAKSI T WHERE T.NAMA_TABLE = ? ");
			//ps.setString(1,NAMA_TABLE.toUpperCase());
			//myLogger.info("--------------- PS checkTransaksi :" + rs1.getRow());
			//rs1 = ps.executeQuery();
			
			sql1 = " SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, T.FIELD_CHECK FROM TBLTRANSAKSI T WHERE T.NAMA_TABLE = '"+NAMA_TABLE.toUpperCase()+"' ";
			rs1 = stmt1.executeQuery(sql1);
			
			//rs1 = stmt1.executeQuery(sql1);
			//myLogger.info(" NAMA_TABLE :"+NAMA_TABLE+" --------------- rs checkTransaksi :" + rs1.getRow());
			Hashtable h;
			h = new Hashtable();		
			while (rs1.next()) {		
				//myLogger.info("--------------- MASUK TRANS :" + rs1.getString("ID_TRANSAKSI"));
				h.put("ID_TRANSAKSI",rs1.getString("ID_TRANSAKSI") == null ? "" : rs1.getString("ID_TRANSAKSI"));
				h.put("NAMA_AKTIVITI",rs1.getString("NAMA_AKTIVITI") == null ? "" : rs1.getString("NAMA_AKTIVITI"));
				h.put("NAMA_TABLE",rs1.getString("NAMA_TABLE") == null ? "" : rs1.getString("NAMA_TABLE"));
				h.put("FIELD_CHECK",rs1.getString("FIELD_CHECK") == null ? "" : rs1.getString("FIELD_CHECK"));
			}
			//myLogger.info("--------------- h :" + h + " DB checkTransaksi bawah :::::::::: "+db);
			//myLogger.info(" DB checkTransaksi :::::::::: "+db);
			return h;
		} finally {
			/*
			if (rs1 != null)
				rs1.close();
			if (stmt1 != null)
				stmt1.close();
			*/
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
			
			//myLogger.info(" last DB :::::::::: "+db);
		}
	}
	
	
	 public static String saveLogTransaksi(String ID_TRANSAKSI,String VALUE_FIELD_CHECK,String TYPE,String ID_MASUK,Db db1) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			//HttpSession session = request.getSession();
			//String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");				
			try {
				if(db1==null)
				{
					db = new Db();
				}
				else
				{
					db= db1;
				}
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				long ID_LOGTRANSAKSI = DB.getNextID_DB("TBLLOGTRANSAKSI_SEQ",db);	 
				r.add("ID_LOGTRANSAKSI", ID_LOGTRANSAKSI);
				r.add("ID_TRANSAKSI", ID_TRANSAKSI);
				r.add("VALUE_FIELD_CHECK", VALUE_FIELD_CHECK);
				r.add("TRANSAKSI_TYPE", TYPE);				
				r.add("WAKTU_LOG", r.unquote("sysdate"));				
				r.add("ID_MASUK", ID_MASUK);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));				
				sql = r.getSQLInsert("TBLLOGTRANSAKSI");		
				myLogger.info("V3 : INSERT TBLLOGTRANSAKSI : "+sql);					
				stmt.executeUpdate(sql);
				conn.commit();
				//AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANUNDANG  [ID : "+ID_PANDANGANUNDANG+"] Inserted");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if(db1==null)
				{
				if (db != null)
					db.close();
				}
			}
			return returnUSERID;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
