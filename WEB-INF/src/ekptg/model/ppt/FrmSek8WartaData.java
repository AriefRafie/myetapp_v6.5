package ekptg.model.ppt;

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

public class FrmSek8WartaData {
	static Logger myLogger = Logger.getLogger(FrmSek8WartaData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector dataWarta = null;
	//private static Vector dataBorangD = null; //penambahan yati 6/1/2017
	private static Vector listWarta = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getDataWarta(){
		return dataWarta;
	}
	/*public Vector getDataBorangD(){ //penambahan yati
		return dataBorangD;
	}
	*/
	public Vector getListWarta(){
		return listWarta;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (26,31)";
		
		sql +=" and (p.id_permohonan IN (SELECT M.ID_PERMOHONAN FROM TBLPPTMMK M, TBLPPTMMKKEPUTUSAN K "+
				" WHERE M.ID_MMK = K.ID_MMK  AND K.STATUS_KEPUTUSAN = 1 AND M.ID_PERMOHONAN = p.id_permohonan) ";    
		sql += " OR p.id_permohonan in (select distinct wx.id_permohonan from Tblpptwarta wx ";
		sql += " where wx.id_permohonan = p.id_permohonan)) ";
		
		
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
		    		sql +="AND p.id_status IN (26,31)";
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
	    		sql +="AND p.id_status IN (26,31)";
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
	
	
	@SuppressWarnings("unchecked") //penambahan yati--6/1/2017-tambah tblpptendosanborangd
	public static void simpanWarta(Hashtable data) throws Exception
	  {
		System.out.println("**** data : "+data);
		
	    Db db = null;
	    String sql = "";
	    long id_warta = 0;
	    long id_endosanborangd = 0;

	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String socWarta = (String)data.get("socWarta");
	    		String txtNoWarta = (String)data.get("txtNoWarta");	    		
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");	 
	    		String txdTarikhHantarBorangD = (String)data.get("txdTarikhHantarBorangD");	 
	    		String txtNoKenyataan = (String)data.get("txtNoKenyataan");	
	    		String txdTarikhTerimaWarta = (String)data.get("txdTarikhTerimaWarta");	 
	    		
	    		String THBD = "to_date('" + txdTarikhHantarBorangD + "','dd/MM/yyyy')";
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		String TTW = "to_date('" + txdTarikhTerimaWarta + "','dd/MM/yyyy')";
	    	    			
	    		SQLRenderer r = new SQLRenderer();
	    		id_warta = DB.getNextID(db,"TBLPPTWARTA_SEQ");
	    		r.add("id_warta",id_warta);
	    		r.add("id_permohonan", id_permohonan);	
	    		r.add("proses_warta", socWarta);	
	    		r.add("no_warta", txtNoWarta);	
	    		r.add("no_kenyataan", txtNoKenyataan);	
	    		r.add("tarikh_hantar_borangd",r.unquote(THBD));
	    		r.add("tarikh_warta",r.unquote(TW));
	    		r.add("tarikh_terima_warta",r.unquote(TTW));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptwarta");
 		
	    		System.out.println("DATA LIST WARTA  "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    		r.clear();
	    		//penambahan yati
	    		//table tblpptendorsanborangd
	    		String check_id_endosanborangd = (String) data.get("id_endosanborangd");
	    		//long id_endosanborangd = DB.getNextID("TBLPPTENDOSANBORANGD_SEQ");
	    		
	    		if(check_id_endosanborangd.equals(""))
				{
	    			id_endosanborangd = DB.getNextID(db, "TBLPPTENDOSANBORANGD_SEQ");
				}
				else
				{
					id_endosanborangd = Long.parseLong(check_id_endosanborangd);
				}
	    		String txdTarikhBorangD = (String)data.get("txdTarikhBorangD");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
	    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
	    		String txtPerserahan = (String)data.get("txtPerserahan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtCatatanBorangD = (String)data.get("txtCatatanBorangD");
	    		
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + txdTarikhBorangD + "','dd/MM/yyyy')";
	    		
	    		if(check_id_endosanborangd.equals(""))
				{
					r.add("id_endosanborangd", id_endosanborangd);
					r.add("id_warta",id_warta);
				}
				else
				{
					r.update("id_endosanborangd", id_endosanborangd);
					r.update("id_warta",id_warta);
				}
	    		r.add("tarikh_borangd",r.unquote(TD));
	    		r.add("jenis_masa",socJenisWaktu);  
	    		r.add("no_perserahan",txtPerserahan); 
	    		r.add("masa_catatan",txtMasaCatatan); 
	    		r.add("catatan",txtCatatanBorangD); 
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_catatan",r.unquote(TC));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);  
	    		r.add("id_permohonan", id_permohonan);
	    		//TABLE PLACING
				if(check_id_endosanborangd.equals(""))
				{
					sql = r.getSQLInsert("Tblpptendosanborangd");
				}
				System.out.println("DATA LIST PPT ENDOSAN BORANG D  "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    		
	    		
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanWarta
	
	
	@SuppressWarnings("unchecked")
	public void setDataWarta(String idWarta) throws Exception{
		
		dataWarta = new Vector();
		
		Db db = null;
		dataWarta.clear();
		String sql = "";

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
			/*	sql = "SELECT DISTINCT id_warta,id_permohonan,proses_warta,no_warta,tarikh_warta, tarikh_hantar_borangd, no_kenyataan, tarikh_terima_warta ";
				sql += " FROM tblpptwarta ";
				sql += " WHERE id_warta = '"+idWarta+"'";
			*/
				//Penambahan table tblpptendosanborangd
				sql = "SELECT DISTINCT wrt.id_warta,wrt.id_permohonan,wrt.proses_warta,wrt.no_warta,wrt.tarikh_warta, wrt.tarikh_hantar_borangd, wrt.no_kenyataan, wrt.tarikh_terima_warta, " +
						"ed.id_endosanborangd, ed.id_permohonan, ed.catatan,ed.tarikh_borangd,ed.tarikh_terima, ed.tarikh_catatan, ed.masa_catatan, ed.jenis_masa, ed.no_perserahan, ed.id_warta " +
						"FROM tblpptwarta wrt " +
						"left outer join tblpptendosanborangd ed " +
						"on wrt.id_warta=ed.id_warta " +
						"where wrt.id_warta = '"+idWarta+"'";	
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("id_warta", rs.getString("id_warta")==null?"":rs.getString("id_warta"));
					h.put("no_kenyataan", rs.getString("no_kenyataan")==null?"":rs.getString("no_kenyataan"));
					h.put("no_warta", rs.getString("no_warta")==null?"":rs.getString("no_warta"));
					h.put("proses_warta", rs.getString("proses_warta")==null?"":rs.getString("proses_warta"));
					h.put("tarikh_warta", rs.getDate("tarikh_warta")==null?"":Format.format(rs.getDate("tarikh_warta")));	
					h.put("tarikh_terima_warta", rs.getDate("tarikh_terima_warta")==null?"":Format.format(rs.getDate("tarikh_terima_warta")));
					h.put("tarikh_hantar_borangd", rs.getDate("tarikh_hantar_borangd")==null?"":Format.format(rs.getDate("tarikh_hantar_borangd")));
					h.put("id_endosanborangd", rs.getString("id_endosanborangd")==null?"":rs.getString("id_endosanborangd"));
					h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					h.put("id_warta", rs.getString("id_warta")==null?"":rs.getString("id_warta"));
					h.put("no_perserahan", rs.getString("no_perserahan")==null?"":rs.getString("no_perserahan"));
					h.put("tarikh_borangd", rs.getDate("tarikh_borangd")==null?"":Format.format(rs.getDate("tarikh_borangd")));
					h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));	
					h.put("tarikh_catatan", rs.getDate("tarikh_catatan")==null?"":Format.format(rs.getDate("tarikh_catatan")));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					h.put("masa_catatan", rs.getString("masa_catatan")==null?"":rs.getString("masa_catatan"));
					h.put("jenis_masa", rs.getString("jenis_masa")==null?"":rs.getString("jenis_masa"));
					
					if(rs.getString("jenis_masa") != null && rs.getString("jenis_masa") != ""){
						
						if(rs.getString("jenis_masa").equals("1")){
							h.put("masa","PAGI");
						}else if(rs.getString("jenis_masa").equals("2")){
							h.put("masa","TENGAHARI");
						}else if(rs.getString("jenis_masa").equals("3")){
							h.put("masa","PETANG");
						}else{
							h.put("masa","");
						}
		
	    			}else{
	    				h.put("masa","");
	    			}
					dataWarta.addElement(h);
					
					System.out.println("DATA LIST WARTA  "+dataWarta);
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		}//close dataWarta
	
	
	@SuppressWarnings("unchecked")
	public void setListWarta(String idPermohonan) throws Exception{
		
		listWarta = new Vector();
		
		Db db = null;
		listWarta.clear();
		String sql = "";

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT id_warta,id_permohonan,proses_warta,no_warta,tarikh_warta,tarikh_hantar_borangd,tarikh_terima_warta,no_kenyataan ";
				sql += " FROM tblpptwarta ";
				sql += " WHERE id_permohonan = '"+idPermohonan+"'";
					
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_warta", rs.getString("id_warta")==null?"":rs.getString("id_warta"));
					h.put("no_warta", rs.getString("no_warta")==null?"":rs.getString("no_warta"));
					h.put("tarikh_warta", rs.getDate("tarikh_warta")==null?"":Format.format(rs.getDate("tarikh_warta")));
					h.put("tarikh_terima_warta", rs.getDate("tarikh_terima_warta")==null?"":Format.format(rs.getDate("tarikh_terima_warta")));
					h.put("tarikh_hantar_borangd", rs.getDate("tarikh_hantar_borangd")==null?"":Format.format(rs.getDate("tarikh_hantar_borangd")));
					h.put("no_kenyataan", rs.getString("no_kenyataan")==null?"":rs.getString("no_kenyataan"));
					h.put("proses_warta", rs.getString("proses_warta")==null?"":rs.getString("proses_warta"));
					if(rs.getString("proses_warta").equals("1")){
	    				h.put("proses_warta","CAWANGAN JKPTG");
	    			}else if(rs.getString("proses_warta").equals("2")){
	    				h.put("proses_warta","PTG");
	    			}else{
	    				h.put("proses_warta","-");
	    			}
					listWarta.addElement(h);	
					bil++;
			}
			//return list;
		}finally {
			if(db != null) db.close();
		}
	}//close listWarta
	
	
	 public static void hapusWarta(String id_warta) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		//penambahan function delete utk 2 tables-yati 
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		r.add("id_warta", id_warta);
	    		sql = r.getSQLDelete("tblpptwarta");
	    		stmt.executeUpdate(sql);
	    		
	    		r.clear();
	    		
	    		r.add("id_warta", id_warta);
	    		sql = r.getSQLDelete("tblpptendosanborangd");
	    		stmt.executeUpdate(sql);
	    		
	    		/*sql = "DELETE FROM tblpptwarta where id_warta = '"+id_warta+"'";
	    		stmt.executeUpdate(sql);
	    		*/
   	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusWarta
	
	@SuppressWarnings("unchecked")
	public static void updateWarta(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    long id_endosanborangd=0;
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_warta = (String)data.get("id_warta");
		    		String id_permohonan = (String)data.get("id_permohonan");
		    		
		    		String socWarta = (String)data.get("socWarta");
		    		String txtNoWarta = (String)data.get("txtNoWarta");	    		
		    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");	 
		    		String txdTarikhHantarBorangD = (String)data.get("txdTarikhHantarBorangD");	 
		    		String txtNoKenyataan = (String)data.get("txtNoKenyataan");	 
		    		String txdTarikhTerimaWarta = (String)data.get("txdTarikhTerimaWarta");	
		    		
		    		String THBD = "to_date('" + txdTarikhHantarBorangD + "','dd/MM/yyyy')";
		    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
		    		String TTW = "to_date('" + txdTarikhTerimaWarta + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_warta", id_warta);	
		    		r.add("proses_warta", socWarta);	
		    		r.add("no_warta", txtNoWarta);	
		    		r.add("no_kenyataan", txtNoKenyataan);
		    		r.add("tarikh_hantar_borangd",r.unquote(THBD));
		    		r.add("tarikh_warta",r.unquote(TW));
		    		r.add("tarikh_terima_warta",r.unquote(TTW));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptwarta");
		    		stmt.executeUpdate(sql);
		    		
		    		r.clear();
		    		//penambahan yati
		    		//table tblpptendorsanborangd
		    		String check_id_endosanborangd = (String) data.get("id_endosanborangd");
		    		
		    		if(check_id_endosanborangd.equals(""))
					{
		    			id_endosanborangd = DB.getNextID(db, "TBLPPTENDOSANBORANGD_SEQ");
					}
					else
					{
						id_endosanborangd = Long.parseLong(check_id_endosanborangd);
					}
		    		
		    		String txdTarikhBorangD = (String)data.get("txdTarikhBorangD");
		    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
		    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
		    		String txtPerserahan = (String)data.get("txtPerserahan");
		    		String socJenisWaktu = (String)data.get("socJenisWaktu");
		    		String txtCatatanBorangD = (String)data.get("txtCatatanBorangD");
		    		
		    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
		    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
		    		String TD = "to_date('" + txdTarikhBorangD + "','dd/MM/yyyy')";
		    		
		    		if(check_id_endosanborangd.equals(""))
					{
						r.add("id_endosanborangd", id_endosanborangd);
					}
					else
					{
						r.update("id_endosanborangd", id_endosanborangd);
					}
		    		r.update("id_warta", id_warta);
		    		r.add("tarikh_borangd",r.unquote(TD));
		    		r.add("jenis_masa",socJenisWaktu);  
		    		r.add("no_perserahan",txtPerserahan); 
		    		r.add("masa_catatan",txtMasaCatatan); 
		    		r.add("catatan",txtCatatanBorangD); 
		    		r.add("tarikh_terima",r.unquote(TT));
		    		r.add("tarikh_catatan",r.unquote(TC));
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);  
		    		r.add("id_warta",id_warta);
		    		r.add("id_permohonan", id_permohonan);
		    		//TABLE PLACING
		    		if(check_id_endosanborangd.equals(""))
					{
						sql = r.getSQLInsert("Tblpptendosanborangd");
					}
					else
					{
						sql = r.getSQLUpdate("Tblpptendosanborangd");
					}
					System.out.println("UPDATE TBL ENDOSAN : "+sql);
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateWarta
	 
	@SuppressWarnings("unchecked")
	public static void hantar(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status pengwartaan
	    		String status = "31";
	    		
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
	
}//close class
