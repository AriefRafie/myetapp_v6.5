package ekptg.model.meps;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class PPK_modeldata extends EkptgCache implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger myLogger = Logger.getLogger(PPK_modeldata.class);

	// VECTOR
	public Vector<Hashtable<String,String>> getKeseluruhanPusaka = null;
	public Vector getTotalPenggunaaneTaPP = null;
	public Vector<Hashtable<String,String>> getTotalS8 = null;
	public Vector<Hashtable<String,String>> getTotalS17 = null;
	public Vector getTotalBelumSelesai = null;
	public Vector getTotalBelumSelesaiUnit = null;
	public Vector<Hashtable<String,String>> getTotalBayaranPerintah = null;
	public Vector getTotalBayaranCukai = null;
	public Vector getTotalBayaranBaitulmal = null;
	public Vector getPermohonanOnline = null;
	public Vector getKeseluruhanPusakaDaerah = null;
	Vector combine = new Vector();
	Vector<Hashtable<String,String>> combineS8 = new Vector<Hashtable<String,String>>();
	Vector<Hashtable<String,String>> combineS17 = new Vector<Hashtable<String,String>>();
	Vector<Hashtable<String,String>> combineS = new Vector<Hashtable<String,String>>();
	Vector<Hashtable<String,String>> combineU = new Vector<Hashtable<String,String>>();
	Vector combineB = new Vector();
	Vector combineD = new Vector();
	Vector combineP = new Vector();

	public Vector<Hashtable<String,String>> listUpk = null;
	public Vector getAbbrev = null;

	private String SQL;

	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}
	
	//Hamidah add
	public List listTableRujukanV3(HttpSession session, String tableRujukan, String id_filter,String USER_ID)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
			}
			
			else if(tableRujukan.equals("TBLRUJNEGERI"))
			{
				sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
				sql += " WHERE ID_NEGERI IS NOT NULL";
				
			}
			
			myLogger.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
			rs = stmt.executeQuery(sql);
			listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				if(tableRujukan.equals("ROLE"))
				{
					h.put("ROLE_DETAILS",rs.getString("ROLE_DETAILS") == null ? "" : rs.getString("ROLE_DETAILS").toUpperCase());
				}
				
				listTableRujukanV3.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukanV3;

	}
	
	@SuppressWarnings("unchecked")
	public List listPejabatJKPTG(HttpSession session, String ID_SEKSYEN, String ID_NEGERI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPejabatJKPTG = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
					" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
					" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					if(!ID_NEGERI.equals(""))
					{
						sql += " AND PEJ.ID_NEGERI = "+ID_NEGERI+" ";
					}
					if(!ID_SEKSYEN.equals(""))
					{
						sql += " AND PEJ.ID_SEKSYEN = "+ID_SEKSYEN+" ";
					}
					sql += " ORDER BY PEJ.KOD_JKPTG ";
			myLogger.info(" V3: SQL listPejabatJKPTG :"+ sql);
			rs = stmt.executeQuery(sql);
			listPejabatJKPTG = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				listPejabatJKPTG.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listPejabatJKPTG;

	}

	public Vector<Hashtable<String,String>> getUpk() throws Exception {
		Db db = null;
		String sql = "";
		try {
			listUpk = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLRUJPEJABATJKPTG.NAMA_PEJABAT, TBLRUJPEJABATJKPTG.ID_NEGERI, COUNT(DISTINCT TBLPFDFAIL.ID_FAIL) AS BILANGAN_PERMOHONAN "
					+ "FROM TBLPFDFAIL, TBLPPKPERMOHONAN,TBLRUJPEJABATURUSAN, TBLRUJPEJABATJKPTG "
					+ "WHERE TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL "
					+ "AND ( TBLPPKPERMOHONAN.ID_STATUS <>'999' OR  TBLPPKPERMOHONAN.ID_STATUS IS NULL) "
					+ "AND TBLPFDFAIL.ID_SEKSYEN = 2 "
					+ "AND TBLPPKPERMOHONAN.ID_DAERAHMHN = TBLRUJPEJABATURUSAN.ID_DAERAHURUS "
					+ "AND TBLRUJPEJABATURUSAN.ID_SEKSYEN = 2 AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = 22 "
					+ "AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = TBLRUJPEJABATJKPTG.ID_PEJABATJKPTG "
					+ "AND TBLRUJPEJABATJKPTG.ID_JENISPEJABAT = 22 AND TBLRUJPEJABATJKPTG.ID_SEKSYEN = 2 "
					+ "GROUP BY TBLRUJPEJABATJKPTG.NAMA_PEJABAT, TBLRUJPEJABATJKPTG.ID_NEGERI ";
			myLogger.debug("List UPK : " +sql);		
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT"));
				h.put("BILANGAN_PERMOHONAN", rs.getString("BILANGAN_PERMOHONAN"));
				listUpk.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}

		return listUpk;

	}

	public Vector<Hashtable<String,String>> getListTotalKeseluruhanFail(String id_tahunDari, String id_tahunHingga, String id_bulanDari,
			String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getKeseluruhanPusaka = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			/*sql = " SELECT C.ID_NEGERI,C.KOD_MAMPU,C.NAMA_NEGERI AS x, count(*) AS y ";
			sql += " FROM ";
			sql += " TBLPFDFAIL A,TBLPPKPERMOHONAN B,TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND ( A.ID_STATUS <>'999' OR A.ID_STATUS IS NULL) " +
				   " AND C.ID_NEGERI NOT IN (0,17) ";
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT')";*/
			
			sql = " SELECT C.ID_NEGERI,C.KOD_MAMPU,C.NAMA_NEGERI AS x, SUM(BILANGAN_PERMOHONAN) AS Y FROM ( ";
			sql += " SELECT TBLRUJPEJABATJKPTG.NAMA_PEJABAT, ";
			sql += " TBLRUJPEJABATJKPTG.ID_NEGERI, ";
			sql += " COUNT (DISTINCT TBLPFDFAIL.ID_FAIL) AS BILANGAN_PERMOHONAN ";
			sql += " FROM TBLPFDFAIL, TBLPPKPERMOHONAN, TBLRUJPEJABATURUSAN, TBLRUJPEJABATJKPTG, TBLRUJNEGERI ";
			sql += " WHERE TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL ";
			sql += " AND (TBLPPKPERMOHONAN.ID_STATUS <> '999' OR TBLPPKPERMOHONAN.ID_STATUS IS NULL) ";
			sql += " AND TBLPFDFAIL.ID_SEKSYEN = 2 ";
			sql += " AND TBLPPKPERMOHONAN.ID_DAERAHMHN = TBLRUJPEJABATURUSAN.ID_DAERAHURUS ";
			sql += " AND TBLPPKPERMOHONAN.ID_NEGERIMHN = TBLRUJNEGERI.ID_NEGERI ";
			sql += " AND TBLRUJPEJABATURUSAN.ID_SEKSYEN = 2 ";
			sql += " AND TBLRUJPEJABATURUSAN.ID_JENISPEJABAT = 22 ";
			sql += " AND TBLRUJPEJABATURUSAN.ID_PEJABATJKPTG = TBLRUJPEJABATJKPTG.ID_PEJABATJKPTG ";
			sql += " AND TBLRUJPEJABATJKPTG.ID_JENISPEJABAT = 22 ";
			sql += " AND TBLRUJPEJABATJKPTG.ID_SEKSYEN = 2 ";
			sql += " AND TBLRUJNEGERI.ID_NEGERI NOT IN (0, 17) ";
					

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			/*sql += " GROUP BY C.ID_NEGERI,C.NAMA_NEGERI,C.KOD_MAMPU " +
					" ORDER BY C.KOD_MAMPU ";*/
			
			sql += " GROUP BY TBLRUJPEJABATJKPTG.NAMA_PEJABAT, TBLRUJPEJABATJKPTG.ID_NEGERI ";
			sql += " ) UTAMA, TBLRUJNEGERI C WHERE UTAMA.ID_NEGERI = C.ID_NEGERI ";
			sql += " GROUP BY C.ID_NEGERI,C.KOD_MAMPU,C.NAMA_NEGERI ";
			
			setSQL(sql);
			
			myLogger.info("LAPORAN PERMOHONAN BY UPK :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				getKeseluruhanPusaka.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getKeseluruhanPusaka;
		
	}

	// Faresh ADDED 10.12.2012
	public Vector getAbbrev() throws Exception {
		Db db = null;
		String sql = "";
		try {
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI "
					+ " FROM TBLRUJNEGERI RK WHERE RK.ABBREV NOT IN('PEL', 'TM')" + " ORDER BY RK.ID_NEGERI" + "";
			// myLog.info(sql);
			setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
				h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
				getAbbrev.addElement(h);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getAbbrev;

	}

	public Vector<Hashtable<String,String>> getListTotalKeseluruhanFailAbbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari,
		String id_bulanHingga, String txdTarikhMula, String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		combine.clear();
		try {
			getKeseluruhanPusaka = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT C.KOD_NEGERI, C.ID_NEGERI,C.NAMA_NEGERI,C.ABBREV AS x, count(*) AS y ";
			sql += " FROM TBLPFDFAIL A, ";
			sql += " TBLPPKPERMOHONAN B, ";
			sql += " TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND B.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY C.KOD_NEGERI, C.NAMA_NEGERI,C.ABBREV,C.ID_NEGERI ";
			sql += " ORDER BY C.KOD_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL KESELURUHAN PPK2 :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			Vector<Hashtable<String,String>> newKeseluruhanPusaka = new Vector<Hashtable<String,String>>();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(x));
				h.put("jumlah_permohonan", "0");
				String abbrev = "";
				abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				String idNegeri = "";
				idNegeri = idNegeri(x);
				h.put("ID_NEGERI", idNegeri);
				String namaNegeri = "";
				namaNegeri = namaNegeri(x);
				h.put("nama_negeri", namaNegeri);
				newKeseluruhanPusaka.addElement(h);
			}

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				getKeseluruhanPusaka.addElement(h);
				bil++;
			}

			for (Object listFull : newKeseluruhanPusaka) {
				Hashtable<String,String> hListFull = (Hashtable) listFull;
				for (Object listSelected : getKeseluruhanPusaka) {
					Hashtable<String,String> hListSelected = (Hashtable) listSelected;
					if (hListFull.get("ID_NEGERI").equals(hListSelected.get("ID_NEGERI"))) {
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						hListFull.put("ID_NEGERI", hListSelected.get("ID_NEGERI"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}

				combine.add(hListFull);

			}

		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}

		return combine;
	}

	private String namaNegeri(int x) {
		String namaNegeri = "";
		switch (x) {
		case 1:
			namaNegeri = "JOHOR";
			break;
		case 2:
			namaNegeri = "KEDAH";
			break;
		case 3:
			namaNegeri = "KELANTAN";
			break;
		case 4:
			namaNegeri = "MELAKA";
			break;
		case 5:
			namaNegeri = "NEGERI SEMBILAN";
			break;
		case 6:
			namaNegeri = "PAHANG";
			break;
		case 7:
			namaNegeri = "PULAU PINANG";
			break;
		case 8:
			namaNegeri = "PERAK";
			break;
		case 9:
			namaNegeri = "PERLIS";
			break;
		case 10:
			namaNegeri = "SELANGOR";
			break;
		case 11:
			namaNegeri = "TERENGGANU";
			break;
		case 12:
			namaNegeri = "SABAH";
			break;
		case 13:
			namaNegeri = "SARAWAK";
			break;
		case 14:
			namaNegeri = "WILAYAH PERSEKUTUAN KUALA LUMPUR";
			break;
		case 15:
			namaNegeri = "WILAYAH PERSEKUTUAN LABUAN";
			break;
		case 16:
			namaNegeri = "WILAYAH PERSEKUTUAN PUTRAJAYA";
			break;
//		case 17:
//			namaNegeri = "TIADA MAKLUMAT";
//			break;
		default:
			break;
		}

		return namaNegeri;
	}

	private String idNegeri(int x) {
		String idNegeri = "";
		switch (x) {
		case 1:
			idNegeri = "1";
			break;
		case 2:
			idNegeri = "2";
			break;
		case 3:
			idNegeri = "3";
			break;
		case 4:
			idNegeri = "4";
			break;
		case 5:
			idNegeri = "5";
			break;
		case 6:
			idNegeri = "6";
			break;
		case 7:
			idNegeri = "7";
			break;
		case 8:
			idNegeri = "8";
			break;
		case 9:
			idNegeri = "9";
			break;
		case 10:
			idNegeri = "10";
			break;
		case 11:
			idNegeri = "11";
			break;
		case 12:
			idNegeri = "12";
			break;
		case 13:
			idNegeri = "13";
			break;
		case 14:
			idNegeri = "14";
			break;
		case 15:
			idNegeri = "15";
			break;
		case 16:
			idNegeri = "16";
			break;
//		case 17:
//			idNegeri = "0";
//			break;
		default:
			break;
		}

		return idNegeri;
	}

	private String abbrev(int x) {
		String abbrev = "";
		switch (x) {
		case 1:
			abbrev = "JHR";
			break;
		case 2:
			abbrev = "KDH";
			break;
		case 3:
			abbrev = "KTN";
			break;
		case 4:
			abbrev = "MLK";
			break;
		case 5:
			abbrev = "NS";
			break;
		case 6:
			abbrev = "PHG";
			break;
		case 7:
			abbrev = "PNG";
			break;
		case 8:
			abbrev = "PRK";
			break;
		case 9:
			abbrev = "PLS";
			break;
		case 10:
			abbrev = "SEL";
			break;
		case 11:
			abbrev = "TRG";
			break;
		case 12:
			abbrev = "SBH";
			break;
		case 13:
			abbrev = "SRW";
			break;
		case 14:
			abbrev = "KUL";
			break;
		case 15:
			abbrev = "LBN";
			break;
		case 16:
			abbrev = "PJY";
			break;
//		case 17:
//			abbrev = "TM";
//			break;
		default:
			break;
		}

		return abbrev;
	}

	public Vector<Hashtable<String,String>> getListTotalSeksyen8(String id_tahunDari, String id_tahunHingga, String id_bulanDari,
			String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalS8 = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT C.ID_NEGERI,C.NAMA_NEGERI AS x, count(*) AS y ";
			sql += " FROM TBLPFDFAIL A, ";
			sql += " TBLPPKPERMOHONAN B, ";
			sql += " TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND B.SEKSYEN = '8' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY C.NAMA_NEGERI,C.ID_NEGERI ";

			setSQL(sql);
			myLogger.info("LAPORAN TOTAL SEKSYEN 8* :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));

				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));

				myLogger.info(" NICE STATE NAME ::" + Utils.NiceStateName(rs.getString("x")));

				getTotalS8.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalS8;
	}

	public Vector<Hashtable<String,String>> getListTotalSeksyen8Abbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		combineS8.clear();
		try {
			getTotalS8 = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT C.KOD_NEGERI,C.ID_NEGERI,C.NAMA_NEGERI,C.ABBREV AS x, count(*) AS y ";
			sql += " FROM TBLPFDFAIL A, ";
			sql += " TBLPPKPERMOHONAN B, ";
			sql += " TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND B.SEKSYEN = '8' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND B.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY C.ABBREV,C.NAMA_NEGERI,C.ID_NEGERI,C.KOD_NEGERI";
			sql += " ORDER BY C.KOD_NEGERI";

			setSQL(sql);
			myLogger.info("LAPORAN TOTAL SEKSYEN 8** :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			Vector<Hashtable<String,String>> newKeseluruhanPusakaS8 = new Vector<Hashtable<String,String>>();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(x));
				h.put("jumlah_permohonan", "0");
				String abbrev = "";
				abbrev = abbrevS8(x);
				h.put("abbrev", abbrev);
				String idNegeri = "";
				idNegeri = idNegeriS8(x);
				h.put("ID_NEGERI", idNegeri);
				String namaNegeri = "";
				namaNegeri = namaNegeriS8(x);
				h.put("nama_negeri", namaNegeri);
				newKeseluruhanPusakaS8.addElement(h);
			}
			
			setSQL(sql);
			//myLogger.info("LAPORAN TOTAL SEKSYEN 8 :: " + sql);
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				getTotalS8.addElement(h);
				bil++;
			}

			for (Object listFull : newKeseluruhanPusakaS8) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalS8) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("ID_NEGERI").equals(hListSelected.get("ID_NEGERI"))) {
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						hListFull.put("ID_NEGERI", hListSelected.get("ID_NEGERI"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}
				combineS8.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineS8;
	}

	/*
	 * while (rs.next()) { h = new Hashtable(); h.put("bil", bil);
	 * h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
	 * h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
	 * h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
	 * h.put("nama_negeri",rs.getString("NAMA_NEGERI")); myLogger.info(
	 * " NICE STATE NAME ::" +Utils.NiceStateName(rs.getString("x")));
	 * 
	 * getTotalS8.addElement(h); bil++; } } finally{ if(db != null)db.close(); }
	 * return getTotalS8; }
	 */

	private String namaNegeriS8(int x) {
		String namaNegeri = "";
		switch (x) {
		case 1:
			namaNegeri = "JOHOR";
			break;
		case 2:
			namaNegeri = "KEDAH";
			break;
		case 3:
			namaNegeri = "KELANTAN";
			break;
		case 4:
			namaNegeri = "MELAKA";
			break;
		case 5:
			namaNegeri = "NEGERI SEMBILAN";
			break;
		case 6:
			namaNegeri = "PAHANG";
			break;
		case 7:
			namaNegeri = "PULAU PINANG";
			break;
		case 8:
			namaNegeri = "PERAK";
			break;
		case 9:
			namaNegeri = "PERLIS";
			break;
		case 10:
			namaNegeri = "SELANGOR";
			break;
		case 11:
			namaNegeri = "TERENGGANU";
			break;
		case 12:
			namaNegeri = "SABAH";
			break;
		case 13:
			namaNegeri = "SARAWAK";
			break;
		case 14:
			namaNegeri = "WILAYAH PERSEKUTUAN KUALA LUMPUR";
			break;
		case 15:
			namaNegeri = "WILAYAH PERSEKUTUAN LABUAN";
			break;
		case 16:
			namaNegeri = "WILAYAH PERSEKUTUAN PUTRAJAYA";
			break;
//		case 17:
//			namaNegeri = "TIADA MAKLUMAT";
//			break;
		default:
			break;
		}

		return namaNegeri;
	}

	private String idNegeriS8(int x) {
		String idNegeri = "";
		switch (x) {
		case 1:
			idNegeri = "1";
			break;
		case 2:
			idNegeri = "2";
			break;
		case 3:
			idNegeri = "3";
			break;
		case 4:
			idNegeri = "4";
			break;
		case 5:
			idNegeri = "5";
			break;
		case 6:
			idNegeri = "6";
			break;
		case 7:
			idNegeri = "7";
			break;
		case 8:
			idNegeri = "8";
			break;
		case 9:
			idNegeri = "9";
			break;
		case 10:
			idNegeri = "10";
			break;
		case 11:
			idNegeri = "11";
			break;
		case 12:
			idNegeri = "12";
			break;
		case 13:
			idNegeri = "13";
			break;
		case 14:
			idNegeri = "14";
			break;
		case 15:
			idNegeri = "15";
			break;
		case 16:
			idNegeri = "16";
			break;
//		case 17:
//			idNegeri = "0";
//			break;
		default:
			break;
		}

		return idNegeri;
	}

	private String abbrevS8(int x) {
		String abbrev = "";
		switch (x) {
		case 1:
			abbrev = "JHR";
			break;
		case 2:
			abbrev = "KDH";
			break;
		case 3:
			abbrev = "KTN";
			break;
		case 4:
			abbrev = "MLK";
			break;
		case 5:
			abbrev = "NS";
			break;
		case 6:
			abbrev = "PHG";
			break;
		case 7:
			abbrev = "PNG";
			break;
		case 8:
			abbrev = "PRK";
			break;
		case 9:
			abbrev = "PLS";
			break;
		case 10:
			abbrev = "SEL";
			break;
		case 11:
			abbrev = "TRG";
			break;
		case 12:
			abbrev = "SBH";
			break;
		case 13:
			abbrev = "SRW";
			break;
		case 14:
			abbrev = "KUL";
			break;
		case 15:
			abbrev = "LBN";
			break;
		case 16:
			abbrev = "PJY";
			break;
//		case 17:
//			abbrev = "TM";
//			break;
		default:
			break;
		}

		return abbrev;
	}

	public Vector<Hashtable<String,String>> getListTotalSeksyen17(String id_tahunDari, String id_tahunHingga, String id_bulanDari,
			String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalS17 = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT C.NAMA_NEGERI AS x, count(*) AS y ";
			sql += " FROM TBLPFDFAIL A, ";
			sql += " TBLPPKPERMOHONAN B, ";
			sql += " TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND B.SEKSYEN = '17' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY C.NAMA_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL SEKSYEN 17 :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
				getTotalS17.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalS17;
	}

	public Vector<Hashtable<String,String>> getListTotalSeksyen17Abbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		combineS17.clear();
		try {
			getTotalS17 = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT C.NAMA_NEGERI, C.ABBREV AS x, count(*) AS y ";
			sql += " FROM TBLPFDFAIL A, ";
			sql += " TBLPPKPERMOHONAN B, ";
			sql += " TBLRUJNEGERI C ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_NEGERIMHN = C.ID_NEGERI ";
			sql += " AND A.ID_SEKSYEN = '2' ";
			sql += " AND B.SEKSYEN = '17' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN AND BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY')  + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}


			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND B.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}
			

			sql += " GROUP BY NAMA_NEGERI,C.ABBREV ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL SEKSYEN 17* :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			Vector<Hashtable<String,String>> newKeseluruhanPusakaS17 = new Vector<Hashtable<String,String>>();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(x));
				h.put("jumlah_permohonan", "0");
				String abbrev = "";
				abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				String namaNegeri = "";
				namaNegeri = namaNegeri(x);
				h.put("nama_negeri", namaNegeri);
				newKeseluruhanPusakaS17.addElement(h);
				
			}

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				// h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				getTotalS17.addElement(h);
				bil++;
			}

			for (Object listFull : newKeseluruhanPusakaS17) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalS17) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("nama_negeri").equals(hListSelected.get("nama_negeri"))) {
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						// hListFull.put("ID_NEGERI",
						// hListSelected.get("ID_NEGERI"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}
				combineS17.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineS17;
		
	}

	public Vector<Hashtable<String,String>> getListTotalKesBelumSelesai(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
		String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		combineS.clear();
		try {
			getTotalBelumSelesai = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT  NAMA_NEGERI, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI, ";
			sql += " NVL (SUM (INITCAP (DECODE (rn, 1, SELESAI))), '0') + NVL (SUM (INITCAP (DECODE (rn, 2, SELESAI))), '0') JUMLAH ";
			sql += " FROM ( ";
			sql += " SELECT NAMA_NEGERI, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_NEGERI ORDER BY ROWNUM) RN ";
			sql += " FROM ( ";
			sql += " SELECT N.NAMA_NEGERI,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_STATUS = '21' ";
			sql += " AND ( F.ID_STATUS <>'999' OR  F.ID_STATUS IS NULL) ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";
			sql += " UNION ";
			sql += " SELECT N.NAMA_NEGERI,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,tblrujnegeri N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND P.ID_STATUS NOT IN ('21') ";
			sql += " AND ( F.ID_STATUS <>'999' OR F.ID_STATUS IS NULL) ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY N.NAMA_NEGERI) ";
			sql += " ) GROUP BY NAMA_NEGERI ";

			setSQL(sql);
			myLogger.info("LAPORAN TOTAL PPK SELESAI & XSELESAI 1  :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;

			Vector<Hashtable<String,String>> newBelumSelesai = new Vector<Hashtable<String,String>>();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(x));
				h.put("jumlah_permohonan_selesai", "0");
				h.put("jumlah_permohonan_xselesai", "0");
				h.put("jumlah", "0");
				//h.put("jumlahJumlah", "0");
				 String abbrev = "";
				 abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				// String idNegeri = "";
				// idNegeri = idNegeri(x);
				// h.put("ID_NEGERI",idNegeri);
				String namaNegeri = "";
				namaNegeri = namaNegeri(x);
				h.put("nama_negeri", namaNegeri);
				newBelumSelesai.addElement(h);
			}

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("jumlah_permohonan_selesai", Utils.isNull(rs.getString("SELESAI")));
				//h.put("jumlah_permohonan_selesai_int", Utils.isNull(rs.getString("SELESAI_INT")));
				h.put("jumlah_permohonan_xselesai", Utils.isNull(rs.getString("XSELESAI")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				h.put("jumlah", Utils.isNull(rs.getString("JUMLAH")));
				getTotalBelumSelesai.addElement(h);
				bil++;
				
			}

			for (Object listFull : newBelumSelesai) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalBelumSelesai) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("nama_negeri").equals(hListSelected.get("nama_negeri"))) {
						hListFull.put("jumlah_permohonan_selesai", hListSelected.get("jumlah_permohonan_selesai"));
						//hListFull.put("jumlah_permohonan_selesai_int", hListSelected.get("jumlah_permohonan_selesai_int"));
						hListFull.put("jumlah_permohonan_xselesai", hListSelected.get("jumlah_permohonan_xselesai"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
						hListFull.put("jumlah", hListSelected.get("jumlah"));
					}
				}
				combineS.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineS;
	}

	/*
	 * while (rs.next()) { h = new Hashtable(); h.put("bil", bil); //
	 * h.put("nama_negeri",
	 * rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
	 * h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
	 * h.put("jumlah_permohonan_selesai",
	 * rs.getString("SELESAI")==null?"":rs.getString("SELESAI"));
	 * h.put("jumlah_permohonan_xselesai",
	 * rs.getString("XSELESAI")==null?"":rs.getString("XSELESAI"));
	 * 
	 * getTotalBelumSelesai.addElement(h); bil++; } } finally{ if(db !=
	 * null)db.close(); } return getTotalBelumSelesai; }
	 */

	public Vector getListTotalKesBelumSelesaiUnit(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalBelumSelesai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT ID_PEJABATJKPTG,NAMA_PEJABAT, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI, ";
			sql += " NVL (SUM (INITCAP (DECODE (rn, 1, SELESAI))), '0') + NVL (SUM (INITCAP (DECODE (rn, 2, SELESAI))), '0') JUMLAH ";
			sql += " FROM ( ";
			sql += " SELECT ID_PEJABATJKPTG,NAMA_PEJABAT, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_PEJABAT ORDER BY ROWNUM) RN ";
			sql += " FROM ( ";
			sql += " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";
			sql += " AND P.ID_STATUS = '21' ";
			sql += " AND ( F.ID_STATUS <>'999' OR  F.ID_STATUS IS NULL) ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ";
			sql += " UNION ";
			sql += " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, tblrujnegeri N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND P.ID_STATUS NOT IN ('21') ";
			sql += " AND ( F.ID_STATUS <>'999' OR F.ID_STATUS IS NULL) ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY   RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ) ) ";
			sql += " GROUP BY ID_PEJABATJKPTG,NAMA_PEJABAT ";
			sql += " ORDER BY ID_PEJABATJKPTG,NAMA_PEJABAT ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL PPK SELESAI & XSELESAI UNIT  :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("nama_negeri",
				// rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				//h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
				h.put("jumlah_permohonan_selesai", rs.getString("SELESAI") == null ? "" : rs.getString("SELESAI"));
				h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI") == null ? "" : rs.getString("XSELESAI"));
				h.put("jumlah", rs.getString("JUMLAH") == null ? "" : rs.getString("JUMLAH"));
				h.put("namaDaerah", rs.getString("NAMA_PEJABAT"));

				getTotalBelumSelesai.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBelumSelesai;
	}
	
	public Vector getListTotalKesBelumSelesaiAbbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalBelumSelesai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  ABBREV, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
			sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI ";
			sql += " FROM ( ";
			sql += " SELECT ABBREV, SELESAI , ROW_NUMBER() OVER ( PARTITION BY ABBREV ORDER BY ROWNUM) RN ";
			sql += " FROM ( ";
			sql += " SELECT N.ABBREV,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_STATUS = '21' ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY N.ABBREV ";
			sql += " UNION ";
			sql += " SELECT N.ABBREV,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,tblrujnegeri N ";
			sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.SEKSYEN IN ('8','17') ";
			sql += " AND P.ID_STATUS NOT IN ('21') ";
			sql += " AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY N.ABBREV) ";
			sql += " )GROUP BY ABBREV ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL PPK SELESAI & XSELESAI 2  :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("nama_negeri",
				// rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("ABBREV")));
				h.put("jumlah_permohonan_selesai", rs.getString("SELESAI") == null ? "" : rs.getString("SELESAI"));
				h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI") == null ? "" : rs.getString("XSELESAI"));
				h.put("jumlah", rs.getString("JUMLAH") == null ? "" : rs.getString("JUMLAH"));

				getTotalBelumSelesai.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBelumSelesai;
	}

	public Vector getTotalBayaranPerintah(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		combineP.clear();
		try {
			getTotalBayaranPerintah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT D.NAMA_NEGERI, D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND C.ID_JENISBAYARAN = '24' ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			//
			// if (txdTarikhMula != null && txdTarikhAkhir != null) {
			// if (!txdTarikhMula.trim().equals("") &&
			// !txdTarikhAkhir.trim().equals("")) {
			//
			// sql += " AND B.TARIKH_MOHON BETWEEN "+
			// " TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND
			// TO_DATE('"+txdTarikhAkhir+"', "+
			// " 'DD/MM/YYYY')";
			//
			// }
			// }
			//
			sql += " GROUP BY D.ID_NEGERI, D.NAMA_NEGERI, D.ABBREV ";
			sql += " ORDER BY D.ID_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN PERINTAH :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			Vector newKeseluruhanPerintah = new Vector();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable();
				h.put("bil", x);
				h.put("jumlah_permohonan", 0.00);
				String abbrev = "";
				abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				String namaNegeri = "";
				namaNegeri = namaNegeriS8(x);
				h.put("nama_negeri", namaNegeri);
				newKeseluruhanPerintah.addElement(h);
			}

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				getTotalBayaranPerintah.addElement(h);
				bil++;
			}

			for (Object listFull : newKeseluruhanPerintah) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalBayaranPerintah) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("nama_negeri").equals(hListSelected.get("nama_negeri"))) {
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}
				combineP.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineP;
	}

	/*
	 * while (rs.next()) { h = new Hashtable(); h.put("bil", bil);
	 * 
	 * // h.put("jumlah_permohonan",
	 * rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
	 * h.put("jumlah_permohonan",rs.getDouble("y")); h.put("nama_negeri",
	 * Utils.NiceStateName(rs.getString("x")));
	 * 
	 * getTotalBayaranPerintah.addElement(h); bil++; } } finally{ if(db !=
	 * null)db.close(); } return getTotalBayaranPerintah; }
	 */

	public Vector getTotalBayaranPerintahAbbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";

		try {
			getTotalBayaranPerintah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT D.NAMA_NEGERI, D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND C.ID_JENISBAYARAN = '24' ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			sql += " GROUP BY D.NAMA_NEGERI, D.ID_NEGERI, D.ABBREV ";
			sql += " ORDER BY D.ID_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN PERINTAH 2:: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);

				// h.put("jumlah_permohonan",
				// rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				getTotalBayaranPerintah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBayaranPerintah;
	}
	
	public Vector getTotalBayaranPerintahUnit(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";

		try {
			getTotalBayaranPerintah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RP.NAMA_PEJABAT AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND B.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RP.ID_SEKSYEN = 2 ";
			sql += " AND C.ID_JENISBAYARAN = '24' ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND RPU.ID_NEGERIURUS = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			sql += " GROUP BY RP.NAMA_PEJABAT ";
			sql += " ORDER BY RP.NAMA_PEJABAT ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN PERINTAH UNIT:: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);

				// h.put("jumlah_permohonan",
				// rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				//h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_pejabat", rs.getString("x"));
				getTotalBayaranPerintah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBayaranPerintah;
	}

	public Vector getTotalBayaranCukai(String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalBayaranCukai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT D.NAMA_NEGERI AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND C.ID_JENISBAYARAN = '26' ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY D.ID_NEGERI, D.NAMA_NEGERI ";
			sql += " ORDER BY D.ID_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN CUKAI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);

				// h.put("jumlah_permohonan",
				// rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("nama_negeri", (rs.getString("x")));

				getTotalBayaranCukai.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBayaranCukai;
	}

	public Vector getTotalBayaranCukaiAbbrev(String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		combineB.clear();
		try {
			getTotalBayaranCukai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT D.NAMA_NEGERI, D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND C.ID_JENISBAYARAN = '26' ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			// if (txdTarikhMula != null && txdTarikhAkhir != null) {
			// if (!txdTarikhMula.trim().equals("") &&
			// !txdTarikhAkhir.trim().equals("")) {
			//
			// sql += " AND B.TARIKH_MOHON BETWEEN "+
			// " TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND
			// TO_DATE('"+txdTarikhAkhir+"', "+
			// " 'DD/MM/YYYY')";
			//
			// }
			// }
			sql += " GROUP BY D.NAMA_NEGERI, D.ID_NEGERI, D.ABBREV ";
			sql += " ORDER BY D.ID_NEGERI ";
			setSQL(sql);
			myLogger.info("LAPORAN JUM BYRAN CUKAI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			Vector newBayaranCukai = new Vector();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable();
				h.put("bil", x);
				h.put("jumlah_permohonan", 0.00);
				String namaNegeri = "";
				namaNegeri = namaNegeriS8(x);
				h.put("nama_negeri", namaNegeri);
				String abbrev = "";
				abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				newBayaranCukai.addElement(h);
			}

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", (rs.getString("x")));
				h.put("nama_negeri", rs.getString("nama_negeri"));
				getTotalBayaranCukai.addElement(h);
				bil++;
			}

			for (Object listFull : newBayaranCukai) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalBayaranCukai) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("nama_negeri").equals(hListSelected.get("nama_negeri"))) {
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}
				combineB.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineB;
	}
	/*
	 * while (rs.next()) { h = new Hashtable(); h.put("bil", bil);
	 * 
	 * // h.put("jumlah_permohonan",
	 * rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
	 * h.put("jumlah_permohonan",rs.getDouble("y")); h.put("abbrev",
	 * Utils.NiceStateName(rs.getString("x")));
	 * h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
	 * 
	 * getTotalBayaranCukai.addElement(h); bil++; } } finally{ if(db !=
	 * null)db.close(); } return getTotalBayaranCukai; }
	 */

	public Vector getTotalBayaranBaitulmalUnit(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalBayaranBaitulmal = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RP.NAMA_PEJABAT AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, TBLPFDFAIL A, TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND B.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RP.ID_SEKSYEN = 2 ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";
			sql += " AND C.ID_JENISBAYARAN = '29' ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND RPU.ID_NEGERIURUS = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY RP.NAMA_PEJABAT ";
			sql += " ORDER BY RP.NAMA_PEJABAT ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN BAITULMAL UNIT :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);

				// h.put("jumlah_permohonan",
				// rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("nama_pejabat", (rs.getString("x")));

				getTotalBayaranBaitulmal.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalBayaranBaitulmal;
	}

	public Vector getPermohonanOnline(String id_tahun, String id_bulan, String id_tahunhingga, String id_bulanhingga)
			throws Exception {
		Db db = null;
		String sql = "";
		System.out.println(id_tahunhingga);
		try {
			getPermohonanOnline = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT  B.NAMA_NEGERI,B.ABBREV, count(*)BIL_PERMOHONAN "
					+ " FROM TBLPPKPERMOHONAN A, TBLRUJNEGERI B, TBLPFDFAIL C" + " WHERE A.ID_NEGERIMHN =B.ID_NEGERI"
					+ " AND A.NO_PERMOHONAN_ONLINE IS NOT NULL" + " AND A.ID_FAIL= C.ID_FAIL";

			if (id_tahun != null) {
				if (!id_tahun.trim().equals("")) {
					if (!id_tahunhingga.trim().equals("")) {
						sql = sql + " AND  TO_CHAR(A.TARIKH_MOHON,'YYYY') >= " + id_tahun + " "
								+ " AND TO_CHAR(A.TARIKH_MOHON,'YYYY') <= " + id_tahunhingga;
						if (!id_bulanhingga.trim().equals("")) {
							sql = sql + " AND  TO_CHAR(A.TARIKH_MOHON,'MM') >= " + id_bulan + " "
									+ " AND TO_CHAR(A.TARIKH_MOHON,'MM') <= " + id_bulanhingga;

						}

					} else {
						sql = sql + " AND  TO_CHAR(A.TARIKH_MOHON,'YYYY') = " + id_tahun + "";
						if (id_bulanhingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(A.TARIKH_MOHON,'MM') = " + id_bulan + "";
						} else {
							sql = sql + " AND  TO_CHAR(A.TARIKH_MOHON,'MM') >= " + id_bulan + " "
									+ " AND TO_CHAR(A.TARIKH_MOHON,'MM') <= " + id_bulanhingga;

						}
					}
				}
			}

			sql += " GROUP BY B.NAMA_NEGERI,B.ABBREV ";
			sql += " ORDER BY B.NAMA_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM PERMOHONAN ONLINE:: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);

				// h.put("jumlah_permohonan",
				// rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
				h.put("jumlah_permohonan", rs.getInt("BIL_PERMOHONAN"));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("NAMA_NEGERI")));
				h.put("abbrev", rs.getString("abbrev"));

				getPermohonanOnline.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getPermohonanOnline;
	}

	public Vector getTotalBayaranBaitulmalAbbrev(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		combineD.clear();
		try {
			getTotalBayaranBaitulmal = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT D.NAMA_NEGERI, D.ABBREV AS x, NVL(SUM (C.JUMLAH_BAYARAN),0) AS y ";
			sql += " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B, ";
			sql += " TBLPPKBAYARAN C,TBLRUJNEGERI D ";
			sql += " WHERE A.ID_FAIL = B.ID_FAIL ";
			sql += " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN ";
			sql += " AND B.ID_NEGERIMHN = D.ID_NEGERI ";
			sql += " AND C.ID_JENISBAYARAN = '29' ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND B.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
			
			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

					sql += " AND B.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
							+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

				}
			}

			sql += " GROUP BY D.NAMA_NEGERI, D.ID_NEGERI, D.ABBREV ";
			sql += " ORDER BY D.ID_NEGERI ";

			setSQL(sql);

			myLogger.info("LAPORAN JUM BYRAN BAITULMAL ABBREV :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			Vector newBayaranBaitulmal = new Vector();
			for (int x = 1; x <= 16; x++) {
				h = new Hashtable();
				h.put("bil", x);
				h.put("jumlah_permohonan", 0.00);
				String namaNegeri = "";
				namaNegeri = namaNegeriS8(x);
				h.put("nama_negeri", namaNegeri);
				String abbrev = "";
				abbrev = abbrev(x);
				h.put("abbrev", abbrev);
				newBayaranBaitulmal.addElement(h);
			}

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", (rs.getString("x")));
				h.put("nama_negeri", rs.getString("nama_negeri"));
				getTotalBayaranBaitulmal.addElement(h);
				bil++;
			}

			for (Object listFull : newBayaranBaitulmal) {
				Hashtable hListFull = (Hashtable) listFull;
				for (Object listSelected : getTotalBayaranBaitulmal) {
					Hashtable hListSelected = (Hashtable) listSelected;
					if (hListFull.get("nama_negeri").equals(hListSelected.get("nama_negeri"))) {
						hListFull.put("abbrev", hListSelected.get("abbrev"));
						hListFull.put("jumlah_permohonan", hListSelected.get("jumlah_permohonan"));
						hListFull.put("nama_negeri", hListSelected.get("nama_negeri"));
					}
				}
				combineD.add(hListFull);
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return combineD;
	}
	/*
	 * while (rs.next()) { h = new Hashtable(); h.put("bil", bil);
	 * 
	 * // h.put("jumlah_permohonan",
	 * rs.getDouble("y")==0?0.00:Utils.format2Decimal(rs.getDouble("y")));
	 * h.put("jumlah_permohonan",rs.getDouble("y")); h.put("abbrev",
	 * Utils.NiceStateName(rs.getString("x")));
	 * h.put("nama_negeri",rs.getString("NAMA_NEGERI"));
	 * 
	 * getTotalBayaranBaitulmal.addElement(h); bil++; } } finally{ if(db !=
	 * null)db.close(); } return getTotalBayaranBaitulmal; }
	 */
	// TODO

	// GENERATE BAR/PIE CHART

	public String generateXML(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combine){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLDaerah(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Daerah' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
//			for(Object vec : combine){
//				Hashtable htbl = (Hashtable) vec;
//				xml = xml + "<set label='" + htbl.get("nama_daerah")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
//			}
			
			
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	//PEJABAT JKPTG
	public String generateXMLUnit(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Unit' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineU){
			Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("nama_daerah")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	
	public String generateXMLS8(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineS8){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLS17(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineS17){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLP(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineP){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLPUnit(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineP){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLB(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineB){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLD(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineD){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
//			while (rs.next()) {
//				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
//			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLDUnit(String nama_laporan) {

		String xml = "<chart caption='" + nama_laporan
				+ "' subcaption='' xAxisName='Unit' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineD){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("abbrev")+ "' value='" + htbl.get("jumlah_permohonan") + "' />";
			}
			
			
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}

	public String generateXML_MSColumn2D(String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String nama_laporan) {

		String xml = "<chart palette='2' caption='" + nama_laporan + "' "
				+ "shownames='1' showvalues='1' decimals='0' numberPrefix='' "
				+ "useRoundEdges='1' legendBorderAlpha='0' showLegend='1'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			sql = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND NVL(P.ID_STATUS,0) = '21' ";
			sql += " AND p.seksyen in ('8','17') ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";

			myLogger.info("SQL STATUS PPK(SELESAI) :: "+sql);

			ResultSet rs = stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='3813F0' showValues='1'>";
			for(Object vec : combineS){
				Hashtable htbl = (Hashtable) vec;
				categories = categories + "<category label='" + htbl.get("abbrev") + "'/>";
				dataset = dataset + "<set value='" + htbl.get("jumlah_permohonan_selesai") + "'/>";
			}
			
//			while (rs.next()) {
//				// xml =xml+"<set label='"+rs.getString("x")+"'
//				// value='"+rs.getString("y")+"' />";
//				categories = categories + "<category label='" + rs.getString("abbrev") + "'/>";
//				dataset = dataset + "<set value='" + rs.getString("SELESAI") + "'/>";
//			}
			categories = categories + "</categories>";
			dataset = dataset + "</dataset>";

			// TAK SELESAI

			sql = " SELECT N.ABBREV,COUNT(DISTINCT P.ID_PERMOHONAN) AS TAKSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N ";
			sql += " WHERE N.ID_NEGERI = F.ID_NEGERI ";
			sql += " AND P.ID_FAIL = F.ID_FAIL ";
			sql += " AND P.ID_STATUS <> '21' ";
			sql += " AND p.seksyen in ('8','17') ";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY N.ABBREV ";
			sql += " ORDER BY 1 ";

			myLogger.info("SQL STATUS PPK(TAKSELESAI) :: "+sql);

			rs = stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='BA1435' showValues='1'>";
			
			for(Object vec : combineS){
				Hashtable htbl = (Hashtable) vec;
				dataset2 = dataset2 + "<set value='" + htbl.get("jumlah_permohonan_xselesai") + "'/>";
			}
			
//			while (rs.next()) {
//				dataset2 = dataset2 + "<set value='" + rs.getString("TAKSELESAI") + "'/>";
//			}
			dataset2 = dataset2 + "</dataset>";

			xml = xml + categories + dataset + dataset2 + "</chart>";

		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	//-----TEST
	
	public String generateXML_MSColumn2DUnit2(String tajukLaporan) {

		String xml = "<chart caption='" + tajukLaporan
				+ "' subcaption='' xAxisName='Unit' yAxisName='Jumlah ' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {			
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			
			for(Object vec : combineS){
				Hashtable htbl = (Hashtable) vec;
				xml = xml + "<set label='" + htbl.get("namaDaerah")+ "' value='" + htbl.get("jumlah_permohonan_selesai") + "' />";
			}
			
			//h.put("jumlah_permohonan_selesai", rs.getString("SELESAI") == null ? "" : rs.getString("SELESAI"));
			//h.put("jumlah_permohonan_xselesai", rs.getString("XSELESAI") == null ? "" : rs.getString("XSELESAI"));
			//h.put("jumlah", rs.getString("JUMLAH") == null ? "" : rs.getString("JUMLAH"));
			//h.put("namaDaerah", rs.getString("NAMA_PEJABAT"));
			
			
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("x") + "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXML_MSColumn2DUnit(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String nama_laporan) {

		String xml = "<chart palette='2' caption='" + nama_laporan + "' "
				+ "shownames='1' showvalues='1' decimals='0' numberPrefix='' "
				+ "useRoundEdges='1' legendBorderAlpha='0' showLegend='1'>";
		Db db = null;
		String sql = "";
		String categories = "";
		String dataset = "";
		String dataset2 = "";
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			sql = " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT(DISTINCT P.ID_PERMOHONAN) AS SELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU ";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL ";
			sql += " AND N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";
			sql += " AND NVL(P.ID_STATUS,0) = '21' ";
			sql += " AND (F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) ";
			sql += " AND p.seksyen in ('8','17') ";
			sql += " AND F.ID_SEKSYEN = '2' ";

			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ";
			sql += " ORDER BY 1 ";

			myLogger.info("SQL STATUS PPK(SELESAI) :: "+sql);

			ResultSet rs = stat.executeQuery(sql);
			categories = "<categories>";
			dataset = "<dataset seriesName='Selesai' color='3813F0' showValues='1'>";
			for(Object vec : combineU){
				Hashtable htbl = (Hashtable) vec;
				categories = categories + "<category label='" + htbl.get("abbrev") + "'/>";
				dataset = dataset + "<set value='" + htbl.get("jumlah_permohonan_selesai") + "'/>";
			}
			
//			while (rs.next()) {
//				// xml =xml+"<set label='"+rs.getString("x")+"'
//				// value='"+rs.getString("y")+"' />";
//				categories = categories + "<category label='" + rs.getString("abbrev") + "'/>";
//				dataset = dataset + "<set value='" + rs.getString("SELESAI") + "'/>";
//			}
			categories = categories + "</categories>";
			dataset = dataset + "</dataset>";

			// TAK SELESAI

			sql = " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT(DISTINCT P.ID_PERMOHONAN) AS XSELESAI ";
			sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F,TBLRUJNEGERI N, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU ";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL ";
			sql += " AND N.ID_NEGERI = P.ID_NEGERIMHN ";
			sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
			sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
			sql += " AND RPU.ID_JENISPEJABAT = 22 ";
			sql += " AND (F.ID_STATUS <> '999' OR F.ID_STATUS IS NULL) ";
			sql += " AND P.ID_STATUS <> '21' ";
			sql += " AND F.ID_SEKSYEN = '2' ";
			sql += " AND p.seksyen in ('8','17') ";
			
			//NEGERI
			if (!ID_NEGERI.equals("")) {
				
				sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
			
			}
			
			sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			sql += " GROUP BY RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ";
			sql += " ORDER BY 1 ";

			myLogger.info("SQL STATUS PPK(TAKSELESAI) :: "+sql);

			rs = stat.executeQuery(sql);
			dataset2 = "<dataset seriesName='Tidak Selesai' color='BA1435' showValues='1'>";
			
			for(Object vec : combineU){
				Hashtable htbl = (Hashtable) vec;
				dataset2 = dataset2 + "<set value='" + htbl.get("jumlah_permohonan_xselesai") + "'/>";
			}
			
//			while (rs.next()) {
//				dataset2 = dataset2 + "<set value='" + rs.getString("TAKSELESAI") + "'/>";
//			}
			dataset2 = dataset2 + "</dataset>";

			xml = xml + categories + dataset + dataset2 + "</chart>";

		} catch (Exception er) {
			myLogger.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}

	public Vector getListLaporanPrestasiDaerah(String id_tahunDari, String id_tahunHingga, String id_bulanDari,
			String id_bulanHingga, String txdTarikhMula, String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getKeseluruhanPusakaDaerah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT D.NAMA_DAERAH AS x, count(*) AS y "
					+ " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B,TBLRUJNEGERI C, TBLRUJDAERAH D "
					+ "WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_NEGERIMHN = C.ID_NEGERI "
					+ "AND A.ID_SEKSYEN = '2' AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) "
					+ "AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') "
					+ "AND B.ID_DAERAHMHN = D.ID_DAERAH " + "AND B.ID_NEGERIMHN = " + idNegeri;

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					sql += " AND B.TARIKH_MOHON BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('"
							+ txdTarikhAkhir + "', 'DD/MM/YYYY')";
				}
			}

			sql += " GROUP BY D.NAMA_DAERAH";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL KESELURUHAN PPK3 :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("namaDaerah", Utils.NiceStateName(rs.getString("x")));
				getKeseluruhanPusakaDaerah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getKeseluruhanPusakaDaerah;
	}
	
	
	public Vector getListLaporanPrestasiUnit(String id_tahunDari, String id_tahunHingga, String id_bulanDari,
			String id_bulanHingga, String txdTarikhMula, String txdTarikhAkhir, String idNegeri, String seksyen, String ID_PEJABATJKPTG) throws Exception {
		Db db = null;
		Vector list = null;
		String sql = "";
		try {
			list = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			sql = "SELECT D.NAMA_DAERAH AS x, count(*) AS y "
					+ " FROM TBLPFDFAIL A,TBLPPKPERMOHONAN B,TBLRUJNEGERI C, TBLRUJDAERAH D "
					+ "WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_NEGERIMHN = C.ID_NEGERI "
					+ "AND A.ID_SEKSYEN = '2' AND ( A.ID_STATUS <>'999' OR  A.ID_STATUS IS NULL) "
					+ "AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') "
					+ "AND B.ID_DAERAHMHN = D.ID_DAERAH " + "AND B.ID_NEGERIMHN = " + idNegeri;
					*/
			
			sql = " SELECT RP.NAMA_PEJABAT AS x, COUNT(F.ID_FAIL) AS y FROM "+
					" TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, TBLPFDFAIL F, TBLPPKPERMOHONAN B "+
					" WHERE RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG  "+
					" AND F.ID_FAIL = B.ID_FAIL AND F.ID_SEKSYEN = 2 " +
					//" AND F.NO_FAIL IS NOT NULL "+
					" AND RPU.ID_JENISPEJABAT = 22 AND B.ID_DAERAHMHN = RPU.ID_DAERAHURUS "+
					" AND RP.ID_SEKSYEN = 2  AND ( B.ID_STATUS <>'999' OR  B.ID_STATUS IS NULL) ";
			if(!seksyen.equals(""))
			{
				sql += " AND B.SEKSYEN = '"+seksyen+"'  "; //-- PARAMETER SEKSYEN (8/17)
			}
			sql += " AND RPU.ID_NEGERIURUS = '"+idNegeri+"' "; //-- PARAMETER NEGERI
			
			
			sql += " AND B.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
			sql += " AND B.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
			sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
			//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

			
			//UNIT/PEJABAT
			if (!ID_PEJABATJKPTG.equals("")) {
				
				sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
			}
			
			sql += ")";
					

			// TAHUN & BULAN
			if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
				if (!id_tahunDari.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
					if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
						if (!id_bulanDari.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
				if (!id_tahunHingga.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
					if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
						if (!id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
						}
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
						}
					}
				}
			} else if (id_tahunDari != null && id_tahunHingga != null) {
				if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
					if ((id_bulanDari == null || id_bulanDari == "")
							&& (id_bulanHingga == null || id_bulanHingga == "")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
					} else if (id_bulanDari != null && id_bulanHingga != null) {
						if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
							sql = sql + " AND trunc(B.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
									+ id_tahunDari + "','MM/YYYY'))";
							sql = sql + " AND trunc(B.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
									+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
						}
					}
				}
			}
			
			if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
				if (!id_bulanDari.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
				if (!id_bulanHingga.trim().equals("")) {
					if (id_tahunDari != null && id_tahunHingga != null) {
						if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						}
					}
				}
			}

			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					sql += " AND B.TARIKH_MOHON BETWEEN TO_DATE('" + txdTarikhMula + "','DD/MM/YYYY') AND TO_DATE('"
							+ txdTarikhAkhir + "', 'DD/MM/YYYY')";
				}
			}

			//sql += " GROUP BY D.NAMA_DAERAH";
			sql += " GROUP BY RP.NAMA_PEJABAT "+
			" ORDER BY RP.NAMA_PEJABAT ";

			setSQL(sql);

			myLogger.info("LAPORAN TOTAL KESELURUHAN getListLaporanPrestasiUnit :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("namaDaerah", rs.getString("x"));
				myLogger.info(">>>getListLaporanPrestasiUnit " + rs.getString("x"));
				list.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLogger.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return list;
	}
	
	//---
	/*public Vector<Hashtable<String,String>> getListTotalKesBelumSelesaiUnit(String ID_NEGERI, String ID_PEJABATJKPTG, String id_tahunDari, String id_tahunHingga, String id_bulanDari, String id_bulanHingga, String txdTarikhMula,
			String txdTarikhAkhir) throws Exception {
			Db db = null;
			String sql = "";
			combineU.clear();
			try {
				getTotalBelumSelesaiUnit = new Vector<Hashtable<String,String>>();
				db = new Db();
				Connection conn = db.getConnection();
				Statement stmt = db.getStatement();
				
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				

				sql = " SELECT ID_PEJABATJKPTG,NAMA_PEJABAT, ";
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 1, SELESAI ))),0) SELESAI, ";
				sql += " NVL (MAX(INITCAP(DECODE ( rn , 2, SELESAI ))),0) XSELESAI, ";
				sql += " NVL (SUM (INITCAP (DECODE (rn, 1, SELESAI))), '0') + NVL (SUM (INITCAP (DECODE (rn, 2, SELESAI))), '0') JUMLAH ";
				sql += " FROM ( ";
				sql += " SELECT ID_PEJABATJKPTG,NAMA_PEJABAT, SELESAI , ROW_NUMBER() OVER ( PARTITION BY NAMA_PEJABAT ORDER BY ROWNUM) RN ";
				sql += " FROM ( ";
				sql += " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT( P.ID_PERMOHONAN) AS SELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, TBLRUJNEGERI N ";
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
				sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
				sql += " AND RPU.ID_JENISPEJABAT = 22 ";
				sql += " AND P.ID_STATUS = '21' ";
				sql += " AND ( F.ID_STATUS <>'999' OR  F.ID_STATUS IS NULL) ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
				//NEGERI
				if (!ID_NEGERI.equals("")) {
					
					sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
				
				}
				
				sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
				sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
				sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
				//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

				
				//UNIT/PEJABAT
				if (!ID_PEJABATJKPTG.equals("")) {
					
					sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
				}
				
				sql += ")";

				// TAHUN & BULAN
				if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
					if (!id_tahunDari.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
						if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
							if (!id_bulanDari.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
							}
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							}
						}
					}
				} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
					if (!id_tahunHingga.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
						if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
							if (!id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
							}
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							}
						}
					}
				} else if (id_tahunDari != null && id_tahunHingga != null) {
					if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
						if ((id_bulanDari == null || id_bulanDari == "")
								&& (id_bulanHingga == null || id_bulanHingga == "")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
										+ id_tahunDari + "','MM/YYYY'))";
								sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
										+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
							}
						}
					}
				}
				
				if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
					if (!id_bulanDari.trim().equals("")) {
						if (id_tahunDari != null && id_tahunHingga != null) {
							if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
							}
						}
					}
				} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
					if (!id_bulanHingga.trim().equals("")) {
						if (id_tahunDari != null && id_tahunHingga != null) {
							if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
							}
						}
					}
				}
				
				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

						sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
								+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

					}
				}

				sql += " GROUP BY RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ";
				sql += " UNION ";
				sql += " SELECT RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT,COUNT( P.ID_PERMOHONAN) AS xSELESAI ";
				sql += " FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLRUJPEJABATJKPTG RP, TBLRUJPEJABATURUSAN RPU, tblrujnegeri N ";
				sql += " WHERE N.ID_NEGERI = P.ID_NEGERIMHN ";
				sql += " AND RP.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG ";
				sql += " AND P.ID_FAIL = F.ID_FAIL ";
				sql += " AND P.ID_DAERAHMHN = RPU.ID_DAERAHURUS ";
				sql += " AND RPU.ID_JENISPEJABAT = 22 ";
				sql += " AND P.SEKSYEN IN ('8','17') ";
				sql += " AND P.ID_STATUS NOT IN ('21') ";
				sql += " AND ( F.ID_STATUS <>'999' OR F.ID_STATUS IS NULL) ";
				sql += " AND F.ID_SEKSYEN = '2' ";
				
				//NEGERI
				if (!ID_NEGERI.equals("")) {
					
					sql = sql + " AND P.ID_NEGERIMHN = '" + ID_NEGERI +"' ";
				
				}
				
				sql += " AND P.ID_DAERAHMHN NOT IN (SELECT ID_DAERAH FROM TBLRUJDAERAH WHERE NAMA_DAERAH = 'TIADA MAKLUMAT') ";
				sql += " AND P.ID_DAERAHMHN IN (SELECT PU.ID_DAERAHURUS FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJPEJABATURUSAN PU ";
				sql += " WHERE PEJ.ID_PEJABATJKPTG = PU.ID_PEJABATJKPTG ";
				//PEJ.ID_PEJABATJKPTG = '"+ID_PEJABATJKPTG+"' AND 

				
				//UNIT/PEJABAT
				if (!ID_PEJABATJKPTG.equals("")) {
					
					sql = sql + " AND PEJ.ID_PEJABATJKPTG = '" + ID_PEJABATJKPTG +"' ";
				}
				
				sql += ")";

				// TAHUN & BULAN
				if (id_tahunDari != null && (id_tahunHingga == null || id_tahunHingga == "")) {
					if (!id_tahunDari.trim().equals("")) {
						sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '" + id_tahunDari + "' ";
						if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
							if (!id_bulanDari.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanDari + "' ";
							}
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							}
						}
					}
				} else if ((id_tahunDari == null || id_tahunDari == "") && id_tahunHingga != null) {
					if (!id_tahunHingga.trim().equals("")) {
						sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') = '" + id_tahunHingga + "' ";
						if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
							if (!id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') = '" + id_bulanHingga + "' ";
							}
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
							}
						}
					}
				} else if (id_tahunDari != null && id_tahunHingga != null) {
					if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
						if ((id_bulanDari == null || id_bulanDari == "")
								&& (id_bulanHingga == null || id_bulanHingga == "")) {
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
							sql = sql + " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
						} else if (id_bulanDari != null && id_bulanHingga != null) {
							if (!id_bulanDari.trim().equals("") && !id_bulanHingga.trim().equals("")) {
								sql = sql + " AND trunc(P.TARIKH_MOHON) >= trunc(to_date('" + id_bulanDari + "/"
										+ id_tahunDari + "','MM/YYYY'))";
								sql = sql + " AND trunc(P.TARIKH_MOHON) <= trunc(to_date('" + id_bulanHingga + "/"
										+ id_tahunHingga + "','MM/YYYY') + INTERVAL '1' MONTH)";
							}
						}
					}
				}
				
				if (id_bulanDari != null && (id_bulanHingga == null || id_bulanHingga == "")) {
					if (!id_bulanDari.trim().equals("")) {
						if (id_tahunDari != null && id_tahunHingga != null) {
							if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') >= '" + id_bulanDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
							}
						}
					}
				} else if ((id_bulanDari == null || id_bulanDari == "") && id_bulanHingga != null) {
					if (!id_bulanHingga.trim().equals("")) {
						if (id_tahunDari != null && id_tahunHingga != null) {
							if (!id_tahunDari.trim().equals("") && !id_tahunHingga.trim().equals("")) {
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'MM') <= '" + id_bulanHingga + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') >= '" + id_tahunDari + "' ";
								sql = sql + " AND TO_CHAR(B.TARIKH_MOHON,'YYYY') <= '" + id_tahunHingga + "' ";
							}
						}
					}
				}

				if (txdTarikhMula != null && txdTarikhAkhir != null) {
					if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {

						sql += " AND P.TARIKH_MOHON BETWEEN " + " TO_DATE('" + txdTarikhMula
								+ "','DD/MM/YYYY') AND TO_DATE('" + txdTarikhAkhir + "', " + " 'DD/MM/YYYY')";

					}
				}

				sql += " GROUP BY   RP.ID_PEJABATJKPTG,RP.NAMA_PEJABAT ) ) ";
				sql += " GROUP BY ID_PEJABATJKPTG,NAMA_PEJABAT ";
				sql += " ORDER BY ID_PEJABATJKPTG,NAMA_PEJABAT ";

				setSQL(sql);
				myLogger.info("getListTotalKesBelumSelesaiUnit  :: " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String,String> h;
				int bil = 1;
				int size = 0;
				
				
				String idPej = "";
			
				if (rs!=null){
					rs.beforeFirst();
					rs.last();
					size = rs.getRow();
				}
				myLogger.info("----------- size ---------- "+ size);
				rs.beforeFirst();
				myLogger.info("resultset :: "+ rs.next()); 
				
				
				Vector<Hashtable<String,String>> newBelumSelesaiUnit = new Vector<Hashtable<String,String>>();
				
				myLogger.info("----------- next size ---------- "+ size);
				
				for (int x = 0; x <= size; x++) {
					
					myLogger.info("-----------  size in belumselesai ---------- "+ size);
					
					while (rs.next()) {
					idPej = rs.getString("ID_PEJABATJKPTG");
					
					h = new Hashtable<String,String>();
					h.put("bil", String.valueOf(x));
					h.put("jumlah_permohonan_selesai", "0");
					h.put("jumlah_permohonan_xselesai", "0");
					h.put("jumlah", "0");
					h.put("jumlahJumlah", "0");
					 String abbrev = "";
					 abbrev = abbrev(x);
					h.put("abbrev", abbrev);
					 String idNegeri = "";
					 idNegeri = idNegeri(x);
					 h.put("ID_NEGERI",idNegeri);
					//String namaNegeri = "";
					String NAMA_PEJABAT = "";
					String ID_PEJABAT = "";
					//NAMA_PEJABAT = namaPejabat(x);
				//	h.put("nama_negeri", namaNegeri);
					h.put("id_pejabat", idPej);
					NAMA_PEJABAT = getNamaPejabat(idPej);
					h.put("namaDaerah", NAMA_PEJABAT);
					
					myLogger.info("nama pejabat dalam for :: "+ h.put("namaDaerah", NAMA_PEJABAT)); 
					myLogger.info("----------- idPej ---------- "+ idPej);
					
					newBelumSelesaiUnit.addElement(h);
					}
					
				}
				rs.beforeFirst();
				myLogger.info("resultset2 :: "+ rs.next()); 
				while (rs.next()) {
					
					myLogger.info("----------- sini ---------- ");
					
					h = new Hashtable<String,String>();
					h.put("bil", String.valueOf(bil));
					h.put("jumlah_permohonan_selesai", Utils.isNull(rs.getString("SELESAI")));
					h.put("jumlah_permohonan_xselesai", Utils.isNull(rs.getString("XSELESAI")));
					h.put("id_pejabat", rs.getString("ID_PEJABATJKPTG"));
					h.put("namaDaerah", rs.getString("NAMA_PEJABAT"));
					h.put("jumlah", Utils.isNull(rs.getString("JUMLAH")));
					getTotalBelumSelesaiUnit.addElement(h);
					
					myLogger.info("nama pejabat :: "+ rs.getString("NAMA_PEJABAT")); 
					bil++;
				}

				for (Object listFull : newBelumSelesaiUnit) {
					Hashtable hListFull = (Hashtable) listFull;
					for (Object listSelected : getTotalBelumSelesaiUnit) {
						Hashtable hListSelected = (Hashtable) listSelected;
						if (hListFull.get("namaDaerah").equals(hListSelected.get("namaDaerah"))) {
							hListFull.put("jumlah_permohonan_selesai", hListSelected.get("jumlah_permohonan_selesai"));
							hListFull.put("jumlah_permohonan_xselesai", hListSelected.get("jumlah_permohonan_xselesai"));
							hListFull.put("id_pejabat", hListSelected.get("id_pejabat"));
							hListFull.put("namaDaerah", hListSelected.get("namaDaerah"));
							//hListFull.put("namaDaerah", rs.getString("NAMA_PEJABAT"));
							hListFull.put("jumlah", hListSelected.get("jumlah"));
							myLogger.info("selected pejabat xxx :: "+ hListFull.put("namaDaerah", hListSelected.get("namaDaerah")));
						}
					}
					combineU.add(hListFull);
				}
			} catch (Exception er) {
				myLogger.error(er);
				throw er;
			} finally {
				if (db != null)
					db.close();
			}
			return combineU;
		}*/
	//---
	
	public String getNamaPejabat(String ID_PEJABATJKPTG) throws Exception {

		String NamaPejabat = "";
		String sql = "";
		Db db = null;
		//Connection conn = db.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {			
			db = new Db();
			stat = db.getStatement();
			
			if (!ID_PEJABATJKPTG.equals("")){
			sql = " SELECT NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = " + ID_PEJABATJKPTG;
		
			///myLogger.info("sql -- " + sql);
			rs = stat.executeQuery(sql);
			
			while (rs.next()){
				NamaPejabat = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
			}
			}
			return NamaPejabat;
		} finally {
			if (db != null)
				db.close();
		}

		
	}
	
	public String getNamaNegeri(HttpSession session, String ID_NEGERI) throws Exception {

		String NamaNegeri = "";
		String sql = "";
		Db db = null;
		//Connection conn = db.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {			
			db = new Db();
			stat = db.getStatement();
			
			if (!ID_NEGERI.equals("")){
			sql = " SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = " + ID_NEGERI;
		
			///myLogger.info("sql -- " + sql);
			rs = stat.executeQuery(sql);
			
			while (rs.next()){
				NamaNegeri = rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI");
			}
			}
			return NamaNegeri;
		} finally {
			if (db != null)
				db.close();
		}

		
	}

}// CLOSE MAIN CLASS
