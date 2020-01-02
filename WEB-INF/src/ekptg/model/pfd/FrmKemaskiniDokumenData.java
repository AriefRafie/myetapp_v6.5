package ekptg.model.pfd;

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



public class FrmKemaskiniDokumenData {
	
	private static Vector paparFail = null;
	private static Vector paparMinitArahan = null;
	private static Vector paparLevelArahan = null;
	private static Vector paparLampiranDokumenMasuk = null;
	private static Vector paparLampiranDokumenKeluar = null;
	private static Vector paparLampiranDokumen = null;
	private static Vector paparPegawai = null;
	
	private static Vector getIDseksyen = null;
	
	private static Vector paparIdDokumen = null;
	private static Vector senaraiPegawai = new Vector();
	private static Vector paparDokumen = null;
	private static Vector paparDokumenFail = null;
	private static Vector paparNoDokumen = null;
	private static Vector paparCountDokumen = null;
	private static Vector senaraiDokumen = null;
	private static Vector senaraiMinit = null;
	private static Vector senaraiPTFail = null;
	private static Vector senaraiMinitPengarah = null;
	private static Vector senaraiMinitPegawai1 = null;
	private static Vector senaraiMinitPegawai2 = null;
	private static Vector senaraiMinitPegawai3 = null;
	private static Vector senaraiMinitPegawai4 = null;
	private static Vector senaraiMinitSelesai = null;
	private static Vector senaraiMinitSeksyenLain = null;
	private static Vector paparMinitArahanSeksyenLain = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Vector listLampiran= null;
	private static Vector listPA= null;
	private static Vector listPT= null;
	private static Vector listPTFail= new Vector();
	private static Vector listPTFail2= new Vector();
    static Vector paparMinit = null;
    static Vector paparMinitArahan2b = null;
    static Vector paparMinitArahan2c = null;
    static Vector senaraiPegawaiTinggi = null;
    private static Logger log = Logger.getLogger(ekptg.model.pfd.FrmKemaskiniDokumenData.class);
    
    public static void setDataFail(String id)throws Exception {
		
		 Db db = null;
		 String sql = "";
		 
		 try {
			 paparFail =  new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("b.keterangan");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("b.id_Status");
		      
		      r.add("a.id_Fail",id);
		      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b");
		      
		      System.out.println("sql---"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail",rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")== null?"":rs.getString("no_Fail"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
//		    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    	  h.put("tarikh_Daftar_Fail",rs.getString("tarikh_Daftar_Fail")== null?"":sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("id_Status",rs.getString("id_Status")== null?"":rs.getString("id_Status"));

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
	 public static void setDataDokumen(String id)throws Exception {
			
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.bil_Minit_Dokumen");
		      r.add("a.id_Jenisdokumen");
		      r.add("a.no_Rujukan_Dokumen");
		      r.add("a.no_Rujukan_Lain");
		      r.add("a.tajuk_Dokumen");
		      r.add("a.tarikh_Dokumen_Keluar");
		      r.add("a.tarikh_Dokumen_Diterima");
		    //  r.add("a.tarikh_Dokumen_Masuk");
		      r.add("a.id_NamaPengirim");
		      r.add("a.id_NamaPenerima");
		     // r.add("a.tarikh_Daftar");
		      r.add("a.flag_Dokumen");  
		      r.add("a.id_Dokumen",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfddokumen a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("bil_Minit_Dokumen", rs.getString("bil_Minit_Dokumen")==null?"":rs.getString("bil_Minit_Dokumen"));
		    	  h.put("jenis_Dokumen",rs.getString("id_Jenisdokumen"));
		    	  h.put("no_Rujukan_Dokumen",rs.getString("no_Rujukan_Dokumen")==null?"":rs.getString("no_Rujukan_Dokumen"));
		    	  h.put("no_Rujukan_Lain",rs.getString("no_Rujukan_Lain")== null?"":rs.getString("no_Rujukan_Lain"));
		    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  h.put("tarikh_Dokumen_Keluar", rs.getDate("tarikh_Dokumen_Keluar")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Keluar")));
		    	  h.put("tarikh_Dokumen_Diterima", rs.getDate("tarikh_Dokumen_Diterima")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Diterima")));
		    	//  h.put("tarikh_Dokumen_Masuk",rs.getDate("tarikh_Dokumen_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Masuk")));
		    	  h.put("id_nama_Pengirim",rs.getString("id_NamaPengirim")== null?"":rs.getString("id_NamaPengirim"));
		    	  h.put("id_nama_Penerima",rs.getString("id_NamaPenerima")== null?"":rs.getString("id_NamaPenerima"));
		    	 // h.put("tarikh_Daftar", sdf.format(rs.getDate("tarikh_Daftar")== null?"":rs.getDate("tarikh_Daftar"));
		    	  h.put("flag_Dokumen",rs.getString("flag_Dokumen"));
		    	  
		    	  paparDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("id_Dokumen",0);
		    	  h.put("bil_Minit_Dokumen", "");
		    	  h.put("id_Jenisdokumen",0);
		    	  h.put("no_Rujukan_Dokumen","");
		    	  h.put("no_Rujukan_Lain","");
		    	  h.put("tajuk_Dokumen","");
		    	  h.put("tarikh_Dokumen_Keluar","");
		    	  h.put("tarikh_Dokumen_Diterima", "");
		    	  h.put("tarikh_Dokumen_Masuk","");
		    	  h.put("id_nama_Pengirim","");
		    	  h.put("id_nama_Penerima","");
		    	  //h.put("tarikh_Daftar", "");
		    	  h.put("flag_Dokumen",0);
		    	  paparDokumen.addElement(h); 
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataDokumen(){
		 
		  return paparDokumen;
	  }
	 

	 public static void  setListDokumen(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    
		    try {
		    	senaraiDokumen = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "SELECT A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.FLAG_DOKUMEN, B.USER_NAME AS NAMA_PENGIRIM1, C.USER_NAME AS NAMA_PENERIMA1, A.NAMA_PENGIRIM, A.NAMA_PENERIMA, D.ID_LAMPIRAN, A.ID_FAILSUBJAKET, A.ID_FAIL " +
	      		"FROM TBLPFDDOKUMEN A, USERS B, USERS C, TBLPFDRUJLAMPIRAN D, USERS E " +	
	      		//"FROM TBLPFDDOKUMEN A, TBLPFDRUJLAMPIRAN D " +	
	      		"WHERE A.ID_NAMAPENGIRIM = B.USER_ID(+) " +
	      		"AND A.ID_NAMAPENERIMA = C.USER_ID(+) " +
	      		"AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) " +
	      		"AND A.ID_FAIL = '"+id+"'";
	      		
		      sql = sql + " ORDER BY ID_DOKUMEN DESC";
		      
		      
		      System.out.println("SQL DOKUMEN ::"+sql.toUpperCase());
				
//				 sql = "SELECT A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.FLAG_DOKUMEN, B.NAMA_PEGAWAI AS NAMA_PENGIRIM1, C.NAMA_PEGAWAI AS NAMA_PENERIMA1, A.NAMA_PENGIRIM, A.NAMA_PENERIMA, A " +
//		      		"FROM TBLPFDDOKUMEN A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +	
//		      		//"FROM TBLPFDDOKUMEN A, TBLPFDRUJLAMPIRAN D " +	
//		      		"WHERE A.ID_NAMAPENGIRIM = B.ID_PEGAWAI(+) " +
//		      		"AND A.ID_NAMAPENERIMA = C.ID_PEGAWAI(+) " +
//		      		//"AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) " +
//		      		"AND A.ID_FAIL = '"+id+"'";
//		      		
//			      sql = sql + " ORDER BY A.ID_DOKUMEN DESC";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
		    	 // h.put("no_Rujukan_Lain", rs.getString("no_Rujukan_Lain")== null?"":rs.getString("no_Rujukan_Lain"));

		    	  if (rs.getString("id_Jenisdokumen").equals("1")){
			    	  h.put("jenis_Dokumen", "SURAT");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("2")){
			    	  h.put("jenis_Dokumen", "MEMO");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("3")){
			    	  h.put("jenis_Dokumen", "LAPORAN");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("4")){
			    	  h.put("jenis_Dokumen", "MINIT MESYUARAT");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("5")){
			    	  h.put("jenis_Dokumen", "FAIL SUBJAKET");
		    	  }
		    	  
		    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  
		    	  if (rs.getString("nama_pengirim") == null){
		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim1")== null?"":rs.getString("Nama_Pengirim1"));
		    	  }
		    	  else {
		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim")== null?"":rs.getString("Nama_Pengirim"));
		    	  }
		    	  
		    	  if (rs.getString("nama_penerima") == null){
		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima1")== null?"":rs.getString("Nama_Penerima1"));
		    	  }
		    	  else{
		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima")== null?"":rs.getString("Nama_Penerima"));
		    	  }
		    	  
		    	  h.put("idFailSubjaket", rs.getString("id_Failsubjaket")== null?"":rs.getString("id_Failsubjaket"));
		    	  h.put("id_Lampiran", rs.getString("id_Lampiran")== null?"":rs.getString("id_Lampiran"));			    	 
		    	  h.put("flag_Dokumen", rs.getString("flag_Dokumen")== null?"":rs.getString("flag_Dokumen"));
		    	  h.put("id_Fail", rs.getString("id_Fail")== null?"":rs.getString("id_Fail"));
 
		    	  bil++;
		    	  count++;
		    	  	 
		    	  senaraiDokumen.addElement(h);
		      }
		      
		     
		      
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	  h.put("id_Dokumen","");
		    	  h.put("no_Rujukan_Dokumen", "Tiada rekod.");
		    	  //h.put("no_Rujukan_Lain", "Tiada rekod.");
		    	  h.put("jenis_Dokumen", "");
		    	  h.put("tajuk_Dokumen","");
		    	  h.put("nama_Pengirim","");
		    	  h.put("nama_Penerima", "");
		    	  h.put("id_Lampiran", "");
		    	  h.put("flag_Dokumen", "");
		    	  h.put("id_Fail", "");

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
	public static void  setListMinitArahan(String id)throws Exception {
	    Db db = null;
	    String sql = "";
	    
	    try {
	      senaraiMinit = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_Minitarahan");
	      r.add("b.no_Rujukan_Dokumen");
	      r.add("a.minit_Arahan");
	      r.add("c.user_name as PegawaiMengarah");
	      r.add("d.user_name as PegawaiMenerima");
	      r.add("a.tarikh_Minit_Arahan");
	      r.add("e.status_Tindakan");
	     
	      
	      r.add("a.id_Dokumen",id);
	      r.add("a.id_Pegawai_Ygmengarah",r.unquote("c.user_id"));
	      r.add("a.id_Pegawai_Ygmenerima",r.unquote("d.user_id"));
	      r.add("a.id_Dokumen",r.unquote("b.id_Dokumen"));
	      r.add("a.id_statustindakan",r.unquote("e.id_statustindakan"));
	      
	     
	
	      sql = r.getSQLSelect("Tblpfdminitarahan a, Tblpfddokumen b, users c, users d, Tblpfdrujstatustindakan e","a.id_Minitarahan asc");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	     
	      System.out.println("hereee"+sql);
	      
	      
	      Hashtable h;
	      
		  int no = 1;
		  int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  h.put("bil",no);
	    	  h.put("id_Minitarahan",rs.getString("id_Minitarahan"));
	    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
	    	  h.put("minit_Arahan", rs.getString("minit_Arahan")== null?"":rs.getString("minit_Arahan"));
	    	  h.put("PegawaiMengarah",rs.getString("PegawaiMengarah")== null?"":rs.getString("PegawaiMengarah"));
	    	  h.put("PegawaiMenerima", rs.getString("PegawaiMenerima")== null?"":rs.getString("PegawaiMenerima"));
	    	  h.put("tarikh_Minit_Arahan", rs.getDate("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
	    	  h.put("status_Tindakan",rs.getString("status_tindakan")== null?"":rs.getString("status_tindakan"));

	    	  
	    	  no++;
			  count++;
	    	  senaraiMinit.addElement(h);

	      }
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil","");
	    	  h.put("id_Minitarahan","");
	    	  h.put("no_Rujukan_Dokumen", "Tiada rekod.");
	    	  h.put("minit_Arahan", "");
	    	  h.put("PegawaiMengarah","");
	    	  h.put("PegawaiMenerima", "");
	    	  h.put("tarikh_Minit_Arahan", "");
	    	  h.put("status_Tindakan", "");
	    	  senaraiMinit.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	public static Vector getListMinitArahan(){
			 
		return senaraiMinit;
	}
	public static String  addMasuk(Hashtable data)throws Exception {
		
		Db db = null;
	    String sql = "";
	    String sql2 = "";
	    String sql3 = "";
	    String sql4 = "";
	   
	    try
	    {	 
	    	  long idDokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
	    	  long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String namaPengirim = (String)data.get("namaPengirim");
		      String idNamaPenerima = (String)data.get("id_nama_penerima");
		      String id_PA = (String)data.get("id_PA");
		      //String status = (String)data.get("status");
		      String idFail = (String)data.get("id_Fail");
		      String idLogDokumen = (String)data.get("idLogDokumen");
		      String idMasuk = (String)data.get("id_Masuk");
		      String tag_dokumen = (String)data.get("tag_dokumen");
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Dokumen",idDokumen);
		      
		      if(idFail != ""){  r.add("id_Fail",idFail);}
		      
		      r.add("id_LogDokumen",idLogDokumen);
		      r.add("id_Minit",idMinit);
		      r.add("id_Laporan",idLaporan);
		      r.add("cd",idCD);
		      r.add("flag_Dokumen", flagDokumen);
		      r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		      r.add("tarikh_Dokumen_Diterima", r.unquote(tarikhDokDiterima));
		      r.add("tarikh_Dokumen", r.unquote(tarikhDok));
		      r.add("nama_Pengirim", namaPengirim);
		      r.add("id_NamaPenerima", idNamaPenerima);
		      r.add("ID_SETIAUSAHA", id_PA);
		      r.add("STATUS_MINIT_PENGARAH","1");
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		      r.add("id_Masuk",idMasuk);
		      
		      sql = r.getSQLInsert("tblpfddokumen");  
		      stmt.executeUpdate(sql);
      
		      ///
		      String sqlInsert = "INSERT INTO TBLPFDRUJLAMPIRAN (id_dokumen, content, nama_fail, jenis_mime)";
		      String sqlSelect = "SELECT '"+idDokumen+"', content, nama_fail, jenis_mime from tblpfdrujlampiranlogdok WHERE id_logdokumen = "+idLogDokumen+"";
		      
		      sql2 = sqlInsert+" "+sqlSelect;
		      stmt.executeUpdate(sql2);
		      
		      String sqlUpdate = "UPDATE TBLPFDLOGDOKUMEN SET STATUS_LOGDOKUMEN = '0' WHERE id_LogDokumen = '"+idLogDokumen+"'";
			     
		      sql3 = sqlUpdate;
		      stmt.executeUpdate(sql3);
		      
		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",r.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
    
		      sql4 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql4);
		      
		      
		      return ""+idDokumen;
		    } finally {
		      if (db != null) db.close();
		    }

	}

	public static String  addKeluar(Hashtable data)throws Exception {
		
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	   
	    try
	    {	 
	    	  long idDokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
	    	  long idLogDokumen = DB.getNextID("TBLPFDLOGDOKUMEN_SEQ");
	    	  long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      //String tkhDokKeluar = (String)data.get("tarikh_Dokumen_Keluar");
		     // String tarikhDokKeluar = "to_date('" + tkhDokKeluar + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String idNamaPengirim = (String)data.get("id_nama_pengirim");
		      String idPAR = (String)data.get("id_PAR");
		      String namaPenerima = (String)data.get("namaPenerima");
		      String idFail = (String)data.get("id_Fail");
		      String idMasuk = (String)data.get("id_Masuk");
		      String tag_dokumen = (String)data.get("tag_dokumen");
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Dokumen",idDokumen);
		      r.add("id_logDokumen",idLogDokumen);
		      r.add("id_Fail",idFail);
		      r.add("id_Minit",idMinit);
		      r.add("id_Laporan",idLaporan);
		      r.add("CD",idCD);
		      r.add("flag_Dokumen", flagDokumen);
		      r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		     // r.add("tarikh_Dokumen_Keluar", r.unquote(tarikhDokKeluar));
		      r.add("tarikh_Dokumen", r.unquote(tarikhDok));
		      r.add("id_NamaPengirim", idNamaPengirim);
		      r.add("id_PAR", idPAR);
		      r.add("nama_Penerima", namaPenerima);
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		      r.add("id_Masuk",idMasuk);
		      
		      sql = r.getSQLInsert("tblpfddokumen");  
	  	      stmt.executeUpdate(sql);
	  	      
	  	      db = new Db();
		      Statement stmt1 = db.getStatement();
		      SQLRenderer s = new SQLRenderer();
		      
		      s.add("id_logDokumen",idLogDokumen);
		      s.add("id_Dokumen",idDokumen);
		      s.add("no_rujukan",noRujDok);
		      s.add("tajuk_dokumen",tajukDok);
		      s.add("flag_logdokumen","2");
		      s.add("status_logdokumen","1");
		      s.add("id_ptfail",idPAR);
		      s.add("tarikh_dokumen",r.unquote(tarikhDok));
      
		      sql1 = s.getSQLInsert("tblpfdlogdokumen");  
	  	      stmt1.executeUpdate(sql1);
	  	      
	  	      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",r.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
    
		      sql2 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
	  	      
//		      String sqlInsert = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_dokumen, content, nama_fail, jenis_mime)";
//		      String sqlSelect = "SELECT '"+idDokumen+"', content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+"";
//		      
//		      sql2 = sqlInsert+" "+sqlSelect;
//		      stmt.executeUpdate(sql2);
	  	      
		      return ""+idDokumen;
		    } finally {
		      if (db != null) db.close();
		    }

	}
	public static String  addDalaman(Hashtable data)throws Exception {
		
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	   
	    try
	    {	 
	    	  long idDokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
	    	  long idLogDokumen = DB.getNextID("TBLPFDLOGDOKUMEN_SEQ");
	    	  long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      //String tkhDokKeluar = (String)data.get("tarikh_Dokumen_Keluar");
		     // String tarikhDokKeluar = "to_date('" + tkhDokKeluar + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String idNamaPengirim = (String)data.get("id_nama_pengirim");
		      String idPAR = (String)data.get("id_PAR");
		      String namaPenerima = (String)data.get("namaPenerima");
		      String idFail = (String)data.get("id_Fail");
		      String idMasuk = (String)data.get("id_Masuk");
		      String tag_dokumen = (String)data.get("tag_dokumen");
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Dokumen",idDokumen);
		      r.add("id_logDokumen",idLogDokumen);
		      r.add("id_Fail",idFail);
		      r.add("id_Minit",idMinit);
		      r.add("id_Laporan",idLaporan);
		      r.add("CD",idCD);
		      r.add("flag_Dokumen", flagDokumen);
		      r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		     // r.add("tarikh_Dokumen_Keluar", r.unquote(tarikhDokKeluar));
		      r.add("tarikh_Dokumen", r.unquote(tarikhDok));
		      r.add("id_NamaPengirim", idNamaPengirim);
		      r.add("id_PAR", idPAR);
		      r.add("nama_Penerima", namaPenerima);
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		      r.add("id_Masuk",idMasuk);
		      
		      sql = r.getSQLInsert("tblpfddokumen");  
	  	      stmt.executeUpdate(sql);
	  	      
	  	      db = new Db();
		      Statement stmt1 = db.getStatement();
		      SQLRenderer s = new SQLRenderer();
		      
		      s.add("id_logDokumen",idLogDokumen);
		      s.add("id_Dokumen",idDokumen);
		      s.add("no_rujukan",noRujDok);
		      s.add("tajuk_dokumen",tajukDok);
		      s.add("flag_logdokumen","5");
		      s.add("status_logdokumen","1");
		      s.add("id_ptfail",idPAR);
		      s.add("tarikh_dokumen",r.unquote(tarikhDok));
      
		      sql1 = s.getSQLInsert("tblpfdlogdokumen");  
	  	      stmt1.executeUpdate(sql1);
	  	      
	  	      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",r.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
    
		      sql2 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
	  	      
//		      String sqlInsert = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_dokumen, content, nama_fail, jenis_mime)";
//		      String sqlSelect = "SELECT '"+idDokumen+"', content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+"";
//		      
//		      sql2 = sqlInsert+" "+sqlSelect;
//		      stmt.executeUpdate(sql2);
	  	      
		      return ""+idDokumen;
		    } finally {
		      if (db != null) db.close();
		    }

	}
	public static void updateMasuk(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql3 = "";
	    try
	    {
	    	  String idDokumen = (String)data.get("id_Dokumen");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String namaPengirim = (String)data.get("namaPengirim");
		      String idNamaPenerima = (String)data.get("id_nama_penerima");
		      String id_PA = (String)data.get("id_PA");
		      //String status = (String)data.get("status");
		      String idFail = (String)data.get("id_Fail");
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String id_tagdokumen = (String)data.get("id_tagdokumen");
			  String tag_dokumen = (String)data.get("tag_dokumen");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Dokumen", idDokumen);
		      r.add("id_Fail",idFail);
		      r.add("id_Minit",idMinit);
		      r.add("id_Laporan",idLaporan);
		      r.add("cd",idCD);
		      r.add("flag_Dokumen", flagDokumen);
		      r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		      r.add("tarikh_Dokumen_Diterima", r.unquote(tarikhDokDiterima));
		      r.add("tarikh_Dokumen", r.unquote(tarikhDok));
		      r.add("nama_Pengirim", namaPengirim);
		      r.add("id_NamaPenerima", idNamaPenerima);
		      r.add("ID_SETIAUSAHA", id_PA);
//			  r.add("bil_Minit_Dokumen", bilMinitDokumen);
//		      r.add("id_Jenisdokumen", idJenisdokumen);
//		      r.add("tajuk_Dokumen",tajukDok);
//		      r.add("no_Rujukan_Dokumen", noRujDok);
//		      r.add("no_Rujukan_Lain", noRujLain);
//		      r.add("tarikh_Dokumen_Diterima", r.unquote(tarikhDokDiterima));
//		      r.add("nama_Pengirim", namaPengirim);
//		      r.add("id_NamaPenerima", idNamaPenerima);
			  r.add("id_Kemaskini",idKemaskini);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
			
			  sql = r.getSQLUpdate("tblpfddokumen");
		      stmt.executeUpdate(sql);
		      
		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.update("id_tagdokumen",id_tagdokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Kemaskini",r.unquote("sysdate")); 
		      t.add("id_Kemaskini",idKemaskini);
		      
    
		      sql3 = t.getSQLUpdate("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql3);
		      
		      
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
	    }
	
	public static void updateKeluar(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	    	  String idDokumen = (String)data.get("id_Dokumen");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
	    	  String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      //String tkhDokKeluar = (String)data.get("tarikh_Dokumen_Keluar");
		     // String tarikhDokKeluar = "to_date('" + tkhDokKeluar + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String idNamaPengirim = (String)data.get("id_nama_pengirim");
		      String idPAR = (String)data.get("id_PAR");
		      String namaPenerima = (String)data.get("namaPenerima");
		      String idFail = (String)data.get("id_Fail");
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String tag_dokumen = (String)data.get("tag_dokumen");
			  String id_tagdokumen = (String)data.get("id_tagdokumen");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Dokumen", idDokumen);
			  r.add("id_minit", idMinit);
			  r.add("id_laporan", idLaporan);
			  r.add("cd", idCD);
			  r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		      r.add("tarikh_Dokumen_Keluar", r.unquote(tarikhDok));
		      r.add("id_NamaPengirim", idNamaPengirim);
		      r.add("id_PAR", idPAR);
		      r.add("nama_Penerima", namaPenerima);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
			
			  sql = r.getSQLUpdate("tblpfddokumen");
		      stmt.executeUpdate(sql);
		      
		      String sqlInsert = "UPDATE TBLPFDLOGDOKUMEN SET ID_PTFAIL = ('"+idPAR+"') where id_logdokumen in";
		      String sqlSelect = "(select id_logdokumen from tblpfddokumen where id_dokumen = '"+idDokumen+"')";
		      
		      sql2 = sqlInsert+" "+sqlSelect;
		      stmt.executeUpdate(sql2);
		      
		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.update("id_tagdokumen",id_tagdokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Kemaskini",r.unquote("sysdate")); 
		      t.add("id_Kemaskini",idKemaskini);
		      
    
		      sql3 = t.getSQLUpdate("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql3);
		      
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
	    }
	public static void updateDalam(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql2 = "";
	    String sql3 = "";
	    try
	    {
	    	  String idDokumen = (String)data.get("id_Dokumen");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
	    	  String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String noRujLain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      //String tkhDokKeluar = (String)data.get("tarikh_Dokumen_Keluar");
		     // String tarikhDokKeluar = "to_date('" + tkhDokKeluar + "','dd/MM/yyyy')";
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";
		      String idNamaPengirim = (String)data.get("id_nama_pengirim");
		      String idPAR = (String)data.get("id_PAR");
		      String namaPenerima = (String)data.get("namaPenerima");
		      String idFail = (String)data.get("id_Fail");
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String tag_dokumen = (String)data.get("tag_dokumen");
			  String id_tagdokumen = (String)data.get("id_tagdokumen");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Dokumen", idDokumen);
			  r.add("id_minit", idMinit);
			  r.add("id_laporan", idLaporan);
			  r.add("cd", idCD);
			  r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("no_Rujukan_Lain", noRujLain);
		      r.add("tarikh_Dokumen_Keluar", r.unquote(tarikhDok));
		      r.add("id_NamaPengirim", idNamaPengirim);
		      r.add("id_PAR", idPAR);
		      r.add("nama_Penerima", namaPenerima);
			  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
			
			  sql = r.getSQLUpdate("tblpfddokumen");
		      stmt.executeUpdate(sql);
		      
		      String sqlInsert = "UPDATE TBLPFDLOGDOKUMEN SET ID_PTFAIL = ('"+idPAR+"') where id_logdokumen in";
		      String sqlSelect = "(select id_logdokumen from tblpfddokumen where id_dokumen = '"+idDokumen+"')";
		      
		      sql2 = sqlInsert+" "+sqlSelect;
		      stmt.executeUpdate(sql2);
		      
		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.update("id_tagdokumen",id_tagdokumen);
		      t.add("id_Dokumen",idDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Kemaskini",r.unquote("sysdate")); 
		      t.add("id_Kemaskini",idKemaskini);
		      
    
		      sql3 = t.getSQLUpdate("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql3);
		      
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
	    }
	public static void hapus(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPFDMINITARAHAN
			r.add("ID_DOKUMEN", idDokumen);
			sql = r.getSQLDelete("TBLPFDMINITARAHAN");
			stmt.executeUpdate(sql);
			
			//TBLPFDRUJLAMPIRAN
			r.add("ID_DOKUMEN", idDokumen);
			sql = r.getSQLDelete("TBLPFDRUJLAMPIRAN");
			stmt.executeUpdate(sql);
			
			//TBLPFDDOKUMEN
			r.add("ID_DOKUMEN", idDokumen);
			sql = r.getSQLDelete("TBLPFDDOKUMEN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	public static void hapusLampiran(String idLampiran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPFDRUJLAMPIRAN
			r.add("ID_LAMPIRAN", idLampiran);
			sql = r.getSQLDelete("TBLPFDRUJLAMPIRAN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	 public static void  setListLampiran(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    try {
		    	listLampiran = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Lampiran");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		     
		      
		      r.add("a.id_Dokumen",id);

		      sql = r.getSQLSelect("Tblpfdrujlampiran a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      

		      
		      
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_Lampiran", rs.getString("id_Lampiran"));
		    	  h.put("nama_Fail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("id_Lampiran", "");
		    	  h.put("nama_Fail","Tiada rekod.");
		    	  h.put("jenis_Mime", "");
		    	  listLampiran.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListLampiran(){
			 
		return listLampiran;
	}
	
	public static void setDataMinit(String id)throws Exception {
		
		 Db db = null;
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			  paparMinit = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("a.minit_Arahan");
		      r.add("a.catatan");
		     // r.add("b.id_Pegawai as PegawaiA");
		     // r.add("c.id_Pegawai as PegawaiB");
		      r.add("a.id_statusTindakan");
		      r.add("a.tarikh_Minit_Arahan");
		      
		     // r.add("a.id_Pegawai_Ygmengarah",r.unquote("b.id_Pegawai"));
		     // r.add("a.id_Pegawai_Ygmenerima",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Minitarahan",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitarahan a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("sql---"+sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", rs.getString("id_Minitarahan"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan")== null?"":rs.getString("minit_Arahan"));
		    	  h.put("catatan", rs.getString("catatan")== null?"":rs.getString("catatan"));
		    	 // h.put("pegawai_Mengarah",rs.getString("PegawaiA")== null?"":rs.getString("PegawaiA"));
		    	//  h.put("pegawai_Menerima",rs.getString("PegawaiB")== null?"":rs.getString("PegawaiB"));
		    	  h.put("tarikh_Minit_Arahan", rs.getString("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  h.put("id_statusTindakan", rs.getString("id_statustindakan")== null?0:rs.getString("id_statustindakan"));
		    	  paparMinit.addElement(h);
		    	  
		    	  
		      }
		      
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	public static Vector getPaparMinit(){
		 
		return paparMinit;
	}
	
	public static String  addMinit(Hashtable data)throws Exception {
		
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql3 = "";
	    String sql4 = "";
	    String sqlx = "";
	    String sqly1 = "";
	    String sqly2 = "";
	    String sqlz1 = "";
	    String sqlz2 = "";
	    String sqlz3 = "";
	   
	    
	    Date now = new Date();
	    try
	    {	 
	    	  long idMinitarahan = DB.getNextID("TBLPFDMINITARAHAN_SEQ");
	    	  String idDokumen = (String)data.get("id_Dokumen");
	    	  String idMinitArahanSebelum = (String)data.get("idMinitArahanSebelum");
	    	  String minitArahan = (String)data.get("minit_Arahan")== null?"":(String)data.get("minit_Arahan");
	    	  String catatan = (String)data.get("catatan")== null?"":(String)data.get("catatan");
	    	  String level = (String)data.get("level");
	    	  String idSeksyen = (String)data.get("idSeksyen");
	    	  String idNegeri = (String)data.get("idNegeri");
		      String pegawaiMengarah = (String)data.get("id_Pegawai_Ygmengarah")== null?"":(String)data.get("id_Pegawai_Ygmengarah");
		      String pegawaiMenerima1 = (String)data.get("id_Pegawai_Ygmenerima1")== null?"":(String)data.get("id_Pegawai_Ygmenerima1");
		      String pegawaiMenerima2 = (String)data.get("id_Pegawai_Ygmenerima2")== null?"":(String)data.get("id_Pegawai_Ygmenerima2");
		      String pegawaiMenerima3 = (String)data.get("id_Pegawai_Ygmenerima3")== null?"":(String)data.get("id_Pegawai_Ygmenerima3");
		      String statusTindakan = (String)data.get("id_status_Tindakan")== null?"":(String)data.get("id_status_Tindakan");
		      String tkhMinitArahan = (String)data.get("tarikh_Minit_Arahan");
			  String tarikhMinitArahan = "to_date('" + tkhMinitArahan + "','dd/MM/yyyy')";
			  String id_Masuk = (String)data.get("idMasuk");

		   
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Minitarahan",idMinitarahan);
		      r.add("id_Dokumen",idDokumen);
		      r.add("minit_Arahan", minitArahan);
		      r.add("catatan", catatan);
		      r.add("level_arahan", level);
		      r.add("id_seksyen", idSeksyen);
		      r.add("id_negeri", idNegeri);
		      r.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
		      r.add("id_Pegawai_Ygmenerima1", pegawaiMenerima1);
		      r.add("id_Pegawai_Ygmenerima2", pegawaiMenerima2);
		      r.add("id_Pegawai_Ygmenerima3", pegawaiMenerima3);
		      r.add("id_statustindakan", statusTindakan);
		      r.add("tarikh_Minit_Arahan",r.unquote(tarikhMinitArahan)); 
		      
		      sql = r.getSQLInsert("tblpfdminitarahan");     
		      stmt.executeUpdate(sql);
		      
		      if(!"".equalsIgnoreCase(pegawaiMenerima1) && "".equalsIgnoreCase(pegawaiMenerima2) && "".equalsIgnoreCase(pegawaiMenerima3))
		      {
			      String sqlInsert = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";

			      sqlx = sqlInsert;
			      stmt.execute(sqlx);
		      }
		      
		      else if(!"".equalsIgnoreCase(pegawaiMenerima1) && !"".equalsIgnoreCase(pegawaiMenerima2) && "".equalsIgnoreCase(pegawaiMenerima3))
		      {
			      String sqlInserty1 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";

			      sqly1 = sqlInserty1;
			      stmt.execute(sqly1);
			      
			      String sqlInserty2 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima2+"')";

			      sqly2 = sqlInserty2;
			      stmt.execute(sqly2);
		      }
		      
		      else if(!"".equalsIgnoreCase(pegawaiMenerima1) && !"".equalsIgnoreCase(pegawaiMenerima2) && !"".equalsIgnoreCase(pegawaiMenerima3))
		      {
			      String sqlInsertz1 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";

			      sqlz1 = sqlInsertz1;
			      stmt.execute(sqlz1);
			      
			      String sqlInsertz2 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima2+"')";

			      sqlz2 = sqlInsertz2;
			      stmt.execute(sqlz2);
			      
			      String sqlInsertz3 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima3+"')";

			      sqlz3 = sqlInsertz3;
			      stmt.execute(sqlz3);
			      
		      }
		      
		      else 
		      {

		      }
		      
		      
		      
		      if("1".equalsIgnoreCase(level))
		      {
			      String sqlUpdate1 = "UPDATE TBLPFDDOKUMEN SET STATUS_MINIT_PENGARAH = '0' WHERE id_Dokumen = '"+idDokumen+"'";
	
			      sql1 = sqlUpdate1;
			      stmt.executeUpdate(sql1);
		      }
		      else
		      {
//			      String sqlUpdate2 = "UPDATE TBLPFDMINITARAHANPEGAWAI SET STATUS_TUGASAN = '0' " +
//			      					  "WHERE id_Minitarahan = '"+idMinitArahanSebelum+"' " +
//			      					  "AND ID_PEGAWAIPENERIMA = '"+id_Masuk+"'";
//				     
//			      sql2 = sqlUpdate2;
//			      stmt.executeUpdate(sql2);
		      }
		      
		      if("99".equalsIgnoreCase(statusTindakan)){
			      String sqlUpdate3 = "UPDATE TBLPFDMINITARAHAN SET STATUS_TUGASAN = '0' WHERE id_Minitarahan = '"+idMinitarahan+"'";
				     
			      sql3 = sqlUpdate3;
			      stmt.executeUpdate(sql3);
			      
			      String sqlUpdate4 = "UPDATE TBLPFDMINITARAHANPEGAWAI SET STATUS_TUGASAN = '0' WHERE id_Minitarahan = '"+idMinitArahanSebelum+"' " +
			      					  "AND ID_PEGAWAIYGMENERIMA = '"+pegawaiMengarah+"'";
				     
			      sql4 = sqlUpdate4;
			      stmt.executeUpdate(sql4);
		      }
		      
		      return ""+idMinitarahan;
		      
		    } finally {
		      if (db != null) db.close();
		    }

	}
	
	
	public static void updateMinit(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sqlx = "";
	    String sqly1 = "";
	    String sqly2 = "";
	    String sqlz1 = "";
	    String sqlz2 = "";
	    String sqlz3 = "";
	    try
	    {
	    	
	    	  String idMinitarahan = (String)data.get("id_Minitarahan");
	    	  String minitArahan = (String)data.get("minit_Arahan")== null?"":(String)data.get("minit_Arahan");
	    	  String catatan = (String)data.get("catatan")== null?"":(String)data.get("catatan");
		      String pegawaiMengarah = (String)data.get("id_Pegawai_Ygmengarah")== null?"":(String)data.get("id_Pegawai_Ygmengarah");
		      String pegawaiMenerima1 = (String)data.get("id_Pegawai_Ygmenerima1")== null?"":(String)data.get("id_Pegawai_Ygmenerima1");
		      String pegawaiMenerima2 = (String)data.get("id_Pegawai_Ygmenerima2")== null?"":(String)data.get("id_Pegawai_Ygmenerima2");
		      String pegawaiMenerima3 = (String)data.get("id_Pegawai_Ygmenerima3")== null?"":(String)data.get("id_Pegawai_Ygmenerima3");
		      String statusTindakan = (String)data.get("id_status_Tindakan")== null?"":(String)data.get("id_status_Tindakan");
		      String tkhMinitArahan = (String)data.get("tarikh_Minit_Arahan");
			  String tarikhMinitArahan = "to_date('" + tkhMinitArahan + "','dd/MM/yyyy')";
			  String id_Masuk = (String)data.get("idMasuk");

		   
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.update("id_Minitarahan",idMinitarahan);
		      r.add("minit_Arahan", minitArahan);
		      r.add("catatan", catatan);
		      r.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
		      r.add("id_Pegawai_Ygmenerima1", pegawaiMenerima1);
		      r.add("id_Pegawai_Ygmenerima2", pegawaiMenerima2);
		      r.add("id_Pegawai_Ygmenerima3", pegawaiMenerima3);
		      r.add("id_statustindakan", statusTindakan);
		      r.add("tarikh_Minit_Arahan",r.unquote(tarikhMinitArahan)); 
		      
		      sql = r.getSQLUpdate("tblpfdminitarahan");    
		      stmt.executeUpdate(sql);
		      

          	  	// delete idminitarahan dalam TBLPFDMINITARAHANPEGAWAI 
          	  	deleteIdMinitArahan(idMinitarahan);	
          			
          	  	// insert semula peegawai baru yang telah di update ke dalam TBLPFDMINITARAHANPEGAWAI
          			  if(!"".equalsIgnoreCase(pegawaiMenerima1) && "".equalsIgnoreCase(pegawaiMenerima2) && "".equalsIgnoreCase(pegawaiMenerima3))
       			      {
       				      String sqlInsert = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";
       	
       				      sqlx = sqlInsert;
       				      stmt.execute(sqlx);
       			      }
       			      
       			      else if(!"".equalsIgnoreCase(pegawaiMenerima1) && !"".equalsIgnoreCase(pegawaiMenerima2) && "".equalsIgnoreCase(pegawaiMenerima3))
       			      {
       				      String sqlInserty1 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";
       	
       				      sqly1 = sqlInserty1;
       				      stmt.execute(sqly1);
       				      
       				      String sqlInserty2 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima2+"')";
       	
       				      sqly2 = sqlInserty2;
       				      stmt.execute(sqly2);
       			      }
       			      
       			      else if(!"".equalsIgnoreCase(pegawaiMenerima1) && !"".equalsIgnoreCase(pegawaiMenerima2) && !"".equalsIgnoreCase(pegawaiMenerima3))
       			      {
       				      String sqlInsertz1 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima1+"')";
       	
       				      sqlz1 = sqlInsertz1;
       				      stmt.execute(sqlz1);
       				      
       				      String sqlInsertz2 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima2+"')";
       	
       				      sqlz2 = sqlInsertz2;
       				      stmt.execute(sqlz2);
       				      
       				      String sqlInsertz3 = "INSERT INTO TBLPFDMINITARAHANPEGAWAI (ID_MINITARAHAN, ID_PEGAWAIYGMENERIMA) VALUES ('"+idMinitarahan+"','"+pegawaiMenerima3+"')";
       	
       				      sqlz3 = sqlInsertz3;
       				      stmt.execute(sqlz3);
       				      
       			      }
       			      
       			      else 
       			      {
       	
       			      }
  
		    }
	   	
		    finally {
		      if (db != null) db.close();
		    }
	    }

	public static void hapusMinit(String idMinitarahan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("ID_MINITARAHAN", idMinitarahan);
			sql = r.getSQLDelete("TBLPFDMINITARAHAN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector getDataDokumen(String idDokumen) throws Exception {
		
			 Db db = null;
			 String sql = "";
			 Date now = new Date();
			 try {
				 paparDokumen = new Vector();
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("a.id_Dokumen");
			      r.add("a.bil_Minit_Dokumen");
			      r.add("a.id_Jenisdokumen");
			      r.add("a.no_Rujukan_Dokumen");
			      r.add("a.no_Rujukan_Lain");
			      r.add("a.tajuk_Dokumen");
			      r.add("a.tarikh_Dokumen");
			      r.add("a.tarikh_Dokumen_Diterima");
			      r.add("a.nama_Pengirim");
			      r.add("a.nama_Penerima");
			      r.add("a.id_NamaPengirim");
			      r.add("a.id_Minit");
			      r.add("a.id_Laporan");
			      r.add("a.CD");
			      r.add("a.id_NamaPenerima");
			      r.add("a.flag_Dokumen");  
			      r.add("b.tag_Dokumen");
			      r.add("b.id_tagdokumen");
			      r.add("a.id_Dokumen",idDokumen);
			      r.add("b.id_Dokumen(+)",r.unquote("a.id_Dokumen"));
			     
			      
			      sql = r.getSQLSelect("Tblpfddokumen a,Tblpfdtagdokumen b");
			     
			      System.out.println("sql papar dokumen---"+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = new Hashtable();
			      while (rs.next()) {
			    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
			    	  h.put("bil_Minit_Dokumen", rs.getString("bil_Minit_Dokumen") == null?"":rs.getString("bil_Minit_Dokumen"));
			    	  h.put("jenis_Dokumen",rs.getString("id_Jenisdokumen"));
			    	  h.put("no_Rujukan_Dokumen",rs.getString("no_Rujukan_Dokumen") == null?"":rs.getString("no_Rujukan_Dokumen"));
			    	  h.put("no_Rujukan_Lain",rs.getString("no_Rujukan_Lain")== null?"":rs.getString("no_Rujukan_Lain"));
			    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
			    	  h.put("tarikh_Dokumen", rs.getDate("tarikh_Dokumen")== null?"":sdf.format(rs.getDate("tarikh_Dokumen")));
			    	  h.put("tarikh_Dokumen_Diterima", rs.getDate("tarikh_Dokumen_Diterima")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Diterima")));
			    	  h.put("id_NamaPengirim",rs.getString("id_NamaPengirim")== null?"":rs.getString("id_NamaPengirim"));
			    	  h.put("id_NamaPenerima",rs.getString("id_NamaPenerima")== null?"":rs.getString("id_NamaPenerima"));
			    	  h.put("nama_Pengirim",rs.getString("nama_Pengirim")== null?"":rs.getString("nama_Pengirim"));
			    	  h.put("nama_Penerima",rs.getString("nama_Penerima")== null?"":rs.getString("nama_Penerima"));
			    	  h.put("idMinit",rs.getString("id_Minit")== null?"":rs.getString("id_Minit"));
			    	  h.put("idLaporan",rs.getString("id_Laporan")== null?"":rs.getString("id_Laporan"));
			    	  h.put("idCD",rs.getString("CD")== null?"":rs.getString("CD"));
			    	  h.put("id_nama_Pengirim",rs.getString("id_NamaPengirim")== null?"":rs.getString("id_NamaPengirim"));
			    	  h.put("id_nama_Penerima",rs.getString("id_NamaPenerima")== null?"":rs.getString("id_NamaPenerima"));
			    	  h.put("flag_Dokumen",rs.getString("flag_Dokumen"));
			    	  h.put("tag_Dokumen",rs.getString("tag_Dokumen")== null?"":rs.getString("tag_Dokumen"));
			    	  h.put("id_tagdokumen",rs.getString("id_tagdokumen")==null?"":rs.getString("id_tagdokumen"));
			    	  
			    	  paparDokumen.addElement(h); 
			      } 
						
			      return paparDokumen;
				}
					finally {
					if (db != null)
					db.close();
				}
	}
	public static Vector getDataDokumen2(String idDokumen2) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Dokumen");
		      r.add("a.bil_Minit_Dokumen");
		      r.add("a.id_Jenisdokumen");
		      r.add("a.no_Rujukan_Dokumen");
		      r.add("a.no_Rujukan_Lain");
		      r.add("a.tajuk_Dokumen");
		      r.add("a.tarikh_Dokumen");
		      r.add("a.tarikh_Dokumen_Diterima");
		    //  r.add("a.tarikh_Dokumen_Masuk");
		      r.add("a.id_NamaPengirim");
		      r.add("a.id_NamaPenerima");
		      r.add("a.Nama_Pengirim");
		      r.add("a.Nama_Penerima");
		      r.add("a.id_minit");
		      r.add("a.id_laporan");
		      r.add("a.cd");
		      r.add("a.flag_Dokumen");  
		      r.add("a.id_Dokumen",idDokumen2);
		      r.add("b.tag_Dokumen");
		      r.add("b.id_tagdokumen");
		      r.add("b.id_Dokumen(+)",r.unquote("a.id_Dokumen"));
		     
		    
		      sql = r.getSQLSelect("Tblpfddokumen a,Tblpfdtagdokumen b");
		    
		     
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      while (rs.next()) {
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("bil_Minit_Dokumen", rs.getString("bil_Minit_Dokumen")==null?"":rs.getString("bil_Minit_Dokumen"));
		    	  h.put("jenis_Dokumen",rs.getString("id_Jenisdokumen"));
		    	  h.put("no_Rujukan_Dokumen",rs.getString("no_Rujukan_Dokumen")==null?"":rs.getString("no_Rujukan_Dokumen"));
		    	  h.put("no_Rujukan_Lain",rs.getString("no_Rujukan_Lain")== null?"":rs.getString("no_Rujukan_Lain"));
		    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  h.put("tarikh_Dokumen", rs.getDate("tarikh_Dokumen")== null?"":sdf.format(rs.getDate("tarikh_Dokumen")));
		    	  h.put("tarikh_Dokumen_Diterima", rs.getDate("tarikh_Dokumen_Diterima")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Diterima")));
		    	 // h.put("tarikh_Dokumen_Masuk",rs.getDate("tarikh_Dokumen_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Dokumen_Masuk")));
		    	  h.put("id_nama_Pengirim",rs.getString("id_NamaPengirim")== null?"":rs.getString("id_NamaPengirim"));
		    	  h.put("id_nama_Penerima",rs.getString("id_NamaPenerima")== null?"":rs.getString("id_NamaPenerima"));
		    	  h.put("nama_Pengirim",rs.getString("Nama_Pengirim")== null?"":rs.getString("Nama_Pengirim"));
		    	  h.put("nama_Penerima",rs.getString("Nama_Penerima")== null?"":rs.getString("Nama_Penerima"));
		    	  h.put("idMinit",rs.getString("id_Minit")== null?"":rs.getString("id_Minit"));
		    	  h.put("idLaporan",rs.getString("id_Laporan")== null?"":rs.getString("id_Laporan"));
		    	  h.put("idCD",rs.getString("cd")== null?"":rs.getString("cd"));
		    	  h.put("flag_Dokumen",rs.getString("flag_Dokumen"));
		    	  h.put("tag_Dokumen",rs.getString("tag_Dokumen")== null?"":rs.getString("tag_Dokumen"));
		    	  h.put("id_tagdokumen",rs.getString("id_tagdokumen")== null?"":rs.getString("id_tagdokumen"));
		    	  
		    	  paparDokumen.addElement(h); 
		      } 
					
		      return paparDokumen;
			}
				finally {
				if (db != null)
				db.close();
			}
	}


	public static void setDataDokumenFail(String id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparDokumenFail = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		     // sql = "SELECT MAX(bil_Minit_Dokumen) AS B FROM TBLPFDDOKUMEN WHERE ID_FAIL = '"+id+"'";
		      sql = "SELECT MAX(TO_NUMBER(BIL_MINIT_DOKUMEN)) AS B FROM TBLPFDDOKUMEN WHERE ID_FAIL = '"+id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("bil_Minit_Dokumen", rs.getString("B")==null?"0":rs.getString("B"));
		    	  paparDokumenFail.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparDokumenFail.addElement(h);
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
	}
	public static Vector getDataDokumenFail(String idFail) {
	
		return paparDokumenFail;
	}

	public static void setDataNoDokumen(String id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparNoDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("no_Rujukan_Dokumen", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
		    	  paparNoDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("no_Rujukan_Dokumen","");
		    	  paparNoDokumen.addElement(h);
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
	}
	
	public static Vector getDataNoDokumen(String idFail) {
		
		return paparNoDokumen;
	}
	

	public static void setDataCountDokumen(String id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparCountDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT COUNT(ID_FAIL) AS B FROM TBLPFDDOKUMEN WHERE ID_FAIL = '"+id+"'";
		      //sql = "SELECT MAX(TO_NUMBER(BIL_MINIT_DOKUMEN)) AS B FROM TBLPFDDOKUMEN WHERE ID_FAIL = '"+id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("jumlah_Dokumen", rs.getString("B")==null?"":rs.getString("B"));
		    	  paparCountDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("jumlah_Dokumen","");
		    	  paparCountDokumen.addElement(h);
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    } 
		
	}


	public static Vector getDataCountDokumen(String idFail) {
		
		return paparCountDokumen;
	}

	public static Vector getPaparMinit(String idMinitarahan) throws Exception {
		 Db db = null;
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			  paparMinit = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
//		      sql = "SELECT a.ID_MINITARAHAN, a.minit_Arahan, a.id_statusTindakan, a.tarikh_Minit_Arahan, a.CATATAN, b.ID_PEGAWAIYGMENERIMA FROM TBLPFDMINITARAHAN a, TBLPFDMINITARAHANPEGAWAI b WHERE ID_MINITARAHAN = '"+idMinitarahan+"'";
//		      ResultSet rs = stmt.executeQuery(sql);
//		      
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("a.minit_Arahan");
		      r.add("a.id_statusTindakan");
		      r.add("a.id_pegawai_ygmenerima1");
		      r.add("a.id_pegawai_ygmenerima2");
		      r.add("a.id_pegawai_ygmenerima3");
		      r.add("a.tarikh_Minit_Arahan");
		      r.add("a.catatan");
		      r.add("a.id_Minitarahan",idMinitarahan);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitarahan a");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("sql---"+sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", rs.getString("id_Minitarahan"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan")== null?"":rs.getString("minit_Arahan"));
		    	  h.put("catatan", rs.getString("catatan")== null?"":rs.getString("catatan"));
		    	  h.put("id_pegawai_ygmenerima1", rs.getString("id_pegawai_ygmenerima1")== null?"":rs.getString("id_pegawai_ygmenerima1"));
		    	  h.put("id_pegawai_ygmenerima2", rs.getString("id_pegawai_ygmenerima2")== null?"":rs.getString("id_pegawai_ygmenerima2"));
		    	  h.put("id_pegawai_ygmenerima3", rs.getString("id_pegawai_ygmenerima3")== null?"":rs.getString("id_pegawai_ygmenerima3"));
		    	  h.put("tarikh_Minit_Arahan", rs.getString("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  h.put("id_statusTindakan", rs.getString("id_statustindakan")== null?0:rs.getString("id_statustindakan"));
		    	  paparMinit.addElement(h);
		    	  
		    	  
		      }
		      
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }
		return paparMinit;
		
	}
	public static Vector getPaparMinitArahan2(String idMinitarahan2) throws Exception {
		 Db db = null;
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			 paparMinitArahan2b = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("a.minit_Arahan");
		      r.add("b.id_Pegawai as PegawaiA");
		      r.add("c.id_Pegawai as PegawaiB");
		      r.add("a.id_statusTindakan");
		      r.add("a.tarikh_Minit_Arahan");
		      r.add("a.catatan");
		      r.add("a.id_Pegawai_Ygmengarah",r.unquote("b.id_Pegawai"));
		      r.add("a.id_Pegawai_Ygmenerima",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Minitarahan",idMinitarahan2);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitarahan a, Tblrujpegawai b, Tblrujpegawai c");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("sql---"+sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", rs.getString("id_Minitarahan"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan"));
		    	  h.put("catatan", rs.getString("catatan"));
		    	  h.put("pegawai_Mengarah",rs.getString("PegawaiA")== null?"":rs.getString("PegawaiA"));
		    	  h.put("pegawai_Menerima",rs.getString("PegawaiB")== null?"":rs.getString("PegawaiB"));
		    	  h.put("tarikh_Minit_Arahan", rs.getString("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  h.put("id_statusTindakan", rs.getString("id_statustindakan")== null?0:rs.getString("id_statustindakan"));
		    	  paparMinitArahan2b.addElement(h);
		    	  
		    	  
		      }
		      
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }
		return paparMinitArahan2b;
	}
	public static Vector getPaparMinitArahan3(String idMinitarahan3) throws Exception {
		 Db db = null;
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			  paparMinitArahan2c = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("a.minit_Arahan");
		      r.add("b.id_Pegawai as PegawaiA");
		      r.add("c.id_Pegawai as PegawaiB");
		      r.add("a.id_statusTindakan");
		      r.add("a.tarikh_Minit_Arahan");
		      r.add("a.catatan");
		      r.add("a.id_Pegawai_Ygmengarah",r.unquote("b.id_Pegawai"));
		      r.add("a.id_Pegawai_Ygmenerima",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Minitarahan",idMinitarahan3);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitarahan a, Tblrujpegawai b, Tblrujpegawai c");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("sql---"+sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", rs.getString("id_Minitarahan"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan"));
		    	  h.put("catatan", rs.getString("catatan"));
		    	  h.put("pegawai_Mengarah",rs.getString("PegawaiA")== null?"":rs.getString("PegawaiA"));
		    	  h.put("pegawai_Menerima",rs.getString("PegawaiB")== null?"":rs.getString("PegawaiB"));
		    	  h.put("tarikh_Minit_Arahan", rs.getString("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  h.put("id_statusTindakan", rs.getString("id_statustindakan")== null?0:rs.getString("id_statustindakan"));
		    	  paparMinitArahan2c.addElement(h);
		    	  
		    	  
		      }
		      
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }
		return paparMinitArahan2c;
	}

	public static void setListPegawaiAtas(String user_id, String user_negeri) throws Exception {
		Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
//	      sql = "select user_id,user_name from users where user_id in " +
//	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
//	      		"and id_jawatan not in (0,1,2,3,4))";
	      
	      sql =  " SELECT U.user_id,U.user_name FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN not IN (0,1,2,3)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') ";	   
	      
	      
	      sql += "ORDER BY UI.ID_JAWATAN ASC ";

	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

			  senaraiPegawai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	public static Vector getListPegawaiAtas() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	
	public static void setListPegawai1(String user_id, String user_negeri) throws Exception {
		Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
//	      sql = "select user_id,user_name from users where user_id in " +
//	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
//	      		"and id_jawatan not in (0,1,2,3,4))";
	      
	      sql =  " SELECT U.user_id,U.user_name,TJ.shortname_jawatan FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN TJ WHERE U.USER_ID = UI.USER_ID and UI.ID_JAWATAN = TJ.ID_JAWATAN ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3,4)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk','partest', 'testpt' ) and u.user_login not in ('partest', 'testpt')";	   
	      
	      
	      sql += "ORDER BY U.user_name ASC ";

	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
			  h.put("keterangan",rs.getString("shortname_jawatan")==null?"":rs.getString("shortname_jawatan"));
				

			  senaraiPegawai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	public static Vector getListPegawai1() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	
	public static void setListPegawai2(String user_id, String user_negeri) throws Exception {
		Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
//	      sql = "select user_id,user_name from users where user_id in " +
//    		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
//    		"and id_jawatan not in (0,1,2,3,4))";
    
		    sql =  " SELECT U.user_id,U.user_name,TJ.shortname_jawatan FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN TJ WHERE U.USER_ID = UI.USER_ID and UI.ID_JAWATAN = TJ.ID_JAWATAN ";
		    
		    if(user_negeri.equals("16"))
		    {      
		    sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
		  		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
		    }	
		    
		    
		    sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3,4)"+
		  		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') and u.user_login not in ('partest', 'testpt')";	   
		    
		    
		    sql += "ORDER BY U.user_name ASC ";
		
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    Hashtable h;
		    int bil = 1;
		    int count = 0;
		    while (rs.next()) {
		  	  h = new Hashtable();
		  	  h.put("bil", bil);
		  	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  h.put("keterangan",rs.getString("shortname_jawatan")==null?"":rs.getString("shortname_jawatan"));
					
		
				  senaraiPegawai.addElement(h);
		  	  bil++;
		  	  count++;
		    }
		
		  } finally {
		    if (db != null) db.close();
		  }
		
	}
	public static Vector getListPegawai2() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	
	public static void setListPegawai3(String user_id, String user_negeri) throws Exception {
		Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
//	      sql = "select user_id,user_name from users where user_id in " +
//	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
//	      		"and id_jawatan not in (0,1,2,3,4))";
		    
		    sql =  " SELECT U.user_id,U.user_name,TJ.shortname_jawatan FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN TJ WHERE U.USER_ID = UI.USER_ID and UI.ID_JAWATAN = TJ.ID_JAWATAN ";
		    
		    if(user_negeri.equals("16"))
		    {      
		    sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
		  		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
		    }	
		    
		    
		    sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3,4)"+
		  		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk','partest', 'testpt' ) and u.user_login not in ('partest', 'testpt')";	   
		    
		    
		    sql += "ORDER BY U.user_name ASC ";
		
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    Hashtable h;
		    int bil = 1;
		    int count = 0;
		    while (rs.next()) {
		  	  h = new Hashtable();
		  	  h.put("bil", bil);
		  	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  h.put("keterangan",rs.getString("shortname_jawatan")==null?"":rs.getString("shortname_jawatan"));
					
		
				  senaraiPegawai.addElement(h);
		  	  bil++;
		  	  count++;
		    }
		
		  } finally {
		    if (db != null) db.close();
		  }
		
	}
	public static Vector getListPegawai3() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	public static Vector getDataPegawai1(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA1 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getDataPegawai2(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA2 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getDataPegawai3(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA3 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getPaparDataPegawai(String idDokumen2) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_NAMAPENGIRIM AND A.ID_DOKUMEN = '"+idDokumen2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getDataSimpanLogDokumen(String idLogDokumen, String idDokumen) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void setListPA(String user_id) throws Exception {
		Db db = null;
		listPA = new Vector();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "select user_id,user_name from users where user_id in " +
	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
	      		"and id_negeri =(select id_negeri from users_internal where user_id="+user_id+")" +
	      		"and id_jawatan in (20,21,22,24,27,28)) order by user_name";

	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

			  listPA.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	public static Vector getListPA() {
		// TODO Auto-generated method stub
		return listPA;
	}
	public static void setLampiranDokumen(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_dokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiran where id_dokumen = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_dokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranDokumen.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
	}
	public static Vector getListLampiranDokumen() {
		// TODO Auto-generated method stub
		return paparLampiranDokumen;
	}
	public static String addSubjaket(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql2 = "";
	   
	    try
	    {	 
	    	  long idDokumen = DB.getNextID("TBLPFDDOKUMEN_SEQ");
	    	  String flagDokumen = (String)data.get("flag_Dokumen");
		      String bilMinitDokumen = (String)data.get("bil_Minit_Dokumen");
		      String idJenisdokumen = (String)data.get("id_Jenisdokumen");
		      String noRujDok = (String)data.get("no_Rujukan_Dokumen");
		      String idFailSubjaket = (String)data.get("idFailSubjaket");
		      String tajukDok = (String)data.get("tajuk_Dokumen");
		      String idFail = (String)data.get("id_Fail");
		      String idMasuk = (String)data.get("id_Masuk");
		      
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Dokumen",idDokumen);
		      r.add("id_Fail",idFail);
		      r.add("flag_Dokumen", flagDokumen);
		      r.add("bil_Minit_Dokumen", bilMinitDokumen);
		      r.add("id_FailSubjaket", idFailSubjaket);
		      r.add("id_Jenisdokumen", idJenisdokumen);
		      r.add("tajuk_Dokumen",tajukDok);
		      r.add("no_Rujukan_Dokumen", noRujDok);
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		      r.add("id_Masuk",idMasuk);
		      
		      sql = r.getSQLInsert("tblpfddokumen");  
		      stmt.executeUpdate(sql);
		      
		       
//		      sql2 = "update ";
//		      stmt.executeUpdate(sql2);
		      
		      
		      return ""+idDokumen;
		    } finally {
		      if (db != null) db.close();
		    }
	}
	public Vector getPARbyIdSeksyen(String socSeksyenPAR) throws Exception {
		Vector v = null;
		v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  

 	      sql = "select user_id,user_name from users where user_id in (select user_id from users_internal where id_seksyen ='"+socSeksyenPAR+"')";
 	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getString("user_id") == null ? "" : rs.getString("user_id"));
				h.put("NAMA_PEGAWAI", rs.getString("user_name") == null ? "" : rs.getString("user_name").toUpperCase());
				v.addElement(h);
			}
 
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
 		}
	public static String addMinitSeksyenLain(Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		    String sql3 = "";
		   
		    try
		    {	 
			    
		    	  long idMinitArahanSeksyenLain = DB.getNextID("TBLPFDMINITARAHANSEKLAIN_SEQ");
		    	  long idMinitarahan = DB.getNextID("TBLPFDMINITARAHAN_SEQ");
		    	  String idDokumen = (String)data.get("idDokumen");
			      String no_Rujukan_Dokumen = (String)data.get("no_Rujukan_Dokumen");
			      String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String idSeksyen = (String)data.get("idSeksyen");
			      String pegawaiMenerima = (String)data.get("pegawaiMenerima");
			      String catatan = (String)data.get("catatan");
			      String level = (String)data.get("level");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhArahan");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("id_statusdokumen");
			      String idMasuk = (String)data.get("idMasuk");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_MINITARAHANSEKLAIN",idMinitArahanSeksyenLain);
			      r.add("id_DOKUMEN",idDokumen);
			      r.add("no_rujukan_dokumen",no_Rujukan_Dokumen );
			      r.add("tajuk_Dokumen",tajuk_Dokumen );
			      r.add("id_pegawai_ygmengarah", pegawaiMengarah);
			      r.add("id_pegawai_ygmenerima", pegawaiMenerima);
			      r.add("id_seksyen",idSeksyen);
			      r.add("catatan",catatan );
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      r.add("id_Masuk",idMasuk);
			      
			      sql = r.getSQLInsert("TBLPFDMINITARAHANSEKLAIN");  
			      stmt.executeUpdate(sql);
			      
			      
			      SQLRenderer s = new SQLRenderer();
			      
			      s.add("id_Minitarahan",idMinitarahan);
			      s.add("id_Dokumen",idDokumen);
			      //s.add("minit_Arahan", minitArahan);
			      s.add("catatan", catatan);
			      s.add("level_arahan", level);
			      s.add("id_seksyen", idSeksyen);
			      //s.add("id_negeri", idNegeri);
			      s.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
			      s.add("id_Pegawai_Ygmenerima1", pegawaiMenerima);
			      //s.add("id_Pegawai_Ygmenerima2", pegawaiMenerima2);
			      //s.add("id_Pegawai_Ygmenerima3", pegawaiMenerima3);
			      s.add("id_statustindakan", "0");
			      s.add("tarikh_Minit_Arahan",s.unquote(tarikhArahan)); 
			      
			      sql3 = s.getSQLInsert("tblpfdminitarahan");     
			      stmt.executeUpdate(sql3);
			      
			      
			      String sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_dokumen, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			      String sqlSelect = "select '"+pegawaiMenerima+"',id_dokumen,nama_pengirim, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumen_diterima, no_rujukan_lain, id_minit, id_laporan,'3','1','"+catatan+"','"+pegawaiMengarah+"' from tblpfddokumen where id_dokumen = '"+idDokumen+"'";
			      
			      sql1 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql1);
			      
			      
			      if("1".equalsIgnoreCase(level))
			      {
				      String sqlUpdate1 = "UPDATE TBLPFDDOKUMEN SET STATUS_MINIT_PENGARAH = '0' WHERE id_Dokumen = '"+idDokumen+"'";
		
				      sql2 = sqlUpdate1;
				      stmt.executeUpdate(sql2);
			      }
			      else
			      {
//				      String sqlUpdate2 = "UPDATE TBLPFDMINITARAHANPEGAWAI SET STATUS_TUGASAN = '0' " +
//				      					  "WHERE id_Minitarahan = '"+idMinitArahanSebelum+"' " +
//				      					  "AND ID_PEGAWAIPENERIMA = '"+id_Masuk+"'";
//					     
//				      sql2 = sqlUpdate2;
//				      stmt.executeUpdate(sql2);
			      }
	      
			      return ""+idMinitArahanSeksyenLain;
			    } finally {
			      if (db != null) db.close();
			    }
	
	}
	public static Vector getDataMinitArahanSeksyenLain(String idMinitArahanSeksyenLain) throws Exception {
		Db db = null;
		 String sql = "";
		
		 Date now = new Date();
		 try {
			 paparMinitArahanSeksyenLain = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_MINITARAHANSEKLAIN, A.ID_DOKUMEN, A.TAJUK_DOKUMEN, A.TARIKH_MINIT_ARAHAN, A.ID_PEGAWAI_YGMENGARAH, B.USER_NAME AS PEGAWAI_MENGARAH, A.ID_PEGAWAI_YGMENERIMA, C.USER_NAME AS PEGAWAI_MENERIMA, A.CATATAN, A.NO_RUJUKAN_DOKUMEN, A.ID_SEKSYEN, " +
		      		"A.ID_STATUSDOKUMEN FROM TBLPFDMINITARAHANSEKLAIN A, USERS B, users C WHERE B.USER_ID = A.ID_PEGAWAI_YGMENGARAH AND C.USER_ID = A.ID_PEGAWAI_YGMENERIMA AND A.ID_MINITARAHANSEKLAIN = '"+idMinitArahanSeksyenLain+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idMinitArahanSeksyenLain", rs.getString("ID_MINITARAHANSEKLAIN")==null?"":rs.getString("ID_MINITARAHANSEKLAIN"));
		    	  h.put("idDokumen", rs.getString("ID_DOKUMEN")==null?"":rs.getString("ID_DOKUMEN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("tarikh_Arahan", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
		    	 // h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN_DOKUMEN")==null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
		    	//  h.put("tajuk_Dokumen", rs.getString("ID_PEGAWAI_YGMENERIMA")==null?"":rs.getString("ID_PEGAWAI_YGMENERIMA"));
		    	  h.put("pengarah", rs.getString("PEGAWAI_MENGARAH")==null?"":rs.getString("PEGAWAI_MENGARAH"));
		    	  h.put("penerima", rs.getString("PEGAWAI_MENERIMA")==null?"":rs.getString("PEGAWAI_MENERIMA"));
		    	  h.put("idSeksyen", rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
		    	  h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
		    	  paparMinitArahanSeksyenLain.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparMinitArahanSeksyenLain.addElement(h);
		    	  
		      }
		      
		      return paparMinitArahanSeksyenLain;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getListLampiranDokumenMasuk(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_dokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiran where id_dokumen = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_dokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranDokumen.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranDokumen;
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListLampiranDokumenKeluar(String idDokumen2) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_dokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiran where id_dokumen = '"+idDokumen2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_dokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranDokumen.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranDokumen;
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getListPegawai(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_NAMAPENERIMA AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getListPA(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_SETIAUSAHA AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}


	public static void setListDokumenFailSubjaket(String id) throws Exception {
		  Db db = null;
		    String sql = "";
		    String sql1 = "";
		    
		    try {
		    	senaraiDokumen = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();

		      sql = "SELECT A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.FLAG_DOKUMEN, B.USER_NAME AS NAMA_PENGIRIM1, C.USER_NAME AS NAMA_PENERIMA1, A.NAMA_PENGIRIM, A.NAMA_PENERIMA, D.ID_LAMPIRAN, A.ID_FAILSUBJAKET " +
	      		"FROM TBLPFDDOKUMEN A, USERS B, USERS C, TBLPFDRUJLAMPIRAN D, USERS E " +	
	      		//"FROM TBLPFDDOKUMEN A, TBLPFDRUJLAMPIRAN D " +	
	      		"WHERE A.ID_NAMAPENGIRIM = B.USER_ID(+) " +
	      		"AND A.ID_NAMAPENERIMA = C.USER_ID(+) " +
	      		"AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) " +
	      		"AND A.ID_FAIL = '"+id+"'";
	      		
		      sql = sql + " ORDER BY ID_DOKUMEN DESC";
				
//				 sql = "SELECT A.ID_DOKUMEN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.FLAG_DOKUMEN, B.NAMA_PEGAWAI AS NAMA_PENGIRIM1, C.NAMA_PEGAWAI AS NAMA_PENERIMA1, A.NAMA_PENGIRIM, A.NAMA_PENERIMA, A " +
//		      		"FROM TBLPFDDOKUMEN A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +	
//		      		//"FROM TBLPFDDOKUMEN A, TBLPFDRUJLAMPIRAN D " +	
//		      		"WHERE A.ID_NAMAPENGIRIM = B.ID_PEGAWAI(+) " +
//		      		"AND A.ID_NAMAPENERIMA = C.ID_PEGAWAI(+) " +
//		      		//"AND A.ID_DOKUMEN = D.ID_DOKUMEN(+) " +
//		      		"AND A.ID_FAIL = '"+id+"'";
//		      		
//			      sql = sql + " ORDER BY A.ID_DOKUMEN DESC";
		      
		      System.out.println("sql view fail subjaket--"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_Dokumen",rs.getString("id_Dokumen"));
		    	  h.put("no_Rujukan_Dokumen", rs.getString("no_Rujukan_Dokumen")== null?"":rs.getString("no_Rujukan_Dokumen"));
		    	 // h.put("no_Rujukan_Lain", rs.getString("no_Rujukan_Lain")== null?"":rs.getString("no_Rujukan_Lain"));

		    	  if (rs.getString("id_Jenisdokumen").equals("1")){
			    	  h.put("jenis_Dokumen", "SURAT");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("2")){
			    	  h.put("jenis_Dokumen", "MEMO");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("3")){
			    	  h.put("jenis_Dokumen", "LAPORAN");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("4")){
			    	  h.put("jenis_Dokumen", "MINIT MESYUARAT");
		    	  }
		    	  else if (rs.getString("id_Jenisdokumen").equals("5")){
			    	  h.put("jenis_Dokumen", "FAIL SUBJAKET");
		    	  }
		    	  
		    	  h.put("tajuk_Dokumen",rs.getString("tajuk_Dokumen")== null?"":rs.getString("tajuk_Dokumen"));
		    	  
		    	  if (rs.getString("nama_pengirim") == null){
		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim1")== null?"":rs.getString("Nama_Pengirim1"));
		    	  }
		    	  else {
		    		  h.put("nama_Pengirim",rs.getString("Nama_Pengirim")== null?"":rs.getString("Nama_Pengirim"));
		    	  }
		    	  
		    	  if (rs.getString("nama_penerima") == null){
		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima1")== null?"":rs.getString("Nama_Penerima1"));
		    	  }
		    	  else{
		    		  h.put("nama_Penerima",rs.getString("Nama_Penerima")== null?"":rs.getString("Nama_Penerima"));
		    	  }
		    	  
		    	  h.put("idFailSubjaket", rs.getString("id_Failsubjaket")== null?"":rs.getString("id_Failsubjaket"));
		    	  h.put("id_Lampiran", rs.getString("id_Lampiran")== null?"":rs.getString("id_Lampiran"));			    	 
		    	  h.put("flag_Dokumen", rs.getString("flag_Dokumen")== null?"":rs.getString("flag_Dokumen"));

		    	  bil++;
		    	  count++;
		    	  	 
		    	  senaraiDokumen.addElement(h);
		      }
		      
		     
		      
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("id_Dokumen","");
		    	  h.put("no_Rujukan_Dokumen", "");
		    	  //h.put("no_Rujukan_Lain", "Tiada rekod.");
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
	
	public static Vector getListDokumenFailSubjaket() {
		// TODO Auto-generated method stub
		return senaraiDokumen;
	}
	
	public static void setDataCountLevelArahan(String id) throws Exception {
				Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLevelArahan = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT MAX(LEVEL_ARAHAN) as LEVEL_ARAHAN FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN= '"+id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("levelArahan", rs.getString("LEVEL_ARAHAN")==null?"0":rs.getString("LEVEL_ARAHAN"));
		    	  paparLevelArahan.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("levelArahan",1);
		    	  paparLevelArahan.addElement(h);
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  


	}
	public static Vector getDataCountLevelArahan(String id) {
		// TODO Auto-generated method stub
		return paparLevelArahan;
	}
	
	public static void setDataMinitArahanPengarah(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.ID_PEGAWAI_YGMENGARAH, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.LEVEL_ARAHAN = 1"+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("idPegawaiMengarah",rs.getString("ID_PEGAWAI_YGMENGARAH"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
//	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("0")){
//		    	  h.put("statusTindakan", "");
//	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));

	    	  


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPengarah.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idMinitArahan","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");
	    	  h.put("idPegawaiPengarah", "");


	    	  senaraiMinitPengarah.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getlistMinitArahanPengarah() {
		// TODO Auto-generated method stub
		return senaraiMinitPengarah;
	}
	
	public static void setDataMinitArahanPegawai1(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPegawai1 = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.LEVEL_ARAHAN = 2"+
	      		"AND ID_STATUSTINDAKAN <> 99"+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
		    	  h.put("statusTindakan", "SUDAH MAKLUM");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("99")){
		    	  h.put("statusTindakan", "TUGASAN SELESAI");
	    	  }
	    	  else {
		    	  h.put("statusTindakan", "");
	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPegawai1.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idMinitArahan","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");
	    	  h.put("statusTindakan","");
	    	  h.put("idPegawaiPengarah", "");

	    	  senaraiMinitPegawai1.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanPegawai1() {
		// TODO Auto-generated method stub
		return senaraiMinitPegawai1;
	}
	
	public static void setDataMinitArahanPegawai2(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPegawai2 = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.LEVEL_ARAHAN = 3"+
	      		"AND ID_STATUSTINDAKAN <> 99"+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
		    	  h.put("statusTindakan", "SUDAH MAKLUM");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("99")){
		    	  h.put("statusTindakan", "TUGASAN SELESAI");
	    	  }
	    	  else {
		    	  h.put("statusTindakan", "");
	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));

	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPegawai2.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idMinitArahan","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");
	    	  h.put("statusTindakan","");
	    	  h.put("idPegawaiPengarah", "");

	    	  senaraiMinitPegawai2.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanPegawai2() {
		// TODO Auto-generated method stub
		return senaraiMinitPegawai2;
	}
	public static void setDataMinitArahanPegawai3(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPegawai3 = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.LEVEL_ARAHAN = 4"+
	      		"AND ID_STATUSTINDAKAN <> 99"+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
		    	  h.put("statusTindakan", "SUDAH MAKLUM");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("99")){
		    	  h.put("statusTindakan", "TUGASAN SELESAI");
	    	  }
	    	  else {
		    	  h.put("statusTindakan", "");
	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPegawai3.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idMinitArahan","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");
	    	  h.put("statusTindakan","");
	    	  h.put("idPegawaiPengarah", "");

	    	  senaraiMinitPegawai3.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanPegawai3() {
		// TODO Auto-generated method stub
		return senaraiMinitPegawai3;
	}
	
	public static void setDataMinitArahanPegawai4(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPegawai4 = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.LEVEL_ARAHAN = 4"+
	      		"AND ID_STATUSTINDAKAN <> 99"+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
		    	  h.put("statusTindakan", "SUDAH MAKLUM");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("99")){
		    	  h.put("statusTindakan", "TUGASAN SELESAI");
	    	  }
	    	  else {
		    	  h.put("statusTindakan", "");
	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));
	    	  bil++;
	    	  count++;    	  	 
	    	  senaraiMinitPegawai4.addElement(h);
	    	  
	      }
// Komen oleh Mohamad Rosli pada 23/03/2011				      
//	      if (count == 0){
//	    	  h = new Hashtable();
//	    	  h.put("bil",bil );
//	    	  h.put("idMinitArahan","");
//	    	  h.put("pegawaiMengarah","");
//	    	  h.put("minitArahan","Tiada");
//	    	  h.put("pegawaiMengarah","");
//	    	  h.put("noRujukanDokumen","");
//	    	  h.put("pegawaiMenerima","");
//	    	  h.put("tarikh","");
//	    	  h.put("statusTindakan","");
//	    	  h.put("idPegawaiPengarah", "");
//
//	    	  senaraiMinitPegawai4.addElement(h);
//	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanPegawai4() {
		// TODO Auto-generated method stub
		return senaraiMinitPegawai4;
	}

	public static void setDataMinitArahanSelesai(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitSelesai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, A.ID_PEGAWAI_YGMENGARAH "+
	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C "+
	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID "+
	      		"AND A.ID_DOKUMEN= '"+id+"' "+
	      		"AND A.ID_STATUSTINDAKAN = '99' "+
	      		"ORDER BY A.ID_MINITARAHAN ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idMinitArahan",rs.getString("ID_MINITARAHAN"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN_DOKUMEN")== null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
		    	  h.put("statusTindakan", "SUDAH MAKLUM");
	    	  }
	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("99")){
		    	  h.put("statusTindakan", "TUGASAN SELESAI");
	    	  }
	    	  else {
		    	  h.put("statusTindakan", "");
	    	  }
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitSelesai.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("idMinitArahan","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("tarikh","");
	    	  h.put("statusTindakan","");
	    	  h.put("idPegawaiPengarah", "");


	    	  senaraiMinitSelesai.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanSelesai() {
		// TODO Auto-generated method stub
		return senaraiMinitSelesai;
	}
	
	public static void setDataMinitArahanSeksyenLain(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitSeksyenLain = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

//	      sql = "SELECT A.ID_MINITARAHAN, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_STATUSTINDAKAN, A.CATATAN, B.NO_RUJUKAN_DOKUMEN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA, A.ID_PEGAWAI_YGMENGARAH "+
//	    	  	"FROM TBLPFDMINITARAHAN A, TBLPFDDOKUMEN B, USERS C, USERS D "+
//	      		"WHERE A.ID_DOKUMEN = B.ID_DOKUMEN "+
//	      		"AND A.ID_PEGAWAI_YGMENGARAH = C.USER_ID(+) "+
//	      		"AND (A.ID_PEGAWAI_YGMENERIMA1 = D.USER_ID "+
//	      		"OR A.ID_PEGAWAI_YGMENERIMA2 = D.USER_ID "+
//	      		"OR A.ID_PEGAWAI_YGMENERIMA3 = D.USER_ID) "+
//	      		"AND A.ID_DOKUMEN= '"+id+"' "+
//	      		"AND A.LEVEL_ARAHAN = 00"+
//	      		"AND ID_STATUSTINDAKAN = 4"+
//	      		"ORDER BY A.ID_MINITARAHAN ASC";;
			
			sql = "Select a.id_minitarahanseklain, d.USER_NAME AS PEGAWAI_MENGARAH, a.catatan, b.USER_NAME AS PEGAWAI_MENERIMA,  C.NAMA_SEKSYEN, a.tarikh_minit_arahan, a.id_pegawai_ygmengarah " +
				  "from tblpfdminitarahanseklain a, users b, tblrujseksyen c, users d where ID_DOKUMEN= '"+id+"' " +
				  "and a.id_seksyen = c.id_seksyen and a.ID_PEGAWAI_YGMENERIMA = b.USER_ID and a.ID_PEGAWAI_YGMENGARAH = d.USER_ID";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_minitarahanseklain",rs.getString("id_minitarahanseklain"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("catatan", rs.getString("catatan")== null?"":rs.getString("catatan"));
	    	  h.put("namaSeksyen", rs.getString("NAMA_SEKSYEN")== null?"":rs.getString("NAMA_SEKSYEN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  h.put("idPegawaiPengarah", rs.getString("ID_PEGAWAI_YGMENGARAH")== null?"":rs.getString("ID_PEGAWAI_YGMENGARAH"));
//			  h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
//	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("1")){
//		    	  h.put("statusTindakan", "TUGASAN TELAH DIAGIHKAN");
//	    	  }
//	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("2")){
//		    	  h.put("statusTindakan", "TELAH DIAMBIL TINDAKAN");
//	    	  }
//	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("3")){
//		    	  h.put("statusTindakan", "SUDAH MAKLUM");
//	    	  }
//	    	  else if (rs.getString("ID_STATUSTINDAKAN").equals("4")){
//		    	  h.put("statusTindakan", "TUGASAN SELESAI");
//	    	  }
//	    	  else {
//		    	  h.put("statusTindakan", "");
//	    	  }


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitSeksyenLain.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_minitarahanseklain","");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("namaSeksyen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");
	    	  h.put("statusTindakan","");
	    	  h.put("idPegawaiPengarah", "");

	    	  senaraiMinitSeksyenLain.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListMinitArahanSeksyenLain() {
		// TODO Auto-generated method stub
		return senaraiMinitSeksyenLain;
	}
	
	public static Vector getListPegawaiKeluar(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_NAMAPENGIRIM AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getDataPegawaiTinggi(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_NAMAPENERIMA AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getListPegawaiTinggi() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}

	public static Vector getListPAKeluar(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_SETIAUSAHA AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getIdDokumen(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparIdDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT ID_DOKUMEN FROM TBLPFDDOKUMEN " +
		      		"WHERE ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("idDokumen",rs.getString("ID_DOKUMEN")==null?"":rs.getString("ID_DOKUMEN"));
				  paparIdDokumen.addElement(h); 
		      }
		      
		      return paparIdDokumen;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static void updateStatusTugasan(String user_id, String idDokumen, String idMinitArahanSebelum) throws Exception {
		 Db db = null;
		 String sql = "";
		 String returnValue = "";
		 Date now = new Date();
		 try {
			 
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "UPDATE TBLPFDMINITARAHANPEGAWAI SET STATUS_TUGASAN = '0' " +
		      		"WHERE id_minitarahan = '"+idMinitArahanSebelum+"'"+
		      	    "and id_PegawaiYgmenerima = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     log.info("updateStatusTugasan::"+sql);
		     
//		      Hashtable h = new Hashtable();
//		      if (rs.next()) {
//		    	  returnValue = rs.getString("STATUS_TUGASAN")==null?"":rs.getString("STATUS_TUGASAN");
//		      }
//		      
//		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static String checkPegawaiMenerima(String user_id, String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select b.status_tugasan from TBLPFDMINITARAHAN a, TBLPFDMINITARAHANPEGAWAI b where"+
				" b.id_minitarahan = a.id_minitarahan " +
		        " and b.id_PegawaiYgmenerima = "+ user_id +
				" and a.id_dokumen = "+ idDokumen +"";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("status_tugasan")==null?"":rs.getString("status_tugasan");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	
	public static String getEmailPenerima1(String idPenerima1) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima1+"'" ;
 
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("emel")==null?"":rs.getString("emel");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static String getEmailPenerima2(String idPenerima2) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima2+"'" ;
 
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("emel")==null?"":rs.getString("emel");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static String getEmailPenerima3(String idPenerima3) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima3+"'" ;
 
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("emel")==null?"":rs.getString("emel");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static String getUserId1(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as id_user from users a, tblpfdminitarahan b "+
		    	  	"where A.USER_ID = B.ID_PEGAWAI_YGMENERIMA1 "+
		    	  	"and b.id_minitarahan = '"+idMinitarahan+"'" ;

		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_user")==null?"":rs.getString("id_user");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static String getUserId2(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as id_user from users a, tblpfdminitarahan b "+
		    	  	"where A.USER_ID = B.ID_PEGAWAI_YGMENERIMA2 "+
		    	  	"and b.id_minitarahan = '"+idMinitarahan+"'" ;

		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_user")==null?"":rs.getString("id_user");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static String getUserId3(String idMinitarahan) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as id_user from users a, tblpfdminitarahan b "+
		    	  	"where A.USER_ID = B.ID_PEGAWAI_YGMENERIMA3 "+
		    	  	"and b.id_minitarahan = '"+idMinitarahan+"'" ;

		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_user")==null?"":rs.getString("id_user");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static String getIdPengarah(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as id_user from users a, tblpfddokumen b "+
		    	  	"where A.USER_ID = B.ID_NAMAPENERIMA "+
		    	  	"and b.id_dokumen = '"+idDokumen+"'" ;

		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_user")==null?"":rs.getString("id_user");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static String getIdPA(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as id_user from users a, tblpfddokumen b "+
		    	  	"where A.USER_ID = B.ID_SETIAUSAHA "+
		    	  	"and b.id_dokumen = '"+idDokumen+"'" ;

		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_user")==null?"":rs.getString("id_user");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static String getEmelPengirim(String user_id) throws Exception {
		// TODO Auto-generated method stub
			Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+user_id+"'" ;
		      	      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("emel")==null?"":rs.getString("emel");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static String getUserIdSeksyenLain(String idMinitArahanSeksyenLain) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Vector getDataPARKeluar(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PAR AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getDataPT(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 listPT = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PTFAIL AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  listPT.addElement(h); 
		      }
		      
		      return listPT;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void setListPTFail(String user_id) throws Exception {
		 Db db = null;	
		 listPTFail.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select user_id,user_name from users where user_id in " +
		      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
		      		"and id_negeri =(select id_negeri from users_internal where user_id="+user_id+")" +
		      		"and id_jawatan in (11,15,20,21,22,24,28)) order by user_name asc";
	          
		      
		     
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  listPTFail.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListPTFail() {
		
		return listPTFail;
	}
	public static void setListPTFail2(String user_id) throws Exception {
		 Db db = null;	
		 listPTFail2.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select user_id,user_name from users where user_id in " +
		      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
		      		"and id_negeri =(select id_negeri from users_internal where user_id="+user_id+")" +
		      		"and id_jawatan in (15,21,24)) order by user_name asc";
	          
		      
		     
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  listPTFail2.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListPTFail2() {
		
		return listPTFail2;
	}
	public static void setListPegawai(String user_id,String user_negeri) throws Exception {
	    Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	  /*    
	      sql = "select user_id,user_name from users where user_id in " +
	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
	      		"and id_jawatan in (0,1,2,3,4,5,6))";

	      sql = "select user_id,user_name from users where user_id in " +
    		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
    		"and id_jawatan in (0,1,2,3,4,5,6))";
    		
    		*/
	      
	      sql =  " SELECT U.user_id,U.user_name FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') ";	   
	      
	      
	      sql += "ORDER BY U.USER_NAME ASC ";

	      
	      System.out.println("SQL USER PENGARAH ::"+sql.toUpperCase());
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

			  senaraiPegawai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListPegawai() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	public static Vector getDataPTKeluar(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PAR AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("error test :"+sql.toUpperCase());
		      
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getDataPegawaiKeluar(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.id_NamaPengirim AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getListPegawaiMinitArahan1(String idMinitarahan) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA1 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListPegawaiMinitArahan2(String idMinitarahan) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA2 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static Vector getListPegawaiMinitArahan3(String idMinitarahan) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHAN A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_YGMENERIMA3 AND A.ID_MINITARAHAN = '"+idMinitarahan+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getUser(String idDokumen, String idUser) throws Exception {
		Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  

 	      sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"'";
 	   //  sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable s;
 			while (rs.next()) {
 				s = new Hashtable();
 				s.put("ID_PEGAWAI_YGMENGARAH", rs.getString("ID_PEGAWAI_YGMENGARAH") == null ? ""
						: rs.getString("ID_PEGAWAI_YGMENGARAH"));
 				
 				v.addElement(s);
 			}
 		
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
	}
	
	public static Vector getUserSekLain(String idDokumen, String idUser) throws Exception {
		Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  

 	      sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHANSEKLAIN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"'";
 	   //  sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable s;
 			while (rs.next()) {
 				s = new Hashtable();
 				s.put("ID_PEGAWAI_YGMENGARAH", rs.getString("ID_PEGAWAI_YGMENGARAH") == null ? ""
						: rs.getString("ID_PEGAWAI_YGMENGARAH"));
 				
 				v.addElement(s);
 			}
 		
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
	}
	
	public static Vector getUserSelesai(String idMinitArahan, String idDokumen, String idUser) throws Exception {
		Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      SQLRenderer r = new SQLRenderer();
 	  
 	     // sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"'";
 	     sql = "SELECT ID_PEGAWAI_YGMENGARAH FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
	      
 			ResultSet rs = stmt.executeQuery(sql);			

 			Hashtable s;
 			while (rs.next()) {
 				s = new Hashtable();
 				s.put("ID_PEGAWAI_YGMENGARAH", rs.getString("ID_PEGAWAI_YGMENGARAH") == null ? ""
						: rs.getString("ID_PEGAWAI_YGMENGARAH"));
 				
 				v.addElement(s);
 			}
 		
 			return v;
 		} finally {
 			if (db != null)
 				db.close();
 		}
	}
	
	public static String getMinitArahan(String idMinitArahan, String idDokumen, String idUser) throws Exception {
		//Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	    String returnValue = "";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	  
// 	     sql = "SELECT id_StatusTindakan FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
 	     sql = "SELECT STATUS_TUGASAN FROM TBLPFDMINITARAHANPEGAWAI WHERE ID_PEGAWAIYGMENERIMA= '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
 
 			ResultSet rs = stmt.executeQuery(sql);			
		      
 			Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("STATUS_TUGASAN")==null?"":rs.getString("STATUS_TUGASAN");
		      }
 			
 		} finally {
 			if (db != null)
 				db.close();
 		}
 		return returnValue;
	}

	public static String getMinitArahanSekLain(String idDokumen, String idUser) throws Exception {
		//Vector v = new Vector();
 	    Db db = null;
 	    String sql = " ";
 	    String returnValue = "";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	  
// 	     sql = "SELECT id_StatusTindakan FROM TBLPFDMINITARAHAN WHERE ID_DOKUMEN = '"+idDokumen+"' AND ID_PEGAWAI_YGMENGARAH = '"+idUser+"' AND ID_MINITARAHAN = '"+idMinitArahan+"'";
 	     sql = "SELECT ID_STATUSDOKUMEN FROM TBLPFDMINITARAHANSEKLAIN WHERE ID_PEGAWAI_YGMENGARAH= '"+idUser+"' AND ID_DOKUMEN = '"+idDokumen+"'";
 
 			ResultSet rs = stmt.executeQuery(sql);			
		      
 			Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("ID_STATUSDOKUMEN")==null?"":rs.getString("ID_STATUSDOKUMEN");
		      }
 			
 		} finally {
 			if (db != null)
 				db.close();
 		}
 		return returnValue;
	}

	
	private static void deleteIdMinitArahan(String idMinitarahan) throws Exception {

 	    Db db = null;
 	    String sql = " ";
 	   
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();

 	      sql = "DELETE FROM TBLPFDMINITARAHANPEGAWAI WHERE ID_MINITARAHAN = '"+idMinitarahan+"'";
 	      
 		  ResultSet rs = stmt.executeQuery(sql);			

 		} finally {
 			if (db != null)
 				db.close();
 		}
	}
	
	
	public static Vector getIdSeksyen(String user_id) throws Exception {
		 
		 getIDseksyen = new Vector();
		
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 
			 
		      
			  db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT DISTINCT ID_SEKSYEN FROM USERS_INTERNAL WHERE USER_ID = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				  getIDseksyen.addElement(h); 
		      }
		      
		      return getIDseksyen;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
}

	



