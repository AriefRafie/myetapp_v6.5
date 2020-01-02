package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.model.entities.Tblrujcarabayar;

public class FrmCaraBayarData {

	 public static Vector<Tblrujcarabayar> getList()throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_carabayar");
	      r.add("kod_carabayar");
	      r.add("keterangan");
	      r.add("id_masuk");
	      r.add("tarikh_masuk");
	      r.add("id_kemaskini");
	      r.add("tarikh_kemaskini");
	      sql = r.getSQLSelect("tblrujcarabayar");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector<Tblrujcarabayar> list = new Vector<Tblrujcarabayar>();
	      while (rs.next()) {
	        Tblrujcarabayar cb = new Tblrujcarabayar();
	        cb.setIdCarabayar(rs.getLong("id_carabayar"));
	        cb.setKodCaraBayar(rs.getString("kod_carabayar"));
	        cb.setKeterangan(rs.getString("keterangan"));
			cb.setIdMasuk(rs.getString("id_masuk") == null ? 0L : rs.getLong("id_masuk"));
			cb.setTarikhMasuk(rs.getString("tarikh_masuk") == null ? new Date() : rs.getDate("tarikh_masuk"));
			cb.setIdKemaskini(rs.getString("id_kemaskini") == null ? 0L : rs.getLong("id_kemaskini"));
			cb.setTarikhKemaskini(rs.getString("tarikh_kemaskini") == null ? new Date() : rs.getDate("tarikh_kemaskini"));
	        list.addElement(cb);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void add(String kod_carabayar, String keterangan, Long id_masuk,
			  Date tarikh_masuk) throws Exception {
	    Db db = null;
	    String sql = "";
	    if(tarikh_masuk==null){
	    	tarikh_masuk = new Date(); }
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("kod_carabayar", kod_carabayar);
	      r.add("keterangan", keterangan);
	      r.add("id_masuk", id_masuk);
	      r.add("tarikh_masuk", tarikh_masuk);
	      r.add("id_kemaskini", id_masuk);
	      r.add("tarikh_kemaskini", tarikh_masuk);
	      sql = r.getSQLInsert("tblrujcarabayar");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }


	  public static void update(Long id_carabayar, String kod_carabayar,
			  String keterangan, Long id_kemaskini, Date tarikhKemaskini) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.clear();

	      r.update("id_carabayar", ""+id_carabayar);
	      r.add("kod_carabayar", kod_carabayar);
	      r.add("keterangan", keterangan);
	      r.add("id_kemaskini", id_kemaskini);
	      r.add("tarikh_kemaskini", tarikhKemaskini);
	      sql = r.getSQLUpdate("tblrujcarabayar");
	      stmt.executeUpdate(sql);

	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void delete(String id_carabayar)
	    throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      //boolean found = false;
	      //sql = "select id_carabayar from faculty_subject where faculty_id = '" + id_carabayar + "'";
	      //ResultSet rs = stmt.executeQuery(sql);
	      //if (rs.next()) found = true;
	      //if (found)
	      sql = "delete from tblrujcarabayar where id_carabayar = " + id_carabayar;
	      stmt.executeUpdate(sql);
	    }
	    finally
	    {
	      if (db != null) db.close();
	    }
	  }
	}