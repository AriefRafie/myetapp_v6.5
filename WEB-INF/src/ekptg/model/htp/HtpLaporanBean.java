package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;


public class HtpLaporanBean extends EkptgCache implements IHtpLaporan {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.HtpLaporanBean.class);
	
	@Override
	public Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusan
		(String suburusan, String level, String jlaporan) throws Exception {
			
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("rjd.keterangan");
	      r.add("rdu.module_id");
	      r.add("rdu.peringkat");
	      r.add("rdu.template");
	      r.add("rdu.id_suburusan");
	      r.add("RU.NAMA_URUSAN");
	      r.add("RDU.ATURAN");
		  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
		  r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
		  r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
		  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
		  String sqlAdd = "";
		  if(level!=null){
			  if(level.equals("unit")){
				  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
			  }else if(level.equals("daerah"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);
			  else if(level.equals("negeri"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);

		  }		           
	      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
	      if(!suburusan.equals("TIADA")){
	    		  sql += " AND RDU.ID_SUBURUSAN in ("+suburusan+")"; 
	    		  }
	      sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ATURAN";
	      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
	      Statement stmt = db.getStatement();
//	      myLog.info("getLaporanMengikutUrusan("+suburusan+","+level+"):sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String, Comparable> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, Comparable>();
	    	  h.put("idmodule",rs.getString("module_id")); 
	    	  h.put("keterangan",rs.getString("keterangan"));
	    	  h.put("peringkat",rs.getString("peringkat"));
	    	  h.put("template",rs.getString("template"));
	    	  h.put("idsuburusan",rs.getString("id_suburusan"));
	    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
	    	  v.addElement(h);
	      }
	      return v;
	    } finally {
	      if (db != null) db.close();
	    }
		//}
	}		
	
	@Override
	public Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusanLike
		(String suburusan, String level, String jlaporan,String template) throws Exception {
		Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("rjd.keterangan");
	      r.add("rdu.module_id");
	      r.add("rdu.peringkat");
	      r.add("rdu.template");
	      r.add("rdu.id_suburusan");
	      r.add("RU.NAMA_URUSAN");
	      r.add("RDU.ATURAN");
		  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
		  r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
		  r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
		  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
		  if(template!=null){
		      r.add("rdu.template","%"+template+"%","LIKE");
	  
		  }
		  String sqlAdd = "";
		  if(level!=null){
			  if(level.equals("unit")){
				  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
			  }else if(level.equals("daerah"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);
			  else if(level.equals("negeri"))
				  r.add("SUBSTR(rdu.peringkat,0,6)",level);

		  }		           
	      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
	      if(!suburusan.equals("TIADA")){
	    		  sql += " AND RDU.ID_SUBURUSAN in ("+suburusan+")"; 
	    		  }
	      sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ATURAN";
	      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
	      Statement stmt = db.getStatement();
	      myLog.info("getLaporanMengikutUrusanLike("+suburusan+","+level+"):sql::"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable<String, Comparable> h;
	      while (rs.next()) {
	    	  h = new Hashtable<String, Comparable>();
	    	  h.put("idmodule",rs.getString("module_id")); 
	    	  h.put("keterangan",rs.getString("keterangan"));
	    	  h.put("peringkat",rs.getString("peringkat"));
	    	  h.put("template",rs.getString("template"));
	    	  h.put("idsuburusan",rs.getString("id_suburusan"));
	    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
	    	  v.addElement(h);
	      }
	      return v;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}	
	
	@Override
	public Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusan
		(String suburusan, String level, String jlaporan,String template,String notIn) throws Exception {
		System.out.println("laporan...");
		String key = "DBgetLaporan";
		Db db = null;
		String sql = "";
		    try {
		      db = new Db();
		      SQLRenderer r = new SQLRenderer();
		      r.add("rjd.keterangan");
		      r.add("rdu.module_id");
		      r.add("rdu.peringkat");
		      r.add("rdu.template");
		      r.add("rdu.id_suburusan");
		      r.add("RU.NAMA_URUSAN");
		      r.add("RDU.ATURAN");
			  r.add("rjd.ID_JENISDOKUMEN",r.unquote("rdu.ID_JENISDOKUMEN"));
			  r.add("rdu.ID_SUBURUSAN",r.unquote("rsu.ID_SUBURUSAN"));
			  r.add("RSU.ID_URUSAN",r.unquote("RU.ID_URUSAN"));
			  r.add("rdu.DOKUMEN",r.unquote("'"+jlaporan+"'"));
			  r.add("LOWER(rdu.template)","%"+template+"%","like");
			  String sqlAdd = "";
			  if(level!=null){
				  if(level.equals("unit")){
					  sqlAdd = " AND ( SUBSTR(rdu.peringkat,0,4)='"+level+"' OR SUBSTR(rdu.peringkat,0,6)='daerah' )";
				  }else if(level.equals("daerah"))
					  r.add("SUBSTR(rdu.peringkat,0,6)",level);
				  else if(level.equals("negeri"))
					  r.add("SUBSTR(rdu.peringkat,0,6)",level);

			  }		           
		      sql = r.getSQLSelect("tblrujdokumenurl rdu,tblrujjenisdokumen rjd,tblrujurusan ru,tblrujsuburusan rsu");
		      if(!suburusan.equals("TIADA")){
		    		  sql += " AND RDU.ID_SUBURUSAN in ("+suburusan+")"; 
		      }
		      sql += " AND rdu.template NOT IN ("+notIn+") ";
		      sql += sqlAdd + " ORDER BY RU.NAMA_URUSAN,RDU.ATURAN";
		      Vector<Hashtable<String, Comparable>> v = new Vector<Hashtable<String, Comparable>>(); 
		      Statement stmt = db.getStatement();
		      System.out.println("laporan... "+sql);
		      //myLog.info("getLaporanMengikutUrusan("+suburusan+","+level+","+jlaporan+","+template+","+ notIn+"):sql="+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable<String, Comparable> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, Comparable>();
		    	  h.put("idmodule",rs.getString("module_id")); 
		    	  h.put("keterangan",rs.getString("keterangan"));
		    	  h.put("peringkat",rs.getString("peringkat"));
		    	  h.put("template",rs.getString("template"));
		    	  h.put("idsuburusan",rs.getString("id_suburusan"));
		    	  h.put("namaurusan",rs.getString("NAMA_URUSAN"));
		    	  v.addElement(h);
		      }
		      return v;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	
	
	
	
	
}
