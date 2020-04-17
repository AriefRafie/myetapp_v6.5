package ekptg.model.ppt;
/*
 * @author 
 * NORZAILY BINTI JASMI
 */
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class SementaraJPPH {
	static Logger myLogger = Logger.getLogger(SementaraJPPH.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	
	public static Vector getListCarian(){
		return listcarian;
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
		    		sql +="AND p.id_status IN (58,43,147)";
		    		
		    		// ADDED BY ELLY 14 JUN 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}
		    		
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		myLogger.info("DEFAULT :: "+sql);
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
	    		sql +="AND p.id_status IN (58,43,147)";
	    		
	    		// ADDED BY ELLY 14 JUN 2010
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

	public static void updateSuburusanHakmilik(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_fail = (String)data.get("id_fail");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		//update n add tblrujsuburusanstatus
			    SQLRenderer r = new SQLRenderer();
				r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);	 
				  
				r.clear();
				
	     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
	     		r.add("ID_PERMOHONAN",id_permohonan);
	     		r.add("ID_HAKMILIK",id_hakmilik);
	     		r.add("ID_FAIL",id_fail);
	     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     		r.add("AKTIF",1);
				r.add("ID_MASUK",id_user);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close addSuburusanHakmilik

	public static void updateSuburusanStatusFailPPT(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_fail = (String)data.get("id_fail");
	    		
	    		//update n add tblrujsuburusanstatus
			    SQLRenderer r = new SQLRenderer();
				r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
				stmt.executeUpdate(sql);	 
				  
				r.clear();
				
				r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
				r.add("ID_PERMOHONAN",id_permohonan);
				r.add("ID_FAIL",id_fail);
				r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
				r.add("AKTIF",1);
				r.add("ID_MASUK",id_user);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
				stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateSuburusanStatusFailPPT

	public static void updateStatus(String idpermohonan,String idUser,String idstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan",idpermohonan);
	    		r.add("id_status",idstatus);      			
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",idUser);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	 }
	
}
