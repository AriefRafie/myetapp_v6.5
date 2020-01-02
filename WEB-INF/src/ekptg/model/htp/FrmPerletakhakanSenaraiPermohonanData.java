package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmPerletakhakanSenaraiPermohonanData {
	private static Vector list = new Vector();
	public static void setListPermohonan(int idFail, String carian)throws Exception {
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      String Select = "SELECT tp.id_Permohonan, p.nama, s.keterangan,f.id_Negeri ";
	      String From = "FROM Tblpermohonan tp, Tblhtphakmilikurusan u, Tblhtppihakberkepentingan p, " +
	      		"Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblpfdfail f ";
	      String Where = "WHERE u.id_Permohonan = tp.id_Permohonan AND u.id_Hakmilikurusan = p.id_Hakmilikurusan ";
	      		 Where +="AND sf.id_Permohonan = tp.id_Permohonan AND sf.id_Suburusanstatus = ss.id_Suburusanstatus ";
	      		 Where +="AND ss.id_Status = s.id_Status ";
	      		 Where +="AND tp.id_Fail = f.id_Fail ";
	      		 Where +="AND tp.id_Fail = "+idFail+" ";
	      		 Where +="AND p.nama LIKE '%"+carian+"%'";
	      /*
	      SQLRenderer r = new SQLRenderer();
	      r.add("tp.id_Fail");
	      r.add("tp.id_Permohonan");
	      r.add("p.nama");
	      r.add("s.keterangan");

	      r.add("u.id_Permohonan",r.unquote("tp.id_Permohonan"));
	      r.add("u.id_Hakmilikurusan",r.unquote("p.id_Hakmilikurusan"));
	      r.add("sf.id_Permohonan",r.unquote("tp.id_Permohonan"));
	      r.add("sf.id_Suburusanstatus",r.unquote("ss.id_Suburusanstatus"));
	      r.add("ss.id_Status",r.unquote("s.id_Status"));
	      r.set("tp.id_Fail",idFail);

	      sql = r.getSQLSelect(Table);
	      */
	      sql = Select + From + Where;
	      ResultSet rs = stmt.executeQuery(sql);
	      //
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("nama", rs.getString("nama"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("idNegeri", rs.getString("id_Negeri"));
	    	  list.addElement(h);
	    	  bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getListPermohonan(){
		  System.out.println(list.size());
		  return list;
	  }
}
