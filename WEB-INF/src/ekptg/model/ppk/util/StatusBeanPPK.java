package ekptg.model.ppk.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.utils.status.IStatus;

public class StatusBeanPPK implements IStatus {
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.util.StatusBeanPPK.class);
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;  	
	private String sql = "";
	private Connection conn = null;
	private Db db = null;
	private SQLRenderer r = null;			
	private Statement stmt = null;

	
	@Override
	public Tblrujsuburusanstatusfail getStatusFailPermohonanAktif(String idFail,String idPermohonan) throws Exception {		
		Tblrujsuburusanstatusfail susf = null;
	    try {	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();	    		
	    	sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    	sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    	sql += " ,f.id_masuk,st.id_suburusanstatus,stf.url_catatan" +
	    			",TO_CHAR(stf.tarikh_masuk,'dd/mm/yyyy') tarikh_masuk " +
	    			",TO_CHAR(stf.tarikh_kemaskini,'dd/mm/yyyy') tarikh_selesai " +
	    			"FROM tblpermohonan a, tblpfdfail f, tblrujstatus s, ";
	    	sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    	sql += " WHERE " ;
	    	sql += " a.id_fail = f.id_fail ";
	    	sql += " AND st.id_status = s.id_status ";
	    	sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    	sql += " AND stf.id_permohonan = a.id_permohonan ";
	    	sql += " AND stf.id_fail = a.id_fail ";
	    	sql += " AND F.id_status <> '999' ";
	    	sql += " AND stf.aktif = 1 ";
	    	sql += " AND stf.id_permohonan = '" + idPermohonan + "'";
	    	sql += " AND stf.id_fail = '" + idFail + "'";
	    	//myLog.info("getStatusFailPermohonanAktif :sql="+sql);
	    	ResultSet rs = stmt.executeQuery(sql);	      				 
	    	while (rs.next()) {
	    		susf = new Tblrujsuburusanstatusfail();
	    		susf.setIdSuburusanstatusfail(rs.getLong("id_suburusanstatusfail"));
	    		susf.setIdFail(rs.getLong("id_fail"));
	    		susf.setIdPermohonan(rs.getLong("id_permohonan"));
	    		susf.setIdSuburusanstatus(rs.getLong("id_suburusanstatus"));
	    		susf.setUrl(Utils.isNull(rs.getString("catatan")));
	    		//susf.setTarikhKemaskini(rs.getString("TARIKH_SELESAI").equals("01/01/1900")?new Date():rs.getDate("TARIKH_SELESAI"));

	    	}	    	  
	    	return susf;
	    	
	    } finally {
	      if (db != null) db.close();
	    }	    
	    
	  }//close Tblrujsuburusanstatusfail		
	/* Dibuat Oleh	: 2010/06/16 Mohamad Rosli 
	 * Kemaskini 	: 2017/12/21 Salin dari Class ekptg.model.htp.FrameUtilData 
	 * Tujuan	  	: Keluarkan id Sub Urusan (id_suburusanstatus)
	 * Pra syarat 	: Assign langkah mengikut suburusan
	 * Parameter  	: langkah, id_suburusan, operation sql untuk langkah(op) 
	 * return  
	*/
	@Override
	public String getIdSuburusanStatusByLangkah(String langkah, String idsuburusan, String op) 
		throws Exception {
		String returnValue = "";
		try {
			db = new Db();
		    SQLRenderer r = new SQLRenderer();
		    r.add("rsus.id_suburusanstatus");
		    r.add("rsus.id_suburusan",r.unquote(idsuburusan));
		    r.add("rsus.langkah",langkah,op);
		    sql = r.getSQLSelect("tblrujsuburusanstatus rsus");
		      
		    Statement stmt = db.getStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    while (rs.next()) {
		    	  returnValue = rs.getString("id_suburusanstatus");
		    }
		    return returnValue;
		} finally {
			if (db != null) db.close();
		}
		
	}
	
	public boolean isSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus) throws Exception {
		boolean returnValue = false;
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("ID_SUBURUSANSTATUSFAIL");
		  r.add("ID_SUBURUSANSTATUS",rsus.getIdSuburusanstatus());
		  r.add("ID_PERMOHONAN",rsus.getIdPermohonan());
		  r.add("ID_FAIL",rsus.getIdFail());
	      sql = r.getSQLSelect("TBLRUJSUBURUSANSTATUSFAIL");		      
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = true;	}
	      
	    }catch(Exception e){
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.toString());	      
	    } finally {
	      if (db != null) db.close();
	    }		
	    return returnValue;
	
	}
	
	public boolean isSubUrusanStatusPermohonanAktif(Tblrujsuburusanstatusfail rsus) throws Exception {
		boolean returnValue = false;
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("ID_SUBURUSANSTATUSFAIL");
		  //r.add("ID_SUBURUSANSTATUS",rsus.getIdSuburusanstatus());
		  r.add("ID_PERMOHONAN",rsus.getIdPermohonan());
		  r.add("ID_FAIL",rsus.getIdFail());
	      sql = r.getSQLSelect("TBLRUJSUBURUSANSTATUSFAIL");		      
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = true;	}
	      
	    }catch(Exception e){
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.toString());	      
	    } finally {
	      if (db != null) db.close();
	    }		
	    return returnValue;
	
	}
	
	public void hapusSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus) throws Exception {
	    try {
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql ="DELETE FROM TBLRUJSUBURUSANSTATUSFAIL WHERE " +
            	" ID_PERMOHONAN ="+rsus.getIdPermohonan()+"" +
            	" AND ID_SUBURUSANSTATUS = " +rsus.getIdSuburusanstatus()+
            	" AND ID_FAIL = " +rsus.getIdFail()+
            	"";
            myLog.info(sql);
            stmt.executeQuery(sql);
            conn.commit();
	      
	    }catch(Exception e){
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.toString());
			
		} finally {
	      if (db != null) db.close();
	    }		
	
	}
	
	@Override
	public void kemaskiniStatus(Tblrujsuburusanstatusfail s) throws Exception {
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_PERMOHONAN",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
			r.update("ID_SUBURUSANSTATUS",r.unquote(String.valueOf(s.getIdSuburusanstatus())));
			r.add("URL",s.getUrl());
			r.add("ID_KEMASKINI",s.getIdKemaskini());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			myLog.info(sql);
			stmt.executeUpdate(sql);				
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				 getIHTP().getErrorHTML("Rollback error:"+se2.toString());
				
			}
			 getIHTP().getErrorHTML("Ralat Kemaskini Status:"+se.toString());
			    
		}catch(Exception ex){
			 conn.rollback();
			 getIHTP().getErrorHTML(ex.toString());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		  
	
	}	

	public String getIdStatusSah(String kod) throws Exception {
		String returnValue = "0";
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("ID_STATUSSAH");
		  r.add("STATUS_SAH",kod);
	      sql = r.getSQLSelect("TBLHTPRUJSTATUSAH");		      
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { returnValue = rs.getString("ID_STATUSSAH");	}
	      
	    }catch(Exception e){
			getIHTP().getErrorHTML(e.toString());	      
	    } finally {
	      if (db != null) db.close();
	    }		
	    return returnValue;
	
	}

	public String kemaskiniSimpanStatusAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru) 
		throws Exception {
		String idSuburusanstatusfail = "0";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			r.update("aktif", "1");
			r.add("aktif",s.getAktif());
			r.add("id_kemaskini", sBaru.getIdKemaskini());
			r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
//			IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
//			r = new SQLRenderer();		  
//			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
//			r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
//			r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
//			r.add("aktif",sBaru.getAktif());
//			r.add("url",sBaru.getUrl());
//			r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
//			r.add("tarikh_Masuk", r.unquote("sysdate"));
//			r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
//			r.add("tarikh_kemaskini", r.unquote("sysdate"));
//			r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
//			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
//			//myLog.info(sql);
//		    stmt.executeUpdate(sql);
			sBaru.setIdPermohonan(s.getIdPermohonan());
			sBaru.setIdFail(s.getIdFail());
			sBaru.setTarikhMasuk("sysdate");
			sBaru.setTarikhKemaskini("sysdate");
		    idSuburusanstatusfail = simpanStatusAktif(sBaru);
	    
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat kemaskini status fail:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		
		return idSuburusanstatusfail;
		
	}	
	@Override
	public String simpanStatusAktif(Tblrujsuburusanstatusfail susf) throws Exception {
		String idSuburusanstatusfail = "0";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				
			idSuburusanstatusfail = String.valueOf(DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));		  
			r = new SQLRenderer();		  
			r.add("id_suburusanstatusfail", idSuburusanstatusfail);
			r.add("id_fail",r.unquote(String.valueOf(susf.getIdFail())));
			r.add("id_permohonan", String.valueOf(susf.getIdPermohonan()));
			r.add("id_suburusanstatus", r.unquote(String.valueOf(susf.getIdSuburusanstatus())));
			r.add("aktif",susf.getAktif());
			r.add("url",susf.getUrl());
			r.add("id_masuk", String.valueOf(susf.getIdMasuk()));
			r.add("tarikh_Masuk", r.unquote(susf.getTarikhMasukStr()));
			r.add("id_kemaskini", String.valueOf(susf.getIdMasuk()));
			r.add("tarikh_kemaskini", r.unquote(susf.getTarikhKemaskiniStr()));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			myLog.info(sql);
		    stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat kemasukan status fail:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
//			if (conn != null) conn.close();
	
		}		
		return idSuburusanstatusfail;
		
	}
	/**
	 Sebagai jumlah bilangan tugasan dan senarai fail/ maklumat yang berkaitan
	 dengan tugasan yang perlu dibuat.
	 */
	@Override
	public Vector<Hashtable<String, String>> getInfoStatusPermohonan(String idUrusan,String idSubUrusan,String langkah)
		throws Exception{
		Vector<Hashtable<String, String>> senaraiMaklumat = new Vector<Hashtable<String, String>>();
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		sql = "SELECT " +
	    		//	"distinct f.no_fail,F.ID_MASUK, a.id_status,  ";
	    		" S.KETERANGAN,ST.ID_SUBURUSANSTATUS "+
	    		" ,STF.ID_SUBURUSANSTATUSFAIL, STF.AKTIF,ST.ID_SUBURUSAN,STF.URL CATATAN" +
	    		" ,TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    		" ,TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI" +
	    		" ,STF.ID_PERMOHONAN,STF.ID_FAIL " +
	    	    " FROM " +
//	    	    " Tblpermohonan a, Tblpfdfail f,  ";
//	    		" TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS ST, TBLHTPRUJSUBURUSANSTATUSFAIL STF "+
	    		" TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STF "+
	    		" WHERE "+
	    		//sql += " a.id_fail = f.id_fail ";
	    		" ST.ID_STATUS = S.ID_STATUS "+
	    		" AND STF.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS "+
	    		//" AND stf.id_permohonan = a.id_permohonan ";
	    		//sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    		//sql += " AND F.id_status <> '999' ";
	    		" AND ST.LANGKAH = '"+langkah+"'"+
	    		" AND STF.aktif = 1 "+
	    		//" AND STF.ID_FAIL = '" + idHakmilik + "'" +
	    				"";
	    		if(!idUrusan.equals("")){
	    			sql+=" AND ST.ID_SUBURUSAN IN " +
	    					"(SELECT ID_SUBURUSAN FROM TBLRUJSUBURUSAN " +
	    					" WHERE ID_URUSAN ='"+idUrusan+"')";
	    		}
	    		if(!idSubUrusan.equals("")){
	    			sql+=" AND ST.ID_SUBURUSAN = "+idSubUrusan;	    		
	    		}
	    		myLog.info("getInfoStatusPermohonan 2:sql="+sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      			  
				int bil = 1;
			    Hashtable<String,String> h = null;
			    while (rs.next()) {
	        			h = new Hashtable<String, String>();  
	    				//h.put("level",rs.getString("ID_MASUK"));	 
	    	    		h.put("bil", String.valueOf(bil));
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_suburusan", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    				//h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				//h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("tarikhSelesai", Utils.isNull(rs.getString("TARIKH_SELESAI")));
	    				senaraiMaklumat.addElement(h);
	    				bil++;

	    		}
	    		
	      //return h;
	    } finally {
	      if (db != null) db.close();
	    }	    
	     return senaraiMaklumat;
	     
	}//close list	
	
	/**
	 Sebagai jumlah bilangan tugasan dan senarai fail/ maklumat yang berkaitan
	 dengan tugasan yang perlu dibuat.
	 */
	@Override
	public Vector<Hashtable<String, String>> getStatusPermohonanByIndividu(String idUrusan,String idUser,String langkah)
		throws Exception{
		Vector<Hashtable<String, String>> senaraiMaklumat = new Vector<Hashtable<String, String>>();
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		sql = "SELECT " +
	    		" TO_CHAR (P.TARIKH_MASUK, 'DD/MM/YYYY') TARIKH_MASUK"+
	    		" ,TO_CHAR (P.TARIKH_MOHON_ONLINE, 'DD/MM/YYYY') TARIKH_MOHON_ONLINE,P.SEKSYEN "+
	    		" , UPPER (PM.NAMA_PEMOHON) AS NAMA_PEMOHON "+
	    		" ,S.KETERANGAN  "+
	    		" ,SM.NAMA_SIMATI"+
	    		" ,ST.ID_SUBURUSANSTATUS,ST.ID_SUBURUSAN "+
	    		" ,STF.ID_SUBURUSANSTATUSFAIL, STF.AKTIF,STF.URL CATATAN" +
	    		" ,TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    		" ,TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI" +
	    		" ,STF.ID_PERMOHONAN,STF.ID_FAIL " +
	    	    " FROM " +
	    	    " TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPEMOHON PM   "+
	    		" ,TBLPPKPERMOHONANSIMATI PSM,TBLPPKSIMATI SM "+
	    		" ,TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STF "+
	    		" WHERE "+
	    		" F.ID_FAIL = P.ID_FAIL "+
	    		" AND F.ID_FAIL = STF.ID_FAIL "+
	    		" AND P.ID_PERMOHONAN = STF.ID_PERMOHONAN "+
	    		" AND ST.ID_STATUS = S.ID_STATUS "+
	    		" AND STF.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS "+
	    		" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
	    		" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
	    		" AND PM.ID_PEMOHON = P.ID_PEMOHON  "+
	    		" AND ST.LANGKAH = '"+langkah+"'"+
	    		" AND STF.aktif = 1 "+
	    		//" AND STF.ID_FAIL = '" + idHakmilik + "'" +
	    				"";
	    		if(!idUrusan.equals("")){
	    			sql+=" AND ST.ID_SUBURUSAN IN " +
	    					"(SELECT ID_SUBURUSAN FROM TBLRUJSUBURUSAN " +
	    					" WHERE ID_URUSAN IN ("+idUrusan+") )";
	    		}
	    		if(!idUser.equals("")){
	    			sql+=" AND F.ID_MASUK = "+idUser;	    		
	    		}
	    		myLog.info("getStatusPermohonanByIndividu:sql="+sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      			  
				int bil = 1;
			    Hashtable<String,String> h = null;
			    while (rs.next()) {
	        			h = new Hashtable<String, String>();  
	    				//h.put("level",rs.getString("ID_MASUK"));	 
	    	    		h.put("bil", String.valueOf(bil));
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_suburusan", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    				//h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				//h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
		    		    h.put("nama_pemohon", rs.getString("NAMA_PEMOHON")==null?"":rs.getString("NAMA_PEMOHON"));
		    		    h.put("nama_simati", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
	    		    	h.put("seksyen", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
		    		    h.put("status", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    		    	//h.put("status", rs.getString("STATUS")==null?"":rs.getString("STATUS")); senarai deraf

	    		    	h.put("tarikhmasuk", rs.getString("TARIKH_MASUK")==null?"":rs.getString("TARIKH_MASUK"));
		    		    h.put("tarikh_mohon_online", rs.getString("TARIKH_MOHON_ONLINE")==null?"":rs.getString("TARIKH_MOHON_ONLINE"));
	    				h.put("tarikhSelesai", Utils.isNull(rs.getString("TARIKH_SELESAI")));
	    				senaraiMaklumat.addElement(h);
//	    				  h.put("tarikhMohon", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
//	    		    	  h.put("idFail", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
//	    		    	  h.put("nofail", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
//	    		    	  h.put("no", rs.getString("NO_PERMOHONAN_ONLINE")==null?"":rs.getString("NO_PERMOHONAN_ONLINE"));
//	    		    	  h.put("id_Permohonan", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
//	    		    	  h.put("icSimati", rs.getString("MYID_SIMATI")==null?"":rs.getString("MYID_SIMATI"));
//	    		    	  h.put("icPemohon", rs.getString("MYID_PEMOHON")==null?"":rs.getString("MYID_PEMOHON"));
//	    		    	  h.put("id_pemohon", rs.getString("ID_PEMOHON")==null?"":rs.getString("ID_PEMOHON"));
//	    		    	  h.put("status", rs.getString("STATUS")==null?"":rs.getString("STATUS"));
	    				bil++;

	    		}
	    		
	      //return h;
	    } finally {
	      if (db != null) db.close();
	    }	    
	     return senaraiMaklumat;
	     
	  }//close list	
	
	public void kemaskiniStatusPermohonan(String idPermohonan,String idStatus,String userId) throws Exception {
		try{
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = db.getStatement();
			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS",idStatus);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			//myLog.info("kemaskiniStatusPermohonan:sql="+sql);
			stmt.executeUpdate(sql);			
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();			    
			} catch (SQLException se2) {
				 getIHTP().getErrorHTML("Rollback error:kemaskiniStatusPermohonan::"+se2.toString());			
			}
			getIHTP().getErrorHTML("Ralat Kemaskini Status:kemaskiniStatusPermohonan::"+se.toString());
			    
		}catch(Exception ex){
			 conn.rollback();
			 getIHTP().getErrorHTML(ex.toString());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		  
		
	}
	
	@Override
	public String simpan(Hashtable<String,String> hash) throws Exception {
		String idSuburusanStatus = getIdSuburusanStatusByLangkah(String.valueOf(hash.get("langkah"))
									,String.valueOf(hash.get("idSuburusan")), "=");
	
		Tblrujsuburusanstatusfail susf = new Tblrujsuburusanstatusfail();
		susf.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
		susf.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
		susf.setIdSuburusanstatus(Long.parseLong(idSuburusanStatus));
		susf.setUrl(String.valueOf(hash.get("catatan")));
		susf.setAktif("1");
		susf.setIdMasuk(Long.parseLong(String.valueOf(hash.get("idUser"))));
		susf.setTarikhMasuk("sysdate");
		susf.setIdKemaskini(Long.parseLong(String.valueOf(hash.get("idUser"))));
		susf.setTarikhKemaskini("sysdate");
		return simpanStatusAktif(susf);
	
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
}
