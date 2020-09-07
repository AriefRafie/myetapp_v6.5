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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8HakmilikData {
	static Logger myLogger = Logger.getLogger(FrmPermohonanUPTData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmUPTSek8HakmilikData.class);
	
	private static  Vector listcarian = null;
	private static  Vector listPB = null;
	private static  Vector dataPB = null;
	private static  Vector listBebanan = null;
	private static  Vector dataBebanan = null;
	private static  Vector checkExistPB = null;
	private static  Vector checkSizeExistPB = null;
	private static  Vector totalSyer = null;
	private static  Vector listPBwithAward = null;
	private static  Vector sizeBahagianPB = null;
	private static  Vector dataExistBahagianPB = null;
	private static  Vector listcarianHM = null;
	
	
	
	
	
	
	public static  Vector getListCarian(){
		return listcarian;
	}
	public static  Vector getListPB(){
		return listPB;
	}
	public static  Vector getDataDetailPB(){
		return dataPB;
	}
	public static  Vector getListBebanan(){
		return listBebanan;
	}
	public static  Vector getDataBebanan(){
		return dataBebanan;
	}
	public static  Vector getCheckExistPB(){
		return checkExistPB;
	}
	public static  Vector getCheckSizeExistPB(){
		return checkSizeExistPB;
	}
	public static  Vector getTotalSyer(){
		return totalSyer;
	}
	public static  Vector getListPBwithAward(){
		return listPBwithAward;
	}
	public static  Vector getSizeBahagianPB(){
		return sizeBahagianPB;
	}
	public static  Vector getDataExistBahagianPB(){
		return dataExistBahagianPB;
	}
	public static  Vector getListCarianHM(){
		return listcarianHM;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{
		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		
		//sql +="AND p.id_status IN (127,16)";
		/*
		sql +="AND p.id_permohonan IN ";
		sql += " (select px.id_permohonan from Tblpptpermohonan px, Tblpfdfail fx ";
		sql += " where fx.id_fail = px.id_fail "; 
		sql += " and px.id_permohonan = p.id_permohonan ";
		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
			if(userIdNegeri.equals("14")){
				sql += " AND fx.id_negeri in (14,15,16) ";
			}else{
				sql += " AND fx.id_negeri ='"+userIdNegeri+"'";
			}		
		}
		*/
		
		sql += " AND (p.FLAG_SEMAK = '2' ";
		sql += " OR f.NO_FAIL IS NOT NULL)  ";
		sql += " and f.no_fail is not null ";
		
		
		
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
		     
		    		sql = sqlListPermohonan(userIdNegeri);
		    		sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
		    		myLogger.info(" ::: list permohonan : "+sql);
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
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
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
	    		sql +="AND p.id_status IN (127,16)";
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
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	
	@SuppressWarnings("unchecked")
	public static void simpanHM(Hashtable data,String flagSubjaket) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    myLogger.info("simpanHM: yang Pertama (nak yang ni) ----- ");
	    try{
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");

	    		//pengambilan segera
	    		String socPSegera = (String)data.get("socPSegera");
	    		
	    		String socDaerahPenggawa = (String)data.get("socDaerahPenggawa");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_negeriProjek = (String)data.get("id_negeriProjek");
	    		String id_daerahProjek = (String)data.get("id_daerahProjek");
	    		String id_mukimProjek = (String)data.get("socMukim");
	    		String txtseksyen = (String)data.get("txtseksyen");
	    		String catatan = (String)data.get("txtCatatan");
	    		String txtnolot = (String)data.get("txtNoLot");
	    		String txtnopt = (String)data.get("txtNoPT");
	    		
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik");	 
	    		String no_hakmilik = (String)data.get("txtNoHakmilik");
	    		String id_lot = (String)data.get("kodLot");
	    		String id_luasasal = (String)data.get("unitLuas");
	    		String luas_ambil = (String)data.get("txtLuasAmbil");	
	    		String luas_asal = (String)data.get("txtLuasAsal");
	    		
	    		String tarikhLuput = (String)data.get("txdTarikhLuput");	 
	    		String tarikhDaftar = (String)data.get("txdTarikhDaftar");
	    		String baki = (String)data.get("txtBakiTempoh");
	    		String id_kategoriTanah = (String)data.get("socKategoriTanah");
	    		String lokasi = (String)data.get("txtLokasi");	
	    	
	    		//new
	    		String id_luasambil = (String)data.get("unitLuasAmbil");
	    		String nama_luas_asal = (String)data.get("txtLuasLotAsalSebelumConvert");
	    		String nama_luas_ambil = (String)data.get("txtLuasLotAmbilSebelumConvert");
	    		String id_unitluaslot_convert = (String)data.get("sorDropdownUnitAsal");
	    		String id_unitluasambil_convert = (String)data.get("sorDropdownUnitAmbil");
	    		
	    		//rizab
	    		String sorJenisRizab = (String)data.get("sorJenisRizab");
	    		String txtLain = (String)data.get("txtLain");
	    		String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    		
	    		String syaratNyata = (String)data.get("txtSyaratNyata");	 
	    		String syaratKhas = (String)data.get("txtSyaratKhas");
	    		String sekatanKepentingan = (String)data.get("txtSekatanKepentingan");
	    		String sekatanHak = (String)data.get("txtSekatanHak");
	    		String noSyit = (String)data.get("txtNoSyit");	
	    		
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
	    		
	    		//PPT-03  Usop tambah
	    		String txtNoBangunan = (String)data.get("txtNoBangunan");
	    		String txtNoTingkat = (String)data.get("txtNoTingkat");
	    		String txtNoPetak = (String)data.get("txtNoPetak");

	    		
	    		String flagSebahagian = "0";
	    		
	    		if(!luas_asal.isEmpty() && !luas_ambil.isEmpty()){
	    			
	    			//validate sebahagian / keseluruhan
	    			double luasAsal = Double.parseDouble(luas_asal);
	    			if(id_unitluaslot_convert.equals("1")){
	    				luasAsal *= 10000;
	    			}
	    		
	    		
	    			double luasAmbil = Double.parseDouble(luas_ambil);
	    			if(id_unitluasambil_convert.equals("1")){
	    				luasAmbil *= 10000;
	    			}
	    		
	    			if((luasAsal - luasAmbil) > 0 ){
	    				flagSebahagian = "1";
	    			}else if((luasAsal - luasAmbil) == 0 ){
	    				flagSebahagian = "2";
	    			}else{
	    				flagSebahagian = "0";
	    			}
	    		
	    		}
	    		
	    		//1 = sebahagian
	    		//2 = keseluruhan	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("flag_sebahagian",flagSebahagian);
	    		//flag segera
	    		r.add("FLAG_SEGERA_SEBAHAGIAN", socPSegera);
	    		r.add("id_daerahpenggawa", socDaerahPenggawa);
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_negeri", id_negeriProjek); 	
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("id_daerah", id_daerahProjek);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
	    		r.add("tarikh_warta_rizab", r.unquote(TW));
	    		r.add("flag_jenis_rizab", sorJenisRizab); 	
	    		r.add("nama_lain_rizab", txtLain);
	    		r.add("id_mukim", id_mukimProjek);
	    		r.add("id_unitluaslot", id_luasasal);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_lot",luas_asal);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("no_pt", txtnopt);
	    		r.add("catatan",catatan);
	    		r.add("seksyen",txtseksyen);	    		
	    		r.add("tarikh_daftar",r.unquote(TD));
	    		r.add("tarikh_luput",r.unquote(TL));
	    		r.add("tempoh_luput", baki);
	    		r.add("id_kategoritanah",id_kategoriTanah);
	    		r.add("lokasi",lokasi);	    		
	    		r.add("syarat_nyata", syaratNyata);
	    		r.add("syarat_khas", syaratKhas);
	    		r.add("sekatan_kepentingan",sekatanKepentingan);
	    		r.add("sekatan_hak",sekatanHak);
	    		r.add("no_syit",noSyit);

	    		// PPT-03 Baru
	    		r.add("no_bangunan",txtNoBangunan); //PPT-03 
	    		r.add("no_tingkat",txtNoTingkat); //PPT-03 
	    		r.add("no_petak",txtNoPetak); //PPT-03 

	    		//new
	    		r.add("id_unitluasambil", id_luasambil);
	    		r.add("nama_luas_asal", nama_luas_asal);
	    		r.add("nama_luas_ambil", nama_luas_ambil);
	    		r.add("id_unitluaslot_convert", id_unitluaslot_convert);
	    		r.add("id_unitluasambil_convert", id_unitluasambil_convert);	
	    		
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);    		
	    		sql = r.getSQLInsert("tblppthakmilik");
	    		myLogger.info("sql add tanah : "+sql);
	    		stmt.executeUpdate(sql);
    	
	    		//remove subjaket kalau dah ada
	    		if(flagSubjaket.equals("1")){
	    			
	    			r.clear();
	    		
	    			//update flag di tblpptpermohonan
	    			r.update("id_permohonan", id_permohonan);		    				
	    			r.add("flag_subjaket", "");
	    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    			r.add("id_kemaskini",id_user);    		
	    			sql = r.getSQLUpdate("Tblpptpermohonan");
	    			stmt.executeUpdate(sql);
	    			
	    			r.clear();
	    			
	    			r.update("id_permohonan", id_permohonan);		    				
	    			r.add("no_subjaket", "");
	    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    			r.add("id_kemaskini",id_user);    		
	    			sql = r.getSQLUpdate("Tblppthakmilik");
	    			stmt.executeUpdate(sql);
	    		}
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanHM
	
	
	@SuppressWarnings("unchecked")
	public static void updateHM(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		//pengambilan segera
	    		String socPSegera = (String)data.get("socPSegera");
	    		
	    		String socDaerahPenggawa = (String)data.get("socDaerahPenggawa");
	    		
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		String id_daerah = (String)data.get("id_daerah");
	    		
	    		String id_mukimProjek = (String)data.get("socMukim"); myLogger.info("socMukim: " +id_mukimProjek);
	    		String txtseksyen = (String)data.get("txtseksyen"); myLogger.info("txtseksyen: " +txtseksyen);
	    		String catatan = (String)data.get("txtCatatan");
	    		String txtnolot = (String)data.get("txtNoLot");
	    		String txtnopt = (String)data.get("txtNoPT");
	    		
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik"); myLogger.info("socJenisHakmilik: " +id_jenishakmilik);
	    		String no_hakmilik = (String)data.get("txtNoHakmilik");
	    		String id_lot = (String)data.get("kodLot");  myLogger.info("socKodLot: " +id_lot);
	    		String id_luas = (String)data.get("unitLuas");	 myLogger.info("id_luas: " +id_luas);
	    		String luas_ambil = (String)data.get("txtLuasAmbil");	
	    		String luas_asal = (String)data.get("txtLuasAsal");

	    		String tarikhLuput = (String)data.get("txdTarikhLuput");	 
	    		String tarikhDaftar = (String)data.get("txdTarikhDaftar");
	    		String baki = (String)data.get("txtBakiTempoh");
	    		String id_kategoriTanah = (String)data.get("socKategoriTanah"); myLogger.info("KategoriTanah: " +id_kategoriTanah);
	    		String lokasi = (String)data.get("txtLokasi");	
	    	
	    		//rizab
		    	String sorJenisRizab = (String)data.get("sorJenisRizab");
		    	String txtLain = (String)data.get("txtLain");
		    	String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
		    	String txdTarikhWarta = (String)data.get("txdTarikhWarta");
		    	 
	    		String syaratNyata = (String)data.get("txtSyaratNyata");	 
	    		String syaratKhas = (String)data.get("txtSyaratKhas");
	    		String sekatanKepentingan = (String)data.get("txtSekatanKepentingan");
	    		String sekatanHak = (String)data.get("txtSekatanHak");
	    		String noSyit = (String)data.get("txtNoSyit");	
	    		
	    		//new
	    		String id_luasambil = (String)data.get("unitLuasAmbil");
	    		String nama_luas_asal = (String)data.get("txtLuasLotAsalSebelumConvert");
	    		String nama_luas_ambil = (String)data.get("txtLuasLotAmbilSebelumConvert");
	    		String id_unitluaslot_convert = (String)data.get("sorDropdownUnitAsal");
	    		String id_unitluasambil_convert = (String)data.get("sorDropdownUnitAmbil");
	    		
	    		
	    		String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";

	    		//PPT-03 Penambahan Strata
	    		String txtNoBangunan = (String)data.get("no_bangunan");
	    		String txtNoTingkat = (String)data.get("no_tingkat");
	    		String txtNoPetak =  (String)data.get("no_petak");
	    		myLogger.info("Data updateHM no bangunan: " +txtNoBangunan);
	    		myLogger.info("nak nombor bangunan ni: " +(String)data.get("no_bangunan"));
	    		
	    		String flagSebahagian = "0";
	    		
	    		if(!luas_asal.isEmpty() && !luas_ambil.isEmpty()){
	    			
	    			//validate sebahagian / keseluruhan
	    			double luasAsal = Double.parseDouble(luas_asal);
	    			if(id_unitluaslot_convert.equals("1")){
	    				luasAsal *= 10000;
	    			}
	    		
	    			double luasAmbil = Double.parseDouble(luas_ambil);
	    			if(id_unitluasambil_convert.equals("1")){
	    				luasAmbil *= 10000;
	    			}
	    		
	    			if((luasAsal - luasAmbil) > 0 ){
	    				flagSebahagian = "1";
	    			}else if((luasAsal - luasAmbil) == 0 ){
	    				flagSebahagian = "2";
	    			}else{
	    				flagSebahagian = "0";
	    			}
	    			
	    		}
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", id_hakmilik);
	    		//flag segera
	    		r.add("FLAG_SEGERA_SEBAHAGIAN", socPSegera);
	    		r.add("flag_sebahagian", flagSebahagian);
	    		r.add("id_daerahpenggawa", socDaerahPenggawa);
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("id_daerah", id_daerah);
	    		r.add("id_mukim", id_mukimProjek);
	    		r.add("id_unitluaslot", id_luas);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_lot",luas_asal);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
		    	r.add("tarikh_warta_rizab", r.unquote(TW));
		    	r.add("flag_jenis_rizab", sorJenisRizab); 	
		    	r.add("nama_lain_rizab", txtLain);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("no_pt", txtnopt);
	    		r.add("catatan",catatan);
	    		r.add("seksyen",txtseksyen);	    		
	    		r.add("tarikh_daftar",r.unquote(TD));
	    		r.add("tarikh_luput",r.unquote(TL));
	    		r.add("tempoh_luput", baki);
	    		r.add("id_kategoritanah",id_kategoriTanah);
	    		r.add("lokasi",lokasi);	    		
	    		r.add("syarat_nyata", syaratNyata);
	    		r.add("syarat_khas", syaratKhas);
	    		r.add("sekatan_kepentingan",sekatanKepentingan);
	    		r.add("sekatan_hak",sekatanHak);
	    		r.add("no_syit",noSyit);
	    		
//	    		PPT-03 Strata
	    		r.add("no_bangunan",txtNoBangunan);
	    		r.add("no_tingkat",txtNoTingkat);
	    		r.add("no_petak",txtNoPetak);
	    		
	    		//new
	    		r.add("id_unitluasambil", id_luasambil);
	    		r.add("nama_luas_asal", nama_luas_asal);
	    		r.add("nama_luas_ambil", nama_luas_ambil);
	    		r.add("id_unitluaslot_convert", id_unitluaslot_convert);
	    		r.add("id_unitluasambil_convert", id_unitluasambil_convert);	
	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user); 
	    		
	    		sql = r.getSQLUpdate("TBLPPTHAKMILIK");
	    		stmt.executeUpdate(sql);
	    		myLogger.info("updateHM ****  : "+sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateHM
	
	
	  public static void hapusHM(String id_hakmilik) throws Exception{
		
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblppthakmilik where id_hakmilik = '"+id_hakmilik+"'";
	    		stmt.executeUpdate(sql);
    	 
	    		sql = "DELETE FROM Tblppthakmilikpb WHERE id_hakmilik = '"+id_hakmilik+"'";
	    		stmt.executeUpdate(sql);
	    		
	    		sql = "DELETE FROM Tblpptbebanan WHERE id_hakmilik = '"+id_hakmilik+"'";
	    		stmt.executeUpdate(sql);
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusHM
	
	  
	  @SuppressWarnings("unchecked")
	  public static void simpanPB(Hashtable data,long id_pihakberkepentingan,String id_hakmilikpb,String id_bahagianpb) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		String txtNama = (String)data.get("txtNama");
		    		String socJenisNoPB = (String)data.get("socJenisNoPB");
		    		String socJenisPB = (String)data.get("socJenisPB");
		    		String socBangsa = (String)data.get("socBangsa");
		    		String socWarga = (String)data.get("socWarga");
		    		String socNegeri = (String)data.get("socNegeri");		    		
		    		String socBandar = (String)data.get("socBandar");
		    		String txtNoPB = (String)data.get("txtNoPB");
		    		String txtJenisPB = (String)data.get("txtJenisPB");
		    		String txtAlamat1 = (String)data.get("txtAlamat1");
		    		String txtAlamat2 = (String)data.get("txtAlamat2");		    		
		    		String txtAlamat3 = (String)data.get("txtAlamat3");
		    		String txtPoskod = (String)data.get("txtPoskod");
		    		String txtNoTelefon = (String)data.get("txtNoTelefon");
		    		String txtNoBimbit = (String)data.get("txtNoBimbit");
		    		String txtNoFaks = (String)data.get("txtNoFaks");
		    		
		    		String txtNamaBank = (String)data.get("txtNamaBank");
		    		String txtNoAkaun = (String)data.get("txtNoAkaun");
		    	
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		//r.add("id_hakmilik", id_hakmilik);
		    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);
		    		r.add("nama_pb", txtNama);
		    		r.add("id_jenisnopb", socJenisNoPB);		    		
		    		r.add("id_bangsa", socBangsa);
		    		r.add("id_warganegara", socWarga);		    				    		
		    		r.add("no_pb", txtNoPB); 		
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("Tblpptpihakberkepentingan");
		    		stmt.executeUpdate(sql);
	    	
		    		
		    		SQLRenderer rx = new SQLRenderer();
		    		rx.add("id_hakmilikpb",id_hakmilikpb);
		    		rx.add("id_hakmilik", id_hakmilik);		    		
		    		rx.add("id_pihakberkepentingan", id_pihakberkepentingan);			    
		    		rx.add("id_bahagianpb", id_bahagianpb);		
		    		//move field from tblpptpihakberkepentingan TO tblppthakmilikpb
		    		rx.add("jenis_lain2_pb", txtJenisPB);	
		    		rx.add("alamat1", txtAlamat1);
		    		rx.add("alamat2", txtAlamat2);
		    		rx.add("alamat3", txtAlamat3);
		    		rx.add("poskod", txtPoskod);
		    		rx.add("no_tel_rumah", txtNoTelefon);
		    		rx.add("no_handphone", txtNoBimbit);
		    		rx.add("no_fax", txtNoFaks);		
		    		rx.add("id_negeri", socNegeri);
		    		rx.add("id_bandar", socBandar);
		    		rx.add("id_jenispb", socJenisPB);

		    		rx.add("nama_bank", txtNamaBank);
		    		rx.add("no_akaun", txtNoAkaun);
		    		//
		    		rx.add("tarikh_masuk",rx.unquote("sysdate"));
		    		rx.add("id_masuk",id_user); 
		    		sql = rx.getSQLInsert("Tblppthakmilikpb");
		    		stmt.executeUpdate(sql);
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanPB
	  
	  @SuppressWarnings("unchecked")
	  public static void simpanExistPB(Hashtable data,String id_hakmilikpb,String id_bahagianpb) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		//id pb
		    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		
		    		String socJenisPB = (String)data.get("socJenisPB");
		    		String socNegeri = (String)data.get("socNegeri");		    		
		    		String socBandar = (String)data.get("socBandar");
		    		String txtAlamat1 = (String)data.get("txtAlamat1");
		    		String txtAlamat2 = (String)data.get("txtAlamat2");		    		
		    		String txtAlamat3 = (String)data.get("txtAlamat3");
		    		String txtPoskod = (String)data.get("txtPoskod");
		    		String txtNoTelefon = (String)data.get("txtNoTelefon");
		    		String txtNoBimbit = (String)data.get("txtNoBimbit");
		    		String txtNoFaks = (String)data.get("txtNoFaks");
		    		String txtJenisPB = (String)data.get("txtJenisPB");
		    		
		    		SQLRenderer rx = new SQLRenderer();
		    		rx.add("id_hakmilikpb",id_hakmilikpb);
		    		rx.add("id_hakmilik", id_hakmilik);		    		
		    		rx.add("id_pihakberkepentingan", id_pihakberkepentingan);	
		    		rx.add("id_bahagianpb",id_bahagianpb);
		    		//move field from tblpptpihakberkepentingan TO tblppthakmilikpb
		    		rx.add("alamat1", txtAlamat1);
		    		rx.add("alamat2", txtAlamat2);
		    		rx.add("alamat3", txtAlamat3);
		    		rx.add("poskod", txtPoskod);
		    		rx.add("no_tel_rumah", txtNoTelefon);
		    		rx.add("no_handphone", txtNoBimbit);
		    		rx.add("no_fax", txtNoFaks);		
		    		rx.add("id_negeri", socNegeri);
		    		rx.add("id_bandar", socBandar);
		    		rx.add("id_jenispb", socJenisPB);
		    		rx.add("jenis_lain2_pb", txtJenisPB);
		    		//
		    		rx.add("tarikh_masuk",rx.unquote("sysdate"));
		    		rx.add("id_masuk",id_user); 
		    		sql = rx.getSQLInsert("Tblppthakmilikpb");
		    		stmt.executeUpdate(sql);
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanExistPB
	  
	  @SuppressWarnings("unchecked")
	  public static void updatePB(Hashtable data,String mode,String id_bahagianpb) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");
		    		
		    		//data for update tblpptpihakberkepentingan
		    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
		    		String txtNama = (String)data.get("txtNama");
		    		String socJenisNoPB = (String)data.get("socJenisNoPB");
		    		String socBangsa = (String)data.get("socBangsa");
		    		String socWarga = (String)data.get("socWarga");
		    		String txtNoPB = (String)data.get("txtNoPB");
		    		String txtJenisPB = (String)data.get("txtJenisPB");

		    		
		    		if(mode.equals("update")){
		    			//data for update tblppthakmilikpb
			    		String socJenisPB = (String)data.get("socJenisPB");
			    		String socNegeri = (String)data.get("socNegeri");		    		
			    		String socBandar = (String)data.get("socBandar");
			    		String txtAlamat1 = (String)data.get("txtAlamat1");
			    		String txtAlamat2 = (String)data.get("txtAlamat2");		    		
			    		String txtAlamat3 = (String)data.get("txtAlamat3");
			    		String txtPoskod = (String)data.get("txtPoskod");
			    		String txtNoTelefon = (String)data.get("txtNoTelefon");
			    		String txtNoBimbit = (String)data.get("txtNoBimbit");
			    		String txtNoFaks = (String)data.get("txtNoFaks");		 
			    		
			    		String txtNamaBank = (String)data.get("txtNamaBank");
			    		String txtNoAkaun = (String)data.get("txtNoAkaun");
			    		
			    		SQLRenderer rx = new SQLRenderer();
			    		rx.update("id_hakmilikpb", id_hakmilikpb);	
			    		rx.add("id_bahagianpb",id_bahagianpb);
			    		rx.add("id_jenispb", socJenisPB);    		
			    		rx.add("id_negeri", socNegeri);
			    		rx.add("id_bandar", socBandar);    		
			    		rx.add("alamat1", txtAlamat1);
			    		rx.add("alamat2", txtAlamat2);
			    		rx.add("alamat3", txtAlamat3);
			    		rx.add("poskod", txtPoskod);
			    		rx.add("no_tel_rumah", txtNoTelefon);
			    		rx.add("no_handphone", txtNoBimbit);
			    		rx.add("no_fax", txtNoFaks);	
			    		rx.add("jenis_lain2_pb", txtJenisPB);
			    		
			    		rx.add("nama_bank", txtNamaBank);
			    		rx.add("no_akaun", txtNoAkaun);
			    		
			    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
			    		rx.add("id_kemaskini",id_user);    		
			    		sql = rx.getSQLUpdate("Tblppthakmilikpb");
			    		stmt.executeUpdate(sql);
		    		}
		    
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_pihakberkepentingan", id_pihakberkepentingan);		    		
		    		r.add("nama_pb", txtNama);
		    		r.add("id_jenisnopb", socJenisNoPB);
		    		r.add("id_bangsa", socBangsa);
		    		r.add("id_warganegara", socWarga);		    		
		    		r.add("no_pb", txtNoPB);	    		
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblpptpihakberkepentingan");
		    		stmt.executeUpdate(sql);
	    	
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updatePB	
	  
	  @SuppressWarnings("unchecked")
	  public static void updatePBwithIdHakmilikAndIdPB(Hashtable data,String id_pihakberkepentingan) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    			    	
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		
		    		//data for update tblpptpihakberkepentingan
		    		String txtNama = (String)data.get("txtNama");
		    		String socJenisNoPB = (String)data.get("socJenisNoPB");
		    		String socBangsa = (String)data.get("socBangsa");
		    		String socWarga = (String)data.get("socWarga");
		    		String txtNoPB = (String)data.get("txtNoPB");
		    		String txtJenisPB = (String)data.get("txtJenisPB");

		    		//data for update tblppthakmilikpb
			    	String socJenisPB = (String)data.get("socJenisPB");
			    	String socNegeri = (String)data.get("socNegeri");		    		
			    	String socBandar = (String)data.get("socBandar");
			    	String txtAlamat1 = (String)data.get("txtAlamat1");
			    	String txtAlamat2 = (String)data.get("txtAlamat2");		    		
			    	String txtAlamat3 = (String)data.get("txtAlamat3");
			    	String txtPoskod = (String)data.get("txtPoskod");
			    	String txtNoTelefon = (String)data.get("txtNoTelefon");
			    	String txtNoBimbit = (String)data.get("txtNoBimbit");
			    	String txtNoFaks = (String)data.get("txtNoFaks");		 
			    		
			    	SQLRenderer rx = new SQLRenderer();
			    	rx.update("id_hakmilik", id_hakmilik);   
			    	rx.update("id_pihakberkepentingan", id_pihakberkepentingan);  
			    	rx.add("id_jenispb", socJenisPB);    		
			    	rx.add("id_negeri", socNegeri);
			    	rx.add("id_bandar", socBandar); 		
			    	rx.add("alamat1", txtAlamat1);
			    	rx.add("alamat2", txtAlamat2);
			    	rx.add("alamat3", txtAlamat3);
			    	rx.add("poskod", txtPoskod);
			    	rx.add("no_tel_rumah", txtNoTelefon);
			    	rx.add("no_handphone", txtNoBimbit);
			    	rx.add("no_fax", txtNoFaks);		    
			    	rx.add("jenis_lain2_pb", txtJenisPB);	
			    	rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
			    	rx.add("id_kemaskini",id_user);    		
			    	sql = rx.getSQLUpdate("Tblppthakmilikpb");
			    	stmt.executeUpdate(sql);
		    	
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_pihakberkepentingan", id_pihakberkepentingan);		    		
		    		r.add("nama_pb", txtNama);
		    		r.add("id_jenisnopb", socJenisNoPB);
		    		r.add("id_bangsa", socBangsa);
		    		r.add("id_warganegara", socWarga);		    		
		    		r.add("no_pb", txtNoPB);		
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblpptpihakberkepentingan");
		    		stmt.executeUpdate(sql);
	    	
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updatePBwithIdHakmilikAndIdPB	
	  
	  @SuppressWarnings("unchecked")
	  public static void setListPB(String id_hakmilik,String nama)throws Exception {
			
		  	listPB = new Vector();
			
		    Db db = null;
		    listPB.clear();
		    String sql = "";
		    
		    String namaPB = nama.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilikpb, a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb, a.id_bangsa, a.id_warganegara, ";
		    		sql += " c.id_negeri, c.id_bandar, a.no_pb, c.jenis_lain2_pb, c.alamat1, c.alamat2, c.alamat3, c.poskod, ";
		    		sql += " c.no_tel_rumah, c.no_handphone, c.no_fax, d.syer_atas, d.syer_bawah, e.keterangan as jenis_pb ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbahagianpb d, Tblrujjenispb e ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND c.id_bahagianpb = d.id_bahagianpb(+)";
		    		sql += " AND c.id_jenispb = e.id_jenispb(+)";
		    		sql += " AND c.id_hakmilik = '"+id_hakmilik+"'";
		    		sql += " AND c.id_jenispb not in (40,41,42)";
		      
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
		    			h.put("jenis_pb", rs.getString("jenis_pb")== null?"":rs.getString("jenis_pb"));
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
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listPB
	  
	  
	  @SuppressWarnings("unchecked")
	  public int setListPB_count(String id_hakmilik,String nama)throws Exception {
			
		     Db db = null;
		    String sql = "";
		    
		    String namaPB = nama.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT count(*) as total ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbahagianpb d, Tblrujjenispb e ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND c.id_bahagianpb = d.id_bahagianpb(+)";
		    		sql += " AND c.id_jenispb = e.id_jenispb(+)";
		    		sql += " AND c.id_hakmilik = '"+id_hakmilik+"'";
		    		sql += " AND c.id_jenispb not in (40,41,42)";
		      
		    		//NAMA PB
					if (nama != "" && nama != null) {
						if (!namaPB.equals("")) {
							sql += " AND UPPER(nama_pb) LIKE '%" + namaPB.toUpperCase() + "%'";
						}
					}//close if namaPB
		    		
					sql += " ORDER BY nama_pb asc";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int total =0;

		    		while (rs.next()) {
		    			total = rs.getInt("total");
		    			
		    			
		      }//close while
		     return total; 
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listPB
	  
	  
	  @SuppressWarnings("unchecked")
	  public static void setDataDetailPB(String id_pihakberkepentingan,String id_hakmilik,int mode)throws Exception {
			
		  	dataPB = new Vector();
			
		    Db db = null;
		    dataPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = " SELECT DISTINCT ROWNUM,b.id_hakmilikpb,b.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, b.id_jenispb, a.id_bangsa, a.id_warganegara, "; 
		    		sql += " b.id_negeri, b.id_bandar, a.no_pb, b.jenis_lain2_pb, c.syer_atas, c.syer_bawah, b.alamat1, b.alamat2, b.alamat3, b.poskod, ";
		    		sql += " b.no_tel_rumah, b.no_handphone, b.no_fax, b.id_bahagianpb, b.nama_bank, b.no_akaun ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilikpb b, Tblpptbahagianpb c ";
		    		sql += " WHERE b.id_pihakberkepentingan = a.id_pihakberkepentingan(+) ";
		    		sql += " AND b.id_bahagianpb = c.id_bahagianpb(+)";
		    		sql += " AND b.id_pihakberkepentingan = '"+id_pihakberkepentingan+"'";		    		
		    		if(mode!=1){
		    			sql += " AND b.id_hakmilik = '"+id_hakmilik+"'";			    			
		    		}	
		    		if(mode==1){
		    			sql += " AND ROWNUM = '1' ";		    			
		    		}		    		
		    		sql += " ORDER BY b.id_hakmilikpb ";
		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;

		    		while (rs.next()) {
		    			h = new Hashtable();	
		    			h.put("nama_bank", rs.getString("nama_bank")== null?"":rs.getString("nama_bank"));
		    			h.put("no_akaun", rs.getString("no_akaun")== null?"":rs.getString("no_akaun"));
		    			h.put("id_bahagianpb", rs.getString("id_bahagianpb")== null?"":rs.getString("id_bahagianpb"));
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
		    			
		    			dataPB.addElement(h);
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close dataPB
	  
	  
	  public static void hapusPB(String id_pihakberkepentingan,String id_hakmilik) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM Tblppthakmilikpb where id_pihakberkepentingan = '"+id_pihakberkepentingan+"'";
	    		sql += " AND id_hakmilik = '"+id_hakmilik+"'";
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusHM
	  
	@SuppressWarnings("unchecked")
	public static void hantar(Hashtable data) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		//status maklumat hakmilik
	    		String status = "16";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);	   
	    		r.add("id_status",status);  
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hantar
	  
	  @SuppressWarnings("unchecked")
	  public static void simpanBebanan(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		
		    		String txdTarikhDaftar = (String)data.get("txdTarikhDaftar");
		    		String txtPerserahan = (String)data.get("txtPerserahan");
		    		String txtNama = (String)data.get("txtNama");
		    		String socJenisNoPB = (String)data.get("socJenisNoPB");
		    		String txtNoPB = (String)data.get("txtNoPB");
		    		String txtAlamat1 = (String)data.get("txtAlamat1");
		    		
		    		String txtAlamat2 = (String)data.get("txtAlamat2");	 
		    		String txtAlamat3 = (String)data.get("txtAlamat3");
		    		String txtPoskod = (String)data.get("txtPoskod");
		    		String socNegeri = (String)data.get("socNegeri");
		    		String socBandar = (String)data.get("socBandar");	
		    		String socJenisBebanan = (String)data.get("socJenisBebanan");
		    		String keterangan_bebanan = (String)data.get("keterangan_bebanan");	
		    	
		    		String TD = "to_date('" + txdTarikhDaftar + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_hakmilik", id_hakmilik);		    				
		    		r.add("no_perserahan", txtPerserahan);
		    		r.add("nama", txtNama);
		    		r.add("keterangan_bebanan", keterangan_bebanan);
		    		r.add("id_jenisnopb", socJenisNoPB);
		    		r.add("no_pb", txtNoPB);
		    		r.add("alamat1", txtAlamat1);
		    		r.add("alamat2", txtAlamat2);
		    		r.add("alamat3", txtAlamat3);
		    		r.add("poskod", txtPoskod);
		    		r.add("id_negeri", socNegeri);
		    		r.add("id_bandar", socBandar);
		    		r.add("id_kodbebanan", socJenisBebanan);
		    		r.add("tarikh_daftar",r.unquote(TD));
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("tblpptbebanan");
		    		stmt.executeUpdate(sql);
	    	
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanBebanan
	  
	  
	  @SuppressWarnings("unchecked")
	  public static void updateBebanan(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_bebanan = (String)data.get("id_bebanan");
		    		
		    		
		    		String txdTarikhDaftar = (String)data.get("txdTarikhDaftar");
		    		String txtPerserahan = (String)data.get("txtPerserahan");
		    		String txtNama = (String)data.get("txtNama");
		    		String socJenisNoPB = (String)data.get("socJenisNoPB");
		    		String txtNoPB = (String)data.get("txtNoPB");
		    		String txtAlamat1 = (String)data.get("txtAlamat1");
		    		
		    		String txtAlamat2 = (String)data.get("txtAlamat2");	 
		    		String txtAlamat3 = (String)data.get("txtAlamat3");
		    		String txtPoskod = (String)data.get("txtPoskod");
		    		String socNegeri = (String)data.get("socNegeri");
		    		String socBandar = (String)data.get("socBandar");	
		    		String socJenisBebanan = (String)data.get("socJenisBebanan");
		    		
		    		String keterangan_bebanan = (String)data.get("keterangan_bebanan");	
			    	
		    	
		    		String TD = "to_date('" + txdTarikhDaftar + "','dd/MM/yyyy')";
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_bebanan", id_bebanan);		    				
		    		r.add("no_perserahan", txtPerserahan);
		    		r.add("nama", txtNama);
		    		r.add("keterangan_bebanan", keterangan_bebanan);
		    		r.add("id_jenisnopb", socJenisNoPB);
		    		r.add("no_pb", txtNoPB);
		    		r.add("alamat1", txtAlamat1);
		    		r.add("alamat2", txtAlamat2);
		    		r.add("alamat3", txtAlamat3);
		    		r.add("poskod", txtPoskod);
		    		r.add("id_negeri", socNegeri);
		    		r.add("id_bandar", socBandar);
		    		r.add("id_kodbebanan", socJenisBebanan);
		    		r.add("tarikh_daftar",r.unquote(TD));
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("tblpptbebanan");
		    		stmt.executeUpdate(sql);
	    	
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateBebanan
	  
	  
	  @SuppressWarnings("unchecked")
	  public static void listBebanan(String id_hakmilik,String noSerah)throws Exception {
			
		  	listBebanan = new Vector();
			
		    Db db = null;
		    listBebanan.clear();
		    String sql = "";
		    
		    String noPerserahan = noSerah.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT a.id_bebanan, a.no_perserahan, a.id_kodbebanan, a.id_jenisnopb, a.no_pb, a.nama, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.id_negeri, ";
		    		sql += " a.id_hakmilik, a.id_bandar, b.keterangan as jenis_bebanan, a.tarikh_daftar";
		    		sql += " FROM Tblpptbebanan a, Tblrujbebanan b ";
		    		sql += " WHERE a.id_kodbebanan = b.id_bebanan(+) ";
		    		sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
		    		
		    		//NO SERAH
					if (noSerah != "" && noSerah != null) {
						if (!noPerserahan.equals("")) {
							sql += " AND UPPER(a.no_perserahan) LIKE '%" + noPerserahan.toUpperCase() + "%'";
						}
					}//close if noserah
		    		
					sql += " ORDER BY a.no_perserahan";
					
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("id_bebanan", rs.getString("id_bebanan")== null?"":rs.getString("id_bebanan"));
		    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
		    			h.put("no_perserahan", rs.getString("no_perserahan")== null?"":rs.getString("no_perserahan"));
		    			h.put("id_kodbebanan", rs.getString("id_kodbebanan")== null?"":rs.getString("id_kodbebanan"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));		    			
		    			h.put("nama", rs.getString("nama")== null?"":rs.getString("nama"));
		    			h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    			h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    			h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    			h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
		    			h.put("jenis_bebanan", rs.getString("jenis_bebanan")== null?"":rs.getString("jenis_bebanan"));
		    			h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
		    			listBebanan.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listBebanan
	  
	  @SuppressWarnings("unchecked")
	  public static void dataBebanan(String id_bebanan)throws Exception {
			
		  	dataBebanan = new Vector();
			
		    Db db = null;
		    dataBebanan.clear();
		    String sql = "";
		    
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT a.keterangan_bebanan,a.id_bebanan, a.no_perserahan, a.id_kodbebanan, a.id_jenisnopb, a.no_pb, a.nama, a.alamat1, a.alamat2, a.alamat3, a.poskod, a.id_negeri, ";
		    		sql += " a.id_hakmilik, a.id_bandar, b.keterangan as jenis_bebanan, a.tarikh_daftar";
		    		sql += " FROM Tblpptbebanan a, Tblrujbebanan b ";
		    		sql += " WHERE a.id_kodbebanan = b.id_bebanan(+) ";
		    		sql += " AND a.id_bebanan = '"+id_bebanan+"'";
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("keterangan_bebanan", rs.getString("keterangan_bebanan")== null?"":rs.getString("keterangan_bebanan"));
		    			h.put("id_bebanan", rs.getString("id_bebanan")== null?"":rs.getString("id_bebanan"));
		    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
		    			h.put("no_perserahan", rs.getString("no_perserahan")== null?"":rs.getString("no_perserahan"));
		    			h.put("id_kodbebanan", rs.getString("id_kodbebanan")== null?"":rs.getString("id_kodbebanan"));
		    			h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    			h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));		    			
		    			h.put("nama", rs.getString("nama")== null?"":rs.getString("nama"));
		    			h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    			h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    			h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    			h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    			h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    			h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
		    			h.put("jenis_bebanan", rs.getString("jenis_bebanan")== null?"":rs.getString("jenis_bebanan"));
		    			h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
		    			dataBebanan.addElement(h);
		    		
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close dataBebanan
	  
	  
	  public static void hapusBebanan(String id_bebanan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptbebanan where id_bebanan = '"+id_bebanan+"'";
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusHM
	  
	  
	  @SuppressWarnings("unchecked")
	  public static void setCheckExistPB(String jenisNoPB,String noPB)throws Exception {
			
		  	checkExistPB = new Vector();
			
		    Db db = null;
		    checkExistPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilik,a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb, a.id_bangsa, a.id_warganegara, ";
		    		sql += " c.id_negeri, c.id_bandar, a.no_pb, c.jenis_lain2_pb, d.syer_atas, d.syer_bawah, c.alamat1, c.alamat2, c.alamat3, c.poskod, ";
		    		sql += " c.no_tel_rumah, c.no_handphone, c.no_fax ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbahagianpb d";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND c.id_bahagianpb = d.id_bahagianpb(+)";
		    		sql += " AND a.id_jenisnopb = '"+jenisNoPB+"'";
		    		sql += " AND a.no_pb = '"+noPB+"'";
		    		sql += " AND c.id_jenispb not in (40,41,42)";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
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
		    			checkExistPB.addElement(h);

		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setCheckExistPB
	  
	  @SuppressWarnings("unchecked")
	  public static void setSizeExistPB(String idHakmilik,String idpb)throws Exception {
			
		  	checkSizeExistPB = new Vector();
			
		    Db db = null;
		    checkSizeExistPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT c.id_hakmilikpb,c.id_hakmilik,a.id_pihakberkepentingan, a.nama_pb, a.id_jenisnopb, c.id_jenispb, a.id_bangsa, a.id_warganegara, ";
		    		sql += " c.id_negeri, c.id_bandar, a.no_pb, c.jenis_lain2_pb, d.syer_atas, d.syer_bawah, c.alamat1, c.alamat2, c.alamat3, c.poskod, ";
		    		sql += " c.no_tel_rumah, c.no_handphone, c.no_fax ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptbahagianpb d";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
		    		sql += " AND c.id_bahagianpb = d.id_bahagianpb(+) ";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND c.id_hakmilik = '"+idHakmilik+"'";
		    		sql += " AND a.id_pihakberkepentingan = '"+idpb+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("id_hakmilikpb", rs.getString("id_hakmilikpb")== null?"":rs.getString("id_hakmilikpb"));
		    			h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
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
		    			checkSizeExistPB.addElement(h);

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setSizeExistPB
	  
	  @SuppressWarnings("unchecked")
	  public static void setTotalSyer(String id_hakmilik,String idpb)throws Exception {
			
		  totalSyer = new Vector();
			
		    Db db = null;
		    totalSyer.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		// Kemaskini oleh Mohamad Rosli  pada 2014/01/09, besarkan range SHER 
		    		//sql = " SELECT TO_CHAR(SUM((B.SYER_ATAS) /  NULLIF( B.SYER_BAWAH, 0 )), '999.99') AS TOTAL  ";
		    		sql = " SELECT TO_CHAR(SUM((B.SYER_ATAS) /  NULLIF( B.SYER_BAWAH, 0 )), '999.9999') AS TOTAL  ";
		    		sql += " FROM TBLPPTHAKMILIKPB A, TBLPPTBAHAGIANPB B, TBLPPTPIHAKBERKEPENTINGAN C ";
		    		sql += " WHERE A.ID_BAHAGIANPB = B.ID_BAHAGIANPB ";
		    		sql += " AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
		    		sql += " AND A.ID_HAKMILIKPB = (SELECT MIN(ID_HAKMILIKPB) FROM TBLPPTHAKMILIKPB WHERE TBLPPTHAKMILIKPB.ID_BAHAGIANPB = B.ID_BAHAGIANPB) ";
					sql += " AND A.ID_HAKMILIK = '"+id_hakmilik+"'";
		      
		    		if(idpb!="" && idpb != null){
		    			sql += " AND a.id_pihakberkepentingan <> '"+idpb+"'";
		    		}
		    		
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    	

		    		while (rs.next()) {
		    			h = new Hashtable();	    			
		    			h.put("total", rs.getString("TOTAL")== null?"":rs.getString("TOTAL"));
		    			
		    			if(rs.getString("TOTAL")!=null){
		    				
		    				if(rs.getDouble("TOTAL") < 1.00){
		    					h.put("hideAdd","notcomplete");
		    				}else{
		    					h.put("hideAdd","yes");
		    				}
		    				
		    			}else{
		    				h.put("hideAdd","no");
		    			}
		    			
		    			totalSyer.addElement(h);
		    		
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setTotalSyer
	  
	  public static void janaSubjaket(String idHakmilik,int bil,String idpermohonan,String id_user) throws Exception{
			
		  Db db = null;
		  String sql = "";

		  try{
			  	
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", idHakmilik);		    				
	    		r.add("no_subjaket", bil);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblppthakmilik");
	    		stmt.executeUpdate(sql);
	    		
	    		r.clear();
	    		
	    		//update flag di tblpptpermohonan
	    		r.update("id_permohonan", idpermohonan);		    				
	    		r.add("flag_subjaket", "1");
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close janaSubjaket
	  
	  public static void simpanSj(String idHakmilik,String sj,String idpermohonan,String id_user) throws Exception{
			
		  Db db = null;
		  String sql = "";

		  try{
			  	
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", idHakmilik);		    				
	    		r.add("no_subjaket", sj);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblppthakmilik");
	    		stmt.executeUpdate(sql);
	    		
	    		r.clear();
	    		
	    		//update flag di tblpptpermohonan
	    		r.update("id_permohonan", idpermohonan);		    				
	    		r.add("flag_subjaket", "1");
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("Tblpptpermohonan");
	    		stmt.executeUpdate(sql);
	    		
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close simpanSj
	  
	  @SuppressWarnings("unchecked")
	  public static void listPBwithAward(String id_hakmilik,String nama)throws Exception {
			
		  listPBwithAward = new Vector();
			
		    Db db = null;
		    listPBwithAward.clear();
		    String sql = "";
		    
		    String namaPB = nama.trim();
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT DISTINCT d.id_award,d.penama, ";
		    		sql += " d.ulasan, d.tarikh_sedia_bayaran, d.pampasan_tanah, d.jumlah_pelarasan, d.tempoh_lewat, d.faedah_sebelum_pu, ";
		    		sql += " d.faedah_selepas_pu, d.tarikh_surat, d.bil_surat, d.tempoh_akhir_bayar_semula, d.status_bayar, ";
		    		
		    		sql +=	"c.id_hakmilikpb, a.id_pihakberkepentingan, upper(a.nama_pb) AS nama_pb, a.id_jenisnopb, c.id_jenispb, a.id_bangsa, a.id_warganegara, ";
		    		sql += " c.id_negeri, c.id_bandar, a.no_pb, c.jenis_lain2_pb, f.syer_atas, f.syer_bawah, c.alamat1, c.alamat2, c.alamat3, c.poskod, ";
		    		sql += " c.no_tel_rumah, c.no_handphone, c.no_fax ";
		    		sql += " FROM Tblpptpihakberkepentingan a, Tblppthakmilik b, Tblppthakmilikpb c, Tblpptaward d, Tblrujjenispb e, ";
		    		sql += " Tblpptbahagianpb f ";
		    		sql += " WHERE c.id_hakmilik = b.id_hakmilik(+)";
		    		sql += " AND c.id_jenispb = e.id_jenispb(+)";
		    		sql += " AND c.id_pihakberkepentingan = a.id_pihakberkepentingan(+)";
		    		sql += " AND d.id_hakmilikpb = c.id_hakmilikpb";
		    		sql += " AND c.id_bahagianpb = f.id_bahagianpb(+)";
		    		//sql += " AND NVL(e.jenis_daftar_pb,0) = 'Y' ";
		    		sql += " AND c.id_hakmilik = '"+id_hakmilik+"'";
		    		sql += " AND c.id_jenispb not in (40,41,42)";
		      
		    		//NAMA PB
					if (nama != "" && nama != null) {
						if (!namaPB.equals("")) {
							sql += " AND UPPER(nama_pb) LIKE '%" + namaPB.toUpperCase() + "%'";
						}
					}//close if namaPB
		    		
					sql += " ORDER BY nama_pb asc";
					
					myLogger.info(" listPBwithAward :"+sql);
					
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
		    			
		    			listPBwithAward.addElement(h);
		    			bil++;
		    			
		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close listPBwithAward
	  
	@SuppressWarnings("unchecked")
	public static void setSizeBahagianPB(String idHakmilik)throws Exception {
			
		sizeBahagianPB = new Vector();
			
		    Db db = null;
		    sizeBahagianPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT B.ID_BAHAGIANPB, C.NAMA_PB "; 
				    sql += " FROM TBLPPTHAKMILIKPB A, TBLPPTBAHAGIANPB B, TBLPPTPIHAKBERKEPENTINGAN C ";
				    sql += " WHERE A.ID_BAHAGIANPB = B.ID_BAHAGIANPB ";
				    sql += " AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
				    sql += " AND A.ID_HAKMILIK = '"+idHakmilik+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("id_bahagianpb", rs.getString("ID_BAHAGIANPB")== null?"":rs.getString("ID_BAHAGIANPB"));   			
		    			sizeBahagianPB.addElement(h);

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setSizeBahagianPB
	  
	
	@SuppressWarnings("unchecked")
	  public static void simpanBahagianPB(Hashtable data,String id_bahagianpb) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");

		    		String txtSyorAtas = (String)data.get("txtSyorAtas");
		    		String txtSyorBawah = (String)data.get("txtSyorBawah");

		    		SQLRenderer rx = new SQLRenderer();
		    		rx.add("id_bahagianpb",id_bahagianpb);    		
		    		rx.add("syer_atas", txtSyorAtas);
		    		rx.add("syer_bawah", txtSyorBawah);
		    		rx.add("tarikh_masuk",rx.unquote("sysdate"));
		    		rx.add("id_masuk",id_user); 		    
		    		sql = rx.getSQLInsert("Tblpptbahagianpb");
		    		stmt.executeUpdate(sql);
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanBahagianPB
	
	@SuppressWarnings("unchecked")
	public static void updateBahagianPB(Hashtable data,String id_bahagianpb) throws Exception{
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");

		    		String txtSyorAtas = (String)data.get("txtSyorAtas");
		    		String txtSyorBawah = (String)data.get("txtSyorBawah");

		    		SQLRenderer rx = new SQLRenderer();
		    		rx.update("id_bahagianpb",id_bahagianpb);    		
		    		rx.add("syer_atas", txtSyorAtas);
		    		rx.add("syer_bawah", txtSyorBawah);
		    		rx.add("tarikh_kemaskini",rx.unquote("sysdate"));
		    		rx.add("id_kemaskini",id_user); 
		    		sql = rx.getSQLUpdate("Tblpptbahagianpb");
		    		stmt.executeUpdate(sql);
		    		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
	}//close updateBahagianPB
	
	@SuppressWarnings("unchecked")
	public static void setDataExistBahagianPB(String idBahagianPB)throws Exception {
			
		dataExistBahagianPB = new Vector();
			
		    Db db = null;
		    dataExistBahagianPB.clear();
		    String sql = "";
		    
		    try {
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		      
		    		sql = "SELECT B.ID_BAHAGIANPB, C.NAMA_PB, B.SYER_ATAS, B.SYER_BAWAH "; 
				    sql += " FROM TBLPPTHAKMILIKPB A, TBLPPTBAHAGIANPB B, TBLPPTPIHAKBERKEPENTINGAN C ";
				    sql += " WHERE A.ID_BAHAGIANPB = B.ID_BAHAGIANPB ";
				    sql += " AND A.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN ";
				    sql += " AND B.ID_BAHAGIANPB = '"+idBahagianPB+"'";
		      
		    		ResultSet rs = stmt.executeQuery(sql);
		      
		    		Hashtable h;
		    		
		    		while (rs.next()) {
		    			h = new Hashtable();	    		
		    			h.put("id_bahagianpb", rs.getString("ID_BAHAGIANPB")== null?"":rs.getString("ID_BAHAGIANPB"));   
		    			h.put("syer_atas", rs.getString("SYER_ATAS")== null?"":rs.getString("SYER_ATAS")); 
		    			h.put("syer_bawah", rs.getString("SYER_BAWAH")== null?"":rs.getString("SYER_BAWAH")); 
		    			dataExistBahagianPB.addElement(h);

		      }//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}// 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataExistBahagianPB
	
	
	public static void updatePA(String id_user,String id_hakmilikpb,String id_pa) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	    	
	    	 db = new Db();
	    	 conn = db.getConnection();
	    	 conn.setAutoCommit(false);
	    	 Statement stmt = db.getStatement();
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilikpb", id_hakmilikpb);
	    	 r.add("id_pa1", id_pa);	
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("Tblppthakmilikpb");
	    	 stmt.executeUpdate(sql);	 
	    	 
	    	 conn.commit();
	    	 
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }finally {
	      if (db != null) db.close();
	    }
	  }//close updateCatatan
	
	@SuppressWarnings("unchecked")
	public static Vector getDataPA(String id_hakmilikpb)throws Exception {
		    
		Db db = null;
		String sql = "";
		
		try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT ID_PA1 FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB = '"+id_hakmilikpb+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("ID_PA1", rs.getString("ID_PA1")==null?"":rs.getString("ID_PA1"));
		    	  list.addElement(h);
		 }
		   return list;
		 } catch (Exception re) {
			 log.error("Error: ", re);
			 throw re;
			 } finally {
		      if (db != null) db.close();
		    }
	}//close getDataPA
	
	@SuppressWarnings("unchecked")
	public static void setListCarianPopupHM(String txtNoFail, String txtNolot, 
											String txtNoHakmilik, String txtNamaPB, String userIdNegeri, String id_hakmilik)throws Exception {
		
		listcarianHM = new Vector();
		
	    Db db = null;
	    listcarianHM.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  " SELECT A.ID_FAIL, B.ID_PERMOHONAN, C.ID_HAKMILIK, C.ID_JENISHAKMILIK, C.ID_MUKIM, B.ID_DAERAH, ";
	    		sql += " D.NAMA_MUKIM, E.NAMA_DAERAH, G.KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, I.TARIKH_BORANGK, ";
	    		sql += " CASE ";
	    		sql += " WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NULL THEN C.NO_LOT "; 
	    		sql += " WHEN C.NO_LOT IS NULL AND C.NO_PT IS NOT NULL THEN  F.KETERANGAN || C.NO_PT ";    
	    		sql += " WHEN C.NO_LOT IS NOT NULL AND C.NO_PT IS NOT NULL THEN F.KETERANGAN || C.NO_PT || CHR(32) || CHR(40) || C.NO_LOT || CHR(41) "; 
	    		sql += " ELSE ''  ";
	    		sql += " END AS NO_LOTPT, ";
	    		sql += " C.ID_UNITLUASAMBIL_CONVERT, C.LUAS_AMBIL, C.LUAS_LOT, C.ID_UNITLUASLOT_CONVERT ";
	    		sql += " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, ";
	    		sql += " TBLRUJMUKIM D, TBLRUJDAERAH E, TBLRUJLOT F, TBLRUJJENISHAKMILIK G, TBLPPTHAKMILIKBORANGK H, TBLPPTBORANGK I ";
	    		sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
	    		sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
	    		sql += " AND A.ID_SUBURUSAN = '52' ";
	    		sql += " AND C.ID_MUKIM = D.ID_MUKIM(+) ";
	    		sql += " AND B.ID_DAERAH = E.ID_DAERAH(+) ";
	    		sql += " AND C.ID_LOT = F.ID_LOT(+) ";
	    		sql += " AND C.ID_JENISHAKMILIK = G.ID_JENISHAKMILIK(+) ";
	    		sql += " AND C.ID_HAKMILIK = H.ID_HAKMILIK(+) ";
	    		sql += " AND H.ID_BORANGK = I.ID_BORANGK(+) ";
	    		
	    		if(!id_hakmilik.equals("") && !id_hakmilik.isEmpty()){
	    			sql += " AND C.ID_HAKMILIK = "+id_hakmilik+" ";
	    		}
	    		
	    		if(!txtNamaPB.equals("") && !txtNamaPB.isEmpty()){
		    		sql += " AND C.ID_HAKMILIK IN (SELECT H.ID_HAKMILIK FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB  ";
		    		sql += " WHERE H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN ";
		    		sql += " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+txtNamaPB+"'|| '%') )  ";
	    		}
	    		
	    		if(!txtNoHakmilik.equals("") && !txtNoHakmilik.isEmpty()){
	    			sql += " AND C.ID_HAKMILIK IN (SELECT H1.ID_HAKMILIK FROM TBLPPTHAKMILIK H1 WHERE H1.ID_PERMOHONAN = B.ID_PERMOHONAN AND TRIM(H1.NO_HAKMILIK) LIKE UPPER('%' ||'"+txtNoHakmilik+"'|| '%') ) "; 
	    		}
	    		
	    		if(!txtNolot.equals("") && !txtNolot.isEmpty()){
	    			sql += " AND C.ID_HAKMILIK IN (SELECT H1.ID_HAKMILIK FROM TBLPPTHAKMILIK H1 WHERE H1.ID_PERMOHONAN = B.ID_PERMOHONAN ";
	    			sql += " AND UPPER(H1.NO_LOT) LIKE UPPER('%' ||'"+txtNolot+"'|| '%') OR UPPER(H1.NO_PT) LIKE UPPER('%' ||'"+txtNolot+"'|| '%')) ";
	    		}
	    		
	    		//sql += " AND C.ID_HAKMILIK IN (SELECT (C1.ID_HAKMILIK) FROM TBLPPTHAKMILIK C1, TBLPPTBORANGK K1, TBLPPTHAKMILIKBORANGK HK1 ";
	    		//sql += " WHERE C1.ID_PERMOHONAN = B.ID_PERMOHONAN AND HK1.ID_HAKMILIK = C1.ID_HAKMILIK AND HK1.ID_BORANGK = K1.ID_BORANGK) ";
	    		
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += " AND A.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += " AND A.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
	    		//NO FAIL
				if (txtNoFail != "" && txtNoFail != null) {
						sql += " AND (UPPER(A.no_fail) LIKE '%" + txtNoFail.toUpperCase() + "%' " +
							" OR UPPER(B.no_rujukan_ptg) LIKE '%" + txtNoFail.toUpperCase() + "%' " +
							" OR UPPER(B.no_rujukan_upt) LIKE '%" + txtNoFail.toUpperCase() + "%' " +
							" OR UPPER(B.no_rujukan_ptd) LIKE '%" + txtNoFail.toUpperCase() + "%')"; 
				}//close carian by nofail
		
	    		sql +=" ORDER by B.tarikh_permohonan desc, A.no_fail desc ";
	      
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("TARIKH_BORANGK", rs.getDate("TARIKH_BORANGK")==null?"":Format.format(rs.getDate("TARIKH_BORANGK")));
	    			h.put("ID_DAERAH", rs.getString("ID_DAERAH")== null?"":rs.getString("ID_DAERAH"));
	    			h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK")== null?"":rs.getString("ID_JENISHAKMILIK"));
	    			h.put("ID_MUKIM", rs.getString("ID_MUKIM")== null?"":rs.getString("ID_MUKIM"));
	    			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")== null?"":rs.getString("ID_HAKMILIK"));
	    			h.put("NO_LOTPT", rs.getString("NO_LOTPT")== null?"":rs.getString("NO_LOTPT"));
	    			h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK")== null?"":rs.getString("KOD_JENIS_HAKMILIK"));
	    			h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")== null?"":rs.getString("NO_HAKMILIK"));
	    			h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM")== null?"":rs.getString("NAMA_MUKIM"));
	    			h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH")== null?"":rs.getString("NAMA_DAERAH"));
	    			h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT")== null?"":rs.getString("ID_UNITLUASAMBIL_CONVERT"));
	    			h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT")== null?"":rs.getString("ID_UNITLUASLOT_CONVERT"));
	    			if(rs.getString("LUAS_AMBIL") != null && rs.getString("LUAS_AMBIL") != ""){
						
						double luasAmbil = rs.getDouble("LUAS_AMBIL");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("LUAS_AMBIL",LA);
								
					}else{
						h.put("LUAS_AMBIL","0");
					}
	    			
	    			double LL = 0;
					if(rs.getString("LUAS_LOT") != null){
						
						if(rs.getString("ID_UNITLUASLOT_CONVERT")!=null){
							if(rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")){
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
						h.put("LUAS_LOT",luasLot);
								
					}else{
						h.put("LUAS_LOT","");
					}
	    			
	    			listcarianHM.addElement(h);
	    			bil++;
	    			
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close carian
	
	

	//PENAMBAHAN YATI
	
			public Vector senarai_siasatan(String id_permohonan,String id_siasatan) throws Exception {
					Vector senarai_siasatan = new Vector();
					senarai_siasatan.clear();
					Db db = null;
					String sql = "";
					try {
						db = new Db();
						Statement stmt = db.getStatement();
						sql = " SELECT ID_SIASATAN, BIL_SIASATAN FROM TBLPPTSIASATAN " +
								" WHERE ID_PERMOHONAN = "+id_permohonan+" AND ID_HAKMILIK IN (SELECT ID_HAKMILIK FROM TBLPPTSIASATAN WHERE ID_SIASATAN = '"+id_siasatan+"') ";
						sql += "ORDER BY BIL_SIASATAN ASC";
						myLogger.info(" SQL SIASATAN LIST FROM MODEL :"+sql);
						ResultSet rs = stmt.executeQuery(sql);
						Hashtable h;
						while (rs.next()) {
							h = new Hashtable();
							h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "" : rs.getString("BIL_SIASATAN"));
							h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? "" : rs.getString("ID_SIASATAN"));
							senarai_siasatan.addElement(h);	
							
						}
						myLogger.info(" SENARAI SIASATAN FROM MODEL :"+senarai_siasatan);
						return senarai_siasatan;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
				}	

	/*
		public Vector senarai_siasatan(String id_permohonan) throws Exception {
				Vector senarai_siasatan = new Vector();
				senarai_siasatan.clear();
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					sql = " SELECT ID_SIASATAN, BIL_SIASATAN FROM TBLPPTSIASATAN WHERE ID_PERMOHONAN = "+id_permohonan+" ";
					sql += "ORDER BY BIL_SIASATAN ASC";
					myLogger.info(" SQL SIASATAN LIST FROM MODEL :"+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h;
					while (rs.next()) {
						h = new Hashtable();
						h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "" : rs.getString("BIL_SIASATAN"));
						h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? "" : rs.getString("ID_SIASATAN"));
						senarai_siasatan.addElement(h);	
						
					}
					myLogger.info(" SENARAI SIASATAN FROM MODEL :"+senarai_siasatan);
					return senarai_siasatan;
				}catch (Exception re) {
					throw re;
				} finally {
					if (db != null)
						db.close();
				}
			}	
			*/

	
}//close class
