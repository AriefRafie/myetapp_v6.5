package ekptg.model.htp;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class FrmRekodPendaftaranRizabData {
	public static void updateRizabById(Hashtable data) throws Exception {
	    Db dbHakmilik = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try
	    {
	    	  dbHakmilik = new Db();
			  Statement stmtHakmilik = dbHakmilik.getStatement();
			  SQLRenderer rHakmilik = new SQLRenderer();
			  rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	  //convert date before add
			  String tarikhTerima = data.get("txdTarikhTerima").toString();
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  	
			  rHakmilik.add("ID_LUAS", data.get("socLuas"));
			  rHakmilik.add("NO_PELAN", data.get("txtNoPelan"));
			  rHakmilik.add("NO_PU", data.get("txtNoPu"));
			  rHakmilik.add("NO_SYIT", data.get("txtNoSyit"));
			  rHakmilik.add("LOKASI", data.get("txtLokasi"));
			  rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	  
			  rHakmilik.add("STATUS_SAH", data.get("socStatus"));
	    	  //convert date before add
			  String tarikhKemaskini = data.get("txdTarikhKemaskini").toString();
			  String txdTarikhKemaskini = "to_date('" + tarikhKemaskini + "','dd/MM/yyyy')";		    	  
			  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote(txdTarikhKemaskini));			  

			  sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			  stmtHakmilik.executeUpdate(sql);
		 }
		 finally {
		    if (dbHakmilik != null) dbHakmilik.close();
		 }
		 Db dbHakmilikPerihal = new Db();
		 String sqlHakmilikPerihal = "";
		 try 
		 {
		     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
			 SQLRenderer rHakmilikPerihal = new SQLRenderer();
			 rHakmilikPerihal.update("ID_HAKMILIK", data.get("idHakmilik"));
			 rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			 sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			 stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);

		}
		finally {
			if (dbHakmilikPerihal != null)dbHakmilikPerihal.close();
		}		  
		 
	}
}
