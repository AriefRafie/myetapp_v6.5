package ekptg.model.ppk;

import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmPrmhnnSek8SenaraiSemak {
	//private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static void semakanAdd(String idsemakan, String idListSemakan, String txtbox, String tarikhresit) throws Exception {
	    Db db = null;
	    String sql = "";
	    //String sql = "INSERT INTO Tblsemakanhantar " +
	    //		"(id_Semakanhantar, id_semakansenarai, id_permohonan, catatan, tarikh_pelbagai)  " +
	    //		"VALUES ("+DB.getNextID("TBLSEMAKANHANTAR_SEQ")+", "+idsemakan+", "+idListSemakan+", '"+txtbox+"', '"+tarikhresit+"') ";
	    try {
	      long idSemakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
          //long idTempPemohon = DB.getNextID("IDTEMP_SEQ");
	      int idPermohonan = Integer.parseInt(idListSemakan);
	      int idSemakan = Integer.parseInt(idsemakan);
	      String tarikh_resit = "to_date('" + tarikhresit + "','MM/dd/yyyy')";
	      
	      //int idNegeri = 1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Semakanhantar", idSemakanhantar);
	      r.add("id_semakansenarai", idSemakan);
	      r.add("id_permohonan", idPermohonan);
	      r.add("catatan", txtbox);
	      //r.add("tarikh_pelbagai", tarikh_resit);
	      r.add("tarikh_masuk",r.unquote("sysdate")); 
	      sql = r.getSQLInsert("tblsemakanhantar");
	      
	      stmt.executeUpdate(sql);
	    } finally {
	      if (db != null) db.close();
	    }
	  }
}
