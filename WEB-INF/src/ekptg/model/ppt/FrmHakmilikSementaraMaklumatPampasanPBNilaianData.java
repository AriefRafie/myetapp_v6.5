package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmHakmilikSementaraMaklumatPampasanPBNilaianData {
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraMaklumatPampasanPBNilaianData.class);
	
	static Vector paparJPPH = null;
	static Vector paparAmaun = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
public static void setDataJPPH(String idPermohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparJPPH = new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
//		    sql = "SELECT A.NO_RUJUKANSURATJT,A.TARIKH_SURATJT,A.TARIKH_TERIMAJT FROM TBLPPTULASANTEKNIKAL A WHERE ID_JABATANTEKNIKAL = 13 AND ID_PERMOHONAN = '"+idPermohonan+"'";
		    sql = "SELECT A.NO_RUJUKANSURATJT,A.TARIKH_SURATJT,A.TARIKH_TERIMAJT FROM TBLPPTULASANTEKNIKAL A WHERE ID_PERMOHONAN = '"+idPermohonan+"'";
		    myLogger.info("setDataJPPH >> "+sql);
		   
		    
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("NO_RUJUKANSURATJT", rs.getString("NO_RUJUKANSURATJT")==null?"":rs.getString("NO_RUJUKANSURATJT"));
		    	  h.put("TARIKH_SURATJT", rs.getString("TARIKH_SURATJT")==null?"":sdf.format(rs.getDate("TARIKH_SURATJT")));
		    	  h.put("TARIKH_TERIMAJT", rs.getString("TARIKH_TERIMAJT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMAJT")));
		    	  paparJPPH.addElement(h);
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}
	public static Vector getDataJPPH(){
		return paparJPPH;
	}

	public static void setAmaun(String idSiasatan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			paparAmaun= new Vector();
			db = new Db();
		    Statement stmt = db.getStatement();
		    
		    sql = "SELECT NILAIAN_JPPH FROM TBLPPTSIASATAN WHERE ID_SIASATAN = '"+idSiasatan+"'";
			myLogger.info("SQL SET AMAUN :: "+sql);
		    ResultSet rs = stmt.executeQuery(sql);
		     
		     Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
//		    	  h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH")==null?"":rs.getString("NILAIAN_JPPH"));
		    	  h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH")==null?"0":Double.parseDouble(rs.getString("NILAIAN_JPPH")));
		    	  paparAmaun.addElement(h);
		      }
			
		}
		finally {
			if(db != null) db.close();
		}

		
	}
	public static Vector getAmaun(){
		return paparAmaun;
	}
	
	public static void updateNilaian(Hashtable data,String id_permohonan)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			String idSiasatan = (String)data.get("idSiasatan");
			String amaun = (String)data.get("nilaianJPPH");
			String idKemaskini = (String)data.get("idKemaskini");
			
			String txtNoRujSurat = (String)data.get("txtNoRujSurat");
			String txdTkhTerimaSurat = (String)data.get("txdTkhTerimaSurat");
			String txdTkhSurat = (String)data.get("txdTkhSurat");
			
			String TTS = "to_date('" + txdTkhTerimaSurat + "','dd/MM/yyyy')";
			String TS = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')";
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("id_Siasatan",idSiasatan);
			r.add("nilaian_Jpph",amaun);
			r.add("id_Kemaskini",idKemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			myLogger.info("update nilaian :: "+sql);
			stmt.executeUpdate(sql);
			
			r.clear();
			
		    //TBLPPTULASANTEKNIKAL
		    r.update("id_permohonan", id_permohonan);

		    r.add("id_jabatanteknikal",13);
			r.add("no_rujukansuratjt",txtNoRujSurat); 
			r.add("tarikh_suratjt",r.unquote(TS));
			r.add("tarikh_terimajt",r.unquote(TTS));
			r.add("id_kemaskini",idKemaskini);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
			sql = r.getSQLUpdate("Tblpptulasanteknikal");
			myLogger.info("UPDATE  Tblpptulasanteknikal ::-> "+sql);	
			stmt.executeUpdate(sql);				
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}
	
	public static void simpanNilaian(Hashtable data)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			
			String idSiasatan = (String)data.get("idSiasatan");
			String amaun = (String)data.get("nilaianJPPH");
			String idKemaskini = (String)data.get("idKemaskini");
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
			r.add("nilaian_Jpph",amaun);
			r.add("id_masuk",idKemaskini);
			r.add("tarikh_masuk",r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptsiasatan");
			stmt.executeUpdate(sql);
			
		}
		finally {
			if(db != null) db.close();
		}
		
	}	
}
