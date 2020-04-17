package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmHakmilikSementaraPerundinganBantahanData {
	
	static Vector paparBantahan = null;
	
	public static void setDataBantahan(String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparBantahan= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT BANTAHAN_TUANTNH,BANTAHAN_AGENSI, BANTAHAN_LAIN FROM TBLPPTSIASATAN WHERE ID_SIASATAN = '"+idSiasatan+"'";
			
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("BANTAHAN_TUANTNH", rs.getString("BANTAHAN_TUANTNH")==null?"":rs.getString("BANTAHAN_TUANTNH"));
		    	  h.put("BANTAHAN_AGENSI", rs.getString("BANTAHAN_AGENSI")==null?"":rs.getString("BANTAHAN_AGENSI"));
		    	  h.put("BANTAHAN_LAIN", rs.getString("BANTAHAN_LAIN")==null?"":rs.getString("BANTAHAN_LAIN"));

		    	  paparBantahan.addElement(h);
		    	  
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}
	public static void updateBantahan(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			String idSiasatan = (String)data.get("idSiasatan");
			String bantahanTuanTanah = (String)data.get("bantahanTuanTanah");
			String bantahanAgensi = (String)data.get("bantahanAgensi");
			String bantahanLain = (String)data.get("bantahanLain");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Siasatan",idSiasatan);
			r.add("bantahan_Tuantnh",bantahanTuanTanah);
			r.add("bantahan_Agensi",bantahanAgensi);
			r.add("bantahan_Lain",bantahanLain);
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	public static Vector getDataBantahan(){
		return paparBantahan;
	}

}
