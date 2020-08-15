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
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import ekptg.view.ppt.NO_FAIL_PROJEK_SA;
//import ekptg.view.ppt.TARIKH_WARTA_SA;

public class FrmSek8PermintaanUkurData {
	static Logger myLogger = Logger.getLogger(FrmSek8PermintaanUkurData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static Vector listcarian = null;
	private static Vector dataPermintaanUkur = null;
	private static Vector dataDetailPB = null;
	private static Vector jumlahPelarasanAP = null;
	private static Vector listNoPelan = null;
	private static Vector dataSiasatan = null;
	private static Vector listDokumen = null;
	private static Vector dataMaklumatPU = null;
	
	
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public Vector getListDokumen(){
		return listDokumen;
	}
	public Vector getDataPermintaanUkur(){
		return dataPermintaanUkur;
	}
	public Vector getDataDetailPB(){
		return dataDetailPB;
	}
	public Vector getJumlahPelarasanAP(){
		return jumlahPelarasanAP;
	}
	public Vector getListNoPelan(){
		return listNoPelan;
	}
	public Vector getDataSiasatan(){
		return dataSiasatan;
	}
	public Vector getDataMaklumatPU(){
		return dataMaklumatPU;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (76,82) ";
		
		sql += " and ((p.flag_segera = '1' and p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblppthakmilikpb hpbx, Tblpptbayaran bx ";
		sql += " where hx.id_permohonan = p.id_permohonan and hpbx.id_hakmilik = hx.id_hakmilik and bx.id_hakmilikpb = hpbx.id_hakmilikpb ";
		sql += " and bx.cara_bayar in ('1','2')))  ";
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptpermintaanukur pux "; 
		sql += " where hx.id_permohonan = p.id_permohonan and pux.id_hakmilik = hx.id_hakmilik) ";                        
		sql += " OR p.id_permohonan in (select distinct bkx.id_permohonan from Tblpptborangk bkx "; 
		sql += " where bkx.id_permohonan = p.id_permohonan))  ";  
		
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
		    		sql +="AND p.id_status IN (76,82) ";
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
	    		sql +="AND p.id_status IN (76,82) ";
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
	public void setDataPermintaanUkur(String idHakmilik) throws Exception{
		
		dataPermintaanUkur = new Vector();
		
		Db db = null;
		dataPermintaanUkur.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT DISTINCT A.ID_HAKMILIK, B.ID_PERMINTAANUKUR, B.NO_PU, B.TARIKH_SURAT_PTG, B.NO_HAKMILIK_SAMBUNG,";
				sql += " B.TARIKH_HANTAR_JUPEM, B.TARIKH_PU, B.TARIKH_TERIMA_PA, B.NO_PA, B.CATATAN, B.LUAS_AMBIL_SAMBUNG, ";
				sql += " B.TARIKH_TERIMA_SA, B.TARIKH_SELESAI, B.LUAS_PU, B.BEZA_LUAS, B.JENIS_PELARASAN, ";
				sql += " B.BIL_SURAT, B.TARIKH_SURAT_SUSULAN, B.TEMPOH_AKHIR_CEK, B.TARIKH_TERIMA_AGENSI, A.NO_SUBJAKET, B.NO_LOT_BARU, B.TARIKH_BAYARAN_TAMBAHAN ";
				sql += " FROM TBLPPTHAKMILIK A, TBLPPTPERMINTAANUKUR B ";
				sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
				sql += " AND B.ID_HAKMILIK = '"+idHakmilik+"'";
				
				myLogger.info("SQ MAKLUMAT PU : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("no_lot_baru", rs.getString("NO_LOT_BARU")==null?"":rs.getString("NO_LOT_BARU"));
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					h.put("no_subjaket", rs.getString("NO_SUBJAKET")==null?"":rs.getString("NO_SUBJAKET"));
					h.put("id_permintaanukur", rs.getString("ID_PERMINTAANUKUR")==null?"":rs.getString("ID_PERMINTAANUKUR"));
					//h.put("no_pelan", rs.getString("NO_PELAN")==null?"":rs.getString("NO_PELAN"));
					h.put("no_pu", rs.getString("NO_PU")==null?"":rs.getString("NO_PU"));
					h.put("tarikh_surat_ptg", rs.getDate("TARIKH_SURAT_PTG")==null?"":Format.format(rs.getDate("TARIKH_SURAT_PTG")));
					h.put("tarikh_hantar_jupem", rs.getDate("TARIKH_HANTAR_JUPEM")==null?"":Format.format(rs.getDate("TARIKH_HANTAR_JUPEM")));
					h.put("tarikh_pu", rs.getDate("TARIKH_PU")==null?"":Format.format(rs.getDate("TARIKH_PU")));
					h.put("tarikh_terima_pa", rs.getDate("TARIKH_TERIMA_PA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_PA")));
					h.put("tarikh_terima_sa", rs.getDate("TARIKH_TERIMA_SA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_SA")));
					h.put("tarikh_selesai", rs.getDate("TARIKH_SELESAI")==null?"":Format.format(rs.getDate("TARIKH_SELESAI")));
					h.put("no_pa", rs.getString("NO_PA")==null?"":rs.getString("NO_PA"));
					h.put("catatan", rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
					h.put("jenis_pelarasan", rs.getString("JENIS_PELARASAN")==null?"":rs.getString("JENIS_PELARASAN"));
					h.put("no_hakmilik_sambung", rs.getString("NO_HAKMILIK_SAMBUNG")==null?"":rs.getString("NO_HAKMILIK_SAMBUNG"));
					h.put("luas_pu", rs.getString("LUAS_PU")==null?"":Utils.formatLuasHM(rs.getDouble("LUAS_PU")));
					h.put("luas_ambil_sambung", rs.getString("LUAS_AMBIL_SAMBUNG")==null?"":Utils.formatLuasHM(rs.getDouble("LUAS_AMBIL_SAMBUNG")));
					h.put("beza_luas", rs.getString("BEZA_LUAS")==null?"":Utils.formatLuasHM(rs.getDouble("BEZA_LUAS")));
					h.put("tarikh_surat_susulan", rs.getDate("TARIKH_SURAT_SUSULAN")==null?"":Format.format(rs.getDate("TARIKH_SURAT_SUSULAN")));
					h.put("tarikh_terima_agensi", rs.getDate("TARIKH_TERIMA_AGENSI")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_AGENSI")));
					h.put("bil_surat", rs.getString("BIL_SURAT")==null?"":rs.getString("BIL_SURAT"));
					h.put("tempoh_akhir_cek", rs.getString("TEMPOH_AKHIR_CEK")==null?"":rs.getString("TEMPOH_AKHIR_CEK"));
					h.put("tarikh_bayaran_tambahan", rs.getDate("TARIKH_BAYARAN_TAMBAHAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN_TAMBAHAN")));
					
					if(rs.getString("JENIS_PELARASAN") != null){
						
						if(rs.getString("JENIS_PELARASAN").equals("1")){
							h.put("lblBezaLuas","TERLEBIH");
						}else if(rs.getString("JENIS_PELARASAN").equals("2")){
							h.put("lblBezaLuas","KURANG");
						}else if(rs.getString("JENIS_PELARASAN").equals("3")){
							h.put("lblBezaLuas","SEMPURNA");
						}else{
							h.put("lblBezaLuas","");
						}
						
					}else{
	    				h.put("lblBezaLuas","");
	    			}
					
					
					dataPermintaanUkur.addElement(h);	
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setDataPermintaanUkur
	
	@SuppressWarnings("unchecked")
	public static void simpanMaklumatPU(Hashtable data) throws Exception
	  {
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");	
	    		
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_permohonan = (String)data.get("id_permohonan");
	   		
	    		String txdTarikhSuratPTG = (String)data.get("txdTarikhSuratPTG");	 
	    		String txtNoPelan = (String)data.get("txtNoPelan");	 
	    		String txdTarikhHantarJUPEM = (String)data.get("txdTarikhHantarJUPEM");	 
	    		String txtNoPU = (String)data.get("txtNoPU");	 
	    		String txdTarikhBorangPU = (String)data.get("txdTarikhBorangPU");	 
	    		
	    		String TSP = "to_date('" + txdTarikhSuratPTG + "','dd/MM/yyyy')";
	    		String THJ = "to_date('" + txdTarikhHantarJUPEM + "','dd/MM/yyyy')";
	    		String TBP = "to_date('" + txdTarikhBorangPU + "','dd/MM/yyyy')";
	    		
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");
	    		String txtLotBaru = (String)data.get("txtLotBaru");
	    		String txtLuasLotAmbil = (String)data.get("txtLuasLotAmbil");

	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permintaanukur",id_permintaanukur);
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("no_pu", txtNoPU);	
	    		r.add("tarikh_surat_ptg",r.unquote(TSP));
	    		r.add("tarikh_hantar_jupem",r.unquote(THJ));
	    		r.add("tarikh_pu",r.unquote(TBP));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("no_lot_baru", txtLotBaru);
	    		r.add("no_hakmilik_sambung", txtNoHakmilik);
	    		r.add("luas_ambil_sambung", txtLuasLotAmbil);
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptpermintaanukur");
	    		stmt.executeUpdate(sql);
    	
	    		if(txtNoPelan != ""){
	
	    			long id_nopelan = DB.getNextID("TBLPPTNOPELAN_SEQ");
	    			
	    			r.clear();
	    		
	    			r.add("id_nopelan", id_nopelan);
	    			r.add("id_permohonan", id_permohonan);	
	    			r.add("no_pelan", txtNoPelan);
	    			r.add("tarikh_masuk",r.unquote("sysdate"));
	    			r.add("id_masuk",id_user);    		
	    			sql = r.getSQLInsert("Tblpptnopelan");
	    			stmt.executeUpdate(sql);
	    		
	    			r.clear();
	    		
	    			r.add("id_permintaanukur",id_permintaanukur);
	    			r.add("id_nopelan", id_nopelan);
	    			r.add("tarikh_masuk",r.unquote("sysdate"));
	    			r.add("id_masuk",id_user);    		
	    			sql = r.getSQLInsert("Tblpptnopelanpu");
	    			stmt.executeUpdate(sql);
	    		
	    		}//close if nopelan != ""
	    		
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	  }//close simpanMaklumatPU
	
	@SuppressWarnings("unchecked")
	public static void simpanNoPelanLain(Hashtable data, String id_nopelan) throws Exception{
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");
	    	
	    		SQLRenderer r = new SQLRenderer();
    			r.add("id_permintaanukur",id_permintaanukur);
    			r.add("id_nopelan", id_nopelan);
    			r.add("tarikh_masuk",r.unquote("sysdate"));
    			r.add("id_masuk",id_user);    		
    			sql = r.getSQLInsert("Tblpptnopelanpu");
    			stmt.executeUpdate(sql);
    	
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanNoPelanLain
	
	@SuppressWarnings("unchecked")
	public static void deleteNoPelanLain(Hashtable data) throws Exception{
		
	    Db db = null;
	   
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");	    	
	    		String sql = "DELETE FROM TBLPPTNOPELANPU WHERE ID_PERMINTAANUKUR = '"+id_permintaanukur+"' ";
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close deleteNoPelanLain
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatPU(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String txdTarikhSuratPTG = (String)data.get("txdTarikhSuratPTG");	 
	    		String txtNoPelan = (String)data.get("txtNoPelan");	 
	    		String txdTarikhHantarJUPEM = (String)data.get("txdTarikhHantarJUPEM");	 
	    		String txtNoPU = (String)data.get("txtNoPU");	 
	    		String txdTarikhBorangPU = (String)data.get("txdTarikhBorangPU");
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");	
	    		String txtLotBaru = (String)data.get("txtLotBaru");	
	    		String luas_ambil_sambung = (String)data.get("txtLuasAmbil");	
	    		String TSP = "to_date('" + txdTarikhSuratPTG + "','dd/MM/yyyy')";
	    		String THJ = "to_date('" + txdTarikhHantarJUPEM + "','dd/MM/yyyy')";
	    		String TBP = "to_date('" + txdTarikhBorangPU + "','dd/MM/yyyy')";
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permintaanukur", id_permintaanukur);
	    		//r.add("no_pelan", txtNoPelan);
	    		r.add("no_pu", txtNoPU);	
	    		r.add("tarikh_surat_ptg",r.unquote(TSP));
	    		r.add("tarikh_hantar_jupem",r.unquote(THJ));
	    		r.add("tarikh_pu",r.unquote(TBP));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user); 
	    		r.add("no_hakmilik_sambung", txtNoHakmilik);	
	    		r.add("no_lot_baru", txtLotBaru);	
	    		r.add("luas_ambil_sambung", luas_ambil_sambung);
	    		sql = r.getSQLUpdate("Tblpptpermintaanukur");
	    		stmt.executeUpdate(sql);

	    		if(txtNoPelan != ""){
	    			
	    			long id_nopelan = DB.getNextID("TBLPPTNOPELAN_SEQ");
	    			
	    			r.clear();
	    		
	    			r.add("id_nopelan", id_nopelan);
	    			r.add("id_permohonan", id_permohonan);	
	    			r.add("no_pelan", txtNoPelan);
	    			r.add("tarikh_masuk",r.unquote("sysdate"));
	    			r.add("id_masuk",id_user);    		
	    			sql = r.getSQLInsert("Tblpptnopelan");
	    			stmt.executeUpdate(sql);
	    		
	    			r.clear();
	    		
	    			r.add("id_permintaanukur",id_permintaanukur);
	    			r.add("id_nopelan", id_nopelan);
	    			r.add("tarikh_masuk",r.unquote("sysdate"));
	    			r.add("id_masuk",id_user);    		
	    			sql = r.getSQLInsert("Tblpptnopelanpu");
	    			stmt.executeUpdate(sql);
	    		
	    		}//close if nopelan != ""
	    		
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updateMaklumatPU
	
	@SuppressWarnings("unchecked")
	public static void updatePenyelesaian(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");
	   		
	    		String txdTarikhTerimaPA = (String)data.get("txdTarikhTerimaPA");	 
	    		String txtNoPA = (String)data.get("txtNoPA");	 
	    		String txdTarikhTerimaSA = (String)data.get("txdTarikhTerimaSA");	 
	    		String txdTarikhPenyelesaian = (String)data.get("txdTarikhPenyelesaian");	 
	    		String txtCatatan = (String)data.get("txtCatatan");	 
	    		String txtLotBaru = (String)data.get("txtLotBaru");	 
	    		
	    		String TTP = "to_date('" + txdTarikhTerimaPA + "','dd/MM/yyyy')";
	    		String TTS = "to_date('" + txdTarikhTerimaSA + "','dd/MM/yyyy')";
	    		String TP = "to_date('" + txdTarikhPenyelesaian + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permintaanukur", id_permintaanukur);
	    		r.add("no_pa", txtNoPA);
	    		r.add("no_lot_baru", txtLotBaru);
	    		r.add("catatan", txtCatatan);	
	    		r.add("tarikh_terima_pa",r.unquote(TTP));
	    		r.add("tarikh_terima_sa",r.unquote(TTS));
	    		r.add("tarikh_selesai",r.unquote(TP));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptpermintaanukur");
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePenyelesaian
	  
	@SuppressWarnings("unchecked")
	public static void updatePelarasanLuas(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");
	   		
	    		String txtKeluasanPU = (String)data.get("txtKeluasanPU");	 
	    		String sorJenisPelarasan = (String)data.get("sorJenisPelarasan");	 
	    		String countBezaLuas = (String)data.get("countBezaLuas");	 
	    		String tarikhBayarTambahan = (String)data.get("tarikhBayarTambahan");	 
	    		
	    		String TP = "to_date('" + tarikhBayarTambahan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permintaanukur", id_permintaanukur);
	    		r.add("luas_pu", txtKeluasanPU);
	    		r.add("beza_luas", countBezaLuas);	
	    		r.add("jenis_pelarasan",sorJenisPelarasan);
	    		r.add("tarikh_bayaran_tambahan",r.unquote(TP));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);   
	    		
	    		
	    		
	    		sql = r.getSQLUpdate("Tblpptpermintaanukur");
	    		
	    		myLogger.info("updatePelarasanLuas :::::::::"+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePelarasanLuas
	
	@SuppressWarnings("unchecked")
	  public static void setDataDetailPB(String id_hakmilikpb)throws Exception {
			
		dataDetailPB = new Vector();
			
		    Db db = null;
		    dataDetailPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = " SELECT DISTINCT f.id_award, b.id_hakmilikpb, b.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, b.id_jenispb, a.id_bangsa, a.id_warganegara, "; 
		    		sql += " b.id_negeri, b.id_bandar, a.no_pb, b.jenis_lain2_pb, g.syer_atas, g.syer_bawah, b.alamat1, b.alamat2, b.alamat3, b.poskod, ";
		    		sql += " b.no_tel_rumah, b.no_handphone, b.no_fax, c.keterangan as jenis_nopb, d.keterangan as jenis_pb, e.tarikh_akhir_bayaragensi, f.penama, ";
		    		sql += " f.ulasan, f.tarikh_sedia_bayaran, f.pampasan_tanah, f.jumlah_pelarasan, f.tempoh_lewat, f.faedah_sebelum_pu, ";
		    		sql += " f.faedah_selepas_pu, f.tarikh_surat, f.bil_surat, f.tempoh_akhir_bayar_semula, f.status_bayar ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilikpb b, Tblrujjenisnopb c, Tblrujjenispb d, Tblpptborangh e, Tblpptaward f, ";
		    		sql += " tblpptbahagianpb g ";
		    		sql += " WHERE b.id_pihakberkepentingan = a.id_pihakberkepentingan(+) ";
		    		sql += " AND a.id_jenisnopb = c.id_jenisnopb(+)";
		    		sql += " AND b.id_jenispb = d.id_jenispb(+)";
		    		sql += " AND b.id_bahagianpb = g.id_bahagianpb(+)";
		    		sql += " AND e.id_hakmilikpb(+) = b.id_hakmilikpb";
		    		sql += " AND f.id_hakmilikpb(+) = b.id_hakmilikpb";
		    		sql += " AND b.id_hakmilikpb = '"+id_hakmilikpb+"'";		    		
		    		myLogger.info("SELECT PB ::::::"+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			
		    			h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")== null?"":rs.getString("id_hakmilikpb"));
		    			h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")== null?"":rs.getString("id_pihakberkepentingan"));
		    			h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));
		    			h.put("syer_atas", rs.getString("syer_atas")== null?"":rs.getString("syer_atas"));
		    			h.put("syer_bawah", rs.getString("syer_bawah")== null?"":rs.getString("syer_bawah"));
		    			h.put("jenis_nopb", rs.getString("jenis_nopb")== null?"":rs.getString("jenis_nopb"));
		    			h.put("jenis_pb", rs.getString("jenis_pb")== null?"":rs.getString("jenis_pb"));
		    			h.put("tarikh_akhir_bayaragensi", rs.getDate("tarikh_akhir_bayaragensi")==null?"":Format.format(rs.getDate("tarikh_akhir_bayaragensi")));
		    			
		    			h.put("id_award", rs.getString("id_award")== null?"":rs.getString("id_award"));
		    			h.put("penama", rs.getString("penama")== null?"":rs.getString("penama"));
		    			h.put("tarikh_sedia_bayaran", rs.getDate("tarikh_sedia_bayaran")==null?"":Format.format(rs.getDate("tarikh_sedia_bayaran")));
		    			h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    			h.put("pampasan_tanah", rs.getString("pampasan_tanah")== null?0:rs.getDouble("pampasan_tanah"));
		    			h.put("jumlah_pelarasan", rs.getString("jumlah_pelarasan")== null?0:rs.getDouble("jumlah_pelarasan"));
		    			h.put("tempoh_lewat", rs.getString("tempoh_lewat")== null?"":rs.getString("tempoh_lewat"));
		    			h.put("faedah_sebelum_pu", rs.getString("faedah_sebelum_pu")== null?0:rs.getDouble("faedah_sebelum_pu"));
		    			h.put("faedah_selepas_pu", rs.getString("faedah_selepas_pu")== null?0:rs.getDouble("faedah_selepas_pu"));
		    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
		    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));
		    			h.put("tempoh_akhir_bayar_semula", rs.getString("tempoh_akhir_bayar_semula")== null?"":rs.getString("tempoh_akhir_bayar_semula"));
		    			h.put("status_bayar", rs.getString("status_bayar")== null?"":rs.getString("status_bayar"));
		    			dataDetailPB.addElement(h);
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setDataDetailPB
	
	@SuppressWarnings("unchecked")
	public static void updatePelarasanPB_auto(String id_user,String id_award,String txtPenama,String txtPampasanTanah,String txtTempoh,String txtJumlahPelarasan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_award", id_award);
	    		r.add("penama", txtPenama);	    	
	    		r.add("tempoh_lewat", txtTempoh);
	    		r.add("pampasan_tanah", txtPampasanTanah);	
	    		r.add("jumlah_pelarasan", txtJumlahPelarasan);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    
	    		sql = r.getSQLUpdate("Tblpptaward");
	    		myLogger.info("SAVE AWARD AUTO :"+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePelarasanPB
	
	
	@SuppressWarnings("unchecked")
	public static void updatePelarasanPBStandalone_auto(String id_user,String id_award,String txtPenama,String txtPampasanTanah,String txtTempoh,String txtJumlahPelarasan) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_pupb", id_award);
	    		r.add("penama", txtPenama);	    	
	    		r.add("tempoh_lewat", txtTempoh);
	    		r.add("pampasan_tanah", txtPampasanTanah);	
	    		r.add("jumlah_pelarasan", txtJumlahPelarasan);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    
	    		sql = r.getSQLUpdate("Tblpptpupb");
	    		myLogger.info("SAVE AWARD AUTO :"+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePelarasanPB
	
	
	
	@SuppressWarnings("unchecked")
	public static void updatePelarasanPB(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_award = (String)data.get("id_award");
	   		
	    		String txtPenama = (String)data.get("txtPenama");	 
	    		String txtUlasan = (String)data.get("txtUlasan");	 
	    		String txdTarikhSedia = (String)data.get("txdTarikhSedia");	 
	    		
	    		String txtPampasanTanah = (String)data.get("txtPampasanTanah");	 
	    		String txtFaedahSebelum = (String)data.get("txtFaedahSebelum");	 
	    		String txtFaedahSelepas = (String)data.get("txtFaedahSelepas");	
	    		
	    		String txtJumlahPelarasan = (String)data.get("txtJumlahPelarasan");	 
	    		String txtTempoh = (String)data.get("txtTempoh");	
	    		
	    		String TSB = "to_date('" + txdTarikhSedia + "','dd/MM/yyyy')";

	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_award", id_award);
	    		r.add("penama", txtPenama);
	    		r.add("ulasan", txtUlasan);	
	    		r.add("tempoh_lewat", txtTempoh);
	    		r.add("pampasan_tanah", txtPampasanTanah);	
	    		r.add("jumlah_pelarasan", txtJumlahPelarasan);	
	    		r.add("faedah_sebelum_pu", txtFaedahSebelum);	
	    		r.add("faedah_selepas_pu", txtFaedahSelepas);
	    		r.add("tarikh_sedia_bayaran",r.unquote(TSB));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    
	    		sql = r.getSQLUpdate("Tblpptaward");
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePelarasanPB
	
	
	@SuppressWarnings("unchecked")
	public static void updateSusulan(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_permintaanukur = (String)data.get("id_permintaanukur");
	   		
	    		String txtBilSurat = (String)data.get("txtBilSurat");	 
	    		String txdTarikhSurat = (String)data.get("txdTarikhSurat");	 
	    		String txdTarikhTerimaAgensi = (String)data.get("txdTarikhTerimaAgensi");	 
	    		String txtTempohCek = (String)data.get("txtTempohCek");	 
	    		String txdTarikhBayaranTambahan = (String)data.get("txdTarikhBayaranTambahan");	 
	    		
	    		String TS = "to_date('" + txdTarikhSurat + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTerimaAgensi + "','dd/MM/yyyy')";
	    		String TBT = "to_date('" + txdTarikhBayaranTambahan + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permintaanukur", id_permintaanukur);
	    		r.add("bil_surat", txtBilSurat);
	    		r.add("tempoh_akhir_cek", txtTempohCek);	
	    		r.add("tarikh_surat_susulan",r.unquote(TS));
	    		r.add("tarikh_terima_agensi",r.unquote(TT));
	    		r.add("tarikh_bayaran_tambahan",r.unquote(TBT));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptpermintaanukur");
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updateSusulan
	
	@SuppressWarnings("unchecked")
	public static void updatePBSusulan(Hashtable data) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String id_user = (String)data.get("id_user");	    	    		
	    		String id_award = (String)data.get("id_award");
	   		
	    		String txtBilSuratPB = (String)data.get("txtBilSuratPB");	 
	    		String txdTarikhSuratPB = (String)data.get("txdTarikhSuratPB");	 
	    		String txtTempohBayar = (String)data.get("txtTempohBayar");	 
	    		String sorStatusBayaran = (String)data.get("sorStatusBayaran");	 
	    		
	    		String TSB = "to_date('" + txdTarikhSuratPB + "','dd/MM/yyyy')";

	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_award", id_award);
	    		r.add("bil_surat", txtBilSuratPB);
	    		r.add("tempoh_akhir_bayar_semula", txtTempohBayar);	
	    		r.add("status_bayar", sorStatusBayaran);
	    		r.add("tarikh_surat",r.unquote(TSB));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptaward");
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePBSusulan
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data,String idpermohonan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		
	    		//status pu
	    		String status = "82";
	    		
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
	  public static void setJumlahPelarasanAP(String id_hakmilik)throws Exception {
			
		jumlahPelarasanAP = new Vector();
			
		    Db db = null;
		    jumlahPelarasanAP.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT SUM(C.JUMLAH_PELARASAN)AS TOTAL_BAYAR ";
		    		sql += " FROM TBLPPTHAKMILIK A, TBLPPTSIASATAN B, TBLPPTAWARD C ";
		    		sql += " WHERE B.ID_HAKMILIK = A.ID_HAKMILIK ";
		    		sql += " AND C.ID_SIASATAN = B.ID_SIASATAN ";
		    		sql += " AND B.ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN S WHERE A.ID_HAKMILIK = S.ID_HAKMILIK) ";
					sql += " AND A.ID_HAKMILIK = '"+id_hakmilik+"'";		    		
			    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("total_bayar", rs.getString("TOTAL_BAYAR")== null?"":rs.getString("TOTAL_BAYAR"));
		    			jumlahPelarasanAP.addElement(h);
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setJumlahPelarasanAP
	
	@SuppressWarnings("unchecked")
	public void setListNoPelan(String id_permohonan,String id_hakmilik) throws Exception{
		
		listNoPelan = new Vector();
		
		Db db = null;
		listNoPelan.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT DISTINCT C.ID_NOPELAN,C.NO_PELAN, ";
				sql += " (SELECT COUNT(*) FROM TBLPPTPERMINTAANUKUR A1, TBLPPTNOPELANPU B1, TBLPPTNOPELAN C1 ";
				sql += " WHERE B1.ID_PERMINTAANUKUR = A1.ID_PERMINTAANUKUR ";
				sql += " AND B1.ID_NOPELAN = C1.ID_NOPELAN ";
				sql += " AND C1.ID_NOPELAN = C.ID_NOPELAN ";
				sql += " AND A1.ID_HAKMILIK = '"+id_hakmilik+"')AS FLAG ";
				sql += " FROM TBLPPTNOPELAN C ";
				sql += " WHERE C.ID_PERMOHONAN = '"+id_permohonan+"'"; 
				sql += " ORDER BY C.NO_PELAN ";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("flag", rs.getString("FLAG")==null?"":rs.getString("FLAG"));
					h.put("id_nopelan", rs.getString("ID_NOPELAN")==null?"":rs.getString("ID_NOPELAN"));
					h.put("no_pelan", rs.getString("NO_PELAN")==null?"":rs.getString("NO_PELAN"));
					listNoPelan.addElement(h);	
					bil++;
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setListNoPelan
	
	public static void hapusNoPelan(String id_nopelan) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		
	    		sql = "DELETE FROM TBLPPTNOPELANPU WHERE ID_NOPELAN = '"+id_nopelan+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		sql = "DELETE FROM TBLPPTNOPELAN WHERE ID_NOPELAN = '"+id_nopelan+"'";
	    		stmt.executeUpdate(sql);
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close hapusNoPelan
	
	
	@SuppressWarnings("unchecked")
	public void setDataSiasatan(String idHakmilik) throws Exception{
		
		dataSiasatan = new Vector();
		
		Db db = null;
		dataSiasatan.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = "SELECT C.NILAI_SEUNIT, C.UNIT_TANAH ";
				sql += " FROM TBLPPTHAKMILIK A, TBLPPTSIASATAN C ";
				sql += " WHERE C.ID_HAKMILIK = A.ID_HAKMILIK ";
				sql += " AND C.ID_SIASATAN = (SELECT MAX(ID_SIASATAN) FROM TBLPPTSIASATAN WHERE A.ID_HAKMILIK = TBLPPTSIASATAN.ID_HAKMILIK) ";
				sql += " AND A.ID_HAKMILIK = '"+idHakmilik+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("NILAI_SEUNIT", rs.getString("NILAI_SEUNIT")==null?0:rs.getDouble("NILAI_SEUNIT"));
					h.put("UNIT_TANAH", rs.getString("UNIT_TANAH")==null?"":rs.getString("UNIT_TANAH"));
					dataSiasatan.addElement(h);	
				}
	
		}finally {
			if(db != null) db.close();
		}
	}//close setDataSiasatan
	
	
	//view list dokumen by id
	@SuppressWarnings("unchecked")
	public void setListDokumen(String id_permintaanukur) throws Exception {
		    
		listDokumen = new Vector();
		listDokumen.clear();
		Db db = null;
		String sql = "";
		    
			try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		sql =  " SELECT DISTINCT ID_DOKUMEN,NAMA_FAIL,JENIS_MIME,TAJUK,KETERANGAN ";
		    		sql += " FROM TBLPPTDOKUMEN ";
		    		sql += " WHERE ID_PERMINTAANUKUR = '"+id_permintaanukur+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		     
		    		Hashtable h;
		    		int bil = 1;
		    
		    		while (rs.next()) {
		    	  
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("id_dokumen", rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
		    			h.put("nama_fail", rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
		    	  		h.put("jenis_mime",rs.getString("JENIS_MIME")== null?"":rs.getString("JENIS_MIME"));
		    	  		h.put("tajuk",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
		    	  		h.put("keterangan",rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
		    	  		listDokumen.addElement(h);
		    	  		bil++;	    	
		    		}			    
		      
		    	} finally {
		    		if (db != null) db.close();
		    	}
		    	
	}//close setListDokumen
	
	@SuppressWarnings("unchecked")
	public static String simpanPUStandAlone(Hashtable data,String userIdNeg) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    String output="";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		long id_pu = DB.getNextID("TBLPPTPU_SEQ");    
	    		
	    		String txtNoFailPUJUPEM = (String)data.get("txtNoFailPUJUPEM");
	    		String txtNoFailProjek = (String)data.get("txtNoFailProjek");
	    		String txtTujuanProjek = (String)data.get("txtTujuanProjek");
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    		String txtNoWarta = (String)data.get("txtNoWarta");
	    		String txtLuasBorangK = (String)data.get("txtLuasBorangK");
	    		String sorDropdownUnitBorangK = (String)data.get("sorDropdownUnitBorangK");
	    		String txtNoWK = (String)data.get("txtNoWK");
	    		String txtNoPAasal = (String)data.get("txtNoPAasal");
	    		
	    		String txtNoFailPU = (String)data.get("txtNoFailPU");	
	    		
	    		//data hakmilik
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");	
	    		String txtNoLot = (String)data.get("txtNoLot");
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");
	    		String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String sorDropdownUnit = (String)data.get("sorDropdownUnit");
	    		
	    		String txtLuasAsal = (String)data.get("txtLuasAsal");
	    		String sorDropdownUnitAsal = (String)data.get("sorDropdownUnitAsal");
	    		
	    		String socJenisHakmilik = (String)data.get("socJenisHakmilik");
	    		String socMukim = (String)data.get("socMukim");
	    		String socDaerah = (String)data.get("socDaerah");
	    		
	    		String txdTarikhSuratPTG = (String)data.get("txdTarikhSuratPTG");	 
	    		String txtNoPelan = (String)data.get("txtNoPelan");	 
	    		String txdTarikhHantarJUPEM = (String)data.get("txdTarikhHantarJUPEM");	 
	    		String txtNoPU = (String)data.get("txtNoPU");	 
	    		String txdTarikhBorangPU = (String)data.get("txdTarikhBorangPU");	 
	    		
	    		String txdTarikhTerimaPA = (String)data.get("txdTarikhTerimaPA");	 
	    		String txtNoPA = (String)data.get("txtNoPA");	 
	    		String txdTarikhTerimaSA = (String)data.get("txdTarikhTerimaSA");	 
	    		String txdTarikhPenyelesaian = (String)data.get("txdTarikhPenyelesaian");	 
	    		String txtCatatan = (String)data.get("txtCatatan");	
	    		String txtLotBaru = (String)data.get("txtLotBaru");	
	    		
	    		String txtKeluasanPU = (String)data.get("txtKeluasanPU");	 
	    		String lblLuasAsal = (String)data.get("lblLuasAsal");	 
	    		String lblLuasAmbil = (String)data.get("lblLuasAmbil");	 
	    		String sorJenisPelarasan = (String)data.get("sorJenisPelarasan");	 
	    		String countBakiLuas = (String)data.get("countBakiLuas");	
	    		String countBezaLuas = (String)data.get("countBezaLuas");	
	    		
	    		
	    		
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String TSP = "to_date('" + txdTarikhSuratPTG + "','dd/MM/yyyy')";
	    		String THJ = "to_date('" + txdTarikhHantarJUPEM + "','dd/MM/yyyy')";
	    		String TBP = "to_date('" + txdTarikhBorangPU + "','dd/MM/yyyy')";
	    		
	    		String TTP = "to_date('" + txdTarikhTerimaPA + "','dd/MM/yyyy')";
	    		String TTS = "to_date('" + txdTarikhTerimaSA + "','dd/MM/yyyy')";
	    		String TP = "to_date('" + txdTarikhPenyelesaian + "','dd/MM/yyyy')";
	    		
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		String TWK = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_pu",id_pu);
	    		
	    		r.add("no_hakmilik ", txtNoHakmilik);	
	    		r.add("no_lot", txtNoLot);	
	    		r.add("luas_ambil_hm", txtLuasAmbil);	    		
	    		r.add("id_unitluasambil", sorDropdownUnit);
	    		
	    		r.add("luas_asal_hm", txtLuasAsal);	    		
	    		r.add("id_unitluasasal", sorDropdownUnitAsal);
	    		
	    		r.add("NO_FAIL_JUPEM_SA", txtNoFailPUJUPEM);	
	    		r.add("NO_FAIL_PROJEK_SA", txtNoFailProjek);
	    		r.add("TUJUAN_PROJEK_SA", txtTujuanProjek);	
	    		r.add("TARIKH_WARTA_SA", r.unquote(TWK));	
	    		r.add("NO_WARTA_SA", txtNoWarta);	
	    		r.add("LUAS_BORANGK_SA ", txtLuasBorangK);	
	    		r.add("ID_UNITBORANGK_SA ", sorDropdownUnitBorangK);	
	    		r.add("NO_WK_SA", txtNoWK);	
	    		r.add("NO_PA_ASAL_SA", txtNoPAasal);	
	    		
	    		r.add("id_jenishakmilik", socJenisHakmilik);
	    		r.add("id_mukim", socMukim);	
	    		r.add("id_daerah", socDaerah);
	    		r.add("tarikh_borangk",r.unquote(TBK));
	    		
	    		r.add("no_fail_pu ", txtNoFailPU);	
	    		r.add("no_pu", txtNoPU);	
	    		r.add("no_pelan", txtNoPelan);	    		
	    		r.add("no_pa", txtNoPA);
	    		r.add("no_lot_baru", txtLotBaru);
	    		r.add("catatan", txtCatatan);	
	    		r.add("tarikh_terima_pa",r.unquote(TTP));
	    		r.add("tarikh_terima_sa",r.unquote(TTS));
	    		r.add("tarikh_selesai",r.unquote(TP));	    		
	    		r.add("luas_pu", txtKeluasanPU);    		
	    		r.add("luas_asal", lblLuasAsal);	
	    		r.add("luas_ambil", lblLuasAmbil);		    		
	    		r.add("beza_luas", countBezaLuas);	
	    		r.add("baki_luas", countBakiLuas);	
	    		r.add("jenis_pelarasan",sorJenisPelarasan);    		
	    		r.add("tarikh_surat_ptg",r.unquote(TSP));
	    		r.add("tarikh_hantar_jupem",r.unquote(THJ));
	    		r.add("tarikh_pu",r.unquote(TBP));
	    		r.add("id_negeri", userIdNeg);	
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("Tblpptpu");
	    		myLogger.info("INSERT PU:"+sql);
	    		stmt.executeUpdate(sql);
    	
	    		output = ""+id_pu;
	    		
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	    	return output;
	    	
	  }//close simpanMaklumatPU
	
	@SuppressWarnings("unchecked")
	public static void setDataMaklumatPU(String id_pu)throws Exception {
		
		dataMaklumatPU = new Vector();
		
	    Db db = null;
	    dataMaklumatPU.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT  A.TARIKH_BAYARAN_TAMBAHAN, TO_CHAR(A.NILAI_METERPERSEGI) AS NILAI_SEUNIT, "+
						" TRIM(TO_CHAR(A.NILAI_METERPERSEGI,'999,999,999,990.99')) AS RM_NILAI_SEUNIT, "+
	    				" A.NILAI_METERPERSEGI,A.NO_HAKMILIK, A.NO_LOT, A.TARIKH_BORANGK, " +
	    				" TRIM(TO_CHAR(A.LUAS_AMBIL_HM,'999999999999990.9999')) AS LUAS_AMBIL_HM," +
	    				"  A.ID_UNITLUASASAL, " +
	    				" TRIM(TO_CHAR(A.LUAS_ASAL_HM,'999999999999990.9999')) AS LUAS_ASAL_HM, A.ID_UNITLUASAMBIL, A.ID_DAERAH,A.ID_MUKIM,A.ID_JENISHAKMILIK,A.ID_PU, A.NO_FAIL_PU, A.NO_PU, A.NO_PELAN, A.NO_PA, A.NO_LOT_BARU, A.CATATAN, A.TARIKH_TERIMA_PA, A.TARIKH_TERIMA_SA, A.TARIKH_SELESAI, A.LUAS_PU, ";
	    		sql += " TRIM(TO_CHAR(A.LUAS_ASAL,'999999999999990.9999')) AS LUAS_ASAL, TRIM(TO_CHAR(A.LUAS_AMBIL,'999999999999990.9999')) AS LUAS_AMBIL, A.BEZA_LUAS, A.BAKI_LUAS, A.JENIS_PELARASAN, A.TARIKH_SURAT_PTG, A.TARIKH_HANTAR_JUPEM, A.TARIKH_PU," +
	    				"  A.NO_FAIL_JUPEM_SA,A.NO_FAIL_PROJEK_SA,A.TUJUAN_PROJEK_SA,A.NO_WARTA_SA, TO_CHAR(A.TARIKH_WARTA_SA, 'DD/MM/YYYY') AS TARIKH_WARTA_SA," +
	    				"  A.NO_WK_SA,A.NO_PA_ASAL_SA,TRIM(TO_CHAR(A.LUAS_BORANGK_SA,'999999999999990.9999')) AS LUAS_BORANGK_SA,TO_CHAR(A.ID_UNITBORANGK_SA) AS ID_UNITBORANGK_SA ";
	    		sql += " FROM TBLPPTPU A ";
	    		sql += " WHERE A.ID_PU ='"+id_pu+"' ";
	    		myLogger.info(" setDataMaklumatPU :"+sql);
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	    		
	    	
	    		

	    		
	    		
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			
	    			h.put("NO_FAIL_JUPEM_SA", rs.getString("NO_FAIL_JUPEM_SA")== null?"":rs.getString("NO_FAIL_JUPEM_SA"));
	    			h.put("NO_FAIL_PROJEK_SA", rs.getString("NO_FAIL_PROJEK_SA")== null?"":rs.getString("NO_FAIL_PROJEK_SA"));
	    			h.put("TUJUAN_PROJEK_SA", rs.getString("TUJUAN_PROJEK_SA")== null?"":rs.getString("TUJUAN_PROJEK_SA"));
	    			h.put("NO_WARTA_SA", rs.getString("NO_WARTA_SA")== null?"":rs.getString("NO_WARTA_SA"));
	    			h.put("TARIKH_WARTA_SA", rs.getString("TARIKH_WARTA_SA")== null?"":rs.getString("TARIKH_WARTA_SA"));
	    			h.put("NO_WK_SA", rs.getString("NO_WK_SA")== null?"":rs.getString("NO_WK_SA"));
	    			h.put("NO_PA_ASAL_SA", rs.getString("NO_PA_ASAL_SA")== null?"":rs.getString("NO_PA_ASAL_SA"));
	    			h.put("LUAS_BORANGK_SA", rs.getString("LUAS_BORANGK_SA")== null?"":rs.getString("LUAS_BORANGK_SA"));
	    			h.put("ID_UNITBORANGK_SA", rs.getString("ID_UNITBORANGK_SA")== null?"":rs.getString("ID_UNITBORANGK_SA"));
	    			
	    			h.put("nilai", rs.getString("RM_NILAI_SEUNIT")== null?"":"RM "+rs.getString("RM_NILAI_SEUNIT"));
	    			h.put("nilaiseunit", rs.getString("NILAI_SEUNIT")== null?"":rs.getString("NILAI_SEUNIT"));
	    			
	    			
	    			h.put("ID_DAERAH", rs.getString("ID_DAERAH")== null?"":rs.getString("ID_DAERAH"));
	    			h.put("ID_MUKIM", rs.getString("ID_MUKIM")== null?"":rs.getString("ID_MUKIM"));
	    			h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK")== null?"":rs.getString("ID_JENISHAKMILIK"));
	    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    			h.put("NO_LOT", rs.getString("NO_LOT")== null?"":rs.getString("NO_LOT"));
	    			h.put("LUAS_AMBIL_HM", rs.getString("LUAS_AMBIL_HM")== null?"":rs.getString("LUAS_AMBIL_HM"));
	    			h.put("ID_UNITLUASAMBIL", rs.getString("ID_UNITLUASAMBIL")== null?"":rs.getString("ID_UNITLUASAMBIL"));
	    			h.put("LUAS_ASAL_HM", rs.getString("LUAS_ASAL_HM")== null?"":rs.getString("LUAS_ASAL_HM"));
	    			h.put("ID_UNITLUASASAL", rs.getString("ID_UNITLUASASAL")== null?"":rs.getString("ID_UNITLUASASAL"));
	    			
	    			h.put("TARIKH_BORANGK", rs.getDate("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")));
	    			h.put("TARIKH_BAYARAN_TAMBAHAN", rs.getDate("TARIKH_BAYARAN_TAMBAHAN")==null?"":Format.format(rs.getDate("TARIKH_BAYARAN_TAMBAHAN")));
	    			
	    			h.put("NO_FAIL_PU", rs.getString("NO_FAIL_PU")== null?"":rs.getString("NO_FAIL_PU"));
	    			h.put("ID_PU", rs.getString("ID_PU")== null?"":rs.getString("ID_PU"));
	    			h.put("TARIKH_PU", rs.getDate("TARIKH_PU")==null?"":Format.format(rs.getDate("TARIKH_PU")));
	    			h.put("TARIKH_TERIMA_PA", rs.getDate("TARIKH_TERIMA_PA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_PA")));
	    			h.put("TARIKH_TERIMA_SA", rs.getDate("TARIKH_TERIMA_SA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_SA")));
	    			h.put("TARIKH_SELESAI", rs.getDate("TARIKH_SELESAI")==null?"":Format.format(rs.getDate("TARIKH_SELESAI")));
	    			h.put("TARIKH_SURAT_PTG", rs.getDate("TARIKH_SURAT_PTG")==null?"":Format.format(rs.getDate("TARIKH_SURAT_PTG")));
	    			h.put("TARIKH_HANTAR_JUPEM", rs.getDate("TARIKH_HANTAR_JUPEM")==null?"":Format.format(rs.getDate("TARIKH_HANTAR_JUPEM")));	    			
	    			h.put("JENIS_PELARASAN", rs.getString("JENIS_PELARASAN")== null?"":rs.getString("JENIS_PELARASAN"));	    			
	    			h.put("CATATAN", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
	    			h.put("NO_PU", rs.getString("NO_PU")== null?"":rs.getString("NO_PU"));
	    			h.put("NO_PELAN", rs.getString("NO_PELAN")== null?"":rs.getString("NO_PELAN"));
	    			h.put("NO_PA", rs.getString("NO_PA")== null?"":rs.getString("NO_PA"));
	    			h.put("NO_LOT_BARU", rs.getString("NO_LOT_BARU")== null?"":rs.getString("NO_LOT_BARU"));	    
	    			
	    			h.put("BAKI_LUAS", rs.getString("BAKI_LUAS")== null?"0":rs.getString("BAKI_LUAS"));
	    			h.put("BEZA_LUAS", rs.getString("BEZA_LUAS")== null?"0":rs.getString("BEZA_LUAS"));
	    			h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL")== null?"0":rs.getString("LUAS_AMBIL"));
	    			h.put("LUAS_ASAL", rs.getString("LUAS_ASAL")== null?"0":rs.getString("LUAS_ASAL"));
	    			h.put("LUAS_PU", rs.getString("LUAS_PU")== null?"0":rs.getString("LUAS_PU"));
	    			dataMaklumatPU.addElement(h);
	    			
	      }//close while
	      
	    }// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataMaklumatPU
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatPB(String id_user,String ID_PUPB,String id_pu,String txtNamaPBPU,String txtNoPBPU,String txtSyerAtasPU,String txtSyerBawahPU) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		    		
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		if(!ID_PUPB.equals(""))
	    		{
	    		r.update("ID_PUPB", ID_PUPB);
	    		}
	    		
	    		r.add("ID_PU", id_pu);	    	
	    		r.add("NAMA_PB", txtNamaPBPU);
	    		r.add("NO_PB", txtNoPBPU);	
	    		r.add("SYER_ATAS", txtSyerAtasPU);
	    		r.add("SYER_BAWAH",txtSyerBawahPU);
	    		
	    		
	    		if(!ID_PUPB.equals(""))
	    		{
	    			r.add("id_kemaskini",id_user);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    			sql = r.getSQLUpdate("TBLPPTPUPB");
	    		}
	    		else
	    		{
	    			r.add("id_kemaskini",id_user);
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);
	    			sql = r.getSQLInsert("TBLPPTPUPB");	
	    		}
	    		myLogger.info("SAVE AWARD AUTO :"+sql);
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	}//close updatePelarasanPB
	
	private static Vector listPBstandalone = null;
	
	@SuppressWarnings("unchecked")
	  public static void listPBstandalone(String id_pu)throws Exception {
			
		listPBstandalone = new Vector();
			
		    Db db = null;
		    listPBstandalone.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = " SELECT "+
		    				" T.ID_PUPB, T.ID_PU, T.PENAMA, "+
		    				" T.ULASAN, T.TARIKH_SEDIA_BAYARAN, T.PAMPASAN_TANAH, "+
		    				" T.JUMLAH_PELARASAN, T.TEMPOH_LEWAT, T.FAEDAH_SEBELUM_PU, "+
		    				" T.FAEDAH_SELEPAS_PU, T.TARIKH_SURAT, T.BIL_SURAT, "+
		    				" T.TEMPOH_AKHIR_BAYAR_SEMULA, T.STATUS_BAYAR, T.NAMA_PB, "+
		    				" T.ID_JENISNOPB, T.NO_PB, T.ID_JENISPB, "+
		    				" T.ID_BANGSA, T.ID_WARGANEGARA, T.ID_NEGERI, "+
		    				" T.ID_BANDAR, T.JENIS_LAIN2_PB, T.SYER_ATAS, "+
		    				" T.SYER_BAWAH, T.ALAMAT1, T.ALAMAT2, "+
		    				" T.ALAMAT3, T.POSKOD, T.NO_TEL_RUMAH, "+
		    				" T.NO_HANDPHONE, T.NO_FAX, T.ID_MASUK, "+
		    				" T.TARIKH_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI "+
		    				" FROM TBLPPTPUPB T WHERE T.ID_PU = '"+id_pu+"' ";
					myLogger.info(" listPBstandalone :"+sql);					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		
		    			h.put("id_award", rs.getString("ID_PUPB")== null?"":rs.getString("ID_PUPB"));
		    			h.put("id_pupb", rs.getString("ID_PUPB")== null?"":rs.getString("ID_PUPB"));
		    			h.put("id_pu", rs.getString("ID_PU")== null?"":rs.getString("ID_PU"));
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
		    			h.put("penama", rs.getString("penama")== null?"":rs.getString("penama"));
		    			h.put("tarikh_sedia_bayaran", rs.getDate("tarikh_sedia_bayaran")==null?"":Format.format(rs.getDate("tarikh_sedia_bayaran")));
		    			h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    			h.put("pampasan_tanah", rs.getString("pampasan_tanah")== null?0:rs.getDouble("pampasan_tanah"));
		    			h.put("jumlah_pelarasan", rs.getString("jumlah_pelarasan")== null?0:rs.getDouble("jumlah_pelarasan"));
		    			h.put("tempoh_lewat", rs.getString("tempoh_lewat")== null?"":rs.getString("tempoh_lewat"));
		    			h.put("faedah_sebelum_pu", rs.getString("faedah_sebelum_pu")== null?0:rs.getDouble("faedah_sebelum_pu"));
		    			h.put("faedah_selepas_pu", rs.getString("faedah_selepas_pu")== null?0:rs.getDouble("faedah_selepas_pu"));
		    			h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
		    			h.put("bil_surat", rs.getString("bil_surat")== null?"":rs.getString("bil_surat"));
		    			h.put("tempoh_akhir_bayar_semula", rs.getString("tempoh_akhir_bayar_semula")== null?"":rs.getString("tempoh_akhir_bayar_semula"));
		    			h.put("status_bayar", rs.getString("status_bayar")== null?"":rs.getString("status_bayar"));		    			
		    			listPBstandalone.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    }// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listPBwithAward

	public static  Vector getlistPBstandalone(){
		return listPBstandalone;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPUStandAlone(String userIdNegeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = " SELECT A.ID_PU, A.NO_FAIL_PU, A.NO_PU, A.NO_PELAN, A.NO_PA, A.NO_LOT_BARU, A.CATATAN, A.TARIKH_TERIMA_PA, A.TARIKH_TERIMA_SA, A.TARIKH_SELESAI, A.LUAS_PU, "; 
		    		sql += " A.LUAS_ASAL, A.LUAS_AMBIL, A.BEZA_LUAS, A.BAKI_LUAS, A.JENIS_PELARASAN, A.TARIKH_SURAT_PTG, A.TARIKH_HANTAR_JUPEM, A.TARIKH_PU ";
		    		sql += " FROM TBLPPTPU A ";
		    		
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "WHERE A.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "WHERE A.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		sql +=" ORDER by A.NO_FAIL_PU desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("NO_FAIL_PU", rs.getString("NO_FAIL_PU")== null?"":rs.getString("NO_FAIL_PU"));
		    			h.put("ID_PU", rs.getString("ID_PU")== null?"":rs.getString("ID_PU"));
		    			h.put("TARIKH_PU", rs.getDate("TARIKH_PU")==null?"":Format.format(rs.getDate("TARIKH_PU")));
		    			h.put("TARIKH_TERIMA_PA", rs.getDate("TARIKH_TERIMA_PA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_PA")));
		    			h.put("TARIKH_TERIMA_SA", rs.getDate("TARIKH_TERIMA_SA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA_SA")));
		    			h.put("TARIKH_SELESAI", rs.getDate("TARIKH_SELESAI")==null?"":Format.format(rs.getDate("TARIKH_SELESAI")));
		    			h.put("TARIKH_SURAT_PTG", rs.getDate("TARIKH_SURAT_PTG")==null?"":Format.format(rs.getDate("TARIKH_SURAT_PTG")));
		    			h.put("TARIKH_HANTAR_JUPEM", rs.getDate("TARIKH_HANTAR_JUPEM")==null?"":Format.format(rs.getDate("TARIKH_HANTAR_JUPEM")));	    			
		    			h.put("JENIS_PELARASAN", rs.getString("JENIS_PELARASAN")== null?"":rs.getString("JENIS_PELARASAN"));
		    			h.put("BAKI_LUAS", rs.getString("BAKI_LUAS")== null?"":rs.getString("BAKI_LUAS"));
		    			h.put("BEZA_LUAS", rs.getString("BEZA_LUAS")== null?"":rs.getString("BEZA_LUAS"));
		    			h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL")== null?"":rs.getString("LUAS_AMBIL"));
		    			h.put("LUAS_ASAL", rs.getString("LUAS_ASAL")== null?"":rs.getString("LUAS_ASAL"));
		    			h.put("LUAS_PU", rs.getString("LUAS_PU")== null?"":rs.getString("LUAS_PU"));
		    			h.put("CATATAN", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
		    			h.put("NO_PU", rs.getString("NO_PU")== null?"":rs.getString("NO_PU"));
		    			h.put("NO_PELAN", rs.getString("NO_PELAN")== null?"":rs.getString("NO_PELAN"));
		    			h.put("NO_PA", rs.getString("NO_PA")== null?"":rs.getString("NO_PA"));
		    			h.put("NO_LOT_BARU", rs.getString("NO_LOT_BARU")== null?"":rs.getString("NO_LOT_BARU"));	
		    			list.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return list;
		    	}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPUStandAlone
	
	public static void hapusDataPU(String id_pu) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	 
	    		
	    		sql = "DELETE FROM TBLPPTPUPB WHERE ID_PU = '"+id_pu+"'";
	    		stmt.executeUpdate(sql);

	    		sql = "DELETE FROM TBLPPTPU WHERE ID_PU = '"+id_pu+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		
	    }//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close hapusDataPU
	
	@SuppressWarnings("unchecked")
	public static void updatePUStandAlone(Hashtable data,String id_pu) throws Exception{
	
	    Db db = null;
	    String sql = "";
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    	
	    		String txtNoFailPU = (String)data.get("txtNoFailPU");		    		
	    		String NilaiSeunit = (String)data.get("NilaiSeunit");	
	    		String tarikhBayarTambahan = (String)data.get("tarikhBayarTambahan");
	    		
	    		String txtNoFailPUJUPEM = (String)data.get("txtNoFailPUJUPEM");
	    		String txtNoFailProjek = (String)data.get("txtNoFailProjek");
	    		String txtTujuanProjek = (String)data.get("txtTujuanProjek");
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    		String txtNoWarta = (String)data.get("txtNoWarta");
	    		String txtLuasBorangK = (String)data.get("txtLuasBorangK");
	    		String sorDropdownUnitBorangK = (String)data.get("sorDropdownUnitBorangK");
	    		String txtNoWK = (String)data.get("txtNoWK");
	    		String txtNoPAasal = (String)data.get("txtNoPAasal");
	    		/*    		
	    		 #set($txtNoFailPUJUPEM=$data.NO_FAIL_JUPEM_SA)
	             #set($txtNoFailProjek=$data.NO_FAIL_PROJEK_SA)
	             #set($txtTujuanProjek=$data.TUJUAN_PROJEK_SA)
	             #set($txdTarikhWarta=$data.TARIKH_WARTA_SA)
	             #set($txtNoWarta=$data.NO_WARTA_SA)
	             #set($txtLuasBorangK=$data.LUAS_BORANGK_SA)
	             #set($sorDropdownUnitBorangK=$data.ID_UNITBORANGK_SA)
	             #set($txtNoWK=$data.NO_WK_SA)
	             #set($txtNoPAasal=$data.NO_PA_ASAL_SA)
	             
	             NO_FAIL_JUPEM_SA VARCHAR(100),
	    		NO_FAIL_PROJEK_SA VARCHAR(100),
	    		TUJUAN_PROJEK_SA VARCHAR(4000),
	    		NO_WARTA_SA VARCHAR(100),
	    		TARIKH_WARTA_SA DATE,
	    		NO_WK_SA VARCHAR(100),
	    		NO_PA_ASAL_SA VARCHAR(100),
	    		LUAS_BORANGK_SA NUMBER (30,4),
	    		ID_UNITBORANGK_SA NUMBER
	    		*/
	    		
	    		
	    		
	    		
	    		
	    		
	    		//data hakmilik
	    		String txtNoHakmilik = (String)data.get("txtNoHakmilik");	
	    		String txtNoLot = (String)data.get("txtNoLot");
	    		String txdTarikhBorangK = (String)data.get("txdTarikhBorangK");
	    		String txtLuasAmbil = (String)data.get("txtLuasAmbil");
	    		String sorDropdownUnit = (String)data.get("sorDropdownUnit");
	    		
	    		String txtLuasAsal = (String)data.get("txtLuasAsal");
	    		String sorDropdownUnitAsal = (String)data.get("sorDropdownUnitAsal");
	    		
	    		String socJenisHakmilik = (String)data.get("socJenisHakmilik");
	    		String socMukim = (String)data.get("socMukim");
	    		String socDaerah = (String)data.get("socDaerah");
	    		
	    		String txdTarikhSuratPTG = (String)data.get("txdTarikhSuratPTG");	 
	    		String txtNoPelan = (String)data.get("txtNoPelan");	 
	    		String txdTarikhHantarJUPEM = (String)data.get("txdTarikhHantarJUPEM");	 
	    		String txtNoPU = (String)data.get("txtNoPU");	 
	    		String txdTarikhBorangPU = (String)data.get("txdTarikhBorangPU");	 
	    		
	    		String txdTarikhTerimaPA = (String)data.get("txdTarikhTerimaPA");	 
	    		String txtNoPA = (String)data.get("txtNoPA");	 
	    		String txdTarikhTerimaSA = (String)data.get("txdTarikhTerimaSA");	 
	    		String txdTarikhPenyelesaian = (String)data.get("txdTarikhPenyelesaian");	 
	    		String txtCatatan = (String)data.get("txtCatatan");	
	    		String txtLotBaru = (String)data.get("txtLotBaru");	
	    		
	    		String txtKeluasanPU = (String)data.get("txtKeluasanPU");	 
	    		String lblLuasAsal = (String)data.get("lblLuasAsal");	 
	    		String lblLuasAmbil = (String)data.get("lblLuasAmbil");	 
	    		String sorJenisPelarasan = (String)data.get("sorJenisPelarasan");	 
	    		String countBakiLuas = (String)data.get("countBakiLuas");	
	    		String countBezaLuas = (String)data.get("countBezaLuas");	
	    		
	    		String id_user = (String)data.get("id_user");	
	    		
	    		String TSP = "to_date('" + txdTarikhSuratPTG + "','dd/MM/yyyy')";
	    		String THJ = "to_date('" + txdTarikhHantarJUPEM + "','dd/MM/yyyy')";
	    		String TBP = "to_date('" + txdTarikhBorangPU + "','dd/MM/yyyy')";
	    		
	    		String TTP = "to_date('" + txdTarikhTerimaPA + "','dd/MM/yyyy')";
	    		String TTS = "to_date('" + txdTarikhTerimaSA + "','dd/MM/yyyy')";
	    		String TP = "to_date('" + txdTarikhPenyelesaian + "','dd/MM/yyyy')";
	    		
	    		String TBK = "to_date('" + txdTarikhBorangK + "','dd/MM/yyyy')";
	    		
	    		String TBT = "to_date('" + tarikhBayarTambahan + "','dd/MM/yyyy')";
	    		
	    		String TWK = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_pu",id_pu);
	    		
	    		
	    		
	    		r.add("NO_FAIL_JUPEM_SA", txtNoFailPUJUPEM);	
	    		r.add("NO_FAIL_PROJEK_SA", txtNoFailProjek);
	    		r.add("TUJUAN_PROJEK_SA", txtTujuanProjek);	
	    		r.add("TARIKH_WARTA_SA", r.unquote(TWK));	
	    		r.add("NO_WARTA_SA", txtNoWarta);	
	    		r.add("LUAS_BORANGK_SA ", txtLuasBorangK);	
	    		r.add("ID_UNITBORANGK_SA ", sorDropdownUnitBorangK);	
	    		r.add("NO_WK_SA", txtNoWK);	
	    		r.add("NO_PA_ASAL_SA", txtNoPAasal);	
	    		
	    		
	    		
	    		r.add("NILAI_METERPERSEGI ", NilaiSeunit);	
	    		
	    		r.add("no_hakmilik ", txtNoHakmilik);	
	    		r.add("no_lot", txtNoLot);	
	    		r.add("luas_ambil_hm", txtLuasAmbil);	    		
	    		r.add("id_unitluasambil", sorDropdownUnit);
	    		
	    		r.add("luas_asal_hm", txtLuasAsal);	    		
	    		r.add("id_unitluasasal", sorDropdownUnitAsal);
	    		
	    		r.add("id_jenishakmilik", socJenisHakmilik);
	    		r.add("id_mukim", socMukim);	
	    		r.add("id_daerah", socDaerah);
	    		r.add("tarikh_borangk",r.unquote(TBK));
	    		
	    		r.add("no_fail_pu ", txtNoFailPU);	
	    		r.add("no_pu", txtNoPU);	
	    		r.add("no_pelan", txtNoPelan);	    		
	    		r.add("no_pa", txtNoPA);
	    		r.add("no_lot_baru", txtLotBaru);
	    		r.add("catatan", txtCatatan);	
	    		r.add("tarikh_terima_pa",r.unquote(TTP));
	    		r.add("tarikh_terima_sa",r.unquote(TTS));
	    		r.add("tarikh_selesai",r.unquote(TP));	    		
	    		r.add("luas_pu", txtKeluasanPU);    		
	    		r.add("luas_asal", lblLuasAsal);	
	    		r.add("luas_ambil", lblLuasAmbil);		    		
	    		r.add("beza_luas", countBezaLuas);	
	    		r.add("baki_luas", countBakiLuas);	
	    		r.add("jenis_pelarasan",sorJenisPelarasan);    		
	    		r.add("tarikh_surat_ptg",r.unquote(TSP));
	    		r.add("tarikh_hantar_jupem",r.unquote(THJ));
	    		r.add("tarikh_pu",r.unquote(TBP));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		
	    		r.add("tarikh_bayaran_tambahan",r.unquote(TBT));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptpu");
	    		stmt.executeUpdate(sql);
    	
	    	}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	  }//close updatePUStandAlone
	
}//close class
