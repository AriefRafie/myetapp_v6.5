package ekptg.report.ppk;

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

import ekptg.helpers.DB;

public class FrmPopupPilihPegawaiReportData {
	
	static Logger myLogger = Logger.getLogger(FrmPopupPilihPegawaiReportData.class);

	private Vector beanMaklumatPermohonan = new Vector();
	private Vector beanMaklumatPegawai = new Vector();
	private Vector beanMaklumatPejabatJKPTG = new Vector();
	private static Vector listPilihanOb = new Vector();
	private Vector beanMaklumatPentadbir = new Vector();
	private Vector beanSenaraiOBPentadbir = new Vector();
	private Vector beanPemohonTerdahulu = null;
	private Vector beanMaklumatPermohonanbyUser = new Vector();

	public void setMaklumatPermohonanbyUser(String user_id) throws Exception {
		Db db = null;
		this.beanMaklumatPermohonanbyUser.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

	
			sql = "SELECT UI.ID_NEGERI FROM USERS U,USERS_INTERNAL UI "+ 
			" WHERE UI.USER_ID = U.USER_ID AND U.USER_ID = '"+user_id+"'";
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			Integer count = Integer.valueOf(0);

			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idNegeri", rs.getString("ID_NEGERI"));
				this.beanMaklumatPermohonanbyUser.addElement(h);
				++bil;
				count = Integer.valueOf(count.intValue() + 1);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatusBatal(String noFail, String user_id ) throws Exception
	  {
		System.out.println("***********updateStatusBatal*************"); 
		System.out.println("noFail = " + noFail);
	    Db db = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    String sql2 = "";
	    String sql3 = "";
		try {
			System.out.println("noFail2 = " + noFail);
			String IdPermohonan = getIdPermohonan(noFail);
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			SQLRenderer rr = new SQLRenderer();

			r.update("id_permohonan", IdPermohonan);
			r.add("ID_STATUS", "152");
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
			 
		    sql = r.getSQLUpdate("tblppkpermohonan");
		    System.out.println("***********sql1 = *************"+sql);
		    stmt.executeUpdate(sql);
		    
		    /*
		    rr.update("id_permohonan", IdPermohonan);
		    rr.add("AKTIF", "0");
			//rr.add("ID_SUBURUSANSTATUS", "454");
			rr.add("ID_KEMASKINI", user_id );
			rr.add("TARIKH_KEMASKINI", r.unquote("sysdate")); 
			 
		    sql2 = rr.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
		    System.out.println("***********sql2 = *************"+sql2);
		    stmt.executeUpdate(sql2);
		    */
		    sql3 = " SELECT * FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_SUBURUSANSTATUS = '454' AND AKTIF = '1' AND ID_PERMOHONAN = '"+IdPermohonan+"'";
		    ResultSet rs3 = stmt.executeQuery(sql3);
 
			int bil = 0;
			while (rs3.next()) {
				bil++;
				
			}
			System.out.println("bil = "+bil);
			
			if (bil == 0)
			{
				sql2 = "UPDATE TBLRUJSUBURUSANSTATUSFAIL SET AKTIF = '0', ID_KEMASKINI = '"+user_id+"' , TARIKH_KEMASKINI = sysdate   WHERE id_permohonan = '"+IdPermohonan+"' AND AKTIF ='1'";
				System.out.println("***********sql2 = *************"+sql2);
			    stmt.executeQuery(sql2);
			    
			    sql3 = " INSERT INTO TBLRUJSUBURUSANSTATUSFAIL (ID_PERMOHONAN, ID_SUBURUSANSTATUS, AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_FAIL) "+
					   " VALUES ('"+IdPermohonan+"', '454', '1', '"+ user_id +"',sysdate, '"+ user_id +"', sysdate, (SELECT ID_FAIL FROM TBLPFDFAIL WHERE NO_FAIL = '"+noFail+"')) "; 
			    System.out.println("***********sql3 = *************"+sql);
				stmt.executeQuery(sql3); 
			}
   
			
		} finally {
			if (db != null)
				db.close();
		}
	    
	  }
	
	public void setMaklumatPermohonan(String noFail) throws Exception {
		Db db = null;
		this.beanMaklumatPermohonan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERMOHONAN");
			r.add("A.ID_NEGERIMHN");

			r.add("B.ID_FAIL", r.unquote("A.ID_FAIL"));

			r.add("B.NO_FAIL", noFail);

			sql = r.getSQLSelect("TBLPPKPERMOHONAN A, TBLPFDFAIL B");
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			Integer count = Integer.valueOf(0);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("idNegeri", rs.getString("ID_NEGERIMHN"));
				this.beanMaklumatPermohonan.addElement(h);
				++bil;
				count = Integer.valueOf(count.intValue() + 1);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPegawai(String idUnitPSK, String flagReport) throws Exception {
		Db db = null;
		this.beanMaklumatPegawai.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("INITCAP(NAMA_PEGAWAI) NAMA_PEGAWAI");
			r.add("INITCAP(JAWATAN) AS JAWATAN");
			r.add("INITCAP(NAMA_PEJABAT) AS NAMA_PEJABAT");
			r.add("INITCAP(KETERANGAN_UNIT_PSK) AS KETERANGAN_UNIT_PSK");
			r.add("ID_UNITPSK", idUnitPSK);

			sql = r.getSQLSelect("TBLPPKRUJUNIT");
			System.out.println("getPegawai :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			Integer count = Integer.valueOf(0);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				if (flagReport.equals("S")) {
					h.put("nama", rs.getString("NAMA_PEGAWAI").toUpperCase());
					h.put("jawatan", rs.getString("JAWATAN")==null?"":rs.getString("JAWATAN"));
					h.put("namaPejabat",rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT")+ " " + rs.getString("KETERANGAN_UNIT_PSK"));
					h.put("unit",rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
					h.put("negeri",rs.getString("KETERANGAN_UNIT_PSK")==null?"":rs.getString("KETERANGAN_UNIT_PSK"));
				} else {
					h.put("nama", rs.getString("NAMA_PEGAWAI").toUpperCase());
					h.put("jawatan", "PENOLONG PENTADBIR TANAH");
				}

				this.beanMaklumatPegawai.addElement(h);
				++bil;
				count = Integer.valueOf(count.intValue() + 1);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPejabatJKPTG(String idPejabatJKPTG) throws Exception {
		Db db = null;
		this.beanMaklumatPejabatJKPTG.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PEJABATJKPTG");
			r.add("A.ID_JAWATAN");
			r.add("B.KETERANGAN");
			r.add("A.NAMA_PEJABAT");
			r.add("A.ALAMAT1");
			r.add("A.ALAMAT2");
			r.add("A.ALAMAT3");
			r.add("A.POSKOD");
			r.add("C.NAMA_DAERAH");
			r.add("D.NAMA_NEGERI");

			r.add("A.ID_JAWATAN", r.unquote("B.ID_JAWATAN(+)"));
			r.add("A.ID_DAERAH(+)", r.unquote("C.ID_DAERAH"));
			r.add("A.ID_NEGERI", r.unquote("D.ID_NEGERI"));

			r.add("A.ID_PEJABATJKPTG", idPejabatJKPTG);

			sql = r.getSQLSelect("TBLRUJPEJABATJKPTG A, TBLRUJJAWATAN B, TBLRUJDAERAH C, TBLRUJNEGERI D");
			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			Integer count = Integer.valueOf(0);

			while (rs.next()) {
				Hashtable h = new Hashtable();

				h.put("jawatan", (rs.getString("KETERANGAN") == null) ? "" : rs.getString("KETERANGAN"));
				h.put("unit", (rs.getString("NAMA_PEJABAT") == null) ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("alamat1", (rs.getString("ALAMAT1") == null) ? "" : rs.getString("ALAMAT1"));
				h.put("alamat2", (rs.getString("NAMA_PEJABAT") == null) ? "": rs.getString("ALAMAT2"));
				h.put("alamat3", (rs.getString("NAMA_PEJABAT") == null) ? "": rs.getString("ALAMAT3") == null?"":"");
				h.put("poskod", (rs.getString("POSKOD") == null) ? "" : rs.getString("POSKOD"));
				h.put("daerah", (rs.getString("NAMA_DAERAH") == null) ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", (rs.getString("NAMA_NEGERI") == null) ? "" : rs.getString("NAMA_NEGERI"));
				this.beanMaklumatPejabatJKPTG.addElement(h);
				++bil;
				count = Integer.valueOf(count.intValue() + 1);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getDaerahMohon(String noFail, String flagReport) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT INITCAP(C.NAMA_DAERAH) AS NAMA_DAERAH FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJDAERAH C WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_DAERAHMHN = C.ID_DAERAH AND B.NO_FAIL = '"
					+ noFail + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String str1;
				if (flagReport.equals("S"))
					return rs.getString("NAMA_DAERAH").toString();

				return rs.getString("NAMA_DAERAH").toString().toUpperCase();
			}

			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String str1;
				return rs.getString("NAMA_NEGERI").toString();
			}
			return "";
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getUnit(String idUnit) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT A.NAMA_PEJABAT, B.NAMA_DAERAH FROM TBLRUJPEJABATJKPTG A, TBLRUJDAERAH B WHERE A.ID_DAERAH = B.ID_DAERAH AND ID_PEJABATJKPTG = '"
					+ idUnit + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				String str1;
				temp = rs.getString("NAMA_PEJABAT").toString() + " " + rs.getString("NAMA_DAERAH").toString();
				return temp.toUpperCase();
			}
			return "";
			
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getDaerahMohonByIdFail(String idFail, String flagReport) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();

			sql = "SELECT INITCAP(C.NAMA_DAERAH) AS NAMA_DAERAH"
					+ " FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJDAERAH C"
					+ " WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_DAERAHMHN = C.ID_DAERAH"
					+ " AND B.ID_FAIL = '" + idFail + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if ("S".equals(flagReport)) {
					return rs.getString("NAMA_DAERAH").toString();
				} else {
					return rs.getString("NAMA_DAERAH").toString().toUpperCase();
				}
			} else {
				return "";
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdFail(String noFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT A.ID_FAIL FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B WHERE A.ID_FAIL = B.ID_FAIL AND B.NO_FAIL = '"
					+ noFail + "' AND A.ID_STATUS != 999";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				String str1;
				return rs.getString("ID_FAIL");
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPermohonan(String noFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT A.ID_PERMOHONAN FROM TBLPPKPERMOHONAN A, TBLPFDFAIL B WHERE A.ID_FAIL = B.ID_FAIL AND B.NO_FAIL = '"
					+ noFail + "'";

			Statement stmt = db.getStatement();
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				String str1;
				return rs.getString("ID_PERMOHONAN");
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getFlagBorangF(String idfail) throws Exception {
		Db db = null;
		String flagBorangF = "T";
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT ROWNUM"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKPEMOHON C, TBLPPKPERMOHONANSIMATI D, TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F, TBLPPKPERINTAH G, TBLPPKPERINTAHHTAOBMST H, TBLPPKPERINTAHHTAOBDTL I"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN AND B.ID_PEMOHON = C.ID_PEMOHON AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST AND H.ID_JENISPERINTAH IN (2)"
					//15122017 COMMENT BY PEJE -  AISHAH TERTINGGAL TO COMMENT THIS LINE
//					+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN AND A.ID_FAIL =  '" + idfail + "'"					
					
					+ " UNION"
					+ " SELECT ROWNUM"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND J.ID_JENISPERINTAH IN (2)"
					//+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'"
					+ " UNION"
					+ " SELECT ROWNUM"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHTAOBMST H,"
					+ " TBLPPKPERINTAHHTAOBDTL I"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST"
					+ " AND H.ID_JENISPERINTAH IN (1,7,2)"
					+ " AND I.STATUS_TADBIR LIKE 'Y' "
					//+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'"
					+ " UNION"
					+ " SELECT ROWNUM"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND J.ID_JENISPERINTAH IN (1,7)"
					+ " AND K.STATUS_TADBIR LIKE 'Y'"
					//+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'";

			System.out.println(sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				flagBorangF = "Y";
			}
		} finally {
			if (db != null)
				db.close();
		}
		return flagBorangF;
	}

	// delete checked ob
	public static void deletePilihanOb(String id_perbicaraan) throws Exception {

		Db db = null;
		String sql = "";

		try {

			db = new Db();
			Statement stmt = db.getStatement();

			sql = "DELETE FROM TBLPPKNOTISOB WHERE id_perbicaraan = '"
					+ id_perbicaraan + "'";
			sql += " AND FLAG_CETAK = '1'";
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}// delete checked ob

	// simpan pilihan ob
	public static void simpanPilihanOb(Hashtable data, String id_ob) throws Exception {

		Connection conn = null;
		Db db = null;

		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			long id_notisob = DB.getNextID("TBLPPKNOTISOB_SEQ");

			String id_user = (String) data.get("id_user");
			String id_perbicaraan = (String) data.get("id_perbicaraan");

			// flag utk borang dd
			String flag_cetak = "1";

			// insert data into tblsemakanhantar
			sql = "INSERT INTO Tblppknotisob "
					+ "(id_notisob, id_perbicaraan, id_ob, id_masuk, tarikh_masuk, flag_cetak)  "
					+ "VALUES ('" + id_notisob + "', '" + id_perbicaraan
					+ "', '" + id_ob + "', '" + id_user + "', sysdate, '"
					+ flag_cetak + "') ";
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ se.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

	}// close SIMPAN PILIHAN

	// LIST OB cbsemak
	public static void setListPilihanOb(String id_permohonansimati, String id_perbicaraan, String id_simati) throws Exception {

		Db db = null;
		listPilihanOb.clear();
		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT ob.id_ob, ob.nama_ob, ob.umur,";
			sql += " (SELECT COUNT(*) FROM tblppknotisob WHERE id_ob=ob.id_ob AND flag_cetak = '1' AND id_perbicaraan = '" + id_perbicaraan + "')as flag ";
			sql += " FROM Tblppkob ob, Tblppkpermohonansimati sm, Tblppkrujtarafkptg tr, Tblppkpermohonan p ";
			sql += " WHERE ob.id_permohonansimati = sm.id_permohonansimati ";
			sql += " AND ob.id_tarafkptg = tr.id_tarafkptg ";
			sql += " AND sm.id_permohonan = p.id_permohonan ";
			sql += " AND NVL(ob.status_hidup,0) = 0 ";
			sql += " AND NVL(ob.umur,18)  >= 18 ";
			sql += " AND p.id_status <> '169' ";
			sql += " AND NVL(ob.status_ob,0) != ALL(3,4) ";
			sql += " AND ob.id_simati = '" + id_simati + "'";
			sql += " AND ob.id_permohonansimati <= '" + id_permohonansimati
					+ "'";
			sql += " ORDER BY ob.umur desc,ob.nama_ob asc ";

			myLogger.info("list ob = " + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();

				h.put("bil", bil);
				h.put("id_ob", rs.getString("id_ob") == null ? "" : rs .getString("id_ob"));
				h.put("nama_ob", rs.getString("nama_ob") == null ? "" : rs.getString("nama_ob"));
				h.put("flag", rs.getString("flag") == null ? "" : rs.getString("flag"));

				listPilihanOb.addElement(h);
				bil++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (db != null)
				db.close();
		}
	}// close OB cbsemak

	public void setSenaraiOBPentadbir_OLD(String idfail) throws Exception {
		Db db = null;
		beanSenaraiOBPentadbir.clear();
		String sqlIdOB = "";

		try {
			db = new Db();

			
			sqlIdOB = "SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                     " CASE"+
                       " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN L.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'" +
                       " ELSE ''"+
                     " END AS WARGANEGARA"+ 
                      

                   " FROM"+ 
                      " TBLPPKPERINTAHHTAOBMST A,"+
                      " TBLPPKPERINTAH B,"+ 
                      " TBLPPKPERINTAHHTAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                       " (SELECT"+
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                   " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                       " AND D.ID_OB = C.ID_PA1"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 1"+
                       " AND C.STATUS_TADBIR = 'Y'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       " AND C.ID_OB IS NULL"+
                      
                    " UNION"+
                
                    " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                    " CASE"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                    " FROM"+ 
                      " TBLPPKPERINTAHHTAOBMST A,"+
                      " TBLPPKPERINTAH B,"+ 
                      " TBLPPKPERINTAHHTAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                      " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                    " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                       " AND D.ID_OB = C.ID_PA2"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 1"+
                       " AND C.STATUS_TADBIR = 'Y'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       " AND C.ID_OB IS NULL"+
                      
                     " UNION"+
                
                     " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                     " CASE"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                     " FROM"+ 
                     
                      " TBLPPKPERINTAHHTAOBMST A,"+
                      " TBLPPKPERINTAH B,"+ 
                      " TBLPPKPERINTAHHTAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                      " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      "CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                     " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                       " AND D.ID_OB = C.ID_PA3"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 1"+
                       " AND C.STATUS_TADBIR = 'Y'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       " AND C.ID_OB IS NULL"+ 
                     
                       " UNION"+

                       " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                       " CASE"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                       " FROM"+ 
                        " TBLPPKPERINTAHHTAOBMST A,"+
                        " TBLPPKPERINTAH B,"+ 
                        " TBLPPKPERINTAHHTAOBDTL C,"+ 
                        " TBLPPKOB D,"+
                        " TBLPFDFAIL E,"+
                        " TBLPPKPERMOHONAN F,"+
                        " TBLPPKKEPUTUSANPERMOHONAN G,"+
                        " TBLPPKPERBICARAAN H,"+
                        " TBLPPKPERINTAH I,"+
                        " TBLRUJBANDAR J,"+
                        " TBLRUJNEGERI K,"+
                        " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                       " WHERE"+ 
                        " B.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND E.ID_FAIL = F.ID_FAIL"+
                        " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                        " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                        " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                        " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                        " AND D.ID_OB = C.ID_PA4"+
                        " AND D.ID_OB = AAA.ID_OB"+
                        " AND D.ID_NEGERI = K.ID_NEGERI"+
                        " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                        " AND A.ID_JENISPERINTAH = 1"+
                        " AND C.STATUS_TADBIR = 'Y'"+
                        " AND E.ID_FAIL = '" + idfail + "'"+
                        " AND C.ID_OB IS NULL"+
                      
                      " UNION"+
                
                      " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                      " CASE"+ 
                      " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                      " FROM"+ 
                       " TBLPPKPERINTAHHTAOBMST A,"+
                       " TBLPPKPERINTAH B,"+ 
                       " TBLPPKPERINTAHHTAOBDTL C,"+ 
                       " TBLPPKOB D,"+
                       " TBLPFDFAIL E,"+
                       " TBLPPKPERMOHONAN F,"+
                       " TBLPPKKEPUTUSANPERMOHONAN G,"+
                       " TBLPPKPERBICARAAN H,"+
                       " TBLPPKPERINTAH I,"+
                       " TBLRUJBANDAR J,"+
                       " TBLRUJNEGERI K,"+
                       "(SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                      " WHERE"+ 
                        " B.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND E.ID_FAIL = F.ID_FAIL"+
                        " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                        " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                        " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                        " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                        " AND D.ID_OB = C.ID_OB"+
                        " AND D.ID_OB = AAA.ID_OB"+
                        " AND D.ID_NEGERI = K.ID_NEGERI"+
                        " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                        " AND A.ID_JENISPERINTAH = 2"+
                        " AND A.FLAG_HARTA = 'B'"+
                        " AND E.ID_FAIL = '" + idfail + "'"+
                      
                      " UNION"+
                 
                      " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                      " CASE"+ 
                      " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                      " FROM"+ 
                       " TBLPPKPERINTAHHAOBMST A,"+
                       " TBLPPKPERINTAH B,"+ 
                       " TBLPPKPERINTAHHAOBDTL C,"+ 
                       " TBLPPKOB D,"+
                       " TBLPFDFAIL E,"+
                       " TBLPPKPERMOHONAN F,"+
                       " TBLPPKKEPUTUSANPERMOHONAN G,"+
                       " TBLPPKPERBICARAAN H,"+
                       " TBLPPKPERINTAH I,"+
                       " TBLRUJBANDAR J,"+
                       " TBLRUJNEGERI K,"+
                       " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                      " WHERE"+ 
                        " B.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND E.ID_FAIL = F.ID_FAIL"+
                        " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                        " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                        " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                        " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"+
                        " AND D.ID_OB = C.ID_PA1"+
                        " AND D.ID_OB = AAA.ID_OB"+
                        " AND D.ID_NEGERI = K.ID_NEGERI"+
                        " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                        " AND A.ID_JENISPERINTAH = 1"+
                        " AND C.STATUS_TADBIR = 'Y'"+
                        " AND E.ID_FAIL = '" + idfail + "'"+
                        " AND C.ID_OB IS NULL"+
                      
                      " UNION"+
                
                      " SELECT DISTINCT D.ID_OB , D.NAMA_OB,"+
                      " CASE"+ 
                      " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+      
                      " FROM"+ 
                       " TBLPPKPERINTAHHAOBMST A,"+
                       " TBLPPKPERINTAH B,"+
                       " TBLPPKPERINTAHHAOBDTL C,"+ 
                       " TBLPPKOB D,"+
                       " TBLPFDFAIL E,"+
                       " TBLPPKPERMOHONAN F,"+
                       " TBLPPKKEPUTUSANPERMOHONAN G,"+
                       " TBLPPKPERBICARAAN H,"+
                       " TBLPPKPERINTAH I,"+
                       " TBLRUJBANDAR J,"+
                       " TBLRUJNEGERI K,"+
                       " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                      " WHERE"+ 
                        " B.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND E.ID_FAIL = F.ID_FAIL"+
                        " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                        " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                        " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                        " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                        " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"+
                        " AND D.ID_OB = C.ID_PA2"+
                        " AND D.ID_OB = AAA.ID_OB"+
                        " AND D.ID_NEGERI = K.ID_NEGERI"+
                        " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                        " AND A.ID_JENISPERINTAH = 1"+
                        " AND C.STATUS_TADBIR = 'Y'"+
                        " AND E.ID_FAIL = '" + idfail + "'"+
                        " AND C.ID_OB IS NULL"+
              
                     " UNION"+
                     " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                     " CASE"+
                     " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                     " FROM"+
                      " TBLPPKPERINTAHHAOBMST A,"+
                      " TBLPPKPERINTAH B,"+
                      " TBLPPKPERINTAHHAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                      " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                     " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"+
                       " AND D.ID_OB = C.ID_PA3"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 1"+
                       " AND C.STATUS_TADBIR = 'Y'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       " AND C.ID_OB IS NULL"+
                   
                      
                    " UNION"+
                    " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                    " CASE"+
                    " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                    " FROM"+ 
                      " TBLPPKPERINTAHHAOBMST A,"+
                      " TBLPPKPERINTAH B,"+
                      " TBLPPKPERINTAHHAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                      " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                    " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"+
                       " AND D.ID_OB = C.ID_PA4"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 1"+
                       " AND C.STATUS_TADBIR = 'Y'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       " AND C.ID_OB IS NULL"+
                      
                    " UNION"+
                
                    " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                    " CASE"+ 
                    " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"+
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                    " FROM"+ 
                      " TBLPPKPERINTAHHAOBMST A,"+
                      " TBLPPKPERINTAH B,"+
                      " TBLPPKPERINTAHHAOBDTL C,"+ 
                      " TBLPPKOB D,"+
                      " TBLPFDFAIL E,"+
                      " TBLPPKPERMOHONAN F,"+
                      " TBLPPKKEPUTUSANPERMOHONAN G,"+
                      " TBLPPKPERBICARAAN H,"+
                      " TBLPPKPERINTAH I,"+
                      " TBLRUJBANDAR J,"+
                      " TBLRUJNEGERI K,"+
                      " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                    " WHERE"+ 
                       " B.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND E.ID_FAIL = F.ID_FAIL"+
                       " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                       " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                       " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                       " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                       " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"+
                       " AND D.ID_OB = C.ID_OB"+
                       " AND D.ID_OB = AAA.ID_OB"+
                       " AND D.ID_NEGERI = K.ID_NEGERI"+
                       " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                       " AND A.ID_JENISPERINTAH = 2"+
                       " AND A.FLAG_HARTA = 'B'"+
                       " AND E.ID_FAIL = '" + idfail + "'"+
                       
                       " UNION"+

                       " SELECT DISTINCT D.ID_OB, D.NAMA_OB,"+
                       " CASE"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN ''"+
                       " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"+
                       " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"+ 
                       " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"+
                       " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"+
                     " END  AS NO_KP,"+
                     " CASE"+ 
                       " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"+
                       " WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'" +
                       " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"+
                       " ELSE ''"+
                     " END AS WARGANEGARA"+   
                        " FROM"+ 
                          " TBLPPKPERINTAHHTAOBMST A,"+
                          " TBLPPKPERINTAH B,"+
                          " TBLPPKPERINTAHHTAOBDTL C,"+ 
                          " TBLPPKOB D,"+
                          " TBLPFDFAIL E,"+
                          " TBLPPKPERMOHONAN F,"+
                          " TBLPPKKEPUTUSANPERMOHONAN G,"+
                          " TBLPPKPERBICARAAN H,"+
                          " TBLPPKPERINTAH I,"+
                          " TBLRUJBANDAR J,"+
                          " TBLRUJNEGERI K,"+
                          " (SELECT"+ 
						      " CASE"+ 
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"+
						          " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"+ 
						          " ELSE TBLPPKOB.NO_KP_BARU"+
						      " END || '' ||"+
						      " CASE"+
						         " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"+
						      " END || '' ||"+     
						      " CASE"+
						        " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"+     
						      " END AS NO_KP1, ID_OB"+
						      " FROM TBLPPKOB ) AAA"+
                        " WHERE"+ 
                          " B.ID_PERINTAH = A.ID_PERINTAH"+
                          " AND E.ID_FAIL = F.ID_FAIL"+
                          " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"+
                          " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"+
                          " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"+
                          " AND I.ID_PERINTAH = A.ID_PERINTAH"+
                          " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"+
                          " AND D.ID_OB = C.ID_OB"+
                          " AND D.ID_OB = AAA.ID_OB"+
                          " AND D.ID_NEGERI = K.ID_NEGERI"+
                          " AND D.ID_BANDAR = J.ID_BANDAR(+)"+
                          " AND A.ID_JENISPERINTAH = 2"+
                          " AND A.FLAG_HARTA = 'B'"+
                          " AND E.ID_FAIL ='" + idfail + "'";
			

			System.out.println("sqlIdOB==========="+sqlIdOB);
			Statement stmt = db.getStatement();
			ResultSet rsIdOB = stmt.executeQuery(sqlIdOB);
			String temp = "";
			String temp3 = "";
			Hashtable h1;
			Vector list = new Vector();
			list.clear();
			int countOB = 0;
			while (rsIdOB.next()) {
				h1 = new Hashtable();

				h1.put("ob",rsIdOB.getString("ID_OB"));
				h1.put("namaOB",rsIdOB.getString("NAMA_OB"));
				h1.put("noKPOB",rsIdOB.getString("NO_KP")!= null ? rsIdOB.getString("NO_KP"):"");
				h1.put("wargaOB",rsIdOB.getString("WARGANEGARA")!=null?rsIdOB.getString("WARGANEGARA"):"");

				list.addElement(h1);
				countOB++;

				
				if (temp.equals("") && temp3.equals("")){
					if(rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=" "){
					temp = "\n" + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"") + " Warganegara: " + (rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=""?rsIdOB.getString("WARGANEGARA"):"");
					temp3 = rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"") + " (" + (rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=""?rsIdOB.getString("WARGANEGARA"):"")+ ")";
					}
					else{
						temp = "\n" + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"");
						temp3 = rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"");
					}
				}
				else{
					if(rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=" "){
					temp = temp + "\n" + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"") + " Warganegara: " + (rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=""?rsIdOB.getString("WARGANEGARA"):"");
					temp3 = temp3 + ", " + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"") + " (" + (rsIdOB.getString("WARGANEGARA")!=null && rsIdOB.getString("WARGANEGARA")!=""?rsIdOB.getString("WARGANEGARA"):"") + ")";
					}
					else{
						temp = temp + "\n" + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"");
						temp3 = temp3 + ", " + rsIdOB.getString("NAMA_OB") + (rsIdOB.getString("NO_KP")!= null ?" No KP:"+ rsIdOB.getString("NO_KP"):"");
					}
				}
				

			}

			String sqlAlamat = "";

			if (list.size() != 0 ){
				
				Hashtable h2 = (Hashtable)list.get(0);
	
				sqlAlamat = "SELECT "
						+ " CASE"
						+ " WHEN A.ALAMAT1_SURAT IS NULL THEN 'TIADA'"
						+ " WHEN A.ALAMAT2_SURAT IS NULL AND A.ALAMAT3_SURAT IS NULL THEN  REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') || ', ' || REPLACE(UPPER(A.POSKOD_SURAT),',') || ' ' || REPLACE(UPPER(B.KETERANGAN),',') ||', '|| REPLACE(UPPER(C.NAMA_NEGERI),',')"
						+ " WHEN A.ALAMAT2_SURAT IS NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT3_SURAT),','),'&','&#38;') || ', ' || REPLACE(UPPER(A.POSKOD_SURAT),',') || ' ' ||REPLACE(UPPER(B.KETERANGAN),',') ||', '|| REPLACE(UPPER(C.NAMA_NEGERI),',')"
						+ " WHEN A.ALAMAT3_SURAT IS NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT2_SURAT),','),'&','&#38;') || ', ' || UPPER(A.POSKOD_SURAT) || ' ' ||UPPER(B.KETERANGAN) ||', '|| UPPER(C.NAMA_NEGERI)"
						+ " WHEN A.ALAMAT3_SURAT IS NOT NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT2_SURAT),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(A.ALAMAT3_SURAT),','),'&','&#38;') || ', ' || UPPER(A.POSKOD_SURAT) || ' ' ||UPPER(B.KETERANGAN) ||', '|| UPPER(C.NAMA_NEGERI)"
						+ " ELSE 'TIADA'" + " END AS ALAMAT_PENUH_OB" 
						+ " FROM TBLPPKOB A," + " TBLRUJBANDAR B, TBLRUJNEGERI C" 
						+ " WHERE B.ID_BANDAR(+) = A.ID_BANDARSURAT"
						+ " AND C.ID_NEGERI = A.ID_NEGERISURAT"
						+ " AND A.ID_OB = " + h2.get("ob");

				ResultSet rsAlamat = stmt.executeQuery(sqlAlamat);
				Hashtable h3;
				String temp2 = "";
				String temp4 = "";
				Vector listAlamat = new Vector();
				while (rsAlamat.next()) {
					h3 = new Hashtable();
					if (countOB > 1) {
						h3 = new Hashtable();
						temp2 = temp + "\nadalah Sebagai Pentadbir bersama"
								+ "\n\n" + "yang beralamat "
								+ rsAlamat.getString("ALAMAT_PENUH_OB");
						h3.put("maklumatPentadbir", temp2);
					} else {
						h3 = new Hashtable();
						temp2 = temp + "\nadalah Sebagai Pentadbir" + "\n\n"
								+ "yang beralamat "
								+ rsAlamat.getString("ALAMAT_PENUH_OB");
						h3.put("maklumatPentadbir", temp2);

					}
					temp4 = temp3 + " yang beralamat "
							+ rsAlamat.getString("ALAMAT_PENUH_OB");
					h3.put("maklumatPentadbirUtkBorangE", temp4);
					beanSenaraiOBPentadbir.addElement(h3);
				}
			}
			
			
				//AISHAH MASUKKAN 13102017
				//setMaklumatPentadbir( idfail);
				

			
			

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void setSenaraiOBPentadbir(String idfail) throws Exception {
		setSenaraiOBPentadbir(idfail,"",null);
	}

	public void setSenaraiOBPentadbir(String idfail,String flagJanaPerintah,Db db) throws Exception {
		Db db1 = null;
		beanSenaraiOBPentadbir.clear();
		String sqlIdOB = "";

		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}

			sqlIdOB = "SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP," + " CASE " + " WHEN D.JENIS_KP = 4 THEN ' No.Pasport :'" + " WHEN D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN D.JENIS_KP = 6 THEN ' Polis :'" + " WHEN D.JENIS_KP = 7 THEN ' Lain-lain :'" + " ELSE ' No KP:'" + " END AS JENIS_KP," + " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'" + " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'" + " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'" + " ELSE ''" + " END AS WARGANEGARA" +

					" FROM" + " TBLPPKPERINTAHHTAOBMST A," + " TBLPPKPERINTAH B," + " TBLPPKPERINTAHHTAOBDTL C," + " TBLPPKOB D," + " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F," + " TBLPPKKEPUTUSANPERMOHONAN G," + " TBLPPKPERBICARAAN H," + " TBLPPKPERINTAH I," + " TBLRUJBANDAR J," + " TBLRUJNEGERI K,"
					+ " (SELECT" + " CASE" + " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA" + " ELSE TBLPPKOB.NO_KP_BARU" + " END || '' ||"
					+ " CASE" + " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA" + " END || '' ||" + " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN" + " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA" + " WHERE" + " B.ID_PERINTAH = A.ID_PERINTAH" + " AND E.ID_FAIL = F.ID_FAIL" + " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN" + " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN" + " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST" + " AND D.ID_OB = C.ID_PA1" + " AND D.ID_OB = AAA.ID_OB" + " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)" + " AND A.ID_JENISPERINTAH = 1" + " AND C.STATUS_TADBIR = 'Y'" + " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHTAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHTAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"
					+ " AND D.ID_OB = C.ID_PA2"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+

					" TBLPPKPERINTAHHTAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHTAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ "CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"
					+ " AND D.ID_OB = C.ID_PA3"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHTAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHTAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"
					+ " AND D.ID_OB = C.ID_PA4"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHTAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHTAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ "(SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"
					+ " AND D.ID_OB = C.ID_OB"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 2"
					+ " AND A.FLAG_HARTA = 'B'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB,"
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"
					+ " AND D.ID_OB = C.ID_PA1"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB , "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"
					+ " AND D.ID_OB = C.ID_PA2"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+ " SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"
					+ " AND D.ID_OB = C.ID_PA3"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+ " SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"
					+ " AND D.ID_OB = C.ID_PA4"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 1"
					+ " AND C.STATUS_TADBIR = 'Y'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+ " AND C.ID_OB IS NULL"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHAOBMST = C.ID_PERINTAHHAOBMST"
					+ " AND D.ID_OB = C.ID_OB"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)"
					+ " AND A.ID_JENISPERINTAH = 2"
					+ " AND A.FLAG_HARTA = 'B'"
					+ " AND E.ID_FAIL = '"
					+ idfail
					+ "'"
					+

					" UNION"
					+

					" SELECT DISTINCT D.ID_OB, "
					+ " CASE "     
					+ " WHEN d.nama_ob IS NOT NULL "
					+ " THEN    REPLACE ((d.nama_ob), '&','&#38;') "
					+ "  ELSE "
					+ "  d.nama_ob "
					+ "  END AS nama_ob, "
					+ " CASE"
					+ " WHEN AAA.NO_KP1 IS NULL THEN ''"
					+ " WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''"
					+ " WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)"
					+ " WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'"
					+ " ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NOT NULL AND D.JENIS_KP = 4 THEN ' No.Pasport :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 5 THEN ' Tentera :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 6 THEN ' Polis :'"
					+ " WHEN AAA.NO_KP1 IS NULL AND D.JENIS_KP = 7 THEN ' Lain-lain :'"
					+ " ELSE ' No KP:'"
					+ "	END AS JENIS_KP,"
					+ " CASE"
					+ " WHEN D.JENIS_WARGA = 0 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN D.JENIS_WARGA = 2 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " ELSE ''"
					+ " END AS WARGANEGARA"
					+ " FROM"
					+ " TBLPPKPERINTAHHTAOBMST A,"
					+ " TBLPPKPERINTAH B,"
					+ " TBLPPKPERINTAHHTAOBDTL C,"
					+ " TBLPPKOB D,"
					+ " TBLPFDFAIL E,"
					+ " TBLPPKPERMOHONAN F,"
					+ " TBLPPKKEPUTUSANPERMOHONAN G,"
					+ " TBLPPKPERBICARAAN H,"
					+ " TBLPPKPERINTAH I,"
					+ " TBLRUJBANDAR J,"
					+ " TBLRUJNEGERI K,"
					+ " (SELECT"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA"
					+ " ELSE TBLPPKOB.NO_KP_BARU"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA"
					+ " END || '' ||"
					+ " CASE"
					+ " WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN"
					+ " END AS NO_KP1, ID_OB"
					+ " FROM TBLPPKOB ) AAA"
					+ " WHERE"
					+ " B.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND E.ID_FAIL = F.ID_FAIL"
					+ " AND F.ID_PERMOHONAN = G.ID_PERMOHONAN"
					+ " AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN"
					+ " AND H.ID_PERBICARAAN = I.ID_PERBICARAAN"
					+ " AND I.ID_PERINTAH = A.ID_PERINTAH"
					+ " AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST"
					+ " AND D.ID_OB = C.ID_OB"
					+ " AND D.ID_OB = AAA.ID_OB"
					+ " AND D.ID_NEGERI = K.ID_NEGERI"
					+ " AND D.ID_BANDAR = J.ID_BANDAR(+)" + " AND A.ID_JENISPERINTAH = 2" + " AND A.FLAG_HARTA = 'B'" + " AND E.ID_FAIL ='" + idfail + "'";

			Statement stmt = db1.getStatement();
			ResultSet rsIdOB = stmt.executeQuery(sqlIdOB);
			System.out.println("SQL OB:" + sqlIdOB);
			String temp = "";
			String temp3 = "";
			Hashtable h1;
			Vector list = new Vector();
			list.clear();
			int countOB = 0;
			while (rsIdOB.next()) {
				h1 = new Hashtable();

				h1.put("ob", rsIdOB.getString("ID_OB"));
				h1.put("namaOB", rsIdOB.getString("NAMA_OB"));
				h1.put("noKPOB", rsIdOB.getString("NO_KP") != null ? rsIdOB.getString("NO_KP") : "");
				h1.put("wargaOB", rsIdOB.getString("WARGANEGARA") != null ? rsIdOB.getString("WARGANEGARA") : "");
				h1.put("jenisKP", rsIdOB.getString("JENIS_KP") != null ? rsIdOB.getString("JENIS_KP") : "");

				list.addElement(h1);
				countOB++;

				if (temp.equals("") && temp3.equals("")) {
					if (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != " ") {
						temp = "\n" + rsIdOB.getString("NAMA_OB");
						if (rsIdOB.getString("NO_KP") != null) {
							temp = temp
									+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
											+ rsIdOB.getString("NO_KP") : "");
						}

						if (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "") {
							if (rsIdOB.getString("WARGANEGARA").toString().equals("MALAYSIA")) {
								temp = temp + " Warganegara: "
										+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "");
							}
							else if (rsIdOB.getString("WARGANEGARA").toString().equals("PEMASTAUTIN TETAP")) {
								temp = temp + " Warganegara: "
										+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "");
							}
							else {
								temp = temp + ", BUKAN WARGANEGARA";
							}
						}

						temp3 = rsIdOB.getString("NAMA_OB")
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "") + " ("
								+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "") + ")";
					} else {
						temp = "\n" + rsIdOB.getString("NAMA_OB");
						if (rsIdOB.getString("NO_KP") != null) {
							temp = temp
									+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
											+ rsIdOB.getString("NO_KP") : "");
						}

						temp3 = rsIdOB.getString("NAMA_OB")
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "");
					}
				} else {
					if (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != " ") {
						temp = temp + "\n" + rsIdOB.getString("NAMA_OB");
						temp = temp
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "");

						if (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "") {
							if (rsIdOB.getString("WARGANEGARA").toString().equals("MALAYSIA")) {
								temp = temp + " Warganegara: "
										+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "");
							} 
							else if (rsIdOB.getString("WARGANEGARA").toString().equals("PEMASTAUTIN TETAP")) {
								temp = temp + " Warganegara: "
										+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "");
							}
							else {
								temp = temp + ", BUKAN WARGANEGARA";
							}
						}

						// temp = temp + " Warganegara: " +
						// (rsIdOB.getString("WARGANEGARA")!=null &&
						// rsIdOB.getString("WARGANEGARA")!=""?rsIdOB.getString("WARGANEGARA"):"");
						temp3 = temp3
								+ ", "
								+ rsIdOB.getString("NAMA_OB")
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "") + " ("
								+ (rsIdOB.getString("WARGANEGARA") != null && rsIdOB.getString("WARGANEGARA") != "" ? rsIdOB.getString("WARGANEGARA") : "") + ")";
					} else {
						temp = temp
								+ "\n"
								+ rsIdOB.getString("NAMA_OB")
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "");
						temp3 = temp3
								+ ", "
								+ rsIdOB.getString("NAMA_OB")
								+ (rsIdOB.getString("NO_KP") != null ? (rsIdOB.getString("JENIS_KP") != null ? "" + rsIdOB.getString("JENIS_KP") : "")
										+ rsIdOB.getString("NO_KP") : "");
					}
				}
			}

			String sqlAlamat = "";

			
				if (list.size() != 0) {
	
					Hashtable h2 = (Hashtable) list.get(0);
	
					sqlAlamat = "SELECT "
							+ " CASE"
							+ " WHEN A.ALAMAT1_SURAT IS NULL THEN 'TIADA'"
							+ " WHEN A.ALAMAT2_SURAT IS NULL AND A.ALAMAT3_SURAT IS NULL THEN  REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') || ', ' || REPLACE(UPPER(A.POSKOD_SURAT),',') || ' ' || REPLACE(UPPER(B.KETERANGAN),',') ||', '|| REPLACE(UPPER(C.NAMA_NEGERI),',')"
							+ " WHEN A.ALAMAT2_SURAT IS NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT3_SURAT),','),'&','&#38;') || ', ' || REPLACE(UPPER(A.POSKOD_SURAT),',') || ' ' ||REPLACE(UPPER(B.KETERANGAN),',') ||', '|| REPLACE(UPPER(C.NAMA_NEGERI),',')"
							+ " WHEN A.ALAMAT3_SURAT IS NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT2_SURAT),','),'&','&#38;') || ', ' || UPPER(A.POSKOD_SURAT) || ' ' ||UPPER(B.KETERANGAN) ||', '|| UPPER(C.NAMA_NEGERI)"
							+ " WHEN A.ALAMAT3_SURAT IS NOT NULL THEN REPLACE(REPLACE(UPPER(A.ALAMAT1_SURAT),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(A.ALAMAT2_SURAT),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(A.ALAMAT3_SURAT),','),'&','&#38;') || ', ' || UPPER(A.POSKOD_SURAT) || ' ' ||UPPER(B.KETERANGAN) ||', '|| UPPER(C.NAMA_NEGERI)"
							+ " ELSE 'TIADA'" + " END AS ALAMAT_PENUH_OB" + " FROM TBLPPKOB A," + " TBLRUJBANDAR B, TBLRUJNEGERI C"
							+ " WHERE B.ID_BANDAR(+) = A.ID_BANDARSURAT" + " AND C.ID_NEGERI = A.ID_NEGERISURAT" + " AND A.ID_OB = " + h2.get("ob");
	
					ResultSet rsAlamat = stmt.executeQuery(sqlAlamat);
					System.out.println("sqlAlamat:" + sqlAlamat);
					Hashtable h3;
					String temp2 = "";
					String temp4 = "";
					Vector listAlamat = new Vector();
					while (rsAlamat.next()) {
						h3 = new Hashtable();
						if (countOB > 1) {
							h3 = new Hashtable();
							temp2 = temp + "\nadalah Sebagai Pentadbir bersama";
							if(flagJanaPerintah.equals(""))
							{
								temp2 += "\n\n" + "yang beralamat " + rsAlamat.getString("ALAMAT_PENUH_OB");
							}
							h3.put("maklumatPentadbir", temp2);
						} else {
							h3 = new Hashtable();
							temp2 = temp + "\nadalah Sebagai Pentadbir";
							if(flagJanaPerintah.equals(""))
							{
								temp2 += "\n\n" + "yang beralamat " + rsAlamat.getString("ALAMAT_PENUH_OB");
							}
							h3.put("maklumatPentadbir", temp2);
	
						}
						
						temp4 = temp3; 
						if(flagJanaPerintah.equals(""))
						{
							temp4 += " yang beralamat " + rsAlamat.getString("ALAMAT_PENUH_OB");
						}
						h3.put("maklumatPentadbirUtkBorangE", temp4);
						beanSenaraiOBPentadbir.addElement(h3);
					}
				}
			

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}

	}
	
	public String setMaklumatPentadbirString(String idfail) throws Exception {
		Db db = null;
	//beanMaklumatPentadbir.clear();
		String sql = "";
		String pentadbir = "";
		try {
			db = new Db();

			sql = "SELECT DISTINCT L.NAMA_OB,"
					+ " CASE"
					+ " WHEN LENGTH(L.NO_KP_BARU)<12 THEN  ''||RTRIM(L.NO_KP_BARU)||''"
					+ " WHEN LENGTH(RTRIM(L.NO_KP_BARU))=12 THEN SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)"
					+ " ELSE SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)  ||' ('||SUBSTR(L.NO_KP_BARU,12,LENGTH(L.NO_KP_BARU))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN L.JENIS_WARGA IS NULL THEN ''"
					+ " WHEN L.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN L.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN L.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " END AS WARGANEGARA,"
					+ " CASE"
					+ " WHEN L.ALAMAT_1 IS NULL THEN 'TIADA'"
					+ " WHEN L.ALAMAT_2 IS NULL AND L.ALAMAT_3 IS NULL THEN  REPLACE(UPPER(L.ALAMAT_1),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' || REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_2 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' ||REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_3 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " WHEN L.ALAMAT_3 IS NOT NULL THEN REPLACE(UPPER(L.ALAMAT_1),',')||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " ELSE 'TIADA'"
					+ " END AS ALAMAT_PENUH_OB"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHTAOBMST H,"
					+ " TBLPPKPERINTAHHTAOBDTL I,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K,"
					+ " TBLPPKOB L,"
					+ " TBLRUJNEGERI M,"
					+ " TBLRUJBANDAR N"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND (H.ID_JENISPERINTAH IN (2) OR J.ID_JENISPERINTAH IN (2))"
					+ " AND (L.ID_OB = I.ID_OB OR L.ID_OB = K.ID_OB)"
					+ " AND (L.ID_OB IN (I.ID_PA1,I.ID_PA2,I.ID_PA3,I.ID_PA4) OR L.ID_OB IN (K.ID_PA1,K.ID_PA2,K.ID_PA3,K.ID_PA4))"
					+ " AND L.ID_NEGERI = M.ID_NEGERI"
					+ " AND L.ID_BANDAR = N.ID_BANDAR"
					+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'"
					+ " UNION "
					+ " SELECT DISTINCT L.NAMA_OB,"
					+ " CASE"
					+ " WHEN LENGTH(L.NO_KP_BARU)<12 THEN  ''||RTRIM(L.NO_KP_BARU)||''"
					+ " WHEN LENGTH(RTRIM(L.NO_KP_BARU))=12 THEN SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)"
					+ " ELSE SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)  ||' ('||SUBSTR(L.NO_KP_BARU,12,LENGTH(L.NO_KP_BARU))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN L.JENIS_WARGA IS NULL THEN ''"
					+ " WHEN L.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN L.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN L.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " END AS WARGANEGARA,"
					+ " CASE"
					+ " WHEN L.ALAMAT_1 IS NULL THEN 'TIADA'"
					+ " WHEN L.ALAMAT_2 IS NULL AND L.ALAMAT_3 IS NULL THEN  REPLACE(UPPER(L.ALAMAT_1),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' || REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_2 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' ||REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_3 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " WHEN L.ALAMAT_3 IS NOT NULL THEN REPLACE(UPPER(L.ALAMAT_1),',')||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " ELSE 'TIADA'"
					+ " END AS ALAMAT_PENUH_OB"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHTAOBMST H,"
					+ " TBLPPKPERINTAHHTAOBDTL I,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K,"
					+ " TBLPPKOB L,"
					+ " TBLRUJNEGERI M,"
					+ " TBLRUJBANDAR N"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND (H.ID_JENISPERINTAH IN (1,7) OR J.ID_JENISPERINTAH IN (1,7))"
					+ " AND (I.STATUS_TADBIR LIKE 'Y' OR K.STATUS_TADBIR LIKE 'Y')"
					+ " AND (L.ID_OB = I.ID_OB OR L.ID_OB = K.ID_OB)"
					+ " AND (L.ID_OB IN (I.ID_PA1,I.ID_PA2,I.ID_PA3,I.ID_PA4) OR L.ID_OB IN (K.ID_PA1,K.ID_PA2,K.ID_PA3,K.ID_PA4))"
					+ " AND L.ID_NEGERI = M.ID_NEGERI"
					+ " AND L.ID_BANDAR = N.ID_BANDAR"
					+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'" +
					" UNION " +
					" SELECT DISTINCT D.NAMA_OB," +
					" CASE" +
					" WHEN AAA.NO_KP1 IS NULL THEN ''" +
					" WHEN LENGTH(AAA.NO_KP1)<12 THEN  ''||RTRIM(AAA.NO_KP1)||''" +
					" WHEN LENGTH(RTRIM(AAA.NO_KP1))=12 THEN SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)" +
					" WHEN AAA.NO_KP1 IS NULL THEN '('||D.NO_SURAT_BERANAK||')'" +
					"  ELSE SUBSTR(AAA.NO_KP1,1,6) || '-' || SUBSTR(AAA.NO_KP1,7,2) || '-' || SUBSTR(AAA.NO_KP1,9,4)  ||' ('||SUBSTR(AAA.NO_KP1,13,LENGTH(AAA.NO_KP1))||')'" +
					" END  AS NO_KP," +
					" CASE" +
					" WHEN D.JENIS_WARGA = 1 THEN 'MALAYSIA'" +
					" WHEN D.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'" +
				    " WHEN D.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'" +
					"  ELSE ''" +
					" END AS WARGANEGARA,  " +
					" CASE" +
					" WHEN D.ALAMAT_1 IS NULL THEN 'TIADA'" +
					" WHEN D.ALAMAT_2 IS NULL AND D.ALAMAT_3 IS NULL THEN  REPLACE(UPPER(D.ALAMAT_1),',') || ', ' || REPLACE(UPPER(D.POSKOD),',') || ' ' || REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')" +
					" WHEN D.ALAMAT_2 IS NULL THEN REPLACE(UPPER(D.ALAMAT_1),',') ||', ' || REPLACE(UPPER(D.ALAMAT_3),',') || ', ' || REPLACE(UPPER(D.POSKOD),',') || ' ' ||REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')" +
					" WHEN D.ALAMAT_3 IS NULL THEN REPLACE(UPPER(D.ALAMAT_1),',') ||', ' || REPLACE(UPPER(D.ALAMAT_2),',') || ', ' || UPPER(D.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)" +
					" WHEN D.ALAMAT_3 IS NOT NULL THEN REPLACE(UPPER(D.ALAMAT_1),',')||', ' || REPLACE(UPPER(D.ALAMAT_2),',') || ', ' || REPLACE(UPPER(D.ALAMAT_3),',') || ', ' || UPPER(D.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)" +
					" ELSE 'TIADA'" +
					" END AS ALAMAT_PENUH_OB" +
					"  FROM" +
					" TBLPPKPERINTAHHTAOBMST A," +
					"  TBLPPKPERINTAH B," +
					" TBLPPKPERINTAHHTAOBDTL C," +
					" TBLPPKOB D," +
					" TBLPFDFAIL E," +
					" TBLPPKPERMOHONAN F," +
					" TBLPPKKEPUTUSANPERMOHONAN G," +
					" TBLPPKPERBICARAAN H," +
					" TBLPPKPERINTAH I," +
					" TBLRUJBANDAR J," +
					" TBLRUJNEGERI K," +
					" TBLRUJNEGERI M," +
					" TBLRUJBANDAR N," +
					"  (SELECT" +
					" CASE" +
					"  WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN  TBLPPKOB.NO_KP_LAMA" +
					" WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAMA IS NULL THEN  TBLPPKOB.NO_KP_LAIN" +
					"  WHEN TBLPPKOB.NO_KP_BARU IS NULL AND TBLPPKOB.NO_KP_LAIN IS NULL THEN  TBLPPKOB.NO_KP_LAMA" +
					"  ELSE TBLPPKOB.NO_KP_BARU" +
					"  END || '' ||" +
					"  CASE" +
					"  WHEN TBLPPKOB.NO_KP_BARU IS NOT NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAMA" +
					" END || '' ||    " +
					"  CASE" +
					"  WHEN TBLPPKOB.NO_KP_BARU IS  NULL AND TBLPPKOB.NO_KP_LAMA IS NOT NULL THEN TBLPPKOB.NO_KP_LAIN   " +
					" END AS NO_KP1, ID_OB" +
					"  FROM TBLPPKOB ) AAA" +
					"  WHERE" +
					"  B.ID_PERINTAH = A.ID_PERINTAH" +
					"  AND E.ID_FAIL = F.ID_FAIL" +
					"  AND F.ID_PERMOHONAN = G.ID_PERMOHONAN" +
					"  AND G.ID_KEPUTUSANPERMOHONAN = H.ID_KEPUTUSANPERMOHONAN" +
					" AND H.ID_PERBICARAAN = I.ID_PERBICARAAN" +
					"   AND I.ID_PERINTAH = A.ID_PERINTAH" +
					" AND A.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST" +
					"  AND D.ID_OB = C.ID_OB" +
					"  AND D.ID_OB = AAA.ID_OB" +
					"  AND D.ID_NEGERI = K.ID_NEGERI" +
					"  AND D.ID_BANDAR = J.ID_BANDAR(+)" +
					" AND A.ID_JENISPERINTAH = 2" +
					" AND A.FLAG_HARTA = 'B'" +
					"  AND E.ID_FAIL =  '" + idfail + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			System.out.println("sql======="+sql);
			while (rs.next()) {
				h = new Hashtable();/*
				h.put("maklumatPentadbir", rs.getString("NAMA_OB")
						+ " No KP:  " + rs.getString("NO_KP")
						+ " Warganegara: " + rs.getString("WARGANEGARA")
						+ " yang beralamat di "
						+ rs.getString("ALAMAT_PENUH_OB"));
				*/
				//System.out.println("NAMA_OB"+rs.getString("NAMA_OB"));
				pentadbir = "\n" +rs.getString("NAMA_OB")
				+ "\n" +" No KP:  " + rs.getString("NO_KP")
				+ "\n" +" Warganegara: " + rs.getString("WARGANEGARA")
				//+ " yang beralamat di "
				//+ rs.getString("ALAMAT_PENUH_OB")
				+ "";
				//beanMaklumatPentadbir.addElement(h);
				//System.out.println("pentadbir------"+pentadbir);
			}
			
			
			
		} finally {
			if (db != null)
				db.close();
		}
		return pentadbir;
	}
	
	
	public void setMaklumatPentadbir(String idfail) throws Exception {
		Db db = null;
		beanMaklumatPentadbir.clear();
		String sql = "";

		try {
			db = new Db();

			sql = "SELECT DISTINCT L.NAMA_OB,"
					+ " CASE"
					+ " WHEN LENGTH(L.NO_KP_BARU)<12 THEN  ''||RTRIM(L.NO_KP_BARU)||''"
					+ " WHEN LENGTH(RTRIM(L.NO_KP_BARU))=12 THEN SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)"
					+ " ELSE SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)  ||' ('||SUBSTR(L.NO_KP_BARU,12,LENGTH(L.NO_KP_BARU))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN L.JENIS_WARGA IS NULL THEN ''"
					+ " WHEN L.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN L.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN L.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " END AS WARGANEGARA,"
					+ " CASE"
					+ " WHEN L.ALAMAT_1 IS NULL THEN 'TIADA'"
					+ " WHEN L.ALAMAT_2 IS NULL AND L.ALAMAT_3 IS NULL THEN  REPLACE(UPPER(L.ALAMAT_1),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' || REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_2 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' ||REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_3 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " WHEN L.ALAMAT_3 IS NOT NULL THEN REPLACE(UPPER(L.ALAMAT_1),',')||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " ELSE 'TIADA'"
					+ " END AS ALAMAT_PENUH_OB"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHTAOBMST H,"
					+ " TBLPPKPERINTAHHTAOBDTL I,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K,"
					+ " TBLPPKOB L,"
					+ " TBLRUJNEGERI M,"
					+ " TBLRUJBANDAR N"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND (H.ID_JENISPERINTAH IN (2) OR J.ID_JENISPERINTAH IN (2))"
					+ " AND (L.ID_OB = I.ID_OB OR L.ID_OB = K.ID_OB)"
					+ " AND (L.ID_OB IN (I.ID_PA1,I.ID_PA2,I.ID_PA3,I.ID_PA4) OR L.ID_OB IN (K.ID_PA1,K.ID_PA2,K.ID_PA3,K.ID_PA4))"
					+ " AND L.ID_NEGERI = M.ID_NEGERI"
					+ " AND L.ID_BANDAR = N.ID_BANDAR"
					+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'"
					+ " UNION "
					+ " SELECT DISTINCT L.NAMA_OB,"
					+ " CASE"
					+ " WHEN LENGTH(L.NO_KP_BARU)<12 THEN  ''||RTRIM(L.NO_KP_BARU)||''"
					+ " WHEN LENGTH(RTRIM(L.NO_KP_BARU))=12 THEN SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)"
					+ " ELSE SUBSTR(L.NO_KP_BARU,1,6) || '-' || SUBSTR(L.NO_KP_BARU,7,2) || '-' || SUBSTR(L.NO_KP_BARU,9,4)  ||' ('||SUBSTR(L.NO_KP_BARU,12,LENGTH(L.NO_KP_BARU))||')'"
					+ " END  AS NO_KP,"
					+ " CASE "
					+ " WHEN L.JENIS_WARGA IS NULL THEN ''"
					+ " WHEN L.JENIS_WARGA = 1 THEN 'MALAYSIA'"
					+ " WHEN L.JENIS_WARGA = 0 THEN 'BUKAN WARGANEGARA'"
					+ " WHEN L.JENIS_WARGA = 3 THEN 'PEMASTAUTIN TETAP'"
					+ " END AS WARGANEGARA,"
					+ " CASE"
					+ " WHEN L.ALAMAT_1 IS NULL THEN 'TIADA'"
					+ " WHEN L.ALAMAT_2 IS NULL AND L.ALAMAT_3 IS NULL THEN  REPLACE(UPPER(L.ALAMAT_1),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' || REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_2 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || REPLACE(UPPER(L.POSKOD),',') || ' ' ||REPLACE(UPPER(N.KETERANGAN),',') ||', '|| REPLACE(UPPER(M.NAMA_NEGERI),',')"
					+ " WHEN L.ALAMAT_3 IS NULL THEN REPLACE(UPPER(L.ALAMAT_1),',') ||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " WHEN L.ALAMAT_3 IS NOT NULL THEN REPLACE(UPPER(L.ALAMAT_1),',')||', ' || REPLACE(UPPER(L.ALAMAT_2),',') || ', ' || REPLACE(UPPER(L.ALAMAT_3),',') || ', ' || UPPER(L.POSKOD) || ' ' ||UPPER(N.KETERANGAN) ||', '|| UPPER(M.NAMA_NEGERI)"
					+ " ELSE 'TIADA'"
					+ " END AS ALAMAT_PENUH_OB"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPPKPERMOHONAN B,"
					+ " TBLPPKPEMOHON C,"
					+ " TBLPPKPERMOHONANSIMATI D,"
					+ " TBLPPKPERBICARAAN E,"
					+ " TBLPPKKEPUTUSANPERMOHONAN F,"
					+ " TBLPPKPERINTAH G,"
					+ " TBLPPKPERINTAHHTAOBMST H,"
					+ " TBLPPKPERINTAHHTAOBDTL I,"
					+ " TBLPPKPERINTAHHAOBMST J,"
					+ " TBLPPKPERINTAHHAOBDTL K,"
					+ " TBLPPKOB L,"
					+ " TBLRUJNEGERI M,"
					+ " TBLRUJBANDAR N"
					+ " WHERE A.ID_FAIL = B.ID_FAIL"
					+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = F.ID_PERMOHONAN"
					+ " AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON"
					+ " AND G.ID_PERINTAH = H.ID_PERINTAH"
					+ " AND G.ID_PERINTAH = J.ID_PERINTAH"
					+ " AND H.ID_PERINTAHHTAOBMST = I.ID_PERINTAHHTAOBMST"
					+ " AND J.ID_PERINTAHHAOBMST = K.ID_PERINTAHHAOBMST"
					+ " AND (H.ID_JENISPERINTAH IN (1,7) OR J.ID_JENISPERINTAH IN (1,7))"
					+ " AND (I.STATUS_TADBIR LIKE 'Y' OR K.STATUS_TADBIR LIKE 'Y')"
					+ " AND (L.ID_OB = I.ID_OB OR L.ID_OB = K.ID_OB)"
					+ " AND (L.ID_OB IN (I.ID_PA1,I.ID_PA2,I.ID_PA3,I.ID_PA4) OR L.ID_OB IN (K.ID_PA1,K.ID_PA2,K.ID_PA3,K.ID_PA4))"
					+ " AND L.ID_NEGERI = M.ID_NEGERI"
					+ " AND L.ID_BANDAR = N.ID_BANDAR"
					+ " AND E.ID_PERBICARAAN IN (SELECT MAX(ID_PERBICARAAN) FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN)"
					+ " AND G.ID_PERBICARAAN = E.ID_PERBICARAAN"
					+ " AND A.ID_FAIL = '" + idfail + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("maklumatPentadbir", rs.getString("NAMA_OB")
						+ " No KP:  " + rs.getString("NO_KP")
						+ " Warganegara: " + rs.getString("WARGANEGARA")
						+ " yang beralamat di "
						+ rs.getString("ALAMAT_PENUH_OB"));
				
				System.out.println("NAMA_OB"+rs.getString("NAMA_OB"));

				beanMaklumatPentadbir.addElement(h);

			}
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

			sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";
			System.out.println("getNoFailByIdFail"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String str1;
				return rs.getString("NO_FAIL").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNoFailS17ByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT REPLACE(SUBSTR(NO_FAIL,1,INSTR(NO_FAIL,'/S17-')-1),'&','&#38;') AS NO_FAIL "+
				  " FROM TBLPFDFAIL WHERE ID_FAIL = '" + idFail
					+ "'";
			System.out.println("getNoFailS17ByIdFail"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String str1;
				return rs.getString("NO_FAIL").toString();
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdUnitPSKByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_UNITPSK FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKPERINTAH C, TBLPPKPERBICARAAN D, TBLPPKKEPUTUSANPERMOHONAN E "
					+ "WHERE C.FLAG_JENIS_KEPUTUSAN = '0' AND A.ID_FAIL = B.ID_FAIL AND D.ID_PERBICARAAN = C.ID_PERBICARAAN AND E.ID_KEPUTUSANPERMOHONAN = D.ID_KEPUTUSANPERMOHONAN "
					+ "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";
			myLogger.info("getIdUnitPSKByIdFail :"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String str1;
				return rs.getString("ID_UNITPSK").toString();
			}
			return "";
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getSebab(String idFail) throws Exception {
		Db db = null;
		String sebab = "";
		String sebabHartaTertinggal = "";
		String sebabBatalPT = "";
		String sebabBatalPA = "";
		String sebabLainTujuan = "";
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.HARTA_TINGGAL, A.BATAL_KUASA_PENTADBIR, A.LANTIK_PENTADBIR, A.BATAL_P_AMANAH, A.LANTIK_P_AMANAH, B.ID_PERMOHONANSIMATI, A.SEKSYEN,A.LAIN_TUJUAN "
					+ " FROM TBLPPKPERMOHONAN A, TBLPPKPERMOHONANSIMATI B"
					+ " WHERE A.SEKSYEN = 17 AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			System.out.println("SELECT GET SEBAB :::::::::::::::"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String idPermohonanSimati = rs.getString("ID_PERMOHONANSIMATI");
				String hartaTertinggal = rs.getString("HARTA_TINGGAL");
				String batalPT = rs.getString("BATAL_KUASA_PENTADBIR");
				String lantikPT = rs.getString("LANTIK_PENTADBIR");
				String batalPA = rs.getString("BATAL_P_AMANAH");
				String lantikPA = rs.getString("LANTIK_P_AMANAH");
				String lain_tujuan = rs.getString("LAIN_TUJUAN");
				 
				myLogger.info("hartaTertinggal :"+hartaTertinggal);
				myLogger.info("batalPT :"+batalPT);
				myLogger.info("lantikPT :"+lantikPT);
				myLogger.info("batalPA :"+batalPA);
				myLogger.info("lantikPA :"+lantikPA);
				myLogger.info("lain_tujuan :"+lain_tujuan);
			
				
				
				//if (hartaTertinggal.equals("Y")){
				if ("Y".equals(hartaTertinggal)){
					
					//KETINGGALAN HARTA TAK ALIH ADA HAKMILIK
					sql = "SELECT B.KOD_JENIS_HAKMILIK, REPLACE(A.NO_HAKMILIK,'&','&#38;') AS NO_HAKMILIK, REPLACE(A.NO_PT,'&','&#38;') AS NO_PT, C.NAMA_MUKIM,  REPLACE(D.NAMA_DAERAH,'&','&#38;') AS NAMA_DAERAH, E.NAMA_NEGERI"
							+ " FROM TBLPPKHTA A, TBLRUJJENISHAKMILIK B, TBLRUJMUKIM C, TBLRUJDAERAH D, TBLRUJNEGERI E"
							+ " WHERE A.ID_JENISHM = B.ID_JENISHAKMILIK AND A.ID_MUKIM = C.ID_MUKIM AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_NEGERI = E.ID_NEGERI"
							+ " AND A.JENIS_HTA = 'Y' AND A.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
					
					ResultSet rsTinggalHTAAH = stmt.executeQuery(sql);
					
					while (rsTinggalHTAAH.next()){
						if (sebabHartaTertinggal.isEmpty()){
							sebabHartaTertinggal = " HARTA KETINGGALAN " + rsTinggalHTAAH.getString("KOD_JENIS_HAKMILIK") + rsTinggalHTAAH.getString("NO_HAKMILIK") + ", " + rsTinggalHTAAH.getString("NO_PT")
									+ ", " + rsTinggalHTAAH.getString("NAMA_MUKIM") + ", " + rsTinggalHTAAH.getString("NAMA_DAERAH") + ", " + rsTinggalHTAAH.getString("NAMA_NEGERI");
						} else {
							sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + rsTinggalHTAAH.getString("KOD_JENIS_HAKMILIK") + rsTinggalHTAAH.getString("NO_HAKMILIK") + ", " + rsTinggalHTAAH.getString("NO_PT")
							+ ", " + rsTinggalHTAAH.getString("NAMA_MUKIM") + ", " + rsTinggalHTAAH.getString("NAMA_DAERAH") + ", " + rsTinggalHTAAH.getString("NAMA_NEGERI");
						}
					}

					//KETINGGALAN HARTA TAK ALIH TIADA HAKMILIK
					sql = "SELECT A.JENIS_HTA, A.FLAG_KATEGORI_HTA, A.NO_PERJANJIAN, A.TARIKH_PERJANJIAN, A.NAMA_PEMAJU, A.ALAMAT_HTA1, A.ALAMAT_HTA2, A.ALAMAT_HTA3, A.POSKOD_HTA, B.KETERANGAN AS BANDAR_HTA, C.NAMA_NEGERI, A.NAMA_RANCANGAN, A.NO_ROH, A.NO_LOT_ID, A.JENIS_KEPENTINGAN"
							+ " FROM TBLPPKHTA A, TBLRUJBANDAR B, TBLRUJNEGERI C"
							+ " WHERE A.ID_BANDARHTA = B.ID_BANDAR(+) AND B.ID_NEGERI = C.ID_NEGERI(+)"
							+ " AND A.JENIS_HTA = 'T' AND A.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
					ResultSet rsTinggalHTATH = stmt.executeQuery(sql);
					
					while (rsTinggalHTATH.next()){
						//PERJANJIAN JUAL BELI
						if (rsTinggalHTATH.getString("FLAG_KATEGORI_HTA").equals("1")){
							if (sebabHartaTertinggal.isEmpty()){
								sebabHartaTertinggal = " HARTA KETINGGALAN " + (rsTinggalHTATH.getString("ALAMAT_HTA1") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA1")) + ", " + (rsTinggalHTATH.getString("ALAMAT_HTA2") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA2"))
										+ ", " + (rsTinggalHTATH.getString("ALAMAT_HTA3") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA3")) + ", " + (rsTinggalHTATH.getString("POSKOD_HTA") == null ? "" : rsTinggalHTATH.getString("POSKOD_HTA"))
										+ " " + (rsTinggalHTATH.getString("BANDAR_HTA") == null ? "" : rsTinggalHTATH.getString("BANDAR_HTA")) + ", " + (rsTinggalHTATH.getString("NAMA_NEGERI") == null ? "" : rsTinggalHTATH.getString("NAMA_NEGERI"));
							} else {
								sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + (rsTinggalHTATH.getString("ALAMAT_HTA1") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA1")) + ", " + (rsTinggalHTATH.getString("ALAMAT_HTA2") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA2"))
								+ ", " + (rsTinggalHTATH.getString("ALAMAT_HTA3") == null ? "" : rsTinggalHTATH.getString("ALAMAT_HTA3")) + ", " + (rsTinggalHTATH.getString("POSKOD_HTA") == null ? "" : rsTinggalHTATH.getString("POSKOD_HTA"))
								+ " " + (rsTinggalHTATH.getString("BANDAR_HTA") == null ? "" : rsTinggalHTATH.getString("BANDAR_HTA")) + ", " + (rsTinggalHTATH.getString("NAMA_NEGERI") == null ? "" : rsTinggalHTATH.getString("NAMA_NEGERI"));
							}
						} else 
							//PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)
							if (rsTinggalHTATH.getString("FLAG_KATEGORI_HTA").equals("2")){
								if (sebabHartaTertinggal.isEmpty()){
									sebabHartaTertinggal = " HARTA KETINGGALAN " + (rsTinggalHTATH.getString("NO_ROH") == null ? "" : rsTinggalHTATH.getString("NO_ROH")) + ", " + (rsTinggalHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsTinggalHTATH.getString("NAMA_RANCANGAN"));
								} else {
									sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + (rsTinggalHTATH.getString("NO_ROH") == null ? "" : rsTinggalHTATH.getString("NO_ROH")) + ", " + (rsTinggalHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsTinggalHTATH.getString("NAMA_RANCANGAN"));
								}
							} else 
								//KEPENTINGAN LAIN- LAIN
								if (rsTinggalHTATH.getString("FLAG_KATEGORI_HTA").equals("3")){
									if (sebabHartaTertinggal.isEmpty()){
										sebabHartaTertinggal = " HARTA KETINGGALAN " + (rsTinggalHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsTinggalHTATH.getString("JENIS_KEPENTINGAN"));
									} else {
										sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + (rsTinggalHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsTinggalHTATH.getString("JENIS_KEPENTINGAN"));
									}
								}
								
					}
					
					//KETINGGALAN HARTA ALIH
					sql = "SELECT A.ID_JENISHA,B.KETERANGAN, REPLACE(A.JENAMA,'&','&#38;') AS JENAMA,REPLACE(A.BUTIRAN,'&','&#38;') AS BUTIRAN, REPLACE(A.NO_DAFTAR,'&','&#38;') AS NO_DAFTAR"
							+ " FROM TBLPPKHA A, TBLPPKRUJJENISHA B"
							+ " WHERE B.ID_JENISHA = A.ID_JENISHA"
							+ " AND A.ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "'";
					
					ResultSet rsTinggalHA = stmt.executeQuery(sql);
					
					while (rsTinggalHA.next()){
						if (sebabHartaTertinggal.isEmpty()){
							
							if(rsTinggalHA.getString("ID_JENISHA").equals("12")){
								sebabHartaTertinggal = " HARTA KETINGGALAN " + (rsTinggalHA.getString("BUTIRAN") == null ? "" : rsTinggalHA.getString("BUTIRAN"));	
							}
							else{
							sebabHartaTertinggal = " HARTA KETINGGALAN " + (rsTinggalHA.getString("KETERANGAN") == null ? "" : rsTinggalHA.getString("KETERANGAN")) + " - " + (rsTinggalHA.getString("JENAMA") == null ? "" : rsTinggalHA.getString("JENAMA")) + ", " + (rsTinggalHA.getString("NO_DAFTAR") == null ? "" : rsTinggalHA.getString("NO_DAFTAR"));
							}
						} else {
							if(rsTinggalHA.getString("ID_JENISHA").equals("12")){
								sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + (rsTinggalHA.getString("BUTIRAN") == null ? "" : rsTinggalHA.getString("BUTIRAN"));	
							}
							else{
							sebabHartaTertinggal = sebabHartaTertinggal + " DAN " + (rsTinggalHA.getString("KETERANGAN") == null ? "" : rsTinggalHA.getString("KETERANGAN")) + " - " + (rsTinggalHA.getString("JENAMA") == null ? "" : rsTinggalHA.getString("JENAMA")) + ", " + (rsTinggalHA.getString("NO_DAFTAR") == null ? "" : rsTinggalHA.getString("NO_DAFTAR"));
							}
						}
					}
				}
				
				if ("Y".equals(batalPT) || "Y".equals(lantikPT)){
					
					//BATAL PT BAGI HARTA TAK ALIH ADA HAKMILIK
					sql = "SELECT B.KOD_JENIS_HAKMILIK, REPLACE(A.NO_HAKMILIK,'&','&#38;') AS NO_HAKMILIK, REPLACE(A.NO_PT,'&','&#38;') AS NO_PT, C.NAMA_MUKIM, REPLACE(D.NAMA_DAERAH,'&','&#38;') AS NAMA_DAERAH, E.NAMA_NEGERI"
							+ " FROM TBLPPKHTA A, TBLRUJJENISHAKMILIK B, TBLRUJMUKIM C, TBLRUJDAERAH D, TBLRUJNEGERI E"
							+ " WHERE A.ID_JENISHM = B.ID_JENISHAKMILIK AND A.ID_MUKIM = C.ID_MUKIM AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_NEGERI = E.ID_NEGERI"
							+ " AND A.JENIS_HTA = 'Y' AND A.FLAG_PT = 'Y' AND A.ID_HTA IN (SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					
					myLogger.info("sql1 :"+sql);
					ResultSet rsBatalPTHTAAH = stmt.executeQuery(sql);
					
					while (rsBatalPTHTAAH.next()){
						if (sebabBatalPT.isEmpty()){
							sebabBatalPT = " MEMBATALKAN PENTADBIR " + rsBatalPTHTAAH.getString("KOD_JENIS_HAKMILIK") + rsBatalPTHTAAH.getString("NO_HAKMILIK") + ", " + rsBatalPTHTAAH.getString("NO_PT")
									+ ", " + rsBatalPTHTAAH.getString("NAMA_MUKIM") + ", " + rsBatalPTHTAAH.getString("NAMA_DAERAH") + ", " + rsBatalPTHTAAH.getString("NAMA_NEGERI");
						} else {
							sebabBatalPT = sebabBatalPT + " DAN " + rsBatalPTHTAAH.getString("KOD_JENIS_HAKMILIK") + rsBatalPTHTAAH.getString("NO_HAKMILIK") + ", " + rsBatalPTHTAAH.getString("NO_PT")
							+ ", " + rsBatalPTHTAAH.getString("NAMA_MUKIM") + ", " + rsBatalPTHTAAH.getString("NAMA_DAERAH") + ", " + rsBatalPTHTAAH.getString("NAMA_NEGERI");
						}
					}
					
					//BATAL PT BAGI HARTA TAK ALIH TIADA HAKMILIK
					sql = "SELECT A.JENIS_HTA, A.FLAG_KATEGORI_HTA, A.NO_PERJANJIAN, A.TARIKH_PERJANJIAN, A.NAMA_PEMAJU, REPLACE(A.ALAMAT_HTA1,'&','&#38;') AS ALAMAT_HTA1, REPLACE(A.ALAMAT_HTA2,'&','&#38;') AS ALAMAT_HTA2,REPLACE(A.ALAMAT_HTA3,'&','&#38;') AS ALAMAT_HTA3, A.POSKOD_HTA, B.KETERANGAN AS BANDAR_HTA, C.NAMA_NEGERI, A.NAMA_RANCANGAN, A.NO_ROH, A.NO_LOT_ID, A.JENIS_KEPENTINGAN"
							+ " FROM TBLPPKHTA A, TBLRUJBANDAR B, TBLRUJNEGERI C"
							+ " WHERE A.ID_BANDARHTA = B.ID_BANDAR(+) AND B.ID_NEGERI = C.ID_NEGERI(+)"
							+ " AND A.JENIS_HTA = 'T' AND A.FLAG_PT = 'Y' AND A.ID_HTA IN (SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					myLogger.info("sql2 :"+sql);
					ResultSet rsBatalPTHTATH = stmt.executeQuery(sql);
					
					while (rsBatalPTHTATH.next()){
						//PERJANJIAN JUAL BELI
						if (rsBatalPTHTATH.getString("FLAG_KATEGORI_HTA").equals("1")){
							if (sebabBatalPT.isEmpty()){
								sebabBatalPT = " MEMBATALKAN PENTADBIR " + (rsBatalPTHTATH.getString("ALAMAT_HTA1") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA1")) + ", " + (rsBatalPTHTATH.getString("ALAMAT_HTA2") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA2"))
										+ ", " + (rsBatalPTHTATH.getString("ALAMAT_HTA3") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA3")) + ", " + (rsBatalPTHTATH.getString("POSKOD_HTA") == null ? "" : rsBatalPTHTATH.getString("POSKOD_HTA"))
										+ " " + (rsBatalPTHTATH.getString("BANDAR_HTA") == null ? "" : rsBatalPTHTATH.getString("BANDAR_HTA")) + ", " + (rsBatalPTHTATH.getString("NAMA_NEGERI") == null ? "" : rsBatalPTHTATH.getString("NAMA_NEGERI"));
							} else {
								sebabBatalPT = sebabBatalPT + " DAN " + (rsBatalPTHTATH.getString("ALAMAT_HTA1") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA1")) + ", " + (rsBatalPTHTATH.getString("ALAMAT_HTA2") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA2"))
								+ ", " + (rsBatalPTHTATH.getString("ALAMAT_HTA3") == null ? "" : rsBatalPTHTATH.getString("ALAMAT_HTA3")) + ", " + (rsBatalPTHTATH.getString("POSKOD_HTA") == null ? "" : rsBatalPTHTATH.getString("POSKOD_HTA"))
								+ " " + (rsBatalPTHTATH.getString("BANDAR_HTA") == null ? "" : rsBatalPTHTATH.getString("BANDAR_HTA")) + ", " + (rsBatalPTHTATH.getString("NAMA_NEGERI") == null ? "" : rsBatalPTHTATH.getString("NAMA_NEGERI"));
							}
						} else 
							//PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)
							if (rsBatalPTHTATH.getString("FLAG_KATEGORI_HTA").equals("2")){
								if (sebabBatalPT.isEmpty()){
									sebabBatalPT = " MEMBATALKAN PENTADBIR " + (rsBatalPTHTATH.getString("NO_ROH") == null ? "" : rsBatalPTHTATH.getString("NO_ROH")) + ", " + (rsBatalPTHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsBatalPTHTATH.getString("NAMA_RANCANGAN"));
								} else {
									sebabBatalPT = sebabBatalPT + " DAN " + (rsBatalPTHTATH.getString("NO_ROH") == null ? "" : rsBatalPTHTATH.getString("NO_ROH")) + ", " + (rsBatalPTHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsBatalPTHTATH.getString("NAMA_RANCANGAN"));
								}
							} else 
								//KEPENTINGAN LAIN- LAIN
								if (rsBatalPTHTATH.getString("FLAG_KATEGORI_HTA").equals("3")){
									if (sebabBatalPT.isEmpty()){
										sebabBatalPT = " MEMBATALKAN PENTADBIR " + (rsBatalPTHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsBatalPTHTATH.getString("JENIS_KEPENTINGAN"));
									} else {
										sebabBatalPT = sebabBatalPT + " DAN " + (rsBatalPTHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsBatalPTHTATH.getString("JENIS_KEPENTINGAN"));
									}
								}
								
					}
					
					//BATAL PT HARTA ALIH
					sql = "SELECT B.KETERANGAN, REPLACE(A.JENAMA,'&','&#38;') AS JENAMA, REPLACE(A.NO_DAFTAR,'&','&#38;') AS NO_DAFTAR"
							+ " FROM TBLPPKHA A, TBLPPKRUJJENISHA B"
							+ " WHERE B.ID_JENISHA = A.ID_JENISHA"
							+ " AND A.FLAG_PT = 'Y' AND A.ID_HA IN (SELECT ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					myLogger.info("sql3 :"+sql);
					ResultSet rsBatalPTHA = stmt.executeQuery(sql);
					
					while (rsBatalPTHA.next()){
						if (sebabBatalPT.isEmpty()){
							sebabBatalPT = " MEMBATALKAN PENTADBIR " + (rsBatalPTHA.getString("KETERANGAN") == null ? "" : rsBatalPTHA.getString("KETERANGAN")) + " - " + (rsBatalPTHA.getString("JENAMA") == null ? "" : rsBatalPTHA.getString("JENAMA")) + ", " + (rsBatalPTHA.getString("NO_DAFTAR") == null ? "" : rsBatalPTHA.getString("NO_DAFTAR"));
						} else {
							sebabBatalPT = sebabBatalPT + " DAN " + (rsBatalPTHA.getString("KETERANGAN") == null ? "" : rsBatalPTHA.getString("KETERANGAN")) + " - " + (rsBatalPTHA.getString("JENAMA") == null ? "" : rsBatalPTHA.getString("JENAMA")) + ", " + (rsBatalPTHA.getString("NO_DAFTAR") == null ? "" : rsBatalPTHA.getString("NO_DAFTAR"));
						}
					}
				}
				
				if ("Y".equals(batalPA) || "Y".equals(lantikPA)){
					
					//BATAL PA BAGI HARTA TAK ALIH ADA HAKMILIK
					sql = "SELECT B.KOD_JENIS_HAKMILIK, REPLACE(A.NO_HAKMILIK,'&','&#38;') AS NO_HAKMILIK, REPLACE(A.NO_PT,'&','&#38;') AS NO_PT, C.NAMA_MUKIM, REPLACE(D.NAMA_DAERAH,'&','&#38;') AS NAMA_DAERAH, E.NAMA_NEGERI"
							+ " FROM TBLPPKHTA A, TBLRUJJENISHAKMILIK B, TBLRUJMUKIM C, TBLRUJDAERAH D, TBLRUJNEGERI E"
							+ " WHERE A.ID_JENISHM = B.ID_JENISHAKMILIK AND A.ID_MUKIM = C.ID_MUKIM AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_NEGERI = E.ID_NEGERI"
							+ " AND A.JENIS_HTA = 'Y' AND A.FLAG_PA = 'Y' AND A.ID_HTA IN (SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					
					ResultSet rsBatalPAHTAAH = stmt.executeQuery(sql);
					
					while (rsBatalPAHTAAH.next()){
						if (sebabBatalPA.isEmpty()){
							sebabBatalPA = " MEMBATALKAN PEMEGANG AMANAH " + rsBatalPAHTAAH.getString("KOD_JENIS_HAKMILIK") + rsBatalPAHTAAH.getString("NO_HAKMILIK") + ", " + rsBatalPAHTAAH.getString("NO_PT")
									+ ", " + rsBatalPAHTAAH.getString("NAMA_MUKIM") + ", " + rsBatalPAHTAAH.getString("NAMA_DAERAH") + ", " + rsBatalPAHTAAH.getString("NAMA_NEGERI");
						} else {
							sebabBatalPA = sebabBatalPA + " DAN " + rsBatalPAHTAAH.getString("KOD_JENIS_HAKMILIK") + rsBatalPAHTAAH.getString("NO_HAKMILIK") + ", " + rsBatalPAHTAAH.getString("NO_PT")
							+ ", " + rsBatalPAHTAAH.getString("NAMA_MUKIM") + ", " + rsBatalPAHTAAH.getString("NAMA_DAERAH") + ", " + rsBatalPAHTAAH.getString("NAMA_NEGERI");
						}
					}
					
					//BATAL PA BAGI HARTA TAK ALIH TIADA HAKMILIK
					sql = "SELECT A.JENIS_HTA, A.FLAG_KATEGORI_HTA, A.NO_PERJANJIAN, A.TARIKH_PERJANJIAN, A.NAMA_PEMAJU, A.ALAMAT_HTA1, A.ALAMAT_HTA2, A.ALAMAT_HTA3, A.POSKOD_HTA, B.KETERANGAN AS BANDAR_HTA, C.NAMA_NEGERI, A.NAMA_RANCANGAN, A.NO_ROH, A.NO_LOT_ID, A.JENIS_KEPENTINGAN"
							+ " FROM TBLPPKHTA A, TBLRUJBANDAR B, TBLRUJNEGERI C"
							+ " WHERE A.ID_BANDARHTA = B.ID_BANDAR(+) AND B.ID_NEGERI = C.ID_NEGERI(+)"
							+ " AND A.JENIS_HTA = 'T' AND A.FLAG_PA = 'Y' AND A.ID_HTA IN (SELECT ID_HTA FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					ResultSet rsBatalPAHTATH = stmt.executeQuery(sql);
					
					while (rsBatalPAHTATH.next()){
						//PERJANJIAN JUAL BELI
						if (rsBatalPAHTATH.getString("FLAG_KATEGORI_HTA").equals("1")){
							if (sebabBatalPA.isEmpty()){
								sebabBatalPA = " MEMBATALKAN PEMEGANG AMANAH " + (rsBatalPAHTATH.getString("ALAMAT_HTA1") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA1")) + ", " + (rsBatalPAHTATH.getString("ALAMAT_HTA2") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA2"))
										+ ", " + (rsBatalPAHTATH.getString("ALAMAT_HTA3") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA3")) + ", " + (rsBatalPAHTATH.getString("POSKOD_HTA") == null ? "" : rsBatalPAHTATH.getString("POSKOD_HTA"))
										+ " " + (rsBatalPAHTATH.getString("BANDAR_HTA") == null ? "" : rsBatalPAHTATH.getString("BANDAR_HTA")) + ", " + (rsBatalPAHTATH.getString("NAMA_NEGERI") == null ? "" : rsBatalPAHTATH.getString("NAMA_NEGERI"));
							} else {
								sebabBatalPA = sebabBatalPA + " DAN " + (rsBatalPAHTATH.getString("ALAMAT_HTA1") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA1")) + ", " + (rsBatalPAHTATH.getString("ALAMAT_HTA2") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA2"))
								+ ", " + (rsBatalPAHTATH.getString("ALAMAT_HTA3") == null ? "" : rsBatalPAHTATH.getString("ALAMAT_HTA3")) + ", " + (rsBatalPAHTATH.getString("POSKOD_HTA") == null ? "" : rsBatalPAHTATH.getString("POSKOD_HTA"))
								+ " " + (rsBatalPAHTATH.getString("BANDAR_HTA") == null ? "" : rsBatalPAHTATH.getString("BANDAR_HTA")) + ", " + (rsBatalPAHTATH.getString("NAMA_NEGERI") == null ? "" : rsBatalPAHTATH.getString("NAMA_NEGERI"));
							}
						} else 
							//PEGANGAN DI BAWAH AKTA TANAH (KAWASAN PENEMPATAN BERKELOMPOK 1960)
							if (rsBatalPAHTATH.getString("FLAG_KATEGORI_HTA").equals("2")){
								if (sebabBatalPA.isEmpty()){
									sebabBatalPA = " MEMBATALKAN PEMEGANG AMANAH " + (rsBatalPAHTATH.getString("NO_ROH") == null ? "" : rsBatalPAHTATH.getString("NO_ROH")) + ", " + (rsBatalPAHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsBatalPAHTATH.getString("NAMA_RANCANGAN"));
								} else {
									sebabBatalPA = sebabBatalPA + " DAN " + (rsBatalPAHTATH.getString("NO_ROH") == null ? "" : rsBatalPAHTATH.getString("NO_ROH")) + ", " + (rsBatalPAHTATH.getString("NAMA_RANCANGAN") == null ? "" : rsBatalPAHTATH.getString("NAMA_RANCANGAN"));
								}
							} else 
								//KEPENTINGAN LAIN- LAIN
								if (rsBatalPAHTATH.getString("FLAG_KATEGORI_HTA").equals("3")){
									if (sebabBatalPA.isEmpty()){
										sebabBatalPA = " MEMBATALKAN PEMEGANG AMANAH " + (rsBatalPAHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsBatalPAHTATH.getString("JENIS_KEPENTINGAN"));
									} else {
										sebabBatalPA = sebabBatalPA + " DAN " + (rsBatalPAHTATH.getString("JENIS_KEPENTINGAN") == null ? "" : rsBatalPAHTATH.getString("JENIS_KEPENTINGAN"));
									}
								}
								
					}
					
					//BATAL PA HARTA ALIH
					sql = "SELECT B.KETERANGAN, REPLACE(A.JENAMA,'&','&#38;') AS JENAMA,REPLACE(A.NO_DAFTAR,'&','&#38;') AS NO_DAFTAR"
							+ " FROM TBLPPKHA A, TBLPPKRUJJENISHA B"
							+ " WHERE B.ID_JENISHA = A.ID_JENISHA"
							+ " AND A.FLAG_PA = 'Y' AND A.ID_HA IN (SELECT ID_HA FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '" + idPermohonanSimati + "')";
					
					ResultSet rsBatalPAHA = stmt.executeQuery(sql);
					
					while (rsBatalPAHA.next()){
						if (sebabBatalPA.isEmpty()){
							sebabBatalPA = " MEMBATALKAN PEMEGANG AMANAH " + (rsBatalPAHA.getString("KETERANGAN") == null ? "" : rsBatalPAHA.getString("KETERANGAN")) + " - " + (rsBatalPAHA.getString("JENAMA") == null ? "" : rsBatalPAHA.getString("JENAMA")) + ", " + (rsBatalPAHA.getString("NO_DAFTAR") == null ? "" : rsBatalPAHA.getString("NO_DAFTAR"));
						} else {
							sebabBatalPA = sebabBatalPA + " DAN " + (rsBatalPAHA.getString("KETERANGAN") == null ? "" : rsBatalPAHA.getString("KETERANGAN")) + " - " + (rsBatalPAHA.getString("JENAMA") == null ? "" : rsBatalPAHA.getString("JENAMA")) + ", " + (rsBatalPAHA.getString("NO_DAFTAR") == null ? "" : rsBatalPAHA.getString("NO_DAFTAR"));
						}
					}
					
					
					
					
				}
				
				if ("Y".equals(lain_tujuan)){
					
					
					sql = " select xx.catatan from tblsemakanhantar xx,tblppkpermohonan T,tblpfdfail f "+
					" where xx.id_permohonan = T.id_permohonan and t.id_fail = f.id_fail and xx.id_semakansenarai = 17 "+
					" and f.id_fail = '"+idFail+"' ";
					
					ResultSet rsLainTujuan= stmt.executeQuery(sql);
					
					while (rsLainTujuan.next()){
											
						sebabLainTujuan = rsLainTujuan.getString("catatan") == null ? "" : rsLainTujuan.getString("catatan").toUpperCase();
						
					}
					
					
			
					
					
				}
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		
		myLogger.info("sebabLainTujuan :"+sebabLainTujuan);
		myLogger.info("sebab :"+sebab);
		
		sebab = sebabHartaTertinggal;
		if (sebab.isEmpty()){
			sebab = sebabBatalPT;
		} else {
			if (!sebabBatalPT.isEmpty()){
				sebab = sebab + " DAN " + sebabBatalPT;
			}
		}
		if (sebab.isEmpty()){
			sebab = sebabBatalPA;
		} else {
			if (!sebabBatalPA.isEmpty()){
				sebab = sebab + " DAN " + sebabBatalPA;
			}
		}		
		
		if (sebab.isEmpty()){
			sebab = sebabLainTujuan;
		} else {
			if (!sebabLainTujuan.isEmpty()){
				sebab = sebab + " DAN " + sebabLainTujuan;
			}
		}
	
		
		myLogger.info("sebab2 :"+sebab);
		
		return sebab.toUpperCase();
	}
	
	
	public boolean getFlagKebenaranEdit(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		
		boolean flag = false;
		try {
			db = new Db();

			sql = "SELECT * FROM TBLEDITAGIHAN WHERE ID_FAIL =  '"+ idFail + "' ";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				flag = true;
			}
			return flag;
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getStatusFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		String status = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_STATUS "
				  +" FROM TBLPPKPERMOHONAN"
				  +" WHERE ID_FAIL  = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
					status = rs.getString("ID_STATUS");
				
			}
		} finally {
			if (db != null)
				db.close();
		}

		return status;
	}
	
	public String getBaluDuda(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		String baluDuda = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPPKOB.NAMA_OB, TBLPPKPERMOHONANSIMATI.ID_SIMATI"
				  +" FROM TBLPPKPERMOHONAN, TBLPPKPERMOHONANSIMATI, TBLPPKOB, TBLPPKRUJSAUDARA"
				  +" WHERE TBLPPKRUJSAUDARA.ID_SAUDARA IN (1, 2)"
			      +" AND TBLPPKOB.LAPIS = 1"
			      +" AND TBLPPKPERMOHONAN.ID_PERMOHONAN = TBLPPKPERMOHONANSIMATI.ID_PERMOHONAN"
			      +" AND TBLPPKPERMOHONANSIMATI.ID_PERMOHONANSIMATI =TBLPPKOB.ID_PERMOHONANSIMATI"
			      +" AND TBLPPKRUJSAUDARA.ID_SAUDARA = TBLPPKOB.ID_SAUDARA"
			      +" AND TBLPPKPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				if(baluDuda.isEmpty()){
					baluDuda = " " + rs.getString("NAMA_OB");
				} else {
					baluDuda = baluDuda + " DAN " + rs.getString("NAMA_OB");
				}
			}
		} finally {
			if (db != null)
				db.close();
		}

		return baluDuda;
	}
	
	public void getPemohonTerdahulu(String idfail) throws Exception{
		Db db = null;
		String sqlFailCurrent = "";
		String sqlFailTerdahulu = "";
		String sqlPermohonanTerdahulu = "";
		String sqlPemohon = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			beanPemohonTerdahulu = new Vector();
						
			sqlFailTerdahulu = "SELECT ID_FAIL FROM TBLPFDFAIL"
					+ " WHERE NO_FAIL = (SELECT distinct"
					+ "  CASE"
					+ " WHEN TBLPPKPERMOHONAN.NO_SUBJAKET = 1 THEN SUBSTR(TBLPFDFAIL.NO_FAIL,1,INSTR(TBLPFDFAIL.NO_FAIL,'/S17-')-1)"
					+ " WHEN TBLPPKPERMOHONAN.NO_SUBJAKET > 1 THEN SUBSTR(TBLPFDFAIL.NO_FAIL,1,INSTR(TBLPFDFAIL.NO_FAIL,'-')-1)||'-'||TO_CHAR(SUBSTR(TBLPFDFAIL.NO_FAIL,-1)-1)"
					+ " END AS NO_FAIL_AWAL"
					+ " FROM TBLPFDFAIL, TBLPPKPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL"
					+ " AND TBLPPKPERMOHONAN.ID_STATUS NOT IN ('999')"
					+ "AND TBLPFDFAIL.ID_FAIL = '" + idfail + "')";		
			
	
			
			ResultSet rsFailTerdahulu = stmt.executeQuery(sqlFailTerdahulu);
			
	
			
			Hashtable h = null;
			boolean hasPemohon = false;
			while (rsFailTerdahulu.next()){				
				if(rsFailTerdahulu.getString("ID_FAIL").trim().length() > 0){
					sqlPemohon = "SELECT DISTINCT REPLACE(REPLACE(UPPER(G.NAMA_PEMOHON),','),'&','&#38;') AS NAMA_PEMOHON,  NVL(g.no_kp_baru,g.NO_KP_LAMA) as NO_KP_BARU , " 
							//" G.NO_KP_BARU,"
							+ " CASE"
							+ " WHEN G.ALAMAT_1 IS NULL THEN 'TIADA'"
							+ " WHEN G.ALAMAT_2 IS NULL AND G.ALAMAT_3 IS NULL THEN  REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
							+ " WHEN G.ALAMAT_2 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
							+ " WHEN G.ALAMAT_3 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
							+ " WHEN G.ALAMAT_3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
							+ " ELSE 'TIADA'"
							+ " END AS ALAMAT_PENUH_PEMOHON,"
							+ " CASE"
							+ " WHEN LENGTH(CCC.NO_KP2)<12 THEN  ''||RTRIM(CCC.NO_KP2)||''"
							+ " WHEN LENGTH(RTRIM(CCC.NO_KP2))=12 THEN SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)"
							+ " ELSE SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)  ||' ('||SUBSTR(CCC.NO_KP2,13,LENGTH(CCC.NO_KP2))||')'"
							+ " END  AS KP_PEMOHON"  
							+ " FROM TBLPPKPEMOHON G,"
							+ " TBLPPKPERMOHONAN A,"
							+ " TBLPFDFAIL B,"
							+ " TBLRUJNEGERI NEGERIPEMOHON,"
							+ " TBLRUJBANDAR BANDARPEMOHON,"
							+ " (SELECT TBLPPKPEMOHON.NAMA_PEMOHON,TBLPPKPEMOHON.ID_PEMOHON,"
							+ " CASE" 
							+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
							+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAIN"
							+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAIN IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
							+ " ELSE TBLPPKPEMOHON.NO_KP_BARU"
							+ " END || '' ||"     
							+ " CASE"
							+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NOT NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAMA"
							+ " END || '' ||"     
							+ " CASE"
							+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS  NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAIN"     
							+ " END AS NO_KP2"
							+ " FROM TBLPPKPERMOHONAN,TBLPPKPEMOHON"
							+ " WHERE TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
							+ " ) CCC"
							+ " WHERE G.ID_PEMOHON = A.ID_PEMOHON"
							+ " AND B.ID_FAIL = A.ID_FAIL"
							+ " AND G.ID_PEMOHON = CCC.ID_PEMOHON"
							+ " AND NEGERIPEMOHON.ID_NEGERI = G.ID_NEGERI"
							+ " AND BANDARPEMOHON.ID_BANDAR(+) = G.ID_BANDAR"
							+ " AND B.ID_FAIL = '" + rsFailTerdahulu.getString("ID_FAIL") + "'";
					
					
					ResultSet rsPemohon = stmt.executeQuery(sqlPemohon);
					
					
					System.out.println("====sqlPemohon==="+sqlPemohon);
					if (rsPemohon.next()) {
						hasPemohon = true;
						
						//System.out.println("====hasPemohon1==="+hasPemohon);
						
						h = new Hashtable();
						h.put("namaPemohon",rsPemohon.getString("NAMA_PEMOHON"));
						h.put("alamatPemohon",rsPemohon.getString("ALAMAT_PENUH_PEMOHON"));
						//System.out.println("====hasPemohonX===");
						//h.put("kpPemohon", rsPemohon.getString("KP_PEMOHON"));
						h.put("kpPemohon",rsPemohon.getString("KP_PEMOHON")!=null?rsPemohon.getString("KP_PEMOHON"):"");
						//System.out.println("====hasPemohonY===");
						h.put("kpBaru",rsPemohon.getString("NO_KP_BARU")!=null?rsPemohon.getString("NO_KP_BARU"):"");
						beanPemohonTerdahulu.addElement(h);
						break;
					}
				}
			}
			//System.out.println("====hasPemohon2==="+hasPemohon);
			if (!hasPemohon) {				
				sqlPermohonanTerdahulu = "SELECT NVL(A.ID_PERMOHONANTERDAHULU,'0')AS ID_PERMOHONANTERDAHULU FROM TBLPPKPERMOHONAN A,TBLPFDFAIL B"
					+ " WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_FAIL = '" + idfail + "'";
				System.out.println("====sqlPermohonanTerdahulu bbbb===");	
				
					ResultSet rsPermohonanTerdahulu = stmt.executeQuery(sqlPermohonanTerdahulu);
					System.out.println("====sqlPermohonanTerdahulu==="+sqlPermohonanTerdahulu);	
					while(rsPermohonanTerdahulu.next()){
						if(!"0".equals(rsPermohonanTerdahulu.getString("ID_PERMOHONANTERDAHULU"))){
							sqlPemohon = "SELECT REPLACE(REPLACE(UPPER(G.NAMA_PEMOHON),','),'&','&#38;') AS NAMA_PEMOHON,G.NO_KP_BARU,"
									+ " CASE"
									+ " WHEN G.ALAMAT_1 IS NULL THEN 'TIADA'"
									+ " WHEN G.ALAMAT_2 IS NULL AND G.ALAMAT_3 IS NULL THEN  REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_2 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_3 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " ELSE 'TIADA'"   
									+ " END AS ALAMAT_PENUH_PEMOHON," 
									+ " CASE" 
									+ " WHEN LENGTH(CCC.NO_KP2)<12 THEN  ''||RTRIM(CCC.NO_KP2)||''"
									+ " WHEN LENGTH(RTRIM(CCC.NO_KP2))=12 THEN SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)"
									+ " ELSE SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)  ||' ('||SUBSTR(CCC.NO_KP2,13,LENGTH(CCC.NO_KP2))||')'"
									+ " END  AS KP_PEMOHON"  
									+ " FROM TBLPPKPEMOHON G,"
									+ " TBLPPKPERMOHONAN A,"
									+ " TBLPFDFAIL B,"
									+ " TBLPPKPERMOHONAN C,"
									+ " TBLRUJNEGERI NEGERIPEMOHON,"
									+ " TBLRUJBANDAR BANDARPEMOHON,"
									+ " (SELECT TBLPPKPEMOHON.NAMA_PEMOHON,TBLPPKPEMOHON.ID_PEMOHON,"
									+ " CASE" 
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAIN"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAIN IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
									+ " ELSE TBLPPKPEMOHON.NO_KP_BARU"
									+ " END || '' ||"     
									+ " CASE"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NOT NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAMA"
									+ " END || '' ||"     
									+ " CASE"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS  NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAIN"     
									+ " END AS NO_KP2"
									+ " FROM TBLPPKPERMOHONAN,TBLPPKPEMOHON"
									+ " WHERE TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
									+ " ) CCC"
									+ " WHERE G.ID_PEMOHON = C.ID_PEMOHON"
									+ " AND B.ID_FAIL = A.ID_FAIL"
									+ " AND G.ID_PEMOHON = CCC.ID_PEMOHON"
									+ " AND A.ID_PERMOHONANTERDAHULU = C.ID_PERMOHONAN"
									+ " AND NEGERIPEMOHON.ID_NEGERI = G.ID_NEGERI"
									+ " AND BANDARPEMOHON.ID_BANDAR(+) = G.ID_BANDAR"
									+ " AND B.ID_FAIL = '" + idfail + "'";
							System.out.println("====sqlPemohon 222222222===");	
							ResultSet rsPemohon = stmt.executeQuery(sqlPemohon);
							System.out.println("====sqlPemohon==="+sqlPemohon);	
							
							if (rsPemohon.next()) {
								hasPemohon = true;
								h = new Hashtable();
								h.put("namaPemohon",rsPemohon.getString("NAMA_PEMOHON"));
								h.put("alamatPemohon",rsPemohon.getString("ALAMAT_PENUH_PEMOHON"));
								h.put("kpPemohon", rsPemohon.getString("KP_PEMOHON"));
								h.put("kpBaru",rsPemohon.getString("NO_KP_BARU")!=null?rsPemohon.getString("NO_KP_BARU"):"");
								beanPemohonTerdahulu.addElement(h);
								break;
							}
						} else {
							//--------------- pemohon yang tidak ada fail terdahulu-----------------------
							sqlPemohon = "SELECT REPLACE(REPLACE(UPPER(G.NAMA_PEMOHON),','),'&','&#38;') AS NAMA_PEMOHON,G.NO_KP_BARU,"
									+ " CASE"
									+ " WHEN G.ALAMAT_1 IS NULL THEN 'TIADA'"
									+ " WHEN G.ALAMAT_2 IS NULL AND G.ALAMAT_3 IS NULL THEN  REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_2 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_3 IS NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;') ||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' ||REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " WHEN G.ALAMAT_3 IS NOT NULL THEN REPLACE(REPLACE(UPPER(G.ALAMAT_1),','),'&','&#38;')||', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_2),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.ALAMAT_3),','),'&','&#38;') || ', ' || REPLACE(REPLACE(UPPER(G.POSKOD),','),'&','&#38;') || ' ' || REPLACE(REPLACE(UPPER(BANDARPEMOHON.KETERANGAN),','),'&','&#38;') ||', '|| REPLACE(REPLACE(UPPER(NEGERIPEMOHON.NAMA_NEGERI),','),'&','&#38;')"
									+ " ELSE 'TIADA'"   
									+ " END AS ALAMAT_PENUH_PEMOHON," 
									+ " CASE" 
									+ " WHEN LENGTH(CCC.NO_KP2)<12 THEN  ''||RTRIM(CCC.NO_KP2)||''"
									+ " WHEN LENGTH(RTRIM(CCC.NO_KP2))=12 THEN SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)"
									+ " ELSE SUBSTR(CCC.NO_KP2,1,6) || '-' || SUBSTR(CCC.NO_KP2,7,2) || '-' || SUBSTR(CCC.NO_KP2,9,4)  ||' ('||SUBSTR(CCC.NO_KP2,13,LENGTH(CCC.NO_KP2))||')'"
									+ " END  AS KP_PEMOHON"  
									+ " FROM TBLPPKPEMOHON G,"
									+ " TBLPPKPERMOHONAN A,"
									+ " TBLPFDFAIL B,"
									+ " TBLRUJNEGERI NEGERIPEMOHON,"
									+ " TBLRUJBANDAR BANDARPEMOHON,"
									+ " (SELECT TBLPPKPEMOHON.NAMA_PEMOHON,TBLPPKPEMOHON.ID_PEMOHON,"
									+ " CASE" 
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAIN"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAIN IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
									+ " ELSE TBLPPKPEMOHON.NO_KP_BARU"
									+ " END || '' ||"     
									+ " CASE"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NOT NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAMA"
									+ " END || '' ||"     
									+ " CASE"
									+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS  NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN TBLPPKPEMOHON.NO_KP_LAIN"     
									+ " END AS NO_KP2"
									+ " FROM TBLPPKPERMOHONAN,TBLPPKPEMOHON"
									+ " WHERE TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
									+ " ) CCC"
									+ " WHERE G.ID_PEMOHON = A.ID_PEMOHON"
									+ " AND B.ID_FAIL = A.ID_FAIL"
									+ " AND G.ID_PEMOHON = CCC.ID_PEMOHON"
									+ " AND NEGERIPEMOHON.ID_NEGERI = G.ID_NEGERI"
									+ " AND BANDARPEMOHON.ID_BANDAR(+) = G.ID_BANDAR"
									+ " AND B.ID_FAIL = '" + idfail + "'";
							
							System.out.println("====sblm===");
							ResultSet rsPemohon = stmt.executeQuery(sqlPemohon);
							
							System.out.println("sqlPemohon==="+sqlPemohon);
							
							if (rsPemohon.next()) {
								hasPemohon = true;
								h = new Hashtable();
								h.put("namaPemohon",rsPemohon.getString("NAMA_PEMOHON"));
								h.put("alamatPemohon",rsPemohon.getString("ALAMAT_PENUH_PEMOHON"));
								h.put("kpPemohon", rsPemohon.getString("KP_PEMOHON"));
								h.put("kpBaru",rsPemohon.getString("NO_KP_BARU")!=null?rsPemohon.getString("NO_KP_BARU"):"");
								beanPemohonTerdahulu.addElement(h);
								break;
							}
						}
					}
				}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPerintah(String idFail) throws Exception {
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
			r.add("A.ID_PERMOHONAN",r.unquote("D.ID_PERMOHONAN"));
			
			r.add("D.ID_FAIL", idFail);
			r.add("C.FLAG_JENIS_KEPUTUSAN", "0");

			sql = r.getSQLSelect("TBLPPKKEPUTUSANPERMOHONAN A, TBLPPKPERBICARAAN B, TBLPPKPERINTAH C, TBLPPKPERMOHONAN D");
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
	
	public Boolean getFlagLampiran(String idPerintah) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT * FROM TBLPPKPERINTAH A, TBLPPKPERINTAHHTAOBMST B, TBLPPKPERINTAHPEMBAHAGIAN C, TBLPPKOBPERMOHONAN D, TBLPPKHTA E, TBLRUJJENISHAKMILIK F"
					+ " WHERE A.ID_PERINTAH = B.ID_PERINTAH AND B.ID_PERINTAHHTAOBMST = C.ID_PERINTAHHTAOBMST AND C.ID_OB = D.ID_OB AND B.ID_HTA = E.ID_HTA AND E.ID_JENISHM = F.ID_JENISHAKMILIK AND A.ID_PERINTAH = '" + idPerintah + "'";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//aishahlatip ad 20/4/2018
	public boolean getJawatanKPP_HQ(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			boolean JawatanKpp=false;
			sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
					" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL," +
					" J.KETERANGAN AS NAMA_JAWATAN , J.SHORTNAME_JAWATAN KOD_JWTN" +
					" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D " +
					" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " +
					" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
					" AND UI.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) " +
					" AND UI.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND J.ID_JAWATAN IN (5)  " +
					" AND PEJ.ID_NEGERI = 16 " +
					//" AND pej.id_jenispejabat IN (24) " +
					" AND UI.FLAG_AKTIF != 2 " +
					" AND U.USER_ID = "+USER_ID;
			
			myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
			
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					JawatanKpp = true;
				}		
				
				myLogger.info("JawatanKpp==="+JawatanKpp);
			return JawatanKpp;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public Vector getBeanMaklumatPermohonanbyUser() {
		return beanMaklumatPermohonanbyUser;
	}
	
	public Vector getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}

	public Vector getBeanMaklumatPegawai() {
		return this.beanMaklumatPegawai;
	}

	public void setBeanMaklumatPegawai(Vector beanMaklumatPegawai) {
		this.beanMaklumatPegawai = beanMaklumatPegawai;
	}

	public Vector getBeanMaklumatPejabatJKPTG() {
		return this.beanMaklumatPejabatJKPTG;
	}

	public void setBeanMaklumatPejabatJKPTG(Vector beanMaklumatPejabatJKPTG) {
		this.beanMaklumatPejabatJKPTG = beanMaklumatPejabatJKPTG;
	}

	public void setBeanMaklumatPentadbir(Vector beanMaklumatPentadbir) {
		this.beanMaklumatPentadbir = beanMaklumatPentadbir;
	}

	public Vector getBeanMaklumatPentadbir() {
		return this.beanMaklumatPentadbir;
	}

	public Vector getListPilihanOb() {
		return this.listPilihanOb;
	}

	public void setBeanSenaraiOBPentadbir(Vector beanSenaraiOBPentadbir) {
		this.beanSenaraiOBPentadbir = beanSenaraiOBPentadbir;
	}

	public Vector getBeanSenaraiOBPentadbir() {
		return this.beanSenaraiOBPentadbir;
	}
	
	public Vector getBeanPemohonTerdahulu() {
		return beanPemohonTerdahulu;
	}

	public void setBeanPemohonTerdahulu(Vector beanPemohonTerdahulu) {
		this.beanPemohonTerdahulu = beanPemohonTerdahulu;
	}

	public String getIdFailbyIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();

			sql = "SELECT ID_FAIL FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			Statement stmt = db.getStatement();
			System.out.println("sql = "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				String str1;
				return rs.getString("ID_FAIL");
			}
			return "";
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getSignedData(String idPerbicaraan) throws Exception {
		Db db = null;
		String sql = "";
		String signdata = "";
		try {
			db = new Db();

			sql = "SELECT A.SIGNED_TEXT FROM TBLPPKPERBICARAAN A WHERE A.ID_PERBICARAAN ="+ idPerbicaraan;

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String temp = "";

			if (rs.next()) {
				
				signdata =  rs.getString("SIGNED_TEXT");
			}
			return signdata;
		} finally {
			if (db != null)
				db.close();
		}
	}
}
