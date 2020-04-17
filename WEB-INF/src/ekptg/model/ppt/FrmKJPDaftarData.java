package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmKJPDaftarData {
	
	private static Vector list = new Vector();
	private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");

	  public static Vector getListTanah(int id_permohonan) throws Exception {
		  
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("a.id_hakmilik");
	      r.add("a.no_lot");
	      r.add("a.no_pt");
	      r.add("a.no_hakmilik");
	      r.add("c.nama_mukim");
	      r.add("b.keterangan");
	      r.add("a.luas_lot");
	      r.add("d.keterangan");
	      
	      r.add("a.id_jenishakmilik",r.unquote("b.id_jenishakmilik"));
	      r.add("a.id_mukim",r.unquote("c.id_mukim"));
	      r.add("a.id_unitluaslot",r.unquote("d.id_luas"));
	      r.add("a.id_permohonan", id_permohonan, "=");

	      sql = r.getSQLSelect("Tblppthakmilik a, Tblrujjenishakmilik b, Tblrujmukim c, Tblrujluas d");

	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_hakmilik",rs.getLong("id_hakmilik"));
	        if(rs.getString("no_lot") == null){
	    		h.put("no_lot","");
	    	}else{
	    		h.put("no_lot",rs.getString("no_lot"));
	    	}
	    	if(rs.getString("no_pt") == null){
	    		h.put("no_pt","");
	    	}else{
	    		h.put("no_pt",rs.getString("no_pt"));
	    	}
	    	if(rs.getString("no_hakmilik") == null){
	    		h.put("no_hakmilik","");
	    	}else{
	    		h.put("no_hakmilik",rs.getString("no_hakmilik"));
	    	}
	    	if(rs.getString("nama_mukim") == null){
	    		h.put("nama_mukim","");
	    	}else{
	    		h.put("nama_mukim",rs.getString("nama_mukim"));
	    	}
	    	if(rs.getString("keterangan") == null){
	    		h.put("jenis_hakmilik","");
	    	}else{
	    		h.put("jenis_hakmilik",rs.getString("keterangan"));
	    	}
	    	if(rs.getString("luas_lot") == null){
	    		h.put("luas_lot","");
	    	}else{
	    		h.put("luas_lot",rs.getString("luas_lot"));
	    	}
	    	if(rs.getString("keterangan") == null){
	    		h.put("unit_luas","");
	    	}else{
	    		h.put("unit_luas",rs.getString("keterangan"));
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
	  
	  public static Vector getListDokumen(int id_permohonan) throws Exception {
		  
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		    Db db = null;
		    list.clear();
		    String sql = "";
		    try {
		      Vector localVector1;
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_dokumen");
		      r.add("tajuk");
		      r.add("keterangan");
		      r.add("nama_fail");		      
		      r.add("id_permohonan", id_permohonan, "=");

		      sql = r.getSQLSelect("Tblpptdokumen");
		      ResultSet rs = stmt.executeQuery(sql);
		      //Vector v = new Vector();
		      Hashtable h;
		      int bil = 1;

		      while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		        h.put("id_dokumen",rs.getLong("id_dokumen"));
		        if(rs.getString("tajuk") == null){
		    		h.put("tajuk","");
		    	}else{
		    		h.put("tajuk",rs.getString("tajuk"));
		    	}
		    	if(rs.getString("keterangan") == null){
		    		h.put("keterangan","");
		    	}else{
		    		h.put("keterangan",rs.getString("keterangan"));
		    	}
		    	if(rs.getString("nama_fail") == null){
		    		h.put("nama_fail","");
		    	}else{
		    		h.put("nama_fail",rs.getString("nama_fail"));
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

}

