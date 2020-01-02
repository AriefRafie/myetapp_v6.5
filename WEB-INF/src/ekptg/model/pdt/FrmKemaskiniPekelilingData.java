package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmKemaskiniPekelilingData {
	 private static Logger myLogger = Logger.getLogger(FrmKemaskiniPekelilingData.class);
	 private static Vector list = new Vector();
	 public static String idTag = "";
	 
	 public static String add(Hashtable data) throws Exception{		 
		 	Db db = null;
		    String sql = "";
		    Date now = new Date();		   
		    try {	 
		    	  long idPekeliling = DB.getNextID("TBLPDTPEKELILING_SEQ");
		    	  String bilPekeliling = (String)data.get("bil_Pekeliling");
		    	  String kategoriPekeliling = (String)data.get("id_Dokumenpekeliling");
		    	  String perkaraPekeliling = (String)data.get("id_Perkarapekeliling");
		    	  String tajukPekeliling = (String)data.get("tajuk_Pekeliling");
		    	  String tahun = (String)data.get("tahun");
		    	  String tarikhPekeliling = (String)data.get("tarikh_Pekeliling");
		    	  String tkhPekeliling = "to_date('" + tarikhPekeliling + "','dd/MM/yyyy')";
			      String tarikhKuatkuasa = (String)data.get("tarikh_Kuatkuasa");
			      String tkhKuatkuasa = "to_date('" + tarikhKuatkuasa + "','dd/MM/yyyy')";
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String idFail = (String)data.get("id_Fail");
			      String status = (String)data.get("id_Status");
			      String catatan = (String)data.get("catatan");
			      String idMasuk = (String)data.get("id_Masuk");
			      String noFail = (String)data.get("noFail");
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();		      
			      r.add("id_Pekeliling",idPekeliling);
			      r.add("bil_Pekeliling",bilPekeliling);
			      r.add("id_Dokumenpekeliling", kategoriPekeliling);
			      r.add("id_Perkarapekeliling", perkaraPekeliling);
			      r.add("tajuk_Pekeliling", tajukPekeliling);
			      r.add("tahun",tahun);
			      r.add("tarikh_Pekeliling", r.unquote(tkhPekeliling));
			      r.add("tarikh_Kuatkuasa", r.unquote(tkhKuatkuasa));
			      r.add("id_Seksyen", idSeksyen);
			      r.add("id_Fail", idFail);
			      r.add("id_Status", status);
			      r.add("catatan", catatan);
			      r.add("tarikh_Daftar",r.unquote("sysdate"));
			      r.add("id_Masuk",idMasuk);
			      r.add("no_fail",noFail);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      sql = r.getSQLInsert("tblpdtpekeliling");     
			      myLogger.info("ADD ::::::::: "+sql);
			      stmt.executeUpdate(sql);
			    
			      
			      String tagDokumen = (String)data.get("tagDokumen");
			      if(!tagDokumen.equals("")){
			    	  long idTagDokumen = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
			    	  db = new Db();
			    	  stmt = db.getStatement();
					  r = new SQLRenderer();			      
					  r.add("ID_RUJTAG",idTagDokumen);
					  r.add("ID_DOKUMEN",idPekeliling);
					  r.add("TAG_DOKUMEN",tagDokumen);
					  r.add("SUMBER","PEKELILING");
					  r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					  r.add("ID_MASUK",data.get("idMasuk"));
					  sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
					  stmt.executeUpdate(sql);
					  idTag = String.valueOf(idTagDokumen);
					  
			      }
			      return String.valueOf(idPekeliling);
	
		    }catch (Exception re) {
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			     finally {
			      if (db != null) db.close();
		    }
	 
	 }
	 
	 
	 
	 
	 public static void setData(String id)throws Exception {
		 Db db = null;
		 list.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.no_fail");
		      r.add("a.id_Pekeliling");
		      r.add("a.bil_Pekeliling");
		      r.add("b.id_Dokumenpekeliling");
		      r.add("c.id_Perkarapekeliling");
		      r.add("a.tajuk_Pekeliling");
		      r.add("a.tahun");
		      r.add("a.tarikh_Pekeliling");
		      r.add("a.tarikh_Kuatkuasa");
		      r.add("d.id_Seksyen");
		      r.add("e.id_Fail");
		      r.add("f.id_Status");
		      r.add("a.catatan");
		      r.add("a.tarikh_Daftar");
		      r.add("NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_PEKELILING AND SUMBER='PEKELILING'),'') TAG_DOKUMEN");
		      r.add("NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_PEKELILING AND SUMBER='PEKELILING'),'0') ID_DOKUMEN");
		      
		      r.add("a.id_Dokumenpekeliling",r.unquote("b.id_Dokumenpekeliling(+)"));
		      r.add("a.id_Perkarapekeliling",r.unquote("c.id_Perkarapekeliling(+)"));
		      r.add("a.id_Seksyen",r.unquote("d.id_Seksyen(+)"));
		      r.add("a.id_Fail",r.unquote("e.id_Fail(+)"));
		      r.add("a.id_Status",r.unquote("f.id_Status(+)"));
		      r.add("a.id_Pekeliling",id);	      
		      sql = r.getSQLSelect("Tblpdtpekeliling a, Tblpdtrujdokumenpekeliling b, " +
		      		"Tblpdtrujperkarapekeliling c, Tblrujseksyen d, Tblpfdfail e, " +
		      		"Tblrujstatus f, Tblrujkementerian g");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;	      
		      while (rs.next()) {
		    	  h = new Hashtable();	    	  
		    	  h.put("idPekeliling", rs.getString("id_Pekeliling"));
		    	  h.put("no_fail",rs.getString("no_fail")== null?"": rs.getString("no_fail"));
		    	  h.put("bilPekeliling", rs.getString("bil_Pekeliling")== null?"": rs.getString("bil_Pekeliling"));
		    	  h.put("idDokumenPekeliling",rs.getString("id_Dokumenpekeliling")== null?0:rs.getString("id_Dokumenpekeliling"));
		    	  h.put("idPerkaraPekeliling",rs.getString("id_Perkarapekeliling")== null?0:rs.getString("id_Perkarapekeliling"));
		    	  h.put("tjkPekeliling",rs.getString("tajuk_Pekeliling")== null?"":rs.getString("tajuk_Pekeliling"));
		    	  h.put("tahun",rs.getString("tahun")== null?"":rs.getString("tahun"));
		    	  h.put("tarikhPekeliling",rs.getDate("tarikh_Pekeliling")== null?"":sdf.format(rs.getDate("tarikh_Pekeliling")));
		    	  h.put("tkhKuatkuasa",rs.getDate("tarikh_Kuatkuasa")== null?"":sdf.format(rs.getDate("tarikh_Kuatkuasa")));
		    	  h.put("seksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("idFail",rs.getString("id_Fail")== null?0:rs.getString("id_Fail"));
		    	  h.put("statusPekeliling", rs.getString("id_Status")== null?0:rs.getString("id_Status"));
		    	  h.put("catatan", rs.getString("catatan")== null?"":rs.getString("catatan"));
		    	  h.put("tarikhDaftar",rs.getDate("tarikh_Daftar") == null?"":sdf.format(rs.getDate("tarikh_Daftar")));
		    	  h.put("tagging", Utils.isNull(rs.getString(14)));
		    	  h.put("idTagging", Utils.isNull(rs.getString(15)));
		    	  list.addElement(h);
		    	  
		      }
		     		     
		 } catch (Exception re) {
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			     finally {
		      if (db != null) db.close();
		    }  
		 
	 }
	 
	 public static Vector getData(){
		 
		  return list;
	  }
	 
	 
	 public static void update(Hashtable data) throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
		    	  String idPekeliling = (String)data.get("id_Pekeliling");
		    	  String bilPekeliling = (String)data.get("bil_Pekeliling");
		    	  String kategoriPekeliling = (String)data.get("id_Dokumenpekeliling");
		    	  String perkaraPekeliling = (String)data.get("id_Perkarapekeliling");
		    	  String tajukPekeliling = (String)data.get("tajuk_Pekeliling");
		    	  String tahun = (String)data.get("tahun");
		    	  String tarikhPekeliling = (String)data.get("tarikh_Pekeliling");
		    	  String tkhPekeliling = "to_date('" + tarikhPekeliling + "','dd/MM/yyyy')";
			      String tarikhKuatkuasa = (String)data.get("tarikh_Kuatkuasa");
			      String tkhKuatkuasa = "to_date('" + tarikhKuatkuasa + "','dd/MM/yyyy')";
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String idFail = (String)data.get("id_Fail");
			      String status = (String)data.get("id_Status");
			      String catatan = (String)data.get("catatan");
			      String idKemaskini = (String)data.get("id_Kemaskini");
			      
			      db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Pekeliling", idPekeliling);
				  r.add("bil_Pekeliling",bilPekeliling);
				  r.add("id_Dokumenpekeliling", kategoriPekeliling);
			      r.add("id_Perkarapekeliling", perkaraPekeliling);
			      r.add("tajuk_Pekeliling", tajukPekeliling);
			      r.add("tahun",tahun);
			      r.add("tarikh_Pekeliling", r.unquote(tkhPekeliling));
			      r.add("tarikh_Kuatkuasa", r.unquote(tkhKuatkuasa));
			      r.add("id_Seksyen", idSeksyen);
			      r.add("id_Fail", idFail);
			      r.add("id_Status", status);
			      r.add("catatan", catatan);
			      r.add("id_Kemaskini",idKemaskini);
				  r.add("tarikh_Kemaskini",r.unquote("sysdate"));   
			      sql = r.getSQLUpdate("tblpdtpekeliling");
			      myLogger.debug(sql);
			      stmt.executeUpdate(sql);
			      
			      String tagDokumen = (String)data.get("tagDokumen");
			      String idTagDokumen = (String)data.get("idTagDokumen");
			      if(!tagDokumen.equals("")){
			    	  db = new Db();
			    	  stmt = db.getStatement();
			    	  r = new SQLRenderer();			      
			    	  if(!idTagDokumen.equals("0")){
			    		  r.update("ID_RUJTAG", data.get("idTagDokumen"));
			    		  r.add("ID_KEMASKINI", data.get("idMasuk"));
			    		  r.add("TAG_DOKUMEN",tagDokumen);
			    		  r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			    		  sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
						
			    	  }else{
			    		  long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
			    		  r.add("ID_RUJTAG",idTagDokumenBaru);
			    		  r.add("ID_DOKUMEN",idPekeliling);
			    		  r.add("TAG_DOKUMEN",tagDokumen);
						  r.add("SUMBER","PEKELILING");
			    		  r.add("TARIKH_MASUK",r.unquote("sysdate")); 
			    		  r.add("ID_MASUK",data.get("idMasuk"));
			    		  sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
						
			    	  }
				      myLogger.info(sql);
			    	  stmt.executeUpdate(sql);

			      }
			     
		    }catch (Exception re) {
		    	myLogger.error("Error: ", re);
				 throw re;
				 }
			     finally {
		    	if (db != null) db.close();
		    }
	 
	 }

	 
	 
}
