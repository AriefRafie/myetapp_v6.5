package ekptg.model.htp;

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
import lebah.util.Util;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmPerletakhakanMaklumatData {

	private static Vector maklumatFailMaster = null;
	private static Vector maklumatSenarai = null;
	private static Vector maklumatSenaraiCari = null;
	
	private static Vector maklumatLetakHak = null;
	private static Vector maklumatBayaran = null;
	private static Vector maklumatBorang30A = null;
	
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	private HakmilikInterface hakmilikInterface;
	private HakmilikInterface getHakmilikBean(){
		if(hakmilikInterface == null){
			hakmilikInterface = new HakmilikBean();
		}
		return hakmilikInterface;
	}
	public void transferToRecord(String idFail){
		Db db = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL="+idFail;
			ResultSet rs = stmt.executeQuery(sql);
			String idPermohonan="";
			if(rs.next()){
				idPermohonan = rs.getString("ID_PERMOHONAN");
			}
			
			getHakmilikBean().transferRecord(idPermohonan);
			
		}
		catch(Exception e){
				e.printStackTrace();
		}
		finally{
			if(db!=null)
				db.close();
		}
	}
	public boolean isFailTransfered(String idFail){
		Db db = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL="+idFail;
			ResultSet rs = stmt.executeQuery(sql);
			String idPermohonan="";
			if(rs.next()){
				idPermohonan = rs.getString("ID_PERMOHONAN");
			}
			
			sql = "SELECT ID_HAKMILIK FROM TBLHTPHAKMILIK WHERE ID_PERMOHONAN="+idPermohonan;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return true;
			}else{
				return false;
			}
			
		}
		catch(Exception e){
				e.printStackTrace();
		}
		finally{
			if(db!=null)
				db.close();
		}
		return false;
	}
	public Vector getFailSelesaiStatus(String idFail){
		Db db = null;
		Vector v = new Vector();
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "SELECT A.ID_FAIL,A.NO_FAIL,A.TARIKH_DAFTAR_FAIL,B.TUJUAN,B.ID_PERMOHONAN,C.KETERANGAN, C.KOD_STATUS "+
			"FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C,TBLHTPPERMOHONAN D,TBLRUJSUBURUSANSTATUSFAIL E,TBLRUJSUBURUSANSTATUS F "+
			"WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL "+
			"AND B.ID_PERMOHONAN = D.ID_PERMOHONAN AND C.ID_STATUS = F.ID_STATUS "+
			"AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND F.ID_SUBURUSANSTATUS = E.ID_SUBURUSANSTATUS AND F.AKTIF='1' "+
			"AND a.NO_FAIL like '%%'";                         						
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR_FAIL") == null ? "": Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));
				v.addElement(h);
				bil++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db!=null)
			 db.close();
		}
		return v;
	}
	// set list senarai
	public Vector setMaklumatSenaraiCarian(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatSenaraiCari = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL,A.NO_FAIL,A.TARIKH_DAFTAR_FAIL,B.TUJUAN,B.ID_PERMOHONAN,C.KETERANGAN"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPERMOHONAN B,"
					+ " TBLRUJSTATUS C,"
					+ " TBLHTPPERMOHONAN D "
					+ " WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "
					+ " AND C.ID_STATUS = A.ID_STATUS and a.NO_FAIL like '%"+idFail+"%'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR_FAIL") == null ? "": Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));

				maklumatSenaraiCari.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return maklumatSenaraiCari;

	}
	public Vector getMaklumatSenaraiCarian() {
		return maklumatSenarai;
	}


	public void setMaklumatSenarai(String noFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatSenarai = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL,A.NO_FAIL,A.TARIKH_DAFTAR_FAIL,B.TUJUAN,B.ID_PERMOHONAN,C.KETERANGAN"
					+ " FROM TBLPFDFAIL A,"
					+ " TBLPERMOHONAN B,"
					+ " TBLRUJSTATUS C,"
					+ " TBLHTPPERMOHONAN D "
					+ " WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN "
					+ " AND C.ID_STATUS = A.ID_STATUS and a.NO_FAIL like '%"+noFail+"%'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail", rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("tarikhDaftar", rs.getString("TARIKH_DAFTAR_FAIL") == null ? "": Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "": rs.getString("KETERANGAN"));

				maklumatSenarai.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	// return list senarai
	public Vector getMaklumatSenarai() {
		return maklumatSenarai;
	}

	// set maklumat fail master
	public void setMaklumatFailMaster(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatFailMaster = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.TUJUAN,C.TARIKH_AGIHAN,C.NO_RUJUKAN_KJP,C.NO_RUJUKAN_LAIN,A.ID_NEGERI,D.NAMA_KEMENTERIAN,F.NAMA_AGENSI,G.NAMA_SUBURUSAN,A.ID_SUBURUSAN,A.TARIKH_DAFTAR_FAIL"
				+ " ,RN.NAMA_NEGERI FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN C, TBLRUJKEMENTERIAN D, TBLRUJAGENSI F, TBLRUJSUBURUSAN G,TBLRUJNEGERI RN "
				+ "WHERE A.ID_FAIL = B.ID_FAIL(+) AND B.ID_PERMOHONAN = C.ID_PERMOHONAN(+) "
				+ "AND A.ID_KEMENTERIAN = D.ID_KEMENTERIAN(+) "
				+ "AND C.ID_AGENSI = F.ID_AGENSI(+)"
				+ "AND A.ID_SUBURUSAN = G.ID_SUBURUSAN(+)"
				+" AND RN.ID_NEGERI=A.ID_NEGERI "
				+ "AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("txtNoFailSeksyen", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("txtTajuk", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN").toUpperCase());
				h.put("txdTarikhSurKJP", rs.getDate("TARIKH_AGIHAN") == null ? "" : Format.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("txtNoFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("txtNoFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("txtNamaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("txtNamaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("txtNamaSuburusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("txdTarikhBukaFail", rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("txdNamaNegeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));

				maklumatFailMaster.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getMaklumatFailMaster() {
		return maklumatFailMaster;
	}

	public String getIdBayaranByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String idBayaran = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT ID_BAYARAN FROM TBLHTPBAYARAN WHERE ID_PERMOHONAN = '" + idPermohonan + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				idBayaran = rs.getString("ID_BAYARAN");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idBayaran;
	}
	
	public String getIdLetakHakByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String idLetakHak = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT ID_LETAKHAK FROM TBLHTPLETAKHAK WHERE ID_PERMOHONAN = '" + idPermohonan + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				idLetakHak = rs.getString("ID_LETAKHAK");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idLetakHak;
	}
	
	public String getIdBorang30AByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String idBorang30A = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			String sql = "SELECT ID_BORANG30A FROM TBLHTPBORANG30A WHERE ID_PERMOHONAN = '" + idPermohonan + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()){
				idBorang30A = rs.getString("ID_BORANG30A");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return idBorang30A;
	}

	public Vector getMaklumatLetakHak() {
		return maklumatLetakHak;
	}

	public void setMaklumatLetakHak(String idLetakHak) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatLetakHak = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_AKTA, TARIKH_KUATKUASA, TAJUK_AKTA, NO_SAMANMULA, NAMA_MAHKAMAH, TARIKH_PERINTAH FROM TBLHTPLETAKHAK WHERE ID_LETAKHAK = '" + idLetakHak + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("NO_AKTA", rs.getString("NO_AKTA") == null ? "" : rs.getString("NO_AKTA").toUpperCase());
				h.put("TARIKH_KUATKUASA", rs.getDate("TARIKH_KUATKUASA") == null ? "" : Format.format(rs.getDate("TARIKH_KUATKUASA")));				
				h.put("TAJUK_AKTA", rs.getString("TAJUK_AKTA") == null ? "" : rs.getString("TAJUK_AKTA").toUpperCase());
				h.put("NO_SAMANMULA", rs.getString("NO_SAMANMULA") == null ? "" : rs.getString("NO_SAMANMULA").toUpperCase());
				h.put("NAMA_MAHKAMAH", rs.getString("NAMA_MAHKAMAH") == null ? "" : rs.getString("NAMA_MAHKAMAH").toUpperCase());
				h.put("TARIKH_PERINTAH", rs.getDate("TARIKH_PERINTAH") == null ? "" : Format.format(rs.getDate("TARIKH_PERINTAH")));
				
				maklumatLetakHak.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getMaklumatBayaran() {
		return maklumatBayaran;
	}

	public void setMaklumatBayaran(String idBayaran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatBayaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_CARABAYAR, NO_BAYARAN, JUMLAH_BAYARAN, NO_RESIT, AMAUN_ATAS_RESIT, TARIKH_RESIT, TARIKH_HANTAR_RESIT, TARIKH_TERIMA FROM TBLHTPBAYARAN WHERE ID_BAYARAN = '" + idBayaran + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_CARABAYAR",rs.getString("ID_CARABAYAR") == null ? "999999" : rs.getString("ID_CARABAYAR").toUpperCase());
				h.put("NO_BAYARAN", rs.getString("NO_BAYARAN") == null ? "" : rs.getString("NO_BAYARAN").toUpperCase());
				h.put("JUMLAH_BAYARAN",rs.getString("JUMLAH_BAYARAN") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("JUMLAH_BAYARAN"))));
				h.put("NO_RESIT", rs.getString("NO_RESIT") == null ? "" : rs.getString("NO_RESIT").toUpperCase());
				h.put("AMAUN_ATAS_RESIT",rs.getString("AMAUN_ATAS_RESIT") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("AMAUN_ATAS_RESIT"))));
				h.put("TARIKH_RESIT", rs.getDate("TARIKH_RESIT") == null ? "": Format.format(rs.getDate("TARIKH_RESIT")));
				h.put("TARIKH_HANTAR_RESIT",rs.getDate("TARIKH_HANTAR_RESIT") == null ? "" : Format.format(rs.getDate("TARIKH_HANTAR_RESIT")));
				h.put("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA") == null ? "": Format.format(rs.getDate("TARIKH_TERIMA")));
				
				maklumatBayaran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getMaklumatBorang30A() {
		return maklumatBorang30A;
	}

	public void setMaklumatBorang30A(String idBorang30A) throws Exception {
		Db db = null;
		String sql = "";

		try {
			maklumatBorang30A = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TARIKH_TERIMA, TARIKH_TANDATANGAN_PTP, TARIKH_HANTAR, TARIKH_DAFTAR FROM TBLHTPBORANG30A WHERE ID_BORANG30A = '" + idBorang30A + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("TARIKH_TERIMA", rs.getDate("TARIKH_TERIMA") == null ? "": Format.format(rs.getDate("TARIKH_TERIMA")));
				h.put("TARIKH_TANDATANGAN_PTP", rs.getDate("TARIKH_TANDATANGAN_PTP") == null ? "": Format.format(rs.getDate("TARIKH_TANDATANGAN_PTP")));
				h.put("TARIKH_HANTAR", rs.getDate("TARIKH_HANTAR") == null ? "": Format.format(rs.getDate("TARIKH_HANTAR")));
				h.put("TARIKH_DAFTAR", rs.getDate("TARIKH_DAFTAR") == null ? "": Format.format(rs.getDate("TARIKH_DAFTAR")));
				
				maklumatBorang30A.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanAkta(String idPermohonan, String idLetakhak, String txtNoAkta, String txdTarikhKuatkuasa, String txtTajukAkta, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPLETAKHAK
			if ("".equals(idLetakhak)){
				//SAVE
				
				long id = DB.getNextID("TBLHTPLETAKHAK_SEQ");
				r.add("ID_LETAKHAK", id);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("NO_AKTA", txtNoAkta);
				if (!"".equals(txdTarikhKuatkuasa)){
					r.add("TARIKH_KUATKUASA", r.unquote("to_date('" + txdTarikhKuatkuasa + "','dd/MM/yyyy')"));
				}				
				r.add("TAJUK_AKTA", txtTajukAkta);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPLETAKHAK");
				stmt.executeUpdate(sql);
				
			} else {
				//UPDATE
				
				r.update("ID_LETAKHAK", idLetakhak);
				
				r.add("NO_AKTA", txtNoAkta);
				if (!"".equals(txdTarikhKuatkuasa)){
					r.add("TARIKH_KUATKUASA", r.unquote("to_date('" + txdTarikhKuatkuasa + "','dd/MM/yyyy')"));
				}				
				r.add("TAJUK_AKTA", txtTajukAkta);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPLETAKHAK");
				stmt.executeUpdate(sql);
				
			}

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanPerintah(String idPermohonan, String idLetakhak, String txtNoSamanPemula, String txtNamaMahkamah, String txdTarikhPerintah, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPLETAKHAK
			if ("".equals(idLetakhak)){
				//SAVE
				
				long id = DB.getNextID("TBLHTPLETAKHAK_SEQ");
				r.add("ID_LETAKHAK", id);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("NO_SAMANMULA", txtNoSamanPemula);
				r.add("NAMA_MAHKAMAH", txtNamaMahkamah);
				if (!"".equals(txdTarikhPerintah)){
					r.add("TARIKH_PERINTAH", r.unquote("to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')"));
				}

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPLETAKHAK");
				stmt.executeUpdate(sql);
				
			} else {
				//UPDATE
				
				r.update("ID_LETAKHAK", idLetakhak);
				
				r.add("NO_SAMANMULA", txtNoSamanPemula);
				r.add("NAMA_MAHKAMAH", txtNamaMahkamah);
				if (!"".equals(txdTarikhPerintah)){
					r.add("TARIKH_PERINTAH", r.unquote("to_date('" + txdTarikhPerintah + "','dd/MM/yyyy')"));
				}

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPLETAKHAK");
				stmt.executeUpdate(sql);
				
			}

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanBayaran(String idPermohonan, String idBayaran,
			String idCaraBayar, String txtNoCek, String txtJumlahBayar, String txtNoResit,
			String txtAmaunResit, String txdTarikhResit, String txdHantarResit, String txdTarikhTerima,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPBAYARAN
			if ("".equals(idBayaran)){
				//SAVE
				
				// TBLHTPBAYARAN
				long id = DB.getNextID("TBLHTPBAYARAN_SEQ");
				r.add("ID_BAYARAN", id);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_CARABAYAR", idCaraBayar.equals("99999") ? "" : idCaraBayar);
				r.add("NO_BAYARAN", txtNoCek);
				r.add("JUMLAH_BAYARAN", Utils.RemoveSymbol(txtJumlahBayar));
				r.add("NO_RESIT", txtNoResit);
				r.add("AMAUN_ATAS_RESIT", Utils.RemoveSymbol(txtAmaunResit));
				if (!"".equals(txdTarikhResit)){
					r.add("TARIKH_RESIT", r.unquote("to_date('" + txdTarikhResit + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhTerima)){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + txdTarikhTerima + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdHantarResit)){
					r.add("TARIKH_HANTAR_RESIT", r.unquote("to_date('" + txdHantarResit + "','dd/MM/yyyy')"));
				}

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPBAYARAN");
				stmt.executeUpdate(sql);
				
			} else {
				//UPDATE
				
				r.update("ID_BAYARAN", idBayaran);

				r.add("ID_CARABAYAR", idCaraBayar.equals("99999") ? "" : idCaraBayar);
				r.add("NO_BAYARAN", txtNoCek);
				r.add("JUMLAH_BAYARAN", Utils.RemoveSymbol(txtJumlahBayar));
				r.add("NO_RESIT", txtNoResit);
				r.add("AMAUN_ATAS_RESIT", Utils.RemoveSymbol(txtAmaunResit));
				if (!"".equals(txdTarikhResit)){
					r.add("TARIKH_RESIT", r.unquote("to_date('" + txdTarikhResit + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhTerima)){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + txdTarikhTerima + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdHantarResit)){
					r.add("TARIKH_HANTAR_RESIT", r.unquote("to_date('" + txdHantarResit + "','dd/MM/yyyy')"));
				}

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPBAYARAN");
				stmt.executeUpdate(sql);
				
			}

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}		
	}

	public void simpanBorang30A(String idPermohonan, String idBorang30A,
			String txdTarikhTerimaBorang30A, String txdTarikhTandatanganPTP, String txdTarikhHantar, String txdTarikhDaftar30A,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPBAYARAN
			if ("".equals(idBorang30A)){
				//SAVE
				
				// TBLHTPBORANG30A
				long id = DB.getNextID("TBLHTPBORANG30A_SEQ");
				r.add("ID_BORANG30A", id);
				r.add("ID_PERMOHONAN", idPermohonan);
				if (!"".equals(txdTarikhTandatanganPTP)){
					r.add("TARIKH_TANDATANGAN_PTP", r.unquote("to_date('" + txdTarikhTandatanganPTP + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhTerimaBorang30A)){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + txdTarikhTerimaBorang30A + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhHantar)){
					r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhDaftar30A)){
					r.add("TARIKH_DAFTAR", r.unquote("to_date('" + txdTarikhDaftar30A + "','dd/MM/yyyy')"));
				}

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPBORANG30A");
				stmt.executeUpdate(sql);
				
			} else {
				//UPDATE
				
				r.update("ID_BORANG30A", idBorang30A);
				
				if (!"".equals(txdTarikhTandatanganPTP)){
					r.add("TARIKH_TANDATANGAN_PTP", r.unquote("to_date('" + txdTarikhTandatanganPTP + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhTerimaBorang30A)){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + txdTarikhTerimaBorang30A + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhHantar)){
					r.add("TARIKH_HANTAR", r.unquote("to_date('" + txdTarikhHantar + "','dd/MM/yyyy')"));
				}
				if (!"".equals(txdTarikhDaftar30A)){
					r.add("TARIKH_DAFTAR", r.unquote("to_date('" + txdTarikhDaftar30A + "','dd/MM/yyyy')"));
				}

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPBORANG30A");
				stmt.executeUpdate(sql);
				
			}

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}		
	}

}
