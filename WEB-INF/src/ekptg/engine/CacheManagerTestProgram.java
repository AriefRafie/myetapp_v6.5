package ekptg.engine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lebah.db.Db;

/**
 * Title: Caching Description: A test program for the CacheManager Copyright:
 * Copyright (c) 2001 Company: Filename: CacheManagerTestProgram.java
 * 
 * @author Jonathan Lurie
 * @version 1.0
 */
public class CacheManagerTestProgram {
	/*
	public CacheManagerTestProgram() {
	}
	*/
	public static void main(String[] args) throws Exception {
		CacheManagerTestProgram cacheManagerTestProgram1 = new CacheManagerTestProgram();
		/*
		 * This is the object that we are going to cache. Admittedly this is a
		 * trivial object to cache. You might replace our alphabet with a list
		 * of every character in the world.
		 */
		//String s = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		//System.out.println(" SHOW LIST MULA! "+ s);
		/*
		 * Create an instance of CachedObject, set the minutesToLive to 1
		 * minute. Give the object some unique identifier.
		 */
		CachedObject o = (CachedObject) CacheManager.getCache("SenaraiFail");
		System.out.println("check null ! "+ o);
		if(o != null)
		{
			if(o.isExpired())
			{
				System.out.println("check expired! "+ o.isExpired());
				List s = carianFailCache();
				CachedObject co = new CachedObject(s, "SenaraiFail", 1);
				CacheManager.putCache(co);
			}
			else
			{
				List ss = (List) CacheManager.getCache(o);
				System.out.println("ss! "+ ss);
			}
		}
		else
		{	
			List s = carianFailCache();
			CachedObject co = new CachedObject(s, "SenaraiFail", 1);
			CacheManager.putCache(co);
		}
		
		/* Place the object into the cache! */
		
		/* Try to retrieve the object from the cache! */
		
		/* Let's see if we got it! */
		
		//System.out.println("check expired! "+ o.isExpired());
		/*
		if (o == null)			
			System.out.println("CacheManagerTestProgram.Main:  FAILURE!  Object not Found.");
		else
			System.out.println("CacheManagerTestProgram.Main:  SUCCESS! "+ o);
		*/
	}
	
	public static List carianFailCache()
			throws Exception {

		String userId = "1";
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		
		List senaraiFail = null;

		String sql = "";
		Integer count = 0;

		try {
			// senaraiFail = new Vector();
			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

		
			sql = " SELECT SEKSYEN,ID_STATUS, ID_FAIL, NO_FAIL, "
					+ " TARIKH_DAFTAR_FAIL, ID_PERMOHONAN, TARIKH_MOHON, "
					+ " TARIKH_MASUK, ID_SIMATI, NAMA_SIMATI, ID_PEMOHON, "
					+ " NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, "
					+ " KETERANGAN,  ID_PERMOHONANSIMATI, "
					+ " FLAG_JENIS_FAIL, USER_LOGIN, NO_SIJIL_MATI FROM "
					+ " ( ";

			sql = sql
					+ "	SELECT DISTINCT B.SEKSYEN, B.ID_STATUS, B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
					+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS AS ID_STATUS2, H.ID_PERMOHONANSIMATI, A.FLAG_JENIS_FAIL, K.USER_LOGIN, C.NO_SIJIL_MATI"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J, USERS K"
					+ " WHERE "
					+ " A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
					+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND "
					+ "B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND B.ID_MASUK = K.USER_ID "
					+ "AND E.AKTIF = 1 AND G.ID_STATUS NOT IN (155) "
					+
					// "AND B.ID_STATUS NOT IN (999,56) "+ buang filter 56
					"AND B.ID_STATUS NOT IN (999) "
					+ "AND J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = "
					+ userId + ")";

		

			sql = sql
					+ ""
					+ " ORDER BY TO_DATE(NVL(B.TARIKH_MOHON,TO_DATE('01/01/2010','DD/MM/YYYY'))) DESC) ";

		
			System.out.println("CACHE FAIL TUGASAN:" + sql);
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			// Hashtable h = new Hashtable();
			int bil = 1;
			senaraiFail = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				// h = new Hashtable();
				// h.clear();

				h.put("bil", bil);
				h.put("idpermohonansimati",
						rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs
								.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("noFail",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? ""
						: sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("idSimati",
						rs.getString("ID_SIMATI") == null ? "" : rs
								.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("flagjenisfail",
						rs.getString("FLAG_JENIS_FAIL") == null ? "" : rs
								.getString("FLAG_JENIS_FAIL"));
				h.put("seksyen",
						rs.getString("SEKSYEN") == null ? "" : rs
								.getString("SEKSYEN"));
				h.put("daftarOleh", rs.getString("USER_LOGIN") == null ? ""
						: rs.getString("USER_LOGIN"));
				h.put("noSijilMati", rs.getString("NO_SIJIL_MATI") == null ? ""
						: rs.getString("NO_SIJIL_MATI"));

				String no_fail = rs.getString("no_Fail") == null ? "" : rs
						.getString("no_Fail");

			

				// senaraiFail.addElement(h);
				senaraiFail.add(h);

				bil++;
				count++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();

		}
		return senaraiFail;
		
	}

}