package ekptg.model.str;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujbandar;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.view.str.FrmMaklumatStrata;

public class FrmSTRSkimBgnnKhasData {
	static Logger myLogger = Logger.getLogger(FrmSTRSkimBgnnKhasData.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Vector carian(String paramNegeri, String paramKodLot, String paramNoLot, String paramNoCF, String paramNamaPemaju,
			String paramNamaSkim,String paramNoStrata) throws Exception {

		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			

			if ((!"".equalsIgnoreCase(paramNegeri) && (!"-1".equalsIgnoreCase(paramNegeri)))) {
				SQL_SEARCH += " AND UPPER(D.ID_NEGERI) ="+ paramNegeri.toUpperCase();
			}
			if (!"".equalsIgnoreCase(paramNoStrata)) {
				SQL_SEARCH += " AND UPPER(B.NO_HAKMILIK)  LIKE '%" + paramNoStrata.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramKodLot)) {
				SQL_SEARCH += " AND UPPER(F.KOD_LOT) = " + paramKodLot.toUpperCase();
			}
			if (!"".equalsIgnoreCase(paramNoLot)) {
				SQL_SEARCH += " AND UPPER(B.NO_LOT) LIKE '%" + paramNoLot.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramNoCF)) {
				SQL_SEARCH += " AND UPPER(C.NO_CF) LIKE '%" + paramNoCF.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramNamaPemaju)) {
				SQL_SEARCH += " AND UPPER(A.NAMA_PEMAJU) LIKE '%" + paramNamaPemaju.toUpperCase() + "%' ";
			}
			if (!"".equalsIgnoreCase(paramNamaSkim)) {
				SQL_SEARCH += " AND UPPER(A.NAMA_SKIM) LIKE '%" + paramNamaSkim.toUpperCase() + "%' ";
			}

			int iCount = 1;
			String RS_Negeri = "", RS_NegeriHm = "", RS_NoCF = "", RS_NoKelulusanKhas = "", RS_NoFailMajlis = "", RS_NamaPemaju = "", RS_NamaSkim = "", RS_KodLot = "", RS_NoLot = "", RS_Status = "", RS_Id = "",RS_FlagCf = "",RS_Cf="", RS_Khas="",RS_NamaPemilik = "";
			Date RS_TarikhCF;
			String noHakmilik = "";
			
//					sql = " SELECT D.NAMA_NEGERI AS NEGERI, A.NO_FAILMAJLIS, A.NAMA_PEMAJU, A.NAMA_SKIM, d2.NAMA_NEGERI AS NEGERIHM, "+
//							" F.KETERANGAN KODLOT, B.NO_LOT, E.PERIHAL STATUSSTRATA, A.ID_STRATA, "+
//							" A.FLAG_CF, "+
//							" B.NO_HAKMILIK,  "+
//							" (SELECT REPLACE (wm_concat (   '<br/>' || no_cf || ' : ' || TO_CHAR (tarikh_cf, 'DD/MM/YYYY') ),',', NULL ) " +
//							"AS cf " +
//							"FROM tblstrcf cf " +
//							"WHERE cf.id_tblstrbangunankhas = a.id_strata) AS cf," +
//							"(SELECT REPLACE (wm_concat ('<br/>' || no_kelulusankhas), ',', " +
//							"NULL ) FROM tblstrcf cf " +
//							"WHERE cf.id_tblstrbangunankhas = a.id_strata) AS khas," +
//							" RTRIM (XMLAGG (XMLELEMENT (e, CASE WHEN g.nama_pemilik IS NOT NULL THEN g.nama_pemilik || ', ' END )" +
//							").EXTRACT ('//text()'), " +
//							"',') AS nama_pemilik " +
//							"FROM TBLSTRBANGUNANKHAS A, "+
//							" TBLSTRHAKMILIK B, "+
//							" TBLSTRCF C, "+
//							" TBLRUJNEGERI D, "+
//							" TBLRUJNEGERI D2, "+
//							" TBLRUJSTATUSSTRATA E, "+
//							" TBLRUJLOT F, "+
//							" TBLSTRPEMILIK G "+
//							" WHERE A.ID_STRATA = B.ID_TBLSTRBANGUNANKHAS(+) "+
//							" AND B.ID_NEGERI = D.ID_NEGERI(+) "+
//							" AND A.ID_NEGERI = D2.ID_NEGERI(+) "+
//							" AND B.ID_LOT = F.ID_LOT(+) "+
//							" AND A.ID_TBLRUJSTATUSSTRATA = E.ID(+) "+
//							" AND A.ID_STRATA = C.ID_TBLSTRBANGUNANKHAS(+) "+
//							" AND A.ID_STRATA = G.ID_TBLSTRBANGUNANKHAS(+) " + SQL_SEARCH +
//							//" AND A.ID_STRATA = '16172823' "+
//							" GROUP BY D.NAMA_NEGERI, D2.NAMA_NEGERI, A.NO_FAILMAJLIS, A.NAMA_PEMAJU, A.NAMA_SKIM, "+
//							" F.KETERANGAN, B.NO_LOT, E.PERIHAL, A.ID_STRATA, "+
//							" A.FLAG_CF, B.NO_HAKMILIK,A.TARIKH_MASUK ORDER BY  A.TARIKH_MASUK DESC ";
			
			sql = " SELECT D.NAMA_NEGERI AS NEGERI, A.NO_FAILMAJLIS, A.NAMA_PEMAJU, A.NAMA_SKIM, D2.NAMA_NEGERI AS NEGERIHM, F.KETERANGAN KODLOT," +
					" B.NO_LOT, E.PERIHAL STATUSSTRATA, A.ID_STRATA, A.FLAG_CF, B.NO_HAKMILIK, NO_CF  || CASE WHEN NO_CF IS NOT NULL THEN ' : ' END || TO_CHAR (c.TARIKH_CF, 'DD/MM/YYYY') AS CF," +
					" NO_KELULUSANKHAS  ||CASE WHEN NO_KELULUSANKHAS IS NOT NULL THEN ' : ' || TO_CHAR (c.TARIKH_CF, 'DD/MM/YYYY') END AS KHAS FROM TBLSTRBANGUNANKHAS A, TBLSTRHAKMILIK B," +
					" TBLSTRCF C, TBLRUJNEGERI D, TBLRUJNEGERI D2, TBLRUJSTATUSSTRATA E, TBLRUJLOT F, TBLRUJNEGERI P, TBLRUJBANDAR BANDAR " +
					" WHERE A.ID_STRATA = B.ID_TBLSTRBANGUNANKHAS(+)" +
					" AND B.ID_NEGERI = D.ID_NEGERI(+) AND A.ID_NEGERI = D2.ID_NEGERI(+) AND A.ID_KODNEGERIPEMAJU = P.ID_NEGERI(+) " +
					" AND A.ID_KODBANDARPEMAJU = BANDAR.ID_BANDAR(+) " +
					" AND B.ID_LOT = F.ID_LOT(+) AND A.ID_TBLRUJSTATUSSTRATA = E.ID(+) AND A.ID_STRATA = C.ID_TBLSTRBANGUNANKHAS(+) " + SQL_SEARCH +
					" ORDER BY A.TARIKH_MASUK DESC ";
			
			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_Negeri = rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI");
				RS_NegeriHm = rs.getString("NEGERIHM") == null ? "" : rs.getString("NEGERIHM");
//				RS_NoCF = rs.getString(2) == null ? "" : rs.getString(2);
//				RS_TarikhCF = (Date)rs.getDate(3);
//				RS_NoKelulusanKhas = rs.getString(4) == null ? "" : rs.getString(4);
				RS_NoFailMajlis = rs.getString("NO_FAILMAJLIS") == null ? "" : rs.getString("NO_FAILMAJLIS");
				RS_NamaPemaju = rs.getString("NAMA_PEMAJU") == null ? "" : rs.getString("NAMA_PEMAJU");
				RS_NamaSkim = rs.getString("NAMA_SKIM") == null ? "" : rs.getString("NAMA_SKIM");
				RS_KodLot = rs.getString("KODLOT") == null ? "" : rs.getString("KODLOT");
				RS_NoLot = rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT");
				RS_Status = rs.getString("STATUSSTRATA") == null ? "" : rs.getString("STATUSSTRATA");
				RS_Id = rs.getString("ID_STRATA") == null ? "" : rs.getString("ID_STRATA");
				RS_FlagCf = rs.getString("FLAG_CF") == null ? "" : rs.getString("FLAG_CF");
				RS_Cf = rs.getString("CF") == null ? "" : rs.getString("CF");
				RS_Khas = rs.getString("KHAS") == null ? "" : rs.getString("KHAS");
				noHakmilik = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				

				h = new Hashtable();
				h.put("No", iCount);
				h.put("Negeri", RS_Negeri);
				h.put("NegeriHm", RS_NegeriHm);
//				h.put("NoCF", RS_NoCF);
//				if(RS_TarikhCF!=null){
//					h.put("TarikhCF", sdf.format(RS_TarikhCF));
//				}
//				h.put("NoKelulusanKhas", RS_NoKelulusanKhas);
				h.put("NoFail", RS_NoFailMajlis);
				h.put("NamaPemaju", RS_NamaPemaju);
				h.put("NamaSkim", RS_NamaSkim);
				h.put("KodLot", RS_KodLot);
				h.put("NoLot", RS_NoLot);
				h.put("Status", RS_Status);
				h.put("Id", RS_Id);
				h.put("flagCf", RS_FlagCf);
				h.put("cf", RS_Cf);
				h.put("khas", RS_Khas);
				h.put("noHakmilik", noHakmilik);
				h.put("namaPemilik", RS_NamaPemilik);
				v.addElement(h);
				iCount++;
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			} finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	//public void SimpanPemilik(String id_strata,Vector vPemilikTemp,HttpSession session)throws Exception {
		public void SimpanPemilik(Hashtable data)throws Exception {
		System.out.println("************data********** : "+data);
		long idPemilik = 0;
		Db db = null;
		Connection conn = null;
		//String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//Hashtable h = (Hashtable)vPemilikTemp.get(0);
			String id_strata = (String)data.get("idStrata");
			String radioPemilik = (String)data.get("radioPemilik");
			String noKadPengenalan = (String)data.get("noKadPengenalan");
			String namaPemilikTanah = (String)data.get("namaPemilikTanah");
			String alamat1Pemilik = (String)data.get("alamat1Pemilik");
			String alamat2Pemilik = (String)data.get("alamat2Pemilik");
			String alamat3Pemilik = (String)data.get("alamat3Pemilik");
			String poskodPemilik = (String)data.get("poskodPemilik");
			String idKodnegeriPemilik = (String)data.get("idKodnegeriPemilik");
			String idKodbandarPemilik = (String)data.get("idKodbandarPemilik");
			//System.out.println("idKodbandarPemilik" +idKodbandarPemilik);
			//System.out.println("---------3----------");
			sql = "";
			//r = new SQLRenderer();
			idPemilik = DB.getNextID("TBLSTRPEMILIK_SEQ");
			//System.out.println("---------2----------");
			r.add("ID_PEMILIK", idPemilik);
			r.add("ID_TBLSTRBANGUNANKHAS", id_strata);
				r.add("NAMA_PEMILIK", namaPemilikTanah);
				r.add("ALAMAT1_PEMILIK", alamat1Pemilik);
				r.add("ALAMAT2_PEMILIK", alamat2Pemilik);
				r.add("ALAMAT3_PEMILIK", alamat3Pemilik);
				r.add("POSKOD_PEMILIK", poskodPemilik);
				r.add("ID_KODBANDARPEMILIK", idKodbandarPemilik);
				r.add("ID_KODNEGERIPEMILIK", idKodnegeriPemilik );
				r.add("NOPENGENALAN_PEMILIK", noKadPengenalan);
				r.add("JENIS_PEMILIK", radioPemilik );
//				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//				r.add("MASUK_OLEH", userId);
			// r.add("ID_KODNEGERIPEMILIK", r.unquote("SYSDATE"));
			// r.add("KEMASKINI_OLEH", userId);
				//System.out.println("---------4----------");
			sql = r.getSQLInsert("TBLSTRPEMILIK");
			myLogger.info("add TBLSTRPEMILIK ASING:" + sql);
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
	
	public String simpan(String idNegeriStr, String idLot, String noLot, String bilPetak, String noFailMajlis,
			String tarikhLulusmajlis, String idNegeriHM, String idDaerah,
			String idMukim, String idJenisHakmilik, String noHakmilik, String namaPemaju, String alamat1Pemaju,
			String alamat2Pemaju, String alamat3Pemaju, String poskodPemaju, String idKodnegeripemaju, String idKodbandarpemaju,
			String namaSkim, String alamat1Skim, String alamat2Skim, String alamat3Skim, String poskodSkim,
			String idKodnegeriskim, String idKodbandarskim, String idTblrujstatusstrata, String tarikhDaftarstrata,
			String tarikhMohonstrata, String noRujptg,String ulasanStrata,String flagCf,String flagStrata,String tarikhSifus,String tarikhCPSP,String txtSenaraiPBT,HttpSession session) throws Exception {
		long idStrBgnnKhas = 0;
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
			
			// TBLSTRBANGUNANKHAS

			sql = "";
			r = new SQLRenderer();
			idStrBgnnKhas = DB.getNextID("TBLSTRBANGUNANKHAS_SEQ");

			String TL = "to_date('" + tarikhLulusmajlis + "','dd/MM/yyyy')";
			String TMS = "to_date('" + tarikhMohonstrata + "','dd/MM/yyyy')";
			String TDS = "to_date('" + tarikhDaftarstrata + "','dd/MM/yyyy')";
			String TSF = "to_date('" + tarikhSifus + "','dd/MM/yyyy')";
			String TCPSP = "to_date('" + tarikhCPSP + "','dd/MM/yyyy')";

			r.add("ID_STRATA", idStrBgnnKhas);
			r.add("ID_NEGERI", idNegeriHM == null ? "" : idNegeriHM);
			//r.add("ID_TBLSTRHAKMILIK", idTblstrhakmilik);
			r.add("BIL_PETAK", bilPetak == null ? "" : bilPetak);
			r.add("NO_FAILMAJLIS", noFailMajlis == null ? "" : noFailMajlis);
			r.add("TARIKH_LULUSMAJLIS", r.unquote(TL));
			r.add("NAMA_PEMAJU", namaPemaju == null ? "" : namaPemaju);
			r.add("ALAMAT1_PEMAJU", alamat1Pemaju == null ? "" : alamat1Pemaju);
			r.add("ALAMAT2_PEMAJU", alamat2Pemaju == null ? "" : alamat2Pemaju);
			r.add("ALAMAT3_PEMAJU", alamat3Pemaju == null ? "" : alamat3Pemaju);
			r.add("POSKOD_PEMAJU", poskodPemaju == null ? "" : poskodPemaju);
			r.add("ID_KODBANDARPEMAJU", idKodbandarpemaju == null ? "" : idKodbandarpemaju);
			r.add("ID_KODNEGERIPEMAJU", idKodnegeripemaju == null ? "" : idKodnegeripemaju);
			r.add("NAMA_SKIM", namaSkim == null ? "" : namaSkim);
			r.add("ALAMAT1_SKIM", alamat1Skim == null ? "" : alamat1Skim);
			r.add("ALAMAT2_SKIM", alamat2Skim == null ? "" : alamat2Skim);
			r.add("ALAMAT3_SKIM", alamat3Skim == null ? "" : alamat3Skim);
			r.add("POSKOD_SKIM", poskodSkim == null ? "" : poskodSkim);
			r.add("ID_KODBANDARSKIM", idKodbandarskim == null ? "" : idKodbandarskim);
			r.add("ID_KODNEGERISKIM", idKodnegeriskim == null ? "" : idKodnegeriskim);
			r.add("ID_TBLRUJSTATUSSTRATA", idTblrujstatusstrata == null ? "" : idTblrujstatusstrata);
			r.add("TARIKH_MOHONSTRATA", r.unquote(TMS));
			r.add("TARIKH_DAFTARSTRATA", r.unquote(TDS));
			r.add("TARIKH_SIFUS", r.unquote(TSF));
			r.add("TARIKH_CPSP", r.unquote(TCPSP));
			r.add("NO_RUJUKANPTG", noRujptg == null ? "" : noRujptg);
			r.add("ULASAN_STRATA", ulasanStrata == null ? "" : ulasanStrata);
			r.add("FLAG_STRATA", flagStrata == null ? "" : flagStrata);
			r.add("ID_PEJABAT", txtSenaraiPBT == null ? "" : txtSenaraiPBT);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("MASUK_OLEH", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("KEMASKINI_OLEH", userId);

			sql = r.getSQLInsert("TBLSTRBANGUNANKHAS");
			myLogger.info("add TBLSTRBANGUNANKHAS:" + sql);
			
			stmt.executeUpdate(sql);
			r.clear();
			

			// TBLSTRHAKMILIK
			long idTblstrhakmilik = DB.getNextID("TBLSTRHAKMILIK_SEQ");
			//long idStrBgnnKhas = DB.getNextID("TBLSTRBANGUNANKHAS_SEQ");

			if((noHakmilik!=null)&& noHakmilik.length()>0){
				int noHakmilikLength = noHakmilik.length();
				for (int j = 0; j < (8-noHakmilikLength); j++) {
					noHakmilik = "0"+noHakmilik;
				}
			}

			String idHakmilik = getNegeri(idNegeriHM).getKodNegeri() +  getDaerah(idDaerah).getKodDaerah() + getMukim(idMukim).getKodMukim() + getJenishakmilik(idJenisHakmilik).getKodJenisHakmilik() + noHakmilik;

			r.add("ID", idTblstrhakmilik);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_NEGERI", idNegeriHM == null ? "" : idNegeriHM);
			r.add("ID_DAERAH", idDaerah == null ? "" : idDaerah);
			r.add("ID_BANDARPEKANMUKIM", idMukim == null ? "" : idMukim);
			r.add("ID_KODJENISHAKMILIK", idJenisHakmilik == null ? "" : idJenisHakmilik);
			r.add("NO_HAKMILIK", noHakmilik == null ? "" : noHakmilik);
			r.add("ID_LOT", idLot == null ? "" : idLot);
			r.add("NO_LOT", noLot == null ? "" : noLot);
			r.add("STATUS", "D");
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("MASUK_OLEH", userId);
			r.add("ID_TBLSTRBANGUNANKHAS", idStrBgnnKhas);

			sql = r.getSQLInsert("TBLSTRHAKMILIK");
			myLogger.info("add TBLSTRHAKMILIK:" + sql);
			stmt.executeUpdate(sql);
			//long idStrBgnnKhas = DB.getNextID("TBLSTRBANGUNANKHAS_SEQ");
			//r.clear();
			insertHistoryHakmilik(session,"INSERT",idJenisHakmilik,noHakmilik,idLot,noLot,idTblstrhakmilik+"");
			
//			// TBLSTRCF
			
//			for (int i = 0; i < vCfTemp.size(); i++) {
//			
//			Hashtable h = (Hashtable)vCfTemp.get(i);
//			String noCf = (String)h.get("NoCF");
//			String tarikhCf = (String)h.get("TarikhCF");
//			String noKelulusankhas = (String)h.get("NoKelulusanKhas");
//			String ulasan = (String)h.get("ulasan");
//			
//			sql = "";
//			r = new SQLRenderer();
//			long idCf = DB.getNextID("TBLSTRCF_SEQ");
//			
//			r.add("ID", idCf);
//			r.add("ID_TBLSTRBANGUNANKHAS", idStrBgnnKhas);
//			if(flagCf!=null && flagCf.equals("Y")){
//				r.add("NO_CF", noCf == null ? "" : noCf);
//				if(!tarikhCf.isEmpty()){
//					String TCF = "to_date('" + tarikhCf + "','dd/MM/yyyy')";
//					r.add("TARIKH_CF", r.unquote(TCF));
//					
//					}
//			}else{
//				r.add("NO_KELULUSANKHAS",noKelulusankhas);
//				r.add("ULASAN",ulasan);
//				System.out.println("ulasan" +ulasan);
//				}
//							
//				r.add("FLAG_AKTIF", "Y");
//				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//				r.add("MASUK_OLEH", userId);
//				// r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//				// r.add("KEMASKINI_OLEH", userId);
//
//				sql = r.getSQLInsert("TBLSTRCF");
//				myLogger.info("add TBLSTRCF:" + sql);
//				stmt.executeUpdate(sql);
//			}
			r.clear();
			
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
		System.out.println("---------idStrBgnnKhas----------"+idStrBgnnKhas);
		return idStrBgnnKhas+"";
	}
	
	public void saveUpdate(String idNegeriStr, String idLot, String noLot, String bilPetak,
			String noFailMajlis, String tarikhLulusmajlis, String idNegeri, String idDaerah, String idBandarpekanmukim,
			String idKodjenishakmilik, String noHakmilik, String namaPemaju, String alamat1Pemaju,
			String alamat2Pemaju, String alamat3Pemaju, String poskodPemaju,
			String idKodnegeripemaju, String idKodbandarpemaju, String namaSkim,
			String alamat1Skim, String alamat2Skim, String alamat3Skim, String poskodSkim,
			String idKodnegeriskim, String idKodbandarskim, String idTblrujstatusstrata,
			String tarikhDaftarstrata, String tarikhMohonstrata, String noRujptg,String idStrata,
			String idHm,String flagCf,String idcfs,String ulasanStrata,String idpemilik,String radioCF,
			String noCf,String tarikhCf,String noKelulusankhas,String ulasan,String flagStrata,String tarikhSifus,String tarikhCPSP,String txtSenaraiPBT,Vector vPemilikTemp,HttpSession session) throws Exception {
		

		insertHistoryHakmilik(session,"UPDATE",idKodjenishakmilik,noHakmilik,idLot,noLot,idHm);

		System.out.println("flag cf:::::::"+flagCf);
		//System.out.println("flag cf:::::::"+flagCf);
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

			// TBLSTRHAKMILIK
			long idTblstrhakmilik = 0L;
			if(idHm.isEmpty()){
				idTblstrhakmilik = DB.getNextID("TBLSTRHAKMILIK_SEQ");
			}else{
				idTblstrhakmilik = Utils.parseLong(idHm);
			}
			
			if((noHakmilik!=null)&& noHakmilik.length()>0){
				int noHakmilikLength = noHakmilik.length();
				for (int j = 0; j < (8-noHakmilikLength); j++) {
					noHakmilik = "0"+noHakmilik;
				}
			}
			
			String idHakmilik = getNegeri(idNegeri).getKodNegeri() +  getDaerah(idDaerah).getKodDaerah() + getMukim(idBandarpekanmukim).getKodMukim() + getJenishakmilik(idKodjenishakmilik).getKodJenisHakmilik() + noHakmilik;

			r.update("ID", idTblstrhakmilik);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_NEGERI", idNegeri == null ? "" : idNegeri);
			r.add("ID_DAERAH", idDaerah == null ? "" : idDaerah);
			r.add("ID_BANDARPEKANMUKIM", idBandarpekanmukim == null ? "" : idBandarpekanmukim);
			r.add("ID_KODJENISHAKMILIK", idKodjenishakmilik == null ? "" : idKodjenishakmilik);
			r.add("NO_HAKMILIK", noHakmilik == null ? "" : noHakmilik);
			r.add("ID_LOT", idLot == null ? "" : idLot);
			r.add("NO_LOT", noLot == null ? "" : noLot);
			r.add("STATUS", "D");
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("KEMASKINI_OLEH", userId);

			sql = r.getSQLUpdate("TBLSTRHAKMILIK");
			System.out.println("add TBLSTRHAKMILIK:" + sql);
			stmt.executeUpdate(sql);
		
			
			// TBLSTRBANGUNANKHAS

			sql = "";
			r = new SQLRenderer();
			
			long idStrBgnnKhas = 0L;
			if(idStrata.isEmpty()){
				idStrBgnnKhas = DB.getNextID("TBLSTRBANGUNANKHAS_SEQ");
			}else{
				idStrBgnnKhas = Utils.parseLong(idStrata);
			}
			
			String TL = "to_date('" + tarikhLulusmajlis + "','dd/MM/yyyy')";
			String TMS = "to_date('" + tarikhMohonstrata + "','dd/MM/yyyy')";
			String TDS = "to_date('" + tarikhDaftarstrata + "','dd/MM/yyyy')";
			String TSF = "to_date('" + tarikhSifus + "','dd/MM/yyyy')";
			String TCPSP = "to_date('" + tarikhCPSP + "','dd/MM/yyyy')";
			

			r.update("ID_STRATA", idStrBgnnKhas);
			r.add("ID_NEGERI", idNegeri == null ? "" : idNegeri);
			//r.add("ID_TBLSTRHAKMILIK", idTblstrhakmilik);
			r.add("BIL_PETAK", bilPetak == null ? "" : bilPetak);
			r.add("NO_FAILMAJLIS", noFailMajlis == null ? "" : noFailMajlis);
			r.add("TARIKH_LULUSMAJLIS", r.unquote(TL));
			r.add("NAMA_PEMAJU", namaPemaju == null ? "" : namaPemaju);
			r.add("ALAMAT1_PEMAJU", alamat1Pemaju == null ? "" : alamat1Pemaju);
			r.add("ALAMAT2_PEMAJU", alamat2Pemaju == null ? "" : alamat2Pemaju);
			r.add("ALAMAT3_PEMAJU", alamat3Pemaju == null ? "" : alamat3Pemaju);
			r.add("POSKOD_PEMAJU", poskodPemaju == null ? "" : poskodPemaju);
			r.add("ID_KODBANDARPEMAJU", idKodbandarpemaju == null ? "" : idKodbandarpemaju);
			r.add("ID_KODNEGERIPEMAJU", idKodnegeripemaju == null ? "" : idKodnegeripemaju);
			r.add("NAMA_SKIM", namaSkim == null ? "" : namaSkim);
			r.add("ALAMAT1_SKIM", alamat1Skim == null ? "" : alamat1Skim);
			r.add("ALAMAT2_SKIM", alamat2Skim == null ? "" : alamat2Skim);
			r.add("ALAMAT3_SKIM", alamat3Skim == null ? "" : alamat3Skim);
			r.add("POSKOD_SKIM", poskodSkim == null ? "" : poskodSkim);
			r.add("ID_KODBANDARSKIM", idKodbandarskim == null ? "" : idKodbandarskim);
			r.add("ID_KODNEGERISKIM", idKodnegeriskim == null ? "" : idKodnegeriskim);
			r.add("ID_TBLRUJSTATUSSTRATA", idTblrujstatusstrata == null ? "" : idTblrujstatusstrata);
			r.add("TARIKH_MOHONSTRATA", r.unquote(TMS));
			r.add("TARIKH_DAFTARSTRATA", r.unquote(TDS));
			r.add("TARIKH_SIFUS", r.unquote(TSF));
			r.add("TARIKH_CPSP", r.unquote(TCPSP));
			r.add("NO_RUJUKANPTG", noRujptg == null ? "" : noRujptg);
			r.add("ULASAN_STRATA", ulasanStrata == null ? "" : ulasanStrata);
			r.add("FLAG_STRATA", flagStrata == null ? "" : flagStrata);
			r.add("ID_PEJABAT", txtSenaraiPBT == null ? "" : txtSenaraiPBT);
			r.add("FLAG_CF", flagCf == null ? "" : flagCf);
			//r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			//r.add("MASUK_OLEH", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("KEMASKINI_OLEH", userId);

			sql = r.getSQLUpdate("TBLSTRBANGUNANKHAS");
			System.out.println("add TBLSTRBANGUNANKHAS:" + sql);
			stmt.executeUpdate(sql);
			

			// TBLSTRCF
			
			//String TCF = "to_date('" + tarikhCf + "','dd/MM/yyyy')";
//				boolean addCSF = false;
//				if (idcfs == null || idcfs.length() == 0) {
//					addCSF = true;
//				}
				System.out.println("id cfs++++"+idcfs);
				sql = "";
				r = new SQLRenderer();
				long idCfs = 0L;
				
				if(idcfs.isEmpty()){
					idCfs = DB.getNextID("TBLSTRCF_SEQ");
					r.add("ID", idCfs);
				}else{
					idCfs = Utils.parseLong(idcfs);
					r.update("ID", idCfs);
				}
				
				r.add("ID_TBLSTRBANGUNANKHAS", idStrBgnnKhas);
				
				String TCF = "to_date('" + tarikhCf + "','dd/MM/yyyy')";
				
				if(radioCF!=null && radioCF.equals("Y")){
					r.add("NO_CF", noCf == null ? "" : noCf);
					if(!tarikhCf.isEmpty()){
						
						r.add("TARIKH_CF", r.unquote(TCF));
						r.add("FLAG_AKTIF", "Y");
					}
				}else{
					r.add("NO_KELULUSANKHAS", noKelulusankhas == null ? "" : noKelulusankhas);
					r.add("ULASAN", ulasan == null ? "" : ulasan);
					r.add("TARIKH_CF", r.unquote(TCF));
					r.add("FLAG_AKTIF", "T");
				}
				
				
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("KEMASKINI_OLEH", userId);

				if(idcfs.isEmpty()){
					sql = r.getSQLInsert("TBLSTRCF");
				}else{
					sql = r.getSQLUpdate("TBLSTRCF");
				}
				
				System.out.println("Update TBLSTRCF:" + sql);
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


	public void saveUpdate2(String negeri, String daerah, String mukim, String jenisHakmilik, String noHakmilik, String lot,
			String noLot, String status, String negeri1, String bilPetak, String noFailMajlis, String namaPemaju,
			String txdTarikhLulus, String txdTarikhMohon, String txdTarikhDaftar, String txdTarikhCf, String alamat1P,
			String alamat2P, String alamat3P, String poskodP, String skim, String alamat1S, String alamat2S, String alamat3S,
			String poskodS, String txtnoRujPtg, String noCf, String noKelulusan,String flagCf, HttpSession session) throws Exception {

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
			ResultSet rs = stmt.executeQuery(sql);

			// TBLSTRHAKMILIK
			r.update("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
			r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			// r.add("ID_NEGERI",negeri == null ? "" : negeri);
			r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
			// r.add("ID_DAERAH",daerah == null ? "" : daerah);
			r.add("ID_BANDARPEKANMUKIM", rs.getString("ID_BANDARPEKANMUKIM") == null ? "" : rs.getString("ID_BANDARPEKANMUKIM"));
			// r.add("ID_BANDARPEKANMUKIM",mukim == null ? "" : mukim);
			r.add("ID_KODJENISHAKMILIK", rs.getString("ID_KODJENISHAKMILIK") == null ? "" : rs.getString("ID_KODJENISHAKMILIK"));
			// r.add("ID_KODJENISHAKMILIK",jenisHakmilik == null ? "" :
			// jenisHakmilik);
			r.add("NO_HAKMILIK", noHakmilik == null ? "" : noHakmilik);
			r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
			// r.add("ID_LOT",lot == null ? "" : lot);
			r.add("NO_LOT", noLot == null ? "" : noLot);
			r.add("STATUS", "D");

			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("MASUK_OLEH", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("KEMASKINI_OLEH", userId);

			sql = r.getSQLUpdate("TBLSTRHAKMILIK");
			stmt.executeUpdate(sql);

			// TBLSTRBANGUNANKHAS

			r = new SQLRenderer();
			long idStrata = DB.getNextID("TBLSTRBANGUNANKHAS_SEQ");

			String TL = "to_date('" + txdTarikhLulus + "','dd/MM/yyyy')";
			String TMS = "to_date('" + txdTarikhMohon + "','dd/MM/yyyy')";
			String TDS = "to_date('" + txdTarikhDaftar + "','dd/MM/yyyy')";

			r.add("ID_NEGERI", negeri1 == null ? "" : negeri1);
			//r.add("ID_TBLSTRHAKMILIK", rs.getString("ID_TBLSTRHAKMILIK") == null ? "" : rs.getString("ID_TBLSTRHAKMILIK"));
			r.add("BIL_PETAK", bilPetak == null ? "" : bilPetak);
			r.add("NO_FAILMAJLIS", noFailMajlis == null ? "" : noFailMajlis);
			r.add("TARIKH_LULUSMAJLIS", r.unquote(TL));
			r.add("NAMA_PEMAJU", namaPemaju == null ? "" : namaPemaju);
			r.add("ALAMAT1_PEMAJU", alamat1P == null ? "" : alamat1P);
			r.add("ALAMAT2_PEMAJU", alamat2P == null ? "" : alamat2P);
			r.add("ALAMAT3_PEMAJU", alamat3P == null ? "" : alamat3P);
			r.add("POSKOD_PEMAJU", poskodP == null ? "" : poskodP);
			r.add("ID_KODBANDARPEMAJU", rs.getString("ID_KODBANDARPEMAJU") == null ? "" : rs.getString("ID_KODBANDARPEMAJU"));
			r.add("ID_KODNEGERIPEMAJU", rs.getString("ID_KODNEGERIPEMAJU") == null ? "" : rs.getString("ID_KODNEGERIPEMAJU"));
			r.add("NAMA_SKIM", skim == null ? "" : skim);
			r.add("ALAMAT1_SKIM", alamat1S == null ? "" : alamat1S);
			r.add("ALAMAT2_SKIM", alamat2S == null ? "" : alamat2S);
			r.add("ALAMAT3_SKIM", alamat3S == null ? "" : alamat3S);
			r.add("POSKOD_SKIM", poskodS == null ? "" : poskodS);
			r.add("ID_KODBANDARSKIM", rs.getString("ID_KODBANDARSKIM") == null ? "" : rs.getString("ID_KODBANDARSKIM"));
			r.add("ID_KODNEGERISKIM", rs.getString("ID_KODNEGERISKIM") == null ? "" : rs.getString("ID_KODNEGERISKIM"));
			r.add("ID_TBLRUJSTATUSSTRATA",
					rs.getString("ID_TBLRUJSTATUSSTRATA") == null ? "" : rs.getString("ID_TBLRUJSTATUSSTRATA"));
			r.add("TARIKH_MOHONSTRATA", r.unquote(TMS));
			r.add("TARIKH_DAFTARSTRATA", r.unquote(TDS));
			r.add("NO_RUJUKANPTG", txtnoRujPtg == null ? "" : txtnoRujPtg);
			r.add("FLAG_CF", flagCf == null ? "" : flagCf);

			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("MASUK_OLEH", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("KEMASKINI_OLEH", userId);

			sql = r.getSQLUpdate("TBLSTRBANGUNANKHAS");
			stmt.executeUpdate(sql);

			// TBLSTRCF
//			r = new SQLRenderer();
//			long idCf = DB.getNextID("TBLSTRCF_SEQ");
//			String TCF = "to_date('" + txdTarikhCf + "','dd/MM/yyyy')";
//
//			r.add("ID_TBLSTRBANGUNANKHAS",
//					rs.getString("ID_TBLSTRBANGUNANKHAS") == null ? "" : rs.getString("ID_TBLSTRBANGUNANKHAS"));
//			r.add("NO_CF", noCf == null ? "" : noCf);
//			r.add("TARIKH_CF", r.unquote(TCF));
//			r.add("NO_KELULUSANKHAS", noKelulusan == null ? "" : noKelulusan);
//			r.add("FLAG_AKTIF", "Y");
//
//			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//			r.add("MASUK_OLEH", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//			r.add("KEMASKINI_OLEH", userId);
//
//			sql = r.getSQLUpdate("TBLSTRBANGUNANKHAS");
//			stmt.executeUpdate(sql);

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
	
	public void deleteBgnnKhas(String listIdStrataDel, FrmMaklumatStrata frmMaklumatStrata, HttpSession session) throws Exception {
		String sql = "";
		String sqlCf = "";
		String sqlHak = "";
		String sqlPemilik = "";
		Db db = new Db();
		try {
			db = new Db();
			
			Statement stmtGet = db.getStatement();
			sql = "SELECT NAMA_PEMAJU FROM TBLSTRBANGUNANKHAS WHERE ID_STRATA IN (" + listIdStrataDel + ")";
			ResultSet rs = stmtGet.executeQuery(sql);
			while (rs.next())
			{
				AuditTrail.logActivity(frmMaklumatStrata, session, "DEL", "PEMAJU ["+rs.getString("NAMA_PEMAJU")+"] Deleted");
			}
			
			Statement stmtCf = db.getStatement();
			sqlCf = "DELETE FROM TBLSTRCF WHERE ID_TBLSTRBANGUNANKHAS IN (" + listIdStrataDel + ")";
			System.out.println("sql delete cf" +sqlCf);
			stmtCf.executeUpdate(sqlCf);
			
			Statement stmtPemilik = db.getStatement();
			sqlPemilik = "DELETE FROM TBLSTRPEMILIK WHERE ID_TBLSTRBANGUNANKHAS IN (" + listIdStrataDel + ")";
			System.out.println("delete data pemilik " +sqlPemilik);
			stmtPemilik.executeUpdate(sqlPemilik);
			
			Statement stmtHak = db.getStatement();
			sqlHak = "DELETE FROM TBLSTRHAKMILIK WHERE ID_TBLSTRBANGUNANKHAS IN (" + listIdStrataDel + ")";
			System.out.println("delete data HAKMILIK" +sqlHak);
			stmtHak.executeUpdate(sqlHak);
			
			
			Statement stmt = db.getStatement();
			sql = "DELETE FROM TBLSTRBANGUNANKHAS WHERE ID_STRATA IN (" + listIdStrataDel + ")";
			System.out.println("sql delete STRATABANGUNANKHAS" +sql);
			
			
			stmt.executeUpdate(sql);
			
			
			
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getData(String idStrata) throws Exception {
		Vector data = new Vector();

		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BGNN.ID_STRATA,BGNN.ID_NEGERI AS ID_NEGERI_STR,BGNN.BIL_PETAK,BGNN.NO_FAILMAJLIS,BGNN.TARIKH_LULUSMAJLIS,BGNN.ULASAN_STRATA,"
					+ "BGNN.NAMA_PEMAJU,BGNN.ALAMAT1_PEMAJU,BGNN.ALAMAT2_PEMAJU,BGNN.ALAMAT3_PEMAJU, BGNN.POSKOD_PEMAJU,BGNN.ID_KODBANDARPEMAJU,BGNN.ID_KODNEGERIPEMAJU,"
					+ "BGNN.NAMA_SKIM,BGNN.ALAMAT1_SKIM,BGNN.ALAMAT2_SKIM,BGNN.ALAMAT3_SKIM,BGNN.POSKOD_SKIM,BGNN.ID_KODBANDARSKIM,BGNN.ID_KODNEGERISKIM,"
					+ "BGNN.ID_TBLRUJSTATUSSTRATA,BGNN.TARIKH_MOHONSTRATA,BGNN.TARIKH_DAFTARSTRATA,BGNN.NO_RUJUKANPTG, BGNN.FLAG_CF,BGNN.FLAG_STRATA,BGNN.TARIKH_SIFUS,BGNN.TARIKH_CPSP,BGNN.ID_PEJABAT,"
					+ "HM.ID,HM.ID_HAKMILIK, HM.ID_NEGERI,HM.ID_DAERAH,HM.ID_BANDARPEKANMUKIM,HM.ID_KODJENISHAKMILIK,HM.NO_HAKMILIK,HM.ID_LOT,HM.NO_LOT,HM.STATUS,"
					+ "CF.ID AS ID_CF,CF.ID_TBLSTRBANGUNANKHAS,CF.NO_CF,CF.TARIKH_CF,CF.NO_KELULUSANKHAS,CF.FLAG_AKTIF, CF.ULASAN, P.NAMA_PEJABAT "
					+ "FROM TBLSTRBANGUNANKHAS BGNN, TBLSTRHAKMILIK HM, TBLSTRCF CF, TBLRUJNEGERI D, TBLRUJSTATUSSTRATA E, TBLRUJLOT F, TBLRUJPEJABAT P "
					+ "WHERE BGNN.ID_STRATA = HM.ID_TBLSTRBANGUNANKHAS(+) AND CF.ID_TBLSTRBANGUNANKHAS(+) = BGNN.ID_STRATA "
					+ "AND BGNN.ID_NEGERI = D.ID_NEGERI(+) AND HM.ID_LOT = F.ID_LOT(+) "
					+ "AND BGNN.ID_PEJABAT = P.ID_PEJABAT(+) "
					+ "AND BGNN.ID_TBLRUJSTATUSSTRATA = E.ID(+) " + "AND BGNN.ID_STRATA = " + idStrata;
			sql += " ORDER BY BGNN.ID_NEGERI ";
			myLogger.info("GET DATA:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STRATA", rs.getString("ID_STRATA") == null ? "" : rs.getString("ID_STRATA"));
				h.put("ID_NEGERI_STR", rs.getString("ID_NEGERI_STR") == null ? "" : rs.getString("ID_NEGERI_STR"));
				//h.put("ID_TBLSTRHAKMILIK", rs.getString("ID_TBLSTRHAKMILIK") == null ? "" : rs.getString("ID_TBLSTRHAKMILIK"));
				h.put("BIL_PETAK", rs.getString("BIL_PETAK") == null ? "" : rs.getString("BIL_PETAK"));
				h.put("NO_FAILMAJLIS", rs.getString("NO_FAILMAJLIS") == null ? "" : rs.getString("NO_FAILMAJLIS"));
				h.put("TARIKH_LULUSMAJLIS",rs.getDate("TARIKH_LULUSMAJLIS") == null ? "" : sdf.format(rs.getDate("TARIKH_LULUSMAJLIS")));
				h.put("NAMA_PEMAJU", rs.getString("NAMA_PEMAJU") == null ? "" : rs.getString("NAMA_PEMAJU"));
				h.put("ALAMAT1_PEMAJU", rs.getString("ALAMAT1_PEMAJU") == null ? "" : rs.getString("ALAMAT1_PEMAJU"));
				h.put("ALAMAT2_PEMAJU", rs.getString("ALAMAT2_PEMAJU") == null ? "" : rs.getString("ALAMAT2_PEMAJU"));
				h.put("ALAMAT3_PEMAJU", rs.getString("ALAMAT3_PEMAJU") == null ? "" : rs.getString("ALAMAT3_PEMAJU"));
				h.put("POSKOD_PEMAJU", rs.getString("POSKOD_PEMAJU") == null ? "" : rs.getString("POSKOD_PEMAJU"));
				h.put("ID_KODBANDARPEMAJU", rs.getString("ID_KODBANDARPEMAJU") == null ? "" : rs.getString("ID_KODBANDARPEMAJU"));
				h.put("ID_KODNEGERIPEMAJU", rs.getString("ID_KODNEGERIPEMAJU") == null ? "" : rs.getString("ID_KODNEGERIPEMAJU"));
				h.put("NAMA_SKIM", rs.getString("NAMA_SKIM") == null ? "" : rs.getString("NAMA_SKIM"));
				h.put("ALAMAT1_SKIM", rs.getString("ALAMAT1_SKIM") == null ? "" : rs.getString("ALAMAT1_SKIM"));
				h.put("ALAMAT2_SKIM", rs.getString("ALAMAT2_SKIM") == null ? "" : rs.getString("ALAMAT2_SKIM"));
				h.put("ALAMAT3_SKIM", rs.getString("ALAMAT3_SKIM") == null ? "" : rs.getString("ALAMAT3_SKIM"));
				h.put("POSKOD_SKIM", rs.getString("POSKOD_SKIM") == null ? "" : rs.getInt("POSKOD_SKIM"));
				h.put("ID_KODBANDARSKIM", rs.getString("ID_KODBANDARSKIM") == null ? "" : rs.getString("ID_KODBANDARSKIM"));
				h.put("ID_KODNEGERISKIM", rs.getString("ID_KODNEGERISKIM") == null ? "" : rs.getString("ID_KODNEGERISKIM"));
				h.put("ID_TBLRUJSTATUSSTRATA",rs.getString("ID_TBLRUJSTATUSSTRATA") == null ? "" : rs.getString("ID_TBLRUJSTATUSSTRATA"));
				h.put("TARIKH_MOHONSTRATA",rs.getDate("TARIKH_MOHONSTRATA") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHONSTRATA")));
				h.put("TARIKH_DAFTARSTRATA",rs.getDate("TARIKH_DAFTARSTRATA") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTARSTRATA")));
				h.put("TARIKH_SIFUS",rs.getDate("TARIKH_SIFUS") == null ? "" : sdf.format(rs.getDate("TARIKH_SIFUS")));
				h.put("TARIKH_CPSP",rs.getDate("TARIKH_CPSP") == null ? "" : sdf.format(rs.getDate("TARIKH_CPSP")));
				h.put("NO_RUJUKANPTG", rs.getString("NO_RUJUKANPTG") == null ? "" : rs.getString("NO_RUJUKANPTG"));
				h.put("ULASAN_STRATA", rs.getString("ULASAN_STRATA") == null ? "" : rs.getString("ULASAN_STRATA"));
				h.put("FLAG_CF", rs.getString("FLAG_CF") == null ? "" : rs.getString("FLAG_CF"));
				h.put("FLAG_STRATA", rs.getString("FLAG_STRATA") == null ? "" : rs.getString("FLAG_STRATA"));
				h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				
				h.put("ID", rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_BANDARPEKANMUKIM",rs.getString("ID_BANDARPEKANMUKIM") == null ? "" : rs.getString("ID_BANDARPEKANMUKIM"));
				h.put("ID_KODJENISHAKMILIK",rs.getString("ID_KODJENISHAKMILIK") == null ? "" : rs.getString("ID_KODJENISHAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));

				h.put("ID_CF", rs.getString("ID_CF") == null ? "" : rs.getString("ID_CF"));
				h.put("ID_TBLSTRBANGUNANKHAS",rs.getString("ID_TBLSTRBANGUNANKHAS") == null ? "" : rs.getString("ID_TBLSTRBANGUNANKHAS"));
				h.put("NO_CF", rs.getString("NO_CF") == null ? "" : rs.getString("NO_CF"));
				h.put("TARIKH_CF", rs.getDate("TARIKH_CF") == null ? "" : sdf.format(rs.getDate("TARIKH_CF")));
				h.put("NO_KELULUSANKHAS", rs.getString("NO_KELULUSANKHAS") == null ? "" : rs.getString("NO_KELULUSANKHAS"));
				h.put("ULASAN", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				//h.put("TARIKHNOKELULUSANKHAS", rs.getDate("TARIKHNOKELULUSANKHAS") == null ? "" : sdf.format(rs.getDate("TARIKHNOKELULUSANKHAS")));
				h.put("FLAG_AKTIF", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));

				data.addElement(h);
				
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return data;
	}

	public Vector getBandarByNegeri(String idnegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "Select id_bandar,kod_bandar,upper(keterangan) as keterangan from tblrujbandar where id_negeri = '" + idnegeri
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("idbandarnegeri", rs.getString("id_bandar") == null ? "" : rs.getString("id_bandar"));
				h.put("kodbandar", rs.getString("kod_bandar") == null ? "" : rs.getString("kod_bandar"));
				h.put("nama", rs.getString("keterangan") == null ? "" : rs.getString("keterangan"));
				v.addElement(h);
			}
			return v;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	public Tblrujnegeri getNegeri(String idnegeri) {

		Vector v = null;
		Tblrujnegeri n = new Tblrujnegeri();
		try {
			if(!idnegeri.isEmpty()){
				v = new Vector();
				v = DB.getNegeri(idnegeri);
				if(v!=null && v.size()>0){
					n = (Tblrujnegeri) v.get(0);
				}
			}else{
				n.setNamaNegeri("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Tblrujdaerah getDaerah(String idDaerah) {

		Vector v = null;
		Tblrujdaerah n = new Tblrujdaerah();
		try {
			if(!idDaerah.isEmpty()){
				v = new Vector();
				v = DB.getDaerah(idDaerah);
				if(v!=null && v.size()>0){
					n = (Tblrujdaerah) v.get(0);
				}
			}else{
				n.setNamaDaerah("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Tblrujbandar getBandarDesc(String idBandar) {
		Vector v = null;
		Tblrujbandar n = new Tblrujbandar();
		try {
			if(!idBandar.isEmpty()){
				v = new Vector();
				v = DB.getBandar(idBandar);
				if(v!=null && v.size()>0){
					n = (Tblrujbandar) v.get(0);
				}
			}else{
				n.setKeterangan("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public Tblrujlot getLot(String idLot) {
		Vector v = null;
		Tblrujlot n = new Tblrujlot();
		try {
			if(!idLot.isEmpty()){
				v = new Vector();
				v = DB.getLot(idLot);
				if(v!=null && v.size()>0){
					n = (Tblrujlot) v.get(0);	
				}
			}else{
				n.setKeterangan("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Tblrujmukim getMukim(String idMukim) {
		Vector v = null;
		Tblrujmukim n = new Tblrujmukim();
		try {
			if(!idMukim.isEmpty()){
				v = new Vector();
				v = DB.getMukim(idMukim);
				if(v!=null && v.size()>0){
					n = (Tblrujmukim) v.get(0);	
				}
			}else{
				n.setNamaMukim("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Tblrujjenishakmilik getJenishakmilik(String idJenishakmilik) {
		Vector v = null;
		Tblrujjenishakmilik n = new Tblrujjenishakmilik();
		try {
			if(!idJenishakmilik.isEmpty()){
				v = new Vector();
				v = DB.getJenishakmilik(idJenishakmilik);
				if(v!=null && v.size()>0){
					n = (Tblrujjenishakmilik) v.get(0);	
				}
			}else{
				n.setKeterangan("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public Vector getDataCF(String idStrata) throws DbException, SQLException {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_IdStrata = "", RS_NoCF = "",  RS_NoKelulusanKhas = "",RS_FlagAktif="",RS_Id="",RS_Ulasan="";
			Date RS_TarikhCF = null, RS_TarikhKelulusanKhas = null; 
			sql = "SELECT ID_TBLSTRBANGUNANKHAS ,NO_CF, TARIKH_CF, NO_KELULUSANKHAS,ULASAN,FLAG_AKTIF,ID FROM TBLSTRCF WHERE ID_TBLSTRBANGUNANKHAS='"+ idStrata +"' ORDER BY ID";
			myLogger.info("sql nk keluarkan id cf ::" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IdStrata = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NoCF = rs.getString(2) == null ? "" : rs.getString(2);
				RS_TarikhCF = (Date) rs.getDate(3);
				RS_NoKelulusanKhas = rs.getString(4) == null ? "" : rs.getString(4);
				RS_Ulasan = rs.getString(5) == null ? "" : rs.getString(5);
				RS_FlagAktif = rs.getString(6) == null ? "" : rs.getString(6);
				//RS_TarikhKelulusanKhas = (Date) rs.getDate(7);
				RS_Id = rs.getString(7) == null ? "" : rs.getString(7);
				
				System.out.println("RS_Id ::"+RS_Id);
				System.out.println("Ulasan"+RS_Ulasan);

				h = new Hashtable();
//				h.put("IdStrata", RS_IdStrata);
				h.put("No", iCount);
				h.put("noCf", RS_NoCF);
				if(RS_TarikhCF!=null){
					h.put("tarikhCf", sdf.format(RS_TarikhCF));
				}
				h.put("noKelulusankhas", RS_NoKelulusanKhas);
				h.put("ulasan", RS_Ulasan);
				System.out.println("Ulasannnnn"+h.put("ulasan", RS_Ulasan));
				h.put("FlagAktif", RS_FlagAktif);
//				if(RS_TarikhKelulusanKhas!=null){
//					h.put("tarikhNoKelulusanKhas", sdf.format(RS_TarikhKelulusanKhas));
//				}
				h.put("Id", RS_Id);
				v.addElement(h);
				iCount++;
			}
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Vector getDataPemilik(String idStrata) throws DbException, SQLException {
		String sql = "";
		Db db = new Db();
		Vector v = new Vector();
		Hashtable h = null;

		try {
			String SQL_SEARCH = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;

			int iCount = 1;
			String RS_IdStrata = "", RS_NamaPemilik = "",  RS_NoKadPengenalan = "",RS_FlagPemilik="",RS_IdPemilik="", RS_Alamat1Pemilik="",RS_Alamat2Pemilik="", RS_Alamat3Pemilik="";
			String RS_Poskod="", RS_IdKodNegeri="", RS_IdKodBandar="", RS_NamaNegeri="", RS_NamaBandar="", RS_JenisPemilik="";
			sql = "SELECT P.id_tblstrbangunankhas, P.nama_pemilik, P.alamat1_pemilik, P.alamat2_pemilik,P.alamat3_pemilik, P.poskod_pemilik, P.id_kodbandarpemilik, P.id_kodnegeripemilik, P.nopengenalan_pemilik, P.jenis_pemilik, P.id_pemilik, N.NAMA_NEGERI, B.KETERANGAN FROM tblstrpemilik p, tblrujnegeri n, tblrujbandar b WHERE ID_TBLSTRBANGUNANKHAS='"+ idStrata +"' AND P.id_kodnegeripemilik = N.ID_NEGERI (+) AND P.ID_KODBANDARPEMILIK = B.ID_BANDAR (+) ORDER BY ID_PEMILIK";
			myLogger.info("sql ::" + sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RS_IdStrata = rs.getString(1) == null ? "" : rs.getString(1);
				RS_NamaPemilik = rs.getString(2) == null ? "" : rs.getString(2);
				RS_Alamat1Pemilik = rs.getString(3) == null ? "" : rs.getString(3);
				RS_Alamat2Pemilik = rs.getString(4) == null ? "" : rs.getString(4);
				RS_Alamat3Pemilik = rs.getString(5) == null ? "" : rs.getString(5);
				RS_Poskod = rs.getString(6) == null ? "" : rs.getString(6);
				RS_IdKodBandar = rs.getString(7) == null ? "" : rs.getString(7);
				RS_IdKodNegeri = rs.getString(8) == null ? "" : rs.getString(8);
				RS_NoKadPengenalan = rs.getString(9) == null ? "" : rs.getString(9);
				RS_FlagPemilik = rs.getString(10) == null ? "" : rs.getString(10);
				RS_IdPemilik = rs.getString(11) == null ? "" : rs.getString(11);
				RS_NamaNegeri = rs.getString(12) == null ? "" : rs.getString(12);
				RS_NamaBandar = rs.getString(13) == null ? "" : rs.getString(13);

				h = new Hashtable();
				h.put("IdStrata", RS_IdStrata);
				h.put("No", iCount);
				h.put("noKadPengenalan", RS_NoKadPengenalan);
				h.put("namaPemilikTanah", RS_NamaPemilik);
				h.put("alamat1Pemilik", RS_Alamat1Pemilik);
				h.put("alamat2Pemilik", RS_Alamat2Pemilik);
				h.put("alamat3Pemilik", RS_Alamat3Pemilik);
				h.put("namaNegeri", RS_NamaNegeri);
				h.put("namaBandar", RS_NamaBandar);
				h.put("radioPemilik", RS_FlagPemilik);
				//h.put("Flag", RS_FlagAktif);
				h.put("idPemilik", RS_IdPemilik);
				v.addElement(h);
				iCount++;
				
				System.out.println("SQL LISTTTTT----"+v);
			}
			System.out.println("sql pemilikkk------"+sql);
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return v;
	}
	
	public Hashtable getAlamatPemilikSebelum(String ID_STRATA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT " +
					" ALAMAT1_PEMILIK, ALAMAT2_PEMILIK, ALAMAT3_PEMILIK, POSKOD_PEMILIK, ID_KODNEGERIPEMILIK, ID_KODBANDARPEMILIK " +
					" FROM TBLSTRPEMILIK WHERE ID_PEMILIK IN "+
					" (SELECT MAX(ID_PEMILIK) FROM TBLSTRPEMILIK, TBLSTRBANGUNANKHAS WHERE ID_TBLSTRBANGUNANKHAS = ID_STRATA AND ID_STRATA = '"+ID_STRATA+"') ";
			myLogger.info(" viewPejabatJKPTG :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h = null;
			
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ALAMAT1_PEMILIK",rs.getString("ALAMAT1_PEMILIK") == null ? "" : rs.getString("ALAMAT1_PEMILIK"));
				h.put("ALAMAT2_PEMILIK",rs.getString("ALAMAT2_PEMILIK") == null ? "" : rs.getString("ALAMAT2_PEMILIK"));
				h.put("ALAMAT3_PEMILIK",rs.getString("ALAMAT3_PEMILIK") == null ? "" : rs.getString("ALAMAT3_PEMILIK"));
				h.put("POSKOD_PEMILIK",rs.getString("POSKOD_PEMILIK") == null ? "" : rs.getString("POSKOD_PEMILIK"));
				h.put("ID_KODNEGERIPEMILIK",rs.getString("ID_KODNEGERIPEMILIK") == null ? "0" : rs.getString("ID_KODNEGERIPEMILIK"));
				h.put("ID_KODBANDARPEMILIK",rs.getString("ID_KODBANDARPEMILIK") == null ? "0" : rs.getString("ID_KODBANDARPEMILIK"));
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public void deletePemilik(String idPemilik, FrmMaklumatStrata frmMaklumatStrata, HttpSession session) throws Exception {
		String sqlPemilik = "";
		String sql = "";
		
		Db db = new Db();
		try {
			db = new Db();
			
			Statement stmtGet = db.getStatement();
			sql = "SELECT NAMA_PEMILIK FROM TBLSTRPEMILIK WHERE ID_PEMILIK IN (" + idPemilik + ")";
			ResultSet rs = stmtGet.executeQuery(sql);
			while (rs.next())
			{
				AuditTrail.logActivity(frmMaklumatStrata, session, "DEL", "NAMA PEMILIK ["+rs.getString("NAMA_PEMILIK")+"] Deleted");
			}
			
			Statement stmtPemilik = db.getStatement();
			sqlPemilik = "DELETE FROM TBLSTRPEMILIK WHERE ID_PEMILIK IN (" + idPemilik + ")";
			System.out.println("sql delete PEMILIK" +sqlPemilik);
			stmtPemilik.executeUpdate(sqlPemilik);

			
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	//LIST PEMILIK
	Vector senarai_pemilik = null;
	public Vector senarai_pemilik(String id_tblstrbangunankhas) throws Exception {
		
		senarai_pemilik = new Vector();
		Db db = null;
		senarai_pemilik.clear();
		String sql = "";
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PEMILIK, NAMA_PEMILIK, ALAMAT1_PEMILIK,ALAMAT2_PEMILIK, " +
					"ALAMAT3_PEMILIK,POSKOD_PEMILIK,ID_KODBANDRPEMILIK,ID_KODNEGERIPEMILIK," +
					"ID_TBLSTRBANGUNANKHAS,NOPENGENALAN_PEMILIK,JENIS_PEMILIK" +
					"FROM TBLSTRPEMILIK WHERE ID_TBLSTRBANGUNANKHAS = '"+id_tblstrbangunankhas+"' ";
			
			myLogger.info("SELECT XXXX---"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_PEMILIK",rs.getString("ID_PEMILIK") == null ? "" : rs.getString("ID_PEMILIK"));
				h.put("NAMA_PEMILIK",rs.getString("NAMA_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK"));
				h.put("ALAMAT1_PEMILIK",rs.getString("ALAMAT1_PEMILIK") == null ? "" : rs.getString("ALAMAT1_PEMILIK"));
				h.put("ALAMAT2_PEMILIK",rs.getString("ALAMAT2_PEMILIK") == null ? "" : rs.getString("ALAMAT2_PEMILIK"));
				h.put("ALAMAT3_PEMILIK",rs.getString("ALAMAT3_PEMILIK") == null ? "0" : rs.getString("ALAMAT3_PEMILIK"));
				h.put("POSKOD_PEMILIK",rs.getString("POSKOD_PEMILIK") == null ? "0" : rs.getString("POSKOD_PEMILIK"));
				h.put("ID_KODBANDRPEMILIK",rs.getString("ID_KODBANDRPEMILIK") == null ? "0" : rs.getString("ID_KODBANDRPEMILIK"));
				h.put("ID_KODNEGERIPEMILIK",rs.getString("ID_KODNEGERIPEMILIK") == null ? "0" : rs.getString("ID_KODNEGERIPEMILIK"));
				h.put("ID_TBLSTRBANGUNANKHAS",rs.getString("ID_TBLSTRBANGUNANKHAS") == null ? "0" : rs.getString("ID_TBLSTRBANGUNANKHAS"));
				h.put("NOPENGENALAN_PEMILIK",rs.getString("NOPENGENALAN_PEMILIK") == null ? "0" : rs.getString("NOPENGENALAN_PEMILIK"));
				h.put("JENIS_PEMILIK",rs.getString("JENIS_PEMILIK") == null ? "0" : rs.getString("JENIS_PEMILIK"));
				
				senarai_pemilik.addElement(h);
				
				System.out.println("SQLLLLL VIEW LIST----"+senarai_pemilik);
			}
		return senarai_pemilik;
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getDataHistoy(String id) throws Exception {
		Vector data = new Vector();

		Db db = null;
		data.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT BGNN.ID_STRATA,BGNN.ID_NEGERI AS ID_NEGERI_STR,BGNN.BIL_PETAK,BGNN.NO_FAILMAJLIS,BGNN.TARIKH_LULUSMAJLIS,"
					+ "BGNN.NAMA_PEMAJU,BGNN.ALAMAT1_PEMAJU,BGNN.ALAMAT2_PEMAJU,BGNN.ALAMAT3_PEMAJU, BGNN.POSKOD_PEMAJU,BGNN.ID_KODBANDARPEMAJU,BGNN.ID_KODNEGERIPEMAJU,"
					+ "BGNN.NAMA_SKIM,BGNN.ALAMAT1_SKIM,BGNN.ALAMAT2_SKIM,BGNN.ALAMAT3_SKIM,BGNN.POSKOD_SKIM,BGNN.ID_KODBANDARSKIM,BGNN.ID_KODNEGERISKIM,"
					+ "BGNN.ID_TBLRUJSTATUSSTRATA,BGNN.TARIKH_MOHONSTRATA,BGNN.TARIKH_DAFTARSTRATA,BGNN.NO_RUJUKANPTG, BGNN.FLAG_CF,"
					+ "HM.ID,HM.ID_HAKMILIK, HM.ID_NEGERI,HM.ID_DAERAH,HM.ID_BANDARPEKANMUKIM,HM.ID_KODJENISHAKMILIK,HM.NO_HAKMILIK,HM.ID_LOT,HM.NO_LOT,HM.STATUS,"
					+ "CF.ID AS ID_CF,CF.ID_TBLSTRBANGUNANKHAS,CF.NO_CF,CF.TARIKH_CF,CF.NO_KELULUSANKHAS,CF.FLAG_AKTIF "
					+ "FROM TBLSTRBANGUNANKHAS BGNN, TBLSTRHAKMILIK HM, TBLSTRCF CF, TBLRUJNEGERI D, TBLRUJSTATUSSTRATA E, TBLRUJLOT F "
					+ "WHERE BGNN.ID_STRATA = HM.ID_TBLSTRBANGUNANKHAS(+) AND CF.ID_TBLSTRBANGUNANKHAS(+) = BGNN.ID_STRATA "
					+ "AND BGNN.ID_NEGERI = D.ID_NEGERI(+) AND HM.ID_LOT = F.ID_LOT(+) "
					+ "AND BGNN.ID_TBLRUJSTATUSSTRATA = E.ID(+) " + "AND BGNN.ID_STRATA = " + id;
			sql += " ORDER BY BGNN.ID_NEGERI ";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STRATA", rs.getString("ID_STRATA") == null ? "" : rs.getString("ID_STRATA"));
				h.put("ID_NEGERI_STR", rs.getString("ID_NEGERI_STR") == null ? "" : rs.getString("ID_NEGERI_STR"));
				//h.put("ID_TBLSTRHAKMILIK", rs.getString("ID_TBLSTRHAKMILIK") == null ? "" : rs.getString("ID_TBLSTRHAKMILIK"));
				h.put("BIL_PETAK", rs.getString("BIL_PETAK") == null ? "" : rs.getString("BIL_PETAK"));
				h.put("NO_FAILMAJLIS", rs.getString("NO_FAILMAJLIS") == null ? "" : rs.getString("NO_FAILMAJLIS"));
				h.put("TARIKH_LULUSMAJLIS",rs.getDate("TARIKH_LULUSMAJLIS") == null ? "" : sdf.format(rs.getDate("TARIKH_LULUSMAJLIS")));
				h.put("NAMA_PEMAJU", rs.getString("NAMA_PEMAJU") == null ? "" : rs.getString("NAMA_PEMAJU"));
				h.put("ALAMAT1_PEMAJU", rs.getString("ALAMAT1_PEMAJU") == null ? "" : rs.getString("ALAMAT1_PEMAJU"));
				h.put("ALAMAT2_PEMAJU", rs.getString("ALAMAT2_PEMAJU") == null ? "" : rs.getString("ALAMAT2_PEMAJU"));
				h.put("ALAMAT3_PEMAJU", rs.getString("ALAMAT3_PEMAJU") == null ? "" : rs.getString("ALAMAT3_PEMAJU"));
				h.put("POSKOD_PEMAJU", rs.getString("POSKOD_PEMAJU") == null ? "" : rs.getString("POSKOD_PEMAJU"));
				h.put("ID_KODBANDARPEMAJU", rs.getString("ID_KODBANDARPEMAJU") == null ? "" : rs.getString("ID_KODBANDARPEMAJU"));
				h.put("ID_KODNEGERIPEMAJU", rs.getString("ID_KODNEGERIPEMAJU") == null ? "" : rs.getString("ID_KODNEGERIPEMAJU"));
				h.put("NAMA_SKIM", rs.getString("NAMA_SKIM") == null ? "" : rs.getString("NAMA_SKIM"));
				h.put("ALAMAT1_SKIM", rs.getString("ALAMAT1_SKIM") == null ? "" : rs.getString("ALAMAT1_SKIM"));
				h.put("ALAMAT2_SKIM", rs.getString("ALAMAT2_SKIM") == null ? "" : rs.getString("ALAMAT2_SKIM"));
				h.put("ALAMAT3_SKIM", rs.getString("ALAMAT3_SKIM") == null ? "" : rs.getString("ALAMAT3_SKIM"));
				h.put("POSKOD_SKIM", rs.getString("POSKOD_SKIM") == null ? "" : rs.getInt("POSKOD_SKIM"));
				h.put("ID_KODBANDARSKIM", rs.getString("ID_KODBANDARSKIM") == null ? "" : rs.getString("ID_KODBANDARSKIM"));
				h.put("ID_KODNEGERISKIM", rs.getString("ID_KODNEGERISKIM") == null ? "" : rs.getString("ID_KODNEGERISKIM"));
				h.put("ID_TBLRUJSTATUSSTRATA",rs.getString("ID_TBLRUJSTATUSSTRATA") == null ? "" : rs.getString("ID_TBLRUJSTATUSSTRATA"));
				h.put("TARIKH_MOHONSTRATA",rs.getDate("TARIKH_MOHONSTRATA") == null ? "" : sdf.format(rs.getDate("TARIKH_MOHONSTRATA")));
				h.put("TARIKH_DAFTARSTRATA",rs.getDate("TARIKH_DAFTARSTRATA") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTARSTRATA")));
				h.put("NO_RUJUKANPTG", rs.getString("NO_RUJUKANPTG") == null ? "" : rs.getString("NO_RUJUKANPTG"));
				h.put("FLAG_CF", rs.getString("FLAG_CF") == null ? "" : rs.getString("FLAG_CF"));
				
				h.put("ID", rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("ID_BANDARPEKANMUKIM",rs.getString("ID_BANDARPEKANMUKIM") == null ? "" : rs.getString("ID_BANDARPEKANMUKIM"));
				h.put("ID_KODJENISHAKMILIK",rs.getString("ID_KODJENISHAKMILIK") == null ? "" : rs.getString("ID_KODJENISHAKMILIK"));
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));

				h.put("ID_CF", rs.getString("ID_CF") == null ? "" : rs.getString("ID_CF"));
				h.put("ID_TBLSTRBANGUNANKHAS",rs.getString("ID_TBLSTRBANGUNANKHAS") == null ? "" : rs.getString("ID_TBLSTRBANGUNANKHAS"));
				h.put("NO_CF", rs.getString("NO_CF") == null ? "" : rs.getString("NO_CF"));
				h.put("TARIKH_CF", rs.getDate("TARIKH_CF") == null ? "" : sdf.format(rs.getDate("TARIKH_CF")));
				h.put("NO_KELULUSANKHAS", rs.getString("NO_KELULUSANKHAS") == null ? "" : rs.getString("NO_KELULUSANKHAS"));
				h.put("FLAG_AKTIF", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));

				data.addElement(h);
			}
		} catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
		return data;
	}
 
	
	
	public void insertHistoryHakmilik(HttpSession session,String flag,String ID_JENISHAKMILIK,String NO_HAKMILIK,String ID_LOT,String LOT,String ID_HAKMILIK) throws Exception {

		Hashtable gh = getStrHakmilik(session, ID_HAKMILIK);
		String check_ID_JENISHAKMILIK = "";
		String check_NO_HAKMILIK = "";
		String check_ID_LOT = "";
		String check_LOT = "";
		
		
		boolean updatehistory = false;
		myLogger.info("V3 : flag : "+flag);
		myLogger.info("V3 : GH : "+gh);
		if(flag.equals("INSERT"))
		{
			updatehistory = true;
		}
		else if(flag.equals("UPDATE"))
		{
			if(gh!=null)
			{
				check_ID_JENISHAKMILIK = (String)gh.get("ID_KODJENISHAKMILIK");
				check_NO_HAKMILIK = (String)gh.get("NO_HAKMILIK");
				check_ID_LOT = (String)gh.get("ID_LOT");
				check_LOT = (String)gh.get("NO_LOT");	
				
				if(!check_ID_JENISHAKMILIK.toUpperCase().equals(ID_JENISHAKMILIK.toUpperCase()))						
				{
					myLogger.info("V3 : tak sama 1 : ");
					
					updatehistory = true;
				}
				else if(!check_NO_HAKMILIK.toUpperCase().equals(NO_HAKMILIK.toUpperCase()))						
				{
					myLogger.info("V3 : tak sama 2 : ");
					updatehistory = true;
				}
				else if(!check_ID_LOT.toUpperCase().equals(ID_LOT.toUpperCase()))						
				{
					myLogger.info("V3 : tak sama 3 : ");
					updatehistory = true;
				}
				else if(!check_LOT.toUpperCase().equals(LOT.toUpperCase()))						
				{
					myLogger.info("V3 : tak sama 4 : ");
					updatehistory = true;
				}
				
				
				
			}
		}
		
		if(updatehistory==true)
		{
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			//String userId = session.getAttribute("_ekptg_user_id").toString();
			String userId = session.getAttribute("_ekptg_user_id").toString();
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
			
				Statement stmt = db.getStatement();			
				
				sql = "UPDATE TBLSTRTEMP SET FLAG_AKTIF = 'T' WHERE ID_HAKMILIK = '" + ID_HAKMILIK + "'";
				stmt.execute(sql);
				
				SQLRenderer r = new SQLRenderer();
				
				long ID = DB.getNextID("TBLSTRTEMP_SEQ");
				r.add("ID", ID);
				r.add("ID_JENISHAKMILIK", check_ID_JENISHAKMILIK);
				r.add("ID_HAKMILIK", ID_HAKMILIK);				
				r.add("NO_HAKMILIK", check_NO_HAKMILIK);
				r.add("ID_LOT", check_ID_LOT);	
				r.add("LOT", check_LOT);
				r.add("FLAG_AKTIVITI", flag);
				r.add("KEMASKINI_OLEH", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLSTRTEMP");		
				myLogger.info("V3 : INSERT TBLSTRTEMP : "+sql);
					
				stmt.executeUpdate(sql);
				
				
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
		}
	
	}
	
	
	@SuppressWarnings("unchecked")
	public Hashtable getStrHakmilik(HttpSession session, String ID_HAKMILIK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		Hashtable h = null;

		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			
				sql = " SELECT T.ID, T.ID_HAKMILIK, T.ID_NEGERI, "+
						" T.ID_DAERAH, T.ID_BANDARPEKANMUKIM, T.ID_KODJENISHAKMILIK,  "+
						" T.NO_HAKMILIK, T.ID_LOT, T.NO_LOT,  "+
						" T.STATUS, T.TARIKH_MASUK, T.MASUK_OLEH,  "+
						" T.TARIKH_KEMASKINI, T.KEMASKINI_OLEH, T.ID_TBLSTRBANGUNANKHAS "+
						" FROM TBLSTRHAKMILIK T WHERE T.ID = '"+ID_HAKMILIK+"' ";
				
				myLogger.info(" mm: SQL TBLSTRHAKMILIK :"+ sql);
				
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					h = new Hashtable();					
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
					h.put("ID_KODJENISHAKMILIK",rs.getString("ID_KODJENISHAKMILIK") == null ? "" : rs.getString("ID_KODJENISHAKMILIK"));
					h.put("ID_LOT",rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
					h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
					}
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return h;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listHistoryStata(HttpSession session, String ID_HAKMILIK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listHistoryStata = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = "SELECT   t.ID, t.id_jenishakmilik, t.no_hakmilik, t.id_lot, t.lot,t.id_hakmilik, t.kemaskini_oleh, t.tarikh_kemaskini, t.flag_aktiviti, j.KETERANGAN AS KeteranganHakmilik, j.ID_JENISHAKMILIK, l.ID_LOT, l.KETERANGAN AS KeteranganLot FROM tblstrtemp t, tblrujjenishakmilik j, tblrujlot l WHERE t.id_hakmilik = '"+ID_HAKMILIK+"' AND   t.id_jenishakmilik = j.ID_JENISHAKMILIK AND   t.id_lot = l.ID_LOT ORDER BY t.tarikh_kemaskini DESC ";

			myLogger.info(" V3: SQL list TBLSTRTEMP :"+ sql);
			rs = stmt.executeQuery(sql);
			listHistoryStata = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }		
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));	
				h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));	
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));	
				h.put("LOT",rs.getString("LOT") == null ? "" : rs.getString("LOT"));
				h.put("ID_LOT",rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));	
				h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("KeteranganHakmilik",rs.getString("KeteranganHakmilik") == null ? "" : rs.getString("KeteranganHakmilik"));
				h.put("KeteranganLot",rs.getString("KeteranganLot") == null ? "" : rs.getString("KeteranganLot"));
				h.put("KEMASKINI_OLEH",rs.getString("KEMASKINI_OLEH") == null ? "" : rs.getString("KEMASKINI_OLEH"));	
				h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));	
				h.put("FLAG_AKTIVITI",rs.getString("FLAG_AKTIVITI") == null ? "" : rs.getString("FLAG_AKTIVITI"));	
					
				listHistoryStata.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listHistoryStata;

	}

	public void deleteCF(String idCF) throws Exception {
		String sqlCF = "";

		Db db = new Db();
		try {
			db = new Db();
			
			Statement stmtPemilik = db.getStatement();
			sqlCF = "DELETE FROM TBLSTRCF WHERE ID IN (" + idCF + ")";
			System.out.println("sql delete CF" +sqlCF);
			stmtPemilik.executeUpdate(sqlCF);

			
		}catch (Exception re) {
			myLogger.error("Error: ", re);
			throw re;
			}
		finally {
			if (db != null)
				db.close();
		}
	}

	 public Vector dropdown_senaraipbt(String idNegeri,String idDaerah) throws Exception {
			Vector senaraiPBT = new Vector();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = " SELECT ID_PEJABAT, ID_NEGERI, ID_DAERAH, ID_JENISPEJABAT, NAMA_PEJABAT " +
						" FROM TBLRUJPEJABAT " +
						" WHERE ID_JENISPEJABAT = '25' " +
						" AND ID_NEGERI = '"+idNegeri+"' AND ID_DAERAH = '"+idDaerah+"' ";
						
				System.out.println(" SQL SENARAI PBT :"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("ID_PEJABAT", rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
					h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));				
					senaraiPBT.addElement(h);	
					
				}
				System.out.println(" SENARAI NEGERI FROM MODEL :"+senaraiPBT);
				return senaraiPBT;
			}catch (Exception re) {
				throw re;
			} finally {
				if (db != null)
					db.close();
			}
		}
	
}