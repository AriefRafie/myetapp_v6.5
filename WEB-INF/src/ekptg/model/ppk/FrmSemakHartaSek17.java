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

public class FrmSemakHartaSek17 {
	

	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLogger = Logger.getLogger(FrmTukaranStatus.class);
	
		
	
	
	public static  Vector listLatestHta(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listLatestHta = null;
		listLatestHta = new Vector();
		listLatestHta.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT HTA.ID_HTA,HTA.ID_PERMOHONANSIMATI,MST.ID_PERINTAHHTAOBMST, PP.ID_PERMOHONAN,NVL(PP.NO_SUBJAKET,0) AS NO_SUBJAKET "+ 
				" FROM TBLPPKPERMOHONANSIMATI PS,TBLPPKPERMOHONAN PP,TBLPPKKEPUTUSANPERMOHONAN KP, "+
				" TBLPPKPERBICARAAN BB,TBLPPKPERINTAH PR,TBLPPKPERINTAHHTAOBMST MST,TBLPPKHTA HTA "+
				" WHERE PP.ID_PERMOHONAN = KP.ID_PERMOHONAN AND BB.ID_PERBICARAAN = PR.ID_PERBICARAAN "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = BB.ID_KEPUTUSANPERMOHONAN AND PR.ID_PERINTAH  = MST.ID_PERINTAH "+
				" AND PS.ID_PERMOHONAN = PP.ID_PERMOHONAN AND HTA.ID_PERINTAHOBMST = MST.ID_PERINTAHHTAOBMST  "+
				" AND PS.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') "+
				" AND NVL(PP.NO_SUBJAKET,0) =    "+
				" (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONANSIMATI SSM,TBLPPKPERMOHONAN PPP "+
				" WHERE SSM.ID_PERMOHONAN = PPP.ID_PERMOHONAN AND SSM.ID_SIMATI IN "+
				" (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"')) ";
				if(flag_daftar.equals("BARU"))
				{
				sql += "-1";	
				}
				
				sql += " AND HTA.ID_HTA = '"+id_harta+"'";
				//--IF PERMOHONAN DAH DAFTAR,SUBKAJET KENA -1";
			myLogger.info("SQL listLatestHta :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			Hashtable h = new Hashtable();	
				
			if (rs.getString("ID_HTA") == null) {
					h.put("ID_HTA", "");
			} else {
					h.put("ID_HTA", rs.getString("ID_HTA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHHTAOBMST") == null) {
					h.put("ID_PERINTAHHTAOBMST", "");
			} else {
					h.put("ID_PERINTAHHTAOBMST", rs.getString("ID_PERINTAHHTAOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			listLatestHta.addElement(h);
			}
			return listLatestHta;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public static Vector listCurrentHta(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listCurrentHta = null;
		listCurrentHta = new Vector();
		listCurrentHta.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT HTA.ID_HTA,PS.ID_PERMOHONANSIMATI,MST.ID_PERINTAHHTAOBMST, PP.ID_PERMOHONAN,NVL(PP.NO_SUBJAKET,0) AS NO_SUBJAKET "+
				" FROM TBLPPKPERMOHONANSIMATI PS,TBLPPKPERMOHONAN PP,TBLPPKKEPUTUSANPERMOHONAN KP, "+
				" TBLPPKPERBICARAAN BB,TBLPPKPERINTAH PR,TBLPPKPERINTAHHTAOBMST MST,TBLPPKHTA HTA "+
				" WHERE PP.ID_PERMOHONAN = KP.ID_PERMOHONAN AND BB.ID_PERBICARAAN = PR.ID_PERBICARAAN "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = BB.ID_KEPUTUSANPERMOHONAN AND PR.ID_PERINTAH  = MST.ID_PERINTAH "+
				" AND PS.ID_PERMOHONAN = PP.ID_PERMOHONAN AND HTA.ID_PERINTAHOBMST = MST.ID_PERINTAHHTAOBMST  "+
				" AND PS.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') "+
				" AND NVL(PP.NO_SUBJAKET,0) =    "+
				" (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN PPP "+
				" WHERE PPP.ID_PERMOHONAN = '"+id_permohonan+"') ";
			if(flag_daftar.equals("BARU"))
			{
			sql += "-1";	
			}
			sql += " AND HTA.ID_HTA = '"+id_harta+"'";
				//--IF PERMOHONAN DAH DAFTAR,SUBKAJET KENA -1";
			myLogger.info("SQL listCurrentHta :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			Hashtable h = new Hashtable();	
				
			if (rs.getString("ID_HTA") == null) {
				h.put("ID_HTA", "");
			} else {
					h.put("ID_HTA", rs.getString("ID_HTA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHHTAOBMST") == null) {
					h.put("ID_PERINTAHHTAOBMST", "");
			} else {
					h.put("ID_PERINTAHHTAOBMST", rs.getString("ID_PERINTAHHTAOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			
			
			listCurrentHta.addElement(h);
			
			}
			return listCurrentHta;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public static Vector listCurrentSelepasNofailHta(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listCurrentSelepasNofailHta = null;
		listCurrentSelepasNofailHta = new Vector();
		listCurrentSelepasNofailHta.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT PHTA.ID_HTA,PSM.ID_PERMOHONANSIMATI,PHTA.ID_PERINTAHOBMST,P.ID_PERMOHONAN,P.NO_SUBJAKET "+
					" FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERMOHONANSIMATI PSM,TBLPPKHTAPERMOHONAN PHTA "+
					" WHERE P.ID_PERMOHONAN = '"+id_permohonan+"' "+
					" AND F.ID_FAIL = P.ID_FAIL AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = PHTA.ID_PERMOHONANSIMATI "+
					" AND PHTA.ID_HTA = '"+id_harta+"' ";
					myLogger.info("SQL listCurrentSelepasNofailHta :"+sql);
					myLogger.info("LUAR");
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				myLogger.info("DALAM ");
			Hashtable h = new Hashtable();	
			myLogger.info("DALAM ## ");
			if (rs.getString("ID_HTA") == null) {
				h.put("ID_HTA", "");
			} else {
					h.put("ID_HTA", rs.getString("ID_HTA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHOBMST") == null) {
					h.put("ID_PERINTAHHTAOBMST", "");
			} else {
					h.put("ID_PERINTAHHTAOBMST", rs.getString("ID_PERINTAHOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			
			myLogger.info("#####################");
			
			listCurrentSelepasNofailHta.addElement(h);
			
			myLogger.info("DALAM LAGI :"+listCurrentSelepasNofailHta);
			
			}
			myLogger.info("----return listCurrentSelepasNofailHta :"+listCurrentSelepasNofailHta);
			return listCurrentSelepasNofailHta;
		} finally {
			if (db != null)
				db.close();
		}
	}
			
			
	
	public static Vector listLatestHa(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listLatestHa = null;
		listLatestHa = new Vector();
		listLatestHa.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT HA.ID_HA,HA.ID_PERMOHONANSIMATI,MST.ID_PERINTAHHAOBMST, PP.ID_PERMOHONAN,NVL(PP.NO_SUBJAKET,0) AS NO_SUBJAKET  "+
				" FROM TBLPPKPERMOHONANSIMATI PS,TBLPPKPERMOHONAN PP,TBLPPKKEPUTUSANPERMOHONAN KP, "+
				" TBLPPKPERBICARAAN BB,TBLPPKPERINTAH PR,TBLPPKPERINTAHHAOBMST MST,TBLPPKHA HA "+
				" WHERE PP.ID_PERMOHONAN = KP.ID_PERMOHONAN AND BB.ID_PERBICARAAN = PR.ID_PERBICARAAN "+
				" AND KP.ID_KEPUTUSANPERMOHONAN = BB.ID_KEPUTUSANPERMOHONAN AND PR.ID_PERINTAH  = MST.ID_PERINTAH "+
				" AND PS.ID_PERMOHONAN = PP.ID_PERMOHONAN AND HA.ID_PERINTAHOBMST = MST.ID_PERINTAHHAOBMST  "+
				" AND PS.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"') "+
				" AND NVL(PP.NO_SUBJAKET,0) =     "+
				" (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONANSIMATI SSM,TBLPPKPERMOHONAN PPP  "+
				" WHERE SSM.ID_PERMOHONAN = PPP.ID_PERMOHONAN AND SSM.ID_SIMATI IN "+
				" (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"')) ";
			if(flag_daftar.equals("BARU"))
			{
			sql += "-1";	
			}
			sql += " AND HA.ID_HA = '"+id_harta+"'";
				//--IF PERMOHONAN DAH DAFTAR,SUBKAJET KENA -1";
			myLogger.info("SQL listLatestHa :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			Hashtable h = new Hashtable();	
				
			if (rs.getString("ID_HA") == null) {
					h.put("ID_HA", "");
			} else {
					h.put("ID_HA", rs.getString("ID_HA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHHAOBMST") == null) {
					h.put("ID_PERINTAHHAOBMST", "");
			} else {
					h.put("ID_PERINTAHHAOBMST", rs.getString("ID_PERINTAHHAOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			listLatestHa.addElement(h);
			}
			return listLatestHa;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public static Vector listCurrentSelepasNofailHa(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listLatestSelepasNofailHa = null;
		listLatestSelepasNofailHa = new Vector();
		listLatestSelepasNofailHa.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT PHA.ID_HA,PSM.ID_PERMOHONANSIMATI,PHA.ID_PERINTAHOBMST,P.ID_PERMOHONAN,P.NO_SUBJAKET "+
					" FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLPPKPERMOHONANSIMATI PSM,TBLPPKHAPERMOHONAN PHA "+
					" WHERE P.ID_PERMOHONAN = '"+id_permohonan+"' "+
					" AND F.ID_FAIL = P.ID_FAIL AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = PHA.ID_PERMOHONANSIMATI "+
					" AND PHA.ID_HA = '"+id_harta+"'";
				//--IF PERMOHONAN DAH DAFTAR,SUBKAJET KENA -1";
			myLogger.info("SQL listLatestSelepasNofailHa :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			Hashtable h = new Hashtable();	
				
			if (rs.getString("ID_HA") == null) {
					h.put("ID_HA", "");
			} else {
					h.put("ID_HA", rs.getString("ID_HA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHOBMST") == null) {
					h.put("ID_PERINTAHOBMST", "");
			} else {
					h.put("ID_PERINTAHOBMST", rs.getString("ID_PERINTAHOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			listLatestSelepasNofailHa.addElement(h);
			}
			return listLatestSelepasNofailHa;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public static Vector listCurrentHa(String id_permohonan,String flag_daftar,String id_harta) throws Exception {
		Vector listCurrentHa = null;
		listCurrentHa = new Vector();
		listCurrentHa.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT HA.ID_HA,PS.ID_PERMOHONANSIMATI,MST.ID_PERINTAHHAOBMST, PP.ID_PERMOHONAN,NVL(PP.NO_SUBJAKET,0) AS NO_SUBJAKET "+
				" FROM TBLPPKPERMOHONANSIMATI PS,TBLPPKPERMOHONAN PP,TBLPPKKEPUTUSANPERMOHONAN KP,"+
				" TBLPPKPERBICARAAN BB,TBLPPKPERINTAH PR,TBLPPKPERINTAHHAOBMST MST,TBLPPKHA HA"+
				" WHERE PP.ID_PERMOHONAN = KP.ID_PERMOHONAN AND BB.ID_PERBICARAAN = PR.ID_PERBICARAAN"+
				" AND KP.ID_KEPUTUSANPERMOHONAN = BB.ID_KEPUTUSANPERMOHONAN AND PR.ID_PERINTAH  = MST.ID_PERINTAH"+
				" AND PS.ID_PERMOHONAN = PP.ID_PERMOHONAN AND HA.ID_PERINTAHOBMST = MST.ID_PERINTAHHAOBMST "+
				" AND PS.ID_SIMATI IN (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+id_permohonan+"')"+
			    " AND NVL(PP.NO_SUBJAKET,0) =    "+
				" (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN PPP "+
				" WHERE PPP.ID_PERMOHONAN = '"+id_permohonan+"') ";
			if(flag_daftar.equals("BARU"))
			{
			sql += "-1";	
			}
			sql += " AND HA.ID_HA = '"+id_harta+"'";
				//--IF PERMOHONAN DAH DAFTAR,SUBKAJET KENA -1";
			myLogger.info("SQL listCurrentHa :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			Hashtable h = new Hashtable();	
				
			if (rs.getString("ID_HA") == null) {
				h.put("ID_HA", "");
			} else {
					h.put("ID_HA", rs.getString("ID_HA").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONANSIMATI") == null) {
				h.put("ID_PERMOHONANSIMATI", "");
			} else {
					h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI").toUpperCase());
			}
			
			if (rs.getString("ID_PERINTAHHAOBMST") == null) {
					h.put("ID_PERINTAHHAOBMST", "");
			} else {
					h.put("ID_PERINTAHHAOBMST", rs.getString("ID_PERINTAHHAOBMST").toUpperCase());
			}
			
			if (rs.getString("ID_PERMOHONAN") == null) {
				h.put("ID_PERMOHONAN", "");
			} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase());
			}
			
			if (rs.getString("NO_SUBJAKET") == null) {
				h.put("NO_SUBJAKET", "");
			} else {
					h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET").toUpperCase());
			}
			
			listCurrentHa.addElement(h);
			}
			return listCurrentHa;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_semakhta = null;
	public Vector list_semakhta(String id_simati,String id_permohonan,String flag_daftar) throws Exception {
		list_semakhta = new Vector();
		list_semakhta.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			/*
			sql = "" +
		    " SELECT  a.id_perintahhtaobmst,hta.jenis_hta,nn.nama_negeri as nama_negeri,dd.nama_daerah as nama_daerah,mm.nama_mukim as nama_mukim, "+
			" hta.no_pt,jhm.kod_jenis_hakmilik || hta.no_hakmilik as no_hakmilik, " +
			" trim(f.no_fail) as no_fail,s.id_simati,ps.id_permohonansimati,hta.flag_pa,hta.flag_pt,hta.flag_selesai,hta.id_hta "+
			" FROM tblppkperintahhtaobmst a, "+
			" tblppkperintah b, "+
			" tblppkperbicaraan c, "+
			" tblppkkeputusanpermohonan d, "+
			" tblppkpermohonan e," +
			" tblppkhta hta1,tblppkhtapermohonan hta, "+
			" tblpfdfail f,tblppkpermohonansimati ps," +
			" tblppksimati s, "+
			" tblrujnegeri nn,tblrujdaerah dd,tblrujmukim mm,tblrujjenishakmilik jhm "+
			" WHERE hta.id_permohonansimati = hta1.id_permohonansimati and hta.id_hta = hta1.id_hta " +
			" and a.id_perintah = b.id_perintah "+
			" AND b.id_perbicaraan = c.id_perbicaraan "+		
			" AND c.id_keputusanpermohonan = d.id_keputusanpermohonan "+
			" AND d.id_permohonan = e.id_permohonan "+
			" AND e.id_fail = f.id_fail and ps.id_permohonan = e.id_permohonan "+
			" and ps.id_simati = s.id_simati and hta.id_hta = a.id_hta "+
			" and nn.id_negeri(+) = hta.id_negeri " +
			" and hta.id_daerah = dd.id_daerah(+) and mm.id_mukim(+) = hta.id_mukim "+
			" and hta.id_jenishm = jhm.id_jenishakmilik(+) ";
			sql += " and s.id_simati = '"+id_simati+"'";
			sql +=" ORDER BY f.no_fail, a.id_perintahhtaobmst ";*/
			
			/*
			sql = "SELECT " +
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
			" HP.flag_pa,HP.flag_pt,HP.flag_selesai,HP.ID_PERMOHONANSIMATI,H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT, " +
			" H.NILAI_HTA_TARIKHMOHON, " +
			"H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI, H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, " +
			"H.ID_DAERAH, H.ID_LUAS, H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN, " +
			"H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,  H.NO_PERSERAHAN, " +
			"H.CATATAN, H.STATUS_PEMILIKAN, " +
			" H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,RUJ.KOD_JENIS_HAKMILIK,RUJ.KETERANGAN," +
			" NN.NAMA_NEGERI,DD.NAMA_DAERAH,MM.NAMA_MUKIM,FF.NO_FAIL,P.NO_SUBJAKET  "+
			"FROM TBLPPKHTA HP,TBLPPKHTAPERMOHONAN H, TBLPPKSIMATI S, " +
			"TBLPPKPERMOHONANSIMATI MS, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P,TBLPFDFAIL FF, " +
			"TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ,TBLRUJNEGERI NN,TBLRUJDAERAH DD,TBLRUJMUKIM MM   " +
			"WHERE H.ID_SIMATI = S.ID_SIMATI AND H.ID_HTA = HP.ID_HTA AND P1.ID_FAIL = FF.ID_FAIL  " +
			" AND H.ID_NEGERI = NN.ID_NEGERI AND H.ID_DAERAH = DD.ID_DAERAH AND H.ID_MUKIM = MM.ID_MUKIM " +
			" AND H.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI   " +
			"AND H.ID_SIMATI = MS.ID_SIMATI  " +
			"AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+) "+
			"AND MS.ID_PERMOHONANSIMATI <> HP.ID_PERMOHONANSIMATI " +
			"AND MS.ID_SIMATI = '"+id_simati+"'   " +
			//"AND H.JENIS_HTA = 'Y' " +
			"AND HP.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   " +
			"AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  " +
			"AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  " +			
			"AND S.ID_SIMATI = MS.ID_SIMATI ";
			*/
			
			
			sql = " SELECT   H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI, MS1.ID_PERMOHONANSIMATI, "+
			" H.ID_HTA, H.NO_HAKMILIK, H.ID_SIMATI, H.NO_PT,"+
			" H.NILAI_HTA_TARIKHMOHON, H.NILAI_HTA_TARIKHMATI, H.ID_KATEGORI,"+
			" H.ID_JENISHM, H.ID_JENISPB, H.ID_NEGERI, H.ID_DAERAH, H.ID_LUAS,"+
			" H.ID_MUKIM, H.LUAS_HMP, H.LUAS, H.NO_CAGARAN, H.NO_PAJAKAN,"+
			" H.JENIS_TNH, H.BA_SIMATI, H.BB_SIMATI, H.JENIS_HTA, H.TANGGUNGAN,"+
			" H.NO_PERSERAHAN, H.CATATAN, H.STATUS_PEMILIKAN, H.FLAG_PA, H.FLAG_PT,"+
			" H.FLAG_SELESAI, RUJ.KOD_JENIS_HAKMILIK, RUJ.KETERANGAN,"+
			" NN.NAMA_NEGERI, DD.NAMA_DAERAH, MM.NAMA_MUKIM, FF.NO_FAIL,"+
			" P.NO_SUBJAKET"+
			" FROM " +
			" TBLPPKHTA H,TBLPPKSIMATI S, TBLPPKPERMOHONANSIMATI MS,TBLPPKPERMOHONAN P, TBLPFDFAIL FF,"+
			" TBLPPKPERMOHONANSIMATI MS1, TBLRUJJENISHAKMILIK RUJ, TBLRUJNEGERI NN, TBLRUJDAERAH DD, TBLRUJMUKIM MM"+
			" WHERE H.ID_SIMATI = S.ID_SIMATI"+
			" AND P.ID_FAIL = FF.ID_FAIL"+
			" AND H.ID_NEGERI = NN.ID_NEGERI"+
			" AND H.ID_DAERAH = DD.ID_DAERAH"+
			" AND H.ID_MUKIM = MM.ID_MUKIM"+
			" AND H.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI"+
			" AND H.ID_SIMATI = MS.ID_SIMATI"+
			" AND H.ID_JENISHM = RUJ.ID_JENISHAKMILIK(+)"+
			" AND MS.ID_SIMATI = '"+id_simati+"'"+
			" AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI"+
			" AND MS1.ID_PERMOHONAN = P.ID_PERMOHONAN(+)"+
			" AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)"+
			" AND S.ID_SIMATI = MS.ID_SIMATI AND FLAG_SELESAI != 'Y' ";
    
			sql +=" AND NVL(P.NO_SUBJAKET,0) <=   ";			
			sql += " (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN PPP "+
			" WHERE PPP.ID_PERMOHONAN = '"+id_permohonan+"') ";
			if(flag_daftar.equals("BARU"))
			{
			sql += "-1 ";	
			}
			sql += " ORDER BY P.NO_SUBJAKET,H.ID_HTA,H.JENIS_HTA DESC ";
			myLogger.info("SQL HTA perintahhtaobmst :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();	
				/*if (rs.getString("id_perintahhtaobmst") == null) {
					h.put("id_perintahhtaobmst", "");
				} else {
					h.put("id_perintahhtaobmst", "");
				}*/
				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("nama_negeri", "");
				} else {
					h.put("nama_negeri", rs.getString("NAMA_NEGERI").toUpperCase());
				}
				if (rs.getString("NAMA_DAERAH") == null) {
					h.put("nama_daerah", "");
				} else {
					h.put("nama_daerah", rs.getString("NAMA_DAERAH").toUpperCase());
				}
				if (rs.getString("NAMA_MUKIM") == null) {
					h.put("nama_mukim", "");
				} else {
					h.put("nama_mukim", rs.getString("NAMA_MUKIM").toUpperCase());
				}
				if (rs.getString("NO_PT") == null) {
					h.put("no_pt", "");
				} else {
					h.put("no_pt", rs.getString("NO_PT"));
				}
				if (rs.getString("NO_HAKMILIK") == null) {
					h.put("no_hakmilik", "");
				} else {
					h.put("no_hakmilik", rs.getString("NO_HAKMILIK"));
				}
				if (rs.getString("NO_FAIL") == null) {
					h.put("no_fail", "");
				} else {
					h.put("no_fail", rs.getString("NO_FAIL"));
				}
				if (rs.getString("ID_SIMATI") == null) {
					h.put("id_simati", "");
				} else {
					h.put("id_simati", rs.getString("ID_SIMATI"));
				}
				if (rs.getString("ID_PERMOHONANSIMATI") == null) {
					h.put("id_permohonansimati", "");
				} else {
					h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				}
				if (rs.getString("flag_pa") == null) {
					h.put("flag_pa", "");
				} else {
					h.put("flag_pa", rs.getString("flag_pa"));
				}
				if (rs.getString("flag_pt") == null) {
					h.put("flag_pt", "");
				} else {
					h.put("flag_pt", rs.getString("flag_pt"));
				}
				if (rs.getString("flag_selesai") == null) {
					h.put("flag_selesai", "");
				} else {
					h.put("flag_selesai", rs.getString("flag_selesai"));
				}
				if (rs.getString("ID_HTA") == null) {
					h.put("id_hta", "");
				} else {
					h.put("id_hta", rs.getString("ID_HTA"));
				}
				
				if (rs.getString("jenis_hta") == null) {
					h.put("jenis_hta", "");
				} else {
					h.put("jenis_hta", rs.getString("JENIS_HTA"));
				}
								
				list_semakhta.addElement(h);
			}
			return list_semakhta;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Vector list_semakha = null;
	public Vector list_semakha(String id_simati,String id_permohonan,String flag_daftar) throws Exception {
		list_semakha = new Vector();
		list_semakha.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			/*
			sql = "SELECT " +
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
			
			
					" H1.flag_pa,H1.flag_pt,H1.flag_selesai,H1.ID_PERMOHONANSIMATI,H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, H.NILAI_HA_TARIKHMOHON, "
					+ "H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, "
					+ "H.ALAMAT_HA3, H.POSKOD,H.BUTIRAN,H.NAMA_SAHAM,H.JENAMA,  "
					+ " H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI,FF.NO_FAIL,S.ID_SIMATI,P.NO_SUBJAKET  "
					+ "FROM TBLPFDFAIL FF,TBLPPKHA H1,TBLPPKHAPERMOHONAN H, TBLPPKRUJJENISHA J, TBLPPKPERMOHONANSIMATI MS,"
					+ "TBLPPKSIMATI S, TBLPPKPERMOHONAN P1,TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 "
					+ "WHERE FF.ID_FAIL = P1.ID_FAIL AND H1.ID_HA = H.ID_HA AND H.ID_PERMOHONANSIMATI = MS.ID_PERMOHONANSIMATI " +
							" AND H.ID_SIMATI = S.ID_SIMATI "
					+ "AND H.ID_JENISHA = J.ID_JENISHA  "
					+ "AND H.ID_SIMATI = MS.ID_SIMATI  "
					+ "AND H1.ID_PERMOHONANSIMATI <> MS.ID_PERMOHONANSIMATI "
					+ "AND MS.ID_SIMATI = '"
					+ id_simati
					+ "' "
					+ "AND H1.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI   "
					+ "AND MS1.ID_PERMOHONAN = P1.ID_PERMOHONAN(+)  "
					+ "AND P.ID_PERMOHONAN = MS.ID_PERMOHONAN(+)  "
					+ "AND S.ID_SIMATI = MS.ID_SIMATI ";
					//+ "AND P1.NO_SUBJAKET < P.NO_SUBJAKET  ";
			*/
			
			sql = "SELECT   "+
				" H.FLAG_PA, H.FLAG_PT, H.FLAG_SELESAI, MS1.ID_PERMOHONANSIMATI, "+
				" H.ID_HA, H.BIL, H.ID_SIMATI, H.ID_JENISHA, H.NO_DAFTAR, "+
				" H.NILAI_HA_TARIKHMOHON, H.NILAI_HA_TARIKHMATI, H.NO_SIJIL, J.KOD, "+
				" J.KETERANGAN, H.ALAMAT_HA1, H.ALAMAT_HA2, H.ALAMAT_HA3, H.POSKOD, "+
				" H.BUTIRAN, H.NAMA_SAHAM, H.JENAMA, H.FLAG_PA, H.FLAG_PT,  "+
				" H.FLAG_SELESAI, FF.NO_FAIL, S.ID_SIMATI, P.NO_SUBJAKET "+
				" FROM TBLPFDFAIL FF, TBLPPKHA H, TBLPPKRUJJENISHA J, "+
				" TBLPPKSIMATI S, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI MS1 "+
				" WHERE FF.ID_FAIL = P.ID_FAIL  AND H.ID_SIMATI = S.ID_SIMATI AND H.ID_JENISHA = J.ID_JENISHA "+
				" AND MS1.ID_SIMATI = '"+id_simati+"' AND H.ID_PERMOHONANSIMATI = MS1.ID_PERMOHONANSIMATI "+
				" AND MS1.ID_PERMOHONAN = P.ID_PERMOHONAN AND S.ID_SIMATI = MS1.ID_SIMATI AND FLAG_SELESAI != 'Y'";
			
					sql += " AND NVL(P.NO_SUBJAKET,0) <=   ";			
					sql += " (SELECT MAX(NVL(PPP.NO_SUBJAKET,0)) AS NO_SUBJAKET FROM TBLPPKPERMOHONAN PPP "+
					" WHERE PPP.ID_PERMOHONAN = '"+id_permohonan+"') ";
					if(flag_daftar.equals("BARU"))
					{
					sql += "-1";	
					}
					sql += " ORDER BY P.NO_SUBJAKET,H.ID_HA DESC";
		    
		    myLogger.info("SQL HA perintahhaobmst :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				/*
				if (rs.getString("id_perintahhaobmst") == null) {
					h.put("id_perintahhaobmst", "");
				} else {
					h.put("id_perintahhaobmst", rs.getString("id_perintahhaobmst"));				
				}
				*/
				if (rs.getString("NO_DAFTAR") == null) {
					h.put("no_daftar", "");
				} else {
					h.put("no_daftar", rs.getString("NO_DAFTAR"));				
				}
				
				if (rs.getString("JENAMA") == null) {
					h.put("jenama", "");
				} else {
					h.put("jenama", rs.getString("JENAMA"));				
				}
				
				if (rs.getString("KETERANGAN") == null) {
					h.put("jenis_ha", "");
				} else {
					h.put("jenis_ha", rs.getString("KETERANGAN"));				
				}
				
				if (rs.getString("NO_FAIL") == null) {
					h.put("no_fail", "");
				} else {
					h.put("no_fail", rs.getString("NO_FAIL"));
				}
				if (rs.getString("ID_SIMATI") == null) {
					h.put("id_simati", "");
				} else {
					h.put("id_simati", rs.getString("ID_SIMATI"));
				}
				if (rs.getString("ID_PERMOHONANSIMATI") == null) {
					h.put("id_permohonansimati", "");
				} else {
					h.put("id_permohonansimati", rs.getString("ID_PERMOHONANSIMATI"));
				}
				if (rs.getString("flag_pa") == null) {
					h.put("flag_pa", "");
				} else {
					h.put("flag_pa", rs.getString("flag_pa"));
				}
				if (rs.getString("flag_pt") == null) {
					h.put("flag_pt", "");
				} else {
					h.put("flag_pt", rs.getString("flag_pt"));
				}
				if (rs.getString("flag_selesai") == null) {
					h.put("flag_selesai", "");
				} else {
					h.put("flag_selesai", rs.getString("flag_selesai"));
				}
				if (rs.getString("ID_HA") == null) {
					h.put("id_ha", "");
				} else {
					h.put("id_ha", rs.getString("ID_HA"));
				}
								
				list_semakha.addElement(h);
			}
			return list_semakha;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public static void updateHta(HttpSession session,String id_hta,String flag_pa,String flag_pt,String flag_selesai,String USER_ID,String id_perintahhtaobmst,String id_permohonan,String flag_daftar) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";
		Vector listLatestHta = new Vector();
		Vector listCurrentHta = new Vector();
		Vector listCurrentSelepasNofailHta = new Vector();
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
					String id_latestobmst = "";
					listLatestHta = listLatestHta(id_permohonan,flag_daftar,id_hta);
					if(listLatestHta.size()>0)
					{
					Hashtable h = (Hashtable) listLatestHta.get(0);
					id_latestobmst = h.get("ID_PERINTAHHTAOBMST").toString();		
					}
					r.update("id_hta",id_hta);
					r.add("flag_pa",flag_pa);
					r.add("flag_pt",flag_pt);
					r.add("flag_selesai",flag_selesai);
					if(!id_latestobmst.equals(""))
					{
					r.add("id_perintahobmst",id_latestobmst);
					}
					r.add("ID_KEMASKINI",USER_ID);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKHTA");	
					myLogger.info("UPDATE HTA :"+sql1);
					
					stmt.executeUpdate(sql1);
					
					String id_currentobmst = "";
					String id_permohonansimati = "";
					listCurrentHta = listCurrentHta(id_permohonan,flag_daftar,id_hta);				
					
					if(listCurrentHta.size()>0)
					{
					Hashtable h = (Hashtable) listCurrentHta.get(0);
					id_currentobmst = h.get("ID_PERINTAHHTAOBMST").toString();	
					id_permohonansimati = h.get("ID_PERMOHONANSIMATI").toString();	
					}
					
					
					myLogger.info("---FLAG DAFTAR :"+flag_daftar);
					
					String id_permohonansimatiSelepasNofail = "";					
					if(flag_daftar.equals("BARU"))
					{
					myLogger.info("---MASUK");	 
					listCurrentSelepasNofailHta = listCurrentSelepasNofailHta(id_permohonan,flag_daftar,id_hta);		
					myLogger.info("---listCurrentSelepasNofailHta.size() :"+listCurrentSelepasNofailHta.size());
					if(listCurrentSelepasNofailHta.size()>0)
					{
					Hashtable h = (Hashtable) listCurrentSelepasNofailHta.get(0);					
					id_permohonansimatiSelepasNofail = h.get("ID_PERMOHONANSIMATI").toString();	
					myLogger.info("---id_permohonansimatiSelepasNofail :"+id_permohonansimatiSelepasNofail);
					}
					}
					
					if(!id_permohonansimati.equals(""))
					{
							r.clear();
							r.update("id_hta",id_hta);
							r.update("id_permohonansimati",id_permohonansimati);
							r.add("flag_pa",flag_pa);
							r.add("flag_pt",flag_pt);
							r.add("flag_selesai",flag_selesai);
							if(!id_latestobmst.equals(""))
							{
							r.add("id_perintahobmst",id_currentobmst);
							}
							r.add("ID_KEMASKINI",USER_ID);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");	
							myLogger.info("UPDATE HTA PERMOHONAN :"+sql1);
							stmt.executeUpdate(sql1);
					}	
					
					if(!id_permohonansimatiSelepasNofail.equals("") && flag_daftar.equals("BARU"))
					{
							r.clear();
							r.update("id_hta",id_hta);
							r.update("id_permohonansimati",id_permohonansimatiSelepasNofail);
							r.add("flag_pa",flag_pa);
							r.add("flag_pt",flag_pt);
							r.add("flag_selesai",flag_selesai);
							if(!id_latestobmst.equals(""))
							{
							r.add("id_perintahobmst",id_currentobmst);
							}
							r.add("ID_KEMASKINI",USER_ID);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql1 = r.getSQLUpdate("TBLPPKHTAPERMOHONAN");	
							myLogger.info("UPDATE HTA PERMOHONAN SelepasNofail :"+sql1);
							stmt.executeUpdate(sql1);
					}	
					
					
					
					
					
					
					
			
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	public static void updateHa(HttpSession session,String id_ha,String flag_pa,String flag_pt,String flag_selesai,String USER_ID,String id_perintahhaobmst,String id_permohonan,String flag_daftar) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1="";
		
		Vector listLatestHa = new Vector();
		Vector listCurrentHa = new Vector();
		Vector listCurrentSelepasNofailHa = new Vector();
				
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
					
			String id_latestobmst = "";
			listLatestHa = listLatestHa(id_permohonan,flag_daftar,id_ha);
			if(listLatestHa.size()>0)
			{
			Hashtable h = (Hashtable) listLatestHa.get(0);
			id_latestobmst = h.get("ID_PERINTAHHAOBMST").toString();		
			}
			r.update("id_ha",id_ha);
			r.add("flag_pa",flag_pa);
			r.add("flag_pt",flag_pt);
			r.add("flag_selesai",flag_selesai);
			if(!id_latestobmst.equals(""))
			{
			r.add("id_perintahobmst",id_latestobmst);
			}
			r.add("ID_KEMASKINI",USER_ID);
			r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("TBLPPKHA");	
			myLogger.info("UPDATE HA :"+sql1);
			stmt.executeUpdate(sql1);
			
			
			
			String id_currentobmst = "";
			String id_permohonansimati = "";
			listCurrentHa = listCurrentHa(id_permohonan,flag_daftar,id_ha);
			if(listCurrentHa.size()>0)
			{
			Hashtable h = (Hashtable) listCurrentHa.get(0);
			id_currentobmst = h.get("ID_PERINTAHHAOBMST").toString();	
			id_permohonansimati = h.get("ID_PERMOHONANSIMATI").toString();	
			}
			
			
			myLogger.info("---FLAG DAFTAR :"+flag_daftar);
			
			String id_permohonansimatiSelepasNofail = "";					
			if(flag_daftar.equals("BARU"))
			{
			myLogger.info("---MASUK");	 
			listCurrentSelepasNofailHa = listCurrentSelepasNofailHa(id_permohonan,flag_daftar,id_ha);		
			myLogger.info("---listCurrentSelepasNofailHa.size() :"+listCurrentSelepasNofailHa.size());
			if(listCurrentSelepasNofailHa.size()>0)
			{
			Hashtable h = (Hashtable) listCurrentSelepasNofailHa.get(0);					
			id_permohonansimatiSelepasNofail = h.get("ID_PERMOHONANSIMATI").toString();	
			myLogger.info("---id_permohonansimatiSelepasNofail :"+id_permohonansimatiSelepasNofail);
			}
			}
			
			
			if(!id_permohonansimati.equals(""))
			{
					r.clear();
					r.update("id_ha",id_ha);
					r.update("id_permohonansimati",id_permohonansimati);
					r.add("flag_pa",flag_pa);
					r.add("flag_pt",flag_pt);
					r.add("flag_selesai",flag_selesai);
					if(!id_latestobmst.equals(""))
					{
					r.add("id_perintahobmst",id_currentobmst);
					}
					r.add("ID_KEMASKINI",USER_ID);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKHAPERMOHONAN");	
					myLogger.info("UPDATE HA PERMOHONAN :"+sql1);
					stmt.executeUpdate(sql1);
			}
			
			if(!id_permohonansimatiSelepasNofail.equals("") && flag_daftar.equals("BARU"))
			{
					r.clear();
					r.update("id_ha",id_ha);
					r.update("id_permohonansimati",id_permohonansimatiSelepasNofail);
					r.add("flag_pa",flag_pa);
					r.add("flag_pt",flag_pt);
					r.add("flag_selesai",flag_selesai);
					if(!id_latestobmst.equals(""))
					{
					r.add("id_perintahobmst",id_currentobmst);
					}
					r.add("ID_KEMASKINI",USER_ID);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKHAPERMOHONAN");	
					myLogger.info("UPDATE HA PERMOHONAN SelepasNofail :"+sql1);
					stmt.executeUpdate(sql1);
			}	
			
					
			
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
}
