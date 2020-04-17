package ekptg.model.ppt;

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

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmAgihanTugasSek8Data {
	static Logger myLogger = Logger.getLogger(FrmAgihanTugasSek8Data.class);
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector listcarian = null;
	private static  Vector dataLaporanTanah = null;
	private static  Vector dataPegawai = null;
	private static  Vector dataAgihan = null;
	private static  Vector listHMByPegawai = null;
	private static  Vector dataStatistikAgihan = null;
	private static  Vector listPegawaiTerimaHM = null;
	private static  Vector totalLotBelumAgih = null;
	private  Vector listMaklumatTanah = null;
	private static  Vector listAgihHM = null;
	
	private static  Vector listAgihHM_INFO = null;
	
	private static  Vector listAgihHM_INFO_ALL = null;
	
	public static  Vector getListCarian(){
		return listcarian;
	}
	public static  Vector getDataPegawai(){
		return dataPegawai;
	}
	public static  Vector getDataAgihan(){
		return dataAgihan;
	}
	public static  Vector getListHMByPegawai(){
		return listHMByPegawai;
	}
	public static  Vector getDataStatistikAgihan(){
		return dataStatistikAgihan;
	}
	public static  Vector getListPegawaiTerimaHM(){
		return listPegawaiTerimaHM;
	}
	public static  Vector getTotalLotBelumAgih(){
		return totalLotBelumAgih;
	}
	public Vector getListMaklumatTanah(){
		return listMaklumatTanah;
	}
	public Vector getListAgihHM(){
		return listAgihHM;
	}
	
	public Vector getListAgihHM_INFO(){
		return listAgihHM_INFO;
	}
	
	public Vector getListAgihHM_INFO_ALL(){
		return listAgihHM_INFO_ALL;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (16,148,1612198)";
		
		//sql +="AND p.id_permohonan IN ";
		sql += " and (p.id_status='16' "; 
		sql += " OR p.id_status='148' OR p.id_status='1612198' "; 
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
			   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
		sql += " OR p.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
		sql += " where tx.id_permohonan = p.id_permohonan)) ";
		
		
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
		     
//		    		sql = " SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
//		    		sql +=" tg.id_tugas, tg.tarikh_agih, g.nama_pegawai ";
//		    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttugas tg, ";
//		    		sql +=" Tblrujpegawai g ";
//		    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
//		    		sql +=" AND p.id_status = s.id_status ";
//		    		sql +=" AND tg.id_permohonan(+) = p.id_permohonan ";
//		    		sql +=" AND tg.id_pegawaipenerima = g.id_pegawai(+) ";
//		    		sql +=" AND f.id_suburusan = '52' ";
//		    		sql +=" AND p.id_status IN (16,148)";
//		    		sql +=" AND f.no_fail is not null";
//		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		
		    		
		    		/*
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '52' ";
		    		sql +="AND p.id_status IN (16,148,1612198)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		
		    		sql =  sqlListPermohonan(userIdNegeri);
		    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
//		    			h.put("id_tugas", rs.getString("id_tugas")== null?"":rs.getString("id_tugas"));
//		    			h.put("nama_pegawai", rs.getString("nama_pegawai")== null?"BELUM DIAGIHKAN":rs.getString("nama_pegawai"));
//		    			h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"BELUM DIAGIHKAN":Format.format(rs.getDate("tarikh_agih")));
//		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
//		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
//		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
//		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
//		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
//		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
//		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
//		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
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
		    catch (Exception re) {
		    	myLogger.error("Error: ", re);
		    	throw re;
		    	}
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
	      
//	    		sql = " SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
//	    		sql +=" tg.id_tugas, tg.tarikh_agih, g.nama_pegawai ";
//	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblppttugas tg, ";
//	    		sql +=" Tblrujpegawai g ";
//	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
//	    		sql +=" AND p.id_status = s.id_status ";
//	    		sql +=" AND tg.id_permohonan(+) = p.id_permohonan ";
//	    		sql +=" AND tg.id_pegawaipenerima = g.id_pegawai(+) ";
//	    		sql +=" AND f.id_suburusan = '52' ";
//	    		sql +=" AND p.id_status IN (16,148)";
	    		
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (16,148)";
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
//	    			h.put("id_tugas", rs.getString("id_tugas")== null?"":rs.getString("id_tugas"));
//	    			h.put("nama_pegawai", rs.getString("nama_pegawai")== null?"BELUM DIAGIHKAN":rs.getString("nama_pegawai"));
//	    			h.put("tarikh_agih", rs.getDate("tarikh_agih")==null?"BELUM DIAGIHKAN":Format.format(rs.getDate("tarikh_agih")));
//	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
//	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
//	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
//	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
//	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
//	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
//	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
//	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
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
	    			listcarian.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    }// 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	  
	//penambahan yati
		public static Vector listEmel = null;
		
		public static Vector getListEmel() {
			return listEmel;
		}
		
		//get detail user
			@SuppressWarnings("unchecked")
			public static Vector setListEmel(String id_ppengarah) throws Exception {
			
				Vector listEmel = new Vector();
				
				Db db = null;
				String sql = "";
				
				try {
						db = new Db();
						Statement stmt = db.getStatement();
					
						sql =  "SELECT U.USER_ID AS USER_ID, U.USER_NAME AS USER_NAME, UI.EMEL AS EMEL FROM USERS U, USERS_INTERNAL UI " +
								"WHERE U.USER_ID= UI.USER_ID " +
								"AND U.USER_ID ='"+id_ppengarah+"' ";
						//myLogger.info("GET EMEL : "+sql);
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h = null;
						while (rs.next()) {
							h = new Hashtable();
							
							h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
							h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
							h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
							listEmel.addElement(h);
							
					}
						
						//System.out.println("SQL LIST email yat :::"+listEmel);
					return listEmel;
				}catch (Exception re) {
					myLogger.error("Error: ", re);
					throw re;
					} finally {
					if(db != null)db.close();
				}
			}//close setNamaPengarah
			
			//penambahan yati
			public static Vector listEmelPenolong = null;
			
			public static Vector getListEmelPenolong() {
				return listEmelPenolong;
			}
		
			//get detail user
				@SuppressWarnings("unchecked")
				public static Vector setListEmelPenolong(String id_penolong) throws Exception {
				
					Vector listEmelPenolong = new Vector();
					
					Db db = null;
					String sql = "";
					
					try {
							db = new Db();
							Statement stmt = db.getStatement();
						
							sql =  "SELECT U.USER_ID AS USER_ID, U.USER_NAME AS USER_NAME, UI.EMEL AS EMEL FROM USERS U, USERS_INTERNAL UI " +
									"WHERE U.USER_ID= UI.USER_ID " +
									"AND U.USER_ID ='"+id_penolong+"' ";
							//myLogger.info("GET EMEL : "+sql);
							ResultSet rs = stmt.executeQuery(sql);
							Hashtable h = null;
							while (rs.next()) {
								h = new Hashtable();
								
								h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
								h.put("nama_penolong", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
								h.put("emel", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
								listEmelPenolong.addElement(h);
								
						}
						return listEmelPenolong;
					}catch (Exception re) {
						myLogger.error("Error: ", re);
						throw re;
						} finally {
						if(db != null)db.close();
					}
				}//close setNamaPenolong
				
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
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataPegawai
	
	@SuppressWarnings("unchecked")
	public static void updatestatus(Hashtable data,String id_permohonan) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    	
	    		SQLRenderer rs = new SQLRenderer();
	    		rs.update("id_permohonan", id_permohonan);	
	    		rs.add("id_status", "148");			    		
	    		rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
	    		rs.add("id_kemaskini",id_user);    		
	    		sql = rs.getSQLUpdate("Tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanAgihan
	
	
	@SuppressWarnings("unchecked")
	public static void updatestatusAgihanPentadbir(Hashtable data,String id_permohonan) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    	
	    		SQLRenderer rs = new SQLRenderer();
	    		rs.update("id_permohonan", id_permohonan);	
	    		rs.add("id_status", "1612198");			    		
	    		rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
	    		rs.add("id_kemaskini",id_user);    		
	    		sql = rs.getSQLUpdate("Tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	    		
	    		
    	
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanAgihan
	
//	/161284882
	
	
	
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
	    		sql += " c.user_name as pegawai_terima, c.jawatan "; 
	    		sql += " FROM Tblppttugas a, Users b, Users c ";
	    		sql += " WHERE a.id_pegawai = b.user_id(+) ";
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
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
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
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateAgihan
	
	@SuppressWarnings("unchecked")
	public static void setListHMByPegawai(String id_permohonan,String id_pegawai,String lot)throws Exception {
	    
		listHMByPegawai = new Vector();
		
		Db db = null;
		listHMByPegawai.clear();
	    String sql = "";
	    String noLOT = lot.trim();
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT B.SEKSYEN,B.ID_HAKMILIK, B.ID_PEGAWAI, B.NO_HAKMILIK, B.NO_LOT, C.NAMA_MUKIM, B.LUAS_AMBIL, B.ID_KATEGORITANAH, ";
	    		sql += " B.NO_PT, D.KETERANGAN AS KOD_LOT, B.ID_UNITLUASAMBIL_CONVERT, E.KOD_JENIS_HAKMILIK, B.NO_SUBJAKET, ";	    		
	    		sql += " CASE ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT "; 
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN D.KETERANGAN || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN  D.KETERANGAN || B.NO_PT "; 
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN D.KETERANGAN || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT ";  				
	    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLRUJMUKIM C, TBLRUJLOT D, TBLRUJJENISHAKMILIK E ";
	    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
	    		sql += " AND B.ID_MUKIM = C.ID_MUKIM(+) ";
	    		sql += " AND B.ID_LOT = D.ID_LOT(+) ";
	    		sql += " AND B.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK(+)";
	    		sql += " AND NVL(b.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(b.flag_penarikan_keseluruhan,0) <> 'Y'";
	    		sql += " AND A.ID_PERMOHONAN = '"+id_permohonan+"'";
				sql += " AND NVL(B.ID_PEGAWAI,0) IN ('"+id_pegawai+"',0) ";
	    	
				//NO LOT
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND (UPPER(b.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(b.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(d.keterangan) LIKE '%" + noLOT.toUpperCase() + "%')";
					}
				}//close if nolot
				
				//sql += " ORDER BY LPAD(B.NO_SUBJAKET,10) asc, LPAD(B.NO_LOT,10) asc, LPAD(B.NO_PT,10) asc, LPAD(NO_LOTPT,10) asc, C.NAMA_MUKIM asc " ;
				sql += " ORDER BY c.nama_mukim asc, LPAD(b.no_lot,20) asc, LPAD(b.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(b.no_subjaket,20) asc";
				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("seksyen", rs.getString("SEKSYEN")== null?"":rs.getString("SEKSYEN"));
	    			h.put("no_subjaket", rs.getString("NO_SUBJAKET")== null?"":rs.getString("NO_SUBJAKET"));
	    			h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));	
	    			h.put("no_lotpt", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));	
	    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));	
	    			h.put("id_pegawai", rs.getString("ID_PEGAWAI")== null?"":rs.getString("ID_PEGAWAI"));	
	    			h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));	
	    			h.put("no_lot", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));	
	    			h.put("nama_mukim", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));	
	    			h.put("no_pt", rs.getString("NO_PT")== null?"":rs.getString("NO_PT"));
	    			h.put("kod_lot", rs.getString("KOD_LOT")== null?"":rs.getString("KOD_LOT"));
	    			
	    			if(rs.getString("LUAS_AMBIL") != null && rs.getString("LUAS_AMBIL") != ""){
						
						double luasAmbil = rs.getDouble("LUAS_AMBIL");
						h.put("luas_ambil",Utils.formatLuasHM(luasAmbil));
								
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
	    			
	    			listHMByPegawai.addElement(h);
	    			bil++;
	    	}			    
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListHMByPegawai
	
	@SuppressWarnings("unchecked")
	public static void simpanFlagHM(Hashtable data, String idHakmilik,String id_pegawai) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		
	    		SQLRenderer rs = new SQLRenderer();
	    		rs.update("id_hakmilik", idHakmilik);	
	    		rs.add("id_pegawai", id_pegawai);			    		
	    		rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
	    		rs.add("id_kemaskini",id_user);    		
	    		sql = rs.getSQLUpdate("Tblppthakmilik");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanFlagHM
	
	@SuppressWarnings("unchecked")
	public static void deleteFlagHM(Hashtable data,String id_pegawai) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	
	    		String id_permohonan = (String)data.get("id_permohonan");	    	
	    		
	    		SQLRenderer rs = new SQLRenderer();
	    		rs.update("id_permohonan", id_permohonan);	
	    		rs.update("id_pegawai", id_pegawai);
	    		rs.add("id_pegawai","");
	    		rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
	    		rs.add("id_kemaskini",id_user);    		
	    		sql = rs.getSQLUpdate("Tblppthakmilik");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close deleteFlagHM
	
	
	@SuppressWarnings("unchecked")
	public static void setDataStatistikAgihan(String userIdNegeri)throws Exception {
	    
		dataStatistikAgihan = new Vector();
		
		Db db = null;
		dataStatistikAgihan.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT (SELECT COUNT(DISTINCT TBLPPTHAKMILIK.ID_PERMOHONAN) FROM TBLPPTHAKMILIK WHERE TBLPPTHAKMILIK.ID_PEGAWAI = C.USER_ID)AS TOTAL_PERMOHONAN, ";
	    		sql += " (SELECT COUNT(DISTINCT TBLPPTHAKMILIK.ID_HAKMILIK) FROM TBLPPTHAKMILIK WHERE TBLPPTHAKMILIK.ID_PEGAWAI = C.USER_ID)AS TOTAL_HAKMILIK, ";
	    		sql += " C.USER_ID AS ID_PEGAWAI, C.USER_NAME AS NAMA_PEGAWAI, F.ID_NEGERI ";
	    		sql += " FROM TBLPFDFAIL F, TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, USERS C ";
	    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
	    		sql += " AND A.ID_FAIL = F.ID_FAIL ";
				sql += " AND B.ID_PEGAWAI = C.USER_ID ";
				if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND F.ID_NEGERI ='"+userIdNegeri+"'";
	    		}
				sql += " ORDER BY C.USER_NAME ";
				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("total_permohonan", rs.getString("TOTAL_PERMOHONAN")== null?"":rs.getString("TOTAL_PERMOHONAN"));	
	    			h.put("total_hakmilik", rs.getString("TOTAL_HAKMILIK")== null?"":rs.getString("TOTAL_HAKMILIK"));	
	    			h.put("nama_pegawai", rs.getString("NAMA_PEGAWAI")== null?"":rs.getString("NAMA_PEGAWAI"));	
	    		
	    			dataStatistikAgihan.addElement(h);
	    			bil++;
	    	}			    
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataStatistikAgihan
	
	@SuppressWarnings("unchecked")
	public static void setListPegawaiTerimaHM(String id_permohonan)throws Exception {
	    
		listPegawaiTerimaHM = new Vector();
		
		Db db = null;
		listPegawaiTerimaHM.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT UPPER(C.USER_NAME) AS USER_NAME, ";
	    		//sql += " D.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, ";
	    		sql += " (SELECT COUNT(HM.ID_HAKMILIK) FROM TBLPPTHAKMILIK HM WHERE HM.ID_PERMOHONAN = A.ID_PERMOHONAN AND HM.ID_PEGAWAI = C.USER_ID )AS TOTALHM ";
	    		sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, USERS C, TBLRUJJENISHAKMILIK D ";
	    		sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
	    		sql += " AND B.ID_PEGAWAI = C.USER_ID ";
	    		sql += " AND B.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK(+) ";
	    		sql += " AND A.ID_PERMOHONAN = '"+id_permohonan+"'";
	    		sql += " ORDER BY USER_NAME ";
				
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("totalhm", rs.getString("TOTALHM")== null?"":rs.getString("TOTALHM"));	
	    			h.put("user_name", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));	
	    			//h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));	
	    			//h.put("no_hakmilik", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));	
	    			listPegawaiTerimaHM.addElement(h);
	    			bil++;
	    	}			    
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListPegawaiTerimaHM
	
	
	@SuppressWarnings("unchecked")
	public static void setTotalLotBelumAgih(String id_permohonan)throws Exception {
	    
		totalLotBelumAgih = new Vector();
		
		Db db = null;
		totalLotBelumAgih.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PEGAWAI IS NULL AND ID_PERMOHONAN = '"+id_permohonan+"'";
	    	
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {
	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
	    			totalLotBelumAgih.addElement(h);
	    			bil++;
	    	}			    
	     
	    } catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListHMByPegawai
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanah(String idpermohonan,String id_ppengarah,String x) throws Exception{
		
		listMaklumatTanah = new Vector();
		
		Db db = null;
		listMaklumatTanah.clear();
		String sql = "";

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " TPB_MAIN.TPB AS TOTALPB, " ;
				sql += " TAG_MAIN.TAG AS CHECKBOXVAL, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE 'TIADA' ";
				sql += " END AS NO_LOTPT, m.seksyen ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d, Users u,";
				sql += " (SELECT HM1.ID_HAKMILIK, COUNT(HPB1.ID_PIHAKBERKEPENTINGAN) AS TPB FROM TBLPPTHAKMILIKPB HPB1, TBLPPTHAKMILIK HM1 " +
						"WHERE HPB1.ID_HAKMILIK = HM1.ID_HAKMILIK " +
						"AND HPB1.ID_JENISPB NOT IN (40, 41, 42) " +
						"AND HM1.ID_PERMOHONAN = '"+idpermohonan+"' " +
						"GROUP BY HM1.ID_HAKMILIK) TPB_MAIN,";
				sql += " ( SELECT HM2.ID_HAKMILIK, COUNT(AHM2.ID_AGIHANHM) AS TAG " +
						"FROM TBLPPTAGIHANHM AHM2, TBLPPTHAKMILIK HM2 " +
						"WHERE AHM2.ID_HAKMILIK = HM2.ID_HAKMILIK " +
						"AND AHM2.BARIS = '1' " +
						"AND AHM2.USER_ID = '"+id_ppengarah+"' " +
						"AND HM2.ID_PERMOHONAN = '"+idpermohonan+"' " +
						"GROUP BY HM2.ID_HAKMILIK) TAG_MAIN ";
				sql += " WHERE TAG_MAIN.ID_HAKMILIK(+) = M.ID_HAKMILIK  AND TPB_MAIN.ID_HAKMILIK(+) = M.ID_HAKMILIK " ;
				sql += " AND m.id_Permohonan = p.id_Permohonan ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND p.id_Permohonan = '"+idpermohonan+"'";
				
				sql += " AND m.id_hakmilik not in (select m1.id_hakmilik from tblpptagihanhm m1 " +
											      "where m1.id_hakmilik = m.id_hakmilik and m1.baris = '1' ";
				
				if(!id_ppengarah.equals("")){
					sql += " AND m1.user_id <> '"+id_ppengarah+"' ";
				}
				
				sql += " )";
				
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
				
				//QRSLOW
				myLogger.info("setListMaklumatTanah : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("checkBoxVal", rs.getString("checkBoxVal")==""?"":rs.getInt("checkBoxVal"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));				
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));					
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					listMaklumatTanah.addElement(h);
					bil++;	
					
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			if(db != null) db.close();
		}
	}//close setListMaklumatTanah
	
	@SuppressWarnings("unchecked")
	public static void simpanAgihanXX(Hashtable data,String idHakmilik,String baris) throws Exception{
		
	    Db db = null;

		Connection conn = null;
	    String sql = "";
	    
	    try{
	    	
    		db = new Db();
    		conn = db.getConnection();
			conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();
	    		
	    		long id_agihanhm = DB.getNextID("TBLPPTAGIHANHM_SEQ"); 	
	    		
	    		String id_user = (String)data.get("id_user");
	    		String socPegawaiPP = (String)data.get("socPegawaiPP");
	    		String txdTarikhAgihanLayer1 = (String)data.get("txdTarikhAgihanLayer1");
	    		
	    		String TK = "to_date('" + txdTarikhAgihanLayer1 + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_agihanhm", id_agihanhm);
	    		r.add("id_hakmilik", idHakmilik);	
	    		r.add("user_id", socPegawaiPP);
	    		r.add("baris", baris);
	    		r.add("tarikh_serah", r.unquote(TK));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptagihanhm");
	    		System.out.println("insert cb : "+sql);
	    		stmt.executeUpdate(sql);
	    		conn.commit();
	    		
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanNotis
	
	
	
	@SuppressWarnings("unchecked")
	public static void simpanAgihanNew(Hashtable data,String idHakmilik,String baris,Db db) throws Exception{
		
	    //Db db = null;

		//Connection conn = null;
	    String sql = "";
	    
	    try{
	    	
    		//db = new Db();
    		//conn = db.getConnection();
			//conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();
	    		myLogger.info("DB simpanAgihanNew before : "+db);
	    		long id_agihanhm = DB.getNextID_DB("TBLPPTAGIHANHM_SEQ",db); 	
	    		myLogger.info("DB simpanAgihanNew after : "+db);
	    		String id_user = (String)data.get("id_user");
	    		String socPegawaiPP = (String)data.get("socPegawaiPP");
	    		String txdTarikhAgihanLayer1 = (String)data.get("txdTarikhAgihanLayer1");
	    		
	    		String TK = "to_date('" + txdTarikhAgihanLayer1 + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_agihanhm", id_agihanhm);
	    		r.add("id_hakmilik", idHakmilik);	
	    		r.add("user_id", socPegawaiPP);
	    		r.add("baris", baris);
	    		r.add("tarikh_serah", r.unquote(TK));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	
	    		myLogger.info("DB tblpptagihanhm xxxx : "+db);
	    		sql = r.getSQLInsert("tblpptagihanhm",db);
	    		myLogger.info("insert cb : "+sql);
	    		stmt.executeUpdate(sql);
	    		//conn.commit();
	    		
	    }//close try 
	    catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	      //if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanNotis
	
	
	@SuppressWarnings("unchecked")
	public void setListAgihHM_INFO(String idpermohonan,String baris,String userIdNegeri) throws Exception{
		
		listAgihHM_INFO = new Vector();
		
		Db db = null;
		listAgihHM_INFO.clear();
		String sql = "";
		
		String check_neg = "";
		
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			check_neg = "AND ID_NEGERI ='"+userIdNegeri+"'";
		}

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT UPPER(USER_NAME) AS NAMA_PEGAWAI, "+
		       " (SELECT COUNT(*) FROM TBLPPTAGIHANHM HM WHERE HM.USER_ID = U.USER_ID AND HM.BARIS = '"+baris+"' AND HM.ID_HAKMILIK  IN (SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = '"+idpermohonan+"' "+check_neg+")) " +
		       " AS COUNT_HAKMILIK "+
		       " FROM USERS U WHERE U.USER_ID IN  "+
		       " (SELECT USER_ID FROM TBLPPTAGIHANHM HM WHERE HM.BARIS = '"+baris+"' AND  HM.ID_HAKMILIK IN (SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = '"+idpermohonan+"' "+check_neg+")) ";			
				sql += " ORDER BY UPPER(USER_NAME) ";
				
				myLogger.info("GET INFO AGIHAN TUGAS"+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")==null?"":rs.getString("NAMA_PEGAWAI"));
					h.put("COUNT_HAKMILIK", rs.getString("COUNT_HAKMILIK")==null?"":rs.getString("COUNT_HAKMILIK"));
				    listAgihHM_INFO.addElement(h);
					bil++;	
					
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			if(db != null) db.close();
		}
	}//close setListAgihHM
	
	
	@SuppressWarnings("unchecked")
	public void setListAgihHM_INFO_ALL(String idpermohonan,String baris,String userIdNegeri) throws Exception{
		
		listAgihHM_INFO_ALL = new Vector();
		
		Db db = null;
		listAgihHM_INFO_ALL.clear();
		String sql = "";
		
		String check_neg = "";
		
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			check_neg = userIdNegeri;
		}

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql =  " "+
						/*" SELECT UPPER(USER_NAME) AS NAMA_PEGAWAI, "+ 
						" (SELECT COUNT(*) FROM TBLPPTAGIHANHM HM WHERE HM.USER_ID = U.USER_ID AND HM.BARIS = '"+baris+"' AND HM.ID_HAKMILIK  IN (SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK H "+ 
						" WHERE (FLAG_PENARIKAN_KESELURUHAN != 'Y'  AND  FLAG_PEMBATALAN_KESELURUHAN != 'Y') OR ID_HAKMILIK NOT IN (SELECT DISTINCT SH.ID_HAKMILIK FROM TBLRUJSUBURUSANSTATUSHAKMILIK SH "+ 
						" WHERE SH.ID_SUBURUSANSTATUS IN (1486)) "+check_neg+" "+ //BORANG K
						" ))  AS COUNT_HAKMILIK, "+ 
						" ( "+ 
						" SELECT COUNT(DISTINCT H.ID_PERMOHONAN) FROM TBLPPTAGIHANHM HM,TBLPPTHAKMILIK H WHERE H.ID_HAKMILIK = HM.ID_HAKMILIK AND "+ 
						" HM.USER_ID = U.USER_ID AND HM.BARIS = '"+baris+"' AND "+ 
						" HM.ID_HAKMILIK  IN (SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK H "+ 
						" WHERE (FLAG_PENARIKAN_KESELURUHAN != 'Y'  AND  FLAG_PEMBATALAN_KESELURUHAN != 'Y') OR ID_HAKMILIK NOT IN (SELECT DISTINCT SH.ID_HAKMILIK FROM TBLRUJSUBURUSANSTATUSHAKMILIK SH "+ 
						" WHERE SH.ID_SUBURUSANSTATUS IN (1486)) "+check_neg+" "+ 
						" ) "+ 
						" )  AS COUNT_PERMOHONAN  "+ 
						" FROM USERS U WHERE U.USER_ID IN   (SELECT USER_ID FROM TBLPPTAGIHANHM HM WHERE HM.BARIS = '"+baris+"' AND  HM.ID_HAKMILIK IN (SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK "+ 
						" WHERE (FLAG_PENARIKAN_KESELURUHAN != 'Y'  AND FLAG_PEMBATALAN_KESELURUHAN != 'Y') OR ID_HAKMILIK  NOT IN (SELECT DISTINCT SH.ID_HAKMILIK FROM TBLRUJSUBURUSANSTATUSHAKMILIK SH "+ 
						" WHERE SH.ID_SUBURUSANSTATUS IN (1486)) "+check_neg+")) "+ 
						" ORDER BY UPPER(USER_NAME) ";
				*/
						" SELECT   UPPER (USER_NAME) AS NAMA_PEGAWAI, COUNT_HAKMILIK_TEMP.TM AS COUNT_HAKMILIK, COUNT_PERMOHONAN_TEMP.TP AS COUNT_PERMOHONAN " +
						"FROM USERS U,(SELECT HM.USER_ID, COUNT (DISTINCT H.ID_HAKMILIK) AS TM " +
						"FROM TBLPPTAGIHANHM HM, TBLPPTHAKMILIK H, TBLPPTHAKMILIKBORANGK HBK " +
						"WHERE " +
						"HM.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_HAKMILIK = HBK.ID_HAKMILIK(+) " +
						"AND HM.BARIS = '1' " +
						"AND NVL(FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' AND  NVL(FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' " +
						"AND H.ID_NEGERI = '"+check_neg+"' AND HBK.ID_BORANGK IS NULL " +
						"GROUP BY HM.USER_ID) COUNT_HAKMILIK_TEMP, " +
						"(SELECT HM.USER_ID, COUNT (DISTINCT P.ID_PERMOHONAN) AS TP " +
						"FROM TBLPPTAGIHANHM HM, TBLPPTHAKMILIK H, TBLPPTPERMOHONAN P, TBLPPTHAKMILIKBORANGK HBK " +
						"WHERE " +
						"HM.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN AND H.ID_HAKMILIK = HBK.ID_HAKMILIK(+) " +
						"AND HM.BARIS = '1' " +
						"AND NVL(FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' AND  NVL(FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' " +
						"AND H.ID_NEGERI = '"+check_neg+"'  AND HBK.ID_BORANGK IS NULL " +
						"GROUP BY HM.USER_ID) COUNT_PERMOHONAN_TEMP " +
						"WHERE COUNT_HAKMILIK_TEMP.USER_ID = U.USER_ID AND COUNT_PERMOHONAN_TEMP.USER_ID = U.USER_ID " +
						"ORDER BY UPPER(USER_NAME) ";
				//QRSLOW
				myLogger.info("GET INFO AGIHAN TUGAS ALL :"+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")==null?"":rs.getString("NAMA_PEGAWAI"));
					h.put("COUNT_HAKMILIK", rs.getString("COUNT_HAKMILIK")==null?"":rs.getString("COUNT_HAKMILIK"));
					h.put("COUNT_PERMOHONAN", rs.getString("COUNT_PERMOHONAN")==null?"":rs.getString("COUNT_PERMOHONAN"));
				    listAgihHM_INFO_ALL.addElement(h);
					bil++;	
					
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			if(db != null) db.close();
		}
	}//close setListAgihHM
	
	@SuppressWarnings("unchecked")
	public void setListAgihHM(String idpermohonan,String id_penolong) throws Exception{
		
		listAgihHM = new Vector();
		
		Db db = null;
		listAgihHM.clear();
		String sql = "";

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT DISTINCT A.ID_AGIHANHM, B.ID_HAKMILIK, ";
				sql += " D.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, B.NO_SUBJAKET, E.NAMA_MUKIM, B.SEKSYEN, ";
				
				sql += " (select count(*) from tblpptagihanhm m1 where m1.id_hakmilik = B.id_hakmilik and m1.baris = '2' and " +
				       " m1.user_id = '"+id_penolong+"' )as checkBoxVal, ";
				
				sql += " CASE "; 
				sql += " WHEN B.no_lot IS NOT NULL AND B.no_pt IS NULL THEN B.no_lot ";  
				sql += " WHEN B.no_lot IS NULL AND B.no_pt IS NULL THEN F.keterangan || B.no_pt "; 
				sql += " WHEN B.no_lot IS NULL AND B.no_pt IS NOT NULL THEN  F.keterangan || B.no_pt ";        
				sql += " WHEN B.no_lot IS NOT NULL AND B.no_pt IS NOT NULL THEN F.keterangan || B.no_pt || CHR(32) || CHR(40) || B.no_lot || CHR(41) "; 
				sql += " ELSE 'TIADA' "; 
				sql += " END AS NO_LOTPT, UPPER(G.USER_NAME)AS NAMA_PEGAWAI, A.TARIKH_SERAH ";
				sql += " FROM TBLPPTAGIHANHM A, TBLPPTHAKMILIK B, TBLPPTPERMOHONAN C, "; 
				sql += " TBLRUJJENISHAKMILIK D, TBLRUJMUKIM E, TBLRUJLOT F, USERS G ";
				sql += " WHERE A.ID_HAKMILIK = B.ID_HAKMILIK ";
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND B.ID_JENISHAKMILIK = D.ID_JENISHAKMILIK(+) ";
				sql += " AND B.ID_MUKIM = E.ID_MUKIM(+) ";
				sql += " AND B.ID_LOT = F.ID_LOT(+) ";
				sql += " AND A.USER_ID = G.USER_ID ";
				sql += " AND A.BARIS = '1' ";
				sql += " AND C.ID_PERMOHONAN = '"+idpermohonan+"' ";
				
				
				/*
				sql += " AND B.id_hakmilik not in (select m1.id_hakmilik from tblpptagihanhm m1 " +
			      "where m1.id_hakmilik = B.id_hakmilik and m1.baris = '2' ";

				if(!id_penolong.equals("")){
				sql += " AND m1.user_id <> '"+id_penolong+"' ";
				}
				*/
				
				
				sql += " AND B.ID_HAKMILIK NOT IN (   ";                     
				sql += " SELECT DISTINCT M1.ID_HAKMILIK  ";
				sql += " FROM TBLPPTAGIHANHM M1, TBLPPTHAKMILIK HM ";
				sql += " WHERE M1.ID_HAKMILIK = HM.ID_HAKMILIK ";
				sql += " AND HM.ID_PERMOHONAN = '"+idpermohonan+"' ";                 
				sql += " AND M1.BARIS = '2' ";
				if(!id_penolong.equals("")){
				sql += " AND m1.user_id <> '"+id_penolong+"' ";
				}
				sql += " )";
			
				
				sql += " ORDER BY NAMA_PEGAWAI ";
				
				myLogger.info("GET LIST AGIHAN HM"+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("checkBoxVal", rs.getInt("checkBoxVal")==0?0:rs.getInt("checkBoxVal"));
					h.put("id_agihanhm", rs.getString("ID_AGIHANHM")==null?"":rs.getString("ID_AGIHANHM"));
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					h.put("kod_jenis_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
					h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
					h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
					h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
					h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("nama_pegawai", rs.getString("NAMA_PEGAWAI")==null?"":rs.getString("NAMA_PEGAWAI"));
					h.put("tarikh_serah", rs.getDate("TARIKH_SERAH")==null?"":Format.format(rs.getDate("TARIKH_SERAH")));
					listAgihHM.addElement(h);
					bil++;	
					
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			if(db != null) db.close();
		}
	}//close setListAgihHM
	
	public static void deleteAgihan(String id_pegawai,String baris) throws Exception{
		
		  Db db = null;
		  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptagihanhm WHERE user_id = '"+id_pegawai+"' AND baris = '"+baris+"' ";
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
	   
	  }//close deleteBorangFInBulk
	
	public Vector getListHakmilik(String id_permohonan) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h = new Hashtable();
		Vector v = new Vector<>();
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql =  "SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = "+id_permohonan;
				ResultSet rs = stmt.executeQuery(sql);	
				
				while (rs.next()){
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));		
					v.addElement(h);
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			if(db != null) db.close();
		}
		return v;
	}
	
	public Vector getListHakmilikNew(String id_permohonan,Db db) throws Exception {
		//Db db = null;
		String sql = "";
		Hashtable h = new Hashtable();
		Vector v = new Vector<>();
		try {
				//db = new Db();
				Statement stmt = db.getStatement();
				sql =  "SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = "+id_permohonan;
				ResultSet rs = stmt.executeQuery(sql);	
				
				while (rs.next()){
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));		
					v.addElement(h);
				}
			//return list;
		}catch (Exception re) {
	    	myLogger.error("Error: ", re);
	    	throw re;
	    	}finally {
			//if(db != null) db.close();
		}
		return v;
	}
	
}//close class
