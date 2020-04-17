package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmLaporanKemasukanBayaranData {

	static Logger myLog = Logger.getLogger(ekptg.model.ppk.FrmLaporanKemasukanBayaranData.class);
	public FrmLaporanKemasukanBayaranData() {	}
	
	public static boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPKBAYARAN WHERE ID_BAYARAN="+id;
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
	public static boolean insert(Hashtable<?, ?> parameters,String tarikh, String uid) {
		String sql = "";
	    String name = "";
		String value ="";
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idBilangan = DB.getNextID("TBLPPKBAYARAN_SEQ");
			int x = 1;

			r.add("ID_BAYARAN", idBilangan);
			  for (Enumeration<?> e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }

			//r.add("ID_LAPORANBILFAIL", idBilangan);
			//r.add("ID_DAERAH", r.unquote((String)parameters.get("id_Daerah")));
			//r.add("BILANGAN", r.unquote((String)parameters.get("bilangan")));
		    r.add("tarikh_bayaran",  r.unquote("to_date('" + tarikh + "','dd/MM/yyyy')"));
			r.add("id_masuk", uid);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_kemaskini", uid);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPPKBAYARAN");
			myLog.info("insert("+parameters+","+uid+"):TBLPPKBAYARAN:: "+sql);

			  stmt.executeUpdate(sql);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  
		  return true;
	}
	
	  public static boolean update(Hashtable<?, ?> parameters,String id) {
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "UPDATE TBLPPKBAYARAN SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_BAYARAN = " +id+ "";
			  myLog.info(sql);
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
	
			  } catch (Exception e) { 
				  e.printStackTrace();
				  return false;
			  }
			  finally {
				  if (db != null) db.close();
			  }
		  }
		  return true;
	  }
	
	
	public static Vector<Hashtable<String, String>> getSenarai(String idPejabat)	throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " SELECT PKB.ID_BAYARAN,PKB.NO_RESIT,TO_CHAR(PKB.TARIKH_BAYARAN,'dd/mm/yyyy') TARIKH_BAYARAN," +
					" PKB.JUMLAH_BAYARAN,rjb.KETERANGAN,F.NO_FAIL,rjb.ID_JENISBAYARAN,PKP.ID_PERMOHONAN "+
					" FROM tblppkbayaran pkb, tblrujjenisbayaran rjb,tblppkpermohonan pkp,TBLPFDFAIL F "+
					" WHERE rjb.ID_JENISBAYARAN = pkb.ID_JENISBAYARAN "+
					" AND PKP.ID_PERMOHONAN=PKB.ID_PERMOHONAN "+
					" AND PKP.ID_FAIL = F.ID_FAIL " +
					" AND PKP.ID_DAERAHMHN IN (  "+
					" 	SELECT RPU.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN RPU  "+
					" 	WHERE RPU.ID_PEJABATJKPTG = "+idPejabat+" " +
					"   AND RPU.ID_SEKSYEN = 2 "+
					//"	AND RPU.ID_JENISPEJABAT=22 "+
					" ) ";
					//	sql += " order by RPU.ID_DAERAHURUS";
	        //myLog.info("getSenarai("+idPejabat+")sql:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("idbayaran",Utils.isNull(rs.getString("ID_BAYARAN")));
				record.put("noresit",Utils.isNull(rs.getString("NO_RESIT")));
				record.put("jumlahbayaran",Util.formatDecimal(rs.getDouble("JUMLAH_BAYARAN")));
				record.put("idjenisbayaran",Utils.isNull(rs.getString("ID_JENISBAYARAN")));
				record.put("keterangan",Utils.isNull(rs.getString("KETERANGAN")));
				record.put("nofail",Utils.isNull(rs.getString("NO_FAIL")));
				record.put("tarikhbayar",Utils.isNull(rs.getString("TARIKH_BAYARAN")));
				record.put("idpermohonan",Utils.isNull(rs.getString("ID_PERMOHONAN")));
				lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;		
	}
	
	public static Vector<Hashtable<String, String>> getSenarai(String idPejabat,String noFail)	throws Exception{
		Db db = null;
		Vector<Hashtable<String, String>> lists = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " SELECT PKB.ID_BAYARAN,PKB.NO_RESIT,TO_CHAR(PKB.TARIKH_BAYARAN,'dd/mm/yyyy') TARIKH_BAYARAN," +
					" PKB.JUMLAH_BAYARAN,rjb.KETERANGAN,F.NO_FAIL,rjb.ID_JENISBAYARAN,PKP.ID_PERMOHONAN "+
					" FROM tblppkbayaran pkb, tblrujjenisbayaran rjb,tblppkpermohonan pkp,TBLPFDFAIL F "+
					" WHERE rjb.ID_JENISBAYARAN = pkb.ID_JENISBAYARAN "+
					" AND PKP.ID_PERMOHONAN=PKB.ID_PERMOHONAN "+
					" AND PKP.ID_FAIL = F.ID_FAIL " +
					" AND PKP.ID_DAERAHMHN IN (  "+
					" 	SELECT RPU.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN RPU  "+
					" 	WHERE RPU.ID_PEJABATJKPTG = "+idPejabat+" " +
					"   AND RPU.ID_SEKSYEN = 2 "+
					"	"+
					" ) " +
					" AND F.NO_FAIL ='"+noFail+"'";
					sql += " ORDER BY PKB.TARIKH_BAYARAN DESC";
	        //myLog.info("getSenarai("+idPejabat+")sql:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> record = null;
			while(rs.next()) {
				record = new Hashtable<String, String>();
				record.put("idbayaran",Utils.isNull(rs.getString("ID_BAYARAN")));
				record.put("noresit",Utils.isNull(rs.getString("NO_RESIT")));
				record.put("jumlahbayaran",Util.formatDecimal(rs.getDouble("JUMLAH_BAYARAN")));
				record.put("idjenisbayaran",Utils.isNull(rs.getString("ID_JENISBAYARAN")));
				record.put("keterangan",Utils.isNull(rs.getString("KETERANGAN")));
				record.put("nofail",Utils.isNull(rs.getString("NO_FAIL")));
				record.put("tarikhbayar",Utils.isNull(rs.getString("TARIKH_BAYARAN")));
				record.put("idpermohonan",Utils.isNull(rs.getString("ID_PERMOHONAN")));
				lists.addElement(record);
			}
		} catch (Exception e) {
				
		}finally  {
			if (db != null) db.close();
		}
		return lists;		
	}
	
	public static Vector<Hashtable<String, String>> carianFail(String noFail, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Integer count = 0;
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();

		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			String chkDataFail = noFail.trim();

				/*sql = "	SELECT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.ID_PERMOHONAN, B.TARIKH_MOHON, " +
						" B.TARIKH_MASUK, C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, " +
						" D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, H.ID_PERMOHONANSIMATI, " +
						" A.FLAG_JENIS_FAIL, C.NO_SIJIL_MATI "+
						" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, " +
						" TBLPPKPERMOHONANSIMATI H " + 
						" WHERE B.ID_DAERAHMHN IN " +
						"	(SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
						"	WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + ")" +
						" AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN " +
						" AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON " +
						" AND B.ID_STATUS not in (999,56)";*/
			sql = "	SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, " +
				" F.TARIKH_DAFTAR_FAIL, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, " +
				" A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU " +
				" , MS.ID_PERMOHONANSIMATI "+
				" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLPPKSIMATI P, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS," +
				" TBLRUJDAERAH D " +
				"    WHERE D.id_daerah in " +
				"         ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u,users_internal ur " +
				"             where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userId+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				sql += "         ) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON " +
				"  AND A.ID_FAIL = F.ID_FAIL " +
				" AND A.ID_DAERAHMHN = D.ID_DAERAH " +
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN " +
				" AND P.ID_SIMATI = MS.ID_SIMATI AND A.ID_STATUS <> '999' " +
				" AND (A.FLAG_JENIS_PERMOHONAN = 1) " +
				" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2) " ;
				/*AND UPPER(F.NO_FAIL) LIKE '%2000%' AND f.no_fail is not null ORDER BY F.ID_FAIL DESC ";
			*/
				//dapat flag
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					//sql = sql + " AND ( UPPER(A.NO_FAIL) LIKE '%' ||'"
					sql = sql + " AND ( UPPER(F.NO_FAIL) LIKE '%"
							+ chkDataFail.toUpperCase() + "%' ) " ;
				}
			}
			
			//sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";		
			sql = sql + " ORDER BY A.ID_PERMOHONAN DESC";		
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				senaraiFail.addElement(h);
				count++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;
	}

	public static Vector<Hashtable<String, String>> carianFailMengikutNoFail(String noFail, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Integer count = 0;
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();

		try {	
			db = new Db();
			Statement stmt = db.getStatement();
			String chkDataFail = noFail.trim();

				/*sql = "	SELECT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.ID_PERMOHONAN, B.TARIKH_MOHON, " +
						" B.TARIKH_MASUK, C.ID_SIMATI,C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, " +
						" D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, H.ID_PERMOHONANSIMATI, " +
						" A.FLAG_JENIS_FAIL, C.NO_SIJIL_MATI "+
						" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, " +
						" TBLPPKPERMOHONANSIMATI H " + 
						" WHERE B.ID_DAERAHMHN IN " +
						"	(SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
						"	WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + ")" +
						" AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN " +
						" AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON " +
						" AND B.ID_STATUS not in (999,56)";*/
			sql = "	SELECT F.ID_FAIL, F.NO_FAIL, A.ID_PERMOHONAN, A.TARIKH_MOHON, A.TARIKH_MASUK, " +
				" F.TARIKH_DAFTAR_FAIL, P.ID_SIMATI, P.NAMA_SIMATI, A.ID_DAERAHMHN, A.TARIKH_MOHON_ONLINE, " +
				" A.NO_PERMOHONAN_ONLINE, PM.NAMA_PEMOHON, PM.NO_KP_BARU, PM.ID_PEMOHON, P.NO_KP_BARU, P.NO_KP_BARU " +
				" , MS.ID_PERMOHONANSIMATI "+
				" FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLPPKSIMATI P, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS," +
				" TBLRUJDAERAH D " +
				"    WHERE D.id_daerah in " +
				"         ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u,users_internal ur " +
				"             where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userId+"' ";
				
				 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				
				
				sql += "         ) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON " +
				"  AND A.ID_FAIL = F.ID_FAIL " +
				" AND A.ID_DAERAHMHN = D.ID_DAERAH " +
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN " +
				" AND P.ID_SIMATI = MS.ID_SIMATI AND A.ID_STATUS <> '999' " +
				" AND (A.FLAG_JENIS_PERMOHONAN = 1) " +
				" AND (F.FLAG_JENIS_FAIL = 1 OR F.FLAG_JENIS_FAIL = 2) " ;
				/*AND UPPER(F.NO_FAIL) LIKE '%2000%' AND f.no_fail is not null ORDER BY F.ID_FAIL DESC ";
			*/
				//dapat flag
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					//sql = sql + " AND ( UPPER(A.NO_FAIL) LIKE '%' ||'"
					sql = sql + " AND ( UPPER(F.NO_FAIL) = '"
							+ chkDataFail.toUpperCase() + "' ) " ;
				}
			}
			
			//sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";		
			sql = sql + " ORDER BY A.ID_PERMOHONAN DESC";		
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("idpermohonansimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				senaraiFail.addElement(h);
				count++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return senaraiFail;
	}
	
}
