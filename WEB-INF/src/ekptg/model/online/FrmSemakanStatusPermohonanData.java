package ekptg.model.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmSemakanStatusPermohonanData {
	
	private static Vector listPPK = new Vector();
	private static Vector listPPT = new Vector();
	private static Vector listPHP = new Vector();
	private static Vector listHTP = new Vector();
	public static void  setCarianPermohonanPPK(String checkRadio,String carianPPK)throws Exception{
		
		Db db = null;
	    listPPK.clear();
	    String sql = "";
	    
		
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		     
					
					sql = "SELECT A.NO_FAIL, C.NO_PERMOHONAN_ONLINE,B.KETERANGAN,D.NO_KP_BARU,E.NO_SIJIL_MATI " +
						  " FROM TBLPFDFAIL A, TBLRUJSTATUS B, TBLPPKPERMOHONAN C, TBLPPKPEMOHON D, TBLPPKSIMATI E, TBLPPKPERMOHONANSIMATI F"+
						  " WHERE C.ID_FAIL = A.ID_FAIL " +
						  " AND C.ID_STATUS = B.ID_STATUS(+)" +
						  " AND D.ID_PERMOHONAN = C.ID_PERMOHONAN" +
						  " AND F.ID_PERMOHONAN = C.ID_PERMOHONAN" +
						  " AND F.ID_SIMATI = E.ID_SIMATI";
					
					if("noFail".equals(checkRadio)){
						if (!carianPPK.trim().equals("")) {
						sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'" + carianPPK.toUpperCase() + "'|| '%'";
						}
					}
					if ("noPermohonanOnline".equals(checkRadio)){
						if (!carianPPK.trim().equals("")) {
						sql = sql + " AND UPPER(C.NO_PERMOHONAN_ONLINE) LIKE '%' ||'" + carianPPK.toUpperCase() + "'|| '%'";
						}
					}
					if ("noKPPemohon".equals(checkRadio)){
						if (!carianPPK.trim().equals("")) {
						sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + carianPPK.toUpperCase() + "'|| '%'";
						}
					}
					if ("noSijilMati".equals(checkRadio)){
						if (!carianPPK.trim().equals("")) {
						sql = sql + " AND UPPER(E.NO_SIJIL_MATI) LIKE '%' ||'" + carianPPK.toUpperCase() + "'|| '%'";
						}
					}
					 sql = sql + " ORDER BY A.ID_FAIL ASC";
					 ResultSet rs = stmt.executeQuery(sql);
					 
				      Hashtable h;
				      int bil = 1;
				      int count = 0;
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("bil",bil);
				    	  h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				    	  h.put("noPermohonanOnline",rs.getString("NO_PERMOHONAN_ONLINE")== null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
				    	  h.put("noKPPemohon",rs.getString("NO_KP_BARU")== null?"":rs.getString("NO_KP_BARU"));
				    	  h.put("noSijilMati",rs.getString("NO_SIJIL_MATI")== null?"":rs.getString("NO_SIJIL_MATI"));
				    	  h.put("keterangan", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
				    	  listPPK.addElement(h);
				    	  bil++;
				    	  count++;
				    	  
				      }
				      if (count == 0){
				    	  h = new Hashtable();
				    	  h.put("bil","");
				    	  h.put("noFail","Tiada rekod.");
				    	  h.put("noPermohonanOnline","");
				    	  h.put("noKPPemohon","");
				    	  h.put("noSijilMati","");
				    	  h.put("keterangan", "");
				    	  listPPK.addElement(h);
				      }
		    
		      
		 }finally {
		      if (db != null) db.close();
		    }
	}
	 public static Vector getListPPK(){
		 
		  return listPPK;
	  }
	 public static void  setCarianPermohonanPPT(String noPermohonanOnline, String noFail, String tkhPermohonan, String statusFail)throws Exception{
			
			Db db = null;
		    listPPT.clear();
		    String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
	
						sql = "SELECT A.NO_PERMOHONAN, B.NO_FAIL ,A.TARIKH_PERMOHONAN,C.KETERANGAN" +
							  " FROM TBLPPTPERMOHONAN A, TBLPFDFAIL B, TBLRUJSTATUS C" +
							  " WHERE A.ID_FAIL = B.ID_FAIL" +
							  " AND A.ID_STATUS = C.ID_STATUS";
						
						if (noPermohonanOnline != null) {
							if (!noPermohonanOnline.trim().equals("")) {
								sql = sql + " AND UPPER(A.NO_PERMOHONAN) LIKE '%' ||'" + noPermohonanOnline.toUpperCase() + "'|| '%'";
							}
						}
				      
						if (noFail != null) {
							if (!noFail.trim().equals("")) {
								sql = sql + " AND UPPER(B.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
							}
						}
				      
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
					      if (tkhPermohonan != null) {
								if (!tkhPermohonan.toString().trim().equals("")) {			 
									sql = sql + " AND A.TARIKH_PERMOHONAN = '" + sdf1.format(sdf.parse(tkhPermohonan)).toUpperCase() +"'";
								}
							}
					      if (statusFail != null) {
								if (!statusFail.trim().equals("")) {
									if (!statusFail.trim().equals("0")) {
										sql = sql + " AND A.ID_STATUS = '" + statusFail + "'";
									}
								}
							}
						
						 sql = sql + " ORDER BY B.ID_FAIL DESC";
						 
						 ResultSet rs = stmt.executeQuery(sql);
					      Hashtable h;
					      int bil = 1;
					      int count = 0;
					      while (rs.next()) {
					    	  h = new Hashtable();
					    	  h.put("bil",bil);
					    	  h.put("noPermohonanOnline",rs.getString("NO_PERMOHONAN")== null?"":rs.getString("NO_PERMOHONAN"));
					    	  h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
					    	  h.put("tkhPermohonan",rs.getDate("TARIKH_PERMOHONAN")== null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));
					    	  h.put("keterangan", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
					    	  listPPT.addElement(h);
					    	  bil++;
					    	  count++;
					    	  
					      }
					      if (count == 0){
					    	  h = new Hashtable();
					    	  h.put("bil","");
					    	  h.put("noPermohonanOnline","Tiada rekod.");
					    	  h.put("noFail","");
					    	  h.put("tkhPermohonan","");
					    	  h.put("keterangan", "");
					    	  listPPT.addElement(h);
					      }
			    
			      
			 }finally {
			      if (db != null) db.close();
			    }
		}
		 public static Vector getListPPT(){
			 
			  return listPPT;
		  }
		 public static void  setCarianPermohonanPHP(String noFail, String namaPemohon, String noKP, String statusFail)throws Exception{
				
				Db db = null;
				listPHP.clear();
			    String sql = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				 try {
				      db = new Db();
				      Statement stmt = db.getStatement();
		
							sql = "SELECT D.NAMA, D.NO_PENGENALAN,D.NO_KP_LAMA, G.NO_FAIL,F.KETERANGAN"+
								  " FROM TBLPHPPERMOHONANPELEPASAN A,TBLPHPPERMOHONANSEWA B,TBLPHPPMOHONNJDUALPERTAMA C,TBLPHPPEMOHON D,TBLPERMOHONAN E,TBLRUJSTATUS F,TBLPFDFAIL G,TBLRUJSUBURUSANSTATUS H,TBLRUJSUBURUSANSTATUSFAIL I"+
								  " WHERE E.ID_PERMOHONAN = A.ID_PERMOHONAN(+)"+
								  " AND E.ID_PERMOHONAN = B.ID_PERMOHONAN(+)"+
							      " AND E.ID_PERMOHONAN =C.ID_PERMOHONAN(+)"+
							      " AND G.ID_FAIL = E.ID_FAIL"+
							      " AND E.ID_PERMOHONAN = D.ID_PERMOHONAN"+
							      " AND F.ID_STATUS = H.ID_STATUS"+
							      " AND E.ID_PERMOHONAN = I.ID_PERMOHONAN"+
							      " AND H.ID_SUBURUSANSTATUS = I.ID_SUBURUSANSTATUS";
							
					      
							if (noFail != null) {
								if (!noFail.trim().equals("")) {
									sql = sql + " AND UPPER(G.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
								}
							}
							if (namaPemohon != null) {
								if (!namaPemohon.trim().equals("")) {
									sql = sql + " AND UPPER(D.NAMA) LIKE '%' ||'" + namaPemohon.toUpperCase() + "'|| '%'";
								}
							}
							if (noKP != null) {
								if (!noKP.trim().equals("")) {
									sql = sql + " AND UPPER(D.NO_PENGENALAN) LIKE '%' ||'" + noKP.toUpperCase() + "'|| '%'";
									sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKP.toUpperCase() + "'|| '%'";

								}
							}
					      
						    if (statusFail != null) {
									if (!statusFail.trim().equals("")) {
										if (!statusFail.trim().equals("0")) {
											sql = sql + " AND H.ID_STATUS = '" + statusFail + "'";
										}
									}
							}
							
							 sql = sql + " ORDER BY G.ID_FAIL";
							 
							 ResultSet rs = stmt.executeQuery(sql);
						      Hashtable h;
						      int bil = 1;
						      int count = 0;
						      while (rs.next()) {
						    	  h = new Hashtable();
						    	  h.put("bil",bil);
						    	  h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
						    	  h.put("namaPemohon",rs.getString("NAMA")== null?"":rs.getString("NAMA"));
						    	  
						    	  if (rs.getString("NO_PENGENALAN") != null){
							    	  h.put("noKP",rs.getString("NO_PENGENALAN")== null?"":rs.getString("D.NO_PENGENALAN"));
 
						    	  }
						    	  else{
							    	  h.put("noKP",rs.getString("NO_KP_LAMA")== null?"":rs.getString("NO_KP_LAMA"));
 
						    	  }
 
						    	  h.put("keterangan", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
						    	  listPHP.addElement(h);
						    	  bil++;
						    	  count++;
						    	  
						      }
						      if (count == 0){
						    	  h = new Hashtable();
						    	  h.put("bil","");
						    	  h.put("noFail","Tiada rekod.");
						    	  h.put("namaPemohon","");
						    	  h.put("noKP","");
						    	  h.put("keterangan", "");
						    	  listPHP.addElement(h);
						      }
				    
				      
				 }finally {
				      if (db != null) db.close();
				    }
			}
			 public static Vector getListPHP(){
				 
				  return listPHP;
			  } 
			 
			 public static void  setCarianPermohonanHTP(String noFail, String noHakmilik, String statusFail)throws Exception{
					
					Db db = null;
					listHTP.clear();
				    String sql = "";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					 try {
					      db = new Db();
					      Statement stmt = db.getStatement();
			
								sql = "SELECT A.NO_HAKMILIK,B.NO_FAIL, C.KETERANGAN" +
									  " FROM TBLHTPPERMOHONAN D,TBLPERMOHONAN E,TBLHTPHAKMILIK A,TBLPFDFAIL B,TBLRUJSTATUS C,"+
									  " TBLRUJSUBURUSANSTATUSFAIL F,TBLRUJSUBURUSANSTATUS G"+
									  " WHERE E.ID_PERMOHONAN = D.ID_PERMOHONAN(+)"+
									  " AND B.ID_FAIL = E.ID_FAIL"+
									  " AND E.ID_PERMOHONAN =F.ID_PERMOHONAN"+
									  " AND C.ID_STATUS = G.ID_STATUS"+
									  " AND G.ID_SUBURUSANSTATUS =F.ID_SUBURUSANSTATUS";
								
						      
								if (noFail != null) {
									if (!noFail.trim().equals("")) {
										sql = sql + " AND UPPER(B.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase() + "'|| '%'";
									}
								}
								if (noHakmilik != null) {
									if (!noHakmilik.trim().equals("")) {
										sql = sql + " AND UPPER( A.NO_HAKMILIK) LIKE '%' ||'" + noHakmilik.toUpperCase() + "'|| '%'";
									}
								}
							      if (statusFail != null) {
										if (!statusFail.trim().equals("")) {
											if (!statusFail.trim().equals("0")) {
												sql = sql + " AND G.ID_STATUS = '" + statusFail + "'";
											}
										}
									}
								
								 sql = sql + " ORDER BY B.ID_FAIL";
								 
								 ResultSet rs = stmt.executeQuery(sql);
							      Hashtable h;
							      int bil = 1;
							      int count = 0;
							      while (rs.next()) {
							    	  h = new Hashtable();
							    	  h.put("bil",bil);
							    	  h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
							    	  h.put("noHakmilik",rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
							    	  h.put("keterangan", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
							    	  listHTP.addElement(h);
							    	  bil++;
							    	  count++;
							    	  
							      }
							      if (count == 0){
							    	  h = new Hashtable();
							    	  h.put("bil","");
							    	  h.put("noFail","Tiada rekod.");
							    	  h.put("noHakmilik","");
							    	  h.put("keterangan", "");
							    	  listHTP.addElement(h);
							    	 
							      }
					    
					      
					 }finally {
					      if (db != null) db.close();
					    }
				}
				 public static Vector getListHTP(){
					 
					  return listHTP;
				  } 



}
