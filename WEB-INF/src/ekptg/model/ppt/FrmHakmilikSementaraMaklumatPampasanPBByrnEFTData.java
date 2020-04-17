package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData {
	
	static Vector listEFT = null;
	static Vector paparEFT = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static String simpanEFT(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			long idBayaran = DB.getNextID("TBLPPTBAYARAN_SEQ");
			String idHakmilikPB = (String)data.get("idHakmilikPB");
			String noRujSuratEFT = (String)data.get("noRujSuratEFT");
			String tarikhSuratEFT = (String)data.get("tarikhSuratEFT");
			String tarikhTerimaEFT = (String)data.get("tarikhTerimaEFT");
			String noEFT = (String)data.get("noEFT");
			String amaunEFT = (String)data.get("amaunEFT");
			String tarikhBayaranEFT = (String)data.get("tarikhBayaranEFT");
			String idMasuk = (String)data.get("idMasuk");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("id_Bayaran",idBayaran);
			r.add("id_Hakmilikpb",idHakmilikPB);
			r.add("no_Rujukan_Surateft",noRujSuratEFT);
			r.add("tarikh_Surat_Eft",r.unquote("to_date('" + tarikhSuratEFT + "','dd/MM/yyyy')"));
			r.add("tarikh_Terima_Eft",r.unquote("to_date('" + tarikhTerimaEFT + "','dd/MM/yyyy')"));
			r.add("no_Eft",noEFT);
			r.add("amaun_Bayaran",amaunEFT);
			r.add("tarikh_Bayaran_Eft",r.unquote("to_date('" + tarikhBayaranEFT + "','dd/MM/yyyy')"));
			r.add("jenis_Award","1");
			r.add("cara_Bayar","2");
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptbayaran");
			stmt.executeUpdate(sql);
			
			return ""+idBayaran;
						
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void updateEFT(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			String idBayaran = (String)data.get("idBayaran");
			String noRujSuratEFT = (String)data.get("noRujSuratEFT");
			String tarikhSuratEFT = (String)data.get("tarikhSuratEFT");
			String tarikhTerimaEFT = (String)data.get("tarikhTerimaEFT");
			String noEFT = (String)data.get("noEFT");
			String amaunEFT = (String)data.get("amaunEFT");
			String tarikhBayaranEFT = (String)data.get("tarikhBayaranEFT");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Bayaran",idBayaran);
			r.add("no_Rujukan_Surateft",noRujSuratEFT);
			r.add("tarikh_Surat_Eft",r.unquote("to_date('" + tarikhSuratEFT + "','dd/MM/yyyy')"));
			r.add("tarikh_Terima_Eft",r.unquote("to_date('" + tarikhTerimaEFT + "','dd/MM/yyyy')"));
			r.add("no_Eft",noEFT);
			r.add("amaun_Bayaran",amaunEFT);
			r.add("tarikh_Bayaran_Eft",r.unquote("to_date('" + tarikhBayaranEFT + "','dd/MM/yyyy')"));
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptbayaran");
			stmt.executeUpdate(sql);
			
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void setListEFT(String idHakmilikPB)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			listEFT= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.ID_BAYARAN,A.NO_EFT,A.TARIKH_BAYARAN_EFT,A.AMAUN_BAYARAN" +
		    		" FROM TBLPPTBAYARAN A, TBLPPTHAKMILIKPB B" +
		    		" WHERE B.ID_HAKMILIKPB = A.ID_HAKMILIKPB AND A.CARA_BAYAR = '2' AND A.ID_HAKMILIKPB= '"+idHakmilikPB+"'";
			
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     int bil = 1;
		     int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("ID_BAYARAN", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	  h.put("TARIKH_BAYARAN_EFT", rs.getString("TARIKH_BAYARAN_EFT")==null?"":sdf.format(rs.getDate("TARIKH_BAYARAN_EFT")));
		    	  h.put("NO_EFT", rs.getString("NO_EFT")==null?"":rs.getString("NO_EFT"));
		    	  h.put("AMAUN_BAYARAN", rs.getString("NO_EFT")==null?"":Utils.format2Decimal(Double.parseDouble(rs.getString("AMAUN_BAYARAN"))));

		    	  bil++;
		    	  count++;
		    	  listEFT.addElement(h);
		    	  
		      }
		      
		      if(count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("ID_BAYARAN","");
		    	  h.put("TARIKH_BAYARAN_EFT", "");
		    	  h.put("NO_EFT", "Tiada rekod.");
		    	  h.put("AMAUN_BAYARAN", "");

		    	  listEFT.addElement(h);
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}

	public static Vector getListEFT(){
		return listEFT;
	}
	
	public static void setDataEFT(String idBayaran)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparEFT= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT A.ID_BAYARAN,A.NO_EFT,A.TARIKH_BAYARAN_EFT,A.AMAUN_BAYARAN," +
		    		" A.NO_RUJUKAN_SURATEFT,A.TARIKH_TERIMA_EFT,A.TARIKH_SURAT_EFT" +
		    		" FROM TBLPPTBAYARAN A, TBLPPTHAKMILIKPB B" +
		    		" WHERE B.ID_HAKMILIKPB = A.ID_HAKMILIKPB AND A.CARA_BAYAR IN ('2') AND A.ID_BAYARAN ='"+idBayaran+"'";
			
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_BAYARAN", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	  h.put("NO_EFT", rs.getString("NO_EFT")==null?"":rs.getString("NO_EFT"));
		    	  h.put("NO_RUJUKAN_SURATEFT", rs.getString("NO_RUJUKAN_SURATEFT")==null?"":rs.getString("NO_RUJUKAN_SURATEFT"));
		    	  h.put("TARIKH_TERIMA_EFT", rs.getString("TARIKH_TERIMA_EFT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_EFT")));
		    	  h.put("TARIKH_SURAT_EFT", rs.getString("TARIKH_SURAT_EFT")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_EFT")));
		    	  h.put("TARIKH_BAYARAN_EFT", rs.getString("TARIKH_BAYARAN_EFT")==null?"":sdf.format(rs.getDate("TARIKH_BAYARAN_EFT")));
		    	  h.put("AMAUN_BAYARAN", rs.getString("AMAUN_BAYARAN")==null?"":rs.getString("AMAUN_BAYARAN"));
		    	  paparEFT.addElement(h);
		    	  
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}

	public static Vector getDataEFT(){
		return paparEFT;
	}


}
