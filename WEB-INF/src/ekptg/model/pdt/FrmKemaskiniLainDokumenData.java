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

public class FrmKemaskiniLainDokumenData {
	static Logger myLog = Logger.getLogger(FrmKemaskiniLainDokumenData.class);
	 private static Vector list = new Vector();
	 private static Vector senaraiLampiran = new Vector();
	 public static String idTag = "";

	 public String add(Hashtable data) throws Exception{
		 Db db = null;
		 String sql = "";  
		 Date now = new Date();
		 try {	 
			 long idDokumen = DB.getNextID("TBLPDTDOKUMEN_SEQ");
			 String noRujDokumen = (String)data.get("no_Rujukan_Dokumen");
			 String tajukDokumen = (String)data.get("tajuk_Dokumen");
			 String idJenisDokumen = (String)data.get("id_Jenisdokumen");
			 String tarikhDokumen = (String)data.get("tarikh_Dokumen");
			 String tkhDokumen = "to_date('" + tarikhDokumen + "','dd/MM/yyyy')";
			 String idSeksyen = (String)data.get("id_Seksyen");
			 String idFail = (String)data.get("id_Fail");
			 String catatan = (String)data.get("catatan");
			 String noFail = (String)data.get("noFail");
			 String tagDokumen = (String)data.get("tagDokumen");
			      
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();			      
			 r.add("id_Dokumen",idDokumen);
			 r.add("no_Dokumen",noRujDokumen);
			 r.add("JENIS_DOKUMEN",noRujDokumen);
			 r.add("tajuk_Dokumen", tajukDokumen);
			 r.add("id_Jenisdokumen", idJenisDokumen);
			 r.add("tarikh_Dokumen", r.unquote(tkhDokumen));
			 r.add("id_Seksyen", idSeksyen);
			 r.add("id_Fail", idFail);
			 r.add("catatan", catatan);
			 r.add("no_Fail", noFail);
			 r.add("tagDokumen", tagDokumen);
			 r.add("tarikh_Daftar",r.unquote("sysdate"));
			 r.add("tarikh_masuk",r.unquote("sysdate"));
			 sql = r.getSQLInsert("tblpdtdokumen");	
			// myLog.debug("simpan sql :::::::::::::" + sql);
			 System.out.println("simpan--"+sql);
			 stmt.executeUpdate(sql);
			      
			 //String tagDokumen = (String)data.get("tagDokumen");
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
					 idTag = String.valueOf(idTagDokumen);
						
				 }else{
					long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
					r.add("ID_RUJTAG",idTagDokumenBaru);
					r.add("ID_DOKUMEN",idDokumen);
					r.add("TAG_DOKUMEN",tagDokumen);
					r.add("SUMBER","DOKUMEN");
					r.add("TARIKH_MASUK",r.unquote("sysdate")); 
					r.add("ID_MASUK",data.get("idMasuk"));
					sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
					idTag = String.valueOf(idTagDokumenBaru);
						
				 }
				 stmt.executeUpdate(sql);

			 }	
			 return String.valueOf(idDokumen);
		    
		 }catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
		    
		 }finally {
		    if (db != null) db.close();
		 }
		    
	 }
	 
	 public void update(Hashtable data) throws Exception {
		 Db db = null;
		 String sql = "";		 
		 try {
		    	  String idDokumen = (String)data.get("id_Dokumen");
		    	  
		    	  String noRujDokumen = (String)data.get("no_Rujukan_Dokumen");
		    	  String tajukDokumen = (String)data.get("tajuk_Dokumen");
		    	  String idJenisDokumen = (String)data.get("id_Jenisdokumen");
		    	  String tarikhDokumen = (String)data.get("tarikh_Dokumen");
		    	  String tkhDokumen = "to_date('" + tarikhDokumen + "','dd/MM/yyyy')";
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String noFail = (String)data.get("no_Fail");
			      String catatan = (String)data.get("catatan");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();			      
			      r.update("id_Dokumen",idDokumen);
			      r.add("no_Dokumen",noRujDokumen);
			      r.add("JENIS_DOKUMEN",noRujDokumen);
			      r.add("tajuk_Dokumen", tajukDokumen);
			      r.add("id_Jenisdokumen", idJenisDokumen);
			      r.add("tarikh_Dokumen", r.unquote(tkhDokumen));
			      r.add("id_Seksyen", idSeksyen);
			      r.add("no_Fail", noFail);
			      r.add("catatan", catatan);			      
			      sql = r.getSQLUpdate("tblpdtdokumen");
			      myLog.info("tblpdtdokumen ::::::::::: "+sql);
			      stmt.executeUpdate(sql);
			      
					String tagDokumen = (String)data.get("tagDokumen");
					String idTagDokumen = (String)data.get("idTagDokumen");
					myLog.info(tagDokumen+","+idTagDokumen);
					if(!tagDokumen.equals("")){
						db = new Db();
						stmt = db.getStatement();
					    r = new SQLRenderer();			      
						if(!idTagDokumen.equals("0")){
							r.update("ID_RUJTAG", data.get("idTagDokumen"));
							r.add("ID_KEMASKINI", data.get("idMasuk"));
							r.add("TAG_DOKUMEN",tagDokumen);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							myLog.info("getSQLUpdate TBLRUJTAGDOKUMEN ::::::::::: "+sql);
							sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
						
						}else{
							long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
							r.add("ID_RUJTAG",idTagDokumenBaru);
							r.add("ID_DOKUMEN",idDokumen);
							r.add("TAG_DOKUMEN",tagDokumen);
							r.add("SUMBER","DOKUMEN");
							r.add("TARIKH_MASUK",r.unquote("sysdate")); 
							r.add("ID_MASUK",data.get("idMasuk"));
							myLog.info("getSQLInsert TBLRUJTAGDOKUMEN ::::::::::: "+sql);
							sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
						
						}
					    stmt.executeUpdate(sql);

					}

   
		    }catch (Exception re) {
				myLog.error("Error: ", re);
				 throw re;
				 }
			    
		 	finally {
		    	if (db != null) db.close();
		    }
		  
	 }
	 
	 
	 public void setData(String id)throws Exception {
		 Db db = null;
		 list.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.no_Dokumen");
		      r.add("a.JENIS_DOKUMEN");
		      r.add("a.tajuk_Dokumen");
		      r.add("a.tarikh_Dokumen");
		      r.add("c.id_Seksyen");
		      r.add("a.no_Fail");
		      r.add("a.catatan");
		      r.add("a.tarikh_Daftar");
		      r.add("NVL((SELECT TAG_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_DOKUMEN AND SUMBER='DOKUMEN' ),'') TAG_DOKUMEN");
		      r.add("NVL((SELECT ID_RUJTAG FROM TBLRUJTAGDOKUMEN WHERE ID_DOKUMEN=A.ID_DOKUMEN AND SUMBER='DOKUMEN' ),'0') ID_DOKUMEN");
		      r.add("NVL(A.ID_JENISDOKUMEN,'0') JENIS_DOKUMEN");
		      
		      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen(+)"));
		      //r.add("a.id_Fail",r.unquote("d.id_Fail(+)"));
		      //--------
		      r.add("a.id_Dokumen",id);
		      
		      //sql = r.getSQLSelect("Tblpdtdokumen a, Tblrujjenisdokumen b,Tblrujseksyen c, Tblpfdfail d");
		      sql = r.getSQLSelect("Tblpdtdokumen a, Tblrujseksyen c");
		      myLog.debug(sql);
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;		      
		      while (rs.next()) {
		    	  h = new Hashtable();	    	  
		    	  h.put("idDokumen", rs.getString("id_Dokumen"));
		    	  //h.put("noRujDok", rs.getString("no_Dokumen")== null?"": rs.getString("no_Dokumen"));
		    	  h.put("noRujDok", rs.getString("JENIS_DOKUMEN")== null?"": rs.getString("JENIS_DOKUMEN"));
		    	  h.put("tjkDok",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  h.put("tarikhDokumen",rs.getDate("tarikh_Dokumen")== null?"":sdf.format(rs.getDate("tarikh_Dokumen")));		    	  
		    	  h.put("idSeksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("noFail",Utils.isNull(rs.getString("no_fail")));
		    	  h.put("catatan", rs.getString("catatan")== null?0:rs.getString("catatan"));
		    	  h.put("tarikhDaftar",rs.getDate("tarikh_Daftar") == null?"":sdf.format(rs.getDate("tarikh_Daftar")));
		    	  h.put("tagging", Utils.isNull(rs.getString(10)));
		    	  h.put("idTagging", Utils.isNull(rs.getString(11)));
		    	  h.put("idJenisDokumen", Utils.isNull(rs.getString(12)));
		    	  list.addElement(h);
		    	  
		      }
		      
		 }catch (Exception re) {
				myLog.error("Error: ", re);
				 throw re;
				 }
			    finally {
		      if (db != null) db.close();
		 }  
	 
	 }
	 
	 public Vector getData(){
		 
		  return list;
	  }
	 public void  setListLampiran(String id)throws Exception {
		    Db db = null;
		    senaraiLampiran.clear();
		    String sql = "";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Lampiran");
		      r.add("a.id_Dokumen");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		     
		     
		      
		      r.add("a.id_Dokumen",id);
		      r.add("a.id_Dokumen",r.unquote("b.id_Dokumen"));
		
		      sql = r.getSQLSelect("Tblpdtrujlampiran a,Tblpdtdokumen b","a.id_Lampiran asc");
		      myLog.info("SQL List Lampiran:: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_Lampiran",rs.getString("id_Lampiran"));
		    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
		    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	 
		    	  senaraiLampiran.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      /*
		       if (count == 0){
		       
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("id_Lampiran","");
		    	  h.put("id_Dokumen", "");
		    	  h.put("nama_Fail", "Tiada rekod.");
		    	  h.put("jenis_Mime","");
		    	
		    	  senaraiLampiran.addElement(h);
		      }
		      */
		      //return list;
		    } catch (Exception re) {
				myLog.error("Error: ", re);
				 throw re;
				 }
			    finally {
		      if (db != null) db.close();
		    }
		}
		public Vector getListLampiran(){
				 
			return senaraiLampiran;
		}
		      
		public void hapus(String idLampiran) throws Exception {
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
			
				
				//TBLPFDRUJLAMPIRAN
				r.add("ID_LAMPIRAN", idLampiran);
				sql = r.getSQLDelete("TBLPDTRUJLAMPIRAN");
				stmt.executeUpdate(sql);
				
			

			}catch (Exception re) {
				myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
				if (db != null)
					db.close();
			}
		}


		
		}
