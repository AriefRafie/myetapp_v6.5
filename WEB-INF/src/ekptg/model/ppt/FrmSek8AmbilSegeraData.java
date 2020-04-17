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


public class FrmSek8AmbilSegeraData {
	static Logger myLogger = Logger.getLogger(FrmSek8AmbilSegeraData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector dataBorangI = null;
	private static Vector listBorangJ = null;
	private static Vector dataBorangJ = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getDataBorangI(){
		return dataBorangI;
	}
	public Vector getListBorangJ(){
		return listBorangJ;
	}
	public Vector getDataBorangJ(){
		return dataBorangJ;
	}
	
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (58,59) ";
		
		
		sql += " and ((p.flag_segera = '1' AND p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, tblppthakmilikborangk hbk, tblpptborangk kk, Tblpptendosanborangk bpx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = hbk.id_hakmilik and hbk.id_borangk = kk.id_borangk and  kk.id_borangk = bpx.id_borangk ";
		sql += " )) "; 
		sql += " OR p.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
		sql += " where bix.id_permohonan = p.id_permohonan) ";
		sql += " OR (p.flag_segera = '3' AND p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, tblppthakmilikborangk hbk, tblpptborangk kk, Tblpptendosanborangk bpx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = hbk.id_hakmilik and hbk.id_borangk = kk.id_borangk and  kk.id_borangk = bpx.id_borangk ";
		sql += " and hx.flag_segera_sebahagian = 'Y'))" +
				"   )  ";
		
		//sql +="AND p.flag_segera = '1' ";
		
		
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
		    		sql +="AND p.id_status IN (58,59) ";
		    		sql +="AND p.flag_segera = '1' ";
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
	    		/*
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, p.no_rujukan_surat, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
	    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '52' ";
	    		sql +="AND p.id_status IN (58,59) ";
	    		sql +="AND p.flag_segera = '1' ";
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
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%'" +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%'" +
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
	public static void updateFlagBorangI(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_mmk = (String)data.get("id_mmk");	    		
	    		String socSemakanBorangI = (String)data.get("socSemakanBorangI");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_mmk", id_mmk);
	    		r.add("flag_borangi",socSemakanBorangI); 
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptmmk");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateFlagBorangI
	
	@SuppressWarnings("unchecked")
	public void setDataBorangI(String idPermohonan) throws Exception{
		
		dataBorangI = new Vector();
		
		Db db = null;
		dataBorangI.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT a.id_borangi,a.tarikh_surat,a.no_rujukan_surat,a.tarikh_terima,a.tarikh_borangi,a.jenis_ambilsegera, ";
				sql += " b.id_borangk,b.tarikh_borangk ";
				sql += " FROM TBLPPTBORANGI a, TBLPPTBORANGK b, TBLPPTPERMOHONAN c ";
				sql += " WHERE a.id_permohonan = c.id_permohonan ";
				sql += " AND b.id_permohonan(+) = c.id_permohonan ";
				sql += " AND c.id_permohonan = '"+idPermohonan+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("id_borangi", rs.getString("id_borangi")==null?"":rs.getString("id_borangi"));
					h.put("id_borangk", rs.getString("id_borangk")==null?"":rs.getString("id_borangk"));
					h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
					h.put("tarikh_terima", rs.getDate("tarikh_terima")==null?"":Format.format(rs.getDate("tarikh_terima")));
					h.put("tarikh_borangi", rs.getDate("tarikh_borangi")==null?"":Format.format(rs.getDate("tarikh_borangi")));
					h.put("tarikh_borangk", rs.getDate("tarikh_borangk")==null?"":Format.format(rs.getDate("tarikh_borangk")));
					h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
					h.put("jenis_ambilsegera", rs.getString("jenis_ambilsegera")==null?"":rs.getString("jenis_ambilsegera"));
					h.put("id_borangk", rs.getString("id_borangk")==null?"":rs.getString("id_borangk"));
					dataBorangI.addElement(h);					
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setDataBorangI
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatSegera(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	 
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhBorangI = (String)data.get("txdTarikhBorangI");
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");	
	    		String socSemakanBorangI = (String)data.get("socSemakanBorangI");	 
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TBI = "to_date('" + txdTarikhBorangI + "','dd/MM/yyyy')";
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("no_rujukan_surat", txtNoRujSurat);
	    		r.add("jenis_ambilsegera", socSemakanBorangI);
	    		r.add("tarikh_surat",r.unquote(TS));
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_borangi",r.unquote(TBI));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptborangi");
	    		stmt.executeUpdate(sql);
	    		
	    		SQLRenderer p = new SQLRenderer();
	    		p.add("id_permohonan", id_permohonan);		
	    		p.add("tarikh_borangk",p.unquote(TBK));
	    		p.add("tarikh_masuk",p.unquote("sysdate"));
	    		p.add("id_masuk",id_user);    		
	    		sql = p.getSQLInsert("tblpptborangk");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanMaklumatSegera
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatSegera(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();

	    		String id_user = (String)data.get("id_user");    	
	    		//String id_permohonan = (String)data.get("id_permohonan");
	    		String id_borangi = (String)data.get("id_borangi");
	    		String id_borangk = (String)data.get("id_borangk");
	    		
	    		String txtNoRujSurat = (String)data.get("txtNoRujSurat");	 
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhBorangI = (String)data.get("txdTarikhBorangI");
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");	
	    		String socSemakanBorangI = (String)data.get("socSemakanBorangI");	 
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TBI = "to_date('" + txdTarikhBorangI + "','dd/MM/yyyy')";
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borangi", id_borangi);
	    		r.add("no_rujukan_surat", txtNoRujSurat);
	    		r.add("jenis_ambilsegera", socSemakanBorangI);
	    		r.add("tarikh_surat",r.unquote(TS));
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_borangi",r.unquote(TBI));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLUpdate("tblpptborangi");
	    		stmt.executeUpdate(sql);
	    		
	    		SQLRenderer p = new SQLRenderer();
	    		p.update("id_borangk", id_borangk);		
	    		p.add("tarikh_borangk",p.unquote(TBK));
	    		p.add("tarikh_kemaskini",p.unquote("sysdate"));
	    		p.add("id_kemaskini",id_user);    		
	    		sql = p.getSQLUpdate("tblpptborangk");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateMaklumatSegera
	
	//simpan flag segera dalam pilihan HM
	public static void simpanFlagPilihanHM(String idUser,String idHakmilik) throws Exception
	  {
		
	    Db db = null;
	    
	    String sql = "";
	   
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_hakmilik", idHakmilik);
   		    r.add("flag_ambil_segera","1");		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanFlagPilihanPTD
	
	//simpan flag segera dalam pilihan HM
	public static void simpanSemuaPilihanPTD(String idUser,String idPermohonan) throws Exception
	  {
		
	    Db db = null;
	    
	    String sql = "";
	   
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_permohonan", idPermohonan);
   		    r.add("flag_ambil_segera","1");		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanSemuaPilihanPTD
	
	//simpan flag segera dalam pilihan HM
	public static void clearFlagPilihanPTD(String idUser,String idPermohonan) throws Exception
	  {
		
	    Db db = null;
	    
	    String sql = "";
	   
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_permohonan", idPermohonan);
   		    r.add("flag_ambil_segera","");		
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    r.add("id_kemaskini",idUser);    		
	 	    sql = r.getSQLUpdate("tblppthakmilik");
	 	    stmt.executeUpdate(sql);
		    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close clearFlagPilihanPTD
	
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangJ(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");    	
	    		String id_bangunan = (String)data.get("id_bangunan");
	    		String id_borangi = (String)data.get("id_borangi");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		String txdTarikhNotis = (String)data.get("txdTarikhNotis");	 
	    		String txdTarikhTamatNotis = (String)data.get("txdTarikhTamatNotis");
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String txtTindakan = (String)data.get("txtTindakan");
	    		
	    		String TN = "to_date('" + txdTarikhNotis + "','dd/MM/yyyy')";
	    		String TTN = "to_date('" + txdTarikhTamatNotis + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_bangunan", id_bangunan);
	    		r.add("id_borangi", id_borangi);
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("tarikh_notis",r.unquote(TN));
	    		r.add("tarikh_tamat_notis",r.unquote(TTN));
	    		r.add("tempoh", txtTempoh);
	    		r.add("tindakan", txtTindakan);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblpptborangj");
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanBorangJ
	
	
	 @SuppressWarnings("unchecked")
	  public static void updateBangunan(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_bangunan = (String)data.get("id_bangunan");
		    		
		    		String txtNoBangunan = (String)data.get("txtNoBangunan");
		    		String txtJenisBangunan = (String)data.get("txtJenisBangunan");
		    		String txtNilai = (String)data.get("txtNilai");
		    		String txtKos = (String)data.get("txtKos");
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_bangunan",id_bangunan);
		    		r.add("no_bangunan",txtNoBangunan);
		    		r.add("jenis_bangunan",txtJenisBangunan);
		    		r.add("nilai_bangunan",txtNilai);
		    		r.add("kos_pindah",txtKos);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblpptbangunan");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateBangunanPB
	
	 @SuppressWarnings("unchecked")
	 public static void setListBorangJ(String id_permohonan)throws Exception {
			
		 listBorangJ = new Vector();
			
		 Db db = null;
		 listBorangJ.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT a.id_borangj,b.id_borangi,c.id_bangunan,e.id_hakmilik,lt.keterangan as unit2, ";
		    	sql += " c.no_bangunan,c.jenis_bangunan,c.nilai_bangunan,c.kos_pindah, ";
		    	sql += " a.tarikh_notis,a.tarikh_tamat_notis,a.tempoh,a.tindakan, ";
		    	sql += " e.no_lot,e.no_hakmilik,e.no_pt, jh.kod_jenis_hakmilik, ";
		    	sql += " CASE ";
				sql += " WHEN e.no_lot IS NOT NULL AND e.no_pt IS NULL THEN e.no_lot "; 
				sql += " WHEN e.no_lot IS NULL AND e.no_pt IS NULL THEN lt.keterangan || e.no_pt ";
				sql += " WHEN e.no_lot IS NULL AND e.no_pt IS NOT NULL THEN  lt.keterangan || e.no_pt "; 
				sql += " WHEN e.no_lot IS NOT NULL AND e.no_pt IS NOT NULL THEN lt.keterangan || e.no_pt || CHR(32) || CHR(40) || e.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT ";  
		    	sql += " FROM Tblpptborangj a, Tblpptborangi b, Tblpptbangunan c, Tblpptpermohonan d, Tblppthakmilik e, Tblrujlot lt, Tblrujjenishakmilik jh ";
				sql += " WHERE a.id_borangi(+) = b.id_borangi ";
				sql += " AND a.id_bangunan = c.id_bangunan(+) ";
				sql += " AND b.id_permohonan = d.id_permohonan "; 
				sql += " AND e.id_lot = lt.id_lot(+) ";
				sql += " AND e.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND a.id_hakmilik = e.id_hakmilik ";
				sql += " AND d.id_permohonan = '"+id_permohonan+"'";
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
		    	myLogger.info("SQL LIST BANGUNAN---"+sql);
		      
		    	Hashtable h;
		    	int bil = 1;
		    	
		    	while (rs.next()) {
		    		h = new Hashtable();	 
		    		h.put("bil", bil);
		    		h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")== null?"":rs.getString("kod_jenis_hakmilik"));
		    		h.put("no_lotpt", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
		    		h.put("id_borangj", rs.getString("id_borangj")== null?"":rs.getString("id_borangj"));
		    		h.put("id_borangi", rs.getString("id_borangi")== null?"":rs.getString("id_borangi"));
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
		    		h.put("no_hakmilik", rs.getString("no_hakmilik")== null?"":rs.getString("no_hakmilik"));
		    		h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
		    		h.put("no_lot", rs.getString("no_lot")== null?"":rs.getString("no_lot"));
		    		h.put("no_pt", rs.getString("no_pt")== null?"":rs.getString("no_pt"));
		    		h.put("no_bangunan", rs.getString("no_bangunan")== null?"":rs.getString("no_bangunan"));
		    		listBorangJ.addElement(h);
		    		bil++;
		    		
		    	}//close while
		      
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListBorangJ
	 
	 
	 @SuppressWarnings("unchecked")
	 public static void setDataBorangJ(String id_borangj)throws Exception {
			
		 dataBorangJ = new Vector();
			
		 Db db = null;
		 dataBorangJ.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT a.id_borangj,b.id_borangi,c.id_bangunan,e.id_hakmilik, ";
		    	sql += " c.no_bangunan,c.jenis_bangunan,c.nilai_bangunan,c.kos_pindah, ";
		    	sql += " a.tarikh_notis,a.tarikh_tamat_notis,a.tempoh,a.tindakan, ";
		    	sql += " e.no_lot,e.no_hakmilik ";
		    	sql += " FROM Tblpptborangj a, Tblpptborangi b, Tblpptbangunan c, Tblpptpermohonan d, Tblppthakmilik e ";
				sql += " WHERE a.id_borangi(+) = b.id_borangi ";
				sql += " AND a.id_bangunan = c.id_bangunan(+) ";
				sql += " AND b.id_permohonan = d.id_permohonan "; 
				sql += " AND a.id_hakmilik = e.id_hakmilik ";
				sql += " AND a.id_borangj = '"+id_borangj+"'";
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;
		    	
		    	while (rs.next()) {
		    		h = new Hashtable();	 
		    		h.put("id_borangj", rs.getString("id_borangj")== null?"":rs.getString("id_borangj"));
		    		h.put("id_borangi", rs.getString("id_borangi")== null?"":rs.getString("id_borangi"));
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
		    		h.put("no_hakmilik", rs.getString("no_hakmilik")== null?"":rs.getString("no_hakmilik"));
		    		h.put("no_lot", rs.getString("no_lot")== null?"":rs.getString("no_lot"));
		    		h.put("no_bangunan", rs.getString("no_bangunan")== null?"":rs.getString("no_bangunan"));
		    		h.put("jenis_bangunan", rs.getString("jenis_bangunan")== null?"":rs.getString("jenis_bangunan"));
		    		h.put("nilai_bangunan", rs.getString("nilai_bangunan")== null?"":rs.getDouble("nilai_bangunan"));
		    		h.put("kos_pindah", rs.getString("kos_pindah")== null?"":rs.getDouble("kos_pindah"));
		    		h.put("tempoh", rs.getString("tempoh")== null?"":rs.getString("tempoh"));
		    		h.put("tindakan", rs.getString("tindakan")== null?"":rs.getString("tindakan"));
		    		h.put("tarikh_notis", rs.getDate("tarikh_notis")==null?"":Format.format(rs.getDate("tarikh_notis")));
		    		h.put("tarikh_tamat_notis", rs.getDate("tarikh_tamat_notis")==null?"":Format.format(rs.getDate("tarikh_tamat_notis")));
		    		dataBorangJ.addElement(h);
		    		
		    	}//close while
		      
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataBorangJ
	 
	 public static void hapusBorangJ(String id_borangj) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptborangj where id_borangj = '"+id_borangj+"'";
	    		stmt.executeUpdate(sql);
   	 
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusHM 
	 
	 
	@SuppressWarnings("unchecked")
	public static void updateBorangJ(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");    	
		    		String id_borangj = (String)data.get("id_borangj");

		    		String txdTarikhNotis = (String)data.get("txdTarikhNotis");	 
		    		String txdTarikhTamatNotis = (String)data.get("txdTarikhTamatNotis");
		    		String txtTempoh = (String)data.get("txtTempoh");
		    		String txtTindakan = (String)data.get("txtTindakan");
		    		
		    		String TN = "to_date('" + txdTarikhNotis + "','dd/MM/yyyy')";
		    		String TTN = "to_date('" + txdTarikhTamatNotis + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_borangj", id_borangj);
		    		r.add("tarikh_notis",r.unquote(TN));
		    		r.add("tarikh_tamat_notis",r.unquote(TTN));
		    		r.add("tempoh", txtTempoh);
		    		r.add("tindakan", txtTindakan);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptborangj");
		    		stmt.executeUpdate(sql);
		    		
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateBorangJ
	 
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status pengambilan segera
	    		String status = "59";
	    		
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
	   
	  }//close updateStatus
	 
}//close class
