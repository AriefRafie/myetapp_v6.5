package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class BantahanDaftar extends EkptgCache implements Serializable  {
	 static Logger myLogger = Logger.getLogger(BantahanDaftar.class);
	 private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	 
	 private Vector list = null;
	 public Vector listPageDepan = null;
	 private Vector header = null;
	 public Vector listCarian = null;
	 public Vector listCarianPBOnline = null;	
	 public Vector listCarianAPOnline = null;		 
	 public Vector getHakmilik = null;
	 public Vector getAlamatPembantah = null;
	 public Vector getMaklumatBantahan = null;
	 public Vector getMaklumatPampasan = null;
	 public Vector getMaklumatDeposit = null;
	 public Vector getAlamatMahkamah = null;
	 public Vector getDataBorangO = null;
	 public Vector getMaklumatSusulan = null;
	 public Vector getMaklumatTarikBalik = null;
	 public Vector getMaklumatBatalMahkamah = null;
	 public Vector getTarikhPenting = null;
	 public Vector view_details_dokumen = null;
	 public Vector listDokumen_bantahan = null;
	 public Vector getSenaraiPB = null;
	 public Vector listCarianPB = null;	 
	 public Vector getBayaranHTP = null;
	 public Vector getNoSiasatan = null;
	 public Vector getNoWarta = null;
	 public Vector getMaklumatChecking = null;
	 public Vector getCheckingBayaranPampasan = null;
	 public Vector getTarikhBorangG = null;
	 private Vector listMaklumatTanah = null;
	 
	 public Vector getListMaklumatTanah(){
			return listMaklumatTanah;
	}		 
	 
	 public Vector getListCarian(){
			return listCarian;
	 }
	 
	 public Vector getListCarianPBOnline(String userIdNegeri){
			return listCarianPBOnline;
	 }
	 
	 public Vector getListCarianAPOnline(String userIdNegeri){
			return listCarianAPOnline;
	 }
	 
	 @SuppressWarnings("unchecked")
	public Vector getListPemohon(String userIdNegeri)throws Exception {		 
		    Db db = null;
		    String sql = "";		    
		    try{
		      list = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();		     
		      /*
		      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
		      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini, p.no_rujukan_ptg ";
		      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
		      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
		      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
		      sql +=" AND ( ";
		      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
		      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
		      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
		      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
		      sql +=" ) > 0  ";
		      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
		      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
		      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
		      */
		      
		      
		      sql = " SELECT DISTINCT P.ID_STATUS, P.ID_PERMOHONAN, P.NO_PERMOHONAN, F.ID_FAIL,NVL(F.NO_FAIL,'Belum Diluluskan') AS NO_FAIL, P.TARIKH_PERMOHONAN, "+
		    		  " SU.NAMA_SUBURUSAN,K.NAMA_KEMENTERIAN, S.KETERANGAN, P.NO_RUJUKAN_UPT,P.TARIKH_KEMASKINI, P.NO_RUJUKAN_PTG "+
		    		  " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S,TBLRUJKEMENTERIAN K, "+
		    		  " (SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H, TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB, "+
		    		  " TBLPPTPIHAKBERKEPENTINGAN PB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
		    		  " AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN "+
		    		  " AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' "+
		    		  " AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ) CHECK_H  "+               
		    		  " WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN "+
		    		  " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND SU.ID_SUBURUSAN = '52'  AND CHECK_H.ID_PERMOHONAN = P.ID_PERMOHONAN "+
		    		  " AND P.ID_STATUS = S.ID_STATUS  ";
             
		    
		      //sql +=" AND P.ID_STATUS NOT IN (199,200,201,203,204,205,1610249) ";
		     
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
		      sql +=" ORDER BY P.TARIKH_KEMASKINI DESC ";			      		      
		      myLogger.info("SQL DEFAULT PB :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      		      
		      Hashtable h = null;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
		    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
		    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
		    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));		
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
		    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));/*
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail")==null?"":rs.getString("no_fail"));
			    	}*/
		    	  h.put("no_fail",rs.getString("no_fail")==null?"":rs.getString("no_fail"));
		    	  h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));		    	  
		    	  list.addElement(h);
		    	  bil++;		    	  
		      }
		      myLogger.info("SQL SETEL ");
				return list;
		    }
		    finally{
		      if (db != null) db.close();
		    }
	 }
	 
		@SuppressWarnings("unchecked")
		public Vector setCarianFail(String usid, String txtNoFail, String socKementerian, String userIdNegeri)throws Exception {
		    Db db = null;
		    String sql = "";
		    String nofail = "";
		    
		    try {
		      listCarian = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      nofail = txtNoFail.trim();      
		  		     
		      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
		      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini, p.no_rujukan_ptg ";
		      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
		      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
		      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
		      sql +=" AND ( ";
		      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
		      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
		      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
		      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
		      sql +=" ) > 0  ";
		      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
		      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
		      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
		     //sql +=" AND P.ID_STATUS NOT IN (199,200,201,203,204,205,1610249) ";
		      
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    		}
		      
				//NO FAIL
				if (txtNoFail != "" && txtNoFail != null) {
					if (!nofail.equals("")) {
						//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
						sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
							" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
					}
				}//close carian by nofail
				
				
				//SOCKEMENTERIAN
				if (socKementerian != null) {
					if (!socKementerian.trim().equals("") && !socKementerian.trim().equals("0")) {
						sql = sql + " AND F.ID_KEMENTERIAN = " + socKementerian + "  ";
					}
				}
	  
				//sorting
				sql +=" ORDER BY P.ID_STATUS ASC ";
//				myLogger.info("SQL CARIAN :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
	  
				Hashtable h;
				int bil = 1;
				while (rs.next()) {					
				  h = new Hashtable();		  
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
		    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
		    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
		    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));		
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
		    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
		    	  if(rs.getString("no_fail") == null){
			    		h.put("no_fail","Belum Diluluskan");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
					listCarian.addElement(h);
					bil++;
				}
				return listCarian;
		} finally {
			if (db != null) db.close();
		}

	}
		// CLOSE
		
		// GET LIST PEMOHON AP
		
		 @SuppressWarnings("unchecked")
		public Vector getListPemohonAP(String userIdNegeri)throws Exception {		 
			    
			 Db db = null;
			 String sql = "";		    
			    
			 try{
				 	listPageDepan = new Vector();
				 	db = new Db();
			     	Statement stmt = db.getStatement();		     
			      /*
			      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
			      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini, p.no_rujukan_ptg ";
			      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
			      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
			      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
			      sql +=" AND ( ";
			      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
			      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
			      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
			      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
			      sql +=" ) > 0  ";
			      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
			      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
			      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
			      //sql +=" AND P.ID_STATUS NOT IN (181,183,184,185,186,187,220,1610248) ";
  
		    		// ADDED BY ELLY 14 JUNE 2010
			      if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
			      
			      sql +=" ORDER BY P.ID_STATUS DESC, P.TARIKH_KEMASKINI DESC ";			      		      
			      System.out.println("sql list bantahan AP : "+sql);
			      */
			      
			      
			      
			      
			      sql = " SELECT DISTINCT P.ID_STATUS, P.ID_PERMOHONAN, P.NO_PERMOHONAN, F.ID_FAIL,NVL(F.NO_FAIL,'Belum Diluluskan') AS NO_FAIL, P.TARIKH_PERMOHONAN, "+
			    		  " SU.NAMA_SUBURUSAN,K.NAMA_KEMENTERIAN, S.KETERANGAN, P.NO_RUJUKAN_UPT,P.TARIKH_KEMASKINI, P.NO_RUJUKAN_PTG "+
			    		  " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S,TBLRUJKEMENTERIAN K, "+
			    		  " (SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H, TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB, "+
			    		  " TBLPPTPIHAKBERKEPENTINGAN PB WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "+
			    		  " AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN "+
			    		  " AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' "+
			    		  " AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ) CHECK_H  "+               
			    		  " WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN "+
			    		  " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND SU.ID_SUBURUSAN = '52'  AND CHECK_H.ID_PERMOHONAN = P.ID_PERMOHONAN "+
			    		  " AND P.ID_STATUS = S.ID_STATUS  ";
	             
			    
			      //sql +=" AND P.ID_STATUS NOT IN (199,200,201,203,204,205,1610249) ";
			     
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		
			      sql +=" ORDER BY P.TARIKH_KEMASKINI DESC ";
			      
			      myLogger.info("sql list bantahan AP : "+sql);
			      
			      
			      
			      
			      ResultSet rs = stmt.executeQuery(sql);		      		      
			      Hashtable h = null;
			      int bil = 1;
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("bil", bil);
			    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
			    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
			    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
			    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));		
			    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
			    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
			    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
			    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
			    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
			    	  if(rs.getString("no_fail") == null){
				    		h.put("no_fail","Belum Diluluskan");
				    	}else{
				    		h.put("no_fail",rs.getString("no_fail"));
				    	}
			    	  h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));		    	  
			    	  listPageDepan.addElement(h);
			    	  bil++;		    	  
			      }
					return listPageDepan;
			    }
			    finally{
			      if (db != null) db.close();
			    }
		 }		
		 
			@SuppressWarnings("unchecked")
			public Vector setCarianFailAP(String usid, String txtNoFail, String socKementerian, String userIdNegeri)throws Exception {
			    Db db = null;
			    String sql = "";
			    String nofail = "";
			    
			    try {
			      listCarian = new Vector();
			      db = new Db();
			      Statement stmt = db.getStatement();
			      nofail = txtNoFail.trim();      
			  		     
			      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
			      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini, p.no_rujukan_ptg ";
			      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
			      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
			      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
			    /*  sql +=" AND ( ";
			      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM ";	
			      sql +=" WHERE HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
			      sql +=" AND P1.ID_PERMOHONAN = p.id_permohonan ";
			      sql +=" ) > 0  ";*/     
			      sql +=" AND ( ";
			      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
			      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
			      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
			      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
			      sql +=" ) > 0  ";
			      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
			      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
			      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
			      //sql +=" AND P.ID_STATUS NOT IN (181,183,184,185,186,187,220,1610248) ";
			      
		    		// ADDED BY ELLY 14 JUNE 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}
			      
					//NO FAIL
					if (txtNoFail != "" && txtNoFail != null) {
						if (!nofail.equals("")) {
							//sql = sql + " AND UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%'";
							sql += " AND (UPPER(f.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(p.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(p.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(p.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
						}
					}//close carian by nofail
					
					//SOCKEMENTERIAN
					if (socKementerian != null) {
						if (!socKementerian.trim().equals("") && !socKementerian.trim().equals("0")) {
							sql = sql + " AND F.ID_KEMENTERIAN = " + socKementerian + "  ";
						}
					}
		  
					//sorting
					sql +=" ORDER BY P.ID_STATUS DESC, P.TARIKH_KEMASKINI DESC ";	
				
					ResultSet rs = stmt.executeQuery(sql);
		  
					Hashtable h;
					int bil = 1;
					while (rs.next()) {					
					  h = new Hashtable();		  
					  h.put("bil", bil);					  
					  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
			    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
			    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
			    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));							
			    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
			    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
			    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
			    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
			    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
			    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
			    	  if(rs.getString("no_fail") == null){
				    		h.put("no_fail","Belum Diluluskan");
				    	}else{
				    		h.put("no_fail",rs.getString("no_fail"));
				    	}
			    	  h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));
						listCarian.addElement(h);
						bil++;
					}
					return listCarian;
			} finally {
				if (db != null) db.close();
			}

		}
		// CLOSE
			
		// GET LIST PEMOHON PB ONLINE
		
//		 public Vector getListPemohonPBOnlihe(String usid)throws Exception {		 
//			    Db db = null;
//			    String sql = "";		    
//			    try{
//			      list = new Vector();
//			      db = new Db();
//			      Statement stmt = db.getStatement();		     
//			      
//			      sql = " SELECT C.ID_FAIL,D.ID_PERMOHONAN,ST.ID_STATUS,C.NO_FAIL,F.NAMA_PB,F.NO_PB,D.NO_RUJUKAN_PTG,D.NO_RUJUKAN_PTD,H.NAMA_KEMENTERIAN,D.TARIKH_PERMOHONAN ";		      
//			      sql +=" FROM USERS A, ";
//			      sql +=" USERS_ONLINE B, ";
//			      sql +=" TBLPFDFAIL C, ";
//			      sql +=" TBLPPTPERMOHONAN D, ";
//			      sql +=" TBLPPTHAKMILIK E, ";
//			      sql +=" TBLPPTPIHAKBERKEPENTINGAN F, ";	
//			      sql +=" TBLPPTHAKMILIKPB G, ";
//			      sql +=" TBLRUJKEMENTERIAN H, ";
//			      sql +=" TBLRUJSUBURUSAN SU, ";
//			      sql +=" TBLRUJSTATUS ST ";
//			      sql +=" WHERE A.USER_ID = B.USER_ID ";
//			      sql +=" AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
//			      sql +=" AND C.ID_FAIL = D.ID_FAIL ";
//			      sql +=" AND D.ID_PERMOHONAN = E.ID_PERMOHONAN ";
//			      sql +=" AND E.ID_HAKMILIK = G.ID_HAKMILIK ";
//			      sql +=" AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
//			      sql +=" AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
//			      sql +=" AND B.NO_KP_BARU = F.NO_PB(+) ";
//			      sql +=" AND D.ID_STATUS = ST.ID_STATUS ";
//			      sql +=" AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";			      
//			      sql +=" AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";
//			      sql +=" AND SU.ID_SUBURUSAN = '52' ";
//			      sql +=" AND A.USER_ID = '"+usid+"' ";			    			      
//			      sql +=" ORDER BY D.TARIKH_KEMASKINI DESC ";	
//			      
//			      myLogger.info("SQL PB ONLINE :: "+sql);
//			      ResultSet rs = stmt.executeQuery(sql);		      		      
//			      Hashtable h = null;
//			      int bil = 1;
//			      while (rs.next()) {
//			    	  h = new Hashtable();
//			    	  h.put("bil", bil);
//			    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
//			    	  h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
//			    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));			    	  
//			    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
//			    	  h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));		    	  
//			    	  h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
//			    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")==null?"":rs.getString("NO_RUJUKAN_PTG"));
//			    	  h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")==null?"":rs.getString("NO_RUJUKAN_PTD"));
//			    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
//			    	  h.put("tarikh_permohonan", rs.getString("TARIKH_PERMOHONAN")==null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));			    	  
//			    	  list.addElement(h);
//			    	  bil++;		    	  
//			      }
//					return list;
//			    }
//			    finally{
//			      if (db != null) db.close();
//			    }
//		 }	
		 
			public Vector setCarianFailPBOnline(String usid, String txtNoFail, String tarikh_permohonan)throws Exception {
			    Db db = null;
			    String sql = "";
			    String nofail = "";
			    
			    try {
			      listCarianPBOnline = new Vector();
			      db = new Db();
			      Statement stmt = db.getStatement();
			      nofail = txtNoFail.trim();      
			  		     
			      sql = " SELECT C.ID_FAIL,D.ID_PERMOHONAN,ST.ID_STATUS,C.NO_FAIL,F.NAMA_PB,F.NO_PB,D.NO_RUJUKAN_PTG,D.NO_RUJUKAN_PTD,H.NAMA_KEMENTERIAN,D.TARIKH_PERMOHONAN ";
			      sql +=" FROM USERS A, ";
			      sql +=" USERS_ONLINE B, ";
			      sql +=" TBLPFDFAIL C, ";
			      sql +=" TBLPPTPERMOHONAN D, ";
			      sql +=" TBLPPTHAKMILIK E, ";
			      sql +=" TBLPPTPIHAKBERKEPENTINGAN F, ";	
			      sql +=" TBLPPTHAKMILIKPB G, ";
			      sql +=" TBLRUJKEMENTERIAN H, ";
			      sql +=" TBLRUJSUBURUSAN SU, ";
			      sql +=" TBLRUJSTATUS ST";
			      sql +=" WHERE A.USER_ID = B.USER_ID ";
			      sql +=" AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
			      sql +=" AND C.ID_FAIL = D.ID_FAIL ";
			      sql +=" AND D.ID_PERMOHONAN = E.ID_PERMOHONAN ";
			      sql +=" AND E.ID_HAKMILIK = G.ID_HAKMILIK ";
			      sql +=" AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
			      sql +=" AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
			      sql +=" AND B.NO_KP_BARU = F.NO_PB(+) ";
			      sql +=" AND D.ID_STATUS = ST.ID_STATUS ";
			      sql +=" AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";			      
			      sql +=" AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";
			      sql +=" AND SU.ID_SUBURUSAN = '52' ";
			      sql +=" AND A.USER_ID = '"+usid+"' ";			      			    
			      
			    //NO FAIL
					if (txtNoFail != "" && txtNoFail != null) {
						if (!nofail.equals("")) {
							sql += " AND (UPPER(c.no_fail) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(d.no_rujukan_ptg) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(d.no_rujukan_upt) LIKE '%" + nofail.toUpperCase() + "%' " +
								" OR UPPER(d.no_rujukan_ptd) LIKE '%" + nofail.toUpperCase() + "%')";
						}
					}//close carian by nofail
					
					// TARIKH PERMOHONAN
					if (tarikh_permohonan != null) {
						if (!tarikh_permohonan.toString().trim().equals("")) {			
							sql = sql + " AND REPLACE(TO_CHAR(D.TARIKH_PERMOHONAN,'dd/MM/YYYY'),' ','') = '" + tarikh_permohonan +"' ";
						}
					}					
		  
					//sorting
					sql +=" ORDER BY D.ID_STATUS ASC ";
				myLogger.info("SQL PB ONLINE CARIAN :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
		  
					Hashtable h;
					int bil = 1;
					while (rs.next()) {					
					  h = new Hashtable();		  
			    	  h.put("bil", bil);
			    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
			    	  h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
			    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));	
			    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
			    	  h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));		    	  
			    	  h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
			    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")==null?"":rs.getString("NO_RUJUKAN_PTG"));
			    	  h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")==null?"":rs.getString("NO_RUJUKAN_PTD"));
			    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
			    	  h.put("tarikh_permohonan", rs.getString("TARIKH_PERMOHONAN")==null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));

			    	  listCarianPBOnline.addElement(h);
			    	  bil++;
					}
					return listCarianPBOnline;
			} finally {
				if (db != null) db.close();
			}

		}
		// CLOSE	
			
		// PEMOHON AGENSI PEMOHON ONLINE
			
			 public Vector getListPemohonAPOnlihe(String userIdNegeri)throws Exception {		 
				    Db db = null;
				    String sql = "";		    
				    try{
				      list = new Vector();
				      db = new Db();
				      Statement stmt = db.getStatement();		     
				      
				      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
				      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini ";
				      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
				      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
				      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
				      sql +=" AND ( ";
				      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
				      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
				      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
				      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
				      sql +=" ) > 0  ";
				      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
				      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
				      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";		      
				      sql +=" AND P.ID_STATUS NOT IN (181,183,184,185,186,187,220,1610248) ";
				      
			    		// ADDED BY ELLY 14 JUNE 2010
			    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			    		}
				      
				      sql +=" ORDER BY P.TARIKH_KEMASKINI DESC ";			      		      
				      myLogger.info("SQL PB ONLINE :: "+sql);
				      ResultSet rs = stmt.executeQuery(sql);		      		      
				      Hashtable h = null;
				      int bil = 1;
				      while (rs.next()) {
				    	  h = new Hashtable();
				    	  h.put("bil", bil); 
				    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
				    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
				    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
				    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));		
				    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
				    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
				    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
				    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
				    	  if(rs.getString("no_fail") == null){
					    		h.put("no_fail","Belum Diluluskan");
					    	}else{
					    		h.put("no_fail",rs.getString("no_fail"));
					    	}
				    	  h.put("tarikh_kemaskini", rs.getString("tarikh_kemaskini")==null?"":sdf.format(rs.getDate("tarikh_kemaskini")));		    	  
				    	  list.addElement(h);
				    	  bil++;		    	  
				      }
						return list;
				    }
				    finally{
				      if (db != null) db.close();
				    }
			 }	
			 
				@SuppressWarnings("unchecked")
				public Vector setCarianFailAPOnline(String usid, String txtNoFail, String socKementerian, String userIdNegeri)throws Exception {
				    Db db = null;
				    String sql = "";
				    
				    try {
				      listCarianAPOnline = new Vector();
				      db = new Db();
				      Statement stmt = db.getStatement();
				      txtNoFail = txtNoFail.trim();      
				  		     
				      sql = " SELECT DISTINCT p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail,f.no_fail, p.tarikh_permohonan, su.nama_suburusan, ";
				      sql +=" k.nama_kementerian, s.keterangan, p.no_rujukan_upt,p.tarikh_kemaskini ";
				      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
				      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
				      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
				      sql +=" AND ( ";
				      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
				      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
				      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
				      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
				      sql +=" ) > 0  ";
				      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
				      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
				      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
				      sql +=" AND P.ID_STATUS NOT IN (181,183,184,185,186,187,220,1610248) ";
				      
			    		// ADDED BY ELLY 14 JUNE 2010
			    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
			    		}
				      
						//NO FAIL / NO RUJ JKPTG NEGERI
						if(txtNoFail != null)
						{
							if(!txtNoFail.trim().equals("")) {
								sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%" + txtNoFail.toUpperCase() + "%'";
							}
						}
			  
						//SOCKEMENTERIAN
						if (socKementerian != null) {
							if (!socKementerian.trim().equals("") && !socKementerian.trim().equals("0")) {
								sql = sql + " AND F.ID_KEMENTERIAN = " + socKementerian + "  ";
							}
						}
			  
						//sorting
						sql +=" ORDER BY P.ID_STATUS ASC ";
//						myLogger.info("SQL PB ONLINE CARIAN :: "+sql);
						ResultSet rs = stmt.executeQuery(sql);
			  
						Hashtable h;
						int bil = 1;
						while (rs.next()) {					
						  h = new Hashtable();		  
				    	  h.put("bil", bil);
				    	  h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));		
				    	  h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));		
				    	  h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));		
				    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));		
				    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
				    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));		    	  
				    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
				    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
				    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
				    	  if(rs.getString("no_fail") == null){
					    		h.put("no_fail","Belum Diluluskan");
					    	}else{
					    		h.put("no_fail",rs.getString("no_fail"));
					    	}
				    	    listCarianAPOnline.addElement(h);
							bil++;
						}
						return listCarianAPOnline;
				} finally {
					if (db != null) db.close();
				}

			}
			// CLOSE				
			
	 @SuppressWarnings("unchecked")
	public Vector getHeader(String id_fail,String id_permohonan)throws Exception {		 
			Db db = null;
			String sql = "";
			try{
				header = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT P.ID_PERMOHONAN, F.ID_NEGERI, P.ID_NEGERI, P.NO_PERMOHONAN, P.ID_FAIL, "; 
				sql += " F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN, P.ID_STATUS, ";
				sql += " F.ID_KEMENTERIAN, P.ID_AGENSI, P.FLAG_PERUNTUKAN, P.FLAG_SEGERA, ";
				sql += " P.ID_DAERAH, P.TUJUAN, P.NO_RUJUKAN_SURAT, P.TARIKH_KEHENDAKI, "; 
				sql += " P.ALAMAT1, P.ALAMAT2, P.ALAMAT3, P.POSKOD, P.ID_MUKIM, ";
				sql += " K.NAMA_KEMENTERIAN, B.NAMA_DAERAH, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, "; 
				sql += " P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN, C.NAMA_NEGERI,P.TARIKH_TERIMA,AG.NAMA_AGENSI "; 
				sql += " FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJNEGERI C, "; 
				sql += " TBLRUJDAERAH B,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S, TBLRUJAGENSI AG "; 
				sql += " WHERE F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) AND F.ID_FAIL = P.ID_FAIL AND B.ID_DAERAH = P.ID_DAERAH(+) ";
				sql += " AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN(+) AND P.ID_STATUS = S.ID_STATUS ";
				sql += " AND P.ID_NEGERI = C.ID_NEGERI(+) AND P.ID_AGENSI = AG.ID_AGENSI(+) AND P.ID_FAIL = '"+ id_fail +"' AND P.ID_PERMOHONAN = '"+ id_permohonan +"' ";
//				myLogger.info("SQL getHeader :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
		    	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
		    	h.put("projek_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    	if(rs.getString("id_suburusan") == null){
		    		h.put("id_suburusan","");
		    	}else{
		    		h.put("id_suburusan",rs.getString("id_suburusan"));
		    	}
		    	h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
		    	h.put("tarikh_terima", rs.getString("tarikh_terima")==null?"":sdf.format(rs.getDate("tarikh_terima")));
		    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		    	if(rs.getString("id_kementerian") == null){
		    		h.put("id_kementerian","");
		    	}else{
		    		h.put("id_kementerian",rs.getString("id_kementerian"));
		    	}
		    	if(rs.getString("id_agensi") == null){
		    		h.put("id_agensi","");
		    	}else{
		    		h.put("id_agensi",rs.getString("id_agensi"));
		    	}
		    	h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
		    	h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
		    	if(rs.getString(3) == null){
		    		h.put("id_negeri_projek","");
		    	}else{
		    		h.put("id_negeri_projek",rs.getString(3));
		    	}
		    	if(rs.getString("id_daerah") == null){
		    		h.put("id_daerah","");
		    	}else{
		    		h.put("id_daerah",rs.getString("id_daerah"));
		    	}
		    	h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
		    	h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
		    	h.put("tarikh_kehendaki", rs.getString("tarikh_kehendaki")==null?"":sdf.format(rs.getDate("tarikh_kehendaki")));
		    	h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	if(rs.getString("id_negeri") == null){
		    		h.put("id_negeri","");
		    	}else{
		    		h.put("id_negeri",rs.getString("id_negeri"));
		    	}
		    	if(rs.getString("id_mukim") == null){
		    		h.put("id_mukim","");
		    	}else{
		    		h.put("id_mukim",rs.getString("id_mukim"));
		    	}
		    	header.addElement(h);
		      	}      
				}
				finally{
					if(db != null)db.close();
				}
				return header;
	 }
	
	 @SuppressWarnings("unchecked")
	public Vector getHakmilik(String id_hakmilikpb)throws Exception {		 		 
			 Db db = null;
			 String sql = "";
			try{
					getHakmilik = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT DISTINCT A.ID_HAKMILIK, A.NO_LOT, A.ID_UNITLUASLOT, B.KETERANGAN AS UNITLOT, A.NO_PT, A.ID_UNITLUASPT, E.KETERANGAN AS UNITPT, C.ID_HAKMILIKPB, ";					
					sql += " CASE ";
					sql += " WHEN A.NO_LOT IS NOT NULL AND A.NO_PT IS NULL THEN A.NO_LOT ";
					sql += " WHEN A.NO_LOT IS NULL AND A.NO_PT IS NULL THEN lt.keterangan || A.NO_PT ";
					sql += " WHEN A.NO_LOT IS NULL AND A.NO_PT IS NULL THEN lt.keterangan || A.NO_PT ";
					sql += " WHEN A.NO_LOT IS NULL AND A.NO_PT IS NOT NULL THEN lt.keterangan || A.NO_PT ";
					sql += " WHEN A.NO_LOT IS NOT NULL AND A.NO_PT IS NOT NULL THEN lt.keterangan || A.NO_PT || CHR(32) || CHR(40) || A.NO_LOT || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT ";
					sql += " FROM TBLPPTHAKMILIK A,TBLPPTHAKMILIKPB C,TBLRUJLUAS B,TBLRUJLUAS E,TBLPPTPIHAKBERKEPENTINGAN D, TBLRUJLOT LT ";
					sql += " WHERE A.ID_HAKMILIK = C.ID_HAKMILIK AND A.ID_UNITLUASLOT = B.ID_LUAS(+) AND A.ID_UNITLUASPT = E.ID_LUAS(+) ";
					sql += " AND C.ID_PIHAKBERKEPENTINGAN = D.ID_PIHAKBERKEPENTINGAN AND A.ID_LOT = LT.ID_LOT(+) AND C.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";
//					myLogger.info("SQL GETHAKMILIK CASE :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();		    	 
			        h.put("id_hakmilik", rs.getString("ID_HAKMILIK") == null ?"":rs.getString("ID_HAKMILIK"));
			    	h.put("no_lot", rs.getString("NO_LOT") == null ?"":rs.getString("NO_LOT"));
			    	h.put("id_unitluaslot", rs.getString("ID_UNITLUASLOT") == null?"":rs.getString("ID_UNITLUASLOT"));
			    	h.put("unitlot", rs.getString(4) == null ?"":rs.getString(4));
			    	h.put("no_pt", rs.getString("NO_PT") == null ?"":rs.getString("NO_PT"));
			    	h.put("id_unitluaspt", rs.getString("ID_UNITLUASPT") == null ?"":rs.getString("ID_UNITLUASPT"));
			    	h.put("unitpt", rs.getString(7) == null ?"":rs.getString(7));		    	
			    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB") == null ?"":rs.getString("ID_HAKMILIKPB"));
			    	h.put("no_lotpt", rs.getString("NO_LOTPT") == null ?"":rs.getString("NO_LOTPT"));			    	
			    	getHakmilik.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getHakmilik;					
	 }
	 
	 @SuppressWarnings("unchecked")
	public Vector getAlamatPembantah(String id_pihakberkepentingan)throws Exception {
		String key = "getAlamatPembantah"+id_pihakberkepentingan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
		  return (Vector)cachedObject.getObjectValue();
		} else {		 							
			 Db db = null;
			 String sql = "";
			try{
					getAlamatPembantah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT A.ID_PIHAKBERKEPENTINGAN,A.ID_JENISPB,B.KETERANGAN,A.ID_JENISNOPB, A.NO_PB, A.NAMA_PB, "; 
					sql += " C.ALAMAT1,C.ALAMAT2,C.ALAMAT3,C.POSKOD,C.ID_NEGERI,C.ID_BANDAR ";
					sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLRUJJENISPB B, TBLPPTHAKMILIKPB C ";
					sql += " WHERE A.ID_JENISPB = B.ID_JENISPB(+) AND C.ID_PIHAKBERKEPENTINGAN = A.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND A.ID_PIHAKBERKEPENTINGAN = '"+ id_pihakberkepentingan +"'  ";
					
//					myLogger.info("SQL getAlamatPembantah :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			        h.put("id_jenispb", rs.getString("ID_JENISPB")==null?"":rs.getString("ID_JENISPB"));
			    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")==null?"":rs.getString("ID_JENISNOPB"));
			    	h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
			    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
			    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
			    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
			    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
			    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
			    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
			    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
			    	getAlamatPembantah.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				myCache.put(new Element(key, getAlamatPembantah));
				return getAlamatPembantah;
		}
	 }

	@SuppressWarnings("unchecked")
	public Vector getMaklumatBantahan(String id_hakmilikpb, String _MaxIdSiasatan, String id_warta) throws Exception {		 							
			 Db db = null;
			 String sql = "";
			try{
					getMaklumatBantahan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT A.MAKLUMAT_BANTAHAN_TAMAT_TEMPOH,A.ID_BANTAHAN,A.NO_BANTAHAN,A.JENIS_PEMBANTAH,A.TARIKH_TERIMA,A.TARIKH_BORANGN,A.ID_PIHAKBERKEPENTINGAN, "+
							"A.FLAG_PENERIMA_PAMPASAN,A.FLAG_BAHAGIAN_PAMPASAN,A.STATUS_BANTAHAN,A.ALASAN,A.KEPENTINGANKEATAS,A.FLAG_ONLINE, "+
							"A.FLAG_UKUR_LUAS,A.FLAG_PAMPASAN,A.TARIKH_TERIMA_AWARD,S.NO_SIASATAN,A.AMAUN_TUNTUTAN,A.FLAG_SYARAT,"; 
					sql += " NVL(B.ID_PIHAKBERKEPENTINGAN,0) ID_PIHAKBERKEPENTINGAN,B.ID_JENISPB,B.NAMA_PB,B.NO_PB,"+
							"C.NO_HAKMILIK,C.NO_PT,C.NO_LOT, "+
							"D.KETERANGAN,";
					sql += " E.ID_HAKMILIK,E.ALAMAT1,E.ALAMAT2,E.ALAMAT3,E.POSKOD,NVL(E.ID_NEGERI,0) ID_NEGERI,E.ID_BANDAR,"+
							"E.NO_TEL_RUMAH,E.NO_HANDPHONE,E.NO_FAX,E.FLAG_BANTAHAN,"+
							"F.ID_WARTA,";
					sql += " G.KETERANGAN AS DESC_STATUS_BANTAHAN, G.ID_STATUS, ";
					sql += " S.ID_SIASATAN, ";
					sql += " RN.NAMA_NEGERI"+
							",NVL(NOPB.ID_JENISNOPB,'') ID_JENISNOPB,NVL(NOPB.KETERANGAN,'') JENISNOPB ";
					sql += " FROM TBLPPTBANTAHAN A"+
							",TBLPPTPIHAKBERKEPENTINGAN B"+
							",TBLPPTHAKMILIK C"+
							",TBLRUJJENISPB D"+
							",TBLPPTHAKMILIKPB E " +
							",TBLPPTSIASATAN S " +
							",TBLPPTWARTA F " +
							",TBLRUJSTATUS G "+
							",TBLRUJNEGERI RN,TBLRUJJENISNOPB NOPB ";	
					sql +=	" WHERE E.ID_JENISPB=D.ID_JENISPB(+) "+
							" AND E.ID_PIHAKBERKEPENTINGAN=B.ID_PIHAKBERKEPENTINGAN ";	
					sql += 	" AND E.ID_HAKMILIK=C.ID_HAKMILIK "+
							" AND E.ID_HAKMILIKPB=A.ID_HAKMILIKPB AND S.ID_HAKMILIK = C.ID_HAKMILIK ";
					sql += 	" AND C.ID_PERMOHONAN = F.ID_PERMOHONAN "+
							" AND A.STATUS_BANTAHAN = G.ID_STATUS(+) "+
							" AND E.ID_NEGERI = RN.ID_NEGERI(+) "+
							" AND B.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) "+
							" AND A.ID_HAKMILIKPB = '"+id_hakmilikpb+"' " + "AND S.ID_SIASATAN = '"+_MaxIdSiasatan+"' AND F.ID_WARTA = '"+id_warta+"' " +
							" ";	
					myLogger.info("SQL GETMAKLUMATBANTAHAN :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	//Bantahan MT
			    	h.put("nama", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
			    	h.put("nama_negeri", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
			    	h.put("noPB", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
			    	h.put("idRujukanPB", rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    	h.put("jenisPB", rs.getString("JENISNOPB"));
			    	h.put("idJenisNoPB", rs.getString("ID_JENISNOPB"));
	
			    	h.put("maklumat_bantahan_tamat_tempoh", rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH")==null?"":rs.getString("MAKLUMAT_BANTAHAN_TAMAT_TEMPOH"));
			    	h.put("id_status_bantahan", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
			    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
			    	h.put("no_bantahan", rs.getString("NO_BANTAHAN")==null?"":rs.getString("NO_BANTAHAN"));
			    	h.put("jenis_pembantah", rs.getString("JENIS_PEMBANTAH")==null?"":rs.getString("JENIS_PEMBANTAH"));
			    	h.put("tarikh_terima", rs.getString("TARIKH_TERIMA")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA")));
			    	h.put("tarikh_borangn", rs.getString("TARIKH_BORANGN")==null?"":sdf.format(rs.getDate("TARIKH_BORANGN")));
			    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
			    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
			    	h.put("alasan", rs.getString("ALASAN")==null?"":rs.getString("ALASAN"));
			    	h.put("kepentingankeatas", rs.getString("KEPENTINGANKEATAS")==null?"":rs.getString("KEPENTINGANKEATAS"));
			    	h.put("id_jenispb", rs.getString("ID_JENISPB")==null?"":rs.getString("ID_JENISPB"));
			    	h.put("keteranganjenispb", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
			    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
			    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
			    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
			    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
			    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
			    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
			    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
			    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
			    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
			    	h.put("flag_syarat", rs.getString("FLAG_SYARAT")==null?"":rs.getString("FLAG_SYARAT"));
			    	h.put("no_tel_rumah", rs.getString("NO_TEL_RUMAH")==null?"":rs.getString("NO_TEL_RUMAH"));
			    	h.put("no_hp", rs.getString("NO_HANDPHONE")==null?"":rs.getString("NO_HANDPHONE"));
			    	h.put("no_fax", rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
			    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
			    	h.put("flag_penerima_pampasan", rs.getString("FLAG_PENERIMA_PAMPASAN")==null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
			    	h.put("flag_bahagian_pampasan", rs.getString("FLAG_BAHAGIAN_PAMPASAN")==null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
			    	h.put("flag_ukur_luas", rs.getString("FLAG_UKUR_LUAS")==null?"":rs.getString("FLAG_UKUR_LUAS"));
			    	h.put("flag_pampasan", rs.getString("FLAG_PAMPASAN")==null?"":rs.getString("FLAG_PAMPASAN"));			    	
			    	h.put("tarikh_terima_award", rs.getString("TARIKH_TERIMA_AWARD")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_AWARD")));
			    	h.put("no_siasatan", rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));
			    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
			    	h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
			    	h.put("id_warta", rs.getString("ID_WARTA")==null?"":rs.getString("ID_WARTA"));
			    	h.put("desc_status_bantahan", rs.getString("DESC_STATUS_BANTAHAN")==null?"":rs.getString("DESC_STATUS_BANTAHAN"));
			    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
			    	getMaklumatBantahan.addElement(h);
			      	
			     }      
				
			}finally{
				if(db != null)db.close();
				
			}	
			return getMaklumatBantahan;
				
	}

	@SuppressWarnings("unchecked")
	public Vector getMaklumatPampasan(String id_hakmilikpb) throws Exception {		 							
			 Db db = null;
			 String sql = "";
			try{
					getMaklumatPampasan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT C.NO_AKAUN,D.NAMA_BANK,D.NO_BAYARAN,F.BAYAR_PAMPASAN,D.CARA_BAYAR,G.KETERANGAN "; 
					sql += " FROM TBLPPTPERMOHONAN A,TBLPPTHAKMILIK B, ";
					sql += " TBLPPTHAKMILIKPB C,TBLPPTBAYARAN D, ";
					sql += " TBLPPTTERIMABAYARAN E,TBLPPTAWARD F, ";
					sql += " TBLRUJCARABAYAR G ";
					sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
					sql += " AND B.ID_HAKMILIK = C.ID_HAKMILIK AND C.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
					sql += " AND D.ID_TERIMABAYARAN = E.ID_TERIMABAYARAN ";
					sql += " AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB ";
					sql += " AND D.CARA_BAYAR = G.ID_CARABAYAR ";
					sql += " AND C.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";
					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL getMaklumatPampasan :: "+sql);
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("no_akaunPampasan", rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
			    	h.put("nama_bank", rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
			    	h.put("no_bayaran", rs.getString("NO_BAYARAN")==null?"":rs.getString("NO_BAYARAN"));
			    	h.put("amaun_bayaran", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));
			    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
			    	h.put("keterangancarabayar", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));			    	
			    	getMaklumatPampasan.addElement(h);
			      	}      
				}catch (SQLException se) { 
			    	throw new Exception(se.getMessage());
			    }
					finally{
						if(db != null)db.close();
					}	
				return getMaklumatPampasan;
	}	
	
	@SuppressWarnings("unchecked")
	public Vector getMaklumatDeposit(String id_hakmilikpb) throws Exception {		 							
			 Db db = null;
			 String sql = "";
			 try{
					getMaklumatDeposit = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT A.TARIKH_TERIMA_RESIT,A.TARIKH_RESIT,A.NO_RESIT,A.AMAUN_DEPOSIT,A.CARA_BAYAR,A.NO_RUJUKAN_BAYARAN,A.AMAUN_TUNTUTAN,"; 
					sql += " A.TARIKH_SURAT_BAYARDEPOSIT,A.TARIKH_AKHIR_BAYARDEPOSIT,B.ID_BANK,B.NO_AKAUN,A.FLAG_TERIMADEPOSIT,B.NAMA_PENGHANTAR,B.NAMA_BANK,B.CATATAN_PEMULANGAN_DEPOSIT ";
					sql += " FROM TBLPPTBANTAHAN A, TBLPPTBORANGO B ";
					sql += " WHERE A.ID_BANTAHAN=B.ID_BANTAHAN AND A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";												
					myLogger.info("SQL getMaklumatDeposit :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("tarikh_terima_resit", rs.getString("TARIKH_TERIMA_RESIT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_RESIT")));
			    	h.put("tarikh_resit", rs.getString("TARIKH_RESIT")==null?"":sdf.format(rs.getDate("TARIKH_RESIT")));
			    	h.put("no_resit", rs.getString("NO_RESIT")==null?"":rs.getString("NO_RESIT"));
			    	h.put("amaun_deposit", rs.getString("AMAUN_DEPOSIT")==null?"":Double.parseDouble(rs.getString("AMAUN_DEPOSIT")));
			    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
			    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
			    	h.put("no_rujukan_bayaran", rs.getString("NO_RUJUKAN_BAYARAN")==null?"":rs.getString("NO_RUJUKAN_BAYARAN"));
			    	h.put("tarikh_surat_bayardeposit", rs.getString("TARIKH_SURAT_BAYARDEPOSIT")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_BAYARDEPOSIT")));
			    	h.put("tarikh_akhir_bayardeposit", rs.getString("TARIKH_AKHIR_BAYARDEPOSIT")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_BAYARDEPOSIT")));			    				    	
			    	h.put("id_bank", rs.getString("ID_BANK")==null?"":rs.getString("ID_BANK"));
			    	h.put("no_akaun", rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
			    	h.put("flag_terimadeposit", rs.getString("FLAG_TERIMADEPOSIT")==null?"":rs.getString("FLAG_TERIMADEPOSIT"));
			    	h.put("nama_penghantar", rs.getString("NAMA_PENGHANTAR")==null?"":rs.getString("NAMA_PENGHANTAR"));
			    	h.put("nama_bank", rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
			    	h.put("catatan_pemulangan_deposit", rs.getString("CATATAN_PEMULANGAN_DEPOSIT")==null?"":rs.getString("CATATAN_PEMULANGAN_DEPOSIT"));
			    	getMaklumatDeposit.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getMaklumatDeposit;
	}

	 public static Hashtable getStatusFail(String id_permohonan) throws Exception {	
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =  " SELECT A.ID_STATUS FROM TBLPPTPERMOHONAN A WHERE A.ID_PERMOHONAN = '"+ id_permohonan +"' ";				
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
			    	h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
		      }
		      return h;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}
	 
	@SuppressWarnings("unchecked")
	public Vector getAlamatMahkamah(String idNegeriMhn,String id_pejabat) throws Exception {
		String key = "getAlamatMahkamah"+id_pejabat;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
		  return (Vector)cachedObject.getObjectValue();
		} else {		 							
			 Db db = null;
			 String sql = "";
			 try{
				 	getAlamatMahkamah = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT PJ.ID_PEJABAT, PJ.KOD_PEJABAT, PJ.NAMA_PEJABAT, PJ.ALAMAT1,PJ.ALAMAT2, PJ.ALAMAT3, PJ.POSKOD, PJ.NO_TEL, PJ.NO_FAX,DA.NAMA_DAERAH,DA.ID_DAERAH,PJ.ID_BANDAR,PJ.ID_NEGERI "; 
					sql += " FROM TBLRUJPEJABAT PJ, TBLRUJDAERAH da ";
					sql += " WHERE PJ.ID_JENISPEJABAT = 8 AND PJ.ID_DAERAH = da.ID_DAERAH ";
					sql += " AND PJ.ID_NEGERI = '"+ idNegeriMhn +"' and PJ.ID_PEJABAT = '"+ id_pejabat +"' ";															
//					myLogger.info("SQL getAlamatMahkamah :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("id_pejabat", rs.getString("ID_PEJABAT")==null?"":rs.getString("ID_PEJABAT"));
			    	h.put("kod_pejabat", rs.getString("KOD_PEJABAT")==null?"":rs.getString("KOD_PEJABAT"));
			    	h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
			    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
			    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
			    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
			    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
			    	h.put("no_tel", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
			    	h.put("no_fax", rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
			    	h.put("nama_daerah", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
			    	h.put("id_daerah", rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
			    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
			    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
			    	getAlamatMahkamah.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				myCache.put(new Element(key, getAlamatMahkamah));
				return getAlamatMahkamah;
		}
	}
	
	 @SuppressWarnings("unchecked")
	public static Hashtable getCheckingIdMahkamah(String id_hakmilikpb,String id_bantahan) throws Exception {	
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =  " SELECT O.ID_MAHKAMAH FROM TBLPPTBANTAHAN A, TBLPPTBORANGO O WHERE A.ID_BANTAHAN = O.ID_BANTAHAN AND A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' AND O.ID_BANTAHAN = '"+ id_bantahan +"' ";					     
//		      myLogger.info("getCheckingIdMahkamah :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
			    	h.put("id_mahkamah", rs.getString("ID_MAHKAMAH")==null?"":rs.getString("ID_MAHKAMAH"));
		      }
		      return h;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}

	@SuppressWarnings("unchecked")
	public static Hashtable getIdBorangO(String id_hakmilikpb,String id_bantahan) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT O.ID_BORANGO FROM TBLPPTBANTAHAN A, TBLPPTBORANGO O WHERE A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' AND O.ID_BANTAHAN = '"+ id_bantahan +"' ";					     
	      myLogger.info("SQL getIdBorangO :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("id_borango", rs.getString("ID_BORANGO")==null?"":rs.getString("ID_BORANGO"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}

	@SuppressWarnings("unchecked")
	public static Hashtable getIdAward(String id_hakmilikpb) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT A.ID_AWARD FROM TBLPPTAWARD A WHERE A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";					     
//	      myLogger.info("SQL getIdAward :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("id_award", rs.getString("ID_AWARD")==null?"":rs.getString("ID_AWARD"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}	
	
	@SuppressWarnings("unchecked")
	public static Hashtable getKeteranganPampasan(String id_hakmilikpb) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT B.KETERANGAN FROM TBLPPTAWARD A, TBLPPTTAMBAHAWARD B WHERE A.ID_AWARD = B.ID_AWARD AND A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";					     
	      myLogger.info("SQL getKeteranganPampasan :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}	
	
	@SuppressWarnings("unchecked")
	public Vector getDataBorangO(String idBorangO) throws Exception {	 							
			 Db db = null;
			 String sql = "";
			 try{
				 getDataBorangO = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT O.ID_BORANGO, O.TARIKH_BORANGO, O.ID_MAHKAMAH, A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, "; 
					sql += " A.ALAMAT3, A.POSKOD, A.ID_BANDAR, A.ID_NEGERI, A.NO_TEL, A.NO_FAX, O.NAMA_PENGHANTAR_BORANGO, O.NAMA_PENERIMA_BORANGO, O.TARIKH_HANTAR_BORANGO ";
					sql += " FROM TBLPPTBORANGO O, TBLRUJPEJABAT A ";
					sql += " WHERE O.ID_MAHKAMAH=A.ID_PEJABAT AND O.ID_BORANGO = '"+ idBorangO +"' ";																				
//					myLogger.info("getDataBorangO :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("id_borango", rs.getString("ID_BORANGO")==null?"":rs.getString("ID_BORANGO"));
			    	h.put("tarikh_borango", rs.getString("TARIKH_BORANGO")==null?"":sdf.format(rs.getDate("TARIKH_BORANGO")));
			    	h.put("id_mahkamah", rs.getString("ID_MAHKAMAH")==null?"":rs.getString("ID_MAHKAMAH"));
			    	h.put("nama_pejabat", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
			    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
			    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
			    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
			    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
			    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
			    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
			    	h.put("no_tel", rs.getString("NO_TEL")==null?"":rs.getString("NO_TEL"));
			    	h.put("no_fax", rs.getString("NO_FAX")==null?"":rs.getString("NO_FAX"));
			    	h.put("nama_penghantar_borango", rs.getString("NAMA_PENGHANTAR_BORANGO")==null?"":rs.getString("NAMA_PENGHANTAR_BORANGO"));
			    	h.put("nama_penerima_borango", rs.getString("NAMA_PENERIMA_BORANGO")==null?"":rs.getString("NAMA_PENERIMA_BORANGO"));
			    	h.put("tarikh_hantar_borango", rs.getString("TARIKH_HANTAR_BORANGO")==null?"":sdf.format(rs.getDate("TARIKH_HANTAR_BORANGO")));
			    	getDataBorangO.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getDataBorangO;
	}

//	public Hashtable getIdBantahan(String id_hakmilikpb) throws Exception {
//		    Db db = null;
//		    String sql = "";
//		    try {
//		      db = new Db();
//		      Statement stmt = db.getStatement();
//		      SQLRenderer r = new SQLRenderer();		      
//		      sql =  " SELECT A.ID_BANTAHAN FROM TBLPPTBANTAHAN A, TBLPPTHAKMILIKPB B WHERE A.ID_HAKMILIKPB = B.ID_HAKMILIKPB AND A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";					     
//		      myLogger.info("getIdBantahan :: "+sql);
//		      ResultSet rs = stmt.executeQuery(sql);
//		      Hashtable h = new Hashtable();
//		      if (rs.next()) {
//			    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
//		      }
//		      return h;
//		    }
//		    finally {
//		      if (db != null) db.close();
//		    }
//	}
	

	@SuppressWarnings("unchecked")
	public Vector getTarikhBorangG(String id_siasatan) throws Exception {	 
		 Db db = null;
		 String sql = "";
		 try{
			 getTarikhBorangG = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 
			 sql = " SELECT TARIKH_BORANGG FROM TBLPPTBORANGG WHERE ID_SIASATAN = '"+ id_siasatan +"'";
			 ResultSet rs = stmt.executeQuery(sql);					
			 Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("tarikh_borangg", rs.getString("TARIKH_BORANGG")==null?"":rs.getDate("TARIKH_BORANGG"));
		    	getTarikhBorangG.addElement(h);
		      	}      
			 
		 /*
		 if(getTarikhBorangG.size()==0)
		 {
			 h = new Hashtable();
		    	h.put("tarikh_borangg", "");
		    	getTarikhBorangG.addElement(h);
		 }
		 */
		 
	}
		 
				finally{
					if(db != null)db.close();
				}	
			return getTarikhBorangG;
		 
	}
	public Vector getMaklumatSusulan(String id_bantahan) throws Exception {	 							
			 Db db = null;
			 String sql = "";
			 try{
				 	getMaklumatSusulan = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT A.AMAUN_AWARD,A.TEMPOH_BAYAR,A.UNIT_TEMPOH,O.TARIKH_TERIMA_PERINTAH,O.TARIKH_PERINTAH, O.TARIKH_LANJUTAN_MAHKAMAH_OB, O.TARIKH_LANJUTAN_MAHKAMAH_PT, "; 
					sql += " O.FLAG_PULANG_DEPOSIT,O.KEPUTUSAN_MAHKAMAH,O.KOS_PENGAPIT_HAKIM,O.NO_RUJUKAN_TANAH,O.NO_RUJUKAN_MAHKAMAH, ";
					sql += " O.KOS_JPPH,O.NAMA_JPPH,O.KOS_SWASTA,O.NAMA_SWASTA,O.SYARIKAT_SWASTA";
					sql += " FROM TBLPPTBANTAHAN A, TBLPPTBORANGO O ";
					sql += " WHERE A.ID_BANTAHAN = O.ID_BANTAHAN AND A.ID_BANTAHAN = '"+ id_bantahan +"' ";	
					myLogger.info("SQL getMaklumatSusulan :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("no_rujukan_mahkamah", rs.getString("NO_RUJUKAN_MAHKAMAH")==null?"":rs.getString("NO_RUJUKAN_MAHKAMAH"));
			    	h.put("amaun_award", rs.getString("AMAUN_AWARD")==null?"":Double.parseDouble(rs.getString("AMAUN_AWARD")));
			    	h.put("tempoh_bayar", rs.getString("TEMPOH_BAYAR")==null?"":rs.getString("TEMPOH_BAYAR"));			    	
			    	h.put("unit_tempoh", rs.getString("UNIT_TEMPOH")==null?"":rs.getString("UNIT_TEMPOH"));
			    	h.put("tarikh_terima_perintah", rs.getString("TARIKH_TERIMA_PERINTAH")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_PERINTAH")));
			    	h.put("tarikh_lanjutan_mahkamah_ob", rs.getString("TARIKH_LANJUTAN_MAHKAMAH_OB")==null?"":sdf.format(rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_OB")));
			    	h.put("tarikh_lanjutan_mahkamah_pt", rs.getString("TARIKH_LANJUTAN_MAHKAMAH_PT")==null?"":sdf.format(rs.getDate("TARIKH_LANJUTAN_MAHKAMAH_PT")));
			    	h.put("tarikhTerimaPerintah_convert", rs.getString("TARIKH_TERIMA_PERINTAH")==null?"":rs.getDate("TARIKH_TERIMA_PERINTAH"));
//			    	h.put("tarikhTerimaPerintah_convert",rs.getDate("TARIKH_TERIMA_PERINTAH"));
			    	h.put("tarikh_perintah", rs.getString("TARIKH_PERINTAH")==null?"":sdf.format(rs.getDate("TARIKH_PERINTAH")));
			    	h.put("flag_pulang_deposit", rs.getString("FLAG_PULANG_DEPOSIT")==null?"":rs.getString("FLAG_PULANG_DEPOSIT"));
			    	h.put("keputusan_mahkamah", rs.getString("KEPUTUSAN_MAHKAMAH")==null?"":rs.getString("KEPUTUSAN_MAHKAMAH"));
			    	h.put("kos_pengapit_hakim", rs.getString("KOS_PENGAPIT_HAKIM")==null?"":Double.parseDouble(rs.getString("KOS_PENGAPIT_HAKIM")));
			    	h.put("kos_jpph", rs.getString("KOS_JPPH")==null?"":Double.parseDouble(rs.getString("KOS_JPPH")));
			    	h.put("nama_jpph", rs.getString("NAMA_JPPH")==null?"":rs.getString("NAMA_JPPH"));
			    	h.put("kos_swasta", rs.getString("KOS_SWASTA")==null?"":Double.parseDouble(rs.getString("KOS_SWASTA")));
			    	h.put("nama_swasta", rs.getString("NAMA_SWASTA")==null?"":rs.getString("NAMA_SWASTA"));
			    	h.put("syarikat_swasta", rs.getString("SYARIKAT_SWASTA")==null?"":rs.getString("SYARIKAT_SWASTA"));
			    	h.put("no_rujukan_tanah", rs.getString("NO_RUJUKAN_TANAH")==null?"":rs.getString("NO_RUJUKAN_TANAH"));
			    	getMaklumatSusulan.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getMaklumatSusulan;
	}
		
	@SuppressWarnings("unchecked")
	public Vector getMaklumatTarikBalik(String id_bantahan) throws Exception {	 							
			 Db db = null;
			 String sql = "";
			 try{
				 	getMaklumatTarikBalik = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT A.NO_RUJUKAN_SURATTARIKBALIK,A.TARIKH_TERIMA_TARIKBALIK, "; 
					sql += " A.TARIKH_SURAT_TARIKBALIK,A.FLAG_TARIKBALIK ";
					sql += " FROM TBLPPTBANTAHAN A WHERE A.ID_BANTAHAN = '"+ id_bantahan +"' ";
					
//					myLogger.info("SQL getMaklumatTarikBalik :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);					
					Hashtable h;			    
			     while (rs.next()) {
			    	h = new Hashtable();			    	
			    	h.put("no_rujukan_surattarikbalik", rs.getString("NO_RUJUKAN_SURATTARIKBALIK")==null?"":rs.getString("NO_RUJUKAN_SURATTARIKBALIK"));			    				    	
			    	h.put("tarikh_terima_tarikbalik", rs.getString("TARIKH_TERIMA_TARIKBALIK")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_TARIKBALIK")));
			    	h.put("tarikh_surat_tarikbalik", rs.getString("TARIKH_SURAT_TARIKBALIK")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_TARIKBALIK")));			    	
			    	h.put("flag_tarikbalik", rs.getString("FLAG_TARIKBALIK")==null?"":rs.getString("FLAG_TARIKBALIK"));
			    	getMaklumatTarikBalik.addElement(h);
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getMaklumatTarikBalik;			
	}
	
	@SuppressWarnings("unchecked")
	public Vector getMaklumatBatalMahkamah(String id_bantahan) throws Exception {	 							
		 Db db = null;
		 String sql = "";
		 try{
			    getMaklumatBatalMahkamah = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT A.NO_RUJUKAN_SURAT_BATALMAHKAMAH,A.TARIKH_TERIMA_BATALMAHKAMAH, "; 
				sql += " A.TARIKH_SURAT_BATALMAHKAMAH,A.FLAG_BATAL_MAHKAMAH,A.CATATAN_BATAL_MAHKAMAH ";
				sql += " FROM TBLPPTBANTAHAN A WHERE A.ID_BANTAHAN = '"+ id_bantahan +"' ";
				
//				myLogger.info("SQL getMaklumatBatalMahkamah :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);					
				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();			    	
		    	h.put("no_rujukan_surat_batalmahkamah", rs.getString("NO_RUJUKAN_SURAT_BATALMAHKAMAH")==null?"":rs.getString("NO_RUJUKAN_SURAT_BATALMAHKAMAH"));			    				    	
		    	h.put("tarikh_terima_batalmahkamah", rs.getString("TARIKH_TERIMA_BATALMAHKAMAH")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_BATALMAHKAMAH")));
		    	h.put("tarikh_surat_batalmahkamah", rs.getString("TARIKH_SURAT_BATALMAHKAMAH")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_BATALMAHKAMAH")));			    	
		    	h.put("flag_batal_mahkamah", rs.getString("FLAG_BATAL_MAHKAMAH")==null?"":rs.getString("FLAG_BATAL_MAHKAMAH"));
		    	h.put("catatan_batal_mahkamah", rs.getString("CATATAN_BATAL_MAHKAMAH")==null?"":rs.getString("CATATAN_BATAL_MAHKAMAH"));
		    	getMaklumatBatalMahkamah.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatBatalMahkamah;			
}	

	@SuppressWarnings("unchecked")
	public Vector view_details_dokumen(String id_dokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 try
		 {
			 view_details_dokumen = new Vector();
			 db = new Db();
			 Statement stmt = db.getStatement();
			 sql = " SELECT ID_DOKUMEN,TAJUK,KETERANGAN,NAMA_FAIL,CONTENT,JENIS_MIME,NO_RUJUKAN,ID_BANTAHAN FROM TBLPPTDOKUMEN "+ 
			 	   " WHERE ID_DOKUMEN = '"+ id_dokumen +"' ";		
//			 myLogger.info("view_details_dokumen ::"+sql);
			 ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int bil = 0;
		    
		      while (rs.next()) {
		    	  bil = bil + 1;
		    	  h = new Hashtable();			    	 
		    	  h.put("BIL", bil);
		    	  h.put("ID_BANTAHAN",rs.getString("ID_BANTAHAN")== null?"":rs.getString("ID_BANTAHAN"));
		    	  h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
		    	  h.put("NAMA_FAIL", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    	  h.put("JENIS_MIME",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
		    	  h.put("TAJUK",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
		    	  h.put("KETERANGAN",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
		          
		    	  view_details_dokumen.addElement(h);		    	 	    	
				}
		 	}
				finally{
					if(db != null)db.close();
				}	
			return view_details_dokumen;			
	}

	@SuppressWarnings("unchecked")
	public Vector senarai_dokumen_bantahan(String id_bantahan) throws Exception {				
	    Db db = null;
	    String sql = "";			  	    
	    try {
	      listDokumen_bantahan = new Vector();		
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql = " SELECT A.ID_BANTAHAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"+ 
				" A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTBANTAHAN P WHERE A.ID_BANTAHAN = '"+id_bantahan+"' "+
				" AND A.ID_BANTAHAN = P.ID_BANTAHAN";
	      
//	      myLogger.info("SQL DOKUMEN :"+sql.toUpperCase());
	      ResultSet rs = stmt.executeQuery(sql);
	     
	      Hashtable h;
	      int bil = 0;
	    
	      while (rs.next()) {
	    	
	    	  bil = bil + 1;
	    	  h = new Hashtable();			    	 
	    	  h.put("BIL", bil);
	    	  h.put("ID_BANTAHAN",rs.getString("ID_BANTAHAN")== null?"":rs.getString("ID_BANTAHAN"));
	    	  h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
	    	  h.put("NAMA_FAIL", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
	    	  h.put("JENIS_MIME",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
	    	  h.put("TAJUK",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
	    	  h.put("KETERANGAN",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));          
	    	  listDokumen_bantahan.addElement(h);
	      }		      
	      return listDokumen_bantahan;
	      
	    } finally {
	      if (db != null) db.close();
	    }	
	}

	@SuppressWarnings("unchecked")
	public Vector getSenaraiPB(String id_permohonan)throws Exception {		 							
		Db db = null;
		String sql = "";
		try{
				getSenaraiPB = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT B.ID_PERMOHONAN,C.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB,A.NO_PB,B.NO_LOT,B.NO_PT,B.ID_MUKIM,C.SYER_ATAS,C.SYER_BAWAH,C.JUMLAH_AWARD_PU,E.NAMA_MUKIM," +
						"F.STATUS_BANTAHAN,G.KETERANGAN,F.NO_BANTAHAN, "; 				
				sql += " CASE ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
				sql += " ELSE ''  ";
				sql += " END AS NO_LOTPT, F.FLAG_ONLINE "; 
				sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G, TBLPPTBORANGH H,TBLPPTBORANGG I, Tblrujlot lt ";
				sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) AND ";
				sql += " B.ID_MUKIM = E.ID_MUKIM AND H.ID_BORANGG = I.ID_BORANGG AND F.ID_HAKMILIK IS NULL AND H.ID_HAKMILIKPB = C.ID_HAKMILIKPB AND B.ID_LOT = lt.id_lot(+) AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";		
				sql += " ORDER BY B.NO_LOT,F.STATUS_BANTAHAN ";
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getSenaraiPB :: "+sql);
				Hashtable h;
				int bil = 1;
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
		    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
		    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
		    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
		    	h.put("syer_atas", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
		    	h.put("syer_bawah", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH")); 	
		    	h.put("jumlah_award_pu", rs.getString("JUMLAH_AWARD_PU")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD_PU")));	
//		    	h.put("jumlah_award_pu", rs.getString("JUMLAH_AWARD_PU")==null?"":rs.getDouble("JUMLAH_AWARD_PU"));	
		    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));		    	
		    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	h.put("no_bantahan", rs.getString("NO_BANTAHAN")==null?"":rs.getString("NO_BANTAHAN"));
		    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
		    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
		    	getSenaraiPB.addElement(h);
		    	bil++;
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getSenaraiPB;
}
	
	public int getSenaraiPB_count(String id_permohonan)throws Exception {		 							
		Db db = null;
		String sql = "";
		try{
				getSenaraiPB = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT count(*) as total FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G, TBLPPTBORANGH H,TBLPPTBORANGG I, Tblrujlot lt ";
				sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) AND ";
				sql += " B.ID_MUKIM = E.ID_MUKIM AND H.ID_BORANGG = I.ID_BORANGG AND F.ID_HAKMILIK IS NULL AND H.ID_HAKMILIKPB = C.ID_HAKMILIKPB AND B.ID_LOT = lt.id_lot(+) AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";		
				sql += " ORDER BY B.NO_LOT,F.STATUS_BANTAHAN ";
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getSenaraiPB :: "+sql);
				Hashtable h;
				int total = 0;
		     while (rs.next()) {
		    	 total = rs.getInt("total");
		      	}   
		     return total;
			}
				finally{
					if(db != null)db.close();
				}	
			
}	


	@SuppressWarnings("unchecked")
	public Vector setCarianPB(String id_permohonan,String carianNamaPB,String carianNoLot) throws Exception{
		
		Db db = null;
		String sql = "";
		carianNamaPB = carianNamaPB.trim();	
		carianNoLot = carianNoLot.trim();
		try {
				listCarianPB = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT DISTINCT B.ID_PERMOHONAN,C.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB," +
						"A.NO_PB,B.NO_LOT,B.NO_PT,B.ID_MUKIM,C.SYER_ATAS,C.SYER_BAWAH,C.JUMLAH_AWARD_PU,E.NAMA_MUKIM,F.STATUS_BANTAHAN,G.KETERANGAN,F.NO_BANTAHAN, "; 				
				sql += " CASE ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
				sql += " ELSE ''  ";
				sql += " END AS NO_LOTPT, F.FLAG_ONLINE "; 
				sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G, TBLPPTBORANGH H,TBLPPTBORANGG I, Tblrujlot lt ";
				sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) AND ";
				sql += " B.ID_MUKIM = E.ID_MUKIM AND H.ID_BORANGG = I.ID_BORANGG AND F.ID_HAKMILIK IS NULL AND H.ID_HAKMILIKPB = C.ID_HAKMILIKPB AND B.ID_LOT = lt.id_lot(+) AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";		
				
				/*
				sql =  " SELECT DISTINCT B.ID_PERMOHONAN, B.ID_HAKMILIK, C.ID_HAKMILIKPB, A.ID_PIHAKBERKEPENTINGAN, A.NAMA_PB, A.NO_PB, B.NO_LOT, ";				
				sql += " B.NO_PT, B.ID_MUKIM, C.SYER_ATAS, C.SYER_BAWAH, C.JUMLAH_AWARD_PU,E.NAMA_MUKIM,F.STATUS_BANTAHAN,G.KETERANGAN, ";
				sql += " CASE ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN lt.keterangan || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
				sql += " ELSE ''  ";
				sql += " END AS NO_LOTPT  "; 				
				sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLPPTHAKMILIK B,TBLPPTHAKMILIKPB C,TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G, Tblrujlot lt ";
				sql += " WHERE C.ID_HAKMILIK = b.ID_HAKMILIK ";
				sql += " AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) "; 
				sql += " AND B.ID_MUKIM = E.ID_MUKIM AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";
				*/
				//NAMA PB
				if (carianNamaPB != "" && carianNamaPB != null) {
					if (!carianNamaPB.equals("")) {
						sql += " AND UPPER(A.NAMA_PB) LIKE '%" + carianNamaPB.toUpperCase().trim() + "%'";
					}
				}
				
				//NO LOT
				if (carianNoLot != "" && carianNoLot != null) {
					if (!carianNoLot.equals("")) {
						sql += " AND UPPER(B.NO_LOT) LIKE '%" + carianNoLot.toUpperCase() + "%'";
					}
				}				
			
				sql += " ORDER BY A.NAMA_PB ASC";						
//				myLogger.info("SQL CARIAN :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				while (rs.next()){
					h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
			    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
			    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
			    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
			    	h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
			    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
			    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
			    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
			    	h.put("syer_atas", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
			    	h.put("syer_bawah", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH")); 	
			    	h.put("jumlah_award_pu", rs.getString("JUMLAH_AWARD_PU")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD_PU")));			    	
			    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
			    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
			    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));		
			    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
			    	listCarianPB.addElement(h);
					bil++;	
				}
				return listCarianPB;
				
		}finally {
			if(db != null) db.close();
		}
	}
	

	
	 @SuppressWarnings("unchecked")
	public Vector getTarikhPenting(String id_hakmilikpb)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 try{
				getTarikhPenting = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT A.TARIKH_SEDIA_AWARD, C.TARIKH_BORANGH, C.TARIKH_BORANGG "; 
				sql += " FROM TBLPPTAWARD A, TBLPPTBORANGH B, TBLPPTBORANGG C ";
				sql += " WHERE A.ID_HAKMILIKPB = B.ID_HAKMILIKPB AND B.ID_BORANGG = C.ID_BORANGG ";
				sql += " AND A.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";				
//				myLogger.info("getTarikhPenting :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
				
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		    	h.put("tarikh_sedia_award", rs.getString("TARIKH_SEDIA_AWARD")==null?"":sdf.format(rs.getDate("TARIKH_SEDIA_AWARD")));		    	
		    	h.put("tarikh_borangh", rs.getString("TARIKH_BORANGH")==null?"":sdf.format(rs.getDate("TARIKH_BORANGH")));
		    	h.put("tarikh_borangg", rs.getString("TARIKH_BORANGG")==null?"":sdf.format(rs.getDate("TARIKH_BORANGG")));
		    	getTarikhPenting.addElement(h);		    	
		     	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getTarikhPenting;					
	 }

	 @SuppressWarnings("unchecked")
	public Vector getNoSiasatan(String id_permohonan,String id_hakmilik)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 try{
			 	getNoSiasatan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT NO_SIASATAN,ID_SIASATAN FROM TBLPPTSIASATAN A WHERE ID_PERMOHONAN = '"+id_permohonan+"' "; 
				sql += " AND ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN WHERE ID_PERMOHONAN = A.ID_PERMOHONAN AND ID_HAKMILIK = '"+id_hakmilik+"') ";
//				myLogger.info("getNoSiasatan :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		    	h.put("no_siasatan", rs.getString("NO_SIASATAN")==null?"":rs.getString("NO_SIASATAN"));		    	
		    	h.put("id_siasatan", rs.getString("ID_SIASATAN")==null?"":rs.getString("ID_SIASATAN"));
		    	getNoSiasatan.addElement(h);		    	
		     	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getNoSiasatan;					
	 }
	
	 public Vector getNoWarta(String id_permohonan)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 try{
			 	getNoWarta = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT ID_WARTA,NO_WARTA FROM TBLPPTWARTA A WHERE ID_PERMOHONAN = '"+id_permohonan+"' "; 
				sql += " AND ID_WARTA = (SELECT MAX(ID_WARTA) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = A.ID_PERMOHONAN) ";
//				myLogger.info("getNoWarta :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		    	h.put("id_warta", rs.getString("ID_WARTA")==null?"":rs.getString("ID_WARTA"));		    	
		    	h.put("no_warta", rs.getString("NO_WARTA")==null?"":rs.getString("NO_WARTA"));
		    	getNoWarta.addElement(h);		    	
		     	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getNoWarta;					
	 }	 
	 
	 public Vector getMaklumatChecking(String id_permohonan,String maxIdSiasatan, String id_hakmilikpb)throws Exception {		 		 
		 Db db = null;
		 String sql = "";
		 try{
			 	getMaklumatChecking = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT C.ID_HAKMILIKPB,D.NAMA_PB,F.FLAG_HADIR,G.TARIKH_SEDIA_AWARD,I.TARIKH_BORANGH AS TARIKH_TERIMABORANGH,I.TARIKH_BORANGG "; 
				sql += " FROM TBLPPTPERMOHONAN A, ";
				sql += " TBLPPTHAKMILIK B, ";
				sql += " TBLPPTHAKMILIKPB C, ";
				sql += " TBLPPTPIHAKBERKEPENTINGAN D, ";
				sql += " TBLPPTSIASATAN E, ";
				//sql += " (SELECT * FROM (SELECT JMAX.*, MAX(JMAX.TARIKH_SIASATAN) OVER (PARTITION BY JMAX.ID_HAKMILIK) MAX_ID FROM TBLPPTSIASATAN JMAX) WHERE TARIKH_SIASATAN = MAX_ID) E, ";
				sql += " TBLPPTKEHADIRAN F, ";
				sql += " TBLPPTAWARD G, ";
				sql += "  ";
				sql += " TBLPPTBORANGH H, TBLPPTBORANGG I ";
				sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_HAKMILIK = C.ID_HAKMILIK ";
				sql += " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN AND A.ID_PERMOHONAN = E.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND C.ID_HAKMILIKPB = G.ID_HAKMILIKPB(+) ";
				sql += " AND C.ID_HAKMILIKPB = H.ID_HAKMILIKPB(+) AND I.ID_SIASATAN = E.ID_SIASATAN AND C.ID_JENISPB NOT IN (40,41,42) ";
				sql += " AND A.ID_PERMOHONAN = '"+id_permohonan+"' ";
				sql +=	" AND E.ID_SIASATAN = '"+maxIdSiasatan+"' ";
				sql += " AND C.ID_HAKMILIKPB = '"+id_hakmilikpb+"' " +
						" ";
				myLogger.info("getMaklumatChecking HADIR BICARA :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;	
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));	
		    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	h.put("flag_hadir", rs.getString("FLAG_HADIR")==null?"":rs.getString("FLAG_HADIR"));	
		    	h.put("tarikh_sedia_award", rs.getString("TARIKH_SEDIA_AWARD")==null?"":sdf.format(rs.getDate("TARIKH_SEDIA_AWARD")));
//		    	h.put("tarikh_terimaborangh", rs.getString("TARIKH_TERIMABORANGH")==null?"":sdf.format(rs.getDate("TARIKH_TERIMABORANGH")));
		    	h.put("tarikh_terimaborangh",rs.getDate("TARIKH_TERIMABORANGH"));
//		    	h.put("tarikh_borangg", rs.getString("TARIKH_BORANGG")==null?"":sdf.format(rs.getDate("TARIKH_BORANGG")));
		    	h.put("tarikh_borangg",rs.getDate("TARIKH_BORANGG"));
		    	getMaklumatChecking.addElement(h);		    	
		     	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatChecking;					
	 }

	public static Hashtable getFlagBantahan(String id_hakmilikpb) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT FLAG_BANTAHAN FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";					     
//	      myLogger.info("SQL getFlagBantahan :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}

	public Vector getCheckingBayaranPampasan(String id_hakmilikpb) throws Exception {		 							
		 Db db = null;
		 String sql = "";
		try{
				getCheckingBayaranPampasan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT A.BAYAR_PAMPASAN FROM TBLPPTAWARD A WHERE A.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";
				ResultSet rs = stmt.executeQuery(sql);
//				myLogger.info("SQL getCheckingBayaranPampasan :: "+sql);
				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
//		    	h.put("amaun_bayaran", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));		    			    	
		    	
				if(rs.getString("BAYAR_PAMPASAN") == null){
		    		h.put("amaun_bayaran","0");
		    	}else{
		    		h.put("amaun_bayaran", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));
		    	}

		    	getCheckingBayaranPampasan.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getCheckingBayaranPampasan;
	}
	
	public static String checkingDateDiffBG(String tarikh_borangg) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =" SELECT ROUND(SYSDATE-to_date('"+ tarikh_borangg +"','dd-MM-yyyy')) AS DIFFDATE from dual  ";					     
//		      myLogger.info("SQL checkingDateDiffBG :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      
		      String  tt = "0";
		      if (rs.next()) {			    	
		    	  if(rs.getString("DIFFDATE")!= null){
		    		tt =   rs.getString("DIFFDATE");		    		
		    	  }			    	
		      }		      
		      //myLogger.info("hhhhh"+tt);		      
		      return tt;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
	}

	public static String checkingDateDiffBH(String tarikh_terimaborangh) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =" SELECT ROUND(SYSDATE-to_date('"+ tarikh_terimaborangh +"','dd-MM-yyyy')) AS DIFFDATE from dual  ";					     
//		      myLogger.info("SQL checkingDateDiffBH :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      
		      String  borangH = "0";
		      if (rs.next()) {			    	
		    	  if(rs.getString("DIFFDATE")!= null){
		    		  borangH =   rs.getString("DIFFDATE");		    		
		    	  }			    	
		      }		      
//		      myLogger.info("borangH :: "+borangH);		      
		      return borangH;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
	}
	
	public static String checking6BulanBorangO(String tarikhBorangO) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =" SELECT ROUND(SYSDATE-to_date('"+ tarikhBorangO +"','dd-MM-yyyy')) AS DIFFDATE from dual  ";					     
//		      myLogger.info("SQL 6BULAN SLPS TERIMA BORANG N :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      
		      String  tt = "0";
		      if (rs.next()) {			    	
		    	  if(rs.getString("DIFFDATE")!= null){
		    		tt =   rs.getString("DIFFDATE");		    		
		    	  }			    	
		      }		      
		      //myLogger.info("hhhhh"+tt);		      
		      return tt;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
	}	

	public static String checkingDateTerimaPerintah(String tarikh_terimaPerintah) throws Exception { 
	    Db db = null;
	    String sql = "";
	    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		      sql =" SELECT ROUND(SYSDATE-to_date('"+ tarikh_terimaPerintah +"','dd-MM-yyyy')) AS DIFFDATE from dual  ";					     
//		      myLogger.info("SQL checkingDateDiffBG :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      
		      String  tt = "0";
		      if (rs.next()) {			    	
		    	  if(rs.getString("DIFFDATE")!= null){
		    		tt =   rs.getString("DIFFDATE");		    		
		    	  }			    	
		      }		      
		      //myLogger.info("hhhhh"+tt);		      
		      return tt;
		    }
		    finally {
		      if (db != null) db.close();
		    }	
	}
	

	public void setListMaklumatTanah(String id_permohonan,String usid,String lot) throws Exception{
		
		 listMaklumatTanah = new Vector();
			
			Db db = null;
			listMaklumatTanah.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql =  " SELECT C.ID_FAIL,D.ID_PERMOHONAN,E.ID_HAKMILIK,E.NO_HAKMILIK,G.ID_HAKMILIKPB,H.NAMA_MUKIM,E.LUAS_AMBIL,E.ID_UNITLUASLOT_CONVERT,I.KETERANGAN, ";
					sql += " CASE ";
					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NULL THEN E.NO_LOT ";
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT "; 
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT  ";
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT  ";       
					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT || CHR(32) || CHR(40) || E.NO_LOT || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT, BST.KETERANGAN AS STATUS_BANTAHAN ";
					sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C, "; 
					sql += " TBLPPTPERMOHONAN D,TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F, ";
					sql += " TBLPPTHAKMILIKPB G,TBLRUJKEMENTERIAN H,TBLRUJSUBURUSAN SU, ";       
					sql += " TBLRUJSTATUS ST,TBLRUJLOT LT,TBLRUJLUAS I,TBLRUJMUKIM H,TBLPPTBANTAHAN BTH, TBLRUJSTATUS BST, TBLPPTAWARD AW, TBLPPTBORANGH BH ";
					sql += " WHERE A.USER_ID = B.USER_ID AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
					sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
					sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND B.NO_KP_BARU = F.NO_PB(+) AND D.ID_STATUS = ST.ID_STATUS ";       
					sql += " AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
					sql += " AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";					
					sql += " AND E.ID_LOT = LT.ID_LOT(+) AND I.ID_LUAS(+) = E.ID_UNITLUASAMBIL   "; 
					sql += " AND H.ID_MUKIM(+) = E.ID_MUKIM AND su.id_suburusan = '52' ";
					sql += " AND BTH.ID_HAKMILIKPB(+) = G.ID_HAKMILIKPB ";
					sql += " AND BTH.STATUS_BANTAHAN = BST.ID_STATUS(+) ";
					sql += " AND AW.ID_HAKMILIKPB = G.ID_HAKMILIKPB ";
					sql += " AND BH.ID_HAKMILIKPB = G.ID_HAKMILIKPB ";
					sql += " AND A.USER_ID = '"+ usid +"' ";	
					sql += " AND D.ID_PERMOHONAN = '"+ id_permohonan +"' ";	
	
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(E.NO_LOT) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(E.NO_PT) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(LT.KETERANGAN) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot										
					
					sql += " ORDER BY NO_LOTPT ASC, H.NAMA_MUKIM asc";
					//myLogger.info("SQL HAKMILIK BANTAHAN PB :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"BELUM DIDAFTAR":rs.getString("STATUS_BANTAHAN"));
						h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
						h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
						h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
						h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));						
						h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
						if(rs.getString("LUAS_AMBIL") != null && rs.getString("LUAS_AMBIL") != ""){
							
							double luasAmbil = rs.getDouble("LUAS_AMBIL");
							String LA = Utils.formatLuasHM(luasAmbil);
							h.put("luas_ambil",LA);
									
						}else{
							h.put("luas_ambil","0");
						}
						
						if(rs.getString("ID_UNITLUASLOT_CONVERT") != null){
							
							if(rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")){
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

	  public int totalFail(String userIdNegeri)  throws Exception {

		  	Db db = null; 
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try { 
				//Open the database connection
				db = new Db(); 

			      sql = " SELECT COUNT(DISTINCT P.ID_PERMOHONAN) ";
			      sql +=" FROM tblpptpermohonan p,tblpfdfail f,tblrujsuburusan su,tblrujstatus s,tblrujkementerian k,tblppthakmilik l ";
			      sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
			      sql +=" AND f.id_kementerian = k.id_kementerian AND su.id_suburusan = '52' ";
			      sql +=" AND ( ";
			      sql +=" SELECT DISTINCT P1.ID_PERMOHONAN FROM TBLPPTBORANGH H,TBLPPTPERMOHONAN P1,TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB ";	
			      sql +=" WHERE PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
			      sql +=" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HM.ID_PERMOHONAN = P1.ID_PERMOHONAN ";
			      sql +=" AND H.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB AND P1.ID_PERMOHONAN = p.id_permohonan ";
			      sql +=" ) > 0  ";
			      sql +=" AND p.id_status = s.id_status AND p.id_permohonan = l.id_permohonan ";
			      sql +=" AND NVL (l.flag_penarikan_keseluruhan, ' ') <> 'Y'  ";
			      sql +=" AND NVL (l.flag_pembatalan_keseluruhan, ' ') <> 'Y' ";
			      sql +=" AND P.ID_STATUS NOT IN (199,200,201,203,204,205,1610249) ";
			      
		    		// ADDED BY ELLY 14 JUNE 2010
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    		}				
				  
				rs = db.getStatement().executeQuery(sql); 
//				System.out.println("SQL MY INFO TOTAL FAIL (B) :: "+sql);
			if ( rs.next() ) { 
				total = rs.getInt(1); 
			} 
			} finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			if (rs != null) rs.close();			
			} 
			return total;
	  }


	
	
	
}
