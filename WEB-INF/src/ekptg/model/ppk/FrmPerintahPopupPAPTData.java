/**
 * 
 */
package ekptg.model.ppk;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.misc.Fraction;
import ekptg.helpers.DB;
import ekptg.helpers.Pecahan;
import ekptg.helpers.Utils;

/**
 * 
 *
 */
public class FrmPerintahPopupPAPTData {
	
	private Vector senaraiPT = new Vector();
	private Vector senaraiPAHTA = new Vector();
	private Vector senaraiPAHA = new Vector();
	
	static Logger myLogger = Logger.getLogger(FrmPerintahPopupPAPTData.class);
	
	public void setDataSenaraiPTHTA(String idOB, String idPerintahHTAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
		*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
						" AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
						" AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
						" AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' " +
						" AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG = 1 " +
										" AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
								" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG NOT IN (0,1,14) " +
										" ORDER BY UMUR DESC NULLS LAST";
			
			myLogger.info("###setDataSenaraiPTHTA( :"+sql.toUpperCase());

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
				senaraiPT.addElement(h);
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
		    	senaraiPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiPTHA(String idOB, String idPerintahHAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
		*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1 " +
								" AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						"WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
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
				senaraiPT.addElement(h);
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
		    	senaraiPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiPTTiadaWarisHTA(String idPerintahHTAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
		*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +						
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1 " +
								" AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1" +
						"  WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
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
				senaraiPT.addElement(h);
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
		    	senaraiPT.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setDataSenaraiPTTiadaWarisHA(String idPerintahHAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPT.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT ID_OB, NAMA_OB, UMUR,"
				+ " CASE" 
				+ " WHEN ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
		*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1 AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER (OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL) THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
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
				senaraiPT.addElement(h);
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
		    	senaraiPT.addElement(h);
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
			
			//HTA PERINTAH TERUS / FARAID
			sqlCheckPerintahTerusHTA = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B"
					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) " +
							" AND A.STATUS_TADBIR = 'Y' " +
							" AND B.ID_PERINTAHHTAOBMST = A.ID_PERINTAHHTAOBMST";
			myLogger.info("sqlCheckPerintahTerusHTA :"+sqlCheckPerintahTerusHTA.toUpperCase());
			
			ResultSet rsHTA = stmt.executeQuery(sqlCheckPerintahTerusHTA);
			
			while (rsHTA.next()){
				updatePTHTAPTPF(rsHTA.getString("ID_PERINTAHHTAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			}
			
			//HA PERINTAH TERUS / FARAID
			sqlCheckPerintahTerusHA = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B"
					+ " WHERE B.ID_PERINTAH = '" + idPerintah + "' AND B.ID_JENISPERINTAH IN (1,7) AND A.STATUS_TADBIR = 'Y' AND B.ID_PERINTAHHAOBMST = A.ID_PERINTAHHAOBMST";
			
			ResultSet rsHA = stmt.executeQuery(sqlCheckPerintahTerusHA);
			
			while (rsHA.next()){
				updatePTHAPTPF(rsHA.getString("ID_PERINTAHHAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			}
			
			//HTA PERINTAH KUASA TADBIR
			sqlCheckPerintahKuasaTadbirHTA = "SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST WHERE ID_JENISPERINTAH = 2 AND ID_PERINTAH = '" + idPerintah + "'";
			myLogger.info("###sqlCheckPerintahKuasaTadbirHTA :"+sqlCheckPerintahKuasaTadbirHTA);
			ResultSet rsKTHTA = stmt.executeQuery(sqlCheckPerintahKuasaTadbirHTA);
			
			while (rsKTHTA.next()){
				myLogger.info("###idPA1.trim() :"+idPA1.trim());
				
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
			myLogger.info("updatePTHTAPTPF(:"+sql.toUpperCase());
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
			myLogger.info("###updatePTHTAPKT( :"+sqlInsert);
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
	
	public void setDataSenaraiPAHTA(String idOB, String idPerintahHTAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPAHTA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 *
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
					+ " CASE" 
					+ " WHEN ID_OB IN ("
					+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
					+ " END AS FLAG"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
					+ " UNION"
					+ " SELECT ID_OB, NAMA_OB, UMUR,"
					+ " CASE" 
					+ " WHEN ID_OB IN ("
					+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
					+ " END AS FLAG"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG = 1 AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 WHERE " +
						" OB1.ID_SIMATI = '" + idSimati + "'" +
				" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
						"  AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
								" AND OB.ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";

			ResultSet rs = stmt.executeQuery(sql);
System.out.println("sql===="+sql);
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
				senaraiPAHTA.addElement(h);
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
		    	senaraiPAHTA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePAHTA(String idOB, String idPerintahHTAOBMST, String idPA1, String idPA2, String idPA3, String idPA4, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHTAOBDTL				
			r.update("ID_OB", idOB);
			r.update("ID_PERINTAHHTAOBMST", idPerintahHTAOBMST);
			
			r.add("ID_PA1", idPA1);
			r.add("ID_PA2", idPA2);
			r.add("ID_PA3", idPA3);
			r.add("ID_PA4", idPA4);
	
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPPKPERINTAHHTAOBDTL");
			myLogger.info("TEST TBLPPKPERINTAHHTAOBDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//aishah add
	//public void insertTBLPPKPENJAGA(String idOB, String idPerintahHTAOBMST, String idPA, HttpSession session) throws Exception {
		public static void insertTBLPPKPENJAGA(String idOB, String idPerintahHTAOBMST, String idPA, HttpSession session) throws Exception {
			Db db = null;
			String sql = "";
			String userId = session.getAttribute("_ekptg_user_id").toString();
			try {
				
				db = new Db();
				
				long id_penjaga = DB.getNextID("TBLPPKPENJAGA_SEQ");
				
				Statement stmt = db.getStatement();
				
				//TBLPPKPERINTAHHTAOBDTL
				sql = " INSERT INTO TBLPPKPENJAGA (ID_PENJAGA, ID_OBMINOR, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NAMA_PENJAGA, " +
					  " JANTINA, JENIS_AGAMA,JENIS_WARGA, UMUR, ALAMAT1, ALAMAT2, ALAMAT3, POSKOD, ID_BANDAR, ID_NEGERI, ID_OB, " +
					  " ID_MASUK, TARIKH_MASUK )" +
					  " SELECT " + id_penjaga + ", " + idOB + ", ob.no_kp_baru, ob.no_kp_lama,ob.jenis_kp,ob.no_kp_lain, ob.nama_ob, " +
					  " ob.jantina, ob.jenis_agama,ob.jenis_warga,ob.umur,ob.alamat_1, ob.alamat_2, ob.alamat_3,ob.poskod, " +
					  " ob.id_bandar, ob.id_negeri,"+idPA+", " +
					  " " + userId + ", SYSDATE  FROM Tblppkob ob where ob.id_ob = "+idPA ;
					

				System.out.println("sebelum   insert TBLPPKPENJAGA "+sql);
				stmt.executeUpdate(sql);
				System.out.println("lepasssss insert TBLPPKPENJAGA ");
				
				
				sql = " SELECT ID_PENJAGA FROM TBLPPKPENJAGA WHERE ID_OBMINOR = '"
						+ idOB
						+ "' "
						+ " ORDER BY TO_DATE(TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY HH:MIpm'),'DD/MM/YYYY HH:MIpm') ASC";
				myLogger.info("SELECT * PENJAGA :" + sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				Integer order = 0;
				while (rs.next()) {
					order++;
					updateDataPenjagaBIL(order + "", rs.getString("ID_PENJAGA"));
				}
				
			
			} finally {
				if (db != null)
					db.close();
			}
		}
	//aishah
		
		public static void deletePENJAGA(String id_ob) throws Exception {
			Db db = null;
			String sql = "";

			try {
				
				db = new Db();
				Statement stmt = db.getStatement();
				
				//TBLPPKPERINTAHHTAOBDTL
				sql = "DELETE FROM TBLPPKPENJAGA WHERE ID_OB = '" + id_ob + "'";
				stmt.executeUpdate(sql);
			
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		//aishah
		
		public static void updateDataPenjagaBIL(String order, String id_penjaga)
				throws Exception {

			Db db = null;
			String sql = "";

			try {

				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();

				myLogger.info("order :" + order);
				r.clear();
				r.update("ID_PENJAGA", id_penjaga);
				r.add("BIL_PENJAGA", order);
				sql = r.getSQLUpdate("TBLPPKPENJAGA");
				myLogger.info("UPDATE PENJAGA :" + sql);
				stmt.executeUpdate(sql);

			}// close try

			finally {
				if (db != null)
					db.close();
			}// close finally

		}
	
	public void setDataSenaraiPAHA(String idOB, String idPerintahHAOBMST, String idPermohonanSimati, String idSimati) throws Exception {
		Db db = null;
		senaraiPAHA.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			/*
			 * COMMENT BY PEJE - UNTUK CASE DI MANA PENTADBIR MENINGGAL SELEPAS SESI BICARA DAN KEPUTUSAN DIKELUARKAN.
			 * 
			sql = "SELECT ID_OB, NAMA_OB, UMUR,"
					+ " CASE" 
					+ " WHEN ID_OB IN ("
					+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
					+ " END AS FLAG"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG = 1 AND STATUS_HIDUP = 0 AND (STATUS_OB = 1 OR STATUS_OB IS NULL)"
					+ " UNION"
					+ " SELECT ID_OB, NAMA_OB, UMUR,"
					+ " CASE" 
					+ " WHEN ID_OB IN ("
					+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
					+ " UNION"
					+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
					+ " END AS FLAG"
					+ " FROM TBLPPKOB WHERE ID_SIMATI = '" + idSimati + "' AND ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") AND ID_TARAFKPTG NOT IN (0,1,14) ORDER BY UMUR DESC NULLS LAST";
			*/
			sql = "SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP, "
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
								" AND OB1.ID_PERMOHONANSIMATI IN (" + getSenaraiIdPermohonanSimati(idSimati, idPermohonanSimati) + ") " +
										" AND OB.ID_TARAFKPTG = 1 AND (OB.STATUS_OB = 1 OR OB.STATUS_OB IS NULL)"
				+ " UNION"
				+ " SELECT OB1.ID_OB, UPPER(OB.NAMA_OB) AS NAMA_OB, OB.UMUR, OB.STATUS_HIDUP,"
				+ " CASE" 
				+ " WHEN OB1.ID_OB IN ("
				+ " SELECT ID_PA1 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA2 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA3 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'"
				+ " UNION"
				+ " SELECT ID_PA4 AS ID_OB FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "') THEN 'Y'"
				+ " END AS FLAG"
				+ " FROM TBLPPKOBPERMOHONAN OB,TBLPPKOB OB1 " +
						" WHERE OB1.ID_SIMATI = '" + idSimati + "' " +
						" AND OB1.ID_OB = OB.ID_OB AND OB.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"' " +
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
				senaraiPAHA.addElement(h);
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
		    	senaraiPAHA.addElement(h);
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePAHA(String idOB, String idPerintahHAOBMST, String idPA1, String idPA2, String idPA3, String idPA4, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			SQLRenderer r = new SQLRenderer();
			//TBLPPKPERINTAHHAOBDTL				
			r.update("ID_OB", idOB);
			r.update("ID_PERINTAHHAOBMST", idPerintahHAOBMST);
			
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
	
	public boolean checkRowWarisHTA(String idOB, String idPerintahHTAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (idOB.equals("")){
				
				sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB IS NULL";
				
			} else {
				
				sql = "SELECT ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "' AND ID_OB = '" + idOB + "'";
			}
			
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
	
	public boolean checkRowWarisHA(String idOB, String idPerintahHAOBMST) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			if (idOB.equals("")){
				
				sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB IS NULL";
				
			} else {
				
				sql = "SELECT ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND ID_OB = '" + idOB + "'";
			}

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
	
	public void updatePentadbirHTA(String idOB, String idPerintahHTAOBMST, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//HTA PERINTAH TERUS / FARAID
			sql = "SELECT A.ID_PERINTAHHTAOBDTL FROM TBLPPKPERINTAHHTAOBDTL A, TBLPPKPERINTAHHTAOBMST B"
					+ " WHERE A.ID_PERINTAHHTAOBMST = B.ID_PERINTAHHTAOBMST"
					+ " AND B.ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'";
					if ("".equals(idOB)){
						sql = sql + " AND A.ID_OB IS NULL";
					} else {
						sql = sql + " AND A.ID_OB = '" + idOB + "'";
					}
					

			myLogger.info("updatePentadbirHTA :"+sql.toUpperCase());
			
			ResultSet rsHTA = stmt.executeQuery(sql);
			
			if (rsHTA.next()){
				updatePTHTAPTPF(rsHTA.getString("ID_PERINTAHHTAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			}
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updatePentadbirHA(String idOB, String idPerintahHAOBMST, String idPA1, String idPA2, String idPA3, String idPA4, String idPerintah, HttpSession session) throws Exception{
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			//HA PERINTAH TERUS / FARAID
			sql = "SELECT A.ID_PERINTAHHAOBDTL FROM TBLPPKPERINTAHHAOBDTL A, TBLPPKPERINTAHHAOBMST B"
					+ " WHERE A.ID_PERINTAHHAOBMST = B.ID_PERINTAHHAOBMST"
					+ " AND B.ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "' AND A.ID_OB = '" + idOB + "'";
			myLogger.info("updatePentadbirHA :"+sql.toUpperCase());
			
			ResultSet rsHA = stmt.executeQuery(sql);
			
			if (rsHA.next()){
				updatePTHAPTPF(rsHA.getString("ID_PERINTAHHAOBDTL"), idPA1, idPA2, idPA3, idPA4, idPerintah, session);
			}
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiPT() {
		return senaraiPT;
	}

	public void setSenaraiPT(Vector senaraiPT) {
		this.senaraiPT = senaraiPT;
	}

	public Vector getSenaraiPAHTA() {
		return senaraiPAHTA;
	}

	public void setSenaraiPAHTA(Vector senaraiPAHTA) {
		this.senaraiPAHTA = senaraiPAHTA;
	}

	public Vector getSenaraiPAHA() {
		return senaraiPAHA;
	}

	public void setSenaraiPAHA(Vector senaraiPAHA) {
		this.senaraiPAHA = senaraiPAHA;
	}

	public Vector getSenaraiHTA(String idPerintah, String idPerintahHTAOBMST) throws Exception {
		
		Db db = null;
		Vector senaraiHTA = new Vector();
		senaraiHTA.clear();
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT HTAOBMST.ID_PERINTAHHTAOBMST, RUJJENISHM.KOD_JENIS_HAKMILIK, HTA.NO_HAKMILIK, HTA.NO_PT,"
				+ " RUJNEGERI.NAMA_NEGERI, RUJDAERAH.NAMA_DAERAH, RUJMUKIM.NAMA_MUKIM"
				+ " FROM TBLPPKPERINTAHHTAOBMST HTAOBMST, TBLPPKHTA HTA, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJNEGERI RUJNEGERI, TBLRUJDAERAH RUJDAERAH, TBLRUJMUKIM RUJMUKIM"
				+ " WHERE HTAOBMST.ID_HTA = HTA.ID_HTA AND HTA.ID_JENISHM = RUJJENISHM.ID_JENISHAKMILIK(+) AND HTA.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
				+ " AND HTA.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND HTA.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HTAOBMST.ID_JENISPERINTAH = 1 AND HTAOBMST.FLAG_HARTA = 'B'"
				+ " AND HTAOBMST.ID_PERINTAH = '" + idPerintah + "'";
			
			if (!"".equals(idPerintahHTAOBMST)){
				sql = sql + " AND HTAOBMST.ID_PERINTAHHTAOBMST != '" + idPerintahHTAOBMST + "'";
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("sql getSenaraiHTA=="+sql);
			System.out.println("idPerintahHTAOBMST=="+idPerintahHTAOBMST);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPerintahHTAOBMST", rs.getString("ID_PERINTAHHTAOBMST"));
				h.put("keteranganHakmilik", (rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? "" : (rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK").toUpperCase() + ", ")) 
						+ rs.getString("NO_PT") + ", " 
						+ rs.getString("NAMA_MUKIM").toUpperCase() + ", " + rs.getString("NAMA_DAERAH").toUpperCase() 
						+ ", " + rs.getString("NAMA_NEGERI").toUpperCase());
				senaraiHTA.addElement(h);
				bil++;
			}
			
		}  finally {
			if (db != null)
				db.close();
		}
		
		return senaraiHTA;
	}
	
	public Vector getSenaraiHA(String idPerintah, String idPerintahHAOBMST) throws Exception {
		
		Db db = null;
		Vector senaraiHA = new Vector();
		senaraiHA.clear();
		String sql = "";
		String idJenisHA = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HAOBMST.ID_PERINTAHHAOBMST, RUJJENISHA.KETERANGAN AS JENIS_HA, HA.ID_JENISHA, HA.NAMA_SAHAM, HA.JENAMA, HA.BUTIRAN, HA.NILAI_HA_TARIKHMOHON"
				+ " FROM TBLPPKPERINTAHHAOBMST HAOBMST, TBLPPKHA HA, TBLPPKRUJJENISHA RUJJENISHA"
				+ " WHERE HAOBMST.ID_HA = HA.ID_HA AND HA.ID_JENISHA = RUJJENISHA.ID_JENISHA"
				+ " AND HAOBMST.ID_JENISPERINTAH = 1 AND HAOBMST.FLAG_HARTA = 'B'"
				+ " AND HAOBMST.ID_PERINTAH = '" + idPerintah + "'";
			
			if (!"".equals(idPerintahHAOBMST)){
				sql = sql + " AND HAOBMST.ID_PERINTAHHAOBMST != '" + idPerintahHAOBMST + "'";
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("sql senarai HA :::: "+sql);
			
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPerintahHAOBMST",rs.getString("ID_PERINTAHHAOBMST") == null ? "" : rs.getString("ID_PERINTAHHAOBMST"));
				h.put("jenisHA",rs.getString("JENIS_HA") == null ? "" : rs.getString("JENIS_HA"));
				
				if (rs.getString("ID_JENISHA") != null && rs.getString("ID_JENISHA").trim().length() != 0){
					idJenisHA = rs.getString("ID_JENISHA");
					
					if ("1".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NAMA_SAHAM") == null ? "" : " - " + rs.getString("NAMA_SAHAM").toUpperCase());
					} else if ("2".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("3".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("4".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("5".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("6".equals(idJenisHA)){
						h.put("keterangan", rs.getString("JENAMA") == null ? "" : " - " + rs.getString("JENAMA").toUpperCase());
					} else if ("7".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("8".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("9".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("10".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("11".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("12".equals(idJenisHA)){
						h.put("keterangan", rs.getString("BUTIRAN") == null ? "" : " - " + rs.getString("BUTIRAN").toUpperCase());
					} else if ("98".equals(idJenisHA)){
						h.put("keterangan", rs.getString("NILAI_HA_TARIKHMOHON") == null ? "" : " - RM" + Util.formatDecimal(Double.valueOf(rs.getString("NILAI_HA_TARIKHMOHON"))));
					} else {
						h.put("keterangan", "");
					}
					
				} else {
					h.put("keterangan", "");
				}
				
				senaraiHA.addElement(h);
				bil++;
			}
			
		}  finally {
			if (db != null)
				db.close();
		}
		
		return senaraiHA;
	}

	public void applyToAllHarta(String idPerintah, String idPerintahHTAOBMST, String idPerintahHAOBMST, String[] idsHTA, String[] idsHA, HttpSession session) throws Exception {
		Db db = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idDTL = "";
		 
		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			if (idsHTA != null) {
				for (int i = 0; i < idsHTA.length; i++) {
					deleteTblPPKPerintahHTAOBDTL(idsHTA[i]);
				}				
			}
			if (idsHA != null) {
				for (int i = 0; i < idsHA.length; i++) {
					deleteTblPPKPerintahHAOBDTL(idsHA[i]);
				}				
			}
			
			sql = "SELECT ID_PERINTAHHTAOBDTL AS ID_DTL, 'Y' AS FLAG_HTA FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idPerintahHTAOBMST + "'"
				+ " UNION"
				+ " SELECT ID_PERINTAHHAOBDTL AS ID_DTL, '' AS FLAG_HTA FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idPerintahHAOBMST + "'";
			
			
			ResultSet rs = stmt.executeQuery(sql);
		
			while (rs.next()){
				idDTL = rs.getString("ID_DTL");
				
				
				if (idsHTA != null) {
					for (int i = 0; i < idsHTA.length; i++) {
						
						insertIntoTblPPKPerintahHTAOBDTL(idDTL, idsHTA[i], rs.getString("FLAG_HTA"), userId);
						
					}				
				}
				
			
				if (idsHA != null) {
					for (int i = 0; i < idsHA.length; i++) {
						
						insertIntoTblPPKPerintahHAOBDTL(idDTL, idsHA[i], rs.getString("FLAG_HTA"), userId);
					}				
				}
			}
			
			if (idsHTA != null) {
				for (int i = 0; i < idsHTA.length; i++) {
					updatePecahanWarisHTA(idsHTA[i]);
				}				
			}
			if (idsHA != null) {
				for (int i = 0; i < idsHA.length; i++) {
					updatePecahanWarisHA(idsHA[i]);
				}				
			}
			
		} finally {
			if (db != null)
				db.close();
		}		
	}



	
	private static void insertIntoTblPPKPerintahHTAOBDTL(String idDTLOld, String idHTAMSTMaster, String flagHTA, String userId) throws Exception {
		Db db = null;
		String sql = "";
		Vector senaraiHTA = new Vector();
		ResultSet rs = null;
		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			String BA = "";
			String BB = "";
			String ID_PERINTAHHTAOBMST = "";
			String ID_HTA = "";
			
			sql = " SELECT   BA, BB " +
				  " FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idDTLOld + "'";
				
			
				rs = stmt.executeQuery(sql);
			
				while (rs.next()){
					BA =  rs.getString("BA");
					BB =  rs.getString("BB");
				}
			
				//untuk dapatkan id_HTA
				sql = "SELECT ID_HTA"
						+ " FROM TBLPPKPERINTAHHTAOBMST"
						+ " WHERE ID_PERINTAHHTAOBMST = '" + idHTAMSTMaster + "'";
					rs = stmt.executeQuery(sql);
					
					
					if (rs.next()) {
						
						ID_HTA = rs.getString("ID_HTA");
						
						Fraction fracSimati = new Fraction(1,1);
						//	if (rsHTA.next()){
						fracSimati = getFractionSimatiHTA(ID_HTA);
						//}
							
						Fraction fracWaris = new Fraction(0,1);
						if (Utils.parseLong(BB) > 0){
							fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));

						}
						fracWaris = fracWaris.times(fracSimati);
						
						
						
						//TBLPPKPERINTAHHTAOBDTL
						sql = "INSERT INTO TBLPPKPERINTAHHTAOBDTL (ID_PERINTAHHTAOBMST, ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1, BATAL_PA1, ID_PENJAGA2,"
							+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
							+ " ID_MASUK, TARIKH_MASUK, BA_WARIS, BB_WARIS)" 

							+ " SELECT " + idHTAMSTMaster + ", ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1, BATAL_PA1, ID_PENJAGA2,"
							+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
							+ " " + userId + ", SYSDATE, "+ fracWaris.numerator() +", "+ fracWaris.denominator() +"";
							
							if ("Y".equals(flagHTA)){
								sql = sql + " FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idDTLOld + "'";
							} else {
								sql = sql + " FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idDTLOld + "'";
							}
							

						stmt.executeUpdate(sql);
						
						
					}
				
		
		} finally {
			if (db != null)
				db.close();
			
			
			if (rs != null)
				rs.close();
			
		}
	}
	
	//aishah
public static Fraction getFractionSimatiHTA(String idHTA) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.BA_SIMATI, A.BB_SIMATI " +
					" FROM TBLPPKHTA A1,TBLPPKHTAPERMOHONAN A" +
					" WHERE A.ID_HTA=A1.ID_HTA " +
					"AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI  " +
							"AND A1.ID_HTA = '" + idHTA + "'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				if (rs.getString("BA_SIMATI") != null && rs.getString("BB_SIMATI") != null){
					//if (rs.getInt("BB_SIMATI") > 0){
					//Azam change on 31/3/2010
					if (rs.getLong("BB_SIMATI") > 0){
						Fraction f2 = new Fraction(rs.getLong("BA_SIMATI"),rs.getLong("BB_SIMATI"));
						fractionSimati = f2;
					}
				}
				
				System.out.println("BA_SIMATI=="+rs.getString("BA_SIMATI"));
				System.out.println("BB_SIMATI=="+rs.getString("BB_SIMATI"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return fractionSimati;
	}
	
	public static Fraction getFractionSimatiHA(String idHA) throws Exception{
		
		Db db = null;
		String sql = "";
		Fraction fractionSimati =  new Fraction(1, 1);

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.BA_SIMATI, A.BB_SIMATI " +
					" FROM TBLPPKHA A1,TBLPPKHAPERMOHONAN A " +
					" WHERE A1.ID_HA = A.ID_HA AND A.ID_PERMOHONANSIMATI = A1.ID_PERMOHONANSIMATI " +
					" AND A1.ID_HA = '" + idHA + "'";

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
	
	public static void updatePecahanWarisHTA(String idPerintahHTAOBMST) throws Exception {
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

//				Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
//				Pecahan pchn1 = new Pecahan(frac1.getNumerator(), frac1.getDenominator());
//				listFrac = pchn1.addToList(listFrac);
				list2 = pchn.addToList(list2);							
			}
	
			Hashtable hash;
			for (int j = list.size()-1 ; j >= 0; j--) {
				hash = (Hashtable) list.get(j);
				Pecahan pchn1 = (Pecahan) list2.get(j);
				sql = "UPDATE  TBLPPKPERINTAHHTAOBDTL SET BA_WARIS = '" + pchn1.getPengangka() + "', BB_WARIS = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHTAOBDTL = '"+ hash.get("ID_PERINTAHHTAOBDTL").toString()+ "'";
				//sql = "UPDATE  TBLPPKPERINTAHHTAOBDTL SET BA_WARIS_STR = '" + pchn1.getPengangka() + "', BB_WARIS_STR = '" + pchn1.getPenyebut() + "' WHERE ID_PERINTAHHTAOBDTL = '"+ hash.get("ID_PERINTAHHTAOBDTL").toString()+ "'";
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
				//Pecahan pchn = new Pecahan(Long.parseLong(k.get("BA_WARIS").toString()), Long.parseLong(k.get("BB_WARIS").toString()));
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
	
	//end aishah add
	
	private static void insertIntoTblPPKPerintahHAOBDTL(String idDTLOld, String idHAMSTMaster, String flagHTA, String userId) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		String BA = "";
		String BB = "";
		String ID_HA = "";
		
		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = " SELECT   BA, BB " +
					  " FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idDTLOld + "'";
					
				
					rs = stmt.executeQuery(sql);
				
					while (rs.next()){
						BA =  rs.getString("BA");
						BB =  rs.getString("BB");
					}
					System.out.println("sql BA BB ==== "+sql);
					
						//untuk dapatkan id_HA
						sql = "SELECT ID_HA"
							+ " FROM TBLPPKPERINTAHHAOBMST"
							+ " WHERE ID_PERINTAHHAOBMST = '" + idHAMSTMaster + "'";
						rs = stmt.executeQuery(sql);
						
						System.out.println("sql ID_HA ==== "+sql);
						
						if (rs.next()) {
							
							ID_HA = rs.getString("ID_HA");
							
							Fraction fracSimati = new Fraction(1,1);
							fracSimati = getFractionSimatiHA(ID_HA);

								
							Fraction fracWaris = new Fraction(0,1);
							if (Utils.parseLong(BB) > 0){
								fracWaris = new Fraction(Long.parseLong(BA),Long.parseLong(BB));

							}
							fracWaris = fracWaris.times(fracSimati);
							
								
							sql = "INSERT INTO TBLPPKPERINTAHHAOBDTL (ID_PERINTAHHAOBMST, ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1,  BATAL_PA1, ID_PENJAGA2,"
									+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
									+ " ID_MASUK, TARIKH_MASUK, ID_DB, BA_WARIS, BB_WARIS)" 

									+ " SELECT " + idHAMSTMaster + ", ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1, BATAL_PA1, ID_PENJAGA2, "
									+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
									+ " " + userId + ", SYSDATE, ID_DB, "+ fracWaris.numerator() +", "+ fracWaris.denominator() +"";
								
									if ("Y".equals(flagHTA)){
										sql = sql + " FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idDTLOld + "'";
									} else {
										sql = sql + " FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idDTLOld + "'";
									}
								System.out.println("insert TBLPPKPERINTAHHAOBDTL="+sql);

							stmt.executeUpdate(sql);
							
							
						}
			
			
		/*	OLD
			//TBLPPKPERINTAHHAOBDTL
			sql = "INSERT INTO TBLPPKPERINTAHHAOBDTL (ID_PERINTAHHAOBMST, ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1,  BATAL_PA1, ID_PENJAGA2,"
				+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
				+ " ID_MASUK, TARIKH_MASUK, ID_DB, BA_WARIS, BB_WARIS)" 

				+ " SELECT " + idHAMSTMaster + ", ID_OB, BA, BB, PEKALI, MINOR, ID_PENJAGA1, BATAL_PA1, ID_PENJAGA2, "
				+ " BATAL_PA2, ID_PENJAGA3, BATAL_PA3, ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR, CATATAN, ID_PA1, ID_PA2, ID_PA3, ID_PA4,"
				+ " " + userId + ", SYSDATE, ID_DB, BA_WARIS, BB_WARIS";
			
				if ("Y".equals(flagHTA)){
					sql = sql + " FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBDTL = '" + idDTLOld + "'";
				} else {
					sql = sql + " FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBDTL = '" + idDTLOld + "'";
				}*/

			
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	private static void deleteTblPPKPerintahHTAOBDTL(String idHTAMSTMaster) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			//TBLPPKPERINTAHHTAOBDTL
			sql = "DELETE FROM TBLPPKPERINTAHHTAOBDTL WHERE ID_PERINTAHHTAOBMST = '" + idHTAMSTMaster + "'";
			stmt.executeUpdate(sql);
		
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private static void deleteTblPPKPerintahHAOBDTL(String idHAMSTMaster) throws Exception {
		Db db = null;
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();
			
			//TBLPPKPERINTAHHAOBDTL
			sql = "DELETE FROM TBLPPKPERINTAHHAOBDTL WHERE ID_PERINTAHHAOBMST = '" + idHAMSTMaster + "'";
			stmt.executeUpdate(sql);
		
		} finally {
			if (db != null)
				db.close();
		}
	}
}
