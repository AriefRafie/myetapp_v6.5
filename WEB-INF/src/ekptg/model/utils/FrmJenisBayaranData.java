package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.model.entities.Tblrujjenisbayaran;

public class FrmJenisBayaranData {

	 public static Vector<Tblrujjenisbayaran> getList()throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_jenisbayaran");
	      r.add("kod_jenis_bayaran");
	      r.add("keterangan");
	      r.add("id_bayaran");
	      r.add("amaun");
	      r.add("id_seksyen");
	      r.add("id_masuk");
	      r.add("tarikh_masuk");
	      r.add("id_kemaskini");
	      r.add("tarikh_kemaskini");
	      sql = r.getSQLSelect("tblrujjenisbayaran");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<Tblrujjenisbayaran> list = new Vector<Tblrujjenisbayaran>();
	      while (rs.next()) {
	        Tblrujjenisbayaran cb = new Tblrujjenisbayaran();
	        cb.setIdBayaran(rs.getLong("id_jenisbayaran"));
	        cb.setKodJenisBayar(rs.getString("kod_jenis_bayaran"));
	        cb.setKeterangan(rs.getString("keterangan"));
	        cb.setAmaun(rs.getDouble("amaun"));
	        cb.setIdMasuk(rs.getLong("id_masuk"));
	        cb.setTarikhMasuk(rs.getDate("tarikh_masuk"));
	        cb.setIdKemaskini(rs.getLong("id_kemaskini"));
	        cb.setTarikhKemaskini(rs.getDate("tarikh_kemaskini"));
	        list.addElement(cb);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	 
	   public static Vector<Tblrujjenisbayaran> getJenisBayaranBySeksyen(String idSeksyen) throws Exception {
	    	Db db = null;
	    	String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_jenisbayaran");
		      r.add("kod_jenis_bayaran");
		      r.add("keterangan");
		      r.add("amaun");
		      r.add("id_seksyen");	      
		      r.add("id_masuk");
		      r.add("tarikh_masuk");
		      r.add("id_kemaskini");
		      r.add("tarikh_kemaskini");
		      r.add("id_db");
		      r.add("id_seksyen",r.unquote(idSeksyen));
		      sql = r.getSQLSelect("tblrujjenisbayaran");
		      ResultSet rs = stmt.executeQuery(sql);
		
		      Vector<Tblrujjenisbayaran> v = new Vector<Tblrujjenisbayaran>();
		      Tblrujjenisbayaran s = null;
		      while (rs.next()) {    	  
		    	  s = new Tblrujjenisbayaran();
			      s.setIdJenisbayaran(rs.getLong("id_jenisbayaran"));
			      s.setKodJenisBayar(rs.getString("kod_jenis_bayaran"));
			  	  s.setKeterangan(rs.getString("keterangan"));
			  	  s.setAmaun(rs.getDouble("amaun"));
				  s.setIdMasuk(rs.getString("id_masuk") == null ? 0L : rs.getLong("id_masuk"));
				  s.setTarikhMasuk(rs.getString("tarikh_masuk") == null ? new Date() : rs.getDate("tarikh_masuk"));
				  s.setIdKemaskini(rs.getString("id_kemaskini") == null ? 0L : rs.getLong("id_kemaskini"));
				  s.setTarikhKemaskini(rs.getString("tarikh_kemaskini") == null ? new Date() : rs.getDate("tarikh_kemaskini"));
				  v.addElement(s);

		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}
	   
	   public static Tblrujjenisbayaran getJenisBayaran(String id_JenisBayaran) throws Exception {
	    	Db db = null;
	    	String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_jenisbayaran");
		      r.add("kod_jenis_bayaran");
		      r.add("keterangan");
		      r.add("amaun");
		      r.add("id_seksyen");	      
		      r.add("id_masuk");
		      r.add("tarikh_masuk");
		      r.add("id_kemaskini");
		      r.add("tarikh_kemaskini");
		      r.add("id_db");
		      r.add("id_jenisbayaran",r.unquote(id_JenisBayaran));
		      sql = r.getSQLSelect("tblrujjenisbayaran");
		      ResultSet rs = stmt.executeQuery(sql);
		
		      Tblrujjenisbayaran s = null;
		      while (rs.next()) {    	  
		    	  s = new Tblrujjenisbayaran();
			      s.setIdJenisbayaran(rs.getLong("id_jenisbayaran"));
			      s.setKodJenisBayar(rs.getString("kod_jenis_bayaran"));
			  	  s.setKeterangan(rs.getString("keterangan"));
			  	  s.setAmaun(rs.getDouble("amaun"));
				  s.setIdMasuk(rs.getString("id_masuk") == null ? 0L : rs.getLong("id_masuk"));
				  s.setTarikhMasuk(rs.getString("tarikh_masuk") == null ? new Date() : rs.getDate("tarikh_masuk"));
				  s.setIdKemaskini(rs.getString("id_kemaskini") == null ? 0L : rs.getLong("id_kemaskini"));
				  s.setTarikhKemaskini(rs.getString("tarikh_kemaskini") == null ? new Date() : rs.getDate("tarikh_kemaskini"));
		      }
		      return s;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		}

	   
	  public static void add(String kodjenisbayaran, String keterangan, double amaun, Long id_masuk) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_jenisbayaran",ekptg.helpers.DB.getNextID("TBLRUJJENISBAYARAN_SEQ"));
	      r.add("kod_jenis_bayaran", kodjenisbayaran);
	      r.add("keterangan", keterangan);
	      r.add("amaun", amaun);
	      r.add("id_masuk", id_masuk);
	      r.add("tarikh_masuk", r.unquote("sysdate"));
	      r.add("id_kemaskini", id_masuk);
	      r.add("tarikh_kemaskini", r.unquote("sysdate"));
	      sql = r.getSQLInsert("tblrujjenisbayaran");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	  public static void update(Long idjenisbayaran, String kodjenisbayaran,
			  String keterangan,String amaun, Long id_kemaskini) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.clear();
	      System.out.println("FrmJenisBayaranData:update::idjenisbayaran"+idjenisbayaran);
	      System.out.println("FrmJenisBayaranData:update::kodjenisbayaran"+kodjenisbayaran);
	      System.out.println("FrmJenisBayaranData:update::keterangan"+keterangan);
	      System.out.println("FrmJenisBayaranData:update::amaun"+amaun);
	      System.out.println("FrmJenisBayaranData:update::id_kemaskini"+id_kemaskini);

	      r.update("id_jenisbayaran", ""+idjenisbayaran);
	      r.add("kod_jenis_bayaran", kodjenisbayaran);
	      r.add("keterangan", keterangan);
	      r.add("amaun", amaun);
	      r.add("id_kemaskini", id_kemaskini);
	      //r.add("tarikh_kemaskini", r.unquote("sysdate"));
	      //System.out.println("FrmJenisBayaranData:update::r"+r);
	      sql = r.getSQLUpdate(" tblrujjenisbayaran");
	      System.out.println("FrmJenisBayaranData:update::"+sql);
	      stmt.executeUpdate(sql);

	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String id_carabayar) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = "delete from tblrujjenisbayaran where id_jenisbayaran = " + id_carabayar;
	      stmt.executeUpdate(sql);
	    }
	    finally
	    {
	      if (db != null) db.close();
	    }
	  }
	}