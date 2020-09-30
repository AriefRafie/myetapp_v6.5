package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class BantahanPampasan {
	 static Logger myLogger = Logger.getLogger(BantahanPampasan.class);
	 private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	
	 
	// private Vector list = null;
	 public  Vector listCarian = null;
	// public  Vector getListPemohon = null;
	 private Vector header = null;
	 private Vector getSenaraiPB = null;
	 private Vector getMaklumatPB = null;
	 private Vector getMaklumatTerimaCek = null;
	 private Vector getMaklumatSerahCek = null;
	 private Vector getMaklumatViaEFT = null;
	 public  Vector listCarianPB = null;
	 private Vector getSenaraiAP = null;
	 private Vector listCarianAP = null;
	 private Vector getMaklumatAP = null;
	 private Vector getMaklumatTkhH = null;
	 
	 public Vector getListCarian(String userIdNegeri){
			return listCarian;
	 }

	/* @SuppressWarnings("unchecked")
	 public Vector getListPemohon(String userIdNegeri)throws Exception {		 
		    Db db = null;
		    String sql = "";
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  
		    try{
		     
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = " SELECT DISTINCT P.ID_STATUS, P.ID_PERMOHONAN, P.NO_PERMOHONAN, F.ID_FAIL,F.NO_FAIL, P.TARIKH_PERMOHONAN, SU.NAMA_SUBURUSAN,K.NAMA_KEMENTERIAN, S.KETERANGAN, P.NO_RUJUKAN_UPT, P.no_rujukan_ptg ";
		      sql +=" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S,TBLRUJKEMENTERIAN K ";
		      sql +=" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
		      sql +=" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN ";
		      sql +=" AND P.ID_STATUS = S.ID_STATUS AND ( P.ID_STATUS = 184 OR P.ID_STATUS = 186 OR P.ID_STATUS = 187  OR P.ID_STATUS = 199 OR P.ID_STATUS = 200 OR P.ID_STATUS = 201 OR P.ID_STATUS = 202 OR P.ID_STATUS = 203 OR P.ID_STATUS = 204 OR P.ID_STATUS = 205 ) ";
		      
	    		// ADDED BY ELLY 14 JUNE 2010
		      if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
		      
		      sql +=" ORDER BY P.TARIKH_PERMOHONAN DESC, P.NO_PERMOHONAN DESC ";		      
		      myLogger.info("SQL :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		      int bil = 1;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
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
		    	
		    	  myLogger.info("no_permohonan :"+h.put("no_permohonan", rs.getString("no_permohonan")));
		    	  list.addElement(h);
		    	  bil++;
		    	  
		    	 
		    	  
		      }
		      return list;
		    }
		    finally{
		      if (db != null) db.close();
		    }
		 }

	 */
	 
	 
		@SuppressWarnings("unchecked")
		public static Vector getListPemohon(String userIdNegeri)throws Exception {
		
			    Db db = null;
			    String sql = "";
			    
			    try{
			    	
			    		db = new Db();
			    		Statement stmt = db.getStatement();
			     
			    		  sql = " SELECT DISTINCT P.ID_STATUS, P.ID_PERMOHONAN, P.NO_PERMOHONAN, F.ID_FAIL,F.NO_FAIL, P.TARIKH_PERMOHONAN, SU.NAMA_SUBURUSAN,K.NAMA_KEMENTERIAN, S.KETERANGAN, P.NO_RUJUKAN_UPT, P.no_rujukan_ptg ";
					      sql +=" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S,TBLRUJKEMENTERIAN K ";
					      sql +=" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
					      sql +=" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN ";
					      sql +=" AND P.ID_STATUS = S.ID_STATUS AND ( P.ID_STATUS = 184 OR P.ID_STATUS = 186 OR P.ID_STATUS = 187  OR P.ID_STATUS = 199 OR P.ID_STATUS = 200 OR P.ID_STATUS = 201 OR P.ID_STATUS = 202 OR P.ID_STATUS = 203 OR P.ID_STATUS = 204 OR P.ID_STATUS = 205 ) ";
					      
				    		// ADDED BY ELLY 14 JUNE 2010
					      if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
				    			if(userIdNegeri.equals("14")){
				    				sql += "AND f.id_negeri in (14,15,16) ";
				    			}else{
				    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
				    			}		
				    		}
					      
					      sql +=" ORDER BY P.TARIKH_PERMOHONAN DESC, P.NO_PERMOHONAN DESC ";		      
					      myLogger.info("SQL :: "+sql);
					      ResultSet rs = stmt.executeQuery(sql);
			    		
			    		Vector list = new Vector();
			      
			    		Hashtable h = null;
			    		int bil = 1;

			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
					    	  h.put("id_fail", rs.getString("id_fail"));
					    	  h.put("id_status", rs.getString("id_status"));
					    	  h.put("id_permohonan", rs.getString("id_permohonan"));
					    	  h.put("no_permohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
					    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":sdf.format(rs.getDate("tarikh_permohonan")));
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
			    			
			    			list.addElement(h);
			    			bil++;
			    	  
			    		}//close while
			    		return list;
			    	}//close try 
			    	finally{
			    		if (db != null) db.close();
			    	}//close finally
			    	
		}//close getListBantahanStandAlone
		
		
		public Vector setCarianFail(String usid, String txtNoFail, String socKementerian, String userIdNegeri)throws Exception {
		    Db db = null;
		    String sql = "";
		    String nofail = "";
		    
		    try {
		      listCarian = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      nofail = txtNoFail.trim();      
		  
			  //SYARAT
		      sql = " SELECT DISTINCT P.ID_STATUS, P.ID_PERMOHONAN, P.NO_PERMOHONAN, F.ID_FAIL,F.NO_FAIL, P.TARIKH_PERMOHONAN, SU.NAMA_SUBURUSAN,K.NAMA_KEMENTERIAN, S.KETERANGAN, P.NO_RUJUKAN_UPT, P.no_rujukan_ptg ";
		      sql +=" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S,TBLRUJKEMENTERIAN K ";
		      sql +=" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
		      sql +=" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN ";
		      sql +=" AND P.ID_STATUS = S.ID_STATUS AND ( P.ID_STATUS = 184 OR P.ID_STATUS = 186 OR P.ID_STATUS = 187  OR P.ID_STATUS = 199 OR P.ID_STATUS = 200 OR P.ID_STATUS = 201 OR P.ID_STATUS = 202 OR P.ID_STATUS = 203 OR P.ID_STATUS = 204 OR P.ID_STATUS = 205 ) ";
		      
	    		// ADDED BY ELLY 14 JUNE 2010
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    		}
		     
				//NO FAIL
				if (txtNoFail != "" && txtNoFail != null) {
					if (!nofail.equals("")) {
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
	  
				//SORTING
				sql +=" ORDER BY P.ID_STATUS ASC ";
				ResultSet rs = stmt.executeQuery(sql);
	  
				Hashtable h;
				int bil = 1;
				while (rs.next()) {					
				  h = new Hashtable();		  
		    	  h.put("bil", bil);
		    	  h.put("id_fail", rs.getString("id_fail"));
		    	  h.put("id_status", rs.getString("id_status"));
		    	  h.put("id_permohonan", rs.getString("id_permohonan"));
		    	  h.put("no_permohonan", rs.getString("no_permohonan"));
		    	  h.put("tarikh_permohonan", rs.getString("tarikh_permohonan")==null?"-":sdf.format(rs.getDate("tarikh_permohonan")));
		    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));		    	  
		    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
		    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
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
				sql += " WHERE F.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND F.ID_FAIL = P.ID_FAIL AND B.ID_DAERAH = P.ID_DAERAH ";
				sql += " AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND P.ID_STATUS = S.ID_STATUS ";
				sql += " AND P.ID_NEGERI = C.ID_NEGERI AND P.ID_AGENSI = AG.ID_AGENSI(+) AND P.ID_FAIL = '"+ id_fail +"' AND P.ID_PERMOHONAN = '"+ id_permohonan +"' ";
				myLogger.info("SQL getHeader :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			    
		     while (rs.next()) {
		    	h = new Hashtable();		    	 
		        h.put("id_permohonan", rs.getString("id_permohonan")==null?"-":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")==null?"-":rs.getString("no_permohonan"));
		    	h.put("id_fail", rs.getString("id_fail")==null?"-":rs.getString("id_fail"));
		    	h.put("id_status", rs.getString("id_status"));
		    	h.put("no_fail", rs.getString("no_fail")==null?"-":rs.getString("no_fail"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"-":rs.getString("nama_kementerian"));
		    	h.put("nama_daerah", rs.getString("nama_daerah")==null?"-":rs.getString("nama_daerah"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"-":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"-":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"-":rs.getString("no_rujukan_upt"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"-":rs.getString("nama_suburusan"));
		    	h.put("keterangan", rs.getString("keterangan")==null?"-":rs.getString("keterangan"));
		    	h.put("nama_agensi", rs.getString("nama_agensi")==null?"-":rs.getString("nama_agensi"));
		    	h.put("projek_negeri", rs.getString("nama_negeri")==null?"-":rs.getString("nama_negeri"));
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

		public Vector getSenaraiPB(String id_permohonan)throws Exception {		 							
			Db db = null;
			String sql = "";
			try{
					getSenaraiPB = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT DISTINCT B.ID_PERMOHONAN,C.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB,A.NO_PB,B.NO_LOT, "; 
					sql += " B.NO_PT,B.ID_MUKIM,C.SYER_ATAS,C.SYER_BAWAH,H.BAYAR_PAMPASAN,E.NAMA_MUKIM,F.STATUS_BANTAHAN,G.KETERANGAN,F.ID_BANTAHAN,C.FLAG_BAYAR_BANTAHAN,F.AMAUN_AWARD , "; 
					sql += " I.TARIKH_BORANGH ";
					sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G, TBLPPTAWARD H, TBLPPTBORANGG I ";
					sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";				
					sql += " AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) AND ";
					sql += " B.ID_MUKIM = E.ID_MUKIM AND C.ID_HAKMILIKPB = H.ID_HAKMILIKPB AND H.ID_SIASATAN = I.ID_SIASATAN AND C.FLAG_BANTAHAN = '1' AND ( F.STATUS_BANTAHAN='184' OR F.STATUS_BANTAHAN = '186' OR F.STATUS_BANTAHAN = '187' ) AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";
					 
					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL getSenaraiPB PAMPASAN :: "+sql);
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
			    	h.put("no_pb", rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
			    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
			    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
			    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
			    	h.put("syer_atas", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
			    	h.put("syer_bawah", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH")); 	
			    	h.put("bayar_pampasan", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));			    	
			    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));		    	
			    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
			    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
			    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
			    	h.put("amaun_award", rs.getString("AMAUN_AWARD")==null?"":Double.parseDouble(rs.getString("AMAUN_AWARD")));
			    	h.put("tarikh_borangh", rs.getString("TARIKH_BORANGH")==null?"":sdf.format(rs.getDate("TARIKH_BORANGH")));
			    	getSenaraiPB.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getSenaraiPB;
	}	 
		
		public Vector getSenaraiAP(String id_permohonan)throws Exception {		 							
			Db db = null;
			String sql = "";
			try{
					getSenaraiAP = new Vector();
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();				
					sql =  " SELECT B.ID_PERMOHONAN,C.ID_HAKMILIK,D.ID_AGENSI,E.NAMA_AGENSI,D.STATUS_BANTAHAN_AP, "; 
					sql += " F.KETERANGAN,C.JUMLAH_AWARD,D.ID_BANTAHAN,C.FLAG_BAYAR_BANTAHAN,C.NO_HAKMILIK,D.AMAUN_AWARD ";
					sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
					sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";				
					sql += " TBLRUJAGENSI E,TBLRUJSTATUS F ";
					sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
					sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_HAKMILIK = D.ID_HAKMILIK "; 
					sql += " AND D.ID_AGENSI = E.ID_AGENSI AND D.STATUS_BANTAHAN_AP = F.ID_STATUS ";
					sql += " AND C.FLAG_BANTAHAN = '1' ";
					sql += " AND D.STATUS_BANTAHAN_AP = '201' AND B.ID_PERMOHONAN = '"+id_permohonan+"'  ";

					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL getSenaraiAP PAMPASAN :: "+sql);
					Hashtable h;
					int bil = 1;
			     while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
			    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
			    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"":rs.getString("ID_AGENSI"));
			    	h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"":rs.getString("NAMA_AGENSI"));
			    	h.put("status_bantahan_ap", rs.getString("STATUS_BANTAHAN_AP")==null?"":rs.getString("STATUS_BANTAHAN_AP"));
			    	h.put("keteranganStatusBantahanAp", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
			    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
			    	h.put("jumlah_award", rs.getString("JUMLAH_AWARD")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD")));
			    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
			    	h.put("amaun_award", rs.getString("AMAUN_AWARD")==null?"":Double.parseDouble(rs.getString("AMAUN_AWARD")));
			    	getSenaraiAP.addElement(h);
			    	bil++;
			      	}      
				}
					finally{
						if(db != null)db.close();
					}	
				return getSenaraiAP;
	}	 		
	 
	public Vector getMaklumatPB(String id_hakmilikpb)throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatPB = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN, A.NAMA_PB,A.NO_PB,B.NO_LOT,B.NO_PT,B.ID_MUKIM,C.SYER_ATAS,C.SYER_BAWAH, "; 
				sql += " G.BAYAR_PAMPASAN,E.NAMA_MUKIM,G.LUAS_AMBIL,G.ID_UNITLUAS_AMBIL,F.KETERANGAN,C.JUMLAH_AWARD_BANTAHAN,C.FLAG_BAYAR_BANTAHAN,C.ID_HAKMILIKPB,D.STATUS_BANTAHAN,D.ID_BANTAHAN ";
				sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A,TBLPPTHAKMILIK B,TBLPPTHAKMILIKPB C,TBLPPTBANTAHAN D,TBLRUJMUKIM E,TBLRUJLUAS F,TBLPPTAWARD G ";
				sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
				sql += " AND D.ID_HAKMILIKPB = C.ID_HAKMILIKPB AND C.FLAG_BANTAHAN = '1' AND B.ID_MUKIM = E.ID_MUKIM AND C.ID_HAKMILIKPB = G.ID_HAKMILIKPB ";
				sql += " AND G.ID_UNITLUAS_AMBIL = F.ID_LUAS(+) AND C.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumatPB :: "+sql);
				Hashtable h;
				
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"-":rs.getString("NAMA_PB"));
		    	h.put("no_pb", rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
		    	h.put("no_lot", rs.getString("NO_LOT")==null?"-":rs.getString("NO_LOT"));
		    	h.put("no_pt", rs.getString("NO_PT")==null?"-":rs.getString("NO_PT"));
		    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
		    	h.put("syer_atas", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
		    	h.put("syer_bawah", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH"));
		    	h.put("jumlah_award_pu", rs.getString("BAYAR_PAMPASAN")==null?"":Double.parseDouble(rs.getString("BAYAR_PAMPASAN")));
		    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));			    			
		    	h.put("luas_ambil", rs.getString("LUAS_AMBIL")==null?"":rs.getString("LUAS_AMBIL"));
		    	h.put("id_unitluasambil", rs.getString("ID_UNITLUAS_AMBIL")==null?"":rs.getString("ID_UNITLUAS_AMBIL"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	h.put("jumlah_award_bantahan", rs.getString("JUMLAH_AWARD_BANTAHAN")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD_BANTAHAN")));
		    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
		    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
		    	getMaklumatPB.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatPB;
	}

	public Vector getMaklumatTerimaCek(String id_bayaran) throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatTerimaCek = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT A.ID_BAYARAN,A.NO_BAYARAN,A.AMAUN_BAYARAN,A.TARIKH_TERIMA,A.TARIKH_CEK,A.JENIS_AWARD, "; 
				sql += " A.NO_PB,A.ID_PIHAKBERKEPENTINGAN,A.FLAG_SERAH_CEK,A.TARIKH_AKHIR_CEK,A.CARA_BAYAR,A.TARIKH_AMBIL_CEK,A.MASA_AMBIL_CEK ";
				sql += " FROM TBLPPTBAYARAN A WHERE A.ID_BAYARAN = "+ id_bayaran +" ";				
											
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumatTerimaCek :: "+sql);
				Hashtable h;
	
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	h.put("no_bayaran", rs.getString("NO_BAYARAN")==null?"":rs.getString("NO_BAYARAN"));
		    	h.put("amaun_bayaran", rs.getString("AMAUN_BAYARAN")==null?"":Double.parseDouble(rs.getString("AMAUN_BAYARAN")));
		    	h.put("tarikh_terima", rs.getString("TARIKH_TERIMA")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA")));
		    	h.put("tarikh_cek", rs.getString("TARIKH_CEK")==null?"":sdf.format(rs.getDate("TARIKH_CEK")));
		    	h.put("jenis_award", rs.getString("JENIS_AWARD")==null?"":rs.getString("JENIS_AWARD"));
		    	h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("flag_serah_cek", rs.getString("FLAG_SERAH_CEK")==null?"":rs.getString("FLAG_SERAH_CEK"));
		    	h.put("tarikh_akhir_cek", rs.getString("TARIKH_AKHIR_CEK")==null?"":sdf.format(rs.getDate("TARIKH_AKHIR_CEK")));
		    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
		    	h.put("tarikh_ambil_cek", rs.getString("TARIKH_AMBIL_CEK")==null?"":sdf.format(rs.getDate("TARIKH_AMBIL_CEK")));
		    	h.put("masa_ambil_cek", rs.getString("MASA_AMBIL_CEK")==null?"":rs.getString("MASA_AMBIL_CEK"));
		    	getMaklumatTerimaCek.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatTerimaCek;				
	}

	public Hashtable getIdBayaran(String id_hakmilikpb,String id_bantahan)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql =  " SELECT ID_BAYARAN FROM TBLPPTBAYARAN ";
	      sql += " WHERE ID_HAKMILIKPB = '"+ id_hakmilikpb +"' AND ID_BANTAHAN = '"+ id_bantahan +"' ";	 

	      ResultSet rs = stmt.executeQuery(sql);
	      myLogger.info("ID BAYARAN :: "+sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
	}	
	
	public Hashtable getIdBayaranAgensi(String id_hakmilik, String id_bantahan)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql =  " SELECT ID_BAYARAN FROM TBLPPTBAYARAN ";
	      sql += " WHERE ID_HAKMILIK = '"+ id_hakmilik +"' AND ID_BANTAHAN = '"+ id_bantahan +"' ";	 

	      ResultSet rs = stmt.executeQuery(sql);
	      myLogger.info("ID BAYARAN :: "+sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
	}		
	
	public Vector getMaklumatSerahCek(String id_bayaran) throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatSerahCek = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT ID_BAYARAN,NAMA_WAKIL,NO_WAKIL,TARIKH_SERAH_CEK,FLAG_SERAH_CEK "; 
				sql += " FROM TBLPPTBAYARAN ";
				sql += " WHERE ID_BAYARAN = '"+ id_bayaran +"' ";				
		
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumatSerahCek :: "+sql);
				Hashtable h;
	
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	h.put("nama_wakil", rs.getString("NAMA_WAKIL")==null?"":rs.getString("NAMA_WAKIL"));
		    	h.put("no_wakil", rs.getString("NO_WAKIL")==null?"":rs.getString("NO_WAKIL"));		    	
		    	h.put("tarikh_serah_cek", rs.getString("TARIKH_SERAH_CEK")==null?"":sdf.format(rs.getDate("TARIKH_SERAH_CEK")));
		    	h.put("flag_serah_cek", rs.getString("FLAG_SERAH_CEK")==null?"":rs.getString("FLAG_SERAH_CEK"));
		    	getMaklumatSerahCek.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatSerahCek;		
	}

	public Vector getMaklumatViaEFT(String id_bayaran) throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatViaEFT = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT ID_BAYARAN,NO_RUJUKAN_SURATEFT,TARIKH_SURAT_EFT,NO_EFT,TARIKH_TERIMA_EFT,TARIKH_BAYARAN_EFT,NO_BAUCER,AMAUN_BAYARAN "; 
				sql += " FROM TBLPPTBAYARAN ";
				sql += " WHERE ID_BAYARAN = "+ id_bayaran +" ";
		
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumatViaEFT :: "+sql);
				Hashtable h;
	
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bayaran", rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN"));
		    	h.put("no_rujukan_surateft", rs.getString("NO_RUJUKAN_SURATEFT")==null?"":rs.getString("NO_RUJUKAN_SURATEFT"));	    	
		    	h.put("tarikh_surat_eft", rs.getString("TARIKH_SURAT_EFT")==null?"":sdf.format(rs.getDate("TARIKH_SURAT_EFT")));
		    	h.put("no_eft", rs.getString("NO_EFT")==null?"":rs.getString("NO_EFT"));
		    	h.put("tarikh_terima_eft", rs.getString("TARIKH_TERIMA_EFT")==null?"":sdf.format(rs.getDate("TARIKH_TERIMA_EFT")));
		    	h.put("tarikh_bayaran_eft", rs.getString("TARIKH_BAYARAN_EFT")==null?"":sdf.format(rs.getDate("TARIKH_BAYARAN_EFT")));
		    	h.put("no_baucer", rs.getString("NO_BAUCER")==null?"":rs.getString("NO_BAUCER"));
		    	h.put("amaun_bayaran", rs.getString("AMAUN_BAYARAN")==null?"":Double.parseDouble(rs.getString("AMAUN_BAYARAN")));
		    	getMaklumatViaEFT.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatViaEFT;	
	}

	public Hashtable getMethodCaraBayar(String id_hakmilikpb, String id_bantahan) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql =  " SELECT CARA_BAYAR FROM TBLPPTBAYARAN ";
	      sql += " WHERE ID_HAKMILIKPB = '"+ id_hakmilikpb +"' AND ID_BANTAHAN = '"+ id_bantahan +"' ";	 

	      ResultSet rs = stmt.executeQuery(sql);
	      myLogger.info("getMethodCaraBayar :: "+sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
		
	}
	
	public Hashtable getMethodCaraBayarAgensi(String id_hakmilik) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql =  " SELECT CARA_BAYAR FROM TBLPPTBAYARAN ";
	      sql += " WHERE ID_HAKMILIK = '"+ id_hakmilik +"' ";	 

	      ResultSet rs = stmt.executeQuery(sql);
	      myLogger.info("getMethodCaraBayar :: "+sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("cara_bayar", rs.getString("CARA_BAYAR")==null?"":rs.getString("CARA_BAYAR"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }		
		
	}	

	public Vector setCarianPB(String id_permohonan,String carianNamaPB,String carianNoLot) throws Exception{
		
		Db db = null;
		String sql = "";
		carianNamaPB = carianNamaPB.trim();	
		carianNoLot = carianNoLot.trim();
		try {
				listCarianPB = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT B.ID_PERMOHONAN,C.ID_HAKMILIKPB,B.ID_HAKMILIK,A.ID_PIHAKBERKEPENTINGAN,A.NAMA_PB,A.NO_PB,B.NO_LOT, "; 
				sql += " B.NO_PT,B.ID_MUKIM,C.SYER_ATAS,C.SYER_BAWAH,C.JUMLAH_AWARD_PU,E.NAMA_MUKIM,F.STATUS_BANTAHAN,G.KETERANGAN,F.ID_BANTAHAN,C.FLAG_BAYAR_BANTAHAN ";
				sql += " FROM TBLPPTPIHAKBERKEPENTINGAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C, TBLRUJMUKIM E, TBLPPTBANTAHAN F, TBLRUJSTATUS G ";
				sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";				
				sql += " AND C.ID_HAKMILIKPB = F.ID_HAKMILIKPB(+) AND F.STATUS_BANTAHAN = G.ID_STATUS(+) AND ";
				sql += " B.ID_MUKIM = E.ID_MUKIM AND C.FLAG_BANTAHAN = '1' AND F.STATUS_BANTAHAN='184' AND B.ID_PERMOHONAN = '"+ id_permohonan +"' ";
				
				//NAMA PB
				if (carianNamaPB != "" && carianNamaPB != null) {
					if (!carianNamaPB.equals("")) {
						sql += " AND UPPER(A.NAMA_PB) LIKE '%" + carianNamaPB.toUpperCase() + "%'";
					}
				}
				
				//NO LOT
				if (carianNoLot != "" && carianNoLot != null) {
					if (!carianNoLot.equals("")) {
						sql += " AND UPPER(B.NO_LOT) LIKE '%" + carianNoLot.toUpperCase() + "%'";
					}
				}				
				
				sql += " ORDER BY A.NAMA_PB ASC";						
				myLogger.info("CARIAN PAMPASAN :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				while (rs.next()){
					h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
			    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
			    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
			    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
			    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
			    	h.put("no_pb", rs.getString("NO_PB")==null?"-":rs.getString("NO_PB"));
			    	h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
			    	h.put("no_pt", rs.getString("NO_PT")==null?"":rs.getString("NO_PT"));
			    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
			    	h.put("syer_atas", rs.getString("SYER_ATAS")==null?"":rs.getString("SYER_ATAS"));
			    	h.put("syer_bawah", rs.getString("SYER_BAWAH")==null?"":rs.getString("SYER_BAWAH")); 	
			    	h.put("jumlah_award_pu", rs.getString("JUMLAH_AWARD_PU")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD_PU")));			    	
			    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));		    	
			    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
			    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
			    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
			    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
			    	listCarianPB.addElement(h);
					bil++;	
				}
				return listCarianPB;
				
		}finally {
			if(db != null) db.close();
		}
	}

	public Vector setCarianAP(String id_permohonan,String carianNamaAP,String carianNoHakmilik) throws Exception{
		
		Db db = null;
		String sql = "";
		carianNamaAP = carianNamaAP.trim();	
		carianNoHakmilik = carianNoHakmilik.trim();
		try {
				listCarianAP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql =  " SELECT B.ID_PERMOHONAN,C.ID_HAKMILIK,D.ID_AGENSI,E.NAMA_AGENSI,D.STATUS_BANTAHAN_AP, "; 
				sql += " F.KETERANGAN,C.JUMLAH_AWARD,D.ID_BANTAHAN,C.FLAG_BAYAR_BANTAHAN,C.NO_HAKMILIK ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";				
				sql += " TBLRUJAGENSI E,TBLRUJSTATUS F ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_HAKMILIK = D.ID_HAKMILIK "; 
				sql += " AND D.ID_AGENSI = E.ID_AGENSI AND D.STATUS_BANTAHAN_AP = F.ID_STATUS ";
				sql += " AND C.FLAG_BANTAHAN = '1' ";
				sql += " AND D.STATUS_BANTAHAN_AP = '201' AND B.ID_PERMOHONAN = '"+id_permohonan+"'  ";
				
				//NAMA AGENSI
				if (carianNamaAP != "" && carianNamaAP != null) {
					if (!carianNamaAP.equals("")) {
						sql += " AND UPPER(E.NAMA_AGENSI) LIKE '%" + carianNamaAP.toUpperCase() + "%'";
					}
				}
				
				//NO HAKMILIK
				if (carianNoHakmilik != "" && carianNoHakmilik != null) {
					if (!carianNoHakmilik.equals("")) {
						sql += " AND UPPER(C.NO_HAKMILIK) LIKE '%" + carianNoHakmilik.toUpperCase() + "%'";
					}
				}				
				
				sql += " ORDER BY E.NAMA_AGENSI ASC";						
				myLogger.info("CARIAN PAMPASAN AP :: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				while (rs.next()){
					
				h = new Hashtable();
		    	h.put("bil", bil);
		    	h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
		    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"":rs.getString("ID_AGENSI"));
		    	h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"":rs.getString("NAMA_AGENSI"));
		    	h.put("keteranganStatusBantahanAp", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
		    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
		    	h.put("amaun_award", rs.getString("JUMLAH_AWARD")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD")));
		    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));

		    	listCarianAP.addElement(h);
				bil++;	
				}
				return listCarianAP;
				
		}finally {
			if(db != null) db.close();
		}
	} 

	public Vector getMaklumatAP(String id_hakmilik)throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatAP = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT D.ID_BANTAHAN,C.ID_HAKMILIK,C.NO_HAKMILIK,D.ID_AGENSI,E.NAMA_AGENSI,C.ID_MUKIM,F.NAMA_MUKIM, "; 
				sql += " C.JUMLAH_AWARD,C.LUAS_LOT,C.ID_UNITLUASLOT,G.KETERANGAN,D.AMAUN_AWARD,D.STATUS_BANTAHAN_AP,C.FLAG_BAYAR_BANTAHAN, ";
				sql += " H.KETERANGAN AS KETERANGANSTATUSBANTAHANAP ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
				sql += " TBLPPTHAKMILIK C,TBLPPTBANTAHAN D, ";
				sql += " TBLRUJAGENSI E,TBLRUJMUKIM F, ";
				sql += " TBLRUJLUAS G,TBLRUJSTATUS H ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
				sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
				sql += " AND C.ID_HAKMILIK = D.ID_HAKMILIK ";
				sql += " AND D.ID_AGENSI = E.ID_AGENSI ";
				sql += " AND C.ID_MUKIM = F.ID_MUKIM(+) ";
				sql += " AND C.ID_UNITLUASLOT = G.ID_LUAS(+) ";				
				sql += " AND D.STATUS_BANTAHAN_AP = H.ID_STATUS ";
				sql += " AND C.ID_HAKMILIK = '"+id_hakmilik+"' ";

				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumatAP :: "+sql);
				Hashtable h;
				
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
		    	h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"-":rs.getString("ID_AGENSI"));
		    	h.put("nama_agensi", rs.getString("NAMA_AGENSI")==null?"-":rs.getString("NAMA_AGENSI"));		    
		    	h.put("id_mukim", rs.getString("ID_MUKIM")==null?"":rs.getString("ID_MUKIM"));
		    	h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));		    	
		    	h.put("jumlah_award", rs.getString("JUMLAH_AWARD")==null?"":Double.parseDouble(rs.getString("JUMLAH_AWARD")));   		    			
		    	h.put("luas_lot", rs.getString("LUAS_LOT")==null?"":rs.getString("LUAS_LOT"));
		    	h.put("id_unitluaslot", rs.getString("ID_UNITLUASLOT")==null?"":rs.getString("ID_UNITLUASLOT"));
		    	h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));		    	
		    	h.put("amaun_award", rs.getString("AMAUN_AWARD")==null?"":Double.parseDouble(rs.getString("AMAUN_AWARD")));		    	
		    	h.put("status_bantahan_ap", rs.getString("STATUS_BANTAHAN_AP")==null?"":rs.getString("STATUS_BANTAHAN_AP"));
		    	h.put("flag_bayar_bantahan", rs.getString("FLAG_BAYAR_BANTAHAN")==null?"":rs.getString("FLAG_BAYAR_BANTAHAN"));
		    	h.put("keteranganstatusbantahanap", rs.getString("KETERANGANSTATUSBANTAHANAP")==null?"":rs.getString("KETERANGANSTATUSBANTAHANAP"));
		    	getMaklumatAP.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatAP;
	}	
	
	//PENAMBAHAN YATI 24/9/2020
	public Vector getMaklumatTkhH(String id_hakmilikpb)throws Exception {
		Db db = null;
		String sql = "";
		try{
				getMaklumatTkhH = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT G.TARIKH_BORANGH FROM TBLPPTBORANGG G, TBLPPTSIASATAN S, TBLPPTHAKMILIK HM, TBLPPTHAKMILIKPB HPB " + 
						"	WHERE S.ID_HAKMILIK = HM.ID_HAKMILIK " + 
						"	AND HPB.ID_HAKMILIK = HM.ID_HAKMILIK " + 
						"	AND S.ID_SIASATAN = G.ID_SIASATAN " + 
						"	AND HPB.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL getMaklumat tkh :: "+sql);
				Hashtable h;
				
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("tarikh_borangh", rs.getDate("TARIKH_BORANGH")==null?"":sdf.format(rs.getDate("TARIKH_BORANGH")));
		    	getMaklumatTkhH.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatTkhH;
	}
	
}
