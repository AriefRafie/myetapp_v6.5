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

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.File;

public class FrmPerletakhakanPendaftaranData {
	
	
	private static Vector listMaklumatFail = null;
	static Vector listFailPerletakhakan = null;
	static Vector senaraiSemak = null;
	static Vector listAddBaru = null;
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private static Vector MaklumatPermohonan=null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmPerletakhakanPendaftaranData.class);

	// PAPAR MAKLUMAT FAIL BY ID
	public static void setPaparMaklumatFailById(String id) throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			listMaklumatFail = new Vector();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("a.id_negeri");
			r.add("a.id_suburusan");
			r.add("a.no_fail");
			r.add("a.id_fail",r.unquote(id));
			
		
			
			sql = r.getSQLSelect("Tblpfdfail a");
			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("dat: "+sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_negeri", rs.getString("id_negeri")==null ? "" :rs.getString("id_negeri"));
				h.put("id_suburusan", rs.getString("id_suburusan")==null ? "" :rs.getString("id_suburusan"));
				h.put("no_fail", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
				listMaklumatFail.addElement(h);

			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	// PAPAR MAKLUMAT tambahan BY ID
	public static void setPaparMaklumatTambah(String id) throws Exception {

		Db db = null;
		String sql = "";

		try {
			db = new Db();
			listAddBaru = new Vector();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("b.id_negeri");
			r.add("b.id_kementerian");
			r.add("b.id_agensi");
			r.add("c.tujuan");
			r.add("a.id_suburusan");
			r.add("a.no_fail");
			r.add("d.no_rujukan_kjp");
			r.add("d.tarikh_agihan");
			r.add("d.no_rujukan_lain");
			r.add("a.id_fail",r.unquote(id));
			
			
		
			
			sql = r.getSQLSelect("Tblpfdfail a, Tblhtphakmilik b, Tblpermohonan c, Tblhtppermohonan d");
			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("dat: "+sql);

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();
				h.put("id_negeri", rs.getString("id_negeri")==null ? "" :rs.getString("id_negeri"));
				h.put("id_kementerian", rs.getString("id_kementerian")==null ? "" :rs.getString("id_kementerian"));
				h.put("id_agensi", rs.getString("id_agensi")==null ? "" :rs.getString("id_agensi"));
				h.put("tujuan", rs.getString("tujuan")==null ? "" :rs.getString("tujuan"));
				h.put("id_suburusan", rs.getString("id_suburusan")==null ? "" :rs.getString("id_suburusan"));
				h.put("no_fail", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
				h.put("no_rujukan_kjp", rs.getString("no_rujukan_kjp")==null ? "" :rs.getString("no_rujukan_kjp"));
//				h.put("tarikh_agihan", rs.getDate("tarikh_agihan")==null ? "" :format(rs.getDate("tarikh_agihan"))); 
				h.put("no_rujukan_lain", rs.getString("no_rujukan_lain")==null ? "" :rs.getString("no_rujukan_lain"));
				listAddBaru.addElement(h);
				

			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Vector getPaparMaklumatFailById() {
		return listMaklumatFail;
	}	
	
	public static Vector getPaparMaklumatTambah(){
		return listAddBaru;
	}	

	// SET CARIAN HAKMILIK PERLETAKHAKAN	
	public static void getSenaraiFail(String noFail, String tajukFail, String id_kementerian, String idAgensiC, String idNegeriC, String idDaerahC, String idMukimC)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			db = new Db();
			listFailPerletakhakan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.TUJUAN,C.KETERANGAN,B.ID_PERMOHONAN,D.ID_HTPPERMOHONAN," +
					" E.ID_SUBURUSANSTATUSFAIL, F.NAMA_NEGERI, A.ID_NEGERI, D.ID_DAERAH, A.ID_KEMENTERIAN, D.ID_AGENSI "+
					" FROM TBLPFDFAIL A,"+
					" TBLPERMOHONAN B,"+
					" TBLRUJSTATUS C,"+
					" TBLHTPPERMOHONAN D, "+
					" TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJNEGERI F, TBLRUJSUBURUSANSTATUS G"+
					" WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
					" AND C.ID_STATUS = G.ID_STATUS AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = e.ID_FAIL AND A.ID_NEGERI = F.ID_NEGERI "+
					" AND G.ID_SUBURUSANSTATUS=E.ID_SUBURUSANSTATUS AND E.AKTIF='1'";
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//tajuk fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.TUJUAN) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//ID NEGERI
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//ID DAERAH
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND D.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//ID MUKIM
/*			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}*/
			
			//ID KEMENTERIAN
			if (id_kementerian != null) {
				if (!id_kementerian.trim().equals("") && !id_kementerian.trim().equals("99999")) {
					sql = sql + " AND A.ID_KEMENTERIAN = '" + id_kementerian.trim() + "'";
				}
			}
			
			//ID AGENSI
			if (idAgensiC != null) {
				if (!idAgensiC.trim().equals("") && !idAgensiC.trim().equals("99999")) {
					sql = sql + " AND D.ID_AGENSI = '" + idAgensiC.trim() + "'";
				}
			}
			
			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
				listFailPerletakhakan.addElement(h);
				bil++;
				count++;
			}			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public static void getSenaraiFail2(String noFail, String tajukFail, String id_kementerian, String idAgensiC, String idNegeriC, String idDaerahC, String idMukimC)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			db = new Db();
			listFailPerletakhakan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.TUJUAN,C.KETERANGAN,B.ID_PERMOHONAN,D.ID_HTPPERMOHONAN," +
					" E.ID_SUBURUSANSTATUSFAIL, F.NAMA_NEGERI, A.ID_NEGERI, D.ID_DAERAH, A.ID_KEMENTERIAN, D.ID_AGENSI "+
					" FROM TBLPFDFAIL A,"+
					" TBLPERMOHONAN B,"+
					" TBLRUJSTATUS C,"+
					" TBLHTPPERMOHONAN D, "+
					" TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJNEGERI F, TBLRUJSUBURUSANSTATUS G"+
					" WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
					" AND C.ID_STATUS = G.ID_STATUS AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = e.ID_FAIL AND A.ID_NEGERI = F.ID_NEGERI "+
					" AND G.ID_SUBURUSANSTATUS=E.ID_SUBURUSANSTATUS AND E.AKTIF='1'";
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//tajuk fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.TUJUAN) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//ID NEGERI
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//ID DAERAH
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND D.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//ID MUKIM
/*			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}*/
			
			//ID KEMENTERIAN
//			if (id_kementerian != null) {
//				if (!id_kementerian.trim().equals("") && !id_kementerian.trim().equals("99999")) {
//					sql = sql + " AND A.ID_KEMENTERIAN = '" + id_kementerian.trim() + "'";
//				}
//			}
			
			//ID AGENSI
			if (idAgensiC != null) {
				if (!idAgensiC.trim().equals("") && !idAgensiC.trim().equals("99999")) {
					sql = sql + " AND D.ID_AGENSI = '" + idAgensiC.trim() + "'";
				}
			}
			
			sql = sql + "  AND A.NO_FAIL not in (' ','TIADA') ORDER BY B.ID_PERMOHONAN DESC";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
				listFailPerletakhakan.addElement(h);
				bil++;
				count++;
			}			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	
	// SET CARIAN HAKMILIK PERLETAKHAKAN	
	public static Vector getSenaraiFail(String noFail, String tajukFail, String idkementerianC, String idAgensiC, String idNegeriC, String idDaerahC, String idMukimC,String idUser)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			db = new Db();
			listFailPerletakhakan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.TUJUAN,C.KETERANGAN,B.ID_PERMOHONAN,D.ID_HTPPERMOHONAN," +
					" E.ID_SUBURUSANSTATUSFAIL, F.NAMA_NEGERI, A.ID_NEGERI, D.ID_DAERAH, A.ID_KEMENTERIAN, D.ID_AGENSI "+
					" FROM TBLPFDFAIL A,"+
					" TBLPERMOHONAN B,"+
					" TBLRUJSTATUS C,"+
					" TBLHTPPERMOHONAN D, "+
					" TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJNEGERI F, TBLRUJSUBURUSANSTATUS G"+
					" WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
					" AND C.ID_STATUS = G.ID_STATUS AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = e.ID_FAIL AND A.ID_NEGERI = F.ID_NEGERI "+
					" AND G.ID_SUBURUSANSTATUS=E.ID_SUBURUSANSTATUS AND E.AKTIF='1'";
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//tajuk fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.TUJUAN) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//ID NEGERI
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//ID DAERAH
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND D.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//ID MUKIM
/*			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}*/
			
			//ID KEMENTERIAN
			if (idkementerianC != null) {
				if (!idkementerianC.trim().equals("") && !idkementerianC.trim().equals("99999")) {
					sql = sql + " AND A.ID_KEMENTERIAN = '" + idkementerianC.trim() + "'";
				}
			}
			
			//ID AGENSI
			if (idAgensiC != null) {
				if (!idAgensiC.trim().equals("") && !idAgensiC.trim().equals("99999")) {
					sql = sql + " AND D.ID_AGENSI = '" + idAgensiC.trim() + "'";
				}
			}
			if (idUser != null) {
				if (!idUser.trim().equals("") && !idUser.trim().equals("99999")) {
					sql = sql + " AND A.ID_MASUK = '" + idUser.trim() + "'";
				}
			}			
			//sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			sql = sql + " ORDER BY B.TARIKH_MASUK DESC";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"":rs.getString("NO_FAIL"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
				listFailPerletakhakan.addElement(h);
				bil++;
				count++;
			}			
		return listFailPerletakhakan;
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	
	// SET CARIAN HAKMILIK PERLETAKHAKAN	
	public static Vector getSenaraiFailOnline(String noFail, String tajukFail, String idkementerianC, String idAgensiC, String idNegeriC, String idDaerahC, String idMukimC,String idUser)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			db = new Db();
			listFailPerletakhakan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.TUJUAN,C.KETERANGAN,B.ID_PERMOHONAN,D.ID_HTPPERMOHONAN," +
				" E.ID_SUBURUSANSTATUSFAIL, F.NAMA_NEGERI, A.ID_NEGERI, D.ID_DAERAH, A.ID_KEMENTERIAN, D.ID_AGENSI " +
				" ,B.NO_PERMOHONAN"+
				" FROM TBLPFDFAIL A,"+
				" TBLPERMOHONAN B,"+
				" TBLRUJSTATUS C,"+
				" TBLHTPPERMOHONAN D, "+
				" TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJNEGERI F, TBLRUJSUBURUSANSTATUS G"+
				" WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL " +
				" AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
				" AND C.ID_STATUS = G.ID_STATUS AND E.ID_PERMOHONAN = B.ID_PERMOHONAN " +
				" AND B.ID_FAIL = e.ID_FAIL AND A.ID_NEGERI = F.ID_NEGERI "+
				" AND G.ID_SUBURUSANSTATUS=E.ID_SUBURUSANSTATUS AND E.AKTIF='1'" +
				"";
			sql +=" AND A.ID_STATUS <> 999 ";
			sql +=" AND nvl(A.NO_FAIL,' ') = ' ' ";

			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//tajuk fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.TUJUAN) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//ID NEGERI
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//ID DAERAH
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND D.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//ID MUKIM
/*			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}*/
			
			//ID KEMENTERIAN
			if (idkementerianC != null) {
				if (!idkementerianC.trim().equals("") && !idkementerianC.trim().equals("99999")) {
					sql = sql + " AND A.ID_KEMENTERIAN = '" + idkementerianC.trim() + "'";
				}
			}
			
			//ID AGENSI
			if (idAgensiC != null) {
				if (!idAgensiC.trim().equals("") && !idAgensiC.trim().equals("99999")) {
					sql = sql + " AND D.ID_AGENSI = '" + idAgensiC.trim() + "'";
				}
			}
			if (idUser != null) {
				if (!idUser.trim().equals("") && !idUser.trim().equals("99999")) {
					sql = sql + " AND A.ID_MASUK = '" + idUser.trim() + "'";
				}
			}			
			//sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			sql = sql + " ORDER BY B.TARIKH_MASUK DESC";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"TIADA":rs.getString("NO_FAIL"));
				h.put("noPermohonan",rs.getString("NO_PERMOHONAN")== null?"TIADA":rs.getString("NO_PERMOHONAN"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
				listFailPerletakhakan.addElement(h);
				bil++;
				count++;
			}			
		return listFailPerletakhakan;
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	
public static Vector getSenaraiFailOnline2(String noFail, String tajukFail, String idkementerianC, String idAgensiC, String idNegeriC, String idDaerahC, String idMukimC,String idUser)throws Exception{
		
		Db db = null;
		String sql = "";
		
		try{
			db = new Db();
			listFailPerletakhakan = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TARIKH_DAFTAR_FAIL, B.TUJUAN,C.KETERANGAN,B.ID_PERMOHONAN,D.ID_HTPPERMOHONAN," +
					" E.ID_SUBURUSANSTATUSFAIL, F.NAMA_NEGERI, A.ID_NEGERI, D.ID_DAERAH, A.ID_KEMENTERIAN, D.ID_AGENSI " +
					" ,B.NO_PERMOHONAN"+
					" FROM TBLPFDFAIL A,"+
					" TBLPERMOHONAN B,"+
					" TBLRUJSTATUS C,"+
					" TBLHTPPERMOHONAN D, "+
					" TBLRUJSUBURUSANSTATUSFAIL E, TBLRUJNEGERI F, TBLRUJSUBURUSANSTATUS G"+
					" WHERE A.ID_SEKSYEN = 3 AND A.ID_URUSAN = 5 AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = D.ID_PERMOHONAN" +
					" AND C.ID_STATUS = G.ID_STATUS AND E.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_FAIL = e.ID_FAIL AND A.ID_NEGERI = F.ID_NEGERI "+
					" AND G.ID_SUBURUSANSTATUS=E.ID_SUBURUSANSTATUS AND E.AKTIF='1'";
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.NO_PERMOHONAN) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//tajuk fail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(B.TUJUAN) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			//ID NEGERI
			if (idNegeriC != null) {
				if (!idNegeriC.trim().equals("") && !idNegeriC.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeriC.trim() + "'";
				}
			}
			
			//ID DAERAH
			if (idDaerahC != null) {
				if (!idDaerahC.trim().equals("") && !idDaerahC.trim().equals("99999")) {
					sql = sql + " AND D.ID_DAERAH = '" + idDaerahC.trim() + "'";
				}
			}
			
			//ID MUKIM
/*			if (idMukimC != null) {
				if (!idMukimC.trim().equals("") && !idMukimC.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukimC.trim() + "'";
				}
			}*/
			
			//ID KEMENTERIAN
			if (idkementerianC != null) {
				if (!idkementerianC.trim().equals("") && !idkementerianC.trim().equals("99999")) {
					sql = sql + " AND A.ID_KEMENTERIAN = '" + idkementerianC.trim() + "'";
				}
			}
			
			//ID AGENSI
			if (idAgensiC != null) {
				if (!idAgensiC.trim().equals("") && !idAgensiC.trim().equals("99999")) {
					sql = sql + " AND D.ID_AGENSI = '" + idAgensiC.trim() + "'";
				}
			}
			if (idUser != null) {
				if (!idUser.trim().equals("") && !idUser.trim().equals("99999")) {
					sql = sql + " AND A.ID_MASUK = '" + idUser.trim() + "'";
				}
			}			
			//sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			sql = sql + " ORDER BY B.TARIKH_MASUK DESC";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			int count = 0;
			while(rs.next()){
				h = new Hashtable();
				h.put("bil",bil);
				h.put("idFail",rs.getString("ID_FAIL"));
				h.put("idPermohonan",rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail",rs.getString("NO_FAIL")== null?"TIADA":rs.getString("NO_FAIL"));
				h.put("noPermohonan",rs.getString("NO_PERMOHONAN")== null?"TIADA":rs.getString("NO_PERMOHONAN"));
				h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")== null?"":Format.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tujuan",rs.getString("TUJUAN") == null?"":rs.getString("TUJUAN"));
				h.put("keterangan",rs.getString("KETERANGAN") == null?"":rs.getString("KETERANGAN"));
				h.put("negeri",rs.getString("NAMA_NEGERI") == null?"":rs.getString("NAMA_NEGERI"));
				listFailPerletakhakan.addElement(h);
				bil++;
				count++;
			}			
		return listFailPerletakhakan;
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}

	
	//	dat 11/2
	public String save(String txtTajuk,String txtNoFailKJP,String txdTarikhSurKJP, String txtNoFailLain, String idkementerian, String idAgensi, String idNegeri, String idDaerah, String idSuburusan, HttpSession session) throws Exception {
	
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//Date now = new Date();
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			String tarikhSurKJP = "to_date('" + txdTarikhSurKJP + "','dd/MM/yyyy HH:MI:SS AM')";

			// UNTUK DAPATKAN KOD NEGERI
			sql = "SELECT TBLRUJNEGERI.KOD_MAMPU FROM TBLRUJNEGERI WHERE TBLRUJNEGERI.ID_NEGERI="+idNegeri;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String kodNeg = rs.getString("KOD_MAMPU");
			
			// UNTUK DAPATKAN KOD KEMENTERIAN
			sql = "SELECT TBLRUJKEMENTERIAN.KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE TBLRUJKEMENTERIAN.ID_KEMENTERIAN="+idkementerian;
			ResultSet rs2 = stmt.executeQuery(sql);
			rs2.next();
			String kodKem = rs2.getString("KOD_KEMENTERIAN");
			
			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "5");
			r.add("ID_SUBURUSAN", idSuburusan);
		   
		    r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", txtTajuk);
			r.add("ID_STATUS",8);
			
			String kodUrusan = "712";
			r.add("NO_FAIL",generateNoFail(kodUrusan, kodKem, idkementerian, kodNeg, idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN",idkementerian);
			r.add("ID_MASUK", userId);
			 
			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long id_permohonan = DB.getNextID(db,"TBLPERMOHONAN_SEQ");
		    r.add("id_permohonan",id_permohonan);
		    r.add("id_fail", idFail);
		    r.add("id_jkptg", 1);
		    r.add("tujuan", txtTajuk);
		    r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
		    r.add("ID_MASUK", userId);
		      
		      
		    sql = r.getSQLInsert("TBLPERMOHONAN");
		    stmt.executeUpdate(sql);
		    
			//TBLHTPPERMOHONAN
		    r = new SQLRenderer();
			long idhtp = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
		    r.add("id_htppermohonan",idhtp);
		    r.add("id_permohonan",id_permohonan);
		    r.add("id_daerah", idDaerah);
		    r.add("id_agensi", idAgensi);
		    r.add("tarikh_agihan",r.unquote(tarikhSurKJP));
		    r.add("no_rujukan_kjp", txtNoFailKJP);
		    r.add("no_rujukan_lain", txtNoFailLain);
		    r.add("id_pegawai", 1);
		    
		    r.add("ID_MASUK", userId);
	      
		    sql = r.getSQLInsert("TBLHTPPERMOHONAN");
		    stmt.executeUpdate(sql);
		
		    r = new SQLRenderer();
		    long idSubFail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
		    r.add("id_suburusanstatusfail", idSubFail);
		    r.add("id_permohonan",id_permohonan);
		    
		    Integer subUrusan = Integer.parseInt(idSuburusan);
		    String subUrusanStatus = String.valueOf(FrmUtilData.getIdSuburusanstatusByLangkah("1",""+subUrusan,"="));
	    	r.add("id_suburusanstatus",r.unquote(subUrusanStatus));
		   
		    r.add("aktif",1);
		    r.add("id_masuk",userId);
		    r.add("tarikh_masuk",r.unquote("SYSDATE"));
		    r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
		    r.add("id_fail",idFail);
		    
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
		return idFailString;
	}
	
	public String saveOnline(String txtTajuk,String txtNoFailKJP,String txdTarikhSurKJP, String txtNoFailLain, String idkementerian, String idAgensi, String idNegeri, String idDaerah, String idSuburusan, HttpSession session) throws Exception {
		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idFailString = "";		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//Date now = new Date();
			//SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			String tarikhSurKJP = "to_date('" + txdTarikhSurKJP + "','dd/MM/yyyy')";

			// UNTUK DAPATKAN KOD NEGERI
			sql = "SELECT TBLRUJNEGERI.KOD_MAMPU FROM TBLRUJNEGERI WHERE TBLRUJNEGERI.ID_NEGERI="+idNegeri;
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			String kodNeg = rs.getString("KOD_MAMPU");
			
			// UNTUK DAPATKAN KOD KEMENTERIAN
			sql = "SELECT TBLRUJKEMENTERIAN.KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE TBLRUJKEMENTERIAN.ID_KEMENTERIAN="+idkementerian;
			ResultSet rs2 = stmt.executeQuery(sql);
			rs2.next();
			String kodKem = rs2.getString("KOD_KEMENTERIAN");
			
			//TBLPFDFAIL
			long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "5");
			r.add("ID_SUBURUSAN", idSuburusan);
		   
		    r.add("ID_TARAFKESELAMATAN", "1");
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", txtTajuk);
			r.add("ID_STATUS",8);
			
			String kodUrusan = "712";
			//r.add("NO_PERMOHONAN",FrmUtilData.generateNoOnline(5, kodKem, idkementerian, kodNeg, idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN",idkementerian);
			r.add("ID_MASUK", userId);
			 
			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long id_permohonan = DB.getNextID(db,"TBLPERMOHONAN_SEQ");
		    r.add("id_permohonan",id_permohonan);
			r.add("NO_PERMOHONAN",FrmUtilData.generateNoOnline(5,kodKem, idkementerian, kodNeg, idNegeri));
		    r.add("id_fail", idFail);
		    r.add("id_jkptg", 1);
		    r.add("tujuan", txtTajuk);
		    r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
		    r.add("ID_MASUK", userId);
		      
		      
		    sql = r.getSQLInsert("TBLPERMOHONAN");
		    stmt.executeUpdate(sql);
		    
			//TBLHTPPERMOHONAN
		    r = new SQLRenderer();
			long idhtp = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
		    r.add("id_htppermohonan",idhtp);
		    r.add("id_permohonan",id_permohonan);
		    r.add("id_daerah", idDaerah);
		    r.add("id_agensi", idAgensi);
		    r.add("tarikh_agihan",r.unquote(tarikhSurKJP));
		    r.add("no_rujukan_kjp", txtNoFailKJP);
		    r.add("no_rujukan_lain", txtNoFailLain);
		    r.add("id_pegawai", 1);
		    
		    r.add("ID_MASUK", userId);
	      
		    sql = r.getSQLInsert("TBLHTPPERMOHONAN");
		    stmt.executeUpdate(sql);
		
		    r = new SQLRenderer();
		    long idSubFail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
		    r.add("id_suburusanstatusfail", idSubFail);
		    r.add("id_permohonan",id_permohonan);
		    
		    Integer subUrusan = Integer.parseInt(idSuburusan);
		    String subUrusanStatus = String.valueOf(FrmUtilData.getIdSuburusanstatusByLangkah("-1",""+subUrusan,"="));
	    	r.add("id_suburusanstatus",r.unquote(subUrusanStatus));
		   
		    r.add("aktif",1);
		    r.add("id_masuk",userId);
		    r.add("tarikh_masuk",r.unquote("SYSDATE"));
		    r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
		    r.add("id_fail",idFail);
		    
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
		return idFailString;
	}

	
	public static void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			MaklumatPermohonan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN,B.TUJUAN" +
			" ,C.TARIKH_AGIHAN,C.NO_RUJUKAN_KJP,C.NO_RUJUKAN_LAIN,C.ID_AGENSI,A.ID_SUBURUSAN" +
			" ,C.ID_HTPPERMOHONAN,D.ID_SUBURUSANSTATUSFAIL,E.ID_NEGERI,E.KOD_NEGERI,F.ID_KEMENTERIAN" +
			" ,F.KOD_KEMENTERIAN, C.ID_DAERAH, B.NO_PERMOHONAN " +
			" ,A.TARIKH_MASUK " +
			" FROM TBLPFDFAIL A, TBLPERMOHONAN B,TBLHTPPERMOHONAN C" +
			" ,TBLRUJSUBURUSANSTATUSFAIL D,TBLRUJNEGERI E,TBLRUJKEMENTERIAN F" +
			" WHERE A.ID_FAIL = B.ID_FAIL(+) " +
			" AND A.ID_NEGERI = E.ID_NEGERI(+) " +
			" AND A.ID_KEMENTERIAN = F.ID_KEMENTERIAN(+) " +
			" AND B.ID_PERMOHONAN = C.ID_PERMOHONAN(+) " +
			" AND D.ID_PERMOHONAN(+) = B.ID_PERMOHONAN " +
			" AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("setMaklumatPermohonan ::" + sql);
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("idHtpPermohonan", rs.getString("ID_HTPPERMOHONAN") == null ? "" : rs.getString("ID_HTPPERMOHONAN"));
				h.put("idSuburusanStatusFail", rs.getString("ID_SUBURUSANSTATUSFAIL") == null ? "" : rs.getString("ID_SUBURUSANSTATUSFAIL"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noP", rs.getString("NO_PERMOHONAN") == null ? "" : rs.getString("NO_PERMOHONAN").toUpperCase());
				h.put("txtTajuk", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN").toUpperCase());
				h.put("txdTarikhSurKJP", rs.getDate("TARIKH_AGIHAN") == null ? "" : Format.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("txtNoFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("txtNoFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("id_negeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("id_daerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH").toUpperCase());
				h.put("id_kementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				h.put("id_agensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("kodNegeri", rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				h.put("kodKementerian", rs.getString("KOD_KEMENTERIAN") == null ? "" : rs.getString("KOD_KEMENTERIAN"));
				h.put("tarikhDaftar", rs.getDate("TARIKH_MASUK") == null ? "" : Format.format(rs.getDate("TARIKH_MASUK")));
				MaklumatPermohonan.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//update data

	public void update(String txtTajuk,String txtNoFailKJP, String txdTarikhSurKJP,String txtNoFailLain, String idPermohonan,String idHtpPermohonan, HttpSession session) throws Exception {
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
			
			SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
			String tarikhSurKJP = "to_date('" + txdTarikhSurKJP + "','dd/MM/yyyy HH:MI:SS AM')";

			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TUJUAN", txtTajuk);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_HTPPERMOHONAN", idHtpPermohonan);
			r.add("TARIKH_AGIHAN",r.unquote(tarikhSurKJP));
		    r.add("NO_RUJUKAN_KJP", txtNoFailKJP);
		    r.add("NO_RUJUKAN_LAIN", txtNoFailLain);
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
	
	public String generateNoFail(String kodUrusan, String kodKementerian, String idKementerian, String kodNegeri, String idNegeri) throws Exception{
		String noFail = "";
		//noFail = "JKPTG/SHTP/" + kodUrusan + "/" + kodKementerian + "/" + kodNegeri + "-" + String.format("%06d", File.getSeqNo(3, 5, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri)));
		noFail = "JKPTG/101/" + kodUrusan + "/" + kodKementerian + "/" + kodNegeri + "-" + File.getSeqNo(3, 5, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));
		
		System.out.println("nofail:"+noFail);
		return noFail;
	}
	
	public String generateNoOnline(int idUrusan, String kodKementerian, String idKementerian, String kodNegeri, String idNegeri) throws Exception{
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		int getYear = calendar.get(java.util.Calendar.YEAR);
		String noFail = "";
		String X = String.format("%04d",File.getSeqNo(3, idUrusan,Integer.parseInt(idKementerian), Integer.parseInt(idNegeri),getYear));

		noFail += "JKPTG/SHTP/"+ kodKementerian + "/"+ kodNegeri + "/"+X+"/"+getYear;				
		myLog.info("generateNoOnline:"+noFail);
		return noFail;
		
	}
	
	public String getIdPermonanByIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				return rs.getString("ID_PERMOHONAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector getMaklumatPermohonan() {
		return MaklumatPermohonan;
	}
	public Vector getListFailPerletakhakan() {
		return listFailPerletakhakan;
	}
}

