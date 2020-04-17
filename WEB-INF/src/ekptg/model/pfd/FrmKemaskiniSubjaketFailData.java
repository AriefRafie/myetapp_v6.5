package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File_PFD;

public class FrmKemaskiniSubjaketFailData {
	
	private static Vector paparFail = new Vector();
	private static Vector paparFailAll = new Vector();
	private static Vector paparSubjaketFail = new Vector();
	private static Vector senaraiDokumen = new Vector();
	private static Vector senaraiSubjaket = new Vector();
	private static Vector paparSemuaFail = null;
	private static Vector paparFailSubjaket = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setDataFail(String id)throws Exception {
		
		 Db db = null;
		 db = new Db();
		 long totalFail = 0;
		 paparFail.clear();
		 String sql1 = "";
		 Statement stmt = db.getStatement();
		 
		
		 
		 try {
		      
		     
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("b.keterangan");
		      r.add("a.ID_STATUS" , r.unquote("b.ID_STATUS" ));
		      r.add("a.id_Fail",id);
		      
		    
		      sql1 = r.getSQLSelect("TBLPFDFAIL a,TBLRUJSTATUS b");
		      System.out.println("bod2 = " + sql1);
		      ResultSet rs1 = stmt.executeQuery(sql1);
		      

		     
		      Hashtable h;
		      
		      while (rs1.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail",rs1.getString("id_Fail"));
		    	  h.put("noFail", rs1.getString("no_Fail"));
		    	  h.put("keterangan", rs1.getString("keterangan"));
		    	  h.put("tajukFail",rs1.getString("tajuk_Fail")== null?"":rs1.getString("tajuk_Fail"));
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
	 public static Vector getDataFailAll(){
		 
		  return paparFailAll;
	  }
	 public static String setSubjaketFail(String idSubjaket)throws Exception {
			
		 Db db = null;
		 db = new Db();
		 
		 paparSubjaketFail.clear();
		 String sql = "";
		 Statement stmt = db.getStatement();
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 
		 try {
		      
			// db = new Db();
			// Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Subjaket");
		      r.add("a.no_Fail_Subjaket");
		      r.add("a.id_Pegawai");
		      r.add("a.tarikh_Subjaket_Fail");
		      r.add("a.tarikh_Masuk_Fail");
		      
		      r.add("a.id_Subjaket",idSubjaket);
//		      r.add("a.id_Pegawai",r.unquote("b.id_Pegawai"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdsubjaket a, Tblrujpegawai b");
//		      r.add("a.id_fail");
//		      r.add("a.no_Fail");
//		      r.add("a.id_Pegawai");
//		      r.add("a.tarikh_Subjaket_Fail");
//		      r.add("a.tarikh_Masuk");
//		      
//		      r.add("a.id_fail",idSubjaket);
//		      r.add("a.id_Pegawai",r.unquote("b.id_Pegawai"));
//		     
//		      sql = r.getSQLSelect("Tblpfdsubjaket a, Tblrujpegawai b");		    
//		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujpegawai b");
		      ResultSet rs = stmt.executeQuery(sql);
		      

		     
		      Hashtable h;
		      
		      String ID_SJ = "", ID_PEGAWAI = "", NO_FAIL = "", TARIKH_SJ = "", TARIKH_FAIL = "";
		      
		      while (rs.next()) {
//		    	  ID_SJ=rs.getString("id_fail")== null?"":rs.getString("id_fail");
//		    	  ID_PEGAWAI=rs.getString("id_Pegawai")== null?"":rs.getString("id_Pegawai");
//		    	  NO_FAIL=rs.getString("no_Fail")== null?"":rs.getString("no_Fail");
//		    	  TARIKH_SJ=rs.getDate("tarikh_Subjaket_Fail")== null?"":sdf.format(rs.getDate("tarikh_Subjaket_Fail"));
//		    	  TARIKH_FAIL=rs.getDate("tarikh_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Masuk"));
//		    	  h = new Hashtable();
//		    	  
//		    	  h.put("idSubjaket",ID_SJ);
//		    	  h.put("id_Pegawai",ID_PEGAWAI);
//		    	  h.put("no_Fail_Subjaket", NO_FAIL);
//		    	  h.put("tarikh_Subjaket_Fail",TARIKH_SJ);
//		    	  h.put("tarikh_Masuk_Fail",TARIKH_FAIL);
		    	  
		    	  ID_SJ=rs.getString("id_Subjaket")== null?"":rs.getString("id_Subjaket");
//		    	  ID_PEGAWAI=rs.getString("id_Pegawai")== null?"":rs.getString("id_Pegawai");
		    	  NO_FAIL=rs.getString("no_Fail_Subjaket")== null?"":rs.getString("no_Fail_Subjaket");
		    	  TARIKH_SJ=rs.getDate("tarikh_Subjaket_Fail")== null?"":sdf.format(rs.getDate("tarikh_Subjaket_Fail"));
		    	  TARIKH_FAIL=rs.getDate("tarikh_Masuk_Fail")== null?"":sdf.format(rs.getDate("tarikh_Masuk_Fail"));
		    	  h = new Hashtable();
		    	  
		    	  h.put("idSubjaket",ID_SJ);
//		    	  h.put("id_Pegawai",ID_PEGAWAI);
		    	  h.put("no_Fail_Subjaket", NO_FAIL);
		    	  h.put("tarikh_Subjaket_Fail",TARIKH_SJ);
		    	  h.put("tarikh_Masuk_Fail",TARIKH_FAIL);

		    	  paparSubjaketFail.addElement(h);
		  
		      }
		      return ""+idSubjaket;
		    //  return ""+id_fail;
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }	 
		 
	}
	 
		public static Vector getSubjaketFail(String idSubjaket) {
			return paparSubjaketFail;
		}
		
		 public static String addSubjeket(Hashtable dataH,Hashtable dataK)throws Exception {
			 Db db = null;
			 db = new Db();
			 Statement stmt = db.getStatement();
			 String sql = "";
			
		 try
		    {	 
				  long idSubjaket = DB.getNextID("TBLPFDSUBJAKET_SEQ");
			      String pegawai = (String)dataH.get("id_Pegawai");
				  String tarikhSubjaketFail = (String)dataH.get("tarikh_Subjaket_Fail");
				  String tkhSbjktFail = "to_date('" + tarikhSubjaketFail + "','dd/MM/yyyy')";
				 // String tarikhMasukFail = (String)data.get("tarikh_Masuk_Fail");
				 // String tkhMskFail = "to_date('" + tarikhMasukFail + "','dd/MM/yyyy')";
				  String idFail = (String)dataH.get("id_Fail");
				  String noFail = (String)dataH.get("no_Fail"); 
				  String no_root_file = (String)dataH.get("no_root_file");
				  
				  //-----------------------------------------------------------------------
				  String id_Suburusan = (String)dataK.get("id_Suburusan");
				  String id_Subsuburusan = (String)dataK.get("id_Subsuburusan");
				  
				  Integer negeri = Integer.parseInt((String)dataK.get("id_Negeri"));
				  Integer seksyen = Integer.parseInt((String)dataK.get("id_Seksyen"));
				  Integer urusan = Integer.parseInt((String)dataK.get("id_Urusan"));
				  
				  
				  System.out.println("XXXXXXXXXXXXXX"+(String)dataK.get("id_Suburusan"));
				  
				  
				  
				  Integer intsuburusan = 0;
				  if((String)dataK.get("id_Suburusan")!="" && (String)dataK.get("id_Suburusan")!=null)
				  {
					  intsuburusan = Integer.parseInt((String)dataK.get("id_Suburusan"));
				  }
				  
				  Integer intsubsuburusan = 0;
				  if((String)dataK.get("id_Suburusan")!="" && (String)dataK.get("id_Subsuburusan")!=null)
				  {
					  intsubsuburusan = Integer.parseInt((String)dataK.get("id_Subsuburusan"));
				  }
				  
				 // Integer intsuburusan = Integer.parseInt((String)dataK.get("id_Suburusan")== null?"0":(String)dataK.get("id_Suburusan"));
				  
				 // Integer Subsuburusan = Integer.parseInt((String)dataK.get("id_Subsuburusan")== null?"0":(String)dataK.get("id_Subsuburusan"));


				  //-----------------------------------------------------------------------
				  
				  int SJ_SEQ =  File_PFD.getSeqNoSubjaket(seksyen, urusan , 0, negeri, intsuburusan, intsubsuburusan);
				  //int SJ_SEQ = File.getSeqNoSubjaket(Integer.parseInt(idFail));
				  noFail = no_root_file + " SJ" + Integer.toString(SJ_SEQ);
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Subjaket",idSubjaket);
			      r.add("no_Fail_Subjaket",noFail );
			      r.add("id_Pegawai",pegawai);
			      r.add("tarikh_Subjaket_Fail", r.unquote(tkhSbjktFail));
			      //r.add("tarikh_Masuk_Fail", r.unquote(tkhMskFail));
			      r.add("tarikh_Masuk",r.unquote("sysdate"));
			      r.add("id_Fail", idFail);
			
			      sql = r.getSQLInsert("tblpfdsubjaket");
		
			      stmt.executeUpdate(sql);
			      
			      return ""+noFail.replace(" ","_")+","+idSubjaket+","+SJ_SEQ;
			    } finally {
			      if (db != null) db.close();
			    }

		 }
	 public static String add(Hashtable dataH,Hashtable dataK, String no_fail,String SJ_SEQ)throws Exception {
		 Db db = null;
		 db = new Db();
		 Statement stmt = db.getStatement();
		 String sql = "";
		
	 try
	    {
	 	      long idSubjaket = DB.getNextID("TBLPFDFAIL_SEQ");
		 	  //long idSubjaket = DB.getNextID("TBLPFDSUBJAKET_SEQ");
	 	     
	 	      
		 	  String idFail = Long.toString(idSubjaket);
		      String pegawai = (String)dataH.get("id_Pegawai");
			  String no_root_file = (String)dataH.get("no_root_file");
		      String tarikhSubjaketFail = (String)dataH.get("tarikh_Masuk_Fail");
			  String tkhSbjktFail = "to_date('" + tarikhSubjaketFail + "','dd/MM/yyyy')";
	
//			  String tkhSbjktFail = "to_date('" + tarikhSubjaketFail + "','dd/MM/yyyy')";			  
//			  String noFailx = (String)dataH.get("no_Fail");   			  
//			  int SJ_SEQ = File.getSeqNoSubjaket(Integer.parseInt(idFail));
		      
//		      String noFailRoot = (String)dataK.get("noFailRoot");
			  Integer negeri = Integer.parseInt((String) dataK.get("id_Negeri"));
			  Integer seksyen = Integer.parseInt((String)dataK.get("id_Seksyen"));
			  Integer urusan = Integer.parseInt((String)dataK.get("id_Urusan"));
			  String suburusan = dataK.get("id_Suburusan").toString();
			  String taraf = (String)dataK.get("id_Tarafkeselamatan");
			  String tajukFail = (String)dataK.get("tajukFail");
			  //String status = (String)dataK.get("id_Status");
			  String status = "7";
			  String lokasi = dataK.get("id_Lokasifail").toString();
			  String faharasat = dataK.get("faharasat").toString();
			  String tarikhDaftar = (String)dataK.get("tarikh_Daftar_Fail");
			  String tkhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
			  String flagFail = (String)dataK.get("flagFail");
			  String flag_view_File = (String)dataK.get("flag_view_File");
//			  String nSJ_SEQ =  SJ_SEQ+"";
			  
			  
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_Fail", idFail);
//		      r.add("NO_FAIL",noFail + " SJ" + Integer.toString(SJ_SEQ));
//		      r.add("NO_FAIL",no_fail );
		      r.add("NO_FAIL",no_root_file + " SJ" + SJ_SEQ );
		      r.add("tarikh_Masuk",r.unquote("sysdate"));
		      r.add("NAMA_PEGAWAI_SJ", pegawai);
		      r.add("no_Fail_Root", no_root_file);
		      r.add("id_Negeri", negeri);
		      r.add("id_Seksyen", seksyen);
		      r.add("id_Urusan", urusan);
		      r.add("id_Suburusan", suburusan);
		      r.add("id_Tarafkeselamatan", taraf);
		      r.add("tajuk_Fail", tajukFail);
		      r.add("id_Status", status);
		      r.add("id_Lokasifail", lokasi);
		      r.add("Faharasat", faharasat);
		      r.add("flag_Fail", flagFail);
		      r.add("flag_view_File", flag_view_File);
		      r.add("tarikh_Daftar_Fail", r.unquote(tkhDaftar));
		      
//		      System.out.println("tajukFail :" +tajukFail);
//		      System.out.println("noFailRoot :" +noFailRoot);
//		      System.out.println("negeri :" + negeri);
//		      System.out.println("seksyen :" + seksyen );
//		      System.out.println("suburusan :" + suburusan);
//		      System.out.println("urusan :" + urusan);
//		      System.out.println("tajukFail :" + tajukFail);
//		      System.out.println("status :" + status);
//		      System.out.println("lokasi :" + lokasi);
//		      System.out.println("faharasat :" + faharasat );
//		      System.out.println("tarikhDaftar :" + tarikhDaftar);
//		      System.out.println("tkhDaftar :" + tkhDaftar);
//		      System.out.println("flagFail :" + flagFail);
		      sql = r.getSQLInsert("tblpfdfail");	
		      stmt.executeUpdate(sql);
		      System.out.println("subjeket : " + sql);
		      return ""+idSubjaket;
		    } finally {
		      if (db != null) db.close();
		    }

	 }
	 public static void updateFailSJAsal(Hashtable data) throws Exception {
		 
		   	Db db = null;
		   	db = new Db();
		    String sql = "";
		    long idStatus = 0;
		    
		    String sql2 = "SELECT a.id_status,a.kod_status"+
		    " FROM tblrujstatus a"+
		    " WHERE a.id_seksyen = 6 AND a.kod_status = '03'";
		    
		    
		    Statement stmt = db.getStatement();
		    ResultSet rs = stmt.executeQuery(sql2);
		    rs.next();
		    idStatus = rs.getLong(1);
		    
		    try
		    {
		    	String idFailAsal = (String)data.get("id_Fail");
		       	
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
	 public static int update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	  int idSubjaket = (Integer)data.get("id_Subjaket");
		    	  String tarikhMasukFail = (String)data.get("tarikh_Masuk_Fail");
				  String tkhMskFail = "to_date('" + tarikhMasukFail + "','dd/MM/yyyy')";
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Subjaket", idSubjaket);
				  r.add("tarikh_Masuk_Fail",r.unquote(tkhMskFail));
				 
				 
				
				  sql = r.getSQLUpdate("tblpfdsubjaket");
			      stmt.executeUpdate(sql);
			      return (int)idSubjaket;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	 public void deleteSubjaketDokumen(String id_subjaket) throws Exception {
		    Db db = null;
		    String sql = "";
		    sql = "DELETE FROM TBLPFDSUBJAKETDOKUMEN WHERE ID_SUBJAKET = "+id_subjaket+"";
		    try
		    {
		    	 				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  stmt.executeUpdate(sql);			    
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	 public void simpanDokumen(String id_dokumen,String id_fail, String id_subjaket) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	 				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.add("id_dokumen", id_dokumen);
				  r.add("id_fail", id_fail);
				  r.add("id_subjaket", id_subjaket);
				  sql = r.getSQLInsert("tblpfdsubjaketdokumen");
			      stmt.executeUpdate(sql);
			    
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	 
//	 public static void  setListDokumen(int id)throws Exception {
//		    Db db = null;
//		    senaraiDokumen.clear();
//		    String sql = "";
//		    try {
//		      db = new Db();
//		      Statement stmt = db.getStatement();
//		      SQLRenderer r = new SQLRenderer();
//		      
//		      r.add("a.id_Dokumen");
//		      r.add("a.no_Rujukan_Dokumen");
//		      r.add("a.nama_Pengirim");
//		      r.add("a.nama_Penerima");
//		     
//		      
//		      r.add("a.id_Fail",id);
//		     
//		     
//		
//		      sql = r.getSQLSelect("Tblpfddokumen a","a.id_Dokumen desc");
//		      ResultSet rs = stmt.executeQuery(sql);
//		      
//		      Hashtable h;
//		      int bil = 1;
//		      int count = 0;
//		      while (rs.next()) {
//		    	  h = new Hashtable();
//		    	  h.put("bil", bil);
//		    	
//		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
//		    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
//		    	  h.put("nama_Pengirim",rs.getString("nama_Pengirim")== null?"":rs.getString("nama_Pengirim"));
//		    	  h.put("nama_Penerima", rs.getString("nama_Penerima")== null?"":rs.getString("nama_Penerima"));
//		    	  
//		    	  senaraiDokumen.addElement(h);
//		    	  bil++;
//		    	  count++;
//		      }
//		      if (count == 0){
//		    	  h = new Hashtable();
//		    	  h.put("bil", "");
//		    	
//		    	  h.put("id_Dokumen","");
//		    	  h.put("no_Rujukan_Dokumen", "Tiada rekod.");
//		    	  h.put("nama_Pengirim","");
//		    	  h.put("nama_Penerima", "");
//		    	  
//		    	  senaraiDokumen.addElement(h);
//		      }
//		      //return list;
//		    } finally {
//		      if (db != null) db.close();
//		    }
//		}
	 
	 public static Vector getListDokumenBySubjaket(String SJ_ID) throws Exception {
		 Db db = new Db();
		 Db dbIn = new Db();
		 String sql = "";
		 
		 try {
			 Vector v = new Vector();
			 Hashtable h = null;
			 Statement stmt = db.getStatement();
			 Statement stmtIn = dbIn.getStatement();
			 ResultSet rs = null;
			 ResultSet rsIn = null;
			 
			 String ID_DOKUMEN = "", NO_RUJ_DOK = "", TAJUK_DOK = "", ID_SJ = "";
			 int iCount = 1;
			 // get ID FAIL dari TBLPFDSUBJAKET
			 sql = "SELECT B.ID_DOKUMEN, B.NO_RUJUKAN_DOKUMEN, B.TAJUK_DOKUMEN FROM TBLPFDSUBJAKET A, TBLPFDDOKUMEN B " + 
			 	"WHERE A.ID_FAIL = B.ID_FAIL AND A.ID_SUBJAKET = " + SJ_ID;
			 rs = stmt.executeQuery(sql);
			 while (rs.next()) {
				 ID_DOKUMEN = rs.getString(1) == null ? "" : rs.getString(1);
				 NO_RUJ_DOK = rs.getString(2) == null ? "" : rs.getString(2);
				 TAJUK_DOK = rs.getString(3) == null ? "" : rs.getString(3);
				 h = new Hashtable();				 
				 h.put("bil", iCount);
				 h.put("idDokumen", ID_DOKUMEN);
				 h.put("no_Rujukan_Dokumen", NO_RUJ_DOK);
				 h.put("tajuk_Dokumen", TAJUK_DOK);
				 
				 ID_SJ = "";
				 sql = "SELECT ID_DOKUMEN FROM TBLPFDSUBJAKETDOKUMEN WHERE ID_SUBJAKET = " + SJ_ID + " AND ID_DOKUMEN = " + ID_DOKUMEN;
				 rsIn = stmtIn.executeQuery(sql);
				 if (rsIn.next()) {
					 ID_SJ = rsIn.getString(1) == null ? "" : rsIn.getString(1);
				 }
				 rsIn.close();
				 if (!"".equalsIgnoreCase(ID_SJ)) {
					 h.put("SUB_ID_DOKUMEN", "true");
				 } else {
					 h.put("SUB_ID_DOKUMEN", "");
				 }
				 v.add(h);
				 iCount++;
			 }
			 rs.close();
			 
			 return v;
		 } finally {
			 if (db != null)
				 db.close();
		 }
	 }
	 
	 public static void  setListDokumen(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    
		    try {
		    	senaraiDokumen = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				Hashtable h;
				int bil = 1;
				

		      sql = "SELECT " +
		      		"SD.ID_DOKUMEN AS SUB_ID_DOKUMEN, " +
		      		"A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.FLAG_DOKUMEN, B.NAMA_PEGAWAI AS NAMA_PENGIRIM1, C.NAMA_PEGAWAI AS NAMA_PENERIMA1, A.NAMA_PENGIRIM, A.NAMA_PENERIMA, D.ID_LAMPIRAN " +
		      		"FROM TBLPFDDOKUMEN A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C, TBLPFDRUJLAMPIRAN D " +
		      		",TBLPFDSUBJAKETDOKUMEN SD " +	
		      		//"FROM TBLPFDDOKUMEN A, TBLPFDRUJLAMPIRAN D " +	
		      		"WHERE A.ID_NAMAPENGIRIM = B.ID_PEGAWAI(+) " +
		      		"AND A.ID_DOKUMEN = SD.ID_DOKUMEN(+) "+
		      		"AND A.ID_NAMAPENERIMA = C.ID_PEGAWAI(+) " +
		      		"AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) " +
		      		"AND A.ID_FAIL = '"+id+"'";
	      		
		      sql = sql + " ORDER BY ID_DOKUMEN DESC";
		      
//			 sql = "select a.id_subjaket, B.ID_DOKUMEN, b.no_rujukan_dokumen, b.tajuk_dokumen from tblpfdsubjaket a, tblpfddokumen b where a.id_fail = b.id_fail "+
//				  "and a.id_subjaket = '"+id+"'";	

		      System.out.println("setListDokumen :"+ sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  
		    	 h.put("SUB_ID_DOKUMEN", rs.getString("SUB_ID_DOKUMEN")== null?"":rs.getString("SUB_ID_DOKUMEN"));	
		    	  
		    	  h.put("idDokumen",rs.getString("id_Dokumen"));
		    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
		    	 // h.put("idSubjaket", rs.getString("id_subjaket")== null?"":rs.getString("id_subjaket"));
		    	  h.put("idDokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));

//		    	  if (rs.getString("id_Jenisdokumen").equals("1")){
//			    	  h.put("jenis_Dokumen", "SURAT");
//		    	  }
//		    	  else if (rs.getString("id_Jenisdokumen").equals("2")){
//			    	  h.put("jenis_Dokumen", "MEMO");
//		    	  }
//		    	  else if (rs.getString("id_Jenisdokumen").equals("3")){
//			    	  h.put("jenis_Dokumen", "LAPORAN");
//		    	  }
//		    	  else if (rs.getString("id_Jenisdokumen").equals("4")){
//			    	  h.put("jenis_Dokumen", "MINIT MESYUARAT");
//		    	  }
		    	  
		    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  
//		    	  if (rs.getString("nama_pengirim") == null){
//		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim1")== null?"":rs.getString("Nama_Pengirim1"));
//		    	  }
//		    	  else {
//		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim")== null?"":rs.getString("Nama_Pengirim"));
//		    	  }
//		    	  
//		    	  if (rs.getString("nama_penerima") == null){
//		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima1")== null?"":rs.getString("Nama_Penerima1"));
//		    	  }
//		    	  else{
//		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima")== null?"":rs.getString("Nama_Penerima"));
//		    	  }
//		    	  
//		    	  h.put("id_Lampiran", rs.getString("id_Lampiran")== null?"":rs.getString("id_Lampiran"));			    	 
//		    	  h.put("flag_Dokumen", rs.getString("flag_Dokumen")== null?"":rs.getString("flag_Dokumen"));

		    	  bil++;
		    	  count++;
		    	  	 
		    	  senaraiDokumen.addElement(h);
		      }
		      
		     
		      
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("idDokumen","");
		    	  h.put("no_Rujukan_Dokumen", "Tiada rekod.");
		    	  h.put("jenis_Dokumen", "");
		    	  h.put("tajuk_Dokumen","");
		    	  h.put("nama_Pengirim","");
		    	  h.put("nama_Penerima", "");
		    	  h.put("id_Lampiran", "");
		    	  h.put("flag_Dokumen", "");

		    	  senaraiDokumen.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	 
	public static Vector getListDokumen(){
			 
		return senaraiDokumen;
	}
	 public static void  setListSubjaket(int id)throws Exception {
		    Db db = null;
		    senaraiSubjaket.clear();
		    String sql = "";
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Subjaket");
		      r.add("a.no_Fail_Subjaket");
		      //r.add("b.nama_Pegawai");
		      r.add("b.user_name");
		      r.add("a.tarikh_Masuk_Fail");
		      r.add("a.tarikh_Subjaket_Fail");
		     
		      
//		      r.add("a.id_Pegawai",r.unquote("b.id_Pegawai"));
		      r.add("a.id_Pegawai",r.unquote("b.user_id"));
		      r.add("a.id_Fail",id);
		     
		     
		
		      sql = r.getSQLSelect("Tblpfdsubjaket a, Users b","a.id_Subjaket desc");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	
		    	  h.put("idSubjaket",rs.getString("id_Subjaket"));
		    	  h.put("no_Fail_Subjaket",rs.getString("no_Fail_Subjaket"));
		    	  h.put("nama_Pegawai",rs.getString("user_name"));
		    	  h.put("tarikh_Masuk_Fail",rs.getDate("tarikh_Masuk_Fail")== null?"":sdf.format(rs.getDate("tarikh_Masuk_Fail")));
		    	  h.put("tarikh_Subjaket_Fail",rs.getDate("tarikh_Subjaket_Fail")== null?"":sdf.format(rs.getDate("tarikh_Subjaket_Fail")));

		    	  senaraiSubjaket.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	
		    	  h.put("idSubjaket","");
		    	  h.put("no_Fail_Subjaket","Tiada rekod.");
		    	  h.put("nama_Pegawai","");
		    	  h.put("tarikh_Masuk_Fail","");
		    	  h.put("tarikh_Subjaket_Fail","");

		    	  senaraiSubjaket.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListSubjaket(){
			 
		return senaraiSubjaket;
	}
	public static Vector getDataSubjaketFail() {
		// TODO Auto-generated method stub
		return paparSubjaketFail;
	}
	public static void setDataFailSubjaket(String idSubjaket) {
		// TODO Auto-generated method stub
		
	}
	public static void updateSubjaketFail(Hashtable data) throws Exception {
		  Db db = null;
		    String sql = "";
		    try
		    {
		    	  String idSubjaket = (String)data.get("idSubjaket");
			      String idNamaPenerima = (String)data.get("id_nama_penerima");
				  String idKemaskini = (String)data.get("id_Kemaskini");
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Subjaket", idSubjaket);
			      r.add("id_Pegawai", idNamaPenerima);
				  r.add("id_Kemaskini",idKemaskini);
				  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				
				  sql = r.getSQLUpdate("tblpfdsubjaket");
			      stmt.executeUpdate(sql);
			    }
			    finally {
			      if (db != null) db.close();
			    }
		
		
	}

	public static void setDataFailAll(int id)throws Exception {
		
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
		      r.add("a.id_Suburusan");
		      r.add("a.id_Subsuburusan");
		      r.add("b.id_Status");
		      r.add("c.id_Negeri");
		      r.add("d.id_Seksyen");
		      r.add("e.id_Urusan");
//		      r.add("f.id_Suburusan");
//		      r.add("f.id_Subsuburusan");
		      r.add("g.id_Tarafkeselamatan");
		      r.add("h.id_Lokasifail");
		      //r.add("i.id_Faharasat");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("a.flag_Fail");
		      r.add("a.flag_view_File");
		      r.add("a.faharasat");
		      
		      r.add("a.id_Fail",id);
		      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
		      r.add("a.id_Negeri",r.unquote("c.id_Negeri(+)"));
		      r.add("a.id_Seksyen",r.unquote("d.id_Seksyen(+)"));
		      r.add("a.id_Urusan",r.unquote("e.id_Urusan(+)"));
		      //r.add("a.id_Suburusan",r.unquote("f.id_Suburusan(+)"));
		      r.add("a.id_Tarafkeselamatan",r.unquote("g.id_Tarafkeselamatan(+)"));
		      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
		     // r.add("a.id_Faharasat",r.unquote("i.id_Faharasat(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b, Tblrujnegeri c,Tblrujseksyen d, Tblrujurusan e, Tblrujsuburusan f, Tblpfdrujtarafkeselamatan g, Tblpfdrujlokasifail h, Tblpfdrujfaharasat i");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		     
		      while (rs.next()) {
		    	  Hashtable k = new Hashtable();
		    	  k.put("idFail",rs.getString("id_Fail"));
		    	  k.put("noFail", rs.getString("no_Fail"));		    	  
		    	  k.put("tajukFail", rs.getString("tajuk_Fail"));
		    	  k.put("flagFail", rs.getString("flag_Fail")==null?"":rs.getString("flag_Fail"));
		    	//  k.put("flag_view_File" ,rs.getString("flag_view_File")); 
		    	  k.put("flag_view_File", rs.getString("flag_view_File")==null?"":rs.getString("flag_view_File"));
			    	 
		    	 // k.put("faharasat", rs.getString("faharasat"));
		    	  k.put("noFailRoot", rs.getString("no_Fail_Root")==null?"":rs.getString("no_Fail_Root"));
		    	  k.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  k.put("id_Status",rs.getString("id_Status")== null?"0":rs.getString("id_Status"));
		    	  k.put("id_Negeri", rs.getString("id_Negeri")== null?"0":rs.getString("id_Negeri"));
		    	  k.put("id_Seksyen",rs.getString("id_Seksyen")== null?"0":rs.getString("id_Seksyen"));
		    	  k.put("id_Urusan",rs.getString("id_Urusan")== null?"0":rs.getString("id_Urusan"));
		    	  k.put("id_Suburusan", rs.getString("id_Suburusan") == null?"0":rs.getString("id_Suburusan"));
		    	  k.put("id_Tarafkeselamatan", rs.getString("id_Tarafkeselamatan")== null?"0": rs.getString("id_Tarafkeselamatan"));
		    	  k.put("id_Lokasifail",rs.getString("id_Lokasifail")== null?"0":rs.getString("id_Lokasifail"));
		    	  k.put("faharasat",rs.getString("faharasat")== null?"0":rs.getString("faharasat"));
		    	  k.put("tarikh_Daftar_Fail", sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  paparFailAll.addElement(k);
		
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  

	}
	public static String getnorootfile(String idFail) throws SQLException, DbException {
		
		 Db db = null;
		 paparFail.clear();
		 String sql = "";
		 String no_Fail_Root= "";
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();

		      	
		      r.add("no_Fail_Root");		      
		      r.add("id_Fail",idFail);
		     // r.add("a.id_Faharasat",r.unquote("i.id_Faharasat(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
			      no_Fail_Root = rs.getString("no_Fail_Root")== null?"":rs.getString("no_Fail_Root");  
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }
		return no_Fail_Root;  
	}
	
	public static String addSubjaket(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		
		try
			{
				
			 	long idFailSubjaket = DB.getNextID("TBLPFDFAIL_SEQ");
			 	String idFailAsal = (String)data.get("idFailAsal");
			 	//String taraf = (String)data.get("id_Tarafkeselamatan");
			    String idMasuk = (String)data.get("id_Masuk");
			    String noFailSubjaket = createNoFail(idFailAsal);
			    String tarikhSubjaketFail = (String)data.get("tarikhSubjaketFail");
			    int subjaket = checkSubjaket(idFailAsal);
			    int newSubjaket = subjaket+1;
			    
			    // r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')")
			    db = new Db();
			    Statement stmt = db.getStatement();

			    String sqlInsert = "INSERT INTO TBLPFDFAIL (ID_FAIL,ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
				   				+"FLAG_FAIL, ID_STATUS, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
				   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN,ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN )";
			  
			    String sqlSelect = "SELECT '"+idFailSubjaket+"',ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, to_date('"+ data.get("tarikhSubjaketFail")+ "','dd/MM/yyyy'), TAJUK_FAIL, '"+noFailSubjaket+"', NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
				   				+"FLAG_FAIL, ID_STATUS, CATATAN, '"+idMasuk+"', sysdate, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
				   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN,ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, '"+newSubjaket+"', NO_TURUTAN "
				   				+"FROM TBLPFDFAIL WHERE ID_FAIL = '"+idFailAsal+"'";
			  


		      sql = sqlInsert+" "+sqlSelect;
		      
		    
		      stmt.executeUpdate(sql);  
		      //42
		      
			  String sqlUpdate = "UPDATE TBLPFDFAIL SET ID_STATUS = '15' WHERE ID_FAIL = '"+idFailAsal+"'";
	   		
  
			  sql1 = sqlUpdate;
			  stmt.executeUpdate(sql1);  
		      
		      
		      return "" +idFailSubjaket;
		      
		} finally {
			if (db != null) db.close();
		}
	}
	
	private static String createNoFail(String idFailAsal) throws Exception {

		Db db = null;
		String sql = "";
		String noFailSubjaket = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		    	int subjaket = checkSubjaket(idFailAsal);
		    	
		    	
		    	if(subjaket >= 0){
		    		
		    		int jilid = checkJilid(idFailAsal);
		    		
		    		if (jilid <= 1){
		    			
		    			int sj = subjaket+1;
		    			String noFailRoot  =  checkNoRoot(idFailAsal);
		    			noFailSubjaket = noFailRoot+" "+"SJ"+sj;
		    		}
		    		else
		    		{
		    	
		    			int sj = subjaket+1;
		    			String noFailRoot  =  checkNoRoot(idFailAsal);
		    			noFailSubjaket = noFailRoot+" "+"JLD"+jilid+" "+"SJ"+sj;
		    		}
		    	}
		    	else
		    	{
		    		int jilid = checkJilid(idFailAsal);
		    		
		    		if (jilid == 1){
		    			
		    			// do nothing
		    		}
		    		else
		    		{
		    			// do nothing

		    		}
		    	}
		      
		      return noFailSubjaket;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
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
	public static Vector getPaparSemuaFail(String idFailAsal) throws Exception {
		String sql = "";
		Db db = null;
		
		try{
			paparSemuaFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, FLAG_VIEW_FILE " +
				  "FROM TBLPFDFAIL " +
				  "WHERE ID_FAIL='"+idFailAsal+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFailAsal",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("noFailAsal",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
				h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				
				
				paparSemuaFail.addElement(h);
				
			}
			
			
			return paparSemuaFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector getPaparFailSubjaket(String idFailSubjaket) throws Exception {
		String sql = "";
		Db db = null;
		
		try{
			paparFailSubjaket = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, FLAG_VIEW_FILE " +
				  "FROM TBLPFDFAIL " +
				  "WHERE ID_FAIL='"+idFailSubjaket+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFailSubjaket",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("noFailSubjaket",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
				h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				
				
				paparFailSubjaket.addElement(h);
				
			}
			
			
			return paparFailSubjaket;
		}
		finally {
			if (db != null)
				db.close();
		}
	}



}
