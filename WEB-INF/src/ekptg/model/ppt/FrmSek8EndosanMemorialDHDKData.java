package ekptg.model.ppt;
//baru
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmSek8EndosanMemorialDHDKData {
	static Logger myLogger = Logger.getLogger(FrmSek8EndosanMemorialDHDKData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector listHakmilikPTG = null;
	private static Vector listHakmilikPTGOnly = null;
	private static Vector listHakmilikPTDOnly = null;
	private static Vector listHakmilikPTD = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getListHakmilikPTG(){
		return listHakmilikPTG;
	}
	public Vector getListHakmilikPTD(){
		return listHakmilikPTD;
	}
	public Vector getListHakmilikPTGOnly(){
		return listHakmilikPTGOnly;
	}
	public Vector getListHakmilikPTDOnly(){
		return listHakmilikPTDOnly;
	}
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (31,35,74)";
		sql +="AND ";
		sql += " (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2'))";
		sql += " OR p.id_permohonan in (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan)) ";
		
		
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
		    		sql +="AND p.id_status IN (31,35,74)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}*/
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
	    		sql +="AND p.id_status IN (31,35,74)";
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
	public static void hantar(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status memorial dhdk
	    		String status = "35";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hantar
	
	@SuppressWarnings("unchecked")
	public static void updateNamaPejabatPTG(Hashtable data,String id_pejabat_ptg,Db db) throws Exception{
			
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
	    		String txtCatatanPTG = (String)data.get("txtCatatanPTG");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_pejabat_ptg",id_pejabat_ptg);  
	    		r.add("catatan_endosan_ptg",txtCatatanPTG); 
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
		  finally {
				if (db == null) {
					if (db1 != null)
						db1.close();
				}
			}
	  }//close updateNamaPejabatPTG
	
	@SuppressWarnings("unchecked")
	public static void updateNamaPejabatPTD(Hashtable data,String id_pejabat_ptd,Db db) throws Exception{
			
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
	    		String txtCatatanPTD = (String)data.get("txtCatatanPTD");	
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_pejabat_ptd",id_pejabat_ptd);  
	    		r.add("catatan_endosan_ptd",txtCatatanPTD);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	
		  finally {
				if (db == null) {
					if (db1 != null)
						db1.close();
				}
			}
	   
	  }//close updateNamaPejabatPTD
	
	@SuppressWarnings("unchecked")
	public void setHakmilikPTG(String idPermohonan) throws Exception{
		
		listHakmilikPTG = new Vector();
		
		Db db = null;
		listHakmilikPTG.clear();
		String sql = "";
	
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT p.id_permohonan, ";
				sql += " m.id_hakmilik,mk.nama_mukim,m.no_hakmilik,jh.keterangan as jenis_hakmilik, m.flag_endosan, u.user_name as nama_pegawai, "; 
				sql += " CASE ";
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NULL THEN m.NO_LOT "; 
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NULL THEN D.KETERANGAN || m.NO_PT ";
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NOT NULL THEN  D.KETERANGAN || m.NO_PT "; 
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NOT NULL THEN D.KETERANGAN || m.NO_PT || CHR(32) || CHR(40) || m.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_lot, m.no_pt, m.no_subjaket ";  
				sql += " FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u, Tblrujlot D ";  
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";  
				sql += " AND m.id_mukim = mk.id_mukim(+) ";
				sql += " AND m.id_lot = D.id_lot(+) ";
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND NVL(m.flag_endosan,0) <> '2' ";
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				//sql += " ORDER BY mk.nama_mukim asc ";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"-":rs.getString("jenis_hakmilik"));
					h.put("flag_endosan", rs.getString("flag_endosan")==null?"-":rs.getString("flag_endosan"));
					listHakmilikPTG.addElement(h);
					bil++;	
				}
				
		}finally {
			if(db != null) db.close();
		}
		}//close setHakMilikPTG
	
	@SuppressWarnings("unchecked")
	public void setHakmilikPTD(String idPermohonan) throws Exception{
		
		listHakmilikPTD = new Vector();
		
		Db db = null;
		listHakmilikPTD.clear();
		String sql = "";
	
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT p.id_permohonan, ";
				sql += " m.id_hakmilik,mk.nama_mukim,m.no_hakmilik,jh.keterangan as jenis_hakmilik, m.flag_endosan, u.user_name as nama_pegawai, "; 
				sql += " CASE ";
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NULL THEN m.NO_LOT "; 
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NULL THEN D.KETERANGAN || m.NO_PT ";
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NOT NULL THEN  D.KETERANGAN || m.NO_PT "; 
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NOT NULL THEN D.KETERANGAN || m.NO_PT || CHR(32) || CHR(40) || m.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_lot, m.no_pt, m.no_subjaket ";  
				sql += " FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u, tblrujlot D ";  
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";  
				sql += " AND m.id_mukim = mk.id_mukim(+) ";
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_lot = D.id_lot(+) ";
				sql += " AND NVL(m.flag_endosan,0) <> '1' ";
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				//sql += " ORDER BY mk.nama_mukim asc ";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"-":rs.getString("jenis_hakmilik"));
					h.put("flag_endosan", rs.getString("flag_endosan")==null?"-":rs.getString("flag_endosan"));
					listHakmilikPTD.addElement(h);
					bil++;	
				}
				
		}finally {
			if(db != null) db.close();
		}
		}//close setHakMilikPTD
	
	@SuppressWarnings("unchecked")
	public void setHakmilikPTGOnly(String idPermohonan) throws Exception{
		
		listHakmilikPTGOnly = new Vector();
		
		Db db = null;
		listHakmilikPTGOnly.clear();
		String sql = "";
	
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT p.id_permohonan, ";
				sql += " m.id_hakmilik,mk.nama_mukim,m.no_hakmilik,jh.keterangan as jenis_hakmilik, u.user_name as nama_pegawai, "; 
				sql += " m.no_lot, m.no_pt, m.no_subjaket, ";
				sql += " CASE ";
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NULL THEN m.NO_LOT "; 
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NULL THEN D.KETERANGAN || m.NO_PT ";
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NOT NULL THEN  D.KETERANGAN || m.NO_PT "; 
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NOT NULL THEN D.KETERANGAN || m.NO_PT || CHR(32) || CHR(40) || m.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT ";
				sql += " FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u, tblrujlot D ";  
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";  
				sql += " AND m.id_mukim = mk.id_mukim(+) ";
				sql += " AND m.id_lot = D.id_lot(+) ";
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND NVL(m.flag_endosan,0) = '1' ";
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				//sql += " ORDER BY mk.nama_mukim asc ";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"-":rs.getString("jenis_hakmilik"));
					listHakmilikPTGOnly.addElement(h);
					bil++;	
				}
				
		}finally {
			if(db != null) db.close();
		}
		}//close setHakmilikPTGOnly
	
	@SuppressWarnings("unchecked")
	public void setHakmilikPTDOnly(String idPermohonan) throws Exception{
		
		listHakmilikPTDOnly = new Vector();
		
		Db db = null;
		listHakmilikPTDOnly.clear();
		String sql = "";
	
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT p.id_permohonan, ";
				sql += " m.id_hakmilik,mk.nama_mukim,m.no_hakmilik,jh.keterangan as jenis_hakmilik, u.user_name as nama_pegawai, ";
				sql += " CASE ";
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NULL THEN m.NO_LOT "; 
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NULL THEN D.KETERANGAN || m.NO_PT ";
				sql += " WHEN m.NO_LOT IS NULL AND m.NO_PT IS NOT NULL THEN  D.KETERANGAN || m.NO_PT "; 
				sql += " WHEN m.NO_LOT IS NOT NULL AND m.NO_PT IS NOT NULL THEN D.KETERANGAN || m.NO_PT || CHR(32) || CHR(40) || m.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_lot, m.no_pt, m.no_subjaket ";
				sql += " FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u, tblrujlot D ";  
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) ";  
				sql += " AND m.id_mukim = mk.id_mukim(+) ";
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_lot = D.id_lot(+) ";
				sql += " AND NVL(m.flag_endosan,0) = '2' ";
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				//sql += " ORDER BY mk.nama_mukim asc ";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				
				myLogger.info("sql list ptd-- "+sql);
				Hashtable h;
				int bil = 1;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"-":rs.getString("jenis_hakmilik"));
					listHakmilikPTDOnly.addElement(h);
					bil++;	
				}
				
		}finally {
			if(db != null) db.close();
		}
		}//close setHakmilikPTDOnly
	
	//simpan pilihan PTG
	@SuppressWarnings("unchecked")
	public static void simpanFlagPilihanPTG(Hashtable data,String idUser,String idHakmilik,Db db) throws Exception
	  {
		
	    Db db1 = null;
	    
	    String sql = "";
	    String id_suburusanstatushakmilik = "";
	    String id_suburusanstatus = "";
	    
	    try
	    {
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    	Statement stmt = db1.getStatement();
	    	
			String id_permohonan = (String)data.get("id_permohonan");
	    	String id_fail = (String)data.get("id_fail");
	    		
	 	    //flag endosan 1 = PTG
	 	    String flag_endosan = "1";
	 	    
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_hakmilik", idHakmilik);
   		    r.add("flag_endosan",flag_endosan);		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	 	    r.clear();
	 	    
			sql = "SELECT ID_SUBURUSANSTATUSHAKMILIK,ID_SUBURUSANSTATUS,AKTIF ";
			sql += " FROM TBLRUJSUBURUSANSTATUSHAKMILIK ";
			sql += " WHERE ID_HAKMILIK = '"+idHakmilik+"'";
			sql += " AND AKTIF = '1' ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id_suburusanstatushakmilik = rs.getString("ID_SUBURUSANSTATUSHAKMILIK")==null?"":rs.getString("ID_SUBURUSANSTATUSHAKMILIK");
				id_suburusanstatus = rs.getString("ID_SUBURUSANSTATUS")==null?"":rs.getString("ID_SUBURUSANSTATUS");
			}
			
			if(!id_suburusanstatus.equals("1472")){
				
				//update n add tblrujsuburusanstatus
				r.update("ID_SUBURUSANSTATUSHAKMILIK", id_suburusanstatushakmilik);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",idUser);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);	 
			  
				r.clear();
			
     			r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
     			r.add("ID_PERMOHONAN",id_permohonan);
     			r.add("ID_HAKMILIK",idHakmilik);
     			r.add("ID_FAIL",id_fail);
     			r.add("ID_SUBURUSANSTATUS", "1472");
     			r.add("AKTIF",1);
     			r.add("ID_MASUK",idUser);
				r.add("ID_KEMASKINI",idUser);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);
			
			}
			
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close simpanFlagPilihanPTG
	
	
	//simpan pilihan PTD
	public static void simpanFlagPilihanPTD(Hashtable data,String idUser,String idHakmilik,Db db) throws Exception
	  {
		
	    Db db1 = null;
	    
	    String sql = "";
	    String id_suburusanstatushakmilik = "";
	    String id_suburusanstatus = "";
	    
	    try
	    {
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    	Statement stmt = db1.getStatement();
	    	
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	String id_fail = (String)data.get("id_fail");
	    	
	 	    //flag endosan 2 = PTD
	 	    String flag_endosan = "2";
	 	    
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_hakmilik", idHakmilik);
   		    r.add("flag_endosan",flag_endosan);		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	 	   r.clear();
	 	    
			sql = "SELECT ID_SUBURUSANSTATUSHAKMILIK,ID_SUBURUSANSTATUS,AKTIF ";
			sql += " FROM TBLRUJSUBURUSANSTATUSHAKMILIK ";
			sql += " WHERE ID_HAKMILIK = '"+idHakmilik+"'";
			sql += " AND AKTIF = '1' ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id_suburusanstatushakmilik = rs.getString("ID_SUBURUSANSTATUSHAKMILIK")==null?"":rs.getString("ID_SUBURUSANSTATUSHAKMILIK");
				id_suburusanstatus = rs.getString("ID_SUBURUSANSTATUS")==null?"":rs.getString("ID_SUBURUSANSTATUS");
			}
			
			if(!id_suburusanstatus.equals("1472")){
				
				//update n add tblrujsuburusanstatus
				r.update("ID_SUBURUSANSTATUSHAKMILIK", id_suburusanstatushakmilik);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",idUser);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);	 
			  
				r.clear();
			
    			r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
    			r.add("ID_PERMOHONAN",id_permohonan);
    			r.add("ID_HAKMILIK",idHakmilik);
    			r.add("ID_FAIL",id_fail);
    			r.add("ID_SUBURUSANSTATUS", "1472");
    			r.add("AKTIF",1);
    			r.add("ID_MASUK",idUser);
				r.add("ID_KEMASKINI",idUser);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);
			
			}
			
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close simpanFlagPilihanPTD
	
	
	//simpan pilihan PTG
	
	public static void deleteFlagPilihanPTG(String idUser,String idpermohonan,Db db) throws Exception
	  {
		
	    Db db1 = null;
	    
	    String sql = "";
	   
	    try
	    {
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    	Statement stmt = db1.getStatement();
	    	
	 	    //flag endosan 1 = PTG
	 	    String clear_flag_endosan = "";
	 	    
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_permohonan", idpermohonan);
	 	    r.update("flag_endosan", "1");
   		    r.add("flag_endosan",clear_flag_endosan);		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close deleteFlagPilihanPTG
	
	//simpan pilihan PTG
	public static void deleteFlagPilihanPTD(String idUser,String idpermohonan, Db db) throws Exception
	  {		
	    Db db1 = null;	    
	    String sql = "";
	   
	    try
	    {
	    	if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
	    	Statement stmt = db1.getStatement();
	    	
	 	    //flag endosan 1 = PTG
	 	    String clear_flag_endosan = "";
	 	    
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_permohonan", idpermohonan);
	 	    r.update("flag_endosan", "2");
   		    r.add("flag_endosan",clear_flag_endosan);		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	    }//close try 
	    finally {
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}
	   
	  }//close deleteFlagPilihanPTD
	
}//close class
