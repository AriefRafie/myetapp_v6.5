package ekptg.model.ppt;

import java.sql.ResultSet;
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

public class FrmSek8LaporanAwalTanahData {
	static Logger myLogger = Logger.getLogger(FrmSek8LaporanAwalTanahData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmSek8LaporanAwalTanahData.class);
	
	private static  Vector listcarian = null;
	private static  Vector dataLaporanTanah = null;
	private static  Vector totalLuasAmbil = null;
	private static  Vector totalLot = null;
	
	public static  Vector getTotalLot(){
		return totalLot;
	}
	
	public static  Vector getListCarian(){
		return listcarian;
	}

	public static  Vector getDataLaporanTanah(){
		return dataLaporanTanah;
	}
	
	public static  Vector getTotalLuasAmbil(){
		return totalLuasAmbil;
	}
	
	
	public static String sqlListPermohonan(String userIdNegeri)
	{

		String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		sql +="AND p.id_status = s.id_status ";
		sql +="AND f.id_suburusan = '52' ";
		//sql +="AND p.id_status IN (147,148)";
		
		sql += " and (p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx "; 
		sql += " where hx.id_permohonan = p.id_permohonan and hx.id_pegawai is not null) ";      
		sql += " OR p.id_permohonan in (select distinct hx.id_permohonan from Tblppthakmilik hx, Tblpptagihanhm aghm " +
		   		   " where hx.id_permohonan = p.id_permohonan and aghm.id_hakmilik = hx.id_hakmilik and aghm.baris = '2' )";
		sql += " OR p.id_permohonan in (select distinct tx.id_permohonan from Tblppttanahumum tx ";
		sql += " where tx.id_permohonan = p.id_permohonan)";
		sql +=	 " ) ";
		
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
	    		sql +="AND p.id_status IN (147,148)";
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
	public static void simpanLaporan(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		String id_pegawai = (String)data.get("id_pegawai");
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
	    		//String unitLuas = (String)data.get("unitLuas");
	    		String txtKosTanah = (String)data.get("txtKosTanah");
	    		String txtKosBangunan = (String)data.get("txtKosBangunan");
	    		String txtPerihal = (String)data.get("txtPerihal");	    	
	    		String txtTanahSendiri = (String)data.get("txtTanahSendiri");
	    		String txtTanahNegeri = (String)data.get("txtTanahNegeri");
	    		String txtTanahPersekutuan = (String)data.get("txtTanahPersekutuan");
	    		String txtKeadaanTanah = (String)data.get("txtKeadaanTanah");    
	    		String txtKemudahanAwam = (String)data.get("txtKemudahanAwam");    
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtTanahMelayu = (String)data.get("txtTanahMelayu");
	    		String txtTanahKerajaan = (String)data.get("txtTanahKerajaan");
	    		String txtSyor = (String)data.get("txtSyor");
	    		String txtNoPeta = (String)data.get("txtNoPeta");
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
				String txtNilai = (String)data.get("txtNilai");
	    		String txtKosProjek = (String)data.get("txtKosProjek");
	    		
	    		
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("harga_anggar_projek", txtKosProjek);
	    		r.add("id_unitluas", id_unitluas);
	    		r.add("id_pegawai", id_pegawai);
	    		r.add("no_peta_kadaster", txtNoPeta);
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    		
	    		r.add("luas_terlibat",txtLuasKeseluruhan);
	    		r.add("harga_anggar",txtKosTanah);
	    		r.add("pendahuluan",txtPendahuluan);
				r.add("nilai_tanah",txtNilai); //tambah yati					
	    		r.add("harga_anggar_bangunan",txtKosBangunan);
	    		r.add("perihal_bangunan",txtPerihal);	    		
	    		r.add("perihal_tm_sendiri",txtTanahSendiri);
	    		r.add("perihal_tr_negeri",txtTanahNegeri);
	    		r.add("perihal_tmtr_sekutuan",txtTanahPersekutuan);
	    		r.add("keadaan_rupabumi",txtKeadaanTanah);
	    		r.add("kemudahan_awam",txtKemudahanAwam);
	    		r.add("lokasi_tanah",txtLokasi);	    		
	    		r.add("perihal_kawasan_simpan",txtTanahMelayu);
	    		r.add("perihal_tnh_kerajaan",txtTanahKerajaan);
	    		r.add("ulasan_syor",txtSyor);	    		
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user); 
	    		
	    		sql = r.getSQLInsert("tblppttanahumum");
	    		myLogger.info("sql simpan laporan tanah: "+sql);
	    		stmt.executeUpdate(sql);	    
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close simpanLaporan
	
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String statusLaporanTanah = "147";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("id_status",statusLaporanTanah);
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
	   
	}//close updateStatus
	
	
	
	
	@SuppressWarnings("unchecked")
	public static void setDataLaporanTanah(String idpermohonan,String idpegawai)throws Exception {
		
		dataLaporanTanah = new Vector();
		
	    Db db = null;
	    dataLaporanTanah.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT DISTINCT id_tanahumum,id_permohonan,id_pegawai,tarikh_mula_chart,tarikh_akhir_chart,tarikh_lawatan,lokasi_tanah,keadaan_rupabumi, ";
	    		sql += " perihal_bangunan,kemudahan_awam,ulasan_syor,perihal_tm_sendiri,perihal_tnh_kerajaan,bil_hakmilik,id_unitluas,luas_terlibat, ";
	    		sql += " harga_anggar,harga_anggar_bangunan,perihal_tmtr_sekutuan,perihal_tr_negeri,perihal_kawasan_simpan,no_peta_kadaster,tarikh_lapor, "; 
	    		sql += " pendahuluan, nilai_tanah, harga_anggar_projek ";
	    		sql += " FROM tblppttanahumum ";
	    		sql += " WHERE id_permohonan = '"+idpermohonan+"'";
	    		sql += " AND id_pegawai = '"+idpegawai+"'";
	    		myLogger.info("setDataLaporanTanah :: "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("harga_anggar_projek", rs.getString("harga_anggar_projek")== null?"":rs.getString("harga_anggar_projek"));
	    			h.put("pendahuluan", rs.getString("pendahuluan")== null?"":rs.getString("pendahuluan"));
					h.put("nilai_tanah", rs.getString("nilai_tanah")== null?"":rs.getString("nilai_tanah"));
	    			h.put("id_tanahumum", rs.getString("id_tanahumum")== null?"":rs.getString("id_tanahumum"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_peta_kadaster", rs.getString("no_peta_kadaster")== null?"":rs.getString("no_peta_kadaster"));
	    			h.put("lokasi_tanah", rs.getString("lokasi_tanah")== null?"":rs.getString("lokasi_tanah"));
	    			h.put("keadaan_rupabumi", rs.getString("keadaan_rupabumi")== null?"":rs.getString("keadaan_rupabumi"));
	    			h.put("kemudahan_awam", rs.getString("kemudahan_awam")== null?"":rs.getString("kemudahan_awam"));
	    			h.put("tarikh_mula_chart", rs.getDate("tarikh_mula_chart")==null?"":Format.format(rs.getDate("tarikh_mula_chart")));
	    			h.put("tarikh_akhir_chart", rs.getDate("tarikh_akhir_chart")==null?"":Format.format(rs.getDate("tarikh_akhir_chart")));
	    			h.put("tarikh_lawatan", rs.getDate("tarikh_lawatan")==null?"":Format.format(rs.getDate("tarikh_lawatan")));	    			
	    			h.put("perihal_bangunan", rs.getString("perihal_bangunan")== null?"":rs.getString("perihal_bangunan"));
	    			h.put("ulasan_syor", rs.getString("ulasan_syor")== null?"":rs.getString("ulasan_syor"));
	    			h.put("perihal_tm_sendiri", rs.getString("perihal_tm_sendiri")== null?"":rs.getString("perihal_tm_sendiri"));
	    			h.put("perihal_tmtr_sekutuan", rs.getString("perihal_tmtr_sekutuan")== null?"":rs.getString("perihal_tmtr_sekutuan"));
	    			h.put("perihal_tr_negeri", rs.getString("perihal_tr_negeri")== null?"":rs.getString("perihal_tr_negeri"));
	    			h.put("perihal_kawasan_melayu", rs.getString("perihal_kawasan_simpan")== null?"":rs.getString("perihal_kawasan_simpan"));	    			
	    			h.put("luas_terlibat", rs.getString("luas_terlibat")== null?"":rs.getString("luas_terlibat"));   			
	    			h.put("perihal_tnh_kerajaan", rs.getString("perihal_tnh_kerajaan")== null?"":rs.getString("perihal_tnh_kerajaan"));
	    			h.put("bil_hakmilik", rs.getString("bil_hakmilik")== null?"":rs.getString("bil_hakmilik"));
	    			h.put("id_unitluas", rs.getString("id_unitluas")== null?"":rs.getString("id_unitluas"));
	    			h.put("harga_anggar_bangunan", rs.getString("harga_anggar_bangunan")== null?"":rs.getString("harga_anggar_bangunan"));	
	    			h.put("harga_anggar", rs.getString("harga_anggar")== null?"":rs.getString("harga_anggar"));
	    			
	    			//String
	    			h.put("str_harga_anggar_bangunan", rs.getString("harga_anggar_bangunan")== null?"":rs.getString("harga_anggar_bangunan"));	
	    			h.put("str_harga_anggar", rs.getString("harga_anggar")== null?"":rs.getString("harga_anggar"));
	    			h.put("tarikh_lapor", rs.getDate("tarikh_lapor")==null?"":Format.format(rs.getDate("tarikh_lapor")));
	    			
	    			dataLaporanTanah.addElement(h);
	    		
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setDataLaporanTanah
	
	@SuppressWarnings("unchecked")
	public static void updateLaporan(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");	    	
	    		String id_tanahumum = (String)data.get("id_tanahumum");
	    		
	    		String id_unitluas = (String)data.get("sorDropdownUnitAsal");
	    		String txdTarikhMula = (String)data.get("txdTarikhMula");
	    		String txdTarikhTamat = (String)data.get("txdTarikhTamat");
	    		String txdTarikhLawat = (String)data.get("txdTarikhLawat");
	    		String txtBilKeseluruhan = (String)data.get("txtBilKeseluruhan");
	    		String txtLuasKeseluruhan = (String)data.get("txtLuasKeseluruhan");
	    		//String unitLuas = (String)data.get("unitLuas");
	    		String txtKosTanah = (String)data.get("txtKosTanah");
	    		String txtKosBangunan = (String)data.get("txtKosBangunan");
	    		String txtPerihal = (String)data.get("txtPerihal");	    	
	    		String txtTanahSendiri = (String)data.get("txtTanahSendiri");
	    		String txtTanahNegeri = (String)data.get("txtTanahNegeri");
	    		String txtTanahPersekutuan = (String)data.get("txtTanahPersekutuan");
	    		String txtKeadaanTanah = (String)data.get("txtKeadaanTanah");  
	    		String txtKemudahanAwam = (String)data.get("txtKemudahanAwam");  
	    		String txtLokasi = (String)data.get("txtLokasi");
	    		String txtTanahMelayu = (String)data.get("txtTanahMelayu");
	    		String txtTanahKerajaan = (String)data.get("txtTanahKerajaan");
	    		String txtSyor = (String)data.get("txtSyor");
	    		String txtNoPeta = (String)data.get("txtNoPeta");
	    		String txtPendahuluan = (String)data.get("txtPendahuluan");
				String txtNilai = (String)data.get("txtNilai");
	    		String txtKosProjek = (String)data.get("txtKosProjek");
	    		
	    		String TM = "to_date('" + txdTarikhMula + "','dd/MM/yyyy')";
	    		String TT = "to_date('" + txdTarikhTamat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + txdTarikhLawat + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_tanahumum", id_tanahumum);
	    		r.add("bil_hakmilik",txtBilKeseluruhan);    
	    		r.add("harga_anggar_projek", txtKosProjek);
	    		r.add("luas_terlibat",txtLuasKeseluruhan);
	    		r.add("id_unitluas",id_unitluas);
	    		r.add("no_peta_kadaster",txtNoPeta);
	    		r.add("harga_anggar",txtKosTanah);
	    		r.add("harga_anggar_bangunan",txtKosBangunan);
	    		r.add("perihal_bangunan",txtPerihal);
	    		r.add("pendahuluan",txtPendahuluan);	
				r.add("nilai_tanah",txtNilai);				
	    		r.add("perihal_tm_sendiri",txtTanahSendiri);
	    		r.add("perihal_tr_negeri",txtTanahNegeri);
	    		r.add("perihal_tmtr_sekutuan",txtTanahPersekutuan);
	    		r.add("keadaan_rupabumi",txtKeadaanTanah);
	    		r.add("kemudahan_awam",txtKemudahanAwam);
	    		r.add("lokasi_tanah",txtLokasi);	    		
	    		r.add("perihal_kawasan_simpan",txtTanahMelayu);
	    		r.add("perihal_tnh_kerajaan",txtTanahKerajaan);
	    		r.add("ulasan_syor",txtSyor);	    		
	    		r.add("tarikh_mula_chart",r.unquote(TM));
	    		r.add("tarikh_akhir_chart",r.unquote(TT));
	    		r.add("tarikh_lawatan",r.unquote(TL));
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);    		
	    		sql = r.getSQLUpdate("tblppttanahumum");
	    		stmt.executeUpdate(sql);
    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	}//close updateLaporan
	
	
	@SuppressWarnings("unchecked")
	public static void setTotalLuasAmbil(String idpermohonan,String id_pegawai)throws Exception {
		
		totalLuasAmbil = new Vector();
		
	    Db db = null;
	    totalLuasAmbil.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  "SELECT SUM(CASE ";
	    		sql += " WHEN ID_UNITLUASAMBIL_CONVERT IS NOT NULL AND ID_UNITLUASAMBIL_CONVERT = '1' AND LUAS_AMBIL IS NOT NULL THEN LUAS_AMBIL ";
	    		sql += " WHEN ID_UNITLUASAMBIL_CONVERT IS NOT NULL AND ID_UNITLUASAMBIL_CONVERT = '2' AND LUAS_AMBIL IS NOT NULL THEN ROUND(LUAS_AMBIL / 10000,'4') ";
	    		sql += " ELSE 0 ";
	    		sql += " END) AS TOTALLUAS ";
	    		sql += " FROM TBLPPTHAKMILIK H ";
	    		
	    		if (!id_pegawai.equals("") && id_pegawai != null) 
				{				
				sql +=	" ,TBLPPTAGIHANHM AGHM, Users u ";
				}
	    		
	    		
	    		sql += " WHERE NVL(flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(flag_penarikan_keseluruhan,0) <> 'Y' ";
	    		sql += " AND ID_PERMOHONAN = '"+idpermohonan+"'";
	    		
	    		
	    		
	    		if (!id_pegawai.equals("") && id_pegawai != null) 
				{
				sql += " AND AGHM.ID_HAKMILIK = H.ID_HAKMILIK  "; 
						sql += " AND AGHM.USER_ID = u.USER_ID  "; 
								sql += " AND AGHM.BARIS = '2'  "; 
				}
				if (!id_pegawai.equals("") && id_pegawai != null) {
					sql += " AND u.USER_ID  = '"+id_pegawai+"'";
				}
	    		
	    		/*
	    		if(idpegawai!=""){
	    			sql += " AND ID_PEGAWAI = '"+idpegawai+"'";
	    		}
	    		*/
	    		myLogger.info("---- get total & luas lot :"+sql);
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	    		
	    		
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();

	    			if(rs.getString("TOTALLUAS") != null){
						
						double totalLuas = rs.getDouble("TOTALLUAS");
						String TL = Utils.formatLuasHM(totalLuas);
						
						String TL_non_format = totalLuas+"";
						
						myLogger.info("::::::::::::::::::::: TL :"+TL);
						
						
						h.put("totalLuasAmbil",TL);
						
						h.put("totalLuasAmbil_nonformat",TL_non_format);
						
								
					}else{
						h.put("totalLuasAmbil","0");
					}

	    			
	    			totalLuasAmbil.addElement(h);
	    		
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setTotalLuasAmbil
	
	
	
	@SuppressWarnings("unchecked")
	public static void setTotalLot(String idpermohonan,String idpegawai)throws Exception {
		
		totalLot = new Vector();
		
	    Db db = null;
	    totalLot.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql =  "SELECT COUNT(*) AS TOTAL_LOT FROM TBLPPTHAKMILIK WHERE ID_PERMOHONAN = '"+idpermohonan+"' AND NVL(flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(flag_penarikan_keseluruhan,0) <> 'Y' ";
	    		
	    		
	    		myLogger.info("---- get total & luas lot :"+sql);
	    		
	    		ResultSet rs = stmt.executeQuery(sql);
	    		
	    		
	      
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();

	    			

	    			h.put("totaLLot",rs.getString("TOTAL_LOT"));
	    			
	    			totalLot.addElement(h);
	    		
	      }//close while
	      
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	      if (db != null) db.close();
	    }
	    
	  }//close setTotalLuasAmbil
	
}//close class
