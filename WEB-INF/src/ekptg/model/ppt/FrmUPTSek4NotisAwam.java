package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmUPTSek4NotisAwam {
	
	private static Vector list = new Vector();
	private static Vector listWarta = new Vector();
	private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static Vector setListWarta(String id_permohonan) throws Exception {
		  
	    Db db = null;
	    listWarta.clear();
	    String sql = "";
	    
	    try {

	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_warta, proses_warta, no_warta, tarikh_warta, id_permohonan" +
	      			  " FROM Tblpptwarta where id_permohonan = '"+id_permohonan+"'";
	 
	    		ResultSet rs = stmt.executeQuery(sql);
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("id_warta", rs.getString("id_warta")==null?"":rs.getString("id_warta"));
	    			h.put("tarikh_warta", rs.getDate("tarikh_warta")==null?"":formatter.format(rs.getDate("tarikh_warta")));
	    			h.put("proses_warta", rs.getString("proses_warta")==null?"0":rs.getString("proses_warta"));
	    			h.put("no_warta", rs.getString("no_warta")==null?"":rs.getString("no_warta"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	    			listWarta.addElement(h);
	    		
	    		}
	    		return listWarta;
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}
	}
	
	public static void updateWarta(Hashtable data) throws Exception{
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
	    	
		    	String id_warta = (String)data.get("id_warta");
		    	String proses_warta = (String)data.get("proses_warta");
		    	String no_warta = (String)data.get("no_warta");
		    	String tarikh_warta = (String)data.get("tarikh_warta"); 
		    	String TW = "to_date('" + tarikh_warta + "','dd/MM/yyyy')";
		    	
		    	String id_user = (String)data.get("id_user");
		
		    	SQLRenderer r = new SQLRenderer();
		    	r.update("id_warta", id_warta);
		    	r.add("proses_warta", proses_warta);
		    	r.add("no_warta", no_warta);
		    	r.add("tarikh_warta", r.unquote(TW));
		    	r.add("id_kemaskini",id_user);
		    	r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    	sql = r.getSQLUpdate("Tblpptwarta");
		    	stmt.executeUpdate(sql);
		    	r.clear();
		      
		    } finally {
		      if (db != null) db.close();
		    }
	}
	  
	public static void addNotis(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	    		
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
		      
	    		long id_notisawam = DB.getNextID("TBLPPTNOTISAWAM_SEQ");
	    		String jenis_tempat_tampal = (String)data.get("jenis_tempat_tampal");
	    		String tempat = (String)data.get("tempat");
	    		String tarikh_tampal = (String)data.get("tarikh_tampal"); 
	    		String TT = "to_date('" + tarikh_tampal + "','dd/MM/yyyy')";
	    		String id_permohonan = (String)data.get("id_permohonan");
	      
	    		String id_user = (String)data.get("id_user");
	    	
	     
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_notisawam", id_notisawam);
	    		r.add("jenis_tempat_tampal", jenis_tempat_tampal);
	    		r.add("tempat", tempat);
	    		r.add("tarikh_tampal", r.unquote(TT));
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_masuk",id_user);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));    
	    		sql = r.getSQLInsert("Tblpptnotisawam");
	    		stmt.executeUpdate(sql);
	    		r.clear();
	      
	    	}finally {
	    		if (db != null) db.close();
	    	}
	}
	  
	public static Vector setListNotis(String id_permohonan) throws Exception {
		  
		Db db = null;
		list.clear();
		String sql = "";
		
		try {

		      	db = new Db();
		      	Statement stmt = db.getStatement();
		      	SQLRenderer r = new SQLRenderer();
		      	r.add("id_notisawam");
		      	r.add("jenis_tempat_tampal");
		      	r.add("tempat");
		      	r.add("tarikh_tampal");
		      	r.add("id_permohonan");
		      
		      	r.add("id_permohonan", id_permohonan);
		      	sql = r.getSQLSelect("Tblpptnotisawam");
		  
		      	ResultSet rs = stmt.executeQuery(sql);
		      
		      	Hashtable h;
		      	int bil = 1;

		     	while (rs.next()) {
		     		h = new Hashtable();
		     		h.put("bil", bil);
		     		h.put("id_notisawam", rs.getString("id_notisawam")==null?"":rs.getString("id_notisawam"));
		     		h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		     		h.put("tempat", rs.getString("tempat")==null?"":rs.getString("tempat"));
		     		h.put("tarikh_tampal", rs.getDate("tarikh_tampal")==null?"":formatter.format(rs.getDate("tarikh_tampal")));
	
		     		
		     		if(rs.getLong("jenis_tempat_tampal") == 1){
		     			h.put("jenis_tempat_tampal","PTD / PTG");
		     			h.put("flagTT1","1");
		     		}
		     		else if(rs.getLong("jenis_tempat_tampal") == 2){
		     			h.put("jenis_tempat_tampal","PAPAN KENYATAAN AWAM DI DALAM MUKIM/BANDAR");
		     			h.put("flagTT2","1");
		     		}
		     		else if(rs.getLong("jenis_tempat_tampal") == 3){
		     			h.put("jenis_tempat_tampal","TEMPAT LAIN DIATAS / BERHAMPIRAN TANAH");
		     			h.put("flagTT3","1");
		     		}
		     		else{
		     			h.put("jenis_tempat_tampal","-");
		     			h.put("flagTT1","");
		     			h.put("flagTT2","");
		     			h.put("flagTT3","");
		     		}
		        
		    		list.addElement(h);
		    		bil++;
		     	}
		     	return list;
		    	}
		    finally {
		      if (db != null) db.close();
		    }
	}
		  
	
		  public static Vector getListNotis(){
			  return list;
		  } 
		  
	public static Vector setListNotisView(String id_notisawam) throws Exception {
			  
			    Db db = null;
			    list.clear();
			    String sql = "";
			    try {
			    
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		sql = "SELECT DISTINCT id_notisawam, jenis_tempat_tampal, tempat, tarikh_tampal, id_permohonan ";  
			    		sql += " FROM Tblpptnotisawam ";
			    		sql += " WHERE id_notisawam = '"+id_notisawam+"'";
			    		
			    		ResultSet rs = stmt.executeQuery(sql);

			    		Hashtable h;
			    		int bil = 1;

			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
			    			h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
			    			h.put("id_notisawam", rs.getString("id_notisawam")==null?"":rs.getString("id_notisawam"));
			    			h.put("jenis_tempat_tampal", rs.getString("jenis_tempat_tampal")==null?"":rs.getString("jenis_tempat_tampal"));
			    			h.put("tempat", rs.getString("tempat")==null?"":rs.getString("tempat"));
			    			h.put("tarikh_tampal", rs.getDate("tarikh_tampal")==null?"":formatter.format(rs.getDate("tarikh_tampal")));
			    			list.addElement(h);
			    			bil++;
			    		}
			    		return list;
			    	}
			    	finally {
			    		if (db != null) db.close();
			    	}
	}
		  
	public static void updateNotis(Hashtable data) throws Exception{
		
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	 	db = new Db();
		    	 	Statement stmt = db.getStatement();
			      
		    		String id_notisawam = (String)data.get("id_notisawam");
		    		String jenis_tempat_tampal = (String)data.get("jenis_tempat_tampal");
		    		String tempat = (String)data.get("tempat");
		    		String tarikh_tampal = (String)data.get("tarikh_tampal"); 
		    		String TT = "to_date('" + tarikh_tampal + "','dd/MM/yyyy')";
		    	
		    		String id_user = (String)data.get("id_user");
		    	  
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_notisawam", id_notisawam);
		    		r.add("jenis_tempat_tampal", jenis_tempat_tampal);
		    		r.add("tempat", tempat);
		    		r.add("tarikh_tampal", r.unquote(TT));
		    		r.add("id_kemaskini",id_user);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		sql = r.getSQLUpdate("Tblpptnotisawam");
		    		stmt.executeUpdate(sql);
		    		r.clear();
		      
		    	}finally {
		    		if (db != null) db.close();
		    	}
	}

	
	  
	//simpan tarikh borang B
	public static void simpanTarikh(Hashtable data) throws Exception {
			 
		Db db = null;
		String sql = "";
		
		try {
			    	
				db = new Db();
				Statement stmt = db.getStatement();
		    
			    String id_user = (String)data.get("id_user");
			    String id_permohonan = (String)data.get("id_permohonan");
			    	
			    SQLRenderer r = new SQLRenderer();
			    r.update("id_permohonan", id_permohonan);
			    r.add("tarikh_borangb",r.unquote("sysdate"));
			    r.add("id_kemaskini",id_user);
			    r.add("tarikh_kemaskini",r.unquote("sysdate"));
			    sql = r.getSQLUpdate("tblpptpermohonan");
			    stmt.executeUpdate(sql);
			}
			finally {
			   if (db != null) db.close();
			}
	}//close simpan tarikh borang a
	  
	public static void hapusTampal(String id_notisawam) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM Tblpptnotisawam WHERE id_notisawam = '"+id_notisawam+"'";
	    		stmt.executeUpdate(sql);
	 
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusTampal
	
}
