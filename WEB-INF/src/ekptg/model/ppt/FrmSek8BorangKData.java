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

public class FrmSek8BorangKData {
	static Logger myLogger = Logger.getLogger(FrmSek8BorangKData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector dataBorangK = null;
	private static Vector listEndosanBorangK = null;
	private static Vector dataEndosanBorangK = null;
	private static Vector dataBorangL = null;
	private static Vector listPenerimaanHakmilik = null;
	private static Vector tarikhBorangK = null;
	private static Vector listHMBorangK = null;
	
	
	
	
	
	
	
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getDataBorangK(){
		return dataBorangK;
	}
	public Vector getListEndosanBorangK(){
		return listEndosanBorangK;
	}
	public Vector getDataEndosanBorangK(){
		return dataEndosanBorangK;
	}
	public Vector getDataBorangL(){
		return dataBorangL;
	}
	public Vector getListPenerimaanHakmilik(){
		return listPenerimaanHakmilik;
	}
	public Vector getTarikhBorangK(){
		return tarikhBorangK;
	}
	public Vector getListHMBorangK(){
		return listHMBorangK;
	}
	
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (59,72,76,74,187,204)";
		
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
		sql += " and bx.cara_bayar in ('1','2')) ";
		sql += " OR p.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
		sql += " where bkx.id_permohonan = p.id_permohonan) ";
		sql += " OR p.flag_segera = '1' AND p.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
		sql += " where bix.id_permohonan = p.id_permohonan) ";                        
		sql += " OR p.flag_segera = '3' AND p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";
		sql += " AND p.id_permohonan in (select distinct bix.id_permohonan from Tblpptborangi bix "; 
		sql += " where bix.id_permohonan = p.id_permohonan)) ";
	
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
		    		sql +="AND p.id_status IN (59,72,76,74,187,204)";
		    	
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		*/
		    		sql = sqlListPermohonan(userIdNegeri);
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
	    		sql +="AND p.id_status IN (59,72,76,74,187,204)";
	    		//sql +="AND p.id_status NOT IN (11,127)";
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
	public void setDataBorangK(String idHakmilik) throws Exception{
		
		dataBorangK = new Vector();
		
		Db db = null;
		dataBorangK.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT DISTINCT B.ID_NEGERI,A.ID_PERMOHONAN, B.ID_HAKMILIK, C.ID_BORANGK, D.ID_HAKMILIKBORANGK, ";
				sql += " C.TARIKH_BORANGK, E.NAMA_MUKIM, F.KETERANGAN AS JENIS_HAKMILIK, B.NO_HAKMILIK, B.TARIKH_DAFTAR, ";
				sql += " B.NO_LOT, B.LUAS_AMBIL, G1.ID_LUAS, G1.KETERANGAN AS UNIT_LUAS, B.LUAS_LOT, B.ID_KATEGORITANAH, ";
				sql += " H.NO_PERSERAHAN, H.TARIKH_TERIMA, H.TARIKH_CATATAN, H.MASA_CATATAN, H. JENIS_MASA, C.CATATAN, H.JENIS_MASA, ";
				sql += " id_unitluasambil_convert,id_unitluaslot_convert, I.keterangan, ";
				
				sql += " CASE ";
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NULL THEN B.NO_LOT "; 
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NULL THEN I.keterangan || B.NO_PT ";
				sql += " WHEN B.NO_LOT IS NULL AND B.NO_PT IS NOT NULL THEN  I.keterangan || B.NO_PT ";       
				sql += " WHEN B.NO_LOT IS NOT NULL AND B.NO_PT IS NOT NULL THEN I.keterangan || B.NO_PT || CHR(32) || CHR(40) || B.NO_LOT || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, B.NO_SYIT "; 
				
				sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTBORANGK C, TBLPPTHAKMILIKBORANGK D, TBLRUJMUKIM E, "; 
				sql += " TBLRUJJENISHAKMILIK F, TBLRUJLUAS G1, TBLPPTENDOSANBORANGK H, TBLRUJLOT I ";
				sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN "; 
				sql += " AND D.ID_BORANGK = C.ID_BORANGK(+) ";
				sql += " AND D.ID_HAKMILIK(+) = B.ID_HAKMILIK ";
				sql += " AND B.ID_MUKIM = E.ID_MUKIM(+) ";
				sql += " AND B.ID_LOT = I.ID_LOT(+) ";
				sql += " AND B.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) ";
				sql += " AND B.ID_UNITLUASAMBIL = G1.ID_LUAS(+) ";
				sql += " AND H.ID_BORANGK(+) = C.ID_BORANGK ";
				sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
				
				myLogger.info(" dataBorangK :"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("no_syit", rs.getString("NO_SYIT")==null?"":rs.getString("NO_SYIT"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("id_borangk", rs.getString("ID_BORANGK")==null?"":rs.getString("ID_BORANGK"));
					h.put("id_hakmilikborangk", rs.getString("ID_HAKMILIKBORANGK")==null?"":rs.getString("ID_HAKMILIKBORANGK"));
					h.put("id_permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					h.put("tarikh_borangk", rs.getDate("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")));
					
					h.put("id_negeri", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("id_kategoritanah", rs.getString("ID_KATEGORITANAH")==null?"":rs.getString("ID_KATEGORITANAH"));
					h.put("nama_mukim", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
					h.put("jenis_hakmilik", rs.getString("JENIS_HAKMILIK")==null?"":rs.getString("JENIS_HAKMILIK"));
					h.put("no_hakmilik", rs.getString("NO_HAKMILIK")==null?"":rs.getString("NO_HAKMILIK"));
					h.put("no_lot", rs.getString("NO_LOT")==null?"":rs.getString("NO_LOT"));
					h.put("unit_luas", rs.getString("UNIT_LUAS")==null?"":rs.getString("UNIT_LUAS"));
					h.put("no_perserahan", rs.getString("NO_PERSERAHAN")==null?"":rs.getString("NO_PERSERAHAN"));
					h.put("tarikh_daftar", rs.getDate("TARIKH_DAFTAR")==null?"":Format.format(rs.getDate("TARIKH_DAFTAR")));
					h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
					h.put("jenis_masa", rs.getString("JENIS_MASA")==null?"":rs.getString("JENIS_MASA"));
					
					h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikh_catatan", rs.getDate("TARIKH_CATATAN")==null?"":Format.format(rs.getDate("TARIKH_CATATAN")));
					h.put("masa_catatan", rs.getString("MASA_CATATAN")==null?"":rs.getString("MASA_CATATAN"));
					h.put("jenis_masa", rs.getString("JENIS_MASA")==null?"":rs.getString("JENIS_MASA"));
					h.put("id_unit_luas", rs.getString("ID_LUAS")==null?"":rs.getString("ID_LUAS"));
	
					if(rs.getString("id_unitluasambil_convert") != null){
						
						if(rs.getString("id_unitluasambil_convert").equals("1")){
							h.put("unitByKategori", "Hektar");
						}else{
							h.put("unitByKategori", "Meter Persegi");
						}			
					}else{
						h.put("unitByKategori", "");
					}
					
					double luasAmbil = 0;
					if(rs.getString("LUAS_AMBIL") != null){
						
						if(rs.getString("id_unitluasambil_convert").equals("1")){
							luasAmbil = rs.getDouble("LUAS_AMBIL");
						}else{
							luasAmbil = rs.getDouble("LUAS_AMBIL") / 10000;
						}
						
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					double LL = 0;
					if(rs.getString("LUAS_LOT") != null){
						
						if(rs.getString("id_unitluaslot_convert")!=null){
							if(rs.getString("id_unitluaslot_convert").equals("1")){
								LL = rs.getDouble("LUAS_LOT");
							}else{
								if(rs.getDouble("LUAS_LOT")==0 || rs.getDouble("LUAS_LOT")==0.00 ||
								   rs.getDouble("LUAS_LOT")==0.0000 ){
									LL = rs.getDouble("LUAS_LOT") / 10000;
								}else{
									LL = 0;
								}							
							}
						}else{
							LL = rs.getDouble("LUAS_LOT");
						}
						
						String luasLot = Utils.formatLuasHM(LL);
						h.put("luas_lot",luasLot);
								
					}else{
						h.put("luas_lot","");
					}
				
					
					//get baki luas
					if((rs.getString("LUAS_LOT") != null)){
	    				double baki_luas = LL - luasAmbil;
						h.put("baki_luas_asal", Utils.formatLuasHM(baki_luas));
	    			}else{
	    				h.put("baki_luas_asal", "");
	    			}
					
					
					dataBorangK.addElement(h);				
				}
	   
		}catch (SQLException se){
			throw new Exception("RALAT :" +se.getMessage());		
		}finally {
			if(db != null) db.close();
		}
	}//close setDataBorangK
	
	@SuppressWarnings("unchecked")
	public static void simpanBorangK(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		conn = db.getConnection();
		    	conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();
	    	
	    		long id_borangk = DB.getNextID("TBLPPTBORANGK_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");	    		
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	   		
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");	 
	    		
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		
	    		//endosan borang k
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
	    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
	    		String txtPerserahan = (String)data.get("txtPerserahan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
	    		
	    		//flag segera
	    		// 0 = biasa
	    		// 1 = pengambilan segera
	    		
	    		//insert into tblpptborangk
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangk", id_borangk);
	    		r.add("flag_segera","0");
	    		r.add("id_permohonan", id_permohonan);	
	    		r.add("catatan", txtCatatan);	
	    		r.add("tarikh_borangk",r.unquote(TBK));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptborangk");
	    		stmt.executeUpdate(sql);
    	
	    		//insert into tblppthakmilikborangk
	    		SQLRenderer rhm = new SQLRenderer();
	    		rhm.add("id_borangk", id_borangk);
	    		rhm.add("id_hakmilik", id_hakmilik);
	    		rhm.add("tarikh_masuk",rhm.unquote("sysdate"));
	    		rhm.add("id_masuk",id_user);    		
	    		sql = rhm.getSQLInsert("Tblppthakmilikborangk");
	    		stmt.executeUpdate(sql);
	    		
	    		r.clear();
	    		
	    		r.add("id_borangk", id_borangk);	   
	    		r.add("jenis_masa",socJenisWaktu);  
	    		r.add("no_perserahan",txtPerserahan); 
	    		r.add("masa_catatan",txtMasaCatatan); 
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_catatan",r.unquote(TC));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptendosanborangk");
	    		stmt.executeUpdate(sql);
	    		
	    		conn.commit();	
	    		
	    	}catch (SQLException se) { 
	    		try {
	    			conn.rollback();
	    		}catch (SQLException se2) {
	    			throw new Exception("Rollback error:"+se2.getMessage());
	    		}
	    	throw new Exception("Ralat : Masalah penyimpanan data "+se.getMessage());
	    }
	    finally {
	      if (db != null) db.close();
	    }
	   
	  }//close simpanBorangK
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status borang k
	    		String status = "76";
	    		
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
	
	@SuppressWarnings("unchecked")
	public static void updateBorangK(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_borangk = (String)data.get("id_borangk");
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");
	    		String txtCatatan = (String)data.get("txtCatatan");
	    		
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		//endosan borang k
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
	    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
	    		String txtPerserahan = (String)data.get("txtPerserahan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	   
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borangk", id_borangk);
	    		r.add("tarikh_borangk",r.unquote(TBK));
	    		r.add("catatan",txtCatatan);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptborangk");
	    		stmt.executeUpdate(sql);
    	
	    		r.clear();

	    		r.update("id_borangk", id_borangk);	   
	    		r.add("jenis_masa",socJenisWaktu);  
	    		r.add("no_perserahan",txtPerserahan); 
	    		r.add("masa_catatan",txtMasaCatatan); 
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_catatan",r.unquote(TC));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptendosanborangk");
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateBorangK
	
	@SuppressWarnings("unchecked")
	public static void simpanEndosanBorangK(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_borangk = (String)data.get("id_borangk");
	    		
	    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
	    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
	    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
	    		String txtPerserahan = (String)data.get("txtPerserahan");
	    		String socJenisWaktu = (String)data.get("socJenisWaktu");
	    		//String sorTerima = (String)data.get("sorTerima");
	    		
	    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
	    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangk", id_borangk);	   
	    		r.add("jenis_masa",socJenisWaktu);  
	    		r.add("no_perserahan",txtPerserahan); 
	    		//r.add("flag_terima",sorTerima); 
	    		r.add("masa_catatan",txtMasaCatatan); 
	    		r.add("tarikh_terima",r.unquote(TT));
	    		r.add("tarikh_catatan",r.unquote(TC));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptendosanborangk");
	    		stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanEndosanBorangK
	
	@SuppressWarnings("unchecked")
	public void setListEndosanBorangK(String idBorangK) throws Exception{
		
		listEndosanBorangK = new Vector();
		
		Db db = null;
		listEndosanBorangK.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT DISTINCT ID_ENDOSANBORANGK,TARIKH_TERIMA,TARIKH_CATATAN,MASA_CATATAN, ";
				sql += " JENIS_MASA,FLAG_TERIMA,NO_PERSERAHAN ";
				sql += " FROM TBLPPTENDOSANBORANGK ";
				sql += " WHERE ID_BORANGK = '"+idBorangK+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_endosanborangk", rs.getString("ID_ENDOSANBORANGK")==null?"":rs.getString("ID_ENDOSANBORANGK"));
					h.put("masa_catatan", rs.getString("MASA_CATATAN")==null?"":rs.getString("MASA_CATATAN"));
					h.put("jenis_masa", rs.getString("JENIS_MASA")==null?"":rs.getString("JENIS_MASA"));
					
					if(rs.getString("JENIS_MASA") != null && rs.getString("JENIS_MASA") != ""){
						
						if(rs.getString("JENIS_MASA").equals("1")){
							h.put("masa","PAGI");
						}else if(rs.getString("JENIS_MASA").equals("2")){
							h.put("masa","TENGAHARI");
						}else if(rs.getString("JENIS_MASA").equals("3")){
							h.put("masa","PETANG");
						}else{
							h.put("masa","");
						}
		
	    			}else{
	    				h.put("masa","");
	    			}
					
					h.put("flag_terima", rs.getString("FLAG_TERIMA")==null?"":rs.getString("FLAG_TERIMA"));
					h.put("no_perserahan", rs.getString("NO_PERSERAHAN")==null?"":rs.getString("NO_PERSERAHAN"));
					h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikh_catatan", rs.getDate("TARIKH_CATATAN")==null?"":Format.format(rs.getDate("TARIKH_CATATAN")));
					listEndosanBorangK.addElement(h);		
					bil++;
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setListEndosanBorangK
	
	@SuppressWarnings("unchecked")
	//public void setDataEndosanBorangK(String idEndosanBorangk) throws Exception{
	public void setDataEndosanBorangK(String idborangk) throws Exception{	
		
		dataEndosanBorangK = new Vector();
		
		Db db = null;
		dataEndosanBorangK.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT DISTINCT ID_BORANGK,ID_ENDOSANBORANGK,TARIKH_TERIMA,TARIKH_CATATAN,MASA_CATATAN, ";
				sql += " JENIS_MASA,FLAG_TERIMA,NO_PERSERAHAN ";
				sql += " FROM TBLPPTENDOSANBORANGK ";
				//sql += " WHERE ID_ENDOSANBORANGK = '"+idEndosanBorangk+"'";
				sql += " WHERE ID_BORANGK = '"+idborangk+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
			
				while (rs.next()){
					h = new Hashtable();
					h.put("id_endosanborangk", rs.getString("ID_ENDOSANBORANGK")==null?"":rs.getString("ID_ENDOSANBORANGK"));
					h.put("masa_catatan", rs.getString("MASA_CATATAN")==null?"":rs.getString("MASA_CATATAN"));
					h.put("jenis_masa", rs.getString("JENIS_MASA")==null?"":rs.getString("JENIS_MASA"));
					h.put("flag_terima", rs.getString("FLAG_TERIMA")==null?"":rs.getString("FLAG_TERIMA"));
					h.put("no_perserahan", rs.getString("NO_PERSERAHAN")==null?"":rs.getString("NO_PERSERAHAN"));
					h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikh_catatan", rs.getDate("TARIKH_CATATAN")==null?"":Format.format(rs.getDate("TARIKH_CATATAN")));
					dataEndosanBorangK.addElement(h);		
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setListEndosanBorangK
	
	 public static void hapusEndosan(String id_endosanborangk) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM Tblpptendosanborangk where id_endosanborangk = '"+id_endosanborangk+"'";
	    		stmt.executeUpdate(sql);
  	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusWarta
	
	 @SuppressWarnings("unchecked")
		public static void updateEndosan(Hashtable data) throws Exception{
				
			  Db db = null;
			  String sql = "";
		    
			  try{
		      
				  	db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_endosanborangk = (String)data.get("id_endosanborangk");
		    		
		    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		    		String txdTarikhCatatan = (String)data.get("txdTarikhCatatan");
		    		String txtMasaCatatan = (String)data.get("txtMasaCatatan");
		    		String txtPerserahan = (String)data.get("txtPerserahan");
		    		String socJenisWaktu = (String)data.get("socJenisWaktu");
		    		//String sorTerima = (String)data.get("sorTerima");
		    		
		    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
		    		String TC = "to_date('" + txdTarikhCatatan + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_endosanborangk", id_endosanborangk);	   
		    		r.add("jenis_masa",socJenisWaktu);  
		    		r.add("no_perserahan",txtPerserahan); 
		    		//r.add("flag_terima",sorTerima); 
		    		r.add("masa_catatan",txtMasaCatatan); 
		    		r.add("tarikh_terima",r.unquote(TT));
		    		r.add("tarikh_catatan",r.unquote(TC));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblpptendosanborangk");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
	}//close updateEndosan
	 
	@SuppressWarnings("unchecked")
	public void setDataBorangL(String idpermohonan) throws Exception{
			
		dataBorangL = new Vector();
			
			Db db = null;
			dataBorangL.clear();
			String sql = "";
			
			try {
				
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql  = "SELECT DISTINCT A.ID_PERMOHONAN, B.ID_HAKMILIK, C.ID_BORANGL, C.TARIKH_BORANGL, C.TEMPOH, C.JENIS_PILIH ";
					sql += " FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B, TBLPPTBORANGL C ";
					sql += " WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN ";
					sql += " AND C.ID_PERMOHONAN = A.ID_PERMOHONAN ";
					sql += " AND B.ID_BORANGL = C.ID_BORANGL ";
					sql += " AND A.ID_PERMOHONAN = '"+idpermohonan+"'";
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
				
					while (rs.next()){
						h = new Hashtable();
						h.put("id_borangl", rs.getString("ID_BORANGL")==null?"":rs.getString("ID_BORANGL"));
						h.put("jenis_pilih", rs.getString("JENIS_PILIH")==null?"":rs.getString("JENIS_PILIH"));
						h.put("tempoh", rs.getString("TEMPOH")==null?"":rs.getString("TEMPOH"));
						h.put("tarikh_borangl", rs.getDate("TARIKH_BORANGL")==null?"":Format.format(rs.getDate("TARIKH_BORANGL")));
						dataBorangL.addElement(h);		
					}
		
			}finally {
				if(db != null) db.close();
			}
	}//close setDataBorangL 
	 
	@SuppressWarnings("unchecked")
	public static void simpanBorangL(Hashtable data, String idHakmilik) throws Exception{
			
		Db db = null;
		String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		long id_borangl = DB.getNextID("TBLPPTBORANGL_SEQ");
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String txdTarikhBorangL = (String)data.get("txdTarikhBorangL");
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		//String sorPilihHakmilik = (String)data.get("sorPilihHakmilik");
	 
	    		String TBL = "to_date('" + txdTarikhBorangL + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_borangl",id_borangl);
	    		r.add("id_permohonan", id_permohonan);	   
	    		r.add("tempoh",txtTempoh);  
	    		//r.add("jenis_pilih",sorPilihHakmilik); 
	    		r.add("tarikh_borangl",r.unquote(TBL));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("TBLPPTBORANGL");
	    		stmt.executeUpdate(sql);
    	
	    		r.clear();
	    		
	    		r.update("id_hakmilik", idHakmilik);	   
	    		r.add("flag_borangl","1");  
	    		r.add("id_borangl",id_borangl);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTHAKMILIK");
	    		stmt.executeUpdate(sql);
	    		
		  	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
	   
	  }//close simpanBorangL
	
	@SuppressWarnings("unchecked")
	public static void updateBorangL(Hashtable data, String idHakmilik) throws Exception{
			
		Db db = null;
		String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_borangl = (String)data.get("id_borangl");
	    		
	    		String txdTarikhBorangL = (String)data.get("txdTarikhBorangL");
	    		String txtTempoh = (String)data.get("txtTempoh");
	    		//String sorPilihHakmilik = (String)data.get("sorPilihHakmilik");
	 
	    		String TBL = "to_date('" + txdTarikhBorangL + "','dd/MM/yyyy')";
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_borangl",id_borangl);	   
	    		r.add("tempoh",txtTempoh);  
	    		//r.add("jenis_pilih",sorPilihHakmilik); 
	    		r.add("tarikh_borangl",r.unquote(TBL));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("TBLPPTBORANGL");
	    		stmt.executeUpdate(sql);
    	
	    		SQLRenderer r2 = new SQLRenderer();
	    		r2.update("id_hakmilik", idHakmilik);	   
	    		r2.add("flag_borangl","1");  
	    		r2.add("id_borangl",id_borangl);
	    		r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	    		r2.add("id_kemaskini",id_user);    		
	    		sql = r2.getSQLUpdate("TBLPPTHAKMILIK");
	    		stmt.executeUpdate(sql);
	    		
		  	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
	   
	  }//close updateBorangL
	
	@SuppressWarnings("unchecked")
	public static void deleteFlag(Hashtable data) throws Exception{
			
		Db db = null;
		String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");

	    		SQLRenderer r2 = new SQLRenderer();
	    		r2.update("id_permohonan", id_permohonan);	   
	    		r2.add("flag_borangl","");  
	    		r2.add("tarikh_kemaskini",r2.unquote("sysdate"));
	    		r2.add("id_kemaskini",id_user);    		
	    		sql = r2.getSQLUpdate("TBLPPTHAKMILIK");
	    		stmt.executeUpdate(sql);
	    		
		  	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
	   
	  }//close deleteFlag
	
	
	@SuppressWarnings("unchecked")
	public void setListPenerimaanHakmilik(String idPermohonan,String lot,String radio) throws Exception{
		
		listPenerimaanHakmilik = new Vector();
		
		Db db = null;
		listPenerimaanHakmilik.clear();
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, m.no_subjaket ";
				sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				sql += " AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				if(radio.equals("2")){
					sql += " AND NVL(m.status_borangl,0) = '1'";  
				}else if(radio.equals("3")){
					sql += " AND NVL(m.status_borangl,0) = '2'";  
				}
				
				//NO LOT / NAMA PB
				if (lot != "" && lot != null) {
					if (!noLOT.equals("")) {
						sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
							   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
							   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
		                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
		                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
		                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
					}
				}//close if nolot
				
				sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, mk.nama_mukim asc ";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
					
					if(rs.getString("status_borangl") != null && rs.getString("status_borangl") != ""){
						if(rs.getString("status_borangl").equals("1")){
							h.put("statusBorangL", "HAKMILIK BELUM DITERIMA");
						}else if(rs.getString("status_borangl").equals("2")){
							h.put("statusBorangL", "HAKMILIK TELAH DITERIMA");
						}else{
							h.put("statusBorangL", "");
						}
					}else{
						h.put("statusBorangL", "");
					}
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){
						
						if(rs.getString("id_kategoritanah").equals("2")){
							h.put("unitByKategori", "Hektar");
						}else{
							h.put("unitByKategori", "Meter Persegi");
						}			
					}else{
						h.put("unitByKategori", "");
					}

					if(rs.getString("id_kategoritanah") != null && rs.getString("id_kategoritanah") != ""){						
						if(rs.getString("id_kategoritanah").equals("2")){	
							
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil") * 10000;
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}			
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"-":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
					h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
					h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
					h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
					
					h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
					h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
					h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
					
					h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
					h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
					h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
					
					h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
					h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
					h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
					h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
					h.put("flag_borangl", rs.getString("flag_borangl")==null?"":rs.getString("flag_borangl"));
					
					listPenerimaanHakmilik.addElement(h);
					bil++;	
				}
			//return list;
		}finally {
			if(db != null) db.close();
		}
		
	}//close setListPenerimaanHakmilik
	
	 @SuppressWarnings("unchecked")
		public static void updateHakmilik(Hashtable data) throws Exception{
				
			  Db db = null;
			  String sql = "";
		    
			  try{
		      
				  	db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		
		    		String txdTarikhTerima = (String)data.get("txdTarikhTerima");
		    		String sorStatusBorangL = (String)data.get("sorStatusBorangL");
		   
		    		String TT = "to_date('" + txdTarikhTerima + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_hakmilik", id_hakmilik);	   
		    		r.add("status_borangl",sorStatusBorangL);  
		    		r.add("tarikh_terima_hm",r.unquote(TT));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblppthakmilik");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
	}//close updateEndosan
	
	 @SuppressWarnings("unchecked")
		public void setTarikhBorangK(String idHakmilik) throws Exception{
			
		 tarikhBorangK = new Vector();
			
			Db db = null;
			tarikhBorangK.clear();
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql  = "SELECT DISTINCT MAX ";
					sql += " (CASE ";
					sql += " WHEN A.TARIKH_AMBIL_CEK IS NOT NULL THEN A.TARIKH_AMBIL_CEK ";
					sql += " WHEN A.TARIKH_SURAT_EFT IS NOT NULL THEN A.TARIKH_SURAT_EFT ";
					sql += " END) AS TARIKH_BORANGK ";
					sql += " FROM TBLPPTBAYARAN A, TBLPPTHAKMILIK B, TBLPPTHAKMILIKPB C ";
					sql += " WHERE C.ID_HAKMILIK = B.ID_HAKMILIK ";
					sql += " AND A.ID_HAKMILIKPB = C.ID_HAKMILIKPB ";
					sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
					
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("tarikh_borangk", rs.getDate("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")));
						tarikhBorangK.addElement(h);					
					}
		
			}finally {
				if(db != null) db.close();
			}
	}//close  
	 
	@SuppressWarnings("unchecked")
	public void setListHMBorangK(String idPermohonan,String lot,String flagSegera) throws Exception{
			
		 listHMBorangK = new Vector();
			
			Db db = null;
			listHMBorangK.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = /*"SELECT DISTINCT m.flag_segera_sebahagian, m.seksyen,m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
					sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
					sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
					sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
					sql += " m.flag_ambil_segera, u.user_name as nama_pegawai, m.id_unitluasambil_convert, m.flag_borangl, m.no_subjaket, ";
					sql += "(select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
					sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
					sql += " CASE ";
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt "; 
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT ";  
					
					sql += " , bl.id_borangl, bl.tarikh_borangl, bl.jenis_pilih,  bl.tempoh ";
					
					sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u "; 
					sql += " , Tblpptborangl bl ";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND m.id_hakmilik = bl.id_hakmilik(+) ";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND m.id_pegawai = u.user_id(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y' ";
				
					sql += " AND (p.flag_segera = '3' AND m.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx "; 
					sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb "; 
					sql += " and bx.cara_bayar in ('1','2') AND hx.flag_segera_sebahagian = 'N' ) "; 
					sql += " OR p.flag_segera = '3' AND m.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx ";  
					sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";  
					sql += " and (select count(*) from Tblpptborangi bix ";  
					sql += " where bix.id_permohonan = p.id_permohonan) > 0 ";
					sql += " OR flag_segera = '2' AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b "; 
					sql += " where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > 0 "; 
					sql += " OR flag_segera = '1' AND (select count(*) from Tblpptborangi bix where bix.id_permohonan = p.id_permohonan) > 0 ) ";
					
//					if(flagSegera.equals("2")){
//						sql += " AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";
//					}
					
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT / NAMA PB
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
								   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
			                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
			                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
			                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
			                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
						}
					}//close if nolot
					
					//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(NO_LOTPT,10) asc, mk.nama_mukim asc ";
					//Susunan di ubah ke mukim,lot,subjaket
					sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
					*/
							" SELECT DISTINCT M.FLAG_SEGERA_SEBAHAGIAN, M.SEKSYEN, M.CATATAN," +
							"P.ID_PERMOHONAN, LS.KETERANGAN AS UNIT1," +
							"LT.KETERANGAN AS UNIT2, M.ID_HAKMILIK, M.ID_NEGERI," +
							"N.NAMA_NEGERI, M.ID_JENISHAKMILIK, M.ID_DAERAH, M.ID_MUKIM," +
							"MK.NAMA_MUKIM, M.ID_UNITLUASLOT, M.LUAS_AMBIL, M.NO_HAKMILIK," +
							"M.NO_LOT, M.LUAS_LOT, M.NO_PT, M.TARIKH_DAFTAR," +
							"M.TARIKH_LUPUT, M.TEMPOH_LUPUT, JH.KOD_JENIS_HAKMILIK," +
							"M.LOKASI, M.SYARAT_NYATA, M.SYARAT_KHAS, M.SEKATAN_HAK," +
							"M.SEKATAN_KEPENTINGAN, M.NO_SYIT," +
							"JH.KETERANGAN AS JENIS_HAKMILIK, M.ID_KATEGORITANAH," +
							"M.FLAG_AMBIL_SEGERA, U.USER_NAME AS NAMA_PEGAWAI," +
							"M.ID_UNITLUASAMBIL_CONVERT, M.FLAG_BORANGL, M.NO_SUBJAKET, " +
							"NVL(TPB_MAIN.TPB,0) AS TOTALPB," +
							"CASE " +
							"WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL " +
							"THEN M.NO_LOT " +
							"WHEN M.NO_LOT IS NULL AND M.NO_PT IS NULL " +
							"THEN LT.KETERANGAN || M.NO_PT " +
							"WHEN M.NO_LOT IS NULL AND M.NO_PT IS NOT NULL " +
							"THEN LT.KETERANGAN || M.NO_PT " +
							"WHEN M.NO_LOT IS NOT NULL " +
							"AND M.NO_PT IS NOT NULL " +
							"THEN LT.KETERANGAN || M.NO_PT || CHR (32)|| CHR (40)|| M.NO_LOT|| CHR (41) " +
							"ELSE '' " +
							"END AS NO_LOTPT, " +
							"BL.ID_BORANGL, BL.TARIKH_BORANGL, BL.JENIS_PILIH, BL.TEMPOH " +
							"FROM TBLPPTPERMOHONAN P,TBLRUJLOT LT,TBLRUJLUAS LS,TBLRUJMUKIM MK,TBLRUJNEGERI N," +
							"TBLPPTHAKMILIK M,TBLRUJJENISHAKMILIK JH,USERS U,TBLPPTBORANGL BL," +
							"(SELECT HM1.ID_HAKMILIK, COUNT(HPB1.ID_PIHAKBERKEPENTINGAN) AS TPB FROM " +
							"TBLPPTHAKMILIKPB HPB1, TBLPPTHAKMILIK HM1 " +
							"WHERE HPB1.ID_HAKMILIK = HM1.ID_HAKMILIK " +
							"AND HPB1.ID_JENISPB NOT IN (40, 41, 42) " +
							"AND HM1.ID_PERMOHONAN = '"+idPermohonan+"' " +
							"GROUP BY HM1.ID_HAKMILIK) TPB_MAIN " +
							"WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND M.ID_NEGERI = N.ID_NEGERI " +
							"AND M.ID_HAKMILIK = TPB_MAIN.ID_HAKMILIK(+) " +
							"AND M.ID_HAKMILIK = BL.ID_HAKMILIK(+) " +
							"AND LS.ID_LUAS(+) = M.ID_UNITLUASLOT " +
							"AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) " +
							"AND M.ID_LOT = LT.ID_LOT(+) " +
							"AND M.ID_MUKIM = MK.ID_MUKIM(+) " +
							"AND M.ID_PEGAWAI = U.USER_ID(+) " +
							"AND NVL (M.FLAG_PEMBATALAN_KESELURUHAN, 0) <> 'Y' " +
							"AND NVL (M.FLAG_PENARIKAN_KESELURUHAN, 0) <> 'Y'" +
							" AND (P.FLAG_SEGERA = '3' " +
							"AND M.ID_HAKMILIK IN (SELECT DISTINCT HX.ID_HAKMILIK " +
							"FROM TBLPPTHAKMILIK HX, TBLPPTHAKMILIKPB HPBX, TBLPPTBAYARAN BX " +
							"WHERE HX.ID_PERMOHONAN = '"+idPermohonan+"' " +
							"AND HPBX.ID_HAKMILIK = HX.ID_HAKMILIK " +
							"AND BX.ID_HAKMILIKPB = HPBX.ID_HAKMILIKPB " +
							"AND BX.CARA_BAYAR IN ('1', '2') " +
							"AND HX.FLAG_SEGERA_SEBAHAGIAN = 'N') OR " +
							"P.FLAG_SEGERA = '3' " +
							"AND M.ID_HAKMILIK IN (SELECT DISTINCT HX.ID_HAKMILIK " +
							"FROM TBLPPTHAKMILIK HX" +
							" WHERE HX.ID_PERMOHONAN = '"+idPermohonan+"' " +
							"AND HX.FLAG_SEGERA_SEBAHAGIAN = 'Y') AND (SELECT COUNT (*) " +
							"FROM TBLPPTBORANGI BIX " +
							"WHERE BIX.ID_PERMOHONAN = '"+idPermohonan+"') > 0 " +
							"OR FLAG_SEGERA = '2' AND (SELECT COUNT (*) " +
							"FROM TBLPPTBAYARAN A, TBLPPTHAKMILIKPB B, TBLPPTHAKMILIK C " +
							"WHERE A.ID_HAKMILIKPB = B.ID_HAKMILIKPB AND B.ID_HAKMILIK = C.ID_HAKMILIK " +
							"AND C.ID_PERMOHONAN = '"+idPermohonan+"') > 0 " +
							"OR FLAG_SEGERA = '1' " +
							"AND (SELECT COUNT (*) FROM TBLPPTBORANGI BIX " +
							"WHERE BIX.ID_PERMOHONAN = '"+idPermohonan+"') > 0) " +
							"AND P.ID_PERMOHONAN = '"+idPermohonan+"' " +
							"ORDER BY MK.NAMA_MUKIM ASC, " +
							"LPAD (M.NO_LOT, 20) ASC, " +
							"LPAD (M.NO_PT, 20) ASC," +
							"LPAD (NO_LOTPT, 20) ASC, " +
							"LPAD (M.NO_SUBJAKET, 20) ASC";
					//QRSLOW
					myLogger.info("LIST KKKKKKKK :"+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						
						h.put("id_borangl", rs.getString("id_borangl")==null?"":rs.getString("id_borangl"));
						h.put("jenis_pilih", rs.getString("jenis_pilih")==null?"":rs.getString("jenis_pilih"));
						h.put("tarikh_borangl", rs.getDate("tarikh_borangl")==null?"":Format.format(rs.getDate("tarikh_borangl")));
						
						if(rs.getString("jenis_pilih") != null && rs.getString("jenis_pilih") != ""){
							if(rs.getString("jenis_pilih").equals("1")){
								h.put("status_borang_l","Hakmilik Belum Diterima");
							}else if(rs.getString("jenis_pilih").equals("2")){
								h.put("status_borang_l","Hakmilik Telah Diterima");
							}								
						}else{
							h.put("status_borang_l","");
						}
						
						h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
						h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
						h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
						h.put("flag_borangl", rs.getString("flag_borangl")==null?"":rs.getString("flag_borangl"));
						h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
						h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
						h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
						h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
						h.put("unitluaslot", rs.getString("unit1")==null?"-":rs.getString("unit1"));			
						h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
						h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
						h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
						h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
						h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
						h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
						h.put("nama_mukim", rs.getString("nama_mukim")==null?"-":rs.getString("nama_mukim"));
						h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
						h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
						
						if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
							
							double luasAmbil = rs.getDouble("luas_ambil");
							String LA = Utils.formatLuasHM(luasAmbil);
							h.put("luas_ambil",LA);
									
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

						if(rs.getString("id_unitluasambil_convert") != null){						
							if(rs.getString("id_unitluasambil_convert").equals("1")){	
								
								if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
									double luasAmbil = rs.getDouble("luas_ambil") * 10000;
									String LAH = Utils.formatLuasHM(luasAmbil);
									h.put("nilaiTanah",LAH);
								}else{
									h.put("nilaiTanah","0");
								}
								
							}else{						
								if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
									double luasAmbil = rs.getDouble("luas_ambil");
									String LAM = Utils.formatLuasHM(luasAmbil);
									h.put("nilaiTanah",LAM);
								}else{
									h.put("nilaiTanah","0");
								}						
							}			
						}else{
							h.put("nilaiTanah","0");
						}
						
						
						h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"-":rs.getString("id_kategoritanah"));
						h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"-":rs.getString("no_hakmilik"));
						h.put("no_lot", rs.getString("no_lot")==null?"-":rs.getString("no_lot"));
						h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
						h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
						h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
						
						h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
						h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
						h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
						
						h.put("tempoh", rs.getString("tempoh")==null?"":rs.getString("tempoh"));
						
						h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
						h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
						h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
						
						h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
						h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
						h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
						h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
						
						listHMBorangK.addElement(h);
						bil++;	
					}
				//return list;
			}finally {
				if(db != null) db.close();
			}
	}//close setListHMBorangK 
	
	
	@SuppressWarnings("unchecked")
	public int setListHMBorangK_count(String idPermohonan,String lot,String flagSegera) throws Exception{
		
			Db db = null;
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = /*"SELECT count(*) as total ";
					
					sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u "; 
					sql += " , Tblpptborangl bl ";
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND m.id_hakmilik = bl.id_hakmilik(+) ";
					sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND m.id_pegawai = u.user_id(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y' ";
				
					sql += " AND (p.flag_segera = '3' AND m.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx "; 
					sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb "; 
					sql += " and bx.cara_bayar in ('1','2') AND hx.flag_segera_sebahagian = 'N' ) "; 
					sql += " OR p.flag_segera = '3' AND m.id_hakmilik in (select distinct hx.id_hakmilik from Tblppthakmilik hx ";  
					sql += " where hx.id_permohonan = p.id_permohonan and hx.flag_segera_sebahagian = 'Y') ";  
					sql += " and (select count(*) from Tblpptborangi bix ";  
					sql += " where bix.id_permohonan = p.id_permohonan) > 0 ";
					sql += " OR flag_segera = '2' AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b "; 
					sql += " where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > 0 "; 
					sql += " OR flag_segera = '1' AND (select count(*) from Tblpptborangi bix where bix.id_permohonan = p.id_permohonan) > 0 ) ";
					
//					if(flagSegera.equals("2")){
//						sql += " AND (select count(*)from tblpptbayaran a, tblppthakmilikpb b where a.id_hakmilikpb = b.id_hakmilikpb and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";
//					}
					
					sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
					
					//NO LOT / NAMA PB
					if (lot != "" && lot != null) {
						if (!noLOT.equals("")) {
							sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
								   " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
								   " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
			                       " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
			                       " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
			                       " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
			                       " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
						}
					}//close if nolot
					
					//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(NO_LOTPT,10) asc, mk.nama_mukim asc ";
					//Susunan di ubah ke mukim,lot,subjaket
					//sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
					*/
							" SELECT COUNT (*) AS TOTAL FROM TBLPPTPERMOHONAN P, TBLPPTHAKMILIK M, TBLPPTBORANGL BL " +
							"WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND M.ID_HAKMILIK = BL.ID_HAKMILIK(+) " +
							"AND NVL (M.FLAG_PEMBATALAN_KESELURUHAN, 0) <> 'Y' " +
							"AND NVL (M.FLAG_PENARIKAN_KESELURUHAN, 0) <> 'Y' " +
							"AND (P.FLAG_SEGERA = '3' " +
							"AND M.ID_HAKMILIK IN (" +
							"SELECT DISTINCT HX.ID_HAKMILIK " +
							"FROM TBLPPTHAKMILIK HX, " +
							"TBLPPTHAKMILIKPB HPBX, " +
							"TBLPPTBAYARAN BX " +
							"WHERE HPBX.ID_HAKMILIK = HX.ID_HAKMILIK " +
							"AND BX.ID_HAKMILIKPB = HPBX.ID_HAKMILIKPB " +
							"AND BX.CARA_BAYAR IN ('1', '2') " +
							"AND HX.FLAG_SEGERA_SEBAHAGIAN = 'N' " +
							"AND HX.ID_PERMOHONAN = '"+idPermohonan+"' ) OR " +
							"P.FLAG_SEGERA = '3' " +
							"AND M.ID_HAKMILIK IN ( " +
							"SELECT DISTINCT HX.ID_HAKMILIK " +
							"FROM TBLPPTHAKMILIK HX " +
							"WHERE HX.FLAG_SEGERA_SEBAHAGIAN = 'Y'  AND HX.ID_PERMOHONAN = '16141246' ) " +
							"AND (SELECT COUNT (*) " +
							"FROM TBLPPTBORANGI BIX" +
							" WHERE BIX.ID_PERMOHONAN = '"+idPermohonan+"') > 0 " +
							"OR FLAG_SEGERA = '2' " +
							"AND (SELECT COUNT (*) " +
							"FROM TBLPPTBAYARAN A, TBLPPTHAKMILIKPB B, TBLPPTHAKMILIK C " +
							"WHERE A.ID_HAKMILIKPB = B.ID_HAKMILIKPB " +
							"AND B.ID_HAKMILIK = C.ID_HAKMILIK AND C.ID_PERMOHONAN = '16141246') > 0 " +
							"OR FLAG_SEGERA = '1' " +
							"AND (SELECT COUNT (*) " +
							"FROM TBLPPTBORANGI BIX " +
							"WHERE BIX.ID_PERMOHONAN =  '"+idPermohonan+"') > 0) " +
							"AND P.ID_PERMOHONAN = '"+idPermohonan+"'";
					
					myLogger.info("LIST COUNT KKKKKKKK :"+sql);
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
	}//close setListHMBorangK 
	 

	public static void simpanBorangL(String idUser,String id_hakmilik,long id_borangl, String dateBorangL, String statusBL,String tempoh) throws Exception{
		
	    Db db = null;
	    String sql = "";

	    try{
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.add("id_borangl", id_borangl);
	 	    r.add("id_hakmilik", id_hakmilik);
	 	    r.add("tempoh", tempoh);
	 	    r.add("tarikh_borangl",r.unquote("to_date('"+dateBorangL+"','dd/MM/yyyy')"));
	 	    r.add("jenis_pilih",statusBL);    
	 	    r.add("id_masuk",idUser);   
	 	    r.add("tarikh_masuk",r.unquote("sysdate"));
	 	    sql = r.getSQLInsert("tblpptborangl");
	 	    stmt.executeUpdate(sql);
	 	    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanBorangL
	
	public static void updateBorangL(String idUser,String id_hakmilik,String id_borangl, String dateBorangL, String statusBL,String tempoh) throws Exception{
		
	    Db db = null;
	    String sql = "";

	    try{
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	 	    SQLRenderer r = new SQLRenderer();
	 	    r.update("id_borangl", id_borangl);
	 	    r.update("id_hakmilik", id_hakmilik);
	 	    r.add("tarikh_borangl",r.unquote("to_date('"+dateBorangL+"','dd/MM/yyyy')"));
	 	    r.add("jenis_pilih",statusBL);  
	 	    r.add("tempoh",tempoh); 
	 	    r.add("id_kemaskini",idUser);   
	 	    r.add("tarikh_kemaskini",r.unquote("sysdate"));
	 	    sql = r.getSQLUpdate("tblpptborangl");
	 	    stmt.executeUpdate(sql);
	 	    
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateBorangL

}//close class
