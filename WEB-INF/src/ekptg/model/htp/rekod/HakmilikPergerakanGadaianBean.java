package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class HakmilikPergerakanGadaianBean implements IHakmilikPergerakan{
	
	private static Logger myLog = Logger.getLogger(HakmilikPergerakanGadaianBean.class);
	private static Vector<Hashtable<String,String>> senaraiPergerakanHakmilik = null;
	private Db db = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// PAPAR PERGERAKAN HAKMILIK BY ID
	@Override
	public Vector<Hashtable<String,String>> getSenaraiPergerakanHakmilik(String id) throws Exception {

		try {
			db = new Db();
			senaraiPergerakanHakmilik = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PERGERAKAN, A.TARIKH, UPPER(A.KETERANGAN) AS KETERANGAN" +
					", A.BIL_SALINAN, A.STATUS, UPPER(A.KEPADA) AS KEPADA, A.TARIKH_KEMBALI, "+
			  	  "CASE "+
			  	  	"WHEN A.STATUS = 'D' THEN 'DALAMAN' "+
			  	  	"WHEN A.STATUS = 'L' THEN 'LUARAN' "+
			  	  "END AS STATUS_PINJAMAN "+
			  	  "FROM TBLHTPPERGERAKAN A, TBLHTPHAKMILIKURUSAN B "+
			  	  "WHERE B.ID_HAKMILIKURUSAN = A.ID_HAKMILIK " +
			  	  " AND A.FLAG_PILIHAN = 'HG' "+
			  	  "AND A.ID_HAKMILIK = ' "+id +"'"+
			  	  "ORDER BY A.TARIKH DESC";
			//myLog.info("senarai pergerakan hakmilik : "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
		    int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idPergerakan", rs.getString("ID_PERGERAKAN"));
				h.put("tarikh", rs.getString("TARIKH")==null ? "" :sdf.format(rs.getDate("TARIKH")));
				h.put("keterangan", rs.getString("KETERANGAN")==null ? "" :rs.getString("KETERANGAN"));
				h.put("bilSalinan", rs.getString("BIL_SALINAN")==null ? "":rs.getString("BIL_SALINAN"));
				h.put("status", rs.getString("STATUS_PINJAMAN")==null ? "":rs.getString("STATUS_PINJAMAN"));
				h.put("kepada", rs.getString("KEPADA")==null ? "":rs.getString("KEPADA"));
				h.put("tarikhKembali", rs.getString("TARIKH_KEMBALI")==null ? "" :sdf.format(rs.getDate("TARIKH_KEMBALI")));
				senaraiPergerakanHakmilik.addElement(h);
		        bil++;
		    	count++;
		    	
		   }
		   if (count == 0){
		        h = new Hashtable<String,String>();
		    	h.put("bil","");
		    	h.put("idPergerakan","0");
		        h.put("tarikh", "");
		    	h.put("keterangan", "Tiada rekod.");
		        h.put("bilSalinan", "");
		    	h.put("status", "");
		    	h.put("kepada", "");
		    	h.put("tarikhKembali", "");
		    	senaraiPergerakanHakmilik.addElement(h);
		    	
		  }
		}
		finally {
			if (db != null)
				db.close();
	    }
		return senaraiPergerakanHakmilik;
	
	}
	// ADD PERGERAKAN
	@Override
	public String addPergerakan(Hashtable<String,String> data) throws Exception {
		Connection conn = null;
		//Date date = new Date(); 
		//String currentDate = sdf.format(date);
		//String idHakmilikAsal =  (String)data.get("idHakmilik");
		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    long idPergerakan = DB.getNextID("TBLHTPPERGERAKAN_SEQ"); 
		    r.add("ID_PERGERAKAN",idPergerakan);
		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
		    r.add("KETERANGAN",data.get("keterangan"));
		    r.add("KEPADA",data.get("kepada"));
			//convert date before add
			String tarikhSerahan = (String)data.get("tarikhSerah");
			String txdTarikhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
		    r.add("TARIKH", r.unquote(txdTarikhSerahan));
		    r.add("STATUS",data.get("socStatusS"));
		    r.add("BIL_SALINAN",data.get("bilSalinan")); 
			//convert date before add
			String tarikhKembali = (String)data.get("txdTarikhKembali");
			String txdTarikhKembali = "to_date('" + tarikhKembali + "','dd/MM/yyyy')";
	        if (tarikhKembali !=null && !tarikhKembali.equals("")){
				r.add("TARIKH_KEMBALI", r.unquote(txdTarikhKembali));	
			}
		    r.add("CATATAN",data.get("txtCatatan")); 
		    r.add("NO_FAIL",data.get("failRujukanPer")); 
		    r.add("TAJUK_FAIL",data.get("txTajukPer")); 
		    r.add("FLAG_PILIHAN",data.get("sorDokumen")); 
		    r.add("ID_MASUK",data.get("idKemaskini")); 
		    r.add("TARIKH_MASUK",r.unquote("SYSDATE")); 
		    sql = r.getSQLInsert("TBLHTPPERGERAKAN");
		    myLog.info("sql insert baru "+sql);
			stmt.executeUpdate(sql);
		
			conn.commit();
		    return String.valueOf(idPergerakan);
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
	    }
		
	}
	// UPDATE PERGERAKAN
	@Override
	public void updatePergerakan(Hashtable<String,String> data) throws Exception {
		Connection conn = null;
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
		    r.update("ID_PERGERAKAN",data.get("idPergerakan"));
		    r.update("FLAG_PILIHAN","HG");
		    r.add("ID_HAKMILIK",data.get("idHakmilik"));
		    r.add("KETERANGAN",data.get("keterangan"));
		    r.add("KEPADA",data.get("kepada"));
			//convert date before add
			String tarikhSerahan = (String)data.get("tarikhSerah");
			String txdTarikhSerahan = "to_date('" + tarikhSerahan + "','dd/MM/yyyy')";
		    r.add("TARIKH", r.unquote(txdTarikhSerahan));
		    r.add("STATUS",data.get("socStatus"));
		    r.add("BIL_SALINAN",data.get("bilSalinan"));
			//convert date before add
			String tarikhKembali = (String)data.get("txdTarikhKembali");
		    myLog.info("tarikhKembali : "+tarikhKembali);
			String txdTarikhKembali = "to_date('" + tarikhKembali + "','dd/MM/yyyy')";
	        if (tarikhKembali !=null && !tarikhKembali.equals("")){
				r.add("TARIKH_KEMBALI", r.unquote(txdTarikhKembali));	
			}else{
				r.add("TARIKH_KEMBALI", tarikhKembali);				
			}
	        
		    r.add("CATATAN",data.get("txtCatatan")); 
		    r.add("FLAG_PILIHAN",data.get("sorDokumen"));
		    r.add("ID_KEMASKINI",data.get("idKemaskini"));
		    r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE")); 
		    r.add("NO_FAIL",data.get("failRujukanPer")); 
		    r.add("TAJUK_FAIL",data.get("txTajukPer")); 		    
		    sql = r.getSQLUpdate("TBLHTPPERGERAKAN");
		    myLog.info("sql update baru "+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
		    
		}catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	    

	}
	// PAPAR MAKLUMAT PERGERAKAN BY ID
	@Override
	public Hashtable<String,String> getMaklumatPergerakan(String id) throws Exception {
		Hashtable<String,String> h = null;
		try {
			db = new Db();
			senaraiPergerakanHakmilik = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();			
			sql ="SELECT A.ID_PERGERAKAN, A.ID_HAKMILIK, A.FLAG_PILIHAN, A.KEPADA, A.KETERANGAN" +
					" , A.STATUS, A.BIL_SALINAN, A.TARIKH, A.TARIKH_KEMBALI, A.CATATAN " +
					" ,A.NO_FAIL,  A.TAJUK_FAIL " +
					" FROM TBLHTPPERGERAKAN A " +
					" WHERE A.ID_PERGERAKAN = '"+id +"'";		
			myLog.info("papar detail pergerakan "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idPergerakan", rs.getString("ID_PERGERAKAN"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK"));
				h.put("sorDokumen", rs.getString("FLAG_PILIHAN")==null ? "":rs.getString("FLAG_PILIHAN"));
				h.put("kepada", rs.getString("KEPADA")==null ? "":rs.getString("KEPADA"));
				h.put("tarikhSerahan", rs.getString("TARIKH")==null ? "	" :sdf.format(rs.getDate("TARIKH")));
				h.put("bilSalinan", rs.getString("BIL_SALINAN")==null ? "" :rs.getString("BIL_SALINAN"));
				h.put("keterangan", rs.getString("KETERANGAN")==null ? "":rs.getString("KETERANGAN"));
				h.put("socStatusS", rs.getString("STATUS")==null ? "":rs.getString("STATUS"));
				h.put("tarikhKembali", rs.getString("TARIKH_KEMBALI")==null ? "" :sdf.format(rs.getDate("TARIKH_KEMBALI")));
				h.put("catatan", rs.getString("CATATAN")==null ? "":rs.getString("CATATAN"));
				h.put("failRujukanPer", Utils.isNull(rs.getString("NO_FAIL")));
				h.put("txTajukPer", Utils.isNull(rs.getString("TAJUK_FAIL")));			
				//senaraiPergerakanHakmilik.addElement(h);
				
			}
			
		} finally {
			if (db != null)
				db.close();
		
		}
		return h;
		
	}	
	@Override
	public void hapus(String id) throws Exception {
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PERGERAKAN",id);
			r.add("FLAG_PILIHAN","HG");
			sql = r.getSQLDelete("TBLHTPPERGERAKAN");
			stmt.executeUpdate(sql);
			/// DELETE MAKLUMAT PERGERAKAN

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	
}
