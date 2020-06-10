package ekptg.model.htp;

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
import ekptg.model.entities.Tblrujsuburusanstatus;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPStatusBean implements IHTPStatus {
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.HTPStatusBean.class);
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private IHtp iHTP = null;  	
	private Long idSubUrusanStatusFail = 0L;
	private String idSubUrusanStatusFailStr = "0";
	private String idSubUrusanStatus = "0";
	private String sql = "";
	private Connection conn = null;
	private Db db = null;
	private SQLRenderer r = null;			
	private Statement stmt = null;

	@Override
	public Tblrujsuburusanstatus getDetails(String id) {
		Tblrujsuburusanstatus detailsData = null;
		try {
			db = new Db();
			detailsData = new Tblrujsuburusanstatus();
			Statement stmt = db.getStatement();
			String sql = "select rsus.id_suburusanstatus,rs.keterangan, rsus.langkah, rsu.nama_suburusan"+
					" ,rsu.id_urusan,rsu.id_suburusan,rsus.aktif,rsus.module_id,rsus.id_status "+
					" from tblrujstatus rs, tblrujsuburusanstatus rsus,tblrujsuburusan rsu" +
					" where rsus.ID_STATUS=rs.ID_STATUS" +
					" and rsus.ID_SUBURUSAN=rsu.ID_SUBURUSAN";
			if (id != null && !"".equals(id)) {
				sql += " and rsus.ID_SUBURUSANSTATUS ="+id;
			}
			sql += " order by rsu.id_suburusan,rsus.langkah";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				detailsData.setIdSuburusanstatus(Long.parseLong(id));
				detailsData.setIdStatus(rs.getLong("id_status"));
				detailsData.setIdSuburusan(rs.getLong("id_suburusan"));
				detailsData.setLangkah(rs.getInt("langkah"));
			  
			}	
			//return detailsData;
			  
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		return detailsData;
	  
	}

	@Override
	public Tblrujsuburusanstatusfail getStatusFailPermohonanAktif(String idFail,String idPermohonan) throws Exception {		
		Tblrujsuburusanstatusfail susf = null;
	    try {	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();	    		
	    	sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    	sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    	sql += " ,F.ID_MASUK,ST.ID_SUBURUSANSTATUS,STF.URL CATATAN" +
	    			",TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    			",TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI " +
	    			"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    	sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    	sql += " WHERE " ;
	    	sql += " a.id_fail = f.id_fail ";
	    	sql += " AND st.id_status = s.id_status ";
	    	sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    	sql += " AND stf.id_permohonan = a.id_permohonan ";
	    	sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    	sql += " AND F.id_status <> '999' ";
	    	//sql += " AND ST.LANGKAH = '"+langkah+"' ";
	    	sql += " AND stf.aktif = 1 ";
	    	sql += " AND STF.ID_PERMOHONAN = '" + idPermohonan + "'";
	    	sql += " AND STF.ID_FAIL = '" + idFail + "'";
	    	//myLog.info("getStatusFailPermohonanAktif :sql="+sql);
	    	ResultSet rs = stmt.executeQuery(sql);	      				 
	    	while (rs.next()) {
	    		susf = new Tblrujsuburusanstatusfail();
	    		susf.setIdSuburusanstatusfail(rs.getLong("id_suburusanstatusfail"));
	    		susf.setIdFail(rs.getLong("id_fail"));
	    		susf.setIdPermohonan(rs.getLong("id_permohonan"));
	    		susf.setIdSuburusanstatus(rs.getLong("id_suburusanstatus"));
	    		susf.setUrl(Utils.isNull(rs.getString("CATATAN")));
	    		//susf.setTarikhKemaskini(rs.getString("TARIKH_SELESAI").equals("01/01/1900")?new Date():rs.getDate("TARIKH_SELESAI"));

	    	}	    	  
	    	return susf;
	    	
	    } finally {
	      if (db != null) db.close();
	    }	    
	    
	  }//close Tblrujsuburusanstatusfail	
	@Override
	public void statusChangeActionL1(String idFail,String idPermohonan,String idSuburusan,String user){
		try{
			idSubUrusanStatusFailStr = String.valueOf(DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			idSubUrusanStatus = getIdSuburusanStatusByLangkah("1",idSuburusan,"=");

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("Id_Suburusanstatusfail", idSubUrusanStatusFailStr);
			r.add("id_Permohonan", idPermohonan);
			r.add("id_fail", idFail);
			r.add("Id_Suburusanstatus",idSubUrusanStatus);
			r.add("aktif","1");
			r.add("ID_MASUK", r.unquote(user));
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", r.unquote(user));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblrujsuburusanstatusfail");
			myLog.info("HTPStatusBean::statusChangeActionL1:sql = "+sql);
			stmt.executeUpdate(sql);
		      
		  }catch(Exception ex){
			  ex.printStackTrace();			  
		  }finally{
			  if (db != null) db.close();			  
		  }		  
		  
	  }	
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
	@Override
	public void simpanKemaskiniStatus(Tblrujsuburusanstatusfail s,Long setIdStatus) throws Exception {
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(isSubUrusanStatusPermohonan(s)){
				r.update("ID_PERMOHONAN",r.unquote(String.valueOf(s.getIdPermohonan())));
				r.update("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
				r.update("ID_SUBURUSANSTATUS",r.unquote(String.valueOf(s.getIdSuburusanstatus())));
				if(isSubUrusanStatusPermohonanAktif(s))	
					r.add("AKTIF",s.getAktif());

				r.add("ID_KEMASKINI",s.getIdKemaskini());
				r.add("TARIKH_KEMASKINI",r.unquote("sysdate")); 
				sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
				myLog.info("UPDATE:sql="+sql);
				stmt.executeUpdate(sql);	
				
				//TBLPERMOHONAN
//				r = new SQLRenderer();
//				r.update("ID_PERMOHONAN", String.valueOf(s.getIdPermohonan()));
//				r.add("ID_STATUS",setIdStatus);
//
//				r.add("ID_KEMASKINI", String.valueOf(s.getIdMasuk()));
//				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//
//				sql = r.getSQLUpdate("TBLPERMOHONAN");
//				myLog.info("simpanKemaskiniStatus:UPDATE::sql="+sql);
//				stmt.executeUpdate(sql);
			
			}else{
//				r = new SQLRenderer();
//				r.update("ID_PERMOHONAN",r.unquote(String.valueOf(s.getIdPermohonan())));
//				r.update("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
//				r.update("AKTIF", "1");
//				r.add("AKTIF","0");
//				r.add("ID_KEMASKINI", s.getIdKemaskini());
//				r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
//				sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
//				//myLog.info("UPDATE:sql="+sql);
//				stmt.executeUpdate(sql);
//
//				long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
//				r = new SQLRenderer();		  
//				r.add("ID_SUBURUSANSTATUSFAIL", IdSuburusanstatusfail);
//				r.add("ID_PERMOHONAN", String.valueOf(s.getIdPermohonan()));
//				r.add("ID_SUBURUSANSTATUS", r.unquote(String.valueOf(s.getIdSuburusanstatus())));
//				r.add("AKTIF",s.getAktif());
//				r.add("URL",s.getUrl());
//				r.add("ID_MASUK", String.valueOf(s.getIdMasuk()));
//				r.add("TARIKH_MASUK", r.unquote("sysdate"));
//				r.add("ID_KEMASKINI", String.valueOf(s.getIdMasuk()));
//				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
//				r.add("ID_FAIL",r.unquote(String.valueOf(s.getIdFail())));
//				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
//				//myLog.info("INSERT:sql="+sql);
//			    stmt.executeUpdate(sql);
			    Tblrujsuburusanstatusfail sf = new Tblrujsuburusanstatusfail();
				sf.setIdPermohonan(s.getIdPermohonan());
				sf.setIdFail(s.getIdFail());
				sf.setAktif("0");

				Tblrujsuburusanstatusfail sfn = new Tblrujsuburusanstatusfail();
				sfn.setIdSuburusanstatus(s.getIdSuburusanstatus());
				sfn.setAktif("1");
				sfn.setUrl("-");
				sfn.setIdMasuk(s.getIdMasuk());
				
				kemaskiniSimpanStatusAktif(sf, sfn);

			    
				//TBLPERMOHONAN
//				r = new SQLRenderer();
//				r.update("ID_PERMOHONAN", String.valueOf(s.getIdPermohonan()));
//				r.add("ID_STATUS",setIdStatus);
//				r.add("ID_KEMASKINI", String.valueOf(s.getIdMasuk()));
//				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//				sql = r.getSQLUpdate("TBLPERMOHONAN");
//				stmt.executeUpdate(sql);
				
			}
			
			kemaskiniStatusPermohonan(String.valueOf(s.getIdPermohonan())
					,String.valueOf(setIdStatus),String.valueOf(s.getIdMasuk()));
			
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				//throw new Exception("Rollback error:"+se2.getMessage());
				 getIHTP().getErrorHTML("Rollback error:"+se2.toString());
				
			}
			//throw new Exception("Ralat Kemaskini Status:"+se.getMessage());
			 getIHTP().getErrorHTML("Ralat Kemaskini Status:"+se.toString());
			    
		}catch(Exception ex){
			 conn.rollback();
			 //ex.printStackTrace();
			 //throw new Exception("Ralat:"+ex.getMessage());
			 getIHTP().getErrorHTML(ex.toString());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
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
	public void kemaskiniStatus(Tblrujsuburusanstatusfail s, String strTarikh) throws Exception {
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
			String tarikMasuk = "to_date('" + strTarikh + "','dd/MM/yyyy')";
			r.add("TARIKH_KEMASKINI",r.unquote(tarikMasuk)); 
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
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
	@Override
	public Hashtable<String, String> getInfoStatusPermohonanFail(String idFail,String idPermohonan,String langkah) throws Exception {		
	    Hashtable<String, String> h = null;
	    try {	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();	    		
	    	//SYARAT
	    	sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    	sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    	sql += " ,F.ID_MASUK,ST.ID_SUBURUSANSTATUS,STF.URL CATATAN" +
	    			",TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    			",TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI " +
	    			"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    	sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    	sql += " WHERE " ;
	    	sql += " a.id_fail = f.id_fail ";
	    	sql += " AND st.id_status = s.id_status ";
	    	sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    	sql += " AND stf.id_permohonan = a.id_permohonan ";
	    	sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    	sql += " AND F.id_status <> '999' ";
	    	sql += " AND ST.LANGKAH = '"+langkah+"' ";
	    	//sql += " AND stf.aktif = 1 ";
	    	sql += " AND STF.ID_PERMOHONAN = '" + idPermohonan + "'";
	    	sql += " AND STF.ID_FAIL = '" + idFail + "'";
	    	myLog.info("getInfoStatusPermohonanFail 2:sql="+sql);
	    	ResultSet rs = stmt.executeQuery(sql);	      	
			 
	    	while (rs.next()) {
	    		h = new Hashtable<String, String>();  
	    		h.put("level",rs.getString("ID_MASUK"));	 
	    		h.put("id_permohonan", rs.getString("id_permohonan"));
	    		h.put("id_status", rs.getString("id_suburusan"));
	    		h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    		h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    		h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    		h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    		h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
	    		h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    		//h.put("tarikhSelesai", rs.getString("TARIKH_SELESAI").equals("01/01/1900")?"":Utils.isNull(rs.getString("TARIKH_SELESAI")));

	    	}	    	  
	    	return h;
	    	
	    } finally {
	      if (db != null) db.close();
	    }	    
	    
	  }//close list	
		
	@Override
	public Hashtable<String, String> getInfoStatusPermohonan(String idPermohonan,String langkah) throws Exception {		
	    Hashtable<String, String> h = null;
	    try {	    	
	    	db = new Db();
	    	Statement stmt = db.getStatement();	    		
	    	//SYARAT
	    	sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    	sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    	sql += " ,F.ID_MASUK,ST.ID_SUBURUSANSTATUS,STF.URL CATATAN" +
	    			",TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    			",TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI " +
	    			"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    	sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    	sql += " WHERE " ;
	    	sql += " a.id_fail = f.id_fail ";
	    	sql += " AND st.id_status = s.id_status ";
	    	sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    	sql += " AND stf.id_permohonan = a.id_permohonan ";
	    	sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    	sql += " AND F.id_status <> '999' ";
	    	sql += " AND ST.LANGKAH = '"+langkah+"' ";
	    	//sql += " AND stf.aktif = 1 ";
	    	sql += " AND STF.ID_PERMOHONAN = '" + idPermohonan + "'";
	    	//myLog.info("getInfoSelesaiPermohonan 2:sql="+sql);
	    	ResultSet rs = stmt.executeQuery(sql);	      	
			 
	    	while (rs.next()) {
	    		h = new Hashtable<String, String>();  
	    		h.put("level",rs.getString("ID_MASUK"));	 
	    		h.put("id_permohonan", rs.getString("id_permohonan"));
	    		h.put("id_status", rs.getString("id_suburusan"));
	    		h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    		h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    		h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    		h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    		h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
	    		h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    		h.put("tarikhSelesai", rs.getString("TARIKH_SELESAI").equals("01/01/1900")?"":Utils.isNull(rs.getString("TARIKH_SELESAI")));

	    	}	    	  
	    	return h;
	    	
	    } finally {
	      if (db != null) db.close();
	    }	    
	    
	  }//close list
	
	@Override
	public Tblrujsuburusanstatusfail kemaskiniSimpanStatusPermohonanAktif(
		Tblrujsuburusanstatusfail sLama, Tblrujsuburusanstatusfail sBaru, String tahun) throws Exception {
		Tblrujsuburusanstatusfail terbaru = null;
		long IdSuburusanstatusfail = 0;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(isSubUrusanStatusPermohonan(sBaru,tahun )){	
				myLog.info("idSubUrusanStatusFail = "+idSubUrusanStatusFail);
				IdSuburusanstatusfail = idSubUrusanStatusFail ;
				//r.update("id_permohonan",r.unquote(String.valueOf(sLama.getIdPermohonan())));
				//r.update("id_fail",r.unquote(String.valueOf(sLama.getIdFail())));
				//r.update("aktif", "1");
				r.update("ID_SUBURUSANSTATUSFAIL", idSubUrusanStatusFail);
				r.add("aktif",sLama.getAktif());
				r.add("id_kemaskini", sBaru.getIdKemaskini());
				r.add("tarikh_kemaskini", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
				myLog.info("kemaskini:"+sBaru.getTarikhMasuk());
				//stmt.executeUpdate(sql);
				
			}else{
				r.update("id_permohonan",r.unquote(String.valueOf(sLama.getIdPermohonan())));
				r.update("id_fail",r.unquote(String.valueOf(sLama.getIdFail())));
				r.update("aktif", "1");
				r.add("aktif",sLama.getAktif());
				r.add("id_kemaskini", sBaru.getIdKemaskini());
				r.add("tarikh_kemaskini", r.unquote("sysdate")); 
				sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
				myLog.info("kemaskini:"+sBaru.getTarikhMasuk());
				stmt.executeUpdate(sql);
			
				IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
				r = new SQLRenderer();		  
				r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
				r.add("id_Permohonan", String.valueOf(sLama.getIdPermohonan()));
				r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
				r.add("aktif",sBaru.getAktif());
				r.add("URL",sBaru.getUrl());
				r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
				r.add("tarikh_Masuk", r.unquote("sysdate"));
				r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				r.add("id_fail",r.unquote(String.valueOf(sLama.getIdFail())));
				sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
				myLog.info("sBaru.getTarikhMasuk():"+sBaru.getTarikhMasuk());
				stmt.executeUpdate(sql);
				
			}
			conn.commit();
			terbaru = new Tblrujsuburusanstatusfail();
			terbaru.setIdSuburusanstatusfail(IdSuburusanstatusfail);
			terbaru.setIdFail(sLama.getIdFail());
			terbaru.setIdPermohonan(sLama.getIdPermohonan());
			terbaru.setAktif(sBaru.getAktif());
			terbaru.setIdMasuk(sBaru.getIdMasuk());
			
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
		
		return terbaru;
		
	}
	
//	@Override
//	public void kemaskiniSimpanStatusCukai(Tblrujsuburusanstatusfail sLama
//		, Tblrujsuburusanstatusfail sBaru, String tahun) throws Exception {
//		long idStatusCukai = 0;
//		try{
//			db = new Db();
//			conn = db.getConnection();
//			conn.setAutoCommit(false);
//			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
//			if(isSubUrusanStatusPermohonan(sLama,tahun )){			
//				r.update("ID_SUBURUSANSTATUSFAIL",r.unquote(String.valueOf(sLama.getIdSuburusanstatusfail())));
//				r.add("id_kemaskini", sBaru.getIdKemaskini());
//				r.add("tarikh_kemaskini", r.unquote("sysdate")); 
//				sql = r.getSQLUpdate("TBLHTPRUJSTATUSCUKAI");
//				myLog.info("kemaskini:"+sBaru.getTarikhMasuk());
//				stmt.executeUpdate(sql);
//			}else{
//				idStatusCukai = DB.getNextID("TBLHTPRUJSTATUSCUKAI_SEQ");		  
//				r = new SQLRenderer();		  
//				r.add("ID_STATUSCUKAI", idStatusCukai);
//				r.add("ID_SUBURUSANSTATUSFAIL", r.unquote(String.valueOf(sBaru.getIdSuburusanstatusfail())));
//				r.add("ID_MASUK", sBaru.getIdMasuk());
//				r.add("TARIKH_MASUK", r.unquote("sysdate"));
//				r.add("ID_KEMASKINI", sBaru.getIdMasuk());
//				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
//				sql = r.getSQLInsert("TBLHTPRUJSTATUSCUKAI");
//				myLog.info("TBLHTPRUJSTATUSCUKAI="+sql);
//				stmt.executeUpdate(sql);			
//				
//			}
//			conn.commit();
//			
//		}catch (SQLException se) { 
//			try {
//				conn.rollback();
//			    
//			} catch (SQLException se2) {
//				throw new Exception("Rollback error:"+se2.getMessage());
//				
//			}
//			throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
//			    
//		}catch(Exception ex){
//			 conn.rollback();
//			 ex.printStackTrace();
//			 throw new Exception("Ralat:"+ex.getMessage());
//		
//		}finally{
//			if (db != null) db.close();
//			if (conn != null) conn.close();
//	
//		}	
//		
//		//return terbaru;
//		
//	}
	
	//public boolean isSubUrusanStatusPermohonanCukai(Tblrujsuburusanstatusfail rsus, String tahun) throws Exception {
	public boolean isSubUrusanStatusPermohonan(Tblrujsuburusanstatusfail rsus, String tahun) throws Exception {
	boolean returnValue = false;
	    try {
	      db = new Db();
	      SQLRenderer r = new SQLRenderer();
	      r.add("RSUSF.ID_SUBURUSANSTATUSFAIL");
		  r.add("RSUSF.ID_SUBURUSANSTATUSFAIL",r.unquote("RSUSC.ID_SUBURUSANSTATUSFAIL"));
		  r.add("RSUSF.ID_PERMOHONAN",rsus.getIdPermohonan());
		  r.add("RSUSF.ID_FAIL",rsus.getIdFail());
		  r.add("RSUSF.ID_SUBURUSANSTATUS",rsus.getIdSuburusanstatus());
		  r.add("RSUSC.TAHUN",tahun);
	      sql = r.getSQLSelect("TBLRUJSUBURUSANSTATUSFAIL RSUSF, TBLHTPRUJSTATUSCUKAI RSUSC");		      
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) { 
	    	  idSubUrusanStatusFail = rs.getLong("ID_SUBURUSANSTATUSFAIL");
	    	  returnValue = true;	
	    	  }
	      
	    }catch(Exception e){
			//e.printStackTrace();
			getIHTP().getErrorHTML(e.toString());	      
	    } finally {
	      if (db != null) db.close();
	    }		
	    return returnValue;
	
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

	@Override
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
	
	@Override
	public String kemaskiniSimpanStatusAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru,String strTarikh) 
		throws Exception {
		String IdSuburusanstatusfail = "0";
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
			sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
			//myLog.info(sql);
			stmt.executeUpdate(sql);
			
//			IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
//			r = new SQLRenderer();		  
//			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
//			r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
//			r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
//			r.add("aktif",sBaru.getAktif());
//			r.add("URL",sBaru.getUrl());
//			r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
//			String tarikMasuk = "sysdate";
//			r.add("tarikh_Masuk", r.unquote(tarikMasuk));
//			r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
			String tarikhKemaskini = "to_date('" + strTarikh + "','dd/MM/yyyy')";
//			r.add("tarikh_kemaskini", r.unquote(tarikhKemaskini));
//			r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
//			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
//		    stmt.executeUpdate(sql);
			sBaru.setIdPermohonan(s.getIdPermohonan());
			sBaru.setIdFail(s.getIdFail());
			sBaru.setTarikhMasuk("sysdate");
			sBaru.setTarikhKemaskini(tarikhKemaskini);
		    IdSuburusanstatusfail = simpanStatusAktif(sBaru);
		    
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Daftar/Kemaskini Status Permohonan:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		
		return IdSuburusanstatusfail;
		
	}
	
	@Override
	public void kemaskiniStatusPermohonan(String idPermohonan, String subUrusan, String langkah,String userId) 
		throws Exception {
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah(langkah,subUrusan,"=");
	
			r.update("ID_PERMOHONAN",idPermohonan);
			r.add("ID_STATUS", setIdStatus);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
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
	
	@Override
	public Vector<Hashtable<String, String>> getInfoStatusPermohonan(String idUrusan,String idSubUrusan,String langkah,String idNegeri)
		throws Exception{
	    Hashtable<String, String> h = null;
		Vector<Hashtable<String, String>> senaraiMaklumat = null;
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
	    	" TBLRUJSTATUS S, TBLRUJSUBURUSANSTATUS ST, TBLHTPRUJSUBURUSANSTATUSFAIL STF "+
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
	    		bil++;
	    		senaraiMaklumat.addElement(h);

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
	
	public void semakStatusSemasa(org.apache.velocity.VelocityContext context
			,String akses
			,String idPermohonan
			,String idFail
			,String portal_role) throws Exception{
			//lebah.portal.velocity.VTemplate context
			String langkah = "";
			String statuSemasa = "0";
			if(portal_role.contains("PenggunaNegeri")){
					langkah = "1";
			}else if(portal_role.contains("PegawaiNegeri")){
					langkah = "2";
			}else if(portal_role.contains("PengarahNegeri")){
					langkah = "3";	    		
			}
			Hashtable<String,String> hashStatus =getInfoStatusPermohonanFail(idFail, idPermohonan,langkah);
			if(hashStatus != null)
				statuSemasa = hashStatus.get("langkah");
				
			context.put("jenisAkses", akses);
			context.put("statuSemasa", statuSemasa);
		
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	
}
