
package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;


/**
 * @author Mohd Faizal
 *
 */
public class FrmSenaraiHutangData {

    private static Logger myLog = Logger.getLogger(FrmSenaraiHutangData.class);	
	protected Db db;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

	public Vector<Hashtable<String,String>> getSenaraiPenghutang(String nama, String noPengenalanBaru, String USER_ID_SYSTEM) {
		String sql = "";
		Vector<Hashtable<String,String>> listPenghutang = null;
		Hashtable<String,String> h;
		System.out.println("getSenaraiPenghutang");
		System.out.println("nama = "+nama);
		try {
			listPenghutang = new Vector<Hashtable<String,String>>();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
//			
			sql = getSqlPenghutangAgensi();
			// nama
			if (nama != null) {
				if (!nama.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA) LIKE '%' ||'"
							+ nama.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// noPengenalanBaru
			if (noPengenalanBaru != null) {
				if (!noPengenalanBaru.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PENGENALAN_BARU) LIKE '%' ||'"
							+ noPengenalanBaru.trim().toUpperCase() + "'|| '%'";
				}
			}
			sql = sql + " AND A.ID_PEJABAT = (SELECT D.ID_PEJABAT";
			sql = sql + " FROM USERS_INTEGRASI D";
			sql = sql + " WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			sql = sql + " ORDER BY A.NAMA ASC";	
			System.out.println("******sqlgetSenaraiPenghutang = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				h = new Hashtable<String,String>();
				h.put("idHutang", rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalanBaru", rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));

				
	
				listPenghutang.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listPenghutang;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiPenghutangPPK(String nama, String noPengenalanBaru, String USER_ID_SYSTEM) {
		String sql = "";
		Vector<Hashtable<String,String>> listPenghutang = null;
		Hashtable<String,String> h;
		System.out.println("getSenaraiPenghutang");
		System.out.println("nama = "+nama);
		try {
			listPenghutang = new Vector<Hashtable<String,String>>();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
//			
			sql = getSqlPenghutang();
			// nama
			if (nama != null) {
				if (!nama.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA) LIKE '%' ||'"
							+ nama.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// noPengenalanBaru
			if (noPengenalanBaru != null) {
				if (!noPengenalanBaru.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PENGENALAN_BARU) LIKE '%' ||'"
							+ noPengenalanBaru.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			sql = sql + " ORDER BY A.NAMA ASC";	
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				h = new Hashtable<String,String>();
				h.put("idHutang", rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalanBaru", rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				
	
				listPenghutang.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listPenghutang;
	}
	
	public Vector getLaporaneHutangAgensi(String bulan1, String bulan2, String tahun1, String tahun2, String USER_ID_SYSTEM) {
		String sql = "";
		Vector listBILANGAN = null;
		Hashtable h;
		
		try {
			listBILANGAN = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			if ((bulan1 == null) && (bulan2 == null) && (tahun1 != null) && (tahun2 == null))
			{
				sql = 	"SELECT COUNT(B.ID_HUTANG) AS BIL, SUM(B.NILAI_HUTANG) AS NILAI_HUTANG, SUM(B.BAKI_HUTANG) AS BAKI_HUTANG, "+
						" (COUNT(DISTINCT CASE "+
						" WHEN B.TARIKH_SELESAI_HUTANG IS NOT NULL "+
						" THEN B.TARIKH_SELESAI_HUTANG END)) AS SELESAI_HUTANG "+
						" FROM TBLPPKHUTANG A, TBLPPKSENARAIHUTANG B "+
						" WHERE A.ID_HUTANG = B.ID_HUTANG "+
						" AND TO_CHAR(B.TARIKH_PERJANJIAN, 'yyyy') = '"+tahun1+"' "+
						" AND A.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
				 		" FROM USERS_INTEGRASI D "+
				 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			}
			if ((bulan1 != null) && (tahun1 != null) && (bulan2 == null) && (tahun2 == null))
			{
			sql = 	"SELECT COUNT(B.ID_HUTANG) AS BIL, SUM(B.NILAI_HUTANG) AS NILAI_HUTANG, SUM(B.BAKI_HUTANG) AS BAKI_HUTANG, "+
					" (COUNT(DISTINCT CASE "+
					" WHEN B.TARIKH_SELESAI_HUTANG IS NOT NULL "+
					" THEN B.TARIKH_SELESAI_HUTANG END)) AS SELESAI_HUTANG "+
					" FROM TBLPPKHUTANG A, TBLPPKSENARAIHUTANG B "+
					" WHERE A.ID_HUTANG = B.ID_HUTANG "+
					" AND TO_CHAR(B.TARIKH_PERJANJIAN, 'mm/yyyy') = '"+bulan1+"/"+tahun1+"' "+
					" AND A.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
			 		" FROM USERS_INTEGRASI D "+
			 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			}
			if ((bulan1 != null) && (bulan2 != null) && (tahun1 != null) && (tahun2 != null))
			{
			sql = 	"SELECT COUNT(B.ID_HUTANG) AS BIL, SUM(B.NILAI_HUTANG) AS NILAI_HUTANG, SUM(B.BAKI_HUTANG) AS BAKI_HUTANG, "+
					" (COUNT(DISTINCT CASE "+
					" WHEN B.TARIKH_SELESAI_HUTANG IS NOT NULL "+
					" THEN B.TARIKH_SELESAI_HUTANG END)) AS SELESAI_HUTANG "+
					" FROM TBLPPKHUTANG A, TBLPPKSENARAIHUTANG B "+
					" WHERE A.ID_HUTANG = B.ID_HUTANG "+
					" AND B.TARIKH_PERJANJIAN between TO_DATE('"+bulan1+"/"+tahun1+"', 'mm/yyyy') and TO_DATE('"+bulan2+"/"+tahun2+"','mm/yyyy') "+
					" AND A.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
			 		" FROM USERS_INTEGRASI D "+
			 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			}
			System.out.println("getLaporaneHutangAgensi = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				h = new Hashtable();
				h.put("BIL", rs.getString("BIL") == null ? "" : rs.getString("BIL"));
				
				if(rs.getString("NILAI_HUTANG") != null && rs.getString("NILAI_HUTANG") != "" ){
			    	String jb = rs.getString("NILAI_HUTANG");
			    	DecimalFormat decim = new DecimalFormat("#.00");
			    	double NILAI_HUTANG2 = Double.parseDouble(jb);
			    	String NILAI_HUTANG = decim.format(NILAI_HUTANG2);
			    	h.put("NILAI_HUTANG", NILAI_HUTANG);
			    }else{
			    	h.put("NILAI_HUTANG", "");
			    }
				
				if(rs.getString("BAKI_HUTANG") != null && rs.getString("BAKI_HUTANG") != "" ){
			    	String jb = rs.getString("BAKI_HUTANG");
			    	DecimalFormat decim = new DecimalFormat("#.00");
			    	double BAKI_HUTANG2 = Double.parseDouble(jb);
			    	String BAKI_HUTANG = decim.format(BAKI_HUTANG2);
			    	h.put("BAKI_HUTANG", BAKI_HUTANG);
			    }else{
			    	h.put("BAKI_HUTANG", "");
			    }
				
				//h.put("NILAI_HUTANG", rs.getString("NILAI_HUTANG") == null ? "" : rs.getString("NILAI_HUTANG"));
				//h.put("BAKI_HUTANG", rs.getString("BAKI_HUTANG") == null ? "" : rs.getString("BAKI_HUTANG"));
				h.put("SELESAI_HUTANG", rs.getString("SELESAI_HUTANG") == null ? "" : rs.getString("SELESAI_HUTANG"));
					
				listBILANGAN.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listBILANGAN;
	}
	
	public Vector getLaporaneHutangAgensibyPPK(String bulan1, String bulan2, String tahun1, String tahun2, String id_Pejabat) {
		String sql = "";
		Vector listBILANGAN = null;
		Hashtable h;
		
		try {
			listBILANGAN = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			if ((bulan1 == null) && (bulan2 == null) && (tahun1 != null) && (tahun2 == null))
			{
				sql = 	"SELECT P.ID_PEJABAT, P.NAMA_PEJABAT, SUM(NVL(SH.NILAI_HUTANG,0)) AS NILAI_HUTANG, SUM(NVL(SH.BAKI_HUTANG,0)) AS BAKI_HUTANG, "+
						" COUNT(SH.ID_SENARAIHUTANG) AS BILANGAN_AKAUN_HUTANG, "+
						" COUNT(DISTINCT H.NO_PENGENALAN_BARU) AS JUMLAH_PENGHUTANG,COUNT(DISTINCT FAIL.ID_FAIL) AS BILANGAN_FAIL_PUSAKA "+
						" FROM TBLPPKHUTANG H, TBLRUJPEJABAT P, TBLPPKSENARAIHUTANG SH, "+
						" (SELECT SM.NO_KP_BARU, F.ID_FAIL FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPFDFAIL F "+
						" WHERE SM.ID_SIMATI = PSM.ID_SIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "+
						" AND P.ID_STATUS <> '999') FAIL "+
						" WHERE H.ID_PEJABAT = P.ID_PEJABAT AND H.ID_HUTANG = SH.ID_HUTANG "+
						" AND TO_CHAR(SH.TARIKH_PERJANJIAN, 'yyyy') = '"+tahun1+"' "+
						" AND H.NO_PENGENALAN_BARU = FAIL.NO_KP_BARU(+) "+
						" AND H.ID_PEJABAT = '" + id_Pejabat +"' "+
						" GROUP BY  P.ID_PEJABAT, P.NAMA_PEJABAT ";
				
				
			}
			if ((bulan1 != null) && (tahun1 != null) && (bulan2 == null) && (tahun2 == null))
			{
			sql = 	"SELECT P.ID_PEJABAT, P.NAMA_PEJABAT, SUM(NVL(SH.NILAI_HUTANG,0)) AS NILAI_HUTANG, SUM(NVL(SH.BAKI_HUTANG,0)) AS BAKI_HUTANG, "+
					" COUNT(SH.ID_SENARAIHUTANG) AS BILANGAN_AKAUN_HUTANG, "+
					" COUNT(DISTINCT H.NO_PENGENALAN_BARU) AS JUMLAH_PENGHUTANG,COUNT(DISTINCT FAIL.ID_FAIL) AS BILANGAN_FAIL_PUSAKA "+
					" FROM TBLPPKHUTANG H, TBLRUJPEJABAT P, TBLPPKSENARAIHUTANG SH, "+
					" (SELECT SM.NO_KP_BARU, F.ID_FAIL FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE SM.ID_SIMATI = PSM.ID_SIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "+
					" AND P.ID_STATUS <> '999') FAIL "+
					" WHERE H.ID_PEJABAT = P.ID_PEJABAT AND H.ID_HUTANG = SH.ID_HUTANG "+
					" AND TO_CHAR(SH.TARIKH_PERJANJIAN, 'mm/yyyy') = '"+bulan1+"/"+tahun1+"' "+
					" AND H.NO_PENGENALAN_BARU = FAIL.NO_KP_BARU(+) "+
					" AND H.ID_PEJABAT = '" + id_Pejabat +"' "+
					" GROUP BY  P.ID_PEJABAT, P.NAMA_PEJABAT ";
			
			
			}
			if ((bulan1 != null) && (bulan2 != null) && (tahun1 != null) && (tahun2 != null))
			{
			sql = 	"SELECT P.ID_PEJABAT, P.NAMA_PEJABAT, SUM(NVL(SH.NILAI_HUTANG,0)) AS NILAI_HUTANG, SUM(NVL(SH.BAKI_HUTANG,0)) AS BAKI_HUTANG, "+
					" COUNT(SH.ID_SENARAIHUTANG) AS BILANGAN_AKAUN_HUTANG, "+
					" COUNT(DISTINCT H.NO_PENGENALAN_BARU) AS JUMLAH_PENGHUTANG,COUNT(DISTINCT FAIL.ID_FAIL) AS BILANGAN_FAIL_PUSAKA "+
					" FROM TBLPPKHUTANG H, TBLRUJPEJABAT P, TBLPPKSENARAIHUTANG SH, "+
					" (SELECT SM.NO_KP_BARU, F.ID_FAIL FROM TBLPPKSIMATI SM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE SM.ID_SIMATI = PSM.ID_SIMATI AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "+
					" AND P.ID_STATUS <> '999') FAIL "+
					" WHERE H.ID_PEJABAT = P.ID_PEJABAT AND H.ID_HUTANG = SH.ID_HUTANG "+
					" AND SH.TARIKH_PERJANJIAN between TO_DATE('"+bulan1+"/"+tahun1+"', 'mm/yyyy') and TO_DATE('"+bulan2+"/"+tahun2+"','mm/yyyy') "+
					" AND H.NO_PENGENALAN_BARU = FAIL.NO_KP_BARU(+) "+
					" AND H.ID_PEJABAT = '" + id_Pejabat +"' "+
					" GROUP BY  P.ID_PEJABAT, P.NAMA_PEJABAT ";
			}
			System.out.println("getLaporaneHutangAgensibyPPK = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				h = new Hashtable();
				//h.put("BIL", rs.getString("BIL") == null ? "" : rs.getString("BIL"));
				
				if(rs.getString("NILAI_HUTANG") != null && rs.getString("NILAI_HUTANG") != "" ){
			    	String jb = rs.getString("NILAI_HUTANG");
			    	DecimalFormat decim = new DecimalFormat("#.00");
			    	double NILAI_HUTANG2 = Double.parseDouble(jb);
			    	String NILAI_HUTANG = decim.format(NILAI_HUTANG2);
			    	h.put("NILAI_HUTANG", NILAI_HUTANG);
			    }else{
			    	h.put("NILAI_HUTANG", "");
			    }
				
				if(rs.getString("BAKI_HUTANG") != null && rs.getString("BAKI_HUTANG") != "" ){
			    	String jb = rs.getString("BAKI_HUTANG");
			    	DecimalFormat decim = new DecimalFormat("#.00");
			    	double BAKI_HUTANG2 = Double.parseDouble(jb);
			    	String BAKI_HUTANG = decim.format(BAKI_HUTANG2);
			    	h.put("BAKI_HUTANG", BAKI_HUTANG);
			    }else{
			    	h.put("BAKI_HUTANG", "");
			    }
				
				h.put("BILANGAN_AKAUN_HUTANG", rs.getString("BILANGAN_AKAUN_HUTANG") == null ? "" : rs.getString("BILANGAN_AKAUN_HUTANG"));
				h.put("JUMLAH_PENGHUTANG", rs.getString("JUMLAH_PENGHUTANG") == null ? "" : rs.getString("JUMLAH_PENGHUTANG"));
				h.put("BILANGAN_FAIL_PUSAKA", rs.getString("BILANGAN_FAIL_PUSAKA") == null ? "" : rs.getString("BILANGAN_FAIL_PUSAKA"));
					
				listBILANGAN.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listBILANGAN;
	}
	
	public Vector<Hashtable<String,String>> getLaporaneHutangAgensi2(String bulan1, String bulan2, String tahun1, String tahun2, String USER_ID_SYSTEM) {
		String sql = "";
		Vector<Hashtable<String,String>> listBILANGANFAILPUSAKA = null;
		Hashtable<String,String> h;
		
		try {
			listBILANGANFAILPUSAKA = new Vector<Hashtable<String,String>>();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			if ((bulan1 == null) && (bulan2 == null) && (tahun1 != null) && (tahun2 == null))
			{
				sql = 	"SELECT COUNT(DISTINCT H.ID_HUTANG) AS JUMLAH_FAIL "+
						" FROM TBLPFDFAIL F, "+
						" TBLPPKPERMOHONAN P, "+
						" TBLPPKPERMOHONANSIMATI PSM, "+
						" TBLPPKSIMATI SM, "+
						" TBLPPKPEMOHON PM, "+
						" TBLPPKOBPERMOHONAN OB, "+
						" TBLPPKHUTANG H, "+
						" TBLPPKSENARAIHUTANG B "+
						" WHERE F.ID_FAIL = P.ID_FAIL "+
						" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
						" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
						" AND P.ID_PEMOHON = PM.ID_PEMOHON "+
						" AND OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI "+
						" AND ( PM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
								" OR SM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
								" OR OB.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
							" ) "+
						" AND H.ID_HUTANG = B.ID_HUTANG "+
						" AND TO_CHAR(B.TARIKH_MASUK, 'yyyy') = '"+tahun1+"' "+
						" AND H.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
				 		" FROM USERS_INTEGRASI D "+
				 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			}
			
			if ((bulan1 != null) && (tahun1 != null) && (bulan2 == null) && (tahun2 == null))
			{
			sql = 	"SELECT COUNT(DISTINCT H.ID_HUTANG) AS JUMLAH_FAIL "+
					" FROM TBLPFDFAIL F, "+
					" TBLPPKPERMOHONAN P, "+
					" TBLPPKPERMOHONANSIMATI PSM, "+
					" TBLPPKSIMATI SM, "+
					" TBLPPKPEMOHON PM, "+
					" TBLPPKOBPERMOHONAN OB, "+
					" TBLPPKHUTANG H, "+
					" TBLPPKSENARAIHUTANG B "+
					" WHERE F.ID_FAIL = P.ID_FAIL "+
					" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
					" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
					" AND P.ID_PEMOHON = PM.ID_PEMOHON "+
					" AND OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI "+
					" AND ( PM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
							" OR SM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
							" OR OB.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
						" ) "+
					" AND H.ID_HUTANG = B.ID_HUTANG "+
					" AND TO_CHAR(B.TARIKH_PERJANJIAN, 'mm/yyyy') = '"+bulan1+"/"+tahun1+"' "+
					" AND H.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
			 		" FROM USERS_INTEGRASI D "+
			 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			}
			if ((bulan1 != null) && (bulan2 != null) && (tahun1 != null) && (tahun2 != null))
			{
			sql = 	"SELECT COUNT(DISTINCT H.ID_HUTANG) AS JUMLAH_FAIL "+
					" FROM TBLPFDFAIL F, "+
					" TBLPPKPERMOHONAN P, "+
					" TBLPPKPERMOHONANSIMATI PSM, "+
					" TBLPPKSIMATI SM, "+
					" TBLPPKPEMOHON PM, "+
					" TBLPPKOBPERMOHONAN OB, "+
					" TBLPPKHUTANG H, "+
					" TBLPPKSENARAIHUTANG B "+
					" WHERE F.ID_FAIL = P.ID_FAIL "+
					" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN "+
					" AND PSM.ID_SIMATI = SM.ID_SIMATI "+
					" AND P.ID_PEMOHON = PM.ID_PEMOHON "+
					" AND OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI "+
					" AND ( PM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
							" OR SM.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
							" OR OB.NO_KP_BARU = H.NO_PENGENALAN_BARU "+
						" ) "+
					" AND H.ID_HUTANG = B.ID_HUTANG "+
					" AND B.TARIKH_PERJANJIAN between TO_DATE('"+bulan1+"/"+tahun1+"', 'mm/yyyy') and TO_DATE('"+bulan2+"/"+tahun2+"','mm/yyyy') "+
					" AND H.ID_PEJABAT = (SELECT D.ID_PEJABAT "+
			 		" FROM USERS_INTEGRASI D "+
			 		" WHERE D.USER_ID = " + USER_ID_SYSTEM +")";	
			}
			System.out.println("getLaporaneHutangAgensi2 = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				h = new Hashtable<String,String>();
				h.put("JUMLAH_FAIL", rs.getString("JUMLAH_FAIL") == null ? "" : rs.getString("JUMLAH_FAIL"));
				
					
				listBILANGANFAILPUSAKA.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listBILANGANFAILPUSAKA;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiPenghutang2(String nama, String noPengenalanBaru, String USER_ID_SYSTEM) {
		String sql = "";
		Vector<Hashtable<String,String>> listPenghutang2 = null;
		Hashtable<String,String> h;
		
		try {
			listPenghutang2 = new Vector<Hashtable<String,String>>();
			
			db = new Db();
			Statement stmt = db.getStatement();
			

			sql = getSqlPenghutangAgensi();
			// nama
			if (nama != null) {
				if (!nama.trim().equals("")) {
					sql = sql + " AND UPPER(A.NAMA) LIKE '%' ||'"
							+ nama.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// noPengenalanBaru
			if (noPengenalanBaru != null) {
				if (!noPengenalanBaru.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_PENGENALAN_BARU) LIKE '%' ||'"
							+ noPengenalanBaru.trim().toUpperCase() + "'|| '%'";
				}
			}
			sql = sql + " AND A.ID_PEJABAT = (SELECT D.ID_PEJABAT";
			sql = sql + " FROM USERS_INTEGRASI D";
			sql = sql + " WHERE D.USER_ID = " + USER_ID_SYSTEM +")";
			sql = sql + " ORDER BY A.NAMA ASC";	
			System.out.println("sql2 = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHutang", rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalanBaru", rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));

				listPenghutang2.addElement(h);
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listPenghutang2;
	}
	
	public Vector idPejabat(String USER_ID_SYSTEM) {
		String sql = "";
		Vector idPejabat = null;
		Hashtable h;
		System.out.println("USER_ID_SYSTEM = "+USER_ID_SYSTEM);
		try {
			idPejabat = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PEJABAT, B.NAMA_PEJABAT"
					+ " FROM USERS_INTEGRASI A, TBLRUJPEJABAT B"
					+ " WHERE A.USER_ID = " + USER_ID_SYSTEM
					+ " AND A.ID_PEJABAT = B.ID_PEJABAT ";
			
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));

				
				idPejabat.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return idPejabat;
	}
	
	public Hashtable namaPejabat(String USER_ID_SYSTEM) {
		String sql = "";
		Vector namaPejabat = null;
		Hashtable h = null;
		System.out.println("USER_ID_SYSTEM = "+USER_ID_SYSTEM);
		try {
			namaPejabat = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PEJABAT, B.NAMA_PEJABAT"
					+ " FROM USERS_INTEGRASI A, TBLRUJPEJABAT B"
					+ " WHERE A.USER_ID = " + USER_ID_SYSTEM
					+ " AND A.ID_PEJABAT = B.ID_PEJABAT ";
			
			System.out.println("sqlnamaAgensi = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();

				h.put("namaAgensi", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return h;
	}
	
	public Hashtable namaPejabat2(String USER_ID_SYSTEM) {
		String sql = "";
		Vector namaPejabat = null;
		Hashtable h = null;
		System.out.println("USER_ID_SYSTEM = "+USER_ID_SYSTEM);
		try {
			namaPejabat = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PEJABAT, B.NAMA_PEJABAT"
					+ " FROM USERS_INTEGRASI A, TBLRUJPEJABAT B"
					+ " WHERE A.USER_ID = " + USER_ID_SYSTEM
					+ " AND A.ID_PEJABAT = B.ID_PEJABAT ";
			
			System.out.println("sqlID_PEJABAT = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();

				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return h;
	}
	
	public Vector namaAgensi() {
		String sql = "";
		Vector namaAgensi = null;
		Hashtable h = null;
		
		try {
			namaAgensi = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PEJABAT, B.NAMA_PEJABAT"
					+ " FROM USERS_INTEGRASI A, TBLRUJPEJABAT B"
					+ " WHERE A.ID_PEJABAT = B.ID_PEJABAT"
					+ " AND B.ID_JENISPEJABAT='161782' "
					+ " ORDER BY B.NAMA_PEJABAT ASC";
			
			System.out.println("sqlnamaAgensi = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();

				h.put("namaAgensi", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("idAgensi", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				
				namaAgensi.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return namaAgensi;
	}
	
	public Vector namaAgensibyIdPejabat(String idAgensi) {
		String sql = "";
		Vector namaAgensi = null;
		Hashtable h = null;
		
		try {
			namaAgensi = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT A.ID_PEJABAT, B.NAMA_PEJABAT"
					+ " FROM USERS_INTEGRASI A, TBLRUJPEJABAT B"
					+ " WHERE A.ID_PEJABAT = B.ID_PEJABAT"
					+ " AND B.ID_PEJABAT='"+idAgensi+"' "
					+ " ORDER BY B.NAMA_PEJABAT ASC";
			
			System.out.println("sqlnamaAgensi = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();

				h.put("namaAgensi", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("idAgensi", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				
				namaAgensi.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return namaAgensi;
	}

	public Hashtable simpanPenghutang(String nama, String noPengenalanBaru, String noTelefon,
			String emel, String catatan, String alamat1, String alamat2,
			String alamat3, String poskod, String idNegeri, String idBandar, String jabatan,
			HttpSession session, String idPejabat) {

		Hashtable penghutang = null;
		String sql = "";
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String idHutang = "0";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			// TBLPPKHUTANG
			//
			idHutang = String.valueOf(DB.getNextID("TBLPPKHUTANG_SEQ"));
			r.add("ID_HUTANG", r.unquote(idHutang));
			r.add("NAMA", nama);
			r.add("NO_PENGENALAN_BARU", noPengenalanBaru);
			r.add("NO_TELEFON", noTelefon);
			r.add("EMEL", emel);
			r.add("CATATAN", catatan);
			r.add("ALAMAT_1", alamat1);
			r.add("ALAMAT_2", alamat2);
			r.add("ALAMAT_3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_PEJABAT", r.unquote(idPejabat));
			r.add("ID_NEGERI", r.unquote(idNegeri == "" ? "9999" : idNegeri));
			r.add("ID_BANDAR", r.unquote(idBandar == "" ? "9999" : idBandar));
			r.add("JABATAN", jabatan);
			r.add("ID_MASUK", r.unquote(userId));
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			
			sql = r.getSQLInsert("TBLPPKHUTANG");
			System.out.println("simpanPenghutang:sql="+sql);
			stmt.executeUpdate(sql);			
			conn.commit();

			 //penghutang = getMaklumatPenghutang(String.valueOf(idHutang));
			 penghutang = getMaklumatPenghutang(idHutang);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}

		return penghutang;
		
	}
	
	
	public Vector getMaklumatSimati(String id_SenaraiHutang) {
		Db db = null;
		String sql = "";
		Vector vMaklumatSimati = null;
		Hashtable MaklumatSimati = null;
		
		try {
			vMaklumatSimati = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SIMATI FROM tblppksimati WHERE NO_KP_BARU in (";
			sql = sql +	"SELECT H.NO_PENGENALAN_BARU FROM TBLPPKHUTANG H, TBLPPKSENARAIHUTANG SH WHERE H.ID_HUTANG = SH.ID_HUTANG AND SH.ID_SENARAIHUTANG = " + id_SenaraiHutang + ")";
			System.out.print("getMaklumatSimati:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			if (rs.next()) {
				MaklumatSimati = new Hashtable();
				MaklumatSimati.put("ID_SIMATI",rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				vMaklumatSimati.addElement(MaklumatSimati);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return vMaklumatSimati;
	}
	
	public Vector getMaklumatMyID(String id_SenaraiHutang) {
		Db db = null;
		String sql = "";
		Vector vMaklumatSimati = null;
		Hashtable MaklumatSimati = null;
		
		try {
			vMaklumatSimati = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_PENGENALAN_BARU FROM TBLPPKHUTANG HUTANG, TBLPPKSENARAIHUTANG SENARAI WHERE"; 
			sql = sql +	" HUTANG.ID_HUTANG = SENARAI.ID_HUTANG";
			sql = sql +	" AND SENARAI.ID_SENARAIHUTANG = "+id_SenaraiHutang;
			
			System.out.print("getMaklumatID:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			if (rs.next()) {
				MaklumatSimati = new Hashtable();
				MaklumatSimati.put("NO_PENGENALAN_BARU",rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				vMaklumatSimati.addElement(MaklumatSimati);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return vMaklumatSimati;
	}
	
	public Vector getMaklumatPermohonanSimati(String idSimati) {
		Db db = null;
		String sql = "";
		Vector vMaklumatSimati = null;
		Hashtable MaklumatSimati = null;
		
		try {
			vMaklumatSimati = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_SIMATI = "+idSimati;
			System.out.print("getMaklumatPermohonanSimati:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			if (rs.next()) {
				MaklumatSimati = new Hashtable();
				MaklumatSimati.put("ID_PERMOHONANSIMATI",rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				vMaklumatSimati.addElement(MaklumatSimati);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return vMaklumatSimati;
	}
	
	public Hashtable getMaklumatPenghutang(String idHutang) {
		Db db = null;
		String sql = "";
		Hashtable penghutang = null;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPPKHUTANG WHERE ID_HUTANG = '" + idHutang + "'";
			System.out.print("getMaklumatPenghutang:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			if (rs.next()) {
				penghutang = new Hashtable();
				penghutang.put("idHutang",rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				penghutang.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				penghutang.put("noPengenalanBaru",rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				penghutang.put("noTelefon",rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));
				penghutang.put("emel",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				penghutang.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				penghutang.put("alamat1",rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				penghutang.put("alamat2",rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				penghutang.put("alamat3",rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				penghutang.put("poskod",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				penghutang.put("jabatan",rs.getString("JABATAN") == null ? "" : rs.getString("JABATAN"));
				penghutang.put("idBandar",rs.getString("ID_BANDAR") == null ? "9999" : rs.getString("ID_BANDAR"));
				penghutang.put("idNegeri",rs.getString("ID_NEGERI") == null ? "9999" : rs.getString("ID_NEGERI"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return penghutang;
	}
	
	public Vector getMaklumatPenghutangPPK(String idHutang) {
		
		String sql = "";
		Vector penghutangPPK = null;
		Hashtable h = null;
		
		try {
			penghutangPPK = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT F.NO_FAIL,P.SEKSYEN, "+
					" (CASE WHEN SM.NO_KP_BARU = H.NO_PENGENALAN_BARU THEN 'SIMATI'  "+
					" WHEN PM.NO_KP_BARU = H.NO_PENGENALAN_BARU THEN 'PEMOHON' "+
					" WHEN OB.NO_KP_BARU =  H.NO_PENGENALAN_BARU AND OB.ID_TARAFKPTG = '1' THEN 'WARIS' ELSE '' END) AS PENGHUTANG_ADALAH, "+
					" CASE WHEN P.ID_STATUS IN (177,175,173,21,47) THEN 'SELESAI' ELSE 'DALAM PROSES' END AS STATUS, "+
					" UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, "+
					" UPPER(PEJ.ALAMAT3) AS ALAMAT3, PEJ.POSKOD,UPPER(PEJ.NEGERI) AS NEGERI, UPPER(PEJ.BANDAR) AS BANDAR, "+
					" PEJ.NO_TEL,PEJ.NO_FAX "+
					" FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLRUJSTATUS ST, TBLPPKOBPERMOHONAN OB, "+
					" TBLPPKHUTANG H, "+
					" (SELECT PU.ID_DAERAHURUS, PJ.NAMA_PEJABAT,PJ.ALAMAT1,PJ.ALAMAT2,PJ.ALAMAT3,PJ.POSKOD,N.NAMA_NEGERI AS NEGERI,B.KETERANGAN AS BANDAR, PJ.NO_TEL, PJ.NO_FAX "+
					" FROM TBLRUJPEJABATJKPTG PJ, TBLRUJPEJABATURUSAN PU, TBLRUJNEGERI N, TBLRUJBANDAR B "+
					" WHERE PU.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG AND PJ.ID_JENISPEJABAT = '22' AND PU.ID_SEKSYEN = '2' AND PJ.ID_NEGERI = N.ID_NEGERI(+) AND PJ.ID_BANDAR = B.ID_BANDAR(+)) PEJ "+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI AND P.ID_STATUS = ST.ID_STATUS "+
					" AND P.ID_PEMOHON = PM.ID_PEMOHON AND OB.ID_PERMOHONANSIMATI = PSM.ID_PERMOHONANSIMATI(+) "+
					" AND (PM.NO_KP_BARU = H.NO_PENGENALAN_BARU OR SM.NO_KP_BARU =  H.NO_PENGENALAN_BARU OR OB.NO_KP_BARU =  H.NO_PENGENALAN_BARU) "+
					" AND P.ID_DAERAHMHN = PEJ.ID_DAERAHURUS  "+
					" AND H.ID_HUTANG = '"+idHutang+"'";
			System.out.print("getMaklumatPenghutangPPK:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			while (rs.next()) {
				h = new Hashtable();
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("KATEGORI",rs.getString("PENGHUTANG_ADALAH") == null ? "" : rs.getString("PENGHUTANG_ADALAH"));
				penghutangPPK.addElement(h);
				System.out.print("penghutang VECTOR****"+penghutangPPK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return penghutangPPK;
	}
	
	public Vector getMaklumatAgensi(String idHutang) {
		Db db = null;
		String sql = "";
		Vector agensi = null;
		Hashtable h;
		
		try {
			agensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT A.ID_PEJABAT, A.NAMA, A.NO_PENGENALAN_BARU, P.NAMA_PEJABAT, A.ID_HUTANG FROM TBLPPKHUTANG A, TBLPPKSENARAIHUTANG B, TBLRUJPEJABAT P "+
				" WHERE A.ID_HUTANG = B.ID_HUTANG "+
				" AND A.ID_PEJABAT = P.ID_PEJABAT "+
				" AND A.NO_PENGENALAN_BARU IN (SELECT C.NO_PENGENALAN_BARU FROM TBLPPKHUTANG C WHERE ID_HUTANG = '" + idHutang + "')";
			System.out.print("getMaklumatAgensi:sql"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			
			if (rs.next()) {
				h = new Hashtable();
				h.put("idHutang",rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA").toUpperCase());
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("noPengenalanBaru",rs.getString("NO_PENGENALAN_BARU") == null ? "" : rs.getString("NO_PENGENALAN_BARU"));
				h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				agensi.addElement(h);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return agensi;
	}
	
	
	
	public Hashtable kemaskiniPenghutang(String idHutang, String nama, String noTelefon,
			String emel, String catatan, String alamat1, String alamat2,
			String alamat3, String poskod, String idNegeri, String idBandar, String jabatan,
			HttpSession session) {

		Hashtable penghutang = null;
		String sql = "";
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			// TBLPPKHUTANG
			r.update("ID_HUTANG", idHutang);
			r.add("NAMA", nama);
			r.add("NO_TELEFON", noTelefon);
			r.add("EMEL", emel);
			r.add("CATATAN", catatan);
			r.add("ALAMAT_1", alamat1);
			r.add("ALAMAT_2", alamat2);
			r.add("ALAMAT_3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri == "" ? "9999" : idNegeri);
			r.add("ID_BANDAR", idBandar == "" ? "9999" : idBandar);
			r.add("JABATAN", jabatan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPPKHUTANG");
			System.out.println("sqlPENGHUTANG = "+sql);
			stmt.executeUpdate(sql);	
			conn.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		penghutang = getMaklumatPenghutang(idHutang);
		return penghutang;
	}
	
	public void hapusPenghutang(String idHutang) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT ID_SENARAIHUTANG FROM TBLPPKSENARAIHUTANG WHERE ID_HUTANG = '" + idHutang + "'";
			ResultSet rs = stmt.executeQuery(sql);			
			while (rs.next()) {
				// TBLPPKDOKUMENSENARAIHUTANG
				r = new SQLRenderer();
				r.add("ID_SENARAIHUTANG", rs.getString("ID_SENARAIHUTANG"));
				sql = r.getSQLDelete("TBLPPKDOKUMENSENARAIHUTANG");
				stmt.executeUpdate(sql);
			}
			
			// TBLPPKSENARAIHUTANG
			r = new SQLRenderer();
			r.add("ID_HUTANG", idHutang);
			sql = r.getSQLDelete("TBLPPKSENARAIHUTANG");
			stmt.executeUpdate(sql);
						
			// TBLPPKHUTANG
			r = new SQLRenderer();
			r.add("ID_HUTANG", idHutang);
			sql = r.getSQLDelete("TBLPPKHUTANG");
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
			conn.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String simpanHutang(Hashtable data) {
		//Hashtable penghutang = null;
		
		//return penghutang;
		String idPermohonansimati = (String) data.get("idPermohonansimati");
		String idSimati = (String) data.get("idSimati");
		String nama = (String) data.get("nama");
		String jenisHutang = (String) data.get("jenisHutang");
		String noPengenalan = (String) data.get("noPengenalan");
		String alamat1 = (String) data.get("alamat1");
		String alamat2 = (String) data.get("alamat2");
		String alamat3 = (String) data.get("alamat3");
		String poskod = (String) data.get("poskod");
		String negeri = (String) data.get("negeri");
		String bandar = (String) data.get("bandar");
		String noTelefon = (String) data.get("noTelefon");
		String noAkaun = (String) data.get("noAkaun");
		String bakiHutang = (String) data.get("bakiHutang");	
		String id_SenaraiHutang = (String) data.get("id_SenaraiHutang");	
		id_SenaraiHutang = id_SenaraiHutang.replace("'", "");
		System.out.println("nama = "+nama);
		System.out.println("jenisHutang = "+jenisHutang);
		System.out.println("noPengenalan = "+noPengenalan);
		System.out.println("alamat1 = "+alamat1);
		System.out.println("alamat2 = "+alamat2);
		System.out.println("alamat3 = "+alamat3);
		System.out.println("poskod = "+poskod);
		System.out.println("negeri = "+negeri);
		System.out.println("bandar = "+bandar);
		System.out.println("noTelefon = "+noTelefon);
		System.out.println("bakiHutang = "+bakiHutang);
		System.out.println("idPermohonansimati = "+idPermohonansimati);
		System.out.println("idSimati = "+idSimati);
		System.out.println("id_SenaraiHutang = "+id_SenaraiHutang);
		
		
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", idSimati);
			// r.add("nama_warga", nama_warga);
			r.add("nama_Penghutang", nama);
			r.add("no_Kp_Baru", noPengenalan);
			r.add("no_Kp_Lain", "");
			r.add("no_Kp_Lama", "");
			r.add("jenis_Kp", "");
			r.add("jenis_Penghutang", 1);
			r.add("alamat_1", alamat1);
			r.add("alamat_2", alamat2);
			r.add("alamat_3", alamat3);
			r.add("id_Bandar", bandar);
			r.add("poskod", poskod);
			r.add("jenis_Warga", "");
			r.add("jenis_Agama", "");
			r.add("id_Negeri", negeri);
			r.add("no_Akaun", noAkaun);
			r.add("butiran_Hutang", "");
			r.add("jumlah_Hutang", bakiHutang);
			r.add("id_Permohonansimati", idPermohonansimati);
			r.add("id_Masuk", "");
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkpenghutang");
			
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
			
			// TBLPPKSENARAIHUTANG
			r = new SQLRenderer();
			r.update("ID_SENARAIHUTANG", id_SenaraiHutang);
			r.add("FLAG_SALIN", 1);
						
			sql = r.getSQLUpdate("TBLPPKSENARAIHUTANG");
			System.out.println("sql3 = "+sql);
			stmt.executeUpdate(sql);			
			conn.commit();							
		}catch (Exception re) {
			myLog.error("Error: ", re);
			//throw re;
		} finally {
			if (db != null)
				db.close();
		}	
		
		return nama;
	}
	/*
	public Hashtable simpanHutangDummy() {
		Hashtable penghutang = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_Simati", "1616519074");
			// r.add("nama_warga", nama_warga);
			r.add("nama_Penghutang", "PTPTN");
			r.add("no_Kp_Baru", "480713201249");
			r.add("no_Kp_Lain", "");
			r.add("no_Kp_Lama", "");
			r.add("jenis_Kp", "");
			r.add("jenis_Penghutang", 1);
			r.add("alamat_1", "PERBADANAN TABUNG PENDIDIKAN TINGGI NASIONAL");
			r.add("alamat_2", "MENARA PTPTN");
			r.add("alamat_3", "MEGAN AVENUE II");
			r.add("id_Bandar", "");
			r.add("poskod", "50450");
			r.add("jenis_Warga", "");
			r.add("jenis_Agama", "");
			r.add("id_Negeri", "");
			r.add("no_Akaun", "");
			r.add("butiran_Hutang", "");
			r.add("jumlah_Hutang", 7000);
			r.add("id_Permohonansimati", "1616537230");
			r.add("id_Masuk", "");
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			sql = r.getSQLInsert("tblppkpenghutang");
			
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
		}catch (Exception re) {
			myLog.error("Error: ", re);
			//throw re;
		} finally {
			if (db != null)
				db.close();
		}
		return penghutang;
	}
	*/
	
	
	
	public Vector getSenaraiHutangAgensi(String idHutang) {
		String sql = "";
		Vector listHutang = null;
		Hashtable h;
		
		try {
			listHutang = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SENARAIHUTANG, A.NAMA, A.NO_PENGENALAN, A.ALAMAT_1, A.ALAMAT_2,"
					+ " A.ALAMAT_3, A.POSKOD, C.KETERANGAN AS NAMA_BANDAR, B.NAMA_NEGERI, A.NO_TELEFON, A.EMEL,"
					+ " A.JENIS_HUTANG, A.NO_AKAUN, A.BUTIRAN_HUTANG, A.NILAI_HUTANG, A.BAKI_HUTANG,"
					+ " A.STATUS_HUTANG, A.TARIKH_SELESAI_HUTANG, A.TARIKH_PERJANJIAN, A.CATATAN, A.TARIKH_KEMASKINI, A.FLAG_SALIN"
					+ " FROM TBLPPKSENARAIHUTANG A, TBLRUJNEGERI B, TBLRUJBANDAR C"
					+ " WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+)"
					+ " AND A.ID_HUTANG = '" + idHutang + "'"
					+ " ORDER BY A.NAMA ASC";	
			System.out.println("sqlgetSenaraiHutangAgensi*** = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiHutang", rs.getString("ID_SENARAIHUTANG") == null ? "" : rs.getString("ID_SENARAIHUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));				
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("jenisHutang", rs.getString("JENIS_HUTANG") == null ? "" : rs.getString("JENIS_HUTANG"));
				h.put("noAkaun", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));
				h.put("butiranHutang", rs.getString("BUTIRAN_HUTANG") == null ? "" : rs.getString("BUTIRAN_HUTANG"));
				h.put("nilaiHutang", rs.getString("NILAI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HUTANG"))));
				h.put("bakiHutang", rs.getString("BAKI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("BAKI_HUTANG"))));
				h.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				h.put("tarikhSelesaiHutang", rs.getDate("TARIKH_SELESAI_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_SELESAI_HUTANG")));
				h.put("tarikhPerjanjian", rs.getDate("TARIKH_PERJANJIAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERJANJIAN")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("flagSalin", rs.getString("FLAG_SALIN") == null ? "" : rs.getString("FLAG_SALIN"));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));

				listHutang.addElement(h);
				System.out.print("listHutang VECTOR"+listHutang);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listHutang;
	}
	
	public Vector getSenaraiHutang(String idHutang) {
		String sql = "";
		Vector listHutang = null;
		Hashtable h;
		
		try {
			listHutang = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SENARAIHUTANG, A.NAMA, A.NO_PENGENALAN, A.ALAMAT_1, A.ALAMAT_2, "+
					" A.ALAMAT_3, A.POSKOD, C.KETERANGAN AS NAMA_BANDAR, B.NAMA_NEGERI, "+
					" A.NO_TELEFON, A.EMEL, A.JENIS_HUTANG, A.NO_AKAUN, A.BUTIRAN_HUTANG, "+
					" A.NILAI_HUTANG, A.BAKI_HUTANG, A.STATUS_HUTANG, "+
					" A.TARIKH_SELESAI_HUTANG, A.TARIKH_PERJANJIAN, A.CATATAN, A.TARIKH_KEMASKINI, A.STATUS_HUTANG, A.FLAG_SALIN "+
					" FROM TBLPPKSENARAIHUTANG A, TBLRUJNEGERI B, TBLRUJBANDAR C, TBLPPKHUTANG H, TBLPPKHUTANG H2 "+
					" WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) "+
					" AND A.ID_HUTANG = H.ID_HUTANG AND H.NO_PENGENALAN_BARU = H2.NO_PENGENALAN_BARU AND H2.ID_HUTANG = '"+idHutang+"' "+
					" ORDER BY A.NAMA ASC ";
			System.out.println("sqlgetSenaraiHutang2*** = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiHutang", rs.getString("ID_SENARAIHUTANG") == null ? "" : rs.getString("ID_SENARAIHUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));				
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("jenisHutang", rs.getString("JENIS_HUTANG") == null ? "" : rs.getString("JENIS_HUTANG"));
				h.put("noAkaun", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));
				h.put("butiranHutang", rs.getString("BUTIRAN_HUTANG") == null ? "" : rs.getString("BUTIRAN_HUTANG"));
				h.put("nilaiHutang", rs.getString("NILAI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HUTANG"))));
				h.put("bakiHutang", rs.getString("BAKI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("BAKI_HUTANG"))));
				h.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				h.put("tarikhSelesaiHutang", rs.getDate("TARIKH_SELESAI_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_SELESAI_HUTANG")));
				h.put("tarikhPerjanjian", rs.getDate("TARIKH_PERJANJIAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERJANJIAN")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("flagSalin", rs.getString("FLAG_SALIN") == null ? "" : rs.getString("FLAG_SALIN"));
				h.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));

				listHutang.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listHutang;
	}
	
	public Vector getSenaraiHutangviaMyID(String mydID) {
		String sql = "";
		Vector listHutang = null;
		Hashtable h;
		
		try {
			listHutang = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SENARAIHUTANG, A.NAMA, A.NO_PENGENALAN, A.ALAMAT_1, A.ALAMAT_2,"
					+ " A.ALAMAT_3, A.POSKOD, C.KETERANGAN AS NAMA_BANDAR, B.NAMA_NEGERI, A.NO_TELEFON, A.EMEL,"
					+ " A.JENIS_HUTANG, A.NO_AKAUN, A.BUTIRAN_HUTANG, A.NILAI_HUTANG, A.BAKI_HUTANG,"
					+ " A.STATUS_HUTANG, A.TARIKH_SELESAI_HUTANG, A.TARIKH_PERJANJIAN, A.CATATAN, A.TARIKH_KEMASKINI"
					+ " FROM TBLPPKSENARAIHUTANG A, TBLRUJNEGERI B, TBLRUJBANDAR C, TBLPPKHUTANG D"
					+ " WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+)"
					+ " AND A.ID_HUTANG = D.ID_HUTANG"
					+ " AND A.FLAG_SALIN = 0"
					+ " AND D.NO_PENGENALAN_BARU = '" + mydID + "'"
					+ " ORDER BY A.NAMA ASC";	
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiHutang", rs.getString("ID_SENARAIHUTANG") == null ? "" : rs.getString("ID_SENARAIHUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));				
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("jenisHutang", rs.getString("JENIS_HUTANG") == null ? "" : rs.getString("JENIS_HUTANG"));
				h.put("noAkaun", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));
				h.put("butiranHutang", rs.getString("BUTIRAN_HUTANG") == null ? "" : rs.getString("BUTIRAN_HUTANG"));
				h.put("nilaiHutang", rs.getString("NILAI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HUTANG"))));
				h.put("bakiHutang", rs.getString("BAKI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("BAKI_HUTANG"))));
				h.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				h.put("tarikhSelesaiHutang", rs.getDate("TARIKH_SELESAI_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_SELESAI_HUTANG")));
				h.put("tarikhPerjanjian", rs.getDate("TARIKH_PERJANJIAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERJANJIAN")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));

				listHutang.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listHutang;
	}
	
	public Vector getSenaraiHutang2(String idSenaraiHutang) {
		String sql = "";
		Vector listHutang = null;
		Hashtable h;
		
		try {
			listHutang = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SENARAIHUTANG, A.NAMA, A.ID_NEGERI, A.ID_BANDAR, A.NO_PENGENALAN, A.ALAMAT_1, A.ALAMAT_2,"
					+ " A.ALAMAT_3, A.POSKOD, C.KETERANGAN AS NAMA_BANDAR, B.NAMA_NEGERI, A.NO_TELEFON, A.EMEL,"
					+ " A.JENIS_HUTANG, A.NO_AKAUN, A.BUTIRAN_HUTANG, A.NILAI_HUTANG, A.BAKI_HUTANG,"
					+ " A.STATUS_HUTANG, A.TARIKH_SELESAI_HUTANG, A.TARIKH_PERJANJIAN, A.CATATAN, A.TARIKH_KEMASKINI, A.FLAG_SALIN"
					+ " FROM TBLPPKSENARAIHUTANG A, TBLRUJNEGERI B, TBLRUJBANDAR C"
					+ " WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+)"
					+ " AND A.ID_SENARAIHUTANG = " + idSenaraiHutang +""
					+ " ORDER BY A.NAMA ASC";	
			System.out.println("sqlgetSenaraiHutang2 = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiHutang", rs.getString("ID_SENARAIHUTANG") == null ? "" : rs.getString("ID_SENARAIHUTANG"));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));				
				h.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("jenisHutang", rs.getString("JENIS_HUTANG") == null ? "" : rs.getString("JENIS_HUTANG"));
				h.put("noAkaun", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));
				h.put("butiranHutang", rs.getString("BUTIRAN_HUTANG") == null ? "" : rs.getString("BUTIRAN_HUTANG"));
				h.put("nilaiHutang", rs.getString("NILAI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HUTANG"))));
				h.put("bakiHutang", rs.getString("BAKI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("BAKI_HUTANG"))));
				h.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				h.put("tarikhSelesaiHutang", rs.getDate("TARIKH_SELESAI_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_SELESAI_HUTANG")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("flagSalin", rs.getString("FLAG_SALIN") == null ? "" : rs.getString("FLAG_SALIN"));
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("id_bandar", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINI") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINI")));

				listHutang.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listHutang;
	}
	
	
	
	public Hashtable simpanHutang(String idHutang
		, String nama, String noPengenalan
		, String alamat1, String alamat2,String noTelefon, String alamat3, String poskod, String idNegeri, String idBandar
		, String jenisHutang, String noAkaun,String butiranHutang,  String nilaiHutang,  String bakiHutang
		, String tarikhTamatTempohHutang, String tarikhPerjanjian,String catatan, String insuransHutang, String catatanInsuransHutang
		, HttpSession session) {
		System.out.println("***sqlSimpanHutang***");
		Hashtable hutang = null;
		String sql = "";
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		long idSenaraiHutang = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			// TBLPPKSENARAIHUTANG
			idSenaraiHutang = DB.getNextID("TBLPPKSENARAIHUTANG_SEQ");
			r.add("ID_SENARAIHUTANG", idSenaraiHutang);
			r.add("ID_HUTANG", idHutang);
			r.add("NAMA", nama);
			r.add("NO_PENGENALAN", noPengenalan);			
			r.add("ALAMAT_1", alamat1);
			r.add("ALAMAT_2", alamat2);
			r.add("ALAMAT_3", alamat3);
			r.add("POSKOD", poskod);
			r.add("ID_NEGERI", idNegeri == "" ? "9999" : idNegeri);
			r.add("ID_BANDAR", idBandar == "" ? "9999" : idBandar);			
			r.add("JENIS_HUTANG", jenisHutang);
			r.add("NO_AKAUN", noAkaun);
			r.add("BUTIRAN_HUTANG", butiranHutang);			
			r.add("NILAI_HUTANG", Utils.RemoveComma(nilaiHutang));
			r.add("BAKI_HUTANG", Utils.RemoveComma(bakiHutang));
			if (Double.valueOf(Utils.RemoveComma(bakiHutang)) > 0D){
				r.add("STATUS_HUTANG", "T");
			} else {
				r.add("STATUS_HUTANG", "Y");
			}
			r.add("TARIKH_TAMAT_TEMPOH_HUTANG", r.unquote("to_date('" + tarikhTamatTempohHutang + "','dd/MM/yyyy')"));
			r.add("TARIKH_PERJANJIAN", r.unquote("to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')"));		
			r.add("CATATAN", catatan);	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("FLAG_SALIN", "0");
			r.add("NO_TELEFON", noTelefon);
			r.add("CATATAN_INSURANS", catatanInsuransHutang);
			r.add("FLAG_INSURANS", insuransHutang);
			sql = r.getSQLInsert("TBLPPKSENARAIHUTANG");
			System.out.println("sqlSimpanHutang = "+sql);
			stmt.executeUpdate(sql);			
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		hutang = getMaklumatHutang(String.valueOf(idSenaraiHutang));
		return hutang;
	}
	
	public Hashtable getMaklumatHutang(String idSenaraiHutang) {
		String sql = "";
		Hashtable hutang = null;
		
		try {			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_SENARAIHUTANG, A.ID_HUTANG, A.NAMA, A.NO_PENGENALAN, A.ALAMAT_1, A.ALAMAT_2,"
					+ " A.ALAMAT_3, A.POSKOD, A.ID_BANDAR, A.ID_NEGERI, A.NO_TELEFON, A.EMEL,"
					+ " A.JENIS_HUTANG, A.NO_AKAUN, A.BUTIRAN_HUTANG, A.NILAI_HUTANG, A.BAKI_HUTANG,"
					+ " A.STATUS_HUTANG, A.TARIKH_TAMAT_TEMPOH_HUTANG, A.TARIKH_PERJANJIAN, A.CATATAN, A.TARIKH_KEMASKINI, A.TARIKH_SELESAI_HUTANG, A.FLAG_SALIN,"
					+ " A.FLAG_INSURANS, A.CATATAN_INSURANS"
					+ " FROM TBLPPKSENARAIHUTANG A"
					+ " WHERE A.ID_SENARAIHUTANG = '" + idSenaraiHutang + "'"
					+ " ORDER BY A.NAMA ASC";
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				hutang = new Hashtable();
				hutang.put("idSenaraiHutang", rs.getString("ID_SENARAIHUTANG") == null ? "" : rs.getString("ID_SENARAIHUTANG"));
				hutang.put("idHutang", rs.getString("ID_HUTANG") == null ? "" : rs.getString("ID_HUTANG"));
				hutang.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				hutang.put("noPengenalan", rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
				hutang.put("alamat1", rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				hutang.put("alamat2", rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				hutang.put("alamat3", rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				hutang.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				hutang.put("idNegeri", rs.getString("ID_NEGERI") == null ? "9999" : rs.getString("ID_NEGERI"));
				hutang.put("idBandar", rs.getString("ID_BANDAR") == null ? "9999" : rs.getString("ID_BANDAR"));
				hutang.put("noTelefon", rs.getString("NO_TELEFON") == null ? "" : rs.getString("NO_TELEFON"));				
				hutang.put("emel", rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				hutang.put("flag_salin", rs.getString("FLAG_SALIN") == null ? "" : rs.getString("FLAG_SALIN"));
				hutang.put("jenisHutang", rs.getString("JENIS_HUTANG") == null ? "" : rs.getString("JENIS_HUTANG"));
				hutang.put("noAkaun", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));
				hutang.put("butiranHutang", rs.getString("BUTIRAN_HUTANG") == null ? "" : rs.getString("BUTIRAN_HUTANG"));
				hutang.put("nilaiHutang", rs.getString("NILAI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HUTANG"))));
				hutang.put("bakiHutang", rs.getString("BAKI_HUTANG") == null ? "0.00" : Util.formatDecimal(Double.valueOf(rs.getString("BAKI_HUTANG"))));
				hutang.put("statusHutang", rs.getString("STATUS_HUTANG") == null ? "" : rs.getString("STATUS_HUTANG"));
				hutang.put("tarikhTamatTempohHutang", rs.getDate("TARIKH_TAMAT_TEMPOH_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMAT_TEMPOH_HUTANG")));
				hutang.put("tarikhPerjanjian", rs.getDate("TARIKH_PERJANJIAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERJANJIAN")));
				hutang.put("tarikhSelesaiHutang", rs.getDate("TARIKH_SELESAI_HUTANG") == null ? "" : sdf.format(rs.getDate("TARIKH_SELESAI_HUTANG")));
				hutang.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				hutang.put("catatanInsuransHutang", rs.getString("CATATAN_INSURANS") == null ? "" : rs.getString("CATATAN_INSURANS"));
				hutang.put("insuransHutang", rs.getString("FLAG_INSURANS") == null ? "" : rs.getString("FLAG_INSURANS"));
				hutang.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINI") == null ? "" : dtf.format(rs.getTimestamp("TARIKH_KEMASKINI")));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return hutang;
	}
	
	public Hashtable kemaskiniHutang(String idSenaraiHutang, String nama, String noTelefon, String noPengenalan, String alamat1, String alamat2,
			String alamat3, String poskod, String idNegeri, String idBandar, String jenisHutang, String noAkaun,
			String butiranHutang,  String nilaiHutang,  String bakiHutang, String tarikhTamatTempohHutang, String tarikhPerjanjian,
			String catatan, String statusHutang, String tarikhSelesaiHutang, String insuransHutang, 
			String catatanInsuransHutang, HttpSession session) {

		Hashtable hutang = null;
		String sql = "";
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			// TBLPPKSENARAIHUTANG
			r.update("ID_SENARAIHUTANG", idSenaraiHutang);
			r.add("NAMA", nama); //kemaskini1
			r.add("NO_PENGENALAN", noPengenalan);			
			r.add("ALAMAT_1", alamat1);
			r.add("ALAMAT_2", alamat2);
			r.add("ALAMAT_3", alamat3);
			r.add("POSKOD", poskod);
			r.add("NO_TELEFON", noTelefon);
			r.add("ID_NEGERI", idNegeri == "" ? "9999" : idNegeri);
			r.add("ID_BANDAR", idBandar == "" ? "9999" : idBandar);			
			r.add("JENIS_HUTANG", jenisHutang);
			r.add("NO_AKAUN", noAkaun);
			r.add("BUTIRAN_HUTANG", butiranHutang);			
			r.add("NILAI_HUTANG", Utils.RemoveComma(nilaiHutang));
			r.add("BAKI_HUTANG", Utils.RemoveComma(bakiHutang));
			r.add("TARIKH_TAMAT_TEMPOH_HUTANG", r.unquote("to_date('" + tarikhTamatTempohHutang + "','dd/MM/yyyy')"));
			r.add("TARIKH_PERJANJIAN", r.unquote("to_date('" + tarikhPerjanjian + "','dd/MM/yyyy')"));
			r.add("CATATAN", catatan);
			r.add("CATATAN_INSURANS", catatanInsuransHutang);
			r.add("FLAG_INSURANS", insuransHutang);
			r.add("STATUS_HUTANG", statusHutang);
			if (statusHutang.equals("Y")) {
				r.add("TARIKH_SELESAI_HUTANG", r.unquote("to_date('" + tarikhSelesaiHutang + "','dd/MM/yyyy')"));
			} else {
				r.add("TARIKH_SELESAI_HUTANG", "");
			}
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPPKSENARAIHUTANG");
			System.out.println("sqlHUTANG = "+sql);
			stmt.executeUpdate(sql);			
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		hutang = getMaklumatHutang(String.valueOf(idSenaraiHutang));
		return hutang;
	}

	public void hapusHutang(String idSenaraiHutang) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			// TBLPPKDOKUMENSENARAIHUTANG
			r.add("ID_SENARAIHUTANG", idSenaraiHutang);
			sql = r.getSQLDelete("TBLPPKDOKUMENSENARAIHUTANG");
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);

			// TBLPPKSENARAIHUTANG
			r = new SQLRenderer();
			r.add("ID_SENARAIHUTANG", idSenaraiHutang);
			sql = r.getSQLDelete("TBLPPKSENARAIHUTANG");
			System.out.println("sql = "+sql);
			stmt.executeUpdate(sql);
			conn.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean semakanExistPenghutang(String noPengenalanBaru) {
		boolean bool = false;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT UPPER(NO_PENGENALAN_BARU) FROM TBLPPKHUTANG WHERE NO_PENGENALAN_BARU = '"
					+ noPengenalanBaru.toUpperCase() + "'";

			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				bool = true;
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}
	
	public Vector getSenaraiDokumenSokongan(String idSenaraiHutang) {
		String sql = "";
		Vector listDokumenSokongan = null;
		Hashtable h;
		
		try {
			listDokumenSokongan = new Vector();
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN FROM TBLPPKDOKUMENSENARAIHUTANG WHERE ID_SENARAIHUTANG = '" + idSenaraiHutang + "'";
			
			sql = sql + " ORDER BY NAMA_DOKUMEN ASC";	
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN") == null ? "" : rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				
				listDokumenSokongan.addElement(h);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null ) db.close();
		}
		
		return listDokumenSokongan;
	}
	
	public void hapusDokumen(String idDokumen) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPPKDOKUMENSENARAIHUTANG
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPPKDOKUMENSENARAIHUTANG");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String getSqlPenghutang(){
		String sql ="SELECT DISTINCT A.ID_HUTANG, ID_PEJABAT, A.NAMA, A.NO_PENGENALAN_BARU, "+
					" A.ALAMAT_1, A.ALAMAT_2, A.ALAMAT_3, A.POSKOD, B.NAMA_NEGERI, "+
					" C.KETERANGAN AS NAMA_BANDAR, A.NO_TELEFON "+
					" FROM TBLPPKHUTANG A, "+
					" TBLRUJNEGERI B, "+
					" TBLRUJBANDAR C "+
					" WHERE A.NAMA IS NOT NULL "+
					" AND A.ID_NEGERI = B.ID_NEGERI(+) "+
					" AND A.ID_BANDAR = C.ID_BANDAR(+) "+
					" AND A.ID_HUTANG IN (SELECT MAX(ID_HUTANG) AS ID_HUTANG FROM TBLPPKHUTANG  "+
					" GROUP BY NO_PENGENALAN_BARU) ";
		System.out.println("getSqlPenghutang = "+sql);
		return sql;
		
	}
	
	private String getSqlPenghutangAgensi(){
		String sql ="SELECT DISTINCT A.ID_HUTANG, ID_PEJABAT, A.NAMA, A.NO_PENGENALAN_BARU, "+
					" A.ALAMAT_1, A.ALAMAT_2, A.ALAMAT_3, A.POSKOD, B.NAMA_NEGERI, "+
					" C.KETERANGAN AS NAMA_BANDAR, A.NO_TELEFON "+
					" FROM TBLPPKHUTANG A, "+
					" TBLRUJNEGERI B, "+
					" TBLRUJBANDAR C "+
					" WHERE A.NAMA IS NOT NULL "+
					" AND A.ID_NEGERI = B.ID_NEGERI(+) "+
					" AND A.ID_BANDAR = C.ID_BANDAR(+) ";
		System.out.println("getSqlPenghutangAgensi = "+sql);
		return sql;
		
	}
	
	/*
	private String getSqlPenghutang(){
		String sql ="SELECT DISTINCT A.ID_HUTANG, ID_PEJABAT, A.NAMA, A.NO_PENGENALAN_BARU, "+
					" a.alamat_1, a.alamat_2, a.alamat_3, a.poskod, b.nama_negeri, "+
					" c.keterangan AS nama_bandar, a.no_telefon "+
					" , "+
               		"NVL(CASE "+
                	"WHEN S.NO_KP_BARU IS NOT NULL "+
                    " THEN 'Penghutang Merupakan Simati <br/>No.Fail : ' "+
                               "|| s.no_fail "+
                               "|| ' <br/> Status fail : '"+
                               "|| s.keterangan"+
                    " ELSE '' "+
                	" END,'') MATI "+
            		",NVL(CASE "+
                	"WHEN PE.NO_KP_BARU IS NOT NULL "+
                    " THEN 'Penghutang Merupakan Pemohon pusaka <br/>No.Fail : ' "+
                               "|| pe.no_fail "+
                               "|| ' <br/> Status fail : '"+
                               "|| pe.keterangan"+
                    " ELSE '' "+
                    " END,'') PEMOHON,"+
                    " S.PEJABAT,S.ALAMAT1,S.ALAMAT2,S.ALAMAT3,S.POSKOD as POSKODS,S.BANDAR_PEJABAT,S.NEGERI_PEJABAT,S.NO_TEL, S.NO_FAX,"+
                    " PE.PEJABAT AS PEJABAT_1,PE.ALAMAT1 AS ALAMAT1_1,PE.ALAMAT2 AS ALAMAT2_1,PE.ALAMAT3 AS ALAMAT3_1,PE.POSKOD AS POSKODPE,PE.BANDAR_PEJABAT AS BANDAR_PEJABAT_1 ,PE.NEGERI_PEJABAT AS NEGERI_PEJABAT_1, PE.NO_TEL AS NO_TEL_1, PE.NO_FAX AS NO_FAX_1 "+    
                    " FROM TBLPPKHUTANG A, TBLRUJNEGERI B, TBLRUJBANDAR C"+
                    " ,(SELECT S.NO_KP_BARU,st.keterangan,F.NO_FAIL"+
                    " ,PEJ.PEJABAT,PEJ.ALAMAT1,PEJ.ALAMAT2,PEJ.ALAMAT3,PEJ.POSKOD,PEJ.BANDAR_PEJABAT,PEJ.NEGERI_PEJABAT, PEJ.NO_TEL, PEJ.NO_FAX FROM"+
                    " TBLPPKSIMATI S,TBLPPKPERMOHONANSIMATI PS,TBLPPKPERMOHONAN P, TBLPFDFAIL F, tblrujstatus st"+
                    " ,( "+
                    " SELECT REPLACE(REPLACE(TRIM(RJ.KETERANGAN),'&','&#38;'),',') JAWATAN "+
                    " ,REPLACE(REPLACE(TRIM(PT.NAMA_PEJABAT),'&','&#38;'),',') PEJABAT "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT1),'&','&#38;'),',') ALAMAT1 "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT2),'&','&#38;'),',') ALAMAT2 "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT3),'&','&#38;'),',') ALAMAT3 "+
                    " ,PT.POSKOD "+
                    " ,PT.NO_TEL "+
                    " ,PT.NO_FAX "+
                    " ,CASE "+
                    " WHEN TRIM(BPT.KETERANGAN) <> 'TIADA MAKLUMAT' "+
                    " THEN NVL(TRIM(BPT.KETERANGAN),'') "+
                    " ELSE '' "+
                    " END BANDAR_PEJABAT "+
                    " ,NPT.NAMA_NEGERI NEGERI_PEJABAT, RPU.ID_DAERAHURUS "+
                    " ,RPU.ID_JENISPEJABAT "+
                    " FROM TBLRUJPEJABATJKPTG PT "+
                    " ,TBLRUJPEJABATURUSAN RPU "+
                    " ,TBLRUJJAWATAN RJ "+
                    " ,TBLRUJNEGERI NPT "+
                    " ,TBLRUJBANDAR BPT "+
                    " WHERE PT.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG "+
                    " AND PT.ID_JAWATAN = RJ.ID_JAWATAN "+
                    " AND PT.ID_JENISPEJABAT = 22 "+
                    " AND RPU.ID_SEKSYEN = 2 "+
                    " AND PT.ID_NEGERI = NPT.ID_NEGERI(+) "+
                    " AND PT.ID_BANDAR = BPT.ID_BANDAR(+) "+
                    " ) PEJ  "+
                    " WHERE S.ID_SIMATI=PS.ID_SIMATI "+
                    " AND PS.ID_PERMOHONAN = P.ID_PERMOHONAN "+
                    " AND P.ID_FAIL = F.ID_FAIL  "+
                    " AND P.ID_DAERAHMHN = PEJ.ID_DAERAHURUS "+
                    " AND st.id_status = p.id_status"+
                    " ) S "+
                    " , (SELECT PE.NO_KP_BARU,st.keterangan,F.NO_FAIL,PEJ.PEJABAT,PEJ.ALAMAT1,PEJ.ALAMAT2,PEJ.ALAMAT3,PEJ.POSKOD,PEJ.BANDAR_PEJABAT,PEJ.NEGERI_PEJABAT, PEJ.NO_TEL, PEJ.NO_FAX FROM "+ 
                    " TBLPPKPEMOHON PE,TBLPPKPERMOHONAN P, TBLPFDFAIL F, tblrujstatus st "+
                    " ,(SELECT REPLACE(REPLACE(TRIM(RJ.KETERANGAN),'&','&#38;'),',') JAWATAN "+
                    " ,REPLACE(REPLACE(TRIM(PT.NAMA_PEJABAT),'&','&#38;'),',') PEJABAT "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT1),'&','&#38;'),',') ALAMAT1 "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT2),'&','&#38;'),',') ALAMAT2 "+
                    " ,REPLACE(REPLACE(TRIM(PT.ALAMAT3),'&','&#38;'),',') ALAMAT3 "+
                    " ,PT.POSKOD "+
                    " ,PT.NO_TEL "+
                    " ,PT.NO_FAX "+
                    " ,CASE "+
                    " WHEN TRIM(BPT.KETERANGAN) <> 'TIADA MAKLUMAT' "+
                    " THEN NVL(TRIM(BPT.KETERANGAN),'') "+
                    " ELSE '' "+
                    " END BANDAR_PEJABAT "+
                    " ,NPT.NAMA_NEGERI NEGERI_PEJABAT, RPU.ID_DAERAHURUS "+
                    " ,RPU.ID_JENISPEJABAT "+
                    " FROM TBLRUJPEJABATJKPTG PT "+
                    " ,TBLRUJPEJABATURUSAN RPU "+
                    " ,TBLRUJJAWATAN RJ "+
                    " ,TBLRUJNEGERI NPT "+
                    " ,TBLRUJBANDAR BPT "+
                    " WHERE PT.ID_PEJABATJKPTG = RPU.ID_PEJABATJKPTG "+
                    " AND PT.ID_JAWATAN = RJ.ID_JAWATAN "+
                    " AND PT.ID_JENISPEJABAT = 22 "+
                    " AND RPU.ID_SEKSYEN = 2 "+
                    " AND PT.ID_NEGERI = NPT.ID_NEGERI(+) "+
                    " AND PT.ID_BANDAR = BPT.ID_BANDAR(+) "+
                    " ) PEJ  "+
                    " WHERE PE.ID_PEMOHON=P.ID_PEMOHON "+
                    " AND P.ID_FAIL = F.ID_FAIL  "+
                    " AND P.ID_DAERAHMHN = PEJ.ID_DAERAHURUS "+
                    " AND st.id_status = p.id_status "+
                    " ) PE           "+         
                    " WHERE  "+
                    " A.NAMA IS NOT NULL  "+
                    " AND A.ID_NEGERI = B.ID_NEGERI(+)  "+
                    " AND A.ID_BANDAR = C.ID_BANDAR(+) "+
                    " AND A.NO_PENGENALAN_BARU = S.NO_KP_BARU(+) "+
                    " AND A.NO_PENGENALAN_BARU = PE.NO_KP_BARU(+) ";
		System.out.println("sql2 = "+sql);
		return sql;
		
	}
	
	*/
}
