package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class PermohonanPPT {
	
	private static Vector list = new Vector();
	private static Vector listNotis = new Vector();
	private static Vector listSemak = new Vector();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static Vector getList(){
		  return list;
	}
	public static Vector getListNotis(){
		 return listNotis;
	}
	public static Vector getSemak(){
		  return listSemak;
	} 
	
	public static void setList(String carianPermohonan, String carianTarikh, int cSuburusan, int cStatus)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    String cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct p.no_rujukan_upt, p.id_permohonan, p.no_permohonan, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status ";
	      sql +="AND p.id_status = ANY(5,51,128)";
	      if(carianPermohonan != null)
	      {
	    	  sql +="AND p.no_permohonan LIKE '%"+carianPermohonan+"%' ";
	      }
	      if(carianTarikh != "")
	      {
	    	  sql +="AND p.tarikh_permohonan = "+cariTarikh+" ";
	      }
	      if(cSuburusan != 0)
	      {	  
	    	  sql +="AND su.id_suburusan = "+cSuburusan+" ";
	      }
	      if(cStatus != 0)
	      {	  
	    	  sql +="AND s.id_status = "+cStatus+" ";
	      }
	      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";

	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  if(rs.getString("no_permohonan") == null){
		    		h.put("no_permohonan"," ");
	    	  }else{
		    		h.put("no_permohonan",rs.getString("no_permohonan"));
	    	  }
	    	  if(rs.getString("tarikh_permohonan") == null){
	    		  h.put("tarikh_permohonan"," ");
	    	  }else{
	    		  h.put("tarikh_permohonan", Format.format(rs.getDate("tarikh_permohonan")));
	    	  }
	    	  if(rs.getString("nama_suburusan") == null){
		    		h.put("nama_suburusan"," ");
	    	  }else{
		    		h.put("nama_suburusan",rs.getString("nama_suburusan"));
	    	  }
	    	  if(rs.getString("nama_kementerian") == null){
		    		h.put("nama_kementerian"," ");
	    	  }else{
		    		h.put("nama_kementerian",rs.getString("nama_kementerian"));
	    	  }
	    	  if(rs.getString("no_rujukan_upt") == null){
		    		h.put("no_rujukan_upt"," ");
	    	  }else{
		    		h.put("no_rujukan_upt",rs.getString("no_rujukan_upt"));
	    	  }
	    	  if(rs.getString("keterangan") == null){
		    		h.put("keterangan"," ");
	    	  }else{
		    		h.put("keterangan",rs.getString("keterangan"));
	    	  }
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
	    	  }else{
		    		h.put("no_fail",rs.getString("no_fail"));
	    	  }
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
 
	public static void setListNotis(String carianFail,String carianTarikh,String carianStatus)throws Exception {
		    Db db = null;
		    listNotis.clear();
		    String sql = "";
	
		    String noFail = carianFail.trim();		    
		    String cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT distinct p.no_rujukan_upt, p.id_permohonan, p.no_permohonan, f.no_fail, p.tarikh_permohonan, su.id_suburusan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
		      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		      sql +="AND p.id_status = s.id_status ";
		      sql +="AND p.id_status = ANY(31,52)";
		      sql +=" AND su.id_suburusan = '51'";
		      
		      if(carianFail != null && carianFail != "")
		      {
		    	  sql +="AND UPPER(f.no_fail) LIKE '%"+noFail.toUpperCase()+"%' ";
		      }
		      if(carianTarikh != "" & carianTarikh != null)
		      {
		    	  sql +="AND UPPER(p.tarikh_permohonan) = "+cariTarikh+" ";
		      }
//		      if(cSuburusan != "" & cSuburusan != null)
//		      {	  
//		    	  sql +="AND su.id_suburusan = '"+cSuburusan+"'";
//		      }
		      if(carianStatus != "" & carianStatus != null)
		      {	  
		    	  sql +="AND p.id_status = '"+carianStatus+"'";
		      }
		      
		      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		      
		      ResultSet rs = stmt.executeQuery(sql);

		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  listNotis.addElement(h);
		    	  bil++;
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	
	  public static void setSemak(int id)throws Exception {
		    Db db = null;
		    listSemak.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		 
		      r.add("p.id_permohonan");
		      r.add("p.no_permohonan");
		      r.add("p.tarikh_permohonan");
		      r.add("su.nama_suburusan");
		      r.add("k.nama_kementerian");
		      r.add("s.keterangan");
		      
		      r.add("p.id_fail",r.unquote("f.id_fail"));
		      r.add("f.id_suburusan",r.unquote("su.id_suburusan"));
		      r.add("f.id_kementerian",r.unquote("k.id_kementerian"));
		      r.add("p.id_status",r.unquote("s.id_status"));

		      sql = r.getSQLSelect("Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //Vector list = new Vector();
		      Hashtable h;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan"));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  listSemak.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		  
	  public static Vector getListPemohon()throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
		      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		      sql +="AND p.id_status = s.id_status ";
		      sql +="AND p.id_status = ANY(31,52)";
		      sql +=" AND su.id_suburusan = '51'";
		      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
		    	  h.put("status", rs.getString("keterangan"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  list.addElement(h);
		    	  bil++;
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	  
	  public static Vector getListPemohonan()throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan ";
		      sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		      sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		      sql +="AND p.id_status = s.id_status ";
		      sql +="AND p.id_status = ANY(31,52)";
		      sql +="ORDER by p.tarikh_permohonan desc, p.no_permohonan desc ";
		     
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan"));
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan"));
		    	  h.put("status", rs.getString("keterangan"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
		    	  list.addElement(h);
		    	  bil++;
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	  
	  
}
