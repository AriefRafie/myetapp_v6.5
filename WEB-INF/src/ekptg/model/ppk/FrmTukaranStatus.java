package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

public class FrmTukaranStatus {
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmTukaranStatus.class);	
	
	private static Vector list = new Vector();
	private static Vector listNotaBicara = new Vector();
	private static Vector dataNotaBicara = new Vector();
	private static Vector senaraiNotabicara = new Vector();
	private Vector beanMaklumatNota = null;
	private Vector listNota = null;
	
	public static Vector getList(){
		return list;
	}
	
	public static Vector getListNotaBicara(){
		return listNotaBicara;
	}
	
	public static Vector getDataNotaBicara(){
		return dataNotaBicara;
	}
	
	
	
	private static Vector list_sub = new Vector();
	
	public static Vector getListSub(){
		return list_sub;
	}
	
	public void setSenaraiNota(String id_notabicara) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listNota = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT UPL.ID_DOKUMEN, DOK.NAMA_DOKUMEN FROM TBLPPKDOKUMEN DOK , TBLPPKUPLOADNOTABICARA UPL" 
				   + " WHERE DOK.ID_DOKUMEN = UPL.ID_DOKUMEN AND UPL.ID_NOTABICARA = '" + id_notabicara + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
			
				listNota.addElement(h);
				bil++;
				count++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector tukarPemohonOnline(String id_permohonan) throws Exception {
		list_sub = new Vector();
		list_sub.clear();
		Db db = null;
		Hashtable sek = null;
		String sql = "";
		String seksyen = "";
		String id_suburusan = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	
			sql = " SELECT * FROM TBLPPKTUKARPEMOHONLINE WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
			
			myLogger.info("SENARAI TUKAR PEMOHON ONLINE :"+sql);
			
			
			int bil = 0;
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
							
				h.put("BIL", bil);
				h.put("ADA", "ada");
				if (rs.getString("ID_PEMOHONLAMA") == null) {
					h.put("ID_PEMOHONLAMA", "");
				} else {
					h.put("ID_PEMOHONLAMA", rs.getString("ID_PEMOHONLAMA"));
				}
				
				if (rs.getString("ID_PEMOHONBARU") == null) {
					h.put("ID_PEMOHONBARU", "");
				} else {
					h.put("ID_PEMOHONBARU", rs.getString("ID_PEMOHONBARU"));
				}
				
				if (rs.getString("SEBAB_TUKAR") == null) {
					h.put("SEBAB_TUKAR", "");
				} else {
					h.put("SEBAB_TUKAR", rs.getString("SEBAB_TUKAR"));
				}
				
					
				list_sub.addElement(h);
			}
			return list_sub;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatNota(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatNota = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN FROM TBLPPKDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				beanMaklumatNota.addElement(h);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanKemaskiniNota(String idDokumen, String txtNamaDokumen,
			 HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaDokumen);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			myLogger.error(ex);
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusNota(String idDokumen) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPPKUPLOADNOTABICARA");
			stmt.executeUpdate(sql);
			
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_DOKUMEN", idDokumen);

			sql = r1.getSQLDelete("TBLPPKDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			myLogger.error(ex);
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//semak data nota bicara
	public static void setDataNotaBicara(String id_perbicaraan)throws Exception {
		
	    Db db = null;
	    dataNotaBicara.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		sql = "SELECT ID_NOTABICARA, NOTA_BICARA FROM TBLPPKNOTABICARA";
	    		sql += " WHERE ID_PERBICARAAN = '"+id_perbicaraan+"'";
	    		
				ResultSet rs = stmt.executeQuery(sql);
	      	
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();      		  
	    			h.put("id_notabicara", rs.getString("ID_NOTABICARA")==null?"":rs.getString("ID_NOTABICARA"));
	    			h.put("nota_bicara", rs.getString("NOTA_BICARA")==null?"":rs.getString("NOTA_BICARA"));
	    			dataNotaBicara.addElement(h);
	    		}
	      
	      //return list;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close data nota bicara
	
	
	
	
	public static void simpanNotaBicara(Hashtable data) throws Exception{
	    
		Db db = null;
		String sql = "";
		    
		try
		{
		   db = new Db();
		    	 
		   String notaBicara = (String)data.get("notaBicara");
		   String id_masuk = (String)data.get("id_masuk");
		   String id_perbicaraan = (String)data.get("id_perbicaraan");
		   
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();
		   r.add("nota_bicara", notaBicara); 	
		   r.add("id_perbicaraan", id_perbicaraan); 
		   r.add("tarikh_masuk", r.unquote("sysdate"));
		   r.add("id_masuk",id_masuk);
		    	
		   sql = r.getSQLInsert("Tblppknotabicara");
		   stmt.executeUpdate(sql);
		    
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}//close try 
		    
		finally {
		    if (db != null) db.close();
		}//close finally
		   
	}//close simpan nota bicara
	
	
	public static void updateNotaBicara(Hashtable data) throws Exception{
	    
		Db db = null;
		String sql = "";
		    
		try
		{
		   db = new Db();
		    	 
		   String notaBicara = (String)data.get("notaBicara");
		   String id_kemaskini = (String)data.get("id_kemaskini");
		   String id_notabicara = (String)data.get("id_notabicara");
		   
		   Statement stmt = db.getStatement();
		   SQLRenderer r = new SQLRenderer();
		   r.update("id_notabicara", id_notabicara); 	
		   r.add("nota_bicara", notaBicara); 
		   r.add("tarikh_kemaskini", r.unquote("sysdate"));
		   r.add("id_kemaskini",id_kemaskini);
		    	
		   sql = r.getSQLUpdate("Tblppknotabicara");
		   stmt.executeUpdate(sql);
		    
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		}//close try 
		    
		finally {
		    if (db != null) db.close();
		}//close finally
		   
	}//close simpan nota bicara
	
	public static void setListNota(String id_fail, String usid)throws Exception {
		
	    Db db = null;
	    listNotaBicara.clear();
	    String sql = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		String chkDataFail = id_fail.trim();
	      
	    		//SQL
	    		sql = "SELECT NVL(MAX(A.ID_PERBICARAAN),0) AS ID_PERBICARAAN";
	    		sql += " FROM TBLPPKPERBICARAAN A, TBLPPKKEPUTUSANPERMOHONAN B, TBLPPKPERMOHONAN C, TBLPFDFAIL D, TBLRUJDAERAH F,";
	    		sql += " TBLRUJSTATUS G, TBLRUJSUBURUSANSTATUS H, TBLRUJSUBURUSANSTATUSFAIL I";
	    		sql += " WHERE F.ID_DAERAH";
	    		sql += " IN(SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID= '"+usid+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				
				sql += " )";
	    		sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN";
	    		sql += " AND C.ID_DAERAHMHN = F.ID_DAERAH";
	    		sql += " AND A.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN";
				sql += " AND C.ID_FAIL = D.ID_FAIL"; 
				sql += " AND C.ID_STATUS = G.ID_STATUS";
				sql += " AND H.ID_STATUS = G.ID_STATUS";
				sql += " AND I.ID_SUBURUSANSTATUS = H.ID_SUBURUSANSTATUS";
				sql += " AND I.AKTIF = '1'";
				sql += " AND C.ID_STATUS NOT IN ('999','56')";
				sql += " AND D.ID_FAIL = '"+id_fail+"'";
	    		myLogger.info("LIST NOTA :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
	      	
	    		Hashtable h;

	    		while (rs.next()) {
	    			h = new Hashtable();      		  
	    			h.put("id_perbicaraan", rs.getString("ID_PERBICARAAN")==null?"":rs.getString("ID_PERBICARAAN"));
	    			listNotaBicara.addElement(h);
	    		}
	      
	      //return list;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list
	
	
	
		
	public Vector setListSub(String id_fail) throws Exception {
		list_sub = new Vector();
		list_sub.clear();
		Db db = null;
		Hashtable sek = null;
		String sql = "";
		String seksyen = "";
		String id_suburusan = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sek = (Hashtable)getSeksyen(id_fail);
			
			if((String)sek.get("SEKSYEN")!=null)
			{
			seksyen = (String)sek.get("SEKSYEN");	
			if(seksyen.equals("8"))
			{
				id_suburusan = "59";	
			}
			else
			{
				id_suburusan = "60";
			}
			}
		
			
			sql = "" +
			" SELECT SU.ID_SUBURUSAN,SS.ID_SUBURUSANSTATUS,F.NO_FAIL,SSF.ID_SUBURUSANSTATUSFAIL,SSF.ID_FAIL,SSF.ID_PERMOHONAN," +
			" TO_CHAR(SSF.TARIKH_KEMASKINI,'DD/MM/YYYY HH:MI AM') AS TARIKH_MASUK,S.KETERANGAN AS STATUS,S.ID_STATUS,SSF.AKTIF " +
			" FROM TBLRUJSUBURUSANSTATUSFAIL SSF,TBLPFDFAIL F,TBLRUJSUBURUSANSTATUS SS, TBLRUJSUBURUSAN SU,"+
			" TBLRUJSTATUS S "+
			" WHERE SSF.ID_FAIL = F.ID_FAIL "+
			" AND SSF.ID_SUBURUSANSTATUS = SS.ID_SUBURUSANSTATUS "+
			" AND SS.ID_STATUS = S.ID_STATUS AND SS.ID_SUBURUSAN = SU.ID_SUBURUSAN ";
			//sql += " AND SU.ID_SUBURUSAN = '"+id_suburusan+"' ";
			sql += " AND F.ID_FAIL = '" + id_fail + "'";
			sql +=" ORDER BY SSF.ID_SUBURUSANSTATUSFAIL ASC "+
			""; 
			
			myLogger.info("SENARAI SUBURUSANSTATUSFAIL :"+sql);
			
			//SELECT * FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS IN ('8','9','14','50','53','151','18','44','175','172','173','174','41','47','25')
			
			
			
			int bil = 0;
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
							
				h.put("BIL", bil);
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL"));
				}
				
				if (rs.getString("ID_SUBURUSAN") == null) {
					h.put("ID_SUBURUSAN", "");
				} else {
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));
				}
				
				if (rs.getString("ID_SUBURUSANSTATUS") == null) {
					h.put("ID_SUBURUSANSTATUS", "");
				} else {
					h.put("ID_SUBURUSANSTATUS", rs.getString("ID_SUBURUSANSTATUS"));
				}
				
				if (rs.getString("ID_SUBURUSANSTATUSFAIL") == null) {
					h.put("ID_SUBURUSANSTATUSFAIL", "");
				} else {
					h.put("ID_SUBURUSANSTATUSFAIL", rs.getString("ID_SUBURUSANSTATUSFAIL"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("TARIKH_MASUK") == null) {
					h.put("TARIKH_MASUK", "");
				} else {
					h.put("TARIKH_MASUK", rs.getString("TARIKH_MASUK"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
				if (rs.getString("STATUS") == null) {
					h.put("STATUS", "");
				} else {
					h.put("STATUS", rs.getString("STATUS"));
				}
				
				if (rs.getString("AKTIF") == null) {
					h.put("AKTIF", "");
				} else {
					h.put("AKTIF", rs.getString("AKTIF"));
				}
					
				list_sub.addElement(h);
			}
			return list_sub;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	
	Vector list_status = null;
	public Vector list_status(String id_fail) throws Exception {
		list_status = new Vector();
		list_status.clear();
		Db db = null;
		String sql = "";
		Hashtable sek = null;
		String seksyen = "";
		String id_suburusan = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sek = (Hashtable)getSeksyen(id_fail);
			if((String)sek.get("SEKSYEN")!=null)
			{
			seksyen = (String)sek.get("SEKSYEN");
	
			if(seksyen.equals("8"))
			{
				id_suburusan = "59";	
			}
			else
			{
				id_suburusan = "60";
			}
			}
			
			
			sql = "" +
		    " SELECT S.ID_STATUS,SS.ID_SUBURUSANSTATUS,SS.ID_SUBURUSAN,S.KETERANGAN AS NAMA_STATUS " +
		    " FROM TBLRUJSUBURUSANSTATUS SS,TBLRUJSUBURUSAN SU," +
		    " TBLRUJSTATUS S "+
		    " WHERE SS.ID_SUBURUSAN = SU.ID_SUBURUSAN AND SS.ID_STATUS = S.ID_STATUS AND SU.ID_SUBURUSAN = '"+id_suburusan+"' "+
		    " AND SU.ID_SEKSYEN = 2 ";
			
			myLogger.info("SQL LIST SUBURUSANSTATUS :"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();				
				if (rs.getString("ID_SUBURUSANSTATUS") == null) {
					h.put("ID_SUBURUSANSTATUS", "");
				} else {
					h.put("ID_SUBURUSANSTATUS", rs.getString("ID_SUBURUSANSTATUS"));
				}
				if (rs.getString("NAMA_STATUS") == null) {
					h.put("NAMA_STATUS", "");
				} else {
					h.put("NAMA_STATUS", rs.getString("NAMA_STATUS"));
				}
				if (rs.getString("ID_SUBURUSAN") == null) {
					h.put("ID_SUBURUSAN", "");
				} else {
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));
				}
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				list_status.addElement(h);
			}
			return list_status;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector search_bantahan = null;
	
	public Vector search_bantahan(String no_fail,String usid,String txtIcPemohon, String txtNamaSimati, String txtNamaPemohon) throws Exception {
		search_bantahan = new Vector();
		search_bantahan.clear();
		Db db = null;
		String sql = "";
	try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT ST.KETERANGAN AS NAMA_STATUS, ST.ID_STATUS, F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN,PM.NO_KP_BARU,PM.NO_KP_LAMA," +
					  " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,S.NAMA_SIMATI,PM.NAMA_PEMOHON "+
					  " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST "+
					  " WHERE F.ID_FAIL = P.ID_FAIL "+
					  " AND ST.ID_STATUS = '18' "+
					  " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN ";
					
				sql += " AND ( trim(F.NO_FAIL) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_BARU) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_LAMA) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_LAIN) LIKE '%"+no_fail.trim()+"%') ";
				
				if(!txtIcPemohon.trim().equals("") && txtIcPemohon.trim()!=null){
				sql += " AND ( trim(PM.NO_KP_BARU) LIKE '%"+txtIcPemohon.trim()+"%' ";
				sql += " OR trim(PM.NO_KP_LAMA) LIKE '%"+txtIcPemohon.trim()+"%' )";
				}
				
				if(!txtNamaSimati.trim().equals("") && txtNamaSimati.trim()!=null){
					sql += " AND ( trim(S.NAMA_SIMATI) LIKE '%"+txtNamaSimati.trim()+"%' ) ";
				}
				
				if(!txtNamaPemohon.trim().equals("") && txtNamaPemohon.trim()!=null){
					sql += " AND ( trim(S.NAMA_SIMATI) LIKE '%"+txtNamaPemohon.trim()+"%' ) ";
				}
				
//				sql += "AND D.ID_DAERAH = P.ID_DAERAHMHN "+
//					   "AND N.ID_NEGERI = P.ID_NEGERIMHN";
						
				sql += " AND S.ID_SIMATI = PS.ID_SIMATI "+
					   " AND P.ID_PEMOHON = PM.ID_PEMOHON "+
				       " AND P.ID_STATUS = ST.ID_STATUS(+) ";
//				sql += " AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur "+
//						   " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= '"+usid+"' ";
//						   
//						    sql += " UNION "+                                                                            
//							" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
//							" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
//							" WHERE ID_STATUS = 2  "+ 
//							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
//							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
//							" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
//							" AND PBU.ID_PEMOHON = "+usid+"  ";
//						   
//						   sql += " ) ";
					sql += " ORDER BY P.TARIKH_MOHON";
		   
			myLogger.info("SQL LIST NO_FAIL :"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				
				if (rs.getString("NAMA_STATUS") == null) {
					h.put("NAMA_STATUS", "");
				} else {
					h.put("NAMA_STATUS", rs.getString("NAMA_STATUS"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				if (rs.getString("NO_KP_BARU") == null) {
					h.put("NO_KP_BARU", "");
				} else {
					h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
				}
				if (rs.getString("NO_KP_LAMA") == null) {
					h.put("NO_KP_LAMA", "");
				} else {
					h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
				}
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL"));
				}
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("TARIKH_MOHON") == null) {
					h.put("TARIKH_MOHON", "");
				} else {
					h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
				if (rs.getString("NAMA_SIMATI") == null) {
					h.put("NAMA_SIMATI", "");
				} else {
					h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
				}
				
				if (rs.getString("NAMA_PEMOHON") == null) {
					h.put("NAMA_PEMOHON", "");
				} else {
					h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON"));
				}
				
				
				
				search_bantahan.addElement(h);
			}
			return search_bantahan;
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector search_nofail = null;
	
	public Vector search_nofail(String no_fail,String usid,String txtIcPemohon, String txtNamaSimati, String txtNamaPemohon) throws Exception {
		search_nofail = new Vector();
		search_nofail.clear();
		Db db = null;
		String sql = "";
	try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT ST.KETERANGAN AS NAMA_STATUS, ST.ID_STATUS, F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN,PM.NO_KP_BARU,PM.NO_KP_LAMA," +
					  " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,S.NAMA_SIMATI,PM.NAMA_PEMOHON,D.NAMA_DAERAH || ', '|| N.NAMA_NEGERI AS LOKASI_PERMOHONAN "+
					  " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST, TBLRUJDAERAH D, TBLRUJNEGERI N "+
					  " WHERE F.ID_FAIL = P.ID_FAIL "+
					  " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN ";
					
				sql += " AND ( trim(F.NO_FAIL) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_BARU) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_LAMA) LIKE '%"+no_fail.trim()+"%' ";
				sql += " OR trim(S.NO_KP_LAIN) LIKE '%"+no_fail.trim()+"%') ";
				
				if(!txtIcPemohon.trim().equals("") && txtIcPemohon.trim()!=null){
				sql += " AND ( trim(PM.NO_KP_BARU) LIKE '%"+txtIcPemohon.trim()+"%' ";
				sql += " OR trim(PM.NO_KP_LAMA) LIKE '%"+txtIcPemohon.trim()+"%' )";
				}
				
				if(!txtNamaSimati.trim().equals("") && txtNamaSimati.trim()!=null){
					sql += " AND ( trim(S.NAMA_SIMATI) LIKE '%"+txtNamaSimati.trim()+"%' ) ";
				}
				
				if(!txtNamaPemohon.trim().equals("") && txtNamaPemohon.trim()!=null){
					sql += " AND ( trim(S.NAMA_SIMATI) LIKE '%"+txtNamaPemohon.trim()+"%' ) ";
				}
				
				sql += "AND D.ID_DAERAH = P.ID_DAERAHMHN "+
					   "AND N.ID_NEGERI = P.ID_NEGERIMHN";
						
				sql += " AND S.ID_SIMATI = PS.ID_SIMATI "+
					   " AND P.ID_PEMOHON = PM.ID_PEMOHON "+
				       " AND P.ID_STATUS = ST.ID_STATUS(+) ";
				sql += " AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur "+
						   " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= '"+usid+"' ";
						   
						    sql += " UNION "+                                                                            
							" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
							" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
							" WHERE ID_STATUS = 2  "+ 
							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
							" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
							" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
							" AND PBU.ID_PEMOHON = "+usid+"  ";
						   
						   sql += " ) ";
					sql += " ORDER BY P.TARIKH_MOHON";
		   
			myLogger.info("SQL LIST NO_FAIL :"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				
				if (rs.getString("NAMA_STATUS") == null) {
					h.put("NAMA_STATUS", "");
				} else {
					h.put("NAMA_STATUS", rs.getString("NAMA_STATUS"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				if (rs.getString("NO_KP_BARU") == null) {
					h.put("NO_KP_BARU", "");
				} else {
					h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
				}
				if (rs.getString("NO_KP_LAMA") == null) {
					h.put("NO_KP_LAMA", "");
				} else {
					h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
				}
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL"));
				}
				if (rs.getString("LOKASI_PERMOHONAN") == null) {
					h.put("LOKASI_PERMOHONAN", "");
				} else {
					h.put("LOKASI_PERMOHONAN", rs.getString("LOKASI_PERMOHONAN"));
				}
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("TARIKH_MOHON") == null) {
					h.put("TARIKH_MOHON", "");
				} else {
					h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
				if (rs.getString("NAMA_SIMATI") == null) {
					h.put("NAMA_SIMATI", "");
				} else {
					h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
				}
				
				if (rs.getString("NAMA_PEMOHON") == null) {
					h.put("NAMA_PEMOHON", "");
				} else {
					h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON"));
				}
				
				
				
				search_nofail.addElement(h);
			}
			return search_nofail;
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteSub(String id_suburusanstatusfail) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_SUBURUSANSTATUSFAIL = "
					+ id_suburusanstatusfail;
			
			myLogger.info("DELETE SUBURUSANSTATUSFAIL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteBayaran(String id_bayaran) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblppkbayaran WHERE id_bayaran = "
					+ id_bayaran;
			
			myLogger.info("DELETE id_bayaran :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteData(HttpSession session,String id_status,String id_fail) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql11 = "";
		String update_audit = "";
		String hapus_pendaftaran = "";
		String hapus_keputusanpermohonan = "";
		String hapus_notis = "";
		String hapus_keputusanperbicaraan = "";
		String hapus_perintah = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String no_fail_temp = "";
							
				sql11 = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
				ResultSet rs = stmt.executeQuery(sql11);	
				myLogger.info("SQL no_fail_temp :"+sql11);
				while (rs.next()){				
					no_fail_temp = rs.getString("NO_FAIL");				
			    }
			myLogger.info("no_fail_temp :"+no_fail_temp);
			
			if(id_status.equals("8") || id_status.equals("9") || id_status.equals("170"))
			{
				
				
			sql = " delete from TBLEDITAGIHAN where ID_FAIL in ('"+id_fail+"')";		
					myLogger.info("DELETE TBLEDITAGIHAN :"+sql.toUpperCase());
					stmt.executeUpdate(sql);
					
			sql = " delete from TBLEDITNOTIFIKASI where ID_FAIL in ('"+id_fail+"')";		
					myLogger.info("DELETE TBLEDITNOTIFIKASI :"+sql.toUpperCase());
					stmt.executeUpdate(sql);
					
			sql = " delete from TBLEDITCOMMENT where ID_FAIL in ('"+id_fail+"')";		
					myLogger.info("DELETE TBLEDITCOMMENT :"+sql.toUpperCase());
					stmt.executeUpdate(sql);
				
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			//set temp in ID_NOTISOBMST
			sql = " select distinct ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("set temp in ID_NOTISOBMST :"+sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			String ID_NOTISOBMST_TEMP = "";
			while (rs.next()){	
					 if(rs.getString("ID_NOTISOBMST")!= null && !rs.getString("ID_NOTISOBMST").equals(""))
					 {
						 ID_NOTISOBMST_TEMP += "'"+rs.getString("ID_NOTISOBMST")+"',";
					 }
			}
			ID_NOTISOBMST_TEMP += "'0000000'";
			myLogger.info("ID_NOTISOBMST_TEMP :::"+ID_NOTISOBMST_TEMP);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			/*
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			*/
			
			
							
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
	            " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);		
				
			/*	
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ( "+
				  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
				  "	in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());	
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
              " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	*/
				
			
			sql = " delete from TBLPPKKEPUTUSANPERMOHONAN where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE TBLPPKKEPUTUSANPERMOHONAN :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from TBLPPKOBPILIHANHA where id_pilihanha in (" +
			" select id_pilihanha from tblppkpilihanha where id_permohonansimati in ( "+
			      " select id_permohonansimati from tblppkpermohonansimati where "+
			      " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppkobpilihanha :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
				
				sql = " delete from tblppkpilihanha where id_permohonansimati in ( "+
			      " select id_permohonansimati from tblppkpermohonansimati where "+
			      " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkpilihanha :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from TBLPPKOBPILIHANHTA where id_pilihanhta in (" +
					" select id_pilihanhta from tblppkpilihanhta where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
		myLogger.info("DELETE tblppkobpilihanhta :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
			
			sql = " delete from tblppkpilihanhta where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkpilihanhta :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
			
			sql = " delete from tblppkhapermohonan where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkhapermohonan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
							
			sql = " delete from tblppkha where id_permohonansimati in ( "+
			      " select id_permohonansimati from tblppkpermohonansimati where "+
			      " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkha :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppkhtapermohonan where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkhtapermohonan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
				
			sql = " delete from tblppkhta where id_permohonansimati in ( "+
			      " select id_permohonansimati from tblppkpermohonansimati where "+
			      " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkhta :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppkhubunganobpermohonan where id_permohonansimati in" +
			"  (select id_permohonansimati from tblppkpermohonansimati where "+
			  " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkhubunganobpermohonan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppkhubunganob where id_ob in" +
					"  (select id_ob from tblppkob where id_permohonansimati in ( "+
			  " select id_permohonansimati from tblppkpermohonansimati where "+
			  " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppkhubunganob :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from tblppkobpermohonan where id_permohonansimati in ( "+
			  " select id_permohonansimati from tblppkpermohonansimati where "+
			  " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkobpermohonan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
				
			sql = " delete from tblppkob where id_permohonansimati in ( "+
				  " select id_permohonansimati from tblppkpermohonansimati where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkob :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from tblsemakanhantar where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblsemakanhantar :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from tblppkbayaran where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblppkbayaran :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			
			sql = "SELECT COUNT(*) AS TOTAL_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_SIMATI IN ( "+
			"	SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P,TBLPFDFAIL F "+
			"	WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"+id_fail+"' ) ";		
			myLogger.info("SQL MAIN FAIL DETAIL :" + sql);			
			ResultSet rs1 = stmt.executeQuery(sql);
			Integer total_permohonansimati = 0;
			while (rs1.next()) {
				total_permohonansimati = rs1.getInt("TOTAL_PERMOHONANSIMATI");
			}
			
			if(total_permohonansimati == 1)
			{
			sql = " delete from tblppksimati where id_simati in ( "+
				  " select id_simati from tblppkpermohonansimati where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppksimati :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			}
				
			sql = " delete from tblppkpermohonansimati where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblppkpermohonansimati :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from TBLPPKPEGUAMPEMOHON where id_pemohon in ( "+
			" select id_pemohon from tblppkpermohonan where id_fail in ( "+
			" select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblppkpemohon :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			if(total_permohonansimati == 1)
			{	
		    sql = " delete from tblppkpemohon where id_pemohon in ( "+
			" select id_pemohon from tblppkpermohonan where id_fail in ( "+
			" select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblppkpemohon :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			}
				
			sql = " delete from tblppkpermohonan where id_fail in ( "+
			" select id_fail from tblpfdfail where id_fail in "+
			" ('"+id_fail+"'))";		
			myLogger.info("DELETE tblppkpermohonan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
			      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
				  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
				  " and ssf.ID_FAIL = f.id_fail "+
				  " and ss.ID_STATUS = s.id_status "+
				  " and f.id_fail = '"+id_fail+"' ";
			//sql +=" and s.id_status in ()";
			sql +=" ) ";
			myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblpfdfail where id_fail in ('"+id_fail+"')";		
			myLogger.info("DELETE tblpfdfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			update_audit = "yes";
			hapus_pendaftaran = "yes";
			
			
			
				
			}
			if(id_status.equals("151") || id_status.equals("152") || id_status.equals("14") || id_status.equals("53") || id_status.equals("70")
					|| id_status.equals("8") || id_status.equals("9") || id_status.equals("170"))
			{
			
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			//set temp in ID_NOTISOBMST
			sql = " select distinct ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("set temp in ID_NOTISOBMST :"+sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			String ID_NOTISOBMST_TEMP = "";
			while (rs.next()){	
					 if(rs.getString("ID_NOTISOBMST")!= null && !rs.getString("ID_NOTISOBMST").equals(""))
					 {
						 ID_NOTISOBMST_TEMP += "'"+rs.getString("ID_NOTISOBMST")+"',";
					 }
			}
			ID_NOTISOBMST_TEMP += "'0000000'";
			myLogger.info("ID_NOTISOBMST_TEMP :::"+ID_NOTISOBMST_TEMP);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			/*
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			*/
			
			
				
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
	            " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			sql = " delete from TBLPPKKEPUTUSANPERMOHONAN where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE TBLPPKKEPUTUSANPERMOHONAN :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
		      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
			  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
			  " and ssf.ID_FAIL = f.id_fail "+
			  " and ss.ID_STATUS = s.id_status "+
			  " and f.id_fail = '"+id_fail+"' ";
			sql +=" and s.id_status in ('41','21','25','151','152','14','53','70','18','44','47')";
			sql +=" ) ";
			myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			update_audit = "yes";			
			hapus_keputusanpermohonan = "yes";
						
			}
			if(id_status.equals("18")
					|| id_status.equals("8") || id_status.equals("9") || id_status.equals("170")
					|| id_status.equals("151") || id_status.equals("152") || id_status.equals("14") || id_status.equals("53") || id_status.equals("70"))
			{
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
				sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			//set temp in ID_NOTISOBMST
			sql = " select distinct ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("set temp in ID_NOTISOBMST :"+sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			String ID_NOTISOBMST_TEMP = "";
			while (rs.next()){	
					 if(rs.getString("ID_NOTISOBMST")!= null && !rs.getString("ID_NOTISOBMST").equals(""))
					 {
						 ID_NOTISOBMST_TEMP += "'"+rs.getString("ID_NOTISOBMST")+"',";
					 }
			}
			ID_NOTISOBMST_TEMP += "'0000000'";
			myLogger.info("ID_NOTISOBMST_TEMP :::"+ID_NOTISOBMST_TEMP);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			/*
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ( "+
			  "	select ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan "+
			  "	in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			*/
			
			
				
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
                  " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
		      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
			  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
			  " and ssf.ID_FAIL = f.id_fail "+
			  " and ss.ID_STATUS = s.id_status "+
			  " and f.id_fail = '"+id_fail+"' ";
			sql +=" and s.id_status in ('41','21','25','18','44','47')";
			sql +=" ) ";
			myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			update_audit = "yes";		
			hapus_notis = "yes";
			
				
			}
			if(id_status.equals("44") || id_status.equals("47")
					|| id_status.equals("8") || id_status.equals("9") || id_status.equals("170")
					|| id_status.equals("151") || id_status.equals("152") || id_status.equals("14") || id_status.equals("53") || id_status.equals("70")
					|| id_status.equals("18"))
			{
			
				
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);		
				
			/*sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);*/
			
			sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
		      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
			  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
			  " and ssf.ID_FAIL = f.id_fail "+
			  " and ss.ID_STATUS = s.id_status "+
			  " and f.id_fail = '"+id_fail+"' ";
			sql +=" and s.id_status in ('41','21','25','44','47')";
			sql +=" ) ";
			myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			update_audit = "yes";		
			hapus_keputusanperbicaraan = "yes";
			
				
			}
			if(id_status.equals("41") || id_status.equals("21") || id_status.equals("25")
					|| id_status.equals("8") || id_status.equals("9") || id_status.equals("170")
					|| id_status.equals("151") || id_status.equals("152") || id_status.equals("14") || id_status.equals("53") || id_status.equals("70")
					|| id_status.equals("18")
					|| id_status.equals("44") || id_status.equals("47"))
			{
			
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			
			sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
		      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
			  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
			  " and ssf.ID_FAIL = f.id_fail "+
			  " and ss.ID_STATUS = s.id_status "+
			  " and f.id_fail = '"+id_fail+"' ";
			sql +=" and s.id_status in ('41','21','25')";
			sql +=" ) ";
			myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			update_audit = "yes";			
			hapus_perintah = "yes";				
			}			
			myLogger.info("DELETE DATA :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			
			conn.commit();			
			
			if(update_audit.equals("yes"))
			{
			AuditTrail at = new AuditTrail();
			if(hapus_pendaftaran.equals("yes"))
			{			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT PENDAFTARAN DIHAPUSKAN");
			}
			if(hapus_keputusanpermohonan.equals("yes"))
			{			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT KEPUTUSAN PERMOHONAN APUSKAN");
			}
			if(hapus_notis.equals("yes"))
			{			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT NOTIS PERBICARAAN DIHAPUSKAN");
			}
			if(hapus_keputusanperbicaraan.equals("yes"))
			{			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT KEPUTUSAN PERBICARAAN DIHAPUSKAN");
			}
			if(hapus_perintah.equals("yes"))
			{			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT PERINTAH DIHAPUSKAN");
			}			
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void insertSub(String ID_PERMOHONAN,String ID_SUBURUSANSTATUS,String AKTIF,String ID_FAIL,String user_id) throws Exception {
		Db db = null;
		String sql = "";
		String sql2 = "";
	
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(AKTIF.equals(""))
			{
				AKTIF="0";				
			}

			r.add("ID_PERMOHONAN", ID_PERMOHONAN);
			r.add("ID_SUBURUSANSTATUS", ID_SUBURUSANSTATUS);
			r.add("ID_FAIL", ID_FAIL);
			r.add("AKTIF", AKTIF);
			r.add("ID_MASUK", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			myLogger.info("SQL INSERT SUB :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			if(AKTIF.equals("1"))
			{
			r.clear();					
			sql2=" UPDATE TBLPPKPERMOHONAN SET ID_STATUS = " +
					" (SELECT  ID_STATUS  FROM TBLRUJSUBURUSANSTATUS " +
					" WHERE ID_SUBURUSANSTATUS = '"+ID_SUBURUSANSTATUS+"'), " +
							" ID_KEMASKINI = '"+user_id+"'  , " +
				 " TARIKH_KEMASKINI = sysdate "; 
							
							
							if(ID_SUBURUSANSTATUS.equals("355") || ID_SUBURUSANSTATUS.equals("358") 
									|| ID_SUBURUSANSTATUS.equals("425") || ID_SUBURUSANSTATUS.equals("398")
									|| ID_SUBURUSANSTATUS.equals("303") || ID_SUBURUSANSTATUS.equals("407")
									|| ID_SUBURUSANSTATUS.equals("273") || ID_SUBURUSANSTATUS.equals("429"))
							{
							sql2+=", FLAG_PERMOHONAN = '' ";
							}
							
			sql2+=" WHERE ID_FAIL = '"+ID_FAIL+"' ";  
			myLogger.info("UPDATE PERMOHONAN :"+sql2);
			stmt.executeUpdate(sql2);	
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public static void updateSSF(HttpSession session,String ID_SUBURUSANSTATUSFAIL,String ID_SUBURUSANSTATUS,String AKTIF,String USER_ID) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";
		String sql2="";
				
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if(AKTIF.equals(""))
			{
				AKTIF = "0";
			}
					
					r.update("ID_SUBURUSANSTATUSFAIL",ID_SUBURUSANSTATUSFAIL);
					r.add("ID_SUBURUSANSTATUS",ID_SUBURUSANSTATUS);
					r.add("AKTIF", AKTIF);
					//r.add("ID_MASUK", USER_ID);						
					r.add("ID_KEMASKINI",USER_ID);						
					//r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");	
					myLogger.info("UPDATE SSF :"+sql1);
					stmt.executeUpdate(sql1);
					
					if(AKTIF.equals("1"))
					{
					r.clear();					
					sql2=" UPDATE TBLPPKPERMOHONAN SET ID_STATUS = (SELECT  ID_STATUS  FROM TBLRUJSUBURUSANSTATUS WHERE ID_SUBURUSANSTATUS = '"+ID_SUBURUSANSTATUS+"'), ID_KEMASKINI = '"+USER_ID+"' , " +
						 " TARIKH_KEMASKINI = sysdate " ; 
					
					//355,358,425,398,303,407,273,429
					if(ID_SUBURUSANSTATUS.equals("355") || ID_SUBURUSANSTATUS.equals("358") 
							|| ID_SUBURUSANSTATUS.equals("425") || ID_SUBURUSANSTATUS.equals("398")
							|| ID_SUBURUSANSTATUS.equals("303") || ID_SUBURUSANSTATUS.equals("407")
							|| ID_SUBURUSANSTATUS.equals("273") || ID_SUBURUSANSTATUS.equals("429"))
					{
					sql2+=", FLAG_PERMOHONAN = '' ";
					}
					
					sql2+=" WHERE ID_PERMOHONAN = (SELECT ID_PERMOHONAN FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_SUBURUSANSTATUSFAIL = '"+ID_SUBURUSANSTATUSFAIL+"') ";  
					myLogger.info("UPDATE PERMOHONAN :"+sql2);
					stmt.executeUpdate(sql2);	
					}
					conn.commit();
	} catch (SQLException se) {
		myLogger.error(se);
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} catch (Exception er) {
		myLogger.error(er);
		throw er;
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	public Hashtable getMainFail(String ID_FAIL) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT P.CATATAN_KEBENARAN_EDIT,U.USER_NAME,P.USER_ID_KEBENARAN_EDIT,P.FLAG_KEBENARAN_EDIT,P.ID_STATUS,F.ID_FAIL,P.ID_PERMOHONAN,F.NO_FAIL,F.ID_SUBURUSAN " +
					" FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,USERS U "+
				  " WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_FAIL = '"+ID_FAIL+"' AND P.USER_ID_KEBENARAN_EDIT = U.USER_ID(+) ";	
			
			myLogger.info("SQL MAIN FAIL DETAIL :" + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				
				if (rs.getString("CATATAN_KEBENARAN_EDIT") == null) {
					h.put("CATATAN_KEBENARAN_EDIT","");
				} else {
					h.put("CATATAN_KEBENARAN_EDIT", rs.getString("CATATAN_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME","");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME").toUpperCase());
				}
				
				if (rs.getString("USER_ID_KEBENARAN_EDIT") == null) {
					h.put("USER_ID_KEBENARAN_EDIT","");
				} else {
					h.put("USER_ID_KEBENARAN_EDIT", rs.getString("USER_ID_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("FLAG_KEBENARAN_EDIT") == null) {
					h.put("FLAG_KEBENARAN_EDIT", "");
				} else {
					h.put("FLAG_KEBENARAN_EDIT", rs.getString("FLAG_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL").trim());
				}
				
				if (rs.getString("ID_SUBURUSAN") == null) {
					h.put("ID_SUBURUSAN", "");
				} else {
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
			}
			return h;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
public Hashtable getMainFail_bicara_semula(String ID_FAIL) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT S.KETERANGAN AS STATUS," +
					" P.FLAG_KEBENARAN_BICARA_SEMULA,P.ID_STATUS,F.ID_FAIL,P.ID_PERMOHONAN,F.NO_FAIL," +
					" F.ID_SUBURUSAN," +
					
					" P.CATATAN_BICARA_SEMULA,U.USER_NAME,P.USER_ID_BICARA_SEMULA,P.ID_PERMOHONAN_SEBELUM_BICARA," +
					" (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
					" AS NO_FAIL_SEBELUM_BICARA, " +
					" (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
					" AS ID_STATUS_SEBELUM_BICARA, " +
					" (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP  " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA) " +
					" AS SEKSYEN_SEBELUM_BICARA, " +
					" (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) " +
					" AS ID_PERMOHONANSIMATI_SEBELUM, " +
					" P.ID_PERMOHONAN_SELEPAS_BICARA, " +
					" (SELECT NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
					" AS NO_FAIL_SELEPAS_BICARA, " +
					" (SELECT PP.ID_STATUS FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
					" AS ID_STATUS_SELEPAS_BICARA, " +
					" (SELECT PP.SEKSYEN FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA) " +
					" AS SEKSYEN_SELEPAS_BICARA, " +
					" (SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PP,TBLPPKPERMOHONANSIMATI PSM  " +
					" WHERE FF.ID_FAIL = PP.ID_FAIL AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA AND PP.ID_PERMOHONAN = PSM.ID_PERMOHONAN) " +
					" AS ID_PERMOHONANSIMATI_SELEPAS, " +
					
					"TO_CHAR(( SELECT FF.NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PX " +
							"WHERE FF.ID_FAIL = PX.ID_FAIL " +
							"AND PX.ID_PERMOHONAN = P.ID_PERMOHONAN_SEBELUM_BICARA)) AS NO_FAIL_SEBELUM, " +
					"TO_CHAR(( SELECT FF.NO_FAIL FROM TBLPFDFAIL FF,TBLPPKPERMOHONAN PX " +
							"WHERE FF.ID_FAIL = PX.ID_FAIL " +
							"AND PX.ID_PERMOHONAN = P.ID_PERMOHONAN_SELEPAS_BICARA)) AS NO_FAIL_SELEPAS,SSM.ID_PERMOHONANSIMATI,P.SEKSYEN " +
					"FROM TBLRUJSTATUS S,TBLPFDFAIL F,TBLPPKPERMOHONAN P,USERS U,TBLPPKPERMOHONANSIMATI SSM " +
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = SSM.ID_PERMOHONAN AND " +
					 " P.ID_STATUS = S.ID_STATUS(+) AND P.USER_ID_BICARA_SEMULA = U.USER_ID(+) AND F.ID_FAIL = '"+ID_FAIL+"'    ";
			//myLogger.info("SQL GET MAKLUMAT FAIL BICARA :" + sql);
			
			
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("STATUS") == null) {
					h.put("STATUS","");
				} else {
					h.put("STATUS", rs.getString("STATUS"));
				}
				
				if (rs.getString("NO_FAIL_SEBELUM") == null) {
					h.put("NO_FAIL_SEBELUM","");
				} else {
					h.put("NO_FAIL_SEBELUM", rs.getString("NO_FAIL_SEBELUM"));
				}
				
				if (rs.getString("NO_FAIL_SELEPAS") == null) {
					h.put("NO_FAIL_SELEPAS","");
				} else {
					h.put("NO_FAIL_SELEPAS", rs.getString("NO_FAIL_SELEPAS"));
				}
				
				if (rs.getString("ID_PERMOHONAN_SEBELUM_BICARA") == null) {
					h.put("ID_PERMOHONAN_SEBELUM_BICARA","");
				} else {
					h.put("ID_PERMOHONAN_SEBELUM_BICARA", rs.getString("ID_PERMOHONAN_SEBELUM_BICARA"));
				}
				
				if (rs.getString("ID_PERMOHONAN_SELEPAS_BICARA") == null) {
					h.put("ID_PERMOHONAN_SELEPAS_BICARA","");
				} else {
					h.put("ID_PERMOHONAN_SELEPAS_BICARA", rs.getString("ID_PERMOHONAN_SELEPAS_BICARA"));
				}
				
				if (rs.getString("CATATAN_BICARA_SEMULA") == null) {
					h.put("CATATAN_BICARA_SEMULA","");
				} else {
					h.put("CATATAN_BICARA_SEMULA", rs.getString("CATATAN_BICARA_SEMULA"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME","");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME").toUpperCase());
				}
				
				if (rs.getString("USER_ID_BICARA_SEMULA") == null) {
					h.put("USER_ID_BICARA_SEMULA","");
				} else {
					h.put("USER_ID_BICARA_SEMULA", rs.getString("USER_ID_BICARA_SEMULA"));
				}
				
				if (rs.getString("FLAG_KEBENARAN_BICARA_SEMULA") == null) {
					h.put("FLAG_KEBENARAN_BICARA_SEMULA", "");
				} else {
					h.put("FLAG_KEBENARAN_BICARA_SEMULA", rs.getString("FLAG_KEBENARAN_BICARA_SEMULA"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL").trim());
				}
				
				if (rs.getString("ID_SUBURUSAN") == null) {
					h.put("ID_SUBURUSAN", "");
				} else {
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
				if (rs.getString("NO_FAIL_SELEPAS_BICARA") == null) {
					h.put("NO_FAIL_SELEPAS_BICARA", "");
				} else {
					h.put("NO_FAIL_SELEPAS_BICARA", rs.getString("NO_FAIL_SELEPAS_BICARA"));
				}
				
				if (rs.getString("NO_FAIL_SEBELUM_BICARA") == null) {
					h.put("NO_FAIL_SEBELUM_BICARA", "");
				} else {
					h.put("NO_FAIL_SEBELUM_BICARA", rs.getString("NO_FAIL_SEBELUM_BICARA"));
				}
								
				if (rs.getString("ID_PERMOHONANSIMATI") == null) {
					h.put("ID_PERMOHONANSIMATI", "");
				} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI"));
				}
				
				if (rs.getString("SEKSYEN") == null) {
					h.put("SEKSYEN", "");
				} else {
					h.put("SEKSYEN", rs.getString("SEKSYEN"));
				}
							
				if (rs.getString("ID_STATUS_SEBELUM_BICARA") == null) {
					h.put("ID_STATUS_SEBELUM_BICARA", "");
				} else {
					h.put("ID_STATUS_SEBELUM_BICARA", rs.getString("ID_STATUS_SEBELUM_BICARA"));
				}
				
				if (rs.getString("SEKSYEN_SEBELUM_BICARA") == null) {
					h.put("SEKSYEN_SEBELUM_BICARA", "");
				} else {
					h.put("SEKSYEN_SEBELUM_BICARA", rs.getString("SEKSYEN_SEBELUM_BICARA"));
				}
				
				if (rs.getString("ID_PERMOHONANSIMATI_SEBELUM") == null) {
					h.put("ID_PERMOHONANSIMATI_SEBELUM", "");
				} else {
					h.put("ID_PERMOHONANSIMATI_SEBELUM", rs.getString("ID_PERMOHONANSIMATI_SEBELUM"));
				}
				
				
				
				if (rs.getString("ID_STATUS_SELEPAS_BICARA") == null) {
					h.put("ID_STATUS_SELEPAS_BICARA", "");
				} else {
					h.put("ID_STATUS_SELEPAS_BICARA", rs.getString("ID_STATUS_SELEPAS_BICARA"));
				}
				
				if (rs.getString("SEKSYEN_SELEPAS_BICARA") == null) {
					h.put("SEKSYEN_SELEPAS_BICARA", "");
				} else {
					h.put("SEKSYEN_SELEPAS_BICARA", rs.getString("SEKSYEN_SELEPAS_BICARA"));
				}
				
				if (rs.getString("ID_PERMOHONANSIMATI_SELEPAS") == null) {
					h.put("ID_PERMOHONANSIMATI_SELEPAS", "");
				} else {
					h.put("ID_PERMOHONANSIMATI_SELEPAS", rs.getString("ID_PERMOHONANSIMATI_SELEPAS"));
				}
				
			}
			return h;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable getCountHta(String ID_FAIL) throws Exception {		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) AS JUMLAH_HTA FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, "+
				" TBLPPKPERMOHONANSIMATI PS,TBLPPKHTA HTA,TBLPPKSIMATI S "+
				" WHERE F.ID_FAIL = P.ID_FAIL "+
				" AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN "+
				" AND S.ID_SIMATI = PS.ID_SIMATI "+
				" AND S.ID_SIMATI = HTA.ID_SIMATI "+
				  " AND F.ID_FAIL = '"+ID_FAIL+"' ";	
			
			myLogger.info("SQL COUNT HTA :" + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("JUMLAH_HTA") == null) {
					h.put("JUMLAH_HTA", "");
				} else {
					h.put("JUMLAH_HTA", rs.getString("JUMLAH_HTA"));
				}
			}
			return h;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable getCountHa(String ID_FAIL) throws Exception {		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) AS JUMLAH_HA FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, "+
				" TBLPPKPERMOHONANSIMATI PS,TBLPPKHA HA,TBLPPKSIMATI S "+
				" WHERE F.ID_FAIL = P.ID_FAIL "+
				" AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN "+
				" AND S.ID_SIMATI = PS.ID_SIMATI "+
				" AND S.ID_SIMATI = HA.ID_SIMATI "+
				  " AND F.ID_FAIL = '"+ID_FAIL+"' ";	
			
			myLogger.info("SQL COUNT HA :" + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("JUMLAH_HA") == null) {
					h.put("JUMLAH_HA", "");
				} else {
					h.put("JUMLAH_HA", rs.getString("JUMLAH_HA"));
				}
			}
			return h;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	//semak status 
	public static void setList(String id_fail, String usid)throws Exception {
		
	    Db db = null;
	    list.clear();
	    String sql = "";
	    String sqlx = "";
	    
	    try {
	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		//String chkDataFail = noFail.trim();
	      
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif, a.seksyen ";
	    		sql += " FROM Tblppkpermohonansimati psm, Tblppkpermohonan a, Tblpfdfail f, Tblrujstatus s, Tblppksimati p, ";
	    		sql += " Tblppkpemohon pp,tblrujdaerah d, tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE d.id_daerah in ( select distinct u.id_daerahurus from ";
	    		sql += " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= " +usid+ " ";
				
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				
				sql+=" ) ";
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
	    		sql += " AND UPPER(F.ID_FAIL) = '" + id_fail + "'";
	    		//sql += " AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL)";
	    		myLogger.info("setList ::::::"+sql);
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
	      
	      
	      //return list;
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list
	
/*	
	 public static void tukarstatus(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	   
	      String id_permohonan = (String)data.get("id_permohonan");
	      String id_fail = (String)data.get("id_fail");
	      String id_suburusanstatusfail = (String)data.get("id_suburusanstatusfail");	      
	      String id_masuk = (String)data.get("id_masuk");
	      String id_suburusanstatus = (String)data.get("id_suburusanstatus");	
	      
	      db = new Db();
	      
	      
	      	Statement stmtP = db.getStatement();
	      	SQLRenderer rP = new SQLRenderer();				 
	      	rP.update("id_permohonan",id_permohonan);
	      	rP.add("id_status", 21);	
	      	rP.add("id_kemaskini",id_masuk);
	      	rP.add("tarikh_kemaskini", rP.unquote("sysdate"));
	      	sql = rP.getSQLUpdate("Tblppkpermohonan");
	      	stmtP.executeUpdate(sql);

	      	//update n add tblrujsuburusanstatus
			Statement stmtX = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_Suburusanstatusfail",id_suburusanstatusfail);	
			r.add("AKTIF",0);
			r.add("ID_KEMASKINI",id_masuk);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("tblrujsuburusanstatusfail");
			stmtX.executeUpdate(sql1);	 
	   
			Statement stmtF = db.getStatement();
			SQLRenderer rStf = new SQLRenderer();
			rStf.add("ID_SUBURUSANSTATUSFAIL",DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ"));
			rStf.add("ID_PERMOHONAN",id_permohonan);
			rStf.add("ID_FAIL",id_fail);
			rStf.add("ID_SUBURUSANSTATUS",id_suburusanstatus);
			rStf.add("AKTIF",1);
			rStf.add("ID_MASUK",id_masuk);
			rStf.add("TARIKH_MASUK",rStf.unquote("sysdate"));	
			sql2 = rStf.getSQLInsert("tblrujsuburusanstatusfail");
			stmtF.executeUpdate(sql2);	
	      
	      
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close tukarstatus
*/
	
	 //Azam Add
	 public static void hapusfail(Hashtable data) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    try
	    {
	   
	      String id_permohonan = (String)data.get("id_permohonan");
	      String id_fail = (String)data.get("id_fail");
	      String id_masuk = (String)data.get("id_masuk");
	      
	      db = new Db();
		  SQLRenderer r = new SQLRenderer();	
		  
		  r.update("id_fail",id_fail);
		  r.add("id_status", 999);	
		  r.add("id_kemaskini",id_masuk);
		  r.add("tarikh_kemaskini", r.unquote("sysdate"));
		  sql = r.getSQLUpdate("tblpfdfail");
		  db.getStatement().executeUpdate(sql);
		  
		  r.clear();
		  
		  r.update("id_permohonan",id_permohonan);
		  r.add("id_status", 999);	
		  r.add("id_kemaskini",id_masuk);
		  r.add("tarikh_kemaskini", r.unquote("sysdate"));
		  sql = r.getSQLUpdate("Tblppkpermohonan");
		  db.getStatement().executeUpdate(sql);
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    }//close try 
	    
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close hapusfail
	
	 
	 public static void tukarKE(Hashtable data) throws Exception
	  {
		 
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String lvl = (String)data.get("level");
	    
	    int level = 0;
	    
	    if(lvl!=""){
	    	level = Integer.parseInt(lvl);
	    }
	    
	    try
	    {
	    	
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    	} finally {
	    		if (db != null) db.close();
	    	}
	  	}
	 
	 
	 public static void tukarKE_sub(Hashtable data) throws Exception
	  {
		 
	    Db db = null;
	    Connection conn = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    String sql_htamst = "";
	    String sql_hamst = "";
	    String lvl = (String)data.get("level");
	    
	    int level = 0;
	    
	    if(lvl!=""){
	    	level = Integer.parseInt(lvl);
	    }
	    
	    try
	    {
	    	
	    		// LEVEL   
	  	  		/*  1 = PERMOHONAN DITERUSKAN 
	  	      		2 = NOTIS PERBICARAAN 
	  	      		3 = SELESAI PERBICARAAN 
	  	      		4 = PERMOHONAN SELESAI 	*/
	    	
	    			
	    		db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
	
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
	    				myLogger.info("getSQLInsert(tblppkkeputusanpermohonan) :"+sql);
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
	    				myLogger.info("getSQLInsert(Tblppkperbicaraan) :"+sql);
	    				stmtx.executeUpdate(sql);
			
			
	    				//create table notisobmst
	    				Statement stmtMST = db.getStatement();
	    				SQLRenderer rMST = new SQLRenderer();
	    				rMST.add("id_notisobmst", id_notisobmst);		     
	    				rMST.add("id_masuk",id_masuk);
	    				rMST.add("tarikh_masuk", rMST.unquote("sysdate"));	      
	    				sql = rMST.getSQLInsert("Tblppknotisobmst");
	    				myLogger.info("getSQLInsert(Tblppknotisobmst) :"+sql);
	    				stmtMST.executeUpdate(sql);
			      
	    				//create child table
	    				Statement stmt1 = db.getStatement();
	    				SQLRenderer r1 = new SQLRenderer();
	    				r1.add("id_perbicaraan", id_perbicaraan);
	    				r1.add("id_notisobmst", id_notisobmst); 		     
	    				r1.add("id_masuk",id_masuk);
	    				r1.add("tarikh_masuk", r1.unquote("sysdate"));
	    				sql = r1.getSQLInsert("Tblppknotisperbicaraan");
	    				myLogger.info("getSQLInsert(Tblppknotisperbicaraan) :"+sql);
	    				stmt1.executeUpdate(sql);
	    			}
			      
	    			long id_perintah = 0;
			
	    			if(idPerintah!=""){
	    				id_perintah = Long.parseLong(idPerintah);
	    			}else{
	    				id_perintah = DB.getNextID("TBLPPKPERINTAH_SEQ");
	    			}
			
			
	    			
	    			Vector list_hta = null;
	    			Vector list_ha = null;
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
	    				myLogger.info("getSQLInsert(tblppkperintah) :"+sql);
	    				stmtc.executeUpdate(sql);
			
	    				Statement stmt2 = db.getStatement();
	    				SQLRenderer r2 = new SQLRenderer();
	    				r2.add("id_bayaran",id_bayaran);
	    				r2.add("id_permohonan",id_permohonan);
	    				r2.add("id_jenisbayaran", "24");
	    				r2.add("id_masuk",id_masuk);
	    				r2.add("tarikh_masuk",r2.unquote("sysdate"));					
	    				sql2 = r2.getSQLInsert("tblppkbayaran");
	    				myLogger.info("getSQLInsert(tblppkbayaran) :"+sql2);
	    				stmt2.executeUpdate(sql2);
	    				
	    				list_hta = list_hta(id_permohonan);
	    				list_ha = list_ha(id_permohonan);
	    				
	    				if(list_hta.size()>0)
	    				{
	    					for(int i = 0; i < list_hta.size(); i++)
	    					{
	    						Hashtable h1 = (Hashtable) list_hta.get(i);
	    						myLogger.info("ID_HTA :"+h1.get("ID_HTA").toString());
	    						Statement stmt_htamst = db.getStatement();
	    	    				SQLRenderer r_htamst = new SQLRenderer();
	    	    				r_htamst.add("id_hta",h1.get("ID_HTA").toString());
	    	    				r_htamst.add("id_perintah",id_perintah);
	    	    				r_htamst.add("id_jenisperintah",1);
	    	    				r_htamst.add("flag_harta","B");	    	    				
	    	    				r_htamst.add("id_masuk",id_masuk);
	    	    				r_htamst.add("tarikh_masuk",r_htamst.unquote("sysdate"));
	    	    				r_htamst.add("id_kemaskini",id_masuk);
	    	    				r_htamst.add("tarikh_kemaskini",r_htamst.unquote("sysdate"));
	    	    				sql_htamst = r_htamst.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
	    	    				myLogger.info("getSQLInsert(TBLPPKPERINTAHHTAOBMST)("+h1.get("ID_HTA").toString()+") :"+sql_htamst);
	    	    				stmt_htamst.executeUpdate(sql_htamst);
	    					}
	    				}
	    				
	    				
	    				if(list_ha.size()>0)
	    				{
	    					for(int i = 0; i < list_ha.size(); i++)
	    					{
	    						Hashtable h2 = (Hashtable) list_hta.get(i);
	    						myLogger.info("ID_HA :"+h2.get("ID_HA").toString());
	    						Statement stmt_hamst = db.getStatement();
	    	    				SQLRenderer r_hamst = new SQLRenderer();
	    	    				r_hamst.add("id_ha",h2.get("ID_HA").toString());
	    	    				r_hamst.add("id_perintah",id_perintah);
	    	    				r_hamst.add("id_jenisperintah",1);
	    	    				r_hamst.add("flag_harta","B");	    	    				
	    	    				r_hamst.add("id_masuk",id_masuk);
	    	    				r_hamst.add("tarikh_masuk",r_hamst.unquote("sysdate"));
	    	    				r_hamst.add("id_kemaskini",id_masuk);
	    	    				r_hamst.add("tarikh_kemaskini",r_hamst.unquote("sysdate"));
	    	    				sql_hamst = r_hamst.getSQLInsert("TBLPPKPERINTAHHAOBMST");
	    	    				myLogger.info("getSQLInsert(TBLPPKPERINTAHHAOBMST)("+h2.get("ID_HA").toString()+") :"+sql_hamst);
	    	    				stmt_hamst.executeUpdate(sql_hamst);
	    					}
	    				}
			
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
	    		conn.commit();
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
	    	} finally {
	    		if (db != null) db.close();
	    	}
	  	}
	 
	 public static void updateANDinsertStatusFail_sub(Hashtable data) throws Exception
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
				r6.update("id_permohonan",id_permohonan);	
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
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
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
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
		    } finally {
		      if (db != null) db.close();
		    }
		  }//Get idkeputusanpermohonan
	 
	 
	
		public static Vector list_hta(String id_permohonan) throws Exception {
			Vector list_hta = null;
			list_hta = new Vector();
			list_hta.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT HTA.ID_HTA FROM TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKHTA HTA "+
					  " WHERE P.ID_PERMOHONAN = PS.ID_PERMOHONAN  "+
					  " AND PS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI AND P.ID_PERMOHONAN = '"+id_permohonan+"'";
				
				myLogger.info("CHECK HTA STATUS :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Hashtable h = new Hashtable();
					if (rs.getString("ID_HTA") == null) {
						h.put("ID_HTA", "");
					} else {
						h.put("ID_HTA", rs.getString("ID_HTA"));
					}
					list_hta.addElement(h);
				}
				return list_hta;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public static Vector list_ha(String id_permohonan) throws Exception {
			Vector list_ha = null;
			list_ha = new Vector();
			list_ha.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT HTA.ID_HA FROM TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKHA HTA "+
					  " WHERE P.ID_PERMOHONAN = PS.ID_PERMOHONAN  "+
					  " AND PS.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI AND P.ID_PERMOHONAN = '"+id_permohonan+"'";
				
				myLogger.info("CHECK HA STATUS :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Hashtable h = new Hashtable();
					if (rs.getString("ID_HA") == null) {
						h.put("ID_HA", "");
					} else {
						h.put("ID_HA", rs.getString("ID_HA"));
					}
					list_ha.addElement(h);
				}
				return list_ha;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		 public static Hashtable getSeksyen(String id_fail) throws Exception {
				
				Db db = null;
				String sql = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					sql = " SELECT P.SEKSYEN FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F WHERE P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL  = '"+id_fail+"'";		
					myLogger.info("getSeksyen :"+sql);	
					ResultSet rs = stmt.executeQuery(sql);
					
					Hashtable h;
					h = new Hashtable();
					while (rs.next()) {
						if (rs.getString("SEKSYEN") == null) {
							h.put("SEKSYEN", "");
						} else {
							h.put("SEKSYEN", rs.getString("SEKSYEN"));
						}
												
					}
					return h;
				} catch (Exception er) {
					myLogger.error(er);
					throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 Vector papar_list_simati = null;
			public Vector papar_list_simati(String id_fail) throws Exception {
				papar_list_simati = new Vector();
				papar_list_simati.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = " select ID_SIMATI,NAMA_SIMATI,NO_KP_BARU,NO_KP_LAMA,NO_KP_LAIN from tblppksimati where id_simati in ( "+
						  " select id_simati from tblppkpermohonansimati where id_permohonan in ( "+
						  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
						  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";			   
					
					myLogger.info("SQL LIST NAMA SIMATI :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					int bil=0;
					while (rs.next()) {
						Hashtable h = new Hashtable();
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("ID_SIMATI") == null) {
							h.put("ID_SIMATI", "");
						} else {
							h.put("ID_SIMATI", rs.getString("ID_SIMATI"));
						}
						
						if (rs.getString("NAMA_SIMATI") == null) {
							h.put("NAMA_SIMATI", "");
						} else {
							h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
						}
						
						if (rs.getString("NO_KP_BARU") == null) {
							h.put("NO_KP_BARU", "");
						} else {
							h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
						}
						
						if (rs.getString("NO_KP_LAMA") == null) {
							h.put("NO_KP_LAMA", "");
						} else {
							h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
						}
						
						if (rs.getString("NO_KP_LAIN") == null) {
							h.put("NO_KP_LAIN", "");
						} else {
							h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN"));
						}
						
						papar_list_simati.addElement(h);
					}
					return papar_list_simati;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			
			Vector papar_list_pemohon = null;
			public Vector papar_list_pemohon(String id_fail) throws Exception {
				papar_list_pemohon = new Vector();
				papar_list_pemohon.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = " select ID_PEMOHON,NAMA_PEMOHON,NO_KP_BARU,NO_KP_LAMA,NO_KP_LAIN from tblppkpemohon where id_pemohon in ( "+
						  " select id_pemohon from tblppkpermohonan where id_fail in ( "+
						  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";			   
					
					myLogger.info("SQL LIST NAMA PEMOHON :"+sql);
					int bil = 0;
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
						
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("ID_PEMOHON") == null) {
							h.put("ID_PEMOHON", "");
						} else {
							h.put("ID_PEMOHON", rs.getString("ID_PEMOHON"));
						}
						
						if (rs.getString("NAMA_PEMOHON") == null) {
							h.put("NAMA_PEMOHON", "");
						} else {
							h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON"));
						}
						
						if (rs.getString("NO_KP_BARU") == null) {
							h.put("NO_KP_BARU", "");
						} else {
							h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
						}
						
						if (rs.getString("NO_KP_LAMA") == null) {
							h.put("NO_KP_LAMA", "");
						} else {
							h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
						}
						
						if (rs.getString("NO_KP_LAIN") == null) {
							h.put("NO_KP_LAIN", "");
						} else {
							h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN"));
						}
						
						papar_list_pemohon.addElement(h);
					}
					return papar_list_pemohon;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			
			Vector list_tukar_pemohon = null;
			public Vector list_tukar_pemohon(String id_fail) throws Exception {
				list_tukar_pemohon = new Vector();
				list_tukar_pemohon.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
				/*	sql = " select STATUS_HIDUP,ID_OB,NAMA_OB,NO_KP_BARU,NO_KP_LAMA,ID_PEMOHON,NO_KP_LAIN from tblppkobpermohonan " +
							" where STATUS_HIDUP != '1' and umur >= 18 and id_permohonansimati in ( "+
						  " select id_permohonansimati from tblppkpermohonansimati where "+
						  " id_permohonan in ( "+
						  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
						  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";	*/
					
					
					sql = " SELECT TO_CHAR(CASE WHEN OB.STATUS_OB = '1' THEN 'DEWASA/WARAS' WHEN OB.STATUS_OB = '2' THEN 'BELUM DEWASA' WHEN OB.STATUS_OB = '3' THEN 'HILANG' "+
						" WHEN OB.STATUS_OB = '4' THEN 'TIDAK SEMPURNA AKAL' ELSE '' "+
						" END) AS NAMA_STATUS_OB,OB.STATUS_HIDUP,OB.ID_TARAFKPTG,OB.STATUS_OB, OB.ID_OB, OB.NAMA_OB, OB.NO_KP_BARU, OB.NO_KP_LAMA, OB.ID_PEMOHON, "+
						" OB.NO_KP_LAIN,T.KETERANGAN AS TARAF,OB.ID_PERMOHONANSIMATI " +
						"FROM TBLPPKOBPERMOHONAN OB,TBLPPKRUJTARAFKPTG T " +
						" WHERE OB.ID_TARAFKPTG = T.ID_TARAFKPTG(+) " +
						" AND NVL(OB.STATUS_HIDUP,0) != '1'  AND NVL(OB.UMUR,18) >= 18  " +
						" AND OB.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN IN ( "+
						" SELECT ID_PERMOHONAN FROM TBLPPKPERMOHONAN WHERE ID_FAIL IN (SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ('"+id_fail+"'))))" +
								" ORDER BY OB.ID_TARAFKPTG, OB.NAMA_OB ASC ";
					
					myLogger.info("SQL LIST NAMA OB :"+sql);
					
				    int bil = 0;
					
					
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("TARAF") == null) {
							h.put("TARAF", "");
						} else {
							h.put("TARAF", rs.getString("TARAF"));
						}
						
						if (rs.getString("STATUS_OB") == null) {
							h.put("STATUS_OB", "");
						} else {
							h.put("STATUS_OB", rs.getString("STATUS_OB"));
						}
						
						if (rs.getString("ID_TARAFKPTG") == null) {
							h.put("ID_TARAFKPTG", "");
						} else {
							h.put("ID_TARAFKPTG", rs.getString("ID_TARAFKPTG"));
						}
						
						if (rs.getString("STATUS_HIDUP") == null) {
							h.put("STATUS_HIDUP", "");
						} else {
							h.put("STATUS_HIDUP", rs.getString("STATUS_HIDUP"));
						}
						
						if (rs.getString("NAMA_STATUS_OB") == null) {
							h.put("NAMA_STATUS_OB", "");
						} else {
							h.put("NAMA_STATUS_OB", rs.getString("NAMA_STATUS_OB"));
						}
						
						if (rs.getString("ID_OB") == null) {
							h.put("ID_OB", "");
						} else {
							h.put("ID_OB", rs.getString("ID_OB"));
						}
						if (rs.getString("ID_PERMOHONANSIMATI") == null) {
							h.put("ID_PERMOHONANSIMATI", "");
						} else {
							h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI"));
						}
						
						if (rs.getString("ID_PEMOHON") == null) {
							h.put("ID_PEMOHON", "");
						} else {
							h.put("ID_PEMOHON", rs.getString("ID_PEMOHON"));
						}
						
						if (rs.getString("NAMA_OB") == null) {
							h.put("NAMA_OB", "");
						} else {
							h.put("NAMA_OB", rs.getString("NAMA_OB"));
						}
						
						if (rs.getString("NO_KP_BARU") == null) {
							h.put("NO_KP_BARU", "");
						} else {
							h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
						}
						
						if (rs.getString("NO_KP_LAMA") == null) {
							h.put("NO_KP_LAMA", "");
						} else {
							h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
						}
						
						if (rs.getString("NO_KP_LAIN") == null) {
							h.put("NO_KP_LAIN", "");
						} else {
							h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN"));
						}
						
						list_tukar_pemohon.addElement(h);
					}
					return list_tukar_pemohon;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			Vector list_IDpermohonanSimati = null;
			public Vector IDpermohonanSimati(String id_permohonansimati) throws Exception {
				myLogger.info("IDpermohonanSimati");
				list_IDpermohonanSimati = new Vector();
				list_IDpermohonanSimati.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
				/*	sql = " select STATUS_HIDUP,ID_OB,NAMA_OB,NO_KP_BARU,NO_KP_LAMA,ID_PEMOHON,NO_KP_LAIN from tblppkobpermohonan " +
							" where STATUS_HIDUP != '1' and umur >= 18 and id_permohonansimati in ( "+
						  " select id_permohonansimati from tblppkpermohonansimati where "+
						  " id_permohonan in ( "+
						  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
						  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";	*/
					
					
					sql = " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI = "+id_permohonansimati;
					
					myLogger.info("SQL IDpermohonanSimati :"+sql);
					
				   
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
												
						if (rs.getString("ID_SIMATI") == null) {
							h.put("ID_SIMATI", "");
						} else {
							h.put("ID_SIMATI", rs.getString("ID_SIMATI"));
						}
						
						
						
						list_IDpermohonanSimati.addElement(h);
					}
					return list_IDpermohonanSimati;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			Vector papar_list_ob = null;
			public Vector papar_list_ob(String id_fail) throws Exception {
				papar_list_ob = new Vector();
				papar_list_ob.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = " select ID_OB,NAMA_OB,NO_KP_BARU,NO_KP_LAMA,ID_PEMOHON,NO_KP_LAIN from tblppkobpermohonan where id_permohonansimati in ( "+
						  " select id_permohonansimati from tblppkpermohonansimati where "+
						  " id_permohonan in ( "+
						  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
						  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";			   
					
					myLogger.info("SQL LIST NAMA OB :"+sql);
					
				    int bil = 0;
					
					
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("ID_OB") == null) {
							h.put("ID_OB", "");
						} else {
							h.put("ID_OB", rs.getString("ID_OB"));
						}
						
						if (rs.getString("ID_PEMOHON") == null) {
							h.put("ID_PEMOHON", "");
						} else {
							h.put("ID_PEMOHON", rs.getString("ID_PEMOHON"));
						}
						
						if (rs.getString("NAMA_OB") == null) {
							h.put("NAMA_OB", "");
						} else {
							h.put("NAMA_OB", rs.getString("NAMA_OB"));
						}
						
						if (rs.getString("NO_KP_BARU") == null) {
							h.put("NO_KP_BARU", "");
						} else {
							h.put("NO_KP_BARU", rs.getString("NO_KP_BARU"));
						}
						
						if (rs.getString("NO_KP_LAMA") == null) {
							h.put("NO_KP_LAMA", "");
						} else {
							h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA"));
						}
						
						if (rs.getString("NO_KP_LAIN") == null) {
							h.put("NO_KP_LAIN", "");
						} else {
							h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN"));
						}
						
						papar_list_ob.addElement(h);
					}
					return papar_list_ob;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			public Hashtable IDpermohonanSimati2(String id_permohonan) throws Exception {
				myLogger.info("IDpermohonanSimati");
				list_IDpermohonanSimati = new Vector();
				list_IDpermohonanSimati.clear();
				Db db = null;
				String sql = "";
				Hashtable h = new Hashtable();
			try {
				 
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
									
					sql = " SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = "+id_permohonan;
					
					myLogger.info("SQL IDpermohonanSimati :"+sql);
					
				   
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						
												
						if (rs.getString("ID_SIMATI") == null) {
							h.put("ID_SIMATI", "");
						} else {
							h.put("ID_SIMATI", rs.getString("ID_SIMATI"));
						}
						
						
						
						//list_IDpermohonanSimati.addElement(h);
					}
					return h;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			Vector papar_list_bayaran = null;
			public Vector papar_list_bayaran(String id_fail) throws Exception {
				papar_list_bayaran = new Vector();
				papar_list_bayaran.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = "SELECT JB.ID_JENISBAYARAN,B.ID_BAYARAN,F.ID_FAIL,F.NO_FAIL,JB.KETERANGAN,B.NO_RESIT,TO_CHAR(B.JUMLAH_BAYARAN,'99990.99') AS JUMLAH_BAYARAN,B.TARIKH_BAYARAN,TO_CHAR(B.TARIKH_BAYARAN,'DD/MM/YYYY') AS TARIKH_BAYARAN_CONVERT " +
							" FROM TBLPPKBAYARAN B, TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJJENISBAYARAN JB "+
							" WHERE P.ID_PERMOHONAN = B.ID_PERMOHONAN AND F.ID_FAIL = P.ID_FAIL AND F.ID_FAIL = '"+id_fail+"' "+
							" AND B.ID_JENISBAYARAN = JB.ID_JENISBAYARAN(+) "+
							" ORDER BY B.TARIKH_MASUK ASC";			   
					
					myLogger.info("SQL LIST BAYARAN :"+sql);
					
				    int bil = 0;
					
					
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("ID_BAYARAN") == null) {
							h.put("ID_BAYARAN", "");
						} else {
							h.put("ID_BAYARAN", rs.getString("ID_BAYARAN"));
						}
						
						if (rs.getString("ID_FAIL") == null) {
							h.put("ID_FAIL", "");
						} else {
							h.put("ID_FAIL", rs.getString("ID_FAIL"));
						}
						
						if (rs.getString("NO_FAIL") == null) {
							h.put("NO_FAIL", "");
						} else {
							h.put("NO_FAIL", rs.getString("NO_FAIL"));
						}
						
						if (rs.getString("KETERANGAN") == null) {
							h.put("KETERANGAN", "");
						} else {
							h.put("KETERANGAN", rs.getString("KETERANGAN"));
						}
						
						if (rs.getString("ID_JENISBAYARAN") == null) {
							h.put("ID_JENISBAYARAN", "");
						} else {
							h.put("ID_JENISBAYARAN", rs.getString("ID_JENISBAYARAN"));
						}
						
						if (rs.getString("NO_RESIT") == null) {
							h.put("NO_RESIT", "");
						} else {
							h.put("NO_RESIT", rs.getString("NO_RESIT"));
						}
						
						if (rs.getString("JUMLAH_BAYARAN") == null) {
							h.put("JUMLAH_BAYARAN", "0.00");
						} else {
							h.put("JUMLAH_BAYARAN", rs.getString("JUMLAH_BAYARAN"));
						}
						
						if (rs.getString("TARIKH_BAYARAN_CONVERT") == null) {
							h.put("TARIKH_BAYARAN_CONVERT", "");
						} else {
							h.put("TARIKH_BAYARAN_CONVERT", rs.getString("TARIKH_BAYARAN_CONVERT"));
						}
						
						papar_list_bayaran.addElement(h);
					}
					return papar_list_bayaran;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			Vector papar_list_waris = null;
			public Vector papar_list_waris(String id_fail) throws Exception {
				papar_list_waris = new Vector();
				papar_list_waris.clear();
				Db db = null;
				String sql = "";
			try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = "SELECT 'A_WARIS' AS JENIS_ALAMAT,TO_CHAR('A_WARIS' || OBP.ID_OB) AS ID_OB," +
						 " F.ID_FAIL AS ID_FAIL,UPPER(OBP.NAMA_OB) AS NAMA,UPPER(OBP.ALAMAT1_SURAT) AS ALAMAT1," +
						 " UPPER(OBP.ALAMAT2_SURAT) AS ALAMAT2," +
						 " UPPER(OBP.ALAMAT3_SURAT) AS ALAMAT3," +
						 " OBP.POSKOD_SURAT AS POSKOD,UPPER(N.NAMA_NEGERI) AS NEGERI,UPPER(B.KETERANGAN) AS BANDAR" +
						 " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PSM,TBLPPKOBPERMOHONAN OBP," +
						 " TBLRUJNEGERI N,TBLRUJBANDAR B" +
						 " WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN" +
						 " AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
						 " AND OBP.ID_NEGERISURAT = N.ID_NEGERI(+)" +
						 " AND OBP.ID_BANDARSURAT = B.ID_BANDAR(+)" +
						 " AND OBP.ID_TARAFKPTG = '1' " +
						 " AND F.ID_FAIL = '"+id_fail+"' " +
						 //" --AND TO_CHAR('A_WARIS' || OBP.ID_OB) = '' " +
						 " UNION " +
						 " SELECT 'B_OB' AS JENIS_ALAMAT,TO_CHAR('B_OB' || OBP.ID_OB) AS ID_OB,F.ID_FAIL AS ID_FAIL," +
						 " UPPER(OBP.NAMA_OB) AS NAMA,UPPER(OBP.ALAMAT1_SURAT) AS ALAMAT1,UPPER(OBP.ALAMAT2_SURAT) AS ALAMAT2," +
						 " UPPER(OBP.ALAMAT3_SURAT) AS ALAMAT3, " +
						 " (OBP.POSKOD_SURAT) AS POSKOD,UPPER(N.NAMA_NEGERI) AS NEGERI,UPPER(B.KETERANGAN) AS BANDAR " +
						 " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PSM,TBLPPKOBPERMOHONAN OBP,TBLRUJNEGERI N," +
						 " TBLRUJBANDAR B " +
						 " WHERE  F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN " +
						 " AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI  " +
						 " AND OBP.ID_NEGERISURAT = N.ID_NEGERI(+) " +
						 " AND OBP.ID_BANDARSURAT = B.ID_BANDAR(+) " +
						 " AND OBP.ID_TARAFKPTG <> '1' " +
						 " AND F.ID_FAIL = '"+id_fail+"' " +
						 //" --AND TO_CHAR('B_OB'|| OBP.ID_OB) = '' " +
						 " UNION " +
						 " SELECT 'C_HUTANG' AS JENIS_ALAMAT,TO_CHAR('C_HUTANG' || OBP.ID_PENGHUTANG) AS ID_OB," +
						 " F.ID_FAIL AS ID_FAIL,UPPER(OBP.NAMA_PENGHUTANG) AS NAMA,UPPER(OBP.ALAMAT_1) AS ALAMAT1," +
						 " UPPER(OBP.ALAMAT_2) AS ALAMAT2, " +
						 " UPPER(OBP.ALAMAT_3) AS ALAMAT3, " +
						 " OBP.POSKOD AS POSKOD,UPPER(N.NAMA_NEGERI) AS NEGERI,UPPER(B.KETERANGAN) AS BANDAR " +
						 " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PSM,TBLPPKPENGHUTANG OBP,TBLRUJNEGERI N," +
						 " TBLRUJBANDAR B " +
						 " WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN " +
						 " AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI  " +
						 " AND OBP.ID_NEGERI = N.ID_NEGERI(+) " +
						 " AND OBP.ID_BANDAR = B.ID_BANDAR(+) " +
						 " AND F.ID_FAIL = '"+id_fail+"' " +
						 //" --AND TO_CHAR('C_HUTANG'|| OBP.ID_OB) = '' " +
						 " ORDER BY JENIS_ALAMAT,NAMA";			   
					
					myLogger.info("papar_list_waris :"+sql);
					
				    int bil = 0;
					
					
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();
						bil = bil + 1;
						h.put("BIL", bil);
						
						if (rs.getString("JENIS_ALAMAT") == null) {
							h.put("JENIS_ALAMAT", "");
						} else {
							h.put("JENIS_ALAMAT", rs.getString("JENIS_ALAMAT"));
						}
						
						if (rs.getString("ID_OB") == null) {
							h.put("ID_OB", "");
						} else {
							h.put("ID_OB", rs.getString("ID_OB"));
						}
						
						if (rs.getString("ID_FAIL") == null) {
							h.put("ID_FAIL", "");
						} else {
							h.put("ID_FAIL", rs.getString("ID_FAIL"));
						}
						
						if (rs.getString("NAMA") == null) {
							h.put("NAMA", "");
						} else {
							h.put("NAMA", rs.getString("NAMA"));
						}
						
						if (rs.getString("ALAMAT1") == null) {
							h.put("ALAMAT1", "");
						} else {
							h.put("ALAMAT1", rs.getString("ALAMAT1"));
						}
						
						if (rs.getString("ALAMAT2") == null) {
							h.put("ALAMAT2", "");
						} else {
							h.put("ALAMAT2", rs.getString("ALAMAT2"));
						}
						
						if (rs.getString("ALAMAT3") == null) {
							h.put("ALAMAT3", "");
						} else {
							h.put("ALAMAT3", rs.getString("ALAMAT3"));
						}
						
						if (rs.getString("POSKOD") == null) {
							h.put("POSKOD", "");
						} else {
							h.put("POSKOD", rs.getString("POSKOD"));
						}
						
						if (rs.getString("NEGERI") == null) {
							h.put("NEGERI", "");
						} else {
							h.put("NEGERI", rs.getString("NEGERI"));
						}
						
						if (rs.getString("BANDAR") == null) {
							h.put("BANDAR", "");
						} else {
							h.put("BANDAR", rs.getString("BANDAR"));
						}
						
						
						
						
						papar_list_waris.addElement(h);
					}
					return papar_list_waris;
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}
			
			
			Vector list_jenisbayaran = null;
			public Vector list_jenisbayaran() throws Exception {
				list_jenisbayaran = new Vector();
				list_jenisbayaran.clear();
				Db db = null;
				String sql = "";
				Hashtable sek = null;
				String seksyen = "";
				String id_suburusan = "";
				try {
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = "" +
				    " SELECT ID_JENISBAYARAN,KETERANGAN AS JENIS_BAYARAN FROM TBLRUJJENISBAYARAN WHERE ID_SEKSYEN = '2' ";
					
					myLogger.info("SQL LIST SUBURUSANSTATUS :"+sql);
					
					ResultSet rs = stmt.executeQuery(sql);
					while (rs.next()) {
						Hashtable h = new Hashtable();				
						if (rs.getString("ID_JENISBAYARAN") == null) {
							h.put("ID_JENISBAYARAN", "");
						} else {
							h.put("ID_JENISBAYARAN", rs.getString("ID_JENISBAYARAN"));
						}
						if (rs.getString("JENIS_BAYARAN") == null) {
							h.put("JENIS_BAYARAN", "");
						} else {
							h.put("JENIS_BAYARAN", rs.getString("JENIS_BAYARAN"));
						}
						
						list_jenisbayaran.addElement(h);
					}
					return list_jenisbayaran;
				} catch (Exception er) {
					myLogger.error(er);
					throw er;
				} finally {
					if (db != null)
						db.close();
				}
			}

			public Vector getBeanMaklumatNota() {
				return beanMaklumatNota;
			}

			public void setBeanMaklumatNota(Vector beanMaklumatNota) {
				this.beanMaklumatNota = beanMaklumatNota;
			}

			public Vector getListNota() {
				return listNota;
			}

			public void setListNota(Vector listNota) {
				this.listNota = listNota;
			}

		
}//close class
