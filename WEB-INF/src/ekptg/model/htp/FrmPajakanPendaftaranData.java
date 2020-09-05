/**
 *
 */
package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanPendaftaranData {

	private Vector<Hashtable<String, String>> senaraiHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmPajakanPendaftaranData.class);
	private FrmPajakanMemorandumJemaahMenteriData mjmData = null;
	private String sql = "";
	private Db db = null;
	private Connection conn = null;

	public void setListHakmilik(String idPermohonan) throws Exception {
		try {
			senaraiHakmilik = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT" +
				" ,A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH" +
				" ,C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM" +
				" ,A.ID_DAERAH, A.ID_NEGERI,A.LUAS,A.LUAS_BERSAMAAN" +
				" ,REPLACE(RJH.KOD_JENIS_HAKMILIK,'00','') KOD_JENIS_HAKMILIK " +
				" ,RL.KOD_LUAS KOD_LUAS " +
				" ,RLB.KOD_LUAS KOD_LUASBERSAMAAN " +
				" ,NVL(GIS.UPI,'N') GIS_HANTAR  ,NVL(GIS.LATITUDE,'N') GIS_CHARTING " +
				" FROM TBLHTPHAKMILIKURUSAN A, TBLRUJLOT B" +
				", TBLRUJNEGERI C, TBLRUJDAERAH D,TBLRUJMUKIM E " +
				" ,TBLRUJJENISHAKMILIK RJH, TBLRUJLUAS RL,TBLRUJLUAS RLB,TBLINTGIS GIS " +
				" ,(   SELECT MT.ID_HAKMILIKURUSAN " +
				" ,GETUPI(RN.KOD_NEGERI,RD.KOD_DAERAH_UPI,RM.KOD_MUKIM_UPI,'000',RJH.STATUS_HAKMILIK,MT.NO_LOT,MT.NO_HAKMILIK,RJH.KOD_JENIS_HAKMILIK ) UPI  " +
				"  FROM TBLHTPHAKMILIKURUSAN MT,TBLRUJJENISHAKMILIK RJH  " +
				" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM  " +
				" WHERE MT.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK  " +
				" AND MT.ID_NEGERI = RN.ID_NEGERI  " +
				" AND MT.ID_DAERAH = RD.ID_DAERAH  " +
				" AND MT.ID_MUKIM = RM.ID_MUKIM  " +
				" ) UP  	" +
				" WHERE A.ID_LOT = B.ID_LOT " +
				" AND A.ID_NEGERI = C.ID_NEGERI " +
				" AND A.ID_DAERAH = D.ID_DAERAH " +
				" AND A.ID_MUKIM = E.ID_MUKIM" +
				" AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK " +
				" AND A.ID_LUAS = RL.ID_LUAS " +
				" AND A.ID_LUAS_BERSAMAAN = RLB.ID_LUAS " +
				" AND A.ID_HAKMILIKURUSAN = UP.ID_HAKMILIKURUSAN" +
				" AND UP.UPI = GIS.UPI(+) AND GIS.STATUS_TANAH(+) = 7 " +
				" AND A.ID_PERMOHONAN = '" + idPermohonan + "' ORDER BY A.ID_HAKMILIKURUSAN ASC";
			myLog.info("setListHakmilik :: sql >>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
				h.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("kodJenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs.getString("KOD_JENIS_HAKMILIK"));
				h.put("luas",rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("kodLuas", rs.getString("KOD_LUAS") == null ? "" : rs.getString("KOD_LUAS"));
				h.put("luasBersamaan", rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble("LUAS_BERSAMAAN")));
				h.put("kodLuasBersamaan", rs.getString("KOD_LUASBERSAMAAN") == null ? "" : rs.getString("KOD_LUASBERSAMAAN"));
				h.put("gisHantar", rs.getString("GIS_HANTAR"));
				h.put("gisCharting", rs.getString("GIS_CHARTING"));
				senaraiHakmilik.addElement(h);
				bil++;

			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void seterusnya(String idFail, String idPermohonan, String subUrusan, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		FrmPajakanMemorandumJemaahMenteriData mjmData = new FrmPajakanMemorandumJemaahMenteriData();

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPPEMOHON
			if(mjmData.isMaklumatPemohonPajakan(idPermohonan)==false){
				long idPemohon = DB.getNextID("TBLHTPPEMOHON_SEQ");
				r.add("ID_PEMOHON", idPemohon);
				r.add("ID_PERMOHONAN", idPermohonan);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPPEMOHON");
				stmt.executeUpdate(sql);

			}
//			//TBLHTPULASANKJP
//			r = new SQLRenderer();
//			long idUlasanKJP = DB.getNextID("TBLHTPULASANKJP_SEQ");
//			r.add("ID_ULASANKJP", idUlasanKJP);
//			r.add("ID_PERMOHONAN", idPermohonan);
//
//			r.add("ID_MASUK", userId);
//			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//
//			sql = r.getSQLInsert("TBLHTPULASANKJP");
//			stmt.executeUpdate(sql);

			//TBLHTPJEMAAHMENTERI
			r = new SQLRenderer();
			mjmData = new FrmPajakanMemorandumJemaahMenteriData();
			if(mjmData.isMaklumatMemorandumJemaahMenteri(idPermohonan)==false){

				long idJemaahMenteri = DB.getNextID("TBLHTPJEMAAHMENTERI_SEQ");
				r.add("ID_JEMAAHMENTERI", idJemaahMenteri);
				r.add("ID_PERMOHONAN", idPermohonan);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPJEMAAHMENTERI");
				stmt.executeUpdate(sql);

			}
			//TBLPERMOHONAN
			Long setIdStatus = 0L;
			//MJM =5
			setIdStatus = FrmUtilData.getIdStatusByLangkah("5",subUrusan,"=");

			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("ID_PERMOHONAN", idPermohonan);
			//r.add("ID_STATUS", "65");
			r.add("ID_STATUS", setIdStatus);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			// Buka komen oleh Mohamad Rosli pada 2017/04/10
			stmt.executeUpdate(sql);

			//TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");
			r.add("AKTIF", "0");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			Long setIdSuburusanstatus = 0L;
			//MJM =5
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("5",subUrusan,"=");

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			/*if ("7".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "19"); //MEMORANDUM JEMAAH MENTERI
			} else if ("17".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "190"); //MEMORANDUM JEMAAH MENTERI
			} else if ("18".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "199"); //MEMORANDUM JEMAAH MENTERI
			}*/
			r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus); //MEMORANDUM JEMAAH MENTERI
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}

	}

	public void deleteHakmilik(String idHakmilikUrusan) throws Exception {
		Connection conn = null;
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPHAKMILIKURUSAN
			r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);

			sql = r.getSQLDelete("TBLHTPHAKMILIKURUSAN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}
	}

	public void saveUpdateFail(String idFail,String subUrusan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("3",subUrusan,"=");
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("3",subUrusan,"=");
			*/
			//TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", hash.get("tajuk"));
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			myLog.info("TBLPFDFAIL_sql="+sql);
			stmt.executeUpdate(sql);

			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.add("TUJUAN", hash.get("tajuk"));
			//r.add("ID_STATUS",setIdStatus);
			String tarikhSuratKJP = "to_date('" + hash.get("tarikhSuratKJP").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhSuratKJP").toString())){
				r.add("TARIKH_SURAT", r.unquote(tarikhSuratKJP));
			}
			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			myLog.info("TBLPERMOHONAN_sql="+sql);
			stmt.executeUpdate(sql);

			//get id permohonan
			sql = "SELECT A.ID_PERMOHONAN ";
			sql += "FROM TBLPERMOHONAN A ";
			sql += "WHERE A.ID_FAIL ='"+idFail+"'";
			ResultSet rs = stmt.executeQuery(sql);

			String idPermohonan = "";
			while(rs.next()){
				idPermohonan = rs.getString("ID_PERMOHONAN");
			}

			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN_LAIN", hash.get("noFailLain"));
			r.add("NO_RUJUKAN_KJP", hash.get("noFailKJP"));

			String tarikhAgihan = "to_date('" + hash.get("tarikhAgihan").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhAgihan").toString())){
				r.add("TARIKH_AGIHAN", r.unquote(tarikhAgihan));
			}

			String tarikhSuratPemohon = "to_date('" + hash.get("tarikhSuratPemohon").toString() + "','dd/MM/yyyy')";
			if (!"".equals(hash.get("tarikhSuratPemohon").toString())){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(tarikhSuratPemohon));
			}

			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			myLog.info("TBLHTPPERMOHONAN_sql="+sql);
			stmt.executeUpdate(sql);

			//TBLRUJSUBURUSANSTATUSFAIL
			/*r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("ID_FAIL", idFail);
			r.update("AKTIF", "1");
			r.add("AKTIF", "0");
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus);
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			*/
			conn.commit();

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}

	}

	public void saveUpdateFail(String idFail, Hashtable<String,String> hash, HttpSession session) throws Exception {
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", hash.get("tajuk"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.add("TUJUAN", hash.get("tajuk"));

			String tarikhSuratKJP = "to_date('" + hash.get("tarikhSuratKJP").toString() + "','dd/MM/yyyy')";

			if (!"".equals(hash.get("tarikhSuratKJP").toString())){
				r.add("TARIKH_SURAT", r.unquote(tarikhSuratKJP));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			//get id permohonan
			sql = "SELECT A.ID_PERMOHONAN ";
			sql += "FROM TBLPERMOHONAN A ";
			sql += "WHERE A.ID_FAIL ='"+idFail+"'";

			ResultSet rs = stmt.executeQuery(sql);

			String idPermohonan = "";

			while(rs.next()){
				idPermohonan = rs.getString("ID_PERMOHONAN");
			}

			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN_LAIN", hash.get("noFailLain"));
			r.add("NO_RUJUKAN_KJP", hash.get("noFailKJP"));

			String tarikhAgihan = "to_date('" + hash.get("tarikhAgihan").toString() + "','dd/MM/yyyy')";

			if (!"".equals(hash.get("tarikhAgihan").toString())){
				r.add("TARIKH_AGIHAN", r.unquote(tarikhAgihan));
			}

			String tarikhSuratPemohon = "to_date('" + hash.get("tarikhSuratPemohon").toString() + "','dd/MM/yyyy')";

			if (!"".equals(hash.get("tarikhSuratPemohon").toString())){
				r.add("TARIKH_SURAT_RUJUKAN_LAIN", r.unquote(tarikhSuratPemohon));
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}

	}

	public Vector<Hashtable<String, String>> getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public void setSenaraiHakmilik(Vector<Hashtable<String,String>> senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}


}
