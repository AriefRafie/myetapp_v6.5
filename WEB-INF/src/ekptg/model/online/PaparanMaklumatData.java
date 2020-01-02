package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class PaparanMaklumatData {
	private static Vector listPHP = new Vector();
	private static Vector listHTP = new Vector();
	private static Vector paparHTPHakmilik = new Vector();
	private static Vector paparHTPWarta = new Vector();
	private static Vector paparPHPPenyewaan = new Vector();
	private static Vector paparPHPLesen = new Vector();
	
	public static void  setCarianPaparanMaklumatPHP(String noSiriPerjanjian, String noSiriLesen)throws Exception{
		
		Db db = null;
		listPHP.clear();
	    String sql = "";
		
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();

					sql = "SELECT F.ID_PERMOHONAN,B.NO_SIRI_PERJANJIAN,A.NO_SIRI_LESEN, C.NAMA_NEGERI,E.NAMA_DAERAH, D.NAMA_MUKIM" +
						  " FROM  TBLPHPJADUALKEDUALESENAPB A,TBLPHPPERJANJIANPENYEWAAN B,TBLRUJNEGERI C,TBLRUJMUKIM D,TBLRUJDAERAH E,TBLPERMOHONAN F" +
						  " WHERE B.ID_NEGERI = C.ID_NEGERI" +
						  " AND B.ID_MUKIM = D.ID_MUKIM" +
						  " AND C.ID_NEGERI = E.ID_NEGERI" +
						  " AND B.ID_PERMOHONAN =F.ID_PERMOHONAN" +
						  " AND A.ID_PERMOHONAN =   F.ID_PERMOHONAN";

					
					
					if (noSiriPerjanjian != null) {
						if (!noSiriPerjanjian.trim().equals("")) {
							sql = sql + " AND UPPER(B.NO_SIRI_PERJANJIAN) LIKE '%' ||'" + noSiriPerjanjian.toUpperCase() + "'|| '%'";
						}
					}
			      
					if (noSiriLesen != null) {
						if (!noSiriLesen.trim().equals("")) {
							sql = sql + " AND UPPER(A.NO_SIRI_LESEN) LIKE '%' ||'" + noSiriLesen.toUpperCase() + "'|| '%'";
						}
					}
			      	
					 sql = sql + " ORDER BY F.ID_PERMOHONAN DESC";
					 
					 ResultSet rs = stmt.executeQuery(sql);
				      Hashtable h;
				      int bil = 1;
				      int count = 0;
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("bil",bil);
				    	  h.put("idPermohonan",rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
				    	  h.put("namaNegeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
				    	  h.put("namaDaerah",rs.getString("NAMA_DAERAH")== null?"":rs.getString("NAMA_DAERAH"));
				    	  h.put("namaMukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
				    	  h.put("noSiriPerjanjian", rs.getString("NO_SIRI_PERJANJIAN")== null?"":rs.getString("NO_SIRI_PERJANJIAN"));
				    	  h.put("noSiriLesen", rs.getString("NO_SIRI_LESEN")== null?"":rs.getString("NO_SIRI_LESEN"));
				    	  listPHP.addElement(h);
				    	  bil++;
				    	  count++;
				    	  
				      }
				      if (count == 0){
				    	  h = new Hashtable();
				    	  h.put("bil","");
				    	  h.put("idPermohonan","");
				    	  h.put("namaNegeri","Tiada rekod.");
				    	  h.put("namaDaerah","");
				    	  h.put("namaMukim", "");
				    	  h.put("noSiriPerjanjian", "");
				    	  h.put("noSiriLesen", "");
				    	  listPHP.addElement(h);
				      }
		    
		      
		 }finally {
		      if (db != null) db.close();
		    }
	}
	 public static Vector getListPHP(){
		 
		  return listPHP;
	  }
	 
	 public static void  setCarianPaparanMaklumatHTP(String noHakmilik, String noWarta)throws Exception{
			
			Db db = null;
			listHTP.clear();
		    String sql = "";
			
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();

			      sql = "SELECT A.ID_HAKMILIK,A.NO_HAKMILIK, A.NO_WARTA,B.NAMA_NEGERI, C.NAMA_MUKIM,D.NAMA_DAERAH"+		
				  " FROM TBLHTPHAKMILIK A, TBLRUJNEGERI B, TBLRUJMUKIM C, TBLRUJDAERAH D" +
				  " WHERE B.ID_NEGERI = A.ID_NEGERI" +
				  " AND B.ID_NEGERI = D.ID_NEGERI" +
				  " AND D.ID_DAERAH = A.ID_DAERAH" +
				  " AND C.ID_MUKIM = A.ID_MUKIM";
			
						
						if (noHakmilik != null) {
							if (!noHakmilik.trim().equals("")) {
								sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
							}
						}
				      
						if (noWarta != null) {
							if (!noWarta.trim().equals("")) {
								sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'" + noWarta.toUpperCase() + "'|| '%'";
							}
						}
				      	
						 sql = sql + " ORDER BY A.ID_HAKMILIK";
						 
						 ResultSet rs = stmt.executeQuery(sql);
					      Hashtable h;
					      int bil = 1;
					      int count = 0;
					      while (rs.next()) {
					    	  h = new Hashtable();
					    	  h.put("bil",bil);
					    	  h.put("idHakmilik",rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
					    	  h.put("namaNegeri",rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
					    	  h.put("namaDaerah",rs.getString("NAMA_DAERAH")== null?"":rs.getString("NAMA_DAERAH"));
					    	  h.put("namaMukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
					    	  h.put("noHakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
					    	  h.put("noWarta", rs.getString("NO_WARTA")== null?"":rs.getString("NO_WARTA"));
					    	  listHTP.addElement(h);
					    	  bil++;
					    	  count++;
					    	  
					      }
					      if (count == 0){
					    	  h = new Hashtable();
					    	  h.put("bil","");
					    	  h.put("idHakmilik","");
					    	  h.put("namaNegeri","Tiada rekod.");
					    	  h.put("namaDaerah","");
					    	  h.put("namaMukim", "");
					    	  h.put("noHakmilik", "");
					    	  h.put("noWarta", "");
					    	  listHTP.addElement(h);
					      }
			    
			      
			 }finally {
			      if (db != null) db.close();
			    }
		}
		 public static Vector getListHTP(){
			 
			  return listHTP;
		  }
		
		public static void paparDataHakmilik(int id)throws Exception {
				
				 Db db = null;
				 paparHTPHakmilik.clear();
				 String sql = "";
				 
				 try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      
				      r.add("a.id_Hakmilik");
				      r.add("a.no_Hakmilik");
				      r.add("b.keterangan");
				      r.add("a.syarat");
				      r.add("a.hakmilik_Asal");
				    
				      
				      r.add("a.id_Jenishakmilik",r.unquote("b.id_Jenishakmilik"));
				     
				      r.add("a.id_Hakmilik",id);
				     
				    
				      sql = r.getSQLSelect("Tblhtphakmilik a, Tblrujjenishakmilik b");
				      ResultSet rs = stmt.executeQuery(sql);
				     
				      Hashtable h;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  
				    	  h.put("id_Hakmilik", rs.getString("id_Hakmilik"));
				    	  h.put("no_Hakmilik", rs.getString("no_Hakmilik"));
				    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
				    	  h.put("syarat",rs.getString("syarat")== null?"":rs.getString("syarat"));
				    	  h.put("hakmilik_Asal",rs.getString("hakmilik_Asal")== null?"":rs.getString("hakmilik_Asal"));
				    	  paparHTPHakmilik.addElement(h);
		 
				      }
				 }
				 finally {
				      if (db != null) db.close();
				    }  

			}
			 public static Vector getDataHakmilik(){
				 
				  return paparHTPHakmilik;
			  }
			 public static void paparDataWarta(int id)throws Exception {
					
				 Db db = null;
				 paparHTPWarta.clear();
				 String sql = "";
				 
				 try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      
				      r.add("a.id_Hakmilik");
				      r.add("a.no_Warta");
				      r.add("a.lokasi");
				      r.add("a.syarat");
				      r.add("a.status_Sah");
	
				      r.add("a.id_Hakmilik",id);
				     
				    
				      sql = r.getSQLSelect("Tblhtphakmilik a");
				      ResultSet rs = stmt.executeQuery(sql);
				     
				      Hashtable h;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  
				    	  h.put("id_Hakmilik", rs.getString("id_Hakmilik"));
				    	  h.put("no_Warta", rs.getString("no_Warta"));
				    	  h.put("lokasi",rs.getString("lokasi")== null?"":rs.getString("lokasi"));
				    	  h.put("syarat",rs.getString("syarat")== null?"":rs.getString("syarat"));
				    	  h.put("status_Sah",rs.getString("status_Sah")== null?"":rs.getString("status_Sah"));
				    	  paparHTPWarta.addElement(h);
		 
				      }
				 }
				 finally {
				      if (db != null) db.close();
				    }  

			}
			 public static Vector getDataWarta(){
				 
				  return paparHTPWarta;
			  }
			 public static void paparDataPenyewaan(int id)throws Exception {
					
				 Db db = null;
				 paparPHPPenyewaan.clear();
				 String sql = "";
				 
				 try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      
				      r.add("a.idPermohonan");
				      r.add("a.no_Siri_Perjanjian");
				      r.add("a.no_Lot");
				      r.add("a.kadar_Sewa");
				      r.add("a.status_Perjanjian");
				    				     
				      r.add("a.idPermohonan",id);
				     
				    
				      sql = r.getSQLSelect("Tblphpperjanjianpenyewaan a");
				      ResultSet rs = stmt.executeQuery(sql);
				     
				      Hashtable h;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("idPermohonan", rs.getString("idPermohonan"));
				    	  h.put("no_Siri_Perjanjian", rs.getString("no_Siri_Perjanjian"));
				    	  h.put("no_Lot",rs.getString("no_Lot")== null?"":rs.getString("no_Lot"));
				    	  h.put("kadar_Sewa",rs.getString("kadar_Sewa")== null?"":rs.getString("kadar_Sewa"));
				    	  h.put("status_Perjanjian",rs.getString("status_Perjanjian")== null?"":rs.getString("status_Perjanjian"));
				    	  paparPHPPenyewaan.addElement(h);
		 
				      }
				 }
				 finally {
				      if (db != null) db.close();
				    }  

			}
			 public static Vector getDataPenyewaan(){
				 
				  return paparPHPPenyewaan;
			  }
			 public static void paparDataLesen(int id)throws Exception {
					
				 Db db = null;
				 paparPHPLesen.clear();
				 String sql = "";
				 
				 try {
				      db = new Db();
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				      
				      r.add("a.id_Permohonan");
				      r.add("a.no_Siri_Lesen");
				      r.add("a.lokasi");
				      r.add("a.tempoh");
				      r.add("a.status_Lesen");
	
				      r.add("a.id_Permohonan",id);
				     
				    
				      sql = r.getSQLSelect("Tblphpjadualkedualesenapb a");
				      ResultSet rs = stmt.executeQuery(sql);
				     
				      Hashtable h;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  
				    	  h.put("id_Permohonan", rs.getString("id_Permohonan"));
				    	  h.put("no_Siri_Lesen", rs.getString("no_Siri_Lesen"));
				    	  h.put("lokasi",rs.getString("lokasi")== null?"":rs.getString("lokasi"));
				    	  h.put("tempoh",rs.getString("tempoh")== null?"":rs.getString("tempoh"));
				    	  h.put("status_Lesen",rs.getString("status_Lesen")== null?"":rs.getString("status_Lesen"));
				    	  paparPHPLesen.addElement(h);
		 
				      }
				 }
				 finally {
				      if (db != null) db.close();
				    }  

			}
			 public static Vector getDataLesen(){
				 
				  return paparPHPLesen;
			  }
}
