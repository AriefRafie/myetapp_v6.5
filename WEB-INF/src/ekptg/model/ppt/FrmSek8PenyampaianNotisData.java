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

public class FrmSek8PenyampaianNotisData {
	static Logger myLogger = Logger.getLogger(FrmSek8PenyampaianNotisData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector listPenyampaianNotis = null;
	private static Vector dataPenyampaianNotis = null;
	private static Vector namaPB = null;
	private static  Vector listPB = null;
	private static  Vector listPBPilihan = null;
	private static  Vector listPenyampaianNotisBorangH = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}	
	public Vector getListPenyampaianNotis(){
		return listPenyampaianNotis;
	}
	public Vector getDataPenyampaianNotis(){
		return dataPenyampaianNotis;
	}
	public Vector getNamaPB(){
		return namaPB;
	}
	public static  Vector getListPB(){
		return listPB;
	}
	public static  Vector getListPBPilihan(){
		return listPBPilihan;
	}
	public Vector getListPenyampaianNotisBorangH(){
		return listPenyampaianNotisBorangH;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
		sql += " and flag_jenis_bukti = '1')) ";
		
		/*
		if(flagJenisBukti.equals("1")){
			sql +="AND p.id_status IN (52,58,74) ";
		}else if(flagJenisBukti.equals("2")){
			sql +="AND p.id_status IN (68,72) ";
		}else if(flagJenisBukti.equals("3")){
			sql +="AND p.id_status IN (76) ";
		}
		*/
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
	public static Vector getListPermohonan(String flagJenisBukti,String userIdNegeri)throws Exception {
		 
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
		    		
		    		if(flagJenisBukti.equals("1")){
		    			sql +="AND p.id_status IN (52,58,74) ";
		    		}else if(flagJenisBukti.equals("2")){
		    			sql +="AND p.id_status IN (68,72) ";
		    		}else if(flagJenisBukti.equals("3")){
		    			sql +="AND p.id_status IN (76) ";
		    		}
		    		
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
	
	
	public static String sqlListPermohonanK(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		
		/*
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptnotisawam nx, Tblpptnotisawamhakmilik nah "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = nah.id_hakmilik and nah.id_notisawam = nx.id_notisawam) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
		sql += " and flag_jenis_bukti = '1')) ";
		*/
		
		sql += " and (p.id_permohonan in (select distinct blx.id_permohonan from Tblpptborangl blx "; 
		sql += " where blx.id_permohonan = p.id_permohonan) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptbuktipenyampaian bpx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = bpx.id_hakmilik ";
		sql += " and flag_jenis_bukti = '3')";
		sql += " OR p.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
		sql += " where bkx.id_permohonan = p.id_permohonan)) ";
		
		/*
		if(flagJenisBukti.equals("1")){
			sql +="AND p.id_status IN (52,58,74) ";
		}else if(flagJenisBukti.equals("2")){
			sql +="AND p.id_status IN (68,72) ";
		}else if(flagJenisBukti.equals("3")){
			sql +="AND p.id_status IN (76) ";
		}
		*/
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
	public static Vector getListPermohonanK(String flagJenisBukti,String userIdNegeri)throws Exception {
		 
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
		    		
		    		if(flagJenisBukti.equals("1")){
		    			sql +="AND p.id_status IN (52,58,74) ";
		    		}else if(flagJenisBukti.equals("2")){
		    			sql +="AND p.id_status IN (68,72) ";
		    		}else if(flagJenisBukti.equals("3")){
		    			sql +="AND p.id_status IN (76) ";
		    		}
		    		
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonanK(userIdNegeri);
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
	public static void setListCarian(String carianNofail, String carianTarikh, String status, String flagJenisBukti, String userIdNegeri)throws Exception {
		
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
	    		
	    		if(flagJenisBukti.equals("1")){
	    			sql +="AND p.id_status IN (52,58,74) ";
	    		}else if(flagJenisBukti.equals("2")){
	    			sql +="AND p.id_status IN (68,72) ";
	    		}else if(flagJenisBukti.equals("3")){
	    			sql +="AND p.id_status IN (76) ";
	    		}
	     
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
	public static void setListCarianK(String carianNofail, String carianTarikh, String status, String flagJenisBukti, String userIdNegeri)throws Exception {
		
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
	    		
	    		if(flagJenisBukti.equals("1")){
	    			sql +="AND p.id_status IN (52,58,74) ";
	    		}else if(flagJenisBukti.equals("2")){
	    			sql +="AND p.id_status IN (68,72) ";
	    		}else if(flagJenisBukti.equals("3")){
	    			sql +="AND p.id_status IN (76) ";
	    		}
	     
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		*/
	    		
	    		sql = sqlListPermohonanK(userIdNegeri);
	    		
	    		//default flag
				boolean setLimit = true; 
				
	    		//NO FAIL
				if (carianNofail != "" && carianNofail != null) {
					if (!nofail.equals("")) {
						setLimit = false;
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
	public static void simpanPenyampaianNotis(Hashtable data,long id_buktipenyampaian,String flagJenisBukti) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
	    		
	    		String txtNamaHantar = (String)data.get("txtNamaHantar");
	    		String socKodDokumen = (String)data.get("socKodDokumen");
	    		String socJenisSerah = (String)data.get("socJenisSerah");
	    		String socStatusSerah = (String)data.get("socStatusSerah");
	    		String txdTarikhSerah = (String)data.get("txdTarikhSerah");
	    		
	    		//data penerima
	    		String txtNamaTerima = (String)data.get("txtNamaTerima");
	    		String txtNoKP = (String)data.get("txtNoKP");
	    		String socJenisNoKP = (String)data.get("socJenisNoKP");
	    		String txtHubungan = (String)data.get("txtHubungan");
	    		
	    		//data tampal
	    		String txtMasaTampal = (String)data.get("txtMasaTampal");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatTampal = (String)data.get("txtTempatTampal");

	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
  		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_buktipenyampaian",id_buktipenyampaian);
	    		r.add("id_hakmilik", id_hakmilik);	  
	    		r.add("hubungan", txtHubungan);	
	    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);	
	    		r.add("nama_penghantar", txtNamaHantar);
	    		r.add("masa_tampal", txtMasaTampal);	
	    		r.add("jenis_waktu", socJenisWaktu);	
	    		r.add("tempat_tampal", txtTempatTampal);	
	    		r.add("flag_jenis_bukti", flagJenisBukti);
	    		r.add("id_jenisdokumen", socKodDokumen);
	    		r.add("jenis_serahan", socJenisSerah);
	    		r.add("flag_serah", socStatusSerah);
	    		r.add("nama_penerima", txtNamaTerima);
	    		r.add("no_kp_penerima", txtNoKP);
	    		r.add("catatan", txtCatatan);
	    		r.add("id_jenisnopb", socJenisNoKP);	    		
	    		r.add("tarikh_hantar",r.unquote(TS));	    			    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptbuktipenyampaian");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPenyampaianNotis
	
	@SuppressWarnings("unchecked")
	public static void simpanPilihanPB(Hashtable data,String id_pihakberkepentingan,long id_buktipenyampaian) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_pihakberkepentingan",id_pihakberkepentingan);
	    		r.add("id_buktipenyampaian",id_buktipenyampaian);	    			    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptbuktipenyampaianpb");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanPilihanPB
	
	
	public static void deletePilihanPB(long id_buktipenyampaian) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	  
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
    		
	    		sql = "DELETE FROM Tblpptbuktipenyampaianpb WHERE id_buktipenyampaian = '"+id_buktipenyampaian+"'";
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close deletePilihanPB
	
	
	@SuppressWarnings("unchecked")
	public void setListPenyampaianNotis(String idHakmilik,String flagJenisBukti) throws Exception{
		
		listPenyampaianNotis = new Vector();
		
		Db db = null;
		listPenyampaianNotis.clear();
		String sql = "";

		
		try {
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
				sql += " a.tarikh_hantar,a.id_jenisnopb,a.flag_serah,a.flag_jenis_bukti, b.nama_pb, b.id_jenisnopb as id_kodnopb, b.no_pb ";
				sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b ";
				sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan ";
				sql += " AND a.id_hakmilik = '"+idHakmilik+"'";
				sql += " AND NVL(a.flag_jenis_bukti,0) = '"+flagJenisBukti+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;

				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian"));
					h.put("nama_penerima", rs.getString("nama_penerima")==null?"<b>Tidak Dapat Diserahkan / Bukan Melalui Serahan Tangan</b>":rs.getString("nama_penerima"));
					h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar"));			
					h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
					h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb"));		
					h.put("id_kodnopb", rs.getString("id_kodnopb")==null?"":rs.getString("id_kodnopb"));		
					h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb"));		
					listPenyampaianNotis.addElement(h);
					bil++;	
				}
		
			}finally {
				if(db != null) db.close();
			}
			
	}//close setListPenyampaianNotis
	
	
	@SuppressWarnings("unchecked")
	public int setListPenyampaianNotis_count(String idHakmilik,String flagJenisBukti) throws Exception{
		
			Db db = null;
		String sql = "";

		
		try {
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT count(*) as total ";
				sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b ";
				sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan ";
				sql += " AND a.id_hakmilik = '"+idHakmilik+"'";
				sql += " AND NVL(a.flag_jenis_bukti,0) = '"+flagJenisBukti+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int total = 0;

				while (rs.next()){
					total = rs.getInt("total");
				}
		return total;
			}finally {
				if(db != null) db.close();
			}
			
	}//close setListPenyampaianNotis
	
	@SuppressWarnings("unchecked")
	public void setDataPenyampaianNotis(String id_buktipenyampaian) throws Exception{
		
		dataPenyampaianNotis = new Vector();
		
		Db db = null;
		dataPenyampaianNotis.clear();
		String sql = "";

		
		try {
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT d.id_hakmilikpb, c.id_hakmilik, a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
				sql += " a.tarikh_hantar,a.id_jenisnopb,a.flag_serah, b.nama_pb, b.id_pihakberkepentingan, b.id_jenisnopb as id_kodnopb, b.no_pb, ";
				sql += " a.hubungan, a.tempat_tampal, a.masa_tampal, a.jenis_waktu ";
				sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b, tblppthakmilik c, tblppthakmilikpb d ";
				sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
				sql += " AND d.id_pihakberkepentingan = b.id_pihakberkepentingan ";
				sql += " AND d.id_hakmilik = c.id_hakmilik "; 
				sql += " AND a.id_buktipenyampaian = '"+id_buktipenyampaian+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
	
				while (rs.next()){
					h = new Hashtable();
					h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")==null?"":rs.getString("id_hakmilikpb"));
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")==null?"":rs.getString("id_pihakberkepentingan"));
					h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian"));
					h.put("nama_penerima", rs.getString("nama_penerima")==null?"":rs.getString("nama_penerima"));
					h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar"));	
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					h.put("jenis_serahan", rs.getString("jenis_serahan")==null?"":rs.getString("jenis_serahan"));	
					h.put("no_kp_penerima", rs.getString("no_kp_penerima")==null?"":rs.getString("no_kp_penerima"));
					h.put("id_jenisnopb", rs.getString("id_jenisnopb")==null?"":rs.getString("id_jenisnopb"));	
					h.put("status_serahan", rs.getString("flag_serah")==null?"":rs.getString("flag_serah"));
					h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
					h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb"));
					h.put("id_kodnopb", rs.getString("id_kodnopb")==null?"":rs.getString("id_kodnopb"));	
					h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb"));
					h.put("hubungan", rs.getString("hubungan")==null?"":rs.getString("hubungan"));
					h.put("tempat_tampal", rs.getString("tempat_tampal")==null?"":rs.getString("tempat_tampal"));	
					h.put("masa_tampal", rs.getString("masa_tampal")==null?"":rs.getString("masa_tampal"));
					h.put("jenis_waktu", rs.getString("jenis_waktu")==null?"":rs.getString("jenis_waktu"));
					dataPenyampaianNotis.addElement(h);
				}
		
			}finally {
				if(db != null) db.close();
			}
			
	}//close setDataPenyampaianNotis
	
	
	public static void hapusPenyampaianNotis(String id_buktipenyampaian) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM Tblpptbuktipenyampaian WHERE id_buktipenyampaian = '"+id_buktipenyampaian+"'";
	    		stmt.executeUpdate(sql);
	 
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusPenyampaianNotis
	
	@SuppressWarnings("unchecked")
	public static void updatePenyampaianNotis(Hashtable data,long id_buktipenyampaian) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
	    		
	    		String txtNamaHantar = (String)data.get("txtNamaHantar");
	    		String socKodDokumen = (String)data.get("socKodDokumen");
	    		String socJenisSerah = (String)data.get("socJenisSerah");
	    		String socStatusSerah = (String)data.get("socStatusSerah");
	    		String txdTarikhSerah = (String)data.get("txdTarikhSerah");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String txtNamaTerima = (String)data.get("txtNamaTerima");
	    		String txtNoKP = (String)data.get("txtNoKP");	 
	    		String socJenisNoKP = (String)data.get("socJenisNoKP");
	    		String txtHubungan = (String)data.get("txtHubungan");
	    		
	    		//data tampal
	    		String txtMasaTampal = (String)data.get("txtMasaTampal");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtTempatTampal = (String)data.get("txtTempatTampal");
	    		
	    		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
  		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_buktipenyampaian", id_buktipenyampaian);	
	    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);
	    		r.add("nama_penghantar", txtNamaHantar);
	    		r.add("hubungan", txtHubungan);
	    		r.add("id_jenisdokumen", socKodDokumen);
	    		r.add("masa_tampal", txtMasaTampal);
	    		r.add("jenis_waktu", socJenisWaktu);
	    		r.add("tempat_tampal", txtTempatTampal);
	    		r.add("jenis_serahan", socJenisSerah);
	    		r.add("flag_serah", socStatusSerah);
	    		r.add("nama_penerima", txtNamaTerima);
	    		r.add("no_kp_penerima", txtNoKP);
	    		r.add("catatan", txtCatatan);
	    		r.add("id_jenisnopb", socJenisNoKP);	    		
	    		r.add("tarikh_hantar",r.unquote(TS));	    			    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptbuktipenyampaian");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updatePenyampaianNotis
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data,String idpermohonan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		
	    		//status penyampaian notis
	    		String status = "58";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", idpermohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateStatus
	
	@SuppressWarnings("unchecked")
	public void setNamaPB(String idpb) throws Exception{
		
		namaPB = new Vector();
		
		Db db = null;
		namaPB.clear();
		String sql = "";

		
		try {
			
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT DISTINCT a.nama_pb, a.no_pb, a.id_jenisnopb " +
					  " FROM Tblpptpihakberkepentingan a WHERE a.id_pihakberkepentingan = '"+idpb+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;

				while (rs.next()){
					h = new Hashtable();
					h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb"));
					h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb"));
					h.put("id_jenisnopb", rs.getString("id_jenisnopb")==null?"":rs.getString("id_jenisnopb"));
					namaPB.addElement(h);
				}
		
			}finally {
				if(db != null) db.close();
			}
			
	}//close setNamaPB
	
	 @SuppressWarnings("unchecked")
	  public static void setListPB(String id_permohonan,String nama)throws Exception {
			
		  	listPB = new Vector();
			
		    Db db = null;
		    listPB.clear();
		    String sql = "";
		    
		    String namaPB = nama.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilikpb, a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb, a.id_bangsa, a.id_warganegara, ";
		    		sql += " c.id_negeri, c.id_bandar, a.no_pb, c.jenis_lain2_pb, c.syer_atas, c.syer_bawah, c.alamat1, c.alamat2, c.alamat3, c.poskod, ";
		    		sql += " c.no_tel_rumah, c.no_handphone, c.no_fax ";
		    		sql += " FROM Tblpptpermohonan p, Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
		    		sql += " AND b.id_permohonan = p.id_permohonan ";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND c.id_jenispb not in (40,41,42)";
		    		sql += " AND p.id_permohonan = '"+id_permohonan+"'";
		      
		    		//NAMA PB
					if (nama != "" && nama != null) {
						if (!namaPB.equals("")) {
							sql += " AND UPPER(nama_pb) LIKE '%" + namaPB.toUpperCase() + "%'";
						}
					}//close if namaPB
		    		
					sql += " ORDER BY nama_pb asc";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")== null?"":rs.getString("id_hakmilikpb"));
		    			h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")== null?"":rs.getString("id_pihakberkepentingan"));
		    			h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    			h.put("id_bangsa", rs.getString("id_bangsa")== null?"":rs.getString("id_bangsa"));		    			
		    			h.put("id_warganegara", rs.getString("id_warganegara")== null?"":rs.getString("id_warganegara"));
		    			h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    			h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));
		    			h.put("jenis_lain2_pb", rs.getString("jenis_lain2_pb")== null?"":rs.getString("jenis_lain2_pb"));		    			
		    			h.put("syer_atas", rs.getString("syer_atas")== null?"":rs.getString("syer_atas"));
		    			h.put("syer_bawah", rs.getString("syer_bawah")== null?"":rs.getString("syer_bawah"));
		    			h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    			h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    			h.put("no_tel_rumah", rs.getString("no_tel_rumah")== null?"":rs.getString("no_tel_rumah"));
		    			h.put("no_hp", rs.getString("no_handphone")== null?"":rs.getString("no_handphone"));
		    			h.put("no_fax", rs.getString("no_fax")== null?"":rs.getString("no_fax"));
		    			
		    			listPB.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listPB
	
	 @SuppressWarnings("unchecked")
	  public static void setListPBPilihan(String id_buktipenyampaian)throws Exception {
			
		 listPBPilihan = new Vector();
			
		    Db db = null;
		    listPBPilihan.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilikpb, a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb,a.no_pb, ";
		    		sql += " (select count(*) from Tblpptbuktipenyampaianpb where id_pihakberkepentingan(+) = a.id_pihakberkepentingan and id_buktipenyampaian = '"+id_buktipenyampaian+"' )as flag ";
		    		sql += " FROM Tblpptpermohonan p, Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbuktipenyampaian d ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+) ";
		    		sql += " AND b.id_permohonan = p.id_permohonan  ";
		    		sql += " AND d.id_permohonan(+) = p.id_permohonan ";
					sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+) "; 
					sql += " AND c.id_jenispb not in (40,41,42)";
					sql += " AND d.id_buktipenyampaian = '"+id_buktipenyampaian+"'";
					sql += " ORDER BY nama_pb asc ";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("flag", rs.getString("flag")== null?"":rs.getString("flag"));
		    			h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")== null?"":rs.getString("id_hakmilikpb"));
		    			h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")== null?"":rs.getString("id_pihakberkepentingan"));
		    			h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));
		    			listPBPilihan.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setListPBPilihan
	 
	@SuppressWarnings("unchecked")
	public void setListPenyampaianNotisBorangH(String idHakmilik,String flagJenisBukti) throws Exception{
			
		listPenyampaianNotisBorangH = new Vector();
			
			Db db = null;
			listPenyampaianNotisBorangH.clear();
			String sql = "";

			
			try {
				
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT DISTINCT a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
					sql += " a.tarikh_hantar,a.id_jenisnopb,a.flag_serah,a.flag_jenis_bukti, b.nama_pb, b.id_jenisnopb as id_kodnopb, b.no_pb ";
					sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b ";
					sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
					sql += " AND a.id_hakmilik = '"+idHakmilik+"'";
					sql += " AND NVL(a.flag_jenis_bukti,0) = '"+flagJenisBukti+"'";
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;

					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian"));
						h.put("nama_penerima", rs.getString("nama_penerima")==null?"<b>Tidak Dapat Diserahkan / Bukan Melalui Serahan Tangan</b>":rs.getString("nama_penerima"));
						h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar"));			
						h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
						h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb"));		
						h.put("id_kodnopb", rs.getString("id_kodnopb")==null?"":rs.getString("id_kodnopb"));		
						h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb"));		
						listPenyampaianNotisBorangH.addElement(h);
						bil++;	
					}
			
				}finally {
					if(db != null) db.close();
				}
				
	}//close setListPenyampaianNotisBorangH
	 
}//close class
