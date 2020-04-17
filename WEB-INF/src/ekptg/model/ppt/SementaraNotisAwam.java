package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class SementaraNotisAwam {
	static Logger myLogger = Logger.getLogger(SementaraNotisAwam.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector listNotis = null;
	private static Vector dataNotis = null;	
	
	public static Vector getListCarian(String userIdNegeri){
		return listcarian;
	}	
	public static Vector getListNotis(){
		return listNotis;
	}
	public static Vector getDataNotis(){
		return dataNotis;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
				 
		    Db db = null;
		    String sql = "";		    
		    try{		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
		    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '53' ";
		    		sql +="AND p.id_status IN (52,26,134) ";
		    		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
		    		
		    		// ADDED BY ELLY 14 JUNE 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}
		    		
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
		public static void setListCarian(String carianNofail, String carianTarikh, String status, String userIdNegeri)throws Exception {
			
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
		      
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk ";
		    		sql +=", p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '53' ";
		    		sql +="AND p.id_status IN (52,26,134) ";
		    		
		    		// ADDED BY ELLY 14 JUNE 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}
		     
		    		//NO FAIL
					if (carianNofail != "" && carianNofail != null) {
						if (!nofail.equals("")) {
							//setLimit = false;
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
							sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
						}
					}//close carian by tarikh
		
		    		//STATUS
					if (status != "" && status != null) {
							sql = sql + " AND UPPER(s.id_status) = '"+status+"'";
					}//close carian by status
		
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

		public static void simpanNotis(Hashtable data) throws Exception
		{
			
		  Db db = null;
		  String sql = "";
		  
		  try{
		    
		  		db = new Db();
		  		Statement stmt = db.getStatement();
		  		
		  		String id_user = (String)data.get("id_user");		  
		  		String id_hakmilik = (String)data.get("id_hakmilik");
		  		String id_permohonan = (String)data.get("id_permohonan");		  		
		      	String txdTarikhTampal = (String)data.get("txdTarikhTampal");
		  		String socTempatTampal = (String)data.get("socTempatTampal");
		  		String txtTempat = (String)data.get("txtTempat");
		      	
		  		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
		  		
		  		SQLRenderer r = new SQLRenderer();
		  		r.add("id_permohonan",id_permohonan);
		  		r.add("id_hakmilik", id_hakmilik);	
		  		r.add("jenis_tempat_tampal", socTempatTampal); 	
		  		r.add("tempat", txtTempat); 	
		  		r.add("tarikh_tampal",r.unquote(TTMPL));
		  		r.add("tarikh_masuk",r.unquote("sysdate"));
		  		r.add("id_masuk",id_user);	    		
		  		sql = r.getSQLInsert("tblpptnotisawam");
		  		stmt.executeUpdate(sql);
		  		myLogger.info("SQL insert notis :: "+sql);
		  }//close try 
		  finally {
		    if (db != null) db.close();
		  }//close finally
		 
		}//close simpanNotis
		
		@SuppressWarnings("unchecked")
		public static void setListNotis(String id_hakmilik)throws Exception {
		  
			listNotis = new Vector();
			
			Db db = null;
			listNotis.clear();
		  String sql = "";
		  
		  try{
		  		db = new Db();
		  		Statement stmt = db.getStatement();
		    
		  		sql =  " SELECT DISTINCT a.id_notisawam,a.id_dokumen,a.notis,a.tarikh_keluar,a.tarikh_terima,b.nama_fail, ";
		  		sql += " a.jenis_tempat_tampal,a.tempat,a.tarikh_tampal";
		  		sql += " FROM Tblpptnotisawam a, Tblpptdokumen b ";
		  		sql += " WHERE a.id_dokumen = b.id_dokumen(+) ";
		  		sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
		  		myLogger.info("SQL setListNotis :: "+sql);
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
		
		@SuppressWarnings("unchecked")
		public static void updateStatus(Hashtable data) throws Exception{
				
			  Db db = null;
			  String sql = "";		  
			  try{		    
				db = new Db();
		  		Statement stmt = db.getStatement();
		  		
		  		String id_user = (String)data.get("id_user");	    	
		  		String id_permohonan = (String)data.get("id_permohonan");
		  		
		  		//status notis awam
		  		String status = "52";
		  		
		  		SQLRenderer r = new SQLRenderer();
		  		r.update("id_permohonan", id_permohonan);	   
		  		r.add("id_status",status);  
		  		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		  		r.add("id_kemaskini",id_user);    		
		  		sql = r.getSQLUpdate("tblpptpermohonan");
		  		stmt.executeUpdate(sql);
		  		myLogger.info("SQL update status notis :: "+sql);
		  }//close try 
		  finally {
		    if (db != null) db.close();
		  }//close finally
		 
		}//close updateStatus
		
		@SuppressWarnings("unchecked")
		public static void setDataNotis(String id_notisawam)throws Exception {
		  
			dataNotis = new Vector();
			
			Db db = null;
			dataNotis.clear();
		  String sql = "";
		  
		  try{
		  		db = new Db();
		  		Statement stmt = db.getStatement();
		    
		  		sql =  " SELECT DISTINCT a.id_notisawam,a.id_dokumen,a.notis,a.tarikh_keluar,a.tarikh_terima,b.nama_fail, ";
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
			 
		//  		sql = "DELETE FROM tblpptdokumen WHERE id_dokumen = '"+id_dokumen+"'";
		//  		stmt.executeUpdate(sql);
		  		
		  }//close try 
		  finally {
		    if (db != null) db.close();
		  }//close finally
		 
		}//close hapusNotis
		
		@SuppressWarnings("unchecked")
			public static void updateNotis(Hashtable data) throws Exception
			  {
				
			    Db db = null;
			    String sql = "";
			    
			    try{
			      
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			    		
			    		String id_user = (String)data.get("id_user");
			    
			    		String id_notisawam = (String)data.get("id_notisawam");
			    		//String txtNotis = (String)data.get("txtNotis");
//			    		String txdTarikhKeluar = (String)data.get("txdTarikhKeluar");
			    		//String txdTarikhTerima = (String)data.get("txdTarikhTerima");
			    	
			    		String txdTarikhTampal = (String)data.get("txdTarikhTampal");
			    		String socTempatTampal = (String)data.get("socTempatTampal");
			    		String txtTempat = (String)data.get("txtTempat");
			        	
			    		String TTMPL = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
			    		
//			    		String TK = "to_date('" + txdTarikhKeluar + "','dd/MM/yyyy')";
			    		//String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
			    		
			    		SQLRenderer r = new SQLRenderer();
			    		r.update("id_notisawam", id_notisawam);
			    		//r.add("notis", txtNotis); 	
			    		r.add("jenis_tempat_tampal", socTempatTampal); 	
			    		r.add("tempat", txtTempat); 	
			    		r.add("tarikh_tampal",r.unquote(TTMPL));		    		
//			    		r.add("tarikh_keluar",r.unquote(TK));
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

}
