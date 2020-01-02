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

import ekptg.helpers.DB;
import ekptg.model.entities.Tblintrujkategoritanah;
import ekptg.model.entities.Tblrujjenisbangunan;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8InfoTanahTerperinciBangunanData {
	static Logger myLogger = Logger.getLogger(FrmUPTSek8InfoTanahTerperinciBangunanData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmUPTSek8InfoTanahTerperinciBangunanData.class);
	
	private static Vector listcarian = null;
	private static Vector listBangunan = null;
	private static Vector dataBangunan = null;
	private static Vector listMaklumatPB = null;
	private static Vector dataBangunanPB = null;
	private static Vector listMaklumatPBPemilik = null;
	
	public static Vector getListCarian(){
		return listcarian;
	}
	public static  Vector getListBangunan(){
		return listBangunan;
	}
	public static  Vector getDataBangunan(){
		return dataBangunan;
	}
	public static  Vector getListMaklumatPB(){
		return listMaklumatPB;
	}
	public static  Vector getListMaklumatPBPemilik(){
		return listMaklumatPBPemilik;
	}
	public static  Vector getDataBangunanPB(){
		return dataBangunanPB;
	}
	
	//penambahan yati
		public Vector senaraiTanah() throws Exception {
		Vector senarai_tanah = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT ID_KATEGORITANAH, KETERANGAN FROM TBLINTRUJKATEGORITANAH ";
			
			
			//System.out.println(" SQL OB LIST FROM MODEL :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Tblintrujkategoritanah h;
			while (rs.next()) {
				h = new Tblintrujkategoritanah();
				h.setIdKategoriTanah(rs.getString("ID_KATEGORITANAH") == null ? 0L: rs.getLong("ID_KATEGORITANAH"));
				h.setKeterangan(rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));				
				senarai_tanah.addElement(h);	
				
			}
			//System.out.println(" SENARAI TANAH FROM MODEL :"+senarai_tanah);
			return senarai_tanah;
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}
		
		//penambahan yati
		public Vector senaraiBangunan() throws Exception {
		Vector senarai_bangunan = new Vector();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT ID_JENIS_BANGUNAN, NAMA_BANGUNAN, KETERANGAN FROM TBLRUJJENISBANGUNAN ORDER BY ID_JENIS_BANGUNAN ASC";
			
			
			//System.out.println(" SQL OB LIST FROM MODEL :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Tblrujjenisbangunan h;
			while (rs.next()) {
				h = new Tblrujjenisbangunan();
				h.setIdJenisBangunan(rs.getString("ID_JENIS_BANGUNAN") == null ? 0L: rs.getLong("ID_JENIS_BANGUNAN"));
				h.setNamaBangunan(rs.getString("NAMA_BANGUNAN") == null ? "" : rs.getString("NAMA_BANGUNAN"));
				h.setKeteranganBangunan(rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				senarai_bangunan.addElement(h);	
				
			}
			//System.out.println(" SENARAI TANAH FROM MODEL :"+senarai_bangunan);
			return senarai_bangunan;
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
		public static String sqlListPermohonan(String userIdNegeri)
		{

			String sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, ";
    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt ";
    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k,Tblppttandakawasan hx ";
    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
    		sql +="AND p.id_status = s.id_status and hx.id_permohonan(+) = p.id_permohonan ";
    		sql +="AND f.id_suburusan = '52' ";
    		sql +="AND (p.id_permohonan in ";
    		//sql += " (" +
    		/*		" select distinct px.id_permohonan from Tblpptpermohonan px ";
    		sql += " where px.id_permohonan = p.id_permohonan ";
    		sql += " AND (px.id_permohonan in (select distinct hx.id_permohonan from Tblppttandakawasan hx "; 
    		sql += " where hx.id_permohonan = p.id_permohonan) ";
    		sql += " OR " +
    				"px.id_permohonan in " +*/
    				sql += " (SELECT DISTINCT HM.ID_PERMOHONAN FROM TBLPPTTANAH TN, TBLPPTHAKMILIK HM " +
    				" WHERE TN.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_PERMOHONAN = p.id_permohonan) OR hx.id_permohonan IS NOT NULL) ";
    				//")) ";
    		//sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
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
		    		sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
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
		    		myLogger.info("Laporan Tanah : "+sql);
		    		
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
	    		sql +="AND p.id_status IN (22,26,31,43,132,133,134,147) ";
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
	public static void simpanInfoTanah(Hashtable data,String idstatus,String idpermohonan) throws Exception{
		System.out.println("DATA SIMPAN TANAH :"+data);
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		
	    		String sorSaiz = (String)data.get("sorSaiz");
	    		String txtNoBangunan = (String)data.get("txtNoBangunan");
	    		String txtLainBangunan = (String)data.get("txtLainBangunan");
	    		String socBangunan = (String)data.get("socBangunan");
	    		String txtSaizBangunan = (String)data.get("txtSaizBangunan");
	    		String txtSaizBangunanDua = (String)data.get("txtSaizBangunanDua");
	    		String txtNilai = (String)data.get("txtNilai");
	    		String txtPerkara = (String)data.get("txtPerkara");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		String txtUlasan = (String)data.get("txtUlasan");
	    		String socNegeri = (String)data.get("socNegeri");
	    		String socBandar = (String)data.get("socBandar");
	    		String socTanah = (String)data.get("socTanah"); //penambahan yati
	    		
//	    		if(idstatus.equals("43")){
//	    			SQLRenderer rp = new SQLRenderer();
//		    		rp.update("id_permohonan", idpermohonan);
//		    		rp.add("id_status", "46");
//		    		rp.add("tarikh_kemaskini",rp.unquote("sysdate"));
//		    		rp.add("id_kemaskini",id_user);	    		
//		    		sql = rp.getSQLUpdate("Tblpptpermohonan");
//		    		stmt.executeUpdate(sql);
//	    		}
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_hakmilik", id_hakmilik);
	    		r.add("no_bangunan", txtNoBangunan);
	    		r.add("jenis_bangunan", socBangunan);
	    		r.add("lain_jenis_bangunan", txtLainBangunan);
	    		r.add("saiz_bangunan", txtSaizBangunan);
	    	
	    		if(txtSaizBangunanDua!=null)
	    		{
	    		r.add("saiz_bangunan_dua", txtSaizBangunanDua);
	    		}
	    		r.add("nilai_bangunan", txtNilai);
	    		r.add("lain_perkara", txtPerkara);
	    		r.add("alamat1", txtAlamat1);
	    		r.add("alamat2", txtAlamat2);
	    		r.add("alamat3", txtAlamat3);
	    		r.add("poskod", txtPoskod);
	    		r.add("ulasan", txtUlasan);
	    		r.add("unit_saiz", sorSaiz);
	    		r.add("id_negeri", socNegeri);
	    		r.add("id_bandar", socBandar);
	    		r.add("id_kategori_tanah", socTanah);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	    		
	    		sql = r.getSQLInsert("Tblpptbangunan");
	    		myLogger.info("simpanInfoTanah :"+sql);
	    		stmt.executeUpdate(sql);
	    	
	    	} catch (Exception re) {
	    		log.error("Error: ", re);
	    		throw re;
	    		}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	  }//close simpanInfoTanah
	
	@SuppressWarnings("unchecked")
	public static void updateInfoTanah(Hashtable data) throws Exception{
		System.out.println("DATA--***"+data);
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_bangunan = (String)data.get("id_bangunan");
	    		
	    		String sorSaiz = (String)data.get("sorSaiz");
	    		String txtNoBangunan = (String)data.get("txtNoBangunan");
	    		String txtLainBangunan = (String)data.get("txtLainBangunan");
	    		String socBangunan = (String)data.get("socBangunan");
	    		String txtSaizBangunan = (String)data.get("txtSaizBangunan");
	    		String txtSaizBangunanDua = (String)data.get("txtSaizBangunanDua");
	    		String txtNilai = (String)data.get("txtNilai");
	    		String txtPerkara = (String)data.get("txtPerkara");
	    		String txtAlamat1 = (String)data.get("txtAlamat1");
	    		String txtAlamat2 = (String)data.get("txtAlamat2");
	    		String txtAlamat3 = (String)data.get("txtAlamat3");
	    		String txtPoskod = (String)data.get("txtPoskod");
	    		String txtUlasan = (String)data.get("txtUlasan");
	    		String socNegeri = (String)data.get("socNegeri");
	    		String socBandar = (String)data.get("socBandar");
	    		String socTanah = (String)data.get("socTanah");
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_bangunan", id_bangunan);
	    		r.add("no_bangunan", txtNoBangunan);
	    		r.add("jenis_bangunan", socBangunan);
	    		r.add("lain_jenis_bangunan", txtLainBangunan);
	    		r.add("unit_saiz", sorSaiz);
	    		r.add("saiz_bangunan", txtSaizBangunan);
	    		r.add("saiz_bangunan_dua", txtSaizBangunanDua);
	    		r.add("nilai_bangunan", txtNilai);
	    		r.add("lain_perkara", txtPerkara);
	    		r.add("alamat1", txtAlamat1);
	    		r.add("alamat2", txtAlamat2);
	    		r.add("alamat3", txtAlamat3);
	    		r.add("poskod", txtPoskod);
	    		r.add("ulasan", txtUlasan);
	    		r.add("id_negeri", socNegeri);
	    		r.add("id_bandar", socBandar);
	    		r.add("id_kategori_tanah", socTanah);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user);	    		
	    		sql = r.getSQLUpdate("Tblpptbangunan");
	    		
	    		myLogger.info("SQL INSERT : "+sql);
	    		stmt.executeUpdate(sql);
	    		
	    		
	    	
	    		
	    	} catch (Exception re) {
	    		log.error("Error: ", re);
	    		throw re;
	    		}//close try 
	    	finally {
	    		if (db != null) db.close();
	    	}//close finally
	   
	  }//close updateInfoTanah
	
	
	 @SuppressWarnings("unchecked")
	 public static void setListBangunan(String id_hakmilik)throws Exception {
			
		 listBangunan = new Vector();
			
		 Db db = null;
		 listBangunan.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT TRIM(NVL(TO_CHAR(a.nilaian_jpph,'999999990.99'),'')) AS nilaian_jpph," +
		    			" a.id_bangunan,a.id_hakmilik,a.no_bangunan,a.jenis_bangunan,a.saiz_bangunan, a.lain_jenis_bangunan,";
		    	sql += " a.nilai_bangunan,a.alamat1,a.alamat2,a.alamat3,a.poskod,a.id_negeri,a.id_bandar, a.id_kategori_tanah,";
		    	sql += " a.ulasan,a.lain_perkara, b.nama_negeri, c.keterangan as nama_bandar, ";
		    	sql += " d.keterangan, ";
		    	sql += " e.nama_bangunan, e.keterangan as keterangan_bangunan ";
		    	sql += " FROM TBLPPTBANGUNAN a, TBLRUJNEGERI b, TBLRUJBANDAR c , TBLINTRUJKATEGORITANAH d, TBLRUJJENISBANGUNAN e";
		    	sql += " WHERE a.id_negeri = b.id_negeri(+)";
		    	sql += " AND a.id_bandar = c.id_bandar(+)";
		    	sql += " AND a.jenis_bangunan = e.id_jenis_bangunan(+)";
		    	sql += " AND a.id_kategori_tanah = d.id_kategoritanah(+)";
		    	sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
			
		    	myLogger.info(" setListBangunan:"+sql);	
				
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;
		    	int bil = 1;

		    	while (rs.next()) {
		    		h = new Hashtable();
		    		h.put("bil", bil);		    			
		    		h.put("nama_negeri", rs.getString("nama_negeri")== null?"":rs.getString("nama_negeri"));
		    		h.put("nama_bandar", rs.getString("nama_bandar")== null?"":rs.getString("nama_bandar"));
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("no_bangunan", rs.getString("no_bangunan")== null?"":rs.getString("no_bangunan"));
		    		//h.put("jenis_bangunan", rs.getString("jenis_bangunan")== null?"":rs.getString("jenis_bangunan"));
		    		h.put("nama_bangunan", rs.getString("nama_bangunan")== null?"":rs.getString("nama_bangunan"));
		    		h.put("keterangan_bangunan", rs.getString("keterangan_bangunan")== null?"":rs.getString("keterangan_bangunan"));
		    		h.put("lain_jenis_bangunan", rs.getString("lain_jenis_bangunan")== null?"":rs.getString("lain_jenis_bangunan"));
		    		h.put("saiz_bangunan", rs.getString("saiz_bangunan")== null?"":rs.getString("saiz_bangunan"));
		    		h.put("nilai_bangunan", rs.getString("nilai_bangunan")== null?"":rs.getString("nilai_bangunan"));
		    		h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    		h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    		h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    		h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    		h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    		h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
		    		h.put("id_kategori_tanah", rs.getString("id_kategori_tanah")== null?"":rs.getString("id_kategori_tanah"));
		    		h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    		h.put("lain_perkara", rs.getString("lain_perkara")== null?"":rs.getString("lain_perkara"));
		    		h.put("nilaian_jpph", rs.getString("nilaian_jpph")== null?"":rs.getDouble("nilaian_jpph"));
		    		listBangunan.addElement(h);
		    		bil++;
		    			
		    	}//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setListBangunan
	 
	 @SuppressWarnings("unchecked")
	 public int setListBangunan_count(String id_hakmilik)throws Exception {
		
			
		 Db db = null;
		
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT count(*) as total FROM TBLPPTBANGUNAN a, TBLRUJNEGERI b, TBLRUJBANDAR c, TBLINTRUJKATEGORITANAH d , TBLRUJJENISBANGUNAN e";
		    	sql += " WHERE a.id_negeri = b.id_negeri(+)";
		    	sql += " AND a.id_bandar = c.id_bandar(+)";
		    	sql += " AND a.id_kategori_tanah = d.id_kategoritanah(+)";
		    	sql += " AND a.jenis_bangunan = e.id_jenis_bangunan(+)";
		    	sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
				//myLogger.info(" setListBangunan:"+sql);	
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;
		    	int total = 0;

		    	while (rs.next()) {
		    		
		    		total = rs.getInt("total");
		    		
		    	
		    			
		    	}//close while
		      return total;
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setListBangunan
	
	 @SuppressWarnings("unchecked")
	 public static void setDataBangunan(String id_bangunan)throws Exception {
			
		 dataBangunan = new Vector();
			
		 Db db = null;
		 dataBangunan.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT a.saiz_bangunan_dua, a.id_bangunan,a.id_hakmilik,a.no_bangunan,a.jenis_bangunan,a.saiz_bangunan, ";
		    	sql += " a.nilai_bangunan,a.alamat1,a.alamat2,a.alamat3,a.poskod,a.id_negeri,a.id_bandar, a.id_kategori_tanah, a.lain_jenis_bangunan,";
		    	sql += " a.ulasan,a.lain_perkara, b.nama_negeri, c.keterangan as nama_bandar, a.unit_saiz, ";
		    	sql += " d.keterangan, ";
		    	sql += " e.nama_bangunan, e.keterangan as keterangan_bangunan ";
		    	sql += " FROM TBLPPTBANGUNAN a, TBLRUJNEGERI b, TBLRUJBANDAR c , TBLINTRUJKATEGORITANAH d, TBLRUJJENISBANGUNAN e";
		    	sql += " WHERE a.id_negeri = b.id_negeri(+)";
		    	sql += " AND a.id_kategori_tanah = d.id_kategoritanah(+)";
		    	sql += " AND a.jenis_bangunan = e.id_jenis_bangunan(+)";
		    	sql += " AND a.id_bandar = c.id_bandar(+)";
		    	sql += " AND id_bangunan = '"+id_bangunan+"'";
		    	
		    	myLogger.info(" CHECK ERROR : "+sql);
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;

		    	while (rs.next()) {
		    		h = new Hashtable();	  
		    		h.put("saiz_bangunan_dua", rs.getString("saiz_bangunan_dua")== null?"":rs.getString("saiz_bangunan_dua"));
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("no_bangunan", rs.getString("no_bangunan")== null?"":rs.getString("no_bangunan"));
		    		h.put("jenis_bangunan", rs.getString("jenis_bangunan")== null?"":rs.getString("jenis_bangunan"));
		    		h.put("nama_bangunan", rs.getString("nama_bangunan")== null?"":rs.getString("nama_bangunan"));
		    		h.put("keterangan_bangunan", rs.getString("keterangan_bangunan")== null?"":rs.getString("keterangan_bangunan"));
		    		h.put("lain_jenis_bangunan", rs.getString("lain_jenis_bangunan")== null?"":rs.getString("lain_jenis_bangunan"));
		    		h.put("unit_saiz", rs.getString("unit_saiz")== null?"":rs.getString("unit_saiz"));
		    		h.put("saiz_bangunan", rs.getString("saiz_bangunan")== null?"":rs.getString("saiz_bangunan"));
		    		//h.put("nilai_bangunan", rs.getString("nilai_bangunan")== null?"":rs.getString("nilai_bangunan"));
		    		h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    		h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    		h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    		h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    		h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    		h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
		    		h.put("id_kategori_tanah", rs.getString("id_kategori_tanah")== null?"":rs.getString("id_kategori_tanah"));
		    		h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
		    		h.put("lain_perkara", rs.getString("lain_perkara")== null?"":rs.getString("lain_perkara"));
		    		h.put("nama_negeri", rs.getString("nama_negeri")== null?"":rs.getString("nama_negeri"));
		    		h.put("nama_bandar", rs.getString("nama_bandar")== null?"":rs.getString("nama_bandar"));
		    		if(rs.getString("nilai_bangunan") != null){
		    			h.put("nilai_bangunan",rs.getDouble("nilai_bangunan"));
	    			}else{
	    				h.put("nilai_bangunan","");
	    			}
		    		dataBangunan.addElement(h);
		    		//myLogger.info("DATA BANGUNAN"+dataBangunan);
		    			
		    	}//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
		  }//close setDataBangunan
	 
	 public static void hapusBangunan(String id_bangunan) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptbangunan where id_bangunan = '"+id_bangunan+"'";
	    		stmt.executeUpdate(sql);
  	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusBangunan
	 
	  @SuppressWarnings("unchecked")
	  public static void simpanBangunanPB(Hashtable data,String id_hakmilikpb) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		long id_bangunanpb = DB.getNextID("TBLPPTBANGUNANPB_SEQ");
		    		
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_bangunan = (String)data.get("id_bangunan");
		    		String txtNoUnit = (String)data.get("txtNoUnit");
		    		String txtKadarSewa = (String)data.get("txtKadarSewa");
		    		String txtSaizBangunan = (String)data.get("txtSaizBangunan");
		    		String txtSaizBangunanDua = (String)data.get("txtSaizBangunanDua");
		    		String txtKegunaan = (String)data.get("txtKegunaan");
		    		String flag_pb = (String)data.get("flag_pb");
		    		String sorSaiz = (String)data.get("sorSaiz");
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_bangunanpb",id_bangunanpb);
		    		r.add("id_hakmilikpb",id_hakmilikpb);	
		    		r.add("id_bangunan",id_bangunan);
		    		r.add("no_unit_bangunan",txtNoUnit);
		    		r.add("kadar_sewa",txtKadarSewa);
		    		r.add("saiz_bangunan",txtSaizBangunan);
		    		r.add("saiz_bangunan_dua",txtSaizBangunanDua);
		    		r.add("kegunaan_bangunan",txtKegunaan);
		    		r.add("flag_pb",flag_pb);
		    		r.add("unit_saiz",sorSaiz);
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("Tblpptbangunanpb");
		    		myLogger.info(" SIMPAN PENDUDUK :"+sql);
		    		stmt.executeUpdate(sql);
	    	
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close simpanBangunanPB
	 
	 @SuppressWarnings("unchecked")
	 public static void setListMaklumatPB(String idBangunan,String idHakmilik)throws Exception {
			
		 listMaklumatPB = new Vector();
			
		 Db db = null;
		 listMaklumatPB.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT a.id_bangunanpb,a.id_bangunan,e.id_hakmilik, ";
		    	sql += " a.no_unit_bangunan,a.kadar_sewa,a.kegunaan_bangunan,a.saiz_bangunan, ";
		    	sql += " b.nama_pb, f.keterangan as jenis_pb ";
		    	sql += " FROM TBLPPTBANGUNANPB a, TBLPPTPIHAKBERKEPENTINGAN b, TBLPPTBANGUNAN c, TBLPPTHAKMILIKPB e, ";
		    	sql += " TBLRUJJENISPB f";
		    	sql += " WHERE a.id_hakmilikpb = e.id_hakmilikpb ";
				sql += " AND a.id_bangunan = c.id_bangunan(+) ";
				sql += " AND e.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
				sql += " AND e.id_jenispb = f.id_jenispb(+)";
				sql += " AND a.id_bangunan = '"+idBangunan+"'";
				sql += " AND e.id_hakmilik = '"+idHakmilik+"'";
					
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;
		    	int bil = 1;

		    	while (rs.next()) {
		    		h = new Hashtable();
		    		h.put("bil", bil);		    	
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("id_bangunanpb", rs.getString("id_bangunanpb")== null?"":rs.getString("id_bangunanpb"));
		    		h.put("no_unit_bangunan", rs.getString("no_unit_bangunan")== null?"":rs.getString("no_unit_bangunan"));
		    		h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    		h.put("jenis_pb", rs.getString("jenis_pb")== null?"":rs.getString("jenis_pb"));
		    		listMaklumatPB.addElement(h);
		    		bil++;
		    			
		    	}//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListMaklumatPB
	 
	 
	 public int countPenduduk(String idBangunan,String idHakmilik)throws Exception {
			
		
			
		 Db db = null;
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT count(*) as total_penduduk ";
		    	sql += " FROM TBLPPTBANGUNANPB a, TBLPPTPIHAKBERKEPENTINGAN b, TBLPPTBANGUNAN c, TBLPPTHAKMILIKPB e, ";
		    	sql += " TBLRUJJENISPB f";
		    	sql += " WHERE a.id_hakmilikpb = e.id_hakmilikpb ";
				sql += " AND a.id_bangunan = c.id_bangunan(+) ";
				sql += " AND e.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
				sql += " AND e.id_jenispb = f.id_jenispb(+)";
				sql += " AND a.id_bangunan = '"+idBangunan+"'";
				sql += " AND e.id_hakmilik = '"+idHakmilik+"'";
					
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;
		    	int bil = 1;
		    	int total_penduduk = 0;
		    	while (rs.next()) {
		    				    	
		    		total_penduduk = (rs.getString("total_penduduk")== null?0:rs.getInt("total_penduduk"));
		    			
		    	}//close while
		      return total_penduduk;
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListMaklumatPB
	 
	 @SuppressWarnings("unchecked")
	 public static void setListMaklumatPBPemilik(String idBangunan,String idHakmilik)throws Exception {
			
		 listMaklumatPBPemilik = new Vector();
			
		 Db db = null;
		 listMaklumatPBPemilik.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = "SELECT DISTINCT a.id_bangunanpb,a.id_bangunan,e.id_hakmilik, ";
		    	sql += " a.no_unit_bangunan,a.kadar_sewa,a.kegunaan_bangunan,a.saiz_bangunan, ";
		    	sql += " b.nama_pb, f.id_jenispb ";
		    	sql += " FROM TBLPPTBANGUNANPB a, TBLPPTPIHAKBERKEPENTINGAN b, TBLPPTBANGUNAN c, TBLPPTHAKMILIKPB e, ";
		    	sql += " TBLRUJJENISPB f";
		    	sql += " WHERE a.id_hakmilikpb = e.id_hakmilikpb ";
				sql += " AND a.id_bangunan = c.id_bangunan ";
				sql += " AND e.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
				sql += " AND e.id_jenispb = f.id_jenispb(+)";
				sql += " AND f.id_jenispb IN (1,30) ";
				sql += " AND a.id_bangunan = '"+idBangunan+"'";
				sql += " AND e.id_hakmilik = '"+idHakmilik+"'";
					
		    	ResultSet rs = stmt.executeQuery(sql);
		    	
		      
		    	Hashtable h;
		    	int bil = 1;

		    	while (rs.next()) {
		    		h = new Hashtable();
		    		h.put("bil", bil);		    	
		    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
		    		h.put("id_bangunanpb", rs.getString("id_bangunanpb")== null?"":rs.getString("id_bangunanpb"));
		    		h.put("no_unit_bangunan", rs.getString("no_unit_bangunan")== null?"":rs.getString("no_unit_bangunan"));
		    		h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    		h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    		listMaklumatPBPemilik.addElement(h);
		    		bil++;
		    			
		    	}//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setListMaklumatPBPemilik 
	 
	 @SuppressWarnings("unchecked")
	 public static void setDataBangunanPB(String id_bangunanpb,String id_hakmilik)throws Exception {
			
		 dataBangunanPB = new Vector();
			
		 Db db = null;
		 dataBangunanPB.clear();
		 String sql = "";
		    
		 try {
		    	
		    	db = new Db();
		    	Statement stmt = db.getStatement();
		      
		    	sql = " SELECT DISTINCT a.saiz_bangunan_dua,a.id_bangunanpb,a.id_bangunan,b.id_pihakberkepentingan,e.id_hakmilik, ";
		    	sql += " a.no_unit_bangunan,a.kadar_sewa,a.kegunaan_bangunan,a.saiz_bangunan,a.flag_pb, ";
		    	sql += " b.nama_pb,b.id_jenisnopb,b.no_pb,e.id_jenispb,e.jenis_lain2_pb,b.id_bangsa,b.id_warganegara, ";
		    	sql += " e.alamat1,e.alamat2,e.alamat3,e.poskod,e.id_negeri,e.id_bandar,e.no_tel_rumah,e.no_handphone,e.no_fax, a.unit_saiz ";
		    	sql += " FROM TBLPPTBANGUNANPB a, TBLPPTPIHAKBERKEPENTINGAN b, TBLPPTBANGUNAN c, TBLRUJJENISPB d, TBLPPTHAKMILIKPB e ";
				sql += " WHERE a.id_hakmilikpb = e.id_hakmilikpb ";
				sql += " AND a.id_bangunan = c.id_bangunan(+) ";
				sql += " AND e.id_pihakberkepentingan = b.id_pihakberkepentingan(+) "; 
				sql += " AND a.id_bangunanpb = '"+id_bangunanpb+"'";
				sql += " AND e.id_hakmilik = '"+id_hakmilik+"'";
			
		    	ResultSet rs = stmt.executeQuery(sql);
		      
		    	Hashtable h;

		    	while (rs.next()) {
		    		h = new Hashtable();	    			
		    		h.put("saiz_bangunan_dua", rs.getString("saiz_bangunan_dua")== null?"":rs.getString("saiz_bangunan_dua"));
		    		h.put("unit_saiz", rs.getString("unit_saiz")== null?"":rs.getString("unit_saiz"));
		    		h.put("id_bangunanpb", rs.getString("id_bangunanpb")== null?"":rs.getString("id_bangunanpb"));
		    		h.put("id_pihakberkepentingan", rs.getString("id_pihakberkepentingan")== null?"":rs.getString("id_pihakberkepentingan"));
		    		h.put("no_unit_bangunan", rs.getString("no_unit_bangunan")== null?"":rs.getString("no_unit_bangunan"));
		    		h.put("kegunaan_bangunan", rs.getString("kegunaan_bangunan")== null?"":rs.getString("kegunaan_bangunan"));
		    		h.put("saiz_bangunan", rs.getString("saiz_bangunan")== null?"":rs.getString("saiz_bangunan"));
		    		h.put("flag_pb", rs.getString("flag_pb")== null?"":rs.getString("flag_pb"));
		    		h.put("nama_pb", rs.getString("nama_pb")== null?"":rs.getString("nama_pb"));
		    		h.put("id_jenisnopb", rs.getString("id_jenisnopb")== null?"":rs.getString("id_jenisnopb"));
		    		h.put("no_pb", rs.getString("no_pb")== null?"":rs.getString("no_pb"));
		    		h.put("id_jenispb", rs.getString("id_jenispb")== null?"":rs.getString("id_jenispb"));
		    		h.put("jenis_lain2_pb", rs.getString("jenis_lain2_pb")== null?"":rs.getString("jenis_lain2_pb"));
		    		h.put("id_bangsa", rs.getString("id_bangsa")== null?"":rs.getString("id_bangsa"));
		    		h.put("id_warganegara", rs.getString("id_warganegara")== null?"":rs.getString("id_warganegara"));
		    		h.put("no_tel_rumah", rs.getString("no_tel_rumah")== null?"":rs.getString("no_tel_rumah"));
		    		h.put("no_handphone", rs.getString("no_handphone")== null?"":rs.getString("no_handphone"));
		    		h.put("no_fax", rs.getString("no_fax")== null?"":rs.getString("no_fax"));		    		
		    		h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
		    		h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
		    		h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
		    		h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
		    		h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
		    		h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));		    		
		    		if(rs.getString("kadar_sewa") != null && rs.getString("kadar_sewa") != ""){
		    			h.put("kadar_sewa",rs.getDouble("kadar_sewa"));
	    			}else{
	    				h.put("kadar_sewa","");
	    			}
		    		dataBangunanPB.addElement(h);
		    			
		    	}//close while
		      
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }
		    
	}//close setDataBangunanPB 
	 
	 
	 public static void hapusBangunanPB(String id_bangunanpb) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();	  
	    		sql = "DELETE FROM tblpptbangunanpb where id_bangunanpb = '"+id_bangunanpb+"'";
	    		stmt.executeUpdate(sql);
 	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusBangunanPB
	 
	 @SuppressWarnings("unchecked")
	  public static void updateBangunanPB(Hashtable data) throws Exception
		  {
			
		    Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    	
		    		String id_bangunanpb = (String)data.get("id_bangunanpb");
		    		String txtNoUnit = (String)data.get("txtNoUnit");
		    		String txtKadarSewa = (String)data.get("txtKadarSewa");
		    		String txtSaizBangunan = (String)data.get("txtSaizBangunan");
		    		String txtSaizBangunanDua = (String)data.get("txtSaizBangunanDua");
		    		String txtKegunaan = (String)data.get("txtKegunaan");
		    		String sorSaiz = (String)data.get("sorSaiz");
		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_bangunanpb",id_bangunanpb);
		    		r.add("no_unit_bangunan",txtNoUnit);
		    		r.add("kadar_sewa",txtKadarSewa);
		    		r.add("saiz_bangunan",txtSaizBangunan);
		    		r.add("saiz_bangunan_dua",txtSaizBangunanDua);
		    		r.add("kegunaan_bangunan",txtKegunaan);
		    		r.add("unit_saiz",sorSaiz);
		    		
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",id_user);    		
		    		sql = r.getSQLUpdate("Tblpptbangunanpb");
		    		stmt.executeUpdate(sql);
	    	
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}//close try 
		    finally {
		      if (db != null) db.close();
		    }//close finally
		   
		}//close updateBangunanPB
	 
}//close class
