package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmHakmilikSementaraPerundinganTuntutanData {
	
	static Vector paparTuntutan = null;
	
	public static void setDataTuntutan(String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparTuntutan= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT TUNTUTAN_TUANTNH,TUNTUTAN_PB_BEBANAN, TUNTUTAN_PB_TDKDAFTAR, TUNTUTAN_PB_LAIN FROM TBLPPTSIASATAN WHERE ID_SIASATAN = '"+idSiasatan+"'";
			
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("TUNTUTAN_TUANTNH", rs.getString("TUNTUTAN_TUANTNH")==null?"":rs.getString("TUNTUTAN_TUANTNH"));
		    	  h.put("TUNTUTAN_PB_BEBANAN", rs.getString("TUNTUTAN_PB_BEBANAN")==null?"":rs.getString("TUNTUTAN_PB_BEBANAN"));
		    	  h.put("TUNTUTAN_PB_TDKDAFTAR", rs.getString("TUNTUTAN_PB_TDKDAFTAR")==null?"":rs.getString("TUNTUTAN_PB_TDKDAFTAR"));
		    	  h.put("TUNTUTAN_PB_LAIN", rs.getString("TUNTUTAN_PB_LAIN")==null?"":rs.getString("TUNTUTAN_PB_LAIN"));

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
			String tuanTanah = (String)data.get("tuntutanTuanTanah");
			String pbDaftar = (String)data.get("tuntutanPBDaftar");
			String pbTdkDaftar = (String)data.get("tuntutanPBTdkDaftar");
			String lain = (String)data.get("tuntutanLain");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Siasatan",idSiasatan);
			r.add("tuntutan_Tuantnh",tuanTanah);
			r.add("tuntutan_Pb_Bebanan",pbDaftar);
			r.add("tuntutan_Pb_Tdkdaftar",pbTdkDaftar);
			r.add("tuntutan_Pb_Lain",lain);
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	public static Vector getDataTuntutan(){
		return paparTuntutan;
	}

}
