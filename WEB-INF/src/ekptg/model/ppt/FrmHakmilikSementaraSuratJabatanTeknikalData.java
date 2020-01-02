package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmHakmilikSementaraSuratJabatanTeknikalData {
	
	Vector maklumatSuratJabatan = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	 public static void edit_status(Hashtable data) throws Exception {
			Db db = null;
			String sql3 = "";
			
				try
				{						
					String id_permohonan = (String)data.get("id_permohonan");
					String id_status = "22" ;			        				
					  
					//**UNTUK UPDATE ID_STATUS = 22 [Maklumat Jabatan Teknikal]
					  db = new Db();					  
					  Statement stmt3 = db.getStatement();
					  SQLRenderer r3 = new SQLRenderer();
					
					  r3.update("id_permohonan", id_permohonan);
					  r3.add("id_status", id_status);
					  
					  sql3 = r3.getSQLUpdate("tblpptpermohonan");
					  stmt3.executeUpdate(sql3);
									
				}finally {
					if(db != null) db.close();
				}			
		}
		public static void addSuratJabatan(Hashtable data) throws Exception{
			
			 Db db = null;
			 String sql = "";
			 String bilSuratJT = "";
		     String noRujSuratJT = "";
		     String txdTkhSurat = "";
		     String tkhSuratJT = "";
		     String tarikhTerimaJT = "";
		     String tkhTerimaJT = "";
		     String keputusanJT = "";
		     String ulasanJT = "";
			 

			    try
			    {
			    	long id_ulasanteknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
			    	String id_permohonan = (String)data.get("id_permohonan");
			    	String jabatan = (String)data.get("jabatan");
			    	String bilSurat = (String)data.get("bilSurat");
			    	String noRujSurat = (String)data.get("noRujSurat");
			    	String tarikhSurat = (String)data.get("tarikhSurat");
			    	String tkhSurat = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";
			    	String tarikhHantar= (String)data.get("tarikhHantar");
			    	String tkhHantar = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";
			    	String tempoh = (String)data.get("tempoh");
			    	String perihal = (String)data.get("perihal");
			    	String flagTerima = (String)data.get("flagTerima");
			    	
			    	
			    	if (flagTerima.equals("1")){
			    		 bilSuratJT = (String)data.get("bilSuratJT");
				    	 noRujSuratJT = (String)data.get("noRujSuratJT");
				    	 txdTkhSurat = (String)data.get("txdTkhSurat");
				    	 tkhSuratJT = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')"; tarikhTerimaJT = (String)data.get("tarikhTerimaJT");
				    	 tarikhTerimaJT = (String)data.get("tarikhTerimaJT");
				    	 tkhTerimaJT = "to_date('" + tarikhTerimaJT + "','dd/MM/yyyy')";
				    	 keputusanJT = (String)data.get("keputusanJT");
				    	 ulasanJT = (String)data.get("ulasanJT");
				    	 
				    

			    	}
			    	
			    	String id_Masuk = (String)data.get("id_Masuk");
			    	
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    
				    r.add("id_ulasanteknikal",id_ulasanteknikal);
				    r.add("id_permohonan",id_permohonan);
				    r.add("id_jabatanteknikal",jabatan);
				    r.add("bil_surat",bilSurat);
				    r.add("no_rujukansurat",noRujSurat);
				    r.add("tarikh_surat",r.unquote(tkhSurat));
				    r.add("tarikh_hantar",r.unquote(tkhHantar));
				    r.add("tempoh",tempoh);
				    r.add("perihal",perihal);
				    r.add("flag_terima",flagTerima);
				    if (flagTerima.equals("1")){
				    	 r.add("bil_surat_jt",bilSuratJT);
						 r.add("no_rujukansuratjt",noRujSuratJT);
						 r.add("tarikh_suratjt",r.unquote(tkhSuratJT));
						 r.add("tarikh_terimajt",r.unquote(tkhTerimaJT));
						 r.add("keputusanjt",keputusanJT);
						 r.add("ulasanjt",ulasanJT);
				    }
				   
				    r.add("id_masuk",id_Masuk);
				    r.add("tarikh_masuk",r.unquote("sysdate"));
				    
				    sql = r.getSQLInsert("tblpptulasanteknikal");
				    System.out.println("sql surat"+sql);
				    stmt.executeUpdate(sql);

			    }
			    finally {
				      if (db != null) db.close();
				    }
			
			
			
		}
		public static void updateSuratJabatan(Hashtable data) throws Exception{
			
			 Db db = null;
			 String sql = "";
			 String bilSuratJT = "";
		     String noRujSuratJT = "";
		     String txdTkhSurat = "";
		     String tkhSuratJT = "";
		     String tarikhTerimaJT = "";
		     String tkhTerimaJT = "";
		     String keputusanJT = "";
		     String ulasanJT = "";
			 

			    try
			    {
			    	String id_ulasanteknikal = (String)data.get("id_ulasanteknikal");
			    	String id_permohonan = (String)data.get("id_permohonan");
			    	String jabatan = (String)data.get("jabatan");
			    	String bilSurat = (String)data.get("bilSurat");
			    	String noRujSurat = (String)data.get("noRujSurat");
			    	String tarikhSurat = (String)data.get("tarikhSurat");
			    	String tkhSurat = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";
			    	String tarikhHantar= (String)data.get("tarikhHantar");
			    	String tkhHantar = "to_date('" + tarikhHantar + "','dd/MM/yyyy')";
			    	String tempoh = (String)data.get("tempoh");
			    	String perihal = (String)data.get("perihal");
			    	String flagTerima = (String)data.get("flagTerima");
			    	
			    	
			    	
			    	if (flagTerima.equals("1")){
			    		 bilSuratJT = (String)data.get("bilSuratJT");
				    	 noRujSuratJT = (String)data.get("noRujSuratJT");
				    	 txdTkhSurat = (String)data.get("txdTkhSurat");
				    	 tkhSuratJT = "to_date('" + txdTkhSurat + "','dd/MM/yyyy')"; tarikhTerimaJT = (String)data.get("tarikhTerimaJT");
				    	 tkhTerimaJT = "to_date('" + tarikhTerimaJT + "','dd/MM/yyyy')";
				    	 keputusanJT = (String)data.get("keputusanJT");
				    	 ulasanJT = (String)data.get("ulasanJT");
			    	}
			    	

			    	String id_Kemaskini = (String)data.get("id_Kemaskini");
			    	
			    	db = new Db();
				    Statement stmt = db.getStatement();
				    SQLRenderer r = new SQLRenderer();
				    
				    r.update("id_ulasanteknikal",id_ulasanteknikal);
				    r.add("id_jabatanteknikal",jabatan);
				    r.add("bil_surat",bilSurat);
				    r.add("no_rujukansurat",noRujSurat);
				    r.add("tarikh_surat",r.unquote(tkhSurat));
				    r.add("tarikh_hantar",r.unquote(tkhHantar));
				    r.add("tempoh",tempoh);
				    r.add("perihal",perihal);
				    r.add("flag_terima",flagTerima);
				    if (flagTerima.equals("1")){
				    	 r.add("bil_surat_jt",bilSuratJT);
						 r.add("no_rujukansuratjt",noRujSuratJT);
						 r.add("tarikh_suratjt",r.unquote(tkhSuratJT));
						 r.add("tarikh_terimajt",r.unquote(tkhTerimaJT));
						 r.add("keputusanjt",keputusanJT);
						 r.add("ulasanjt",ulasanJT);
				    }
				   
				    r.add("id_kemaskini",id_Kemaskini);
				    r.add("tarikh_kemaskini",r.unquote("sysdate"));
				    
				    sql = r.getSQLUpdate("tblpptulasanteknikal");
				    System.out.println("sql update--"+sql);
				    stmt.executeUpdate(sql);

			    }
			    finally {
				      if (db != null) db.close();
				    }
			
			
			
		}
		public void setMaklumatSuratJabatan(String idUlasanTeknikal)throws Exception {
		    Db db = null;
		    String sql = "";
		    
		    try {
		      maklumatSuratJabatan = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("a.id_ulasanteknikal");
		      r.add("a.id_jabatanteknikal");
		      r.add("a.bil_surat");
		      r.add("a.no_rujukansurat");
		      r.add("a.tarikh_surat");
		      r.add("a.tarikh_hantar");
		      r.add("a.tempoh");
		      r.add("a.perihal");
		      r.add("a.flag_terima");
		      r.add("a.bil_surat_jt");
		      r.add("a.no_rujukansuratjt");
		      r.add("a.tarikh_suratjt");
		      r.add("a.tarikh_terimajt");
		      r.add("a.keputusanjt");
		      r.add("a.ulasanjt");
		      
		      r.add("a.id_ulasanteknikal",r.unquote(idUlasanTeknikal));
		     
		      
		      sql = r.getSQLSelect("tblpptulasanteknikal a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_ULASANTEKNIKAL", rs.getString("id_ulasanteknikal")== null?"":rs.getString("id_ulasanteknikal"));
		    	  h.put("ID_JABATANTEKNIKAL",rs.getString("id_jabatanteknikal") == null?"":rs.getString("id_jabatanteknikal"));
		    	  h.put("BIL_SURAT",rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));	 
		    	  h.put("NO_RUJ_SURAT",rs.getString("no_rujukansurat")== null?"":rs.getString("no_rujukansurat"));	    	 
		    	  h.put("TARIKH_SURAT",rs.getString("tarikh_surat") == null?"":sdf.format(rs.getDate("tarikh_surat")));	    	 
		    	  h.put("TARIKH_HANTAR",rs.getString("tarikh_hantar") == null?"":sdf.format(rs.getDate("tarikh_hantar")));	    	 
		    	  h.put("TEMPOH",rs.getString("tempoh") == null?"":rs.getString("tempoh"));	    	 
		    	  h.put("PERIHAL",rs.getString("perihal") == null?"":rs.getString("perihal"));	
		    	  h.put("FLAG_TERIMA", rs.getString("flag_terima")== null?"":rs.getString("flag_terima"));
		    	  h.put("BIL_SURATJT",rs.getString("bil_surat_jt") == null?"":rs.getString("bil_surat_jt"));	    	 
		    	  h.put("NO_RUJ_SURATJT",rs.getString("no_rujukansuratjt") == null?"":rs.getString("no_rujukansuratjt"));	    	 
		    	  h.put("TARIKH_SURATJT",rs.getString("tarikh_suratjt")== null?"":sdf.format(rs.getDate("tarikh_suratjt")));	    	 
		    	  h.put("TARIKH_TERIMAJT",rs.getString("tarikh_terimajt")== null?"":sdf.format(rs.getDate("tarikh_terimajt")));	    	 
		    	  h.put("KEPUTUSANJT",rs.getString("keputusanjt")==null?"":rs.getString("keputusanjt"));	    	 
		    	  h.put("ULASANJT",rs.getString("ulasanjt")==null?"":rs.getString("ulasanjt"));	    	   	 

		    	  maklumatSuratJabatan.addElement(h);
		      }
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		
		public Vector getMaklumatSuratJabatan(){
			return maklumatSuratJabatan;
		}
		
		public static void deleteJabatanTeknikal(String id) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmtQ = db.getStatement();
		      SQLRenderer rQ = new SQLRenderer();
		      rQ.add("id_Ulasanteknikal", id);
		      sql = rQ.getSQLDelete("tblpptulasanteknikal");	      
		      stmtQ.executeUpdate(sql);
		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }//close delete


}
