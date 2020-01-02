package ekptg.model.meps;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;

public class HTP_modeldata extends EkptgCache implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7780701602638098320L;
	static Logger myLog = Logger.getLogger(HTP_modeldata.class);

	public Vector getTotalStatusPermohonanTanahMilikNegeri = null;
	public Vector getTotalStatusPermohonanTanahMilikKementerian = null;
	public Vector getTotalPermohonanPemberimilikanMengikutNegeri = null;
	public Vector getTotalPermohonanPemberimilikanMengikutKementerian = null;
	public Vector getTotalPermohonanPerizabanMengikutNegeri = null;
	public Vector getTotalPermohonanPerizabanKementerian = null;
	public Vector getTotalPembelianMengikutNegeri = null;
	public Vector getTotalPembelianTanahMengikutKementerian = null;
	public Vector getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = null;
	public Vector getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian = null;
	public Vector getTotalPerletakhakanTanahMengikutNegeri = null;
	public Vector getTotalPerletakhakanTanahMengikutKementerian = null;
	public Vector getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = null;
	public Vector getTotalPermohonanYgBelumDaftarPerletakhakanTanah = null;
	public Vector getTotalPajakanTanahMengikutNegeri = null;
	public Vector getTotalPajakanMengikutKementerian = null;
	public Vector getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = null;
	public Vector getTotalRingkasanTanahRizabPersekutuanMengikutKementerian = null;
	public Vector getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = null;
	public Vector getTotalRingkasanTanahMilikPersekutuanMengikutKementerian = null;
	public Vector getTotalRingkasanTanahCerobohMilikPersekutuanMengikutKementerian = null;
	public Vector getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeri = null;
	public Vector getTotalRingkasanTanahCerobohRizabMengikutNegeri = null;
	public Vector getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev = null;
	public Vector getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev = null;
	public Vector getTotalRingkasanTanahBelumBangunPermohonan = null;
	public Vector getTotalRingkasanTanahBelumBangunPenyewaan = null;
	public Vector getTotalRingkasanTanahBolehBangunMilikPersekutuanMengikutKementerian = null;
	public Vector getTotalRingkasanTanahRizabBolehBangunMengikutKementerian = null;
	public Vector getTotalRingkasanTanahRizabCerobohMilikPersekutuanMengikutKementerian = null;
	public Vector getKodKementerian = null;
	public Vector getNamaNegeri = null;
	public Vector getAbbrev = null;
	public Vector getStatusTanahCeroboh = null;
	public Vector getTotalDaerah = null;

	private String SQL;

	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sql) {
		SQL = sql;
	}

	// Laporan Status Permohonan Tanah Milik Mengikut Negeri

	public Vector getListTotalStatusPermohonanTanahMilikMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusPermohonanTanahMilikNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI as x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJJENISTANAH E, TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G,TBLRUJKEMENTERIAN H";
			sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH ";
			sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI=G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			// myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalStatusPermohonanTanahMilikNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusPermohonanTanahMilikNegeri;
	}

	// Laporan Status Permohonan Tanah Milik Mengikut Kementerian

	public Vector getListTotalStatusPermohonanTanahMilikMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusPermohonanTanahMilikKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  H.KOD_KEMENTERIAN,H.NAMA_KEMENTERIAN as x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLRUJDAERAH D,TBLRUJNEGERI N,TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G,TBLRUJKEMENTERIAN H";
			sql += " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
			sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI=G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY H.NAMA_KEMENTERIAN, H.KOD_KEMENTERIAN ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalStatusPermohonanTanahMilikKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusPermohonanTanahMilikKementerian;
	}

	// Laporan Permohonan Pemberimilikan Mengikut Negeri

	public Vector getListTotalPermohonanPemberimilikanMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPemberimilikanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI as x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N,TBLRUJJENISTANAH E , TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G ,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
			sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH  ";
			sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI= H.ID_AGENSI AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN ( SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK) ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalPermohonanPemberimilikanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPemberimilikanMengikutNegeri;
	}

	public Vector getListTotalPermohonanPemberimilikanMengikutNegeriAbbrev(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir )
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPemberimilikanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql =  " SELECT  N.NAMA_NEGERI,N.ABBREV as x, COUNT(*) AS y";					
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B" +
				//",TBLHTPPERMOHONAN C " +
				//" ,TBLRUJDAERAH D" +
				", TBLRUJNEGERI N" +
				//", TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLRUJAGENSI G" +
				",TBLRUJKEMENTERIAN H ";
			sql += " WHERE B.ID_FAIL=A.ID_FAIL " +
				//"AND B.ID_PERMOHONAN = C.ID_PERMOHONAN " +
				" AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND A.ID_NEGERI NOT IN (0)" +
				//" AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH "+
				//" AND F.ID_PERMOHONAN = B.ID_PERMOHONAN " +
				//"AND C.ID_AGENSI=G.ID_AGENSI " +
				" AND A.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";
			// TAHUN 
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
				}
			}					
			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND A.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " GROUP BY N.KOD_NEGERI,N.NAMA_NEGERI,N.ABBREV ";	
			sql += " ORDER BY N.KOD_NEGERI ";
			setSQL(sql);
			System.out.println("Laporan Permohonan Pemberimilikan Mengikut Negeri = "+sql);
			//myLogger.info("LAPORAN TOTAL KEMENTERIAN :: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();				    
				h.put("bil", bil);
				//h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ?"":rs.getString("NAMA_KEMENTERIAN"));
			    h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    h.put("abbrev", Utils.isNull(rs.getString("x")));
			    h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
			    getTotalPermohonanPemberimilikanMengikutNegeri.addElement(h);
			    bil++;
			}      
		
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}
		return getTotalPermohonanPemberimilikanMengikutNegeri;
	}
	//add by sara 
	public Vector getListTotalPermohonanPemberimilikanMengikutNegeriAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir )
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPemberimilikanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT  N.NAMA_NEGERI,N.ABBREV as x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJDAERAH D, TBLRUJNEGERI N,TBLRUJJENISTANAH E , TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G ,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
			sql += " WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH  ";
			sql += " AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI= H.ID_AGENSI AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN ( SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK) ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
//			// NEGERI
//			if (socNegeri != null) {
//				if (!socNegeri.trim().equals("")) {
//					sql = sql + " AND TO_CHAR(N.NAMA_NEGERI) = '" + socNegeri
//							+ "' ";
//				}
//			}
			
			sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";
			
			
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanPemberimilikanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
	} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPemberimilikanMengikutNegeri;
	}


	// Laporan Permohonan Pemberimilikan Mengikut Kementerian
	public Vector getListTotalPermohonanPemberimilikanMengikutKementerian(String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPemberimilikanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// sql =
			// " SELECT I.KOD_KEMENTERIAN,I.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			// sql +=
			// " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C,TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
			// sql +=
			// " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI  ";
			// sql +=
			// " AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = H.ID_AGENSI ";
			// sql +=
			// " AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK)";

			sql = " SELECT DISTINCT A.ID_KEMENTERIAN,A.KOD_KEMENTERIAN "
					+ " ,A.NAMA_KEMENTERIAN X " + " ,(SELECT COUNT(*) "
					+ " FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "
					+ " WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "
					+ " AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "
					+ " AND F.ID_STATUS NOT IN ('999') "
					+ " AND F.ID_URUSAN = 1 ";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " ) AS Y "
					+ " ,(SELECT COUNT(*) "
					+ " 	FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "
					+ " 	WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "
					+
					// " 	--AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "+
					" 	AND F.ID_STATUS NOT IN ('999') "
					+ " 	AND F.ID_URUSAN =10 "
					+ " ) AS BIL_LOT_SELURUH "
					+ " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME "
					+ " WHERE  "
					+ " A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU  "
					+ " AND RKME.STATUS = 'A' ";

			// sql += " GROUP BY I.NAMA_KEMENTERIAN,I.KOD_KEMENTERIAN ";
			sql += " ORDER BY A.NAMA_KEMENTERIAN ";
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalPermohonanPemberimilikanMengikutKementerian
						.addElement(h);
				bil++;

			}

		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPemberimilikanMengikutKementerian;

	}
	
	// Laporan Permohonan Pemberimilikan Mengikut Kementerian
	public Vector getListTotalPermohonanPemberimilikanMengikutKementerian_baru(String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPemberimilikanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// sql =
			// " SELECT I.KOD_KEMENTERIAN,I.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			// sql +=
			// " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C,TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJJENISTANAH E,TBLHTPKEPUTUSANMOHON F,TBLHTPNOTIS5A G,TBLRUJAGENSI H,TBLRUJKEMENTERIAN I";
			// sql +=
			// " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI  ";
			// sql +=
			// " AND C.ID_DAERAH = D.ID_DAERAH AND C.ID_JENISTANAH = E.ID_JENISTANAH AND F.ID_PERMOHONAN = B.ID_PERMOHONAN AND G.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = H.ID_AGENSI ";
			// sql +=
			// " AND H.ID_KEMENTERIAN = I.ID_KEMENTERIAN AND C.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIK)";

			sql = " SELECT DISTINCT A.ID_KEMENTERIAN,A.KOD_KEMENTERIAN "
					+ " ,A.NAMA_KEMENTERIAN as X " + " ,(SELECT COUNT(*) "
					+ " FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "
					+ " WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "
					+ " AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "
					+ " AND F.ID_STATUS NOT IN ('999') "
					+ " AND F.ID_URUSAN = 1 ";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			
			sql += " ) AS Y "
					+ " ,(SELECT COUNT(*) "
					+ " 	FROM TBLRUJKEMENTERIAN C,TBLPFDFAIL F "
					+ " 	WHERE C.ID_KEMENTERIAN = F.ID_KEMENTERIAN  "
					+
					// " 	--AND F.ID_KEMENTERIAN = A.ID_KEMENTERIAN "+
					" 	AND F.ID_STATUS NOT IN ('999') "
					+ " 	AND F.ID_URUSAN =10 "
					+ " ) AS BIL_LOT_SELURUH "
					+ " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME "
					+ " WHERE  "
					+ " A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU  "
					+ " AND RKME.STATUS = 'A' ";

			// sql += " GROUP BY I.NAMA_KEMENTERIAN,I.KOD_KEMENTERIAN ";
			sql += " ORDER BY A.NAMA_KEMENTERIAN ";
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalPermohonanPemberimilikanMengikutKementerian
						.addElement(h);
				bil++;

			}

		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPemberimilikanMengikutKementerian;
		
	}

	// Laporan Permohonan Perizaban Mengikut Negeri
	public Vector getListTotalPermohonanPerizabanMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  E.NAMA_NEGERI as x, COUNT(*) AS y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E";
			sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_NEGERI NOT IN (0) AND C.id_urusan = 10  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY E.KOD_NEGERI, E.NAMA_NEGERI ";
			sql += " ORDER BY E.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL PERIZABAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", rs.getString("x"));

				getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanMengikutNegeri;
	}

	public Vector getListTotalPermohonanPerizabanMengikutNegeriAbbrev(
			String socTahun, String socBulan, String socNegeri,
			String socKementerian) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  E.NAMA_NEGERI,E.ABBREV as x, COUNT(*) AS y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E";
			sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_NEGERI NOT IN (0) AND C.id_urusan = 10  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			if (socNegeri != null) {
				if (!socNegeri.trim().equals("")) {
					sql = sql + " AND (C.ID_NEGERI) = '" + socNegeri + "' ";
				}
			}

			if (socKementerian != null) {
				if (!socKementerian.trim().equals("")) {
					sql = sql + " AND (C.ID_KEMENTERIAN) = '" + socKementerian
							+ "' ";
				}
			}

			sql += " GROUP BY E.KOD_NEGERI, E.NAMA_NEGERI,E.ABBREV ";
			sql += " ORDER BY E.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanMengikutNegeri;
	}
	

	public Vector getListTotalPermohonanPerizabanMengikutNegeriAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			sql = " SELECT  E.NAMA_NEGERI,E.ABBREV as x, COUNT(*) AS y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E";
			sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_NEGERI NOT IN (0) AND C.id_urusan = 10  ";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}
			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			
			sql += " GROUP BY E.KOD_NEGERI, E.NAMA_NEGERI,E.ABBREV ";
			sql += " ORDER BY E.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("Laporan Permohonan Perizaban Mengikut Negeri :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanMengikutNegeri;
	}
	public Vector getJumlahMengikutNegeriDaerahPerizaban(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			sql = " SELECT  I.NAMA_DAERAH,E.ABBREV as x, COUNT(*) AS y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E,TBLRUJDAERAH I";
			sql += " WHERE  C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.id_urusan = 10 AND B.ID_DAERAH = I.ID_DAERAH(+)";
			sql += " AND E.ID_NEGERI = "+idNegeri;
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}
			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			
			sql += " GROUP BY I.NAMA_DAERAH,E.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalPermohonanPerizabanMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanMengikutNegeri;
	}

	// Laporan Permohonan Perizaban Mengikut Kementerian

	public Vector getListTotalPermohonanPerizabanMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT G.KOD_KEMENTERIAN,G.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E, TBLRUJAGENSI F, TBLRUJKEMENTERIAN G";
			sql += " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_URUSAN = 10 ";
			sql += " AND B.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = G.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY G.NAMA_KEMENTERIAN, G.KOD_KEMENTERIAN  ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPermohonanPerizabanKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanKementerian;
	}
	//by sara
	public Vector getListTotalPermohonanPerizabanMengikutKementerian_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanPerizabanKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT G.KOD_KEMENTERIAN,G.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLRUJNEGERI E, TBLRUJAGENSI F, TBLRUJKEMENTERIAN G";
			sql += " WHERE C.ID_FAIL = A.ID_FAIL AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_NEGERI = E.ID_NEGERI AND C.ID_URUSAN = 10 ";
			sql += " AND B.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = G.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}


			sql += " GROUP BY G.NAMA_KEMENTERIAN, G.KOD_KEMENTERIAN  ";

			setSQL(sql);

			myLog.info("TotalPermohonanPerizabanMengikutKementerian :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPermohonanPerizabanKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanPerizabanKementerian;
	}

	// Laporan Status Bayaran Notis 5A Tanah Milik Mengikut Negeri

	public Vector getJumlahMengikutNegeriDaerahBayaranNotis5ATanahMilik(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT I.NAMA_DAERAH AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, A.ID_DAERAH, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM,  TBLRUJDAERAH I, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
			sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND HTPPER.ID_DAERAH = I.ID_DAERAH(+) AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI AND RUJNEG.ID_NEGERI = "+idNegeri;

			sql += " GROUP BY  I.NAMA_DAERAH ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("nama_daerah", Utils.NiceStateName(rs.getString("x")));

				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;
	}
	
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RUJNEG.NAMA_NEGERI AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
			sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_NEGERI NOT IN (0) AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

			sql += " GROUP BY RUJNEG.KOD_NEGERI, RUJNEG.NAMA_NEGERI ";
			sql += " ORDER BY RUJNEG.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN Status Bayaran Notis 5A Tanah Milik :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("nama_negeri", rs.getString("x"));

				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;
	}
	
	
	
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeriAbbrev(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			// TAHUN
			
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			
			sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
			sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

			sql += " GROUP BY RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV ";
			
			System.out.println("cek sql negeri="+sql);
			
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI"));
				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;
	}
	
	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeriAbbrev_baru(
			String socTahun, String socBulan,String jenisLaporan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(jenisLaporan=="negeri"){
			sql = " SELECT RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			// TAHUN
			
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			
			sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
			sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

			sql += " GROUP BY RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV ";
			
			System.out.println("cek sql negeri="+sql);
			setSQL(sql);

			myLog.info("LAPORAN TOTAL NEGERI :: " + sql);
			}else if(jenisLaporan=="kementerian"){
				sql = " SELECT RUJKEM.KOD_KEMENTERIAN as x,RUJKEM.NAMA_KEMENTERIAN AS NAMA_NEGERI, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
				sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
				sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3";
				// TAHUN
				if (socTahun != null) {
					if (!socTahun.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
								+ socTahun + "' ";
					}
				}

				// BULAN
				if (socBulan != null) {
					if (!socBulan.trim().equals("")) {
						sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
								+ socBulan + "' ";
					}
				}
				sql += ") NTS,";
				sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM, ";
				sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
				sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
				sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN";
				sql += " WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
				sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

				sql += " GROUP BY RUJKEM.NAMA_KEMENTERIAN,RUJKEM.KOD_KEMENTERIAN  ";

			
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			}
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI"));
				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;
	}

	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutNegeriAbbrev(
			String socTahun, String socBulan,String jenisLaporan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C ";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT,B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM,";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN";
			sql += " AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

			sql += " GROUP BY RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV ";
			
			System.out.println("cek sql negeri="+sql);
			
		
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI") == null ? "":rs.getString("NAMA_NEGERI"));
				getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutNegeri;
	}

	// Laporan Status Bayaran Notis 5A Tanah Milik Mengikut Kementerian

	public Vector getListTotalStatusBayaranNotis5ATanahMilikMengikutKementerian(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RUJKEM.KOD_KEMENTERIAN,RUJKEM.NAMA_KEMENTERIAN AS X, NVL (SUM (NTS.JUMLAH_BAYARAN), 0) AS Y";
			sql += " FROM (SELECT A.ID_NOTIS5A, A.JUMLAH_BAYARAN, B.ID_PERMOHONAN, C.ID_FAIL,C.NO_FAIL, C.ID_NEGERI, C.ID_KEMENTERIAN";
			sql += " FROM TBLHTPNOTIS5A A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += ") NTS,";
			sql += " (SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, A.NO_LOT, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HM, ";
			sql += " (SELECT A.ID_HAKMILIKURUSAN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HMU,";
			sql += " (SELECT A.ID_HTPPERMOHONAN, A.ID_AGENSI, A.NO_RUJUKAN_KJP,A.NO_RUJUKAN_LAIN, B.ID_PERMOHONAN, C.ID_FAIL FROM TBLHTPPERMOHONAN A, TBLPERMOHONAN B, TBLPFDFAIL C WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = C.ID_FAIL AND C.ID_SEKSYEN = 3) HTPPER,";
			sql += " TBLRUJNEGERI RUJNEG, TBLRUJKEMENTERIAN RUJKEM, TBLRUJAGENSI RUJAGEN";
			sql += " WHERE NTS.ID_PERMOHONAN = HM.ID_PERMOHONAN AND NTS.ID_FAIL = HM.ID_FAIL AND HM.ID_PERMOHONAN = HMU.ID_PERMOHONAN AND HM.ID_FAIL = HMU.ID_FAIL AND HMU.ID_PERMOHONAN = HTPPER.ID_PERMOHONAN";
			sql += " AND HMU.ID_FAIL = HTPPER.ID_FAIL AND NTS.ID_NEGERI = RUJNEG.ID_NEGERI AND NTS.ID_KEMENTERIAN = RUJKEM.ID_KEMENTERIAN AND RUJKEM.ID_KEMENTERIAN = RUJAGEN.ID_KEMENTERIAN AND HTPPER.ID_AGENSI = RUJAGEN.ID_AGENSI";

			sql += " GROUP BY RUJKEM.NAMA_KEMENTERIAN,RUJKEM.KOD_KEMENTERIAN  ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				// h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("jumlah_permohonan", rs.getDouble("y"));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalStatusBayaranNotis5ATanahMilikMengikutKementerian;
	}

	// Laporan Pembelian Tanah Mengikut Negeri.

	public Vector getListTotalPembelianTanahMengikutNegeri(String socTahun,
			String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPembelianMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
			sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalPembelianMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPembelianMengikutNegeri;
	}

	public Vector getListTotalPembelianTanahMengikutNegeriAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPembelianMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
			sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI, N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPembelianMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPembelianMengikutNegeri;
	}
	
	//Laporan Pembelian Tanah Mengikut Negeri_baru by sara
	public Vector getListTotalPembelianTanahMengikutNegeriAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPembelianMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
			sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND A.ID_NEGERI NOT IN(0) AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND A.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY N.KOD_NEGERI, N.NAMA_NEGERI, N.ABBREV ";
			sql += " ORDER BY N.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN Pembelian Tanah :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPembelianMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPembelianMengikutNegeri;
	}

	// Laporan Pembelian Tanah Mengikut Kementerian.

	public Vector getListTotalPembelianTanahMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPembelianTanahMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  F.KOD_KEMENTERIAN,F.NAMA_KEMENTERIAN AS x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
			sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY F.NAMA_KEMENTERIAN, F.KOD_KEMENTERIAN ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPembelianTanahMengikutKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPembelianTanahMengikutKementerian;
	}
	
	// Laporan Pembelian Tanah Mengikut Kementerian_baru by sara.

	public Vector getListTotalPembelianTanahMengikutKementerian_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPembelianTanahMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  F.KOD_KEMENTERIAN,F.NAMA_KEMENTERIAN AS x, COUNT(*) AS y";
			sql += " FROM TBLPFDFAIL A,TBLPERMOHONAN B,TBLHTPPERMOHONAN C,TBLHTPPERJANJIAN D,TBLRUJSUBURUSAN E,TBLRUJKEMENTERIAN F ,TBLRUJNEGERI N,TBLRUJDAERAH G,TBLHTPPERJANJIANPEMBELIAN H, TBLRUJAGENSI I";
			sql += " WHERE A.ID_URUSAN = 2 AND B.ID_FAIL = A.ID_FAIL AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN  AND A.ID_SUBURUSAN = E.ID_SUBURUSAN AND C.ID_AGENSI = I.ID_AGENSI";
			sql += " AND I.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND A.ID_NEGERI = N.ID_NEGERI AND G.ID_DAERAH = C.ID_DAERAH AND D.ID_PERJANJIAN = H.ID_PERJANJIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY F.NAMA_KEMENTERIAN, F.KOD_KEMENTERIAN ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPembelianTanahMengikutKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPembelianTanahMengikutKementerian;
	}

	// Laporan Perletakhakan Tanah Mengikut Negeri.

	public Vector getListTotalPerletakhakanTanahMengikutNegeri(String socTahun,
			String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutNegeri;
	}

	public Vector getListTotalPerletakhakanTanahMengikutNegeriAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutNegeri;
	}

	public Vector getJumlahMengikutNegeriDaerahTotalPerletakhakanTanahMengikutNegeriAbbrev_baru(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  G.NAMA_DAERAH, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";
			sql += " AND A.ID_NEGERI = "+idNegeri;

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY G.NAMA_DAERAH,N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutNegeri;
	}
	public Vector getListTotalPerletakhakanTanahMengikutNegeriAbbrev_baru(String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJMUKIM M,TBLHTPHAKMILIKURUSAN A,TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN E,TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N,TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND G.ID_NEGERI NOT IN (0) AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY N.KOD_NEGERI, N.NAMA_NEGERI,N.ABBREV ";
			sql += " ORDER BY N.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN Perletakhakan Tanah :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPerletakhakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutNegeri;
	}
	// Laporan Perletakhakan Tanah Mengikut Kementerian.

	public Vector getListTotalPerletakhakanTanahMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT E.KOD_KEMENTERIAN,E.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			sql += " FROM TBLRUJMUKIM M, TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D, TBLRUJKEMENTERIAN E, TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N, TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY E.NAMA_KEMENTERIAN, E.KOD_KEMENTERIAN ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPerletakhakanTanahMengikutKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutKementerian;
	}
	
	// Laporan Perletakhakan Tanah Mengikut Kementerian_baru by sara.

	public Vector getListTotalPerletakhakanTanahMengikutKementerian_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPerletakhakanTanahMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT E.KOD_KEMENTERIAN,E.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			sql += " FROM TBLRUJMUKIM M, TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN B,TBLPFDFAIL C,TBLHTPPERMOHONAN D, TBLRUJKEMENTERIAN E, TBLRUJAGENSI F,TBLRUJDAERAH G,TBLRUJNEGERI N, TBLRUJJENISHAKMILIK H";
			sql += " WHERE A.ID_MUKIM = M.ID_MUKIM AND M.ID_DAERAH = G.ID_DAERAH AND G.ID_NEGERI = N.ID_NEGERI AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND D.ID_AGENSI = F.ID_AGENSI AND F.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND B.ID_FAIL = C.ID_FAIL AND A.ID_JENISHAKMILIK = H.ID_JENISHAKMILIK AND C.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(C.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
		    sql = sql + " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY E.NAMA_KEMENTERIAN, E.KOD_KEMENTERIAN ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPerletakhakanTanahMengikutKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPerletakhakanTanahMengikutKementerian;
	}


	// Laporan Permohonan Yang Telah Didaftarkan Perletakhakan Tanah.

	public Vector getListPermohonanYgTelahDidaftarkanPerletakhakanTanah(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN D,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN C,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH I,TBLRUJNEGERI N,TBLRUJMUKIM J";
			sql += " WHERE D.ID_MUKIM = J.ID_MUKIM AND J.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = N.ID_NEGERI AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND D.ID_JENISHAKMILIK=E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND B.ID_PERMOHONAN IN (select B.ID_PERMOHONAN from tblpfdfail A ,TBLPERMOHONAN B,TBLHTPHAKMILIK H where H.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.id_urusan = 5 ) ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah;
	}

	public Vector getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN D,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN C,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH I,TBLRUJNEGERI N,TBLRUJMUKIM J";
			sql += " WHERE D.ID_MUKIM = J.ID_MUKIM AND J.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = N.ID_NEGERI AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND D.ID_JENISHAKMILIK=E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND B.ID_PERMOHONAN IN (select B.ID_PERMOHONAN from tblpfdfail A ,TBLPERMOHONAN B,TBLHTPHAKMILIK H where H.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.id_urusan = 5 ) ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI, N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah;
	}
	//by sara 
	public Vector getListPermohonanYgTelahDidaftarkanPerletakhakanTanahAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI, N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN D,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN C,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH I,TBLRUJNEGERI N,TBLRUJMUKIM J";
			sql += " WHERE D.ID_MUKIM = J.ID_MUKIM AND J.ID_DAERAH = I.ID_DAERAH AND I.ID_NEGERI = N.ID_NEGERI AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND C.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND D.ID_JENISHAKMILIK=E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND B.ID_PERMOHONAN IN (select B.ID_PERMOHONAN from tblpfdfail A ,TBLPERMOHONAN B,TBLHTPHAKMILIK H where H.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.id_urusan = 5 ) ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}


			sql += " GROUP BY N.NAMA_NEGERI, N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah
						.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgTelahDidaftarkanPerletakhakanTanah;
	}

	// Laporan Permohonan Yang Belum Didaftarkan Perletakhakan Tanah.

	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanah(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
			sql += " WHERE C.ID_MUKIM = I.ID_MUKIM AND I.ID_DAERAH = H.ID_DAERAH AND H.ID_NEGERI = N.ID_NEGERI AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;
	}

	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI,N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A,TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
			sql += " WHERE C.ID_MUKIM = I.ID_MUKIM AND I.ID_DAERAH = H.ID_DAERAH AND H.ID_NEGERI = N.ID_NEGERI AND C.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_PERMOHONAN = B.ID_PERMOHONAN AND D.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY N.NAMA_NEGERI,N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;
	}
	
	//by sara
	
	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT  N.NAMA_NEGERI,N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A," +
					" TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
			sql += " WHERE C.ID_NEGERI = N.ID_NEGERI AND C.ID_NEGERI NOT IN (0) AND C.ID_MUKIM = I.ID_MUKIM AND C.ID_DAERAH = H.ID_DAERAH " +
					" AND C.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND D.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND D.ID_AGENSI = G.ID_AGENSI " +
					" AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL " +
					" AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}


			sql += " GROUP BY N.KOD_NEGERI,N.NAMA_NEGERI,N.ABBREV ";
			sql += " ORDER BY N.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN Permohonan Yang Belum Daftar Perletakhakan Tanah :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;
	}

	// Laporan Pajakan Mengikut Negeri.

	
	public Vector getListPermohonanYgBelumDaftarPerletakhakanTanahAbbrevDaerah_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir,String id_negeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPermohonanYgBelumDaftarPerletakhakanTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
	

			sql = " SELECT  H.NAMA_DAERAH,N.ABBREV AS x, COUNT(*) AS y";
			sql += " FROM TBLRUJJENISHAKMILIK E,TBLHTPHAKMILIKURUSAN C,TBLPERMOHONAN B,TBLPFDFAIL A," +
					" TBLHTPPERMOHONAN D,TBLRUJKEMENTERIAN F,TBLRUJAGENSI G,TBLRUJDAERAH H,TBLRUJNEGERI N,TBLRUJMUKIM I";
			sql += " WHERE C.ID_NEGERI = N.ID_NEGERI AND C.ID_MUKIM = I.ID_MUKIM AND C.ID_DAERAH = H.ID_DAERAH " +
					" AND C.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND D.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND D.ID_AGENSI = G.ID_AGENSI " +
					" AND G.ID_KEMENTERIAN = F.ID_KEMENTERIAN AND B.ID_FAIL = A.ID_FAIL " +
					" AND C.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK AND A.ID_URUSAN = 5 AND C.ID_NEGERI = '"+id_negeri+"' ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND B.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}


			sql += " GROUP BY H.NAMA_DAERAH,N.ABBREV ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN BY DAERAH :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalPermohonanYgBelumDaftarPerletakhakanTanah.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPermohonanYgBelumDaftarPerletakhakanTanah;
	}
	
	
	
	public Vector getListTotalPajakanTanahMengikutNegeri(String socTahun,
			String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPajakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RUJNEG.NAMA_NEGERI AS X, COUNT (*) AS Y";
			sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP,TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE, TBLRUJNEGERI RUJNEG, TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3 AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) ";
			sql += " AND f.ID_NEGERI = RUJNEG.ID_NEGERI AND f.ID_NEGERI NOT IN (0) AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " GROUP BY RUJNEG.KOD_NEGERI, RUJNEG.NAMA_NEGERI ";
			sql += " ORDER BY RUJNEG.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("LAPORAN Pajakan Tanah :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", rs.getString("x"));

				getTotalPajakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPajakanTanahMengikutNegeri;
	}

	public Vector getJumlahMengikutNegeriDaerahPajakanTanahMengikutNegeriAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPajakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql  = " SELECT NVL(B.NAMA_DAERAH,'TIADA MAKLUMAT') AS X, COUNT (*) AS Y ";
			sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP,TBLHTPPAJAKAN TPP, TBLRUJDAERAH B, TBLHTPPEMOHON TPPE, TBLRUJNEGERI RUJNEG, TBLRUJAGENSI G, TBLRUJKEMENTERIAN H ";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3 AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) ";
			sql += " AND f.ID_NEGERI = RUJNEG.ID_NEGERI AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND B.ID_DAERAH (+)= PP.ID_DAERAH AND RUJNEG.ID_NEGERI = "+idNegeri;

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY B.NAMA_DAERAH ";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				//h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", rs.getString("x"));

				getTotalPajakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPajakanTanahMengikutNegeri;
	}
	public Vector getListTotalPajakanTanahMengikutNegeriAbbrev(String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPajakanTanahMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV AS X, COUNT (*) AS Y";
			sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP,TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE, TBLRUJNEGERI RUJNEG, TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3 AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) ";
			sql += " AND f.ID_NEGERI = RUJNEG.ID_NEGERI AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " GROUP BY RUJNEG.KOD_NEGERI,RUJNEG.NAMA_NEGERI,RUJNEG.ABBREV ";
			sql += " ORDER BY RUJNEG.KOD_NEGERI ";

			setSQL(sql);

			myLog.info("Laporan Pajakan Tanah :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalPajakanTanahMengikutNegeri.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPajakanTanahMengikutNegeri;
	}

	// Laporan Pajakan Mengikut Kementerian.

	public Vector getListTotalPajakanMengikutKementerian(String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalPajakanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT H.KOD_KEMENTERIAN,H.NAMA_KEMENTERIAN AS X, COUNT (*) AS Y";
			sql += " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLHTPPERMOHONAN PP, TBLHTPPAJAKAN TPP, TBLHTPPEMOHON TPPE,TBLRUJAGENSI G, TBLRUJKEMENTERIAN H";
			sql += " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN AND F.ID_STATUS <> 999 AND F.ID_URUSAN = 3";
			sql += " AND P.ID_PERMOHONAN = TPPE.ID_PERMOHONAN(+) AND PP.ID_AGENSI = G.ID_AGENSI AND G.ID_KEMENTERIAN = H.ID_KEMENTERIAN ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_DAFTAR_FAIL,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " GROUP BY H.NAMA_KEMENTERIAN, H.KOD_KEMENTERIAN ";
    
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalPajakanMengikutKementerian.addElement(h);
				bil++;
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalPajakanMengikutKementerian;
	}

	// Laporan Ringkasan Tanah Rizab Persekutuan Mengikut Negeri.

	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) " + ") AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '";
			sql += " "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) "
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));

				getTotalRingkasanTanahRizabPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabPersekutuanMengikutNegeri;

	}

	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutDaerahAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT  RD.ID_DAERAH AS Y, RD.NAMA_DAERAH AS NAMA_DAERAH,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) " + ") AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '";
			sql += " "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) "
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJDAERAH RD, TBLRUJNEGERI A, TBLHTPHAKMILIK C ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			sql += " AND C.ID_NEGERI = A.ID_NEGERI";
			sql += " AND C.ID_DAERAH = RD.ID_DAERAH";
			sql += " AND C.ID_NEGERI = "+idNegeri;
			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				//h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", Utils.NiceStateName(rs.getString("NAMA_DAERAH")));

				getTotalRingkasanTanahRizabPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabPersekutuanMengikutNegeri;

	}
	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutNegeriAbbrev(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT A.ID_NEGERI, A.NAMA_NEGERI, A.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) " + ") AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' '";
			sql += " "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) "
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);

			myLog.info("LAPORAN Ringkasan Tanah Rizab Persekutuan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahRizabPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabPersekutuanMengikutNegeri;

	}

	// Laporan Ringkasan Tanah Rizab Persekutuan Mengikut Kementerian.

	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutKementerian(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)  ";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " AND RKME.STATUS = C.STATUS AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_WARTA,' ')<>' ' ";
			sql += " " + " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " ))" + " ) AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A'";
			//
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalRingkasanTanahRizabPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabPersekutuanMengikutKementerian;

	}
//by sara
	public Vector getListTotalRingkasanTanahRizabPersekutuanMengikutKementerian_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir
) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)  ";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND RKME.STATUS = C.STATUS AND NVL(D.NO_WARTA,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' ' ";
			sql += " " + " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " ))" + " ) AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A'";
			//
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));

				getTotalRingkasanTanahRizabPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabPersekutuanMengikutKementerian;

	}
	// Laporan Ringkasan Tanah Milik Persekutuan Mengikut Negeri.

	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " )" + ")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ "AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.NiceStateName(rs.getString("x")));
				getTotalRingkasanTanahMilikPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;

	}

	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutNegeriAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI, A.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " )" + ")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ "AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahMilikPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;

	}
//by sara
	
	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutNegeriAbbrev_baru(
		String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir
		) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT A.ID_NEGERI,A.NAMA_NEGERI, A.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " )" + ")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI ";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ "AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND D.ID_NEGERI = A.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJNEGERI C,TBLHTPHAKMILIK D";
			sql += "	WHERE C.ID_NEGERI = D.ID_NEGERI";
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJNEGERI A ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			setSQL(sql);
			myLog.info("LAPORAN Ringkasan Tanah Milik Persekutuan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahMilikPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;

	}
	
	public Vector getListTotalDaerahRingkasanTanahMilikPersekutuanMengikutNegeriAbbrev_baru(String idNegeri,
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir
) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT A.ID_DAERAH,A.NAMA_DAERAH, B.ABBREV AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJDAERAH C,TBLHTPHAKMILIK D,TBLRUJNEGERI E";
			sql += "	WHERE C.ID_DAERAH = D.ID_DAERAH ";
			sql += "	AND D.ID_DAERAH = A.ID_DAERAH ";
			sql += " 	AND C.ID_NEGERI = E.ID_NEGERI ";
			sql += "    AND E.ID_NEGERI = "+idNegeri;
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '  "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " )" + ")) AS Y,(SELECT COUNT(*)";
			sql += "	FROM TBLRUJDAERAH C,TBLHTPHAKMILIK D,TBLRUJNEGERI E";
			sql += "	WHERE C.ID_DAERAH = D.ID_DAERAH ";
			sql += "    AND C.ID_NEGERI = E.ID_NEGERI";
			sql += "    AND E.ID_NEGERI = "+idNegeri;
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ "AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS BIL_LOT_SELURUH,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += "	FROM TBLRUJDAERAH C,TBLHTPHAKMILIK D,TBLRUJNEGERI E";
			sql += "	WHERE C.ID_DAERAH = D.ID_DAERAH";
			sql += "	AND D.ID_DAERAH = A.ID_DAERAH";
			sql += "    AND C.ID_NEGERI = E.ID_NEGERI";
			sql += "    AND E.ID_NEGERI = "+idNegeri;
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))"
					+ ") AS TOTAL_LUAS_NEGERI,(SELECT SUM(D.LUAS_BERSAMAAN)";
			sql += "	FROM TBLRUJDAERAH C,TBLHTPHAKMILIK D,TBLRUJNEGERI E";
			sql += "	WHERE C.ID_DAERAH = D.ID_DAERAH";
			sql += "    AND C.ID_NEGERI = E.ID_NEGERI";
			sql += "    AND E.ID_NEGERI = "+idNegeri;
			sql += "	AND NVL(D.NO_HAKMILIK,' ')<>' ' "
					+ " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ "))" + ") AS TOTAL_LUAS_SELURUH";
			sql += " FROM TBLRUJDAERAH A, TBLRUJNEGERI B ";
			sql += " WHERE A.ID_NEGERI NOT IN (0,17)";
			sql += " AND A.ID_NEGERI = B.ID_NEGERI";
			sql += " AND B.ID_NEGERI = "+idNegeri;
			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.NiceStateName(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalRingkasanTanahMilikPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutNegeri;

	}
	
	// Laporan Ringkasan Tanah Milik Persekutuan Mengikut Kementerian.

	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " ))" + " ) AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " + " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) " + ") AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = 'A'";

			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahMilikPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutKementerian;

	}
	//by sara
	public Vector getListTotalRingkasanTanahMilikPersekutuanMengikutKementerian_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir
) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT distinct(A.ID_KEMENTERIAN),A.KOD_KEMENTERIAN,A.NAMA_KEMENTERIAN AS X,(SELECT COUNT(*)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(D.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			//tarikh 
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND D.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " "
					+ " AND (D.STATUS_SAH IN ("
					+ " 	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ " ))" + " ) AS Y,(SELECT NVL(SUM(D.LUAS_BERSAMAAN),0)";
			sql += " FROM TBLRUJKEMENTERIANMAPPING C,TBLHTPHAKMILIK D";
			sql += " WHERE C.ID_KEMENTERIANLAMA = D.ID_KEMENTERIAN";
			sql += " AND C.ID_KEMENTERIANBARU = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = C.STATUS";
			sql += " AND NVL(D.NO_HAKMILIK,' ')<>' '";
			sql += " " + " AND (D.STATUS_SAH IN ("
					+ "	SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1"
					+ ")) " + ") AS TOTAL_LUAS_NEGERI";
			sql += " FROM TBLRUJKEMENTERIAN A,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU";
			sql += " AND RKME.STATUS = 'A'";

			setSQL(sql);
			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahMilikPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahMilikPersekutuanMengikutKementerian;

	}


	public Vector getListTotalRingkasanTanahCerobobohMilikPersekutuanMengikutKementerian(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_KEMENTERIAN) as Y,RK.KOD_KEMENTERIAN,RK.NAMA_KEMENTERIAN AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN";
			sql += " AND RKME.STATUS='A' ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " group by RK.ID_KEMENTERIAN,RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahCerobohMilikPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohMilikPersekutuanMengikutKementerian;

	}

	public Vector getListTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_NEGERI) as Y,RK.NAMA_NEGERI AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " group by RK.KOD_NEGERI,RK.ID_NEGERI,RK.NAMA_NEGERI";
			sql += " ORDER BY RK.KOD_NEGERI ";

			setSQL(sql);

			
			System.out.println("LAPORAN Ringkasan Pencerobohan Tanah Milik Persekutuan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeri
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeri;

	}

	public Vector getListTotalRingkasanTanahCerobohRizabMengikutNegeri(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohRizabMengikutNegeri = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_NEGERI) as Y,RK.NAMA_NEGERI AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_WARTA,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " group by RK.ID_NEGERI,RK.NAMA_NEGERI";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL NEGERI :: " + sql);
			System.out.println("LAPORAN TOTAL NEGERI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_negeri", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahCerobohRizabMengikutNegeri.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohRizabMengikutNegeri;

	}

	public Vector getListTotalRingkasanTanahCerobohMilikPersekutuanMengikutDaerahAbbrev(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT (RD.ID_DAERAH) AS Y, RD.NAMA_DAERAH AS NAMA_DAERAH";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJDAERAH RD, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";
			sql += " AND A.ID_DAERAH = RD.ID_DAERAH";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_MASUK BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " AND A.ID_NEGERI = "+idNegeri;
			sql += " group by RD.ID_DAERAH, RD.NAMA_DAERAH";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL NEGERI :: " + sql);
			System.out.println("LAPORAN TOTAL NEGERI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				//h.put("abbrev", Utils.isNull(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev;

	}
	public Vector getListTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_NEGERI) as Y,RK.NAMA_NEGERI,RK.ABBREV AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_MASUK BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " group by RK.ID_NEGERI, RK.NAMA_NEGERI, RK.ABBREV";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL NEGERI :: " + sql);
			System.out.println("LAPORAN TOTAL NEGERI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.isNull(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohMilikPersekutuanMengikutNegeriAbbrev;

	}

	public Vector getListTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_NEGERI) as Y,RK.NAMA_NEGERI, RK.ABBREV AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_WARTA,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " group by RK.ID_NEGERI,RK.NAMA_NEGERI, RK.ABBREV";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL NEGERI :: " + sql);
			System.out.println("LAPORAN TOTAL NEGERI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.isNull(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev;

	}
	//by sara
	public Vector getListTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_NEGERI) as Y,RK.NAMA_NEGERI, RK.ABBREV AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJNEGERI RK";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";
			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_MASUK BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_WARTA,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_NEGERI = RK.ID_NEGERI";
			sql += " group by RK.KOD_NEGERI,RK.ID_NEGERI,RK.NAMA_NEGERI, RK.ABBREV";
			sql += " ORDER BY RK.KOD_NEGERI ";

			setSQL(sql);


			System.out.println("LAPORAN Ringkasan Pencerobohan Tanah Rizab Persekutuan :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.isNull(rs.getString("x")));
				h.put("nama_negeri", rs.getString("NAMA_NEGERI"));

				getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev;

	}
	
	public Vector getListTotalDaerahRingkasanTanahCerobohRizabMengikutNegeriAbbrev_baru(String idNegeri,
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_DAERAH) as Y,RK.NAMA_DAERAH ,KR.ABBREV AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJDAERAH RK, TBLRUJNEGERI KR";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";
			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND NVL(A.NO_WARTA,' ')<>' ' ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56    " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}
			//tarikh
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND F.TARIKH_MASUK BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " AND A.ID_DAERAH (+) = RK.ID_DAERAH";
			sql += " AND C.ID_NEGERI = KR.ID_NEGERI";
			sql += " AND KR.ID_NEGERI = "+idNegeri;
			sql += " group by RK.ID_DAERAH,RK.NAMA_DAERAH,KR.ABBREV";

			setSQL(sql);


			System.out.println("LAPORAN TOTAL NEGERI :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				// h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("abbrev", Utils.isNull(rs.getString("x")));
				h.put("nama_daerah", rs.getString("NAMA_DAERAH"));

				getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahCerobohRizabMengikutNegeriAbbrev;

	}

	public Vector getListTotalRingkasanTanahBelumBangunPenyewaan(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahBelumBangunPenyewaan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT KOD_KEMENTERIAN, NAMA_KEMENTERIAN AS X, COUNT(*) AS Y FROM (";
			sql += " SELECT DISTINCT  RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN, PTH.ID_HAKMILIK";
			sql += " FROM TBLPPTHAKMILIK PTH,  TBLPPTPERMOHONAN PP,  TBLPFDFAIL F,  TBLRUJJENISHAKMILIK RJH,";
			sql += " TBLRUJKEMENTERIAN RK, TBLRUJKEMENTERIANMAPPING RKME  ,TBLPPTBORANGK PTK, TBLPPTHAKMILIKBORANGK PTHK ";
			sql += " WHERE PP.ID_PERMOHONAN = PTH.ID_PERMOHONAN  ";
			sql += " AND PP.ID_FAIL = F.ID_FAIL  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND PTH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = F.ID_KEMENTERIAN  ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
			sql += " AND RKME.STATUS='A'  ";
			sql += " AND PTH.ID_HAKMILIK = PTHK.ID_HAKMILIK ";
			sql += " AND PTH.flag_endosan IS NOT NULL  ";
			sql += " AND PTH.NO_HAKMILIK  IN (SELECT NO_HAKMILIK FROM TBLPHPHAKMILIK ";
			sql += " WHERE ID_NEGERI=PTH.ID_NEGERI  ";
			sql += " AND ID_DAERAH=PTH.ID_DAERAH ";
			sql += " AND ID_MUKIM=PTH.ID_MUKIM ";
			sql += " AND NO_HAKMILIK=PTH.NO_HAKMILIK	)) ";
			sql += " GROUP BY KOD_KEMENTERIAN, NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahBelumBangunPenyewaan.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahBelumBangunPenyewaan;

	}
//by sara
	public Vector getListTotalRingkasanTanahBelumBangunPenyewaan_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahBelumBangunPenyewaan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT KOD_KEMENTERIAN, NAMA_KEMENTERIAN AS X, COUNT(*) AS Y FROM (";
			sql += " SELECT DISTINCT  RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN, PTH.ID_HAKMILIK";
			sql += " FROM TBLPPTHAKMILIK PTH,  TBLPPTPERMOHONAN PP,  TBLPFDFAIL F,  TBLRUJJENISHAKMILIK RJH,";
			sql += " TBLRUJKEMENTERIAN RK, TBLRUJKEMENTERIANMAPPING RKME  ,TBLPPTBORANGK PTK, TBLPPTHAKMILIKBORANGK PTHK ";
			sql += " WHERE PP.ID_PERMOHONAN = PTH.ID_PERMOHONAN  ";
			sql += " AND PP.ID_FAIL = F.ID_FAIL  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND PTH.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " AND PTH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = F.ID_KEMENTERIAN  ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
			sql += " AND RKME.STATUS='A'  ";
			sql += " AND PTH.ID_HAKMILIK = PTHK.ID_HAKMILIK ";
			sql += " AND PTH.flag_endosan IS NOT NULL  ";
			sql += " AND PTH.NO_HAKMILIK  IN (SELECT NO_HAKMILIK FROM TBLPHPHAKMILIK ";
			sql += " WHERE ID_NEGERI=PTH.ID_NEGERI  ";
			sql += " AND ID_DAERAH=PTH.ID_DAERAH ";
			sql += " AND ID_MUKIM=PTH.ID_MUKIM ";
			sql += " AND NO_HAKMILIK=PTH.NO_HAKMILIK	)) ";
			sql += " GROUP BY KOD_KEMENTERIAN, NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahBelumBangunPenyewaan.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahBelumBangunPenyewaan;

	}

	
	public Vector getListTotalRingkasanTanahBelumBangunPermohonan(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahBelumBangunPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT KOD_KEMENTERIAN, NAMA_KEMENTERIAN AS X ,COUNT(*) AS Y FROM (";
			sql += " SELECT DISTINCT  RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN, PTH.ID_HAKMILIK";
			sql += " FROM TBLPPTHAKMILIK PTH,  TBLPPTPERMOHONAN PP,  TBLPFDFAIL F,  TBLRUJJENISHAKMILIK RJH,";
			sql += " TBLRUJKEMENTERIAN RK, TBLRUJKEMENTERIANMAPPING RKME  ,TBLPPTBORANGK PTK, TBLPPTHAKMILIKBORANGK PTHK ";
			sql += " WHERE PP.ID_PERMOHONAN = PTH.ID_PERMOHONAN  ";
			sql += " AND PP.ID_FAIL = F.ID_FAIL  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND PTH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = F.ID_KEMENTERIAN  ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
			sql += " AND RKME.STATUS='A'  ";
			sql += " AND PTH.ID_HAKMILIK = PTHK.ID_HAKMILIK ";
			sql += " AND PTH.flag_endosan IS NOT NULL  ";
			sql += " AND PTH.NO_HAKMILIK  IN (SELECT NO_HAKMILIK FROM TBLHTPHAKMILIKURUSAN  ";
			sql += " WHERE ID_NEGERI=PTH.ID_NEGERI  ";
			sql += " AND ID_DAERAH=PTH.ID_DAERAH ";
			sql += " AND ID_MUKIM=PTH.ID_MUKIM ";
			sql += " AND NO_HAKMILIK=PTH.NO_HAKMILIK	)) ";
			sql += " group by KOD_KEMENTERIAN, NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN RINGKASAN BELUM BANGUN :: " + sql);
			System.out.println("LAPORAN RINGKASAN BELUM BANGUN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahBelumBangunPermohonan.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahBelumBangunPermohonan;

	}
	public Vector getListTotalRingkasanTanahBelumBangunPermohonan_baru(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahBelumBangunPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT KOD_KEMENTERIAN, NAMA_KEMENTERIAN AS X ,COUNT(*) AS Y FROM (";
			sql += " SELECT DISTINCT  RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN, PTH.ID_HAKMILIK";
			sql += " FROM TBLPPTHAKMILIK PTH,  TBLPPTPERMOHONAN PP,  TBLPFDFAIL F,  TBLRUJJENISHAKMILIK RJH,";
			sql += " TBLRUJKEMENTERIAN RK, TBLRUJKEMENTERIANMAPPING RKME  ,TBLPPTBORANGK PTK, TBLPPTHAKMILIKBORANGK PTHK ";
			sql += " WHERE PP.ID_PERMOHONAN = PTH.ID_PERMOHONAN  ";
			sql += " AND PP.ID_FAIL = F.ID_FAIL  ";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(PTH.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND PTH.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " AND PTH.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = F.ID_KEMENTERIAN  ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN ";
			sql += " AND RKME.STATUS='A'  ";
			sql += " AND PTH.ID_HAKMILIK = PTHK.ID_HAKMILIK ";
			sql += " AND PTH.flag_endosan IS NOT NULL  ";
			sql += " AND PTH.NO_HAKMILIK  IN (SELECT NO_HAKMILIK FROM TBLHTPHAKMILIKURUSAN  ";
			sql += " WHERE ID_NEGERI=PTH.ID_NEGERI  ";
			sql += " AND ID_DAERAH=PTH.ID_DAERAH ";
			sql += " AND ID_MUKIM=PTH.ID_MUKIM ";
			sql += " AND NO_HAKMILIK=PTH.NO_HAKMILIK	)) ";
			sql += " group by KOD_KEMENTERIAN, NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN RINGKASAN BELUM BANGUN :: " + sql);
			System.out.println("LAPORAN RINGKASAN BELUM BANGUN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahBelumBangunPermohonan.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahBelumBangunPermohonan;

	}

	public Vector getListTotalRingkasanTanahBolehBangunMilikPersekutuanMengikutKementerian(
			String socTahun, String socBulan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahBolehBangunMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_KEMENTERIAN) as Y,RK.KOD_KEMENTERIAN,RK.NAMA_KEMENTERIAN AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN";
			sql += " AND RKME.STATUS='A' ";
			sql += " AND A.ID_HAKMILIK NOT IN (SELECT TPHP.ID_HAKMILIK";
			sql += " FROM   TBLHTPHAKMILIKPERIHAL TPHP ";
			sql += " WHERE   TPHP.LUAS_BERSAMAAN < A.LUAS_BERSAMAAN   )   ";
			sql += " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " group by RK.ID_KEMENTERIAN,RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahBolehBangunMilikPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahBolehBangunMilikPersekutuanMengikutKementerian;

	}

	public Vector getListTotalRingkasanRizabTanahBolehBangunMengikutKementerian(
			String socTahun, String socBulan,String txdTarikhMula,String txdTarikhAkhir) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabBolehBangunMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_KEMENTERIAN) as Y,RK.KOD_KEMENTERIAN,RK.NAMA_KEMENTERIAN AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN ";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_TERIMA,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_TERIMA,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND A.TARIKH_TERIMA BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}
			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN";
			sql += " AND RKME.STATUS='A' ";
			sql += " AND A.ID_HAKMILIK NOT IN (SELECT TPHP.ID_HAKMILIK";
			sql += " FROM   TBLHTPHAKMILIKPERIHAL TPHP ";
			sql += " WHERE   TPHP.LUAS_BERSAMAAN < A.LUAS_BERSAMAAN   )   ";
			sql += " AND NVL(A.NO_WARTA,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " group by RK.ID_KEMENTERIAN,RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahRizabBolehBangunMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabBolehBangunMengikutKementerian;

	}

	public Vector getTotalRingkasanTanahRizabCerobohMilikPersekutuanMengikutKementerian(
			String socTahun, String socBulan,String txdTarikhAkhir,String txdTarikhMula) throws Exception {
		Db db = null;
		String sql = "";
		try {
			getTotalRingkasanTanahRizabCerobohMilikPersekutuanMengikutKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT count(RK.ID_KEMENTERIAN) as Y,RK.KOD_KEMENTERIAN,RK.NAMA_KEMENTERIAN AS X";
			sql += " FROM TBLHTPHAKMILIK A, TBLPERMOHONAN B, TBLPFDFAIL C, TBLRUJURUSAN D,";
			sql += " TBLRUJJENISHAKMILIK E, TBLRUJLOT F, TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME";
			sql += " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN";
			sql += " AND B.ID_FAIL = C.ID_FAIL";

			sql += " AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK ";
			sql += " AND A.ID_LOT = F.ID_LOT ";
			sql += " AND C.ID_URUSAN = D.ID_URUSAN ";
			sql += " AND RKME.ID_KEMENTERIANLAMA = A.ID_KEMENTERIAN ";
			sql += " AND RKME.ID_KEMENTERIANBARU = RK.ID_KEMENTERIAN";
			sql += " AND RKME.STATUS='A' ";
			sql += " AND A.ID_HAKMILIK IN (SELECT TPHA.ID_HAKMILIK";
			sql += " FROM   TBLPFDFAIL F,TBLPERMOHONAN P,TBLPHPHAKMILIKPERMOHONAN HPHP,TBLHTPHAKMILIKAGENSI TPHA ";
			sql += " WHERE P.ID_FAIL=F.ID_FAIL       AND P.ID_PERMOHONAN=HPHP.ID_PERMOHONAN ";
			sql += " AND HPHP.ID_HAKMILIKAGENSI = TPHA.ID_HAKMILIKAGENSI";
			sql += " AND F.ID_URUSAN= 8 AND F.ID_SUBURUSAN = 56  " + "";
			// TAHUN
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'YYYY') = '"
							+ socTahun + "' ";
				}
			}

			// BULAN
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(F.TARIKH_MASUK,'MM') = '"
							+ socBulan + "' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
			
			sql += " AND C.TARIKH_DAFTAR_FAIL BETWEEN "+
			" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
			" 'DD/MM/YYYY')";
			
				}
			}

			sql += " )";
			sql += " AND NVL(A.NO_WARTA,' ') <> ' ' ";
			sql += " AND (A.STATUS_SAH IN (";
			sql += " SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH WHERE AKTIF=1))";
			sql += " group by RK.KOD_KEMENTERIAN, RK.NAMA_KEMENTERIAN";

			setSQL(sql);

			myLog.info("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			System.out.println("LAPORAN TOTAL KEMENTERIAN :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				// h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null
				// ?"":rs.getString("NAMA_KEMENTERIAN"));
				h.put("kod", Utils.isNull(rs.getString("KOD_KEMENTERIAN")));
				h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
				h.put("nama_kementerian", Utils.isNull(rs.getString("x")));
				getTotalRingkasanTanahRizabCerobohMilikPersekutuanMengikutKementerian
						.addElement(h);
				bil++;

			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getTotalRingkasanTanahRizabCerobohMilikPersekutuanMengikutKementerian;

	}
	
	public Vector getKodKementerian_add() throws Exception {
		Db db = null;
		String sql = "";
		try {
			getKodKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT distinct(RK.KOD_KEMENTERIAN) AS KOD, RK.NAMA_KEMENTERIAN AS KEMENTERIAN "
					+ " FROM TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME "
					+ " WHERE "
					+ " RK.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A' "
					+ " ORDER BY RK.KOD_KEMENTERIAN" + "";
			myLog.info(sql);
			// setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("abbrev", Utils.isNull(rs.getString("KOD")));
				h.put("negeri", Utils.isNull(rs.getString("KEMENTERIAN")));
				getKodKementerian.addElement(h);
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getKodKementerian;

	}

	// ELLY ADDED 20.09.2010
	public Vector getKodKementerian() throws Exception {
		Db db = null;
		String sql = "";
		try {
			getKodKementerian = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT distinct(RK.KOD_KEMENTERIAN) AS KOD, RK.NAMA_KEMENTERIAN AS KEMENTERIAN "
					+ " FROM TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME "
					+ " WHERE "
					+ " RK.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A' "
					+ " ORDER BY RK.KOD_KEMENTERIAN" + "";
			myLog.info(sql);
			// setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("kod", Utils.isNull(rs.getString("KOD")));
				h.put("kementerian", Utils.isNull(rs.getString("KEMENTERIAN")));
				getKodKementerian.addElement(h);
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getKodKementerian;

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
					+ " FROM TBLRUJNEGERI RK WHERE RK.ABBREV <>'PEL'"
					+ " ORDER BY RK.ID_NEGERI" + "";
			myLog.info(sql);
			// setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
				h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
				getAbbrev.addElement(h);
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getAbbrev;

	}
	
	public Vector getAbbrevKementerian() throws Exception {
		Db db = null;
		String sql = "";
		try {
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI "
					+ " FROM TBLRUJNEGERI RK WHERE RK.ABBREV <>'PEL'"
					+ " ORDER BY RK.ID_NEGERI" + "";
			myLog.info(sql);
			// setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
				h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
				getAbbrev.addElement(h);
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getAbbrev;

	}
	
	public Vector getAbbrevKementerian(String jenisLaporan) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			getAbbrev = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			if(jenisLaporan.equals("negeri") || jenisLaporan.equals("")  )
			{
			sql = " SELECT distinct(RK.ABBREV) AS ABBREV,RK.ID_NEGERI, RK.NAMA_NEGERI AS NEGERI "
					+ " FROM TBLRUJNEGERI RK WHERE RK.ABBREV <>'PEL'"
					+ " ORDER BY RK.ID_NEGERI" + "";
			
			}
			else if(jenisLaporan.equals("kementerian")){
				sql = " SELECT distinct(RK.KOD_KEMENTERIAN) AS abbrev, RK.NAMA_KEMENTERIAN AS negeri "
					+ " FROM TBLRUJKEMENTERIAN RK,TBLRUJKEMENTERIANMAPPING RKME "
					+ " WHERE "
					+ " RK.ID_KEMENTERIAN = RKME.ID_KEMENTERIANBARU AND RKME.STATUS = 'A' "
					+ " ORDER BY RK.KOD_KEMENTERIAN" + "";
				
			}
			
			myLog.info("check sql" + sql);
			// setSQL(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("abbrev", Utils.isNull(rs.getString("ABBREV")));
				h.put("negeri", Utils.isNull(rs.getString("NEGERI")));
				getAbbrev.addElement(h);
			}
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		} finally {
			if (db != null)
				db.close();
		}
		return getAbbrev;

	}
	
	public Vector getJumlahMengikutNegeriDaerah(String socTahun,String socBulan,String txdTarikhMula,String txdTarikhAkhir, String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try{
			getTotalDaerah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();									
			sql =  "SELECT I.NAMA_DAERAH as x, COUNT(*) AS y " +
					"FROM TBLPFDFAIL A,TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLRUJNEGERI N, TBLRUJKEMENTERIAN H, TBLRUJDAERAH I " +
					"WHERE B.ID_FAIL=A.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN(+) AND A.ID_URUSAN = 1 AND A.ID_NEGERI = N.ID_NEGERI AND A.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND C.ID_DAERAH = I.ID_DAERAH(+) " +
					"AND A.ID_NEGERI = "+idNegeri;
			
			if (socTahun != null) {
				if (!socTahun.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'YYYY') = '" + socTahun +"' ";
				}
			}					
			if (socBulan != null) {
				if (!socBulan.trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_DAFTAR_FAIL,'MM') = '" + socBulan +"' ";
				}
			}
			
			if (txdTarikhMula != null && txdTarikhAkhir != null) {
				if (!txdTarikhMula.trim().equals("") && !txdTarikhAkhir.trim().equals("")) {
					sql += " AND A.TARIKH_DAFTAR_FAIL BETWEEN "+
					" TO_DATE('"+txdTarikhMula+"','DD/MM/YYYY') AND TO_DATE('"+txdTarikhAkhir+"', "+
					" 'DD/MM/YYYY')";
				}
			}
			sql += " GROUP BY I.NAMA_DAERAH ";	
			
			
			setSQL(sql);
			System.out.println("checking select = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;	
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();				    
				h.put("bil", bil);
			    h.put("jumlah_permohonan", Utils.isNull(rs.getString("y")));
			    h.put("namaDaerah",rs.getString("x"));
			    getTotalDaerah.addElement(h);
			    bil++;
			}      
		
		} catch (Exception er) {
			myLog.error(er);
			throw er;
		}finally{
			if(db != null)db.close();
		}	
		return getTotalDaerah;	
	}

	// GENERATE BAR/PIE CHART

	public String generateXML(String nama_laporan) {

		String xml = "<chart caption='"
				+ nama_laporan
				+ "' subcaption='' xAxisName='Negeri' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			myLog.info(" SQL MEP :: "+getSQL());
			ResultSet rs = stat.executeQuery(getSQL());
			
			while (rs.next()) {
				xml = xml + "<set label='"
						+ Utils.NiceStateName(rs.getString("x")) + "' value='"
						+ rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLog.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	
	public String generateXMLDaerah(String nama_laporan) {

		String xml = "<chart caption='"
				+ nama_laporan
				+ "' subcaption='' xAxisName='Daerah' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			while (rs.next()) {
				xml = xml + "<set label='"
						+ rs.getString("NAMA_DAERAH") + "' value='"
						+ rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLog.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}
	public String generateXMLDaerahP(String nama_laporan) {

		String xml = "<chart caption='"
				+ nama_laporan
				+ "' subcaption='' xAxisName='Daerah' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			while (rs.next()) {
				xml = xml + "<set label='"
						+ rs.getString("x") + "' value='"
						+ rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLog.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}

	public String generateXMLKementerian(String nama_laporan) {

		String xml = "<chart caption='"
				+ nama_laporan
				+ "' subcaption='' xAxisName='Kementerian' yAxisName='Jumlah Permohonan' numberPrefix='' showLegend='1'>";
		Db db = null;
		try {
			db = new Db();
			Connection conn = db.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(getSQL());
			while (rs.next()) {
				xml = xml + "<set label='" + rs.getString("KOD_KEMENTERIAN")
						+ "' value='" + rs.getString("y") + "' />";
			}
			xml = xml + "</chart>";
		} catch (Exception er) {
			myLog.error(er);
		} finally {
			if (db != null)
				db.close();
		}

		return xml;
	}

}
