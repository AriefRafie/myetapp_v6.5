package ekptg.model.ppk;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.faraid.EkptgEngine;
import ekptg.helpers.DB;
import ekptg.helpers.Pecahan;
import ekptg.helpers.Utils;

/**
 * 
 *
 * * CHANGES
 * 31/3/2010 - Azam change rs.getInt("BB_SIMATI") to rs.getLong("BB_SIMATI")
 */


public class FrmPerintahSek17Data {
	static Logger myLogger = Logger.getLogger(FrmPerintahSek17Data.class);
	
	private Vector senaraiFail = new Vector();
	private Vector senaraiFail_semakanPerintahHQ = new Vector();
	
	private Vector beanMaklumatPermohonan = new Vector();
	private Vector beanHeaderDetail = new Vector();
	
	private Vector senaraiHTA = new Vector();
	private Vector senaraiHTAPopup = new Vector();
	private Vector beanMaklumatHTA = new Vector();
	private Vector senaraiHTATH = new Vector();
	private Vector beanMaklumatHTATH = new Vector();
	private Vector senaraiHA = new Vector();
	private Vector senaraiHAPopup = new Vector();
	private Vector beanMaklumatHA = new Vector();
	
	private Vector senaraiHTAPT = new Vector();
	private Vector senaraiHAPT = new Vector();
	private Vector senaraiHTAPKT = new Vector();
	private Vector senaraiHAPKT = new Vector();
	private Vector senaraiHTAPL = new Vector();
	private Vector senaraiHAPL = new Vector();
	private Vector senaraiHTAPF = new Vector();
	private Vector senaraiHAPF = new Vector();
	
	private Vector senaraiHTAPTDTL = new Vector();
	private Vector senaraiHAPTDTL = new Vector();
	private Vector senaraiHTAPKTDTL = new Vector();
	private Vector senaraiHAPKTDTL = new Vector();
	private Vector senaraiHTAPFDTL = new Vector();
	private Vector senaraiHAPFDTL = new Vector();
	
	private Vector senaraiHTAPTDTLHilang = new Vector();
	private Vector senaraiHAPTDTLHilang = new Vector();
	
	private Vector beanMaklumatHTAPL = new Vector();
	private Vector beanMaklumatHAPL = new Vector();
	
	private Vector senaraiHTAPKTTerDahulu = new Vector();
	private Vector senaraiHAPKTTerDahulu = new Vector();
	
	private Vector senaraiHTAPATerDahulu = new Vector();
	private Vector senaraiHAPATerDahulu = new Vector();
	
	private Vector senaraiHTAPKTTerDahuluBB = new Vector();
	private Vector senaraiHTAPKTTerDahuluBL = new Vector();
	
	private Vector senaraiHAPKTTerDahuluBB = new Vector();
	private Vector senaraiHAPKTTerDahuluBL = new Vector();
	
	 Vector listHTAdulu = new Vector();	
	 
	 Vector senaraiPembahagianHTAPKTDTL = new Vector();
	
	 public Vector<Hashtable<String,String>> setDataHTAduluPerintah(String idperintah,String idsimati,String bkp,String lp,String bpa,String lpa) throws Exception {
			Db db = null;
			listHTAdulu.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try{
				db = new Db();
				Statement stmt = db.getStatement();


        sql = "SELECT OBMP.ID_PERINTAHHTAOBMST,H.FLAG_KATEGORI_HTA," +
		" (SELECT MAX(RP.KETERANGAN) FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
		" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
		" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "+
		" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
		" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
		" (SELECT MAX(RP.ID_JENISPERINTAH) FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
		" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
		" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "+
		" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
		" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH," +
		" H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, " +
		" H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
		" H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
		" H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
		" H.CATATAN, H.STATUS_PEMILIKAN, " +
		" H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,H.JENIS_HTA," +
		" UPPER(NN.NAMA_NEGERI) AS NAMA_NEGERI,UPPER(DD.NAMA_DAERAH) AS NAMA_DAERAH,UPPER(MM.NAMA_MUKIM) AS NAMA_MUKIM  "+
		" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
		" TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, " +
		" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,TBLRUJNEGERI NN,TBLRUJDAERAH DD,TBLRUJMUKIM MM," +
		" TBLPPKPERINTAHHTAOBMST OBMP   " +
		" WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA AND OBMP.ID_HTA = H.ID_HTA " +
		" AND OBMP.ID_PERINTAH = '"+idperintah+"' AND OBMP.FLAG_HARTA = 'L' " +
		" AND H.ID_NEGERI = NN.ID_NEGERI AND H.ID_DAERAH = DD.ID_DAERAH AND H.ID_MUKIM = MM.ID_MUKIM " +
		" AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
		" AND H.ID_SIMATI = MS.ID_SIMATI  " +
		" AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
		" AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI " +
		" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
//		" AND H.JENIS_HTA = 'Y' " +
		" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
		" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
		" AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
		" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
		" AND S.ID_SIMATI = MS.ID_SIMATI ";



		 if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
		 {
		 sql += " AND H.FLAG_PT = 'Y'";
		 }
		 else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
		 {
		 sql += " AND H.FLAG_PA = 'Y'";
		 }
		 else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
		 {
		 sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
		 }
		 else
		 {
		 sql += "";
		 }

		 sql += " ORDER BY H.ID_HTA DESC";
		 
		 System.out.println("DET HARTA XALIH PERINTAH :"+sql.toUpperCase());
         ResultSet rs = stmt.executeQuery(sql);
		
			Hashtable h;

			while(rs.next()) {
				h = new Hashtable();
				
				h.put("ID_PERINTAHHTAOBMST", rs.getString("ID_PERINTAHHTAOBMST")==null?"":rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("FLAG_KATEGORI_HTA", rs.getString("FLAG_KATEGORI_HTA")==null?"":rs.getString("FLAG_KATEGORI_HTA"));
				h.put("JENIS_HTA", rs.getString("JENIS_HTA")==null?"":rs.getString("JENIS_HTA"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
				h.put("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH")==null?"":rs.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH", rs.getString("JENIS_PERINTAH")==null?"":rs.getString("JENIS_PERINTAH"));
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
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
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("kod_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				listHTAdulu.addElement(h);
			}
			return listHTAdulu;
			}
			finally {
				if(db != null) db.close();
			}

		}
	 
	 
	 Vector listHTAdulu_Pilihan = new Vector();
	 public Vector<Hashtable<String,String>> setDataHTAduluPerintah_Pilihan(String idperintah,String idsimati,String bkp,String lp,String bpa,String lpa) throws Exception {
			Db db = null;
			listHTAdulu_Pilihan.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try{
				db = new Db();
				Statement stmt = db.getStatement();


     sql = "SELECT OBMP.ID_PERINTAHHTAOBMST,H.FLAG_KATEGORI_HTA," +
		" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
		" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
		" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "+
		" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
		" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
		" (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
		" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
		" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HTA = HP.ID_HTA AND SM.ID_PERMOHONANSIMATI = HP.ID_PERMOHONANSIMATI "+
		" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
		" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH," +
		" H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, H.NILAI_HTA_TARIKHMOHON, " +
		" H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
		" H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
		" H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
		" H.CATATAN, H.STATUS_PEMILIKAN, " +
		" H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN,H.JENIS_HTA," +
		" UPPER(NN.NAMA_NEGERI) AS NAMA_NEGERI,UPPER(DD.NAMA_DAERAH) AS NAMA_DAERAH,UPPER(MM.NAMA_MUKIM) AS NAMA_MUKIM  "+
		" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
		" TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, " +
		" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,TBLRUJNEGERI NN,TBLRUJDAERAH DD,TBLRUJMUKIM MM," +
		" TBLPPKPERINTAHHTAOBMST OBMP " +
		//",TBLPPKPILIHANHTA PHTA   " +
		" WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA AND OBMP.ID_HTA = H.ID_HTA " +
		" AND OBMP.ID_PERINTAH = '"+idperintah+"' AND OBMP.FLAG_HARTA = 'L' " +
		" AND H.ID_NEGERI = NN.ID_NEGERI AND H.ID_DAERAH = DD.ID_DAERAH AND H.ID_MUKIM = MM.ID_MUKIM " +
		" AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
		" AND H.ID_SIMATI = MS.ID_SIMATI  " +
		" AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
		" AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI " +
		" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   " +
//		" AND H.JENIS_HTA = 'Y' " +
		" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
		" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
		" AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +
		" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  " +
		" AND S.ID_SIMATI = MS.ID_SIMATI ";
		//"AND PHTA.ID_PERMOHONANSIMATI = '"+idsimati+"'  AND PHTA.ID_HTA = HP.ID_HTA";


		 sql += " ORDER BY H.ID_HTA DESC";
		 
		 myLogger.info("listHTAdulu_Pilihan :"+sql.toUpperCase());
      ResultSet rs = stmt.executeQuery(sql);
		
			Hashtable h;

			while(rs.next()) {
				h = new Hashtable();
				
				h.put("ID_PERINTAHHTAOBMST", rs.getString("ID_PERINTAHHTAOBMST")==null?"":rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("FLAG_KATEGORI_HTA", rs.getString("FLAG_KATEGORI_HTA")==null?"":rs.getString("FLAG_KATEGORI_HTA"));
				h.put("JENIS_HTA", rs.getString("JENIS_HTA")==null?"":rs.getString("JENIS_HTA"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")==null?"":rs.getString("NAMA_NEGERI"));
				h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH")==null?"":rs.getString("NAMA_DAERAH"));
				h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM")==null?"":rs.getString("NAMA_MUKIM"));
				h.put("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH")==null?"":rs.getString("ID_JENISPERINTAH"));
				h.put("JENIS_PERINTAH", rs.getString("JENIS_PERINTAH")==null?"":rs.getString("JENIS_PERINTAH"));
				h.put("idhta", rs.getString("id_Hta")==null?"":rs.getString("id_Hta"));
				h.put("noHakmilik", rs.getString("no_Hakmilik")==null?"":rs.getString("no_Hakmilik"));
				h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
				h.put("nopt", rs.getString("no_Pt")==null?"":rs.getString("no_Pt"));
				h.put("nilai_Hta_memohon", rs.getString("nilai_Hta_Tarikhmohon")==null?"":rs.getDouble("nilai_Hta_Tarikhmohon"));
				h.put("nilai_Hta_mati", rs.getString("nilai_Hta_Tarikhmati")==null?"":rs.getDouble("nilai_Hta_Tarikhmati"));
				h.put("kategori", rs.getString("id_Kategori")==null?"":rs.getString("id_Kategori"));
				h.put("jenishakmilik", rs.getString("id_Jenishm")==null?"":rs.getString("id_Jenishm"));
				h.put("pemilikan", rs.getString("id_Jenispb")==null?"":rs.getString("id_Jenispb"));
				h.put("negeri", rs.getString("id_Negeri")==null?"":rs.getString("id_Negeri"));
				h.put("daerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
				h.put("mukim", rs.getString("id_Mukim")==null?"":rs.getString("id_Mukim"));
				h.put("luashmp", rs.getString("luas_Hmp")==null?"":rs.getString("luas_Hmp"));
				h.put("luasasal", rs.getString("luas")==null?"":rs.getString("luas"));
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
				h.put("flag_pt", rs.getString("FLAG_PT")==null?"":rs.getString("FLAG_PT"));
				h.put("flag_pa", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("flag_s", rs.getString("FLAG_PA")==null?"":rs.getString("FLAG_PA"));
				h.put("kod_hakmilik", rs.getString("KOD_JENIS_HAKMILIK")==null?"":rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("keterangan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				listHTAdulu_Pilihan.addElement(h);
			}
			return listHTAdulu_Pilihan;
			}
			finally {
				if(db != null) db.close();
			}

		}
	 
	 
	 Vector listDataHadulu_Pilihan = new Vector();
	 public Vector<Hashtable<String,String>> setDataHaDulu_Pilihan(String idperintah,String id, String bkp, String lp,
				String bpa, String lpa) throws Exception {
			Db db = null;
			listDataHadulu_Pilihan.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				

				sql = "SELECT OBMP.ID_PERINTAHHAOBMST, " +
				" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
				" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
				" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
				" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
				" (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
				" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
				" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
				" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH," +
						" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
						+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
						+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
						+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
						+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
						+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1," +
								" TBLPPKPERINTAHHAOBMST OBMP " 
								//",TBLPPKPILIHANHA PHA  "
						+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"+id+"' " +
								" AND H.ID_SIMATI = S.ID_SIMATI "
						+ "AND H.ID_JENISHA = J.ID_JENISHA AND OBMP.ID_HA = H.ID_HA " 
						+" AND OBMP.ID_PERINTAH = '"+idperintah+"' AND OBMP.FLAG_HARTA = 'L' " 
						+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
						+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
						+ "AND MS.ID_PERMOHONANSIMATI = '"
						+ id
						+ "' "
						+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
						+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
						+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
						+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET " 
								//" AND  PHA.ID_PERMOHONANSIMATI = '"+id+"' AND PHA.ID_HA = H1.ID_HA "
						+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
				
				sql += "ORDER BY H.ID_HA DESC";

				myLogger.info("LIST HA DULU PILIHAN:" + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);	
					h.put("ID_PERINTAHHAOBMST", rs.getString("ID_PERINTAHHAOBMST")==null?"":rs.getString("ID_PERINTAHHAOBMST"));
					h.put("ID_JENISPERINTAH",
							rs.getString("ID_JENISPERINTAH") == null ? "" : rs
									.getString("ID_JENISPERINTAH"));
					h.put("JENIS_PERINTAH",
							rs.getString("JENIS_PERINTAH") == null ? "" : rs
									.getString("JENIS_PERINTAH"));
					h.put("id_Ha",
							rs.getString("id_Ha") == null ? "" : rs
									.getString("id_Ha"));
					h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
							: rs.getString("id_Jenisha"));
					h.put("id_Simati",
							rs.getString("id_Simati") == null ? "" : rs
									.getString("id_Simati"));
					h.put("nosijil",
							rs.getString("no_sijil") == null ? "" : rs
									.getString("no_sijil"));
					h.put("noDaftar",
							rs.getString("no_Daftar") == null ? "" : rs
									.getString("no_Daftar"));
					h.put("Kod",
							rs.getString("kod") == null ? "" : rs.getString("kod"));

					h.put("alamat1",
							rs.getString("alamat_ha1") == null ? "" : rs
									.getString("alamat_ha1"));
					h.put("alamat2",
							rs.getString("alamat_ha2") == null ? "" : rs
									.getString("alamat_ha2"));
					h.put("alamat3",
							rs.getString("alamat_ha3") == null ? "" : rs
									.getString("alamat_ha3"));
					h.put("poskod",
							rs.getString("poskod") == null ? "" : rs
									.getString("poskod"));

					h.put("Keterangan", rs.getString("keterangan") == null ? ""
							: rs.getString("keterangan"));
					h.put("nilai_tarikhmohon",
							rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
									.getDouble("nilai_ha_tarikhmohon"));
					h.put("nilai_tarikhmati",
							rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
									.getDouble("nilai_ha_tarikhmati"));

					h.put("nama_saham", rs.getString("nama_saham") == null ? ""
							: rs.getString("nama_saham"));
					h.put("jenama",
							rs.getString("jenama") == null ? "" : rs
									.getString("jenama"));

					h.put("butiran",
							rs.getString("butiran") == null ? "" : rs
									.getString("butiran"));

					listDataHadulu_Pilihan.addElement(h);
					bil++;
				}
				 return listDataHadulu_Pilihan;
			} finally {
				if (db != null)
					db.close();
			}
		}
	 
	 Vector listDataHadulu = new Vector();
	 public Vector<Hashtable<String,String>> setDataHaDulu(String idperintah,String id, String bkp, String lp,
				String bpa, String lpa) throws Exception {
			Db db = null;
			listDataHadulu.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				

				sql = "SELECT OBMP.ID_PERINTAHHAOBMST, " +
				" (SELECT RP.KETERANGAN FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
				" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
				" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
				" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS JENIS_PERINTAH," +
				" (SELECT RP.ID_JENISPERINTAH FROM TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERBICARAAN KB,TBLPPKPERINTAH PR, "+
				" TBLPPKPERINTAHHAOBMST OBM,TBLPPKRUJJENISPERINTAH RP "+
				" WHERE P.ID_PERMOHONAN = SM.ID_PERMOHONAN AND KP.ID_PERMOHONAN = P.ID_PERMOHONAN AND OBM.ID_HA = H.ID_HA AND SM.ID_PERMOHONANSIMATI = H1.ID_PERMOHONANSIMATI "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = KB.ID_KEPUTUSANPERMOHONAN AND KB.ID_PERBICARAAN = PR.ID_PERBICARAAN AND PR.ID_PERINTAH = OBM.ID_PERINTAH "+
				" AND RP.ID_JENISPERINTAH = OBM.ID_JENISPERINTAH) AS ID_JENISPERINTAH," +
						" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
						+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
						+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
						+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI "
						+ "FROM TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
						+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1,TBLPPKPERINTAHHAOBMST OBMP  "
						+ "WHERE H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = '"+id+"' " +
								" AND H.ID_SIMATI = S.ID_SIMATI "
						+ "AND H.ID_JENISHA = J.ID_JENISHA AND OBMP.ID_HA = H.ID_HA " 
						+" AND OBMP.ID_PERINTAH = '"+idperintah+"' AND OBMP.FLAG_HARTA = 'L' " 
						+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
						+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
						+ "AND MS.ID_PERMOHONANSIMATI = '"
						+ id
						+ "' "
						+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
						+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
						+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
						+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  "
						+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
				if ((bkp.equals("Y") || lp.equals("Y"))
						&& (bpa.equals("T") && lpa.equals("T"))) {
					sql += " AND H.FLAG_PT = 'Y'";
				} else if ((bkp.equals("T") && lp.equals("T"))
						&& (bpa.equals("Y") || lpa.equals("Y"))) {
					sql += " AND H.FLAG_PA = 'Y'";
				} else if ((bkp.equals("Y") || lp.equals("Y"))
						&& (bpa.equals("Y") || lpa.equals("Y"))) {
					sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
				} else {
					sql += "";
				}
				sql += "ORDER BY H.ID_HA DESC";

				myLogger.info("LIST HA DULU:" + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("bil", bil);	
					h.put("ID_PERINTAHHAOBMST", rs.getString("ID_PERINTAHHAOBMST")==null?"":rs.getString("ID_PERINTAHHAOBMST"));
					h.put("ID_JENISPERINTAH",
							rs.getString("ID_JENISPERINTAH") == null ? "" : rs
									.getString("ID_JENISPERINTAH"));
					h.put("JENIS_PERINTAH",
							rs.getString("JENIS_PERINTAH") == null ? "" : rs
									.getString("JENIS_PERINTAH"));
					h.put("id_Ha",
							rs.getString("id_Ha") == null ? "" : rs
									.getString("id_Ha"));
					h.put("id_Jenisha", rs.getString("id_Jenisha") == null ? ""
							: rs.getString("id_Jenisha"));
					h.put("id_Simati",
							rs.getString("id_Simati") == null ? "" : rs
									.getString("id_Simati"));
					h.put("nosijil",
							rs.getString("no_sijil") == null ? "" : rs
									.getString("no_sijil"));
					h.put("noDaftar",
							rs.getString("no_Daftar") == null ? "" : rs
									.getString("no_Daftar"));
					h.put("Kod",
							rs.getString("kod") == null ? "" : rs.getString("kod"));

					h.put("alamat1",
							rs.getString("alamat_ha1") == null ? "" : rs
									.getString("alamat_ha1"));
					h.put("alamat2",
							rs.getString("alamat_ha2") == null ? "" : rs
									.getString("alamat_ha2"));
					h.put("alamat3",
							rs.getString("alamat_ha3") == null ? "" : rs
									.getString("alamat_ha3"));
					h.put("poskod",
							rs.getString("poskod") == null ? "" : rs
									.getString("poskod"));

					h.put("Keterangan", rs.getString("keterangan") == null ? ""
							: rs.getString("keterangan"));
					h.put("nilai_tarikhmohon",
							rs.getString("nilai_ha_tarikhmohon") == null ? "" : rs
									.getDouble("nilai_ha_tarikhmohon"));
					h.put("nilai_tarikhmati",
							rs.getString("nilai_ha_tarikhmati") == null ? "" : rs
									.getDouble("nilai_ha_tarikhmati"));

					h.put("nama_saham", rs.getString("nama_saham") == null ? ""
							: rs.getString("nama_saham"));
					h.put("jenama",
							rs.getString("jenama") == null ? "" : rs
									.getString("jenama"));

					h.put("butiran",
							rs.getString("butiran") == null ? "" : rs
									.getString("butiran"));

					listDataHadulu.addElement(h);
					bil++;
				}
				 return listDataHadulu;
			} finally {
				if (db != null)
					db.close();
			}
		}

	 
	 Vector listOBHTAdulu = new Vector();
	 public Vector<Hashtable<String,String>> setDataOBHTAdulu(String idperintah,String idsimati,String bkp,String lp,String bpa,String lpa,String jenis_hta) throws Exception {
			Db db = null;
			listOBHTAdulu.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try{
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = " SELECT OBT.ID_PERINTAHHTAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(OB.ID_OB) AS ID_OB,HP.ID_HTA,OB.NAMA_OB,OB.STATUS_OB,OBT.BA,OBT.BB, "+							
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA1,OBT.ID_PA1, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA2,OBT.ID_PA2, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA3,OBT.ID_PA3, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA4,OBT.ID_PA4 "+
				" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
				" TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
				" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ," +
				" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKPERINTAHHTAOBDTL OBT, "+
				" TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
				" WHERE  H.ID_SIMATI = S.ID_SIMATI ";
				
				sql += "AND H.ID_HTA = HP.ID_HTA  AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'  "+  
				" AND H.ID_SIMATI = MS.ID_SIMATI  AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
				" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   ";
				
				if(!jenis_hta.equals(""))
				{
				sql += " AND H.JENIS_HTA = '"+jenis_hta+"' ";
				}
				
				sql +=	" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
				" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
				" AND OBM.ID_PERINTAH = '"+idperintah+"' AND OBM.FLAG_HARTA = 'L' " +
				" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
				" AND H.ID_HTA = OBM.ID_HTA "+
				" AND OBT.ID_PERINTAHHTAOBMST = OBM.ID_PERINTAHHTAOBMST  "+
				" AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idsimati+"' "+
				" AND OBT.ID_OB = OB1.ID_OB AND OBT.ID_OB IS NOT NULL "+
				"  ";							
		
					    if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
					    {
		             sql += " AND H.FLAG_PT = 'Y'";
					    }
		             else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
		             {
		             sql += " AND H.FLAG_PA = 'Y'";
		             }
		             else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
		             {
		             sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
		             }
		             else
		             {
		             sql += "";
		             }
					    
				 //UNION
				  sql += " UNION";
				  
				  sql += " SELECT OBT.ID_PERINTAHHTAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(9999 || H.ID_HTA) AS ID_OB, HP.ID_HTA, 'TIDAK DAPAT DIKESAN' AS NAMA_OB,'' AS STATUS_OB, OBT.BA, OBT.BB, "+							
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA1,OBT.ID_PA1, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA2,OBT.ID_PA2, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA3,OBT.ID_PA3, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA4,OBT.ID_PA4 "+
					" FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
					" TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
					" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ," +
					" TBLPPKPERINTAHHTAOBMST OBM,TBLPPKPERINTAHHTAOBDTL OBT" +
					" WHERE  H.ID_SIMATI = S.ID_SIMATI ";
										
					sql += "AND H.ID_HTA = HP.ID_HTA  AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'  "+  
					" AND H.ID_SIMATI = MS.ID_SIMATI  AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
					" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   ";
					if(!jenis_hta.equals(""))
					{
					sql += " AND H.JENIS_HTA = '"+jenis_hta+"' ";
					}
			        sql += " AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
					" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
					" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
					" AND OBM.ID_PERINTAH = '"+idperintah+"' AND OBM.FLAG_HARTA = 'L' " +
					" AND H.ID_HTA = OBM.ID_HTA "+
					" AND OBT.ID_PERINTAHHTAOBMST = OBM.ID_PERINTAHHTAOBMST AND OBT.ID_OB IS NULL "+
					"  ";							
			
						 if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
						 {
			             sql += " AND H.FLAG_PT = 'Y'";
						 }
			             else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
			             {
			             sql += " AND H.FLAG_PA = 'Y'";
			             }
			             else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
			             {
			             sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			             }
			             else
			             {
			             sql += "";
			             }

				  sql += " ORDER BY ID_OB DESC";
				  
				  myLogger.info("LIST OB HTA DULU :"+sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while(rs.next()) {
				h = new Hashtable();							
				h.put("ID_HTA", rs.getString("ID_HTA")==null?"":rs.getString("ID_HTA"));
				h.put("ID_OB", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("BA", rs.getString("BA")==null?"":rs.getString("BA"));
				h.put("BB", rs.getString("BB")==null?"":rs.getString("BB"));
				
				h.put("ID_PA1", rs.getString("ID_PA1")==null?"":rs.getString("ID_PA1"));
				h.put("ID_PA2", rs.getString("ID_PA2")==null?"":rs.getString("ID_PA2"));
				h.put("ID_PA3", rs.getString("ID_PA3")==null?"":rs.getString("ID_PA3"));
				h.put("ID_PA4", rs.getString("ID_PA4")==null?"":rs.getString("ID_PA4"));
				
				h.put("NAMA_PA1", rs.getString("NAMA_PA1")==null?"":rs.getString("NAMA_PA1"));
				h.put("NAMA_PA2", rs.getString("NAMA_PA2")==null?"":rs.getString("NAMA_PA2"));
				h.put("NAMA_PA3", rs.getString("NAMA_PA3")==null?"":rs.getString("NAMA_PA3"));
				h.put("NAMA_PA4", rs.getString("NAMA_PA4")==null?"":rs.getString("NAMA_PA4"));
				
				h.put("STATUS_OB", rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));	
				
				h.put("STATUS_TADBIR", rs.getString("STATUS_TADBIR")==null?"":rs.getString("STATUS_TADBIR"));
				
				h.put("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL")==null?"":rs.getString("ID_PERINTAHHTAOBDTL"));
				
				listOBHTAdulu.addElement(h);
			}
			return listOBHTAdulu;
			}
			finally {
				if(db != null) db.close();
			}

		}
	 
	 Vector listOBHAdulu = new Vector();
	 public Vector<Hashtable<String,String>> setDataOBHAdulu(String idperintah,String idsimati,String bkp,String lp,String bpa,String lpa,String jenis_hta) throws Exception {
			Db db = null;
			listOBHAdulu.clear();
			String sql = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try{
				db = new Db();
				Statement stmt = db.getStatement();


				sql = " SELECT OBT.ID_PERINTAHHAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(OB.ID_OB) AS ID_OB,HP.ID_HA,OB.NAMA_OB,OB.STATUS_OB,OBT.BA,OBT.BB, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA1,OBT.ID_PA1, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA2,OBT.ID_PA2, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA3,OBT.ID_PA3, "+
				" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
				" AS NAMA_PA4,OBT.ID_PA4 "+
				" FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, " +
				" TBLPPKPERMOHONANSIMATI MS, " +
				" TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
				" TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM," +
				" TBLPPKPERINTAHHAOBDTL OBT, "+
				" TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
				" WHERE " +
				//" OBM.ID_JENISPERINTAH IN (1,7) AND " +
				"H.ID_SIMATI = S.ID_SIMATI AND H.ID_HA = HP.ID_HA  " +
				" AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'  "+  
				" AND H.ID_SIMATI = MS.ID_SIMATI  " +
				" AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
				" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   "+
				" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
				" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
				" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
				" AND H.ID_HA = OBM.ID_HA "+
				" AND OBM.ID_PERINTAH = '"+idperintah+"' AND OBM.FLAG_HARTA = 'L' " +
				" AND OBT.ID_PERINTAHHAOBMST = OBM.ID_PERINTAHHAOBMST  "+
				" AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idsimati+"' "+
				" AND OBT.ID_OB = OB1.ID_OB "+
				//" AND OBT.STATUS_TADBIR IN ('Y','T') " +
				"";							
		
					    if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
					    {
		             sql += " AND H.FLAG_PT = 'Y'";
					    }
		             else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
		             {
		             sql += " AND H.FLAG_PA = 'Y'";
		             }
		             else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
		             {
		             sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
		             }
		             else
		             {
		             sql += "";
		             }

				 // sql += " ORDER BY OB.ID_OB DESC";
					    sql += " UNION ";
				  
				//  sql += " SELECT OBT.STATUS_TADBIR,OB.ID_OB,HP.ID_HA,OB.NAMA_OB,OBT.BA,OBT.BB, "+
				  sql += " SELECT OBT.ID_PERINTAHHAOBDTL,OBT.STATUS_TADBIR,TO_CHAR(9999 || HP.ID_HA) AS ID_OB, HP.ID_HA, 'TIDAK DAPAT DIKESAN' AS NAMA_OB,'' AS STATUS_OB, OBT.BA, OBT.BB, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA1 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA1,OBT.ID_PA1, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA2 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA2,OBT.ID_PA2, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA3 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA3,OBT.ID_PA3, "+
					" (SELECT NAMA_OB FROM TBLPPKOBPERMOHONAN WHERE ID_OB = OBT.ID_PA4 AND ID_PERMOHONANSIMATI = '"+idsimati+"') "+
					" AS NAMA_PA4,OBT.ID_PA4 "+
					" FROM TBLPPKHA HP,TBLPPKHAPERMOHONAN H, TBLPPKSIMATI S, " +
					" TBLPPKPERMOHONANSIMATI MS, " +
					" TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,  "+
					" TBLPPKPERMOHONANSIMATI MS1, TBLPPKPERINTAHHAOBMST OBM," +
					" TBLPPKPERINTAHHAOBDTL OBT" +
					//", "+
					//" TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB  "+
					" WHERE " +
					//"OBM.ID_JENISPERINTAH IN (1,7) AND " +
					"  H.ID_SIMATI = S.ID_SIMATI AND H.ID_HA = HP.ID_HA  " +
					" AND H.ID_PERMOHONANSIMATI = '"+idsimati+"'  "+  
					" AND H.ID_SIMATI = MS.ID_SIMATI  " +
					" AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI "+ 
					" AND MS.ID_PERMOHONANSIMATI = '"+idsimati+"'   "+
					" AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "+
					" AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "+
					" AND P1.NO_SUBJAKET < P.NO_SUBJAKET  AND S.ID_SIMATI = MS.ID_SIMATI "+
					" AND H.ID_HA = OBM.ID_HA "+
					" AND OBM.ID_PERINTAH = '"+idperintah+"' AND OBM.FLAG_HARTA = 'L' " +
					" AND OBT.ID_PERINTAHHAOBMST = OBM.ID_PERINTAHHAOBMST AND OBT.ID_OB IS NULL "+
					//" AND OB.ID_OB =  OB1.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idsimati+"' "+
					//" AND OBT.ID_OB = OB1.ID_OB "+
					//" AND OBT.STATUS_TADBIR IN ('Y','T') " +
					"";							
			
						    if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("T") && lpa.equals("T")))
						    {
			             sql += " AND H.FLAG_PT = 'Y'";
						    }
			             else if((bkp.equals("T") && lp.equals("T")) && (bpa.equals("Y") || lpa.equals("Y")))
			             {
			             sql += " AND H.FLAG_PA = 'Y'";
			             }
			             else if((bkp.equals("Y") || lp.equals("Y")) && (bpa.equals("Y") || lpa.equals("Y")))
			             {
			             sql += " AND (H.FLAG_PA = 'Y' OR H.FLAG_PT = 'Y') ";
			             }
			             else
			             {
			             sql += "";
			             }

					  sql += " ORDER BY ID_OB DESC";

				  
				  myLogger.info("LIST OB HA PERINTAH :"+sql.toUpperCase());

			//System.out.println("HTAAM :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			//
			Hashtable h;
			
			//OB.NAMA_OB,OBT.BA,OBT.BB

			while(rs.next()) {
				h = new Hashtable();							
				h.put("ID_HA", rs.getString("ID_HA")==null?"":rs.getString("ID_HA"));
				h.put("ID_OB", rs.getString("ID_OB")==null?"":rs.getString("ID_OB"));
				h.put("NAMA_OB", rs.getString("NAMA_OB")==null?"":rs.getString("NAMA_OB"));
				h.put("BA", rs.getString("BA")==null?"":rs.getString("BA"));
				h.put("BB", rs.getString("BB")==null?"":rs.getString("BB"));
				h.put("STATUS_TADBIR", rs.getString("STATUS_TADBIR")==null?"":rs.getString("STATUS_TADBIR"));
				
				h.put("ID_PA1", rs.getString("ID_PA1")==null?"":rs.getString("ID_PA1"));
				h.put("ID_PA2", rs.getString("ID_PA2")==null?"":rs.getString("ID_PA2"));
				h.put("ID_PA3", rs.getString("ID_PA3")==null?"":rs.getString("ID_PA3"));
				h.put("ID_PA4", rs.getString("ID_PA4")==null?"":rs.getString("ID_PA4"));
				
				h.put("NAMA_PA1", rs.getString("NAMA_PA1")==null?"":rs.getString("NAMA_PA1"));
				h.put("NAMA_PA2", rs.getString("NAMA_PA2")==null?"":rs.getString("NAMA_PA2"));
				h.put("NAMA_PA3", rs.getString("NAMA_PA3")==null?"":rs.getString("NAMA_PA3"));
				h.put("NAMA_PA4", rs.getString("NAMA_PA4")==null?"":rs.getString("NAMA_PA4"));	
				
				h.put("STATUS_OB", rs.getString("STATUS_OB")==null?"":rs.getString("STATUS_OB"));
				
				h.put("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL")==null?"":rs.getString("ID_PERINTAHHAOBDTL"));	
				
				listOBHAdulu.addElement(h);
			}
			return listOBHAdulu;
			}
			finally {
				if(db != null) db.close();
			}

		}


	@SuppressWarnings("unchecked")
	public void carianFail(String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFail.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
				+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS"
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJSUBURUSANSTATUS F, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE J.ID_DAERAH IN (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = " + userId + " ";
				
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
				//+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 8 AND B.FLAG_JENIS_PERMOHONAN = 1 AND A.FLAG_JENIS_FAIL = 1 AND B.ID_STATUS != 999";				
				+ " AND G.ID_STATUS = F.ID_STATUS AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH AND E.AKTIF = 1 AND F.ID_STATUS IN (41,21,25) AND B.SEKSYEN = 17 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS != 999";
				
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.toUpperCase() + "'|| '%'";
				}
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.toUpperCase() + "'|| '%'";
				}
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							if (jenisKp.equals("1")){
								sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("2")){
								sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("3")){
								sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
						}
					}
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				senaraiFail.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonanSimati", "");
		    	h.put("idPermohonan", "");
		    	h.put("idFail", "");
		    	h.put("noFail", "Tiada Rekod");
		    	h.put("tarikhMohon","");
		    	h.put("tarikhMasuk","");
		    	h.put("keterangan","");
		    	h.put("idSimati", "");
		    	h.put("namaSimati", "");
		    	h.put("idStatus", "");
				senaraiFail.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void carianFail_semakanPerintahHQ(String role,String noFail, String namaPemohon, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		senaraiFail_semakanPerintahHQ.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT DISTINCT B.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, H.ID_PERMOHONANSIMATI, B.ID_PERMOHONAN, B.TARIKH_MOHON, B.TARIKH_MASUK, C.ID_SIMATI,"
				+ " C.NAMA_SIMATI, D.ID_PEMOHON, D.NAMA_PEMOHON, D.NO_KP_BARU, D.NO_KP_LAMA, D.NO_KP_LAIN, G.KETERANGAN, G.ID_STATUS"
				+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C, TBLPPKPEMOHON D, TBLRUJSTATUS G, TBLPPKPERMOHONANSIMATI H, TBLRUJNEGERI I, TBLRUJDAERAH J" 
				+ " WHERE  A.ID_FAIL = B.ID_FAIL AND H.ID_PERMOHONAN = B.ID_PERMOHONAN AND H.ID_SIMATI = C.ID_SIMATI " +
						" AND D.ID_PEMOHON = B.ID_PEMOHON "
				+ " AND B.ID_NEGERIMHN = I.ID_NEGERI AND B.ID_DAERAHMHN = J.ID_DAERAH " +
						" AND B.ID_STATUS = G.ID_STATUS AND B.SEKSYEN = 17 AND B.FLAG_JENIS_PERMOHONAN = 1 AND B.ID_STATUS = '21' AND B.ID_STATUS != 999";
				
			boolean setLimit = true;
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
				
			}
			
			//namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.toUpperCase() + "'|| '%'";
				}
				setLimit = false;
			}
			
			//namaSimati
			if (namaSimati != null) {
				if (!namaSimati.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(C.NAMA_SIMATI) LIKE '%' ||'"
							+ namaSimati.toUpperCase() + "'|| '%'";
				}
				
			}
			
			//jenisKp & noKp
			if (jenisKp != null) {
				if (!jenisKp.trim().equals("")) {
					if (noKp != null) {
						if (!noKp.trim().equals("")) {
							if (jenisKp.equals("1")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_BARU) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("2")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_LAMA) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
							else if (jenisKp.equals("3")){
								setLimit = false;
								sql = sql + " AND UPPER(D.NO_KP_LAIN) LIKE '%' ||'" + noKp.toUpperCase() + "'|| '%'";
							}
						}
					}
				}
				
			}
				
			myLogger.info("setLimit :"+setLimit);
			if (setLimit == true) 
			{
			sql = sql + " AND ROWNUM <= 50 ";
			}
			myLogger.info("SENARAI PERINTAH SEK17 HQ"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPermohonanSimati", rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhMohon", rs.getString("TARIKH_MOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHON")));
				h.put("tarikhMasuk", rs.getString("TARIKH_MASUK") == null ? "" : sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("keterangan",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idSimati", rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				senaraiFail_semakanPerintahHQ.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPermohonanSimati", "");
		    	h.put("idPermohonan", "");
		    	h.put("idFail", "");
		    	h.put("noFail", "Tiada Rekod");
		    	h.put("tarikhMohon","");
		    	h.put("tarikhMasuk","");
		    	h.put("keterangan","");
		    	h.put("idSimati", "");
		    	h.put("namaSimati", "");
		    	h.put("idStatus", "");
		    	senaraiFail_semakanPerintahHQ.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatPermohonan(String idPermohonan, HttpSession session) throws Exception {
		
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String userId = session.getAttribute("_ekptg_user_id").toString();
		beanMaklumatPermohonan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql =  "SELECT distinct pm.id_negeri, n.id_Negeri, n.nama_Negeri,f.id_Fail, f.no_Fail, d.id_Daerah, p.id_Permohonan, "; 
			sql += "p.tarikh_Mohon, s.no_Kp_Baru, s.no_Kp_Lama, s.jenis_Kp, s.no_Kp_Lain, s.id_Simati, ";
			sql += "s.nama_Simati, s.tarikh_Mati, pm.id_Pemohon, pm.nama_Pemohon, pm.no_kp_baru, ";
			sql += "pm.no_kP_lama, pm.jenis_Kp, pm.no_Kp_lain, pm.alamat_1, pm.alamat_2, pm.alamat_3, "; 
			sql += "pm.poskod, pm.bandar, d.nama_Daerah, p.seksyen, st.keterangan, ";
			sql += "p.id_Status, u.nama_pejabat, mosi.id_Permohonansimati, s.umur, s.jantina, "; 
			sql += "p.batal_kuasa_pentadbir, p.lantik_pentadbir, p.batal_p_amanah, p.lantik_p_amanah,p.harta_tinggal, ";
			sql += "pm.umur, pm.jantina,u.id_pejabatjkptg, p.no_subjaket, sstf.id_suburusanstatusfail, daerahPejabat.nama_daerah as daerah_pejabat,u.id_negeri as id_negeripejabat ";
			sql += "FROM Tblpfdfail f,Tblppkpermohonan p, Tblrujnegeri n, Tblrujdaerah d, Tblppksimati s, "; 
			sql += "Tblppkpemohon pm, Tblrujstatus st, tblrujsuburusanstatusfail sstf, tblrujsuburusanstatus sst, "; 
			sql += "tblrujpejabatjkptg u, Tblppkpermohonansimati mosi, Users_Internal ur, Tblrujdaerah daerahPejabat ";
			sql += "WHERE f.id_Negeri = n.id_Negeri(+) ";
			sql += "AND sstf.id_permohonan = p.id_permohonan ";
			sql += "AND sstf.id_suburusanstatus = sst.id_suburusanstatus ";
			sql += "AND sst.id_status = st.id_status ";
			sql += "AND p.id_Daerahmhn = d.id_Daerah(+) ";  
			sql += "AND ur.user_id = "+userId ;
			sql += "And ur.ID_PEJABATJKPTG = u.ID_PEJABATJKPTG "; 
			sql += "AND p.id_Fail = f.id_Fail ";
			sql += "AND pm.id_pemohon = p.id_pemohon ";   
			sql += "AND s.id_Simati = mosi.id_Simati ";
			sql += "AND p.id_Permohonan = mosi.id_Permohonan ";   
			sql += "AND st.id_Status = p.id_Status ";
			sql += "AND d.id_daerah = p.id_daerahmhn "; 
			sql += "AND u.id_daerah = daerahPejabat.id_daerah "; 
		//	sql += "AND sstf.aktif = 1 ";
			sql += " AND p.id_Permohonan = " +idPermohonan ;
			
			ResultSet rs = stmt.executeQuery(sql);
			
		    Hashtable h;
			
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
		    	  h.put("idFail", rs.getString("id_Fail")==null?"":rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail")==null?"":rs.getString("no_Fail"));
		    	  h.put("idDaerah", rs.getString("id_Daerah")==null?"":rs.getString("id_Daerah"));
		    	  h.put("idPermohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
		    	  h.put("tarikhMohon", rs.getDate("tarikh_Mohon")==null?"":sdf.format(rs.getDate("tarikh_Mohon")));
		    	  h.put("idSimati", rs.getString("id_Simati")==null?"":rs.getString("id_Simati"));
		    	  h.put("namaSimati", rs.getString("nama_Simati")==null?"":rs.getString("nama_Simati"));
		    	  h.put("tarikhMati",  rs.getDate("tarikh_Mati")==null?"": sdf.format(rs.getDate("tarikh_Mati")));
		    	  h.put("idPemohon", rs.getString("id_Pemohon")==null?"":rs.getString("id_Pemohon"));
		    	  h.put("namaPemohon", rs.getString("nama_Pemohon")==null?"":rs.getString("nama_Pemohon"));
		    	  h.put("alamat1", rs.getString("alamat_1")==null?"":rs.getString("alamat_1"));
		    	  h.put("alamat2", rs.getString("alamat_2")==null?"":rs.getString("alamat_2"));
		    	  h.put("alamat3", rs.getString("alamat_3")==null?"":rs.getString("alamat_3"));
		    	  h.put("poskod", rs.getString("poskod")==null?"":rs.getString("poskod"));
		    	  h.put("bandar", rs.getString("bandar")==null?"":rs.getString("bandar"));
		    	  h.put("idnegeri", rs.getString(16)==null?"":rs.getString(16));
		    	  h.put("namanegeri", rs.getString("nama_Negeri")==null?"":rs.getString("nama_Negeri"));
		    	  h.put("namadaerah", rs.getString("nama_Daerah")==null?"":rs.getString("nama_Daerah"));
		    	  h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
		    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
		    	  h.put("id_Status", rs.getString("id_Status")==null?"":rs.getString("id_Status"));
		    	  h.put("hartaTertinggal", rs.getString("harta_tinggal")==null?"":rs.getString("harta_tinggal"));
		    	  h.put("batalPT", rs.getString("batal_kuasa_pentadbir")==null?"":rs.getString("batal_kuasa_pentadbir"));
		    	  h.put("lantikPT", rs.getString("lantik_pentadbir")==null?"":rs.getString("lantik_pentadbir"));
		    	  h.put("batalPA", rs.getString("batal_p_amanah")==null?"":rs.getString("batal_p_amanah"));
		    	  h.put("lantikPA", rs.getString("lantik_p_amanah")==null?"":rs.getString("lantik_p_amanah"));
		    	  
		    	  if(rs.getString("id_negeripejabat").equals("7")){
		    		  h.put("namaPejabat", rs.getString("nama_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  }
		    	  else{
		    		  h.put("namaPejabat", rs.getString("nama_pejabat")==null && rs.getString("daerah_pejabat")==null?"":rs.getString("nama_pejabat") + ", " + rs.getString("daerah_pejabat"));
		    	  }
//		    	  h.put("namaPejabat", rs.getString("nama_pejabat")==null && rs.getString("daerah_pejabat")==null?"":rs.getString("nama_pejabat"));
		    	  //h.put("pmidnegeri", rs.getString(23)==null?"0":rs.getString(23));
		    	  h.put("id_pejabatjkptg", rs.getString("id_pejabatjkptg"));
		    	  //h.put("pmNama_negeri", rs.getString(24)==null?"":rs.getString(24));
		    	  if(rs.getString(3) == null || rs.getString(3) ==""){
			    		h.put("pmNama_negeri","");
			    	}else{
			    		h.put("pmNama_negeri",rs.getString(3));
			    	}
		    	  if(rs.getString(2) == null || rs.getString(2) ==""){
			    		h.put("pmidnegeri","0");
			    	}else{
			    		h.put("pmidnegeri",rs.getString(2));
			    	}
		    	  
		    	  beanMaklumatPermohonan.addElement(h);
		      	}      
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPerintah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERMOHONAN");
			r.add("C.ID_PERINTAH");

			r.add("A.ID_KEPUTUSANPERMOHONAN",r.unquote("B.ID_KEPUTUSANPERMOHONAN"));
			r.add("B.ID_PERBICARAAN",r.unquote("C.ID_PERBICARAAN"));
			
			r.add("A.ID_PERMOHONAN", idPermohonan);
			r.add("C.FLAG_JENIS_KEPUTUSAN", "0");

			sql = r.getSQLSelect("TBLPPKKEPUTUSANPERMOHONAN A, TBLPPKPERBICARAAN B, TBLPPKPERINTAH C");
			
			ResultSet rs = stmt.executeQuery(sql);


			if (rs.next()){
				return rs.getString("ID_PERINTAH").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getTarikhPerintah(String idPerintah) throws Exception {
		Db db = null;
		String a = "";
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT COUNT(TARIKH_PERINTAH) AS BIL FROM TBLPPKPERINTAH WHERE ID_PERINTAH = "+idPerintah + " AND TARIKH_PERINTAH > TO_DATE('09/10/2017','dd/mm/yyyy')";
			myLogger.info("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
				{				
					if (rs.getInt("BIL") > 0) {
						a = "T";
					}

				}

			
			
		} finally {
			if (db != null)
				db.close();
		}
		return a;
	}
	
	public String getIdPerbicaraan(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.ID_PERBICARAAN");
			
			r.add("C.ID_PERINTAH", idPerintah);
			r.add("C.FLAG_JENIS_KEPUTUSAN", "0");

			sql = r.getSQLSelect("TBLPPKPERINTAH C");
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_PERBICARAAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getCatatanPerintah(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("CATATAN");
			
			r.add("ID_PERINTAH", idPerintah);
			
			sql = r.getSQLSelect("TBLPPKPERINTAH");
			myLogger.info("sql getCatatanPerintah = "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				if (rs.getString("CATATAN") != null){
					return rs.getString("CATATAN").toString();
				} else {
					return "";
				}
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTA(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT HTA1.ID_HTA FROM TBLPPKHTA HTA1,TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_HTA = HTA1.ID_HTA " +
					" AND HTA.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI " +
					" AND HTA1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
							" AND HTA.JENIS_HTA = 'Y'"
				+ " UNION"
				+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, " +
						" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
						" WHERE A.ID_HTA = B1.ID_HTA " +
						" AND B.ID_HTA = B1.ID_HTA AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND A.ID_PERINTAH = '" + idPerintah + "' " +
								" AND A.FLAG_HARTA = 'L'"
				+ " AND A.FLAG_BATAL = 'BB' AND B.JENIS_HTA = 'Y'"
				+ " UNION"
				+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
						"WHERE B.ID_HTA = B1.ID_HTA AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND A.ID_HTA = B1.ID_HTA " +
						" AND A.ID_PERINTAH = '" + idPerintah + "' AND A.FLAG_HARTA = 'L'"
				+ " AND A.FLAG_BATAL = 'BL' AND B.JENIS_HTA = 'Y'";
			myLogger.info("checkExistHTA :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTATH(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT HTA1.ID_HTA FROM TBLPPKHTA HTA1,TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_HTA = HTA1.ID_HTA " +
					" AND HTA1.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " +
					" AND HTA1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND HTA.JENIS_HTA = 'T'"
					+ " UNION"
					+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
							" WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
							" AND A.ID_HTA = B1.ID_HTA AND A.ID_PERINTAH = '" + idPerintah + "' " +
									" AND A.FLAG_HARTA = 'L'"
					+ " AND A.FLAG_BATAL = 'BB' AND B.JENIS_HTA = 'T'"
					+ " UNION"
					+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
							" WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
									" AND A.ID_HTA = B1.ID_HTA AND A.ID_PERINTAH = '" + idPerintah + "' " +
											" AND A.FLAG_HARTA = 'L'"
					+ " AND A.FLAG_BATAL = 'BL' AND B.JENIS_HTA = 'T'";
					
			myLogger.info("checkExistHTATH :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHA(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A1.ID_HA = A.ID_HA AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'"
					+ " UNION"
					+ " SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'L'"
					+ " AND FLAG_BATAL = 'BB'"
					+ " UNION"
					+ " SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'L'"
					+ " AND FLAG_BATAL = 'BL'";
	
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void registerHTATHIntoPKT(String idPerintah, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlInsert = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT HTA1.ID_HTA " +
					" FROM TBLPPKHTA HTA1,TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_HTA = HTA1.ID_HTA " +
					" AND HTA1.ID_PERMOHONANSIMATI = HTA.ID_PERMOHONANSIMATI " +
					" AND HTA.JENIS_HTA = 'T' " +
					" AND HTA1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
							" AND HTA1.ID_HTA NOT IN "
				+ "(SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')";
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){

				SQLRenderer r = new SQLRenderer();
								
				//TBLPPKPERINTAHHTAOBMST
				long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_HTA", rs.getString("ID_HTA"));
				r.add("ID_PERINTAH", idPerintah);
				r.add("ID_JENISPERINTAH", "2");
				r.add("FLAG_HARTA", "B");
				
				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
				stmt.executeUpdate(sqlInsert);
				
				//INSERT PENTADBIR
				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(idPerintahHTAOBMST,hash.get("idOB").toString(),session);
				}
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHTAPerintahKuasaTadbir(long idPerintahHTAOBMST, String idOB, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private Vector getPentadbir(String idPerintah) throws Exception{
		
		Db db = null;
		Vector list = new Vector();
		list.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT DISTINCT(A.ID_PA1) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA2) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA3) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA4) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA1) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA2) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA3) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_PA4) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND A.STATUS_TADBIR = 'Y' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = '2' AND B.FLAG_HARTA = 'B'  AND B.ID_PERINTAH = '" + idPerintah + "'"
					+ " UNION" 
					+ " SELECT DISTINCT(A.ID_OB) AS ID_OB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = '2' AND B.FLAG_HARTA = 'B' AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if (rs.getString("ID_OB") != null){
					if (rs.getString("ID_OB").trim().length() > 0){
						h = new Hashtable();
						h.put("idOB", rs.getString("ID_OB"));
						list.addElement(h);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return list;
	}
	
	private Vector getPemegangAmanah(String idOB) throws Exception{
		
		Db db = null;
		Vector list = new Vector();
		list.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_OB FROM TBLPPKPENJAGA WHERE ID_OBMINOR = '" + idOB + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if (rs.getString("ID_OB") != null){
					if (rs.getString("ID_OB").trim().length() > 0){
						h = new Hashtable();
						h.put("idOB", rs.getString("ID_OB"));
						list.addElement(h);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTA(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("G.KETERANGAN");
			r.add("A.CATATAN");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");

			
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("B.JENIS_HTA", "Y");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("B1.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("A.ID_HTA",r.unquote("B1.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G","A.ID_PERINTAHHTAOBMST ASC");
			*/
			//comment on 29/07/2012
			
			
			sql = "SELECT   A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, "+
		    " D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, G.KETERANGAN, A.CATATAN, "+
			" B.BA_SIMATI, B.BB_SIMATI" +
			/*", "+        
			" (SELECT COUNT(*) "+
			" FROM TBLPPKHTA HTA1, TBLPPKHTAPERMOHONAN HTA "+
			" WHERE HTA.ID_HTA = HTA1.ID_HTA AND HTA1.ID_HTA = B1.ID_HTA "+
			" AND HTA.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI "+
			" AND HTA1.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
			" AND HTA.JENIS_HTA = 'Y' "+
			" AND HTA1.ID_HTA NOT IN ( "+
			" SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '"+idPerintah+"') "+
			" UNION "+
			" SELECT A.ID_HTA "+
			" FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B "+
			" WHERE A.ID_HTA = B1.ID_HTA "+
			" AND B.ID_HTA = B1.ID_HTA "+
			" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
			" AND A.ID_PERINTAH = '"+idPerintah+"'  "+
			" AND A.FLAG_HARTA = 'L' "+
			" AND A.FLAG_BATAL = 'BB' "+
			" AND B.JENIS_HTA = 'Y' "+
			" AND B1.ID_HTA NOT IN (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '"+idPerintah+"' " +
			" AND FLAG_HARTA = 'B')) AS STATUS_DAFTAR "+  */       
			" FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C, "+
			" TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G "+
			" WHERE B.ID_NEGERI = D.ID_NEGERI "+
			" AND B.ID_DAERAH = E.ID_DAERAH "+
			" AND B.ID_MUKIM = F.ID_MUKIM "+
			" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH "+
			" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) "+
			" AND A.ID_PERINTAH = '"+idPerintah+"' "+
			" AND B.JENIS_HTA = 'Y' "+
			" AND A.FLAG_HARTA = 'B' "+
			" AND B1.ID_HTA = B.ID_HTA "+
			" AND A.ID_HTA = B1.ID_HTA "+
			" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' "+
			" ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			myLogger.info("PERINTAH setDataSenaraiHTA( :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
						rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
								+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				
				//h.put("status_daftar",rs.getString("STATUS_DAFTAR") == null ? "" : rs.getString("STATUS_DAFTAR"));
				//baru : check harta ni dah didaftarkan ker bulum
				//silap buat...flag ni xrelevan kat sini..tp biar dlu
				senaraiHTA.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("bahagianSimati","");
		    	senaraiHTA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPopup(String idPermohonanSimati, String idPerintah) throws Exception {
		Db db = null;
		senaraiHTAPopup.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, A.JENIS_HTA"
				+ " FROM TBLPPKHTA A1, TBLPPKHTAPERMOHONAN A, " +
						" TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E"
				+ " WHERE A1.ID_HTA = A.ID_HTA " +
						" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
						" AND A.ID_JENISHM = B.ID_JENISHAKMILIK " +
						" AND A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH " +
						" AND A.ID_MUKIM = E.ID_MUKIM" 
				+ " AND A.JENIS_HTA = 'Y' " +
						"AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			
			//idPerintah
			if (idPerintah != null) {
				if (!idPerintah.trim().equals("")) {
					sql = sql + " AND A1.ID_HTA NOT IN (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')"
						+ " UNION"
						+ " SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, A.JENIS_HTA"
						+ " FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A, " +
								" TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, TBLRUJDAERAH D, " +
								" TBLRUJMUKIM E " +
								" WHERE A1.ID_HTA = A.ID_HTA " +
								//" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
								//" AND A1.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND A.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
										" AND A.ID_JENISHM = B.ID_JENISHAKMILIK"
						+ " AND A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM AND A.JENIS_HTA = 'Y' AND"
						+ " A1.ID_HTA IN " +
								" (SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, " +
								" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
								" WHERE B1.ID_HTA = B.ID_HTA " +
								" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
										" AND A.ID_HTA = B1.ID_HTA AND B.JENIS_HTA = 'Y'"
						+ " AND A.FLAG_HARTA = 'L' AND A.FLAG_BATAL = 'BB' AND A.ID_PERINTAH = '" + idPerintah + "' " +
								" AND A.ID_HTA NOT IN"
						+ " (SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
								" WHERE B1.ID_HTA = B.ID_HTA " +
								" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'" +
										" AND A.ID_HTA = B1.ID_HTA AND B.JENIS_HTA = 'Y' " +
										" AND A.FLAG_HARTA = 'B' AND A.ID_PERINTAH = '" + idPerintah + "'))";
				}
			}
			myLogger.info("setDataSenaraiHTAPopup(:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;			

			while (rs.next()) {
				h = new Hashtable();	
				
				h.put("bil", bil);
				h.put("idHTA",rs.getString("ID_HTA"));
				h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
						rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
								+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				senaraiHTAPopup.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idHTA", "");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	senaraiHTAPopup.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTATHPopup(String idPermohonanSimati, String idPerintah) throws Exception {
		Db db = null;
		senaraiHTAPopup.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, A.JENIS_HTA"
				+ " FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A, " +
						" TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E"
				+ " WHERE A1.ID_HTA = A.ID_HTA " +
						" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
						" AND A.ID_JENISHM = B.ID_JENISHAKMILIK(+) AND A.ID_NEGERI = C.ID_NEGERI " +
						" AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM" 
				+ " AND A.JENIS_HTA = 'T' AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			
			//idPerintah
			if (idPerintah != null) {
				if (!idPerintah.trim().equals("")) {
					sql = sql + " AND A1.ID_HTA NOT IN (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')"
						+ " UNION"
						+ " SELECT A.ID_HTA, A.NO_HAKMILIK, B.KOD_JENIS_HAKMILIK, C.NAMA_NEGERI, D.NAMA_DAERAH, E.NAMA_MUKIM, A.ID_SIMATI, A.JENIS_HTA"
						+ " FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A, " +
								" TBLRUJJENISHAKMILIK B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E " +
								" WHERE A1.ID_HTA = A.ID_HTA " +
								" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
								" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
										" AND A.ID_JENISHM = B.ID_JENISHAKMILIK(+)"
						+ " AND A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM " +
								" AND A.JENIS_HTA = 'T' AND"
						+ " A1.ID_HTA IN (SELECT A.ID_HTA " +
								" FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
								" WHERE B1.ID_HTA = B.ID_HTA " +
								" AND B.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
										" AND A.ID_HTA = B1.ID_HTA AND B.JENIS_HTA = 'T'"
						+ " AND A.FLAG_HARTA = 'L' AND A.FLAG_BATAL = 'BB' " +
								" AND A.ID_PERINTAH = '" + idPerintah + "' AND A1.ID_HTA NOT IN"
						+ " (SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, " +
								" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
								" WHERE B.ID_HTA = B1.ID_HTA " +
								" AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
										" AND A.ID_HTA = B1.ID_HTA " +
										" AND B.JENIS_HTA = 'T' AND A.FLAG_HARTA = 'B' " +
										" AND A.ID_PERINTAH = '" + idPerintah + "'))";
				}
			}
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;			

			while (rs.next()) {
				h = new Hashtable();	
				
				h.put("bil", bil);
				h.put("idHTA",rs.getString("ID_HTA"));
				h.put("keteranganHakmilik", rs.getString("NAMA_NEGERI") == null || 
						rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				senaraiHTAPopup.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idHTA", "");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	senaraiHTAPopup.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHTA(String idHTA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("A.CATATAN");
			r.add("A.ID_JENISPERINTAH");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			
			r.add("G.KOD_JENIS_PB");
			r.add("G.KETERANGAN");
			r.add("B1.JENIS_TNH");
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTA);
			
			r.add("B.ID_JENISHM", r.unquote("C.ID_JENISHAKMILIK"));
			r.add("B.ID_NEGERI", r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("F.ID_MUKIM"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("G.ID_JENISPB(+)"));
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C,"
					+ " TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLRUJJENISPB G");
			myLogger.info("setDataMaklumatHTA( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") + " " + rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM") 
						+ ", " + rs.getString("NAMA_DAERAH") + ", " + rs.getString("NAMA_NEGERI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				beanMaklumatHTA.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveHTA(String idJenisPerintah, String catatan, String idHTA, String idPerintah, String idPermohonan, String idSimati, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
				
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBMST
			long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_HTA", idHTA);
			r.add("ID_PERINTAH", idPerintah);
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("FLAG_HARTA", "B");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			Fraction fracSimati = getFractionSimatiHTA(idHTA,idPermohonanSimati);
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				
				generateFaraid(idPermohonan,idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID, OB.UMUR, OB.STATUS_OB " +
						"FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						"WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 AND OB.ID_TARAFKPTG = 1 " +
						"AND OB.ID_SIMATI = '" + idSimati + "' " +
								"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){					
					insertDTLHTA(idPerintahHTAOBMST, rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sqlOB = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID " +
						" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 " +
						" AND OB.ID_TARAFKPTG = 8 AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sqlOB);
				
				while (rsBaitulmal.next()){					
					insertDTLHTA(idPerintahHTAOBMST, rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
			
				updatePecahanWarisHTA(String.valueOf(idPerintahHTAOBMST));
				
			}  else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(idPerintahHTAOBMST,hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void generateFaraid(String idPermohonan, String idSimati, String idPermohonanSimati) throws Exception{
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//CHECK IF FARAID IS GENERATED
			sql = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID " +
					" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
					" WHERE OB.ID_OB = OB1.ID_OB " +
					" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
							" AND OB.ID_SIMATI = '" + idSimati + "' " +
							" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")"
					+ " AND (OB.BA_FARAID IS NULL OR OB.BB_FARAID IS NULL) AND OB.STATUS_HIDUP = 0";

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				//GENERATE FARAID
				EkptgEngine ekptgEngine = new EkptgEngine();
				ekptgEngine.doAllFaraidProcessing(idSimati, idPermohonan, idPermohonanSimati, ekptgEngine);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusWaris(Integer umur, Integer statusOb){
		
		String statusWaris = "";
		
		if (umur != null){
			if (umur < 18){
				statusWaris = "T";
			} else {
				if (statusOb != null){
					if (statusOb == 3){
						statusWaris = "Y";
					} else if (statusOb == 2 || statusOb == 4){
						statusWaris = "T";
					}
				}
			}
		}
		return statusWaris;
	}
	
	public void insertDTLHTA(long idPerintahHTAOBMST, String idOB, String BA, String BB, String statusWaris, String idPerintah, Fraction fracSimati, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPAPT = new Vector();
		listPAPT.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", BA);
			r.add("BB", BB);
			r.add("BA_WARIS", fracWaris.numerator());
			r.add("BB_WARIS", fracWaris.denominator());
			r.add("STATUS_TADBIR", statusWaris);
			
			if (statusWaris.equals("Y")){
				listPAPT = getPentadbir(idPerintah);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
				
			} else if (statusWaris.equals("Y")){
				
				listPAPT = getPemegangAmanah(idOB);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
			}
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusHTA(String idHTA) throws Exception {
		//Azam add transaction on 30/4/2010
		Connection conn = null;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
		    conn = db.getConnection();
		    conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idHTA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.add("ID_PERINTAHHTAOBMST", idHTA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			conn.commit();
			
		} catch (SQLException se) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	    } finally {
	      if (db != null) db.close();
	    }	
	}
	
	public void updateCatatanHTA(String catatan, String idPerintahHTAOBMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			r.add("CATATAN", catatan);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTA(String idJenisPerintah, String catatan, String idPerintahHTAOBMST, String idPermohonan, String idSimati, String idPermohonanSimati, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
			//TBLPPKPERINTAHHTAOBMST
			r = new SQLRenderer();
			r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("TARIKH_JUALAN", "");
			r.add("AMAUN", "");
			r.add("JENIS_LELONG", "");
			r.add("HARGA_RIZAB", "");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}

			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan,idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID, OB.UMUR, OB.STATUS_OB " +
						"FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						"WHERE OB.ID_OB = OB1.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								"AND OB.STATUS_HIDUP = 0 " +
						"AND OB.ID_TARAFKPTG = 1 " +
						"AND OB.ID_SIMATI = '" + idSimati + "' " +
								"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){
					insertDTLHTA(Long.parseLong(idPerintahHTAOBMST), rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sqlOB = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID " +
						" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 " +
						" AND OB.ID_TARAFKPTG = 8 AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sqlOB);
				
				while (rsBaitulmal.next()){					
					insertDTLHTA(Long.parseLong(idPerintahHTAOBMST), rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHTA(idPerintahHTAOBMST);

			}  else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHTAPerintahKuasaTadbir(Long.parseLong(idPerintahHTAOBMST),hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTATH(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTATH.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.NAMA_NEGERI");
			r.add("D.NAMA_DAERAH");
			r.add("E.NAMA_MUKIM");
			r.add("F.KETERANGAN");
			r.add("A.CATATAN");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");

			
			r.add("B.ID_NEGERI",r.unquote("C.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("D.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("E.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("F.ID_JENISPERINTAH"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("B.JENIS_HTA", "T");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLPPKRUJJENISPERINTAH F","A.ID_PERINTAHHTAOBMST ASC");
			myLogger.info("setDataSenaraiHTATH(:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
								+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				senaraiHTATH.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("bahagianSimati","");
		    	senaraiHTATH.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHTATH(String idHTATH,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("A.CATATAN");
			r.add("A.ID_JENISPERINTAH");
			r.add("C.NAMA_NEGERI");
			r.add("D.NAMA_DAERAH");
			r.add("E.NAMA_MUKIM");
			r.add("F.KETERANGAN");
			
			r.add("G.KOD_JENIS_PB");
			r.add("G.KETERANGAN");
			r.add("B1.JENIS_TNH");
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTATH);
			r.add("A.ID_JENISPERINTAH", r.unquote("F.ID_JENISPERINTAH"));
			
			r.add("B.ID_NEGERI", r.unquote("C.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("D.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("E.ID_MUKIM"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("G.ID_JENISPB(+)"));
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B,TBLRUJNEGERI C, TBLRUJDAERAH D,"
					+ " TBLRUJMUKIM E, TBLPPKRUJJENISPERINTAH F, TBLRUJJENISPB G");
			myLogger.info("setDataMaklumatHTATH( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("keteranganHakmilik", rs.getString("NAMA_MUKIM") + ", " + rs.getString("NAMA_DAERAH") + ", " + rs.getString("NAMA_NEGERI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("jenisPerintah",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				beanMaklumatHTA.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHA(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.KETERANGAN AS JENIS_HARTA_ALIH");
			r.add("D.KETERANGAN AS JENIS_PERINTAH");
			r.add("A.CATATAN");
			r.add("A.ID_PERINTAHHAOBMST");
			r.add("B.ID_SIMATI");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("B.NILAI_HA_TARIKHMOHON");

			
			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			r.add("D.ID_JENISPERINTAH",r.unquote("A.ID_JENISPERINTAH"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.FLAG_HARTA", "B");
			
			r.add("B1.ID_HA",r.unquote("A.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C, TBLPPKRUJJENISPERINTAH D","B.ID_JENISHA ASC");
			myLogger.info("GET HARTA ALIH ATAS :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idSimati",rs.getString("ID_SIMATI"));
				h.put("jenisHarta",rs.getString("JENIS_HARTA_ALIH") == null ? "" : rs.getString("JENIS_HARTA_ALIH"));
				h.put("jenisPerintah",rs.getString("JENIS_PERINTAH") == null ? "" : rs.getString("JENIS_PERINTAH"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("nilaiTarikhMohon",rs.getString("NILAI_HA_TARIKHMOHON") == null ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				senaraiHA.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idSimati", "");
		    	h.put("jenisHarta", "Tiada Rekod");
		    	h.put("jenisPerintah","");
		    	h.put("catatan","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("bahagianSimati","");
		    	h.put("nilaiTarikhMohon","");
		    	senaraiHA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPopup(String idPermohonanSimati, String idPerintah) throws Exception {
		Db db = null;
		senaraiHAPopup.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HA, B.KETERANGAN, A.JENAMA, A.NO_DAFTAR, A.NILAI_HA_TARIKHMOHON"
				+ " FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A, TBLPPKRUJJENISHA B"
				+ " WHERE A1.ID_HA = A.ID_HA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
						"  AND B.ID_JENISHA = A.ID_JENISHA" 
				+ " AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
			
			//idPerintah
			if (idPerintah != null) {
				if (!idPerintah.trim().equals("")) {
					sql = sql + " AND A1.ID_HA NOT IN " +
							" (SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "')"
								+ " UNION"
								+ " SELECT A.ID_HA, B.KETERANGAN, A.JENAMA, A.NO_DAFTAR, A.NILAI_HA_TARIKHMOHON"
								+ " FROM TBLPPKHAPERMOHONAN A,TBLPPKHA A1, TBLPPKRUJJENISHA B " +
										" WHERE A.ID_HA = A1.ID_HA " +
										" AND A.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
										//" AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
										//" AND A1.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +										
										" AND B.ID_JENISHA = A.ID_JENISHA AND"
								+ " A1.ID_HA IN (SELECT A.ID_HA FROM TBLPPKPERINTAHHAOBMST A"
								+ " WHERE A.FLAG_HARTA = 'L' AND A.FLAG_BATAL = 'BB' AND A.ID_PERINTAH = '" + idPerintah + "' AND A.ID_HA NOT IN"
								+ " (SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "'))";
				}
			}
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idHA",rs.getString("ID_HA"));
				h.put("jenisHartaAlih",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("no_daftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));
				h.put("nilai",rs.getString("NILAI_HA_TARIKHMOHON") == null || "0".equals(rs.getString("NILAI_HA_TARIKHMOHON").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				senaraiHAPopup.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idHA", "");
		    	h.put("jenisHartaAlih", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("no_daftar","");
		    	h.put("nilai","");
		    	senaraiHAPopup.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataMaklumatHA(String idHA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanMaklumatHA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("A.ID_JENISPERINTAH");
			r.add("A.CATATAN");
			r.add("C.KETERANGAN");
			r.add("B.ID_JENISHA");
			
			r.add("ID_PERINTAHHAOBMST", idHA);
			r.add("C.ID_JENISHA", r.unquote("B.ID_JENISHA"));
			
			r.add("A.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("jenisHartaAlih",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisPerintah",rs.getString("ID_JENISPERINTAH") == null ? "0" : rs.getString("ID_JENISPERINTAH"));
				h.put("idJenisHA",rs.getString("ID_JENISHA") == null ? "" : rs.getString("ID_JENISHA"));
				beanMaklumatHA.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void saveHA(String idJenisPerintah, String catatan, String idHA, String idPerintah, String idPermohonan, String idSimati, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlCheck = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBMST
			long idPerintahHAOBMST = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_HA", idHA);
			r.add("ID_PERINTAH", idPerintah);
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("FLAG_HARTA", "B");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
			Fraction fracSimati = getFractionSimatiHA(idHA,idPermohonanSimati);
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan, idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sql = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID, OB.UMUR, OB.STATUS_OB " +
						" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.STATUS_HIDUP = 0 " +
						" AND OB.ID_TARAFKPTG = 1 AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				ResultSet rs = stmt.executeQuery(sql);
				
				while (rs.next()){
					insertDTLHA(idPerintahHAOBMST, rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sql = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID " +
						" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB.ID_OB = OB1.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 AND OB.ID_TARAFKPTG = 8 " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sql);
				
				while (rsBaitulmal.next()){					
					insertDTLHA(idPerintahHAOBMST, rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHA(String.valueOf(idPerintahHAOBMST));
		
			} else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHAPerintahKuasaTadbir(idPerintahHAOBMST,hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHA(long idPerintahHAOBMST, String idOB, String BA, String BB, String statusWaris, String idPerintah, Fraction fracSimati, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPAPT = new Vector();
		listPAPT.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", BA);
			r.add("BB", BB);
			r.add("BA_WARIS", fracWaris.numerator());
			r.add("BB_WARIS", fracWaris.denominator());
			r.add("STATUS_TADBIR", statusWaris);
			
			if (statusWaris.equals("Y")){
				listPAPT = getPentadbir(idPerintah);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
				
			} else if (statusWaris.equals("Y")){
				
				listPAPT = getPemegangAmanah(idOB);
				
				if (listPAPT.size() != 0){
					for (int i = 0; i < listPAPT.size(); i++) {
						Hashtable hash = (Hashtable) listPAPT.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
				}
			}
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertDTLHAPerintahKuasaTadbir(long idPerintahHAOBMST, String idOB, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusHA(String idHA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idHA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.add("ID_PERINTAHHAOBMST", idHA);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateCatatanHA(String catatan, String idPerintahHAOBMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			r.add("CATATAN", catatan);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHA(String idJenisPerintah, String catatan, String idPerintahHAOBMST, String idPermohonan, String idSimati, String idPermohonanSimati, String idJenisHA, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String sqlOB = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
			//TBLPPKPERINTAHHAOBMST
			r = new SQLRenderer();
			r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
			r.add("CATATAN", catatan);
			r.add("ID_JENISPERINTAH", idJenisPerintah);
			r.add("TARIKH_JUALAN", "");
			r.add("AMAUN", "");
			r.add("JENIS_LELONG", "");
			r.add("HARGA_RIZAB", "");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHA.next()){
				fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"),idPermohonanSimati);
			}
			
			if (idJenisPerintah.equals("7")){
				//GENERATE FARAID
				generateFaraid(idPermohonan, idSimati, idPermohonanSimati);
				
				//GET ALL WARIS
				sqlOB = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID, OB.UMUR, OB.STATUS_OB " +
						" FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 AND OB.ID_TARAFKPTG = 1 " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";

				ResultSet rs = stmt.executeQuery(sqlOB);
				
				while (rs.next()){
					insertDTLHA(Long.parseLong(idPerintahHAOBMST), rs.getString("ID_OB"), rs.getString("BA_FARAID"), rs.getString("BB_FARAID"), getStatusWaris(rs.getInt("UMUR"), rs.getInt("STATUS_OB")), idPerintah, fracSimati, session);
				}
				
				//GET BAITULMAL IF EXIST
				sql = "SELECT OB.ID_OB, OB1.BA_FARAID, OB1.BB_FARAID " +
						"FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						"WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.STATUS_HIDUP = 0 " +
						"AND OB.ID_TARAFKPTG = 8 " +
						"AND OB.ID_SIMATI = '" + idSimati + "' " +
								"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ")";
				
				ResultSet rsBaitulmal = stmt.executeQuery(sql);
				
				while (rsBaitulmal.next()){					
					insertDTLHA(Long.parseLong(idPerintahHAOBMST), rsBaitulmal.getString("ID_OB"), rsBaitulmal.getString("BA_FARAID"), rsBaitulmal.getString("BB_FARAID"), "", idPerintah, fracSimati, session);
				}
				
				updatePecahanWarisHA(idPerintahHAOBMST);
				
			} else if (idJenisPerintah.equals("2")){

				Vector listPentadbir = new Vector();
				listPentadbir = getPentadbir(idPerintah);
				
				for (int i = 0; i < listPentadbir.size(); i++){
					Hashtable hash = (Hashtable) listPentadbir.get(i);
					
					insertDTLHAPerintahKuasaTadbir(Long.parseLong(idPerintahHAOBMST),hash.get("idOB").toString(),session);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '1' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '1' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPKT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '2' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPKT(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '2' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPL(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '3' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPL(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '3' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHTAPF(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '7' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistHAPF(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = '7' AND FLAG_HARTA = 'B'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			
			r.add("H.KOD_JENIS_PB");
			r.add("B1.JENIS_TNH");
			
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "1");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			myLogger.info("setDataSenaraiHTAPT( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				
				senaraiHTAPT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");	
		    	h.put("kodPB","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "1");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1, TBLPPKHAPERMOHONAN B,TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPKT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPKT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B1.JENIS_TNH");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");
			r.add("B.FLAG_KATEGORI_HTA");
			r.add("B1.JENIS_TNH");
			
			r.add("B.FLAG_KATEGORI_HTA");
			r.add("B.ALAMAT_HTA1");
			r.add("B.ALAMAT_HTA2");
			r.add("B.ALAMAT_HTA3");
			r.add("B.POSKOD_HTA");
			r.add("B.NO_ROH");
			r.add("B.JENIS_KEPENTINGAN");
			
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1,  TBLPPKHTAPERMOHONAN B,TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			myLogger.info("setDataSenaraiHTAPKT :::::( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("keterangan","");
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase() 
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("kategoriHarta",rs.getString("FLAG_KATEGORI_HTA") == null ? "" : rs.getString("FLAG_KATEGORI_HTA"));
					if (rs.getString("FLAG_KATEGORI_HTA") != null) {
						if (rs.getString("FLAG_KATEGORI_HTA").equals("1")) {
							h.put("keterangan", " - " + (rs.getString("ALAMAT_HTA1") == null ? "" : rs.getString("ALAMAT_HTA1")) + " " + (rs.getString("ALAMAT_HTA2") == null ? "" : rs.getString("ALAMAT_HTA2")) + " " + (rs.getString("POSKOD_HTA") == null ? "" : rs.getString("POSKOD_HTA")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("2")) {
							h.put("keterangan", " - " + (rs.getString("NO_ROH") == null ? "" : rs.getString("NO_ROH")));
						} else if (rs.getString("FLAG_KATEGORI_HTA").equals("3")) {
							h.put("keterangan", " - " + (rs.getString("JENIS_KEPENTINGAN") == null ? "" : rs.getString("JENIS_KEPENTINGAN")));
						} else {
							h.put("keterangan","");
						}
					} else {
						h.put("keterangan","");
					}
				} else {
					h.put("keteranganHakmilik", "");
					h.put("keterangan","");
				}
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				
				senaraiHTAPKT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		
		    	h.put("kodPB","");
		    	h.put("keterangan","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPKT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPKT(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPKT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			r.add("A.FLAG_HARTA", "B");
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1, TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPKT.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPKT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		senaraiHTAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.NO_PT");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "3");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, " +
					" E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA, A.JENIS_LELONG, A.TARIKH_JUALAN, A.HARGA_RIZAB, " +
					" A.AMAUN, A.CATATAN, B.NO_PT, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, " +
					" TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA " +
							" AND A.ID_HTA = B1.ID_HTA  AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					" AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  " +
					" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  AND A.ID_PERINTAH = '"+idPerintah+"'  " +
					" AND A.ID_JENISPERINTAH = '3'  ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPL(String idPerintah) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				if (rs.getString("JENIS_LELONG") != null){
					if (rs.getString("JENIS_LELONG").equals("A")){
						h.put("jenisLelong","AWAM");
					} else if (rs.getString("JENIS_LELONG").equals("T")) {
						h.put("jenisLelong","TENDER");
					} else {
						h.put("jenisLelong","");
					}
				} else {
					h.put("jenisLelong","");
				}
				h.put("tarikhJualan",rs.getString("TARIKH_JUALAN") == null ? "": sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "0".equals(rs.getString("HARGA_RIZAB").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "0".equals(rs.getString("AMAUN").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				senaraiHTAPL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");	
		    	h.put("jenisLelong","");
		    	h.put("tarikhJualan","");
		    	h.put("hargaRizab","");
		    	h.put("amaun","");
		    	h.put("catatan","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		senaraiHAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("A.ID_PERINTAHHAOBMST");
			
			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "3");
			r.add("A.FLAG_HARTA", "B");
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				if (rs.getString("JENIS_LELONG") != null){
					if (rs.getString("JENIS_LELONG").equals("A")){
						h.put("jenisLelong","AWAM");
					} else if (rs.getString("JENIS_LELONG").equals("T")) {
						h.put("jenisLelong","TENDER");
					} else {
						h.put("jenisLelong","");
					}
				} else {
					h.put("jenisLelong","");
				}
				h.put("tarikhJualan",rs.getString("TARIKH_JUALAN") == null ? "": sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "0".equals(rs.getString("HARGA_RIZAB").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "0".equals(rs.getString("AMAUN").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				senaraiHAPL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	h.put("jenisLelong","");
		    	h.put("tarikhJualan","");
		    	h.put("hargaRizab","");
		    	h.put("amaun","");
		    	h.put("catatan","");
		    	senaraiHAPL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPF(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPF.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.NO_PT");
			r.add("H.KOD_JENIS_PB");

			r.add("A.ID_HTA",r.unquote("B.ID_HTA"));
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "7");

			//sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, " +
					" F.NAMA_MUKIM, B.JENIS_HTA, B.NO_PT, H.KOD_JENIS_PB, B1.JENIS_TNH  " +
					" FROM TBLPPKPERINTAHHTAOBMST A, " +
					" TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, " +
					" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, " +
					" TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H " +
					" WHERE B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND B1.ID_HTA = B.ID_HTA AND A.ID_HTA = B1.ID_HTA  AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  " +
					" AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH  " +
					" AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+)  AND B.STATUS_PEMILIKAN = H.ID_JENISPB(+)  " +
					" AND A.ID_PERINTAH = '"+idPerintah+"'  AND A.ID_JENISPERINTAH = '7'  " +
					" ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			myLogger.info("PERINTAH HTA X ### setDataSenaraiHTAPF(String idPerintah) :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NO_PT") + ", "+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPF.addElement(h);
				h.put("kodPB", rs.getString("KOD_JENIS_PB") == null ? "" : rs.getString("KOD_JENIS_PB"));
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		
		    	h.put("kodPB","");
		    	h.put("idJenisTanah","");
		    	h.put("jenisTanah","");
		    	senaraiHTAPF.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPF(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPF.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "7");
			r.add("A.FLAG_HARTA", "B");
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPF.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPF.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatHeaderDetailHTA(String idHTA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanHeaderDetail.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHTAOBMST");
			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			r.add("F.NAMA_MUKIM");
			r.add("B.JENIS_HTA");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("A.CATATAN");
		
			r.add("A.FLAG_BATAL");
			r.add("A.BA_WARIS");
			r.add("A.BB_WARIS");
			r.add("A.ID_WARIS");
			r.add("OB1.NAMA_OB");
			
			r.add("H.KOD_JENIS_PB");
			r.add("H.KETERANGAN AS KETERANGAN_PB");
			r.add("B1.JENIS_TNH");
			
			r.add("B.ID_NEGERI",r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH",r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM",r.unquote("F.ID_MUKIM"));
			r.add("A.ID_JENISPERINTAH",r.unquote("G.ID_JENISPERINTAH"));
			r.add("B.ID_JENISHM",r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("A.ID_WARIS",r.unquote("OB1.ID_OB(+)"));
			r.add("B.STATUS_PEMILIKAN", r.unquote("H.ID_JENISPB(+)"));
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTA);			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKOB OB1,TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C,"
					+ " TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G, TBLRUJJENISPB H","A.ID_PERINTAHHTAOBMST ASC");
			
			myLogger.info("setMaklumatHeaderDetailHTA( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){					
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_NEGERI") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
									+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
								
				h.put("flagBatal",rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
				h.put("ba_waris",rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("bb_waris",rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("id_waris",rs.getString("ID_WARIS") == null ? "" : rs.getString("ID_WARIS"));
				h.put("nama_ob",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("jenisPB", rs.getString("KOD_JENIS_PB") == null || rs.getString("KETERANGAN_PB") == null ? "" : rs.getString("KOD_JENIS_PB") + " - " + rs.getString("KETERANGAN_PB").toUpperCase());
				h.put("idJenisTanah",rs.getString("JENIS_TNH") == null ? "" : rs.getString("JENIS_TNH"));
				if (rs.getString("JENIS_TNH") != null && rs.getString("JENIS_TNH").length() > 0){
					if ("1".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH RIZAB");
					} else if ("2".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH ADAT");
					} else if ("3".equals(rs.getString("JENIS_TNH"))){
						h.put("jenisTanah","TANAH GSA");
					} else {
						h.put("jenisTanah","TIDAK DINYATAKAN");
					}
				} else {
					h.put("jenisTanah","");
				}				
				beanHeaderDetail.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPFDTL(String idPerintahHTAOBMST,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPFDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_OB, B.NAMA_OB, A.BA, A.BB, B.STATUS_OB, B.UMUR " +
					" FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKOB B1,TBLPPKOBPERMOHONAN B"
					+ " WHERE B1.ID_OB = A.ID_OB " +
							" AND B1.ID_OB = B.ID_OB " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND A.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
									" ORDER BY B.UMUR DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				senaraiHTAPFDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

				h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","");
		    	h.put("BB","");
		    	h.put("status","");
		    	senaraiHTAPFDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setMaklumatHeaderDetailHA(String idHA,String id_permohonansimati) throws Exception {
		Db db = null;
		beanHeaderDetail.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			r.add("B.NILAI_HA_TARIKHMOHON");
			r.add("B.BA_SIMATI");
			r.add("B.BB_SIMATI");
			r.add("A.FLAG_JENISPEMBAHAGIAN");
			r.add("C.ID_JENISHA");
			r.add("A.CATATAN");
			
			r.add("A.FLAG_BATAL");
			r.add("A.BA_WARIS");
			r.add("A.BB_WARIS");
			r.add("A.ID_WARIS");
			r.add("OB1.NAMA_OB");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAHHAOBMST", idHA);
			
			
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);
			r.add("A.ID_WARIS",r.unquote("OB1.ID_OB(+)"));

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKOB OB1,TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","A.ID_PERINTAHHAOBMST ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				h.put("bahagianSimati",rs.getString("BA_SIMATI") == null || rs.getString("BB_SIMATI") == null? "" : rs.getString("BA_SIMATI") + " / " + rs.getString("BB_SIMATI"));
				h.put("nilai",rs.getString("NILAI_HA_TARIKHMOHON") == null || "0".equals(rs.getString("NILAI_HA_TARIKHMOHON").toString()) ? "0.00" : Util.formatDecimal(Double.parseDouble(rs.getString("NILAI_HA_TARIKHMOHON"))));
				h.put("flagJenisPembahagian",rs.getString("FLAG_JENISPEMBAHAGIAN") == null ? "" : rs.getString("FLAG_JENISPEMBAHAGIAN"));
				h.put("idJenisHA",rs.getString("ID_JENISHA") == null ? "" : rs.getString("ID_JENISHA"));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				
				h.put("flagBatal",rs.getString("FLAG_BATAL") == null ? "" : rs.getString("FLAG_BATAL"));
				h.put("ba_waris",rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("bb_waris",rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("id_waris",rs.getString("ID_WARIS") == null ? "" : rs.getString("ID_WARIS"));
				h.put("nama_ob",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				
				beanHeaderDetail.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPFDTL(String idPerintahHAOBMST,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPFDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_OB, B.NAMA_OB, A.BA, A.BB, B.STATUS_OB, B.UMUR " +
					" FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKOB B1,TBLPPKOBPERMOHONAN B"
					+ " WHERE B1.ID_OB = A.ID_OB " +
							" AND B1.ID_OB = B.ID_OB " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND A.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' " +
									" ORDER BY B.UMUR DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				senaraiHAPFDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

				h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","");
		    	h.put("BB","");
		    	h.put("status","");
		    	senaraiHAPFDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataMaklumatHTAPL(String idHTAMST,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		beanMaklumatHTAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("C.KOD_JENIS_HAKMILIK");
			r.add("B.NO_HAKMILIK");
			r.add("D.NAMA_NEGERI");
			r.add("E.NAMA_DAERAH");
			
			r.add("F.NAMA_MUKIM");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.JENIS_HTA");
			r.add("A.ID_PERINTAHHTAOBMST");
			
			
			r.add("B.ID_JENISHM", r.unquote("C.ID_JENISHAKMILIK(+)"));
			r.add("B.ID_NEGERI", r.unquote("D.ID_NEGERI"));
			r.add("B.ID_DAERAH", r.unquote("E.ID_DAERAH"));
			r.add("B.ID_MUKIM", r.unquote("F.ID_MUKIM"));
			
			r.add("A.ID_PERINTAHHTAOBMST", idHTAMST);
			
			r.add("B1.ID_HTA", r.unquote("A.ID_HTA"));
			r.add("B1.ID_HTA", r.unquote("B.ID_HTA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST A, TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, TBLRUJMUKIM F");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_NEGERI") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
									+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				h.put("jenisLelong",rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				h.put("tarikhJualan",rs.getDate("TARIKH_JUALAN") == null ? "" : sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "".equals(rs.getString("HARGA_RIZAB").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "".equals(rs.getString("AMAUN").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));

				beanMaklumatHTAPL.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTAPL(String idHTAMST, Hashtable h, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		try {
			
			String tarikhJualan = h.get("tarikhJualan").toString();
			String TJ = "to_date('" + tarikhJualan + "','dd/MM/yyyy')";
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBMST				
			r.update("ID_PERINTAHHTAOBMST", idHTAMST);
			
			r.add("JENIS_LELONG", h.get("jenisLelong"));
			r.add("TARIKH_JUALAN", r.unquote(TJ));
			r.add("HARGA_RIZAB", h.get("hargaRizab"));
			r.add("AMAUN", h.get("amaun"));
			r.add("CATATAN", h.get("catatan"));
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataMaklumatHAPL(String idHAMST,String id_permohonansimati) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		beanMaklumatHAPL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("A.JENIS_LELONG");
			r.add("A.TARIKH_JUALAN");
			r.add("A.HARGA_RIZAB");
			r.add("A.AMAUN");
			r.add("A.CATATAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");
			
			
			r.add("C.ID_JENISHA", r.unquote("B.ID_JENISHA"));
			
			r.add("A.ID_PERINTAHHAOBMST", idHAMST);
			
			
			r.add("A.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B.ID_HA", r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI", id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1, TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenisLelong",rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				h.put("tarikhJualan",rs.getDate("TARIKH_JUALAN") == null ? "" : sdf.format(rs.getDate("TARIKH_JUALAN")));
				h.put("hargaRizab",rs.getString("HARGA_RIZAB") == null || "".equals(rs.getString("HARGA_RIZAB").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("HARGA_RIZAB"))));
				h.put("amaun",rs.getString("AMAUN") == null || "".equals(rs.getString("AMAUN").toString()) ? "" : Util.formatDecimal(Double.parseDouble(rs.getString("AMAUN"))));
				h.put("catatan",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));

				beanMaklumatHAPL.addElement(h);
				count++;
			}			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHAPL(String idHA, Hashtable h, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			String tarikhJualan = h.get("tarikhJualan").toString();
			String TJ = "to_date('" + tarikhJualan + "','dd/MM/yyyy')";
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBMST				
			r.update("ID_PERINTAHHAOBMST", idHA);
			
			r.add("JENIS_LELONG", h.get("jenisLelong"));
			r.add("TARIKH_JUALAN", r.unquote(TJ));
			r.add("HARGA_RIZAB", h.get("hargaRizab"));
			r.add("AMAUN", h.get("amaun"));
			r.add("CATATAN", h.get("catatan"));
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPKTDTL(String idPerintahHTAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiHTAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_OB = OB.ID_OB " +
								" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1 " +
								" AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_OB = OB.ID_OB " +
								" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'" +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG NOT IN (0,1,14) " +
										" ORDER BY UMUR DESC NULLS LAST";

		ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idOB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaOB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB").toUpperCase());
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				h.put("statusHidup", rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHTAPKTDTL.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB", "");
		    	h.put("namaOB", "Tiada Rekod");
		    	h.put("flag", "");
		    	h.put("statusHidup", "");
		    	senaraiHTAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPKTDTL(String idPerintahHAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiHAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG = 1 " +
										" AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB.ID_OB IN ("
				+ " SELECT ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB" +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";

		ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idOB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaOB", rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB").toUpperCase());
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				h.put("statusHidup", rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHAPKTDTL.addElement(h);
				bil++;
				count++;
			}

			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB", "");
		    	h.put("namaOB", "Tiada Rekod");
		    	h.put("flag", "");
		    	h.put("statusHidup", "");
		    	senaraiHAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePentadbir(String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlCheckPerintahTerusHTA = "";
		String sqlCheckPerintahTerusHA = "";
		String sqlCheckPerintahKuasaTadbirHTA = "";
		String sqlCheckPerintahKuasaTadbirHA = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//COMMENT BY PEJE - PENTADBIR PERINTAH TERUS X SAMA DENGAN PENTADBIR PERINTAH KUASA TADBIR
//			//HTA PERINTAH TERUS / FARAID
//			sqlCheckPerintahTerusHTA = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B"
//					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST";
//			
//			ResultSet rsHTA = stmt.executeQuery(sqlCheckPerintahTerusHTA);
//			
//			while (rsHTA.next()){
//				updatePTHTAPTPF(rsHTA.getString("ID_PERINTAHHTAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
//			}
//			
//			//HA PERINTAH TERUS / FARAID
//			sqlCheckPerintahTerusHA = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B"
//					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAHHAOBMST = A.ID_PERINTAHHAOBMST";
//			
//			ResultSet rsHA = stmt.executeQuery(sqlCheckPerintahTerusHA);
//			
//			while (rsHA.next()){
//				updatePTHAPTPF(rsHA.getString("ID_PERINTAHHAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
//			}
			
			//HTA PERINTAH KUASA TADBIR
			sqlCheckPerintahKuasaTadbirHTA = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2 AND FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rsKTHTA = stmt.executeQuery(sqlCheckPerintahKuasaTadbirHTA);
			
			while (rsKTHTA.next()){
				
				deletePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"));
				
				if (idPA1.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA1, session);
				}
				if (idPA2.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA2, session);
				}
				if (idPA3.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA3, session);
				}
				if (idPA4.trim().length() > 0){
					updatePTHTAPKT(rsKTHTA.getString("ID_PERINTAHHTAOBMST"), idPA4, session);
				}
			}
			
			//HA PERINTAH KUASA TADBIR
			sqlCheckPerintahKuasaTadbirHA = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rsKTHA = stmt.executeQuery(sqlCheckPerintahKuasaTadbirHA);
			
			while (rsKTHA.next()){
				
				deletePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"));
				
				if (idPA1.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA1, session);
				}
				if (idPA2.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA2, session);
				}
				if (idPA3.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA3, session);
				}
				if (idPA4.trim().length() > 0){
					updatePTHAPKT(rsKTHA.getString("ID_PERINTAHHAOBMST"), idPA4, session);
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deletePTHTAPKT(String idHTAMST) throws Exception {
		Db db = null;
		String sqlDelete = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL				
			r.add("ID_PERINTAHHTAOBMST", idHTAMST);

			sqlDelete = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sqlDelete);			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deletePTHAPKT(String idHAMST) throws Exception {
		Db db = null;
		String sqlDelete = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL				
			r.add("ID_PERINTAHHAOBMST", idHAMST);

			sqlDelete = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sqlDelete);			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHTAPTPF(String idHTADTL, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL				
			r.update("ID_PERINTAHHTAOBDTL", idHTADTL);
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHAPTPF(String idHADTL, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL				
			r.update("ID_PERINTAHHAOBDTL", idHADTL);
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHTAPKT(String idHTAMST, String idOB, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL				
			long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
			r.add("ID_PERINTAHHTAOBDTL", id);
			r.add("ID_PERINTAHHTAOBMST", idHTAMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sqlInsert);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePTHAPKT(String idHAMST, String idOB, HttpSession session) throws Exception {
		
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sqlInsert = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL				
			long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
			r.add("ID_PERINTAHHAOBDTL", id);
			r.add("ID_PERINTAHHAOBMST", idHAMST);
			r.add("ID_OB", idOB);
			r.add("BA", "1");
			r.add("BB", "1");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sqlInsert);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPTDTL(String idPerintahHTAOBMST, String idSimati, 
			String idPermohonanSimati,String idPermohonan, String flagBatal) throws Exception {
		Db db = null;
		senaraiHTAPTDTL.clear();
		String sql = "";
		String sqlCheck = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			if (!"BL".equals(flagBatal)){
				sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
							" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST " +
							" AND A.ID_OB = OB.ID_OB " +
							" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
							"WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST " +
							" AND A.ID_OB = OB.ID_OB " +
							" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
					+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
							" WHERE OB.ID_SIMATI = '" + idSimati + "' " +
									" AND OB.ID_OB = OB1.ID_OB " +
									" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
							"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
									" AND OB.ID_TARAFKPTG = 1"
					+ " UNION"
					+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
							" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST " +
							" AND A.ID_OB = OB.ID_OB " +
							" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B " +
							" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST " +
							" AND A.ID_OB = OB.ID_OB " +
							" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
					+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
							" WHERE OB.ID_OB = OB1.ID_OB " +
							" AND OB.ID_SIMATI = '" + idSimati + "' " +
							" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
									" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
											" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			} else {
				sqlCheck = "";
				
				sql = "SELECT OB1.ID_OB,OB.NAMA_OB,D.BA,D.BB,OB.STATUS_OB, OB.STATUS_HIDUP" +
						" FROM TBLPPKPERINTAHHTAOBDTL D,TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB "+
						" WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' "+
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' "+
						" AND OB1.ID_OB = OB.ID_OB "+
						" AND D.ID_OB = OB.ID_OB" +
					
						" UNION" + 
					
						" SELECT D.ID_OB, 'WARIS TIDAK DAPAT DIKESAN' AS NAMA_OB, D.BA, D.BB, '5' AS STATUS_OB, '0' AS STATUS_HIDUP" +
						" FROM TBLPPKPERINTAHHTAOBDTL D WHERE D.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND D.ID_OB = '9999" + getidHTAFromIdPerintahHTAOBMST(idPerintahHTAOBMST) + "'";
			}

			myLogger.info("setDataSenaraiHTAPTDTL :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else if (rs.getString("STATUS_OB").equals("5")){
						h.put("status","TIDAK DAPAT DIKESAN");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiHTAPTDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","");
		    	h.put("BB","");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiHTAPTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPTDTLHilang(String idHAMST) throws Exception {
		Db db = null;
		senaraiHAPTDTLHilang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHAOBDTL, BA, BB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND STATUS_TADBIR = 'Y' AND ID_PERINTAHHAOBMST = '" + idHAMST + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHAOBDTL",rs.getString("ID_PERINTAHHAOBDTL"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "" : rs.getString("BB"));
				
				senaraiHAPTDTLHilang.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHTAPTDTLHilang(String idHTAMST) throws Exception {
		Db db = null;
		senaraiHTAPTDTLHilang.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHTAOBDTL, BA, BB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND STATUS_TADBIR = 'Y' AND ID_PERINTAHHTAOBMST = '" + idHTAMST + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("idPerintahHTAOBDTL",rs.getString("ID_PERINTAHHTAOBDTL"));
				h.put("bahagianWaris",rs.getString("BA") == null || rs.getString("BB") == null ? "" : rs.getString("BA") + " / " + rs.getString("BB"));
				h.put("BA",rs.getString("BA") == null ? "" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "" : rs.getString("BB"));
				
				senaraiHTAPTDTLHilang.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateHTAPT(String idPerintahHTAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA, FLAG_BATAL, ID_WARIS, BA_WARIS, BB_WARIS FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			System.out.println("GET FRACTION SIMATI sql==="+sql);
			Fraction fracSimati = new Fraction(1,1);
			Fraction fracWarisSblm = new Fraction(1,1);
			if (rsHTA.next()){
				if ("BB".equals(rsHTA.getString("FLAG_BATAL")) && rsHTA.getString("FLAG_BATAL") != null){
					System.out.println("getfracSimati1===");
					if (Utils.parseLong(rsHTA.getString("BB_WARIS")) > 0){
						//old code, aishahlatip ubah sebab en rosli minta ubah supaya bhgn waris didarapkan juga dgn bhgn simati
						fracWarisSblm = new Fraction(Long.parseLong(rsHTA.getString("BA_WARIS")),Long.parseLong(rsHTA.getString("BB_WARIS")));
						fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
					}					
				} else {
					System.out.println("getfracSimati2===");
					fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
				}				
			}
			System.out.println("fracSimati==="+fracSimati);
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			System.out.println("fracWaris1 ==="+fracWaris);
			
			fracWaris = fracWaris.times(fracSimati);
			fracWaris = fracWaris.times(fracWarisSblm);
			
			System.out.println("fracWaris2 ==="+fracWaris);

			
			//GET ROW OB
			sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsRowOB = stmt.executeQuery(sql);

			if (rsRowOB.next()){
				//UPDATE TBLPPKPERINTAHHTAOBDTL		
				r.update("ID_PERINTAHHTAOBDTL", rsRowOB.getString("ID_PERINTAHHTAOBDTL"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
				
			} else {
				//NEW TBLPPKPERINTAHHTAOBDTL				
				long idPerintahHTAOBDTL = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
				r.add("ID_PERINTAHHTAOBDTL", idPerintahHTAOBDTL);
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
				
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDataSenaraiHAPTDTL(String idPerintahHAOBMST, String idSimati, 
			String idPermohonanSimati,String idPermohonan, String flagBatal) throws Exception {
		Db db = null;
		senaraiHAPTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			if (!"BL".equals(flagBatal)){
			sql = "SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
							" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST " +
							" AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
							" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST " +
							" AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
					+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB" +
							" WHERE OB.ID_SIMATI = '" + idSimati + "' " +
							" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
									" AND OB.ID_OB = OB1.ID_OB " +
									" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
											" AND OB.ID_TARAFKPTG = 1"
					+ " UNION"
					+ " SELECT OB.ID_OB, OB.NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
					+ " (SELECT A.BA FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
							" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST " +
							" AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BA,"
					+ " (SELECT A.BB FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B " +
							" WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST " +
							" AND A.ID_OB = OB.ID_OB AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "') AS BB"
					+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
							" WHERE OB.ID_SIMATI = '" + idSimati + "' AND OB.ID_OB = OB1.ID_OB " +
									" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
									" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
											" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			} else {
				
				sql = "SELECT OB1.ID_OB,OB.NAMA_OB,D.BA,D.BB,OB.STATUS_OB, OB.STATUS_HIDUP " +
						" FROM TBLPPKPERINTAHHAOBDTL D,TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB "+
						" WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' "+
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' "+
						" AND OB1.ID_OB = OB.ID_OB "+
						" AND D.ID_OB = OB.ID_OB" +
				
						" UNION" + 
				
						" SELECT D.ID_OB, 'WARIS TIDAK DAPAT DIKESAN' AS NAMA_OB, D.BA, D.BB, '5' AS STATUS_OB, '0' AS STATUS_OB" +
						" FROM TBLPPKPERINTAHHAOBDTL D WHERE D.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND D.ID_OB = '9999" + getidHAFromIdPerintahHAOBMST(idPerintahHAOBMST) + "'";
				
			}
			
			myLogger.info("-----CEHECK setDataSenaraiHAPTDTL :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			int count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				senaraiHAPTDTL.addElement(h);
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				bil++;
				count++;
			}
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","");
		    	h.put("BB","");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiHAPTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateHAPT(String idPerintahHAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT ID_HA, FLAG_BATAL, ID_WARIS, BA_WARIS, BB_WARIS FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			Fraction fracWarisSblm = new Fraction(1,1);
			if (rsHA.next()){
				if ("BB".equals(rsHA.getString("FLAG_BATAL")) && rsHA.getString("FLAG_BATAL") != null){
					if (Utils.parseLong(rsHA.getString("BB_WARIS")) > 0){
						
						//24012018, aishahlatip ubah sebab en rosli minta ubah supaya bhgn waris didarapkan juga dgn bhgn simati
						//fracSimati = new Fraction(Long.parseLong(rsHA.getString("BA_WARIS")),Long.parseLong(rsHA.getString("BB_WARIS")));
						fracWarisSblm = new Fraction(Long.parseLong(rsHA.getString("BA_WARIS")),Long.parseLong(rsHA.getString("BB_WARIS")));
						fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"),idPermohonanSimati);
					}					
				} else {
					fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"),idPermohonanSimati);
				}
			}
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			fracWaris = fracWaris.times(fracWarisSblm);
			//GET ROW OB
			sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsRowOB = stmt.executeQuery(sql);
			
			if (rsRowOB.next()){
				//UPDATE TBLPPKPERINTAHHAOBDTL		
				r.update("ID_PERINTAHHAOBDTL", rsRowOB.getString("ID_PERINTAHHAOBDTL"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);
				
			} else {
				//NEW TBLPPKPERINTAHHAOBDTL				
				long idPerintahHAOBDTL = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
				r.add("ID_PERINTAHHAOBDTL", idPerintahHAOBDTL);
				r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");
					
					listPemegangAmanah = getPemegangAmanah(idOB);
					
					for (int i = 0; i < listPemegangAmanah.size(); i++) {
						Hashtable hash = (Hashtable) listPemegangAmanah.get(i);
						if (i == 0){
							idPA1 = hash.get("idOB").toString();
						} else if (i == 1){
							idPA2 = hash.get("idOB").toString();
						} else if (i == 2){
							idPA3 = hash.get("idOB").toString();
						} else if (i == 3){
							idPA4 = hash.get("idOB").toString();
						}
					}
					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				r.add("ID_PA1", idPA1);
				r.add("ID_PA2", idPA2);
				r.add("ID_PA3", idPA3);
				r.add("ID_PA4", idPA4);
				
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void selesaiPermohonan(String idPermohonan, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");			
			r.add("AKTIF", "0");	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			
			//get id_Fail
			sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", "359"); //SELESAI PERMOHONAN
			r.add("AKTIF", "1");
			r.add("ID_FAIL", rs.getString("ID_FAIL"));			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			
			//TBLPPKPERMOHONAN
			/*
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_STATUS", "25");// SELESAI PERMOHONAN	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			*/
			
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"25","359",rs.getString("ID_FAIL")+"");


		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void selesai(String idPermohonan, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");			
			r.add("AKTIF", "0");		
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/

			
			//get id_Fail
			sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			//TBLRUJSUBURUSANSTATUSFAIL
			/*
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", "358"); //SELESAI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", rs.getString("ID_FAIL"));			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/

			//TBLPPKPERMOHONAN
			/*
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);			
			r.add("ID_STATUS", "21");// SELESAI	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			*/
			
			
			FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
			logic_A.kemaskiniSubUrusanStatusFail(session,idPermohonan+"",userId,"21","358",rs.getString("ID_FAIL")+"");

			createMasterHTA(idPerintah, idPermohonanSimati, session);
			createMasterHA(idPerintah, idPermohonanSimati, session);
			updateHTAAfterSelesai(idPerintah, session,idPermohonanSimati);
			updateHAAfterSelesai(idPerintah, session,idPermohonanSimati);
			
			//17042018 aishahlatip untuk kemaskini tarikh selesai pada table tblppkpermohonan
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);			
			r.add("TARIKH_SELESAI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPPKPERMOHONAN");
			stmt.executeUpdate(sql);
			
//			//UPDATE FLAG PA HARTA TAK ALIH
//			sql = "SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAHHTAOBDTL B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBDTL AND B.ID_PA1 IS NOT NULL AND B.STATUS_TADBIR = 'T' AND B.BA > 0 AND A.ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs1 = stmt.executeQuery(sql);			
//			while (rs1.next()){
//				updateFlagPAHTA(rs1.getString("ID_HTA"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE FLAG PT HARTA TAK ALIH
//			sql = "SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAHHTAOBDTL B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBDTL AND B.ID_OB IS NULL AND B.ID_PA1 IS NOT NULL AND B.STATUS_TADBIR = 'Y' AND B.BA > 0 AND A.ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs2 = stmt.executeQuery(sql);			
//			while (rs2.next()){
//				updateFlagPTHTA(rs2.getString("ID_HTA"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE FLAG PT HARTA TAK ALIH
//			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2  AND ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs3 = stmt.executeQuery(sql);			
//			while (rs3.next()){
//				updateFlagPTHTA(rs3.getString("ID_HTA"), session,idPermohonanSimati);
//			}			
//			
//			//UPDATE FLAG PA HARTA ALIH
//			sql = "SELECT A.ID_HA FROM TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAHHAOBDTL B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBDTL AND B.ID_PA1 IS NOT NULL AND B.STATUS_TADBIR = 'T' AND B.BA > 0 AND A.ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs4 = stmt.executeQuery(sql);			
//			while (rs4.next()){
//				updateFlagPAHA(rs4.getString("ID_HA"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE FLAG PT HARTA ALIH
//			sql = "SELECT A.ID_HA FROM TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAHHAOBDTL B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBDTL AND B.ID_OB IS NULL AND B.ID_PA1 IS NOT NULL AND B.STATUS_TADBIR = 'Y' AND B.BA > 0 AND A.ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs5 = stmt.executeQuery(sql);			
//			while (rs5.next()){
//				updateFlagPTHA(rs5.getString("ID_HA"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE FLAG PT HARTA ALIH
//			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 2  AND ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs6 = stmt.executeQuery(sql);			
//			while (rs6.next()){
//				updateFlagPTHA(rs6.getString("ID_HA"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE ID_PERINTAHHTAOBMST DI TBLPPKHTA
//			sql = "SELECT ID_PERINTAHHTAOBMST, ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs7 = stmt.executeQuery(sql);			
//			while (rs7.next()){
//				updateIdPerintahHTAOBMST(rs7.getString("ID_HTA"), rs7.getString("ID_PERINTAHHTAOBMST"), session,idPermohonanSimati);
//			}
//			
//			//UPDATE ID_PERINTAHHAOBMST DI TBLPPKHA
//			sql = "SELECT ID_PERINTAHHAOBMST, ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "'";
//			ResultSet rs8 = stmt.executeQuery(sql);			
//			while (rs8.next()){
//				updateIdPerintahHAOBMST(rs8.getString("ID_HA"), rs8.getString("ID_PERINTAHHAOBMST"), session,idPermohonanSimati);
//			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateHTAAfterSelesai(String idPerintah, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              //PERINTAH KUASA TADBIR
              sql = "SELECT ID_PERINTAHHTAOBMST, ID_HTA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'B' AND ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'"
              
              	+ " UNION"
              
              	//HARTA TERTINGGAL
              	+ " SELECT ID_PERINTAHHTAOBMST, ID_HTA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'B' AND ID_JENISPERINTAH != 2 AND FLAG_BATAL IS NULL AND ID_PERINTAH = '" + idPerintah + "'"
              	
              	+ " UNION"
                
              	//PERINTAH TERUS MASTER
              	+ " SELECT ID_PERINTAHHTAOBMST, ID_HTA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'M' AND ID_JENISPERINTAH != 2 AND ID_PERINTAH = '" + idPerintah + "'";
              
              ResultSet rs = stmt.executeQuery(sql);
              
              while (rs.next()){
                    if (rs.getString("ID_JENISPERINTAH") != null){
                          updateFlagSelesaiHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                          if (rs.getString("ID_JENISPERINTAH").equals("1") || rs.getString("ID_JENISPERINTAH").equals("7")){
                                if (checkForPTHTA(rs.getString("ID_PERINTAHHTAOBMST"))){
                                      updateFlagPTHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                                }
                                if (checkForPAHTA(rs.getString("ID_PERINTAHHTAOBMST"))){
                                      updateFlagPAHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                                }                                   
                          } else if (rs.getString("ID_JENISPERINTAH").equals("2")){
                                updateFlagPTHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                          }
                    }                       
                    updateIdPerintahOBMSTForHTA(rs.getString("ID_PERINTAHHTAOBMST"), rs.getString("ID_HTA"), session,id_permohonansimati);
                    //TODO                    
                    updateHTAPermohonan(rs.getString("ID_HTA"), session, id_permohonansimati);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
	}
	
	public void updateHTAPermohonan(String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
		Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
        	db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
              
              sql = "SELECT ID_PERINTAHOBMST, FLAG_PT, FLAG_PA, FLAG_SELESAI FROM TBLPPKHTA WHERE ID_HTA = '" + idHTA + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
            	 /* r = new SQLRenderer();
                  r.update("ID_HTA", idHTA);
                  r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
                  r.add("FLAG_PA", rs.getString("FLAG_PA"));
                  r.add("FLAG_PT", rs.getString("FLAG_PT"));
                  r.add("FLAG_SELESAI", rs.getString("FLAG_SELESAI"));
                  r.add("ID_PERINTAHOBMST", rs.getString("ID_PERINTAHOBMST"));
                  r.add("ID_KEMASKINI", userId);
                  r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
                  sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
                  System.out.println("----------------- : " + sql);
                  stmt.executeUpdate(sql);*/
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
	
	public void updateHAPermohonan(String idHA, HttpSession session,String id_permohonansimati) throws Exception {
		Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
        	db = new Db();
            Statement stmt = db.getStatement();
            SQLRenderer r = new SQLRenderer();
              
              sql = "SELECT ID_PERINTAHOBMST, FLAG_PT, FLAG_PA, FLAG_SELESAI FROM TBLPPKHA WHERE ID_HA = '" + idHA + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
            	  r = new SQLRenderer();
                  r.update("ID_HA", idHA);
                  r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
                  r.add("FLAG_PA", rs.getString("FLAG_PA"));
                  r.add("FLAG_PT", rs.getString("FLAG_PT"));
                  r.add("FLAG_SELESAI", rs.getString("FLAG_SELESAI"));
                  r.add("ID_PERINTAHOBMST", rs.getString("ID_PERINTAHOBMST"));
                  r.add("ID_KEMASKINI", userId);
                  r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
                  sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
                  stmt.executeUpdate(sql);
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPTHTA(String idPerintahHTAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE STATUS_TADBIR = 'Y' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPAHTA(String idPerintahHTAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE STATUS_TADBIR = 'T' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagPAHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PA", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              stmt.executeUpdate(sql);
              
//              r.clear();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI",id_permohonansimati);
//              r.add("FLAG_PA", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }

  public void updateFlagPTHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PT", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              myLogger.info(sql);
              stmt.executeUpdate(sql);
              
//              r.clear();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI", idHTA);
//              r.add("FLAG_PT", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              myLogger.info(sql);
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagSelesaiHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              myLogger.info("------------------------------------------------");
              myLogger.info(sql);
              stmt.executeUpdate(sql);
              
//              r.clear();
//              r.update("ID_HTA", idHTA);
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//              r.add("FLAG_PT", "T");
//              r.add("FLAG_PA", "T");
//              r.add("FLAG_SELESAI", "Y");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              myLogger.info(sql);
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateIdPerintahOBMSTForHTA(String idPerintahHTAOBMST, String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHTA
              r.update("ID_HTA", idHTA);              
              r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHTA");
              stmt.executeUpdate(sql);
              
//              r.clear();
//              r.update("ID_HTA", idHTA);  
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
//              r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
 
  
  private boolean checkForPTHA(String idPerintahHAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE STATUS_TADBIR = 'Y' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  private boolean checkForPAHA(String idPerintahHAOBMST) throws Exception {
        Db db = null;
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE STATUS_TADBIR = 'T' AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
              ResultSet rs = stmt.executeQuery(sql);
              
              if (rs.next()){
                    return true;
              } else {
                    return false;
              }
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagPAHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PA", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
              
            //TBLPPKHA
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA); 
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
//              r.add("FLAG_PA", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }

  public void updateFlagPTHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PT", "Y");
              r.add("FLAG_SELESAI", "T");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
              
            //TBLPPKHA
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA);  
//              r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//              r.add("FLAG_PT", "Y");
//              r.add("FLAG_SELESAI", "T");  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
//              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateFlagSelesaiHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
            //TBLPPKHA
              /*
              r = new SQLRenderer();
              r.update("ID_HA", idHA);   
              r.update("ID_PERMOHONANSIMATI", id_permohonansimati); 
              r.add("FLAG_PT", "T");
              r.add("FLAG_PA", "T");
              r.add("FLAG_SELESAI", "Y");  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
              stmt.executeUpdate(sql);*/
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
  
  public void updateIdPerintahOBMSTForHA(String idPerintahHAOBMST, String idHA, HttpSession session,String id_permohonansimati) throws Exception {
        Db db = null;
        String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              SQLRenderer r = new SQLRenderer();
              
              //TBLPPKHA
              r.update("ID_HA", idHA);              
              r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);  
              r.add("ID_KEMASKINI", userId);
              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
              sql = r.getSQLUpdate("TBLPPKHA");
              stmt.executeUpdate(sql);
              
//              r = new SQLRenderer();
//              r.update("ID_HA", idHA);   
//              r.update("ID_HA", id_permohonansimati);  
//              r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);  
//              r.add("ID_KEMASKINI", userId);
//              r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//              sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//              stmt.executeUpdate(sql);
              
        } finally {
              if (db != null)
                    db.close();
        }
  }
	
//	@SuppressWarnings("unchecked")
//	public void updateFlagPAHTA(String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHTA				
//			r.update("ID_HTA", idHTA);			
//			r.add("FLAG_PA", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HTA", idHTA);	
//			r.update("ID_PERMOHONANSIAMTI", id_permohonansimati);	
//			r.add("FLAG_PA", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateFlagPTHTA(String idHTA, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHTA	
//			r = new SQLRenderer();
//			r.update("ID_HTA", idHTA);			
//			r.add("FLAG_PT", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HTA", idHTA);
//			r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//			r.add("FLAG_PT", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateFlagPAHA(String idHA, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHA				
//			r.update("ID_HA", idHA);			
//			r.add("FLAG_PA", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HA", idHA);	
//			r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//			r.add("FLAG_PA", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateFlagPTHA(String idHA, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHA				
//			r.update("ID_HA", idHA);			
//			r.add("FLAG_PT", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HA", idHA);
//			r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//			r.add("FLAG_PT", "Y");	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateIdPerintahHTAOBMST(String idHTA, String idPerintahHTAOBMST, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHTA				
//			r.update("ID_HTA", idHTA);			
//			r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HTA", idHTA);
//			r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//			r.add("ID_PERINTAHOBMST", idPerintahHTAOBMST);	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void updateIdPerintahHAOBMST(String idHA, String idPerintahHAOBMST, HttpSession session,String id_permohonansimati) throws Exception {
//		Db db = null;
//		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
//
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			
//			SQLRenderer r = new SQLRenderer();
//			//TBLPPKHA				
//			r.update("ID_HA", idHA);			
//			r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHA");
//			stmt.executeUpdate(sql);
//			
//			r = new SQLRenderer();
//			r.update("ID_HA", idHA);
//			r.update("ID_PERMOHONANSIMATI", id_permohonansimati);
//			r.add("ID_PERINTAHOBMST", idPerintahHAOBMST);	
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("TBLPPKHAPERMOHONAN");
//			stmt.executeUpdate(sql);
//			
//		} finally {
//			if (db != null)
//				db.close();
//		}
//	}
	
	public boolean checkHTAYangBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT HTA1.ID_HTA FROM TBLPPKHTA HTA1, TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_HTA = HTA1.ID_HTA " +
					" AND HTA.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI" +
					" AND HTA1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
							" AND HTA.JENIS_HTA = 'Y' AND HTA1.ID_HTA NOT IN "
				+ "(SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST " +
						" WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "')"
				+ " UNION"
				+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, " +
						" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
						" WHERE A.ID_HTA = B1.ID_HTA AND B.ID_HTA = B1.ID_HTA " +
						" AND B.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
						" AND A.ID_PERINTAH = '" + idPerintah + "' " +
								" AND A.FLAG_HARTA = 'L'"
				+ " AND A.FLAG_BATAL = 'BB' AND B.JENIS_HTA = 'Y' " +
						" AND B1.ID_HTA NOT IN " +
						" (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST " +
						" WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'B')";

			myLogger.info("checkHTAYangBelumDibahagikan(:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkHTATHYangBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT HTA1.ID_HTA FROM TBLPPKHTA HTA1, TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_HTA = HTA1.ID_HTA " +
					" AND HTA.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI" +
					" AND HTA1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
							" AND HTA.JENIS_HTA = 'Y' AND HTA1.ID_HTA NOT IN "
				+ "(SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST " +
						" WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "')"
				+ " UNION"
				+ " SELECT A.ID_HTA FROM TBLPPKPERINTAHHTAOBMST A, " +
						" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B " +
						" WHERE A.ID_HTA = B1.ID_HTA AND B.ID_HTA = B1.ID_HTA " +
						" AND B.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' " +
						" AND A.ID_PERINTAH = '" + idPerintah + "' " +
								" AND A.FLAG_HARTA = 'L'"
				+ " AND A.FLAG_BATAL = 'BB' AND B.JENIS_HTA = 'T' " +
						" AND B1.ID_HTA NOT IN " +
						" (SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST " +
						" WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'B')";

			myLogger.info("checkHTATHYangBelumDibahagikan(:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkHAYangBelumDibahagikan(String idPermohonanSimati, String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A1.ID_HA FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A1.ID_HA = A.ID_HA AND A1.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI " +
					" AND A1.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND A1.ID_HA NOT IN "
				+ "(SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '" + idPerintah + "')"
				+ " UNION"
				+ " SELECT A.ID_HA FROM TBLPPKPERINTAHHAOBMST A, " +
						"TBLPPKHA B1,TBLPPKHAPERMOHONAN B " +
						" WHERE A.ID_HA = B1.ID_HA  AND B.ID_HA = B1.ID_HA AND B.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND A.ID_PERINTAH = '" + idPerintah + "' AND A.FLAG_HARTA = 'L'"
				+ " AND A.FLAG_BATAL = 'BB' AND A.ID_HA NOT IN (SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND FLAG_HARTA = 'B')";
			
			myLogger.info("checkHAYangBelumDibahagikan(:"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPembahagianHTAPTLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		Boolean bool = false;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = 1 AND FLAG_HARTA = 'B'";
			ResultSet rs = stmt.executeQuery(sql);
			
			int count = 0;
			while (rs.next()) {	
				if (calculateTotalPecahanHTAPT(rs.getString("ID_PERINTAHHTAOBMST")).equals("1")){
					bool = false;
				} else {
					bool = true;
				}
				count ++;
			}
			if (count == 0){
				bool = true;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public String calculateTotalPecahanHTAPT(String idPerintahHTAOBMST) throws Exception{
		Db db = null;
		String sql = "";
		Fraction total = new Fraction(0, 1);
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BA, BB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("BA") != null && rs.getString("BB") != null){
					if (Long.parseLong(rs.getString("BB")) > 0){
						Fraction frac = new Fraction(Long.parseLong(rs.getString("BA")), Long.parseLong(rs.getString("BB")));
						total = total.plus(frac);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return total.toString();
	}
	
	public boolean checkPembahagianHAPTLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		Boolean bool = false;
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH = '" + idPerintah + "' AND ID_JENISPERINTAH = 1 AND FLAG_HARTA = 'B'";

			ResultSet rs = stmt.executeQuery(sql);
			
			int count = 0;
			while (rs.next()) {	
				if (calculateTotalPecahanHAPT(rs.getString("ID_PERINTAHHAOBMST")).equals("1")){
					bool = false;
				} else {
					bool = true;
				}
				count ++;
			}
			if (count == 0){
				bool = true;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public String calculateTotalPecahanHAPT(String idPerintahHAOBMST) throws Exception{
		Db db = null;
		String sql = "";
		Fraction total = new Fraction(0, 1);
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BA, BB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getString("BA") != null && rs.getString("BB") != null){
					if (Long.parseLong(rs.getString("BB")) > 0){
						Fraction frac = new Fraction(Long.parseLong(rs.getString("BA")), Long.parseLong(rs.getString("BB")));
						total = total.plus(frac);
					}
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}

		return total.toString();
	}
	
	public boolean checkPembahagianHTAPKTLengkap(String idPerintah) throws Exception{
		Db db = null;
		boolean bool = false;
		String sql = "";
		String sqlDetail = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				sqlDetail = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + rs.getString("ID_PERINTAHHTAOBMST") + "'";
				ResultSet rsDetail = stmt.executeQuery(sqlDetail);
				
				if (rsDetail.next()){
					bool = false;
				} else {
					return true;
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public boolean checkPembahagianHAPKTLengkap(String idPerintah) throws Exception{
		Db db = null;
		boolean bool = false;
		String sql = "";
		String sqlDetail = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				sqlDetail = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + rs.getString("ID_PERINTAHHAOBMST") + "'";
				ResultSet rsDetail = stmt.executeQuery(sqlDetail);
				
				if (rsDetail.next()){
					bool = false;
				} else {
					return true;
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		return bool;
	}
	
	public boolean checkPembahagianHTAPLLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 3 AND JENIS_LELONG IS NULL AND ID_PERINTAH = '" + idPerintah + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkPembahagianHAPLLengkap(String idPerintah) throws Exception{
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = 3 AND JENIS_LELONG IS NULL AND ID_PERINTAH = '" + idPerintah + "'";
	
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHTAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHTAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHAPT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 1"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkWarisYangPerluAdaPAHTAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPAHAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'T' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHTAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkWarisYangPerluAdaPTHAPF(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST AND B.ID_JENISPERINTAH = 7"
					+ " AND A.STATUS_TADBIR = 'Y' AND ID_PA1 IS NULL AND A.BA > 0 AND B.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHTAPTHilang(String idPerintahHTAOBMST, String idPerintah, String BA, String BB, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";
		
		listPentadbir = getPentadbir(idPerintah);
		
		for (int i = 0; i < listPentadbir.size(); i++) {
			Hashtable hash = (Hashtable) listPentadbir.get(i);
			if (i == 0){
				idPA1 = hash.get("idOB").toString();
			} else if (i == 1){
				idPA2 = hash.get("idOB").toString();
			} else if (i == 2){
				idPA3 = hash.get("idOB").toString();
			} else if (i == 3){
				idPA4 = hash.get("idOB").toString();
			}
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}
						
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
				
				fracWaris = fracWaris.times(fracSimati);
				
				sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBDTL
					r.update("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL"));
					
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
					stmt.executeUpdate(sql);
					
				} else {
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHTAOBDTL				
					long id = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
					r.add("ID_PERINTAHHTAOBDTL", id);
					r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
					stmt.executeUpdate(sql);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHTAPTHilang(String idHTA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHTAOBMST = '" + idHTA + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHTAOBDTL
				r.add("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL"));
				
				sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateHAPTHilang(String idPerintahHAOBMST, String idPerintah, String BA, String BB, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";
		
		listPentadbir = getPentadbir(idPerintah);
		
		for (int i = 0; i < listPentadbir.size(); i++) {
			Hashtable hash = (Hashtable) listPentadbir.get(i);
			if (i == 0){
				idPA1 = hash.get("idOB").toString();
			} else if (i == 1){
				idPA2 = hash.get("idOB").toString();
			} else if (i == 2){
				idPA3 = hash.get("idOB").toString();
			} else if (i == 3){
				idPA4 = hash.get("idOB").toString();
			}
		}

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rsHA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHA.next()){
				fracSimati = getFractionSimatiHA(rsHA.getString("ID_HA"),idPermohonanSimati);
			}
						
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
				
				fracWaris = fracWaris.times(fracSimati);
				
				sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHAOBDTL
					r.update("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL"));
					
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

					sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBDTL");
					stmt.executeUpdate(sql);
					
				} else {
					
					SQLRenderer r = new SQLRenderer();
					
					//TBLPPKPERINTAHHAOBDTL				
					long id = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
					r.add("ID_PERINTAHHAOBDTL", id);
					r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
					r.add("BA", BA);
					r.add("BB", BB);
					r.add("BA_WARIS", fracWaris.numerator());
					r.add("BB_WARIS", fracWaris.denominator());
					r.add("STATUS_TADBIR", "Y");
					r.add("ID_PA1", idPA1);
					r.add("ID_PA2", idPA2);
					r.add("ID_PA3", idPA3);
					r.add("ID_PA4", idPA4);
					
					r.add("ID_MASUK", userId);			
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
					stmt.executeUpdate(sql);
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHAPTHilang(String idHA) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_OB IS NULL AND ID_PERINTAHHAOBMST = '" + idHA + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHAOBDTL
				r.add("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL"));
				
				sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Fraction getFractionSimatiHTA(String idHTA,String id_permohonansimati) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT HTA.BA_SIMATI, HTA.BB_SIMATI " +
					" FROM TBLPPKHTA HTA1, TBLPPKHTAPERMOHONAN HTA " +
					" WHERE HTA.ID_PERMOHONANSIMATI = HTA1.ID_PERMOHONANSIMATI AND " +
					/** COMMENT BY PEJE - NAK CATER BAGI HARTA TERDAHULU (ID_PERMOHONAN SIMATI X SAMA DENGAN CURRENT)
					" HTA.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND " +
					**/
							" HTA1.ID_HTA = HTA.ID_HTA " +
							"AND HTA1.ID_HTA = '" + idHTA + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("sql simati===="+sql);

			if (rs.next()){
				if (rs.getString("BA_SIMATI") != null && rs.getString("BB_SIMATI") != null){
					if (rs.getLong("BB_SIMATI") > 0){
						Fraction f2 = new Fraction(rs.getLong("BA_SIMATI"),rs.getLong("BB_SIMATI"));
						fractionSimati = fractionSimati.times(f2);
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return fractionSimati;
	}
	
	public Fraction getFractionSimatiHA(String idHA,String id_permohonansimati) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT HA.BA_SIMATI, HA.BB_SIMATI " +
			" FROM TBLPPKHA HA1, TBLPPKHAPERMOHONAN HA " +
			" WHERE HA.ID_PERMOHONANSIMATI = HA1.ID_PERMOHONANSIMATI AND " +
			/** COMMENT BY PEJE - NAK CATER BAGI HARTA TERDAHULU (ID_PERMOHONAN SIMATI X SAMA DENGAN CURRENT)
			" HA.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' AND " +
			**/
					" HA1.ID_HA = HA.ID_HA " +
					"AND HA1.ID_HA = '" + idHA + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				if (rs.getString("BA_SIMATI") != null && rs.getString("BB_SIMATI") != null){
					if (rs.getLong("BB_SIMATI") > 0){
						Fraction f2 = new Fraction(rs.getLong("BA_SIMATI"),rs.getLong("BB_SIMATI"));
						fractionSimati = fractionSimati.times(f2);
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return fractionSimati;
	}
	
	public void setDataSenaraiHTAPKTTerDahulu(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPKTTerDahulu.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA "
					+ "FROM TBLPPKPERINTAHHTAOBMST A, " +
							" TBLPPKHTA B1,TBLPPKHTAPERMOHONAN B, " +
							" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, " +
							" TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G "
					+ "WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH "
					+ " AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) AND A.ID_PERINTAH = '" + idPerintah + "'  " +
							" AND A.ID_JENISPERINTAH = '2' AND A.FLAG_HARTA = 'L' " +
							" AND A.FLAG_BATAL IS NULL ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPKTTerDahulu.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		    	
		    	senaraiHTAPKTTerDahulu.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPATerDahulu(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPATerDahulu.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA "
					+ "FROM TBLPPKPERINTAHHTAOBMST A, " +
							" TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, " +
							" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, " +
							" TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G "
					+ "WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'" +
							" AND A.ID_HTA = B1.ID_HTA  AND B.ID_NEGERI = D.ID_NEGERI  " +
							" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
							" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH "
					+ " AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) AND A.ID_PERINTAH = '" + idPerintah + "'  " +
							" AND A.ID_JENISPERINTAH IN (1,7) " +
							" AND A.FLAG_HARTA = 'L' AND A.FLAG_BATAL IS NULL ORDER BY " +
							" A.ID_PERINTAHHTAOBMST ASC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPATerDahulu.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		    	
		    	senaraiHTAPATerDahulu.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPKTTerDahuluBB(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPKTTerDahuluBB.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA "
					+ "FROM TBLPPKPERINTAHHTAOBMST A, " +
							" TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B," +
							" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, " +
							" TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G "
					+ "WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
									" AND A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  " +
							" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
							" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH "
					+ " AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) " +
							" AND A.ID_PERINTAH = '" + idPerintah + "'  AND A.ID_JENISPERINTAH = '2' " +
									" AND A.FLAG_HARTA = 'L' AND A.FLAG_BATAL = 'BB' " +
									" ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPKTTerDahuluBB.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		    	
		    	senaraiHTAPKTTerDahuluBB.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHTAPKTTerDahuluBL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHTAPKTTerDahuluBL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT A.ID_PERINTAHHTAOBMST, C.KOD_JENIS_HAKMILIK, B.NO_HAKMILIK, D.NAMA_NEGERI, E.NAMA_DAERAH, F.NAMA_MUKIM, B.JENIS_HTA "
					+ "FROM TBLPPKPERINTAHHTAOBMST A, " +
							"TBLPPKHTA B1, TBLPPKHTAPERMOHONAN B, " +
							" TBLRUJJENISHAKMILIK C, TBLRUJNEGERI D, TBLRUJDAERAH E, " +
							" TBLRUJMUKIM F, TBLPPKRUJJENISPERINTAH G "
					+ "WHERE B1.ID_HTA = B.ID_HTA " +
							" AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"' " +
							" AND  A.ID_HTA = B1.ID_HTA  " +
							" AND B.ID_NEGERI = D.ID_NEGERI  " +
							" AND B.ID_DAERAH = E.ID_DAERAH  AND B.ID_MUKIM = F.ID_MUKIM  " +
							" AND A.ID_JENISPERINTAH = G.ID_JENISPERINTAH "
					+ " AND B.ID_JENISHM = C.ID_JENISHAKMILIK(+) AND A.ID_PERINTAH = '" + idPerintah + "'  " +
							" AND A.ID_JENISPERINTAH = '2' AND A.FLAG_HARTA = 'L' " +
							" AND A.FLAG_BATAL = 'BL' ORDER BY A.ID_PERINTAHHTAOBMST ASC";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST",rs.getString("ID_PERINTAHHTAOBMST") == null ? "" : rs.getString("ID_PERINTAHHTAOBMST"));
				if (rs.getString("JENIS_HTA").equals("Y")){
					h.put("keteranganHakmilik",rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null || rs.getString("NAMA_NEGERI") == null || 
							rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " 
									+ rs.getString("NO_HAKMILIK").toUpperCase() + ", " + rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
									+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else if (rs.getString("JENIS_HTA").equals("T")){
					h.put("keteranganHakmilik",rs.getString("NAMA_MUKIM") == null || rs.getString("NAMA_DAERAH") == null || rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase()
							+ ", " + rs.getString("NAMA_DAERAH").toUpperCase() + ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				} else {
					h.put("keteranganHakmilik", "");		  
				}
				
				senaraiHTAPKTTerDahuluBL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHTAOBMST","");
		    	h.put("keteranganHakmilik", "Tiada Rekod");		    	
		    	senaraiHTAPKTTerDahuluBL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPKTTerDahulu(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPKTTerDahulu.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			r.add("A.FLAG_HARTA", "L");
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1, TBLPPKHAPERMOHONAN B,TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPKTTerDahulu.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPKTTerDahulu.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPATerDahulu(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPATerDahulu.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PERINTAHHAOBMST, C.KETERANGAN, B.JENAMA, B.NO_DAFTAR "
					+ "FROM TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C "
					+ "WHERE C.ID_JENISHA = B.ID_JENISHA  " +
							" AND A.ID_HA = B1.ID_HA " +
							" AND B.ID_HA = B1.ID_HA AND B.ID_PERMOHONANSIMATI = '"+id_permohonansimati+"'  " +
							" AND A.ID_JENISPERINTAH IN (1,7)  AND A.FLAG_HARTA = 'L' AND A.ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPATerDahulu.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPATerDahulu.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPKTTerDahuluBB(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPKTTerDahuluBB.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			r.add("A.FLAG_HARTA", "L");
			r.add("A.FLAG_BATAL", "BB");
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, " +
					"TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPKTTerDahuluBB.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPKTTerDahuluBB.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiHAPKTTerDahuluBL(String idPerintah,String id_permohonansimati) throws Exception {
		Db db = null;
		senaraiHAPKTTerDahuluBL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERINTAHHAOBMST");
			r.add("C.KETERANGAN");
			r.add("B.JENAMA");
			r.add("B.NO_DAFTAR");

			r.add("C.ID_JENISHA",r.unquote("B.ID_JENISHA"));
			
			
			r.add("A.ID_PERINTAH", idPerintah);
			r.add("A.ID_JENISPERINTAH", "2");
			r.add("A.FLAG_HARTA", "L");
			r.add("A.FLAG_BATAL", "BL");
			
			r.add("A.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_HA",r.unquote("B1.ID_HA"));
			r.add("B.ID_PERMOHONANSIMATI",id_permohonansimati);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST A, TBLPPKHA B1,TBLPPKHAPERMOHONAN B, TBLPPKRUJJENISHA C","B.ID_JENISHA ASC");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("jenama",rs.getString("JENAMA") == null ? "" : rs.getString("JENAMA"));
				h.put("noDaftar",rs.getString("NO_DAFTAR") == null ? "" : rs.getString("NO_DAFTAR"));				
				senaraiHAPKTTerDahuluBL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idPerintahHAOBMST","");
		    	h.put("jenisHA", "Tiada Rekod");
		    	h.put("jenama","");
		    	h.put("noDaftar","");
		    	senaraiHAPKTTerDahuluBL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void batalDanBahagiHTAPKT(String idHTAMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.update("ID_PERINTAHHTAOBMST", idHTAMST);
			
			r.add("FLAG_BATAL", "BB");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void batalDanBahagiHAPKT(String idHAMST, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.update("ID_PERINTAHHAOBMST", idHAMST);
			
			r.add("FLAG_BATAL", "BB");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void batalDanLantikHTAPKT(String idHTAMST, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			r.update("ID_PERINTAHHTAOBMST", idHTAMST);
			
			r.add("FLAG_BATAL", "BL");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBMST
			long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_HTA", getidHTAFromIdPerintahHTAOBMST(idHTAMST));
			r.add("ID_PERINTAH", idPerintah);
			r.add("ID_JENISPERINTAH", "2");
			r.add("FLAG_HARTA", "B");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
			stmt.executeUpdate(sql);
			
			//INSERT PENTADBIR
			Vector listPentadbir = new Vector();
			listPentadbir = getPentadbir(idPerintah);
			
			for (int i = 0; i < listPentadbir.size(); i++){
				Hashtable hash = (Hashtable) listPentadbir.get(i);
				
				insertDTLHTAPerintahKuasaTadbir(idPerintahHTAOBMST,hash.get("idOB").toString(),session);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getidHTAFromIdPerintahHTAOBMST(String idHTAMST) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_HTA");
			
			r.add("ID_PERINTAHHTAOBMST", idHTAMST);

			sql = r.getSQLSelect("TBLPPKPERINTAHHTAOBMST");
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_HTA");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void batalDanLantikHAPKT(String idHAMST, String idPerintah, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			r.update("ID_PERINTAHHAOBMST", idHAMST);
			
			r.add("FLAG_BATAL", "BL");
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBMST
			long idPerintahHAOBMST = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_HA", getidHAFromIdPerintahHAOBMST(idHAMST));
			r.add("ID_PERINTAH", idPerintah);
			r.add("ID_JENISPERINTAH", "2");
			r.add("FLAG_HARTA", "B");
			
			r.add("ID_MASUK", userId);			
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");
			stmt.executeUpdate(sql);
			
			//INSERT PENTADBIR
			Vector listPentadbir = new Vector();
			listPentadbir = getPentadbir(idPerintah);
			
			for (int i = 0; i < listPentadbir.size(); i++){
				Hashtable hash = (Hashtable) listPentadbir.get(i);
				
				insertDTLHAPerintahKuasaTadbir(idPerintahHAOBMST,hash.get("idOB").toString(),session);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getidHAFromIdPerintahHAOBMST(String idHAMST) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("ID_HA");
			
			r.add("ID_PERINTAHHAOBMST", idHAMST);

			sql = r.getSQLSelect("TBLPPKPERINTAHHAOBMST");
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_HA");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public boolean checkExistPreviousRecordForHTAPKT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = '2' AND FLAG_HARTA = 'L' AND FLAG_BATAL IS NULL"
				+ " AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistPreviousRecordForHTAPA(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH IN (1,7) AND FLAG_HARTA = 'L' AND FLAG_BATAL IS NULL"
				+ " AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistPreviousRecordForHAPKT(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH = '2' AND FLAG_HARTA = 'L' AND FLAG_BATAL IS NULL"
				+ " AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkExistPreviousRecordForHAPA(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST WHERE ID_JENISPERINTAH IN (1,7) AND FLAG_HARTA = 'L' AND FLAG_BATAL IS NULL"
				+ " AND ID_PERINTAH = '" + idPerintah + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateCatatanPerintah(String idPerintah, String catatan, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAH
			r.update("ID_PERINTAH", idPerintah);
			
			r.add("CATATAN", catatan);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAH");
			myLogger.info("updateCatatanPerintah :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHTAOBDTL(String idPerintahHTAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHTAOBDTL
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHTAOBDTL");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePecahanWarisHTA(String idPerintahHTAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHTAOBDTL, ID_PERINTAHHTAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHHTAOBDTL", rs.getString("ID_PERINTAHHTAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHTAOBDTL"));
				list.addElement(h);				
			}
			    
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));
				
//				Hashtable k = (Hashtable) list.get(i);
//				Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
				list2 = pchn.addToList(list2);							
			}
			
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHHTAOBDTL SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHTAOBDTL = '"+ hash.get("ID_PERINTAHHTAOBDTL").toString()+ "'";
				myLogger.info("UPDATE PECAHAN WARIS :"+sql.toUpperCase());
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePecahanWarisHA(String idPerintahHAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHHAOBDTL, ID_PERINTAHHAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHHAOBDTL", rs.getString("ID_PERINTAHHAOBDTL") == null ? "" : rs.getString("ID_PERINTAHHAOBDTL"));
				list.addElement(h);				
			}
			    
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));
				list2 = pchn.addToList(list2);							
			}
			
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHHAOBDTL SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHAOBDTL = '"+ hash.get("ID_PERINTAHHAOBDTL").toString()+ "'";
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removeHAOBDTL(String idPerintahHAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHHAOBDTL
			r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHHAOBDTL");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private String getSenaraiIdPermohonanSimati(String idSimati, String idPermohonanSimati) throws Exception{
		
		Db db = null;
		String sql = "";
		String temp = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_SIMATI = '" + idSimati + "'"
					+ "AND NVL(A.NO_SUBJAKET,0) <= '" + getNoSubjaket(idSimati, idPermohonanSimati) + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){
				if ("".equals(temp)){
					temp = rs.getString("ID_PERMOHONANSIMATI");
				} else {
					temp = temp + "," + rs.getString("ID_PERMOHONANSIMATI");
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		if ("".equals(temp)){
			return "''";
		} else {
			return temp;
		}		
	}
	
	private String getNoSubjaket(String idSimati, String idPermohonanSimati) throws Exception{
		
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NVL(A.NO_SUBJAKET,0) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "' AND B.ID_SIMATI = '" + idSimati + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
		
			if (rs.next()){
				return rs.getString("NO_SUBJAKET");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getSenaraiFail_semakanPerintahHQ() {
		return senaraiFail_semakanPerintahHQ;
	}
	
	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTA() {
		return senaraiHTA;
	}

	public void setSenaraiHTA(Vector senaraiHTA) {
		this.senaraiHTA = senaraiHTA;
	}

	public Vector<Hashtable<String, String>> getSenaraiHTAPopup() {
		return senaraiHTAPopup;
	}

	public void setSenaraiHTAPopup(Vector senaraiHTAPopup) {
		this.senaraiHTAPopup = senaraiHTAPopup;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatHTA() {
		return beanMaklumatHTA;
	}

	public void setBeanMaklumatHTA(Vector beanMaklumatHTA) {
		this.beanMaklumatHTA = beanMaklumatHTA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTATH() {
		return senaraiHTATH;
	}

	public void setSenaraiHTATH(Vector senaraiHTATH) {
		this.senaraiHTATH = senaraiHTATH;
	}

	public Vector getBeanMaklumatHTATH() {
		return beanMaklumatHTATH;
	}

	public void setBeanMaklumatHTATH(Vector beanMaklumatHTATH) {
		this.beanMaklumatHTATH = beanMaklumatHTATH;
	}

	public Vector<Hashtable<String,String>> getSenaraiHA() {
		return senaraiHA;
	}

	public void setSenaraiHA(Vector senaraiHA) {
		this.senaraiHA = senaraiHA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPopup() {
		return senaraiHAPopup;
	}

	public void setSenaraiHAPopup(Vector senaraiHAPopup) {
		this.senaraiHAPopup = senaraiHAPopup;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHA() {
		return beanMaklumatHA;
	}

	public void setBeanMaklumatHA(Vector beanMaklumatHA) {
		this.beanMaklumatHA = beanMaklumatHA;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPT() {
		return senaraiHTAPT;
	}

	public void setSenaraiHTAPT(Vector senaraiHTAPT) {
		this.senaraiHTAPT = senaraiHTAPT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPT() {
		return senaraiHAPT;
	}

	public void setSenaraiHAPT(Vector senaraiHAPT) {
		this.senaraiHAPT = senaraiHAPT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPKT() {
		return senaraiHTAPKT;
	}

	public void setSenaraiHTAPKT(Vector senaraiHTAPKT) {
		this.senaraiHTAPKT = senaraiHTAPKT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPKT() {
		return senaraiHAPKT;
	}

	public void setSenaraiHAPKT(Vector senaraiHAPKT) {
		this.senaraiHAPKT = senaraiHAPKT;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPL() {
		return senaraiHTAPL;
	}

	public void setSenaraiHTAPL(Vector senaraiHTAPL) {
		this.senaraiHTAPL = senaraiHTAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPL() {
		return senaraiHAPL;
	}

	public void setSenaraiHAPL(Vector senaraiHAPL) {
		this.senaraiHAPL = senaraiHAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPF() {
		return senaraiHTAPF;
	}

	public void setSenaraiHTAPF(Vector senaraiHTAPF) {
		this.senaraiHTAPF = senaraiHTAPF;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPF() {
		return senaraiHAPF;
	}

	public void setSenaraiHAPF(Vector senaraiHAPF) {
		this.senaraiHAPF = senaraiHAPF;
	}

	public Vector<Hashtable<String,String>> getBeanHeaderDetail() {
		return beanHeaderDetail;
	}

	public void setBeanHeaderDetail(Vector beanHeaderDetail) {
		this.beanHeaderDetail = beanHeaderDetail;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPFDTL() {
		return senaraiHTAPFDTL;
	}

	public void setSenaraiHTAPFDTL(Vector senaraiHTAPFDTL) {
		this.senaraiHTAPFDTL = senaraiHTAPFDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPFDTL() {
		return senaraiHAPFDTL;
	}

	public void setSenaraiHAPFDTL(Vector senaraiHAPFDTL) {
		this.senaraiHAPFDTL = senaraiHAPFDTL;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHTAPL() {
		return beanMaklumatHTAPL;
	}

	public void setBeanMaklumatHTAPL(Vector beanMaklumatHTAPL) {
		this.beanMaklumatHTAPL = beanMaklumatHTAPL;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHAPL() {
		return beanMaklumatHAPL;
	}

	public void setBeanMaklumatHAPL(Vector beanMaklumatHAPL) {
		this.beanMaklumatHAPL = beanMaklumatHAPL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPTDTL() {
		return senaraiHTAPTDTL;
	}

	public void setSenaraiHTAPTDTL(Vector senaraiHTAPTDTL) {
		this.senaraiHTAPTDTL = senaraiHTAPTDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPTDTL() {
		return senaraiHAPTDTL;
	}

	public void setSenaraiHAPTDTL(Vector senaraiHAPTDTL) {
		this.senaraiHAPTDTL = senaraiHAPTDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHTAPTDTLHilang() {
		return senaraiHTAPTDTLHilang;
	}

	public void setSenaraiHTAPTDTLHilang(Vector senaraiHTAPTDTLHilang) {
		this.senaraiHTAPTDTLHilang = senaraiHTAPTDTLHilang;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPTDTLHilang() {
		return senaraiHAPTDTLHilang;
	}

	public void setSenaraiHAPTDTLHilang(Vector senaraiHAPTDTLHilang) {
		this.senaraiHAPTDTLHilang = senaraiHAPTDTLHilang;
	}

	public Vector getSenaraiHTAPKTTerDahulu() {
		return senaraiHTAPKTTerDahulu;
	}

	public void setSenaraiHTAPKTTerDahulu(Vector senaraiHTAPKTTerDahulu) {
		this.senaraiHTAPKTTerDahulu = senaraiHTAPKTTerDahulu;
	}

	public Vector getSenaraiHAPKTTerDahulu() {
		return senaraiHAPKTTerDahulu;
	}

	public void setSenaraiHAPKTTerDahulu(Vector senaraiHAPKTTerDahulu) {
		this.senaraiHAPKTTerDahulu = senaraiHAPKTTerDahulu;
	}

	public Vector getSenaraiHTAPKTTerDahuluBB() {
		return senaraiHTAPKTTerDahuluBB;
	}

	public void setSenaraiHTAPKTTerDahuluBB(Vector senaraiHTAPKTTerDahuluBB) {
		this.senaraiHTAPKTTerDahuluBB = senaraiHTAPKTTerDahuluBB;
	}

	public Vector getSenaraiHTAPKTTerDahuluBL() {
		return senaraiHTAPKTTerDahuluBL;
	}

	public void setSenaraiHTAPKTTerDahuluBL(Vector senaraiHTAPKTTerDahuluBL) {
		this.senaraiHTAPKTTerDahuluBL = senaraiHTAPKTTerDahuluBL;
	}

	public Vector getSenaraiHAPKTTerDahuluBB() {
		return senaraiHAPKTTerDahuluBB;
	}

	public void setSenaraiHAPKTTerDahuluBB(Vector senaraiHAPKTTerDahuluBB) {
		this.senaraiHAPKTTerDahuluBB = senaraiHAPKTTerDahuluBB;
	}

	public Vector getSenaraiHAPKTTerDahuluBL() {
		return senaraiHAPKTTerDahuluBL;
	}

	public void setSenaraiHAPKTTerDahuluBL(Vector senaraiHAPKTTerDahuluBL) {
		this.senaraiHAPKTTerDahuluBL = senaraiHAPKTTerDahuluBL;
	}

	public Vector getSenaraiHTAPATerDahulu() {
		return senaraiHTAPATerDahulu;
	}

	public void setSenaraiHTAPATerDahulu(Vector senaraiHTAPATerDahulu) {
		this.senaraiHTAPATerDahulu = senaraiHTAPATerDahulu;
	}

	public Vector getSenaraiHAPATerDahulu() {
		return senaraiHAPATerDahulu;
	}

	public void setSenaraiHAPATerDahulu(Vector senaraiHAPATerDahulu) {
		this.senaraiHAPATerDahulu = senaraiHAPATerDahulu;
	}
	
	public Vector<Hashtable<String,String>> getSenaraiHTAPKTDTL() {
		return senaraiHTAPKTDTL;
	}

	public void setSenaraiHTAPKTDTL(Vector senaraiHTAPKTDTL) {
		this.senaraiHTAPKTDTL = senaraiHTAPKTDTL;
	}

	public Vector<Hashtable<String,String>> getSenaraiHAPKTDTL() {
		return senaraiHAPKTDTL;
	}

	public void setSenaraiHAPKTDTL(Vector senaraiHAPKTDTL) {
		this.senaraiHAPKTDTL = senaraiHAPKTDTL;
	}
	
	/*class Pecahan {

		private long pengangka;
		private long penyebut;

		public void setPengangka(long a) {
			this.pengangka = a;
		}

		public long getPengangka() {
			return pengangka;
		}

		public void setPenyebut(long b) {
			this.penyebut = b;
		}

		public long getPenyebut() {
			return penyebut;
		}

		public Pecahan(long a, long b) {
			if (b == 0){
				b = 1;
			}
			setPengangka(a);
			setPenyebut(b);
		}
		
		private long gcd (long a, long b) {
			  
			  if (b==0) 
				  return a;
			   return gcd(b,a%b);
		  }
		
		public Vector addToList(Vector list){
			
			if (list.size() == 0){
				list.add(this);
				
			} else {
				long pembawahBaru = 1;
				long pembawahLama = 1;
				
				boolean update = false;
				
				
				Pecahan pchn0 = (Pecahan) list.get(0);
					if (pchn0.getPenyebut() != this.getPenyebut()){
						update = true;
						pembawahBaru = this.getPenyebut();
						pembawahLama = pchn0.getPenyebut();
					}
					
				if (update){
					
					//update pecahan dlm list
					for (int i = 0; i < list.size(); i++){
						Pecahan pchn1 = (Pecahan) list.get(i);
						pchn1.setPengangka(pchn1.getPengangka() * pembawahBaru);
						pchn1.setPenyebut(pchn1.getPenyebut() * pembawahBaru);
						list.set(i, pchn1);
					}
					
					//update pecahan yang terbaru
					this.setPengangka(this.getPengangka() * pembawahLama);
					this.setPenyebut(this.getPenyebut() * pembawahLama);					
				}
				
				list.add(this);
				
				//get gcd
				long temp = this.getPenyebut();
				for (int j = 0; j < list.size(); j++) {
					Pecahan pchn2 = (Pecahan) list.get(j);
					if (pchn2.getPengangka() > 0){
						temp = gcd(temp,pchn2.getPengangka());
					}
				}

				//kecikkan
				if (temp == 0){
					temp = 1;
				}
				for (int k = 0; k < list.size(); k++) {
					Pecahan pchn3 = (Pecahan) list.get(k);
					pchn3.setPengangka(pchn3.getPengangka() / temp);
					pchn3.setPenyebut(pchn3.getPenyebut() / temp);
					list.set(k, pchn3);
				}
			}

			return list;
		}
	}*/
	
	
	
	public void updateFlagBatalHTA_insert(String id_perintah,String id_harta,String idOBMST, String flag_harta,String jenis_harta, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_harta.equals("HTA"))
			{
				r.update("ID_PERINTAHHTAOBMST", idOBMST);
				r.add("FLAG_BATAL", "BB");			
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
				myLogger.info(" updateFlagBatal HTA:"+sql.toUpperCase());
				stmt.executeUpdate(sql);	
				
				
				String sql_total = " SELECT COUNT(*) AS TOTAL FROM TBLPPKPERINTAHHTAOBMST " +
						" WHERE ID_HTA = '"+id_harta+"' AND FLAG_HARTA = 'B' AND ID_PERINTAH = '"+id_perintah+"' ";
				ResultSet rs = stmt.executeQuery(sql_total);
				int total = 0;
				while (rs.next()) {
					total = rs.getInt("TOTAL");
				}
				myLogger.info("TOTAL :"+total);
				if(jenis_harta.equals("T"))
				{
					if(total==0)
					{
					r.clear();
					r.add("ID_HTA",id_harta);	
					r.add("ID_PERINTAH",id_perintah);
					r.add("ID_JENISPERINTAH","2");
					r.add("FLAG_HARTA","B");
					r.add("FLAG_BATAL","BL");
					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));					
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));					
					sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
					myLogger.info("------- updateFlagBatal Insert HTA:"+sql.toUpperCase());
					stmt.executeUpdate(sql);
					}
				}
				
			}
			
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateFlagBatalHTA_clear(String jenis_perintah,String id_perintah,String id_harta,String idOBMST, String flag_harta,String jenis_harta, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";

		try {
			
			db = new Db();
			Statement stmt1 = db.getStatement();
			Statement stmt2 = db.getStatement();
			Statement stmt3 = db.getStatement();
			SQLRenderer r = new SQLRenderer();
		
			sql1 = "DELETE FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST IN ( "+
			  "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
			  " WHERE ((FLAG_HARTA IN ('B') AND FLAG_BATAL IS NOT NULL) OR FLAG_HARTA IN ('M')) " +
			  " AND ID_PERINTAH = '"+id_perintah+"' AND ID_HTA = '"+id_harta+"')";
	    	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHTAOBDTL :"+sql1);
	    	stmt1.executeUpdate(sql1);
			
			sql2 = "DELETE FROM TBLPPKPERINTAHHTAOBMST WHERE ((FLAG_HARTA IN ('B') AND FLAG_BATAL IS NOT NULL) OR   FLAG_HARTA IN ('M')) " +
					" AND ID_PERINTAH = '"+id_perintah+"'  AND ID_HTA = '"+id_harta+"' " +
					"  ";
	    	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHTAOBMST :"+sql2);
	    	stmt2.executeUpdate(sql2);
			
	    	if(jenis_perintah.equals("2"))
	    	{
				if(flag_harta.equals("HTA"))
				{
					r.update("ID_PERINTAHHTAOBMST", idOBMST);
					r.add("FLAG_BATAL", "BB");			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql3 = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
					myLogger.info(" updateFlagBatal HTA:"+sql3.toUpperCase());
					stmt3.executeUpdate(sql3);	
					
					if(jenis_harta.equals("Y"))
					{
										
					}
					
				}
	    	}
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void updateDataBatalLantik_clear(String id_perintah,String id_harta,String idOBMST, String flag_harta,String jenis_harta, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";

		try {
			
			db = new Db();
			Statement stmt1 = db.getStatement();
			Statement stmt2 = db.getStatement();
			Statement stmt3 = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql1 = "DELETE FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST IN ( "+
				    "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '"+id_perintah+"' AND ID_HTA = '"+id_harta+"')";
	    	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHTAOBDTL :"+sql1);
	    	stmt1.executeUpdate(sql1);
			
			sql2 = "DELETE FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'B' AND ID_PERINTAH = '"+id_perintah+"' " +
					" AND ID_HTA = '"+id_harta+"' ";
	    	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHTAOBMST :"+sql2);
	    	stmt2.executeUpdate(sql2);
			/*
			if(flag_harta.equals("HTA"))
			{
				r.update("ID_PERINTAHHTAOBMST", idOBMST);
				r.add("FLAG_BATAL", "BB");			
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql3 = r.getSQLUpdate("TBLPPKPERINTAHHTAOBMST");
				myLogger.info(" updateFlagBatal HTA:"+sql3.toUpperCase());
				stmt3.executeUpdate(sql3);	
				
				if(jenis_harta.equals("Y"))
				{
									
				}
				
			}			
			*/
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void updateFlagBatalHA_insert(String id_perintah,String id_harta,String idOBMST, String flag_harta,String jenis_harta, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_harta.equals("HA"))
			{
				r.update("ID_PERINTAHHAOBMST", idOBMST);
				r.add("FLAG_BATAL", "BB");	
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
				myLogger.info(" updateFlagBatal HA:"+sql.toUpperCase());
				stmt.executeUpdate(sql);				
			}
		
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateFlagBatalHA_clear(String jenis_perintah,String id_perintah,String id_harta,String idOBMST, String flag_harta,String jenis_harta, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql1 = "";
		String sql2 = "";
		String sql3 = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql1 = "DELETE FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST IN ( "+
			  "SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
			  " WHERE ((FLAG_HARTA IN ('B') AND FLAG_BATAL IS NOT NULL) OR   FLAG_HARTA IN ('M')) " +
			  " AND ID_PERINTAH = '"+id_perintah+"' AND ID_HA = '"+id_harta+"' " +
			  		")";
		  	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHAOBDTL :"+sql1);
		  	stmt.executeUpdate(sql1);
				
			sql2 = "DELETE FROM TBLPPKPERINTAHHAOBMST WHERE ((FLAG_HARTA IN ('B') AND FLAG_BATAL IS NOT NULL) OR   FLAG_HARTA IN ('M')) " +
					" AND ID_PERINTAH = '"+id_perintah+"' AND ID_HA = '"+id_harta+"' ";
		  	myLogger.info("DELETE FROM FROM TBLPPKPERINTAHHAOBMST :"+sql2);
		  	stmt.executeUpdate(sql2);
			
		  	if(jenis_perintah.equals("2"))
		  	{
				if(flag_harta.equals("HA"))
				{
					r.update("ID_PERINTAHHAOBMST", idOBMST);
					r.add("FLAG_BATAL", "");			
					r.add("ID_KEMASKINI", userId);
					r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
					sql3 = r.getSQLUpdate("TBLPPKPERINTAHHAOBMST");
					myLogger.info(" updateFlagBatal HA:"+sql3.toUpperCase());
					stmt.executeUpdate(sql3);				
				}
		  	}
			
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void createMasterHTA(String idPerintah, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";
        
        String idHTAMSTMaster = "";
        String idPilihanHTA = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              //LIST PILIHANHTA BY ID_PERMOHONANSIMATI
              sql = "SELECT ID_PILIHANHTA, ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
              ResultSet rsPilihanHTA = stmt.executeQuery(sql);
              
              while (rsPilihanHTA.next()){
            	  idPilihanHTA = rsPilihanHTA.getString("ID_PILIHANHTA");
            	  //GET OLD PERINTAHOBMST BY ID_HTA
            	  sql = "SELECT ID_PERINTAHHTAOBMST, ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE FLAG_HARTA = 'L' AND ID_JENISPERINTAH = 1 AND ID_HTA = '" + rsPilihanHTA.getString("ID_HTA") + "'";
            	  ResultSet rsOldOBMST = stmt.executeQuery(sql);
            	  
            	  if (rsOldOBMST.next()){
            		  idHTAMSTMaster = insertIntoTblPPKPerintahHTAOBMSTMaster(rsOldOBMST.getString("ID_PERINTAHHTAOBMST"), idPerintah, userId);
            		  insertHTADTLMaster(idPilihanHTA, idHTAMSTMaster, 
            				  rsOldOBMST.getString("ID_PERINTAHHTAOBMST"), rsOldOBMST.getString("ID_HTA"), idPerintah, userId);
            	  }
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }		
	}	

	private String insertIntoTblPPKPerintahHTAOBMSTMaster(String idHTAOBMSTOld, String idPerintah, String userId) throws Exception {
		Db db = null;
		String sql = "";
		String sqlInsert = "";
		String idHTAMSTNew = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idHTAOBMSTOld + "'";
			ResultSet rs = stmt.executeQuery(sql);				
		
			if (rs.next()){
				
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHTAOBMST
				long idPerintahHTAOBMST = DB.getNextID("TBLPPKPERINTAHHTAOBMST_SEQ");
				idHTAMSTNew = String.valueOf(idPerintahHTAOBMST);
				
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_HTA", rs.getString("ID_HTA"));
				r.add("ID_PERINTAH", idPerintah);
				r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				r.add("TARIKH_JUALAN", rs.getDate("TARIKH_JUALAN") == null ? "" : rs.getDate("TARIKH_JUALAN"));
				r.add("AMAUN", rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
				r.add("JENIS_LELONG", rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				r.add("HARGA_RIZAB", rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
				r.add("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));					
				r.add("FLAG_HARTA", "M");		

				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHTAOBMST");
				stmt.executeUpdate(sqlInsert);	
			}
		
		} finally {
			if (db != null)
				db.close();
		}
		return idHTAMSTNew;
	}
	
	private static void insertHTADTLMaster(String idPilihanHTA, String idHTAMSTMaster, String idPerintahHTAOBMSTOld, String idHTA, String idPerintah, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_PERINTAHHTAOBDTL, B.ID_OB FROM TBLPPKPERINTAHHTAOBMST A, TBLPPKPERINTAHHTAOBDTL B WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST"
				+ " AND A.FLAG_HARTA = 'B' AND A.ID_JENISPERINTAH = 1 AND A.FLAG_BATAL IS NOT NULL AND B.STATUS_TADBIR IS NOT NULL AND A.ID_PERINTAH = '" + idPerintah + "'"
				
				+ " UNION"
				
				+ " SELECT ID_PERINTAHHTAOBDTL, ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMSTOld + "'"
				+ " AND ID_PERINTAHHTAOBDTL NOT IN (SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKOBPILIHANHTA WHERE ID_PILIHANHTA = '" + idPilihanHTA + "')";
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				insertIntoTblPPKPerintahHTAOBDTL(rs.getString("ID_PERINTAHHTAOBDTL"), idHTAMSTMaster, rs.getString("ID_OB"), userId);					
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void insertIntoTblPPKPerintahHTAOBDTL(String idHTADTLOld, String idHTAMSTMaster, String idOb, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idHTADTLOld + "'";	
			ResultSet rs = stmt.executeQuery(sql);
		
			if (rs.next()){
				
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHTAOBDTL
				long idPerintahHTAOBDTL = DB.getNextID("TBLPPKPERINTAHHTAOBDTL_SEQ");
				
				r.add("ID_PERINTAHHTAOBDTL", idPerintahHTAOBDTL);					
				r.add("ID_PERINTAHHTAOBMST", idHTAMSTMaster);
				
				if (idOb != null && idOb.trim().length() > 4){					
					if ("9999".equals(idOb.substring(0, 4))){
						r.add("ID_OB", "");
					} else {
						r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
					}
				} else {
					r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				}
								
				r.add("BA", rs.getString("BA") == null ? "" : rs.getString("BA"));
				r.add("BB", rs.getString("BB") == null ? "" : rs.getString("BB"));
				r.add("STATUS_TADBIR", rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				r.add("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				r.add("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				r.add("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				r.add("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
				
				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHTAOBDTL");
				stmt.executeUpdate(sql);						
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void createMasterHA(String idPerintah, String idPermohonanSimati, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
        String sql = "";
        
        String idHAMSTMaster = "";
        String idPilihanHA = "";

        try {
              db = new Db();
              Statement stmt = db.getStatement();
              
              //LIST PILIHANHA BY ID_PERMOHONANSIMATI
              sql = "SELECT ID_PILIHANHA, ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
              ResultSet rsPilihanHA = stmt.executeQuery(sql);
              
              while (rsPilihanHA.next()){
            	  idPilihanHA = rsPilihanHA.getString("ID_PILIHANHA");
            	  //GET OLD PERINTAHOBMST BY ID_HA
            	  sql = "SELECT ID_PERINTAHHAOBMST, ID_HA FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'L' AND ID_JENISPERINTAH = 1 AND ID_HA = '" + rsPilihanHA.getString("ID_HA") + "'";
            	  ResultSet rsOldOBMST = stmt.executeQuery(sql);
            	  
            	  if (rsOldOBMST.next()){
            		  idHAMSTMaster = insertIntoTblPPKPerintahHAOBMSTMaster(rsOldOBMST.getString("ID_PERINTAHHAOBMST"), idPerintah, userId);
            		  insertHADTLMaster(idPilihanHA, idHAMSTMaster, 
            				  rsOldOBMST.getString("ID_PERINTAHHAOBMST"), rsOldOBMST.getString("ID_HA"), idPerintah, userId);
            	  }
              }
              
              
        } finally {
              if (db != null)
                    db.close();
        }		
	}	

	private String insertIntoTblPPKPerintahHAOBMSTMaster(String idHAOBMSTOld, String idPerintah, String userId) throws Exception {
		Db db = null;
		String sql = "";
		String sqlInsert = "";
		String idHAMSTNew = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAHHAOBMST = '" + idHAOBMSTOld + "'";
			ResultSet rs = stmt.executeQuery(sql);				
		
			if (rs.next()){
				
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHAOBMST
				long idPerintahHAOBMST = DB.getNextID("TBLPPKPERINTAHHAOBMST_SEQ");
				idHAMSTNew = String.valueOf(idPerintahHAOBMST);
				
				r.add("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
				r.add("ID_HA", rs.getString("ID_HA"));
				r.add("ID_PERINTAH", idPerintah);
				r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				r.add("TARIKH_JUALAN", rs.getDate("TARIKH_JUALAN") == null ? "" : rs.getDate("TARIKH_JUALAN"));
				r.add("AMAUN", rs.getString("AMAUN") == null ? "" : rs.getString("AMAUN"));
				r.add("JENIS_LELONG", rs.getString("JENIS_LELONG") == null ? "" : rs.getString("JENIS_LELONG"));
				r.add("HARGA_RIZAB", rs.getString("HARGA_RIZAB") == null ? "" : rs.getString("HARGA_RIZAB"));
				r.add("ID_JENISPERINTAH", rs.getString("ID_JENISPERINTAH") == null ? "" : rs.getString("ID_JENISPERINTAH"));					
				r.add("FLAG_HARTA", "M");		

				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sqlInsert = r.getSQLInsert("TBLPPKPERINTAHHAOBMST");
				stmt.executeUpdate(sqlInsert);	
			}
		
		} finally {
			if (db != null)
				db.close();
		}
		return idHAMSTNew;
	}
	
	private static void insertHADTLMaster(String idPilihanHA, String idHAMSTMaster, String idPerintahHAOBMSTOld, String idHA, String idPerintah, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT B.ID_PERINTAHHAOBDTL, B.ID_OB FROM TBLPPKPERINTAHHAOBMST A, TBLPPKPERINTAHHAOBDTL B WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST"
				+ " AND A.FLAG_HARTA = 'B' AND A.ID_JENISPERINTAH = 1 AND A.FLAG_BATAL IS NOT NULL AND B.STATUS_TADBIR IS NOT NULL AND A.ID_PERINTAH = '" + idPerintah + "'"
				
				+ " UNION"
				
				+ " SELECT ID_PERINTAHHAOBDTL, ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMSTOld + "'"
				+ " AND ID_PERINTAHHAOBDTL NOT IN (SELECT ID_PERINTAHHAOBDTL FROM TBLPPKOBPILIHANHA WHERE ID_PILIHANHA = '" + idPilihanHA + "')";
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				insertIntoTblPPKPerintahHAOBDTL(rs.getString("ID_PERINTAHHAOBDTL"), idHAMSTMaster, rs.getString("ID_OB"), userId);					
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void insertIntoTblPPKPerintahHAOBDTL(String idHADTLOld, String idHAMSTMaster, String idOb, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT * FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idHADTLOld + "'";	
			ResultSet rs = stmt.executeQuery(sql);
		
			if (rs.next()){
				
				SQLRenderer r = new SQLRenderer();
				
				//TBLPPKPERINTAHHAOBDTL
				long idPerintahHAOBDTL = DB.getNextID("TBLPPKPERINTAHHAOBDTL_SEQ");
				
				r.add("ID_PERINTAHHAOBDTL", idPerintahHAOBDTL);					
				r.add("ID_PERINTAHHAOBMST", idHAMSTMaster);
				if (idOb != null && idOb.trim().length() > 4){					
					if ("9999".equals(idOb.substring(0, 4))){
						r.add("ID_OB", "");
					} else {
						r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
					}
				} else {
					r.add("ID_OB", rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				}			
				r.add("BA", rs.getString("BA") == null ? "" : rs.getString("BA"));
				r.add("BB", rs.getString("BB") == null ? "" : rs.getString("BB"));
				r.add("STATUS_TADBIR", rs.getString("STATUS_TADBIR") == null ? "" : rs.getString("STATUS_TADBIR"));
				r.add("CATATAN", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				r.add("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));
				r.add("ID_PA2", rs.getString("ID_PA2") == null ? "" : rs.getString("ID_PA2"));
				r.add("ID_PA3", rs.getString("ID_PA3") == null ? "" : rs.getString("ID_PA3"));
				r.add("ID_PA4", rs.getString("ID_PA4") == null ? "" : rs.getString("ID_PA4"));
				
				r.add("ID_MASUK", userId);			
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHHAOBDTL");
				stmt.executeUpdate(sql);						
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiPembahagianHTAPKTDTL(String idPerintahHTAOBMST, String idSimati, String idPermohonanSimati) throws Exception {
		Db db = null;
		senaraiPembahagianHTAPKTDTL.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						"WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB " +
						" AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.STATUS_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " (SELECT A.BA FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BA,"
				+ " (SELECT A.BB FROM TBLPPKPERINTAHPEMBAHAGIAN A, TBLPPKPERINTAHHTAOBMST B " +
						" WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST AND A.ID_OB = OB1.ID_OB " +
						" AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "') AS BB"
				+ " FROM TBLPPKOB OB1,TBLPPKOBPERMOHONAN OB " +
						" WHERE OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			myLogger.info("###setDataSenaraiHTAPTDTL( :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();
				
				h.put("bil", bil);
				h.put("idOB",rs.getString("ID_OB") == null ? "" : rs.getString("ID_OB"));
				h.put("namaWaris",rs.getString("NAMA_OB") == null ? "" : rs.getString("NAMA_OB"));
				h.put("BA",rs.getString("BA") == null ? "0" : rs.getString("BA"));
				h.put("BB",rs.getString("BB") == null ? "0" : rs.getString("BB"));
				if (rs.getString("STATUS_OB") != null){
					if (rs.getString("STATUS_OB").equals("1")){
						h.put("status","");
					} else if (rs.getString("STATUS_OB").equals("2")){
						h.put("status","BELUM DEWASA");
					} else if (rs.getString("STATUS_OB").equals("3")){
						h.put("status","HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN");
					} else if (rs.getString("STATUS_OB").equals("4")){
						h.put("status","TIDAK SEMPURNA AKAL");
					} else {
						h.put("status","");
					}
				} else {
					h.put("status","");
				}
				h.put("statusHidup",rs.getString("STATUS_HIDUP") == null ? "" : rs.getString("STATUS_HIDUP"));
				senaraiPembahagianHTAPKTDTL.addElement(h);
				bil++;
				count++;
			}
			
			if (count == 0) {
				h = new Hashtable();

		    	h.put("bil","");
		    	h.put("idOB","");
		    	h.put("namaWaris","Tiada Rekod");
		    	h.put("BA","0");
		    	h.put("BB","0");
		    	h.put("status","");
		    	h.put("statusHidup","");
		    	senaraiPembahagianHTAPKTDTL.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePembahagianHTAPKT(String idPerintahHTAOBMST, String idOB, String status, String BA, String BB, String idPerintah, HttpSession session,String idPermohonanSimati) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		Vector listPentadbir = new Vector();
		listPentadbir.clear();
		Vector listPemegangAmanah = new Vector();
		listPemegangAmanah.clear();
		String idPA1 = "";
		String idPA2 = "";
		String idPA3 = "";
		String idPA4 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//GET FRACTION SIMATI
			sql = "SELECT ID_HTA FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			Fraction fracSimati = new Fraction(1,1);
			if (rsHTA.next()){
				fracSimati = getFractionSimatiHTA(rsHTA.getString("ID_HTA"),idPermohonanSimati);
			}
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);
			
			//GET ROW OB
			sql = "SELECT ID_PERINTAHPEMBAHAGIAN FROM TBLPPKPERINTAHPEMBAHAGIAN WHERE ID_OB = '" + idOB + "' AND ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rsRowOB = stmt.executeQuery(sql);
			
			if (rsRowOB.next()){
				//UPDATE TBLPPKPERINTAHPEMBAHAGIAN		
				r.update("ID_PERINTAHPEMBAHAGIAN", rsRowOB.getString("ID_PERINTAHPEMBAHAGIAN"));
				
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");					
				} else {
					r.add("STATUS_TADBIR", "");
				}
		
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPPKPERINTAHPEMBAHAGIAN");
				stmt.executeUpdate(sql);
				
			} else {
				//NEW TBLPPKPERINTAHPEMBAHAGIAN				
				long idPerintahPembahagian = DB.getNextID("TBLPPKPERINTAHPEMBAHAGIAN_SEQ");
				r.add("ID_PERINTAHPEMBAHAGIAN", idPerintahPembahagian);
				r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
				r.add("ID_OB", idOB);
				r.add("BA", BA);
				r.add("BB", BB);
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());
				if (status.equals("BELUM DEWASA") || status.equals("TIDAK SEMPURNA AKAL") || status.equals("HILANG / MENINGGAL DUNIA / TIDAK DAPAT DIKESAN")){
					r.add("STATUS_TADBIR", "T");					
				} else {
					r.add("STATUS_TADBIR", "");
				}
				
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPPKPERINTAHPEMBAHAGIAN");
				stmt.executeUpdate(sql);
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePecahanWarisHTAPKT(String idPerintahHTAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		Vector list = new Vector();
		Vector list2 = new Vector();
		
		list.clear();
		list2.clear();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERINTAHPEMBAHAGIAN, ID_PERINTAHHTAOBMST, BB_WARIS, BA_WARIS FROM TBLPPKPERINTAHPEMBAHAGIAN WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			while (rs.next()){					
				h = new Hashtable();
				h.put("BA_WARIS", rs.getString("BA_WARIS") == null ? "" : rs.getString("BA_WARIS"));
				h.put("BB_WARIS", rs.getString("BB_WARIS") == null ? "" : rs.getString("BB_WARIS"));
				h.put("ID_PERINTAHPEMBAHAGIAN", rs.getString("ID_PERINTAHPEMBAHAGIAN") == null ? "" : rs.getString("ID_PERINTAHPEMBAHAGIAN"));
				list.addElement(h);				
			}
			    
			for (int i = 0; i < list.size(); i++) {
				Hashtable k = (Hashtable) list.get(i);
				//Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
				Pecahan pchn = new Pecahan(BigInteger.valueOf(Long.parseLong(k.get("BA_WARIS").toString())), BigInteger.valueOf(Long.parseLong(k.get("BB_WARIS").toString())));
				list2 = pchn.addToList(list2);							
			}
			
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHPEMBAHAGIAN SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHPEMBAHAGIAN = '"+ hash.get("ID_PERINTAHPEMBAHAGIAN").toString()+ "'";
				stmt.executeQuery(sql);	
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void removePembahagianHTAPKT(String idPerintahHTAOBMST, String idOB) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPPKPERINTAHPEMBAHAGIAN
			r.add("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			r.add("ID_OB", idOB);
			
			sql = r.getSQLDelete("TBLPPKPERINTAHPEMBAHAGIAN");
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoFailByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("NO_FAIL");
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkGeneratedPerintah(String noFail) throws DbException, Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
		
			sql = "SELECT H.NEGERI FROM INT_PPKPERINTAH P, INT_PPKHAKMILIKPERINTAH H WHERE P.ID_PPKPERINTAH = H.ID_PPKPERINTAH AND H.FLAG_HANTAR = 'Y' AND P.NO_FAIL = '" + noFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				return true;
			} else {
				return false;
			}
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//INTERGRASI ETANAH
		public void janaPerintah(String idFail, String idPermohonan, String idPermohonanSimati, String idPerbicaraan, String idPerintah) throws Exception {
			String sql = "";
			Db db = null;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			try {
							
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "DELETE FROM INT_PPKBORANGH WHERE ID_PPKBORANGE IN (SELECT ID_PPKBORANGE FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'))))";
				stmt.executeUpdate(sql);
				sql = "DELETE FROM INT_PPKBORANGE WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')))";
				stmt.executeUpdate(sql);
				sql = "DELETE FROM INT_PPKBORANGF WHERE ID_PPKHAKMILIKPERINTAH IN (SELECT ID_PPKHAKMILIKPERINTAH FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')))";
				stmt.executeUpdate(sql);
				sql = "DELETE FROM INT_PPKHAKMILIKPERINTAH WHERE ID_PPKPERINTAH IN (SELECT ID_PPKPERINTAH FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "'))";
				stmt.executeUpdate(sql);
				sql = "DELETE FROM INT_PPKPERINTAH WHERE NO_FAIL = (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail + "')";
				stmt.executeUpdate(sql);
				
				sql = "SELECT FAIL.NO_FAIL, SIMATI.NAMA_SIMATI,"
						+ " CASE"
						+ " WHEN SIMATI.NO_KP_BARU IS NOT NULL THEN SIMATI.NO_KP_BARU"
						+ " WHEN SIMATI.NO_KP_LAMA IS NOT NULL THEN SIMATI.NO_KP_LAMA"
						+ " WHEN SIMATI.NO_KP_LAIN IS NOT NULL THEN SIMATI.NO_KP_LAIN"
						+ " END AS NO_KP_SIMATI,"
						+ " SIMATI.TARIKH_MATI, SIMATI.NO_SIJIL_MATI,"
						+ " PEMOHON.NAMA_PEMOHON,"
						+ " CASE"
						+ " WHEN PEMOHON.NO_KP_BARU IS NOT NULL THEN PEMOHON.NO_KP_BARU"
						+ " WHEN PEMOHON.NO_KP_LAMA IS NOT NULL THEN PEMOHON.NO_KP_LAMA"
						+ " WHEN PEMOHON.NO_KP_LAIN IS NOT NULL THEN PEMOHON.NO_KP_LAIN"
						+ " END AS NO_KP_PEMOHON, PEMOHON.ALAMAT_1 AS ALAMAT_PEMOHON1, PEMOHON.ALAMAT_2 AS ALAMAT_PEMOHON2, PEMOHON.ALAMAT_3 AS ALAMAT_PEMOHON3, PEMOHON.POSKOD AS POSKOD_PEMOHON,"
						+ " BICARA.TEMPAT_BICARA, BICARA.ALAMAT_BICARA1, BICARA.ALAMAT_BICARA2, BICARA.ALAMAT_BICARA3, BICARA.POSKOD AS POSKOD_BICARA, BICARA.BANDAR AS BANDAR_BICARA, NEGERIBICARA.KOD_NEGERI AS NEGERI_BICARA, BICARA.PEG_PENGENDALI,"
						+ " PERINTAH.TARIKH_PERINTAH"
						+ " FROM TBLPFDFAIL FAIL, TBLPPKPERMOHONAN MOHON, TBLPPKPERMOHONANSIMATI MOHONSIMATI, TBLPPKSIMATI SIMATI, TBLPPKPEMOHON PEMOHON,TBLPPKKEPUTUSANPERMOHONAN KEPUTUSANMOHON, TBLPPKPERBICARAAN BICARA, TBLRUJNEGERI NEGERIBICARA, TBLPPKPERINTAH PERINTAH"
						+ " WHERE FAIL.ID_FAIL = MOHON.ID_FAIL"
						+ " AND MOHON.ID_PERMOHONAN = MOHONSIMATI.ID_PERMOHONAN"
						+ " AND MOHONSIMATI.ID_SIMATI = SIMATI.ID_SIMATI"
						+ " AND MOHON.ID_PEMOHON = PEMOHON.ID_PEMOHON"
						+ " AND MOHON.ID_PERMOHONAN = KEPUTUSANMOHON.ID_PERMOHONAN"
						+ " AND KEPUTUSANMOHON.ID_KEPUTUSANPERMOHONAN = BICARA.ID_KEPUTUSANPERMOHONAN"
						+ " AND NEGERIBICARA.ID_NEGERI = BICARA.ID_NEGERIBICARA(+)"
						+ " AND BICARA.ID_PERBICARAAN = PERINTAH.ID_PERBICARAAN"
						+ " AND FAIL.ID_FAIL = '" + idFail + "'"
						+ " AND BICARA.ID_PERBICARAAN = '" + idPerbicaraan + "'"
						+ " AND PERINTAH.ID_PERINTAH = '" + idPerintah + "'";
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()){
					//INT_PPKPERINTAH				
					long idPPKPerintah = DB.getNextID("INT_PPKPERINTAH_SEQ");
					r.add("ID_PPKPERINTAH", idPPKPerintah);
					r.add("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
					r.add("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI").toUpperCase());
					r.add("NO_KPSIMATI", rs.getString("NO_KP_SIMATI") == null ? "" : rs.getString("NO_KP_SIMATI").toUpperCase());
					r.add("TARIKH_MATI", sdf.format(rs.getDate("TARIKH_MATI")));
					r.add("NO_SIJILMATI", rs.getString("NO_SIJIL_MATI") == null ? "" : rs.getString("NO_SIJIL_MATI").toUpperCase());
					r.add("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON").toUpperCase());
					r.add("NO_KPPEMOHON", rs.getString("NO_KP_PEMOHON") == null ? "" : rs.getString("NO_KP_PEMOHON").toUpperCase());				
					r.add("ALAMAT_PEMOHON1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1").toUpperCase());;
					r.add("ALAMAT_PEMOHON2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2").toUpperCase());
					r.add("ALAMAT_PEMOHON3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3").toUpperCase());
					r.add("POSKOD_PEMOHON", rs.getString("POSKOD_PEMOHON") == null ? "" : rs.getString("POSKOD_PEMOHON"));
					r.add("TEMPAT_BICARA", rs.getString("TEMPAT_BICARA") == null ? "" : rs.getString("TEMPAT_BICARA").toUpperCase());;
					r.add("ALAMAT_BICARA1", rs.getString("ALAMAT_BICARA1") == null ? "" : rs.getString("ALAMAT_BICARA1").toUpperCase());				
					r.add("ALAMAT_BICARA2", rs.getString("ALAMAT_BICARA2") == null ? "" : rs.getString("ALAMAT_BICARA2").toUpperCase());
					r.add("ALAMAT_BICARA3", rs.getString("ALAMAT_BICARA3") == null ? "" : rs.getString("ALAMAT_BICARA3").toUpperCase());;
					r.add("POSKOD_BICARA", rs.getString("POSKOD_BICARA") == null ? "" : rs.getString("POSKOD_BICARA"));
					r.add("BANDAR_BICARA", rs.getString("BANDAR_BICARA") == null ? "" : rs.getString("BANDAR_BICARA").toUpperCase());;
					r.add("NEGERI_BICARA", rs.getString("NEGERI_BICARA") == null ? "" : rs.getString("NEGERI_BICARA").toUpperCase());
					r.add("PEGAWAI_BICARA", rs.getString("PEG_PENGENDALI") == null ? "" : rs.getString("PEG_PENGENDALI").toUpperCase());
					r.add("TARIKH_PERINTAH", rs.getString("TARIKH_PERINTAH") == null ? "" : sdf.format(rs.getDate("TARIKH_PERINTAH")));

					sql = r.getSQLInsert("INT_PPKPERINTAH");
					stmt.executeUpdate(sql);
					
					sql = "SELECT OBMST.ID_PERINTAHHTAOBMST, OBMST.ID_JENISPERINTAH, HTA.ID_JENISHM, HTA.NO_HAKMILIK, NEGERI.KOD_NEGERI AS NEGERI, DAERAH.KOD_DAERAH AS DAERAH, MUKIM.KOD_MUKIM AS MUKIM, HTA.ID_LUAS, HTA.LUAS, HTA.NO_PT, HTA.BA_SIMATI, HTA.BB_SIMATI"
							+ " FROM TBLPPKPERINTAHHTAOBMST OBMST, TBLPPKHTA HTA, TBLRUJNEGERI NEGERI, TBLRUJDAERAH DAERAH, TBLRUJMUKIM MUKIM"
							+ " WHERE OBMST.ID_HTA = HTA.ID_HTA"
							+ " AND HTA.ID_NEGERI = NEGERI.ID_NEGERI(+)"
							+ " AND HTA.ID_DAERAH = DAERAH.ID_DAERAH(+)"
							+ " AND HTA.ID_MUKIM = MUKIM.ID_MUKIM(+)"
							+ " AND HTA.JENIS_HTA = 'Y'"
							+ " AND OBMST.FLAG_HARTA = 'B'"
							+ " AND OBMST.ID_PERINTAH = '" + idPerintah + "'";
					ResultSet rsHakmilik = stmt.executeQuery(sql);
					
					while (rsHakmilik.next()){
						inserHakmilikPerintah(idPPKPerintah, rsHakmilik.getString("ID_PERINTAHHTAOBMST"), rsHakmilik.getString("ID_JENISHM"), rsHakmilik.getString("NO_HAKMILIK"), rsHakmilik.getString("ID_LUAS"), rsHakmilik.getString("LUAS"), rsHakmilik.getString("NEGERI"), rsHakmilik.getString("DAERAH"), rsHakmilik.getString("MUKIM"), 
								rsHakmilik.getString("NO_PT"), rsHakmilik.getString("BA_SIMATI"), rsHakmilik.getString("BB_SIMATI"), rsHakmilik.getString("ID_JENISPERINTAH"));
					}
				}			
				
			}  finally {
				if (db != null)
					db.close();
			}	
		}


		private void inserHakmilikPerintah(long idPPKPerintah, String idPerintahHTAOBMST, String idJenisHakmilik,
				String noHakmilik, String idLuas, String luas, String negeri,
				String daerah, String mukim, String noPT, String BA,
				String BB, String idJenisPerintah) throws Exception {
			String sql = "";
			Db db = null;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Fraction fracSimati = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracSimati = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}

			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				// INT_PPKHAKMILIKPERINTAH
				long idPPKHakmilikPerintah = DB
						.getNextID("INT_PPKHAKMILIKPERINTAH_SEQ");
				r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);
				r.add("ID_PPKPERINTAH", idPPKPerintah);
				r.add("ID_HAKMILIK", negeri.trim()
						+ daerah.trim() + mukim.trim()
						+ getKodJenisHakmilik(idJenisHakmilik).trim()
						+ Utils.digitLastFormatted(noHakmilik, 8));
				r.add("ID_JENISHAKMILIK", idJenisHakmilik == null ? "" : idJenisHakmilik);
				r.add("NO_HAKMILIK", noHakmilik == null ? "" : noHakmilik);
				r.add("ID_LUAS", idLuas == null ? "" : idLuas);
				r.add("LUAS", luas == null ? "" : luas);
				r.add("NEGERI", negeri == null ? "" : negeri);
				r.add("DAERAH", daerah == null ? "" : daerah);
				r.add("MUKIM", mukim == null ? "" : mukim);
				r.add("NO_LOT", noPT == null ? "" : noPT);
				r.add("BA_SIMATI", BA == null ? "" : BA);
				r.add("BB_SIMATI", BB == null ? "" : BB);	
				r.add("ID_JENIS_PERINTAH", idJenisPerintah == null ? "" : idJenisPerintah);	
				if ("1".equals(idJenisPerintah)) {
					r.add("JENIS_PEMBAHAGIAN", "1");
				} else if ("2".equals(idJenisPerintah)) {
					r.add("JENIS_PEMBAHAGIAN", "3");
				}

				sql = r.getSQLInsert("INT_PPKHAKMILIKPERINTAH");
				stmt.executeUpdate(sql);
				
				if ("1".equals(idJenisPerintah)) {
					//GENERATE DATA BORANG E
					sql = "SELECT OBDTL.ID_PERINTAHHTAOBDTL, OB.NAMA_OB,"
							+ " CASE"
							+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
					        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
					        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
					        + " END AS JENIS_PENGENALAN,"						
							+ " CASE"
							+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
							+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
							+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
							+ " END AS NO_KP_OB,"
							+ " OB.STATUS_OB, OBDTL.BA, OBDTL.BB,"
							+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
							+ " CASE"
							+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
							+ " ELSE 'MALAYSIA'"
					        + " END AS WARGANEGARA,"
							+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
							+ " CASE"
							+ " WHEN OB.JANTINA = 1 THEN 'L'"
					        + " WHEN OB.JANTINA = 2 THEN 'P'"
					        + " ELSE ''"
					        + " END AS JANTINA"
							+ " FROM TBLPPKPERINTAHHTAOBDTL OBDTL, TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
							+ " WHERE OBDTL.ID_OB = OB.ID_OB AND OB.STATUS_OB IS NOT NULL AND OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OBDTL.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
					ResultSet rsOB = stmt.executeQuery(sql);
					
					while (rsOB.next()){
						insertBorangE(idPPKHakmilikPerintah, rsOB.getString("ID_PERINTAHHTAOBDTL"), rsOB.getString("NAMA_OB"), rsOB.getString("JENIS_PENGENALAN"), rsOB.getString("NO_KP_OB"), rsOB.getString("STATUS_OB"), rsOB.getString("BA"), rsOB.getString("BB"),
								rsOB.getString("ALAMAT1"), rsOB.getString("ALAMAT2"), rsOB.getString("ALAMAT3"), rsOB.getString("POSKOD"), rsOB.getString("BANDAR"), rsOB.getString("NEGERI"), rsOB.getString("WARGANEGARA"),
								rsOB.getString("UMUR"), rsOB.getString("ID_TARAFKPTG"), rsOB.getString("ID_SAUDARA"), rsOB.getString("JANTINA"), rsOB.getDate("TARIKH_LAHIR"), fracSimati);
					}
				} else if ("2".equals(idJenisPerintah)) {
					//GENERATE DATA BORANG F
					sql = "SELECT OB.NAMA_OB,"
							+ " CASE"
							+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
					        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
					        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
					        + " END AS JENIS_PENGENALAN,"						
							+ " CASE"
							+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
							+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
							+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
							+ " END AS NO_KP_OB,"
							+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
							+ " CASE"
							+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
							+ " ELSE 'MALAYSIA'"
					        + " END AS WARGANEGARA,"
							+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
							+ " CASE"
							+ " WHEN OB.JANTINA = 1 THEN 'L'"
					        + " WHEN OB.JANTINA = 2 THEN 'P'"
					        + " ELSE ''"
					        + " END AS JANTINA"
							+ " FROM TBLPPKPERINTAHHTAOBDTL OBDTL, TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
							+ " WHERE OBDTL.ID_OB = OB.ID_OB AND OB.STATUS_OB IS NOT NULL AND OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OBDTL.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
					ResultSet rsOB = stmt.executeQuery(sql);
					
					while (rsOB.next()){
						insertBorangF(idPPKHakmilikPerintah, rsOB.getString("NAMA_OB"), rsOB.getString("JENIS_PENGENALAN"), rsOB.getString("NO_KP_OB"),
								rsOB.getString("ALAMAT1"), rsOB.getString("ALAMAT2"), rsOB.getString("ALAMAT3"), rsOB.getString("POSKOD"), rsOB.getString("BANDAR"), rsOB.getString("NEGERI"), rsOB.getString("WARGANEGARA"),
								rsOB.getString("UMUR"), rsOB.getString("ID_TARAFKPTG"), rsOB.getString("ID_SAUDARA"), rsOB.getString("JANTINA"), rsOB.getDate("TARIKH_LAHIR"));
					}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private void insertBorangE(long idPPKHakmilikPerintah, String idPerintahHTAOBDTL, String namaOB, String jenisKPOB, String noKPOB, String statusOB, String BA, String BB, String alamat1, String alamat2, String alamat3, String poskod, 
				String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir, Fraction fracSimati) throws Exception {
			String sql = "";
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Fraction fracWaris = new Fraction(0,1);
			if (Utils.parseLong(BB) > 0){
				fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));
			}
			fracWaris = fracWaris.times(fracSimati);

			
			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				// INT_PPKBORANGE
				long idPPKBorangE = DB
						.getNextID("INT_PPKBORANGE_SEQ");
				r.add("ID_PPKBORANGE", idPPKBorangE);
				r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);			
				
				r.add("NAMA_OB", namaOB == null ? "" : namaOB);
				r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
				r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
				r.add("STATUS_OB", statusOB == null ? "" : statusOB);
				r.add("BA", BA == null ? "" : BA);
				r.add("BB", BB == null ? "" : BB);			
				r.add("BA_WARIS", fracWaris.numerator());
				r.add("BB_WARIS", fracWaris.denominator());			
				r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
				r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
				r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
				r.add("POSKOD", poskod == null ? "" : poskod);
				r.add("BANDAR", bandar == null ? "" : bandar);
				r.add("NEGERI", negeri == null ? "" : negeri);
				r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
				r.add("UMUR", umur == null ? "" : umur);
				r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
				r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
				r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
				r.add("JANTINA", jantina == null ? "" : jantina);
				
				sql = r.getSQLInsert("INT_PPKBORANGE");
				stmt.executeUpdate(sql);
				
				if (!"1".equals(statusOB)){
					sql = "SELECT ID_PA1, ID_PA2, ID_PA3, ID_PA4 FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idPerintahHTAOBDTL + "'";
					ResultSet rsPA = stmt.executeQuery(sql);
					
					if (rsPA.next()) {
						String idPA1 = rsPA.getString("ID_PA1");
						String idPA2 = rsPA.getString("ID_PA2");
						String idPA3 = rsPA.getString("ID_PA3");
						String idPA4 = rsPA.getString("ID_PA4");
						
						// START PA1
						if (idPA1 != null && idPA1.length() > 0){
							sql = "SELECT OB.NAMA_OB,"
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
							        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
							        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
							        + " END AS JENIS_PENGENALAN,"						
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
									+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
									+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
									+ " END AS NO_KP_OB,"
									+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
									+ " CASE"
									+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
									+ " ELSE 'MALAYSIA'"
							        + " END AS WARGANEGARA,"
									+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
									+ " CASE"
									+ " WHEN OB.JANTINA = 1 THEN 'L'"
							        + " WHEN OB.JANTINA = 2 THEN 'P'"
							        + " ELSE ''"
							        + " END AS JANTINA"
									+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
									+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA1 + "'";
							ResultSet rsPA1 = stmt.executeQuery(sql);
							if (rsPA1.next()){
								insertBorangH(idPPKBorangE, rsPA1.getString("NAMA_OB"), rsPA1.getString("JENIS_PENGENALAN"), rsPA1.getString("NO_KP_OB"),
										rsPA1.getString("ALAMAT1"), rsPA1.getString("ALAMAT2"), rsPA1.getString("ALAMAT3"), rsPA1.getString("POSKOD"), rsPA1.getString("BANDAR"), rsPA1.getString("NEGERI"), rsPA1.getString("WARGANEGARA"),
										rsPA1.getString("UMUR"), rsPA1.getString("ID_TARAFKPTG"), rsPA1.getString("ID_SAUDARA"), rsPA1.getString("JANTINA"), rsPA1.getDate("TARIKH_LAHIR"));
							}
						}
						
						// START PA2
						if (idPA2 != null && idPA2.length() > 0){
							sql = "SELECT OB.NAMA_OB,"
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
							        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
							        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
							        + " END AS JENIS_PENGENALAN,"						
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
									+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
									+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
									+ " END AS NO_KP_OB,"
									+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
									+ " CASE"
									+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
									+ " ELSE 'MALAYSIA'"
							        + " END AS WARGANEGARA,"
									+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
									+ " CASE"
									+ " WHEN OB.JANTINA = 1 THEN 'L'"
							        + " WHEN OB.JANTINA = 2 THEN 'P'"
							        + " ELSE ''"
							        + " END AS JANTINA"
									+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
									+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA2 + "'";
							ResultSet rsPA2 = stmt.executeQuery(sql);
							if (rsPA2.next()){
								insertBorangH(idPPKBorangE, rsPA2.getString("NAMA_OB"), rsPA2.getString("JENIS_PENGENALAN"), rsPA2.getString("NO_KP_OB"),
										rsPA2.getString("ALAMAT1"), rsPA2.getString("ALAMAT2"), rsPA2.getString("ALAMAT3"), rsPA2.getString("POSKOD"), rsPA2.getString("BANDAR"), rsPA2.getString("NEGERI"), rsPA2.getString("WARGANEGARA"),
										rsPA2.getString("UMUR"), rsPA2.getString("ID_TARAFKPTG"), rsPA2.getString("ID_SAUDARA"), rsPA2.getString("JANTINA"), rsPA2.getDate("TARIKH_LAHIR"));
							}
						}
						
						// START PA3
						if (idPA3 != null && idPA3.length() > 0){
							sql = "SELECT OB.NAMA_OB,"
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
							        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
							        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
							        + " END AS JENIS_PENGENALAN,"						
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
									+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
									+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
									+ " END AS NO_KP_OB,"
									+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
									+ " CASE"
									+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
									+ " ELSE 'MALAYSIA'"
							        + " END AS WARGANEGARA,"
									+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
									+ " CASE"
									+ " WHEN OB.JANTINA = 1 THEN 'L'"
							        + " WHEN OB.JANTINA = 2 THEN 'P'"
							        + " ELSE ''"
							        + " END AS JANTINA"
									+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
									+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA3 + "'";
							ResultSet rsPA3 = stmt.executeQuery(sql);
							if (rsPA3.next()){
								insertBorangH(idPPKBorangE, rsPA3.getString("NAMA_OB"), rsPA3.getString("JENIS_PENGENALAN"), rsPA3.getString("NO_KP_OB"),
										rsPA3.getString("ALAMAT1"), rsPA3.getString("ALAMAT2"), rsPA3.getString("ALAMAT3"), rsPA3.getString("POSKOD"), rsPA3.getString("BANDAR"), rsPA3.getString("NEGERI"), rsPA3.getString("WARGANEGARA"),
										rsPA3.getString("UMUR"), rsPA3.getString("ID_TARAFKPTG"), rsPA3.getString("ID_SAUDARA"), rsPA3.getString("JANTINA"), rsPA3.getDate("TARIKH_LAHIR"));
							}
						}
						
						// START PA4
						if (idPA4 != null && idPA4.length() > 0){
							sql = "SELECT OB.NAMA_OB,"
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN '1'"
							        + " WHEN OB.NO_KP_LAMA IS NOT NULL THEN '2'"
							        + " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.JENIS_KP"
							        + " END AS JENIS_PENGENALAN,"						
									+ " CASE"
									+ " WHEN OB.NO_KP_BARU IS NOT NULL THEN OB.NO_KP_BARU"
									+ " WHEN OB.NO_KP_LAMA IS NOT NULL THEN OB.NO_KP_LAMA"
									+ " WHEN OB.NO_KP_LAIN IS NOT NULL THEN OB.NO_KP_LAIN"
									+ " END AS NO_KP_OB,"
									+ " OB.ALAMAT_1 AS ALAMAT1, OB.ALAMAT_2 AS ALAMAT2, OB.ALAMAT_3 AS ALAMAT3, OB.POSKOD, BANDAR.KETERANGAN AS BANDAR, NEGERI.KOD_NEGERI AS NEGERI,"
									+ " CASE"
									+ " WHEN NAMA_WARGA IS NOT NULL THEN NAMA_WARGA"
									+ " ELSE 'MALAYSIA'"
							        + " END AS WARGANEGARA,"
									+ " OB.UMUR, OB.TARIKH_LAHIR, OB.ID_TARAFKPTG, OB.ID_SAUDARA,"
									+ " CASE"
									+ " WHEN OB.JANTINA = 1 THEN 'L'"
							        + " WHEN OB.JANTINA = 2 THEN 'P'"
							        + " ELSE ''"
							        + " END AS JANTINA"
									+ " FROM TBLPPKOB OB, TBLRUJBANDAR BANDAR, TBLRUJNEGERI NEGERI"
									+ " WHERE OB.ID_BANDAR = BANDAR.ID_BANDAR(+) AND OB.ID_NEGERI = NEGERI.ID_NEGERI(+) AND OB.ID_OB = '" + idPA4 + "'";
							ResultSet rsPA4 = stmt.executeQuery(sql);
							if (rsPA4.next()){
								insertBorangH(idPPKBorangE, rsPA4.getString("NAMA_OB"), rsPA4.getString("JENIS_PENGENALAN"), rsPA4.getString("NO_KP_OB"),
										rsPA4.getString("ALAMAT1"), rsPA4.getString("ALAMAT2"), rsPA4.getString("ALAMAT3"), rsPA4.getString("POSKOD"), rsPA4.getString("BANDAR"), rsPA4.getString("NEGERI"), rsPA4.getString("WARGANEGARA"),
										rsPA4.getString("UMUR"), rsPA4.getString("ID_TARAFKPTG"), rsPA4.getString("ID_SAUDARA"), rsPA4.getString("JANTINA"), rsPA4.getDate("TARIKH_LAHIR"));
							}
						}					
						
					}
				}
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private void insertBorangH(long idPPKBorangE, String namaOB, String jenisKPOB, String noKPOB, String alamat1, String alamat2, String alamat3, String poskod, 
				String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir) throws Exception {
			String sql = "";
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				// INT_PPKBORANGH
				long idPPKBorangH = DB
						.getNextID("INT_PPKBORANGH_SEQ");
				r.add("ID_PPKBORANGH", idPPKBorangH);
				r.add("ID_PPKBORANGE", idPPKBorangE);			
				
				r.add("NAMA_OB", namaOB == null ? "" : namaOB);
				r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
				r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
				r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
				r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
				r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
				r.add("POSKOD", poskod == null ? "" : poskod);
				r.add("BANDAR", bandar == null ? "" : bandar);
				r.add("NEGERI", negeri == null ? "" : negeri);
				r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
				r.add("UMUR", umur == null ? "" : umur);
				r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
				r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
				r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
				r.add("JANTINA", jantina == null ? "" : jantina);
				
				sql = r.getSQLInsert("INT_PPKBORANGH");
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		private void insertBorangF(long idPPKHakmilikPerintah, String namaOB, String jenisKPOB, String noKPOB, String alamat1, String alamat2, String alamat3, String poskod, 
				String bandar, String negeri, String warganegara, String umur, String idTarafKPTG, String idSaudara, String jantina, Date tarikhLahir) throws Exception {
			String sql = "";
			Db db = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				// INT_PPKBORANGF
				long idPPKBorangF = DB
						.getNextID("INT_PPKBORANGF_SEQ");
				r.add("ID_PPKBORANGF", idPPKBorangF);
				r.add("ID_PPKHAKMILIKPERINTAH", idPPKHakmilikPerintah);			
				
				r.add("NAMA_OB", namaOB == null ? "" : namaOB);
				r.add("JENIS_PENGENALAN", jenisKPOB == null ? "" : jenisKPOB);
				r.add("NO_KPOB", noKPOB == null ? "" : noKPOB);
				r.add("ALAMAT1", alamat1 == null ? "" : alamat1);
				r.add("ALAMAT2", alamat2 == null ? "" : alamat2);
				r.add("ALAMAT3", alamat3 == null ? "" : alamat3);
				r.add("POSKOD", poskod == null ? "" : poskod);
				r.add("BANDAR", bandar == null ? "" : bandar);
				r.add("NEGERI", negeri == null ? "" : negeri);
				r.add("WARGANEGARA", warganegara == null ? "" : warganegara);
				r.add("UMUR", umur == null ? "" : umur);
				r.add("TARIKH_LAHIR", tarikhLahir == null ? "" : sdf.format(tarikhLahir));
				r.add("ID_TARAFKPTG", idTarafKPTG == null ? "" : idTarafKPTG);
				r.add("ID_SAUDARA", idSaudara == null ? "" : idSaudara);
				r.add("JANTINA", jantina == null ? "" : jantina);
				
				sql = r.getSQLInsert("INT_PPKBORANGF");
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '" + idJenisHakmilik + "'";
				
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()){
					return (String)rs.getString("KOD_JENIS_HAKMILIK");
				} else {
					return "";
				}
				
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		
		//aishah add
		
		 public void updateHAAfterSelesai(String idPerintah, HttpSession session,String id_permohonansimati) throws Exception {
		        Db db = null;
		        String sql = "";

		        try {
		              db = new Db();
		              Statement stmt = db.getStatement();
		              
		            //PERINTAH KUASA TADBIR
		              sql = "SELECT ID_PERINTAHHAOBMST, ID_HA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'B' AND ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'"
		              
		              	+ " UNION"
		              
		              	//HARTA TERTINGGAL
		              	+ " SELECT ID_PERINTAHHAOBMST, ID_HA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'B' AND ID_JENISPERINTAH != 2 AND FLAG_BATAL IS NULL AND ID_PERINTAH = '" + idPerintah + "'"
		              	
		              	+ " UNION"
		                
		              	//PERINTAH TERUS MASTER
		              	+ " SELECT ID_PERINTAHHAOBMST, ID_HA, ID_JENISPERINTAH FROM TBLPPKPERINTAHHAOBMST WHERE FLAG_HARTA = 'M' AND ID_JENISPERINTAH != 2 AND ID_PERINTAH = '" + idPerintah + "'";
		              
		              ResultSet rs = stmt.executeQuery(sql);
		              
		              while (rs.next()){
		                    if (rs.getString("ID_JENISPERINTAH") != null){
		                          updateFlagSelesaiHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
		                          if (rs.getString("ID_JENISPERINTAH").equals("1") || rs.getString("ID_JENISPERINTAH").equals("7")){
		                                if (checkForPTHA(rs.getString("ID_PERINTAHHAOBMST"))){
		                                      updateFlagPTHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
		                                }
		                                if (checkForPAHA(rs.getString("ID_PERINTAHHAOBMST"))){
		                                      updateFlagPAHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
		                                }                                   
		                          } else if (rs.getString("ID_JENISPERINTAH").equals("2")){
		                                updateFlagPTHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session,id_permohonansimati);
		                          }
		                    }                       
		                    updateIdPerintahOBMSTForHA(rs.getString("ID_PERINTAHHAOBMST"), rs.getString("ID_HA"), session, id_permohonansimati);
		                    updateHAPermohonan(rs.getString("ID_HA"), session, id_permohonansimati);
		              }
		              
		              
		        } finally {
		              if (db != null)
		                    db.close();
		        }
		  }


	public Vector<Hashtable<String,String>> getSenaraiPembahagianHTAPKTDTL() {
		return senaraiPembahagianHTAPKTDTL;
	}


	public void setSenaraiPembahagianHTAPKTDTL(Vector senaraiPembahagianHTAPKTDTL) {
		this.senaraiPembahagianHTAPKTDTL = senaraiPembahagianHTAPKTDTL;
	}
}