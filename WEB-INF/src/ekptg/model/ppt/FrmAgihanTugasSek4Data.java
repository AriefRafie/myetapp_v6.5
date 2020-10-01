package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmAgihanTugasSek4Data {
	static Logger myLogger = Logger.getLogger(FrmAgihanTugasSek4Data.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private static  Vector dataLaporanTanah = null;
	private static  Vector dataPegawai = null;
	private static  Vector dataAgihan = null;
	
	public static  Vector getListCarian(){
		return listcarian;
	}
	public static  Vector getDataPegawai(){
		return dataPegawai;
	}
	public static  Vector getDataAgihan(){
		return dataAgihan;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPermohonan(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = " SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +=" tg.id_tugas, tg.tarikh_agih, u.user_name as nama_pegawai, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttugas tg, ";
		    		sql +=" Users u ";
		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +=" AND p.id_status = s.id_status ";
		    		sql +=" AND tg.id_permohonan(+) = p.id_permohonan ";
		    		sql +=" AND tg.id_pegawaipenerima = u.user_id(+) ";
		    		sql +=" AND f.id_suburusan = '51' ";
		    		sql +=" AND p.id_status IN (127,148)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

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
		    			h.put("id_tugas", rs.getString("id_tugas")== null?"":rs.getString("id_tugas"));
		    			h.put("nama_pegawai", rs.getString("nama_pegawai")== null?"<b>BELUM DIAGIHKAN</b>":rs.getString("nama_pegawai"));
		    			h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"<b>BELUM DIAGIHKAN</b>":Format.format(rs.getDate("tarikh_agih")));
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
	      
	    		sql = " SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +=" tg.id_tugas, tg.tarikh_agih, g.user_name as nama_pegawai, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttugas tg, ";
	    		sql +=" Users g ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND tg.id_permohonan(+) = p.id_permohonan ";
	    		sql +=" AND tg.id_pegawaipenerima = g.user_id(+) ";
	    		sql +=" AND f.id_suburusan = '51' ";
	    		sql +=" AND p.id_status IN (127,148)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
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
						sql = sql + " AND UPPER(tg.tarikh_agih) = "+cariTarikh;
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
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			h.put("id_tugas", rs.getString("id_tugas")== null?"":rs.getString("id_tugas"));
	    			h.put("nama_pegawai", rs.getString("nama_pegawai")== null?"<b>BELUM DIAGIHKAN</b>":rs.getString("nama_pegawai"));
	    			h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"<b>BELUM DIAGIHKAN</b>":Format.format(rs.getDate("tarikh_agih")));
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
	public static void setDataPegawai(String id_pegawai)throws Exception {
	    
		dataPegawai = new Vector();
		
		Db db = null;
		dataPegawai.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT b.keterangan as jawatan FROM Users_internal a, Tblrujjawatan b "; 
	    		sql += " WHERE a.id_jawatan = b.id_jawatan(+) ";
	    		sql += " AND a.user_id = '"+id_pegawai+"'";
	    		sql += " AND keterangan is not null";
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("jawatan", rs.getString("jawatan")== null?"":rs.getString("jawatan"));	
	    			dataPegawai.addElement(h);
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataPegawai
	
	@SuppressWarnings("unchecked")
	public static void simpanAgihan(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");

	    		String socPegawai = (String)data.get("socPegawai");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		String id_fail = (String)data.get("id_fail");
	    		
	    		SQLRenderer r = new SQLRenderer();	    	
	    		r.add("id_permohonan", id_permohonan);	
	    		r.add("id_fail", id_fail);	
	    		r.add("id_pegawaipenerima",socPegawai); 
	    		r.add("perihal_agih",txtCatatan); 
	    		r.add("id_pegawai",id_user); 
	    		r.add("tarikh_agih",r.unquote("sysdate"));  		    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblppttugas");
	    		myLogger.info("sql : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    		SQLRenderer rs = new SQLRenderer();
	    		rs.update("id_permohonan", id_permohonan);	
	    		rs.add("id_status", "148");			    		
	    		rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
	    		rs.add("id_kemaskini",id_user);    		
	    		sql = rs.getSQLUpdate("Tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanAgihan
	
	@SuppressWarnings("unchecked")
	public static void setDataAgihan(String id_permohonan)throws Exception {
	    
		dataAgihan = new Vector();
		
		Db db = null;
		dataAgihan.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT a.id_tugas,a.id_pegawai,a.id_pegawaipenerima,a.tarikh_agih,a.perihal_agih, ";
	    		sql += " b.user_name as pegawai_agih, ";
	    		sql += " c.user_name as pegawai_terima, e.keterangan as jawatan "; 
	    		sql += " FROM Tblppttugas a, Users b, Users c, Users_internal d, Tblrujjawatan e ";
	    		sql += " WHERE a.id_pegawai = b.user_id(+) ";
	    		sql += " AND c.user_id = d.user_id ";
	    		sql += " AND d.id_jawatan = e.id_jawatan(+)";
				sql += " AND a.id_pegawaipenerima = c.user_id(+) ";
				sql += " AND a.id_permohonan = '"+id_permohonan+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;

	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("id_tugas", rs.getString("id_tugas")== null?"":rs.getString("id_tugas"));	
	    			h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"":Format.format(rs.getDate("tarikh_agih")));
	    			h.put("id_pegawaipenerima", rs.getString("id_pegawaipenerima")== null?"":rs.getString("id_pegawaipenerima"));	
	    			h.put("id_pegawai", rs.getString("id_pegawai")== null?"":rs.getString("id_pegawai"));	
	    			h.put("perihal_agih", rs.getString("perihal_agih")== null?"":rs.getString("perihal_agih"));	
	    			h.put("pegawai_agih", rs.getString("pegawai_agih")== null?"":rs.getString("pegawai_agih"));	
	    			h.put("jawatan", rs.getString("jawatan")== null?"":rs.getString("jawatan"));	
	    			h.put("pegawai_terima", rs.getString("pegawai_terima")== null?"":rs.getString("pegawai_terima"));	
	    			dataAgihan.addElement(h);
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataPegawai
	
	@SuppressWarnings("unchecked")
	public static void updateAgihan(Hashtable data) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_tugas = (String)data.get("id_tugas");

	    		String socPegawai = (String)data.get("socPegawai");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		SQLRenderer r = new SQLRenderer();	    	
	    		r.update("id_tugas", id_tugas);	
	    		r.add("id_pegawaipenerima",socPegawai); 
	    		r.add("perihal_agih",txtCatatan); 
	    		r.add("id_pegawai",id_user); 
	    		r.add("tarikh_agih",r.unquote("sysdate"));  		    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblppttugas");
	    		myLogger.info("tblppttugas : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateAgihan
	
}//close class
