package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraMaklumatPampasanPBTuntutanData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraMaklumatPampasanPBTuntutanData.class);
	static Vector paparTuntutan = null;
	
public static void setDataTuntutan(String idHakmilik)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparTuntutan= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT ID_SIASATAN,TUNTUTAN_TUANTNH,TUNTUTAN_PB_BEBANAN, TUNTUTAN_PB_TDKDAFTAR, TUNTUTAN_PB_LAIN,STATUS_TUNTUTAN FROM TBLPPTSIASATAN WHERE ID_HAKMILIK= '"+idHakmilik+"'";
			myLogger.info("SQL PAMPASAN SEMENTARA :: "+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_SIASATAN", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
		    	  h.put("TUNTUTAN_TUANTNH", rs.getString("TUNTUTAN_TUANTNH")==null?"":rs.getString("TUNTUTAN_TUANTNH"));
		    	  h.put("TUNTUTAN_PB_BEBANAN", rs.getString("TUNTUTAN_PB_BEBANAN")==null?"":rs.getString("TUNTUTAN_PB_BEBANAN"));
		    	  h.put("TUNTUTAN_PB_TDKDAFTAR", rs.getString("TUNTUTAN_PB_TDKDAFTAR")==null?"":rs.getString("TUNTUTAN_PB_TDKDAFTAR"));
		    	  h.put("TUNTUTAN_PB_LAIN", rs.getString("TUNTUTAN_PB_LAIN")==null?"":rs.getString("TUNTUTAN_PB_LAIN"));
		    	  h.put("STATUS_TUNTUTAN", rs.getString("STATUS_TUNTUTAN")==null?"":rs.getString("STATUS_TUNTUTAN"));

		    	  paparTuntutan.addElement(h);
		    	  
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}
	public static void updateTuntutan(Hashtable data)throws Exception{
	
	Db db = null;
	String sql = "";
	
	try{
		
		String idSiasatan = (String)data.get("idSiasatan");
		String statusTuntutan = (String)data.get("statusTuntutan");
		String idKemaskini = (String)data.get("idKemaskini");
		String id_permohonan = (String)data.get("id_permohonan");
		
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		
		r.update("id_Siasatan",idSiasatan);
		r.add("status_Tuntutan",statusTuntutan);
		r.add("id_Kemaskini",idKemaskini);
		r.add("tarikh_Kemaskini",r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblpptsiasatan");
		stmt.executeUpdate(sql);
		
		Statement stmtQ = db.getStatement();
		SQLRenderer rQ = new SQLRenderer();
		rQ.update("ID_PERMOHONAN", id_permohonan);
		rQ.add("ID_STATUS", "68");
		sql = rQ.getSQLUpdate("TBLPPTPERMOHONAN");	
	    stmtQ.executeUpdate(sql);
		
	}
	finally {
		if(db != null) db.close();
	}
	
}
	public static Vector getDataTuntutan(){
		return paparTuntutan;
	}
}
