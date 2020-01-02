package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

public class FrmRynSek8Rayuan {
	static Logger myLogger = Logger.getLogger(FrmRynSek8Rayuan.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	//this
	//seksyen 8 (campur dengan seksyen 17)
	private static Vector listDefault = new Vector();
	private static Vector listCarian = new Vector();
	private static Vector listSemakK2 = new Vector();
	private static Vector listBayaranK2 = new Vector();
	private static Vector listDataMaklumat = new Vector();
	private static Vector listDataMaklumatSerahan = new Vector();
	private static Vector listLatestPeguam = new Vector();
	private static Vector listcbOB = new Vector();
	
	//--02122009
	private static Vector listMstRayuan = new Vector();
	
	//seksyen 17
	private static Vector listDefaultSek17 = new Vector();
	private static Vector listCarianSek17 = new Vector();
	
	
	//seksyen 8
	public static Vector getListDefault(){
		return listDefault;
	}
	public static Vector getListCarian(){
		return listCarian;
	}
	public static Vector getListSemakK2(){
		return listSemakK2;
	}
	public static Vector getBayaranK2(){
		return listBayaranK2;
	}
	public static Vector getDataMaklumat(){
		return listDataMaklumat;
	}
	public static Vector getDataMaklumatSerahan(){
		return listDataMaklumatSerahan;
	}
	public static Vector getLatestPeguam(){
		return listLatestPeguam;
	}
	public static Vector getListcbOB(){
		return listcbOB;
	}
	
	//--02122009
	public static Vector getDataMstRayuan(){
		return listMstRayuan;
	}
	
	//seksyen 17
	public static Vector getListDefaultSek17(){
		return listDefaultSek17;
	}
	public static Vector getListCarianSek17(){
		return listCarianSek17;
	}
	
	
	
	//list default seksyen 8
	public static void setListDefault(String usid)throws Exception {
		
	    Db db = null;
	    listDefault.clear();
	    String sql = "";
	    String sqlx = "";
	    String idpermohonan = "";
	    
	    try {
	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	      
	      //SYARAT
	      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
	      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
	      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI, "
	      + " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN WHERE id_permohonan = ms.id_permohonan AND id_status = '21' AND tarikh_kemaskini < SYSDATE - 14)AS flag"
	      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
	      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
	      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
		  
		  
		   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
		  
		  sql += " ) " 
	      + " AND P.ID_STATUS = S.ID_STATUS "
	      + " AND P.ID_FAIL = F.ID_FAIL(+) "
	      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
	      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
	      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
	      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
	      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
	      + " AND (P.ID_STATUS=21 OR P.ID_STATUS=64 OR P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180 OR P.ID_STATUS=25) "
	      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
	      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
	      + " AND STA.AKTIF = '1' "
	      + " AND P.ID_STATUS <> '999' " 
	      + " AND P.SEKSYEN = '8' "
	      + " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
	      //+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";

	      sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
				h.put("bil", bil);
				h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
				listDefault.addElement(h);
				bil++;	
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close list default
	
	
	//CARIAN seksyen 8
	public static void setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid)throws Exception {
	   
		Db db = null;
	    listCarian.clear();
	    String sql = "";
	    String sqlx = "";
	    String idpermohonan = "";
	    
	    try {
	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
  
	    	String chkDataFail = noFail.trim();
	    	String chkDataPemohon = namaPemohon.trim();
	    	String chkDataSimati = namaSimati.trim();
	    	String chkDataIcSimati = icSimati.trim();
	    	String chkDataJenisKp = JenisIc;
	      
	      //SYARAT
	      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
	      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
	      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI, "
	      + " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN WHERE id_permohonan = ms.id_permohonan AND id_status = '21' AND tarikh_kemaskini < SYSDATE - 14)AS flag"
	      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
	      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
	      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
		  
		   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
		  
		  sql += " ) " 
	      + " AND P.ID_STATUS = S.ID_STATUS "
	      + " AND P.ID_FAIL = F.ID_FAIL(+) "
	      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
	      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
	      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
	      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
	      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
	      + " AND (P.ID_STATUS=21 OR P.ID_STATUS=64 OR P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180 OR P.ID_STATUS=25) "
	      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
	      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
	      + " AND STA.AKTIF = '1' "
	      + " AND P.ID_STATUS <> '999' " 
	      + " AND P.SEKSYEN = '8' "
	      + " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
	      // + " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
	    
	      //NO FAIL
	      if(noFail != null)
	      	{
	    	  if(!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
				}
	      	}//close if nofail
	      
	      //NAMA PEMOHON
	      if (namaPemohon != "") {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
				}
			}//close if pemohon
	      
	      //NAMA SIMATI
	      if (namaSimati != "") {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
				}
			}//close if nama simati
	   	  
	      //IC SIMATI
	      if (icSimati != "") {
			   if (!icSimati.trim().equals("")) {
					if (chkDataJenisKp.equals("1")){
						sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("2")){
						sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
					else if (chkDataJenisKp.equals("3")){
						sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
					}
		    	}
			}//close if ic simati  
	      
	      //sorting
	      sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  	h.put("bil", bil);
				h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getString("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("nama_simati",rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
				listCarian.addElement(h);
				bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	//simpan Semakan K2
	public static void simpanSemakanK2(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran, String jumlahbayaran) throws Exception
	  {
		
	    Db db = null;
	    
	    String sql = "";
	    String sqlBayaran = "";
	    String sqlP = "";
	    String sql5 = "";
	    String sql6 = "";
	    
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	    	long id_semakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	    	
	    	//id login
	 	    String id_masuk = (String)data.get("id_masuk");
	 	    
	 	    String tarikhTerimaRayuan = (String)data.get("tarikhTerimaRayuan");
	 	   
	    	long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
		    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
		    String tarikh_rayuan = "to_date('" + tarikhTerimaRayuan + "','dd/MM/yyyy')";
		    
		    //insert data into tblsemakanhantar
		    Statement stmtS = db.getStatement();
		    sql = "INSERT INTO Tblsemakanhantar " +
			"(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
			"VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_masuk+"', sysdate) ";
		    stmtS.executeUpdate(sql);
		    
		    //insert into tblppkbayaran
		    if (idsemakan.equals("124")) 
		    {
			     
		    	SQLRenderer r1 = new SQLRenderer();
			      r1.add("id_bayaran", idBayaran);
			      r1.add("id_permohonan", idpermohonan);
			      r1.add("id_jenisbayaran",3);
			      r1.add("no_resit",noresit);
			      r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
			      r1.add("jumlah_bayaran",50);
			      r1.add("id_masuk",id_masuk); 
			      r1.add("tarikh_masuk",r1.unquote("sysdate")); 
			    sqlBayaran = r1.getSQLInsert("tblppkbayaran");
			    stmt.executeUpdate(sqlBayaran);
			}
		    
		    //insert into tblppkbayaran
		    if (idsemakan.equals("125")) 
		    {
			     
			    SQLRenderer rB = new SQLRenderer();
			      rB.add("id_bayaran", idBayaran);
			      rB.add("id_permohonan", idpermohonan);
			      rB.add("id_jenisbayaran",4);
			      rB.add("no_resit",noresit);
			      rB.add("tarikh_bayaran",rB.unquote(tarikh_bayaran));
			      rB.add("jumlah_bayaran",jumlahbayaran);
			      rB.add("id_masuk",id_masuk); 
			      rB.add("tarikh_masuk",rB.unquote("sysdate")); 
			      sqlBayaran = rB.getSQLInsert("tblppkbayaran");
			    stmt.executeUpdate(sqlBayaran);
			}
		    
		    	//change status [21] to permohonan rayuan[64]
		    	int status_pr = 64;
		    	
		    	SQLRenderer rP = new SQLRenderer();
		    	  rP.update("id_permohonan", idpermohonan);
		    	  rP.add("id_status", status_pr);
		    	  rP.add("id_kemaskini",id_masuk); 
		    	  rP.add("tarikh_rayuan",rP.unquote(tarikh_rayuan)); 
		     	  rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
		     	sqlP = rP.getSQLUpdate("tblppkpermohonan");
		     	stmt.executeUpdate(sqlP);
		    
		     	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanSemakanK2
	
	
	//list semakan k2
	public static void setListSemakK2(String id_permohonan)throws Exception {
		
	    Db db = null;
	    listSemakK2.clear();
	    String sql = "";
	    
	    try {
	    	
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      //SYARAT
	      sql =  "SELECT distinct a.id_permohonan,h.id_semakanhantar,s.id_semakansenarai,a.id_status ";
	      sql += "FROM Tblppkpermohonan a, Tblsemakanhantar h, Tblsemakansenarai s ";
	      sql += "WHERE h.id_permohonan = a.id_permohonan AND h.id_semakansenarai = s.id_semakansenarai ";
	      sql += "AND a.id_permohonan = '" +id_permohonan+ "'";
	      sql += "AND s.id_semakansenarai = ANY(121,122,123,124,125,126) ";
	      
	      //sorting
	      sql +="ORDER BY s.id_semakansenarai desc";
	    
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
	    	  h.put("id_semakanhantar", rs.getString("id_semakanhantar")==null?"":rs.getString("id_semakanhantar"));
	    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	  h.put("id_semakansenarai", rs.getString("id_semakansenarai")==null?"":rs.getString("id_semakansenarai"));
	    	  
	    	  listSemakK2.addElement(h);
			  bil++;
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close list semakan k2
	
	
	//data bayaran
	public static void setBayaranK2(String id_permohonan)throws Exception {
		
	    Db db = null;
	    listBayaranK2.clear();
	    String sql = "";
	    
	    try {
	    	
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      //SYARAT
	      sql =  "SELECT distinct a.id_permohonan, b.id_bayaran, b.id_jenisbayaran, b.no_resit, b.tarikh_bayaran, b.jumlah_bayaran ";
	      sql += "FROM Tblppkbayaran b, Tblppkpermohonan a ";
	      sql += "WHERE b.id_permohonan = a.id_permohonan ";
	      sql += "AND a.id_permohonan = '" +id_permohonan+ "'";
	      sql += " AND b.id_jenisbayaran = ANY(3,4) ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  
	    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
	    	  h.put("id_bayaran", rs.getString("id_bayaran")==null?"":rs.getString("id_bayaran"));
	    	  h.put("id_jenisbayaran", rs.getString("id_jenisbayaran")==null?"":rs.getString("id_jenisbayaran"));
	    	  h.put("no_resit",rs.getString("no_resit")==null?"":rs.getString("no_resit"));
	    	  h.put("tarikh_bayaran", rs.getString("tarikh_bayaran")==null?"":Format.format(rs.getDate("tarikh_bayaran")));
	    	  if(rs.getString("jumlah_bayaran") != null && rs.getString("jumlah_bayaran") != "" ){
			    	String jb = rs.getString("jumlah_bayaran");
			    	double jumbayar = Double.parseDouble(jb);
			    	h.put("jumlah_bayaran", jumbayar);
			    }else{
			    	h.put("jumlah_bayaran", "");
			    }
				listBayaranK2.addElement(h);
				bil++;
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close data bayaran
	
	
	//delete semakan k2 checkbox
	public static void semakanDelete(String id_permohonan) throws Exception {
		
	    Db db = null;
	    String sql1 = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		sql1 =  "DELETE FROM tblsemakanhantar WHERE id_permohonan = '"+id_permohonan+"'";
	    		sql1 += " AND id_semakansenarai = ANY(121,122,123,124,125,126) ";      
	    		stmt.executeUpdate(sql1);
	      
	    } finally {
	      if (db != null) db.close();
	    }
	  
	}//delete semakan k2 checkbox
	
	
	//update Semakan K2
	public static void updateSemakanK2(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran, String jumlahbayaran, String deleteF) throws Exception
	  {
		
	    Db db = null;
	    
	    String sql = "";
	    String sqlBayaran = "";
	    String sqlP = "";
	    
	    try
	    {
	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	long id_semakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
	 	    
	 	    //id login
	 	    String id_login = (String)data.get("id_kemaskini");
	 	    
	 	    String tarikhTerimaRayuan = (String)data.get("tarikhTerimaRayuan");
	 	    String tarikh_rayuan = "to_date('" + tarikhTerimaRayuan + "','dd/MM/yyyy')";
	 	    
		   
		    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
					    
		    //insert data into tblsemakanhantar
		    sql = "INSERT INTO Tblsemakanhantar " +
				  "(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
				  "VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_login+"', sysdate) ";
		    
		    stmt.executeUpdate(sql);
		    
		    String id_update_bayaranF = (String)data.get("update_bayaranF");
		    String id_update_bayaranD = (String)data.get("update_bayaranD");

		    
		    if (idsemakan.equals("124")) 
			   {
				SQLRenderer r1 = new SQLRenderer();
				  r1.update("id_bayaran", id_update_bayaranF);
				  r1.add("no_resit",noresit);
				  r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
				  r1.add("id_kemaskini",id_login); 
				  r1.add("tarikh_kemaskini",r1.unquote("sysdate")); 
				sqlBayaran = r1.getSQLUpdate("tblppkbayaran");
				myLogger.info("sqlBayaran124 = "+sqlBayaran);
				stmt.executeUpdate(sqlBayaran);
				}
		   
		    if (idsemakan.equals("125")) 
			    {
		    	
		    	 SQLRenderer rB = new SQLRenderer();
				   rB.update("id_bayaran", id_update_bayaranD);
				   rB.add("no_resit",noresit);
				   rB.add("tarikh_bayaran",rB.unquote(tarikh_bayaran));
				   rB.add("jumlah_bayaran",jumlahbayaran);
				   rB.add("id_kemaskini",id_login); 
				   rB.add("tarikh_kemaskini",rB.unquote("sysdate")); 
				 sqlBayaran = rB.getSQLUpdate("tblppkbayaran");
				 myLogger.info("sqlBayaran125 = "+sqlBayaran);
				 stmt.executeUpdate(sqlBayaran);
				 
			    }
		  
		    //update tarikh rayuan
	    	SQLRenderer rP = new SQLRenderer();
	    	  rP.update("id_permohonan", idpermohonan);
	    	  rP.add("id_kemaskini",id_login); 
	    	  rP.add("tarikh_rayuan",rP.unquote(tarikh_rayuan)); 
	     	  rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
	     	sqlP = rP.getSQLUpdate("tblppkpermohonan");
	     	stmt.executeUpdate(sqlP);
		    
		    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateSemakanK2
	 
	
	//simpan Maklumat PP
	public static void simpanMaklumatPP(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    try
	    {
	    	
	      db = new Db();
	      conn = db.getConnection();
		  conn.setAutoCommit(false);
	      Statement stmt = db.getStatement();
	    	
	      //validation insert/update
		  int Xdefault = (Integer)data.get("Xdefault");
		  String id_peguamVAL = (String)data.get("id_peguam");	
	    	
		  long id_peguam = 0;
		  
	      if(id_peguamVAL!=""){
	    	  String idpg = (String)data.get("id_peguam");
	    	  id_peguam = Long.parseLong(idpg);
	      }else{
	    	  id_peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
	      }
		  
	      //generate other table id	
	     
	      long id_perayu = DB.getNextID("TBLPPKPERAYU_SEQ");
	      long id_rayuan = DB.getNextID("TBLPPKRAYUAN_SEQ");
	     
	      //id	
	      String id_perintah = (String)data.get("id_perintah");
	      String id_permohonan = (String)data.get("id_permohonan");
	      String id_pemohon = (String)data.get("id_pemohon");
	      String id_ob = (String)data.get("id_ob");
	      
	      
	      //data perayu
	      String noKPBaru = (String)data.get("txtNoKPBaru");
	      String noKPLama = (String)data.get("txtNoKPLama");
	      String noKPLain = (String)data.get("txtNoKPLain");
	      String jeniskp = (String)data.get("jeniskp");
	      String namaPerayu = (String)data.get("txtNamaPerayu");
	      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
	      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
	      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
	      String poskodPerayu = (String)data.get("txtPoskodPerayu");
	      String bandarPerayu = (String)data.get("txtBandarPerayu");
	      String negeriPerayu = (String)data.get("socNegeriPerayu");
	     
	      //data peguam
	      String namaFirma = (String)data.get("txtNamaFirma");
	      String noRujukan = (String)data.get("txtNoRujukan");
		  String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
		  String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
		  String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
		  String poskodPeguam = (String)data.get("txtPoskodPeguam");
		  String bandarPeguam = (String)data.get("txtBandarPeguam");
		  String noTelefon = (String)data.get("txtNoTelefon");
		  String noFaks = (String)data.get("txtNoFaks");
		  String email = (String)data.get("txtEmel");
		  String negeriPeguam = (String)data.get("socNegeriPeguam");
	    	
		  String tarikhrayuan = (String)data.get("tarikh_rayuan");
		  String tarikh_rayuan = "to_date('" + tarikhrayuan + "','dd/MM/yyyy')";
		  
	      //id login masuk
	      String id_masuk = (String)data.get("id_masuk");
	      
	      
	      //insert
	      if(Xdefault==2){
	      
	    	  //add data to Tblppkpeguam	      
	    	  SQLRenderer r = new SQLRenderer();
	    	  r.add("id_peguam", id_peguam);
	    	  r.add("nama_firma", namaFirma);
	    	  r.add("no_rujukan_firma", noRujukan);
	    	  r.add("alamat1", alamatPeguam1); 	
	    	  r.add("alamat2", alamatPeguam2);
	    	  r.add("alamat3", alamatPeguam3);
	    	  r.add("poskod", poskodPeguam);
	    	  r.add("id_bandar", bandarPeguam);
	    	  r.add("no_tel", noTelefon);
	    	  r.add("no_fax", noFaks);
	    	  r.add("emel", email);
	    	  r.add("id_negeri", negeriPeguam);
	    	  r.add("tarikh_masuk", r.unquote("sysdate"));
	    	  r.add("id_masuk",id_masuk);	      
	    	  sql = r.getSQLInsert("Tblppkpeguam");
	    	  myLogger.info("sql1 = "+sql);
	    	  stmt.executeUpdate(sql);
	        
	      }
	      
	      //update
	      else if(Xdefault==1){
	    	
	    	  
	    	  SQLRenderer r = new SQLRenderer();
	    	  r.update("id_peguam", id_peguam);
		      r.add("no_rujukan_firma", noRujukan);
		      r.add("alamat1", alamatPeguam1); 	
		      r.add("alamat2", alamatPeguam2);
		      r.add("alamat3", alamatPeguam3);
		      r.add("poskod", poskodPeguam);
		      r.add("id_bandar", bandarPeguam);
		      r.add("no_tel", noTelefon);
		      r.add("no_fax", noFaks);
		      r.add("emel", email);
		      r.add("id_negeri", negeriPeguam);
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));
		      r.add("id_kemaskini",id_masuk);	
		      sql = r.getSQLUpdate("Tblppkpeguam");
		      myLogger.info("sql2 = "+sql);
		      stmt.executeUpdate(sql);
	    	  
	      }
	      
	      if(Xdefault==2)
	      {
	    	  //add data to tblppkpeguampemohon with id pemohon
	    	  SQLRenderer rMST = new SQLRenderer();
	    	  rMST.add("id_peguam", id_peguam);	
	    	  rMST.add("id_pemohon", id_pemohon);	
	    	  rMST.add("id_masuk",id_masuk);
	    	  rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
	    	  sql1 = rMST.getSQLInsert("Tblppkpeguampemohon");
	    	  myLogger.info("sql1a = "+sql);
	    	  stmt.executeUpdate(sql1);
	      }
	      
	      //add data to tblppkrayuan
	      SQLRenderer rR = new SQLRenderer();
	      rR.add("id_rayuan", id_rayuan);
	      rR.add("id_permohonan", id_permohonan); 	
	      rR.add("id_perintah", id_perintah); 	
	      rR.add("tarikh_mohon", rR.unquote(tarikh_rayuan)); 
	      rR.add("id_masuk",id_masuk);
	      rR.add("tarikh_masuk", rR.unquote("sysdate"));	      
	      sql = rR.getSQLInsert("Tblppkrayuan");
	      myLogger.info("sql3 = "+sql);
	      stmt.executeUpdate(sql);
	      
	      //add data to tblppkperayu
	      SQLRenderer r1 = new SQLRenderer();
	      r1.add("id_perayu", id_perayu);
	      r1.add("id_rayuan", id_rayuan); 
	      r1.add("no_kp_baru", noKPBaru);
	      r1.add("no_kp_lama", noKPLama);
	      r1.add("no_kp_lain", noKPLain);
	      r1.add("jenis_kp",jeniskp);
	      r1.add("nama_perayu", namaPerayu);
	      r1.add("alamat_1", alamatPerayu1);
	      r1.add("alamat_2", alamatPerayu2);
	      r1.add("alamat_3", alamatPerayu3);
	      r1.add("poskod", poskodPerayu);
	      r1.add("id_bandar", bandarPerayu);
	      r1.add("id_negeri", negeriPerayu);	      
	      r1.add("id_masuk",id_masuk);
	      r1.add("tarikh_masuk", r1.unquote("sysdate"));	      
	      sql = r1.getSQLInsert("Tblppkperayu");
	      myLogger.info("sql4 = "+sql);
	      stmt.executeUpdate(sql);
	      
	      //add data to tblppkpeguamperayu
	      SQLRenderer rPe = new SQLRenderer();
	      rPe.add("id_peguam", id_peguam); 	
	      rPe.add("id_perayu", id_perayu); 
	      rPe.add("id_masuk",id_masuk);
	      rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
	      sql = rPe.getSQLInsert("Tblppkpeguamperayu");
	      myLogger.info("sql5 = "+sql);
	      stmt.executeUpdate(sql);

	      //[add perayu] = [update tblppkob]
	      SQLRenderer rOB = new SQLRenderer();
	      rOB.update("id_ob", id_ob);
	      rOB.add("id_perayu", id_perayu);
	      rOB.add("alamat1_surat", alamatPerayu1); 	
	      rOB.add("alamat2_surat", alamatPerayu2);
	      rOB.add("alamat3_surat", alamatPerayu3);
	      rOB.add("poskod_surat", poskodPerayu);
	      rOB.add("id_bandarsurat", bandarPerayu);
	      rOB.add("id_negerisurat", negeriPerayu);
	      rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
	      rOB.add("id_kemaskini",id_masuk);	      
	      sql = rOB.getSQLUpdate("Tblppkob");
	      myLogger.info("sql6 = "+sql);
	      stmt.executeUpdate(sql);
	      
	      
	      conn.commit();	
	      
	      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
	    }
	    finally {
	      if (db != null) db.close();
	    }
	   
	  }//close simpan Maklumat PP
	
	//data maklumat rayuan
	//public static void setDataMaklumat(int id_permohonan, String id_pemohon)throws Exception {
	public static void setDataMaklumat(String id_permohonan,String id_perintah)throws Exception {	
	    Db db = null;
	    listDataMaklumat.clear();
	    String sql = "";
	    
	    try {
	    	
	      db = new Db();
	      Statement stmt = db.getStatement();

	      sql =  " SELECT x.id_permohonan, pe.bandar, pe.poskod, pe.id_negeri, pe.nama_perayu, ";
	      sql += " pe.no_kp_baru, pe.alamat_1, pe.alamat_2, pe.alamat_3,pe.id_perayu, ra.id_rayuan, ra.tarikh_mohon, ra.nota_bicara, ra.perkara_rayu, ";
	      sql += " ra.id_keputusanpegawai,ra.catatan_pegawai, ra.id_keputusanmahkamah, ra.catatan, x.id_status, ";
	      sql += " ra.alasan_rayuan, pe.no_kp_lama, pe.no_kp_lain, pe.id_bandar, ra.asas_keputusan, ra.Borang_A, ra.Borang_P, ra.Borang_DDA, ra.SA, ra.Jumlah_Bayaran, ra.Lampiran1, ra.FLampiran1, ";
	      sql += " ra.QBorang1A, ra.QBorang1P, QBorang1DDA, Q1SA, ";
	      sql += " ra.FLampiran2, ra.Lampiran2, ra.F2Lampiran1, ra.F2Lampiran2, ra.QLampiran2, ra.QLampiran1, ";
	      sql += " ra.FLampiran2, ra.Lampiran2, ra.F2Lampiran2, ra.QLampiran2, ";
	      sql += " ra.FLampiran3, ra.Lampiran3, ra.F2Lampiran3, ra.QLampiran3, ";
	      sql += " ra.FLampiran4, ra.Lampiran4, ra.F2Lampiran4, ra.QLampiran4, ";
	      sql += " ra.FLampiran5, ra.Lampiran5, ra.F2Lampiran5, ra.QLampiran5, ";
	      sql += " ra.FLampiran6, ra.Lampiran6, ra.F2Lampiran6, ra.QLampiran6, ";
	      sql += " ra.FLampiran7, ra.Lampiran7, ra.F2Lampiran7, ra.QLampiran7, ";
	      sql += " ra.FLampiran8, ra.Lampiran8, ra.F2Lampiran8, ra.QLampiran8, ";
	      sql += " ra.nota_bicara, ra.perkara_rayu_memorandum, ra.alasan_rayuan_memorandum ";
	      sql += " FROM Tblppkrayuan ra, Tblppkperayu pe, Tblppkpermohonan x, Tblppkperintah ph ";
	      sql += " WHERE pe.id_rayuan = ra.id_rayuan ";
	      sql += " AND ra.id_perintah = ph.id_perintah  ";
	      sql += " AND ra.id_permohonan = x.id_permohonan ";
	      sql += " AND x.id_permohonan = '"+id_permohonan+"'";
	      sql += " AND ra.id_perintah = '"+id_perintah+"'";
	      
	     /* YANG NI ORIGINAL - SALNIZAM
	      sql =  " SELECT distinct x.id_permohonan, pe.bandar, pe.poskod, pe.id_negeri, pe.nama_perayu, ";
	      sql += " pe.no_kp_baru, pe.alamat_1, pe.alamat_2, pe.alamat_3,pe.id_perayu, ra.id_rayuan, ra.tarikh_mohon, ra.nota_bicara, ra.perkara_rayu, ";
	      sql += " ra.id_keputusanpegawai,ra.catatan_pegawai, ra.id_keputusanmahkamah, ra.catatan, x.id_status, ";
	      sql += " ra.alasan_rayuan, pe.no_kp_lama, pe.no_kp_lain, pe.id_bandar, ra.asas_keputusan, ra.nota_bicara, ra.perkara_rayu_memorandum ";
	      sql += " FROM Tblppkrayuan ra, Tblppkperayu pe, Tblppkpermohonan x, Tblppkperintah ph ";
	      sql += " WHERE pe.id_rayuan = ra.id_rayuan ";
	      sql += " AND ra.id_perintah = ph.id_perintah  ";
	      sql += " AND ra.id_permohonan = x.id_permohonan ";
	      sql += " AND x.id_permohonan = '"+id_permohonan+"'";
	      sql += " AND ra.id_perintah = '"+id_perintah+"'";
	      */
	      myLogger.info("sql = "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	 
	      while (rs.next()) {
	    	  h = new Hashtable();
				//h.put("id_pemohon", rs.getString("id_pemohon"));
	    	  	h.put("id_perayu", rs.getString("id_perayu")==null?"":rs.getString("id_perayu"));
	    	  	h.put("id_rayuan", rs.getString("id_rayuan")==null?"":rs.getString("id_rayuan"));
	    	  	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
	    	  	h.put("catatan_pegawai", rs.getString("catatan_pegawai")==null?"":rs.getString("catatan_pegawai"));
				h.put("id_keputusanpegawai", rs.getString("id_keputusanpegawai")==null?"":rs.getString("id_keputusanpegawai"));
				h.put("nama_perayu", rs.getString("nama_perayu")==null?"":rs.getString("nama_perayu"));
				h.put("no_kp_baru1", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(0,6));
			    h.put("no_kp_baru2", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(6,8));
			    h.put("no_kp_baru3", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(8,12));
			    h.put("alamat_perayu1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
			    h.put("alamat_perayu2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
			    h.put("alamat_perayu3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
			    h.put("bandar_perayu", rs.getString(2)==null?"":rs.getString(2));
			    h.put("id_bandar_perayu", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
			    h.put("poskod_perayu", rs.getString(3)==null?"":rs.getString(3));
			    if(rs.getString(4) == null || rs.getString(4) ==""){
		    		h.put("negeri_perayu","");
		    	}else{
		    		h.put("negeri_perayu",rs.getString(4));
		    	}
			    

				h.put("perkara_rayu", rs.getString("perkara_rayu")==null?"":rs.getString("perkara_rayu"));
				h.put("alasan_rayuan", rs.getString("alasan_rayuan")==null?"":rs.getString("alasan_rayuan"));
				h.put("nota_bicara", rs.getString("nota_bicara")==null?"":rs.getString("nota_bicara"));
				h.put("tarikh_rayuan", rs.getDate("tarikh_mohon")==null?"":Format.format(rs.getDate("tarikh_mohon")));
				h.put("id_keputusanmahkamah", rs.getString("id_keputusanmahkamah")==null?"":rs.getString("id_keputusanmahkamah"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
				h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
				h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
				h.put("asas_keputusan", rs.getString("asas_keputusan")==null?"":rs.getString("asas_keputusan"));
				h.put("nota_bicara", rs.getString("nota_bicara")==null?"":rs.getString("nota_bicara"));
				h.put("perkara_rayu_memorandum", rs.getString("perkara_rayu_memorandum")==null?"":rs.getString("perkara_rayu_memorandum"));
				h.put("alasan_rayuan_memorandum", rs.getString("alasan_rayuan_memorandum")==null?"":rs.getString("alasan_rayuan_memorandum"));
				String strBorang_A = rs.getString("Borang_A")==null?"0":rs.getString("Borang_A");
				String strBorang_P = rs.getString("Borang_P")==null?"0":rs.getString("Borang_P");
				String strBorang_DDA = rs.getString("Borang_DDA")==null?"0":rs.getString("Borang_DDA");
				String strBorang_SA = rs.getString("SA")==null?"0":rs.getString("SA");
				
				String strqBorang1A = rs.getString("QBorang1A")==null?"0":rs.getString("QBorang1A");
				String strqBorang1P = rs.getString("QBorang1P")==null?"0":rs.getString("QBorang1P");
				String strqBorang1DDA = rs.getString("QBorang1DDA")==null?"0":rs.getString("QBorang1DDA");
				String strqBorang1SA = rs.getString("Q1SA")==null?"0":rs.getString("Q1SA");
				
				String strFLampiran1 = rs.getString("FLampiran1")==null?"0":rs.getString("FLampiran1");
				String strF2Lampiran1 = rs.getString("F2Lampiran1")==null?"0":rs.getString("F2Lampiran1");
				String strQLampiran1 = rs.getString("QLampiran1")==null?"0":rs.getString("QLampiran1");
				String strFLampiran2 = rs.getString("FLampiran2")==null?"0":rs.getString("FLampiran2");
				String strF2Lampiran2 = rs.getString("F2Lampiran2")==null?"0":rs.getString("F2Lampiran2");
				String strQLampiran2 = rs.getString("QLampiran2")==null?"0":rs.getString("QLampiran2");
				
				String strFLampiran3 = rs.getString("FLampiran3")==null?"0":rs.getString("FLampiran3");
				String strF2Lampiran3 = rs.getString("F2Lampiran3")==null?"0":rs.getString("F2Lampiran3");
				String strQLampiran3 = rs.getString("QLampiran3")==null?"0":rs.getString("QLampiran3");
				
				String strFLampiran4 = rs.getString("FLampiran4")==null?"0":rs.getString("FLampiran4");
				String strF2Lampiran4 = rs.getString("F2Lampiran4")==null?"0":rs.getString("F2Lampiran4");
				String strQLampiran4 = rs.getString("QLampiran4")==null?"0":rs.getString("QLampiran4");
				
				String strFLampiran5 = rs.getString("FLampiran5")==null?"0":rs.getString("FLampiran5");
				String strF2Lampiran5 = rs.getString("F2Lampiran5")==null?"0":rs.getString("F2Lampiran5");
				String strQLampiran5 = rs.getString("QLampiran5")==null?"0":rs.getString("QLampiran5");
				
				String strFLampiran6 = rs.getString("FLampiran6")==null?"0":rs.getString("FLampiran6");
				String strF2Lampiran6 = rs.getString("F2Lampiran6")==null?"0":rs.getString("F2Lampiran6");
				String strQLampiran6 = rs.getString("QLampiran6")==null?"0":rs.getString("QLampiran6");
				
				String strFLampiran7 = rs.getString("FLampiran7")==null?"0":rs.getString("FLampiran7");
				String strF2Lampiran7 = rs.getString("F2Lampiran7")==null?"0":rs.getString("F2Lampiran7");
				String strQLampiran7 = rs.getString("QLampiran7")==null?"0":rs.getString("QLampiran7");
				
				String strFLampiran8 = rs.getString("FLampiran8")==null?"0":rs.getString("FLampiran8");
				String strF2Lampiran8 = rs.getString("F2Lampiran8")==null?"0":rs.getString("F2Lampiran8");
				String strQLampiran8 = rs.getString("QLampiran8")==null?"0":rs.getString("QLampiran8");
				
				h.put("Lampiran1", rs.getString("Lampiran1")==null?"":rs.getString("Lampiran1"));
				h.put("Lampiran2", rs.getString("Lampiran2")==null?"":rs.getString("Lampiran2"));
				h.put("Lampiran3", rs.getString("Lampiran3")==null?"":rs.getString("Lampiran3"));
				h.put("Lampiran4", rs.getString("Lampiran4")==null?"":rs.getString("Lampiran4"));
				h.put("Lampiran5", rs.getString("Lampiran5")==null?"":rs.getString("Lampiran5"));
				h.put("Lampiran6", rs.getString("Lampiran6")==null?"":rs.getString("Lampiran6"));
				h.put("Lampiran7", rs.getString("Lampiran7")==null?"":rs.getString("Lampiran7"));
				h.put("Lampiran8", rs.getString("Lampiran8")==null?"":rs.getString("Lampiran8"));
				String strJUMLAH_BAYARAN = rs.getString("JUMLAH_BAYARAN")==null?"":rs.getString("JUMLAH_BAYARAN");
				
				//h.put("Borang_A", rs.getString("Borang_A")==null?"":rs.getString("Borang_A"));
				int Borang_A;
				if (strBorang_A != "")
				{
					Borang_A = Integer.parseInt(strBorang_A);
				}
				else{
					Borang_A = 0;
					}
				
				int Borang_P;
				if (strBorang_P != "")
				{
					Borang_P = Integer.parseInt(strBorang_P);
				}
				else{
					Borang_P = 0;
					}
				
				int Borang_DDA;
				if (strBorang_DDA != "")
				{
					Borang_DDA = Integer.parseInt(strBorang_DDA);
				}
				else{
					Borang_DDA = 0;
					}

				int Borang_SA = Integer.parseInt(strBorang_SA);
				
				int qBorang1SA = Integer.parseInt(strqBorang1SA);
				int qBorang1DDA = Integer.parseInt(strqBorang1DDA);
				int qBorang1A = Integer.parseInt(strqBorang1A);
				int qBorang1P = Integer.parseInt(strqBorang1P);
				
				int FLampiran1 = Integer.parseInt(strFLampiran1);
				int F2Lampiran1 = Integer.parseInt(strF2Lampiran1);
				int QLampiran1 = Integer.parseInt(strQLampiran1);
				int FLampiran2 = Integer.parseInt(strFLampiran2);
				int F2Lampiran2 = Integer.parseInt(strF2Lampiran2);
				int QLampiran2 = Integer.parseInt(strQLampiran2);
				
				int FLampiran3 = Integer.parseInt(strFLampiran3);
				int F2Lampiran3 = Integer.parseInt(strF2Lampiran3);
				int QLampiran3 = Integer.parseInt(strQLampiran3);
				
				int FLampiran4 = Integer.parseInt(strFLampiran4);
				int F2Lampiran4 = Integer.parseInt(strF2Lampiran4);
				int QLampiran4 = Integer.parseInt(strQLampiran4);
				
				int FLampiran5 = Integer.parseInt(strFLampiran5);
				int F2Lampiran5 = Integer.parseInt(strF2Lampiran5);
				int QLampiran5 = Integer.parseInt(strQLampiran5);
				
				int FLampiran6 = Integer.parseInt(strFLampiran6);
				int F2Lampiran6 = Integer.parseInt(strF2Lampiran6);
				int QLampiran6 = Integer.parseInt(strQLampiran6);
				
				int FLampiran7 = Integer.parseInt(strFLampiran7);
				int F2Lampiran7 = Integer.parseInt(strF2Lampiran7);
				int QLampiran7 = Integer.parseInt(strQLampiran7);
				
				int FLampiran8 = Integer.parseInt(strFLampiran8);
				int F2Lampiran8 = Integer.parseInt(strF2Lampiran8);
				int QLampiran8 = Integer.parseInt(strQLampiran8);
				
				int JUMLAH_BAYARAN;
				if (strJUMLAH_BAYARAN != "")
					{
					JUMLAH_BAYARAN = Integer.parseInt(strJUMLAH_BAYARAN);
					}
				else{
					JUMLAH_BAYARAN = 0;
					}
				h.put("Borang_A", Borang_A);
				h.put("Borang_P", Borang_P);
				h.put("Borang_DDA", Borang_DDA);
				h.put("Borang_SA", Borang_SA);
				
				h.put("qBorang1A", qBorang1A);
				h.put("qBorang1P", qBorang1P);
				h.put("qBorang1DDA", qBorang1DDA);
				h.put("qBorang1SA", qBorang1SA);
				
				h.put("FLampiran1", FLampiran1);
				h.put("F2Lampiran1", F2Lampiran1);
				h.put("QLampiran1", QLampiran1);
				h.put("FLampiran2", FLampiran2);
				h.put("F2Lampiran2", F2Lampiran2);
				h.put("QLampiran2", QLampiran2);
				
				h.put("FLampiran3", FLampiran3);
				h.put("F2Lampiran3", F2Lampiran3);
				h.put("QLampiran3", QLampiran3);
				
				h.put("FLampiran4", FLampiran4);
				h.put("F2Lampiran4", F2Lampiran4);
				h.put("QLampiran4", QLampiran4);
				
				h.put("FLampiran5", FLampiran5);
				h.put("F2Lampiran5", F2Lampiran5);
				h.put("QLampiran5", QLampiran5);
				
				h.put("FLampiran6", FLampiran6);
				h.put("F2Lampiran6", F2Lampiran6);
				h.put("QLampiran6", QLampiran6);
				
				h.put("FLampiran7", FLampiran7);
				h.put("F2Lampiran7", F2Lampiran7);
				h.put("QLampiran7", QLampiran7);
				
				h.put("FLampiran8", FLampiran8);
				h.put("F2Lampiran8", F2Lampiran8);
				h.put("QLampiran8", QLampiran8);
				h.put("JUMLAH_BAYARAN", JUMLAH_BAYARAN);
				
				listDataMaklumat.addElement(h);
	      }
	      
	    } finally {
	      if (db != null) db.close();
	    }    
	  }//close data maklumat rayuan
	
	
	//update Maklumat PP
	public static void updatePerayu(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {
	    	  db = new Db();
	    	  Statement stmt = db.getStatement();
	    	  
	    	  
	    	  //id	
		      //String id_permohonan = (String)data.get("id_permohonan");
		      String id_pemohon = (String)data.get("id_pemohon");
		      String id_perayu = (String)data.get("id_perayu");
		      String id_peguam = (String)data.get("id_peguam");
		      
		      //data perayu
		      String noKPBaru = (String)data.get("txtNoKPBaru");
		      String noKPLama = (String)data.get("txtNoKPLama");
		      String noKPLain = (String)data.get("txtNoKPLain");
		      String namaPerayu = (String)data.get("txtNamaPerayu");
		      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
		      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
		      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
		      String poskodPerayu = (String)data.get("txtPoskodPerayu");
		      String bandarPerayu = (String)data.get("txtBandarPerayu");
		      String negeriPerayu = (String)data.get("socNegeriPerayu");
		     
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		       
		      //update table perayu    
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_perayu", id_perayu);
		      r.add("no_kp_baru", noKPBaru);
		      r.add("no_kp_lama", noKPLama);
		      r.add("no_kp_lain", noKPLain);
		      r.add("nama_perayu", namaPerayu);
		      r.add("alamat_1", alamatPerayu1);
		      r.add("alamat_2", alamatPerayu2);
		      r.add("alamat_3", alamatPerayu3);
		      r.add("poskod", poskodPerayu);
		      r.add("id_bandar", bandarPerayu);
		      r.add("id_negeri", negeriPerayu);	      
		      r.add("id_kemaskini",id_kemaskini);
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
		      sql = r.getSQLUpdate("Tblppkperayu");
		      stmt.executeUpdate(sql);
	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close update perayu
	
	
	//update table OB
	public static void updatetableOB(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {	    	
	    	  db = new Db();
	    	  Statement stmt = db.getStatement();
	    	  
	    	  //id	
		      String id_perayu = (String)data.get("id_perayu");
		    
		      //data perayu
		      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
		      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
		      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
		      String poskodPerayu = (String)data.get("txtPoskodPerayu");
		      String bandarPerayu = (String)data.get("txtBandarPerayu");
		      String negeriPerayu = (String)data.get("socNegeriPerayu");
		
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		 
		      //[add perayu] = [update tblppkob]
		      SQLRenderer rOB = new SQLRenderer();
	      		rOB.update("id_perayu", id_perayu);
	      		rOB.add("alamat1_surat", alamatPerayu1); 	
	      		rOB.add("alamat2_surat", alamatPerayu2);
	      		rOB.add("alamat3_surat", alamatPerayu3);
	      		rOB.add("poskod_surat", poskodPerayu);
	      		rOB.add("id_bandarsurat", bandarPerayu);
	      		rOB.add("id_negerisurat", negeriPerayu);
	      		rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
	      		rOB.add("id_kemaskini",id_kemaskini);	      
	      		sql = rOB.getSQLUpdate("Tblppkob");
	      	  stmt.executeUpdate(sql);
	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close update table OB
	
	
	//insert Maklumat ob
	public static void inserttableOB(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    String sqlx = "";
	    try
	    {	    	
	    	  db = new Db();
	    	  Statement stmt = db.getStatement();
	    	 
	    	  //id	
		      String id_perayu = (String)data.get("id_perayu");
		      String id_ob = (String)data.get("id_ob");
		      
		      //data perayu
		      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
		      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
		      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
		      String poskodPerayu = (String)data.get("txtPoskodPerayu");
		      String bandarPerayu = (String)data.get("txtBandarPerayu");
		      String negeriPerayu = (String)data.get("socNegeriPerayu");
		
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		 
		      	//-- buang id perayu dlm selection ob
		      	//[add perayu] = [update tblppkob]
		      	SQLRenderer rOB = new SQLRenderer();
	      		rOB.update("id_perayu", id_perayu);
	      		rOB.add("id_perayu", ""); 	
	      		rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
	      		rOB.add("id_kemaskini",id_kemaskini);	      
	      		sql = rOB.getSQLUpdate("Tblppkob");
	      	    stmt.executeUpdate(sql);
	      
	      	    //-- tambah id perayu dlm selection ob
	      	    SQLRenderer rOBx = new SQLRenderer();
	      	  	rOBx.update("id_ob", id_ob);
	      	  	rOBx.add("alamat1_surat", alamatPerayu1); 	
	      		rOBx.add("alamat2_surat", alamatPerayu2);
	      		rOBx.add("alamat3_surat", alamatPerayu3);
	      		rOBx.add("poskod_surat", poskodPerayu);
	      		rOBx.add("id_bandarsurat", bandarPerayu);
	      		rOBx.add("id_negerisurat", negeriPerayu);
	      	  	rOBx.add("id_perayu", id_perayu); 	
	      	  	rOBx.add("tarikh_kemaskini", rOBx.unquote("sysdate"));
	      	  	rOBx.add("id_kemaskini",id_kemaskini);	      
	      	  	sqlx = rOBx.getSQLUpdate("Tblppkob");
	      	    stmt.executeUpdate(sqlx);
	      	  
	      	  
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close insert table OB
	
	
	
	//simpan Data Rayuan [update]
	public static void simpanDataRayuan(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	
	    	  db = new Db();
		      Statement stmt = db.getStatement();
		      
	    	  //id	
		      String id_rayuan = (String)data.get("id_rayuan");
		  
		      //data perayu
		      //String tarikh_rayuan = (String)data.get("tarikh_rayuan");
		      String perkara_rayu = (String)data.get("perkara_rayu");
		      //String alasan_rayuan = (String)data.get("alasan_rayuan");
		      
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		 
		      //String TR = "to_date('" + tarikh_rayuan + "','dd/MM/yyyy')";
				
		  //update table perayu    
	      SQLRenderer r = new SQLRenderer();
	      r.update("id_rayuan", id_rayuan);
	      r.add("perkara_rayu", perkara_rayu);
	      //r.add("alasan_rayuan", alasan_rayuan);
	      //r.add("tarikh_mohon", r.unquote(TR));	   
	      r.add("id_kemaskini",id_kemaskini);
	      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
	      sql = r.getSQLUpdate("Tblppkrayuan");
	      stmt.executeUpdate(sql);
	     	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpan Data Rayuan [update]
	
	
	//simpan Serahan K3
	public static void simpanSerahanK3(HttpSession session,Hashtable data) throws Exception{
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    String sql5 = "";
	    String sql6 = "";
	    String sqlMST = "";
	    String sqlNO = "";
	    
	    try
	    {
	    	  db = new Db();
	    	  conn = db.getConnection();
			  conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
	    	
		      
	    	  //id	
		      String _id_rayuan = (String)data.get("id_rayuan");
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_fail = (String)data.get("id_fail");
		      String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
		      
		      //-- 02122009
		      String idperbicaraan = (String)data.get("idperbicaraan");
		      String jenis_status = (String)data.get("jenis_status");
		      String jenis_serah = (String)data.get("jenis_serah");
		      String catatan = (String)data.get("catatan");
		      String id_penghantar = (String)data.get("id_penghantar");
		      String daftar_pos = (String)data.get("no_surat_daftar");
		      String nama_penghantar = (String)data.get("nama_penghantar");
		      //-- 02122009
		      
		      //11122009
		      String _tarikhS = (String)data.get("tarikhS");
		      String tarikhS = "to_date('" + _tarikhS + "','dd/MM/yyyy')";
		      
		      //data perayu
		      String tarikh_serahan = (String)data.get("txdTarikhSerahan");
		      String nama_penerima = (String)data.get("txtNamaPenerima");
		      String alamatSerah1 = (String)data.get("alamatSerah1");
		      String alamatSerah2 = (String)data.get("alamatSerah2");
		      String alamatSerah3 = (String)data.get("alamatSerah3");
		      String poskod = (String)data.get("txtPoskodSerah");
		      String bandar = (String)data.get("txtBandarSerah");
		      String negeri = (String)data.get("idnegeri");
		      
		      String bayaranRekod = (String)data.get("bayaranRekod");
		      
		      //id login kemaskini
		      String id_masuk = (String)data.get("id_masuk");
		 
		      String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
				
		      String flag_serahan = "1";   
		      
	      
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_rayuan", _id_rayuan);
		      r.add("nama", nama_penerima);
		      r.add("alamat1", alamatSerah1);
		      r.add("alamat2", alamatSerah2);
		      r.add("alamat3", alamatSerah3);
		      r.add("poskod", poskod);
		      r.add("id_bandar", bandar);
		      r.add("id_negeri", negeri);
		      r.add("flag_serahan", flag_serahan);
		      r.add("tarikh_borangk3", r.unquote(TS));	   
		      r.add("id_masuk",id_masuk);
		      r.add("tarikh_masuk", r.unquote("sysdate"));	      
		      sql = r.getSQLInsert("Tblppkserahanrayuan");
		      stmt.executeUpdate(sql);
	     	      
		      SQLRenderer r1 = new SQLRenderer();
		      r1.add("id_permohonan", id_permohonan);
		      r1.add("id_jenisbayaran",18);
		      r1.add("jumlah_bayaran",bayaranRekod);
		      r1.add("id_masuk",id_masuk); 
		      r1.add("tarikh_masuk",r1.unquote("sysdate")); 
		      sql = r1.getSQLInsert("tblppkbayaran");
		      myLogger.info("sql :: " +sql);
		      stmt.executeUpdate(sql);
		      
		      
		      //change status 64 --> 163[mnuggu pnerimaan brg k1]
		      int status_k1 = 163;
		      
		      /*
		      
		      SQLRenderer rP = new SQLRenderer();
		      rP.update("id_permohonan", id_permohonan);
		      rP.add("id_status", status_k1);
		      rP.add("id_kemaskini",id_masuk); 
		      rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
		      sql = rP.getSQLUpdate("tblppkpermohonan");
		      stmt.executeUpdate(sql);
	      
		      SQLRenderer r5 = new SQLRenderer();
		      r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
		      r5.add("ID_PERMOHONAN",id_permohonan);
		      r5.add("ID_FAIL",id_fail);
		      r5.add("ID_SUBURUSANSTATUS",466);
		      r5.add("AKTIF",1);
		      r5.add("ID_MASUK",id_masuk);
		      r5.add("ID_KEMASKINI",id_masuk);
		      r5.add("TARIKH_MASUK",r5.unquote("sysdate"));	
		      r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
		      sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
		      stmt.executeUpdate(sql5);		
	     	
		      //update n add tblrujsuburusanstatus
		      SQLRenderer r6 = new SQLRenderer();
		      r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
		      r6.add("AKTIF",0);
		      r6.add("ID_KEMASKINI",id_masuk);
		      r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
		      sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
		      stmt.executeUpdate(sql6);
		      */
		      
		      
				
		      
		      //-- 02122009
		      long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ"); 
		      
		      SQLRenderer rMST = new SQLRenderer();
		      rMST.add("id_notisobmst",id_notisobmst);
		      rMST.add("status_serah", jenis_status);
		      rMST.add("jenis_serah", jenis_serah);
		      rMST.add("catatan", catatan);
		      rMST.add("nama_penghantar_notis", nama_penghantar);
		      rMST.add("id_penghantarnotis", id_penghantar);
		      rMST.add("no_surat_daftar", daftar_pos);
		      rMST.add("id_masuk",id_masuk);
		      rMST.add("tarikh_masuk",rMST.unquote("sysdate"));
		      
		      //11122009
		      rMST.add("tarikh_serahan",rMST.unquote(tarikhS));
		      
		      sqlMST = rMST.getSQLInsert("TBLPPKNOTISOBMST");
		      myLogger.info("sqlMST :: " +sqlMST);
		      stmt.executeUpdate(sqlMST);
		      
		      // + flag jenis notis [1 = rayuan]
		      String flag = "1";
		      
		      SQLRenderer rNO = new SQLRenderer();
		      rNO.add("id_notisobmst", id_notisobmst);
		      rNO.add("id_perbicaraan", idperbicaraan);
		      rNO.add("flag_jenis_notis", flag);
		      rNO.add("id_masuk",id_masuk);
		      rNO.add("tarikh_masuk",rNO.unquote("sysdate"));
		      sqlNO = rNO.getSQLInsert("TBLPPKNOTISPERBICARAAN");
          		myLogger.info("sqlNO :: " +sqlNO);
		      stmt.executeUpdate(sqlNO); 
		      
		      //-- 02122009
		      
		      conn.commit();
		      
		      FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,status_k1+"","466",id_fail);
		      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
	    	
	    }
	    finally {
	      if (db != null) db.close();
	    }
	   
	  }//close simpan Serahan K3
	
	//Data Maklumat Serahan
	public static void setDataMaklumatSerahan(String id_rayuan)throws Exception {
		
	    Db db = null;
	    listDataMaklumatSerahan.clear();
	    String sql = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		//SYARAT
	    		sql =  "SELECT distinct ry.id_serahanrayuan, ry.id_rayuan, ry.tarikh_borangk3, ry.nama, ry.alamat1, ry.alamat2, ry.alamat3, ";
	    		sql += "ry.poskod, ry.bandar, ry.id_negeri, ry.id_bandar "; 
	    		sql += "FROM Tblppkserahanrayuan ry ";
	    		sql += "WHERE ry.id_rayuan = '"+id_rayuan+"'";
	    		sql += "AND ry.flag_serahan = 1 ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	 
	    		while (rs.next()) {
	    			
	    			h = new Hashtable();
	    			h.put("id_rayuan", rs.getString("id_rayuan")==null?"":rs.getString("id_rayuan"));
	    			h.put("id_serahanrayuan", rs.getString("id_serahanrayuan")==null?"":rs.getString("id_serahanrayuan"));
	    			h.put("nama_penerima", rs.getString("nama")==null?"":rs.getString("nama"));
	    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
	    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
	    			h.put("tarikh_serahan", rs.getDate("tarikh_borangk3")==null?"":Format.format(rs.getDate("tarikh_borangk3")));
	    			listDataMaklumatSerahan.addElement(h);
	    		}
	     
	    } finally {
	      if (db != null) db.close();
	    }    
	  }//close Data Maklumat Serahan
	
	
	//update Serahan
	public static void updateSerahan(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {
	    	  db = new Db();
		      Statement stmt = db.getStatement();
		      
		      
	    	  //id	
		      String id_rayuan = (String)data.get("id_rayuan");
		      String id_serahanrayuan = (String)data.get("id_serahanrayuan");
		      
		      
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_bayaran = (String)data.get("id_bayaran");
		      String bayaranR = (String)data.get("bayaranR");
		      
		      //data 
		      String tarikh_serahan = (String)data.get("tarikh_serahan");
		      String nama_penerima = (String)data.get("nama_penerima");
		      String alamatSerah1 = (String)data.get("alamatSerah1");
		      String alamatSerah2 = (String)data.get("alamatSerah2");
		      String alamatSerah3 = (String)data.get("alamatSerah3");
		      String poskod = (String)data.get("txtPoskodSerah");
		      String bandar = (String)data.get("txtBandarSerah");
		      String idnegeri = (String)data.get("idnegeri");
		      
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		 
		      String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
				
		      
		      //update table serahanrayuan    
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_serahanrayuan", id_serahanrayuan);
		      r.add("nama", nama_penerima);
		      r.add("tarikh_borangk3", r.unquote(TS));	 
		      r.add("alamat1", alamatSerah1);
		      r.add("alamat2", alamatSerah2);
		      r.add("alamat3", alamatSerah3);
		      r.add("poskod", poskod);
		      r.add("id_bandar", bandar);
		      r.add("id_negeri", idnegeri);
		      r.add("id_kemaskini",id_kemaskini);
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
		      sql = r.getSQLUpdate("Tblppkserahanrayuan");
		      stmt.executeUpdate(sql);
	     	      
		      SQLRenderer r1 = new SQLRenderer();
		      r1.update("id_bayaran", id_bayaran);
		      r1.add("id_permohonan", id_permohonan);
		      r1.add("jumlah_bayaran", bayaranR);
		      r1.add("id_kemaskini",id_kemaskini); 
		      r1.add("tarikh_kemaskini",r1.unquote("sysdate")); 
		      sql = r1.getSQLUpdate("tblppkbayaran");
		      stmt.executeUpdate(sql);
		      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close update serahan
	
	
	//getOnchangeListOB
	 public static Vector getOnchangeListOB(String id_ob)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  "SELECT DISTINCT id_ob, nama_ob, no_kp_baru, jenis_kp, no_kp_lama, no_kp_lain, alamat1_surat, alamat2_surat, alamat3_surat, poskod_surat, bandar_surat, id_negerisurat, id_bandarsurat ";
		    		sql += "FROM tblppkob ";
		    		sql += "WHERE id_ob = '"+id_ob+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		    		
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		      
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
		    			h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
		    			h.put("alamat1", rs.getString("alamat1_surat")==null?"":rs.getString("alamat1_surat"));
		    			h.put("alamat2", rs.getString("alamat2_surat")==null?"":rs.getString("alamat2_surat"));
		    			h.put("alamat3", rs.getString("alamat3_surat")==null?"":rs.getString("alamat3_surat"));
		    			h.put("id_negeri", rs.getString("id_negerisurat")==null?"":rs.getString("id_negerisurat"));
		    			h.put("poskod", rs.getString("poskod_surat")==null?"":rs.getString("poskod_surat"));
		    			h.put("bandar", rs.getString("bandar_surat")==null?"":rs.getString("bandar_surat"));
		    			h.put("no_kp_baru1", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(0,6));
		    			h.put("no_kp_baru2", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(6,8));
		    			h.put("no_kp_baru3", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru").substring(8,12));
		    			h.put("no_kp_lama", rs.getString("no_kp_lama")==null?"":rs.getString("no_kp_lama"));
		    			h.put("no_kp_lain", rs.getString("no_kp_lain")==null?"":rs.getString("no_kp_lain"));
		    			h.put("no_kp_baru", rs.getString("no_kp_baru")==null?"":rs.getString("no_kp_baru"));
		    			h.put("jenis_kp", rs.getString("jenis_kp")==null?"":rs.getString("jenis_kp"));
		    			h.put("id_bandar", rs.getString("id_bandarsurat")==null?"":rs.getString("id_bandarsurat"));
		    			list.addElement(h);
		      }
		      return list;
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get getOnchangeListOB
	
	 
	//getListFirma
	 public static Vector getListFirma(String id_perayu)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT DISTINCT pg.id_peguam, p.id_perayu, pg.nama_firma ";
		    		sql += "FROM Tblppkperayu p, Tblppkpeguamperayu pp, Tblppkpeguam pg ";
		    		sql += "WHERE pp.id_perayu = p.id_perayu AND pp.id_peguam = pg.id_peguam ";
		    		sql += "AND p.id_perayu = '"+id_perayu+"'";
		    		sql += " ORDER BY pg.nama_firma asc";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		    		
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
		      
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil",bil);
		    			h.put("id_peguam", rs.getString("id_peguam")==null?"":rs.getString("id_peguam"));
		    			h.put("id_perayu", rs.getString("id_perayu")==null?"":rs.getString("id_perayu"));
		    			h.put("nama_firma", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
		    	 
		    			list.addElement(h);
		    			bil++;
		      }
		    		
		      return list;
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  }//getListFirma
	 
	 
	//data peguam latest
		public static void setLatestPeguam(String id_perayu)throws Exception {
			
		    Db db = null;
		    listLatestPeguam.clear();
		    
		    String sql = "";
		    String sql2 = "";
		    String id_peguam = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT MAX(pg.id_peguam)as id_peguam FROM Tblppkperayu p, Tblppkpeguamperayu pp, Tblppkpeguam pg ";
		    		sql += "WHERE pp.id_perayu = p.id_perayu AND pp.id_peguam = pg.id_peguam ";
		    		sql += "AND p.id_perayu = '"+id_perayu+"'";
		      
		    		ResultSet rs2 = stmt.executeQuery(sql);
		    		while (rs2.next()) {
		    			id_peguam = rs2.getString("id_peguam");
		    		}
		         
		    		
		    		//get latest data peguam by max(id peguam)
		    		sql2 = "SELECT DISTINCT pg.id_peguam, p.id_perayu, pg.nama_firma, pg.alamat1, pg.alamat2, pg.alamat3, ";
		    		sql2 += "pg.bandar, pg.poskod, pg.id_negeri, pg.no_rujukan_firma, pg.no_tel, pg.no_fax, pg.emel, pg.id_bandar ";
		    		sql2 += "FROM Tblppkperayu p, Tblppkpeguamperayu pp, Tblppkpeguam pg ";
		    		sql2 += "WHERE pp.id_perayu = p.id_perayu AND pp.id_peguam = pg.id_peguam ";
		    		sql2 += "AND p.id_perayu = '"+id_perayu+"'";
		    		sql2 += " AND pg.id_peguam = '"+id_peguam+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql2);
		      
		    		Hashtable h;
		 
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("id_peguam", rs.getString("id_peguam")==null?"":rs.getString("id_peguam"));
		    			h.put("nama_firma", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
		    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
		    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    			h.put("no_rujukan_firma", rs.getString("no_rujukan_firma")==null?"":rs.getString("no_rujukan_firma"));
		    			h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
		    			h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
		    			h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
		    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				    
		    			listLatestPeguam.addElement(h);
		      }
		      
		    } finally {
		      if (db != null) db.close();
		    }    
		  }//close Latest Peguam
	 
		
		//simpan Tambahan Peguam
		public static void simpanTambahanPeguam(Hashtable data) throws Exception
		  {
			
			Connection conn = null;
		    Db db = null;
		    String sql = "";
		    
		    try
		    {
		    	  db = new Db();
		    	  conn = db.getConnection();
				  conn.setAutoCommit(false);
		    	  Statement stmt = db.getStatement();
		    	  
		    	  
		    	  //validation insert/update
				  int Xdefault = (Integer)data.get("Xdefault");
				  String id_peguamVAL = (String)data.get("id_peguam");	
			    	
				  long id_peguam = 0;
				  
			      if(id_peguamVAL!=""){
			    	  String idpg = (String)data.get("id_peguam");
			    	  id_peguam = Long.parseLong(idpg);
			      }else{
			    	  id_peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
			      }
		      
		     
			      //id	
			      String id_permohonan = (String)data.get("id_permohonan");
			      String id_pemohon = (String)data.get("id_pemohon");
			      String id_perayu = (String)data.get("id_perayu");
		    	   
			      //data peguam
			      String namaFirma = (String)data.get("txtNamaFirma");
			      String noRujukan = (String)data.get("txtNoRujukan");
			      String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
			      String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
			      String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
			      String poskodPeguam = (String)data.get("txtPoskodPeguam");
			      String bandarPeguam = (String)data.get("txtBandarPeguam");
			      String noTelefon = (String)data.get("txtNoTelefon");
			      String noFaks = (String)data.get("txtNoFaks");
			      String email = (String)data.get("txtEmel");
			      String negeriPeguam = (String)data.get("socNegeriPeguam");
		    	
			      //id login masuk
			      String id_masuk = (String)data.get("id_masuk");
		      
		      
		      
			      if(Xdefault==2){
			    	  
			    	  //add data to Tblppkpeguam
			    	  SQLRenderer r = new SQLRenderer();
			    	  r.add("id_peguam", id_peguam);
			    	  r.add("nama_firma", namaFirma);
			    	  r.add("no_rujukan_firma", noRujukan);
			    	  r.add("alamat1", alamatPeguam1); 	
			    	  r.add("alamat2", alamatPeguam2);
			    	  r.add("alamat3", alamatPeguam3);
			    	  r.add("poskod", poskodPeguam);
			    	  r.add("id_bandar", bandarPeguam);
			    	  r.add("no_tel", noTelefon);
			    	  r.add("no_fax", noFaks);
			    	  r.add("emel", email);
			    	  r.add("id_negeri", negeriPeguam);
			    	  r.add("tarikh_masuk", r.unquote("sysdate"));
			    	  r.add("id_masuk",id_masuk);	      
			    	  sql = r.getSQLInsert("Tblppkpeguam");
			    	  stmt.executeUpdate(sql);
			    	  
			      }
		      
			      else if(Xdefault==1){
			    	
			    	  SQLRenderer r = new SQLRenderer();
			    	  r.update("id_peguam", id_peguam);
			    	  //r.add("nama_firma", namaFirma);
			    	  r.add("no_rujukan_firma", noRujukan);
			    	  r.add("alamat1", alamatPeguam1); 	
			    	  r.add("alamat2", alamatPeguam2);
			    	  r.add("alamat3", alamatPeguam3);
			    	  r.add("poskod", poskodPeguam);
			    	  r.add("id_bandar", bandarPeguam);
			    	  r.add("no_tel", noTelefon);
			    	  r.add("no_fax", noFaks);
			    	  r.add("emel", email);
			    	  r.add("id_negeri", negeriPeguam);
			    	  r.add("tarikh_kemaskini", r.unquote("sysdate"));
			    	  r.add("id_kemaskini",id_masuk);	      
			    	  sql = r.getSQLUpdate("Tblppkpeguam");
			    	  stmt.executeUpdate(sql);
		    	  
			      }
		      
			      
			      if(Xdefault==2){
			    	  
			    	  //add data to tblppkpeguampemohon with id pemohon
			    	  SQLRenderer rMST = new SQLRenderer();
			    	  rMST.add("id_peguam", id_peguam);	
			    	  rMST.add("id_pemohon", id_pemohon);	
			    	  rMST.add("id_masuk",id_masuk);
			    	  rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
			    	  sql = rMST.getSQLInsert("Tblppkpeguampemohon");
			    	  stmt.executeUpdate(sql);
			      }
		      
			      
			      //add data to tblppkpeguamperayu
			      SQLRenderer rPe = new SQLRenderer();
			      rPe.add("id_peguam", id_peguam); 	
			      rPe.add("id_perayu", id_perayu); 
			      rPe.add("id_masuk",id_masuk);
			      rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
			      sql = rPe.getSQLInsert("Tblppkpeguamperayu");
			      stmt.executeUpdate(sql);
			      
			      
			      conn.commit();	
			      
		    }catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
		    	
		    }
		    finally {
		      if (db != null) db.close();
		    }
		   
		  }//close simpan Tambahan Peguam
	
		
		//get Other Peguam
		 public static Vector getOtherPeguam(String id_peguam)throws Exception {
			 
			    Db db = null;
			    String sql = "";
			    
			    try {
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			     
			    		sql = "SELECT DISTINCT id_peguam, nama_firma, no_rujukan_firma, alamat1, ";
			    		sql += "alamat2, alamat3, poskod, bandar, id_negeri, no_tel, no_fax, emel, id_bandar ";
			    		sql += "FROM Tblppkpeguam ";
			    		sql += "WHERE id_peguam = '"+id_peguam+"'";
			      
			    		ResultSet rs = stmt.executeQuery(sql);
			    		
			    		Vector list = new Vector();
			      
			    		Hashtable h = null;
			      
			    		while (rs.next()) {
			    			
			    			h = new Hashtable();
			    			h.put("id_peguam", rs.getString("id_peguam")==null?"":rs.getString("id_peguam"));
			    			h.put("nama_firma", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
			    			h.put("no_rujukan_firma", rs.getString("no_rujukan_firma")==null?"":rs.getString("no_rujukan_firma"));
			    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    			h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
			    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    			h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
			    			h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
			    			h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
			    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
					     
			    			list.addElement(h);

			      }
			    		
			      return list;
			      
			    } finally {
			      if (db != null) db.close();
			    }
			    
		 }//get Other Peguam
		 
		 
		//update SuburusanS Fail
		public static void updateSuburusanSFail(HttpSession session,Hashtable data) throws Exception{
			
			    Db db = null;
			    String sql = "";
			    String sql5 = "";
			    String sql6 = "";
			    
			    try
			    {
			    	  	db = new Db();
			    	  	Statement stmt = db.getStatement();
			    		
			    	  	//id	
			    	  	
			    	  	String id_permohonan = (String)data.get("id_permohonan");
			    	  	String id_masuk = (String)data.get("id_masuk");
			    	  	String id_fail = (String)data.get("id_fail");
			    	  	String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
			    	  	
			    	  	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					    logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"64","422",id_fail);
				 	  
				 	  /*
				 	  	//update n add tblrujsuburusanstatus
				      	SQLRenderer r6 = new SQLRenderer();
						r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
						r6.add("AKTIF",0);
						r6.add("ID_KEMASKINI",id_masuk);
						r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
						sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
						stmt.executeUpdate(sql6);	 
				      
				      
						SQLRenderer r5 = new SQLRenderer();
						r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
						r5.add("ID_PERMOHONAN",id_permohonan);
						r5.add("ID_FAIL",id_fail);
						r5.add("ID_SUBURUSANSTATUS",422);
						r5.add("AKTIF",1);
						r5.add("ID_MASUK",id_masuk);
						r5.add("ID_KEMASKINI",id_masuk);
						r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
						r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
						sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
						stmt.executeUpdate(sql5);	
						*/
			      
			    }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close update Suburusan SFail
			
		
		//getListFirma
		public static Vector getListFirmaTerdahulu(String id_pemohon)throws Exception {
			
			Db db = null;
		 	String sql = "";
				    
			try {
				
				      db = new Db();
				      Statement stmt = db.getStatement();
				     
				      sql =  "select p.id_peguam,pp.id_pemohon,p.nama_firma,p.no_rujukan_firma,p.alamat1,p.alamat2,p.alamat3,p.poskod, ";
				      sql += "p.bandar,p.id_negeri,p.no_tel,p.no_fax,emel ";
				      sql += "from tblppkpeguampemohon pp,tblppkpeguam p ";
				      sql += "where pp.id_peguam = p.id_peguam ";
				      sql += "and id_pemohon = '"+id_pemohon+"'";
				      sql += " ORDER BY p.nama_firma asc";
				      
				      ResultSet rs = stmt.executeQuery(sql);
				      Vector list = new Vector();
				      
				      Hashtable h = null;
				      int bil = 1;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("bil",bil);
				    	  h.put("id_peguam", rs.getString("id_peguam")==null?"":rs.getString("id_peguam"));
				    	  h.put("id_pemohon", rs.getString("id_pemohon")==null?"":rs.getString("id_pemohon"));
				    	  h.put("nama_firma", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
				    	  h.put("no_rujukan_firma", rs.getString("no_rujukan_firma")==null?"":rs.getString("no_rujukan_firma"));
				    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
				    	  h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
				    	  
				    	  list.addElement(h);
				    	  bil++;
				      }
				      return list;
				    } finally {
				      if (db != null) db.close();
				    }
				  }//getListFirma terdahulu		
	
		
		//getListFirma by idpeguam
		 public static Vector getDetailFirma(String id_peguam)throws Exception {
			 
			Db db = null;
			String sql = "";
				    
			try {
				
				      db = new Db();
				      Statement stmt = db.getStatement();
				     
				      sql =  "select p.id_peguam,p.nama_firma,p.no_rujukan_firma,p.alamat1,p.alamat2,p.alamat3,p.poskod, ";
				      sql += "p.bandar,p.id_negeri,p.no_tel,p.no_fax,p.emel,p.id_bandar ";
				      sql += "from tblppkpeguam p ";
				      sql += "where id_peguam = '"+id_peguam+"'" ;
				      sql += " ORDER BY p.nama_firma asc";
				      
				      ResultSet rs = stmt.executeQuery(sql);
				      Vector list = new Vector();
				      
				      Hashtable h = null;
				      
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("id_peguam", rs.getString("id_peguam")==null?"":rs.getString("id_peguam"));
				    	  h.put("nama_firma", rs.getString("nama_firma")==null?"":rs.getString("nama_firma"));
				    	  h.put("no_rujukan_firma", rs.getString("no_rujukan_firma")==null?"":rs.getString("no_rujukan_firma"));
				    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
				    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
				    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
				    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
				    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
				    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				    	  h.put("no_tel", rs.getString("no_tel")==null?"":rs.getString("no_tel"));
				    	  h.put("no_fax", rs.getString("no_fax")==null?"":rs.getString("no_fax"));
				    	  h.put("emel", rs.getString("emel")==null?"":rs.getString("emel"));
				    	  h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
				    	  
				    	  list.addElement(h);
				    	 
				      }
				      return list;
				    } finally {
				      if (db != null) db.close();
				    }
				  }//getListFirma terdahulu	by idpeguam
			
		 
		//get Other Peguam
		public static Vector getDropdownFirmaTerdahulu(String id_pemohon,String id_permohonan)throws Exception {
				    
			Db db = null;
			String sql = "";
			String id_peguam = "";
			
			try {
				
				      	db = new Db();
				     	Statement stmt = db.getStatement();
				     
				     	sql =  " SELECT pg.id_peguam, p.id_perayu, pg.nama_firma ";
						sql += " FROM Tblppkperayu p, tblppkrayuan pr, tblppkpermohonan a, Tblppkpeguamperayu pp, ";
						sql += " Tblppkpeguam pg, Tblppkpeguampemohon pgp ";
						sql += " WHERE pp.id_perayu = p.id_perayu ";
						sql += " AND p.id_rayuan = pr.id_rayuan ";
						sql += " AND pr.id_permohonan = a.id_permohonan ";
						sql += " AND pp.id_peguam = pg.id_peguam ";
						sql += " AND pgp.id_peguam = pg.id_peguam ";
						sql += " AND pgp.id_pemohon = '" + id_pemohon + "'" ;
						sql += " AND a.id_permohonan = '" + id_permohonan + "'" ;
						sql += " ORDER BY pg.nama_firma asc "; 
				      
						ResultSet rs = stmt.executeQuery(sql);
						int i =0;
						while (rs.next()) {
						
						
							if( i==0)
							{
								id_peguam += "ALL("+rs.getString("id_peguam");
							}
							else
							{
								id_peguam += ","+rs.getString("id_peguam");
							}
							i++;
						  
							}
							if(id_peguam!="")
							{	
								id_peguam +=")";
							}
							
							
						SQLRenderer rN = new SQLRenderer();
						sql = "select p.id_peguam,p.nama_firma ";
						sql += "from tblppkpeguampemohon pp,tblppkpeguam p ";
						sql += "where pp.id_peguam = p.id_peguam ";
						sql += "and id_pemohon = " +id_pemohon ;
						sql += " and p.nama_firma is not null ";
							if(id_peguam != "")
						    {
						    sql +=" and p.id_peguam != "+id_peguam;
						    }
						sql += " ORDER BY p.nama_firma asc ";
						    
						ResultSet rx = stmt.executeQuery(sql);
						Vector list = new Vector();
						      
						      Hashtable h = null;
						      
						      while (rx.next()) {
						    	  h = new Hashtable();
						    	  h.put("id_peguam", rx.getString("id_peguam")==null?"":rx.getString("id_peguam"));
						    	  h.put("nama_firma", rx.getString("nama_firma")==null?"":rx.getString("nama_firma"));
						    	  
						    	  list.addElement(h);

				      }
						      
				      return list;
				      
				    } finally {
				      if (db != null) db.close();
				    }
				    
		}//get Other Peguam
			 
			 
		public static void deleteDataPeguam(String id_peguam) throws Exception {
				 
			     Connection conn = null;
				 Db db = null;
				 String sql = "";
				 String sql2 = "";
				 String sql3 = "";
				    
				 try {
				    	
				      db = new Db();
				      conn = db.getConnection();
					  conn.setAutoCommit(false);
				      Statement stmt = db.getStatement();
				      
				      sql =  "DELETE FROM tblppkpeguamperayu WHERE id_peguam = '"+id_peguam+"'";     				    
				      stmt.executeUpdate(sql);
				      
				      sql2 =  "DELETE FROM tblppkpeguampemohon WHERE id_peguam = '"+id_peguam+"'";     				    
				      stmt.executeUpdate(sql2);
				      
				      sql3 =  "DELETE FROM tblppkpeguam WHERE id_peguam = '"+id_peguam+"'";     				    
				      stmt.executeUpdate(sql3);
				      
				      conn.commit();	
				      
				      
				    }catch (SQLException se) { 
				    	try {
				    		conn.rollback();
				    	} catch (SQLException se2) {
				    		throw new Exception("Rollback error:"+se2.getMessage());
				    	}
				    	throw new Exception("Ralat : Masalah penyimpanan data ");
				    }finally {
				      if (db != null) db.close();
				    }
				  }
				//delete delete data peguam
			
		//tambah Peguam Lama
		public static void tambahPeguamLama(Hashtable data) throws Exception{
			
				Db db = null;
				String sql = "";
				
				try
				    {
				    	  db = new Db();
				    	  Statement stmt = db.getStatement();
				    	  
				    	  //id	
					      String id_perayu = (String)data.get("id_perayu");
					      String id_peguamlama = (String)data.get("id_peguamlama");
					      
					      //id login kemaskini
					      String id_login = (String)data.get("id_kemaskini");
					      
					   	  //data peguam
					      String noRujukan = (String)data.get("txtNoRujukan");
						  String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
						  String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
						  String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
						  String poskodPeguam = (String)data.get("txtPoskodPeguam");
						  String bandarPeguam = (String)data.get("txtBandarPeguam");
						  String noTelefon = (String)data.get("txtNoTelefon");
						  String noFaks = (String)data.get("txtNoFaks");
						  String email = (String)data.get("txtEmel");
						  String negeriPeguam = (String)data.get("socNegeriPeguam");
					      
					      //update table peguam    
					      SQLRenderer r2 = new SQLRenderer();
					      r2.update("id_peguam", id_peguamlama);
					      r2.add("no_rujukan_firma", noRujukan);
					      r2.add("alamat1", alamatPeguam1); 	
					      r2.add("alamat2", alamatPeguam2);
					      r2.add("alamat3", alamatPeguam3);
					      r2.add("poskod", poskodPeguam);
					      r2.add("id_bandar", bandarPeguam);
					      r2.add("no_tel", noTelefon);
					      r2.add("no_fax", noFaks);
					      r2.add("emel", email);
					      r2.add("id_negeri", negeriPeguam);
					      r2.add("tarikh_kemaskini", r2.unquote("sysdate"));
					      r2.add("id_kemaskini",id_login);		      
					      sql = r2.getSQLUpdate("Tblppkpeguam");
					      stmt.executeUpdate(sql);
					      
					      //add data to tblppkpeguamperayu
					      SQLRenderer rPe = new SQLRenderer();
					      rPe.add("id_peguam", id_peguamlama); 	
					      rPe.add("id_perayu", id_perayu); 
					      rPe.add("id_masuk", id_login);
					      rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
					      sql = rPe.getSQLInsert("Tblppkpeguamperayu");
					      stmt.executeUpdate(sql);
					       
				    }//close try 
				    
				    finally {
				      if (db != null) db.close();
				    }//close finally
				   
			}//close tambah Peguam Lama
			 
				
		//update Maklumat PP
		public static void updatePeguam(Hashtable data) throws Exception{
				    
			Db db = null;
			String sql = "";
			
			try
				  {
				    	
						  db = new Db();
						  Statement stmt = db.getStatement();
						  
				    	  //id	
					      String id_permohonan = (String)data.get("id_permohonan");
					      String id_pemohon = (String)data.get("id_pemohon");
					      String id_perayu = (String)data.get("id_perayu");
					      String id_peguam = (String)data.get("id_peguam");
					      
					      //data peguam
					      String namaFirma = (String)data.get("txtNamaFirma");
					      String noRujukan = (String)data.get("txtNoRujukan");
						  String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
						  String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
						  String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
						  String poskodPeguam = (String)data.get("txtPoskodPeguam");
						  String bandarPeguam = (String)data.get("txtBandarPeguam");
						  String noTelefon = (String)data.get("txtNoTelefon");
						  String noFaks = (String)data.get("txtNoFaks");
						  String email = (String)data.get("txtEmel");
						  String negeriPeguam = (String)data.get("socNegeriPeguam");
					    	
					      //id login kemaskini
					      String id_kemaskini = (String)data.get("id_kemaskini");
					  
					      //update table peguam    
					      SQLRenderer r2 = new SQLRenderer();
					      r2.update("id_peguam", id_peguam);
					      r2.add("nama_firma", namaFirma);
					      r2.add("no_rujukan_firma", noRujukan);
					      r2.add("alamat1", alamatPeguam1); 	
					      r2.add("alamat2", alamatPeguam2);
					      r2.add("alamat3", alamatPeguam3);
					      r2.add("poskod", poskodPeguam);
					      r2.add("id_bandar", bandarPeguam);
					      r2.add("no_tel", noTelefon);
					      r2.add("no_fax", noFaks);
					      r2.add("emel", email);
					      r2.add("id_negeri", negeriPeguam);
					      r2.add("tarikh_kemaskini", r2.unquote("sysdate"));
					      r2.add("id_kemaskini",id_kemaskini);		      
					      sql = r2.getSQLUpdate("Tblppkpeguam");
					      stmt.executeUpdate(sql);
				      
				    }//close try 
				    
				    finally {
				      if (db != null) db.close();
				    }//close finally
				   
			}//close update perayu	
		
		//simpan Rekod Rayuan [update]
		public static void simpanRekodRayuan(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	
		    	  db = new Db();
			      Statement stmt = db.getStatement();
			      
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			  
			      //data perayu
			      String asas_keputusan = (String)data.get("asas_keputusan");
			      String nota_bicara = (String)data.get("nota_bicara");
			      String qBorangA = (String)data.get("qBorangA");
			      String qBorangP = (String)data.get("qBorangP");
			      String qBorangDDA = (String)data.get("qBorangDDA");
			      String qBorangSA = (String)data.get("qBorangSA");
			      
			      String q1BorangA = (String)data.get("q1BorangA");
			      String q1BorangP = (String)data.get("q1BorangP");
			      String q1BorangDDA = (String)data.get("q1BorangDDA");
			      String q1BorangSA = (String)data.get("q1BorangSA");
			      
			      String qLampiran1 = (String)data.get("qfeeLain1");
			      String Lampiran1 = (String)data.get("Lampiran1");
			      String F2Lampiran1 = (String)data.get("F2LAMPIRAN1");
			      String FLampiran1 = (String)data.get("FLAMPIRAN1");
			      
			      String qLampiran2 = (String)data.get("qfeeLain2");
			      String Lampiran2 = (String)data.get("Lampiran2");
			      String F2Lampiran2 = (String)data.get("F2LAMPIRAN2");
			      String FLampiran2 = (String)data.get("FLAMPIRAN2");
			      
			      String qLampiran3 = (String)data.get("qfeeLain3");
			      String Lampiran3 = (String)data.get("Lampiran3");
			      String F2Lampiran3 = (String)data.get("F2LAMPIRAN3");
			      String FLampiran3 = (String)data.get("FLAMPIRAN3");
			      
			      String qLampiran4 = (String)data.get("qfeeLain4");
			      String Lampiran4 = (String)data.get("Lampiran4");
			      String F2Lampiran4 = (String)data.get("F2LAMPIRAN4");
			      String FLampiran4 = (String)data.get("FLAMPIRAN4");
			      
			      String qLampiran5 = (String)data.get("qfeeLain5");
			      String Lampiran5 = (String)data.get("Lampiran5");
			      String F2Lampiran5 = (String)data.get("F2LAMPIRAN5");
			      String FLampiran5 = (String)data.get("FLAMPIRAN5");
			      
			      String qLampiran6 = (String)data.get("qfeeLain6");
			      String Lampiran6 = (String)data.get("Lampiran6");
			      String F2Lampiran6 = (String)data.get("F2LAMPIRAN6");
			      String FLampiran6 = (String)data.get("FLAMPIRAN6");
			      
			      String qLampiran7 = (String)data.get("qfeeLain7");
			      String Lampiran7 = (String)data.get("Lampiran7");
			      String F2Lampiran7 = (String)data.get("F2LAMPIRAN7");
			      String FLampiran7 = (String)data.get("FLAMPIRAN7");
			      
			      String qLampiran8 = (String)data.get("qfeeLain8");
			      String Lampiran8 = (String)data.get("Lampiran8");
			      String F2Lampiran8 = (String)data.get("F2LAMPIRAN8");
			      String FLampiran8 = (String)data.get("FLAMPIRAN8");
			      
			      String jumlahAllfee = (String)data.get("jumlahAllfee");
			      //id login kemaskini
			      String id_kemaskini = (String)data.get("id_kemaskini");
			      
			      if(id_rayuan!=""){
			    	  //update table perayu    
			    	  SQLRenderer r = new SQLRenderer();
			    	  r.update("id_rayuan", id_rayuan);
			    	  r.add("asas_keputusan", asas_keputusan);
			    	  r.add("nota_bicara", nota_bicara);
			    	  r.add("id_kemaskini",id_kemaskini);
			    	  r.add("BORANG_A",qBorangA);
			    	  r.add("BORANG_P",qBorangP);
			    	  r.add("BORANG_DDA",qBorangDDA);
			    	  r.add("SA",qBorangSA);
			    	  
			    	  r.add("QBORANG1A",q1BorangA);
			    	  r.add("QBORANG1P",q1BorangP);
			    	  r.add("QBORANG1DDA",q1BorangDDA);
			    	  r.add("Q1SA",q1BorangSA);
			    	  
			    	  r.add("JUMLAH_BAYARAN",jumlahAllfee);
			    	  r.add("tarikh_kemaskini", r.unquote("sysdate"));	
			    	  r.add("FLampiran1",F2Lampiran1);
			    	  r.add("F2LAMPIRAN1",FLampiran1);
			    	  r.add("Lampiran1",Lampiran1);
			    	  r.add("QLAMPIRAN1",qLampiran1);
			    	  
			    	  r.add("Lampiran2",Lampiran2);
			    	  r.add("FLampiran2",F2Lampiran2);
			    	  r.add("F2LAMPIRAN2",FLampiran2);
			    	  r.add("QLAMPIRAN2",qLampiran2);
			    	  
			    	  r.add("Lampiran3",Lampiran3);
			    	  r.add("FLampiran3",F2Lampiran3);
			    	  r.add("F2LAMPIRAN3",FLampiran3);
			    	  r.add("QLAMPIRAN3",qLampiran3);
			    	  
			    	  r.add("Lampiran4",Lampiran4);
			    	  r.add("FLampiran4",F2Lampiran4);
			    	  r.add("F2LAMPIRAN4",FLampiran4);
			    	  r.add("QLAMPIRAN4",qLampiran4);
			    	  
			    	  r.add("Lampiran5",Lampiran5);
			    	  r.add("FLampiran5",F2Lampiran5);
			    	  r.add("F2LAMPIRAN5",FLampiran5);
			    	  r.add("QLAMPIRAN5",qLampiran5);
			    	  
			    	  r.add("Lampiran6",Lampiran6);
			    	  r.add("FLampiran6",F2Lampiran6);
			    	  r.add("F2LAMPIRAN6",FLampiran6);
			    	  r.add("QLAMPIRAN6",qLampiran6);
			    	  
			    	  r.add("Lampiran7",Lampiran7);
			    	  r.add("FLampiran7",F2Lampiran7);
			    	  r.add("F2LAMPIRAN7",FLampiran7);
			    	  r.add("QLAMPIRAN7",qLampiran7);
			    	  
			    	  r.add("Lampiran8",Lampiran8);
			    	  r.add("FLampiran8",F2Lampiran8);
			    	  r.add("F2LAMPIRAN8",FLampiran8);
			    	  r.add("QLAMPIRAN8",qLampiran8);
			    	  
			    	  sql = r.getSQLUpdate("Tblppkrayuan");
			    	  myLogger.info("sql = "+sql);
			    	  stmt.executeUpdate(sql);
			      }
			      
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close simpan Rekod Rayuan [update]
		
		
		public static void addSelectedOB(Hashtable data, String idOB) throws Exception {
			
			Connection conn = null;
			Db db = null;
			String sql = "";
		  
			try {
				  
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
		        
					String id_rayuan = (String)data.get("id_rayuan");
					String id_pegawai = (String)data.get("id_kemaskini");

					SQLRenderer rDTL = new SQLRenderer();
					rDTL.add("id_ob", idOB);
					rDTL.add("id_rayuan", id_rayuan);
					rDTL.add("id_masuk",id_pegawai);
					rDTL.add("tarikh_masuk",rDTL.unquote("sysdate"));
					sql = rDTL.getSQLInsert("TBLPPKRAYUANOB");
					stmt.executeUpdate(sql);
	
					
			      conn.commit();	
			      
			    }   catch (SQLException se) { 
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat : Masalah penyimpanan data ");
			    }
			    finally {
			      if (db != null) db.close();
			    }
		
		}//close addSelectedOB

			
		//LIST OB cbsemak
		public static void setListcbOB(String id_permohonansimati,String id_rayuan,String id_perayu,String id_simati) throws Exception {

			Db db = null;
			listcbOB.clear();
			String sql = "";
			String sql2 = "";
			
			try{
				
				db = new Db();
				
				Statement stmt = db.getStatement();
				
				String id_ob = "";
				
				sql2 = "SELECT distinct id_ob FROM tblppkob WHERE id_perayu = '"+id_perayu+"'";
				ResultSet rx = stmt.executeQuery(sql2);
				
				while (rx.next()) {
					 id_ob = rx.getString("id_ob");
				}
				
				//System.out.println("idperayu/idob : "+id_ob);
		
				sql =  "SELECT ob.id_ob,ob.nama_ob, (select count(*) from tblppkrayuanob where id_ob=ob.id_ob and id_rayuan = '"+id_rayuan+"')as flag ";
				sql += " FROM Tblppkob ob, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
				sql += " WHERE ob.id_permohonansimati = sm.id_permohonansimati ";
				sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";  
				sql += " AND sm.id_permohonan = p.id_permohonan ";  
				sql += " AND p.id_status <> '169' ";  
				sql += " AND ob.id_simati = '" + id_simati + "'"; 
				sql += " AND ob.id_permohonansimati <= '"+id_permohonansimati+"'";
				sql += " AND NVL(ob.status_ob,0) != 4 ";
				sql += " AND ob.id_tarafkptg != 14 ";   
				sql += " AND NVL(ob.status_hidup,0) = 0 "; 
				sql += " AND NVL(ob.umur,18)  >= 18 "; 
				
				if(id_ob!=""){
					sql += " AND ob.id_ob <> '"+id_ob+"'"; 
				}
				
				sql += " ORDER BY ob.nama_ob asc ";

				//System.out.println("sql :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
				int bil = 1;
			    
				
			   while (rs.next()) {
			    h = new Hashtable();
			    
			    h.put("bil", bil);
			    h.put("id_ob", rs.getString("id_ob")==null?"":rs.getString("id_ob"));
			    h.put("nama_ob", rs.getString("nama_ob")==null?"":rs.getString("nama_ob"));
			    h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
			    
			    listcbOB.addElement(h);
			    bil++;
			    
			   }      
			} catch (Exception e) {e.printStackTrace();}
			finally{
				
				if(db != null)db.close();
			}
		}//close OB cbsemak
		
		
		//delete checked ob
		public static void cbObDelete(String id_rayuan) throws Exception {
			
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		sql =  "DELETE FROM TBLPPKRAYUANOB WHERE id_rayuan = '"+id_rayuan+"'";     
		    		stmt.executeUpdate(sql);
		      
		    } finally {
		      if (db != null) db.close();
		    }
		  
		}//delete checked ob
		
		//get id perintah
		public static Vector getPerintah(String idperbicaraan)throws Exception {
			 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT max(k.id_perintah)as id_perintah ";
		      sql +="FROM Tblppkperintah k ";
		      sql +="WHERE k.id_perbicaraan = '"+idperbicaraan+"'";
		      System.out.println("sqlgetPerintah :"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get id perintah
		
		//get flag rayuan from idblppkperintah
		public static Vector getFlagRayuan(String id_perintah)throws Exception {
			 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT id_perintah, flag_rayuan ";
		      sql +="FROM Tblppkperintah k ";
		      sql +="WHERE k.id_perintah = '"+id_perintah+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	  h.put("flag_rayuan", rs.getString("flag_rayuan")==null?"":rs.getString("flag_rayuan"));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//get flag rayuan from itblppkperintah
		
		//get tarikhselesai dari TBLPPKPERMOHONAN
		public static Vector getTarikhSelesai(String id_permohonan)throws Exception {
			 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT TARIKH_SELESAI FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("tarikh_selesai", rs.getString("tarikh_selesai")==null?"":rs.getString("tarikh_selesai"));
		    	 
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }		
		
		
		//update flag rayuan di tblppkperintah
		public static void updateFlagRayuan(Hashtable data) throws Exception{
			
			    Db db = null;
			    String sql = "";

			    
			    try
			    {
			    	  	db = new Db();
			    	  	Statement stmt = db.getStatement();
			    		
			    	  	//id	
			    	  	String id_perintah = (String)data.get("id_perintah");
			    	  	String flag = (String)data.get("flag");
			    	  	String id_masuk = (String)data.get("id_masuk");
			    	  	
				 	  	//update n add tblrujsuburusanstatus
				      	SQLRenderer r6 = new SQLRenderer();
						r6.update("id_perintah",id_perintah);	
						r6.add("FLAG_RAYUAN",flag);
						r6.add("ID_KEMASKINI",id_masuk);
						r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
						sql = r6.getSQLUpdate("tblppkperintah");
						stmt.executeUpdate(sql); 
				      
			      
			    }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close update flag rayuan di tblppkperintah
		
		//simpan update memorandum
		public static void updateMemorandum(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	
		    	  db = new Db();
			      Statement stmt = db.getStatement();
			      
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			  
			      //data perayu
			      String perkara_rayu_memorandum = (String)data.get("perkara_rayu_memorandum");
			      String alasan_rayuan_memorandum = (String)data.get("alasan_rayuan_memorandum");
			     // String nota_bicara = (String)data.get("nota_bicara");
			      //id login
			      String id_kemaskini = (String)data.get("id_kemaskini");
			 
			      //update table perayu    
			      SQLRenderer r = new SQLRenderer();
			      r.update("id_rayuan", id_rayuan);
			      r.add("perkara_rayu_memorandum", perkara_rayu_memorandum);
			      r.add("alasan_rayuan_memorandum", alasan_rayuan_memorandum);
			      //r.add("nota_bicara", nota_bicara);
				  r.add("id_kemaskini",id_kemaskini);
				  r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
				  sql = r.getSQLUpdate("Tblppkrayuan");
				  System.out.println("sql :"+sql);
				  stmt.executeUpdate(sql);
		     	      
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close update memorandum
		
		
		//simpan Semakan K2
		public static void simpanRadioK2(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		 
		    try
		    {
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		    	
		    	long id_semakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
		    	
		    	//id login
		 	    String id_masuk = (String)data.get("id_masuk");
		 	    String id_permohonan = (String)data.get("id_permohonan");
		 	    String radiosemaks = (String)data.get("radiosemaks");
		 	     
			    SQLRenderer r1 = new SQLRenderer();
				r1.add("id_semakanhantar", id_semakanhantar);
				r1.add("id_semakansenarai", radiosemaks);
				r1.add("id_permohonan", id_permohonan);
				r1.add("id_masuk",id_masuk); 
				r1.add("tarikh_masuk",r1.unquote("sysdate")); 
				sql = r1.getSQLInsert("Tblsemakanhantar");
				stmt.executeUpdate(sql);
				
				
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close simpanSemakanK2
		
		
		//-- 02122009
		//LIST  maklumat mst rayuan
		public static void setDataMstRayuan(String id_perbicaraan) throws Exception {

			Db db = null;
			listMstRayuan.clear();
			String sql = "";
		
			try{
				
				db = new Db();
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				sql =  "SELECT mst.id_notisobmst, mst.nama_penghantar_notis, mst.catatan, mst.status_serah,";
				sql += " mst.jenis_serah, mst.no_surat_daftar, pn.kod_penghantar_notis, mst.id_penghantarnotis, mst.tarikh_serahan";
				sql += " FROM Tblppknotisobmst mst, Tblppkrujpenghantarnotis pn, Tblppknotisperbicaraan npb, Tblppkperbicaraan p";
				sql += " WHERE mst.id_penghantarnotis = pn.id_penghantarnotis";
				sql += " AND npb.id_notisobmst = mst.id_notisobmst";
				sql += " AND npb.id_perbicaraan = p.id_perbicaraan";
				sql += " AND npb.flag_jenis_notis = '1'";
				sql += " AND npb.id_perbicaraan = '"+id_perbicaraan+"'"; 

				//System.out.println("sql :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
			    Hashtable h;
				
			    while (rs.next()) {
			    	
			    h = new Hashtable();
			    
			    h.put("id_notisobmst", rs.getString("id_notisobmst")==null?"":rs.getString("id_notisobmst"));
			    h.put("id_penghantarnotis", rs.getString("id_penghantarnotis")==null?"":rs.getString("id_penghantarnotis"));
			    h.put("nama_penghantar_notis", rs.getString("nama_penghantar_notis")==null?"":rs.getString("nama_penghantar_notis"));
			    h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			    h.put("status_serah", rs.getString("status_serah")==null?"":rs.getString("status_serah"));
			    h.put("jenis_serah", rs.getString("jenis_serah")==null?"":rs.getString("jenis_serah"));
			    h.put("no_surat_daftar", rs.getString("no_surat_daftar")==null?"":rs.getString("no_surat_daftar"));
			    h.put("kod_penghantar_notis", rs.getString("kod_penghantar_notis")==null?"":rs.getString("kod_penghantar_notis"));
			    h.put("tarikhS", rs.getDate("tarikh_serahan")==null?"":Format.format(rs.getDate("tarikh_serahan")));
			    
			    listMstRayuan.addElement(h);
			    
			   }      
			} catch (Exception e) {e.printStackTrace();}
			finally{
				
				if(db != null)db.close();
			}
			
		}//close maklumat mst rayuan
		
		
		//-- 02122009
		//add Mst Rayuan
		public static void addMstRayuan(Hashtable data) throws Exception{
			
			Connection conn = null;
		    Db db = null;
		    String sql = "";
		    String sqlMST = "";
		    String sqlNO = "";
		    
		    try
		    {
		    	  db = new Db();
		    	  conn = db.getConnection();
				  conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();
		    	
			      String idperbicaraan = (String)data.get("idperbicaraan");
			      String jenis_status = (String)data.get("jenis_status");
			      String jenis_serah = (String)data.get("jenis_serah");
			      String catatan = (String)data.get("catatan");
			      String id_penghantar = (String)data.get("id_penghantar");
			      String daftar_pos = (String)data.get("daftar_pos");
			      String nama_penghantar = (String)data.get("nama_penghantar");
			     
			      //11122009
			      String _tarikhS = (String)data.get("tarikhS");
			      String tarikhS = "to_date('" + _tarikhS + "','dd/MM/yyyy')";
			      
			      //id login kemaskini
			      String id_masuk = (String)data.get("id_kemaskini");
			  
			      long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ"); 
			      
			      SQLRenderer rMST = new SQLRenderer();
			      rMST.add("id_notisobmst",id_notisobmst);
			      rMST.add("status_serah", jenis_status);
			      rMST.add("jenis_serah", jenis_serah);
			      rMST.add("catatan", catatan);
			      rMST.add("nama_penghantar_notis", nama_penghantar);
			      rMST.add("id_penghantarnotis", id_penghantar);
			      rMST.add("no_surat_daftar", daftar_pos);
			      
			      //11122009
			      rMST.add("tarikh_serahan",rMST.unquote(tarikhS));
			      
			      rMST.add("id_masuk",id_masuk);
			      rMST.add("tarikh_masuk",rMST.unquote("sysdate"));
			      sqlMST = rMST.getSQLInsert("TBLPPKNOTISOBMST");
			      stmt.executeUpdate(sqlMST);
			      
			      // + flag jenis notis [1 = rayuan]
			      String flag = "1";
			      
			      SQLRenderer rNO = new SQLRenderer();
			      rNO.add("id_notisobmst", id_notisobmst);
			      rNO.add("id_perbicaraan", idperbicaraan);
			      rNO.add("flag_jenis_notis", flag);
			      rNO.add("id_masuk",id_masuk);
			      rNO.add("tarikh_masuk",rNO.unquote("sysdate"));
			      sqlNO = rNO.getSQLInsert("TBLPPKNOTISPERBICARAAN");
			      stmt.executeUpdate(sqlNO); 
			     
			      conn.commit();	
			      
		    }catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
		    	
		    }
		    finally {
		      if (db != null) db.close();
		    }
		   
		  }//close add mst rayuan
		
		//add Mst Rayuan
		public static void updateMstRayuan(Hashtable data) throws Exception{
			
			Connection conn = null;
		    Db db = null;
		    String sqlMST = "";
		    
		    try
		    {
		    	  db = new Db();
		    	  conn = db.getConnection();
				  conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();
		    	
			      String id_notisobmst = (String)data.get("id_notisobmst");
			      String jenis_status = (String)data.get("jenis_status");
			      String jenis_serah = (String)data.get("jenis_serah");
			      String catatan = (String)data.get("catatan");
			      String id_penghantar = (String)data.get("id_penghantar");
			      String daftar_pos = (String)data.get("daftar_pos");
			      String nama_penghantar = (String)data.get("nama_penghantar");
			     
			      
			      //11122009
			      String _tarikhS = (String)data.get("tarikhS");
			      String tarikhS = "to_date('" + _tarikhS + "','dd/MM/yyyy')";
			   
			      
			      //id login kemaskini
			      String id_kemaskini = (String)data.get("id_kemaskini");
			  
			      SQLRenderer rMST = new SQLRenderer();
			      rMST.update("id_notisobmst",id_notisobmst);
			      rMST.add("status_serah", jenis_status);
			      rMST.add("jenis_serah", jenis_serah);
			      rMST.add("catatan", catatan);
			      rMST.add("nama_penghantar_notis", nama_penghantar);
			      rMST.add("id_penghantarnotis", id_penghantar);
			      rMST.add("no_surat_daftar", daftar_pos);
			      rMST.add("id_kemaskini",id_kemaskini);
			      
			      //11122009
			      rMST.add("tarikh_serahan",rMST.unquote(tarikhS));
			      
			      rMST.add("tarikh_kemaskini",rMST.unquote("sysdate"));
			      sqlMST = rMST.getSQLUpdate("TBLPPKNOTISOBMST");
			      stmt.executeUpdate(sqlMST);
			      
			      conn.commit();	
			      
		    }catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
		    	
		    }
		    finally {
		      if (db != null) db.close();
		    }
		   
		  }//close update mst rayuan
		  //-- 02122009
		
	/*				*
	 * 				*
	 * [SEKSYEN 17]	*
	 *				*	 
	 *				* 		
	 *				*/	
			
			
			//list default seksyen 17
			public static void setListDefaultSek17(String usid)throws Exception {
			    Db db = null;
			    listDefaultSek17.clear();
			    String sql = "";
			    String sqlx = "";
			    String idpermohonan = "";
			    
			    try {
			    	
			    	db = new Db();
			    	Statement stmt = db.getStatement();
		
			      
			    //SYARAT
			      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
			      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
			      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI, "
			      + " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN WHERE id_permohonan = ms.id_permohonan AND id_status = '21' AND tarikh_kemaskini < SYSDATE - 14)AS flag"
			      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
			      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
			      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
				  
				   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				  
				  sql += " ) " 
			      + " AND P.ID_STATUS = S.ID_STATUS "
			      + " AND P.ID_FAIL = F.ID_FAIL(+) "
			      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
			      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
			      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
			      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
			      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
			      + " AND (P.ID_STATUS=21 OR P.ID_STATUS=64 OR P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180 OR P.ID_STATUS=25) "
			      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
			      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
			      + " AND STA.AKTIF = '1' "
			      + " AND P.ID_STATUS <> '999' " 
			      + " AND P.SEKSYEN = '17' "
			      + " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
			      //+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
			      		      
			      sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
			      
			      ResultSet rs = stmt.executeQuery(sql);
			      
			      Hashtable h;
			      int bil = 1;

			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  	h.put("bil", bil);
						h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
						h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
						h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
						h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
						h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
						h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
						h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
						h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
						h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
						h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
						h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
						listDefaultSek17.addElement(h);
						bil++;	
			      }
			      //return list default;
			    } finally {
			      if (db != null) db.close();
			    }
			    
			  }//close list default
			
			//CARIAN seksyen 17
			public static void setCarianFailSek17(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid)throws Exception {
			    Db db = null;
			    
			    listCarianSek17.clear();
			    String sql = "";
			    String sqlx = "";
			    String idpermohonan = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
		      
				      String chkDataFail = noFail.trim();
				      String chkDataPemohon = namaPemohon.trim();
				      String chkDataSimati = namaSimati.trim();
				      String chkDataIcSimati = icSimati.trim();
				      String chkDataJenisKp = JenisIc;
				      
				      //SYARAT
				      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
				      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
				      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI, "
				      + " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN WHERE id_permohonan = ms.id_permohonan AND id_status = '21' AND tarikh_kemaskini < SYSDATE - 14)AS flag"
				      + " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
				      + " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
				      + " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "' ";
					  
					  
					   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
					  
					  sql +=" ) " 
				      + " AND P.ID_STATUS = S.ID_STATUS "
				      + " AND P.ID_FAIL = F.ID_FAIL(+) "
				      + " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
				      + " AND P.ID_DAERAHMHN = D.ID_DAERAH "
				      + " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
				      + " AND M.ID_SIMATI = MS.ID_SIMATI " 
				      + " AND P.ID_PEMOHON = PP.ID_PEMOHON "
				      + " AND (P.ID_STATUS=21 OR P.ID_STATUS=64 OR P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180 OR P.ID_STATUS=25) "
				      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
				      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
				      + " AND STA.AKTIF = '1' "
				      + " AND P.ID_STATUS <> '999' " 
				      + " AND P.SEKSYEN = '17' "
				      + " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
				      //+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
			      
				      //NO FAIL
				      if(noFail != null)
				      	{
				    	  if(!noFail.trim().equals("")) {
								sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + chkDataFail.toUpperCase() + "%'";
							}
				      	}//close if nofail
				      
				      //NAMA PEMOHON
				      if (namaPemohon != "") {
							if (!namaPemohon.trim().equals("")) {
								sql = sql + " AND UPPER(PP.NAMA_PEMOHON) LIKE '%" + chkDataPemohon.toUpperCase() + "%'";
							}
						}//close if pemohon
				      
				      //NAMA SIMATI
				      if (namaSimati != "") {
							if (!namaSimati.trim().equals("")) {
								sql = sql + " AND UPPER(M.NAMA_SIMATI) LIKE '%" + chkDataSimati.toUpperCase() + "%'";
							}
						}//close if nama simati
				   	  
				      //IC SIMATI
				      if (icSimati != "") {
						   if (!icSimati.trim().equals("")) {
								if (chkDataJenisKp.equals("1")){
									sql = sql + " AND UPPER(M.NO_KP_BARU) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
								}
								else if (chkDataJenisKp.equals("2")){
									sql = sql + " AND UPPER(M.NO_KP_LAMA) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
								}
								else if (chkDataJenisKp.equals("3")){
									sql = sql + " AND UPPER(M.NO_KP_LAIN) LIKE '%" + chkDataIcSimati.toUpperCase() + "%'";
								}
					    	}
						}//close if ic simati  
				      
				      //sorting
				      sql += " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
				      
				    
				      ResultSet rs = stmt.executeQuery(sql);
				      
				      Hashtable h;
				      int bil = 1;

				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  	h.put("bil", bil);
							h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
							h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
							h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
							h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
							h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
							h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
							h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
							h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
							h.put("id_simati", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
							h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
							h.put("flag", rs.getString("flag")==null?"":rs.getString("flag"));
							listCarianSek17.addElement(h);
							bil++;
			      }
			      //return list;
			    } finally {
			      if (db != null) db.close();
			    }
			    
			  }//close carian
			
			
			//simpan Semakan K2 Seksyen 17
			public static void simpanSemakanK2Sek17(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran, String jumlahbayaran) throws Exception
			  {
				
			    Db db = null;
			    
			    String sql = "";
			    String sqlBayaran = "";
			    String sqlP = "";
			    String sql5 = "";
			    String sql6 = "";
			    
			    try
			    {
			    	db = new Db();
			    	Statement stmt = db.getStatement();
			    	
			    	long id_semakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
			 	    
			 	    //id login
			 	    String id_masuk = (String)data.get("id_masuk");
			 	    
			 	    String tarikhTerimaRayuan = (String)data.get("tarikhTerimaRayuan");
			 	    
			    	long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
				 
				    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
				    String tarikh_rayuan = "to_date('" + tarikhTerimaRayuan + "','dd/MM/yyyy')";
				    
				    //insert data into tblsemakanhantar
				    sql = "INSERT INTO Tblsemakanhantar " +
					"(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
					"VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_masuk+"', sysdate) ";
				    stmt.executeUpdate(sql);
				    
				    //insert into tblppkbayaran
				    if (idsemakan.equals("124")) 
				    {
					     
					      SQLRenderer r1 = new SQLRenderer();
					      r1.add("id_bayaran", idBayaran);
					      r1.add("id_permohonan", idpermohonan);
					      r1.add("id_jenisbayaran",3);
					      r1.add("no_resit",noresit);
					      r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
					      r1.add("jumlah_bayaran",50);
					      r1.add("id_masuk",id_masuk); 
					      r1.add("tarikh_masuk",r1.unquote("sysdate")); 
					      sqlBayaran = r1.getSQLInsert("tblppkbayaran");
					      stmt.executeUpdate(sqlBayaran);
					}
				    
				    //insert into tblppkbayaran
				    if (idsemakan.equals("125")) 
				    {
					     
					      SQLRenderer rB = new SQLRenderer();
					      rB.add("id_bayaran", idBayaran);
					      rB.add("id_permohonan", idpermohonan);
					      rB.add("id_jenisbayaran",4);
					      rB.add("no_resit",noresit);
					      rB.add("tarikh_bayaran",rB.unquote(tarikh_bayaran));
					      rB.add("jumlah_bayaran",jumlahbayaran);
					      rB.add("id_masuk",id_masuk); 
					      rB.add("tarikh_masuk",rB.unquote("sysdate")); 
					      sqlBayaran = rB.getSQLInsert("tblppkbayaran");
					      stmt.executeUpdate(sqlBayaran);
					}
				    
				    	//change status [21] to permohonan rayuan[64]
				    	int status_pr = 64;
				    	
				    	SQLRenderer rP = new SQLRenderer();
				    	rP.update("id_permohonan", idpermohonan);
				    	rP.add("id_status", status_pr);
				    	rP.add("id_kemaskini",id_masuk); 
				    	rP.add("tarikh_rayuan",rP.unquote(tarikh_rayuan)); 
				     	rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
				     	sqlP = rP.getSQLUpdate("tblppkpermohonan");
				     	stmt.executeUpdate(sqlP);
				    
			    }//close try 
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close simpanSemakanK2

			
			
			//delete semakan k2 checkbox
			public static void semakanDeleteSek17(String id_permohonan) throws Exception {
				
			    Db db = null;
			    String sql1 = "";
			    
			    try {
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		sql1 =  "DELETE FROM tblsemakanhantar WHERE id_permohonan = '" +id_permohonan+ "'";
			    		sql1 += " AND id_semakansenarai = ANY(121,122,123,124,125,126) ";      
			    		stmt.executeUpdate(sql1);
			      
			    } finally {
			      if (db != null) db.close();
			    }
			  
			}//delete semakan k2 checkbox
			
	
			//update Semakan K2 Seksyen 17
			public static void updateSemakanK2Sek17(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran, String jumlahbayaran, String deleteF) throws Exception{
				
			    Db db = null;
			    
			    String sql = "";
			    String sqlP = "";
			    String sqlBayaran = "";
			    
			    try
			    {
			    
			    	db = new Db();
			    	Statement stmt = db.getStatement();
			    	long id_semakanhantar = DB.getNextID("TBLSEMAKANHANTAR_SEQ");
			 	    
			 	    //id login
			 	    String id_login = (String)data.get("id_kemaskini");
			 	    
			 	    String tarikhTerimaRayuan = (String)data.get("tarikhTerimaRayuan");
			 	    String tarikh_rayuan = "to_date('" + tarikhTerimaRayuan + "','dd/MM/yyyy')";
			 	    
				    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
							    
				    //insert data into tblsemakanhantar
				    sql = "INSERT INTO Tblsemakanhantar " +
						  "(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
						  "VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_login+"', sysdate) ";
				    	
				    stmt.executeUpdate(sql);
				    
				    String id_update_bayaranF = (String)data.get("update_bayaranF");
				    String id_update_bayaranD = (String)data.get("update_bayaranD");

				    
				    if (idsemakan.equals("124")) 
					   {
							SQLRenderer r1 = new SQLRenderer();
							r1.update("id_bayaran", id_update_bayaranF);
							r1.add("no_resit",noresit);
							r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
							r1.add("id_kemaskini",id_login); 
							r1.add("tarikh_kemaskini",r1.unquote("sysdate")); 
							sqlBayaran = r1.getSQLUpdate("tblppkbayaran");
							stmt.executeUpdate(sqlBayaran);
						}
				   
				    if (idsemakan.equals("125")) 
					    {
				    	
						 	SQLRenderer rB = new SQLRenderer();
						 	rB.update("id_bayaran", id_update_bayaranD);
						 	rB.add("no_resit",noresit);
						 	rB.add("tarikh_bayaran",rB.unquote(tarikh_bayaran));
						 	rB.add("jumlah_bayaran",jumlahbayaran);
						 	rB.add("id_kemaskini",id_login); 
						 	rB.add("tarikh_kemaskini",rB.unquote("sysdate")); 
						 	sqlBayaran = rB.getSQLUpdate("tblppkbayaran");
						 	stmt.executeUpdate(sqlBayaran);
						 
					    }
					    
				    //update tarikh rayuan
			    	SQLRenderer rP = new SQLRenderer();
			    	  rP.update("id_permohonan", idpermohonan);
			    	  rP.add("id_kemaskini",id_login); 
			    	  rP.add("tarikh_rayuan",rP.unquote(tarikh_rayuan)); 
			     	  rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
			     	sqlP = rP.getSQLUpdate("tblppkpermohonan");
			     	stmt.executeUpdate(sqlP);
			     	
			     	
			    }//close try 
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close updateSemakanK2 Seksyen 17
			
			
		//simpan Maklumat PP seksyen 17
		public static void simpanMaklumatPPSek17(Hashtable data) throws Exception
			  {
			
				Connection conn = null;
			    Db db = null;
			    String sql = "";
			    String sql1 = "";
			    try
			    {
			    	
			    	db = new Db();
				      conn = db.getConnection();
					  conn.setAutoCommit(false);
				      Statement stmt = db.getStatement();
				    	
				      //validation insert/update
					  int Xdefault = (Integer)data.get("Xdefault");
					  String id_peguamVAL = (String)data.get("id_peguam");	
				    	
					  long id_peguam = 0;
					  
				      if(id_peguamVAL!=""){
				    	  String idpg = (String)data.get("id_peguam");
				    	  id_peguam = Long.parseLong(idpg);
				      }else{
				    	  id_peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
				      }
					  
				      //generate other table id	
				     
				      long id_perayu = DB.getNextID("TBLPPKPERAYU_SEQ");
				      long id_rayuan = DB.getNextID("TBLPPKRAYUAN_SEQ");
				     
				      //id	
				      String id_perintah = (String)data.get("id_perintah");
				      String id_permohonan = (String)data.get("id_permohonan");
				      String id_pemohon = (String)data.get("id_pemohon");
				      String id_ob = (String)data.get("id_ob");
				      
				      
				      //data perayu
				      String noKPBaru = (String)data.get("txtNoKPBaru");
				      String noKPLama = (String)data.get("txtNoKPLama");
				      String noKPLain = (String)data.get("txtNoKPLain");
				      String jeniskp = (String)data.get("jeniskp");
				      String namaPerayu = (String)data.get("txtNamaPerayu");
				      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
				      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
				      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
				      String poskodPerayu = (String)data.get("txtPoskodPerayu");
				      String bandarPerayu = (String)data.get("txtBandarPerayu");
				      String negeriPerayu = (String)data.get("socNegeriPerayu");
				     
				      //data peguam
				      String namaFirma = (String)data.get("txtNamaFirma");
				      String noRujukan = (String)data.get("txtNoRujukan");
					  String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
					  String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
					  String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
					  String poskodPeguam = (String)data.get("txtPoskodPeguam");
					  String bandarPeguam = (String)data.get("txtBandarPeguam");
					  String noTelefon = (String)data.get("txtNoTelefon");
					  String noFaks = (String)data.get("txtNoFaks");
					  String email = (String)data.get("txtEmel");
					  String negeriPeguam = (String)data.get("socNegeriPeguam");
				    	
					  String tarikhrayuan = (String)data.get("tarikh_rayuan");
					  String tarikh_rayuan = "to_date('" + tarikhrayuan + "','dd/MM/yyyy')";
					  
				      //id login masuk
				      String id_masuk = (String)data.get("id_masuk");
				      
				      
				      //insert
				      if(Xdefault==2){
				      
				    	  //add data to Tblppkpeguam	      
				    	  SQLRenderer r = new SQLRenderer();
				    	  r.add("id_peguam", id_peguam);
				    	  r.add("nama_firma", namaFirma);
				    	  r.add("no_rujukan_firma", noRujukan);
				    	  r.add("alamat1", alamatPeguam1); 	
				    	  r.add("alamat2", alamatPeguam2);
				    	  r.add("alamat3", alamatPeguam3);
				    	  r.add("poskod", poskodPeguam);
				    	  r.add("id_bandar", bandarPeguam);
				    	  r.add("no_tel", noTelefon);
				    	  r.add("no_fax", noFaks);
				    	  r.add("emel", email);
				    	  r.add("id_negeri", negeriPeguam);
				    	  r.add("tarikh_masuk", r.unquote("sysdate"));
				    	  r.add("id_masuk",id_masuk);	      
				    	  sql = r.getSQLInsert("Tblppkpeguam");
				    	  stmt.executeUpdate(sql);
				        
				      }
				      
				      //update
				      else if(Xdefault==1){
				    	
				    	  
				    	  SQLRenderer r = new SQLRenderer();
				    	  r.update("id_peguam", id_peguam);
					      r.add("no_rujukan_firma", noRujukan);
					      r.add("alamat1", alamatPeguam1); 	
					      r.add("alamat2", alamatPeguam2);
					      r.add("alamat3", alamatPeguam3);
					      r.add("poskod", poskodPeguam);
					      r.add("id_bandar", bandarPeguam);
					      r.add("no_tel", noTelefon);
					      r.add("no_fax", noFaks);
					      r.add("emel", email);
					      r.add("id_negeri", negeriPeguam);
					      r.add("tarikh_kemaskini", r.unquote("sysdate"));
					      r.add("id_kemaskini",id_masuk);	
					      sql = r.getSQLUpdate("Tblppkpeguam");
					      stmt.executeUpdate(sql);
				    	  
				      }
				      
				      if(Xdefault==2)
				      {
				    	  //add data to tblppkpeguampemohon with id pemohon
				    	  SQLRenderer rMST = new SQLRenderer();
				    	  rMST.add("id_peguam", id_peguam);	
				    	  rMST.add("id_pemohon", id_pemohon);	
				    	  rMST.add("id_masuk",id_masuk);
				    	  rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
				    	  sql1 = rMST.getSQLInsert("Tblppkpeguampemohon");
				    	  stmt.executeUpdate(sql1);
				      }
				      
				      //add data to tblppkrayuan
				      SQLRenderer rR = new SQLRenderer();
				      rR.add("id_rayuan", id_rayuan);
				      rR.add("id_permohonan", id_permohonan); 	
				      rR.add("id_perintah", id_perintah); 	
				      rR.add("tarikh_mohon", rR.unquote(tarikh_rayuan)); 
				      rR.add("id_masuk",id_masuk);
				      rR.add("tarikh_masuk", rR.unquote("sysdate"));	      
				      sql = rR.getSQLInsert("Tblppkrayuan");
				      stmt.executeUpdate(sql);
				      
				      //add data to tblppkperayu
				      SQLRenderer r1 = new SQLRenderer();
				      r1.add("id_perayu", id_perayu);
				      r1.add("id_rayuan", id_rayuan); 
				      r1.add("no_kp_baru", noKPBaru);
				      r1.add("no_kp_lama", noKPLama);
				      r1.add("no_kp_lain", noKPLain);
				      r1.add("jenis_kp",jeniskp);
				      r1.add("nama_perayu", namaPerayu);
				      r1.add("alamat_1", alamatPerayu1);
				      r1.add("alamat_2", alamatPerayu2);
				      r1.add("alamat_3", alamatPerayu3);
				      r1.add("poskod", poskodPerayu);
				      r1.add("id_bandar", bandarPerayu);
				      r1.add("id_negeri", negeriPerayu);	      
				      r1.add("id_masuk",id_masuk);
				      r1.add("tarikh_masuk", r1.unquote("sysdate"));	      
				      sql = r1.getSQLInsert("Tblppkperayu");
				      stmt.executeUpdate(sql);
				      
				      //add data to tblppkpeguamperayu
				      SQLRenderer rPe = new SQLRenderer();
				      rPe.add("id_peguam", id_peguam); 	
				      rPe.add("id_perayu", id_perayu); 
				      rPe.add("id_masuk",id_masuk);
				      rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
				      sql = rPe.getSQLInsert("Tblppkpeguamperayu");
				      stmt.executeUpdate(sql);

				      //[add perayu] = [update tblppkob]
				      SQLRenderer rOB = new SQLRenderer();
				      rOB.update("id_ob", id_ob);
				      rOB.add("id_perayu", id_perayu);
				      rOB.add("alamat1_surat", alamatPerayu1); 	
				      rOB.add("alamat2_surat", alamatPerayu2);
				      rOB.add("alamat3_surat", alamatPerayu3);
				      rOB.add("poskod_surat", poskodPerayu);
				      rOB.add("id_bandarsurat", bandarPerayu);
				      rOB.add("id_negerisurat", negeriPerayu);
				      rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
				      rOB.add("id_kemaskini",id_masuk);	      
				      sql = rOB.getSQLUpdate("Tblppkob");
				      stmt.executeUpdate(sql);
				      
				      
				      conn.commit();
			      
			   }catch (SQLException se) { 
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
			    }
			    finally {
			      if (db != null) db.close();
			    }
			   
			  }//close simpan Maklumat PP Seksyen 17
	
			
		//update Maklumat PP Seksyen 17
		public static void updatePerayuSek17(Hashtable data) throws Exception{
			
			    Db db = null;
			    String sql = "";
			    
			    try
			    {
			    		
			    	  db = new Db();
				      Statement stmt = db.getStatement();
				      
				      
			    	  //id	
				      String id_permohonan = (String)data.get("id_permohonan");
				      String id_pemohon = (String)data.get("id_pemohon");
				      String id_perayu = (String)data.get("id_perayu");
				      String id_peguam = (String)data.get("id_peguam");
				      
				      //data perayu
				      String noKPBaru = (String)data.get("txtNoKPBaru");
				      String noKPLama = (String)data.get("txtNoKPLama");
				      String noKPLain = (String)data.get("txtNoKPLain");
				      String namaPerayu = (String)data.get("txtNamaPerayu");
				      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
				      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
				      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
				      String poskodPerayu = (String)data.get("txtPoskodPerayu");
				      String bandarPerayu = (String)data.get("txtBandarPerayu");
				      String negeriPerayu = (String)data.get("socNegeriPerayu");
				     
				      //id login kemaskini
				      String id_kemaskini = (String)data.get("id_kemaskini");
				       
				      //update table perayu    
				      SQLRenderer r = new SQLRenderer();
				      r.update("id_perayu", id_perayu);
				      r.add("no_kp_baru", noKPBaru);
				      r.add("no_kp_lama", noKPLama);
				      r.add("no_kp_lain", noKPLain);
				      r.add("nama_perayu", namaPerayu);
				      r.add("alamat_1", alamatPerayu1);
				      r.add("alamat_2", alamatPerayu2);
				      r.add("alamat_3", alamatPerayu3);
				      r.add("poskod", poskodPerayu);
				      r.add("id_bandar", bandarPerayu);
				      r.add("id_negeri", negeriPerayu);	      
				      r.add("id_kemaskini",id_kemaskini);
				      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
				      sql = r.getSQLUpdate("Tblppkperayu");
				      stmt.executeUpdate(sql);
			      
			  }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
		}//close update perayu Seksyen 17
		
		
			
		//update table OB Seksyen17
		public static void updatetableOBSek17(Hashtable data) throws Exception{
			
			    Db db = null;
			    String sql = "";
			    
			    try
			    {	    	
			    	  db = new Db();
			    	  Statement stmt = db.getStatement();
			    	  
			    	  //id	
				      String id_perayu = (String)data.get("id_perayu");
				    
				      //data perayu
				      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
				      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
				      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
				      String poskodPerayu = (String)data.get("txtPoskodPerayu");
				      String bandarPerayu = (String)data.get("txtBandarPerayu");
				      String negeriPerayu = (String)data.get("socNegeriPerayu");
				
				      //id login kemaskini
				      String id_kemaskini = (String)data.get("id_kemaskini");
				 
				      //[add perayu] = [update tblppkob]
				      SQLRenderer rOB = new SQLRenderer();
			      		rOB.update("id_perayu", id_perayu);
			      		rOB.add("alamat1_surat", alamatPerayu1); 	
			      		rOB.add("alamat2_surat", alamatPerayu2);
			      		rOB.add("alamat3_surat", alamatPerayu3);
			      		rOB.add("poskod_surat", poskodPerayu);
			      		rOB.add("id_bandarsurat", bandarPerayu);
			      		rOB.add("id_negerisurat", negeriPerayu);
			      		rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
			      		rOB.add("id_kemaskini",id_kemaskini);	      
			      		sql = rOB.getSQLUpdate("Tblppkob");
			      	  stmt.executeUpdate(sql);
			      
			    }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close update table OB Seksyen 17	
			
			
			//insert Maklumat ob Seksyen 17
			public static void inserttableOBSek17(Hashtable data) throws Exception{
				
			    Db db = null;
			    String sql = "";
			    String sqlx = "";
			    
			    try
			    {	    	
			    	  db = new Db();
			    	  Statement stmt = db.getStatement();
			    	  
			    	  
			    	  //id	
				      String id_perayu = (String)data.get("id_perayu");
				      String id_ob = (String)data.get("id_ob");
				      
				      //data perayu
				      String alamatPerayu1 = (String)data.get("txtAlamatPerayu1");
				      String alamatPerayu2 = (String)data.get("txtAlamatPerayu2");
				      String alamatPerayu3 = (String)data.get("txtAlamatPerayu3");
				      String poskodPerayu = (String)data.get("txtPoskodPerayu");
				      String bandarPerayu = (String)data.get("txtBandarPerayu");
				      String negeriPerayu = (String)data.get("socNegeriPerayu");
				
				      //id login kemaskini
				      String id_kemaskini = (String)data.get("id_kemaskini");
				 
				      	//-- buang id perayu dlm selection ob
				      	//[add perayu] = [update tblppkob]
				      	SQLRenderer rOB = new SQLRenderer();
			      		rOB.update("id_perayu", id_perayu);
			      		rOB.add("id_perayu", ""); 	
			      		rOB.add("tarikh_kemaskini", rOB.unquote("sysdate"));
			      		rOB.add("id_kemaskini",id_kemaskini);	      
			      		sql = rOB.getSQLUpdate("Tblppkob");
			      	    stmt.executeUpdate(sql);
			      
			      	    //-- tambah id perayu dlm selection ob
			      	    SQLRenderer rOBx = new SQLRenderer();
			      	  	rOBx.update("id_ob", id_ob);
			      	  	rOBx.add("alamat1_surat", alamatPerayu1); 	
			      		rOBx.add("alamat2_surat", alamatPerayu2);
			      		rOBx.add("alamat3_surat", alamatPerayu3);
			      		rOBx.add("poskod_surat", poskodPerayu);
			      		rOBx.add("id_bandarsurat", bandarPerayu);
			      		rOBx.add("id_negerisurat", negeriPerayu);
			      	  	rOBx.add("id_perayu", id_perayu); 	
			      	  	rOBx.add("tarikh_kemaskini", rOBx.unquote("sysdate"));
			      	  	rOBx.add("id_kemaskini",id_kemaskini);	      
			      	  	sqlx = rOBx.getSQLUpdate("Tblppkob");
			      	    stmt.executeUpdate(sqlx);
			      	  
			      	  
			    }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close insert table OB Seksyen 17
			
			
			//simpan Data Rayuan [update] Seksyen 17
			public static void simpanDataRayuanSek17(Hashtable data) throws Exception{
				
			    Db db = null;
			    String sql = "";
			    
			    try
			    {
			    	
			    	  db = new Db();
			    	  Statement stmt = db.getStatement();
			    	 
			    	  //id	
				      String id_rayuan = (String)data.get("id_rayuan");
				  
				      //data perayu
				      String tarikh_rayuan = (String)data.get("tarikh_rayuan");
				      String perkara_rayu = (String)data.get("perkara_rayu");
				      String alasan_rayuan = (String)data.get("alasan_rayuan");
				      
				      //id login kemaskini
				      String id_kemaskini = (String)data.get("id_kemaskini");
				 
				      String TR = "to_date('" + tarikh_rayuan + "','dd/MM/yyyy')";
						
				      //update table perayu    
				      SQLRenderer r = new SQLRenderer();
				      r.update("id_rayuan", id_rayuan);
				      r.add("perkara_rayu", perkara_rayu);
				      r.add("alasan_rayuan", alasan_rayuan);
				      r.add("tarikh_mohon", r.unquote(TR));	   
				      r.add("id_kemaskini",id_kemaskini);
			      	  r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
			      	  sql = r.getSQLUpdate("Tblppkrayuan");
			      	  stmt.executeUpdate(sql);
			     	      
			    }//close try 
			    
			    finally {
			      if (db != null) db.close();
			    }//close finally
			   
			  }//close simpan Data Rayuan [update] Seksyen 17
		
			
		//simpan Serahan K3 Seksyen 17
		public static void simpanSerahanK3Sek17(HttpSession session,Hashtable data) throws Exception{
			
				Connection conn = null;
			    Db db = null;
			    String sql = "";
			    String sql5 = "";
			    String sql6 = "";
			    String sqlMST = "";
			    String sqlNO = "";
			    
			    try
			    {
			    	  db = new Db();
			    	  conn = db.getConnection();
					  conn.setAutoCommit(false);
				      Statement stmt = db.getStatement();
			    	
				      //-- 02122009
				      String idperbicaraan = (String)data.get("idperbicaraan");
				      String jenis_status = (String)data.get("jenis_status");
				      String jenis_serah = (String)data.get("jenis_serah");
				      String catatan = (String)data.get("catatan");
				      String id_penghantar = (String)data.get("id_penghantar");
				      String daftar_pos = (String)data.get("no_surat_daftar");
				      String nama_penghantar = (String)data.get("nama_penghantar");
				      //-- 02122009
				      
				      //11122009
				      String _tarikhS = (String)data.get("tarikhS");
				      String tarikhS = "to_date('" + _tarikhS + "','dd/MM/yyyy')";
				      
			    	  //id	
				      String _id_rayuan = (String)data.get("id_rayuan");
				      String id_permohonan = (String)data.get("id_permohonan");
				      String id_fail = (String)data.get("id_fail");
				      String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
				      
				      //data perayu
				      String tarikh_serahan = (String)data.get("txdTarikhSerahan");
				      String nama_penerima = (String)data.get("txtNamaPenerima");
				      String alamatSerah1 = (String)data.get("alamatSerah1");
				      String alamatSerah2 = (String)data.get("alamatSerah2");
				      String alamatSerah3 = (String)data.get("alamatSerah3");
				      String poskod = (String)data.get("txtPoskodSerah");
				      String bandar = (String)data.get("txtBandarSerah");
				      String negeri = (String)data.get("idnegeri");
				      
				      String bayaranRekod = (String)data.get("bayaranRekod");
				      
				      //id login kemaskini
				      String id_masuk = (String)data.get("id_masuk");
				 
				      String TS = "to_date('" + tarikh_serahan + "','dd/MM/yyyy')";
						
				      String flag_serahan = "1";   
				      
			      
				      SQLRenderer r = new SQLRenderer();
				      r.add("id_rayuan", _id_rayuan);
				      r.add("nama", nama_penerima);
				      r.add("alamat1", alamatSerah1);
				      r.add("alamat2", alamatSerah2);
				      r.add("alamat3", alamatSerah3);
				      r.add("poskod", poskod);
				      r.add("id_bandar", bandar);
				      r.add("id_negeri", negeri);
				      r.add("flag_serahan", flag_serahan);
				      r.add("tarikh_borangk3", r.unquote(TS));	   
				      r.add("id_masuk",id_masuk);
				      r.add("tarikh_masuk", r.unquote("sysdate"));	      
				      sql = r.getSQLInsert("Tblppkserahanrayuan");
				      stmt.executeUpdate(sql);
			     	      
				      SQLRenderer r1 = new SQLRenderer();
				      r1.add("id_permohonan", id_permohonan);
				      r1.add("id_jenisbayaran",18);
				      r1.add("jumlah_bayaran",bayaranRekod);
				      r1.add("id_masuk",id_masuk); 
				      r1.add("tarikh_masuk",r1.unquote("sysdate")); 
				      sql = r1.getSQLInsert("tblppkbayaran");
				      stmt.executeUpdate(sql);
			     	      
				      //change status 64 --> 163[mnuggu pnerimaan brg k1]
				      int status_k1 = 163;
			    	/*
				      SQLRenderer rP = new SQLRenderer();
				      rP.update("id_permohonan", id_permohonan);
				      rP.add("id_status", status_k1);
				      rP.add("id_kemaskini",id_masuk); 
				      rP.add("tarikh_kemaskini",rP.unquote("sysdate")); 
				      sql = rP.getSQLUpdate("tblppkpermohonan");
				      stmt.executeUpdate(sql);
			      
			     	
				      //update n add tblrujsuburusanstatus
				      SQLRenderer r6 = new SQLRenderer();
				      r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
				      r6.add("AKTIF",0);
				      r6.add("ID_KEMASKINI",id_masuk);
				      r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
				      sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
				      stmt.executeUpdate(sql6);	 
			      
			    
				      // 472 = menunggu penerimaan borang k1
				      SQLRenderer r5 = new SQLRenderer();
				      r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				      r5.add("ID_PERMOHONAN",id_permohonan);
				      r5.add("ID_FAIL",id_fail);
				      r5.add("ID_SUBURUSANSTATUS",472);
				      r5.add("AKTIF",1);
				      r5.add("ID_MASUK",id_masuk);
				      r5.add("ID_KEMASKINI",id_masuk);
				      r5.add("TARIKH_MASUK",r5.unquote("sysdate"));	
				      r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				      sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				      stmt.executeUpdate(sql5);		
				      */
				      
				      
				      //-- 02122009
				      long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ"); 
				      
				      SQLRenderer rMST = new SQLRenderer();
				      rMST.add("id_notisobmst",id_notisobmst);
				      rMST.add("status_serah", jenis_status);
				      rMST.add("jenis_serah", jenis_serah);
				      rMST.add("catatan", catatan);
				      rMST.add("nama_penghantar_notis", nama_penghantar);
				      rMST.add("id_penghantarnotis", id_penghantar);
				      rMST.add("no_surat_daftar", daftar_pos);
				      rMST.add("id_masuk",id_masuk);
				      rMST.add("tarikh_masuk",rMST.unquote("sysdate"));
				      
				      //11122009
				      rMST.add("tarikh_serahan",rMST.unquote(tarikhS));
				      
				      sqlMST = rMST.getSQLInsert("TBLPPKNOTISOBMST");
				      stmt.executeUpdate(sqlMST);
				      
				      // + flag jenis notis [1 = rayuan]
				      String flag = "1";
				      
				      SQLRenderer rNO = new SQLRenderer();
				      rNO.add("id_notisobmst", id_notisobmst);
				      rNO.add("id_perbicaraan", idperbicaraan);
				      rNO.add("flag_jenis_notis", flag);
				      rNO.add("id_masuk",id_masuk);
				      rNO.add("tarikh_masuk",rNO.unquote("sysdate"));
				      sqlNO = rNO.getSQLInsert("TBLPPKNOTISPERBICARAAN");
				      stmt.executeUpdate(sqlNO); 
				      
				      //-- 02122009
				      
				      conn.commit();
				      
				      FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,status_k1+"","472",id_fail);
			     	
				      
			    }catch (SQLException se) { 
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
			    }
			    finally {
			      if (db != null) db.close();
			    }
			   
			  }//close simpan Serahan K3 Seksyen 17	
		
			
			//simpan Tambahan Peguam Seksyen 17
			public static void simpanTambahanPeguamSek17(Hashtable data) throws Exception{
					
					Connection conn = null;
				    Db db = null;
				    String sql = "";
				    
				    try
				    {
				    	
				    	  db = new Db();
				    	  conn = db.getConnection();
						  conn.setAutoCommit(false);
				    	  Statement stmt = db.getStatement();
				    	  
				    	  //validation insert/update
						  int Xdefault = (Integer)data.get("Xdefault");
						  String id_peguamVAL = (String)data.get("id_peguam");	
					    	
						  long id_peguam = 0;
						  
					      if(id_peguamVAL!=""){
					    	  String idpg = (String)data.get("id_peguam");
					    	  id_peguam = Long.parseLong(idpg);
					      }else{
					    	  id_peguam = DB.getNextID("TBLPPKPEGUAM_SEQ");
					      }
				      
				     
					      //id	
					      String id_permohonan = (String)data.get("id_permohonan");
					      String id_pemohon = (String)data.get("id_pemohon");
					      String id_perayu = (String)data.get("id_perayu");
				    	   
					      //data peguam
					      String namaFirma = (String)data.get("txtNamaFirma");
					      String noRujukan = (String)data.get("txtNoRujukan");
					      String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
					      String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
					      String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
					      String poskodPeguam = (String)data.get("txtPoskodPeguam");
					      String bandarPeguam = (String)data.get("txtBandarPeguam");
					      String noTelefon = (String)data.get("txtNoTelefon");
					      String noFaks = (String)data.get("txtNoFaks");
					      String email = (String)data.get("txtEmel");
					      String negeriPeguam = (String)data.get("socNegeriPeguam");
				    	
					      //id login masuk
					      String id_masuk = (String)data.get("id_masuk");
				      
				      
				      
					      if(Xdefault==2){
					    	  
					    	  //add data to Tblppkpeguam
					    	  SQLRenderer r = new SQLRenderer();
					    	  r.add("id_peguam", id_peguam);
					    	  r.add("nama_firma", namaFirma);
					    	  r.add("no_rujukan_firma", noRujukan);
					    	  r.add("alamat1", alamatPeguam1); 	
					    	  r.add("alamat2", alamatPeguam2);
					    	  r.add("alamat3", alamatPeguam3);
					    	  r.add("poskod", poskodPeguam);
					    	  r.add("id_bandar", bandarPeguam);
					    	  r.add("no_tel", noTelefon);
					    	  r.add("no_fax", noFaks);
					    	  r.add("emel", email);
					    	  r.add("id_negeri", negeriPeguam);
					    	  r.add("tarikh_masuk", r.unquote("sysdate"));
					    	  r.add("id_masuk",id_masuk);	      
					    	  sql = r.getSQLInsert("Tblppkpeguam");
					    	  stmt.executeUpdate(sql);
					      }
				      
					      else if(Xdefault==1){
					    	
					    	  SQLRenderer r = new SQLRenderer();
					    	  r.update("id_peguam", id_peguam);
					    	  //r.add("nama_firma", namaFirma);
					    	  r.add("no_rujukan_firma", noRujukan);
					    	  r.add("alamat1", alamatPeguam1); 	
					    	  r.add("alamat2", alamatPeguam2);
					    	  r.add("alamat3", alamatPeguam3);
					    	  r.add("poskod", poskodPeguam);
					    	  r.add("id_bandar", bandarPeguam);
					    	  r.add("no_tel", noTelefon);
					    	  r.add("no_fax", noFaks);
					    	  r.add("emel", email);
					    	  r.add("id_negeri", negeriPeguam);
					    	  r.add("tarikh_kemaskini", r.unquote("sysdate"));
					    	  r.add("id_kemaskini",id_masuk);	      
					    	  sql = r.getSQLUpdate("Tblppkpeguam");
					    	  stmt.executeUpdate(sql);
				    	  
					      }
				      
					      
					      if(Xdefault==2)
					      {
					    	  //add data to tblppkpeguampemohon with id pemohon
					    	  SQLRenderer rMST = new SQLRenderer();
					    	  rMST.add("id_peguam", id_peguam);	
					    	  rMST.add("id_pemohon", id_pemohon);	
					    	  rMST.add("id_masuk",id_masuk);
					    	  rMST.add("tarikh_masuk", rMST.unquote("sysdate"));
					    	  sql = rMST.getSQLInsert("Tblppkpeguampemohon");
					    	  stmt.executeUpdate(sql);
					      }
				      
					      //add data to tblppkpeguamperayu
					      SQLRenderer rPe = new SQLRenderer();
					      rPe.add("id_peguam", id_peguam); 	
					      rPe.add("id_perayu", id_perayu); 
					      rPe.add("id_masuk",id_masuk);
					      rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
					      sql = rPe.getSQLInsert("Tblppkpeguamperayu");
					      stmt.executeUpdate(sql);

					      
					      conn.commit();	
					      
				    }catch (SQLException se) { 
				    	try {
				    		conn.rollback();
				    	} catch (SQLException se2) {
				    		throw new Exception("Rollback error:"+se2.getMessage());
				    	}
				    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
				    }
				    finally {
				      if (db != null) db.close();
				    }
				   
				  }//close simpan Tambahan Peguam Seksyen 17
			
			
		//update SuburusanS Fail Seksyen 17
		public static void updateSuburusanSFailSek17(HttpSession session,Hashtable data) throws Exception{
				
				Db db = null;
				String sql = "";
				String sql5 = "";
				String sql6 = "";
				
				try{
						db = new Db();
						Statement stmt = db.getStatement();
						
						
					   	//id	
						String id_permohonan = (String)data.get("id_permohonan");
						String id_masuk = (String)data.get("id_masuk");
						String id_fail = (String)data.get("id_fail");
						String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
						
						FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"64","305",id_fail);
				     	
						/* 	  
						//update n add tblrujsuburusanstatus
						SQLRenderer r6 = new SQLRenderer();
						r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
						r6.add("AKTIF",0);
						r6.add("ID_KEMASKINI",id_masuk);
						r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
						sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
						stmt.executeUpdate(sql6);	 
						      
						      
						SQLRenderer r5 = new SQLRenderer();
						r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
						r5.add("ID_PERMOHONAN",id_permohonan);
						r5.add("ID_FAIL",id_fail);
						r5.add("ID_SUBURUSANSTATUS",305);
						r5.add("AKTIF",1);
						r5.add("ID_MASUK",id_masuk);
						r5.add("ID_KEMASKINI",id_masuk);
						r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
						r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
						sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
						stmt.executeUpdate(sql5);
						*/
					      
				  }//close try 
					    
				  finally {
					      if (db != null) db.close();
					      
				  }//close finally
					   
			}//close update Suburusan SFail Seksyen 17
			
		
			//delete data peguam Seksyen 17
			public static void deleteDataPeguamSek17(String id_peguam) throws Exception {
				
				Connection conn = null;	    
				Db db = null;
				String sql = "";
				String sql2 = "";
				String sql3 = "";
						    
				try {
						    	
						      db = new Db();
							  conn = db.getConnection();
							  conn.setAutoCommit(false);
						      Statement stmt = db.getStatement();
						      
						      sql =  "DELETE FROM tblppkpeguamperayu WHERE id_peguam = " +id_peguam ;     				    
						      stmt.executeUpdate(sql);
						      
						      sql2 =  "DELETE FROM tblppkpeguampemohon WHERE id_peguam = " +id_peguam ;     				    
						      stmt.executeUpdate(sql2);
						      
						      sql3 =  "DELETE FROM tblppkpeguam WHERE id_peguam = " +id_peguam ;     				    
						      stmt.executeUpdate(sql3);
						      conn.commit();	
						      
						      
			    	}catch (SQLException se) { 
			    		try {
			    			conn.rollback();
			    		} catch (SQLException se2) {
			    			throw new Exception("Rollback error:"+se2.getMessage());
			    		}
			    		throw new Exception("Ralat : Masalah penyimpanan data ");
			    	}finally {
						 	if (db != null) db.close();
					 	}
			}
			//delete data peguam Seksyen 17
			
			
			//tambah Peguam Lama Seksyen 17
			public static void tambahPeguamLamaSek17(Hashtable data) throws Exception{
				
					Db db = null;
					String sql = "";
					
					try{
						    	
							db = new Db();
							Statement stmt = db.getStatement();
							
							
						    //id	
							String id_perayu = (String)data.get("id_perayu");
							String id_peguamlama = (String)data.get("id_peguamlama");
							      
							//id login kemaskini
							String id_login = (String)data.get("id_kemaskini");
							      
							//data peguam
							String noRujukan = (String)data.get("txtNoRujukan");
							String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
							String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
							String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
							String poskodPeguam = (String)data.get("txtPoskodPeguam");
							String bandarPeguam = (String)data.get("txtBandarPeguam");
							String noTelefon = (String)data.get("txtNoTelefon");
							String noFaks = (String)data.get("txtNoFaks");
							String email = (String)data.get("txtEmel");
							String negeriPeguam = (String)data.get("socNegeriPeguam");
							      
							//update table peguam    
							SQLRenderer r2 = new SQLRenderer();
							r2.update("id_peguam", id_peguamlama);
							r2.add("no_rujukan_firma", noRujukan);
							r2.add("alamat1", alamatPeguam1); 	
							r2.add("alamat2", alamatPeguam2);
							r2.add("alamat3", alamatPeguam3);
							r2.add("poskod", poskodPeguam);
							r2.add("id_bandar", bandarPeguam);
							r2.add("no_tel", noTelefon);
							r2.add("no_fax", noFaks);
							r2.add("emel", email);
							r2.add("id_negeri", negeriPeguam);
							r2.add("tarikh_kemaskini", r2.unquote("sysdate"));
							r2.add("id_kemaskini",id_login);		      
							sql = r2.getSQLUpdate("Tblppkpeguam");
							stmt.executeUpdate(sql);
							      
							//add data to tblppkpeguamperayu
							SQLRenderer rPe = new SQLRenderer();
							rPe.add("id_peguam", id_peguamlama); 	
							rPe.add("id_perayu", id_perayu); 
							rPe.add("id_masuk", id_login);
							rPe.add("tarikh_masuk", rPe.unquote("sysdate"));	      
							sql = rPe.getSQLInsert("Tblppkpeguamperayu");
							stmt.executeUpdate(sql);
							       
					}//close try 
						    
					finally {
						if (db != null) db.close();
					}//close finally
						   
			}//close tambah Peguam Lama Seksyen 17
			
			
			//update Maklumat PP Seksyen 17
			public static void updatePeguamSek17(Hashtable data) throws Exception{
				
					Db db = null;
					String sql = "";
					
					try{
						    	
							db = new Db();
							Statement stmt = db.getStatement();
							
							
						    //id	
							String id_permohonan = (String)data.get("id_permohonan");
							String id_pemohon = (String)data.get("id_pemohon");
							String id_perayu = (String)data.get("id_perayu");
							String id_peguam = (String)data.get("id_peguam");
							      
							//data peguam
							String namaFirma = (String)data.get("txtNamaFirma");
							String noRujukan = (String)data.get("txtNoRujukan");
							String alamatPeguam1 = (String)data.get("txtAlamatPeguam1");
							String alamatPeguam2 = (String)data.get("txtAlamatPeguam2");
							String alamatPeguam3 = (String)data.get("txtAlamatPeguam3");
							String poskodPeguam = (String)data.get("txtPoskodPeguam");
							String bandarPeguam = (String)data.get("txtBandarPeguam");
							String noTelefon = (String)data.get("txtNoTelefon");
							String noFaks = (String)data.get("txtNoFaks");
							String email = (String)data.get("txtEmel");
							String negeriPeguam = (String)data.get("socNegeriPeguam");
							    	
							//id login kemaskini
							String id_kemaskini = (String)data.get("id_kemaskini");
							  
						    //update table peguam    
						    SQLRenderer r2 = new SQLRenderer();
						    r2.update("id_peguam", id_peguam);
						    r2.add("nama_firma", namaFirma);
						    r2.add("no_rujukan_firma", noRujukan);
						    r2.add("alamat1", alamatPeguam1); 	
						    r2.add("alamat2", alamatPeguam2);
						    r2.add("alamat3", alamatPeguam3);
						    r2.add("poskod", poskodPeguam);
						    r2.add("id_bandar", bandarPeguam);
						    r2.add("no_tel", noTelefon);
						    r2.add("no_fax", noFaks);
						    r2.add("emel", email);
						    r2.add("id_negeri", negeriPeguam);
						    r2.add("tarikh_kemaskini", r2.unquote("sysdate"));
						    r2.add("id_kemaskini",id_kemaskini);		      
						    sql = r2.getSQLUpdate("Tblppkpeguam");
						    stmt.executeUpdate(sql);
						      
					}//close try 
						    
					finally {
						if (db != null) db.close();
					}//close finally
						   
		}//close update perayu Seksyen 17
						
			
}//close class
