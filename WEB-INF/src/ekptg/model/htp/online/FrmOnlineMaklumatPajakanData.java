/**
 * 
 */
package ekptg.model.htp.online;

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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * @author Firzan.Fir
 *
 */
public class FrmOnlineMaklumatPajakanData {
	
	private Vector<Hashtable<String, String>> senaraiHakmilik = null;
	private Vector beanMaklumatLampiran = null;
	private Vector listLampiran = null;
	private Vector beanMaklumatTanah = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.online.FrmOnlineMaklumatPajakanData.class);
	//private FrmPajakanMemorandumJemaahMenteriData mjmData = null;
	private String sql = "";
	private Db db = null;
	private Connection conn = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setListHakmilik(String idPermohonan) throws Exception {
		try {
			senaraiHakmilik = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIKURUSAN, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, " +
					" A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, " +
					" A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI,A.LUAS,A.LUAS_BERSAMAAN, " +
					" REPLACE(RJH.KOD_JENIS_HAKMILIK,'00','') KOD_JENIS_HAKMILIK, RL.KOD_LUAS KOD_LUAS, " +
					" RLB.KOD_LUAS KOD_LUASBERSAMAAN, NVL(GIS.UPI,'N') GIS_HANTAR, NVL(GIS.LATITUDE,'N') GIS_CHARTING " +
					
					" FROM TBLHTPHAKMILIKURUSAN A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D,TBLRUJMUKIM E, " +
					" TBLRUJJENISHAKMILIK RJH, TBLRUJLUAS RL,TBLRUJLUAS RLB,TBLINTGIS GIS, (SELECT MT.ID_HAKMILIKURUSAN, " +
					" GETUPI(RN.KOD_NEGERI,RD.KOD_DAERAH_UPI,RM.KOD_MUKIM_UPI,'000',RJH.STATUS_HAKMILIK,MT.NO_LOT, " +
					" MT.NO_HAKMILIK,RJH.KOD_JENIS_HAKMILIK ) UPI " +
					" FROM TBLHTPHAKMILIKURUSAN MT,TBLRUJJENISHAKMILIK RJH, TBLRUJNEGERI RN,TBLRUJDAERAH RD," +
					" TBLRUJMUKIM RM " +
					
					" WHERE MT.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK AND MT.ID_NEGERI = RN.ID_NEGERI " +
					" AND MT.ID_DAERAH = RD.ID_DAERAH AND MT.ID_MUKIM = RM.ID_MUKIM) UP " +
					" WHERE A.ID_LOT = B.ID_LOT AND A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH " +
					" AND A.ID_MUKIM = E.ID_MUKIM AND A.ID_JENISHAKMILIK = RJH.ID_JENISHAKMILIK " +
					" AND A.ID_LUAS = RL.ID_LUAS AND A.ID_LUAS_BERSAMAAN = RLB.ID_LUAS " +
					" AND A.ID_HAKMILIKURUSAN = UP.ID_HAKMILIKURUSAN AND UP.UPI = GIS.UPI(+) " +
					" AND GIS.STATUS_TANAH(+) = 7 AND A.ID_PERMOHONAN = '" + idPermohonan + "' ";
					
			
			
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				//h.put("bil", String.valueOf(bil));
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
			if (bil == 1){
				h = new Hashtable();
				h.put("idHakmilikUrusan","");
				h.put("peganganHakmilik","");
				h.put("lot","");
				h.put("noLot","");
				h.put("noHakmilik","");
				h.put("noWarta","");
				h.put("mukim","");
				h.put("daerah","");
				h.put("negeri","");
				h.put("kodJenisHakmilik","");
				h.put("luas","");
				h.put("kodLuas","");
				h.put("luasBersamaan","");
				h.put("kodLuasBersamaan","");
				h.put("gisHantar", rs.getString("GIS_HANTAR"));
				h.put("gisCharting", rs.getString("GIS_CHARTING"));
				senaraiHakmilik.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}	
	}
	
	/*public void seterusnya(String idFail, String idPermohonan, String subUrusan, HttpSession session) throws Exception {
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
		/*	r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus); //MEMORANDUM JEMAAH MENTERI		
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
		
	}*/

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
	

	public Vector<Hashtable<String,String>> getLampiranByDokumen(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 Vector<Hashtable<String,String>> listLampiran = new Vector<Hashtable<String,String>>();
		 	try {
		 		db = new Db();
		 		Statement stmt = db.getStatement();
		 		SQLRenderer r = new SQLRenderer();
		 		r.add("a.id_Lampiran");
		 		r.add("a.nama_Fail");
		 		r.add("a.jenis_Mime");
		 		r.add("FDD.Id_Dokumen");
			    r.add("FDD.Nama_Pengirim");
			    r.add("FDD.Tajuk_Dokumen");
		        //r.add("a.id_Dokumen",id);
		 		r.add("A.ID_DOKUMEN",r.unquote("FDD.ID_DOKUMEN"));
		 		r.add("P.ID_FAIL",r.unquote("FDD.ID_FAIL"));
		 		r.add("A.ID_LAMPIRAN",id);
		        sql = r.getSQLSelect("Tblpfdrujlampiran a,Tblpfddokumen FDD,tblpermohonan p");
		        ResultSet rs = stmt.executeQuery(sql);
		        Hashtable<String,String> h;
		        int bil = 1;
		        while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("idDokumen",rs.getString("Id_Dokumen"));
		    	  h.put("perihal", rs.getString("Nama_Pengirim"));	    	  
		    	  h.put("keterangan", Utils.isNull(rs.getString("Tajuk_Dokumen")));
		    	  h.put("idLampiran", rs.getString("id_Lampiran"));
		    	  h.put("namaFail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		      }

		    } finally {
		      if (db != null) db.close();
		    }
		    return listLampiran;
		    
		}
	
	public Vector<Hashtable<String, String>> getLampiranByPermohonan(String id)throws Exception {
		 Db db = null;
		 String sql = "";
		 Vector<Hashtable<String, String>> listLampiran = new Vector<Hashtable<String, String>>();
		 	try {
		 		db = new Db();
		 		Statement stmt = db.getStatement();
		 		SQLRenderer r = new SQLRenderer();
		 		r.add("a.id_Lampiran");
		 		r.add("a.nama_Fail");
		 		r.add("a.jenis_Mime");
		 		r.add("FDD.Id_Dokumen");
			    r.add("FDD.Nama_Pengirim");
			    r.add("FDD.Tajuk_Dokumen");
		        //r.add("a.id_Dokumen",id);
		 		r.add("A.ID_DOKUMEN",r.unquote("FDD.ID_DOKUMEN"));
		 		r.add("P.ID_FAIL",r.unquote("FDD.ID_FAIL"));
		 		r.add("P.ID_PERMOHONAN",id);
		        sql = r.getSQLSelect("Tblpfdrujlampiran a,Tblpfddokumen FDD,tblpermohonan p");
		        myLog.info(sql);
		        ResultSet rs = stmt.executeQuery(sql);
		        Hashtable<String, String> h;
		        int bil = 1;
		        //int count = 0;
		        while (rs.next()) {
		    	  h = new Hashtable<String, String>();
		    	  h.put("bil",String.valueOf(bil));
		    	  h.put("idDokumen",rs.getString("Id_Dokumen"));
		    	  h.put("perihal", rs.getString("Nama_Pengirim"));	    	  
		    	  h.put("keterangan", Utils.isNull(rs.getString("Tajuk_Dokumen")));
		    	  h.put("idLampiran", rs.getString("id_Lampiran"));
		    	  h.put("namaFail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  //count++;
		      }
//		      if (count == 0){
//		    	  h = new Hashtable();
//		    	  h.put("bil","");
//		    	  h.put("id_Lampiran", "");
//		    	  h.put("nama_Fail","Tiada rekod.");
//		    	  h.put("jenis_Mime", "");
//		    	  listLampiran.addElement(h);
//		      }
		    } finally {
		      if (db != null) db.close();
		    }
		    return listLampiran;
		    
		}
	
	public String getIdHakmilikAgensiByPeganganHakmilik(String noLot, String noHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			/*sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
				+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '" + peganganHakmilik.toUpperCase() + "'";
			*/
			
			/*sql= " SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI " +
					" WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK " +
					" AND UPPER(TBLHTPHAKMILIK.NO_LOT) = '"+ noLot +"' AND TBLHTPHAKMILIK.NO_HAKMILIK LIKE '%"+ noHakmilik +"'";
					*/
			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, " +
					" E.NAMA_MUKIM, D.NAMA_DAERAH,C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, " +
					" A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI,HM.kod_jenis_hakmilik"+
//				    "(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH " +
//					" WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK) KOD_JENIS_HAKMILIK " +
					" FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK HM" +
					" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = HM.ID_JENISHAKMILIK AND A.NO_LOT = '" + noLot + "' AND A.NO_HAKMILIK LIKE '%" + noHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return (String)rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}
			
		}  catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	//Copy dari internal
	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			/*sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
				+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
				+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
				+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
				+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
				+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
				+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
				
				+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, " 
				+ " TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, "
				+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, "
				+ " TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
				+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
				+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			*/
			
			sql = "SELECT A.ID_HAKMILIK,A.PEGANGAN_HAKMILIK,A.NO_HAKMILIK,A.NO_LOT,A.LUAS, A.ID_LUAS, A.NO_WARTA, " +
					" A.TARIKH_WARTA, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, B.KOD_LOT, " +
					" B.KETERANGAN AS JENIS_LOT, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, " +
					" H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, " +
					" K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS, A.ID_KEMENTERIAN, A.ID_AGENSI" +
					" ,A.ID_NEGERI"+
					" FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, " +
					" TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, " +
					" TBLRUJLUAS K " +
					
					" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) " +
					" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) " +
					" AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS " +
					" AND A.ID_HAKMILIK = '" + idHakmilik + "'";
			
			myLog.info("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("jenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("JENIS_HAKMILIK") == null? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " - " + rs.getString("JENIS_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("KOD_LOT") == null || rs.getString("JENIS_LOT") == null? "" : rs.getString("KOD_LOT").toUpperCase() + " - " + rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));				
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "0" : rs.getString("ID_LUAS"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());	
				h.put("idNegeriTanah", rs.getString("ID_NEGERI"));	

				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				beanMaklumatTanah.addElement(h);
				bil++;
			}if (bil == 1){
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");				
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		}  catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	/*public void paparMaklumatTanah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "	SELECT A.ID_HAKMILIKURUSAN, A.PEGANGAN_HAKMILIK, A.NO_HAKMILIK, A.NO_LOT, A.LUAS, A.ID_LUAS, A.NO_WARTA, " +
					" A.TARIKH_WARTA, B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, " +
					" F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, " +
					" B.KETERANGAN AS JENIS_LOT, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, " +
					" H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS " +
					
					" FROM TBLHTPHAKMILIKURUSAN A, TBLPERMOHONAN TB, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, " +
					" TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJLUAS K " +
					
					" WHERE A.ID_PERMOHONAN = TB.ID_PERMOHONAN AND A.ID_LOT = B.ID_LOT(+) " +
					" AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
					" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) " +
					" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) " +
					" AND A.ID_LUAS = K.ID_LUAS AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikUrusan", rs.getString("ID_HAKMILIKURUSAN") == null ? "" : rs.getString("ID_HAKMILIKURUSAN"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("jenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("JENIS_HAKMILIK") == null? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " - " + rs.getString("JENIS_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("KOD_LOT") == null || rs.getString("JENIS_LOT") == null? "" : rs.getString("KOD_LOT").toUpperCase() + " - " + rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));				
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "0" : rs.getString("ID_LUAS"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				beanMaklumatTanah.addElement(h);
				bil++;
			}if (bil == 1){
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");				
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		}  catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	*/
	public void setMaklumatTanah1(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT HMA.ID_HAKMILIKAGENSI, HM.ID_HAKMILIK, NULL AS ID_HAKMILIKSEMENTARA, HM.PEGANGAN_HAKMILIK,"
				+ " HM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK, HM.ID_LOT,"
				+ " RUJLOT.KETERANGAN AS JENIS_LOT, HM.NO_LOT, HMA.ID_LUAS_BERSAMAAN, HMA.LUAS_BERSAMAAN,"
				+ " RUJLUAS.KETERANGAN AS JENIS_LUAS, HM.NO_WARTA, HM.TARIKH_WARTA, HM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM,"
				+ " HM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, HM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI, HM.ID_KATEGORI AS ID_KATEGORI,"
				+ " RUJKATEGORI.KETERANGAN AS KATEGORI, HM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, HM.KEGUNAAN_TANAH,"
				+ " HM.SYARAT, HM.SEKATAN, HMA.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN"
				
				+ " FROM TBLHTPHAKMILIKAGENSI HMA, TBLHTPHAKMILIK HM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, " 
				+ " TBLRUJLUAS RUJLUAS, TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, "
				+ " TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, "
				+ " TBLRUJKEMENTERIAN RUJKEMENTERIAN"
				
				+ " WHERE HMA.ID_HAKMILIK = HM.ID_HAKMILIK AND HM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND HM.ID_LOT = RUJLOT.ID_LOT(+)"
				+ " AND HMA.ID_LUAS_BERSAMAAN = RUJLUAS.ID_LUAS(+) AND HM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND HM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+)"
				+ " AND HM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) AND HM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND HM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+)"
				+ " AND HMA.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
				+ " AND HMA.ID_HAKMILIKAGENSI = '" + idHakmilikAgensi + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilikAgensi", rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs.getString("ID_HAKMILIKAGENSI"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("NO_HAKMILIK") == null ? 
						"" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " " + rs.getString("NO_HAKMILIK"));
				h.put("noLot",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("lot",(rs.getString("JENIS_LOT") == null ? "" : rs.getString("JENIS_LOT").toUpperCase())+ " "
						+ (rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT")));
				h.put("luasLot",(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs
						.getDouble("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : 
						rs.getString("JENIS_LUAS")));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				//h.put("kegunaanTanah", rs.getString("KEGUNAAN_TANAH") == null ? "" : rs.getString("KEGUNAAN_TANAH").toUpperCase());
				//--mula------
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("idLot", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? "": rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luas", (rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils.formatLuas(rs.getDouble
						("LUAS_BERSAMAAN"))) + " " + (rs.getString("JENIS_LUAS") == null ? "" : rs.getString("JENIS_LUAS")));
				h.put("idDaerah",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("idNegeri",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("idSubKategori",rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				//--tamat------
				
				if (rs.getString("NO_HAKMILIK") != null && rs.getString("NO_WARTA") == null){
					h.put("statusRizab", "MILIK");
				} else if (rs.getString("NO_HAKMILIK") == null && rs.getString("NO_WARTA") != null){
					h.put("statusRizab", "RIZAB");
				} else {
					h.put("statusRizab", "");
				}
				beanMaklumatTanah.addElement(h);
				bil++;
			}
			
			if (bil == 1){
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("noHakmilik", "");
				h.put("noLot", "");
				h.put("luasLot", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("mukim", "");
				h.put("daerah", "");
				h.put("negeri", "");				
				h.put("kategoriTanah", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kementerian", "");
				h.put("agensi", "");
				h.put("kegunaanTanah", "");
				h.put("statusRizab", "");
				beanMaklumatTanah.addElement(h);
			}

		}  catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			}	finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}
	
	//SENARAI SEMAK
	public Vector getSenaraiSemak(String idPermohonan, String kategori) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
				sql = "SELECT A.ID_RUJSENARAISEMAK, A.KETERANGAN,"
					+ " CASE WHEN A.ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG, "
					+ " CASE WHEN B.KETERANGAN = 'INDIVIDU' THEN '1' ELSE '2' END AS ID_KATEGORIPEMOHON "
					+ " FROM TBLPHPRUJSENARAISEMAK A, TBLRUJKATEGORIPEMOHON B "
					+ " WHERE A.ID_KATEGORIPEMOHON = B.ID_KATEGORIPEMOHON AND A.FLAG_AKTIF = 'Y' AND B.KETERANGAN = '" + kategori + "' ";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			myLog.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return senaraiSemak;
	}
		
	//UPDATE SENARAI SEMAK
	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";		
		
		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();
			
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);
			
			for (int i = 0; i < semaks.length; i++) {
			 	r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();
			
			//AuditTrail.logActivity("1610198", "4", null, session, "UPD", "FAIL [" + idPermohonan + "] DIKEMASKINI");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateTanah(String idPermohonan, String idHakmilik,HttpSession session) throws Exception {


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
			
			//TBLHTPHAKMILIKURUSAN
			sql = "SELECT PEGANGAN_HAKMILIK, NO_HAKMILIK, NO_WARTA, TARIKH_WARTA, NO_LOT, ID_NEGERI, ID_DAERAH, " +
					" ID_MUKIM, ID_LOT, ID_JENISHAKMILIK, ID_KATEGORI " +
					" FROM TBLHTPHAKMILIK WHERE ID_HAKMILIK = '" + idHakmilik + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			r.update("ID_PERMOHONAN", idPermohonan);
			if (rs.next()){
				r.add("PEGANGAN_HAKMILIK", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				r.add("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				r.add("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				r.add("TARIKH_WARTA", rs.getDate("TARIKH_WARTA") == null ? "" : rs.getDate("TARIKH_WARTA"));
				r.add("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				r.add("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "0" : rs.getString("ID_JENISHAKMILIK"));
				if(!rs.getString("ID_KATEGORI").equals(null) && !rs.getString("ID_KATEGORI").equals("0") ){
					r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
				}else{
					r.add("ID_KATEGORI", "1");
				}
			}	
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
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

}
