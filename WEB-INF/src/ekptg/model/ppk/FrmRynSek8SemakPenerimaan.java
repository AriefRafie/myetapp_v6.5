package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */
import ekptg.view.ppk.FrmRynSemakPenerimaan;

public class FrmRynSek8SemakPenerimaan {

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmRynSemakPenerimaan.class);
	//seksyen 8 (termasuk dengan seksyen 17)
	private static Vector listDefault = new Vector();
	private static Vector listCarian = new Vector();
	private static Vector listSemakKR = new Vector();
	private static Vector listBayarKR = new Vector();
	private static Vector listDataMaklumatSerahanPenasihat = new Vector();
	private static Vector listDataMaklumatSerahanMahkamah = new Vector();
	
	//seksyen 17
	private static Vector listDefaultSek17 = new Vector();
	private static Vector listCarianSek17 = new Vector();
	
	//seksyen 8 (termasuk dengan seksyen 17)
	public static Vector getListDefault(){
		return listDefault;
	}
	public static Vector getListCarianSemakPenerimaan(){
		return listCarian;
	}
	public static Vector getListSemakKR(){
		return listSemakKR;
	}
	public static Vector getBayaranKR(){
		return listBayarKR;
	}
	public static Vector getMaklumatSerahanPenasihat(){
		return listDataMaklumatSerahanPenasihat;
	}
	public static Vector getMaklumatSerahanMahkamah(){
		return listDataMaklumatSerahanMahkamah;
	}
	
	//Seksyen 17
	public static Vector getListDefaultSek17(){
		return listDefaultSek17;
	}
	public static Vector getListCarianSemakPenerimaanSek17(){
		return listCarianSek17;
	}
	
	//list default Seksyen 8
	public static void setListDefault(String usid)throws Exception {
		
	    Db db = null;
	    listDefault.clear();
	    String sql = "";
	    
	    try {
	    	
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      //SYARAT
	      sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
	      + " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
	      + " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
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
	      + " AND (P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180) "
	      + " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
	      + " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
	      + " AND STA.AKTIF = '1' "
	      + " AND P.ID_STATUS <> '999' " 
	      + " AND P.SEKSYEN = '8' "
	      + " AND P.FLAG_JENIS_PERMOHONAN = '1' "
	      //+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
	      + " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
				h.put("bil", bil);
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS"));
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("id_simati", rs.getString("ID_SIMATI"));
				h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				listDefault.addElement(h);
				bil++;	
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close list default Seksyen 8
	
	//CARIAN Seksyen 8
	public static void setCarianFail(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid)throws Exception {
	   
		Db db = null;
	    listCarian.clear();
	    String sql = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		String chkDataFail = noFail.trim();
	    		String chkDataPemohon = namaPemohon.trim();
	    		String chkDataSimati = namaSimati.trim();
	    		String chkDataIcSimati = icSimati.trim();
	    		String chkDataJenisKp = JenisIc;
	      
	    		//SYARAT
	    		sql = "SELECT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
	    			+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
	    			+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
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
					 
					sql+= ") " 
	    			+ " AND P.ID_STATUS = S.ID_STATUS "
	    			+ " AND P.ID_FAIL = F.ID_FAIL(+) "
	    			+ " AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN(+) " 
	    			+ " AND P.ID_DAERAHMHN = D.ID_DAERAH "
	    			+ " AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN " 
	    			+ " AND M.ID_SIMATI = MS.ID_SIMATI " 
	    			+ " AND P.ID_PEMOHON = PP.ID_PEMOHON "
	    			+ " AND (P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180) "
	    			+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
	    			+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
	    			+ " AND STA.AKTIF = '1' "
	    			+ " AND P.ID_STATUS <> '999' " 
	    			+ " AND P.SEKSYEN = '8' "
	    			+ " AND P.FLAG_JENIS_PERMOHONAN = '1' ";
	    			//+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)";
	         
	    		//NO FAIL
	    		if(noFail != null){
	    			
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
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN"));
				h.put("id_status", rs.getString("ID_STATUS"));
				h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikh_masuk", rs.getString("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				h.put("nama_simati",rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("id_simati", rs.getString("ID_SIMATI"));
				listCarian.addElement(h);
				bil++;
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian Seksyen 8
	
	//update Keputusan Pegawai [tblppkrayuan]
	public static void updateKeputusanPegawai(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try
	    {
	    	  db = new Db();
	    	  Statement stmt = db.getStatement();
	    	  
	    	  //id	
		      String id_rayuan = (String)data.get("id_rayuan");
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_status = (String)data.get("id_status");
		      int idS = Integer.parseInt(id_status);
		      
		      //data perayu
		      String catatanPegawai = (String)data.get("catatanPegawai");
		      String sorKeputusanP = (String)data.get("sorKeputusanP");
		      
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_masuk");
		      
		      //update table perayu [pegawai]   
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_rayuan", id_rayuan);
		      r.add("catatan_pegawai", catatanPegawai);   
		      
		      if(idS!=166 && idS!=167 && idS!=180){
		      r.add("id_keputusanpegawai",sorKeputusanP);
		      }
		      
		      r.add("id_kemaskini",id_kemaskini);
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
		      sql = r.getSQLUpdate("Tblppkrayuan");
		      stmt.executeUpdate(sql);
	     	    
		      if(idS==163 || idS==164 || idS==165)
		      {
		    	  //update status 
		    	  SQLRenderer r2 = new SQLRenderer();
		    	  r2.update("id_permohonan", id_permohonan);
		    	  r2.add("id_status", sorKeputusanP);   
		    	  r2.add("id_kemaskini",id_kemaskini);
		    	  r2.add("tarikh_kemaskini", r2.unquote("sysdate"));	      
		    	  sql = r2.getSQLUpdate("Tblppkpermohonan");
		    	  stmt.executeUpdate(sql);
		      }
	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close update Keputusan Pegawai
	
	
	//delete semakan pegawai checkbox
	public static void semakanDelete(String id_permohonan) throws Exception {
		
	    Db db = null;
	    String sql1 = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		sql1 =  "DELETE FROM tblsemakanhantar WHERE id_permohonan = '" +id_permohonan+"'" ;
	    		sql1 += " AND id_semakansenarai = ANY(141,142,143) ";      
	    		stmt.executeUpdate(sql1);
	    		
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	//delete semakan pegawai checkbox
	
	
	//update Semakan Keputusan Pegawai
	public static void updateSemakanKeputusanPegawai(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran) throws Exception
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
	 	    String id_masuk = (String)data.get("id_masuk");
	 	    
	 	    String id_bayaran = (String)data.get("id_bayaran");
	 	    
	    	//long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
		    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
					    
		    //insert data into tblsemakanhantar
		    sql = "INSERT INTO Tblsemakanhantar " +
				  "(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
			      "VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_masuk+"', sysdate) ";
		   
		    stmt.executeUpdate(sql);
		    
		    //insert into tblppkbayaran
		    if (idsemakan.equals("142")) 
		    {
			      SQLRenderer r1 = new SQLRenderer();
			      r1.update("id_bayaran", id_bayaran);
			      //r1.add("id_permohonan", idpermohonan);
			      //r1.add("id_jenisbayaran",18);
			      r1.add("no_resit",noresit);
			      r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
			      r1.add("id_kemaskini",id_masuk); 
			      r1.add("tarikh_kemaskini",r1.unquote("sysdate")); 
			      sqlBayaran = r1.getSQLUpdate("tblppkbayaran");
			      stmt.executeUpdate(sqlBayaran);
			}
		    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateSemakanKeputusanPegawai
	
	
	//delete bayaran / set to ""
	public static void deleteBayaran(String id_permohonan) throws Exception {
		
	    Db db = null;
	    String sql1 = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		 
		 		sql1 = "DELETE FROM tblppkbayaran WHERE id_permohonan = '" +id_permohonan+ "'";
		 		sql1 += " AND id_jenisbayaran = 18 ";      
		 		stmt.executeUpdate(sql1);
		 		
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	//list semakan kr
	public static void setListSemakKR(String id_permohonan)throws Exception {
		
	    Db db = null;
	    listSemakKR.clear();
	    String sql = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		//SYARAT
	    		sql =  "SELECT distinct a.id_permohonan,h.id_semakanhantar,s.id_semakansenarai,a.id_status ";
	    		sql += "FROM Tblppkpermohonan a, Tblsemakanhantar h, Tblsemakansenarai s ";
	    		sql += "WHERE h.id_permohonan = a.id_permohonan AND h.id_semakansenarai = s.id_semakansenarai ";
	    		sql += "AND a.id_permohonan = '" +id_permohonan+ "'";
	    		sql += "AND s.id_semakansenarai = ANY(141,142,143) ";
	      
	    		//sorting
	    		sql +="ORDER BY s.id_semakansenarai desc";
	    
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;
	      
	    		while (rs.next()) {
	    			
	    			h = new Hashtable();
	    			h.put("id_permohonan", rs.getString("id_Permohonan"));
	    			h.put("id_semakanhantar", rs.getString("id_semakanhantar"));
	    			h.put("id_status", rs.getString("id_status"));
	    			h.put("id_semakansenarai", rs.getString("id_semakansenarai"));			
	    			listSemakKR.addElement(h);
	    			bil++;
	    			
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close list semakan kr
	
	//data bayaran
	public static void setBayaranKR(String id_permohonan)throws Exception {
		
	    Db db = null;
	    listBayarKR.clear();
	    String sql = "";
	    String sql1 = "";
	    String id_bayaran = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		sql1 = "SELECT MAX(id_bayaran)as id_bayaran FROM tblppkbayaran WHERE id_permohonan = '" +id_permohonan+ "'";
		 		sql1 += " AND id_jenisbayaran = 18 ";      
		 		
		 		ResultSet rx = stmt.executeQuery(sql1);
			    while (rx.next()) {
			    	id_bayaran = rx.getString("id_bayaran");
			    }
	    		
	    		//SYARAT
	    		sql =  "SELECT distinct a.id_permohonan, b.id_bayaran, b.id_jenisbayaran, b.no_resit, b.tarikh_bayaran, b.jumlah_bayaran ";
	    		sql += "FROM Tblppkbayaran b, Tblppkpermohonan a ";
	    		sql += "WHERE b.id_permohonan = a.id_permohonan ";
	    		sql += "AND a.id_permohonan = '" +id_permohonan+ "'";
	    		if(id_bayaran!="" && id_bayaran!=null){
	    			sql += "AND b.id_bayaran = '" +id_bayaran+ "'";
	    		}
	    		sql += " AND b.id_jenisbayaran = 18 ";
	    		System.out.println("sql = " + sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	      
	    		while (rs.next()) {
	    			
	    			h = new Hashtable();
	    			h.put("id_permohonan", rs.getString("id_permohonan"));
	    			h.put("id_bayaran",rs.getString("id_bayaran")==null?"":rs.getString("id_bayaran"));
	    			h.put("id_jenisbayaran", rs.getString("id_jenisbayaran"));			
	    			h.put("no_resit",rs.getString("no_resit")==null?"":rs.getString("no_resit"));
	    			h.put("tarikh_bayaran", rs.getString("tarikh_bayaran")==null?"":Format.format(rs.getDate("tarikh_bayaran")));
	    			//h.put("jumlah_bayaran",rs.getString("jumlah_bayaran")==null?"":rs.getString("jumlah_bayaran"));
	    			if(rs.getString("jumlah_bayaran") != null && rs.getString("jumlah_bayaran") != "" ){
				    	String jb = rs.getString("jumlah_bayaran");
				    	double jumbayar = Double.parseDouble(jb);
				    	h.put("jumlah_bayaran", jumbayar);
				    }else{
				    	h.put("jumlah_bayaran", "");
				    }
	    			
	    			listBayarKR.addElement(h);
	    			
	      }
	      //return list default;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	  }//close data bayaran
	
	//update Keputusan Mahkamah [tblppkrayuan]
	public static void updateKeputusanMahkamah(HttpSession session,Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    String sql6 = "";
	    String sql5 = "";
	    
	    try
	    {
	    		
	    	  db = new Db();
	    	  Statement stmt = db.getStatement();
	    	 
	    	  //id	
		      String id_rayuan = (String)data.get("id_rayuan");
		      String id_perintah = (String)data.get("id_perintah");
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_status = (String)data.get("id_status");
		      String id_fail = (String)data.get("id_fail");
		 	  String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
		 	  System.out.println("id_status = " + id_status);
		 	  System.out.println("id_rayuan = " + id_rayuan);
		 	  int idS = Integer.parseInt(id_status);
		      
		      //data perayu
		      String catatanMahkamah = (String)data.get("catatanMahkamah");
		      System.out.println("catatanMahkamah = " + catatanMahkamah);
		      String sorKeputusanM = (String)data.get("sorKeputusanM");
		      int id_stat = Integer.parseInt(sorKeputusanM);
		      //id login kemaskini
		      String id_kemaskini = (String)data.get("id_kemaskini");
		      //update table perayu [pegawai]   
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_rayuan", id_rayuan);
		      r.add("catatan", catatanMahkamah);   
		      r.add("id_keputusanmahkamah",sorKeputusanM);
		      r.add("id_kemaskini",id_kemaskini);
		      r.add("tarikh_kemaskini", r.unquote("sysdate"));	
		      
		      sql = r.getSQLUpdate("Tblppkrayuan");
		      System.out.println(" ------------- Tblppkrayuan : "+sql);
		      stmt.executeUpdate(sql);
	     	 
		      if(idS!=166) {     
		      
		    	  if(id_stat==166){
		    		  SQLRenderer p = new SQLRenderer();
		    		  p.update("id_perintah", id_perintah);
		    		  p.add("flag_tangguh",99);
		    		  p.add("id_kemaskini",id_kemaskini);
		    		  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
		    		  sql = p.getSQLUpdate("Tblppkperintah");
		    		  stmt.executeUpdate(sql);
		    	  }
		      }else{
		    	  
		    	  if(id_stat==166){
		    		  SQLRenderer p = new SQLRenderer();
		    		  p.update("id_perintah", id_perintah);
		    		  p.add("flag_tangguh",99);
		    		  p.add("id_kemaskini",id_kemaskini);
		    		  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
		    		  sql = p.getSQLUpdate("Tblppkperintah");
		    		  stmt.executeUpdate(sql);
		    	  }else{
		    		  SQLRenderer p = new SQLRenderer();
		    		  p.update("id_perintah", id_perintah);
	    		  	  p.add("flag_tangguh","");
	    		  	  p.add("id_kemaskini",id_kemaskini);
	    		  	  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
	    		  	  sql = p.getSQLUpdate("Tblppkperintah");
	    		  	  stmt.executeUpdate(sql);
		    	  }
		      }
		
		
		   if(!id_status.equals(sorKeputusanM)){			
			 	      
		      int no = 0;
		      if(id_stat==166){
		    	  //no = 470;
		    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
				   logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"166","470",id_fail);
		      }else if(id_stat==167){
		    	  //no = 471;
		    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
				  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"167","471",id_fail);
		      }else if(id_stat==180){
		    	  //no = 1213;
		    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
				  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"180","1213",id_fail);
		      }
		      else if(id_stat==164){
		    	  //no = 467;
		    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
				  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"164","467",id_fail);
		      }
		      
	    	}
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close update Keputusan Mahkamah
	
	
	//simpan Maklumat Serahan K1
	public static void simpanMaklumatSerahanK1(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    String sqlM = "";
	    
	    try
	    {
	    	
	    	  db = new Db();
		      Statement stmt = db.getStatement();
		      
		      
	    	  //id	
		      String id_rayuan = (String)data.get("id_rayuan");
		      String id_permohonan = (String)data.get("id_permohonan");

		      //-- 06122009
		      String id_pejabatPenasihat = (String)data.get("id_pejabatPenasihat");
		      //-- 06122009
		      
		      //data penasihat
		      String tarikh_penasihat = (String)data.get("txdTarikhSerahanPenasihat");
		      String nama_penasihat = (String)data.get("txtNamaPenasihat");
		      String alamatPenasihat1 = (String)data.get("alamatPenasihat1");
		      String alamatPenasihat2 = (String)data.get("alamatPenasihat2");
		      String alamatPenasihat3 = (String)data.get("alamatPenasihat3");
		      String poskodPenasihat = (String)data.get("txtPoskodPenasihat");
		      String id_bandarP = (String)data.get("id_bandarP");
		      String negeriPenasihat = (String)data.get("idnegeriPenasihat");
		      
		      //data mahkamah
		      String tarikh_mahkamah = (String)data.get("txdTarikhSerahanMahkamah");
		      String nama_mahkamah = (String)data.get("socMahkamah");
		      String txtAlamatMahkamah1 = (String)data.get("txtAlamatMahkamah1");
		      String txtAlamatMahkamah2 = (String)data.get("txtAlamatMahkamah2");
		      String txtAlamatMahkamah3 = (String)data.get("txtAlamatMahkamah3");
		      String poskodMahkamah = (String)data.get("txtPoskodMahkamah");
		      String id_bandarM = (String)data.get("id_bandarM");
		      String negeriMahkamah = (String)data.get("socNegeriMahkamah");
		      
		      String id_mahkamah = (String)data.get("id_mahkamah");
		      
		      //id login masuk
		      String id_masuk = (String)data.get("id_masuk");
		 
		      String TP = "to_date('" + tarikh_penasihat + "','dd/MM/yyyy')";
		      String TM = "to_date('" + tarikh_mahkamah + "','dd/MM/yyyy')";
		      
		      String flag_serahan_penasihat = "2";   
		      String flag_serahan_mahkamah = "3"; 
		
		  
		      /*  String namaMahkamah = "";
		  
		  		Statement stmtMT = db.getStatement();
	      		SQLRenderer rMT = new SQLRenderer();				 
	      		rMT.add("id_pejabat");
	      		rMT.add("nama_pejabat");
	      		rMT.add("id_pejabat",nama_mahkamah);				      
	      		sql = rMT.getSQLSelect("Tblrujpejabat");
	      		ResultSet rsMT = stmtMT.executeQuery(sql);
	      		while (rsMT.next()) {
	    	  	namaMahkamah = rsMT.getString("nama_pejabat");
	      		}
		       */  
		  
		      //add penasihat
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_rayuan", id_rayuan);
		      r.add("id_pejabat", id_pejabatPenasihat);
		      r.add("nama", nama_penasihat);
		      r.add("alamat1", alamatPenasihat1);
		      r.add("alamat2", alamatPenasihat2);
		      r.add("alamat3", alamatPenasihat3);
		      r.add("poskod", poskodPenasihat);
		      r.add("id_bandar", id_bandarP);
		      r.add("id_negeri", negeriPenasihat);
		      r.add("flag_serahan", flag_serahan_penasihat);
		      r.add("tarikh_borangk3", r.unquote(TP));	   
		      r.add("id_masuk",id_masuk);
		      r.add("tarikh_masuk", r.unquote("sysdate"));	      
		      sql = r.getSQLInsert("Tblppkserahanrayuan");
		      stmt.executeUpdate(sql);
	    
		      //add mahkamah
		      SQLRenderer r2 = new SQLRenderer();
		      r2.add("id_rayuan", id_rayuan);
		      r2.add("nama", nama_mahkamah);
		      r2.add("alamat1", txtAlamatMahkamah1);
		      r2.add("alamat2", txtAlamatMahkamah2);
		      r2.add("alamat3", txtAlamatMahkamah3);
		      r2.add("poskod", poskodMahkamah);
		      r2.add("id_bandar", id_bandarM);
		      r2.add("id_negeri", negeriMahkamah);
		      r2.add("flag_serahan", flag_serahan_mahkamah);
		      r2.add("tarikh_borangk3", r2.unquote(TM));	   
		      r2.add("id_masuk",id_masuk);
		      r2.add("tarikh_masuk", r2.unquote("sysdate"));	      
		      sql = r2.getSQLInsert("Tblppkserahanrayuan");
		      stmt.executeUpdate(sql);
      
		      
		      if(id_mahkamah!=""){
	    	 
		    	  //update tblppkrayuan
		    	  SQLRenderer rR = new SQLRenderer();
		    	  rR.update("id_rayuan", id_rayuan);
		    	  rR.add("id_mahkamah", id_mahkamah);
		    	  rR.add("id_kemaskini",id_masuk);
		    	  rR.add("tarikh_kemaskini", rR.unquote("sysdate"));	      
		    	  sql = rR.getSQLUpdate("Tblppkrayuan");
		    	  stmt.executeUpdate(sql);
		      }
	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpan Maklumat Serahan K1
	
	
	//Data Maklumat Serahan Penasihat k1
	public static void setMaklumatSerahanPenasihat(String id_rayuan)throws Exception {
		
	    Db db = null;
	    listDataMaklumatSerahanPenasihat.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		//SYARAT
	    		sql =  "SELECT distinct ry.id_serahanrayuan, ry.id_rayuan, ry.tarikh_borangk3, ry.nama, ry.alamat1, ry.alamat2, ry.alamat3, ";
	    		sql += "ry.poskod, ry.id_bandar, ry.id_negeri, ry.id_pejabat "; 
	    		sql += "FROM Tblppkserahanrayuan ry ";
	    		sql += "WHERE ry.id_rayuan = '"+id_rayuan+"'";
	    		sql += "AND ry.flag_serahan = 2 ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	 
	    		while (rs.next()) {
	    			
	    			h = new Hashtable();
	    			h.put("id_serahanrayuan", rs.getString("id_serahanrayuan")==null?"":rs.getString("id_serahanrayuan"));
	    			h.put("id_rayuan", rs.getString("id_rayuan")==null?"":rs.getString("id_rayuan"));
	    			h.put("nama_penerima", rs.getString("nama")==null?"":rs.getString("nama"));
	    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
	    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	    			h.put("tarikh_serahan", rs.getDate("tarikh_borangk3")==null?"":Format.format(rs.getDate("tarikh_borangk3")));
	    			h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
	    			listDataMaklumatSerahanPenasihat.addElement(h);
	    		}
	     
	    } finally {
	      if (db != null) db.close();
	    }    
	  }//close Data Maklumat Serahan Penasihat k1
	
	
	//Data Maklumat Serahan Mahkamah k1
	public static void setMaklumatSerahanMahkamah(String id_rayuan)throws Exception {
		
	    Db db = null;
	    listDataMaklumatSerahanMahkamah.clear();
	    String sql = "";
	    
	    try {
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		//SYARAT
	    		sql =  "SELECT distinct ry.id_serahanrayuan, ry.id_rayuan, ry.tarikh_borangk3, ry.nama, ry.alamat1, ry.alamat2, ry.alamat3, ";
	    		sql += "ry.poskod, ry.id_bandar, ry.id_negeri, r.id_mahkamah "; 
	    		sql += "FROM Tblppkserahanrayuan ry, Tblppkrayuan r ";
	    		sql += "WHERE ry.id_rayuan = '"+id_rayuan+"'";
	    		sql += " AND ry.id_rayuan = r.id_rayuan";
	    		sql += " AND ry.flag_serahan = 3 ";
	    		myLogger.info("setMaklumatSerahanMahkamah = "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	 
	    		while (rs.next()) {
	    			
	    			h = new Hashtable();
	    			h.put("id_serahanrayuan", rs.getString("id_serahanrayuan")==null?"":rs.getString("id_serahanrayuan"));
	    			h.put("id_rayuan", rs.getString("id_rayuan")==null?"":rs.getString("id_rayuan"));
	    			h.put("id_mahkamah", rs.getString("id_mahkamah")==null?"":rs.getString("id_mahkamah"));
	    			h.put("nama_penerima", rs.getString("nama")==null?"":rs.getString("nama"));
	    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
	    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
	    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
	    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
	    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
	    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	    			h.put("tarikh_serahan", rs.getDate("tarikh_borangk3")==null?"":Format.format(rs.getDate("tarikh_borangk3")));
	    			listDataMaklumatSerahanMahkamah.addElement(h);
	    		}
	     
	    	} finally {
	    		if (db != null) db.close();
	    	}    
	  }//close Data Maklumat Serahan Mahkamah k1
	
	
	//get getListMahkamah
	 public static Vector getListMahkamah(String id_negeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " select b.nama_daerah, b.id_daerah, a.id_pejabat, a.nama_pejabat, a.alamat1, a.alamat2, a.alamat3, a.poskod "; 
		    		sql += " from tblrujpejabat a, tblrujdaerah b ";
		    		sql += " where id_jenispejabat = 8 ";
		    		sql += " and a.id_daerah = b.id_daerah ";
		    		sql += " and a.id_negeri = '"+id_negeri+"'";
		      
		    		myLogger.info("getListMahkamah = "+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
		      
		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil",bil);
		    			h.put("id_pejabat", rs.getString("id_pejabat"));
		    			h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    			//h.put("nama_pejabat", rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah")==null?"":rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah"));
					    
		    			list.addElement(h);
		    			bil++;
		    		}
		    		return list;
		    		
		    	} finally {
		    		if (db != null) db.close();
		    	}
		    	
		  }//Get getListMahkamah
	
	//getDetailMahkamah
	 public static Vector getDetailMahkamah(String id_pejabat)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  "select a.id_daerah, a.id_negeri, a.id_pejabat, a.nama_pejabat, a.alamat1, a.alamat2, a.alamat3, a.poskod, b.nama_negeri, c.nama_daerah, a.id_bandar ";
		    		sql += " from tblrujpejabat a, tblrujnegeri b, tblrujdaerah c";
		    		sql += " where id_jenispejabat = 8 ";
		    		sql += " and a.id_negeri = b.id_negeri";
		    		sql += " and a.id_daerah = c.id_daerah";
		    		sql += " and id_pejabat = '"+id_pejabat+"'";
		      
		    		myLogger.info("getDetailMahkamah = "+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		      
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
		    			
		    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    			h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    			h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
		    			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
		    			h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    			//h.put("nama_pejabat", rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah")==null?"":rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah"));
					      
		    			
		    			list.addElement(h);
		    		}
		    		return list;
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  }//Get getDetailMahkamah
	 
	 
	//update Maklumat Serahan
		public static void updateMaklumatSerahan(Hashtable data) throws Exception{
			
		    Db db = null;
		    String sql = "";
		    String sqlM = "";
		    
		    try
		    {
		    	  db = new Db();
			      Statement stmt = db.getStatement();
		    	
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			      String id_permohonan = (String)data.get("id_permohonan");
			      String id_serahanrayuanP = (String)data.get("id_serahanrayuanP");
			      String id_serahanrayuanM = (String)data.get("id_serahanrayuanM");
			      
			      //-- 06122009
			      String id_pejabatPenasihat = (String)data.get("id_pejabatPenasihat");
			      //-- 06122009
			      
			      //data penasihat
			      String tarik_penasihat = (String)data.get("txdTarikhSerahanPenasihat");
			      String nama_penasihat = (String)data.get("txtNamaPenasihat");
			      String alamatPenasihat1 = (String)data.get("alamatPenasihat1");
			      String alamatPenasihat2 = (String)data.get("alamatPenasihat2");
			      String alamatPenasihat3 = (String)data.get("alamatPenasihat3");
			      String poskodPenasihat = (String)data.get("txtPoskodPenasihat");
			      String bandarPenasihat = (String)data.get("id_bandarP");
			      String negeriPenasihat = (String)data.get("idnegeriPenasihat");
			      
			      //data mahkamah
			      String tarikh_mahkamah = (String)data.get("txdTarikhSerahanMahkamah");
			      String nama_mahkamah = (String)data.get("socMahkamah");
			      String txtAlamatMahkamah1 = (String)data.get("txtAlamatMahkamah1");
			      String txtAlamatMahkamah2 = (String)data.get("txtAlamatMahkamah2");
			      String txtAlamatMahkamah3 = (String)data.get("txtAlamatMahkamah3");
			      String poskodMahkamah = (String)data.get("txtPoskodMahkamah");
			      String bandarMahkamah = (String)data.get("id_bandarM");
			      String negeriMahkamah = (String)data.get("socNegeriMahkamah");
			      
			      String id_mahkamah = (String)data.get("id_mahkamah");
			      
			      //id login kemaskini
			      String id_kemaskini = (String)data.get("id_kemaskini");
			 
			      String TP = "to_date('" + tarik_penasihat + "','dd/MM/yyyy')";
			      String TM = "to_date('" + tarikh_mahkamah + "','dd/MM/yyyy')";
			      
			      String flag_serahan_penasihat = "2";   
			      String flag_serahan_mahkamah = "3"; 
			  
			      
			      //update penasihat
			      SQLRenderer r = new SQLRenderer();
			      r.update("id_serahanrayuan", id_serahanrayuanP);
			      r.add("id_pejabat", id_pejabatPenasihat);
			      r.add("nama", nama_penasihat);
			      r.add("alamat1", alamatPenasihat1);
			      r.add("alamat2", alamatPenasihat2);
			      r.add("alamat3", alamatPenasihat3);
			      r.add("poskod", poskodPenasihat);
			      r.add("id_bandar", bandarPenasihat);
			      r.add("id_negeri", negeriPenasihat);
			      r.add("tarikh_borangk3", r.unquote(TP));	   
			      r.add("id_kemaskini",id_kemaskini);
			      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
			      sql = r.getSQLUpdate("Tblppkserahanrayuan");
			      stmt.executeUpdate(sql);
		    
			      
			      //add mahkamah
			      SQLRenderer r2 = new SQLRenderer();
			      r2.update("id_serahanrayuan", id_serahanrayuanM);
			      r2.add("nama",nama_mahkamah);
			      r2.add("alamat1", txtAlamatMahkamah1);
			      r2.add("alamat2", txtAlamatMahkamah2);
			      r2.add("alamat3", txtAlamatMahkamah3);
			      r2.add("poskod", poskodMahkamah);
			      r2.add("id_bandar", bandarMahkamah);
			      r2.add("id_negeri", negeriMahkamah);
			      r2.add("tarikh_borangk3", r2.unquote(TM));	   
			      r2.add("id_kemaskini",id_kemaskini);
			      r2.add("tarikh_kemaskini", r2.unquote("sysdate"));	      
			      sql = r2.getSQLUpdate("Tblppkserahanrayuan");
			      stmt.executeUpdate(sql);
	      
			      if(id_mahkamah!=""){
			    	  //update tblppkrayuan
			    	  SQLRenderer rR = new SQLRenderer();
			    	  rR.update("id_rayuan", id_rayuan);
			    	  rR.add("id_mahkamah", id_mahkamah);
			    	  rR.add("id_kemaskini",id_kemaskini);
			    	  rR.add("tarikh_kemaskini", rR.unquote("sysdate"));	      
			    	  sql = rR.getSQLUpdate("Tblppkrayuan");
			    	  stmt.executeUpdate(sql);
			      }
	      
		      
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close update Maklumat Serahan
	 
		//update SuburusanS Fail
		public static void updateSuburusanSFail(HttpSession session,Hashtable data) throws Exception
		  {
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
			 	  String sorKeputusanP = (String)data.get("sorKeputusanP");
			 	  
			 	  int id_stat = Integer.parseInt(sorKeputusanP);
			 	  
			        //update n add tblrujsuburusanstatus
			 	  	/*
			      	SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
					r6.add("AKTIF",0);
					r6.add("ID_KEMASKINI",id_masuk);
					r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmt.executeUpdate(sql6);	 
			      */
					int no = 0;
					if(id_stat==164){
						//no = 467;
						FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"164","467",id_fail);
					}else if(id_stat==165){
						//no = 468;
						FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"165","468",id_fail);
					}
					
					/*
					long id_suburusSFail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
					
			        SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",id_suburusSFail);
					r5.add("ID_PERMOHONAN",id_permohonan);
					r5.add("ID_FAIL",id_fail);
					r5.add("ID_SUBURUSANSTATUS",no);
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
		
		//getDetailMahkamah
		 public static Vector getDetailPenasihat(String id_pejabat)throws Exception {
			 
			    Db db = null;
			    String sql = "";
			    
			    try {
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			     
			    		sql =  "select a.id_daerah, a.id_negeri, a.id_pejabat, a.nama_pejabat, a.alamat1, a.alamat2, a.alamat3, a.poskod, b.nama_negeri, c.nama_daerah, a.id_bandar ";
			    		sql += " from tblrujpejabat a, tblrujnegeri b, tblrujdaerah c";
			    		sql += " where id_jenispejabat = '81' ";
			    		sql += " and a.id_negeri = b.id_negeri";
			    		sql += " and a.id_daerah = c.id_daerah";
			    		sql += " and id_pejabat = '"+id_pejabat+"'";
			      
			    		ResultSet rs = stmt.executeQuery(sql);
			    		Vector list = new Vector();
			      
			    		Hashtable h = null;
			      
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("id_pejabat", rs.getString("id_pejabat")==null?"":rs.getString("id_pejabat"));
			    			h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
			    			h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
			    			h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
			    			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			    			h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
			    			h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			    			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			    			h.put("id_bandar", rs.getString("id_bandar")==null?"":rs.getString("id_bandar"));
			    			h.put("nama_pejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
			    			//h.put("nama_pejabat", rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah")==null?"":rs.getString("nama_pejabat")+" "+rs.getString("nama_daerah"));
						      
			    			
			    			list.addElement(h);
			    		}
			    		return list;
			    	} finally {
			    		if (db != null) db.close();
			    	}
			  }//Get getDetailPenasihat
		
		/*				*
		 * 				*
		 * [SEKSYEN 17]	*
		 *				*	 
		 *				* 		
		 *				*/	
		
		
		
		//list default Seksyen 17
		public static void setListDefaultSek17(String usid)throws Exception {
			
		    Db db = null;
		    listDefaultSek17.clear();
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		//SYARAT
		    		sql = "SELECT MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
		    			+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
		    			+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
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
		    			+ " AND (P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180) "
		    			+ " AND P.ID_PERMOHONAN = STA.ID_PERMOHONAN "
		    			+ " AND STA.ID_SUBURUSANSTATUS = SUB.ID_SUBURUSANSTATUS "
		    			+ " AND STA.AKTIF = '1' "
		    			+ " AND P.ID_STATUS <> '999' " 
		    			+ " AND P.SEKSYEN = '17' "    
		    			+ " AND P.FLAG_JENIS_PERMOHONAN = '1' "
		    			//+ " AND (P.FLAG_PERMOHONAN <> '1' OR P.FLAG_PERMOHONAN IS NULL)"
		    			+ " AND F.NO_FAIL IS NOT NULL ORDER BY P.TARIKH_KEMASKINI desc, STA.ID_SUBURUSANSTATUSFAIL DESC ";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("id_permohonan", rs.getString("ID_PERMOHONAN"));
		    			h.put("id_status", rs.getString("ID_STATUS"));
		    			h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
		    			h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
		    			h.put("tarikh_mohon", rs.getDate("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
		    			h.put("tarikh_masuk", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
		    			h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
		    			h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    			h.put("id_simati", rs.getString("ID_SIMATI"));
		    			h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
					
		    			listDefaultSek17.addElement(h);
		    			bil++;	
		      }
		    		
		      //return list default;
		    		
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }//close list default Seksyen 17
		
		
		//CARIAN Seksyen 17
		public static void setCarianFailSek17(String noFail, String namaPemohon, String namaSimati, String icSimati, String JenisIc, String usid)throws Exception {
		    
			Db db = null;
		    listCarianSek17.clear();
		    String sql = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String chkDataFail = noFail.trim();
		    		String chkDataPemohon = namaPemohon.trim();
		    		String chkDataSimati = namaSimati.trim();
		    		String chkDataIcSimati = icSimati.trim();
		    		String chkDataJenisKp = JenisIc;
		      
		    		//SYARAT
		    		sql = "SELECT PP.NAMA_PEMOHON, MS.ID_PERMOHONANSIMATI, F.ID_FAIL, F.NO_FAIL, P.ID_PERMOHONAN, P.TARIKH_MOHON, KP.ID_KEPUTUSANPERMOHONAN,KP.KEPUTUSAN_PERMOHONAN, " 
		    			+ " P.ID_STATUS, S.KETERANGAN, P.TARIKH_MASUK, P.TARIKH_KEMASKINI, F.TARIKH_DAFTAR_FAIL, M.ID_SIMATI, (SELECT SS.KETERANGAN FROM TBLRUJSTATUS SS "
		    			+ " WHERE SS.ID_STATUS = KP.KEPUTUSAN_PERMOHONAN) AS STATUS_KEPUTUSAN,M.NAMA_SIMATI "
		    			+ " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S, TBLRUJDAERAH D, "
		    			+ " TBLPPKPEMOHON PP, TBLPPKSIMATI M, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUSFAIL STA,TBLRUJSUBURUSANSTATUS SUB "
		    			+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='" +usid+ "'";
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
		    			+ " AND (P.ID_STATUS=163 OR P.ID_STATUS=164 OR P.ID_STATUS=165 OR P.ID_STATUS=166 OR P.ID_STATUS=167 OR P.ID_STATUS=180) "
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
					h.put("id_permohonan", rs.getString("ID_PERMOHONAN"));
					h.put("id_status", rs.getString("ID_STATUS"));
					h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":Format.format(rs.getDate("TARIKH_MOHON")));
					h.put("tarikh_masuk", rs.getString("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
					h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR_FAIL")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("keterangan",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
					h.put("nama_simati",rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
					h.put("id_simati", rs.getString("ID_SIMATI"));
					listCarianSek17.addElement(h);
					bil++;
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		    
		  }//close carian Seksyen 17
	
		
		//update Keputusan Pegawai [tblppkrayuan] Seksyen 17
		public static void updateKeputusanPegawaiSek17(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    
		    try
		    {

		    	  db = new Db();
			      Statement stmt = db.getStatement();
			      
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			      String id_permohonan = (String)data.get("id_permohonan");
			      String id_status = (String)data.get("id_status");
			      int idS = Integer.parseInt(id_status);
			      
			      //data perayu
			      String catatanPegawai = (String)data.get("catatanPegawai");
			      String sorKeputusanP = (String)data.get("sorKeputusanP");
			      
			      //id login kemaskini
			      String id_kemaskini = (String)data.get("id_masuk");
			      
			      //update table perayu [pegawai]   
			      SQLRenderer r = new SQLRenderer();
			      r.update("id_rayuan", id_rayuan);
			      r.add("catatan_pegawai", catatanPegawai); 
			      
			      if(idS!=166 && idS!=167 && idS!=180){
				      r.add("id_keputusanpegawai",sorKeputusanP);
				      }
			      
			      r.add("id_kemaskini",id_kemaskini);
			      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
			      sql = r.getSQLUpdate("Tblppkrayuan");
			      stmt.executeUpdate(sql);
		     	    
			      if(idS==163 || idS==164 || idS==165)
			      {
			    	  //update status 
			    	  SQLRenderer r2 = new SQLRenderer();
			    	  r2.update("id_permohonan", id_permohonan);
			    	  r2.add("id_status", sorKeputusanP);   
			    	  r2.add("id_kemaskini",id_kemaskini);
			    	  r2.add("tarikh_kemaskini", r2.unquote("sysdate"));	      
			    	  sql = r2.getSQLUpdate("Tblppkpermohonan");
			    	  stmt.executeUpdate(sql);
			      }
		      
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close update Keputusan Pegawai Seksyen 17
		
		
		//delete semakan pegawai checkbox Seksyen 17
		public static void semakanDeleteSek17(String id_permohonan) throws Exception {
			
		    Db db = null;
		    String sql1 = "";
		    
		    try {
		    	
		    	 	db = new Db();
		    	 	Statement stmt = db.getStatement();
			  
		    	 	sql1 =  "DELETE FROM tblsemakanhantar WHERE id_permohonan = '"+id_permohonan+"'";
		    	 	sql1 += " AND id_semakansenarai = ANY(141,142,143) ";      
		    	 	stmt.executeUpdate(sql1);
		    	 	
		    	} finally {
		    		if (db != null) db.close();
		    	}
		  
		}//delete semakan pegawai checkbox Seksyen 17
		
		
		//update Semakan Keputusan Pegawai Seksyen 17
		public static void updateSemakanKeputusanPegawaiSek17(Hashtable data,String idsemakan, String idpermohonan, String noresit, String tarikhbayaran) throws Exception
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
		 	    String id_masuk = (String)data.get("id_masuk");
		 	    
		 	    String id_bayaran = (String)data.get("id_bayaran");
		 	    
		    	//long idBayaran = DB.getNextID("TBLPPKBAYARAN_SEQ");
			    String tarikh_bayaran = "to_date('" + tarikhbayaran + "','dd/MM/yyyy')";
						    
			    //insert data into tblsemakanhantar
			    sql = "INSERT INTO Tblsemakanhantar " +
					  "(id_semakanhantar, id_semakansenarai, id_permohonan, id_masuk, tarikh_masuk)  " +
				      "VALUES ('"+id_semakanhantar+"', '"+idsemakan+"', '"+idpermohonan+"', '"+id_masuk+"', sysdate) ";
			   
			    stmt.executeUpdate(sql);
			    
			    //insert into tblppkbayaran
			    if (idsemakan.equals("142")) 
			    {
				      SQLRenderer r1 = new SQLRenderer();
				      r1.update("id_bayaran", id_bayaran);
				      //r1.add("id_permohonan", idpermohonan);
				      //r1.add("id_jenisbayaran",18);
				      r1.add("no_resit",noresit);
				      r1.add("tarikh_bayaran",r1.unquote(tarikh_bayaran));
				      r1.add("id_kemaskini",id_masuk); 
				      r1.add("tarikh_kemaskini",r1.unquote("sysdate")); 
				      sqlBayaran = r1.getSQLUpdate("tblppkbayaran");
				      stmt.executeUpdate(sqlBayaran);
				}
			    
			    
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close updateSemakanKeputusanPegawai Seksyen 17
		
		
		//delete bayaran / set to "" Seksyen 17
		public static void deleteBayaranSek17(String id_permohonan) throws Exception {
			
		    Db db = null;
		    String sql1 = "";
		    
		    try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
			  
		    		sql1 =  "DELETE FROM tblppkbayaran WHERE id_permohonan = '" +id_permohonan+ "'";
		    		sql1 += " AND id_jenisbayaran = 18 ";      
		    		stmt.executeUpdate(sql1);
		    		
		    } finally {
		      if (db != null) db.close();
		    }
		  }
		
		
		//update Keputusan Mahkamah [tblppkrayuan] Seksyen 17
		public static void updateKeputusanMahkamahSek17(HttpSession session,Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    String sql6 = "";
		    String sql5 = "";
		    
		    try
		    {
		    	db = new Db();
		    	  Statement stmt = db.getStatement();
		    	 
		    	 
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			      String id_perintah = (String)data.get("id_perintah");
			      String id_permohonan = (String)data.get("id_permohonan");
			      String id_status = (String)data.get("id_status");
			      String id_fail = (String)data.get("id_fail");
			 	  String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");
			 	  int idS = Integer.parseInt(id_status);
			      
			      //data perayu
			      String catatanMahkamah = (String)data.get("catatanMahkamah");
			      String sorKeputusanM = (String)data.get("sorKeputusanM");
			      int id_stat = Integer.parseInt(sorKeputusanM);
			      //id login kemaskini
			      String id_kemaskini = (String)data.get("id_kemaskini");
			      
			      //update table perayu [pegawai]   
			      SQLRenderer r = new SQLRenderer();
			      r.update("id_rayuan", id_rayuan);
			      r.add("catatan", catatanMahkamah);   
			      r.add("id_keputusanmahkamah",sorKeputusanM);
			      r.add("id_kemaskini",id_kemaskini);
			      r.add("tarikh_kemaskini", r.unquote("sysdate"));	      
			      sql = r.getSQLUpdate("Tblppkrayuan");
			      stmt.executeUpdate(sql);
		     	   
			      if(idS!=166) {     
				      
			    	  if(id_stat==166){
			    		  SQLRenderer p = new SQLRenderer();
			    		  p.update("id_perintah", id_perintah);
			    		  p.add("flag_tangguh",99);
			    		  p.add("id_kemaskini",id_kemaskini);
			    		  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
			    		  sql = p.getSQLUpdate("Tblppkperintah");
			    		  stmt.executeUpdate(sql);
			    	  }
			      }else{
			    	  
			    	  if(id_stat==166){
			    		  SQLRenderer p = new SQLRenderer();
			    		  p.update("id_perintah", id_perintah);
			    		  p.add("flag_tangguh",99);
			    		  p.add("id_kemaskini",id_kemaskini);
			    		  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
			    		  sql = p.getSQLUpdate("Tblppkperintah");
			    		  stmt.executeUpdate(sql);
			    	  }else{
			    		  SQLRenderer p = new SQLRenderer();
			    		  p.update("id_perintah", id_perintah);
		    		  	  p.add("flag_tangguh","");
		    		  	  p.add("id_kemaskini",id_kemaskini);
		    		  	  p.add("tarikh_kemaskini", p.unquote("sysdate"));	      
		    		  	  sql = p.getSQLUpdate("Tblppkperintah");
		    		  	  stmt.executeUpdate(sql);
			    	  }
			      }
			      
			   if(!id_status.equals(sorKeputusanM)){			
				   
			      if(idS==164 || idS==166 || idS==167 || idS==180){
			    	  //update status 
			    	/*  SQLRenderer r2 = new SQLRenderer();
			    	  r2.update("id_permohonan", id_permohonan);
			    	  r2.add("id_status", sorKeputusanM);   
			    	  r2.add("id_kemaskini",id_kemaskini);
			    	  r2.add("tarikh_kemaskini", r2.unquote("sysdate"));	      
			    	  sql = r2.getSQLUpdate("Tblppkpermohonan");
			    	  stmt.executeUpdate(sql);*/
			      }
		      
			      //update n add tblrujsuburusanstatus
			     /* SQLRenderer r6 = new SQLRenderer();
			      r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
			      r6.add("AKTIF",0);
			      r6.add("ID_KEMASKINI",id_kemaskini);
			      r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
			      sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
			      stmt.executeUpdate(sql6);	*/
		      
			      int no = 0;
			      if(id_stat==166){
			    	  //no = 475;
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"166","475",id_fail);
			      }else if(id_stat==167){
			    	  //no = 476;
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"167","476",id_fail);			    	  
			      }else if(id_stat==180){
			    	  //no = 1214;
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"180","1214",id_fail);	
			      }
			      else if(id_stat==164){
			    	  //no = 1214;
			    	  FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
					  logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_kemaskini,"164","473",id_fail);	
			      }
/*
			      long id_suburusSFail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				
			      SQLRenderer r5 = new SQLRenderer();
			      r5.add("ID_SUBURUSANSTATUSFAIL",id_suburusSFail);
			      r5.add("ID_PERMOHONAN",id_permohonan);
			      r5.add("ID_FAIL",id_fail);
			      r5.add("ID_SUBURUSANSTATUS",no);
			      r5.add("AKTIF",1);
			      r5.add("ID_MASUK",id_kemaskini);
			      r5.add("ID_KEMASKINI",id_kemaskini);
			      r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
			      r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
			      sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
			      stmt.executeUpdate(sql5);*/
			      
		    	}
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close update Keputusan Mahkamah Seksyen 17
		
		//simpan Maklumat Serahan K1 Seksyen 17
		public static void simpanMaklumatSerahanK1Sek17(Hashtable data) throws Exception
		  {
		    Db db = null;
		    String sql = "";
		    String sqlM = "";
		    
		    try
		    {
		    	  db = new Db();
			      Statement stmt = db.getStatement();
			      
			      
		    	  //id	
			      String id_rayuan = (String)data.get("id_rayuan");
			      String id_permohonan = (String)data.get("id_permohonan");

			      //-- 06122009
			      String id_pejabatPenasihat = (String)data.get("id_pejabatPenasihat");
			      //-- 06122009
			      
			      //data penasihat
			      String tarik_penasihat = (String)data.get("txdTarikhSerahanPenasihat");
			      String nama_penasihat = (String)data.get("txtNamaPenasihat");
			      String alamatPenasihat1 = (String)data.get("alamatPenasihat1");
			      String alamatPenasihat2 = (String)data.get("alamatPenasihat2");
			      String alamatPenasihat3 = (String)data.get("alamatPenasihat3");
			      String poskodPenasihat = (String)data.get("txtPoskodPenasihat");
			      String id_bandarP = (String)data.get("id_bandarP");
			      String negeriPenasihat = (String)data.get("idnegeriPenasihat");
			      
			      //data mahkamah
			      String tarikh_mahkamah = (String)data.get("txdTarikhSerahanMahkamah");
			      String nama_mahkamah = (String)data.get("socMahkamah");
			      String txtAlamatMahkamah1 = (String)data.get("txtAlamatMahkamah1");
			      String txtAlamatMahkamah2 = (String)data.get("txtAlamatMahkamah2");
			      String txtAlamatMahkamah3 = (String)data.get("txtAlamatMahkamah3");
			      String poskodMahkamah = (String)data.get("txtPoskodMahkamah");
			      String id_bandarM = (String)data.get("id_bandarM");
			      String negeriMahkamah = (String)data.get("socNegeriMahkamah");
			      
			      String id_mahkamah = (String)data.get("id_mahkamah");
			      
			      //id login masuk
			      String id_masuk = (String)data.get("id_masuk");
			 
			      String TP = "to_date('" + tarik_penasihat + "','dd/MM/yyyy')";
			      String TM = "to_date('" + tarikh_mahkamah + "','dd/MM/yyyy')";
			      
			      String flag_serahan_penasihat = "2";   
			      String flag_serahan_mahkamah = "3"; 
			  
			      /*  String namaMahkamah = "";
			  
			  		Statement stmtMT = db.getStatement();
		      		SQLRenderer rMT = new SQLRenderer();				 
		      		rMT.add("id_pejabat");
		      		rMT.add("nama_pejabat");
		      		rMT.add("id_pejabat",nama_mahkamah);				      
		      		sql = rMT.getSQLSelect("Tblrujpejabat");
		      		ResultSet rsMT = stmtMT.executeQuery(sql);
		      		while (rsMT.next()) {
		    	  		namaMahkamah = rsMT.getString("nama_pejabat");
		      		}
			       */  
			  
			      //add penasihat
			      SQLRenderer r = new SQLRenderer();
			      r.add("id_rayuan", id_rayuan);
			      r.add("id_pejabat", id_pejabatPenasihat);
			      r.add("nama", nama_penasihat);
			      r.add("alamat1", alamatPenasihat1);
			      r.add("alamat2", alamatPenasihat2);
			      r.add("alamat3", alamatPenasihat3);
			      r.add("poskod", poskodPenasihat);
			      r.add("id_bandar", id_bandarP);
			      r.add("id_negeri", negeriPenasihat);
			      r.add("flag_serahan", flag_serahan_penasihat);
			      r.add("tarikh_borangk3", r.unquote(TP));	   
			      r.add("id_masuk",id_masuk);
			      r.add("tarikh_masuk", r.unquote("sysdate"));	      
			      sql = r.getSQLInsert("Tblppkserahanrayuan");
			      stmt.executeUpdate(sql);
		    
			      //add mahkamah
			      SQLRenderer r2 = new SQLRenderer();
			      r2.add("id_rayuan", id_rayuan);
			      r2.add("nama", nama_mahkamah);
			      r2.add("alamat1", txtAlamatMahkamah1);
			      r2.add("alamat2", txtAlamatMahkamah2);
			      r2.add("alamat3", txtAlamatMahkamah3);
			      r2.add("poskod", poskodMahkamah);
			      r2.add("id_bandar", id_bandarM);
			      r2.add("id_negeri", negeriMahkamah);
			      r2.add("flag_serahan", flag_serahan_mahkamah);
			      r2.add("tarikh_borangk3", r2.unquote(TM));	   
			      r2.add("id_masuk",id_masuk);
			      r2.add("tarikh_masuk", r2.unquote("sysdate"));	      
			      sql = r2.getSQLInsert("Tblppkserahanrayuan");
			      stmt.executeUpdate(sql);
	      
			      if(id_mahkamah!=""){
			    	  //update tblppkrayuan
			    	  SQLRenderer rR = new SQLRenderer();
			    	  rR.update("id_rayuan", id_rayuan);
			    	  rR.add("id_mahkamah", id_mahkamah);
			    	  rR.add("id_kemaskini",id_masuk);
			    	  rR.add("tarikh_kemaskini", rR.unquote("sysdate"));	      
			    	  sql = rR.getSQLUpdate("Tblppkrayuan");
			    	  stmt.executeUpdate(sql);
			      }
		      
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close simpan Maklumat Serahan K1 Seksyen 17
		
		
		//update SuburusanS Fail Seksyen 17
		public static void updateSuburusanSFailSek17(HttpSession session,Hashtable data) throws Exception
		  {
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
			 	  String sorKeputusanP = (String)data.get("sorKeputusanP");
			 	  int id_stat = Integer.parseInt(sorKeputusanP);
			 	  
			        //update n add tblrujsuburusanstatus
			      	/*SQLRenderer r6 = new SQLRenderer();
					r6.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
					r6.add("AKTIF",0);
					r6.add("ID_KEMASKINI",id_masuk);
					r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
					sql6 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
					stmt.executeUpdate(sql6);	*/ 
			      
					int no = 0;
					if(id_stat==164){
						//no = 473;
						FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"164","473",id_fail);
					}else if(id_stat==165){
						//no = 474;
						FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
						logic_A.kemaskiniSubUrusanStatusFail(session,id_permohonan,id_masuk,"165","474",id_fail);
					}
					
					/*
					long id_suburusSFail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
					
			        SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAIL",id_suburusSFail);
					r5.add("ID_PERMOHONAN",id_permohonan);
					r5.add("ID_FAIL",id_fail);
					r5.add("ID_SUBURUSANSTATUS",no);
					r5.add("AKTIF",1);
					r5.add("ID_MASUK",id_masuk);
					r5.add("ID_KEMASKINI",id_masuk);
					r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
					r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
					sql5 = r5.getSQLInsert("tblrujsuburusanstatusfail");
					stmt.executeUpdate(sql5);*/	
					
					
					
		    }//close try 
		    
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		  }//close update Suburusan SFail Seksyen 17
		
		
}//close class
