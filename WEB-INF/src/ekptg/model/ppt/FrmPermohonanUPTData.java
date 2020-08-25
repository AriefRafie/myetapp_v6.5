package ekptg.model.ppt;
//baru
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;
import ekptg.helpers.Utils;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPermohonanUPTData {
	static Logger myLogger = Logger.getLogger(FrmPermohonanUPTData.class);
	private static final Log log = LogFactory.getLog(FrmPermohonanUPTData.class);
	
	private static FrmPermohonanUPTData instance = null;
	public static FrmPermohonanUPTData getInstance()
	  {
	    if (instance == null) instance = new FrmPermohonanUPTData();
	    return instance;
	  }
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	private static  Vector list = new Vector();
	private static  Vector listDokumen = new Vector();
	private  Vector listPohon = new Vector();
	private  Vector listAgensi = new Vector();
	private  Vector listPohon2 = new Vector();
	private  Vector listMaklumatTanah = null;
	private  Vector maklumatTanah = new Vector();
	private  Vector listSeqSubjaket = new Vector();
	private  Vector getIdSuburusanstatusfail = null;
	private  Vector dataSuburusanHakmilik = null;
	private Vector listMaklumatTanahAcceptPenarikan = null;
	private Vector listHMwithIdBorangK = null;
	private static Vector dataKementerianOnline = null;
	private static Vector dataUser = null;
	private static Vector checkExistNoFail = null;
	private static Vector checkExistLot = null;
	
	private static Vector malayDateByDate = null;
	
	
	public static Vector getMalayDateByDate() {
		return malayDateByDate;
	}
	
	public static Vector getDataUser() {
		return dataUser;
	}
	public static Vector getDataKementerianOnline() {
		return dataKementerianOnline;
	}
	public static  Vector getList(){
		return list;
	}
	public Vector getListPohon(){
		return listPohon;
	}
	public Vector getListDokumen(){
		return listDokumen;
	}
	public Vector getListAgensi(){
		return listAgensi;
	}
	public Vector getListPohon2(){
		return listPohon2;
	}
	public Vector getListMaklumatTanah(){
		return listMaklumatTanah;
	}
	public Vector getMaklumatTanah(){
		return maklumatTanah;
	}
	public Vector getListSeqSubjaket(){
		return listSeqSubjaket;
	}
	public Vector getGetIdSuburusanstatusfail(){
		return getIdSuburusanstatusfail;
	}
	public Vector getDataSuburusanHakmilik(){
		return dataSuburusanHakmilik;
	}
	public Vector getListMaklumatTanahAcceptPenarikan(){
		return listMaklumatTanahAcceptPenarikan;
	}
	public Vector getListHMwithIdBorangK(){
		return listHMwithIdBorangK;
	}
	public Vector getCheckExistNoFail(){
		return checkExistNoFail;
	}
	
	//19042012
	@SuppressWarnings("unchecked")
	public Vector getCheckExistLot(){
		return checkExistLot;
	}
	
	//penambahbaikan yati 24082020
	@SuppressWarnings("unchecked")
	private  Vector dataPermohonan = new Vector();
	
	@SuppressWarnings("unchecked")
	public Vector getDataPermohonan(){
		return dataPermohonan;
	}
	
	@SuppressWarnings("unchecked")
	public static void setMalayDateByDate(String stgdate) throws Exception {
		
		malayDateByDate = new Vector();
		malayDateByDate.clear();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'),'DD/MM/YYYY') AS SDATE, TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'DD') AS SDATE_HARI, ";
				sql += " CASE ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '01' THEN 'Januari' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '02' THEN 'Februari' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '03' THEN 'Mac' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '04' THEN 'April' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '05' THEN 'Mei' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '06' THEN 'Jun' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '07' THEN 'Julai' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '08' THEN 'Ogos' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '09' THEN 'September' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '10' THEN 'Oktober' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '11' THEN 'November' ";
				sql += " WHEN TO_CHAR (TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'MM') = '12' THEN 'Disember' ";
				sql += " ELSE '' ";
				sql += " END AS SDATE_BULAN, ";
				sql += " TO_CHAR(TO_DATE('"+stgdate+"','DD/MM/YYYY'), 'YYYY') AS SDATE_TAHUN FROM DUAL ";
				
				myLogger.info(" CHECK DATE BY DATE :"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("sysdateMalayByDate", rs.getString("SDATE")== null?"":rs.getString("SDATE_HARI")+" "+rs.getString("SDATE_BULAN")+" "+rs.getString("SDATE_TAHUN"));
					malayDateByDate.addElement(h);
				}	
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
		if (db != null)db.close();
		}
		
	}//close setMalayDate
	
	@SuppressWarnings("unchecked")
	public static Vector getListPemohon(String userIdNegeri)throws Exception {
		 
		Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    	db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, p.FLAG_STATUS_ONLINE ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND f.id_suburusan = '51' ";
		    		sql +="AND p.id_status IN (11,127,138)";
		    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
		    			if(userIdNegeri.equals("14")){
		    				sql += "AND f.id_negeri in (14,15,16) ";
		    			}else{
		    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
		    			}		
		    		}
		    		sql +="ORDER by f.no_fail desc, p.tarikh_permohonan desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);
		    			h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    			
		    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
		    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    			
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			
		    			if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
		    				
		    				if(rs.getString("flag_semak").equals("1")){
		    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
		    				}else if(rs.getString("flag_semak").equals("2")){
		    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
		    				}else{
		    					h.put("status_fail","PENDAFTARAN");
		    				}
		    				
		    			}else{
		    				h.put("status_fail","PENDAFTARAN");
		    			}
		    			
		    			if(rs.getString("no_fail") == null){
		    				h.put("no_fail","BELUM DISAHKAN");
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
		    	
	}//close getListPemohon
	
	@SuppressWarnings("unchecked")
	public static void setList(String carianPermohonan, String carianTarikh, String cStatus, String cStatusFail, String userIdNegeri)throws Exception {
		
	  Db db = null;
	    list.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    String noPermohonan = carianPermohonan.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, ";
	    		sql += " p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, p.FLAG_STATUS_ONLINE ";
	    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +="WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +="AND p.id_status = s.id_status ";
	    		sql +="AND f.id_suburusan = '51' ";
	    		sql +="AND p.id_status IN (11,127,138)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
	    		//default flag
				boolean setLimit = true;  
	    		
	    		
	    		//NO PERMOHONAN
				if (carianPermohonan != "" && carianPermohonan != null) {
					if (!noPermohonan.equals("")) {
						setLimit = false;
						sql += " AND (UPPER(f.no_fail) LIKE '%" + noPermohonan.toUpperCase() + "%' " +
							   " OR UPPER(p.no_rujukan_ptg) LIKE '%" + noPermohonan.toUpperCase() + "%' " +
							   " OR UPPER(p.no_rujukan_upt) LIKE '%" + noPermohonan.toUpperCase() + "%' " +
							   " OR UPPER(p.no_rujukan_ptd) LIKE '%" + noPermohonan.toUpperCase() + "%')";
					}
				}//close if pemohon
		
				//TARIKH
				if (carianTarikh != "" && carianTarikh != null) {
					if (!cariTarikh.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.tarikh_permohonan) = "+cariTarikh;
					}
				}//close tarikh
	
	    		//STATUS
				if (cStatus != "" && cStatus != null) {
					if (!cStatus.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(s.id_status) = '"+cStatus+"'";
					}
				}//close status

				//STATUS
				if (cStatusFail != "" && cStatusFail != null) {
					if (!cStatusFail.equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(p.flag_semak) = '"+cStatusFail+"'";
					}
				}//close status
				
				
				if(setLimit){	
					sql += " AND ROWNUM <= 10 ";				
				}	
				
				sql +="ORDER by f.no_fail desc, p.tarikh_permohonan desc ";

	    		ResultSet rs = stmt.executeQuery(sql);
	      
	    		Hashtable h;
	    		int bil = 1;

	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			
	    			h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			
	    			if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
	    				
	    				if(rs.getString("flag_semak").equals("1")){
	    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
	    				}else if(rs.getString("flag_semak").equals("2")){
	    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
	    				}else{
	    					h.put("status_fail","PENDAFTARAN");
	    				}
	    				
	    			}else{
	    				h.put("status_fail","PENDAFTARAN");
	    			}
	    			
	    			if(rs.getString("no_fail") == null){
	    				h.put("no_fail","BELUM DISAHKAN");
	    			}else{
	    				h.put("no_fail",rs.getString("no_fail"));
	    			}
	    			list.addElement(h);
	    			bil++;
	    			
	      }//close while
	      //return list;
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}// 
	    finally {
	    if (db != null) db.close();
	    }//close finally
	  }//close setlist
	
	@SuppressWarnings("unchecked")
	public static String addSPT(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	    String sql5 = "";
	    String output="";
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	myLogger.info("masuk db");
	    	//user login id
	    	String id_user = (String)data.get("id_user");
	    	
	    	//Table pptpermohonan
	    	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");	       
	    	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	String no_rujukan_upt = (String)data.get("no_rujukan_upt");
	    	
	    	String txdTarikhPermohonanKjp = (String)data.get("txdTarikhPermohonanKjp");
	    	String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    	String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	    	String txdTarikhPengesahan = (String)data.get("txdTarikhPengesahan");
	    	String tarikh_surat = (String)data.get("tarikh_surat");	       
	    	String flag_peruntukan = (String)data.get("flag_peruntukan");
	    	String flag_segera = (String)data.get("flag_segera");
	    	String tujuan = (String)data.get("tujuan");
	    	String rujukan_surat = (String)data.get("rujukan_kementerian");	      
	    	String id_agensi = (String)data.get("agensi");  
	    	String id_daerah = (String)data.get("daerah");
			String tarikh_lengkap = (String)data.get("tarikh_lengkap"); // penambahan yati 13/12/2016
	    	
	    	String jenis_projek = (String)data.get("jenis_projek");
	    	
	    	String sorJenisKodDaerah = (String)data.get("sorJenisKodDaerah");
	    	
	    	//untuk n9 shj [27042011]
	    	String txtTujuanBI = (String)data.get("txtTujuanBI");
	    	
	    	//table pfdfail
	    	String id_suburusan = (String)data.get("suburusan");
	    	String id_projek_negeri = (String)data.get("projek_negeri");
	    	String id_kementerian = (String)data.get("kementerian");

	    	//convert string to date
	    	String TP = "to_date('" + txdTarikhPermohonanKjp + "','dd/MM/yyyy')";
	    	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
			String TL = "to_date('" + tarikh_lengkap + "','dd/MM/yyyy')";
			String TPP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
			String TSP = "to_date('" + txdTarikhPengesahan + "','dd/MM/yyyy')";
			
	      
	    	
	    	//status "PERMOHONAN CAWANGAN"
	    	int status = 11;
	    	
	    	//status "PERMOHONAN KAUNTER"
	    	int flag_jenisserahan = 2;
	      
	    	//generate no permohonan "JKPTG/PPT/kod_suburusan/this_year-000001
	    	long id_permohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");    
	    	long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	    	long idSenaraiSemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
	    	Date now = new Date();
	    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
	    	String tahun = formatter.format(now);
	    	int thn = Integer.parseInt(tahun);

	    	//generate no permohonan	    	
	    	String seq = String.format("%06d",File.getSeqNo(1,1,0,0,0,false,false,thn,0));	    	
	    	String noPermohonan = tahun+"-"+seq;
	      
	    	SQLRenderer rF = new SQLRenderer();
	    	rF.add("id_fail",idFail);
	    	rF.add("id_suburusan", id_suburusan);
	    	rF.add("id_negeri", id_projek_negeri);
	    	rF.add("id_kementerian", id_kementerian);
	    	rF.add("id_seksyen", "1");
	    	rF.add("tarikh_masuk",rF.unquote("sysdate"));
			rF.add("id_masuk",id_user);
	    	sql = rF.getSQLInsert("tblpfdfail");
	    	stmt.executeUpdate(sql);
	      
	    	SQLRenderer rPH = new SQLRenderer();	
	     	rPH.add("id_permohonan",id_permohonan);
	     	rPH.add("no_permohonan",noPermohonan);
	     	rPH.add("id_fail",idFail);
	     	rPH.add("flag_peruntukan", flag_peruntukan);
	     	rPH.add("flag_segera", flag_segera);
	     	rPH.add("flag_jenis_projek", jenis_projek);
	     	rPH.add("tujuan", tujuan);
	     	rPH.add("flag_semak", "0");
	     	rPH.add("no_rujukan_surat", rujukan_surat);
	     	rPH.add("tarikh_permohonan_kjp", rPH.unquote(TP));
	     	rPH.add("tarikh_permohonan", rPH.unquote(TPP));
	     	rPH.add("tarikh_kehendaki", rPH.unquote(TK));
	     	rPH.add("tarikh_sahkan",rPH.unquote("sysdate"));
	     	rPH.add("tarikh_surat", rPH.unquote(TS));
			rPH.add("tarikh_lengkap", rPH.unquote(TL));
	     	rPH.add("id_daerah", id_daerah);
	     	rPH.add("id_status", status);
	     	rPH.add("id_agensi", id_agensi);
	     	rPH.add("flag_jenispermohonan", flag_jenisserahan);
	     	rPH.add("no_rujukan_ptg",no_rujukan_ptg);
	     	rPH.add("no_rujukan_ptd",no_rujukan_ptd);
	     	rPH.add("no_rujukan_upt",no_rujukan_upt);
	     	
	     	rPH.add("flag_jenis_kod_daerah",sorJenisKodDaerah);

	     	//untuk n9 shj [27042011]
	     	rPH.add("tujuan_bi",txtTujuanBI);
	     	
	     	rPH.add("tarikh_masuk",rPH.unquote("sysdate"));
			rPH.add("id_masuk",id_user);
	     	sql = rPH.getSQLInsert("tblpptpermohonan");
			myLogger.info("INSERT TEST SEK 4 : "+sql);
	     	stmt.executeUpdate(sql);
	  
	     	SQLRenderer rS = new SQLRenderer();
	     	rS.add("id_senaraisemak",idSenaraiSemak);
	     	rS.add("id_permohonan", id_permohonan);
	    	rS.add("tarikh_masuk",rS.unquote("sysdate"));
			rS.add("id_masuk",id_user);
	     	sql = rS.getSQLInsert("tblpptsenaraisemak");
	     	stmt.executeUpdate(sql);
	     	
	     	//id suburusanstatusfail
	     	String id_suburusanstatus = "";
	     	if(id_suburusan.equals("51")){
	     		id_suburusanstatus = "1420";
	     	}else if(id_suburusan.equals("52")){
	     		id_suburusanstatus = "1466";
	     	}else if(id_suburusan.equals("53")){
	     		id_suburusanstatus = "1512";
	     	}
	     	
	     	
	     		SQLRenderer r5 = new SQLRenderer();
	     		r5.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
	     		r5.add("ID_PERMOHONAN",id_permohonan);
	     		r5.add("ID_FAIL",idFail);
	     		r5.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     		r5.add("AKTIF",1);
				r5.add("ID_MASUK",id_user);
				r5.add("ID_KEMASKINI",id_user);
				r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				sql5 = r5.getSQLInsert("tblrujsuburusanstatusfailppt");
				stmt.executeUpdate(sql5);	
	     	
	     	
	     	output = ""+id_permohonan;
	    	
	     	conn.commit();	
		      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }
	    finally {
	    if (db != null) db.close();
	    }
	    
	    return output;
	   
	  }//close add
	
	
	public static void deleteMaklumatTanah(String id) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	r.add("id_hakmilik", id);
	    	sql = r.getSQLDelete("tblppthakmilik");
	    	stmt.executeUpdate(sql);
	    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close delete
	
	
	@SuppressWarnings("unchecked")
	public static void updateMaklumatTanah(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 String id_hakmilik = (String)data.get("id_hakmilik");
	      
	    	 //seksyen 4 & 8 
	    	 String negeri = (String)data.get("editNegeri");
	    	 String daerah = (String)data.get("editDaerah");
	    	 String mukim = (String)data.get("editMukim");
	    	 String txtseksyen = (String)data.get("txtseksyen");
	    	 String txtnolot = (String)data.get("txtnolot");
	    	 String txtnopt = (String)data.get("txtnopt");
	    	 String catatan = (String)data.get("catatan");
	    	 
	    	 //rizab
	    	 String sorJenisRizab = (String)data.get("sorJenisRizab");
	    	 String txtLain = (String)data.get("txtLain");
	    	 String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
	    	 String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    	 
	    	 String daerahpenggawa = (String)data.get("daerahpenggawa");
	    	 
	    	 //seksyen 8
	    	 String jenishakmilik = (String)data.get("editJenisHakmilik");
	    	 String edit_no_hakmilik = (String)data.get("edit_no_hakmilik");  
	    	 String luas = (String)data.get("editLuas");
	    	 String lot = (String)data.get("editLot");
	    	 String edit_luas_lot = (String)data.get("edit_luas_lot");
	    	 String edit_anggaran_luas = (String)data.get("edit_anggaran_luas");
	    	 String socKategoriTanah = (String)data.get("socKategoriTanah");
	    	 
//	    	PPT-03 Penambahan Strata
	    	String no_bangunan = (String)data.get("no_bangunan");
	    	String no_tingkat = (String)data.get("no_tingkat");
	    	String no_petak = (String)data.get("no_petak");
	    	
	    	 String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilik", id_hakmilik);
	    	 r.add("id_daerahpenggawa",daerahpenggawa);
	    	 r.add("id_negeri", negeri);
	    	 r.add("no_pt", txtnopt);
	    	 r.add("no_warta_rizab", txtNoWartaRizab); 	
	    	 r.add("tarikh_warta_rizab", r.unquote(TW));
	    	 r.add("flag_jenis_rizab", sorJenisRizab); 	
	    	 r.add("nama_lain_rizab", txtLain);
	    	 r.add("id_jenishakmilik", jenishakmilik);
	    	 r.add("id_daerah", daerah);
	    	 r.add("id_mukim", mukim);
	    	 r.add("id_unitluaslot", luas);
	    	 r.add("id_lot", lot);		  
	    	 r.add("no_hakmilik", edit_no_hakmilik);
	    	 r.add("no_lot", txtnolot);
	    	 r.add("luas_lot", edit_luas_lot);
	    	 r.add("luas_ambil", edit_anggaran_luas);
	    	 r.add("catatan",catatan);
	    	 r.add("seksyen",txtseksyen);
	    	 r.add("id_kategoritanah",socKategoriTanah);
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
		     
//	    	PPT-03 Penambahan Strata
	    	r.add("no_bangunan",no_bangunan);
	    	r.add("no_tingkat",no_tingkat);
	    	r.add("no_petak",no_petak);
		     
	    	 sql = r.getSQLUpdate("tblppthakmilik");
	    	 stmt.executeUpdate(sql);
	    	 myLogger.info("updateMaklumatTanah TBLPPTHAKMILIK   :"+sql);
	    	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close updateMaklumatTanah
	
	
	@SuppressWarnings("unchecked")
	public static void update(Hashtable data) throws Exception {
		
		
	    Db db = null;
	    String sql = "";
	    long id_senaraisemak = 0;
	   
	    try{
	      
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
	    	 	
	    	 	String id_user = (String)data.get("id_user");
	    	 	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");
	    	 	
	    	 	String jumlah_hakmilik = (String)data.get("jumlah_hakmilik");
	    	 	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	 	String no_rujukan_upt = (String)data.get("no_rujukan_upt");
	    	 	
	    		String id_permohonan = (String)data.get("id_permohonan"); 
	    		String id_fail = (String)data.get("id_fail"); 
	    		String id_check = (String)data.get("id_senaraisemak"); 
	    		//penambahan yati
	
	      
	    		String sorJenisKodDaerah = (String)data.get("sorJenisKodDaerah"); 
	    		
	    		String id_urusan = (String)data.get("id_urusan"); 
	    		int idUrusan = 0;
	    		if(id_urusan!=""){
	    			idUrusan = Integer.parseInt(id_urusan);
	    		}

	    		//checkbox seksyen 8
	    		String semak_1 = (String)data.get("semak1"); 
	    		String semak_2 = (String)data.get("semak2"); 
	    		String semak_3 = (String)data.get("semak3"); 
	    		String semak_4 = (String)data.get("semak4"); 
	    		String semak_5 = (String)data.get("semak5"); 
	    		String semak_6 = (String)data.get("semak6"); 
	    		String semak_7 = (String)data.get("semak7"); 
	    		String semak_8 = (String)data.get("semak8"); 
	      
	    		//checkbox seksyen 4
	    		String semak_10 = (String)data.get("semak10"); 
	    		String semak_20 = (String)data.get("semak20");
	     
	    		String id_daerah = (String)data.get("daerah");
	    		String id_projekNegeri = (String)data.get("projeknegeri");
	    		String id_agensi = (String)data.get("agensi");
	    		String id_kementerian = (String)data.get("kementerian");
		  
	    		String tarikh_lengkap = (String)data.get("tarikh_lengkap");
	    		String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    		String txdTarikhPermohonanKjp = (String)data.get("txdTarikhPermohonanKjp") == null ? "": (String)data.get("txdTarikhPermohonanKjp");
	    		String txdTarikhPengesahan = (String)data.get("txdTarikhPengesahan") == null ? "": (String)data.get("txdTarikhPengesahan");
	    		String tujuan = (String)data.get("tujuan");
	    		String rujukan_kementerian = (String)data.get("rujukan_kementerian");
	    		String tarikh_hendak = (String)data.get("tarikh_hendak");
	    		String tarikh_surat = (String)data.get("tarikh_surat");
	    		String flagPeruntukan = (String)data.get("flag_peruntukan");
	    		String flagSegera = (String)data.get("flag_segera");
		  		  
	    		String flagJenisProjek = (String)data.get("jenis_projek");
				//untuk n9 shj [27042011]
				String txtTujuanBI = (String)data.get("txtTujuanBI");
	    		
	    		String TP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + tarikh_lengkap + "','dd/MM/yyyy')";
	    		String TKJP = "to_date('" + txdTarikhPermohonanKjp + "','dd/MM/yyyy')";
	    		String TSP = "to_date('" + txdTarikhPengesahan + "','dd/MM/yyyy')";
	    		//if(!tarikh_lengkap.equals("")){
	    		
	    		//}
		  
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("tarikh_kehendaki", r.unquote(TH));
	    		r.add("tarikh_surat", r.unquote(TS));
	    		r.add("tarikh_permohonan", r.unquote(TP));
	    		r.add("tarikh_permohonan_kjp", r.unquote(TKJP));
	    		//if(!tarikh_lengkap.equals("")){
	    		r.add("tarikh_lengkap", r.unquote(TL));
	    		r.add("tarikh_sahkan",r.unquote("sysdate"));
	    		//}
	    		r.add("tujuan", tujuan);
	    		r.add("no_rujukan_surat", rujukan_kementerian);
	    		r.add("id_agensi", id_agensi);
	    		r.add("id_daerah",id_daerah);
	    		r.add("flag_peruntukan", flagPeruntukan);
	    		r.add("flag_segera", flagSegera);
	    		r.add("flag_jenis_projek", flagJenisProjek);
	    		r.add("no_rujukan_ptg",no_rujukan_ptg);
	    		r.add("no_rujukan_ptd",no_rujukan_ptd);
	    		r.add("no_rujukan_upt",no_rujukan_upt);
	    		r.add("flag_jenis_kod_daerah",sorJenisKodDaerah);
	    		r.add("id_kemaskini",id_user);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		if(idUrusan==52){
	   	      	r.add("jumlah_hakmilik",jumlah_hakmilik);
	    		}
	   	   //untuk n9 shj [27042011]
		     	r.add("tujuan_bi",txtTujuanBI);
	    		sql = r.getSQLUpdate("tblpptpermohonan");	    
	    		myLogger.info("UPDATE TBLPPTPERMOHONAN  :"+sql);
	    		stmt.executeUpdate(sql);

	    		//projek negeri
	    		SQLRenderer rF = new SQLRenderer();
	    		rF.update("id_fail",id_fail);
	    		rF.add("id_negeri", id_projekNegeri);
	    		rF.add("id_kementerian", id_kementerian);
	    		rF.add("id_suburusan", id_urusan);
	    		rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
	   	      	rF.add("id_kemaskini",id_user);
	    		sql = rF.getSQLUpdate("tblpfdfail");
	    		stmt.executeUpdate(sql);
	      
	    		//checkbutton seksyen 8
	    		if(idUrusan==52)
	    		{
	    			if(id_check.equals(""))
					{
		    			id_senaraisemak = DB.getNextID(db, "TBLPPTSENARAISEMAK_SEQ");
					}
					else
					{
						id_senaraisemak = Long.parseLong(id_check);
					}
	    			
	    			SQLRenderer rCK = new SQLRenderer();
	    			
	    			if(id_check.equals(""))
					{
						rCK.add("i.id_senaraisemak", id_check);
					}
					else
					{
						rCK.update("i.id_senaraisemak", id_check);
					}

	    			//rCK.add("i.id_senaraisemak",id_senaraisemak);
	    			rCK.add("i.id_permohonan", id_permohonan);
	    			rCK.add("i.semak1", semak_1);
	    			rCK.add("i.semak2", semak_2);
	    			rCK.add("i.semak3", semak_3);
	    			rCK.add("i.semak4", semak_4);
	    			rCK.add("i.semak5", semak_5);
	    			rCK.add("i.semak6", semak_6);
	    			rCK.add("i.semak7", semak_7);
	    			rCK.add("i.semak8", semak_8); //penambahan yati
	    			rCK.add("tarikh_kemaskini",rCK.unquote("sysdate"));
	    		    rCK.add("id_kemaskini",id_user);
	    		    if(id_check.equals(""))
					{    		    	
					sql = rCK.getSQLInsert("tblpptsenaraisemak i");
					stmt.executeUpdate(sql);
					}
	    			else {
	    			
	    				sql = rCK.getSQLUpdate("tblpptsenaraisemak i");
	    				stmt.executeUpdate(sql);
	    			}   		   
	    		
	    		}      
	    		//checkbutton seksyen 4
	    		if(idUrusan==51)
	    		{ 
	    		
	    		if(id_check.equals(""))
				{
	    			id_senaraisemak = DB.getNextID(db, "TBLPPTSENARAISEMAK_SEQ");
				}
				else
				{
					id_senaraisemak = Long.parseLong(id_check);
				}
	    		
	    			SQLRenderer rCK4 = new SQLRenderer();
	    			
	    			if(id_check.equals(""))
					{
						rCK4.add("j.id_senaraisemak", id_senaraisemak);
					}
					else
					{
						rCK4.update("j.id_senaraisemak", id_senaraisemak);
					}

	    			//rCK4.add("j.id_senaraisemak",id_senaraisemak);
	    	
	    			rCK4.add("j.id_permohonan", id_permohonan);
	    			rCK4.add("j.semak1", semak_10);
	    			rCK4.add("j.semak2", semak_20);
	    			rCK4.add("tarikh_kemaskini",rCK4.unquote("sysdate"));
	    		    rCK4.add("id_kemaskini",id_user);
	    		    if(id_check.equals(""))
					{
					sql = rCK4.getSQLInsert("tblpptsenaraisemak j");
					stmt.executeUpdate(sql);
					}
	    			else {
	    				
	    				sql = rCK4.getSQLUpdate("tblpptsenaraisemak j");
	    				stmt.executeUpdate(sql);
	    			}
	    		}
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	myLogger.info("insert LIST ::: "+sql);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close update
	
	
	@SuppressWarnings("unchecked")
	public void setListPohon(String idpermohonan) throws Exception {
		Db db = null;
		listPohon.clear();
		String sql = "";
		
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT DISTINCT p.tujuan_bi, p.id_permohonan, p.id_status, p.jumlah_hakmilik, p.flag_jenispermohonan, p.no_permohonan, f.no_fail, flag_jenis_kod_daerah, ";
				sql += " f.id_fail, n.nama_negeri, case when p.tarikh_permohonan is not null then p.tarikh_permohonan else sysdate end as tarikh_permohonan,";
				sql += " p.tarikh_permohonan_kjp, p.tujuan, k.nama_kementerian, k.id_kementerian, catatan_status_online, "; 
				sql += " s.keterangan, d.nama_daerah, d.id_daerah, p.tarikh_kehendaki,  p.tarikh_surat,p.tarikh_lengkap, p.id_agensi, p.flag_status_online, "; 
				sql += " p.no_rujukan_surat, p.flag_peruntukan, p.flag_segera, p.id_mukim, p.flag_jenis_projek, p.no_permohonan_online,p.tarikh_sahkan, "; 
				sql += " us.nama_suburusan, us.id_suburusan, smk.id_senaraisemak, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, ";
				sql += " k.alamat1, k.alamat2, k.alamat3, k.poskod, k.id_negeri, p.flag_semak, n2.id_negeri as idProjekNegeri, n2.nama_negeri as nama_negeriprojek ";
				sql += " FROM Tblrujsuburusan us, Tblpptsenaraisemak smk, Tblpfdfail f, Tblrujdaerah d, Tblrujnegeri n, ";
				sql += " Tblrujkementerian k, Tblrujstatus s,  Tblpptpermohonan p, Tblrujnegeri n2 ";  
				sql += " WHERE f.id_fail(+) = p.id_fail "; 
				sql += " AND k.id_kementerian(+) = f.id_kementerian "; 
				sql += " AND n.id_negeri(+) = k.id_negeri "; 
				sql += " AND n2.id_negeri(+) = f.id_negeri "; 
				sql += " AND s.id_status(+) = p.id_status "; 
				sql += " AND d.id_daerah(+) = p.id_daerah "; 
				sql += " AND us.id_suburusan(+) = f.id_suburusan "; 
				sql += " AND p.id_permohonan = smk.id_permohonan(+) ";  
				sql += " AND p.id_permohonan = '"+idpermohonan+"'";

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("tujuan_bi", rs.getString("tujuan_bi")==null?"":rs.getString("tujuan_bi"));
					h.put("flag_jenis_kod_daerah", rs.getString("flag_jenis_kod_daerah")==null?"":rs.getString("flag_jenis_kod_daerah"));
					h.put("jumlah_hakmilik", rs.getString("jumlah_hakmilik")==null?"":rs.getString("jumlah_hakmilik"));
					h.put("catatan_status_online", rs.getString("catatan_status_online")==null?"":rs.getString("catatan_status_online"));
					h.put("flag_status_online", rs.getString("flag_status_online")==null?"":rs.getString("flag_status_online"));
					h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
					h.put("no_permohonan_online", rs.getString("no_permohonan_online")==null?"":rs.getString("no_permohonan_online"));
					h.put("flag_jenis_projek", rs.getString("flag_jenis_projek")==null?"":rs.getString("flag_jenis_projek"));
					h.put("nama_negeriprojek", rs.getString("nama_negeriprojek")==null?"":rs.getString("nama_negeriprojek"));
					h.put("flag_semak", rs.getString("flag_semak")==null?"":rs.getString("flag_semak"));
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("idProjekNegeri", rs.getString("idProjekNegeri")==null?"":rs.getString("idProjekNegeri"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("id_senaraisemak", rs.getString("id_senaraisemak")==null?"":rs.getString("id_senaraisemak"));
					h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
					h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
					h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
					h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
					h.put("noPermohonan", rs.getString("no_permohonan")==null?"":rs.getString("no_permohonan"));
					h.put("no_fail", rs.getString("no_fail")==null?"BELUM DISAHKAN":rs.getString("no_fail"));
					h.put("no_jkptg", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
					h.put("tarikh_permohonan_kjp", rs.getDate("tarikh_permohonan_kjp")==null?"":Format.format(rs.getDate("tarikh_permohonan_kjp")));
					h.put("tarikh_sahkan", rs.getDate("tarikh_sahkan")==null?"":Format.format(rs.getDate("tarikh_sahkan")));
					h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
					h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
					h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
					h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
					h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
					h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
					h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
					h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
					h.put("tarikh_lengkap", rs.getDate("tarikh_lengkap")==null?"":Format.format(rs.getDate("tarikh_lengkap")));
					//h.put("tarikh_sahkan", rs.getDate("tarikh_sahkan")==null?"":Format.format(rs.getDate("tarikh_sahkan")));
				
					
					if(rs.getString("flag_jenispermohonan") != null && rs.getString("flag_jenispermohonan") != ""){
						
						if(rs.getString("flag_jenispermohonan").equals("1")){
							h.put("flag_jenispermohonan","PERMOHONAN ONLINE");
						}else if(rs.getString("flag_jenispermohonan").equals("2")){
							h.put("flag_jenispermohonan","PERMOHONAN KAUNTER");
						}else{
							h.put("flag_jenispermohonan","");
						}
						
					}else{
						h.put("flag_jenispermohonan","");
					}
					
					h.put("no_flag_jenispermohonan", rs.getString("flag_jenispermohonan")==null?"":rs.getString("flag_jenispermohonan"));
					
					listPohon.addElement(h);
					myLogger.info("jumlah hakmilik xx : "+h.put("jumlah_hakmilik", rs.getString("jumlah_hakmilik")==null?"":rs.getString("jumlah_hakmilik")));
				}
				
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setlistpohon
	
	@SuppressWarnings("unchecked")
	public void setListAgensi(int id) throws Exception {
		
		Db db = null;
		listAgensi.clear();
		String sql = "";
		
		
		try{
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			r.add("p.id_permohonan");
			r.add("a.nama_agensi");
			r.add("a.id_agensi");
		
			r.add("a.id_agensi",r.unquote("p.id_agensi"));
		
			r.add("p.id_Permohonan",id);
		
			sql = r.getSQLSelect("Tblpptpermohonan p, tblrujagensi a");
		
			ResultSet rs = stmt.executeQuery(sql);
	
			Hashtable h;
		
			while(rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("idAgensi", rs.getString("id_agensi")==null?"":rs.getString("id_agensi"));
				h.put("nama_agensi", rs.getString("nama_agensi"));
			
				listAgensi.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setlistpohon
	
	@SuppressWarnings("unchecked")
	public void setListPohon2(String id) throws Exception {
		
		Db db = null;
		listPohon2.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				r.add("p.id_permohonan");
				r.add("p.id_fail");
				r.add("n.nama_negeri");
				r.add("n.id_negeri");
		
				r.add("f.id_fail",r.unquote("p.id_fail"));
				r.add("n.id_negeri",r.unquote("f.id_negeri"));
		
	
				r.add("p.id_Permohonan",id);
		
				sql = r.getSQLSelect("Tblpfdfail f, Tblrujnegeri n, Tblpptpermohonan p");
		
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					h.put("projek_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("idProjekNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
	
			
					listPohon2.addElement(h);
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close list pohon2
	
	
	@SuppressWarnings("unchecked")
	public static void add_maklumat_tanah(Hashtable data) throws Exception
	  {
//		System.out.println("DATA---"+data);
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		String id_user = (String)data.get("id_user");
	    		
	    		//seksyen 4 & 8
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_negeri = (String)data.get("negeri");
	    		String id_daerah = (String)data.get("daerah");
	    		String id_mukim = (String)data.get("mukim");
	    		String txtseksyen = (String)data.get("txtseksyen");
	    		String catatan = (String)data.get("catatan");
	    		String txtnolot = (String)data.get("txtnolot");
	    		String txtnopt = (String)data.get("txtnopt");
	    		String daerahpenggawa = (String)data.get("daerahpenggawa");
	    		
	    		//rizab
	    		String sorJenisRizab = (String)data.get("sorJenisRizab");
	    		String txtLain = (String)data.get("txtLain");
	    		String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
	    		String txdTarikhWarta = (String)data.get("txdTarikhWarta");
	    		
	    		//seksyen 8
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik");	 
	    		String no_hakmilik = (String)data.get("no_hakmilik");
	    		String id_lot = (String)data.get("lot");
	    		String id_luas = (String)data.get("luas");
	    		String luas_lot = (String)data.get("luas_lot");
	    		String luas_ambil = (String)data.get("anggaran_luas");	
	    		String socKategoriTanah = (String)data.get("socKategoriTanah");
	    		
	    		//PPT-03 Penambahan Strata
	    		String no_bangunan = (String)data.get("txtNoBangunan");
	    		String no_tingkat = (String)data.get("txtNoTingkat");
	    		String no_petak = (String)data.get("txtNoPetak");
	    		
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_daerahpenggawa", daerahpenggawa);
	    		r.add("id_negeri", id_negeri); 	
	    		r.add("no_pt", txtnopt);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
	    		r.add("tarikh_warta_rizab", r.unquote(TW));
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("flag_jenis_rizab", sorJenisRizab); 	
	    		r.add("nama_lain_rizab", txtLain);
	    		r.add("id_daerah", id_daerah);
	    		r.add("id_mukim", id_mukim);
	    		r.add("id_unitluaslot", id_luas);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("luas_lot", luas_lot);
	    		r.add("id_kategoritanah",socKategoriTanah);
	    		r.add("catatan",catatan);
	    		r.add("seksyen",txtseksyen);
	    		r.add("tarikh_masuk",r.unquote("sysdate"));
	    		r.add("id_masuk",id_user);	 
	    		
//	    		PPT-03 Penambahan Strata
	    		r.add("no_bangunan",no_bangunan);
	    		r.add("no_tingkat",no_tingkat);
	    		r.add("no_petak",no_petak);
//	    		r.add("column_name", String.valueOf(data.get("")));
//	    		r.add("no_bangunan",String.valueOf(data.get("txtNoBangunan"))); 
//	    		r.add("no_tingkat", String.valueOf(data.get("txtNoTingkat")));
//	    		r.add("no_petak", String.valueOf(data.get("txtNoPetak")));
	    		myLogger.info("Simpan maklumat no bangunan, tingkat dan petak");
	    		
	    		sql = r.getSQLInsert("tblppthakmilik");
	    		
	    		myLogger.info("ADD HAKMILIK SEK 4 :"+sql.toUpperCase());
	    		
	    		stmt.executeUpdate(sql);
	    	
	    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	    if (db != null) db.close();
	    }//close finally
	   
	  }//close add_maklumat_tanah
	
	@SuppressWarnings("unchecked")
	
	/*public void setListMaklumatTanah(String idPermohonan,String lot,String idpegawai) throws Exception{
		setListMaklumatTanah(idPermohonan,lot,idpegawai,null,null);
	}*/
	
	
public void setListMaklumatTanah_SEGERA(String idPermohonan,String lot,String idpegawai) throws Exception{
        
        listMaklumatTanah = new Vector();
        
        Db db = null;
        listMaklumatTanah.clear();
        String sql = "";
        String noLOT = lot.trim();
        String nama2Mukim = "";
        String listLOT = "";
        String listLOTHM = "";
        double totalSize = 0;
        
        try {
                    db = new Db();
                     Statement stmt = db.getStatement();
                     
                     sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) " +
                     		"" +
                     		" AND (p.flag_segera = '1' or  (p.flag_segera = '3' and m.FLAG_SEGERA_SEBAHAGIAN = 'Y'))  "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rx = stmt.executeQuery(sql);                        
                     int totalMukim = 0;

                     while (rx.next()){                              
                             totalMukim = rx.getInt("totalMukim");                                                                                              
                     }
        
                     sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) " +
                     		"" +
                     		" AND (p.flag_segera = '1' or  (p.flag_segera = '3' and m.FLAG_SEGERA_SEBAHAGIAN = 'Y')) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rz = stmt.executeQuery(sql);   
                     
                     String namaMukim = "";
                     int bilMukim = 0;
                     
                     while (rz.next()){                              
                                   
                            if(totalMukim!=0){
                                   if(bilMukim==0){
                                          
                                          namaMukim += namaMukim = rz.getString("nama_mukim");   
                                   
                                   }else{
                                          
                                          if(totalMukim - bilMukim == 1){
                                                 namaMukim += " dan "+rz.getString("nama_mukim");
                                          }else{
                                                 namaMukim += ", "+rz.getString("nama_mukim");   
                                          }      
                                   }
                                   bilMukim++;   
                            
                            }else{
                                   namaMukim = "";
                            }
                            
                            if(totalMukim == bilMukim){
                                   nama2Mukim = namaMukim;
                            }else{
                                   nama2Mukim = "";
                            }
                     }
                     
                     
                     ////////////get lot////////////////////
                     sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
                     sql += " AND m.id_lot = lt.id_lot(+) " +
                     		" AND (p.flag_segera = '1' or  (p.flag_segera = '3' and m.FLAG_SEGERA_SEBAHAGIAN = 'Y')) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rl = stmt.executeQuery(sql);                        
                     int totalLOT = 0;

                     while (rl.next()){                              
                            totalLOT = rl.getInt("totalLOT");                                                                                           
                     }
                     
                     sql = "SELECT CASE ";
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
                     sql += " ELSE '' ";
                     sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
                     sql += " AND m.id_lot = lt.id_lot(+) "; 
                     sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+) " +
                     		"" +
                     		" AND (p.flag_segera = '1' or  (p.flag_segera = '3' and m.FLAG_SEGERA_SEBAHAGIAN = 'Y')) ";
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rl2 = stmt.executeQuery(sql);  
                     
                     String LotPT = "";
                     String LotPTHM = "";
                     int bilLOT = 0;
                     
                     while (rl2.next()){                             
                            
                            if(totalLOT!=0){
                                   if(bilLOT==0){
                                          
                                          LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));   
                                          LotPT += LotPT = rl2.getString("NO_LOTPT");
                                          
                                   }else{
                                          
                                          if(totalLOT - bilLOT == 1){
                                                 LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
                                                 LotPT += " dan "+rl2.getString("NO_LOTPT");
                                          }else{
                                                 LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));   
                                                 LotPT += ", "+rl2.getString("NO_LOTPT");
                                          }      
                                   }
                                   bilLOT++;     
                            
                            }else{
                                   LotPT = "";
                                   LotPTHM = "";
                            }
                            
                            if(totalLOT == bilLOT){
                                   listLOT = LotPT;
                                   listLOTHM = LotPTHM;
                            }else{
                                   listLOT = "";
                                   listLOTHM = "";
                            }
                     }
                     
                     sql = "SELECT * ";
                     sql +=               "  "; 
                     sql += " from ( ";
                                   
                                   sql +=               "SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC, LPAD (m.no_lot, 20) ASC, LPAD (m.no_pt, 20) ASC, LPAD (m.no_subjaket, 20) ASC) AS rn," +
                                                 "m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
                     
                     sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
                     sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
                     
                     sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
                     sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
                     sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
                     sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
                     sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
                     sql += " CASE ";
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
                      sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
                     sql += " ELSE 'TIADA' ";
                     sql += " END AS NO_LOTPT, m.seksyen ";  
                     sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
                     sql += " Tblrujdaerah d, Users u";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
                     sql += " AND m.id_negeri = n.id_negeri "; 
                     sql += " AND p.id_fail = f.id_fail "; 
                     sql += " AND m.id_daerah = d.id_daerah(+)";
                     sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
                     sql += " AND m.id_pegawai = u.user_id(+)";
                     sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
                     sql += " AND m.id_lot = lt.id_lot(+) ";
                     sql += " AND m.id_mukim = mk.id_mukim(+) ";  
                     sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
                     sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'   " +
                     		" AND (p.flag_segera = '1' or  (p.flag_segera = '3' and m.FLAG_SEGERA_SEBAHAGIAN = 'Y'))  ";
               //################   
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
 
                     //NO LOT / NAMA PB
                     if (lot != "" && lot != null) {
                            if (!noLOT.equals("")) {
                                   sql += " AND (UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
                                             " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
                                             " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
                                             " OR M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1,TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
                               " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
                               " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
                               " AND M1.ID_HAKMILIK = M.ID_HAKMILIK  " +
                               "  "+ 
                               " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%'))) ";
                            }
                     }//close if nolot
                     
                     //ID PEGAWAI
                     if (idpegawai != "" && idpegawai != null) {
                                   sql += " AND m.id_pegawai = '"+idpegawai+"'";
                     }//close if idpegawai
                     
                     
                     //sql += " ORDER BY LPAD(m.no_subjaket,20) asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, mk.nama_mukim asc ";
                     //Susunan di ubah ke mukim,lot,subjaket
                     sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
                     
                     
                     
                     sql += ") ";
                     
               /*     if(from!=null && to!=null)
                     {
                     sql += " where rn >= "+from+"  and  rn <= "+to+" ";
                     }*/
                     
                     myLogger.info(" cARIAN LOT : "+sql);
                     ResultSet rs = stmt.executeQuery(sql);   
                     Hashtable h;
                     int bil = 1;
                     
                     while (rs.next()){
                            h = new Hashtable();
                            h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
                            h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
                            h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
                            h.put("listLOT", listLOT.toUpperCase());
                            h.put("listLOTHM", listLOTHM.toUpperCase());
                            h.put("nama2Mukim", nama2Mukim.toUpperCase());
                            h.put("nama2MukimInit", nama2Mukim);
                            h.put("bil", bil);
                            h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
                            h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
                            h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
                            h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
                            h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
                            h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
                            h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
                            h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
                            h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
                            h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
                            h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
                            h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));               
                            h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
                            h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
                            h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
                            h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
                            h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
                            h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
                            h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
                            h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
                            h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
                            h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
                            h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
                            h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
                            h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
                            
                            h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
                            h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
                            h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
                            h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
                            
                            if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
                                   
                                   double luasAmbil = rs.getDouble("luas_ambil");
                                   String LA = Utils.formatLuasHM(luasAmbil);
                                   h.put("luas_ambil",LA);
                                                 
                            }else{
                                   h.put("luas_ambil","0");
                            }
                            
                            
                            //validation for PU
                            if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
                                   
                                   double luasAmbil = 0;
                                   if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
                                          luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
                                   }else{
                                          luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
                                   }
                                   
                                   double luasLot = 0;
                                   if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
                                          luasLot = rs.getDouble("luas_lot"); //meter persegi
                                   }else{
                                          luasLot = rs.getDouble("luas_lot") * 10000; //hektar
                                   }                                        
                                   
                                   double baki = luasLot - luasAmbil;
                                   
                                   if(baki>0){
                                          h.put("flagPU","yes");
                                   }else{
                                          h.put("flagPU","no");
                                   }
                     
                            }else{
                                   h.put("flagPU","no");
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
                                          
                                          if(rs.getString("luas_ambil") != null){
                                                 double luasAmbil = rs.getDouble("luas_ambil");
                                                 String LAH = Utils.formatLuasHM(luasAmbil);
                                                 h.put("nilaiTanah",LAH);
                                          }else{
                                                 h.put("nilaiTanah","0");
                                          }
                                          
                                   }else{                                   
                                          if(rs.getString("luas_ambil") != null){
                                                 double luasAmbil = rs.getDouble("luas_ambil") / 10000;
                                                 String LAM = Utils.formatLuasHM(luasAmbil);
                                                 h.put("nilaiTanah",LAM);
                                          }else{
                                                 h.put("nilaiTanah","0");
                                          }                                        
                                   }      
                                   
                            }else{
                                   h.put("nilaiTanah","0");
                            }
                            
                            
                            //get total luas ambil in hectar
                            if(rs.getString("luas_ambil") != null){  
                                   if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
                                          totalSize += rs.getDouble("luas_ambil");
                                   }else{
                                          totalSize += (rs.getDouble("luas_ambil") / 10000);
                                   }
                            }
                            // ADDED BY ELLY
                            //myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
                            h.put("luas_keseluruhan", totalSize);                                
                            
                            h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
                            h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
                            h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
                            
                            listMaklumatTanah.addElement(h);
                            bil++; 
                     }
               //return list;
        } catch (Exception re) {
        	log.error("Error: ", re);
        	throw re;
        	}finally {
               if(db != null) db.close();
        }
        }//close setDataListKertas
	
	
    public void setListMaklumatTanah(String idPermohonan,String lot,String idpegawai) throws Exception{
        
        listMaklumatTanah = new Vector();
        
        Db db = null;
        listMaklumatTanah.clear();
        String sql = "";
        String noLOT = lot.trim();
        String nama2Mukim = "";
        String listLOT = "";
        String listLOTHM = "";
        double totalSize = 0;
        
        try {
                    db = new Db();
                     Statement stmt = db.getStatement();
                     
                     sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rx = stmt.executeQuery(sql);                        
                     int totalMukim = 0;

                     while (rx.next()){                              
                             totalMukim = rx.getInt("totalMukim");                                                                                              
                     }
        
                     sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rz = stmt.executeQuery(sql);     
                     
                     String namaMukim = "";
                     int bilMukim = 0;
                     
                     while (rz.next()){                              
                                   
                            if(totalMukim!=0){
                                   if(bilMukim==0){
                                          
                                          namaMukim += namaMukim = rz.getString("nama_mukim");   
                                   
                                   }else{
                                          
                                          if(totalMukim - bilMukim == 1){
                                                 namaMukim += " dan "+rz.getString("nama_mukim");
                                          }else{
                                                 namaMukim += ", "+rz.getString("nama_mukim");   
                                          }      
                                   }
                                   bilMukim++;   
                            
                            }else{
                                   namaMukim = "";
                            }
                            
                            if(totalMukim == bilMukim){
                                   nama2Mukim = namaMukim;
                            }else{
                                   nama2Mukim = "";
                            }
                     }
                     
                     
                     //////////////get lot////////////////////
                     sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
                     sql += " AND m.id_lot = lt.id_lot(+)"; 
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rl = stmt.executeQuery(sql);                        
                     int totalLOT = 0;

                     while (rl.next()){                              
                            totalLOT = rl.getInt("totalLOT");                                                                                           
                     }
                     
                     sql = "SELECT CASE ";
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
                     sql += " ELSE '' ";
                     sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
                     sql += " AND m.id_lot = lt.id_lot(+) "; 
                     sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
                     sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
                     
                     ResultSet rl2 = stmt.executeQuery(sql);  
                     
                     String LotPT = "";
                     String LotPTHM = "";
                     int bilLOT = 0;
                     
                     while (rl2.next()){                             
                            
                            if(totalLOT!=0){
                                   if(bilLOT==0){
                                          
                                          LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));   
                                          LotPT += LotPT = rl2.getString("NO_LOTPT");
                                          
                                   }else{
                                          
                                          if(totalLOT - bilLOT == 1){
                                                 LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
                                                 LotPT += " dan "+rl2.getString("NO_LOTPT");
                                          }else{
                                                 LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));   
                                                 LotPT += ", "+rl2.getString("NO_LOTPT");
                                          }      
                                   }
                                   bilLOT++;     
                            
                            }else{
                                   LotPT = "";
                                   LotPTHM = "";
                            }
                            
                            if(totalLOT == bilLOT){
                                   listLOT = LotPT;
                                   listLOTHM = LotPTHM;
                            }else{
                                   listLOT = "";
                                   listLOTHM = "";
                            }
                     }
                     
                     sql = "SELECT * ";
                     sql +=               "  "; 
                     sql += " from ( ";
                                   
//                      sql += "SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC,LPAD (m.no_lot, 20) ASC,LPAD (m.no_pt, 20) ASC,LPAD (m.no_subjaket, 20) ASC) AS rn," +
//                             "m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
//                     
//                     sql += "COUNT(DISTINCT CASE WHEN hmpb.id_jenispb NOT IN (40, 41, 42) THEN hmpb.id_hakmilikpb END) AS totalpb, ";
//                     //sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
//                     
//                     sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
//                     sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
//                     sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
//                     sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
//                     sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
//                     sql += " CASE ";
//                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
//                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
//                     sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
//                     sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
//                     sql += " ELSE 'TIADA' ";
//                     sql += " END AS NO_LOTPT, m.seksyen ";  
//                     sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
//                     sql += " Tblrujdaerah d, Users u ";
//                     sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
//                     sql += " AND m.id_negeri = n.id_negeri "; 
//                     sql += " AND p.id_fail = f.id_fail "; 
//                     sql += " AND p.id_masuk = u.user_id "; 
//                     sql += " AND m.id_daerah = d.id_daerah(+)";
//                     sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
//                     /*sql += " AND m.id_pegawai = u.user_id(+)";*/
//                     sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
//                     sql += " AND m.id_lot = lt.id_lot(+) ";
//                     sql += " AND m.id_mukim = mk.id_mukim(+) ";  
//                     sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
//                     sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
               //################   
                     
                     sql += "SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC," +
                     		"LPAD (m.no_lot, 20) ASC,LPAD (m.no_pt, 20) ASC,LPAD (m.no_subjaket, 20) ASC) AS rn," +
                     		"m.flag_segera_sebahagian, p.no_rujukan_ptg,p.id_status, f.no_fail, m.catatan," +
                     		"p.id_permohonan,ls.keterangan AS unit1, lt.keterangan AS unit2," +
                     		"m.id_hakmilik, m.id_negeri, n.nama_negeri," +
                     		"COUNT (DISTINCT CASE WHEN hmpb.id_jenispb NOT IN (40, 41, 42) THEN hmpb.id_hakmilikpb END) AS totalpb," +
                     		"m.id_jenishakmilik, m.id_daerah, m.id_mukim," +
                     		"mk.nama_mukim, m.id_unitluaslot, m.luas_ambil," +
                     		"m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt," +
                     		"m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput," +
                     		"jh.kod_jenis_hakmilik, m.lokasi, m.syarat_nyata," +
                     		"m.syarat_khas, m.sekatan_hak, m.sekatan_kepentingan," +
                     		"m.no_syit, jh.keterangan AS jenis_hakmilik," +
                     		"m.id_kategoritanah, m.flag_ambil_segera," +
                     		"d.nama_daerah, m.flag_borangl, m.status_borangl," +
                     		"m.tarikh_terima_hm, u.user_name AS nama_pegawai," +
                     		"jh.status_hakmilik, m.flag_jenis_rizab," +
                     		"m.nama_lain_rizab, m.no_warta_rizab," +
                     		"m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai," +
                     		"m.id_unitluasambil_convert,m.id_unitluaslot_convert," +
                     		"CASE WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL " +
                     		"THEN m.no_lot WHEN m.no_lot IS NULL AND m.no_pt IS NULL " +
                     		"THEN lt.keterangan || m.no_pt " +
                     		"WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL " +
                     		"THEN lt.keterangan || m.no_pt " +
                     		"WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL " +
                     		"THEN lt.keterangan || m.no_pt || CHR (32) || CHR (40) || m.no_lot || CHR (41) " +
                     		"ELSE 'TIADA' END AS no_lotpt, m.seksyen " +
                     		"FROM tblpfdfail f,tblpptpermohonan p,tblrujlot lt,tblrujluas ls,tblrujmukim mk," +
                     		"tblrujnegeri n,tblppthakmilik m,tblrujjenishakmilik jh,tblrujdaerah d,users u,tblppthakmilikpb hmpb " +
                     		"WHERE m.id_hakmilik = hmpb.id_hakmilik(+) " +
                     		"AND m.id_permohonan = p.id_permohonan(+) " +
                     		"AND m.id_negeri = n.id_negeri " +
                     		"AND p.id_fail = f.id_fail " +
                     		"AND p.id_masuk = u.user_id(+) " +
                     		"AND m.id_daerah = d.id_daerah(+) " +
                     		"AND ls.id_luas(+) = m.id_unitluaslot " +
                     		"AND m.id_jenishakmilik = jh.id_jenishakmilik(+) " +
                     		"AND m.id_lot = lt.id_lot(+) " +
                     		"AND m.id_mukim = mk.id_mukim(+) " +
                     		"AND NVL (m.flag_pembatalan_keseluruhan, 0) <> 'Y' " +
                     		"AND NVL (m.flag_penarikan_keseluruhan, 0) <> 'Y' " +
                     		"AND p.id_Permohonan = '"+idPermohonan+"'";
 
                     //NO LOT / NAMA PB
                     if (lot != "" && lot != null) {
                            if (!noLOT.equals("")) {
                                   sql += "AND (M.ID_HAKMILIK IN (SELECT M1.ID_HAKMILIK FROM TBLPPTHAKMILIK M1, TBLPPTHAKMILIKPB HPB1, TBLPPTPIHAKBERKEPENTINGAN PB1 "+
                               " WHERE M1.ID_HAKMILIK = HPB1.ID_HAKMILIK "+
                               " AND HPB1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
                               " AND M1.ID_HAKMILIK = M.ID_HAKMILIK "+ 
                             /* " AND upper(PB1.NAMA_PB) LIKE ('%"+noLOT.toUpperCase()+"%')" +*/
                               ")) ";
                            }
                     }//close if nolot
                     
                     //ID PEGAWAI
                     if (idpegawai != "" && idpegawai != null) {
                                   sql += " AND m.id_pegawai = '"+idpegawai+"'";
                     }//close if idpegawai
                     
                     sql += "GROUP BY m.no_lot,m.no_pt, m.no_subjaket,m.flag_segera_sebahagian,p.no_rujukan_ptg," +
                     		"p.id_status,f.no_fail,m.catatan,p.id_permohonan,ls.keterangan,lt.keterangan,m.id_hakmilik," +
                     		"m.id_negeri,n.nama_negeri,m.id_jenishakmilik,m.id_daerah,m.id_mukim,mk.nama_mukim," +
                     		"m.id_unitluaslot,m.luas_ambil,m.no_hakmilik,m.no_lot,m.luas_lot,m.no_pt,m.tarikh_daftar," +
                     		"m.tarikh_luput,m.tempoh_luput,jh.kod_jenis_hakmilik,m.lokasi,m.syarat_nyata,m.syarat_khas," +
                     		"m.sekatan_hak,m.sekatan_kepentingan,m.no_syit,jh.keterangan,m.id_kategoritanah," +
                     		"m.flag_ambil_segera,d.nama_daerah,m.flag_borangl,m.status_borangl,m.tarikh_terima_hm,u.user_name," +
                     		"jh.status_hakmilik,m.flag_jenis_rizab,m.nama_lain_rizab,m.no_warta_rizab,m.tarikh_warta_rizab," +
                     		"m.no_subjaket,m.id_pegawai,m.id_unitluasambil_convert,m.id_unitluaslot_convert,m.seksyen ";
                     
                     sql += ")";
                     
                     sql += "WHERE UPPER (no_lotpt) LIKE ('%"+noLOT.toUpperCase()+"%')";
                     
                     //sql += " ORDER BY LPAD(m.no_subjaket,20) asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, mk.nama_mukim asc ";
                     //Susunan di ubah ke mukim,lot,subjaket
                     sql += "ORDER BY nama_mukim ASC," +
                     		"LPAD (no_lot, 20) ASC," +
                     		"LPAD (no_pt, 20) ASC," +
                     		"LPAD (no_lotpt, 20) ASC," +
                     		"LPAD (no_subjaket, 20) ASC";
                     
                     
                     
                    /*sql += ") "; */
                     
               /*     if(from!=null && to!=null)
                     {
                     sql += " where rn >= "+from+"  and  rn <= "+to+" ";
                     }*/
                     //QRSLOW
                     myLogger.info(" LIST CARIAN LOT ASAL : "+sql);
                     ResultSet rs = stmt.executeQuery(sql);   
                     Hashtable h;
                     int bil = 1;
                     
                     while (rs.next()){
                            h = new Hashtable();
                            h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
                            h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
                            h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
                            h.put("listLOT", listLOT.toUpperCase());
                            h.put("listLOTHM", listLOTHM.toUpperCase());
                            h.put("nama2Mukim", nama2Mukim.toUpperCase());
                            h.put("nama2MukimInit", nama2Mukim);
                            h.put("bil", bil);
                            h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
                            h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
                            h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
                            h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
                            h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
                            h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
                            h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
                            h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
                            h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
                            h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
                            h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
                            h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));               
                            h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
                            h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
                            h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
                            h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
                            h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
                            h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
                            h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
                            h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
                            h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
                            h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
                            h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
                            h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
                            h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
                            
                            h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
                            h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
                            h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
                            h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
                            
                            if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
                                   
                                   double luasAmbil = rs.getDouble("luas_ambil");
                                   String LA = Utils.formatLuasHM(luasAmbil);
                                   h.put("luas_ambil",LA);
                                                 
                            }else{
                                   h.put("luas_ambil","0");
                            }
                            
                            
                            //validation for PU
                            if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
                                   
                                   double luasAmbil = 0;
                                   if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
                                          luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
                                   }else{
                                          luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
                                   }
                                   
                                   double luasLot = 0;
                                   if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
                                          luasLot = rs.getDouble("luas_lot"); //meter persegi
                                   }else{
                                          luasLot = rs.getDouble("luas_lot") * 10000; //hektar
                                   }                                        
                                   
                                   double baki = luasLot - luasAmbil;
                                   
                                   if(baki>0){
                                          h.put("flagPU","yes");
                                   }else{
                                          h.put("flagPU","no");
                                   }
                     
                            }else{
                                   h.put("flagPU","no");
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
                                          
                                          if(rs.getString("luas_ambil") != null){
                                                 double luasAmbil = rs.getDouble("luas_ambil");
                                                 String LAH = Utils.formatLuasHM(luasAmbil);
                                                 h.put("nilaiTanah",LAH);
                                          }else{
                                                 h.put("nilaiTanah","0");
                                          }
                                          
                                   }else{                                   
                                          if(rs.getString("luas_ambil") != null){
                                                 double luasAmbil = rs.getDouble("luas_ambil") / 10000;
                                                 String LAM = Utils.formatLuasHM(luasAmbil);
                                                 h.put("nilaiTanah",LAM);
                                          }else{
                                                 h.put("nilaiTanah","0");
                                          }                                        
                                   }      
                                   
                            }else{
                                   h.put("nilaiTanah","0");
                            }
                            
                            
                            //get total luas ambil in hectar
                            if(rs.getString("luas_ambil") != null){  
                                   if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
                                          totalSize += rs.getDouble("luas_ambil");
                                   }else{
                                          totalSize += (rs.getDouble("luas_ambil") / 10000);
                                   }
                            }
                            // ADDED BY ELLY
                            //myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
                            h.put("luas_keseluruhan", totalSize);                                
                            
                            h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
                            h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
                            h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
                            
                            listMaklumatTanah.addElement(h);
                            bil++; 
                     }
               //return list;
        } catch (Exception re) {
        	log.error("Error: ", re);
        	throw re;
        	}finally {
               if(db != null) db.close();
        }
        }//close setDataListKertas
 

	
	
public void setListMaklumatTanahSeksyen8(String idPermohonan,String lot,String idpegawai) throws Exception{
		
		
		myLogger.info("*****************************");
		myLogger.info("idPermohonan :"+idPermohonan);
		myLogger.info("lot :"+lot);
		myLogger.info("idpegawai :"+idpegawai);
		
		
		listMaklumatTanah = new Vector();
		
		Db db = null;
		listMaklumatTanah.clear();
		String sql = "";
		String noLOT = lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;

				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rz = stmt.executeQuery(sql);	
				
				String namaMukim = "";
				int bilMukim = 0;
				
				while (rz.next()){					
						
					if(totalMukim!=0){
						if(bilMukim==0){
							
							namaMukim += namaMukim = rz.getString("nama_mukim");	
						
						}else{
							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;

				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl2 = stmt.executeQuery(sql);	
				//QRSLOW
				
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;
				
				while (rl2.next()){					
					
					if(totalLOT!=0){
						if(bilLOT==0){
							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");
							
						}else{
							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				
				
				sql = "SELECT * ";
				sql += 		"  "; 
				sql += " from ( ";
						
						sql += 		"SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC, LPAD (m.no_lot, 20) ASC, LPAD (m.no_pt, 20) ASC, LPAD (m.no_subjaket, 20) ASC) AS rn," +
								"m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				
				sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
				
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, ";
				
				if (idpegawai != "" && idpegawai != null) 
				{		
					sql +="u.user_name as nama_pegawai, ";
				}
				else
				{
					sql +="'' as nama_pegawai, ";
				}
				
				
				sql +="jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, " +
						" m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE 'TIADA' ";
				sql += " END AS NO_LOTPT, m.seksyen ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
						
				
				if (idpegawai != "" && idpegawai != null) 
				{				
				sql +=	" Users u,TBLPPTAGIHANHM AGHM, ";
				}
				
				sql += " Tblrujdaerah d ";
				
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) "; 
				
				if (idpegawai != "" && idpegawai != null) 
				{
				sql += " AND AGHM.ID_HAKMILIK = m.ID_HAKMILIK  "; 
						sql += " AND AGHM.USER_ID = u.USER_ID  "; 
								sql += " AND AGHM.BARIS = '2'  "; 
				}				
								
								//idpegawai
								
								
								
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				//sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
			//################	
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
				
				//ID PEGAWAI
				if (idpegawai != "" && idpegawai != null) {
						sql += " AND u.USER_ID = '"+idpegawai+"'";
				}//close if idpegawai
				
				
				//sql += " ORDER BY LPAD(m.no_subjaket,20) asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, mk.nama_mukim asc ";
				//Susunan di ubah ke mukim,lot,subjaket
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
				
				sql += ") ";
				
			/*	if(from!=null && to!=null)
				{
				sql += " where rn >= "+from+"  and  rn <= "+to+" ";
				}*/
				
	
				//QRSLOW
				myLogger.info(" cARIAN LOT asal : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
					h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
					h.put("listLOT", listLOT.toUpperCase());
					h.put("listLOTHM", listLOTHM.toUpperCase());
					h.put("nama2Mukim", nama2Mukim.toUpperCase());
					h.put("nama2MukimInit", nama2Mukim);
					h.put("bil", bil);
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
					h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
					
					h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
					h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
					h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
					h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					//validation for PU
					if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
						
						double luasAmbil = 0;
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
							luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
						}else{
							luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
						}
						
						double luasLot = 0;
						if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
							luasLot = rs.getDouble("luas_lot"); //meter persegi
						}else{
							luasLot = rs.getDouble("luas_lot") * 10000; //hektar
						}						
						
						double baki = luasLot - luasAmbil;
						
						if(baki>0){
							h.put("flagPU","yes");
						}else{
							h.put("flagPU","no");
						}
				
					}else{
						h.put("flagPU","no");
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
							
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil") / 10000;
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}	
						
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					//get total luas ambil in hectar
					if(rs.getString("luas_ambil") != null){	
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
							totalSize += rs.getDouble("luas_ambil");
						}else{
							totalSize += (rs.getDouble("luas_ambil") / 10000);
						}
					}
					// ADDED BY ELLY
					//myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
					h.put("luas_keseluruhan", totalSize);					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
					
					listMaklumatTanah.addElement(h);
					bil++;	
				}
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	


		Hashtable getListMukimLot = null;
		public Hashtable getListMukimLot(String idPermohonan,String lot,String idpegawai,Integer from,Integer to) throws Exception {
			getListMukimLot = new Hashtable();
			
			String nama2Mukim = "";
			String listLOT = "";
			String listLOTHM = "";
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;
				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rz = stmt.executeQuery(sql);					
				String namaMukim = "";
				int bilMukim = 0;				
				while (rz.next()){					
					if(totalMukim!=0){
						if(bilMukim==0){							
							namaMukim += namaMukim = rz.getString("nama_mukim");						
						}else{							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;
				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";				
				ResultSet rl2 = stmt.executeQuery(sql);					
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;				
				while (rl2.next()){				
					if(totalLOT!=0){
						if(bilLOT==0){							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");							
						}else{							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				

				
				/*	
				ResultSet rs = stmt.executeQuery(sql);
				
				Hashtable h;
				h = new Hashtable();
				while (rs.next()) {
					if (rs.getString("id_seksyen") == null) {
						h.put("id_seksyen_user", "");
					} else {
						h.put("id_seksyen_user", rs.getString("id_seksyen"));
					}				
				}*/
				
				//getListMukimLot = new Hashtable();
				getListMukimLot.put("listLOT", listLOT.toUpperCase());
				getListMukimLot.put("listLOTHM", listLOTHM.toUpperCase());
				getListMukimLot.put("nama2Mukim", nama2Mukim.toUpperCase());
				getListMukimLot.put("nama2MukimInit", nama2Mukim);
				
				return getListMukimLot;
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				} finally {
				if (db != null) db.close();
			}
		}




	public void setListMaklumatTanah_listHakmilik(String idPermohonan,String lot,String idpegawai,Integer from,Integer to) throws Exception{
	
	listMaklumatTanah = new Vector();
	
	Db db = null;
	listMaklumatTanah.clear();
	String sql = "";
	String noLOT = lot.trim();
	//String nama2Mukim = "";
	//String listLOT = "";
	//String listLOTHM = "";
	double totalSize = 0;
	
	try {
			db = new Db();
			Statement stmt = db.getStatement();
			/*
			sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
			sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
			
			ResultSet rx = stmt.executeQuery(sql);				
			int totalMukim = 0;

			while (rx.next()){					
				totalMukim = rx.getInt("totalMukim");														
			}
	
			sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
			sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
			
			ResultSet rz = stmt.executeQuery(sql);	
			
			String namaMukim = "";
			int bilMukim = 0;
			
			while (rz.next()){					
					
				if(totalMukim!=0){
					if(bilMukim==0){
						
						namaMukim += namaMukim = rz.getString("nama_mukim");	
					
					}else{
						
						if(totalMukim - bilMukim == 1){
							namaMukim += " dan "+rz.getString("nama_mukim");
						}else{
							namaMukim += ", "+rz.getString("nama_mukim");	
						}	
					}
					bilMukim++;	
				
				}else{
					namaMukim = "";
				}
				
				if(totalMukim == bilMukim){
					nama2Mukim = namaMukim;
				}else{
					nama2Mukim = "";
				}
			}
			
			
			//////////////get lot////////////////////
			sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
			sql += " AND m.id_lot = lt.id_lot(+)"; 
			sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
			
			ResultSet rl = stmt.executeQuery(sql);				
			int totalLOT = 0;

			while (rl.next()){					
				totalLOT = rl.getInt("totalLOT");														
			}
			
			sql = "SELECT CASE ";
			sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
			sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
			sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
			sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
			sql += " ELSE '' ";
			sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
			sql += " AND m.id_lot = lt.id_lot(+) "; 
			sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
			sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
			
			ResultSet rl2 = stmt.executeQuery(sql);	
			
			String LotPT = "";
			String LotPTHM = "";
			int bilLOT = 0;
			
			while (rl2.next()){					
				
				if(totalLOT!=0){
					if(bilLOT==0){
						
						LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
						LotPT += LotPT = rl2.getString("NO_LOTPT");
						
					}else{
						
						if(totalLOT - bilLOT == 1){
							LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
							LotPT += " dan "+rl2.getString("NO_LOTPT");
						}else{
							LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += ", "+rl2.getString("NO_LOTPT");
						}	
					}
					bilLOT++;	
				
				}else{
					LotPT = "";
					LotPTHM = "";
				}
				
				if(totalLOT == bilLOT){
					listLOT = LotPT;
					listLOTHM = LotPTHM;
				}else{
					listLOT = "";
					listLOTHM = "";
				}
			}
			*/
			sql = "SELECT * ";
			sql += 		"  "; 
			sql += " from ( ";
					
					sql += 		"SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC, LPAD (m.no_lot, 20) ASC, LPAD (m.no_pt, 20) ASC, LPAD (m.no_subjaket, 20) ASC) AS rn," +
							"m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
			
			sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
			sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
			
			sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
			sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
			sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
			sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
			sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
			sql += " CASE ";
			sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
			sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
			sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
			sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
			sql += " ELSE 'TIADA' ";
			sql += " END AS NO_LOTPT, m.seksyen ";  
			sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
			sql += " Tblrujdaerah d, Users u";
			sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
			sql += " AND m.id_negeri = n.id_negeri "; 
			sql += " AND p.id_fail = f.id_fail "; 
			sql += " AND m.id_daerah = d.id_daerah(+)";
			sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
			sql += " AND m.id_pegawai = u.user_id(+)";
			sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
			sql += " AND m.id_lot = lt.id_lot(+) ";
			sql += " AND m.id_mukim = mk.id_mukim(+) ";  
			sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
			sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
		//################	
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
			
			//ID PEGAWAI
			if (idpegawai != "" && idpegawai != null) {
					sql += " AND m.id_pegawai = '"+idpegawai+"'";
			}//close if idpegawai
			
			
			//sql += " ORDER BY LPAD(m.no_subjaket,20) asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, mk.nama_mukim asc ";
			//Susunan di ubah ke mukim,lot,subjaket
			sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
			
			
			
			sql += ") ";
			
			if(from!=null && to!=null)
			{
			sql += " where rn >= "+from+"  and  rn <= "+to+" order by rownum asc";
			}
			
			
			
			myLogger.info(" cARIAN LOT : "+sql);
			ResultSet rs = stmt.executeQuery(sql);	
			Hashtable h;
			int bil = 1;				
			while (rs.next()){
				h = new Hashtable();
				h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
				h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				/*
				h.put("listLOT", listLOT.toUpperCase());
				h.put("listLOTHM", listLOTHM.toUpperCase());
				h.put("nama2Mukim", nama2Mukim.toUpperCase());
				h.put("nama2MukimInit", nama2Mukim);
				*/
				h.put("bil", bil);
				h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
				h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
				h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
				h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
				h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
				h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
				h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
				h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
				h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
				h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
				h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
				h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
				h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
				h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
				h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
				h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
				h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
				h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
				
				h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
				h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
				h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
				h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
				
				if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
					
					double luasAmbil = rs.getDouble("luas_ambil");
					String LA = Utils.formatLuasHM(luasAmbil);
					h.put("luas_ambil",LA);
							
				}else{
					h.put("luas_ambil","0");
				}
				
				
				//validation for PU
				if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
					
					double luasAmbil = 0;
					if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
						luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
					}else{
						luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
					}
					
					double luasLot = 0;
					if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
						luasLot = rs.getDouble("luas_lot"); //meter persegi
					}else{
						luasLot = rs.getDouble("luas_lot") * 10000; //hektar
					}						
					
					double baki = luasLot - luasAmbil;
					
					if(baki>0){
						h.put("flagPU","yes");
					}else{
						h.put("flagPU","no");
					}
			
				}else{
					h.put("flagPU","no");
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
						
						if(rs.getString("luas_ambil") != null){
							double luasAmbil = rs.getDouble("luas_ambil");
							String LAH = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah",LAH);
						}else{
							h.put("nilaiTanah","0");
						}
						
					}else{						
						if(rs.getString("luas_ambil") != null){
							double luasAmbil = rs.getDouble("luas_ambil") / 10000;
							String LAM = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah",LAM);
						}else{
							h.put("nilaiTanah","0");
						}						
					}	
					
				}else{
					h.put("nilaiTanah","0");
				}
				
				
				//get total luas ambil in hectar
				if(rs.getString("luas_ambil") != null){	
					if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
						totalSize += rs.getDouble("luas_ambil");
					}else{
						totalSize += (rs.getDouble("luas_ambil") / 10000);
					}
				}
				// ADDED BY ELLY
				//myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
				h.put("luas_keseluruhan", totalSize);					
				
				h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
				h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
				
				listMaklumatTanah.addElement(h);
				bil++;	
			}
		//return list;
	} catch (Exception re) {
		log.error("Error: ", re);
		throw re;
		}finally {
		if(db != null) db.close();
	}
	}//close setDataListKertas

	
	
	public int setListMaklumatTanah_count(String idPermohonan,String lot,String id_pegawai) throws Exception{
		
		Db db = null;
		String sql = "";
		
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql += " SELECT count(*) as total_hakmilik ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 

				if (!id_pegawai.equals("") && id_pegawai != null) 
				{				
				sql +=	" TBLPPTAGIHANHM AGHM, Users u, ";
				}
				
				sql += " Tblrujdaerah d";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				
				
				if (!id_pegawai.equals("") && id_pegawai != null) 
				{
				sql += " AND AGHM.ID_HAKMILIK = m.ID_HAKMILIK  "; 
						sql += " AND AGHM.USER_ID = u.USER_ID  "; 
								sql += " AND AGHM.BARIS = '2'  "; 
				}
				if (!id_pegawai.equals("") && id_pegawai != null) {
					sql += " AND u.USER_ID  = '"+id_pegawai+"'";
				}
				
				
				//sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
				
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				
				
				myLogger.info(" cARIAN LOT : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int total_hakmilik = 0;				
				while (rs.next()){
					total_hakmilik = rs.getString("total_hakmilik")==null?0:rs.getInt("total_hakmilik");
					
				}
			return total_hakmilik;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	
public boolean cekStatusFailDahWujud(String idPermohonan,String id_status,String id_suburusan) throws Exception{
		
		Db db = null;
		String sql = "";
		boolean setCekStatusFailDahWujud = false;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql += " SELECT COUNT(DISTINCT SSS.ID_STATUS) AS TOT FROM TBLRUJSUBURUSANSTATUSFAILPPT SFP, TBLRUJSUBURUSANSTATUS SSS, TBLRUJSUBURUSAN SS, TBLPPTPERMOHONAN P "+
						" WHERE SFP.ID_SUBURUSANSTATUS = SSS.ID_SUBURUSANSTATUS AND SSS.ID_SUBURUSAN = SS.ID_SUBURUSAN "+
						" AND SS.ID_SEKSYEN = '1' AND SS.ID_SUBURUSAN = '"+id_suburusan+"' AND SFP.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = "+idPermohonan+" "+
						" AND SSS.ID_STATUS = '"+id_status+"' ";				
				myLogger.info(" setCekStatusFailDahWujud: "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int total_hakmilik = 0;				
				while (rs.next()){
					total_hakmilik = rs.getString("TOT")==null?0:rs.getInt("TOT");					
				}
				
				if(total_hakmilik > 0 )
				{
					setCekStatusFailDahWujud = true;
				}
				
			return setCekStatusFailDahWujud;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
		

	
	public void setListMaklumatTanah_yg_asal(String idPermohonan,String lot,String idpegawai,Integer from,Integer to) throws Exception{
		
		listMaklumatTanah = new Vector();
		
		Db db = null;
		listMaklumatTanah.clear();
		String sql = "";
		String noLOT = lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;

				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT initcap(nama_mukim)as nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rz = stmt.executeQuery(sql);	
				
				String namaMukim = "";
				int bilMukim = 0;
				
				while (rz.next()){					
						
					if(totalMukim!=0){
						if(bilMukim==0){
							
							namaMukim += namaMukim = rz.getString("nama_mukim");	
						
						}else{
							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;

				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl2 = stmt.executeQuery(sql);	
				
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;
				
				while (rl2.next()){					
					
					if(totalLOT!=0){
						if(bilLOT==0){
							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");
							
						}else{
							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				
				sql = "SELECT * ";
				sql += 		"  "; 
				sql += " from ( ";
						
						sql += 		"SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY mk.nama_mukim ASC, LPAD (m.no_lot, 20) ASC, LPAD (m.no_pt, 20) ASC, LPAD (m.no_subjaket, 20) ASC) AS rn," +
								"m.flag_segera_sebahagian, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				
				sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
				
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE 'TIADA' ";
				sql += " END AS NO_LOTPT, m.seksyen ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d, Users u";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
				sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
			//################	
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
				
				//ID PEGAWAI
				if (idpegawai != "" && idpegawai != null) {
						sql += " AND m.id_pegawai = '"+idpegawai+"'";
				}//close if idpegawai
				
				
				//sql += " ORDER BY LPAD(m.no_subjaket,20) asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, mk.nama_mukim asc ";
				//Susunan di ubah ke mukim,lot,subjaket
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
				
				
				
				sql += ") ";
				
				if(from!=null && to!=null)
				{
				sql += " where rn >= "+from+"  and  rn <= "+to+" order by rownum asc";
				}
				
				
				
				myLogger.info(" cARIAN LOT : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;				
				while (rs.next()){
					h = new Hashtable();
					h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
					h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
					h.put("listLOT", listLOT.toUpperCase());
					h.put("listLOTHM", listLOTHM.toUpperCase());
					h.put("nama2Mukim", nama2Mukim.toUpperCase());
					h.put("nama2MukimInit", nama2Mukim);
					h.put("bil", bil);
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
					h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
					
					h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
					h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
					h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
					h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					//validation for PU
					if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
						
						double luasAmbil = 0;
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
							luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
						}else{
							luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
						}
						
						double luasLot = 0;
						if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
							luasLot = rs.getDouble("luas_lot"); //meter persegi
						}else{
							luasLot = rs.getDouble("luas_lot") * 10000; //hektar
						}						
						
						double baki = luasLot - luasAmbil;
						
						if(baki>0){
							h.put("flagPU","yes");
						}else{
							h.put("flagPU","no");
						}
				
					}else{
						h.put("flagPU","no");
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
							
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil") / 10000;
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}	
						
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					//get total luas ambil in hectar
					if(rs.getString("luas_ambil") != null){	
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
							totalSize += rs.getDouble("luas_ambil");
						}else{
							totalSize += (rs.getDouble("luas_ambil") / 10000);
						}
					}
					// ADDED BY ELLY
					//myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
					h.put("luas_keseluruhan", totalSize);					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
					
					listMaklumatTanah.addElement(h);
					bil++;	
				}
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	
	@SuppressWarnings("unchecked")
	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		maklumatTanah.clear();
		String sql = "";
	
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT hk.flag_segera_sebahagian, f.id_fail,f.id_suburusan, jhk.kod_jenis_hakmilik, hk.id_hakmilik, ls.id_luas as id_unitluaslot, lam.id_luas as id_unitluasambil, p.id_permohonan, jhk.id_jenishakmilik, n.id_negeri, ";
			
			sql += " (select count(a.id_borangh) ";
			sql += " from tblpptborangh a, tblpptborangg b, tblppthakmilikpb c";
			sql += " where a.id_borangg = b.id_borangg ";
			sql += " and a.id_hakmilikpb = c.id_hakmilikpb ";
			sql += " and c.id_hakmilik = hk.id_hakmilik) as flag_status_borang_h, ";
			
			sql += " hk.id_lot, d.id_daerah, mk.id_mukim, hk.luas_ambil, hk.no_hakmilik, hk.no_lot, ";
			sql += " hk.luas_lot, hk.no_pt, n.nama_negeri, d.nama_daerah, ls.keterangan, mk.nama_mukim, jhk.keterangan as jenis_hakmilik, hk.catatan, hk.seksyen, ";
			sql += " hk.tarikh_daftar, hk.tarikh_luput, hk.tempoh_luput, ";
			sql += " hk.lokasi,hk.syarat_nyata,hk.syarat_khas,hk.sekatan_hak,hk.sekatan_kepentingan,hk.no_syit,hk.id_kategoritanah, ";
			sql += " mk.nama_mukim, d.nama_daerah, n.nama_negeri, kt.keterangan as kategori_tanah, hk.id_daerahpenggawa, ";
			sql += " hk.status_borangl, hk.tarikh_terima_hm, lt.keterangan as unitQT, ";
			sql += " hk.flag_jenis_rizab, hk.nama_lain_rizab, hk.no_warta_rizab, hk.tarikh_warta_rizab, hk.id_pegawai, ";
			sql += " hk.id_unitluaslot_convert, hk.id_unitluasambil_convert, hk.nama_luas_asal, hk.nama_luas_ambil, ";
			
			sql += " hk.no_bangunan, hk.no_tingkat, hk.no_petak, "; //PPT-03 Penambahan Strata
			
			sql += " CASE ";
			sql += " WHEN hk.no_lot IS NOT NULL AND hk.no_pt IS NULL THEN hk.no_lot "; 
			sql += " WHEN hk.no_lot IS NULL AND hk.no_pt IS NULL THEN lt.keterangan || hk.no_pt ";
			sql += " WHEN hk.no_lot IS NULL AND hk.no_pt IS NOT NULL THEN  lt.keterangan || hk.no_pt "; 
			sql += " WHEN hk.no_lot IS NOT NULL AND hk.no_pt IS NOT NULL THEN lt.keterangan || hk.no_pt || CHR(32) || CHR(40) || hk.no_lot || CHR(41) ";
			sql += " ELSE '' ";
			sql += " END AS NO_LOTPT ";
			
			sql += " FROM Tblpfdfail f,Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujdaerah d, ";
			sql += " Tblrujnegeri n, Tblpptpermohonan p, Tblrujkategori kt, Tblrujluas lam, Tblrujlot lt ";
			sql += " WHERE n.id_negeri(+) = hk.id_negeri ";
			sql += " AND d.id_daerah(+) = p.id_daerah ";
			sql += " AND p.id_fail = f.id_fail(+)";
			sql += " AND ls.id_luas(+) = hk.id_unitluaslot ";
			sql += " AND lam.id_luas(+) = hk.id_unitluasambil ";
			sql += " AND mk.id_mukim(+) = hk.id_mukim ";
			sql += " AND kt.id_kategori(+) = hk.id_kategoritanah ";
			sql += " AND hk.id_lot = lt.id_lot(+)";
			sql += " AND jhk.id_jenishakmilik(+) = hk.id_jenishakmilik ";
			sql += " AND p.id_permohonan = hk.id_permohonan ";
			sql += " AND hk.id_hakmilik = '"+idHakmilik+"'";
			myLogger.info("GET MAKLUMAT HAKMILIK :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			
			while(rs.next()) {
			h = new Hashtable();
			h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
			h.put("flag_status_borang_h", rs.getInt("flag_status_borang_h")== 0?0:rs.getInt("flag_status_borang_h"));
			h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
			h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
			h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
			h.put("unitQT", rs.getString("unitQT")==null?"":rs.getString("unitQT"));
			h.put("id_daerahpenggawa", rs.getString("id_daerahpenggawa")==null?"":rs.getString("id_daerahpenggawa"));
			h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
			h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
			h.put("id_luasLot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
			h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
			h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
			h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
			h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
			h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
			
			h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
			h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
			h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
			h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
			
			h.put("id_unitluaslot_convert", rs.getString("id_unitluaslot_convert")==null?"":rs.getString("id_unitluaslot_convert"));
			h.put("id_unitluasambil_convert", rs.getString("id_unitluasambil_convert")==null?"":rs.getString("id_unitluasambil_convert"));
			h.put("nama_luas_asal", rs.getString("nama_luas_asal")==null?"":rs.getString("nama_luas_asal"));
			h.put("nama_luas_ambil", rs.getString("nama_luas_ambil")==null?"":rs.getString("nama_luas_ambil"));
			
			h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
			
			
			if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
				
				double luasAmbil = rs.getDouble("luas_ambil");
				String LA = Utils.formatLuasHM(luasAmbil);
				h.put("luas_ambil",LA);
						
			}else{
				h.put("luas_ambil","");
			}
			
			if(rs.getString("luas_lot") != null && rs.getString("luas_lot") != ""){
				
				double LL = rs.getDouble("luas_lot");
				String luasLot = Utils.formatLuasHM(LL);
				h.put("luas_lot",luasLot);
						
			}else{
				h.put("luas_lot","");
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
			
			h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
			h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
			h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));			
			h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
			h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
			h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));			
			h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
			h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
			h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));			
			h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
			h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
			h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));			
			h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
			
			h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
			h.put("kategori_tanah", rs.getString("kategori_tanah")==null?"":rs.getString("kategori_tanah"));
			
			//PPT-03 Penambahan Strata
			h.put("no_bangunan", rs.getString("no_bangunan")==null?"":rs.getString("no_bangunan"));
			h.put("no_tingkat", rs.getString("no_tingkat")==null?"":rs.getString("no_tingkat"));
			h.put("no_petak", rs.getString("no_petak")==null?"":rs.getString("no_petak"));

			maklumatTanah.addElement(h);
		}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setlistpohon
	
	
	@SuppressWarnings("unchecked")
	public Hashtable salin_maklumat_tanah(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
	
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT hk.flag_segera_sebahagian, f.id_fail,f.id_suburusan, jhk.kod_jenis_hakmilik, hk.id_hakmilik, ls.id_luas as id_unitluaslot, lam.id_luas as id_unitluasambil, p.id_permohonan, jhk.id_jenishakmilik, n.id_negeri, "; 
			
			sql += " (select count(a.id_borangh) ";
			sql += " from tblpptborangh a, tblpptborangg b, tblppthakmilikpb c";
			sql += " where a.id_borangg = b.id_borangg ";
			sql += " and a.id_hakmilikpb = c.id_hakmilikpb ";
			sql += " and c.id_hakmilik = hk.id_hakmilik) as flag_status_borang_h, ";
			
			sql += " hk.id_lot, d.id_daerah, mk.id_mukim, hk.luas_ambil, hk.no_hakmilik, hk.no_lot, ";
			sql += " hk.luas_lot, hk.no_pt, n.nama_negeri, d.nama_daerah, ls.keterangan, mk.nama_mukim, jhk.keterangan as jenis_hakmilik, hk.catatan, hk.seksyen, ";  
			sql += " hk.tarikh_daftar, hk.tarikh_luput, hk.tempoh_luput, ";
			sql += " hk.lokasi,hk.syarat_nyata,hk.syarat_khas,hk.sekatan_hak,hk.sekatan_kepentingan,hk.no_syit,hk.id_kategoritanah, ";
			sql += " mk.nama_mukim, d.nama_daerah, n.nama_negeri, kt.keterangan as kategori_tanah, hk.id_daerahpenggawa, ";
			sql += " hk.status_borangl, hk.tarikh_terima_hm, lt.keterangan as unitQT, ";
			sql += " hk.flag_jenis_rizab, hk.nama_lain_rizab, hk.no_warta_rizab, hk.tarikh_warta_rizab, hk.id_pegawai, ";
			sql += " hk.id_unitluaslot_convert, hk.id_unitluasambil_convert, hk.nama_luas_asal, hk.nama_luas_ambil, ";
			
			sql += " CASE ";
			sql += " WHEN hk.no_lot IS NOT NULL AND hk.no_pt IS NULL THEN hk.no_lot "; 
			sql += " WHEN hk.no_lot IS NULL AND hk.no_pt IS NULL THEN lt.keterangan || hk.no_pt ";
			sql += " WHEN hk.no_lot IS NULL AND hk.no_pt IS NOT NULL THEN  lt.keterangan || hk.no_pt "; 
			sql += " WHEN hk.no_lot IS NOT NULL AND hk.no_pt IS NOT NULL THEN lt.keterangan || hk.no_pt || CHR(32) || CHR(40) || hk.no_lot || CHR(41) ";
			sql += " ELSE '' ";
			sql += " END AS NO_LOTPT ";  
			
			sql += " FROM Tblpfdfail f,Tblppthakmilik hk, Tblrujjenishakmilik jhk, Tblrujmukim mk, Tblrujluas ls, Tblrujdaerah d, "; 
			sql += " Tblrujnegeri n, Tblpptpermohonan p, Tblrujkategori kt, Tblrujluas lam, Tblrujlot lt ";
			sql += " WHERE n.id_negeri(+) = hk.id_negeri "; 
			sql += " AND d.id_daerah(+) = p.id_daerah "; 
			sql += " AND p.id_fail = f.id_fail(+)";
			sql += " AND ls.id_luas(+) = hk.id_unitluaslot ";  
			sql += " AND lam.id_luas(+) = hk.id_unitluasambil ";  
			sql += " AND mk.id_mukim(+) = hk.id_mukim "; 
			sql += " AND kt.id_kategori(+) = hk.id_kategoritanah "; 
			sql += " AND hk.id_lot = lt.id_lot(+)";
			sql += " AND jhk.id_jenishakmilik(+) = hk.id_jenishakmilik ";  
			sql += " AND p.id_permohonan = hk.id_permohonan ";  
			sql += " AND hk.id_hakmilik = '"+idHakmilik+"'";

			ResultSet rs = stmt.executeQuery(sql);
	
			Hashtable h;
			h = new Hashtable();
			while(rs.next()) {
			
			h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
			h.put("flag_status_borang_h", rs.getInt("flag_status_borang_h")== 0?0:rs.getInt("flag_status_borang_h"));
			h.put("id_suburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
			h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
			h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
			h.put("unitQT", rs.getString("unitQT")==null?"":rs.getString("unitQT"));
			h.put("id_daerahpenggawa", rs.getString("id_daerahpenggawa")==null?"":rs.getString("id_daerahpenggawa"));
			h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
			h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
			h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
			h.put("id_luasLot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
			h.put("id_unitluasambil", rs.getString("id_unitluasambil")==null?"":rs.getString("id_unitluasambil"));
			h.put("id_lot", rs.getString("id_lot")==null?"":rs.getString("id_lot"));
			h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
			h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
			h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
			h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
			h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
			h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
			
			h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
			h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
			h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
			h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
			
			h.put("id_unitluaslot_convert", rs.getString("id_unitluaslot_convert")==null?"":rs.getString("id_unitluaslot_convert"));
			h.put("id_unitluasambil_convert", rs.getString("id_unitluasambil_convert")==null?"":rs.getString("id_unitluasambil_convert"));
			h.put("nama_luas_asal", rs.getString("nama_luas_asal")==null?"":rs.getString("nama_luas_asal"));
			h.put("nama_luas_ambil", rs.getString("nama_luas_ambil")==null?"":rs.getString("nama_luas_ambil"));
			
			h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
			
			
			if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
				
				double luasAmbil = rs.getDouble("luas_ambil");
				String LA = Utils.formatLuasHM(luasAmbil);
				h.put("luas_ambil",LA);
						
			}else{
				h.put("luas_ambil","");
			}
			
			if(rs.getString("luas_lot") != null && rs.getString("luas_lot") != ""){
				
				double LL = rs.getDouble("luas_lot");
				String luasLot = Utils.formatLuasHM(LL);
				h.put("luas_lot",luasLot);
						
			}else{
				h.put("luas_lot","");
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
			
			h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
			h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
			h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
			h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
			h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
			h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
			h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
			h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
			h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
			h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
			h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));			
			h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
			h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
			h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
			h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
			
			h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
			h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
			h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
			h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
			h.put("kategori_tanah", rs.getString("kategori_tanah")==null?"":rs.getString("kategori_tanah"));
			
			//maklumatTanah.addElement(h);
		}
			
			return h;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		
		
		finally {
			if(db != null) db.close();
		}
		
	}//close setlistpohon
	
	//sahkan (status change to "127")
	@SuppressWarnings("unchecked")
	public static void sahkan(Hashtable data,String id_suburusanstatusfailppt,String newIdSuburusanStatus,String flagJenisKodDaerah) throws Exception {
		
		myLogger.info("DATA ---- "+data);
		Connection conn = null;
	    Db db = null;
	    String sql = "";
	   
	    try{
	   	
	    		db = new Db();
	    		conn = db.getConnection();
				conn.setAutoCommit(false);
	    		Statement stmt = db.getStatement();
    		
	    		String idpermohonan = (String)data.get("id_permohonan");
	    		String id_user = (String)data.get("id_user");
	    		String id_negeriuser = (String)data.get("id_negeriuser");
	      
	    		String id_fail = (String)data.get("id_fail");
			    //String id_negeri = (String)data.get("id_negeri");
			    String id_kementerian = (String)data.get("id_kementerian");
			  
			    String id_projekNegeri = (String)data.get("id_projekNegeri");
			    String idDaerah = (String)data.get("idDaerah");
				String id_sahkan = (String)data.get("id_user");	
			    
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", idpermohonan);
	    		r.add("id_status", "127");	
	    		r.add("id_sahkan", id_sahkan);	
		 		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		 		r.add("id_kemaskini",id_user);
		 		
		 		sql = r.getSQLUpdate("tblpptpermohonan");
		 		stmt.executeUpdate(sql);
	      
		 		
		 		//-- Generate no Fail "JKPTG(S).NEGERI/KOD DAERAH/881/kod_kementerian/TAHUN-seq_fail
		 		String KodFailJabatan = "JKPTG(S).";
		 		//String KodFailJabatanNS = "JKPTG.";
		 		//String SulitNS = "(S)";
		 		String kod_kementerian = "";
		 		//String kodMampu = "";
		 		String abbrev = "";
		 		String kodDaerah = "";
		 		int seq_id_seksyen = 1;
		 		int seq_id_urusan = 17;
	      
		 		SQLRenderer rMN = new SQLRenderer();				 
		 		rMN.add("id_negeri");
		 		rMN.add("ABBREV");				      
		 		rMN.add("id_negeri",id_projekNegeri);				      
		 		sql = rMN.getSQLSelect("Tblrujnegeri");
		 		ResultSet rsMN = stmt.executeQuery(sql);
		 		while (rsMN.next()) {
		 			abbrev = rsMN.getString("ABBREV");
		 		}
	      
		 		SQLRenderer rMKD = new SQLRenderer();				 
		 		rMKD.add("id_daerah");
		 		rMKD.add("kod_daerah");	
		 		rMKD.add("kod_daerah_ptg");	
		 		rMKD.add("id_daerah",idDaerah);				      
		 		sql = rMKD.getSQLSelect("Tblrujdaerah");
		 		ResultSet rsMKD = stmt.executeQuery(sql);
		 		while (rsMKD.next()) {
		 			if(flagJenisKodDaerah.equals("1")){
		 				kodDaerah = rsMKD.getString("kod_daerah_ptg")==null?rsMKD.getString("kod_daerah"):rsMKD.getString("kod_daerah_ptg");
		 			}else{
		 				kodDaerah = rsMKD.getString("kod_daerah");
		 			}
		 		}
		 		
		 		SQLRenderer rMT = new SQLRenderer();				 
		 		rMT.add("id_kementerian");
		 		rMT.add("kod_kementerian");				      
		 		rMT.add("id_kementerian",id_kementerian);				      
		 		sql = rMT.getSQLSelect("Tblrujkementerian");
		 		ResultSet rsMT = stmt.executeQuery(sql);
		 		while (rsMT.next()) {
		 			kod_kementerian = rsMT.getString("kod_kementerian");
		 		}
	      
//		 		SQLRenderer rnegeri = new SQLRenderer();				 
//		 		rnegeri.add("id_negeri");
//		 		rnegeri.add("kod_mampu");				      
//		 		rnegeri.add("id_negeri",id_negeri);				      
//		 		sql = rnegeri.getSQLSelect("Tblrujnegeri");
//		 		ResultSet rsnegeri = stmt.executeQuery(sql);
//		 		while (rsnegeri.next()) {
//		 			kodMampu = rsnegeri.getString("kod_mampu");
//		 		}
	
		 		//tahun
		 		Date now = new Date();
		    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
		    	String tahun = formatter.format(now);
		 		
		 		//no fail
		 		//String seq = String.format("%06d",File.getSeqNo(seq_id_seksyen,seq_id_urusan));	
		    	int seq = File.getSeqNoByYearPPT(seq_id_seksyen,seq_id_urusan,0,Integer.parseInt(id_negeriuser),Integer.parseInt(tahun));	
		 		//String noFail = KodFailJabatan+"/"+kod_kementerian+"/"+kodMampu+"-"+seq;
		    	
		    	//-- Generate no Fail "JKPTG(S).NEGERI/KOD DAERAH/881/kod_kementerian/TAHUN-seq_fail
		    	//-- Untuk N.Sembilan "JKPTG.NS(S)/UPT/KOD DAERAH/KOD KEMENTERIAN/TAHUN/SEQ-FAIL  "
		    	
		 		String noFail = KodFailJabatan+abbrev+"/"+kodDaerah+"/881/"+kod_kementerian+"/"+tahun+"/"+seq;
		 		
		 		//String noFailNS = KodFailJabatanNS+abbrev+SulitNS+"/UPT/"+kodDaerah+"/"+kod_kementerian+"/"+tahun+"/"+seq;
		 		
		 		//masukkan nofail(generated) ke pfdfail
		 		SQLRenderer rF = new SQLRenderer();
		 		rF.update("id_fail", id_fail);
		 		rF.add("no_Fail", noFail);
		 		rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
		 		rF.add("id_kemaskini",id_user);
		 		sql = rF.getSQLUpdate("Tblpfdfail");
		 		stmt.executeUpdate(sql);
	      
		 		SQLRenderer rs = new SQLRenderer();
		    	rs.update("id_permohonan", idpermohonan);
		    	rs.add("flag_semak", "2");	    	 
		    	rs.add("tarikh_kemaskini",rs.unquote("sysdate"));
			    rs.add("id_kemaskini",id_user);
		    	sql = rs.getSQLUpdate("Tblpptpermohonan");
		    	stmt.executeUpdate(sql);

		    	if(id_suburusanstatusfailppt!=""){
		    		//update n add tblrujsuburusanstatus
		    		SQLRenderer r6 = new SQLRenderer();
					r6.update("id_suburusanstatusfailppt",id_suburusanstatusfailppt);	
					r6.add("AKTIF",0);
					r6.add("ID_KEMASKINI",id_user);
					r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
					sql = r6.getSQLUpdate("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);	 
		
		    		SQLRenderer r5 = new SQLRenderer();
					r5.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
					r5.add("ID_PERMOHONAN",idpermohonan);
					r5.add("ID_FAIL",id_fail);
					r5.add("ID_SUBURUSANSTATUS", newIdSuburusanStatus);
					r5.add("AKTIF",1);
					r5.add("ID_MASUK",id_user);
					r5.add("ID_KEMASKINI",id_user);
					r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
					r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
					sql = r5.getSQLInsert("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);
		    	}
		    	
		    	conn.commit();	
			      
	    }catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }
	    finally {
	    if (db != null) db.close();
	    }
	    	
	  }//close sahkan
	
	
	//luluskan id status = 128 & generate no fail baru
	@SuppressWarnings("unchecked")
	public static void lulus(Hashtable data) throws Exception {
		  Db db = null;
		    String sql = "";
		    try
		    {
		     
		     db = new Db();
		    	
		      String id_user = (String)data.get("id_user");
		      
		      String id_permohonan = (String)data.get("id_permohonan");
		      String id_fail = (String)data.get("id_fail");
		      String id_negeri = (String)data.get("id_negeri");
		      String id_kementerian = (String)data.get("id_kementerian");
		      String id_status = (String)data.get("id_status");
		      
		      //-- Generate no Fail "JKPTG(S)/101/SPT/881/kod_kementerian/kod_negeri-seq_fail
		      String KodFailJabatan = "JKPTG(S)/101/SPT/881";
		      String kod_kementerian = "";
		      String kodMampu = "";
		      int seq_id_seksyen = 1;
		      int seq_id_urusan = 17;
		      
		      
		      Statement stmtMT = db.getStatement();
		      SQLRenderer rMT = new SQLRenderer();				 
		      rMT.add("id_kementerian");
		      rMT.add("kod_kementerian");				      
		      rMT.add("id_kementerian",id_kementerian);				      
		      sql = rMT.getSQLSelect("Tblrujkementerian");
		      ResultSet rsMT = stmtMT.executeQuery(sql);
		      while (rsMT.next()) {
		    	  kod_kementerian = rsMT.getString("kod_kementerian");
		      }
		      
		      Statement stmtnegeri = db.getStatement();
		      SQLRenderer rnegeri = new SQLRenderer();				 
		      rnegeri.add("id_negeri");
		      rnegeri.add("kod_mampu");				      
		      rnegeri.add("id_negeri",id_negeri);				      
		      sql = rnegeri.getSQLSelect("Tblrujnegeri");
		      ResultSet rsnegeri = stmtnegeri.executeQuery(sql);
		      while (rsnegeri.next()) {
		    	  kodMampu = rsnegeri.getString("kod_mampu");
		      }
		      
		      //id negeri user
//		      int userIdNegeri = 0;
//		      if(userNegeri!=""){
//		    	  userIdNegeri = Integer.parseInt(userNegeri);
//		      }
	
		      //get year
//		      java.util.Calendar calendar = java.util.Calendar.getInstance();
//		      int getYear = calendar.get(java.util.Calendar.YEAR);
		   
		      String seq = String.format("%06d",File.getSeqNo(seq_id_seksyen,seq_id_urusan));
		      String noFail = KodFailJabatan+"/"+kod_kementerian+"/"+kodMampu+"-"+seq;
		      
		      //update status 128
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_permohonan", id_permohonan);
		      r.add("id_status", id_status);
		      r.add("tarikh_kemaskini",r.unquote("sysdate"));
		      r.add("id_kemaskini",id_user);
		      sql = r.getSQLUpdate("Tblpptpermohonan");
		      stmt.executeUpdate(sql);
		      
		      //masukkan nofail(generated) ke pfdfail
		      Statement stmtF = db.getStatement();
			  SQLRenderer rF = new SQLRenderer();
			  rF.update("id_fail", id_fail);
			  rF.add("no_Fail", noFail);
			  rF.add("tarikh_kemaskini",rF.unquote("sysdate"));
		      rF.add("id_kemaskini",id_user);
		      sql = rF.getSQLUpdate("Tblpfdfail");
		      stmtF.executeUpdate(sql);
		      		
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}
		    finally {
		    if (db != null) db.close();
		    }
	}//close luluskan

	
	@SuppressWarnings("unchecked")
	public static Vector getSenaraiSemakan(String idpermohonan) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = " SELECT DISTINCT ID_SENARAISEMAK,SEMAK1,SEMAK2,SEMAK3,SEMAK4,SEMAK5,SEMAK6,SEMAK7,SEMAK10,SEMAK20	" +
		      		" FROM TBLPPTSENARAISEMAK WHERE ID_PERMOHONAN = '"+idpermohonan+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_senaraisemak", rs.getString("ID_SENARAISEMAK")==null?"":rs.getString("ID_SENARAISEMAK"));
		    	  h.put("semak1", rs.getString("SEMAK1")==null?"0":rs.getString("SEMAK1"));
		    	  h.put("semak2", rs.getString("SEMAK2")==null?"0":rs.getString("SEMAK2"));
		    	  h.put("semak3", rs.getString("SEMAK3")==null?"0":rs.getString("SEMAK3"));
		    	  h.put("semak4", rs.getString("SEMAK4")==null?"0":rs.getString("SEMAK4"));
		    	  h.put("semak5", rs.getString("SEMAK5")==null?"0":rs.getString("SEMAK5"));
		    	  h.put("semak6", rs.getString("SEMAK6")==null?"0":rs.getString("SEMAK6"));
		    	  h.put("semak7", rs.getString("SEMAK7")==null?"0":rs.getString("SEMAK7"));
		    	 
		    	  
		    	  h.put("semak10", rs.getString("SEMAK10")==null?"0":rs.getString("SEMAK10"));
		    	  h.put("semak20", rs.getString("SEMAK20")==null?"0":rs.getString("SEMAK20"));
		    	  list.addElement(h);
		      }
		      return list;
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	} finally {
		    if (db != null) db.close();
		    }
		  }
	
	 public void semakanHapus(String idpermohonan,int idsemakansenarai) throws Exception {
		    Db db = null;
		    int idPermohonan = Integer.parseInt(idpermohonan);
		    String sql = "";
		    try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("id_permohonan", idPermohonan);
		      r.add("id_semakansenarai", idsemakansenarai);
		      sql = r.getSQLDelete("tblsemakanhantar");
		      stmt.executeUpdate(sql);
		    
		    } catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}finally	{
		    if (db != null) db.close();
	    	}
	  }
	 
	 //--Get alamat Kementerian--//
	@SuppressWarnings("unchecked")
	public static Vector getAlamatKementerian(String id_kementerian) throws Exception {
		    
		Db db = null;
		String sql = "";
		  
		try {
		      	
				db = new Db();
				Statement stmt = db.getStatement();
		     
				sql = "SELECT k.alamat1, k.alamat2, k.alamat3, k.poskod, n.id_negeri ";
				sql +="FROM Tblrujkementerian k, Tblrujnegeri n ";
				sql +="WHERE k.id_negeri = n.id_negeri(+) ";
				sql +="AND k.id_kementerian = '"+id_kementerian+"' ";

		     
				ResultSet rs = stmt.executeQuery(sql);
				Vector list = new Vector();
		      
				Hashtable h = null;
		     
				while (rs.next()) {
					h = new Hashtable();
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
					list.addElement(h);
				}
		      return list;
		    }  catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}finally {
		    if (db != null) db.close();
		    }
		  }//find alamat kementerian
	 
	//--Get alamat Kementerian--//
	@SuppressWarnings("unchecked")
	public static Vector getAlamatAgensi(int idA) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT a.alamat1, a.alamat2, a.alamat3, a.poskod, n.id_negeri ";
		      sql +="FROM Tblrujagensi a, Tblrujnegeri n ";
		      sql +="WHERE a.id_negeri = n.id_negeri ";
		      sql +="AND a.id_agensi = "+idA+" ";

		   
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_negeri", rs.getString("id_negeri"));
		    	  h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
		    	  h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
		    	  h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    }  catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}finally {
		    if (db != null) db.close();
		    }
		  }//find alamat Agensi
	 
	//view list dokumen by id
	 @SuppressWarnings("unchecked")
	public static void  setListDokumen(String id) throws Exception {
		    Db db = null;
		    listDokumen.clear();
		    String sql = "";
		    
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();		      
		    /*  r.add("a.id_permohonan");
		      r.add("a.id_Dokumen");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		      r.add("a.tajuk");
		      r.add("a.keterangan");
		      r.add("a.content");		      
		      r.add("p.id_permohonan",id);
		      r.add("p.id_permohonan",r.unquote("a.id_permohonan"));		
		      sql = r.getSQLSelect("Tblpptdokumen a, tblpptpermohonan p");	
		      myLogger.info(" sql doc"+sql);
		      */
		      
		      
		      /*sql = " SELECT a.id_permohonan, a.id_Dokumen, a.nama_Fail, a.jenis_Mime, a.tajuk, a.keterangan, a.content,  FROM Tblpptdokumen a, tblpptpermohonan p WHERE p.id_permohonan = '"+id+"'  AND p.id_permohonan = a.id_permohonan ";
*/		      
		      sql = " SELECT m.keterangan as jenisdoc, a.id_permohonan, a.id_Dokumen, a.nama_Fail, a.jenis_Mime, a.tajuk, a.keterangan, a.content "+
		    		" FROM Tblpptdokumen a, tblpptpermohonan p, tblrujjenisdokumen m "+
		    		" WHERE  p.id_permohonan = a.id_permohonan "+
		    		" AND a.id_jenisdokumen = m.id_jenisdokumen "+
		    		" AND p.id_permohonan = '"+id+"'  ";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      myLogger.info(" sql doc"+sql);
		     
		      Hashtable h;
		      int bil = 1;
		    
		      while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	 
		    	  h.put("bil", bil);
		    	  h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
		    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
		    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    	  h.put("jenisdoc",rs.getString("jenisdoc")== null?"":rs.getString("jenisdoc"));
		          
		    	  listDokumen.addElement(h);
		    	  bil++;	    	
		      }			    
		      //return list;
		    }  catch (Exception re) {
		    	log.error("Error: ", re);
		    	throw re;
		    	}finally {		    	
		    if (db != null) db.close();
		    }
		}
	 
	 public Vector dropdown_jenisdokumen() throws Exception {
			Vector jenis_dokumen = new Vector();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = " SELECT ID_JENISDOKUMEN, KETERANGAN "+
					  " FROM TBLRUJJENISDOKUMEN "+
					  " WHERE ID_JENISDOKUMEN IN ('16171332', '16171331', '1329') ";
						
				//System.out.println(" SQL Negri LIST FROM MODEL :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_JENISDOKUMEN", rs.getString("ID_JENISDOKUMEN") == null ? "" : rs.getString("ID_JENISDOKUMEN"));
					h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));				
					jenis_dokumen.addElement(h);	
					
				}
				//System.out.println(" SENARAI NEGERI FROM MODEL :"+jenis_dokumen);
				return jenis_dokumen;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}	
	 
	 
	 
	 
	 /*
	  * 
	  * 
	  * SEKSYEN 8
	  * 
	  * 
	  */
	 
	
	private static Vector listCarianSek8 = null;
	
	
	public static  Vector getListCarianSek8(){
		return listCarianSek8;
	} 
	 
	
	
	@SuppressWarnings("unchecked")
	public List listPermohonanUmum(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPermohonanUmum = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String MODULENAME = (String) session.getAttribute("_portal_module");
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT LAYER, ID_NEGERI, NAMA_NEGERI FROM "+
					" ( "+
					//" SELECT 1 AS LAYER,'ALL' AS ID_NEGERI, 'KESELURUHAN NEGARA' AS NAMA_NEGERI FROM DUAL "+
					//" UNION "+
					" SELECT 2 AS LAYER, TO_CHAR(N.ID_NEGERI) AS ID_NEGERI, N.NAMA_NEGERI FROM TBLRUJNEGERI N WHERE ID_NEGERI NOT IN (0,17) "+
					" ) "+
					" WHERE ID_NEGERI IS NOT NULL ";
			
			if(USER_ROLE.equals("user_ppk") || (USER_ROLE.equals("adminppk") && !USER_NEGERI.equals("16")))
			{
				//sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
			}
			
			sql += "ORDER BY LAYER,NAMA_NEGERI ";			
			myLogger.info(" V3: SQL listNegeri :"+ sql);
			rs = stmt.executeQuery(sql);
			listPermohonanUmum = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				listPermohonanUmum.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPermohonanUmum;

	}

	 
	@SuppressWarnings("unchecked")
	public static Vector getListPemohonSeksyen8(String userIdNegeri) throws Exception {
			 
		Db db = null;
		String sql = "";
			    
		try{
			    	
				db = new Db();
			    Statement stmt = db.getStatement();
			     
			    sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
	    		sql +=" su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, ";
			    sql +=" p.FLAG_STATUS_ONLINE ";
	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND f.id_suburusan = '52' ";
	    		sql +=" AND p.id_status IN (11,127,138)";
	    	
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
	    		sql +=" ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	    		
	    		
			    ResultSet rs = stmt.executeQuery(sql);
			    Vector list = new Vector();
			      
			    Hashtable h = null;
			    int bil = 1;

			    while (rs.next()) {
			    	h = new Hashtable();
			    	h.put("bil", bil);
			    	h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
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
			    	
			    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
	    				
	    				if(rs.getString("flag_semak").equals("1")){
	    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
	    				}else if(rs.getString("flag_semak").equals("2")){
	    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
	    				}else{
	    					h.put("status_fail","PENDAFTARAN");
	    				}
	    				
	    			}else{
	    				h.put("status_fail","PENDAFTARAN");
	    			}

			    	if(rs.getString("no_fail") == null){
			    		h.put("no_fail","BELUM DISAHKAN");
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
			    	
	}//close getListPemohonSeksyen8
	 
	
	@SuppressWarnings("unchecked")
	public static void setListCarianSek8(String carianNofail, String carianTarikh, String status, String userIdNegeri) throws Exception {
		
		listCarianSek8 = new Vector();
		
	    Db db = null;
	    listCarianSek8.clear();
	    String sql = "";
	    
	    String cariTarikh = "";
	    if(carianTarikh!=""){
	    	cariTarikh = "to_date('" + carianTarikh + "','dd/MM/yyyy')";
	    }	    
	    
	    String nofail = carianNofail.trim();
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	      
	    		sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, "; 
	    		sql +=" su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, p.no_rujukan_ptg, p.no_rujukan_ptd, p.no_rujukan_upt, ";
			    sql +=" p.FLAG_STATUS_ONLINE ";
	    		sql +=" FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
	    		sql +=" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	    		sql +=" AND p.id_status = s.id_status ";
	    		sql +=" AND f.id_suburusan = '52' ";
	    		sql +=" AND p.id_status IN (11,127,138)";
	    		if(!userIdNegeri.equals("16") && !userIdNegeri.isEmpty()){
	    			if(userIdNegeri.equals("14")){
	    				sql += "AND f.id_negeri in (14,15,16) ";
	    			}else{
	    				sql += "AND f.id_negeri ='"+userIdNegeri+"'";
	    			}		
	    		}
	    		
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
			    	h.put("FLAG_STATUS_ONLINE", rs.getString("FLAG_STATUS_ONLINE")== null?"":rs.getString("FLAG_STATUS_ONLINE"));
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
			    	
			    	if(rs.getString("flag_semak") != null && rs.getString("flag_semak") != ""){
	    				
	    				if(rs.getString("flag_semak").equals("1")){
	    					h.put("status_fail","<b>TUNGGU PENGESAHAN</b>");
	    				}else if(rs.getString("flag_semak").equals("2")){
	    					h.put("status_fail","<b>TELAH DISAHKAN</b>");
	    				}else{
	    					h.put("status_fail","PENDAFTARAN");
	    				}
	    				
	    			}else{
	    				h.put("status_fail","PENDAFTARAN");
	    			}

			    	if(rs.getString("no_fail") == null){
			    		h.put("no_fail","BELUM DISAHKAN");
			    	}else{
			    		h.put("no_fail",rs.getString("no_fail"));
			    	}
			    	listCarianSek8.addElement(h);
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
	
	
	//get detail user
	private static Vector listGetUserId = null;
	
	public static Vector getUserIds() {
		return listGetUserId;
	}
	
	@SuppressWarnings("unchecked")
	public static void setGetUserId(String userid) throws Exception {
		
		listGetUserId = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  " SELECT DISTINCT B.ID_DAERAH,B.KOD_DAERAH,C.ID_NEGERI,A.ID_PEJABATJKPTG,C.KOD_MAMPU ";
				sql += " FROM USERS_INTERNAL A, TBLRUJDAERAH B, TBLRUJNEGERI C ";
				sql += " WHERE A.ID_NEGERI = C.ID_NEGERI(+) ";
				sql += " AND A.ID_DAERAH = B.ID_DAERAH(+) ";
				sql += " AND A.USER_ID = '"+userid+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("idDaerah", rs.getString("ID_DAERAH")== null?"":rs.getString("ID_DAERAH"));
					h.put("kodDaerah", rs.getString("KOD_DAERAH")== null?"":rs.getString("KOD_DAERAH"));
					h.put("idNegeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
					h.put("idpejabat", rs.getString("ID_PEJABATJKPTG")== null?"":rs.getString("ID_PEJABATJKPTG"));
					h.put("kodnegeri", rs.getString("KOD_MAMPU")== null?"":rs.getString("KOD_MAMPU"));
					listGetUserId.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if(db != null)db.close();
		}
	}
	 
	//get detail user
	private static Vector namaPengarah = null;
	
	public static Vector getNamaPengarah() {
		return namaPengarah;
	}
	
	@SuppressWarnings("unchecked")
	public static void setNamaPengarah(String userIdNeg) throws Exception {
		
		namaPengarah = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  "SELECT DISTINCT A.USER_ID, A.USER_NAME ";
				sql += " FROM USERS A, USERS_INTERNAL B ";
				sql += " WHERE A.USER_ID = B.USER_ID ";
				sql += " AND NVL(B.ID_JAWATAN,0) = '4' ";
				sql += " AND NVL(B.ID_SEKSYEN,0) = '1' ";
				sql += " AND B.FLAG_AKTIF = '1' ";
				
				if(userIdNeg.equals("15") || userIdNeg.equals("16") ){
					sql += " AND B.ID_NEGERI = '14'";
				}else{
					sql += " AND B.ID_NEGERI = '"+userIdNeg+"'";
				}
				
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
					h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
					namaPengarah.addElement(h);
				
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if(db != null)db.close();
		}
	}//close setNamaPengarah
	
	//penambahan yati
	public static Vector listPengarah = null;
	
	public static Vector getListPengarah() {
		return listPengarah;
	}
	
	//get detail user
		@SuppressWarnings("unchecked")
		public static Vector setListPengarah(String id_fail) throws Exception {
		
			Vector listPengarah = new Vector();
			
			Db db = null;
			String sql = "";
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
				
					sql =  "SELECT F.ID_FAIL, F.ID_NEGERI, U.USER_ROLE,U.USER_ID, U.USER_NAME,F.NO_FAIL,UI.EMEL AS ROLE FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, USERS U, USERS_INTERNAL UI " +
							"WHERE P.ID_FAIL = F.ID_FAIL AND U.USER_ID = UI.USER_ID AND U.USER_ROLE = '(PPT)PengarahUnit' " +
							"AND UI.ID_NEGERI = F.ID_NEGERI  AND UI.FLAG_AKTIF = '1' AND F.ID_FAIL =  '"+id_fail+"'" +
							"UNION " +
							"SELECT F.ID_FAIL, F.ID_NEGERI, UR.ROLE_ID, U.USER_ID, U.USER_NAME,F.NO_FAIL,UI.EMEL AS ROLE FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, USERS U, USERS_INTERNAL UI, USER_ROLE UR " +
							"WHERE P.ID_FAIL = F.ID_FAIL AND U.USER_ID = UI.USER_ID " +
							"AND UI.ID_NEGERI = F.ID_NEGERI AND U.USER_LOGIN = UR.USER_ID AND UR.ROLE_ID = '(PPT)PengarahUnit' " +
							"AND UI.FLAG_AKTIF = '1' AND F.ID_FAIL = '"+id_fail+"' ";
					myLogger.info("GET EMEL : "+sql);
					ResultSet rs = stmt.executeQuery(sql);
					Hashtable h = null;
					while (rs.next()) {
						h = new Hashtable();
						h.put("id_fail", rs.getString("ID_FAIL")== null?"":rs.getString("ID_FAIL"));
						h.put("user_id", rs.getString("USER_ID")== null?"":rs.getString("USER_ID"));
						h.put("nama_pengarah", rs.getString("USER_NAME")== null?"":rs.getString("USER_NAME"));
						h.put("emel", rs.getString("ROLE")== null?"":rs.getString("ROLE"));
						listPengarah.addElement(h);
						
				}
					
					//System.out.println("SQL LIST listPengarah yat :::"+listPengarah);
				return listPengarah;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}//close setNamaPengarah
		

	@SuppressWarnings("unchecked")
	public static void hapusDokumen(Hashtable data) throws Exception {	   
		Db db = null;
	    String sql = "";	   
	    try{	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	String iddokumen = (String)data.get("id_dokumen");
	    	sql = "DELETE FROM tblpptdokumen where id_dokumen = '"+iddokumen+"'";
	    	stmt.executeUpdate(sql);
	    
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    }
	    finally {
	    	if (db != null) db.close();
	    }
	  
	}//close hapus
	
	@SuppressWarnings("unchecked")
	public static void updateFlagSah(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 String id_permohonan = (String)data.get("id_permohonan");
	      
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_permohonan", id_permohonan);
	    	 r.add("flag_semak", "1");	    	 
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
		     r.add("tarikh_sahkan",r.unquote("sysdate"));

	    	 sql = r.getSQLUpdate("Tblpptpermohonan");
	    	 stmt.executeUpdate(sql);
	    	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close updateFlagSah
	
	@SuppressWarnings("unchecked")
	public static void updateFlagSubjaket(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");

	    		
	    		SQLRenderer r = new SQLRenderer();
    			r.update("id_permohonan", id_permohonan);		    				
    			r.add("no_subjaket", "");
    			r.add("tarikh_kemaskini",r.unquote("sysdate"));
    			r.add("id_kemaskini",id_user);    		
    			sql = r.getSQLUpdate("Tblppthakmilik");
    			stmt.executeUpdate(sql);
    		
    			r.clear();
    		
    			//update flag di tblpptpermohonan
    			r.update("id_permohonan", id_permohonan);		    				
    			r.add("flag_subjaket", "");
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
	   
	  }//close updateFlagSubjaket
	
	
	@SuppressWarnings("unchecked")
	public void setListSeqSubjaket(String idPermohonan) throws Exception{
		
		listSeqSubjaket = new Vector();
		
		Db db = null;
		listSeqSubjaket.clear();
		String sql = "";

		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT M.ID_HAKMILIK, M.NO_SUBJAKET, MK.NAMA_MUKIM, ";
				
				sql += " CASE ";
				sql += " WHEN M.no_lot IS NOT NULL AND M.no_pt IS NULL THEN M.no_lot "; 
				sql += " WHEN M.no_lot IS NULL AND M.no_pt IS NULL THEN LT.keterangan || M.no_pt ";
				sql += " WHEN M.no_lot IS NULL AND M.no_pt IS NOT NULL THEN  LT.keterangan || M.no_pt ";       
				sql += " WHEN M.no_lot IS NOT NULL AND M.no_pt IS NOT NULL THEN LT.keterangan || M.no_pt || CHR(32) || CHR(40) || M.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTSUB ";
				
				sql += " FROM TBLPPTHAKMILIK M, TBLRUJLOT LT, TBLRUJMUKIM MK ";
				sql += " WHERE M.ID_PERMOHONAN = '"+idPermohonan+"'";
				sql += " AND M.ID_MUKIM = MK.ID_MUKIM(+) ";
				sql += " AND M.ID_LOT = LT.ID_LOT(+)";
				sql += " AND NVL(M.flag_pembatalan_keseluruhan,0) <> 'Y' ";
				sql += " AND NVL(M.flag_penarikan_keseluruhan,0) <> 'Y'";
				
				//sql += " ORDER BY LPAD(M.NO_SUBJAKET,10) asc, LPAD(M.no_lot,10) asc, LPAD(M.no_pt,10) asc, LPAD(NO_LOTSUB,10) asc, MK.NAMA_MUKIM asc";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTSUB,20) asc, LPAD(m.no_subjaket,20) asc";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_hakmilik", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					listSeqSubjaket.addElement(h);
					bil++;	
				}
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	@SuppressWarnings("unchecked")
	public void setGetIdSuburusanstatusfail(String id_permohonan) throws Exception {
		
		getIdSuburusanstatusfail = new Vector();
		
		Db db = null;
		getIdSuburusanstatusfail.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT DISTINCT ID_SUBURUSANSTATUSFAILPPT,ID_SUBURUSANSTATUS,AKTIF ";
				sql += " FROM TBLRUJSUBURUSANSTATUSFAILPPT ";
				sql += " WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
				sql += " AND AKTIF = '1' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_suburusanstatusfailppt", rs.getString("ID_SUBURUSANSTATUSFAILPPT")==null?"":rs.getString("ID_SUBURUSANSTATUSFAILPPT"));
					h.put("id_suburusanstatus", rs.getString("ID_SUBURUSANSTATUS")==null?"":rs.getString("ID_SUBURUSANSTATUS"));
					getIdSuburusanstatusfail.addElement(h);
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setGetIdSuburusanstatusfail
	
	@SuppressWarnings("unchecked")
	public static void updateSuburusanStatusFailPPT(Hashtable data,String currentSuburusanstatusfailppt,String newSuburusanStatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_fail = (String)data.get("id_fail");
	    		
	    		//update n add tblrujsuburusanstatus
			    SQLRenderer r = new SQLRenderer();
				r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
				myLogger.info(" updateSuburusanStatusFailPPT atas : "+sql);
				stmt.executeUpdate(sql);	 
				  
				r.clear();
				
				r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
				r.add("ID_PERMOHONAN",id_permohonan);
				r.add("ID_FAIL",id_fail);
				r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
				r.add("AKTIF",1);
				r.add("ID_MASUK",id_user);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
				myLogger.info(" updateSuburusanStatusFailPPT bawah : "+sql);
				stmt.executeUpdate(sql);
				
				/*
				 
				//BARU
				//SQLRenderer r = new SQLRenderer();
				r.clear();
				r.update("ID_PERMOHONAN", id_permohonan);	
				r.add("ID_STATUS",getIDSTATUSbySUBURUSANSTATUS(id_suburusanstatus));
				sql = r.getSQLUpdate("TBLPPTPERMOHONAN");
				stmt.executeUpdate(sql);
				
				 */
				
	    	
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	    if (db != null) db.close();
	    }//close finally
	   
	  }//close updateSuburusanStatusFailPPT
	
	@SuppressWarnings("unchecked")
	public static void updateSuburusanHakmilik(Hashtable data,String currentSuburusanstatushakmilik,String id_suburusanstatus) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan");
	    		String id_fail = (String)data.get("id_fail");
	    		String id_hakmilik = (String)data.get("id_hakmilik");
	    		
	    		//update n add tblrujsuburusanstatus
			    SQLRenderer r = new SQLRenderer();
				r.update("ID_SUBURUSANSTATUSHAKMILIK", currentSuburusanstatushakmilik);	
				r.add("AKTIF",0);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLUpdate("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);	 
				  
				r.clear();
				
	     		r.add("ID_SUBURUSANSTATUSHAKMILIK",DB.getNextID("TBLRUJSUBURUSANHAKMILIK_SEQ"));
	     		r.add("ID_PERMOHONAN",id_permohonan);
	     		r.add("ID_HAKMILIK",id_hakmilik);
	     		r.add("ID_FAIL",id_fail);
	     		r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     		r.add("AKTIF",1);
				r.add("ID_MASUK",id_user);
				r.add("ID_KEMASKINI",id_user);
				r.add("TARIKH_MASUK",r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
				sql = r.getSQLInsert("tblrujsuburusanstatushakmilik");
				stmt.executeUpdate(sql);
				
				
				
				
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	    if (db != null) db.close();
	    }//close finally
	   
	  }//close addSuburusanHakmilik
	
	public static String getIDSTATUSbySUBURUSANSTATUS(String ID_SUBURUSANSTATUS) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String ID_STATUS="";
			sql = " SELECT ID_STATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_SUBURUSANSTATUS = '"+ID_SUBURUSANSTATUS+"'";				
				myLogger.info(" SQL getIDSTATUSbySUBURUSANSTATUS :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					ID_STATUS = (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					
				}
			
			return ID_STATUS;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSuburusanHakmilik(String id_hakmilik) throws Exception {
		
		dataSuburusanHakmilik = new Vector();
		
		Db db = null;
		dataSuburusanHakmilik.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_SUBURUSANSTATUSHAKMILIK,ID_SUBURUSANSTATUS,AKTIF ";
				sql += " FROM TBLRUJSUBURUSANSTATUSHAKMILIK ";
				sql += " WHERE ID_HAKMILIK = '"+id_hakmilik+"'";
				sql += " AND AKTIF = '1' ";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_suburusanstatushakmilik", rs.getString("ID_SUBURUSANSTATUSHAKMILIK")==null?"":rs.getString("ID_SUBURUSANSTATUSHAKMILIK"));
					h.put("id_suburusanstatus", rs.getString("ID_SUBURUSANSTATUS")==null?"":rs.getString("ID_SUBURUSANSTATUS"));
					dataSuburusanHakmilik.addElement(h);
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setDataSuburusanHakmilik
	
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanahAcceptPenarikan_yg_asal(String idPermohonan,String lot,String idpegawai) throws Exception{
		
		listMaklumatTanahAcceptPenarikan = new Vector();
		
		Db db = null;
		listMaklumatTanahAcceptPenarikan.clear();
		String sql = "";
		String noLOT = lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;

				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rz = stmt.executeQuery(sql);	
				
				String namaMukim = "";
				int bilMukim = 0;
				
				while (rz.next()){					
						
					if(totalMukim!=0){
						if(bilMukim==0){
							
							namaMukim += namaMukim = rz.getString("nama_mukim");	
						
						}else{
							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;

				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl2 = stmt.executeQuery(sql);	
				
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;
				
				while (rl2.next()){					
					
					if(totalLOT!=0){
						if(bilLOT==0){
							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");
							
						}else{
							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				
				sql = "SELECT DISTINCT m.flag_segera_sebahagian, m.seksyen, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (27,40,41,42))as totalPB, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d, Users u ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
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
				
				//ID PEGAWAI
				if (idpegawai != "" && idpegawai != null) {
						sql += " AND m.id_pegawai = '"+idpegawai+"'";
				}//close if idpegawai
				
				//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(no_lotpt,10) asc, mk.nama_mukim asc";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
				
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
					h.put("listLOT", listLOT.toUpperCase());
					h.put("listLOTHM", listLOTHM.toUpperCase());
					h.put("nama2Mukim", nama2Mukim.toUpperCase());
					h.put("nama2MukimInit", nama2Mukim);
					h.put("bil", bil);
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
					h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
					
					h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
					h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
					h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
					h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					//validation for PU
					if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
						
						double luasAmbil = 0;
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
							luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
						}else{
							luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
						}
						
						double luasLot = 0;
						if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
							luasLot = rs.getDouble("luas_lot"); //meter persegi
						}else{
							luasLot = rs.getDouble("luas_lot") * 10000; //hektar
						}						
						
						double baki = luasLot - luasAmbil;
						
						if(baki>0){
							h.put("flagPU","yes");
						}else{
							h.put("flagPU","no");
						}
				
					}else{
						h.put("flagPU","no");
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
							
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil") / 10000;
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}	
						
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					//get total luas ambil in hectar
					if(rs.getString("luas_ambil") != null){	
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
							totalSize += rs.getDouble("luas_ambil");
						}else{
							totalSize += (rs.getDouble("luas_ambil") / 10000);
						}
					}
				
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
					
					listMaklumatTanahAcceptPenarikan.addElement(h);
					bil++;	
				}
				
				
				
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	@SuppressWarnings("unchecked")
	public void setListMaklumatTanahAcceptPenarikan_listHakmilik(String idPermohonan,String lot,String idpegawai) throws Exception{
		
		listMaklumatTanahAcceptPenarikan = new Vector();
		
		Db db = null;
		listMaklumatTanahAcceptPenarikan.clear();
		String sql = "";
		String noLOT = lot.trim();
		//String nama2Mukim = "";
		//String listLOT = "";
		//String listLOTHM = "";
		double totalSize = 0;
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				/*
				sql = "SELECT COUNT(distinct mk.nama_mukim) as totalMukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rx = stmt.executeQuery(sql);				
				int totalMukim = 0;

				while (rx.next()){					
					totalMukim = rx.getInt("totalMukim");														
				}
		
				sql = "SELECT DISTINCT nama_mukim FROM Tblpptpermohonan p, Tblrujmukim mk, Tblppthakmilik m ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) AND m.id_mukim = mk.id_mukim(+) "; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rz = stmt.executeQuery(sql);	
				
				String namaMukim = "";
				int bilMukim = 0;
				
				while (rz.next()){					
						
					if(totalMukim!=0){
						if(bilMukim==0){
							
							namaMukim += namaMukim = rz.getString("nama_mukim");	
						
						}else{
							
							if(totalMukim - bilMukim == 1){
								namaMukim += " dan "+rz.getString("nama_mukim");
							}else{
								namaMukim += ", "+rz.getString("nama_mukim");	
							}	
						}
						bilMukim++;	
					
					}else{
						namaMukim = "";
					}
					
					if(totalMukim == bilMukim){
						nama2Mukim = namaMukim;
					}else{
						nama2Mukim = "";
					}
				}
				
				
				//////////////get lot////////////////////
				
				sql = "SELECT COUNT(m.id_hakmilik) as totalLOT FROM Tblpptpermohonan p, Tblppthakmilik m, Tblrujlot lt ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+)"; 
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl = stmt.executeQuery(sql);				
				int totalLOT = 0;

				while (rl.next()){					
					totalLOT = rl.getInt("totalLOT");														
				}
				
				sql = "SELECT CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";      
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT, m.no_hakmilik, jh.kod_jenis_hakmilik  FROM Tblpptpermohonan p, Tblrujlot lt, Tblppthakmilik m, Tblrujjenishakmilik jh ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";
				sql += " AND m.id_lot = lt.id_lot(+) "; 
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND p.id_Permohonan = '"+idPermohonan+"'";
				
				ResultSet rl2 = stmt.executeQuery(sql);	
				
				String LotPT = "";
				String LotPTHM = "";
				int bilLOT = 0;
				
				while (rl2.next()){					
					
					if(totalLOT!=0){
						if(bilLOT==0){
							
							LotPTHM += LotPTHM = (rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
							LotPT += LotPT = rl2.getString("NO_LOTPT");
							
						}else{
							
							if(totalLOT - bilLOT == 1){
								LotPTHM += " dan "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));
								LotPT += " dan "+rl2.getString("NO_LOTPT");
							}else{
								LotPTHM += ", "+(rl2.getString("NO_LOTPT")+" "+rl2.getString("kod_jenis_hakmilik")+" "+rl2.getString("no_hakmilik"));	
								LotPT += ", "+rl2.getString("NO_LOTPT");
							}	
						}
						bilLOT++;	
					
					}else{
						LotPT = "";
						LotPTHM = "";
					}
					
					if(totalLOT == bilLOT){
						listLOT = LotPT;
						listLOTHM = LotPTHM;
					}else{
						listLOT = "";
						listLOTHM = "";
					}
				}
				*/
				sql = "SELECT DISTINCT m.flag_segera_sebahagian, m.seksyen, p.no_rujukan_ptg, p.id_status, f.no_fail, m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
				sql += " (select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
				sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (27,40,41,42))as totalPB, ";
				sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
				sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
				sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
				sql += " m.flag_ambil_segera,d.nama_daerah,m.flag_borangl, m.status_borangl, m.tarikh_terima_hm, u.user_name as nama_pegawai, jh.status_hakmilik, ";
				sql += " m.flag_jenis_rizab, m.nama_lain_rizab, m.no_warta_rizab, m.tarikh_warta_rizab, m.no_subjaket, m.id_pegawai, m.id_unitluasambil_convert, m.id_unitluaslot_convert, ";
				sql += " CASE ";
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
				sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN  lt.keterangan || m.no_pt ";       
				sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
				sql += " ELSE '' ";
				sql += " END AS NO_LOTPT ";  
				sql += " FROM Tblpfdfail f, Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, "; 
				sql += " Tblrujdaerah d, Users u ";
				sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
				sql += " AND m.id_negeri = n.id_negeri "; 
				sql += " AND p.id_fail = f.id_fail "; 
				sql += " AND m.id_daerah = d.id_daerah(+)";
				sql += " AND ls.id_luas(+) = m.id_unitluaslot "; 
				sql += " AND m.id_pegawai = u.user_id(+)";
				sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
				sql += " AND m.id_lot = lt.id_lot(+) ";
				sql += " AND m.id_mukim = mk.id_mukim(+) ";  
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
				
				//ID PEGAWAI
				if (idpegawai != "" && idpegawai != null) {
						sql += " AND m.id_pegawai = '"+idpegawai+"'";
				}//close if idpegawai
				
				//sql += " ORDER BY LPAD(m.no_subjaket,10) asc, LPAD(m.no_lot,10) asc, LPAD(m.no_pt,10) asc, LPAD(no_lotpt,10) asc, mk.nama_mukim asc";
				sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(no_lotpt,20) asc, LPAD(m.no_subjaket,20) asc";
				
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
					h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
					//h.put("listLOT", listLOT.toUpperCase());
					//h.put("listLOTHM", listLOTHM.toUpperCase());
					//h.put("nama2Mukim", nama2Mukim.toUpperCase());
					//h.put("nama2MukimInit", nama2Mukim);
					h.put("bil", bil);
					h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
					h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
					h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
					h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
					h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
					h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
					h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
					h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
					h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
					h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
					h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
					h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
					h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
					h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
					h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
					h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
					
					h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
					h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
					h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
					h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
					
					if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
						
						double luasAmbil = rs.getDouble("luas_ambil");
						String LA = Utils.formatLuasHM(luasAmbil);
						h.put("luas_ambil",LA);
								
					}else{
						h.put("luas_ambil","0");
					}
					
					
					//validation for PU
					if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
						
						double luasAmbil = 0;
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
							luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
						}else{
							luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
						}
						
						double luasLot = 0;
						if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
							luasLot = rs.getDouble("luas_lot"); //meter persegi
						}else{
							luasLot = rs.getDouble("luas_lot") * 10000; //hektar
						}						
						
						double baki = luasLot - luasAmbil;
						
						if(baki>0){
							h.put("flagPU","yes");
						}else{
							h.put("flagPU","no");
						}
				
					}else{
						h.put("flagPU","no");
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
							
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil");
								String LAH = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAH);
							}else{
								h.put("nilaiTanah","0");
							}
							
						}else{						
							if(rs.getString("luas_ambil") != null){
								double luasAmbil = rs.getDouble("luas_ambil") / 10000;
								String LAM = Utils.formatLuasHM(luasAmbil);
								h.put("nilaiTanah",LAM);
							}else{
								h.put("nilaiTanah","0");
							}						
						}	
						
					}else{
						h.put("nilaiTanah","0");
					}
					
					
					//get total luas ambil in hectar
					if(rs.getString("luas_ambil") != null){	
						if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
							totalSize += rs.getDouble("luas_ambil");
						}else{
							totalSize += (rs.getDouble("luas_ambil") / 10000);
						}
					}
				
					
					
					h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
					h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
					h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
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
					
					listMaklumatTanahAcceptPenarikan.addElement(h);
					bil++;	
				}
				
				
				
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	
	@SuppressWarnings("unchecked")
	public void setListHMwithIdBorangK(String idPermohonan,String lot) throws Exception{
			
		listHMwithIdBorangK = new Vector();
			
			Db db = null;
			listHMwithIdBorangK.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT DISTINCT m.seksyen,m.catatan, p.id_permohonan, ls.keterangan as unit1, lt.keterangan as unit2, m.id_hakmilik, m.id_negeri, n.nama_negeri, ";
					sql += " m.id_jenishakmilik, m.id_daerah, m.id_mukim, mk.nama_mukim, m.id_unitluaslot, m.luas_ambil, ";
					sql += " m.no_hakmilik, m.no_lot, m.luas_lot, m.no_pt, m.tarikh_daftar, m.tarikh_luput, m.tempoh_luput, jh.kod_jenis_hakmilik, ";
					sql += " m.lokasi,m.syarat_nyata,m.syarat_khas,m.sekatan_hak,m.sekatan_kepentingan,m.no_syit, jh.keterangan as jenis_hakmilik, m.id_kategoritanah, ";
					sql += " m.flag_ambil_segera, m.id_unitluasambil_convert, m.id_unitluaslot_convert, u.user_name as nama_pegawai, m.no_subjaket, ";
					sql += "(select count(a.id_hakmilikpb) from Tblppthakmilikpb a, Tblpptpihakberkepentingan b ";
					sql += " where a.id_pihakberkepentingan = b.id_pihakberkepentingan and a.id_hakmilik(+) = m.id_hakmilik and a.id_jenispb not in (40,41,42))as totalPB, ";
					sql += " CASE ";
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NULL THEN m.no_lot "; 
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NULL THEN lt.keterangan || m.no_pt ";
					sql += " WHEN m.no_lot IS NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt "; 
					sql += " WHEN m.no_lot IS NOT NULL AND m.no_pt IS NOT NULL THEN lt.keterangan || m.no_pt || CHR(32) || CHR(40) || m.no_lot || CHR(41) ";
					sql += " ELSE '' ";
					sql += " END AS NO_LOTPT ";  
					
					sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u "; 
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
					sql += " AND u.user_id(+) = m.id_pegawai ";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
					sql += " AND (select count(*)from Tblpptborangk a, Tblppthakmilikborangk b where a.id_borangk = b.id_borangk and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";
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
					sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
					
					
					//myLogger.info("sql : "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					Hashtable h;
					int bil = 1;
					
					while (rs.next()){
						h = new Hashtable();
						h.put("bil", bil);
						h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
						h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
						h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
						h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
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
						
						//validation for PU
						if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
							
							double luasAmbil = 0;
							if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
								luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
							}else{
								luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
							}
							
							double luasLot = 0;
							if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
								luasLot = rs.getDouble("luas_lot"); //meter persegi
							}else{
								luasLot = rs.getDouble("luas_lot") * 10000; //hektar
							}						
							
							double baki = luasLot - luasAmbil;
							
							if(baki>0){
								h.put("flagPU","yes");
							}else{
								h.put("flagPU","no");
							}
					
						}else{
							h.put("flagPU","no");
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
						
						listHMwithIdBorangK.addElement(h);
						bil++;	
					}
				//return list;
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}finally {
				if(db != null) db.close();
			}
	}//close setListNotisBorangK 
	
	
	@SuppressWarnings("unchecked")
	public int setListHMwithIdBorangK_count(String idPermohonan,String lot) throws Exception{
			
		//listHMwithIdBorangK = new Vector();
			
			Db db = null;
		//	listHMwithIdBorangK.clear();
			String sql = "";
			String noLOT = lot.trim();
			
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					
					sql = "SELECT count(*) as total ";  
					
					sql += " FROM Tblpptpermohonan p, Tblrujlot lt, Tblrujluas ls, Tblrujmukim mk, Tblrujnegeri n, Tblppthakmilik m, Tblrujjenishakmilik jh, Users u "; 
					sql += " WHERE m.id_Permohonan = p.id_Permohonan(+) ";  
					sql += " AND m.id_negeri = n.id_negeri "; 
					sql += " AND ls.id_luas(+) = m.id_unitluaslot ";  
					sql += " AND u.user_id(+) = m.id_pegawai ";
					sql += " AND m.id_jenishakmilik = jh.id_jenishakmilik(+)";
					sql += " AND m.id_lot = lt.id_lot(+) ";
					sql += " AND m.id_mukim = mk.id_mukim(+) ";  
					sql += " AND NVL(m.flag_pembatalan_keseluruhan,0) <> 'Y' ";
					sql += " AND NVL(m.flag_penarikan_keseluruhan,0) <> 'Y'";
					sql += " AND (select count(*)from Tblpptborangk a, Tblppthakmilikborangk b where a.id_borangk = b.id_borangk and b.id_hakmilik(+) = m.id_hakmilik) > '0' ";
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
					//sql += " ORDER BY mk.nama_mukim asc, LPAD(m.no_lot,20) asc, LPAD(m.no_pt,20) asc, LPAD(NO_LOTPT,20) asc, LPAD(m.no_subjaket,20) asc";
					
					
					//myLogger.info("sql : "+sql);
					ResultSet rs = stmt.executeQuery(sql);	
					int total = 0;
					
					while (rs.next()){
						
						total = rs.getInt("total");
					
					}
				return total;
			} catch (Exception re) {
				log.error("Error: ", re);
				throw re;
				}finally {
				if(db != null) db.close();
			}
	}//close setListNotisBorangK 
	
	@SuppressWarnings("unchecked")
	public static void setDataKementerianOnline(String userid) throws Exception {
		
		dataKementerianOnline = new Vector();
		
		dataKementerianOnline.clear();
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  "SELECT DISTINCT B.ID_KEMENTERIAN ";
				sql += " FROM USERS A, USERS_KEMENTERIAN B ";
				sql += " WHERE A.USER_ID = B.USER_ID(+) ";
				sql += " AND A.USER_ID = '"+userid+"'"; 
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("id_kementerian", rs.getString("ID_KEMENTERIAN")== null?"":rs.getString("ID_KEMENTERIAN"));
					dataKementerianOnline.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
		if (db != null)	db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void setCheckExistNoFail(String checkNoFail,String jenisNoFail,String seksyen,String no_rujukan_ptg,
										   String no_rujukan_ptd, String no_rujukan_upt) throws Exception {
		
		checkExistNoFail = new Vector();
		
		checkExistNoFail.clear();
		Db db = null;
		String sql = "";
		
		String no_fail = checkNoFail.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  "SELECT DISTINCT A.NO_RUJUKAN_PTG, A.NO_RUJUKAN_PTD, A.NO_RUJUKAN_UPT ";
				sql += " FROM TBLPPTPERMOHONAN A, TBLPFDFAIL B ";
				sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
				sql += " AND B.ID_SUBURUSAN = '52' ";
				
				if(jenisNoFail.equals("ptg") && !no_fail.equals("")){
					sql += " AND UPPER(A.NO_RUJUKAN_PTG) = UPPER('"+no_fail.toUpperCase()+"') ";
					if(!no_rujukan_ptg.equals("")){
						sql += " AND UPPER(A.NO_RUJUKAN_PTG) <> UPPER('"+no_rujukan_ptg+"')";
					}
				}else if(jenisNoFail.equals("ptd") && !no_fail.equals("")){
					sql += " AND UPPER(A.NO_RUJUKAN_PTD) = UPPER('"+no_fail.toUpperCase()+"') ";
					if(!no_rujukan_ptd.equals("")){
						sql += " AND UPPER(A.NO_RUJUKAN_PTD) <> UPPER('"+no_rujukan_ptd+"')";
					}
				}else if(jenisNoFail.equals("upt") && !no_fail.equals("")){
					sql += " AND UPPER(A.NO_RUJUKAN_UPT) = UPPER('"+no_fail.toUpperCase()+"') ";
					if(!no_rujukan_upt.equals("")){
						sql += " AND UPPER(A.NO_RUJUKAN_UPT) <> UPPER('"+no_rujukan_upt+"')";
					}
				}
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG")== null?"":rs.getString("NO_RUJUKAN_PTG"));
					h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD")== null?"":rs.getString("NO_RUJUKAN_PTD"));
					h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT")== null?"":rs.getString("NO_RUJUKAN_UPT"));
					checkExistNoFail.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)db.close();
		}
		
	}//close setCheckExistNoFail
	
	@SuppressWarnings("unchecked")
	public static void simpanCatatanTolak(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 String id_permohonan = (String)data.get("id_permohonan");
	    	 String txtCatatan = (String)data.get("txtCatatan");
	    	 String id_status = "8";
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_permohonan", id_permohonan);
	    	 r.add("flag_status_online", "1");
	    	 r.add("catatan_status_online", txtCatatan);	
	    	 r.add("id_status",id_status);
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
		     r.add("flag_semakan_online","");
	    	 sql = r.getSQLUpdate("Tblpptpermohonan");
	    	 stmt.executeUpdate(sql);
	    	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close simpanCatatanTolak
	
	
	public void simpanCatatanTolakKJP(String user_id,String id_permohonan,String jenisTolak,String catatan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	db = new Db();
	    	 Statement stmt = db.getStatement();
	    	
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_permohonan", id_permohonan);
	    	 if(jenisTolak.equals("pelulus"))
	    	 {
	    		 r.add("CATATANTOLAK_PELULUS",catatan);
	    	 }
	    	 else if(jenisTolak.equals("penyemak"))
	    	 {
	    		 r.add("CATATANTOLAK_PENYEMAK",catatan);
	    	 }
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",user_id);
		     sql = r.getSQLUpdate("Tblpptpermohonan");
		     myLogger.info("UPDATE CATATAN TOLAK : "+sql);
	    	 stmt.executeUpdate(sql);
	    	 
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close simpanCatatanTolak
	
	
	@SuppressWarnings("unchecked")
	public static Vector getDataTolakPermohonanKJP(String id_permohonan,String jenisTolak) throws Exception {
		    
		Db db = null;
		String sql = "";
		
		try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT CATATANTOLAK_PELULUS,CATATANTOLAK_PENYEMAK FROM TBLPPTPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      myLogger.info("getDataTolakPermohonanKJP :::::::::: "+sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  if(jenisTolak.equals("pelulus"))
		    	  {
		    		  h.put("catatan_status_online", rs.getString("CATATANTOLAK_PELULUS")==null?"":rs.getString("CATATANTOLAK_PELULUS"));
		    	  }
		    	  else if(jenisTolak.equals("penyemak"))
		    	  {
		    		  h.put("catatan_status_online", rs.getString("CATATANTOLAK_PENYEMAK")==null?"":rs.getString("CATATANTOLAK_PENYEMAK"));
		    	  }
		    	  list.addElement(h);
		 }
		   return list;
		 } catch (Exception re) {
			 log.error("Error: ", re);
			 throw re;
			 } finally {
		    if (db != null) db.close();
		    }
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static Vector getDataTolakPermohonan(String id_permohonan) throws Exception {
		    
		Db db = null;
		String sql = "";
		
		try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT CATATAN_STATUS_ONLINE FROM TBLPPTPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("catatan_status_online", rs.getString("CATATAN_STATUS_ONLINE")==null?"":rs.getString("CATATAN_STATUS_ONLINE"));
		    	  list.addElement(h);
		 }
		   return list;
		 } catch (Exception re) {
			 log.error("Error: ", re);
			 throw re;
			 } finally {
		    if (db != null) db.close();
		    }
	}
	
	@SuppressWarnings("unchecked")
	public static void updateFlag(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try{
	      
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
	    	 	
	    	 	String id_user = (String)data.get("id_user");
	    		String id_permohonan = (String)data.get("id_permohonan"); 
	    		String flag_semakan_online = (String)data.get("flag_semakan_online");
	    		String flag_status_online = "";
	    		//String id_status = "";
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("flag_semakan_online",flag_semakan_online);
	    		if(flag_semakan_online.equals("1"))
	    		{
	    			r.add("CATATANTOLAK_PELULUS","");
	    			r.add("CATATANTOLAK_PENYEMAK","");
	    			r.add("CATATAN_STATUS_ONLINE","");
	    		}	    		
	    		r.add("flag_status_online",flag_status_online);
	    		//r.add("id_status",id_status);
	    		r.add("id_kemaskini",id_user);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		sql = r.getSQLUpdate("tblpptpermohonan");	    		
	    		stmt.executeUpdate(sql);

	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close updateFlag
	
	@SuppressWarnings("unchecked")
	public static void setDataUser(String userid) throws Exception {
		
		dataUser = new Vector();
		
		dataUser.clear();
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
			
				sql =  "SELECT DISTINCT B.ID_JAWATAN ";
				sql += " FROM USERS A, USERS_INTERNAL B ";
				sql += " WHERE A.USER_ID = B.USER_ID(+) ";
				sql += " AND A.USER_ID = '"+userid+"'"; 
				myLogger.info("get jawatan : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN")== null?"":rs.getString("ID_JAWATAN"));
					dataUser.addElement(h);
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)	db.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void setCheckExistLot(String idPermohonan,String lot,String id_hakmilik) throws Exception{
		
		checkExistLot = new Vector();
		
		Db db = null;
		checkExistLot.clear();
		String sql = "";
		String noLOT = lot.trim();
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT B.ID_HAKMILIK FROM TBLPPTPERMOHONAN A, TBLPPTHAKMILIK B" +
					  " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN" +
					  " AND A.ID_PERMOHONAN = '"+idPermohonan+"'";
					  if(id_hakmilik!="" && id_hakmilik!=null){
						  sql += " AND B.ID_HAKMILIK != '"+id_hakmilik+"'";
					  }
				sql += " AND UPPER(B.NO_LOT) = '" + noLOT.toUpperCase() + "' ";
				
				//System.out.println("sql[checkExistLot] : "+sql);
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK")==null?"":rs.getString("ID_HAKMILIK"));
					checkExistLot.addElement(h);
					bil++;	
				}
			//return list;
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if(db != null) db.close();
		}
		}//close setDataListKertas
	
	@SuppressWarnings("unchecked")
	public static void updateCatatan(Hashtable data) throws Exception {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	    	
	    	db = new Db();
	    	 conn = db.getConnection();
	    	 conn.setAutoCommit(false);
	    	 Statement stmt = db.getStatement();
	    	 
	    	 String id_user = (String)data.get("id_user");
	    	 String id_hakmilik = (String)data.get("id_hakmilik");
//	    	 String id_permohonan = (String)data.get("id_permohonan");
//	    	 String id_fail = (String)data.get("id_fail");
//	    	 String id_suburusanstatusfailppt = (String)data.get("id_suburusanstatusfailppt");
	    	 String txtCatatan = (String)data.get("txtCatatan");
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_hakmilik", id_hakmilik);
	    	 r.add("CATATAN_STOP_SIASATAN", txtCatatan);	
	    	 r.add("flag_stop_siasatan","Y");
	    	 r.add("tarikh_stop_siasatan",r.unquote("sysdate"));
	    	 r.add("tarikh_kemaskini",r.unquote("sysdate"));
		     r.add("id_kemaskini",id_user);
	    	 sql = r.getSQLUpdate("Tblppthakmilik");
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
	public static Vector getDataStopSiasatan(String id_hakmilik) throws Exception {
		    
		Db db = null;
		String sql = "";
		
		try {
		     db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT CATATAN_STOP_SIASATAN FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"+id_hakmilik+"'";

		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;

		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("CATATAN_STOP_SIASATAN", rs.getString("CATATAN_STOP_SIASATAN")==null?"":rs.getString("CATATAN_STOP_SIASATAN"));
		    	  list.addElement(h);
		 }
		   return list;
		 } catch (Exception re) {
			 log.error("Error: ", re);
			 throw re;
			 } finally {
		    if (db != null) db.close();
		    }
	}//close getDataStopSiasatan
	
	@SuppressWarnings("unchecked")
	public static void updateStatus(String id_permohonan,String id_user, String status) throws Exception{
			
		  Db db = null;
		  String sql = "";
	    
		  try{
	      
			  	db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		
	    		
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
	
	public Hashtable<String,String> getPermohonanPPT(String id) throws Exception {
		Hashtable<String,String> hash = null;
		Db db = null;
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				r.add("p.id_permohonan");
				r.add("p.id_fail");
				r.add("f.id_suburusan");		
				r.add("f.id_fail",r.unquote("p.id_fail"));
				//r.add("n.id_negeri",r.unquote("f.id_negeri"));
				r.add("p.id_Permohonan",id);
		
				sql = r.getSQLSelect("tblpfdfail f,tblpptpermohonan p");
		
				ResultSet rs = stmt.executeQuery(sql);
		
				while(rs.next()) {
					hash = new Hashtable();
					hash.put("idPermohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					hash.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					hash.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
	
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		}finally {
			if(db != null) db.close();
		}
		return hash;
		
	}//close list PPT
	
	public Hashtable<String,String> getPermohonanPPK(String id) throws Exception {
		Hashtable<String,String> hash = null;
		Db db = null;
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				r.add("p.id_permohonan");
				r.add("p.id_fail");
				r.add("f.id_suburusan");		
				r.add("f.id_fail",r.unquote("p.id_fail"));
				//r.add("n.id_negeri",r.unquote("f.id_negeri"));
				r.add("p.id_Permohonan",id);
		
				sql = r.getSQLSelect("tblpfdfail f,tblppkpermohonan p");
		
				ResultSet rs = stmt.executeQuery(sql);
		
				while(rs.next()) {
					hash = new Hashtable();
					hash.put("idPermohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					hash.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					hash.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
	
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		}finally {
			if(db != null) db.close();
		}
		return hash;
		
	}//close list pohon2
	
	public Hashtable<String,String> getPermohonan(String id) throws Exception {
		Hashtable<String,String> hash = null;
		Db db = null;
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
		
				r.add("f.id_suburusan");		
				r.add("p.id_permohonan");
				r.add("p.id_fail");
				r.add("f.id_fail",r.unquote("p.id_fail"));
				//r.add("n.id_negeri",r.unquote("f.id_negeri"));
				r.add("p.id_permohonan",id);	
				sql = r.getSQLSelect("tblpfdfail f,tblpermohonan p");		
				
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					hash = new Hashtable();
					hash.put("idPermohonan", rs.getString("id_permohonan")==null?"":rs.getString("id_permohonan"));
					hash.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					hash.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
	
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
		}finally {
			if(db != null) db.close();
		}
		return hash;
		
	}//close list pohon2
	
	//penambahbaikan yati -v7
	@SuppressWarnings("unchecked")
	public void setDataPermohonan(String idpermohonan) throws Exception {
		
		Db db = null;
		dataPermohonan.clear();
		String sql = "";
		
		try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT DISTINCT p.jumlah_hakmilik, p.id_permohonan, p.id_status, ";
				sql += " p.flag_jenispermohonan, p.no_permohonan, f.no_fail, f.id_fail, "; 
				sql += " n.nama_negeri, p.tarikh_permohonan_kjp, p.tarikh_permohonan, p.tujuan, "; 
				sql += " k.nama_kementerian, k.id_kementerian, s.keterangan, "; 
				sql += " d.nama_daerah, d.id_daerah, p.tarikh_kehendaki, ";
				sql += " p.tarikh_surat, p.id_agensi, p.no_rujukan_surat, "; 
				sql += " p.flag_peruntukan, p.flag_segera, p.id_mukim, "; 
				sql += " p.flag_jenis_projek, p.tarikh_sahkan, us.nama_suburusan, "; 
				sql += " us.id_suburusan, p.no_rujukan_ptg, p.no_rujukan_ptd, ";
				sql += " p.no_permohonan_online, p.no_rujukan_upt, k.alamat1, "; 
				sql += " k.alamat2, k.alamat3, k.poskod, k.id_negeri, p.flag_semak, "; 
				sql += " n2.id_negeri AS idprojeknegeri, n2.nama_negeri AS nama_negeriprojek";
						//", ut.ulasanjt "; 
				sql += " FROM tblrujsuburusan us, tblpfdfail f, tblrujdaerah d, ";
				sql += " tblrujnegeri n, tblrujkementerian k, tblrujstatus s, "; 
				sql += " tblpptpermohonan p, tblrujnegeri n2";
					//	", tblpptulasanteknikal ut "; 
				sql += " WHERE f.id_fail = p.id_fail AND k.id_kementerian = f.id_kementerian "; 
				sql += " AND n.id_negeri(+) = k.id_negeri AND n2.id_negeri = f.id_negeri ";
				sql += " AND s.id_status(+) = p.id_status AND d.id_daerah = p.id_daerah "; 
				sql += " AND us.id_suburusan(+) = f.id_suburusan ";
						//" AND p.id_permohonan(+)= ut.id_permohonan ";
				sql += " AND p.id_permohonan = '"+idpermohonan+"'";
				
				System.out.println("***SQL setDataPermohonan = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
		
				while(rs.next()) {
					h = new Hashtable();
					h.put("jumlah_hakmilik", rs.getString("jumlah_hakmilik")==null?"0":rs.getString("jumlah_hakmilik"));
					h.put("no_permohonan_online", rs.getString("no_permohonan_online")==null?"":rs.getString("no_permohonan_online"));
					h.put("flag_jenis_projek", rs.getString("flag_jenis_projek")==null?"":rs.getString("flag_jenis_projek"));
					h.put("nama_negeriprojek", rs.getString("nama_negeriprojek")==null?"":rs.getString("nama_negeriprojek"));
					h.put("flag_semak", rs.getString("flag_semak")==null?"":rs.getString("flag_semak"));
					h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
					h.put("idProjekNegeri", rs.getString("idProjekNegeri")==null?"":rs.getString("idProjekNegeri"));
					h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
					h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
					h.put("idNegeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
					h.put("alamat1", rs.getString("alamat1")==null?"":rs.getString("alamat1"));
					h.put("alamat2", rs.getString("alamat2")==null?"":rs.getString("alamat2"));
					h.put("alamat3", rs.getString("alamat3")==null?"":rs.getString("alamat3"));
					h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
					h.put("idFail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
					h.put("idDaerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
					h.put("idSuburusan", rs.getString("id_suburusan")==null?"":rs.getString("id_suburusan"));
					h.put("idAgensi", rs.getString("id_agensi")==null?"0":rs.getString("id_agensi"));
					h.put("idKementerian", rs.getString("id_kementerian")==null?"":rs.getString("id_kementerian"));
					h.put("noPermohonan", rs.getString("no_permohonan")==null?"<b>BELUM DIHANTAR</b>":rs.getString("no_permohonan"));
					h.put("no_fail", rs.getString("no_fail")==null?"BELUM DISAHKAN":rs.getString("no_fail"));
					h.put("namaNegeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
					h.put("tarikh_permohonan_kjp", rs.getDate("tarikh_permohonan_kjp")==null?"":Format.format(rs.getDate("tarikh_permohonan_kjp")));
					h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
					h.put("tarikh_sahkan", rs.getDate("tarikh_sahkan")==null?"":Format.format(rs.getDate("tarikh_sahkan")));
					h.put("tujuan", rs.getString("tujuan")==null?"":rs.getString("tujuan"));
					h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
					h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
					h.put("daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
					h.put("tarikh_kehendaki", rs.getDate("tarikh_kehendaki")==null?"":Format.format(rs.getDate("tarikh_kehendaki")));
					h.put("tarikh_surat", rs.getDate("tarikh_surat")==null?"":Format.format(rs.getDate("tarikh_surat")));
					h.put("no_rujukan_surat", rs.getString("no_rujukan_surat")==null?"":rs.getString("no_rujukan_surat"));
					h.put("flag_peruntukan", rs.getString("flag_peruntukan")==null?"":rs.getString("flag_peruntukan"));
					h.put("flag_segera", rs.getString("flag_segera")==null?"":rs.getString("flag_segera"));
					h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
					h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
					h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
					h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
				//	h.put("ulasanjt", rs.getString("ulasanjt")==null?"":rs.getString("ulasanjt"));
					
					if(rs.getString("flag_jenispermohonan") != null && rs.getString("flag_jenispermohonan") != ""){
						
						if(rs.getString("flag_jenispermohonan").equals("1")){
							h.put("flag_jenispermohonan","PERMOHONAN ONLINE");
						}else if(rs.getString("flag_jenispermohonan").equals("2")){
							h.put("flag_jenispermohonan","PERMOHONAN KAUNTER");
						}else{
							h.put("flag_jenispermohonan","");
						}
						
					}else{
						h.put("flag_jenispermohonan","");
					}
			
					dataPermohonan.addElement(h);
				}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}
		finally {
			if(db != null) db.close();
		}
		
	}//close setDataPermohonan
	
}//close class
