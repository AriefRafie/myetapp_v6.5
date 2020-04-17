package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraSenaraiNotisAwamData {
	
	Vector list = null;
	Vector listNotis = null;
	Vector paparNotis = null;
	Vector jenisTempatTampal = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	
	public void  setCarianFail(String noFail,String tarikh,String status)throws Exception {
		Db db = null;
	    String sql = "";
	    String chkData = noFail.trim();
	    //
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.kod_status NOT IN('32','27') AND s.id_seksyen = 1  AND f.id_suburusan = 53";
	      
	      if(noFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + noFail.toUpperCase() + "'||'%'";  
	      }
	     
	      if(tarikh != null){
	    	  if (!tarikh.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(tarikh)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(status != null){
	    	  if (!status.trim().equals("")){
	    		  if (!status.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + status + "' ";
	    		  }
	    	  }	  
	      }
	    
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_fail","");
	    	  h.put("id_status", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "");
	    	  h.put("no_rujukan_ptd", "");
	    	  h.put("no_rujukan_ptg", "");
	    	  h.put("no_rujukan_upt", "");
	    	  h.put("tarikh_permohonan", "");
	    	  h.put("nama_suburusan", "");
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("keterangan", "");
	    	  h.put("no_fail","Tiada rekod.");
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public Vector getList(){
		return list;
	}
	public Vector getListNotis(){
		return listNotis;
	}
	public Vector getPaparNotis(){
		return paparNotis;
	}
	public static String simpanNotis(Hashtable data) throws Exception {
		// Masukan data dalam table - PTG
		
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	long idNotisAwam = DB.getNextID("TBLPPTNOTISAWAM_SEQ");
		    	String idPermohonan = (String)data.get("idPermohonan");
		    	String tempatTampal = (String)data.get("jenisTempatTampal");
		    	String tempat = (String)data.get("tempat");
	    	 	String tarikhTampal = (String)data.get("tarikhTampal");
	    	 	String tkhTampal = "to_date('" + tarikhTampal + "','dd/MM/yyyy')";
		    			    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.add("id_Notisawam",idNotisAwam);
			     r.add("id_permohonan", idPermohonan);
			     r.add("tempat", tempat);
			     r.add("tarikh_Tampal", r.unquote(tkhTampal));
			     r.add("jenis_Tempat_Tampal",tempatTampal);
			     			     
			     sql = r.getSQLInsert("tblpptnotisawam");
			     stmt.executeUpdate(sql);
			     
			     Statement stmtQ = db.getStatement();
			     SQLRenderer rQ = new SQLRenderer();
			     rQ.update("id_permohonan", idPermohonan);
			     rQ.add("id_status", "52");
			     			     
			     sql = rQ.getSQLUpdate("tblpptpermohonan");
			     stmtQ.executeUpdate(sql);
			     
			     id = ""+ idNotisAwam;
			    
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
		    return id;
	}

	public void listTampalNotis(String idPermohonan) throws Exception {
		//listkan notis yg telah ditampal di PTG
		Db db = null;
	    String sql = "";
	    String chkData = idPermohonan.trim();
	    
	    try {
	      listNotis = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT n.id_notisawam,p.id_permohonan, n.tempat, n.tarikh_tampal, n.jenis_tempat_tampal FROM Tblpptnotisawam n, Tblpptpermohonan p "+
	      		" WHERE p.id_permohonan = n.id_permohonan AND n.id_permohonan = '"+idPermohonan+"'";

	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_notisawam", rs.getString("id_notisawam"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("tempat", rs.getString("tempat"));
	    	  if(rs.getString("jenis_tempat_tampal").equals("1")){
	    		  h.put("tempat_tampal","PTG/PTD");
	    	  }
	    	  else if(rs.getString("jenis_tempat_tampal").equals("2")){
	    		  h.put("tempat_tampal","PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR");
	    	  }
	    	  else if(rs.getString("jenis_tempat_tampal").equals("3")){
	    		  h.put("tempat_tampal","TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH");
	    	  }
	    	 
	       	  h.put("tarikh", rs.getDate("tarikh_tampal")==null?"":Format.format(rs.getDate("tarikh_tampal")));

	       	  listNotis.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_notisawam", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("tempat_tampal","Tiada rekod.");
	    	  h.put("tempat", "");
	    	  h.put("tarikh", "");
	    	  
	    	  listNotis.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public void paparNotis(String idNotisAwam) throws Exception {
	//papar Notis PTG
		Db db = null;
	    String sql = "";
	    
	    try {
	      paparNotis = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT N.ID_NOTISAWAM, N.TEMPAT, N.TARIKH_TAMPAL,N.JENIS_TEMPAT_TAMPAL FROM TBLPPTNOTISAWAM N"+
	      		" WHERE N.ID_NOTISAWAM = '"+idNotisAwam+"'";
	        	    
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("ID_NOTISAWAM", rs.getString("ID_NOTISAWAM"));
	    	  h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL"));
	    	  h.put("TEMPAT", rs.getString("TEMPAT"));
	       	  h.put("TARIKH_TAMPAL", rs.getDate("TARIKH_TAMPAL")==null?"":Format.format(rs.getDate("TARIKH_TAMPAL")));

	       	  paparNotis.addElement(h);
	    	 
	      }
	      
	    
	    } finally {
	      if (db != null) db.close();
	    }
	}
	public static void updateNotis(Hashtable data) throws Exception {
		// update table Notis Awam
		Db db = null;
		 String sql = "";
		 String id = "";
		 
		    try
		    {
		    	String idNotisAwam = (String)data.get("idNotisAwam");
		    	String tempatTampal = (String)data.get("jenisTempatTampal");
		    	String tempat = (String)data.get("tempat");
	    	 	String tarikhTampal = (String)data.get("tarikhTampal");
	    	 	String tkhTampal = "to_date('" + tarikhTampal + "','dd/MM/yyyy')";
		    			    			    	
		    	 db = new Db();
			     Statement stmt = db.getStatement();
			     SQLRenderer r = new SQLRenderer();
			     r.update("id_Notisawam",idNotisAwam);
			     r.add("tempat", tempat);
			     r.add("tarikh_Tampal", r.unquote(tkhTampal));
			     r.add("jenis_Tempat_Tampal",tempatTampal);
			     
			     
			     sql = r.getSQLUpdate("tblpptnotisawam");
			     stmt.executeUpdate(sql);
			     
			    
			      
		    }finally {
			      if (db != null) db.close();
		    }
		    
	}

	public static void deleteNotisAwam(String idNotisAwam) throws Exception {
		// delete row berdasarkan idNotisAwam
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmtQ = db.getStatement();
	      SQLRenderer rQ = new SQLRenderer();
	      rQ.add("id_Notisawam", idNotisAwam);
	      sql = rQ.getSQLDelete("tblpptnotisawam");	      
	      stmtQ.executeUpdate(sql);
	      
	    }
	    finally {
	      if (db != null) db.close();
	    }
		
		
	}
	
	public Vector jenisTempatTampal(String id_permohonan)throws Exception{
		Db db = null;
		String sql = "";
		try{
			 jenisTempatTampal = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		      
			 sql = "SELECT JENIS_TEMPAT_TAMPAL FROM TBLPPTNOTISAWAM WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
			 
			 ResultSet rs = stmt.executeQuery(sql);  
		     Hashtable h;
		     while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL"));
		    	  jenisTempatTampal.addElement(h);
		     }
		     
		     return jenisTempatTampal;
			
		}
		 finally {
		      if (db != null) db.close();
		    }
			
	}
}
