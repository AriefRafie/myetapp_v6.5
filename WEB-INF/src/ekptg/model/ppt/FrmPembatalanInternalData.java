package ekptg.model.ppt;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.EkptgCache;
import ekptg.helpers.Utils;





public class FrmPembatalanInternalData extends EkptgCache implements
		Serializable {

	static Logger myLogger = Logger.getLogger(FrmPembatalanInternalData.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	public static final String SEQ_TABLE = "TBLRUJSEQFAIL";

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);

	Vector list_kementerian = null;
	public Vector list_kementerian() throws Exception {
		list_kementerian = new Vector();
		Vector key = list_kementerian;
		Element cachedObject = myCache.get(key);
		myLogger.info("CACHED KEMENTERIAN :" + myCache.get(key));
		if (cachedObject != null) {
			myLogger.info("CACHED L1");
			return (Vector) cachedObject.getObjectValue();
		} else {
			myLogger.info("CACHED L2");
			Db db = null;
			list_kementerian.clear();
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();

				sql = "SELECT ID_KEMENTERIAN, KOD_KEMENTERIAN, NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN ORDER BY KOD_KEMENTERIAN ASC ";
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_KEMENTERIAN",
							rs.getString("ID_KEMENTERIAN") == null ? "" : rs
									.getString("ID_KEMENTERIAN"));
					h.put("KOD_KEMENTERIAN", rs
									.getString("KOD_KEMENTERIAN") == null ? ""
									: rs.getString("KOD_KEMENTERIAN")
											.toUpperCase());
					h.put("NAMA_KEMENTERIAN",
							rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
									.getString("NAMA_KEMENTERIAN")
									.toUpperCase());
					list_kementerian.addElement(h);
				}

				myCache.put(new Element(key, list_kementerian));
				return list_kementerian;
			} finally {
				if (db != null)
					db.close();
			}
		}
	}

	Vector list_urusan = null;

	public Vector list_urusan() throws Exception {
		list_urusan = new Vector();
		Db db = null;
		list_urusan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSAN,KOD_SUBURUSAN,NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SEKSYEN = 1";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",
						rs.getString("KOD_SUBURUSAN") == null ? "" : rs
								.getString("KOD_SUBURUSAN").toUpperCase());
				h.put("NAMA_SUBURUSAN",
						rs.getString("NAMA_SUBURUSAN") == null ? "" : rs
								.getString("NAMA_SUBURUSAN").toUpperCase());
				list_urusan.addElement(h);
			}
			return list_urusan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector list_pejabat = null;

	public Vector list_pejabat() throws Exception {
		list_pejabat = new Vector();
		Db db = null;
		list_pejabat.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEJABATJKPTG,NAMA_PEJABAT,"+
					" CASE "+
					" WHEN ID_JENISPEJABAT = '22' THEN 'UPT' "+
					" WHEN ID_JENISPEJABAT = '21' THEN 'SPT '  "+  
					" END AS JENIS_PEJABAT "+
					" FROM TBLRUJPEJABATJKPTG "+
					" WHERE ID_JENISPEJABAT IN ('22','21') "+
					" AND ID_SEKSYEN = 1 ";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("NAMA_PEJABAT",
						rs.getString("NAMA_PEJABAT") == null ? "" : rs
								.getString("NAMA_PEJABAT").toUpperCase());
				h.put("JENIS_PEJABAT",
						rs.getString("JENIS_PEJABAT") == null ? "" : rs
								.getString("JENIS_PEJABAT").toUpperCase());
				list_pejabat.addElement(h);
			}
			return list_pejabat;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_daerahpejabat = null;
	public Vector list_daerahpejabat(String id_pejabat) throws Exception {
		list_daerahpejabat = new Vector();
		Db db = null;
		list_daerahpejabat.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT D.ID_DAERAH,D.KOD_DAERAH,D.NAMA_DAERAH"+					
					" FROM TBLRUJPEJABATJKPTG P,TBLRUJDAERAH D "+
					" WHERE P.ID_SEKSYEN = '1' AND P.ID_NEGERI = D.ID_NEGERI AND  P.ID_JENISPEJABAT IN ('22','21')";					
				if(!id_pejabat.equals("16"))	
				{
			sql +=		" AND P.ID_PEJABATJKPTG = '"+id_pejabat+"'";
				}
			
		    sql+= " AND D.KOD_DAERAH <> '0' ORDER BY P.ID_NEGERI,D.KOD_DAERAH ASC";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? ""
						: rs.getString("ID_DAERAH"));
				h.put("KOD_DAERAH",
						rs.getString("KOD_DAERAH") == null ? "" : rs
								.getString("KOD_DAERAH").toUpperCase());
				h.put("NAMA_DAERAH",
						rs.getString("NAMA_DAERAH") == null ? "" : rs
								.getString("NAMA_DAERAH").toUpperCase());
				list_daerahpejabat.addElement(h);
			}
			return list_daerahpejabat;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector tarikh_akhir_tampal = null;
	public Vector tarikh_akhir_tampal(String id_penarikan) throws Exception {
	tarikh_akhir_tampal = new Vector();
	tarikh_akhir_tampal.clear();
	Db db = null;        			
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
    	sql = "SELECT MAX(TARIKH_TAMPAL) AS TARIKH_TAMPAL FROM TBLPPTNOTISAWAM A, TBLPPTPENARIKANBALIK P WHERE P.ID_PENARIKANBALIK = '"+id_penarikan+"' "+
    		  " AND A.ID_PERMOHONAN = P.ID_PERMOHONAN";
    	myLogger.info("SQL TARIKH TAMPAL :"+sql.toUpperCase());
    	ResultSet rs = stmt.executeQuery(sql);    	
		
		Hashtable h;
		while (rs.next()) {
			h = new Hashtable();
			h.put("TARIKH_TAMPAL", rs.getString("TARIKH_TAMPAL") == null ? "" : Format.format(rs.getDate("TARIKH_TAMPAL")));			
			tarikh_akhir_tampal.addElement(h);
		}
		return tarikh_akhir_tampal;
		//myLogger.info("TARIKH TAMPAL :"+tarikh_akhir_tampal);
		
	}
	finally {
		if (db != null)
			db.close();
	}
	}
	
	Vector list_status = null;

	public Vector list_status() throws Exception {
		list_status = new Vector();
		Db db = null;
		list_status.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_STATUS,KOD_STATUS,KETERANGAN FROM TBLRUJSTATUS " +
					"WHERE ID_SEKSYEN = 1 " +
					" AND ID_STATUS NOT IN (188,189,1610223,1610224,1610225,1610226,1610227,1610228,1610229,1610230,1610231,1610232,1610233,1610234) " +
					" ORDER BY KOD_STATUS ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS"));
				h.put("KOD_STATUS", rs.getString("KOD_STATUS") == null ? ""
						: rs.getString("KOD_STATUS").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				list_status.addElement(h);
			}
			return list_status;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector jenis_hakmilik = null;
	public Vector jenis_hakmilik() throws Exception {
		jenis_hakmilik = new Vector();
		Db db = null;
		jenis_hakmilik.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_JENISHAKMILIK,KOD_JENIS_HAKMILIK,KETERANGAN,STATUS_HAKMILIK,SIMPANAN FROM TBLRUJJENISHAKMILIK ORDER BY KOD_JENIS_HAKMILIK ASC";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
						.getString("ID_JENISHAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK", rs.getString("KOD_JENIS_HAKMILIK") == null ? ""
						: rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("STATUS_HAKMILIK", rs.getString("STATUS_HAKMILIK") == null ? ""
						: rs.getString("STATUS_HAKMILIK").toUpperCase());
				h.put("SIMPANAN", rs.getString("SIMPANAN") == null ? ""
						: rs.getString("SIMPANAN").toUpperCase());
				jenis_hakmilik.addElement(h);
			}
			return jenis_hakmilik;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
/*
	Vector senarai_pembatalan = null;

	public Vector senarai_pembatalan(String jenis_permohonan) throws Exception {
		senarai_pembatalan = new Vector();
		Db db = null;
		senarai_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN,B.ID_PEMBATALAN,P.TARIKH_PERMOHONAN,B.NO_PEMBATALAN ,B.TARIKH_PEMBATALAN,F.NO_FAIL,P.NO_RUJUKAN_UPT,K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN"
					+ " FROM TBLPPTPERMOHONAN P, TBLPPTPEMBATALAN B,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,TBLRUJSTATUS S "
					+ " WHERE P.ID_PERMOHONAN = B.ID_PERMOHONAN(+)"
					+ " AND P.ID_FAIL = F.ID_FAIL(+)"
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)"
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohonan
					+ "'"
					+ " AND P.ID_STATUS = S.ID_STATUS"
					+ " ORDER BY P.TARIKH_KEMASKINI DESC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PEMBATALAN") == null ? "" : rs
								.getString("NO_PEMBATALAN").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "default"
						: rs.getString("NO_FAIL").toUpperCase());
				h.put("TARIKH_PEMBATALAN",
						rs.getString("TARIKH_PEMBATALAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT").toUpperCase());
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				senarai_pembatalan.addElement(h);
			}
			return senarai_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	Vector senarai_pembatalan = null;

	public Vector senarai_pembatalan(String id_permohonan) throws Exception {
		senarai_pembatalan = new Vector();
		Db db = null;
		senarai_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_ONLINE,F.ID_FAIL,B.ID_PEMBATALAN, B.NO_RUJUKAN_SURAT, B.TARIKH_PEMBATALAN," +
				"B.TARIKH_TERIMA_SURAT, " +
					"B.NO_PEMBATALAN,"
					+ " B.JENIS_PEMBATALAN, B.SEBAB_PEMBATALAN, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPEMBATALAN B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND P.ID_PERMOHONAN = '"
					+ id_permohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? "" : rs
						.getString("FLAG_ONLINE"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PEMBATALAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PEMBATALAN") == null ? "" : rs
								.getString("NO_PEMBATALAN").toUpperCase());
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PEMBATALAN") == null ? "" : rs
								.getString("JENIS_PEMBATALAN")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA_SURAT") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA_SURAT")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN")
								);
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));

				senarai_pembatalan.addElement(h);
			}
			return senarai_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector senarai_pembatalan_carian = null;

	public Vector senarai_pembatalan_carian(String negeriuser,String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
		senarai_pembatalan_carian = new Vector();
		Db db = null;
		senarai_pembatalan_carian.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTG,"
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND (SELECT COUNT(*) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = P.ID_PERMOHONAN ) = '0'"
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					
					+ " AND F.ID_SUBURUSAN = '52' "
				/*	+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "*/
				/*	+ " AND NVL(S.ID_STATUS,0) IN ('5','11','16','20','22','113','127','128','132',"
					+ " '133','134','148','147','149','181','182')"*/
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
					
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			   // if(!role.equals("(PPT)Pengarah"))
			
			if(!negeriuser.equals("16") && !negeriuser.isEmpty()){
    			if(negeriuser.equals("14")){
    				sql += "AND f.id_negeri in (14,15,16) ";
    			}else{
    				sql += "AND f.id_negeri ='"+negeriuser+"'";
    			}		
    		}
			    

			// kena filter by status (sudah diwartakan)

			    if (no_fail != "") {
					if (!no_fail.trim().equals("")) {
						sql = sql + " AND (UPPER(F.NO_FAIL) LIKE '%"
								+ no_fail.toUpperCase().trim() + "%'";					
						
						sql = sql + " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
						+ no_fail.toUpperCase().trim() + "%'";
						
						sql = sql + " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"
						+ no_fail.toUpperCase().trim() + "%')";
					}
				}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.toUpperCase().trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan.toUpperCase() + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian.toUpperCase() + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status.toUpperCase() + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL PENARIKAN CARI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
				senarai_pembatalan_carian.addElement(h);
			}
			return senarai_pembatalan_carian;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector senarai_pembatalan_carian_online = null;
	public Vector senarai_pembatalan_carian_online(String carianStatus,String carianJenisHM,String carianNegeri,String carianNoFail,String carianTarikhMohon,
			String carianBilMohon,String carianNamaPB,String carianNoPB,String carianNoHM,String carianNoLot,
			String carianTujuan,String userId,String portal_role) throws Exception {
		senarai_pembatalan_carian_online = new Vector();
		Db db = null;
		senarai_pembatalan_carian_online.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_PTD, "
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN" 
						//	+" ,PB.FLAG_ONLINE  " 
									+" FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S,USERS UR,USERS_KEMENTERIAN UK " 
						//	+",TBLPPTPEMBATALAN PB "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) " 
						//	+"AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN"
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND (SELECT COUNT(*) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = P.ID_PERMOHONAN ) = '0'"
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND UR.USER_ID = UK.USER_ID AND UK.ID_KEMENTERIAN = F.ID_KEMENTERIAN "					
					+ " AND F.ID_SUBURUSAN = '52' "
					+ " AND F.NO_FAIL IS NOT NULL "
					+ " AND UR.USER_ID = '"+userId+"' "
				/*	+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "*/
				/*	+ " AND NVL(S.ID_STATUS,0) IN ('5','11','16','20','22','113','127','128','132',"
					+ " '133','134','148','147','149','181','182')"*/
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
					
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			   // if(!role.equals("(PPT)Pengarah"))
			
			/*
			    if(!negeriuser.equals("16"))
			    {
				sql += "AND  F.ID_NEGERI = '"+user_negeri+"' ";
			    }
			    */

			if(portal_role.equals("online_kjpagensi")){
    			sql +="AND uk.id_agensi = p.id_agensi ";
    		}
			
			//NO FAIL
			if (!carianNoFail.equals("")) {
					sql += " AND (UPPER(f.no_fail) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptg) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_permohonan_online) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_upt) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptd) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%'))";
			}//close if pemohon 
	
			if (!carianBilMohon.equals("")) {
				sql += " AND UPPER(p.no_permohonan) LIKE UPPER('%" + carianBilMohon.toUpperCase() + "%') ";
			}
			
			//TARIKH
			if (!carianTarikhMohon.equals("")) {
				sql = sql + " AND UPPER(p.tarikh_permohonan) = "+carianTarikhMohon;
			}//close tarikh  

    		//STATUS
			if (!carianStatus.equals("")) {
				sql = sql + " AND UPPER(s.id_status) = '"+carianStatus+"' ";
			}//close status
			
			//Negeri Projek
			if (!carianNegeri.equals("")) {
				sql = sql + " AND UPPER(f.id_negeri) = '"+carianNegeri+"'";
			}//close Urusan
			
			//Nama Projek
			if (!carianTujuan.equals("")) {
				sql = sql + " AND UPPER(p.tujuan) LIKE UPPER('%"+carianTujuan+"%')";
			}//close Urusan
			
			//No hakmilik
	    	if (!carianNoHM.equals("")) {
	    		sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
	    			   " WHERE UPPER(NO_HAKMILIK) LIKE UPPER('%"+carianNoHM+"%'))";
		    }
	    	
	    	//Nama PB
	    	if (!carianNamaPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB " +
		               " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+carianNamaPB.trim()+"'|| '%') ) ";								
			}
	    	
	    	//No PB
			if (!carianNoPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
		            +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NO_PB) LIKE UPPER('%"+carianNoPB+"%')";									
			}
	    	
			
			//no_lot/pt
	    	if (!carianNoLot.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
			    	   " WHERE UPPER(NO_LOT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%') " +
			    	   " OR UPPER(NO_PT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%'))";					   
			}
			
	    	//Id jenishakmilik
	    	if (!carianJenisHM.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_JENISHAKMILIK = '"+carianJenisHM+"')";
	    	}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL PENARIKAN CARI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
			//	h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? ""
			//			: rs.getString("FLAG_ONLINE").toUpperCase());
				senarai_pembatalan_carian_online.addElement(h);
			}
			return senarai_pembatalan_carian_online;
		} finally {
			if (db != null)
				db.close();
		}
	}


	
/*
	Vector senarai_pembatalan_carian = null;

	public Vector senarai_pembatalan_carian(String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon) throws Exception {
		senarai_pembatalan_carian = new Vector();
		Db db = null;
		senarai_pembatalan_carian.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,F.NO_FAIL,P.NO_RUJUKAN_UPT,K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN"
					+ " FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,TBLRUJSTATUS S "
					+ " WHERE P.ID_FAIL = F.ID_FAIL(+)"
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)"
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "'"
					+ " AND NVL(S.ID_STATUS,0) IN ('5','11','16','20','22','82','113','127','128','132',"
					+ " '133','134','148','147','149','181','182')"
					+ " AND P.ID_STATUS = S.ID_STATUS";
			// kena filter by status (belom diwartakan)

			if (no_fail != "") {
				if (!no_fail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ no_fail.toUpperCase().trim() + "%'";
				}
			}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.toUpperCase().trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan.toUpperCase() + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian.toUpperCase() + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status.toUpperCase() + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL BATAL CARI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PEMBATALAN") == null ?
				// "" : rs.getString("ID_PEMBATALAN"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PEMBATALAN") == null ?
				// "" : rs.getString("NO_PEMBATALAN").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "default"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PEMBATALAN")==null?"":Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT").toUpperCase());
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				senarai_pembatalan_carian.addElement(h);
			}
			return senarai_pembatalan_carian;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	*/
	
	
	Vector senarai_penarikan_carian_online = null;

	public Vector senarai_penarikan_carian_online(String carianStatus,String carianJenisHM,String carianNegeri,String carianNoFail,String carianTarikhMohon,
			String carianBilMohon,String carianNamaPB,String carianNoPB,String carianNoHM,String carianNoLot,
			String carianTujuan,String userId,String portal_role) throws Exception {
		senarai_penarikan_carian_online = new Vector();
		Db db = null;
		senarai_penarikan_carian_online.clear();
		String sql = "";

		if(carianTarikhMohon!=""){
	    	carianTarikhMohon = "to_date('" + carianTarikhMohon + "','dd/MM/yyyy')";
	    }	  
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT,"
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN" 
						//	+",PB.FLAG_ONLINE  " 
									+" FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S,USERS UR,USERS_KEMENTERIAN UK" 
							//+",TBLPPTPENARIKANBALIK PB "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) " 
						//	+"AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) " +
					"AND UR.USER_ID = UK.USER_ID AND UK.ID_KEMENTERIAN = F.ID_KEMENTERIAN "
					+ " AND (SELECT COUNT(*) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = P.ID_PERMOHONAN ) > '0' "
					+ " AND F.ID_SUBURUSAN = '52' "
					+" "
					+" AND (SELECT COUNT(*) FROM TBLPPTHAKMILIK HMK WHERE HMK.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND HMK.ID_HAKMILIK  NOT IN "
					+" (SELECT DISTINCT H.ID_HAKMILIK FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK HK,TBLPPTBORANGK BK "
					+" WHERE HK.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN AND HK.ID_BORANGK = BK.ID_BORANGK ) ) > 0  "
					+ " AND UR.USER_ID = '"+userId+"'"
				/*	+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "	*/				
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
			/*		+ " AND NVL(S.ID_STATUS,0) IN ('31','35','38','43','46','48','52','54','58','59',"
					+ " '62','66','68','72','74','76','181')"*/
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							""
			
			        + "   "; 
			
			
			if(portal_role.equals("online_kjpagensi")){
    			sql +="AND uk.id_agensi = p.id_agensi ";
    		}
			
			//NO FAIL
			if (!carianNoFail.equals("")) {
					sql += " AND (UPPER(f.no_fail) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptg) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_permohonan_online) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_upt) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%') " +
						" OR UPPER(p.no_rujukan_ptd) LIKE UPPER('%" + carianNoFail.toUpperCase() + "%'))";
			}//close if pemohon 
	
			if (!carianBilMohon.equals("")) {
				sql += " AND UPPER(p.no_permohonan) LIKE UPPER('%" + carianBilMohon.toUpperCase() + "%') ";
			}
			
			//TARIKH
			if (!carianTarikhMohon.equals("")) {
				sql = sql + " AND UPPER(p.tarikh_permohonan) = "+carianTarikhMohon;
			}//close tarikh  

    		//STATUS
			if (!carianStatus.equals("")) {
				sql = sql + " AND UPPER(s.id_status) = '"+carianStatus+"' ";
			}//close status
			
			//Negeri Projek
			if (!carianNegeri.equals("")) {
				sql = sql + " AND UPPER(f.id_negeri) = '"+carianNegeri+"'";
			}//close Urusan
			
			//Nama Projek
			if (!carianTujuan.equals("")) {
				sql = sql + " AND UPPER(p.tujuan) LIKE UPPER('%"+carianTujuan+"%')";
			}//close Urusan
			
			//No hakmilik
	    	if (!carianNoHM.equals("")) {
	    		sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
	    			   " WHERE UPPER(NO_HAKMILIK) LIKE UPPER('%"+carianNoHM+"%')";
		    }
	    	
	    	//Nama PB
	    	if (!carianNamaPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB " +
		               " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+carianNamaPB.trim()+"'|| '%') ) ";								
			}
	    	
	    	//No PB
			if (!carianNoPB.equals("")) {
				sql += " AND P.ID_PERMOHONAN IN (SELECT H.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "
		            +  " WHERE H.ID_HAKMILIK = HPB.ID_HAKMILIK AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN" +
		               " AND UPPER(PB.NO_PB) LIKE UPPER('%"+carianNoPB+"%')";									
			}
	    	
			
			//no_lot/pt
	    	if (!carianNoLot.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK " +
			    	   " WHERE UPPER(NO_LOT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%') " +
			    	   " OR UPPER(NO_PT) LIKE UPPER('%' ||'"+carianNoLot.trim()+"'|| '%'))";					   
			}
			
	    	//Id jenishakmilik
	    	if (!carianJenisHM.equals("")) {
			    sql += " AND P.ID_PERMOHONAN IN (SELECT ID_PERMOHONAN FROM TBLPPTHAKMILIK WHERE ID_JENISHAKMILIK = '"+carianJenisHM+"')";
	    	}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";
			
			
			myLogger.info("SQL PENARIKAN ONLINE :"+sql.toUpperCase());

			myLogger.info("senarai_penarikan_carian_online :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
			/*	h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? ""
						: rs.getString("FLAG_ONLINE").toUpperCase());*/
				senarai_penarikan_carian_online.addElement(h);
			}
			return senarai_penarikan_carian_online;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_penarikan_carian = null;

	public Vector senarai_penarikan_carian(String negeriuser,String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
		senarai_penarikan_carian = new Vector();
		Db db = null;
		senarai_penarikan_carian.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTG, "
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND (SELECT COUNT(*) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = P.ID_PERMOHONAN ) > '0' "
					+ " AND F.ID_SUBURUSAN = '52' "
					+" "
					+" AND (SELECT COUNT(*) FROM TBLPPTHAKMILIK HMK WHERE HMK.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND HMK.ID_HAKMILIK  NOT IN "
					+" (SELECT DISTINCT H.ID_HAKMILIK FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK HK,TBLPPTBORANGK BK "
					+" WHERE HK.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN AND HK.ID_BORANGK = BK.ID_BORANGK ) ) > 0  "
			/*		+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "		*/			
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
			/*		+ " AND NVL(S.ID_STATUS,0) IN ('31','35','38','43','46','48','52','54','58','59',"
					+ " '62','66','68','72','74','76','181')"*/
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			
			if(!negeriuser.equals("16") && !negeriuser.isEmpty()){
    			if(negeriuser.equals("14")){
    				sql += "AND f.id_negeri in (14,15,16) ";
    			}else{
    				sql += "AND f.id_negeri ='"+negeriuser+"'";
    			}		
    		}

			// kena filter by status (sudah diwartakan)

			if (no_fail != "") {
				if (!no_fail.trim().equals("")) {
					sql = sql + " AND (UPPER(F.NO_FAIL) LIKE '%"
							+ no_fail.toUpperCase().trim() + "%'";					
					
					sql = sql + " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
					+ no_fail.toUpperCase().trim() + "%'";
					
					sql = sql + " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"
					+ no_fail.toUpperCase().trim() + "%')";
				}
			}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan.toUpperCase() + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian.toUpperCase() + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status.toUpperCase() + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL PENARIKAN CARI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
				senarai_penarikan_carian.addElement(h);
			}
			return senarai_penarikan_carian;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector header = null;

	public Vector content_header(String id_permohonan) throws Exception {
		header = new Vector();
		Db db = null;
		header.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.NO_RUJUKAN_PTG,SF.ID_SUBURUSANSTATUSFAILPPT,SF.AKTIF,SF.ID_SUBURUSANSTATUS,P.ID_PERMOHONAN, F.ID_NEGERI, P.ID_NEGERI AS ID_NEGERI_PROJECT, P.NO_PERMOHONAN, "
					+ "P.ID_FAIL,  F.NO_FAIL, F.ID_SUBURUSAN, P.TARIKH_PERMOHONAN, P.ID_STATUS,  F.ID_KEMENTERIAN, "
					+ "P.ID_AGENSI, P.FLAG_PERUNTUKAN, P.FLAG_SEGERA,  P.ID_DAERAH,  P.TUJUAN, P.NO_RUJUKAN_SURAT, "
					+ "P.TARIKH_KEHENDAKI,  P.ALAMAT1, P.ALAMAT2, P.ALAMAT3, P.POSKOD, P.ID_MUKIM, "
					+ "Initcap(K.NAMA_KEMENTERIAN) as NAMA_KEMENTERIAN, Initcap(B.NAMA_DAERAH) as NAMA_DAERAH, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, "
					+ "P.NO_RUJUKAN_UPT, SU.NAMA_SUBURUSAN, S.KETERANGAN, C.ID_NEGERI,C.NAMA_NEGERI,P.TARIKH_TERIMA,AG.NAMA_AGENSI "
					+ "FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN K,TBLRUJNEGERI C,  "
					+ "TBLRUJDAERAH B,TBLRUJSUBURUSAN SU,TBLRUJSUBURUSANSTATUSFAILPPT SF,TBLRUJSTATUS S, TBLRUJAGENSI AG  "
					+ "WHERE "
					+ "P.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ "AND P.ID_FAIL = F.ID_FAIL(+) "
					+ "AND SF.ID_FAIL(+) = F.ID_FAIL "
					+ "AND P.ID_DAERAH = B.ID_DAERAH(+) "
					+ "AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN(+) "
					+ "AND P.ID_STATUS = S.ID_STATUS "
					+ "AND F.ID_NEGERI = C.ID_NEGERI(+) "
					+ "AND P.ID_AGENSI = AG.ID_AGENSI(+)";

			myLogger.info("SQL setHeader :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				//SF.ID_SUBURUSANSTATUSFAIL,SF.AKTIV,SF.ID_SUBURUSANSTATUS,
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "................." : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("ID_SUBURUSANSTATUSFAIL",
						rs.getString("ID_SUBURUSANSTATUSFAILPPT") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUSFAILPPT"));
				h.put("AKTIF",
						rs.getString("AKTIF") == null ? "" : rs
								.getString("AKTIF"));
				
				h.put("ID_SUBURUSANSTATUS",
						rs.getString("ID_SUBURUSANSTATUS") == null ? "" : rs
								.getString("ID_SUBURUSANSTATUS"));
				
				h.put("ID_NEGERI",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				
				h.put("id_permohonan",
						rs.getString("id_permohonan") == null ? "" : rs
								.getString("id_permohonan"));
				
	    		h.put("no_permohonan",
						rs.getString("no_permohonan") == null ? "-" : rs
								.getString("no_permohonan"));
				h.put("id_fail", rs.getString("id_fail") == null ? "" : rs
						.getString("id_fail").toUpperCase());
				h.put("id_status", rs.getString("id_status"));
				h.put("no_fail", rs.getString("no_fail") == null ? "-" : rs
						.getString("no_fail").toUpperCase());
				h.put("nama_kementerian",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian").toUpperCase());
				h.put("nama_kementerian1",
						rs.getString("nama_kementerian") == null ? "-" : rs
								.getString("nama_kementerian"));
				h.put("nama_daerah", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah").toUpperCase());
				h.put("nama_daerah1", rs.getString("nama_daerah") == null ? "-"
						: rs.getString("nama_daerah"));
				h.put("no_rujukan_ptd",
						rs.getString("no_rujukan_ptd") == null ? "" : rs
								.getString("no_rujukan_ptd").toUpperCase());
				h.put("no_rujukan_ptg",
						rs.getString("no_rujukan_ptg") == null ? "" : rs
								.getString("no_rujukan_ptg").toUpperCase());
				h.put("no_rujukan_upt",
						rs.getString("no_rujukan_upt") == null ? "" : rs
								.getString("no_rujukan_upt").toUpperCase());
				h.put("nama_suburusan",
						rs.getString("nama_suburusan") == null ? "-" : rs
								.getString("nama_suburusan").toUpperCase());
				h.put("keterangan", rs.getString("keterangan") == null ? "-"
						: rs.getString("keterangan").toUpperCase());
				h.put("nama_agensi", rs.getString("nama_agensi") == null ? "-"
						: rs.getString("nama_agensi").toUpperCase());
				h.put("projek_negeri",
						rs.getString("nama_negeri") == null ? "-" : rs
								.getString("nama_negeri"));
				if (rs.getString("id_suburusan") == null) {
					h.put("id_suburusan", "");
				} else {
					h.put("id_suburusan", rs.getString("id_suburusan"));
				}
				h
						.put("tarikh_permohonan",
								rs.getString("tarikh_permohonan") == null ? "-"
										: Format.format(rs
												.getDate("tarikh_permohonan")));
				h.put("tarikh_terima",
						rs.getString("tarikh_terima") == null ? "-" : Format
								.format(rs.getDate("tarikh_terima")));
				h.put("id_status", rs.getString("id_status") == null ? "" : rs
						.getString("id_status"));
				if (rs.getString("id_kementerian") == null) {
					h.put("id_kementerian", "");
				} else {
					h.put("id_kementerian", rs.getString("id_kementerian"));
				}
				if (rs.getString("id_agensi") == null) {
					h.put("id_agensi", "");
				} else {
					h.put("id_agensi", rs.getString("id_agensi"));
				}
				h.put("flag_peruntukan",
						rs.getString("flag_peruntukan") == null ? "" : rs
								.getString("flag_peruntukan"));
				h.put("flag_segera", rs.getString("flag_segera") == null ? ""
						: rs.getString("flag_segera"));
				if (rs.getString("ID_NEGERI_PROJECT") == null) {
					h.put("id_negeri_projek", "");
				} else {
					h
							.put("id_negeri_projek", rs
									.getString("ID_NEGERI_PROJECT"));
				}
				if (rs.getString("id_daerah") == null) {
					h.put("id_daerah", "");
				} else {
					h.put("id_daerah", rs.getString("id_daerah"));
				}
				h.put("tujuan", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan"));
				h.put("tujuan_upper", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan").toUpperCase());
				h.put("tujuan_mmk", rs.getString("tujuan") == null ? "-" : rs
						.getString("tujuan"));
				h.put("no_rujukan_surat",
						rs.getString("no_rujukan_surat") == null ? "-" : rs
								.getString("no_rujukan_surat"));
				h.put("tarikh_kehendaki",
						rs.getString("tarikh_kehendaki") == null ? "-" : Format
								.format(rs.getDate("tarikh_kehendaki")));
				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				if (rs.getString("id_negeri") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("id_negeri"));
				}
				if (rs.getString("id_mukim") == null) {
					h.put("id_mukim", "");
				} else {
					h.put("id_mukim", rs.getString("id_mukim"));
				}
				header.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
		return header;
	}

	Vector maklumat_pembatalan = null;

	public Vector maklumat_pembatalan(String id_penarikan) throws Exception {
		maklumat_pembatalan = new Vector();
		Db db = null;
		maklumat_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_SEMAKAN_ONLINE, F.ID_FAIL,B.FLAG_ONLINE,B.ID_PEMBATALAN, B.NO_RUJUKAN_SURAT, B.TARIKH_PEMBATALAN," +
					"B.TARIKH_TERIMA_SURAT, " +
					"B.NO_PEMBATALAN,"
					+ " B.JENIS_PEMBATALAN, Initcap(B.SEBAB_PEMBATALAN) AS SEBAB_PEMBATALAN, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPEMBATALAN B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND B.ID_PEMBATALAN = '" + id_penarikan + "'";

			myLogger.info("MAKLUMAT_PEMBATALAN:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? "" : rs
						.getString("FLAG_ONLINE"));
				h.put("FLAG_SEMAKAN_ONLINE", rs.getString("FLAG_SEMAKAN_ONLINE") == null ? "" : rs
						.getString("FLAG_SEMAKAN_ONLINE"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PEMBATALAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PEMBATALAN")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PEMBATALAN") == null ? "" : rs
								.getString("NO_PEMBATALAN"));
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PEMBATALAN") == null ? "" : rs
								.getString("JENIS_PEMBATALAN")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA_SURAT") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA_SURAT")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN")
								);
				h.put("SEBAB_PEMBATALAN1",
						rs.getString("SEBAB_PEMBATALAN") == null ? "" : rs
								.getString("SEBAB_PEMBATALAN"));
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));

				maklumat_pembatalan.addElement(h);
			}
			return maklumat_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_penarikan = null;

	public Vector maklumat_penarikan(String id_penarikan) throws Exception {
		maklumat_penarikan = new Vector();
		Db db = null;
		maklumat_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_SEMAKAN_ONLINE, F.ID_FAIL,B.ID_PENARIKANBALIK,B.FLAG_ONLINE, B.NO_RUJUKAN_SURAT, B.TARIKH_PENARIKAN_BALIK,B.TARIKH_TERIMA, B.NO_PENARIKANBALIK,"
					+ " B.JENIS_PENARIKANBALIK, Initcap(B.SEBAB_PENARIKANBALIK) AS SEBAB_PENARIKANBALIK, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPENARIKANBALIK B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND B.ID_PENARIKANBALIK = '" + id_penarikan + "'";

			myLogger.info("MAKLUMAT_PENARIKAN:" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? "" : rs
						.getString("FLAG_ONLINE"));
				h.put("FLAG_SEMAKAN_ONLINE", rs.getString("FLAG_SEMAKAN_ONLINE") == null ? "" : rs
						.getString("FLAG_SEMAKAN_ONLINE"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PENARIKAN_BALIK") == null ? ""
						: Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PENARIKANBALIK") == null ? "" : rs
								.getString("NO_PENARIKANBALIK"));
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
								.getString("JENIS_PENARIKANBALIK")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
								.getString("SEBAB_PENARIKANBALIK")
								);
				h.put("SEBAB_PEMBATALAN1",
						rs.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
								.getString("SEBAB_PENARIKANBALIK"));
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));

				maklumat_penarikan.addElement(h);
			}
			return maklumat_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_penarikan = null;

	public Vector senarai_penarikan(String id_permohonan) throws Exception {
		senarai_penarikan = new Vector();
		Db db = null;
		senarai_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_ONLINE,F.ID_FAIL,B.ID_PENARIKANBALIK, B.NO_RUJUKAN_SURAT, B.TARIKH_PENARIKAN_BALIK,B.TARIKH_TERIMA, B.NO_PENARIKANBALIK,"
					+ " B.JENIS_PENARIKANBALIK, B.SEBAB_PENARIKANBALIK, B.ID_STATUS AS ID_BATAL_STATUS, B.TARIKH_SURAT,"
					+ "B.TARIKH_KUATKUASA, B.NO_RUJUKAN_SURAT, B.ID_PERMOHONAN,P.ID_STATUS "
					+ "FROM TBLPPTPENARIKANBALIK B, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND P.ID_PERMOHONAN = '"
					+ id_permohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_ONLINE", rs.getString("FLAG_ONLINE") == null ? "" : rs
						.getString("FLAG_ONLINE"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PEMBATALAN", rs
						.getString("TARIKH_PENARIKAN_BALIK") == null ? ""
						: Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_PEMBATALAN",
						rs.getString("NO_PENARIKANBALIK") == null ? "" : rs
								.getString("NO_PENARIKANBALIK"));
				h.put("JENIS_PEMBATALAN",
						rs.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
								.getString("JENIS_PENARIKANBALIK")
								.toUpperCase());
				h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT") == null ? ""
						: Format.format(rs.getDate("TARIKH_SURAT")));
				h.put("TARIKH_KUATKUASA",
						rs.getString("TARIKH_KUATKUASA") == null ? "" : Format
								.format(rs.getDate("TARIKH_KUATKUASA")));
				h.put("TARIKH_TERIMA_SURAT",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("SEBAB_PEMBATALAN",
						rs.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
								.getString("SEBAB_PENARIKANBALIK")
								);
				h.put("ID_BATAL_STATUS",
						rs.getString("ID_BATAL_STATUS") == null ? "" : rs
								.getString("ID_BATAL_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN").toUpperCase());
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs
						.getString("ID_STATUS").toUpperCase());
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "" : rs
								.getString("NO_RUJUKAN_SURAT"));

				senarai_penarikan.addElement(h);
			}
			return senarai_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addPembatalanOnline(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		int id_stat = 235;
		String kodSubUrusan = "21";
		String kodJabatan = "PB";
		
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String tahun = formatter.format(now);
		String no_penarikan = tahun + "-"
				+ String.format("%06d", getSeqNoAduanOnline(id_stat));

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("TARIKH_PEMBATALAN", r.unquote("sysdate"));

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA_SURAT", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_SURAT", "");
			}

			r.add("NO_PEMBATALAN", no_penarikan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PEMBATALAN", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PEMBATALAN", 2);
			} else {
				r.add("JENIS_PEMBATALAN", "");
			}
			r.add("SEBAB_PEMBATALAN", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			r.add("FLAG_ONLINE",1);

			sql = r.getSQLInsert("tblpptpembatalan");

			myLogger.info("SQL INSERT PENARIKAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			/* TIME JKPTG SAHKAN BARU UPDATE STATUS
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 235);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");
			
			
			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);
			*/
			
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void addPembatalan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		int id_stat = 235;
		String kodSubUrusan = "21";
		String kodJabatan = "PB";
		
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String tahun = formatter.format(now);
		String no_penarikan = tahun + "-"
				+ String.format("%06d", getSeqNoAduanOnline(id_stat));

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("TARIKH_PEMBATALAN", r.unquote("sysdate"));

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA_SURAT", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_SURAT", "");
			}

			r.add("NO_PEMBATALAN", no_penarikan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PEMBATALAN", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PEMBATALAN", 2);
			} else {
				r.add("JENIS_PEMBATALAN", "");
			}
			r.add("SEBAB_PEMBATALAN", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLInsert("tblpptpembatalan");

			myLogger.info("SQL INSERT PENARIKAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 235);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");

			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}



	public void addPenarikan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_pembatalan = (String) data.get("id_pembatalan");		
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		int id_stat = 74;
		String kodSubUrusan = "21";
		// String kodSubUrusan =
		// String.format("%06d",getHakmilik_Subjaket_PPT(Integer.parseInt(id_permohonan)));
		String kodJabatan = "PB";
		// String kodJabatan = "";

		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String tahun = formatter.format(now);
		String no_penarikan = tahun + "-"
				+ String.format("%06d", getSeqNoAduanOnline(id_stat));

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("TARIKH_PENARIKAN_BALIK", r.unquote("sysdate"));

			/*
			 * if(txdTarikhPembatalan!="") {
			 * r.add("TARIKH_PENARIKAN_BALIK",r.unquote("sysdate")); } else {
			 * r.add("TARIKH_PENARIKAN_BALIK",""); }
			 */

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			r.add("NO_PENARIKANBALIK", no_penarikan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PENARIKANBALIK", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PENARIKANBALIK", 2);
			} else {
				r.add("JENIS_PENARIKANBALIK", "");
			}
			r.add("SEBAB_PENARIKANBALIK", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_STATUS", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			myLogger.info("SQL INSERT BEFORE :" + sql.toUpperCase());
			sql = r.getSQLInsert("tblpptpenarikanbalik");

			myLogger.info("SQL INSERT PENARIKAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 74);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			myLogger.info("SQL UPDATE BEFORE :" + sql1.toUpperCase());
			sql1 = r1.getSQLUpdate("tblpptpermohonan");

			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void addPenarikanOnline(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_pembatalan = (String) data.get("id_pembatalan");		
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		int id_stat = 74;
		String kodSubUrusan = "21";
		// String kodSubUrusan =
		// String.format("%06d",getHakmilik_Subjaket_PPT(Integer.parseInt(id_permohonan)));
		String kodJabatan = "PB";
		// String kodJabatan = "";

		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String tahun = formatter.format(now);
		String no_penarikan = tahun + "-"
				+ String.format("%06d", getSeqNoAduanOnline(id_stat));

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("TARIKH_PENARIKAN_BALIK", r.unquote("sysdate"));

			/*
			 * if(txdTarikhPembatalan!="") {
			 * r.add("TARIKH_PENARIKAN_BALIK",r.unquote("sysdate")); } else {
			 * r.add("TARIKH_PENARIKAN_BALIK",""); }
			 */

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			r.add("NO_PENARIKANBALIK", no_penarikan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PENARIKANBALIK", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PENARIKANBALIK", 2);
			} else {
				r.add("JENIS_PENARIKANBALIK", "");
			}
			r.add("SEBAB_PENARIKANBALIK", txtSebabPembatalan);
			
			
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			r.add("FLAG_ONLINE",1);

			sql = r.getSQLInsert("tblpptpenarikanbalik");

			myLogger.info("SQL INSERT PENARIKAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			/* TUKAR STATUS DIUBAH DI BUTTON SAHKAN PERMOHONAN 
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 74);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");
			
			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);
			*/
		} finally {
			if (db != null)
				db.close();
		}

	}


	public void updatePenarikan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_PENARIKANBALIK", id_pembatalan);
			/*
			 * if(txdTarikhPembatalan!="") {
			 * r.add("TARIKH_PEMBATALAN",r.unquote("to_date('" +
			 * txdTarikhPembatalan + "','dd/MM/yyyy')")); } else {
			 * r.add("TARIKH_PEMBATALAN",""); }
			 */

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			r.add("NO_PENARIKANBALIK", txtNoPembatalan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PENARIKANBALIK", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PENARIKANBALIK", 2);
			} else {
				r.add("JENIS_PENARIKANBALIK", "");
			}
			r.add("SEBAB_PENARIKANBALIK", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("tblpptpenarikanbalik");

			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void updatePenarikanOnline3(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_permohonan = (String) data.get("id_permohonan");	
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PENARIKANBALIK", id_pembatalan);			
			r.add("FLAG_ONLINE", 3);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		
			//Update status setelah disahkan jkptg
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 74);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");
			myLogger.info("SQL UPDATE BATALKAN :" + sql.toUpperCase());
			stmt1.executeUpdate(sql1);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePenarikanOnline3Tolak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PENARIKANBALIK", id_pembatalan);			
			r.add("FLAG_ONLINE", 4);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembatalanOnline3(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");
		String id_permohonan = (String) data.get("id_permohonan");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PEMBATALAN", id_pembatalan);			
			r.add("FLAG_ONLINE", 3);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpembatalan");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PERMOHONAN", id_permohonan);
			r1.add("ID_STATUS", 235);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpermohonan");
			stmt1.executeUpdate(sql1);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembatalanOnline3Tolak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PEMBATALAN", id_pembatalan);			
			r.add("FLAG_ONLINE", 4);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpembatalan");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	public void updatePenarikanOnline(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PENARIKANBALIK", id_pembatalan);			
			r.add("FLAG_ONLINE", 2);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePenarikanOnlineTolak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PENARIKANBALIK", id_pembatalan);			
			r.add("FLAG_ONLINE", 4);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembatalanOnline(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PEMBATALAN", id_pembatalan);			
			r.add("FLAG_ONLINE", 2);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpembatalan");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembatalanOnlineTolak(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");		
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PEMBATALAN", id_pembatalan);			
			r.add("FLAG_ONLINE", 4);			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpembatalan");
			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePembatalan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtNoPembatalan = (String) data.get("txtNoPembatalan");
		String txdTarikhPembatalan = (String) data.get("txdTarikhPembatalan");
		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String sorJenisPembatalan = (String) data.get("sorJenisPembatalan");
		String txtSebabPembatalan = (String) data.get("txtSebabPembatalan");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_PEMBATALAN", id_pembatalan);
			

			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTarikhSurat
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA_SURAT", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_SURAT", "");
			}

			r.add("NO_PEMBATALAN", txtNoPembatalan);
			if (sorJenisPembatalan.equals("sebahagian")) {
				r.add("JENIS_PEMBATALAN", 1);
			} else if (sorJenisPembatalan.equals("keseluruhan")) {
				r.add("JENIS_PEMBATALAN", 2);
			} else {
				r.add("JENIS_PEMBATALAN", "");
			}
			r.add("SEBAB_PEMBATALAN", txtSebabPembatalan);
			r.add("NO_RUJUKAN_SURAT", txtNoRujSurat);
			r.add("ID_PERMOHONAN", id_permohonan);
			r.add("ID_STATUS", "");
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("tblpptpembatalan");

			myLogger.info("SQL UPDATE BATAL :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateFiles(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_dokumen = (String) data.get("id_dokumen");
		String txtnamadokumen = (String) data.get("txtnamadokumen");
		String txtketerangandokumen = (String) data.get("txtketerangandokumen");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_DOKUMEN", id_dokumen);
			r.add("TAJUK", txtnamadokumen);
			r.add("KETERANGAN", txtketerangandokumen);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("tblpptdokumen");

			myLogger.info("SQL UPDATE DOKUMEN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deletePembatalan(String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			sql = r.getSQLDelete("tblpptpembatalan");
			stmt.executeUpdate(sql);

			/*
			 * Statement stmt1 = db.getStatement(); SQLRenderer r1 = new
			 * SQLRenderer(); r1.update("ID_PEMBATALAN", id_pembatalan);
			 * r1.add("ID_PEMBATALAN", ""); r1.add("TARIKH_KEMASKINI",
			 * r1.unquote("sysdate")); sql1 = r1.getSQLUpdate("tblpptdokumen");
			 * myLogger.info("SQL DELETE ID_PEMBATALAN :"+sql1.toUpperCase());
			 * stmt1.executeUpdate(sql1);
			 */

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_PENARIKANBALIK", id_pembatalan);
			sql1 = r1.getSQLDelete("tblpptdokumen");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("ID_PEMBATALAN", id_pembatalan);
			r2.add("ID_PEMBATALAN", "");
			r2.add("FLAG_PEMBATALAN", "");
			r2.add("FLAG_PENARIKAN_BALIK", "");

			r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
			sql2 = r2.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql2.toUpperCase());
			stmt2.executeUpdate(sql2);

			/*
			 * Statement stmt1 = db.getStatement(); SQLRenderer r1 = new
			 * SQLRenderer(); r1.add("ID_PEMBATALAN", id_pembatalan); sql =
			 * r1.getSQLDelete("tblpptdokumen"); stmt.executeUpdate(sql);
			 */
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePenarikan(String id_pembatalan) throws Exception {
		Db db = null;

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			sql = r.getSQLDelete("tblpptpenarikanbalik");
			stmt.executeUpdate(sql);

			/*
			 * Statement stmt1 = db.getStatement(); SQLRenderer r1 = new
			 * SQLRenderer(); r1.update("ID_PENARIKANBALIK", id_pembatalan);
			 * r1.add("ID_PENARIKANBALIK", ""); // r.add("ID_KEMASKINI",
			 * id_Masuk); r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			 * sql1 = r1.getSQLUpdate("tblpptdokumen");
			 * myLogger.info("SQL DELETE ID_PEMBATALAN :"+sql1.toUpperCase());
			 * stmt1.executeUpdate(sql1);
			 */

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_PENARIKANBALIK", id_pembatalan);
			sql1 = r1.getSQLDelete("tblpptdokumen");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("ID_PENARIKANBALIK", id_pembatalan);
			r2.add("ID_PENARIKANBALIK", "");
			r2.add("FLAG_PENARIKAN_BALIK", "");
			r2.add("FLAG_PENARIKAN_BALIK", "");
			r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
			sql2 = r2.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql2.toUpperCase());
			stmt2.executeUpdate(sql2);

			/*
			 * Statement stmt1 = db.getStatement(); SQLRenderer r1 = new
			 * SQLRenderer(); r1.add("ID_PENARIKANBALIK", id_pembatalan); sql =
			 * r1.getSQLDelete("tblpptdokumen"); stmt.executeUpdate(sql);
			 */
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deletePenarikanInternal(String id_pembatalan) throws Exception {
		Db db = null;

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		String sql8 = "";
		String sql9 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			sql = r.getSQLDelete("tblpptpenarikanbalik");
			stmt.executeUpdate(sql);

			

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_PENARIKANBALIK", id_pembatalan);
			sql1 = r1.getSQLDelete("tblpptdokumen");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("ID_PENARIKANBALIK", id_pembatalan);
			r2.add("ID_PENARIKANBALIK", "");
			r2.add("FLAG_PENARIKAN_BALIK", "");
			r2.add("FLAG_PENARIKAN_KESELURUHAN", "");
			r2.add("LUAS_LOT_TARIK", "");
			r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
			sql2 = r2.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql2.toUpperCase());
			stmt2.executeUpdate(sql2);

			/*
			 * //untuklaporan tanah //kena tambah field id_penarikanbalik
			 * 
			 * Statement stmt3 = db.getStatement(); SQLRenderer r3 = new
			 * SQLRenderer(); r3.add("ID_PENARIKANBALIK", id_pembatalan); sql3 =
			 * r3.getSQLDelete("tblppttanahumum");
			 * myLogger.info("SQL DELETE ID_PEMBATALAN :"+sql3.toUpperCase());
			 * stmt3.executeUpdate(sql3);
			 */

			Statement stmt5 = db.getStatement();
			SQLRenderer r5 = new SQLRenderer();
			r5.add("ID_PENARIKANBALIK", id_pembatalan);
			sql5 = "DELETE TBLPPTMMKKEPUTUSAN WHERE ID_MMK = (SELECT ID_MMK FROM TBLPPTMMK WHERE ID_PENARIKANBALIK = '"
					+ id_pembatalan + "')";
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql5.toUpperCase());
			stmt5.executeUpdate(sql5);

			Statement stmt4 = db.getStatement();
			SQLRenderer r4 = new SQLRenderer();
			r4.add("ID_PENARIKANBALIK", id_pembatalan);
			sql4 = r4.getSQLDelete("tblpptmmk");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql4.toUpperCase());
			stmt4.executeUpdate(sql4);

			Statement stmt7 = db.getStatement();
			SQLRenderer r7 = new SQLRenderer();
			r7.add("ID_PENARIKANBALIK", id_pembatalan);
			sql7 = r7.getSQLDelete("tblpptwarta");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql7.toUpperCase());
			stmt7.executeUpdate(sql7);
			
			Statement stmt9 = db.getStatement();
			SQLRenderer r9 = new SQLRenderer();			
			sql9 = "DELETE TBLPPTSUBSIASATAN WHERE ID_SIASATAN IN (SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE ID_PENARIKANBALIK = "+ id_pembatalan + ")";		
			stmt9.executeUpdate(sql9);
			
			Statement stmt8 = db.getStatement();
			SQLRenderer r8 = new SQLRenderer();			
			sql8 = "DELETE TBLPPTSIASATAN WHERE ID_PENARIKANBALIK = "+ id_pembatalan + "";		
			stmt8.executeUpdate(sql8);

			// 78327823
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deletePembatalanInternal(String id_pembatalan) throws Exception {
		Db db = null;

		String sql = "";
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";
		String sql4 = "";
		String sql5 = "";
		String sql6 = "";
		String sql7 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			sql = r.getSQLDelete("tblpptpembatalan");
			stmt.executeUpdate(sql);			

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_PEMBATALAN", id_pembatalan);
			sql1 = r1.getSQLDelete("tblpptdokumen");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql1.toUpperCase());
			stmt1.executeUpdate(sql1);

			Statement stmt2 = db.getStatement();
			SQLRenderer r2 = new SQLRenderer();
			r2.update("ID_PEMBATALAN", id_pembatalan);
			r2.add("ID_PEMBATALAN", "");
			r2.add("FLAG_PEMBATALAN", "");
			r2.add("FLAG_PEMBATALAN_KESELURUHAN", "");
			r2.add("LUAS_LOT_TARIK", "");
			r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
			sql2 = r2.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL DELETE ID_PEMBATALAN :" + sql2.toUpperCase());
			stmt2.executeUpdate(sql2);
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_hakmilik = null;

	public Vector senarai_hakmilik(String id_permohonan) throws Exception {
		senarai_hakmilik = new Vector();
		Db db = null;
		senarai_hakmilik.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT  H.no_subjaket,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,H.ID_PEMBATALAN,H.NO_PT," +
				//	"H.NO_LOT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
			" H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT, "+
					"JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK," +
					"H.NO_HAKMILIK,"					
					+ "M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL,H.FLAG_PEMBATALAN "
					+ " ,N.NAMA_NEGERI,D.NAMA_DAERAH "
					+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M "
					+ " ,TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL "
					+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ "AND H.ID_MUKIM = M.ID_MUKIM "
					+ "AND NVL(H.FLAG_PEMBATALAN,' ') <> 'Y' "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+)  AND H.ID_LOT = JL.ID_LOT(+) "
					+ "AND H.ID_PERMOHONAN = '" + id_permohonan + "' ORDER BY LPAD(H.no_subjaket,10) asc  ";
			// "AND PB.ID_HAKMILIK = H.ID_HAKMILIK "+
			// "AND PB.ID_PIHAKBERKEPENTINGAN IS NOT NULL";

			myLogger.info("MAKLUMAT PEMBATALAN BELOM BATAL XXX ::"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("BIL_DUM", bil + "abc");
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				// h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ?
				// "" : rs.getString("ID_HAKMILIKPB"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				// h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" :
				// rs.getString("NAMA_PB").toUpperCase());
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM").toUpperCase());

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				
				
				h.put("FLAG_PEMBATALAN",
						rs.getString("FLAG_PEMBATALAN") == null ? "" : rs
								.getString("FLAG_PEMBATALAN"));
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getString("LUAS_AMBIL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));

				senarai_hakmilik.addElement(h);
			}
			return senarai_hakmilik;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_hakmilik_overall = null;
	public Vector senarai_hakmilik_overall(String id_permohonan)
			throws Exception {
		senarai_hakmilik_overall = new Vector();
		Db db = null;
		senarai_hakmilik_overall.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT  H.no_subjaket,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,H.ID_PEMBATALAN,H.NO_PT," +
					//"H.NO_LOT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
			//" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+

					"JH.KETERANGAN AS JENIS_HAKMILIK,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ "M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL,H.FLAG_PEMBATALAN "
					+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLRUJLOT JL "
					+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ "AND H.ID_MUKIM = M.ID_MUKIM  AND H.ID_LOT = JL.ID_LOT(+) "
				//	+ " AND NVL(H.LUAS_AMBIL,'0') > 0 "
					+ "AND H.ID_PERMOHONAN = '" + id_permohonan + "' ORDER BY LPAD(H.no_subjaket,10) asc ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("BIL_DUM", bil + "abc");
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				// h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ?
				// "" : rs.getString("ID_HAKMILIKPB"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				// h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" :
				// rs.getString("NAMA_PB"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				
			
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				

				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("FLAG_PEMBATALAN",
						rs.getString("FLAG_PEMBATALAN") == null ? "" : rs
								.getString("FLAG_PEMBATALAN"));
				senarai_hakmilik_overall.addElement(h);
			}
			return senarai_hakmilik_overall;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_hakmilik_batal = null;

	public Vector senarai_hakmilik_batal(String id_permohonan) throws Exception {
		senarai_hakmilik_batal = new Vector();
		Db db = null;
		senarai_hakmilik_batal.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT  H.no_subjaket,H.LUAS_LOT_TARIK, H.ID_HAKMILIK,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.ID_PEMBATALAN,H.NO_PT," +
				//	"H.NO_LOT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+

					"JH.KETERANGAN AS JENIS_HAKMILIK,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ "M.NAMA_MUKIM,H.LUAS_LOT,H.LUAS_AMBIL,H.FLAG_PEMBATALAN "
					+ " ,N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL "
					+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M "
					+ " ,TBLRUJNEGERI N,TBLRUJDAERAH D, TBLRUJLOT JL "
					+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ "AND H.ID_MUKIM = M.ID_MUKIM "
					+ "AND H.FLAG_PEMBATALAN = 'Y' "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+)  AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ "AND H.ID_PERMOHONAN = '" + id_permohonan + "' ORDER BY LPAD(H.no_subjaket,10) asc ";
			myLogger.info("MAKLUMAT PEMBATALAN BATAL XXX ::"
					+ sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);

				h.put("BIL_DUM", bil + "abc");
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				// h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ?
				// "" : rs.getString("ID_HAKMILIKPB"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				// h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" :
				// rs.getString("NAMA_PB"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				

				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("FLAG_PEMBATALAN",
						rs.getString("FLAG_PEMBATALAN") == null ? "" : rs
								.getString("FLAG_PEMBATALAN"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));

				senarai_hakmilik_batal.addElement(h);
			}
			return senarai_hakmilik_batal;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_hakmilik_penarikan = null;

	public Vector senarai_hakmilik_penarikan(String id_permohonan,
			String id_penarikan) throws Exception {
		senarai_hakmilik_penarikan = new Vector();
		Db db = null;
		senarai_hakmilik_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET,H.ID_HAKMILIK,H.NO_PT," +
					
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ " M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M, "
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D, TBLRUJLOT JL"
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM  AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND  "
					+" (SELECT COUNT(*) FROM TBLPPTHAKMILIK HM,TBLPPTHAKMILIKBORANGK HK,TBLPPTBORANGK BK"
						+"	WHERE HM.ID_HAKMILIK = H.ID_HAKMILIK AND HK.ID_HAKMILIK = HM.ID_HAKMILIK AND HK.ID_BORANGK = BK.ID_BORANGK ) = '0' "
				    + " AND NVL(H.FLAG_PENARIKAN_KESELURUHAN,' ') <> 'Y'"
				    + " AND NVL(H.FLAG_PEMBATALAN_KESELURUHAN,' ') <> 'Y'"
					+ " AND H.ID_HAKMILIK NOT IN "
					+ " (SELECT DISTINCT H.ID_HAKMILIK  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' )"
					+ " AND H.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " 	 ORDER BY LPAD(H.NO_SUBJAKET,10) ASC	";

			myLogger.info("SENARAI HAKMILIK PENARIKAN" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT") != null) {
					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} 
				else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
				}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					
					}

			
				h.put("LUAS_LOT_EDIT", rs.getString("LUAS_LOT") == null ? ""
						: rs.getDouble("LUAS_LOT"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));

				
				
				senarai_hakmilik_penarikan.addElement(h);
			}
			return senarai_hakmilik_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	Vector senarai_hakmilik_penarikan_all = null;

	public Vector senarai_hakmilik_penarikan_all(String id_permohonan,
			String id_penarikan) throws Exception {
		senarai_hakmilik_penarikan = new Vector();
		Db db = null;
		senarai_hakmilik_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET,H.ID_HAKMILIK,H.NO_PT," +
					
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ " M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M, "
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D, TBLRUJLOT JL "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM  AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
				//	+ " AND NVL(H.LUAS_AMBIL,'0') > 0 "
					+ " AND H.ID_HAKMILIK NOT IN "
					+ " (SELECT DISTINCT H.ID_HAKMILIK  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' )"
					+ " AND H.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " 	 ORDER BY LPAD(H.NO_SUBJAKET,10) asc 	";

			myLogger.info("SENARAI HAKMILIK PENARIKAN" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT") != null) {
					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} 
				else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
				}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					
					}

			
				h.put("LUAS_LOT_EDIT", rs.getString("LUAS_LOT") == null ? ""
						: rs.getDouble("LUAS_LOT"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));

				
				
				senarai_hakmilik_penarikan.addElement(h);
			}
			return senarai_hakmilik_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
////////////////////////////
	Vector senarai_hakmilik_batal_penarikan = null;
	public Vector senarai_hakmilik_batal_penarikan(String id_penarikan)
			throws Exception {
		senarai_hakmilik_batal_penarikan = new Vector();
		Db db = null;
		senarai_hakmilik_batal_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET, " +
			" CASE WHEN " 
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102700' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 "
			+" THEN 'SELESAI' " 
			+" WHEN "
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102700' AND ID_HAKMILIK = H.ID_HAKMILIK) = 0 "
			+" AND (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102699' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 " 
			+" THEN 'PENYEDIAAN LAPORAN' "
			+"ELSE 'BELUM SELESAI' END AS STATUS_LAPORAN, "	
			
			+" CASE WHEN " 
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102705' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 "
			+" THEN 'SELESAI' ELSE 'BELUM SELESAI' END AS STATUS_BAYAR, "	
			
			
			+"PH.FLAG_WARTA,PH.ID_PENARIKANHAKMILIK,PH.LUAS_LOT_TARIK,H.LUAS_AMBIL,PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PENARIKANBALIK,H.NO_PT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL   "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' "
					+ "  ORDER BY LPAD(H.NO_SUBJAKET,10) ASC ";
			System.out.println("SENARAI SUDAH PENARIKAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("STATUS_LAPORAN", rs.getString("STATUS_LAPORAN") == null ? ""
						: rs.getString("STATUS_LAPORAN"));
				
				h.put("STATUS_BAYAR", rs.getString("STATUS_BAYAR") == null ? ""
						: rs.getString("STATUS_BAYAR"));
				
				h.put("FLAG_WARTA", rs.getString("FLAG_WARTA") == null ? ""
						: rs.getString("FLAG_WARTA"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PENARIKANHAKMILIK") == null ? "" : rs
						.getString("ID_PENARIKANHAKMILIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL_ASAL") == null ? "" : rs
										.getDouble("LUAS_AMBIL_ASAL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL_ASAL") == null ? "" : rs
										.getDouble("LUAS_AMBIL_ASAL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL_ASAL") == null ? ""
							: rs.getDouble("LUAS_AMBIL_ASAL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_LOT_TARIK_VALUE",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getString("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("LUAS_AMBIL_ASAL",
						rs.getString("LUAS_AMBIL_ASAL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL_ASAL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));
				
				senarai_hakmilik_batal_penarikan.addElement(h);
			}
			return senarai_hakmilik_batal_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector senarai_hakmilik_batal_penarikan_status = null;
	public Vector senarai_hakmilik_batal_penarikan_status(String id_penarikan)
			throws Exception {
		senarai_hakmilik_batal_penarikan_status = new Vector();
		Db db = null;
		senarai_hakmilik_batal_penarikan_status.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET," +
	//		" CASE WHEN SHM.ID_SUBURUSANSTATUS IS NOT NULL AND SHM.ID_SUBURUSANSTATUS = '16102700' THEN 'SELESAI' ELSE 'BELUM SELESAI' END AS STATUS_LAPORAN, "+
					"PH.FLAG_WARTA,PH.ID_PENARIKANHAKMILIK,PH.LUAS_LOT_TARIK,H.LUAS_AMBIL,PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PENARIKANBALIK,H.NO_PT," +
				    " CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL,TBLRUJSUBURUSANSTATUSHAKMILIK SHM   "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+" AND SHM.ID_HAKMILIK(+) = H.ID_HAKMILIK "
					+" AND ((SHM.ID_SUBURUSANSTATUS IS NOT NULL AND SHM.ID_SUBURUSANSTATUS = '16102700') "
					+" ) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' "
					+ " ORDER BY LPAD(H.NO_SUBJAKET,10) ASC ";
			myLogger.info("SENARAI SUDAH PENARIKAN STATUS :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
			//	h.put("STATUS_LAPORAN", rs.getString("STATUS_LAPORAN") == null ? ""
			//			: rs.getString("STATUS_LAPORAN"));
				
				h.put("FLAG_WARTA", rs.getString("FLAG_WARTA") == null ? ""
						: rs.getString("FLAG_WARTA"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PENARIKANHAKMILIK") == null ? "" : rs
						.getString("ID_PENARIKANHAKMILIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_LOT_TARIK_VALUE",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getString("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("LUAS_AMBIL_ASAL",
						rs.getString("LUAS_AMBIL_ASAL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL_ASAL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));
				
				senarai_hakmilik_batal_penarikan_status.addElement(h);
			}
			return senarai_hakmilik_batal_penarikan_status;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	Vector senarai_hakmilik_batal_penarikan_maklumat = null;

	public Vector senarai_hakmilik_batal_penarikan_maklumat(String id_penarikan,String id_hakmilik)
			throws Exception {
		senarai_hakmilik_batal_penarikan_maklumat = new Vector();
		Db db = null;
		senarai_hakmilik_batal_penarikan_maklumat.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET, " +
			" CASE WHEN " 
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102700' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 "
			+" THEN 'SELESAI' " 
			+" WHEN "
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102700' AND ID_HAKMILIK = H.ID_HAKMILIK) = 0 "
			+" AND (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102699' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 " 
			+" THEN 'PENYEDIAAN LAPORAN' "
			+"ELSE 'BELUM SELESAI' END AS STATUS_LAPORAN, "	
			+" CASE WHEN " 
			+" (SELECT COUNT(DISTINCT ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE ID_SUBURUSANSTATUS = '16102705' AND ID_HAKMILIK = H.ID_HAKMILIK) > 0 "
			+" THEN 'SELESAI' ELSE 'BELUM SELESAI' END AS STATUS_BAYAR, "	
			
			+"PH.ID_PENARIKANHAKMILIK,PH.LUAS_LOT_TARIK,H.LUAS_AMBIL,PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PENARIKANBALIK,H.NO_PT," +
			//		"H.NO_LOT, "
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH, H.NO_SYIT, K.KETERANGAN AS KATEGORI_TANAH "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL, TBLRUJKATEGORI K  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_KATEGORITANAH = K.ID_KATEGORI"
					+ " AND PH.ID_HAKMILIK = '"+id_hakmilik+"' "					
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' "
					+ " ORDER BY LPAD(H.NO_SUBJAKET,10) ASC ";
			myLogger.info("SENARAI SUDAH PENARIKAN X :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("STATUS_BAYAR", rs
						.getString("STATUS_BAYAR") == null ? "" : rs
						.getString("STATUS_BAYAR"));
				h.put("STATUS_LAPORAN", rs
						.getString("STATUS_LAPORAN") == null ? "" : rs
						.getString("STATUS_LAPORAN"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PENARIKANHAKMILIK") == null ? "" : rs
						.getString("ID_PENARIKANHAKMILIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				h.put("NO_SYIT",
						rs.getString("NO_SYIT") == null ? "" : rs
								.getString("NO_SYIT"));
				h.put("KATEGORI_TANAH",
						rs.getString("KATEGORI_TANAH") == null ? "" : rs
								.getString("KATEGORI_TANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				
				
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_LOT_TARIK_VALUE",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getString("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("LUAS_AMBIL_ASAL",
						rs.getString("LUAS_AMBIL_ASAL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL_ASAL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				senarai_hakmilik_batal_penarikan_maklumat.addElement(h);
			}
			return senarai_hakmilik_batal_penarikan_maklumat;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	Vector senarai_pihak_penting = null;

	public Vector senarai_pihak_penting(String id_permohonan) throws Exception {
		senarai_pihak_penting = new Vector();
		Db db = null;
		senarai_pihak_penting.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT PB1.NAMA_PB,PB1.ID_JENISPB,H1.ID_HAKMILIK "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIKPB B1,TBLPPTHAKMILIK H1 "
					+ " WHERE B1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
					+ " AND B1.ID_HAKMILIK = H1.ID_HAKMILIK "
					+ " AND NVL(B1.ID_JENISPB,0) NOT IN ('40','41','42') "
					+

					// " AND NVL(H1.FLAG_PEMBATALAN,' ') <> 'Y' "+
					" AND H1.ID_PERMOHONAN = '" + id_permohonan + "' "
					+ " ORDER BY NAMA_PB ASC ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				senarai_pihak_penting.addElement(h);
			}
			return senarai_pihak_penting;
		} finally {
			if (db != null)
				db.close();
		}
	}

	/*
	 * public void batalkan(String id_hakmilik,String id_Masuk,String
	 * id_pembatalan) throws Exception { Db db = null; String sql = ""; try { db
	 * = new Db(); Statement stmt = db.getStatement(); SQLRenderer r = new
	 * SQLRenderer(); r.update("ID_HAKMILIK", id_hakmilik);
	 * r.add("FLAG_PEMBATALAN", "Y"); r.add("ID_PEMBATALAN", id_pembatalan);
	 * r.add("ID_KEMASKINI", id_Masuk); r.add("TARIKH_KEMASKINI",
	 * r.unquote("sysdate")); sql = r.getSQLUpdate("tblppthakmilik");
	 * 
	 * myLogger.info("SQL UPDATE BATALKAN :"+sql);
	 * stmt.executeUpdate(sql);
	 * 
	 * } finally { if (db != null) db.close(); }
	 * 
	 * }
	 */

	public void batalkan(String id_hakmilik, String lot_tarik, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PEMBATALAN", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void batalkan_lottarik(String id_hakmilik, String lot_tarik,
			String id_Masuk, String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			// r.add("FLAG_PENARIKAN_BALIK", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void penarikan_lot(String id_hakmilik, String lot_tarik,
			String id_Masuk, String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PENARIKAN_BALIK", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void pilih_penarikan(String id_kat,String id_hakmilik, String lot_tarik,
			String lot_ambil, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		
		myLogger.info("lot_tarik"+lot_tarik);
		
		
		
		
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql6 = "";
		try {
			double l_ambil = 0.00;
			double l_tarik = 0.00;
			
			if(!lot_ambil.equals(""))
			{
			l_ambil = Double.parseDouble(lot_ambil);
			}
			
			if(!lot_tarik.equals(""))
			{
			l_tarik = Double.parseDouble(lot_tarik);
			}
			
						
			double jumlah_luas = l_ambil - l_tarik;
		    String flag_warta = "";
		    
			if(id_kat.equals("2"))
			{
				if((l_tarik > 0.25) || (l_tarik/l_ambil > 0.01))
				{
					flag_warta = "Y";
				}				
				
			}
			else
			{
				if((l_tarik > 2500) || (l_tarik/l_ambil > 0.01))
				{
					flag_warta = "Y";
				}	
			}

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_HAKMILIK", id_hakmilik);
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("LUAS_AMBIL", l_ambil);
			r.add("LUAS_TINGGAL", "");
			r.add("ID_MASUK", id_Masuk);
			r.add("FLAG_WARTA", flag_warta);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptpenarikanhakmilik");
			myLogger.info("SQL INSET LOT PENARIKANHAKMILIK :"
					+ sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", jumlah_luas);
			
			if(jumlah_luas == 0)
			{
				r1.add("FLAG_PENARIKAN_KESELURUHAN", "Y");
			}	
			else 
			{
				r1.add("FLAG_PENARIKAN_KESELURUHAN", "");
			}	
			
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1);
			stmt1.executeUpdate(sql1);
			
			
			
			
			
			  

			
			

		} finally {
			if (db != null)
				db.close();
		}

	}

	
	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	public void update_pilih_penarikan(String id_kat,String id_penarikanhakmilik,
			String id_hakmilik, String lot_tarik, String lot_ambil,
			String lot_ambil_asal, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";

		if (lot_tarik.equals("")) {
			lot_tarik = "0.0";
		}
		if (lot_ambil.equals("")) {
			lot_ambil = "0.0";
		}
		if (lot_ambil_asal.equals("")) {
			lot_ambil_asal = "0.0";
		}
		
		

		try {

			double jl = 0.0;
			db = new Db();

			Statement stmt2 = db.getStatement();
			sql2 = "SELECT NVL(LUAS_LOT_TARIK,'0') AS LUAS_LOT_TARIK FROM TBLPPTPENARIKANHAKMILIK WHERE ID_PENARIKANHAKMILIK = '"
					+ id_penarikanhakmilik + "'";
			ResultSet rs = stmt2.executeQuery(sql2);
			while (rs.next()) {
				jl = rs.getString("LUAS_LOT_TARIK") == null ? 0.0 : rs
						.getDouble("LUAS_LOT_TARIK");
			}

			double l_ambil = Double.parseDouble(lot_ambil);
			double lot_tarik1 = Double.parseDouble(lot_tarik);
			double l_ambil_asal = Double.parseDouble(lot_ambil_asal);
			double l_tarik = Double.parseDouble(lot_tarik) - jl;
			double jumlah_luas = l_ambil - l_tarik;
			String flag_warta = "";
			
			if(id_kat.equals("2"))
			{
				if((lot_tarik1 > 0.25) || (lot_tarik1/l_ambil_asal > 0.01))
				{
					flag_warta = "Y";
				}				
				
			}
			else
			{
				if((lot_tarik1 > 2500) || (lot_tarik1/l_ambil_asal > 0.01))
				{
					flag_warta = "Y";
				}	
			}

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PENARIKANHAKMILIK", id_penarikanhakmilik);
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("LUAS_AMBIL", lot_ambil_asal);
			r.add("FLAG_WARTA", flag_warta);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpenarikanhakmilik");
			myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
					+ sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", jumlah_luas);
			if(jumlah_luas == 0)
			{
				r1.add("FLAG_PENARIKAN_KESELURUHAN", "Y");
			}
			else 
			{
				r1.add("FLAG_PENARIKAN_KESELURUHAN", "");
			}	
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("**UPDATE SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void update_pilih_pembatalan(String id_kat,String id_penarikanhakmilik,
			String id_hakmilik, String lot_tarik, String lot_ambil,
			String lot_ambil_asal, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";

		if (lot_tarik.equals("")) {
			lot_tarik = "0.0";
		}
		if (lot_ambil.equals("")) {
			lot_ambil = "0.0";
		}
		if (lot_ambil_asal.equals("")) {
			lot_ambil_asal = "0.0";
		}
		
		

		try {

			double jl = 0.0;
			db = new Db();

			Statement stmt2 = db.getStatement();
			sql2 = "SELECT NVL(LUAS_LOT_TARIK,'0') AS LUAS_LOT_TARIK FROM TBLPPTPEMBATALANHAKMILIK WHERE ID_PEMBATALANHAKMILIK = '"
					+ id_penarikanhakmilik + "'";
			ResultSet rs = stmt2.executeQuery(sql2);
			while (rs.next()) {
				jl = rs.getString("LUAS_LOT_TARIK") == null ? 0.0 : rs
						.getDouble("LUAS_LOT_TARIK");
			}

			double l_ambil = Double.parseDouble(lot_ambil);
			double lot_tarik1 = Double.parseDouble(lot_tarik);
			double l_ambil_asal = Double.parseDouble(lot_ambil_asal);
			double l_tarik = Double.parseDouble(lot_tarik) - jl;
			double jumlah_luas = l_ambil - l_tarik;
			String flag_warta = "";
			
			if(id_kat.equals("2"))
			{
				if((lot_tarik1 > 0.25) || (lot_tarik1/l_ambil_asal > 0.01))
				{
					flag_warta = "Y";
				}				
				
			}
			else
			{
				if((lot_tarik1 > 2500) || (lot_tarik1/l_ambil_asal > 0.01))
				{
					flag_warta = "Y";
				}	
			}

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PEMBATALANHAKMILIK", id_penarikanhakmilik);
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("LUAS_AMBIL", lot_ambil_asal);
			//r.add("FLAG_WARTA", flag_warta);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpembatalanhakmilik");
			myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
					+ sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", jumlah_luas);
			r1.add("ID_KEMASKINI", id_Masuk);
			
			if(jumlah_luas == 0)
			{
				r1.add("FLAG_PEMBATALAN_KESELURUHAN", "Y");
			}	
			else 
			{
				r1.add("FLAG_PEMBATALAN_KESELURUHAN", "");
			}	
			
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("**UPDATE SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	
	public void hapus_update_pilih_penarikan(String id_penarikanhakmilik,
			String id_hakmilik, String lot_tarik, String lot_ambil,
			String lot_ambil_asal, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		if (lot_tarik.equals("") || lot_tarik.equals(null)) {
			lot_tarik = "0.0";
		}

		try {
			double jl = 0.0;
			double undo_luasbail = 0.0;
			
			
			db = new Db();
						
			Statement stmt2 = db.getStatement();
			sql2 = "SELECT NVL(LUAS_AMBIL,'0') AS LUAS_AMBIL FROM TBLPPTHAKMILIK WHERE ID_HAKMILIK = '"
					+ id_hakmilik + "'";
			ResultSet rs = stmt2.executeQuery(sql2);
			while (rs.next()) {
				jl = rs.getString("LUAS_AMBIL") == null ? 0.0 : rs
						.getDouble("LUAS_AMBIL");
			}

		
			undo_luasbail = Double.parseDouble(lot_tarik) + jl;

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", undo_luasbail);
			//r1.add("LUAS_AMBIL", undo_luasbail);
			r1.add("FLAG_PENARIKAN_KESELURUHAN","");
			r1.add("FLAG_PEMBATALAN_KESELURUHAN","");
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("**UPDATE SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void hapus_pilih_penarikan(String id_penarikanhakmilik)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		try {

			db = new Db();
			
			
			
			Statement stmt1 = db.getStatement();
			sql1 = "DELETE FROM TBLPPTSIASATAN WHERE ID_HAKMILIK =(SELECT ID_HAKMILIK FROM TBLPPTPENARIKANHAKMILIK WHERE ID_PENARIKANHAKMILIK = "
					+ id_penarikanhakmilik+") AND ID_PENARIKANBALIK = (SELECT ID_PENARIKANBALIK FROM TBLPPTPENARIKANHAKMILIK WHERE ID_PENARIKANHAKMILIK = "
					+ id_penarikanhakmilik+")";
			myLogger.info("**DELETE SQL SIASATAN :"
					+ sql1);
			stmt1.executeUpdate(sql1);
			
			

			Statement stmt = db.getStatement();
			sql = "DELETE FROM TBLPPTPENARIKANHAKMILIK WHERE ID_PENARIKANHAKMILIK = "
					+ id_penarikanhakmilik;
			myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
					+ sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void hapus_pilih_pembatalan(String id_penarikanhakmilik)
	throws Exception {
Db db = null;
String sql = "";

try {

	db = new Db();

	Statement stmt = db.getStatement();
	sql = "DELETE FROM TBLPPTPEMBATALANHAKMILIK WHERE ID_PEMBATALANHAKMILIK = "
			+ id_penarikanhakmilik;
	myLogger.info("**UPDATE SQL INSERT LOT PENARIKANHAKMILIK :"
			+ sql);
	stmt.executeUpdate(sql);

} finally {
	if (db != null)
		db.close();
}

}

	
	public void batalkan_penarikan(String id_hakmilik, String lot_tarik,
			String id_Masuk, String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PENARIKAN_BALIK", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void batalkan_penarikan_lottarik(String id_hakmilik,
			String lot_tarik, String id_Masuk, String id_pembatalan)
			throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			// r.add("FLAG_PENARIKAN_BALIK", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void undobatalkan(String id_hakmilik, String lot_tarik,
			String id_Masuk) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PEMBATALAN", "");
			
			
				r.add("FLAG_PEMBATALAN_KESELURUHAN", "");
			
			
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void undobatalkan_penarikan(String id_hakmilik, String lot_tarik,
			String id_Masuk) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PENARIKAN_BALIK", "");
			r.add("FLAG_PENARIKAN_KESELURUHAN", "");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void undobatalkan_lottarik(String id_hakmilik, String lot_tarik,
			String id_Masuk) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			// r.add("FLAG_PENARIKAN_BALIK", "");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void undobatalkan_penarikan_lottarik(String id_hakmilik,
			String lot_tarik, String id_Masuk) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			// r.add("FLAG_PENARIKAN_BALIK", "");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	/*
	 * Vector listDokumen = new Vector(); public Vector senarai_dokumen(String
	 * id_pembatalan)throws Exception { Vector key = listDokumen; Element
	 * cachedObject = myCache.get(key);
	 * myLogger.info("GET CACHED:"+myCache.get(key)); if (cachedObject != null)
	 * { myLogger.info("CACHED 1");
	 * myLogger.info("GET CACHED RETURN :"+(Vector)cachedObject
	 * .getObjectValue()); return (Vector)cachedObject.getObjectValue(); } else
	 * { myLogger.info("CACHED 2"); Db db = null; listDokumen.clear(); String
	 * sql = "";
	 * 
	 * try { db = new Db(); Statement stmt = db.getStatement(); sql =
	 * "SELECT A.ID_PEMBATALAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
	 * +
	 * " A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTPEMBATALAN P WHERE A.ID_PEMBATALAN = '"
	 * +id_pembatalan+"'  "+ " AND A.ID_PEMBATALAN = P.ID_PEMBATALAN";
	 * myLogger.info("SQL DOKUMEN :"+sql); ResultSet rs =
	 * stmt.executeQuery(sql);
	 * 
	 * Hashtable h; int bil = 0;
	 * 
	 * while (rs.next()) {
	 * 
	 * bil = bil + 1; h = new Hashtable(); h.put("BIL", bil);
	 * h.put("ID_PEMBATALAN",rs.getString("ID_PEMBATALAN")==
	 * null?"":rs.getString("ID_PEMBATALAN")); h.put("ID_DOKUMEN",
	 * rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
	 * h.put("NAMA_FAIL", rs.getString("NAMA_FAIL")==
	 * null?"":rs.getString("NAMA_FAIL"));
	 * h.put("JENIS_MIME",rs.getString("JENIS_MIME")==
	 * null?"":rs.getString("JENIS_MIME"));
	 * h.put("TAJUK",rs.getString("TAJUK")== null?"":rs.getString("TAJUK"));
	 * h.put("KETERANGAN",rs.getString("KETERANGAN")==
	 * null?"":rs.getString("KETERANGAN"));
	 * 
	 * listDokumen.addElement(h);
	 * 
	 * } myCache.put(new Element(key, listDokumen)); return listDokumen;
	 * 
	 * } finally { if (db != null) db.close(); }
	 * 
	 * } }
	 */

	Vector listDokumen = null;

	public Vector senarai_dokumen(String id_pembatalan) throws Exception {
		listDokumen = new Vector();
		Db db = null;
		listDokumen.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_PEMBATALAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
					+ " A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTPEMBATALAN P WHERE A.ID_PEMBATALAN = '"
					+ id_pembatalan
					+ "'  "
					+ " AND A.ID_PEMBATALAN = P.ID_PEMBATALAN";
			myLogger.info("SQL DOKUMEN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
						.getString("NAMA_FAIL"));
				h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				listDokumen.addElement(h);

			}

			return listDokumen;

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector listDokumen_penarikan = null;

	public Vector senarai_dokumen_penarikan(String id_pembatalan)
			throws Exception {
		listDokumen_penarikan = new Vector();
		Db db = null;
		listDokumen_penarikan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_PENARIKANBALIK,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
					+ " A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTPENARIKANBALIK P WHERE A.ID_PENARIKANBALIK = '"
					+ id_pembatalan
					+ "'  "
					+ " AND A.ID_PENARIKANBALIK = P.ID_PENARIKANBALIK";
			myLogger.info("SQL DOKUMEN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
						.getString("NAMA_FAIL"));
				h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				listDokumen_penarikan.addElement(h);

			}

			return listDokumen_penarikan;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector listDokumen_pembatalan = null;

	public Vector senarai_dokumen_pembatalan(String id_pembatalan)
			throws Exception {
		listDokumen_pembatalan = new Vector();
		Db db = null;
		listDokumen_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_PEMBATALAN,A.ID_DOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
					+ " A.CONTENT  FROM TBLPPTDOKUMEN A,TBLPPTPEMBATALAN P WHERE A.ID_PEMBATALAN = '"
					+ id_pembatalan
					+ "'  "
					+ " AND A.ID_PEMBATALAN = P.ID_PEMBATALAN";
			myLogger.info("SQL DOKUMEN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
						.getString("NAMA_FAIL"));
				h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				listDokumen_pembatalan.addElement(h);

			}

			return listDokumen_pembatalan;

		} finally {
			if (db != null)
				db.close();
		}

	}


	public void deleteTanahNilaian(String id_tanah) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTTANAH WHERE ID_TANAH = " + id_tanah;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deleteMMKPenyediaan(String id_mmk) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTMMK WHERE ID_MMK = " + id_mmk;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deleteMMKKeputusan(String id_mmkkeputusan) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTMMKKEPUTUSAN WHERE ID_MMK = "
					+ id_mmkkeputusan;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void deleteDokumen(String id_dokumen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTDOKUMEN WHERE ID_DOKUMEN = "
					+ id_dokumen;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector view_details_dokumen = null;

	public Vector view_details_dokumen(String id_dokumen) throws Exception {
		view_details_dokumen = new Vector();
		Db db = null;
		view_details_dokumen.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_DOKUMEN,TAJUK,KETERANGAN,NAMA_FAIL,CONTENT,JENIS_MIME,NO_RUJUKAN,ID_PEMBATALAN FROM TBLPPTDOKUMEN "
					+ " WHERE ID_DOKUMEN = '" + id_dokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("ID_DOKUMEN", rs.getString("ID_DOKUMEN") == null ? ""
						: rs.getString("ID_DOKUMEN"));
				h.put("NAMA_FAIL", rs.getString("NAMA_FAIL") == null ? "" : rs
						.getString("NAMA_FAIL"));
				h.put("JENIS_MIME", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("TAJUK", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				view_details_dokumen.addElement(h);

			}
			return view_details_dokumen;
		} finally {
			if (db != null)
				db.close();
		}

	}

	// create seq no fail penarikan balik/ pnjam seq aduan dulu

	public static synchronized int getSeqNoAduanOnline(int id_jenisaduan)
			throws DbException {
		return getSeqNoAduan(id_jenisaduan);
	}

	public static synchronized int getSeqNoAduan(int id_jenisaduan)
			throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQADUAN WHERE ");
			sb.append("ID_JENISADUAN = " + id_jenisaduan);

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseSeqAduan(id_jenisaduan);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNewAduan(id_jenisaduan);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}

	public static void increaseSeqAduan(int id_jenisaduan) throws DbException {

		Db db = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQADUAN  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append("id_jenisaduan = '" + id_jenisaduan + "'");

		try {
			db = new Db();
			try {
				db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addNewAduan(int id_jenisaduan) throws DbException {

		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQADUAN (ID_JENISADUAN,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + id_jenisaduan + "'");
		sb.append(",1)"); // initial value

		try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	// TBLRUJSEQNO_PENARIKAN_BALIK

	public static synchronized int getSeqNo_PB(int tahun) throws DbException {
		return getSeqNoAduan(tahun);
	}

	public static synchronized int getSeqNoPB(int tahun) throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb
					.append("SELECT NO_TURUTAN FROM TBLRUJSEQNO_PENARIKAN_BALIK WHERE ");
			sb.append("TAHUN = " + tahun);

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			if (found) {
				// f.increaseSeqPB(tahun);
				increaseSeqPB(tahun);
			} else {
				// f.addNewPB(tahun);
				addNewPB(tahun);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}

	public static void increaseSeqPB(int tahun) throws DbException {

		Db db = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQNO_PENARIKAN_BALIK  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append("tahun = '" + tahun + "'");

		try {
			db = new Db();
			try {
				db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addNewPB(int tahun) throws DbException {

		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQNO_PENARIKAN_BALIK (TAHUN,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + tahun + "'");
		sb.append(",1)"); // initial value

		try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	// get seq no subjaket untuk ppt hakmilik

	public static synchronized int getHakmilik_Subjaket_PPT(int id_permohonan)
			throws DbException {
		return getSeqHakmilik_Subjaket(id_permohonan);
	}

	public static synchronized int getSeqHakmilik_Subjaket(int id_permohonan)
			throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb
					.append("SELECT NO_TURUTAN FROM TBLRUJSEQSUBJAKET_HAKMILIK WHERE ");
			sb.append("ID_PERMOHONAN = " + id_permohonan);

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			if (found) {
				// f.increaseSeqHakmilik_Subjaket(id_permohonan);
				increaseSeqHakmilik_Subjaket(id_permohonan);
			} else {
				// f.addNewHakmilik_Subjaket(id_permohonan);
				addNewHakmilik_Subjaket(id_permohonan);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}

	public static void increaseSeqHakmilik_Subjaket(int id_permohonan)
			throws DbException {

		Db db = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQSUBJAKET_HAKMILIK  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append("id_permohonan = '" + id_permohonan + "'");

		try {
			db = new Db();
			try {
				db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addNewHakmilik_Subjaket(int id_permohonan)
			throws DbException {

		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb
				.append("INSERT INTO TBLRUJSEQSUBJAKET_HAKMILIK (ID_PERMOHONAN,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + id_permohonan + "'");
		sb.append(",1)"); // initial value

		try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_tambahan = null;

	public Vector maklumat_tambahan(String id_permohonan) throws Exception {
		maklumat_tambahan = new Vector();
		Db db = null;
		maklumat_tambahan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN,F.NO_FAIL,B.NO_PENARIKANBALIK,K.NAMA_KEMENTERIAN,P.NO_RUJUKAN_SURAT "
					+ "FROM TBLPPTPERMOHONAN P,TBLPPTPENARIKANBALIK B,TBLPFDFAIL F,TBLRUJKEMENTERIAN K "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)"
					+ "AND P.ID_PERMOHONAN = '" + id_permohonan + "'";
			myLogger.info("SQL MALUMAT TAMBAHAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "-" : rs
						.getString("NO_FAIL"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "-" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "-" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("NO_PENARIKANBALIK",
						rs.getString("NO_PENARIKANBALIK") == null ? "-" : rs
								.getString("NO_PENARIKANBALIK"));
				h.put("TARIKH", cd);

				maklumat_tambahan.addElement(h);
			}
			return maklumat_tambahan;
		} finally {
			if (db != null)
				db.close();
		}
	}


	Vector maklumat_tambahan_pembatalan = null;

	public Vector maklumat_tambahan_pembatalan(String id_permohonan) throws Exception {
		maklumat_tambahan_pembatalan = new Vector();
		Db db = null;
		maklumat_tambahan_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT P.ID_PERMOHONAN,F.NO_FAIL,B.NO_PEMBATALAN,K.NAMA_KEMENTERIAN,P.NO_RUJUKAN_SURAT "
					+ "FROM TBLPPTPERMOHONAN P,TBLPPTPEMBATALAN B,TBLPFDFAIL F,TBLRUJKEMENTERIAN K "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND P.ID_FAIL = F.ID_FAIL "
					+ "AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+)"
					+ "AND P.ID_PERMOHONAN = '" + id_permohonan + "'";
			myLogger.info("SQL MALUMAT TAMBAHAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "-" : rs
						.getString("NO_FAIL"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "-" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("NO_RUJUKAN_SURAT",
						rs.getString("NO_RUJUKAN_SURAT") == null ? "-" : rs
								.getString("NO_RUJUKAN_SURAT"));
				h.put("NO_PENARIKANBALIK",
						rs.getString("NO_PEMBATALAN") == null ? "-" : rs
								.getString("NO_PEMBATALAN"));
				h.put("TARIKH", cd);

				maklumat_tambahan_pembatalan.addElement(h);
			}
			return maklumat_tambahan_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}


	
	Vector maklumat_penyediaan = null;

	public Vector maklumat_penyediaan(String id_penarikan) throws Exception {
		maklumat_penyediaan = new Vector();
		Db db = null;
		maklumat_penyediaan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT M.JENIS_WAKTU_SIDANG,M.WAKTU_SIDANG,M.TARIKH_SIDANG,M.TEMPAT_SIDANG,M.KETERANGAN_SIDANG," +
					"M.BUTIR_TANAH,B.SEBAB_PENARIKANBALIK,M.FLAG_MMK,M.NAMA_TUAN_TANAH,M.KEPUTUSAN,M.TAJUK,M.ULASAN_PENGARAH,M.SEBAB_PENARIKAN,M.ID_MMK,M.ID_PENARIKANBALIK,M.JENIS_MMK,M.ULASAN,M.NO_RUJMMK,M.FLAG_SEMAK,"
					+ " M.ID_SEMAK,M.TARIKH_SEMAK,M.FLAG_BORANGI,M.ID_PERMOHONAN, M.TUJUAN,M.LATARBELAKANG,"
					+ " M.ASAS_PERTIMBANGAN,M.KESIMPULAN,M.SYOR,M.KEDUDUKAN_LAPORAN_TNH,M.PENGESAHAN_PERUNTUKAN,"
					+ " M.PANDANGAN,M.IMPLIKASI, M.ULASAN_JABTEKNIKAL,M.PERIHAL_TANAH,M.PERIHAL_POHON,M.ANGGARAN_KOS,"
					+ " M.PERAKUAN_PENTADBIR_TNH,M.NILAI_ATAS_TANAH,M.TARIKH_MMK,M.STATUS_SEMAKAN "
					+ " FROM TBLPPTMMK M,TBLPPTPENARIKANBALIK B, TBLPPTPERMOHONAN P  "
					+ " WHERE M.ID_PENARIKANBALIK = B.ID_PENARIKANBALIK "
					+ " AND B.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND B.ID_PENARIKANBALIK = '" + id_penarikan + "'";

			myLogger.info("PENYEDIAAN:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
	///sql = "SELECT M.JENIS_WAKTU_SIDANG,M.WAKTU_SIDANG,M.TARIKH_SIDANG,M.TEMPAT_SIDANG,M.KETERANGAN_SIDANG," +
				
				h.put("JENIS_WAKTU_SIDANG", rs.getString("JENIS_WAKTU_SIDANG") == null ? "" : rs
						.getString("JENIS_WAKTU_SIDANG"));				
				h.put("WAKTU_SIDANG", rs.getString("WAKTU_SIDANG") == null ? "" : rs
						.getString("WAKTU_SIDANG"));				
				h.put("TARIKH_SIDANG", rs.getString("TARIKH_SIDANG") == null ? ""
						: Format.format(rs.getDate("TARIKH_SIDANG")));				
				h.put("TEMPAT_SIDANG", rs.getString("TEMPAT_SIDANG") == null ? "" : rs
						.getString("TEMPAT_SIDANG"));				
				h.put("KETERANGAN_SIDANG", rs.getString("KETERANGAN_SIDANG") == null ? "" : rs
						.getString("KETERANGAN_SIDANG"));			
				
				
				h.put("BUTIR_TANAH", rs.getString("BUTIR_TANAH") == null ? "" : rs
						.getString("BUTIR_TANAH"));
				h.put("ID_MMK", rs.getString("ID_MMK") == null ? "" : rs
						.getString("ID_MMK"));
				h.put("FLAG_MMK",
						rs.getString("FLAG_MMK") == null ? "" : rs
								.getString("FLAG_MMK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("JENIS_MMK", rs.getString("JENIS_MMK") == null ? "" : rs
						.getString("JENIS_MMK"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
						.getString("ULASAN"));
				h.put("NO_RUJMMK", rs.getString("NO_RUJMMK") == null ? "" : rs
						.getString("NO_RUJMMK"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_BORANGI", rs.getString("FLAG_BORANGI") == null ? ""
						: rs.getString("FLAG_BORANGI"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("TUJUAN", rs.getString("TUJUAN") == null ? "" : rs
						.getString("TUJUAN"));
				h.put("LATARBELAKANG",
						rs.getString("LATARBELAKANG") == null ? "" : rs
								.getString("LATARBELAKANG"));
				h.put("ASAS_PERTIMBANGAN",
						rs.getString("ASAS_PERTIMBANGAN") == null ? "" : rs
								.getString("ASAS_PERTIMBANGAN"));
				h.put("KESIMPULAN", rs.getString("KESIMPULAN") == null ? ""
						: rs.getString("KESIMPULAN"));
				h.put("SYOR", rs.getString("SYOR") == null ? "" : rs
						.getString("SYOR"));
				h.put("KEDUDUKAN_LAPORAN_TNH", rs
						.getString("KEDUDUKAN_LAPORAN_TNH") == null ? "" : rs
						.getString("KEDUDUKAN_LAPORAN_TNH"));
				h.put("PENGESAHAN_PERUNTUKAN", rs
						.getString("PENGESAHAN_PERUNTUKAN") == null ? "" : rs
						.getString("PENGESAHAN_PERUNTUKAN"));
				h.put("PANDANGAN", rs.getString("PANDANGAN") == null ? "" : rs
						.getString("PANDANGAN"));
				h.put("IMPLIKASI", rs.getString("IMPLIKASI") == null ? "" : rs
						.getString("IMPLIKASI"));
				h.put("ULASAN_JABTEKNIKAL",
						rs.getString("ULASAN_JABTEKNIKAL") == null ? "" : rs
								.getString("ULASAN_JABTEKNIKAL"));
				h.put("PERIHAL_TANAH",
						rs.getString("PERIHAL_TANAH") == null ? "" : rs
								.getString("PERIHAL_TANAH"));
				h.put("PERIHAL_POHON",
						rs.getString("PERIHAL_POHON") == null ? "" : rs
								.getString("PERIHAL_POHON"));
				h.put("ANGGARAN_KOS", rs.getString("ANGGARAN_KOS") == null ? ""
						: rs.getString("ANGGARAN_KOS"));
				h.put("PERAKUAN_PENTADBIR_TNH", rs
						.getString("PERAKUAN_PENTADBIR_TNH") == null ? "" : rs
						.getString("PERAKUAN_PENTADBIR_TNH"));
				h.put("NILAI_ATAS_TANAH",
						rs.getString("NILAI_ATAS_TANAH") == null ? "" : rs
								.getString("NILAI_ATAS_TANAH"));
				h.put("SEBAB_PENARIKAN",
						rs.getString("SEBAB_PENARIKAN") == null ? "" : rs
								.getString("SEBAB_PENARIKAN"));
				h.put("STATUS_SEMAKAN",
						rs.getString("STATUS_SEMAKAN") == null ? "" : rs
								.getString("STATUS_SEMAKAN"));
				h.put("TARIKH_SEMAK", rs.getString("TARIKH_SEMAK") == null ? ""
						: Format.format(rs.getDate("TARIKH_SEMAK")));
				h.put("TARIKH_MMK", rs.getString("TARIKH_MMK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MMK")));
				h.put("SEBAB_PENARIKANBALIK", rs
						.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
						.getString("SEBAB_PENARIKANBALIK"));
				h.put("ULASAN_PENGARAH", rs
						.getString("ULASAN_PENGARAH") == null ? "" : rs
						.getString("ULASAN_PENGARAH"));
				h.put("TAJUK", rs
						.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("NAMA_TUAN_TANAH", rs
						.getString("NAMA_TUAN_TANAH") == null ? "" : rs
						.getString("NAMA_TUAN_TANAH"));
				h.put("KEPUTUSAN", rs
						.getString("KEPUTUSAN") == null ? "" : rs
						.getString("KEPUTUSAN"));
				
				maklumat_penyediaan.addElement(h);
			}
			return maklumat_penyediaan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addPenyediaanMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");

		String id_mmk = (String) data.get("id_mmk");
		String txtTujuan = (String) data.get("txtTujuan");
		String txtTajuk = (String) data.get("txtTajuk");
		String txtLatarbelakang = (String) data.get("txtLatarbelakang");		
		String txtImplikasi = (String) data.get("txtImplikasi");
		String txtUlasan = (String) data.get("txtUlasan");
		String txtSyor = (String) data.get("txtSyor");		
		String txtAsasPertimbangan = (String) data.get("txtAsasPertimbangan");
		String txtNilaiAtasTanah = (String) data.get("txtNilaiAtasTanah");
		String txtSebabPenarikan = (String) data.get("txtSebabPenarikan");
		String txtPerihalTanah = (String) data.get("txtPerihalTanah");
		String txtKesimpulan = (String) data.get("txtKesimpulan");
		String txtUlasanPengarah = (String) data.get("txtUlasanPengarah");		
		String txtKeputusan = (String) data.get("txtKeputusan");
		String txtPerihalPohon = (String) data.get("txtPerihalPohon");
		String txtNamaTuan = (String) data.get("txtNamaTuan");
		String txtPerakuanPentadbir = (String) data.get("txtPerakuanPentadbir");		
		String txtPeruntukan = (String) data.get("txtPeruntukan");
		
		String txtLokasi = (String) data.get("txtLokasi");
		
		
		String txtSidang = (String) data.get("txtSidang");
		String txtTempatSidang = (String) data.get("txtTempatSidang");
		String txtTarikhSidang = (String) data.get("txtTarikhSidang");
		String txtMasaSidang = (String) data.get("txtMasaSidang");
		String jeniswaktu = (String) data.get("jeniswaktu");
		
		
		
		
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("ID_MMK", id_mmk);
			
			
			
			
			
			r.add("KETERANGAN_SIDANG", txtSidang);
			r.add("TEMPAT_SIDANG", txtTempatSidang);
			r.add("WAKTU_SIDANG", txtMasaSidang);
			r.add("JENIS_WAKTU_SIDANG", jeniswaktu);
			if (txtTarikhSidang != "") {
				r.add("TARIKH_SIDANG", r.unquote("to_date('"
						+ txtTarikhSidang + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SIDANG", "");
			}

			
			
			
			r.add("BUTIR_TANAH", txtLokasi);
			r.add("PENGESAHAN_PERUNTUKAN", txtPeruntukan);
			r.add("KEPUTUSAN", txtKeputusan);
			r.add("PERAKUAN_PENTADBIR_TNH", txtPerakuanPentadbir);
			r.add("NAMA_TUAN_TANAH", txtNamaTuan);
			r.add("PERIHAL_POHON", txtPerihalPohon);
			
			r.add("ASAS_PERTIMBANGAN", txtAsasPertimbangan);
			r.add("NILAI_ATAS_TANAH", txtNilaiAtasTanah);
			r.add("PERIHAL_TANAH", txtPerihalTanah);
			r.add("KESIMPULAN", txtKesimpulan);
			r.add("ULASAN_PENGARAH", txtUlasanPengarah);	

			r.add("TAJUK", txtTajuk);
			r.add("TUJUAN", txtTujuan);
			r.add("LATARBELAKANG", txtLatarbelakang);
			r.add("IMPLIKASI", txtImplikasi);
			//r.add("ULASAN", txtUlasan);
			r.add("SEBAB_PENARIKAN", txtSebabPenarikan);
			r.add("SYOR", txtSyor);
			
			
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptmmk");
			myLogger.info("SQL INSERT PENYEDIAAN MMK :" + sql);
			stmt.executeUpdate(sql);
/*
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PENARIKANBALIK", id_pembatalan);
			r1.add("SEBAB_PENARIKANBALIK", txtSebab);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK PENARIKAN :"
					+ sql);
			stmt1.executeUpdate(sql1);
			*/
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void addKeputusanMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String txtNoRujMMK = (String) data.get("txtNoRujMMK");
		String txdTarikhMMK = (String) data.get("txdTarikhMMK");
		String txdTarikhHantar = (String) data.get("txdTarikhHantar");
		String txdTarikhKeputusan = (String) data.get("txdTarikhKeputusan");
		String txdTarikhTerimaKeputusan = (String) data
				.get("txdTarikhTerimaKeputusan");
		String socKeputusan = (String) data.get("socKeputusan");
		String txtUlasan = (String) data.get("txtUlasan");

		String id_mmk = (String) data.get("id_mmk");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_MMK", id_mmk);
			r.add("STATUS_KEPUTUSAN", socKeputusan);
			r.add("ULASAN", txtUlasan);

			if (txdTarikhTerimaKeputusan != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaKeputusan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			if (txdTarikhKeputusan != "") {
				r.add("TARIKH_KEPUTUSAN", r.unquote("to_date('"
						+ txdTarikhKeputusan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_KEPUTUSAN", "");
			}

			if (txdTarikhHantar != "") {
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_HANTAR", "");
			}

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptmmkkeputusan");
			myLogger.info("SQL INSERT KEPUTUSAN MMK :" + sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_MMK", id_mmk);
			r1.add("NO_RUJMMK", txtNoRujMMK);
			

			if (txdTarikhMMK != "") {
				r1.add("TARIKH_MMK", r1.unquote("to_date('" + txdTarikhMMK
						+ "','dd/MM/yyyy')"));
			} else {
				r1.add("TARIKH_MMK", "");
			}

			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptmmk");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK PENARIKAN :"
					+ sql);
			stmt1.executeUpdate(sql1);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateKeputusanMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String txtNoRujMMK = (String) data.get("txtNoRujMMK");
		String id_mmkkeputusan = (String) data.get("id_mmkkeputusan");
		String txdTarikhMMK = (String) data.get("txdTarikhMMK");
		String txdTarikhHantar = (String) data.get("txdTarikhHantar");
		String txdTarikhKeputusan = (String) data.get("txdTarikhKeputusan");
		String txdTarikhTerimaKeputusan = (String) data
				.get("txdTarikhTerimaKeputusan");
		String socKeputusan = (String) data.get("socKeputusan");
		String txtUlasan = (String) data.get("txtUlasan");

		String id_mmk = (String) data.get("id_mmk");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_MMKKEPUTUSAN", id_mmkkeputusan);
			r.add("ID_MMK", id_mmk);
			r.add("STATUS_KEPUTUSAN", socKeputusan);
			r.add("ULASAN", txtUlasan);

			if (txdTarikhTerimaKeputusan != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaKeputusan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}

			if (txdTarikhKeputusan != "") {
				r.add("TARIKH_KEPUTUSAN", r.unquote("to_date('"
						+ txdTarikhKeputusan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_KEPUTUSAN", "");
			}

			if (txdTarikhHantar != "") {
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_HANTAR", "");
			}

			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptmmkkeputusan");
			myLogger.info("SQL INSERT KEPUTUSAN MMK :" + sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_MMK", id_mmk);
			r1.add("NO_RUJMMK", txtNoRujMMK);

			if (txdTarikhMMK != "") {
				r1.add("TARIKH_MMK", r1.unquote("to_date('" + txdTarikhMMK
						+ "','dd/MM/yyyy')"));
			} else {
				r1.add("TARIKH_MMK", "");
			}

			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptmmk");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK PENARIKAN :"
					+ sql);
			stmt1.executeUpdate(sql1);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePenyediaanMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_mmk = (String) data.get("id_mmk");
		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");
		
		String txtTajuk = (String) data.get("txtTajuk");
		String txtTujuan = (String) data.get("txtTujuan");
		String txtLatarbelakang = (String) data.get("txtLatarbelakang");		
		String txtImplikasi = (String) data.get("txtImplikasi");
		String txtUlasan = (String) data.get("txtUlasan");
		String txtSyor = (String) data.get("txtSyor");		
		String txtAsasPertimbangan = (String) data.get("txtAsasPertimbangan");
		String txtNilaiAtasTanah = (String) data.get("txtNilaiAtasTanah");
		String txtSebabPenarikan = (String) data.get("txtSebabPenarikan");
		String txtPerihalTanah = (String) data.get("txtPerihalTanah");
		String txtKesimpulan = (String) data.get("txtKesimpulan");
		String txtUlasanPengarah = (String) data.get("txtUlasanPengarah");
		
		String txtKeputusan = (String) data.get("txtKeputusan");
		String txtPerihalPohon = (String) data.get("txtPerihalPohon");
		String txtNamaTuan = (String) data.get("txtNamaTuan");
		String txtPerakuanPentadbir = (String) data.get("txtPerakuanPentadbir");
		String txtPeruntukan = (String) data.get("txtPeruntukan");
		String flag_mmk = (String) data.get("flag_mmk");
		String txtLokasi = (String) data.get("txtLokasi");
		
		
		String txtSidang = (String) data.get("txtSidang");
		String txtTempatSidang = (String) data.get("txtTempatSidang");
		String txtTarikhSidang = (String) data.get("txtTarikhSidang");
		String txtMasaSidang = (String) data.get("txtMasaSidang");
		String jeniswaktu = (String) data.get("jeniswaktu");
		
		
		
		
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_MMK", id_mmk);
			r.add("FLAG_MMK", flag_mmk);
			

			r.add("KETERANGAN_SIDANG", txtSidang);
			r.add("TEMPAT_SIDANG", txtTempatSidang);
			r.add("WAKTU_SIDANG", txtMasaSidang);
			r.add("JENIS_WAKTU_SIDANG", jeniswaktu);
			if (txtTarikhSidang != "") {
				r.add("TARIKH_SIDANG", r.unquote("to_date('"
						+ txtTarikhSidang + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SIDANG", "");
			}
			
			r.add("BUTIR_TANAH", txtLokasi);
			
			r.add("PENGESAHAN_PERUNTUKAN", txtPeruntukan);
			r.add("KEPUTUSAN", txtKeputusan);
			r.add("PERAKUAN_PENTADBIR_TNH", txtPerakuanPentadbir);
			r.add("NAMA_TUAN_TANAH", txtNamaTuan);
			r.add("PERIHAL_POHON", txtPerihalPohon);
			
			r.add("ASAS_PERTIMBANGAN", txtAsasPertimbangan);
			r.add("NILAI_ATAS_TANAH", txtNilaiAtasTanah);
			r.add("PERIHAL_TANAH", txtPerihalTanah);
			r.add("KESIMPULAN", txtKesimpulan);
			r.add("ULASAN_PENGARAH", txtUlasanPengarah);	

			r.add("TAJUK", txtTajuk);
			r.add("TUJUAN", txtTujuan);
			r.add("LATARBELAKANG", txtLatarbelakang);
			r.add("IMPLIKASI", txtImplikasi);
	//		r.add("ULASAN", txtUlasan);
			r.add("SEBAB_PENARIKAN", txtSebabPenarikan);
			r.add("SYOR", txtSyor);
			
			
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptmmk");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK :" + sql);
			stmt.executeUpdate(sql);
/*
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_PENARIKANBALIK", id_pembatalan);
			r1.add("SEBAB_PENARIKANBALIK", txtSebab);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblpptpenarikanbalik");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK PENARIKAN :"
					+ sql);
			stmt1.executeUpdate(sql1);
			*/
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateSemakanMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		String id_mmk = (String) data.get("id_mmk");
		String user_id = (String) data.get("user_id");
		String socKeputusan = (String) data.get("socKeputusan");
		String txtUlasan = (String) data.get("txtUlasan");
		String id_Masuk = (String) data.get("id_Masuk");
		String txdTarikhSemakan = (String) data.get("txdTarikhSemakan");
		String txdTarikhHantar = (String) data.get("txdTarikhHantar");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_MMK", id_mmk);
			r.add("FLAG_MMK", "2");
			r.add("USER_ID", user_id);
			r.add("STATUS_SEMAKAN", socKeputusan);
			r.add("ULASAN", txtUlasan);
			r.add("ID_KEMASKINI", id_Masuk);

			if (txdTarikhSemakan != "") {
				r.add("TARIKH_SEMAK", r.unquote("to_date('" + txdTarikhSemakan
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SEMAK", "");
			}
			if (txdTarikhHantar != "") {
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_HANTAR", "");
			}

			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptmmk");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteSemakanMMK(String id_mmk, String id_Masuk)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_MMK", id_mmk);
			r.add("USER_ID", "");
			r.add("STATUS_SEMAKAN", "");
			r.add("TARIKH_SEMAK", "");
			r.add("ULASAN_PENGARAH", "");
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptmmk");
			myLogger.info("SQL UPDATE PENYEDIAAN MMK :" + sql);
			stmt.executeUpdate(sql);
			
			
			
			Statement stmt1 = db.getStatement();
			sql1 = "DELETE FROM TBLPPTMMKKEPUTUSAN WHERE ID_MMK = "
					+ id_mmk;
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector maklumat_semakan_pegawai = null;

	public Vector maklumat_semakan_pegawai(String user_id) throws Exception {
		maklumat_semakan_pegawai = new Vector();
		Db db = null;
		maklumat_semakan_pegawai.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT USER_ID,USER_NAME FROM USERS WHERE USER_ID = '"
					+ user_id + "'";

			myLogger.info("PENYEDIAAN:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("USER_ID", rs.getString("USER_ID") == null ? "" : rs
						.getString("USER_ID"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs
						.getString("USER_NAME"));

				maklumat_semakan_pegawai.addElement(h);
			}
			return maklumat_semakan_pegawai;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_semakan = null;

	public Vector maklumat_semakan(String id_penarikan) throws Exception {
		maklumat_semakan = new Vector();
		Db db = null;
		maklumat_semakan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT M.FLAG_MMK,M.TARIKH_HANTAR,M.ULASAN,M.ULASAN_PENGARAH,U.USER_ID,U.USER_NAME,M.TARIKH_SEMAK,M.STATUS_SEMAKAN "
					+ "FROM TBLPPTMMK M,USERS U,TBLPPTPENARIKANBALIK B,TBLPPTPERMOHONAN P "
					+ "WHERE M.USER_ID = U.USER_ID "
					+ "AND M.ID_PENARIKANBALIK = B.ID_PENARIKANBALIK "
					+ "AND B.ID_PERMOHONAN = P.ID_PERMOHONAN"
					+ " AND B.ID_PENARIKANBALIK = '" + id_penarikan + "'";

			myLogger.info("SEMAKAN:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("USER_ID", rs.getString("USER_ID") == null ? "" : rs
						.getString("USER_ID"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs
						.getString("USER_NAME"));
				h.put("FLAG_MMK", rs.getString("FLAG_MMK") == null ? ""
						: rs.getString("FLAG_MMK"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? ""
						: rs.getString("ULASAN"));
				h.put("STATUS_SEMAKAN",
						rs.getString("STATUS_SEMAKAN") == null ? "" : rs
								.getString("STATUS_SEMAKAN"));
				h.put("TARIKH_SEMAK", rs.getString("TARIKH_SEMAK") == null ? ""
						: Format.format(rs.getDate("TARIKH_SEMAK")));
				h.put("TARIKH_HANTAR", rs.getString("TARIKH_HANTAR") == null ? ""
						: Format.format(rs.getDate("TARIKH_HANTAR")));


				maklumat_semakan.addElement(h);
			}
			return maklumat_semakan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_keputusan = null;

	public Vector maklumat_keputusan(String id_penarikan) throws Exception {
		maklumat_keputusan = new Vector();
		Db db = null;
		maklumat_keputusan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT MK.ID_MMKKEPUTUSAN,MK.TARIKH_TERIMA,MK.TARIKH_KEPUTUSAN,"
					+ "MK.TARIKH_HANTAR,MK.STATUS_KEPUTUSAN,MK.ULASAN,M.NO_RUJMMK,M.TARIKH_MMK "
					+ "FROM TBLPPTMMK M,"
					+ "TBLPPTPENARIKANBALIK B, TBLPPTPERMOHONAN P, TBLPPTMMKKEPUTUSAN MK "
					+ "WHERE M.ID_PENARIKANBALIK = B.ID_PENARIKANBALIK "
					+ "AND B.ID_PERMOHONAN = P.ID_PERMOHONAN AND M.ID_MMK = MK.ID_MMK "
					+ " AND B.ID_PENARIKANBALIK = '" + id_penarikan + "'";

			myLogger.info("KEPUTUSAN MMK:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("ID_MMKKEPUTUSAN",
						rs.getString("ID_MMKKEPUTUSAN") == null ? "" : rs
								.getString("ID_MMKKEPUTUSAN"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
						.getString("ULASAN"));
				h.put("NO_RUJMMK", rs.getString("NO_RUJMMK") == null ? "" : rs
						.getString("NO_RUJMMK"));
				h.put("TARIKH_MMK", rs.getString("TARIKH_MMK") == null ? ""
						: Format.format(rs.getDate("TARIKH_MMK")));
				h.put("STATUS_KEPUTUSAN",
						rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs
								.getString("STATUS_KEPUTUSAN"));
				h.put("TARIKH_TERIMA",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("TARIKH_KEPUTUSAN",
						rs.getString("TARIKH_KEPUTUSAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("TARIKH_HANTAR",
						rs.getString("TARIKH_HANTAR") == null ? "" : Format
								.format(rs.getDate("TARIKH_HANTAR")));
				maklumat_keputusan.addElement(h);
			}
			return maklumat_keputusan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_warta = null;

	public Vector maklumat_warta(String id_penarikan) throws Exception {
		maklumat_warta = new Vector();
		Db db = null;
		maklumat_warta.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT W.ID_WARTA,W.PROSES_WARTA,W.NO_WARTA,W.TARIKH_WARTA "
					+ "FROM TBLPPTWARTA W,TBLPPTPENARIKANBALIK B,TBLPPTPERMOHONAN P "
					+ "WHERE B.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ "AND W.ID_PENARIKANBALIK = B.ID_PENARIKANBALIK"
					+ " AND B.ID_PENARIKANBALIK = '" + id_penarikan + "'";

			myLogger.info("MAKLUMAT WARTA:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {

				h = new Hashtable();
				h.put("ID_WARTA", rs.getString("ID_WARTA") == null ? "" : rs
						.getString("ID_WARTA"));
				h.put("PROSES_WARTA", rs.getString("PROSES_WARTA") == null ? ""
						: rs.getString("PROSES_WARTA"));
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA"));
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: Format.format(rs.getDate("TARIKH_WARTA")));
				maklumat_warta.addElement(h);
			}
			return maklumat_warta;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addWartaMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String socProcesWarta = (String) data.get("socProcesWarta");
		String txdTarikhWarta = (String) data.get("txdTarikhWarta");
		String txtNoWarta = (String) data.get("txtNoWarta");

		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("PROSES_WARTA", socProcesWarta);
			r.add("NO_WARTA", txtNoWarta);
			r.add("ID_PENARIKANBALIK", id_pembatalan);

			if (txdTarikhWarta != "") {
				r.add("TARIKH_WARTA", r.unquote("to_date('" + txdTarikhWarta
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_WARTA", "");
			}

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptwarta");
			myLogger.info("SQL INSERT WARTA :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateWartaMMK(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String id_warta = (String) data.get("id_warta");
		String socProcesWarta = (String) data.get("socProcesWarta");
		String txdTarikhWarta = (String) data.get("txdTarikhWarta");
		String txtNoWarta = (String) data.get("txtNoWarta");

		String id_permohonan = (String) data.get("id_permohonan");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_WARTA", id_warta);
			r.add("PROSES_WARTA", socProcesWarta);
			r.add("NO_WARTA", txtNoWarta);
			r.add("ID_PENARIKANBALIK", id_pembatalan);

			if (txdTarikhWarta != "") {
				r.add("TARIKH_WARTA", r.unquote("to_date('" + txdTarikhWarta
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_WARTA", "");
			}

			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptwarta");
			myLogger.info("SQL INSERT WARTA :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteWarta(String id_warta) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLPPTWARTA WHERE ID_WARTA = " + id_warta;
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector senarai_pihak_penting_pampasan_perintah = null;

	public Vector senarai_pihak_penting_pampasan_perintah(String id_pembatalan,String id_siasatan,String CariPB)
			/*
			String namapb, String nopb, String kplama, String nolot,
			String socMukim)
			*/ throws Exception {
		senarai_pihak_penting_pampasan_perintah = new Vector();
		Db db = null;
		senarai_pihak_penting_pampasan_perintah.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT " +
				//	"AW.PERINTAH,AW.ULASAN_TIDAK_HADIR," +
					"H.ID_HAKMILIK," +
				//	"AW.ID_AWARD," +
					"PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB," +
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+
							"M.NAMA_MUKIM," 
						//	"AW.BAYAR_PAMPASAN,"
					+" BPB.ID_BAHAGIANPB," 
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB, "
					+ "JPB.KETERANGAN AS JENIS_PB,BPB.KETERANGAN_SYER "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, " +
						//	"TBLPPTAWARD AW," +
							"TBLPPTSIASATAN S,"
					
				
					
					+ "TBLPPTHAKMILIK H,TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M," +
							"TBLPPTPENARIKANHAKMILIK PH,TBLRUJLOT JL,  "
					+ "TBLRUJJENISPB JPB  "
					+ "WHERE "
					+ " HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					//+" AND AW.ID_SIASATAN(+) = S.ID_SIASATAN "
					//+" AND ((AW.ID_SIASATAN IS NOT NULL AND AW.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB) OR (AW.ID_SIASATAN IS NULL))"
					+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " +
					"AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) "
					+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+" AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+" AND H.ID_HAKMILIK = S.ID_HAKMILIK  AND H.ID_LOT = JL.ID_LOT(+) "
					
					+" AND HPB.ID_JENISPB = JPB.ID_JENISPB(+) "
					
					+" AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" AND S.ID_SIASATAN = '" + id_siasatan + "' "
					+" AND PH.ID_PENARIKANBALIK = '" + id_pembatalan + "'  ";
			
			if(!CariPB.equals("") && !CariPB.equals(null))
			{
				
				if (!CariPB.trim().equals("")) {
					sql = sql + " AND (UPPER(PB.NO_PB) LIKE '%"
							+ CariPB.toUpperCase().trim() + "%'";					
					
					sql = sql + " OR UPPER(PB.NAMA_PB) LIKE '%"
					+ CariPB.toUpperCase().trim() + "%')";
					
					
				}
				
			}
			

			sql += "ORDER BY NO_LOT ASC,ID_BAHAGIANPB ASC,NAMA_PB ASC ";
			myLogger.info("SENARAI PB PAMPASAN PERINTAH :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
		/*		h.put("PERINTAH", rs.getString("PERINTAH") == null ? ""
						: rs.getString("PERINTAH"));
				h.put("ULASAN_TIDAK_HADIR", rs.getString("ULASAN_TIDAK_HADIR") == null ? ""
						: rs.getString("ULASAN_TIDAK_HADIR"));*/
				
		//		h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
		//				: rs.getString("ID_AWARD"));
				
				
				h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_SYER") == null ? ""
						: rs.getString("KETERANGAN_SYER"));					
				h.put("JENIS_PB", rs.getString("JENIS_PB") == null ? ""
						: rs.getString("JENIS_PB"));
				
				h.put("ID_BAHAGIANPB",
						rs.getString("ID_BAHAGIANPB") == null ? "" : rs
								.getString("ID_BAHAGIANPB"));
				
				
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
			//	h.put("BAYAR_PAMPASAN",
			//			rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
			//					.getDouble("BAYAR_PAMPASAN"));
				senarai_pihak_penting_pampasan_perintah.addElement(h);
			}
			return senarai_pihak_penting_pampasan_perintah;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector senarai_pihak_penting_pampasan_perintah_award = null;

	public Vector senarai_pihak_penting_pampasan_perintah_award(String id_pembatalan,String id_siasatan)
			 throws Exception {
		senarai_pihak_penting_pampasan_perintah_award = new Vector();
		Db db = null;
		senarai_pihak_penting_pampasan_perintah_award.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.NO_SUBJAKET,H.ID_HAKMILIK," +
            		"AW.PERINTAH,AW.ULASAN_TIDAK_HADIR,AW.STATUS_PENERIMA," +			
					"AW.ID_AWARD," +
					"PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB," +
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+
							"M.NAMA_MUKIM," 
						+	"AW.BAYAR_PAMPASAN,"
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, " +
							"TBLPPTAWARD AW," +
							"TBLPPTSIASATAN S,"
					+ "TBLPPTHAKMILIK H,TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M,TBLPPTPENARIKANHAKMILIK PH,TBLRUJLOT JL  "
					+ "WHERE "
					+ " HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+" AND AW.ID_SIASATAN = S.ID_SIASATAN "
					+" AND AW.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB "
						+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " +
					"AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) "
					+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+" AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+" AND H.ID_HAKMILIK = S.ID_HAKMILIK  AND H.ID_LOT = JL.ID_LOT(+) "
					+" AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" AND S.ID_SIASATAN = '" + id_siasatan + "' "
					+" AND PH.ID_PENARIKANBALIK = '" + id_pembatalan + "'  ";

			sql += "ORDER BY LPAD(H.NO_SUBJAKET,10) ASC,NAMA_PB ASC ";
			myLogger.info("SENARAI PB PAMPASAN PERINTAH :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
						: rs.getString("ID_AWARD").toUpperCase());
				h.put("STATUS_PENERIMA", rs.getString("STATUS_PENERIMA") == null ? ""
						: rs.getString("STATUS_PENERIMA").toUpperCase());
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? ""
						: rs.getString("PERINTAH"));
				h.put("ULASAN_TIDAK_HADIR", rs.getString("ULASAN_TIDAK_HADIR") == null ? ""
						: rs.getString("ULASAN_TIDAK_HADIR"));
				h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
					: rs.getString("ID_AWARD"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));
				senarai_pihak_penting_pampasan_perintah_award.addElement(h);
			}
			return senarai_pihak_penting_pampasan_perintah_award;
		} finally {
			if (db != null)
				db.close();
		}
	}


	Vector senarai_pihak_penting_pampasan = null;

	public Vector senarai_pihak_penting_pampasan(String id_pembatalan,
			String namapb, String nopb, String kplama, String nolot,
			String socMukim,String id_hakmilik) throws Exception {
		senarai_pihak_penting_pampasan = new Vector();
		Db db = null;
		senarai_pihak_penting_pampasan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.NO_SUBJAKET,H.ID_HAKMILIK,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB," +
						//	"H.NO_LOT," +
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
				//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+
							"M.NAMA_MUKIM,AW.BAYAR_PAMPASAN,"
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW,TBLRUJLOT JL,"
					+ "TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S,TBLPPTBAHAGIANPB BPB,TBLRUJMUKIM M,TBLPPTPENARIKANHAKMILIK PH  "
					+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND S.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND S.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
					+ " AND S.ID_SIASATAN = AW.ID_SIASATAN "
					+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " +
					"AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+)  AND H.ID_LOT = JL.ID_LOT(+) "
					+" AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+" AND H.ID_HAKMILIK = '" + id_hakmilik + "' "
					+" AND PH.ID_PENARIKANBALIK = '" + id_pembatalan + "'  ";

			if (namapb != "") {
				sql += "AND  PB.NAMA_PB LIKE '%" + namapb + "%'  ";
			}

			if (nopb != "") {
				sql += "AND  PB.NO_PB LIKE '%" + nopb + "%'  ";
			}

			if (kplama != "") {
				sql += "AND  PB.NO_KP_LAMA LIKE '%" + kplama + "%'  ";
			}

			if (nolot != "") {
				sql += "AND  (H.NO_LOT LIKE '%" + nolot + "%' OR H.NO_PT LIKE '%" + nolot + "%')  ";
			}

			if (socMukim != "") {
				sql += "AND  H.ID_MUKIM = '" + socMukim + "'  ";
			}

			sql += "ORDER BY LPAD(H.NO_SUBJAKET,10) ASC,NAMA_PB ASC ";
			myLogger.info("senarai_pihak_penting_pampasan :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));
				senarai_pihak_penting_pampasan.addElement(h);
			}
			return senarai_pihak_penting_pampasan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_lot_pampasan = null;

	public Vector senarai_lot_pampasan(String id_pembatalan, String namapb,
			String nopb, String kplama, String nolot, String socMukim)
			throws Exception {
		senarai_lot_pampasan = new Vector();
		Db db = null;
		senarai_lot_pampasan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT H.NO_SUBJAKET,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB"
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW,"
					+ "TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M,TBLPPTBAHAGIANPB BPB "
					+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " +
					" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"+
					"AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) ";

			if (namapb != "") {
				sql += "AND  PB.NAMA_PB LIKE '%" + namapb + "%'  ";
			}

			if (nopb != "") {
				sql += "AND  PB.NO_PB LIKE '%" + nopb + "%'  ";
			}

			if (kplama != "") {
				sql += "AND  PB.NO_KP_LAMA LIKE '%" + kplama + "%'  ";
			}

			if (nolot != "") {
				sql += "AND  H.NO_LOT LIKE '%" + nolot + "%'  ";
			}

			if (socMukim != "") {
				sql += "AND  H.ID_MUKIM = '" + socMukim + "'  ";
			}

			sql += "ORDER BY LPAD(H.NO_SUBJAKET,10) ASC,NAMA_PB ASC ";
			myLogger.info("senarai_pihak_penting_pampasan :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				senarai_lot_pampasan.addElement(h);
			}
			return senarai_lot_pampasan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_hakmilik_pampasan = null;

	public Vector maklumat_hakmilik_pampasan(String id_hakmilik)
			throws Exception {
		maklumat_hakmilik_pampasan = new Vector();
		Db db = null;
		maklumat_hakmilik_pampasan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// ************

			sql = "SELECT DISTINCT H.NO_SUBJAKET,H.ID_HAKMILIK,H.ID_PENARIKANBALIK,H.NO_PT," +
					//"H.NO_LOT,"
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "		
					+ "JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ "M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL,H.FLAG_PENARIKAN_BALIK "
					+ " ,N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT "
					+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLRUJLOT JL "
					+ " ,TBLRUJNEGERI N,TBLRUJDAERAH D "
					+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ "AND H.ID_MUKIM = M.ID_MUKIM "
					+ "AND H.FLAG_PENARIKAN_BALIK = 'Y' "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) AND H.ID_LOT = JL.ID_LOT(+)  "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ "AND H.ID_HAKMILIK = '" + id_hakmilik + "' ORDER BY LPAD(H.NO_SUBJAKET,10) ASC ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				// h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ?
				// "" : rs.getString("ID_HAKMILIKPB"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				// h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" :
				// rs.getString("NAMA_PB"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
					
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
				
					}

				
				h.put("FLAG_PEMBATALAN",
						rs.getString("FLAG_PENARIKAN_BALIK") == null ? "" : rs
								.getString("FLAG_PENARIKAN_BALIK"));

				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				maklumat_hakmilik_pampasan.addElement(h);
			}
			return maklumat_hakmilik_pampasan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_mukim_pampasan = null;

	public Vector list_mukim_pampasan(String id_penarikan) throws Exception {
		list_mukim_pampasan = new Vector();
		Db db = null;
		list_mukim_pampasan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT M.ID_MUKIM,M.KOD_MUKIM,M.NAMA_MUKIM "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTPENARIKANHAKMILIK PHK, TBLPPTAWARD AW,TBLPPTHAKMILIK H,"
					+ "TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M "
					+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+) "
					+ "AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ "AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) AND  H.ID_HAKMILIK = PHK.ID_HAKMILIK AND PHK.ID_PENARIKANBALIK = '"
					+ id_penarikan + "'" + " ORDER BY KOD_MUKIM ASC ";
			myLogger.info("SQL PAMPASAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM"));
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				list_mukim_pampasan.addElement(h);
			}
			return list_mukim_pampasan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector header_pampasan_pb = null;
//*********
	public Vector header_pampasan_pb(String id_hakmilikpb) throws Exception {
		header_pampasan_pb = new Vector();
		Db db = null;
		header_pampasan_pb.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT H.NO_SUBJAKET,K.NAMA_KEMENTERIAN,H.NO_PT,H.ID_KATEGORITANAH,H.LUAS_AMBIL,H.ID_HAKMILIK,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
					+ "PB.NO_PB," +
					//		"H.NO_LOT," +
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
				//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+
							"M.NAMA_MUKIM,AW.BAYAR_PAMPASAN,"
					+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,H.LUAS_LOT,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,HPB.ID_BANK,HPB.NO_AKAUN,HPB.NAMA_BANK "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW, TBLRUJBANK BB,"
					+ "TBLPPTHAKMILIK H,TBLRUJLOT JL,TBLPPTBAHAGIANPB BPB,TBLPPTHAKMILIKPB HPB,TBLRUJMUKIM M,TBLPPTPERMOHONAN  P,TBLPFDFAIL F,TBLRUJKEMENTERIAN K "
					+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND HPB.ID_BANK = BB.ID_BANK(+) "
					+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+ "AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ "AND H.ID_MUKIM = M.ID_MUKIM(+) AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND P.ID_FAIL = F.ID_FAIL AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN "
					+ "AND HPB.ID_HAKMILIKPB = '" + id_hakmilikpb + "'  ";

			sql += " ORDER BY LPAD(H.NO_SUBJAKET,10) ASC,NAMA_PB ASC ";
			myLogger.info("header_pampasan_pb :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
				
				
				
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					 
					}
				
				
				
				
				
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));

				header_pampasan_pb.addElement(h);
			}
			return header_pampasan_pb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_bayaran_bp = null;

	public Vector maklumat_bayaran_bp(String id_hakmilikpb, String cara_bayar,String id_pembatalan)
			throws Exception {
		maklumat_bayaran_bp = new Vector();
		Db db = null;
		maklumat_bayaran_bp.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT PB.NAMA_PB,KM.NAMA_KEMENTERIAN,B.TARIKH_AKHIR_CEK,B.ID_BAYARAN,B.TARIKH_TERIMA,PB.NAMA_PB,B.NO_BAYARAN,B.TARIKH_CEK,B.AMAUN_BAYARAN, "
					+ " B.PENERIMA_CEK,B.NO_PB,B.TARIKH_SERAH_CEK,B.FLAG_SERAH_CEK,B.NO_RUJUKAN_SURATEFT,B.TARIKH_SURAT_EFT, "
					+ " B.TARIKH_TERIMA_EFT,B.CARA_BAYAR,B.JENIS_AWARD,B.NO_EFT,B.NO_WAKIL, "
					+ " B.NAMA_WAKIL,B.NO_BAUCER,B.TARIKH_BAYARAN_EFT "
					+ " FROM TBLPPTBAYARAN B,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIK H, "
					+ " TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN KM,TBLPFDFAIL F "
					+ " WHERE HPB.ID_HAKMILIKPB = B.ID_HAKMILIKPB "
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND B.CARA_BAYAR = '"
					+ cara_bayar
					+ "' "
					+ " AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND B.ID_PENARIKANBALIK = '"+id_pembatalan+"' "
					+ " AND P.ID_FAIL = F.ID_FAIL "
					+ " AND F.ID_KEMENTERIAN = KM.ID_KEMENTERIAN(+) "
					+ " AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HPB.ID_HAKMILIKPB = '" + id_hakmilikpb + "' ";
			sql += "ORDER BY ID_BAYARAN DESC ";
			myLogger.info("MAKLUMAT PEMBAYARAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("TARIKH_TERIMA",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_BAYARAN", rs.getString("NO_BAYARAN") == null ? ""
						: rs.getString("NO_BAYARAN"));
				h.put("TARIKH_CEK", rs.getString("TARIKH_CEK") == null ? ""
						: Format.format(rs.getDate("TARIKH_CEK")));
				h.put("AMAUN_BAYARAN",
						rs.getString("AMAUN_BAYARAN") == null ? "" : rs
								.getDouble("AMAUN_BAYARAN"));
				h.put("PENERIMA_CEK", rs.getString("PENERIMA_CEK") == null ? ""
						: rs.getString("PENERIMA_CEK"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("FLAG_SERAH_CEK",
						rs.getString("FLAG_SERAH_CEK") == null ? "" : rs
								.getString("FLAG_SERAH_CEK"));
				h.put("TARIKH_SERAH_CEK",
						rs.getString("TARIKH_SERAH_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_SERAH_CEK")));
				h.put("NO_RUJUKAN_SURATEFT", rs
						.getString("NO_RUJUKAN_SURATEFT") == null ? "" : rs
						.getString("NO_RUJUKAN_SURATEFT"));
				h.put("TARIKH_SURAT_EFT",
						rs.getString("TARIKH_SURAT_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_SURAT_EFT")));
				h.put("TARIKH_TERIMA_EFT",
						rs.getString("TARIKH_TERIMA_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA_EFT")));
				h.put("CARA_BAYAR", rs.getString("CARA_BAYAR") == null ? ""
						: rs.getString("CARA_BAYAR"));
				h.put("JENIS_AWARD", rs.getString("JENIS_AWARD") == null ? ""
						: rs.getString("JENIS_AWARD"));
				h.put("NO_EFT", rs.getString("NO_EFT") == null ? "" : rs
						.getString("NO_EFT"));
				h.put("NO_WAKIL", rs.getString("NO_WAKIL") == null ? "" : rs
						.getString("NO_WAKIL"));
				h.put("NAMA_WAKIL", rs.getString("NAMA_WAKIL") == null ? ""
						: rs.getString("NAMA_WAKIL"));
				h.put("NO_BAUCER", rs.getString("NO_BAUCER") == null ? "" : rs
						.getString("NO_BAUCER"));
				h.put("TARIKH_AKHIR_CEK",
						rs.getString("TARIKH_AKHIR_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_AKHIR_CEK")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("TARIKH_BAYARAN_EFT",
						rs.getString("TARIKH_BAYARAN_EFT") == null ? ""
								: Format.format(rs
										.getDate("TARIKH_BAYARAN_EFT")));

				maklumat_bayaran_bp.addElement(h);
			}
			return maklumat_bayaran_bp;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_bayaran_semua = null;

	public Vector maklumat_bayaran_semua(String id_hakmilikpb, String cara_bayar,String id_pembatalan)
			throws Exception {
		maklumat_bayaran_semua = new Vector();
		Db db = null;
		maklumat_bayaran_semua.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT PB.NAMA_PB,KM.NAMA_KEMENTERIAN,B.TARIKH_AKHIR_CEK,B.ID_BAYARAN,B.TARIKH_TERIMA,PB.NAMA_PB,B.NO_BAYARAN,B.TARIKH_CEK,B.AMAUN_BAYARAN, "
					+ " B.PENERIMA_CEK,B.NO_PB,B.TARIKH_SERAH_CEK,B.FLAG_SERAH_CEK,B.NO_RUJUKAN_SURATEFT,B.TARIKH_SURAT_EFT, "
					+ " B.TARIKH_TERIMA_EFT,B.CARA_BAYAR,B.JENIS_AWARD,B.NO_EFT,B.NO_WAKIL, "
					+ " B.NAMA_WAKIL,B.NO_BAUCER,B.TARIKH_BAYARAN_EFT "
					+ " FROM TBLPPTBAYARAN B,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIK H, "
					+ " TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN KM,TBLPFDFAIL F "
					+ " WHERE HPB.ID_HAKMILIKPB = B.ID_HAKMILIKPB "
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "					
					+ " AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND B.ID_PENARIKANBALIK = '"+id_pembatalan+"' "
					+ " AND P.ID_FAIL = F.ID_FAIL "
					+ " AND F.ID_KEMENTERIAN = KM.ID_KEMENTERIAN(+) "
					+ " AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HPB.ID_HAKMILIKPB = '" + id_hakmilikpb + "' ";
			sql += "ORDER BY ID_BAYARAN DESC ";
			myLogger.info("MAKLUMAT PEMBAYARAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("TARIKH_TERIMA",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_BAYARAN", rs.getString("NO_BAYARAN") == null ? ""
						: rs.getString("NO_BAYARAN"));
				h.put("TARIKH_CEK", rs.getString("TARIKH_CEK") == null ? ""
						: Format.format(rs.getDate("TARIKH_CEK")));
				h.put("AMAUN_BAYARAN",
						rs.getString("AMAUN_BAYARAN") == null ? "" : rs
								.getDouble("AMAUN_BAYARAN"));
				h.put("PENERIMA_CEK", rs.getString("PENERIMA_CEK") == null ? ""
						: rs.getString("PENERIMA_CEK"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("FLAG_SERAH_CEK",
						rs.getString("FLAG_SERAH_CEK") == null ? "" : rs
								.getString("FLAG_SERAH_CEK"));
				h.put("TARIKH_SERAH_CEK",
						rs.getString("TARIKH_SERAH_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_SERAH_CEK")));
				h.put("NO_RUJUKAN_SURATEFT", rs
						.getString("NO_RUJUKAN_SURATEFT") == null ? "" : rs
						.getString("NO_RUJUKAN_SURATEFT"));
				h.put("TARIKH_SURAT_EFT",
						rs.getString("TARIKH_SURAT_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_SURAT_EFT")));
				h.put("TARIKH_TERIMA_EFT",
						rs.getString("TARIKH_TERIMA_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA_EFT")));
				h.put("CARA_BAYAR", rs.getString("CARA_BAYAR") == null ? ""
						: rs.getString("CARA_BAYAR"));
				h.put("JENIS_AWARD", rs.getString("JENIS_AWARD") == null ? ""
						: rs.getString("JENIS_AWARD"));
				h.put("NO_EFT", rs.getString("NO_EFT") == null ? "" : rs
						.getString("NO_EFT"));
				h.put("NO_WAKIL", rs.getString("NO_WAKIL") == null ? "" : rs
						.getString("NO_WAKIL"));
				h.put("NAMA_WAKIL", rs.getString("NAMA_WAKIL") == null ? ""
						: rs.getString("NAMA_WAKIL"));
				h.put("NO_BAUCER", rs.getString("NO_BAUCER") == null ? "" : rs
						.getString("NO_BAUCER"));
				h.put("TARIKH_AKHIR_CEK",
						rs.getString("TARIKH_AKHIR_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_AKHIR_CEK")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("TARIKH_BAYARAN_EFT",
						rs.getString("TARIKH_BAYARAN_EFT") == null ? ""
								: Format.format(rs
										.getDate("TARIKH_BAYARAN_EFT")));

				maklumat_bayaran_semua.addElement(h);
			}
			return maklumat_bayaran_semua;
		} finally {
			if (db != null)
				db.close();
		}
	}

	

	Vector maklumat_details_bayaran = null;

	public Vector maklumat_details_bayaran(String id_bayaran) throws Exception {
		maklumat_details_bayaran = new Vector();
		Db db = null;
		maklumat_details_bayaran.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT B.ULASAN,B.NAMA_BANK,B.JENIS_WAKTU_AMBIL_CEK,B.TEMPAT_AMBIL,B.MASA_AMBIL_CEK,PB.NAMA_PB,KM.NAMA_KEMENTERIAN,B.TARIKH_AKHIR_CEK,B.ID_BAYARAN,B.TARIKH_TERIMA,PB.NAMA_PB,B.NO_BAYARAN,B.TARIKH_CEK,B.AMAUN_BAYARAN, "
					+ " B.PENERIMA_CEK,B.NO_PB,B.TARIKH_SERAH_CEK,B.FLAG_SERAH_CEK,B.NO_RUJUKAN_SURATEFT,B.TARIKH_SURAT_EFT, "
					+ " B.TARIKH_TERIMA_EFT,B.CARA_BAYAR,B.JENIS_AWARD,B.NO_EFT,B.NO_WAKIL, "
					+ " B.NAMA_WAKIL,B.NO_BAUCER,B.TARIKH_BAYARAN_EFT "
					+ " FROM TBLPPTBAYARAN B,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,TBLPPTHAKMILIK H, "
					+ " TBLPPTPERMOHONAN P,TBLRUJKEMENTERIAN KM,TBLPFDFAIL F "
					+ " WHERE HPB.ID_HAKMILIKPB = B.ID_HAKMILIKPB "
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND P.ID_FAIL = F.ID_FAIL "
					+ " AND F.ID_KEMENTERIAN = KM.ID_KEMENTERIAN(+) "
					+ " AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN "
					+ " AND B.ID_BAYARAN = '" + id_bayaran + "' ";
			sql += "ORDER BY ID_BAYARAN DESC ";
			myLogger.info("MAKLUMAT PEMBAYARAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ULASAN",
						rs.getString("ULASAN") == null ? "" : rs
								.getString("ULASAN"));
				h.put("NAMA_BANK",
						rs.getString("NAMA_BANK") == null ? "" : rs
								.getString("NAMA_BANK"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("ID_BAYARAN", rs.getString("ID_BAYARAN") == null ? ""
						: rs.getString("ID_BAYARAN"));
				h.put("TARIKH_TERIMA",
						rs.getString("TARIKH_TERIMA") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_BAYARAN", rs.getString("NO_BAYARAN") == null ? ""
						: rs.getString("NO_BAYARAN"));
				h.put("TARIKH_CEK", rs.getString("TARIKH_CEK") == null ? ""
						: Format.format(rs.getDate("TARIKH_CEK")));
				h.put("AMAUN_BAYARAN",
						rs.getString("AMAUN_BAYARAN") == null ? "" : rs
								.getDouble("AMAUN_BAYARAN"));
				h.put("PENERIMA_CEK", rs.getString("PENERIMA_CEK") == null ? ""
						: rs.getString("PENERIMA_CEK"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("FLAG_SERAH_CEK",
						rs.getString("FLAG_SERAH_CEK") == null ? "" : rs
								.getString("FLAG_SERAH_CEK"));
				h.put("TARIKH_SERAH_CEK",
						rs.getString("TARIKH_SERAH_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_SERAH_CEK")));
				h.put("NO_RUJUKAN_SURATEFT", rs
						.getString("NO_RUJUKAN_SURATEFT") == null ? "" : rs
						.getString("NO_RUJUKAN_SURATEFT"));

				h.put("TARIKH_SURAT_EFT",
						rs.getString("TARIKH_SURAT_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_SURAT_EFT")));
				h.put("TARIKH_TERIMA_EFT",
						rs.getString("TARIKH_TERIMA_EFT") == null ? "" : Format
								.format(rs.getDate("TARIKH_TERIMA_EFT")));
				h.put("CARA_BAYAR", rs.getString("CARA_BAYAR") == null ? ""
						: rs.getString("CARA_BAYAR"));
				h.put("JENIS_AWARD", rs.getString("JENIS_AWARD") == null ? ""
						: rs.getString("JENIS_AWARD"));
				h.put("NO_EFT", rs.getString("NO_EFT") == null ? "" : rs
						.getString("NO_EFT"));
				h.put("NO_WAKIL", rs.getString("NO_WAKIL") == null ? "" : rs
						.getString("NO_WAKIL"));
				h.put("NAMA_WAKIL", rs.getString("NAMA_WAKIL") == null ? ""
						: rs.getString("NAMA_WAKIL"));
				h.put("NO_BAUCER", rs.getString("NO_BAUCER") == null ? "" : rs
						.getString("NO_BAUCER"));
				h.put("TARIKH_AKHIR_CEK",
						rs.getString("TARIKH_AKHIR_CEK") == null ? "" : Format
								.format(rs.getDate("TARIKH_AKHIR_CEK")));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("TARIKH_BAYARAN_EFT",
						rs.getString("TARIKH_BAYARAN_EFT") == null ? ""
								: Format.format(rs
										.getDate("TARIKH_BAYARAN_EFT")));
				h.put("TEMPAT_AMBIL", rs.getString("TEMPAT_AMBIL") == null ? "" : rs
						.getString("TEMPAT_AMBIL"));
				h.put("MASA_AMBIL_CEK", rs.getString("MASA_AMBIL_CEK") == null ? "" : rs
						.getString("MASA_AMBIL_CEK"));
				
				h.put("JENIS_WAKTU_AMBIL_CEK", rs.getString("JENIS_WAKTU_AMBIL_CEK") == null ? "" : rs
						.getString("JENIS_WAKTU_AMBIL_CEK"));

				
				
				maklumat_details_bayaran.addElement(h);
			}
			return maklumat_details_bayaran;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void AddMaklumatBayaran(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhTerimaCek = (String) data.get("txdTarikhTerimaCek");
		String txtpenamacek = (String) data.get("txtpenamacek");
		String txtnocek = (String) data.get("txtnocek");
		String txdTarikhCek = (String) data.get("txdTarikhCek");
		String txtAmaunCek = (String) data.get("txtAmaunCek");
		String txdTarikhAkhirCek = (String) data.get("txdTarikhAkhirCek");
		String cara_bayar = (String) data.get("cara_bayar");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String txdTarikhSerahCek = (String) data.get("txdTarikhSerahCek");
		
		String jeniswaktu = (String) data.get("jeniswaktu");
		String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
		String txtTempat = (String) data.get("txtTempat");
		
		String txtNamaBank = (String) data.get("txtNamaBank");
		String txtUlasanBayaran = (String) data.get("txtUlasanBayaran");
		
		
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("JENIS_WAKTU_AMBIL_CEK", jeniswaktu);
			r.add("MASA_AMBIL_CEK", txtMasaSiasatan);
			r.add("TEMPAT_AMBIL", txtTempat);
			r.add("NAMA_BANK", txtNamaBank);
			
			
			if (txdTarikhSerahCek != "") {
				r.add("TARIKH_SERAH_CEK", r.unquote("to_date('"
						+ txdTarikhSerahCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SERAH_CEK", "");
			}
			if (txdTarikhTerimaCek != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}
			if (txdTarikhCek != "") {
				r.add("TARIKH_CEK", r.unquote("to_date('" + txdTarikhCek
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_CEK", "");
			}
			if (txdTarikhAkhirCek != "") {
				r.add("TARIKH_AKHIR_CEK", r.unquote("to_date('"
						+ txdTarikhAkhirCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_AKHIR_CEK", "");
			}
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_HAKMILIKPB", id_hakmilikpb);
			r.add("NO_BAYARAN", txtnocek);
			r.add("AMAUN_BAYARAN", txtAmaunCek);
			r.add("CARA_BAYAR", cara_bayar);
			r.add("PENERIMA_CEK", txtpenamacek);
			r.add("JENIS_AWARD", "");
			r.add("ULASAN",txtUlasanBayaran);

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLInsert("TBLPPTBAYARAN");

			myLogger.info("SQL INSERT BAYARAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void UpdateMaklumatBayaran(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhTerimaCek = (String) data.get("txdTarikhTerimaCek");
		String id_bayaran = (String) data.get("id_bayaran");
		String txtpenamacek = (String) data.get("txtpenamacek");
		String txtnocek = (String) data.get("txtnocek");
		String txdTarikhCek = (String) data.get("txdTarikhCek");
		String txtAmaunCek = (String) data.get("txtAmaunCek");
		String txdTarikhAkhirCek = (String) data.get("txdTarikhAkhirCek");
		String cara_bayar = (String) data.get("cara_bayar");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		String id_Masuk = (String) data.get("id_Masuk");
		String txtwakil = (String) data.get("txtwakil");
		String txtNoKP = (String) data.get("txtNoKP");
		String txdTarikhSerahCek = (String) data.get("txdTarikhSerahCek");
		String socStatusPenyerahan = (String) data.get("socStatusPenyerahan");
		String txtUlasanBayaran = (String) data.get("txtUlasanBayaran");
		String jeniswaktu = (String) data.get("jeniswaktu");
		String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
		String txtTempat = (String) data.get("txtTempat");
		String txtNamaBank = (String) data.get("txtNamaBank");
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_BAYARAN", id_bayaran);
			r.add("NAMA_BANK", txtNamaBank);
			r.add("JENIS_WAKTU_AMBIL_CEK", jeniswaktu);
			r.add("MASA_AMBIL_CEK", txtMasaSiasatan);
			r.add("TEMPAT_AMBIL", txtTempat);
			r.add("ULASAN",txtUlasanBayaran);
			
			
			if (txdTarikhTerimaCek != "") {
				r.add("TARIKH_TERIMA", r.unquote("to_date('"
						+ txdTarikhTerimaCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA", "");
			}
			if (txdTarikhSerahCek != "") {
				r.add("TARIKH_SERAH_CEK", r.unquote("to_date('"
						+ txdTarikhSerahCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SERAH_CEK", "");
			}
			if (txdTarikhCek != "") {
				r.add("TARIKH_CEK", r.unquote("to_date('" + txdTarikhCek
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_CEK", "");
			}
			if (txdTarikhAkhirCek != "") {
				r.add("TARIKH_AKHIR_CEK", r.unquote("to_date('"
						+ txdTarikhAkhirCek + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_AKHIR_CEK", "");
			}
			r.add("ID_HAKMILIKPB", id_hakmilikpb);
			r.add("NO_BAYARAN", txtnocek);
			r.add("AMAUN_BAYARAN", txtAmaunCek);
			r.add("CARA_BAYAR", cara_bayar);
			r.add("PENERIMA_CEK", txtpenamacek);
			r.add("JENIS_AWARD", "");

			r.add("NAMA_WAKIL", txtwakil);
			r.add("NO_WAKIL", txtNoKP);
			r.add("FLAG_SERAH_CEK", socStatusPenyerahan);

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("TBLPPTBAYARAN");

			myLogger.info("SQL INSERT BAYARAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteBayaran(String id_bayaran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " DELETE FROM TBLPPTBAYARAN WHERE ID_BAYARAN = " + id_bayaran;
			myLogger.info("SQL UPDATE PENYEDIAAN MMK :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteSiasatan(String id_siasatan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " DELETE FROM TBLPPTSIASATAN WHERE ID_SIASATAN = "
					+ id_siasatan;
			myLogger.info("SQL DELETE SIASATAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void AddMaklumatBayaranEFT(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txdTarikh = (String) data.get("txdTarikh");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String txtNamaKementerian = (String) data.get("txtNamaKementerian");
		String txtNoEFT = (String) data.get("txtNoEFT");
		String txtAmaunEFT = (String) data.get("txtAmaunEFT");
		String cara_bayar = (String) data.get("cara_bayar");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String txtUlasanBayaran = (String) data.get("txtUlasanBayaran");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT_EFT", r.unquote("to_date('"
						+ txdTarikhSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT_EFT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA_EFT", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_EFT", "");
			}

			if (txdTarikh != "") {
				r.add("TARIKH_BAYARAN_EFT", r.unquote("to_date('" + txdTarikh
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_BAYARAN_EFT", "");
			}
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("NO_RUJUKAN_SURATEFT", txtNoRujSurat);
			r.add("AMAUN_BAYARAN", txtAmaunEFT);
			r.add("NO_EFT", txtNoEFT);
			r.add("CARA_BAYAR", cara_bayar);
			r.add("ID_HAKMILIKPB", id_hakmilikpb);

			r.add("JENIS_AWARD", "");
			r.add("ULASAN",txtUlasanBayaran);

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLInsert("TBLPPTBAYARAN");

			myLogger.info("SQL INSERT BAYARAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void UpdateMaklumatBayaranEFT(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhSurat = (String) data.get("txdTarikhSurat");
		String txdTarikhTerimaSurat = (String) data.get("txdTarikhTerimaSurat");
		String txdTarikh = (String) data.get("txdTarikh");
		String txtNoRujSurat = (String) data.get("txtNoRujSurat");
		String id_bayaran = (String) data.get("id_bayaran");
		String txtNamaKementerian = (String) data.get("txtNamaKementerian");
		String txtNoEFT = (String) data.get("txtNoEFT");
		String txtAmaunEFT = (String) data.get("txtAmaunEFT");
		String cara_bayar = (String) data.get("cara_bayar");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		String id_Masuk = (String) data.get("id_Masuk");
		String txtUlasanBayaran = (String) data.get("txtUlasanBayaran");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_BAYARAN", id_bayaran);
			r.add("ULASAN",txtUlasanBayaran);
			if (txdTarikhSurat != "") {
				r.add("TARIKH_SURAT_EFT", r.unquote("to_date('"
						+ txdTarikhSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SURAT_EFT", "");
			}

			if (txdTarikhTerimaSurat != "") {
				r.add("TARIKH_TERIMA_EFT", r.unquote("to_date('"
						+ txdTarikhTerimaSurat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TERIMA_EFT", "");
			}

			if (txdTarikh != "") {
				r.add("TARIKH_BAYARAN_EFT", r.unquote("to_date('" + txdTarikh
						+ "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_BAYARAN_EFT", "");
			}

			r.add("NO_RUJUKAN_SURATEFT", txtNoRujSurat);
			r.add("AMAUN_BAYARAN", txtAmaunEFT);
			r.add("NO_EFT", txtNoEFT);
			r.add("CARA_BAYAR", cara_bayar);
			r.add("ID_HAKMILIKPB", id_hakmilikpb);

			r.add("JENIS_AWARD", "");

			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("TBLPPTBAYARAN");

			myLogger.info("SQL INSERT BAYARAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector maklumat_am_tanah = null;
	public Vector maklumat_am_tanah(String id_penarikanbalik, String id_hakmilik)
			throws Exception {
		maklumat_am_tanah = new Vector();
		Db db = null;
		maklumat_am_tanah.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT TU.KEROSAKAN_TANAH,TU.KETERANGAN_KEROSAKAN,TU.KEROSAKAN_TANAMAN,TU.KEROSAKAN_BANGUNAN,TU.KOS_DITANGGUNG,TU.LOT_KWSN_TERLIBAT,HM.ID_HAKMILIK,HM.ID_PENARIKANBALIK,TU.ID_TANAHUMUM,HM.NO_HAKMILIK,N.NAMA_NEGERI,D.NAMA_DAERAH,M.NAMA_MUKIM,"
					+ " TU.TARIKH_KEMASUKAN,TU.PERIHAL_SYIT,TU.TARIKH_PEMERIKSAAN, "
					+ " TU.FLAG_DLM_MAJLIS,TU.FLAG_LUAR_MAJLIS,TU.FLAG_DLM_SIMPANAN,TU.FLAG_LUAR_SIMPANAN,"
					+ " TU.FLAG_JENIS_TANAH,TU.NO_GAZET, "
					+ " TU.LOKASI_TANAH,TU.LOT_SELURUH_LOT,TU.LOT_JENIS_TANAMAN,TU.LOT_KEADAAN_TANAMAN,"
					+ " TU.LOT_BERHAMPIRAN,TU.ULASAN,TU.RUPABUMI_SELURUH_LOT, "
					+ " TU.RUPABUMI_KWSN_TERLIBAT,TU.MELIBATKAN_BANGUNAN,"
					+ " TU.KATEGORI_BANGUNAN,TU.NO_GAZET_DAERAH,TU.BILANGAN_BANGUNAN, "
					
					//TAMBAH v6.5
					+ " TU.PENDAHULUAN, TU.STATUS_TANAH, TU.JALAN_UTAMA, TU.JALAN_MASUK,TU.NAMA_TEMPAT, TU.JARAK_BANDAR, "
					+ " TU.PERUMAHAN, TU.INDUSTRI, TU.NAMA_PBT, TU.FLAG_PBT, TU.FLAG_REZAB_MELAYU, TU.TARIKH_AKHIR_LAWAT, TU.TARIKH_MULA_LAWAT, "
					+ " TU.PENDAHULUAN, TU.STATUS_TANAH, TU.JALAN_UTAMA, TU.JALAN_MASUK,TU.NAMA_TEMPAT, TU.JARAK_BANDAR, " + 
					"   TU.PERUMAHAN, TU.INDUSTRI, TU.NAMA_PBT, TU.FLAG_PBT, TU.FLAG_REZAB_MELAYU, TU.TARIKH_AKHIR_LAWAT, TU.TARIKH_MULA_LAWAT, " + 
					"   TU.FLAG_BUKIT, TU.FLAG_BELUKAR, TU.FLAG_DIUSAHA, TU.FLAG_HUTAN, TU.FLAG_JENIS_TANAH, TU.FLAG_LANDAI, TU.FLAG_LAPANG, TU.FLAG_LEMBAH, " + 
					"   TU.FLAG_LURAH, TU.FLAG_PAYA, TU.FLAG_SEMAK, TU.FLAG_TERBIAR, TU.KEADAAN_TANAH, TU.RUPABUMI, TU.FLAG_RENDAH, TU.FLAG_RATA"

					+ " FROM TBLPPTTANAHUMUM TU,TBLPPTPENARIKANBALIK PB,TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN P, "
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJMUKIM M,TBLPPTPENARIKANHAKMILIK PH  "
					+ " WHERE TU.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND TU.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND PB.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND HM.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND HM.ID_MUKIM = M.ID_MUKIM(+) "
					+ " AND HM.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND HM.ID_HAKMILIK = " + id_hakmilik + " "
					+ " AND PB.ID_PENARIKANBALIK = " + id_penarikanbalik + "" +
							" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK "+
							" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK ";

			myLogger.info("MAKLUMAT AM TANAH :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_TANAHUMUM", rs.getString("ID_TANAHUMUM") == null ? ""
						: rs.getString("ID_TANAHUMUM"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("PERIHAL_SYIT", rs.getString("PERIHAL_SYIT") == null ? ""
						: rs.getString("PERIHAL_SYIT"));
				h.put("FLAG_DLM_MAJLIS",
						rs.getString("FLAG_DLM_MAJLIS") == null ? "" : rs
								.getString("FLAG_DLM_MAJLIS"));
				h.put("FLAG_LUAR_MAJLIS",
						rs.getString("FLAG_LUAR_MAJLIS") == null ? "" : rs
								.getString("FLAG_LUAR_MAJLIS"));
				h.put("FLAG_DLM_SIMPANAN",
						rs.getString("FLAG_DLM_SIMPANAN") == null ? "" : rs
								.getString("FLAG_DLM_SIMPANAN"));
				h.put("FLAG_LUAR_SIMPANAN",
						rs.getString("FLAG_LUAR_SIMPANAN") == null ? "" : rs
								.getString("FLAG_LUAR_SIMPANAN"));
				h.put("FLAG_JENIS_TANAH",
						rs.getString("FLAG_JENIS_TANAH") == null ? "" : rs
								.getString("FLAG_JENIS_TANAH"));
				h.put("LOKASI_TANAH", rs.getString("LOKASI_TANAH") == null ? ""
						: rs.getString("LOKASI_TANAH"));
				h.put("NO_GAZET", rs.getString("NO_GAZET") == null ? "" : rs
						.getString("NO_GAZET"));
				h.put("NO_GAZET_DAERAH",
						rs.getString("NO_GAZET_DAERAH") == null ? "" : rs
								.getString("NO_GAZET_DAERAH"));
				h.put("LOT_SELURUH_LOT",
						rs.getString("LOT_SELURUH_LOT") == null ? "" : rs
								.getString("LOT_SELURUH_LOT"));
				h.put("LOT_JENIS_TANAMAN",
						rs.getString("LOT_JENIS_TANAMAN") == null ? "" : rs
								.getString("LOT_JENIS_TANAMAN"));
				h.put("LOT_KEADAAN_TANAMAN", rs
						.getString("LOT_KEADAAN_TANAMAN") == null ? "" : rs
						.getString("LOT_KEADAAN_TANAMAN"));
				h.put("LOT_BERHAMPIRAN",
						rs.getString("LOT_BERHAMPIRAN") == null ? "" : rs
								.getString("LOT_BERHAMPIRAN"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
						.getString("ULASAN"));
				h.put("RUPABUMI_SELURUH_LOT", rs
						.getString("RUPABUMI_SELURUH_LOT") == null ? "" : rs
						.getString("RUPABUMI_SELURUH_LOT"));
				h.put("RUPABUMI_KWSN_TERLIBAT", rs
						.getString("RUPABUMI_KWSN_TERLIBAT") == null ? "" : rs
						.getString("RUPABUMI_KWSN_TERLIBAT"));
				h.put("MELIBATKAN_BANGUNAN", rs
						.getString("MELIBATKAN_BANGUNAN") == null ? "" : rs
						.getString("MELIBATKAN_BANGUNAN"));
				h.put("KATEGORI_BANGUNAN",
						rs.getString("KATEGORI_BANGUNAN") == null ? "" : rs
								.getString("KATEGORI_BANGUNAN"));
				h.put("BILANGAN_BANGUNAN",
						rs.getString("BILANGAN_BANGUNAN") == null ? "" : rs
								.getString("BILANGAN_BANGUNAN"));

				h.put("TARIKH_KEMASUKAN",
						rs.getString("TARIKH_KEMASUKAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_KEMASUKAN")));
				h.put("TARIKH_PEMERIKSAAN",
						rs.getString("TARIKH_PEMERIKSAAN") == null ? ""
								: Format.format(rs
										.getDate("TARIKH_PEMERIKSAAN")));
				
				h.put("LOT_KWSN_TERLIBAT", rs.getString("LOT_KWSN_TERLIBAT") == null ? ""
						: rs.getString("LOT_KWSN_TERLIBAT"));				
				
				h.put("KEROSAKAN_TANAH",
						rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
								.getString("KEROSAKAN_TANAH"));
				
				h.put("KETERANGAN_KEROSAKAN",
						rs.getString("KETERANGAN_KEROSAKAN") == null ? "" : rs
								.getString("KETERANGAN_KEROSAKAN"));
				
				h.put("KEROSAKAN_TANAMAN",
						rs.getString("KEROSAKAN_TANAMAN") == null ? "" : rs
								.getString("KEROSAKAN_TANAMAN"));				
				
				h.put("KEROSAKAN_BANGUNAN",
						rs.getString("KEROSAKAN_BANGUNAN") == null ? "" : rs
								.getString("KEROSAKAN_BANGUNAN"));
				
				h.put("KOS_DITANGGUNG",
						rs.getString("KOS_DITANGGUNG") == null ? "" : rs
								.getString("KOS_DITANGGUNG"));
				
				//TAMBAH v6.5
				h.put("PENDAHULUAN", rs.getString("PENDAHULUAN") == null ? "" : rs
					.getString("PENDAHULUAN"));
				h.put("STATUS_TANAH", rs.getString("STATUS_TANAH") == null ? "" : rs
					.getString("STATUS_TANAH"));
				h.put("JALAN_UTAMA", rs.getString("JALAN_UTAMA") == null ? "" : rs
					.getString("JALAN_UTAMA"));
				h.put("JALAN_MASUK", rs.getString("JALAN_MASUK") == null ? "" : rs
					.getString("JALAN_MASUK"));
				h.put("NAMA_TEMPAT", rs.getString("NAMA_TEMPAT") == null ? "" : rs
					.getString("NAMA_TEMPAT"));
				h.put("JARAK_BANDAR", rs.getString("JARAK_BANDAR") == null ? "" : rs
					.getString("JARAK_BANDAR"));
				h.put("PERUMAHAN", rs.getString("PERUMAHAN") == null ? "" : rs
					.getString("PERUMAHAN"));
				h.put("INDUSTRI", rs.getString("INDUSTRI") == null ? "" : rs
					.getString("INDUSTRI"));
				h.put("NAMA_PBT", rs.getString("NAMA_PBT") == null ? "" : rs
					.getString("NAMA_PBT"));
				h.put("sorPBT", rs.getString("flag_pbt")==null?"0":rs
					.getString("flag_pbt"));
				h.put("tarikh_akhir_lawat", rs.getDate("tarikh_akhir_lawat")==null?"":
					Format.format(rs.getDate("tarikh_akhir_lawat")));
				h.put("tarikh_mula_lawat", rs.getDate("tarikh_mula_lawat")==null?"":
					Format.format(rs.getDate("tarikh_mula_lawat")));
				h.put("keadaan_tanah", rs.getString("keadaan_tanah")==null?"0":rs.getString("keadaan_tanah"));
				h.put("rupabumi", rs.getString("rupabumi")==null?"0":rs.getString("rupabumi"));
				//flag checkbox
				h.put("flag_diusaha", rs.getString("flag_diusaha")==null?"0":rs.getString("flag_diusaha"));
				h.put("flag_lembah", rs.getString("flag_lembah")==null?"0":rs.getString("flag_lembah"));
				h.put("flag_lurah", rs.getString("flag_lurah")==null?"0":rs.getString("flag_lurah"));
				h.put("flag_paya", rs.getString("flag_paya")==null?"0":rs.getString("flag_paya"));
				h.put("flag_rendah", rs.getString("flag_rendah")==null?"0":rs.getString("flag_rendah"));
				h.put("flag_rata", rs.getString("flag_rata")==null?"0":rs.getString("flag_rata"));
				
				h.put("flag_landai", rs.getString("flag_landai")==null?"0":rs.getString("flag_landai"));
				h.put("flag_bukit", rs.getString("flag_bukit")==null?"0":rs.getString("flag_bukit"));
				h.put("flag_semak", rs.getString("flag_semak")==null?"0":rs.getString("flag_semak"));
				h.put("flag_belukar", rs.getString("flag_belukar")==null?"0":rs.getString("flag_belukar"));
				h.put("flag_hutan", rs.getString("flag_hutan")==null?"0":rs.getString("flag_hutan"));
				h.put("flag_terbiar", rs.getString("flag_terbiar")==null?"0":rs.getString("flag_terbiar"));
				h.put("flag_lapang", rs.getString("flag_lapang")==null?"0":rs.getString("flag_lapang"));
				
				

				maklumat_am_tanah.addElement(h);
			}
			return maklumat_am_tanah;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_am_tanah_permohonan = null;
	public Vector maklumat_am_tanah_permohonan(String id_permohonan)
			throws Exception {
		maklumat_am_tanah_permohonan = new Vector();
		Db db = null;
		maklumat_am_tanah_permohonan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT TU.KEROSAKAN_TANAH,TU.KEROSAKAN_TANAMAN,TU.KEROSAKAN_BANGUNAN,TU.KOS_DITANGGUNG,TU.LOT_KWSN_TERLIBAT,TU.ID_TANAHUMUM,"
					+ " TU.TARIKH_KEMASUKAN,TU.PERIHAL_SYIT,TU.TARIKH_PEMERIKSAAN, "
					+ " TU.FLAG_DLM_MAJLIS,TU.FLAG_LUAR_MAJLIS,TU.FLAG_DLM_SIMPANAN,TU.FLAG_LUAR_SIMPANAN,"
					+ " TU.FLAG_JENIS_TANAH,TU.NO_GAZET, "
					+ " TU.LOKASI_TANAH,TU.LOT_SELURUH_LOT,TU.LOT_JENIS_TANAMAN,TU.LOT_KEADAAN_TANAMAN,"
					+ " TU.LOT_BERHAMPIRAN,TU.ULASAN,TU.RUPABUMI_SELURUH_LOT, "
					+ " TU.RUPABUMI_KWSN_TERLIBAT,TU.MELIBATKAN_BANGUNAN,"
					+ " TU.KATEGORI_BANGUNAN,TU.NO_GAZET_DAERAH,TU.BILANGAN_BANGUNAN "
					+" FROM TBLPPTTANAHUMUM TU,TBLPPTPERMOHONAN P "
					+ "   "
					+ "  WHERE TU.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND P.ID_PERMOHONAN = '"+id_permohonan+"'";
			

			myLogger.info("MAKLUMAT AM TANAH permohonan :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
			
				h.put("ID_TANAHUMUM", rs.getString("ID_TANAHUMUM") == null ? ""
						: rs.getString("ID_TANAHUMUM"));
				
				h.put("LOT_KWSN_TERLIBAT", rs.getString("LOT_KWSN_TERLIBAT") == null ? ""
						: rs.getString("LOT_KWSN_TERLIBAT"));
				
				h.put("PERIHAL_SYIT", rs.getString("PERIHAL_SYIT") == null ? ""
						: rs.getString("PERIHAL_SYIT"));
				h.put("FLAG_DLM_MAJLIS",
						rs.getString("FLAG_DLM_MAJLIS") == null ? "" : rs
								.getString("FLAG_DLM_MAJLIS"));
				h.put("FLAG_LUAR_MAJLIS",
						rs.getString("FLAG_LUAR_MAJLIS") == null ? "" : rs
								.getString("FLAG_LUAR_MAJLIS"));
				h.put("FLAG_DLM_SIMPANAN",
						rs.getString("FLAG_DLM_SIMPANAN") == null ? "" : rs
								.getString("FLAG_DLM_SIMPANAN"));
				h.put("FLAG_LUAR_SIMPANAN",
						rs.getString("FLAG_LUAR_SIMPANAN") == null ? "" : rs
								.getString("FLAG_LUAR_SIMPANAN"));
				h.put("FLAG_JENIS_TANAH",
						rs.getString("FLAG_JENIS_TANAH") == null ? "" : rs
								.getString("FLAG_JENIS_TANAH"));
				h.put("LOKASI_TANAH", rs.getString("LOKASI_TANAH") == null ? ""
						: rs.getString("LOKASI_TANAH"));
				h.put("NO_GAZET", rs.getString("NO_GAZET") == null ? "" : rs
						.getString("NO_GAZET"));
				h.put("NO_GAZET_DAERAH",
						rs.getString("NO_GAZET_DAERAH") == null ? "" : rs
								.getString("NO_GAZET_DAERAH"));
				h.put("LOT_SELURUH_LOT",
						rs.getString("LOT_SELURUH_LOT") == null ? "" : rs
								.getString("LOT_SELURUH_LOT"));
				h.put("LOT_JENIS_TANAMAN",
						rs.getString("LOT_JENIS_TANAMAN") == null ? "" : rs
								.getString("LOT_JENIS_TANAMAN"));
				h.put("LOT_KEADAAN_TANAMAN", rs
						.getString("LOT_KEADAAN_TANAMAN") == null ? "" : rs
						.getString("LOT_KEADAAN_TANAMAN"));
				h.put("LOT_BERHAMPIRAN",
						rs.getString("LOT_BERHAMPIRAN") == null ? "" : rs
								.getString("LOT_BERHAMPIRAN"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs
						.getString("ULASAN"));
				h.put("RUPABUMI_SELURUH_LOT", rs
						.getString("RUPABUMI_SELURUH_LOT") == null ? "" : rs
						.getString("RUPABUMI_SELURUH_LOT"));
				h.put("RUPABUMI_KWSN_TERLIBAT", rs
						.getString("RUPABUMI_KWSN_TERLIBAT") == null ? "" : rs
						.getString("RUPABUMI_KWSN_TERLIBAT"));
				h.put("MELIBATKAN_BANGUNAN", rs
						.getString("MELIBATKAN_BANGUNAN") == null ? "" : rs
						.getString("MELIBATKAN_BANGUNAN"));
				h.put("KATEGORI_BANGUNAN",
						rs.getString("KATEGORI_BANGUNAN") == null ? "" : rs
								.getString("KATEGORI_BANGUNAN"));
				h.put("BILANGAN_BANGUNAN",
						rs.getString("BILANGAN_BANGUNAN") == null ? "" : rs
								.getString("BILANGAN_BANGUNAN"));

				h.put("TARIKH_KEMASUKAN",
						rs.getString("TARIKH_KEMASUKAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_KEMASUKAN")));
				h.put("TARIKH_PEMERIKSAAN",
						rs.getString("TARIKH_PEMERIKSAAN") == null ? ""
								: Format.format(rs
										.getDate("TARIKH_PEMERIKSAAN")));
				
				h.put("KEROSAKAN_TANAH",
						rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
								.getString("KEROSAKAN_TANAH"));
				
				h.put("KEROSAKAN_TANAMAN",
						rs.getString("KEROSAKAN_TANAMAN") == null ? "" : rs
								.getString("KEROSAKAN_TANAMAN"));				
				
				h.put("KEROSAKAN_BANGUNAN",
						rs.getString("KEROSAKAN_BANGUNAN") == null ? "" : rs
								.getString("KEROSAKAN_BANGUNAN"));
				
				h.put("KOS_DITANGGUNG",
						rs.getString("KOS_DITANGGUNG") == null ? "" : rs
								.getString("KOS_DITANGGUNG"));

				maklumat_am_tanah_permohonan.addElement(h);
			}
			return maklumat_am_tanah_permohonan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_hakmilik = null;

	public Vector maklumat_hakmilik(String id_hakmilik) throws Exception {
		maklumat_hakmilik = new Vector();
		Db db = null;
		maklumat_hakmilik.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.LUAS_LOT_TARIK,H.ID_HAKMILIK,H.ID_PENARIKANBALIK,H.NO_PT," +
					//"H.NO_LOT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
			//" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ "M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL,H.FLAG_PENARIKAN_BALIK "
					+ " ,N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT "
					+ "FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLRUJLOT JL "
					+ " ,TBLRUJNEGERI N,TBLRUJDAERAH D "
					+ "WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ "AND H.ID_MUKIM = M.ID_MUKIM "
			//		+ "AND H.FLAG_PENARIKAN_BALIK = 'Y' "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ "AND H.ID_HAKMILIK = '" + id_hakmilik + "' ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}

				
				h.put("FLAG_PEMBATALAN",
						rs.getString("FLAG_PENARIKAN_BALIK") == null ? "" : rs
								.getString("FLAG_PENARIKAN_BALIK"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				maklumat_hakmilik.addElement(h);
			}
			return maklumat_hakmilik;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void addMaklumatAmTanah(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhKemasukan = (String) data.get("txdTarikhKemasukan");
		String txdTarikhPemeriksaan = (String) data.get("txdTarikhPemeriksaan");
		String txtbilsyit = (String) data.get("txtbilsyit");
		String sorJenisTanah = (String) data.get("sorJenisTanah");
		String sorKedudukan = (String) data.get("sorKedudukan");
		String txtNoGazet = (String) data.get("txtNoGazet");
		String sorJenisGazet = (String) data.get("sorJenisGazet");
		String txtNoGazetDaerah = (String) data.get("txtNoGazetDaerah");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_hakmilik = (String) data.get("id_hakmilik");
		
		//TAMBAH v6.5
		String txtPendahuluan = (String) data.get("txtPendahuluan");
		String txtStatusTanah = (String) data.get("txtStatusTanah");
		String txtJalanUtama = (String) data.get("txtJalanUtama");
		String txtJalanMasuk = (String) data.get("txtJalanMasuk");
		String txtNamaTempat = (String) data.get("txtNamaTempat");
		String txtJarak = (String) data.get("txtJarak");
		String txtPerumahan = (String) data.get("txtPerumahan");
		String txtIndustri = (String) data.get("txtIndustri");
		String txtNamaPBT = (String) data.get("txtNamaPBT");
		String txtLokasi = (String) data.get("txtLokasi");
		String txdTarikhLawatMula = (String)data.get("txdTarikhLawatMula");
		String txdTarikhLawatAkhir = (String)data.get("txdTarikhLawatAkhir");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("PERIHAL_SYIT", txtbilsyit);
			r.add("FLAG_JENIS_TANAH", sorJenisTanah);
			r.add("NO_GAZET", txtNoGazet);
			r.add("NO_GAZET_DAERAH", txtNoGazetDaerah);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			
			r.add("PENDAHULUAN", txtPendahuluan);
			r.add("STATUS_TANAH", txtStatusTanah);
			r.add("JALAN_UTAMA", txtJalanUtama);
			r.add("JALAN_MASUK", txtJalanMasuk);
			r.add("NAMA_TEMPAT", txtNamaTempat);
			r.add("JARAK_BANDAR", txtJarak);
			r.add("PERUMAHAN", txtPerumahan);
			r.add("INDUSTRI", txtIndustri);
			r.add("NAMA_PBT", txtNamaPBT);
			r.add("LOKASI_TANAH", txtLokasi);

			if (sorKedudukan.equals("1")) {
				r.add("FLAG_DLM_SIMPANAN", "");
				r.add("FLAG_LUAR_SIMPANAN", "1");
			} else if (sorKedudukan.equals("2")) {
				r.add("FLAG_DLM_SIMPANAN", "1");
				r.add("FLAG_LUAR_SIMPANAN", "");
			} else {
				r.add("FLAG_DLM_SIMPANAN", "");
				r.add("FLAG_LUAR_SIMPANAN", "");
			}

			if (sorJenisGazet.equals("1")) {
				r.add("FLAG_DLM_MAJLIS", "");
				r.add("FLAG_LUAR_MAJLIS", "1");
			} else if (sorJenisGazet.equals("2")) {
				r.add("FLAG_DLM_MAJLIS", "1");
				r.add("FLAG_LUAR_MAJLIS", "");
			} else {
				r.add("FLAG_DLM_MAJLIS", "");
				r.add("FLAG_LUAR_MAJLIS", "");
			}

			if (txdTarikhKemasukan != "") {
				r.add("TARIKH_KEMASUKAN", r.unquote("to_date('"
						+ txdTarikhKemasukan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_KEMASUKAN", "");
			}

			if (txdTarikhPemeriksaan != "") {
				r.add("TARIKH_PEMERIKSAAN", r.unquote("to_date('"
						+ txdTarikhPemeriksaan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_PEMERIKSAAN", "");
			}

			r.add("ID_HAKMILIK", id_hakmilik);
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppttanahumum");
			myLogger.info("SQL INSERT TANAH UMUM :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateMaklumatAmTanah(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txdTarikhKemasukan = (String) data.get("txdTarikhKemasukan");
		String txdTarikhPemeriksaan = (String) data.get("txdTarikhPemeriksaan");
		String txtbilsyit = (String) data.get("txtbilsyit");
		String sorJenisTanah = (String) data.get("sorJenisTanah");
		String sorKedudukan = (String) data.get("sorKedudukan");
		String txtNoGazet = (String) data.get("txtNoGazet");
		String txtNoGazetDaerah = (String) data.get("txtNoGazetDaerah");
		String sorJenisGazet = (String) data.get("sorJenisGazet");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_tanahumum = (String) data.get("id_tanahumum");
		String id_hakmilik = (String) data.get("id_hakmilik");
			
		
		//TAMBAH v6.5
		String txtPendahuluan = (String) data.get("txtPendahuluan");
		String txtStatusTanah = (String) data.get("txtStatusTanah");
		String txtJalanUtama = (String) data.get("txtJalanUtama");
		String txtJalanMasuk = (String) data.get("txtJalanMasuk");
		String txtNamaTempat = (String) data.get("txtNamaTempat");
		String txtJarak = (String) data.get("txtJarak");
		String txtPerumahan = (String) data.get("txtPerumahan");
		String txtIndustri = (String) data.get("txtIndustri");
		String txtNamaPBT = (String) data.get("txtNamaPBT");
		String txtLokasi = (String) data.get("txtLokasi");
		String sorPBT = (String) data.get("sorPBT");
		String txdTarikhLawatMula = (String)data.get("txdTarikhLawatMula");
		String txdTarikhLawatAkhir = (String)data.get("txdTarikhLawatAkhir");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_TANAHUMUM", id_tanahumum);
			r.add("PERIHAL_SYIT", txtbilsyit);
			r.add("FLAG_JENIS_TANAH", sorJenisTanah);
			r.add("NO_GAZET", txtNoGazet);
			r.add("NO_GAZET_DAERAH", txtNoGazetDaerah);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("PENDAHULUAN", txtPendahuluan);
			r.add("STATUS_TANAH", txtStatusTanah);
			r.add("JALAN_UTAMA", txtJalanUtama);
			r.add("JALAN_MASUK", txtJalanMasuk);
			r.add("NAMA_TEMPAT", txtNamaTempat);
			r.add("JARAK_BANDAR", txtJarak);
			r.add("PERUMAHAN", txtPerumahan);
			r.add("INDUSTRI", txtIndustri);
			r.add("NAMA_PBT", txtNamaPBT);
			r.add("LOKASI_TANAH", txtLokasi);
			r.add("FLAG_PBT", sorPBT);
			

			if (sorKedudukan.equals("1")) {
				r.add("FLAG_DLM_SIMPANAN", "");
				r.add("FLAG_LUAR_SIMPANAN", "1");
			} else if (sorKedudukan.equals("2")) {
				r.add("FLAG_DLM_SIMPANAN", "1");
				r.add("FLAG_LUAR_SIMPANAN", "");
			} else {
				r.add("FLAG_DLM_SIMPANAN", "");
				r.add("FLAG_LUAR_SIMPANAN", "");
			}

			if (sorJenisGazet.equals("1")) {
				r.add("FLAG_DLM_MAJLIS", "");
				r.add("FLAG_LUAR_MAJLIS", "1");
			} else if (sorJenisGazet.equals("2")) {
				r.add("FLAG_DLM_MAJLIS", "1");
				r.add("FLAG_LUAR_MAJLIS", "");
			} else {
				r.add("FLAG_DLM_MAJLIS", "");
				r.add("FLAG_LUAR_MAJLIS", "");
			}

			if (txdTarikhKemasukan != "") {
				r.add("TARIKH_KEMASUKAN", r.unquote("to_date('"
						+ txdTarikhKemasukan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_KEMASUKAN", "");
			}

			if (txdTarikhPemeriksaan != "") {
				r.add("TARIKH_PEMERIKSAAN", r.unquote("to_date('"
						+ txdTarikhPemeriksaan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_PEMERIKSAAN", "");
			}

			r.add("ID_HAKMILIK", id_hakmilik);

			r.add("ID_KEMASKINI", id_Masuk);

			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppttanahumum");
			myLogger.info("SQL UPDATE TANAH UMUM :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteMaklumatAm(String id_tanahumum) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " DELETE FROM TBLPPTTANAHUMUM WHERE ID_TANAHUMUM = "
					+ id_tanahumum;
			myLogger.info("SQL DELETE TANAH UMUM :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void addPerihalTanah(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txtLokasiTanah = (String) data.get("txtLokasiTanah");
		String txtKeadaanLot = (String) data.get("txtKeadaanLot");
		String txtJenisTanaman = (String) data.get("txtJenisTanaman");
		String txtBerhampiran = (String) data.get("txtBerhampiran");
		String txtKeadaanTanaman = (String) data.get("txtKeadaanTanaman");
		String txtUlasan = (String) data.get("txtUlasan");
		String txtKeseluruhanLot = (String) data.get("txtKeseluruhanLot");
		String txtKawasan = (String) data.get("txtKawasan");
		String sorBangunan = (String) data.get("sorBangunan");
		String txtBilBangunan = (String) data.get("sorBangunan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_hakmilik = (String) data.get("id_hakmilik");
		String txtKawasanTerlibat = (String) data.get("txtKawasanTerlibat");
		
		//TAMBAH v6.5
		String flagBukit = (String)data.get("flagBukit");
		String flagLandai = (String)data.get("flagLandai");
		String flagRata = (String)data.get("flagRata");
		String flagRendah = (String)data.get("flagRendah");
		String flagBerpaya = (String)data.get("flagBerpaya");
		String flagLurah = (String)data.get("flagLurah");
		String flagLembah = (String)data.get("flagLembah");
		String txtPerihalRupabumi = (String)data.get("txtPerihalRupabumi");
		String txtKemudahan = (String)data.get("txtKemudahan");
		String txtTanaman = (String)data.get("txtTanaman");
		String txtHalangan = (String)data.get("txtHalangan");
		String txtPerihalKeadaan = (String)data.get("txtPerihalKeadaan");
		String flagSemak = (String)data.get("flagSemak");
		String flagBelukar = (String)data.get("flagBelukar");
		String flagHutan = (String)data.get("flagHutan");
		String flagTerbiar = (String)data.get("flagTerbiar");
		String flagLapang = (String)data.get("flagLapang");	    		
		String flagUsaha = (String)data.get("flagUsaha");
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("LOT_KWSN_TERLIBAT", txtKawasanTerlibat);
			r.add("LOKASI_TANAH", txtLokasiTanah);
			r.add("LOT_SELURUH_LOT", txtKeadaanLot);
			r.add("LOT_JENIS_TANAMAN", txtJenisTanaman);
			r.add("LOT_BERHAMPIRAN", txtBerhampiran);
			r.add("LOT_KEADAAN_TANAMAN", txtKeadaanTanaman);
			r.add("ULASAN", txtUlasan);
			r.add("RUPABUMI_SELURUH_LOT", txtKeseluruhanLot);
			r.add("RUPABUMI_KWSN_TERLIBAT", txtKawasan);
			r.add("MELIBATKAN_BANGUNAN", sorBangunan);
			r.add("BILANGAN_BANGUNAN", txtBilBangunan);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			
			//TAMBAH v6.5
			r.add("flag_lapang",flagLapang);
    		r.add("flag_terbiar",flagTerbiar);
    		r.add("flag_hutan",flagHutan);
    		r.add("flag_belukar",flagBelukar);
    		r.add("flag_semak",flagSemak);
    		r.add("keadaan_tanah",txtPerihalKeadaan);
    		r.add("halangan",txtHalangan);
    		r.add("tanaman",txtTanaman);
    		r.add("flag_bukit",flagBukit);
    		r.add("flag_landai",flagLandai);
    		r.add("flag_rata",flagRata);
    		r.add("flag_rendah",flagRendah);
    		r.add("flag_paya",flagBerpaya);
    		r.add("flag_lurah",flagLurah);
    		r.add("flag_lembah",flagLembah);
    		r.add("rupabumi",txtPerihalRupabumi);
    		r.add("flag_diusaha",flagUsaha);
    		
    		r.add("ID_HAKMILIK", id_hakmilik);
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppttanahumum");
			myLogger.info("SQL INSERT Perihal :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePerihalTanah(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String id_tanahumum = (String) data.get("id_tanahumum");
		String txtLokasiTanah = (String) data.get("txtLokasiTanah");
		String txtKeadaanLot = (String) data.get("txtKeadaanLot");
		String txtJenisTanaman = (String) data.get("txtJenisTanaman");
		String txtBerhampiran = (String) data.get("txtBerhampiran");
		String txtKeadaanTanaman = (String) data.get("txtKeadaanTanaman");
		String txtUlasan = (String) data.get("txtUlasan");
		String txtKeseluruhanLot = (String) data.get("txtKeseluruhanLot");
		String txtKawasan = (String) data.get("txtKawasan");
		String sorBangunan = (String) data.get("sorBangunan");
		String txtBilBangunan = (String) data.get("sorBangunan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_hakmilik = (String) data.get("id_hakmilik");
		String txtKawasanTerlibat = (String) data.get("txtKawasanTerlibat");
		
		//TAMBAH v6.5
		String flagBukit = (String)data.get("flagBukit");
		String flagLandai = (String)data.get("flagLandai");
		String flagRata = (String)data.get("flagRata");
		String flagRendah = (String)data.get("flagRendah");
		String flagBerpaya = (String)data.get("flagBerpaya");
		String flagLurah = (String)data.get("flagLurah");
		String flagLembah = (String)data.get("flagLembah");
		String txtPerihalRupabumi = (String)data.get("txtPerihalRupabumi");
		String txtKemudahan = (String)data.get("txtKemudahan");
		String txtTanaman = (String)data.get("txtTanaman");
		String txtHalangan = (String)data.get("txtHalangan");
		String txtPerihalKeadaan = (String)data.get("txtPerihalKeadaan");
		String flagSemak = (String)data.get("flagSemak");
		String flagBelukar = (String)data.get("flagBelukar");
		String flagHutan = (String)data.get("flagHutan");
		String flagTerbiar = (String)data.get("flagTerbiar");
		String flagLapang = (String)data.get("flagLapang");	    		
		String flagUsaha = (String)data.get("flagUsaha");
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_TANAHUMUM", id_tanahumum);
			r.add("LOT_KWSN_TERLIBAT", txtKawasanTerlibat);
			r.add("LOKASI_TANAH", txtLokasiTanah);
			r.add("LOT_SELURUH_LOT", txtKeadaanLot);
			r.add("LOT_JENIS_TANAMAN", txtJenisTanaman);
			r.add("LOT_BERHAMPIRAN", txtBerhampiran);
			r.add("LOT_KEADAAN_TANAMAN", txtKeadaanTanaman);
			r.add("ULASAN", txtUlasan);
			r.add("RUPABUMI_SELURUH_LOT", txtKeseluruhanLot);
			r.add("RUPABUMI_KWSN_TERLIBAT", txtKawasan);
			r.add("MELIBATKAN_BANGUNAN", sorBangunan);
			r.add("BILANGAN_BANGUNAN", txtBilBangunan);
			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_HAKMILIK", id_hakmilik);

			//TAMBAH v6.5
			r.add("flag_lapang",flagLapang);
    		r.add("flag_terbiar",flagTerbiar);
    		r.add("flag_hutan",flagHutan);
    		r.add("flag_belukar",flagBelukar);
    		r.add("flag_semak",flagSemak);
    		r.add("keadaan_tanah",txtPerihalKeadaan);
    		r.add("halangan",txtHalangan);
    		r.add("tanaman",txtTanaman);
    		r.add("flag_bukit",flagBukit);
    		r.add("flag_landai",flagLandai);
    		r.add("flag_rata",flagRata);
    		r.add("flag_rendah",flagRendah);
    		r.add("flag_paya",flagBerpaya);
    		r.add("flag_lurah",flagLurah);
    		r.add("flag_lembah",flagLembah);
    		r.add("rupabumi",txtPerihalRupabumi);
    		r.add("flag_diusaha",flagUsaha);
    		
			
			r.add("ID_KEMASKINI", id_Masuk);

			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppttanahumum");
			myLogger.info("SQL UPDATE Perihal :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector senarai_bangunan = null;

	public Vector senarai_bangunan(String id_hakmilik, String id_penarikan)
			throws Exception {
		senarai_bangunan = new Vector();
		Db db = null;
		senarai_bangunan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT HM.ID_HAKMILIK,PB.ID_PENARIKANBALIK,"
					+ " B.ID_BANGUNAN,B.NO_BANGUNAN,B.JENIS_BANGUNAN,B.SAIZ_BANGUNAN,B.NILAI_BANGUNAN, "
					+ " B.NO_UNIT_BANGUNAN,B.ALAMAT1,B.ALAMAT2,B.ALAMAT3,B.POSKOD,B.ID_NEGERI,B.LAIN_PERKARA,"
					+ " B.ID_BANDAR,N.NAMA_NEGERI,BN.KETERANGAN AS NAMA_BANDAR "
					+ " FROM TBLPPTBANGUNAN B,TBLPPTPENARIKANBALIK PB,TBLPPTHAKMILIK HM,TBLRUJNEGERI N,TBLRUJBANDAR BN "
					+ " WHERE HM.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND B.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND B.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND B.ID_BANDAR = BN.ID_BANDAR(+)"
					+ " AND HM.ID_HAKMILIK = '" + id_hakmilik + "' "
					+ " AND PB.ID_PENARIKANBALIK = '" + id_penarikan + "' ";

			myLogger.info("SENARAI BANGUNAN PENARIKAN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();

				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_BANGUNAN", rs.getString("ID_BANGUNAN") == null ? ""
						: rs.getString("ID_BANGUNAN"));
				h.put("NO_BANGUNAN", rs.getString("NO_BANGUNAN") == null ? ""
						: rs.getString("NO_BANGUNAN"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("SAIZ_BANGUNAN",
						rs.getString("SAIZ_BANGUNAN") == null ? "" : rs
								.getString("SAIZ_BANGUNAN"));
				h.put("NILAI_BANGUNAN",
						rs.getString("NILAI_BANGUNAN") == null ? "" : rs
								.getDouble("NILAI_BANGUNAN"));
				h.put("NO_UNIT_BANGUNAN",
						rs.getString("NO_UNIT_BANGUNAN") == null ? "" : rs
								.getString("NO_UNIT_BANGUNAN"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("LAIN_PERKARA", rs.getString("LAIN_PERKARA") == null ? ""
						: rs.getString("LAIN_PERKARA"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));

				senarai_bangunan.addElement(h);
			}
			return senarai_bangunan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector senarai_pihak_penting_bangunan = null;

	public Vector senarai_pihak_penting_bangunan(String id_hakmilik)
			throws Exception {
		senarai_pihak_penting_bangunan = new Vector();
		Db db = null;
		senarai_pihak_penting_bangunan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT PB1.NAMA_PB,PB1.ID_JENISPB,H1.ID_HAKMILIK,BPB.ID_BANGUNAN "
					+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIK H1,"
					+ " TBLPPTBANGUNAN B,TBLPPTBANGUNANPB BPB "
					+ " WHERE B.ID_HAKMILIK = H1.ID_HAKMILIK "
					+ " AND BPB.ID_BANGUNAN = B.ID_BANGUNAN "
					+ " AND BPB.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "
					+ " AND H1.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
					+ " ORDER BY NAMA_PB ASC ";

			myLogger.info("SQL PB BANGUNAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				h.put("ID_BANGUNAN", rs.getString("ID_BANGUNAN") == null ? ""
						: rs.getString("ID_BANGUNAN"));

				senarai_pihak_penting_bangunan.addElement(h);
			}
			return senarai_pihak_penting_bangunan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_negeri = null;

	public Vector getListnegeri() throws Exception {
		list_negeri = new Vector();
		list_negeri.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_NEGERI,KOD_MAMPU,NAMA_NEGERI,KOD_NEGERI FROM TBLRUJNEGERI ORDER BY KOD_MAMPU ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_NEGERI", rs.getInt("ID_NEGERI"));

				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("NAMA_NEGERI", "");
				} else {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")
							);
				}
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h
							.put("KOD_NEGERI", rs.getString("KOD_MAMPU")
									);
				}

				list_negeri.addElement(h);
			}
			return list_negeri;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_jenispb = null;

	public Vector list_jenispb() throws Exception {
		list_jenispb = new Vector();
		list_jenispb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JENISPB");
			r.add("KOD_JENIS_PB");
			r.add("KETERANGAN");
			r.add("JENIS_DAFTAR_PB");

			// sql = r.getSQLSelect("Tblrujnegeri", "KOD_NEGERI");
			sql = "SELECT ID_JENISPB,KOD_JENIS_PB,KETERANGAN,JENIS_DAFTAR_PB FROM TBLRUJJENISPB ORDER BY KOD_JENIS_PB ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_JENISPB", rs.getInt("ID_JENISPB"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("KETERANGAN", "");
				} else {
					h.put("KETERANGAN", rs.getString("KETERANGAN")
							);
				}
				if (rs.getString("KOD_JENIS_PB") == null) {
					h.put("KOD_JENIS_PB", "");
				} else {
					h.put("KOD_JENIS_PB", rs.getString("KOD_JENIS_PB")
							);
				}
				if (rs.getString("JENIS_DAFTAR_PB") == null) {
					h.put("JENIS_DAFTAR_PB", "");
				} else {
					h.put("JENIS_DAFTAR_PB", rs.getString("JENIS_DAFTAR_PB")
							);
				}

				list_jenispb.addElement(h);
			}
			return list_jenispb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_bank = null;

	public Vector list_bank() throws Exception {
		list_bank = new Vector();
		list_bank.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_BANK");
			r.add("KOD_BANK");
			r.add("NAMA_BANK");

			sql = "SELECT ID_BANK,KOD_BANK,NAMA_BANK FROM TBLRUJBANK ORDER BY NAMA_BANK ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_BANK", rs.getInt("ID_BANK"));

				if (rs.getString("NAMA_BANK") == null) {
					h.put("NAMA_BANK", "");
				} else {
					h.put("NAMA_BANK", rs.getString("NAMA_BANK"));
				}
				if (rs.getString("KOD_BANK") == null) {
					h.put("KOD_BANK", "");
				} else {
					h.put("KOD_BANK", rs.getString("KOD_BANK"));
				}

				list_bank.addElement(h);
			}
			return list_bank;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_jenisnopb = null;

	public Vector list_jenisnopb() throws Exception {
		list_jenisnopb = new Vector();
		list_jenisnopb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_JENISNOPB");
			r.add("KOD_JENIS_NOPB");
			r.add("KETERANGAN");

			sql = "SELECT ID_JENISNOPB,KOD_JENIS_NOPB,KETERANGAN FROM TBLRUJJENISNOPB ORDER BY KOD_JENIS_NOPB ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_JENISNOPB", rs.getInt("ID_JENISNOPB"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("KETERANGAN", "");
				} else {
					h.put("KETERANGAN", rs.getString("KETERANGAN")
							);
				}
				if (rs.getString("KOD_JENIS_NOPB") == null) {
					h.put("KOD_JENIS_NOPB", "");
				} else {
					h.put("KOD_JENIS_NOPB", rs.getString("KOD_JENIS_NOPB")
							);
				}

				list_jenisnopb.addElement(h);
			}
			return list_jenisnopb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_pegawai = null;


	
	
	/*public Vector list_pegawai(String negeriuser,String id_seksyen,String id_negeri,String role) throws Exception {
		list_pegawai = new Vector();
		list_pegawai.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PEGAWAI,NAMA_PEGAWAI,JAWATAN,ID_JAWATAN FROM TBLRUJPEGAWAI "
					+ "WHERE ID_SEKSYEN = '" + id_seksyen + "' ";
			       if(!negeriuser.equals("16"))
				    {
					sql += "AND  ID_NEGERI = '"+id_negeri+"' ";
				    }
					sql += "ORDER BY NAMA_PEGAWAI ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getInt("ID_PEGAWAI"));

				if (rs.getString("NAMA_PEGAWAI") == null) {
					h.put("NAMA_PEGAWAI", "");
				} else {
					h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI")
							);
				}
				if (rs.getString("JAWATAN") == null) {
					h.put("JAWATAN", "");
				} else {
					h.put("JAWATAN", rs.getString("JAWATAN"));
				}
				
				if (rs.getString("ID_JAWATAN") == null) {
					h.put("ID_JAWATAN", "");
				} else {
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN"));
				}

				list_pegawai.addElement(h);
			}
			return list_pegawai;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	*/
	
	public Vector list_pegawai(String negeriuser,String id_seksyen,String id_negeri,String role) throws Exception {
		list_pegawai = new Vector();
		list_pegawai.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
/*
			sql = "SELECT ID_PEGAWAI,NAMA_PEGAWAI,JAWATAN,ID_JAWATAN FROM TBLRUJPEGAWAI "
					+ "WHERE ID_SEKSYEN = '" + id_seksyen + "' ";
			       if(!negeriuser.equals("16"))
				    {
					sql += "AND  ID_NEGERI = '"+id_negeri+"' ";
				    }
					sql += "ORDER BY NAMA_PEGAWAI ASC";
					
					*/
						
			sql = "SELECT U.USER_ID,U.USER_NAME,J.KETERANGAN,J.ID_JAWATAN,UI.ID_NEGERI " +
					"FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
					"WHERE U.USER_ID = UI.USER_ID AND UI.ID_SEKSYEN = '1'" +
					"AND UI.ID_JAWATAN = J.ID_JAWATAN(+)";
			
			 if(!negeriuser.equals("16"))
			    {
				sql += "AND  UI.ID_NEGERI = '"+id_negeri+"' ";
			    }
			 
			 sql += "ORDER BY U.USER_NAME ASC";
			 
			 myLogger.info("SQL:"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getString("USER_ID"));

				if (rs.getString("USER_NAME") == null) {
					h.put("NAMA_PEGAWAI", "");
				} else {
					h.put("NAMA_PEGAWAI", rs.getString("USER_NAME").toUpperCase()
							);
				}
				if (rs.getString("KETERANGAN") == null) {
					h.put("JAWATAN", "");
				} else {
					h.put("JAWATAN", rs.getString("KETERANGAN"));
				}
				
				if (rs.getString("ID_JAWATAN") == null) {
					h.put("ID_JAWATAN", "");
				} else {
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN"));
				}

				list_pegawai.addElement(h);
			}
			return list_pegawai;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_pegawai_byid = null;
	public Vector list_pegawai_byid(String negeriuser,String id_pegawai,String id_seksyen,String id_negeri,String role) throws Exception {
		list_pegawai_byid = new Vector();
		list_pegawai_byid.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
/*
			sql = "SELECT ID_PEGAWAI,NAMA_PEGAWAI,JAWATAN,ID_JAWATAN FROM TBLRUJPEGAWAI "
					+ "WHERE ID_SEKSYEN = '" + id_seksyen + "' ";
			 	    if(!negeriuser.equals("16"))
				    {
					sql += "AND  ID_NEGERI = '"+id_negeri+"' AND ID_PEGAWAI = '"+id_pegawai+"' ";
				    }
					sql += "ORDER BY NAMA_PEGAWAI ASC";*/
			
			
			sql = "SELECT U.USER_ID,U.USER_NAME,J.KETERANGAN,J.ID_JAWATAN,UI.ID_NEGERI " +
			"FROM USERS U,USERS_INTERNAL UI,TBLRUJJAWATAN J " +
			"WHERE U.USER_ID = UI.USER_ID AND UI.ID_SEKSYEN = '1'" +
			"AND UI.ID_JAWATAN = J.ID_JAWATAN(+)";
	
	 if(!negeriuser.equals("16"))
	    {
		sql += "AND  UI.ID_NEGERI = '"+id_negeri+"' AND U.USER_ID = '"+id_pegawai+"' ";
	    }
	 
	 sql += "ORDER BY U.USER_NAME ASC";
			
			
					
					myLogger.info("SQL DROP PEGAWAI:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEGAWAI", rs.getString("USER_ID"));

				if (rs.getString("USER_NAME") == null) {
					h.put("NAMA_PEGAWAI", "");
				} else {
					h.put("NAMA_PEGAWAI", rs.getString("USER_NAME")
							);
				}
				
				if (rs.getString("USER_ID") == null) {
					h.put("ID_PEGAWAI", "");
				} else {
					h.put("ID_PEGAWAI", rs.getString("USER_ID")
							);
				}
				
				if (rs.getString("KETERANGAN") == null) {
					h.put("JAWATAN", "");
				} else {
					h.put("JAWATAN", rs.getString("KETERANGAN"));
				}
				
				if (rs.getString("ID_JAWATAN") == null) {
					h.put("ID_JAWATAN", "0");
				} else {
					h.put("ID_JAWATAN", rs.getString("ID_JAWATAN"));
				}

				list_pegawai_byid.addElement(h);
			}
			return list_pegawai_byid;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_jpph = null;


	public Vector list_jpph(String negeriuser,String id_seksyen,String id_negeri,String role) throws Exception {
		list_jpph = new Vector();
		list_jpph.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT P.ID_PEJABAT,P.NAMA_PEJABAT,D.NAMA_DAERAH FROM TBLRUJPEJABAT P,TBLRUJDAERAH D WHERE P.ID_SEKSYEN = '1' AND P.ID_JENISPEJABAT = '3' AND P.ID_DAERAH = D.ID_DAERAH "
					+ "";
			       if(!negeriuser.equals("16"))
				    {
					sql += "AND  P.ID_NEGERI = '"+id_negeri+"' ";
				    }
					sql += "ORDER BY NAMA_DAERAH ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_PEJABAT", rs.getInt("ID_PEJABAT"));

				if (rs.getString("NAMA_PEJABAT") == null) {
					h.put("NAMA_PEJABAT", "");
				} else {
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT")
							);
				}
				if (rs.getString("NAMA_DAERAH") == null) {
					h.put("NAMA_DAERAH", "");
				} else {
					h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));
				}

				list_jpph.addElement(h);
			}
			return list_jpph;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_bandar = null;

	public Vector getListBandarByNegeri(String idnegeri) throws Exception {
		list_bandar = new Vector();
		list_bandar.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR WHERE ID_NEGERI = '"
					+ idnegeri + "' ORDER BY KOD_BANDAR ASC";

			myLogger.info("FIND BANDAR :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_BANDAR", rs.getInt("ID_BANDAR"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("NAMA_BANDAR", "");
				} else {
					h.put("NAMA_BANDAR", rs.getString("KETERANGAN").toUpperCase()
							);
				}
				if (rs.getString("KOD_BANDAR") == null) {
					h.put("KOD_BANDAR", "");
				} else {
					h.put("KOD_BANDAR", rs.getString("KOD_BANDAR")
							);
				}

				list_bandar.addElement(h);
			}
			return list_bandar;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_siasatan = null;

	public Vector list_siasatan(String idpenarikan, String id_hakmilik)
			throws Exception {
		list_siasatan = new Vector();
		list_siasatan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT HM.NO_PT," +
				//	"HM.NO_LOT," +
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
		//	" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN JL.KETERANGAN || HM.NO_PT "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN "
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM, TBLRUJLOT JL,"
					+ "TBLPPTPENARIKANBALIK PB,USERS PG,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+ " AND PH.ID_HAKMILIK = HM.ID_HAKMILIK  "
					+ " AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND HM.ID_LOT = JL.ID_LOT(+) "					
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND PB.ID_PENARIKANBALIK = '"
					+ idpenarikan
					+ "'"
					+ " AND S.ID_HAKMILIK = '" + id_hakmilik + "'" + "";
			sql += " ORDER BY S.NO_KES ASC,S.BIL_SIASATAN ASC";
			myLogger.info("LIST SIASATAN :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;
				temp = bil + "a";

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("BILTEMP", temp);
				h.put("BILDUM", bil + "dum");
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));

				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("NO_LOT",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("NO_PT",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));

				list_siasatan.addElement(h);
			}
			return list_siasatan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_siasatan_penarikan = null;

	public Vector list_siasatan_penarikan(String idpenarikan) throws Exception {
		list_siasatan_penarikan = new Vector();
		list_siasatan_penarikan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT HM.NO_SUBJAKET,S.ID_PEGAWAI_SIASATAN," +
				//	"HM.NO_LOT," +
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
		//	" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN JL.KETERANGAN || HM.NO_PT "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"HM.NO_PT,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN "
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM, TBLRUJLOT JL,"
					+ "TBLPPTPENARIKANBALIK PB,USERS PG,TBLPPTPENARIKANHAKMILIK PH  "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK " 
					+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND HM.ID_LOT = JL.ID_LOT(+) "
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND PB.ID_PENARIKANBALIK = '" + idpenarikan + "'" +

					" ORDER BY LPAD(HM.NO_SUBJAKET,10) ASC,BIL_SIASATAN ASC ";

			myLogger.info("list_siasatan_penarikan" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;
				temp = bil + "a";

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("BILTEMP", temp);
				h.put("BILDUM", bil + "dum");
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));

				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));

				list_siasatan_penarikan.addElement(h);
			}
			return list_siasatan_penarikan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_siasatan = null;

	public Vector maklumat_siasatan(String id_siasatan) throws Exception {
		maklumat_siasatan = new Vector();
		maklumat_siasatan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT JHM.SIMPANAN,S.TARIKH_PERINTAH,S.NILAI_SEUNIT,S.UNIT_TANAH,HM.ID_JENISHAKMILIK,S.KETERANGAN_JPPH,S.AKUJANJI_AGENSI,S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN," +
					"PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
					+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
					+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH,"
					
					+" CASE "+
					" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
					" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
					" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "					
					+ " HM.NO_HAKMILIK,JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK" 
					
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
					+ " TBLRUJJENISHAKMILIK JH,TBLRUJLOT JL,"
					+ "TBLPPTPENARIKANBALIK PB,TBLRUJJENISHAKMILIK JHM,USERS PG,TBLPPTTANAH T,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK  AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+)"
					+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND HM.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND HM.ID_LOT = JL.ID_LOT(+)"
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
					+ " AND S.ID_SIASATAN = '" + id_siasatan + "'" + "";

			myLogger.info("MAKLUMAT SIASATAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				
				h.put("SIMPANAN",
						rs.getString("SIMPANAN") == null ? "" : rs
								.getString("SIMPANAN"));				
				h.put("TARIKH_PERINTAH",
						rs.getString("TARIKH_PERINTAH") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERINTAH")));				
				h.put("NILAI_SEUNIT",rs.getString("NILAI_SEUNIT") == null ? ""
						: rs.getDouble("NILAI_SEUNIT"));
				h.put("UNIT_TANAH",
						rs.getString("UNIT_TANAH") == null ? "" : rs
								.getString("UNIT_TANAH"));

				h.put("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? ""
						: rs.getString("ID_JENISHAKMILIK"));
				h.put("KETERANGAN_JPPH", rs.getString("KETERANGAN_JPPH") == null ? ""
						: rs.getString("KETERANGAN_JPPH"));
				h.put("AKUJANJI_AGENSI", rs.getString("AKUJANJI_AGENSI") == null ? ""
						: rs.getString("AKUJANJI_AGENSI"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
						: rs.getString("ULASAN_PERINTAH"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));

				h.put("HARGA_SEUNIT_SO",
						rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_SO"));
				h.put("UNIT_HARGA_SO",
						rs.getString("UNIT_HARGA_SO") == null ? "" : rs
								.getString("UNIT_HARGA_SO"));
				h.put("HARGA_PASARAN_SO",
						rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
								.getDouble("HARGA_PASARAN_SO"));
				h.put("BAYAR_PENJEJASAN",
						rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
								.getDouble("BAYAR_PENJEJASAN"));
				h.put("BAYAR_PECAHPISAH",
						rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
								.getDouble("BAYAR_PECAHPISAH"));
				h.put("BAYAR_NAIK_NILAISO",
						rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
								.getDouble("BAYAR_NAIK_NILAISO"));
				h.put("HARGA_SEUNIT_JPPH",
						rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_JPPH"));
				h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
						: rs.getString("UNIT_HARGA"));
				h.put("HARGA_PASARAN",
						rs.getString("HARGA_PASARAN") == null ? "" : rs
								.getDouble("HARGA_PASARAN"));
				h.put("AMAUN_PENJEJASAN_JPPH", rs
						.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PENJEJASAN_JPPH"));
				h.put("AMAUN_PECAHPISAH_JPPH", rs
						.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PECAHPISAH_JPPH"));
				h.put("NAIK_NILAI_JPPH",
						rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
								.getDouble("NAIK_NILAI_JPPH"));
				h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
						.getString("ID_TANAH"));
				
				h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
						.getString("KEROSAKAN_TANAH"));
				h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
						.getString("KOS_DITANGGUNG"));
				
				maklumat_siasatan.addElement(h);

			}
			return maklumat_siasatan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector maklumat_siasatanX = null;

	public Vector maklumat_siasatanX(String id_penarikan,int bilangan) throws Exception {
		maklumat_siasatanX = new Vector();
		maklumat_siasatanX.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
					+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
					+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH"
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
					+ "TBLPPTPENARIKANBALIK PB,USERS PG,TBLPPTTANAH T,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
				//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK "
					+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
					+ " AND S.BIL_SIASATAN = "+bilangan+" "
					+ " AND PB.ID_PENARIKANBALIK = '" + id_penarikan + "'" + "";

			myLogger.info("MAKLUMAT SIASATAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);

				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
						: rs.getString("ULASAN_PERINTAH"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));

				h.put("HARGA_SEUNIT_SO",
						rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_SO"));
				h.put("UNIT_HARGA_SO",
						rs.getString("UNIT_HARGA_SO") == null ? "" : rs
								.getString("UNIT_HARGA_SO"));
				h.put("HARGA_PASARAN_SO",
						rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
								.getDouble("HARGA_PASARAN_SO"));
				h.put("BAYAR_PENJEJASAN",
						rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
								.getDouble("BAYAR_PENJEJASAN"));
				h.put("BAYAR_PECAHPISAH",
						rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
								.getDouble("BAYAR_PECAHPISAH"));
				h.put("BAYAR_NAIK_NILAISO",
						rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
								.getDouble("BAYAR_NAIK_NILAISO"));
				h.put("HARGA_SEUNIT_JPPH",
						rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_JPPH"));
				h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
						: rs.getString("UNIT_HARGA"));
				h.put("HARGA_PASARAN",
						rs.getString("HARGA_PASARAN") == null ? "" : rs
								.getDouble("HARGA_PASARAN"));
				h.put("AMAUN_PENJEJASAN_JPPH", rs
						.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PENJEJASAN_JPPH"));
				h.put("AMAUN_PECAHPISAH_JPPH", rs
						.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PECAHPISAH_JPPH"));
				h.put("NAIK_NILAI_JPPH",
						rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
								.getDouble("NAIK_NILAI_JPPH"));
				h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
						.getString("ID_TANAH"));
				
				h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
						.getString("KEROSAKAN_TANAH"));
				h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
						.getString("KOS_DITANGGUNG"));
				
				maklumat_siasatanX.addElement(h);

			}
			return maklumat_siasatanX;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_siasatan_pb = null;
//#######
	public Vector maklumat_siasatan_pb(String id_hakmilikpb,String id_pembatalan) throws Exception {
		maklumat_siasatan_pb = new Vector();
		maklumat_siasatan_pb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT AW.ID_AWARD,AW.BAYAR_FEE,AW.BAYAR_PAMPASAN,AW.BAYAR_BANGUNAN,AW.BAYAR_LAIN2,AW.BAYAR_PECAHPISAH AS BAYAR_PECAHPISAH_P,AW.BAYAR_PENJEJASAN AS BAYAR_PENJEJASAN_P,AW.NAIK_NILAI,AW.BAYAR_TANAH,AW.NILAI_RUGI, " +
					"K.NAMA_KEMENTERIAN,B.NAMA_PB,B.NO_PB," +
				//	"HM.NO_LOT," +
					" CASE "+
					" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
				//	" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN JL.KETERANGAN || HM.NO_PT "+
					" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
					" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "+
					"HM.NO_PT,BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_BANK,HPB.NO_AKAUN," +
					" HPB.NAMA_BANK,M.NAMA_MUKIM,S.STATUS_TUNTUTAN,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
					+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
					+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
					+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
					+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
					+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
					+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
					+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH"
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,TBLRUJMUKIM M,TBLRUJKEMENTERIAN K,TBLPFDFAIL F,TBLPPTPERMOHONAN P,"
					+ " TBLPPTPENARIKANBALIK PB,TBLPPTBAHAGIANPB BPB,TBLRUJLOT JL,TBLPPTAWARD AW,USERS PG,TBLPPTPENARIKANHAKMILIK PH,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN B,TBLPPTTANAH T  "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
					+ " AND PB.ID_PENARIKANBALIK = '"+id_pembatalan+"' "
					+ " AND PB.ID_PERMOHONAN = P.ID_PERMOHONAN "
				    +"  AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
					+ " AND P.ID_FAIL = F.ID_FAIL "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN "
					+ " AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+ " AND PH.ID_HAKMILIK = S.ID_HAKMILIK"
					+"  AND S.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK"
					+ " AND HM.ID_MUKIM = M.ID_MUKIM "
					+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
					+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+)  AND HM.ID_LOT = JL.ID_LOT(+)"
					+ " AND HPB.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN"
					+ " AND AW.ID_HAKMILIKPB(+) = HPB.ID_HAKMILIKPB "
					+ " AND HPB.ID_HAKMILIKPB = '"
					+ id_hakmilikpb
					+ "'"
					+ " AND S.BIL_SIASATAN = ( "
					+ " SELECT MAX(S.BIL_SIASATAN) "
					+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,TBLPPTPENARIKANHAKMILIK PH,TBLPPTPENARIKANBALIK PB,TBLRUJPEGAWAI PG,TBLPPTHAKMILIKPB HPB  "
					+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK  AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK  "
					+ " AND PB.ID_PENARIKANBALIK = '"+id_pembatalan+"' AND PH.ID_HAKMILIK = S.ID_HAKMILIK AND S.ID_HAKMILIK = HM.ID_HAKMILIK  AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK AND S.ID_PEGAWAI_SIASATAN = PG.ID_PEGAWAI(+) "
					+ " AND HPB.ID_HAKMILIKPB = '" + id_hakmilikpb + "' )" +

					"";

			myLogger.info("MAKLUMAT SIASATAN PB:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);

				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));

				h.put("KETERANGAN_TUAN_TANAH", rs
						.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
						.getString("KETERANGAN_TUAN_TANAH"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
						.getString("NO_KES"));
				h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
						: rs.getString("NO_SIASATAN"));
				h.put("STATUS_SIASATAN",
						rs.getString("STATUS_SIASATAN") == null ? "" : rs
								.getString("STATUS_SIASATAN"));
				h.put("MASA_SIASATAN",
						rs.getString("MASA_SIASATAN") == null ? "" : rs
								.getString("MASA_SIASATAN"));
				h.put("TARIKH_SIASATAN",
						rs.getString("TARIKH_SIASATAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_SIASATAN")));
				h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
						.getString("TEMPAT"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ALASAN_TANGGUH",
						rs.getString("ALASAN_TANGGUH") == null ? "" : rs
								.getString("ALASAN_TANGGUH"));
				h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
						: rs.getString("ID_UNITLUAS"));
				h.put("BANTAHAN_TUANTNH",
						rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
								.getString("BANTAHAN_TUANTNH"));
				h.put("BANTAHAN_AGENSI",
						rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
								.getString("BANTAHAN_AGENSI"));
				h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
						: Format.format(rs.getDouble("NILAIAN_JPPH")));
				h.put("BANTAHAN_LAIN",
						rs.getString("BANTAHAN_LAIN") == null ? "" : rs
								.getString("BANTAHAN_LAIN"));
				h.put("TEMPOH_MILIK_TANAH",
						rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
								.getString("TEMPOH_MILIK_TANAH"));
				h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
						: rs.getString("CARA_MILIK"));
				h.put("JENIS_BANGUNAN",
						rs.getString("JENIS_BANGUNAN") == null ? "" : rs
								.getString("JENIS_BANGUNAN"));
				h.put("JENIS_TANAMAN",
						rs.getString("JENIS_TANAMAN") == null ? "" : rs
								.getString("JENIS_TANAMAN"));
				h.put("FLAG_PECAH_SEMPADAN", rs
						.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
						.getString("FLAG_PECAH_SEMPADAN"));
				h.put("FLAG_TUKAR_SYARAT",
						rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
								.getString("FLAG_TUKAR_SYARAT"));
				h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
						: rs.getDouble("HARGA_BELI"));
				h.put("STATUS_SEMASA",
						rs.getString("STATUS_SEMASA") == null ? "" : rs
								.getString("STATUS_SEMASA"));
				h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
						.getString("BEBANAN"));
				h.put("KETERANGAN_AGENSI",
						rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
								.getString("KETERANGAN_AGENSI"));
				h.put("KETERANGAN_JURUNILAI", rs
						.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
						.getString("KETERANGAN_JURUNILAI"));
				h.put("TARIKH_PECAH_SEMPADAN", rs
						.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
						: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
				h.put("TARIKH_TUKAR_SYARAT", rs
						.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
						.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
				h.put("TARIKH_AKHIR_TAMPAL", rs
						.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
				h.put("TUNTUTAN_TUANTNH",
						rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
								.getString("TUNTUTAN_TUANTNH"));
				h.put("TUNTUTAN_PB_BEBANAN", rs
						.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
						.getString("TUNTUTAN_PB_BEBANAN"));
				h.put("TUNTUTAN_PB_TDKDAFTAR", rs
						.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
						.getString("TUNTUTAN_PB_TDKDAFTAR"));
				h.put("TUNTUTAN_PB_LAIN",
						rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
								.getString("TUNTUTAN_PB_LAIN"));
				h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
						: rs.getString("ID_BORANGE"));
				h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
						.getString("PERINTAH"));
				h.put("ID_PEGAWAI_SIASATAN", rs
						.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
						.getString("ID_PEGAWAI_SIASATAN"));
				h.put("STATUS_TUNTUTAN",
						rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
								.getString("STATUS_TUNTUTAN"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("JENIS_WAKTU_SIASATAN", rs
						.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
						.getString("JENIS_WAKTU_SIASATAN"));
				h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
						: rs.getString("BIL_SIASATAN"));
				h.put("ULASAN_SIASATAN",
						rs.getString("ULASAN_SIASATAN") == null ? "" : rs
								.getString("ULASAN_SIASATAN"));

				h.put("HARGA_SEUNIT_SO",
						rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_SO"));
				h.put("UNIT_HARGA_SO",
						rs.getString("UNIT_HARGA_SO") == null ? "" : rs
								.getString("UNIT_HARGA_SO"));
				h.put("HARGA_PASARAN_SO",
						rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
								.getDouble("HARGA_PASARAN_SO"));
				h.put("BAYAR_PENJEJASAN",
						rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
								.getDouble("BAYAR_PENJEJASAN"));
				h.put("BAYAR_PECAHPISAH",
						rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
								.getDouble("BAYAR_PECAHPISAH"));
				h.put("BAYAR_NAIK_NILAISO",
						rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
								.getDouble("BAYAR_NAIK_NILAISO"));
				h.put("HARGA_SEUNIT_JPPH",
						rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
								.getDouble("HARGA_SEUNIT_JPPH"));
				h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
						: rs.getString("UNIT_HARGA"));
				h.put("HARGA_PASARAN",
						rs.getString("HARGA_PASARAN") == null ? "" : rs
								.getDouble("HARGA_PASARAN"));
				h.put("AMAUN_PENJEJASAN_JPPH", rs
						.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PENJEJASAN_JPPH"));
				h.put("AMAUN_PECAHPISAH_JPPH", rs
						.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
						.getDouble("AMAUN_PECAHPISAH_JPPH"));
				h.put("NAIK_NILAI_JPPH",
						rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
								.getDouble("NAIK_NILAI_JPPH"));
				h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
						.getString("ID_TANAH"));
				
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				
				h.put("ID_AWARD",
						rs.getString("ID_AWARD") == null ? "" : rs
								.getString("ID_AWARD"));				
				h.put("BAYAR_FEE",
						rs.getString("BAYAR_FEE") == null ? "" : rs
								.getDouble("BAYAR_FEE"));
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));
				h.put("BAYAR_BANGUNAN",
						rs.getString("BAYAR_BANGUNAN") == null ? "" : rs
								.getDouble("BAYAR_BANGUNAN"));
				h.put("BAYAR_PECAHPISAH_P",
						rs.getString("BAYAR_PECAHPISAH_P") == null ? "" : rs
								.getDouble("BAYAR_PECAHPISAH_P"));
				h.put("BAYAR_PENJEJASAN_P",
						rs.getString("BAYAR_PENJEJASAN_P") == null ? "" : rs
								.getDouble("BAYAR_PENJEJASAN_P"));
				h.put("NAIK_NILAI",
						rs.getString("NAIK_NILAI") == null ? "" : rs
								.getDouble("NAIK_NILAI"));
				h.put("BAYAR_TANAH",
						rs.getString("BAYAR_TANAH") == null ? "" : rs
								.getDouble("BAYAR_TANAH"));
				h.put("NILAI_RUGI",
						rs.getString("NILAI_RUGI") == null ? "" : rs
								.getDouble("NILAI_RUGI"));
				h.put("BAYAR_LAIN2",
						rs.getString("BAYAR_LAIN2") == null ? "" : rs
								.getDouble("BAYAR_LAIN2"));
				
				

				maklumat_siasatan_pb.addElement(h);

			}
			return maklumat_siasatan_pb;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector maklumat_pampasan_pb = null;
	public Vector maklumat_pampasan_pb(String id_hakmilikpb,String id_pembatalan) throws Exception {
		maklumat_pampasan_pb = new Vector();
		maklumat_pampasan_pb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT AW.BAYAR_PAMPASAN,PB.ID_PENARIKANBALIK,HM.ID_HAKMILIK,K.NAMA_KEMENTERIAN, "+
			" B.NAMA_PB,B.NO_PB," +
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
					" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
			"HM.NO_PT,BPB.SYER_ATAS, "+
			" BPB.SYER_BAWAH,HPB.ID_BANK,HPB.NO_AKAUN, HPB.NAMA_BANK,M.NAMA_MUKIM "+
			" FROM TBLPPTHAKMILIK HM,TBLRUJMUKIM M,TBLRUJKEMENTERIAN K,TBLPFDFAIL F,"+
			" TBLPPTPERMOHONAN P, "+
			" TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S, "+
			" TBLPPTPIHAKBERKEPENTINGAN B,TBLPPTAWARD AW,TBLRUJLOT JL,TBLPPTBAHAGIANPB BPB   "+
			" WHERE "+
			" PB.ID_PENARIKANBALIK = '"+id_pembatalan+"'  "+
			" AND PB.ID_PERMOHONAN = P.ID_PERMOHONAN  "+
			" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"+
			
			 " AND S.ID_HAKMILIK = HM.ID_HAKMILIK "
			+ " AND S.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
			+ " AND S.ID_SIASATAN = AW.ID_SIASATAN "+
			
			
			" AND P.ID_FAIL = F.ID_FAIL "+ 
			" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN  "+
			" AND PH.ID_HAKMILIK = HPB.ID_HAKMILIK  "+
			" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "+  
			" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK "+
			" AND HM.ID_MUKIM = M.ID_MUKIM "+
			" AND AW.ID_HAKMILIKPB(+) = HPB.ID_HAKMILIKPB  AND HM.ID_LOT = JL.ID_LOT(+) "+
			" AND HPB.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN "+
			" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"'";

			myLogger.info("MAKLUMAT PAMPASAN PB:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);

			h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));				
				
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				
				h.put("BAYAR_PAMPASAN",
						rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
								.getDouble("BAYAR_PAMPASAN"));

				maklumat_pampasan_pb.addElement(h);

			}
			return maklumat_pampasan_pb;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	Vector maklumat_subpampasan_pb = null;
	public Vector maklumat_subpampasan_pb(String id_hakmilikpb,String id_pembatalan) throws Exception {
		maklumat_subpampasan_pb = new Vector();
		maklumat_subpampasan_pb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT SAW.JUMLAH_SUBAWARD,PB.ID_PENARIKANBALIK,HM.ID_HAKMILIK,K.NAMA_KEMENTERIAN, "+
			" B.NAMA_PB,B.NO_PB," +
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT "+
					" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
			"HM.NO_PT,BPB.SYER_ATAS, "+
			" BPB.SYER_BAWAH,HPB.ID_BANK,HPB.NO_AKAUN, HPB.NAMA_BANK,M.NAMA_MUKIM "+
			" FROM TBLPPTHAKMILIK HM,TBLRUJMUKIM M,TBLRUJKEMENTERIAN K,TBLPFDFAIL F,"+
			" TBLPPTPERMOHONAN P, "+
			" TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S, "+
			" TBLPPTPIHAKBERKEPENTINGAN B,TBLPPTAWARD AW,TBLPPTSUBAWARD SAW,TBLRUJLOT JL,TBLPPTBAHAGIANPB BPB   "+
			" WHERE "+
			" PB.ID_PENARIKANBALIK = '"+id_pembatalan+"'  "+
			" AND PB.ID_PERMOHONAN = P.ID_PERMOHONAN  "+
			" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"+
			
			 " AND S.ID_HAKMILIK = HM.ID_HAKMILIK "
			+ " AND S.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
			+ " AND S.ID_SIASATAN = AW.ID_SIASATAN "+
			 " AND SAW.ID_AWARD = AW.ID_AWARD "+
			 " AND SAW.FLAG_JENIS_AWARD = 'BAYAR_LAIN' "+			
			" AND P.ID_FAIL = F.ID_FAIL "+ 
			" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN  "+
			" AND PH.ID_HAKMILIK = HPB.ID_HAKMILIK  "+
			" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "+  
			" AND HM.ID_HAKMILIK = HPB.ID_HAKMILIK "+
			" AND HM.ID_MUKIM = M.ID_MUKIM "+
			" AND AW.ID_HAKMILIKPB(+) = HPB.ID_HAKMILIKPB  AND HM.ID_LOT = JL.ID_LOT(+) "+
			" AND HPB.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN "+
			" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"'";

			myLogger.info("MAKLUMAT PAMPASAN PB:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);

			h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));				
				
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
						.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				
				h.put("JUMLAH_SUBAWARD",
						rs.getString("JUMLAH_SUBAWARD") == null ? "" : rs
								.getDouble("JUMLAH_SUBAWARD"));

				maklumat_subpampasan_pb.addElement(h);

			}
			return maklumat_subpampasan_pb;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void addMaklumatSiasatan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String txtNoKes = (String) data.get("txtNoKes");
		String txtNoSiasatan = (String) data.get("txtNoSiasatan");
		String txtTempatSiasatan = (String) data.get("txtTempatSiasatan");
		String txtPoskod = (String) data.get("txtPoskod");
		String txtAlamat1 = (String) data.get("txtAlamat1");
		String txtAlamat2 = (String) data.get("txtAlamat2");
		String txtAlamat3 = (String) data.get("txtAlamat3");
		String socNegeriSiasatan = (String) data.get("socNegeriSiasatan");
		String socBandarSiasatan = (String) data.get("socBandarSiasatan");
		String socStatusSiasatan = (String) data.get("socStatusSiasatan");
		String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
		String txtAlasan = (String) data.get("txtAlasan");
		String jeniswaktu = (String) data.get("jeniswaktu");
		String txtTkhAkhirNotis = (String) data.get("txtTkhAkhirNotis");
		String txdTarikhSiasatan = (String) data.get("txdTarikhSiasatan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_hakmilik = (String) data.get("id_hakmilik");
		String bil_siasatan = (String) data.get("bil_siasatan");
		if(!txtNoKes.equals("") || !txtNoSiasatan.equals("") || !txtTempatSiasatan.equals("") ||
				!txtPoskod.equals("") || !txtAlamat1.equals("") || !txtAlamat2.equals("") || !txtAlamat3.equals("")
				|| !socNegeriSiasatan.equals("") || !socBandarSiasatan.equals("") || !socStatusSiasatan.equals("") 
				|| !txtMasaSiasatan.equals("") || !jeniswaktu.equals("") || !txdTarikhSiasatan.equals("") || !bil_siasatan.equals(""))
		        {
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("NO_KES", txtNoKes);
			r.add("BIL_SIASATAN", bil_siasatan);
			r.add("NO_SIASATAN", txtNoSiasatan);
			r.add("TEMPAT", txtTempatSiasatan);
			r.add("POSKOD", txtPoskod);
			r.add("ALAMAT1", txtAlamat1);
			r.add("ALAMAT2", txtAlamat2);
			r.add("ALAMAT3", txtAlamat3);
			r.add("ID_NEGERI", socNegeriSiasatan);
			r.add("ID_BANDAR", socBandarSiasatan);
			r.add("STATUS_SIASATAN", socStatusSiasatan);
			r.add("MASA_SIASATAN", txtMasaSiasatan);
			r.add("ALASAN_TANGGUH", txtAlasan);
			r.add("JENIS_WAKTU_SIASATAN", jeniswaktu);

			if (txtTkhAkhirNotis != "") {
				r.add("TARIKH_AKHIR_TAMPAL", r.unquote("to_date('"
						+ txtTkhAkhirNotis + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_AKHIR_TAMPAL", "");
			}

			if (txdTarikhSiasatan != "") {
				r.add("TARIKH_SIASATAN", r.unquote("to_date('"
						+ txdTarikhSiasatan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SIASATAN", "");
			}

			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_HAKMILIK", id_hakmilik);
			r.add("ID_MASUK", id_Masuk);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptsiasatan");
			myLogger.info("SQL INSERT SIASATAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
		        }
	}

	public void updateMaklumatSiasatan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";

		String id_siasatan = (String) data.get("id_siasatan");
		String txtNoKes = (String) data.get("txtNoKes");
		String txtNoSiasatan = (String) data.get("txtNoSiasatan");
		String txtTempatSiasatan = (String) data.get("txtTempatSiasatan");
		String txtPoskod = (String) data.get("txtPoskod");
		String txtAlamat1 = (String) data.get("txtAlamat1");
		String txtAlamat2 = (String) data.get("txtAlamat2");
		String txtAlamat3 = (String) data.get("txtAlamat3");
		String socNegeriSiasatan = (String) data.get("socNegeriSiasatan");
		String socBandarSiasatan = (String) data.get("socBandarSiasatan");
		String socStatusSiasatan = (String) data.get("socStatusSiasatan");
		String txtMasaSiasatan = (String) data.get("txtMasaSiasatan");
		String txtAlasan = (String) data.get("txtAlasan");
		String jeniswaktu = (String) data.get("jeniswaktu");
		String txtTkhAkhirNotis = (String) data.get("txtTkhAkhirNotis");
		String txdTarikhSiasatan = (String) data.get("txdTarikhSiasatan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_pembatalan = (String) data.get("id_pembatalan");
		String id_hakmilik = (String) data.get("id_hakmilik");
		String bil_siasatan = (String) data.get("bil_siasatan");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("BIL_SIASATAN", bil_siasatan);
			r.add("NO_KES", txtNoKes);
			r.add("NO_SIASATAN", txtNoSiasatan);
			r.add("TEMPAT", txtTempatSiasatan);
			r.add("POSKOD", txtPoskod);
			r.add("ALAMAT1", txtAlamat1);
			r.add("ALAMAT2", txtAlamat2);
			r.add("ALAMAT3", txtAlamat3);
			r.add("ID_NEGERI", socNegeriSiasatan);
			r.add("ID_BANDAR", socBandarSiasatan);
			r.add("STATUS_SIASATAN", socStatusSiasatan);
			r.add("MASA_SIASATAN", txtMasaSiasatan);
			r.add("ALASAN_TANGGUH", txtAlasan);
			r.add("JENIS_WAKTU_SIASATAN", jeniswaktu);

			if (txtTkhAkhirNotis != "") {
				r.add("TARIKH_AKHIR_TAMPAL", r.unquote("to_date('"
						+ txtTkhAkhirNotis + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_AKHIR_TAMPAL", "");
			}

			if (txdTarikhSiasatan != "") {
				r.add("TARIKH_SIASATAN", r.unquote("to_date('"
						+ txdTarikhSiasatan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SIASATAN", "");
			}

			r.add("ID_PENARIKANBALIK", id_pembatalan);
			r.add("ID_HAKMILIK", id_hakmilik);

			r.add("ID_KEMASKINI", id_Masuk);

			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			myLogger.info("SQL UPDATE SIASATAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector list_bandar_all = null;

	public Vector list_bandar_all() throws Exception {
		list_bandar_all = new Vector();
		list_bandar_all.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_BANDAR, KOD_BANDAR, KETERANGAN, ID_NEGERI FROM TBLRUJBANDAR";

			myLogger.info("FIND BANDAR :" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_BANDAR", rs.getInt("ID_BANDAR"));

				if (rs.getString("KETERANGAN") == null) {
					h.put("NAMA_BANDAR", "");
				} else {
					h.put("NAMA_BANDAR", rs.getString("KETERANGAN").toUpperCase()
							);
				}
				if (rs.getString("KOD_BANDAR") == null) {
					h.put("KOD_BANDAR", "");
				} else {
					h.put("KOD_BANDAR", rs.getString("KOD_BANDAR")
							);
				}

				list_bandar_all.addElement(h);
			}
			return list_bandar_all;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_kehadiran = null;

	public Vector list_kehadiran(String id_siasatan) throws Exception {
		list_kehadiran = new Vector();
		list_kehadiran.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PENARIKANBALIK,"
					+
					// "H.FLAG_HADIR," +
					"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
					+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
					+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
					+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
					+ " FROM TBLPPTHAKMILIK HM,TBLPPTPENARIKANBALIK PBALIK,TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
					+
					// " TBLPPTKEHADIRAN H," +
					"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
					+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTPENARIKANHAKMILIK PH  "
					+ " WHERE " 
						//	"HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
					+" AND PH.ID_PENARIKANBALIK = PBALIK.ID_PENARIKANBALIK "
					+" AND S.ID_PENARIKANBALIK = PBALIK.ID_PENARIKANBALIK "
					+
					// " AND H.ID_HAKMILIKPB(+) = HMPB.ID_HAKMILIKPB "+
					// " AND H.ID_SIASATAN(+) = S.ID_SIASATAN "+
					" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
					+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
					+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
					+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
					+ " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42','27') "
					+

					" AND S.ID_SIASATAN = '"
					+ id_siasatan
					+ "' "
					+ " ORDER BY NAMA_PB ASC" + "";

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				temp = bil + "k";
				h.put("BILTEMP", temp);
				h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
						: rs.getString("NO_HANDPHONE"));
				h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
				// rs.getString("FLAG_HADIR"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
						.getString("NAMA_KP"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
				// rs.getString("KETERANGAN"));
				h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
						.getString("JENISPB"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR"));
				h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
						: rs.getString("ID_JENISNOPB"));
				h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
						.getString("JENISNOPB"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));

				list_kehadiran.addElement(h);
			}
			return list_kehadiran;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_kehadiran_th = null;

	public Vector list_kehadiran_th(String id_siasatan) throws Exception {
		list_kehadiran_th = new Vector();
		list_kehadiran_th.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PENARIKANBALIK,"
					+
					// "H.FLAG_HADIR," +
					"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
					+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
					+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
					+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
					+ " FROM TBLPPTHAKMILIK HM,TBLPPTPENARIKANBALIK PBALIK,TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
					+
					// " TBLPPTKEHADIRAN H," +
					"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
					+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE " 
					+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
					+" AND PH.ID_PENARIKANBALIK = PBALIK.ID_PENARIKANBALIK "
					//		"HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
					+ " AND S.ID_PENARIKANBALIK = PBALIK.ID_PENARIKANBALIK "
					+
					// " AND H.ID_HAKMILIKPB(+) = HMPB.ID_HAKMILIKPB "+
					// " AND H.ID_SIASATAN(+) = S.ID_SIASATAN "+
					" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
					+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
					+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
					+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
					+ " AND (HMPB.ID_JENISPB = '40' OR HMPB.ID_JENISPB = '41' OR HMPB.ID_JENISPB = '42' OR HMPB.ID_JENISPB = '27') "
					+ " AND S.ID_SIASATAN = '"
					+ id_siasatan
					+ "' "
					+ " ORDER BY NAMA_PB ASC" + "";

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				temp = bil + "k";
				h.put("BILTEMP", temp);
				h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
						: rs.getString("NO_HANDPHONE"));
				h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
				// rs.getString("FLAG_HADIR"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
						.getString("NAMA_KP"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
				// rs.getString("KETERANGAN"));
				h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
						.getString("JENISPB"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR"));
				h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
						: rs.getString("ID_JENISNOPB"));
				h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
						.getString("JENISNOPB"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));

				list_kehadiran_th.addElement(h);
			}
			return list_kehadiran_th;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector maklumat_kehadiran = null;

	public Vector maklumat_kehadiran(String id_hakmilikpb) throws Exception {
		maklumat_kehadiran = new Vector();
		maklumat_kehadiran.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT HMPB.NAMA_BANK AS NAMA_BANK_TXT,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK,S.ID_SIASATAN,PBALIK.ID_PENARIKANBALIK,"
					+
					// "H.FLAG_HADIR," +
					"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,HMPB.KETERANGAN_JENIS_PB,PB.NAMA_PB,"
					+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
					+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
					+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
					+ " FROM TBLPPTHAKMILIK HM,TBLPPTPENARIKANBALIK PBALIK,TBLPPTSIASATAN S,TBLPPTHAKMILIKPB HMPB, "
					+
					// " TBLPPTKEHADIRAN H," +
					"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
					+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTPENARIKANHAKMILIK PH  "
					+ " WHERE " +
							" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
					+" AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK " +

					//		"HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
							
					" AND S.ID_PENARIKANBALIK = PBALIK.ID_PENARIKANBALIK "
					+
					// " AND H.ID_HAKMILIKPB(+) = HMPB.ID_HAKMILIKPB "+
					// " AND H.ID_SIASATAN(+) = S.ID_SIASATAN "+
					" AND  S.ID_HAKMILIK = HM.ID_HAKMILIK "
					+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
					+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
					+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
					+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
					+ " AND HMPB.ID_HAKMILIKPB = '"
					+ id_hakmilikpb
					+ "' "
					+ " ORDER BY NAMA_PB ASC" + "";

			myLogger.info("MAKLUMAT KEHADIRAN PB:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				temp = bil + "k";
				h.put("BILTEMP", temp);
				h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_JENIS_PB") == null ? ""
						: rs.getString("KETERANGAN_JENIS_PB"));
				h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
						: rs.getString("NO_HANDPHONE"));
				h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
						: rs.getString("ID_SIASATAN"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
								.getString("ID_PENARIKANBALIK"));
				// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
				// rs.getString("FLAG_HADIR"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB"));
				h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
						.getString("NAMA_KP"));
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
				// rs.getString("KETERANGAN"));
				h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
						.getString("JENISPB"));
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2"));
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3"));
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR"));
				h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
						: rs.getString("ID_JENISNOPB"));
				h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
						.getString("JENISNOPB"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN"));
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK"));
				
				h.put("NAMA_BANK_TXT", rs.getString("NAMA_BANK_TXT") == null ? "" : rs
						.getString("NAMA_BANK_TXT"));

				
				
				maklumat_kehadiran.addElement(h);
			}
			return maklumat_kehadiran;
		} finally {
			if (db != null)
				db.close();
		}
	}

	Vector list_check_kehadiran = null;

	public Vector list_check_kehadiran(String id_siasatan) throws Exception {
		list_check_kehadiran = new Vector();
		list_check_kehadiran.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT H.ID_KEHADIRAN,H.ID_HAKMILIKPB,H.FLAG_HADIR,S.ID_SIASATAN " +
					"FROM TBLPPTKEHADIRAN H,TBLPPTSIASATAN S "
					+ "WHERE H.ID_SIASATAN = S.ID_SIASATAN "
					+ "AND S.ID_SIASATAN = '" + id_siasatan + "' " + "";
			myLogger.info("CHECK KEHADIRAN"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 0;

			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_KEHADIRAN", rs.getString("ID_KEHADIRAN") == null ? ""
						: rs.getString("ID_KEHADIRAN"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("FLAG_HADIR", rs.getString("FLAG_HADIR") == null ? ""
						: rs.getString("FLAG_HADIR"));

				list_check_kehadiran.addElement(h);
			}
			return list_check_kehadiran;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateKehadiran(String id_hakmilikpb, String id_siasatan,
			String id_Masuk) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";

		try {
			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_HAKMILIKPB", id_hakmilikpb);
			r.add("ID_SIASATAN", id_siasatan);
			r.add("FLAG_HADIR", 1);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("ID_MASUK", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptkehadiran");
			myLogger.info("SQL INSERT KEHADIRAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void addPBKehadiran(String id_pb1, String nama_pb, String no_pb,
			String jenis_nopb, String nama_kp, String id_Masuk,
			String id_hakmilikpb1, String id_jenispb, String no_akaun,
			String id_bank, String alamat1, String alamat2, String alamat3,
			String poskod, String id_negeri, String id_bandar,
			String id_Masuki, String no_hp, String no_tel, String id_siasatan,
			String id_hakmilik, String txtPBhadir,String namaBank,String txtKeteranganPB) throws Exception {
		long id_pb = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
		long id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ");
		Db db = null;
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try {
			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PIHAKBERKEPENTINGAN", id_pb);
			r.add("NAMA_PB", nama_pb);
			r.add("ID_JENISNOPB", jenis_nopb);
			r.add("NAMA_KP", nama_kp);
			r.add("NO_PB", no_pb);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptpihakberkepentingan");
			myLogger.info("SQL UPDATE PB KEHADIRAN :" + sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("ID_HAKMILIKPB", id_hakmilikpb);
			r1.add("KETERANGAN_JENIS_PB", txtKeteranganPB);
			r1.add("ID_PIHAKBERKEPENTINGAN", id_pb);
			r1.add("ID_HAKMILIK", id_hakmilik);
			// r1.add("NO_AKAUN", no_akaun);
			r1.add("NO_AKAUN", no_akaun);
			r1.add("ID_JENISPB", id_jenispb);
			r1.add("ID_BANK", id_bank);
			r1.add("NAMA_BANK", namaBank);
			r1.add("ALAMAT1", alamat1);
			r1.add("ALAMAT2", alamat2);
			r1.add("ALAMAT3", alamat3);
			r1.add("POSKOD", poskod);
			r1.add("NO_HANDPHONE", no_hp);
			r1.add("NO_TEL_RUMAH", no_tel);
			r1.add("ID_BANDAR", id_bandar);
			r1.add("ID_NEGERI", id_negeri);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblppthakmilikpb");
			myLogger.info("SQL UPDATE PB DETAILS KEHADIRAN :"
					+ sql);
			stmt1.executeUpdate(sql1);

			if (txtPBhadir.equals("1")) {
				Statement stmt2 = db.getStatement();
				SQLRenderer r2 = new SQLRenderer();
				r2.add("ID_HAKMILIKPB", id_hakmilikpb);
				r2.add("ID_SIASATAN", id_siasatan);
				r2.add("FLAG_HADIR", 1);
				r2.add("ID_KEMASKINI", id_Masuk);
				r2.add("ID_MASUK", id_Masuk);
				r2.add("TARIKH_MASUK", r2.unquote("sysdate"));
				r2.add("TARIKH_KEMASKINI", r2.unquote("sysdate"));
				sql2 = r2.getSQLInsert("tblpptkehadiran");
				myLogger.info("SQL INSERT KEHADIRAN :" + sql);
				stmt2.executeUpdate(sql2);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePBKehadiran(String id_pb, String nama_pb, String no_pb,
			String jenis_nopb, String nama_kp, String id_Masuk)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PIHAKBERKEPENTINGAN", id_pb);
			r.add("NAMA_PB", nama_pb);
			r.add("ID_JENISNOPB", jenis_nopb);
			r.add("NAMA_KP", nama_kp);
			r.add("NO_PB", no_pb);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptpihakberkepentingan");
			myLogger.info("SQL UPDATE PB KEHADIRAN :" + sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updatePBKehadiranDetails(String id_hakmilikpb,
			String id_jenispb, String no_akaun, String id_bank, String alamat1,
			String alamat2, String alamat3, String poskod, String id_negeri,
			String id_bandar, String id_Masuk, String no_hp, String no_tel,String nama_bank,String txtKeteranganPB)
			throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIKPB", id_hakmilikpb);
			r.add("KETERANGAN_JENIS_PB", txtKeteranganPB);
			r.add("NO_AKAUN", no_akaun);
			r.add("ID_JENISPB", id_jenispb);
			r.add("ID_BANK", id_bank);
			r.add("NAMA_BANK", nama_bank);
			r.add("ALAMAT1", alamat1);
			r.add("ALAMAT2", alamat2);
			r.add("ALAMAT3", alamat3);
			r.add("POSKOD", poskod);
			r.add("NO_HANDPHONE", no_hp);
			r.add("NO_TEL_RUMAH", no_tel);
			r.add("ID_BANDAR", id_bandar);
			r.add("ID_NEGERI", id_negeri);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilikpb");
			myLogger.info("SQL UPDATE PB DETAILS KEHADIRAN :"
					+ sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteKehadiran(String id_siasatan) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTKEHADIRAN WHERE ID_SIASATAN = "
					+ id_siasatan;
			myLogger.info("SQL DELETE TBLPPTKEHADIRAN :" + sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteKehadiranPB(String id_kehadiran) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTKEHADIRAN WHERE ID_KEHADIRAN = "
					+ id_kehadiran;
			myLogger.info("SQL DELETE TBLPPTKEHADIRAN BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateTuanTanah(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txdMilikTanah = (String) data.get("txdMilikTanah");
		String txtCaraMilikTanah = (String) data.get("txtCaraMilikTanah");
		String txtHargaTanah = (String) data.get("txtHargaTanah");
		String txtBebananTanah = (String) data.get("txtBebananTanah");
		String txtKeteranganTuanTanah = (String) data
				.get("txtKeteranganTuanTanah");
		String txtJenisTanaman = (String) data.get("txtJenisTanaman");
		String txtJenisBangunan = (String) data.get("txtJenisBangunan");
		String sorPecahSempadan = (String) data.get("sorPecahSempadan");
		String txdPecahSempadan = (String) data.get("txdPecahSempadan");
		String sorTukarSyarat = (String) data.get("sorTukarSyarat");
		String txdTukarSyarat = (String) data.get("txdTukarSyarat");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("TEMPOH_MILIK_TANAH", txdMilikTanah);
			r.add("CARA_MILIK", txtCaraMilikTanah);
			r.add("HARGA_BELI", txtHargaTanah);
			r.add("BEBANAN", txtBebananTanah);
			r.add("KETERANGAN_TUAN_TANAH", txtKeteranganTuanTanah);
			r.add("JENIS_TANAMAN", txtJenisTanaman);
			r.add("JENIS_BANGUNAN", txtJenisBangunan);
			r.add("FLAG_PECAH_SEMPADAN", sorPecahSempadan);
			r.add("FLAG_TUKAR_SYARAT", sorTukarSyarat);

			if (txdPecahSempadan != "") {
				r.add("TARIKH_PECAH_SEMPADAN", r.unquote("to_date('"
						+ txdPecahSempadan + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_PECAH_SEMPADAN", "");
			}

			if (txdTukarSyarat != "") {
				r.add("TARIKH_TUKAR_SYARAT", r.unquote("to_date('"
						+ txdTukarSyarat + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_TUKAR_SYARAT", "");
			}

			r.add("ID_KEMASKINI", id_Masuk);

			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN TUAN TANAH :"
					+ sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateAgensi(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txtKeteranganAgensi = (String) data.get("txtKeteranganAgensi");
		String txtKeteranganJurunilai = (String) data
				.get("txtKeteranganJurunilai");
		
		String txtKeteranganJPPH = (String) data.get("txtKeteranganJPPH");
		String txtKeteranganAkujanji = (String) data.get("txtKeteranganAkujanji");

		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("KETERANGAN_AGENSI", txtKeteranganAgensi);
			r.add("KETERANGAN_JURUNILAI", txtKeteranganJurunilai);
			
			r.add("KETERANGAN_JPPH", txtKeteranganJPPH);
			r.add("AKUJANJI_AGENSI", txtKeteranganAkujanji);
			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN AGENSI :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void updateKerosakan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txtKeteranganAgensi = (String) data.get("txtKeteranganAgensi");
		String txtKeteranganJurunilai = (String) data
				.get("txtKeteranganJurunilai");

		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("KEROSAKAN_TANAH", txtKeteranganAgensi);
			r.add("KOS_DITANGGUNG", txtKeteranganJurunilai);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN AGENSI :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateTuntutan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txtTuntutanTuanTanah = (String) data.get("txtTuntutanTuanTanah");
		String txtLainTuntutan = (String) data.get("txtLainTuntutan");
		String txtPBDaftar = (String) data.get("txtPBDaftar");
		String txtPBXDaftar = (String) data.get("txtPBXDaftar");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("TUNTUTAN_TUANTNH", txtTuntutanTuanTanah);
			r.add("TUNTUTAN_PB_LAIN", txtLainTuntutan);
			r.add("TUNTUTAN_PB_BEBANAN", txtPBDaftar);
			r.add("TUNTUTAN_PB_TDKDAFTAR", txtPBXDaftar);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN TUNTUTAN :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateTuntutanPB(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txtTuntutanTuanTanah = (String) data.get("txtTuntutanTuanTanah");
		String txtLainTuntutan = (String) data.get("txtLainTuntutan");
		String txtPBDaftar = (String) data.get("txtPBDaftar");
		String txtPBXDaftar = (String) data.get("txtPBXDaftar");
		String txtStatus = (String) data.get("txtStatus");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("TUNTUTAN_TUANTNH", txtTuntutanTuanTanah);
			r.add("TUNTUTAN_PB_LAIN", txtLainTuntutan);
			r.add("TUNTUTAN_PB_BEBANAN", txtPBDaftar);
			r.add("TUNTUTAN_PB_TDKDAFTAR", txtPBXDaftar);
			r.add("STATUS_TUNTUTAN", txtStatus);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN TUNTUTAN :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateBantahan(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String txtBantahanLain = (String) data.get("txtBantahanLain");
		String txtBantahanAgensi = (String) data.get("txtBantahanAgensi");
		String txtBantahanTuanTanah = (String) data.get("txtBantahanTuanTanah");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			r.add("BANTAHAN_LAIN", txtBantahanLain);
			r.add("BANTAHAN_AGENSI", txtBantahanAgensi);
			r.add("BANTAHAN_TUANTNH", txtBantahanTuanTanah);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger.info("SQL UPDATE SIASATAN TUNTUTAN :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateKeputusan(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		String socPegawaiSiasatan = (String) data.get("socPegawaiSiasatan");
		String socStatusSiasatan = (String) data.get("socStatusSiasatan");
		String txtUlasanPerintah = (String) data.get("txtUlasanPerintah");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");
		
		String tarikh_perintah = (String) data.get("tarikh_perintah");
		String socUnit = (String) data.get("socUnit");
		String nilai_seunit = (String) data.get("nilai_seunit");
		
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);
			
		//	r.add("TARIKH_PERINTAH", tarikh_perintah);
			
			

			if (tarikh_perintah != "") {
				r.add("TARIKH_PERINTAH", r.unquote("to_date('"
						+ tarikh_perintah + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_PERINTAH", "");
			}
			
			
			r.add("UNIT_TANAH", socUnit);
			r.add("NILAI_SEUNIT", nilai_seunit);
			

			r.add("ID_PEGAWAI_SIASATAN", socPegawaiSiasatan);
		//	r.add("STATUS_SIASATAN", socStatusSiasatan);
			r.add("ULASAN_PERINTAH", txtUlasanPerintah);

			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger
					.info("SQL UPDATE SIASATAN keputusan :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void updateStatusSiasatan(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		String socPegawaiSiasatan = (String) data.get("socPegawaiSiasatan");
		String socStatusSiasatan = (String) data.get("socStatusSiasatan");
		String txtUlasanSiasatan = (String) data.get("txtUlasanSiasatan");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_SIASATAN", id_siasatan);

			r.add("ID_PEGAWAI_SIASATAN", socPegawaiSiasatan);
			r.add("STATUS_SIASATAN", socStatusSiasatan);
			r.add("ULASAN_SIASATAN", txtUlasanSiasatan);

			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblpptsiasatan");
			stmt.executeUpdate(sql);
			myLogger
					.info("SQL UPDATE SIASATAN keputusan :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void addNilaian(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		String txtHargaSeunitSO = (String) data.get("txtHargaSeunitSO");
		String txtUnitHargaSO = (String) data.get("txtUnitHargaSO");
		String txtHargaPasaranSO = (String) data.get("txtHargaPasaranSO");
		String txtPenjejasanSO = (String) data.get("txtPenjejasanSO");
		String txtPecahPisahSO = (String) data.get("txtPecahPisahSO");
		String txtNaikNilaiSO = (String) data.get("txtNaikNilaiSO");
		String txtHargaSeunitJPPH = (String) data.get("txtHargaSeunitJPPH");
		String txtUnitHargaJPPH = (String) data.get("txtUnitHargaJPPH");
		String txtHargaPasaranJPPH = (String) data.get("txtHargaPasaranJPPH");
		String txtPenjejasanJPPH = (String) data.get("txtPenjejasanJPPH");
		String txtPecahPisahJPPH = (String) data.get("txtPecahPisahJPPH");
		String txtNaikNilaiJPPH = (String) data.get("txtNaikNilaiJPPH");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_hakmilik = (String) data.get("id_hakmilik");
		
		String id_award = (String) data.get("id_award");
		String txtFee = (String) data.get("txtFee");
		String txtBangunan = (String) data.get("txtBangunan");
		String txtTanah = (String) data.get("txtTanah");
		String txtPenjejasan = (String) data.get("txtPenjejasan");
		String txtGantiRugi = (String) data.get("txtGantiRugi");
		String txtNaik = (String) data.get("txtNaik");
		String txtLainPampasan = (String) data.get("txtLainPampasan");
		String txtJumlah = (String) data.get("txtJumlah");
		String txtPecahpisah = (String) data.get("txtPecahpisah");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_HAKMILIK", id_hakmilik);
			r.add("HARGA_SEUNIT_SO", txtHargaSeunitSO);
			r.add("UNIT_HARGA_SO", txtUnitHargaSO);
			r.add("HARGA_PASARAN_SO", txtHargaPasaranSO);
			r.add("BAYAR_PENJEJASAN", txtPenjejasanSO);
			r.add("BAYAR_PECAHPISAH", txtPecahPisahSO);
			r.add("BAYAR_NAIK_NILAISO", txtNaikNilaiSO);
			r.add("HARGA_SEUNIT_JPPH", txtHargaSeunitJPPH);
			r.add("UNIT_HARGA", txtUnitHargaJPPH);
			r.add("HARGA_PASARAN", txtHargaPasaranJPPH);
			r.add("AMAUN_PENJEJASAN_JPPH", txtPenjejasanJPPH);
			r.add("AMAUN_PECAHPISAH_JPPH", txtPecahPisahJPPH);
			r.add("NAIK_NILAI_JPPH", txtNaikNilaiJPPH);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("ID_MASUK", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppttanah");
			stmt.executeUpdate(sql);
			
			
			if(id_award.equals("")){
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("BAYAR_FEE", txtFee);
			r1.add("BAYAR_BANGUNAN", txtBangunan);
			r1.add("BAYAR_TANAH", txtTanah);
			r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
			r1.add("NILAI_RUGI", txtGantiRugi);
			r1.add("NAIK_NILAI", txtNaik );
			r1.add("BAYAR_PAMPASAN", txtJumlah);
			r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
			r1.add("BAYAR_LAIN2", txtLainPampasan);
			r1.add("ID_HAKMILIKPB", id_hakmilikpb);			
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("ID_MASUK", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblpptaward");
			stmt1.executeUpdate(sql1);
			}
			else
			{
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("ID_AWARD", id_award);
				r1.add("BAYAR_FEE", txtFee);
				r1.add("BAYAR_BANGUNAN", txtBangunan);
				r1.add("BAYAR_TANAH", txtTanah);
				r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
				r1.add("NILAI_RUGI", txtGantiRugi);
				r1.add("NAIK_NILAI", txtNaik );
				r1.add("BAYAR_PAMPASAN", txtJumlah);
				r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
				r1.add("BAYAR_LAIN2", txtLainPampasan);
				r1.add("ID_KEMASKINI", id_Masuk);				
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
				sql1 = r1.getSQLUpdate("tblpptaward");
				stmt1.executeUpdate(sql1);	
			}
			
			myLogger.info("SQL INSERT SIASATAN NILAIN :" + sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void updateNilaian(Hashtable data) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		String txtHargaSeunitSO = (String) data.get("txtHargaSeunitSO");
		String txtUnitHargaSO = (String) data.get("txtUnitHargaSO");
		String txtHargaPasaranSO = (String) data.get("txtHargaPasaranSO");
		String txtPenjejasanSO = (String) data.get("txtPenjejasanSO");
		String txtPecahPisahSO = (String) data.get("txtPecahPisahSO");
		String txtNaikNilaiSO = (String) data.get("txtNaikNilaiSO");
		String txtHargaSeunitJPPH = (String) data.get("txtHargaSeunitJPPH");
		String txtUnitHargaJPPH = (String) data.get("txtUnitHargaJPPH");
		String txtHargaPasaranJPPH = (String) data.get("txtHargaPasaranJPPH");
		String txtPenjejasanJPPH = (String) data.get("txtPenjejasanJPPH");
		String txtPecahPisahJPPH = (String) data.get("txtPecahPisahJPPH");
		String txtNaikNilaiJPPH = (String) data.get("txtNaikNilaiJPPH");
		String id_siasatan = (String) data.get("id_siasatan");
		String id_Masuk = (String) data.get("id_Masuk");
		String id_tanah = (String) data.get("id_tanah");
		
		String id_award = (String) data.get("id_award");
		String txtFee = (String) data.get("txtFee");
		String txtBangunan = (String) data.get("txtBangunan");
		String txtTanah = (String) data.get("txtTanah");
		String txtPenjejasan = (String) data.get("txtPenjejasan");
		String txtGantiRugi = (String) data.get("txtGantiRugi");
		String txtNaik = (String) data.get("txtNaik");
		String txtLainPampasan = (String) data.get("txtLainPampasan");
		String txtJumlah = (String) data.get("txtJumlah");
		String txtPecahpisah = (String) data.get("txtPecahpisah");
		String id_hakmilikpb = (String) data.get("id_hakmilikpb");
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_TANAH", id_tanah);
			r.add("HARGA_SEUNIT_SO", txtHargaSeunitSO);
			r.add("UNIT_HARGA_SO", txtUnitHargaSO);
			r.add("HARGA_PASARAN_SO", txtHargaPasaranSO);
			r.add("BAYAR_PENJEJASAN", txtPenjejasanSO);
			r.add("BAYAR_PECAHPISAH", txtPecahPisahSO);
			r.add("BAYAR_NAIK_NILAISO", txtNaikNilaiSO);

			r.add("HARGA_SEUNIT_JPPH", txtHargaSeunitJPPH);
			r.add("UNIT_HARGA", txtUnitHargaJPPH);
			r.add("HARGA_PASARAN", txtHargaPasaranJPPH);
			r.add("AMAUN_PENJEJASAN_JPPH", txtPenjejasanJPPH);
			r.add("AMAUN_PECAHPISAH_JPPH", txtPecahPisahJPPH);
			r.add("NAIK_NILAI_JPPH", txtNaikNilaiJPPH);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppttanah");
			
			
			
			
			if(id_award.equals("")){
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.add("BAYAR_FEE", txtFee);
			r1.add("BAYAR_BANGUNAN", txtBangunan);
			r1.add("BAYAR_TANAH", txtTanah);
			r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
			r1.add("NILAI_RUGI", txtGantiRugi);
			r1.add("NAIK_NILAI", txtNaik );
			r1.add("BAYAR_PAMPASAN", txtJumlah);
			r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
			r1.add("BAYAR_LAIN2", txtLainPampasan);
			r1.add("ID_HAKMILIKPB", id_hakmilikpb);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("ID_MASUK", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblpptaward");
			stmt1.executeUpdate(sql1);
			}
			else
			{
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r1.update("ID_AWARD", id_award);
				r1.add("BAYAR_FEE", txtFee);
				r1.add("BAYAR_BANGUNAN", txtBangunan);
				r1.add("BAYAR_TANAH", txtTanah);
				r1.add("BAYAR_PENJEJASAN", txtPenjejasan);
				r1.add("NILAI_RUGI", txtGantiRugi);
				r1.add("NAIK_NILAI", txtNaik );
				r1.add("BAYAR_PAMPASAN", txtJumlah);
				r1.add("BAYAR_PECAHPISAH", txtPecahpisah);
				r1.add("BAYAR_LAIN2", txtLainPampasan);
				r1.add("ID_KEMASKINI", id_Masuk);				
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
				sql1 = r1.getSQLUpdate("tblpptaward");
				myLogger.info("UPDATE PAMAPASAN:" + sql1);
				stmt1.executeUpdate(sql1);	
			}

			myLogger.info("UPDATE NILAIAN:" + sql + "NILAI ::"
					+ txtNaikNilaiSO);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteMaklumatPB(String id_pb) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTPIHAKBERKEPENTINGAN WHERE ID_PIHAKBERKEPENTINGAN = "
					+ id_pb;
			myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteHakmilikPB(String id_hakmilikpb) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB = "
					+ id_hakmilikpb;
			myLogger.info("SQL DELETE TBLPPTHAKMILIKPB BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
    
	Vector list_jenisluas = null;
	
	public Vector list_jenisluas() throws Exception {
		list_jenisluas = new Vector();
		list_jenisluas.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql="SELECT ID_LUAS,KETERANGAN,KOD_LUAS FROM TBLRUJLUAS ORDER BY KOD_LUAS ASC ";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("ID_LUAS", rs.getInt("ID_LUAS"));
				if (rs.getString("KETERANGAN") == null) {
					h.put("KETERANGAN", "");
				} else {
					h.put("KETERANGAN", rs.getString("KETERANGAN"));
				}
				if (rs.getString("KOD_LUAS") == null) {
					h.put("KOD_LUAS", "");
				} else {
					h.put("KOD_LUAS", rs.getString("KOD_LUAS"));
				}

				list_jenisluas.addElement(h);
			}
			return list_jenisluas;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateStatusPenarikan(Hashtable data) throws Exception {

		    Connection conn = null;

		    Db db = null;
		    String sql = "";
		    String sql5="";
		    String sql6="";
		    
		    String idpermohonan=(String)data.get("id_permohonan");
		 //   String idstatus=(String)data.get("idstatus");
			String id_Fail=(String)data.get("id_Fail");
			String userId = (String)data.get("userId");
			String id_Suburusanstatus=(String)data.get("id_suburusanstatus");
			String id_Suburusanstatusfail=(String)data.get("id_suburusanstatusfail");
	
		    try
		    {
		     
	     		db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
			
			    if(id_Suburusanstatusfail!="")
			    {
				Statement stmtX = db.getStatement();
				SQLRenderer r6 = new SQLRenderer();
				r6.update("ID_SUBURUSANSTATUSFAILPPT",id_Suburusanstatusfail);
				r6.add("AKTIF",0);		
				r6.add("ID_KEMASKINI",userId);
				r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
				sql6 = r6.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAILPPT");
				stmtX.executeUpdate(sql6);
			    }
			    
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
				r5.add("ID_PERMOHONAN",idpermohonan);
				r5.add("ID_FAIL",id_Fail);
				r5.add("ID_SUBURUSANSTATUS",1485);
				r5.add("AKTIF",1);
				r5.add("ID_MASUK",userId);
				r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				r5.add("ID_KEMASKINI",userId);
				r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				sql5 = r5.getSQLInsert("TBLRUJSUBURUSANSTATUSFAILPPT");
				stmtF.executeUpdate(sql5);


				conn.commit();
		    }
		    catch (SQLException se) { 
		    	try 
		    	{conn.rollback();
		    	} 
		    	catch (SQLException se2) 
		    	{
		    	throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	se.printStackTrace();
		    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
		    	}finally {
		    	if (conn != null) conn.close(); 
		    	if (db != null)	db.close();
		    	}
		  }
	
	
	
	public void updateStatusPembatalan(Hashtable data) throws Exception {

	    Connection conn = null;

	    Db db = null;
	    String sql = "";
	    String sql5="";
	    String sql6="";
	    
	    String idpermohonan=(String)data.get("id_permohonan"); 
		String id_Fail=(String)data.get("id_Fail");
		String userId = (String)data.get("userId");
		String id_Suburusanstatus=(String)data.get("id_suburusanstatus");
		String id_Suburusanstatusfail=(String)data.get("id_suburusanstatusfail");

	    try
	    {
	     
	 		db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			  if(id_Suburusanstatusfail!="")
			    {
				Statement stmtX = db.getStatement();
				SQLRenderer r6 = new SQLRenderer();
				r6.update("ID_SUBURUSANSTATUSFAILPPT",id_Suburusanstatusfail);
				r6.add("AKTIF",0);		
				r6.add("ID_KEMASKINI",userId);
				r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
				sql6 = r6.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAILPPT");
				stmtX.executeUpdate(sql6);
			    }
			    
				Statement stmtF = db.getStatement();
				SQLRenderer r5 = new SQLRenderer();
				r5.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
				r5.add("ID_PERMOHONAN",idpermohonan);
				r5.add("ID_FAIL",id_Fail);
				r5.add("ID_SUBURUSANSTATUS",1506);
				r5.add("AKTIF",1);
				r5.add("ID_MASUK",userId);
				r5.add("TARIKH_MASUK",r5.unquote("sysdate"));
				r5.add("ID_KEMASKINI",userId);
				r5.add("TARIKH_KEMASKINI",r5.unquote("sysdate"));
				sql5 = r5.getSQLInsert("TBLRUJSUBURUSANSTATUSFAILPPT");
				stmtF.executeUpdate(sql5);


			conn.commit();
	    }
	    catch (SQLException se) { 
	    	try 
	    	{conn.rollback();
	    	} 
	    	catch (SQLException se2) 
	    	{
	    	throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	se.printStackTrace();
	    	throw new Exception("Ralat Jana Fail:"+se.getMessage());
	    	}finally {
	    	if (conn != null) conn.close(); 
	    	if (db != null)	db.close();
	    	}
	  }

	
	
	public double maklumat_hakmilik_mmk_hektar(String id_penarikan) throws Exception {
		double maklumat_hakmilik_mmk_hektar = 0;	
		
		DecimalFormat fourDForm = new DecimalFormat("#.####");
	

		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT H.ID_UNITLUASAMBIL_CONVERT,PH.LUAS_LOT_TARIK, PH.LUAS_AMBIL AS LUAS_ASAL,H.LUAS_AMBIL," +
					"H.ID_KATEGORITANAH FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				String jenis_convert = "";
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT") == null)
				{
					jenis_convert = "1";
				}
				else
				{
					jenis_convert = rs.getString("ID_UNITLUASAMBIL_CONVERT");
				}
				
				if(jenis_convert.equals("1"))
				{
					double xx = 0;		
					if(rs.getString("LUAS_LOT_TARIK")!="" || rs.getString("LUAS_LOT_TARIK")!=null)
					{
						xx = rs.getDouble("LUAS_LOT_TARIK");						
					}
					
					maklumat_hakmilik_mmk_hektar = maklumat_hakmilik_mmk_hektar + xx;
					
				}
				else
				{
					double yy = 0;		
					if(rs.getString("LUAS_LOT_TARIK")!="" || rs.getString("LUAS_LOT_TARIK")!=null)
					{
						yy = rs.getDouble("LUAS_LOT_TARIK");						
					}
					
					maklumat_hakmilik_mmk_hektar = maklumat_hakmilik_mmk_hektar + (yy*0.0001);					
					
				}				
			}
			////	return Double.valueOf(fourDForm.format(d));
			return Double.valueOf(fourDForm.format(maklumat_hakmilik_mmk_hektar));
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public double maklumat_hakmilik_mmk_ekar(String id_penarikan) throws Exception {
		double maklumat_hakmilik_mmk_ekar = 0;	
		DecimalFormat fourDForm = new DecimalFormat("#.####");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT H.ID_UNITLUASAMBIL_CONVERT,PH.LUAS_LOT_TARIK, PH.LUAS_AMBIL AS LUAS_ASAL,H.LUAS_AMBIL,H.ID_UNITLUASAMBIL_CONVERT," +
					"H.ID_KATEGORITANAH FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();
				String jenis_convert = "";
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT") == null)
				{
					jenis_convert = "1";
				}
				else
				{
					jenis_convert = rs.getString("ID_UNITLUASAMBIL_CONVERT");
				}
				
				
				
				if(jenis_convert.equals("1"))
				{
					double xx = 0;		
					if(rs.getString("LUAS_LOT_TARIK")!="" || rs.getString("LUAS_LOT_TARIK")!=null)
					{
						xx = rs.getDouble("LUAS_LOT_TARIK");						
					}
					
					maklumat_hakmilik_mmk_ekar = maklumat_hakmilik_mmk_ekar + xx;
					
				}
				else
				{
					double yy = 0;		
					if(rs.getString("LUAS_LOT_TARIK")!="" || rs.getString("LUAS_LOT_TARIK")!=null)
					{
						yy = rs.getDouble("LUAS_LOT_TARIK");						
					}
					
					maklumat_hakmilik_mmk_ekar = maklumat_hakmilik_mmk_ekar + (yy*0.0001);					
					
				}				
			}
			
			return Double.valueOf(fourDForm.format(maklumat_hakmilik_mmk_ekar*2.47105));
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_hakmilik_mmk_lot = null;
	public Vector maklumat_hakmilik_mmk_lot(String id_penarikan) throws Exception {
		maklumat_hakmilik_mmk_lot = new Vector();
		maklumat_hakmilik_mmk_lot.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT " +
					"DISTINCT " +
					"initcap(M.NAMA_MUKIM) AS NAMA_MUKIM FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H,TBLRUJMUKIM M "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND H.ID_MUKIM = M.ID_MUKIM "
						+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'";
			

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("NAMA_MUKIM") == null) {
					h.put("NAMA_MUKIM", "");
				} else {
					h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM"));
				}
				
				
				maklumat_hakmilik_mmk_lot.addElement(h);
						
			}
			
			return maklumat_hakmilik_mmk_lot;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_hakmilik_mmk_pb = null;
	public Vector maklumat_hakmilik_mmk_pb(String id_penarikan) throws Exception {
		maklumat_hakmilik_mmk_pb = new Vector();
		maklumat_hakmilik_mmk_pb.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT " +			
			"DISTINCT " +
					"initcap(PPB.NAMA_PB) AS NAMA_PB FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H,TBLPPTPIHAKBERKEPENTINGAN PPB,TBLPPTHAKMILIKPB HPB "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND H.ID_HAKMILIK = HPB.ID_HAKMILIK "
						+"    AND HPB.ID_JENISPB NOT IN (40,41,42) "
						+"    AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
						+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("NAMA_PB") == null) {
					h.put("NAMA_PB", "");
				} else {
					h.put("NAMA_PB", rs.getString("NAMA_PB"));
				}
				
				maklumat_hakmilik_mmk_pb.addElement(h);
						
			}
			
			return maklumat_hakmilik_mmk_pb;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector nama_user = null;
	public Vector nama_user(String id_user) throws Exception {
		nama_user = new Vector();
		nama_user.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT INITCAP(USER_NAME) AS USER_NAME FROM USERS WHERE USER_ID ='"+id_user+"'";
			myLogger.info("SQL NAMA USER"+sql);
            
			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME", "");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME_CAP", "");
				} else {
					h.put("USER_NAME_CAP", rs.getString("USER_NAME"));
				}
				
				
				nama_user.addElement(h);
						
			}
			
			return nama_user;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
		 
    Vector tempat_tampal = null;
	public Vector tempat_tampal(String id_permohonan) throws Exception {
		tempat_tampal = new Vector();
		tempat_tampal.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT INITCAP(TEMPAT) AS TEMPAT,TARIKH_TAMPAL,JENIS_TEMPAT_TAMPAL FROM TBLPPTNOTISAWAM  WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
            myLogger.info("tempat tampal :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("TEMPAT") == null) {
					h.put("TEMPAT", "");
				} else {
					h.put("TEMPAT", rs.getString("TEMPAT"));
				}
				
				if (rs.getString("JENIS_TEMPAT_TAMPAL") == null) {
					h.put("JENIS_TEMPAT_TAMPAL", "");
				} else {
					h.put("JENIS_TEMPAT_TAMPAL", rs.getString("JENIS_TEMPAT_TAMPAL"));
				}
				
				h.put("TARIKH_TAMPAL", rs
						.getString("TARIKH_TAMPAL") == null ? "" : Format
						.format(rs.getDate("TARIKH_TAMPAL")));
				
				
				
				
				tempat_tampal.addElement(h);
						
			}
			
			return tempat_tampal;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector maklumat_hakmilik_mmk_pb_siasatan = null;
	public Vector maklumat_hakmilik_mmk_pb_siasatan(String id_siasatan,String id_penarikan) throws Exception {
		maklumat_hakmilik_mmk_pb_siasatan = new Vector();
		maklumat_hakmilik_mmk_pb_siasatan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT " +			
			"DISTINCT " +
					"initcap(PPB.NAMA_PB) AS NAMA_PB FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H,TBLPPTPIHAKBERKEPENTINGAN PPB,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND S.ID_HAKMILIK = PH.ID_HAKMILIK "
						+"    AND H.ID_HAKMILIK = HPB.ID_HAKMILIK "
						+"    AND HPB.ID_JENISPB NOT IN (40,41,42) "
						+"    AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
						//+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'"
						+"   AND S.ID_SIASATAN = '"+id_siasatan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("NAMA_PB") == null) {
					h.put("NAMA_PB", "");
				} else {
					h.put("NAMA_PB", rs.getString("NAMA_PB"));
				}
				
				maklumat_hakmilik_mmk_pb_siasatan.addElement(h);
						
			}
			
			return maklumat_hakmilik_mmk_pb_siasatan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	

	
	
	Vector senarai_lot_mmk = null;
	public Vector senarai_lot_mmk(String id_penarikan) throws Exception {
		senarai_lot_mmk = new Vector();
		senarai_lot_mmk.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT " +
				//	"H.NO_LOT " +
			" CASE "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL AND HM.NO_HAKMILIK IS NULL THEN HM.NO_LOT "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL AND HM.NO_HAKMILIK IS NOT NULL THEN HM.NO_LOT || CHR(32) || JHK.KOD_JENIS_HAKMILIK || HM.NO_HAKMILIK  "+
			
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL AND HM.NO_HAKMILIK IS NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT  "+
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL AND HM.NO_HAKMILIK IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || HM.NO_PT || CHR(32) || JHK.KOD_JENIS_HAKMILIK || HM.NO_HAKMILIK "+
						
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL AND HM.NO_HAKMILIK IS NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41)  "+
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL AND HM.NO_HAKMILIK IS NOT NULL THEN JL.KETERANGAN  || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT  || CHR(41) || CHR(32) || JHK.KOD_JENIS_HAKMILIK || HM.NO_HAKMILIK  "+
			
			
			" ELSE "+
			" '' "+
			" END AS NO_LOT "+
					"FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK HM,TBLRUJLOT JL,TBLRUJJENISHAKMILIK JHK "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = JHK.ID_JENISHAKMILIK(+) AND HM.ID_LOT = JL.ID_LOT(+) "
						+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				if (rs.getString("NO_LOT") == null) {
					h.put("NO_LOT", "");
				} else {
					h.put("NO_LOT", rs.getString("NO_LOT"));
				}
				
				senarai_lot_mmk.addElement(h);
				}				
			
			
			return senarai_lot_mmk;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	
	
	public void updateAwardPerintah(String status,String id_award,String id_hakmilikpb,String jumlahaward,String id_Masuk,String catatan_tdkhadir,String catatan_perintah) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		
		

		try {
			db = new Db();
			
			if(id_award.equals("")){
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			
			r1.add("STATUS_PENERIMA", status);
			r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
			r1.add("PERINTAH", catatan_perintah);
			
			r1.add("BAYAR_PAMPASAN", jumlahaward);			
			r1.add("ID_HAKMILIKPB", id_hakmilikpb);
			//r1.add("ID_SIASATAN", id_siasatan);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("ID_MASUK", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblpptaward");
			stmt1.executeUpdate(sql1);
			}
			else
			{
				
				
				Statement stmt1 = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				
				
				
				r1.update("ID_AWARD", id_award);	
				
				r1.add("STATUS_PENERIMA", status);
				r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
				r1.add("PERINTAH", catatan_perintah);
				r1.add("BAYAR_PAMPASAN", jumlahaward);
				//r1.add("ID_SIASATAN", id_siasatan);
				r1.add("ID_KEMASKINI", id_Masuk);				
				r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));				
				sql1 = r1.getSQLUpdate("tblpptaward");
				myLogger.info("UPDATE PAMAPASAN:" + sql1);
				stmt1.executeUpdate(sql1);	
			}

			myLogger.info("UPDATE PAMPASAN PERINTAH 1:" + sql1);
			

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void addAwardPerintah(String status,String id_siasatan,long id_award,String id_hakmilikpb,String jumlahaward,String id_Masuk,String catatan_tdkhadir,String catatan_perintah) throws Exception {

		Db db = null;
		String sql = "";
		String sql1 = "";
		
		

		try {
			db = new Db();
			
			
			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			
			r1.add("ID_AWARD", id_award);
			
			r1.add("ULASAN_TIDAK_HADIR", catatan_tdkhadir);
			r1.add("PERINTAH", catatan_perintah);
			r1.add("STATUS_PENERIMA", status);
			
			r1.add("BAYAR_PAMPASAN", jumlahaward);			
			r1.add("ID_HAKMILIKPB", id_hakmilikpb);
			r1.add("ID_SIASATAN", id_siasatan);
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("ID_MASUK", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			r1.add("TARIKH_MASUK", r1.unquote("sysdate"));
			sql1 = r1.getSQLInsert("tblpptaward");
			stmt1.executeUpdate(sql1);
			

			myLogger.info("UPDATE PAMPASAN PERINTAH 2:" + sql1);
			

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	Vector maklumat_hakmilik_mmk_pemilik_siasatan = null;
	public Vector maklumat_hakmilik_mmk_pemilik_siasatan(String id_siasatan,String id_penarikan) throws Exception {
		maklumat_hakmilik_mmk_pemilik_siasatan = new Vector();
		maklumat_hakmilik_mmk_pemilik_siasatan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT " +			
			"DISTINCT " +
					"UPPER(PPB.NAMA_PB) AS NAMA_PB FROM TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PH," +
					"TBLPPTHAKMILIK H,TBLPPTPIHAKBERKEPENTINGAN PPB,TBLPPTHAKMILIKPB HPB,TBLPPTSIASATAN S "
						+"    WHERE PB.ID_PENARIKANBALIK = PH.ID_PENARIKANBALIK "
						+"    AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
						+"    AND S.ID_HAKMILIK = PH.ID_HAKMILIK "
						+"    AND H.ID_HAKMILIK = HPB.ID_HAKMILIK "
						+"    AND HPB.ID_JENISPB = '1' "
						+"    AND HPB.ID_PIHAKBERKEPENTINGAN = PPB.ID_PIHAKBERKEPENTINGAN "
						//+"    AND PH.FLAG_WARTA = 'Y' "
						+"   AND PB.ID_PENARIKANBALIK = '"+id_penarikan+"'"
						+"   AND S.ID_SIASATAN = '"+id_siasatan+"'";

			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				
				if (rs.getString("NAMA_PB") == null) {
					h.put("NAMA_PB", "");
				} else {
					h.put("NAMA_PB", rs.getString("NAMA_PB"));
				}
				
				maklumat_hakmilik_mmk_pemilik_siasatan.addElement(h);
						
			}
			
			return maklumat_hakmilik_mmk_pemilik_siasatan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector senarai_permohonan_laporan = null;

	public Vector senarai_permohonan_laporan(String negeriuser,String id_pejabat,String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
		senarai_permohonan_laporan = new Vector();
		Db db = null;
		senarai_permohonan_laporan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.ID_FAIL,F.NO_FAIL, P.NO_RUJUKAN_UPT,"
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,TBLRUJPEJABATJKPTG PEJ,"
					+ " TBLRUJSTATUS S "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
					+"  AND F.ID_NEGERI = PEJ.ID_NEGERI "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
					+ " AND PEJ.ID_JENISPEJABAT IN (21,22)"
					+" AND PEJ.ID_SEKSYEN = '1' "
				/*	+ " AND NVL(S.ID_STATUS,0) IN ('31','35','38','43','46','48','52','54','58','59',"
					+ " '62','66','68','72','74','76','181')" */
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			   if(!negeriuser.equals("16"))
			    {
				sql += "AND  F.ID_NEGERI = '"+user_negeri+"' ";
			    }
			    

			// kena filter by status (sudah diwartakan)

			if (no_fail != "") {
				if (!no_fail.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_FAIL) LIKE '%"
							+ no_fail.trim() + "%'";
				}
			}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status + "'";
				}
			}
			if (id_pejabat != "" && !id_pejabat.equals("16")) {
				if (!id_pejabat.trim().equals("")) {
					sql = sql + " AND UPPER(PEJ.ID_PEJABATJKPTG) LIKE '"
							+ id_pejabat + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL PENARIKAN CARI :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK"));
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL"));
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				senarai_permohonan_laporan.addElement(h);
			}
			return senarai_permohonan_laporan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	

	Vector senarai_hakmilik_pembatalan = null;

	public Vector senarai_hakmilik_pembatalan(String id_permohonan,
			String id_penarikan) throws Exception {
		senarai_hakmilik_pembatalan = new Vector();
		Db db = null;
		senarai_hakmilik_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT H.NO_SUBJAKET,H.ID_HAKMILIK,H.NO_PT," +
				//	"H.NO_LOT," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
		//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"JH.KETERANGAN AS JENIS_HAKMILIK,JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,"
					+ " M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH,H.LUAS_AMBIL, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M, "
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) AND H.ID_LOT = JL.ID_LOT(+)  "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "			
					+ " AND H.ID_HAKMILIK NOT IN "
					+ " (SELECT DISTINCT H.ID_HAKMILIK  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPEMBATALAN PB,TBLPPTPEMBATALANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PEMBATALAN = PB.ID_PEMBATALAN  "
					+ " AND PB.ID_PEMBATALAN = '"
					+ id_penarikan
					+ "' )"
					+ " AND H.ID_PERMOHONAN = '"
					+ id_permohonan
					+ "' "
					+ " 	ORDER BY LPAD(H.NO_SUBJAKET,10) ASC 	";

			myLogger.info("SENARAI HAKMILIK PENARIKAN" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));			
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));

				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
					
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					 
					}
				
				
				
				
				
				h.put("LUAS_LOT_EDIT", rs.getString("LUAS_LOT") == null ? ""
						: rs.getDouble("LUAS_LOT"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));				
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));


				senarai_hakmilik_pembatalan.addElement(h);
			}
			return senarai_hakmilik_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	Vector senarai_hakmilik_batal_pembatalan = null;

	public Vector senarai_hakmilik_batal_pembatalan(String id_penarikan)
			throws Exception {
		senarai_hakmilik_batal_pembatalan = new Vector();
		Db db = null;
		senarai_hakmilik_batal_pembatalan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT " +
					//"PH.FLAG_WARTA," +
					" H.NO_SUBJAKET,PH.ID_PEMBATALANHAKMILIK,PH.LUAS_LOT_TARIK,H.LUAS_AMBIL,PH.LUAS_AMBIL AS LUAS_AMBIL_ASAL,H.LUAS_LOT_TARIK,H.ID_HAKMILIK,PB.ID_PEMBATALAN,H.NO_PT," +
				//	"H.NO_LOT, "
					" CASE "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
				//	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
					" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
					" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
					" ELSE "+
					" '' "+
					" END AS NO_LOT, "
					+ " JH.KETERANGAN AS JENIS_HAKMILIK, "
					+ " JH.KOD_JENIS_HAKMILIK,H.NO_HAKMILIK,M.NAMA_MUKIM,H.LUAS_LOT,NVL(H.ID_KATEGORITANAH,'0') AS ID_KATEGORITANAH, "
					+ " N.NAMA_NEGERI,D.NAMA_DAERAH,H.ID_UNITLUASLOT_CONVERT,H.ID_UNITLUASAMBIL_CONVERT,H.NAMA_LUAS_ASAL,H.NAMA_LUAS_AMBIL  "
					+ " FROM TBLPPTHAKMILIK H,TBLRUJJENISHAKMILIK JH,TBLRUJMUKIM M,TBLPPTPEMBATALAN PB,TBLPPTPEMBATALANHAKMILIK PH,"
					+ " TBLRUJNEGERI N,TBLRUJDAERAH D,TBLRUJLOT JL  "
					+ " WHERE H.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND H.ID_MUKIM = M.ID_MUKIM "
					+ " AND H.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND H.ID_DAERAH = D.ID_DAERAH(+) AND H.ID_LOT = JL.ID_LOT(+) "
					+ " AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
					+ " AND PH.ID_PEMBATALAN = PB.ID_PEMBATALAN "
					+ " AND PB.ID_PEMBATALAN = '"
					+ id_penarikan
					+ "' "
					+ " ORDER BY LPAD(H.NO_SUBJAKET,10) ASC ";
			myLogger.info("SENARAI SUDAH PENARIKAN :" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
			//	h.put("FLAG_WARTA", rs.getString("FLAG_WARTA") == null ? ""
				//		: rs.getString("FLAG_WARTA"));
				h.put("ID_PENARIKANHAKMILIK", rs
						.getString("ID_PEMBATALANHAKMILIK") == null ? "" : rs
						.getString("ID_PEMBATALANHAKMILIK"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("ID_PEMBATALAN",
						rs.getString("ID_PEMBATALAN") == null ? "" : rs
								.getString("ID_PEMBATALAN"));
				h.put("NO_PT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT"));
				h.put("JENIS_HAKMILIK",
						rs.getString("JENIS_HAKMILIK") == null ? "" : rs
								.getString("JENIS_HAKMILIK"));
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
						: rs.getString("NAMA_MUKIM"));
				h.put("ID_KATEGORITANAH",
						rs.getString("ID_KATEGORITANAH") == null ? "" : rs
								.getString("ID_KATEGORITANAH"));
			
				
				if (rs.getString("ID_UNITLUASLOT_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " MP");				
					}					
					if (rs.getString("ID_UNITLUASLOT_CONVERT").equals("1")) {
						h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
								: rs.getDouble("LUAS_LOT") + " HEKTAR");					
					}
				} else {
					h.put("LUAS_LOT", rs.getString("LUAS_LOT") == null ? ""
							: rs.getDouble("LUAS_LOT"));			
					}
							
				if (rs.getString("ID_UNITLUASAMBIL_CONVERT")!=null) {					
					if (!rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " MP");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " MP");
					}					
					if (rs.getString("ID_UNITLUASAMBIL_CONVERT").equals("1")) {
						h.put("LUAS_AMBIL",
								rs.getString("LUAS_AMBIL") == null ? "" : rs
										.getDouble("LUAS_AMBIL")
										+ " HEKTAR");
						h.put("LUAS_LOT_TARIK",
								rs.getString("LUAS_LOT_TARIK") == null ? ""
										: rs.getDouble("LUAS_LOT_TARIK")
												+ " HEKTAR");
					}

				} else {
					
					h.put("LUAS_AMBIL", rs.getString("LUAS_AMBIL") == null ? ""
							: rs.getDouble("LUAS_AMBIL"));
					h.put("LUAS_LOT_TARIK",
							rs.getString("LUAS_LOT_TARIK") == null ? "" : rs
									.getDouble("LUAS_LOT_TARIK")); 
					}
				
				
				
				h.put("LUAS_LOT_TARIK_EDIT",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getDouble("LUAS_LOT_TARIK"));
				h.put("LUAS_LOT_TARIK_VALUE",
						rs.getString("LUAS_LOT_TARIK") == null ? "0" : rs
								.getString("LUAS_LOT_TARIK"));
				h.put("LUAS_AMBIL_EDIT",
						rs.getString("LUAS_AMBIL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL"));
				h.put("LUAS_AMBIL_ASAL",
						rs.getString("LUAS_AMBIL_ASAL") == null ? "0" : rs
								.getDouble("LUAS_AMBIL_ASAL"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
				
				
				h.put("ID_UNITLUASLOT_CONVERT", rs.getString("ID_UNITLUASLOT_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASLOT_CONVERT"));
				
				h.put("ID_UNITLUASAMBIL_CONVERT", rs.getString("ID_UNITLUASAMBIL_CONVERT") == null ? ""
						: rs.getString("ID_UNITLUASAMBIL_CONVERT"));
				
				h.put("NAMA_LUAS_ASAL", rs.getString("NAMA_LUAS_ASAL") == null ? ""
						: rs.getString("NAMA_LUAS_ASAL"));
				
				h.put("NAMA_LUAS_AMBIL", rs.getString("NAMA_LUAS_AMBIL") == null ? ""
						: rs.getString("NAMA_LUAS_AMBIL"));
				
				
				senarai_hakmilik_batal_pembatalan.addElement(h);
			}
			return senarai_hakmilik_batal_pembatalan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void pilih_pembatalan(String id_kat,String id_hakmilik, String lot_tarik,
			String lot_ambil, String luas_lot, String id_Masuk,
			String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		String sql1 = "";
		try {
			double l_ambil = Double.parseDouble(lot_ambil);
			double l_tarik = Double.parseDouble(lot_tarik);
			double jumlah_luas = l_ambil - l_tarik;
		    String flag_warta = "";
		    
			if(id_kat.equals("2"))
			{
				if((l_tarik > 0.25) || (l_tarik/l_ambil > 0.01))
				{
					flag_warta = "Y";
				}				
				
			}
			else
			{
				if((l_tarik > 2500) || (l_tarik/l_ambil > 0.01))
				{
					flag_warta = "Y";
				}	
			}

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("ID_HAKMILIK", id_hakmilik);
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("LUAS_AMBIL", l_ambil);
			r.add("LUAS_TINGGAL", "");
			r.add("ID_MASUK", id_Masuk);
			//r.add("FLAG_WARTA", flag_warta);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblpptpembatalanhakmilik");
			myLogger.info("SQL INSET LOT PENARIKANHAKMILIK :"
					+ sql);
			stmt.executeUpdate(sql);

			Statement stmt1 = db.getStatement();
			SQLRenderer r1 = new SQLRenderer();
			r1.update("ID_HAKMILIK", id_hakmilik);
			r1.add("LUAS_AMBIL", jumlah_luas);
			
			if(jumlah_luas == 0)
			{
				r1.add("FLAG_PEMBATALAN_KESELURUHAN", "Y");
			}	
			else 
			{
				r1.add("FLAG_PEMBATALAN_KESELURUHAN", "");
			}	
			
			r1.add("ID_KEMASKINI", id_Masuk);
			r1.add("TARIKH_KEMASKINI", r1.unquote("sysdate"));
			sql1 = r1.getSQLUpdate("tblppthakmilik");
			myLogger.info("SQL UPDATE LOT PENARIKANHAKMILIK :"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void batalkan_pembatalan(String id_hakmilik, String lot_tarik,
			String id_Masuk, String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIK", id_hakmilik);
			r.add("FLAG_PEMBATALAN", "Y");
			r.add("LUAS_LOT_TARIK", lot_tarik);
			r.add("ID_PEMBATALAN", id_pembatalan);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilik");

			myLogger.info("SQL UPDATE LOT BATALKAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}

	Vector nowarta_lot_mmk = null;

	
	public Vector nowarta_lot_mmk(String id_permohonan) throws Exception {
		nowarta_lot_mmk = new Vector();
		nowarta_lot_mmk.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT NO_WARTA,"
    +" CASE"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=01 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '||  'Januari' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=02 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'Februari' ||' '|| TO_CHAR(TARIKH_WARTA,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=03 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '||  'Mac' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=04 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'April' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=05 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'Mei' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=06 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '||  'Jun' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY') "               
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=07 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '||  'Julai' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY') "
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=08 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '||  'Ogos' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')  "      
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=09 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'September' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=10 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'Oktober' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')  "      
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=11 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'November' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY')"
        +" WHEN TO_CHAR(TARIKH_WARTA ,'MM')=12 THEN TO_CHAR(TARIKH_WARTA ,'DD') ||' '|| 'Disember' ||' '|| TO_CHAR(TARIKH_WARTA ,'YYYY') "                      
     +" END AS TARIKH_WARTA   FROM "
     +" TBLPPTWARTA  "
     +" WHERE ID_WARTA = (SELECT MAX(ID_WARTA) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = '"+id_permohonan+"')";
			
			myLogger.info("SQL WARTA :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				if (rs.getString("NO_WARTA") == null) {
					h.put("NO_WARTA", "..........");
				} else {
					h.put("NO_WARTA", rs.getString("NO_WARTA"));
				}
				
				
				h.put("TARIKH_WARTA",
						rs.getString("TARIKH_WARTA") == null ? ".........." : rs.getString("TARIKH_WARTA"));
				
				nowarta_lot_mmk.addElement(h);
				}				
			
			
			return nowarta_lot_mmk;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void delete_subsiasatan(String id_siasatan) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN = "
					+ id_siasatan;
			myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void delete_subsiasatan_byPenarikan(String id_penarikan) throws Exception {
		Db db = null;
		String sql1 = "";
		String sql2 = "";
		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN IN (SELECT ID_SIASATAN " +
					"FROM TBLPPTSIASATAN WHERE ID_PENARIKANBALIK = "
					+ id_penarikan+")";
			myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);
			
			Statement stmt2 = db.getStatement();
			sql2 = " DELETE FROM TBLPPTSIASATAN WHERE ID_PENARIKANBALIK = "
					+ id_penarikan;
			myLogger.info("SQL DELETE TBLPPTPIHAKBERKEPENTINGAN BY PB:"
					+ sql2);
			stmt2.executeUpdate(sql2);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void delete_subaward(String id_award) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTSUBAWARD WHERE ID_AWARD = "
					+ id_award;
			myLogger.info("SQL DELETE TBLPPTSUBAWARD BY PB:"
					+ sql1);
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void delete_subMMK(String id_MMK) throws Exception {
		Db db = null;

		String sql1 = "";

		try {
			db = new Db();
			Statement stmt1 = db.getStatement();
			sql1 = " DELETE FROM TBLPPTSUBMMK WHERE ID_MMK = '"+id_MMK+"'" ;
			stmt1.executeUpdate(sql1);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	public void simpan_subsiasatan(String txtUlasanTangguh,String id_siasatan,String column,String user_id) throws Exception {
		Db db = null;
		String sql = "";

	    if(!txtUlasanTangguh.equals(""))
	    {
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("KETERANGAN_SUBSIASATAN", txtUlasanTangguh);
			r.add("FLAG_JENIS_SIASATAN", column);
			r.add("ID_SIASATAN", id_siasatan);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPTSUBSIASATAN");
			myLogger.info("SQL INSERT TBLPPTSUBSIASATAN :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	    }

	}
	
	public void simpan_subAward(String txtUlasanKerosakan,String txtUlasanKerosakanAward,long id_award,String column,String user_id) throws Exception {
		Db db = null;
		String sql = "";

	    if(txtUlasanKerosakan.equals("") && txtUlasanKerosakanAward.equals(""))
	    {
	    	
	    }
	    else
	    {
	    	
	   
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("KETERANGAN_SUBAWARD", txtUlasanKerosakan);
			r.add("JUMLAH_SUBAWARD", txtUlasanKerosakanAward);
			r.add("FLAG_JENIS_AWARD", column);
			r.add("ID_AWARD", id_award);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPTSUBAWARD");
			myLogger.info("SQL INSERT TBLPPTSUBAWARD :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	    }
	}

	public void simpan_subMMK(String txtUlasanKerosakan,String txtUlasanKerosakanAward, long id_award,String column,String user_id) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_submmk = DB.getNextID("TBLPPTSUBMMK_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTSUBMMK " +
        			"(id_submmk,KETERANGAN_SUBMMK,FLAG_JENIS_SUBMMK,FLAG_JENIS_MMK,ID_MMK,ID_MASUK,ID_KEMASKINI,TARIKH_MASUK,TARIKH_KEMASKINI) " +
        			"values(?,?,?,?,?,?,?,sysdate,sysdate)");
        	ps.setLong(1, id_submmk);
        	ps.setString(2, txtUlasanKerosakan);
        	ps.setString(3, txtUlasanKerosakanAward);
        	ps.setString(4, column);
        	ps.setLong(5, id_award);
        	ps.setString(6, user_id);
        	ps.setString(7, user_id);
        	ps.executeUpdate();
            con.commit();
            myLogger.info("PreparedStatement "+ps);
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah simpan rekod");
	    }finally {
		      if (db != null) db.close();
	    }
  }
	
	/*
	public void simpan_subMMK(String txtUlasanKerosakan,String txtUlasanKerosakanAward,
			long id_award,String column,String user_id) throws Exception {
		Db db = null;
		String sql = "";

	    if(txtUlasanKerosakan.equals(""))
	    {
	    	
	    }
	    else
	    {
	    	
	   
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("KETERANGAN_SUBMMK", txtUlasanKerosakan);
			r.add("FLAG_JENIS_SUBMMK", txtUlasanKerosakanAward);
			r.add("FLAG_JENIS_MMK", column);
			r.add("ID_MMK", id_award);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPTSUBMMK");
			myLogger.info("SQL INSERT TBLPPTSUBMMK :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	    }
	}
	*/
	
	public void update_subMMK(String txtUlasanKerosakan,String txtUlasanKerosakanAward,String id_award,String column,String user_id) throws Exception {
		Db db = null;
		String sql = "";

	    if(txtUlasanKerosakan.equals(""))
	    {
	    	
	    }
	    else
	    {
	    	
	   
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("KETERANGAN_SUBMMK", txtUlasanKerosakan);
			r.add("FLAG_JENIS_SUBMMK", txtUlasanKerosakanAward);
			r.add("FLAG_JENIS_MMK", column);
			r.add("ID_MMK", id_award);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPTSUBMMK");
			myLogger.info("SQL INSERT TBLPPTSUBMMK :" + sql);
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	    }
	}
	
	
Vector list_subsiasatan = null;	
	public Vector list_subsiasatan(String id_siasatan,String jenis_siasatan) throws Exception {
		list_subsiasatan = new Vector();
		list_subsiasatan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_SUBSIASATAN,KETERANGAN_SUBSIASATAN FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN = '"+id_siasatan+"' AND FLAG_JENIS_SIASATAN = '"+jenis_siasatan+"' ORDER BY ID_SUBSIASATAN ASC";
			myLogger.info("SQL WARTA :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

            	
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				if (rs.getString("ID_SUBSIASATAN") == null) {
					h.put("ID_SUBSIASATAN", "");
				} else {
					h.put("ID_SUBSIASATAN", rs.getString("ID_SUBSIASATAN"));
				}
				
				if (rs.getString("KETERANGAN_SUBSIASATAN") == null) {
					h.put("KETERANGAN_SUBSIASATAN", "");
				} else {
					h.put("KETERANGAN_SUBSIASATAN", rs.getString("KETERANGAN_SUBSIASATAN"));
				}
				
				list_subsiasatan.addElement(h);
				}				
			
			
			return list_subsiasatan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
Vector senarai_perintah_award = null;
	
public Vector senarai_perintah_award(String id_pembatalan,String id_siasatan,String id_award)
	 throws Exception {
	senarai_perintah_award = new Vector();
Db db = null;
senarai_perintah_award.clear();
String sql = "";

try {
	db = new Db();
	Statement stmt = db.getStatement();
	sql = "SELECT AW.PERINTAH,AW.ULASAN_TIDAK_HADIR,AW.STATUS_PENERIMA,AW.STATUS_PENERIMA,SAW.KETERANGAN_SUBAWARD,SAW.JUMLAH_SUBAWARD,SAW.FLAG_JENIS_AWARD,H.ID_HAKMILIK,AW.ID_AWARD,PB.NAMA_PB,PB.ID_PIHAKBERKEPENTINGAN,"
			+ "PB.NO_PB," +
			" CASE "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
			//" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
			" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
			" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
			" ELSE "+
			" '' "+
			" END AS NO_LOT, "+
					"M.NAMA_MUKIM,AW.BAYAR_PAMPASAN,"
			+ "BPB.SYER_ATAS,BPB.SYER_BAWAH,HPB.ID_HAKMILIKPB "
			+ "FROM TBLPPTPIHAKBERKEPENTINGAN PB, TBLPPTAWARD AW,TBLPPTSIASATAN S,"
			+ "TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLRUJLOT JL,TBLPPTBAHAGIANPB BPB,TBLRUJMUKIM M,TBLPPTPENARIKANHAKMILIK PH,TBLPPTSUBAWARD SAW  "
			+ "WHERE HPB.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)"
			+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK "
			+ " AND NVL(HPB.ID_JENISPB,0) NOT IN ('40','41','42') " 
			+" AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
			+ "AND H.ID_MUKIM = M.ID_MUKIM(+) "
			+" AND PH.ID_HAKMILIK = H.ID_HAKMILIK "
			+" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+)"
			+" AND H.ID_HAKMILIK = S.ID_HAKMILIK "
			+" AND PH.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK AND H.ID_LOT = JL.ID_LOT(+) "
			+" AND AW.ID_AWARD = SAW.ID_AWARD "
			+" AND AW.ID_AWARD = '" + id_award + "' "
			+" AND S.ID_SIASATAN = '" + id_siasatan + "' "
			+" AND PH.ID_PENARIKANBALIK = '" + id_pembatalan + "'  ";

	sql += "ORDER BY SAW.ID_SUBAWARD ASC ";
	myLogger.info("SENARAI PERINTAH award :"
			+ sql);

	ResultSet rs = stmt.executeQuery(sql);
	Hashtable h;
	int bil = 0;
	while (rs.next()) {
		bil = bil + 1;
		h = new Hashtable();
		h.put("BIL", bil);
		
		h.put("PERINTAH", rs.getString("PERINTAH") == null ? ""
				: rs.getString("PERINTAH"));
		h.put("ULASAN_TIDAK_HADIR", rs.getString("ULASAN_TIDAK_HADIR") == null ? ""
				: rs.getString("ULASAN_TIDAK_HADIR"));
		h.put("STATUS_PENERIMA", rs.getString("STATUS_PENERIMA") == null ? ""
				: rs.getString("STATUS_PENERIMA"));
		h.put("ID_AWARD", rs.getString("ID_AWARD") == null ? ""
				: rs.getString("ID_AWARD"));
		h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
				: rs.getString("ID_HAKMILIK"));
		h.put("ID_HAKMILIKPB",
				rs.getString("ID_HAKMILIKPB") == null ? "" : rs
						.getString("ID_HAKMILIKPB"));
		h.put("ID_PIHAKBERKEPENTINGAN", rs
				.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
				.getString("ID_PIHAKBERKEPENTINGAN"));
		h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
				.getString("NAMA_PB"));
		h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
				.getString("NO_PB"));
		h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
				.getString("NO_LOT"));
		h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM") == null ? ""
				: rs.getString("NAMA_MUKIM"));
		h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "" : rs
				.getString("SYER_ATAS"));
		h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
				: rs.getString("SYER_BAWAH"));
		
		
		
		h.put("BAYAR_PAMPASAN",
				rs.getString("BAYAR_PAMPASAN") == null ? "" : rs
						.getDouble("BAYAR_PAMPASAN"));
		
		h.put("JUMLAH_SUBAWARD",
				rs.getString("JUMLAH_SUBAWARD") == null ? "" : rs
						.getDouble("JUMLAH_SUBAWARD"));
		

		h.put("KETERANGAN_SUBAWARD",
				rs.getString("KETERANGAN_SUBAWARD") == null ? "" : rs
						.getString("KETERANGAN_SUBAWARD"));
		
		h.put("FLAG_JENIS_AWARD",
				rs.getString("FLAG_JENIS_AWARD") == null ? "" : rs
						.getString("FLAG_JENIS_AWARD"));
		
		
		senarai_perintah_award.addElement(h);
	}
	return senarai_perintah_award;
} finally {
	if (db != null)
		db.close();
}
}
	

/*
public Hashtable maklumat_pb(String id_hakmilikpb)
	 throws Exception {	
Db db = null;
String sql = "";
try {
	db = new Db();
	Statement stmt = db.getStatement();
	sql = " SELECT PB.NAMA_PB,PB.NO_PB,NOPB.KETERANGAN, "+
	" CASE "+
	" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
	//" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NULL THEN JL.KETERANGAN || H.NO_PT "+
	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
	" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
	" ELSE "+
	" '' "+
	" END AS NO_LOT "+
	" FROM TBLPPTHAKMILIKPB HPB,TBLRUJLOT JL,TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISNOPB NOPB,TBLPPTHAKMILIK H "+
	" WHERE HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
	" AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) "+
	" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK  AND H.ID_LOT = JL.ID_LOT(+) "+
	//" AND H.ID_LOT = JL.ID_LOT(+) "+
	" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";

	sql += "ORDER BY NO_LOT ASC,NAMA_PB ASC ";
	myLogger.info("SENARAI PB award :"
			+ sql);

	ResultSet rs = stmt.executeQuery(sql);
	Hashtable h;
	h = new Hashtable();
	int bil = 0;
	while (rs.next()) {
	h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? ""
				: rs.getString("NAMA_PB"));
	
	h.put("NO_PB", rs.getString("NO_PB") == null ? ""
			: rs.getString("NO_PB"));
	
	h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
			: rs.getString("KETERANGAN"));
	
	h.put("NO_LOT", rs.getString("NO_LOT") == null ? ""
			: rs.getString("NO_LOT"));
	}
	return h;
} finally {
	if (db != null)
		db.close();
}
}
*/



public Hashtable maklumat_pb(String id_hakmilikpb)
throws Exception {	
Db db = null;
String sql = "";
try {
	db = new Db();
	Statement stmt = db.getStatement();
	sql = " SELECT H.NO_SUBJAKET,PB.NAMA_PB,PB.NO_PB,NOPB.KETERANGAN, "+

	" CASE "+
	" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NULL THEN H.NO_LOT "+
	" WHEN H.NO_LOT IS NULL AND H.NO_PT IS NOT NULL THEN  JL.KETERANGAN  || CHR(32) || H.NO_PT  "+
	" WHEN H.NO_LOT IS NOT NULL AND H.NO_PT IS NOT NULL THEN JL.KETERANGAN  || H.NO_PT || CHR(32) || CHR(40) || H.NO_LOT  || CHR(41)  "+
	" ELSE "+
	" '' "+
	" END AS NO_LOT,BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH "+

	" FROM TBLPPTHAKMILIKPB HPB,TBLPPTBAHAGIANPB BPB,TBLRUJLOT JL,TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISNOPB NOPB,TBLPPTHAKMILIK H "+
	" WHERE HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "+
	" AND PB.ID_JENISNOPB = NOPB.ID_JENISNOPB(+) "+		
	" AND HPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) "+
	" AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_LOT = JL.ID_LOT(+) "+

	" AND HPB.ID_HAKMILIKPB = '"+id_hakmilikpb+"' ";

	sql += "ORDER BY LPAD(H.NO_SUBJAKET,10) ASC,NAMA_PB ASC ";
	myLogger.info("SENARAI PB award :"
			+ sql);

	ResultSet rs = stmt.executeQuery(sql);
	Hashtable h;
	h = new Hashtable();
	int bil = 0;
	while (rs.next()) {
		
		
	h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? ""
				: rs.getString("ID_BAHAGIANPB"));
	h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? ""
			: rs.getString("SYER_ATAS"));
	h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
			: rs.getString("SYER_BAWAH"));		
	h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? ""
				: rs.getString("NAMA_PB"));
	
	h.put("NO_PB", rs.getString("NO_PB") == null ? ""
			: rs.getString("NO_PB"));
	
	h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
			: rs.getString("KETERANGAN"));
	
	h.put("NO_LOT", rs.getString("NO_LOT") == null ? ""
			: rs.getString("NO_LOT"));
	}
	return h;
} finally {
	if (db != null)
		db.close();
}
}




Vector senarai_submmk = null;

public Vector senarai_submmk(String id_mmk) throws Exception {
	senarai_submmk = new Vector();
	Db db = null;
	senarai_submmk.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT SMMK.ID_SUBMMK,SMMK.KETERANGAN_SUBMMK,SMMK.ID_MMK,SMMK.FLAG_JENIS_MMK,SMMK.FLAG_JENIS_SUBMMK FROM TBLPPTSUBMMK SMMK,TBLPPTMMK MMK "
			  +" WHERE MMK.ID_MMK = SMMK.ID_MMK "
			  +" AND MMK.ID_MMK = '"+id_mmk+"' ORDER BY SMMK.ID_SUBMMK ASC";

		myLogger.info("SUB MMK:" + sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {

			h = new Hashtable();
			h.put("ID_SUBMMK", rs.getString("ID_SUBMMK") == null ? "" : rs
					.getString("ID_SUBMMK"));
			h.put("ID_MMK", rs.getString("ID_MMK") == null ? "" : rs
					.getString("ID_MMK"));
			
			h.put("KETERANGAN_SUBMMK",rs.getString("KETERANGAN_SUBMMK")==null?"":Utils.unescapeJavaScript(rs.getString("KETERANGAN_SUBMMK")));
			
			h.put("FLAG_JENIS_MMK", rs.getString("FLAG_JENIS_MMK") == null ? "" : rs
					.getString("FLAG_JENIS_MMK"));
			h.put("FLAG_JENIS_SUBMMK", rs.getString("FLAG_JENIS_SUBMMK") == null ? "" : rs
					.getString("FLAG_JENIS_SUBMMK"));
			
			
			
			
			
		
			senarai_submmk.addElement(h);
		}
		return senarai_submmk;
	} finally {
		if (db != null)
			db.close();
	}
}



Vector senarai_submmk_bypenarikan = new Vector();
public Vector senarai_submmk_bypenarikan(String id_penarikan) throws Exception {
	senarai_submmk_bypenarikan = new Vector();
	Db db = null;
	senarai_submmk_bypenarikan.clear();
	String sql = "";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

		sql = "SELECT SMMK.ID_SUBMMK,SMMK.KETERANGAN_SUBMMK,SMMK.ID_MMK,SMMK.FLAG_JENIS_MMK,SMMK.FLAG_JENIS_SUBMMK FROM TBLPPTSUBMMK SMMK,TBLPPTMMK MMK "
			  +" WHERE MMK.ID_MMK = SMMK.ID_MMK "
			  +" AND MMK.ID_PENARIKANBALIK = '"+id_penarikan+"' ORDER BY SMMK.ID_SUBMMK ASC";

		myLogger.info("SUB MMK:" + sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {

			h = new Hashtable();
			h.put("ID_SUBMMK", rs.getString("ID_SUBMMK") == null ? "" : rs
					.getString("ID_SUBMMK"));
			h.put("ID_MMK", rs.getString("ID_MMK") == null ? "" : rs
					.getString("ID_MMK"));
			h.put("KETERANGAN_SUBMMK", rs.getString("KETERANGAN_SUBMMK") == null ? "" : rs
					.getString("KETERANGAN_SUBMMK"));
			h.put("FLAG_JENIS_MMK", rs.getString("FLAG_JENIS_MMK") == null ? "" : rs
					.getString("FLAG_JENIS_MMK"));
			h.put("FLAG_JENIS_SUBMMK", rs.getString("FLAG_JENIS_SUBMMK") == null ? "" : rs
					.getString("FLAG_JENIS_SUBMMK"));
			
			
			
			
			
		
			senarai_submmk_bypenarikan.addElement(h);
		}
		return senarai_submmk_bypenarikan;
	} finally {
		if (db != null)
			db.close();
	}
}



Vector maklumat_siasatan_history = null;
public Vector maklumat_siasatan_history(String id_penarikan,String id_hakmilik,int bil_siasatan) throws Exception {
	maklumat_siasatan_history = new Vector();
	maklumat_siasatan_history.clear();
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		sql = "SELECT S.KETERANGAN_JPPH,S.AKUJANJI_AGENSI,S.ULASAN_PERINTAH,S.KEROSAKAN_TANAH,S.KOS_DITANGGUNG,S.ID_PEGAWAI_SIASATAN,PG.USER_NAME AS NAMA_PEGAWAI,S.ULASAN_SIASATAN,PB.ID_PENARIKANBALIK,S.ID_HAKMILIK,S.BIL_SIASATAN,S.ID_SIASATAN,S.NO_KES,S.NO_SIASATAN,S.STATUS_SIASATAN,S.TARIKH_SIASATAN,S.MASA_SIASATAN, "
				+ " S.TEMPAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD,S.ID_NEGERI,S.ALASAN_TANGGUH,S.NILAIAN_JPPH,S.ID_UNITLUAS,S.BANTAHAN_TUANTNH,S.BANTAHAN_AGENSI, "
				+ " S.BANTAHAN_LAIN,S.TEMPOH_MILIK_TANAH,S.CARA_MILIK,S.HARGA_BELI,S.JENIS_BANGUNAN,S.JENIS_TANAMAN,S.FLAG_PECAH_SEMPADAN,S.FLAG_TUKAR_SYARAT, "
				+ " S.TARIKH_PECAH_SEMPADAN,S.TARIKH_TUKAR_SYARAT,S.STATUS_SEMASA,S.BEBANAN,S.KETERANGAN_TUAN_TANAH,S.KETERANGAN_AGENSI,S.KETERANGAN_JURUNILAI, "
				+ " S.TUNTUTAN_TUANTNH,S.TUNTUTAN_PB_BEBANAN,S.TUNTUTAN_PB_TDKDAFTAR,S.TUNTUTAN_PB_LAIN,S.ID_BORANGE,S.PERINTAH,S.TARIKH_AKHIR_TAMPAL, "
				+ " S.ID_PEGAWAI_SIASATAN,S.STATUS_TUNTUTAN,S.ID_BANDAR,S.JENIS_WAKTU_SIASATAN, "
				+ " T.ID_TANAH,T.HARGA_SEUNIT_SO,T.UNIT_HARGA_SO,T.BAYAR_PENJEJASAN,T.BAYAR_PECAHPISAH,T.BAYAR_NAIK_NILAISO,T.HARGA_PASARAN_SO,"
				+ " T.HARGA_SEUNIT_JPPH,T.UNIT_HARGA,T.HARGA_PASARAN,T.AMAUN_PENJEJASAN_JPPH,T.AMAUN_PECAHPISAH_JPPH,T.NAIK_NILAI_JPPH"
				+ " FROM TBLPPTSIASATAN S,TBLPPTHAKMILIK HM,"
				+ "TBLPPTPENARIKANBALIK PB,USERS PG,TBLPPTTANAH T,TBLPPTPENARIKANHAKMILIK PH "
				+ " WHERE S.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
			//	+ " AND HM.ID_PENARIKANBALIK = S.ID_PENARIKANBALIK "
				+" AND PH.ID_HAKMILIK = HM.ID_HAKMILIK "
				+" AND PH.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK "
				+ " AND S.ID_HAKMILIK = HM.ID_HAKMILIK"
				+ " AND S.ID_PEGAWAI_SIASATAN = PG.USER_ID(+)"
				+ " AND HM.ID_HAKMILIK = T.ID_HAKMILIK(+) "
				+ " AND S.ID_PENARIKANBALIK = '" + id_penarikan + "'" 
				+ " AND S.ID_HAKMILIK = '" + id_hakmilik + "'" 
				+ " AND S.BIL_SIASATAN = '" + bil_siasatan + "'" 
				+ "";

		myLogger.info("MAKLUMAT SIASATAN HISTORY :" + sql);

		ResultSet rs = stmt.executeQuery(sql);

		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;

			Hashtable h = new Hashtable();
			h.put("BIL", bil);

			h.put("KETERANGAN_JPPH", rs
					.getString("KETERANGAN_JPPH") == null ? "" : rs
					.getString("KETERANGAN_JPPH"));
			
			h.put("AKUJANJI_AGENSI", rs
					.getString("AKUJANJI_AGENSI") == null ? "" : rs
					.getString("AKUJANJI_AGENSI"));
			
			h.put("ID_PEGAWAI_SIASATAN", rs
					.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
					.getString("ID_PEGAWAI_SIASATAN"));
			h.put("ULASAN_PERINTAH", rs.getString("ULASAN_PERINTAH") == null ? ""
					: rs.getString("ULASAN_PERINTAH"));
			h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? ""
					: rs.getString("NAMA_PEGAWAI"));
			h.put("KETERANGAN_TUAN_TANAH", rs
					.getString("KETERANGAN_TUAN_TANAH") == null ? "" : rs
					.getString("KETERANGAN_TUAN_TANAH"));
			h.put("ID_PENARIKANBALIK",
					rs.getString("ID_PENARIKANBALIK") == null ? "" : rs
							.getString("ID_PENARIKANBALIK"));
			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
					: rs.getString("ID_HAKMILIK"));
			h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
					: rs.getString("ID_SIASATAN"));
			h.put("NO_KES", rs.getString("NO_KES") == null ? "" : rs
					.getString("NO_KES"));
			h.put("NO_SIASATAN", rs.getString("NO_SIASATAN") == null ? ""
					: rs.getString("NO_SIASATAN"));
			h.put("STATUS_SIASATAN",
					rs.getString("STATUS_SIASATAN") == null ? "" : rs
							.getString("STATUS_SIASATAN"));
			h.put("MASA_SIASATAN",
					rs.getString("MASA_SIASATAN") == null ? "" : rs
							.getString("MASA_SIASATAN"));
			h.put("TARIKH_SIASATAN",
					rs.getString("TARIKH_SIASATAN") == null ? "" : Format
							.format(rs.getDate("TARIKH_SIASATAN")));
			h.put("TEMPAT", rs.getString("TEMPAT") == null ? "" : rs
					.getString("TEMPAT"));
			h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
					.getString("ALAMAT1"));
			h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
					.getString("ALAMAT2"));
			h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
					.getString("ALAMAT3"));
			h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
					.getString("POSKOD"));
			h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
					.getString("ID_NEGERI"));
			h.put("ALASAN_TANGGUH",
					rs.getString("ALASAN_TANGGUH") == null ? "" : rs
							.getString("ALASAN_TANGGUH"));
			h.put("ID_UNITLUAS", rs.getString("ID_UNITLUAS") == null ? ""
					: rs.getString("ID_UNITLUAS"));
			h.put("BANTAHAN_TUANTNH",
					rs.getString("BANTAHAN_TUANTNH") == null ? "" : rs
							.getString("BANTAHAN_TUANTNH"));
			h.put("BANTAHAN_AGENSI",
					rs.getString("BANTAHAN_AGENSI") == null ? "" : rs
							.getString("BANTAHAN_AGENSI"));
			h.put("NILAIAN_JPPH", rs.getString("NILAIAN_JPPH") == null ? ""
					: Format.format(rs.getDouble("NILAIAN_JPPH")));
			h.put("BANTAHAN_LAIN",
					rs.getString("BANTAHAN_LAIN") == null ? "" : rs
							.getString("BANTAHAN_LAIN"));
			h.put("TEMPOH_MILIK_TANAH",
					rs.getString("TEMPOH_MILIK_TANAH") == null ? "" : rs
							.getString("TEMPOH_MILIK_TANAH"));
			h.put("CARA_MILIK", rs.getString("CARA_MILIK") == null ? ""
					: rs.getString("CARA_MILIK"));
			h.put("JENIS_BANGUNAN",
					rs.getString("JENIS_BANGUNAN") == null ? "" : rs
							.getString("JENIS_BANGUNAN"));
			h.put("JENIS_TANAMAN",
					rs.getString("JENIS_TANAMAN") == null ? "" : rs
							.getString("JENIS_TANAMAN"));
			h.put("FLAG_PECAH_SEMPADAN", rs
					.getString("FLAG_PECAH_SEMPADAN") == null ? "" : rs
					.getString("FLAG_PECAH_SEMPADAN"));
			h.put("FLAG_TUKAR_SYARAT",
					rs.getString("FLAG_TUKAR_SYARAT") == null ? "" : rs
							.getString("FLAG_TUKAR_SYARAT"));
			h.put("HARGA_BELI", rs.getString("HARGA_BELI") == null ? ""
					: rs.getDouble("HARGA_BELI"));
			h.put("STATUS_SEMASA",
					rs.getString("STATUS_SEMASA") == null ? "" : rs
							.getString("STATUS_SEMASA"));
			h.put("BEBANAN", rs.getString("BEBANAN") == null ? "" : rs
					.getString("BEBANAN"));
			h.put("KETERANGAN_AGENSI",
					rs.getString("KETERANGAN_AGENSI") == null ? "" : rs
							.getString("KETERANGAN_AGENSI"));
			h.put("KETERANGAN_JURUNILAI", rs
					.getString("KETERANGAN_JURUNILAI") == null ? "" : rs
					.getString("KETERANGAN_JURUNILAI"));
			h.put("TARIKH_PECAH_SEMPADAN", rs
					.getString("TARIKH_PECAH_SEMPADAN") == null ? ""
					: Format.format(rs.getDate("TARIKH_PECAH_SEMPADAN")));
			h.put("TARIKH_TUKAR_SYARAT", rs
					.getString("TARIKH_TUKAR_SYARAT") == null ? "" : Format
					.format(rs.getDate("TARIKH_TUKAR_SYARAT")));
			h.put("TARIKH_AKHIR_TAMPAL", rs
					.getString("TARIKH_AKHIR_TAMPAL") == null ? "" : Format
					.format(rs.getDate("TARIKH_AKHIR_TAMPAL")));
			h.put("TUNTUTAN_TUANTNH",
					rs.getString("TUNTUTAN_TUANTNH") == null ? "" : rs
							.getString("TUNTUTAN_TUANTNH"));
			h.put("TUNTUTAN_PB_BEBANAN", rs
					.getString("TUNTUTAN_PB_BEBANAN") == null ? "" : rs
					.getString("TUNTUTAN_PB_BEBANAN"));
			h.put("TUNTUTAN_PB_TDKDAFTAR", rs
					.getString("TUNTUTAN_PB_TDKDAFTAR") == null ? "" : rs
					.getString("TUNTUTAN_PB_TDKDAFTAR"));
			h.put("TUNTUTAN_PB_LAIN",
					rs.getString("TUNTUTAN_PB_LAIN") == null ? "" : rs
							.getString("TUNTUTAN_PB_LAIN"));
			h.put("ID_BORANGE", rs.getString("ID_BORANGE") == null ? ""
					: rs.getString("ID_BORANGE"));
			h.put("PERINTAH", rs.getString("PERINTAH") == null ? "" : rs
					.getString("PERINTAH"));
			h.put("ID_PEGAWAI_SIASATAN", rs
					.getString("ID_PEGAWAI_SIASATAN") == null ? "" : rs
					.getString("ID_PEGAWAI_SIASATAN"));
			h.put("STATUS_TUNTUTAN",
					rs.getString("STATUS_TUNTUTAN") == null ? "" : rs
							.getString("STATUS_TUNTUTAN"));
			h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
					.getString("ID_BANDAR"));
			h.put("JENIS_WAKTU_SIASATAN", rs
					.getString("JENIS_WAKTU_SIASATAN") == null ? "" : rs
					.getString("JENIS_WAKTU_SIASATAN"));
			h.put("BIL_SIASATAN", rs.getString("BIL_SIASATAN") == null ? "1"
					: rs.getString("BIL_SIASATAN"));
			h.put("ULASAN_SIASATAN",
					rs.getString("ULASAN_SIASATAN") == null ? "" : rs
							.getString("ULASAN_SIASATAN"));
			h.put("HARGA_SEUNIT_SO",
					rs.getString("HARGA_SEUNIT_SO") == null ? "" : rs
							.getDouble("HARGA_SEUNIT_SO"));
			h.put("UNIT_HARGA_SO",
					rs.getString("UNIT_HARGA_SO") == null ? "" : rs
							.getString("UNIT_HARGA_SO"));
			h.put("HARGA_PASARAN_SO",
					rs.getString("HARGA_PASARAN_SO") == null ? "" : rs
							.getDouble("HARGA_PASARAN_SO"));
			h.put("BAYAR_PENJEJASAN",
					rs.getString("BAYAR_PENJEJASAN") == null ? "" : rs
							.getDouble("BAYAR_PENJEJASAN"));
			h.put("BAYAR_PECAHPISAH",
					rs.getString("BAYAR_PECAHPISAH") == null ? "" : rs
							.getDouble("BAYAR_PECAHPISAH"));
			h.put("BAYAR_NAIK_NILAISO",
					rs.getString("BAYAR_NAIK_NILAISO") == null ? "" : rs
							.getDouble("BAYAR_NAIK_NILAISO"));
			h.put("HARGA_SEUNIT_JPPH",
					rs.getString("HARGA_SEUNIT_JPPH") == null ? "" : rs
							.getDouble("HARGA_SEUNIT_JPPH"));
			h.put("UNIT_HARGA", rs.getString("UNIT_HARGA") == null ? ""
					: rs.getString("UNIT_HARGA"));
			h.put("HARGA_PASARAN",
					rs.getString("HARGA_PASARAN") == null ? "" : rs
							.getDouble("HARGA_PASARAN"));
			h.put("AMAUN_PENJEJASAN_JPPH", rs
					.getString("AMAUN_PENJEJASAN_JPPH") == null ? "" : rs
					.getDouble("AMAUN_PENJEJASAN_JPPH"));
			h.put("AMAUN_PECAHPISAH_JPPH", rs
					.getString("AMAUN_PECAHPISAH_JPPH") == null ? "" : rs
					.getDouble("AMAUN_PECAHPISAH_JPPH"));
			h.put("NAIK_NILAI_JPPH",
					rs.getString("NAIK_NILAI_JPPH") == null ? "" : rs
							.getDouble("NAIK_NILAI_JPPH"));
			h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
					.getString("ID_TANAH"));
			
			h.put("KEROSAKAN_TANAH", rs.getString("KEROSAKAN_TANAH") == null ? "" : rs
					.getString("KEROSAKAN_TANAH"));
			h.put("KOS_DITANGGUNG", rs.getString("KOS_DITANGGUNG") == null ? "" : rs
					.getString("KOS_DITANGGUNG"));
			
			maklumat_siasatan_history.addElement(h);

		}
		return maklumat_siasatan_history;
	} finally {
		if (db != null)
			db.close();
	}
}





public void addMaklumatKerosakan(Hashtable data) throws Exception {
	Db db = null;
	String sql = "";

	String txtKerosakanTanah = (String) data.get("txtKerosakanTanah");
	String txtKeteranganKerosakan = (String) data.get("txtKeteranganKerosakan");
	String txtKerosakanTanaman = (String) data.get("txtKerosakanTanaman");
	String txtKerosakanBangunan = (String) data.get("txtKerosakanBangunan");
	String txtKosDitanggung = (String) data.get("txtKosDitanggung");

	
	String id_pembatalan = (String) data.get("id_pembatalan");
	String id_Masuk = (String) data.get("id_Masuk");
	String id_hakmilik = (String) data.get("id_hakmilik");

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.add("KEROSAKAN_TANAH", txtKerosakanTanah);
		r.add("KETERANGAN_KEROSAKAN", txtKeteranganKerosakan);
		r.add("KEROSAKAN_TANAMAN", txtKerosakanTanaman);
		r.add("KEROSAKAN_BANGUNAN", txtKerosakanBangunan);
		r.add("KOS_DITANGGUNG", txtKosDitanggung);
	
		r.add("ID_HAKMILIK", id_hakmilik);
		r.add("ID_MASUK", id_Masuk);
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_MASUK", r.unquote("sysdate"));
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLInsert("tblppttanahumum");
		myLogger.info("SQL INSERT TANAH UMUM :" + sql);
		stmt.executeUpdate(sql);

	} finally {
		if (db != null)
			db.close();
	}

}




public void updateMaklumatKerosakan(Hashtable data) throws Exception {
	Db db = null;
	String sql = "";

	String txtKerosakanTanah = (String) data.get("txtKerosakanTanah");
	String txtKeteranganKerosakan = (String) data.get("txtKeteranganKerosakan");
	String txtKerosakanTanaman = (String) data.get("txtKerosakanTanaman");
	String txtKerosakanBangunan = (String) data.get("txtKerosakanBangunan");
	String txtKosDitanggung = (String) data.get("txtKosDitanggung");
	String id_tanahumum = (String) data.get("id_tanahumum");
	
	
	String id_pembatalan = (String) data.get("id_pembatalan");
	String id_Masuk = (String) data.get("id_Masuk");
	String id_hakmilik = (String) data.get("id_hakmilik");

	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		r.update("ID_TANAHUMUM", id_tanahumum);
		
		r.add("KEROSAKAN_TANAH", txtKerosakanTanah);
		r.add("KETERANGAN_KEROSAKAN", txtKeteranganKerosakan);
		r.add("KEROSAKAN_TANAMAN", txtKerosakanTanaman);
		r.add("KEROSAKAN_BANGUNAN", txtKerosakanBangunan);
		r.add("KOS_DITANGGUNG", txtKosDitanggung);
		
	
		r.add("ID_HAKMILIK", id_hakmilik);
		
		r.add("ID_KEMASKINI", id_Masuk);
		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
		sql = r.getSQLUpdate("tblppttanahumum");
		myLogger.info("SQL INSERT TANAH UMUM :" + sql);
		stmt.executeUpdate(sql);

	} finally {
		if (db != null)
			db.close();
	}

}


Vector senarai_laporan_tanah = null;

public Vector senarai_laporan_tanah(String id_hakmilik) throws Exception {
	senarai_laporan_tanah = new Vector();
	Db db = null;
	senarai_laporan_tanah.clear();
	String sql = "SELECT T.ID_TANAH,T.KEADAAN_TANAH,H.NO_SYIT,T.TANAMAN,T.LOKASI_TANAH,T.RUPABUMI FROM TBLPPTTANAH T,TBLPPTHAKMILIK H "
                 +" WHERE T.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_HAKMILIK = '"+id_hakmilik+"'";

	try {
		db = new Db();
		Statement stmt = db.getStatement();

	//	sql = "";

		myLogger.info("tanah terperinci:" + sql);

		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;

		while (rs.next()) {

			h = new Hashtable();
			h.put("ID_TANAH", rs.getString("ID_TANAH") == null ? "" : rs
					.getString("ID_TANAH"));
			h.put("KEADAAN_TANAH", rs.getString("KEADAAN_TANAH") == null ? "" : rs
					.getString("KEADAAN_TANAH"));
			h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs
					.getString("NO_SYIT"));
			h.put("TANAMAN", rs.getString("TANAMAN") == null ? "" : rs
					.getString("TANAMAN"));
			h.put("RUPABUMI", rs.getString("RUPABUMI") == null ? "" : rs
					.getString("RUPABUMI"));
			
			
			
			
			
		
			senarai_laporan_tanah.addElement(h);
		}
		return senarai_laporan_tanah;
	} finally {
		if (db != null)
			db.close();
	}
}

Vector list_PB = null;
public Vector list_PB(String id_hakmilik,String CariPB) throws Exception {
	list_PB = new Vector();
	list_PB.clear();
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql = " SELECT BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH,HMPB.FLAG_BORANGC,HMPB.FLAG_BORANGE,HMPB.FLAG_BORANGG,HMPB.FLAG_BORANGK,HMPB.KETERANGAN_JENIS_PB,HMPB.CATATAN,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK," +
			//	"S.ID_SIASATAN," +
				"PBALIK.ID_PERMOHONAN,"
				+
				// "H.FLAG_HADIR," +
				"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
				+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
				+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
				+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
				+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK," +
					//	"TBLPPTSIASATAN S," +
						"TBLPPTHAKMILIKPB HMPB, "
				+
				
				"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
				+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTBAHAGIANPB BPB  "
				+ " WHERE " 
				
			//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
				+" HMPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) AND  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
				
			
			
			//	" AND  S.ID_HAKMILIK(+) = HM.ID_HAKMILIK "
				+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
				+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
				+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
				+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
				+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
				+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
				+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
				+ " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42','27') "
				+

				" AND HM.ID_HAKMILIK = '"
				+ id_hakmilik
				+ "' ";
				
				if(!CariPB.equals("") && !CariPB.equals(null))
				{
					
					if (!CariPB.trim().equals("")) {
						sql = sql + " AND (UPPER(PB.NO_PB) LIKE '%"
								+ CariPB.toUpperCase().trim() + "%'";					
						
						sql = sql + " OR UPPER(PB.NAMA_PB) LIKE '%"
						+ CariPB.toUpperCase().trim() + "%')";
						
						
					}
					
				}
				
				
				
			sql += " ORDER BY ID_BAHAGIANPB,NAMA_PB ASC" + "";
			
			
			myLogger.info("LIST PB BARU :"+sql.toUpperCase());

		ResultSet rs = stmt.executeQuery(sql);

		int bil = 0;
		String temp = "";
		while (rs.next()) {
			bil = bil + 1;

			Hashtable h = new Hashtable();
			h.put("BIL", bil);
			temp = bil + "k";
			h.put("BILTEMP", temp);
			
			h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? ""
					: rs.getString("ID_BAHAGIANPB"));					
			h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? ""
					: rs.getString("SYER_ATAS"));
			h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
					: rs.getString("SYER_BAWAH"));
			
			h.put("FLAG_BORANGC", rs.getString("FLAG_BORANGC") == null ? ""
					: rs.getString("FLAG_BORANGC"));
			h.put("FLAG_BORANGE", rs.getString("FLAG_BORANGE") == null ? ""
					: rs.getString("FLAG_BORANGE"));
			h.put("FLAG_BORANGG", rs.getString("FLAG_BORANGG") == null ? ""
					: rs.getString("FLAG_BORANGG"));
			h.put("FLAG_BORANGK", rs.getString("FLAG_BORANGK") == null ? ""
					: rs.getString("FLAG_BORANGK"));
			
			h.put("CATATAN", rs.getString("CATATAN") == null ? ""
					: rs.getString("CATATAN"));
			h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_JENIS_PB") == null ? ""
					: rs.getString("KETERANGAN_JENIS_PB"));
			h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
					: rs.getString("NO_HANDPHONE"));
			h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
					: rs.getString("NO_TEL_RUMAH"));
			h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
					: rs.getString("ID_HAKMILIK"));
	//		h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
		//			: rs.getString("ID_SIASATAN"));
			h.put("ID_PENARIKANBALIK",
					rs.getString("ID_PERMOHONAN") == null ? "" : rs
							.getString("ID_PERMOHONAN"));
			// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
			// rs.getString("FLAG_HADIR"));
			h.put("ID_PIHAKBERKEPENTINGAN", rs
					.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
					.getString("ID_PIHAKBERKEPENTINGAN"));
			h.put("ID_HAKMILIKPB",
					rs.getString("ID_HAKMILIKPB") == null ? "" : rs
							.getString("ID_HAKMILIKPB"));
			h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
					.getString("NAMA_PB").toUpperCase());
			h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
					.getString("NO_PB").toUpperCase());
			h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
					.getString("NAMA_KP").toUpperCase());
			h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
					: rs.getString("ID_JENISPB"));
			// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
			// rs.getString("KETERANGAN"));
			h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
					.getString("JENISPB").toUpperCase());
			h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
					.getString("ALAMAT1").toUpperCase());
			h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
					.getString("ALAMAT2").toUpperCase());
			h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
					.getString("ALAMAT3").toUpperCase());
			h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
					.getString("POSKOD"));
			h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
					.getString("ID_NEGERI"));
			h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
					.getString("ID_BANDAR"));
			h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
					: rs.getString("NAMA_NEGERI").toUpperCase());
			h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
					: rs.getString("NAMA_BANDAR").toUpperCase());
			h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
					: rs.getString("ID_JENISNOPB"));
			h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
					.getString("JENISNOPB").toUpperCase());
			h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
					.getString("NO_AKAUN").toUpperCase());
			h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
					.getString("ID_BANK"));
			h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
					.getString("NAMA_BANK").toUpperCase());

			list_PB.addElement(h);
		}
		return list_PB;
	} finally {
		if (db != null)
			db.close();
	}
}



Vector senarai_pihak_penting_HAKMILIK = null;	
public Vector senarai_pihak_penting_bahagian(String id_hakmilik)throws Exception{
	senarai_pihak_penting_HAKMILIK = new Vector();
	Db db = null;
	senarai_pihak_penting_HAKMILIK.clear();
	String sql = "";
	
	try{
		db = new Db();
		Statement stmt = db.getStatement();
		sql = "SELECT BH.SYER_ATAS,BH.SYER_BAWAH,B1.ID_HAKMILIKPB,PB1.NAMA_PB,B1.ID_JENISPB,B1.ID_BAHAGIANPB,H1.ID_HAKMILIK " +
				"FROM TBLPPTPIHAKBERKEPENTINGAN PB1,TBLPPTHAKMILIKPB B1,TBLPPTHAKMILIK H1," +
				"TBLPPTBAHAGIANPB BH "+
		" WHERE B1.ID_PIHAKBERKEPENTINGAN = PB1.ID_PIHAKBERKEPENTINGAN "+
		" AND B1.ID_HAKMILIK = H1.ID_HAKMILIK AND B1.ID_BAHAGIANPB = BH.ID_BAHAGIANPB "+
		" AND H1.ID_HAKMILIK = '"+id_hakmilik+"' "+
		" ORDER BY NAMA_PB ASC ";
		
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		int bil = 0;
		while (rs.next()) {
			bil = bil + 1;
			h = new Hashtable();
			h.put("BIL",bil);
			h.put("SYER_ATAS",rs.getString("SYER_ATAS") == null ? "" : rs.getString("SYER_ATAS"));
			h.put("SYER_BAWAH",rs.getString("SYER_BAWAH") == null ? "" : rs.getString("SYER_BAWAH"));				
			h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ? "" : rs.getString("ID_HAKMILIKPB"));
			h.put("ID_BAHAGIANPB",rs.getString("ID_BAHAGIANPB") == null ? "" : rs.getString("ID_BAHAGIANPB"));
			h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
			h.put("NAMA_PB",rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB").toUpperCase());
			senarai_pihak_penting_HAKMILIK.addElement(h);				
		}
		return senarai_pihak_penting_HAKMILIK;
	}finally{
		if (db != null)
			db.close();
	}
}





public void update_status_hakmilik(String id_hakmilik, String id_permohonan,
		String id_Masuk,String id_suburusan,String flag,String id_fail) throws Exception {
	Db db = null;
	
	String sql7 = "";
	String sql71 = "";
	String sql6 = "";
	try {
		db = new Db();
	
		Statement stmtX = db.getStatement();
		SQLRenderer r6 = new SQLRenderer();
		r6.update("ID_HAKMILIK",id_hakmilik);
		r6.update("AKTIF",1);
		r6.add("AKTIF",0);		
		r6.add("ID_KEMASKINI",id_Masuk);
		r6.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
		sql6 = r6.getSQLUpdate("TBLRUJSUBURUSANSTATUSHAKMILIK");
		myLogger.info("UPDATE STATUS HAKMILK"+sql6);
		stmtX.executeUpdate(sql6);	    
	   
		if(!flag.equals("hapus"))
		{
		Statement stmtF = db.getStatement();
		SQLRenderer r5 = new SQLRenderer();
		r5.add("ID_HAKMILIK",id_hakmilik);
		r5.add("ID_PERMOHONAN",id_permohonan);
		r5.add("ID_FAIL",id_fail);
		r5.add("ID_SUBURUSANSTATUS",id_suburusan);
		r5.add("AKTIF",1);
		r5.add("ID_MASUK",id_Masuk);
		r5.add("TARIKH_MASUK",r6.unquote("sysdate"));
		r5.add("ID_KEMASKINI",id_Masuk);
		r5.add("TARIKH_KEMASKINI",r6.unquote("sysdate"));
		sql7 = r5.getSQLInsert("TBLRUJSUBURUSANSTATUSHAKMILIK");
		myLogger.info("ADD STATUS HAKMILK"+sql7);
		stmtF.executeUpdate(sql7);
		}
		
		
		if(flag.equals("hapus"))
		{
		Statement stmtF1 = db.getStatement();
		sql71 = "DELETE FROM TBLRUJSUBURUSANSTATUSHAKMILIK SHM WHERE SHM.ID_HAKMILIK = '"+id_hakmilik+"'  AND ID_SUBURUSANSTATUS IN (16102698,16102699,16102700,16102701,16102702,16102703,16102704,16102705,16102706,16102707,16102708,16102709)";
		myLogger.info("DELETE STATUS HAKMILK"+sql71);
		stmtF1.executeUpdate(sql71);
		}

	} finally {
		if (db != null)
			db.close();
	}

}


public void delete_siasatanaward(String id_award) throws Exception {
	Db db = null;

	String sql1 = "";
	String sql2 = "";

	try {
		db = new Db();
		Statement stmt1 = db.getStatement();
		sql1 = " DELETE FROM TBLPPTSUBAWARD WHERE ID_AWARD = "
				+ id_award;
		myLogger.info("SQL DELETE TBLPPTSUBAWARD BY PB:"
				+ sql1);
		stmt1.executeUpdate(sql1);
		
		
		
		Statement stmt2 = db.getStatement();
		sql2 = " DELETE FROM TBLPPTAWARD WHERE ID_AWARD = "
				+ id_award;
		
		stmt2.executeUpdate(sql2);

	} finally {
		if (db != null)
			db.close();
	}

}


public void deleteFail(String id_fail, HttpSession session) throws Exception {
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();	
		
        sql = " DELETE FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE  ID_HAKMILIK IN ( "+ 
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+ 
        " SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+ 
        " SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";   
        myLogger.info("SQL LAALAL"+sql.toUpperCase());
        stmt.executeUpdate(sql);
        
        
       
        

        sql = " DELETE FROM TBLPPTPENARIKANHAKMILIK WHERE  ID_HAKMILIK IN ( "+
        " SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
        " SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
        " SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
        stmt.executeUpdate(sql);

        sql = " DELETE  FROM TBLPPTPENARIKANBALIK  WHERE  ID_PERMOHONAN IN ( "+
        " SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
        " SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
        stmt.executeUpdate(sql);

        sql = " DELETE  FROM TBLPPTPEMBATALANHAKMILIK WHERE  ID_HAKMILIK IN ( "+
        " SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
        " SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
        " SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
        stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPEMBATALAN WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBUKTIPENYAMPAIANPB WHERE ID_BUKTIPENYAMPAIAN IN ( "+
		" SELECT ID_BUKTIPENYAMPAIAN FROM TBLPPTBUKTIPENYAMPAIAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGQ WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOPELAN WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM  TBLPPTBORANGM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGI WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBAHAGIANPB WHERE ID_BAHAGIANPB IN ( "+
		" SELECT ID_BAHAGIANPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGO WHERE ID_BANTAHAN IN ( "+
		" SELECT  ID_BANTAHAN  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTNOPELANPU WHERE  ID_PERMINTAANUKUR IN ( "+
		" SELECT ID_PERMINTAANUKUR FROM TBLPPTPERMINTAANUKUR WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTPERMINTAANUKUR WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGL WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTENDOSANBORANGK WHERE ID_BORANGK IN ( "+
		" SELECT ID_BORANGK FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTHAKMILIKBORANGK WHERE ID_BORANGK IN ( "+
		" SELECT ID_BORANGK  FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTAFIDAVIT  WHERE ID_AWARD IN ( "+
		" SELECT ID_AWARD FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBAYARAN WHERE ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTERIMABAYARAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGH WHERE ID_BORANGG IN ( "+
		" SELECT ID_BORANGG FROM TBLPPTBORANGG WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTBORANGJ WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGG WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTSUBAWARD WHERE ID_AWARD IN ( "+
		" SELECT ID_AWARD FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);
		
		

		sql = " DELETE  FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTKEHADIRAN  WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBUKTIPENYAMPAIAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGF WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGE WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTTANAH WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBANGUNANPB WHERE ID_BANGUNAN IN ( "+
		" SELECT ID_BANGUNAN FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE   FROM TBLPPTULASANTEKNIKAL WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);



		sql = " DELETE   FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTANDAKAWASAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBEBANAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTPIHAKBERKEPENTINGAN WHERE ID_PIHAKBERKEPENTINGAN IN ( "+
		" SELECT ID_PIHAKBERKEPENTINGAN FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTWARTA WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTMMKKEPUTUSAN WHERE  ID_MMK IN ( "+
		" SELECT ID_MMK FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSUBMMK  WHERE ID_MMK IN ( "+
		" SELECT ID_MMK FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTANAHUMUM  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTUGAS  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSENARAISEMAK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE FROM TBLPPTDOKUMEN  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTHAKMILIKASAL WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLRUJSUBURUSANSTATUSFAILPPT  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))";
		stmt.executeUpdate(sql);


		sql = " DELETE FROM TBLRUJSUBURUSANSTATUSHAKMILIK WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE FROM TBLPPTPENARIKANHAKMILIK WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPENARIKANBALIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPEMBATALANHAKMILIK WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPEMBATALAN WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBUKTIPENYAMPAIANPB WHERE ID_BUKTIPENYAMPAIAN IN ( "+
		" SELECT ID_BUKTIPENYAMPAIAN FROM TBLPPTBUKTIPENYAMPAIAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGQ WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOPELAN WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM  TBLPPTBORANGM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGI WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTBAHAGIANPB WHERE ID_BAHAGIANPB IN ( "+
		" SELECT ID_BAHAGIANPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGO WHERE ID_BANTAHAN IN ( "+
		" SELECT  ID_BANTAHAN  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM  TBLPPTBANTAHAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOPELANPU WHERE  ID_PERMINTAANUKUR IN ( "+
		" SELECT ID_PERMINTAANUKUR FROM TBLPPTPERMINTAANUKUR WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTPERMINTAANUKUR WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGL WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTENDOSANBORANGK WHERE ID_BORANGK IN ( "+
		" SELECT ID_BORANGK FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTHAKMILIKBORANGK WHERE ID_BORANGK IN ( "+
		" SELECT ID_BORANGK  FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTAFIDAVIT  WHERE ID_AWARD IN ( "+
		" SELECT ID_AWARD FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBAYARAN WHERE ID_HAKMILIKPB IN ( "+
		" SELECT ID_HAKMILIKPB FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTERIMABAYARAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGH WHERE ID_BORANGG IN ( "+
		" SELECT ID_BORANGG FROM TBLPPTBORANGG WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTBORANGJ WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBORANGG WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTSUBAWARD WHERE ID_AWARD IN ( "+
		" SELECT ID_AWARD FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTAWARD WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSUBSIASATAN WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTKEHADIRAN  WHERE ID_SIASATAN IN ( "+
		" SELECT ID_SIASATAN FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSIASATAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBUKTIPENYAMPAIAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBORANGF WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ("+id_fail+"))))";
		stmt.executeUpdate(sql);
		
		sql = "  DELETE  FROM TBLPPTBORANGEHAKMILIK WHERE ID_HAKMILIK IN ( "+
				" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
				" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
				" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ("+id_fail+"))))";

		sql = " DELETE  FROM TBLPPTBORANGE WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTTANAH WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBANGUNANPB WHERE ID_BANGUNAN IN ( "+
		" SELECT ID_BANGUNAN FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTBANGUNAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE   FROM TBLPPTULASANTEKNIKAL WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);



		sql = " DELETE   FROM TBLPPTULASANTEKNIKAL WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTTANDAKAWASAN WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTANDAKAWASAN WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTBEBANAN WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTPIHAKBERKEPENTINGAN WHERE ID_PIHAKBERKEPENTINGAN IN ( "+
		" SELECT ID_PIHAKBERKEPENTINGAN FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTHAKMILIKPB WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);
		
		sql = " DELETE  FROM TBLPPTNOTISAWAMHAKMILIK WHERE ID_HAKMILIK IN ( "+
				" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
				" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
				" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ("+id_fail+")))) ";


		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTNOTISAWAM WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);



		sql = " DELETE  FROM TBLPPTWARTA WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTMMKKEPUTUSAN WHERE  ID_MMK IN ( "+
		" SELECT ID_MMK FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSUBMMK  WHERE ID_MMK IN ( "+
		" SELECT ID_MMK FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTMMK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTANAHUMUM  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTTUGAS  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTSENARAISEMAK WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE FROM TBLPPTDOKUMEN  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTHAKMILIKASAL WHERE  ID_HAKMILIK IN ( "+
		" SELECT ID_HAKMILIK FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))))";
		stmt.executeUpdate(sql);


		sql = " DELETE  FROM TBLPPTHAKMILIK  WHERE  ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLRUJSUBURUSANSTATUSFAILPPT  WHERE ID_PERMOHONAN IN ( "+
		" SELECT ID_PERMOHONAN FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+")))";
		stmt.executeUpdate(sql);

		sql = " DELETE  FROM TBLPPTPERMOHONAN WHERE ID_FAIL IN ( "+
		" SELECT ID_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( "+id_fail+"))";
		stmt.executeUpdate(sql);

		sql = " DELETE from tblpfdfail where id_fail in ("+id_fail+")";
		stmt.executeUpdate(sql);

		myLogger.info("SQL DELETE :"+sql.toUpperCase());		
		
		stmt.executeUpdate(sql);
		
    	AuditTrail.logActivity(null,session,"DEL","FAIL KJP INI [" + id_fail + "] DIHAPUSKAN");
		
	} finally {
		if (db != null)
			db.close();
	}
}

	private static Vector dataPejabatJKPTG = null;

	public Vector getDataPejabatJKPTG(){
		return dataPejabatJKPTG;
	}

	@SuppressWarnings("unchecked")
	public void setDataPejabatJKPTG(String id_negeri) throws Exception{
		
		dataPejabatJKPTG = new Vector();
		
		Db db = null;
		dataPejabatJKPTG.clear();
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = " SELECT DISTINCT ID_PEJABATJKPTG ";
				sql += " FROM TBLRUJPEJABATJKPTG ";
				sql += " WHERE ID_JENISPEJABAT IN ('22','21') ";
				sql += " AND ID_SEKSYEN = '1' ";
				sql += " AND ID_NEGERI = '"+id_negeri+"' ";
			
				
				ResultSet rs = stmt.executeQuery(sql);	
				myLogger.info("SQL DATA PEJABAT : "+sql);
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG")==null?"":rs.getString("ID_PEJABATJKPTG"));
					dataPejabatJKPTG.addElement(h);					
				}
	
		}finally {
			if(db != null) db.close();
		}
		
	}//close setDataPejabatJKPTG
	
	private static Vector dataKementerian = null;

	public Vector getDataKementerian(){
		return dataKementerian;
	}

	@SuppressWarnings("unchecked")
	public void setDataKementerian() throws Exception{
		
		Vector dataKementerian = new Vector();
		
		Db db = null;
		String sql = "";
		
		try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql  = " SELECT DISTINCT ID_KEMENTERIAN, NAMA_KEMENTERIAN ";
				sql += " FROM TBLRUJKEMENTERIAN ";
				
				System.out.println("************ SQL KEMENTERIAN"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				
				while (rs.next()){
					h = new Hashtable();
					h.put("ID_KEMENTERIAN", rs.getString("ID_KEMENTERIAN")==null?"":rs.getString("ID_KEMENTERIAN"));
					h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN")==null?"":rs.getString("NAMA_KEMENTERIAN"));
					dataKementerian.addElement(h);					
				}
				
		}finally {
			if(db != null) db.close();
		}
		
	}//close setDataKementerian

	public void deleteFailPenarikan(String id_penarikanbalik) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();	
	        
			sql = "UPDATE TBLPPTHAKMILIK ";
			sql += " SET FLAG_PENARIKAN_KESELURUHAN = '' ";
			sql += " WHERE ID_HAKMILIK IN (SELECT DISTINCT ID_HAKMILIK FROM TBLPPTPENARIKANHAKMILIK A WHERE ID_PENARIKANBALIK = '"+id_penarikanbalik+"' )";
			stmt.executeUpdate(sql);
			
	        sql = " DELETE FROM TBLPPTPENARIKANHAKMILIK WHERE ID_PENARIKANBALIK = '"+id_penarikanbalik+"'";
	        stmt.executeUpdate(sql);

	        sql = " DELETE  FROM TBLPPTPENARIKANBALIK  WHERE ID_PENARIKANBALIK = '"+id_penarikanbalik+"'";  
	        stmt.executeUpdate(sql);
					
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteFailPembatalan(String id_pembatalan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();	
	        
			sql = "UPDATE TBLPPTHAKMILIK ";
			sql += " SET FLAG_PEMBATALAN_KESELURUHAN = '' ";
			sql += " WHERE ID_HAKMILIK IN (SELECT DISTINCT ID_HAKMILIK FROM TBLPPTPEMBATALAN A WHERE ID_PEMBATALAN = '"+id_pembatalan+"' )";
			stmt.executeUpdate(sql);
			
	        sql = " DELETE FROM TBLPPTPEMBATALANHAKMILIK WHERE ID_PEMBATALAN = '"+id_pembatalan+"'";
	        stmt.executeUpdate(sql);

	        sql = " DELETE  FROM TBLPPTPEMBATALAN  WHERE ID_PEMBATALAN = '"+id_pembatalan+"'";  
	        stmt.executeUpdate(sql);
					
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//09022012
	@SuppressWarnings("unchecked")
	public static void updateFlag(Hashtable data,String jenisPermohonan) throws Exception {
		
	    Db db = null;
	    String sql = "";
	   
	    try{
	      
	    	 	db = new Db();
	    	 	Statement stmt = db.getStatement();
	    	 	
	    	 	String id_user = (String)data.get("id_user");
	    		String id_pembatalan = (String)data.get("id_pembatalan"); 
	    		String flag_semakan_online = (String)data.get("flag_semakan_online"); 
	    		
	    		SQLRenderer r = new SQLRenderer();
	    		if(jenisPermohonan.equals("batal")){
	    			r.update("id_pembatalan", id_pembatalan);
	    		}else{
	    			r.update("id_penarikanbalik", id_pembatalan);
	    		}
	    		
	    		r.add("flag_semakan_online",flag_semakan_online);
	    		r.add("id_kemaskini",id_user);
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		if(jenisPermohonan.equals("batal")){
	    			sql = r.getSQLUpdate("tblpptpembatalan");
	    		}else{
	    			sql = r.getSQLUpdate("tblpptpenarikanbalik");
	    		}	    		
	    		stmt.executeUpdate(sql);

	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }//close updateFlag
	
	Vector senarai_pembayaran_online = null;
	
	public Vector senarai_pembayaran_online(String negeriuser,String no_fail,
			String no_jkptg_negeri, String id_kementerian, String id_urusan,
			String id_status, String jenis_permohon,String role,String user_negeri) throws Exception {
		senarai_pembayaran_online = new Vector();
		Db db = null;
		senarai_pembayaran_online.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.ID_PERMOHONAN,P.TARIKH_PERMOHONAN,"
					+ " F.NO_FAIL, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTG, "
					+ " K.NAMA_KEMENTERIAN,D.NAMA_DAERAH,S.KETERANGAN,U.NAMA_SUBURUSAN  FROM TBLPPTPERMOHONAN P, "
					+ " TBLRUJSUBURUSAN U,TBLPFDFAIL F,TBLRUJKEMENTERIAN K,TBLRUJDAERAH D,"
					+ " TBLRUJSTATUS S "
					+ " WHERE  P.ID_FAIL = F.ID_FAIL(+) "
					+ " AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN(+) "
					+ " AND P.ID_DAERAH = D.ID_DAERAH(+) "
					+ " AND (SELECT COUNT(*) FROM TBLPPTWARTA WHERE ID_PERMOHONAN = P.ID_PERMOHONAN ) > '0' "
					+ " AND F.ID_SUBURUSAN = '52' "
					+" "
					+" AND (SELECT COUNT(*) FROM TBLPPTHAKMILIK HMK WHERE HMK.ID_PERMOHONAN = P.ID_PERMOHONAN " +
							"AND HMK.ID_HAKMILIK  NOT IN "
					+" (SELECT DISTINCT H.ID_HAKMILIK FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKBORANGK HK,TBLPPTBORANGK BK "
					+" WHERE HK.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN AND HK.ID_BORANGK = BK.ID_BORANGK ) ) > 0  "
			/*		+ " AND P.FLAG_JENISPERMOHONAN = '"
					+ jenis_permohon
					+ "' "		*/
					+ " AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "
					+ " AND F.ID_KEMENTERIAN = '" + id_kementerian +"' " // Penambahan bagi paparan mengikut kementerian
			/*		+ " AND NVL(S.ID_STATUS,0) IN ('31','35','38','43','46','48','52','54','58','59',"
					+ " '62','66','68','72','74','76','181')"*/
					+ " AND P.ID_STATUS = S.ID_STATUS " +
							"";
			
			if(!negeriuser.equals("16") && !negeriuser.isEmpty()){
    			if(negeriuser.equals("14")){
    				sql += "AND f.id_negeri in (14,15,16) ";
    			}else{
    				sql += "AND f.id_negeri ='"+negeriuser+"'";
    			}		
    		}

			// kena filter by status (sudah diwartakan)

			if (no_fail != "") {
				if (!no_fail.trim().equals("")) {
					sql = sql + " AND (UPPER(F.NO_FAIL) LIKE '%"
							+ no_fail.toUpperCase().trim() + "%'";					
					
					sql = sql + " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
					+ no_fail.toUpperCase().trim() + "%'";
					
					sql = sql + " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"
					+ no_fail.toUpperCase().trim() + "%')";
				}
			}
			if (no_jkptg_negeri != "") {
				if (!no_jkptg_negeri.trim().equals("")) {
					sql = sql + " AND UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
							+ no_jkptg_negeri.trim() + "%'";
				}
			}
			if (id_urusan != "") {
				if (!id_urusan.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_SUBURUSAN) LIKE '"
							+ id_urusan.toUpperCase() + "'";
				}
			}
			if (id_kementerian != "") {
				if (!id_kementerian.trim().equals("")) {
					sql = sql + " AND UPPER(F.ID_KEMENTERIAN) LIKE '"
							+ id_kementerian.toUpperCase() + "'";
				}
			}
			if (id_status != "") {
				if (!id_status.trim().equals("")) {
					sql = sql + " AND UPPER(P.ID_STATUS) LIKE '"
							+ id_status.toUpperCase() + "'";
				}
			}

			sql += " ORDER BY P.TARIKH_KEMASKINI DESC";

			myLogger.info("SQL PENARIKAN CARI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("ID_PEMBATALAN",rs.getString("ID_PENARIKANBALIK") ==
				// null ? "" : rs.getString("ID_PENARIKANBALIK"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : Format
								.format(rs.getDate("TARIKH_PERMOHONAN")));
				// h.put("NO_PEMBATALAN",rs.getString("NO_PENARIKANBALIK") ==
				// null ? "" : rs.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "TIADA"
						: rs.getString("NO_FAIL").toUpperCase());
				// h.put("TARIKH_PEMBATALAN",
				// rs.getString("TARIKH_PENARIKAN_BALIK")==null?"":Format.format(rs.getDate("TARIKH_PENARIKAN_BALIK")));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("KETERANGAN", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN").toUpperCase());
				h.put("URUSAN", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN").toUpperCase());
				senarai_pembayaran_online.addElement(h);
			}
			return senarai_pembayaran_online;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
}

