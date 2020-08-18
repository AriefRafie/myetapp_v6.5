/**
 * 
 */
package ekptg.model.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;

/**
 * 
 * 20/4/2010 - Add Fail Pindah Masuk count
 */

public class FrmSenaraiPermohonanSeksyen8Data extends EkptgCache {
	static Logger myLogger = Logger
			.getLogger(FrmSenaraiPermohonanSeksyen8Data.class);

	// private Vector senaraiFail = null;

	public List carianFail(String noFail, String namaPemohon,
			String namaSimati, String jenisKp, String noKp,
			HttpSession session, String tarikhMohon, String status,
			String jenisKpPemohon, String noKpPemohon, String noHakmilik,
			String noSijilMati, String tarikhBicara, String noLot)
			throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		// String key = "carianFail"+noFail+namaPemohon+namaSimati+jenisKp+noKp+
		// tarikhMohon+status+jenisKpPemohon+
		// noKpPemohon+noHakmilik+noSijilMati+tarikhBicara+noLot+userId;

		// Element cachedObject = myCache.get(key);
		// if (cachedObject != null) {
		// return (Vector)cachedObject.getObjectValue();
		// } else {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		// Vector list = null;
		// senaraiFail.clear();

		List senaraiFail = null;

		String sql = "";
		Integer count = 0;

		try {
			// senaraiFail = new Vector();
			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			String chkDataFail = noFail.trim();
			String chkNamaPemohon = namaPemohon.trim();
			String chkNamaSimati = namaSimati.trim();
			String chkNoHakmilik = noHakmilik.trim();
			String chkNoSijilMati = noSijilMati.trim();
			String chkNoLot = noLot.trim();
/*
			sql = " SELECT " +
					" PETISYEN_NO," +
					" SEKSYEN,ID_STATUS, ID_FAIL, NO_FAIL, "
					+ " TARIKH_DAFTAR_FAIL, ID_PERMOHONAN, TARIKH_MOHON, "
					+ " TARIKH_MASUK, ID_SIMATI, NAMA_SIMATI, ID_PEMOHON, "
					+ " NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, "
					+ " KETERANGAN,  ID_PERMOHONANSIMATI, "
					+ " FLAG_JENIS_FAIL, USER_LOGIN, NO_SIJIL_MATI FROM "
					+ " ( ";

			sql = sql
					+ "	SELECT DISTINCT " +
					" MAHKAMAH_BORANGC.PETISYEN_NO, " +
					" B.SEKSYEN, B.ID_STATUS, B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
					+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS AS ID_STATUS2, H.ID_PERMOHONANSIMATI, A.FLAG_JENIS_FAIL, K.USER_LOGIN, C.NO_SIJIL_MATI"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, "
					
					
					+" (SELECT K.IDKEPUTUSAN AS ID_KEPUTUSAN, "
					+" K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C, "
					+" K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C, "
					+" K.IDKADBIRU AS ID_KAD_BIRU, K.JENISTRANSAKSI AS JENIS_TRANSAKSI, "
			        +" K.TARIKHPROSES AS TARIKH_PROSES, K.BORANGCEXTRACTKOD AS EXTRACTION_KOD, "
			        +" K.FLAG_REP AS FLAG_REP, K.TARIKH_REP AS TARIKH_REP, "
			        +" M.PETISYENNO AS PETISYEN_NO "
					+" FROM TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M "
					+" WHERE M.FLAG_REP = '3'  AND K.IDKADBIRU = M.IDKADBIRU) MAHKAMAH_BORANGC, "
					
					
					+ " TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J, USERS K"
					+ " WHERE "
					+ " A.NO_FAIL = MAHKAMAH_BORANGC.PETISYEN_NO(+) AND "
					+ " A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
					+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND "
					+ " B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND B.ID_MASUK = K.USER_ID "
					+ " AND E.AKTIF = 1 AND G.ID_STATUS NOT IN (155) "
					+
					// "AND B.ID_STATUS NOT IN (999,56) "+ buang filter 56
					"AND B.ID_STATUS NOT IN (999) "
					+ "AND J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = "
					+ userId + ")";
*/
			// dapat flag
						boolean setLimit = true;
			
			
			// noFail
						if (noFail != null) {
							if (!noFail.trim().equals("")) {
								setLimit = false;
							}
						}

						// namaPemohon
						if (namaPemohon != null) {
							if (!namaPemohon.trim().equals("")) {
								setLimit = false;
							}
						}

						// namaSimati
						if (namaSimati != null) {
							if (!namaSimati.trim().equals("")) {
								setLimit = false;
							}
						}

						// noHakmilik
						if (noHakmilik != null) {
							if (!noHakmilik.trim().equals("")) {
								setLimit = false;
							}
						}

						// jenisKp & noKp
						if (jenisKp != null) {
							if (!jenisKp.trim().equals("")) {
								if (noKp != null) {
									if (!noKp.trim().equals("")) {
										setLimit = false;
									}
								}
							}
						}

						// jenisKpPemohon & noKpPemohon
						if (jenisKpPemohon != null) {
							if (!jenisKpPemohon.trim().equals("")) {
								if (noKpPemohon != null) {
									if (!noKpPemohon.trim().equals("")) {
										setLimit = false;
									}
								}
							}
						}

						// SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

						// tarikhMohon
						if (tarikhMohon != null) {
							if (!tarikhMohon.toString().trim().equals("")) {
								setLimit = false;
							}
						}

						// no sijil mati
						if (noSijilMati != null) {
							if (!noSijilMati.trim().equals("")) {
								setLimit = false;
							}
						}

						// status
						if (status != null) {
							if (!status.trim().equals("") && !status.trim().equals("0")) {
								setLimit = false;
							}
						}

						// noLot/noPT
						if (noLot != null) {
							if (!noLot.trim().equals("")) {
								setLimit = false;
							
							}
						}

						// tarikhBicara
						if (tarikhBicara != null && !"".equals(tarikhBicara)) {
							setLimit = false;							
						}
			
			
			
			
			sql = " SELECT PETISYEN_NO, SEKSYEN, ID_STATUS, ID_FAIL, NO_FAIL, TARIKH_DAFTAR_FAIL, "+
			" 		       ID_PERMOHONAN, TARIKH_MOHON, TARIKH_MASUK, ID_SIMATI, NAMA_SIMATI, "+
			" 		       ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, "+
			" 		       KETERANGAN, ID_PERMOHONANSIMATI, FLAG_JENIS_FAIL, USER_LOGIN, UPPER(USER_NAME) AS USER_NAME, "+
			" 		       NO_SIJIL_MATI "+
			" 		  FROM ( "+
			" 		  SELECT DISTINCT MAHKAMAH_BORANGC.PETISYEN_NO, B.SEKSYEN, B.ID_STATUS, "+
			" 		                        B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, "+
			" 		                        B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, "+
			" 		                        C.ID_SIMATI, C.NAMA_SIMATI, D.ID_PEMOHON, "+
			" 		                        D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, "+
			" 		                        D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS AS ID_STATUS2, "+
			" 		                        H.ID_PERMOHONANSIMATI, A.FLAG_JENIS_FAIL, "+
			" 		                        K.USER_LOGIN, K.USER_NAME,  C.NO_SIJIL_MATI "+
			" 		                   FROM TBLPFDFAIL A,                    "+    
			" 		                        (                           "+
			" 		                        SELECT DISTINCT ID_PERMOHONAN,TARIKH_MOHON,ID_FAIL, SEKSYEN, ID_STATUS, ID_MASUK,ID_PEMOHON,TARIKH_MASUK, ID_DAERAHMHN,ID_NEGERIMHN "+ 
			" 		                        FROM "+
			" 		                        (       "+                  
			" 		                        SELECT BB.ID_PERMOHONAN,BB.TARIKH_MOHON,BB.ID_FAIL, BB.SEKSYEN, BB.ID_STATUS, "+ 
			" 		                        BB.ID_MASUK, BB.ID_PEMOHON, BB.TARIKH_MASUK, BB.ID_DAERAHMHN,BB.ID_NEGERIMHN  "+
			" 		                        FROM TBLPPKPERMOHONAN BB WHERE BB.ID_DAERAHMHN IN (   "+
			" 		                           SELECT DISTINCT U.ID_DAERAHURUS "+
			" 		                                      FROM TBLRUJPEJABATURUSAN U, "+
			" 		                                           USERS_INTERNAL UR "+
			" 		                                     WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
			" 		                                       AND UR.USER_ID = "+userId+"                    ";
			
			sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
			
			
			sql += " 		                                       ) "+
			" 		                                        ORDER BY (NVL(BB.TARIKH_MOHON,TO_DATE('01/01/2010','DD/MM/YYYY'))) DESC "+             
			" 		                                       ) ";
			
			if(setLimit==true)
			{
				sql += " WHERE ROWNUM < 1000 ";
			}
			
			//" WHERE ROWNUM < 250    "+
			sql += " 		                                       ) B, "+
			" 		                        TBLPPKSIMATI C, "+
			" 		                        TBLPPKPEMOHON D, "+
			" 		                        (                   "+      
			" 		                        SELECT K.IDKEPUTUSAN AS ID_KEPUTUSAN, "+
			" 		                                K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C, "+
			" 		                                K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C, "+
			" 		                                K.IDKADBIRU AS ID_KAD_BIRU, "+
			" 		                                K.JENISTRANSAKSI AS JENIS_TRANSAKSI, "+
			" 		                                K.TARIKHPROSES AS TARIKH_PROSES, "+
			" 		                                K.BORANGCEXTRACTKOD AS EXTRACTION_KOD, "+
			" 		                                K.FLAG_REP AS FLAG_REP, "+
			" 		                                K.TARIKH_REP AS TARIKH_REP, "+
			" 		                                M.PETISYENNO AS PETISYEN_NO "+
			" 		                           FROM TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M "+
			" 		                          WHERE M.FLAG_REP = '3' AND K.IDKADBIRU = M.IDKADBIRU "+                          
			" 		                          ) MAHKAMAH_BORANGC, "+
			" 		                        TBLRUJSUBURUSANSTATUSFAIL E, "+
			" 		                        TBLRUJSUBURUSANSTATUS F, "+
			" 		                        TBLRUJSTATUS G, "+
			" 		                        TBLPPKPERMOHONANSIMATI H, "+
			" 		                        TBLRUJNEGERI I, "+
			" 		                        TBLRUJDAERAH J, "+
			" 		                        USERS K"+
			",tblrujsuburusanstatusfail rsusd "+
			" 		                  WHERE A.ID_FAIL = B.ID_FAIL "+
			" 		                    AND A.NO_FAIL = MAHKAMAH_BORANGC.PETISYEN_NO(+) "+
			" 		                    AND H.ID_PERMOHONAN = B.ID_PERMOHONAN "+
			" 		                    AND H.ID_SIMATI = C.ID_SIMATI "+
			" 		                    AND D.ID_PEMOHON = B.ID_PEMOHON "+
			" 		                    AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS "+
			" 		                    AND G.ID_STATUS = F.ID_STATUS "+
			//" 		                    AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "+
			" AND A.ID_FAIL = E.ID_FAIL "+
			" 		                    AND B.ID_NEGERIMHN = I.ID_NEGERI "+
			" 		                    AND B.ID_DAERAHMHN = J.ID_DAERAH "+
			//" 		                    AND B.ID_MASUK = K.USER_ID(+) "+
			" AND a.id_fail = rsusd.id_fail(+) "+
            " AND rsusd.id_masuk = k.user_id(+) "+
			" 		                    AND E.AKTIF = 1 "+
			" 		                    AND G.ID_STATUS NOT IN (155) "+
			" 		                    AND B.ID_STATUS NOT IN (999)  ";
						
			

			

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND ( UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ chkDataFail.toUpperCase() + "'|| '%' ) ";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ chkNamaPemohon.toUpperCase() + "'|| '%' ";
				}
			}

			// namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ chkNamaSimati.toUpperCase() + "'|| '%'  ";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					setLimit = false;
					sql = sql
							+ "AND H.ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI"
							+ " AND UPPER(NO_HAKMILIK) LIKE '%' ||'"
							+ chkNoHakmilik.toUpperCase()
							+ "'|| '%' AND JENIS_HTA = 'Y') ";
				}
			}

			// jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							setLimit = false;
							if (jenisKp.equals("1")) {
								sql = sql
										+ " AND UPPER(C.NO_KP_BARU) LIKE '%' ||'"
										+ noKp.toUpperCase() + "'|| '%' ";
							} else if (jenisKp.equals("2")) {
								sql = sql
										+ " AND UPPER(C.NO_KP_LAMA) LIKE '%' ||'"
										+ noKp.toUpperCase() + "'|| '%' ";
							} else if (jenisKp.equals("3")) {
								sql = sql
										+ " AND UPPER(C.NO_KP_LAIN) LIKE '%' ||'"
										+ noKp.toUpperCase() + "'|| '%' ";
							}
						}
					}
				}
			}

			// jenisKpPemohon & noKpPemohon
			if (jenisKpPemohon != null) {
				if (!jenisKpPemohon.trim().equals("")) {
					if (noKpPemohon != null) {
						if (!noKpPemohon.trim().equals("")) {
							setLimit = false;
							if (jenisKpPemohon.equals("1")) {
								sql = sql
										+ " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'"
										+ noKpPemohon.toUpperCase()
										+ "'|| '%' ";
							} else if (jenisKpPemohon.equals("2")) {
								sql = sql
										+ " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'"
										+ noKpPemohon.toUpperCase()
										+ "'|| '%' ";
							} else if (jenisKpPemohon.equals("3")) {
								sql = sql
										+ " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'"
										+ noKpPemohon.toUpperCase()
										+ "'|| '%' ";
							}
						}
					}
				}
			}

			// SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhMohon
			if (tarikhMohon != null) {
				if (!tarikhMohon.toString().trim().equals("")) {
					setLimit = false;
					// sql = sql +
					// " AND REPLACE(TO_CHAR(B.TARIKH_MOHON,'dd-MONTH-YY'),' ','') = '"
					// + sdf1.format(sdf.parse(tarikhMohon)).toUpperCase()
					// +"' ";
					sql = sql
							+ " AND REPLACE(TO_CHAR(B.TARIKH_MOHON,'dd/MM/YYYY'),' ','') = '"
							+ tarikhMohon + "' ";
				}
			}

			// no sijil mati
			if (noSijilMati != null) {
				if (!noSijilMati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NO_SIJIL_MATI) LIKE '%' ||'"
							+ chkNoSijilMati.toUpperCase() + "'|| '%'  ";
				}
			}

			// status
			if (status != null) {
				if (!status.trim().equals("") && !status.trim().equals("0")) {
					setLimit = false;
					sql = sql + " AND G.ID_STATUS = " + status + "  ";
				}
			}

			// noLot/noPT
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					setLimit = false;
					sql = sql
							+ "AND H.ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI"
							+ " AND UPPER(NO_PT) LIKE '%' ||'"
							+ chkNoLot.toUpperCase()
							+ "'|| '%' AND JENIS_HTA = 'Y') ";
				}
			}

			// tarikhBicara
			if (tarikhBicara != null && !"".equals(tarikhBicara)) {
				setLimit = false;
				if (!tarikhBicara.toString().trim().equals("")) {
					sql = sql
							+ " AND B.ID_FAIL IN (SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN WHERE ID_KEPUTUSANPERMOHONAN IN(SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKPERBICARAAN WHERE REPLACE(TO_CHAR(TARIKH_BICARA,'dd/MM/YYYY'),' ','') = '"
							+ tarikhBicara + "'))) ";
				}
			}

			// default
			/*
			 * if (setLimit) { //RESERVED BY AZAM
			 * 
			 * sql = sql + " AND ROWNUM <= 50 ";
			 * 
			 * }
			 * 
			 * sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			 */

			sql = sql
					+ ""
					+ " ORDER BY B.TARIKH_MOHON DESC) ";

			if (setLimit) { // RESERVED BY AZAM
				//sql = sql + " WHERE ROWNUM <= 50 ";
			}
			sql = getSQLCarianFail(noFail
					,namaPemohon, jenisKpPemohon, noKpPemohon
					,namaSimati, jenisKp, noKp, noSijilMati
					,tarikhMohon, status
					,noHakmilik, noLot
					, tarikhBicara, userId);
			System.out.println("FAIL TUGASAN:" + sql);
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
				h.put("daftarOleh", rs.getString("USER_NAME") == null ? ""
						: rs.getString("USER_NAME"));
				h.put("noSijilMati", rs.getString("NO_SIJIL_MATI") == null ? ""
						: rs.getString("NO_SIJIL_MATI"));

				String no_fail = rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL");
				
				String PETISYEN_NO = rs.getString("PETISYEN_NO") == null ? "" : rs
						.getString("PETISYEN_NO");
				String display_semak = "";
				if(!PETISYEN_NO.equals(""))
				{
					display_semak = "&nbsp;-&nbsp;<a href=\"javascript:semakBorangC('"
							+ no_fail
							+ "')\"><font color=\"#0000FF\">Semak Borang C</font></a>";
				}
				
				
				/*
				Boolean check = FrmMTBorangC.checkRekodKeputusan(no_fail);
				String display_semak = "";

				if (check == true) {
					display_semak = "&nbsp;-&nbsp;<a href=\"javascript:semakBorangC('"
							+ no_fail
							+ "')\"><font color=\"#0000FF\">Semak Borang C</font></a>";
				} else {
					display_semak = "";
				}
				*/
				
				h.put("test", display_semak);				
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
		//myCache.put(new Element(key, senaraiFail));
		return senaraiFail;
		// }
		
	}

	// public Vector getSenaraiFail() {
	// return senaraiFail;
	// }

	// public void setSenaraiFail(Vector senaraiFail) {
	// this.senaraiFail = senaraiFail;
	// }

	public int totalFail(HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// Open the database connection
			db = new Db();
			sql = "	SELECT COUNT(*) AS TOTAL FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J, USERS K"
					+ " WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = "
					+ userId
					+ " ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql += " )"
					+ " AND A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI AND D.ID_PEMOHON = B.ID_PEMOHON AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS"
					+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND "
					+ "B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND B.ID_MASUK = K.USER_ID AND E.AKTIF = 1 AND G.ID_STATUS NOT IN (155) AND B.ID_STATUS NOT IN (999,56)";
			myLogger.info(" totalFail sql : "+sql);
			// get some data
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			// Close the database connection
			if (db != null)
				db.close();
			if (rs != null)
				rs.close();
		}
		return total;
	}

	public int totalFailPindahMasuk(String userId) throws Exception {
		Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// Open the database connection
			db = new Db();
			sql = "	select count(distinct a.no_fail) "
					+ "as total from tblpfdfail a,tblppkpermohonan b,tblppkbke c "
					+ "where "
					+ "a.id_fail = b.id_fail and b.id_permohonan = c.id_permohonan "
					+ "and c.id_daerah in (SELECT DISTINCT U.ID_DAERAHURUS FROM "
					+ "TBLRUJPEJABATURUSAN U,USERS_INTERNAL UR "
					+ "WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
					+ userId + "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
					
					sql+=" )" + "and b.id_status=56";
			myLogger.debug(sql);
			// get some data
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			// Close the database connection
			if (db != null)
				db.close();
			if (rs != null)
				rs.close();
		}
		return total;
	}
	private String getSQLCarianFail(
			String noFail
			,String namaPemohon,String jenisKpPemohon, String noKpPemohon
			,String namaSimati, String jenisKp, String noKp,String noSijilMati
			,String tarikhMohon, String status
			,String noHakmilik,String noLot
			, String tarikhBicara,String idUser){
		String sql="SELECT distinct "+
				" f.id_fail,f.no_fail,f.tarikh_daftar_fail,f.flag_jenis_fail "+
				//--b.*
				" ,p.id_permohonan,p.id_status,p.tarikh_mohon,p.seksyen "+
				" ,ps.id_permohonansimati "+
				" ,s.id_simati, s.nama_simati, no_sijil_mati "+
				" ,pe.id_pemohon, pe.nama_pemohon, pe.no_kp_baru, pe.no_kp_lama, pe.no_kp_lain "+
				" ,rs.keterangan "+
				" ,u.user_login,UPPER (u.user_name) AS user_name "+
				" ,ud.tarikh_masuk " +
				" ,bc.petisyen_no "+
				" FROM tblppkpermohonan p "+
				" ,tblpfdfail f "+
				" ,tblppkpermohonansimati ps "+
				" ,tblppksimati s "+
				" ,tblppkpemohon pe "+                   
				" ,tblrujstatus rs "+
				" ,users u "+
				//" --,tblrujsuburusanstatusfail rsusf
				//" --
				" ,( "+
				" SELECT  "+
				" m.petisyenno AS petisyen_no "+
				" FROM tblintmtkeputusan k, tblintmtpermohonan m "+
				" WHERE m.flag_rep = '3'  "+
				" AND k.idkadbiru = m.idkadbiru "+
				" ) bc "+
				" ,(  select distinct id_fail,id_masuk,tarikh_masuk from tblrujsuburusanstatusfail rsusd "+
				" where id_suburusanstatus IN (340,430) "+//--Pebdaftaran Sek8,17
				" ) ud "+
				" WHERE f.id_fail = p.id_fail "+
				" and f.no_fail = bc.petisyen_no(+) "+
				" and p.id_permohonan = ps.id_permohonan "+
				" and ps.id_simati = s.id_simati "+
				" and p.id_pemohon = pe.id_pemohon "+
				" and p.id_status = rs.id_status "+   
				" and f.id_fail = ud.id_fail "+  
				" and ud.id_masuk = u.user_id "+ 
				" and p.id_status NOT IN (155,999) "+ 
				" and p.ID_DAERAHMHN in ( "+
				" select id_daerah from vppk_daerahjagaan "+ 
				" where id_user= "+idUser+ 
				" ) "+ 
				//--and f.id_fail = rsusf.id_fail "+ 
				//--and rsusf.AKTIF=1 "+         
				"";
		// noFail
		if (noFail != null) {
			if (!noFail.trim().equals("")) {
				//setLimit = false;
				sql = sql + " AND ( UPPER(F.NO_FAIL) LIKE '%' ||'"
						+ noFail.trim().toUpperCase() + "'|| '%' ) ";
			}
		}
		// namaPemohon
		if (namaPemohon != null) {
			if (!namaPemohon.trim().equals("")) {
				//setLimit = false;
				sql = sql + " AND UPPER(PE.NAMA_PEMOHON) LIKE '%' ||'"
						+ namaPemohon.trim().toUpperCase() + "'|| '%' ";
			}
		}
		// namaSimati
		if (namaSimati != null) {
			if (!namaSimati.trim().equals("")) {
				//setLimit = false;
				sql = sql + " AND UPPER(S.NAMA_SIMATI) LIKE '%' ||'"
						+ namaSimati.trim().toUpperCase() + "'|| '%'  ";
			}
		}
		// noHakmilik
		if (noHakmilik != null) {
			if (!noHakmilik.trim().equals("")) {
				//setLimit = false;
				sql = sql
						+ " AND PS.ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI"
						+ " AND UPPER(NO_HAKMILIK) LIKE '%' ||'"
						+ noHakmilik.trim().toUpperCase()
						+ "'|| '%' AND JENIS_HTA = 'Y') ";
			}
		}
		// jenisKp & noKp
		if (jenisKp != null) {
			if (!jenisKp.trim().equals("")) {
				if (noKp != null) {
					if (!noKp.trim().equals("")) {
						//setLimit = false;
						if (jenisKp.equals("1")) {
							sql = sql
									+ " AND UPPER(S.NO_KP_BARU) LIKE '%' ||'"
									+ noKp.toUpperCase() + "'|| '%' ";
						} else if (jenisKp.equals("2")) {
							sql = sql
									+ " AND UPPER(S.NO_KP_LAMA) LIKE '%' ||'"
									+ noKp.toUpperCase() + "'|| '%' ";
						} else if (jenisKp.equals("3")) {
							sql = sql
									+ " AND UPPER(S.NO_KP_LAIN) LIKE '%' ||'"
									+ noKp.toUpperCase() + "'|| '%' ";
						}
					}
				}
			}
		}

		// jenisKpPemohon & noKpPemohon
		if (jenisKpPemohon != null) {
			if (!jenisKpPemohon.trim().equals("")) {
				if (noKpPemohon != null) {
					if (!noKpPemohon.trim().equals("")) {
						//setLimit = false;
						if (jenisKpPemohon.equals("1")) {
							sql = sql
									+ " AND UPPER(PE.NO_KP_BARU) LIKE '%' ||'"
									+ noKpPemohon.toUpperCase()
									+ "'|| '%' ";
						} else if (jenisKpPemohon.equals("2")) {
							sql = sql
									+ " AND UPPER(PE.NO_KP_LAMA) LIKE '%' ||'"
									+ noKpPemohon.toUpperCase()
									+ "'|| '%' ";
						} else if (jenisKpPemohon.equals("3")) {
							sql = sql
									+ " AND UPPER(PE.NO_KP_LAIN) LIKE '%' ||'"
									+ noKpPemohon.toUpperCase()
									+ "'|| '%' ";
						}
					}
				}
			}
		}
		// tarikhMohon
		if (tarikhMohon != null) {
			if (!tarikhMohon.toString().trim().equals("")) {
					sql = sql
						+ " AND REPLACE(TO_CHAR(P.TARIKH_MOHON,'dd/MM/YYYY'),' ','') = '"
						+ tarikhMohon + "' ";
			}
		}

		// no sijil mati
		if (noSijilMati != null) {
			if (!noSijilMati.trim().equals("")) {
				//setLimit = false;
				sql = sql + " AND UPPER(C.NO_SIJIL_MATI) LIKE '%' ||'"
						+ noSijilMati.trim().toUpperCase() + "'|| '%'  ";
			}
		}
		// status
		if (status != null) {
			if (!status.trim().equals("") && !status.trim().equals("0")) {
				//setLimit = false;
				sql = sql + " AND P.ID_STATUS = " + status + "  ";
			}
		}
		// noLot/noPT
		if (noLot != null) {
			if (!noLot.trim().equals("")) {
				//setLimit = false;
				sql = sql
						+ "AND PS.ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKHTA WHERE ID_PERMOHONANSIMATI = H.ID_PERMOHONANSIMATI"
						+ " AND UPPER(NO_PT) LIKE '%' ||'"
						+ noLot.trim().toUpperCase()
						+ "'|| '%' AND JENIS_HTA = 'Y') ";
			}
		}
		// tarikhBicara
		if (tarikhBicara != null && !"".equals(tarikhBicara)) {
			//setLimit = false;
			if (!tarikhBicara.toString().trim().equals("")) {
				sql = sql
						+ " AND F.ID_FAIL IN (SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN WHERE ID_KEPUTUSANPERMOHONAN IN(SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKPERBICARAAN WHERE REPLACE(TO_CHAR(TARIKH_BICARA,'dd/MM/YYYY'),' ','') = '"
						+ tarikhBicara + "'))) ";
			}
		}

		sql +=" ORDER BY p.tarikh_mohon DESC";

		return sql;
		
	}

	
}
