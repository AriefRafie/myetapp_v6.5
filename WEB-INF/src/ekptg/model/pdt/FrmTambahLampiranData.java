package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmTambahLampiranData {
private static Vector paparDokumen= new Vector();
static Logger myLog = Logger.getLogger(FrmKemaskiniLainDokumenData.class);
	public void setDataDokumen(String id)throws Exception {
		
		 Db db = null;
		 paparDokumen.clear();
		 String sql = "";	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.no_Dokumen");
		      r.add("a.tajuk_Dokumen");
		      //r.add("b.keterangan");//remove due cannot upload attachment
		      //r.add("a.id_Jenisdokumen",r.unquote("b.id_Jenisdokumen"));//remove due cannot upload attachment		    
		      r.add("a.id_Dokumen",id);	     	    
		      sql = r.getSQLSelect("Tblpdtdokumen a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("keterangan",rs.getString("tajuk_dokumen"));
		    	  h.put("no_Rujukan_Dokumen",Utils.isNull(rs.getString("no_Dokumen")));
		    	  paparDokumen.addElement(h); 
		    	  count++;
		      }
		      if (count == 0){			    	 
		    	  h.put("id_Dokumen",0);
		    	  h.put("keterangan","");
		    	  h.put("no_Rujukan_Dokumen","");
		    	
		      }
		      
		 }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
			 if (db != null) db.close();
		 }  
		 
	}
	
	public Vector getDataDokumen(){
		 
		  return paparDokumen;
	}

	
}
