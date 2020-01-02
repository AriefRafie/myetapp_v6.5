package ekptg.model.online;

import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniPembayaranData {
	
public static void addPembayaran(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";
	   
	    
	    Date now = new Date();
	    try
	    {	 
	    	long idBayaran = DB.getNextID("TBLPEMBAYARANONLINE_SEQ");
	    	String modBayaran = (String)data.get("mod_Pembayaran");
	    	String noAkaunBil = (String)data.get("no_Akaun_Bil");
	    	String idJenisbayaran = (String)data.get("id_Jenisbayaran");
	    	String amaunBayaran = (String)data.get("amaun_Bayaran");
	    	
	    	 db = new Db();
		     Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();
		     
		     r = new SQLRenderer();
		     r.add("id_Pembayaranonline",idBayaran);
		     r.add("mod_Pembayaran",modBayaran);
		     r.add("no_Akaun_Bil", noAkaunBil);
		     r.add("id_Jenisbayaran",idJenisbayaran);
		     r.add("amaun_Bayaran", amaunBayaran);
		     
		     sql = r.getSQLInsert("tblpembayaranonline");
		     stmt.executeUpdate(sql);
	    	
	    }finally {
		      if (db != null) db.close();
	    }
	}

}
