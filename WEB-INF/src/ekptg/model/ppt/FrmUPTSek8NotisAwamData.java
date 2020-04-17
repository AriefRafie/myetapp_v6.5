package ekptg.model.ppt;
//test
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8NotisAwamData {
	static Logger myLogger = Logger.getLogger(FrmUPTSek8NotisAwamData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	//private static Vector listNotis = null;
	//private static Vector dataNotis = null;
	private static Vector listNotisHakmilikInBulk = null;
	private static Vector listNotisInBulk = null;
	private static Vector dataNotisInBulk = null;
	private  Vector listMaklumatTanah = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}	
/*	public static Vector getListNotis(){
		return listNotis;
	}
	public static Vector getDataNotis(){
		return dataNotis;
	}
*/	public Vector getListMaklumatTanah(){
		return listMaklumatTanah;
	}
	public static Vector getListHakmilikNotisInBulk(){
		return listNotisHakmilikInBulk;
	}
	public static Vector getListNotisInBulk(){
		return listNotisInBulk;
	}
	public static Vector getDataNotisInBulk(){
		return dataNotisInBulk;
	}
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (52,54,74) ";
		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
		
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange )) ";
		
		
		
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			if(userIdNegeri.equals("14")){
				sql += "AND f.id_negeri in (14,15,16) ";
			}else{
				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			}		
		}
	
	return sql;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (52,54,74) ";
		    		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
		    		
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
				    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			if(rs.getString("no_fail") == null){
		    				h.put("no_fail","Belum Diluluskan");
		    			}else{
		    				h.put("no_fail",rs.getString("no_fail"));
		    			}
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPermohonan
	
	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianNofail, String carianTarikh, String status,String userIdNegeri)throws Exception {
		
		listcarian = new Vector();
		
	    Db db = null;
	    listcarian.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (52,54,74) ";
	    		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		
	    		sql = sqlListPermohonan(userIdNegeri);
	    		
	    		//default flag
				boolean setLimit = true;  
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close carian by tarikh
	
	    		//STATUS
				if (status != "" && status != null) {
					setLimit = false;
					sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
				}//close carian by status

				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}
				
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
			    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","Belum Diluluskan");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
	@SuppressWarnings("unchecked")
	public static void simpanNotis(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		//long id_dokumen = (Long)data.get("id_dokumen");
	    		//String txtNotis = (String)data.get("txtNotis");
	    		String txdTarikhKeluar = (String)data.get("txdTarikhKeluar");
	    		//String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    	
	        	String txdTarikhTampal = (String)data.get("txdTarikhTampal");
	    		String socTempatTampal = (String)data.get("socTempatTampal");
	    		String txtTempat = (String)data.get("txtTempat");
	        	
	    		String TK = "to_date('" + txdTarikhKeluar + "','dd/MM/yyyy')";
	    		//String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_hakmilik", id_hakmilik);
	    		//r.add("id_dokumen", id_dokumen);
	    		//r.add("notis", txtNotis); 	
	    		r.add("jenis_tempat_tampal", socTempatTampal); 	
	    		r.add("tempat", txtTempat); 	
	    		r.add("tarikh_keluar",r.unquote(TK));
	    		//r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_tampal",r.unquote(TTMPL));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptnotisawam");
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanNotis
/*	
	@SuppressWarnings("unchecked")
	public static void setListNotis(String id_hakmilik)throws Exception {
	    
		listNotis = new Vector();
		
		Db db = null;
		listNotis.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT a.id_notisawam,a.id_dokumen,a.notis,a.tarikh_keluar,a.tarikh_terima,b.nama_fail, ";
	    		sql += " a.jenis_tempat_tampal,a.tempat,a.tarikh_tampal";
	    		sql += " FROM Tblpptnotisawam a, Tblpptdokumen b ";
	    		sql += " WHERE a.id_dokumen = b.id_dokumen(+) ";
	    		sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("id_notisawam", rs.getString("id_notisawam")== null?"":rs.getString("id_notisawam"));
	    			h.put("id_dokumen", rs.getString("id_dokumen")== null?"":rs.getString("id_dokumen"));
	    			h.put("notis", rs.getString("notis")== null?"":rs.getString("notis"));
	    			h.put("nama_fail", rs.getString("nama_fail")== null?"":rs.getString("nama_fail"));
	    			h.put("tarikh_keluar", rs.getDate("tarikh_keluar")==null?"":Format.format(rs.getDate("tarikh_keluar")));
	    			h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));	
	    			h.put("jenis_tempat_tampal", rs.getString("jenis_tempat_tampal")== null?"":rs.getString("jenis_tempat_tampal"));
	    			h.put("tempat", rs.getString("tempat")== null?"":rs.getString("tempat"));
	    			h.put("tarikh_tampal", rs.getDate("tarikh_tampal")==null?"":Format.format(rs.getDate("tarikh_tampal")));
	    			
	    			if(rs.getString("jenis_tempat_tampal") != null){
	    				if(rs.getString("jenis_tempat_tampal").equals("1")){
	    					h.put("jenis_tempat","PTD / PTG");
	    					h.put("flag1","1");
	    				}
	    				else if(rs.getString("jenis_tempat_tampal").equals("2")){
	    					h.put("jenis_tempat","PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR");
	    					h.put("flag2","1");
	    				}
	    				else if(rs.getString("jenis_tempat_tampal").equals("3")){
	    					h.put("jenis_tempat","TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH");
	    					h.put("flag3","1");
	    				}
	    				else{
	    					h.put("jenis_tempat","");
	    					h.put("flag1","");
	    					h.put("flag2","");
	    					h.put("flag3","");
	    				}
	    			}else{
	    				h.put("jenis_tempat","");
	    			}
	    			
	    			listNotis.addElement(h);
	    			bil ++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListNotis
*/	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data, Db db) throws Exception{
			
		  Db db1 = null;
		  String sql = "";
	    
		  try{
	      
			  if (db == null) {
					db1 = new Db();
				} else {
					db1 = db;
				}
	    		Statement stmt = db1.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status notis awam
	    		String status = "52";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan",db1);
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
		  finally {
				if (db == null) {
					if (db1 != null)
						db1.close();
				}
			}
	   
	  }//close updateStatus
/*	
	@SuppressWarnings("unchecked")
	public static void setDataNotis(String id_notisawam)throws Exception {
	    
		dataNotis = new Vector();
		
		Db db = null;
		dataNotis.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT a.id_notisawam,a.id_dokumen,a.notis,a.tarikh_keluar,a.tarikh_terima,b.nama_fail, ";
	    		sql += " a.jenis_tempat_tampal,a.tempat,a.tarikh_tampal";
	    		sql += " FROM Tblpptnotisawam a, Tblpptdokumen b ";
	    		sql += " WHERE a.id_dokumen = b.id_dokumen(+) ";
	    		sql += " AND a.id_notisawam = '"+id_notisawam+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("id_notisawam", rs.getString("id_notisawam")== null?"":rs.getString("id_notisawam"));
	    			h.put("id_dokumen", rs.getString("id_dokumen")== null?"":rs.getString("id_dokumen"));
	    			h.put("notis", rs.getString("notis")== null?"":rs.getString("notis"));
	    			h.put("nama_fail", rs.getString("nama_fail")== null?"":rs.getString("nama_fail"));
	    			h.put("tarikh_keluar", rs.getDate("tarikh_keluar")==null?"":Format.format(rs.getDate("tarikh_keluar")));
	    			h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
	    			h.put("jenis_tempat_tampal", rs.getString("jenis_tempat_tampal")== null?"":rs.getString("jenis_tempat_tampal"));
	    			h.put("tempat", rs.getString("tempat")== null?"":rs.getString("tempat"));
	    			h.put("tarikh_tampal", rs.getDate("tarikh_tampal")==null?"":Format.format(rs.getDate("tarikh_tampal")));
	    			dataNotis.addElement(h);
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataNotis
	
	
	 //public static void hapusNotis(String id_notisawam,String id_dokumen) throws Exception{
	 public static void hapusNotis(String id_notisawam) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptnotisawam WHERE id_notisawam = '"+id_notisawam+"'";
	    		stmt.executeUpdate(sql);
   	 
//	    		sql = "DELETE FROM tblpptdokumen WHERE id_dokumen = '"+id_dokumen+"'";
//	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusNotis
	
	 @SuppressWarnings("unchecked")
	 public static void updateNotis(Hashtable data) throws Exception{
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    
		    		String id_notisawam = (String)data.get("id_notisawam");
		    		//String txtNotis = (String)data.get("txtNotis");
		    		String txdTarikhKeluar = (String)data.get("txdTarikhKeluar");
		    		//String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		    	
		    		String txdTarikhTampal = (String)data.get("txdTarikhTampal");
		    		String socTempatTampal = (String)data.get("socTempatTampal");
		    		String txtTempat = (String)data.get("txtTempat");
		        	
		    		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
		    		
		    		String TK = "to_date('" + txdTarikhKeluar + "','dd/MM/yyyy')";
		    		//String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_notisawam", id_notisawam);
		    		//r.add("notis", txtNotis); 	
		    		r.add("jenis_tempat_tampal", socTempatTampal); 	
		    		r.add("tempat", txtTempat); 	
		    		r.add("tarikh_tampal",r.unquote(TTMPL));		    		
		    		r.add("tarikh_keluar",r.unquote(TK));
		    		//r.add("tarikh_terima",r.unquote(TT));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);	    		
		    		sql = r.getSQLUpdate("tblpptnotisawam");
		    		stmt.executeUpdate(sql);
		    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
	 }//close updateNotis
*/	 
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanah(String idPermohonan,String lot,String idpegawai) throws Exception{
			
		 listMaklumatTanah = new Vector();
			
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT DISTINCT m.id_hakmilik, mk.nama_mukim, m.luas_ambil, jh.kod_jenis_hakmilik, ";
					sql += " m.no_hakmilik, u.user_name as nama_pegawai, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert,";
					sql += " m.no_lot, m.no_pt, ";
					
					sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
					sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
					
					sql += " CASE ";
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT, m.seksyen, m.no_subjaket ";  
					sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
					sql += " Tblrujdaerah d, Users u, Tblpptborange g, Tblpptborangehakmilik beh ";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND p.id_fail = f.id_fail "; 
					sql += " AND p.id_daerah = d.id_daerah(+)";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
					sql += " AND m.id_pegawai = u.user_id(+)";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
					sql += " AND beh.id_hakmilik = m.id_hakmilik ";
					sql += " AND beh.id_borange = g.id_borange ";
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT / NAMA PB
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
								   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
			                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
			                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
			                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
			                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
						}
					}//close if nolot
					
					//ID PEGAWAI
					if (idpegawai != "" && idpegawai != null) {
							sql += " AND m.id_pegawai = '"+idpegawai+"'";
					}//close if idpegawai
					
					//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(NO_LOTPT,10) asc, mk.nama_mukim asc";
					sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
						h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
						h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
						h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
						h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
						h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
						h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
						
						if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
							
							double luasAmbil = rs.getDouble("luas_ambil");
							String LA = Utils.formatLuasHM(luasAmbil);
							h.put("luas_ambil",LA);
									
						}else{
							h.put("luas_ambil","0");
						}
						
						if(rs.getString("id_unitluasambil_convert") != null){
							
							if(rs.getString("id_unitluasambil_convert").equals("1")){
								h.put("unitByKategori", "Hektar");
							}else{
								h.put("unitByKategori", "Meter Persegi");
							}			
						}else{
							h.put("unitByKategori", "");
						}
			
						listMaklumatTanah.addElement(h);
						bil++;	
						
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
		}//close setListMaklumatTanah
	
	@SuppressWarnings("unchecked")
	public static void simpanNotisInBulkData(Hashtable data,long id_notisawam,Db db) throws Exception{
		
	    Db db1 = null;
	    String sql = "";
	    
	    try{
	    	
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    		Statement stmt = db1.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String txdTarikhKeluar = (String)data.get("txdTarikhKeluar");
	        	String txdTarikhTampal = (String)data.get("txdTarikhTampal");
	    		String socTempatTampal = (String)data.get("socTempatTampal");
	    		String txtTempat = (String)data.get("txtTempat");	        	
	    		String TK = "to_date('" + txdTarikhKeluar + "','dd/MM/yyyy')";
	    		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_notisawam", id_notisawam);
	    		r.add("jenis_tempat_tampal", socTempatTampal); 	
	    		r.add("tempat", txtTempat); 	
	    		r.add("tarikh_keluar",r.unquote(TK));
	    		r.add("tarikh_tampal",r.unquote(TTMPL));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptnotisawam");
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close simpanNotisInBulkData
	
	@SuppressWarnings("unchecked")
	public static void simpanNotisInBulk(Hashtable data,String idHakmilik,long id_notisawam, Db db) throws Exception{
		
	    Db db1 = null;
	    String sql = "";
	    
	    try{
	    	
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    		Statement stmt = db1.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_notisawam", id_notisawam);
	    		r.add("id_hakmilik", idHakmilik);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptnotisawamhakmilik",db1);
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}//close finally
	   
	  }//close simpanNotis
	
	
	@SuppressWarnings("unchecked")
	public static void setListHakmilikNotisInBulk(String id_permohonan,String LocationType,String id_notisawam)throws Exception {
	    
		listNotisHakmilikInBulk = new Vector();
		
		Db db = null;
		listNotisHakmilikInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT M.ID_HAKMILIK, M.SEKSYEN,  JH.KOD_JENIS_HAKMILIK, ";
	    		sql += " M.NO_HAKMILIK, MK.NAMA_MUKIM, ";  
	    		sql += " CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND ";  
	    		sql += " M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || ";  
	    		sql += " M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41) ";  
	    		sql += " ELSE '' END AS NO_LOTPT, M.NO_SUBJAKET ";  
	    		sql += " ,(SELECT COUNT(M1.ID_HAKMILIK) FROM TBLPPTHAKMILIK M1, TBLPPTNOTISAWAMHAKMILIK A1, TBLPPTNOTISAWAM B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
	    		sql += " AND A1.ID_NOTISAWAM = B1.ID_NOTISAWAM ";  
	    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";  
	    		sql += " AND B1.ID_NOTISAWAM = '"+id_notisawam+"' )AS SELECTEDCB ";  
	    		sql += " FROM TBLPPTPERMOHONAN P, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH, ";  
	    		sql += " TBLPPTBORANGEHAKMILIK BEH, TBLPPTBORANGE G ";
	    		sql += " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN(+) ";   
	    		sql += " AND M.ID_NEGERI = N.ID_NEGERI ";  
	    		sql += " AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";
	    		sql += " AND M.ID_LOT = LT.ID_LOT(+) "; 
	    		sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";   
	    		sql += " AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y' "; 
	    		sql += " AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
	    		sql += " AND BEH.ID_HAKMILIK = M.ID_HAKMILIK ";
	    		sql += " AND BEH.ID_BORANGE = G.ID_BORANGE ";
	    		if(!LocationType.equals("")){
	    		sql += " AND M.ID_HAKMILIK NOT IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTNOTISAWAMHAKMILIK A1, TBLPPTNOTISAWAM B1 "; 
	    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";
	    		sql += " AND A1.ID_NOTISAWAM = B1.ID_NOTISAWAM ";
	    		sql += " AND M1.ID_PERMOHONAN = P.ID_PERMOHONAN "; 
	    		sql += " AND NVL(B1.JENIS_TEMPAT_TAMPAL,0) = '"+LocationType+"' ";
		    		if(!id_notisawam.equals("")){
		    		sql += " AND B1.ID_NOTISAWAM <> '"+id_notisawam+"' ";
		    		}
	    		sql += " )";
	    		}
	    		sql += " AND P.ID_PERMOHONAN = '"+id_permohonan+"' ";
	    		
	    		myLogger.info("sql[listNotisHakmilikInBulk] : "+sql);
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("selectedcb", rs.getInt("SELECTEDCB")== 0?0:rs.getInt("SELECTEDCB"));
	    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
	    			h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
	    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    			h.put("no_lotpt", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
	    			h.put("nama_mukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
	    			h.put("seksyen", rs.getString("SEKSYEN")== null?"":rs.getString("SEKSYEN"));
	    			h.put("no_subjaket", rs.getString("NO_SUBJAKET")== null?"":rs.getString("NO_SUBJAKET"));
	    			listNotisHakmilikInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListHakmilikNotisInBulk
	
	@SuppressWarnings("unchecked")
	public static void setListNotisInBulk(String id_permohonan)throws Exception {
	    
		listNotisInBulk = new Vector();
		
		Db db = null;
		listNotisInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT DISTINCT B.ID_NOTISAWAM, B.JENIS_TEMPAT_TAMPAL, B.TARIKH_TAMPAL, UPPER(trim(B.TEMPAT))AS TEMPAT, ";
	    		sql += " (SELECT COUNT(*) FROM TBLPPTNOTISAWAMHAKMILIK A1, TBLPPTNOTISAWAM B1 "; 
	    		sql += " WHERE B1.ID_NOTISAWAM = A1.ID_NOTISAWAM AND B1.ID_NOTISAWAM = B.ID_NOTISAWAM)AS TOTALHM ";
	    		sql += " FROM TBLPPTNOTISAWAMHAKMILIK A, TBLPPTNOTISAWAM B, TBLPPTHAKMILIK C ";
	    		sql += " WHERE A.ID_NOTISAWAM = B.ID_NOTISAWAM ";
	    		sql += " AND A.ID_HAKMILIK = C.ID_HAKMILIK ";
	    		sql += " AND C.ID_PERMOHONAN = '"+id_permohonan+"'";
	    	
	    		myLogger.info("sql[listNotisInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("TOTALHM", rs.getString("TOTALHM")== null?"":rs.getString("TOTALHM"));
	    			h.put("ID_NOTISAWAM", rs.getString("ID_NOTISAWAM")== null?"":rs.getString("ID_NOTISAWAM"));
	    			h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL")== null?"":rs.getString("JENIS_TEMPAT_TAMPAL"));
	    			
	    			//System.out.println(" s.length() :"+rs.getString("TEMPAT").trim().length());
	    			
	    			h.put("TEMPAT", (rs.getString("TEMPAT")== null ||  rs.getString("TEMPAT").equals("")) ? "-":rs.getString("TEMPAT"));
	    			
	    			if(rs.getString("JENIS_TEMPAT_TAMPAL").equals("1")){
	    				h.put("JENIS_NAMA_TEMPAT_TAMPAL","PTD / PTG");
	    			}else if(rs.getString("JENIS_TEMPAT_TAMPAL").equals("2")){
	    				h.put("JENIS_NAMA_TEMPAT_TAMPAL","PAPAN KENYATAAN AWAM DI DALAM MUKIM / BANDAR");
	    			}else if(rs.getString("JENIS_TEMPAT_TAMPAL").equals("3")){
	    				h.put("JENIS_NAMA_TEMPAT_TAMPAL","TEMPAT LAIN DI ATAS / BERHAMPIRAN TANAH");
	    			}else{
	    				h.put("JENIS_NAMA_TEMPAT_TAMPAL","");
	    			}
	    			
	    			h.put("TARIKH_TAMPAL", rs.getDate("TARIKH_TAMPAL")==null?"":Format.format(rs.getDate("TARIKH_TAMPAL")));
	    			listNotisInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListNotisInBulk
	
	@SuppressWarnings("unchecked")
	public static void setDataNotisInBulk(String id_notisawam)throws Exception {
	    
		dataNotisInBulk = new Vector();
		
		Db db = null;
		dataNotisInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT DISTINCT B.TARIKH_KELUAR, A.ID_NOTISAWAMHAKMILIK, B.ID_NOTISAWAM, B.JENIS_TEMPAT_TAMPAL, B.TARIKH_TAMPAL, UPPER(B.TEMPAT)AS TEMPAT ";
	    		sql += " FROM TBLPPTNOTISAWAMHAKMILIK A, TBLPPTNOTISAWAM B, TBLPPTHAKMILIK C ";
	    		sql += " WHERE A.ID_NOTISAWAM = B.ID_NOTISAWAM ";
	    		sql += " AND A.ID_HAKMILIK = C.ID_HAKMILIK ";
	    		sql += " AND B.ID_NOTISAWAM = '"+id_notisawam+"'";
	    	
	    		myLogger.info("sql[dataNotisInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("ID_NOTISAWAMHAKMILIK", rs.getString("ID_NOTISAWAMHAKMILIK")== null?"":rs.getString("ID_NOTISAWAMHAKMILIK"));
	    			h.put("ID_NOTISAWAM", rs.getString("ID_NOTISAWAM")== null?"":rs.getString("ID_NOTISAWAM"));
	    			h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL")== null?"":rs.getString("JENIS_TEMPAT_TAMPAL"));
	    			h.put("TEMPAT", rs.getString("TEMPAT")== null?"":rs.getString("TEMPAT"));
	    			h.put("TARIKH_TAMPAL", rs.getDate("TARIKH_TAMPAL")==null?"":Format.format(rs.getDate("TARIKH_TAMPAL")));
	    			h.put("TARIKH_KELUAR", rs.getDate("TARIKH_KELUAR")==null?"":Format.format(rs.getDate("TARIKH_KELUAR")));
	    			dataNotisInBulk.addElement(h);    		
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataNotisInBulk
	
	public static void deleteNotisInBulk(String id_notisawam) throws Exception{
		
		  Db db = null;
		  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptnotisawamhakmilik WHERE id_notisawam = '"+id_notisawam+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		sql = "DELETE FROM tblpptnotisawam WHERE id_notisawam = '"+id_notisawam+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		conn.commit();
	    		
	    }catch (SQLException se) { 
		    try {
		    	conn.rollback();
		    } catch (SQLException se2) {
		    	throw new Exception("Rollback error:"+se2.getMessage());
		    }
		    throw new Exception("Ralat : Hapus Fail ");
		}finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close deleteNotisInBulk
	
	@SuppressWarnings("unchecked")
	 public static void updateNotisInBulk(Hashtable data,Db db) throws Exception{
			
		    Db db1 = null;
		    String sql = "";
		    
		    try{
		      
		    	if (db == null) {
					db1 = new Db();
				} else {
					db1 = db;
				}
		    		Statement stmt = db1.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    
		    		String id_notisawam = (String)data.get("id_notisawam");
		    		String txdTarikhKeluar = (String)data.get("txdTarikhKeluar");
		    	
		    		String txdTarikhTampal = (String)data.get("txdTarikhTampal");
		    		String txtTempat = (String)data.get("txtTempat");
		        	
		    		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
		    		
		    		String TK = "to_date('" + txdTarikhKeluar + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_notisawam", id_notisawam);
		    		r.add("tempat", txtTempat); 	
		    		r.add("tarikh_tampal",r.unquote(TTMPL));		    		
		    		r.add("tarikh_keluar",r.unquote(TK));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);	    		
		    		sql = r.getSQLUpdate("tblpptnotisawam");
		    		stmt.executeUpdate(sql);
		    	
		    }//close try 
		    finally {
				if (db == null) {
					if (db1 != null)
						db1.close();
				}
			}
		   
	 }//close updateNotisInBulk
	
	
	public static void deleteListCB(String id_notisawam, Db db) throws Exception{
		
		  Db db1 = null;
		  //Connection conn = null;
		  String sql = "";
	    
		  try{
	      
			  if (db == null) {
					db1 = new Db();
				} else {
					db1 = db;
				}
	    		//conn = db1.getConnection();
				//conn.setAutoCommit(false);
	    		Statement stmt = db1.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptnotisawamhakmilik WHERE id_notisawam = '"+id_notisawam+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		//conn.commit();
	    		
	    }finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close deleteNotisInBulk
	
	
	@SuppressWarnings("unchecked")
	public static Vector getListHM(String id_notisawam)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  "SELECT M.ID_HAKMILIK, M.SEKSYEN,  JH.KOD_JENIS_HAKMILIK, "; 
		    		sql +=  "M.NO_HAKMILIK, MK.NAMA_MUKIM, ";    
		    		sql +=  "CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND ";   
		    		sql +=  "M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || ";   
		    		sql +=  "M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41) ";   
		    		sql +=  "ELSE '' END AS NO_LOTPT, M.NO_SUBJAKET ";               
		    		sql +=  "FROM TBLPPTNOTISAWAMHAKMILIK A, TBLPPTNOTISAWAM B, TBLPPTHAKMILIK M, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JH ";  
		    		sql +=  "WHERE A.ID_NOTISAWAM = B.ID_NOTISAWAM ";  
		    		sql +=  "AND A.ID_HAKMILIK = M.ID_HAKMILIK ";
		    		sql +=  "AND M.ID_LOT = LT.ID_LOT(+) ";
		    		sql +=  "AND M.ID_MUKIM = MK.ID_MUKIM(+) ";
		    		sql +=  "AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";
		    		sql +=  "AND B.ID_NOTISAWAM = '"+id_notisawam+"' ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
	
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("no_subjaket", rs.getString("NO_SUBJAKET")== null?"":rs.getString("NO_SUBJAKET"));
		    			h.put("seksyen", rs.getString("SEKSYEN")== null?"":rs.getString("SEKSYEN"));
		    			h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
		    			h.put("nama_mukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
		    			h.put("no_lotpt", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
		    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));	
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListHM
	
}//close class
