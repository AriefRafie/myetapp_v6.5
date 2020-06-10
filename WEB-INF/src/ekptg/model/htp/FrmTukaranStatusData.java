package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

public class FrmTukaranStatusData {
	
	private static Logger mylog = Logger.getLogger(ekptg.model.htp.FrmTukaranStatusData.class);
	private static Vector<Hashtable<String, String>> list = new Vector<Hashtable<String, String>>();
	
	public static Vector<Hashtable<String, String>> getList(){
		return list;
	}	
	//semak status 
	public static Vector<Hashtable<String, String>> getList(String noFail, String usid) throws Exception {
		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		String chkDataFail = noFail.trim();
	      
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif, a.seksyen ";
	    		sql += " FROM Tblppkpermohonansimati psm, Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, ";
	    		sql += " Tblppkpemohon pp,tblrujdaerah d, tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE d.id_daerah in ( select distinct u.id_daerahurus from ";
	    		sql += " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= " +usid+ ") ";
	    		sql += " AND psm.id_permohonan = a.id_permohonan ";
	    		sql += " AND a.id_fail = f.id_fail(+) ";
	    		sql += " AND a.id_pemohon = pp.id_pemohon(+) ";
	    		sql += " AND psm.id_simati = p.id_simati ";
	    		sql += " AND a.id_status = s.id_status(+) ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND a.id_daerahmhn = d.id_daerah ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND s.id_status <> '999' ";
	    		sql += " AND a.id_status <> '56' ";
	    		sql += " AND a.seksyen in (8,17) ";
	    		sql += " AND stf.aktif = 1 ";
	    		sql += " AND UPPER(F.NO_FAIL) = '" + chkDataFail.toUpperCase() + "'";
	    		//sql += " AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)";
	    		
	    		ResultSet rs = stmt.executeQuery(sql);	      	
	    		Hashtable h;

	    		// LEVEL   
	    		/*  1 = PERMOHONAN DITERUSKAN 
		     		2 = NOTIS PERBICARAAN 
		      		3 = SELESAI PERBICARAAN 
		      		4 = PERMOHONAN SELESAI 	*/
	  
	    		while (rs.next()) {
	    	
	    			//PENDAFTARAN
	    			if (rs.getString("id_status").equals("8") || rs.getString("id_status").equals("9") || rs.getString("id_status").equals("14")){
	    		 
	    				h = new Hashtable();  
	    				h.put("bil","1");
	    				h.put("level","1");	    		  
	    				h.put("list_keterangan","PERMOHONAN DITERUSKAN");
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_status"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    				list.addElement(h);
	    		  
	    				h = new Hashtable();  
	    				h.put("bil","2");
	    				h.put("level","2");	    		  
	    				h.put("list_keterangan","NOTIS PERBICARAAN");
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_status"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    				list.addElement(h);
	    		  
	    				h = new Hashtable();  
	    				h.put("bil","3");
	    				h.put("level","3");	    		  
	    				h.put("list_keterangan","SELESAI PERBICARAAN");
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_status"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    				list.addElement(h);
	    		  
	    				h = new Hashtable();  
	    				h.put("bil","4");
	    				h.put("level","4");	    		  
	    				h.put("list_keterangan","SELESAI");
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_status"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    				list.addElement(h);
	    		  
	    			}//CLOSE PENDAFTARAN  
	    	  
	    	else
	    	
	    	//KEPUTUSAN PERMOHONAN	
	    	if (rs.getString("ID_STATUS").equals("50") || rs.getString("ID_STATUS").equals("53") || rs.getString("ID_STATUS").equals("151")){
	    		  
	    		h = new Hashtable();  
    		  	h.put("bil","1");
    		  	h.put("level","2");	    		  
    		  	h.put("list_keterangan","NOTIS PERBICARAAN");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
    		  
    		  h = new Hashtable();  
    		  	h.put("bil","2");
    		  	h.put("level","3");	    		  
    		  	h.put("list_keterangan","SELESAI PERBICARAAN");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
    		  
    		  h = new Hashtable();  
    		  	h.put("bil","3");
    		  	h.put("level","4");	    		  
    		  	h.put("list_keterangan","SELESAI");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
		
	    	}//CLOSE KEPUTUSAN PERMOHONAN
	    	  
	    	else
	    		
	    	//NOTIS PERBICARAAN	
	    	if (rs.getString("ID_STATUS").equals("18") || rs.getString("ID_STATUS").equals("44") || rs.getString("ID_STATUS").equals("175") || rs.getString("ID_STATUS").equals("172") || rs.getString("ID_STATUS").equals("173") || rs.getString("ID_STATUS").equals("174") || rs.getString("ID_STATUS").equals("176") || rs.getString("ID_STATUS").equals("177")){
	    		
	    		h = new Hashtable();  
    		  	h.put("bil","1");
    		  	h.put("level","3");	    		  
    		  	h.put("list_keterangan","SELESAI PERBICARAAN");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
    		  
    		  h = new Hashtable();  
    		  	h.put("bil","2");
    		  	h.put("level","4");	    		  
    		  	h.put("list_keterangan","SELESAI");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
	    			
	    	}//CLOSE NOTIS PERBICARAAN
	    	  
	    	else
	    		
	    	//KEPUTUSAN PERBICARAAN	
	    	if (rs.getString("ID_STATUS").equals("41") || rs.getString("ID_STATUS").equals("47") || rs.getString("ID_STATUS").equals("25")){
	    			
	    		h = new Hashtable();  
    		  	h.put("bil","1");
    		  	h.put("level","4");	    		  
    		  	h.put("list_keterangan","SELESAI");
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);
	    		
	    	}//CLOSE KEPUTUSAN PERBICARAAN
	    	  
	    	else{
	    		
	    		h = new Hashtable();  
	    		h.put("bil","1");
    		  	h.put("level","4");	 
    		  	h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  	h.put("id_status", rs.getString("id_status"));
	    	  	h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    	  	h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    	  	h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    	  	h.put("list_keterangan","SELESAI");
	    	  	h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  	h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    	  	list.addElement(h);	    		
	    	}
	    	  
	      }
	      
	      
	      return list;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list

	//semak status 
	//public static Vector<Hashtable<String, String>> getListV1(String noFail, String usid)throws Exception {		
	public static Vector<Hashtable<String, String>> getListV1(String noFail, String usid)throws Exception {		
	    Db db = null;
	    //list.clear();
	    Vector vecList = new Vector();
	    String sql = "";
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		String chkDataFail = noFail.trim();
	      
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    		sql += " ,F.ID_MASUK " +
	    				"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    		sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE " ;
	    		sql += " a.id_fail = f.id_fail ";
	    		//sql += " AND a.id_status = s.id_status ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    		sql += " AND F.id_status <> '999' ";
	    		//sql += " AND a.id_status <> '56' ";
	    		//sql += " AND a.seksyen in (8,17) ";
	    		sql += " AND stf.aktif = 1 ";
	    		sql += " AND UPPER(F.NO_FAIL) = '" + chkDataFail.toUpperCase() + "'";
	    		//sql += " AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)";
	    		mylog.info(sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      	
	    		Hashtable h;

		  
	    		while (rs.next()) {
	        		//Vector vStatus = FrmUtilData.getSenaraiStatusMengikutSubUrusan(rs.getString("id_suburusan"));
	        		//for (int i = 0; i < vStatus.size(); i++) {  
	        			h = new Hashtable();  
	    			//	Hashtable<?, ?> hStatus = (Hashtable<?, ?>)vStatus.get(i);  
	    	
	    			//	h.put("bil",i+1);
	    				//h.put("level","4");	 
	    				h.put("level",rs.getString("ID_MASUK"));	 
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				//h.put("id_status", hStatus.get("idsuburusan"));
	    				h.put("id_status", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				//h.put("list_keterangan","SELESAI");
	    				//h.put("keterangan", hStatus.get("keterangan")==null?"":hStatus.get("keterangan"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				//h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
	    				vecList.addElement(h);	    		
	        		//}

	    		}
	    	  
	      return vecList;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list

	public static Hashtable<String,String> getListV2(String noFail, String usid) throws Exception {		
	    Db db = null;
	    //list.clear();
	    Hashtable h = new Hashtable();
	    String sql = "";
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		String chkDataFail = noFail.trim();
	      
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, f.tajuk_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    		sql += " ,f.id_masuk,st.id_suburusanstatus " +
	    				"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    		sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE " ;
	    		sql += " a.id_fail = f.id_fail ";
	    		//sql += " AND a.id_status = s.id_status ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    		sql += " AND f.id_status <> '999' ";
	    		//sql += " AND a.id_status <> '56' ";
	    		//sql += " AND a.seksyen in (8,17) ";
	    		sql += " AND stf.aktif = 1 ";
	    		sql += " AND UPPER(F.NO_FAIL) = '" + chkDataFail.toUpperCase() + "' ";
	    		//sql += " AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)";
//	    		mylog.info(sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      	
	    		//Hashtable h;
		  
	    		while (rs.next()) {
	        		//Vector vStatus = FrmUtilData.getSenaraiStatusMengikutSubUrusan(rs.getString("id_suburusan"));
	        		//for (int i = 0; i < vStatus.size(); i++) {  
	        			h = new Hashtable();  
	    			//	Hashtable<?, ?> hStatus = (Hashtable<?, ?>)vStatus.get(i);  
	    	
	    			//	h.put("bil",i+1);
	    				//h.put("level","4");	 
	    				h.put("level",rs.getString("ID_MASUK"));	 
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				//h.put("id_status", hStatus.get("idsuburusan"));
	    				h.put("id_status", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				//h.put("list_keterangan","SELESAI");
	    				//h.put("keterangan", hStatus.get("keterangan")==null?"":hStatus.get("keterangan"));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("tajuk", rs.getString("tajuk_fail")==null?"":rs.getString("tajuk_fail"));
	    				//vecList.addElement(h);	    		
	        		//}

	    		}	    	  
	      return h;
	      
	    } finally {
	      if (db != null) db.close();
	    }	 
	    
	  }//close list
	
	 //Azam Add
	 public static void hapusfail(Hashtable<String,String> data) throws Exception{		
	    Db db = null;
	    String sql = "";
	    try{	   
	      String id_permohonan = data.get("id_permohonan");
	      String id_fail = data.get("id_fail");
	      String id_masuk = data.get("id_masuk");
	      
	      db = new Db();
		  SQLRenderer r = new SQLRenderer();			  
		  r.update("id_fail",id_fail);
		  r.add("id_status", 999);	
		  r.add("id_kemaskini",id_masuk);
		  r.add("tarikh_kemaskini", r.unquote("sysdate"));
		  sql = r.getSQLUpdate("tblpfdfail");
		  db.getStatement().executeUpdate(sql);
		  
		  /*r.clear();
		  
		  r.update("id_permohonan",id_permohonan);
		  r.add("id_status", 999);	
		  r.add("id_kemaskini",id_masuk);
		  r.add("tarikh_kemaskini", r.unquote("sysdate"));
		  sql = r.getSQLUpdate("Tblppkpermohonan");
		  db.getStatement().executeUpdate(sql);*/
	      
	    }//close try     
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusfail
	
	 
	 public static void tukarKE(Hashtable data) throws Exception{		 
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String lvl = (String)data.get("level");	    
	    int level = 0;
	    
	    if(lvl!=""){
	    	level = Integer.parseInt(lvl);
	    }
	    
	    try{	    	
	    		// LEVEL   
	  	  		/*  1 = PERMOHONAN DITERUSKAN 
	  	      		2 = NOTIS PERBICARAAN 
	  	      		3 = SELESAI PERBICARAAN 
	  	      		4 = PERMOHONAN SELESAI 	*/
	    	
	    	db = new Db();	    	
	
	    	String idPerintah = (String)data.get("id_perintah");  
	   		String id_permohonan = (String)data.get("id_permohonan");  
	    	String id_keputusanpermohonan = (String)data.get("id_keputusanpermohonan"); 
	   		String idperbicaraan = (String)data.get("id_perbicaraan"); 
	   		String id_masuk = (String)data.get("id_masuk");
	   		
	    	if(level==3 || level==4){ 
	    		long id_keputusanPermohonan = 0;
			
	    		if(id_keputusanpermohonan!=""){
	    			id_keputusanPermohonan = Long.parseLong(id_keputusanpermohonan);
	   			}else{
	   				id_keputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
	   			}
		   
	    		if(id_keputusanpermohonan==""){
	    				//add data utk keputusan permohonan
	    			Statement stmt = db.getStatement();
	    			SQLRenderer r = new SQLRenderer();			
	    			r.add("id_keputusanpermohonan", id_keputusanPermohonan);
	    			r.add("id_permohonan", id_permohonan);
	   				r.add("tarikh_hantar_borangB", "");
	    			r.add("tarikh_terima_borangC", "");
	    			r.add("tarikh_hantar_nilaian", "");
	    			r.add("tarikh_terima_nilaian", "");
	    			r.add("jenis_borangC", "");			
	    			r.add("id_Masuk", id_masuk);
	    			r.add("id_Kemaskini", id_masuk);			
	    			r.add("tarikh_Masuk",r.unquote("sysdate"));	
	    			r.add("tarikh_Kemaskini",r.unquote("sysdate"));				
	    			r.add("keputusan_permohonan", "");
	    			r.add("catatan", "");			
	    			sql = r.getSQLInsert("tblppkkeputusanpermohonan");
	    			stmt.executeUpdate(sql);
	    			
	    		}
	    		
	    		long id_perbicaraan = 0;
	    		if(idperbicaraan!=""){
	    			id_perbicaraan = Long.parseLong(idperbicaraan);
	    		}else{
	    			id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
	    		}

	    		long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
				String default_jenispejabat = "22";
				
				if(idperbicaraan==""){
	    				//add data (notis perbicaraan) with bil = 1
	    			Statement stmtx = db.getStatement();
	    			SQLRenderer rx = new SQLRenderer();	
	   				rx.add("id_perbicaraan", id_perbicaraan);
	   				rx.add("masa_bicara", "");
	   				rx.add("alamat_bicara1", "");
	    			rx.add("alamat_bicara2", "");
	    			rx.add("alamat_bicara3", "");
	    			rx.add("poskod", "");			
	    			rx.add("tarikh_bicara", "");
	    			rx.add("tarikh_notis","");
	    			rx.add("id_keputusanpermohonan", id_keputusanPermohonan);
	    			rx.add("bil_bicara", "1"); 			    
	    			rx.add("tarikh_masuk", rx.unquote("sysdate"));		    
	    			rx.add("id_masuk",id_masuk);	
	    			rx.add("id_jenispejabat",default_jenispejabat);		      
	    			sql = rx.getSQLInsert("Tblppkperbicaraan");
	    			stmtx.executeUpdate(sql);
			
	    			//create table notisobmst
	    			Statement stmtMST = db.getStatement();
	    			SQLRenderer rMST = new SQLRenderer();
	    			rMST.add("id_notisobmst", id_notisobmst);		     
	    			rMST.add("id_masuk",id_masuk);
	    			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));	      
	    			sql = rMST.getSQLInsert("Tblppknotisobmst");
	    			stmtMST.executeUpdate(sql);
			      
	    			//create child table
	    			Statement stmt1 = db.getStatement();
	    			SQLRenderer r1 = new SQLRenderer();
	    			r1.add("id_perbicaraan", id_perbicaraan);
	    			r1.add("id_notisobmst", id_notisobmst); 		     
	    			r1.add("id_masuk",id_masuk);
	    			r1.add("tarikh_masuk", r1.unquote("sysdate"));
	    			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
	   				stmt1.executeUpdate(sql);
	   
				}
			      
				long id_perintah = 0;
				if(idPerintah!=""){
	    			id_perintah = Long.parseLong(idPerintah);
	    		}else{
	   				id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
	   			}
			
				if(idPerintah==""){
					long id_bayaran = DB.getNextID("TBLPPKBAYARAN_SEQ"); 
					Statement stmtc = db.getStatement();
	    			SQLRenderer rc = new SQLRenderer();
	    			rc.add("id_perintah",id_perintah);
	    			rc.add("id_perbicaraan",id_perbicaraan);
	    			rc.add("tarikh_perintah", "");
	    			rc.add("id_unitpsk", "");
	    			rc.add("flag_jenis_keputusan", "0");
	    			rc.add("catatan","");
	    			rc.add("id_masuk",id_masuk);
	    			rc.add("tarikh_masuk",rc.unquote("sysdate"));
	    			sql = rc.getSQLInsert("tblppkperintah");		
	    			stmtc.executeUpdate(sql);
			
	    			Statement stmt2 = db.getStatement();
	    			SQLRenderer r2 = new SQLRenderer();
	    			r2.add("id_bayaran",id_bayaran);
	    			r2.add("id_permohonan",id_permohonan);
	    			r2.add("id_jenisbayaran", "24");
	    			r2.add("id_masuk",id_masuk);
	    			r2.add("tarikh_masuk",r2.unquote("sysdate"));					
	    			sql2 = r2.getSQLInsert("tblppkbayaran");
	    			stmt2.executeUpdate(sql2);
			
	    		}
		
	    	}//level 3 & 4
		
	    	if(level==2){ 
	    		long id_keputusanPermohonan = 0;
			
	    		if(id_keputusanpermohonan!=""){
	    			id_keputusanPermohonan = Long.parseLong(id_keputusanpermohonan);
	    		}else{
	    			id_keputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
	    		}
	
	    		if(id_keputusanpermohonan==""){
	    				//add data utk keputusan permohonan
	    			Statement stmt = db.getStatement();
	    			SQLRenderer r = new SQLRenderer();			
	    			r.add("id_keputusanpermohonan", id_keputusanPermohonan);
	    			r.add("id_permohonan", id_permohonan);
	    			r.add("tarikh_hantar_borangB", "");
	    			r.add("tarikh_terima_borangC", "");
	    			r.add("tarikh_hantar_nilaian", "");
	    			r.add("tarikh_terima_nilaian", "");
	    			r.add("jenis_borangC", "");			
	    			r.add("id_Masuk", id_masuk);
	    			r.add("id_Kemaskini", id_masuk);			
	    			r.add("tarikh_Masuk",r.unquote("sysdate"));	
	    			r.add("tarikh_Kemaskini",r.unquote("sysdate"));				
	    			r.add("keputusan_permohonan", "");
	    			r.add("catatan", "");			
	    			sql = r.getSQLInsert("tblppkkeputusanpermohonan");
	    			stmt.executeUpdate(sql);
	    			
	    		}
	    		
	    		long id_perbicaraan = 0;
	    		if(idperbicaraan!=""){
	    			id_perbicaraan = Long.parseLong(idperbicaraan);
	    		}else{
	    			id_perbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
	    		}
		
	    		long id_notisobmst = DB.getNextID("TBLPPKNOTISOBMST_SEQ");
	    		String default_jenispejabat = "22";
				
	    		if(idperbicaraan==""){
	    			//add data (notis perbicaraan) with bil = 1
	    			Statement stmtx = db.getStatement();
	    			SQLRenderer rx = new SQLRenderer();	
	    			rx.add("id_perbicaraan", id_perbicaraan);
	    			rx.add("masa_bicara", "");
	    			rx.add("alamat_bicara1", "");
	    			rx.add("alamat_bicara2", "");
	    			rx.add("alamat_bicara3", "");
	    			rx.add("poskod", "");			
	    			rx.add("tarikh_bicara", "");
	    			rx.add("tarikh_notis","");
	    			rx.add("id_keputusanpermohonan", id_keputusanPermohonan);
	    			rx.add("bil_bicara", "1"); 			    
	    			rx.add("tarikh_masuk", rx.unquote("sysdate"));		    
	    			rx.add("id_masuk",id_masuk);	
	    			rx.add("id_jenispejabat",default_jenispejabat);		      
	    			sql = rx.getSQLInsert("Tblppkperbicaraan");
	    			stmtx.executeUpdate(sql);			
	    				
	    			//create table notisobmst
	    			Statement stmtMST = db.getStatement();
	    			SQLRenderer rMST = new SQLRenderer();
	    			rMST.add("id_notisobmst", id_notisobmst);		     
	    			rMST.add("id_masuk",id_masuk);
	    			rMST.add("tarikh_masuk", rMST.unquote("sysdate"));	      
	    			sql = rMST.getSQLInsert("Tblppknotisobmst");
	    			stmtMST.executeUpdate(sql);
			      
	    			//create child table
	    			Statement stmt1 = db.getStatement();
	    			SQLRenderer r1 = new SQLRenderer();
	    			r1.add("id_perbicaraan", id_perbicaraan);
	    			r1.add("id_notisobmst", id_notisobmst); 		     
	    			r1.add("id_masuk",id_masuk);
	    			r1.add("tarikh_masuk", r1.unquote("sysdate"));
	    			sql = r1.getSQLInsert("Tblppknotisperbicaraan");
	    			stmt1.executeUpdate(sql);
	    			
	    		}
			
	    	}//level 2
		
	    	if(level==1){ 
	    		long id_keputusanPermohonan = 0;
			
	    		if(id_keputusanpermohonan!=""){
	    			id_keputusanPermohonan = Long.parseLong(id_keputusanpermohonan);
	    		}else{
	   				id_keputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
	   			}
	
	    		if(id_keputusanpermohonan==""){
	    			//add data utk keputusan permohonan
	    			Statement stmt = db.getStatement();
	    			SQLRenderer r = new SQLRenderer();			
	    			r.add("id_keputusanpermohonan", id_keputusanPermohonan);
	    			r.add("id_permohonan", id_permohonan);
	    			r.add("tarikh_hantar_borangB", "");
	    			r.add("tarikh_terima_borangC", "");
	    			r.add("tarikh_hantar_nilaian", "");
	    			r.add("tarikh_terima_nilaian", "");
	    			r.add("jenis_borangC", "");			
	    			r.add("id_Masuk", id_masuk);
	    			r.add("id_Kemaskini", id_masuk);			
	    			r.add("tarikh_Masuk",r.unquote("sysdate"));	
	    			r.add("tarikh_Kemaskini",r.unquote("sysdate"));				
	    			r.add("keputusan_permohonan", "");
	    			r.add("catatan", "");			
	    			sql = r.getSQLInsert("tblppkkeputusanpermohonan");
	    			stmt.executeUpdate(sql);
	    			
	    		}
			
	    	}//level 1
		
		} finally {
	    	if (db != null) db.close();   	
		}
	  	
	 }
	 
	 public static void updateANDinsertStatusFail(Hashtable data) throws Exception
	  {
		 
	    Db db = null;

	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    
	    try
	    {
	    	db = new Db();
	    	Statement stmt = db.getStatement();
	    	
	    	String id_permohonan = (String)data.get("id_permohonan");
	    	String id_fail = (String)data.get("id_fail");
		    String id_Suburusanstatusfail = (String)data.get("id_suburusanstatusfail");	      
		    String id_masuk = (String)data.get("id_masuk");
		    String id_suburusanstatus = (String)data.get("id_suburusanstatus");	
		    String id_statusKE = (String)data.get("id_statusKE");
		    
			//tukar status permohonan diteruskan => notis perbicaraan
	      	SQLRenderer rP = new SQLRenderer();				 
	      		rP.update("id_permohonan",id_permohonan);
	      		rP.add("id_status", id_statusKE);	
	      		rP.add("id_kemaskini",id_masuk);
	      		rP.add("tarikh_kemaskini", rP.unquote("sysdate"));
	      	sql = rP.getSQLUpdate("Tblppkpermohonan");
	      	stmt.executeUpdate(sql);
	      	
		    //update suburusanstatusfail lama jadi 0   
			SQLRenderer r6 = new SQLRenderer();
				r6.update("id_Suburusanstatusfail",id_Suburusanstatusfail);	
				r6.add("AKTIF",0);
				r6.add("ID_KEMASKINI",id_masuk);
				r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
			sql2 = r6.getSQLUpdate("tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql2);	 
		      
			
			//insert suburusanstatusfail baru dengan aktif (1)       
			SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
				r5.add("ID_PERMOHONAN",id_permohonan);
				
				r5.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
				
				r5.add("AKTIF",1);
				r5.add("id_Fail",id_fail);

				r5.add("ID_MASUK",id_masuk);
				r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				r5.add("ID_KEMASKINI",id_masuk);
				r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				sql1 = r5.getSQLInsert("tblrujsuburusanstatusfail");
				stmt.executeUpdate(sql1);	

				
	    	} finally {
	    		if (db != null) db.close();
	    	}
	  	}
	 
	 public static Vector getKeputusanPermohonan(String id_permohonan)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT k.id_keputusanpermohonan ";
		      sql +="FROM Tblppkkeputusanpermohonan k ";
		      sql +="WHERE k.id_permohonan = '"+id_permohonan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_keputusanpermohonan", rs.getString("id_keputusanpermohonan")==null?"":rs.getString("id_keputusanpermohonan"));
		    	  
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get idkeputusanpermohonan
	 
	 public static Vector getNotisPerbicaraan(String id_keputusanpermohonan)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT max(k.id_perbicaraan)as id_perbicaraan ";
		      sql +="FROM Tblppkperbicaraan k ";
		      sql +="WHERE k.id_keputusanpermohonan = '"+id_keputusanpermohonan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_perbicaraan", rs.getString("id_perbicaraan")==null?"":rs.getString("id_perbicaraan"));
		    	  
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get idkeputusanpermohonan
	 
	 public static Vector getPerintah(String id_perbicaraan)throws Exception {
		 
		    Db db = null;
		    String sql = "";
		    
		    try {
		    	
		      db = new Db();
		      
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT max(k.id_perintah)as id_perintah ";
		      sql +="FROM Tblppkperintah k ";
		      sql +="WHERE k.id_perbicaraan = '"+id_perbicaraan+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector list = new Vector();
		      
		      Hashtable h = null;
		     
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_perintah", rs.getString("id_perintah")==null?"":rs.getString("id_perintah"));
		    	  
		    	  list.addElement(h);
		    	  
		      }
		      return list;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get idkeputusanpermohonan
	 
	 
}//close class
