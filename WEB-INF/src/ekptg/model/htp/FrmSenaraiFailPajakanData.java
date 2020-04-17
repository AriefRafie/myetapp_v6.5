package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

public class FrmSenaraiFailPajakanData {
	private static Vector list = new Vector();
        private static Logger log = Logger.getLogger(FrmSenaraiFailPajakanData.class);
        private static Db db;
        private static Connection conn;

	public static void setList(String carian, String noFail, Long idNegeri)throws Exception {
	    
	    list.clear();
	    String sql;
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql = "SELECT distinct f.id_Fail, p.id_Permohonan, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu ";
	      sql +="FROM Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
	      sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri ";
	      sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
	      sql +="AND sf.aktif = '1' AND f.id_Urusan = 3 AND f.tajuk_Fail LIKE '%"+carian+"%' ";
	      sql +="AND f.no_Fail LIKE '%"+noFail+"%' ";

              if(idNegeri != null){
	    	  sql +="AND f.id_Negeri = "+idNegeri;
              }

	      sql +=" ORDER BY n.kod_Mampu";

	      ResultSet rs = stmt.executeQuery(sql);
	      log.info(sql);

	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idFail", rs.getString("id_Fail"));
	    	  h.put("idPermohonan", rs.getString("id_Permohonan"));
	    	  h.put("noFail", rs.getString("no_Fail"));
	    	  h.put("tajuk", rs.getString("tajuk_Fail"));
	    	  h.put("negeri", rs.getString("nama_Negeri"));
	    	  h.put("keterangan", rs.getString("keterangan"));
	    	  h.put("kodMampu", rs.getString("kod_Mampu"));
	    	  list.addElement(h);
	    	  bil++;
	      }

	    }
            catch(Exception e){
                e.printStackTrace();
            }


            finally {
	      if (db != null) db.close();
	    }
	  }

	  public static Vector getList(){
		  log.info("FrmSenaraiFailPajakanData : getList : " + list.size());
		  return list;
	  }
}
