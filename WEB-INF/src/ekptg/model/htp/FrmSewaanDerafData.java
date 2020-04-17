package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.model.utils.UniqueStringId;

public class FrmSewaanDerafData {

	 public static Vector getSenarai(String idpermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      //select f.noFail,sf.tarikhMasuk, sf.tarikhKemaskini,s.keterangan " +
	       // " from Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s  " +
	        //" where p.idFail=f.idFail and f.idUrusan=309 and p.idPermohonan=sf.idPermohonan and " +
	        //" sf.idSuburusanstatus=us.idSuburusanstatus and us.idStatus=s.idStatus and " +
	        //" p.idPermohonan = :aIdPermohonan");
	      r.add("f.no_fail");
	      r.add("sf.tarikh_masuk");
	      r.add("sf.tarikh_kemaskini");
	      r.add("s.keterangan");

	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("sf.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("us.id_Suburusanstatus"));
	      r.add("us.id_Status",r.unquote("s.id_Status"));

	      r.add("f.id_Urusan","309");
	      r.add("p.id_Permohonan",idpermohonan);
	      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblrujsuburusanstatusfail sf,Tblrujsuburusanstatus us,Tblrujstatus s");
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector list = new Vector();
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("no", rs.getString("no_Fail"));
	    	  if(rs.getString("tarikh_masuk")!=null)
	    		  h.put("tm", rs.getDate("tarikh_masuk"));
	    	  else
	    		  h.put("tm",new Date());
	    		  
	    	  if(rs.getString("tarikh_kemaskini")!=null)
	    			  h.put("tk", rs.getDate("tarikh_kemaskini"));
	    	  else
    			  h.put("tk", new Date());
	    		  
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  list.addElement(h);

		        /*Tblpermohonan per = new Tblpermohonan();
		        per.setIdPermohonan(rs.getLong("id_Fail"));

		        Tblfail fail = new Tblfail();
		        fail.setKodFail(rs.getString("no_Fail"));
		        fail.setTajukFail(rs.getString("tajuk_Fail"));

		        Tblrujstatus status = new Tblrujstatus();
		        status.setKeterangan("keterangan");

		        list.addElement(per);*/
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static void add(String kod_cara_bayar, String keterangan, Long id_masuk,
			  Date tarikh_masuk) throws Exception {
	    Db db = null;
	    String sql = "";
	    if(tarikh_masuk==null){
	    	tarikh_masuk = new Date(); }
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();

	      r.add("id_carabayar", UniqueStringId.get());
	      r.add("kod_cara_bayar", kod_cara_bayar);
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


	  public static void update(Long id_carabayar, String kod_cara_bayar,
			  String keterangan, Long id_kemaskini, Date tarikhKemaskini) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.clear();

	      r.update("id_carabayar", ""+id_carabayar);
	      r.add("kod_cara_bayar", kod_cara_bayar);
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
