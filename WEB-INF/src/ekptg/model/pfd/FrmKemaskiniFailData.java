package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniFailData {
	
	private static Vector list = new Vector();
	private static Vector vIdFailArkib = null;
	public static void setData(int id)throws Exception {
		
		 Db db = null;
		 list.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("b.id_Negeri");
		      r.add("c.id_Seksyen");
		      r.add("d.id_Urusan");
		      r.add("e.id_Suburusan");
		      r.add("f.id_Tarafkeselamatan");
		      r.add("a.tajuk_Fail");
		      r.add("a.id_Status");
		      r.add("h.id_Lokasifail");
		      r.add("a.faharasat");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("g.keterangan");
		      r.add("j.user_Name");
		      
		      r.add("a.id_Negeri",r.unquote("b.id_Negeri"));
		      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen"));
		      r.add("a.id_Urusan",r.unquote("d.id_Urusan(+)"));
		      r.add("a.id_Suburusan",r.unquote("e.id_Suburusan(+)"));
		      r.add("a.id_Tarafkeselamatan",r.unquote("f.id_Tarafkeselamatan(+)"));
		      r.add("a.id_Status",r.unquote("g.id_Status(+)"));
		      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
		      r.add("a.id_Masuk",r.unquote("j.user_Id(+)"));
		      
		      r.add("a.id_Fail",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujnegeri b, Tblrujseksyen c, Tblrujurusan d, Tblrujsuburusan e, Tblpfdrujtarafkeselamatan f, Tblrujstatus g, Tblpfdrujlokasifail h, users j");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail", rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  h.put("idNegeri",rs.getString("id_Negeri")== null?0:rs.getString("id_Negeri"));
		    	  h.put("idSeksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("idUrusan",rs.getString("id_Urusan")== null?0:rs.getString("id_Urusan"));
		    	  h.put("idSuburusan",rs.getString("id_Suburusan")== null?0:rs.getString("id_Suburusan"));
		    	  h.put("idTarafkeselamatan",rs.getString("id_Tarafkeselamatan")== null?0:rs.getString("id_Tarafkeselamatan"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("idStatus",rs.getString("id_Status")== null?0:rs.getString("id_Status"));
		    	  h.put("idLokasifail", rs.getString("id_Lokasifail")== null?0:rs.getString("id_Lokasifail"));
		    	  h.put("faharasat", rs.getString("faharasat")== null?0:rs.getString("faharasat"));
		    	  h.put("tarikhDaftar",rs.getDate("tarikh_Daftar_Fail") == null?"":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("keterangan",rs.getString("keterangan") == null?"":rs.getString("keterangan"));
		    	  h.put("user_Name", rs.getString("user_Name") == null?"":rs.getString("user_Name"));
		    	  list.addElement(h);
 
		      }
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
		    try
		    {
		    	  int idFail = (Integer)data.get("id_Fail");
			      String tajukFail = (String)data.get("tajuk_Fail");
				  String lokasi = (String)data.get("id_Lokasifail");
				  String faharasat = (String)data.get("id_Faharasat");
				  String idKemaskini = (String)data.get("id_Kemaskini");
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Fail", idFail);
				  r.add("tajuk_Fail", tajukFail);
				  r.add("id_Lokasifail", lokasi);
				  r.add("id_Faharasat", faharasat);
				  r.add("id_Kemaskini",idKemaskini);
				  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				
				  sql = r.getSQLUpdate("tblpfdfail");
			      stmt.executeUpdate(sql);
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	 		
	 	public static void updateFail(Hashtable h) throws Exception {
				 Db db = null;
				 String sql = "";
				 
				    try
				    {

				    	String idFail = (String)h.get("idFail");
				    	String noFail = (String)h.get("noFail");
					    String lokasi = (String)h.get("lokasiFail");
					    String faharasat = (String)h.get("faharasat");
					    String id_Kemaskini = (String)h.get("idMasuk");
				    				    	
				    	db = new Db();
					    Statement stmt = db.getStatement();
					    SQLRenderer r = new SQLRenderer();
					    r.update("ID_FAIL",idFail);
					    r.add("no_Fail", noFail);
					    r.add("lokasi_fail", lokasi);
					    r.add("faharasat", faharasat);
					    r.add("tarikh_Masuk",r.unquote("sysdate")); 
					    r.add("id_Masuk",id_Kemaskini);
		
					    sql = r.getSQLUpdate("TBLPFDFAIL");

					    stmt.executeUpdate(sql);
  	
				    }finally {
					      if (db != null) db.close();
				    }
				
			}
		public static void updateFailLama(Hashtable h) throws Exception {			 
			Db db = null;
			 String sql = "";
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 
			    try
			    {

			    	String idFail = (String)h.get("idFail");
			    	String noFail = (String)h.get("noFail");
			    	String noFailRoot = (String)h.get("noFailRoot");
			    	String noTurutan = (String)h.get("noTurutan");
			    	String noJilid = (String)h.get("noJilid");
			    	String noSubjaket = (String)h.get("noSubjaket");
				    String tajukFail = (String)h.get("tajukFailB");
				    String status = (String)h.get("selectStatusB");
				    String lokasi = (String)h.get("selectLokasiFailB");
				    String faharasat = (String)h.get("kabinet");
				    String tarikhDaftar = (String)h.get("tarikhDaftar");
				    String id_Kemaskini = (String)h.get("idMasuk");
			    				    	
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    
				    r.update("ID_FAIL",idFail);  
				    r.add("no_Fail", noFail);
				    r.add("no_Fail_Root", noFailRoot);
				    r.add("no_Turutan", noTurutan);
				    r.add("NO_TURUTAN_JLD", noJilid);
				    r.add("NO_TURUTAN_SUBJAKET", noSubjaket);		
				    r.add("tajuk_Fail", tajukFail);
				    r.add("id_Status", status);
				    r.add("id_lokasifail", lokasi);
				    r.add("faharasat", faharasat);
				  	r.add("tarikh_daftar_fail", r.unquote("to_date('"+ h.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
				    r.add("tarikh_kemaskini",r.unquote("sysdate")); 
				    r.add("id_Kemaskini",id_Kemaskini);
	
				    sql = r.getSQLUpdate("TBLPFDFAIL");
				    stmt.executeUpdate(sql);

		    	
			    }finally {
				      if (db != null) db.close();
			    }
		}
		public static void updateFailBaru(Hashtable h) throws Exception {
			 Db db = null;
			 String sql = "";
			 
			    try
			    {

			    	String idFail = (String)h.get("idFail");
				    String tajukFailB = (String)h.get("tajukFailB");
				    String status = (String)h.get("selectStatusB");
				    String lokasi = (String)h.get("selectLokasiFailB");
				    String faharasat = (String)h.get("kabinet");
				    String id_Kemaskini = (String)h.get("idMasuk");
			    				    	
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    r.update("ID_FAIL",idFail);
				    r.add("tajuk_Fail", tajukFailB);
				    r.add("id_Status", status);
				    r.add("id_lokasifail", lokasi);
				    r.add("faharasat", faharasat);
				    r.add("tarikh_kemaskini",r.unquote("sysdate")); 
				    r.add("id_Kemaskini",id_Kemaskini);
	
				    sql = r.getSQLUpdate("TBLPFDFAIL");
				    stmt.executeUpdate(sql);
			    	
			    }finally {
				      if (db != null) db.close();
			    }
		}
		
		public static void updateFailArkib(Hashtable h) throws Exception {
			 Db db = null;
			 String sql = "";
			 String sqlFindFailArkib = "";
			 
			    try
			    {

			    	long idFailMapping = DB.getNextID("TBLPFDFAILMAPPING_SEQ");
			    	String idFail = (String)h.get("idFail");
				    String tajukFailC = (String)h.get("tajukFailC");
//				    String status = (String)h.get("selectStatusC");
				    String lokasi = (String)h.get("selectLokasiFailC");
				    String faharasat = (String)h.get("kabinet");
				    String id_Kemaskini = (String)h.get("idMasuk");
				    String id_FailLama = (String)h.get("idFailLama");
			    				    	
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    r.update("ID_FAIL",idFail);
				      

				    r.add("tajuk_Fail", tajukFailC);
//				    r.add("id_Status", status);
				    r.add("id_lokasifail", lokasi);
				    r.add("faharasat", faharasat);
				    r.add("tarikh_kemaskini",r.unquote("sysdate")); 
				    r.add("id_Kemaskini",id_Kemaskini);
	
				    sql = r.getSQLUpdate("TBLPFDFAIL");
				    stmt.executeUpdate(sql);
				    
				    
				    
				    Vector idFailArkib = getIdFailArkib(idFail);
				   
				    
				    
				    
				    SQLRenderer rF = new SQLRenderer();
				  
				    if(id_FailLama != ""){
				    	
				    	
				    	
				    	if(idFailArkib.isEmpty()){
				    		
				    		 rF.add("id_Failmapping",idFailMapping);
						      rF.add("id_FailArkib",idFail);
						      rF.add("id_Failasal",id_FailLama);
						      rF.add("tarikh_kemaskini",rF.unquote("sysdate")); 
							  rF.add("id_Kemaskini",id_Kemaskini); 
						  
						      sql = rF.getSQLInsert("tblpfdfailmapping");
						    
						    
						      stmt.executeUpdate(sql);
						      
				    		
				    		
				    	}
				    	else{
				    		
				    		 
						      
						      Hashtable hA= (Hashtable)idFailArkib.get(0);
							     
						      rF.update("id_Failmapping",hA.get("ID_FAILMAPPING"));
						      rF.add("id_FailArkib",idFail);
						      rF.add("id_Failasal",id_FailLama);
						      rF.add("tarikh_kemaskini",rF.unquote("sysdate")); 
							  rF.add("id_Kemaskini",id_Kemaskini); 
						  
						      sql = rF.getSQLUpdate("tblpfdfailmapping");
						    
						    
						      stmt.executeUpdate(sql);
				    		
				    	}
				    	
				    	
				    
				    
				     
				    }
				      
		    }finally {
				      if (db != null) db.close();
			    }
		}

		
		public static Vector getIdFailArkib(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				vIdFailArkib = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT A.ID_FAILARKIB, A.ID_FAILMAPPING "+
					  "FROM TBLPFDFAILMAPPING A " +
					  "WHERE " +
					
					  "A.ID_FAILARKIB='"+idFail+"'";
				
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_FAILMAPPING",rs.getString("ID_FAILMAPPING")==null?"":rs.getString("ID_FAILMAPPING"));
					h.put("ID_FAILARKIB",rs.getString("ID_FAILARKIB")==null?"":rs.getString("ID_FAILARKIB"));
					
					
					
					vIdFailArkib.addElement(h);
					
				}
				
				
				return vIdFailArkib;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
	 

}
