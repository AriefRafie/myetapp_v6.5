package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.intergration.entity.BorangK;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.entities.Tblrujurusan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPFailBayaranBean implements IBayaranBean {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.HtpBean.class);
	private static String KODKEMENTERIAN ="00";
	private static final long IDTARAFKESELAMATAN = 1;
	private static final int IDSEKSYEN = 3;
	private String sql = "";	//private int idUrusan = 0;
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	private FrmSenaraiFailTerimaPohonData senaraiFail = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfyy = new SimpleDateFormat("dd-MMM-yy");
	SimpleDateFormat sdfMasa =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");

	@Override
	public Vector<HtpPermohonan> findFail(String IdUrusan
		,String noFail
		,String tajukFail
		,String pemohon
		,String idNegeri
		,String idDaerah
		,String idMukim
		,String idKementerian
		,String idAgensi
		,String tarikhPohon
		,String tarikhBukaFail
		,String tarikhBukaFailHingga) { //13 Parameter
		
		Db db = null;
		Vector<HtpPermohonan> v = new Vector<HtpPermohonan>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();


			String sql = "SELECT distinct P.TUJUAN, f.id_Fail, p.id_Permohonan, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri, n.kod_Mampu,n.id_Negeri, PP.id_htppermohonan ";
			sql +="FROM Tblpfdfail F, Tblpermohonan P, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n, tblhtppermohonan PP ";
			sql +="WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri AND PP.ID_PERMOHONAN = P.ID_PERMOHONAN  ";
			sql +="AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql +="AND sf.aktif = '1' AND F.id_Urusan = '"+IdUrusan+"' AND f.tajuk_Fail LIKE '%"+tajukFail+"%' ";
			sql +="AND f.ID_STATUS <> 999 ";
			//Nama Permohon
			if (pemohon != null) {
				if (!pemohon.trim().equals("")) {
					sql = "SELECT F.TAJUK_FAIL,F.ID_FAIL,F.NO_FAIL,P.TUJUAN, P.ID_PERMOHONAN, P.ID_STATUS,P.TARIKH_TERIMA, RS.KETERANGAN,RN.NAMA_NEGERI"
						+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P, TBLRUJSTATUS RS, TBLHTPPEMOHON TPP,TBLRUJNEGERI RN "
						+ " WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_STATUS = RS.ID_STATUS AND P.ID_PERMOHONAN = PP.ID_PERMOHONAN(+) " 
						+ " AND F.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '"+IdUrusan+"' AND f.tajuk_Fail LIKE '%"+tajukFail+"%' ";

					sql += " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ pemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			sql +=" AND F.NO_FAIL LIKE '%"+noFail+"%' ";
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")){
	    	  sql +=" AND F.ID_NEGERI = "+idNegeri;
			}
			if(idKementerian != null && !idKementerian.equals("") && !idKementerian.equals("0")){
		    	  sql +=" AND F.ID_KEMENTERIAN = "+idKementerian;
			}
			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)) {
		    	  sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
		    }
			//tarikhTerima
			if (tarikhPohon != null) {
				if (!tarikhPohon.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(P.TARIKH_TERIMA,'dd-MON-YY') = '" + sdfyy.format(sdf.parse(tarikhPohon)).toUpperCase() +"'";
				}
			}
			
			sql +=" ORDER BY P.ID_PERMOHONAN desc";
			//
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("id_Fail"));
				permohonan.setNamaNegeri(rs.getString("nama_Negeri"));
				permohonan.setIdPermohonan(rs.getLong("id_Permohonan"));
				fail.setNoFail(rs.getString("no_Fail"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("id_htppermohonan"));
				//htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("keterangan"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				v.addElement(htpPermohonan);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			 if (db != null){
		    	  db.close();
		      }
		}
		return v;
	}
	
	@Override
	public BorangK carianFailPPT(String noFail, String idHakmilik) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String sql = "";
		BorangK borang = null;
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
				
			sql ="SELECT * FROM  VIEWBORANGKHTP WHERE ID_HAKMILIK="+idHakmilik;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				borang = new BorangK();
				borang.setIdHakmilik(rs.getString("ID_HAKMILIK"));
				borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				borang.setTarikhBorangK(rs.getString("TARIKH_BORANGK"));
				borang.setNoLot(rs.getString("NO_LOT"));
				borang.setIdLot(rs.getString("ID_LOT"));
				borang.setNoFail(rs.getString("NO_FAIL"));
				borang.setIdMukim(rs.getString("ID_MUKIM"));
				borang.setIdDaerah(rs.getString("ID_DAERAH"));
				borang.setIdNegeri(rs.getString("ID_NEGERI"));
				//borang.setNoHakmilik(rs.getString("NO_HAKMILIK"));
				borang.setIdJenisHakmilik(rs.getString("ID_JENISHAKMILIK"));
				borang.setLuas(rs.getDouble("LUAS_LOT"));
				borang.setIdBorangK(rs.getString("ID_BORANGK"));
				borang.setIdAgensi((rs.getString("ID_AGENSI")==null?"0":rs.getString("ID_AGENSI")));
				borang.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
			}
		}catch(Exception e){
			e.printStackTrace();
		
		} finally {
			if (db != null)
			db.close();
		}
		
		return borang;
	}
	
	@Override
	public Vector<Hashtable<String,String>> carianFail(String noFail
		,String tajukFail
		,String tarikhTerima
		,String namaPemohon
		,String idNegeri
		,String idKementerian
		,String idAgensi) throws Exception {
		
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> senaraiFail = new Vector<Hashtable<String,String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// By Rosli, 05/05/2010
				sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL" +
				" ,B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"+
	      		" ,TO_CHAR(A.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR" +
				" FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN PP, TBLRUJSTATUS C" +
				" ,TBLHTPPEMOHON D,TBLRUJNEGERI RN "+
				" WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
				" AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) "+
				" AND B.ID_PERMOHONAN = PP.ID_PERMOHONAN " +
				" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' " +
				" AND B.ID_STATUS =71 "+ 
				" AND (A.ID_STATUS <> 999 OR A.ID_STATUS IS NULL) ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")&& !idKementerian.equals("99999")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999")){
		    	  sql +=" AND A.ID_NEGERI = "+idNegeri;
			}

			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)&& !idAgensi.equals("99999")) {
			    	  sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
			}				
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdfyy.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			System.out.println("==="+sql);
			//log.info("sql carian fail : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
		    	senaraiFail.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
				db.close();
			
		}
		
		return senaraiFail;
		
	}
	@Override
	public Vector<Hashtable<String, String>> carianFail(String noFail
		,String tajukFail
		,String tarikhTerima
		,String namaPemohon
		,String idNegeri
		,String idKementerian
		,String idAgensi
		,String noHakmilik
		,String noLot
		,String subUrusan) throws Exception {
		
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, String>> senaraiFail = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// By Rosli, 05/05/2010
				sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL" +
				" ,B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"+
	      		" ,TO_CHAR(A.TARIKH_DAFTAR_FAIL,'dd/MM/yyyy') TARIKH_DAFTAR" +
				" FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN PP, TBLRUJSTATUS C" +
				" ,TBLHTPPEMOHON D,TBLRUJNEGERI RN "+
				" WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS " +
				" AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) "+
				" AND B.ID_PERMOHONAN = PP.ID_PERMOHONAN " +
				" AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '3' "+ 
				" AND B.ID_STATUS = 71 "+ 
				" AND (A.ID_STATUS <> 999 OR A.ID_STATUS IS NULL) ";
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")&& !idKementerian.equals("99999")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999")){
		    	  sql +=" AND A.ID_NEGERI = "+idNegeri;
			}

			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)&& !idAgensi.equals("99999")) {
			    sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
			}	
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdfyy.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
			if (noHakmilik != null) {
				if (!noHakmilik.toString().trim().equals("")) {
					sql = sql + " AND B.ID_PERMOHONAN IN ( " +
						"SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIKURUSAN WHERE NO_HAKMILIK ='" + noHakmilik +"')";
				}
			}
			if (noLot != null) {
				if (!noLot.toString().trim().equals("")) {
					sql = sql + " AND B.ID_PERMOHONAN IN ( " +
						"SELECT ID_PERMOHONAN FROM TBLHTPHAKMILIKURUSAN WHERE NO_LOT ='" + noLot +"')";
				}
			}
			if (!subUrusan.equals("99999") && !subUrusan.equals("0")) {
			    sql = sql + " AND A.ID_SUBURUSAN = '"+subUrusan+"' ";
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			myLog.info("sql carian fail: sql " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
		    	h.put("tarikhDaftar", Utils.isNull(rs.getString("TARIKH_DAFTAR")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
				db.close();
			
		}
		
		return senaraiFail;
		
	}
	
	@Override
	public Vector<Hashtable<String,String>> carianFail(String noFail, String tajukFail,String tarikhTerima
			, String namaPemohon,String idNegeri
			,String idKementerian,String idAgensi
			,String idUrusan, String langkah) throws Exception {
		
		Db db = null;
		String sql = "";
		Vector<Hashtable<String,String>> senaraiFail = new Vector<Hashtable<String,String>>();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			// By Rosli, 18/01/2011
			sql = "SELECT A.TAJUK_FAIL,A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN,RN.NAMA_NEGERI"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN PP, TBLRUJSTATUS C"
				+ " , TBLHTPPEMOHON D,TBLRUJNEGERI RN, TBLRUJSUBURUSANSTATUS RSUS,TBLRUJSUBURUSANSTATUSFAIL RSUSF "
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND RSUS.ID_STATUS = C.ID_STATUS "
				+ " AND B.ID_PERMOHONAN = D.ID_PERMOHONAN(+) "
				+ " AND B.ID_PERMOHONAN = PP.ID_PERMOHONAN "
				+ " AND RSUS.ID_SUBURUSANSTATUS = RSUSF.ID_SUBURUSANSTATUS "
				+ " AND (A.ID_STATUS <> 999 OR A.ID_STATUS IS NULL) AND RSUSF.AKTIF = '1' "
				+ " AND  B.ID_PERMOHONAN=RSUSF.ID_PERMOHONAN AND B.ID_FAIL = RSUSF.ID_FAIL "
				+ " AND B.ID_STATUS =71 "
				+ " AND A.ID_NEGERI=RN.ID_NEGERI AND A.ID_URUSAN = '"+idUrusan+"' ";

			//Tajuk Fail
			if (langkah != null) {
				if (!langkah.trim().equals("")) {
					sql = sql + " AND UPPER(RSUS.LANGKAH) = '"+langkah+"'";
				}
			}			
			
			//Tajuk Fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//Nama Permohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(D.NAMA_PEMOHON) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			if(idKementerian != null){
				if(!idKementerian.trim().equals("")&& !idKementerian.equals("99999") && !idKementerian.equals("0")){
					sql = sql + " and A.ID_KEMENTERIAN ="+idKementerian;
				}
			}
			
			if(idNegeri != null && !idNegeri.equals("") && !idNegeri.equals("0")&& !idNegeri.equals("99999")){
		    	  sql +=" AND A.ID_NEGERI = "+idNegeri;
			}

			if (idAgensi!= null && !"-1".equals(idAgensi) && !"".equals(idAgensi)&& !idAgensi.equals("99999") && !idAgensi.equals("0")) {
			    	  sql = sql + " AND PP.id_agensi = '"+idAgensi+"' ";
			}				
			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdfyy.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}
						
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
//			myLog.info("carianFail:sql=" + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
		    	h.put("tajuk", Utils.isNull(rs.getString("TAJUK_FAIL")));
			    h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
		    	h.put("negeri", rs.getString("NAMA_NEGERI"));
		    	senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
		return senaraiFail;
	}
	
	@Override
	public HtpPermohonan kemaskiniPermohonan(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String idFail = null;

		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();		
			permohonan = htpPermohonan.getPermohonan();
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";

			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";

			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			if(!TarikhSurKJP.equals("")){
				r.add("TARIKH_SURAT", r.unquote(TSKJP));	}
			if(!TarikhPermohonan.equals("")){
				r.add("TARIKH_TERIMA", r.unquote(TP));	}
			//Tambah oleh Mohamad Rosli pada2010/03/15, untuk simpan Tujuan
			r.add("TUJUAN", permohonan.getTujuan());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");			
			//myLog.info(sql);
			stmt.executeUpdate(sql);
			
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			if(!idhtpPermohonan.equals("")){
				r.update("id_Htppermohonan",idhtpPermohonan);	}
			r.update("id_Permohonan",idPermohonan);
			r.add("NO_RUJUKAN_KJP",htpPermohonan.getNoRujukanKJP());
			if(!htpPermohonan.getNoRujukanLain().equals("")){
				r.add("NO_RUJUKAN_LAIN", htpPermohonan.getNoRujukanLain());	}
			if(!htpPermohonan.getNoRujukanUPT().equals("")){
				r.add("NO_RUJUKAN_UPT",htpPermohonan.getNoRujukanUPT());	}			
			if(!htpPermohonan.getNoRujukanPTG().equals("")){
				r.add("NO_RUJUKAN_PTG", htpPermohonan.getNoRujukanPTG());	}
			if(!htpPermohonan.getNoRujukanPTD().equals("")){
				r.add("NO_RUJUKAN_PTD",htpPermohonan.getNoRujukanPTD());	}
			r.add("ID_AGENSI",htpPermohonan.getIdAgensi());
			r.add("ID_DAERAH",htpPermohonan.getIdDaerah()==""?getDaerahDefaultMengikutNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri())):htpPermohonan.getIdDaerah());
//			r.add("ID_JENISTANAH", htpPermohonan.getIdJenisTanah());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			r.add("ID_FAIL");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			sql = r.getSQLSelect("TBLPERMOHONAN");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				idFail = rs.getString("ID_FAIL");
			}
			String TarikhDaftar = htpPermohonan.getPermohonan().getPfdFail().getTarikhDaftarFail();
			String TDF = "to_date('" + TarikhDaftar + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.update("ID_FAIL",idFail);
			r.add("TAJUK_FAIL", permohonan.getTujuan());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("TARIKH_DAFTAR_FAIL", r.unquote(TDF));
			r.add("ID_TARAFKESELAMATAN", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan());
			sql = r.getSQLUpdate("TBLPFDFAIL");
			myLog.info("getSQLUpdate(\"TBLPFDFAIL:sql="+sql);
			stmt.executeUpdate(sql);		
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(db != null)
				db.close();
		}
		
		return htpPermohonan;

	}
	
	@Override
	public HtpPermohonan kemaskiniPermohonanKutipan(HtpPermohonan htpPermohonan,String idPermohonan,String idhtpPermohonan)throws Exception {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String idFail = null;

		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			r = new SQLRenderer();		
			permohonan = htpPermohonan.getPermohonan();
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";

			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";

			r = new SQLRenderer();
			r.update("id_Permohonan", idPermohonan);
			if(!TarikhPermohonan.equals("")){
				r.add("tarikh_Surat", r.unquote(TSKJP));	}
			
			//r.add("tujuan",permohonan.getTujuan());
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			if(!TarikhPermohonan.equals("")){
				r.add("TARIKH_TERIMA", r.unquote(TP));	}
			//Tambah oleh Mohamad Rosli pada2010/03/15, untuk simpan Tujuan
			r.add("TUJUAN", permohonan.getTujuan());

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			htpPermohonan.getIdHtpPermohonan();
			r = new SQLRenderer();
			if(!idhtpPermohonan.equals("")){
				r.update("id_Htppermohonan",idhtpPermohonan);	}
			r.update("id_Permohonan",idPermohonan);
			//r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("NO_RUJUKAN_LAIN", htpPermohonan.getNoRujukanLain());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_DAERAH",htpPermohonan.getIdDaerah()==""?getDaerahDefaultMengikutNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri())):htpPermohonan.getIdDaerah());
			r.add("ID_AGENSI",htpPermohonan.getIdAgensi());
			r.add("ID_JENISTANAH", htpPermohonan.getIdJenisTanah());
			r.add("NO_RUJUKAN_KJP",htpPermohonan.getNoRujukanKJP());
			if(!htpPermohonan.getNoRujukanUPT().equals("")){
				r.add("NO_RUJUKAN_UPT",htpPermohonan.getNoRujukanUPT());	}
			
			if(!htpPermohonan.getNoRujukanPTG().equals("")){
				r.add("NO_RUJUKAN_PTG", htpPermohonan.getNoRujukanPTG());	}
			
			if(!htpPermohonan.getNoRujukanPTD().equals("")){
				r.add("NO_RUJUKAN_PTD",htpPermohonan.getNoRujukanPTD());	}

			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			r.add("ID_FAIL");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			//Tambah oleh Mohamad Rosli pada2010/03/15, untuk tujuan
			sql = r.getSQLSelect("TBLPERMOHONAN");
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				idFail = rs.getString("ID_FAIL");
			}
			String TarikhDaftar = htpPermohonan.getPermohonan().getPfdFail().getTarikhDaftarFail();
			String TDF = "to_date('" + TarikhDaftar + "','dd/MM/yyyy')";
			
			r = new SQLRenderer();
			r.update("ID_FAIL",idFail);
			r.add("TAJUK_FAIL", permohonan.getTujuan());
			r.add("ID_KEMASKINI", permohonan.getIdMasuk());
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("TARIKH_DAFTAR_FAIL", r.unquote(TDF));
			r.add("ID_TARAFKESELAMATAN", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan());
			//Tambah oleh Mohamad Rosli pada2010/03/15, untuk simpan id_negeri dan id_kementerian
			r.add("ID_KEMENTERIAN",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()));
			r.add("ID_NEGERI",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()));
			r.add("ID_SUBURUSAN",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()));
			r.add("NO_FAIL",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getNoFail()));

			sql = r.getSQLUpdate("TBLPFDFAIL");
			myLog.info(sql);
			stmt.executeUpdate(sql);		
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			if(db != null)
				db.close();
		}
		
		return htpPermohonan;

	}

	@Override
	public void hapusBayaran(String idBayaran) {
		Db db = null;
		Connection conn = null;
		//SQLRenderer r = null;
		String sql = null;
		try{
			db = new Db();
            conn = db.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = db.getStatement();
            sql ="DELETE FROM TBLHTPBAYARAN WHERE ID_BAYARAN ="+idBayaran;
            stmt.executeQuery(sql);
            conn.commit();
            
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
	}
	
	@Override
	public HtpPermohonan findPermohonan(String idPermohonan,String idHtpPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		//Date now = new Date();
		//String tarikhDaftarFail = "to_date('" + sdfMasa.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT A.id_Permohonan,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.TAJUK_FAIL" +
					",C.ID_KEMENTERIAN,H.ID_KEMENTERIAN ID_AGENSIID_KEMENTERIAN,B.ID_AGENSI,C.ID_URUSAN,C.NO_FAIL, ";
				sql += " TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') as TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN, ";
				sql += " B.ID_JENISTANAH,B.ID_PEGAWAI,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH,";
					sql += " B.NO_RUJUKAN_UPT,B.NO_RUJUKAN_PTG,B.NO_RUJUKAN_PTD,";
						sql += " C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE, ";
						sql +=" G.NAMA_KEMENTERIAN, H.NAMA_AGENSI, E.ID_SUBURUSAN,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN,A.NO_PERMOHONAN";
							sql += " from TBLPERMOHONAN A, TBLHTPPERMOHONAN B , TBLPFDFAIL C , TBLpfdrujtarafkeselamatan D, tblrujsuburusan E,TBLRUJNEGERI F,TBLRUJKEMENTERIAN G,TBLRUJAGENSI H ";
								sql += " where A.ID_PERMOHONAN=B.ID_PERMOHONAN ";
									sql += " And C.ID_SUBURUSAN = E.ID_SUBURUSAN ";
										sql += " AND D.ID_TARAFKESELAMATAN(+) = C.ID_TARAFKESELAMATAN ";
											sql += " AND C.ID_FAIL = A.ID_FAIL ";
												sql += " AND A.ID_PERMOHONAN = '"+ idPermohonan +"'";
												sql += " AND F.ID_NEGERI = C.ID_NEGERI";
												sql += " AND G.ID_KEMENTERIAN(+) = C.ID_KEMENTERIAN";
												sql += " AND B.ID_AGENSI = H.ID_AGENSI(+)";
											if(!idHtpPermohonan.equals("")){
												sql += " AND B.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
											}
											sql +=" ORDER BY A.id_Permohonan desc";
//											myLog.info("Permohonan:::findPermohonan::sql::"+sql);
											
											 Statement stmt = db.getStatement();
										     ResultSet rs = stmt.executeQuery(sql);		
										     
										    
										     if(rs.next()){
										    	fail = new PfdFail();
										 		permohonan = new Permohonan();
										 		htpPermohonan = new HtpPermohonan();
										 		
//										 		 String TarikhSurKJP = rs.getString("TARIKH_SURAT");
//											     String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";	
//											     String TarikhPermohonan = rs.getString("TARIKH_TERIMA");
//											     String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
											     
										 			htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
										 			htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
										 			htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
										 			htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
										 			htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
										 			htpPermohonan.setNoRujukanUPT(rs.getString("NO_RUJUKAN_UPT"));
										 			htpPermohonan.setNoRujukanPTG(rs.getString("NO_RUJUKAN_PTG"));
										 			htpPermohonan.setNoRujukanPTD(rs.getString("NO_RUJUKAN_PTD"));
										 			htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
										 			htpPermohonan.setIdDaerah(rs.getString("ID_DAERAH")==null?"0":rs.getString("ID_DAERAH"));
										 			permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
										 			permohonan.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
										 			permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
										 			permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
										 			permohonan.setTujuan(rs.getString("TUJUAN"));
										 			permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
										 			fail.setNoFail(rs.getString("NO_FAIL"));
										 			if(rs.getString("ID_KEMENTERIAN")==null){
										 				fail.setIdKementerian(rs.getString("ID_AGENSIID_KEMENTERIAN"));	
										 			}else{
										 				fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));	
										 				
										 			}
										 			fail.setIdNegeri(rs.getString("ID_NEGERI"));
										 			fail.setIdUrusan(rs.getLong("ID_URUSAN"));
										 			fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
										 			//fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN"));
										 			fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"1":rs.getString("ID_TARAFKESELAMATAN"));
										 			fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
										 			fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
										 			fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
										 			fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
										 			fail.setTajukFail(rs.getString("TAJUK_FAIL"));
										 			fail.setIdFail(rs.getLong("ID_FAIL"));
										 			permohonan.setPfdFail(fail);
										 			htpPermohonan.setPermohonan(permohonan);
										 			
										 			conn.commit();
										 			
										     }
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
	}
	
	@Override
	public HtpPermohonan findPermohonanKutipan(String idPermohonan,String idHtpPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT A.ID_PERMOHONAN,A.NO_PERMOHONAN,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.TAJUK_FAIL" +
				" ,C.ID_KEMENTERIAN,H.ID_KEMENTERIAN ID_AGENSIID_KEMENTERIAN,B.ID_AGENSI,C.ID_URUSAN,C.NO_FAIL" +
				" ,TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') as TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN" +
				" ,NVL(B.ID_JENISTANAH,0) ID_JENISTANAH,B.ID_PEGAWAI" +
				" ,NVL(B.NO_RUJUKAN_KJP,'TIADA') NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH" +
				" ,B.NO_RUJUKAN_UPT,NVL(B.NO_RUJUKAN_PTG,'TIADA') NO_RUJUKAN_PTG,NVL(B.NO_RUJUKAN_PTD,'TIADA') NO_RUJUKAN_PTD " +
				" ,C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE" +
				" ,G.NAMA_KEMENTERIAN, H.NAMA_AGENSI" +
				" ,NVL((SELECT ID_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = E.ID_SUBURUSAN),'0') ID_SUBURUSAN "+
				" ,NVL((SELECT NAMA_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = E.ID_SUBURUSAN),'') NAMA_SUBURUSAN "+
				" ,NVL((SELECT KOD_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = E.ID_SUBURUSAN),'0') KOD_SUBURUSAN "+
				" ,NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU " +
						" WHERE RU.ID_URUSAN = C.ID_URUSAN) " +
						" ,'') NAMA_URUSAN " +
				"";
//				", E.ID_SUBURUSAN,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN";
			sql += " FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B, TBLPFDFAIL C, TBLPFDRUJTARAFKESELAMATAN D" +
				",TBLRUJSUBURUSAN E" +
				",TBLRUJNEGERI F,TBLRUJKEMENTERIAN G,TBLRUJAGENSI H "+
				" WHERE A.ID_PERMOHONAN=B.ID_PERMOHONAN ";
//				sql += " AND C.ID_SUBURUSAN = E.ID_SUBURUSAN(+) ";
			sql += " AND D.ID_TARAFKESELAMATAN(+) = C.ID_TARAFKESELAMATAN ";
			sql += " AND C.ID_FAIL = A.ID_FAIL AND (C.ID_STATUS<>'999' OR C.ID_STATUS IS NULL) ";
			sql += " AND A.ID_PERMOHONAN = '"+ idPermohonan +"'";
			sql += " AND F.ID_NEGERI(+) = C.ID_NEGERI";
			sql += " AND G.ID_KEMENTERIAN(+) = C.ID_KEMENTERIAN";
			sql += " AND B.ID_AGENSI = H.ID_AGENSI(+)";
			if(!idHtpPermohonan.equals("")){
				sql += " AND B.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
			}
			sql +=" ORDER BY A.id_Permohonan desc";
			//myLog.info("Permohonan:::findPermohonan::sql::"+sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			if(rs.next()){
				fail = new PfdFail();
				permohonan = new Permohonan();
				htpPermohonan = new HtpPermohonan();
//				String TarikhSurKJP = rs.getString("TARIKH_SURAT");
//				String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";	
//				String TarikhPermohonan = rs.getString("TARIKH_TERIMA");
//				String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
				htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
				htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
				htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
				htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
				htpPermohonan.setNoRujukanUPT(rs.getString("NO_RUJUKAN_UPT"));
				htpPermohonan.setNoRujukanPTG(rs.getString("NO_RUJUKAN_PTG"));
				htpPermohonan.setNoRujukanPTD(rs.getString("NO_RUJUKAN_PTD"));
				htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				htpPermohonan.setIdDaerah(rs.getString("ID_DAERAH")==null?"0":rs.getString("ID_DAERAH"));
				permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
				permohonan.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
				permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				if(rs.getString("ID_KEMENTERIAN")==null){
					fail.setIdKementerian(rs.getString("ID_AGENSIID_KEMENTERIAN"));	
									 		
				}else{
					fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));	
										 				
				}
				fail.setIdNegeri(rs.getString("ID_NEGERI"));
				fail.setIdUrusan(rs.getLong("ID_URUSAN"));
				fail.setNamaUrusan(rs.getString("NAMA_URUSAN"));
				fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
				//fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN"));
				fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"1":rs.getString("ID_TARAFKESELAMATAN"));
				fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
				fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
				fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
				fail.setTajukFail(rs.getString("TAJUK_FAIL"));
				fail.setIdFail(rs.getLong("ID_FAIL"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
									 			
				conn.commit();
										 			
			}
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
		
	}		
	
	@Override
	public HtpPermohonan findPermohonan(String idFail, String idPermohonan,String idHtpPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		//Date now = new Date();
		//String tarikhDaftarFail = "to_date('" + sdfMasa.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "select A.id_Permohonan,B.ID_HTPPERMOHONAN,C.ID_FAIL,C.TAJUK_FAIL,C.ID_KEMENTERIAN,B.ID_AGENSI" +
			" ,C.ID_URUSAN,C.NO_FAIL " +
			" ,NVL((SELECT RU.NAMA_URUSAN FROM TBLRUJURUSAN RU " +
			" WHERE RU.ID_URUSAN = C.ID_URUSAN) " +
			" ,'TIADA MAKLUMAT') NAMA_URUSAN " +
			" , E.ID_SUBURUSAN, ";
				sql += " TO_CHAR(A.TARIKH_SURAT,'DD/MM/YYYY') as TARIKH_SURAT,TO_CHAR(A.TARIKH_TERIMA,'DD/MM/YYYY') AS TARIKH_TERIMA,A.TUJUAN, ";
					sql += " B.ID_JENISTANAH,B.ID_PEGAWAI,B.NO_RUJUKAN_KJP,B.NO_RUJUKAN_LAIN,B.TARIKH_AGIHAN,B.ID_DAERAH,";
						sql += " C.ID_NEGERI,C.ID_STATUS, D.TARAF_KESELAMATAN,D.ID_TARAFKESELAMATAN,F.NAMA_NEGERI,TO_CHAR(C.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS OPEN_FILE_DATE, ";
						sql +=" G.NAMA_KEMENTERIAN, H.NAMA_AGENSI,E.NAMA_SUBURUSAN,E.KOD_SUBURUSAN,A.NO_PERMOHONAN";
							sql += " from TBLPERMOHONAN A, TBLHTPPERMOHONAN B , TBLPFDFAIL C , TBLpfdrujtarafkeselamatan D, tblrujsuburusan E,TBLRUJNEGERI F,TBLRUJKEMENTERIAN G,TBLRUJAGENSI H ";
								sql += " where A.ID_PERMOHONAN=B.ID_PERMOHONAN ";
									sql += " And C.ID_SUBURUSAN = E.ID_SUBURUSAN ";
										sql += " AND D.ID_TARAFKESELAMATAN(+) = C.ID_TARAFKESELAMATAN ";
											sql += " AND C.ID_FAIL = A.ID_FAIL ";
												sql += " AND A.ID_FAIL = '"+ idFail +"'";
												sql += " AND F.ID_NEGERI = C.ID_NEGERI";
												sql += " AND G.ID_KEMENTERIAN = C.ID_KEMENTERIAN";
												sql += " AND B.ID_AGENSI = H.ID_AGENSI";
											if(!idPermohonan.equals("")){
												sql += " AND A.ID_PERMOHONAN = '"+ idPermohonan +"'";
											}
											if(!idHtpPermohonan.equals("")){
												sql += " AND B.ID_HTPPERMOHONAN = '"+ idHtpPermohonan +"'";
											}
											sql +=" ORDER BY A.id_Permohonan desc";
//											myLog.info("Permohonan:::findPermohonan::sql::"+sql);
											
											 Statement stmt = db.getStatement();
										     ResultSet rs = stmt.executeQuery(sql);		
										     
										    
										     if(rs.next()){
										    	fail = new PfdFail();
										 		permohonan = new Permohonan();
										 		htpPermohonan = new HtpPermohonan();
										 		
//										 		 String TarikhSurKJP = rs.getString("TARIKH_SURAT");
//											     String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";	
//											     String TarikhPermohonan = rs.getString("TARIKH_TERIMA");
//											     String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
											     
										 			htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
										 			htpPermohonan.setIdAgensi(rs.getLong("ID_AGENSI"));
										 			htpPermohonan.setIdJenisTanah(rs.getString("ID_JENISTANAH"));
										 			htpPermohonan.setNoRujukanKJP(rs.getString("NO_RUJUKAN_KJP"));
										 			htpPermohonan.setNoRujukanLain(rs.getString("NO_RUJUKAN_LAIN"));
										 			htpPermohonan.setNamaAgensi(rs.getString("NAMA_AGENSI"));
										 			permohonan.setNoPermohonan(rs.getString("NO_PERMOHONAN"));
										 			permohonan.setIdPermohonan(rs.getString("id_Permohonan"));
										 			permohonan.setTarikhSurat(rs.getString("TARIKH_SURAT"));
										 			permohonan.setTarikhTerima(rs.getString("TARIKH_TERIMA"));
										 			permohonan.setTujuan(rs.getString("TUJUAN"));
										 			permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
										 			fail.setNoFail(rs.getString("NO_FAIL"));
										 			fail.setIdKementerian(rs.getString("ID_KEMENTERIAN"));
										 			fail.setIdNegeri(rs.getString("ID_NEGERI"));
										 			fail.setNamaUrusan(rs.getString("NAMA_URUSAN"));
										 			fail.setIdUrusan(rs.getLong("ID_URUSAN"));
										 			fail.setIdSubUrusan(rs.getString("ID_SUBURUSAN"));
										 			fail.setIdTarafKeselamatan(rs.getString("ID_TARAFKESELAMATAN")==null?"1":rs.getString("ID_TARAFKESELAMATAN"));
										 			fail.setTarikhDaftarFail(rs.getString("OPEN_FILE_DATE"));
										 			fail.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
										 			fail.setNamaSuburusan(rs.getString("NAMA_SUBURUSAN"));
										 			fail.setKodSuburusan(rs.getString("KOD_SUBURUSAN"));
										 			fail.setTajukFail(rs.getString("TAJUK_FAIL"));
										 			fail.setIdFail(rs.getLong("ID_FAIL"));
										 			permohonan.setPfdFail(fail);
										 			htpPermohonan.setPermohonan(permohonan);
										 			
										 			conn.commit();
										 			
										     }
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
		
	}	
	
	@Override
	public Hashtable<String,String> getInfoSelesaiPermohonan(String idPermohonan)throws Exception {		
	    Db db = null;
	    Hashtable<String,String> h = null;
	    String sql = "";
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    		sql += " ,F.ID_MASUK,ST.ID_SUBURUSANSTATUS,STF.URL CATATAN" +
						",TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    				",TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI " +
	    				" FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    		sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE " ;
	    		sql += " a.id_fail = f.id_fail ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    		sql += " AND F.id_status <> '999' ";
	    		sql += " AND ST.LANGKAH = '99' ";
	    		sql += " AND stf.aktif = 1 ";
	    		sql += " AND STF.ID_PERMOHONAN = '" + idPermohonan + "'";
	    		//myLog.info("getInfoSelesaiPermohonan:sql="+sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      	
			  
	    		while (rs.next()) {
	        			h = new Hashtable<String,String>();  
	    				h.put("level",rs.getString("ID_MASUK"));	 
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("tarikhSelesai", Utils.isNull(rs.getString("TARIKH_SELESAI")));

	    		}
	    	  
	      return h;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list
	
	@Override
	public Hashtable<String,String> getInfoTamatSelesaiPermohonan(String idPermohonan)throws Exception {		
	    Db db = null;
	    Hashtable<String,String> h = null;
	    String sql = "";
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    		sql += " ,f.id_masuk,st.id_suburusanstatus,stf.url_catatan" +
	    				" ,to_char(stf.tarikh_masuk,'dd/mm/yyyy') tarikh_masuk " +
	    				" ,to_char(stf.tarikh_kemaskini,'dd/mm/yyyy') tarikh_selesai " +
	    				" FROM tblpermohonan a, tblpfdfail f, tblrujstatus s, ";
	    		sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE " ;
	    		sql += " a.id_fail = f.id_fail ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND stf.id_fail = a.id_fail ";
	    		sql += " AND st.langkah = '98' ";
	    		//2012/10/08
	    		//sql += " AND stf.aktif = 1 ";
	    		sql += " AND stf.id_permohonan = '" + idPermohonan + "'";
	    		//myLog.info("getInfoSelesaiPermohonan:sql="+sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      	
			  
	    		while (rs.next()) {
	        		h = new Hashtable<String,String>();  
	    			h.put("level",rs.getString("ID_MASUK"));	 
	    			h.put("id_permohonan", rs.getString("id_permohonan"));
	    			h.put("id_status", rs.getString("id_suburusan"));
	   				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	   				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	   				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	   				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
    				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    			h.put("tarikhSelesai", Utils.isNull(rs.getString("TARIKH_SELESAI")));

	    		}  
	      return h;
	      
	    } finally {
	      if (db != null) db.close();
	    }	    
	    
	  }//close list
	
	@Override
	public Hashtable<String,String> getInfoSelesaiPermohonan(String idPermohonan,String langkah)throws Exception {		
	    Db db = null;
	    Hashtable<String,String> h = null;
	    String sql = "";
	    try {	    	
	    		db = new Db();
	    		Statement stmt = db.getStatement();	    		
	    		//SYARAT
	    		sql = "SELECT distinct f.id_fail, f.no_fail, a.id_permohonan, ";
	    		sql += " a.id_status, s.keterangan, stf.id_suburusanstatusfail, stf.aktif,f.id_suburusan ";
	    		sql += " ,F.ID_MASUK,ST.ID_SUBURUSANSTATUS,STF.URL CATATAN" +
	    				",TO_CHAR(STF.TARIKH_MASUK,'dd/mm/yyyy') TARIKH_MASUK " +
	    				",TO_CHAR(STF.TARIKH_KEMASKINI,'dd/mm/yyyy') TARIKH_SELESAI " +
	    				"FROM Tblpermohonan a, Tblpfdfail f, Tblrujstatus s, ";
	    		sql += " tblrujsuburusanstatus st, tblrujsuburusanstatusfail stf  ";
	    		sql += " WHERE " ;
	    		sql += " a.id_fail = f.id_fail ";
	    		sql += " AND st.id_status = s.id_status ";
	    		sql += " AND stf.id_suburusanstatus = st.id_suburusanstatus ";
	    		sql += " AND stf.id_permohonan = a.id_permohonan ";
	    		sql += " AND stf.ID_FAIL = A.ID_FAIL ";
	    		sql += " AND F.id_status <> '999' ";
	    		sql += " AND ST.LANGKAH = '"+langkah+"' ";
	    		sql += " AND stf.aktif = 1 ";
	    		sql += " AND STF.ID_PERMOHONAN = '" + idPermohonan + "'";
	    		//myLog.info("getInfoSelesaiPermohonan 2:sql="+sql);
	    		ResultSet rs = stmt.executeQuery(sql);	      	
			  
	    		while (rs.next()) {
	        			h = new Hashtable<String,String>();  
	    				h.put("level",rs.getString("ID_MASUK"));	 
	    				h.put("id_permohonan", rs.getString("id_permohonan"));
	    				h.put("id_status", rs.getString("id_suburusan"));
	    				h.put("id_suburusanstatusfail", rs.getString("id_suburusanstatusfail"));
	    				h.put("id_suburusanstatus", rs.getString("id_suburusanstatus"));
	    				h.put("id_fail", rs.getString("id_fail")==null?"":rs.getString("id_fail"));
	    				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
	    				h.put("catatan", Utils.isNull(rs.getString("CATATAN")));
	    				h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    				h.put("tarikhSelesai", Utils.isNull(rs.getString("TARIKH_SELESAI")));

	    		}
	    	  
	      return h;
	    } finally {
	      if (db != null) db.close();
	    }	    
	  }//close list

	@Override
	public void hapusHakmilik(String idHakmilikUrusan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
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
	
	@Override
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			r.update("aktif", "1");
			r.add("aktif",s.getAktif());
			r.add("id_kemaskini", sBaru.getIdKemaskini());
			r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
			myLog.info("kemaskini:sql="+sql+","+sBaru.getTarikhMasuk());
			stmt.executeUpdate(sql);
			
			long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			r = new SQLRenderer();		  
			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
			r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
			r.add("aktif",sBaru.getAktif());
			r.add("URL",sBaru.getUrl());
			r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			myLog.info("insert:sql="+sql+","+sBaru.getTarikhMasuk());
		    stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
		    	    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		  
	}
	
	@Override
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,Tblrujsuburusanstatusfail sBaru,String strTarikh) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			r.update("aktif", "1");
			r.add("aktif",s.getAktif());
			r.add("id_kemaskini", sBaru.getIdKemaskini());
			r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
			myLog.info("update:sql="+sql);
			stmt.executeUpdate(sql);
			
			long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			r = new SQLRenderer();		  
			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			r.add("id_Permohonan", String.valueOf(s.getIdPermohonan()));
			r.add("Id_Suburusanstatus", r.unquote(String.valueOf(sBaru.getIdSuburusanstatus())));
			r.add("aktif",sBaru.getAktif());
			r.add("URL",sBaru.getUrl());
			r.add("id_Masuk", String.valueOf(sBaru.getIdMasuk()));
			//String tarikMasuk = "to_date('" + strTarikh + "','dd/MM/yyyy')";
			String tarikMasuk = "sysdate";
			r.add("tarikh_Masuk", r.unquote(tarikMasuk));
			r.add("id_kemaskini", String.valueOf(sBaru.getIdMasuk()));
			//String tarikhKemaskini = "sysdate";
			//myLog.info("txttarikhsuratPTGPTD 3 ="+strTarikh);
			String tarikhKemaskini = "to_date('" + strTarikh + "','dd/MM/yyyy')";
			r.add("tarikh_kemaskini", r.unquote(tarikhKemaskini));
			r.add("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			myLog.info("insert:sql="+sql);
		    stmt.executeUpdate(sql);
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Pendaftaran Permohonan:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		  
	}	
	
	@Override
	public void kemaskiniSimpanStatusPermohonanAktif(Tblrujsuburusanstatusfail s,String strTarikh) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_permohonan",r.unquote(String.valueOf(s.getIdPermohonan())));
			r.update("id_fail",r.unquote(String.valueOf(s.getIdFail())));
			r.update("aktif", "1");
			r.add("URL",s.getUrl());
			String tarikMasuk = "to_date('" + strTarikh + "','dd/MM/yyyy')";
			r.add("tarikh_Masuk", r.unquote(tarikMasuk));
			r.add("id_kemaskini", s.getIdKemaskini());
			r.add("tarikh_kemaskini", r.unquote("sysdate")); 
			sql = r.getSQLUpdate("Tblrujsuburusanstatusfail");
			stmt.executeUpdate(sql);		
		    conn.commit();
	
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Kemaskini Status:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}		  
	}	
	
	@Override
	public String SelectJenisHakmilik(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectJenisHakmilik(selectName, selectedValue, disability, null);
	}

	@Override
	public String SelectJenisHakmilik(String selectName,
		Long selectedValue, String disability, String jsFunction) throws Exception{
		StringBuffer sb = new StringBuffer("");
		try {
//			sb.append("<select name='" + selectName + "'");
//			if (disability != null)
//				sb.append(disability);
//			if (jsFunction != null)
//				sb.append(jsFunction);
//			sb.append(" > ");
//			sb.append("<option value=>SILA PILIH</option>\n");

			Vector<Tblrujjenishakmilik> v = DB.getJenisHakmilik();
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (f.getIdJenishakmilik().equals(selectedValue)) {
					s = "selected";
					sb.append(f.getKodJenisHakmilik().toUpperCase() + " - "
					+ f.getKeterangan().toUpperCase());
				} else {
					s = "";
				}
//				sb.append("<option " + s + " value=" + f.getIdJenishakmilik()
//						+ ">" + f.getKodJenisHakmilik().toUpperCase() + " - "
//						+ f.getKeterangan().toUpperCase() + "</option>\n");
			}
			//sb.append("</select>");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
		
	}

	@Override
	public String getDaerahMengikutBandar(String idBandar) throws Exception {
		Db db = null;
		String sql = "";
		String returnValue = "0";
		String negeriValue = "0";
		sql = "Select id_daerah,id_negeri from tblrujbandar"
				+ " where id_bandar='" + idBandar +"'"
				+ " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getString("id_daerah");
				negeriValue = rs.getString("id_negeri");
			}
			if(returnValue.equals("0")){
				returnValue = getDaerahDefaultMengikutNegeri(negeriValue);
			}	
			return returnValue;
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@Override
	public Pemohon carianPemohonMengikutPermohonan(String idPermohonan) {
		Db db = null;
		Connection conn = null;
		SQLRenderer r = null;
		String sql = null;
		Pemohon pemohon = null;
		try{
			db = new Db();
			conn = db.getConnection();
			Statement stmt = db.getStatement();
			r = new SQLRenderer();
			r.add("id_permohonan");
            r.add("id_pemohon");
            r.add("no_pemohon");
            r.add("no_pa");
            r.add("nama_pemohon");
            r.add("alamat_pemohon1");
            r.add("alamat_pemohon2");
            r.add("alamat_pemohon3");
            r.add("poskod");
            r.add("id_daerah");
            r.add("id_negeri");
            r.add("no_tel");
            r.add("no_fax");
            r.add("flag_penjualpemilik");
            r.add("emel");
            r.set("id_permohonan",idPermohonan);
            sql = r.getSQLSelect("TBLHTPPEMOHON");
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				pemohon = new Pemohon();
				pemohon.setIdPemohon(rs.getLong("id_pemohon"));
				pemohon.setNoPemohon(rs.getString("no_pemohon"));
				pemohon.setNoPA(rs.getString("no_pa"));
				pemohon.setNama(rs.getString("nama_pemohon"));
				pemohon.setAlamat1(rs.getString("alamat_pemohon1"));
				pemohon.setAlamat2(rs.getString("alamat_pemohon2"));
				pemohon.setAlamat3(rs.getString("alamat_pemohon3"));
				pemohon.setPoskod(rs.getString("poskod"));
				pemohon.setIdNegeri(rs.getString("id_negeri"));
				pemohon.setIdDaerah(rs.getString("id_daerah"));
				pemohon.setFlagPemilik(rs.getString("flag_penjualpemilik"));
				pemohon.setTel(rs.getString("no_tel"));
				pemohon.setFax(rs.getString("no_fax"));
				pemohon.setEmel(rs.getString("emel"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(db != null)
				db.close();
		}
		return pemohon;
	}
	
	@Override
	public Vector<HtpPermohonan> getSenaraiFailMengikutUrusanDanPengguna(String idUrusan
		,String carian
		,String noFail
		,String idNegeri
		,String user) throws Exception {
		Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail TUJUAN, s.keterangan, n.nama_Negeri," +
					" n.kod_Mampu,p.id_Permohonan,PP.ID_HTPPERMOHONAN ";
			sql += "FROM Tblpfdfail f, Tblpermohonan p,TBLHTPPERMOHONAN PP, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
			sql += "WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = PP.id_Permohonan  " +
					" AND p.id_Permohonan = sf.id_Permohonan " +
					" AND f.id_Fail = SF.id_Fail AND n.id_Negeri = f.id_Negeri ";
			sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql += "AND sf.aktif = '1' AND F.FLAG_JENIS_FAIL='3' AND F.ID_URUSAN in (" + idUrusan +") ";
			if (idNegeri != "0" )
				sql += "AND f.id_Negeri = " + idNegeri;
			if (user != null)
				sql += " AND F.ID_MASUK = " + user;
			if(carian != null)
				sql +=" AND f.tajuk_Fail LIKE '%" + carian + "%' ";
			if(noFail != null)
				sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
			sql += " ORDER BY n.kod_Mampu";
//			myLog.info(":getSenaraiFailMengikutUrusanDanPengguna::sql = "+sql);

			ResultSet rs = stmt.executeQuery(sql);
			//Hashtable<String, String> h=null;
			while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setIdFail(rs.getLong("ID_FAIL"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				permohonan.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("KETERANGAN"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				list.addElement(htpPermohonan);
				
			}
			return list;
		} finally {
			if (db != null)
				db.close();
			
			//return list;
		}
		
	}
	
	@Override
	public Vector<HtpPermohonan> getSenaraiFailMengikutUrusanDanPengguna(String idUrusan, String carian, String noFail,
			String idNegeri) throws Exception {
		Vector<HtpPermohonan> list = new Vector<HtpPermohonan>();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail TUJUAN, s.keterangan, n.nama_Negeri," +
					" n.kod_Mampu,p.id_Permohonan,PP.ID_HTPPERMOHONAN ";
			sql += "FROM Tblpfdfail f, Tblpermohonan p,TBLHTPPERMOHONAN PP, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, Tblrujstatus s, Tblrujnegeri n ";
			sql += "WHERE f.id_Fail = p.id_Fail AND p.id_Permohonan = PP.id_Permohonan  " +
					" AND p.id_Permohonan = sf.id_Permohonan " +
					" AND f.id_Fail = SF.id_Fail AND n.id_Negeri = f.id_Negeri ";
			sql += "AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status ";
			sql += "AND sf.aktif = '1' AND F.ID_URUSAN ";

			if (idUrusan != "" )
				sql += " in (" + idUrusan +")" ;
			if (idNegeri != "0" )
				sql += "AND f.id_Negeri = " + idNegeri;
			if(carian != null)
				sql +=" AND f.tajuk_Fail LIKE '%" + carian + "%' ";
			if(noFail != null)
				sql += "AND f.no_Fail LIKE '%" + noFail + "%' ";
			sql += " ORDER BY n.kod_Mampu";
			//myLog.info(":getSenaraiFailMengikutUrusanDanPengguna::sql = "+sql);

			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while (rs.next()) {
				permohonan = new Permohonan();
				fail = new PfdFail();
				htpPermohonan = new HtpPermohonan();
				
				fail.setBil(bil);
				fail.setIdFail(rs.getLong("ID_FAIL"));
				permohonan.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				permohonan.setIdPermohonan(rs.getLong("ID_PERMOHONAN"));
				fail.setNoFail(rs.getString("NO_FAIL"));
				permohonan.setTujuan(rs.getString("TUJUAN"));
				//permohonan.setTujuan(rs.getString("tajuk_Fail"));
				htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
				htpPermohonan.setPermohonan(permohonan);
				htpPermohonan.setStatusPermohonan(rs.getString("KETERANGAN"));
				permohonan.setPfdFail(fail);
				htpPermohonan.setPermohonan(permohonan);
				
				list.addElement(htpPermohonan);
				bil++;
				
			}
			return list;
		} finally {
			if (db != null)
				db.close();
			
		}
	}
	
	/* Created by : Mohamad Rosli 2009/12/22
	 * Tujuan	  : Senarai SubUrusan mengikut role 
	 * Pra syarat : Assign user kepada Role terlebih dahulu
	 */	
	public String getUrusanMengikutPengguna(String login) throws Exception {
		Db db = null;
		String sql = " ";
		String returnValue = "";
		try {
			db = new Db();
    		Statement stmt = db.getStatement();
    		SQLRenderer r = new SQLRenderer();
    		r.add("distinct(rsu.ID_URUSAN)");
    		r.add("ur.ROLE_ID",r.unquote("rrsu.NAME"));
    		r.add("rsu.ID_SUBURUSAN",r.unquote("rrsu.ID_SUBURUSAN"));		    		    
    		if(login!=null)	r.add("ur.user_id",login);
    		
    		sql = r.getSQLSelect("tblrujrolesuburusan rrsu,user_role ur,tblrujsuburusan rsu" );
    		ResultSet rs = stmt.executeQuery(sql);					    		    
			Tblrujurusan s = null;
			while (rs.next()) {
				s = new Tblrujurusan(); 
    			s.setIdUrusan(rs.getLong("id_urusan")); 
    			returnValue = rs.getString("id_urusan");
			}
			return returnValue;
		} finally {
			if (db != null)	db.close();
		}
    	    	
	}
	
	@Override
	public HtpPermohonan simpanPermohonan(HtpPermohonan htpPermohonan)throws Exception{
		Db db = null;
		Connection conn = null;
		Permohonan permohonan = htpPermohonan.getPermohonan();
		SQLRenderer r = null;
		if(permohonan == null)
			throw new Exception("VALUE Permohonan TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");
		PfdFail pfdFail = permohonan.getPfdFail();
		//String kodUrusan = null;
		//String kodMampu = null;
		if(pfdFail == null)
			throw new Exception("VALUE PfdFail TIDAK DIPEROLEHI SILA PERIKSA METHOD simpanPermohonan");

		try{
			String sql = null;
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			//ResultSet rs = null;

			senaraiFail = new FrmSenaraiFailTerimaPohonData();
			KODKEMENTERIAN = senaraiFail.getKementerianByMampu(Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())));
			
			String TarikhDaftar = pfdFail.getTarikhDaftarFail();
			String TDF = "to_date('" + TarikhDaftar + "','dd/MM/yyyy')";

			String noFail = "";
			//KOD_JABATAN+"/101/"+kodUrusan+"/"+KODKEMENTERIAN+"/"+kodMampu+"-"+ File.getSeqNo(IDSEKSYEN, IDURUSAN,Integer.parseInt(String.valueOf(pfdFail.getIdKementerian())),Integer.parseInt(String.valueOf(pfdFail.getIdNegeri()))) ;
			if (!("".equals(pfdFail.getNoFail()))) {
				  noFail = pfdFail.getNoFail();
			}
			/*else{
				  fileSeq = File.getSeqNo(Integer.parseInt(getParam("socSeksyen")), Integer.parseInt(idUrusan), idKementerian, idNegeri);
				  noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;			  
			}*/
			pfdFail.setNoFail(noFail);
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			pfdFail.setIdFail(idFail);
			r = new SQLRenderer();
			r.add("id_Fail", idFail);
			  //rFail.add("kod_Jabatan", kodJabatan);
			r.add("id_Tarafkeselamatan", pfdFail.getIdTarafKeselamatan()==0?IDTARAFKESELAMATAN:pfdFail.getIdTarafKeselamatan());
			r.add("id_Seksyen",IDSEKSYEN);
			r.add("id_Urusan",pfdFail.getIdUrusan());
			r.add("id_Suburusan", pfdFail.getIdSubUrusan());
			r.add("tarikh_Daftar_Fail", r.unquote(TDF));
			r.add("flag_Fail", 0);
			r.add("tajuk_Fail", permohonan.getTujuan());
			r.add("no_Fail", noFail);
			r.add("no_Fail_Root", "TIADA");	
			r.add("id_lokasifail", 1);
			r.add("id_Negeri", pfdFail.getIdNegeri());
			r.add("id_Kementerian", pfdFail.getIdKementerian());
			r.add("id_Status", FrmUtilData.getIdStatusByLangkah("1",String.valueOf(pfdFail.getIdSubUrusan()), "="));
			r.add("id_faharasat", 1);
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("FLAG_JENIS_FAIL", r.unquote("3"));
			sql = r.getSQLInsert("TBLPFDFAIL");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			String TarikhSurKJP = permohonan.getTarikhSurat();
			String TSKJP = "to_date('" + TarikhSurKJP + "','dd/MM/yyyy')";
			
			String TarikhPermohonan = permohonan.getTarikhTerima();
			String TP = "to_date('" + TarikhPermohonan + "','dd/MM/yyyy')";
			
			long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
			permohonan.setIdPermohonan(idPermohonan);
			r = new SQLRenderer();
			r.add("id_Permohonan", idPermohonan);
			r.add("id_Fail",idFail);
			r.add("id_Jkptg",1);
			r.add("no_Permohonan","TIADA");
			r.add("no_Perserahan","TIADA");
			r.add("tarikh_Surat", r.unquote(TSKJP));
			r.add("tarikh_Terima", r.unquote(TP));
			r.add("tujuan",permohonan.getTujuan());
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			r.add("id_Status", FrmUtilData.getIdStatusByLangkah("1",String.valueOf(pfdFail.getIdSubUrusan()), "="));
			sql = r.getSQLInsert("TBLPERMOHONAN");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			long idHtpPermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
			htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
			r = new SQLRenderer();
			String idAgensi = (""+pfdFail.getIdKementerian()=="0"?String.valueOf(pfdFail.getIdKementerian()):getAgensiDefaultMengikutKementerian(String.valueOf(pfdFail.getIdKementerian())));
			r.add("id_Htppermohonan",idHtpPermohonan);
			r.add("id_Permohonan",permohonan.getIdPermohonan());
			r.add("id_Agensi",r.unquote(idAgensi));
			r.add("id_Jenistanah", htpPermohonan.getIdJenisTanah());
			r.add("id_Pegawai", htpPermohonan.getIdPegawai());
			r.add("no_Rujukan_KJP",htpPermohonan.getNoRujukanKJP());
			r.add("no_Rujukan_Lain", htpPermohonan.getNoRujukanLain());
			r.add("NO_RUJUKAN_UPT",htpPermohonan.getNoRujukanUPT());
			r.add("NO_RUJUKAN_PTG", htpPermohonan.getNoRujukanPTG());
			r.add("NO_RUJUKAN_PTD",htpPermohonan.getNoRujukanPTD());
			r.add("ID_DAERAH",htpPermohonan.getIdDaerah()==""?getDaerahDefaultMengikutNegeri(String.valueOf(pfdFail.getIdNegeri())):htpPermohonan.getIdDaerah());
			r.add("tarikh_Agihan", r.unquote("sysdate"));
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("id_Kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_Kemaskini", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			
			//rsusf = getPfdFailValues(String.valueOf(pfdFail.getIdSubUrusan()),idPermohonan,idFail,permohonan.getIdMasuk());
			//Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
			long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",String.valueOf(pfdFail.getIdSubUrusan()),"=");

			r = new SQLRenderer();		  
			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			r.add("id_Permohonan", permohonan.getIdPermohonan());
			r.add("Id_Suburusanstatus", setIdSuburusanstatus);
			r.add("aktif","1");
			r.add("id_Masuk", permohonan.getIdMasuk());
			r.add("tarikh_Masuk", r.unquote("sysdate"));
			r.add("URL", "TIADA");
			r.add("id_kemaskini", permohonan.getIdMasuk());
			r.add("tarikh_kemaskini", r.unquote("sysdate"));
			r.add("id_fail",idFail);
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			myLog.info("simpanStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
			stmt.executeUpdate(sql);
					  
			conn.commit();
			//myLog.info("===idSubUrusan" +pfdFail.getIdSubUrusan());
			//FrmPembelianSemakanData.StatusChange_Action(idPermohonan, Integer.parseInt(String.valueOf(pfdFail.getIdSubUrusan())), idFail);
		
		}catch (SQLException se) { 
			try {
				conn.rollback();
			    
			} catch (SQLException se2) {
				throw new Exception("Rollback error:"+se2.getMessage());
				
			}
			throw new Exception("Ralat Kemaskini Status:"+se.getMessage());
			    
		}catch(Exception ex){
			 conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		}finally{
			if (db != null) db.close();
			if (conn != null) conn.close();
	
		}	
		return htpPermohonan;
	}
	
	@Override
	public void simpanStatusPermohonan(Tblrujsuburusanstatusfail s,Connection conn ) throws Exception{
		Date now = new Date();
		String sekarang = "to_date('" + sdf.format(now) + "','dd/MM/yyyy')";
		Db db = null;
		String sql = "";	  
		try{
			long IdSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");		  
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();		  
			r.add("Id_Suburusanstatusfail", IdSuburusanstatusfail);
			r.add("id_Permohonan", s.getIdPermohonan());
			r.add("Id_Suburusanstatus", s.getIdSuburusanstatus());
			r.add("aktif",s.getAktif());
			r.add("id_Masuk", ""+s.getIdMasuk());
			if(!("".equals(s.getTarikhMasuk())))
				r.add("tarikh_Masuk", r.unquote("to_date('" + sdf.format(s.getTarikhMasuk()) + "','dd/MM/yyyy HH:MI:SS AM')"));
			else
				r.add("tarikh_Masuk", r.unquote(sekarang));

			if(!("".equals(s.getUrl())))
				r.add("URL", s.getUrl());
			else
				r.add("URL", "TIADA");
	  
			r.add("id_kemaskini", s.getIdMasuk());
			r.add("tarikh_kemaskini", r.unquote(sekarang));
			r.add("id_fail",s.getIdFail());
			sql = r.getSQLInsert("Tblrujsuburusanstatusfail");
			myLog.info("simpanStatusPermohonan:sql-TBLRUJSUBURUSANSTATUSFAIL::"+sql);
			stmt.executeUpdate(sql);
			conn.commit();

		}catch (SQLException se) { 
//			try {
//				conn.rollback();
//			    
//			} catch (SQLException se2) {
//				throw new Exception("Rollback error:"+se2.getMessage());
//				
//			}
			throw new Exception("Ralat Kemaskini Status:"+se.getMessage());
			    
		}catch(Exception ex){
			 //conn.rollback();
			 ex.printStackTrace();
			 throw new Exception("Ralat:"+ex.getMessage());
		
		//}finally{
			//if (db != null) db.close();
			//if (conn != null) conn.close();
	
		}			  
	}
	
	@Override
	public Tblrujsuburusanstatusfail getPfdFailValues(String idSubUrusan,Long idPermohonan,Long idFail,Long idMasuk) throws Exception {
		  Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
		  Long setIdSuburusanstatus = 0L;
		  setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSubUrusan,"=");

		  s.setIdPermohonan(idPermohonan);
		  s.setIdFail(idFail);	  
		  s.setIdSuburusanstatus(setIdSuburusanstatus);
		  s.setAktif("1");
		  s.setIdMasuk(idMasuk);
		  s.setUrl("TIADA");
		  s.setTarikhMasuk(new Date());
		  return s;
		  
	  }
	
	@Override
	public String getDaerahDefaultMengikutNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		String returnValue = "148"; //default id_daerah untuk id_negeri=0 
		sql = "Select id_Daerah,kod_Daerah,nama_Daerah from tblrujdaerah"
				+ " where id_negeri='" + idNegeri +"' AND NAMA_DAERAH like 'TIADA MAKLUMAT' "
				+ " ORDER BY lpad(kod_Daerah,10)";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getString(1);
			}
			return returnValue;
			
		} finally {
			if (db != null)
				db.close();
		}
	}	
	
	@Override
	public String getAgensiDefaultMengikutKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";
		String returnValue = "0"; 
		sql = "Select id_agensi from tblrujagensi"
				+ " where id_kementerian='" + idKementerian +"' and kod_agensi ='01' "
				+ " ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getString(1);
			}
			if(returnValue.equals("0")){
				returnValue = getAgensiDefault("");
			}	
			return returnValue;
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@Override
	public String getAgensiDefault(String kodAgensi) throws Exception {
		Db db = null;
		String sql = "";
		String returnValue = "0"; 
		sql = "Select id_agensi from tblrujagensi"
				+ " where kod_agensi ='00' ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getString(1);
			}
			return returnValue;
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	@Override
	public String getErrorHTML(String msg) throws Exception,SQLException {
		StringBuffer sb = new StringBuffer("");
		sb.append("<table width=\"100%\" border=\"0\">");
		sb.append("  <tr>");
		sb.append("    <td>");
		sb.append("    	&nbsp;<div class=\"warning\">"+msg+"</div>");
		sb.append("    </td>");
		sb.append("  </tr>");
		sb.append(" </table>");
		sb.append("</select>");
		return sb.toString();
	
	}
	
	
}
