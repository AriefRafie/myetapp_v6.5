package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class BantahanDaftarOnline extends EkptgCache implements Serializable  {
	 static Logger myLogger = Logger.getLogger(BantahanDaftarOnline.class);
	 private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");	 
	 
	 private Vector listMaklumatTanah = null;
	 private Vector listMaklumatTanahBantahan = null;
	 public Vector getMaklumatBantahan = null;
	 public Vector listPBonline = null;
	 public Vector getMaklumatBantahanKaunter = null;
	 
	 public Vector getListMaklumatTanah(){
			return listMaklumatTanah;
	}
	 
	 public Vector getListMaklumatTanahBantahan(){
			return listMaklumatTanahBantahan;
	}	 
	 
	 
	// GET HAKMILIK USER ONLINE
	public Hashtable getHakmilik(String id_hakmilik,String usid) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT E.NO_HAKMILIK,G.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN,C.ID_KEMENTERIAN,D.ID_AGENSI, ";	
	      sql += " CASE ";
	      sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NULL THEN E.NO_LOT  ";
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT ";	
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT  ";
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT ";
	      sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT || CHR(32) || CHR(40) || E.NO_LOT || CHR(41) ";	
	      sql += " ELSE '' ";
	      sql += " END AS NO_LOTPT, ";	     
	      sql += " F.NAMA_PB,G.ALAMAT1,G.ALAMAT2,G.ALAMAT3,G.POSKOD,H.KETERANGAN AS BANDAR,I.NAMA_NEGERI,G.ID_BANDAR,G.ID_NEGERI ";
	      sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C,TBLPPTPERMOHONAN D, ";
	      sql += " TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F,TBLPPTHAKMILIKPB G, ";
	      sql += " TBLRUJLOT LT,TBLRUJBANDAR H,TBLRUJNEGERI I,TBLRUJKEMENTERIAN J, TBLRUJAGENSI K ";
	      sql += " WHERE A.USER_ID = B.USER_ID AND B.NO_KP_BARU = F.NO_PB ";
	      sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN ";
	      sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN AND E.ID_LOT = LT.ID_LOT(+) ";
	      sql += " AND G.ID_BANDAR = H.ID_BANDAR(+) AND G.ID_NEGERI = I.ID_NEGERI(+) ";
	      sql += " AND C.ID_KEMENTERIAN = J.ID_KEMENTERIAN(+) AND D.ID_AGENSI = K.ID_AGENSI(+) ";
	      sql += " AND E.ID_HAKMILIK = '"+id_hakmilik+"' ";	      
	      sql += " AND A.USER_ID = '"+usid+"' ";
	      
	      myLogger.info("GETHAKMILIK :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
		    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"":rs.getString("ID_AGENSI"));
		    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
		    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
	      }
	      return h;
	    }
	    finally {
	      if (db != null) db.close();
	    }	
	}
	// END
	
	// GET HAKMILIK PENGESAHAN DI KAUNTER	
	public Hashtable getHakmilikKaunter(String id_hakmilik,String id_hakmilikpb) throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();		      
	      sql =  " SELECT E.NO_HAKMILIK,G.ID_HAKMILIKPB,F.ID_PIHAKBERKEPENTINGAN,C.ID_KEMENTERIAN,D.ID_AGENSI, ";	
	      sql += " CASE ";
	      sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NULL THEN E.NO_LOT  ";
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT ";	
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT  ";
	      sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT ";
	      sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT || CHR(32) || CHR(40) || E.NO_LOT || CHR(41) ";	
	      sql += " ELSE '' ";
	      sql += " END AS NO_LOTPT, ";	     
	      sql += " F.NAMA_PB,G.ALAMAT1,G.ALAMAT2,G.ALAMAT3,G.POSKOD,H.KETERANGAN AS BANDAR,I.NAMA_NEGERI,G.ID_BANDAR,G.ID_NEGERI ";
	      sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C,TBLPPTPERMOHONAN D, ";
	      sql += " TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F,TBLPPTHAKMILIKPB G, ";
	      sql += " TBLRUJLOT LT,TBLRUJBANDAR H,TBLRUJNEGERI I,TBLRUJKEMENTERIAN J, TBLRUJAGENSI K ";
	      sql += " WHERE A.USER_ID = B.USER_ID AND B.NO_KP_BARU = F.NO_PB ";
	      sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN ";
	      sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN AND E.ID_LOT = LT.ID_LOT(+) ";
	      sql += " AND G.ID_BANDAR = H.ID_BANDAR(+) AND G.ID_NEGERI = I.ID_NEGERI(+) ";
	      sql += " AND C.ID_KEMENTERIAN = J.ID_KEMENTERIAN(+) AND D.ID_AGENSI = K.ID_AGENSI(+) ";
	      sql += " AND E.ID_HAKMILIK = '"+id_hakmilik+"' ";	      
	      sql += " AND G.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";
	      
	      myLogger.info("GETHAKMILIK KAUNTER :: "+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();
	      if (rs.next()) {
		    	h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
		    	h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
		    	h.put("id_agensi", rs.getString("ID_AGENSI")==null?"":rs.getString("ID_AGENSI"));
		    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
		    	h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	h.put("alamat1", rs.getString("ALAMAT1")==null?"":rs.getString("ALAMAT1"));
		    	h.put("alamat2", rs.getString("ALAMAT2")==null?"":rs.getString("ALAMAT2"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("alamat3", rs.getString("ALAMAT3")==null?"":rs.getString("ALAMAT3"));
		    	h.put("poskod", rs.getString("POSKOD")==null?"":rs.getString("POSKOD"));
		    	h.put("id_bandar", rs.getString("ID_BANDAR")==null?"":rs.getString("ID_BANDAR"));
		    	h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
	      }
	      return h;
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
					sql += " END AS NO_LOTPT ";
					sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C, "; 
					sql += " TBLPPTPERMOHONAN D,TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F, ";
					sql += " TBLPPTHAKMILIKPB G,TBLRUJKEMENTERIAN H,TBLRUJSUBURUSAN SU, ";       
					sql += " TBLRUJSTATUS ST,TBLRUJLOT LT,TBLRUJLUAS I,TBLRUJMUKIM H ";
					sql += " WHERE A.USER_ID = B.USER_ID AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
					sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
					sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND B.NO_KP_BARU = F.NO_PB(+) AND D.ID_STATUS = ST.ID_STATUS ";       
					sql += " AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
					sql += " AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";					
					sql += " AND E.ID_LOT = LT.ID_LOT(+) AND I.ID_LUAS(+) = E.ID_UNITLUASAMBIL   "; 
					sql += " AND H.ID_MUKIM(+) = E.ID_MUKIM AND su.id_suburusan = '52' ";
					sql += " AND A.USER_ID = '"+ usid +"' ";	
					sql += " AND D.ID_PERMOHONAN = '"+ id_permohonan +"' ";	
	
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(E.NO_LOT) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(E.NO_PT) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(LT.KETERANGAN) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot										
					
					sql += " ORDER BY NO_LOTPT ASC, H.NAMA_MUKIM asc";
					myLogger.info("SQL HAKMILIK ONLINE  :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
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
	
	public void setListMaklumatTanah_DgnBantahan(String id_hakmilikpb,String usid,String lot) throws Exception {
		listMaklumatTanahBantahan = new Vector();
			
			Db db = null;
			listMaklumatTanahBantahan.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql =  " SELECT J.ID_BANTAHAN,C.ID_FAIL,D.ID_PERMOHONAN,E.ID_HAKMILIK,E.NO_HAKMILIK,G.ID_HAKMILIKPB,H.NAMA_MUKIM,E.LUAS_AMBIL,E.ID_UNITLUASLOT_CONVERT,I.KETERANGAN, ";
					sql += " CASE ";
					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NULL THEN E.NO_LOT ";
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT "; 
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NULL THEN LT.KETERANGAN || E.NO_PT  ";
					sql += " WHEN E.NO_LOT IS NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT  ";       
					sql += " WHEN E.NO_LOT IS NOT NULL AND E.NO_PT IS NOT NULL THEN LT.KETERANGAN || E.NO_PT || CHR(32) || CHR(40) || E.NO_LOT || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT ";
					sql += " FROM USERS A,USERS_ONLINE B,TBLPFDFAIL C, "; 
					sql += " TBLPPTPERMOHONAN D,TBLPPTHAKMILIK E,TBLPPTPIHAKBERKEPENTINGAN F, ";
					sql += " TBLPPTHAKMILIKPB G,TBLRUJKEMENTERIAN H,TBLRUJSUBURUSAN SU, ";       
					sql += " TBLRUJSTATUS ST,TBLRUJLOT LT,TBLRUJLUAS I,TBLRUJMUKIM H,TBLPPTBANTAHAN J ";
					sql += " WHERE A.USER_ID = B.USER_ID AND C.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
					sql += " AND C.ID_FAIL = D.ID_FAIL AND D.ID_PERMOHONAN = E.ID_PERMOHONAN "; 
					sql += " AND E.ID_HAKMILIK = G.ID_HAKMILIK AND F.ID_PIHAKBERKEPENTINGAN = G.ID_PIHAKBERKEPENTINGAN ";
					sql += " AND C.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND B.NO_KP_BARU = F.NO_PB(+) AND D.ID_STATUS = ST.ID_STATUS ";       
					sql += " AND NVL (E.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
					sql += " AND NVL (E.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";					
					sql += " AND E.ID_LOT = LT.ID_LOT(+) AND I.ID_LUAS(+) = E.ID_UNITLUASAMBIL   "; 
					sql += " AND H.ID_MUKIM(+) = E.ID_MUKIM AND su.id_suburusan = '52' ";
					sql += " AND G.ID_HAKMILIKPB = J.ID_HAKMILIKPB ";
					sql += " AND A.USER_ID = '"+ usid +"' ";	
					sql += " AND G.ID_HAKMILIKPB = '"+ id_hakmilikpb +"' ";	
	
					//NO LOT
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(E.NO_LOT) LIKE '%" + noLOT.toUpperCase() + "%' OR  UPPER(E.NO_PT) LIKE '%" + noLOT.toUpperCase() + "%' OR UPPER(LT.KETERANGAN) LIKE '%" + noLOT.toUpperCase() + "%')";
						}
					}//close if nolot										
					
					sql += " ORDER BY NO_LOTPT ASC, H.NAMA_MUKIM asc";
					myLogger.info("SQL HAKMILIK YG ADA BANTAHAN ONLINE  :: "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
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
			
						listMaklumatTanahBantahan.addElement(h);
						bil++;	
						
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
	}
	
	public Vector getMaklumatBantahan(String id_permohonan,String id_hakmilikpb,String id_bantahan) throws Exception {		 							
		 Db db = null;
		 String sql = "";
		try{
				getMaklumatBantahan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT F.TARIKH_MOHON,F.TARIKH_BORANGN,D.ID_PIHAKBERKEPENTINGAN, "; 
				sql += " F.STATUS_BANTAHAN,F.ALASAN,F.KEPENTINGANKEATAS,E.FLAG_BANTAHAN, ";
				sql += " F.FLAG_PENERIMA_PAMPASAN,F.FLAG_BAHAGIAN_PAMPASAN,F.FLAG_UKUR_LUAS, ";
				sql += " F.FLAG_PAMPASAN,F.AMAUN_TUNTUTAN,F.FLAG_ONLINE ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C,TBLPPTPIHAKBERKEPENTINGAN D, ";
				sql += " TBLPPTHAKMILIKPB E,TBLPPTBANTAHAN F ";	
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK ";
				sql += " AND D.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN ";	
				sql += " AND E.ID_HAKMILIKPB = F.ID_HAKMILIKPB ";
				sql += " AND B.ID_PERMOHONAN = '"+id_permohonan+"' ";
				sql += " AND E.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";
				sql += " AND F.ID_BANTAHAN = '"+id_bantahan+"' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL GETMAKLUMATBANTAHAN :: "+sql);
				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
		    	h.put("tarikh_borangn", rs.getString("TARIKH_BORANGN")==null?"":sdf.format(rs.getDate("TARIKH_BORANGN")));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("alasan", rs.getString("ALASAN")==null?"":rs.getString("ALASAN"));
		    	h.put("kepentingankeatas", rs.getString("KEPENTINGANKEATAS")==null?"":rs.getString("KEPENTINGANKEATAS"));
		    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
		    	h.put("flag_penerima_pampasan", rs.getString("FLAG_PENERIMA_PAMPASAN")==null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
		    	h.put("flag_bahagian_pampasan", rs.getString("FLAG_BAHAGIAN_PAMPASAN")==null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
		    	h.put("flag_ukur_luas", rs.getString("FLAG_UKUR_LUAS")==null?"":rs.getString("FLAG_UKUR_LUAS"));
		    	h.put("flag_pampasan", rs.getString("FLAG_PAMPASAN")==null?"":rs.getString("FLAG_PAMPASAN"));			    	
		    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
		    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
		    	getMaklumatBantahan.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatBantahan;
}	
	
	// GET MAKLUMAT ONLINE DI KAUNTER
	
	public Vector getMaklumatBantahanKaunter(String id_permohonan,String id_hakmilikpb,String id_bantahan) throws Exception {		 							
		 Db db = null;
		 String sql = "";
		try{
				getMaklumatBantahanKaunter = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();				
				sql =  " SELECT F.TARIKH_MOHON,F.TARIKH_BORANGN,D.ID_PIHAKBERKEPENTINGAN, "; 
				sql += " F.STATUS_BANTAHAN,F.ALASAN,F.KEPENTINGANKEATAS,E.FLAG_BANTAHAN, ";
				sql += " F.FLAG_PENERIMA_PAMPASAN,F.FLAG_BAHAGIAN_PAMPASAN,F.FLAG_UKUR_LUAS, ";
				sql += " F.FLAG_PAMPASAN,F.AMAUN_TUNTUTAN,F.FLAG_ONLINE ";
				sql += " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C,TBLPPTPIHAKBERKEPENTINGAN D, ";
				sql += " TBLPPTHAKMILIKPB E,TBLPPTBANTAHAN F ";	
				sql += " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";	
				sql += " AND C.ID_HAKMILIK = E.ID_HAKMILIK ";
				sql += " AND D.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN ";	
				sql += " AND E.ID_HAKMILIKPB = F.ID_HAKMILIKPB ";
				sql += " AND B.ID_PERMOHONAN = '"+id_permohonan+"' ";
				sql += " AND E.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";
				sql += " AND F.ID_BANTAHAN = '"+id_bantahan+"' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("SQL GETMAKLUMATBANTAHAN KAUNTER :: "+sql);
				Hashtable h;			    
		     while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("tarikh_mohon", rs.getString("TARIKH_MOHON")==null?"":sdf.format(rs.getDate("TARIKH_MOHON")));
		    	h.put("tarikh_borangn", rs.getString("TARIKH_BORANGN")==null?"":sdf.format(rs.getDate("TARIKH_BORANGN")));
		    	h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")==null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
		    	h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("alasan", rs.getString("ALASAN")==null?"":rs.getString("ALASAN"));
		    	h.put("kepentingankeatas", rs.getString("KEPENTINGANKEATAS")==null?"":rs.getString("KEPENTINGANKEATAS"));
		    	h.put("flag_bantahan", rs.getString("FLAG_BANTAHAN")==null?"":rs.getString("FLAG_BANTAHAN"));
		    	h.put("flag_penerima_pampasan", rs.getString("FLAG_PENERIMA_PAMPASAN")==null?"":rs.getString("FLAG_PENERIMA_PAMPASAN"));
		    	h.put("flag_bahagian_pampasan", rs.getString("FLAG_BAHAGIAN_PAMPASAN")==null?"":rs.getString("FLAG_BAHAGIAN_PAMPASAN"));
		    	h.put("flag_ukur_luas", rs.getString("FLAG_UKUR_LUAS")==null?"":rs.getString("FLAG_UKUR_LUAS"));
		    	h.put("flag_pampasan", rs.getString("FLAG_PAMPASAN")==null?"":rs.getString("FLAG_PAMPASAN"));			    	
		    	h.put("amaun_tuntutan", rs.getString("AMAUN_TUNTUTAN")==null?"":Double.parseDouble(rs.getString("AMAUN_TUNTUTAN")));
		    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
		    	getMaklumatBantahanKaunter.addElement(h);
		      	}      
			}
				finally{
					if(db != null)db.close();
				}	
			return getMaklumatBantahanKaunter;
}	
	
	
	
	// TRANSACTION ADD/EDIT/DELETE
	
	
	public String daftarBantahan(String txdMohon, String txdBrgN,
			String txtNoLot, String txtNoHakmilik, String txtNama,
			String txtAlamat1, String txtAlamat2, String txtAlamat3,
			String txtPoskod, String txtKptgnAtasTnh, String txtAlasanBantahan,
			String usid, String jenis_pembantah, String ukuran_luas,
			String amaun_pampasan,String id_kementerian, String id_agensi, String terima_pampasan,
			String umpuk_pampasan, String id_pihakberkepentingan, String id_hakmilik, String id_hakmilikpb,
			String txtAmaunTuntutan, String id_permohonan) throws Exception {
			
		 	Db db = null;	 	
		 	Connection conn = null;
		    String sql = "";
		    String output = "";
		    Date now = new Date();
		    
	    	int id_stat = 80;		// ID_STATUS = 80 [BANTAHAN TAWARAN]
	    	String tahun = sdf.format(now);
	    	    
		    String no_bantahan = tahun+"-"+String.format("%06d",getSeqNoBantahan(id_stat));	
		    
		    try
		    {				    	  
		    	  long id_bantahan = DB.getNextID("TBLPPTBANTAHAN_SEQ");
		    	  String TM = "to_date('" + txdMohon + "','dd/MM/yyyy')";
		    	  String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";	

			      db = new Db();
			      //SET AUTOCOMMIT TO FALSE
		          conn = db.getConnection();
		          conn.setAutoCommit(false);
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
		    	
			      //TBLPPTBANTAHAN
			      r = new SQLRenderer();
			      r.add("id_bantahan",id_bantahan);
			      r.add("no_bantahan",no_bantahan);		      
			      r.add("jenis_pembantah",1);				// JENIS PEMBANTAH : 1 [PIHAK BERKEPENTINGAN] & 2 [AGENSI PEMOHON]
			      r.add("tarikh_mohon",r.unquote(TM) );		      
			      r.add("tarikh_borangn",r.unquote(TBN) );
			      r.add("alasan",txtAlasanBantahan);
			      r.add("kepentingankeatas",txtKptgnAtasTnh);
			      r.add("id_pihakberkepentingan",id_pihakberkepentingan);
			      r.add("id_hakmilikpb",id_hakmilikpb);
			      r.add("flag_penerima_pampasan",ukuran_luas);
			      r.add("flag_bahagian_pampasan",amaun_pampasan);
			      r.add("id_agensi",id_agensi);
			      r.add("id_kementerian",id_kementerian);
			      r.add("flag_ukur_luas",terima_pampasan);
			      r.add("flag_pampasan",umpuk_pampasan);
			      r.add("status_bantahan",181);				// 181 = [DALAM PROSES]
			      r.add("amaun_tuntutan",txtAmaunTuntutan);
			      r.add("flag_online",1);		// 1: FLAG PERMOHONAN ONLINE (PENDAFTARAN BANTAHAN)
			      r.add("id_masuk",usid);
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      r.add("id_kemaskini",usid);
			      r.add("tarikh_kemaskini",r.unquote("sysdate"));			      
			      sql = r.getSQLInsert("Tblpptbantahan");
			      myLogger.info("INSERT ONLINE ::"+sql);
			      stmt.executeUpdate(sql);
			      
			      r.clear();
			      
			      //TBLPPTHAKMILIKPB
			      r.update("id_hakmilikpb", id_hakmilikpb);
			      
				  r.add("flag_bantahan",1); 						// 1 = YA
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("Tblppthakmilikpb");
				  myLogger.info("UPDATE ::-> "+sql);	
				  stmt.executeUpdate(sql);	
				  
			      r.clear();
			      
			      //TBLRUJSUBURUSANSTATUSBANTAHAN		      
				  r.add("id_permohonan",id_permohonan); 
				  r.add("id_hakmilik",id_hakmilik);
				  r.add("id_bantahan",id_bantahan);
				  r.add("id_suburusanstatus",1545);			// ID_SUBURUSANSTATUS DALAM PROSES = 181
				  r.add("aktif",1);
				  r.add("id_masuk",usid);
				  r.add("tarikh_masuk",r.unquote("sysdate"));
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));			  
			      sql = r.getSQLInsert("Tblrujsuburusanstatusbantahan");
			      myLogger.info("INSERT TBLRUJSUBURUSANSTATUSBANTAHAN ::"+sql);
			      stmt.executeUpdate(sql);
				  
			      
			      output = ""+id_bantahan;
			      
			      
			      conn.commit();		      		
		    } catch (SQLException se) { 
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
		    } finally {
		      if (db != null) db.close();
		    }			
		    
		    return output;
		}	
	 

	public void updateStatusDalamProses(String id_permohonan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_permohonan", id_permohonan);		    
		    r.add("id_status", 181);	// 181=DALAM PROSES
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptpermohonan");	
		    myLogger.info("EDIT IDSTATUS TBLPPTPERMOHONAN = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}
	
	public void updateFlagOnline(String id_bantahan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("flag_online", 2);	// 2 - FLAG HANTAR KE INTERNAL 
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");	
		    myLogger.info("EDIT FLAG ONLINE = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}	

	public void Kaunter_sahkanPermohonan(String id_bantahan, String usid) throws Exception {
	    Db db = null;
	    String sql = "";	    
	    try
	    {			  
		    db = new Db();
		    Statement stmt = db.getStatement();
		    SQLRenderer r = new SQLRenderer();
		    r.update("id_bantahan", id_bantahan);		    
		    r.add("flag_online", 3);	// 3 - FLAG HANTAR KE INTERNAL 
		    r.add("id_kemaskini",usid);
			r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    sql = r.getSQLUpdate("Tblpptbantahan");	
//		    myLogger.info("EDIT FLAG ONLINE = "+sql);
		    stmt.executeUpdate(sql);		    
	    	}
	    	finally {
	    		if (db != null) db.close();
	    	}				
	}	
	
	
	public void simpanEditBantahan(String usid, String id_bantahan,String txdMohon, String txdBrgN, String txtKptgnAtasTnh,String ukuran_luas, 
			String amaun_pampasan, String terima_pampasan,String umpuk_pampasan, 
			String txtAmaunTuntutan,String txtAlasanBantahan,String id_status_bantahan) throws Exception {

	 	Db db = null;	 	
	 	Connection conn = null;
	    String sql = "";
	    Date now = new Date();
	    try
	    {		
//	    	  if (isNoResitExist(txtNoResit)) {
//	    		  throw new Exception("Rekod No. Resit "+ txtNoResit +" Telah Wujud.");
//	    	  }			    	  
	    	 
	    	  String TM = "to_date('" + txdMohon + "','dd/MM/yyyy')";
	    	  String TBN = "to_date('" + txdBrgN + "','dd/MM/yyyy')";

		      db = new Db();
		      //SET AUTOCOMMIT TO FALSE
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
	    	
		      //TBLPPTBANTAHAN
		      r = new SQLRenderer();
		      r.update("id_bantahan",id_bantahan);		      
		      r.add("tarikh_mohon",r.unquote(TM) );		      
		      r.add("tarikh_borangn",r.unquote(TBN) );
		      r.add("kepentingankeatas",txtKptgnAtasTnh);		      
		      r.add("flag_penerima_pampasan",ukuran_luas);		      
		      r.add("flag_bahagian_pampasan",amaun_pampasan);		      
		      r.add("flag_ukur_luas",terima_pampasan);		      
		      r.add("flag_pampasan",umpuk_pampasan);
		      r.add("amaun_tuntutan",txtAmaunTuntutan);
		      r.add("alasan",txtAlasanBantahan);
		      r.add("id_kemaskini",usid);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      sql = r.getSQLUpdate("Tblpptbantahan");
		      myLogger.info("UPDATE ::"+sql);
		      stmt.executeUpdate(sql);		      		      	      		      

		      if(id_status_bantahan.equals("1610250")){
		    	  
		    	  r.clear();
			      
				  r.update("id_bantahan", id_bantahan);		    
				  r.add("flag_online", 1);	// TUKAR KE ONLINE BALIK
				  r.add("status_bantahan","181"); //Status DALAM PROSES
				  r.add("id_kemaskini",usid);
				  r.add("tarikh_kemaskini",r.unquote("sysdate"));
				  sql = r.getSQLUpdate("Tblpptbantahan");	
				  stmt.executeUpdate(sql);	  
				  
		      }
	
			  conn.commit();	
		      
	    } catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Kemaskini Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }		
		
	}	
	
	 public Vector getListPemohonPBOnlihe()throws Exception {		 
		    Db db = null;
		    String sql = "";		    
		    try{
		      listPBonline = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();		     
		      
		      sql = " SELECT B.ID_STATUS, B.ID_PERMOHONAN, B.NO_PERMOHONAN, A.ID_FAIL, B.NO_RUJUKAN_PTD, ";		      
		      sql +=" B.NO_RUJUKAN_PTG,A.NO_FAIL, B.TARIKH_PERMOHONAN, SU.NAMA_SUBURUSAN, ";
		      sql +=" K.NAMA_KEMENTERIAN, S.KETERANGAN, B.NO_RUJUKAN_UPT,B.TARIKH_KEMASKINI, ";
		      sql +=" D.NAMA_PB,D.NO_PB,BTH.ID_BANTAHAN,C.ID_HAKMILIK,E.ID_HAKMILIKPB, "; 
		      sql +=" CASE ";
		      sql +=" WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NULL THEN C.NO_LOT ";
		      sql +=" WHEN C.NO_LOT IS NULL AND C.NO_PT IS NULL THEN lt.keterangan || C.NO_PT ";
		      sql +=" WHEN C.NO_LOT IS NULL AND C.NO_PT IS NULL THEN lt.keterangan || C.NO_PT ";
		      sql +=" WHEN C.NO_LOT IS NULL AND C.NO_PT IS NOT NULL THEN lt.keterangan || C.NO_PT ";
		      sql +=" WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NOT NULL THEN lt.keterangan || C.NO_PT || CHR(32) || CHR(40) || C.NO_LOT || CHR(41) ";
		      sql +=" ELSE '' ";
		      sql +=" END AS NO_LOTPT ";
		      sql +=" FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, ";
		      sql +=" TBLPPTHAKMILIK C,TBLPPTPIHAKBERKEPENTINGAN D, ";
		      sql +=" TBLPPTHAKMILIKPB E,TBLPPTBANTAHAN F, ";
		      sql +=" TBLRUJSUBURUSAN su,TBLRUJSTATUS s,TBLRUJKEMENTERIAN k,TBLRUJLOT LT,TBLPPTBANTAHAN BTH ";	
		      sql +=" WHERE A.ID_FAIL = B.ID_FAIL ";
		      sql +=" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
		      sql +=" AND C.ID_HAKMILIK = E.ID_HAKMILIK ";
		      sql +=" AND D.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN ";
		      sql +=" AND E.ID_HAKMILIKPB = F.ID_HAKMILIKPB ";
		      sql +=" AND A.ID_SUBURUSAN = su.ID_SUBURUSAN ";
		      sql +=" AND A.ID_KEMENTERIAN = k.ID_KEMENTERIAN ";
		      sql +=" AND B.ID_STATUS = s.ID_STATUS ";
		      sql +=" AND C.ID_LOT = LT.ID_LOT(+) ";
		      sql +=" AND BTH.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
		      sql +=" AND NVL (C.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
		      sql +=" AND NVL (C.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";
		      sql +=" AND SU.ID_SUBURUSAN = '52' ";
		      sql +=" AND F.FLAG_ONLINE = '2' ";
		      
		      myLogger.info("SQL LIST INTERNAL PB ONLINE :: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);		      		      
		      Hashtable h = null;
		      int bil = 1;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("id_status", rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
		    	  h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
		    	  h.put("no_permohonan", rs.getString("NO_PERMOHONAN")==null?"":rs.getString("NO_PERMOHONAN"));
		    	  h.put("id_fail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
		    	  h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")==null?"":rs.getString("NO_RUJUKAN_PTG"));
		    	  h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")==null?"":rs.getString("NO_RUJUKAN_PTD"));		    	  
		    	  h.put("no_fail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));	    	  
		    	  h.put("tarikh_permohonan", rs.getString("TARIKH_PERMOHONAN")==null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));
		    	  h.put("nama_suburusan", rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
		    	  h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
		    	  h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	  h.put("no_rujukan_upt", rs.getString("NO_RUJUKAN_UPT")==null?"":rs.getString("NO_RUJUKAN_UPT"));
		    	  h.put("tarikh_kemaskini", rs.getString("TARIKH_KEMASKINI")==null?"":sdf.format(rs.getDate("TARIKH_KEMASKINI")));
		    	  h.put("nama_pb", rs.getString("NAMA_PB")==null?"":rs.getString("NAMA_PB"));
		    	  h.put("no_pb", rs.getString("NO_PB")==null?"":rs.getString("NO_PB"));
		    	  h.put("id_bantahan", rs.getString("ID_BANTAHAN")==null?"":rs.getString("ID_BANTAHAN"));
		    	  h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
		    	  h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
		    	  h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")==null?"":rs.getString("ID_HAKMILIKPB"));
		    	  listPBonline.addElement(h);
		    	  bil++;		    	  
		      }
				return listPBonline;
		    }
		    finally{
		      if (db != null) db.close();
		    }
	 }	
	
	//GET LIST FAIL PERMOHONAN USER PORTAL
	 
		public static Vector getListPemohonPortal(String userId)throws Exception {
			 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = " SELECT DISTINCT F.USER_ID,B.ID_STATUS,B.ID_PERMOHONAN,A.ID_FAIL,A.NO_FAIL,B.TARIKH_PERMOHONAN,SU.NAMA_SUBURUSAN, ";
		    		sql +=" K.NAMA_KEMENTERIAN,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_PTD,B.NO_RUJUKAN_UPT,A.ID_SUBURUSAN, S.KETERANGAN AS STATUS, B.TARIKH_MASUK,B.TARIKH_KEMASKINI ";
		    		sql +=" FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C,TBLPPTPIHAKBERKEPENTINGAN D, ";
		    		sql +=" TBLPPTHAKMILIKPB E,USERS_ONLINE F,TBLRUJSUBURUSAN SU,TBLRUJKEMENTERIAN K, TBLRUJSTATUS S, TBLPPTAWARD AW, TBLPPTBORANGH BH ";
		    		sql +=" WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
		    		sql +=" AND C.ID_HAKMILIK = E.ID_HAKMILIK AND D.ID_PIHAKBERKEPENTINGAN = E.ID_PIHAKBERKEPENTINGAN ";
		    		sql +=" AND F.NO_KP_BARU = D.NO_PB(+) ";
		    		sql +=" AND B.ID_STATUS = S.ID_STATUS(+)";
		    		sql +=" AND A.ID_SUBURUSAN = SU.ID_SUBURUSAN AND A.ID_KEMENTERIAN = K.ID_KEMENTERIAN ";
		    		sql +=" AND (SELECT DISTINCT p1.id_permohonan FROM tblpptborangh h,tblpptpermohonan p1,tblppthakmilik hm ";
		    		sql +=" WHERE hm.id_permohonan = p1.id_permohonan AND p1.id_permohonan = B.id_permohonan) > 0 ";
		    		sql +=" AND NVL (C.FLAG_PENARIKAN_KESELURUHAN, ' ') <> 'Y' ";
		    		sql +=" AND NVL (C.FLAG_PEMBATALAN_KESELURUHAN, ' ') <> 'Y' ";
		    		sql +=" AND AW.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
		    		sql +=" AND BH.ID_HAKMILIKPB = E.ID_HAKMILIKPB ";
		    		sql +=" AND A.ID_SUBURUSAN = '52' ";
		    		sql +=" AND F.USER_ID = '"+userId+"' ";
		    		sql +=" ORDER BY B.TARIKH_MASUK DESC, B.TARIKH_KEMASKINI DESC ";
		    		
		    		myLogger.info("SQL PB PORTAL :: "+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("status", rs.getString("STATUS")== null?"":rs.getString("STATUS"));
		    			h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
		    			h.put("id_fail", rs.getString("ID_FAIL")== null?"":rs.getString("ID_FAIL"));
		    			h.put("no_fail", rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
		    			h.put("id_status", rs.getString("ID_STATUS")== null?"":rs.getString("ID_STATUS"));
		    			h.put("id_permohonan", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));		    			
		    			h.put("nama_suburusan", rs.getString("NAMA_SUBURUSAN")== null?"":rs.getString("NAMA_SUBURUSAN"));
		    			h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN")== null?"":rs.getString("NAMA_KEMENTERIAN"));	    			
		    			h.put("no_rujukan_ptg", rs.getString("NO_RUJUKAN_PTG")== null?"":rs.getString("NO_RUJUKAN_PTG"));
		    			h.put("no_rujukan_ptd", rs.getString("NO_RUJUKAN_PTD")== null?"":rs.getString("NO_RUJUKAN_PTD"));
		    			h.put("no_rujukan_upt", rs.getString("NO_RUJUKAN_UPT")== null?"":rs.getString("NO_RUJUKAN_UPT"));		    			
		    			h.put("tarikh_permohonan", rs.getDate("TARIKH_PERMOHONAN")==null?"":sdf.format(rs.getDate("TARIKH_PERMOHONAN")));		    			
		    			if(rs.getString("ID_SUBURUSAN") != null && rs.getString("ID_SUBURUSAN") != ""){		    				
		    				if(rs.getString("ID_SUBURUSAN").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("ID_SUBURUSAN").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("ID_SUBURUSAN").equals("53")){
		    					h.put("suburusan","PENGAMBILAN SEMENTARA");
		    				}else{
		    					h.put("suburusan","");
		    				}		    					
		    			}else{
		    				h.put("suburusan","");
		    			}	    				    			
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPemohon	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///************** CREATE SEQUENCE TEMP 
	
	 public static synchronized int getSeqNoBantahan(int id_jenisaduan) throws DbException {
			return getSeqNoAduan(id_jenisaduan);
		}	 

	 public static synchronized int getSeqNoAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
			Connection conn = null;
			//File f = null;
			StringBuffer sb = new StringBuffer();
			int seqno=0;
			try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
		//	f = new File();
			boolean found = false;
			
			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
			sb.append("ID_JENISADUAN = " +id_jenisaduan);
			
			
			ResultSet rs = db.getStatement().executeQuery(sb.toString()); 
			
			if (rs.next()) found = true;
			if (found) {
			//	f.increaseSeqAduan(id_jenisaduan); 
				increaseSeqAduan(id_jenisaduan); 
			} else {
			//	f.addNewAduan(id_jenisaduan);	
				addNewAduan(id_jenisaduan);	
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
	        if ( rs2.next() ) {
	      	  
	      		  seqno = rs2.getInt("NO_TURUTAN");
	      	  
	        }
			conn.commit();
			
			} catch (Exception ex) {
			try {
			conn.rollback(); } catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			} 
			finally {
			if (db != null) db.close();
			}
			
			return seqno;
			}
	 
	 public static void increaseSeqAduan(int id_jenisaduan) throws DbException  {

			Db db = null;
		
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE TBLRUJSEQADUAN  SET "); 
			sb.append("no_turutan = no_turutan + 1 ");
			sb.append(" WHERE ");
			sb.append("id_jenisaduan = '"+id_jenisaduan+"'");
			
			try {
				db = new Db();
				try{
				db.getStatement().executeUpdate(sb.toString());
				} catch (SQLException x) {x.printStackTrace();}
			}catch (Exception ex) {
				throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}
	 public static void addNewAduan(int id_jenisaduan) throws DbException {

			Db db = null;
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
			sb.append(" VALUES (");
			sb.append("'"+id_jenisaduan+"'");
			sb.append(",1)"); //initial value
			
			try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
			}catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
			}
			finally {
			if (db != null) db.close();
			}
		}


	 
		////***** END CREATE SEQUENCE TEMP	
	 

	 

	
}
