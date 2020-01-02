package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File_PFD;

public class FrmKemaskiniJilidData {
	
	private static Vector paparFail = new Vector();
	private static Vector paparFailLama = null;
	private static Vector paparFailBaru = null;
	private static Vector paparSemuaFail = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setDataFail(int id)throws Exception {
		
		 Db db = null;
		 paparFail.clear();
		 String sql = "";
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("a.no_Fail_Root");
		      r.add("a.tajuk_Fail");
		      r.add("b.id_Status");
		      r.add("c.id_Negeri");
		      r.add("d.id_Seksyen");
		      r.add("e.id_Urusan");
		      r.add("f.id_Suburusan");
		      r.add("g.id_Tarafkeselamatan");
		      r.add("h.id_Lokasifail");
		      r.add("a.faharasat");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("a.flag_Fail");
	
		      r.add("a.id_Fail",id);
		      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
		      r.add("a.id_Negeri",r.unquote("c.id_Negeri(+)"));
		      r.add("a.id_Seksyen",r.unquote("d.id_Seksyen(+)"));
		      r.add("a.id_Urusan",r.unquote("e.id_Urusan(+)"));
		      r.add("a.id_Suburusan",r.unquote("f.id_Suburusan(+)"));
		      r.add("a.id_Tarafkeselamatan",r.unquote("g.id_Tarafkeselamatan(+)"));
		      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
		      //r.add("a.id_Faharasat",r.unquote("i.id_Faharasat(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b, Tblrujnegeri c,Tblrujseksyen d, Tblrujurusan e, Tblrujsuburusan f, Tblpfdrujtarafkeselamatan g, Tblpfdrujlokasifail h");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
			      int noJilid = Integer.parseInt(rs.getString("flag_Fail")) + 1;
		    	  h.put("idFail",rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  
		    	  if (rs.getString("no_Fail_Root")== null){
			    	  h.put("noFailRoot", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
//			    	  h.put("noFailJilid", rs.getString("no_Fail")+ " JLD" + noJilid);

		    	  }
		    	  else if (rs.getString("no_Fail_Root")== "TIADA"){
		    		  h.put("noFailRoot", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  }
		    	  else{
			    	  h.put("noFailRoot", rs.getString("no_Fail_Root")==null?"":rs.getString("no_Fail_Root"));
			    	  h.put("noFailJilid", rs.getString("no_Fail_Root")+ " JLD" + noJilid);

		    	  }
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("id_Status",rs.getString("id_Status")== null?0:rs.getString("id_Status"));
		    	  h.put("id_Negeri", rs.getString("id_Negeri")== null?0:rs.getString("id_Negeri"));
		    	  h.put("id_Seksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("id_Urusan",rs.getString("id_Urusan")== null?0:rs.getString("id_Urusan"));
		    	  h.put("id_Suburusan", rs.getString("id_Suburusan") == null?0:rs.getString("id_Suburusan"));
		    	  h.put("id_Tarafkeselamatan", rs.getString("id_Tarafkeselamatan")== null?0: rs.getString("id_Tarafkeselamatan"));
		    	  h.put("id_Lokasifail",rs.getString("id_Lokasifail")== null?0:rs.getString("id_Lokasifail"));
		    	  h.put("faharasat",rs.getString("faharasat")== null?0:rs.getString("faharasat"));
		    	  h.put("tarikh_Daftar_Fail", sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("flag_Fail",noJilid);
		    	  paparFail.addElement(h);
		
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  

	}
	 public static Vector getDataFail(){
		 
		  return paparFail;
	  }
	 
	 //////////////////////////////////////////
	 private static Vector paparFailJilid = new Vector();
		
		public static void setDataFailJilid(int id)throws Exception {
			
			 Db db = null;
			 paparFailJilid.clear();
			 String sql = "";
	         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			 
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("a.id_Fail");
			      r.add("a.no_Fail");
			      r.add("a.no_Fail_Root");
			      r.add("a.tajuk_Fail");
			      r.add("b.id_Status");
			      r.add("c.id_Negeri");
			      r.add("d.id_Seksyen");
			      r.add("e.id_Urusan");
			      r.add("f.id_Suburusan");
			      r.add("g.id_Tarafkeselamatan");
			      r.add("h.id_Lokasifail");
			      r.add("a.faharasat");
			      r.add("a.tarikh_Daftar_Fail");
			      r.add("a.flag_Fail");
		
			      r.add("a.id_Fail",id);
			      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
			      r.add("a.id_Negeri",r.unquote("c.id_Negeri(+)"));
			      r.add("a.id_Seksyen",r.unquote("d.id_Seksyen(+)"));
			      r.add("a.id_Urusan",r.unquote("e.id_Urusan(+)"));
			      r.add("a.id_Suburusan",r.unquote("f.id_Suburusan(+)"));
			      r.add("a.id_Tarafkeselamatan",r.unquote("g.id_Tarafkeselamatan(+)"));
			      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
			   
			     
			    
			      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b, Tblrujnegeri c,Tblrujseksyen d, Tblrujurusan e, Tblrujsuburusan f, Tblpfdrujtarafkeselamatan g, Tblpfdrujlokasifail h");
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h;
			      
			     
			      while (rs.next()) {
			    	  h = new Hashtable();
				      int noJilid = Integer.parseInt(rs.getString("flag_Fail")) + 1;
			    	  h.put("idFail",rs.getString("id_Fail"));
			    	  h.put("noFail", rs.getString("no_Fail"));
			    	  
			    	  if (rs.getString("no_Fail_Root")== null){
				    	  h.put("noFailRoot", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
//				    	  h.put("noFailJilid", rs.getString("no_Fail")+ " JLD" + noJilid);

			    	  }
			    	  else if (rs.getString("no_Fail_Root")== "TIADA"){
			    		  h.put("noFailRoot", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
			    	  }
			    	  else{
				    	  h.put("noFailRoot", rs.getString("no_Fail_Root")==null?"":rs.getString("no_Fail_Root"));
				    	  h.put("noFailJilid", rs.getString("no_Fail_Root")+ " JLD" + noJilid);

			    	  }
			    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
			    	  h.put("id_Status",rs.getString("id_Status")== null?0:rs.getString("id_Status"));
			    	  h.put("id_Negeri", rs.getString("id_Negeri")== null?0:rs.getString("id_Negeri"));
			    	  h.put("id_Seksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
			    	  h.put("id_Urusan",rs.getString("id_Urusan")== null?0:rs.getString("id_Urusan"));
			    	  h.put("id_Suburusan", rs.getString("id_Suburusan") == null?0:rs.getString("id_Suburusan"));
			    	  h.put("id_Tarafkeselamatan", rs.getString("id_Tarafkeselamatan")== null?0: rs.getString("id_Tarafkeselamatan"));
			    	  h.put("id_Lokasifail",rs.getString("id_Lokasifail")== null?0:rs.getString("id_Lokasifail"));
			    	  h.put("faharasat",rs.getString("faharasat")== null?0:rs.getString("faharasat"));
			    	  h.put("tarikh_Daftar_Fail", sdf.format(rs.getDate("tarikh_Daftar_Fail")));
			    	  h.put("flag_Fail",noJilid);
			    	  paparFailJilid.addElement(h);
			
			      }
			     
			 }
			 finally {
			      if (db != null) db.close();
			    }  

		}
		 public static Vector getDataFailJilid(String new_id) throws Exception{
			 int id = Integer.parseInt(new_id); 
			 setDataFailJilid(id);
			 return paparFailJilid;
		  }
	 
	 
	 
	 ///////////////////////////////////////////
	 public static String add(Hashtable data)throws Exception {
			 Db db = null;
			 String sql = "";
			 long idFail = 0;
		    
		 try
		    {	 
				  idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			      String noFailRoot = (String)data.get("no_Fail_Root");
				  Integer negeri = (Integer)data.get("id_Negeri");
				  Integer seksyen = (Integer)data.get("id_Seksyen");
				  Integer urusan = (Integer)data.get("id_Urusan");
				  String suburusan = (String)data.get("id_Suburusan");
				  String subsuburusan = (String)data.get("id_Subsuburusan");
				  String taraf = (String)data.get("id_Tarafkeselamatan");
				  String tajukFail = (String)data.get("tajuk_Fail");
				  String status = (String)data.get("id_Status");
				  String lokasi = (String)data.get("id_Lokasifail");
				  String faharasat = (String)data.get("faharasat");
				  String tarikhDaftar = (String)data.get("tarikh_Daftar_Fail");
				  String tkhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
				  String flagFail = (String)data.get("flag_Fail");
				  
				  
				  Integer intsuburusan = Integer.parseInt(suburusan); 
			      //Integer intsubsuburusan = Integer.parseInt(subsuburusan);
				  
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      String a = noFailRoot + " JLD" + File_PFD.getSeqNoJilid(seksyen, urusan, 0, negeri,intsuburusan,0);
			      r.add("id_Fail",idFail);
			      r.add("no_Fail_Root", noFailRoot);
			      r.add("no_Fail",a);
			      r.add("id_Negeri", negeri);
			      r.add("id_Seksyen", seksyen);
			      r.add("id_Urusan", urusan);
			      r.add("id_Suburusan", suburusan);
			      r.add("id_Tarafkeselamatan", taraf);
			      r.add("tajuk_Fail", tajukFail);
			      r.add("id_Status", status);
			      r.add("id_Lokasifail", lokasi);
			      r.add("faharasat", faharasat);
			      r.add("flag_Fail", flagFail);
			      r.add("tarikh_Daftar_Fail", r.unquote(tkhDaftar));
			      
			      
			      sql = r.getSQLInsert("tblpfdfail");
			      
			     // System.out.println("save jilid---"+sql);
			     
			      stmt.executeUpdate(sql);
			    } finally {
			      if (db != null) db.close();
			    }
		String new_idfail = String.valueOf(idFail);	    
		return new_idfail;

	 }
	 public static void update(Hashtable data) throws Exception {
		 
		   	Db db = null;
		   	db = new Db();
		    String sql = "";
		    long idStatus = 0;
		    
		    String sql2 = "SELECT a.id_status,a.kod_status"+
		    " FROM tblrujstatus a"+
		    " WHERE a.id_seksyen = 6 AND a.kod_status = '10'";
		    
		    
		    Statement stmt = db.getStatement();
		    ResultSet rs = stmt.executeQuery(sql2);
		    rs.next();
		    idStatus = rs.getLong(1);
		    
		    try
		    {
		    	String idFailAsal = (String)data.get("id_FailAsal");
		    	
		    	
				
				SQLRenderer r = new SQLRenderer();
				r.update("id_Fail", idFailAsal);
				r.add("id_Status",idStatus);
				
				 sql = r.getSQLUpdate("tblpfdfail");
			     stmt.executeUpdate(sql);
		    }
		    finally {
			      if (db != null) db.close();
			    }
		 
	 }
	 
	 public static Vector getPaparSemuaFail(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparSemuaFail = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				/*sql = "SELECT ID_FAIL, ID_DAERAH, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, ID_SUBSUBURUSAN, ID_SUBSUBSUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, FLAG_VIEW_FILE " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";*/
				

				sql = "SELECT A.ID_NEGERI,A.ID_FAIL, A.ID_TARAFKESELAMATAN, A. ID_SEKSYEN, A.ID_AKTIVITI, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.ID_LOKASIFAIL, A.FAHARASAT, A.ID_STATUS, " +
					  "B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
					  "FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E " +
					  "WHERE " +
					  "A.ID_URUSAN = B.ID_URUSAN " +
					  "AND A.ID_SUBURUSAN = C.ID_SUBURUSAN " +
					  "AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN " +
					  "AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
					  "AND A.ID_FAIL='"+idFail+"'";
				
				
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFailAsal",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
					h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
					h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
					h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
					h.put("KOD_SUBSUBURUSAN",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
					h.put("NAMA_SUBSUBURUSAN",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
					h.put("KOD_SUBSUBSUBURUSAN",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
					h.put("NAMA_SUBSUBSUBURUSAN",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
					h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFailAsal",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					
					/*h.put("idFailAsal",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("idSubSuburusan",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
					h.put("idSubSubSuburusan",rs.getString("ID_SUBSUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBSUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFailAsal",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));*/
					
					
					paparSemuaFail.addElement(h);
					
				}
				
				
				return paparSemuaFail;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
	 
	 public static Vector getPaparFailLama(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFailLama = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, NO_TURUTAN, NO_TURUTAN_SUBJAKET, NO_TURUTAN_JLD, NO_FAIL_ROOT " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("noFailRoot",rs.getString("NO_FAIL_ROOT")==null?"":rs.getString("NO_FAIL_ROOT"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("noTurutan",rs.getString("NO_TURUTAN")==null?"":rs.getString("NO_TURUTAN"));
					h.put("noJilid",rs.getString("NO_TURUTAN_JLD")==null?"":rs.getString("NO_TURUTAN_JLD"));
					h.put("noSubjaket",rs.getString("NO_TURUTAN_SUBJAKET")==null?"":rs.getString("NO_TURUTAN_SUBJAKET"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					
					
					paparFailLama.addElement(h);
					
				}
				
				
				return paparFailLama;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
	 
	 public static Vector getPaparFailBaru(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFailBaru = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_DAERAH, ID_URUSAN, ID_SUBURUSAN,ID_SUBSUBURUSAN,ID_AKTIVITI,TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("idSubSubUrusan",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
					h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					
					
					paparFailBaru.addElement(h);
					
				}
				
				
				return paparFailBaru;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
	public static String simpanFailJilid(String idFail) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static int checkJilid(String idFailAsal) throws Exception {
		int returnValue = 0 ;
		
		Db db = new Db();

		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_FAIL",idFailAsal);
			r.add("NO_TURUTAN_JLD");
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);

		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getInt("NO_TURUTAN_JLD");
		      }
		      
		      return returnValue;
			
		} finally {
			
			if (db != null) db.close();
		}

	
	}
	public static int checkSubjaket(String idFailAsal) throws Exception {
		int returnValue = 0;
		
		Db db = new Db();

		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_FAIL",idFailAsal);
			r.add("NO_TURUTAN_SUBJAKET");
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);
			
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getInt("NO_TURUTAN_SUBJAKET");
		      }
		      
		      return returnValue;

		} finally {
			if (db != null) db.close();
		}

	}
	

	public static String checkNoRoot(String idFailAsal) throws Exception {
		
		String returnValue = "";
		
		Db db = new Db();

		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_FAIL",idFailAsal);
			r.add("NO_FAIL_ROOT");
			sql = r.getSQLSelect("TBLPFDFAIL");
			r.clear();
			rs = stmt.executeQuery(sql);
			
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("NO_FAIL_ROOT")==null?"":rs.getString("NO_FAIL_ROOT");
		      }
		      
		      return returnValue;

		} finally {
			if (db != null) db.close();
		}
	}
	public static String addJilid(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		try
			{
				
			 	long idFailJilid = DB.getNextID("TBLPFDFAIL_SEQ");
			 	String idFailAsal = (String)data.get("idFailAsal");
			 	//String taraf = (String)data.get("id_Tarafkeselamatan");
			    String idMasuk = (String)data.get("id_Masuk");
			    String noFailJilid = createNoFail(idFailAsal);
			    int jilid = checkJilid(idFailAsal);
			    int newJilid = jilid+1;
			    
			    	
			    db = new Db();
			    Statement stmt = db.getStatement();

			    String sqlInsert = "INSERT INTO TBLPFDFAIL (ID_FAIL,ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, ID_SUBSUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
				   				+"FLAG_FAIL, ID_STATUS, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
				   				+"FLAG_VIEW_FILE, ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN )";
			  
			    String sqlSelect = "SELECT '"+idFailJilid+"',ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN,ID_SUBSUBURUSAN, sysdate, TAJUK_FAIL, '"+noFailJilid+"', NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
				   				+"FLAG_FAIL, ID_STATUS, CATATAN, '"+idMasuk+"', sysdate, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
				   				+"FLAG_VIEW_FILE, ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, '"+newJilid+"', NO_TURUTAN_SUBJAKET, NO_TURUTAN "
				   				+"FROM TBLPFDFAIL WHERE ID_FAIL = '"+idFailAsal+"'";
			  


		      sql = sqlInsert+" "+sqlSelect;
		      
		      System.out.println("SQl simpan jilid--"+sql);
		      stmt.executeUpdate(sql);  
		      //42
		      
			  String sqlUpdate = "UPDATE TBLPFDFAIL SET ID_STATUS = '42' WHERE ID_FAIL = '"+idFailAsal+"'";
	   		
  
			  sql1 = sqlUpdate;
			  stmt.executeUpdate(sql1);  
		      
		      
		      return "" +idFailJilid;
		      
		} finally {
			if (db != null) db.close();
		}
	}
	private static String createNoFail(String idFailAsal) throws Exception {
		
		Db db = null;
		 String sql = "";
		 String noFailJilid = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		    	int jilid = checkJilid(idFailAsal);
		    	
		    	
		    	if(jilid >= 1){
		    		
		    		int subjaket = checkSubjaket(idFailAsal);
		    		
		    		if (subjaket == 0){
		    			
		    			int jil = jilid+1;
		    			String noFailRoot  =  checkNoRoot(idFailAsal);
		    			noFailJilid = noFailRoot+" "+"JLD"+jil;
		    		}
		    		else
		    		{
		    	
		    			int jil = jilid+1;
		    			String noFailRoot  =  checkNoRoot(idFailAsal);
		    			noFailJilid = noFailRoot+" "+"SJ"+subjaket+" "+"JLD"+jil;
		    		}
		    	}
		    	else
		    	{
		    		int subjaket = checkSubjaket(idFailAsal);
		    		
		    		if (subjaket == 0){
		    			
		    			// do nothing
		    		}
		    		else
		    		{
		    			// do nothing
		    		}
		    	}
		      
		      return noFailJilid;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
		// TODO Auto-generated method stub
	public static Vector getPaparFailJilid(String idFailJilid) throws Exception {
		String sql = "";
		Db db = null;
		
		try{
			paparFailJilid = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			/*sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, FLAG_VIEW_FILE " +
				  "FROM TBLPFDFAIL " +
				  "WHERE ID_FAIL='"+idFailJilid+"'";
				  */
			
			sql = "SELECT A.ID_NEGERI,A.ID_FAIL, A.ID_TARAFKESELAMATAN, A. ID_SEKSYEN, A.ID_AKTIVITI, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.ID_LOKASIFAIL, A.FAHARASAT, A.ID_STATUS, " +
					  "B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
					  "FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E " +
					  "WHERE " +
					  "A.ID_URUSAN = B.ID_URUSAN " +
					  "AND A.ID_SUBURUSAN = C.ID_SUBURUSAN " +
					  "AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN " +
					  "AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
					  "AND A.ID_FAIL='"+idFailJilid+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFailJilid",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
				h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
				h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
				h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
				h.put("KOD_SUBSUBURUSAN",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
				h.put("NAMA_SUBSUBURUSAN",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
				h.put("KOD_SUBSUBSUBURUSAN",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
				h.put("NAMA_SUBSUBSUBURUSAN",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
				h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("noFailJilid",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				/*h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("noFailJilid",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
				h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));*/
				
				
				paparFailJilid.addElement(h);
				
			}
			
			
			return paparFailJilid;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	

}
