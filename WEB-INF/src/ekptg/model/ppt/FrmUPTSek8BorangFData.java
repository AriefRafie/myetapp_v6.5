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

import ekptg.engine.StateLookup;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8BorangFData {
	
	static Logger myLogger = Logger.getLogger(FrmUPTSek8BorangFData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector listBorangEInBulk = null;
	private static Vector listHakmilikBorangEInBulk = null;
	private static Vector dataBorangEInBulk = null;
	private static Vector listPBInBulk = null;
	private static Vector listBorangFInBulk = null;
	private static Vector dataBorangFInBulk = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}	
	public static Vector getListBorangEInBulk(){
		return listBorangEInBulk;
	}
	public static Vector getListHakmilikBorangEInBulk(){
		return listHakmilikBorangEInBulk;
	}
	public static Vector getDataBorangEInBulk(){
		return dataBorangEInBulk;
	}
	public static Vector getListPBInBulk(){
		return listPBInBulk;
	}
	public static Vector getListBorangFInBulk(){
		return listBorangFInBulk;
	}
	public static Vector getDataBorangFInBulk(){
		return dataBorangFInBulk;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (35,54,74) ";
		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
		
		sql += " AND (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_endosan in ('1','2')) ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptborange bx, Tblpptborangehakmilik beh "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_hakmilik = beh.id_hakmilik and beh.id_borange = bx.id_borange)) ";

		
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
		    		sql +="AND p.id_status IN (35,54,74) ";
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
	    		sql +="AND p.id_status IN (35,54,74) ";
	    		//sql +="AND p.id_status NOT IN (11,127,128,149,16,147,22,132,133,134,26) ";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}*/
	    		
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
	public static void updateStatus(Hashtable data,String idpermohonan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		
	    		//status borang f
	    		String status = "54";
	    		
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
	public static void simpanBorangF(Hashtable data,long id_borangf) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String txtKeterangan = (String)data.get("txtKeterangan");

	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangf", id_borangf);
	    		r.add("tempoh", txtTempoh);
	    		r.add("ulasan", txtKeterangan); 	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptborangf");
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanPenerima
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangE(Hashtable data,long id_borange,Db db) throws Exception
	  {
		
	 //   Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		//db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		String socBandar = (String)data.get("socBandar");
	    		String socNegeri = (String)data.get("socNegeri");
	    		
	    		String txtMasaSiasatan = (String)data.get("txtMasaSiasatan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		
	    		String txdTarikhBorangE = (String)data.get("txdTarikhBorangE");
	    		String txdTarikhBorangF = (String)data.get("txdTarikhBorangF");
	    		String txdTarikhSiasatan = (String)data.get("txdTarikhSiasatan");
	    		String txdTarikhTampal = (String)data.get("txdTarikhTampal");
	    		
	    		String TAT = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
	    		String TBE = "to_date('" + txdTarikhBorangE + "','dd/MM/yyyy')";
	    		String TBF = "to_date('" + txdTarikhBorangF + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + txdTarikhSiasatan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borange", id_borange);
	    		r.add("masa_siasatan",txtMasaSiasatan);
	    		r.add("jenis_waktu", socJenisWaktu); 	
	    		r.add("alamat1",txtAlamat1);
	    		r.add("alamat2", txtAlamat2); 	
	    		r.add("alamat3",txtAlamat3);
	    		r.add("poskod", txtPoskod);
	    		r.add("id_hakmilik", id_hakmilik); 	
	    		r.add("id_bandar",socBandar);
	    		r.add("id_negeri", socNegeri); 
	    		r.add("tarikh_akhir_tampal", r.unquote(TAT)); 
	    		r.add("tarikh_borange", r.unquote(TBE)); 	
	    		r.add("tarikh_siasatan", r.unquote(TS));
	    		r.add("tarikh_borangf", r.unquote(TBF)); 
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("Tblpptborange",db);
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	   //   if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanBorangE
	
	
	@SuppressWarnings("unchecked")
	public static void updateBorangE(Hashtable data,Db db) throws Exception{
		
	    //Db db = null;
	    String sql = "";
	    
	    try{
	      
	    	//	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    
	    		String id_borange = (String)data.get("id_borange");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		String socBandar = (String)data.get("socBandar");
	    		String socNegeri = (String)data.get("socNegeri");
	    		
	    		String txtMasaSiasatan = (String)data.get("txtMasaSiasatan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		
	    		String txdTarikhBorangE = (String)data.get("txdTarikhBorangE") == null ? "" : (String)data.get("txdTarikhBorangE");
	    				//(String)data.get("txdTarikhBorangE");
	    		String txdTarikhBorangF = (String)data.get("txdTarikhBorangF") == null ? "" : (String)data.get("txdTarikhBorangF");
	    				//(String)data.get("txdTarikhBorangF");
	    		String txdTarikhSiasatan = (String)data.get("txdTarikhSiasatan") == null ? "" : (String)data.get("txdTarikhSiasatan");
	    				//(String)data.get("txdTarikhSiasatan");
	    		String txdTarikhTampal = (String)data.get("txdTarikhTampal") == null ? "" : (String)data.get("txdTarikhTampal");
	    				//(String)data.get("txdTarikhTampal");
	    		
	    		String TAT = "to_date('" + txdTarikhTampal + "','dd/MM/yyyy')";
	    		String TBE = "to_date('" + txdTarikhBorangE + "','dd/MM/yyyy')";
	    		String TBF = "to_date('" + txdTarikhBorangF + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + txdTarikhSiasatan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borange", id_borange);
	    		r.add("masa_siasatan",txtMasaSiasatan);
	    		r.add("jenis_waktu", socJenisWaktu); 	
	    		r.add("alamat1",txtAlamat1);
	    		r.add("id_hakmilik",id_hakmilik);
	    		r.add("alamat2", txtAlamat2); 	
	    		r.add("alamat3",txtAlamat3);
	    		r.add("poskod", txtPoskod); 	
	    		r.add("id_bandar",socBandar);
	    		r.add("id_negeri", socNegeri); 
	    		r.add("tarikh_akhir_tampal", r.unquote(TAT)); 
	    		r.add("tarikh_borange", r.unquote(TBE)); 	
	    		r.add("tarikh_siasatan", r.unquote(TS));
	    		r.add("tarikh_borangf", r.unquote(TBF)); 	
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);	    		
	    		sql = r.getSQLUpdate("Tblpptborange",db);
	    		myLogger.info("sql Tblpptborange : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	     //if (db != null) db.close();
	    }//close finally
	   
	  }//close updateBorangE
	
	
public int getListBorangEInBulk_count(String id_permohonan,String carianLotHakmilik)throws Exception {
	    
		
		Db db = null;
		 String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =   " SELECT count(*) as total ";
	    		sql +=  " FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C "; 
	    		sql +=  " WHERE A.ID_BORANGE = B.ID_BORANGE "; 
	    		sql +=  " AND A.ID_HAKMILIK = C.ID_HAKMILIK "; 
	    		sql +=  " AND C.ID_PERMOHONAN = '"+id_permohonan+"' ";
	    		
	    		
	    		//NO LOT / NAMA PB
				if (carianLotHakmilik != "" && carianLotHakmilik != null) {
					if (!carianLotHakmilik.equals("")) {
						sql += " AND (UPPER(C.no_lot) LIKE '%" + carianLotHakmilik.toUpperCase() + "%' " +
							   " OR  UPPER(C.no_pt) LIKE '%" + carianLotHakmilik.toUpperCase() + "%' " +
							   " OR C.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
		                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
		                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		                       " AND M1.ID_HAKMILIK = C.ID_HAKMILIK "+ 
		                       " AND upper(PB1.NAMA_PB) LIKE ('%"+carianLotHakmilik.toUpperCase()+"%'))) ";
					}
				}//close if nolot
	    	
	    		myLogger.info("sql[listBorangEInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int total = 0;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			total = rs.getInt("total");
	    	}			    
	     return total;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListBorangEInBulk
	
	
	@SuppressWarnings("unchecked")
	public static void setListBorangEInBulk(String id_permohonan,String carianLotHakmilik)throws Exception {
	    
		listBorangEInBulk = new Vector();
		
		Db db = null;
		listBorangEInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =   " SELECT DISTINCT B.ID_BORANGE, B.TARIKH_BORANGE, B.TARIKH_SIASATAN, "; 
	    		sql +=  " (SELECT COUNT(*) FROM TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
	    		sql +=  " WHERE B1.ID_BORANGE = A1.ID_BORANGE AND B1.ID_BORANGE = B.ID_BORANGE)AS TOTALHM,  ";
	    		sql +=  " TO_DATE(TO_CHAR(A.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')AS TARIKH_MASUK ";
	    		sql +=  " FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C "; 
	    		sql +=  " WHERE A.ID_BORANGE = B.ID_BORANGE "; 
	    		sql +=  " AND A.ID_HAKMILIK = C.ID_HAKMILIK "; 
	    		sql +=  " AND C.ID_PERMOHONAN = '"+id_permohonan+"' ";
	    		
	    		
	    		//NO LOT / NAMA PB
				if (carianLotHakmilik != "" && carianLotHakmilik != null) {
					if (!carianLotHakmilik.equals("")) {
						sql += " AND (UPPER(C.no_lot) LIKE '%" + carianLotHakmilik.toUpperCase() + "%' " +
							   " OR  UPPER(C.no_pt) LIKE '%" + carianLotHakmilik.toUpperCase() + "%' " +
							   " OR C.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
		                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
		                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		                       " AND M1.ID_HAKMILIK = C.ID_HAKMILIK "+ 
		                       " AND upper(PB1.NAMA_PB) LIKE ('%"+carianLotHakmilik.toUpperCase()+"%'))) ";
					}
				}//close if nolot
	    	
	    		myLogger.info("sql[listBorangEInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("ID_BORANGE", rs.getString("ID_BORANGE")== null?"":rs.getString("ID_BORANGE"));
	    			h.put("TARIKH_BORANGE", rs.getDate("TARIKH_BORANGE")==null?"":Format.format(rs.getDate("TARIKH_BORANGE")));
	    			h.put("TARIKH_SIASATAN", rs.getDate("TARIKH_SIASATAN")==null?"":Format.format(rs.getDate("TARIKH_SIASATAN")));
	    			h.put("TOTALHM", rs.getString("TOTALHM")== null?"":rs.getString("TOTALHM"));
	    			h.put("TARIKH_MASUK", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
	    			listBorangEInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListBorangEInBulk
	
	@SuppressWarnings("unchecked")
	public static void setListHakmilikBorangEInBulk(String id_permohonan,String id_borange,String mode)throws Exception {
	    
		listHakmilikBorangEInBulk = new Vector();
		
		Db db = null;
		listHakmilikBorangEInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = " SELECT DISTINCT M.ID_HAKMILIK, M.SEKSYEN,  JH.KOD_JENIS_HAKMILIK, ";
	    		sql += " M.NO_HAKMILIK, MK.NAMA_MUKIM, ";  
	    		sql += " CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND ";  
	    		sql += " M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || ";  
	    		sql += " M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41) ";  
	    		sql += " ELSE '' END AS NO_LOTPT, M.NO_SUBJAKET, NVL(TO_NUMBER(M.NO_SUBJAKET),0) AS NO_SUBJAKET_NUM"; 
	    		
	    		sql += " ,(SELECT COUNT(M1.ID_HAKMILIK) FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
	    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE ";  
	    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";  
	    		sql += " AND B1.ID_BORANGE = '"+id_borange+"' )AS SELECTEDCB "; 
	    		
	    		
	    		sql += " ,(SELECT DISTINCT TO_CHAR(B1.TARIKH_BORANGE,'DD/MM/YYYY') AS TARIKH_BORANGE FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK ";  
	    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE ";  
	    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";  
	    		sql += "  )AS TARIKH_BORANGE "; 
	    		
	    		sql += " FROM TBLPPTPERMOHONAN P, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH ";  
	    		sql += " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN(+) ";   
	    		sql += " AND M.ID_NEGERI = N.ID_NEGERI ";  
	    		sql += " AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";
	    		sql += " AND M.ID_LOT = LT.ID_LOT(+) "; 
	    		sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";   
	    		
	    		if(mode.equals("new")){
	    		sql += " AND M.ID_HAKMILIK NOT IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTBORANGEHAKMILIK A1, TBLPPTBORANGE B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIK = M1.ID_HAKMILIK "; 
	    		sql += " AND A1.ID_BORANGE = B1.ID_BORANGE "; 
	    		sql += " AND M1.ID_PERMOHONAN = P.ID_PERMOHONAN ";
	    		if(!id_borange.equals("")){
	    		sql += " AND B1.ID_BORANGE <> '"+id_borange+"' ";
	    		}
	    		sql += " ) ";
	    		}
	    		
	    		sql += " AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y' "; 
	    		sql += " AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
	    		sql += " AND P.ID_PERMOHONAN = '"+id_permohonan+"' ";
	    		
	    		sql += " ORDER BY NO_SUBJAKET_NUM ASC";
	    		
	    		myLogger.info("sql[listHakmilikBorangEInBulk] : "+sql);
	    		
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
	    			h.put("TARIKH_BORANGE", rs.getString("TARIKH_BORANGE")== null?"":rs.getString("TARIKH_BORANGE"));
	    			listHakmilikBorangEInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListHakmilikBorangEInBulk
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangEInBulk(Hashtable data,String idHakmilik,long id_borange, Db db) throws Exception{
		
	  //  Db db = null;
	    String sql = "";
	    
	    //myLogger.info("id_borange:::::::::::"+id_borange);
	    
	    try{
	    	
	    	
	    	
	    		//db = new Db();
	    		
	    		long id_borangehakmilik = getNextID("TBLPPTBORANGEHAKMILIK_SEQ",db);
	    		
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangehakmilik", id_borangehakmilik);
	    		r.add("id_borange", id_borange);
	    		r.add("id_hakmilik", idHakmilik);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptborangehakmilik",db);
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	   //   if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanBorangEInBulk
	
	
	public synchronized static long getNextID(String seqName, Db db) throws Exception {
		//Db db = null;
		//original 
		//String sql = "select " + seqName + ".NEXTVAL FROM DUAL ";
		
		//Get State code from dbconnection.properties
		String statecode=StateLookup.getInstance().getTitle("StateCode");
		String sql = "select " + statecode + " || to_char(sysdate,'YY') || " +seqName + ".NEXTVAL  FROM DUAL ";
		try {
		//	db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getLong(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
		//	if (db != null)
		//		db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListHM(String id_borange)throws Exception {
		 
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
		    		sql +=  "FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK M, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJJENISHAKMILIK JH ";  
		    		sql +=  "WHERE A.ID_BORANGE = B.ID_BORANGE ";  
		    		sql +=  "AND A.ID_HAKMILIK = M.ID_HAKMILIK ";
		    		sql +=  "AND M.ID_LOT = LT.ID_LOT(+) ";
		    		sql +=  "AND M.ID_MUKIM = MK.ID_MUKIM(+) ";
		    		sql +=  "AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";
		    		sql +=  "AND B.ID_BORANGE = '"+id_borange+"' ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
	
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("id_hakmilik", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
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
	
	@SuppressWarnings("unchecked")
	public static void setDataBorangEInBulk(String id_borange,String id_hakmilik)throws Exception {
	    
		dataBorangEInBulk = new Vector();
		
		Db db = null;
		dataBorangEInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT DISTINCT A.ID_HAKMILIK, B.ID_BORANGE, B.TARIKH_BORANGF, B.TARIKH_BORANGE, B.TARIKH_SIASATAN, B.MASA_SIASATAN, B.JENIS_WAKTU, ";
				sql += " B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.POSKOD, B.ID_BANDAR, B.ID_NEGERI, B.TARIKH_CETAK, B.TARIKH_AKHIR_TAMPAL ";
	    		sql += " FROM TBLPPTBORANGEHAKMILIK A, TBLPPTBORANGE B, TBLPPTHAKMILIK C ";
	    		sql += " WHERE A.ID_BORANGE = B.ID_BORANGE ";
	    		sql += " AND A.ID_HAKMILIK = C.ID_HAKMILIK ";
	    		if(!id_borange.equals("")){
	    			sql += " AND B.ID_BORANGE = '"+id_borange+"'";
	    		}else if(!id_hakmilik.equals("")){
	    			sql += " AND C.ID_HAKMILIK = '"+id_hakmilik+"'";
	    		}
	    		
	    		myLogger.info("sql[dataBorangEInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("id_borange", rs.getString("ID_BORANGE")==null?"":rs.getString("ID_BORANGE"));
					h.put("tarikh_akhir_tampal", rs.getDate("TARIKH_AKHIR_TAMPAL")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
					h.put("tarikh_cetak", rs.getDate("TARIKH_CETAK")==null?"":Format.format(rs.getDate("TARIKH_CETAK")));
					h.put("tarikh_borangf", rs.getDate("TARIKH_BORANGF")==null?"":Format.format(rs.getDate("TARIKH_BORANGF")));
					h.put("tarikh_borange", rs.getDate("TARIKH_BORANGE")==null?"":Format.format(rs.getDate("TARIKH_BORANGE")));
					h.put("tarikh_siasatan", rs.getDate("TARIKH_SIASATAN")==null?"":Format.format(rs.getDate("TARIKH_SIASATAN")));
					h.put("masa_siasatan", rs.getString("MASA_SIASATAN")==null?"":rs.getString("MASA_SIASATAN"));
					h.put("jenis_waktu", rs.getString("JENIS_WAKTU")==null?"":rs.getString("JENIS_WAKTU"));			
					h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));	
					h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));	
					h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));	
					h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));	
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));	
					h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));	
					h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
	    			dataBorangEInBulk.addElement(h);    		
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataBorangEInBulk
	
	
	
	public static void deleteListCB_byHakmilik(String id_hakmilik, Db db) throws Exception{
		
		//  Db db = null;
		  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    	//	db = new Db();
	    	//	conn = db.getConnection();
			//	conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptborangehakmilik WHERE id_hakmilik = '"+id_hakmilik+"'";
	    		stmt.executeUpdate(sql);
	    		
	    	//	conn.commit();
	    		
	    }catch (SQLException se) { 
		  /*  try {
		    	conn.rollback();
		    } catch (SQLException se2) {
		    	throw new Exception("Rollback error:"+se2.getMessage());
		    }
		    throw new Exception("Ralat : Hapus Fail ");*/
		}finally {
	    //  if (db != null) db.close();
	    }//close finally
	   
	  }//close deleteListCB
	
	public static void deleteListCB(String id_borange, Db db) throws Exception{
		
		 // Db db = null;
		//  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    	//	db = new Db();
	    	//	conn = db.getConnection();
			//	conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptborangehakmilik WHERE id_borange = '"+id_borange+"'";
	    		stmt.executeUpdate(sql);
	    		
	    	//	conn.commit();
	    		
	    }catch (SQLException se) { 
		/*    try {
		    	conn.rollback();
		    } catch (SQLException se2) {
		    	throw new Exception("Rollback error:"+se2.getMessage());
		    }
		    throw new Exception("Ralat : Hapus Fail ");
		    */
		}finally {
	     // if (db != null) db.close();
	    }//close finally
	   
	  }//close deleteListCB
	
	
	
	
	@SuppressWarnings("unchecked")
	public static void setListPBInBulk(String id_hakmilik,String id_borangf)throws Exception {
	    
		listPBInBulk = new Vector();
		
		Db db = null;
		listPBInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT DISTINCT M.NO_SUBJAKET, MPB.ID_HAKMILIKPB, PB.NAMA_PB, NOPB.KETERANGAN AS NO_JENISPB, PB.NO_PB, INITCAP(MPB.ALAMAT1)AS ALAMAT1, INITCAP(MPB.ALAMAT2)AS ALAMAT2, "; 
	    		sql += " INITCAP(MPB.ALAMAT3)AS ALAMAT3, MPB.POSKOD, INITCAP(BDR.KETERANGAN)AS BANDAR, INITCAP(NG.NAMA_NEGERI)AS NAMA_NEGERI, ";
	    		sql += " CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT ";  
	    		sql += " WHEN M.NO_LOT IS NULL AND  M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT ";   
	    		sql += " WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN ||  M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41) ";  
	    		sql += " ELSE '' END AS NO_LOTPT, JH.KOD_JENIS_HAKMILIK,  M.NO_HAKMILIK, ";

	    		sql += " (SELECT COUNT(M1.ID_HAKMILIKPB) FROM TBLPPTHAKMILIKPB M1, TBLPPTBORANGFHAKMILIKPB A1, TBLPPTBORANGF B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIKPB = M1.ID_HAKMILIKPB ";
	    		sql += " AND A1.ID_BORANGF = B1.ID_BORANGF "; 
	    		sql += " AND M1.ID_HAKMILIKPB = MPB.ID_HAKMILIKPB  ";  
	    		sql += " AND B1.ID_BORANGF = '"+id_borangf+"' )AS SELECTEDCB "; 
	    		
	    		sql += " FROM TBLPPTPERMOHONAN P, TBLRUJLOT LT, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLRUJBANDAR BDR, TBLRUJNEGERI NG, ";
	    		sql += " TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH, TBLPPTHAKMILIKPB MPB, TBLPPTPIHAKBERKEPENTINGAN PB, TBLRUJJENISNOPB NOPB ";
	    		sql += " WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN(+) ";  
	    		sql += " AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) ";
	    		sql += " AND MPB.ID_NEGERI = NG.ID_NEGERI(+) ";
	    		sql += " AND MPB.ID_BANDAR = BDR.ID_BANDAR(+) ";
	    		sql += " AND M.ID_NEGERI = N.ID_NEGERI ";  
	    		sql += " AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) ";  
	    		sql += " AND M.ID_LOT = LT.ID_LOT(+) ";  
	    		sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";  
	    		sql += " AND M.ID_HAKMILIK = MPB.ID_HAKMILIK ";
	    		sql += " AND PB.ID_PIHAKBERKEPENTINGAN = MPB.ID_PIHAKBERKEPENTINGAN ";
	    		
	    		sql += " AND MPB.ID_HAKMILIKPB NOT IN (SELECT M1.ID_HAKMILIKPB ";
	    		sql += " FROM TBLPPTHAKMILIKPB M1, TBLPPTBORANGFHAKMILIKPB A1, TBLPPTBORANGF B1 ";  
	    		sql += " WHERE A1.ID_HAKMILIKPB = M1.ID_HAKMILIKPB "; 
	    		sql += " AND A1.ID_BORANGF = B1.ID_BORANGF "; 
	    		sql += " AND M1.ID_HAKMILIK = M.ID_HAKMILIK ";	    		
	    		if(!id_borangf.equals("")){
	    		sql += " AND B1.ID_BORANGF <> '"+id_borangf+"' ";
	    		}
	    		sql += " ) ";
	    		
	    		sql += " AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y' ";
	    		sql += " AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' ";
	    		sql += " AND M.ID_HAKMILIK = '"+id_hakmilik+"' ";
	    		
	    		myLogger.info("sql[listPBBorangFInBulk] : "+sql);
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET")== null?"":rs.getString("NO_SUBJAKET"));
	    			h.put("ID_HAKMILIKPB", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
	    			h.put("SELECTEDCB", rs.getInt("SELECTEDCB")== 0?0:rs.getInt("SELECTEDCB"));
	    			h.put("NAMA_PB", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
	    			h.put("NO_JENISPB", rs.getString("NO_JENISPB")== null?"":rs.getString("NO_JENISPB"));
	    			h.put("NO_PB", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
	    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
	    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
	    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
	    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
	    			h.put("BANDAR", rs.getString("BANDAR")== null?"":rs.getString("BANDAR"));
	    			h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
	    			h.put("NO_LOTPT", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
	    			h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
	    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    			listPBInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListPBInBulk
	
	
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangFInBulk(Hashtable data,String idHakmilikPB,long id_borangf) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangf", id_borangf);
	    		r.add("id_hakmilikpb", idHakmilikPB);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("tblpptborangfhakmilikpb");
	    		
	    		myLogger.info("insert borang f -- "+sql);
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanBorangEInBulk
	
	@SuppressWarnings("unchecked")
	public int getListBorangFInBulk_count(String id_hakmilik)throws Exception {
	    
		listBorangFInBulk = new Vector();
		
		Db db = null;
		listBorangFInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =   " SELECT count(*) as total ";
	    		sql +=  " FROM TBLPPTBORANGFHAKMILIKPB A, TBLPPTBORANGF B, TBLPPTHAKMILIKPB C "; 
	    		sql +=  " WHERE A.ID_BORANGF = B.ID_BORANGF "; 
	    		sql +=  " AND A.ID_HAKMILIKPB = C.ID_HAKMILIKPB "; 
	    		sql +=  " AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";
	    	
	    		myLogger.info("sql[listBorangFInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		
	    		int total = 0;
	    		while (rs.next()) {	
	    			total = rs.getInt("total");
	    	}			    
	     return total;
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListBorangFInBulk
	
	@SuppressWarnings("unchecked")
	public static void setListBorangFInBulk(String id_hakmilik)throws Exception {
	    
		listBorangFInBulk = new Vector();
		
		Db db = null;
		listBorangFInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =   " SELECT DISTINCT B.ID_BORANGF, B.TEMPOH, B.ULASAN, "; 
	    		sql +=  " (SELECT COUNT(*) FROM TBLPPTBORANGFHAKMILIKPB A1, TBLPPTBORANGF B1 ";  
	    		sql +=  " WHERE B1.ID_BORANGF = A1.ID_BORANGF AND B1.ID_BORANGF = B.ID_BORANGF)AS TOTALHM,  ";
	    		
	    		sql +=  " TO_DATE(TO_CHAR(A.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')AS TARIKH_MASUK ";
	    		sql +=  " FROM TBLPPTBORANGFHAKMILIKPB A, TBLPPTBORANGF B, TBLPPTHAKMILIKPB C "; 
	    		sql +=  " WHERE A.ID_BORANGF = B.ID_BORANGF "; 
	    		sql +=  " AND A.ID_HAKMILIKPB = C.ID_HAKMILIKPB "; 
	    		sql +=  " AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";
	    	
	    		myLogger.info("sql[listBorangFInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		int bil = 1;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("ID_BORANGF", rs.getString("ID_BORANGF")== null?"":rs.getString("ID_BORANGF"));
	    			h.put("TOTALHM", rs.getString("TOTALHM")== null?"":rs.getString("TOTALHM"));
	    			h.put("TEMPOH", rs.getString("TEMPOH")== null?"":rs.getString("TEMPOH"));
	    			h.put("ULASAN", rs.getString("ULASAN")== null?"":rs.getString("ULASAN"));
	    			h.put("TARIKH_MASUK", rs.getDate("TARIKH_MASUK")==null?"":Format.format(rs.getDate("TARIKH_MASUK")));
	    			listBorangFInBulk.addElement(h);
	    			bil++;
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setListBorangFInBulk
	
	@SuppressWarnings("unchecked")
	public static Vector getListPB(String id_borangf)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT PB.NAMA_PB, NOPB.KETERANGAN AS NO_JENISPB, PB.NO_PB, INITCAP(M.ALAMAT1)AS ALAMAT1, INITCAP(M.ALAMAT2)AS ALAMAT2, "; 
		    		sql += " INITCAP(M.ALAMAT3)AS ALAMAT3, M.POSKOD, INITCAP(BDR.KETERANGAN)AS BANDAR, INITCAP(NG.NAMA_NEGERI)AS NAMA_NEGERI ";  		    		
		    		sql +=  " FROM TBLPPTBORANGFHAKMILIKPB A, TBLPPTBORANGF B, TBLPPTHAKMILIKPB M, TBLPPTPIHAKBERKEPENTINGAN PB ";  
		    		sql +=  " , TBLRUJJENISNOPB NOPB, TBLRUJBANDAR BDR, TBLRUJNEGERI NG ";
		    		sql +=  " WHERE A.ID_BORANGF = B.ID_BORANGF ";  
		    		sql +=  " AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) ";
		    		sql +=  " AND M.ID_BANDAR = BDR.ID_BANDAR(+) ";
		    		sql +=  " AND M.ID_NEGERI = NG.ID_NEGERI(+) ";
		    		sql +=  " AND PB.ID_PIHAKBERKEPENTINGAN = M.ID_PIHAKBERKEPENTINGAN ";
		    		sql +=  " AND A.ID_HAKMILIKPB = M.ID_HAKMILIKPB ";
		    		sql +=  " AND B.ID_BORANGF = '"+id_borangf+"' ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
	
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("NAMA_PB", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
		    			h.put("NO_JENISPB", rs.getString("NO_JENISPB")== null?"":rs.getString("NO_JENISPB"));
		    			h.put("NO_PB", rs.getString("NO_PB")== null?"":rs.getString("NO_PB"));
		    			h.put("ALAMAT1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
		    			h.put("ALAMAT2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
		    			h.put("ALAMAT3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
		    			h.put("POSKOD", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));	
		    			h.put("BANDAR", rs.getString("BANDAR")== null?"":rs.getString("BANDAR"));
		    			h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")== null?"":rs.getString("NAMA_NEGERI"));
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPB
	
	@SuppressWarnings("unchecked")
	public static void setDataBorangFInBulk(String id_borangf)throws Exception {
	    
		dataBorangFInBulk = new Vector();
		
		Db db = null;
		dataBorangFInBulk.clear();
	    String sql = "";
	    
	    try{
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT DISTINCT A.ID_BORANGFHAKMILIKPB, C.ID_HAKMILIKPB, B.ID_BORANGF, B.TEMPOH, B.ULASAN ";
	    		sql += " FROM TBLPPTBORANGFHAKMILIKPB A, TBLPPTBORANGF B, TBLPPTHAKMILIKPB C ";
	    		sql += " WHERE A.ID_BORANGF = B.ID_BORANGF ";
	    		sql += " AND A.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
	    		sql += " AND B.ID_BORANGF = '"+id_borangf+"'";
	    	
	    		myLogger.info("sql[dataBorangFInBulk] : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	     
	    		Hashtable h;
	    		
	    		while (rs.next()) {	    	  
	    			h = new Hashtable();
	    			h.put("ID_BORANGFHAKMILIKPB", rs.getString("ID_BORANGFHAKMILIKPB")==null?"":rs.getString("ID_BORANGFHAKMILIKPB"));
					h.put("ID_HAKMILIKPB", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));	
					h.put("ID_BORANGF", rs.getString("ID_BORANGF")==null?"":rs.getString("ID_BORANGF"));
					h.put("TEMPOH", rs.getString("TEMPOH")==null?"":rs.getString("TEMPOH"));
					h.put("ULASAN", rs.getString("ULASAN")==null?"":rs.getString("ULASAN"));
					dataBorangFInBulk.addElement(h);    		
	    	}			    
	     
	    } finally {
	      if (db != null) db.close();
	    }
	    
	}//close setDataBorangEInBulk
	
	public static void deleteBorangFInBulk(String id_borangf) throws Exception{
		
		  Db db = null;
		  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptborangfhakmilikpb WHERE id_borangf = '"+id_borangf+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		sql = "DELETE FROM tblpptborangf WHERE id_borangf = '"+id_borangf+"'";
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
	
	@SuppressWarnings("unchecked")
	public static void updateBorangFInBulk(Hashtable data,long id_borangf) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		String txtKeterangan = (String)data.get("txtKeterangan");

	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borangf", id_borangf);
	    		r.add("tempoh", txtTempoh);
	    		r.add("ulasan", txtKeterangan); 	
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);	    		
	    		sql = r.getSQLUpdate("tblpptborangf");
	    		stmt.executeUpdate(sql);
	    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateBorangFInBulk
	
	public static void deleteListBorangFCB(String id_borangf) throws Exception{
		
		  Db db = null;
		  Connection conn = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM tblpptborangfhakmilikpb WHERE id_borangf = '"+id_borangf+"'";
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
	   
	  }//close deleteListBorangFCB
	
}//close class
