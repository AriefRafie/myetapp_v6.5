package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;



public class FrmPaparLampiranData {
	
	private static Vector paparDokumen= null;
	private static Vector listLampiran= null;

	public static void setDataDokumen(String id)throws Exception {
		
		 Db db = null;
		
		 String sql = "";
		 
		 try {
			  paparDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.jenis_Dokumen");
		      r.add("a.no_Rujukan_Dokumen");
		      
		      
		    
		      r.add("a.id_Dokumen",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfddokumen a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  if (rs.getString("jenis_Dokumen").equals("1")){
			    	  h.put("jenis_Dokumen", "SURAT");
 
		    	  }
		    	  else if (rs.getString("jenis_Dokumen").equals("2")){
			    	  h.put("jenis_Dokumen", "MEMO");
 
		    	  }
		    	  else if (rs.getString("jenis_Dokumen").equals("3")){
			    	  h.put("jenis_Dokumen", "LAPORAN");
 
		    	  }
		    	  else if (rs.getString("jenis_Dokumen").equals("4")){
			    	  h.put("jenis_Dokumen", "MINIT MESYUARAT");
 
		    	  }
		    	  h.put("no_Rujukan_Dokumen",rs.getString("no_Rujukan_Dokumen"));

		    	  paparDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("id_Dokumen",0);
		    	  h.put("keterangan","");
		    	  h.put("no_Rujukan_Dokumen","");
		    	  
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 }
	public static Vector getDataDokumen(){
		 
		  return paparDokumen;
	}

	 public static void  setListLampiran(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    try {
		    	listLampiran = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Lampiran");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		     
		      
		      r.add("a.id_Dokumen",id);

		      sql = r.getSQLSelect("Tblpfdrujlampiran a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      

		      
		      
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_Lampiran", rs.getString("id_Lampiran"));
		    	  h.put("nama_Fail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("id_Lampiran", "");
		    	  h.put("nama_Fail","Tiada rekod.");
		    	  h.put("jenis_Mime", "");
		    	  listLampiran.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListLampiran(){
			 
		return listLampiran;
	}
	
	
	private static Vector getlampiran = null;
	 public static Vector getLampiran(String idDokumen) throws Exception {
			
		 getlampiran = new Vector();
		 	    Db db = null;
		 	    String sql = " ";
		 	   
		 	    try {
		 	      db = new Db();
		 	      Statement stmt = db.getStatement();
		 	      SQLRenderer r = new SQLRenderer();
		 	  

		 	      sql = "SELECT id_Dokumen,nama_fail from TBLPFDRUJLAMPIRAN WHERE ID_DOKUMEN = '"+idDokumen+"'";
		 	      
		 			ResultSet rs = stmt.executeQuery(sql);			

		 			Hashtable s;
		 			while (rs.next()) {
		 				s = new Hashtable();
		 				s.put("id_Dokumen", rs.getString("id_Dokumen") == null ? ""
								: rs.getString("id_Dokumen"));
		 				s.put("nama_fail", rs.getString("nama_fail") == null ? ""
								: rs.getString("nama_fail"));
		 				getlampiran.addElement(s);
		 			}
		 		
		 			return getlampiran;
		 		} finally {
		 			if (db != null)
		 				db.close();
		 		}
		 }
		     	

}
