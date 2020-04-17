package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmPerletakhakanInfoData {
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	public static Vector getSemak(int id)throws Exception {
	    Db db = null;
	    Vector list = new Vector();
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	 
	      r.add("p.id_Fail");
	      r.add("p.id_Permohonan");
	      r.add("f.id_Negeri");
	      r.add("f.id_Kementerian");
	      r.add("hp.id_Agensi");
	      r.add("f.id_Suburusan");
	      r.add("f.tajuk_Fail");
	      r.add("f.no_Fail");
	      r.add("p.tarikh_Surat");
	      r.add("hp.no_Rujukan_Lain");
	      r.add("hp.tarikh_Agihan");
	      r.add("p.tarikh_Terima");
	      
	      r.add("p.id_Fail",r.unquote("f.id_Fail"));
	      r.add("p.id_Permohonan",r.unquote("hp.id_Permohonan"));

	      r.set("p.id_Permohonan", id);
	      
	      sql = r.getSQLSelect("Tblpermohonan p, Tblpfdfail f,Tblhtppermohonan hp");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  h.put("idKementerian", rs.getString("id_Kementerian"));
	    	  h.put("idAgensi", rs.getString("id_Agensi"));
	    	  h.put("idSuburusan", rs.getString("id_Suburusan"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tSurat", Format.format(rs.getDate("tarikh_Surat")));
	    	  h.put("noRujukan", rs.getString("no_Rujukan_Lain"));
	    	  h.put("tAgihan", Format.format(rs.getDate("tarikh_Agihan")));
	    	  h.put("tPermohonan", rs.getDate("tarikh_Terima")==null?"":Format.format(rs.getDate("tarikh_Terima")));
	    	  list.addElement(h);
	      }
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
}
