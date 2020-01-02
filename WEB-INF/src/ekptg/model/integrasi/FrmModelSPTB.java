package ekptg.model.integrasi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmModelSPTB {
	
	static Logger myLogger = Logger.getLogger(FrmModelSPTB.class);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public Vector searchSPTB(String NO_FAIL, String NO_PERMOHONAN, String NAMA_PEMOHON, String NOKP_PEMOHON) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FA.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(PM.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NAMA_PEMOHON)) {
				SQL_SEARCH = " AND UPPER(PB.NAMA_PB) LIKE '%" + NAMA_PEMOHON.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NOKP_PEMOHON)) {
				SQL_SEARCH = " AND UPPER(PB.NO_PB) LIKE '%" + NOKP_PEMOHON.toUpperCase() + "%'";
			}
			sql = "SELECT PB.ID_PIHAKBERKEPENTINGAN, FA.NO_FAIL, PM.NO_PERMOHONAN, PB.NAMA_PB, PB.NO_PB, PB.ALAMAT1, PB.ALAMAT2, PB.ALAMAT3 " +
				"FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
				"WHERE PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN" +
				SQL_SEARCH + 
				" ORDER BY FA.NO_FAIL, PB.NAMA_PB";
			int i = 1;
			String ID_PEMOHON = "", ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "", ALAMAT = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ID_PEMOHON = rs.getString(1) == null ? "" : rs.getString(1);
				NO_FAIL = rs.getString(2) == null ? "" : rs.getString(2);
				NO_PERMOHONAN = rs.getString(3) == null ? "" : rs.getString(3);
				NAMA_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
				NOKP_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
				ALAMAT1 = rs.getString(6) == null ? "" : rs.getString(6);
				ALAMAT2 = rs.getString(7) == null ? "" : rs.getString(7);
				ALAMAT3 = rs.getString(8) == null ? "" : rs.getString(8);
				
				if (!"".equalsIgnoreCase(ALAMAT1)) {
					ALAMAT = ALAMAT1;
				}
				if (!"".equalsIgnoreCase(ALAMAT2)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT2;
					} else {
						ALAMAT = ALAMAT2;
					}
				}
				if (!"".equalsIgnoreCase(ALAMAT3)) {
					if (!"".equalsIgnoreCase(ALAMAT)) {
						ALAMAT += ", " + ALAMAT3;
					} else {
						ALAMAT = ALAMAT3;
					}
				}
				
				h = new Hashtable();
				h.put("No", i);
				h.put("IDPemohon", ID_PEMOHON);
				h.put("NoFail", NO_FAIL);
				h.put("NoPermohonan", NO_PERMOHONAN);
				h.put("NamaPemohon", NAMA_PEMOHON);
				h.put("NoKPPemohon", NOKP_PEMOHON);
				h.put("Alamat", ALAMAT);
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector searchSPTBHAKMILIK(String NO_FAIL, String NO_PERMOHONAN, String NO_HAKMILIK, String NO_LOT,String seksyen,String USER_ID) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "", SQL_SEARCH = "";
			
			
			if(seksyen.equals("1"))
			{
			
			if (!"".equalsIgnoreCase(NO_FAIL)) {
				SQL_SEARCH = " AND UPPER(FA.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
				SQL_SEARCH = " AND UPPER(PM.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_HAKMILIK)) {
				SQL_SEARCH = " AND UPPER(HM.NO_HAKMILIK) LIKE '%" + NO_HAKMILIK.toUpperCase() + "%'";
			}
			if (!"".equalsIgnoreCase(NO_LOT)) {
				SQL_SEARCH = " AND UPPER(HM.NO_LOT) LIKE '%" + NO_LOT.toUpperCase() + "%'";
			}	
			
			// check ID NEGERI user
			Statement stmt1 = db.getStatement();
			ResultSet rs1 = null;
			String ID_NEGERI = "";
			if (!"".equalsIgnoreCase(USER_ID)) {
				sql = "SELECT INT.ID_NEGERI FROM USERS U, USERS_INTERNAL INT WHERE U.USER_ID = INT.USER_ID AND UPPER(U.USER_ID) = '" + USER_ID + "'";
				myLogger.info("SQL INT :"+sql);
				
				rs1 = stmt1.executeQuery(sql);
				if (rs1.next()) {
					ID_NEGERI = rs1.getString(1) == null ? "" : rs1.getString(1);
				}
			}
			if (!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)) {
				SQL_SEARCH += " AND FA.ID_NEGERI = " + ID_NEGERI + " ";
			}

			
			
			sql = " SELECT FA.ID_SUBURUSAN,SU.ID_SEKSYEN,FA.NO_FAIL,FA.ID_FAIL, PM.ID_PERMOHONAN,PM.NO_PERMOHONAN,HM.ID_HAKMILIK,HM.NO_HAKMILIK,HM.NO_LOT " +
				  " ,JHM.KOD_JENIS_HAKMILIK,N.NAMA_NEGERI,D.NAMA_DAERAH,M.NAMA_MUKIM," +
				  " KT.KETERANGAN AS KATEGORI_TANAH,HM.NAMA_LUAS_ASAL " +
				  " ,TO_CHAR(HM.TARIKH_DAFTAR,'DD') AS HARI, "+
				  " CASE WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='01' THEN 'Januari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='02' THEN 'Februari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='03' THEN 'Mac' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='04' THEN 'April' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='05' THEN 'Mei' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='06' THEN 'Jun' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='07' THEN 'Julai' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='08' THEN 'Ogos' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='09' THEN 'September' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='10' THEN 'Oktober' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='11' THEN 'November' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='12' THEN 'Disember' "+
					    " END AS BULAN, TO_CHAR(HM.TARIKH_DAFTAR,'yyyy') AS TAHUN "+
					    " ,CASE WHEN HM.FLAG_JENIS_RIZAB ='1' THEN 'Kawasan Rizab Melayu' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='2' THEN 'Kawasan Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='3' THEN 'Rizab Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='4' THEN 'Kawasan Penempatan Berkelompok' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='5' THEN  INITCAP(HM.NAMA_LAIN_RIZAB) "+
					        " ELSE '' "+   
					    " END AS JENIS_RIZAB,HM.NO_SYIT "+
					" FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM "+
					" ,TBLRUJJENISHAKMILIK JHM ,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJMUKIM M,TBLRUJSUBURUSAN SU," +
					" TBLRUJKATEGORI KT "+
					" WHERE PM.ID_FAIL = FA.ID_FAIL "+
					" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN"+
					" AND HM.ID_KATEGORITANAH = KT.ID_KATEGORI(+) "+
					" AND HM.ID_NEGERI = N.ID_NEGERI(+) "+
					" AND HM.ID_DAERAH = D.ID_DAERAH(+) "+
					" AND HM.ID_MUKIM = M.ID_MUKIM(+) "+
					" AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) "+
					" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
					" AND HM.ID_HAKMILIK = HM.ID_HAKMILIK  "+ 
					SQL_SEARCH +
					" ORDER BY FA.NO_FAIL, HM.NO_HAKMILIK ";

			
			}
			
			
			if(seksyen.equals("2"))
			{
				if (!"".equalsIgnoreCase(NO_FAIL)) {
					SQL_SEARCH = " AND UPPER(FA.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase() + "%'";
				}
				if (!"".equalsIgnoreCase(NO_PERMOHONAN)) {
					SQL_SEARCH = " AND UPPER(PM.NO_PERMOHONAN) LIKE '%" + NO_PERMOHONAN.toUpperCase() + "%'";
				}
				if (!"".equalsIgnoreCase(NO_HAKMILIK)) {
					SQL_SEARCH = " AND UPPER(HM.NO_HAKMILIK) LIKE '%" + NO_HAKMILIK.toUpperCase() + "%'";
				}
				if (!"".equalsIgnoreCase(NO_LOT)) {
					SQL_SEARCH = " AND UPPER(HM.NO_LOT) LIKE '%" + NO_LOT.toUpperCase() + "%'";
				}	
				
				Statement stmt1 = db.getStatement();
				ResultSet rs1 = null;
				String ID_NEGERI = "";
				if (!"".equalsIgnoreCase(USER_ID)) {
					sql = "SELECT INT.ID_NEGERI FROM USERS U, USERS_INTERNAL INT WHERE U.USER_ID = INT.USER_ID AND UPPER(U.USER_ID) = '" + USER_ID + "'";
					myLogger.info("SQL INT :"+sql);
					
					rs1 = stmt1.executeQuery(sql);
					if (rs1.next()) {
						ID_NEGERI = rs1.getString(1) == null ? "" : rs1.getString(1);
					}
				}
				if (!"".equalsIgnoreCase(ID_NEGERI) && !"16".equalsIgnoreCase(ID_NEGERI)) {
					SQL_SEARCH += " AND fa.ID_NEGERI = " + ID_NEGERI + " ";
				}

				
			  sql = " SELECT   fa.id_suburusan, su.id_seksyen, fa.no_fail, fa.id_fail, "+
					" pm.id_permohonan, pm.no_permohonan, to_char(hm.id_hta) as id_hakmilik, hm.no_hakmilik, "+
					" to_char(hm.no_pt) as no_lot, jhm.kod_jenis_hakmilik, n.nama_negeri, d.nama_daerah, "+
					" m.nama_mukim, kt.keterangan AS kategori_tanah       "+
					" FROM tblppkpermohonan pm, "+
					" tblpfdfail fa, "+
				    " tblppkhta hm, "+
				    " tblrujjenishakmilik jhm, "+
				    " tblrujdaerah d, "+
				    " tblrujnegeri n, "+
				    " tblrujmukim m, "+
				    " tblrujsuburusan su, "+
				    " tblrujkategori kt, "+
				    " tblppksimati sm, "+
				    " tblppkpermohonansimati psm "+
				    " WHERE pm.id_fail = fa.id_fail "+
				    " AND pm.id_permohonan = psm.id_permohonan "+
				    " AND psm.id_simati = sm.id_simati "+
				    " AND sm.id_simati = hm.id_simati "+
				    " AND fa.id_suburusan = su.id_suburusan "+
				    " AND hm.id_kategori = kt.id_kategori(+) "+
				    " AND hm.id_negeri = n.id_negeri(+) "+
				    " AND hm.id_daerah = d.id_daerah(+) "+
				    " AND hm.id_mukim = m.id_mukim(+) "+
				    " AND hm.id_jenishm = jhm.id_jenishakmilik(+) "+
				     SQL_SEARCH+
				    " ORDER BY fa.no_fail, hm.no_hakmilik ";
			}	
				
			
			
			
			
			myLogger.info("SQL CARIAN :"+sql.toUpperCase());
			
			int i = 1;
			
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			
			while (rs.next()) {
				
				
				
				h = new Hashtable();
				h.put("No", i);
				
				
				
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NO_PERMOHONAN", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("KATEGORI_TANAH", rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH"));							
				h.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				
				if(seksyen.equals("1"))
				{
				h.put("HARI", rs.getString("HARI") == null ? "" : rs.getString("HARI"));
				h.put("BULAN", rs.getString("BULAN") == null ? "" : rs.getString("BULAN"));
				h.put("TAHUN", rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN"));
				h.put("JENIS_RIZAB", rs.getString("JENIS_RIZAB") == null ? "" : rs.getString("JENIS_RIZAB"));
				h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT"));
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? "" : rs.getString("NAMA_LUAS_ASAL"));	
				}
								
				v.add(h);
				i++;
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
		
	public String getIDPemohon(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getIDHakmilik(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public String getIDFail(String ID_PERMOHONAN) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PERMOHONAN)) {
				String sql = "";
				sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = " + ID_PERMOHONAN;
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}

	@SuppressWarnings("unchecked")
	public Vector viewSPTBMaklumatHakmilik(String ID_FAIL,String ID_HAKMILIK,String ID_SEKSYEN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
			
			   // String ID_FAIL = "";
				String ID_PERMOHONAN = "";
				String NO_FAIL = "";
				String NO_PERMOHONAN = "";
			//	String ID_HAKMILIK = "";
				String NO_HAKMILIK = "";
				String NO_LOT = "";
				String KOD_JENIS_HAKMILIK = "";
				String NAMA_NEGERI = "";
				String NAMA_DAERAH = "";
				String NAMA_MUKIM = "";
				String KATEGORI_TANAH = "";
				String NAMA_LUAS_ASAL = "";
				String HARI = "";
				String BULAN = "";
				String TAHUN = "";
				String JENIS_RIZAB = "";
				String NO_SYIT = "";
				//String ID_SEKSYEN = "";
				String STATUS_SPTB = "";
				String CATATAN = "";
				String SELESAI = "";
				String NAMA_PEMILIK = "";
				
				
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				
				if(ID_SEKSYEN.equals("1"))
				{
				
				sql = " SELECT SP.STATUS_SPTB, SP.CATATAN, SP.SELESAI,FA.ID_SUBURUSAN,SU.ID_SEKSYEN," +
						"FA.NO_FAIL,FA.ID_FAIL, PM.ID_PERMOHONAN,PM.NO_PERMOHONAN,HM.ID_HAKMILIK," +
						"HM.NO_HAKMILIK,HM.NO_LOT " +
				  " ,JHM.KOD_JENIS_HAKMILIK,N.NAMA_NEGERI,D.NAMA_DAERAH,M.NAMA_MUKIM," +
				  " KT.KETERANGAN AS KATEGORI_TANAH,HM.NAMA_LUAS_ASAL " +
				  " ,TO_CHAR(HM.TARIKH_DAFTAR,'DD') AS HARI, "+
				  " CASE WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='01' THEN 'Januari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='02' THEN 'Februari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='03' THEN 'Mac' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='04' THEN 'April' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='05' THEN 'Mei' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='06' THEN 'Jun' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='07' THEN 'Julai' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='08' THEN 'Ogos' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='09' THEN 'September' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='10' THEN 'Oktober' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='11' THEN 'November' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='12' THEN 'Disember' "+
					    " END AS BULAN, TO_CHAR(HM.TARIKH_DAFTAR,'yyyy') AS TAHUN "+
					    " ,CASE WHEN HM.FLAG_JENIS_RIZAB ='1' THEN 'Kawasan Rizab Melayu' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='2' THEN 'Kawasan Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='3' THEN 'Rizab Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='4' THEN 'Kawasan Penempatan Berkelompok' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='5' THEN  INITCAP(HM.NAMA_LAIN_RIZAB) "+
					        " ELSE '' "+   
					    " END AS JENIS_RIZAB,HM.NO_SYIT, "+
					    "(select "+
					    "substr(rtrim (xmlagg (xmlelement (e, nama_pemilik)).extract ('//text()'), ','),3) list_pemilik "+
					    "from "+   
					    "  (    "+    
					    " select to_char( "+
					    "  case  "+
					    		    " when "+  
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = "+ 
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+ 
					    		    " and "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) <> 1) "+
					    		    " then "+
					    		    " ' dan ' "+
					    		    " when  count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
					    		    " else "+
					    		    " ', ' "+
					    		    " end || initcap(pb.nama_pb) || ' (' || pb.no_pb || ')') as nama_pemilik, "+
					    		    "    count(*) OVER  "+
					    		    " ( partition by su.id_seksyen ) "+ 
					    		    " cnt, "+
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb) seq, "+
					    		    " case when   "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = "+ 
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+
					    		    " and "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) <> 1) "+  
					    		    " then "+
					    		    " ' dan ' "+
					    		    " when  count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
					    		    " else "+
					    		    "  ', ' "+
					    		    " end as  "+
					    		    " symbol     "+
					    		    "  from "+
					    		    "    tblpptpihakberkepentingan pb,tblppthakmilikpb hpb,tblppthakmilik hmm, "+
					    		    "    tblpptpermohonan p,tblpfdfail ff,tblrujsuburusan su "+
					    		    "    where pb.id_pihakberkepentingan = hpb.id_pihakberkepentingan "+
					    		    "    and hpb.id_jenispb = '1' "+
					    		    "    and hmm.id_hakmilik = hpb.id_hakmilik "+
					    		    "    and hmm.id_permohonan = p.id_permohonan "+
					    		    "    and p.id_fail = ff.id_fail "+
					    		    "   and ff.id_suburusan = su.id_suburusan "+    
					    		    " AND  hmm.id_hakmilik = "+ID_HAKMILIK+ 
									" AND p.id_fail = "+ID_FAIL+
									" AND su.id_seksyen = "+ID_SEKSYEN+	
					    	"	)) as NAMA_PEMILIK "+
					" FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM "+
					" ,TBLRUJJENISHAKMILIK JHM ,TBLINTSPTB SP,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJMUKIM M,TBLRUJSUBURUSAN SU," +
					" TBLRUJKATEGORI KT "+
					" WHERE PM.ID_FAIL = FA.ID_FAIL "+
					" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN"+
					" AND HM.ID_KATEGORITANAH = KT.ID_KATEGORI(+) "+
					" AND HM.ID_NEGERI = N.ID_NEGERI(+) "+
					" AND HM.ID_DAERAH = D.ID_DAERAH(+) "+
					" AND HM.ID_MUKIM = M.ID_MUKIM(+) "+
					" AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) "+
					" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
					" AND HM.ID_HAKMILIK = HM.ID_HAKMILIK  "+ 
					" AND HM.ID_HAKMILIK = "+ID_HAKMILIK+ 
					" AND FA.ID_FAIL = "+ID_FAIL+
					" AND SU.ID_SEKSYEN = "+ID_SEKSYEN+	
					" AND SP.ID_HAKMILIK = HM.ID_HAKMILIK"+ 
					" AND SP.ID_FAIL = FA.ID_FAIL "+
					" AND SP.ID_SEKSYEN = SU.ID_SEKSYEN"+	
					" ORDER BY FA.NO_FAIL, HM.NO_HAKMILIK ";
				
				myLogger.info("KOMA TEST :"+sql.toUpperCase());
				
				}
				
				
				if(ID_SEKSYEN.equals("2"))
				{
				
				   sql = " SELECT  SP.STATUS_SPTB, SP.CATATAN, SP.SELESAI,sm.no_kp_baru, sm.id_simati,hm.id_hta,fa.id_suburusan, su.id_seksyen, fa.no_fail, fa.id_fail,  "+
					     " pm.id_permohonan, pm.no_permohonan, (hm.id_hta) as id_hakmilik, hm.no_hakmilik, "+
					     " (hm.no_pt) as no_lot, jhm.kod_jenis_hakmilik, n.nama_negeri, d.nama_daerah, "+
					     " m.nama_mukim, kt.keterangan AS kategori_tanah,  "+
					     " case  "+
					     " when sm.no_kp_baru != ' ' "+
					     " then sm.nama_simati || ' (' || sm.no_kp_baru || ')' "+
					     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and  sm.no_kp_lama != ' ' "+
				 	     " then sm.nama_simati || ' (' || sm.no_kp_lama || ')' "+
					     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and (sm.no_kp_lama = ' ' or sm.no_kp_lama = null) and  sm.no_kp_lain != ' ' "+
					     " then sm.nama_simati || ' (' || jnpb.keterangan || ' '|| sm.no_kp_lain || ')' "+
					     " else "+ 
					     " sm.nama_simati "+
					     " end as nama_pemilik "+       
					     " FROM tblppkpermohonan pm, "+
					     " tblpfdfail fa, "+
					     " tblppkhta hm, "+
					     " tblrujjenishakmilik jhm, "+
					     " tblrujdaerah d, "+
					     " tblrujnegeri n, "+
					     " tblrujmukim m, "+
					     " tblrujsuburusan su, "+
					     " TBLINTSPTB SP, "+
					     " tblrujkategori kt, "+
					     " tblppksimati sm, "+
					     " tblppkpermohonansimati psm, "+
					     " tblrujjenisnopb jnpb "+
					     " WHERE pm.id_fail = fa.id_fail "+
					     " AND sm.jenis_kp = jnpb.id_jenisnopb(+) "+ 
					     " AND pm.id_permohonan = psm.id_permohonan "+
					     " AND psm.id_simati = sm.id_simati "+
					     " AND sm.id_simati = hm.id_simati "+
					     " AND fa.id_suburusan = su.id_suburusan "+
					     " AND hm.id_kategori = kt.id_kategori(+) "+
					     " AND hm.id_negeri = n.id_negeri(+) "+
					     " AND hm.id_daerah = d.id_daerah(+) "+
					     " AND hm.id_mukim = m.id_mukim(+) "+
				 	     " AND hm.id_jenishm = jhm.id_jenishakmilik(+) "+     
					     " AND hm.id_hta = "+ID_HAKMILIK+
					     " AND fa.id_fail =  "+ID_FAIL+
					     " AND su.id_seksyen = "+ID_SEKSYEN+
					     " AND SP.ID_HAKMILIK = hm.id_hta"+ 
					     " AND SP.ID_FAIL = fa.ID_FAIL "+
					     " AND SP.ID_SEKSYEN = su.ID_SEKSYEN"+	
					     " ORDER BY fa.no_fail, hm.no_hakmilik ";
					
				}
				
				myLogger.info("SQL VIEW SPTB"+sql.toUpperCase());
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
					ID_PERMOHONAN = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
					NO_FAIL = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
					NO_PERMOHONAN = rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN");
					ID_HAKMILIK = rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK");
					NO_HAKMILIK = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
					NO_LOT = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
					KOD_JENIS_HAKMILIK = rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
					NAMA_NEGERI = rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI");
					NAMA_DAERAH = rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH");
					NAMA_MUKIM = rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM");
					KATEGORI_TANAH = rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH");
					STATUS_SPTB = rs.getString("STATUS_SPTB") == null ? "" : rs.getString("STATUS_SPTB");
					CATATAN = rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN");
					SELESAI = rs.getString("SELESAI") == null ? "" : rs.getString("SELESAI");
					NAMA_PEMILIK = rs.getString("NAMA_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK");
					ID_SEKSYEN = rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN");
					
					if(ID_SEKSYEN.equals("1"))
					{
					NAMA_LUAS_ASAL = rs.getString("NAMA_LUAS_ASAL") == null ? "" : rs.getString("NAMA_LUAS_ASAL");
					HARI = rs.getString("HARI") == null ? "" : rs.getString("HARI");
					BULAN  = rs.getString("BULAN") == null ? "" : rs.getString("BULAN");
					TAHUN = rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN");
					JENIS_RIZAB = rs.getString("JENIS_RIZAB") == null ? "" : rs.getString("JENIS_RIZAB");
					NO_SYIT = rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT");
					}
					
					if(ID_SEKSYEN.equals("2"))
					{
					NAMA_LUAS_ASAL = "";
					HARI = "";
					BULAN  = "";
					TAHUN = "";
					JENIS_RIZAB = "";
					NO_SYIT = "";
					}
					
					
				
					
				}
				
				if (!haveINTData) {
					
					if(ID_SEKSYEN.equals("1"))
					{
					sql = " SELECT FA.ID_SUBURUSAN,SU.ID_SEKSYEN,FA.NO_FAIL,FA.ID_FAIL, PM.ID_PERMOHONAN,PM.NO_PERMOHONAN,HM.ID_HAKMILIK,HM.NO_HAKMILIK,HM.NO_LOT " +
					  " ,JHM.KOD_JENIS_HAKMILIK,N.NAMA_NEGERI,D.NAMA_DAERAH,M.NAMA_MUKIM," +
					  " KT.KETERANGAN AS KATEGORI_TANAH,HM.NAMA_LUAS_ASAL " +
					  " ,TO_CHAR(HM.TARIKH_DAFTAR,'DD') AS HARI, "+
					  " CASE WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='01' THEN 'Januari' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='02' THEN 'Februari' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='03' THEN 'Mac' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='04' THEN 'April' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='05' THEN 'Mei' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='06' THEN 'Jun' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='07' THEN 'Julai' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='08' THEN 'Ogos' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='09' THEN 'September' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='10' THEN 'Oktober' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='11' THEN 'November' "+
						        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='12' THEN 'Disember' "+
						    " END AS BULAN, TO_CHAR(HM.TARIKH_DAFTAR,'yyyy') AS TAHUN "+
						    " ,CASE WHEN HM.FLAG_JENIS_RIZAB ='1' THEN 'Kawasan Rizab Melayu' "+
						        " WHEN HM.FLAG_JENIS_RIZAB ='2' THEN 'Kawasan Orang Asli' "+
						        " WHEN HM.FLAG_JENIS_RIZAB ='3' THEN 'Rizab Orang Asli' "+
						        " WHEN HM.FLAG_JENIS_RIZAB ='4' THEN 'Kawasan Penempatan Berkelompok' "+
						        " WHEN HM.FLAG_JENIS_RIZAB ='5' THEN  INITCAP(HM.NAMA_LAIN_RIZAB) "+
						        " ELSE '' "+   
						    " END AS JENIS_RIZAB,HM.NO_SYIT , "+
					    "(select "+
					    "substr(rtrim (xmlagg (xmlelement (e, nama_pemilik)).extract ('//text()'), ','),3) list_pemilik "+
					    "from "+   
					    "  (    "+    
					    " select to_char( "+
					    "  case  "+
					    		    " when "+  
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = "+ 
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+ 
					    		    " and "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) <> 1) "+
					    		    " then "+
					    		    " ' dan ' "+
					    		    " when  count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
					    		    " else "+
					    		    " ', ' "+
					    		    " end || initcap(pb.nama_pb) || ' (' || pb.no_pb || ')' ) as nama_pemilik, "+
					    		    "    count(*) OVER  "+
					    		    " ( partition by su.id_seksyen ) "+ 
					    		    " cnt, "+
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb) seq, "+
					    		    " case when   "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = "+ 
					    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+
					    		    " and "+
					    		    " (count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) <> 1) "+  
					    		    " then "+
					    		    " ' dan ' "+
					    		    " when  count(*) OVER "+ 
					    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
					    		    " else "+
					    		    "  ', ' "+
					    		    " end as  "+
					    		    " symbol     "+
					    		    "  from "+
					    		    "    tblpptpihakberkepentingan pb,tblppthakmilikpb hpb,tblppthakmilik hmm, "+
					    		    "    tblpptpermohonan p,tblpfdfail ff,tblrujsuburusan su "+
					    		    "    where pb.id_pihakberkepentingan = hpb.id_pihakberkepentingan "+
					    		    "    and hpb.id_jenispb = '1' "+
					    		    "    and hmm.id_hakmilik = hpb.id_hakmilik "+
					    		    "    and hmm.id_permohonan = p.id_permohonan "+
					    		    "    and p.id_fail = ff.id_fail "+
					    		    "   and ff.id_suburusan = su.id_suburusan "+    
					    		    " AND  hmm.id_hakmilik = "+ID_HAKMILIK+ 
									" AND p.id_fail = "+ID_FAIL+
									" AND su.id_seksyen = "+ID_SEKSYEN+	
					    	"	)) as NAMA_PEMILIK "+
						" FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM "+
						" ,TBLRUJJENISHAKMILIK JHM ,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJMUKIM M,TBLRUJSUBURUSAN SU," +
						" TBLRUJKATEGORI KT "+
						" WHERE PM.ID_FAIL = FA.ID_FAIL "+
						" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN"+
						" AND HM.ID_KATEGORITANAH = KT.ID_KATEGORI(+) "+
						" AND HM.ID_NEGERI = N.ID_NEGERI(+) "+
						" AND HM.ID_DAERAH = D.ID_DAERAH(+) "+
						" AND HM.ID_MUKIM = M.ID_MUKIM(+) "+
						" AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) "+
						" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
						" AND HM.ID_HAKMILIK = HM.ID_HAKMILIK  "+ 
						" AND HM.ID_HAKMILIK = "+ID_HAKMILIK+ 
						" AND FA.ID_FAIL = "+ID_FAIL+
						" AND SU.ID_SEKSYEN = "+ID_SEKSYEN+							
						" ORDER BY FA.NO_FAIL, HM.NO_HAKMILIK ";
					
					}

					
					if(ID_SEKSYEN.equals("2"))
					{
					
					   sql = " SELECT  sm.no_kp_baru, sm.id_simati,hm.id_hta,fa.id_suburusan, su.id_seksyen, fa.no_fail, fa.id_fail,  "+
						     " pm.id_permohonan, pm.no_permohonan, (hm.id_hta) as id_hakmilik, hm.no_hakmilik, "+
						     " (hm.no_pt) as no_lot, jhm.kod_jenis_hakmilik, n.nama_negeri, d.nama_daerah, "+
						     " m.nama_mukim, kt.keterangan AS kategori_tanah,  "+
						     " case  "+
						     " when sm.no_kp_baru != ' ' "+
						     " then sm.nama_simati || ' (' || sm.no_kp_baru || ')' "+
						     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and  sm.no_kp_lama != ' ' "+
					 	     " then sm.nama_simati || ' (' || sm.no_kp_lama || ')' "+
						     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and (sm.no_kp_lama = ' ' or sm.no_kp_lama = null) and  sm.no_kp_lain != ' ' "+
						     " then sm.nama_simati || ' (' || jnpb.keterangan || ' '|| sm.no_kp_lain || ')' "+
						     " else "+ 
						     " sm.nama_simati "+
						     " end as nama_pemilik "+       
						     " FROM tblppkpermohonan pm, "+
						     " tblpfdfail fa, "+
						     " tblppkhta hm, "+
						     " tblrujjenishakmilik jhm, "+
						     " tblrujdaerah d, "+
						     " tblrujnegeri n, "+
						     " tblrujmukim m, "+
						     " tblrujsuburusan su, "+						    
						     " tblrujkategori kt, "+
						     " tblppksimati sm, "+
						     " tblppkpermohonansimati psm, "+
						     " tblrujjenisnopb jnpb "+
						     " WHERE pm.id_fail = fa.id_fail "+
						     " AND sm.jenis_kp = jnpb.id_jenisnopb(+) "+ 
						     " AND pm.id_permohonan = psm.id_permohonan "+
						     " AND psm.id_simati = sm.id_simati "+
						     " AND sm.id_simati = hm.id_simati "+
						     " AND fa.id_suburusan = su.id_suburusan "+
						     " AND hm.id_kategori = kt.id_kategori(+) "+
						     " AND hm.id_negeri = n.id_negeri(+) "+
						     " AND hm.id_daerah = d.id_daerah(+) "+
						     " AND hm.id_mukim = m.id_mukim(+) "+
					 	     " AND hm.id_jenishm = jhm.id_jenishakmilik(+) "+     
						     " AND hm.id_hta = "+ID_HAKMILIK+
						     " AND fa.id_fail =  "+ID_FAIL+
						     " AND su.id_seksyen = "+ID_SEKSYEN+
						     
						     " ORDER BY fa.no_fail, hm.no_hakmilik ";
						
					}
					
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
						ID_PERMOHONAN = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
						NO_FAIL = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
						NO_PERMOHONAN = rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN");
						ID_HAKMILIK = rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK");
						NO_HAKMILIK = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
						NO_LOT = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
						KOD_JENIS_HAKMILIK = rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
						NAMA_NEGERI = rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI");
						NAMA_DAERAH = rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH");
						NAMA_MUKIM = rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM");
						KATEGORI_TANAH = rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH");
						ID_SEKSYEN = rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN");						
						NAMA_PEMILIK = rs.getString("NAMA_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK");
						
						
						if(ID_SEKSYEN.equals("1"))
						{
						NAMA_LUAS_ASAL = rs.getString("NAMA_LUAS_ASAL") == null ? "" : rs.getString("NAMA_LUAS_ASAL");
						HARI = rs.getString("HARI") == null ? "" : rs.getString("HARI");
						BULAN  = rs.getString("BULAN") == null ? "" : rs.getString("BULAN");
						TAHUN = rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN");
						JENIS_RIZAB = rs.getString("JENIS_RIZAB") == null ? "" : rs.getString("JENIS_RIZAB");
						NO_SYIT = rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT");
						}
						
						if(ID_SEKSYEN.equals("2"))
						{
						NAMA_LUAS_ASAL = "";
						HARI = "";
						BULAN  = "";
						TAHUN = "";
						JENIS_RIZAB = "";
						NO_SYIT = "";
						}
						
					
						
					}
				}
				h = new Hashtable();
				h.put("haveINTData", haveINTData);				
				h.put("MP_ID_PERMOHONAN", ID_PERMOHONAN);
				h.put("MP_NO_FAIL", NO_FAIL);
				h.put("MP_NO_PERMOHONAN", NO_PERMOHONAN);
				h.put("MP_ID_HAKMILIK", ID_HAKMILIK);
				h.put("MP_NO_HAKMILIK", NO_HAKMILIK);
				h.put("MP_NO_LOT", NO_LOT);
				h.put("MP_KOD_JENIS_HAKMILIK", KOD_JENIS_HAKMILIK);
				h.put("MP_NAMA_NEGERI", NAMA_NEGERI);
				h.put("MP_NAMA_DAERAH", NAMA_DAERAH);
				h.put("MP_ID_FAIL", ID_FAIL);
				h.put("MP_NAMA_MUKIM", NAMA_MUKIM);
				h.put("MP_KATEGORI_TANAH", KATEGORI_TANAH);
				h.put("MP_NAMA_LUAS_ASAL", NAMA_LUAS_ASAL);
				h.put("MP_HARI", HARI);
				h.put("MP_BULAN", BULAN);
				h.put("MP_TAHUN", TAHUN);
				h.put("MP_JENIS_RIZAB", JENIS_RIZAB);
				h.put("MP_NO_SYIT", NO_SYIT);
				h.put("MP_ID_SEKSYEN", ID_SEKSYEN);
				h.put("MP_STATUS_SPTB", STATUS_SPTB);
				h.put("MP_CATATAN", CATATAN);
				h.put("MP_SELESAI", SELESAI);
				h.put("MP_NAMA_PEMILIK", NAMA_PEMILIK.toUpperCase());
				
				v.add(h);					
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	
	public Vector viewSPTBMaklumat(String ID_PIHAKBERKEPENTINGAN) throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				String ID_PERMOHONAN = "", NAMA_PEMOHON = "", TARIKH_PERMOHONAN = "", NO_KP_PEMOHON = "", ALAMAT1_PEMOHON = "", ALAMAT2_PEMOHON = "", ALAMAT3_PEMOHON = "", ALAMAT_PEMOHON = "";
				String STATUS_BANGKRAP = "", CATATAN = "", SELESAI = "";
				String sql = "";
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Hashtable h = null;
				Boolean haveINTData = false;
				
				// if no data in TBLINT, get data from TBLPPT
				sql = "SELECT PM.ID_PERMOHONAN, PB.NAMA_PB, M.TARIKH_PERMOHONAN, PB.NO_PB, PB.ALAMAT1, PB.ALAMAT2, PB.ALAMAT3, M.STATUS_KEBANGKRAPAN, M.CATATAN, M.SELESAI " +
					"FROM TBLINTJIM M, TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
					"WHERE M.ID_PEMOHON = PB.ID_PIHAKBERKEPENTINGAN AND PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN " +
					"AND M.ID_PEMOHON = " + ID_PIHAKBERKEPENTINGAN;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveINTData = true;
					ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
					NAMA_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
					TARIKH_PERMOHONAN = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
					NO_KP_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
					ALAMAT1_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
					ALAMAT2_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
					ALAMAT3_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
					STATUS_BANGKRAP = rs.getString(8) == null ? "" : rs.getString(8);
					CATATAN = rs.getString(9) == null ? "" : rs.getString(9);
					SELESAI = rs.getString(10) == null ? "" : rs.getString(10);
					if (!"".equalsIgnoreCase(ALAMAT1_PEMOHON)) {
						ALAMAT_PEMOHON = ALAMAT1_PEMOHON;
					}
					if (!"".equalsIgnoreCase(ALAMAT2_PEMOHON)) {
						if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
							ALAMAT_PEMOHON += ", " + ALAMAT2_PEMOHON;
						} else {
							ALAMAT_PEMOHON = ALAMAT2_PEMOHON;
						}
					}
					if (!"".equalsIgnoreCase(ALAMAT3_PEMOHON)) {
						if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
							ALAMAT_PEMOHON += ", " + ALAMAT3_PEMOHON;
						} else {
							ALAMAT_PEMOHON = ALAMAT3_PEMOHON;
						}
					}
				}
				
				if (!haveINTData) {
					sql = "SELECT PM.ID_PERMOHONAN, PB.NAMA_PB, PM.TARIKH_PERMOHONAN, PB.NO_PB, PB.ALAMAT1, PB.ALAMAT2, PB.ALAMAT3, '', '', '' " +
						"FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM, TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTHAKMILIKPB HP " +
						"WHERE PM.ID_FAIL = FA.ID_FAIL AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN AND HM.ID_HAKMILIK = HP.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HP.ID_PIHAKBERKEPENTINGAN " +
						"AND PB.ID_PIHAKBERKEPENTINGAN = " + ID_PIHAKBERKEPENTINGAN;
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						ID_PERMOHONAN = rs.getString(1) == null ? "" : rs.getString(1);
						NAMA_PEMOHON = rs.getString(2) == null ? "" : rs.getString(2);
						TARIKH_PERMOHONAN = rs.getDate(3) == null ? "" : sdf.format(rs.getDate(3));
						NO_KP_PEMOHON = rs.getString(4) == null ? "" : rs.getString(4);
						ALAMAT1_PEMOHON = rs.getString(5) == null ? "" : rs.getString(5);
						ALAMAT2_PEMOHON = rs.getString(6) == null ? "" : rs.getString(6);
						ALAMAT3_PEMOHON = rs.getString(7) == null ? "" : rs.getString(7);
						STATUS_BANGKRAP = rs.getString(8) == null ? "" : rs.getString(8);
						CATATAN = rs.getString(9) == null ? "" : rs.getString(9);
						SELESAI = rs.getString(10) == null ? "" : rs.getString(10);
						if (!"".equalsIgnoreCase(ALAMAT1_PEMOHON)) {
							ALAMAT_PEMOHON = ALAMAT1_PEMOHON;
						}
						if (!"".equalsIgnoreCase(ALAMAT2_PEMOHON)) {
							if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
								ALAMAT_PEMOHON += ", " + ALAMAT2_PEMOHON;
							} else {
								ALAMAT_PEMOHON = ALAMAT2_PEMOHON;
							}
						}
						if (!"".equalsIgnoreCase(ALAMAT3_PEMOHON)) {
							if (!"".equalsIgnoreCase(ALAMAT_PEMOHON)) {
								ALAMAT_PEMOHON += ", " + ALAMAT3_PEMOHON;
							} else {
								ALAMAT_PEMOHON = ALAMAT3_PEMOHON;
							}
						}
					}
				}
				h = new Hashtable();
				h.put("haveINTData", haveINTData);
				h.put("MP_IDPERMOHONAN", ID_PERMOHONAN);
				h.put("MP_NAMAPEMOHON", NAMA_PEMOHON);
				h.put("MP_TARIKHPERMOHONAN", TARIKH_PERMOHONAN);
				h.put("MP_NOKPPEMOHON", NO_KP_PEMOHON);
				h.put("MP_ALAMATPEMOHON", ALAMAT_PEMOHON);
				h.put("MP_ALAMAT1PEMOHON", ALAMAT1_PEMOHON);
				h.put("MP_ALAMAT2PEMOHON", ALAMAT2_PEMOHON);
				h.put("MP_ALAMAT3PEMOHON", ALAMAT3_PEMOHON);
				h.put("MP_ALAMATPEMOHON", ALAMAT_PEMOHON);
				h.put("MP_STATUSKEBANGKRAPAN", STATUS_BANGKRAP);
				h.put("MP_CATATAN", CATATAN);
				h.put("MP_SELESAI", SELESAI);
				v.add(h);					
			}
		} finally {
			if (db != null)
				db.close();
		}
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Boolean saveSPTB(String ID_HAKMILIK,String ID_FAIL,String ID_SEKSYEN, Boolean isJIMUser, Hashtable h) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) &&!"".equalsIgnoreCase(ID_SEKSYEN) && !h.isEmpty()) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				Boolean haveData = false;
				String sql = "";
				
				long ID_SPTB = 0;
				
				
				//String ID_FAIL = "";
				String ID_PERMOHONAN = "";
				String NO_FAIL = "";
				String NO_PERMOHONAN = "";
			   //String ID_HAKMILIK = "";
				String NO_HAKMILIK = "";
				String NO_LOT = "";
				String KOD_JENIS_HAKMILIK = "";
				String NAMA_NEGERI = "";
				String NAMA_DAERAH = "";
				String NAMA_MUKIM = "";
				String KATEGORI_TANAH = "";
				String NAMA_LUAS_ASAL = "";
				String HARI = "";
				String BULAN = "";
				String TAHUN = "";
				String JENIS_RIZAB = "";
				String NO_SYIT = "";
				//String ID_SEKSYEN = "";
				String STATUS_SPTB = "";
				String CATATAN = "";
				String SELESAI = "";
				String NAMA_PEMILIK = "";
				
				String STATUS_KEBANGKRAPAN = "";
				String STATUS_PROSES = "";
				
				
				if(ID_SEKSYEN.equals("1"))
				{				
				sql = " SELECT FA.ID_SUBURUSAN,SU.ID_SEKSYEN,FA.NO_FAIL,FA.ID_FAIL, PM.ID_PERMOHONAN,PM.NO_PERMOHONAN,HM.ID_HAKMILIK,HM.NO_HAKMILIK,HM.NO_LOT " +
				  " ,JHM.KOD_JENIS_HAKMILIK,N.NAMA_NEGERI,D.NAMA_DAERAH,M.NAMA_MUKIM," +
				  " KT.KETERANGAN AS KATEGORI_TANAH,HM.NAMA_LUAS_ASAL " +
				  " ,TO_CHAR(HM.TARIKH_DAFTAR,'DD') AS HARI, "+
				  " CASE WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='01' THEN 'Januari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='02' THEN 'Februari' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='03' THEN 'Mac' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='04' THEN 'April' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='05' THEN 'Mei' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='06' THEN 'Jun' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='07' THEN 'Julai' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='08' THEN 'Ogos' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='09' THEN 'September' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='10' THEN 'Oktober' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='11' THEN 'November' "+
					        " WHEN TO_CHAR(HM.TARIKH_DAFTAR,'MM')='12' THEN 'Disember' "+
					    " END AS BULAN, TO_CHAR(HM.TARIKH_DAFTAR,'yyyy') AS TAHUN "+
					    " ,CASE WHEN HM.FLAG_JENIS_RIZAB ='1' THEN 'Kawasan Rizab Melayu' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='2' THEN 'Kawasan Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='3' THEN 'Rizab Orang Asli' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='4' THEN 'Kawasan Penempatan Berkelompok' "+
					        " WHEN HM.FLAG_JENIS_RIZAB ='5' THEN  INITCAP(HM.NAMA_LAIN_RIZAB) "+
					        " ELSE '' "+   
					    " END AS JENIS_RIZAB,HM.NO_SYIT , "+
				    "(select "+
				    "substr(rtrim (xmlagg (xmlelement (e, nama_pemilik)).extract ('//text()'), ','),3) list_pemilik "+
				    "from "+   
				    "  (    "+    
				    " select to_char( "+
				    "  case  "+
				    		    " when "+  
				    		    " (count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) = "+ 
				    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+ 
				    		    " and "+
				    		    " (count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) <> 1) "+
				    		    " then "+
				    		    " ' dan ' "+
				    		    " when  count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
				    		    " else "+
				    		    " ', ' "+
				    		    " end || initcap(pb.nama_pb) || ' (' || pb.no_pb || ')' ) as nama_pemilik, "+
				    		    "    count(*) OVER  "+
				    		    " ( partition by su.id_seksyen ) "+ 
				    		    " cnt, "+
				    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb) seq, "+
				    		    " case when   "+
				    		    " (count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) = "+ 
				    		    " ROW_NUMBER () OVER ( partition by su.id_seksyen order by pb.nama_pb)) "+
				    		    " and "+
				    		    " (count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) <> 1) "+  
				    		    " then "+
				    		    " ' dan ' "+
				    		    " when  count(*) OVER "+ 
				    		    " ( partition by su.id_seksyen ) = 1 then ', ' "+
				    		    " else "+
				    		    "  ', ' "+
				    		    " end as  "+
				    		    " symbol     "+
				    		    "  from "+
				    		    "    tblpptpihakberkepentingan pb,tblppthakmilikpb hpb,tblppthakmilik hmm, "+
				    		    "    tblpptpermohonan p,tblpfdfail ff,tblrujsuburusan su "+
				    		    "    where pb.id_pihakberkepentingan = hpb.id_pihakberkepentingan "+
				    		    "    and hpb.id_jenispb = '1' "+
				    		    "    and hmm.id_hakmilik = hpb.id_hakmilik "+
				    		    "    and hmm.id_permohonan = p.id_permohonan "+
				    		    "    and p.id_fail = ff.id_fail "+
				    		    "   and ff.id_suburusan = su.id_suburusan "+    
				    		    " AND  hmm.id_hakmilik = "+ID_HAKMILIK+ 
								" AND p.id_fail = "+ID_FAIL+
								" AND su.id_seksyen = "+ID_SEKSYEN+	
				    	"	)) as NAMA_PEMILIK "+
					" FROM TBLPPTPERMOHONAN PM, TBLPFDFAIL FA, TBLPPTHAKMILIK HM "+
					" ,TBLRUJJENISHAKMILIK JHM ,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJMUKIM M,TBLRUJSUBURUSAN SU," +
					" TBLRUJKATEGORI KT "+
					" WHERE PM.ID_FAIL = FA.ID_FAIL "+
					" AND FA.ID_SUBURUSAN = SU.ID_SUBURUSAN"+
					" AND HM.ID_KATEGORITANAH = KT.ID_KATEGORI(+) "+
					" AND HM.ID_NEGERI = N.ID_NEGERI(+) "+
					" AND HM.ID_DAERAH = D.ID_DAERAH(+) "+
					" AND HM.ID_MUKIM = M.ID_MUKIM(+) "+
					" AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) "+
					" AND PM.ID_PERMOHONAN = HM.ID_PERMOHONAN "+ 
					" AND HM.ID_HAKMILIK = HM.ID_HAKMILIK  "+ 
					" AND HM.ID_HAKMILIK = "+ID_HAKMILIK+ 
					" AND FA.ID_FAIL = "+ID_FAIL+
					" AND SU.ID_SEKSYEN = "+ID_SEKSYEN+							
					" ORDER BY FA.NO_FAIL, HM.NO_HAKMILIK ";
				
				myLogger.info("KOMA TEST :"+sql.toUpperCase());
				
				}
				
				
				if(ID_SEKSYEN.equals("2"))
				{
				
				   sql = " SELECT  sm.no_kp_baru, sm.id_simati,hm.id_hta,fa.id_suburusan, su.id_seksyen, fa.no_fail, fa.id_fail,  "+
					     " pm.id_permohonan, pm.no_permohonan, (hm.id_hta) as id_hakmilik, hm.no_hakmilik, "+
					     " (hm.no_pt) as no_lot, jhm.kod_jenis_hakmilik, n.nama_negeri, d.nama_daerah, "+
					     " m.nama_mukim, kt.keterangan AS kategori_tanah,  "+
					     " case  "+
					     " when sm.no_kp_baru != ' ' "+
					     " then sm.nama_simati || ' (' || sm.no_kp_baru || ')' "+
					     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and  sm.no_kp_lama != ' ' "+
				 	     " then sm.nama_simati || ' (' || sm.no_kp_lama || ')' "+
					     " when (sm.no_kp_baru = ' ' or sm.no_kp_baru = null) and (sm.no_kp_lama = ' ' or sm.no_kp_lama = null) and  sm.no_kp_lain != ' ' "+
					     " then sm.nama_simati || ' (' || jnpb.keterangan || ' '|| sm.no_kp_lain || ')' "+
					     " else "+ 
					     " sm.nama_simati "+
					     " end as nama_pemilik "+       
					     " FROM tblppkpermohonan pm, "+
					     " tblpfdfail fa, "+
					     " tblppkhta hm, "+
					     " tblrujjenishakmilik jhm, "+
					     " tblrujdaerah d, "+
					     " tblrujnegeri n, "+
					     " tblrujmukim m, "+
					     " tblrujsuburusan su, "+						    
					     " tblrujkategori kt, "+
					     " tblppksimati sm, "+
					     " tblppkpermohonansimati psm, "+
					     " tblrujjenisnopb jnpb "+
					     " WHERE pm.id_fail = fa.id_fail "+
					     " AND sm.jenis_kp = jnpb.id_jenisnopb(+) "+ 
					     " AND pm.id_permohonan = psm.id_permohonan "+
					     " AND psm.id_simati = sm.id_simati "+
					     " AND sm.id_simati = hm.id_simati "+
					     " AND fa.id_suburusan = su.id_suburusan "+
					     " AND hm.id_kategori = kt.id_kategori(+) "+
					     " AND hm.id_negeri = n.id_negeri(+) "+
					     " AND hm.id_daerah = d.id_daerah(+) "+
					     " AND hm.id_mukim = m.id_mukim(+) "+
				 	     " AND hm.id_jenishm = jhm.id_jenishakmilik(+) "+     
					     " AND hm.id_hta = "+ID_HAKMILIK+
					     " AND fa.id_fail =  "+ID_FAIL+
					     " AND su.id_seksyen = "+ID_SEKSYEN+
					     
					     " ORDER BY fa.no_fail, hm.no_hakmilik ";
					
				}
			
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ID_FAIL = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
					ID_PERMOHONAN = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
					NO_FAIL = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
					NO_PERMOHONAN = rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN");
					ID_HAKMILIK = rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK");
					NO_HAKMILIK = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
					NO_LOT = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
					KOD_JENIS_HAKMILIK = rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
					NAMA_NEGERI = rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI");
					NAMA_DAERAH = rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH");
					NAMA_MUKIM = rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM");
					KATEGORI_TANAH = rs.getString("KATEGORI_TANAH") == null ? "" : rs.getString("KATEGORI_TANAH");
					
					/*NAMA_LUAS_ASAL = rs.getString("NAMA_LUAS_ASAL") == null ? "" : rs.getString("NAMA_LUAS_ASAL");
					HARI = rs.getString("HARI") == null ? "" : rs.getString("HARI");
					BULAN  = rs.getString("BULAN") == null ? "" : rs.getString("BULAN");
					TAHUN = rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN");
					JENIS_RIZAB = rs.getString("JENIS_RIZAB") == null ? "" : rs.getString("JENIS_RIZAB");
					NO_SYIT = rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT");*/					
					
					ID_SEKSYEN = rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN");					
					NAMA_PEMILIK = rs.getString("NAMA_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK");
					
					
					if(ID_SEKSYEN.equals("1"))
					{
					NAMA_LUAS_ASAL = rs.getString("NAMA_LUAS_ASAL") == null ? "" : rs.getString("NAMA_LUAS_ASAL");
					HARI = rs.getString("HARI") == null ? "" : rs.getString("HARI");
					BULAN  = rs.getString("BULAN") == null ? "" : rs.getString("BULAN");
					TAHUN = rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN");
					JENIS_RIZAB = rs.getString("JENIS_RIZAB") == null ? "" : rs.getString("JENIS_RIZAB");
					NO_SYIT = rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT");
					}
					
					if(ID_SEKSYEN.equals("2"))
					{
					NAMA_LUAS_ASAL = "";
					HARI = "";
					BULAN  = "";
					TAHUN = "";
					JENIS_RIZAB = "";
					NO_SYIT = "";
					}
					
					
				}
				
				
				
				STATUS_KEBANGKRAPAN = (String) h.get("MP_STATUSKEBANGKRAPAN");
				CATATAN = (String) h.get("MP_CATATAN");
				SELESAI = (String) h.get("MP_SELESAI");
				if (STATUS_KEBANGKRAPAN == null) 
					STATUS_KEBANGKRAPAN = "";
				if ("1".equalsIgnoreCase(SELESAI)) {
					STATUS_PROSES = "SELESAI";
				} else {
					if (isJIMUser) {
						STATUS_PROSES = "DALAM PROSES SPBT";
					} else {
						STATUS_PROSES = "BARU";
					}
				}
				
				// check in TBLINTJIM
				r.add("ID_HAKMILIK");
				r.add("ID_FAIL");
				r.add("ID_SEKSYEN");
				r.add("ID_HAKMILIK", ID_HAKMILIK);
				r.add("ID_FAIL", ID_FAIL);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				sql = r.getSQLSelect("TBLINTSPTB");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					haveData = true;
				}
				
				if (haveData) {					
					r.update("ID_HAKMILIK", ID_HAKMILIK);
					r.update("ID_FAIL", ID_FAIL);
					r.update("ID_SEKSYEN", ID_SEKSYEN);
				}
				if (!"0".equalsIgnoreCase(SELESAI) && !"1".equalsIgnoreCase(SELESAI)) {
					SELESAI = "0";
				}
				
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("NO_FAIL", NO_FAIL);
				r.add("NO_PERMOHONAN", NO_PERMOHONAN);
				r.add("ID_HAKMILIK", ID_HAKMILIK);
				r.add("NO_HAKMILIK", NO_HAKMILIK);
				r.add("NO_LOT", NO_LOT);
				r.add("KOD_JENIS_HAKMILIK", KOD_JENIS_HAKMILIK);
				r.add("NAMA_NEGERI", NAMA_NEGERI);
				r.add("NAMA_DAERAH", NAMA_DAERAH);
				r.add("ID_FAIL", ID_FAIL);
				r.add("NAMA_MUKIM", NAMA_MUKIM);
				r.add("KATEGORI_TANAH", KATEGORI_TANAH);
				r.add("NAMA_LUAS_ASAL", NAMA_LUAS_ASAL);
				r.add("HARI", HARI);
				r.add("BULAN", BULAN);
				r.add("TAHUN", TAHUN);				
				r.add("JENIS_RIZAB", JENIS_RIZAB);
				r.add("NO_SYIT", NO_SYIT);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				r.add("STATUS_SPTB", STATUS_KEBANGKRAPAN);
				r.add("CATATAN", CATATAN);
				r.add("SELESAI", SELESAI);
				r.add("NAMA_PEMILIK", NAMA_PEMILIK.toUpperCase());
				r.add("STATUS_PROSES", STATUS_PROSES);
				
				/*
				r.add("STATUS_PROSES", STATUS_PROSES);
				r.add("ID_PERMOHONAN", ID_PERMOHONAN);
				r.add("NO_PERMOHONAN", NO_PERMOHONAN);
				r.add("ID_FAIL", ID_FAIL);
				r.add("NO_FAIL", NO_FAIL);
				r.add("TARIKH_PERMOHONAN", r.unquote("TO_DATE('" + TARIKH_PERMOHONAN + "', 'dd/MM/yyyy')"));
				r.add("ID_PEMOHON", ID_PIHAKBERKEPENTINGAN);
				r.add("NAMA_PEMOHON", NAMA_PEMOHON);
				r.add("NO_KP_PEMOHON", NO_KP_PEMOHON);
				r.add("ALAMAT1_PEMOHON", ALAMAT1_PEMOHON);
				r.add("ALAMAT2_PEMOHON", ALAMAT2_PEMOHON);
				r.add("ALAMAT3_PEMOHON", ALAMAT3_PEMOHON);
				r.add("STATUS_KEBANGKRAPAN", STATUS_KEBANGKRAPAN);
				r.add("CATATAN", CATATAN);
				r.add("SELESAI", SELESAI);*/
				if (haveData) {
					sql = r.getSQLUpdate("TBLINTSPTB");
				} else {
					ID_SPTB = DB.getNextID("TBLINTSPTB_SEQ");
					r.add("ID_SPTB", ID_SPTB);
					r.add("TARIKH_SIMPAN", r.unquote("SYSDATE"));
					sql = r.getSQLInsert("TBLINTSPTB");
				}
				returnValue = true;
				stmt.execute(sql);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
/*
	public Boolean sendSPTB(String ID_PIHAKBERKEPENTINGAN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_PIHAKBERKEPENTINGAN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				// check in TBLINTJIM
				r.add("ID_PEMOHON");
				r.add("ID_PEMOHON", ID_PIHAKBERKEPENTINGAN);
				sql = r.getSQLSelect("TBLINTJIM");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					r.add("STATUS_PROSES", "BARU");
					r.add("SELESAI", "0");
					r.update("ID_PEMOHON", ID_PIHAKBERKEPENTINGAN);
					sql = r.getSQLUpdate("TBLINTJIM");
					returnValue = true;
					stmt.execute(sql);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	*/
	public Boolean sendSPTB(String ID_FAIL,String ID_HAKMILIK,String ID_SEKSYEN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				// check in TBLINTJIM
				r.add("ID_HAKMILIK");
				r.add("ID_FAIL");
				r.add("ID_SEKSYEN");
				r.add("ID_HAKMILIK", ID_HAKMILIK);
				r.add("ID_FAIL", ID_FAIL);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				sql = r.getSQLSelect("TBLINTSPTB");
				
				myLogger.info("SQL SEND :"+sql.toUpperCase());
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					r.add("STATUS_PROSES", "BARU");
					r.add("SELESAI", "0");
					r.update("ID_HAKMILIK", ID_HAKMILIK);
					r.update("ID_FAIL", ID_FAIL);
					r.update("ID_SEKSYEN", ID_SEKSYEN);
					sql = r.getSQLUpdate("TBLINTSPTB");
					returnValue = true;
					stmt.execute(sql);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean sendJKPTG(String ID_FAIL,String ID_HAKMILIK,String ID_SEKSYEN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				// check in TBLINTJIM
				r.add("ID_HAKMILIK");
				r.add("ID_FAIL");
				r.add("ID_SEKSYEN");
				r.add("ID_HAKMILIK", ID_HAKMILIK);
				r.add("ID_FAIL", ID_FAIL);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				sql = r.getSQLSelect("TBLINTSPTB");
				
				myLogger.info("SQL SEND :"+sql.toUpperCase());
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					r.add("STATUS_PROSES", "SELESAI");
					r.add("SELESAI", "1");
					r.update("ID_HAKMILIK", ID_HAKMILIK);
					r.update("ID_FAIL", ID_FAIL);
					r.update("ID_SEKSYEN", ID_SEKSYEN);
					sql = r.getSQLUpdate("TBLINTSPTB");
					returnValue = true;
					stmt.execute(sql);
				}
			}
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean deleteSPTB(String ID_HAKMILIK,String ID_FAIL,String ID_SEKSYEN) throws Exception, DbException, SQLException {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_HAKMILIK) && !"".equalsIgnoreCase(ID_FAIL) && !"".equalsIgnoreCase(ID_SEKSYEN)) {
				SQLRenderer r = new SQLRenderer();
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				
				r.add("ID_SPTB");
				r.add("ID_HAKMILIK", ID_HAKMILIK);
				r.add("ID_FAIL", ID_FAIL);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				sql = r.getSQLSelect("TBLINTSPTB");
				r.clear();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					String ID_SPTB = "";
					ID_SPTB = rs.getString(1) == null ? "" : rs.getString(1);
					r.add("ID_SPTB", ID_SPTB);
					sql = r.getSQLDelete("TBLINTSPTB");
					stmt.execute(sql);
					returnValue = true;
				}
			}
		} finally {
			if (db != null) 
				db.close();
		}
		return returnValue;
	}
	
	private  Vector listHTAbyIdHtaam= new Vector();
	 public void setDataHTAbyIdHtaam(String idhtaam) throws Exception {
			Db db = null;
			listHTAbyIdHtaam.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try{
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

		/*	r.add("h.id_Hta");
			r.add("h.no_Hakmilik");
			r.add("h.id_Simati");
			r.add("h.no_Pt");
			r.add("h.nilai_Hta_Tarikhmohon");
			r.add("h.nilai_Hta_Tarikhmati");
			r.add("h.id_Kategori");
			r.add("h.id_Jenishm");
			r.add("h.id_Jenispb");
			r.add("h.id_Negeri");
			r.add("h.id_Daerah");
			r.add("h.id_Luas");
			r.add("h.id_Mukim");
			r.add("h.luas_Hmp");
			r.add("h.luas");
			r.add("h.no_Cagaran");
			r.add("h.no_Pajakan");
			r.add("h.jenis_Tnh");
			r.add("h.ba_Simati");
			r.add("h.bb_Simati");
			r.add("h.jenis_Hta");
			r.add("h.tanggungan");
			r.add("h.no_Perserahan");
			r.add("h.catatan");
			r.add("h.status_Pemilikan");

			String status="Y";

			r.add("h.id_simati",r.unquote("s.id_simati"));
			r.add("h.jenis_Hta",status);
			r.add("h.id_Hta",idhtaam);
			sql = r.getSQLSelect("Tblppkhta h, Tblppksimati s");
            */
				
				
			sql = "	SELECT H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, "+
			"	H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, TO_CHAR(H.LUAS_HMP,'999999999990.9999') AS LUAS_HMP, "+ 
			"	 H.NO_CAGARAN, H.NO_PAJAKAN, H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, "+
			"	H.TANGGUNGAN, H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, "+ 
			"	CASE  WHEN H.ID_KATEGORI = 2 THEN 'HEKTAR' WHEN H.ID_KATEGORI != 2 THEN 'METER PERSEGI' ELSE '' END  AS JENIS_LUAS, RJT.KETERANGAN AS JENIS_TANAH "+
			"	FROM TBLPPKHTA H, TBLPPKSIMATI S,TBLRUJLUAS RJL,TBLRUJJENISTANAH RJT "+ 
			"	WHERE H.ID_SIMATI = S.ID_SIMATI "+ 
			"	AND H.ID_HTA = "+idhtaam+
			"	AND H.ID_LUAS = RJL.ID_LUAS(+) "+
			"	AND H.JENIS_TNH = RJT.ID_JENISTANAH(+)";
				
				
			myLogger.info("SQL HTA :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;

			while(rs.next()) {
				h = new Hashtable();
				
				h.put("JENIS_TANAH", rs.getString("JENIS_TANAH")==null?"":rs.getString("JENIS_TANAH"));
				h.put("JENIS_LUAS", rs.getString("JENIS_LUAS")==null?"":rs.getString("JENIS_LUAS"));
				
				
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));

				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("status_Pemilikan")==null?"":rs.getString("status_Pemilikan"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
			//	h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
				h.put("nocagaran", rs.getString("no_Cagaran")==null?"":rs.getString("no_Cagaran"));
				h.put("nopajakan", rs.getString("no_Pajakan")==null?"":rs.getString("no_Pajakan"));
				h.put("jenistanah", rs.getString("jenis_Tnh")==null?"":rs.getString("jenis_Tnh"));
				h.put("basimati", rs.getString("ba_Simati")==null?"":rs.getString("ba_Simati"));
				h.put("bbsimati", rs.getString("bb_Simati")==null?"":rs.getString("bb_Simati"));
				h.put("jenishta", rs.getString("jenis_Hta")==null?"":rs.getString("jenis_Hta"));
				h.put("tanggungan", rs.getString("tanggungan")==null?"":rs.getString("tanggungan"));
				h.put("jenisluas", rs.getString("id_Luas")==null?"":rs.getString("id_Luas"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				h.put("noperserahan", rs.getString("no_Perserahan")==null?"":rs.getString("no_Perserahan"));


				//System.out.println(h);
				listHTAbyIdHtaam.addElement(h);
			}
			}
			finally {
				if(db != null) db.close();
			}

		}


	 public Vector getDataHTAbyIdHtaam(){
			return listHTAbyIdHtaam;
		}
}