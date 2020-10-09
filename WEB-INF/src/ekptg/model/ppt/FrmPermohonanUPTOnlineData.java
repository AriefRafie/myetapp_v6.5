package ekptg.model.ppt;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

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

public class FrmPermohonanUPTOnlineData {
	
	static Logger myLogger = Logger.getLogger(FrmPermohonanUPTOnlineData.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static final Log log = LogFactory.getLog(FrmPermohonanUPTOnlineData.class);
	
	@SuppressWarnings("unchecked")
	private static  Vector listCarian = new Vector();
	@SuppressWarnings("unchecked")
	private static  Vector listDokumen = new Vector();
	@SuppressWarnings("unchecked")
	private  Vector dataPermohonan = new Vector();
	
	
	@SuppressWarnings("unchecked")
	public static  Vector getListCarian(){
		return listCarian;
	}
	@SuppressWarnings("unchecked")
	public Vector getDataPermohonan(){
		return dataPermohonan;
	}
	@SuppressWarnings("unchecked")
	public Vector getListDokumen(){
		return listDokumen;
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getListPemohonlama(String userId,String portal_role,String flag_noti)throws Exception {	 
		
		Db db = null;
		String sql = "";
		try{
			db = new Db();
		    Statement stmt = db.getStatement();
		    /*	sql = "SELECT distinct p.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian, s.keterangan, p.tarikh_masuk, p.flag_semak, ";
		    		sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt, p.flag_status_online, ";
		    		sql +="p.no_permohonan_online ";
		    		sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, ";
		    		sql +="Users u, Users_kementerian uk ";
		    		sql +="WHERE f.id_fail = p.id_fail ";
		    		sql +="AND f.id_suburusan = su.id_suburusan " ;
		    		sql +="AND f.id_kementerian = k.id_kementerian ";
		    		sql +="AND u.user_id = uk.user_id ";
		    		sql +="AND p.id_status = s.id_status ";
		    		sql +="AND u.user_id = p.id_masuk ";
		    		sql +="AND f.id_suburusan in ('51','52','53') ";
		    		sql +="AND NVL(p.flag_jenispermohonan,0) = '1' ";
		    		sql += "AND u.user_id ='"+userId+"' ";
		    		sql +="ORDER by p.tarikh_masuk desc, p.tarikh_kemaskini desc "; */
		    		
		    		//modified 21112011 : Papar senarai kesemua fail by kementerian
		    sql = "SELECT distinct UPPER(p.tujuan) tujuan, p.id_status, p.flag_jenispermohonan, p.id_permohonan, p.no_permohonan" +
		    	" , f.id_fail, f.no_fail, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian" +
		    	" , s.keterangan, p.tarikh_masuk, p.flag_semak, p.flag_semakan_online, "; 
		    sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt" +
		    	" , p.flag_status_online, p.catatan_status_online, ";  //addby matjuju for column p.catatan_status_online, 
		    sql +="p.no_permohonan_online "; 
		    sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, ";
		    sql +="Users u, Users_kementerian uk "; 
		    sql +="WHERE f.id_fail = p.id_fail "; 
		    sql +="AND f.id_suburusan = su.id_suburusan "; 
		    sql +="AND f.id_kementerian = k.id_kementerian "; 
		    sql +="AND u.user_id = uk.user_id "; 
		    sql +="AND p.id_status = s.id_status "; 
		    sql +="AND uk.id_kementerian = k.id_kementerian ";
		    sql +="AND f.id_suburusan in ('51','52','53') "; 
		    sql +="AND u.user_id ='"+userId+"' ";
		    		
		    if(portal_role.equals("online_kjpagensi")){
		    	sql +="AND uk.id_agensi = p.id_agensi ";
		    }
		    sql +="ORDER by p.tarikh_masuk desc, p.tarikh_kemaskini desc ";
		    myLogger.info("LIST PERMOHONAN : " + sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    Vector list = new Vector();
		    Hashtable h = null;
		    int bil = 1;
		    
		    while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    			
		    	h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
		    	h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));
		    	h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"BELUM DIHANTAR":rs.getString("no_permohonan_online"));
		    	h.put("flag_status_online", rs.getString("flag_status_online")== null?"":rs.getString("flag_status_online"));
		    			
		    	h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
		    	h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    	h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    	h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    	h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    		
		    	h.put("flag_semakan_online", rs.getString("flag_semakan_online")== null?"":rs.getString("flag_semakan_online"));
		    	h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online")); //addby matjuju for column p.catatan_status_online, 
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    			
		    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			
		    	if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){		    				
		    		if(rs.getString("id_suburusan").equals("51")){
		    			h.put("suburusan","SEKSYEN 4");
		    		}else if(rs.getString("id_suburusan").equals("52")){
		    			h.put("suburusan","SEKSYEN 8");
		    		}else if(rs.getString("id_suburusan").equals("53")){
		    			h.put("suburusan","PENGAMBILAN SEMENTARA");
		    		}else{
		    			h.put("suburusan","");
		    		}		    				
		    	}else{
		    		h.put("suburusan","");
		    	}
		    			
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
		}catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah Carian ");
		}finally{	//close try 
			if (db != null) db.close();
		}//close finally
		    	
	}//close getListPemohon
		
	public static Vector getListPemohon(String userId,String portal_role,String flag_noti)throws Exception {	 		
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			Statement stmt = db.getStatement();			
			
			sql = " SELECT UI.ID_JAWATAN FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID AND U.USER_ID = '"+userId+"' ";
			//System.out.println("*** PRINTLN JAWATAN = "+sql);
			ResultSet rs1 = stmt.executeQuery(sql);
				
			String id_jawatan = "";
			while (rs1.next()) {
	    		id_jawatan = rs1.getString("ID_JAWATAN")== null?"":rs1.getString("ID_JAWATAN");	    			
			}
			//modified 21112011 : Papar senarai kesemua fail by kementerian
		    sql = "SELECT distinct UPPER(p.tujuan) tujuan, p.id_status, p.flag_jenispermohonan, p.id_permohonan, p.no_permohonan" +
		    	" , f.id_fail, f.no_fail, p.tarikh_permohonan_kjp, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian" +
		    	" , s.keterangan, p.tarikh_masuk, p.flag_semak, p.flag_semakan_online, "; 
		    sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt" +
		    	" , p.flag_status_online, p.catatan_status_online, p.CATATANTOLAK_PELULUS,p.CATATANTOLAK_PENYEMAK,";  //addby matjuju for column p.catatan_status_online, 
		    sql +="p.no_permohonan_online "; 
		    sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, ";
		    sql +="Users u, Users_kementerian uk "; 
		    sql +="WHERE f.id_fail = p.id_fail "; 
		    sql +="AND f.id_suburusan = su.id_suburusan "; 
		    sql +="AND f.id_kementerian = k.id_kementerian "; 
		    sql +="AND u.user_id = uk.user_id "; 
		    sql +="AND p.id_status = s.id_status "; 
		    sql +="AND uk.id_kementerian = k.id_kementerian ";
		    sql +="AND f.id_suburusan in ('51','52','53') "; 
		    sql +="AND u.user_id ='"+userId+"' ";
		    		
		    if(portal_role.equals("online_kjpagensi")){
		    	sql +="AND uk.id_agensi = p.id_agensi ";
		    }
		    
		    myLogger.info(" -------- FLAG NOTI : "+flag_noti);
		    if(flag_noti.equals("Y")){
		    	sql += " and f.id_fail in (";
		    	if (id_jawatan.equals("24")) {
					sql += " SELECT F.ID_FAIL " +
							" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
							" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
							" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
							" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userId + "' " +
							/*" AND P.FLAG_STATUS_ONLINE = 'Y' " +*/
							" AND P.FLAG_STATUS_ONLINE = 1 " +
							" UNION ALL " +
							" SELECT F.ID_FAIL " +
							" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
							" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
							" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
							" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userId + "' " +
							" AND P.FLAG_SEMAKAN_ONLINE = 4 ";
				
		    	} else if (id_jawatan.equals("9")) {
					sql += " SELECT f.ID_FAIL "
					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
					        +" users u, users_kementerian uk "
					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
					        +" AND p.flag_semakan_online = 1 ";
				
		    	} else if (id_jawatan.equals("4")) {
					sql += " SELECT f.ID_FAIL "
					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
					        +" users u, users_kementerian uk "
					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
					        +" AND p.flag_semakan_online = 2 ";
				} else { 
					sql += " SELECT f.ID_FAIL "
					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
					        +" users u, users_kementerian uk "
					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
					        +" AND p.flag_semakan_online = 2 "; 
				}
		    	sql += ") ";
		    }		    
		    
		    sql +="ORDER by ";		    
		    myLogger.info(":::: id_jawatan : " + id_jawatan);
		    /*
		    if(id_jawatan.equals("24"))
		    {
		    	sql += "nvl(p.flag_status_online,0) desc, nvl(p.flag_semakan_online,0) desc, ";
		    }
		    if(id_jawatan.equals("9") || id_jawatan.equals("4"))
		    {
		    	sql += "nvl(p.flag_semakan_online,0) desc, ";
		    }*/
		    sql += "p.tarikh_permohonan DESC, p.tarikh_masuk DESC, p.tarikh_kemaskini DESC ";
		    //nvl(p.flag_status_online,0) desc, nvl(p.flag_semakan_online,0) desc, 
		    myLogger.info("getListPemohon:sql=" + sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    Vector list = new Vector();
		    Hashtable h = null;
		    int bil = 1;

		    while (rs.next()) {
		    	h = new Hashtable();
		    	h.put("bil", bil);
		    			
		    	h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
		    	h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));
		    	h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"BELUM DIHANTAR":rs.getString("no_permohonan_online"));
		    	h.put("flag_status_online", rs.getString("flag_status_online")== null?"":rs.getString("flag_status_online"));
		    			
		    	h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
		    	h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
		    	h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
		    	h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
		    	h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
		    	h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
		    	h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
		    	h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    		
		    	h.put("flag_semakan_online", rs.getString("flag_semakan_online")== null?"":rs.getString("flag_semakan_online"));
		    	h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online")); //addby matjuju for column p.catatan_status_online, 
		    	
		    	h.put("CATATANTOLAK_PELULUS", rs.getString("CATATANTOLAK_PELULUS")== null?"":rs.getString("CATATANTOLAK_PELULUS")); //addby matjuju for column p.catatan_status_online, 
		    	h.put("CATATANTOLAK_PENYEMAK", rs.getString("CATATANTOLAK_PENYEMAK")== null?"":rs.getString("CATATANTOLAK_PENYEMAK")); //addby matjuju for column p.catatan_status_online, 
		    	
		    	
		    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
		    	h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
		    	h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
		    	
		    	h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    	h.put("tarikh_permohonan_kjp", rs.getDate("tarikh_permohonan_kjp")==null?"":Format.format(rs.getDate("tarikh_permohonan_kjp")));
		    			
		    	if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){		    				
		    		if(rs.getString("id_suburusan").equals("51")){
		    			h.put("suburusan","SEKSYEN 4");
		    		}else if(rs.getString("id_suburusan").equals("52")){
		    			h.put("suburusan","SEKSYEN 8");
		    		}else if(rs.getString("id_suburusan").equals("53")){
		    			h.put("suburusan","PENGAMBILAN SEMENTARA");
		    		}else{
		    			h.put("suburusan","");
		    		}		    				
		    	}else{
		    		h.put("suburusan","");
		    	}
		    			
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
		}catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah Carian ");
		}finally{	//close try 
			if (db != null) db.close();
		}//close finally
		    	
	}//close getListPemohon
	
	@SuppressWarnings("unchecked")
	public Vector setJabatanTeknikal(String abc)throws Exception {
			
		Vector listJabatanTeknikal = new Vector();
				
				Db db = null;
			    String sql = "";
			    
			    try {
			    	
			    	db = new Db();
		    		Statement stmt = db.getStatement();
			    	
			    		sql = " SELECT UT.ID_JABATANTEKNIKAL, UT.ID_PERMOHONAN, UPPER(JT.NAMA_JABATAN) AS NAMA_JABATAN, ";
			    		sql += " TO_CHAR(UT.TARIKH_TERIMAJT,'DD/MM/YYYY') AS TARIKH_TERIMA_ULASAN, UT.KEPUTUSANJT, ";
			    		sql += " (CASE WHEN UT.KEPUTUSANJT = 1 THEN 'SOKONG' ";
			    		sql += " WHEN UT.KEPUTUSANJT = 1 THEN 'TIDAK SOKONG' ELSE '' END) AS KEPUTUSAN, ";
			    		sql += " UPPER(UT.ULASANJT) AS ULASAN FROM TBLPPTULASANTEKNIKAL UT, TBLPPTJABATANTEKNIKAL JT ";
			    		sql += " WHERE UT.ID_JABATANTEKNIKAL = JT.ID_JABATANTEKNIKAL ";
			    		sql += " AND UT.ID_PERMOHONAN = '"+abc+"' ";
			    		
			    		//System.out.println("*** PRINTLN setJabatanTeknikal = "+sql);

						ResultSet rs = stmt.executeQuery(sql);
						
			    		Hashtable h;
			    		int bil = 1;
			    		
			    		while (rs.next()) {
			    			h = new Hashtable();
			    			h.put("bil", bil);
			    			h.put("ID_JABATANTEKNIKAL", rs.getString("ID_JABATANTEKNIKAL")== null?"":rs.getString("ID_JABATANTEKNIKAL"));
			    			h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")== null?"":rs.getString("ID_PERMOHONAN"));
			    			h.put("NAMA_JABATAN", rs.getString("NAMA_JABATAN")== null?"":rs.getString("NAMA_JABATAN"));
			    			h.put("KEPUTUSAN", rs.getString("KEPUTUSAN")== null?"":rs.getString("KEPUTUSAN"));
			    			h.put("ULASAN", rs.getString("ULASAN")== null?"":rs.getString("ULASAN"));
			    			h.put("TARIKH_TERIMA_ULASAN", rs.getString("TARIKH_TERIMA_ULASAN")== null?"":rs.getString("TARIKH_TERIMA_ULASAN"));
			    			listJabatanTeknikal.addElement(h);	
							bil++;
						}
						//System.out.println(" setJabatanTeknikal :"+listJabatanTeknikal);
						return listJabatanTeknikal;
					}catch (Exception re) {
						throw re;
					} finally {
						if (db != null)
							db.close();
					}
	}//close setJabatanTeknikal
	

	@SuppressWarnings("unchecked")
	public static void setListCarian(String carianStatus,String carianJenisHM,String carianNegeri,String carianNoFail,String carianTarikhMohon,
			String carianUrusan,String carianBilMohon,String carianNamaPB,String carianNoPB,String carianNoHM,String carianNoLot,
			String carianTujuan,String userId,String portal_role,String flag_noti)throws Exception {
		
	    Db db = null;
	    listCarian.clear();
	    String sql = "";	    
	    if(carianTarikhMohon!=""){
	    	carianTarikhMohon = "to_date('" + carianTarikhMohon + "','dd/MM/yyyy')";
	    }	    
	    
	    try {	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		//modified 21112011 : Papar senarai kesemua fail by kementerian
	    		
	    		sql = " SELECT UI.ID_JAWATAN FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID AND U.USER_ID = '"+userId+"' ";
	    		//System.out.println("*** PRINTLN JAWATAN = "+sql);
				ResultSet rs1 = stmt.executeQuery(sql);
				
	    		String id_jawatan = "";
	    		while (rs1.next()) {
	    			id_jawatan = rs1.getString("ID_JAWATAN")== null?"":rs1.getString("ID_JAWATAN");	    			
				}
	    		
	    		sql = "SELECT distinct UPPER(p.tujuan) tujuan, p.id_status, p.flag_jenispermohonan, p.id_permohonan, p.no_permohonan" +
	    		    	" , f.id_fail, f.no_fail, p.tarikh_permohonan_kjp, p.tarikh_permohonan, su.nama_suburusan, k.nama_kementerian" +
	    		    	" , s.keterangan, p.tarikh_masuk, p.flag_semak, p.flag_semakan_online, "; 
	    		    sql +="p.no_rujukan_ptg, p.no_rujukan_ptd, f.id_suburusan, p.tarikh_kemaskini, p.no_rujukan_upt" +
	    		    	" , p.flag_status_online, p.catatan_status_online, p.CATATANTOLAK_PELULUS, p.CATATANTOLAK_PENYEMAK,";  //addby matjuju for column p.catatan_status_online, 
	    		    sql +="p.no_permohonan_online "; 
	    		    sql +="FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, ";
	    		    sql +="Users u, Users_kementerian uk "; 
	    		    sql +="WHERE f.id_fail = p.id_fail "; 
	    		    sql +="AND f.id_suburusan = su.id_suburusan "; 
	    		    sql +="AND f.id_kementerian = k.id_kementerian "; 
	    		    sql +="AND u.user_id = uk.user_id "; 
	    		    sql +="AND p.id_status = s.id_status "; 
	    		    sql +="AND uk.id_kementerian = k.id_kementerian ";
	    		    sql +="AND f.id_suburusan in ('51','52','53') "; 
	    		    sql +="AND u.user_id ='"+userId+"' ";
	    		    		
	    		    if(portal_role.equals("online_kjpagensi")){
	    		    	sql +="AND uk.id_agensi = p.id_agensi ";
	    		    }
	    		    
	    		    myLogger.info(" -------- FLAG NOTI : "+flag_noti);
	    		    if(flag_noti.equals("Y"))
	    		    {
	    		    	
	    		    	sql += " and f.id_fail in (";
	    		    	if (id_jawatan.equals("24")) {
	    					sql += " SELECT F.ID_FAIL " +
	    							" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
	    							" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
	    							" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
	    							" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userId + "' " +
	    							" AND P.FLAG_STATUS_ONLINE = 'Y' AND P.FLAG_STATUS_ONLINE = 1 " +
	    							" UNION ALL " +
	    							" SELECT F.ID_FAIL " +
	    							" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
	    							" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
	    							" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
	    							" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userId + "' " +
	    							" AND P.FLAG_SEMAKAN_ONLINE = 4 ";
	    				} else if (id_jawatan.equals("9")) {
	    					sql += " SELECT f.ID_FAIL "
	    					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
	    					        +" users u, users_kementerian uk "
	    					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
	    					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
	    					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
	    					        +" AND p.flag_semakan_online = 1 ";
	    				} else if (id_jawatan.equals("4")) {
	    					sql += " SELECT f.ID_FAIL "
	    					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
	    					        +" users u, users_kementerian uk "
	    					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
	    					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
	    					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
	    					        +" AND p.flag_semakan_online = 2 ";
	    				} else { 
	    					sql += " SELECT f.ID_FAIL "
	    					        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
	    					        +" users u, users_kementerian uk "
	    					        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
	    					        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
	    					        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userId + "' "
	    					        +" AND p.flag_semakan_online = 2 "; 
	    				}
	    		    	sql += ") ";
	    		    }
	    		
	    		//NO FAIL
				if (!carianNoFail.equals("")) {
					sql += " AND (UPPER(f.no_fail) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
					" OR UPPER(p.no_rujukan_ptg) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
					" OR UPPER(p.no_permohonan_online) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
					" OR UPPER(p.no_rujukan_upt) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
					" OR UPPER(p.no_rujukan_ptd) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%'))";
				}//close if pemohon 
		
				if (!carianBilMohon.equals("")) {
					sql += " AND UPPER(p.no_permohonan) LIKE UPPER('%" + carianBilMohon.toUpperCase() + "%') ";
				}				
				//TARIKH
				if (!carianTarikhMohon.equals("")) {
					sql = sql + " AND UPPER(p.tarikh_permohonan_kjp) = "+carianTarikhMohon;
				}//close tarikh  
	    		//STATUS
				if (!carianStatus.equals("")) {
					sql = sql + " AND UPPER(s.id_status) = '"+carianStatus+"' ";
				}//close status
				//Urusan
				if (!carianUrusan.equals("")) {
					sql = sql + " AND UPPER(f.id_suburusan) = '"+carianUrusan+"'";
				}//close Urusan
				//Negeri Projek
				if (!carianNegeri.equals("")) {
					sql = sql + " AND UPPER(f.id_negeri) = '"+carianNegeri+"'";
				}//close Urusan
				//Nama Projek
				if (!carianTujuan.equals("")) {
					sql = sql + " AND UPPER(p.tujuan) LIKE UPPER('%"+carianTujuan+"%')";
				}//close Urusan
				//No hakmilik
		    	if (!carianNoHM.equals("")) {
		    		sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
		    		" WHERE UPPER(NO_HAKMILIK) LIKE UPPER('%"+carianNoHM+"%'))";
			    }
		    	//Nama PB
		    	if (!carianNamaPB.equals("")) {
					sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB " +
					" WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
			        " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+carianNamaPB.trim()+"'|| '%') ) ";								
				}
		    	//No PB
				if (!carianNoPB.equals("")) {
					sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "+
					" WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
			        " AND UPPER(PB.NO_PB) LIKE UPPER('%"+carianNoPB+"%'))";									
				}
				//no_lot/pt
		    	if (!carianNoLot.equals("")) {
				    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
				    " WHERE UPPER(NO_LOT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%') " +
				    " OR UPPER(NO_PT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%'))";					   
				}				
		    	//Id jenishakmilik
		    	if (!carianJenisHM.equals("")) {
				    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_JENISHAKMILIK = '"+carianJenisHM+"')";
		    	}
		    	
		    	sql +="ORDER by p.tarikh_permohonan DESC, p.tarikh_masuk DESC, p.tarikh_kemaskini DESC ";
				
				myLogger.info("sql carian : "+sql);
	    		ResultSet rs = stmt.executeQuery(sql);
	    		
	    		//System.out.println("*** CARIAN OUTPUT = "+sql);
	      
	    		Hashtable h;
	    		int bil = 1;
	    		while (rs.next()) {
	    			h = new Hashtable();
	    			h.put("bil", bil);
	    			
			    	h.put("tujuan", rs.getString("tujuan")== null?"":rs.getString("tujuan"));
			    	h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));
	    			h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"BELUM DIHANTAR":rs.getString("no_permohonan_online"));
	    			h.put("flag_status_online", rs.getString("flag_status_online")== null?"":rs.getString("flag_status_online"));
	    			h.put("CATATANTOLAK_PELULUS", rs.getString("CATATANTOLAK_PELULUS")== null?"":rs.getString("CATATANTOLAK_PELULUS")); //addby matjuju for column p.catatan_status_online, 
			    	h.put("CATATANTOLAK_PENYEMAK", rs.getString("CATATANTOLAK_PENYEMAK")== null?"":rs.getString("CATATANTOLAK_PENYEMAK")); //addby matjuju for column p.catatan_status_online, 
			    	
	    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));
	    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));
	    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));
	    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    			h.put("no_permohonan", rs.getString("no_permohonan")== null?"":rs.getString("no_permohonan"));
	    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));
	    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));
	    			h.put("status", rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	    			
	    			h.put("flag_semakan_online", rs.getString("flag_semakan_online")== null?"":rs.getString("flag_semakan_online"));
			    	h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online"));
			    	h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")== null?"":rs.getString("no_rujukan_ptg"));
	    			h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")== null?"":rs.getString("no_rujukan_ptd"));
	    			h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")== null?"":rs.getString("no_rujukan_upt"));
	    			
	    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    			h.put("tarikh_permohonan_kjp", rs.getDate("tarikh_permohonan_kjp")==null?"":Format.format(rs.getDate("tarikh_permohonan_kjp")));
	    			
	    			h.put("catatan_status_online", rs.getString("catatan_status_online")== null?"":rs.getString("catatan_status_online")); //addby zulfazdliabuas@gmail.com for bila buat carian error : $list.catatan_status_online
	    			
	    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
	    				
	    				if(rs.getString("id_suburusan").equals("51")){
	    					h.put("suburusan","SEKSYEN 4");
	    				}else if(rs.getString("id_suburusan").equals("52")){
	    					h.put("suburusan","SEKSYEN 8");
	    				}else if(rs.getString("id_suburusan").equals("53")){
	    					h.put("suburusan","PENGAMBILAN SEMENTARA");
	    				}else{
	    					h.put("suburusan","");
	    				}
	    					
	    			}else{
	    				h.put("suburusan","");
	    			}
	    			
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
	    			
	    			listCarian.addElement(h);
	    			bil++;
	    			
	      }//close while

	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah Carian ");
	    }finally {
	    	if (db != null) db.close();
	    }
	    
	  }//close setlist
	
	
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
				
				//System.out.println("***SQL setDataPermohonan = "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				myLogger.info("setDataPermohonan====="+sql);
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
	
	@SuppressWarnings("unchecked")
	public static String simpanPendaftaran(Hashtable data) throws Exception
	  {
		
		Connection conn = null;
	    Db db = null;
	    
	    String sql = "";
	    String output="";
	    
	    try
	    {
	    	db = new Db();
	    	conn = db.getConnection();
			conn.setAutoCommit(false);
	    	Statement stmt = db.getStatement();
	    	SQLRenderer r = new SQLRenderer();
	    	
	    	//user login id
	    	String id_user = (String)data.get("id_user");
	    	
	    	//Table pptpermohonan
	    	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");	       
	    	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	String no_rujukan_upt = (String)data.get("no_rujukan_upt");
	    	
	    	String tarikh_permohonan_kjp = (String)data.get("tarikh_permohonan_kjp");
	    	String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    	String tarikh_kehendaki = (String)data.get("tarikh_kehendaki");
	    	String tarikh_surat = (String)data.get("tarikh_surat");	       
	    	String flag_peruntukan = (String)data.get("flag_peruntukan");
	    	String flag_segera = (String)data.get("flag_segera");
	    	String tujuan = (String)data.get("tujuan");
	    	String rujukan_surat = (String)data.get("rujukan_kementerian");	      
	    	String id_agensi = (String)data.get("agensi");  
	    	String id_daerah = (String)data.get("daerah");
	    	String ulasanjt = (String)data.get("ulasanjt");
	    	
	    	String jenis_projek = (String)data.get("jenis_projek");
	    	
	    	String jumlahHakmilik = (String)data.get("jumlahHakmilik");
	    	
	    	//table pfdfail
	    	String id_suburusan = (String)data.get("suburusan");
	    	String id_projek_negeri = (String)data.get("projek_negeri");
	    	String id_kementerian = (String)data.get("kementerian");

	    	//convert string to date
	    	String TP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
	    	String TK = "to_date('" + tarikh_kehendaki + "','dd/MM/yyyy')";
	    	String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	      
	    	
	    	//status "PENDAFTARAN"
	    	String status = "8";
	    	
	    	//status "PERMOHONAN ONLINE"
	    	String flag_jenisserahan = "1";
	      
	    	//generate no permohonan "this_year-000001
	    	long id_permohonan = DB.getNextID("TBLPPTPERMOHONAN_SEQ");    
	    	long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
	    	long idSenaraiSemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
	    	
//	    	Date now = new Date();
//	    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
//	    	String tahun = formatter.format(now);
//	    	int thn = Integer.parseInt(tahun);
//
//	    	//generate no permohonan	    	
//	    	String seq = String.format("%06d",File.getSeqNo(1,1,0,0,0,false,false,thn,0));	    	
//	    	String noPermohonan = tahun+"-"+seq;
	      
	    	
	    	r.add("id_fail",idFail);
	    	r.add("id_suburusan", id_suburusan);
	    	r.add("id_negeri", id_projek_negeri);
	    	r.add("id_kementerian", id_kementerian);
	    	r.add("id_seksyen", "1");  // yati tambah utk save id_seksyen 6/2/2018
	    	r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_masuk",id_user);
			sql = r.getSQLInsert("tblpfdfail");
	    	stmt.executeUpdate(sql);
	      
	    	r.clear();
	    	
	     	r.add("id_permohonan",id_permohonan);
	     	//r.add("no_permohonan",noPermohonan);
	     	r.add("id_fail",idFail);
	     	r.add("flag_peruntukan", flag_peruntukan);
	     	r.add("flag_segera", flag_segera);
	     	r.add("flag_jenis_projek", jenis_projek);
	     	r.add("tujuan", tujuan);
	     	r.add("flag_semak", "0");
	     	r.add("no_rujukan_surat", rujukan_surat);
	     	//r.add("tarikh_permohonan", r.unquote(TP));
	     	r.add("tarikh_permohonan_kjp", r.unquote(TP));
	     	r.add("tarikh_kehendaki", r.unquote(TK));
	     	r.add("tarikh_surat", r.unquote(TS));
	     	r.add("id_daerah", id_daerah);
	     	r.add("id_status", status);
	     	r.add("id_agensi", id_agensi);
	     	r.add("flag_jenispermohonan", flag_jenisserahan);
	     	r.add("no_rujukan_ptg",no_rujukan_ptg);
	     	r.add("no_rujukan_ptd",no_rujukan_ptd);
	     	r.add("no_rujukan_upt",no_rujukan_upt);
	     	r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_masuk",id_user);
			r.add("jumlah_hakmilik",jumlahHakmilik);
			//r.add("ulasanjt",ulasanjt);
	     	sql = r.getSQLInsert("tblpptpermohonan");
	     	stmt.executeUpdate(sql);
	  
	     	r.clear();
	     	
	     	r.add("id_senaraisemak",idSenaraiSemak);
	     	r.add("id_permohonan", id_permohonan);
	    	r.add("tarikh_masuk",r.unquote("sysdate"));
			r.add("id_masuk",id_user);
	     	sql = r.getSQLInsert("tblpptsenaraisemak");
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
	     	
	     	r.clear();
	     	
	     	r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
	     	r.add("ID_PERMOHONAN",id_permohonan);
	     	r.add("ID_FAIL",idFail);
	     	r.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
	     	r.add("AKTIF",1);
			r.add("ID_MASUK",id_user);
			r.add("ID_KEMASKINI",id_user);
			r.add("TARIKH_MASUK",r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
			stmt.executeUpdate(sql);	
	     	
	     	
	     	output = ""+id_permohonan;
	    	
	     	conn.commit();	
		      
	    }/*catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data ");
	    }*/
	    finally {
	      if (db != null) db.close();
	    }
	    
	    return output;
	   
	  }//close simpanPendaftaran
	
	
	//sahkan (status change to "138") *PERMOHONAN ONLINE
	@SuppressWarnings("unchecked")
	public static void hantarPermohonan(Hashtable data) throws Exception {
		
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
	    		String id_kementerian = (String)data.get("id_kementerian");

		 		//-- Generate no Fail "ONLINE/881/kod_kementerian/seq_fail
		 		String kod_kementerian = "";
		 		int seq_id_seksyen = 1;
		 		int seq_id_urusan = 17;
	      
		 		//generate bil permohonan "YEAR/SEQ"
		 		Date now = new Date();
		    	SimpleDateFormat formatter =  new SimpleDateFormat("yyyy");
		    	String tahun = formatter.format(now);
		    	int thn = Integer.parseInt(tahun);
    	
		    	String seqBIL = String.format("%06d",File.getSeqNo(1,1,0,0,0,false,false,thn,0));	    	
		    	String bilPermohonan = tahun+"-"+seqBIL;
		 		
		 		
		 		SQLRenderer rMT = new SQLRenderer();				 
		 		rMT.add("id_kementerian");
		 		rMT.add("kod_kementerian");				      
		 		rMT.add("id_kementerian",id_kementerian);				      
		 		sql = rMT.getSQLSelect("Tblrujkementerian");
		 		ResultSet rsMT = stmt.executeQuery(sql);
		 		while (rsMT.next()) {
		 			kod_kementerian = rsMT.getString("kod_kementerian");
		 		}
	      
		 		//no permohonan online	
		    	int seq = File.getSeqNo(seq_id_seksyen,seq_id_urusan,Utils.parseInt(id_kementerian),0);	
		 		String noPermohonanOnline = "ONLINE/881/"+kod_kementerian+"/"+seq;
		 		
		 		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", idpermohonan);
	    		r.add("id_status", "138");	
	    		r.add("no_permohonan_online", noPermohonanOnline);	
	    		r.add("no_permohonan",bilPermohonan);
	    		r.add("tarikh_permohonan_kjp",r.unquote("sysdate"));
		 		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		 		r.add("id_kemaskini",id_user);
		 		sql = r.getSQLUpdate("tblpptpermohonan");
		 		stmt.executeUpdate(sql);

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
	    	
	  }//close hantarPermohonan
	
	
	public static void simpanCatatanTolak(String id_permohonan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    
	    	 db = new Db();
	    	 Statement stmt = db.getStatement();
	    	 
	    	 SQLRenderer r = new SQLRenderer();
	    	 r.update("id_permohonan", id_permohonan);
	    	 r.add("flag_status_online", "");
	    	 r.add("catatan_status_online", "");	    	 
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
	public static Vector getListFailExpired(String userId)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT p.id_permohonan, p.no_permohonan_online, p.tarikh_permohonan, su.nama_suburusan, p.tarikh_masuk, sysdate, p.tarikh_kemaskini, ";
		    		sql += " (SELECT TO_DATE(sysdate) - TO_DATE(p.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan ";
		    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u, Users_kementerian uk ";
		    		sql += " WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan ";
		    		sql += " AND f.id_kementerian = k.id_kementerian ";
		    		sql += " AND u.user_id = uk.user_id ";
		    		sql += " AND p.id_status = s.id_status ";
		    		sql += " AND u.user_id = p.id_masuk ";
		    		sql += " AND f.id_suburusan in ('51','52','53') ";
		    		sql += " AND NVL(p.flag_jenispermohonan,0) = '1' ";
		    		sql += " AND p.id_status = '138' ";
		    		sql += " AND (TO_DATE(sysdate) - TO_DATE(p.tarikh_masuk))+1  >= 21 ";
		    		sql += " AND u.user_id ='"+userId+"' ";
		    		sql += " ORDER by bilHari desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		    
		    			h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"":rs.getString("no_permohonan_online"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
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
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListFailExpired
	
	@SuppressWarnings("unchecked")
	public static Vector getListPenarikanExpired(String userId)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT pbk.id_penarikanbalik, f.no_fail, pbk.no_penarikanbalik, ";
		    		sql += " p.id_permohonan, p.no_permohonan_online, p.tarikh_permohonan, su.nama_suburusan, p.tarikh_masuk, sysdate, p.tarikh_kemaskini,  ";
		    		sql += " (SELECT TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan  ";
		    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u, Users_kementerian uk, Tblpptpenarikanbalik pbk "; 
		    		sql += " WHERE f.id_fail = p.id_fail  ";
		    		sql += " AND f.id_suburusan = su.id_suburusan  ";
					sql += " AND f.id_kementerian = k.id_kementerian  ";
					sql += " AND u.user_id = uk.user_id  ";
					sql += " AND p.id_status = s.id_status  ";
					sql += " AND u.user_id = pbk.id_masuk  ";
					sql += " AND f.id_suburusan in ('52')   ";
					sql += " AND pbk.id_permohonan = p.id_permohonan  ";
					sql += " AND pbk.flag_online = '2'   ";
					sql += " AND (TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk))+1  >= 14 ";
					sql += " AND u.user_id ='"+userId+"'  ";
					sql += " ORDER by bilHari desc  ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		  
		    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));	
		    			h.put("no_penarikanbalik", rs.getString("no_penarikanbalik")== null?"":rs.getString("no_penarikanbalik"));
		    			h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"":rs.getString("no_permohonan_online"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
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
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPenarikanExpired
	
	@SuppressWarnings("unchecked")
	public static Vector getListPembatalanExpired(String userId)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT pbk.id_pembatalan, f.no_fail, pbk.no_pembatalan, ";
		    		sql += " p.id_permohonan, p.no_permohonan_online, p.tarikh_permohonan, su.nama_suburusan, p.tarikh_masuk, sysdate, p.tarikh_kemaskini,  ";
		    		sql += " (SELECT TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan  ";
		    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u, Users_kementerian uk, Tblpptpembatalan pbk "; 
		    		sql += " WHERE f.id_fail = p.id_fail  ";
		    		sql += " AND f.id_suburusan = su.id_suburusan  ";
					sql += " AND f.id_kementerian = k.id_kementerian  ";
					sql += " AND u.user_id = uk.user_id  ";
					sql += " AND p.id_status = s.id_status  ";
					sql += " AND u.user_id = pbk.id_masuk  ";
					sql += " AND f.id_suburusan in ('52')   ";
					sql += " AND pbk.id_permohonan = p.id_permohonan  ";
					sql += " AND pbk.flag_online = '2'   ";
					sql += " AND (TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk))+1  >= 14 ";
					sql += " AND u.user_id ='"+userId+"'  ";
					sql += " ORDER by bilHari desc  ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);		    			
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		  
		    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));	
		    			h.put("no_pembatalan", rs.getString("no_pembatalan")== null?"":rs.getString("no_pembatalan"));
		    			h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"":rs.getString("no_permohonan_online"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
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
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPembatalanExpired
	
	@SuppressWarnings("unchecked")
	public static Vector getListFailExpiredUnit(String id_negeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT f.id_fail, p.id_permohonan, k.nama_kementerian, p.no_permohonan_online, p.tarikh_permohonan, su.nama_suburusan, p.tarikh_masuk, sysdate, p.tarikh_kemaskini, ";
		    		sql += " (SELECT TO_DATE(sysdate) - TO_DATE(p.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan, ";
		    		sql += " s.id_status, p.flag_jenispermohonan, p.flag_segera ";
		    		sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k ";
		    		sql += " WHERE f.id_fail = p.id_fail ";
		    		sql += " AND f.id_suburusan = su.id_suburusan "; 
		    		sql += " AND f.id_kementerian = k.id_kementerian(+) ";
		    		sql += " AND p.id_status = s.id_status ";
		    		sql += " AND f.id_suburusan in ('51','52','53') ";
		    		sql += " AND NVL(p.flag_jenispermohonan,0) = '1' ";
		    		sql += " AND p.id_status = '138' ";
		    		sql += " AND (TO_DATE(sysdate) - TO_DATE(p.tarikh_masuk))+1  >= 21 ";
		    		sql += " AND f.id_negeri = '"+id_negeri+"' ";
		    		sql += " ORDER by bilHari desc ";

		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector list = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;
	
		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));	
		    			h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));	
		    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));	
		    			h.put("flag_segera", rs.getString("flag_segera")== null?"":rs.getString("flag_segera"));	
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));	
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		    
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));	
		    			h.put("no_permohonan_online", rs.getString("no_permohonan_online")== null?"":rs.getString("no_permohonan_online"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
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
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListFailExpired
	
	@SuppressWarnings("unchecked")
	public static Vector getListPenarikanExpiredUnit(String id_negeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT f.id_fail, p.id_permohonan, pbk.id_penarikanbalik, k.nama_kementerian, p.no_permohonan_online, p.tarikh_permohonan, "; 
		    		sql += " su.nama_suburusan, pbk.tarikh_masuk as tarikh_masuk_pbk, p.tarikh_masuk, sysdate, p.tarikh_kemaskini, f.no_fail, pbk.no_penarikanbalik,  ";
					sql += " (SELECT TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan,  ";
					sql += " s.id_status, p.flag_jenispermohonan, p.flag_segera ";
					sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptpenarikanbalik pbk "; 
					sql += " WHERE f.id_fail = p.id_fail  ";
					sql += " AND f.id_suburusan = su.id_suburusan   ";
					sql += " AND f.id_kementerian = k.id_kementerian(+)  ";
					sql += " AND p.id_status = s.id_status  ";
					sql += " AND f.id_suburusan in ('52')  ";
					sql += " AND pbk.id_permohonan = p.id_permohonan ";
					sql += " AND pbk.flag_online = '2' ";
					sql += " AND (TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk))+1  >= 14  ";
					sql += " AND f.id_negeri = '"+id_negeri+"'"; 
					sql += " ORDER by bilHari desc ";
					//myLogger.info("getListPenarikanExpiredUnit:sql"+sql);
		    		ResultSet rs = stmt.executeQuery(sql);
		    		Vector listpbk = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));	
		    			h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));	
		    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));	
		    			h.put("flag_segera", rs.getString("flag_segera")== null?"":rs.getString("flag_segera"));	
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));	
		    			h.put("id_penarikanbalik", rs.getString("id_penarikanbalik")== null?"":rs.getString("id_penarikanbalik"));	
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		    
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));	
		    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));	
		    			h.put("no_penarikanbalik", rs.getString("no_penarikanbalik")== null?"":rs.getString("no_penarikanbalik"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
		    					h.put("suburusan","PENGAMBILAN SEMENTARA");
		    				}else{
		    					h.put("suburusan","");
		    				}
		    					
		    			}else{
		    				h.put("suburusan","");
		    			}
		    			
		    			listpbk.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return listpbk;
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPenarikanExpiredUnit
	
	
	@SuppressWarnings("unchecked")
	public static Vector getListPembatalanExpiredUnit(String id_negeri)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try{
		    	
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		     
		    		sql =  " SELECT DISTINCT f.id_fail, p.id_permohonan, pbk.id_pembatalan, k.nama_kementerian, p.no_permohonan_online, p.tarikh_permohonan, "; 
		    		sql += " su.nama_suburusan, pbk.tarikh_masuk as tarikh_masuk_pbk, p.tarikh_masuk, sysdate, p.tarikh_kemaskini, f.no_fail, pbk.no_pembatalan,  ";
					sql += " (SELECT TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk)+1 from dual)as bilHari, su.id_suburusan,  ";
					sql += " s.id_status, p.flag_jenispermohonan, p.flag_segera ";
					sql += " FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Tblpptpembatalan pbk "; 
					sql += " WHERE f.id_fail = p.id_fail  ";
					sql += " AND f.id_suburusan = su.id_suburusan   ";
					sql += " AND f.id_kementerian = k.id_kementerian(+)  ";
					sql += " AND p.id_status = s.id_status  ";
					sql += " AND f.id_suburusan in ('52')  ";
					sql += " AND pbk.id_permohonan = p.id_permohonan ";
					sql += " AND pbk.flag_online = '2' ";
					sql += " AND (TO_DATE(sysdate) - TO_DATE(pbk.tarikh_masuk))+1  >= 14  ";
					sql += " AND f.id_negeri = '"+id_negeri+"'"; 
					sql += " ORDER by bilHari desc ";

					//myLogger.info("getListPembatalanExpiredUnit:sql="+sql);
					ResultSet rs = stmt.executeQuery(sql);
		    		Vector listpbk = new Vector();
		      
		    		Hashtable h = null;
		    		int bil = 1;

		    		while (rs.next()) {
		    			h = new Hashtable();
		    			h.put("bil", bil);	
		    			h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
		    			h.put("id_status", rs.getString("id_status")== null?"":rs.getString("id_status"));	
		    			h.put("flag_jenispermohonan", rs.getString("flag_jenispermohonan")== null?"":rs.getString("flag_jenispermohonan"));	
		    			h.put("id_suburusan", rs.getString("id_suburusan")== null?"":rs.getString("id_suburusan"));	
		    			h.put("flag_segera", rs.getString("flag_segera")== null?"":rs.getString("flag_segera"));	
		    			h.put("id_fail", rs.getString("id_fail")== null?"":rs.getString("id_fail"));	
		    			h.put("id_pembatalan", rs.getString("id_pembatalan")== null?"":rs.getString("id_pembatalan"));	
		    			h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));		    
		    			h.put("nama_kementerian", rs.getString("nama_kementerian")== null?"":rs.getString("nama_kementerian"));	
		    			h.put("no_fail", rs.getString("no_fail")== null?"":rs.getString("no_fail"));	
		    			h.put("no_pembatalan", rs.getString("no_pembatalan")== null?"":rs.getString("no_pembatalan"));	
		    			h.put("nama_suburusan", rs.getString("nama_suburusan")== null?"":rs.getString("nama_suburusan"));	
		    			h.put("bilHari", rs.getString("bilHari")== null?"":rs.getString("bilHari"));	
		    			if(rs.getString("id_suburusan") != null && rs.getString("id_suburusan") != ""){
		    				
		    				if(rs.getString("id_suburusan").equals("51")){
		    					h.put("suburusan","SEKSYEN 4");
		    				}else if(rs.getString("id_suburusan").equals("52")){
		    					h.put("suburusan","SEKSYEN 8");
		    				}else if(rs.getString("id_suburusan").equals("53")){
		    					h.put("suburusan","PENGAMBILAN SEMENTARA");
		    				}else{
		    					h.put("suburusan","");
		    				}
		    					
		    			}else{
		    				h.put("suburusan","");
		    			}
		    			
		    			listpbk.addElement(h);
		    			bil++;
		    	  
		    		}//close while
		    		return listpbk;
		    	} catch (Exception re) {
		    		log.error("Error: ", re);
		    		throw re;
		    		}//close try 
		    	finally{
		    		if (db != null) db.close();
		    	}//close finally
		    	
	}//close getListPembatalanExpiredUnit
	
	//PENAMBAHAN YATI
	
	@SuppressWarnings("unchecked")
	public static void update(Hashtable data) throws Exception {
		
		//System.out.println("**** data : "+data);

		
	    Db db = null;
	    String sql = "";
	    long id_senaraisemak = 0;
	   
	    try{
	      
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
	    	 	
	    	 	String id_user = (String)data.get("id_user");
	    	 	String no_rujukan_ptg = (String)data.get("no_rujukan_ptg");
	    	 	
	    	 	String jumlahHakmilik = (String)data.get("jumlahHakmilik");
	    	 	String no_rujukan_ptd = (String)data.get("no_rujukan_ptd");
	    	 	String no_rujukan_upt = (String)data.get("no_rujukan_upt");
	    	 	
	    		String id_permohonan = (String)data.get("id_permohonan"); 
	    		String id_fail = (String)data.get("id_fail"); 
	    		String id_check = (String)data.get("id_senaraisemak"); 
	      
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
	      
	    		//checkbox seksyen 4
	    		String semak_10 = (String)data.get("semak10"); 
	    		String semak_20 = (String)data.get("semak20");
	     
	    		String id_daerah = (String)data.get("daerah");
	    		String id_projekNegeri = (String)data.get("projeknegeri");
	    		String id_agensi = (String)data.get("agensi");
	    		String id_kementerian = (String)data.get("kementerian");

	    		String tarikh_lengkap = (String)data.get("tarikh_lengkap");
	    		String txdTarikhPermohonan = (String)data.get("txdTarikhPermohonan");
	    		String tarikh_permohonan_kjp = (String)data.get("tarikh_permohonan_kjp");
	    		String tujuan = (String)data.get("tujuan");
	    		String rujukan_kementerian = (String)data.get("rujukan_kementerian");
	    		String tarikh_hendak = (String)data.get("tarikh_hendak");
	    		String tarikh_surat = (String)data.get("tarikh_surat");
	    		String flagPeruntukan = (String)data.get("flag_peruntukan");
	    		String flagSegera = (String)data.get("flag_segera");
	    		String ulasanjt = (String)data.get("ulasanjt");
		  		  
	    		String flagJenisProjek = (String)data.get("jenis_projek");
	    		
	    
				//untuk n9 shj [27042011]
				String txtTujuanBI = (String)data.get("txtTujuanBI");
	    		
				String TPKJP = "to_date('" + tarikh_permohonan_kjp + "','dd/MM/yyyy')";
	    		String TP = "to_date('" + txdTarikhPermohonan + "','dd/MM/yyyy')";
	    		String TH = "to_date('" + tarikh_hendak + "','dd/MM/yyyy')";
	    		String TS = "to_date('" + tarikh_surat + "','dd/MM/yyyy')";
	    		String TL = "to_date('" + tarikh_lengkap + "','dd/MM/yyyy')";
	    		//if(!tarikh_lengkap.equals("")){
	    		
	    		//}
	    		
	    		
	    		
		  
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_permohonan", id_permohonan);
	    		r.add("tarikh_kehendaki", r.unquote(TH));
	    		r.add("tarikh_surat", r.unquote(TS));
	    		//r.add("tarikh_permohonan", r.unquote(TP));
	    		r.add("tarikh_permohonan_kjp", r.unquote(TPKJP));
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
	   	      	r.add("jumlah_hakmilik",jumlahHakmilik);
	   	   //untuk n9 shj [27042011]
		     	r.add("tujuan_bi",txtTujuanBI);
	    		sql = r.getSQLUpdate("tblpptpermohonan");
	    		myLogger.info("sql update permohonan : "+sql);
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
	    			//checkbutton seksyen 4
	    			rCK.add("i.semak10", semak_10);
	    			rCK.add("i.semak20", semak_20);
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
	    		
	    		      
	    		
	    		 
	    		
//	    		if(id_check.equals(""))
//				{
//	    			id_senaraisemak = DB.getNextID(db, "TBLPPTSENARAISEMAK_SEQ");
//				}
//				else
//				{
//					id_senaraisemak = Long.parseLong(id_check);
//				}
//	    		
//	    			SQLRenderer rCK4 = new SQLRenderer();
//	    			
//	    			if(id_check.equals(""))
//					{
//						rCK4.add("j.id_senaraisemak", id_senaraisemak);
//					}
//					else
//					{
//						rCK4.update("j.id_senaraisemak", id_senaraisemak);
//					}
//
//	    			//rCK4.add("j.id_senaraisemak",id_senaraisemak);
//	    	
//	    			rCK4.add("j.id_permohonan", id_permohonan);
//	    			rCK4.add("j.semak1", semak_10);
//	    			rCK4.add("j.semak2", semak_20);
//	    			rCK4.add("tarikh_kemaskini",rCK4.unquote("sysdate"));
//	    		    rCK4.add("id_kemaskini",id_user);
//	    		    if(id_check.equals(""))
//					{
//					sql = rCK4.getSQLInsert("tblpptsenaraisemak j");
//					stmt.executeUpdate(sql);
//					}
//	    			else {
//	    				
//	    				sql = rCK4.getSQLUpdate("tblpptsenaraisemak j");
//	    				stmt.executeUpdate(sql);
//	    			}
	    		
	      
	    		
	    } catch (Exception re) {
	    	log.error("Error: ", re);
	    	//System.out.println("SQL LIST ::: "+sql);
	    	throw re;
	    	}
	    finally {
	    if (db != null) db.close();
	    }
	  }//close update
	
	
	
}//close class
