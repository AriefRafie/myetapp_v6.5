package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmKemaskiniTukarTarafKeselamatanData {
	
	private static Vector list = new Vector();
	private static Vector paparFail = new Vector();
	private static Vector paparDetailFailAsal = new Vector();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
		      r.add("a.tajuk_fail");		      
		      r.add("c.id_Seksyen");
		      r.add("d.id_Urusan");
		      r.add("e.id_Suburusan");
		      r.add("f.id_Tarafkeselamatan");
		      r.add("a.tajuk_Fail");
		      r.add("a.id_Status");
		      r.add("h.id_Lokasifail");
		      r.add("a.faharasat");
		      r.add("a.tarikh_Daftar_Fail");
		      r.add("a.catatan");
		      r.add("a.flag_Fail");
		      
		      r.add("a.id_Negeri",r.unquote("b.id_Negeri(+)"));
		      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen(+)"));
		      r.add("a.id_Urusan",r.unquote("d.id_Urusan(+)"));
		      r.add("a.id_Suburusan",r.unquote("e.id_Suburusan(+)"));
		      r.add("a.id_Tarafkeselamatan",r.unquote("f.id_Tarafkeselamatan(+)"));
		      r.add("a.id_Status",r.unquote("g.id_Status(+)"));
		      r.add("a.id_Lokasifail",r.unquote("h.id_Lokasifail(+)"));
		      r.add("a.id_Fail",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujnegeri b, Tblrujseksyen c, Tblrujurusan d, Tblrujsuburusan e, Tblpfdrujtarafkeselamatan f, Tblrujstatus g, Tblpfdrujlokasifail h");
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("tajukfail", rs.getString("tajuk_fail"));
		    	  h.put("idFail", rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  h.put("idNegeri",rs.getString("id_Negeri")== null?"":rs.getString("id_Negeri"));
		    	  h.put("idSeksyen",rs.getString("id_Seksyen")== null?"":rs.getString("id_Seksyen"));
		    	  h.put("idUrusan",rs.getString("id_Urusan")== null?"":rs.getString("id_Urusan"));
		    	  h.put("idSuburusan",rs.getString("id_Suburusan")== null?"":rs.getString("id_Suburusan"));
		    	  h.put("idTarafkeselamatan",rs.getString("id_Tarafkeselamatan")== null?0:rs.getString("id_Tarafkeselamatan"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("idStatus",rs.getString("id_Status")== null?"":rs.getString("id_Status"));
		    	  h.put("idLokasifail", rs.getString("id_Lokasifail")== null?"":rs.getString("id_Lokasifail"));
		    	  h.put("faharasat", rs.getString("faharasat")== null?"":rs.getString("faharasat"));
		    	  h.put("tarikhDaftar",sdf.format(rs.getDate("tarikh_Daftar_Fail")));
		    	  h.put("catatan",rs.getString("catatan")== null?"":rs.getString("catatan"));
		    	  h.put("flagFail",rs.getString("flag_Fail")== null?"":rs.getString("flag_Fail"));
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
	 public static String add(Hashtable data) throws Exception{
		 
			Db db = new Db();
			String sql = "";
			String sql1 = "";
			try
				{
					
				 	long idFailBaru = DB.getNextID("TBLPFDFAIL_SEQ");
				 	String idFailAsal = (String)data.get("idFailAsal");
				    String idMasuk = (String)data.get("id_Masuk");
				  
				    db = new Db();
				    Statement stmt = db.getStatement();

				    String sqlInsert = "INSERT INTO TBLPFDFAIL (ID_FAIL,ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
					   				+"FLAG_FAIL, ID_STATUS, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
					   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN )";
				  
				    String sqlSelect = "SELECT '"+idFailBaru+"', ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
					   				+"FLAG_FAIL, ID_STATUS, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
					   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_SUBSUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN "
					   				+"FROM TBLPFDFAIL WHERE ID_FAIL = '"+idFailAsal+"'";
				  

			      sql = sqlInsert+" "+sqlSelect;
			      
			      
			      
			     
			      
			      stmt.executeUpdate(sql);   
			      
				  String sqlUpdate = "UPDATE TBLPFDFAIL SET ID_STATUS = '114' WHERE ID_FAIL = '"+idFailBaru+"'";
			   		
				  
				  sql1 = sqlUpdate;
				  stmt.executeUpdate(sql1);  
			      
			      return "" +idFailBaru;
			      
			} finally {
				if (db != null) db.close();
			}
			      
	 }
	 public static String generateNoFail(Integer negeri, Integer seksyen, Integer urusan, String suburusan, String taraf) throws Exception{
	 		String sql = "";
	 		
			String noFail = "";
			String noFailStandard = "JKPTG/101/";
			Db db = null;
			db = new Db();
		    Statement stmt = db.getStatement();
		    String abbrev = "";
		    String sksyn = "";
		    String ursn = "";
		    String subUrsn = "";
		    String trf = "";
		    if (suburusan != "0"){
		    	
		    	  Statement stmtnegeri = db.getStatement();
			      SQLRenderer rnegeri = new SQLRenderer();				 
			      rnegeri.add("id_negeri");
			      rnegeri.add("abbrev");				      
			      rnegeri.add("id_negeri",negeri);				      
			      sql = rnegeri.getSQLSelect("Tblrujnegeri");
			
			      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
			      while (rsnegeri.next()) {
			    	  abbrev = rsnegeri.getString("abbrev");
			      }
			      
			      Statement stmtseksyen = db.getStatement();
			      SQLRenderer rseksyen = new SQLRenderer();				 
			      rseksyen.add("id_seksyen");
			      rseksyen.add("kod_Seksyen");				      
			      rseksyen.add("id_seksyen",seksyen);				      
			      sql = rseksyen.getSQLSelect("Tblrujseksyen");
			
			      ResultSet rsseksyen = stmtseksyen.executeQuery(sql);
			      while (rsseksyen.next()) {
			    	  sksyn = rsseksyen.getString("kod_Seksyen");
			      }
		    	
			      Statement stmturusan = db.getStatement();
			      SQLRenderer rurusan = new SQLRenderer();				 
			      rurusan.add("id_urusan");
			      rurusan.add("kod_Urusan");				      
			      rurusan.add("id_urusan",urusan);				      
			      sql = rurusan.getSQLSelect("Tblrujurusan");
			
			      ResultSet rsurusan = stmturusan.executeQuery(sql);
			      while (rsseksyen.next()) {
			    	  ursn = rsurusan.getString("kod_Urusan");
			      }
		    	  
			      Statement stmtsuburusan = db.getStatement();
			      SQLRenderer rsuburusan = new SQLRenderer();				 
			      rsuburusan.add("id_suburusan");
			      rsuburusan.add("kod_Suburusan");				      
			      rsuburusan.add("id_suburusan",urusan);				      
			      sql = rsuburusan.getSQLSelect("Tblrujsuburusan");
			
			      ResultSet rssuburusan = stmtsuburusan.executeQuery(sql);
			      while (rssuburusan.next()) {
			    	  subUrsn = rssuburusan.getString("kod_Suburusan");
			      }
			      
			      Statement stmttaraf = db.getStatement();
			      SQLRenderer rtaraf = new SQLRenderer();				 
			      rtaraf.add("id_tarafkeselamatan");
			      rtaraf.add("kod_Taraf_Keselamatan");				      
			      rtaraf.add("id_tarafkeselamatan",taraf);				      
			      sql = rtaraf.getSQLSelect("Tblpfdrujtarafkeselamatan");
			
			      ResultSet rstaraf = stmttaraf.executeQuery(sql);
			      while (rstaraf.next()) {
			    	  trf = rstaraf.getString("kod_Taraf_Keselamatan");
			      }
		    	
		    	
				
				
				if (negeri != null){
					
					if  (negeri == 16){
						
						if (trf != null){
						
							if (urusan != null && suburusan != null){
								noFail =  "JKPTG("+ trf + ")/101/"+ sksyn + "/" + ursn + "/" + subUrsn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  "JKPTG("+ trf + ")/101/"+ sksyn + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));	
							}
						}
						else{
							if (urusan != null && suburusan != null){
								noFail =  noFailStandard + sksyn + "/" + ursn + "/" + subUrsn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  noFailStandard + sksyn + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						
					}
					else{
						
						if (trf != null){
							
							if (urusan != null && suburusan != null){
								noFail =  "JKPTG("+ trf + ")/101/"+ abbrev + "/" + ursn + "/" + subUrsn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  "JKPTG("+ trf + ")/101/"+ abbrev + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						else{
							
							if (urusan != null && suburusan != null){
								noFail =  noFailStandard + abbrev + "/" + ursn + "/" + subUrsn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));
							}
							else if (urusan != null && suburusan == null){
								noFail =  noFailStandard + abbrev + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

							}
						}
						
					}
				}

				return noFail;
			}
		    else{
		    	
		    	Statement stmtnegeri = db.getStatement();
			      SQLRenderer rnegeri = new SQLRenderer();				 
			      rnegeri.add("id_negeri");
			      rnegeri.add("abbrev");				      
			      rnegeri.add("id_negeri",negeri);				      
			      sql = rnegeri.getSQLSelect("Tblrujnegeri");
			
			      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
			      while (rsnegeri.next()) {
			    	  abbrev = rsnegeri.getString("abbrev");
			      }
			      
			      Statement stmtseksyen = db.getStatement();
			      SQLRenderer rseksyen = new SQLRenderer();				 
			      rseksyen.add("id_seksyen");
			      rseksyen.add("kod_Seksyen");				      
			      rseksyen.add("id_seksyen",seksyen);				      
			      sql = rseksyen.getSQLSelect("Tblrujseksyen");
			
			      ResultSet rsseksyen = stmtseksyen.executeQuery(sql);
			      while (rsseksyen.next()) {
			    	  sksyn = rsseksyen.getString("kod_Seksyen");
			      }
		    	
			      Statement stmturusan = db.getStatement();
			      SQLRenderer rurusan = new SQLRenderer();				 
			      rurusan.add("id_urusan");
			      rurusan.add("kod_Urusan");				      
			      rurusan.add("id_urusan",urusan);				      
			      sql = rurusan.getSQLSelect("Tblrujurusan");
			
			      ResultSet rsurusan = stmturusan.executeQuery(sql);
			      while (rsseksyen.next()) {
			    	  ursn = rsurusan.getString("kod_Urusan");
			      }
		    	  
			      Statement stmttaraf = db.getStatement();
			      SQLRenderer rtaraf = new SQLRenderer();				 
			      rtaraf.add("id_tarafkeselamatan");
			      rtaraf.add("kod_Taraf_Keselamatan");				      
			      rtaraf.add("id_tarafkeselamatan",taraf);				      
			      sql = rtaraf.getSQLSelect("Tblpfdrujtarafkeselamatan");
			
			      ResultSet rstaraf = stmttaraf.executeQuery(sql);
			      while (rstaraf.next()) {
			    	  trf = rstaraf.getString("kod_Taraf_Keselamatan");
			      }
		    	
				
				if (negeri != null){
					
					if  (negeri == 16){
						
						if (trf != null){
							
							noFail =  "JKPTG("+ trf + ")/101/"+ sksyn + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						else{
						
							noFail =  noFailStandard + sksyn + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}						
					}
					else{
						
						if (trf != null){
							
							noFail =  "JKPTG("+ trf + ")/101/"+ abbrev + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						else{
						
							noFail =  noFailStandard + abbrev + "/" + ursn + "-" + String.format("%06d",File.getSeqNo(seksyen, urusan , 0, negeri));

						}
						
					}
				}

				return noFail;
		    	
		    }
			
	 	}

	 public static void update(Hashtable data) throws Exception{
		 
			Db db = null;
		   	db = new Db();
		    String sql = "";
		    long idStatus = 0;
		    String new_nofail = (String)data.get("new_nofail");
		    String taraf = (String)data.get("id_Tarafkeselamatan");

		    String sql2 = "SELECT a.id_status,a.kod_status"+
		    " FROM tblrujstatus a"+
		    " WHERE a.id_seksyen = 6 AND a.kod_status = '09'";
		    
		    
		    Statement stmt = db.getStatement();
		    ResultSet rs = stmt.executeQuery(sql2);
		    rs.next();
		    idStatus = rs.getLong(1);
		    
		    try
		    {
		    	String idFailAsal = (String)data.get("idFailAsal");
		    	
		    	
				
				SQLRenderer r = new SQLRenderer();
				r.update("id_Fail", idFailAsal);
				//r.add("id_Status",idStatus);
				r.add("no_fail",new_nofail);
				r.add("id_Tarafkeselamatan", taraf);
				 
				 sql = r.getSQLUpdate("tblpfdfail");
				 
			     stmt.executeUpdate(sql);
		    }
		    finally {
			      if (db != null) db.close();
			    }
		 

	 }
	public static Hashtable tukartaraf(String OnoFile, String taraf) {
		// TODO Auto-generated method stub
		String New_status = "JKPTG";
		String[] values = OnoFile.split("/");
		String old_idTarafKeselamatan = "1";
		if("5".equals(taraf)){//rahsia besar			
			New_status = "JKPTG(RB)";
		}else if("4".equals(taraf)){//rahsia
			New_status = "JKPTG(R)";
		}else if("3".equals(taraf)){//sulit
			New_status = "JKPTG(S)";
		}else if("2".equals(taraf)){//terhad
			New_status = "JKPTG(T)";
		}else if("1".equals(taraf)){//terbuka
			New_status = "JKPTG";
		}
		String olf = values[0].trim();
		if("JKPTG(RB)".equals(olf)){//rahsia besar			
			old_idTarafKeselamatan = "5";
		}else if("JKPTG(R)".equals(olf)){//rahsia
			old_idTarafKeselamatan = "4";
		}else if("JKPTG(S)".equals(olf)){//sulit
			old_idTarafKeselamatan = "3";
		}else if("JKPTG(T)".equals(olf)){//terhad
			old_idTarafKeselamatan = "2";
		}else if("JKPTG".equals(olf)){//terbuka
			old_idTarafKeselamatan = "1";
		}
		 Hashtable k = new Hashtable();
		String new_noFail = OnoFile.replace(values[0], New_status);
		System.out.println(new_noFail);
		 k.put("new_nofile",new_noFail);
		 k.put("old_idTarafKeselamatan",old_idTarafKeselamatan);
		return k;
	}
	
	 public static Vector getPaparSemuaFail(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				Vector paparSemuaFail = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT G.NAMA_NEGERI,F.KOD_SEKSYEN, F.NAMA_SEKSYEN" +
					" , A.ID_NEGERI,A.ID_FAIL, A.ID_TARAFKESELAMATAN, A. ID_SEKSYEN, A.ID_AKTIVITI, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.ID_LOKASIFAIL, A.FAHARASAT, A.ID_STATUS, A.FLAG_VIEW_FILE, " +
					" B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
					" FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E, TBLRUJSEKSYEN F, TBLRUJNEGERI G " +
					" WHERE " +
					" A.ID_URUSAN = B.ID_URUSAN " +
					" AND A.ID_SUBURUSAN = C.ID_SUBURUSAN(+) " +
					" AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN(+) " +
					" AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
					" AND A.ID_SEKSYEN = F.ID_SEKSYEN " +
					" AND A.ID_NEGERI = G.ID_NEGERI " +
					" AND A.ID_FAIL='"+idFail+"'";
				//System.out.println("SQL Taraf Keselamatan---"+sql);
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
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFailAsal",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("KOD_SEKSYEN",rs.getString("KOD_SEKSYEN")==null?"":rs.getString("KOD_SEKSYEN"));
					h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN")==null?"":rs.getString("NAMA_SEKSYEN"));
					
					
					paparSemuaFail.addElement(h);
					
				}
				
				
				return paparSemuaFail;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
	public static String updateFail(Hashtable data) throws Exception {
		 Db db = new Db();
		   
		 String sql = "";
		 
		    try
		    {
		    	  String idFailAsal = (String)data.get("idFailAsal");
		    	  String negeri = (String)data.get("id_Negeri");
		    	  String daerah = (String)data.get("id_Daerah");
		    	  String seksyen = (String)data.get("id_Seksyen");
		    	  String urusan = (String)data.get("id_Urusan");
		    	  String suburusan = (String)data.get("id_Suburusan");
		    	  String subsuburusan = (String)data.get("id_SubSubUrusan");
		    	  String subsubsuburusan = (String)data.get("id_Subsubsuburusan");
		    	  String idAktiviti = (String)data.get("id_Aktiviti");
		    	  String taraf = (String)data.get("id_Tarafkeselamatan");
			      String idMasuk = (String)data.get("id_Masuk");
			    
			      String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,subsubsuburusan,idAktiviti,taraf);
			      int noTurutan = 0;
			      
			      if("".equalsIgnoreCase(check)){
			    	 noTurutan = 0;
					
			      }
			      else{
					 noTurutan = Integer.parseInt(check);
			      }
					
			      String nofailTukarTaraf = "";
			      
			      if("16".equalsIgnoreCase(negeri)){
			    	  nofailTukarTaraf = FrmPendaftaranFailData.generateNoFailBaruSeksyen(seksyen,negeri,urusan,suburusan,subsuburusan,subsubsuburusan,taraf,idAktiviti,noTurutan);
			      }
			      else{
			    	  nofailTukarTaraf = FrmPendaftaranFailData.generateNoFailNegeri(seksyen,negeri,urusan,suburusan,subsuburusan,subsubsuburusan,taraf,idAktiviti,noTurutan);
			      }
			      
			      String newNofailTukarTaraf = nofailTukarTaraf;
			    			      
			      Statement stmt = db.getStatement();  
			      
				    String sqlUpdate = "UPDATE TBLPFDFAIL SET (ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
		   				+"FLAG_FAIL, ID_STATUS, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
		   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN) =";
	  
				    String sqlSelect = "(SELECT '"+taraf+"', ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, sysdate, TAJUK_FAIL, '"+newNofailTukarTaraf+"', '"+newNofailTukarTaraf+"', ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, ID_FAHARASAT, "
		   				+"FLAG_FAIL, ID_STATUS, CATATAN, '"+idMasuk+"', sysdate, ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "
		   				+"FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_AKTIVITI, ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, NO_TURUTAN "
		   				+"FROM TBLPFDFAIL WHERE ID_FAIL = '"+idFailAsal+"') "
		   				+"WHERE ID_FAIL = '"+idFailAsal+"'";
	  


				    sql = sqlUpdate+" "+sqlSelect;
				    //sql = sqlSelect;
				    stmt.executeUpdate(sql);  
			      
			      return "" +idFailAsal;
			      
			    } finally {
			      if (db != null) db.close();
			    }
	}

	private static String checkNoFail(String negeri, String seksyen,String urusan, String suburusan, String subsuburusan,String subsubsuburusan,String idAktiviti, String taraf) throws Exception {
		
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      if("16".equalsIgnoreCase(negeri)){
			      sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
					" id_negeri = "+ negeri + 
					" and id_seksyen = "+ seksyen + 
					" and id_urusan = "+ urusan;
					if(!"".equalsIgnoreCase(suburusan)){
						sql = sql +  " and id_suburusan = "+ suburusan;
					}
			      	if(!"".equalsIgnoreCase(subsuburusan)){
			      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
			      	}
		      }
		      else
		      {
		    	  sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
					" id_negeri = "+ negeri + 
					//" and id_seksyen = "+ seksyen + 
					" and id_urusan = "+ urusan;
					if(!"".equalsIgnoreCase(suburusan)){
						sql = sql +  " and id_suburusan = "+ suburusan;
					}
			      	if(!"".equalsIgnoreCase(subsuburusan)){
			      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
			      	}
		      }
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("no_turutan")==null?"":rs.getString("no_turutan");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }

	}
	public static Vector getPaparFail(String idFailBaru) throws Exception {
		String sql = "";
		Db db = null;
		
		try{
			paparFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, FLAG_VIEW_FILE " +
				  "FROM TBLPFDFAIL " +
				  "WHERE ID_FAIL='"+idFailBaru+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFailBaru",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("noFailBaru",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
				h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				
				
				paparFail.addElement(h);
				
			}
			
			
			return paparFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector getPaparDetailFailAsal(String idFailAsal) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			paparDetailFailAsal = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_FAIL, ID_SEKSYEN, ID_NEGERI, ID_DAERAH, ID_URUSAN, ID_SUBURUSAN, ID_SUBSUBURUSAN, ID_SUBSUBSUBURUSAN, ID_TARAFKESELAMATAN, ID_AKTIVITI, NO_TURUTAN " +
				  "FROM TBLPFDFAIL " +
				  "WHERE ID_FAIL='"+idFailAsal+"'";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFailAsal",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
				h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("idSubsuburusan",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
				h.put("idSubsubsuburusan",rs.getString("ID_SUBSUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBSUBURUSAN"));
				h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
				h.put("noTurutan",rs.getString("NO_TURUTAN")==null?"":rs.getString("NO_TURUTAN"));
			
				paparDetailFailAsal.addElement(h);
				
			}
			
			
			return paparDetailFailAsal;
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}


}
