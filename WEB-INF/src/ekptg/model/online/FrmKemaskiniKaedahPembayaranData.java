package ekptg.model.online;

import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmKemaskiniKaedahPembayaranData {
	
public static void addKaedahPembayaran(Hashtable data) throws Exception{
		
		Db db = null;
	    String sql = "";

	    Date now = new Date();
	    try 
	    {	 
	    	int idBayaran = (Integer)data.get("id_Pembayaranonline");
	    	String kaedahPembayaran = (String)data.get("kaedah_Pembayaran");
	    	String idBank = (String)data.get("id_Bank");
	    	String noAkaunPembayar = (String)data.get("no_Akaun_Pembayar");
	    	String namaPemegangAkaun = (String)data.get("nama_Pemegang_Akaun");
	    	String noAkaunPenerima = (String)data.get("no_Akaun_Penerima");
	    	String emel = (String)data.get("emel");
	    	
	    	
	    	 db = new Db();
		     Statement stmt = db.getStatement();
		     SQLRenderer r = new SQLRenderer();
		     
		     r = new SQLRenderer();
		     r.update("id_Pembayaranonline",idBayaran);
		     r.add("kaedah_Pembayaran",kaedahPembayaran);
		     r.add("id_Bank", idBank);
		     r.add("no_Akaun_Pembayar",noAkaunPembayar);
		     r.add("nama_Pemegang_Akaun",namaPemegangAkaun);
		     r.add("no_Akaun_Penerima", noAkaunPenerima);
		     r.add("emel", emel);
		     
		     sql = r.getSQLUpdate("tblpembayaranonline");
		     stmt.executeUpdate(sql);
		    
	    	
	    }finally {
		      if (db != null) db.close();
	    }
	}


}
